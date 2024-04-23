
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; --- Collisions constants ---
	Const COLLISION_NONE						=	0
	Const COLLISION_PLAYER						=	1
	Const COLLISION_OBJECT						=	2
	Const COLLISION_OBJECT2						=	3
	Const COLLISION_OBJECT_GOTHRU				=	4
	Const COLLISION_OBJECT2_GOTHRU				=	5
	Const COLLISION_CAMERA						=	6
	Const COLLISION_WORLD_POLYGON				=	7
	Const COLLISION_WORLD_POLYGON_DEATH			=	8
	Const COLLISION_WORLD_POLYGON_DEATH_GOTHRU	=	9
	Const COLLISION_WORLD_POLYGON_HURT			=	10
	Const COLLISION_WORLD_POLYGON_RAIL			=	11
	Const COLLISION_WORLD_POLYGON_BLOCK			=	12
	Const COLLISION_WORLD_POLYGON_PINBALL		=	13
	Const COLLISION_WORLD_POLYGON_ICE			=	14
	Const COLLISION_WORLD_POLYGON_BOUNCE		=	15
	Const COLLISION_WORLD_POLYGON_SLIDEXP		=	111
	Const COLLISION_WORLD_POLYGON_SLIDEXN		=	112
	Const COLLISION_WORLD_POLYGON_SLIDEZP		=	113
	Const COLLISION_WORLD_POLYGON_SLIDEZN		=	114
	Const COLLISION_WORLD_POLYGON_SLOW			=	16

	Type MeshStructure
		Field Entity
		Field AnimTexType
		Field ScrollSpeedX#
		Field ScrollSpeedY#
		Field ChangeTexSpeed#
		Field ChangeTexTimer
		Field ChangeTexOrder
		Field DiffuseTexture[4]
		Field ShallRepeat
		Field RepeatDistance#
		Field InitialPosX#
		Field InitialPosY#
		Field InitialPosZ#
		Field ForSpecialStage
	End Type

	Type LightSource
		Field Source
	End Type

	Global cam.tCamera

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Dim water_texture(8,13)
Dim water_bump_texture(8)
For h=1 to 8
	Select h
		Case 1: watertype$="water"
		Case 2: watertype$="lava"
		Case 3: watertype$="sea"
		Case 4: watertype$="acid"
		Case 5: watertype$="swamp"
		Case 6: watertype$="sand"
		Case 7: watertype$="oil"
		Case 8: watertype$="pinkwater"
	End Select
	Select h
		Case 6: j=1
		Default: j=13
	End Select
	For i=1 to j
		water_texture(h,i) = LoadTexture("Textures\"+watertype$+"\"+i+".png",1+256)
		Select h
			Case 2,7: ScaleTexture water_texture(h,i), 250,250
			Default: ScaleTexture water_texture(h,i), 50,50
		End Select
	Next
	water_bump_texture(h)=LoadTexture("Textures\"+watertype$+"\Bump.png", 9) 
	Select h
		Case 1,3,5,8: TextureBlend(water_bump_texture(h), FE_BUMP)
		Case 2,4,6,7: TextureBlend(water_bump_texture(h), FE_BUMPLUM)
	End Select
Next

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_Start()
	SetFont LilFont

		; gamepad sensitivity
		If Menu\Stage<>0 Then
			Input_GamepadThreshold = 0.25
		Else
			Input_GamepadThreshold = 0.8
		EndIf

		; Reset pause situation
		Menu\Pause=0
		Input\Hold\Start=False
		Input\Pressed\Start=False

		; Interface loading
		Game_Stage_UpdateProgressBar("Loading interface", 0)
		If Menu\Stage<>0 Then
			If Menu\ChaoGarden=0 Then
				For x=INTERFACE_MENUTOTAL+1 to INTERFACE_STAGETOTAL : LoadSmartImage(x) : Next
			Else
				For x=INTERFACE_BLACKMARKETTOTAL+1 to INTERFACE_CHAOGARDENTOTAL : LoadSmartImage(x) : Next
			EndIf
		Else
			For x=INTERFACE_ALWAYSTOTAL+1 to INTERFACE_MENUTOTAL : LoadSmartImage(x) : Next
		EndIf

		; Sound loading
		Game_Stage_UpdateProgressBar("Loading sounds", 0)
		If Menu\Stage<>0 Then
			For x=SOUNDS_RESULTSTOTAL+1 to SOUNDS_STAGETOTAL : LoadSmartSound(x) : Next
		Else
			For x=SOUNDS_MENUMUSICTOTAL+1 to SOUNDS_MENUTOTAL : LoadSmartSound(x) : Next
		EndIf

		; Reset values
		Game_Stage_UpdateProgressBar("Resetting all values", 0)
		OBJECT_VIEWDISTANCE_IDCOUNT#=0
		OBJECT_VIEWDISTANCE_LARGESTIDCOUNT#=0
		Game\Cheater=0 : Game\CheaterChangedCharacter=0
		Player_ResetOutGameValues()
		Game\Stage\Properties\Water=0
		Game\Stage\Properties\WaterType=1
		Game\Stage\Properties\WaterLevel = -99999
		Game\Stage\Properties\WaterLevelInitial = 0
		Game\Stage\Properties\WaterLevelTarget = 0
		Game\Stage\Properties\WaterLevelChanged = 0
		Game\Stage\Properties\DeathLevel = -2500
		Game\Stage\Properties\AmbientAlarm = 0
		Game\Stage\Properties\AmbientBeach = 0
		Game\Stage\Properties\AmbientForest = 0
		Game\Stage\Properties\AmbientRain = 0
		Game\Stage\Properties\AmbientSnow = 0
		Game\Stage\Properties\AmbientVoid = 0
		Game\Stage\Properties\AmbientWind = 0
		Game\Stage\Properties\AmbientParticle = ParticleTemplate_Create.tParticleTemplate()
		Game\Leader=1
		Game\NewLeader=1
		CAMERA_ZOOMVALUE# = 21
		CAMERA_ACTUALZOOMVALUE# = 21
		CAMERA_DISTANCE_NEAR# = CAMERA_ZOOMVALUE#
		CAMERA_DISTANCE_FAR# = CAMERA_ZOOMVALUE#
		Game\Interface\DebugPlacerOn=0
		If Menu\Settings\DisablePlants#=0 Or (Menu\Settings\DisablePlants#=1 and Menu\ChaoGarden=0) Then Menu\Settings\RealDisablePlants#=0 Else Menu\Settings\RealDisablePlants#=1
		For i=1 to 5 : MonitorIcon(i)\Draw=False : MonitorIcon(i)\Timer=0 : Next
		Game\ResultRings=0
		Game\ResultRingsForBank=0
		Game\ResultEnemies=0
		Game\ResultTime=5999999
		Game\ResultPerfectBonus=0
		Game\ScoreBonus=0
		Game\ScoreTotal=0
		Game\Rank=7
		Game\RivalAmount=0
		Game\InsideBoxCheckerTimer=0
		Game\WholeVehicle=0

		; Decide music
		If Menu\Stage<>0 and Menu\ChaoGarden=0 Then
			If Menu\Members>2 and Menu\Team>0 Then
				Select Menu\Team
					Case TEAM_SONIC,TEAM_ROSE,TEAM_CHAOTIX,TEAM_SOL,TEAM_OLDIES,TEAM_RELIC:
						Game\Stage\Properties\MusicType=1
					Case TEAM_DARK,TEAM_HOOLIGAN,TEAM_BABYLON,TEAM_ROBOTNIK:
						Game\Stage\Properties\MusicType=2
					Default:
						Game\Stage\Properties\MusicType=1
				End Select
			Else
				Select CHARSIDES(InterfaceChar(Menu\Character[1]))
					Case 1,2: Game\Stage\Properties\MusicType=CHARSIDES(InterfaceChar(Menu\Character[1]))
					Default: Game\Stage\Properties\MusicType=1
				End Select
			EndIf
		Else
			Game\Stage\Properties\MusicType=0
		EndIf

		; Menu music
		If (Not(Menu\Stage<>0)) and Menu\ChaoGarden=0 Then
			Game_Stage_UpdateProgressBar("Loading menu music", 0)
			LoadMenuMusic()
			Delay(75)
		EndIf

		; At startup, load up current stage from the list. First, obtain the final path to the stage folder
		; and hold on a string. Then, parse the Stage.xml file to find out all the specifications
		; and objects on the stage.
		Game_Stage_UpdateProgressBar("Acquiring stage path", 0)
		Select Menu\Stage
			Case 0: Path$ = "Interface/"
			Default:
				Menu_UpdateStageNames(Menu\Stage)
				Select Menu\ChaoGarden
					Case 0:
						If Menu\Stage>0 Then
							Path$ = StagePath$(Menu\Stage)+"/"
						Else
							Path$ = "ChaoWorld/Special Stage/"
						EndIf
					Case 1:
						Select Menu\Stage
						Case 998: Path$ = "ChaoWorld/Chao Races/Race "+Menu\RaceType+"/"
						Case 997: Path$ = "ChaoWorld/Chao Races/Karate/"
						Default: Path$ = "ChaoWorld/Chao Garden/"
						End Select
				End Select
		End Select
		Game\Stage\Properties\Path$=Path$

		; Stage music
		If Menu\Stage<>0 Then
			Stage_ResetStageMusic()
			LoadStageMusic(Path$)
		EndIf

		; Character models
		Game_Stage_UpdateProgressBar("Loading player models", 0)
		For i=1 to 3
			If Menu\Members>i-1 Then
				LoadCharacterMesh(Menu\Character[i],0)
				Game\CharacterMesh[i]=CopyEntity(CharacterMesh)
				DeleteCharacterMesh()

				If IsCharMod(Menu\Character[i]) Then
					If CharModHasSuper(Menu\Character[i]) Then
						LoadCharacterMesh(Menu\Character[i],0,1)
					Else
						LoadCharacterMesh(Menu\Character[i],0,0)
					EndIf
				Else
					If Player_HasSuperModel(Menu\Character[i]) Then
						LoadCharacterMesh(Menu\Character[i],0,1)
					Else
						LoadCharacterMesh(Menu\Character[i],0,0)
					EndIf
				EndIf
				Game\SuperCharacterMesh[i]=CopyEntity(CharacterMesh)
				DeleteCharacterMesh()

				If IsCharMod(Menu\Character[i]) Then
					If CharModHasHyper(Menu\Character[i]) Then
						LoadCharacterMesh(Menu\Character[i],0,2)
					ElseIf CharModHasSuper(Menu\Character[i]) Then
						LoadCharacterMesh(Menu\Character[i],0,1)
					Else
						LoadCharacterMesh(Menu\Character[i],0,0)
					EndIf
				Else
					If Player_HasSuperModel(Menu\Character[i]) Then
						LoadCharacterMesh(Menu\Character[i],0,2)
					Else
						LoadCharacterMesh(Menu\Character[i],0,0)
					EndIf
				EndIf
				Game\HyperCharacterMesh[i]=CopyEntity(CharacterMesh)
				DeleteCharacterMesh()
			Else
				Game\CharacterMesh[i]=CopyEntity(MESHES(Mesh_Empty))
				Game\SuperCharacterMesh[i]=CopyEntity(MESHES(Mesh_Empty))
				Game\HyperCharacterMesh[i]=CopyEntity(MESHES(Mesh_Empty))
			EndIf
		Next

		; Create root entity for all the stage entities
		Game\Stage\Root 			= CreatePivot()
		Game\Stage\Gravity			= CreatePivot()
		Game\Stage\GravityAlignment	= Vector(0, 1, 0)

		; Initialize general light
		InitializeGeneralLight(1, 20000, Game\Stage\Root)

		; Create camera
		Game_Stage_UpdateProgressBar("Creating camera", 0)
		cam.tCamera = Camera_Create()
		
		; Parse up Stage.xml file and retrieve root node. Find out if any parse errors ocurred while loading
		; and if so, stop the game.
		Game_Stage_UpdateProgressBar("Parsing stage XML", 0)
		Select Menu\Stage
			Case 0: RootNode = xmlLoad(Path$+"menustage.dat")
			Default: RootNode = xmlLoad(Path$+"Stage.xml")
		End Select
		If (xmlErrorCount()>0) Then RuntimeError("Game_Update_Stage() -> [Start] Error loading stage '"+Game\Stage\List\Folder$+"' xml. Parse error.")

		; Retrieve stage settings. From here on, this programming is kinda shitty, as the program presumes
		; all the nodes we're going to access to exist.
		For i = 1 To xmlNodeChildCount(RootNode)
			; Get child node
			RootChildNode = xmlNodeChild(RootNode, i)
	
			; Find out wich type it is.
			Select xmlNodeNameGet$(RootChildNode)
				Case "information"
					
					; Retrieve stage information such as stage's name, music and ambient light.
					For j = 1 To xmlNodeChildCount(RootChildNode)
						; Update progress bar
						Game_Stage_UpdateProgressBar("Loading stage information", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
						
						; Retrieve child node
						InformationChildNode = xmlNodeChild(RootChildNode, j)
		
						; Find out what it is and acquire.
						Select xmlNodeNameGet$(InformationChildNode)
							Case "ambientlight"
								AmbientLight(xmlNodeAttributeValueGet(InformationChildNode, "r"), xmlNodeAttributeValueGet(InformationChildNode, "g"), xmlNodeAttributeValueGet(InformationChildNode, "b"))
							Case "water"
								Game\Stage\Properties\Water = xmlNodeAttributeValueGet(InformationChildNode, "on")

								If Game\Stage\Properties\Water=1 Then
									Game\Stage\Properties\WaterLevel = xmlNodeAttributeValueGet(InformationChildNode, "level")
									Game\Stage\Properties\WaterLevelInitial=Game\Stage\Properties\WaterLevel
									Game\Stage\Properties\WaterMesh = CreatePlane()
									PositionEntity(Game\Stage\Properties\WaterMesh, 0, Game\Stage\Properties\WaterLevel, 0)
									Game\Stage\Properties\WaterTexture=1

									Select xmlNodeAttributeValueGet(InformationChildNode, "type")
										Case "water": Game\Stage\Properties\WaterType=1
										Case "lava": Game\Stage\Properties\WaterType=2
										Case "sea","ocean": Game\Stage\Properties\WaterType=3
										Case "acid": Game\Stage\Properties\WaterType=4
										Case "swamp": Game\Stage\Properties\WaterType=5
										Case "sand": Game\Stage\Properties\WaterType=6
										Case "oil": Game\Stage\Properties\WaterType=7
										Case "pinkwater": Game\Stage\Properties\WaterType=8
										Default: Game\Stage\Properties\WaterType=1
									End Select
									
									If Menu\Settings\WaterQuality#>0 Then
										Select Game\Stage\Properties\WaterType
											Case 1: CreateWater.tFastWater(128, 128, Game\Stage\Properties\WaterLevel, "Textures/water/Bump.png")
											Case 3: CreateWater.tFastWater(128, 128, Game\Stage\Properties\WaterLevel, "Textures/sea/Bump.png")
											Case 5: CreateWater.tFastWater(128, 128, Game\Stage\Properties\WaterLevel, "Textures/swamp/Bump.png")
										End Select
										Select Game\Stage\Properties\WaterType
											Case 1,3,5: HideEntity(Game\Stage\Properties\WaterMesh)
										End Select
									EndIf

									If Menu\Settings\BumpMaps#>0 Then EntityTexture(Game\Stage\Properties\WaterMesh, water_bump_texture(Game\Stage\Properties\WaterType),0,0)
									EntityTexture(Game\Stage\Properties\WaterMesh, water_texture(Game\Stage\Properties\WaterType,Game\Stage\Properties\WaterTexture),0,1)

									EntityShininess(Game\Stage\Properties\WaterMesh, 0)
									Select Game\Stage\Properties\WaterType
										Case 2,4,6,7: EntityAlpha(Game\Stage\Properties\WaterMesh, 1)
										Default: EntityAlpha(Game\Stage\Properties\WaterMesh, 0.531)
									End Select
								EndIf
							Case "death"
								Game\Stage\Properties\DeathLevel = xmlNodeAttributeValueGet(InformationChildNode, "level")
							Case "alarm"
								Game\Stage\Properties\AmbientAlarm = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientAlarm=1 Then LoadSmartSound(Sound_AmbientAlarm)
							Case "beach"
								Game\Stage\Properties\AmbientBeach = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientBeach=1 Then LoadSmartSound(Sound_AmbientBeach)
							Case "forest"
								Game\Stage\Properties\AmbientForest = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientForest=1 Then LoadSmartSound(Sound_AmbientForest)
							Case "rain"
								Game\Stage\Properties\AmbientRain = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientRain=1 Then LoadSmartSound(Sound_AmbientRain)
							Case "snow"
								Game\Stage\Properties\AmbientSnow = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientSnow=1 Then LoadSmartSound(Sound_AmbientSnow)
							Case "void"
								Game\Stage\Properties\AmbientVoid = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientVoid=1 Then LoadSmartSound(Sound_AmbientVoid)
							Case "wind"
								Game\Stage\Properties\AmbientWind = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\Stage\Properties\AmbientWind=1 Then LoadSmartSound(Sound_AmbientWind)
							Case "skydome","skybox"
								If Menu\Stage<>0 Then
									If Menu\ChaoGarden=0 Or (Menu\ChaoGarden=1 And (Not(Menu\Stage=999))) Then
										Game\Stage\Properties\Skydome = LoadAnimMesh(Path$+xmlNodeDataGet(InformationChildNode))
										ScaleEntity(Game\Stage\Properties\Skydome, 0.1, 0.1, 0.1)
										EntityColor(Game\Stage\Properties\Skydome, 255, 255, 255)
										EntityFX(Game\Stage\Properties\Skydome, 9)
										EntityOrder(Game\Stage\Properties\Skydome, 1)
										Animate(Game\Stage\Properties\Skydome,1)
									Else
										Stage_CreateCyclingSkyBox(Path$)
										Game\Stage\Properties\Skydome = CopyEntity(MESHES(Mesh_Empty))
									EndIf
								Else
									Game\Stage\Properties\Skydome = CopyEntity(MESHES(Mesh_Empty))
								EndIf
							Case "earth"
								If xmlNodeAttributeValueGet(InformationChildNode, "on") = 1 Then
									Game\Stage\Properties\Earth = LoadAnimMesh("Textures/Earth/Earth.b3d")
									ScaleEntity(Game\Stage\Properties\Earth, 0.1, 0.1, 0.1)
									EntityColor(Game\Stage\Properties\Earth, 255, 255, 255)
									EntityFX(Game\Stage\Properties\Earth, 9)
									EntityOrder(Game\Stage\Properties\Earth, 1)
								ElseIf xmlNodeAttributeValueGet(InformationChildNode, "on") = 0 Then
									Game\Stage\Properties\Earth = CopyEntity(MESHES(Mesh_Empty))
								EndIf
							Case "moon"
								If xmlNodeAttributeValueGet(InformationChildNode, "on") = 1 Then
									Game\Stage\Properties\Moon = LoadAnimMesh("Textures/Earth/Moon.b3d")
									ScaleEntity(Game\Stage\Properties\Moon, 0.1, 0.1, 0.1)
									EntityColor(Game\Stage\Properties\Moon, 255, 255, 255)
									EntityFX(Game\Stage\Properties\Moon, 9)
									EntityOrder(Game\Stage\Properties\Moon, 1)
								ElseIf xmlNodeAttributeValueGet(InformationChildNode, "on") = 0 Then
									Game\Stage\Properties\Moon = CopyEntity(MESHES(Mesh_Empty))
								EndIf
							Case "sun"
								If xmlNodeAttributeValueGet(InformationChildNode, "on") = 1 Then
									Game\Stage\Properties\Sun = LoadMesh("Textures/Earth/Sun.b3d")
									ScaleEntity(Game\Stage\Properties\Sun, 2.5, 2.5, 2.5)
									EntityColor(Game\Stage\Properties\Sun, 255, 255, 255)
									EntityFX(Game\Stage\Properties\Sun, 1+8+32)
									EntityOrder(Game\Stage\Properties\Sun, 1)

									If Menu\ChaoGarden=1 and Menu\Stage=999 Then
									Game\Stage\Properties\SunMoon = LoadMesh("Textures/Earth/SunMoon.b3d")
									ScaleEntity(Game\Stage\Properties\SunMoon, 2.5, 2.5, 2.5)
									EntityColor(Game\Stage\Properties\SunMoon, 255, 255, 255)
									EntityFX(Game\Stage\Properties\SunMoon, 1+8+32)
									EntityOrder(Game\Stage\Properties\SunMoon, 1)
									EndIf
								ElseIf xmlNodeAttributeValueGet(InformationChildNode, "on") = 0 Then
									Game\Stage\Properties\Sun = CopyEntity(MESHES(Mesh_Empty))
								EndIf

								Game\Stage\Properties\SunPos = New tVector
								Game\Stage\Properties\SunPos\X#=0
								Game\Stage\Properties\SunPos\Y#=0
								Game\Stage\Properties\SunPos\Z#=0
								Game\Stage\Properties\SunPos\X# = xmlNodeAttributeValueGet(InformationChildNode, "x")
								Game\Stage\Properties\SunPos\Y# = xmlNodeAttributeValueGet(InformationChildNode, "y")
								Game\Stage\Properties\SunPos\Z# = xmlNodeAttributeValueGet(InformationChildNode, "z")
								PositionEntity(Game\Stage\Properties\Sun, Game\Stage\Properties\SunPos\X#, Game\Stage\Properties\SunPos\Y#, Game\Stage\Properties\SunPos\Z#, 1)
								If Menu\ChaoGarden=1 and Menu\Stage=999 Then PositionEntity(Game\Stage\Properties\SunMoon, Game\Stage\Properties\SunPos\X#, Game\Stage\Properties\SunPos\Y#, Game\Stage\Properties\SunPos\Z#, 1)

								Scale# = xmlNodeAttributeValueGet(InformationChildNode, "scale")
								If Scale#<>0 Then
									ScaleEntity(Game\Stage\Properties\Sun, Scale#, Scale#, Scale#)
									If Menu\ChaoGarden=1 and Menu\Stage=999 Then ScaleEntity(Game\Stage\Properties\SunMoon, Scale#, Scale#, Scale#)
								EndIf

								Game\Stage\Properties\SunRays = xmlNodeAttributeValueGet(InformationChildNode, "sunrays")
								If Not(Game\Stage\Properties\Sun > 0) Then Game\Stage\Properties\SunRays = 0
							Case "vehicle"
								Game\WholeVehicle = xmlNodeAttributeValueGet(InformationChildNode, "on")
								If Game\WholeVehicle<0 Or Game\WholeVehicle>9 Then Game\WholeVehicle=0
						End Select 
					Next
		
					
				Case "scene"
					; Retrieve stage's scene specifications
					For j = 1 To xmlNodeChildCount(RootChildNode)
						
						; Retrieve child node
						SceneChildNode = xmlNodeChild(RootChildNode, j)
						
						; Find out wich kind of scene entity it is.
						Select xmlNodeNameGet$(SceneChildNode)
							Case "mesh","animmesh","hurtmesh","deathmesh","railmesh","blockmesh","pinballmesh","rollmesh","icemesh","bouncemesh","slowmesh","slidemesh"
								; Retrieve mesh properties information
								MeshFilename$	= xmlNodeAttributeValueGet(SceneChildNode, "filename") 
								MeshVisible	= xmlNodeAttributeValueGet(SceneChildNode, "visible")
								MeshCollision	= xmlNodeAttributeValueGet(SceneChildNode, "collision")

								; Update progress bar
								Game_Stage_UpdateProgressBar("Loading scene mesh "+j+": "+MeshFilename$, Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
						
								; Load mesh and set visibility. Depending if the mesh should
								; cast collisions, set alpha to 0 or hide the entity. After this
								; set collision type.
								; Create structure
								m.MeshStructure = New MeshStructure
								
								Select xmlNodeNameGet$(SceneChildNode)
									Case "animmesh":
										m\Entity = LoadAnimMesh(Path$+MeshFilename$, Game\Stage\Root)
										Animate(m\Entity,1)
									Default:
										m\Entity = LoadMesh(Path$+MeshFilename$, Game\Stage\Root)
								End Select

								If (MeshVisible = False) Then
									If (MeshCollision = True) Then
										EntityAlpha(m\Entity, 0.0)
									Else
										HideEntity(m\Entity)
									End If
								End If
								If (MeshCollision = True) Then
									Select xmlNodeNameGet$(SceneChildNode)
										Case "deathmesh"
											If (MeshVisible = False) Then
												EntityType(m\Entity, COLLISION_WORLD_POLYGON_DEATH_GOTHRU)
											Else
												EntityType(m\Entity, COLLISION_WORLD_POLYGON_DEATH)
											EndIf
										Case "hurtmesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_HURT)
										Case "railmesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_RAIL)
										Case "blockmesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_BLOCK)
										Case "pinballmesh","rollmesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_PINBALL)
										Case "icemesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_ICE)
										Case "bouncemesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_BOUNCE)
										Case "slidemesh"
											Select xmlNodeAttributeValueGet(SceneChildNode, "dir")
											Case 1,"right": EntityType(m\Entity, COLLISION_WORLD_POLYGON_SLIDEXP)
											Case 2,"left": EntityType(m\Entity, COLLISION_WORLD_POLYGON_SLIDEXN)
											Case 3,"forward": EntityType(m\Entity, COLLISION_WORLD_POLYGON_SLIDEZP)
											Case 4,"backward": EntityType(m\Entity, COLLISION_WORLD_POLYGON_SLIDEZN)
											Default: EntityType(m\Entity, COLLISION_WORLD_POLYGON)
											End Select
										Case "slowmesh"
											EntityType(m\Entity, COLLISION_WORLD_POLYGON_SLOW)
										Default:
											EntityType(m\Entity, COLLISION_WORLD_POLYGON)
									End Select
									EntityPickMode(m\Entity, 2, True)
								End If

								EntityShininess(m\Entity, 0)

								; Repetition
								If xmlNodeAttributeValueGet(SceneChildNode, "repeat") Then
									m\ShallRepeat=1
									m\RepeatDistance#=xmlNodeAttributeValueGet(SceneChildNode, "repeat")
								EndIf

								; Setup position, rotation and scale.
								ScenePosition = xmlNodeFind("position", SceneChildNode)
								If (ScenePosition<>0) Then PositionEntity(m\Entity, Float(xmlNodeAttributeValueGet(ScenePosition, "x")), Float(xmlNodeAttributeValueGet(ScenePosition, "y")), Float(xmlNodeAttributeValueGet(ScenePosition, "z")))
								m\InitialPosX#=EntityX(m\Entity,1) : m\InitialPosY#=EntityY(m\Entity,1) : m\InitialPosZ#=EntityZ(m\Entity,1)
								SceneRotation = xmlNodeFind("rotation", SceneChildNode)
								If (SceneRotation<>0) Then RotateEntity(m\Entity, xmlNodeAttributeValueGet(SceneRotation, "pitch"), xmlNodeAttributeValueGet(SceneRotation, "yaw"), xmlNodeAttributeValueGet(SceneRotation, "roll"))
								SceneScale = xmlNodeFind("scale", SceneChildNode)
								If (SceneScale<>0) Then ScaleEntity(m\Entity, Float(xmlNodeAttributeValueGet(SceneScale, "x")), Float(xmlNodeAttributeValueGet(SceneScale, "y")), Float(xmlNodeAttributeValueGet(SceneScale, "z")))

								; Attributes
								SceneAttributes = xmlNodeFind("attributes", SceneChildNode)
								If (SceneAttributes <> 0) Then
									SceneAttributesColor = xmlNodeFind("color", SceneAttributes)
									If (SceneAttributesColor <> 0) Then EntityColor(m\Entity, Float(xmlNodeAttributeValueGet(SceneAttributesColor, "r")), Float(xmlNodeAttributeValueGet(SceneAttributesColor, "g")), Float(xmlNodeAttributeValueGet(SceneAttributesColor, "b")))

									SceneAttributesAnimTexType = xmlNodeFind("animtex", SceneAttributes)
									If (SceneAttributesAnimTexType <> 0) Then m\AnimTexType=xmlNodeAttributeValueGet(SceneAttributesAnimTexType, "type") 

									Select m\AnimTexType
										Case 1:
											SceneAttributesDiffuseTex = xmlNodeFind("diffuse", SceneAttributes)
											If SceneAttributesDiffuseTex<>0 Then
												If xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "alpha")=1 Then
													m\DiffuseTexture[1]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture1"), 1+2)
												Else
													m\DiffuseTexture[1]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture1"), 1)
												EndIf

												SceneAttributesBumpTex = xmlNodeFind("bump", SceneAttributes)
												If SceneAttributesBumpTex<>0 and Menu\Settings\BumpMaps#>0 Then
													EntityTexture(m\Entity, m\DiffuseTexture[1], 0, 1)
													bumptex=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesBumpTex, "texture"), 9)
													If bumptex<>0 Then
														ScaleTexture(bumptex, 3,3)
														TextureBlend(bumptex, FE_BUMPLUM)
														EntityTexture(m\Entity, bumptex, 0, 0)
														FreeTexture(bumptex)
													EndIf
												Else
													EntityTexture(m\Entity, m\DiffuseTexture[1])
												EndIf
											EndIf
									End Select
									Select m\AnimTexType
										Case 1,3
											SceneAttributesScrollTex = xmlNodeFind("scroll", SceneAttributes)
											If (SceneAttributesScrollTex <> 0) Then
												m\ScrollSpeedX#=Float(xmlNodeAttributeValueGet(SceneAttributesScrollTex, "x"))
												m\ScrollSpeedY#=Float(xmlNodeAttributeValueGet(SceneAttributesScrollTex, "y"))
											EndIf
									End Select
									Select m\AnimTexType
										Case 2,3:
											SceneAttributesDiffuseTex = xmlNodeFind("diffuse", SceneAttributes)
											If SceneAttributesDiffuseTex<>0 Then
												If xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "alpha")=1 Then
													m\DiffuseTexture[1]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture1"), 1+2)
													m\DiffuseTexture[2]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture2"), 1+2)
													m\DiffuseTexture[3]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture3"), 1+2)
													m\DiffuseTexture[4]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture4"), 1+2)
												Else
													m\DiffuseTexture[1]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture1"), 1)
													m\DiffuseTexture[2]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture2"), 1)
													m\DiffuseTexture[3]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture3"), 1)
													m\DiffuseTexture[4]=LoadTexture(Path$+xmlNodeAttributeValueGet(SceneAttributesDiffuseTex, "texture4"), 1)
												EndIf
											EndIf

											SceneAttributesChangeTex = xmlNodeFind("change", SceneAttributes)
											If (SceneAttributesChangeTex <> 0) Then
												m\ChangeTexOrder=1
												m\ChangeTexSpeed#=Float(xmlNodeAttributeValueGet(SceneAttributesChangeTex, "timer"))
											EndIf

											EntityTexture(m\Entity, m\DiffuseTexture[1])
									End Select
								End If


							Case "light"
								; Update progress bar
								Game_Stage_UpdateProgressBar("Loading scene light "+j, Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
								
								; Retrieve mesh properties information
								ls.LightSource = New LightSource
								ls\Source = 0
								LightType$	= xmlNodeAttributeValueGet(SceneChildNode, "type")
								Select LightType$
									Case "directional"
										ls\Source = CreateLight(1)
									Case "point"
										ls\Source = CreateLight(2)
									Case "spot"
										ls\Source = CreateLight(3)
								End Select
								LightRange(ls\Source, Float(xmlNodeAttributeValueGet(SceneChildNode, "range")))
								
								; Setup position, rotation and scale.
								LightPosition = xmlNodeFind("position", SceneChildNode)
								If (LightPosition<>0) Then PositionEntity(ls\Source, Float(xmlNodeAttributeValueGet(LightPosition, "x")), Float(xmlNodeAttributeValueGet(LightPosition, "y")), Float(xmlNodeAttributeValueGet(LightPosition, "z")))
								LightRotation = xmlNodeFind("rotation", SceneChildNode)
								If (LightRotation<>0) Then RotateEntity(ls\Source, xmlNodeAttributeValueGet(LightRotation, "pitch"), xmlNodeAttributeValueGet(LightRotation, "yaw"), xmlNodeAttributeValueGet(LightRotation, "roll"))
								LightCol = xmlNodeFind("color", SceneChildNode)
								If (LightCol<>0) Then LightColor(ls\Source, xmlNodeAttributeValueGet(LightCol, "r"), xmlNodeAttributeValueGet(LightCol, "g"), xmlNodeAttributeValueGet(LightCol, "b"))

							Case "object"

								; Find out wich kind of object is and act consecuently
								Select xmlNodeAttributeValueGet(SceneChildNode, "type")

									Case "player"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Player", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))

										ScenePlayerPosition = xmlNodeFind("position", SceneChildNode)
										If (ScenePlayerPosition<>0) Then
											Game\Stage\Properties\StartX# = Float(xmlNodeAttributeValueGet(ScenePlayerPosition, "x"))
											Game\Stage\Properties\StartY# = Float(xmlNodeAttributeValueGet(ScenePlayerPosition, "y"))
											Game\Stage\Properties\StartZ# = Float(xmlNodeAttributeValueGet(ScenePlayerPosition, "z"))
											Game\Stage\Properties\StartDirection# = Float(xmlNodeAttributeValueGet(ScenePlayerPosition, "dir"))
											If Game\Stage\Properties\StartDirection#<0 Then Game\Stage\Properties\StartDirection#=Game\Stage\Properties\StartDirection#+360
										End If
										Game\Gameplay\CheckX#=Game\Stage\Properties\StartX#
										Game\Gameplay\CheckY#=Game\Stage\Properties\StartY#
										Game\Gameplay\CheckZ#=Game\Stage\Properties\StartZ#
										Game\Gameplay\CheckDirection#=Game\Stage\Properties\StartDirection#

										If Menu\Members>2 Then p3.tPlayer = Player_Create(3)
										If Menu\Members>1 Then p2.tPlayer = Player_Create(2)
										p1.tPlayer = Player_Create(1)
										;num = Rand(1,20)
										If (Menu\Stage <997 And Menu\BuddyChaoNumber>0) Then
											;Menu\HeldChaoNumber = num
											LoadGame_MenuChao(Menu\BuddyChaoNumber,1)
											p1\Objects\FollowerChao	= Object_CreateRaceChao_Stage(1, Game\Stage\Properties\StartX#, Game\Stage\Properties\StartY#+7, Game\Stage\Properties\StartZ#)
											LoadChaoVoices()
										EndIf
										ch.tCheese = Object_Cheese_Create.tCheese()
										f.tFroggy = Object_Froggy_Create.tFroggy()
										Player_Spawn(Game\Gameplay\CheckX#,Game\Gameplay\CheckY#,Game\Gameplay\CheckZ#,Game\Gameplay\CheckDirection#)

										If Menu\Mission=MISSION_ESCAPE# Then CreateSprinkler(xmlNodeAttributeValueGet(ScenePlayerPosition, "chaser"))
									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "rival","rivalrun","rival1","rivalrun1","rival2","rivalrun2","rival3","rivalrun3","rival4","rivalrun4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rival", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										Select xmlNodeAttributeValueGet(SceneChildNode, "type")
											Case "rival": TempAttribute\ObjectNo=-10
											Case "rivalrun": TempAttribute\ObjectNo=-20
											Case "rival1": TempAttribute\ObjectNo=-11
											Case "rivalrun1": TempAttribute\ObjectNo=-21
											Case "rival2": TempAttribute\ObjectNo=-12
											Case "rivalrun2": TempAttribute\ObjectNo=-22
											Case "rival3": TempAttribute\ObjectNo=-13
											Case "rivalrun3": TempAttribute\ObjectNo=-23
											Case "rival4": TempAttribute\ObjectNo=-14
											Case "rivalrun4": TempAttribute\ObjectNo=-24
										End Select
										GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										If Game\RivalAmount<3 and Menu\Mission=MISSION_RIVAL# Then CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "ring","ring2","bring2","bigring2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "spring","spring1","spring2","spring3","spring4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "bspring","bspring1","bspring2","bspring3","bspring4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BSPRING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)

										CreateObject()

									Case "springx"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINGX
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "springt","springtrap","tspring","trapspring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Trap Spring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINGTRAP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "springtx","springtrapx","tspringx","trapspringx"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Trap Spring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINGTRAPX
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "pad","dashpad"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Dash Panel", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PAD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "ramp","dashramp"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Dash Ramp", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RAMP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "hoop","dashhoop"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Dash Hoop", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HOOP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "thoop","trickhoop","rainbowhoop","rhoop"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rainbow Hoop", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_THOOP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "accel","accelerator"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Accelerator", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ACCEL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "lock","locker"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Locker", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LOCKER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(0)

									Case "lockb","lockerb","lock2","locker2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Locker", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LOCKER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(1)

									Case "lockbb","lockerbb","lock3","locker3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Locker", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LOCKER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(2)

									Case "force","forcer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Forcer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FORCER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(0)

									Case "forceb","forcerb","force2","forcer2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Forcer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FORCER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(1)

									Case "node"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Node", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_NODE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "node2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Node", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_NODE2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "fan"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fan", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FAN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "bfan"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fan", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BFAN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "bfan2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fan", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BFANLOW
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "fani"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fan", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FAN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(1)

									Case "bfani"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fan", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BFAN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(1)

									Case "bfan2i"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fan", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BFANLOW
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_LOCK, SceneChildNode)
										GetAttribute(ATTRIB_CAM, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(1)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "check","checkpoint"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Star Post", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CHECK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "bcheck","bcheckpoint","check2","checkpoint2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Star Post", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CHECK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "bbcheck","bbcheckpoint","check3","checkpoint3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Star Post", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CHECK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(3)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "monitor","rings","amonitor","arings","level1","level2","level3","alevel1","alevel2","alevel3","bring","bigring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rings Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RINGS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "life","1up","alife","a1up","blast","ablast"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Life Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LIFE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "trap","eggmanmonitor","atrap","aeggmanmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Trap Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRAP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "invinc","invincibility","ainvinc","ainvincibility"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Invincibility Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_INVINC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "shoes","speedshoes","ashoes","aspeedshoes"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Speed Shoes Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHOES
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "shield","nshield","normalshield","gshield","greenshield","ashield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Normal Shield Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_NSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "fshield","flameshield","rshield","redshield","afshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flame Shield Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "bshield","bubbleshield","blueshield","abshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bubble Shield Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "tshield","thundershield","wshield","whiteshield","atshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Thunder Shield Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "eshield","earthshield","yshield","yellowshield","aeshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Earth Shield Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ESHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "bomb","bombmonitor","abomb"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bomb Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOMB
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "board","boardmonitor","aboard"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Board Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOARD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "glider","glidermonitor","aglider"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Glider Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GLIDER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "car","carmonitor","acar"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Car Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CAR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "bike","bikemonitor","abike"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bike Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BIKE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "bobsleigh","bobsleighmonitor","abobsleigh"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bobsleigh Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOBSLEIGH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "tornado","tornadomonitor","atornado"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Tornado Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TORNADO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "cyclone","cyclonemonitor","acyclone"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cyclone Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CYCLONE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "kart","kartmonitor","akart"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Kart Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_KART
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									Case "wings","wingsmonitor","awings"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Wings Item Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WINGS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(0)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "bmonitor","brings","blevel1","blevel2","blevel3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rings Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RINGS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "blife","b1up","bblast"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Life Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LIFE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "btrap","beggmanmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Trap Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRAP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "binvinc","binvincibility"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Invincibility Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_INVINC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bshoes","bspeedshoes"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Speed Shoes Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHOES
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bnshield","bnormalshield","bgshield","bgreenshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Normal Shield Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_NSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bfshield","bflameshield","brshield","bredshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flame Shield Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bbshield","bbubbleshield","bblueshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bubble Shield Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "btshield","bthundershield","bwshield","bwhiteshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Thunder Shield Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "beshield","bearthshield","byshield","byellowshield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Earth Shield Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ESHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bbomb","bbombmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bomb Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOMB
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bboard","bboardmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Board Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOARD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bglider","bglidermonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Glider Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GLIDER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bcar","bcarmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Car Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CAR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bbike","bbikemonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bike Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BIKE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bbobsleigh","bbobsleighmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bobsleigh Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOBSLEIGH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "btornado","btornadomonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Tornado Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TORNADO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bcyclone","bcyclonemonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cyclone Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CYCLONE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bkart","bkartmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Kart Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_KART
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									Case "bwings","bwingsmonitor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Wings Item Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WINGS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject(1)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "balloon","balloo","ballo"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BALLOON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "spike","spikeb","spikeball"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Ball", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKEBALL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spikebo","spikebomb"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Bomb", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKEBOMB
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spikecr","spikecrush","spikecrushr","spikecrusher","spikebo2","spikebomb2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Bomb", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKECRUSHER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spikes","spiked","spikedrill","spikedrills"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Drills", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKEDRILL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spiket","spiketi","spiketimed","spikedrilltimed","spikedrillstimed"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Drills", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKETIMED
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spiketr","spiketrap","spikedrilltrap","spikedrillstrap"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Drills", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKETRAP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spikeba","spikebar","spikeba1","spikebar1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Bar", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKEBAR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "spikeba2","spikebar2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Bar", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKEBAR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "spikeba3","spikebar3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Bar", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKEBAR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(3)

									Case "spikesw","spikeswing","spikesw1","spikeswing1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Swing", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKESWING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "spikesw2","spikeswing2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Swing", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKESWING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "spikesw3","spikeswing3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Swing", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKESWING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(3)

									Case "spikeswx","spikeswingx"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Swing Ball", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKESWINGBALL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "spikecyl","spikecylinder"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spike Cylinder", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKECYLINDER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "goal","goalring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Goal Ring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GOAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)

										Select Menu\Mission
											Case MISSION_RIVAL#,MISSION_CARNIVAL#,MISSION_BOSS#,MISSION_CAPSULE#:
											Default: CreateObject()
										End Select

									Case "goal2","goalring2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Goal Ring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GOAL2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)

										Select Menu\Mission
											Case MISSION_RIVAL#,MISSION_CARNIVAL#,MISSION_BOSS#,MISSION_CAPSULE#:
											Default: CreateObject()
										End Select

									Case "hubgoal","hubgoalring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Goal Ring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GOAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										Select Menu\Mission
											Case MISSION_RIVAL#,MISSION_CARNIVAL#,MISSION_BOSS#,MISSION_CAPSULE#:
											Default: CreateObject(1)
										End Select

									Case "hubgoal2","hubgoalring2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Goal Ring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GOAL2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										Select Menu\Mission
											Case MISSION_RIVAL#,MISSION_CARNIVAL#,MISSION_BOSS#,MISSION_CAPSULE#:
											Default: CreateObject(1)
										End Select

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "fspout","flamespout","spout","flametrap","ftrap","flame","flamer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flame Spout", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLAMESPOUT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "ispout","icespout","icetrap","itrap","ice","icer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ice Spout", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICESPOUT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "sspout","shockspout","shocktrap","strap","shock","shocker"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Shock Spout", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHOCKSPOUT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "laser","laserv"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Laser", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LASERV
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										CreateObject()

									Case "laserh"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Laser", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LASERH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										CreateObject()

									Case "laser2","laser2v","ringgate","ringgatev"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ring Gate", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RINGGATEV
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "laser2h","ringgateh"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ring Gate", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RINGGATEH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "cbox","cagebox","boxc"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cage Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXCAGE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "ibox","ironbox","boxi"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Iron Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXIRON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "mbox","metalbox","boxm"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Metal Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXMETAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "wbox","woodenbox","woodbox","boxw","box"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Wooden Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXWOODEN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "lbox","lightbox","boxl"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Light Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXLIGHT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										CreateObject(0)

									Case "tntbox","boxtnt"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": TNT Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXTNT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "nbox","nitrobox","boxn"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Nitro Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXNITRO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "fbox","floatbox","boxf"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Float Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXFLOAT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(0)

									Case "bcbox","bcagebox","bboxc"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cage Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXCAGE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "bibox","bironbox","bboxi"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Iron Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXIRON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "bmbox","bmetalbox","bboxm"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Metal Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXMETAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "bbox","bwbox","bwoodenbox","bwoodbox","bboxw"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Wooden Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXWOODEN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "blbox","blightbox","bboxl"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Light Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXLIGHT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										CreateObject(1)

									Case "btntbox","bboxtnt"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": TNT Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXTNT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "bnbox","bnitrobox","bboxn"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Nitro Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXNITRO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "bfbox","bfloatbox","bboxf"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Float Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXFLOAT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "mcbox","mcagebox","mboxc"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cage Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXCAGE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "mibox","mironbox","mboxi"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Iron Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXIRON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "mmbox","mmetalbox","mboxm"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Metal Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXMETAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "mwbox","mwoodenbox","mwoodbox","mboxw"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Wooden Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXWOODEN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "mlbox","mlightbox","mboxl"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Light Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXLIGHT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										CreateObject(2)

									Case "mtntbox","mboxtnt"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": TNT Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXTNT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "mnbox","mnitrobox","mboxn"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Nitro Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXNITRO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									Case "mfbox","mfloatbox","mboxf"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Float Box", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOXFLOAT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(2)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "ballbumper","bumpball","bbumper","bbump"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ball Bumper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BALLBUMPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "groundbumper","groundbump","gbumper","gbump"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ground Bumper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GROUNDBUMPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "metrobumper","metrobump","mbumper","mbump"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Metro Bumper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_METROBUMPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "platebumper","platebump","pbumper","pbump"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plate Bumper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PLATEBUMPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "trianglebumper","trianglebump","tbumper","tbump"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Triangle Bumper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIANGLEBUMPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject()

									Case "paddle","paddle1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Paddle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PADDLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(0)

									Case "paddle2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Paddle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PADDLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(1)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "pawn1","eggpawn1","pawn","eggpawn","pawn1g","pawn1m"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pawn", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PAWN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "pawn2","eggpawn2","pawnshield","eggpawnshield","pawn2g","pawn2m"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pawn", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PAWNSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "pawn3","eggpawn3","pawngun","eggpawngun","pawn3g","pawn3m"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pawn", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PAWNGUN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "pawn4","eggpawn4","pawnsword","eggpawnsword","pawn4g","pawn4m"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pawn", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PAWNSWORD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "flapr1","flapper1","eggflapr1","eggflapper1","flapr","flapper","eggflapr","eggflapper","flapr1g","flapr1m"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flapper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLAPPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "flapr2","flapper2","eggflapr2","eggflapper2","flaprgun","flappergun","eggflaprgun","eggflappergun"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flapper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLAPPERGUN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "flapr3","flapper3","eggflapr3","eggflapper3","flaprbomb","flapperbomb","eggflaprbomb","eggflapperbomb"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flapper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLAPPERBOMB
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "flapr4","flapper4","eggflapr4","eggflapper4","flaprneedle","flapperneedle","eggflaprneedle","eggflapperneedle"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Flapper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLAPPERNEEDLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "spina","spina1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spina", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPINA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "spana","spina2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spana", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPANA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "spona","spina3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spona", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPONA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "motobug","moto"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Motobug", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_MOTOBUG
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "caterkiller","cater"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Caterkiller", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CATERKILLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "buzz1","buzzbomber","buzz"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Buzz Bomber", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUZZBOMBER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "buzz2","buzzer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Buzzer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUZZER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "chopr","chopper","choppr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Chopper", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CHOPPER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "crab","crabmeat"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Crabmeat", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CRABMEAT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "jaws"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Jaws", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_JAWS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "spiny"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spiny", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPINY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "grabr","grabber","grabbr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Grabber", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRABBER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "kiki"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Kiki", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_KIKI
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "cop","copspeedr","copspeeder","cop1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cop Speeder", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "cop2","copracr","copracer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cop Racer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COPRACER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "huntr1","gunhunter","gunhunter1","hunter","hunter1","gunh","gunh1","gunhuntr","gunhuntr1","huntr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hunter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HUNTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "huntr2","gunhunter2","hunter2","gunh2","gunhuntr2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hunter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HUNTERSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "beetle1","gunbeetle","gunbeetle1","beetle","gunb","gunb1","gunbg"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Beetle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BEETLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "beetle2","gunbeetle2","gunb2","gunbeetlemono","beetlemono","gunbmono","gunbeetlem","beetlem","gunbm"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Beetle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BEETLEMONO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "beetle3","gunbeetle3","gunb3","gunbeetlespark","beetlespark","gunbspark","gunbeetles","beetles","gunbs"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Beetle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BEETLESPARK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "beetle4","gunbeetle4","gunb4","gunbeetlespring","beetlespring","gunbspring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Beetle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BEETLESPRING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "achaos1","achaos"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Artificial Chaos", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ACHAOS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "achaos2","achaosblob"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Artificial Chaos", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ACHAOSBLOB
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "rhino1","rhino"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rhino", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RHINO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "rhino2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rhino", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RHINOSPIKES
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "hornet3","hornet2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hornet", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HORNET3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "hornet6","hornet","hornet1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hornet", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HORNET6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "aeroc","aero","aerocannon"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Aero Cannon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_AEROC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "chasr","chaser"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Chaser", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CHASER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "figtr","fightr","fighter"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fighter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FIGHTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "eggrobo"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": EggRobo", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_EGGROBO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "cameron","eggcameron"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cameron", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CAMERON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "klagen","klag","eggklagen","eggklag"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Klagen", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_KLAGEN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "orbi","orbinaut"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Orbinaut", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ORBINAUT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "typh1","typh","typhoon1","typhoon"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Typhoon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TYPHOON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "typh2","typhoon2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Typhoon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TYPHOONF
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "anton"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Anton", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ANTON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "aquis"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Aquis", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_AQUIS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "bombie"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bombie", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOMBIE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "newtron"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Newtron", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_NEWTRON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "penguin","penguinator"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Penguinator", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PENGUINATOR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "slicer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Slicer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SLICER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "snailb","snailblastr","snailblaster"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Snail Blaster", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNAILB
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "spikess"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Spikes", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPIKES
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "asteron"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Asteron", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ASTERON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "bat","batbot"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Batbot", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BATBOT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "bubbls1","bubbls","bubbles1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bubbles", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUBBLS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "bubbls2","bubbles2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bubbles", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUBBLSSPIKES
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "steelion"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Steelion", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_STEELION
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "balki","balkiry"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Balkiry", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BALKIRY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "burro","burrobot"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Burrobot", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BURROBOT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "crawl"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Crawl", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CRAWL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "dragonfly"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Dragonfly", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_DRAGONFLY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "madmole"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Madmole", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_MADMOLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "manta"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Manta", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_MANTA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "mush","mushmeanie"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Mushmeanie", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_MUSHMEANIE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "octus"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Octus", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_OCTUS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "pata","patabata"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pata-Bata", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PATABATA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "zoomr","zoomer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Zoomer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ZOOMER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "biter","bitr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Iblis Biter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BITER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "crawlr","crawler"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Iblis Crawler", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CRAWLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "taker","takr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Iblis Taker", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TAKER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "e1000"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": E-1000", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_E1000
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "ballh","ballhog"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ball Hog", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BALLHOG
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "rhinot","rhinotank"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rhinotank", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RHINOTANK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "technosqu","technosqueek"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": TechnoSqueek", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TECHNOSQU
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "warr","warrior"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Warrior", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WARRIOR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "warr1","warrior1","warrgun","warrgun1","warriorgun","warriorgun1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Warrior", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WARRIORGUN1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "warr2","warrior2","warrgun2","warriorgun2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Warrior", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WARRIORGUN2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "oak","oaksword"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Oak", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_OAKSWORD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "leech"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Leech", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_LEECH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "wing"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Wing", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "soldier1","soldier","gunsoldier1","gunsoldier"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Soldier", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SOLDIER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "soldier2","gunsoldier2","soldiercamo","gunsoldiercamo"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Soldier", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SOLDIERCAMO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "catakiller","catakillerjr","cata","catajr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Catakiller Jr.", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CATAKILLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "cluck","cluckoid"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cluckoid", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CLUCKOID
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "mantis"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Mantis", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_MANTIS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "nebula"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Nebula", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_NEBULA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "roller","rollr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Roller", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ROLLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "sheep"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sheep", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHEEP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "snowy"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Snowy", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "splats"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Splats", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPLATS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "toxo","toxomister"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Toxomister", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TOXO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "hammr1","egghammr1","hammr","egghammr","hammer1","egghammer1","hammer","egghammer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hammer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HAMMER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "hammr2","egghammr2","hammrshield","egghammrshield","hammer2","egghammer2","hammershield","egghammershield"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hammer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HAMMERSHIELD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "hammr3","egghammr3","hammrhammr","egghammrhammr","hammer3","egghammer3","hammerhammer","egghammerhammer"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hammer", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HAMMERHAMMER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "witch1","witch","bishop"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bishop", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WITCH1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "witch2","magician"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Magician", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WITCH2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "fcannon1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fortress Cannon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FCANNON1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "fcannon2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fortress Cannon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FCANNON2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "fcannon3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Fortress Cannon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FCANNON3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "enemy"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Enemy", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=Rand(OBJTYPE_PAWN,OBJTYPE_ENEMYCOUNT)
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "boo1","boo"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Boo", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "boo2","booscare"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Boo", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOOSCARE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "ghost"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Ghost Pumpkin", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GHOST
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)
										
										CreateObject()

									Case "boss"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Eggman Boss", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOSS
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										If Menu\Mission=MISSION_BOSS# Then CreateObject()

									Case "boss2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Eggman Boss", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOSS2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										If Menu\Mission=MISSION_BOSS# Then CreateObject()

									Case "bossrun"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Eggman Boss", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOSSRUN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										If Menu\Mission=MISSION_BOSS# Then CreateObject()

									Case "bossbeta","bossbeta2","bossbetamk2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Beta mk.2 Boss", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOSSBETA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										If Menu\Mission=MISSION_BOSS# Then CreateObject()

									Case "bossmecha","bossmechasonic"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Mecha Sonic Boss", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOSSMECHA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										
										If Menu\Mission=MISSION_BOSS# Then CreateObject()
									Case "bossrobo","bossrobosonic"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Mecha Sonic MK1 Boss", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOSSROBO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCHOBJ, SceneChildNode)
										GetAttribute(ATTRIB_CARNIVAL, SceneChildNode)

										If Menu\Mission=MISSION_BOSS# Then CreateObject()
									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "bombr1","bomber1","bombr","bomber"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bomber", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOMBER1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "bombr2","bomber2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bomber", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BOMBER2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "bubbles","bubble","bubls","bubl","bubbls","bubbl","bubblecreator","bubblcreator","bublcreator","breathbubbles","breathbubbl","breathbubbls"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breath Bubbles", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUBBLES
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "redring","rring","starring","redstarring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Red Star Ring", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_REDRING
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "teleporter","teleportr"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									Case "hubteleporter","hubteleportr","teleporter2","teleportr2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": HUB Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTER2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									Case "teleporterend","teleportrend"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTEREND
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									Case "bmteleporter","bmteleportr","teleporter3","teleportr3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Black Market Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTER3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									Case "tteleporter","tteleportr","teleporter4","teleportr4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Chao Transporter Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTER4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									Case "steleporter","steleportr","teleporter5","teleportr5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Chao Stadium Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTER5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									Case "pteleporter","pteleportr","teleporter6","teleportr6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Principal Room Teleporter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TELEPORTER6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_TELEPORTER, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "omochao"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Omochao", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_OMOCHAO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "cannon","aircannon"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cannon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CANNON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "propeller","propllr","propl"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Propeller", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PROPELLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "pulley","plly"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pulley", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PULLEY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "pulley2","plly2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pulley", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PULLEY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject(1)

									Case "rocket"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rocket Machine", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ROCKET
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "elev","elevator"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Elevator", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ELEVATOR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									Case "handle","handl"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Handle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HANDLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "fplat","fplatform","fallingplatform","plat","platform"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Falling Platform", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FPLAT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "switch"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Switch", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SWITCH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCH, SceneChildNode)

										CreateObject()

									Case "switchbase"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Switch Base", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SWITCHBASE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCH, SceneChildNode)

										CreateObject()

									Case "switchtop"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Switch Top", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SWITCHTOP
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCH, SceneChildNode)

										CreateObject()

									Case "switchwa","switchwater","switchw"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Switch", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SWITCHWATER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject()

									Case "switchwai","switchwateri","switchwi"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Switch", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SWITCHWATER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject(1)

									Case "switchair","switch2","switcha"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Switch", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SWITCHAIR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_SWITCH, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "rock","rock1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Rock", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ROCK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(1)

									Case "rock2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Rock", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ROCK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(2)

									Case "crystal"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Crystal", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CRYSTAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject()

									Case "icicle1","icicle"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Icicle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICICLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(1)

									Case "icicle2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Icicle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICICLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(2)

									Case "iciclebig1","iciclebig"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Icicle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICICLEBIG
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(1)

									Case "iciclebig2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Icicle", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICICLEBIG
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(2)

									Case "icedecor1","icedecor"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Ice Decor", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICEDECOR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(1)

									Case "icedecor2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Breakable Ice Decor", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ICEDECOR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(2)

									Case "auto"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Automobile", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_AUTO
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "hint","hintring"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Hint", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HINT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_HINT, SceneChildNode)

										CreateObject()

									Case "counter5","counter"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Counter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COUNTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject(5)

									Case "counter4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Counter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COUNTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject(4)

									Case "counter3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Counter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COUNTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject(3)

									Case "counter2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Counter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COUNTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject(2)

									Case "counter1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Counter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_COUNTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)

										CreateObject(1)

									Case "sign","signfall"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sign", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SIGN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(1)

									Case "signup"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sign", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SIGN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(2)

									Case "signdown"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sign", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SIGN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(3)

									Case "signleft"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sign", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SIGN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(4)

									Case "signright"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sign", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SIGN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(5)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "tropical","tropic"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Tropical Tree", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TROPICAL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "trashcan"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Trash Can", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRASHCAN
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "sack"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sack", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SACK
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "gardenpoint","gardenstartpoint"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Garden Start Point", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GARDENPOINT
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "bell"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Bell", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BELL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "sprinkler","sprinkler1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Particle Sprinkler", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINKLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)

										CreateObject(1)

									Case "sprinkler2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Particle Sprinkler", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINKLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)

										CreateObject(2)

									Case "sprinkler3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Particle Sprinkler", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINKLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(3)

									Case "drops"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Particle Sprinkler", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINKLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)

										CreateObject(4)

									Case "sprinklerx","sprinkler1x"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Particle Sprinkler", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SPRINKLER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)

										CreateObject(5)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "butterfly"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Butterfly", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUTTERFLY
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "seagull"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Seagull", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SEAGULL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "seac"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Sea Creatures", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SEAC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "orca"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Orca", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ORCA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "dolphin"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Dolphin", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ORCA
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject(1)

									Case "chair"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Chair", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CHAIR
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "parasol"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Parasol", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PARASOL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "airballoon"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Air Balloon", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_AIRBALLOON
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "helicopter"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Helicopter", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_HELICOPTER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "rainbow"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Rainbow", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_RAINBOW
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										CreateObject()

									Case "cloud"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Cloud", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CLOUD
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "pole"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Pole", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_POLE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "vehiclecancel","boardcancel","glidercancel"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Vehicle Cancelling Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_VEHICLECANCEL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "mach"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Mach Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_MACH
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "machcancel"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Mach Cancelling Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_MACHCANCEL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "skydive"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Skydive Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_SKYDIVE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "skydivecancel"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Skydive Cancelling Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_SKYDIVECANCEL
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "water"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Water Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_WATER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "music1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Music Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_MUSIC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "music2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Music Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_MUSIC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject(1)

									Case "music3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Music Trigger", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TRIGGER_MUSIC
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject(2)

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "explosion"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Explosion", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_EXPLOSION
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									Case "explosion2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Explosion", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_EXPLOSION2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										
										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "capsule"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Capsule", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_CAPSULE
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										
										If Menu\Mission=MISSION_CAPSULE# Then CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "repeater","repeat"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Repeater", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_REPEATER
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										GetAttribute(ATTRIB_DESTINATION, SceneChildNode)
										GetAttribute(ATTRIB_HINT, SceneChildNode)

										CreateObject()

									;======================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

									Case "tree1","tree"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TREE1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "tree2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TREE2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "tree3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TREE3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "tree4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TREE4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "tree5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TREE5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "tree6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_TREE6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "shrub1","shrub"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHRUB1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "shrub2","cactus","cactus1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHRUB2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "shrub3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHRUB3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "shrub4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHRUB4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "shrub5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHRUB5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "shrub6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SHRUB6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush1","bush"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "bush7","tree1bush","treebush"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_BUSH7
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass1","grass"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass7"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS7
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass8"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS8
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass9"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS9
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "grass10"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_GRASS10
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "sakura1","sakura"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SAKURA1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "sakura2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SAKURA2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "sakura3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SAKURA3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "sakura4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SAKURA4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "sakura5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SAKURA5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "sakura6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SAKURA6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "palm1","palm"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PALM1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "palm2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PALM2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "palm3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PALM3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "palm4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_PALM4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "wildpalm1","wildpalm"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WILDPALM1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "wildpalm2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WILDPALM2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "wildpalm3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WILDPALM3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "wildpalm4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WILDPALM4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "wildpalm5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WILDPALM5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "wildpalm6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_WILDPALM6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "flower1","flower"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLOWER1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "flower2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLOWER2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "flower3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLOWER3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "flower4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLOWER4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "flower5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_FLOWER5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "snowy1","snowy"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "snowy2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "snowy3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "snowy4"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "snowy5"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "snowy6"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_SNOWY6
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "vine1","vine"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_VINE1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "drytree1","drytree"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_DRYTREE1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "drytree2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_DRYTREE2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "drytree3"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_DRYTREE3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "adabat1","adabatflower"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ADABAT1
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "adabat2","adabatbush"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ADABAT2
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "adabat3","banana"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ADABAT3
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "adabat4","adabatpalm","adabatpalm1"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ADABAT4
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

									Case "adabat5","adabatpalm2"
										; Update progress bar
										Game_Stage_UpdateProgressBar("Loading object n."+j+": Plant", Float#(j)/Float#(xmlNodeChildCount(RootChildNode)))
										TempAttribute\ObjectNo=OBJTYPE_ADABAT5
										GetAttribute(ATTRIB_AMOUNT, SceneChildNode) : GetAttribute(ATTRIB_POSITION, SceneChildNode)
										GetAttribute(ATTRIB_ROTATION, SceneChildNode)
										GetAttribute(ATTRIB_POWER, SceneChildNode)
										
										CreateObject()

								End Select
						End Select 
					Next
	
			End Select 
		Next
		xmlNodeDelete(RootNode)

		; Create chao and load chao garden, and inventory
		If Menu\ChaoGarden=1 Then
			Select Menu\Stage
			Case 999:
				Game_Stage_UpdateProgressBar("Creating chao", 1)
				If (FileType(SaveDataPath$+"CHAOGARDEN.dat")=1) Then 
					Game_Stage_UpdateProgressBar("Loading chao garden", 1)
					LoadGame_ChaoGarden()
					Player_Spawn(Game\Gameplay\CheckX#,Game\Gameplay\CheckY#,Game\Gameplay\CheckZ#,Game\Gameplay\CheckDirection#)
				EndIf
				GetShallExplodeInventory()
				For i=1 to CHAOCOUNT : Object_CreateChao(i) : Next
				CHAOFIRSTTIMER(1)=1
				If Menu\SaveChaoName=1 Then
					For cc.tChaoManager=Each tChaoManager
						If cc\Number=Menu\HeldChaoNumber Then cc\Name$=Menu\HeldChaoName$ : SaveGame_Chao(cc)
					Next
					Menu\SaveChaoName=0
				EndIf
				If Menu\SaveChaoCompetitions=1 Then
					For cc.tChaoManager=Each tChaoManager
						If cc\Number=Menu\HeldChaoNumber Then cc\Stats\CompetitionsWon=Menu\HeldChaoCompetitionsWon : cc\Stats\CompetitionsLost=Menu\HeldChaoCompetitionsLost : SaveGame_Chao(cc)
					Next
					Menu\SaveChaoCompetitions=0
				EndIf
			Case 998:
				Object_CreateRaceChao()
			Case 997:
				Object_CreateKarateChao()
			End Select
			LoadChaoVoices()
			Game\Interface\AutoSaveTimer=180*secs#
		EndIf

		; Determine ideal time and score
		Menu_Stage_LoadMissions(Menu\Stage, true)
		h#=0
		For i=1 to Menu\Members
			h2#=GetCharSpeed#(Menu\Character[i])
			If h2#>h# Then h#=h2#
		Next
		Game\LimitTime=Game\LimitTime*(1+(6-h#)/30.0)*secs#
		Game\IdealTime=Game\IdealTime*(1+(6-h#)/30.0)*secs#
		Select Menu\Mission
			Case MISSION_ENEMY#,MISSION_GOLD#:
				Game\LimitTime=Game\LimitTime*1.25
				Game\IdealTime=Game\IdealTime*1.25
			Case MISSION_RING#:
				Game\LimitTime=Game\LimitTime*0.75
				Game\IdealTime=Game\IdealTime*0.75
		End Select

		; Create special stage
		If Menu\ChaoGarden=0 and Menu\Stage<0 Then
			Game_Stage_UpdateProgressBar("Creating special stage", 1)
			CreateSpecialStageContent_Floor(Path$)
		EndIf

		;Reset all objects to appear
		Game_Stage_UpdateProgressBar("Resetting objects", 1)
		For o.tObject=Each tObject
			Objects_Reset_HasMesh(o)
			Objects_Reset_Repose(o)
			o\Done=0
		Next

		;Reset objects
		Game\ResetObjects=1

		; Decide view range
		OBJECT_VIEWDISTANCE_UPDATEDISTANCE#=80
		Select Menu\Settings\ViewRange#
			Case 1: OBJECT_VIEWDISTANCE#=200
			Case 2: OBJECT_VIEWDISTANCE#=400
			Case 3: OBJECT_VIEWDISTANCE#=600
			Case 4: OBJECT_VIEWDISTANCE#=900
			Default: OBJECT_VIEWDISTANCE#=400
		End Select
		Game\SmartCameraRangeDontAffectTimer=3*secs#

		; Fix largest id
		If OBJECT_VIEWDISTANCE_LARGESTIDCOUNT#>OBJECT_VIEWDISTANCE_IDCOUNT# Then OBJECT_VIEWDISTANCE_IDCOUNT#=OBJECT_VIEWDISTANCE_LARGESTIDCOUNT#
	
		; Once finished loading stage information, setup collisions, and then, we're ready to go to next step.
		Game_Stage_UpdateProgressBar("Almost done", 1)
		If Menu\Stage<>0 Then
			Menu\Transition=0
			For p.tPlayer=Each tPlayer
				If p\No#=1 Then
					If Menu\ChaoGarden=0 Or Menu\Stage=999 Then Player_PlayTurnVoice(p)
				EndIf
			Next
			Game\StartoutLock=1*secs#
		Else
			Game\StartoutLock=0
		EndIf

		; Clear unnecessary meshes
		For x = MESHES_ALWAYSTOTAL+1 to MESHES_PLANTSTOTAL : FreeSmartEntity(x) : Next

		DeltaTime_Reset(Game\DeltaTime)
		Game\State = GAME_STATE_STEP

		; Setup collisions within the environment.
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_DEATH, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_HURT, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_RAIL, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_BLOCK, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_PINBALL, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_ICE, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_BOUNCE, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_SLOW, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_SLIDEXP, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_SLIDEXN, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_SLIDEZP, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_WORLD_POLYGON_SLIDEZN, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_PLAYER, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_OBJECT, 2, 2)
		Collisions(COLLISION_PLAYER, COLLISION_OBJECT2, 2, 2)

		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_DEATH, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_HURT, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_BLOCK, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_PINBALL, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_ICE, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_BOUNCE, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_SLOW, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_SLIDEXP, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_SLIDEXN, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_SLIDEZP, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_WORLD_POLYGON_SLIDEZN, 2, 2)
		Collisions(COLLISION_CAMERA, COLLISION_PLAYER, 2, 2)

		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_DEATH, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_HURT, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_RAIL, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_BLOCK, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_PINBALL, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_ICE, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_BOUNCE, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_SLOW, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_SLIDEXP, 2, 2)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_SLIDEXN, 2, 2)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_SLIDEZP, 2, 2)
		Collisions(COLLISION_OBJECT, COLLISION_WORLD_POLYGON_SLIDEZN, 2, 2)
		Collisions(COLLISION_OBJECT, COLLISION_PLAYER, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_OBJECT, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_OBJECT_GOTHRU, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_OBJECT2, 2, 3)
		Collisions(COLLISION_OBJECT, COLLISION_OBJECT2_GOTHRU, 2, 3)

		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_DEATH, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_HURT, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_RAIL, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_BLOCK, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_PINBALL, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_ICE, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_BOUNCE, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_SLOW, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_SLIDEXP, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_SLIDEXN, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_SLIDEZP, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_WORLD_POLYGON_SLIDEZN, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_PLAYER, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_OBJECT, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_OBJECT_GOTHRU, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_OBJECT2, 2, 2)
		Collisions(COLLISION_OBJECT2, COLLISION_OBJECT2_GOTHRU, 2, 2)

		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_DEATH, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_HURT, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_RAIL, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_BLOCK, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_PINBALL, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_ICE, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_BOUNCE, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_SLOW, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEXP, 2, 2)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEXN, 2, 2)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEZP, 2, 2)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEZN, 2, 2)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_PLAYER, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_OBJECT, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_OBJECT_GOTHRU, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_OBJECT2, 2, 3)
		Collisions(COLLISION_OBJECT_GOTHRU, COLLISION_OBJECT2_GOTHRU, 2, 3)

		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_DEATH, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_HURT, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_RAIL, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_BLOCK, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_PINBALL, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_ICE, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_BOUNCE, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_SLOW, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEXP, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEXN, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEZP, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_WORLD_POLYGON_SLIDEZN, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_PLAYER, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_OBJECT, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_OBJECT_GOTHRU, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_OBJECT2, 2, 2)
		Collisions(COLLISION_OBJECT2_GOTHRU, COLLISION_OBJECT2_GOTHRU, 2, 2)

		; Create fade-in transition effect
		PostEffect_Create_FadeIn(0.01, 10, 10, 10)

	FlushAll()
	SetFont MidFont
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_UpdateProgressBar(Message$, t#)

		ClsColor(0,0,0)
		Cls

		StartDraw()

		If Menu\Stage<>0 Then

			; Draw black ground
			DrawImageEx(INTERFACE(Interface_Black), GAME_WINDOW_W/2, GAME_WINDOW_H/2)

			; Draw title card
			Menu\TitleCardTimer=0
			DrawTitleCardStuff()

			; Calculate percentage
			Progression = Int(t#*196.0)

			; Draw message at center
			Color(255, 255, 255)
			If Menu\Settings\Debug#=1 Then Text(GAME_WINDOW_W-100-130, GAME_WINDOW_H-40+10-50, Message$)
			Rect(GAME_WINDOW_W-100-130, GAME_WINDOW_H-25+10-50, 200, 50, False)
			Rect(GAME_WINDOW_W-98-130, GAME_WINDOW_H-23+10-50, Progression, 46, True)

		EndIf

		EndDraw()

		; Flip
		VWait : Flip(GAME_WINDOW_VSYNC)

	End Function