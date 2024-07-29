
Dim GAMETYPE_NAME$(10)
Dim GAMETYPE_DESCRIPTION$(10)
GAMETYPE_NAME$(1)="Free Play"
GAMETYPE_NAME$(2)="Tag"
GAMETYPE_NAME$(3)="Hide and Seek"
GAMETYPE_NAME$(4)="Race"
GAMETYPE_NAME$(5)="Battle"
GAMETYPE_NAME$(6)="Capture the Flag"
GAMETYPE_NAME$(7)="Prop Hunt"
GAMETYPE_NAME$(8)="Manhunt"
GAMETYPE_NAME$(9)="Flood"
GAMETYPE_NAME$(10)="TBD"
GAMETYPE_DESCRIPTION$(1)="No specific rules. Mess around!"
GAMETYPE_DESCRIPTION$(2)="The host starts as it, tag other players!"
GAMETYPE_DESCRIPTION$(3)="Hide in the level, try not get found!"
GAMETYPE_DESCRIPTION$(4)="Race to the finish in a time attack!"
GAMETYPE_DESCRIPTION$(5)="Battle other players to the death!"
GAMETYPE_DESCRIPTION$(6)="Capture the enemy Flag!"
GAMETYPE_DESCRIPTION$(7)="Hide as stage setpieces, blend in!"
GAMETYPE_DESCRIPTION$(8)="Prevent set players from doing their runs!"
GAMETYPE_DESCRIPTION$(9)="Escape the rising water levels!"
GAMETYPE_DESCRIPTION$(10)="TBD"

Function Interface_Render_PlayerMenu(p.tPlayer)
	
	DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*0)*GAME_WINDOW_SCALE#)
	DrawRealText("Select", (30+15)*GAME_WINDOW_SCALE#, (30+30*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
	DrawRealText("Confirm", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
	DrawRealText("Back", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
	DrawRealText("Toggle Player Tag", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	SetScale(2*GAME_WINDOW_SCALE#,2*GAME_WINDOW_SCALE#)
	SetColor(Interface_Lives_R[InterfaceChar(Menu\Option)],Interface_Lives_G[InterfaceChar(Menu\Option)],Interface_Lives_B[InterfaceChar(Menu\Option)])
	Interface_DrawHead(GAME_WINDOW_W/2,GAME_WINDOW_H/2,Menu\Option-1)
	SetColor(255, 255, 255)
	If Menu\Option>CHAR_NONMODPLAYABLECOUNT Then
		DrawRealText( MODCHARS_NAME$( InterfaceChar(Menu\Option-CHAR_MOD1+1) ), GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
	Else
		DrawRealText(CharNames$(Menu\Option)+" "+CharNames2$(Menu\Option), GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
	EndIf
	
	SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
	
	If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*(Game\DeltaTime\Delta#*0.6) : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
	If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*(Game\DeltaTime\Delta#*0.6) : If Menu\ButtonSize2#>BUTTON_SCALELIMIT# Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#
	Menu\ButtonSize#=Menu\ButtonSize2#
	
	Select arrowtype
		Case -1
			arrowtype=19
		Case 1
			arrowtype=18
	End Select
	
	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2, 19)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+50*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2, 18)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	
	If Input\Pressed\Left Then 
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=CHAR_PLAYABLECOUNT
		PlaySmartSound(Sound_MenuMove)

	
		If UNLOCKEDCHAR[Menu\Option]=0 Then 
			Repeat
				Menu\Option=Menu\Option-1
			Until UNLOCKEDCHAR[Menu\Option]=1
		EndIf
		
		If Menu\Option<1 Then Menu\Option=CHAR_PLAYABLECOUNT
	EndIf
	
	
	If Input\Pressed\Right Then 
		Menu\Option=Menu\Option+1
		If Menu\Option>CHAR_PLAYABLECOUNT Then Menu\Option=1
		PlaySmartSound(Sound_MenuMove)
		
		If UNLOCKEDCHAR[Menu\Option]=0 Then 
			Repeat
				Menu\Option=Menu\Option+1
			Until UNLOCKEDCHAR[Menu\Option]=1
		EndIf
		
		If Menu\Option>CHAR_PLAYABLECOUNT Then Menu\Option=1
	EndIf
	
	If Input\Pressed\ActionJump  Then
		Menu\Character[1]=Menu\Option
		PostEffect_Create_FadeIn(0.004, 10, 10, 10)
		PlaySmartSound(Sound_MenuAccept)
		pp(1)\NewCharacter=Menu\Character[1]
		Game\CheaterChangedCharacter=1
		FlushKeys()
		UnPause()
	EndIf
	If Input\Pressed\ActionSkill3  Then
		Select Game\Online\GameType
		Case GAME_TYPE_TAG, GAME_TYPE_HIDENSEEK:
		Default:
			PlaySmartSound(Sound_MenuMove)
			onlineplayer(1)\Online\ShowTag= Not onlineplayer(1)\Online\ShowTag
			BP_UDPMessage(0,24,onlineplayer(1)\Online\ShowTag)
		End Select
	EndIf
	If Input\Pressed\ActionRoll Then
		PlaySmartSound(Sound_MenuBack)
		Menu\PauseScreen=0 : Menu\Option=1
	EndIf	
	
End Function
Function Interface_Render_HostMenu(p.tPlayer)

	DrawRealText("Game Type", 12*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 255, 23, 23)
	DrawRealText(GAMETYPE_DESCRIPTION(Game\Online\GameType+1),GAME_WINDOW_W/2, (85)*GAME_WINDOW_SCALE#, (Interface_Text_3), 1, 0, 63, 63, 63)
	If Menu\Option2=1 Then
		DrawRealText(GAMETYPE_NAME(Game\Online\GameType+1),GAME_WINDOW_W/2, (65)*GAME_WINDOW_SCALE#, (Interface_TextButtons2_1), 1, 0, 63, 63, 63)
	Else
		DrawRealText(GAMETYPE_NAME(Game\Online\GameType+1),GAME_WINDOW_W/2, (65)*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1, 0, 63, 63, 63)
	EndIf
	DrawRealText("Stage", 12*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(75)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 23, 128, 23)
	If Menu\LoadThumbnailAndMissions Then
		Menu_Stage_LoadThumbnailAndMissions(Menu\Option)
		Menu\LoadThumbnailAndMissions=False
	EndIf
	Menu_UpdateStageNames(Menu\Option)
	Menu_Stage_DrawThumbnailAndMissions(GAME_WINDOW_W/2,GAME_WINDOW_H/2+125*GAME_WINDOW_SCALE#,20*GAME_WINDOW_SCALE#)
	If Menu\Option<=StageAmount And Menu\Option2=2 Then
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-200*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(65)*GAME_WINDOW_SCALE#,19)
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+200*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(65)*GAME_WINDOW_SCALE#,18)
		DrawRealText(Menu\StageName$,GAME_WINDOW_W/2, GAME_WINDOW_H/2+(65)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
	EndIf
	If Input\Pressed\Right And Menu\Option2=2 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>StageAmount Then Menu\Option=1
		Menu\LoadThumbnailAndMissions=True

	EndIf
	If Input\Pressed\Down Or  Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2+1
		If Menu\Option2>2 Then Menu\Option2=1
	EndIf
	If Input\Pressed\Left And Menu\Option2=2 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=StageAmount
		Menu\LoadThumbnailAndMissions=True

	EndIf
	If Input\Pressed\ActionJump Then
		PlaySmartSound(Sound_MenuAccept)
		Select Menu\Option2
		Case 1:
			BP_SetGameType(Game\Online\GameType+1)
			If Game\Online\GameType>3 Then 	BP_SetGameType(0)
			Info("Gametype set to " + GAMETYPE_NAME(Game\Online\GameType+1))
			Game\Online\GTState=0
			Select Game\Online\GameType:
			Case GAME_TYPE_TAG
				it=Rand(1, BP_GetNumberOfPlayers%())
				For op.tPlayer = Each tPlayer
						If op\Online\NetID=it Then	Player_SetTagMode(op)
						If op\Online\NetID<>it Then op\Online\TagMode=TAG_NOT_IT : op\Online\TagTimer=0 : BP_UDPMessage(0,UDPMSG_MESSAGE, op\Online\Name$+" is Not It!") : op\Online\TagCoolDown=3.5*secs#	
				Next
			End Select
		Case 2:
			PlaySmartSound(Sound_MenuAccept)
			Menu\SelectedStage=Menu\Option
			Menu_Stage_LoadMissions(Menu\SelectedStage, True)
			Menu_GoToStage_SetMission(1)
			Chatting\Allowed=0
			BP_UDPMessage (0,6, StageName(Menu\SelectedStage))
			Game_Stage_Quit(2)
			FlushKeys()
			UnPause()
		End Select
	
	EndIf	
	If Input\Pressed\ActionRoll Then
		PlaySmartSound(Sound_MenuBack)
		Menu\PauseScreen=0 : Menu\Option=1
	EndIf	
	If Input\Pressed\ActionSkill3  Then
		PlaySmartSound(Sound_EggmanHurt)
		Game\Online\PVP = Not Game\Online\PVP
		Select Game\Online\PVP
			Case True : Info("PVP is ENABLED!", 0,255,255)
			Case False : Info("PVP is Disabled...", 0,255,255)
		End Select
		BP_UDPMessage(0,28,Game\Online\PVP+"/"+Game\Online\RaceLimit+"/")
	EndIf
	If Input\Pressed\ActionSkill2  Then
		PlaySmartSound(Sound_Switch)
		Game\Online\RaceLimit = Not Game\Online\RaceLimit
		Select Game\Online\RaceLimit
			Case True : Info("Race Timer will now start after the first player clears..", 0,255,255)
			Case False : Info("All players must clear the race to clear the mode", 0,255,255)
		End Select
		BP_UDPMessage(0,28,Game\Online\PVP+"/"+Game\Online\RaceLimit+"/")
	EndIf
		DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyJump[Menu\Settings\PrimaryController#])
		DrawRealText("PVP", GAME_WINDOW_W/2+(-10-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, GAME_WINDOW_W/2-(40+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#)
		DrawRealText("Select", GAME_WINDOW_W/2+(-10+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyRoll[Menu\Settings\PrimaryController#])
		DrawRealText("Move", GAME_WINDOW_W/2+(-10-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#)
		DrawRealText("Back", GAME_WINDOW_W/2+(-10+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
End Function

Function DrawPingMenu()
	no=1
	For p.tPlayer = Each tPlayer
		ninfo.NetInfo = BP_FindID(p\Online\NetID)
		SetColor(Interface_Lives_R[InterfaceChar(p\RealCharacter)],Interface_Lives_G[InterfaceChar(p\RealCharacter)],Interface_Lives_B[InterfaceChar(p\RealCharacter)])
		Interface_DrawHead(GAME_WINDOW_W-20*GAME_WINDOW_SCALE, GAME_WINDOW_H-(no*20)*GAME_WINDOW_SCALE#,p\RealCharacter-1)
		SetColor(255,255,255)
		DrawRealText(ninfo\Name + " :" + ninfo\Net_id, GAME_WINDOW_W-40*GAME_WINDOW_SCALE, GAME_WINDOW_H-(no*20)*GAME_WINDOW_SCALE#, (Interface_Text_1), 2, 0, 63, 63, 63)
		no=no+1
		
	Next
End Function
Function DrawGametypeOverlay()
	Select Game\Online\GameType:
	Case GAME_TYPE_RACE
		Select Game\Online\GTState
			Case 0: DrawRealText("Waiting for other players to load.", GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			Case 1: DrawBetterNumber(Game\Online\Countdown/secs#, GAME_WINDOW_W/2.0, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#, 2, 0)
			Case 2: If Game\Online\Countdown<60*secs# And Game\Online\Countdown>0.5*secs# Then DrawBetterNumber(Game\Online\Countdown/secs#, GAME_WINDOW_W/2.0, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#, 2, 0)
			Case 3: DrawRealText("Race Finished", GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1,0,128,128,16)
		End Select
	Case GAME_TYPE_TAG
		Select Game\Online\GTState
			Case 1: DrawBetterNumber(Game\Online\Countdown/secs#, GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, 2, 0)
			Case 3: DrawRealText("Game Finished!", GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1,0,128,128,16)
		End Select
	Case GAME_TYPE_HIDENSEEK
		Select Game\Online\GTState
			Case 2: DrawBetterNumber(Game\Online\Countdown/secs#, GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, 2, 0)
			Case 4: DrawRealText("Game Finished!", GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1,0,128,128,16)
		End Select
	End Select
End Function

Function Interface_Render_Stage_Spectator(p.tPlayer)

		DrawRealText("SPECTATING", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)

		DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawRealText("Switch Player", (30+30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawRealText("Viewing: " + Game\Online\ViewName, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawImageEx(INTERFACE(Interface_Keys), 			(30)*GAME_WINDOW_SCALE#, 						(30+30*4)*GAME_WINDOW_SCALE#, 61)
		DrawImageEx(INTERFACE(Interface_Keys_small), 	(30)*GAME_WINDOW_SCALE#-7*GAME_WINDOW_SCALE#, 	(30+30*4)*GAME_WINDOW_SCALE#, 5)
		DrawImageEx(INTERFACE(Interface_Keys_small), 	(30)*GAME_WINDOW_SCALE#+2*GAME_WINDOW_SCALE#, 	(30+30*4)*GAME_WINDOW_SCALE#, 31)
		DrawImageEx(INTERFACE(Interface_Keys_small), 	(30)*GAME_WINDOW_SCALE#+7*GAME_WINDOW_SCALE#, 	(30+30*4)*GAME_WINDOW_SCALE#, 31)
		DrawRealText("Close Spectator Mode", 			(30+15)*GAME_WINDOW_SCALE#, 					(30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D