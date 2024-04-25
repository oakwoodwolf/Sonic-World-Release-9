
	Function LoadBlackMarketInfo$(itemtype,itemno)
		If Len(Menu\CurrentItemInfo$)=0
			Select itemtype
				Case 1: Menu\CurrentItemInfo$=ReadAndFindTextExplanation$("Text_FruitsInfo",itemno)
				Case 2: Menu\CurrentItemInfo$=ReadAndFindTextExplanation$("Text_HatsInfo",itemno)
				Case 3: Menu\CurrentItemInfo$=ReadAndFindTextExplanation$("Text_EggsInfo",itemno)
				Case 4: Menu\CurrentItemInfo$=ReadAndFindTextExplanation$("Text_ShellsInfo",itemno)
				Case 5: Menu\CurrentItemInfo$=ReadAndFindTextExplanation$("Text_ToysInfo",itemno)
				Case 6: Menu\CurrentItemInfo$=ReadAndFindTextExplanation$("Text_SeedsInfo",1)
			End Select
		EndIf
	End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_Update()

	Menu\Music=7
	If Menu\WentToChaoMenu=0 Then Menu\Background=3 Else Menu\Background=0
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\WentToChaoMenu=1 Then;!

	graphicsscale# = 30*Menu\Settings\ScreenMode#*GAME_WINDOW_SCALE#
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2-(175)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+105)*GAME_WINDOW_SCALE#+graphicsscale#,0)
	DrawRealText(Menu\Convo$, GAME_WINDOW_W/2-(300)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+90)*GAME_WINDOW_SCALE#+graphicsscale#, (Interface_Text_2), 0, GAME_WINDOW_W/2-(50)*GAME_WINDOW_SCALE#)
	Select Menu\BlackMarketYesNo
		Case 1:
			DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(17.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+105)*GAME_WINDOW_SCALE#+graphicsscale#,1)
			DrawRealText("Yes", GAME_WINDOW_W/2+(-0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+95)*GAME_WINDOW_SCALE#+graphicsscale#, (Interface_Text_3))
			If Menu\Option=1 Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+93.75)*GAME_WINDOW_SCALE#+graphicsscale#,18)
			DrawRealText("No", GAME_WINDOW_W/2+(-0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#+graphicsscale#, (Interface_Text_3))
			If Menu\Option=2 Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+113.75)*GAME_WINDOW_SCALE#+graphicsscale#,18)
		Case 2:
			DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(17.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+105)*GAME_WINDOW_SCALE#+graphicsscale#,1)
			DrawRealText("Confirm", GAME_WINDOW_W/2+(-0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+95)*GAME_WINDOW_SCALE#+graphicsscale#, (Interface_Text_3))
			If Menu\Option2=1 Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+93.75)*GAME_WINDOW_SCALE#+graphicsscale#,18)
			DrawRealText("Cancel", GAME_WINDOW_W/2+(-0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#+graphicsscale#, (Interface_Text_3))
			If Menu\Option2=2 Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+113.75)*GAME_WINDOW_SCALE#+graphicsscale#,18)
	End Select

	Select Menu\Menu2
		Case MENU_BLACKMARKET_MAIN#: Menu_BlackMarket_Main()
		Case MENU_BLACKMARKET_BUY#: Menu_BlackMarket_Buy()
		Case MENU_BLACKMARKET_SELLLIST#: Menu_BlackMarket_SellList()
		Case MENU_BLACKMARKET_EXIT#: Menu_BlackMarket_Exit()
		Case MENU_BLACKMARKET_BUYLIST#: Menu_BlackMarket_BuyList()
		Case MENU_BLACKMARKET_BUYCONFIRM#: Menu_BlackMarket_BuyConfirm()
		Case MENU_BLACKMARKET_BUYREFUSE#: Menu_BlackMarket_BuyRefuse()
		Case MENU_BLACKMARKET_SELLCONFIRM#: Menu_BlackMarket_SellConfirm()
		Case MENU_BLACKMARKET_SELLREFUSE#: Menu_BlackMarket_SellRefuse()
		Case MENU_BLACKMARKET_EXITREAL#: Menu_BlackMarket_ExitReal()
	End Select

	EndIf;!

	If Menu\ChaoMenuTimer>0 Then Menu\ChaoMenuTimer=Menu\ChaoMenuTimer-timervalue#

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_Main()

	Menu\Convo$="Welcome to the Black Market!            How can we help you?"
	Menu\BlackMarketYesNo=0

	DrawSmartButton(1, "Buy", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#)
	DrawSmartButton(2, "Sell", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#)
	DrawSmartButton(3, "Exit", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)

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
		Menu\Transition=1
		Menu\NewMenu2=Menu\Option
		Menu\NewOption=1
		Select Menu\Option
			Case 2: Menu\OptionOrder=0 : Menu\CurrentItem=0
		End Select
		Menu\CurrentItemInfo$=""
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_Buy()

	Menu\Convo$="We have a lot of good items!            What would you like to buy?"
	Menu\BlackMarketYesNo=0

	DrawSmartButton(1, "Food", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#)
	DrawSmartButton(2, "Hats", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#)
	DrawSmartButton(3, "Eggs", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
	DrawSmartButton(4, "Toys", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#)

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
			Case 4: Menu\BlackMarketBuyCategory=5
			Default: Menu\BlackMarketBuyCategory=Menu\Option
		End Select
		Menu\Transition=1
		Menu\NewOption=1
		Menu\OptionOrder=0
		Menu\NewMenu2=MENU_BLACKMARKET_BUYLIST#
		Menu\CurrentItemInfo$=""
		Menu\CurrentItem=0
		Menu\ItemAmount=1
		FillDealersInventory(Menu\BlackMarketBuyCategory)
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=1
		Menu\NewMenu2=0
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_BuyList()

	Select Menu\BlackMarketBuyCategory
		Case 1: Menu\Convo$="Delicious nutrition for your chao.        What would you like to buy?"
		Case 2: Menu\Convo$="Amusing hats for your chao.             What would you like to buy?"
		Case 3: Menu\Convo$="Chao that are looking for a home.      Which one would you like to adopt?"
		Case 5: Menu\Convo$="Fun toys for your chao.                 What would you like to buy?"
	End Select
	Menu\BlackMarketYesNo=0

	If Menu\BlackMarketBuyCategory=3 and CHAOSUM(1)+Menu\EggsBought>=CHAOCOUNT Then Menu\BuyRefused=1 Else Menu\BuyRefused=0

	If Menu\BuyRefused=0 Then ;!

	If Input\Pressed\Down and TOTALDEALERITEMS>1 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>4 Then Menu\Option=4 : Menu\OptionOrder=Menu\OptionOrder+1
		If Menu\OptionOrder>TOTALDEALERITEMS-1 Then Menu\OptionOrder=0
		Menu\ItemAmount=1
		Menu\CurrentItemInfo$=""
	EndIf

	If Input\Pressed\Up and TOTALDEALERITEMS>1 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=1 : Menu\OptionOrder=Menu\OptionOrder-1
		If Menu\OptionOrder<0 Then Menu\OptionOrder=TOTALDEALERITEMS-1
		Menu\ItemAmount=1
		Menu\CurrentItemInfo$=""
	EndIf

	For i=1 To Min(4,TOTALDEALERITEMS)
		Menu_BlackMarket_BuyListItem(i)
	Next
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(115)*GAME_WINDOW_SCALE#,20)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(Min(4,TOTALDEALERITEMS)-1))*GAME_WINDOW_SCALE#-(-27.5)*GAME_WINDOW_SCALE#,21)

	If Menu\CurrentItem<>Menu\NewCurrentItem Then Menu\CurrentItem=Menu\NewCurrentItem : Menu\MeshChange=3

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+100)*GAME_WINDOW_SCALE#,2)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+60)*GAME_WINDOW_SCALE#,2)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+20)*GAME_WINDOW_SCALE#,2)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+87.5)*GAME_WINDOW_SCALE#,6)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+62.5)*GAME_WINDOW_SCALE#,6)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+37.5)*GAME_WINDOW_SCALE#,6)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+12.5)*GAME_WINDOW_SCALE#,6)

	SetColor(150,150,150)
	Select Menu\BlackMarketBuyCategory
		Case 1: DrawRealText(FRUITS$(Menu\CurrentItem)+" "+FRUITS_TYPE$(Menu\CurrentItem), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 2: DrawRealText(HATS$(Menu\CurrentItem), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 3: DrawRealText(CHAOEGGCOLORS$(Menu\CurrentItem)+" egg", GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 5: DrawRealText(TOYS$(Menu\CurrentItem), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
	End Select
	SetColor(255,255,255)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+27.5)*GAME_WINDOW_SCALE#,5)
	LoadBlackMarketInfo$(Menu\BlackMarketBuyCategory,Menu\CurrentItem)
	DrawRealText(Menu\CurrentItemInfo$, GAME_WINDOW_W/2+(107.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+40)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0, GAME_WINDOW_W/2+(245)*GAME_WINDOW_SCALE#)

	Select Menu\BlackMarketBuyCategory
		Case 1: Menu\CurrentPrice=FRUITS_PRICES(Menu\CurrentItem)
		Case 2: Menu\CurrentPrice=HATS_PRICES(Menu\CurrentItem)
		Case 3: Menu\CurrentPrice=EGGS_PRICES(Menu\CurrentItem)
		Case 5: Menu\CurrentPrice=TOYS_PRICES(Menu\CurrentItem)
	End Select
	If Menu\CurrentPrice*Menu\ItemAmount<10000 Then
		DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+80)*GAME_WINDOW_SCALE#,4)
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(195)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+81.25)*GAME_WINDOW_SCALE#, 16)
	Else
		DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+80)*GAME_WINDOW_SCALE#,9)
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(117.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+81.25)*GAME_WINDOW_SCALE#, 16)
	EndIf
	DrawBetterNumber(Menu\CurrentPrice*Menu\ItemAmount, GAME_WINDOW_W/2+(282.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+81.25)*GAME_WINDOW_SCALE#, 0, 1)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(210)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2-32.5)*GAME_WINDOW_SCALE#,3)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(137.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2-32.5)*GAME_WINDOW_SCALE#, 2)
	DrawBetterNumber(Menu\Wallet, GAME_WINDOW_W/2+(277.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2-32.5)*GAME_WINDOW_SCALE#, 0, 1)

	If Input\Pressed\Right Then
		If ( Menu\ItemAmount<50 and (Not(Menu\BlackMarketBuyCategory=3)) ) Or (Menu\ItemAmount+CHAOSUM(1)+Menu\EggsBought)<CHAOCOUNT Then
			If Not(Menu\CurrentPrice*(Menu\ItemAmount+1)>Menu\Wallet) Then
				Menu\ItemAmount=Menu\ItemAmount+1
				PlaySmartSound(Sound_MenuMove)
			Else
				PlaySmartSound(Sound_MenuRefuse)
			EndIf
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf

	If Input\Pressed\Left Then
		If Menu\ItemAmount>1 Then
			Menu\ItemAmount=Menu\ItemAmount-1
			PlaySmartSound(Sound_MenuMove)
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf

	DrawBetterNumber(Menu\ItemAmount, GAME_WINDOW_W/2+(270)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+117.5)*GAME_WINDOW_SCALE#, 0, 1)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(270+20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+117.5)*GAME_WINDOW_SCALE#, 18)
	If Menu\ItemAmount<10 Then
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(270-20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+117.5)*GAME_WINDOW_SCALE#, 19)
	Else
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(270-45)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+117.5)*GAME_WINDOW_SCALE#, 19)
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		If Menu\Wallet>=(Menu\CurrentPrice*Menu\ItemAmount) Then
			PlaySmartSound(Sound_MenuAccept)
			Menu\Transition=1
			Menu\NewOption=Menu\Option
			Menu\NewOption2=1
			Menu\OptionOrder2=0
			Menu\NewMenu2=MENU_BLACKMARKET_BUYCONFIRM#
			Menu\CurrentItemInfo$=""
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Select Menu\BlackMarketBuyCategory
			Case 5: Menu\NewOption=4
			Default: Menu\NewOption=Menu\BlackMarketBuyCategory
		End Select
		Menu\NewMenu2=MENU_BLACKMARKET_BUY#
		Menu\MeshChange=3
	EndIf

	EndIf ;!

	If Menu\BuyRefused=1 and Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuRefuse)
		Menu\Transition=1
		Menu\MeshChange=3
		Menu\NewMenu2=MENU_BLACKMARKET_BUYREFUSE#
		Menu\ChaoMenuTimer=1.82*secs#
	EndIf

End Function

Function Menu_BlackMarket_BuyListItem(option)

	thisitem=option+Menu\OptionOrder
	If thisitem>TOTALDEALERITEMS Then thisitem=thisitem-TOTALDEALERITEMS

	thisitemtype=0
	For dii.tDealerItem=Each tDealerItem
		If dii\ID=thisitem Then thisitemtype=dii\Type1
	Next

	If Menu\Option=option Then
		If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-0.5*BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
		If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+0.5*BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT#*0.5 Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#*0.5
		SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize2#, GAME_WINDOW_SCALE#+Menu\ButtonSize2#)
		DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#,7)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		If (Not(Menu\NewCurrentItem=thisitemtype)) Then Menu\NewCurrentItem=thisitemtype
	Else
		DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#,8)
	EndIf

	Select Menu\BlackMarketBuyCategory
		Case 1: DrawRealText(FRUITS$(thisitemtype), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 2: DrawRealText(HATS$(thisitemtype), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 3: DrawRealText(CHAOEGGCOLORS$(thisitemtype)+" egg", GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 5: DrawRealText(TOYS$(thisitemtype), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_BuyConfirm()

	Select Menu\OptionOrder2
	Case 0:
		If Menu\ItemAmount<=1 Then
			Menu\Convo$="Do you want to make this purchase?"
		Else
			Menu\Convo$="Do you want to make this purchase of  "+Menu\ItemAmount+" items?"
		EndIf
		Menu\BlackMarketYesNo=2

		If Input\Pressed\Down Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option2=Menu\Option2+1
			If Menu\Option2>2 Then Menu\Option2=1
		EndIf

		If Input\Pressed\Up Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option2=Menu\Option2-1
			If Menu\Option2<1 Then Menu\Option2=2
		EndIf

		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			PlaySmartSound(Sound_MenuAccept)
			Select Menu\Option2
				Case 1:
					Menu\OptionOrder2=1
					Menu\ChaoMenuTimer=0.701*secs#
					For item=1 to Menu\ItemAmount
						Select Menu\BlackMarketBuyCategory
							Case 1: ii.tItem = Item_Create(TOTALITEMS+1, Menu\BlackMarketBuyCategory, Menu\CurrentItem, 5, true)
							Case 2: ii.tItem = Item_Create(TOTALITEMS+1, Menu\BlackMarketBuyCategory, Menu\CurrentItem, 0, true)
							Case 3: ii.tItem = Item_Create(TOTALITEMS+1, Menu\BlackMarketBuyCategory, Menu\CurrentItem, 0, true) : Menu\EggsBought=Menu\EggsBought+1
							Case 5: ii.tItem = Item_Create(TOTALITEMS+1, Menu\BlackMarketBuyCategory, Menu\CurrentItem, 0, true)
							Default: ii.tItem = Item_Create(TOTALITEMS+1, Menu\BlackMarketBuyCategory, Menu\CurrentItem, 0, true)
						End Select
						Menu\Wallet=Menu\Wallet-Menu\CurrentPrice
					Next
					SaveGame_Inventory()
				Case 2:
					Menu\Transition=1
					Menu\NewMenu2=MENU_BLACKMARKET_BUYLIST#
			End Select
		EndIf

		If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewMenu2=MENU_BLACKMARKET_BUYLIST#
		EndIf

	Case 1:
		Menu\Convo$="Thank you!!"
		Menu\BlackMarketYesNo=0

		If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewMenu2=MENU_BLACKMARKET_BUYLIST#
		EndIf

	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_BuyRefuse()

	Select Menu\BlackMarketBuyCategory
		Case 3: Menu\Convo$="You already have too many chao in the garden."
		Default: Menu\Convo$=""
	End Select
	Menu\BlackMarketYesNo=0

	If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=Menu\BlackMarketBuyCategory
		Menu\NewMenu2=MENU_BLACKMARKET_BUY#
		Menu\MeshChange=3
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_SellList()

	Menu\Convo$="Let's see what you have here.          Hmm... What would you like to sell?"
	Menu\BlackMarketYesNo=0

	If TOTALITEMS>0 Then ;!

	If Input\Pressed\Down and TOTALITEMS>1 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>Min(4,TOTALITEMS) Then Menu\Option=Min(4,TOTALITEMS) : Menu\OptionOrder=Menu\OptionOrder+1
		If Menu\OptionOrder>TOTALITEMS-1 Then Menu\OptionOrder=0
		Menu\CurrentItemInfo$=""
	EndIf

	If Input\Pressed\Up and TOTALITEMS>1 Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=1 : Menu\OptionOrder=Menu\OptionOrder-1
		If Menu\OptionOrder<0 Then Menu\OptionOrder=TOTALITEMS-1
		Menu\CurrentItemInfo$=""
	EndIf

	For i=1 to Min(4,TOTALITEMS)
		Menu_BlackMarket_SellListItem(i)
	Next
	If TOTALITEMS>1 Then
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(115)*GAME_WINDOW_SCALE#,20)
		DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(Min(4,TOTALITEMS)-1))*GAME_WINDOW_SCALE#-(-27.5)*GAME_WINDOW_SCALE#,21)
	EndIf

	For ii.tItem=Each tItem
		If ii\ID=Menu\NewCurrentItem Then Menu\BlackMarketBuyCategory=ii\Type1 : Menu\BlackMarketSellCategory=ii\Type2 : Menu\BlackMarketSellCategory2=ii\Type3
	Next
	If Menu\CurrentItem<>Menu\NewCurrentItem Then Menu\CurrentItem=Menu\NewCurrentItem : Menu\MeshChange=3

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+100)*GAME_WINDOW_SCALE#,2)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+60)*GAME_WINDOW_SCALE#,2)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+20)*GAME_WINDOW_SCALE#,2)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+87.5)*GAME_WINDOW_SCALE#,6)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+62.5)*GAME_WINDOW_SCALE#,6)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+37.5)*GAME_WINDOW_SCALE#,6)
	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+12.5)*GAME_WINDOW_SCALE#,6)

	SetColor(150,150,150)
	Select Menu\BlackMarketBuyCategory
		Case 1: DrawRealText(FRUITS$(Menu\BlackMarketSellCategory)+" "+FRUITS_TYPE$(Menu\CurrentItem), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 2: DrawRealText(HATS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 3: DrawRealText(CHAOEGGCOLORS$(Menu\BlackMarketSellCategory)+" egg", GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 4: DrawRealText(SHELLS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 5: DrawRealText(TOYS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
		Case 6: DrawRealText("Seed", GAME_WINDOW_W/2+(105)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+115)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0)
	End Select
	SetColor(255,255,255)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+27.5)*GAME_WINDOW_SCALE#,5)
	LoadBlackMarketInfo$(Menu\BlackMarketBuyCategory,Menu\BlackMarketSellCategory)
	DrawRealText(Menu\CurrentItemInfo$, GAME_WINDOW_W/2+(107.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+40)*GAME_WINDOW_SCALE#, (Interface_Text_2), 0, GAME_WINDOW_W/2+(245)*GAME_WINDOW_SCALE#)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+80)*GAME_WINDOW_SCALE#,4)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(195)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+81.25)*GAME_WINDOW_SCALE#, 16)
	Select Menu\BlackMarketBuyCategory
		Case 1: Menu\CurrentPrice=0.6*FRUITS_PRICES(Menu\BlackMarketSellCategory)
		Case 2: Menu\CurrentPrice=0.6*HATS_PRICES(Menu\BlackMarketSellCategory)
		Case 3: Menu\CurrentPrice=0.6*EGGS_PRICES(Menu\BlackMarketSellCategory)
		Case 4: Menu\CurrentPrice=0.6*SHELLS_PRICES(Menu\BlackMarketSellCategory)
		Case 5: Menu\CurrentPrice=0.6*TOYS_PRICES(Menu\BlackMarketSellCategory)
		Case 6: Menu\CurrentPrice=0.2*FRUITS_PRICES(Menu\BlackMarketSellCategory)
	End Select
	DrawBetterNumber(Menu\CurrentPrice, GAME_WINDOW_W/2+(282.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2+81.25)*GAME_WINDOW_SCALE#, 0, 1)

	DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2+(210)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2-32.5)*GAME_WINDOW_SCALE#,3)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(137.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2-32.5)*GAME_WINDOW_SCALE#, 2)
	DrawBetterNumber(Menu\Wallet, GAME_WINDOW_W/2+(277.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*2-32.5)*GAME_WINDOW_SCALE#, 0, 1)

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\NewOption=Menu\Option
		Menu\NewOption2=1
		Menu\OptionOrder2=0
		Menu\NewMenu2=MENU_BLACKMARKET_SELLCONFIRM#
		Menu\CurrentItemInfo$=""
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=2
		Menu\NewMenu2=0
		Menu\MeshChange=3
	EndIf

	EndIf ;!

	If (Not(TOTALITEMS>0)) and Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuRefuse)
		Menu\Transition=1
		Menu\MeshChange=3
		Menu\NewMenu2=MENU_BLACKMARKET_SELLREFUSE#
		Menu\ChaoMenuTimer=1.82*secs#
	EndIf

End Function

Function Menu_BlackMarket_SellListItem(option)

	thisitem=option+Menu\OptionOrder
	If thisitem>TOTALITEMS Then thisitem=thisitem-TOTALITEMS

	For ii.tItem=Each tItem
		If ii\ID=thisitem Then Menu\BlackMarketBuyCategory=ii\Type1 : Menu\BlackMarketSellCategory=ii\Type2 : Menu\BlackMarketSellCategory2=ii\Type3
	Next

	If Menu\Option=option Then
		If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-0.5*BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
		If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+0.5*BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize2#>BUTTON_SCALELIMIT#*0.5 Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#*0.5
		SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize2#, GAME_WINDOW_SCALE#+Menu\ButtonSize2#)
		DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#,7)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		If (Not(Menu\NewCurrentItem=thisitem)) Then Menu\NewCurrentItem=thisitem
	Else
		DrawImageEx(INTERFACE(Interface_BlackMarket), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#,8)
	EndIf

	Select Menu\BlackMarketBuyCategory
		Case 1: DrawRealText(FRUITS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 2: DrawRealText(HATS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 3: DrawRealText(CHAOEGGCOLORS$(Menu\BlackMarketSellCategory)+" egg", GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 4: DrawRealText(SHELLS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 5: DrawRealText(TOYS$(Menu\BlackMarketSellCategory), GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
		Case 6: DrawRealText("Seed", GAME_WINDOW_W/2-(BUTTON_PLACE1#-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(87.5-35*(option-1))*GAME_WINDOW_SCALE#, (Interface_TextButtons_1), 1)
	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_SellConfirm()

	Select Menu\OptionOrder2
	Case 0:
		Menu\Convo$="Do you want to trade for this item?    You will not be able to get it back."
		Menu\BlackMarketYesNo=2

		If Input\Pressed\Down Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option2=Menu\Option2+1
			If Menu\Option2>2 Then Menu\Option2=1
		EndIf

		If Input\Pressed\Up Then
			PlaySmartSound(Sound_MenuMove)
			Menu\Option2=Menu\Option2-1
			If Menu\Option2<1 Then Menu\Option2=2
		EndIf

		If Input\Pressed\ActionJump Or Input\Pressed\Start Then
			PlaySmartSound(Sound_MenuAccept)
			Select Menu\Option2
				Case 1:
					Menu\OptionOrder2=1
					Menu\ChaoMenuTimer=0.701*secs#
					For ii.tItem=Each tItem
						If ii\ID=Menu\CurrentItem Then
							If ii\Type1=3 and ii\IsHeld Then Menu\EggsBought=Menu\EggsBought-1
							Delete ii : TOTALITEMS=TOTALITEMS-1
						EndIf
					Next
					For ii.tItem=Each tItem
						If ii\ID>Menu\CurrentItem Then ii\ID=ii\ID-1
					Next
					Menu\Wallet=Menu\Wallet+Menu\CurrentPrice
					SaveGame_Inventory()
				Case 2:
					Menu\Transition=1
					Menu\NewMenu2=MENU_BLACKMARKET_SELLLIST#
			End Select
		EndIf

		If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewMenu2=MENU_BLACKMARKET_SELLLIST#
		EndIf

	Case 1:
		Menu\Convo$="Thank you!!"
		Menu\BlackMarketYesNo=0

		If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
			PlaySmartSound(Sound_MenuBack)
			Menu\Transition=1
			Menu\NewMenu2=MENU_BLACKMARKET_SELLLIST#
			Menu\NewOption=1
			Menu\OptionOrder=0 : Menu\CurrentItem=0
		EndIf

	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_SellRefuse()

	Menu\Convo$="You have no items to offer."
	Menu\BlackMarketYesNo=0

	If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=2
		Menu\NewMenu2=0
		Menu\MeshChange=3
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_Exit()

	Menu\Convo$="Are you sure you want to leave?"
	Menu\BlackMarketYesNo=1

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

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Select Menu\Option
			Case 1:
				Menu\Transition=1
				Menu\NewMenu2=MENU_BLACKMARKET_EXITREAL#
				Menu\ChaoMenuTimer=1.62*secs#
				SaveGame_Inventory()
			Case 2:
				Menu\Transition=1
				Menu\NewOption=3
				Menu\NewMenu2=0
		End Select
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=3
		Menu\NewMenu2=0
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_BlackMarket_ExitReal()

	Menu\Convo$="Good bye!! Come again!"
	Menu\BlackMarketYesNo=0

	If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\WentToChaoMenu=0
		Menu\Transition=1
		Menu\Background=3
		Menu\MeshChange=-1
		Menu_GoToStage()
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Principal_Update()

	Menu\Music=7
	If Menu\WentToChaoMenu=0 Then Menu\Background=3 Else Menu\Background=0
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Menu\WentToChaoMenu=1 Then;!

	Select Menu\Menu2
		Case MENU_PRINCIPAL_MAIN#: Menu_Principal_Main()
		Case MENU_PRINCIPAL_LESSONS#: Menu_Principal_Lessons()
		Case MENU_PRINCIPAL_EXITREAL#: Menu_Principal_ExitReal()
	End Select

	EndIf;!

	If Menu\ChaoMenuTimer>0 Then Menu\ChaoMenuTimer=Menu\ChaoMenuTimer-timervalue#

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Principal_Main()

	DrawRealText(Menu\Convo$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#-150*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-95*GAME_WINDOW_SCALE#, (Interface_Text_2), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#+120*GAME_WINDOW_SCALE#)

	DrawSmartButtonT(1, "Lessons", GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+22.5*GAME_WINDOW_SCALE#, 4)
	DrawSmartButtonT(2, "Exit", GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+72.5*GAME_WINDOW_SCALE#, 4)

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

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Menu\NewMenu2=Menu\Option
		Menu\NewOption=1
		Select Menu\Option
			Case 2: Menu\ChaoMenuTimer=1.62*secs#
		End Select
		Menu\Convo$=""
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Principal_Lesson(lessonno, x#, y#)
	If Len(Menu\Convo$)=0 Then
		Menu\ConvoName$=ReadAndFindTextExplanation$("Text_ChaoLessonNames",lessonno)
		Menu\Convo$=ReadAndFindTextExplanation$("Text_ChaoLessons",lessonno)
	EndIf
	DrawSmartButtonT(lessonno, Menu\ConvoName$, x#, y#, 4)
End Function

Function Menu_Principal_Lessons()

	Menu_Principal_Lesson(Menu\Option+Menu\OptionOrder, GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-5*GAME_WINDOW_SCALE#-75*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#-75*GAME_WINDOW_SCALE#,20)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#+10*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+30*GAME_WINDOW_SCALE#-75*GAME_WINDOW_SCALE#,21)

	Menu_Transporter_LetterBox(GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#-124*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-12*GAME_WINDOW_SCALE#, 11, 6, 27.5, 1)
	DrawRealText(Menu\Convo$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-10*GAME_WINDOW_SCALE#, (Interface_Text_2), 0, GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#+105*GAME_WINDOW_SCALE#)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>16 Then Menu\Option=1
		Menu\Convo$=""
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=16
		Menu\Convo$=""
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=1
		Menu\NewMenu2=0
		Menu\Convo$=ReadAndFindTextExplanation$("Text_ChaoLessons",0)
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Menu_Principal_ExitReal()

	DrawRealText("Have a nice day! Goodbye!", GAME_WINDOW_W/2+(BUTTON_PLACE1#-60)*GAME_WINDOW_SCALE#-15*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)

	If (Not(Menu\ChaoMenuTimer>0)) and Menu\Transition=0 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\WentToChaoMenu=0
		Menu\Transition=1
		Menu\Background=3
		Menu\MeshChange=-1
		Menu_GoToStage()
	EndIf

End Function