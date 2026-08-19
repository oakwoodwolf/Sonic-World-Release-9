

Function Interface_Render_Stage_SoundTest(p.tPlayer,d.tDeltaTime)
	
	DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
	
	DrawRealText("Sound Test", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
	
	DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
	DrawRealText("Move", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
	DrawRealText("Play", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
	DrawRealText("Stop", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawRealText(Game\Interface\SoundTestOption, GAME_WINDOW_W-(370)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawRealText(SOUNDS_NAME$(Game\Interface\SoundTestOption), GAME_WINDOW_W-(370)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	If Input\Pressed\Right Then 
		StopChannel(Game\Channel_SoundTest)
		PlaySmartSound(Sound_MenuMove)
		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption+1
		If Game\Interface\SoundTestOption>SOUNDS_STAGETOTAL Then Game\Interface\SoundTestOption=SOUNDS_STAGETOTAL
	EndIf
	
	If Input\Pressed\Left Then 
		StopChannel(Game\Channel_SoundTest)
		PlaySmartSound(Sound_MenuMove)
		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption-1
		If Game\Interface\SoundTestOption<Sound_Aim Then Game\Interface\SoundTestOption=Sound_Aim
	EndIf
	
	If Input\Pressed\Up Then 
		StopChannel(Game\Channel_SoundTest)
		PlaySmartSound(Sound_MenuMove)
		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption+10
		If Game\Interface\SoundTestOption>SOUNDS_STAGETOTAL Then Game\Interface\SoundTestOption=SOUNDS_STAGETOTAL
	EndIf
	
	If Input\Pressed\Down Then 
		StopChannel(Game\Channel_SoundTest)
		PlaySmartSound(Sound_MenuMove)
		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption-10
		If Game\Interface\SoundTestOption<Sound_Aim Then Game\Interface\SoundTestOption=Sound_Aim
	EndIf
	
	If Input\Pressed\ActionJump Then
		StopChannel(Game\Channel_SoundTest)
		Game\Channel_SoundTest = PlaySmartSound(Game\Interface\SoundTestOption)
	EndIf
	
	If Input\Pressed\ActionRoll Then
		
		Game\Interface\SoundTest=0
		StopChannel(Game\Channel_SoundTest)
	EndIf
	
End Function	
;====================================================================================
;====================================================================================
;====================================================================================
Function Interface_Render_Stage_Shop()
	
	
	If KeyHit(KEY_ESCAPE) Then Game\Interface\Shop=0
	
	DrawRealText("The Shop", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
	
	DrawBetterNumber(Menu\Wallet, 50*GAME_WINDOW_SCALE#,  (30+30*1)*GAME_WINDOW_SCALE#)
	DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#,  (30+30*1)*GAME_WINDOW_SCALE#, 2)
	
	DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
	DrawRealText("Move", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
	DrawRealText("Select", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
	DrawRealText("Go Back", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	space#=13.5
	
	Interface_Render_Stage_Debug_DrawSquare(1, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-45*GAME_WINDOW_SCALE#, 20, 3, space#,1)
	
		;---
	
	
	
	
	
	
	DrawRealText("Items Unlocked:", 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText("Purchases Made: "+Str(Menu\ShopItemsBought), 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	
	If Not(Game\Interface\ShopMenu=SHOPMENU_MAIN) Then 
		Interface_Render_Stage_Debug_DrawSquare(3, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-90*GAME_WINDOW_SCALE#, 20, 3, space#,1)
		Interface_Render_Stage_Debug_DrawSquare(2, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-121.25*GAME_WINDOW_SCALE#, 10, 2, space#,1)
	EndIf
	
	Select Game\Interface\ShopMenu
		Case SHOPMENU_MAIN
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\ShopMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Abilities", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Skills", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Handicaps", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Consumables", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Cosmetics", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			
			
			If Input\Pressed\ActionRoll Then Game\Interface\Shop=0 : PlaySmartSound(Sound_MenuBack)
			
			If Input\Pressed\ActionJump Then Game\Interface\ShopMenu=Game\Interface\ShopMenuOption: PlaySmartSound(Sound_MenuAccept)
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\ShopMenuOption=Game\Interface\ShopMenuOption+1
				If Game\Interface\ShopMenuOption>5 Then Game\Interface\ShopMenuOption=1
			EndIf
			
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\ShopMenuOption=Game\Interface\ShopMenuOption-1
				If Game\Interface\ShopMenuOption<1 Then Game\Interface\ShopMenuOption=5
			EndIf
			
			If Input\Pressed\ActionJump Then Game\Interface\ShopMenu=Game\Interface\ShopMenuOption : PlaySmartSound(Sound_MenuAccept) : Game\Interface\ShopMenuOption=1
		Case SHOPMENU_ABILITY,SHOPMENU_CONSUME,SHOPMENU_COSMETIC,SHOPMENU_HANDICAP,SHOPMENU_SKILL
			Interface_Render_Stage_Shop_Choose(1)
		Case SHOPMENU_ABILITY*10,SHOPMENU_CONSUME*10,SHOPMENU_COSMETIC*10,SHOPMENU_HANDICAP*10,SHOPMENU_SKILL*10
			Interface_Render_Stage_Shop_Choose(2)	
			
			
	End Select
	
	
	
	
End Function

;====================================================================================
;====================================================================================
;====================================================================================
Function Interface_Render_Stage_Shop_Choose(mode)
	
	space#=13.5
	Local shopmenu
	If Game\Interface\ShopMenu>5 Then shopmenu=Game\Interface\ShopMenu/10 Else shopmenu=Game\Interface\ShopMenu
	DrawRealText("Price: "+SHOPITEM_PRICE(shopmenu,Game\Interface\ShopMenuOption)+" Rings", 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText("Score Multiplier: "+SHOPITEM_MULT#(shopmenu,Game\Interface\ShopMenuOption), 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	
	DrawRealText("Description:", 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-117.5*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText(SHOPITEM_DESC1$(shopmenu,Game\Interface\ShopMenuOption), 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-117.5*GAME_WINDOW_SCALE#+(space#*3)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText(SHOPITEM_DESC2$(shopmenu,Game\Interface\ShopMenuOption), 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-117.5*GAME_WINDOW_SCALE#+(space#*4)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	
	Select mode
		Case 1
			
			DrawArrow(GAME_WINDOW_W-(250+40)*GAME_WINDOW_SCALE#, (10+25*Game\Interface\ShopMenuOption)*GAME_WINDOW_SCALE#)
			
			For i = 1 To SHOPITEM_TOTAL(Game\Interface\ShopMenu)
				If SHOPITEM_UNLOCKED(Game\Interface\ShopMenu,i)=0 Then
					SetColor(255,255,255)
				Else
					If SHOPITEM_ENABLED(Game\Interface\ShopMenu,i)=1 Then
						SetColor(25,255,25)
					Else
						SetColor(255,255,25)
					EndIf
				EndIf
				
				DrawRealText(SHOPITEM_NAME$(Game\Interface\ShopMenu,i), GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (10+25*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				SetColor(255,255,255)
				DrawImageEx(INTERFACE((Interface_Icons_Ability-1)+Game\Interface\ShopMenu), GAME_WINDOW_W-(250+15)*GAME_WINDOW_SCALE#, (10+25*i)*GAME_WINDOW_SCALE#, i-1)
			Next
			
			If Input\Pressed\ActionRoll Then Game\Interface\ShopMenuOption=Game\Interface\ShopMenu : Game\Interface\ShopMenu=0 : PlaySmartSound(Sound_MenuBack) 
			
			If Input\Pressed\ActionJump Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\ShopMenu
					Case SHOPMENU_SKILL
						If SHOPITEM_UNLOCKED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=1 Then
							If Menu\SkillEnabled=1 Then 
								If SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=1 Then
									SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=0
									Menu\SkillEnabled=0
								Else
									PlaySmartSound(Sound_MenuRefuse)
								EndIf
							Else
								Menu\SkillEnabled=1
								SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=Abs(SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)-1)
							EndIf
						Else
							Game\Interface\ShopMenuOption2=1
							Game\Interface\ShopMenu=Game\Interface\ShopMenu*10
						EndIf
					Case SHOPMENU_ABILITY
						If SHOPITEM_UNLOCKED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=1 Then
							If SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=0 Then
								If Menu\ActiveAbilities<5 Then 
									SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=Abs(SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)-1)
									Menu\ActiveAbilities=Menu\ActiveAbilities+1
								Else
									PlaySmartSound(Sound_MenuRefuse)
								EndIf	
									
							Else
								SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=Abs(SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)-1)
								Menu\ActiveAbilities=Menu\ActiveAbilities-1
							EndIf
							
							
								
						Else
							Game\Interface\ShopMenuOption2=1
							Game\Interface\ShopMenu=Game\Interface\ShopMenu*10
						EndIf
					Default
						If SHOPITEM_UNLOCKED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=1 Then
							SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)=Abs(SHOPITEM_ENABLED(Game\Interface\ShopMenu,Game\Interface\ShopMenuOption)-1)
						Else
							Game\Interface\ShopMenuOption2=1
							Game\Interface\ShopMenu=Game\Interface\ShopMenu*10
						EndIf
				End Select
				
				
			EndIf
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\ShopMenuOption=Game\Interface\ShopMenuOption+1
				If Game\Interface\ShopMenuOption>SHOPITEM_TOTAL(Game\Interface\ShopMenu) Then Game\Interface\ShopMenuOption=1
			EndIf
			
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\ShopMenuOption=Game\Interface\ShopMenuOption-1
				If Game\Interface\ShopMenuOption<1 Then Game\Interface\ShopMenuOption=SHOPITEM_TOTAL(Game\Interface\ShopMenu)
			EndIf
		Case 2
			
			SetColor(255,255,0)
			DrawRealText("Purchase "+SHOPITEM_NAME$(Game\Interface\ShopMenu/10,Game\Interface\ShopMenuOption)+"?" ,GAME_WINDOW_W/2, (10+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			
			If Input\Pressed\Up Or Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Select Game\Interface\ShopMenuOption2
					Case 1: Game\Interface\ShopMenuOption2=2
					Case 2: Game\Interface\ShopMenuOption2=1
				End Select
			EndIf
			
			If Input\Pressed\ActionRoll Then Game\Interface\ShopMenu=Game\Interface\ShopMenu/10 : PlaySmartSound(Sound_MenuBack)
			
			If Input\Pressed\ActionJump Then
				Select Game\Interface\ShopMenuOption2
					Case 1
						If Menu\Wallet<SHOPITEM_PRICE(Game\Interface\ShopMenu/10,Game\Interface\ShopMenuOption) Then
							PlaySmartSound(Sound_MenuRefuse)
						Else
							Menu\Wallet=Menu\Wallet-SHOPITEM_PRICE(Game\Interface\ShopMenu/10,Game\Interface\ShopMenuOption)
							PlaySmartSound(Sound_Achievement)
							PlaySmartSound(Sound_MenuAccept)
							
							Select Game\Interface\ShopMenu
								Case SHOPMENU_CONSUME,SHOPMENU_CONSUME_PURCHASE
									Select Game\Interface\ShopMenuOption
										Case CONSUME_LIFE
											Game\Gameplay\Lives=Game\Gameplay\Lives+1
										Case CONSUME_EMBLEM
											EMBLEMS=EMBLEMS+1
											For c=1 To CHAR_NORMALCOUNT
												If EMBLEMS>=TOUNLOCKCHAR[c] And TOUNLOCKCHAR[c]>0 And UNLOCKEDCHAR[c]=0 Then
													
													UNLOCKEDCHAR[c]=1 
													
												EndIf
											Next
											If EMBLEMS>=70 And UNLOCKEDSUPERS=0 Then
												
												UNLOCKEDSUPERS=1
												
											EndIf
										Case CONSUME_REDRING
											TOKENS=TOKENS+1
									End Select
								Default
									SHOPITEM_UNLOCKED((Game\Interface\ShopMenu/10),Game\Interface\ShopMenuOption)=1
									;SHOPITEM_ENABLED((Game\Interface\ShopMenu/10),Game\Interface\ShopMenuOption)=1
									
									
							End Select
							Game\Interface\ShopMenu=Game\Interface\ShopMenu/10
						EndIf
						
					Case 2
						Game\Interface\ShopMenu=Game\Interface\ShopMenu/10
						PlaySmartSound(Sound_MenuBack)
				End Select
				
			EndIf
			
			
			DrawArrow(GAME_WINDOW_W/2-15, (10+20*(4+Game\Interface\ShopMenuOption2))*GAME_WINDOW_SCALE#)
			DrawRealText("Yes" ,GAME_WINDOW_W/2, (10+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("No" ,GAME_WINDOW_W/2, (10+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
	End Select
	
End Function

;Function Interface_Render_Stage_SoundTest(p.tPlayer,d.tDeltaTime)
;	
;	DrawImageEx(INTERFACE(Interface_Background1), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
;	
;	DrawRealText("Sound Test", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
;	
;	DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
;	DrawRealText("Move", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
;	
;	DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
;	DrawRealText("Play", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
;	
;	DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
;	DrawRealText("Stop", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
;	
;	DrawRealText(Game\Interface\SoundTestOption, GAME_WINDOW_W-(370)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
;	DrawRealText(SOUNDS_NAME$(Game\Interface\SoundTestOption), GAME_WINDOW_W-(370)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
;	
;	If Input\Pressed\Right Then 
;		StopChannel(Game\Channel_SoundTest)
;		PlaySmartSound(Sound_MenuMove)
;		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption+1
;		If Game\Interface\SoundTestOption>SOUNDS_STAGETOTAL Then Game\Interface\SoundTestOption=SOUNDS_STAGETOTAL
;	EndIf
;	
;	If Input\Pressed\Left Then 
;		StopChannel(Game\Channel_SoundTest)
;		PlaySmartSound(Sound_MenuMove)
;		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption-1
;		If Game\Interface\SoundTestOption<Sound_Aim Then Game\Interface\SoundTestOption=Sound_Aim
;	EndIf
;	
;	If Input\Pressed\Up Then 
;		StopChannel(Game\Channel_SoundTest)
;		PlaySmartSound(Sound_MenuMove)
;		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption+10
;		If Game\Interface\SoundTestOption>SOUNDS_STAGETOTAL Then Game\Interface\SoundTestOption=SOUNDS_STAGETOTAL
;	EndIf
;	
;	If Input\Pressed\Down Then 
;		StopChannel(Game\Channel_SoundTest)
;		PlaySmartSound(Sound_MenuMove)
;		Game\Interface\SoundTestOption=Game\Interface\SoundTestOption-10
;		If Game\Interface\SoundTestOption<Sound_Aim Then Game\Interface\SoundTestOption=Sound_Aim
;	EndIf
;	
;	If Input\Pressed\ActionJump Then
;		StopChannel(Game\Channel_SoundTest)
;		Game\Channel_SoundTest = PlaySmartSound(Game\Interface\SoundTestOption)
;	EndIf
;	
;	If Input\Pressed\ActionRoll Then
;		
;		Game\Interface\SoundTest=0
;		StopChannel(Game\Channel_SoundTest)
;	EndIf
;	
;End Function	
	

;~IDEal Editor Parameters:
;~C#Blitz3D