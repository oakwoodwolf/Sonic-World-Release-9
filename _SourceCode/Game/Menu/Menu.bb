
;; Stores most relevant in-game settings before saving them.
Type tMenu_Settings
	Field DisallowParticles#
	Field Resolution#
	Field NewResolution#
	Field ScreenMode#
	Field ScreenModeChanged#
	Field Debug#
	Field DebugNodes#
	Field Volume#
	Field VolumeSFX#
	Field VolumeVA#
	Field VolumeM#
	Field VolumeAmb#
	Field DepthOfField#
	Field Bloom#
	Field Shadows#
	Field MotionBlur#
	Field SunRays#
	Field ThreeDSounds#
	Field Theme#
	Field NewTheme#
	Field ChaoNameTag#
	Field BumpMaps#
	Field WaterQuality#
	Field StadiumDifficulty#
	Field DisablePlants#
	Field RealDisablePlants#
	Field Mods#
	Field ControlTips#
	Field PrimaryController#
	Field ControllerLayout#
	Field AutoCameraDisabled#
	Field VSync#
	Field ViewRange#
End Type
;; Menu type, consisting of flags for anything saved in the menu.
Type tMenu 
	Field Developer

	Field FirstTime

	Field GameStarted

	Field Menu
	Field NewMenu
	Field Option
	Field NewOption
	Field OptionOrder

	Field Menu2
	Field NewMenu2
	Field Option2
	Field NewOption2
	Field OptionOrder2
	Field OptionControl
	Field TeamOrder

	Field ControlsToShow

	Field Settings.tMenu_Settings

	Field Pause

	Field Stage
	Field SelectedStage
	Field PreviousStage
	Field HubStage
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
	Field MarathonGotSpecial
	Field MarathonExists

	Field Members
	Field MembersMarathon
	Field MemberToSelect
	Field Team
	Field Character[3]
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

	Field SavedLives
	Field Wallet

	Field Background
	Field ShowCards
	Field Transition
	Field TransitionMayPass
	Field PressStartTimer
	Field TitleCardTimer
	Field TitleCardExitTimer
	Field CreditsTimer
	Field TitleState
	Field TitleStateValues#[4]

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

	Field Music
	Field DontReplayMusic
	Field Channel_Menu
	Field Channel_MenuIntro
	Field Channel_MenuOptions
	Field Channel_MenuCharacter
	Field Channel_MenuCredits
	Field Channel_MenuChao
	Field Channel_MenuChao2
	Field Channel_GameOver
	Field Channel_Emblem

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

	Field BuddyChaoNumber
	Field BuddyChaoName$
	Field BuddyChaoAge
	Field BuddyChaoPersona
	Field BuddyChaoColor
	Field BuddyChaoShape
	Field BuddyChaoSide
	Field BuddyChaoSkills[7]
	Field BuddyChaoCurrentSkills[7]
	Field BuddyChaoEternal
	Field BuddyChaoHat
	Field BuddyChaoStagesCleared
	Field SaveChaoStages

	Field BlackMarketRandomizerTimer

	Field LoadThumbnailAndMissions

	Field BubbleCreatorTimer
	Field CharScrollFade#
	Field CharScrollFadeMode
	Field CharScrollY#

	Field RandomBackgroundChooser

	Field RingRotator
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
	Const MENU_CHARACTERS2#	= 21
	Const MENU_BIOS#	= 3
	Const MENU_TEAMS#	= 4
	Const MENU_STAGE#	= 5
	Const MENU_STAGE2#	= 51
	Const MENU_STAGESPECIAL# = 52
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

	Const MENU_RESOLUTION#	= 1
	Const MENU_SCREEN#	= 2
	Const MENU_DEBUG#	= 3
	Const MENU_VOLUME#	= 4
	Const MENU_CONTROLS#= 5
	Const MENU_CONTROLS2#= 6
	Const MENU_DOF#		= 7
	Const MENU_BLOOM#	= 8
	Const MENU_SHADOWS#	= 9
	Const MENU_BLUR#	= 10
	Const MENU_SRAYS#	= 11
	Const MENU_BUMPMAPS#= 12
	Const MENU_WATER#=13
	Const MENU_SOUNDS#	= 14
	Const MENU_PLANTS#	= 15
	Const MENU_VIEW#	= 16
	Const MENU_AUTOCAM#	= 17
	Const MENU_VSYNC#	= 18
	Const MENU_MODS#	= 19
	Const MENU_TIPS#	= 20
	Const MENU_THEME#	= 21
	Const MENU_RESET#	= 22
	

	Const MENU_BLACKMARKET_MAIN#		= 0
	Const MENU_BLACKMARKET_BUY#		= 1
	Const MENU_BLACKMARKET_SELLLIST#	= 2
	Const MENU_BLACKMARKET_EXIT#		= 3
	Const MENU_BLACKMARKET_BUYLIST#		= 4
	Const MENU_BLACKMARKET_BUYCONFIRM#	= 5
	Const MENU_BLACKMARKET_BUYREFUSE#	= 6
	Const MENU_BLACKMARKET_SELLCONFIRM#	= 7
	Const MENU_BLACKMARKET_SELLREFUSE#	= 8
	Const MENU_BLACKMARKET_EXITREAL#	= 9

	Const MENU_TRANSPORTER_MAIN#		= 0
	Const MENU_TRANSPORTER_NAME#		= 1
	Const MENU_TRANSPORTER_GOODBYE#		= 2
	Const MENU_TRANSPORTER_BUDDY#		= 3
	Const MENU_TRANSPORTER_INVENTORY#	= 4
	Const MENU_TRANSPORTER_EXIT#		= 5
	Const MENU_TRANSPORTER_STADIUM#		= 6
	Const MENU_TRANSPORTER_RACEEXIT#	= 7
	Const MENU_TRANSPORTER_RACES#		= 8
	Const MENU_TRANSPORTER_KARATEEXIT#	= 9
	Const MENU_TRANSPORTER_DIFFICULTY#	= 10

	Const MENU_PRINCIPAL_MAIN#		= 0
	Const MENU_PRINCIPAL_LESSONS#		= 1
	Const MENU_PRINCIPAL_EXITREAL#		= 2

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
	Global MISSION_DECLINE#	= 12
	Global MISSION_ESCAPE#	= 13
	Global MISSION_CAPSULE#	= 14
	Global MISSIONCOUNT#	= 14

Const BUTTON_SCALESPEED#=0.0030*5
Const BUTTON_SCALELIMIT#=0.25
