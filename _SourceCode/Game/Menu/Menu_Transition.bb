Function Menu_DrawCardsTitleControls()
	If (Menu\ShowCards Or Menu\Transition>0) Then
		If Menu\Menu=MENU_BLACKMARKET# Then SetColor(210,28,28)
		For x=-30 To 30
		DrawImageEx(INTERFACE(Interface_Card1), GAME_WINDOW_W/2+x*24*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#+90)*GAME_WINDOW_SCALE#+30*(GAME_WINDOW_SCALE#-Float(1066)/640.0)*GAME_WINDOW_SCALE2#, 1)
		DrawImageEx(INTERFACE(Interface_Card1), GAME_WINDOW_W/2+x*24*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#+90)*GAME_WINDOW_SCALE#-30*(GAME_WINDOW_SCALE#-Float(1066)/640.0)*GAME_WINDOW_SCALE2#, 0)
		Next
		If Menu\Menu=MENU_BLACKMARKET# Then SetColor(255,255,255)
		If (Not(Menu\NewMenu=MENU_CLOSE# Or Menu\NewMenu=MENU_LOADING#)) Then
			Select Menu\Menu
				Case MENU_MAIN#:
					DrawRealText("Main Menu", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
				Case MENU_OPTIONS#:
					DrawRealText("Options", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 186, 165, 23)
				Case MENU_PLAY#,MENU_PLAYMARATHON#:
					DrawRealText("Select Mode", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
				Case MENU_MARATHON#:
					DrawRealText("Select Marathon", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 36, 81, 143)
				Case MENU_CHARACTERS#,MENU_CHARACTERS2#:
					Select Menu\Members
						Case 1:
							DrawRealText("Select Character", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
						Default:
							Select Menu\MemberToSelect
								Case 1:
									DrawRealText("Select Member 1", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
								Case 2:
									DrawRealText("Select Member 2", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
								Case 3:
									DrawRealText("Select Member 3", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
							End Select
					End Select
				Case MENU_TEAMS#:
					DrawRealText("Select Team", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
				Case MENU_BIOS#:
					DrawRealText("Gallery", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 196, 8, 8)
				Case MENU_STAGE#:
					DrawRealText("Select Stage", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 59, 153, 22)
				Case MENU_STAGE2#:
					DrawRealText("Select Stage", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 59, 153, 22)
				Case MENU_STAGESPECIAL#:
					DrawRealText("Select Special Stage", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 59, 153, 22)
				Case MENU_CREDITS#:
					DrawRealText("Credits", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 63, 63, 63)
				Case MENU_WELCOME#:
					DrawRealText("Welcome!", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
				Case MENU_BLACKMARKET#:
					Select Menu\Menu2
						Case MENU_BLACKMARKET_BUY#,MENU_BLACKMARKET_BUYLIST#,MENU_BLACKMARKET_BUYCONFIRM#,MENU_BLACKMARKET_BUYREFUSE#:
							DrawRealText("Shopping", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 196, 8, 8)
						Case MENU_BLACKMARKET_SELLLIST#,MENU_BLACKMARKET_SELLCONFIRM#,MENU_BLACKMARKET_SELLREFUSE#:
							DrawRealText("Trade", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 196, 8, 8)
						Default:
							DrawRealText("Black Market", GAME_WINDOW_W-12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 2, 0, 196, 8, 8)
					End Select
				Case MENU_TRANSPORTER#:
					Select Menu\Menu2
						Case MENU_TRANSPORTER_NAME#:
							DrawRealText("Name Machine", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
						Case MENU_TRANSPORTER_GOODBYE#:
							DrawRealText("Good-bye", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
						Case MENU_TRANSPORTER_INVENTORY#:
							DrawRealText("Inventory", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
						Case MENU_TRANSPORTER_STADIUM#,MENU_TRANSPORTER_RACEEXIT#,MENU_TRANSPORTER_RACES#,MENU_TRANSPORTER_KARATEEXIT#,MENU_TRANSPORTER_DIFFICULTY#:
							DrawRealText("Chao Stadium", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
						Default:
							DrawRealText("Chao Transporter", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
					End Select
				Case MENU_PRINCIPAL#:
					Select Menu\Menu2
						Default:
							DrawRealText("Principal's Room", 0+12*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-27.5)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 59, 153, 22)
					End Select
			End Select
			Select Menu\ControlsToShow
				Case MENU_MAIN#:
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Select", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_OPTIONS#:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyJump[Menu\Settings\PrimaryController#])
					DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyRoll[Menu\Settings\PrimaryController#])
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Done", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_VOLUME#*100:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyJump[Menu\Settings\PrimaryController#])
					DrawRealText("Less", GAME_WINDOW_W/2+(-10-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeySkill2[Menu\Settings\PrimaryController#])
					DrawRealText("More", GAME_WINDOW_W/2+(-10+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyRoll[Menu\Settings\PrimaryController#])
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Done", GAME_WINDOW_W/2+(-10+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_CONTROLS#*100,MENU_CONTROLS2#*100:
					If Menu\ButtonToChange=-1 Then
					If Not(Menu\ButtonIconChoice>0) Then
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyJump[Menu\Settings\PrimaryController#])
					DrawRealText("Clear", GAME_WINDOW_W/2+(-10-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeySkill2[Menu\Settings\PrimaryController#])
					DrawRealText("Choose", GAME_WINDOW_W/2+(-10+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyRoll[Menu\Settings\PrimaryController#])
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Done", GAME_WINDOW_W/2+(-10+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Else
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Choose", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					EndIf
					EndIf
				Case MENU_RESET#*100:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyJump[Menu\Settings\PrimaryController#])
					DrawRealText("All", GAME_WINDOW_W/2+(-10-75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeySkill2[Menu\Settings\PrimaryController#])
					DrawRealText("Select", GAME_WINDOW_W/2+(-10+75)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyRoll[Menu\Settings\PrimaryController#])
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+225)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_RESOLUTION#*100,MENU_SCREEN#*100,MENU_DEBUG#*100,MENU_DOF#*100,MENU_SHADOWS#*100,MENU_BLUR#*100,MENU_SRAYS#*100,MENU_SOUNDS#*100,MENU_THEME#*100,MENU_BUMPMAPS#*100,MENU_PLANTS#*100,MENU_VIEW#*100,MENU_AUTOCAM#*100,MENU_VSYNC#*100,MENU_MODS#*100,MENU_TIPS#*100:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyJump[Menu\Settings\PrimaryController#])
					DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, False, Menu\OptionsForceKeyRoll[Menu\Settings\PrimaryController#])
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_PLAY#,MENU_TEAMS#,MENU_MARATHON#,MENU_PLAYMARATHON#:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Move", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_CHARACTERS#:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					;CHANGEMEEE
					;DrawRealText("Roster", GAME_WINDOW_W/2+(-10-75-20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					;DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75+20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Select", GAME_WINDOW_W/2+(-10+75-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Change", GAME_WINDOW_W/2+(-10-225-22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225+22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+225-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_CHARACTERS2#:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Controls", GAME_WINDOW_W/2+(-10-75-20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75+20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Select", GAME_WINDOW_W/2+(-10+75-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Change", GAME_WINDOW_W/2+(-10-225-22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225+22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+225-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#+(7-1)*40*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#+(6-1)*40*GAME_WINDOW_SCALE#)
					DrawRealText("Page", GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#+(7-1)*40*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#+(6-1)*40*GAME_WINDOW_SCALE#-20*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
					If Menu_Character(Menu\Option,Menu\Option2)=CHAR_TAI Then
						DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#+(7-1)*40*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#+(6-2)*40*GAME_WINDOW_SCALE#)
						DrawRealText("Alt.", GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#+(7-1)*40*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#+(6-2)*40*GAME_WINDOW_SCALE#-20*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
					EndIf
				Case MENU_BIOS#:
					DrawSmartKey(INPUT_BUTTON_ACTIONACT, GAME_WINDOW_W/2-(40-75+2.5-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, True)
					DrawRealText("Animation", GAME_WINDOW_W/2+(-10-75-20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75+20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Super", GAME_WINDOW_W/2+(-10+75-2.5+15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Change", GAME_WINDOW_W/2+(-10-225-22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225+22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+225-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_STAGE#,MENU_STAGE2#:
					Select Menu\Menu
						Case MENU_STAGE2#:
							DrawRealText("Records", GAME_WINDOW_W/2+(-10-75-20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
							;changemee
;							DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W/2-(40-75+2.5)*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
;							DrawRealText("Randomize chars", GAME_WINDOW_W/2+(-10+75-2.5)*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
							DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, GAME_WINDOW_W/2-(40+225+22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-50)*GAME_WINDOW_SCALE#)
							DrawRealText("Randomize stage", GAME_WINDOW_W/2+(-10-225-22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-50)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Default:
							DrawRealText("Panel", GAME_WINDOW_W/2+(-10-75-20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
							
							;DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W/2-(40+225+22.5)*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
							;DrawRealText("Randomize chars", GAME_WINDOW_W/2+(-10-225-22.5)*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					End Select
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-75+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W/2-(40+75+20)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Select", GAME_WINDOW_W/2+(-10+75-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-225+2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Change", GAME_WINDOW_W/2+(-10-225-22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+225+22.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+225-2.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_STAGESPECIAL#:
					DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Change", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_CREDITS#:
					DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Back", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Website", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_GAMEOVER#,MENU_EMBLEM#,MENU_MARATHONEND#:
					DrawSmartKey(INPUT_BUTTON_START, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
					DrawRealText("Done", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case MENU_WELCOME#:
					Select Menu\OptionOrder
					Case 1:
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Default:
						DrawSmartKey(INPUT_BUTTON_START, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Done", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					End Select
				Case MENU_BLACKMARKET#:
					Select Menu\Menu2
					Case MENU_BLACKMARKET_EXITREAL#:
						;nothing
					Case MENU_BLACKMARKET_MAIN#:
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Default:
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Back", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					End Select
				Case MENU_TRANSPORTER#:
					Select Menu\Menu2
					Case MENU_TRANSPORTER_EXIT#,MENU_TRANSPORTER_RACEEXIT#,MENU_TRANSPORTER_KARATEEXIT#:
						;nothing
					Case MENU_TRANSPORTER_MAIN#,MENU_TRANSPORTER_STADIUM#:
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Default:
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Back", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					End Select
				Case MENU_PRINCIPAL#:
					Select Menu\Menu2
					Case MENU_PRINCIPAL_EXITREAL#:
						;nothing
					Case MENU_PRINCIPAL_MAIN#:
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Case MENU_PRINCIPAL_LESSONS#:
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Back", GAME_WINDOW_W/2+(-10+100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-100)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Default:
						DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, GAME_WINDOW_W/2-(40)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Select", GAME_WINDOW_W/2+(-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey(INPUT_BUTTON_ACTIONROLL, GAME_WINDOW_W/2-(40-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Move", GAME_WINDOW_W/2+(-10-200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						DrawSmartKey_MovementGeneral(GAME_WINDOW_W/2-(40+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#)
						DrawRealText("Back", GAME_WINDOW_W/2+(-10+200)*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#-20)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					End Select
			End Select
		EndIf
	Else
		Menu_TakeCards_Out()
	EndIf
End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_Transition(d.tDeltaTime)

	If BUTTON_PLACE1#>BUTTON_PLACE1_TARGET# Then BUTTON_PLACE1#=BUTTON_PLACE1#-15*d\Delta
	If BUTTON_PLACE1#<BUTTON_PLACE1_TARGET# Then BUTTON_PLACE1#=BUTTON_PLACE1#+15*d\Delta
	If BUTTON_PLACE2#>BUTTON_PLACE2_TARGET# Then BUTTON_PLACE2#=BUTTON_PLACE2#-15*d\Delta
	If BUTTON_PLACE2#<BUTTON_PLACE2_TARGET# Then BUTTON_PLACE2#=BUTTON_PLACE2#+15*d\Delta
	If CARD_PLACE#>CARD_PLACE_TARGET# Then CARD_PLACE#=CARD_PLACE#-5*d\Delta
	If CARD_PLACE#<CARD_PLACE_TARGET# Then CARD_PLACE#=CARD_PLACE#+5*d\Delta

	If Menu\Transition=1 Then
		Menu\FrozenMenuSaverTimer=Menu\FrozenMenuSaverTimer+timervalue#
		If Menu\FrozenMenuSaverTimer>1.675*secs# Then
			BUTTON_PLACE1#=BUTTON_PLACE1_TARGET#
			BUTTON_PLACE2#=BUTTON_PLACE2_TARGET#
			CARD_PLACE#=CARD_PLACE_TARGET#
		EndIf
	EndIf

	If Abs(BUTTON_PLACE1#-BUTTON_PLACE1_TARGET#)<ReturnFPSDifferenceFactor() And Abs(BUTTON_PLACE2#-BUTTON_PLACE2_TARGET#)<ReturnFPSDifferenceFactor() And Abs(CARD_PLACE#-CARD_PLACE_TARGET#)<ReturnFPSDifferenceFactor() Then
		BUTTON_PLACE1#=BUTTON_PLACE1_TARGET#
		BUTTON_PLACE2#=BUTTON_PLACE2_TARGET#
		CARD_PLACE#=CARD_PLACE_TARGET#
		Menu\TransitionMayPass=True
	Else
		Game\ControlLock=0.1*secs#
		Menu\TransitionMayPass=False
	EndIf

	If Menu\TransitionMayPass Then
		Select Menu\Transition
			Case 1: ;OUTGOING
				Menu\FrozenMenuSaverTimer=0
				If Menu\Menu2=0 And Menu\NewMenu2=0 Then
					Select Menu\Menu
						Case MENU_START#,MENU_WELCOME#:
							Menu_ResetCards_Mid() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_MAIN#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_OPTIONS#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_PLAY#,MENU_BLACKMARKET#,MENU_TRANSPORTER#,MENU_PRINCIPAL#,MENU_MARATHON#,MENU_PLAYMARATHON#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_RightOut()
						Case MENU_CHARACTERS#,MENU_CHARACTERS2#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_RightOut()
						Case MENU_BIOS#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_TEAMS#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_RightOut()
						Case MENU_STAGE#,MENU_STAGE2#,MENU_STAGESPECIAL#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_RightOut()
						Case MENU_CREDITS#,MENU_GAMEOVER#,MENU_EMBLEM#,MENU_MARATHONEND#:
							Menu_ResetCards_Mid() : Menu_ResetButtonPlace1_LeftOut()
					End Select
					Select Menu\NewMenu
						Case MENU_LOADING#:
							Menu_ResetCards_Mid() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_CLOSE#:
							Menu_ResetCards_Mid() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_START#,MENU_WELCOME#:
							Menu_ResetCards_Mid() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_CREDITS#,MENU_GAMEOVER#,MENU_EMBLEM#,MENU_MARATHONEND#:
							Menu_ResetCards_Mid() : Menu_ResetButtonPlace1_RightOut()
						Case MENU_BLACKMARKET#,MENU_PRINCIPAL#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_RightOut()
					End Select
				Else
					Select Menu\Menu
						Case MENU_OPTIONS#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace2_RightOut()
						Case MENU_BLACKMARKET#,MENU_TRANSPORTER#,MENU_PRINCIPAL#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_RightOut()
					End Select
				EndIf
				Select Menu\Settings\Theme#
					Case 5,30:
						Select Menu\NewMenu
							Case MENU_START#: Menu\RoundNewPos=0
							Case MENU_OPTIONS#: Menu\RoundNewPos=1
							Case MENU_MAIN#,MENU_CREDITS#: Menu\RoundNewPos=2
							Case MENU_BIOS#: Menu\RoundNewPos=3
							Case MENU_PLAY#,MENU_CHARACTERS#,MENU_CHARACTERS2#,MENU_TEAMS#,MENU_STAGE#,MENU_STAGE2#,MENU_STAGESPECIAL#,MENU_MARATHON#,MENU_PLAYMARATHON#: Menu\RoundNewPos=4
							Default: Menu\RoundNewPos=0
						End Select
						Menu\RoundTimer=0.4*secs#
				End Select
				Menu\Transition=2
			Case 2: ;INCOMING
				If Menu\Menu2=0 And Menu\NewMenu2=0 Then
					Select Menu\NewMenu
						Case MENU_LOADING#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_START#:
							Menu_ResetCards_Out() : Menu_ResetButtonPlace1_LeftOut()
						Case MENU_MAIN#,MENU_WELCOME#,MENU_TRANSPORTER#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_LeftOut() : Menu_ResetButtonPlace1_MidIn()
						Case MENU_OPTIONS#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_LeftIn()
						Case MENU_PLAY#,MENU_MARATHON#,MENU_PLAYMARATHON#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_LeftOut() : Menu_ResetButtonPlace1_MidIn()
						Case MENU_CHARACTERS#,MENU_CHARACTERS2#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_RightIn()
						Case MENU_BIOS#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_LeftOut() : Menu_ResetButtonPlace1_LeftIn()
						Case MENU_TEAMS#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_RightIn()
						Case MENU_STAGE#,MENU_STAGE2#,MENU_STAGESPECIAL#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_RightIn()
						Case MENU_CREDITS#,MENU_GAMEOVER#,MENU_EMBLEM#,MENU_MARATHONEND#:
							Menu_ResetCards_In() : Menu_ResetButtonPlace1_RightOut()
						Case MENU_BLACKMARKET#,MENU_PRINCIPAL#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_RightIn()
					End Select
				Else
					Select Menu\Menu
						Case MENU_OPTIONS#:
							Menu_ResetCards_In()
							If Menu\NewMenu2=0 Then
								Menu_ResetButtonPlace2_RightOut()
							Else
								Menu_ResetButtonPlace2_RightIn()
							EndIf
						Case MENU_BLACKMARKET#,MENU_PRINCIPAL#:
							Menu_ResetCards_In() : Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_RightIn()
						Case MENU_TRANSPORTER#:
							Menu_ResetCards_In()
							Select Menu\NewMenu2
								Case Menu_Transporter_Inventory#:
									Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_RightIn()
								Case Menu_Transporter_Goodbye#:
									Menu_TakeButton1Place_RightOut() : Menu_ResetButtonPlace1_MidIn()
								Default:
									Menu_TakeButton1Place_LeftOut() : Menu_ResetButtonPlace1_MidIn()
							End Select
					End Select
				EndIf
				Menu\Option=Menu\NewOption : Menu\Menu=Menu\NewMenu
				Menu\Option2=Menu\NewOption2 : Menu\Menu2=Menu\NewMenu2
				Select Menu\Menu
					Case MENU_PLAY#,MENU_MARATHON#,MENU_PLAYMARATHON#:
						Menu\ChaoGarden=0
					Case MENU_STAGE#,MENU_STAGE2#:
						If Menu\NewOption<0 Then Menu\NewOption=1
						Menu\LoadThumbnailAndMissions=True
						Menu\OptionOrder=0
						If Menu\Menu=MENU_STAGE2# Then
							Menu\Option=(Menu\NewOption Mod 12)
							If Menu\Option=0 Then Menu\Option=12
							Menu\OptionOrder2=(Ceil(Menu\NewOption/12.0)-1)*3
							Menu_Stage_LoadStageSelectThumbnails()
						Else
							Menu\Option=Menu\NewOption
						EndIf
				End Select
				Select Menu\Menu
					Case MENU_START#,MENU_GAMEOVER#,MENU_CREDITS#,MENU_BIOS#,MENU_EMBLEM#,MENU_MARATHONEND#: Menu\DontReplayMusic=1
					Default: Menu\DontReplayMusic=0
				End Select
				If Menu\WentToChaoMenu=0 Then Menu\MeshChange=1
				Menu\Transition=3
			Case 3:
				Menu\Transition=0
		End Select
	EndIf

End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function Menu_RoundTransition(d.tDeltaTime)

	If Menu\RoundPos<>Menu\RoundNewPos Then
		If Menu\RoundTimer>0 Then Menu\RoundTimer=Menu\RoundTimer-timervalue# Else Menu\RoundPos=Menu\RoundNewPos
		Menu\RoundSize#=Menu\RoundSize#+0.25*d\Delta
	Else
		If Menu\RoundSize#>1 Then Menu\RoundSize#=Menu\RoundSize#-0.25*d\Delta
	EndIf
	If Menu\RoundSize#<1 Then Menu\RoundSize#=1

	Select Menu\Menu
	Case MENU_LOADING#,MENU_CREDITS#,MENU_GAMEOVER#,MENU_WELCOME#,MENU_EMBLEM#,MENU_BLACKMARKET#,MENU_PRINCIPAL#,MENU_MARATHONEND#: ;do nothing
	Default:
		SetScale(GAME_WINDOW_SCALE#*Menu\RoundSize#, GAME_WINDOW_SCALE#*Menu\RoundSize#)
		Select Menu\RoundPos
			Case 0: DrawImageEx(INTERFACE(Interface_Round), GAME_WINDOW_W/2+000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+000*GAME_WINDOW_SCALE#)
			Case 1: DrawImageEx(INTERFACE(Interface_Round), GAME_WINDOW_W/2-240*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#)
			Case 2: DrawImageEx(INTERFACE(Interface_Round), GAME_WINDOW_W/2+240*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#)
			Case 3: DrawImageEx(INTERFACE(Interface_Round), GAME_WINDOW_W/2-240*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+150*GAME_WINDOW_SCALE#)
			Case 4: DrawImageEx(INTERFACE(Interface_Round), GAME_WINDOW_W/2+240*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+150*GAME_WINDOW_SCALE#)
		End Select
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	End Select

End Function

;-----------------------------------------------------------------------------------------------------------------------------------------

Type tMenu_Bubble
	Field x#
	Field y#
	Field color
	Field speed#
	Field rot#
End Type

Function Menu_Bubble_Create.tMenu_Bubble()
	bubble.tMenu_Bubble = New tMenu_Bubble
	bubble\x# = Rand(0,GAME_WINDOW_W)
	bubble\y# = GAME_WINDOW_H+Rand(0,500)
	bubble\Color = Rand(1,6)
	Select(Rand(1,4))
		Case 1,2,3: bubble\speed# = 5
		Case 4: bubble\speed# = 2.5
	End Select
	bubble\rot#=Rand(1,360)
	Return bubble
End Function

Function Menu_Bubble_Update(bubble.tMenu_Bubble, d.tDeltaTime)
	SetAlpha(0.6)
	If Menu\Settings\Theme#=27 Then SetRotation(bubble\rot#)
	DrawImageEx(INTERFACE(Interface_Bubble_1+bubble\Color-1), bubble\x#, bubble\y#)
	SetRotation(0)
	SetAlpha(1)
	If bubble\y#>-500 Then bubble\y#=bubble\y#-bubble\speed#*d\Delta Else Delete bubble
End Function

Function Menu_FloatingBubbles(d.tDeltaTime)
	If Not(Menu\BubbleCreatorTimer>0) Then
		Menu\BubbleCreatorTimer=Rand(1,2)*secs#
		For i=1 To 14 : Menu_Bubble_Create.tMenu_Bubble() : Next
	Else
		Menu\BubbleCreatorTimer=Menu\BubbleCreatorTimer-timervalue#
	EndIf
	For bubble.tMenu_Bubble=Each tMenu_Bubble : Menu_Bubble_Update(bubble,d) : Next
End Function

Function Menu_DeleteFloatingBubbles()
	For bubble.tMenu_Bubble=Each tMenu_Bubble : Delete bubble : Next
End Function

Function Menu_CharScroll(d.tDeltaTime)
	Select Menu\Menu
		Case MENU_BIOS#: If Not(Menu\CharScrollFadeMode=12) Then Menu\CharScrollFadeMode=2
		Default: If Not(Menu\CharScrollFadeMode=11) Then Menu\CharScrollFadeMode=1
	End Select

	Select Menu\CharScrollFadeMode
		Case 1,0: Menu\CharScrollFadeMode=11
		Case 2: Menu\CharScrollFadeMode=12
		Case 11: If Menu\CharScrollFade#<1 Then Menu\CharScrollFade#=Menu\CharScrollFade#+0.25*d\Delta
		Case 12: If Menu\CharScrollFade#>0 Then Menu\CharScrollFade#=Menu\CharScrollFade#-0.25*d\Delta
	End Select

	If Menu\CharScrollY#<=-7800 Then
		Menu\CharScrollY#=GAME_WINDOW_H
	Else
		Menu\CharScrollY#=Menu\CharScrollY#-5*d\Delta
	EndIf

	SetAlpha(0.20*Menu\CharScrollFade#)
	DrawImageEx(INTERFACE(Interface_Background2), GAME_WINDOW_W/2, GAME_WINDOW_H/2+1800+Menu\CharScrollY#/2)
	SetAlpha(1)
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D