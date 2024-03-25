
;============================================================================================
;============================================================================================
;============================================================================================

Type tTrail
	Field id,num,brush,surface,Mesh
	Field tdx#[maxVerts], tdy#[maxVerts], tdz#[maxVerts]
	Field update%,alpha#
	Field point1, point2
	Field char
End Type
Type tLongTrail
	Field id,num,brush,surface,Mesh
	Field tdx#[maxVerts], tdy#[maxVerts], tdz#[maxVerts]
	Field update%,alpha#
	Field point1, point2
	Field char
End Type

Const trail_update#=0.3
Const maxVerts=100
Const longtrail_update#=1.0

;============================================================================================
;============================================================================================

Function Player_UpdateTrails()

	Local x#,y#,z#

	;loop through all trails
	For t.tTrail = Each tTrail

		; Move the trail pieces along.
		t\update=t\update+1 : If t\update=trail_update# Then t\update=0

		;update				
		If t\update=0
			For i=2 To CountVertices(t\surface)-1
				t\tdx[i] = (VertexX(t\surface,i-2) - VertexX(t\surface,i))/trail_update#
				t\tdy[i] = (VertexY(t\surface,i-2) - VertexY(t\surface,i))/trail_update#
				t\tdz[i] = (VertexZ(t\surface,i-2) - VertexZ(t\surface,i))/trail_update#
				t\alpha=1.0-Float(i)/30.0
				If t\alpha<0 Then t\alpha=0
				VertexColor t\surface,i,0,0,255,t\alpha
			Next
		End If

		For i=2 To CountVertices(t\surface)-1
			VertexCoords(t\surface,i,VertexX(t\surface,i)+t\tdx[i],VertexY(t\surface,i)+t\tdy[i],VertexZ(t\surface,i)+t\tdz[i])
		Next
	
		;position the first two verts at the back of the ship
		VertexCoords(t\surface,0,EntityX(t\point1,1),EntityY(t\point1,1),EntityZ(t\point1,1))
		VertexCoords(t\surface,1,EntityX(t\point2,1),EntityY(t\point2,1),EntityZ(t\point2,1))
	
	Next
			
End Function

Function Player_CreateTrail(p.tPlayer,point1,point2,polys=20,startalpha#=1.0,num#=1)

	Local x#,y#,z#

	t.tTrail=New tTrail
	t\char = p\RealCharacter

	;create mesh and set properties
	t\Mesh = CreateMesh()
	t\id = 1
	t\brush = CreateBrush()
	BrushBlend t\brush,3
	BrushFX t\brush,2+16
	t\surface = CreateSurface(t\mesh,t\brush)
	t\alpha=1.0

	;check there are two trail strat points
	If point1=False Then RuntimeError "must specify 'point1' for one side of the trail" Else t\point1=point1
	If point2=False Then RuntimeError "must specify 'point2' for one side of the trail" Else t\point2=point2

	;mid pount between two trial objects
	x=(EntityX(t\point1,1)+EntityX(t\point2,1))/2.0
	y=(EntityY(t\point1,1)+EntityY(t\point2,1))/2.0
	z=(EntityZ(t\point1,1)+EntityZ(t\point2,1))/2.0

	;create polygons
	For i=0 To polys
		AddVertex t\surface,x,y,z,Float(i)/Float(polys),1,0
		AddVertex t\surface,x,y,z,Float(i)/Float(polys),0,0
		VertexNormal t\surface,i,0,-1,0
		VertexNormal t\surface,i+1,0,-1,0
		
		If i>0
			AddTriangle t\surface,i*2,i*2-1,i*2-2
			AddTriangle t\surface,i*2,i*2+1,i*2-1
		End If
	Next
	
End Function

Function Player_FreeTrails(p.tPlayer,id)

	For t.tTrail=Each tTrail
		If t\char=p\RealCharacter And t\id=id Then
			FreeEntity t\mesh
			Delete t
		EndIf
	Next
	
	Return True
	
End Function

;==================================================================================================================================

Function Player_UpdateLongTrail()
	
	Local x#,y#,z#

	;loop through all trails
	For trail.tLongTrail = Each tLongTrail 

		; Move the trail pieces along.
		trail\update=trail\update+1 : If trail\update=longtrail_update# Then trail\update=0

		;update				
		If trail\update=0
			For i=2 To CountVertices(trail\surface)-1
				trail\tdx[i] = (VertexX(trail\surface,i-2) - VertexX(trail\surface,i))/longtrail_update#
				trail\tdy[i] = (VertexY(trail\surface,i-2) - VertexY(trail\surface,i))/longtrail_update#
				trail\tdz[i] = (VertexZ(trail\surface,i-2) - VertexZ(trail\surface,i))/longtrail_update#
				trail\alpha=1.0-Float(i)/30.0
				If trail\alpha<0 Then trail\alpha=0
				VertexColor(trail\surface,i,Interface_Circle_R[InterfaceChar(trail\char)], Interface_Circle_G[InterfaceChar(trail\char)], Interface_Circle_B[InterfaceChar(trail\char)],trail\alpha)
			Next
		End If

		For i=2 To CountVertices(trail\surface)-1
			VertexCoords(trail\surface,i,VertexX(trail\surface,i)+trail\tdx[i],VertexY(trail\surface,i)+trail\tdy[i],VertexZ(trail\surface,i)+trail\tdz[i])
		Next
	
		;position the first two verts at the back of the ship
		VertexCoords(trail\surface,0,EntityX(trail\point1,1),EntityY(trail\point1,1),EntityZ(trail\point1,1))
		VertexCoords(trail\surface,1,EntityX(trail\point2,1),EntityY(trail\point2,1),EntityZ(trail\point2,1))
	
	Next
			
End Function


Function Player_CreateLongTrail(p.tPlayer,point1,point2,polys=20,startalpha#=1.0,num#=1, something=0)

	Local x#,y#,z#

	trail.tLongTrail=New tLongTrail
	trail\char = p\RealCharacter

	;create mesh and set properties
	trail\Mesh = CreateMesh()
	trail\id = 2
	trail\brush = CreateBrush()
	BrushBlend trail\brush,3
	BrushFX trail\brush,2+16
	trail\surface = CreateSurface(trail\mesh,trail\brush)
	trail\alpha=1.0

	EntityTexture(trail\mesh,Texture_Trail)
	EntityFX(trail\mesh,1)
	
	;check there are two trail strat points
	If point1=False Then RuntimeError "must specify 'point1' for one side of the trail" Else trail\point1=point1
	If point2=False Then RuntimeError "must specify 'point2' for one side of the trail" Else trail\point2=point2
	
	;mid pount between two trial objects
	x=(EntityX(trail\point1,1)+EntityX(trail\point2,1))/2.0
	y=(EntityY(trail\point1,1)+EntityY(trail\point2,1))/2.0
	z=(EntityZ(trail\point1,1)+EntityZ(trail\point2,1))/2.0

	;create polygons
	For i=0 To polys
		AddVertex trail\surface,x,y,z,Float(i)/Float(polys),1,0
		AddVertex trail\surface,x,y,z,Float(i)/Float(polys),0,0
		VertexNormal trail\surface,i,0,-1,0
		VertexNormal trail\surface,i+1,0,-1,0
		
		If i>0
			AddTriangle trail\surface,i*2,i*2-1,i*2-2
			AddTriangle trail\surface,i*2,i*2+1,i*2-1
		End If
	Next
	
End Function

Function Player_FreeLongTrails(p.tPlayer,id)

	For trail.tLongTrail=Each tLongTrail
		If trail\char=p\RealCharacter And trail\id=id Then
			FreeEntity trail\mesh
			Delete trail
		EndIf
	Next
	
	Return True
	
End Function


Function Player_SetLongTrail(p.tPlayer)

	PositionEntity(p\Objects\TrailPivot, EntityX#(p\Objects\Mesh),EntityY#(p\Objects\Mesh),EntityZ#(p\Objects\Mesh),True)
	RotateEntity(p\Objects\TrailPivot,EntityPitch(p\Objects\Entity),p\Animation\Direction#,EntityRoll(p\Objects\Entity))
	PointEntity(p\Objects\TrailPivot, p\Objects\Entity)
	ScaleEntity(p\Objects\TrailPivot,1+p\ScaleFactor#,1+p\ScaleFactor#,1+p\ScaleFactor#)

End Function

;===============================================================================================================================
;===============================================================================================================================

Function Player_SpawnTrail(p.tPlayer,i)

	e1=CreatePivot(p\Objects\PPivot[i])
	e2=CreatePivot(p\Objects\PPivot[i])
	e3=CreatePivot(p\Objects\PPivot[i])
	e4=CreatePivot(p\Objects\PPivot[i])

	Player_CreateTrail(p,e1,e2,20,1.0,1)
	Player_CreateTrail(p,e2,e3,20,1.0,1)
	Player_CreateTrail(p,e3,e4,20,1.0,1)
	Player_CreateTrail(p,e4,e1,20,1.0,1)

	EntityParent(e1,p\Objects\PPivot[i],0)
	EntityParent(e2,p\Objects\PPivot[i],0)
	EntityParent(e3,p\Objects\PPivot[i],0)
	EntityParent(e4,p\Objects\PPivot[i],0)

	MoveEntity e1,+0.01,+0.01,0
	MoveEntity e2,+0.01,-0.01,0
	MoveEntity e3,-0.01,-0.01,0
	MoveEntity e4,-0.01,+0.01,0

End Function


Function Player_LongTrail(p.tPlayer, Tp=1)

	e1=CreatePivot(p\Objects\TrailPivot)
	e2=CreatePivot(p\Objects\TrailPivot)
	e3=CreatePivot(p\Objects\TrailPivot)
	e4=CreatePivot(p\Objects\TrailPivot)
	e5=CreatePivot(p\Objects\TrailPivot)
	e6=CreatePivot(p\Objects\TrailPivot)
	e7=CreatePivot(p\Objects\TrailPivot)
	e8=CreatePivot(p\Objects\TrailPivot)
	e9=CreatePivot(p\Objects\TrailPivot)
	e10=CreatePivot(p\Objects\TrailPivot)
	e11=CreatePivot(p\Objects\TrailPivot)
	e12=CreatePivot(p\Objects\TrailPivot)
	e13=CreatePivot(p\Objects\TrailPivot)
	e14=CreatePivot(p\Objects\TrailPivot)
	e15=CreatePivot(p\Objects\TrailPivot)
	e16=CreatePivot(p\Objects\TrailPivot)
	e17=CreatePivot(p\Objects\TrailPivot)
	e18=CreatePivot(p\Objects\TrailPivot)
	e19=CreatePivot(p\Objects\TrailPivot)
	e20=CreatePivot(p\Objects\TrailPivot)

	Player_CreateLongTrail(p, e1,e2,20,1.0,1)
	Player_CreateLongTrail(p, e2,e3,20,1.0,1)
	Player_CreateLongTrail(p, e3,e4,20,1.0,1)
	Player_CreateLongTrail(p, e4,e5,20,1.0,1)
	Player_CreateLongTrail(p, e5,e6,20,1.0,1)
	Player_CreateLongTrail(p, e6,e7,20,1.0,1)
	Player_CreateLongTrail(p, e7,e8,20,1.0,1)
	Player_CreateLongTrail(p, e8,e9,20,1.0,1)
	Player_CreateLongTrail(p, e9,e10,20,1.0,1)
	Player_CreateLongTrail(p, e10,e11,20,1.0,1)
	Player_CreateLongTrail(p, e11,e12,20,1.0,1)
	Player_CreateLongTrail(p, e12,e13,20,1.0,1)
	Player_CreateLongTrail(p, e13,e14,20,1.0,1)
	Player_CreateLongTrail(p, e14,e15,20,1.0,1)
	Player_CreateLongTrail(p, e15,e16,20,1.0,1)
	Player_CreateLongTrail(p, e16,e17,20,1.0,1)
	Player_CreateLongTrail(p, e17,e18,20,1.0,1)
	Player_CreateLongTrail(p, e18,e19,20,1.0,1)
	Player_CreateLongTrail(p, e19,e20,20,1.0,1)
	Player_CreateLongTrail(p, e20,e1,20,1.0,1)

	MoveEntity e1,-3,0,0
	MoveEntity e2,-2.9,0.9,0
	MoveEntity e3,-2.4,1.8,0
	MoveEntity e4,-1.8,2.4,0
	MoveEntity e5,-0.9,2.9,0
	MoveEntity e6,0,3,0
	MoveEntity e7,0.9,2.9,0
	MoveEntity e8,1.8,2.4,0
	MoveEntity e9,2.4,1.8,0
	MoveEntity e10,2.9,0.9,0
	MoveEntity e11,3,0,0
	MoveEntity e12,2.9,-0.9,0
	MoveEntity e13,2.4,-1.8,0
	MoveEntity e14,1.8,-2.4,0
	MoveEntity e15,0.9,-2.9,0
	MoveEntity e16,0,-3,0
	MoveEntity e17,-0.9,-2.9,0
	MoveEntity e18,-1.8,-2.4,0
	MoveEntity e19,-2.4,-1.8,0
	MoveEntity e20,-2.9,-0.9,0

End Function

;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================


Type tRazer
Field Alpha#
Field Timer
Field Pivot
Field Mesh
Field Threshold
Field MoveTypeX
Field MoveTypeY
Field MoveTypeZ
Field Mode
Field Owner.tPlayer
Field BelongsToPlayer
End Type

Function Player_CreateRazer.tRazer(p.tPlayer,entity,alphastart#,mode,scalex#=1,scaley#=1,scalez#=1,timer#=0,belongstoplayer=True)
	Select mode
		Case 4,5,6,7:
		Case 8:
			If p\RazerSpawningTimer2>0 Then Return Else p\RazerSpawningTimer2=0.025*secs#
		Default:
			If p\RazerSpawningTimer>0 Then Return Else p\RazerSpawningTimer=0.025*secs#
	End Select

	rz.tRazer = New tRazer
	rz\Threshold = entity
	x# = EntityX(rz\Threshold)
	y# = EntityY(rz\Threshold)
	z# = EntityZ(rz\Threshold)
	rz\Owner = p
	rz\BelongsToPlayer = belongstoplayer
	rz\Pivot = CreatePivot(Game\Stage\Root)
	PositionEntity(rz\Pivot, x#, y#, z#)
	RotateEntity(rz\Pivot, EntityPitch(entity), EntityYaw(entity), EntityRoll(entity))
	Select mode
		Case 1: rz\Mesh = CopyEntity(MESHES(Mesh_Razer), rz\Pivot) : EntityBlend(rz\Mesh, 3)
		Case 2:	rz\Mesh = CopyEntity(MESHES(Mesh_BlackShield1), rz\Pivot)
		Case 3:	rz\Mesh = CopyEntity(MESHES(Mesh_BlackShield2), rz\Pivot)
		Case 4: rz\Mesh = CopyEntity(MESHES(Mesh_Ice), rz\Pivot) : EntityBlend(rz\Mesh, 1)
				ScaleEntity(rz\Mesh,scalex#,scaley#,scalez#) : EntityType(rz\Mesh,COLLISION_WORLD_POLYGON_ICE)
		Case 5,6,7: rz\Mesh = CopyEntity(MESHES(Mesh_Cube), rz\Pivot) : Animate(rz\Mesh,1,0.5,1,10)
				Select mode
				Case 6: MoveEntity rz\Pivot, Rand(-2,2)/2.0, Rand(-2,2)/2.0, Rand(-2,2)/2.0
				Default: MoveEntity rz\Pivot, Rand(-5,5)/2.0, 1+Rand(-5,5)/2.0, Rand(-5,5)/2.0
				End Select
		Case 8: rz\Mesh = CopyEntity(MESHES(Mesh_SuperShield), rz\Pivot) : EntityBlend(rz\Mesh, 3)
				If Game\SuperForm=2 Then
					EntityColorEmerald(rz\Mesh,Rand(1,7),0.5)
				Else
					If Player_SuperAuraShouldFire(p\Character) Then
						EntityColor(rz\Mesh,250,110,110)
					Else
						EntityColor(rz\Mesh,249,228,51)
					EndIf
				EndIf
				ScaleEntity rz\Mesh, 1+p\ScaleFactor#, 1+p\ScaleFactor#, 1+p\ScaleFactor#
	End Select
	rz\Mode = mode
	rz\Alpha# = alphastart#
	EntityAlpha(rz\Mesh, rz\Alpha#)
	rz\MoveTypeX=Rand(-1,1)
	rz\MoveTypeY=Rand(-1,1)
	rz\MoveTypeZ=Rand(-1,1)
	rz\Timer=timer#
	Return rz

End Function

Function Player_SuperAuraShouldFire(char)
	Select char
		Case CHAR_BLA,CHAR_KNU,CHAR_OME,CHAR_GAM,CHAR_MKN,CHAR_EGG,CHAR_EGR,CHAR_INF:
			Return True
		Default:
			Return False
	End Select
End Function

Function Player_UpdateRazer(rz.tRazer, d.tDeltaTime)

	If rz\Timer>0 Then rz\Timer=rz\Timer-timervalue#

	Select rz\Mode
		Case 1,8:
			rz\Alpha# = rz\Alpha# - 0.0825*d\Delta
			EntityAlpha(rz\Mesh, rz\Alpha#)
			MoveEntity rz\Pivot, (0.15+0.1*rz\Owner\SpeedLength#)*d\Delta*rz\MoveTypeX, (0.15+0.1*rz\Owner\SpeedLength#)*d\Delta*rz\MoveTypeY, (0.15+0.1*rz\Owner\SpeedLength#)*d\Delta*rz\MoveTypeZ
		Case 2,3:
			PositionEntity(rz\Pivot,EntityX(rz\Threshold,1),EntityY(rz\Threshold,1),EntityZ(rz\Threshold,1),1)
		Case 4:
			PositionEntity(rz\Pivot,EntityX(rz\Threshold,1),EntityY(rz\Threshold,1)-2.2,EntityZ(rz\Threshold,1),1)
			RotateEntity(rz\Pivot,0,EntityYaw(rz\Threshold,1),0,1)
			If rz\BelongsToPlayer Then
				If rz\Owner\WasGrabbedTimer>0 Then rz\Owner\Action=ACTION_FREEZE
			EndIf
		Case 5,6,7:
			rz\Alpha# = rz\Alpha# - 0.022*d\Delta
			EntityAlpha(rz\Mesh, rz\Alpha#)
			Select rz\Mode
				Case 5,7:
					MoveEntity rz\Pivot, 0, rz\Owner\Motion\Speed\y#*d\Delta, rz\Owner\SpeedLength#*d\Delta
			End Select
			Select rz\Mode
				Case 7: MoveEntity rz\Pivot, 0.42*d\Delta*rz\MoveTypeX, 0.42*d\Delta*rz\MoveTypeY, 0.42*d\Delta*rz\MoveTypeZ
				Default: MoveEntity rz\Pivot, 0.02*d\Delta*rz\MoveTypeX, 0.02*d\Delta*rz\MoveTypeY, 0.02*d\Delta*rz\MoveTypeZ
			End Select
	End Select

	Select rz\Mode
		Case 1,8: TurnEntity(rz\Mesh, 0, 50*d\Delta, 0)
		Case 2,3: TurnEntity(rz\Mesh, 0, 2*d\Delta, 0)
	End Select

	i=False
	Select rz\Mode
		Case 1,5,6,7,8: If rz\Alpha# < 0 Then i=True
		Case 2,3: If rz\Mode<>1 And (Not(rz\Owner\Action=ACTION_SWIPE)) Then i=True
		Case 4: If (Not(rz\Timer>0)) Then i=True
	End Select

	If i=True Then 
		FreeEntity(rz\Mesh)
		FreeEntity(rz\Pivot)
		rz\Alpha# = 0.0
		Delete rz
	EndIf

End Function

Function Player_RubyCubes(p.tPlayer,alpha#=0.65,amount=5,mode=5)
	If p\RubyCubesTimer>0 Then
		p\RubyCubesTimer=p\RubyCubesTimer-timervalue#
	Else
		p\RubyCubesTimer=0.1*secs#
		For i=1 To amount
			Player_CreateRazer.tRazer(p,p\Objects\Mesh,alpha#,mode)
		Next
	EndIf
End Function

Function Bomb_RubyCubes(p.tPlayer,b.tBomb)
	If b\RubyCubesTimer>0 Then
		b\RubyCubesTimer=b\RubyCubesTimer-timervalue#
	Else
		b\RubyCubesTimer=0.025*secs#
		For i=1 To 5
			Player_CreateRazer.tRazer(p,b\Pivot,0.8,6)
		Next
	EndIf
End Function

;============================================================================================
;============================================================================================
Type tAfterImage
    Field Mesh
    Field Entity
    Field Timer,Alpha#,Scale#
    Field AlphaAllowed,ScaleAllowed
End Type

Function Create_AfterImage.tAfterImage(mesh,entity,timer#=500,r,g,b,blended=0,flipentity=0,scale#=1,animation=0,fixrotate=False, canscale=True, texture=0)

af.tAfterImage = New tAfterImage

;t\AlphaAllowed=alphaallowed
af\ScaleAllowed=canscale

af\Mesh=CopyEntity(mesh);CopyEntity(mesh)
EntityFX af\Mesh,1
Animate(af\Mesh,1,1,animation,1)
If texture > 0 Then EntityTexture(af\Mesh, texture)
If blended=1 Then EntityBlend(af\Mesh, 3)
EntityShininess(af\Mesh,0)
If flipentity=1 Then FlipMesh(af\Mesh)
af\Timer=MilliSecs()+timer#
af\Scale#=scale# : ScaleEntity af\Mesh,af\Scale,af\Scale,af\Scale
af\Alpha#=0.7 : EntityAlpha af\Mesh,af\Alpha#
PositionEntity af\Mesh,EntityX(entity),EntityY(entity),EntityZ(entity)
If fixrotate=False Then
RotateEntity af\Mesh,0,EntityYaw(entity)-180,0
Else
RotateEntity af\Mesh,0,EntityYaw(entity),0
EndIf
EntityColor(af\Mesh,r,g,b)
Return af

End Function

Function Update_AfterImage(af.tAfterImage,p.tPlayer,d.tDeltaTime)

RotateEntity af\Mesh,0,EntityYaw(af\Mesh),0
;MoveEntity t\Mesh,0,0,1

;If af\AlphaAllowed=True
af\Alpha#=af\Alpha#-0.075*d\Delta
EntityAlpha af\Mesh,af\Alpha#
;EndIf

If af\ScaleAllowed Then
af\Scale#=af\Scale#-0.075*d\Delta
ScaleEntity af\Mesh,af\Scale,af\Scale,af\Scale
EndIf

If af\Alpha#<0 Then af\Alpha#=0
If af\Scale#<0 Then af\Scale#=0

If af\Scale#=0 Or af\Alpha#=0 Then 
FreeEntity af\Mesh
Delete af
Return
EndIf

End Function
; =========================================================================================================
; =========================================================================================================

Type tSpark
    Field Entity
    Field Pivot
    Field XR#,YR#,ZR#
    Field alpha#,scale#
    Field Mode
    Field Parent 
    Field Timer
    Field No#
    Field Attached 
    Field Height#
End Type

Function Create_Spark.tSpark(p.tPlayer, entity, mesh, camera, yaw#, mode#=0, no#=0, isattached%=False,height#=-1, lock=True)

	sp.tSpark = New tSpark

	;create mesh and set properties
	sp\Pivot = CreatePivot()
	sp\Entity = CopyMesh(mesh)
	sp\alpha#=1.0 : sp\scale#=1.0
	EntityFX sp\Entity ,1+16	
	EntityBlend sp\Entity, 3
	
	sp\Attached = isattached%
	sp\Height# = height#

	PositionEntity sp\Entity,EntityX(entity, lock),EntityY(entity, lock)+height#,EntityZ(entity, lock), lock
	PositionEntity sp\Pivot,EntityX(entity),EntityY(entity)+height#,EntityZ(entity)
	
	PointEntity sp\Pivot,camera

	sp\Mode = mode#

	sp\Parent = entity

	Select sp\Mode#
		Case 0:
			RotateEntity sp\Entity, 0, Yaw#+90, EntityRoll#(entity)
			sp\XR# = Rnd(0,180)
			sp\YR# = Rnd(-30,30)
			sp\ZR# = Rnd(0,30)
		Case 1:
			RotateEntity sp\Entity, 0, Yaw#+90, EntityRoll#(p\Objects\Mesh)
			sp\XR# = 0
			sp\YR# = Rnd(-15,15)
			sp\ZR# = Rnd(0,15)
		Case 2:
			RotateEntity sp\Entity, 0, Yaw#+90, EntityRoll#(entity)
			sp\XR# = Rnd(0,180)
			sp\YR# = Rnd(-30,30)
			sp\ZR# = Rnd(0,30)
	End Select

	sp\timer = MilliSecs()+220

	TurnEntity sp\Entity, sp\XR#,sp\YR#,sp\ZR#

	sp\No#=no#

	Return sp

End Function

Function Update_Spark(sp.tSpark, p.tPlayer, d.tDeltaTime)
	
	If sp\Attached = False Then
		If sp\Timer > MilliSecs() Then
			PositionEntity sp\Entity, EntityX(sp\Parent,True), EntityY(sp\Parent,True)+sp\Height#, EntityZ(sp\Parent,True), 1
		EndIf
	Else
		PositionEntity sp\Entity, EntityX(sp\Parent,True), EntityY(sp\Parent,True)+sp\Height#, EntityZ(sp\Parent,True), 1
	EndIf

	EntityAlpha sp\Entity, sp\alpha#
	ScaleEntity sp\Entity, sp\scale#,1,1
	
	If sp\Mode = 0 Then
		If sp\No#=p\No# Then EntityColor(sp\Entity,Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)])
	ElseIf sp\Mode=2 Then
		AlignToVector(sp\Entity, p\Animation\Align\x#, p\Animation\Align\y#, p\Animation\Align\z#, 2)
	Else
		EntityColor(sp\Entity,255, 203, 061)
		AlignToVector(sp\Entity, p\Animation\Align\x#, p\Animation\Align\y#, p\Animation\Align\z#, 2)
	EndIf

	sp\alpha# = sp\alpha# - 0.1*d\Delta
	sp\scale# = sp\scale# + 0.8*d\Delta

	If sp\alpha# < 0 Then
	FreeEntity sp\Entity
	FreeEntity sp\Pivot
	Delete sp
	Return		
	End If
		
End Function

;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================

	Global Interface_Emerald_R[7]
	Global Interface_Emerald_G[7]
	Global Interface_Emerald_B[7]
	Interface_Emerald_R[1] = 255 : Interface_Emerald_G[1] = 000 : Interface_Emerald_B[1] = 000
	Interface_Emerald_R[2] = 000 : Interface_Emerald_G[2] = 000 : Interface_Emerald_B[2] = 255
	Interface_Emerald_R[3] = 255 : Interface_Emerald_G[3] = 249 : Interface_Emerald_B[3] = 000
	Interface_Emerald_R[4] = 000 : Interface_Emerald_G[4] = 255 : Interface_Emerald_B[4] = 000
	Interface_Emerald_R[5] = 255 : Interface_Emerald_G[5] = 255 : Interface_Emerald_B[5] = 255
	Interface_Emerald_R[6] = 000 : Interface_Emerald_G[6] = 255 : Interface_Emerald_B[6] = 226
	Interface_Emerald_R[7] = 186 : Interface_Emerald_G[7] = 000 : Interface_Emerald_B[7] = 255

Type tEmerald
	Field Mesh[7]
	Field Pivot
	Field PosMultiplier#
	Field PosMultiplier_Actual#
	Field PosMultiplier_NegativeActual#
End Type

Function EntityColorEmerald(entity,emerald,forceoffset#=0)
	If forceoffset#=0 Then offset# = 100*Game\SuperForm Else offset# = 100*forceoffset#
	EntityColor(entity,Min#(255,Interface_Emerald_R[emerald]+offset#),Min#(255,Interface_Emerald_G[emerald]+offset#),Min#(255,Interface_Emerald_B[emerald]+offset#))
End Function

Function Create_Emerald.tEmerald(p.tPlayer)

	ee.tEmerald=New tEmerald

	ee\Pivot=CreatePivot()

	PositionEntity(ee\Pivot,p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#)

	For i=1 To 7
		Select p\Character
			Case CHAR_BLA,CHAR_MAR,CHAR_TIA: ee\Mesh[i]=CopyEntity(MESHES(Mesh_Sol), ee\Pivot)
			Default: ee\Mesh[i]=CopyEntity(MESHES(Mesh_Emerald), ee\Pivot)
		End Select
		EntityColorEmerald(ee\Mesh[i],i)
	Next

	ee\PosMultiplier# = -20 ;-50
	ee\PosMultiplier_Actual# = 3
	ee\PosMultiplier_NegativeActual# = 999

End Function

Function Update_Emerald(ee.tEmerald, p.tPlayer, d.tDeltaTime)

	PositionEntity(ee\Pivot,p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#)

	ee\PosMultiplier# = ee\PosMultiplier# + 1*d\Delta
	If ee\PosMultiplier# > ee\PosMultiplier_Actual# Then
		ee\PosMultiplier# = ee\PosMultiplier_Actual#
	EndIf

	PositionEntity(ee\Mesh[1], +0.0*ee\PosMultiplier#, 0.0, +6.6*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[2], +5.0*ee\PosMultiplier#, 0.0, +4.5*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[3], -5.0*ee\PosMultiplier#, 0.0, +4.5*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[4], -6.0*ee\PosMultiplier#, 0.0, -1.0*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[5], +6.0*ee\PosMultiplier#, 0.0, -1.0*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[6], -2.5*ee\PosMultiplier#, 0.0, -5.2*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[7], +2.5*ee\PosMultiplier#, 0.0, -5.2*ee\PosMultiplier#)

	For i=1 To 7 : 	TurnEntity(ee\Mesh[i], 0, -1*d\Delta, 0) : Next

	TurnEntity(ee\Pivot, 0, 5*d\Delta, 0)

	If Not(p\Action=ACTION_TRANSFORM) Then Remove_Emerald(ee)

End Function

Function Remove_Emerald(ee.tEmerald)
	For i=1 To 7 : FreeEntity(ee\Mesh[i]) : Next
	Delete ee
End Function