

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---- Motion routines ----
	; =========================================================================================================
	; =========================================================================================================
	Function Player_Motion(p.tPlayer, d.tDeltaTime)

		; Acquire position
		Player_UpdatePosition(p)

		; Test out collisions with scenery since last update. While doing this, calculate if character's on
		; ground, and if so, set the alignment.
		p\Motion\GroundTest = Player_TestCollisions(p, d)

		; Once we know if the character's on ground, check for the ground flag and change motion speed in
		; consecuence.
		Select p\Motion\GroundTest
			Case True
				Player_Align(p)

				If p\Physics\Rolling Then
					Player_ConvertGroundToRoll(p, d)
				End If

				If p\Motion\Ground=False and (Not(p\OnDeathMeshTimer>0)) Then					
					; If character just landed, transpose air speed to ground					
					Player_ConvertAirToGround(p)
					p\Motion\Ground = True
					Player_ResetAirRestrictionStuff(p)

					; Change alignment
					p\Animation\Align\x# = p\Motion\Align\x#
					p\Animation\Align\y# = p\Motion\Align\y#
					p\Animation\Align\z# = p\Motion\Align\z#
				End If
			Case False
				; If character just landed, transpose air speed to ground
				If p\Motion\Ground Then
					Player_ConvertGroundToAir(p)
					p\Motion\Ground = False
					Player_ResetAirRestrictionStuff(p)
				End If

				Player_Align(p)
		End Select

		; Place air beginner
		Select p\AirBegGround
			Case False:
				If EntityDistance(p\Objects\Entity,p\Objects\AirBeg)>800 Then
					If (Not(p\Action=ACTION_TORNADO Or p\BoardWaterTimer>0)) Then p\AirBegTooFar=True
				EndIf
				If p\Motion\Ground Then
					If (Not(p\Action=ACTION_CLIMB)) Then p\AirBegGround=True
				EndIf
			Case True:
				If (Not(p\Action=ACTION_CLIMB)) Then
					p\AirBegTooFar=False
					If p\Motion\Ground=False Then Player_Motion_ResetAirBeg(p)
				Else
					p\AirBegGround=False
				EndIf
		End Select

		; Place hommer
		If p\Objects\Hommer\Done=0 Then
			PositionEntity p\Objects\Hommer\Entity, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1
			If Not(Player_IsPlayable(p)) Then
				If p\Action=ACTION_RIVALDIE Then
					p\Objects\Hommer\CanHoming=False
					p\Objects\Hommer\Done=1
				EndIf
			EndIf
		EndIf

		; Smoothly change animation alignment to the one of the player
		Vector_LinearInterpolation(p\Animation\Align, p\Motion\Align, (0.01+Vector_Length#(p\Motion\Speed)*0.07)*d\Delta#)
		Vector_Normalize(p\Animation\Align)

		If p\No#=1 Or (Not(p\FollowerIsHoldingLeaderTimer>0)) Then
			Player_Motion_Placements(p)

			; dummy fix for turning back
			If abs(p\Animation\Direction#-180)<0.05 Or abs(p\Animation\Direction#-0)<0.05 Then
				Select(Rand(1,2))
				Case 1: p\Animation\Direction#=p\Animation\Direction#+0.1
				Case 2: p\Animation\Direction#=p\Animation\Direction#-0.1
				End Select
			EndIf
		
			; Now, just move over the character to the new position, based on it's speed.
			If p\Motion\Ground Then
				MoveEntity(p\Objects\Entity, p\Motion\Speed\x#*p\Physics\ICETRIGGER3#*d\Delta, p\Motion\Speed\y#*d\Delta-(0.015+(Vector_Length#(p\Motion\Speed)*0.33*d\Delta)), p\Motion\Speed\z#*p\Physics\ICETRIGGER3#*d\Delta)
			Else	
				MoveEntity(p\Objects\Entity, p\Motion\Speed\x#*p\Physics\ICETRIGGER3#*d\Delta, p\Motion\Speed\y#*d\Delta, p\Motion\Speed\z#*p\Physics\ICETRIGGER3#*d\Delta)
			EndIf
		EndIf

		; Followers holding
		If p\No#=1 Then
			Select p\Action
				Case ACTION_FLY,ACTION_LEVITATE,ACTION_VICTORYHOLD:
					Player_FollowerHolding_ByFeet(p)
				Case ACTION_GLIDE,ACTION_SOAR,ACTION_SOARFLAP:
					Player_FollowerHolding_ByTriangleDive(p)
				Case ACTION_SLOWGLIDE,ACTION_HOVER,ACTION_SHOOTHOVER,ACTION_SLEET:
					Select p\Character
						Case CHAR_TIA,CHAR_EGR, CHAR_TRI: Player_FollowerHolding_ByFeet(p)
						Default: Player_FollowerHolding_ByLatchOn(p)
					End Select
			End Select
		EndIf

		; Apply minimum speed
		If p\SpeedLength#<p\Physics\COMMON_XZMINSPEED# Then
			If abs(p\SpeedLength#-p\Physics\COMMON_XZMINSPEED#)<1 Then
				Player_SetSpeed(p,p\SpeedLength#+abs(p\SpeedLength#-p\Physics\COMMON_XZMINSPEED#)*d\Delta)
			Else
				Player_SetSpeed(p,p\SpeedLength#+1*d\Delta)
			EndIf
		EndIf

		; Deal trails
		If p\No#=1 and (Game\MachLock>0 Or Game\SpeedShoes=1 or  p\Flags\SonicBoom=True Or (p\Action=ACTION_SKYDIVE and Input\Hold\ActionRoll)) and (Not(p\Action=ACTION_HOMING Or p\Action=ACTION_DEBUG)) and (p\SpeedLength#>0 Or abs(p\Motion\Speed\y#)>1) and (Game\SuperForm=0 Or Game\MachLock>0) and Game\Victory=0 Then
			If (Not(p\SonicBoomTrailTimer>0)) Then
				Player_FreeTrails(p,1)
				For i=0 to 4
					PositionEntity(p\Objects\PPivot[i], EntityX#(p\Objects\Mesh),EntityY#(p\Objects\Mesh),EntityZ#(p\Objects\Mesh), True)
					MoveEntity(p\Objects\PPivot[i], 0.5*Rand(-3,3)+Rand(-1,1)*p\ScaleFactor#,0.5*Rand(-3,3)+Rand(-1,1)*p\ScaleFactor#,0.15*Rand(-2,2)+Rand(-1,1)*p\ScaleFactor#)
					Player_SpawnTrail(p,i)
				Next
				p\SonicBoomTrailTimer=0.15*secs#
			EndIf
			For i=0 to 4
				RotateEntity(p\Objects\PPivot[i],EntityPitch(p\Objects\Entity),p\Animation\Direction#,EntityRoll(p\Objects\Entity))
			Next
		Else
			Player_FreeTrails(p,1)
		EndIf
		
		; Deal longtrail
		Select p\Action
			Case ACTION_JUMPDASH,ACTION_SPRINT,ACTION_HOMING,ACTION_STOMP,ACTION_BUMPED,ACTION_ROLL,ACTION_DRIFT,ACTION_BELLYFLOP: 
				If p\Flags\LongTrailCreated=0 Then 
					Player_LongTrail(p, 1)
					p\Flags\LongTrailCreated=1
				EndIf
			Case ACTION_SOAR,ACTION_GLIDE,ACTION_DOUBLEJUMP:
				If p\Motion\Ground=False Then
					If p\Flags\LongTrailCreated=0 Then 
						Player_LongTrail(p, 1)
						p\Flags\LongTrailCreated=1
						p\TrailTimer = 0.175*secs#
					EndIf
				End If
				If (Not p\TrailTimer>0) Then Player_FreeLongTrails(p,2)
			Default:
				p\Flags\LongTrailCreated=0
				Player_FreeLongTrails(p,2)
		End Select

		; Deal places
		If Game\Vehicle=0 Then Player_Motion_PetPlacements(p)
		If p\No#=1 Then
			If Menu\Members>1 Then
				PositionEntity p\Objects\Follower, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh), 1
				RotateEntity p\Objects\Follower, EntityPitch(p\Objects\Mesh), EntityYaw(p\Objects\Mesh), EntityRoll(p\Objects\Mesh), 1
				MoveEntity p\Objects\Follower, Player_ReturnFollowerPosition(p,1,2), 0, Player_ReturnFollowerPosition(p,2,2)
				PositionEntity p\Objects\FollowerPlace[2-1], EntityX(p\Objects\Follower), EntityY(p\Objects\Follower), EntityZ(p\Objects\Follower), 1
				RotateEntity p\Objects\FollowerPlace[2-1], EntityPitch(p\Objects\Follower), EntityYaw(p\Objects\Follower), EntityRoll(p\Objects\Follower), 1
			EndIf
			If Menu\Members>2 Then
				PositionEntity p\Objects\Follower, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh), 1
				RotateEntity p\Objects\Follower, EntityPitch(p\Objects\Mesh), EntityYaw(p\Objects\Mesh), EntityRoll(p\Objects\Mesh), 1
				MoveEntity p\Objects\Follower, Player_ReturnFollowerPosition(p,1,3), 0, Player_ReturnFollowerPosition(p,2,3)
				PositionEntity p\Objects\FollowerPlace[3-1], EntityX(p\Objects\Follower), EntityY(p\Objects\Follower), EntityZ(p\Objects\Follower), 1
				RotateEntity p\Objects\FollowerPlace[3-1], EntityPitch(p\Objects\Follower), EntityYaw(p\Objects\Follower), EntityRoll(p\Objects\Follower), 1
			EndIf
		EndIf

	End Function

	Function Player_ReturnFollowerPosition(p.tPlayer,xz,follower)
		Select follower
			Case 2: side=1
			Case 3: side=-1
		End Select

		If (Not(Game\RunLock>0)) Then
			If Player_IsPowerChar(p) Then mode=2 Else mode=1
		Else
			mode=3
		EndIf

		Select mode
			Case 1:
				Select xz
					Case 1:
						Return ( side*(5+1*p\ScaleFactor#+5*pp(follower)\ScaleFactor#) )
					Case 2:
						Return ( -((5+5*(follower-2))+1*p\ScaleFactor#+1*pp(follower)\ScaleFactor#) )
				End Select
			Case 2:
				Select xz
					Case 1:
						Return ( side*(10+1*p\ScaleFactor#+5*pp(follower)\ScaleFactor#) )
					Case 2:
						Return ( -(5+1*p\ScaleFactor#) )
				End Select
			Case 3:
				Select xz
					Case 1:
						Return 0
					Case 2:
						Return ( -((5+5*(follower-2))+1*p\ScaleFactor#+1*pp(follower)\ScaleFactor#) )
				End Select
		End Select
	End Function

	Function Player_Motion_ResetAirBeg(p.tPlayer)
		p\AirBegGround=False : p\AirBegTooFar=False
		PositionEntity p\Objects\AirBeg, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_TestCollisions(p.tPlayer, d.tDeltaTime)
		; Define values for the dot product to be considered up and down collisions
		p\Collision\CeilingTest# = -p\Physics\MOTION_CEILING#
		p\Collision\GroundTest#  = p\Physics\MOTION_GROUND#
		p\Collision\FrontTest#	 = p\Physics\MOTION_WALL_DIRECTION#
		p\Collision\FrontFactor# = 0

		p\Collision\Align = False
		p\Collision\ShouldAlign = False
		p\Collision\Result = False
		
		; Create normal vectors for temporaly storing the alignment
		p\Collision\Normal  		= Vector(0, 0, 0)
		p\Collision\GroundNormal	= Vector(0, 0, 0)
		p\Collision\CeilingNormal	= Vector(0, 0, 0)
		p\Collision\SpeedNormal		= Vector_Copy(p\Motion\Speed) : Vector_Normalize(p\Collision\SpeedNormal)
		
		; Iterate through each collision and register all the collision data
		; that may have ocurred within the player sphere.
		For i = 1 To CountCollisions(p\Objects\Entity)
			; Clear current collision normal
			Vector_Set(p\Collision\Normal, 0, 0, 0)
			p\Collision\ShouldAlign = False
			
			; Add vertex normals to our normal vector
			p\Collision\GroundType=GetEntityType(CollisionEntity(p\Objects\Entity, i))
			Select p\Collision\GroundType
				Case COLLISION_WORLD_POLYGON,COLLISION_WORLD_POLYGON_DEATH,COLLISION_WORLD_POLYGON_DEATH_GOTHRU,COLLISION_WORLD_POLYGON_HURT,COLLISION_WORLD_POLYGON_RAIL,COLLISION_WORLD_POLYGON_BLOCK,COLLISION_WORLD_POLYGON_PINBALL,COLLISION_WORLD_POLYGON_ICE,COLLISION_WORLD_POLYGON_BOUNCE,COLLISION_WORLD_POLYGON_SLOW,COLLISION_WORLD_POLYGON_SLIDEXP,COLLISION_WORLD_POLYGON_SLIDEXN,COLLISION_WORLD_POLYGON_SLIDEZP,COLLISION_WORLD_POLYGON_SLIDEZN,COLLISION_PLAYER,COLLISION_OBJECT,COLLISION_OBJECT2
					; Setup
					p\Collision\ShouldAlign = True

					; Acquire collided surface & triangle
					p\Collision\Surface 	= CollisionSurface(p\Objects\Entity, i)
					p\Collision\Triangle 	= CollisionTriangle(p\Objects\Entity, i)

					For j = 0 To 2
						p\Collision\Vertex	= TriangleVertex(p\Collision\Surface, p\Collision\Triangle, j)
						p\Collision\Normal\x#	= p\Collision\Normal\x#+VertexNX#(p\Collision\Surface, p\Collision\Vertex)
						p\Collision\Normal\y#	= p\Collision\Normal\y#+VertexNY#(p\Collision\Surface, p\Collision\Vertex)
						p\Collision\Normal\z#	= p\Collision\Normal\z#+VertexNZ#(p\Collision\Surface, p\Collision\Vertex)
					Next
					Vector_Normalize(p\Collision\Normal)

					; Calculate dot product
					p\Collision\DotProduct# = Vector_DotProduct#(p\Collision\Normal, p\Motion\Align)

					If Not(p\Action=ACTION_DEBUG) Then
					Select p\Collision\GroundType
						Case COLLISION_WORLD_POLYGON:
							If p\Action=ACTION_GRIND Then p\Action=ACTION_COMMON
						Case COLLISION_WORLD_POLYGON_DEATH,COLLISION_WORLD_POLYGON_DEATH_GOTHRU:
							p\OnDeathMeshTimer=0.1*secs#
							Player_TouchDie(p)
							If p\Collision\GroundType=COLLISION_WORLD_POLYGON_DEATH_GOTHRU Then
								If p\Action=ACTION_DIE Or p\Action=ACTION_VICTORY Or p\Action=ACTION_VICTORYHOLD Then EntityType(p\Objects\Entity, COLLISION_NONE)
							EndIf
						Case COLLISION_WORLD_POLYGON_HURT:
							If Game\Victory=0 Then Player_Hit(p)
						Case COLLISION_WORLD_POLYGON_RAIL:
							If (Not(p\Action=ACTION_GRIND)) Then
								If (Not(p\CanClimbTimer>0)) and Game\Victory=0 and (Not(p\Action=ACTION_DIE Or p\Action=ACTION_HURT)) and p\Motion\Ground and (p\Flags\CanClimb=False) Then
									If Game\Vehicle=0 Or (p\No#=1 Or pp(1)\Action=ACTION_GRIND) Then
										If p\No#=1 Then EmitSmartSound(Sound_GrindStart,p\Objects\Entity)
										p\Action=ACTION_GRIND
									EndIf
								EndIf
							EndIf
						Case COLLISION_WORLD_POLYGON_PINBALL,COLLISION_WORLD_POLYGON_BOUNCE:
							If (Not(p\Action=ACTION_HURT Or p\Action=ACTION_DIE)) and Game\Victory=0 Then
								If (Not(p\Action=ACTION_BUMPED Or p\Action=ACTION_FREEZE)) Then
									If (Not(p\Collision\GroundType=COLLISION_WORLD_POLYGON_BOUNCE)) Or p\Motion\Ground Then p\Action=ACTION_BUMPED : p\BumpedTimer=0.8*secs#
								EndIf
								If p\Collision\GroundType=COLLISION_WORLD_POLYGON_BOUNCE and p\Motion\Ground Then
									Player_Action_BumpedBounce_Initiate(p)
								EndIf
							EndIf
						Case COLLISION_WORLD_POLYGON_SLIDEXP,COLLISION_WORLD_POLYGON_SLIDEXN,COLLISION_WORLD_POLYGON_SLIDEZP,COLLISION_WORLD_POLYGON_SLIDEZN:
							h#=1+p\SpeedLength#/3.0
							Select p\Collision\GroundType
							Case COLLISION_WORLD_POLYGON_SLIDEXP: MoveEntity p\Objects\Entity, +h#*d\Delta, 0, 0
							Case COLLISION_WORLD_POLYGON_SLIDEXN: MoveEntity p\Objects\Entity, -h#*d\Delta, 0, 0
							Case COLLISION_WORLD_POLYGON_SLIDEZP: MoveEntity p\Objects\Entity, 0, 0, +h#*d\Delta
							Case COLLISION_WORLD_POLYGON_SLIDEZN: MoveEntity p\Objects\Entity, 0, 0, -h#*d\Delta
							End Select
					End Select
					Select p\Collision\GroundType
						Case COLLISION_WORLD_POLYGON_ICE: p\IceFloorTimer=2.5*secs#
						Default: p\IceFloorTimer=0
					End Select
					Select p\Collision\GroundType
						Case COLLISION_WORLD_POLYGON_SLOW: p\SlowFloorTimer=2.5*secs# : If p\SpeedLength#>p\Physics\COMMON_XZTOPSPEED# Then Player_SetSpeed(p,p\Physics\COMMON_XZTOPSPEED#)
						Default: p\SlowFloorTimer=0
					End Select
					Select p\Collision\GroundType
						Case COLLISION_WORLD_POLYGON: p\CanClimbTimer=0.1*secs#
						Case COLLISION_WORLD_POLYGON_BLOCK: p\CanClimbTimer=0
					End Select
					Select p\Collision\GroundType
						Case COLLISION_WORLD_POLYGON_PINBALL,COLLISION_WORLD_POLYGON_BOUNCE,COLLISION_WORLD_POLYGON_RAIL:
							If p\No#=1 Then Game\Vehicle=0
					End Select
					EndIf
			End Select

			; Test for ground collision
			If (p\Collision\DotProduct# > p\Collision\GroundTest# And (p\Motion\Speed\y#<=0.0 Or p\Motion\Ground)) Then
				If p\Collision\ShouldAlign Then
					p\Collision\Align = True
					Vector_Add(p\Collision\GroundNormal, p\Collision\Normal)
				End If
				p\Collision\GroundTest# = p\Collision\DotProduct#
				
			End If

			; Test for ground collision
			If (p\Collision\DotProduct# < p\Collision\CeilingTest# And p\Motion\Speed\y#>0.0) Then
				Vector_Add(p\Collision\CeilingNormal, p\Collision\Normal)
				p\Collision\CeilingTest# = p\Collision\DotProduct#
			End If

			; Test for front collision. 
			If (p\Collision\DotProduct#>=p\Physics\MOTION_WALL_UP# And p\Collision\DotProduct#<=p\Physics\MOTION_WALL_DOWN#) Then
				; Even though the dot product told us there was a collision, it may have been
				; anywhere surrounding the player. Check out the orientation of the collision
				; with a cross product.
				p\Collision\Cross = Null
				
				Vector_CrossProduct(p\Collision\Normal, p\Motion\Align, p\Collision\Normal)
				p\Collision\DotProduct# = 1-Abs(Vector_DotProduct#(p\Collision\Normal, p\Collision\SpeedNormal))

				; Finally, test for front collision
				If (p\Collision\DotProduct#>p\Collision\FrontTest#) Then
					p\Collision\FrontTest# = p\Collision\DotProduct#
				End If
			End If
		Next

		; If there was a collision in the front, calculate how much the speed should drop.
		If (p\Collision\FrontTest#>p\Physics\MOTION_WALL_DIRECTION#) Then
			p\Collision\FrontFactor# = 1-Min#(((p\Collision\FrontTest#-p\Physics\MOTION_WALL_DIRECTION#)/(1-p\Physics\MOTION_WALL_DIRECTION#))*d\Delta*1.2, 1.0)
			p\Motion\Speed\x# = p\Motion\Speed\x#*p\Collision\FrontFactor#
			p\Motion\Speed\z# = p\Motion\Speed\z#*p\Collision\FrontFactor#
		End If

		; Once we know there's ground collision for sure, change alignment.
		If (p\Collision\GroundTest# > p\Physics\MOTION_GROUND#) Then
			If p\Collision\Align Then
				Vector_Normalize(p\Collision\GroundNormal)
				Vector_SetFromVector(p\Motion\Align, p\Collision\GroundNormal)
			Else
				Vector_SetFromVector(p\Motion\Align, Game\Stage\GravityAlignment)
			End If
			p\Collision\Result = True
		; If no ground collision was found, maybe there's ceiling collision
		Else If (p\Collision\CeilingTest# < p\Physics\MOTION_CEILING#) Then
			; If the ceiling slope is low enough, make Sonic land on the
			; ceiling.
			If (p\Collision\CeilingTest# < p\Physics\MOTION_CEILING_STOP#) Then
				p\Motion\Speed\y# = 0
				p\Collision\Result = False
			
			; If not, adjust to new alignment
			Else
				Vector_Normalize(p\Collision\CeilingNormal)
				Vector_SetFromVector(p\Motion\Align, p\Collision\CeilingNormal)
				p\Collision\Result = True
			End If
		Else
			Vector_SetFromVector(p\Motion\Align, Game\Stage\GravityAlignment)
			p\Collision\Result = False
		End If

		Delete p\Collision\Normal: Delete p\Collision\GroundNormal : Delete p\Collision\CeilingNormal : Delete p\Collision\SpeedNormal
		Return p\Collision\Result

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_Motion_Placements(p.tPlayer)
		; Position the mesh
		If Game\Interface\DebugPlacerOn=1 Then
			PositionEntity(Game\Interface\DebugAxesMesh, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1)
			PositionEntity(p\Objects\Mesh, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1)
			PositionEntity(p\Objects\Mesh2, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1)
			PositionEntity(p\Objects\Mesh3, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1)
			PositionEntity(p\Objects\Mesh4, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1)
		Else
			If p\Motion\Ground and (p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT Or p\Action=ACTION_JUMP Or p\Action=ACTION_BUMPED Or p\Action=ACTION_GRABBED Or p\Action=ACTION_LAND) Then
				PositionEntity(p\Objects\Mesh, p\Objects\Position\x#, p\Objects\Position\y#+p\ScaleFactor#, p\Objects\Position\z#, 1)
			Else
				PositionEntity(p\Objects\Mesh, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1)
			EndIf
		EndIf

		; Change direction of the mesh
		If Game\Interface\DebugPlacerOn=0 Then
			RotateEntity(p\Objects\Mesh, 0, p\Animation\Direction#-180, 0)
			AlignToVector(p\Objects\Mesh, p\Animation\Align\x#, p\Animation\Align\y#, p\Animation\Align\z#, 2)
			If p\Action=ACTION_DRIFT Then
				ScaleEntity(p\Objects\Mesh, 1, 1.3, 1.3)
				If abs(p\Physics\DRIFT_ANGLE#)>5 Then p\Physics\DRIFT_ANGLE_ACTUAL#=p\Physics\DRIFT_ANGLE# Else p\Physics\DRIFT_ANGLE_ACTUAL#=0
				TurnEntity(p\Objects\Mesh,0,0,p\Physics\DRIFT_ANGLE_ACTUAL#)
			ElseIf p\Action=ACTION_CHARGE and p\ChargeTimer>0.125*secs# Then
				ScaleEntity(p\Objects\Mesh, 1, 1.375, 1)
				TurnEntity(p\Objects\Mesh,45.25,0,0)
			Else
				ScaleEntity(p\Objects\Mesh, 1, 1, 1)
				If p\Physics\TRICK_ANGLE#>5 Then p\Physics\TRICK_ANGLE_ACTUAL#=p\Physics\TRICK_ANGLE# Else p\Physics\TRICK_ANGLE_ACTUAL#=0
				If abs(p\Physics\LEAN_ANGLE#)>5 Then p\Physics\LEAN_ANGLE_ACTUAL#=p\Physics\LEAN_ANGLE# Else p\Physics\LEAN_ANGLE_ACTUAL#=0
				If Not(p\Action=ACTION_FLOAT Or p\Action=ACTION_GLIDER) Then
					If abs(p\Physics\UP_ANGLE#)>5 Then p\Physics\UP_ANGLE_ACTUAL#=p\Physics\UP_ANGLE# Else p\Physics\UP_ANGLE_ACTUAL#=0
				Else
					p\Physics\UP_ANGLE_ACTUAL#=p\Physics\UP_ANGLE#
				EndIf

				TurnEntity(p\Objects\Mesh,p\Physics\UP_ANGLE_ACTUAL#,0,p\Physics\LEAN_ANGLE_ACTUAL#)
				If p\Physics\TRICK_ANGLE_ACTUAL#>0 Then TurnEntity(p\Objects\Mesh,0,p\Physics\TRICK_ANGLE_ACTUAL#,0)
			EndIf

			If p\HasVehicle>0 Then
				If abs(p\Physics\DRIFT_ANGLE#)>10 Then p\Physics\DRIFT_ANGLE_ACTUAL#=p\Physics\DRIFT_ANGLE# Else p\Physics\DRIFT_ANGLE_ACTUAL#=0
				If Game\Vehicle=9 Then
					TurnEntity(p\Objects\Mesh,0,p\Physics\DRIFT_ANGLE_ACTUAL#,0)
				Else
					TurnEntity(p\Objects\Mesh,0,0,p\Physics\DRIFT_ANGLE_ACTUAL#)
				EndIf
				RotateEntity(p\Objects\Vehicle, EntityPitch(p\Objects\Mesh), EntityYaw(p\Objects\Mesh), EntityRoll(p\Objects\Mesh), 1)
				Select Game\Vehicle
					Case 2:
						PositionEntity(p\Objects\Vehicle, (EntityX(p\Objects\HandR,1)+EntityX(p\Objects\HandL,1))/2.0, EntityY(p\Objects\HandR,1), (EntityZ(p\Objects\HandR,1)+EntityZ(p\Objects\HandL,1))/2.0, 1)
					Default:
						PositionEntity(p\Objects\Vehicle, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh), 1)
				End Select
				Select Game\Vehicle
					Case 1:
						MoveEntity p\Objects\Vehicle, 0, -2.2, 0
					Case 2:
						TurnEntity p\Objects\Vehicle, -60, 0, 0
						MoveEntity p\Objects\Vehicle, 0, 0.25, 0.5
					Case 3:
						MoveEntity p\Objects\Vehicle, 0, 0.2+0.75*p\ScaleFactor#, -0.8+1.2*p\ScaleFactor#
					Case 4,9:
						MoveEntity p\Objects\Vehicle, 0, 0.65+0.75*p\ScaleFactor#, -0.8+1.2*p\ScaleFactor#
					Case 5,8:
						MoveEntity p\Objects\Vehicle, 0, 1.05+0.75*p\ScaleFactor#, -0.8+1.2*p\ScaleFactor#
				End Select
			EndIf
		Else
			Select Game\Interface\DebugMenu
				Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#,DEBUGMENU_ATTRIBUTES_CAMROTATION#,DEBUGMENU_ATTRIBUTES_CAMZOOM#,DEBUGMENU_ATTRIBUTES_CAMSPEED#:
					ShowEntity(p\Objects\Mesh4)
					RotateEntity(p\Objects\Mesh4,TempAttribute\campitch#+90,TempAttribute\camyaw#,0)
				Case DEBUGMENU_ATTRIBUTES_AMOUNT#,DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#,DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#:
					ShowEntity(p\Objects\Mesh4)
					RotateEntity(p\Objects\Mesh4,TempAttribute\amountpitch#+90,TempAttribute\amountyaw#,0)
				Default:
					HideEntity(p\Objects\Mesh4)
					Select p\ObjType
						Case OBJTYPE_CHECK,OBJTYPE_CHECK+1000,OBJTYPE_CHECK+2000,OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTER2,OBJTYPE_TELEPORTER3,OBJTYPE_TELEPORTER4,OBJTYPE_TELEPORTER5,OBJTYPE_TELEPORTER6,OBJTYPE_TELEPORTEREND,OBJTYPE_GARDENPOINT,OBJTYPE_SPRINKLER+2000:
							RotateEntity(p\Objects\Mesh,TempAttribute\pitch#,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh2,TempAttribute\pitch#+90,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh3,TempAttribute\pitch#,TempAttribute\yaw#,0)
						Case OBJTYPE_CANNON:
							RotateEntity(p\Objects\Mesh,TempAttribute\pitch#+35,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh2,TempAttribute\pitch#+35,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh3,TempAttribute\pitch#+35,TempAttribute\yaw#,0)
						Case OBJTYPE_BSPRING,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000,OBJTYPE_BOXWOODEN,OBJTYPE_BOXMETAL,OBJTYPE_BOXIRON,OBJTYPE_BOXCAGE,OBJTYPE_BOXLIGHT,OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT,OBJTYPE_BOXWOODEN+1000,OBJTYPE_BOXMETAL+1000,OBJTYPE_BOXIRON+1000,OBJTYPE_BOXCAGE+1000,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXTNT+1000,OBJTYPE_BOXNITRO+1000,OBJTYPE_BOXFLOAT+1000,OBJTYPE_BOXWOODEN+2000,OBJTYPE_BOXMETAL+2000,OBJTYPE_BOXIRON+2000,OBJTYPE_BOXCAGE+2000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_BOXTNT+2000,OBJTYPE_BOXNITRO+2000,OBJTYPE_BOXFLOAT+2000,OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT,OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHTOP,OBJTYPE_SWITCHWATER,OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_OMOCHAO,OBJTYPE_SPIKEBAR,OBJTYPE_SPIKEBAR+1000,OBJTYPE_SPIKEBAR+2000,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR,OBJTYPE_FPLAT,OBJTYPE_SPIKESWING,OBJTYPE_SPIKESWING+1000,OBJTYPE_SPIKESWING+2000,OBJTYPE_AIRBALLOON,OBJTYPE_CLOUD,OBJTYPE_POLE,OBJTYPE_EXPLOSION,OBJTYPE_EXPLOSION2,OBJTYPE_HANDLE,OBJTYPE_ICEDECOR,OBJTYPE_ICEDECOR+1000,OBJTYPE_CAPSULE:
							RotateEntity(p\Objects\Mesh,0,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh2,0,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh3,0,TempAttribute\yaw#,0)
						Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA,OBJTYPE_BOSSROBO,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3,OBJTYPE_BOMBER1,OBJTYPE_BOMBER2:
							RotateEntity(p\Objects\Mesh,0,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh2,0,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh3,0,TempAttribute\yaw#,0)
						Case OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000:
							RotateEntity(p\Objects\Mesh,TempAttribute\pitch#+90,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh2,TempAttribute\pitch#+90,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh3,TempAttribute\pitch#+90,TempAttribute\yaw#,0)
						Default:
							RotateEntity(p\Objects\Mesh,TempAttribute\pitch#,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh2,TempAttribute\pitch#,TempAttribute\yaw#,0)
							RotateEntity(p\Objects\Mesh3,TempAttribute\pitch#,TempAttribute\yaw#,0)
					End Select
			End Select
		EndIf
	End Function

	Function Player_Motion_PetPlacements(p.tPlayer)
		Select p\Character
			Case CHAR_CRE:
				PositionEntity p\Objects\Follower, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh), 1
				RotateEntity p\Objects\Follower, EntityPitch(p\Objects\Mesh), EntityYaw(p\Objects\Mesh), EntityRoll(p\Objects\Mesh), 1
				MoveEntity p\Objects\Follower, 2, 3, -2
				PositionEntity p\Objects\Cheese, EntityX(p\Objects\Follower), EntityY(p\Objects\Follower), EntityZ(p\Objects\Follower), 1
			Case CHAR_BIG:
				PositionEntity p\Objects\Follower, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh), 1
				RotateEntity p\Objects\Follower, EntityPitch(p\Objects\Mesh), EntityYaw(p\Objects\Mesh), EntityRoll(p\Objects\Mesh), 1
				MoveEntity p\Objects\Follower, -10.0876, -2.9673, -10.2656
				PositionEntity p\Objects\Froggy, EntityX(p\Objects\Follower), EntityY(p\Objects\Follower), EntityZ(p\Objects\Follower), 1
		End Select
		PositionEntity p\Objects\Follower, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh), 1
		RotateEntity p\Objects\Follower, EntityPitch(p\Objects\Mesh), EntityYaw(p\Objects\Mesh), EntityRoll(p\Objects\Mesh), 1
		MoveEntity p\Objects\Follower, 2+(GetCharScaleFactor(p\Character)/2), 3+(GetCharScaleFactor(p\Character)/1.5), -2.5
		PositionEntity p\Objects\Buddy, EntityX(p\Objects\Follower), EntityY(p\Objects\Follower), EntityZ(p\Objects\Follower), 1
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_Align(p.tPlayer)
		RotateEntity(p\Objects\Entity, 0, 0, 0)
		AlignToVector(p\Objects\Entity, p\Motion\Align\x#, p\Motion\Align\y#, p\Motion\Align\z#, 2)
		; dummy fix
		If (p\Motion\Align\y# = 180) Then p\Animation\Direction# = EntityYaw#(p\Objects\Mesh)
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_ConvertAirToGround(p.tPlayer)
		TFormVector(p\Motion\Speed\x#, p\Motion\Speed\y#, p\Motion\Speed\z#, Game\Stage\Gravity, p\Objects\Entity)
		p\Motion\Speed\x# = TFormedX#()
		p\Motion\Speed\y# = 0
		p\Motion\Speed\z# = TFormedZ#()
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_ConvertGroundToAir(p.tPlayer)
		TFormVector(p\Motion\Speed\x#, p\Motion\Speed\y#, p\Motion\Speed\z#, p\Objects\Entity, Game\Stage\Gravity)
		p\Motion\Speed\x# = TFormedX#()
		p\Motion\Speed\y# = TFormedY#()
		p\Motion\Speed\z# = TFormedZ#()
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_ConvertGroundToRoll(p.tPlayer, d.tDeltaTime)
	If p\Motion\Align\x#<>0 Or p\Motion\Align\z#<>0 Then
		TFormVector(p\Motion\Align\x#, p\Motion\Speed\y#, p\Motion\Align\z#, Game\Stage\Gravity, p\Objects\Entity)
		
		; X
		If TFormedX#() < 0.0 Then
			If p\Motion\Speed\x# > 0.0 Then
			p\Motion\Speed\x# = p\Motion\Speed\x# + (TFormedX#()*p\Physics\COMMON_ROLLWEIGHT_UP#*d\Delta)
			End If
			If p\Motion\Speed\x# < 0.0 Then
			p\Motion\Speed\x# = p\Motion\Speed\x# + (TFormedX#()*p\Physics\COMMON_ROLLWEIGHT_DOWN# * p\Physics\ROLL_WEIGHT_MULTIPLIER#*d\Delta)
			End If
		End If
		
		If TFormedX#() > 0.0 Then
			If p\Motion\Speed\x# < 0.0 Then
			p\Motion\Speed\x# = p\Motion\Speed\x# + (TFormedX#()*p\Physics\COMMON_ROLLWEIGHT_UP#*d\Delta)
			End If
			If p\Motion\Speed\x# > 0.0 Then
			p\Motion\Speed\x# = p\Motion\Speed\x# + (TFormedX#()*p\Physics\COMMON_ROLLWEIGHT_DOWN# * p\Physics\ROLL_WEIGHT_MULTIPLIER#*d\Delta)
			End If
		End If
		
		; Z
		If TFormedZ#() < 0.0 Then
			If p\Motion\Speed\z# > 0.0 Then
			p\Motion\Speed\z# = p\Motion\Speed\z# + (TFormedZ#()*p\Physics\COMMON_ROLLWEIGHT_UP#*d\Delta)
			End If
			If p\Motion\Speed\z# < 0.0 Then
			p\Motion\Speed\z# = p\Motion\Speed\z# + (TFormedZ#()*p\Physics\COMMON_ROLLWEIGHT_DOWN# * p\Physics\ROLL_WEIGHT_MULTIPLIER#*d\Delta)
			End If
		End If
		
		If TFormedZ#() > 0.0 Then
			If p\Motion\Speed\z# < 0.0 Then
			p\Motion\Speed\z# = p\Motion\Speed\z# + (TFormedZ#()*p\Physics\COMMON_ROLLWEIGHT_UP#*d\Delta)
			End If
			If p\Motion\Speed\z# > 0.0 Then
			p\Motion\Speed\z# = p\Motion\Speed\z# + (TFormedZ#()*p\Physics\COMMON_ROLLWEIGHT_DOWN# * p\Physics\ROLL_WEIGHT_MULTIPLIER#*d\Delta)
			End If
		End If		

	End If
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_FollowerHolding_ByFeet(p.tPlayer, groundnotforced=false)
		If p\Invisibility=1 Then Return

		holdingpivot=CreatePivot()
		Select p\RealCharacter
			Case CHAR_BIG,CHAR_VEC,CHAR_OME,CHAR_BAR,CHAR_STO,CHAR_CHO,CHAR_HBO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_TMH:
				PositionEntity holdingpivot, EntityX(p\Objects\ToeR,1), EntityY(p\Objects\ToeR,1), EntityZ(p\Objects\ToeR,1), 1
			Case CHAR_CHW:
				PositionEntity holdingpivot, EntityX(p\Objects\ToeR,1), EntityY(p\Objects\ToeR,1)-1.25, EntityZ(p\Objects\ToeR,1), 1
			Default:
				If IsCharMod(p\RealCharacter) Then
					If MODCHARS_BIGHOLD(p\RealCharacter-CHAR_MOD1+1)>0 Then
					PositionEntity holdingpivot, EntityX(p\Objects\ToeR,1), EntityY(p\Objects\ToeR,1), EntityZ(p\Objects\ToeR,1), 1
					Else
					PositionEntity holdingpivot, (EntityX(p\Objects\ToeR,1)+EntityX(p\Objects\ToeL,1))/2.0, (EntityY(p\Objects\ToeR,1)+EntityY(p\Objects\ToeL,1))/2.0, (EntityZ(p\Objects\ToeR,1)+EntityZ(p\Objects\ToeL,1))/2.0, 1
					EndIf
				Else
					PositionEntity holdingpivot, (EntityX(p\Objects\ToeR,1)+EntityX(p\Objects\ToeL,1))/2.0, (EntityY(p\Objects\ToeR,1)+EntityY(p\Objects\ToeL,1))/2.0, (EntityZ(p\Objects\ToeR,1)+EntityZ(p\Objects\ToeL,1))/2.0, 1
				EndIf
		End Select

		If Menu\Members>=2 Then
			If EntityDistance(p\Objects\Entity,pp(2)\Objects\Entity)<50 and ((pp(2)\Motion\Ground=False and pp(2)\RadiusChange<=1) or groundnotforced) Then
				pp(2)\Action=ACTION_HOLD2 : pp(2)\ShouldBeHoldingTimer=0.1*secs# : pp(2)\BumpedCloudTimer=0
				PositionEntity pp(2)\Objects\Entity, EntityX(holdingpivot,1), EntityY(holdingpivot,1)-0.5, EntityZ(holdingpivot,1), 1
				pp(2)\Animation\Direction#=p\Animation\Direction#
				PositionEntity(pp(2)\Objects\Mesh, EntityX(pp(2)\Objects\Entity), EntityY(pp(2)\Objects\Entity), EntityZ(pp(2)\Objects\Entity), 1)
				RotateEntity(pp(2)\Objects\Mesh, 0, p\Animation\Direction#-180, 0)
				TurnEntity(pp(2)\Objects\Mesh, 120*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(2)\FollowerIsHoldingLeaderTimer=0.1*secs#
			EndIf
		EndIf
		If Menu\Members>=3 Then
			holdingpivot2=CreatePivot()
			Select pp(2)\RealCharacter
				Case CHAR_BIG,CHAR_VEC,CHAR_OME,CHAR_BAR,CHAR_STO,CHAR_CHO,CHAR_HBO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_TMH:
					PositionEntity holdingpivot2, EntityX(pp(2)\Objects\ToeR,1), EntityY(pp(2)\Objects\ToeR,1), EntityZ(pp(2)\Objects\ToeR,1), 1
				Case CHAR_CHW:
					PositionEntity holdingpivot2, EntityX(pp(2)\Objects\ToeR,1), EntityY(pp(2)\Objects\ToeR,1)-1.25, EntityZ(pp(2)\Objects\ToeR,1), 1
				Default:
					If IsCharMod(pp(2)\RealCharacter) Then
						If MODCHARS_BIGHOLD(p\RealCharacter-CHAR_MOD1+1)>0 Then
						PositionEntity holdingpivot2, EntityX(pp(2)\Objects\ToeR,1), EntityY(pp(2)\Objects\ToeR,1), EntityZ(pp(2)\Objects\ToeR,1), 1
						Else
						PositionEntity holdingpivot2, (EntityX(pp(2)\Objects\ToeR,1)+EntityX(pp(2)\Objects\ToeL,1))/2.0, (EntityY(pp(2)\Objects\ToeR,1)+EntityY(pp(2)\Objects\ToeL,1))/2.0, (EntityZ(pp(2)\Objects\ToeR,1)+EntityZ(pp(2)\Objects\ToeL,1))/2.0, 1
						EndIf
					Else
						PositionEntity holdingpivot2, (EntityX(pp(2)\Objects\ToeR,1)+EntityX(pp(2)\Objects\ToeL,1))/2.0, (EntityY(pp(2)\Objects\ToeR,1)+EntityY(pp(2)\Objects\ToeL,1))/2.0, (EntityZ(pp(2)\Objects\ToeR,1)+EntityZ(pp(2)\Objects\ToeL,1))/2.0, 1
					EndIf
			End Select
			If EntityDistance(p\Objects\Entity,pp(3)\Objects\Entity)<50 and ((pp(3)\Motion\Ground=False and pp(3)\RadiusChange<=1) or groundnotforced) Then
				pp(3)\Action=ACTION_HOLD2 : pp(3)\ShouldBeHoldingTimer=0.1*secs# : pp(3)\BumpedCloudTimer=0
				If EntityDistance(p\Objects\Entity,pp(2)\Objects\Entity)<50 and ((pp(2)\Motion\Ground=False and pp(2)\RadiusChange<=1) or groundnotforced) Then
					PositionEntity pp(3)\Objects\Entity, EntityX(holdingpivot2,1), EntityY(holdingpivot2,1)-0.5, EntityZ(holdingpivot2,1), 1
				Else
					PositionEntity pp(3)\Objects\Entity, EntityX(holdingpivot,1), EntityY(holdingpivot,1)-0.5, EntityZ(holdingpivot,1), 1
				EndIf
				pp(3)\Animation\Direction#=p\Animation\Direction#
				PositionEntity(pp(3)\Objects\Mesh, EntityX(pp(3)\Objects\Entity), EntityY(pp(3)\Objects\Entity), EntityZ(pp(3)\Objects\Entity), 1)
				RotateEntity(pp(3)\Objects\Mesh, 0, p\Animation\Direction#-180, 0)
				TurnEntity(pp(3)\Objects\Mesh, 120*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(3)\FollowerIsHoldingLeaderTimer=0.1*secs#
			EndIf
		EndIf

		FreeEntity holdingpivot
		If Menu\Members>=3 Then FreeEntity holdingpivot2
	End Function

	Function Player_FollowerHolding_ByTriangleDive(p.tPlayer)
		If p\Invisibility=1 Then Return

		holdingpivot=CreatePivot()
		PositionEntity holdingpivot, (EntityX(p\Objects\HandR,1)+EntityX(p\Objects\HandL,1))/2.0, (EntityY(p\Objects\HandR,1)+EntityY(p\Objects\HandL,1))/2.0, (EntityZ(p\Objects\HandR,1)+EntityZ(p\Objects\HandL,1))/2.0, 1
		RotateEntity holdingpivot, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot, 0, 0, -2

		holdingpivot_left=CreatePivot()
		PositionEntity holdingpivot_left, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 1
		RotateEntity holdingpivot_left, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_left, 0, 0, -2

		holdingpivot_right=CreatePivot()
		PositionEntity holdingpivot_right, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 1
		RotateEntity holdingpivot_right, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_right, 0, 0, -2

		If Menu\Members>=3 Then
			If EntityDistance(p\Objects\Entity,pp(3)\Objects\Entity)<50 and EntityDistance(p\Objects\Entity,pp(2)\Objects\Entity)<50 and (pp(3)\Motion\Ground=False and pp(3)\RadiusChange<=1) and (pp(2)\Motion\Ground=False and pp(2)\RadiusChange<=1) Then
				pp(2)\Action=ACTION_HOLD : pp(2)\ShouldBeHoldingTimer=0.1*secs# : pp(2)\BumpedCloudTimer=0
				PositionEntity pp(2)\Objects\Entity, EntityX(holdingpivot_right,1), EntityY(holdingpivot_right,1), EntityZ(holdingpivot_right,1), 1
				pp(2)\Animation\Direction#=p\Animation\Direction#+180-45
				PositionEntity(pp(2)\Objects\Mesh, EntityX(pp(2)\Objects\Entity), EntityY(pp(2)\Objects\Entity), EntityZ(pp(2)\Objects\Entity), 1)
				RotateEntity(pp(2)\Objects\Mesh, 0, pp(2)\Animation\Direction#-180, 0)
				TurnEntity(pp(2)\Objects\Mesh, 90, 0, 0)
				TurnEntity(pp(2)\Objects\Mesh, 0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(2)\FollowerIsHoldingLeaderTimer=0.1*secs#

				pp(3)\Action=ACTION_HOLD : pp(3)\ShouldBeHoldingTimer=0.1*secs# : pp(3)\BumpedCloudTimer=0
				PositionEntity pp(3)\Objects\Entity, EntityX(holdingpivot_left,1), EntityY(holdingpivot_left,1), EntityZ(holdingpivot_left,1), 1
				pp(3)\Animation\Direction#=p\Animation\Direction#+180+45
				PositionEntity(pp(3)\Objects\Mesh, EntityX(pp(3)\Objects\Entity), EntityY(pp(3)\Objects\Entity), EntityZ(pp(3)\Objects\Entity), 1)
				RotateEntity(pp(3)\Objects\Mesh, 0, pp(3)\Animation\Direction#-180, 0)
				TurnEntity(pp(3)\Objects\Mesh, 90, 0, 0)
				TurnEntity(pp(3)\Objects\Mesh, 0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(3)\FollowerIsHoldingLeaderTimer=0.1*secs#
			ElseIf EntityDistance(p\Objects\Entity,pp(2)\Objects\Entity)<50 and (pp(2)\Motion\Ground=False and pp(2)\RadiusChange<=1) Then
				pp(2)\Action=ACTION_HOLD : pp(2)\ShouldBeHoldingTimer=0.1*secs# : pp(2)\BumpedCloudTimer=0
				PositionEntity pp(2)\Objects\Entity, EntityX(holdingpivot,1), EntityY(holdingpivot,1), EntityZ(holdingpivot,1), 1
				pp(2)\Animation\Direction#=p\Animation\Direction#+180
				PositionEntity(pp(2)\Objects\Mesh, EntityX(pp(2)\Objects\Entity), EntityY(pp(2)\Objects\Entity), EntityZ(pp(2)\Objects\Entity), 1)
				RotateEntity(pp(2)\Objects\Mesh, 0, pp(2)\Animation\Direction#-180, 0)
				TurnEntity(pp(2)\Objects\Mesh, 90, 0, 0)
				TurnEntity(pp(2)\Objects\Mesh, 0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(2)\FollowerIsHoldingLeaderTimer=0.1*secs#
			ElseIf EntityDistance(p\Objects\Entity,pp(3)\Objects\Entity)<50 and (pp(3)\Motion\Ground=False and pp(3)\RadiusChange<=1) Then
				pp(3)\Action=ACTION_HOLD : pp(3)\ShouldBeHoldingTimer=0.1*secs# : pp(3)\BumpedCloudTimer=0
				PositionEntity pp(3)\Objects\Entity, EntityX(holdingpivot,1), EntityY(holdingpivot,1), EntityZ(holdingpivot,1), 1
				pp(3)\Animation\Direction#=p\Animation\Direction#+180
				PositionEntity(pp(3)\Objects\Mesh, EntityX(pp(3)\Objects\Entity), EntityY(pp(3)\Objects\Entity), EntityZ(pp(3)\Objects\Entity), 1)
				RotateEntity(pp(3)\Objects\Mesh, 0, pp(3)\Animation\Direction#-180, 0)
				TurnEntity(pp(3)\Objects\Mesh, 90, 0, 0)
				TurnEntity(pp(3)\Objects\Mesh, 0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(3)\FollowerIsHoldingLeaderTimer=0.1*secs#
			EndIf
		ElseIf Menu\Members>=2 Then
			If EntityDistance(p\Objects\Entity,pp(2)\Objects\Entity)<50 And (pp(2)\Motion\Ground=False And pp(2)\RadiusChange<=1) Then
				pp(2)\Action=ACTION_HOLD : pp(2)\ShouldBeHoldingTimer=0.1*secs# : pp(2)\BumpedCloudTimer=0
				PositionEntity pp(2)\Objects\Entity, EntityX(holdingpivot,1), EntityY(holdingpivot,1), EntityZ(holdingpivot,1), 1
				pp(2)\Animation\Direction#=p\Animation\Direction#+180
				PositionEntity(pp(2)\Objects\Mesh, EntityX(pp(2)\Objects\Entity), EntityY(pp(2)\Objects\Entity), EntityZ(pp(2)\Objects\Entity), 1)
				RotateEntity(pp(2)\Objects\Mesh, 0, pp(2)\Animation\Direction#-180, 0)
				TurnEntity(pp(2)\Objects\Mesh, 90, 0, 0)
				TurnEntity(pp(2)\Objects\Mesh, 0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				pp(2)\FollowerIsHoldingLeaderTimer=0.1*secs#
			EndIf
		EndIf

		FreeEntity holdingpivot
		FreeEntity holdingpivot_left
		FreeEntity holdingpivot_right
	End Function

	Function Player_FollowerHolding_ByLatchOn(p.tPlayer)
		If p\Invisibility=1 Then Return

		Select p\Character
			Case CHAR_VEC:
				holdingpivot_left=CreatePivot()
				PositionEntity holdingpivot_left, EntityX(p\Objects\ArmL,1), EntityY(p\Objects\ArmL,1), EntityZ(p\Objects\ArmL,1), 1
				RotateEntity holdingpivot_left, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_left, 0, 0, 2

				holdingpivot_right=CreatePivot()
				PositionEntity holdingpivot_right, EntityX(p\Objects\ToeR,1), EntityY(p\Objects\ToeR,1), EntityZ(p\Objects\ToeR,1), 1
				RotateEntity holdingpivot_right, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_right, 0, 0, 2
			Case CHAR_STO:
				holdingpivot_left=CreatePivot()
				PositionEntity holdingpivot_left, EntityX(p\Objects\ToeL,1), EntityY(p\Objects\ToeL,1), EntityZ(p\Objects\ToeL,1), 1
				RotateEntity holdingpivot_left, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_left, -3, 0, 0

				holdingpivot_right=CreatePivot()
				PositionEntity holdingpivot_right, EntityX(p\Objects\ToeR,1), EntityY(p\Objects\ToeR,1), EntityZ(p\Objects\ToeR,1), 1
				RotateEntity holdingpivot_right, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_right, 3, 0, 0
			Default:
				holdingpivot_left=CreatePivot()
				PositionEntity holdingpivot_left, EntityX(p\Objects\ArmL,1), EntityY(p\Objects\ArmL,1), EntityZ(p\Objects\ArmL,1), 1
				RotateEntity holdingpivot_left, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_left, 0, 0, 2

				holdingpivot_right=CreatePivot()
				PositionEntity holdingpivot_right, EntityX(p\Objects\ArmR,1), EntityY(p\Objects\ArmR,1), EntityZ(p\Objects\ArmR,1), 1
				RotateEntity holdingpivot_right, 0, p\Animation\Direction#, 0, 1 : MoveEntity holdingpivot_right, 0, 0, 2
		End Select

		If Menu\Members>=2 Then
			If EntityDistance(p\Objects\Entity,pp(2)\Objects\Entity)<50 And (pp(2)\Motion\Ground=False And pp(2)\RadiusChange<=1) Then
				pp(2)\Action=ACTION_HOLD : pp(2)\ShouldBeHoldingTimer=0.1*secs# : pp(2)\BumpedCloudTimer=0
				PositionEntity pp(2)\Objects\Entity, EntityX(holdingpivot_left,1), EntityY(holdingpivot_left,1), EntityZ(holdingpivot_left,1), 1
				pp(2)\Animation\Direction#=p\Animation\Direction#
				PositionEntity(pp(2)\Objects\Mesh, EntityX(pp(2)\Objects\Entity), EntityY(pp(2)\Objects\Entity), EntityZ(pp(2)\Objects\Entity), 1)
				RotateEntity(pp(2)\Objects\Mesh, 0, pp(2)\Animation\Direction#-180, 0)
				Select p\Character
					Case CHAR_BAR:
						TurnEntity(pp(2)\Objects\Mesh, 80, 0, 0)
						TurnEntity(pp(2)\Objects\Mesh, 20*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
					Case CHAR_STO:
						TurnEntity(pp(2)\Objects\Mesh, 10, 90, 0)
						TurnEntity(pp(2)\Objects\Mesh, 60*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
					Default:
						TurnEntity(pp(2)\Objects\Mesh, 30, 0, 0)
						TurnEntity(pp(2)\Objects\Mesh, 80*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				End Select
				pp(2)\FollowerIsHoldingLeaderTimer=0.1*secs#
			EndIf
		EndIf
		If Menu\Members>=3 Then
			If EntityDistance(p\Objects\Entity,pp(3)\Objects\Entity)<50 And (pp(3)\Motion\Ground=False And pp(3)\RadiusChange<=1) Then
				pp(3)\Action=ACTION_HOLD : pp(3)\ShouldBeHoldingTimer=0.1*secs# : pp(3)\BumpedCloudTimer=0
				PositionEntity pp(3)\Objects\Entity, EntityX(holdingpivot_right,1), EntityY(holdingpivot_right,1), EntityZ(holdingpivot_right,1), 1
				pp(3)\Animation\Direction#=p\Animation\Direction#
				PositionEntity(pp(3)\Objects\Mesh, EntityX(pp(3)\Objects\Entity), EntityY(pp(3)\Objects\Entity), EntityZ(pp(3)\Objects\Entity), 1)
				RotateEntity(pp(3)\Objects\Mesh, 0, pp(3)\Animation\Direction#-180, 0)
				Select p\Character
					Case CHAR_BAR:
						TurnEntity(pp(3)\Objects\Mesh, 80, 0, 0)
						TurnEntity(pp(3)\Objects\Mesh, 20*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
					Case CHAR_STO:
						TurnEntity(pp(3)\Objects\Mesh, 10, -90, 0)
						TurnEntity(pp(3)\Objects\Mesh, 60*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
					Default:
						TurnEntity(pp(3)\Objects\Mesh, 30, 0, 0)
						TurnEntity(pp(3)\Objects\Mesh, 80*pp(1)\SpeedLength#/6.0, 0, pp(1)\Physics\LEAN_ANGLE_ACTUAL#)
				End Select
				pp(3)\FollowerIsHoldingLeaderTimer=0.1*secs#
			EndIf
		EndIf

		FreeEntity holdingpivot_left
		FreeEntity holdingpivot_right
	End Function

	Function Player_FollowerHolding_EveryoneJumpDashes_Real(p.tPlayer)
		If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<50 and (p\Motion\Ground=False) Then
			Player_Action_JumpDash_Initiate_Generic(p)
		EndIf
	End Function
	Function Player_FollowerHolding_EveryoneJumpDashes(p.tPlayer)
		If p\Invisibility=1 Then Return
		If Menu\Members>=2 Then Player_FollowerHolding_EveryoneJumpDashes_Real(pp(2))
		If Menu\Members>=3 Then Player_FollowerHolding_EveryoneJumpDashes_Real(pp(3))
	End Function

	Function Player_FollowerHolding_EveryoneDoubleJumps_Real(p.tPlayer)
		If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<50 and (p\Motion\Ground=False) Then
			Player_Action_DoubleJump_Initiate_Generic(p)
		EndIf
	End Function
	Function Player_FollowerHolding_EveryoneDoubleJumps(p.tPlayer)
		If p\Invisibility=1 Then Return
		If Menu\Members>=2 Then Player_FollowerHolding_EveryoneDoubleJumps_Real(pp(2))
		If Menu\Members>=3 Then Player_FollowerHolding_EveryoneDoubleJumps_Real(pp(3))
	End Function