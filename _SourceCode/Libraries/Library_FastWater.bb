Dim water_texture(5,13)
Dim water_bump_texture(5)
For h=1 to 5
	Select h
		Case 1: watertype$="water"
		Case 2: watertype$="lava"
		Case 3: watertype$="sea"
		Case 4: watertype$="acid"
		Case 5: watertype$="swamp"
	End Select
	For i=1 to 13
		water_texture(h,i) = LoadTexture("Textures\"+watertype$+"\"+i+".png",256)
		Select h
			Case 2: ScaleTexture water_texture(h,i), 250,250
			Default: ScaleTexture water_texture(h,i), 50,50
		End Select
	Next 
	water_bump_texture(h)=LoadTexture("Textures\"+watertype$+"\Bump.png", 9) 
	Select h
		Case 1: TextureBlend(water_bump_texture(h), FE_BUMP)
		Case 2: TextureBlend(water_bump_texture(h), FE_BUMPLUM)
		Case 3: TextureBlend(water_bump_texture(h), FE_BUMP)
		Case 4: TextureBlend(water_bump_texture(h), FE_BUMPLUM)
		Case 5: TextureBlend(water_bump_texture(h), FE_BUMP)
	End Select
Next


;--------------------------------------------------------------------------------------

Type tFastWater
	Field Mesh
	Field BumpTexture
	Field Clipplane
	Field ReflectTexture
	Field BumpFrame
	Field BumpFrameTimer
	Field TextureSize
	Field CausticTexture
	Field SpecularTexture
End Type

Global CameraSprite

Function DetectSupport()
	cansupportdx7=True
	If GfxDriverCapsEx\ClipplanesMax=0 Then Warning = "Warning! Your video-card not support clipplanes. " : cansupportdx7=False
	If GfxDriverCapsEx\Bump=0 Then Warning = Warning + "Warning! Your video-card not support EMBM (Bump). " : cansupportdx7=False
	Return cansupportdx7
End Function


Function CreateReflectTexture(w,h)
	; ----
	If ReflectTexture Then FreeTexture ReflectTexture
	; ----
	ReflectTexture = CreateTexture (w, h, 1+16+32+256 + FE_RENDER + FE_ZRENDER)
																		; create texture for render reflections	
	TextureBlend ReflectTexture, FE_PROJECT
																		; new blend for 2D project texture
	PositionTexture ReflectTexture, 0.5, 0.5
																		; set 2D projection texture to center of screen
	ScaleTexture ReflectTexture, 2, -2
																		; negative scale on Y axis (since reflection mirror)
	; ----				
End Function


Function CreateWater.tFastWater(w=256, h=256, height#=0, Bumppath$="Textures/Water/bump.png")

	Water.tFastWater = New tFastWater
	
	Water\ReflectTexture = CreateTexture(w, h, 1+16+32+256 + FE_RENDER + FE_ZRENDER)
	TextureBlend(Water\ReflectTexture, FE_PROJECT)
	PositionTexture(Water\ReflectTexture, 0.5, 0.5)
	ScaleTexture(Water\ReflectTexture, 2, -2)
	
	Water\BumpTexture = LoadTexture(Bumppath$, 9)
	TextureBlend(Water\BumpTexture, FE_BUMP)
	ScaleTexture(Water\BumpTexture,19.0125,19.0125)

	Water\Mesh = CreatePlane(16)
	EntityColor(Water\Mesh,255,255,255)
	TranslateEntity(Water\Mesh, 0, height#, 0)
	EntityShininess(Water\Mesh, 0.75)

	Water\Clipplane = CreateClipplane(Water\Mesh); align clipplane by water plane
	AlignClipplane(Water\Clipplane, Water\Mesh)

	Return Water
	
End Function


Function FreeWater()
	For Water.tFastWater = Each tFastWater		
		FreeTexture(Water\ReflectTexture)	
		FreeTexture(Water\BumpTexture) 
		FreeClipplane(Water\Clipplane)
		FreeEntity(Water\Mesh)
		Delete Water
	Next
End Function

Function UpdateWater(Water.tFastWater, Camera, Listener, Sky)

			; PASS 1
			CameraFogMode(Camera,1)	
			HideEntity(CameraSprite)
			HideEntity(Listener)	
			
			ScaleEntity(Sky, 350.1, 350.1, 350.1)
			
			ShowClipplane(Water\Clipplane)
			HideEntity(Water\Mesh)
	
			MirrorCamera(Camera, Water\Mesh)
			
			SetBuffer(TextureBuffer(Water\ReflectTexture))
			CameraViewport(Camera, 0,0, TextureWidth(Water\ReflectTexture), TextureHeight(Water\ReflectTexture))
			ScaleEntity(Camera,1,Float(GraphicsHeight())/Float(GraphicsWidth()),1)
			RenderWorld
			
			RestoreCamera(Camera)	
			
			; PASS 2
			If UnderWaterFlag
				EntityAlpha(Water\Mesh,0.6)
			Else
				EntityAlpha(Water\Mesh,0.75)
			EndIf
	
			HideClipplane(Water\Clipplane)
			ShowEntity(Water\Mesh)
			
			PositionTexture(Water\BumpTexture, 0, (0.0725)*(MilliSecs()*0.0035))
			EntityTexture(Water\Mesh, Water\BumpTexture, 0, 0)
			EntityTexture(Water\Mesh, water_texture(Game\Stage\Properties\WaterType, Game\Stage\Properties\WaterTexture), 0, 1)
			EntityTexture(Water\Mesh, Water\BumpTexture, 0, 2)
			EntityTexture(Water\Mesh, Water\ReflectTexture, 0, 3)
	
			SetBuffer BackBuffer()
			CameraViewport(Camera, 0,0, GraphicsWidth(), GraphicsHeight())
			ScaleEntity(Camera,1,1,1)
			RenderWorld
			

			; camera under water-plane?
			If Game\Stage\Properties\WaterLevelChanged=1 Then PositionEntity(Water\Mesh, 0, Game\Stage\Properties\WaterLevel, 0)
			
			If EntityY(Camera,1)>Game\Stage\Properties\WaterLevel Then UnderWaterFlag = 0 Else UnderWaterFlag = 1
			
			For c.tCamera = Each tCamera
			CameraFogMode(Camera,c\Underwater)
			Next
			
			ScaleEntity(Sky, 15.1, 15.1, 15.1)
			
			ShowEntity(Listener)
			
			AlignClipplane(Water\Clipplane, Water\Mesh)
			
			If (MilliSecs()-Water\BumpFrameTimer)>33 Then
				Water\BumpFrame = Water\BumpFrame + 1
				Water\BumpFrameTimer = MilliSecs()
			EndIf

End Function