Menu\Settings\PrimaryController#=1
Function Menu_DebugConsole()
	
	
	
	
	DrawRealText(Game\Interface\DebugConsoleString$, 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
	
	
	If KeyHit(KEY_A) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"a"
	If KeyHit(KEY_B) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"b"
	If KeyHit(KEY_C) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"c"
	If KeyHit(KEY_D) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"d"
	If KeyHit(KEY_E) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"e"
	If KeyHit(KEY_F) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"f"
	If KeyHit(KEY_G) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"g"
	If KeyHit(KEY_H) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"h"
	If KeyHit(KEY_I) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"i"
	If KeyHit(KEY_J) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"j"
	If KeyHit(KEY_K) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"k"
	If KeyHit(KEY_L) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"l"
	If KeyHit(KEY_M) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"m"
	If KeyHit(KEY_N) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"n"
	If KeyHit(KEY_O) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"o"
	If KeyHit(KEY_P) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"p"
	If KeyHit(KEY_Q) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"q"
	If KeyHit(KEY_R) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"r"
	If KeyHit(KEY_S) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"s"
	If KeyHit(KEY_T) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"t"
	If KeyHit(KEY_U) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"u"
	If KeyHit(KEY_V) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"v"
	If KeyHit(KEY_W) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"w"
	If KeyHit(KEY_X) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"x"
	If KeyHit(KEY_Y) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"y"
	If KeyHit(KEY_Z) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"z"
	
	If KeyHit(KEY_1) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"1"
	If KeyHit(KEY_2) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"2"
	If KeyHit(KEY_3) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"3"
	If KeyHit(KEY_4) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"4"
	If KeyHit(KEY_5) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"5"
	If KeyHit(KEY_6) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"6"
	If KeyHit(KEY_7) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"7"
	If KeyHit(KEY_8) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"8"
	If KeyHit(KEY_9) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"9"
	If KeyHit(KEY_0) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"0"
	
	If KeyHit(KEY_HYPHEN) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"-"
	
	If KeyHit(52) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"."
	
	If KeyHit(KEY_ARROW_DOWN) Then
		If Game\Interface\PreviousCommandOrder2<Game\Interface\PreviousCommandOrder Then Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder2+1
		Game\Interface\DebugConsoleString$=Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder2]
		
	EndIf	
	
	If KeyHit(KEY_ARROW_UP) Then
		If Game\Interface\PreviousCommandOrder2>0 Then Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder2-1
		Game\Interface\DebugConsoleString$=Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder2]
		
	EndIf	
	
	
	
	If KeyHit(KEY_SPACE) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+" "
	
	If KeyHit(KEY_BACKSPACE) Then 
		If Len(Game\Interface\DebugConsoleString$)>0 Then Game\Interface\DebugConsoleString$=Left(Game\Interface\DebugConsoleString$,Len(Game\Interface\DebugConsoleString$)-1)
	EndIf
	
	
	
	If KeyHit(KEY_ENTER) Or  Game\Interface\EnteredFavCommand=1 Then
		
		If Game\Interface\PreviousCommandOrder<16 Then Game\Interface\PreviousCommandOrder=Game\Interface\PreviousCommandOrder+1
		Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder]=Game\Interface\DebugConsoleString$
		Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder
		
		Select Game\Interface\DebugConsoleString$
			Case "i cant unlock anything legit"
				PlaySmartSound(Sound_MenuAccept)
				For c=1 To CHAR_NONMODPLAYABLECOUNT
					If Menu_CharacterInRelease(c) Then UNLOCKEDCHAR[c]=1
				Next
				
				
				UNLOCKEDSUPERS=1
			Case "frag is ruining my mesh uhul"
				PlaySmartSound(Sound_MenuAccept)
				Menu\Developer=Abs(Menu\Developer-1)
		End Select
		
		
		
		
		
		Game\Interface\DebugConsoleString$=""
		Game\Interface\EnteredFavCommand=0
		
		
		Game\Interface\DebugConsole=0
		Menu\InputRestrictTimer=0.25*secs
	EndIf
	
End Function
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
Function Menu_Play_Update(mode=0)
	
	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu
	
	If KeyHit(KEY_F2) Then LoadStageList()
	
	If Not mode=2 Then
		
		If Menu\NewMenu=Menu\Menu Then
			i=(25+BUTTON_PLACE1#)
			j=(53+BUTTON_PLACE1#)
		Else
			i=(25-BUTTON_PLACE1#)
			j=(53-BUTTON_PLACE1#)
		EndIf
		
		k=70
		
		
		DrawImageEx(INTERFACE(Interface_Icons), i*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#, 2)
		DrawBetterNumber(Menu\Wallet, j*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#)
		
		k=k+35
		
		DrawImageEx(INTERFACE(Interface_Icons),i*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#, 4)
		DrawBetterNumber(EMBLEMS, j*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#)
		
		k=k+35
		
		DrawImageEx(INTERFACE(Interface_Icons), i*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#, 32)
		DrawBetterNumber(REDRINGS, j*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#)
		
		k=k+35
		
		DrawImageEx(INTERFACE(Interface_Icons2),i*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#, 5)
		DrawBetterNumber(Game\Gameplay\Lives, j*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#)
		
		k=k+35
		
		DrawImageEx(INTERFACE(Interface_Icons2), i*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#, 3)
		DrawBetterNumber(TOKENS,j*GAME_WINDOW_SCALE#, k*GAME_WINDOW_SCALE#)
		
		
	EndIf
	
	Select mode
			
			
			
		Case 0:
			
			Select Menu\Option
				Case 1: t$="Enjoy action stages with fast paced platforming!"
				Case 2: t$="Explore everything the Chao Garden has to offer!"
				Case 3:	t$="Run through many stages back to back!"
				Case 4: t$="What secrets are in store on the island?"
			End Select
			DrawRealText(t$, GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-135*GAME_WINDOW_SCALE#+0*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			
			
			Select Menu\MemberSelect
				Case 1: c$="Single"
				Case 2: c$="Pair"
				Case 3: c$="Team"
			End Select
			DrawSmartButton(1, c$, GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Chao World", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#)
			DrawSmartButton(3, "Marathon", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#)
			DrawSmartButton(4, "The Island", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+70*GAME_WINDOW_SCALE#)
			
			
			If Menu\Option=1 And Menu\Transition=0 Then
				DrawArrow(GAME_WINDOW_W/2+(120)*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#)
				DrawArrow(GAME_WINDOW_W/2-(120)*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#,2)
			EndIf
			
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Menu\Option=Menu\Option+1
				If Menu\Option>4 Then Menu\Option=1
			EndIf
			
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=4
			EndIf
			
			If Input\Pressed\Left And Menu\Option=1 Then 
				PlaySmartSound(Sound_MenuMove)
				Menu\MemberSelect=Menu\MemberSelect-1
				If Menu\MemberSelect<1 Then Menu\MemberSelect=3
			EndIf
			
			If Input\Pressed\Right And Menu\Option=1 Then 
				PlaySmartSound(Sound_MenuMove)
				Menu\MemberSelect=Menu\MemberSelect+1
				If Menu\MemberSelect>3 Then Menu\MemberSelect=1
			EndIf
			
			
			If Input\Pressed\ActionJump   Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\Transition=1
				Select Menu\Option
					Case 2:
						Menu\Members=1
						Menu\ChaoGarden=1
						Menu\MarathonMode=0
						Menu\MemberToSelect=1
						Menu\NewOption2=1
						Menu\NewOption=1
						Menu\NewMenu=MENU_CHARACTERS#
					Case 3:
						LoadMarathonList()
						If Menu\MarathonExists Then Menu\NewOption=2 Else Menu\NewOption=1
						Menu\NewMenu=MENU_MARATHON#
						Menu\MarathonMode=1
					Case 4
						Menu\Members=1
						Menu\ChaoGarden=0
						Menu\CollectionRoom=1
						Menu\MarathonMode=0
						Menu\TutorialMode=0
						Menu\MemberToSelect=1
						Menu\NewOption2=1
						Menu\NewOption=1
						Menu\NewMenu=MENU_CHARACTERS#
					Case 1
						Menu\Members=Menu\MemberSelect
						Menu\CollectionRoom=0
						Menu\TutorialMode=0
						Menu\ChaoGarden=0
						Menu\MarathonMode=0
						Select Menu\MemberSelect
							Case 3:
								Menu\NewOption=1
								If Menu\Team>0 Then Menu\TeamOrder=Menu\Team-1 Else Menu\TeamOrder=1-1
								Menu\NewMenu=MENU_TEAMS#
							Default:
								Menu\MemberToSelect=1
								Menu\NewOption2=1
								Menu\NewOption=1
								Menu\NewMenu=MENU_CHARACTERS#
						End Select
						
				End Select
			EndIf
			
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewOption=1
				Menu\NewMenu=MENU_MAIN#
			EndIf
		Case 1:
			DrawSmartButton(1, "Single", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Pair", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#)
			DrawSmartButton(3, "Team", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#)
			If Menu\MarathonRandom Then
				DrawSmartButton(4, "Randomized: On", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#)
			Else
				DrawSmartButton(4, "Randomized: Off", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#)
			EndIf
			
			
			
			If Input\Pressed\Down Then
			 	PlaySmartSound(Sound_MenuMove)
			 	Menu\Option=Menu\Option+1
				If Menu\Option>4 Then Menu\Option=1
			EndIf
			
			If Input\Pressed\Up Then
			 	PlaySmartSound(Sound_MenuMove)
			 	Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=4
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Menu\Option
					Case 4:
						Menu\MarathonRandom=Abs(Menu\MarathonRandom-1)
					Default:
						Menu\Transition=1
						Menu\ChaoGarden=0
						If Menu\MarathonRandom Then
							Menu\Members=Menu\Option : Menu\MembersMarathon=Menu\Members
							Menu_GoToStage()
						Else
							For i = 1 To 3 : Menu\Character[i]=InterfaceChar(Menu\Character[i]) : Next
							Menu\Members=Menu\Option : Menu\MembersMarathon=Menu\Members
							Select Menu\Option
								Case 3:
									Menu\NewOption=1
									If Menu\Team>0 Then Menu\TeamOrder=Menu\Team-1 Else Menu\TeamOrder=1-1
									Menu\NewMenu=MENU_TEAMS#
								Default:
									Menu\MemberToSelect=1
									Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
									Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
									Menu\NewMenu=MENU_CHARACTERS#
							End Select
						EndIf
				End Select
			EndIf
			
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewOption=3
				Menu\NewMenu=MENU_PLAY#
			EndIf
		Case 2:
			DrawRealText("Play continuously through all stages.", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#+0*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("If you previously quit during a marathon,", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#+1*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("you can continue from where you left off.", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#+2*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			
			DrawSmartButton(1, "New Marathon", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+10*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Continue", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, False, False, Abs(Menu\MarathonExists-1))
			
			If Input\Pressed\Down Or Input\Pressed\Up Then
			 	Select Menu\Option
			 		Case 1: If Menu\MarathonExists Then Menu\Option=2 : PlaySmartSound(Sound_MenuMove)
			 		Case 2: Menu\Option=1 : PlaySmartSound(Sound_MenuMove)
			 	End Select
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\Transition=1
				Menu\NewOption=Menu\Members
				Menu\NewMenu=MENU_PLAYMARATHON#
				If Menu\Option=1 Then
					Menu\MarathonStage=1
					SaveMarathonList()
				EndIf
				If Menu\MarathonStage=0 Then Menu\MarathonStage=1
			EndIf
			
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewOption=4
				Menu\NewMenu=MENU_PLAY#
			EndIf
	End Select
	
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

	If (Not(Menu\NewMenu=MENU_GAMEOVER# Or Menu\NewMenu=MENU_EMBLEM# Or Menu\NewMenu=MENU_REDRING#)) And (Not(Menu\ExitedAStage=1)) Then
		If Menu\TitleCardTimer>0 Then Menu\TitleCardTimer=Menu\TitleCardTimer-timervalue#
		If Not(Menu\TitleCardTimer>0) Then
			If Menu\MustLoadStage=0 Then
				Menu\TitleCardTimer=1*secs#
				If Menu\Mission=MISSION_ENCORE# Then
					PlaySmartSound(Sound_TitleCardRuby)
				Else
					PlaySmartSound(Sound_TitleCard)
				EndIf
				
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
	
	Menu\Background=2
	Menu\ShowCards=False
	Menu\ControlsToShow=Menu\Menu
	
	If Menu\ThemeLogoStyle=1 Then
		Select Menu\TitleState
			Case 0:
				SmartSound(Sound_LogoHum1) : SmartSound(Sound_LogoHum2) : SmartSound(Sound_LogoConnect)
				Menu\TitleStateValues#[0]=0.25
				Menu\TitleStateValues#[1]=15
				Menu\TitleStateValues#[2]=-(GAME_WINDOW_W+750)+500
				Menu\TitleStateValues#[3]=+(GAME_WINDOW_W+(2048+1048))-500
				Menu\TitleStateValues#[4]=1.00
				Menu\TitleStateValues#[5]=0
				Menu\TitleStateValues#[6]=160
				Menu\TitleState=1 : PlaySmartSound(Sound_LogoHum1) : Menu\Channel_MenuIntro=PlaySound(MenuMusic_Intro)	
			Case 1:
				If Menu\TitleStateValues#[2]<GAME_WINDOW_W/2.0 Then
					Menu\TitleStateValues#[2]=Menu\TitleStateValues#[2]+60*d\Delta
				Else
					Menu\TitleStateValues#[2]=GAME_WINDOW_W/2.0
					Menu\TitleState=2 : PlaySmartSound(Sound_LogoHum1)
				EndIf
			Case 2:
				If Menu\TitleStateValues#[3]>GAME_WINDOW_W/2.0 Then
					Menu\TitleStateValues#[3]=Menu\TitleStateValues#[3]-60*d\Delta
				Else
					Menu\TitleStateValues#[3]=GAME_WINDOW_W/2.0
					Menu\TitleState=3 : Menu\Channel_Logo=PlaySmartSound(Sound_LogoHum2)
				EndIf
			Case 3:
				If Menu\TitleStateValues#[1]>1 Then 
					Menu\TitleStateValues#[1]=Menu\TitleStateValues#[1]-0.25*d\Delta
				Else
					Menu\TitleStateValues#[1]=1
					Menu\TitleState=4 : PlaySmartSound(Sound_LogoConnect)
				EndIf
			Case 4:
				If Menu\TitleStateValues#[4]<10 Then
					Menu\TitleStateValues#[4]=Menu\TitleStateValues#[4]+0.07525*d\Delta
					If Menu\TitleStateValues#[5]<1.0 Then Menu\TitleStateValues#[5]=Menu\TitleStateValues#[5]+0.015*d\Delta
				Else
					Menu\TitleStateValues#[4]=15
					Menu\TitleStateValues#[5]=1.0
					If Menu\Settings\Theme#=7 Then Menu\TitleState=5 Else Menu\TitleState=6 : Menu\PressStartTimer=2.0*secs#
				EndIf
			Case 5:
				If Menu\TitleStateValues#[6]>0 Then 
					Menu\TitleStateValues#[6]=Menu\TitleStateValues#[6]-4.75*d\Delta
				Else
					Menu\TitleStateValues#[6]=0
					Menu\TitleState=6 : Menu\PressStartTimer=2.0*secs#
				EndIf
		End Select
		
		
		If Menu\TitleState<4 Then
			SetScale(GAME_WINDOW_SCALE#*Menu\TitleStateValues#[1], GAME_WINDOW_SCALE#*Menu\TitleStateValues#[1])
			If Menu\TitleState<4 Then
				DrawImageEx(INTERFACE(Interface_Logo_SpeedRing), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			Else
				DrawImageEx(INTERFACE(Interface_Logo_Ring), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			EndIf
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			If Menu\TitleState<2 Then
				DrawImageEx(INTERFACE(Interface_Logo_SpeedSonic), Menu\TitleStateValues#[2], GAME_WINDOW_H/2.0-60*GAME_WINDOW_SCALE#)
			Else
				DrawImageEx(INTERFACE(Interface_Logo_Sonic), Menu\TitleStateValues#[2], GAME_WINDOW_H/2.0-60*GAME_WINDOW_SCALE#)
			EndIf
			If Menu\TitleState<3 Then
				DrawImageEx(INTERFACE(Interface_Logo_SpeedWorld), Menu\TitleStateValues#[3], GAME_WINDOW_H/2.0+5*GAME_WINDOW_SCALE#)
			Else
				DrawImageEx(INTERFACE(Interface_Logo_World), Menu\TitleStateValues#[3], GAME_WINDOW_H/2.0+5*GAME_WINDOW_SCALE#)
			EndIf
		ElseIf Menu\TitleState<6 Then
			DrawImageEx(INTERFACE(Interface_Logo), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetAlpha(1-Menu\TitleStateValues#[5])
			DrawImageEx(INTERFACE(Interface_Logo_Flash), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetAlpha(1)
		Else
			DrawImageEx(INTERFACE(Interface_Logo), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)	
		EndIf
		
		If Menu\TitleState=4 Then
			SetScale(GAME_WINDOW_SCALE#*Menu\TitleStateValues#[4], GAME_WINDOW_SCALE#*Menu\TitleStateValues#[4])
			SetAlpha(1-Menu\TitleStateValues#[5])
			DrawImageEx(INTERFACE(Interface_Logo_Ripple), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetAlpha(1.0)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		EndIf
		
		If Menu\TitleState>=5 Then
			If Menu\PressStartTimer>0 Then Menu\PressStartTimer=Menu\PressStartTimer-timervalue#
			If Not(Menu\PressStartTimer>0) Then
				Menu\PressStartTimer=2.0*secs#
			ElseIf Menu\PressStartTimer>1.0*secs# Then
				SetAlpha((Menu\PressStartTimer/secs#-1))
			ElseIf Menu\PressStartTimer>0.0*secs# Then
				SetAlpha(Abs(1-Menu\PressStartTimer/secs#))
			EndIf
			DrawRealText("Press Start to Begin", GAME_WINDOW_W/2, GAME_WINDOW_H/2+120*GAME_WINDOW_SCALE#, (Interface_TextControls_1), 1)
			SetAlpha(1.00)
			
			If Input\Pressed\Start Or Input\Pressed\ActionJump Then
				Menu\Menu2=0
				PlaySmartSound(Sound_MenuBegin)
				Menu\Transition=1
				Select Menu\FirstTime
					Case 0: Menu\NewOption=1 : Menu\OptionOrder=1 : Menu\NewMenu=MENU_WELCOME#
					Case 1: Menu\NewOption=1 : Menu\NewMenu=MENU_MAIN#
				End Select
			EndIf
		Else
			If Input\Pressed\Start Or Input\Pressed\ActionJump Then
				StopChannel(Menu\Channel_Logo)
				Menu\TitleState=6
			EndIf
		EndIf
		
	Else
		
		If Menu\PlayedMusic=0 Then Menu\Channel_MenuIntro=PlaySound(MenuMusic_Intro) : Menu\PlayedMusic=1
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
				DrawImageEx(INTERFACE(Interface_Logo_Ring), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			ElseIf Menu\TitleState<4 Then
				DrawImageEx(INTERFACE(Interface_Logo), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
				SetAlpha(1-Menu\TitleStateValues#[3])
				DrawImageEx(INTERFACE(Interface_Logo_Flash), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
				SetAlpha(1)
			Else
				DrawImageEx(INTERFACE(Interface_Logo), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)	
			EndIf
		EndIf
		
		If Menu\Settings\Theme#=7 Then
			If Menu\TitleState<4 Then
				DrawImageEx(INTERFACE(Interface_Logoxmas), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#-Menu\TitleStateValues#[4]*GAME_WINDOW_SCALE#)
			Else
				DrawImageEx(INTERFACE(Interface_Logoxmas), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			EndIf
		EndIf
		
		If Menu\TitleState=2 Then
			SetScale(GAME_WINDOW_SCALE#*Menu\TitleStateValues#[2], GAME_WINDOW_SCALE#*Menu\TitleStateValues#[2])
			SetAlpha(1-Menu\TitleStateValues#[3])
			DrawImageEx(INTERFACE(Interface_Logo_Ripple), GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-30*GAME_WINDOW_SCALE#)
			SetAlpha(1.0)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		EndIf
		
		If Menu\TitleState>=3 Then
			If Menu\PressStartTimer>0 Then Menu\PressStartTimer=Menu\PressStartTimer-timervalue#
			If Not(Menu\PressStartTimer>0) Then
				Menu\PressStartTimer=2.0*secs#
			ElseIf Menu\PressStartTimer>1.0*secs# Then
				SetAlpha((Menu\PressStartTimer/secs#-1))
			ElseIf Menu\PressStartTimer>0.0*secs# Then
				SetAlpha(Abs(1-Menu\PressStartTimer/secs#))
			EndIf
			DrawRealText("Press Start to Begin", GAME_WINDOW_W/2, GAME_WINDOW_H/2+120*GAME_WINDOW_SCALE#, (Interface_TextControls_1), 1)
			SetAlpha(1.00)
			
			If Input\Pressed\Start Or Input\Pressed\ActionJump Then
				Menu\Menu2=0
				PlaySmartSound(Sound_MenuBegin)
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
			DrawRealText("It looks like this is your first time playing Sonic World.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
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

			If Input\Pressed\ActionJump   Then
				PlaySmartSound(Sound_MenuAccept)
				Select Menu\Option
					Case 1:
						Menu\Settings\DepthOfField#=0
						Menu\Settings\Shadows#=0
						Menu\Settings\MotionBlur#=0
						Menu\Settings\sunrays#=0
						Menu\Settings\BumpMaps#=0
						Menu\Settings\ViewRange#=0
					Case 2:
						Menu\Settings\DepthOfField#=0
						Menu\Settings\Shadows#=2
						Menu\Settings\MotionBlur#=0
						Menu\Settings\sunrays#=0
						Menu\Settings\BumpMaps#=1
						Menu\Settings\ViewRange#=1
					Case 3:
						Menu\Settings\DepthOfField#=0
						Menu\Settings\Shadows#=2
						Menu\Settings\MotionBlur#=0
						Menu\Settings\sunrays#=0
						Menu\Settings\BumpMaps#=1
						Menu\Settings\ViewRange#=2
				End Select
				
				Select Menu\Settings\ViewRange#
					Case 0
						Menu\Settings\MeshViewRange#=5000
						Menu\Settings\ObjectViewRange#=150
					Case 1
						Menu\Settings\MeshViewRange#=7500
						Menu\Settings\ObjectViewRange#=300
					Case 2
						Menu\Settings\MeshViewRange#=10000
						Menu\Settings\ObjectViewRange#=500
					Case 3
						Menu\Settings\MeshViewRange#=20000
						Menu\Settings\ObjectViewRange#=1000
				End Select
				
				Menu\Option=1
				Menu\OptionOrder=2
			EndIf
		Case 2:
			
			DrawRealText("Input Method", GAME_WINDOW_W/2, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Please select your primary input method for Sonic World DX", GAME_WINDOW_W/2, GAME_WINDOW_H/2-80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("This will change some things to better suit your chosen option.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-60*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			
			DrawSmartButton(1, "Keyboard & Mouse", GAME_WINDOW_W/2, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Controller", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#)
			
			
			SetColor(255,0,0):DrawRealText("The game is optimised for keyboard and mouse play.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+120*GAME_WINDOW_SCALE#, (Interface_Text_3), 1):SetColor(255,255,255)
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Menu\Option=Menu\Option+1
				If Menu\Option>2 Then Menu\Option=1
			EndIf
			
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=2
			EndIf
			
			If Input\Pressed\ActionJump   Then
				PlaySmartSound(Sound_MenuAccept)
				Select Menu\Option
					Case 1:
						Menu\Settings\ControllerSupport#=0
					Case 2:
						Menu\Settings\ControllerSupport#=1
						
				End Select
				Menu\Option=1
				Menu\OptionOrder=3
			EndIf
		Case 3:

			DrawRealText("Graphical settings have been set up.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("For further customization, such as controls setup with gamepad,", GAME_WINDOW_W/2, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("or menu themes, be sure to see the options.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Be sure to check out all unique skills at individual character selection.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+35*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			SetColor(0,255,255):DrawRealText("Enjoy!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#, (Interface_Text_3), 1):SetColor(255,255,255)

			If Input\Pressed\ActionJump   Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\OptionOrder=4
			EndIf

		Case 4:

			SetColor(255,0,0)
			DrawRealText("WARNING:", GAME_WINDOW_W/2, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Some content in this game may not be suitable for those with epilepsy.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("If you are known to have epilepsy, this game is not recommended for you.", GAME_WINDOW_W/2, GAME_WINDOW_H/2-5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			SetColor(255,255,255)

			If Input\Pressed\ActionJump   Then
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

	DrawSmartButton(1, "Play", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#)
	DrawSmartButton(2, "Options", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#)
	DrawSmartButton(3, "Credits", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#)
	DrawSmartButton(4, "Exit", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#)
	
	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>4 Then Menu\Option=1
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=4
	EndIf

	If Input\Pressed\ActionJump   Then
		Menu\Menu2=0
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Select Menu\Option
			Case 1:
				Menu\NewOption=1 : Menu\NewMenu=MENU_PLAY#
;			Case 2:
;				Menu\NewOption=1 : Menu\NewMenu=MENU_PROGRESS# : Menu\StatsMode=1
			Case 2:
				Menu\NewOption=1 : Menu\NewMenu=MENU_OPTIONS# : Menu\NewMenu2=0 : Menu\OptionOrder=0
				Menu\Settings\NewResolution#=Menu\Settings\Resolution#
				For i=1 To 2
				Menu\OptionsForceKeyJump[i]=CONTROLS(i,INPUT_BUTTON_ACTIONJUMP)
				Menu\OptionsForceKeyRoll[i]=CONTROLS(i,INPUT_BUTTON_ACTIONROLL)
				Menu\OptionsForceKeySkill2[i]=CONTROLS(i,INPUT_BUTTON_ACTIONSKILL2)
				Menu\OptionsForceKeyAct[i]=CONTROLS(i,INPUT_BUTTON_ACTIONACT)
				Next
				For i=0 To 17 : CONTROLS_NEWGAMEPAD(i) = CONTROLS_GAMEPAD(i) : Next
			Case 3:
				Menu\NewOption=0 : Menu\NewMenu=MENU_CREDITS# : Menu\CreditsTimer=0
			Case 4:
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

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump   Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=3 : Menu\NewMenu=MENU_MAIN#
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

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump   Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		If Menu\GoBackToCollection=1 Then
			Menu\CollectionRoom=1
			Menu_GoToStage()
			
		ElseIf Menu\MarathonMode=0 Then
			Menu\NewOption=Menu\SelectedStage
			
				Menu\NewMenu=MENU_STAGE2#
				
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
				DrawRealText(EMBLEMS, GAME_WINDOW_W/2, GAME_WINDOW_H/2+20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				SetColor(255,255,255)

				Select Menu\EmblemsGot
					Case 1:
						DrawRealText("You got "+Menu\EmblemsGot+" emblem!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					Default:
						DrawRealText("You got "+Menu\EmblemsGot+" emblems!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				End Select
				DrawRealText("Complete each act of a stage to earn an Emblem.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				DrawRealText("And get an S rank to earn another.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			EndIf

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump   Then
				If Menu\EmblemsGot>0 Then
					Menu\UnlockedWho$=""
					For c=1 To CHAR_NORMALCOUNT
						If EMBLEMS>=TOUNLOCKCHAR[c] And TOUNLOCKCHAR[c]>0 And UNLOCKEDCHAR[c]=0 Then
							If Len(Menu\UnlockedWho$)>0 Then Menu\UnlockedWho$=Menu\UnlockedWho$+","
							UNLOCKEDCHAR[c]=1 : Menu\OptionOrder=2
							Menu\UnlockedWho$=Menu\UnlockedWho$+" "+SingleCharNames$(c)
						EndIf
					Next
					If EMBLEMS>=70 And UNLOCKEDSUPERS=0 Then
						If Len(Menu\UnlockedWho$)>0 Then Menu\UnlockedWho$=Menu\UnlockedWho$+","
						UNLOCKEDSUPERS=1 : Menu\OptionOrder=2
						Menu\UnlockedWho$=Menu\UnlockedWho$+" Super Forms"
					EndIf
				EndIf

				If Menu\OptionOrder=1 Then
					PlaySmartSound(Sound_MenuBack)
					Menu\Transition=1
					StopChannel(Menu\Channel_Emblem)
					If Menu\MarathonMode=0 Then
						Menu\NewOption=Menu\SelectedStage
							If Menu\AllRedRings=1 Then
								Menu\NewMenu=MENU_REDRING#
								Menu\OptionOrder=0
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
				DrawRealText(EMBLEMS, GAME_WINDOW_W/2, GAME_WINDOW_H/2+20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
				SetColor(255,255,255)
			EndIf
			
			DrawRealText("You unlocked"+Menu\UnlockedWho$+"!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText(Menu\Compliment$, GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			
			If Input\Pressed\actionroll Or (KeyHit(KEY_ESCAPE)) Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump   Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				StopChannel(Menu\Channel_Emblem)
				If Menu\MarathonMode=0 Then
					Menu\NewOption=Menu\SelectedStage
						If Menu\AllRedRings=1 Then
							Menu\NewMenu=MENU_REDRING#
							Menu\OptionOrder=0
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
Function Menu_RedRing_Update()
	
	If Menu\OptionOrder=2 Then Menu\RubyFade=Menu\RubyFade+(timervalue#*0.65)
	
	If Menu\RubyFade>0.8*secs# Then Menu\RubyFade=0.8*secs#
	
	
	If Menu\RedRingFade>0 Then Menu\RedRingFade=Menu\RedRingFade-(timervalue#*0.65)
	
	
	
	If Menu\RedRingFade<0 Then Menu\RedRingFade=0
	
	Menu\Music=0
	Menu\Background=3
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu
	
	If Menu\Transition=0 Then
		Select Menu\OptionOrder
			Case 0:
				Menu\LoadedRedRingYet=0
				PlaySmartSound(Sound_RedRings)
				Menu\MeshChange = 1
				Menu\OptionOrder=1
			Case 1:
				If Menu\LoadedRedRingYet=1 Then
					Menu\RedRingFade=1*secs#
					SetColor(255,30,30)
					DrawRealText(REDRINGS, GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					SetColor(255,255,255)
					DrawRealText("You collected all five red star rings!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+70*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					
						;DrawRealText("The encore mission for "+Menu\StageName$+" is now available!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
						;DrawRealText("And get an S rank to get another.", GAME_WINDOW_W/2, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					
					
					If Input\Pressed\actionroll Or (KeyHit(KEY_ESCAPE)) Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump   Then
						Menu\OptionOrder=2
						
						Menu\Channel_Emblem=PlaySmartSound(Sound_RubySwirl)
						Menu\RubyFade=0
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
						
						SaveGame()
					EndIf
				EndIf
			Case 2:
				If Menu\LoadedRedRingYet=1 Then
					SetColor(255,30,30)
					DrawRealText(REDRINGS, GAME_WINDOW_W/2, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					SetColor(255,255,255)
					
					DrawRealText("You unlocked the encore mission for "+Menu\StageName$+"!", GAME_WINDOW_W/2, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					DrawRealText(Menu\Compliment$, GAME_WINDOW_W/2, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					
					If Input\Pressed\actionroll Or (KeyHit(KEY_ESCAPE)) Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump   Then
						PlaySmartSound(Sound_MenuBack)
						Menu\Transition=1
						StopChannel(Menu\Channel_Emblem)
						Menu\NewOption=Menu\SelectedStage
						Menu\NewMenu=MENU_STAGE2#
						
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
	
	If Menu\PauseOptions=0 Then
		
		
		DrawImageEx(INTERFACE(Interface_Pause), GAME_WINDOW_W/2, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#, 0)
		
		If Menu\CollectionRoom=0 And Menu\TutorialMode=0 Then Interface_RedRingCounter(True)
		
		DrawSmartButton(1, "Resume", GAME_WINDOW_W/2, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#)
		Select Menu\ChaoGarden
			Case 0:
				DrawSmartButton(2, "Restart", GAME_WINDOW_W/2, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#)
			Case 1:
				DrawSmartButton(2, "Restart", GAME_WINDOW_W/2, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#,False,False,True)
		End Select
		DrawSmartButton(3, "Options", GAME_WINDOW_W/2, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#)
		DrawSmartButton(4, "Quit", GAME_WINDOW_W/2, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#)
		
		DrawRealText("Pause", GAME_WINDOW_W/2, GAME_WINDOW_H/2-117.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
		
		If Input\Pressed\Down And Menu\Transition=0 Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option=Menu\Option+1
			If Menu\ChaoGarden=1 And Menu\Option=2 Then Menu\Option=Menu\Option+1
			If Menu\Option>4 Then Menu\Option=1
		EndIf
		
		If Input\Pressed\Up And Menu\Transition=0 Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option=Menu\Option-1
			If Menu\ChaoGarden=1 And Menu\Option=2 Then Menu\Option=Menu\Option-1
			If Menu\Option<1 Then Menu\Option=4
		EndIf
		
		DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30)*GAME_WINDOW_SCALE#)
		DrawRealText("Capture mode", (50)*GAME_WINDOW_SCALE#, (30)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		
		If Input\Pressed\ActionDrift Then 
			Game_Unpause()
			PlaySmartSound(Sound_DebugOnOff)
			Select Game\CinemaMode
				Case 0:
					Game\CinemaMode=1
					cam\CinemaX#=cam\Position\x#
					cam\CinemaY#=cam\Position\y#
					cam\CinemaZ#=cam\Position\z#
				Default:
					Game\CamDest=1
					Game\CinemaMode=0
			End Select
		EndIf 
		
		If (Input\Pressed\ActionJump Or Input\Pressed\Start) And Menu\Transition=0 Then
			Select Menu\Option
				Case 2
					For p.tPlayer = Each tPlayer: QuickRestartStage(p) : Next
					PlaySmartSound(Sound_MenuAccept)
				Case 3
					PlaySmartSound(Sound_MenuAccept)
					Menu\PauseOptions=1
					Menu\Option2=1
				Case 4
					If Menu\MissionCard=1 Or Menu\HubStage=9999 Then 
						PlaySmartSound(Sound_MenuAccept)
						Game_Stage_Quit(2)
						Menu\CollectionRoom=1
						Menu\MissionCard=0
						Menu\MissionNo=1
						Menu\HubStage=0
						
					Else
						PlaySmartSound(Sound_MenuAccept)
						Menu\Transition=1
						Game_Stage_Quit(3)
					EndIf
					
					
					
					
					
			End Select
		EndIf
	Else
		If Input\Pressed\ActionRoll Then
			UpdateAllSoundVolumes()
			UpdateAllPlayerVolumes()
			Menu\PauseOptions=0
		EndIf
		
		DrawRealText("Master volume:", GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-90*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawRealText("SFX:", GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-60*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawRealText("Voice actors:", GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawRealText("Music:", GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+0*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawRealText("Ambient:", GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		If Menu\Settings\ControllerSupport#=0 Then ctrl$="Keyboard" Else ctrl$="Controller"
		DrawRealText("Controls:"+ctrl$, GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawRealText("Sensitivity:", GAME_WINDOW_W/2-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+90*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Select Menu\Option2
			Case 1: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-90*GAME_WINDOW_SCALE#)
			Case 2: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-60*GAME_WINDOW_SCALE#)
			Case 3: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-30*GAME_WINDOW_SCALE#)
			Case 4: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+0*GAME_WINDOW_SCALE#)
			Case 5: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#)
			Case 6: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#)
			Case 7: DrawArrow(GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+90*GAME_WINDOW_SCALE#)
		End Select
		
		DrawBetterNumber(Menu\Settings\Volume#*10, GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-90*GAME_WINDOW_SCALE#, 0, 1)
		DrawBetterNumber(Menu\Settings\VolumeSFX#*10, GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-60*GAME_WINDOW_SCALE#, 0, 1)
		DrawBetterNumber(Menu\Settings\VolumeVA#*10, GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-30*GAME_WINDOW_SCALE#, 0, 1)
		DrawBetterNumber(Menu\Settings\VolumeM#*10, GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+0*GAME_WINDOW_SCALE#, 0, 1)
		DrawBetterNumber(Menu\Settings\VolumeAmb#*10, GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, 0, 1)
		DrawBetterNumber(Menu\Settings\ControllerSupport#, GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, 0, 1)
		DrawBetterNumber((Menu\Settings\SensitivityMult#*10), GAME_WINDOW_W/2+125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+90*GAME_WINDOW_SCALE#, 0, 1)
		If Input\Pressed\Right Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\Option2
				Case 1: If Menu\Settings\Volume#<1 Then Menu\Settings\Volume#=Menu\Settings\Volume#+0.1
				Case 2: If Menu\Settings\VolumeSFX#<1 Then Menu\Settings\VolumeSFX#=Menu\Settings\VolumeSFX#+0.1
				Case 3: If Menu\Settings\VolumeVA#<1 Then Menu\Settings\VolumeVA#=Menu\Settings\VolumeVA#+0.1
				Case 4: If Menu\Settings\VolumeM#<1 Then Menu\Settings\VolumeM#=Menu\Settings\VolumeM#+0.1
				Case 5: If Menu\Settings\VolumeAmb#<1 Then Menu\Settings\VolumeAmb#=Menu\Settings\VolumeAmb#+0.1
				Case 6: Menu\Settings\ControllerSupport#=Abs(Menu\Settings\ControllerSupport-1)	
				Case 7: Menu\Settings\SensitivityMult#=Menu\Settings\SensitivityMult#+0.1
			End Select
			
		EndIf
		
		If Input\Pressed\Left Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\Option2
				Case 1: If Menu\Settings\Volume#>0 Then Menu\Settings\Volume#=Menu\Settings\Volume#-0.1
				Case 2: If Menu\Settings\VolumeSFX#>0 Then Menu\Settings\VolumeSFX#=Menu\Settings\VolumeSFX#-0.1
				Case 3: If Menu\Settings\VolumeVA#>0 Then Menu\Settings\VolumeVA#=Menu\Settings\VolumeVA#-0.1
				Case 4: If Menu\Settings\VolumeM#>0 Then Menu\Settings\VolumeM#=Menu\Settings\VolumeM#-0.1
				Case 5: If Menu\Settings\VolumeAmb#>0 Then Menu\Settings\VolumeAmb#=Menu\Settings\VolumeAmb#-0.1
				Case 6: Menu\Settings\ControllerSupport#=Abs(Menu\Settings\ControllerSupport-1)
				Case 7: Menu\Settings\SensitivityMult#=Menu\Settings\SensitivityMult#-0.1
			End Select
			
		EndIf
		
		If Input\Pressed\Down Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option2=Menu\Option2+1
			If Menu\Option2>7 Then Menu\Option2=1
		EndIf
		If Input\Pressed\Up Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option2=Menu\Option2-1
			If Menu\Option2<1 Then Menu\Option2=7
		EndIf
		
		If Menu\Settings\Volume#>1 Then Menu\Settings\Volume#=1
		If Menu\Settings\VolumeSFX#>1 Then Menu\Settings\VolumeSFX#=1
		If Menu\Settings\VolumeVA#>1 Then Menu\Settings\VolumeVA#=1
		If Menu\Settings\VolumeM#>1 Then Menu\Settings\VolumeM#=1
		If Menu\Settings\VolumeAmb#>1 Then Menu\Settings\VolumeAmb#=1
		
		If Menu\Settings\Volume#<0 Then Menu\Settings\Volume#=0
		If Menu\Settings\VolumeSFX#<0 Then Menu\Settings\VolumeSFX#=0
		If Menu\Settings\VolumeVA#<0 Then Menu\Settings\VolumeVA#=0
		If Menu\Settings\VolumeM#<0 Then Menu\Settings\VolumeM#=0
		If Menu\Settings\VolumeAmb#<0 Then Menu\Settings\VolumeAmb#=0
		
		
		
		
		
		
		
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

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionJump  Then
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