
Const DEBUGMENU_MAIN# = 0

Const DEBUGMENU_CHOOSE# = 1
Const DEBUGMENU_CHOOSE_COLLECTIBLES# = 11
Const DEBUGMENU_CHOOSE_TRANSLATORS# = 12
Const DEBUGMENU_CHOOSE_TRANSLATORS_2# = 121
Const DEBUGMENU_CHOOSE_TRANSLATORS_3# = 122
Const DEBUGMENU_CHOOSE_TRANSLATORS_4# = 123
Const DEBUGMENU_CHOOSE_MONITORS# = 13
Const DEBUGMENU_CHOOSE_MONITORS_2# = 131
Const DEBUGMENU_CHOOSE_BOXES# = 14
Const DEBUGMENU_CHOOSE_BOXES_2# = 141
Const DEBUGMENU_CHOOSE_ENEMIES# = 15
Const DEBUGMENU_CHOOSE_ENEMIES_2# = 151
Const DEBUGMENU_CHOOSE_SPIKES# = 16
Const DEBUGMENU_CHOOSE_HAZARDS# = 17
Const DEBUGMENU_CHOOSE_MISC# = 18
Const DEBUGMENU_CHOOSE_MISC_2# = 181
Const DEBUGMENU_CHOOSE_TRIGGERS# = 19
Const DEBUGMENU_CHOOSE_TREES# = 20
Const DEBUGMENU_CHOOSE_TREES_2# = 201
Const DEBUGMENU_CHOOSE_VISUALS# = 21
Const DEBUGMENU_CHOOSE_VISUALS_2# = 211
Const DEBUGMENU_CHOOSE_VISUALS_3# = 212
Const DEBUGMENU_CHOOSE_CHAO# = 22

Const DEBUGMENU_ATTRIBUTES# = 2
Const DEBUGMENU_ATTRIBUTES_POSITION# = 51
Const DEBUGMENU_ATTRIBUTES_ROTATION# = 52
Const DEBUGMENU_ATTRIBUTES_POWER# = 53
Const DEBUGMENU_ATTRIBUTES_LOCKS# = 54
Const DEBUGMENU_ATTRIBUTES_CAMPOSITION# = 55
Const DEBUGMENU_ATTRIBUTES_CAMROTATION# = 56
Const DEBUGMENU_ATTRIBUTES_CAMZOOM# = 57
Const DEBUGMENU_ATTRIBUTES_CAMSPEED# = 58
Const DEBUGMENU_ATTRIBUTES_AMOUNT# = 59
Const DEBUGMENU_ATTRIBUTES_AMOUNTROTATION# = 60
Const DEBUGMENU_ATTRIBUTES_AMOUNTSPACE# = 61
Const DEBUGMENU_ATTRIBUTES_SWITCH# = 62
Const DEBUGMENU_ATTRIBUTES_SWITCHSTATUS# = 63
Const DEBUGMENU_ATTRIBUTES_TELEPORTER# = 64
Const DEBUGMENU_ATTRIBUTES_DESTINATION# = 65

Const DEBUGMENU_PLACE# = 3
Function UpdateDebugTag()
Select Game\Interface\DebugMenu
	Case 51 : Menu\DebugTag$=" : POSITION"
	Case 52 : Menu\DebugTag$=" : ROTATION"
	Case 53 : Menu\DebugTag$=" : POWER"
	Case 54 : Menu\DebugTag$=" : LOCKS"
	Case 55 : Menu\DebugTag$=" : CAM POSITION"
	Case 56 : Menu\DebugTag$=" : CAM ROTATION"
	Case 57 : Menu\DebugTag$=" : CAM ZOOM"
	Case 58 : Menu\DebugTag$=" : CAM SPEED"
End Select 
End Function 
Function Interface_Render_Stage_Debug(p.tPlayer)
	If (KeyHit(KEY_ESCAPE)) Then End 
	
	UpdateDebugTag()
	
	For i=1 To 4
		If KeyHit(Key_+i) Then
			Game\Interface\DebugMenu=49+i
		EndIf 
	Next 
	
	DrawRealText("DEBUG MODE"+Menu\DebugTag$, 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)

	Select Game\Interface\DebugMenu
		Case DEBUGMENU_MAIN#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Choose Obj", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Attributes", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Place Obj", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>3 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=3
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				Select Game\Interface\DebugMenuOption
					Case 3:
						Game\Interface\DebugSavedTimer=0
						PlaySmartSound(Sound_MenuAccept)
						Game\Interface\DebugMenu=Game\Interface\DebugMenuOption
						Game\Interface\DebugMenuOption=1
					Default:
						PlaySmartSound(Sound_MenuAccept)
						Game\Interface\DebugMenu=Game\Interface\DebugMenuOption
						Game\Interface\DebugMenuOption=1
				End Select
			EndIf

			If Input\Pressed\ActionAct Then
				FlushAll()
				Game\Interface\DebugEnteredAttributeTimer=0.5*secs#
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=DEBUGMENU_ATTRIBUTES_POSITION#
				Game\Interface\DebugMenuOption=1
			EndIf

			If Input\Pressed\ActionSkill2 And TempAttribute\hasd#=1 Then
				PositionEntity p\Objects\Entity, TempAttribute\dx#, TempAttribute\dy#, TempAttribute\dz#
			EndIf
		Case DEBUGMENU_CHOOSE#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Collectibles", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Translators", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Monitors", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Boxes", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Enemies", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spikes", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Hazards", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Miscellaneous", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Triggers", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Plants", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Visuals", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			If Menu\ChaoGarden=1 Then DrawRealText("Chao Garden", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				Select Menu\ChaoGarden
					Case 0: If Game\Interface\DebugMenuOption>11 Then Game\Interface\DebugMenuOption=1
					Case 1: If Game\Interface\DebugMenuOption>12 Then Game\Interface\DebugMenuOption=1
				End Select
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				Select Menu\ChaoGarden
					Case 0: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=11
					Case 1: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=12
				End Select
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=Game\Interface\DebugMenuOption+10
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenu=DEBUGMENU_MAIN#
				Game\Interface\DebugMenuOption=1
			EndIf
		Case DEBUGMENU_CHOOSE_COLLECTIBLES#:
			DrawArrow(GAME_WINDOW_W-(200+43+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Ring", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Emerald Shard", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Goal", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Goal switch", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Goal prog.", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Goal switch prog.", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bell", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Counter", GAME_WINDOW_W-(200+43)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>8 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=8
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_RING
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_SHARD
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_GOAL
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_GOAL2
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_GOAL+1000
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_GOAL2+1000
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_BELL
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_COUNTER
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TRANSLATORS#:
			DrawArrow(GAME_WINDOW_W-(225+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Spring", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Big Spring", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Air Spring", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ice Spring", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Dash Pad", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Dash Ramp", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Dash Hoop", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Trick Hoop", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Accelerator", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Locker", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Forcer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Node", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Fan", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Big Fan", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Big Fan low", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bumpers", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Cannon", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*15)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Transferers", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*16)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>18 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=18
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_SPRING
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_BSPRING
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_SPRINGX
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_SPRINGICE
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_PAD
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_RAMP
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_HOOP
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_THOOP
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_ACCEL
					Case 10: Game\Interface\DebugNewObj = OBJTYPE_LOCKER
					Case 11: Game\Interface\DebugNewObj = OBJTYPE_FORCER
					Case 12: Game\Interface\DebugNewObj = OBJTYPE_NODE
					Case 13: Game\Interface\DebugNewObj = OBJTYPE_FAN
					Case 14: Game\Interface\DebugNewObj = OBJTYPE_BFAN
					Case 15: Game\Interface\DebugNewObj = OBJTYPE_BFANLOW
					Case 16: Game\Interface\DebugNewObj = 0
					Case 17: Game\Interface\DebugNewObj = OBJTYPE_CANNON
					Case 18: Game\Interface\DebugNewObj = 0
				End Select
				Select Game\Interface\DebugMenuOption
					Case 10,11,13,14,15:
						Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
						Game\Interface\DebugMenuOption=1
					Case 16:
						Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+2
						Game\Interface\DebugMenuOption=1
					Case 18:
						Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+3
						Game\Interface\DebugMenuOption=1
					Default:
						Game\Interface\DebugMenu=0
						Game\Interface\DebugMenuOption=1
				End Select
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TRANSLATORS_2#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*4+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			Select Game\Interface\DebugNewObj
				Case OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW:
					DrawRealText("Normal", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Invisible", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_LOCKER,OBJTYPE_FORCER:
					DrawRealText("Normal", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Big", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					If Game\Interface\DebugNewObj=OBJTYPE_LOCKER Then DrawRealText("Bigger", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				Select Game\Interface\DebugNewObj
					Case OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW: If Game\Interface\DebugMenuOption>2 Then Game\Interface\DebugMenuOption=1
					Case OBJTYPE_LOCKER: If Game\Interface\DebugMenuOption>3 Then Game\Interface\DebugMenuOption=1
					Case OBJTYPE_FORCER: If Game\Interface\DebugMenuOption>2 Then Game\Interface\DebugMenuOption=1
				End Select
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				Select Game\Interface\DebugNewObj
					Case OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=2
					Case OBJTYPE_LOCKER: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=3
					Case OBJTYPE_FORCER: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=2
				End Select
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj
					Case 2: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj+1000
					Case 3: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj+2000
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugNewObj=0
				Game\Interface\DebugMenuOption=DEBUGMENU_CHOOSE_TRANSLATORS#-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TRANSLATORS_3#:
			DrawArrow(GAME_WINDOW_W-(225+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Ball Bumper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ground Bumper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Metro Bumper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Plate Bumper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Triangle Bumper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Paddle", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Cloud", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Pole", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>8 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=8
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_BALLBUMPER
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_GROUNDBUMPER
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_METROBUMPER
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_PLATEBUMPER
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_TRIANGLEBUMPER
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_PADDLE
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_CLOUD
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_POLE
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugNewObj=0
				Game\Interface\DebugMenuOption=DEBUGMENU_CHOOSE_TRANSLATORS#-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TRANSLATORS_4#:
			DrawArrow(GAME_WINDOW_W-(225+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Propeller", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Pulley", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Pulley inverted", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Rocket", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Elevator", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Handle", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ground Jump Panel", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Wall Jump Panel", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>8 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=8
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_PROPELLER
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_PULLEY
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_PULLEY+1000
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_ROCKET
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_ELEVATOR
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_HANDLE
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_PANEL1
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_PANEL2
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugNewObj=0
				Game\Interface\DebugMenuOption=DEBUGMENU_CHOOSE_TRANSLATORS#-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_MONITORS#:
			DrawArrow(GAME_WINDOW_W-(225+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Rings", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Life", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Trap", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Invincibility", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Speed Shoes", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Normal Shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Flame Shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bubble Shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Thunder Shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Earth Shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bomb", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Board", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Glider", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Car", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bike", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bobsleigh", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Tornado", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*15)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Cyclone", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*16)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Kart", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*17)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Wings", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*18)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>20 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=20
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_RINGS
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_LIFE
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_TRAP
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_INVINC
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_SHOES
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_NSHIELD
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_FSHIELD
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_BSHIELD
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_TSHIELD
					Case 10: Game\Interface\DebugNewObj = OBJTYPE_ESHIELD
					Case 11: Game\Interface\DebugNewObj = OBJTYPE_BOMB
					Case 12: Game\Interface\DebugNewObj = OBJTYPE_BOARD
					Case 13: Game\Interface\DebugNewObj = OBJTYPE_GLIDER
					Case 14: Game\Interface\DebugNewObj = OBJTYPE_CAR
					Case 15: Game\Interface\DebugNewObj = OBJTYPE_BIKE
					Case 16: Game\Interface\DebugNewObj = OBJTYPE_BOBSLEIGH
					Case 17: Game\Interface\DebugNewObj = OBJTYPE_TORNADO
					Case 18: Game\Interface\DebugNewObj = OBJTYPE_CYCLONE
					Case 19: Game\Interface\DebugNewObj = OBJTYPE_KART
					Case 20: Game\Interface\DebugNewObj = OBJTYPE_WINGS
				End Select
				Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_MONITORS_2#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Capsule", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Balloon", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>2 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=2
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj
					Case 2: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj+1000
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugNewObj=0
				Game\Interface\DebugMenuOption=DEBUGMENU_CHOOSE_MONITORS#-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_BOXES#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Wooden", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Metal", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Iron", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Cage", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Vanish", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Light", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Explosive", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Nitro", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Float", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>9 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=9
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_BOXWOODEN
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_BOXMETAL
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_BOXIRON
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_BOXCAGE
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_BOXYELLOW
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_BOXLIGHT
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_BOXTNT
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_BOXNITRO
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_BOXFLOAT
				End Select
				Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_BOXES_2#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Normal", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Big", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Medium", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>3 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=3
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj
					Case 2: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj+1000
					Case 3: Game\Interface\DebugNewObj = Game\Interface\DebugNewObj+2000
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugNewObj=0
				Game\Interface\DebugMenuOption=DEBUGMENU_CHOOSE_BOXES#-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_ENEMIES#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Pawn", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Flapper", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spina", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Classic", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Classic 2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Classic 3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Classic 4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("G.U.N.", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Modern", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Nonrobot", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Boss", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>11 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=11
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugNewObj = Game\Interface\DebugMenuOption
				Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_ENEMIES_2#:
			Select Game\Interface\DebugNewObj
				Case 9:
					DrawArrow(GAME_WINDOW_W-(235+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
				Case 10:
					DrawArrow(GAME_WINDOW_W-(282.5+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
				Case 11:
					DrawArrow(GAME_WINDOW_W-(245+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
				Default:
					DrawArrow(GAME_WINDOW_W-(225+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			End Select
			Select Game\Interface\DebugNewObj
				Case 1:
					DrawRealText("Pawn", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Pawn shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Pawn gun", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Pawn sword", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cameron", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Hammer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Hammer shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Hammer hammer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Bishop", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Magician", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 2:
					DrawRealText("Flapper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Flapper gun", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Flapper bomb", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Flapper needle", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Klagen", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cannon 1", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cannon 2", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cannon 3", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 3:
					DrawRealText("Spina", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Spana", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Spona", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 4:
					DrawRealText("Motobug", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Caterkiller", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Buzz Bomber", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Buzzer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Chopper", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Crabmeat", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Jaws", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Spiny", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Grabber", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Kiki", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cop Speeder", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("EggRobo", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Orbinaut", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 5:
					DrawRealText("Anton", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Aquis", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Bombie", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Newtron", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Penguinator", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Slicer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Snail Blaster", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Spikes", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Asteron", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Batbot", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Bubbles", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Bubbles spikes", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Steelion", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 6:
					DrawRealText("Balkiry", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Burrobot", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Crawl", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Dragonfly", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Madmole", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Manta", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Mushmeanie", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Octus", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Pata-Bata", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Zoomer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Ball Hog", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Rhinotank", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("TechnoSqueek", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 7:
					DrawRealText("E-1000", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cop Racer", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Catakiller Jr.", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Cluckoid", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Mantis", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Nebula", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Roller", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Snowy", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Splats", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Toxomister", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Bomber 1", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Bomber 2", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 8:
					DrawRealText("Hunter", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Hunter shield", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Beetle", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Beetle mono", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Beetle spark", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Beetle spring", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("A. Chaos", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("A. Chaos blob", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Rhino", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Rhino spikes", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Hornet with 3", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Hornet with 6", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 9:
					DrawRealText("Egg Gunner", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Egg Searcher", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Egg Hunter", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Aero Cannon", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Chaser", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Fighter", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Typhoon", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Typhoon blizzard", GAME_WINDOW_W-(235)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					
				Case 10:
					DrawRealText("Boo", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Boo scare", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Iblis Biter", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Iblis Crawler", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Iblis Taker", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Black Warrior", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Black Warrior gun 1", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Black Warrior gun 2", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Black Oak sword", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Black Leech", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Black Wing", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("G.U.N. Soldier", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("G.U.N. Soldier camo", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Sheep", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Ghost Pumpkin", GAME_WINDOW_W-(282.5)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 11:
					DrawRealText("Rival", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Rival run", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Eggman", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Eggman 2", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Eggman run", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Beta mk.2", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("Mecha Sonic run", GAME_WINDOW_W-(245)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				Select Game\Interface\DebugNewObj
					Case 1: If Game\Interface\DebugMenuOption>10 Then Game\Interface\DebugMenuOption=1
					Case 2: If Game\Interface\DebugMenuOption>8 Then Game\Interface\DebugMenuOption=1
					Case 3: If Game\Interface\DebugMenuOption>3 Then Game\Interface\DebugMenuOption=1
					Case 4: If Game\Interface\DebugMenuOption>13 Then Game\Interface\DebugMenuOption=1
					Case 5: If Game\Interface\DebugMenuOption>13 Then Game\Interface\DebugMenuOption=1
					Case 6: If Game\Interface\DebugMenuOption>13 Then Game\Interface\DebugMenuOption=1
					Case 7: If Game\Interface\DebugMenuOption>12 Then Game\Interface\DebugMenuOption=1
					Case 8: If Game\Interface\DebugMenuOption>12 Then Game\Interface\DebugMenuOption=1
					Case 9: If Game\Interface\DebugMenuOption>8 Then Game\Interface\DebugMenuOption=1
					Case 10: If Game\Interface\DebugMenuOption>15 Then Game\Interface\DebugMenuOption=1
					Case 11: If Game\Interface\DebugMenuOption>7 Then Game\Interface\DebugMenuOption=1
				End Select
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				Select Game\Interface\DebugNewObj
					Case 1: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=10
					Case 2: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=8
					Case 3: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=3
					Case 4: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=13
					Case 5: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=13
					Case 6: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=13
					Case 7: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=12
					Case 8: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=12
					Case 9: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=8
					Case 10: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=15
					Case 11: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=7
				End Select
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugNewObj
					Case 1:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_PAWN
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_PAWNSHIELD
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_PAWNGUN
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_PAWNSWORD
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_CAMERON
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_HAMMER
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_HAMMERSHIELD
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_HAMMERHAMMER
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_WITCH1
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_WITCH2
						End Select
					Case 2:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_FLAPPER
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_FLAPPERGUN
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_FLAPPERBOMB
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_FLAPPERNEEDLE
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_KLAGEN
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_FCANNON1
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_FCANNON2
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_FCANNON3
						End Select
					Case 3:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_SPINA
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_SPANA
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SPONA
						End Select
					Case 4:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_MOTOBUG
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_CATERKILLER
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BUZZBOMBER
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_BUZZER
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_CHOPPER
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_CRABMEAT
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_JAWS
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_SPINY
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_GRABBER
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_KIKI
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_COP
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_EGGROBO
							Case 13: Game\Interface\DebugNewObj = OBJTYPE_ORBINAUT
						End Select
					Case 5:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_ANTON
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_AQUIS
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BOMBIE
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_NEWTRON
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_PENGUINATOR
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_SLICER
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_SNAILB
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_SPIKES
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_ASTERON
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_BATBOT
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_BUBBLS
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_BUBBLSSPIKES
							Case 13: Game\Interface\DebugNewObj = OBJTYPE_STEELION
						End Select
					Case 6:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_BALKIRY
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_BURROBOT
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_CRAWL
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_DRAGONFLY
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_MADMOLE
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_MANTA
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_MUSHMEANIE
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_OCTUS
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_PATABATA
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_ZOOMER
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_BALLHOG
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_RHINOTANK
							Case 13: Game\Interface\DebugNewObj = OBJTYPE_TECHNOSQU
						End Select
					Case 7:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_E1000
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_COPRACER
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_CATAKILLER
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_CLUCKOID
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_MANTIS
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_NEBULA
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_ROLLER
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_SNOWY
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_SPLATS
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_TOXO
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_BOMBER1
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_BOMBER2
						End Select
					Case 8:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_HUNTER
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_HUNTERSHIELD
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BEETLE
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_BEETLEMONO
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_BEETLESPARK
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_BEETLESPRING
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_ACHAOS
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_ACHAOSBLOB
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_RHINO
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_RHINOSPIKES
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_HORNET3
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_HORNET6
						End Select
					Case 9:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_GUNNER
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_EGGHUNTER
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SEARCHER
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_AEROC
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_CHASER
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_FIGHTER
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_TYPHOON
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_TYPHOONF
								
						End Select
					Case 10:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_BOO
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_BOOSCARE
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BITER
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_CRAWLER
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_TAKER
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_WARRIOR
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_WARRIORGUN1
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_WARRIORGUN2
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_OAKSWORD
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_LEECH
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_WING
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_SOLDIER
							Case 13: Game\Interface\DebugNewObj = OBJTYPE_SOLDIERCAMO
							Case 14: Game\Interface\DebugNewObj = OBJTYPE_SHEEP
							Case 15: Game\Interface\DebugNewObj = OBJTYPE_GHOST
						End Select
					Case 11:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = -1
							Case 2: Game\Interface\DebugNewObj = -2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BOSS
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_BOSS2
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_BOSSRUN
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_BOSSBETA
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_BOSSMECHA
						End Select
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugNewObj
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_ENEMIES#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_SPIKES#:
			DrawArrow(GAME_WINDOW_W-(265+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Spike Ball", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Bomb", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Crusher", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Drills", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Drills timed", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Drills trap", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Bar", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Bar wide", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Bar wider", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Swing with 1", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Swing with 2", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Swing with 3", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Swing Ball", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Spike Cylinder", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>14 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=14
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_SPIKEBALL
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_SPIKEBOMB
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_SPIKECRUSHER
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_SPIKEDRILL
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_SPIKETIMED
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_SPIKETRAP
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_SPIKEBAR
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_SPIKEBAR+1000
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_SPIKEBAR+2000
					Case 10: Game\Interface\DebugNewObj = OBJTYPE_SPIKESWING
					Case 11: Game\Interface\DebugNewObj = OBJTYPE_SPIKESWING+1000
					Case 12: Game\Interface\DebugNewObj = OBJTYPE_SPIKESWING+2000
					Case 13: Game\Interface\DebugNewObj = OBJTYPE_SPIKESWINGBALL
					Case 14: Game\Interface\DebugNewObj = OBJTYPE_SPIKECYLINDER
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_HAZARDS#:
			DrawArrow(GAME_WINDOW_W-(230+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Flame Spout", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ice Spout", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Shock Spout", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Laser vertl.", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Laser horzl.", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ring gate vertl.", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ring gate horzl.", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Rock brown", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Rock grey", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Crystal", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Trap Spring", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Air Trap Spring", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Thorn Spring", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Explosion", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Explosion rocket", GAME_WINDOW_W-(230)*GAME_WINDOW_SCALE#, (40+20*15)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>15 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=15
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_FLAMESPOUT
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_ICESPOUT
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_SHOCKSPOUT
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_LASERV
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_LASERH
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_RINGGATEV
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_RINGGATEH
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_ROCK
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_ROCK+1000
					Case 10: Game\Interface\DebugNewObj = OBJTYPE_CRYSTAL
					Case 11: Game\Interface\DebugNewObj = OBJTYPE_SPRINGTRAP
					Case 12: Game\Interface\DebugNewObj = OBJTYPE_SPRINGTRAPX
					Case 13: Game\Interface\DebugNewObj = OBJTYPE_SPRINGTHORN
					Case 14: Game\Interface\DebugNewObj = OBJTYPE_EXPLOSION
					Case 15: Game\Interface\DebugNewObj = OBJTYPE_EXPLOSION2
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_MISC#:
			DrawArrow(GAME_WINDOW_W-(250+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Checkpoint size 1", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Checkpoint size 2", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Checkpoint size 3", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Balloon", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Breath Bubbles", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Teleporter", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Teleporter HUB", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Teleporter end", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Omochao", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Switch", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Switch base", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Switch top", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Switch Water", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Switch Water inv.", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Switch Air", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Hint", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Signs", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*15)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Falling platform", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*16)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Capsule", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*17)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>19 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=19
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_CHECK
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_CHECK+1000
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_CHECK+2000
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_BALLOON
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_BUBBLES
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER2
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTEREND
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_OMOCHAO
					Case 10: Game\Interface\DebugNewObj = OBJTYPE_SWITCH
					Case 11: Game\Interface\DebugNewObj = OBJTYPE_SWITCHBASE
					Case 12: Game\Interface\DebugNewObj = OBJTYPE_SWITCHTOP
					Case 13: Game\Interface\DebugNewObj = OBJTYPE_SWITCHWATER
					Case 14: Game\Interface\DebugNewObj = OBJTYPE_SWITCHWATER+1000
					Case 15: Game\Interface\DebugNewObj = OBJTYPE_SWITCHAIR
					Case 16: Game\Interface\DebugNewObj = OBJTYPE_HINT
					Case 17: Game\Interface\DebugNewObj = OBJTYPE_SIGN
					Case 18: Game\Interface\DebugNewObj = OBJTYPE_FPLAT
					Case 19: Game\Interface\DebugNewObj = OBJTYPE_CAPSULE
				End Select
				Select Game\Interface\DebugMenuOption
					Case 17: Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
					Default: Game\Interface\DebugMenu=0
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_MISC_2#:
			DrawArrow(GAME_WINDOW_W-(250+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Sign fall", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Sign up", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Sign down", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Sign left", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Sign right", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>5 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=5
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_SIGN+0000
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_SIGN+1000
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_SIGN+2000
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_SIGN+3000
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_SIGN+4000
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=17
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_MISC#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TRIGGERS#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Vehicle Cancel", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Mach", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Mach Cancel", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Skydive", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Skydive Cancel", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Water", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Music 1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Music 2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Music 3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>9 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=9
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_VEHICLECANCEL
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_MACH
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_MACHCANCEL
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_SKYDIVE
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_SKYDIVECANCEL
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_WATER
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_MUSIC
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_MUSIC+1000
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_TRIGGER_MUSIC+2000
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_CHAO#:
			DrawArrow(GAME_WINDOW_W-(255+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Tropical Tree", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Black Market", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Transporter", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Chao Stadium", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Principal Room", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Trash can", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Sack", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Garden start point", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>8 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=8
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_TROPICAL
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER3
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER4
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER5
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER6
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_TRASHCAN
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_SACK
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_GARDENPOINT
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TREES#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Tree", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Shrub", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Bush", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Grass", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Sakura", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Palm", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Wild Palm", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Flower", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Snowy", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Vine", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Dry Tree", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Adabat", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>12 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=12
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugNewObj = Game\Interface\DebugMenuOption
				Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TREES_2#:
			DrawArrow(GAME_WINDOW_W-(200+15)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			Select Game\Interface\DebugNewObj
				Case 1:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 2:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 3:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("7", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 4:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("7", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("8", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("9", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("10", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 5:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 6:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 7:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 8:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 9:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("6", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 10:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 11:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 12:
					DrawRealText("1", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("2", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("3", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("4", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					DrawRealText("5", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				Select Game\Interface\DebugNewObj
					Case 1: If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
					Case 2: If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
					Case 3: If Game\Interface\DebugMenuOption>7 Then Game\Interface\DebugMenuOption=1
					Case 4: If Game\Interface\DebugMenuOption>10 Then Game\Interface\DebugMenuOption=1
					Case 5: If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
					Case 6: If Game\Interface\DebugMenuOption>4 Then Game\Interface\DebugMenuOption=1
					Case 7: If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
					Case 8: If Game\Interface\DebugMenuOption>5 Then Game\Interface\DebugMenuOption=1
					Case 9: If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
					Case 10: If Game\Interface\DebugMenuOption>1 Then Game\Interface\DebugMenuOption=1
					Case 11: If Game\Interface\DebugMenuOption>3 Then Game\Interface\DebugMenuOption=1
					Case 12: If Game\Interface\DebugMenuOption>5 Then Game\Interface\DebugMenuOption=1
				End Select
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				Select Game\Interface\DebugNewObj
					Case 1: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
					Case 2: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
					Case 3: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=7
					Case 4: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=10
					Case 5: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
					Case 6: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=4
					Case 7: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
					Case 8: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=5
					Case 9: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
					Case 10: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=1
					Case 11: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=3
					Case 12: If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=5
				End Select
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugNewObj
					Case 1:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_TREE1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_TREE2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_TREE3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_TREE4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_TREE5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_TREE6
						End Select
					Case 2:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_SHRUB1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_SHRUB2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SHRUB3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_SHRUB4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_SHRUB5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_SHRUB6
						End Select
					Case 3:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_BUSH1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_BUSH2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BUSH3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_BUSH4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_BUSH5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_BUSH6
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_BUSH7
						End Select
					Case 4:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_GRASS1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_GRASS2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_GRASS3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_GRASS4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_GRASS5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_GRASS6
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_GRASS7
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_GRASS8
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_GRASS9
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_GRASS10
						End Select
					Case 5:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_SAKURA1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_SAKURA2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SAKURA3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_SAKURA4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_SAKURA5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_SAKURA6
						End Select
					Case 6:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_PALM1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_PALM2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_PALM3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_PALM4
						End Select
					Case 7:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_WILDPALM1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_WILDPALM2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_WILDPALM3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_WILDPALM4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_WILDPALM5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_WILDPALM6
						End Select
					Case 8:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_FLOWER1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_FLOWER2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_FLOWER3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_FLOWER4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_FLOWER5
						End Select
					Case 9:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_SNOWY1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_SNOWY2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SNOWY3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_SNOWY4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_SNOWY5
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_SNOWY6
						End Select
					Case 10:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_VINE1
						End Select
					Case 11:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_DRYTREE1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_DRYTREE2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_DRYTREE3
						End Select
					Case 12:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_ADABAT1
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_ADABAT2
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_ADABAT3
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_ADABAT4
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_ADABAT5
						End Select
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugNewObj
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_TREES#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_VISUALS#:
			DrawArrow(GAME_WINDOW_W-(265+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption+20*-2)*GAME_WINDOW_SCALE#)
			DrawRealText("Fire sprinkler", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Fire sprinkler blue", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Water sprinkler", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Confetti sprinkler", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Drop sprinkler", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Creatures", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Stage Visuals", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			DrawRealText("Beach chair", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Beach parasol", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Air balloon", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Helicopter", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Rainbow", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Automobile", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Icicle 1", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Icicle 2", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Icicle big 1", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Icicle big 2", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*15)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ice decor 1", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*16)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Ice decor 2", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*17)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>18 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=18
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 6:
						Game\Interface\DebugMenuOption=1
						Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+1
					Case 7:
						Game\Interface\DebugMenuOption=1
						Game\Interface\DebugMenu=Game\Interface\DebugMenu*10+2
					Default:
						Select Game\Interface\DebugMenuOption
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+4000
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+1000
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+2000
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+3000
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_CHAIR
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_PARASOL
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_AIRBALLOON
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_HELICOPTER
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_RAINBOW
							Case 13: Game\Interface\DebugNewObj = OBJTYPE_AUTO
							Case 14: Game\Interface\DebugNewObj = OBJTYPE_ICICLE
							Case 15: Game\Interface\DebugNewObj = OBJTYPE_ICICLE+1000
							Case 16: Game\Interface\DebugNewObj = OBJTYPE_ICICLEBIG
							Case 17: Game\Interface\DebugNewObj = OBJTYPE_ICICLEBIG+1000
							Case 18: Game\Interface\DebugNewObj = OBJTYPE_ICEDECOR
							Case 19: Game\Interface\DebugNewObj = OBJTYPE_ICEDECOR+1000
						End Select
						Game\Interface\DebugMenu=0
						Game\Interface\DebugMenuOption=1
				End Select
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_VISUALS_2#:
			DrawArrow(GAME_WINDOW_W-(265+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption+20*-2)*GAME_WINDOW_SCALE#)
			DrawRealText("Butterfly", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Seagull", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Orca", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Dolphin", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Turtle, manta, fish", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>5 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=5
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_BUTTERFLY
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_SEAGULL
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_ORCA
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_ORCA+1000
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_SEAC
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=6
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_VISUALS#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_VISUALS_3#:
			DrawArrow(GAME_WINDOW_W-(265+15)*GAME_WINDOW_SCALE#, (40+20*Game\Interface\DebugMenuOption+20*-2)*GAME_WINDOW_SCALE#)
			DrawRealText("Stage Visual 1", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*-1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Stage Visual 2", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Stage Visual 3", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Stage Visual 4", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Stage Visual 5", GAME_WINDOW_W-(265)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>5 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=5
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Game\Interface\DebugMenuOption
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_STAGEVISUAL1
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_STAGEVISUAL2
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_STAGEVISUAL3
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_STAGEVISUAL4
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_STAGEVISUAL5
				End Select
				Game\Interface\DebugMenu=0
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=7
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_VISUALS#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_ATTRIBUTES#:
			DrawArrow(GAME_WINDOW_W-(240+15)*GAME_WINDOW_SCALE#, (40-20*1+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Position", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			Select p\ObjType
				Case OBJTYPE_RING,OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS,OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000,OBJTYPE_BALLOON,OBJTYPE_GOAL,OBJTYPE_GOAL2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_BUBBLES,OBJTYPE_SHARD,OBJTYPE_HINT,OBJTYPE_COUNTER,OBJTYPE_BELL,OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+3000,OBJTYPE_SPRINKLER+4000,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_CLOUD,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_BOMBER2:
					SetColor(0,0,0)
				Default:
					SetColor(255,255,255)
			End Select
				DrawRealText("Rotation", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			Select p\ObjType
				Case OBJTYPE_RING,OBJTYPE_HINT,OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_CANNON,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
					SetColor(255,255,255)
				Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
					SetColor(255,255,255)
				Case OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_CLOUD,OBJTYPE_POLE,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
					SetColor(255,255,255)
				Case OBJTYPE_STAGEVISUAL1,OBJTYPE_STAGEVISUAL2,OBJTYPE_STAGEVISUAL3,OBJTYPE_STAGEVISUAL4,OBJTYPE_STAGEVISUAL5
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
			Select p\ObjType
				Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
					DrawRealText("Size", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_AIRBALLOON,OBJTYPE_STAGEVISUAL1,OBJTYPE_STAGEVISUAL2,OBJTYPE_STAGEVISUAL3,OBJTYPE_STAGEVISUAL4,OBJTYPE_STAGEVISUAL5,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000:
					DrawRealText("Size", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
					DrawRealText("Requirement", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_RING
					DrawRealText("Ring Amount", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_HINT
					DrawRealText("Hint Length", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
					DrawRealText("Water Level", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Default:
					DrawRealText("Power", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
			SetColor(255,255,255)
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGICE,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
				DrawRealText("Locks", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				DrawRealText("Camera position", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				DrawRealText("Camera rotation", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				DrawRealText("Camera zoom", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				DrawRealText("Camera speed", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			DrawRealText("Amount", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Amount rotation", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Amount space", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*10)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			Select p\ObjType
				Case OBJTYPE_SWITCH,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHTOP,OBJTYPE_SWITCHAIR,OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
					SetColor(255,255,255)
				Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
				DrawRealText("Switch", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*11)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Select p\ObjType
				Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
				DrawRealText("Switch status", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*12)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Select p\ObjType
				Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTEREND:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
				DrawRealText("Teleporter", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*13)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Select p\ObjType
				Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR,OBJTYPE_SPRINGICE,OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
				DrawRealText("Destination", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*14)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)

			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>15 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=15
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				FlushAll()
				Game\Interface\DebugEnteredAttributeTimer=0.5*secs#
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=Game\Interface\DebugMenuOption+50
				Game\Interface\DebugMenuOption=1
				Select Game\Interface\DebugMenu
					Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#,DEBUGMENU_ATTRIBUTES_CAMROTATION#,DEBUGMENU_ATTRIBUTES_CAMZOOM#,DEBUGMENU_ATTRIBUTES_CAMSPEED#:
						If TempAttribute\campos#=0 Or TempAttribute\campos#=10 Then TempAttribute\camx#=TempAttribute\x# : TempAttribute\camy#=TempAttribute\y# : TempAttribute\camz#=TempAttribute\z#
						If Game\Interface\DebugMenu=DEBUGMENU_ATTRIBUTES_CAMPOSITION# Or (TempAttribute\campos#<>0 And TempAttribute\campos#<>10) Then PositionEntity p\Objects\Entity, TempAttribute\camx#, TempAttribute\camy#, TempAttribute\camz#, 1
					Case DEBUGMENU_ATTRIBUTES_AMOUNT#,DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#,DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#:
						p\Objects\Mesh6=CopyEntity(p\Objects\Mesh, Game\Stage\Root)
					Case DEBUGMENU_ATTRIBUTES_DESTINATION#:
						If TempAttribute\hasd#>0 Then PositionEntity p\Objects\Entity, TempAttribute\dx#, TempAttribute\dy#, TempAttribute\dz#, 1
				End Select
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenu=DEBUGMENU_MAIN#
				Game\Interface\DebugMenuOption=2
			EndIf

			If Input\Pressed\ActionDrift Then ResetTempAttribute() : PlaySmartSound(Sound_MenuMove)
		Case DEBUGMENU_PLACE#:
			If Not(Game\Interface\DebugSavedTimer>0) Then
				Game\Interface\DebugSavedTimer=1*secs#
			ElseIf Game\Interface\DebugSavedTimer>0 And Game\Interface\DebugSavedTimer<0.5*secs# Then
				If p\ObjType>0 Then
					Player_Action_Debug_Save(p)
				ElseIf p\ObjType=0 Then
					Player_Action_Debug_SavePlayer(p)
				ElseIf p\ObjType=-1 Or p\ObjType=-2 Then
					Player_Action_Debug_SaveRival(p)
				EndIf
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenu=DEBUGMENU_MAIN#
				Game\Interface\DebugMenuOption=2
			Else
				SetColor(0,255,0)
				If p\ObjType>0 Then
					DrawRealText("Obj placed!", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					Select p\ObjType
						Case OBJTYPE_HINT:
							SetColor(255,255,0)
							DrawRealText("Write hint manually in xml.", GAME_WINDOW_W-(370)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Case OBJTYPE_PADDLE:
							SetColor(255,255,0)
							DrawRealText("Change to paddle2 in xml for mirrored.", GAME_WINDOW_W-(560)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Case OBJTYPE_COUNTER:
							SetColor(255,255,0)
							DrawRealText("Change to counter5 and so in xml.", GAME_WINDOW_W-(560)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Case OBJTYPE_NODE:
							SetColor(255,255,0)
							DrawRealText("Change to node2 in xml for speed affecting.", GAME_WINDOW_W-(660)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Case OBJTYPE_TELEPORTER2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000:
							SetColor(255,255,0)
							DrawRealText("Add destination stage in xml.", GAME_WINDOW_W-(560)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
					End Select
				ElseIf p\ObjType=0 Then
					DrawRealText("Player placed!", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				ElseIf p\ObjType=-1 Or p\ObjType=-2 Then
					DrawRealText("Rival placed!", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				EndIf
				SetColor(255,255,255)
				Game\Interface\DebugSavedTimer=Game\Interface\DebugSavedTimer-timervalue#
			EndIf
	End Select

	Select Game\Interface\DebugMenu
		Case DEBUGMENU_MAIN#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Move", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Select", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Position", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, 61)
			DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, 58)
			Select Game\Interface\DebugSpawnedObj
				Case 0: DrawRealText("Quit", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 1: DrawRealText("Save and Quit", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select

			DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, 61)
			DrawImageEx(INTERFACE(Interface_Keys_Small), (30)*GAME_WINDOW_SCALE#-6*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, 5)
			DrawImageEx(INTERFACE(Interface_Keys_Small), (30)*GAME_WINDOW_SCALE#+3*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, 33)
			DrawRealText("Quit", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			If TempAttribute\hasd#=1 Then
				DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25-30*0)*GAME_WINDOW_SCALE#)
				DrawRealText("Go to destination", GAME_WINDOW_W-(250)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25-30*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			EndIf
		Case DEBUGMENU_PLACE#:
		Case DEBUGMENU_ATTRIBUTES#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Move", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Select", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Back", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset all", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_POSITION#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+60)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Move around", (30+60+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Movement type ("+Game\Interface\DebugMoveType+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			DrawRealText("Collision type ("+Game\Interface\DebugCollision+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_ROTATION#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Rotate", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Auto Pitch / Yaw", (30+30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_POWER#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed2#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_LOCKS#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Change control lock", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Change camera lock", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Change running lock", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+60)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Move cam around", (30+60+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Movement type ("+Game\Interface\DebugMoveType+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_CAM_CENTER, GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#)
			DrawRealText("Toggle camera mode", GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset camera mode", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_CAMROTATION#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Rotate cam", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Auto Pitch / Yaw", (30+30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_CAMZOOM#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed2#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_CAMSPEED#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed2#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_AMOUNT#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			Select Game\Interface\DebugAmountAxis
				Case 1: DrawRealText("Change axis (Z-axis)", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 2: DrawRealText("Change axis (X-axis)", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 3: DrawRealText("Change axis (Y-axis)", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILLX, GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#)
			DrawRealText("Toggle amount circle", GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Rotate", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Auto Pitch / Yaw", (30+30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILLX, GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#)
			DrawRealText("Toggle amount circle", GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_UP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_DOWN, (30+30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Reset", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			Select Game\Interface\DebugAmountAxis
				Case 1: DrawRealText("Change axis (Z-axis)", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 2: DrawRealText("Change axis (X-axis)", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 3: DrawRealText("Change axis (Y-axis)", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed2#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*6)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILLX, GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#)
			DrawRealText("Toggle amount circle", GAME_WINDOW_W-(355)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_SWITCH#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			Select p\ObjType
				Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
					DrawRealText("Change switch", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
					DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Default:
					DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
					DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
		Case DEBUGMENU_ATTRIBUTES_SWITCHSTATUS#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Initially on", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Initially off", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_TELEPORTER#:
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Increase", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Decrease", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Case DEBUGMENU_ATTRIBUTES_DESTINATION#:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+60)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Move around", (30+60+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, True)
			DrawRealText("Speed ("+Game\Interface\DebugSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Movement type ("+Game\Interface\DebugMoveType+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_CAM_CENTER, GAME_WINDOW_W-(290)*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#)
			DrawRealText("Toggle destination", GAME_WINDOW_W-(290)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, (25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		Default:
			DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
			DrawRealText("Move", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
			DrawRealText("Select", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

			DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
			DrawRealText("Back", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	End Select

	If Game\Interface\DebugMenu>50 Then
		If Input\Pressed\ActionSkill1 Then
			Select Game\Interface\DebugMenu
				Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#,DEBUGMENU_ATTRIBUTES_CAMROTATION#,DEBUGMENU_ATTRIBUTES_CAMZOOM#,DEBUGMENU_ATTRIBUTES_CAMSPEED#,DEBUGMENU_ATTRIBUTES_DESTINATION#:
					PositionEntity p\Objects\Entity, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
				Case DEBUGMENU_ATTRIBUTES_AMOUNT#,DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#,DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#:
					FreeEntity p\Objects\Mesh6
			End Select
			PlaySmartSound(Sound_MenuBack)
			Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-50
			Game\Interface\DebugMenu=DEBUGMENU_ATTRIBUTES#
		EndIf

		DrawSmartKey(INPUT_BUTTON_CHANGE, GAME_WINDOW_W-(220)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25-30*0)*GAME_WINDOW_SCALE#)
		DrawRealText("Hide interface", GAME_WINDOW_W-(220)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25-30*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W-(220)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#, 60)
		DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W-(220)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#, 56)
		DrawRealText("Look around", GAME_WINDOW_W-(220)*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	EndIf

	space#=13.5

	Interface_Render_Stage_Debug_DrawSquare(1, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-45*GAME_WINDOW_SCALE#, 20, 3, space#)
	Interface_Render_Stage_Debug_DrawSquare(3, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-90*GAME_WINDOW_SCALE#, 20, 3, space#)
	Interface_Render_Stage_Debug_DrawSquare(2, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-121.25*GAME_WINDOW_SCALE#, 14, 2, space#)

	DrawRealText("x: "+TempAttribute\x#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText("y: "+TempAttribute\y#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText("z: "+TempAttribute\z#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))

	Select p\ObjType
		Case OBJTYPE_RING,OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS,OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000,OBJTYPE_BALLOON,OBJTYPE_GOAL,OBJTYPE_GOAL2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_BUBBLES,OBJTYPE_SHARD,OBJTYPE_HINT,OBJTYPE_COUNTER,OBJTYPE_BELL,OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+3000,OBJTYPE_SPRINKLER+4000,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_CLOUD,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_BOMBER2:
			SetColor(0,0,0)
		Default:
			SetColor(255,255,255)
	End Select
		DrawRealText("pitch: "+TempAttribute\pitch#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("yaw: "+TempAttribute\yaw#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("roll: "+TempAttribute\roll#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))

	Select p\ObjType
		Case OBJTYPE_RING,OBJTYPE_HINT,OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTHORN,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_CANNON,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
			SetColor(255,255,255)
		Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
			SetColor(255,255,255)
		Case OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_CLOUD,OBJTYPE_POLE,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select
	
	DrawRealText("power: "+TempAttribute\power#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))

	SetColor(255,255,255)

	Select p\ObjType
		Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGICE,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select
		Select TempAttribute\lockcontrol#
			Case 0:
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICEOBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,0,0)
					Default:
						SetColor(0,0,0)
				End Select
				DrawRealText("Cntrl lock: None", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICEOBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,255,255)
					Default:
						SetColor(0,0,0)
				End Select
			Case 1: DrawRealText("Cntrl lock: Forever", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 2: DrawRealText("Cntrl lock: 1 sec", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 3: DrawRealText("Cntrl lock: 3 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 4: DrawRealText("Cntrl lock: 0.5 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 5: DrawRealText("Cntrl lock: 2 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 6: DrawRealText("Cntrl lock: 2D cam", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))

		End Select
		Select TempAttribute\lockcam#
			Case 0:
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,0,0)
					Default:
						SetColor(0,0,0)
				End Select
				DrawRealText("Cam lock: None", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,255,255)
					Default:
						SetColor(0,0,0)
				End Select
			Case 1: DrawRealText("Cam lock: Forever", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 2: DrawRealText("Cam lock: 2 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 3: DrawRealText("Cam lock: 5 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 4: DrawRealText("Cam lock: 1 sec", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 5: DrawRealText("Cam lock: 3.5 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		End Select
		Select TempAttribute\lockrun#
			Case 0:
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,0,0)
					Default:
						SetColor(0,0,0)
				End Select
				DrawRealText("Run lock: None", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,255,255)
					Default:
						SetColor(0,0,0)
				End Select
			Case 1: DrawRealText("Run lock: Forever", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 2: DrawRealText("Run lock: 0.5 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 3: DrawRealText("Run lock: 1.5 secs", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		End Select

		DrawRealText("cx: "+TempAttribute\camx#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("cy: "+TempAttribute\camy#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("cz: "+TempAttribute\camz#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		Select TempAttribute\campos#
			Case 0:
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,0,0)
					Default:
						SetColor(0,0,0)
				End Select
				DrawRealText("cpos: no", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGICE,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,255,255)
					Default:
						SetColor(0,0,0)
				End Select
			Default:
				Select p\ObjType
					Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGICE,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						SetColor(255,255,255)
					Default:
						SetColor(0,0,0)
				End Select
				Select TempAttribute\campos#
					Case 1: DrawRealText("cpos: yes, point", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
					Case 2: DrawRealText("cpos: yes, no point", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
					Case 10: DrawRealText("cpos: no, immediate", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
					Case 11: DrawRealText("cpos: yes, point, immediate", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
					Case 12: DrawRealText("cpos: yes, no point, immediate", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				End Select
		End Select

		DrawRealText("cpitch: "+TempAttribute\campitch#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("cyaw: "+TempAttribute\camyaw#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("croll: "+TempAttribute\camroll#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))

		DrawRealText("czoom: "+TempAttribute\camzoom#, 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		DrawRealText("cspeed: "+TempAttribute\camspeed#, 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	SetColor(255,255,255)

	Select p\ObjType
		Case OBJTYPE_SWITCH,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHTOP,OBJTYPE_SWITCHAIR,OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
			SetColor(255,255,255)
		Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select
	Select p\ObjType
		Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
			Select Game\Interface\DebugWhichSwitch
			Case 1: i=TempAttribute\switch1#
			Case 2: i=TempAttribute\switch2#
			Case 3: i=TempAttribute\switch3#
			End Select
			DrawRealText("switch"+Game\Interface\DebugWhichSwitch+" no: "+i, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		Default:
			DrawRealText("switch no: "+TempAttribute\switch1#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	End Select
	SetColor(255,255,255)

	Select p\ObjType
		Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select
		Select TempAttribute\switchstatus#
			Case 0: DrawRealText("switch init: off", 32.5*GAME_WINDOW_SCALE#+(space#*7)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Case 1: DrawRealText("switch init: on", 32.5*GAME_WINDOW_SCALE#+(space#*7)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		End Select
	SetColor(255,255,255)

	Select p\ObjType
		Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTEREND:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select
		DrawRealText("tlprtr no: "+TempAttribute\teleporterno#, 32.5*GAME_WINDOW_SCALE#+(space#*7)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	SetColor(255,255,255)

	Select Game\Interface\DebugMenu
		Case DEBUGMENU_ATTRIBUTES_AMOUNT#,DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#,DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#:
			Interface_Render_Stage_Debug_DrawSquare(4, GAME_WINDOW_W-300*GAME_WINDOW_SCALE#, 60*GAME_WINDOW_SCALE#, 22, 3, space#)

			If TempAttribute\amountcircle#=0 Then
				If TempAttribute\amount1#<=1 Then
					SetColor(255,0,0)
				Else
					SetColor(255,255,255)
				EndIf
				DrawRealText("z amnt: "+TempAttribute\amount1#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				If TempAttribute\amount2#<=1 Then
					SetColor(255,0,0)
				Else
					SetColor(255,255,255)
				EndIf
				DrawRealText("x amnt: "+TempAttribute\amount2#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				If TempAttribute\amount3#<=1 Then
					SetColor(255,0,0)
				Else
					SetColor(255,255,255)
				EndIf
				DrawRealText("y amnt: "+TempAttribute\amount3#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				SetColor(255,255,255)
			Else
				SetColor(0,255,0)
				DrawRealText("circle!", GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				If TempAttribute\amount1#<=1 Then
					SetColor(255,0,0)
				Else
					SetColor(255,255,255)
				EndIf
				DrawRealText("z amnt: "+TempAttribute\amount1#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				If TempAttribute\amount3#<=1 Then
					SetColor(255,0,0)
				Else
					SetColor(255,255,255)
				EndIf
				DrawRealText("y amnt: "+TempAttribute\amount3#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				SetColor(255,255,255)
			EndIf

			DrawRealText("amnt pitch: "+TempAttribute\amountpitch#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*6)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			DrawRealText("amnt yaw: "+TempAttribute\amountyaw#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*6)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))

			DrawRealText("z amnt space: "+TempAttribute\amountspace1#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*13)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			If TempAttribute\amountcircle#=0 Then
				DrawRealText("x amnt space: "+TempAttribute\amountspace2#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*13)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
				DrawRealText("y amnt space: "+TempAttribute\amountspace3#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*13)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Else
				DrawRealText("y amnt space: "+TempAttribute\amountspace3#, GAME_WINDOW_W-297.5*GAME_WINDOW_SCALE#+(space#*13)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			EndIf
		Case DEBUGMENU_ATTRIBUTES_DESTINATION#:
			Interface_Render_Stage_Debug_DrawSquare(4, GAME_WINDOW_W-210*GAME_WINDOW_SCALE#, 60*GAME_WINDOW_SCALE#, 14, 3, space#)

			Select TempAttribute\hasd#
				Case 0:
					SetColor(255,0,0)
					DrawRealText("has d: no", GAME_WINDOW_W-207.5*GAME_WINDOW_SCALE#+(space#*6)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
					SetColor(255,255,255)
				Case 1: DrawRealText("has d: yes", GAME_WINDOW_W-207.5*GAME_WINDOW_SCALE#+(space#*6)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			End Select

			DrawRealText("dx: "+TempAttribute\dx#, GAME_WINDOW_W-207.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			DrawRealText("dy: "+TempAttribute\dy#, GAME_WINDOW_W-207.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			DrawRealText("dz: "+TempAttribute\dz#, GAME_WINDOW_W-207.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, 61.25*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	End Select

	For o.tObject = Each tObject
		If EntityDistance(p\Objects\Entity,o\Entity)<50 Then
			If o\ThisIsAnEnemy Then
				If o\Switch\SwitchNo[0]>0 Then Interface_ObjectDebugTag(o, "switch: "+o\Switch\SwitchNo[0], 5)
			Else
				Select o\ObjType
					Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHTOP:
						Interface_ObjectDebugTag(o, "switch: "+o\Switch\SwitchNo[0], 5)
					Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_BOXLIGHT,OBJTYPE_GOAL2:
						If o\Switch\SwitchNo[0]>0 Then Interface_ObjectDebugTag(o, "switch1: "+o\Switch\SwitchNo[0], 5)
						If o\Switch\SwitchNo[1]>0 Then Interface_ObjectDebugTag(o, "switch2: "+o\Switch\SwitchNo[1], 7.5)
						If o\Switch\SwitchNo[2]>0 Then Interface_ObjectDebugTag(o, "switch3: "+o\Switch\SwitchNo[2], 10)
					Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTEREND:
						Interface_ObjectDebugTag(o, "teleporter: "+o\Teleporter\TeleporterNo, 5)
					Case OBJTYPE_SWITCHWATER:
						Interface_ObjectDebugTag(o, "level: "+Str(o\Power#), 5)
				End Select
			EndIf
			Select o\ObjType
				Case OBJTYPE_HOMMER:
				Default: Interface_ObjectDebugTag(o, o\ID)
			End Select
		EndIf
	Next

End Function

Function Interface_ObjectDebugTag(o.tObject, text$, extraheight#=0)
	height# = 5+extraheight#
	If EntityInView(o\Entity, cam\Entity) Then
		CameraProject cam\Entity, EntityX (o\Entity), EntityY (o\Entity)+height#, EntityZ (o\Entity)
		w = StringWidth (label$)
		h = StringHeight (label$)
		x = ProjectedX () - (w / 2) - 1
		y = ProjectedY () - (h / 2) - 1
		Color 0, 0, 0
		Rect x, y, w + 2, h + 2, 1
		Color 255, 255, 255
		Text x, y, label$
		
		If KeyHit(Key_F) Then 
			PlaySmartSound(Sound_MenuMove)
				Select Game\Interface\DebugIDMode
					Case 0 : Game\Interface\DebugIDMode=1
					Case 1 : Game\Interface\DebugIDMode=0
				End Select 
		EndIf 
		
		Select Game\Interface\DebugIDMode
			Case 0
				DrawRealText(Text$, x, y, SmartImage(Interface_TextControls_1), 1)
			Case 1
				DrawRealText("X : "+o\Position\x#, x, y, SmartImage(Interface_TextControls_1), 1)
				DrawRealText("Y : "+o\Position\y#, x, y+40, SmartImage(Interface_TextControls_1), 1)
				DrawRealText("Z : "+o\Position\z#, x, y+80, SmartImage(Interface_TextControls_1), 1)
		End Select 
	EndIf
End Function





Function Interface_Render_Stage_Debug_DrawSquare(Color, x#, y#, w, h, space#)

	For i=1 To w
	For j=1 To h
		Select j
			Case 1:
				Select i
					Case 1: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 0+(Color-1)*3)
					Case w: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 2+(Color-1)*3)
					Default: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 1+(Color-1)*3)
				End Select
			Case h:
				Select i
					Case 1: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 0+24+(Color-1)*3)
					Case w: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 2+24+(Color-1)*3)
					Default: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 1+24+(Color-1)*3)
				End Select
			Default:
				Select i
					Case 1: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 0+12+(Color-1)*3)
					Case w: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 2+12+(Color-1)*3)
					Default: DrawImageEx(INTERFACE(SmartImage(Interface_Debug)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 1+12+(Color-1)*3)
				End Select
		End Select
	Next
	Next

End Function
;~IDEal Editor Parameters:
;~C#Blitz3D