


Type MeshStructure
	Field Entity
	Field AnimTexType
	Field ScrollSpeedX#
	Field ScrollSpeedY#
	Field ChangeTexSpeed#
	Field ChangeTexTimer
	Field ChangeTexOrder
	Field DiffuseTexCount
	Field DiffuseTexture[8]
	Field ShallRepeat
	Field RepeatDistance#
	Field CollisionType
	
	Field ID
	Field CastedShadow
	Field StageShadowTexture
	
	Field MoveType
	
	Field GoDestination
	Field DestinationTarget
	Field MoveSpeed#
	
	Field InitialPosX#
	Field InitialPosY#
	Field InitialPosZ#
	
	Field InitialRotX#
	Field InitialRotY#
	Field InitialRotZ#
	
	Field InitialScaleX#
	Field InitialScaleY#
	Field InitialScaleZ#
	
	Field MoveX#
	Field MoveY#
	Field MoveZ#
	
	Field RotX#
	Field RotY#
	Field RotZ#
	
	Field ScaleX#
	Field ScaleY#
	Field ScaleZ#
	
	Field Pivot
	
	Field RedRingMode
	Field RedRingCount
	
	Field AlphaType
	Field TargetAlpha#
	Field TargetAlphaLower#
	Field TargetAlphaUpper#
	Field Alpha#
	Field AlphaRate
	Field AlphaTimer
	Field ChangeAlphaTimer
	
	Field Path$
	
	Field LodType
	Field LodDistance
	Field LodX#
	Field LodY#
	Field LodZ#
	
	Field IsSwitchMesh
	Field SwitchType
	Field SwitchNo
	
	Field IsRollMesh
	Field CollisionStore
	Field CollisionChanged
End Type




Type LightSource
	Field Source
End Type

Global cam.tCamera

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Dim water_texture(9,13)
Dim water_bump_texture(9)
For h=1 To 9
	Select h
		Case 1: watertype$="water"
		Case 2: watertype$="lava"
		Case 3: watertype$="sea"
		Case 4: watertype$="acid"
		Case 5: watertype$="swamp"
		Case 6: watertype$="sand"
		Case 7: watertype$="oil"
		Case 8: watertype$="pinkwater"
		Case 9: watertype$="paint"
	End Select
	Select h
		Case 6: j=1
		Default: j=13
	End Select
	For i=1 To j
		water_texture(h,i) = LoadTexture("Textures\"+watertype$+"\"+i+".png",1+256)
		Select h
			Case 2,7: ScaleTexture water_texture(h,i), 250,250
			Default: ScaleTexture water_texture(h,i), 50,50
		End Select
	Next
	water_bump_texture(h)=LoadTexture("Textures\"+watertype$+"\Bump.png", 9) 
	Select h
		Case 1,3,5,8,9: TextureBlend(water_bump_texture(h), FE_BUMP)
		Case 2,4,6,7: TextureBlend(water_bump_texture(h), FE_BUMPLUM)
	End Select
Next

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Game_Stage_Start()
	
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
					
					Path$ = StagePath$(Menu\Stage)+"/"
					
				Case 1:
					Select Menu\Stage
						Case 998: Path$ = "ChaoWorld/Chao Races/Race "+Menu\RaceType+"/"
						Case 997: Path$ = "ChaoWorld/Chao Races/Karate/"
						Default: Path$ = "ChaoWorld/Chao Garden/"
					End Select
			End Select
	End Select
	
	If Menu\StartedStageWarp=1 Or Menu\MissionCard=1 Then
		Path$=Menu\WarpRingPath$+"/"
		Menu\CollectionRoom=0
		If Menu\MissionCard=1 Then Menu_GoToStage_SetMission(0)
	ElseIf Menu\TutorialMode=1 Then 
		Path$ = "ChaoWorld/Tutorial/"
	ElseIf Menu\CollectionRoom=1 Then
		Path$ = "ChaoWorld/Collection Room/"
		LoadSmartImage(Interface_HudLeft)
		
	EndIf
	
	Game\Stage\Properties\Path$ = Path$
	
	Input_GamepadThreshold = 0.25
	
	; Reset pause situation
	Menu\Pause=0
	Input\Hold\Start=False
	Input\Pressed\Start=False
	
	
	
	; Reset values
	Game_Stage_UpdateProgressBar("Resetting all values", 0)
	Game_Stage_ResetProperties()
	OBJECT_VIEWDISTANCE_IDCOUNT#=0
	OBJECT_VIEWDISTANCE_LARGESTIDCOUNT#=0
	Game\Cheater=0 : Game\CheaterChangedCharacter=0
	Player_ResetOutGameValues()
	
	If Menu\StabilityTest = 1 Then Game\F9Timer=9999*secs#
	
	Game\Leader=1
	Game\NewLeader=1
	
	CAMERA_ZOOMVALUE# = 21
	CAMERA_ACTUALZOOMVALUE# = 21
	CAMERA_DISTANCE_NEAR# = CAMERA_ZOOMVALUE#
	CAMERA_DISTANCE_FAR# = CAMERA_ZOOMVALUE#
	
	Game\Interface\DebugPlacerOn=0
	
	If Menu\Settings\DisablePlants#=0 Or (Menu\Settings\DisablePlants#=1 And Menu\ChaoGarden=0) Then Menu\Settings\RealDisablePlants#=0 Else Menu\Settings\RealDisablePlants#=1
	For i=1 To 5 : MonitorIcon(i)\Draw=False : MonitorIcon(i)\Timer=0 : Next
	
	; Menu music
	If (Not(Menu\Stage<>0)) And Menu\ChaoGarden=0 And Menu\LoadedMenuMusic=0 Then
		Menu\LoadedMenuMusic=1
		Game_Stage_UpdateProgressBar("Loading menu music", 0)
		LoadMenuMusic()
		Delay(75)
	EndIf
	
	
	
	; Member forcing
	If Menu\ForceMembers>0 Then
		Menu\Members=Menu\ForceMembers
		For i = 1 To 3 : 
			If Menu\ForceCharacter[i]>0 Then Menu\Character[i]=Menu\ForceCharacter[i]
		Next
	EndIf
	
	UpdateRichPresence(0)
	
	; Character models
	For i=1 To 3
		Game_Stage_UpdateProgressBar("Loading player models", 0)
		If Menu\Members>i-1 Then
			If IsCharMod(Menu\Character[i]) Then
				LoadCharacterMesh(Menu\Character[i],0,0,Menu\ModCharCostumes[i])
			Else
				LoadCharacterMesh(Menu\Character[i],0)	
			EndIf
			
			Game\CharacterMesh[i]=CopyEntity(CharacterMesh)
			DeleteCharacterMesh()
			
			If IsCharMod(Menu\Character[i]) Then
				If CharModHasSuper(Menu\Character[i]) Then
					LoadCharacterMesh(Menu\Character[i],0,1,Menu\ModCharCostumes[i])
				Else
					LoadCharacterMesh(Menu\Character[i],0,0,Menu\ModCharCostumes[i])
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
		Else
			Game\CharacterMesh[i]=CopyEntity(MESHES(Mesh_Empty))
			Game\SuperCharacterMesh[i]=CopyEntity(MESHES(Mesh_Empty))
		EndIf
	Next
	
	; Interface loading
	Game_Stage_UpdateProgressBar("Loading interface", 0)
	If Menu\Stage<>0 Then
		If Menu\ChaoGarden=0 Then
			For x=INTERFACE_MENUTOTAL+1 To INTERFACE_STAGETOTAL : LoadSmartImage(x) : Next
		Else
			For x=INTERFACE_BLACKMARKETTOTAL+1 To INTERFACE_CHAOGARDENTOTAL : LoadSmartImage(x) : Next
		EndIf
	Else
		For x=INTERFACE_ALWAYSTOTAL+1 To INTERFACE_MENUTOTAL : LoadSmartImage(x) : Next
	EndIf
	
	; Sound loading
	Game_Stage_UpdateProgressBar("Loading sounds", 0)
	If Menu\Stage<>0 Then
		For x=SOUNDS_RESULTSTOTAL+1 To SOUNDS_STAGETOTAL : LoadSmartSound(x) : Next
	Else
		For x=SOUNDS_MENUMUSICTOTAL+1 To SOUNDS_MENUTOTAL : LoadSmartSound(x) : Next
	EndIf
	
	If Menu\MissionTime=1 Then
		Menu\RankMissionOnTime=1 
	Else
		Select Menu\Mission
			Case MISSION_NORMAL#,MISSION_ENCORE#,MISSION_CARNIVAL#,MISSION_ESCAPE#
				Menu\RankMissionOnTime=0
			Default
				Menu\RankMissionOnTime=1
		End Select
	EndIf
	
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
		Case 0: RootNode = xmlLoad(Game\Stage\Properties\Path$+"menustage.dat")
		Default
			If Menu\TutorialMode=1 Then
				RootNode = xmlLoad(Game\Stage\Properties\Path$+ShortCharNames(Menu\Character[1],1)+".xml")
			Else
				If Not(FileType(Game\Stage\Properties\Path$+"Stagem"+Menu\MissionNo+".xml")=1) Then 
					RootNode = xmlLoad(Game\Stage\Properties\Path$+"Stage.xml")
				Else
					RootNode = xmlLoad(Game\Stage\Properties\Path$+"Stagem"+Menu\MissionNo+".xml")
				EndIf 
			EndIf
	End Select
	
	; Parse XML
	Game_Stage_ParseXML_Information(RootNode)
	
	; Parse up Stage.xml file and retrieve root node. Find out if any parse errors ocurred while loading
	; and if so, stop the game.
	Game_Stage_UpdateProgressBar("Parsing stage XML", 0)
	Select Menu\Stage
		Case 0: RootNode = xmlLoad(Game\Stage\Properties\Path$+"menustage.dat")
		Default
			If Menu\TutorialMode=1 Then
				RootNode = xmlLoad(Game\Stage\Properties\Path$+ShortCharNames(Menu\Character[1],1)+".xml")
			Else
				If Not(FileType(Game\Stage\Properties\Path$+"Stagem"+Menu\MissionNo+".xml")=1) Then 
					RootNode = xmlLoad(Game\Stage\Properties\Path$+"Stage.xml")
				Else
					RootNode = xmlLoad(Game\Stage\Properties\Path$+"Stagem"+Menu\MissionNo+".xml")
				EndIf 
			EndIf
	End Select
	
	Game_Stage_ParseXML_Scene(RootNode)
	
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
				For i=1 To CHAOCOUNT : Object_CreateChao(i) : Next
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
	OBJECT_VIEWDISTANCE_UPDATEDISTANCE#=Menu\Settings\ObjectUpdateRange#
	OBJECT_VIEWDISTANCE#=Menu\Settings\ObjectViewRange#
	
	
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
	If Menu\StabilityTest = 1 Then Game\F9Timer=1*secs#
	Game\Interface\RingScaleTimer=1*secs#
	
	DeltaTime_Reset(Game\DeltaTime)
	Game\State = GAME_STATE_STEP
	
		; Setup collisions within the environment.
	Game_Stage_SetCollisions()
	
	; Create fade-in transition effect
	PostEffect_Create_FadeIn(0.01, 10, 10, 10)
	Menu\StartedStageWarp=0
	
	; Stage music
	If Menu\Stage<>0 Then
		Stage_ResetStageMusic()
		LoadStageMusic(Game\Stage\Properties\Path$)
	EndIf
	
	
	
	
	
	FlushAll()
	SetFont MidFont
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D