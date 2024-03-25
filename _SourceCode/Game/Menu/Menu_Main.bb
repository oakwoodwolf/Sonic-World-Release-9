
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
	SetColor(255, 255, 255)

	; Play music
	Menu_Music()

	; Draw background
	Select Menu\Background
		Case 2:
			If Menu\RandomBackgroundChooser=0 Then
				Select Menu\Settings\Theme#
					Case 14,26: Menu\RandomBackgroundChooser=Rand(1,2)
					Default: Menu\RandomBackgroundChooser=1
				End Select
			EndIf
		Default:
			Select Menu\Settings\Theme#
				Case 19,20,23: If Menu\RandomBackgroundChooser=1 Then Menu\RandomBackgroundChooser=Rand(1,2)+1
				Case 22: If UNLOCKEDEMERALDS[7]=1 Then Menu\RandomBackgroundChooser=2+1 Else Menu\RandomBackgroundChooser=1+1
				Default: Menu\RandomBackgroundChooser=0
			End Select
	End Select
	Select Menu\Background
		Case 0:
		Default: DrawImageEx(INTERFACE(Interface_BlackBig), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
	End Select
	Select Menu\Background
		Case 1:
			Select Menu\Settings\Theme#
				Case 5,30:
					Select Menu\Menu
						Case MENU_PLAY#,MENU_CHARACTERS#,MENU_CHARACTERS2#,MENU_TEAMS#,MENU_STAGE#,MENU_STAGE2#,MENU_STAGESPECIAL#,MENU_MARATHON#,MENU_PLAYMARATHON#:
							DrawImageEx(INTERFACE(Interface_Background2), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
						Case MENU_OPTIONS#:
							DrawImageEx(INTERFACE(Interface_Background3), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
						Case MENU_BIOS#:
							DrawImageEx(INTERFACE(Interface_Background4), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
						Default:
							DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
					End Select
				Case 19,20,22,23:
					DrawImageEx(INTERFACE(Interface_Background1+Menu\RandomBackgroundChooser-2), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
				Default:
					DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
			End Select
		Case 2:
			Select Menu\Settings\Theme#
				Case 14:
					DrawImageEx(INTERFACE(Interface_Sky+Menu\RandomBackgroundChooser-1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
				Default:
					DrawImageEx(INTERFACE(Interface_Sky), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
			End Select
		Case 3:
			DrawImageEx(INTERFACE(Interface_Black), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
		Case 4:
			Menu_Transporter_Background(d)
	End Select
	Select Menu\Settings\Theme#
		Case 5,30:
			If Menu\Background<>0 And Menu\Background<>3 And Menu\Background<>4 Then Menu_RoundTransition(d)
		Case 4:
			If Not(Menu\Menu=MENU_START#) Then
				If Menu\Background<>0 And Menu\Background<>3 And Menu\Background<>4 Then Menu_CharScroll(d)
			EndIf
		Case 16:
			If Menu\Menu=MENU_START# Then DrawImageEx(INTERFACE(Interface_Round), GAME_WINDOW_W/2+000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-30*GAME_WINDOW_SCALE#)
	End Select
	Select Menu\Settings\Theme#
		Case 4,30:
			If Menu\Menu=MENU_START# Then
				Menu_FloatingBubbles(d)
			Else
				Menu_DeleteFloatingBubbles()
			EndIf
		Case 27:
			If Menu\Background<>2 Then
				Select Menu\Menu
					Case MENU_BLACKMARKET#,MENU_TRANSPORTER#,MENU_PRINCIPAL#:
						Menu_DeleteFloatingBubbles()
					Default:
						Menu_FloatingBubbles(d)
				End Select
			Else
				Menu_DeleteFloatingBubbles()
			EndIf
	End Select

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
		Case MENU_CHARACTERS#,MENU_CHARACTERS2#: Menu_Characters_Update()
		Case MENU_BIOS#: Menu_Bios_Update()
		Case MENU_TEAMS#: Menu_Teams_Update()
		Case MENU_STAGE#,MENU_STAGE2#: Menu_Stage_Update()
		Case MENU_STAGESPECIAL#: Menu_StageSpecial_Update()
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
	If (Not(Menu\Menu=MENU_BIOS# Or Menu\Menu=MENU_TRANSPORTER#)) And Menu\MeshChaoEmoActivated>0 Then Menu\MeshChaoEmoActivated=0

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

	; Toggle debug nodes
	If KeyHit(KEY_F3) Then PlaySmartSound(Sound_MenuMove) : Menu\Settings\DebugNodes#=Abs(Menu\Settings\DebugNodes#-1)

	;change mission
	If KeyHit(KEY_F9) Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MissionNo=Menu\MissionNo+1
		If Menu\MissionNo>5 Then Menu\MissionNo=1
	EndIf

	;change character
	If (Not(Menu\Menu=MENU_CHARACTERS# Or Menu\Menu=MENU_CHARACTERS2# Or Menu\Menu=MENU_BIOS# Or Menu\Menu=MENU_TEAMS#)) Then
		If KeyHit(KEY_F12) Then
			Menu\CharacterRow=Menu\CharacterRow+1
			If Menu\CharacterRow>Ceil(CHAR_NONMODPLAYABLECOUNT/10.0) Then Menu\CharacterRow=1
			PlaySmartSound(Sound_MenuMove)
		EndIf
		If Menu\Members=1 Then
			For i=KEY_1 To KEY_0
				j = (10*(Menu\CharacterRow-1))+i-1
				If j<=CHAR_NONMODPLAYABLECOUNT Then
				If KeyHit(i) And UNLOCKEDCHAR[j]=1 Then
					Menu\NewCharacter=j : FlushKeys()
				EndIf
				EndIf
			Next
			If Menu\NewCharacter>0 And (Menu\NewCharacter<>Menu\Character[1]) And (Not(Menu\NewCharacter>CHAR_NONMODPLAYABLECOUNT)) And UNLOCKEDCHAR[Menu\NewCharacter]=1 Then Menu\Character[1]=Menu\NewCharacter : PlaySmartSound(Sound_CharacterChange) : Menu\NewCharacter=0
		ElseIf Menu\Members=3 Then
			For i=KEY_1 To KEY_0
				j = (i-1)
				If j<TEAM_TEAMCOUNT Then
				If KeyHit(i) And UNLOCKEDTEAM[j]=1 Then
					Menu\Team=j : PlaySmartSound(Sound_CharacterChange)
					Select j
						Case TEAM_SONIC:
							Menu\Character[1]=CHAR_SON : Menu\Character[2]=CHAR_TAI : Menu\Character[3]=CHAR_KNU
						Case TEAM_DARK:
							Menu\Character[1]=CHAR_SHA : Menu\Character[2]=CHAR_ROU : Menu\Character[3]=CHAR_OME
						Case TEAM_ROSE:
							Menu\Character[1]=CHAR_AMY : Menu\Character[2]=CHAR_CRE : Menu\Character[3]=CHAR_BIG
						Case TEAM_CHAOTIX:
							Menu\Character[1]=CHAR_ESP : Menu\Character[2]=CHAR_CHA : Menu\Character[3]=CHAR_VEC
						Case TEAM_SOL:
							Menu\Character[1]=CHAR_BLA : Menu\Character[2]=CHAR_SIL : Menu\Character[3]=CHAR_MAR
						Case TEAM_OLDIES:
							Menu\Character[1]=CHAR_MIG : Menu\Character[2]=CHAR_RAY : Menu\Character[3]=CHAR_HBO
						Case TEAM_HOOLIGAN:
							Menu\Character[1]=CHAR_NAC : Menu\Character[2]=CHAR_BEA : Menu\Character[3]=CHAR_BAR
						Case TEAM_BABYLON:
							Menu\Character[1]=CHAR_JET : Menu\Character[2]=CHAR_WAV : Menu\Character[3]=CHAR_STO
						Case TEAM_RELIC:
							Menu\Character[1]=CHAR_SHD : Menu\Character[2]=CHAR_TIK : Menu\Character[3]=CHAR_CHO
						Case TEAM_ROBOTNIK:
							Menu\Character[1]=CHAR_MET : Menu\Character[2]=CHAR_TDL : Menu\Character[3]=CHAR_MKN
					End Select
					FlushKeys()
				EndIf
				EndIf
			Next
		EndIf
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
	If KeyHit(KEY_F1) Then
		PlaySmartSound(Sound_MenuAccept)
		For c=1 To CHAR_NORMALCOUNT
			If c<=CHAR_NONMODPLAYABLECOUNT Or c>CHAR_PLAYABLECOUNT Then
				UNLOCKEDCHAR[c]=1
			Else
				If MODCHARS_FOUND(c-CHAR_MOD1+1) Then UNLOCKEDCHAR[c]=1
			EndIf
		Next
		For c=1 To TEAM_TEAMCOUNT : UNLOCKEDTEAM[c]=1 : Next
		If UNLOCKEDSPECIALSTAGES[0] And Menu\Developer=1 Then
			For c=1 To 7 : UNLOCKEDEMERALDS[c]=1 : Next
		EndIf
		UNLOCKEDSPECIALSTAGES[0]=1
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

	If Menu\Music=1 Then
		If (Not(ChannelPlaying(Menu\Channel_Menu))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_Menu=PlaySmartSound(Sound_Menu)
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_Menu) Then StopChannel(Menu\Channel_Menu)
	EndIf

	If Menu\Music=2 Then
		If (Not(ChannelPlaying(Menu\Channel_MenuIntro))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuIntro=PlaySmartSound(Sound_MenuIntro)
		Else
			If ChannelPlaying(Menu\Channel_MenuIntro) And Menu\DontReplayMusic=1 Then Menu\DontReplayMusic=3
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_MenuIntro) Then StopChannel(Menu\Channel_MenuIntro)
	EndIf

	If Menu\Music=3 Then
		If (Not(ChannelPlaying(Menu\Channel_MenuOptions))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuOptions=PlaySmartSound(Sound_MenuOptions)
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_MenuOptions) Then StopChannel(Menu\Channel_MenuOptions)
	EndIf

	If Menu\Music=4 Then
		If (Not(ChannelPlaying(Menu\Channel_MenuCredits))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuCredits=PlaySmartSound(Sound_MenuCredits)
		Else
			If ChannelPlaying(Menu\Channel_MenuCredits) And Menu\DontReplayMusic=1 Then Menu\DontReplayMusic=3
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_MenuCredits) Then StopChannel(Menu\Channel_MenuCredits)
	EndIf

	If Menu\Music=5 Then
		If (Not(ChannelPlaying(Menu\Channel_GameOver))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_GameOver=PlaySmartSound(Sound_GameOver)
		Else
			If ChannelPlaying(Menu\Channel_GameOver) And Menu\DontReplayMusic=1 Then Menu\DontReplayMusic=3
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_GameOver) Then StopChannel(Menu\Channel_GameOver)
	EndIf

	If Menu\Music=6 Then
		If (Not(ChannelPlaying(Menu\Channel_MenuCharacter))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuCharacter=PlaySmartSound(Sound_MenuCharacter)
		Else
			If ChannelPlaying(Menu\Channel_MenuCharacter) And Menu\DontReplayMusic=1 Then Menu\DontReplayMusic=3
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_MenuCharacter) Then StopChannel(Menu\Channel_MenuCharacter)
	EndIf

	If Menu\Music=7 Then
		If (Not(ChannelPlaying(Menu\Channel_MenuChao))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuChao=PlaySmartSound(Sound_MenuChao)
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_MenuChao) Then StopChannel(Menu\Channel_MenuChao)
	EndIf

	If Menu\Music=8 Then
		If (Not(ChannelPlaying(Menu\Channel_MenuChao2))) And Menu\DontReplayMusic<3 Then
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuChao2=PlaySmartSound(Sound_MenuChao2)
		EndIf
	Else
		If ChannelPlaying(Menu\Channel_MenuChao2) Then StopChannel(Menu\Channel_MenuChao2)
	EndIf

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
	special#=0
	If Menu\MarathonGotSpecial And UNLOCKEDSPECIALSTAGES[0] Then
		For i=1 To 7
			If (Not UNLOCKEDEMERALDS[i]) And special#=0 Then special#=i
		Next
	EndIf

	If special# Then
		Menu\Members=1
		Menu\SelectedStage=-special#
		Menu_Stage_LoadMissions(Menu\SelectedStage, True)
		Menu_GoToStage_SetMission(Menu\MissionNo)
	Else
		Menu\Members=Menu\MembersMarathon
		If Menu\MarathonStage<=StageAmount-1 Then
			If Menu\MarathonRandom Then Menu_Stage_RandomizeTeam(True)

			If (Not(Menu\MarathonStage>StageAmount-1)) Then
				While (Not (FileType(StagePath$(MarathonStage(Menu\MarathonStage))+"/Media/Missions.xml")=1))
					Menu\MarathonStage=Menu\MarathonStage+1
					If Menu\MarathonStage>StageAmount-1 Then Menu_GoToMarathonStage_Cancel() : Return
				Wend
			Else
				Menu_GoToMarathonStage_Cancel() : Return
			EndIf
			Menu_Stage_LoadMissions(MarathonStage(Menu\MarathonStage),True)

			i=1
			Repeat
				If i>5 Then
					Menu\MarathonStage=Menu\MarathonStage+1
					If Menu\MarathonStage>StageAmount-1 Then Menu_GoToMarathonStage_Cancel() : Return
					Menu_Stage_LoadMissions(MarathonStage(Menu\MarathonStage),True)
					i=1
				EndIf
				Menu_GoToStage_SetMission(i)
				i=i+1
			Until (Not(Menu\Mission=MISSION_FREEROAM#))

			Menu\SelectedStage=MarathonStage(Menu\MarathonStage)
			SaveMarathonList()
		Else
			Menu_GoToMarathonStage_Cancel() : Return
		EndIf
	EndIf
End Function

Function Menu_GoToStage()

		Menu\NewOption=0 : Menu\NewMenu=MENU_LOADING# : Menu\Menu2=0 : Menu\NewMenu2=0
		Menu\MustLoadStage=0
		Menu\TitleCardTimer=0
		Select Menu\ChaoGarden
			Case 0:
				If Menu\MarathonMode=0 Then
					Select Menu\Menu
						Case MENU_STAGE2#: Menu\SelectedStage=Menu\Option+Menu\OptionOrder2*4
						Default: Menu\SelectedStage=Menu\Option
					End Select
					Menu_Stage_LoadMissions(Menu\SelectedStage,True)
					Menu_GoToStage_SetMission(Menu\MissionNo)					
				Else
					Menu_GoToMarathonStage()
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