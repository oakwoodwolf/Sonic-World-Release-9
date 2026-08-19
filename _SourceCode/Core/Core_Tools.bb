

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Min#(a#, b#)
		If (a#<b#) Then Return a#
		Return b#
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Max#(a#, b#)
		If (a#>b#) Then Return a#
		Return b#
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Clamp#(v#, m#, mx#)
		Return Min#(Max#(v#, m#), mx#)
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Bound#(v#, min#, max#)
		If v#>max# Then v#=max#
		If v#<min# Then v#=min#
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function WrapAngle#(a#)
		While(a#<0.0)
			a# = a#+360.0
		Wend 
		While(a#>=360.0)
			a# = a#-360.0
		Wend
		Return a#
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RotateTowardsAngle#(o#, d#, s#)
    	OCos# = Cos(o#)
    	OSin# = Sin(o#)
    	DCos# = Cos(d#)
    	DSin# = Sin(d#)
    
    	Z#		= OCos*DSin-OSin*DCos
		Dot#	= OCos*DCos+OSin*DSin
		a#		= ACos(Dot#)
    	If (a#>s#) Then 
        	If (Z# >= 0) Then Return WrapAngle(Min#(o#+s#, d#))
	        Return WrapAngle#(Max#(o#-s#, d#))
		End If
    	Return WrapAngle#(o#)
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function AngleDifference#(a#, b#)
		If (a#<b#) Then Return Min(Abs(a#-b#), Abs((a#+360.0)-b#))
		Return Min(Abs(b#-a#), Abs((b#+360.0)-a#))		
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityType(e, t)
		EntityType(e, t)
		For i=1 To CountChildren(e)
			RecursiveEntityType(GetChild(e, i), t)
		Next 
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityPickMode(entity, pick_geometry, obscurer=True)
		EntityPickMode(entity, pick_geometry, obscurer)
		For i=1 To CountChildren(entity)
			RecursiveEntityPickMode(GetChild(entity, i), pick_geometry, obscurer)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveAnimate(entity, mode=1, speed#=1.0, sequence=0, transition#=0.0)
		If (AnimLength(entity)<>-1) Then Animate(entity, mode, speed#, sequence, transition#)
		For i=1 To CountChildren(entity)	
			RecursiveAnimate(GetChild(entity, i), mode, speed#, sequence, transition#)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveExtractAnimSeq(entity, first_frame, last_frame, anim_seq=0)
		If (AnimLength(entity)<>-1) Then ExtractAnimSeq(entity, first_frame, last_frame, anim_seq)
		For i=1 To CountChildren(entity)
			RecursiveExtractAnimSeq(GetChild(entity, i), first_frame, last_frame, anim_seq)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveSetAnimTime(entity, time#, anim_seq=0)
		If (AnimLength(entity)<>-1) Then SetAnimTime(entity, time#, anim_seq)
		For i=1 To CountChildren(entity)
			RecursiveSetAnimTime(GetChild(entity, i), time#, anim_seq)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveUpdateNormals(entity)
		If (EntityClass$(entity)="Mesh") Then UpdateNormals(entity)
		For i=1 To CountChildren(entity)
			RecursiveUpdateNormals(GetChild(entity, i))
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityAutoFade(entity, near#, far#)
		If (EntityClass$(entity)="Mesh") Then EntityAutoFade(entity, near#, far#)
		For i=1 To CountChildren(entity)
			RecursiveEntityAutoFade(GetChild(entity, i), near#, far#)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityFx(entity, fx)
		If (EntityClass$(entity)="Mesh") Then EntityFX(entity, fx)
		For i=1 To CountChildren(entity)
			RecursiveEntityFx(GetChild(entity, i), fx)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityOrder(entity, order)
		If (EntityClass$(entity)="Mesh") Then EntityOrder(entity, order)
		For i=1 To CountChildren(entity)
			RecursiveEntityOrder(GetChild(entity, i), order)
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityBlend(entity, blend)
		If (EntityClass$(entity)="Mesh") Then EntityBlend(entity, blend)
		For i=1 To CountChildren(entity)
			RecursiveEntityBlend(GetChild(entity, i), blend)
		Next
	End Function
	
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function RecursiveEntityColor(entity, r, g, b)
		If (EntityClass$(entity)="Mesh") Then EntityColor(entity, r, g, b)
		For i=1 To CountChildren(entity)
			RecursiveEntityColor(GetChild(entity, i), r, g, b)
		Next
	End Function
	
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Interpolate#(a#, b#, t#)
		Return a#+(b#-a#)*Clamp(t#, 0, 1)
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function InterpolateAngle#(a#, b#, t#)
		Sign = 1
    	If (Abs(a#-b#) < 180) Then Sign = -1

		t# = Clamp#(t#, 0, 1)
		
        Return ATan2(-(Sin(a#)+(Sin(b#)-Sin(a#)))*t#*Sign, Cos(a#)+(Cos(b#)-Cos(a#))*t#*Sign)
	End Function	

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function ZeroPadding$(s$, n)
		l = n-Len(s$)
		While(l>0)
			s$ = "0"+s$
			l = l-1
		Wend
		Return s$
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function CreateQuad(SizeX#, SizeZ#, OffsetX#, OffsetZ#, Parent=0)
		Quad    = CreateMesh(Parent)
		Surface = CreateSurface(Quad)
		
		AddVertex(Surface, -OffsetX#, 			0, -OffsetZ#, 			0, 1)
		AddVertex(Surface, -OffsetX#+SizeX#, 	0, -OffsetZ#, 			1, 1)
		AddVertex(Surface, -OffsetX#,  			0, -OffsetZ#+SizeZ#, 	0, 0)
		AddVertex(Surface, -OffsetX#+SizeX#,	0, -OffsetZ#+SizeZ#, 	1, 0)
		
		AddTriangle(Surface, 0, 2, 1)
		AddTriangle(Surface, 2, 3, 1)

		Return Quad
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function CalculateBoundingBox(Entity)
		MinX# = OMEGA#
		MinY# = OMEGA#
		MinZ# = OMEGA#
		MaxX# = EPSILON#
		MaxY# = EPSILON#
		MaxZ# = EPSILON#
		
		For i = 1 To CountSurfaces(Entity)
			Surface = GetSurface(Entity, i)
			For j = 0 To CountVertices(Surface)-1
				MinX# = Min#(VertexX#(Surface, j), MinX#)
				MinY# = Min#(VertexY#(Surface, j), MinY#)
				MinZ# = Min#(VertexZ#(Surface, j), MinZ#)

				MaxX# = Max#(VertexX#(Surface, j), MaxX#)
				MaxY# = Max#(VertexY#(Surface, j), MaxY#)
				MaxZ# = Max#(VertexZ#(Surface, j), MaxZ#)
			Next
		Next

		EntityBox(Entity, MinX#, MinY#, MinZ#, (MaxX#-MinX#)+1, (MaxY#-MinY#)+1, (MaxZ#-MinZ#)+1)
		DebugLog(MinX#+" "+MinY#+" "+MinZ#+" "+MaxX#+" "+MaxY#+" "+MaxZ#)
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

;this function changes the texture on a mesh so you can apply a bump/normal map
Function ApplyMeshTextureLayer(Mesh, SearchTexture$, Texture, Layered=False)
	Local SurfS=CountSurfaces( Mesh )
	Local SurfI=0
	Local BlankBrush = CreateTexture( 64, 64 )
	Local t0, t1, t2, t3
	
	SetBuffer TextureBuffer( BlankBrush )
	ClsColor 255, 255, 255
	Cls
	ClsColor 0, 0, 0
	SetBuffer BackBuffer()
	
	For SurfI=1 To Surfs
		sur=GetSurface( Mesh, SurfI )
		b=GetSurfaceBrush(sur)
		t0=GetBrushTexture( b, 0 )
		t1=GetBrushTexture( b, 1 )
		t2=GetBrushTexture( b, 2 )

		If Upper$( StripPath$( TextureName$( t0 ) ) ) = Upper$( SearchTexture$ ) Then
			Select Layered
				Case True
					BrushTexture b, Texture, 0, 2
					BrushTexture b, t0, 0, 0
					BrushTexture b, BlankBrush, 0, 1
				Case False
					BrushTexture b, Texture, 0, 0
					BrushTexture b, t0, 0, 1
					BrushTexture b, BlankBrush, 0, 1
			End Select

			PaintSurface sur, b
		EndIf

		If t0<>0 Then
			FreeTexture t0
		EndIf
	Next

	FreeTexture BlankBrush
End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function RenameFile(filehandle$, outputfilehandle$)
		CopyFile filehandle$,outputfilehandle$
		DeleteFile filehandle$
		Return outputfilehandle$
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

Function ScrollMeshTexture(mesh, x#, y#, flipped=False)

	; Loop through all the surfaces
	For index = 1 To CountSurfaces(mesh)
	surf = GetSurface(mesh, index)

	; For each surface, count each vertex amd adjust it's position
	For vertex = 0 To CountVertices(surf)-1
	u# = VertexU#(surf,vertex) + x#
	v# = VertexV#(surf,vertex) + y#

	; Wrap the uv coords for safety. This may be redundant.
	If x# > 0.0 Then
	If u# < 0.0 Then u# = 1.0
	EndIf
	If x# < 0.0 Then
	If u# > 1.0 Then u# = 0.0
	EndIf

	If y# > 0.0 Then
	If v# < 0.0 Then v# = 1.0
	EndIf
	If y# < 0.0 Then
	If v# > 1.0 Then v# = 0.0
	EndIf

	; Assign the new coords.
	VertexTexCoords surf, vertex, u#, v#

	; Go to next vertex
	Next

	; Go to next surface
	Next

End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	

Function lowest#(val1#, val2#)

   If val1<val2 Then
		Return val1
   Else
      Return val2
	EndIf
	
End Function


Function DeltaYawEntity( entity, target, value# = 0)
RotateEntity entity,EntityPitch(entity),(DeltaYaw#(target,entity) - value#),EntityRoll(entity)
End Function

Function DirectEntityTo( entity, pivot, x#, y#, z# , pitchfix#, yawfix#)
   PositionEntity Pivot , x, y, z
   TurnEntity entity, DeltaPitch( entity, pivot )-pitchfix#, DeltaYaw( entity, pivot )-yawfix#, 0
End Function

Function FaceEntity(entity,x#,y#,z#)
xdiff# = EntityX(entity)-x#
ydiff# = EntityY(entity)-y#
zdiff# = EntityZ(entity)-z#
dist#=Sqr#((xdiff#*xdiff#)+(zdiff#*zdiff#))
pitch# = ATan2(ydiff#,dist#)
yaw# = ATan2(xdiff#,-zdiff#)
RotateEntity entity,pitch#,yaw#,0
End Function

Function NormaliseNormals(mesh)

	Local s
	
	For s=1 To CountSurfaces(mesh)
	
		surf=GetSurface(mesh,s)
	
		For v=0 To CountVertices(surf)-1
	
			nx#=VertexNX#(surf,v)
			ny#=VertexNY#(surf,v)
			nz#=VertexNZ#(surf,v)
			
			uv#=Sqr(nx#^2+ny#^2+nz#^2)
	
			nx#=nx#/uv#
			ny#=ny#/uv#
			nz#=nz#/uv#
		
			VertexNormal surf,v,nx#,ny#,nz#
	
		Next
	
	Next

End Function


Function JoyPitchDir#(gamepadport=0)
Return Int(JoyPitch(gamepadport)/180)
End Function

Function JoyYawDir#(gamepadport=0)
Return Int(JoyYaw(gamepadport)/180)
End Function

Function JoyRollDir#(gamepadport=0)
Return Int(JoyRoll(gamepadport)/180)
End Function













; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/




Type flakes
 Field x#
 Field y#
 Field c
End Type

Global flake.flakes
Const TOTALFLAKES=400


InitFlakes()

;------------------------------------------------------------------------------------------------------------------------

Function InitFlakes()

  For x = 1 To TOTALFLAKES
   flake.flakes = New flakes
   flake\x#=Rnd(GraphicsWidth(),-70)
   flake\y#=Rnd(GraphicsHeight(),0)
   flake\c=Rnd(4,0)
  Next

End Function

Function UpdateFlakes()

  For flake.flakes = Each flakes

   If flake\y#>GraphicsHeight() Then 
    flake\x#=Rnd(GraphicsWidth(),-70)
    flake\y#=0
    flake\c=Rnd(4,0)
   End If

     Select flake\c    
      Case 1
       Color 80,80,80
       dir=Rnd(-.5,1)
       flake\x#=flake\x#+dir+.1
       flake\y#=flake\y#+.8
       Oval flake\x#,flake\y#,1,1,1
      Case 2
       Color 120,120,120
       dir=Rnd(-1,1.5)
       flake\x#=flake\x#+dir+.1
       flake\y#=flake\y#+1
       Oval flake\x#,flake\y#,2,2,1
      Case 3
       Color 180,180,180
       dir=Rnd(-1,2)
       flake\x#=flake\x#+dir+.1
       flake\y#=flake\y#+1.5
       Oval flake\x#,flake\y#,3,3,1
      Case 4
       Color 250,250,250
       dir=Rnd(-2,2.6)
       flake\x#=flake\x#+dir+.1
       flake\y#=flake\y#+2
       Oval flake\x#,flake\y#,4.5,4.5,1
     End Select   
  Next

End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

;Creates a full screen quad.
Function CreateFilterQuad(parent=0)
	Local mesh = CreateMesh(parent)
	Local surf = CreateSurface(mesh)
	
	AddVertex surf,-0.5,0.5,0
	AddVertex surf,0.5,0.5,0
	AddVertex surf,-0.5,-0.5,0
	AddVertex surf,0.5,-0.5,0
	
	AddTriangle surf,0,1,3 
	AddTriangle surf,0,3,2
	
	EntityColor mesh,255,255,255
	
	EntityFX mesh,1+8 ;Fullbright FX
	EntityBlend mesh,2 ;Multiply
	EntityOrder mesh, -999
	
	ScaleMesh mesh,4*GAME_WINDOW_SCALE#,3*GAME_WINDOW_SCALE#,1 ;Scale the quad mesh with a 4:3 ratio, to fill the entire screen.
	MoveEntity mesh,0,0,2 ;Move quad mesh so it fills the entire screen of the 4:3 camera.
	
	Return mesh
End Function

Function SetFilterColor(filterMesh, fVal#=1.0, r#=0, g#=0, b#=0)
	;Interpolate from [255,255,255] (which is white, no filter) to [r,g,b] supplied by the user.
	EntityColor filterMesh, 255.0 - (255.0-r)*fVal, 255.0 - (255.0-g)*fVal, 255.0 - (255.0-b)*fVal
End Function