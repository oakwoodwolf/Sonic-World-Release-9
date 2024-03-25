;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
;||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
;   STARTUP
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; Code code
Include "_SourceCode\Core\Core_GeneralConstants.bb"
Include "_SourceCode\Core\Core_InputManagement.bb"
Include "_SourceCode\Core\Core_ScreenFunctions.bb"

; Libraries code
Include "_SourceCode\Libraries\Library_FastImage.bb"
Include "_SourceCode\Libraries\Library_XMLParser.bb"
Include "_SourceCode\Libraries\Library_FastExt.bb"
Include "_SourceCode\Libraries\Library_FxManager.bb"

; Menu code
Include "_SourceCode\Game\Menu\Menu_BlackMarket_Info.bb"

; Some constants
Const CHAR_TOTAL = 35*2
Const MODCHAR_AMOUNT = 26

; Game code
Include "_SourceCode\Game\Game_Settings.bb"
Include "_SourceCode\Game\Game_Save.bb"
Include "_SourceCode\Game\Game_Save_Encryption.bb"
Include "_SourceCode\Game\Game_Resources_Textures_Info.bb"
Include "_SourceCode\Game\Game_Resources_Textures.bb"
Include "_SourceCode\Game\Game_Resources_Sounds_Info.bb"
Include "_SourceCode\Game\Game_Resources_Sounds.bb"

; Stage code
Include "_SourceCode\Game\Stage\Objects\ObjectsMeshes_Info.bb"
Include "_SourceCode\Game\Stage\Objects\ObjectsMeshes.bb"

; Menu code
Include "_SourceCode\Game\Menu\Menu.bb"

; --- Initializate ---
;   throws error if archive is not found. This can be safely removed
If Not(FileType("GameArchive.swarc")=1) Then RuntimeError("Game Archive was not found.")
;   Developer mode flag. Allows debug to be used in any mode and adds extra debug functions.
Menu\Developer=1

CreateDir(GetEnv$("AppData")+"\Sonic World")
ResetOptions_Values()
If Not(FileType(SaveDataPath$+"SETTINGS.dat")=1) Then CreateDir(SaveDataPath$) : ResetAll()
LoadGame(False)
Game_LoadConfig()

SetGameGraphics()

InitExt()
Dither(False)
WBuffer(True)
AntiAlias(False)
SetBuffer(BackBuffer())

Global LilFont=LoadFont("Arial",12,True,False,False)
Global MidFont=LoadFont("Arial",20,True,False,False)
Global BigFont=LoadFont("Arial",30,True,False,False)

; --- Show the loading screen
Global Disclaimer = LoadImage("Interface/Disclaimer.png")
Global Avatars = LoadAnimImage("Interface/Avatars.png", 180, 180, 0, 12)
ResizeImage Disclaimer, (1440/1.75)*GAME_WINDOW_SCALE#, (900/1.75)*GAME_WINDOW_SCALE#
If Menu\Settings\ScreenMode#=0 Then avatarsize#=3.4 Else avatarsize#=3.25
ResizeImage Avatars, (180/avatarsize#)*GAME_WINDOW_SCALE#, (180/avatarsize#)*GAME_WINDOW_SCALE#
MaskImage Avatars, 0, 0, 0
ClsColor(0,0,0)
Cls
VWait
DrawImage Disclaimer,(GAME_WINDOW_W/2.0)-((1440/1.75)*GAME_WINDOW_SCALE#/2.0),(GAME_WINDOW_H/2.0)-((900/1.75)*GAME_WINDOW_SCALE#/2.0)
For i=0 To 12-1 : DrawImage(Avatars,(GAME_WINDOW_W)-((i+1)*(180/avatarsize#)*GAME_WINDOW_SCALE#),(GAME_WINDOW_H)-((180/avatarsize#)*GAME_WINDOW_SCALE#), i) : Next
Flip(GAME_WINDOW_VSYNC)


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
;   INCLUDES
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; Core code
Include "_SourceCode\Core\Core_DeltaTime.bb"
Include "_SourceCode\Core\Core_Maths.bb"
Include "_SourceCode\Core\Core_Tools.bb"

; Libraries code
Include "_SourceCode\Game\Stage\ParticleTemplate.bb"
Include "_SourceCode\Libraries\Library_Particles.bb"
Include "_SourceCode\Libraries\Library_Shadows.bb"
Include "_SourceCode\Libraries\Library_PostFX.bb"

; Game code
Include "_SourceCode\Game\Game.bb"
Include "_SourceCode\Game\Game_Interface.bb"
Include "_SourceCode\Game\Game_Interface_Result.bb"
Include "_SourceCode\Game\Game_Interface_Chao.bb"
Include "_SourceCode\Game\Game_Interface_IconDisplay.bb"
Include "_SourceCode\Game\Game_Interface_Debug.bb"
Include "_SourceCode\Game\Game_Mods.bb"
Include "_SourceCode\Game\Game_Resources_Models.bb"

; Menu code
Include "_SourceCode\Game\Menu\Menu_Main.bb"
Include "_SourceCode\Game\Menu\Menu_BlackMarket.bb"
Include "_SourceCode\Game\Menu\Menu_Transition.bb"
Include "_SourceCode\Game\Menu\Menu_Text.bb"
Include "_SourceCode\Game\Menu\Menu_Pages.bb"
Include "_SourceCode\Game\Menu\Menu_Play.bb"
Include "_SourceCode\Game\Menu\Menu_Play_Teams.bb"
Include "_SourceCode\Game\Menu\Menu_Options.bb"
Include "_SourceCode\Game\Menu\Menu_Transporter.bb"

; Stage code
Include "_SourceCode\Game\Stage\Stage.bb"
Include "_SourceCode\Game\Stage\Stage_Exit.bb"
Include "_SourceCode\Game\Stage\Stage_Update.bb"
Include "_SourceCode\Game\Stage\Stage_ObjectLoading.bb"
Include "_SourceCode\Game\Stage\Stage_ObjectCreate.bb"
Include "_SourceCode\Game\Stage\Camera\Camera.bb"
Include "_SourceCode\Game\Stage\Camera\Camera_Control.bb"
Include "_SourceCode\Game\Stage\Player\Player.bb"
Include "_SourceCode\Game\Stage\Player\Player_Management.bb"
Include "_SourceCode\Game\Stage\Player\Player_ExtraManagement.bb"
Include "_SourceCode\Game\Stage\Player\Player_Motion.bb"
Include "_SourceCode\Game\Stage\Player\Player_Animation.bb"
Include "_SourceCode\Game\Stage\Player\Player_Actions.bb"
Include "_SourceCode\Game\Stage\Player\Player_ActionHelpers.bb"
Include "_SourceCode\Game\Stage\Player\Player_Debug.bb"
Include "_SourceCode\Game\Stage\Player\Player_Physics.bb"
Include "_SourceCode\Game\Stage\Player\Player_Timers.bb"
Include "_SourceCode\Game\Stage\Player\Player_Cheats.bb"
Include "_SourceCode\Game\Stage\Player\Player_Voices.bb"
Include "_SourceCode\Game\Stage\Player\Player_Effects.bb"
Include "_SourceCode\Game\Stage\Objects\Objects.bb"
Include "_SourceCode\Game\Stage\Objects\ObjectsInformation.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Pieces.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Boxes.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Collectables.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Enemies.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Translators.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Miscellaneous.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Props.bb"
Include "_SourceCode\Game\Stage\Objects\ObjectsReset.bb"
Include "_SourceCode\Game\Stage\Objects\Cheese.bb"
Include "_SourceCode\Game\Stage\Objects\Bombs.bb"
Include "_SourceCode\Game\Stage\Objects\Froggy.bb"
Include "_SourceCode\Game\Stage\Objects\BoxBlockers.bb"
Include "_SourceCode\Game\Stage\Objects\SwitchManagers.bb"
Include "_SourceCode\Game\Stage\Objects\ChaoEmo.bb"
Include "_SourceCode\Game\Stage\Objects\ChaoManagers.bb"
Include "_SourceCode\Game\Stage\Objects\ChaoManagers_Actions.bb"
Include "_SourceCode\Game\Stage\Objects\ChaoManagers_Extra.bb"
Include "_SourceCode\Game\Stage\Objects\Gravity.bb"
Include "_SourceCode\Game\Stage\Objects\Objects_Chao.bb"

; Menu code
Include "_SourceCode\Game\Menu\Menu_BlackMarket.bb"

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

VWait
DrawImage Disclaimer,(GAME_WINDOW_W/2.0)-((1440/1.75)*GAME_WINDOW_SCALE#/2.0),(GAME_WINDOW_H/2.0)-((900/1.75)*GAME_WINDOW_SCALE#/2.0)
For i=0 To 12-1 : DrawImage(Avatars,(GAME_WINDOW_W)-((i+1)*(180/avatarsize#)*GAME_WINDOW_SCALE#),(GAME_WINDOW_H)-((180/avatarsize#)*GAME_WINDOW_SCALE#), i) : Next
Flip(GAME_WINDOW_VSYNC)

FlushKeys()

Menu\GameStarted=1

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
;   ENTRY POINT
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Game_Startup()

	FreeImage Disclaimer
	FreeImage Avatars

	While(1)
		If (KeyHit(KEY_ESCAPE)) And Menu\Settings\Debug#=1 Then Game_End()
		WaitTimer(Game\Others\FpsLimit)
		Game_Update()
	Wend
	End