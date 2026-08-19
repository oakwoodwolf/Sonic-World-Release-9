
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_Update(d.tDeltaTime)

	StartDraw()
	SetBlend(FI_ALPHABLEND)
	SetAlpha(1.0)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	If Game\Interface\AchievementTimer>0 Or Game\Interface\DebugConsole=1 Then
		SetColor(55, 55, 55)
	Else
		SetColor(255, 255, 255)
	EndIf

	; Play music
	Menu_Music()

	
	Select Menu\Background
		Case 0:
		Default: DrawImageEx(INTERFACE(Interface_BlackBig), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
	End Select
	
	Select Menu\Background
		Case 1:
			Select Menu\Menu
				Case MENU_OPTIONS#
					If Menu\ThemeBackgroundOptions=1 Then
						DrawImageEx(INTERFACE(Interface_Background3), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
					Else
						DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
					EndIf
				Case MENU_PLAY#,MENU_CHARACTERS#,MENU_TEAMS#,MENU_STAGE#,MENU_STAGE2#,MENU_MARATHON#,MENU_PLAYMARATHON#:
					If Menu\ThemeBackgroundPlay=1 Then
						DrawImageEx(INTERFACE(Interface_Background2), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
					Else
						DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
					EndIf
				Default
					DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
			End Select
			
		Case 2
			DrawImageEx(INTERFACE(Interface_Sky), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
		Case 3:
			DrawImageEx(INTERFACE(Interface_Black), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
		Case 4:
			Menu_Transporter_Background(d)
	End Select
	
	If Menu\ThemeRoundTransition=1 Then 
		If (Menu\Background<>0 And Menu\Background<>3 And Menu\Background<>4) Then Menu_RoundTransition(d)
	EndIf 
	
	If Menu\ThemeBubbles=1 Then
		If Menu\Menu=MENU_START# Then
			Menu_FloatingBubbles(d)
		Else
			Menu_DeleteFloatingBubbles()
		EndIf
	EndIf
	
	If Menu\ThemeScrolls=1 Then
		If Not(Menu\Menu=MENU_START#) Then
			If Menu\Background<>0 And Menu\Background<>3 And Menu\Background<>4 Then Menu_CharScroll(d)
		EndIf
	EndIf

	; Update transtion
	Menu_Transition(d)

	; Update menu pages
	Select Menu\Menu
		Case MENU_LOADING#: Menu_Loading_Update()
		Case MENU_CLOSE#: Menu_Close_Update()
		Case MENU_START#: Menu_Start_Update(d)
		Case MENU_MAIN#: Menu_Main_Update()
		Case MENU_OPTIONS#: Menu_Options_Update()
		Case MENU_PLAY#: Menu_Play_Update()
		Case MENU_CHARACTERS#: Menu_Characters_Update()
		Case MENU_TEAMS#: Menu_Teams_Update()
		Case MENU_STAGE#,MENU_STAGE2#: Menu_Stage_Update()
		Case MENU_CREDITS#: Menu_Credits_Update()
		Case MENU_GAMEOVER#: Menu_GameOver_Update()
		Case MENU_WELCOME#: Menu_Welcome_Update()
		Case MENU_EMBLEM#: Menu_Emblem_Update()
		Case MENU_BLACKMARKET#: Menu_BlackMarket_Update()
		Case MENU_TRANSPORTER#: Menu_Transporter_Update()
		Case MENU_PRINCIPAL#: Menu_Principal_Update()
		Case MENU_MARATHON#: Menu_Play_Update(2)
		Case MENU_PLAYMARATHON#: Menu_Play_Update(1)
		Case MENU_MARATHONEND#: Menu_MarathonEnd_Update()
		Case MENU_REDRING#: Menu_RedRing_Update()

	End Select

	; Draw cards, title, controls
	Menu_DrawCardsTitleControls()

	; Deal with mesh on screen
	Menu_CharacterMeshOnScreen(d)

	; Run cheats
	If Menu\Settings\Debug#=1 And (Not(Game\ControlLock>0)) And (Not(Menu\Menu=MENU_OPTIONS# And (Menu\Menu2=MENU_CONTROLS# Or Menu\Menu2=MENU_CONTROLS2#))) Then Menu_Cheats()

	; Get away from chao world
	If (Not(Menu\Menu=MENU_BLACKMARKET# Or Menu\Menu=MENU_TRANSPORTER# Or Menu\Menu=MENU_PRINCIPAL#)) And Menu\WentToChaoMenu=1 Then Menu\WentToChaoMenu=0

	; Get away from chao emo
	If (Not(Menu\Menu=MENU_TRANSPORTER#)) And Menu\MeshChaoEmoActivated>0 Then Menu\MeshChaoEmoActivated=0
	
	SetColor(255,255,255)
	If Game\Interface\DebugConsole=1 Then Menu_DebugConsole()
	
	EndDraw()

End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_TakeCards_Out()
	CARD_PLACE#=Menu_ResetCards_OutValue#()
	CARD_PLACE_TARGET#=Menu_ResetCards_OutValue#()
End Function

Function Menu_ResetCards_Out()
	CARD_PLACE_TARGET#=Menu_ResetCards_OutValue#()
End Function

Function Menu_ResetCards_In()
	CARD_PLACE_TARGET#=Menu_ResetCards_InValue#()
End Function

Function Menu_ResetCards_Mid()
	CARD_PLACE_TARGET#=Menu_ResetCards_MidValue#()
End Function

Function Menu_ResetCards_OutValue#()
	Return 80
End Function

Function Menu_ResetCards_InValue#()
	Select Menu\Settings\Resolution#
		Case 1: Return -5
		Case 2: Return 2.5
		Case 3: Return 8.75
		Case 4: Return 10
		Case 5,6,9,11,12: Return 0
		Case 7,8: Return 11.25
		Case 10: Return 12.5
	End Select
End Function

Function Menu_ResetCards_MidValue#()
	If Menu\Settings\ScreenModeChanged#=0 Then
		If Menu\Settings\ScreenMode#=0 Then
			Return -150
		Else
			Return -180
		EndIf
	Else
		If Menu\Settings\ScreenMode#=0 Then
			Return -180
		Else
			Return -150
		EndIf
	EndIf
End Function

;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_TakeButton1Place_LeftOut()
	BUTTON_PLACE1#=-450
	BUTTON_PLACE1_TARGET#=-450
End Function

Function Menu_TakeButton1Place_RightOut()
	BUTTON_PLACE1#=+450
	BUTTON_PLACE1_TARGET#=+450
End Function

Function Menu_ResetButtonPlace1_LeftOut()
	BUTTON_PLACE1_TARGET#=-450
End Function

Function Menu_ResetButtonPlace1_RightOut()
	BUTTON_PLACE1_TARGET#=+450
End Function

Function Menu_ResetButtonPlace1_LeftIn()
	BUTTON_PLACE1_TARGET#=-200
End Function

Function Menu_ResetButtonPlace1_RightIn()
	BUTTON_PLACE1_TARGET#=200
End Function

Function Menu_ResetButtonPlace1_MidIn()
	BUTTON_PLACE1_TARGET#=0
End Function

;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_TakeButton2Place_LeftOut()
	BUTTON_PLACE2#=-450
	BUTTON_PLACE2_TARGET#=-450
End Function

Function Menu_TakeButton2Place_RightOut()
	BUTTON_PLACE2#=+450
	BUTTON_PLACE2_TARGET#=+450
End Function

Function Menu_ResetButtonPlace2_LeftOut()
	BUTTON_PLACE2_TARGET#=-450
End Function

Function Menu_ResetButtonPlace2_RightOut()
	BUTTON_PLACE2_TARGET#=+450
End Function

Function Menu_ResetButtonPlace2_LeftIn()
	BUTTON_PLACE2_TARGET#=-100
End Function

Function Menu_ResetButtonPlace2_RightIn()
	BUTTON_PLACE2_TARGET#=+100
End Function

Function Menu_ResetButtonPlace2_MidIn()
	BUTTON_PLACE2_TARGET#=0
End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_Cheats()

	If KeyDown(KEY_F4) Then Interface_Render_Cheats(1)

	; Reset to start screen
	If KeyHit(KEY_F6)
		PlaySmartSound(Sound_MenuBack)
		Menu\Menu2=0
		Menu\NewMenu2=0
		Menu\Transition=1
		Menu\NewOption=0 : Menu\NewMenu=MENU_START#
		Menu\MeshChange=-1
		Menu\TitleState=0
	EndIf

	; Go to game over
	If KeyHit(KEY_F7)
		PlaySmartSound(Sound_MenuBack)
		Menu\GameOverType=0
		Menu\Menu2=0
		Menu\NewMenu2=0
		Menu\Transition=1
		Menu\NewOption=0 : Menu\NewMenu=MENU_GAMEOVER#
		Menu\MeshChange=-1
	EndIf

	; Go to time over
	If KeyHit(KEY_F8)
		PlaySmartSound(Sound_MenuBack)
		Menu\GameOverType=1
		Menu\Menu2=0
		Menu\NewMenu2=0
		Menu\Transition=1
		Menu\NewOption=0 : Menu\NewMenu=MENU_GAMEOVER#
		Menu\MeshChange=-1
	EndIf

	; Skip to stage select
	If KeyHit(KEY_PLUS)
		PlaySmartSound(Sound_MenuAccept)
		Menu\Menu2=0
		Menu\NewMenu2=0
		Menu\Transition=1
		Menu\ChaoGarden=0
		Menu\MarathonMode=0
		Menu\NewOption=Menu\SelectedStage : Menu\NewMenu=MENU_STAGE2#
		If Menu\Team=0 Or Menu\Members<3 Then
			Menu\Members=3
			Menu\Character[1]=CHAR_SON : Menu\Character[2]=CHAR_TAI : Menu\Character[3]=CHAR_KNU
			Menu\Team=1
		EndIf
		Menu\MeshChange=-1
	EndIf
	If KeyHit(KEY_HYPHEN)
		PlaySmartSound(Sound_MenuAccept)
		Menu\Menu2=0
		Menu\NewMenu2=0
		Menu\Transition=1
		Menu\ChaoGarden=0
		Menu\MarathonMode=0
		Menu\NewOption=Menu\SelectedStage : Menu\NewMenu=MENU_STAGE2#
		Menu\Members=1
		Menu\Team=0
		Menu\MeshChange=-1
	EndIf
	
	If KeyHit(KEY_F2) Then PlaySmartSound(Sound_MenuAccept) Menu\Transition = 1 : Menu_GoToStage(True)
	
	; Toggle debug nodes
	If KeyHit(KEY_F3) Then PlaySmartSound(Sound_MenuMove) : Menu\Settings\DebugNodes#=Abs(Menu\Settings\DebugNodes#-1)

	;change mission
	If KeyHit(KEY_F9) Then
		Menu\StabilityTest=(Abs(Menu\StabilityTest-1))
		PlaySmartSound(Sound_Counter1+Menu\StabilityTest)
	EndIf

	;random team
	If KeyHit(KEY_F11) Then Menu_Stage_RandomizeTeam()

	; Reset to welcome screen
	If KeyHit(KEY_F5)
		PlaySmartSound(Sound_MenuBack)
		Menu\Menu2=0
		Menu\NewMenu2=0
		Menu\Transition=1
		Menu\FirstTime=0
		Menu\NewOption=1 : Menu\OptionOrder=1 : Menu\NewMenu=MENU_WELCOME#
		Menu\MeshChange=-1
	EndIf

	; Toggle time control
	If KeyHit(KEY_F10) Then Game\TimeControl=Abs(Game\TimeControl-1) : PlaySmartSound(Sound_MenuMove)

	; Unlock all
	If KeyHit(KEY_F1) And Menu\Settings\Debug#=1 Then
		
		
			Game\Interface\DebugConsole=Abs(Game\Interface\DebugConsole-1) Menu\InputRestrictTimer=0.05*secs#
			Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder+1
			
			
	EndIf

	; Next chao emo
	If KeyHit(KEY_F2) And Menu\MeshChaoEmoActivated>0 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MeshChaoEmoActivated=2
		Menu\MeshChaoEmo\Emotion=Menu\MeshChaoEmo\Emotion+1
		If Menu\MeshChaoEmo\Emotion>CHAOEMO_TOTAL Then Menu\MeshChaoEmo\Emotion=1
	EndIf

	; Enable & anyone
	If KeyHit(KEY_DELETE) Then Menu\AndAnyone=Abs(Menu\AndAnyone-1) : PlaySmartSound(Sound_CharacterChange)

End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_Music()
	
	
	Select Menu\Menu
		Case 100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,MENU_OPTIONS#
			StopChannel(Menu\Channel_Menu)
			StopChannel(Menu\Channel_MenuIntro)
			StopChannel(Menu\Channel_MenuCredits)
			StopChannel(Menu\Channel_MenuChao)
			StopChannel(Menu\Channel_MenuChao2)
			StopChannel(Menu\Channel_MenuCharacter)
			StopChannel(Menu\Channel_GameOver)
			If ChannelPlaying(Menu\Channel_MenuOptions)=False Then Menu\Channel_MenuOptions=PlaySound(MenuMusic_Options)
		Case MENU_START#
			
		Case MENU_GAMEOVER#,MENU_EMBLEM#,MENU_REDRING,MENU_WELCOME#,-3,-2
			StopChannel(Menu\Channel_MenuOptions)
			StopChannel(Menu\Channel_MenuIntro)
			StopChannel(Menu\Channel_Menu)
			StopChannel(Menu\Channel_MenuChao)
			StopChannel(Menu\Channel_MenuChao2)
			StopChannel(Menu\Channel_MenuCharacter)
			StopChannel(Menu\Channel_GameOver)
			StopChannel(Menu\Channel_MenuCredits)
		Case MENU_CREDITS#
			StopChannel(Menu\Channel_MenuOptions)
			StopChannel(Menu\Channel_MenuIntro)
			StopChannel(Menu\Channel_Menu)
			StopChannel(Menu\Channel_MenuChao)
			StopChannel(Menu\Channel_MenuChao2)
			StopChannel(Menu\Channel_MenuCharacter)
			StopChannel(Menu\Channel_GameOver)
			If ChannelPlaying(Menu\Channel_MenuCredits)=False Then Menu\Channel_MenuCredits=PlaySound(MenuMusic_Credits)
			
		Case MENU_BLACKMARKET#,MENU_PRINCIPAL#
			If ChannelPlaying(Menu\Channel_MenuChao)=False Then Menu\Channel_MenuChao=PlaySmartSound(Sound_MenuChao)
			StopChannel(Menu\Channel_Menu)
			StopChannel(Menu\Channel_MenuIntro)
			StopChannel(Menu\Channel_MenuCredits)
			StopChannel(Menu\Channel_MenuCharacter)
			StopChannel(Menu\Channel_GameOver)
			StopChannel(Menu\Channel_MenuOptions)
		Case MENU_TRANSPORTER#
			
			If Menu\HeldChaoNumber>0 Then 
				If ChannelPlaying(Menu\Channel_MenuChao2)=False Then Menu\Channel_MenuChao2=PlaySmartSound(Sound_MenuChao2)
			Else
				If ChannelPlaying(Menu\Channel_MenuChao)=False Then Menu\Channel_MenuChao=PlaySmartSound(Sound_MenuChao)
			EndIf
			StopChannel(Menu\Channel_Menu)
			StopChannel(Menu\Channel_MenuIntro)
			StopChannel(Menu\Channel_MenuCredits)
			StopChannel(Menu\Channel_MenuCharacter)
			StopChannel(Menu\Channel_GameOver)
			StopChannel(Menu\Channel_MenuOptions)
			
		Default
			StopChannel(Menu\Channel_MenuStats)
			StopChannel(Menu\Channel_MenuOptions)
			StopChannel(Menu\Channel_MenuIntro)
			StopChannel(Menu\Channel_MenuCredits)
			StopChannel(Menu\Channel_MenuChao)
			StopChannel(Menu\Channel_MenuChao2)
			StopChannel(Menu\Channel_MenuCharacter)
			StopChannel(Menu\Channel_GameOver)
			If ChannelPlaying(Menu\Channel_Menu)=False Then Menu\Channel_Menu=PlaySound(MenuMusic_Main)
	End Select
	
	
	
	
	
	
	
	

End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_GoToStage_SetMission(missionno)
	Menu\Mission = StageMission[missionno]
	Menu\MissionTime = StageMissionTime[missionno]
	Menu\MissionMach = StageMissionMach[missionno]
	Menu\MissionPerfect = StageMissionPerfect[missionno]
End Function

Function Menu_GoToMarathonStage_Cancel()
	Menu\Stage=0
	Menu\Transition=1
	Menu\NewMenu=MENU_MARATHONEND#
	Menu\NewOption=0
	Menu\OptionOrder=0
	DeleteMarathonList()
End Function

Function Menu_GoToMarathonStage()
	
	Menu\Members=Menu\MembersMarathon
	If Menu\MarathonStage<=StageAmount Then
		If Menu\MarathonRandom Then Menu_Stage_RandomizeTeam(True)
		
		
		Menu_Stage_LoadMissions(Menu\MarathonStage,True)
		Menu_GoToStage_SetMission(1)
		
		
		Menu\SelectedStage=Menu\MarathonStage
		SaveMarathonList()
	Else
		Menu_GoToMarathonStage_Cancel() : Return
	EndIf
	
End Function

Function Menu_GoToStage(forcetest=False)

		Menu\NewOption=0 : Menu\NewMenu=MENU_LOADING# : Menu\Menu2=0 : Menu\NewMenu2=0
		Menu\MustLoadStage=0
		Menu\TitleCardTimer=0
		Select Menu\ChaoGarden
			Case 0:
				If Menu\MarathonMode=1 Then
					Menu_GoToMarathonStage()
				ElseIf Menu\MarathonMode=0 And Menu\TutorialMode=0 And Menu\CollectionRoom=0 Then
					Select Menu\Menu
						Case MENU_STAGE2#: Menu\SelectedStage=Menu\Option+Menu\OptionOrder2*4
						Default: Menu\SelectedStage=Menu\Option
					End Select
					If forcetest Then
						
						For i = 1 To StageAmount
							If StageName$(i)="Test Stage" Then Menu\SelectedStage=i
						Next
						
					EndIf
					Menu_Stage_LoadMissions(Menu\SelectedStage,True)
					Menu_GoToStage_SetMission(Menu\MissionNo)	
				ElseIf Menu\TutorialMode=1 Or Menu\CollectionRoom=1 Then
					Menu\Mission=0
					Menu\MissionTime=0
					Menu\MissionMach=0
					Menu\MissionPerfect=0
					
				EndIf
			Case 1:
				If Menu\SelectedStage<990 Then Menu\SelectedStage=999
				Menu\Mission=MISSION_FREEROAM#
				Menu\MissionTime = 0
				Menu\MissionMach = 0
				Menu\MissionPerfect = 0
		End Select
		Select Menu\Members
			Case 1:
				Menu\Character[2]=0
				Menu\Character[3]=0
			Case 2:
				Menu\Character[3]=0
		End Select
		Menu\ExitedAStage=0

End Function
;~IDEal Editor Parameters:
;~C#Blitz3D