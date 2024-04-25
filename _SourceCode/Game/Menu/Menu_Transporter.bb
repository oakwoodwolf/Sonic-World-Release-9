Function DrawSmartButtonT(buttonno, text$, x#, y#, color, inactive=false, forcecolor=0, small=false)
	option=Menu\Option
	If option=buttonno Then
		If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
		If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT# Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#
		Menu\ButtonSize#=Menu\ButtonSize2#
	EndIf

	Select small
		Case false: buttontype=Interface_ButtonsT : buttontypesize=1 : buttontypetext1=Interface_TextButtons2_1 : buttontypetext2=Interface_TextButtons_1
		Case true: buttontype=Interface_ButtonsT_2 : buttontypesize=0.75 : buttontypetext1=Interface_TextButtons2_2 : buttontypetext2=Interface_TextButtons_2
	End Select

	If inactive Then
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(buttontype), x#, y#, 6)
		DrawRealText(text$, x#, y#+2.5*buttontypesize, (buttontypetext2), 1)
	Else
		If forcecolor=0 Then
			If option=buttonno Then
				SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
				DrawImageEx(INTERFACE(buttontype), x#, y#, 0)
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
				DrawRealText(text$, x#, y#+2.5*buttontypesize, (buttontypetext1), 1)
			Else
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(buttontype), x#, y#, color)
				DrawRealText(text$, x#, y#+2.5*buttontypesize, (buttontypetext2), 1)
			EndIf
		Else
			If option=buttonno Then
				SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
				DrawImageEx(INTERFACE(buttontype), x#, y#, forcecolor)
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
				DrawRealText(text$, x#, y#+2.5*buttontypesize, (buttontypetext1), 1)
			Else
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
				DrawImageEx(INTERFACE(buttontype), x#, y#, forcecolor)
				DrawRealText(text$, x#, y#+2.5*buttontypesize, (buttontypetext2), 1)
			EndIf
		EndIf
	EndIf
End Function

Function Menu_Transporter_Background(d.tDeltaTime)

	If Menu\PreviousBackground2<>Menu\Background2 Then
		Menu\Background2Changed=1
		Menu\BackgroundFader#=0
		Menu\RealPreviousBackground2=Menu\PreviousBackground2
		Menu\PreviousBackground2=Menu\Background2
	EndIf

	If Menu\Background2Changed=1 Then
		If Menu\BackgroundFader#<1 Then
			Menu\BackgroundFader#=Menu\BackgroundFader#+0.0167*d\Delta
		Else
			Menu\Background2Changed=0
		EndIf
		SetAlpha(1-Menu\BackgroundFader#)
		DrawImageEx(INTERFACE(Interface_Transporter1+(Menu\RealPreviousBackground2-1)), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
		SetAlpha(Menu\BackgroundFader#)
	Else
		SetAlpha(1)
	EndIf
	DrawImageEx(INTERFACE(Interface_Transporter1+(Menu\Background2-1)), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
	SetAlpha(1)

End Function

Function Menu_Transporter_Update()

	Select Menu\Menu2
		Case MENU_TRANSPORTER_GOODBYE#: Menu\Music=8
		Default: Menu\Music=7
	End Select
	If Menu\WentToChaoMenu=0 Then Menu\Background=3 Else Menu\Background=4
	If Menu\Background2=5 Then Menu\Background=4
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\WentToChaoMenu=1 Then;!

	Select Menu\Menu2
		Case MENU_TRANSPORTER_MAIN#: Menu_Transporter_Main()
		Case MENU_TRANSPORTER_NAME#: Menu_Transporter_Name()
		Case MENU_TRANSPORTER_GOODBYE#: Menu_Transporter_GoodBye()
		Case MENU_TRANSPORTER_BUDDY#: Menu_Transporter_Buddy()
		Case MENU_TRANSPORTER_INVENTORY#: Menu_Transporter_Inventory()
		Case MENU_TRANSPORTER_EXIT#: Menu_Transporter_Exit()
		Case MENU_TRANSPORTER_STADIUM#: Menu_Transporter_Stadium()
		Case MENU_TRANSPORTER_RACEEXIT#: Menu_Transporter_RaceExit()
		Case MENU_TRANSPORTER_RACES#: Menu_Transporter_Races()
		Case MENU_TRANSPORTER_KARATEEXIT#: Menu_Transporter_KarateExit()
		Case MENU_TRANSPORTER_DIFFICULTY#: Menu_Transporter_Difficulty()
	End Select

	EndIf;!

	If Menu\ChaoMenuTimer>0 Then Menu\ChaoMenuTimer=Menu\ChaoMenuTimer-timervalue#

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Main()

	Menu\Background2=1

	If Menu\HeldChaoNumber>0 Then
		DrawSmartButtonT(1, "Name", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-95*GAME_WINDOW_SCALE#, 1)
		DrawSmartButtonT(2, "Good-bye", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, 1)
		DrawSmartButtonT(3, "Select Buddy", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+05*GAME_WINDOW_SCALE#, 1)
	Else
		DrawSmartButtonT(1, "Name", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-95*GAME_WINDOW_SCALE#, 1, true)
		DrawSmartButtonT(2, "Good-bye", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, 1, true)
		DrawSmartButtonT(3, "Select Buddy", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-45*GAME_WINDOW_SCALE#, 1, true)
	EndIf
	DrawSmartButtonT(4, "Inventory", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+45*GAME_WINDOW_SCALE#, 1)
	DrawSmartButtonT(5, "Exit", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#, 1)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\HeldChaoNumber>0 Then
			If Menu\Option>5 Then Menu\Option=1
		Else
			If Menu\Option>5 Then Menu\Option=4
		EndIf
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\HeldChaoNumber>0 Then
			If Menu\Option<1 Then Menu\Option=5
		Else
			If Menu\Option<4 Then Menu\Option=5
		EndIf
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\NewMenu2=Menu\Option
		Menu\NewOption=1
		Select Menu\Option
			Case 1: Menu\NewChaoName$=Menu\HeldChaoName$
			Case 2: Menu\OptionOrder2=1
			Case 3: Menu\OptionOrder2=1
			Case 4: Menu\OptionOrder=0 : Menu\CurrentItem=0
			Case 5: Menu\Background=3
		End Select
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Inventory()

	Menu\Background2=4

	DrawRealText("Choose items to take out with you.", GAME_WINDOW_W/2-(BUTTON_PLACE1#+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-110*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2-(BUTTON_PLACE1#+100)*GAME_WINDOW_SCALE#+275*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down and TOTALITEMS>1 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>Menu_Transporter_Inventory_Amount(TOTALITEMS) Then Menu\Option=Menu_Transporter_Inventory_Amount(TOTALITEMS) : Menu\OptionOrder=Menu\OptionOrder+1
		If Menu\OptionOrder>TOTALITEMS-1 Then Menu\OptionOrder=0
	EndIf

	If Input\Pressed\Up and TOTALITEMS>1 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=1 : Menu\OptionOrder=Menu\OptionOrder-1
		If Menu\OptionOrder<0 Then Menu\OptionOrder=TOTALITEMS-1
	EndIf

	For i=1 to Menu_Transporter_Inventory_Amount(TOTALITEMS)
		Menu_Transporter_InventoryItem(i)
	Next
	If TOTALITEMS>1 Then
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(135)*GAME_WINDOW_SCALE#,20)
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(65-50*(Menu_Transporter_Inventory_Amount(TOTALITEMS)-1))*GAME_WINDOW_SCALE#,21)
	EndIf

	For ii.tItem=Each tItem
		If ii\ID=Menu\NewCurrentItem Then Menu\BlackMarketBuyCategory=ii\Type1 : Menu\BlackMarketSellCategory=ii\Type2 : Menu\BlackMarketSellCategory2=ii\Type3
	Next
	If Menu\CurrentItem<>Menu\NewCurrentItem Then Menu\CurrentItem=Menu\NewCurrentItem : Menu\MeshChange=3

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=3
		Menu\NewMenu2=0
		Menu\MeshChange=3
	EndIf

End Function

Function Menu_Transporter_Inventory_Amount(amount)
	If amount>=5 Then Return 5 Else Return amount
End Function

Function Menu_Transporter_InventoryItem(option)

	thisitem=option+Menu\OptionOrder
	If thisitem>TOTALITEMS Then thisitem=thisitem-TOTALITEMS

	For ii.tItem=Each tItem
		If ii\ID=thisitem Then Menu\BlackMarketBuyCategory=ii\Type1 : Menu\BlackMarketSellCategory=ii\Type2 : Menu\BlackMarketSellCategory2=ii\Type3 : isheld=ii\IsHeld
	Next

	If Menu\Option=option Then
		If (Not(Menu\NewCurrentItem=thisitem)) Then Menu\NewCurrentItem=thisitem
		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			PlaySmartSound(Sound_MenuAccept)
			For ii.tItem=Each tItem
				If ii\ID=thisitem Then
					Select ii\IsHeld
					Case False: ii\IsHeld=True
					Case True: ii\IsHeld=False
					End Select
				EndIf
			Next
		EndIf
	EndIf

	Select isheld
		Case false: forcecolor=0
		Case true: forcecolor=5
	End Select
	Select Menu\BlackMarketBuyCategory
		Case 1: DrawSmartButtonT(option, FRUITS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-50*(option-1))*GAME_WINDOW_SCALE#, 4, false, forcecolor)
		Case 2: DrawSmartButtonT(option, HATS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-50*(option-1))*GAME_WINDOW_SCALE#, 4, false, forcecolor)
		Case 3: DrawSmartButtonT(option, CHAOEGGCOLORS$(Menu\BlackMarketSellCategory)+" egg", GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-50*(option-1))*GAME_WINDOW_SCALE#, 4, false, forcecolor)
		Case 4: DrawSmartButtonT(option, SHELLS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-50*(option-1))*GAME_WINDOW_SCALE#, 4, false, forcecolor)
		Case 5: DrawSmartButtonT(option, TOYS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-50*(option-1))*GAME_WINDOW_SCALE#, 4, false, forcecolor)
		Case 6: DrawSmartButtonT(option, "Seed", GAME_WINDOW_W/2+(BUTTON_PLACE1#-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-50*(option-1))*GAME_WINDOW_SCALE#, 4, false, forcecolor)
	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Goodbye()

	Menu\Background2=2

	DrawImageEx(INTERFACE(Interface_Goodbye), GAME_WINDOW_W/2+(BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(55)*GAME_WINDOW_SCALE#)

	Select Menu\OptionOrder2
	Case 1: DrawRealText("Chao will have a happy life in a faraway forest. You will never see your chao again. Do you wish to continue?", GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#+175*GAME_WINDOW_SCALE#)
	Case 2: DrawRealText("Chao looks at you with sorrowful eyes...            Are you sure?", GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#+175*GAME_WINDOW_SCALE#)
	Case 3: DrawRealText("Your chao was sent away. It will never forget you.", GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#+175*GAME_WINDOW_SCALE#)
	End Select

	If Menu\OptionOrder2<3 Then
		DrawChaoStats_Menu(GAME_WINDOW_W/2-(BUTTON_PLACE1#+210)*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2-(87.5)*GAME_WINDOW_SCALE#)

		DrawSmartButtonT(1, "Accept", GAME_WINDOW_W/2+(BUTTON_PLACE1#+205)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#, 1, false, 2)

		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			PlaySmartSound(Sound_MenuAccept)
			Menu\OptionOrder2=Menu\OptionOrder2+1
			If Menu\OptionOrder2=3 Then Menu\ChaoMenuTimer=3.202*secs#
		EndIf

		If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewOption=2
			Menu\NewMenu2=0
		EndIf
	Else
		If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewOption=4
			Menu\NewMenu2=0
			Menu_Transporter_Goodbye_DeleteAChao(Menu\HeldChaoNumber)
			Menu\HeldChaoNumber=0
			Menu\BuddyChaoNumber=0
		EndIf
	EndIf

End Function

Function Menu_Transporter_Goodbye_DeleteAChao(chaonumber)
	DeleteFile(SaveDataPath$+"CHAO"+chaonumber+".dat")
	CHAOSLOTS(1,chaonumber)=0

	For i=1 to CHAOCOUNT
		If i>chaonumber and CHAOSLOTS(1,i)=1 Then
			RenameFile(SaveDataPath$+"CHAO"+i+".dat",SaveDataPath$+"CHAO"+(i-1)+".dat")
			DeleteFile(SaveDataPath$+"CHAO"+i+".dat")
			CHAOSLOTS(1,i)=0
			CHAOSLOTS(1,i-1)=1
		EndIf
	Next

	SaveGame_ChaoSlots()
End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Buddy()

	Menu\Background2=5
	DrawImageEx(INTERFACE(Interface_Buddy), GAME_WINDOW_W/2+(BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(55)*GAME_WINDOW_SCALE#)
	Select Menu\OptionOrder2
	Case 1: DrawRealText("Bring your Chao to stages and let it grow.", GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#+175*GAME_WINDOW_SCALE#)
	Case 2: DrawRealText("Buddy Chao assigned! Enter a stage with your buddy!", GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#, (Interface_Text_3), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-100)*GAME_WINDOW_SCALE#+175*GAME_WINDOW_SCALE#)
	End Select

	If Menu\OptionOrder2<2 Then
		DrawChaoStats_Menu(GAME_WINDOW_W/2-(BUTTON_PLACE1#+210)*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2-(87.5)*GAME_WINDOW_SCALE#)

		DrawSmartButtonT(1, "Make Buddy", GAME_WINDOW_W/2+(BUTTON_PLACE1#+205)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+95*GAME_WINDOW_SCALE#, 1, false, 2)

		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			PlaySmartSound(Sound_MenuAccept)
			Menu\OptionOrder2=Menu\OptionOrder2+1
			If Menu\OptionOrder2=2 Then Menu\ChaoMenuTimer=3.202*secs#
		EndIf

		If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewOption=3
			Menu\NewMenu2=0
		EndIf
	Else
		If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewOption=3
			Menu\BuddyChaoNumber= Menu\HeldChaoNumber
			Menu\NewMenu2=0
		EndIf
	EndIf

End Function


;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
Function Menu_Transporter_Name()

	Menu\Background2=3

	Menu_Transporter_LetterBox(GAME_WINDOW_W/2+(BUTTON_PLACE1#-255)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100)*GAME_WINDOW_SCALE#, 11, 8, 27.5)
	Menu_Transporter_Letters(GAME_WINDOW_W/2+(BUTTON_PLACE1#-255+9.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(100-11)*GAME_WINDOW_SCALE#, 10, 7, 28.5)

	DrawSmartButtonT(1, "Backspace", GAME_WINDOW_W/2+(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+15*GAME_WINDOW_SCALE#, 3)
	DrawSmartButtonT(2, "Done", GAME_WINDOW_W/2+(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+65*GAME_WINDOW_SCALE#, 3)

	Menu_Transporter_NameBox(GAME_WINDOW_W/2+(BUTTON_PLACE1#+117.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-70*GAME_WINDOW_SCALE#, 10, 2, 27.5/2)

	If Input\Pressed\Right Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option
			Case 1,2: Menu\Option=10
			Case 19,29,39,49,59,69,79: Menu\Option=1
			Default: Menu\Option=Menu\Option+1
		End Select
	EndIf

	If Input\Pressed\Left Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option
			Case 1,2: Menu\Option=19
			Case 10,20,30,40,50,60,70: Menu\Option=1
			Default: Menu\Option=Menu\Option-1
		End Select
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option
			Case 1: Menu\Option=2
			Case 2: Menu\Option=1
			Default: Menu\Option=Menu\Option-10 : If Menu\Option<10 Then Menu\Option=Menu\Option+70
		End Select
	EndIf

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option
			Case 1: Menu\Option=2
			Case 2: Menu\Option=1
			Default: Menu\Option=Menu\Option+10 : If Menu\Option>79 Then Menu\Option=Menu\Option-70
		End Select
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		Select Menu\Option
			Case 1:
				PlaySmartSound(Sound_MenuAccept)
				If Len(Menu\NewChaoName$)>0 Then Menu\NewChaoName$=Left(Menu\NewChaoName$,Len(Menu\NewChaoName$)-1)
			Case 2:
				PlaySmartSound(Sound_MenuAccept)
				Menu\HeldChaoName$=Menu\NewChaoName$
				Menu\SaveChaoName=1
				Menu\Transition=1
				Menu\NewOption=1
				Menu\NewMenu2=0
			Default:
				If Len(Menu\NewChaoName$)<20 Then
					PlaySmartSound(Sound_MenuAccept)
					Select Menu\Option
						Case 10: Menu\NewChaoName$=Menu\NewChaoName$+"0"
						Case 11: Menu\NewChaoName$=Menu\NewChaoName$+"1"
						Case 12: Menu\NewChaoName$=Menu\NewChaoName$+"2"
						Case 13: Menu\NewChaoName$=Menu\NewChaoName$+"3"
						Case 14: Menu\NewChaoName$=Menu\NewChaoName$+"4"
						Case 15: Menu\NewChaoName$=Menu\NewChaoName$+"5"
						Case 16: Menu\NewChaoName$=Menu\NewChaoName$+"6"
						Case 17: Menu\NewChaoName$=Menu\NewChaoName$+"7"
						Case 18: Menu\NewChaoName$=Menu\NewChaoName$+"8"
						Case 19: Menu\NewChaoName$=Menu\NewChaoName$+"9"
						Case 20: Menu\NewChaoName$=Menu\NewChaoName$+"A"
						Case 21: Menu\NewChaoName$=Menu\NewChaoName$+"B"
						Case 22: Menu\NewChaoName$=Menu\NewChaoName$+"C"
						Case 23: Menu\NewChaoName$=Menu\NewChaoName$+"D"
						Case 24: Menu\NewChaoName$=Menu\NewChaoName$+"E"
						Case 25: Menu\NewChaoName$=Menu\NewChaoName$+"F"
						Case 26: Menu\NewChaoName$=Menu\NewChaoName$+"G"
						Case 27: Menu\NewChaoName$=Menu\NewChaoName$+"H"
						Case 28: Menu\NewChaoName$=Menu\NewChaoName$+"I"
						Case 29: Menu\NewChaoName$=Menu\NewChaoName$+"J"
						Case 30: Menu\NewChaoName$=Menu\NewChaoName$+"K"
						Case 31: Menu\NewChaoName$=Menu\NewChaoName$+"L"
						Case 32: Menu\NewChaoName$=Menu\NewChaoName$+"M"
						Case 33: Menu\NewChaoName$=Menu\NewChaoName$+"N"
						Case 34: Menu\NewChaoName$=Menu\NewChaoName$+"O"
						Case 35: Menu\NewChaoName$=Menu\NewChaoName$+"P"
						Case 36: Menu\NewChaoName$=Menu\NewChaoName$+"Q"
						Case 37: Menu\NewChaoName$=Menu\NewChaoName$+"R"
						Case 38: Menu\NewChaoName$=Menu\NewChaoName$+"S"
						Case 39: Menu\NewChaoName$=Menu\NewChaoName$+"T"
						Case 40: Menu\NewChaoName$=Menu\NewChaoName$+"U"
						Case 41: Menu\NewChaoName$=Menu\NewChaoName$+"V"
						Case 42: Menu\NewChaoName$=Menu\NewChaoName$+"W"
						Case 43: Menu\NewChaoName$=Menu\NewChaoName$+"X"
						Case 44: Menu\NewChaoName$=Menu\NewChaoName$+"Y"
						Case 45: Menu\NewChaoName$=Menu\NewChaoName$+"Z"
						Case 46: Menu\NewChaoName$=Menu\NewChaoName$+"a"
						Case 47: Menu\NewChaoName$=Menu\NewChaoName$+"b"
						Case 48: Menu\NewChaoName$=Menu\NewChaoName$+"c"
						Case 49: Menu\NewChaoName$=Menu\NewChaoName$+"d"
						Case 50: Menu\NewChaoName$=Menu\NewChaoName$+"e"
						Case 51: Menu\NewChaoName$=Menu\NewChaoName$+"f"
						Case 52: Menu\NewChaoName$=Menu\NewChaoName$+"g"
						Case 53: Menu\NewChaoName$=Menu\NewChaoName$+"h"
						Case 54: Menu\NewChaoName$=Menu\NewChaoName$+"i"
						Case 55: Menu\NewChaoName$=Menu\NewChaoName$+"j"
						Case 56: Menu\NewChaoName$=Menu\NewChaoName$+"k"
						Case 57: Menu\NewChaoName$=Menu\NewChaoName$+"l"
						Case 58: Menu\NewChaoName$=Menu\NewChaoName$+"m"
						Case 59: Menu\NewChaoName$=Menu\NewChaoName$+"n"
						Case 60: Menu\NewChaoName$=Menu\NewChaoName$+"o"
						Case 61: Menu\NewChaoName$=Menu\NewChaoName$+"p"
						Case 62: Menu\NewChaoName$=Menu\NewChaoName$+"q"
						Case 63: Menu\NewChaoName$=Menu\NewChaoName$+"r"
						Case 64: Menu\NewChaoName$=Menu\NewChaoName$+"s"
						Case 65: Menu\NewChaoName$=Menu\NewChaoName$+"t"
						Case 66: Menu\NewChaoName$=Menu\NewChaoName$+"u"
						Case 67: Menu\NewChaoName$=Menu\NewChaoName$+"v"
						Case 68: Menu\NewChaoName$=Menu\NewChaoName$+"w"
						Case 69: Menu\NewChaoName$=Menu\NewChaoName$+"x"
						Case 70: Menu\NewChaoName$=Menu\NewChaoName$+"y"
						Case 71: Menu\NewChaoName$=Menu\NewChaoName$+"z"
						Case 72: Menu\NewChaoName$=Menu\NewChaoName$+"!"
						Case 73: Menu\NewChaoName$=Menu\NewChaoName$+"?"
						Case 74: Menu\NewChaoName$=Menu\NewChaoName$+"."
						Case 75: Menu\NewChaoName$=Menu\NewChaoName$+"-"
						Case 76: Menu\NewChaoName$=Menu\NewChaoName$+"%"
						Case 77: Menu\NewChaoName$=Menu\NewChaoName$+" "
						Case 78: Menu\NewChaoName$=Menu\NewChaoName$+" "
						Case 79: Menu\NewChaoName$=Menu\NewChaoName$+" "
					End Select
				Else
					PlaySmartSound(Sound_MenuRefuse)
				EndIf
		End Select
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=1
		Menu\NewMenu2=0
	EndIf

End Function

Function Menu_Transporter_LetterBox(x#, y#, row, column, spacing#, boxtype#=0)

	Select boxtype#
		Case 0: boxtype#=SmartImage(Interface_Naming)
		Case 1: boxtype#=SmartImage(Interface_Principal)
	End Select

	For i=0 to row-1
	For h=0 to column-1
		Select h
			Case 0:
				Select i
					Case 0:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 0)
					Case row-1:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 1)
					Default:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 5)
				End Select
			Case column-1:
				Select i
					Case 0:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 3)
					Case row-1:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 2)
					Default:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 7)
				End Select
			Default:
				Select i
					Case 0:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 4)
					Case row-1:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 6)
					Default:
						DrawImageEx(INTERFACE(boxtype#), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 8)
				End Select
		End Select
	Next
	Next

End Function

Function Menu_Transporter_Letters(x#, y#, row, column, spacing#)

	j=0
	For h=0 to column-1
		For i=0 to row-1
			If (i+h+j)<70 Then
				If Menu\Option=(i+h+j)+10 Then
					If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
					If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT#/1.5 Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#/1.5
					Menu\ButtonSize#=Menu\ButtonSize2#
					SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
					DrawImageEx(INTERFACE(Interface_Naming), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 10)
					DrawImageEx(INTERFACE(Interface_Naming), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 11+i+h+j)
					SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
				Else
					SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
					DrawImageEx(INTERFACE(Interface_Naming), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 11+i+h+j)
				EndIf
			EndIf
		Next
		j=j+(row-1)
	Next

End Function

Function Menu_Transporter_NameBox(x#, y#, row, column, spacing#)

	SetScale(GAME_WINDOW_SCALE#*1.25, GAME_WINDOW_SCALE#*1.25)
	For h=0 to column-1
	For i=0 to row-1
		DrawImageEx(INTERFACE(Interface_Naming), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 9)
	Next
	Next
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)

	For h=0 to column-1
	For i=0 to row-1
		DrawImageEx(INTERFACE(Interface_Naming), x#+i*spacing#*GAME_WINDOW_SCALE#, y#+h*spacing#*GAME_WINDOW_SCALE#, 10)
	Next
	Next

	DrawRealText(Menu\NewChaoName$, x#-11.25*GAME_WINDOW_SCALE#, y#+8.75*GAME_WINDOW_SCALE#, Interface_TextTitleChao_1, 0, 0, 63, 63, 63, 1.35)

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Exit()

	If Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\WentToChaoMenu=0
		Menu\Transition=1
		Menu\Background=3
		Menu\MeshChange=-1
		Menu\MeshChaoEmoActivated=0
		Delete Menu\MeshChaoEmo
		Menu\SelectedStage=999
		Menu_GoToStage()
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Stadium()

	Menu\Background2=5

	If Menu\HeldChaoNumber>0 Then
		If Menu\NewMenu2=Menu\Menu2 Then
			DrawRealText("Enter your chao to competitions!", GAME_WINDOW_W/2+(-BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-87.5*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
			DrawSmartButtonT(1, "Race", GAME_WINDOW_W/2+(-BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#, 5)
			DrawSmartButtonT(2, "Karate", GAME_WINDOW_W/2+(-BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#, 5)
			DrawSmartButtonT(3, "Exit", GAME_WINDOW_W/2+(-BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, 5)

			DrawSmartButtonT(4, "Difficulty", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(58+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 5, false, 0, true)
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(58+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#)

			DrawRealText("Competitions won: "+Menu\HeldChaoCompetitionsWon, GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(-95+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Competitions lost: "+Menu\HeldChaoCompetitionsLost, GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(-95+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)

			DrawChaoStats_Menu(GAME_WINDOW_W/2-(-BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2-(87.5)*GAME_WINDOW_SCALE#)
		Else
			DrawRealText("Enter your chao to competitions!", GAME_WINDOW_W/2+(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-87.5*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
			DrawSmartButtonT(1, "Race", GAME_WINDOW_W/2+(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#, 5)
			DrawSmartButtonT(2, "Karate", GAME_WINDOW_W/2+(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#, 5)
			DrawSmartButtonT(3, "Exit", GAME_WINDOW_W/2+(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, 5)

			DrawSmartButtonT(4, "Difficulty", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(58-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 5, false, 0, true)
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(58-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#)

			DrawRealText("Competitions won: "+Menu\HeldChaoCompetitionsWon, GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(-95-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("Competitions lost: "+Menu\HeldChaoCompetitionsLost, GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(-95-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)

			DrawChaoStats_Menu(GAME_WINDOW_W/2-(BUTTON_PLACE1#+180)*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2-(87.5)*GAME_WINDOW_SCALE#)
		EndIf
	Else
		DrawRealText("Bring a chao with you to enter it to competitions.", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-87.5*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
		DrawSmartButtonT(1, "Race", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#, 5, true)
		DrawSmartButtonT(2, "Karate", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#, 5, true)
		DrawSmartButtonT(3, "Exit", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#, 5)
	EndIf

	If Input\Pressed\Down and Menu\HeldChaoNumber>0 Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option
			Case 4: Menu\Option=1
			Default:
				Menu\Option=Menu\Option+1
				If Menu\Option>3 Then Menu\Option=1
		End Select
	EndIf

	If Input\Pressed\Up and Menu\HeldChaoNumber>0 Then
		PlaySmartSound(Sound_MenuMove)
		Select Menu\Option
			Case 4: Menu\Option=1
			Default:
				Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=3
		End Select
	EndIf

	If Input\Pressed\ActionSkill2 and Menu\Option<>4 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=4
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		If Menu\Settings\StadiumDifficulty#=0 Then Menu\Settings\StadiumDifficulty#=1
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Select Menu\Option
			Case 1: Menu\NewMenu2=MENU_TRANSPORTER_RACES# : If Menu\RaceType=0 Then Menu\RaceType=1
			Case 2: Menu\NewMenu2=MENU_TRANSPORTER_KARATEEXIT# : Menu\RaceType=0 : Menu\Background=3
			Case 3: Menu\NewMenu2=MENU_TRANSPORTER_EXIT# : Menu\Background=3
			Case 4: Menu\NewMenu2=MENU_TRANSPORTER_DIFFICULTY#
		End Select
		Select Menu\Option
			Case 1: Menu\NewOption=Menu\RaceType
			Case 4: Menu\NewOption=Menu\Settings\StadiumDifficulty#
			Default: Menu\NewOption=1
		End Select
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Races()

	Menu\Background2=5

	DrawSmartButtonT(1, "Cup 1", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(2, "Cup 2", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(3, "Cup 3", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(4, "Cup 4", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#, 5)

	If Input\Pressed\Down and Menu\HeldChaoNumber>0 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>4 Then Menu\Option=1
	EndIf

	If Input\Pressed\Up and Menu\HeldChaoNumber>0 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=4
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\RaceType=Menu\Option
		Menu\NewMenu2=MENU_TRANSPORTER_RACEEXIT#
		Menu\NewOption=1
		Menu\Background=3
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=MENU_TRANSPORTER_STADIUM#
		Menu\NewOption=1
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_RaceExit()

	If Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\WentToChaoMenu=0
		Menu\Transition=1
		Menu\Background=3
		Menu\MeshChange=-1
		Menu\MeshChaoEmoActivated=0
		Delete Menu\MeshChaoEmo
		Menu\SelectedStage=998
		Menu_GoToStage()
	EndIf

End Function

Function Menu_Transporter_KarateExit()

	If Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\WentToChaoMenu=0
		Menu\Transition=1
		Menu\Background=3
		Menu\MeshChange=-1
		Menu\MeshChaoEmoActivated=0
		Delete Menu\MeshChaoEmo
		Menu\SelectedStage=997
		Menu_GoToStage()
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Transporter_Difficulty()

	Menu\Background2=5

	DrawRealText("Choose the power of your opponents.", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-137.5+17.5)*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
	DrawSmartButtonT(1, "Level 1", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100+10)*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(2, "Level 2", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-50+10)*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(3, "Level 3", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-0+10)*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(4, "Level 4", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(50+10)*GAME_WINDOW_SCALE#, 5)
	DrawSmartButtonT(5, "Level 5", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(100+10)*GAME_WINDOW_SCALE#, 5)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>5 Then Menu\Option=1
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=5
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		Menu\Settings\StadiumDifficulty#=Menu\Option
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\NewMenu2=MENU_TRANSPORTER_STADIUM#
		Menu\NewOption=1
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewMenu2=MENU_TRANSPORTER_STADIUM#
		Menu\NewOption=1
	EndIf

End Function