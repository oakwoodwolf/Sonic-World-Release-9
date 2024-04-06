
	Dim CHARSIDES(CHAR_PLAYABLECOUNT)
	CHARSIDES(CHAR_SON) = 1
	CHARSIDES(CHAR_TAI) = 1
	CHARSIDES(CHAR_KNU) = 1
	CHARSIDES(CHAR_AMY) = 1
	CHARSIDES(CHAR_SHA) = 2
	CHARSIDES(CHAR_ROU) = 2
	CHARSIDES(CHAR_CRE) = 1
	CHARSIDES(CHAR_BLA) = 1
	CHARSIDES(CHAR_SIL) = 1
	CHARSIDES(CHAR_OME) = 2
	CHARSIDES(CHAR_ESP) = 1
	CHARSIDES(CHAR_CHA) = 1
	CHARSIDES(CHAR_VEC) = 1
	CHARSIDES(CHAR_BIG) = 1
	CHARSIDES(CHAR_MAR) = 1
	CHARSIDES(CHAR_MIG) = 1
	CHARSIDES(CHAR_RAY) = 1
	CHARSIDES(CHAR_CHO) = 2
	CHARSIDES(CHAR_TIK) = 1
	CHARSIDES(CHAR_NAC) = 2
	CHARSIDES(CHAR_BEA) = 2
	CHARSIDES(CHAR_BAR) = 2
	CHARSIDES(CHAR_JET) = 2
	CHARSIDES(CHAR_WAV) = 2
	CHARSIDES(CHAR_STO) = 2
	CHARSIDES(CHAR_TIA) = 1
	CHARSIDES(CHAR_HON) = 1
	CHARSIDES(CHAR_SHD) = 1
	CHARSIDES(CHAR_MPH) = 2
	CHARSIDES(CHAR_HBO) = 1
	CHARSIDES(CHAR_GAM) = 2
	CHARSIDES(CHAR_EME) = 2
	CHARSIDES(CHAR_MET) = 2
	CHARSIDES(CHAR_TDL) = 2
	CHARSIDES(CHAR_MKN) = 2
	CHARSIDES(CHAR_EGG) = 2
	CHARSIDES(CHAR_BET) = 2
	CHARSIDES(CHAR_MT3) = 2
	CHARSIDES(CHAR_GME) = 2
	CHARSIDES(CHAR_PRS) = 1
	CHARSIDES(CHAR_COM) = 1
	CHARSIDES(CHAR_CHW) = 3
	CHARSIDES(CHAR_EGR) = 2
	CHARSIDES(CHAR_INF) = 2

;-----------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------

Function ReturnFPSDifferenceFactor()
	If Game\Others\FPS>=50 Then
		Return 15
	ElseIf Game\Others\FPS>40 Then
		Return 20
	ElseIf Game\Others\FPS>30 Then
		Return 25
	Else
		Return 30
	EndIf
End Function

;-----------------------------------------

Function Gameplay_AddRings(value#)
	If Game\Gameplay\Rings+value#<999 Then
		Game\Gameplay\Rings=Game\Gameplay\Rings+value#
	Else
		Game\Gameplay\Rings=999
	EndIf
End Function

Function Gameplay_AddLives(value#)
	PlaySmartSound(Sound_1Up)
	If Game\Gameplay\Lives+value#<99 Then
		Game\Gameplay\Lives=Game\Gameplay\Lives+value#
	Else
		Game\Gameplay\Lives=99
	EndIf
End Function

Function Gameplay_AddScore(value#)
	If Game\Gameplay\Score+value#<999999 Then
		Game\Gameplay\Score=Game\Gameplay\Score+value#
	Else
		Game\Gameplay\Score=999999
	EndIf

	If value#>10 Then
		Game\Interface\Points=value# : Game\Interface\PointsCommentGiven=0
		Game\Interface\PointsChain=Game\Interface\PointsChain+1
		Game\Interface\PointsTimer=6.2*secs#
	EndIf
End Function

Function Gameplay_AddIdealScore(o.tObject,value#)
	o\AddedToIdealScore=1
	Game\IdealScore=Game\IdealScore+value#
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

Function Gameplay_AddRedRings(value#)
	Game\Gameplay\RedRings=Game\Gameplay\RedRings+value#
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
		Field	MustQuitStage
		Field	CinemaMode
		Field	TimeControl
		Field	Cheater
		Field	CheaterChangedCharacter
		Field	RivalAmount
		Field	CarnivalLevel
		Field	CurrentCarnivalTimer
		Field	CarnivalAppearTimer
		Field	BossNotDefeated
		Field	InsideBoxCheckerTimer
		Field	SmartCameraRangeDontAffectTimer

		; Players
		Field	Leader
		Field	NewLeader
		Field	CharacterMesh[3]
		Field	SuperCharacterMesh[3]
		Field	HyperCharacterMesh[3]

		; Resetting condition values
		Field	ResetCamera
		Field	ResetChecks
		Field	ResetObjects

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

		Field	CounterChance
		Field	CounterChanceTimer

		; Timers
		Field	CheeseTimer
		Field	FroggyTimer

		; Channels
		Field	Channel_Invincible
		Field	Channel_SpeedShoes
		Field	Channel_Drown
		Field	Channel_MissionCompleted
		Field	Channel_ResultCount
		Field	Channel_Result
		Field	Channel_1Up
		Field	Channel_ChaoEffect

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
		Field	RedRings
		Field	Balloons
		Field	Flickies

		Field 	GainedLives

		Field	PerfectBonus
		Field	DiedOnce

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

		Field	TotalEnemies
		Field	TotalGoldEnemies
		Field	TotalBalloons

		Field	TotalRedRings
		Field	RedRing[3]
		Field	RedRingTimer[3]
		Field	RedRingBeepTimer[3]
		Field	RedRingDistance[3]

		Field	PsychoBombCount
		Field	TotalBoxes
		Field	InitialProgress#
		Field	Progress#
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

		Field	GeneralLightPivot
		Field	GeneralLight
		Field	SunRays

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

		Field   Water
		Field   WaterMesh
		Field   WaterLevel
		Field   WaterLevelInitial
		Field   WaterLevelChanged
		Field   WaterLevelChangeChannel
		Field   WaterLevelTarget
		Field   WaterTexture
		Field   WaterTextureTimer
		Field   WaterType

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

		Field	SpecialStageTexture
		Field	SpecialStageSkydomeTexture
		Field	SpecialStageSkydomeR
		Field	SpecialStageSkydomeG
		Field	SpecialStageSkydomeB
		Field	SpecialStageSkydomeTargetR
		Field	SpecialStageSkydomeTargetG
		Field	SpecialStageSkydomeTargetB
		Field	SpecialStageRingGateRequirement
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

	Const FPS_LIMIT = 60
	
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
		CustomPostprocessGlow(0.35, 2, 4, 0.35, 1)
		CustomPostprocessDOF(2000,20000,1,10,.15)
		CustomPostprocessContrast(.60,2,225,225,225,2)

		Game\Others\FpsLimit=CreateTimer(FPS_LIMIT)

		; Initial menu values
		Menu\Stage=0
		Menu\Mission=0
		Menu\MissionTime=0
		Menu\MissionMach=0
		Menu\MissionPerfect=0
		Menu\MissionNo=1
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

		; Load stage list
		LoadStageList()

		; Load mods
		If Menu\Settings\Mods#>0 Then
			LoadMods_Characters()
			LoadMods_Voices()
		EndIf

		; Load always objects
		For x=0 to MESHES_ALWAYSTOTAL : LoadSmartEntity(x) : Next

		; Load always interface
		For x=1 to INTERFACE_ALWAYSTOTAL : LoadSmartImage(x) : Next

		; Load always sounds
		For x=1 to SOUNDS_ALWAYSTOTAL : LoadSmartSound(x) : Next

		; Ready to initiate
		Game\State	= GAME_STATE_START
		Game\Stage\List = First tGame_StagesList
	End Function


	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Update()
		; Acquire keyboard and controls status and update delta time
		DeltaTime_Update(Game\DeltaTime)
		Input_Update()
		
		Game_Stage_Update()
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_Update()
		Select Game\State
			Case GAME_STATE_START : Game_Stage_Start()
			Case GAME_STATE_STEP  : Game_Stage_Step(Game\DeltaTime)
		End Select

		If Game\MustQuitStage>0 and ( Menu\Stage=0 Or (Menu\Stage<>0 and CARD_PLACE#<=(-150+5)) ) Then Game_Stage_ReallyQuit(Game\MustQuitStage) : Game\MustQuitStage=0
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