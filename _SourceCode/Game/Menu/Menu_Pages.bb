Menu\Settings\PrimaryController#=1

Function DrawSmartButton(buttonno, text$, x#, y#, secondary=False, small=False, inactive=False)
	If secondary=False Then
		option=Menu\Option
		If option=buttonno Then
			If Menu\ButtonState1=0 Then Menu\ButtonSize1#=Menu\ButtonSize1#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#<0 Then Menu\ButtonState1=1 : Menu\ButtonSize1#=0
			If Menu\ButtonState1=1 Then Menu\ButtonSize1#=Menu\ButtonSize1#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#>BUTTON_SCALELIMIT# Then Menu\ButtonState1=0 : Menu\ButtonSize1#=BUTTON_SCALELIMIT#
			Menu\ButtonSize#=Menu\ButtonSize1#
		EndIf
	ElseIf secondary Then
		option=Menu\Option2
		If option=buttonno Then
			If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
			If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT# Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#
			Menu\ButtonSize#=Menu\ButtonSize2#
		EndIf
	EndIf

	Select small
		Case False: buttontype=Interface_Buttons_1 : buttontypesize=1 : buttontypetext1=Interface_TextButtons2_1 : buttontypetext2=Interface_TextButtons_1
		Case True: buttontype=Interface_Buttons_2 : buttontypesize=1.5 : buttontypetext1=Interface_TextButtons2_2 : buttontypetext2=Interface_TextButtons_2
	End Select

	If inactive Then
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(buttontype), x#, y#, 2)
		DrawRealText(Text$, x#, y#+2.5*buttontypesize, (buttontypetext2), 1)
	Else
		If option=buttonno Then
			SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
			DrawImageEx(INTERFACE(buttontype), x#, y#, 1)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			DrawRealText(Text$, x#, y#+2.5*buttontypesize, (buttontypetext1), 1)
		Else
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(buttontype), x#, y#, 0)
			DrawRealText(Text$, x#, y#+2.5*buttontypesize, (buttontypetext2), 1)
		EndIf
	EndIf
End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_Loading_Update()

	Menu\Music=0
	Menu\Background=3
	Menu\ShowCards=False
	Menu\ControlsToShow=Menu\Menu

	If (Not(Menu\NewMenu=MENU_GAMEOVER# Or Menu\NewMenu=MENU_EMBLEM#)) And (Not(Menu\ExitedAStage=1)) Then
		If Menu\TitleCardTimer>0 Then Menu\TitleCardTimer=Menu\TitleCardTimer-timervalue#
		If Not(Menu\TitleCardTimer>0) Then
			If Menu\MustLoadStage=0 Then
				Menu\TitleCardTimer=1*secs#
				PlaySmartSound(Sound_TitleCard)
				Menu\MustLoadStage=1
			ElseIf Menu\MustLoadStage=1 Then
				Menu\PreviousStage=Menu\Stage
				Menu\Stage=Menu\SelectedStage
				Game_Stage_Restart()
				Menu\MustLoadStage=0
			EndIf
		Else
			DrawTitleCardStuff(True)
		EndIf
	EndIf

End Function

Function Menu_Close_Update()

	Menu\Music=0
	Menu\Background=3
	Menu\ShowCards=False
	Menu\ControlsToShow=Menu\Menu

	Game_End()

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Start_Update(d.tDeltaTime)

	Menu\Music=2
	Menu\Background=2
	Menu\ShowCards=False
	Menu\ControlsToShow=Menu\Menu

	Select Menu\TitleState
		Case 0:
			If Menu\TitleCardTimer>0 Then
				Menu\TitleCardTimer=Menu\TitleCardTimer-timervalue#
				If Menu\TitleCardTimer<1*secs# Then
					SmartSound(Sound_LogoHum1) : SmartSound(Sound_LogoHum2) : SmartSound(Sound_LogoConnect)
					Menu\TitleStateValues#[0]=0.25
					Menu\TitleStateValues#[1]=15
					Menu\TitleStateValues#[2]=1.00
					Menu\TitleStateValues#[3]=0
					Menu\TitleStateValues#[4]=160
					Menu\TitleState=1 : PlaySmartSound(Sound_LogoHum1)
				EndIf
			Else
				Menu\TitleCardTimer=1.5*secs#
			EndIf
		Case 1:
			If Menu\TitleStateValues#[1]>1 Then
				Menu\TitleStateValues#[1]=Menu\TitleStateValues#[1]-0.25*d\Delta
			Else
				Menu\TitleStateValues#[1]=1
				Menu\TitleState=2 : PlaySmartSound(Sound_LogoConnect)
			EndIf
		Case 2:
			If Menu\TitleStateValues#[2]<10 Then
				Menu\TitleStateValues#[2]=Menu\TitleStateValues#[2]+0.07525*d\Delta
				If Menu\TitleStateValues#[3]<1.0 Then Menu\TitleStateValues#[3]=Menu\TitleStateValues#[3]+0.015*d\Delta
			Else
				Menu\TitleStateValues#[2]=15
				Menu\TitleStateValues#[3]=1.0
				If Menu\Settings\Theme#=7 Then Menu\TitleState=3 Else Menu\TitleState=4 : Menu\PressStartTimer=2.0*secs#
				PlaySmartSound(Sound_LogoHum2)
			EndIf
		Case 3:
			If Menu\TitleStateValues#[4]>0 Then 
				Menu\TitleStateValues#[4]=Menu\TitleStateValues#[4]-4.75*d\Delta
			Else
				Menu\TitleStateValues#[4]=0
				Menu\TitleState=4 : Menu\PressStartTimer=2.0*secs#
			EndIf
	End Select

	If Menu\TitleState>0 Then
		If Menu\TitleState<2 Then
			SetScale(GAME_WINDOW_SCALE#*Menu\TitleStateValues#[1], GAME_WINDOW_SCALE#*Menu\TitleStateValues#[1])
			DrawImageEx(INTERFACE(Interface_Logo_ring), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		ElseIf Menu\TitleState<4 Then
			DrawImageEx(INTERFACE(Interface_Logo), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetAlpha(1-Menu\TitleStateValues#[3])
			DrawImageEx(INTERFACE(Interface_Logo_flash), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetAlpha(1)
		Else
			DrawImageEx(INTERFACE(Interface_Logo), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)	
		EndIf
	EndIf

	

	If Menu\TitleState=2 Then
		SetScale(GAME_WINDOW_SCALE#*Menu\TitleStateValues#[2], GAME_WINDOW_SCALE#*Menu\TitleStateValues#[2])
		SetAlpha(1-Menu\TitleStateValues#[3])
		DrawImageEx(INTERFACE(Interface_LogoRipple), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
		SetAlpha(1.0)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	EndIf

	If Menu\TitleState>=3 Then
		If Menu\PressStartTimer>0 Then Menu\PressStartTimer=Menu\PressStartTimer-timervalue#
		If Not(Menu\PressStartTimer>0) Then
			Menu\PressStartTimer=4.0*secs#
		ElseIf Menu\PressStartTimer>2.0*secs# Then
			SetAlpha((Menu\PressStartTimer/secs#-1))
		ElseIf Menu\PressStartTimer>0.0*secs# Then
			SetAlpha(Abs(1-Menu\PressStartTimer/secs#))
		EndIf
		DrawRealText("Press Start to Begin", GAME_WINDOW_W/2, GAME_WINDOW_H/2+120*GAME_WINDOW_SCALE#, (Interface_TextControls_1), 1)
		DrawRealText("SAGE 2020 Demo", GAME_WINDOW_W/2, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, (Interface_TextControls_1), 1)
		SetAlpha(1.00)

		If Input\Pressed\Start Or Input\Pressed\ActionJump Then
			Menu\Menu2=0
			PlaySmartSound(Sound_MenuAccept)
			Menu\Transition=1
			Select Menu\FirstTime
				Case 0: Menu\NewOption=1 : Menu\OptionOrder=1 : Menu\NewMenu=MENU_WELCOME#
				Case 1: Menu\NewOption=1 : Menu\NewMenu=MENU_MAIN#
			End Select
		EndIf
	Else
		If Input\Pressed\Start Or Input\Pressed\ActionJump Then
			Menu\TitleState=4
		EndIf
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Welcome_Update()

	Menu\Music=0
	Menu\Background=3
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	Select Menu\OptionOrder
		Case 1:

			DrawRealText("Hello there!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("It looks like this is your first time playing Sonic World DX.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Please choose your computer's graphical capabilities:", GAME_WINDOW_W/2, GAME_WINDOW_H/2-60*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)

			DrawSmartButton(1, "Weak", GAME_WINDOW_W/2, GAME_WINDOW_H/2-20*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Medium", GAME_WINDOW_W/2, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#)
			DrawSmartButton(3, "Strong", GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#)

			SetColor(255,0,0):DrawRealText("Choose weak for a smoother experience.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+120*GAME_WINDOW_SCALE#, (Interface_Text_3), 1):SetColor(255,255,255)

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Menu\Option=Menu\Option+1
				If Menu\Option>3 Then Menu\Option=1
			EndIf

			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=3
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Menu\Option
					Case 1:
						Menu\Settings\DepthOfField#=0
						Menu\Settings\Shadows#=0
						Menu\Settings\MotionBlur#=0
						Menu\Settings\SunRays#=0
						Menu\Settings\BumpMaps#=0
					Case 2:
						Menu\Settings\DepthOfField#=0
						Menu\Settings\Shadows#=1
						Menu\Settings\MotionBlur#=0
						Menu\Settings\SunRays#=0
						Menu\Settings\BumpMaps#=1
					Case 3:
						Menu\Settings\DepthOfField#=0
						Menu\Settings\Shadows#=1
						Menu\Settings\MotionBlur#=0
						Menu\Settings\SunRays#=0
						Menu\Settings\BumpMaps#=1
				End Select
				Menu\Option=1
				Menu\OptionOrder=2
			EndIf

		Case 2:

			DrawRealText("Graphical settings have been set up.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("For further customization, such as controls setup with gamepad,", GAME_WINDOW_W/2, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("or game themes, be sure to see the options.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Please remember this is a work in progress demo.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+35*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			SetColor(0,255,255):DrawRealText("Enjoy!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#, (Interface_Text_3), 1):SetColor(255,255,255)

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\OptionOrder=3
			EndIf

		Case 3:

			SetColor(255,0,0)
			DrawRealText("WARNING:", GAME_WINDOW_W/2, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Some content in this game may not be suitable for those with epilepsy.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("If you are known to have epilepsy, this game is not recommended for you.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			SetColor(255,255,255)

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				Menu\Menu2=0
				PlaySmartSound(Sound_MenuAccept)
				Menu\Transition=1
				Menu\FirstTime=1
				SaveGame()
				Menu\NewOption=1 : Menu\NewMenu=MENU_MAIN#
			EndIf

	End Select

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Main_Update()

	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	DrawSmartButton(1, "Play", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#)
	DrawSmartButton(2, "Gallery", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#,False,False,True)
	DrawSmartButton(3, "Options", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
	DrawSmartButton(4, "Credits", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#)
	DrawSmartButton(5, "Exit", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option=2 Then Menu\Option=3
		If Menu\Option>5 Then Menu\Option=1
	EndIf
	If KeyHit(KEY_F) Then
		GetStageAmount()
		LoadStageList()
	EndIf 
	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option=2 Then Menu\Option=1
		If Menu\Option<1 Then Menu\Option=5
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		Menu\Menu2=0
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Select Menu\Option
			Case 1:
				CountEmblems()
				Menu\NewOption=1 : Menu\NewMenu=MENU_PLAY#
			Case 2:
				For i = 1 To 3 : Menu\Character[i]=InterfaceChar(Menu\Character[i]) : Next
				Menu\NewOption=Menu\Character[1] : Menu\NewMenu=MENU_BIOS# : ChannelVolume(Menu\Channel_Menu,0) : Menu\CharacterMeshAnimation=1
				Menu\NewOption2=1
			Case 3:
				Menu\NewOption=1 : Menu\NewMenu=MENU_OPTIONS# : Menu\NewMenu2=0 : Menu\OptionOrder=0
				Menu\Settings\NewResolution#=Menu\Settings\Resolution#
				For i=1 To 2
				Menu\OptionsForceKeyJump[i]=CONTROLS(i,INPUT_BUTTON_ACTIONJUMP)
				Menu\OptionsForceKeyRoll[i]=CONTROLS(i,INPUT_BUTTON_ACTIONROLL)
				Menu\OptionsForceKeySkill2[i]=CONTROLS(i,INPUT_BUTTON_ACTIONSKILL2)
				Menu\OptionsForceKeyAct[i]=CONTROLS(i,INPUT_BUTTON_ACTIONACT)
				Next
				For i=0 To 17 : CONTROLS_NEWGAMEPAD(i) = CONTROLS_GAMEPAD(i) : Next
			Case 4:
				Menu\NewOption=0 : Menu\NewMenu=MENU_CREDITS# : Menu\CreditsTimer=0
			Case 5:
				Menu\NewOption=0 : Menu\NewMenu=MENU_CLOSE#
		End Select
	EndIf


End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Credits_Update(scroll#=200)

	Menu\Music=4
	Menu\Background=3
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\CreditsTimer>0 Then Menu\CreditsTimer=Menu\CreditsTimer-timervalue#
	If Not(Menu\CreditsTimer>0) Then
		Menu\CreditsTimer=scroll#*secs#
	Else
		Menu_DrawCredits(GAME_WINDOW_W/2, GAME_WINDOW_H/2+((Menu\CreditsTimer-(scroll#-8.75)*secs#)/50)*GAME_WINDOW_SCALE#)
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=4 : Menu\NewMenu=MENU_MAIN#
	EndIf

	If Input\Pressed\ActionDrift Then
		PlaySmartSound(Sound_MenuAccept)
		ExecFile("http://sonicworldfangame.com/")
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_GameOver_Update()

	Menu\Music=5
	Menu\Background=3
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	Select Menu\GameOverType
		Case 0: DrawRealText("GAME OVER", GAME_WINDOW_W/2, GAME_WINDOW_H/2, (Interface_TextOvers_1), 1)
		Case 1: DrawRealText("TIME OVER", GAME_WINDOW_W/2, GAME_WINDOW_H/2, (Interface_TextOvers_2), 1)
	End Select

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		If Menu\MarathonMode=0 Then
			Menu\NewOption=Menu\SelectedStage
			If Menu\SelectedStage>0 Then
				Menu\NewMenu=MENU_STAGE2#
			Else
				Menu\NewMenu=MENU_STAGESPECIAL#
			EndIf
		Else
			Menu_GoToStage()
		EndIf
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Emblem_Update()

	Menu\Music=0
	Menu\Background=3
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\Transition=0 Then
	Select Menu\OptionOrder
		Case 0:
			Menu\LoadedEmblemYet=0
			PlaySmartSound(Sound_Emblem)
			Menu\MeshChange = 1
			Menu\OptionOrder=1
		Case 1:
		If Menu\LoadedEmblemYet=1 Then
			If Menu\EmblemsGot>0 Then
				SetColor(255,230,43)
				CountEmblems()
				DrawRealText(EMBLEMS, GAME_WINDOW_W/2, GAME_WINDOW_H/2+20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				SetColor(255,255,255)

				Select Menu\EmblemsGot
					Case 1:
						DrawRealText("You got "+Menu\EmblemsGot+" emblem!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					Default:
						DrawRealText("You got "+Menu\EmblemsGot+" emblems!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				End Select
				DrawRealText("Complete different missions in stages to get an emblem.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				DrawRealText("And get an S rank to get another.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			ElseIf Menu\EmblemsGot<0 Then
				If Not(ChannelPlaying(Menu\Channel_Emblem)) Then Menu\Channel_Emblem=PlaySmartSound(Sound_Emerald)
				DrawRealText("You got a Chaos Emerald and a Sol Emerald!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				If Abs(Menu\EmblemsGot)<7 Then DrawRealText("Complete all Special Stages to unlock super forms.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			EndIf

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump Or Input\Pressed\Start Then
				If Menu\EmblemsGot>0 Then
					CountEmblems()
					Menu\UnlockedWho$=""
					For c=1 To TEAM_TEAMCOUNT
						If EMBLEMS>=TOUNLOCKTEAM[c] And TOUNLOCKTEAM[c]>0 And UNLOCKEDTEAM[c]=0 Then
							If Len(Menu\UnlockedWho$)>0 Then Menu\UnlockedWho$=Menu\UnlockedWho$+","
							UNLOCKEDTEAM[c]=1 : Menu\OptionOrder=2
							Select c
								Case TEAM_SONIC:
									UNLOCKEDCHAR[CHAR_SON]=1
									UNLOCKEDCHAR[CHAR_TAI]=1
									UNLOCKEDCHAR[CHAR_KNU]=1
								Case TEAM_DARK:
									UNLOCKEDCHAR[CHAR_SHA]=1
									UNLOCKEDCHAR[CHAR_ROU]=1
									UNLOCKEDCHAR[CHAR_OME]=1
								Case TEAM_ROSE:
									UNLOCKEDCHAR[CHAR_AMY]=1
									UNLOCKEDCHAR[CHAR_CRE]=1
									UNLOCKEDCHAR[CHAR_BIG]=1
								Case TEAM_CHAOTIX:
									UNLOCKEDCHAR[CHAR_ESP]=1
									UNLOCKEDCHAR[CHAR_CHA]=1
									UNLOCKEDCHAR[CHAR_VEC]=1
								Case TEAM_SOL:
									UNLOCKEDCHAR[CHAR_BLA]=1
									UNLOCKEDCHAR[CHAR_SIL]=1
									UNLOCKEDCHAR[CHAR_MAR]=1
								Case TEAM_OLDIES:
									UNLOCKEDCHAR[CHAR_MIG]=1
									UNLOCKEDCHAR[CHAR_RAY]=1
									UNLOCKEDCHAR[CHAR_HBO]=1
								Case TEAM_HOOLIGAN:
									UNLOCKEDCHAR[CHAR_NAC]=1
									UNLOCKEDCHAR[CHAR_BEA]=1
									UNLOCKEDCHAR[CHAR_BAR]=1
								Case TEAM_BABYLON:
									UNLOCKEDCHAR[CHAR_JET]=1
									UNLOCKEDCHAR[CHAR_WAV]=1
									UNLOCKEDCHAR[CHAR_STO]=1
								Case TEAM_RELIC:
									UNLOCKEDCHAR[CHAR_SHD]=1
									UNLOCKEDCHAR[CHAR_TIK]=1
									UNLOCKEDCHAR[CHAR_CHO]=1
								Case TEAM_ROBOTNIK:
									UNLOCKEDCHAR[CHAR_MET]=1
									UNLOCKEDCHAR[CHAR_TDL]=1
									UNLOCKEDCHAR[CHAR_MKN]=1
							End Select
							Menu\UnlockedWho$=Menu\UnlockedWho$+" "+SingleTeamNames$(c)
						EndIf
					Next
					For c=1 To CHAR_NORMALCOUNT
						If EMBLEMS>=TOUNLOCKCHAR[c] And TOUNLOCKCHAR[c]>0 And UNLOCKEDCHAR[c]=0 Then
							If Len(Menu\UnlockedWho$)>0 Then Menu\UnlockedWho$=Menu\UnlockedWho$+","
							UNLOCKEDCHAR[c]=1 : Menu\OptionOrder=2
							Menu\UnlockedWho$=Menu\UnlockedWho$+" "+SingleCharNames$(c)
						EndIf
					Next
					If EMBLEMS>=TOUNLOCKSPECIALSTAGES[0] And TOUNLOCKSPECIALSTAGES[0]>0 And UNLOCKEDSPECIALSTAGES[0]=0 Then
						If Len(Menu\UnlockedWho$)>0 Then Menu\UnlockedWho$=Menu\UnlockedWho$+","
						UNLOCKEDSPECIALSTAGES[0]=1 : Menu\OptionOrder=2
						Menu\UnlockedWho$=Menu\UnlockedWho$+" Special Stages"
					EndIf
				ElseIf Menu\EmblemsGot<0 Then
					UNLOCKEDEMERALDS[Abs(Menu\EmblemsGot)]=1
					If Abs(Menu\EmblemsGot)=7 Then Menu\OptionOrder=2 : Menu\UnlockedWho$=" super forms"
				EndIf

				If Menu\OptionOrder=1 Then
					PlaySmartSound(Sound_MenuBack)
					Menu\Transition=1
					StopChannel(Menu\Channel_Emblem)
					If Menu\MarathonMode=0 Then
						Menu\NewOption=Menu\SelectedStage
						If Menu\EmblemsGot<0 Then
							Menu\NewMenu=MENU_STAGESPECIAL#
						Else
							Menu\NewMenu=MENU_STAGE2#
						EndIf
					Else
						Menu_GoToStage()
					EndIf
				Else
					PlaySmartSound(Sound_Unlock)
					Select Rand(1,13)
						Case 1: Menu\Compliment$="Great job!"
						Case 2: Menu\Compliment$="Congratulations!"
						Case 3: Menu\Compliment$="Well done!"
						Case 4: Menu\Compliment$="Awesome work."
						Case 5: Menu\Compliment$="Outstanding!"
						Case 6: Menu\Compliment$="Piece of cake."
						Case 7: Menu\Compliment$="Too cool."
						Case 8: Menu\Compliment$="Keep it up!"
						Case 9: Menu\Compliment$="Without a doubt."
						Case 10: Menu\Compliment$="Amazing."
						Case 11: Menu\Compliment$="Did you cheat!?"
						Case 12: Menu\Compliment$="HOW?!"
						Case 13: Menu\Compliment$="Good job."
					End Select
				EndIf
				SaveGame()
			EndIf
		EndIf
		Case 2:
		If Menu\LoadedEmblemYet=1 Then
			If Menu\EmblemsGot>0 Then
				SetColor(255,230,43)
				CountEmblems()
				DrawRealText(EMBLEMS, GAME_WINDOW_W/2, GAME_WINDOW_H/2+20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				SetColor(255,255,255)
			EndIf

			DrawRealText("You unlocked"+Menu\UnlockedWho$+"!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText(Menu\Compliment$, GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				StopChannel(Menu\Channel_Emblem)
				If Menu\MarathonMode=0 Then
					Menu\NewOption=Menu\SelectedStage
					If Menu\EmblemsGot<0 Then
						Menu\NewMenu=MENU_STAGESPECIAL#
					Else
						Menu\NewMenu=MENU_STAGE2#
					EndIf
				Else
					Menu_GoToStage()
				EndIf
			EndIf
		EndIf
	End Select
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Pause_Update()
	StartDraw()
	SetBlend(FI_ALPHABLEND)
	SetAlpha(1.0)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetColor(255, 255, 255)
	
	Interface_RedRingCounter(True)
	
	DrawSmartButton(1, "Continue", GAME_WINDOW_W/2, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#)
	Select Menu\ChaoGarden
		Case 0:
			Select Menu\RestartMode
				Case 0: DrawSmartButton(2, "Reload Stage", GAME_WINDOW_W/2, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
				Case 1: DrawSmartButton(2, "Reload Stage & Keep Position", GAME_WINDOW_W/2, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
			End Select 
		Case 1:
			DrawSmartButton(2, "Restart", GAME_WINDOW_W/2, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#,False,False,True)
	End Select
	DrawSmartButton(3, "Quit", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#)

	DrawRealText("PAUSE", GAME_WINDOW_W/2, GAME_WINDOW_H/2-92.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)

	If Input\Pressed\Down And Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\ChaoGarden=1 And Menu\Option=2 Then Menu\Option=Menu\Option+1
		If Menu\Option>3 Then Menu\Option=1
	EndIf

	If Input\Pressed\Up And Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\ChaoGarden=1 And Menu\Option=2 Then Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=3
	EndIf
	
	If Input\Pressed\Left And Menu\Transition=0 And (Menu\Option=2 And Menu\ChaoGarden=0) Then 
		PlaySmartSound(Sound_MenuMove)
		Menu\RestartMode=Menu\RestartMode-1
		If Menu\RestartMode<0 Then Menu\RestartMode=1
	EndIf 
	
	If Input\Pressed\Right And Menu\Transition=0 And (Menu\Option=2 And Menu\ChaoGarden=0) Then 
		PlaySmartSound(Sound_MenuMove)
		Menu\RestartMode=Menu\RestartMode+1
		If Menu\RestartMode>1 Then Menu\RestartMode=0
	EndIf 
		
		

	
	


	If (Input\Pressed\ActionJump Or Input\Pressed\Start) And Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuAccept)
		If Menu\Option=3 Or (Menu\Option=2 And Menu\ChaoGarden=0) Then Game_Stage_Quit(Menu\Option)
		Menu\Transition=1
		If Menu\Option=3 Then Menu\RestartMode=0
	EndIf

	EndDraw()
End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_MarathonEnd_Update()

	Menu\Music=0
	Menu\Background=3
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\Transition=0 Then
	Select Menu\OptionOrder
		Case 0:
			PlaySmartSound(Sound_Unlock)
			Menu\OptionOrder=1
		Case 1:
			DrawRealText("You completed the marathon.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-15*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Congratulations!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\Transition=1
				Menu\NewMenu=MENU_PLAY#
				Menu\NewOption=6
			EndIf
	End Select
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;~IDEal Editor Parameters:
;~C#Blitz3D