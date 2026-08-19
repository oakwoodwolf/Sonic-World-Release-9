

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tGravity
		Field Collision.tGravity_Collision
		Field Physics.tGravity_Physics
		Field Motion.tGravity_Motion
		Field SpeedLength#
		Field Rotation#
	End Type

	Type tGravity_Collision
		; object test collision fields
		Field CeilingTest#
		Field GroundTest#
		Field FrontTest#
		Field FrontFactor#
		Field Align, ShouldAlign, Result
		Field Normal.tVector
		Field GroundNormal.tVector
		Field CeilingNormal.tVector
		Field SpeedNormal.tVector
		
		; mesh collisions
		Field Surface 
		Field Triangle
		Field Vertex
		Field DotProduct#
		Field Cross.tVector
		Field GroundType
	End Type

	Type tGravity_Physics
		Field MOTION_GROUND#
		Field MOTION_CEILING#
		Field MOTION_CEILING_STOP#
		Field MOTION_WALL_UP#
		Field MOTION_WALL_DOWN#
		Field MOTION_WALL_DIRECTION#
		Field COMMON_YACCELERATION#
		Field COMMON_YTOPSPEED#
		Field UNDERWATERTRIGGER#
		Field UNDERWATERTRIGGERW#
	End Type

	Type tGravity_Motion
		Field Speed.tVector
		Field Align.tVector
		Field AnimationAlign.tVector
		Field Ground
		Field GroundTest
		Field Direction#
		Field WallWasHitTimer
		Field ChangedWallTimer
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Object_Gravity_Create.tGravity()
	
		g.tGravity = New tGravity
		g\Collision = New tGravity_Collision
		g\Physics = New tGravity_Physics
		g\Motion = New tGravity_Motion
		g\Motion\Speed = Vector(0, 0, 0)
		g\Motion\Align = Vector(0, 1, 0)
		g\Motion\AnimationAlign = Vector(0, 1, 0)

		g\Physics\MOTION_GROUND# = 0.65
		g\Physics\MOTION_CEILING# = -0.65
		g\Physics\MOTION_CEILING_STOP# = -0.79
		g\Physics\MOTION_WALL_UP# = -0.7
		g\Physics\MOTION_WALL_DOWN# = 0.2
		g\Physics\MOTION_WALL_DIRECTION# = 0.3

		g\Physics\UNDERWATERTRIGGER# = 1
		g\Physics\UNDERWATERTRIGGERW# = 1
		g\Physics\COMMON_YACCELERATION# = 0.06125
		g\Physics\COMMON_YTOPSPEED# = -1.055

		Return g
		
	End Function
	
	; =========================================================================================================
	; =========================================================================================================
	; =========================================================================================================
	Function Gravity_Motion(pivot, mesh, g.tGravity, d.tDeltaTime, objpickedup=0, action=0)
		; Test out collisions with scenery since last update. While doing this, calculate if character's on
		; ground, and if so, set the alignment.
		g\Motion\GroundTest = Gravity_TestCollisions(pivot, g, d)

		; Once we know if the character's on ground, check for the ground flag and change motion speed in
		; consecuence.
		Select g\Motion\GroundTest
			Case True
				Gravity_Align(pivot, g)

				If g\Motion\Ground=False Then					
					; If character just landed, transpose air speed to ground					
					Gravity_ConvertAirToGround(pivot, g)
					g\Motion\Ground = True

					; Change alignment
					g\Motion\AnimationAlign\x# = g\Motion\Align\x#
					g\Motion\AnimationAlign\y# = g\Motion\Align\y#
					g\Motion\AnimationAlign\z# = g\Motion\Align\z#
				End If
			Case False
				; If character just landed, transpose air speed to ground
				If g\Motion\Ground Then
					Gravity_ConvertGroundToAir(pivot, g)
					g\Motion\Ground = False
				End If
				
				Gravity_Align(pivot, g)
		End Select

		; Smoothly change animation alignment to the one of the object
		Vector_LinearInterpolation(g\Motion\AnimationAlign, g\Motion\Align, (0.01+Vector_Length#(g\Motion\Speed)*0.07)*d\Delta#)
		Vector_Normalize(g\Motion\AnimationAlign)

		; Change direction of the mesh
		If objpickedup=0 Then
			RotateEntity(pivot, 0, g\Motion\Direction#, 0)
			AlignToVector(pivot, g\Motion\AnimationAlign\x#, g\Motion\AnimationAlign\y#, g\Motion\AnimationAlign\z#, 2)
		EndIf

		; Now, just move over the character to the new position, based on it's speed.
		If g\Motion\Ground Then
			MoveEntity(pivot, g\Motion\Speed\x#*d\Delta, g\Motion\Speed\y#*d\Delta-(0.015+(Vector_Length#(g\Motion\Speed)*0.33*d\Delta)), g\Motion\Speed\z#*d\Delta)
		Else	
			MoveEntity(pivot, g\Motion\Speed\x#*d\Delta, g\Motion\Speed\y#*d\Delta, g\Motion\Speed\z*d\Delta)
		EndIf

		;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		; Fall physics
		Select action
			Case CHAOACTION_COMMON,CHAOACTION_DANCE,CHAOACTION_FLY:
				g\Physics\COMMON_YTOPSPEED# = -0.325*(g\Physics\UNDERWATERTRIGGERW#)
			Default:
				g\Physics\COMMON_YTOPSPEED# = -1.055*(g\Physics\UNDERWATERTRIGGERW#)
		End Select

		; Manage Y speeds
		If Not(action=CHAOACTION_SWIM Or action=CHAOACTION_FLY) Then
		If g\Motion\Ground=False Then
			g\Motion\Speed\y# = Max(g\Motion\Speed\y#-(g\Physics\COMMON_YACCELERATION#*d\Delta), g\Physics\COMMON_YTOPSPEED#)
		Else
			g\Motion\Speed\y# = 0
		End If
		EndIf

		; Calculate speed length and rotation
		g\SpeedLength#=g\Motion\Speed\z#
		g\Rotation# = Sqr#(EntityPitch(mesh)^2+EntityRoll(mesh)^2)

		; Control XZ speeds
		g\Motion\Speed\x# = 0
		g\Motion\Speed\z# = 0

		; Check if object falls
		If g\Motion\Ground and (g\Motion\Align\y# <= 0.1 And Vector_Length#(g\Motion\Speed)*10<(2.0)) Then
			Gravity_ConvertGroundToAir(pivot, g)
			g\Motion\Align\x# 	= Game\Stage\GravityAlignment\x#
			g\Motion\Align\y# 	= Game\Stage\GravityAlignment\y#
			g\Motion\Align\z# 	= Game\Stage\GravityAlignment\z#
			g\Motion\Ground 	= False 
		End If

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Gravity_TestCollisions(pivot, g.tGravity, d.tDeltaTime)
		; Define values for the dot product to be considered up and down collisions
		g\Collision\CeilingTest# = -g\Physics\MOTION_CEILING#
		g\Collision\GroundTest#  = g\Physics\MOTION_GROUND#
		g\Collision\FrontTest#	 = g\Physics\MOTION_WALL_DIRECTION#
		g\Collision\FrontFactor# = 0

		g\Collision\Align = False
		g\Collision\ShouldAlign = False
		g\Collision\Result = False
		
		; Create normal vectors for temporaly storing the alignment
		g\Collision\Normal 		= Vector(0, 0, 0)
		g\Collision\GroundNormal	= Vector(0, 0, 0)
		g\Collision\CeilingNormal	= Vector(0, 0, 0)
		g\Collision\SpeedNormal	= Vector_Copy(g\Motion\Speed) : Vector_Normalize(g\Collision\SpeedNormal)
		
		; Iterate through each collision and register all the collision data
		; that may have ocurred within the object sphere.
		For i = 1 To CountCollisions(pivot)
			; Clear current collision normal
			Vector_Set(g\Collision\Normal, 0, 0, 0)
			g\Collision\ShouldAlign = False
			
			; Add vertex normals to our normal vector
			g\Collision\GroundType=GetEntityType(CollisionEntity(pivot, i))
			Select g\Collision\GroundType
				Case COLLISION_WORLD_POLYGON,COLLISION_WORLD_POLYGON_DEATH,COLLISION_WORLD_POLYGON_DEATH_GOTHRU,COLLISION_WORLD_POLYGON_HURT,COLLISION_WORLD_POLYGON_RAIL,COLLISION_WORLD_POLYGON_BLOCK,COLLISION_WORLD_POLYGON_PINBALL,COLLISION_WORLD_POLYGON_ICE,COLLISION_WORLD_POLYGON_BOUNCE,COLLISION_WORLD_POLYGON_SLOW,COLLISION_PLAYER,COLLISION_OBJECT,COLLISION_OBJECT2
					; Setup
					g\Collision\ShouldAlign = True

					; Acquire collided surface & triangle
					g\Collision\Surface 	= CollisionSurface(pivot, i)
					g\Collision\Triangle 	= CollisionTriangle(pivot, i)

					For j = 0 To 2
						g\Collision\Vertex	= TriangleVertex(g\Collision\Surface, g\Collision\Triangle, j)
						g\Collision\Normal\x#	= g\Collision\Normal\x#+VertexNX#(g\Collision\Surface, g\Collision\Vertex)
						g\Collision\Normal\y#	= g\Collision\Normal\y#+VertexNY#(g\Collision\Surface, g\Collision\Vertex)
						g\Collision\Normal\z#	= g\Collision\Normal\z#+VertexNZ#(g\Collision\Surface, g\Collision\Vertex)
					Next
					Vector_Normalize(g\Collision\Normal)

					; Calculate dot product
					g\Collision\DotProduct# = Vector_DotProduct#(g\Collision\Normal, g\Motion\Align)
			End Select

			; Test for ground collision
			If (g\Collision\DotProduct# > g\Collision\GroundTest# And (g\Motion\Speed\y#<=0.0 Or g\Motion\Ground)) Then
				If g\Collision\ShouldAlign Then
					g\Collision\Align = True
					Vector_Add(g\Collision\GroundNormal, g\Collision\Normal)
				End If
				g\Collision\GroundTest# = g\Collision\DotProduct#
				
			End If

			; Test for ground collision
			If (g\Collision\DotProduct# < g\Collision\CeilingTest# And g\Motion\Speed\y#>0.0) Then
				Vector_Add(g\Collision\CeilingNormal, g\Collision\Normal)
				g\Collision\CeilingTest# = g\Collision\DotProduct#
			End If

			; Test for front collision. 
			If (g\Collision\DotProduct#>=g\Physics\MOTION_WALL_UP# And g\Collision\DotProduct#<=g\Physics\MOTION_WALL_DOWN#) Then
				; Even though the dot product told us there was a collision, it may have been
				; anywhere surrounding the object. Check out the orientation of the collision
				; with a cross product.
				g\Collision\Cross = Null
				
				Vector_CrossProduct(g\Collision\Normal, g\Motion\Align, g\Collision\Normal)
				g\Collision\DotProduct# = 1-Abs(Vector_DotProduct#(g\Collision\Normal, g\Collision\SpeedNormal))

				; Finally, test for front collision
				If (g\Collision\DotProduct#>g\Collision\FrontTest#) Then
					g\Collision\FrontTest# = g\Collision\DotProduct#
				End If
			End If
		Next

		; If there was a collision in the front, calculate how much the speed should drop.
		If (g\Collision\FrontTest#>g\Physics\MOTION_WALL_DIRECTION#) Then
			g\Collision\FrontFactor# = 1-Min#(((g\Collision\FrontTest#-g\Physics\MOTION_WALL_DIRECTION#)/(1-g\Physics\MOTION_WALL_DIRECTION#))*d\Delta*1.2, 1.0)
			If g\SpeedLength#>0 and (Not(g\Motion\ChangedWallTimer>0)) Then g\Motion\WallWasHitTimer=0.1*secs#
			g\Motion\Speed\x# = g\Motion\Speed\x#*g\Collision\FrontFactor#
			g\Motion\Speed\z# = g\Motion\Speed\z#*g\Collision\FrontFactor#
		End If
		If g\Motion\WallWasHitTimer>0 Then g\Motion\WallWasHitTimer=g\Motion\WallWasHitTimer-timervalue#
		If g\Motion\ChangedWallTimer>0 Then g\Motion\ChangedWallTimer=g\Motion\ChangedWallTimer-timervalue#

		; Once we know there's ground collision for sure, change alignment.
		If (g\Collision\GroundTest# > g\Physics\MOTION_GROUND#) Then
			If g\Collision\Align Then
				Vector_Normalize(g\Collision\GroundNormal)
				Vector_SetFromVector(g\Motion\Align, g\Collision\GroundNormal)
			Else
				Vector_SetFromVector(g\Motion\Align, Game\Stage\GravityAlignment)
			End If
			g\Collision\Result = True
		; If no ground collision was found, maybe there's ceiling collision
		Else If (g\Collision\CeilingTest# < g\Physics\MOTION_CEILING#) Then
			; If the ceiling slope is low enough, make Sonic land on the
			; ceiling.
			If (g\Collision\CeilingTest# < g\Physics\MOTION_CEILING_STOP#) Then
				g\Motion\Speed\y# = 0
				g\Collision\Result = False
			
			; If not, adjust to new alignment
			Else
				Vector_Normalize(g\Collision\CeilingNormal)
				Vector_SetFromVector(g\Motion\Align, g\Collision\CeilingNormal)
				g\Collision\Result = True
			End If
		Else
			Vector_SetFromVector(g\Motion\Align, Game\Stage\GravityAlignment)
			g\Collision\Result = False
		End If

		Delete g\Collision\Normal: Delete g\Collision\GroundNormal : Delete g\Collision\CeilingNormal : Delete g\Collision\SpeedNormal
		Return g\Collision\Result

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Gravity_Align(pivot, g.tGravity)
		RotateEntity(pivot, 0, 0, 0)
		AlignToVector(pivot, g\Motion\Align\x#, g\Motion\Align\y#, g\Motion\Align\z#, 2)
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Gravity_ConvertAirToGround(pivot, g.tGravity)
		TFormVector(g\Motion\Speed\x#, g\Motion\Speed\y#, g\Motion\Speed\z#, Game\Stage\Gravity, pivot)
		g\Motion\Speed\x# = TFormedX#()
		g\Motion\Speed\y# = 0
		g\Motion\Speed\z# = TFormedZ#()
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Gravity_ConvertGroundToAir(pivot, g.tGravity)
		TFormVector(g\Motion\Speed\x#, g\Motion\Speed\y#, g\Motion\Speed\z#, pivot, Game\Stage\Gravity)
		g\Motion\Speed\x# = TFormedX#()
		g\Motion\Speed\y# = TFormedY#()
		g\Motion\Speed\z# = TFormedZ#()
	End Function