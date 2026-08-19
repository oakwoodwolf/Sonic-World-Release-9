
Type tMenu_Settings
	Field FavouriteCommand$[9]
	Field SensitivityMult#
	Field DisallowParticles#
	Field RollBehaviour#
	Field Resolution#
	Field NewResolution#
	Field ScreenMode#
	Field ScreenModeChanged#
	Field Debug#
	Field DebugNodes#
	Field PreviousVolume#
	Field Volume#
	Field VolumeSFX#
	Field VolumeVA#
	Field VolumeM#
	Field VolumeAmb#
	Field DepthOfField#
	Field Shadows#
	Field MotionBlur#
	Field sunrays#
	Field ThreeDSounds#
	Field Theme#
	Field NewTheme#
	Field ChaoNameTag#
	Field BumpMaps#
	Field StadiumDifficulty#
	Field DisablePlants#
	Field RealDisablePlants#
	Field Mods#
	Field CONTROLTIPS#
	Field PrimaryController#
	Field ControllerLayout#
	Field AutoCameraDisabled#
	Field VSync#
	Field ViewRange#
	Field CustomThemeOn#
	Field CustomThemeDir$
	Field Difficulty#
	Field HideAchievements#
	Field MeshViewRange#
	Field ObjectViewRange#
	Field ObjectUpdateRange#
	
	Field ControllerSupport#
	
	Field WarpRingName$
	
	
	Field ThemeKerningCapA
	Field ThemeKerningCapB
	Field ThemeKerningCapC
	Field ThemeKerningCapD
	Field ThemeKerningCapE
	Field ThemeKerningCapF
	Field ThemeKerningCapG
	Field ThemeKerningCapH
	Field ThemeKerningCapI
	Field ThemeKerningCapJ
	Field ThemeKerningCapK
	Field ThemeKerningCapL
	Field ThemeKerningCapM
	Field ThemeKerningCapN
	Field ThemeKerningCapO
	Field ThemeKerningCapP
	Field ThemeKerningCapQ
	Field ThemeKerningCapR
	Field ThemeKerningCapS
	Field ThemeKerningCapT
	Field ThemeKerningCapU
	Field ThemeKerningCapV
	Field ThemeKerningCapW
	Field ThemeKerningCapX
	Field ThemeKerningCapY
	Field ThemeKerningCapZ
	
	Field ThemeKerningA
	Field ThemeKerningB
	Field ThemeKerningC
	Field ThemeKerningD
	Field ThemeKerningE
	Field ThemeKerningF
	Field ThemeKerningG
	Field ThemeKerningH
	Field ThemeKerningI
	Field ThemeKerningJ
	Field ThemeKerningK
	Field ThemeKerningL
	Field ThemeKerningM
	Field ThemeKerningN
	Field ThemeKerningO
	Field ThemeKerningP
	Field ThemeKerningQ
	Field ThemeKerningR
	Field ThemeKerningS
	Field ThemeKerningT
	Field ThemeKerningU
	Field ThemeKerningV
	Field ThemeKerningW
	Field ThemeKerningX
	Field ThemeKerningY
	Field ThemeKerningZ
	
	Field ThemeKerning0
	Field ThemeKerning1
	Field ThemeKerning2
	Field ThemeKerning3
	Field ThemeKerning4
	Field ThemeKerning5
	Field ThemeKerning6
	Field ThemeKerning7
	Field ThemeKerning8
	Field ThemeKerning9
	
	Field MoonJumpKey
	Field DebugSyncRotKey
	
	
	
End Type

Type tMenu
	Field DebugText$
	Field Developer
	Field ThemeFolder$[23]
	Field InputRestrictTimer
	Field RestartMode
	Field MeshFValues#[2]
	Field RestartX#
	Field RestartY#
	Field RestartZ#
	Field FirstTime
	Field LoadedRedRingYet
	Field DrawHudLeft
	Field DrawHudRight
	Field MarathonResetValues
	Field MemberSelect
	Field GameStarted
	Field StageOptimised
	Field RedRingsGot
	Field WarpRingName$
	Field GameMuted
	Field Menu
	Field ModCharCostume
	Field ModCharCostumes[3]
	Field ModChar
	Field NewMenu
	Field RankMissionOnTime
	Field Option
	Field MissionCard
	Field NewOption
	Field OptionOrder
	Field CollectionMission
	Field GoBackToCollection
	
	Field UnlockedSHN
	
	Field PlayedMusic
	
	Field PauseOptions
	Field ForceCharacter[3]
	Field ForceMembers
	
	Field ActiveAbility[5]
	Field ActiveAbilities
	
	Field ActiveHandicap[5]
	Field ActiveHandicaps
	
	Field Menu2
	Field ThemeHeroesSpinner
	Field NewMenu2
	Field SkillEnabled
	Field Option2
	Field NewOption2
	Field OptionOrder2
	Field OptionControl
	Field TeamOrder

	Field ControlsToShow
	Field ThemeSelectType
	Field LoadedMenuMusic

	Field Settings.tMenu_Settings
	Field ObjectsFolder$
	Field Pause
	Field CustomThemeFolder$[14]
	
	Field StabilityTest
	Field StageAuthor$
	Field WarpRingPath$
	Field StartedStageWarp
	Field RedRingFade
	Field RubyFade
	
	
	Field TutorialMode
	Field LoadingTip$
	Field LoadingTipTimer
	Field CollectionRoom
	Field Stage
	Field SelectedStage
	Field PreviousStage
	Field HubStage
	Field HubMission
	Field HubMissionTime
	Field HubMissionMach
	Field HubMissionPerfect
	Field HubMissionNo
	Field Mission
	Field MissionTime
	Field MissionMach
	Field MissionPerfect
	Field MissionNo
	Field ExitedAStage
	Field ChaoGarden
	Field MarathonMode
	Field MarathonRandom
	Field MarathonStage
	Field MarathonExists
	
	Field UnfocusedSoundMuted
	
	Field ThemeGaugeR
	Field ThemeGaugeG
	Field ThemeGaugeB
	
	Field ThemeGaugeFR
	Field ThemeGaugeFG
	Field ThemeGaugeFB
	
	Field ThemeLoopSpin
	
	Field ThemeTimeStyle
	Field ThemeTimeIcon
	Field ThemeScoreIcon
	Field ThemeRingStyle
	Field ThemeScoreStyle
	
	Field ThemeScrolls
	Field ThemeBubbleAmount
	Field ThemeBubbleChance
	Field ThemeBubbleSpeed#
	Field ThemeBase
	Field ThemeContinuousVictory
	Field ThemeBubbles
	Field ThemeVictoryTimer
	Field ThemeResX
	Field ThemeResY
	
	Field ThemeLifeOffset
	
	Field ThemePlayBackground
	Field ThemeOptionsBackground
	Field ThemeBackgroundScrolls
	Field ThemeSpinners
	Field ThemeBackgroundScrollSpeed
	Field ThemeBackgroundPlay
	Field ThemeBackgroundOptions
	
	Field ThemeLifePause
	
	Field ThemeRoundTransition

	Field Members
	Field MembersMarathon
	Field MemberToSelect
	Field Team
	Field Character[3]
	Field PreviousCharacter[3]
	Field CharacterMode[3]
	Field NewCharacter
	Field MeshChange
	Field MeshMayChangeTimer
	Field Mesh[3]
	Field MeshBone
	Field HasMeshBone
	Field CharacterMeshAnimation
	Field CharacterMesh2MovedOnce
	Field MeshCharacter[3]
	Field MeshCharacterSuper
	Field MeshChaoEmo.tChaoEmo
	Field MeshChaoEmoActivated
	
	Field ThemeMinuteVal
	Field ThemeSecondVal
	Field ThemeMilliVal
	Field ThemeColonVal
	Field ThemeDotVal

	Field SavedLives
	Field Wallet
	Field AllRedRings
	Field CharScrollX#
	Field ThemeScrollSpeed#
	Field ThemeScrollAlpha#
	Field Background
	Field ShowCards
	Field Transition
	Field TransitionMayPass
	Field PressStartTimer
	Field TitleCardTimer
	Field TitleCardExitTimer
	Field CreditsTimer
	Field TitleState
	Field TitleStateValues#[6]

	Field FrozenMenuSaverTimer

	Field MustLoadStage

	Field GameOverType
	Field EmblemsGot
	Field UnlockedWho$
	Field Compliment$
	Field LoadedEmblemYet

	Field ButtonToChange
	Field ButtonBeChangeBy
	Field ButtonWasUsed
	Field ButtonWasChangedTimer
	Field ButtonIconChoice
	Field ButtonThatWasChanged
	Field ControlAssignmentSource

	Field CloseAfterOptions
	Field ControlsAfterOptions
	Field ResetOptionsAfterOptions
	Field ResetGameAfterOptions
	Field ResetRecordsAfterOptions
	Field ResetGardenAfterOptions
	Field LoadThemeAfterOptions
	Field SoundVolumeAfterOptions
	Field MusicPause#
	Field Music
	Field DontReplayMusic
	Field Channel_Menu
	Field Channel_MenuStats
	Field Channel_MenuIntro
	Field Channel_MenuOptions
	Field Channel_MenuCharacter
	Field Channel_MenuCredits
	Field Channel_MenuChao
	Field Channel_MenuChao2
	Field Channel_GameOver
	Field Channel_Emblem
	Field Channel_Logo

	Field Bio$
	Field OptionButton$
	Field TeamButton$
	Field Warning$
	Field StageName$
	Field MissionName$
	Field MissionInfo$
	Field MissionInfo2$
	Field ConvoName$
	Field Convo$

	Field CharacterRow
	Field AndAnyone

	Field OptionsForceKeyJump[2]
	Field OptionsForceKeyRoll[2]
	Field OptionsForceKeySkill2[2]
	Field OptionsForceKeyAct[2]

	Field RoundPos
	Field RoundNewPos
	Field RoundSize#
	Field RoundTimer

	Field ButtonSize#
	Field ButtonSize1#
	Field ButtonState1
	Field ButtonSize2#
	Field ButtonState2
	
	Field MissionTag$

	Field WentToChaoMenu
	Field BlackMarketBuyCategory
	Field BlackMarketSellCategory
	Field BlackMarketSellCategory2
	Field BlackMarketYesNo
	Field CurrentItem
	Field CurrentItemInfo$
	Field NewCurrentItem
	Field CurrentPrice
	Field ChaoMenuTimer
	Field BuyRefused
	Field EggsBought
	Field ItemAmount
	Field RaceType

	Field Background2
	Field Background2Changed
	Field PreviousBackground2
	Field RealPreviousBackground2
	Field BackgroundFader#

	Field HeldChaoNumber
	Field HeldChaoName$
	Field NewChaoName$
	Field SaveChaoName
	Field HeldChaoAge
	Field HeldChaoPersona
	Field HeldChaoColor
	Field HeldChaoShape
	Field HeldChaoSide
	Field HeldChaoSkills[7]
	Field HeldChaoCurrentSkills[7]
	Field HeldChaoEternal
	Field HeldChaoHat
	Field HeldChaoCompetitionsWon
	Field HeldChaoCompetitionsLost
	Field SaveChaoCompetitions
	
	
	Field TestValue[8]

	Field BlackMarketRandomizerTimer

	Field LoadThumbnailAndMissions

	Field BubbleCreatorTimer
	Field CharScrollFade#
	Field CharScrollFadeMode
	Field CharScrollY#

	Field RandomBackgroundChooser

	Field RingRotator
	Field RingRotator2
	Field SlowRingRotator
	Field SlowRingRotator2
	
	Field ThemeLogoStyle
	
	
	Field ShopItemsBought
End Type

;-----------------------------------------------------------------------------------------------------------------------------------------

Global Menu.tMenu = New tMenu
Menu\Settings = New tMenu_Settings



;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

	Const MENU_LOADING#	= -3
	Const MENU_CLOSE#	= -2
	Const MENU_START#	= -1
	Const MENU_MAIN#	= 0
	Const MENU_PLAY#	= 1
	Const MENU_CHARACTERS#	= 2
	Const MENU_BIOS#	= 3
	Const MENU_TEAMS#	= 4
	Const MENU_STAGE#	= 5
	Const MENU_STAGE2#	= 51
	Const MENU_OPTIONS#	= 6
	Const MENU_CREDITS#	= 7
	Const MENU_GAMEOVER#	= 8
	Const MENU_WELCOME#	= 9
	Const MENU_EMBLEM#	= 10
	Const MENU_BLACKMARKET#	= 11
	Const MENU_TRANSPORTER#	= 12
	Const MENU_PRINCIPAL#	= 13
	Const MENU_MARATHON#	= 14
	Const MENU_PLAYMARATHON#= 15
	Const MENU_MARATHONEND# = 16
	Const MENU_REDRING#	= 17
	Const MENU_PROGRESS#	= 18
	

	Const MENU_RESOLUTION#	= 100
	Const MENU_SCREEN#	= 200
	Const MENU_DEBUG#	= 300
	Const MENU_VOLUME#	= 400
	Const MENU_CONTROLS#= 500
	Const MENU_CONTROLS2#= 600
	Const MENU_DOF#		= 700
	Const MENU_VIDEO#	= 800
	Const MENU_GAMEPLAY#	= 900
	Const MENU_SRAYS#	= 1000
	Const MENU_BUMPMAPS#= 1100
	Const MENU_SOUNDS#	= 1200
	Const MENU_PLANTS#	= 1300
	Const MENU_VIEW#	= 1400
	Const MENU_AUTOCAM#	= 1500
	Const MENU_VSYNC#	= 1600
	Const MENU_MODS#	= 1700
	Const MENU_TIPS#	= 1800
	Const MENU_THEME#	= 1900
	Const MENU_RESET#	= 2000
	
	i=1
	Global ORIGIN_SW = i : i=i+1
	Global ORIGIN_DX  = i : i=i+1
	Global ORIGIN_S1 = i : i=i+1
	Global ORIGIN_S2 = i : i=i+1
	Global ORIGIN_S3 = i : i=i+1
	Global ORIGIN_S4  = i : i=i+1
	Global ORIGIN_CD  = i : i=i+1
	Global ORIGIN_3D  = i : i=i+1
	Global ORIGIN_MANIA = i : i=i+1
	Global ORIGIN_SA1 = i : i=i+1
	Global ORIGIN_SA2 = i : i=i+1
	Global ORIGIN_HEROES = i : i=i+1
	Global ORIGIN_SHADOW = i : i=i+1
	Global ORIGIN_06 = i : i=i+1
	Global ORIGIN_AD1 = i : i=i+1
	Global ORIGIN_AD2 = i : i=i+1
	Global ORIGIN_AD3 = i : i=i+1
	Global ORIGIN_RUSH = i : i=i+1
	Global ORIGIN_SECRET = i : i=i+1
	Global ORIGIN_BLACK = i : i=i+1
	Global ORIGIN_UNLEASHED = i : i=i+1
	Global ORIGIN_COLORS = i : i=i+1
	Global ORIGIN_LW = i : i=i+1
	Global ORIGIN_FORCES = i : i=i+1
	Global ORIGIN_FRONTIERS = i : i=i+1

	Const Menu_BlackMarket_Main#		= 0
	Const Menu_BlackMarket_Buy#		= 1
	Const Menu_BlackMarket_SellList#	= 2
	Const Menu_BlackMarket_Exit#		= 3
	Const Menu_BlackMarket_BuyList#		= 4
	Const Menu_BlackMarket_BuyConfirm#	= 5
	Const Menu_BlackMarket_BuyRefuse#	= 6
	Const Menu_BlackMarket_SellConfirm#	= 7
	Const Menu_BlackMarket_SellRefuse#	= 8
	Const Menu_BlackMarket_ExitReal#	= 9

	Const Menu_Transporter_Main#		= 0
	Const Menu_Transporter_Name#		= 1
	Const Menu_Transporter_Goodbye#		= 2
	Const Menu_Transporter_Inventory#	= 3
	Const Menu_Transporter_Exit#		= 4
	Const Menu_Transporter_Stadium#		= 5
	Const Menu_Transporter_RaceExit#	= 6
	Const Menu_Transporter_Races#		= 7
	Const Menu_Transporter_KarateExit#	= 8
	Const Menu_Transporter_Difficulty#	= 9

	Const Menu_Principal_Main#		= 0
	Const Menu_Principal_Lessons#		= 1
	Const Menu_Principal_ExitReal#		= 2

	Global CARD_PLACE# = 80*GAME_WINDOW_SCALE#
	Global CARD_PLACE_TARGET# = 0

	Global BUTTON_PLACE1# = -200*GAME_WINDOW_SCALE#
	Global BUTTON_PLACE1_TARGET# = 0
	Global BUTTON_PLACE2# = -200*GAME_WINDOW_SCALE#
	Global BUTTON_PLACE2_TARGET# = 0

	Global MISSION_NORMAL#	= 0
	Global MISSION_ENEMY#	= 1
	Global MISSION_RING#	= 2
	Global MISSION_HUNT#	= 3
	Global MISSION_GOLD#	= 4
	Global MISSION_STEALTH#	= 5
	Global MISSION_BALLOONS#= 6
	Global MISSION_FREEROAM#= 7
	Global MISSION_RIVAL#	= 8
	Global MISSION_CARNIVAL#= 9
	Global MISSION_BOSS#	= 10
	Global MISSION_FLICKY#	= 11
	Global MISSION_LAP#	= 12
	Global MISSION_ESCAPE#	= 13
	Global MISSION_ENCORE#	= 14
	Global MISSION_COLLECT#	= 15
	
	Global MISSIONCOUNT#	= 15

Const BUTTON_SCALESPEED#=0.0030*5
Const BUTTON_SCALELIMIT#=0.25

;~IDEal Editor Parameters:
;~C#Blitz3D