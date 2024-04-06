

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Character(option,option2)
	Return option+(option2-1)*(6*6-1)
End Function

Function Menu_Characters_Change(choose,nosound=false,chosen=false)
	If (Not(nosound)) Then PlaySmartSound(Sound_MenuMove)
	Menu\MeshChange=1

	Select Menu\Menu
		Case MENU_CHARACTERS2#:
			i = Ceil#(Menu\Option/6.0)
			Select choose
				Case -1,1: Menu\Option=Menu\Option+choose
				Case -2,2: Menu\Option=Menu\Option+6*(choose/2)
			End Select
			Select choose
				Case 1: If Menu\Option>i*6 Then
							Menu\Option=(i-1)*6+1
							If chosen then Menu\Option=Menu\Option+6
						EndIf
				Case -1: If Menu\Option<(i-1)*6+1 Then Menu\Option=i*6
				Case 2: If Menu\Option>6*6 Then Menu\Option=Menu\Option-6*6
				Case -2: If Menu\Option<1 Then Menu\Option=Menu\Option+6*6
			End Select
		Default:
			Menu\Option=Menu\Option+choose
			Select choose
				Case 1:
					If Menu\Settings\Mods#=0 Then
						If Menu\Option>CHAR_NONMODPLAYABLECOUNT Then Menu\Option=1
					Else
						If Menu\Option>CHAR_PLAYABLECOUNT Then Menu\Option=1
					EndIf
				Case -1:
					If Menu\Settings\Mods#=0 Then
						If Menu\Option<1 Then Menu\Option=CHAR_NONMODPLAYABLECOUNT
					Else
						If Menu\Option<1 Then Menu\Option=CHAR_PLAYABLECOUNT
					EndIf
			End Select
	End Select
End Function

Function Menu_Characters_Update()

	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If (Menu\Menu=MENU_CHARACTERS2#) and (Menu\Option>=6*6) Then;!!!!!
		SetColor(255,255,255)
	ElseIf UNLOCKEDCHAR[Menu_Character(Menu\Option,Menu\Option2)]=1 Then;!!!!!
		SetColor(Interface_Circle_R[InterfaceChar(Menu_Character(Menu\Option,Menu\Option2))],Interface_Circle_G[InterfaceChar(Menu_Character(Menu\Option,Menu\Option2))],Interface_Circle_B[InterfaceChar(Menu_Character(Menu\Option,Menu\Option2))])
	Else;!!!!!
		SetColor(0,0,0)
	EndIf;!!!!!
	DrawImageEx(INTERFACE(Interface_Circle), GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3+80.5)*GAME_WINDOW_SCALE#)
	SetColor(255,255,255)

	If (Not(Menu\Menu=MENU_CHARACTERS2#)) Or (Not(Menu\Option>=6*6)) Then
		If Menu_Character(Menu\Option,Menu\Option2)>CHAR_NONMODPLAYABLECOUNT and Menu\Settings\Mods#=0 Then
			Menu_PrintLocked(5,Menu_Character(Menu\Option,Menu\Option2),GAME_WINDOW_W/2-220*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-180)*GAME_WINDOW_SCALE#, 1)
		Else
			Menu_CharacterMeshOnScreen_RotateControl(Menu_Character(Menu\Option,Menu\Option2))
			If UNLOCKEDCHAR[Menu_Character(Menu\Option,Menu\Option2)]=1 Then
				If Menu\Menu=MENU_CHARACTERS# Then
					Menu_DrawCharacterNames(Menu_Character(Menu\Option,Menu\Option2), GAME_WINDOW_W/2-(140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+95.5)*GAME_WINDOW_SCALE#, true, false, true)
				Else
					Menu_DrawCharacterNames(Menu_Character(Menu\Option,Menu\Option2), GAME_WINDOW_W/2-(140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+95.5)*GAME_WINDOW_SCALE#, true)
				EndIf
			Else
				Menu_PrintLocked(1,Menu_Character(Menu\Option,Menu\Option2),GAME_WINDOW_W/2-220*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-180)*GAME_WINDOW_SCALE#, 1)
			EndIf
		EndIf
	EndIf

	Select Menu\Menu
		Case MENU_CHARACTERS2#: Menu_Characters2_Update()
		Default: Menu_Characters1_Update()
	End Select

	Select Menu\Menu
		Case MENU_CHARACTERS#:
			If Input\Pressed\Right Then Repeat : Menu_Characters_Change(1) : Until (Not(Menu_WasMemberChosen())) Or Menu\AndAnyone=1
			If Input\Pressed\Left Then Repeat : Menu_Characters_Change(-1) : Until (Not(Menu_WasMemberChosen())) Or Menu\AndAnyone=1
		Default:
			If Input\Pressed\Right Then Menu_Characters_Change(1)
			If Input\Pressed\Left Then Menu_Characters_Change(-1)
	End Select

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		found=True
		If Not(Menu\Menu=MENU_CHARACTERS2#) Then
			If UNLOCKEDCHAR[Menu\Option]=1 Then found=True Else found=False
		Else
			If (Menu\Option>=6*6) Then
				found=true
			Else
				char = Menu_Character(Menu\Option,Menu\Option2)
				If char<=CHAR_NONMODPLAYABLECOUNT or Menu\Settings\Mods#=1 Then
					If UNLOCKEDCHAR[char]=1 Then found=True Else found=False
				Else
					found=False
				EndIf
			EndIf
		EndIf

		If Menu_WasMemberChosen() and Menu\AndAnyone=0 Then found=false

		If found Then
			PlaySmartSound(Sound_MenuAccept)
			Menu\Transition=1
			If Menu\Menu=MENU_CHARACTERS2# and (Menu\Option>=6*6) Then
				Select Menu\MemberToSelect
					Case 1:
						Repeat : Menu\Character[1]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt1(Menu\Character[1])
					Case 2:
						Repeat : Menu\Character[2]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt2(Menu\Character[1],Menu\Character[2])
					Case 3:
						Repeat : Menu\Character[3]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt3(Menu\Character[1],Menu\Character[2],Menu\Character[3])
				End Select
			Else
				Menu\Character[Menu\MemberToSelect]=Menu_Character(Menu\Option,Menu\Option2)
				If Menu\Character[Menu\MemberToSelect]=CHAR_TAI and Menu\CharacterMode[Menu\MemberToSelect]=1 Then Menu\Character[Menu\MemberToSelect]=900+Menu\Character[Menu\MemberToSelect]
			EndIf
			Select Menu\MemberToSelect
				Case Menu\Members:
					Menu\Team=0
					Select Menu\ChaoGarden
						Case 0:
							If Menu\MarathonMode=0 Then
								Menu\NewOption=Menu\SelectedStage : Menu\NewMenu=MENU_STAGE2#
							Else
								Menu_GoToStage()
							EndIf
						Case 1:
							Menu_GoToStage()
						Case 2:
							Menu\NewOption=-1 : Menu\NewMenu=MENU_STAGESPECIAL#
					End Select
				Default:
					Repeat : Menu_Characters_Change(1,true,true) : Until (Not(Menu_WasMemberChosen(1)))
					Menu\NewOption=Menu\Option
					Menu\NewOption2=Menu\Option2
					Menu\MemberToSelect=Menu\MemberToSelect+1
					Menu\NewMenu=Menu\Menu
			End Select
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		If Menu\MarathonMode=0 Then
			If Menu\ChaoGarden=0 Then
				Menu\NewOption=Menu\Members
			ElseIf Menu\ChaoGarden=1 Then
				Menu\NewOption=4
			ElseIf Menu\ChaoGarden=2 Then
				Menu\NewOption=5
			EndIf
		Else
			Menu\NewOption=6
		EndIf
		Menu\NewMenu=MENU_PLAY#
	EndIf

	If Input\Pressed\ActionSkill3 Then
	If Menu_Character(Menu\Option,Menu\Option2)=CHAR_TAI Then
		PlaySmartSound(Sound_MenuBack)
		Menu\CharacterMode[Menu\MemberToSelect]=abs(Menu\CharacterMode[Menu\MemberToSelect]-1)
		Menu\MeshChange=1
	EndIf
	EndIf

End Function

Function Menu_Characters1_Controls(char,x#,y#,ystep#)
	actualchar=char
	If actualchar=CHAR_TAI and Menu\CharacterMode[Menu\MemberToSelect]=1 Then char=CHAR_EGG

	i=-6

	DrawRealText(CONTROLTIPS$(TIP_MOVE), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_MovementGeneral(x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, true)
	If Menu\Members>1 Then
		DrawRealText(CONTROLTIPS$(TIP_CHANGE), x#+62.5*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawSmartKey_Small(INPUT_BUTTON_CHANGE, x#-15*GAME_WINDOW_SCALE#+62.5*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	EndIf
	i=i+1

	DrawRealText(CONTROLTIPS$(TIP_JUMP), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	If UNLOCKEDEMERALDS[7]=1 and Menu\ChaoGarden=0 Then
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONACT, x#+75*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, true)
		DrawImageEx(INTERFACE(Interface_Keys_Small), x#+60*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP,x#+45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		DrawRealText(CONTROLTIPS$(TIP_SUPER), x#+90*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	EndIf
	i=i+1

	If Menu\ChaoGarden<=1 Then
		DrawRealText(CONTROLTIPS$(TIP_JUMPA[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		If TIP_HoldJUMPA[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
		i=i+1

		If Len(CONTROLTIPS$(TIP_JUMPA2[char]))>0 Then
			DrawRealText(CONTROLTIPS$(TIP_JUMPA2[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			If TIP_HoldJUMPA2[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
			i=i+1
		EndIf
	Else
		DrawRealText(CONTROLTIPS$(TIP_JUMPA[CHAR_SON]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		If TIP_HoldJUMPA[CHAR_SON] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
		i=i+1
	EndIf

	DrawRealText(CONTROLTIPS$(TIP_SPINDASH), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONROLL, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)

	If Player_CanLightDash(char) and Menu\ChaoGarden=0 Then
		DrawRealText(CONTROLTIPS$(TIP_LIGHTDASH), x#+90*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONACT, x#-15*GAME_WINDOW_SCALE#+90*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, true)
		DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#+90*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
	EndIf
	i=i+1

	DrawRealText(CONTROLTIPS$(TIP_STOMP), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONROLL, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	i=i+1

	DrawRealText(CONTROLTIPS$(TIP_JUMPHIGHER), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONROLL, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Keys_Small), x#-60*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-75*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	i=i+1

	DrawRealText(CONTROLTIPS$(TIP_DRIFT), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONDRIFT, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)

	DrawSmartKey_Small(INPUT_BUTTON_ACTIONDRIFT, x#+75*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Keys_Small), x#+60*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP,x#+45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
	DrawRealText(CONTROLTIPS$(TIP_BOUNCE), x#+90*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	i=i+1

	DrawRealText(CONTROLTIPS$(TIP_LOOK), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 60)
	DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 56)

	DrawRealText(CONTROLTIPS$(TIP_PICKUP), x#+110*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawSmartKey_Small(INPUT_BUTTON_ACTIONACT, x#-15*GAME_WINDOW_SCALE#+110*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, true)
	i=i+1

	If Menu\ChaoGarden=0 Then

		DrawRealText(CONTROLTIPS$(TIP_SKILL1[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL1, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		If TIP_HoldSKILL1[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
		i=i+1

		DrawRealText(CONTROLTIPS$(TIP_SKILL1AIR[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL1, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
		DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
		If TIP_HoldSKILL1AIR[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
		i=i+1

		Select char
			Case CHAR_TAI,CHAR_CRE:
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL1, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawRealText(CONTROLTIPS$(TIP_SKILL1[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-60*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-75*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				i=i+1
		End Select

		If Len(CONTROLTIPS$(TIP_SKILL2[char]))>0 Then
			If actualchar=CHAR_TAI and Menu\CharacterMode[Menu\MemberToSelect]=1 Then
				DrawRealText(CONTROLTIPS$(TIP_SKILL2X), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			Else
				DrawRealText(CONTROLTIPS$(TIP_SKILL2[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			EndIf
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL2, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			If TIP_HoldSKILL2[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
			i=i+1
		EndIf

		If Len(CONTROLTIPS$(TIP_SKILL2AIR[char]))>0 Then
			If actualchar=CHAR_TAI and Menu\CharacterMode[Menu\MemberToSelect]=1 Then
				DrawRealText(CONTROLTIPS$(TIP_SKILL2X), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			Else
				DrawRealText(CONTROLTIPS$(TIP_SKILL2AIR[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			EndIf
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL2, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			If TIP_HoldSKILL2AIR[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
			i=i+1
		EndIf

		Select char
			Case CHAR_CHA:
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL2, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawRealText(CONTROLTIPS$(TIP_SKILL2[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-60*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-75*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				i=i+1
		End Select

		If Len(CONTROLTIPS$(TIP_SKILL3[char]))>0 Then
			DrawRealText(CONTROLTIPS$(TIP_SKILL3[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL3, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			If TIP_HoldSKILL3[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
			i=i+1
		EndIf

		If Len(CONTROLTIPS$(TIP_SKILL3AIR[char]))>0 Then
			DrawRealText(CONTROLTIPS$(TIP_SKILL3AIR[char]), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL3, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
			DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
			If TIP_HoldSKILL3AIR[char] Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
			i=i+1
		EndIf

		Select char
			Case CHAR_BIG,CHAR_VEC:
				Select char
				Case CHAR_VEC: DrawRealText(CONTROLTIPS$(TIP_HAMMERDOWN), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
				Default: DrawRealText(CONTROLTIPS$(TIP_BELLYFLOP), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
				End Select
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL3, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				i=i+1
			Case CHAR_RAY:
				DrawRealText(CONTROLTIPS$(TIP_FLAP), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL2, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
				i=i+1
		End Select

	Else

		Select char
			Case CHAR_RAY:
				DrawRealText(CONTROLTIPS$(TIP_FLAP), x#, y#+ystep#*i*GAME_WINDOW_SCALE#, (Interface_Text_2))
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONSKILL2, x#-15*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-30*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#, 62)
				DrawSmartKey_Small(INPUT_BUTTON_ACTIONJUMP, x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(Interface_Keys_Small), x#-45*GAME_WINDOW_SCALE#, y#+ystep#*i*GAME_WINDOW_SCALE#-3.5*GAME_WINDOW_SCALE#, 82)
				i=i+1
		End Select

	EndIf
End Function
;; Updates the older character select, accessible by pressing Skill 2.
Function Menu_Characters1_Update()
	If Menu\Option>CHAR_NONMODPLAYABLECOUNT and Menu\Settings\Mods#=0 Then
		found=false
	Else
		If UNLOCKEDCHAR[Menu\Option]=1 Then found=True Else found=False
	EndIf

	If found Then
		If IsCharMod(Menu\Option) Then char=MODCHARS_TYPE(Menu\Option-CHAR_NONMODPLAYABLECOUNT) Else char=Menu\Option
		Menu_Characters1_Controls(char,(GAME_WINDOW_W/2+(BUTTON_PLACE1#-90)*GAME_WINDOW_SCALE#),(GAME_WINDOW_H/2+CONTROLINFO_START#*GAME_WINDOW_SCALE#),(CONTROLINFO_SPACE#))
	Else
		If Menu\Option>CHAR_NONMODPLAYABLECOUNT Then
			If MODCHARS_FOUND(Menu\Option-CHAR_NONMODPLAYABLECOUNT) Then found=True Else found=False
		Else
			found=True
		EndIf
		If found Then Menu_PrintLocked(1,Menu\Option,GAME_WINDOW_W/2+(BUTTON_PLACE1#-155)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2)
	EndIf

	If Input\Pressed\ActionSkill2 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption2=Ceil#(Menu\Option/35.0)
		Menu\NewOption=Menu\Option-35*(Menu\NewOption2-1)
		Menu\NewMenu=MENU_CHARACTERS2#
	EndIf

End Function

Function Menu_Characters2_Roster_Chosen(x#, y#, char, i, j, h)
	If Menu\ButtonState1=0 Then Menu\ButtonSize1#=Menu\ButtonSize1#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#<0 Then Menu\ButtonState1=1 : Menu\ButtonSize1#=0
	If Menu\ButtonState1=1 Then Menu\ButtonSize1#=Menu\ButtonSize1#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#>BUTTON_SCALELIMIT# Then Menu\ButtonState1=0 : Menu\ButtonSize1#=BUTTON_SCALELIMIT#
	Menu\ButtonSize#=Menu\ButtonSize1#
	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	If (Not(Menu\Option>=6*6)) Then SetColor(Interface_Circle_R[InterfaceChar(char)],Interface_Circle_G[InterfaceChar(char)],Interface_Circle_B[InterfaceChar(char)])
	DrawImageEx(INTERFACE(Interface_Characters), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, 0)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetColor(255,255,255)
End Function

Function Menu_Characters2_Roster(x#, y#)
	char=Menu_Character(Menu\Option,Menu\Option2)
	h=0
	For j=1 to 6
	For i=1 to 6
		h=h+1
		If Menu\Option=h Then Menu_Characters2_Roster_Chosen(x#,y#,char,i,j,h)
		thischar=InterfaceChar(Menu_Character(h,Menu\Option2))
		If h<6*6 and thischar<=CHAR_PLAYABLECOUNT Then
			If Menu\AndAnyone=0 Or IsCharMod(thischar) Then
				If (InterfaceChar(Menu\Character[1])=thischar and (Menu\MemberToSelect>1 Or Menu\NewMenu=MENU_STAGE# Or Menu\NewMenu=MENU_STAGE2#)) Or (InterfaceChar(Menu\Character[2])=thischar and (Menu\MemberToSelect>2 Or Menu\NewMenu=MENU_STAGE# Or Menu\NewMenu=MENU_STAGE2#)) Or (InterfaceChar(Menu\Character[3])=thischar and (Menu\NewMenu=MENU_STAGE# Or Menu\NewMenu=MENU_STAGE2#)) Or UNLOCKEDCHAR[thischar]=0 Or (IsCharMod(thischar) and Menu\Settings\Mods#=0) Then SetColor(0,0,0)
			EndIf
			If thischar<=CHAR_NONMODPLAYABLECOUNT Then
				DrawImageEx(INTERFACE(Interface_Characters), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, thischar)
			Else
				If Menu\Settings\Mods#=1 Then
					If MODCHARS_FOUND(thischar-CHAR_NONMODPLAYABLECOUNT) Then found=True Else found=False
				Else
					found=false
				EndIf
				If found
					DrawImageEx(INTERFACE(Interface_CharactersMod[thischar-CHAR_NONMODPLAYABLECOUNT]), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#)
				Else
					DrawImageEx(INTERFACE(Interface_Characters), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, CHAR_NONMODPLAYABLECOUNT+1)
				EndIf
			EndIf
			SetColor(255,255,255)			
		Else
			DrawImageEx(INTERFACE(Interface_Icons), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, 27)
		EndIf
	Next
	Next
End Function
;; Updates the main grid-based character select.
Function Menu_Characters2_Update()
	Menu_Characters2_Roster(GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then Menu_Characters_Change(2)
	If Input\Pressed\Up Then Menu_Characters_Change(-2)

	If Input\Pressed\ActionSkill2 Then
		If (Menu\AndAnyone=1 and (Not(IsCharMod(Menu_Character(Menu\Option,Menu\Option2))))) Or ((Not(Menu_WasMemberChosen())) and (Menu\Settings\Mods#=1 Or (Not(IsCharMod(Menu_Character(Menu\Option,Menu\Option2)))))) Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			If (Menu\Option>=6*6) Then Menu\NewOption=1 Else Menu\NewOption=Menu_Character(Menu\Option,Menu\Option2)
			Menu\NewOption2=1
			Menu\NewMenu=MENU_CHARACTERS#
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf

	If Input\Pressed\ActionDrift Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Option2=Menu\Option2+1
		If Menu\Option2>6 Then Menu\Option2=1
		Menu\MeshChange=1
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Bios_Change(choose)
	PlaySmartSound(Sound_MenuMove)
	If Menu\Option=CHAR_CRE Or Menu\Option=CHAR_VAN Then Menu\MeshChaoEmoActivated=0 : Delete Menu\MeshChaoEmo
	Repeat
		Select choose
			Case 1:
				Menu\Option=Menu\Option+1
				If Menu\Option>CHAR_NORMALCOUNT Then Menu\Option=1
			Case -1:
				Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=CHAR_NORMALCOUNT
		End Select
	Until ((Menu\Settings\Mods#=0 and (Not(IsCharMod(Menu\Option)))) Or (Menu\Settings\Mods#=1))
	Menu\MeshChange=1
	Menu\DontReplayMusic=1
End Function

Function Menu_Bios_Update()

	Menu\Music=6
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\Option<=CHAR_PLAYABLECOUNT Then
		If UNLOCKEDCHAR[Menu\Option]=1 Then found=True Else found=False
	Else
		found=True
	EndIf

	If found Then
		SetColor(Interface_Circle_R[InterfaceChar(Menu\Option)],Interface_Circle_G[InterfaceChar(Menu\Option)],Interface_Circle_B[InterfaceChar(Menu\Option)])
	Else
		SetColor(0,0,0)
	EndIf
	DrawImageEx(INTERFACE(Interface_Circle2), GAME_WINDOW_W/2+140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3+0.5)*GAME_WINDOW_SCALE#)
	SetColor(255,255,255)

	If Not found Then
		Menu_PrintLocked(2,Menu\Option,GAME_WINDOW_W/2+(BUTTON_PLACE1#-85)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+45*GAME_WINDOW_SCALE#)
	EndIf

	Menu_CharacterMeshOnScreen_RotateControl(Menu\Option,found)
	Menu_DrawCharacterNames(Menu\Option, GAME_WINDOW_W/2-(140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+95.5)*GAME_WINDOW_SCALE#, false, true, true, found)

	Menu_UpdateBios(found)
	DrawRealText(Menu\Bio$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-55*GAME_WINDOW_SCALE#, (Interface_Text_1), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#+140)*GAME_WINDOW_SCALE#)

	If Input\Pressed\Right Then Menu_Bios_Change(1)
	If Input\Pressed\Left Then Menu_Bios_Change(-1)

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		If Menu\Option=CHAR_CRE Or Menu\Option=CHAR_VAN Then Menu\MeshChaoEmoActivated=0 : Delete Menu\MeshChaoEmo
		Menu\Transition=1
		Menu\NewOption=2 : Menu\NewMenu=MENU_MAIN#
	EndIf

	If found Then
		If Input\Pressed\ActionSkill2 Then
			Menu\CharacterMeshAnimation=Menu\CharacterMeshAnimation+1
			Select Menu\Option
				Case CHAR_EGG,CHAR_OBT,CHAR_CBT,CHAR_EGN,CHAR_PRS,CHAR_COM,CHAR_MIA: If Menu\CharacterMeshAnimation>4 Then Menu\CharacterMeshAnimation=1
				Case CHAR_VAN: If Menu\CharacterMeshAnimation>3 Then Menu\CharacterMeshAnimation=1
				Default: If Menu\CharacterMeshAnimation>5 Then Menu\CharacterMeshAnimation=1
			End Select
			Menu\MeshChange = 4
		EndIf

		If Input\Pressed\ActionAct Then
			PlaySmartSound(Sound_MenuMove)
			Menu\MeshCharacterSuper=Menu\MeshCharacterSuper+1
			If Menu\MeshCharacterSuper>2 Then Menu\MeshCharacterSuper=0
			Menu\MeshChange=3
		EndIf
	EndIf

End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Stage_DrawMissionIcon(x#, y#, missionno)
	Select StageMission[missionno]
		Case MISSION_NORMAL#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 23)
		Case MISSION_ENEMY#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 17)
		Case MISSION_RING#:		DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 16)
		Case MISSION_HUNT#:		DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 22)
		Case MISSION_GOLD#:		DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 9)
		Case MISSION_STEALTH#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 8)
		Case MISSION_BALLOONS#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 7)
		Case MISSION_FREEROAM#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 3)
		Case MISSION_RIVAL#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 25)
		Case MISSION_CARNIVAL#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 24)
		Case MISSION_BOSS#:		DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 26)
		Case MISSION_FLICKY#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 29)
		Case MISSION_DECLINE#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 33)
		Case MISSION_ESCAPE#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 34)
		Case MISSION_CAPSULE#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 35)
	End Select

	If StageMissionTime[missionno]=1 Then
		DrawImageEx(INTERFACE(Interface_Icons), x#+7.5*GAME_WINDOW_SCALE#, y#+7.5*GAME_WINDOW_SCALE#, 0)
	EndIf

	If StageMissionMach[missionno]=1 Then
		DrawImageEx(INTERFACE(Interface_Icons), x#-7.5*GAME_WINDOW_SCALE#, y#+7.5*GAME_WINDOW_SCALE#, 6)
	EndIf

	If StageMissionPerfect[missionno]=1 Then
		DrawImageEx(INTERFACE(Interface_Icons), x#-7.5*GAME_WINDOW_SCALE#, y#-7.5*GAME_WINDOW_SCALE#, 5)
	EndIf
End Function

Function Menu_Stage_Draw_MissionSelector(x#, y#)
	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	SetColor(Menu_ReturnCardColor(1,Menu\Character[1],True),Menu_ReturnCardColor(2,Menu\Character[1],True),Menu_ReturnCardColor(3,Menu\Character[1],True))
	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 10)
	SetColor(255,255,255)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
End Function

Function Menu_Stage_Update()

	If Menu\LoadThumbnailAndMissions Then
		Menu\LoadThumbnailAndMissions=False
		Select Menu\Menu
			Case MENU_STAGE2#: Menu_Stage_LoadThumbnailAndMissions(Menu\Option+Menu\OptionOrder2*4)
			Default: Menu_Stage_LoadThumbnailAndMissions(Menu\Option)
		End Select
	EndIf

	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	Select Menu\Menu
		Case MENU_STAGE2#: Menu_UpdateStageNames(Menu\Option+Menu\OptionOrder2*4)
		Default: Menu_UpdateStageNames(Menu\Option)
	End Select

	If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
	If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT# Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#
	Menu\ButtonSize#=Menu\ButtonSize2#

	Select Menu\Menu
		Case MENU_STAGE2#: Menu_Stage2_Update()
		Default: Menu_Stage1_Update()
	End Select

	If Input\Pressed\ActionSkill2 Then
		Select Menu\Menu
			Case MENU_STAGE2#:
				If (Menu\Option+Menu\OptionOrder2*4)<=StageAmount Then
					PlaySmartSound(Sound_MenuBack)
					Menu\Transition=1
					Menu\NewMenu=MENU_STAGE#
					Menu\NewOption=Menu\Option+Menu\OptionOrder2*4
				Else
					PlaySmartSound(Sound_MenuRefuse)
				EndIf
			Default:
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewMenu=MENU_STAGE2#
				Menu\NewOption=Menu\Option
		End Select
	EndIf

	If Input\Pressed\Right and (Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=1) Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MissionNo=Menu\MissionNo+1
		If Menu\MissionNo>5 Then Menu\MissionNo=1
	EndIf

	If Input\Pressed\Left and (Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=1) Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MissionNo=Menu\MissionNo-1
		If Menu\MissionNo<1 Then Menu\MissionNo=5
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		If Menu\Menu=MENU_STAGE# Or ((Menu\Option+4*Menu\OptionOrder2)<=StageAmount) Then
			PlaySmartSound(Sound_MenuAccept)
			If Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=1 Then
				Menu\Transition=1
				Menu_GoToStage()
			Else
				Menu\OptionOrder=1
			EndIf
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		If Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=0 Then
			Menu\Transition=1
			Menu\NewOption=Menu\Members : Menu\NewMenu=MENU_PLAY#
		Else
			Menu\OptionOrder=0
		EndIf
	EndIf

	If Input\Pressed\ActionDrift Then Menu_Stage_RandomizeTeam()
End Function

Function Menu_Stage_RandomizeTeam(nosound=false)
	If Not nosound Then PlaySmartSound(Sound_CharacterChange)
	Repeat : Menu\Character[1]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt1(Menu\Character[1])
	Repeat : Menu\Character[2]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt2(Menu\Character[1],Menu\Character[2])
	Repeat : Menu\Character[3]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt3(Menu\Character[1],Menu\Character[2],Menu\Character[3])
	Menu\Team=0
End Function

Function Menu_Stage_DrawThumbnailAndMissions(x#, y#, compressy#=0)
	Select Menu\Menu
		Case MENU_STAGE2#: i=Menu\Option+Menu\OptionOrder2*4
		Default: i=Menu\Option
	End Select

	If i<=StageAmount Then
		DrawRealText("Mission:", x#, y#-compressy#, (Interface_TextControls_1), 1)
		Menu_Stage_Draw_MissionSelector(x#+(-3+Menu\MissionNo)*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#)
		Menu_Stage_DrawMissionIcon(x#-2*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 1)
		Menu_Stage_DrawMissionIcon(x#-1*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 2)
		Menu_Stage_DrawMissionIcon(x#+0*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 3)
		Menu_Stage_DrawMissionIcon(x#+1*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 4)
		Menu_Stage_DrawMissionIcon(x#+2*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 5)
		If Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=1 Then
			DrawImageEx(INTERFACE(Interface_Icons), x#-3*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 19)
			DrawImageEx(INTERFACE(Interface_Icons), x#+3*30*GAME_WINDOW_SCALE#, y#-compressy#*2+30*GAME_WINDOW_SCALE#, 18)
		EndIf
	EndIf

	DrawImageEx(INTERFACE(Interface_Thumbnail), x#, y#-110*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Square), x#, y#-110*GAME_WINDOW_SCALE#)
End Function

Function Menu_Stage2_Update()

	For i=1 to 4
	For j=1 to 3
		stageno=i+(j-1)*4
		If Menu\Option=stageno Then
			SetScale(GAME_WINDOW_SCALE#*0.325+Menu\ButtonSize#*0.4, GAME_WINDOW_SCALE#*0.325+Menu\ButtonSize#*0.4)
			SetColor(255,255,255)
		Else
			SetScale(GAME_WINDOW_SCALE#*0.325, GAME_WINDOW_SCALE#*0.325)
			SetColor(100,100,100)
		EndIf
		DrawImageEx(INTERFACE(Interface_StageSelectThumbnails[stageno]), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(Interface_Square), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(255,255,255)
		If Menu\OptionOrder=0 Then
			If j=1 and (Menu\Option)=i Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#-45*GAME_WINDOW_SCALE#,20)
			If j=3 and (Menu\Option-8)=i Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#+45*GAME_WINDOW_SCALE#,21)
		EndIf
	Next
	Next

	Menu_Stage_DrawThumbnailAndMissions(GAME_WINDOW_W/2+170*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3+105.5)*GAME_WINDOW_SCALE#, 5.5*GAME_WINDOW_SCALE#)

	If Menu\Transition=0 Then Menu_Stage_DrawRecords_Emblems(500*GAME_WINDOW_SCALE#,-34.25*GAME_WINDOW_SCALE#)

	If (Menu\Option+Menu\OptionOrder2*4)<=StageAmount Then
		If Len(Menu\StageName$)>12 Then
		DrawRealText(Menu\StageName$, GAME_WINDOW_W/2+280*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+113.75)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 63, 63, 63)
		Else
		DrawRealText(Menu\StageName$, GAME_WINDOW_W/2+170*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+113.75)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
		EndIf
	EndIf

	If Menu\OptionOrder=0 Then
		If Input\Pressed\Right Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\Option
				Case 1: Menu\Option=2
				Case 2: Menu\Option=3
				Case 3: Menu\Option=4
				Case 4: Menu\Option=1
				Case 5: Menu\Option=6
				Case 6: Menu\Option=7
				Case 7: Menu\Option=8
				Case 8: Menu\Option=5
				Case 9: Menu\Option=10
				Case 10: Menu\Option=11
				Case 11: Menu\Option=12
				Case 12: Menu\Option=9
			End Select
			Menu\LoadThumbnailAndMissions=True
		EndIf

		If Input\Pressed\Left Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\Option
				Case 1: Menu\Option=4
				Case 2: Menu\Option=1
				Case 3: Menu\Option=2
				Case 4: Menu\Option=3
				Case 5: Menu\Option=8
				Case 6: Menu\Option=5
				Case 7: Menu\Option=6
				Case 8: Menu\Option=7
				Case 9: Menu\Option=12
				Case 10: Menu\Option=9
				Case 11: Menu\Option=10
				Case 12: Menu\Option=11
			End Select
			Menu\LoadThumbnailAndMissions=True
		EndIf

		If Input\Pressed\Down Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\Option
				Case 1: Menu\Option=5
				Case 2: Menu\Option=6
				Case 3: Menu\Option=7
				Case 4: Menu\Option=8
				Case 5: Menu\Option=9
				Case 6: Menu\Option=10
				Case 7: Menu\Option=11
				Case 8: Menu\Option=12
				Case 9,10,11,12:
					If StageAmount>12 Then
						If (12+Menu\OptionOrder2*4)<StageAmount Then
							Menu\OptionOrder2=Menu\OptionOrder2+1
						Else
							Menu\OptionOrder2=0
							Menu\Option=Menu\Option-8
						EndIf
						Menu_Stage_LoadStageSelectThumbnails()
					Else
						Select Menu\Option
							Case 9: Menu\Option=1
							Case 10: Menu\Option=2
							Case 11: Menu\Option=3
							Case 12: Menu\Option=4
						End Select
					EndIf
			End Select
			Menu\LoadThumbnailAndMissions=True
		EndIf

		If Input\Pressed\Up Then
			PlaySmartSound(Sound_MenuMove)
			Select Menu\Option
				Case 5: Menu\Option=1
				Case 6: Menu\Option=2
				Case 7: Menu\Option=3
				Case 8: Menu\Option=4
				Case 9: Menu\Option=5
				Case 10: Menu\Option=6
				Case 11: Menu\Option=7
				Case 12: Menu\Option=8
				Case 1,2,3,4:
					If StageAmount>12 Then
						If Not(Menu\Option<=4 and Menu\OptionOrder2=0) Then
							Menu\OptionOrder2=Menu\OptionOrder2-1
						Else
							Menu\OptionOrder2=Ceil#(StageAmount/4.0)-3
							Menu\Option=Menu\Option+8
						EndIf
						Menu_Stage_LoadStageSelectThumbnails()
					Else
						Select Menu\Option
							Case 1: Menu\Option=9
							Case 2: Menu\Option=10
							Case 3: Menu\Option=11
							Case 4: Menu\Option=12
						End Select
					EndIf
			End Select
			Menu\LoadThumbnailAndMissions=True
		EndIf

		If Input\Pressed\ActionSkill3 Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option=Rand(1,12)
			Menu\OptionOrder2=Rand(0,Ceil#(StageAmount/4.0)-3)
			Menu\MissionNo=Rand(1,5)
			Menu_Stage_LoadStageSelectThumbnails()
			Menu\LoadThumbnailAndMissions=True
		EndIf
	EndIf

End Function

Function Menu_Stage_DrawRecords(specialstage#=false,x#=0,y#=0)
	DrawRealText("RECORDS", x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-156*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-116.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, (Interface_TextRecords_2), 0) 
	DrawImageEx(INTERFACE(Interface_Results), x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-85.0*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 2)
	DrawImageEx(INTERFACE(Interface_Results), x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-40.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 3)
	DrawImageEx(INTERFACE(Interface_Results), x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2+6.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 4)
	DrawImageEx(INTERFACE(Interface_Results), x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2+52.0*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 5)
	DrawRealText("RINGS", x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-161*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-86.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
	DrawRealText("ENEMIES", x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-161*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-63*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
	DrawRealText("TIME", x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-161*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
	DrawRealText("SCORE", x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-161*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
	DrawRealText("RANK", x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-82*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2+52.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, (Interface_TextControls_2))

	DrawBetterNumber(Menu_Stage_ReturnRecordValue(1,Menu\MissionNo,Menu\Option), x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(1,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-86.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 0, 1)
	DrawBetterNumber(Menu_Stage_ReturnRecordValue(2,Menu\MissionNo,Menu\Option), x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(2,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-63*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 0, 1)
		DrawImageEx(INTERFACE(Interface_Numbers), x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(3,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+64*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 10)
		DrawImageEx(INTERFACE(Interface_Numbers), x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(3,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+114*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 10)
		DrawNumber((Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option)/60000), x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(3,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+28*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 2)
		DrawNumber((Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option)/1000) Mod 60, x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(3,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+78*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 2)
		DrawNumber((Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option)/10) Mod 60, x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(3,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+128*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 2)
	DrawBetterNumber(Menu_Stage_ReturnRecordValue(4,Menu\MissionNo,Menu\Option), x#+(((7*GAME_WINDOW_SCALE#)))-25*GAME_WINDOW_SCALE#*Menu_Stage_RecordZeroCharacterTester(4,Menu\MissionNo,Menu\Option)+GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2+8.5*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, 0, 1)
	Rank_Draw(Menu_Stage_ReturnRecordValue(5,Menu\MissionNo,Menu\Option), x#+(((7*GAME_WINDOW_SCALE#)))+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#+36.295*GAME_WINDOW_SCALE#, y#+GAME_WINDOW_H/2+52.0*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#)
	;--------------------------------------
	For r=1 to 3
		If Not(Menu_Stage_ReturnRecordValue(1,Menu\MissionNo,Menu\Option,r)=0) Then
			SetColor(Interface_Lives_R[InterfaceChar(Menu_Stage_ReturnRecordValue(1,Menu\MissionNo,Menu\Option,r))],Interface_Lives_G[InterfaceChar(Menu_Stage_ReturnRecordValue(1,Menu\MissionNo,Menu\Option,r))],Interface_Lives_B[InterfaceChar(Menu_Stage_ReturnRecordValue(1,Menu\MissionNo,Menu\Option,r))])
			Interface_DrawHead(x#+-25*GAME_WINDOW_SCALE#*(r-1)+(((7*GAME_WINDOW_SCALE#)))+150*GAME_WINDOW_SCALE#+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+11*GAME_WINDOW_SCALE#+GAME_WINDOW_H/2-96.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, Menu_Stage_ReturnRecordValue(1,Menu\MissionNo,Menu\Option,r)-1)
		EndIf
	Next
	For r=1 to 3
		If Not(Menu_Stage_ReturnRecordValue(2,Menu\MissionNo,Menu\Option,r)=0) Then
			SetColor(Interface_Lives_R[InterfaceChar(Menu_Stage_ReturnRecordValue(2,Menu\MissionNo,Menu\Option,r))],Interface_Lives_G[InterfaceChar(Menu_Stage_ReturnRecordValue(2,Menu\MissionNo,Menu\Option,r))],Interface_Lives_B[InterfaceChar(Menu_Stage_ReturnRecordValue(2,Menu\MissionNo,Menu\Option,r))])
			Interface_DrawHead(x#+-25*GAME_WINDOW_SCALE#*(r-1)+(((7*GAME_WINDOW_SCALE#)))+150*GAME_WINDOW_SCALE#+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+11*GAME_WINDOW_SCALE#+GAME_WINDOW_H/2-73*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, Menu_Stage_ReturnRecordValue(2,Menu\MissionNo,Menu\Option,r)-1)
		EndIf
	Next
	For r=1 to 3
		If Not(Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option,r)=0) Then
			SetColor(Interface_Lives_R[InterfaceChar(Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option,r))],Interface_Lives_G[InterfaceChar(Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option,r))],Interface_Lives_B[InterfaceChar(Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option,r))])
			Interface_DrawHead(x#+-25*GAME_WINDOW_SCALE#*(r-1)+(((7*GAME_WINDOW_SCALE#)))+150*GAME_WINDOW_SCALE#+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+11*GAME_WINDOW_SCALE#+GAME_WINDOW_H/2-49.5*GAME_WINDOW_SCALE#+25*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, Menu_Stage_ReturnRecordValue(3,Menu\MissionNo,Menu\Option,r)-1)
		EndIf
	Next
	For r=1 to 3
		If Not(Menu_Stage_ReturnRecordValue(4,Menu\MissionNo,Menu\Option,r)=0) Then
			SetColor(Interface_Lives_R[InterfaceChar(Menu_Stage_ReturnRecordValue(4,Menu\MissionNo,Menu\Option,r))],Interface_Lives_G[InterfaceChar(Menu_Stage_ReturnRecordValue(4,Menu\MissionNo,Menu\Option,r))],Interface_Lives_B[InterfaceChar(Menu_Stage_ReturnRecordValue(4,Menu\MissionNo,Menu\Option,r))])
			Interface_DrawHead(x#+-25*GAME_WINDOW_SCALE#*(r-1)+(((7*GAME_WINDOW_SCALE#)))+150*GAME_WINDOW_SCALE#+GAME_WINDOW_W/2-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+11*GAME_WINDOW_SCALE#+GAME_WINDOW_H/2-1.5*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, Menu_Stage_ReturnRecordValue(4,Menu\MissionNo,Menu\Option,r)-1)
		EndIf
	Next
	For r=1 to 3
		If Not(Menu_Stage_ReturnRecordValue(5,Menu\MissionNo,Menu\Option,r)=0) Then
			SetColor(Interface_Lives_R[InterfaceChar(Menu_Stage_ReturnRecordValue(5,Menu\MissionNo,Menu\Option,r))],Interface_Lives_G[InterfaceChar(Menu_Stage_ReturnRecordValue(5,Menu\MissionNo,Menu\Option,r))],Interface_Lives_B[InterfaceChar(Menu_Stage_ReturnRecordValue(5,Menu\MissionNo,Menu\Option,r))])
			Interface_DrawHead(x#+25*GAME_WINDOW_SCALE#*(r-1)+(((7*GAME_WINDOW_SCALE#)))+72*GAME_WINDOW_SCALE#+(GAME_WINDOW_W/2)+12*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#, y#+48*GAME_WINDOW_SCALE#+(GAME_WINDOW_H/2)+27*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#, Menu_Stage_ReturnRecordValue(5,Menu\MissionNo,Menu\Option,r)-1)
		EndIf
	Next
	SetColor(255,255,255)

	If Not specialstage# Then Menu_Stage_DrawRecords_Emblems(x#,y#)
End Function

Function Menu_Stage_DrawRecords_Emblems(x#=0,y#=0)
	Select Menu\Menu
		Case MENU_STAGE2#: stageno=(Menu\Option+Menu\OptionOrder2*4)
		Default: stageno=(Menu\Option)
	End Select

	If stageno<=StageAmount Then
		If EMBLEMS1(Menu\MissionNo,stageno)=0 then
			SetAlpha(0.75)
			SetColor(7.5,7.5,7.5)
		Else
			SetAlpha(1)
			SetColor(255,255,255)
		EndIf
		DrawImageEx(INTERFACE(Interface_Icons), x#+(((7*GAME_WINDOW_SCALE#)))+(GAME_WINDOW_W/2)+12*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-160*GAME_WINDOW_SCALE#, y#+(GAME_WINDOW_H/2)+27*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#+35*GAME_WINDOW_SCALE#, 4)
		If EMBLEMS2(Menu\MissionNo,stageno)=0 then
			SetAlpha(0.75)
			SetColor(7.5,7.5,7.5)
		Else
			SetAlpha(1)
			SetColor(255,255,255)
		EndIf
		DrawImageEx(INTERFACE(Interface_Icons), x#+(((7*GAME_WINDOW_SCALE#)))+(GAME_WINDOW_W/2)+12*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-120*GAME_WINDOW_SCALE#, y#+(GAME_WINDOW_H/2)+27*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#+35*GAME_WINDOW_SCALE#, 4)
		SetAlpha(1)
		SetColor(255,255,255)
	EndIf
End Function

Function Menu_Stage1_Update()

	Menu_Stage_DrawThumbnailAndMissions(GAME_WINDOW_W/2+170*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3+80.5)*GAME_WINDOW_SCALE#)

	If Menu\Option<=StageAmount Then
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-65*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+120.5)*GAME_WINDOW_SCALE#,20)
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-65*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+70.5)*GAME_WINDOW_SCALE#,21)
		If Len(Menu\StageName$)>12 Then
		DrawRealText(Menu\StageName$, GAME_WINDOW_W/2+45*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+90.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 63, 63, 63)
		Else
		DrawRealText(Menu\StageName$, GAME_WINDOW_W/2-65*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+90.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
		EndIf
	EndIf

	Menu_Stage_DrawRecords()

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>StageAmount Then Menu\Option=1
		Menu\LoadThumbnailAndMissions=True
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=StageAmount
		Menu\LoadThumbnailAndMissions=True
	EndIf
End Function

Function Menu_StageSpecial_Update()

	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu
	
	For i=1 to 7
		j=False
		If i>1 Then
			If UNLOCKEDEMERALDS[i-1]=0 Then j=True
		EndIf
		DrawSmartButton(-i, "Special Stage "+Int(i), GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#-10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2.0-105*GAME_WINDOW_SCALE#+(i-1)*35*GAME_WINDOW_SCALE#, False, True, j)
	Next

	Menu_Stage_DrawRecords(true,30*GAME_WINDOW_SCALE#,-20*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then
		j=Menu\Option
		Repeat
			Menu\Option=Menu\Option-1
			If Menu\Option<-7 Then Menu\Option=-1
		Until abs(Menu\Option)=1 Or UNLOCKEDEMERALDS[abs(Menu\Option)-1]=1
		IF j<>Menu\Option Then PlaySmartSound(Sound_MenuMove)
	EndIf

	If Input\Pressed\Up Then
		j=Menu\Option
		Repeat
			Menu\Option=Menu\Option+1
			If Menu\Option>-1 Then Menu\Option=-7
		Until abs(Menu\Option)=1 Or UNLOCKEDEMERALDS[abs(Menu\Option)-1]=1
		IF j<>Menu\Option Then PlaySmartSound(Sound_MenuMove)
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\ChaoGarden=0
		Menu\MissionNo=1
		Menu_GoToStage()
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\ChaoGarden=0
		Menu\NewOption=5
		Menu\NewMenu=MENU_PLAY#
	EndIf
End Function

Function Menu_Stage_RecordZeroCharacterTester(value, h, i)
	If RECORDS_CURRENT<>i Then LoadGame_Records(i)
	Select value
		Case 1: Return RECORDS_RINGS(h,4)
		Case 2: Return RECORDS_ENEMIES(h,4)
		Case 3: Return RECORDS_TIME(h,4)
		Case 4: Return RECORDS_SCORE(h,4)
		Case 5: Return RECORDS_RANK(h,4)
	End Select
End Function

Function Menu_Stage_ReturnRecordValue(value, h, i, r=0)
	If RECORDS_CURRENT<>i Then LoadGame_Records(i)
	Select value
		Case 1: Return RECORDS_RINGS(h,r)
		Case 2: Return RECORDS_ENEMIES(h,r)
		Case 3: Return RECORDS_TIME(h,r)
		Case 4: Return RECORDS_SCORE(h,r)
		Case 5: Return RECORDS_RANK(h,r)
	End Select
End Function

Function Menu_Stage_LoadMissions(stageno, loadideals=false)
	For i=1 to 5
		StageMission[i]=0
		StageMissionTime[i]=0
		StageMissionMach[i]=0
		StageMissionPerfect[i]=0
	Next
	If loadideals Then
		Game\LimitTime=0
		Game\IdealTime=0
		Game\IdealScore=0
	EndIf

	If (Not(stageno>StageAmount Or stageno<0)) Then
	If (fileType(StagePath$(stageno)+"/Media/Missions.xml")=1) Then
		xmlin = xmlLoad(StagePath$(stageno)+"/Media/Missions.xml")

		For i = 1 To xmlNodeChildCount(xmlin)

		child = xmlNodeChild(xmlin, i)

		Select xmlNodeNameGet$(child)
			Case "mission1","mission2","mission3","mission4","mission5":
				j = Int(Mid$(xmlNodeNameGet$(child),8,1))
				StageMission[j] = xmlNodeAttributeValueGet(child, "is")
				StageMissionTime[j] = xmlNodeAttributeValueGet(child, "time")
				StageMissionMach[j] = xmlNodeAttributeValueGet(child, "mach")
				StageMissionPerfect[j] = xmlNodeAttributeValueGet(child, "perfect")
			Case "time":
				If loadideals Then
					Game\LimitTime = xmlNodeAttributeValueGet(child, "max")
					Game\IdealTime = xmlNodeAttributeValueGet(child, "ideal")
				EndIf
		End Select

		Next

		xmlNodeDelete(xmlin)
	EndIf
	EndIf

	For i=1 to 5
		If StageMission[i]>MISSIONCOUNT# Then StageMission[i]=0
	Next
End Function

Function Menu_Stage_LoadThumbnail(stageno)
	If INTERFACE_EXISTS(Interface_Thumbnail) Then FreeSmartImage(Interface_Thumbnail)
	If stageno>StageAmount Then
		LoadSmartFastImage("Interface/NoThumbnail2.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, false, false, true)
	ElseIf Not(FileType(StagePath$(stageno)+"/Media/thumbnail.png")=1) Then
		LoadSmartFastImage("Interface/NoThumbnail.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, false, false, true)
	Else
		LoadSmartFastImage(StagePath$(stageno)+"/Media/thumbnail.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, false, false, true)
	EndIf
End Function

Function Menu_Stage_LoadThumbnailAndMissions(stageno)
	Menu_Stage_LoadThumbnail(stageno)
	Menu_Stage_LoadMissions(stageno)
End Function

Function Menu_Stage_LoadStageSelectThumbnails()
For i=1 to 12
	If INTERFACE_EXISTS(Interface_StageSelectThumbnails[i]) Then FreeSmartImage(Interface_StageSelectThumbnails[i])
	stageno = i+4*Menu\OptionOrder2
	If stageno>StageAmount Then
		LoadSmartFastImage("Interface/NoThumbnail2.png", Interface_StageSelectThumbnails[i], 400, 308, 0, 1, 2.03695, 2.03695, false, false, true)
	ElseIf Not(FileType(StagePath$(stageno)+"/Media/thumbnail.png")=1) Then
		LoadSmartFastImage("Interface/NoThumbnail.png", Interface_StageSelectThumbnails[i], 400, 308, 0, 1, 2.03695, 2.03695, false, false, true)
	Else
		LoadSmartFastImage(StagePath$(stageno)+"/Media/thumbnail.png", Interface_StageSelectThumbnails[i], 400, 308, 0, 1, 2.03695, 2.03695, false, false, true)
	EndIf
Next
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------------------------

Function Menu_RandomNonmodChar()
	i = Rand(0,CHAR_NONMODPLAYABLECOUNT)
	Select i
		Case 0: Return CHAR_TMH
		Default: Return i
	End Select
End Function

Function Menu_RandomNonmodChar_NotAcceptableTails(charA,charB)
	If (charA=CHAR_TMH and charB=CHAR_TAI) Or (charB=CHAR_TMH and charA=CHAR_TAI) Then Return True
	Return False
End Function

Function Menu_RandomNonmodChar_AcceptableAt1(char1,isplayer=true)
	If isplayer and (Not UNLOCKEDCHAR[char1]=1) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_AcceptableAt2(char1,char2,isplayer=true)
	If isplayer and (Not UNLOCKEDCHAR[char2]=1) Then Return False
	If char2=char1 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char1,char2) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_AcceptableAt3(char1,char2,char3,isplayer=true)
	If isplayer and (Not UNLOCKEDCHAR[char3]=1) Then Return False
	If char3=char1 Or char3=char2 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char1,char3) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char2,char3) Then Return False
	Return True
End Function

Function Menu_RandomNonmodChar_RivalAcceptableAt1(char,char1)
	If char=char1 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char1) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_RivalAcceptableAt2(char,char1,char2)
	If char=char1 Or char=char2 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char1) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char2) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_RivalAcceptableAt3(char,char1,char2,char3)
	If char=char1 Or char=char2 Or char=char3 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char1) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char2) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char3) Then Return False
	Return True
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------------------------