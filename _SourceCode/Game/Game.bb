
Function UpdateRichPresence(mode)
	
	
	
	
	If FileType("_SaveData/RichPresence.xml")=1 Then 
		DeleteFile("_SaveData/RichPresence.xml")
	EndIf 
	
	rpout = WriteFile("_SaveData\RichPresence.xml")
	
	Select mode
		Case 0
			name$=Menu\StageName$
			If Menu\CollectionRoom=1 Then name$="The Island"
			If Menu\ChaoGarden=1 Then name$="Chao Garden"
			WriteLine(rpout, name$)
			WriteLine(rpout, Menu\MissionNo)
			
			If Menu\Members=1 Then
				WriteLine(rpout, SingleCharNames$(Menu\Character[1]))
			ElseIf Menu\Members=2 Then
				WriteLine(rpout, "a pair")
			Else
				WriteLine(rpout, "a team")
			EndIf 
		Case 1
			Select Menu\NewMenu
				Case MENU_MAIN#
					tag$="In the main menu"
				Case MENU_CHARACTERS#
					tag$="Selecting a character"
				Case MENU_STAGE#,MENU_STAGE2#
					tag$="Selecting a stage"
				Case MENU_OPTIONS#
					tag$="Configuring options"
				Case MENU_CREDITS#
					tag$="Watching the credits"
				Case MENU_GAMEOVER#
					tag$="Receiving a game over"
			End Select 
			
			
			WriteLine(rpout,"In the menus")
			WriteLine(rpout,tag$)
			
	End Select
	
	CloseFile rpout
	
End Function 

;-----------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------

Function ReturnFPSDifferenceFactor()
	If Game\Others\Fps>=50 Then
		Return 15
	ElseIf Game\Others\Fps>40 Then
		Return 20
	ElseIf Game\Others\Fps>30 Then
		Return 25
	Else
		Return 30
	EndIf
End Function

;-----------------------------------------

Function Gameplay_AddRings(value#)
	
	If SHOPITEM_ENABLED(SHOPMENU_ABILITY,ABILITY_ENDLESSGAUGE)=1 Then Return
	
	If Game\Gameplay\Rings+value#<99999 Then
		Game\Gameplay\Rings=Game\Gameplay\Rings+value#
	Else
		Game\Gameplay\Rings=99999
	EndIf
	
	Game\Interface\RingScaleTimer=1.5*secs#
	Gameplay_AddGaugeEnergy(value#)
End Function

Function Gameplay_AddGaugeEnergy(value#)
	
	If Game\Gameplay\GaugeEnergy=100 Then Return   
	
	If Game\AddGauge=0 Then Game\AddGaugeTimer=0.02*secs#
	Game\GaugeToAdd=Game\GaugeToAdd+value#
	Game\AddGauge=1
	
	
End Function
Function Gameplay_SubstractGaugeEnergy(value#)
	If Game\DontLoseGauge=0 Then
	If Game\Gameplay\GaugeEnergy-value#>0 Then
		Game\Gameplay\GaugeEnergy=Game\Gameplay\GaugeEnergy-value#
	Else
		Game\Gameplay\GaugeEnergy=0
	EndIf
	EndIf
End Function


Function Gameplay_AddLives(value#)
	PlaySmartSound(Sound_1Up)
	If Game\Gameplay\Lives+value#<9999 Then
		Game\Gameplay\Lives=Game\Gameplay\Lives+value#
	Else
		Game\Gameplay\Lives=9999
	EndIf
End Function

Function Gameplay_AddScore(value#)
	Game\Gameplay\Score=Game\Gameplay\Score+value#
		

	If value#>500 Then
		Game\Interface\Points=value# : Game\Interface\PointsCommentGiven=0
		Game\Interface\PointsChain=Game\Interface\PointsChain+1
		Game\Interface\PointsTimer=6.2*secs#
	EndIf
End Function

Function Gameplay_AddEnemies(value#)
	Game\Gameplay\Enemies=Game\Gameplay\Enemies+value#
End Function

Function Gameplay_AddGoldEnemies(value#)
	Game\Gameplay\GoldEnemies=Game\Gameplay\GoldEnemies+value#
End Function

Function Gameplay_AddTotalEnemies(value#)
	Game\Gameplay\TotalEnemies=Game\Gameplay\TotalEnemies+value#
End Function

Function Gameplay_AddTotalGoldEnemies(value#)
	Game\Gameplay\TotalGoldEnemies=Game\Gameplay\TotalGoldEnemies+value#
End Function

Function Gameplay_AddPerfectBonus(value#)
	Game\Gameplay\PerfectBonus=Game\Gameplay\PerfectBonus+value#
End Function

Function Gameplay_AddShards(value#)
	Game\Gameplay\Shards=Game\Gameplay\Shards+value#
End Function

Function Gameplay_AddBalloons(value#)
	Game\Gameplay\Balloons=Game\Gameplay\Balloons+value#
End Function

Function Gameplay_AddTotalBalloons(value#)
	Game\Gameplay\TotalBalloons=Game\Gameplay\TotalBalloons+value#
End Function

Function Gameplay_AddFlickies(value#)
	Game\Gameplay\Flickies=Game\Gameplay\Flickies+value#
End Function

;---------------------------------------------------------------------------------

Function Gameplay_SubstractRings(value#)
	If Game\Gameplay\Rings-value#>0 Then
		Game\Gameplay\Rings=Game\Gameplay\Rings-value#
	Else
		Game\Gameplay\Rings=0
	EndIf
End Function

Function Gameplay_SubstractLives(value#)
	If Game\Gameplay\Lives-value#>0 Then
		Game\Gameplay\Lives=Game\Gameplay\Lives-value#
	Else
		Game\Gameplay\Lives=0
	EndIf
End Function

Function Gameplay_SubstractScore(value#)
	If Game\Gameplay\Score-value#>0 Then
		Game\Gameplay\Score=Game\Gameplay\Score-value#
	Else
		Game\Gameplay\Score=0
	EndIf
End Function

;---------------------------------------------------------------------------------

Function Gameplay_SetRings(value#)
	Game\Gameplay\Rings=value#
End Function
Function Gameplay_SetGaugeEnergy(value#)
	Game\Gameplay\GaugeEnergy=value#
End Function
Function Gameplay_SetLives(value#)
	Game\Gameplay\Lives=value#
End Function

Function Gameplay_SetScore(value#)
	Game\Gameplay\Score=value#
End Function

Function Gameplay_SetEnemies(value#)
	Game\Gameplay\Enemies=value#
End Function

Function Gameplay_SetGoldEnemies(value#)
	Game\Gameplay\GoldEnemies=value#
End Function

Function Gameplay_SetBalloons(value#)
	Game\Gameplay\Balloons=value#
End Function

Function Gameplay_SetFlickies(value#)
	Game\Gameplay\Flickies=value#
End Function

;---------------------------------------------------------------------------------

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Dim pp.tPlayer(3)
	Dim ppe.tPlayer(3)
	
	Type tGame
		Field	DeltaTime.tDeltaTime
		Field	Gameplay.tGame_Gameplay
		Field	Stage.tGame_Stage
		Field	Others.tGame_Others
		Field   Interface.tGame_Interface

		Field	State
		Field ReadyToSeeVictory
		Field ReadyToSeeVictoryTimer
		Field CollectiblePauseMusic
		
		Field	MustQuitStage
		Field DoBoost
		Field	CinemaMode
		Field	TimeControl
		Field	Cheater
		Field	CheaterChangedCharacter
		Field	RivalAmount
		Field	CarnivalLevel
		Field JumpFromPanel
		Field	CurrentCarnivalTimer
		Field	CarnivalAppearTimer
		Field	BossNotDefeated
		Field	InsideBoxCheckerTimer
		Field	SmartCameraRangeDontAffectTimer
		Field	CurrentCharacter
		Field MoonRingState
		Field MoonRingTimer
		Field MoonRingCount
		Field MoonRingCurrent
		Field DeathMesh
		Field DiamondCount
		; Players
		Field	Leader
		Field	NewLeader
		Field MissionCardable
		Field	CharacterMesh[3]
		Field	SuperCharacterMesh[3]
		
		Field SwitchOn[128]
		
		Field ObjToModify
		
		Field AmbientTimer
		Field AmbientState
		

		; Resetting condition values
		Field	ResetCamera
		Field	ResetChecks
		Field	ResetObjects
		
		Field SpawnX#
		Field SpawnY#
		Field SpawnZ#
		Field SpawnDir#
		
		Field DontLoseGauge
		
		Field ChangeDestID
		Field ChangeDest
		Field ChangeDestX#
		Field ChangeDestY#
		Field ChangeDestZ#

		; Power-up related values
		Field	SpeedShoes
		Field	SpeedShoeTimer
		Field	Invinc
		Field	InvincTimer
		Field	Shield
		Field	PreviousShield
		Field	HurtWithoutShield
		Field	Vehicle
		Field	WholeVehicle
		Field	SuperForm
		Field	RingDropTimer
		Field	BishopMagicTimer
		Field MissionValue

		Field	CounterChance
		Field	CounterChanceTimer
		Field CollectibleCount
		
		Field AddSuperRing
		Field SuperRingsToAdd
		Field SuperRingTimer
		
		Field AddGauge
		Field AddGaugeTimer
		Field GaugeToAdd
		Field Channel_SoundTest
		
		; Timers
		Field	CheeseTimer
		Field	FroggyTimer
		Field	F9Timer
		Field	TranslatorTimer
		Field 	CinemaFilter
		Field	CinemaFilterOn[9]
		Field PassedLapTimer
		
		Field CamDest
		Field CamDestX#
		Field CamDestY#
		Field CamDestZ#
		Field CamDestSpeed#
		Field SkydiveCancel
		Field RingRotation
		
		Field DirLock
		Field DirLockDir

		; Channels
		Field	Channel_Invincible
		Field	Channel_SpeedShoes
		Field	Channel_Drown
		Field	Channel_MissionCompleted
		Field	Channel_ResultCount
		Field	Channel_Result
		Field	Channel_1Up
		Field	Channel_ChaoEffect
		Field	Channel_Ring
		Field Channel_MoonTimer
		Field Channel_Shop
		Field Channel_Collectible

		; Locking values
		Field	StartoutLock
		Field	ControlLock
		Field	CamLock
		Field	CamLock2
		Field	RunLock
		Field	MachLock
		Field	MachLockTriggered
		Field	MachLockDisabler
		Field	Victory
		Field	TwoDLock
		Field QuickStepLock
		
		Field ResetCollectibles
		Field UsedSkill

		; Result stuff
		Field	LimitTime
		Field	DeclineTime
		Field	IdealTime
		Field	IdealScore
		Field 	ResultRings
		Field 	ResultRingsForBank
		Field 	ResultEnemies
		Field 	ResultTime
		Field 	ResultPerfectBonus
		Field 	ScoreBonus
		Field 	ScoreTotal
		Field 	Rank
		Field	VictoryEndType
		Field TokensToAdd
		Field	VictoryEndStage

		; color filter
		Field 	FilterIntensity#
		Field 	FilterColorR#
		Field 	FilterColorG#
		Field 	FilterColorB#

		; Penguinator fix
		Field	PenguinatorMovingTimer
		Field	PenguinatorToMove.tObject
	End Type

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tGame_Gameplay
		Field	Character
		Field 	Lives
		
		Field	Score
		Field 	Time
		Field 	Rings
		Field	Enemies
		Field	GoldEnemies
		Field	Shards
		Field	Balloons
		Field	Flickies
	

		Field 	GainedLives
		Field CurrentLap
		Field CurrentLapCheck
		Field TotalLapCheck
		Field TotalLapCount
		Field	PerfectBonus
		Field	DiedOnce
		Field GaugeEnergy

		Field	CheckX#
		Field	CheckY#
		Field	CheckZ#
		Field	CheckDirection#
		Field	CheckScore
		Field	CheckTime
		Field	CheckEnemies
		Field	CheckGoldEnemies
		Field	CheckBalloons
		Field	CheckMusicMode
		Field CheckCollectibles
		Field CheckTokens
		Field CheckRedRing[5]

		Field	TotalEnemies
		Field	TotalGoldEnemies
		Field	TotalBalloons

		Field	TotalShards
		Field	Shard[3]
		Field	ShardTimer[3]
		Field	ShardBeepTimer[3]
		Field	ShardDistance[3]

		Field	PsychoBombCount
		Field	TotalBoxes
		Field	InitialProgress#
		Field	Progress#
		
		Field Collectibles
		
		Field	GotRedRing[5]
	End Type

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tGame_StagesList
		Field	Folder$
	End Type

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tGame_Stage
		Field	Properties.tGame_StageProperties
		Field 	List.tGame_StagesList
		Field	Root
		Field	Gravity
		Field 	GravityAlignment.tVector
	End Type

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tGame_StageProperties
		Field	Path$

		Field 	MusicType
		Field 	Music[2]
		Field	MusicChn[2]
		Field	MusicFade#[2]
		Field	MusicMode
		Field MusicStart
		Field MusicStartChannel
		Field MusicStyle

		Field	GeneralLightPivot
		Field	GeneralLight
		Field	SunRays
		Field BackCompat

		Field	Skydome

		Field 	SkyCycle
		Field 	SkyCycleTimer
		Field 	SkyMesh[3]
		Field 	SkyMeshAlpha#[3]
		Field	AmbientCycle[3]

		Field	Earth
		Field	Moon
		Field	Sun
		Field	SunMoon
		Field   SunPos.tVector
		Field Hub
		
		Field   Water
		Field   WaterFogR
		Field   WaterFogG
		Field   WaterFogB
		Field   WaterFogNear
		Field   WaterFogFar
		Field   WaterR
		Field   WaterG
		Field   WaterB
		Field   WaterMesh
		Field   WaterLevel
		Field   WaterLevelInitial
		Field   WaterLevelChanged
		Field   WaterLevelChangeChannel
		Field   WaterLevelTarget
		Field   WaterTexture
		Field   WaterTextureTimer
		Field   WaterType
		
		Field	Fog
		Field	FogR#
		Field	FogG#
		Field	FogB#
		Field	FogNearDist#
		Field	FogFarDist#
		
		Field ShadowLightPivot
		Field ShadowLight
		Field	TargetFog
		Field	TargetFogR#
		Field	TargetFogG#
		Field	TargetFogB#
		Field	TargetFogNearDist#
		Field	TargetFogFarDist#
		Field 	TargetFogChangeRate#
		
		Field	FilterOn
		Field	FilterIntesity#
		Field	FilterR
		Field	FilterG
		Field	FilterB
		
		
		Field	HubMode
		Field   DeathLevel

		Field	AmbientAlarm
		Field	AmbientBeach
		Field	AmbientForest
		Field	AmbientRain
		Field	AmbientSnow
		Field	AmbientVoid
		Field	AmbientWind

		Field	AmbientParticle.tParticleTemplate

		Field	Channel_AmbientAlarm
		Field	Channel_AmbientBeach
		Field	Channel_AmbientForest
		Field	Channel_AmbientRain
		Field	Channel_AmbientSnow
		Field	Channel_AmbientVoid
		Field	Channel_AmbientWind

		Field	StartX#
		Field	StartY#
		Field	StartZ#
		Field	StartDirection#

	End Type

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tGame_Others
		Field 	CurrentCameraRange
		Field 	Fps%
		Field 	Frames%
		Field 	NextFrame%
		Field	FpsLimit%
	End Type
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	; --- Game States ----
	Const 		GAME_STATE_START	=	0
	Const 		GAME_STATE_STEP		=	1
	Const 		GAME_STATE_END		=	2

	; --- World constants ---
	Const		GAME_SCALE#			=	0.1

	Const FPS_LIMIT = 75
	
	Global CAMPOSX#
	Global CAMPOSY#
	Global CAMPOSZ#
	
	Global CAMPOS=CreatePivot()
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Game_Startup()
		; Set game title
	AppTitle(GAME_TITLE$)
	InitDraw() : SetBuffer(BackBuffer())
	WindowHWND = SystemProperty("AppHWND")
	FxManager_Startup()
	
	InitPostprocess()
	CustomPostprocessGlow(0.35, 1, 1, 0.35, 1, 255, 255, 255, 0)
	CustomPostprocessDOF(400,620,1,3,.15)
	CustomPostprocessContrast(.60,2,225,225,225,2)
	
	Game\Others\FpsLimit=CreateTimer(FPS_LIMIT)
	
		; Initial menu values
	For i = 1 To 3
		Menu\ModCharCostumes[i]=1
	Next
	Menu\Stage=0
	Menu\Mission=0
	Menu\MissionTime=0
	Menu\MissionMach=0
	Menu\MissionPerfect=0
	Menu\MissionNo=1
	Menu\MemberSelect=1
	Menu\Menu=-1
	Menu\Menu2=0
	Menu\Transition=0
	Menu\Members=1
	Menu\Team=0
	Menu\Character[1]=1
	Menu\Character[2]=0
	Menu\Character[3]=0
	Menu\SelectedStage=1
	Menu\Option=1
	Menu\Option2=1
	Menu\DontReplayMusic=1
	Menu\CharacterRow=1
	Menu\RingRotator=CreatePivot()
	Menu\RingRotator2=CreatePivot()
	Menu\SlowRingRotator=CreatePivot()
	Menu\SlowRingRotator2=CreatePivot()
	
	
	For i = 1 To StageAmount
		For j = 1 To 5
			RANK(j,i)=7
		Next
	Next
	
		; Load stage list
	LoadStageList()
	LoadCharacterStuff()
	Menu_LoadThemes()
	Menu_LoadThemeData()
	Menu_LoadThemeKerning()
	
	If RANK(1,NonModStageAmount)=1 And RANK(2,NonModStageAmount)=1 And RANK(3,NonModStageAmount)=1 And RANK(4,NonModStageAmount)=1 And RANK(5,NonModStageAmount)=1 Then 
		UNLOCKEDCHAR[CHAR_EGR]=1
	EndIf	
	
		; Load mods
	If Menu\Settings\Mods#>0 Then
		LoadMods_Characters()
		LoadMods_Voices()
	EndIf
	
	ObjectListRoot = xmlLoad("_Mods/objects.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing '_Mods/objects.xml'")
	For i=1 To xmlNodeChildCount(ObjectListRoot)
		Child = xmlNodeChild(ObjectListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "objects":
				Menu\ObjectsFolder$=xmlNodeAttributeValueGet(Child, "folder")
		End Select
	Next
	xmlNodeDelete(ObjectListRoot)
	
	
		; Load always objects
	For x=0 To MESHES_ALWAYSTOTAL : LoadSmartEntity(x) : Next
	
		; Load always interface
	For x=1 To INTERFACE_ALWAYSTOTAL : LoadSmartImage(x) : Next
	
		; Load always sounds
	For x=1 To SOUNDS_ALWAYSTOTAL : LoadSmartSound(x) : Next
	
		; Ready to initiate
	Game\State	= GAME_STATE_START
	Game\Stage\List = First tGame_StagesList
End Function


	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Update()
		; Acquire keyboard and controls status and update delta time
		DeltaTime_Update(Game\DeltaTime)
		If (Not(Game\Interface\DebugConsole=1 And Menu\Stage=0)) Then Input_Update()
		
		Game_Stage_Update()
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_Update()
		Select Game\State
			Case GAME_STATE_START : Game_Stage_Start()
			Case GAME_STATE_STEP  : Game_Stage_Step(Game\DeltaTime)
		End Select

		If Game\MustQuitStage>0 And ( Menu\Stage=0 Or (Menu\Stage<>0 And CARD_PLACE#<=(-150+5)) ) Then Game_Stage_ReallyQuit(Game\MustQuitStage) : Game\MustQuitStage=0
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_End()
		FreeEntity Menu\RingRotator
		DeInitExt()
		DeInitDraw()
		ClearWorld(True,True,True)
		EndGraphics()
		FreeTimer(Game\Others\FpsLimit)
		End
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function FxManager_RenderingPassInterruption(Pass, Method)
		; Put this on your game. Before rendering anything, this function will be called by the
		; Render World method, so you can disable certain entities.
		If (Method = 0) Then
			Select Pass
				Case 1
					;ShowEntity(Game\Stage\Properties\SkyBox)
				Case 2
					;HideEntity(Game\Stage\Properties\SkyBox)
				Case 3
				Case 4
				Case 5
			End Select
		Else
			Select Pass
				Case 1
					;ShowEntity(Game\Stage\Properties\SkyBox)
				Case 2
					;HideEntity(Game\Stage\Properties\SkyBox)
				Case 3
				Case 4
				Case 5
			End Select
		End If
	End Function
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Global Game.tGame 			= New tGame
	Game\DeltaTime				= DeltaTime_Create()
	Game\Gameplay 				= New tGame_Gameplay
	Game\Stage				= New tGame_Stage
	Game\Stage\Properties			= New tGame_StageProperties
	Game\Others				= New tGame_Others
	Game\Interface 				= New tGame_Interface

;-------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------



Global PostProcess_AlphaTexture = LoadTexture("Textures\Fade.png",1+2)
Global Texture_Empty = LoadTexture("Textures\Empty.png",1+2)
Global Texture_Trail = LoadTexture("Textures\trail.png",1+2+256)
;~IDEal Editor Parameters:
;~C#Blitz3D