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

Global Online=1		
Global PlayerNo=1
global PlayerName$=""
global connecttimer=0

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
Include "_SourceCode\Libraries\Library_BlitzPlay.bb"


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
Include "_SourceCode\Game\Stage\Player\Player_Online.bb"
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

	;Game\Mode=GAME_MODE_MENU
	;=============================================================================================================
	;=============================================================================================================
	If Online=1 Then
		Repeat 
			Cls()
			For i=1 To 255 : Color(i,0,i) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next ; blue gradient
			Color(255,255,255)	
			SetFont(BigFont)
			Text(GraphicsWidth()/2,25,"Welcome to BlitzSonic Online!",1,0)
			Text(GraphicsWidth()/2,75,"From here, you'll enter info to",1, 0)
			Text(GraphicsWidth()/2,125,"join others worldwide, or peer to peer.",1,0)	
			
			
			Text(GraphicsWidth()/2,150+FontHeight(),"Will you be Hosting?",1,0)
			
			If KeyHit(Key_Y) Then Game\Online\Hosting=True : Finished=1
			If KeyHit(Key_N) Then Game\Online\Hosting=False : Finished=1
			If KeyHit(Key_O) Then Game\Online\Hosting=False : Online=0 : Finished=1
			If KeyHit(Key_Escape) Then End
			
			Text(GraphicsWidth()/2, 250, "(Y)=Yes",1,0)
			Text(GraphicsWidth()/2, 300, "(N)=No",1,0)
			Text(GraphicsWidth()/2, 350, "(O)=Offline",1,0)
			Text(GraphicsWidth()/2, 400, "(Esc)=Exit",1,0)
			
			Flip()
		Until Finished=1
		Finished=0 : FlushKeys() : Delay(175)
		If Online=1 Then
			Repeat 
				Cls()
				For i=1 To 255 : Color(i,i,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next ; red gradient
				Color(255,255,255)	
				SetFont(BigFont)
				Text(GraphicsWidth()/2,25,"Welcome to BlitzSonic Online!",1,0)
				Text(GraphicsWidth()/2,75,"From here, you'll enter info to",1, 0)
				Text(GraphicsWidth()/2,125,"join others worldwide, or peer to peer.",1,0)	
				
				key=GetKey()
				If key Then 
					If key=13 Then
						If txt$<>"" Then
							PlayerName$ = txt$
							txt$=""
						Else
							PlayerName$ ="Online_Name"
							txt$=""
						End If
						FlushKeys()
						Finished=1
					Else If key=8
						If Len(txt$)>0 Then txt$=Left$(txt$,Len(txt$)-1)
					Else If key>=32 And key<127
						if len(txt$)<=11 then txt$=txt$+Chr$(key)
					EndIf
				EndIf
				
				Text(GraphicsWidth()/2,150+FontHeight(),"Online Name : "+txt$,1,0)
				;If Finished=1 Then Text(GraphicsWidth()/2,205,"Welcome, "+PlayerName$+"!",1,0)
				Flip()
			Until Finished=1
			Finished=0 : FlushKeys() : Delay(275)
		
			If Game\Online\Hosting=False Then
			Repeat 
				Cls()
				For i=1 To 255 : Color(0,i,i) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next ; blue gradient
				Color(255,255,255)	
				SetFont(BigFont)
				Text(GraphicsWidth()/2,25,"Welcome, "+PlayerName$+"!",1,0)
				Text(GraphicsWidth()/2,75,"Enter Host's IP",1, 0)
				Text(GraphicsWidth()/2,125,"0=localhost",1,0)	
				
				If Finished = 0 Then
				key=GetKey()
				If key Then 
					If key=13 Then
						If txt$<>"" Then
							If txt$="0" Then txt$=BP_GetMyIP$()
							Game\Online\IP$ = txt$
							txt$=""
							FlushKeys()
							Finished=1
						End If
					Else If key=8
						If Len(txt$)>0 Then txt$=Left$(txt$,Len(txt$)-1)
					Else If key>=32 And key<127
						txt$=txt$+Chr$(key)
					EndIf
				EndIf

				
				Text(GraphicsWidth()/2,150+FontHeight(),"Enter Host's IP:"+txt$,1,0)	
				ElseIf Finished=2
				key=GetKey()
				If key Then 
					If key=13 Then
						If txt$<>"" Then
							Game\Online\Port = txt$
							txt$=""
							FlushKeys()
							Finished=1
						End If
					Else If key=8
						If Len(txt$)>0 Then txt$=Left$(txt$,Len(txt$)-1)
					Else If key>=32 And key<127
						txt$=txt$+Chr$(key)
					EndIf
				EndIf
				If KeyHit(Key_Enter) Then Finished=1
				Text(GraphicsWidth()/2,150+FontHeight(),"Enter Host's IP:"+Game\Online\IP$,1,0)	
				Text(GraphicsWidth()/2,200+FontHeight(),"Enter a Port to use:"+txt$,1,0)
				EndIf
				
				Flip()
			Until Finished=1
			Finished=0 : FlushKeys() : Delay(275)
			EndIf
			connecttimer=millisecs()+1750
			Game\Online\Connected=1 ;!!!!!!!!!!!!
			Game\Online\SendUpdates=True
			Cls
			For i=1 To 255 : Color(i,0,i) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next
			Color(255,255,255) 
			Text 0,0,"Starting"
			Flip()
			If Game\Online\Hosting=True Then Game\Online\Status=BP_HostSession (PlayerName$,6,3,2222,100)  
			If Game\Online\Hosting=False Then Game\Online\Status=BP_JoinSession (PlayerName$,Game\Online\Port,Game\Online\IP,2222)

			Game\Online\GameType=BP_GameType

			Game\Online\Debug=False
			; may have connected, but see if it's possible to continue
			If Game\Online\Hosting=False Then
			SetFont(BigFont)
				Select Game\Online\Status
					;Handle any of the reasons if we couldn't join.
					Case BP_NOREPLY
						Cls
						For i=1 To 255 : Color(i,0,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next 
						Color(255,255,255)
						Text 0,0,"No reply in specified timeout period.. exiting"
						Flip()
						WaitKey
						End
					Case BP_IAMBANNED
						Cls
						For i=1 To 255 : Color(i,0,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next 
						Color(255,255,255)
						Text 0,0,"You have been banned from joining this game.. exiting"
						Flip()
						WaitKey
						End
					Case BP_GAMEISFULL
						Cls
						For i=1 To 255 : Color(i,0,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next 
						Color(255,255,255)
						Text 0,0,"The game is full.. exiting"
						Flip()
						WaitKey
						End
					Case BP_PORTNOTAVAILABLE
						Cls
						For i=1 To 255 : Color(i,0,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next
						Color(255,255,255) 
						Text 0,0,"Port: " + Game\Online\Port + " was not available.. exiting"
						Flip()
						WaitKey
						End
					Case BP_USERABORT
						Cls
						For i=1 To 255 : Color(i,0,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next 
						Color(255,255,255)
						Text 0,0,"Connection attempt aborted!"
						WaitKey
						End
					Default
						repeat
						Cls
						For i=1 To 255 : Color(0,i,0) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next 
						Color(255,255,255)
						SetFont(BigFont)
						Text GraphicsWidth()/2,50,"Connecting! (Hit [esc] to cancel)",True,True
						Text(GraphicsWidth()/2,225,"Online gameplay experience may change",True,True)
						Text(GraphicsWidth()/2,275,"from the original gameplay. Be very",True,True)
						Text(GraphicsWidth()/2,325,"responsible and think before doing",True,True)
						Text(GraphicsWidth()/2,375,"anything.",True,True)
						if keyhit(KEY_ESCAPE) then end
						Flip()
						until connecttimer<millisecs()
						;Delay(1500)
				End Select
			EndIf
		EndIf
	Else
		Cls
			For i=1 To 255 : Color(i,0,i) : Rect(0,i,GraphicsWidth(),GraphicsHeight(),1) : Next
			Color(255,255,255) 
			Text 0,0,"Starting Offline"
			Flip()
		Game\Online\Connected=0
	EndIf

	Function GetInput(stri$="")
	key=GetKey()
		If key Then 
			;If key=13 Then
			;	If txt$<>"" Then
			;		txt$=""
			;		FlushKeys()
			;	End If
			;Else 
			If key=8
				If Len(txt$)>0 Then txt$=Left$(txt$,Len(txt$)-1)
			Else If key>=32 And key<127
				txt$=txt$+Chr$(key)
			EndIf
		EndIf
	Return txt$
	End function

	Game_Startup()

	FreeImage Disclaimer
	FreeImage Avatars

	While(1)
		If (KeyHit(KEY_ESCAPE)) And Menu\Settings\Debug#=1 Then Game_End()
		WaitTimer(Game\Others\FpsLimit)
		Game\Online\Online=BP_Online
		Game_Update()
	Wend
	If Game\Online\Hosting=True Then BP_UDPMessage (0,12,"Host has left Session")
	BP_EndSession()
	End