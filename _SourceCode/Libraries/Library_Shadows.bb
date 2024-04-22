
Function InitializeGeneralLight(lighttype, range, parent)

	Game\Stage\Properties\GeneralLightPivot = CreatePivot(parent)
	TurnEntity(Game\Stage\Properties\GeneralLightPivot, 0, 180, 0)
	Game\Stage\Properties\GeneralLight = CreateLight(lighttype, Game\Stage\Properties\GeneralLightPivot)
	TurnEntity Game\Stage\Properties\GeneralLight, 60, 0, 0
	LightRange(Game\Stage\Properties\GeneralLight, range)

End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Type tCircleShadow
	Field Mesh
	Field Texture
	Field Parent
	Field Pivot
End Type

Function Init_CircleShadow(entity,mesh=0, multiply#=1)
	If mesh<>0 Then
	x# = 4*multiply#*FindMeshCircumfrence(mesh)/10
	y# = 4*multiply#*FindMeshCircumfrence(mesh)/10
	offx# = 2*multiply#*FindMeshCircumfrence(mesh)/10
	offy# = 2*multiply#*FindMeshCircumfrence(mesh)/10
	Else
	x# = 4*multiply#*FindMeshCircumfrence(entity)/10
	y# = 4*multiply#*FindMeshCircumfrence(entity)/10
	offx# = 2*multiply#*FindMeshCircumfrence(entity)/10
	offy# = 2*multiply#*FindMeshCircumfrence(entity)/10
	EndIf
	booz=booz+1
	Return Create_CircleShadow(entity,x#,y#,offx#,offy#)
End Function

Function Create_CircleShadow(Parent,x#,y#,offx#,offy#)
	cshw.tCircleShadow = New tCircleShadow
		cshw\Parent=Parent
		cshw\Mesh = CreateQuad(x#, y#, offx#, offy#, Game\Stage\Root)
		cshw\Texture = LoadTexture("Textures\ShadowCircle.png", 1+2)
		EntityTexture(cshw\Mesh, cshw\Texture)
		HideEntity(cshw\Mesh)
		FreeTexture(cshw\Texture)
	Return cshw\Mesh
	DebugLog("Circle Shadow created")
End Function

Global CIRCLESHADOWDISTANCE#=256
Function Update_CircleShadow(Mesh, Parent, Camera, Hide=0)
	If Hide=0 Then
		If EntityDistance(Camera, Parent)<=CIRCLESHADOWDISTANCE# Then ;----
			If (LinePick(EntityX(Parent), EntityY(Parent), EntityZ(Parent), 0, -OMEGA#, 0)<>0) Then
				PositionEntity(Mesh, PickedX#(), PickedY#()+0.1, PickedZ#(), True)
				AlignToVector(Mesh, PickedNX#(), PickedNY#(), PickedNZ#(), 2)
				ScaleEntity(Mesh, (1-Min#((EntityY#(Parent)-PickedY#())/120, 1)), (1-Min#((EntityY#(Parent)-PickedY#())/120, 1)), (1-Min#((EntityY#(Parent)-PickedY#())/120, 1)))
				EntityAlpha(Mesh, 0.75-Min#((EntityY#(Parent)-PickedY#())/120, 1))			
				ShowEntity(Mesh)
			Else
				PositionEntity(Mesh, PickedX#(), PickedY#()+0.1, PickedZ#(), True)
				AlignToVector(Mesh, PickedNX#(), PickedNY#(), PickedNZ#(), 2)
				HideEntity(Mesh)
			End If
		Else ;----
			HideEntity(Mesh)
		EndIf ;----
	Else
		HideEntity(Mesh)
	EndIf
End Function


Function Delete_CircleShadows(cshw.tCircleShadow)
	If cshw\mesh<>0 Then cshw\Mesh=0
	If cshw\Parent<>0 Then cshw\Parent=0
	Delete cshw
	Return
End Function

Function FindMeshCircumfrence(mesh)
	circ# = Sqr#(MeshWidth(mesh)^2+MeshHeight(Mesh)^2+MeshDepth(mesh)^2)
	Return circ#
End Function