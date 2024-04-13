Function DrawSmartKey(key, x#, y#, nocolor=false, forcekey=0, primary=0, gettruekey=false)
	If primary=0 then primary=Menu\Settings\PrimaryController#

	If nocolor Then
		DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 60)
	Else
		If key=INPUT_BUTTON_CHANGE Or key=INPUT_BUTTON_START Or key=INPUT_BUTTON_BACK Or key=INPUT_BUTTON_CAM_CENTER Or key=INPUT_BUTTON_ACTIONSKILLX Or key=INPUT_BUTTON_CAM_LEFT Or key=INPUT_BUTTON_CAM_RIGHT Or key=INPUT_BUTTON_UP Or key=INPUT_BUTTON_DOWN Or key=INPUT_BUTTON_LEFT Or key=INPUT_BUTTON_RIGHT Then
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 61)
		Else
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 67+key-4)
		EndIf
	EndIf

	If forcekey=0 Then
		If Not(CONTROLS(primary,key)=0) Then
		If CONTROLS(primary,key)>=900 Then
			If Menu\Settings\ControllerLayout#<3 and gettruekey=false and CONTROLS_GAMEPAD(key)>0 Then
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 111+CONTROLS_GAMEPAD(key)+27*(Menu\Settings\ControllerLayout#-1))
			Else
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(CONTROLS(primary,key))-57+83)
			EndIf
		Else
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(CONTROLS(primary,key))-1)
		EndIf
		EndIf
	Else
		If Not(forcekey=0) Then
		If forcekey>=900 Then
			If Menu\Settings\ControllerLayout#<3 and gettruekey=false and CONTROLS_GAMEPAD(key)>0 Then
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 111+CONTROLS_GAMEPAD(key)+27*(Menu\Settings\ControllerLayout#-1))
			Else
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(forcekey)-57+83)
			EndIf
		Else
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(forcekey)-1)
		EndIf
		EndIf
	EndIf

End Function

Function DrawSmartKey_Small(key, x#, y#, nocolor=false, forcekey=0, primary=0, gettruekey=false)
	If primary=0 then primary=Menu\Settings\PrimaryController#

	If nocolor Then
		DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, 60)
	Else
		If key=INPUT_BUTTON_CHANGE Or key=INPUT_BUTTON_START Or key=INPUT_BUTTON_BACK Or key=INPUT_BUTTON_CAM_CENTER Or key=INPUT_BUTTON_ACTIONSKILLX Or key=INPUT_BUTTON_CAM_LEFT Or key=INPUT_BUTTON_CAM_RIGHT Or key=INPUT_BUTTON_UP Or key=INPUT_BUTTON_DOWN Or key=INPUT_BUTTON_LEFT Or key=INPUT_BUTTON_RIGHT Then
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, 61)
		Else
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, 67+key-4)
		EndIf
	EndIf

	If forcekey=0 Then
		If Not(CONTROLS(primary,key)=0) Then
		If CONTROLS(primary,key)>=900 Then
			If Menu\Settings\ControllerLayout#<3 and gettruekey=false and CONTROLS_GAMEPAD(key)>0 Then
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, 111+CONTROLS_GAMEPAD(key)+27*(Menu\Settings\ControllerLayout#-1))
			Else
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, RETURN_BUTTON_FROM_KEY(CONTROLS(primary,key))-57+83)
			EndIf
		Else
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, RETURN_BUTTON_FROM_KEY(CONTROLS(primary,key))-1)
		EndIf
		EndIf
	Else
		If Not(forcekey=0) Then
		If forcekey>=900 Then
			If Menu\Settings\ControllerLayout#<3 and gettruekey=false and CONTROLS_GAMEPAD(key)>0 Then
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, 111+CONTROLS_GAMEPAD(key)+27*(Menu\Settings\ControllerLayout#-1))
			Else
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, RETURN_BUTTON_FROM_KEY(forcekey)-57+83)
			EndIf
		Else
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#, RETURN_BUTTON_FROM_KEY(forcekey)-1)
		EndIf
		EndIf
	EndIf

End Function

Function DrawSmartKey_WithNewGamepad(key, x#, y#, nocolor=false, forcekey=0, primary=0, gettruekey=false)
	If primary=0 then primary=Menu\Settings\PrimaryController#

	If nocolor Then
		DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 60)
	Else
		If key=INPUT_BUTTON_CHANGE Or key=INPUT_BUTTON_START Or key=INPUT_BUTTON_BACK Or key=INPUT_BUTTON_CAM_CENTER Or key=INPUT_BUTTON_ACTIONSKILLX Or key=INPUT_BUTTON_CAM_LEFT Or key=INPUT_BUTTON_CAM_RIGHT Or key=INPUT_BUTTON_UP Or key=INPUT_BUTTON_DOWN Or key=INPUT_BUTTON_LEFT Or key=INPUT_BUTTON_RIGHT Then
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 61)
		Else
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 67+key-4)
		EndIf
	EndIf

	If forcekey=0 Then
		If Not(CONTROLS(primary,key)=0) Then
		If CONTROLS(primary,key)>=900 Then
			If Menu\Settings\ControllerLayout#<3 and gettruekey=false and CONTROLS_NEWGAMEPAD(key)>0 Then
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 111+CONTROLS_NEWGAMEPAD(key)+27*(Menu\Settings\ControllerLayout#-1))
			Else
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(CONTROLS(primary,key))-57+83)
			EndIf
		Else
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(CONTROLS(primary,key))-1)
		EndIf
		EndIf
	Else
		If Not(forcekey=0) Then
		If forcekey>=900 Then
			If Menu\Settings\ControllerLayout#<3 and gettruekey=false and CONTROLS_NEWGAMEPAD(key)>0 Then
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, 111+CONTROLS_NEWGAMEPAD(key)+27*(Menu\Settings\ControllerLayout#-1))
			Else
				DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(forcekey)-57+83)
			EndIf
		Else
			DrawImageEx(INTERFACE(Interface_Keys), x#, y#, RETURN_BUTTON_FROM_KEY(forcekey)-1)
		EndIf
		EndIf
	EndIf

End Function

Function DrawSmartKey_MovementGeneral(x#, y#, small=false)
	If small=false Then
		DrawSmartKey_MovementGeneral_Actual(Interface_Keys, x#, y#, small)
	Else
		DrawSmartKey_MovementGeneral_Actual(Interface_Keys_Small, x#, y#, small)
	EndIf
End Function

Function DrawSmartKey_MovementGeneral_Actual(keys, x#, y#, small=false)
	If Menu\Settings\PrimaryController#=2 and Menu\Settings\ControllerLayout#<3 Then
		If CONTROLS_GAMEPAD(INPUT_BUTTON_UP)=11 and CONTROLS_GAMEPAD(INPUT_BUTTON_DOWN)=12 and CONTROLS_GAMEPAD(INPUT_BUTTON_LEFT)=13 and CONTROLS_GAMEPAD(INPUT_BUTTON_RIGHT)=14 Then
			DrawImageEx(INTERFACE(keys), x#, y#, 61)
			DrawImageEx(INTERFACE(keys), x#, y#, 111+10+27*(Menu\Settings\ControllerLayout#-1))
		ElseIf CONTROLS_GAMEPAD(INPUT_BUTTON_UP)=16 and CONTROLS_GAMEPAD(INPUT_BUTTON_DOWN)=17 and CONTROLS_GAMEPAD(INPUT_BUTTON_LEFT)=18 and CONTROLS_GAMEPAD(INPUT_BUTTON_RIGHT)=19 Then
			DrawImageEx(INTERFACE(keys), x#, y#, 61)
			DrawImageEx(INTERFACE(keys), x#, y#, 111+15+27*(Menu\Settings\ControllerLayout#-1))
		ElseIf CONTROLS_GAMEPAD(INPUT_BUTTON_UP)=6 and CONTROLS_GAMEPAD(INPUT_BUTTON_DOWN)=7 and CONTROLS_GAMEPAD(INPUT_BUTTON_LEFT)=8 and CONTROLS_GAMEPAD(INPUT_BUTTON_RIGHT)=9 Then
			DrawImageEx(INTERFACE(keys), x#, y#, 61)
			DrawImageEx(INTERFACE(keys), x#, y#, 111+5+27*(Menu\Settings\ControllerLayout#-1))
		Else
			DrawImageEx(INTERFACE(keys), x#, y#, 63)
		EndIf
	Else
		DrawImageEx(INTERFACE(keys), x#, y#, 63)
	EndIf
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function Menu_Options_Update()

	Menu\Music=3
	Menu\Background=1
	Menu\ShowCards=True

	For i=1 to 5
		Menu_UpdateOptionButtons(i+Menu\OptionOrder) : DrawSmartButton(i, Menu\OptionButton$, GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#)
		Select (i+Menu\OptionOrder)
			Case MENU_CONTROLS#:
				DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-62.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#, 61)
				DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-62.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#, 57)
				DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-32.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#, 61)
				DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-32.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#, 56)
			Case MENU_CONTROLS2#:
				DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-52.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#, 61)
				DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-52.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#+i*50*GAME_WINDOW_SCALE#, 111)
		End Select
	Next

	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#,20)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+130*GAME_WINDOW_SCALE#,21)

	Menu_UpdateWarnings()

	Select Menu\Menu2
		Case 0: Menu_Options_Main_Update()
		Case MENU_RESOLUTION#: Menu_Options_Resolution_Update()
		Case MENU_SCREEN#: Menu_Options_Screen_Update()
		Case MENU_DEBUG#,MENU_DOF#,MENU_BLOOM#,MENU_SHADOWS#,MENU_BLUR#,MENU_SRAYS#,MENU_SOUNDS#,MENU_BUMPMAPS#,MENU_PLANTS#,MENU_VIEW#,MENU_MODS#,MENU_TIPS#,MENU_AUTOCAM#,MENU_VSYNC#, MENU_WATER#:
			Menu_Options_OnOff_Update()
		Case MENU_VOLUME#: Menu_Options_Volume_Update()
		Case MENU_CONTROLS#,MENU_CONTROLS2#: Menu_Options_Controls_Update()
		Case MENU_THEME#: Menu_Options_Theme_Update()
		Case MENU_RESET#: Menu_Options_Reset_Update()
	End Select

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;; controls navigating the options, and what values each section affects.
Function Menu_Options_Main_Update()

	Menu\ControlsToShow=Menu\Menu

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>5 Then Menu\Option=5 : Menu\OptionOrder=Menu\OptionOrder+1
		If Menu\OptionOrder>MENU_RESET#-1 Then Menu\OptionOrder=0
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=1 : Menu\OptionOrder=Menu\OptionOrder-1
		If Menu\OptionOrder<0 Then Menu\OptionOrder=MENU_RESET#-1
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		Menu\Menu2=999
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\NewOption=Menu\Option : Menu\NewMenu=MENU_OPTIONS#
		Menu\OptionOrder2=Menu\OptionOrder+Menu\Option
		If Menu\OptionOrder2>MENU_RESET# Then Menu\OptionOrder2=Menu\OptionOrder2-MENU_RESET#
		Select Menu\OptionOrder2
			Case 1: Menu\NewOption2=Menu\Settings\NewResolution# : Menu\NewMenu2=MENU_RESOLUTION#
			Case 2: Menu\NewOption2=Menu\Settings\ScreenMode# : Menu\NewMenu2=MENU_SCREEN#
			Case 3: Menu\NewOption2=Menu\Settings\Debug# : Menu\NewMenu2=MENU_DEBUG#
			Case 4: Menu\NewOption2=1 : Menu\NewMenu2=MENU_VOLUME#
			Case 5: Menu\NewOption2=1 : Menu\NewMenu2=MENU_CONTROLS# : Menu\ControlAssignmentSource=1 : Menu\OptionOrder2=0 : Menu\ButtonToChange=-1 : Menu\ButtonBeChangeBy=0 : Menu\ButtonWasUsed=0
			Case 6: Menu\NewOption2=1 : Menu\NewMenu2=MENU_CONTROLS2# : Menu\ControlAssignmentSource=2 : Menu\OptionOrder2=0 : Menu\ButtonToChange=-1 : Menu\ButtonBeChangeBy=0 : Menu\ButtonWasUsed=0
			Case 7: Menu\NewOption2=Menu\Settings\DepthOfField# : Menu\NewMenu2=MENU_DOF#
			Case 8: Menu\NewOption2=Menu\Settings\Bloom# : Menu\NewMenu2=MENU_BLOOM#
			Case 9: Menu\NewOption2=Menu\Settings\Shadows# : Menu\NewMenu2=MENU_SHADOWS#
			Case 10: Menu\NewOption2=Menu\Settings\MotionBlur# : Menu\NewMenu2=MENU_BLUR#
			Case 11: Menu\NewOption2=Menu\Settings\SunRays# : Menu\NewMenu2=MENU_SRAYS#
			Case 12: Menu\NewOption2=Menu\Settings\BumpMaps# : Menu\NewMenu2=MENU_BUMPMAPS#
			Case 13: Menu\NewOption2=Menu\Settings\WaterQuality# : Menu\NewMenu2=MENU_WATER#
			Case 14: Menu\NewOption2=Menu\Settings\ThreeDSounds# : Menu\NewMenu2=MENU_SOUNDS#
			Case 15: Menu\NewOption2=Menu\Settings\DisablePlants# : Menu\NewMenu2=MENU_PLANTS#
			Case 16: Menu\NewOption2=Menu\Settings\ViewRange# : Menu\NewMenu2=MENU_VIEW#
			Case 17: Menu\NewOption2=Menu\Settings\AutoCameraDisabled# : Menu\NewMenu2=MENU_AUTOCAM#
			Case 18: Menu\NewOption2=Menu\Settings\VSync# : Menu\NewMenu2=MENU_VSYNC#
			Case 19: Menu\NewOption2=Menu\Settings\Mods# : Menu\NewMenu2=MENU_MODS#
			Case 20: Menu\NewOption2=Menu\Settings\ControlTips# : Menu\NewMenu2=MENU_TIPS#
			Case 21: Menu\NewMenu2=MENU_THEME#
					If Menu\Settings\Mods#=0 Then MENU_THEME_AMOUNT#=MENU_THEME_NONMODAMOUNT#
					Menu\NewOption2=(Menu\Settings\Theme# Mod 7)
					If Menu\NewOption2=0 Then Menu\NewOption2=7
					Menu\OptionOrder2=(Ceil#(Menu\Settings\Theme#/7.0)-1)*7
					If Menu\OptionOrder2>MENU_THEME_AMOUNT#-7 Then
						Menu\OptionOrder2=MENU_THEME_AMOUNT#-7
						Menu\NewOption2=abs(MENU_THEME_AMOUNT#-Menu\Settings\Theme#-7)
					EndIf
					Menu_Options_Theme_LoadThumbnail()
			Case 22: Menu\NewOption2=1 : Menu\NewMenu2=MENU_RESET#
		End Select
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		Menu\Menu2=0
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=3 : Menu\NewMenu=MENU_MAIN#
		Menu_Options_Save()
		FlushAll()
	EndIf

End Function

Function Menu_Options_Save()
	If Menu\ControlsAfterOptions Then
		For i=0 to 17 : CONTROLS_GAMEPAD(i) = CONTROLS_NEWGAMEPAD(i) : Next
		Game_MainControlConfig() : Menu\ControlsAfterOptions=False
	EndIf

	If Menu\Settings\NewTheme#>0 Then Menu\Settings\Theme#=Menu\Settings\NewTheme#

	If Menu\CloseAfterOptions Then
		If Menu\Settings\Mods#=0 and Menu\Settings\Theme#>MENU_THEME_NONMODAMOUNT# Then Menu\Settings\Theme#=1
		Menu\NewMenu=MENU_CLOSE#
		Menu\Settings\Resolution#=Menu\Settings\NewResolution#
	Else
		If Menu\LoadThemeAfterOptions Then
			For x = INTERFACE_ALWAYSTOTAL+1 to INTERFACE_TOTAL : FreeSmartImage(x) : Next
			FreeSmartImage(Interface_Card1) : FreeSmartImage(Interface_Buttons_1) : FreeSmartImage(Interface_Buttons_2)
			For x = SOUNDS_ALWAYSTOTAL+1 to SOUNDS_TOTAL : FreeSmartSound(x) : Next
			For x=INTERFACE_ALWAYSTOTAL+1 to INTERFACE_MENUTOTAL : LoadSmartImage(x) : Next
			LoadSmartImage(Interface_Card1) : LoadSmartImage(Interface_Buttons_1) : LoadSmartImage(Interface_Buttons_2)
			LoadMenuMusic()
			Delay(75)
			Menu\LoadThemeAfterOptions=False
			Menu\NewOption=1 : Menu\Menu=MENU_START# : Menu\NewMenu=MENU_START#
		EndIf

		If Menu\SoundVolumeAfterOptions Then
			UpdateAllSoundVolumes() : Menu\SoundVolumeAfterOptions=False
		EndIf
	EndIf

	SaveGame(false)

	If Menu\ResetOptionsAfterOptions Then ResetOptions() : Menu\ResetOptionsAfterOptions=False
	If Menu\ResetGameAfterOptions Then ResetGame() : Menu\ResetGameAfterOptions=False
	If Menu\ResetRecordsAfterOptions Then ResetRecords() : Menu\ResetRecordsAfterOptions=False
	If Menu\ResetGardenAfterOptions Then ResetGarden() : Menu\ResetGardenAfterOptions=False

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;; Design Pattern for options that only can be set to one specific thing in a list.
Function Menu_Options_OnOff_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	Select Menu\Menu2
		Case MENU_SOUNDS#,MENU_TIPS#:
			DrawSmartButton(1, "On", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(0, "Off", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+55*GAME_WINDOW_SCALE#, true)
		Case MENU_DEBUG#:
			DrawSmartButton(1, "On", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+40*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(0, "Off", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+90*GAME_WINDOW_SCALE#, true)
		Case MENU_PLANTS#:
			DrawSmartButton(0, "All", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-10*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(1, "Stage Only", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+40*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(2, "None", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+90*GAME_WINDOW_SCALE#, true)
		Case MENU_VIEW#:
			DrawSmartButton(0, "Automatic", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-5*GAME_WINDOW_SCALE#, true, true)
			DrawSmartButton(1, "Small", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#, true, true)
			DrawSmartButton(2, "Normal", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+55*GAME_WINDOW_SCALE#, true, true)
			DrawSmartButton(3, "Big", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+85*GAME_WINDOW_SCALE#, true, true)
			DrawSmartButton(4, "Huge", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+115*GAME_WINDOW_SCALE#, true, true)
		Case MENU_MODS#:
			DrawSmartButton(1, "On", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+45*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(0, "Off", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#, true)
		Case MENU_AUTOCAM#:
			DrawSmartButton(0, "On", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(1, "Off", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+55*GAME_WINDOW_SCALE#, true)
		Default:
			DrawSmartButton(1, "On", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#, true)
			DrawSmartButton(0, "Off", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#, true)
	End Select
	Select Menu\Menu2
		Case MENU_SOUNDS#:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-130)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+100)*GAME_WINDOW_SCALE#)
		Case MENU_TIPS#:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-130)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-70*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+100)*GAME_WINDOW_SCALE#)
		Case MENU_DOF#,MENU_BLUR#,MENU_SRAYS#,MENU_BUMPMAPS#,MENU_SHADOWS#:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-70*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)
		Case MENU_DEBUG#:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-95*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)
			SetColor(255,0,0)
			DrawRealText("Press and hold F4 to see the debug controls.", GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)
			SetColor(255,255,255)
		Case MENU_VSYNC#:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-95*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+160)*GAME_WINDOW_SCALE#)
		Case MENU_VIEW#:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-105*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+130)*GAME_WINDOW_SCALE#)
		Default:
			DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-95*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)
	End Select

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2+1
		Select Menu\Menu2
			Case MENU_PLANTS#: If Menu\Option2>2 Then Menu\Option2=0
			Case MENU_VIEW#: If Menu\Option2>4 Then Menu\Option2=0
			Default: If Menu\Option2>1 Then Menu\Option2=0
		End Select
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2-1
		Select Menu\Menu2
			Case MENU_PLANTS#: If Menu\Option2<0 Then Menu\Option2=2
			Case MENU_VIEW#: If Menu\Option2<0 Then Menu\Option2=4
			Default: If Menu\Option2<0 Then Menu\Option2=1
		End Select
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Select Menu\Menu2
			Case MENU_DEBUG#: FlushAll() : Menu\Settings\Debug#=Menu\Option2
			Case MENU_DOF#: Menu\Settings\DepthOfField#=Menu\Option2
			Case MENU_BLOOM#: Menu\Settings\Bloom#=Menu\Option2
			Case MENU_SHADOWS#: Menu\Settings\Shadows#=Menu\Option2
			Case MENU_BLUR#: Menu\Settings\MotionBlur#=Menu\Option2
			Case MENU_SRAYS#: Menu\Settings\SunRays#=Menu\Option2
			Case MENU_SOUNDS#: Menu\Settings\ThreeDSounds#=Menu\Option2
			Case MENU_BUMPMAPS#: Menu\Settings\BumpMaps#=Menu\Option2
			Case MENU_WATER#: Menu\Settings\WaterQuality#=Menu\Option2
			Case MENU_PLANTS#: Menu\Settings\DisablePlants#=Menu\Option2
			Case MENU_VIEW#: Menu\Settings\ViewRange#=Menu\Option2
			Case MENU_AUTOCAM#: Menu\Settings\AutoCameraDisabled#=Menu\Option2
			Case MENU_VSYNC#: Menu\Settings\VSync#=Menu\Option2 : Menu\CloseAfterOptions=True
			Case MENU_MODS#: Menu\Settings\Mods#=Menu\Option2 : Menu\CloseAfterOptions=True
			Case MENU_TIPS#: Menu\Settings\ControlTips#=Menu\Option2
		End Select
		Menu\NewMenu2=0
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=0
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Global MENU_THEME_NONMODAMOUNT# = 32
Global MENU_THEME_AMOUNT# = MENU_THEME_NONMODAMOUNT#+8

Function Menu_Options_Theme_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	For i=1 to 7
		Select(i+Menu\OptionOrder2)
			Case 1: j$ = "Sky"
			Case 2: j$ = "Space"
			Case 3: j$ = "Island"
			Case 4: j$ = "Underwater"
			Case 5: j$ = "City"
			Case 6: j$ = "Expo"
			Case 7: j$ = "Winter"
			Case 8: j$ = "Heroes"
			Case 9: j$ = "Forest"
			Case 10: j$ = "Rainy"
			Case 11: j$ = "Adventure"
			Case 12: j$ = "Sunny"
			Case 13: j$ = "Volcano"
			Case 14: j$ = "Amusement"
			Case 15: j$ = "Windy"
			Case 16: j$ = "Retro"
			Case 17: j$ = "Night"
			Case 18: j$ = "Kingdom"
			Case 19: j$ = "Rush"
			Case 20: j$ = "Advance"
			Case 21: j$ = "Dream"
			Case 22: j$ = "Special"
			Case 23: j$ = "Buddy"
			Case 24: j$ = "Chao"
			Case 25: j$ = "Summer"
			Case 26: j$ = "Classic"
			Case 27: j$ = "Sacred"
			Case 28: j$ = "Resistance"
			Case 29: j$ = "Edgy"
			Case 30: j$ = "World"
			Case 31: j$ = "Hex"
			Case 32: j$ = "Rival"
			Default:
				If (i+Menu\OptionOrder2)>MENU_THEME_NONMODAMOUNT# Then
					j$ = "Custom "+Int((i+Menu\OptionOrder2)-MENU_THEME_NONMODAMOUNT#)
				Else
					j$ = "???"
				EndIf
		End Select
		DrawSmartButton(i, j$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-135*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#+(i-1)*30*GAME_WINDOW_SCALE#, true, true)
		If i=1 and Menu\Option2=1 Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE2#-135*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#+(i-1)*30*GAME_WINDOW_SCALE#-30*GAME_WINDOW_SCALE#,20)
		If i=7 and Menu\Option2=7 Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE2#-135*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#+(i-1)*30*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#,21)
	Next

	SetScale(GAME_WINDOW_SCALE#*0.3375, GAME_WINDOW_SCALE#*0.3375)
	DrawImageEx(INTERFACE(Interface_StageSelectThumbnails[1]), GAME_WINDOW_W/2+(BUTTON_PLACE2#-135*1)*GAME_WINDOW_SCALE#+202.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+8.75*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#*1.025, GAME_WINDOW_SCALE#*0.825)
	DrawImageEx(INTERFACE(Interface_Square), GAME_WINDOW_W/2+(BUTTON_PLACE2#-135*1)*GAME_WINDOW_SCALE#+202.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+8.75*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)

	DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-116.75*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		If Menu\Option2=7 Then
			Menu\OptionOrder2=Menu\OptionOrder2+1
			If (Menu\Option2+Menu\OptionOrder2)>MENU_THEME_AMOUNT# Then
				Menu\Option2=1
				Menu\OptionOrder2=0
			EndIf
		Else
			Menu\Option2=Menu\Option2+1
		EndIf
		Menu_Options_Theme_LoadThumbnail()
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		If Menu\Option2=1 Then
			Menu\OptionOrder2=Menu\OptionOrder2-1
			If (Menu\Option2+Menu\OptionOrder2)<1 Then
				Menu\Option2=7
				Menu\OptionOrder2=MENU_THEME_AMOUNT#-7
			EndIf
		Else
			Menu\Option2=Menu\Option2-1
		EndIf
		Menu_Options_Theme_LoadThumbnail()
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\Settings\NewTheme#=Menu\Option2+Menu\OptionOrder2 : Menu\LoadThemeAfterOptions=True
		Menu\NewMenu2=0
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=0
	EndIf
End Function

Function Menu_Options_Theme_LoadThumbnail()
For i=1 to 1
	If INTERFACE_EXISTS(Interface_StageSelectThumbnails[i]) Then FreeSmartImage(Interface_StageSelectThumbnails[i])
	If Menu\Transition=1 Then
		themeno = Menu\NewOption2+Menu\OptionOrder2
	Else
		themeno = Menu\Option2+Menu\OptionOrder2
	EndIf
	If i=2 Then
		If themeno<=MENU_THEME_NONMODAMOUNT# Then
			If Not(FileType("Interface/Background"+Int(themeno)+".png")=1) Then
				LoadSmartFastImage("Interface/Background"+Int(themeno)+"a.png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			Else
				LoadSmartFastImage("Interface/Background"+Int(themeno)+".png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			EndIf
		Else
			If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(themeno-MENU_THEME_NONMODAMOUNT#)+"/Background.png")=1) Then
				LoadSmartFastImage("Interface/Background"+Int(1)+".png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			Else
				LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(themeno-MENU_THEME_NONMODAMOUNT#)+"/Background.png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			EndIf
		EndIf
	Else
		If themeno<=MENU_THEME_NONMODAMOUNT# Then
			If Not(FileType("Interface/Sky"+Int(themeno)+".png")=1) Then
				LoadSmartFastImage("Interface/Sky"+Int(themeno)+"a.png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			Else
				LoadSmartFastImage("Interface/Sky"+Int(themeno)+".png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			EndIf
		Else
			If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(themeno-MENU_THEME_NONMODAMOUNT#)+"/Sky.png")=1) Then
				LoadSmartFastImage("Interface/Sky"+Int(1)+".png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			Else
				LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(themeno-MENU_THEME_NONMODAMOUNT#)+"/Sky.png", Interface_StageSelectThumbnails[i], 1440, 900, 0, 1, 2.3, 2.3, false, false, true)
			EndIf
		EndIf
	EndIf
Next
End Function

;===============================================================================================================================================================

Function Menu_Options_Screen_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	DrawSmartButton(1, "Fullscreen", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#, true)
	DrawSmartButton(0, "Windowed", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#, true)

	DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-90*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2+1
		If Menu\Option2>1 Then Menu\Option2=0
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2-1
		If Menu\Option2<0 Then Menu\Option2=1
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		If Menu\Option2<>Menu\Settings\ScreenMode# Then Menu\Settings\ScreenModeChanged#=1
		Menu\Settings\ScreenMode#=Menu\Option2 : Menu\CloseAfterOptions=True
		Menu\NewMenu2=0
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=0
	EndIf

End Function

;===============================================================================================================================================================

Function Menu_Options_Resolution_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	DrawSmartButton(1 , "1066x568", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#-1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+0*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(2 , "1280x720", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#-1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+1*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(3 , "1280x768", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#-1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+2*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(4 , "1280x800", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#-1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+3*30*GAME_WINDOW_SCALE#, true, true)

	DrawSmartButton(5 , "1360x768", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+0*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+0*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(6 , "1366x768", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+0*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+1*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(7 , "1440x900", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+0*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+2*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(8 , "1440x960", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+0*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+3*30*GAME_WINDOW_SCALE#, true, true)

	DrawSmartButton(9 , "1600x900", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+0*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(10, "1680x1050", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+1*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(11, "1920x1080", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+2*30*GAME_WINDOW_SCALE#, true, true)
	DrawSmartButton(12, "1920x1200", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+1*135*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+3*30*GAME_WINDOW_SCALE#, true, true)

	DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-165)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-105*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+150)*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option2
			Case 1: Menu\Option2=2
			Case 2: Menu\Option2=3
			Case 3: Menu\Option2=4
			Case 4: Menu\Option2=1
			Case 5: Menu\Option2=6
			Case 6: Menu\Option2=7
			Case 7: Menu\Option2=8
			Case 8: Menu\Option2=5
			Case 9: Menu\Option2=10
			Case 10: Menu\Option2=11
			Case 11: Menu\Option2=12
			Case 12: Menu\Option2=9
		End Select
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option2
			Case 1: Menu\Option2=4
			Case 2: Menu\Option2=1
			Case 3: Menu\Option2=2
			Case 4: Menu\Option2=3
			Case 5: Menu\Option2=8
			Case 6: Menu\Option2=5
			Case 7: Menu\Option2=6
			Case 8: Menu\Option2=7
			Case 9: Menu\Option2=12
			Case 10: Menu\Option2=9
			Case 11: Menu\Option2=10
			Case 12: Menu\Option2=11
		End Select
	EndIf

	If Input\Pressed\Right Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option2
			Case 1: Menu\Option2=5
			Case 2: Menu\Option2=6
			Case 3: Menu\Option2=7
			Case 4: Menu\Option2=8
			Case 5: Menu\Option2=9
			Case 6: Menu\Option2=10
			Case 7: Menu\Option2=11
			Case 8: Menu\Option2=12
			Case 9: Menu\Option2=1
			Case 10: Menu\Option2=2
			Case 11: Menu\Option2=3
			Case 12: Menu\Option2=4
		End Select
	EndIf

	If Input\Pressed\Left Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option2
			Case 1: Menu\Option2=9
			Case 2: Menu\Option2=10
			Case 3: Menu\Option2=11
			Case 4: Menu\Option2=12
			Case 5: Menu\Option2=1
			Case 6: Menu\Option2=2
			Case 7: Menu\Option2=3
			Case 8: Menu\Option2=4
			Case 9: Menu\Option2=5
			Case 10: Menu\Option2=6
			Case 11: Menu\Option2=7
			Case 12: Menu\Option2=8
		End Select
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\Settings\NewResolution#=Menu\Option2 : Menu\CloseAfterOptions=True
		Menu\NewMenu2=0
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=0
	EndIf

End Function

;===============================================================================================================================================================

Function DrawArrow(x#, y#)
	If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
	If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT# Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#
	Menu\ButtonSize#=Menu\ButtonSize2#

	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 18)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
End Function

;===============================================================================================================================================================

Function Menu_Options_Controls_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	If Menu\ButtonWasChangedTimer>0 Then Menu\ButtonWasChangedTimer=Menu\ButtonWasChangedTimer-timervalue#

	If Menu\ButtonToChange=-1 Then;!!!!!

	If Not(Menu\ButtonIconChoice>0) Then;!!!

	DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-165)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-114.5*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+45)*GAME_WINDOW_SCALE#)

	;***************************************************************************************************************************
	;***************************************************************************************************************************
	If Menu\OptionOrder2=0 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150-25)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*-1)*GAME_WINDOW_SCALE#)
	DrawRealText("Reset", GAME_WINDOW_W/2+(BUTTON_PLACE2#-150-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	;***************************************************************************************************************************
	;***************************************************************************************************************************
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, 64)
	If Menu\OptionOrder2=1 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_UP, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_UP, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Up: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, 65)
	If Menu\OptionOrder2=2 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_DOWN, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_DOWN, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Down: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, 66)
	If Menu\OptionOrder2=3 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_LEFT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_LEFT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Left: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, 67)
	If Menu\OptionOrder2=4 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_RIGHT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_RIGHT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Right: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, 80)
	If Menu\OptionOrder2=5 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_CHANGE, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_CHANGE, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Change leader: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, 81)
	If Menu\OptionOrder2=6 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONACT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONACT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Interact: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;***************************************************************************************************************************
	;***************************************************************************************************************************
	If Menu\ControlAssignmentSource=2 Then
		If Menu\OptionOrder2=10 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150-25+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*-1)*GAME_WINDOW_SCALE#)
		DrawRealText("Change layout:", GAME_WINDOW_W/2+(BUTTON_PLACE2#-150-10+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+205+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*-1)*GAME_WINDOW_SCALE#, 166+Menu\Settings\ControllerLayout#-1)
	EndIf
	;***************************************************************************************************************************
	;***************************************************************************************************************************
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, 68)
	If Menu\OptionOrder2=11 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Jump: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, 69)
	If Menu\OptionOrder2=12 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Roll: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, 70)
	If Menu\OptionOrder2=13 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Drift: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, 71)
	If Menu\OptionOrder2=14 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONSKILL1, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Skill 1: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, 72)
	If Menu\OptionOrder2=15 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Skill 2: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, 73)
	If Menu\OptionOrder2=16 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONSKILL3, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Skill 3: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;***************************************************************************************************************************
	;***************************************************************************************************************************
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, 74)
	If Menu\OptionOrder2=21 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_START, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_START, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Start, pause, accept: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*0)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, 75)
	If Menu\OptionOrder2=22 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_BACK, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_BACK, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Back: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*1)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, 78)
	If Menu\OptionOrder2=23 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_CAM_LEFT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_CAM_LEFT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Turn cam left: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*2)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, 79)
	If Menu\OptionOrder2=24 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_CAM_RIGHT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_CAM_RIGHT, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Turn cam right: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*3)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, 77)
	If Menu\OptionOrder2=25 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_CAM_CENTER, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_CAM_CENTER, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Center cam: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*4)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, 76)
	If Menu\OptionOrder2=26 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+30+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#)
	DrawSmartKey(INPUT_BUTTON_ACTIONSKILLX, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+60+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource, true)
	If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then DrawSmartKey_WithNewGamepad(INPUT_BUTTON_ACTIONSKILLX, GAME_WINDOW_W/2+(BUTTON_PLACE2#-150+90+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#, true, 0, Menu\ControlAssignmentSource)
	;;;
	DrawRealText("Extra skill: ", GAME_WINDOW_W/2+(BUTTON_PLACE2#-160+250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-40+33*5)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, (Interface_Text_1))
	;***************************************************************************************************************************
	;***************************************************************************************************************************

	If Not(Menu\ButtonWasChangedTimer>0) Then
		If Input\Pressed\Down Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\OptionOrder2
				Case 0: Menu\OptionOrder2=1
				Case 1: Menu\OptionOrder2=2
				Case 2: Menu\OptionOrder2=3
				Case 3: Menu\OptionOrder2=4
				Case 4: Menu\OptionOrder2=5
				Case 5: Menu\OptionOrder2=6
				Case 6: Menu\OptionOrder2=1
				Case 10: Menu\OptionOrder2=11
				Case 11: Menu\OptionOrder2=12
				Case 12: Menu\OptionOrder2=13
				Case 13: Menu\OptionOrder2=14
				Case 14: Menu\OptionOrder2=15
				Case 15: Menu\OptionOrder2=16
				Case 16: If Menu\ControlAssignmentSource=1 Then Menu\OptionOrder2=11 Else Menu\OptionOrder2=10
				Case 21: Menu\OptionOrder2=22
				Case 22: Menu\OptionOrder2=23
				Case 23: Menu\OptionOrder2=24
				Case 24: Menu\OptionOrder2=25
				Case 25: Menu\OptionOrder2=26
				Case 26: Menu\OptionOrder2=21
			End Select
		EndIf

		If Input\Pressed\Up Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\OptionOrder2
				Case 0: Menu\OptionOrder2=6
				Case 1: Menu\OptionOrder2=0
				Case 2: Menu\OptionOrder2=1
				Case 3: Menu\OptionOrder2=2
				Case 4: Menu\OptionOrder2=3
				Case 5: Menu\OptionOrder2=4
				Case 6: Menu\OptionOrder2=5
				Case 10: Menu\OptionOrder2=16
				Case 11: If Menu\ControlAssignmentSource=1 Then Menu\OptionOrder2=16 Else Menu\OptionOrder2=10
				Case 12: Menu\OptionOrder2=11
				Case 13: Menu\OptionOrder2=12
				Case 14: Menu\OptionOrder2=13
				Case 15: Menu\OptionOrder2=14
				Case 16: Menu\OptionOrder2=15
				Case 21: Menu\OptionOrder2=26
				Case 22: Menu\OptionOrder2=21
				Case 23: Menu\OptionOrder2=22
				Case 24: Menu\OptionOrder2=23
				Case 25: Menu\OptionOrder2=24
				Case 26: Menu\OptionOrder2=25
			End Select
		EndIf

		If Input\Pressed\Right Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\OptionOrder2
				Case 0: If Menu\ControlAssignmentSource=1 Then Menu\OptionOrder2=11 Else Menu\OptionOrder2=10
				Case 1: Menu\OptionOrder2=11
				Case 2: Menu\OptionOrder2=12
				Case 3: Menu\OptionOrder2=13
				Case 4: Menu\OptionOrder2=14
				Case 5: Menu\OptionOrder2=15
				Case 6: Menu\OptionOrder2=16
				Case 10: Menu\OptionOrder2=0
				Case 11: Menu\OptionOrder2=21
				Case 12: Menu\OptionOrder2=22
				Case 13: Menu\OptionOrder2=23
				Case 14: Menu\OptionOrder2=24
				Case 15: Menu\OptionOrder2=25
				Case 16: Menu\OptionOrder2=26
				Case 21: Menu\OptionOrder2=1
				Case 22: Menu\OptionOrder2=2
				Case 23: Menu\OptionOrder2=3
				Case 24: Menu\OptionOrder2=4
				Case 25: Menu\OptionOrder2=5
				Case 26: Menu\OptionOrder2=6
			End Select
		EndIf

		If Input\Pressed\Left Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\OptionOrder2
				Case 0: If Menu\ControlAssignmentSource=1 Then Menu\OptionOrder2=21 Else Menu\OptionOrder2=10
				Case 1: Menu\OptionOrder2=21
				Case 2: Menu\OptionOrder2=22
				Case 3: Menu\OptionOrder2=23
				Case 4: Menu\OptionOrder2=24
				Case 5: Menu\OptionOrder2=25
				Case 6: Menu\OptionOrder2=26
				Case 10: Menu\OptionOrder2=0
				Case 11: Menu\OptionOrder2=1
				Case 12: Menu\OptionOrder2=2
				Case 13: Menu\OptionOrder2=3
				Case 14: Menu\OptionOrder2=4
				Case 15: Menu\OptionOrder2=5
				Case 16: Menu\OptionOrder2=6
				Case 21: Menu\OptionOrder2=11
				Case 22: Menu\OptionOrder2=12
				Case 23: Menu\OptionOrder2=13
				Case 24: Menu\OptionOrder2=14
				Case 25: Menu\OptionOrder2=15
				Case 26: Menu\OptionOrder2=16
			End Select
		EndIf

		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			PlaySmartSound(Sound_MenuAccept)
			Menu\ControlsAfterOptions=True
			Select Menu\OptionOrder2
				Case 0:
					Select Menu\ControlAssignmentSource
						Case 1: ResetOptions_ResetKeyboard()
						Case 2: ResetOptions_ResetNewGamepad()
					End Select
					Menu\ButtonWasChangedTimer=0.1*secs#
				Case 1: Menu\ButtonToChange=INPUT_BUTTON_UP
				Case 2: Menu\ButtonToChange=INPUT_BUTTON_DOWN
				Case 3: Menu\ButtonToChange=INPUT_BUTTON_LEFT
				Case 4: Menu\ButtonToChange=INPUT_BUTTON_RIGHT
				Case 5: Menu\ButtonToChange=INPUT_BUTTON_CHANGE
				Case 6: Menu\ButtonToChange=INPUT_BUTTON_ACTIONACT
				Case 10:
					Select Menu\Settings\ControllerLayout#
						Case 1: Menu\Settings\ControllerLayout#=2
						Case 2: Menu\Settings\ControllerLayout#=3
						Case 3: Menu\Settings\ControllerLayout#=1
					End Select
				Case 11: Menu\ButtonToChange=INPUT_BUTTON_ACTIONJUMP
				Case 12: Menu\ButtonToChange=INPUT_BUTTON_ACTIONROLL
				Case 13: Menu\ButtonToChange=INPUT_BUTTON_ACTIONDRIFT
				Case 14: Menu\ButtonToChange=INPUT_BUTTON_ACTIONSKILL1
				Case 15: Menu\ButtonToChange=INPUT_BUTTON_ACTIONSKILL2
				Case 16: Menu\ButtonToChange=INPUT_BUTTON_ACTIONSKILL3
				Case 21: Menu\ButtonToChange=INPUT_BUTTON_START
				Case 22: Menu\ButtonToChange=INPUT_BUTTON_BACK
				Case 23: Menu\ButtonToChange=INPUT_BUTTON_CAM_LEFT
				Case 24: Menu\ButtonToChange=INPUT_BUTTON_CAM_RIGHT
				Case 25: Menu\ButtonToChange=INPUT_BUTTON_CAM_CENTER
				Case 26: Menu\ButtonToChange=INPUT_BUTTON_ACTIONSKILLX
			End Select
			FlushAll()
		EndIf

		If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
			If Menu_Options_Controls_ReturnNonEmpty() Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewMenu2=0
			Else
				PlaySmartSound(Sound_MenuRefuse)
			EndIf
		EndIf

		If Input\Pressed\ActionSkill2 and (Not(Menu\OptionOrder2<1)) Then
			PlaySmartSound(Sound_MenuAccept)
			Menu\ControlsAfterOptions=True
			Select Menu\OptionOrder2
				Case 1: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_UP)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_UP)=-1
				Case 2: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_DOWN)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_DOWN)=-1
				Case 3: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_LEFT)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_LEFT)=-1
				Case 4: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_RIGHT)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_RIGHT)=-1
				Case 5: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_CHANGE)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CHANGE)=-1
				Case 6: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONACT)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONACT)=-1
				Case 11: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONJUMP)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONJUMP)=-1
				Case 12: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONROLL)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONROLL)=-1
				Case 13: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONDRIFT)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONDRIFT)=-1
				Case 14: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONSKILL1)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILL1)=-1
				Case 15: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONSKILL2)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILL2)=-1
				Case 16: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONSKILL3)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILL3)=-1
				Case 21: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_START)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_START)=-1
				Case 22: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_BACK)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_BACK)=-1
				Case 23: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_CAM_LEFT)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CAM_LEFT)=-1
				Case 24: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_CAM_RIGHT)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CAM_RIGHT)=-1
				Case 25: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_CAM_CENTER)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CAM_CENTER)=-1
				Case 26: CONTROLS(Menu\ControlAssignmentSource,INPUT_BUTTON_ACTIONSKILLX)=KEY_EMPTY : CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILLX)=-1
			End Select
			FlushAll()
		EndIf
	EndIf

	Else;!!!

	SetColor(200,200,200)
	DrawRealText("Choose a gamepad button icon for this key.", GAME_WINDOW_W/2+(BUTTON_PLACE2#-165)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#, (Interface_Text_3), 0)
	SetColor(255,255,255)

	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 111+1+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=1 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 111+2+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=2 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 111+3+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=3 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+600*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 111+4+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=4 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 111+6+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=5 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 111+7+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=6 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 111+8+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=11 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 111+9+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=12 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 111+11+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=13 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 111+12+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=14 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 111+13+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=15 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 111+14+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=16 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*1)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 111+16+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=21 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 111+17+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=22 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 111+18+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=23 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 111+19+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=24 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 111+20+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=25 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 111+21+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=26 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*2)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#, 111+22+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=31 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*0)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#, 111+23+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=32 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*1)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#, 111+24+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=33 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*2)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#, 111+25+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=34 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*3)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#, 111+26+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=35 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*4)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 61)
	DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2+(BUTTON_PLACE2#-90+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#, 111+27+27*(Menu\Settings\ControllerLayout#-1))
	If Menu\ButtonIconChoice=36 Then DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-90-25+60*3)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-45+30*5)*GAME_WINDOW_SCALE#)
	;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	If Not(Menu\ButtonWasChangedTimer>0) Then
		If Input\Pressed\Down Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\ButtonIconChoice
				Case 1: Menu\ButtonIconChoice=2
				Case 2: Menu\ButtonIconChoice=3
				Case 3: Menu\ButtonIconChoice=4
				Case 4: Menu\ButtonIconChoice=5
				Case 5: Menu\ButtonIconChoice=6
				Case 6: Menu\ButtonIconChoice=1
				Case 11: Menu\ButtonIconChoice=12
				Case 12: Menu\ButtonIconChoice=13
				Case 13: Menu\ButtonIconChoice=14
				Case 14: Menu\ButtonIconChoice=15
				Case 15: Menu\ButtonIconChoice=16
				Case 16: Menu\ButtonIconChoice=11
				Case 21: Menu\ButtonIconChoice=22
				Case 22: Menu\ButtonIconChoice=23
				Case 23: Menu\ButtonIconChoice=24
				Case 24: Menu\ButtonIconChoice=25
				Case 25: Menu\ButtonIconChoice=26
				Case 26: Menu\ButtonIconChoice=21
				Case 31: Menu\ButtonIconChoice=32
				Case 32: Menu\ButtonIconChoice=33
				Case 33: Menu\ButtonIconChoice=34
				Case 34: Menu\ButtonIconChoice=35
				Case 35: Menu\ButtonIconChoice=36
				Case 36: Menu\ButtonIconChoice=31
			End Select
		EndIf

		If Input\Pressed\Up Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\ButtonIconChoice
				Case 1: Menu\ButtonIconChoice=6
				Case 2: Menu\ButtonIconChoice=1
				Case 3: Menu\ButtonIconChoice=2
				Case 4: Menu\ButtonIconChoice=3
				Case 5: Menu\ButtonIconChoice=4
				Case 6: Menu\ButtonIconChoice=5
				Case 11: Menu\ButtonIconChoice=16
				Case 12: Menu\ButtonIconChoice=11
				Case 13: Menu\ButtonIconChoice=12
				Case 14: Menu\ButtonIconChoice=13
				Case 15: Menu\ButtonIconChoice=14
				Case 16: Menu\ButtonIconChoice=15
				Case 21: Menu\ButtonIconChoice=26
				Case 22: Menu\ButtonIconChoice=21
				Case 23: Menu\ButtonIconChoice=22
				Case 24: Menu\ButtonIconChoice=23
				Case 25: Menu\ButtonIconChoice=24
				Case 26: Menu\ButtonIconChoice=25
				Case 31: Menu\ButtonIconChoice=36
				Case 32: Menu\ButtonIconChoice=31
				Case 33: Menu\ButtonIconChoice=32
				Case 34: Menu\ButtonIconChoice=33
				Case 35: Menu\ButtonIconChoice=34
				Case 36: Menu\ButtonIconChoice=35
			End Select
		EndIf

		If Input\Pressed\Right Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\ButtonIconChoice
				Case 1: Menu\ButtonIconChoice=11
				Case 2: Menu\ButtonIconChoice=12
				Case 3: Menu\ButtonIconChoice=13
				Case 4: Menu\ButtonIconChoice=14
				Case 5: Menu\ButtonIconChoice=15
				Case 6: Menu\ButtonIconChoice=16
				Case 11: Menu\ButtonIconChoice=21
				Case 12: Menu\ButtonIconChoice=22
				Case 13: Menu\ButtonIconChoice=23
				Case 14: Menu\ButtonIconChoice=24
				Case 15: Menu\ButtonIconChoice=25
				Case 16: Menu\ButtonIconChoice=26
				Case 21: Menu\ButtonIconChoice=31
				Case 22: Menu\ButtonIconChoice=32
				Case 23: Menu\ButtonIconChoice=33
				Case 24: Menu\ButtonIconChoice=34
				Case 25: Menu\ButtonIconChoice=35
				Case 26: Menu\ButtonIconChoice=36
				Case 31: Menu\ButtonIconChoice=1
				Case 32: Menu\ButtonIconChoice=2
				Case 33: Menu\ButtonIconChoice=3
				Case 34: Menu\ButtonIconChoice=4
				Case 35: Menu\ButtonIconChoice=5
				Case 36: Menu\ButtonIconChoice=6
			End Select
		EndIf

		If Input\Pressed\Left Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\ButtonIconChoice
				Case 1: Menu\ButtonIconChoice=31
				Case 2: Menu\ButtonIconChoice=32
				Case 3: Menu\ButtonIconChoice=33
				Case 4: Menu\ButtonIconChoice=34
				Case 5: Menu\ButtonIconChoice=35
				Case 6: Menu\ButtonIconChoice=36
				Case 11: Menu\ButtonIconChoice=1
				Case 12: Menu\ButtonIconChoice=2
				Case 13: Menu\ButtonIconChoice=3
				Case 14: Menu\ButtonIconChoice=4
				Case 15: Menu\ButtonIconChoice=5
				Case 16: Menu\ButtonIconChoice=6
				Case 21: Menu\ButtonIconChoice=11
				Case 22: Menu\ButtonIconChoice=12
				Case 23: Menu\ButtonIconChoice=13
				Case 24: Menu\ButtonIconChoice=14
				Case 25: Menu\ButtonIconChoice=15
				Case 26: Menu\ButtonIconChoice=16
				Case 31: Menu\ButtonIconChoice=21
				Case 32: Menu\ButtonIconChoice=22
				Case 33: Menu\ButtonIconChoice=23
				Case 34: Menu\ButtonIconChoice=24
				Case 35: Menu\ButtonIconChoice=25
				Case 36: Menu\ButtonIconChoice=26
			End Select
		EndIf

		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			oldbuttonchoice = CONTROLS_NEWGAMEPAD(Menu\ButtonThatWasChanged)
			Select Menu\ButtonIconChoice
				Case 1: newbuttonchoice=1
				Case 2: newbuttonchoice=2
				Case 3: newbuttonchoice=3
				Case 4: newbuttonchoice=4
				Case 5: newbuttonchoice=6
				Case 6: newbuttonchoice=7
				Case 11: newbuttonchoice=8
				Case 12: newbuttonchoice=9
				Case 13: newbuttonchoice=11
				Case 14: newbuttonchoice=12
				Case 15: newbuttonchoice=13
				Case 16: newbuttonchoice=14
				Case 21: newbuttonchoice=16
				Case 22: newbuttonchoice=17
				Case 23: newbuttonchoice=18
				Case 24: newbuttonchoice=19
				Case 25: newbuttonchoice=20
				Case 26: newbuttonchoice=21
				Case 31: newbuttonchoice=22
				Case 32: newbuttonchoice=23
				Case 33: newbuttonchoice=24
				Case 34: newbuttonchoice=25
				Case 35: newbuttonchoice=26
				Case 36: newbuttonchoice=27
			End Select
			newbuttonalreadyused = false
			For i=0 to 17
				If CONTROLS_NEWGAMEPAD(i) = newbuttonchoice and CONTROLS_NEWGAMEPAD(i) <> oldbuttonchoice Then newbuttonalreadyused=true
			Next
			If newbuttonalreadyused=false Then
				PlaySmartSound(Sound_MenuAccept)
				CONTROLS_NEWGAMEPAD(Menu\ButtonThatWasChanged) = newbuttonchoice
				Menu\ButtonIconChoice=0
				FlushAll()
			Else
				PlaySmartSound(Sound_MenuRefuse)
			EndIf
		EndIf
	EndIf

	EndIf;!!!

	Else;!!!!!

	SetColor(0,255,0)
	DrawRealText("Press the key you want to assign.", GAME_WINDOW_W/2+(BUTTON_PLACE2#-165)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-13*GAME_WINDOW_SCALE#, (Interface_Text_3), 0)
	SetColor(255,255,255)
	If Menu\ButtonBeChangeBy=-999
		SetColor(255,0,0)
		DrawRealText("The key you pressed is already used.", GAME_WINDOW_W/2+(BUTTON_PLACE2#-165)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+7*GAME_WINDOW_SCALE#, (Interface_Text_3), 0)
		SetColor(255,255,255)
	EndIf

	If Menu\ButtonBeChangeBy=0 Or Menu\ButtonBeChangeBy-999 Then
		Select Menu\ControlAssignmentSource
			Case 1:
				For i=1 to 53
					If KeyHit(BUTTONS[i]) Then Menu\ButtonBeChangeBy=BUTTONS[i] : FlushAll()
				Next

				If MouseDown(1) Then Menu\ButtonBeChangeBy=-1 : FlushAll()
				If MouseDown(2) Then Menu\ButtonBeChangeBy=-2 : FlushAll()
				If MouseDown(3) Then Menu\ButtonBeChangeBy=-3 : FlushAll()
			Case 2:
				If JoyType()>0 Then
				g = Input_Gamepad
				For i=1 to 12
					If JoyHit(i, g) Then Menu\ButtonBeChangeBy=i+900+50 : FlushAll()
				Next
	 			If (JoyX#(g)<-Input_GamepadThreshold2#) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_X_MINUS : FlushAll()
	 			If (JoyX#(g)>Input_GamepadThreshold2#)  Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_X_PLUS : FlushAll()
	 			If (JoyY#(g)<-Input_GamepadThreshold2#) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_Y_MINUS : FlushAll()
	 			If (JoyY#(g)>Input_GamepadThreshold2#)  Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_Y_PLUS : FlushAll()
	 			If (JoyZ#(g)<-Input_GamepadThreshold2#) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_Z_MINUS : FlushAll()
	 			If (JoyZ#(g)>Input_GamepadThreshold2#)  Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_Z_PLUS : FlushAll()
				If (Not(JoyHat(g)=-1)) and (JoyHat(g)=0) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_DPAD_UP : FlushAll()
				If (Not(JoyHat(g)=-1)) and (JoyHat(g)=270) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_DPAD_LEFT : FlushAll()
				If (Not(JoyHat(g)=-1)) and (JoyHat(g)=90) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_DPAD_RIGHT : FlushAll()
				If (Not(JoyHat(g)=-1)) and (JoyHat(g)=180) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_DPAD_DOWN : FlushAll()
				Select JoyType(g)
					Case 1: ;digital
						If ((JoyPitch#(g)/180.0)<-Input_GamepadThreshold2#) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_P_MINUS : FlushAll()
						If ((JoyPitch#(g)/180.0)>Input_GamepadThreshold2#)  Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_P_PLUS : FlushAll()
						If ((JoyYaw#(g)/180.0)<-Input_GamepadThreshold2#)   Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_W_MINUS : FlushAll()
						If ((JoyYaw#(g)/180.0)>Input_GamepadThreshold2#)    Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_W_PLUS : FlushAll()
					Case 2: ;analog
						If ((JoyRoll#(g)/180.0)<-Input_GamepadThreshold2#)  Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_R_MINUS : FlushAll()
						If ((JoyRoll#(g)/180.0)>Input_GamepadThreshold2#)   Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_R_PLUS : FlushAll()
						If (JoyZ#(g)<-Input_GamepadThreshold2#) Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_Z_MINUS : FlushAll()
						If (JoyZ#(g)>Input_GamepadThreshold2#)  Then Menu\ButtonBeChangeBy=KEY_GAMEPAD_Z_PLUS : FlushAll()
				End Select
				EndIf
		End Select
	EndIf

	If Menu\ButtonBeChangeBy<>0 and (Not(Menu\ButtonBeChangeBy=-999)) Then
		If (Not(ChannelPlaying(Menu\Channel_Emblem))) Then Menu\Channel_Emblem=PlaySmartSound(Sound_MenuAccept)
		FlushAll()
		CONTROLS(Menu\ControlAssignmentSource,Menu\ButtonToChange)=Menu\ButtonBeChangeBy
		Menu\ButtonWasUsed=0
		For i=0 to 17
			If Menu\ButtonWasUsed=0 and (Not(Menu\ButtonToChange=i)) and CONTROLS(Menu\ControlAssignmentSource,Menu\ButtonToChange)=CONTROLS(Menu\ControlAssignmentSource,i) Then Menu\ButtonWasUsed=-999
		Next
		If Menu\ButtonWasUsed=-999 Then
			Menu\ButtonBeChangeBy=Menu\ButtonWasUsed
		Else
			If Menu\ControlAssignmentSource=2 and Menu\Settings\ControllerLayout#<3 Then
				Menu\ButtonThatWasChanged=Menu\ButtonToChange
				Select CONTROLS_NEWGAMEPAD(Menu\ButtonThatWasChanged)
					Case 1: Menu\ButtonIconChoice=1
					Case 2: Menu\ButtonIconChoice=2
					Case 3: Menu\ButtonIconChoice=3
					Case 4: Menu\ButtonIconChoice=4
					Case 6: Menu\ButtonIconChoice=5
					Case 7: Menu\ButtonIconChoice=6
					Case 8: Menu\ButtonIconChoice=11
					Case 9: Menu\ButtonIconChoice=12
					Case 11: Menu\ButtonIconChoice=13
					Case 12: Menu\ButtonIconChoice=14
					Case 13: Menu\ButtonIconChoice=15
					Case 14: Menu\ButtonIconChoice=16
					Case 16: Menu\ButtonIconChoice=21
					Case 17: Menu\ButtonIconChoice=22
					Case 18: Menu\ButtonIconChoice=23
					Case 19: Menu\ButtonIconChoice=24
					Case 20: Menu\ButtonIconChoice=25
					Case 21: Menu\ButtonIconChoice=26
					Case 22: Menu\ButtonIconChoice=31
					Case 23: Menu\ButtonIconChoice=32
					Case 24: Menu\ButtonIconChoice=33
					Case 25: Menu\ButtonIconChoice=34
					Case 26: Menu\ButtonIconChoice=35
					Case 27: Menu\ButtonIconChoice=36
					Default: Menu\ButtonIconChoice=1
				End Select
			EndIf
			Menu\ButtonToChange=-1
			Menu\ButtonBeChangeBy=0
		EndIf
		Menu\ButtonWasUsed=0
		Menu\ButtonWasChangedTimer=0.1*secs#
	EndIf

	EndIf;!!!!!

End Function

Function Menu_Options_Controls_ReturnNonEmpty()
	For i=0 to 17
		If CONTROLS(Menu\ControlAssignmentSource,i)=KEY_EMPTY Then Return false
	Next
	Return true
End Function

;===============================================================================================================================================================

Function Menu_Options_Reset_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	DrawSmartButton(1, "Options", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#-90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, true)
	DrawSmartButton(2, "Game Progress", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#-90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, true)
	DrawSmartButton(3, "Records", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+110*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, true)
	DrawSmartButton(4, "Chao Garden", GAME_WINDOW_W/2+BUTTON_PLACE2#*GAME_WINDOW_SCALE#+110*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#, true)

	DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-130)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+120)*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Or Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option2
			Case 1: Menu\Option2=2
			Case 2: Menu\Option2=1
			Case 3: Menu\Option2=4
			Case 4: Menu\Option2=3
		End Select
	EndIf

	If Input\Pressed\Left Or Input\Pressed\Right Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option2
			Case 1: Menu\Option2=3
			Case 2: Menu\Option2=4
			Case 3: Menu\Option2=1
			Case 4: Menu\Option2=2
		End Select
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\CloseAfterOptions=True
		Select Menu\Option2
			Case 1: Menu\ResetOptionsAfterOptions=True
			Case 2: Menu\ResetGameAfterOptions=True
			Case 3: Menu\ResetRecordsAfterOptions=True
			Case 4: Menu\ResetGardenAfterOptions=True
		End Select
		Menu\NewMenu2=0
	EndIf

	If Input\Pressed\ActionSkill2 Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\CloseAfterOptions=True
		Menu\ResetOptionsAfterOptions=True
		Menu\ResetGameAfterOptions=True
		Menu\ResetRecordsAfterOptions=True
		Menu\ResetGardenAfterOptions=True
		Menu\NewMenu2=0
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=0
	EndIf

End Function

;===============================================================================================================================================================

Function Menu_Options_Volume_Update()

	Menu\ControlsToShow=Menu\Menu2*100

	DrawRealText(Menu\Warning$, GAME_WINDOW_W/2+(BUTTON_PLACE2#-110)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-70*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE2#+90)*GAME_WINDOW_SCALE#)

	DrawRealText("Master volume:", GAME_WINDOW_W/2+(BUTTON_PLACE2#-125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawRealText("SFX:", GAME_WINDOW_W/2+(BUTTON_PLACE2#-125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawRealText("Voice actors:", GAME_WINDOW_W/2+(BUTTON_PLACE2#-125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+35*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawRealText("Music:", GAME_WINDOW_W/2+(BUTTON_PLACE2#-125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+65*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawRealText("Ambient:", GAME_WINDOW_W/2+(BUTTON_PLACE2#-125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

	Select Menu\Option2
		Case 1: DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#)
		Case 2: DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#)
		Case 3: DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+35*GAME_WINDOW_SCALE#)
		Case 4: DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+65*GAME_WINDOW_SCALE#)
		Case 5: DrawArrow(GAME_WINDOW_W/2+(BUTTON_PLACE2#-140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#)
	End Select

	DrawBetterNumber(Menu\Settings\Volume#*10, GAME_WINDOW_W/2+(BUTTON_PLACE2#+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#, 0, 1)
	DrawBetterNumber(Menu\Settings\VolumeSFX#*10, GAME_WINDOW_W/2+(BUTTON_PLACE2#+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, 0, 1)
	DrawBetterNumber(Menu\Settings\VolumeVA#*10, GAME_WINDOW_W/2+(BUTTON_PLACE2#+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+35*GAME_WINDOW_SCALE#, 0, 1)
	DrawBetterNumber(Menu\Settings\VolumeM#*10, GAME_WINDOW_W/2+(BUTTON_PLACE2#+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+65*GAME_WINDOW_SCALE#, 0, 1)
	DrawBetterNumber(Menu\Settings\VolumeAmb#*10, GAME_WINDOW_W/2+(BUTTON_PLACE2#+125)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#, 0, 1)

	If Input\Pressed\ActionJump Then
		PlaySmartSound(Sound_MenuAccept)
		Select Menu\Option2
			Case 1: If Menu\Settings\Volume#<1 Then Menu\Settings\Volume#=Menu\Settings\Volume#+0.1
			Case 2: If Menu\Settings\VolumeSFX#<1 Then Menu\Settings\VolumeSFX#=Menu\Settings\VolumeSFX#+0.1
			Case 3: If Menu\Settings\VolumeVA#<1 Then Menu\Settings\VolumeVA#=Menu\Settings\VolumeVA#+0.1
			Case 4: If Menu\Settings\VolumeM#<1 Then Menu\Settings\VolumeM#=Menu\Settings\VolumeM#+0.1
			Case 5: If Menu\Settings\VolumeAmb#<1 Then Menu\Settings\VolumeAmb#=Menu\Settings\VolumeAmb#+0.1
		End Select
		Menu\SoundVolumeAfterOptions=True
	EndIf

	If Input\Pressed\ActionSkill2 Then
		PlaySmartSound(Sound_MenuAccept)
		Select Menu\Option2
			Case 1: If Menu\Settings\Volume#>0 Then Menu\Settings\Volume#=Menu\Settings\Volume#-0.1
			Case 2: If Menu\Settings\VolumeSFX#>0 Then Menu\Settings\VolumeSFX#=Menu\Settings\VolumeSFX#-0.1
			Case 3: If Menu\Settings\VolumeVA#>0 Then Menu\Settings\VolumeVA#=Menu\Settings\VolumeVA#-0.1
			Case 4: If Menu\Settings\VolumeM#>0 Then Menu\Settings\VolumeM#=Menu\Settings\VolumeM#-0.1
			Case 5: If Menu\Settings\VolumeAmb#>0 Then Menu\Settings\VolumeAmb#=Menu\Settings\VolumeAmb#-0.1
		End Select
		Menu\SoundVolumeAfterOptions=True
	EndIf

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2+1
		If Menu\Option2>5 Then Menu\Option2=1
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option2=Menu\Option2-1
		If Menu\Option2<1 Then Menu\Option2=5
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

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=0
	EndIf

End Function
