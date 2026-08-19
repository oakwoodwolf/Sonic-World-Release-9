
Const DEBUGMENU_MAIN# = 0

Const DEBUGMENU_CHOOSE# = 1
Const DEBUGMENU_CHOOSE_RINGS# = 11
Const DEBUGMENU_CHOOSE_TRANSLATORS# = 12
Const DEBUGMENU_CHOOSE_MISC# = 13
Const DEBUGMENU_CHOOSE_VISUALS# = 14
Const DEBUGMENU_CHOOSE_ENEMIES# = 15
Const DEBUGMENU_CHOOSE_HAZARDS# = 16

Const DEBUGMENU_CHOOSE_TRIGGERS# = 17
Const DEBUGMENU_CHOOSE_TREES# = 18
Const DEBUGMENU_CHOOSE_TREES_2# = 181
Const DEBUGMENU_CHOOSE_CHAO# = 19
Const DEBUGMENU_CHOOSE_MONITORS# = 99

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
Function Interface_Render_Stage_Debug_Titles()
	
	Select Game\Interface\DebugMenu
		Case DEBUGMENU_MAIN,DEBUGMENU_PLACE#
			DrawRealText("Object Placer", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE
			DrawRealText("Choose Object", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_RINGS
			DrawRealText("Collectibles", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_TRANSLATORS#
			DrawRealText("Translators", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_MONITORS#
			DrawRealText("Monitors", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_ENEMIES#
			DrawRealText("Enemies", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_HAZARDS#
			DrawRealText("Hazards", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_MISC#
			DrawRealText("Miscellaneous", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_TRIGGERS#
			DrawRealText("Triggers", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case  DEBUGMENU_CHOOSE_TREES#,DEBUGMENU_CHOOSE_TREES_2#
			DrawRealText("Plants", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_CHOOSE_VISUALS#
			DrawRealText("Visuals", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
			
		Case DEBUGMENU_ATTRIBUTES#
			DrawRealText("Attributes", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_POSITION#
			DrawRealText("Position", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_ROTATION#
			DrawRealText("Rotation", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_POWER#
			DrawRealText("Power", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_LOCKS#
			DrawRealText("Choose Locks", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#
			DrawRealText("Cam Position", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_CAMROTATION#
			DrawRealText("Cam Rotation", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_CAMZOOM#
			DrawRealText("Cam Zoom", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_CAMSPEED#
			DrawRealText("Cam Speed", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_AMOUNT#
			DrawRealText("Amount", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#
			DrawRealText("Amount Rotation", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#
			DrawRealText("Amount Space", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_SWITCH#
			DrawRealText("Switch", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_SWITCHSTATUS#
			DrawRealText("Switch Status", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_TELEPORTER#
			DrawRealText("Teleporter", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
		Case DEBUGMENU_ATTRIBUTES_DESTINATION#
			DrawRealText("Destination", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
	End Select
	
End Function

Function Interface_Render_Stage_Debug_MiscControls(p.tPlayer)
	
	
	
	For i=1 To 9
		If KeyHit(Key_+i) Then
			Game\Interface\DebugMenu=49+i
		EndIf 
	Next
	
	
	
	
	
	
	Select Game\Interface\DebugMenu
		Case DEBUGMENU_ATTRIBUTES_POSITION#,DEBUGMENU_ATTRIBUTES_ROTATION#
			If KeyDown(KEY_F) Then 
				TempAttribute\yaw#=cam\Rotation\y#
				RotateEntity(p\Objects\Mesh,TempAttribute\pitch#,TempAttribute\yaw#,0)
				RotateEntity(p\Objects\Mesh2,TempAttribute\pitch#,TempAttribute\yaw#,0)
				RotateEntity(p\Objects\Mesh3,TempAttribute\pitch#,TempAttribute\yaw#,0)
			EndIf
			If Input\Pressed\Back Then Player_Action_Debug_Save(p)
	End Select
	
	If Input\Pressed\ActionSkill3 Then Player_CycleDebugObjects(p)
	
	For i = 11 To 18
		If Game\Interface\DebugMenu=i Then
			If Input\Pressed\ActionSkill2 Then 
				For j = 1 To 9 : Game\Interface\DebugMenuOptionOrder[j]=1 : Next
			EndIf
		EndIf
	Next
	
End Function

Function Interface_Render_Stage_Debug(p.tPlayer)
	
	Interface_Render_Stage_Debug_Titles()
	
	Interface_Render_Stage_Debug_MiscControls(p)
	
	
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
			DrawRealText("Miscellaneous", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Visuals", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Enemies", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Hazards", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Triggers", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			DrawRealText("Plants", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*8)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			If Menu\Developer=1 Then DrawRealText("Chao Garden", GAME_WINDOW_W-(200)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
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
		Case DEBUGMENU_CHOOSE_RINGS#:
			DrawArrow(GAME_WINDOW_W-(225+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(225+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=6
			Game\Interface\DebugMenuOptionOrder[20]=5
			Game\Interface\DebugMenuOptionOrder[30]=6
			Game\Interface\DebugMenuOptionOrder[40]=7
			Game\Interface\DebugMenuOptionOrder[50]=5
			Game\Interface\DebugMenuOptionOrder[60]=8
			
			i=-1
			
			;rings
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: ring$="Ring"
				Case 2: ring$="Red Star Ring"
				Case 3:	ring$="Moon Ring"
				Case 4: ring$="World Token"
				Case 5: ring$="Timer"
				Case 6: ring$="Diamond"
			End Select
			
			DrawRealText(ring$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;goals
			Select Game\Interface\DebugMenuOptionOrder[2]
				Case 1: goal$="Goal"
				Case 2: goal$="Goal Prog"
				Case 3:	goal$="Goal Switch"
				Case 4: goal$="Goal Switch Prog"
				Case 5: goal$="Warp Ring"
			End Select
			
			DrawRealText(goal$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; mission objects
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: mission$="Emerald Shard"
				Case 2: mission$="Collectible"
				Case 3: mission$="Counter"
				Case 4: mission$="Hint"
				Case 5: mission$="Invisible Hint"
				Case 6: mission$="Bell"
			End Select
			
			DrawRealText(mission$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;Regular Monitors
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: monitor$="Ring"
				Case 2: monitor$="Life"
				Case 3: monitor$="Trap"
				Case 4: monitor$="Invincibility"
				Case 5: monitor$="Speed Shoes"
				Case 6: monitor$="Bomb"
				Case 7: monitor$="Wings"
					
			End Select
			
			DrawRealText(monitor$+" Monitor", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;Shield Monitors
			Select Game\Interface\DebugMenuOptionOrder[5]
				Case 1: smonitor$="Shield"
				Case 2: smonitor$="Flame Shield"
				Case 3: smonitor$="Bubble Shield"
				Case 4: smonitor$="Thunder Shield"
				Case 5: smonitor$="Earth Shield"
					
					
			End Select
			
			DrawRealText(smonitor$+" Monitor", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;Vehicle Monitors
			Select Game\Interface\DebugMenuOptionOrder[6]
				Case 1: vmonitor$="Board"
				Case 2: vmonitor$="Glider"
				Case 3: vmonitor$="Car"
				Case 4: vmonitor$="Bike"
				Case 5: vmonitor$="Bobsleigh"
				Case 6: vmonitor$="Tornado"
				Case 7: vmonitor$="Cyclone"
				Case 8: vmonitor$="Kart"
					
			End Select
			
			DrawRealText(vmonitor$+" Monitor", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=0
				
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_RING
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_REDRING
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_MOONRING
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_TOKEN
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_TIMER
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_DIAMOND
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_GOAL
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_GOAL+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_GOAL2
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_GOAL2+1000
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SHARD
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_COLLECTIBLE
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_COUNTER
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_HINT
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_HINT
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_BELL
						End Select
						
					Case 4
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_RINGS
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_LIFE
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_TRAP
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_INVINC
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_SHOES
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_BOMB
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_WINGS
						End Select
						Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_MONITORS#
						Game\Interface\DebugMenuOption=1
					Case 5
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_NSHIELD
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_FSHIELD
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_BSHIELD
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_TSHIELD
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_ESHIELD
						End Select
						Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_MONITORS#
						Game\Interface\DebugMenuOption=1
					Case 6
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_BOARD
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_GLIDER
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_CAR
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_BIKE
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_BOBSLEIGH
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_TORNADO
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_CYCLONE
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_KART
								
						End Select
						Game\Interface\DebugMenu=DEBUGMENU_CHOOSE_MONITORS#
						Game\Interface\DebugMenuOption=1
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_TRANSLATORS#:
			DrawArrow(GAME_WINDOW_W-(225+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(225+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=7
			Game\Interface\DebugMenuOptionOrder[20]=3
			Game\Interface\DebugMenuOptionOrder[30]=4
			Game\Interface\DebugMenuOptionOrder[40]=4
			Game\Interface\DebugMenuOptionOrder[50]=3
			Game\Interface\DebugMenuOptionOrder[60]=9
			Game\Interface\DebugMenuOptionOrder[70]=10
			
			
			i=-1
			
			;springs
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: spring$="Normal"
				Case 2: spring$="Big"
				Case 3: spring$="Air"
				Case 4: spring$="Trap"
				Case 5: spring$="Air Trap"
				Case 6: spring$="Thorn"
				Case 7: spring$="Ice"
			End Select
			
			DrawRealText(spring$+" Spring", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;hoops
			Select Game\Interface\DebugMenuOptionOrder[2]
				Case 1: hoop$="Normal"
				Case 2: hoop$="Trick"
				Case 3: hoop$="Accelerator"
			End Select
			
			DrawRealText(hoop$+" Hoop", GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;fasteners
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: dash$="Dash Pad"
				Case 2: dash$="Dash Ramp"
				Case 3: dash$="Trick Ramp"	
				Case 4: dash$="Grind Booster"
			End Select
			
			DrawRealText(dash$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;helpers
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: help$="Locker"
				Case 2: help$="Forcer"
				Case 3: help$="Node"
				Case 4: help$="Rail Node"
			End Select
			DrawRealText(help$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			;fans
			Select Game\Interface\DebugMenuOptionOrder[5]
				Case 1: fan$="Fan"
				Case 2: fan$="Big Fan"
				Case 3: fan$="Big Fan Low"
			End Select
			
			DrawRealText(fan$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; bumpers
			Select Game\Interface\DebugMenuOptionOrder[6]
				Case 1: bumper$="Cannon"
				Case 2: bumper$="Ball Bumper"
				Case 3: bumper$="Ground Bumper"
				Case 4: bumper$="Metro Bumper"
				Case 5: bumper$="Plate Bumper"
				Case 6: bumper$="Triangle Bumper"
				Case 7: bumper$="Paddle"
				Case 8: bumper$="Cloud"
				Case 9: bumper$="Pole"
			End Select
			DrawRealText(bumper$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; trans
			Select Game\Interface\DebugMenuOptionOrder[7]
				Case 1: trans$="Pulley"
				Case 2: trans$="Inverted Pulley"
				Case 3: trans$="Ground Jump Panel"
				Case 4: trans$="Wall Jump Panel"
				Case 5: trans$="Rocket"
				Case 6: trans$="Elevator"
				Case 7: trans$="Handle"
				Case 8: trans$="Propellor"
				Case 9: trans$="Rope"
				Case 10: trans$="Invisiblw Jump Pane"
			End Select
			DrawRealText(trans$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>7 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=7
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=0
				
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SPRING
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_BSPRING
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SPRINGX
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_SPRINGTRAP
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_SPRINGTRAPX
							Case 6
							Case 7
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_HOOP
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_THOOP
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_ACCEL
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_PAD
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_RAMP
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_TRAMP
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_RAILPAD
						End Select
					Case 4
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_LOCKER
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_FORCER
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_NODE
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_RAILNODE
						End Select
					Case 5
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_FAN
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_FAN+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_FAN+2000
						End Select
					Case 6
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_CANNON
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_BALLBUMPER
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_GROUNDBUMPER
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_METROBUMPER
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_PLATEBUMPER
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_TRIANGLEBUMPER
							Case 7: Game\Interface\DebugNewObj=OBJTYPE_PADDLE
							Case 8: Game\Interface\DebugNewObj=OBJTYPE_CLOUD
							Case 9: Game\Interface\DebugNewObj=OBJTYPE_POLE
								
						End Select
					Case 7
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_PULLEY
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_PULLEY+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_PANEL1
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_PANEL2
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_ROCKET
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_ELEVATOR
							Case 7: Game\Interface\DebugNewObj=OBJTYPE_HANDLE
							Case 8: Game\Interface\DebugNewObj=OBJTYPE_PROPELLER
							Case 9: Game\Interface\DebugNewObj=OBJTYPE_PULLEYROPE
							Case 10: Game\Interface\DebugNewObj=OBJTYPE_PANEL2+1000
								
								
						End Select
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_MONITORS#
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
		Case DEBUGMENU_CHOOSE_MISC#:
			DrawArrow(GAME_WINDOW_W-(255+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(255+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=3
			Game\Interface\DebugMenuOptionOrder[20]=8
			Game\Interface\DebugMenuOptionOrder[30]=6
			Game\Interface\DebugMenuOptionOrder[40]=6
			Game\Interface\DebugMenuOptionOrder[50]=3
			Game\Interface\DebugMenuOptionOrder[60]=5
			
			
			
			i=-1
			
			
			; checkpoints
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: check$="Checkpoint 1"
				Case 2: check$="Checkpoint 2"
				Case 3: check$="Checkpoint 3"
			End Select
			
			DrawRealText(check$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; boxes
			Select Game\Interface\DebugMenuOptionOrder[2]
				Case 1: box$="Wood"
				Case 2: box$="Metal"
				Case 3: box$="Iron"
				Case 4: box$="Cage"
				Case 5: box$="Float"
				Case 6: box$="Explosive"
				Case 7: box$="Light"
				Case 8: box$="Nitro"
			End Select
			
			DrawRealText(box$+" Box", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; switches
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: switch$="Normal"
				Case 2: switch$="Base"
				Case 3: switch$="Top"
				Case 4: switch$="Air"
				Case 5: switch$="Water"
				Case 6: switch$="Invisible Water"
					
			End Select
			
			DrawRealText(switch$+" Switch", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; signs
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: sign$="Speed"
				Case 2: sign$="Flight"
				Case 3: sign$="Power"
				Case 4: sign$="Fall"
				Case 5: sign$="Up"
				Case 6: sign$="Down"
				Case 7: sign$="Left"
				Case 8: sign$="Right"
			End Select
			
			DrawRealText(sign$+" Sign", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; teleporters
			Select Game\Interface\DebugMenuOptionOrder[5]
				Case 1: tele$="Normal"
				Case 2: tele$="Hub"
				Case 3: tele$="End"
			End Select
			
			DrawRealText(tele$+" Teleporter", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; miscmisc
			Select Game\Interface\DebugMenuOptionOrder[6]
				Case 1: misc$="Omochao"
				Case 2: misc$="Falling Platform"
				Case 3: misc$="Balloon"
				Case 4: misc$="Breath Bubbles"
				Case 5: misc$="Capsule"
					
					
			End Select
			
			DrawRealText(misc$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=0
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_CHECK
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_CHECK+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_CHECK+2000
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_BOXWOODEN
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_BOXMETAL
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_BOXIRON
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_BOXCAGE
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_BOXFLOAT
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_BOXTNT
							Case 7: Game\Interface\DebugNewObj=OBJTYPE_BOXLIGHT
							Case 8: Game\Interface\DebugNewObj=OBJTYPE_BOXNITRO
								
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SWITCH
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_SWITCHBASE
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SWITCHTOP
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_SWITCHAIR
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_SWITCHWATER
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_SWITCHWATER+1000
						End Select
					Case 4
						Game\Interface\DebugNewObj=OBJTYPE_SIGN+(1000*Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption])
					Case 5
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_TELEPORTER
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_TELEPORTER2
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_TELEPORTEREND
						End Select
					Case 6
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_OMOCHAO
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_FPLAT
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_BALLOON
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_BUBBLES
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_CAPSULE
						End Select
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_ENEMIES#:
			
			DrawArrow(GAME_WINDOW_W-(255+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(255+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=10
			Game\Interface\DebugMenuOptionOrder[20]=9
			Game\Interface\DebugMenuOptionOrder[30]=13
			Game\Interface\DebugMenuOptionOrder[40]=20
			Game\Interface\DebugMenuOptionOrder[50]=18
			Game\Interface\DebugMenuOptionOrder[60]=12
			Game\Interface\DebugMenuOptionOrder[70]=6
			Game\Interface\DebugMenuOptionOrder[80]=15
			Game\Interface\DebugMenuOptionOrder[90]=4
			i=-1
			
			
			; pawns
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: pawn$="Pawn"
				Case 2: pawn$="Shielded Pawn"
				Case 3: pawn$="Sword Pawn"
				Case 4: pawn$="Gun Pawn"
				Case 5: pawn$="Hammer"
				Case 6: pawn$="Shielded Hammer"
				Case 7: pawn$="Hammer Hammer"
				Case 8: pawn$="Cameron"
				Case 9: pawn$="Bishop"
				Case 10: pawn$="Magician"
			End Select
			
			DrawRealText(pawn$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; flys
			Select Game\Interface\DebugMenuOptionOrder[2]
				Case 1: fly$="Spinner"
				Case 2: fly$="Electric Spinner"
				Case 3: fly$="Metal Spinner"
				Case 4: fly$="Black Spinner"
				Case 5: fly$="Flapper"
				Case 6: fly$="Gun Flapper"
				Case 7: fly$="Bomb Flapper"
				Case 8: fly$="Needle Flapper"
				Case 9: fly$="Klagen"
			End Select
			
			DrawRealText(fly$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; classic 1
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: clas1$="Motobug"
				Case 2: clas1$="Caterkiller"
				Case 3: clas1$="Buzz Bomber"
				Case 4: clas1$="Buzzer"
				Case 5: clas1$="Chopper"
				Case 6: clas1$="Crabmeat"
				Case 7: clas1$="Jaws"
				Case 8: clas1$="Spiny"
				Case 9: clas1$="Grabber"
				Case 10: clas1$="Kiki"
				Case 11: clas1$="Cop Speeder"
				Case 12: clas1$="EggRobo"
				Case 13: clas1$="Orbinaut"
					
			End Select
			
			DrawRealText(clas1$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; classic 2
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: clas2$="Anton"
				Case 2: clas2$="Aquis"
				Case 3: clas2$="Bombie"
				Case 4: clas2$="Newtron"
				Case 5: clas2$="Penguinator"
				Case 6: clas2$="Slicer"
				Case 7: clas2$="Snail Blaster"
				Case 8: clas2$="Spikes"
				Case 9: clas2$="Asteron"
				Case 10: clas2$="Batbot"
				Case 11: clas2$="Bubbles"
				Case 12: clas2$="Bubbles spikes"
				Case 13: clas2$="Steelion"
				Case 14: clas2$="Balkiry"
				Case 15: clas2$="Burrobot"
				Case 16: clas2$="Crawl"
				Case 17: clas2$="Dragonfly"
				Case 18: clas2$="Madmole"
				Case 19: clas2$="Manta"
				Case 20: clas2$="Mushmeanie"
					
					
			End Select
			
			DrawRealText(clas2$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; classic 3
			Select Game\Interface\DebugMenuOptionOrder[5]
				Case 1: clas3$="Octus"
				Case 2: clas3$="Pata-Bata"
				Case 3: clas3$="Zoomer"
				Case 4: clas3$="Ball Hog"
				Case 5: clas3$="Rhinotank"
				Case 6: clas3$="TechnoSqueek"
				Case 7: clas3$="E-1000"
				Case 8: clas3$="Cop Racer"
				Case 9: clas3$="Catakiller Jr."
				Case 10: clas3$="Cluckoid"
				Case 11: clas3$="Mantis"
				Case 12: clas3$="Nebula"
				Case 13: clas3$="Roller"
				Case 14: clas3$="Snowy"
				Case 15: clas3$="Splats"
				Case 16: clas3$="Toxomister"
				Case 17: clas3$="Bomber 1"
				Case 18: clas3$="Bomber 2"
					
					
			End Select
			
			DrawRealText(clas3$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			; gun
			Select Game\Interface\DebugMenuOptionOrder[6]
				Case 1 : gun$="Hunter"
				Case 2: gun$="Shielded Hunter"
				Case 3: gun$="Beetle"
				Case 4: gun$="Mono Beetle"
				Case 5: gun$="Spark Beetle"
				Case 6: gun$="Spring Beetle"
				Case 7: gun$="Artificial Chaos"
				Case 8: gun$="Artificial Chaos Blob"
				Case 9: gun$="Rhino"
				Case 10: gun$="Spiked Rhino"
				Case 11: gun$="3 Spiked Hornet"
				Case 12: gun$="6 Spiked Hornet"
					
					
			End Select
			
			DrawRealText(gun$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; modern
			Select Game\Interface\DebugMenuOptionOrder[7]
				Case 1: modern$="Aero Cannon"
				Case 2: modern$="Chaser"
				Case 3: modern$="Fighter"
				Case 4: modern$="Typhoon"
				Case 5: modern$="Freezing Typhoon"
				Case 6: modern$="Egg Gunner"
					
					
			End Select
			
			DrawRealText(modern$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; nonrobot
			Select Game\Interface\DebugMenuOptionOrder[8]
				Case 1: non$="Boo"
				Case 2: non$="Scare Boo"
				Case 3: non$="Iblis Biter"
				Case 4: non$="Iblis Crawler"
				Case 5: non$="Iblis Taker"
				Case 6: non$="Black Warrior"
				Case 7: non$="Black Warrior Gun 1"
				Case 8: non$="Black Warrior Gun 2"
				Case 9: non$="Black Oak Sword"
				Case 10: non$="Black Leech"
				Case 11: non$="Black Wing"
				Case 12: non$="G.U.N. Soldier"
				Case 13: non$="G.U.N. Soldier Camo"
				Case 14: non$="Sheep"
				Case 15: non$="Ghost Pumpkin"
					
					
			End Select
			
			DrawRealText(non$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; boss
			Select Game\Interface\DebugMenuOptionOrder[9]
				Case 1: boss$="Inactive Enemy"
				Case 2: boss$="FCannon1"	
				Case 3: boss$="FCannon2"
				Case 4: boss$="FCannon3"
			End Select
			
			DrawRealText(boss$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
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
				Game\Interface\DebugMenu=0
				
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_PAWN
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_PAWNSHIELD	
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_PAWNSWORD
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_PAWNGUN
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_HAMMER
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_HAMMERSHIELD
							Case 7: Game\Interface\DebugNewObj=OBJTYPE_HAMMERHAMMER
							Case 8: Game\Interface\DebugNewObj=OBJTYPE_CAMERON
							Case 9: Game\Interface\DebugNewObj=OBJTYPE_WITCH1
							Case 10: Game\Interface\DebugNewObj=OBJTYPE_WITCH2
								
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SPINA	
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_SPANA
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SPUNA
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_SPONA
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_FLAPPER
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_FLAPPERGUN
							Case 7: Game\Interface\DebugNewObj=OBJTYPE_FLAPPERBOMB
							Case 8: Game\Interface\DebugNewObj=OBJTYPE_FLAPPERNEEDLE
							Case 9: Game\Interface\DebugNewObj=OBJTYPE_KLAGEN
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
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
					Case 4
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
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
							Case 14: Game\Interface\DebugNewObj = OBJTYPE_BALKIRY
							Case 15: Game\Interface\DebugNewObj = OBJTYPE_BURROBOT
							Case 16: Game\Interface\DebugNewObj = OBJTYPE_CRAWL
							Case 17: Game\Interface\DebugNewObj = OBJTYPE_DRAGONFLY
							Case 18: Game\Interface\DebugNewObj = OBJTYPE_MADMOLE
							Case 19: Game\Interface\DebugNewObj = OBJTYPE_MANTA
							Case 20: Game\Interface\DebugNewObj = OBJTYPE_MUSHMEANIE
						End Select
					Case 5
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_OCTUS
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_PATABATA
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_ZOOMER
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_BALLHOG
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_RHINOTANK
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_TECHNOSQU
							Case 7: Game\Interface\DebugNewObj = OBJTYPE_E1000
							Case 8: Game\Interface\DebugNewObj = OBJTYPE_COPRACER
							Case 9: Game\Interface\DebugNewObj = OBJTYPE_CATAKILLER
							Case 10: Game\Interface\DebugNewObj = OBJTYPE_CLUCKOID
							Case 11: Game\Interface\DebugNewObj = OBJTYPE_MANTIS
							Case 12: Game\Interface\DebugNewObj = OBJTYPE_NEBULA
							Case 13: Game\Interface\DebugNewObj = OBJTYPE_ROLLER
							Case 14: Game\Interface\DebugNewObj = OBJTYPE_SNOWY
							Case 15: Game\Interface\DebugNewObj = OBJTYPE_SPLATS
							Case 16: Game\Interface\DebugNewObj = OBJTYPE_TOXO
							Case 17: Game\Interface\DebugNewObj = OBJTYPE_BOMBER1
							Case 18: Game\Interface\DebugNewObj = OBJTYPE_BOMBER2
						End Select
					Case 6
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
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
					Case 7
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_AEROC
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_CHASER
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_FIGHTER
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_TYPHOON
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_TYPHOONF
							Case 6: Game\Interface\DebugNewObj = OBJTYPE_EGUNNER
						End Select
					Case 8
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
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
					Case 9
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_INACTIVE
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_FCANNON1
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_FCANNON2
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_FCANNON3
						End Select
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_CHOOSE_HAZARDS#:
			DrawArrow(GAME_WINDOW_W-(255+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(255+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=3
			Game\Interface\DebugMenuOptionOrder[20]=6
			Game\Interface\DebugMenuOptionOrder[30]=5
			Game\Interface\DebugMenuOptionOrder[40]=3
			Game\Interface\DebugMenuOptionOrder[50]=4
			Game\Interface\DebugMenuOptionOrder[60]=3
			
			
			
			
			i=-1
			
			
			; 1
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: one$="Spike Ball"
				Case 2: one$="Spike Bomb"
				Case 3: one$="Spike Crusher"
			End Select
			
			DrawRealText(one$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; 2
			Select Game\Interface\DebugMenuOptionOrder[2]
				Case 1: two$="Spike Drills"
				Case 2: two$="Timed Spike Drills"
				Case 3: two$="Trap Spike Drills"
				Case 4: two$="Spike Bar"
				Case 5: two$="Wide Spike Bar"
				Case 6: two$="Wider Spike Bar"
			End Select
			
			DrawRealText(two$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; 3
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: three$="Spike Swing 1"
				Case 2: three$="Spike Swing 2"
				Case 3: three$="Spike Swing 3"
				Case 4: three$="Spike Swing Ball"
				Case 5: three$="Spike Cylinder"
			End Select
			
			DrawRealText(three$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; signs
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: four$="Flame Spout"
				Case 2: four$="Ice Spout"
				Case 3: four$="Shock Spout"
					
			End Select
			
			DrawRealText(four$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; teleporters
			Select Game\Interface\DebugMenuOptionOrder[5]
				Case 1: five$="Vertical Laser"
				Case 2: five$="Horizontal Laser"
				Case 3: five$="Vertical Ring Gate"
				Case 4: five$="Horizontal Ring Gate"
			End Select
			
			DrawRealText(five$+" Teleporter", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; miscmisc
			Select Game\Interface\DebugMenuOptionOrder[6]
				Case 1: six$="Crystal"
				Case 2: six$="Explosion"
				Case 3: six$="Explosion Rocket"
			End Select
			
			DrawRealText(six$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>6 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=6
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=0
				
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SPIKEBALL
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_SPIKEBOMB
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SPIKECRUSHER
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SPIKEDRILL
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_SPIKETIMED
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SPIKETRAP
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_SPIKEBAR
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_SPIKEBAR+1000
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_SPIKEBAR+2000
								
								
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_SPIKESWING
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_SPIKESWING+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SPIKESWING+2000
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_SPIKESWINGBALL
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_SPIKECYLINDER
						End Select
					Case 4
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_FLAMESPOUT
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_ICESPOUT
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_SHOCKSPOUT
						End Select
					Case 5
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_LASERV
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_LASERH
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_RINGGATEV
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_RINGGATEH
						End Select
					Case 6
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_CRYSTAL
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_EXPLOSION
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_EXPLOSION2
						End Select
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
			
		Case DEBUGMENU_CHOOSE_TRIGGERS#:
			DrawArrow(GAME_WINDOW_W-(225+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(225+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=5
			Game\Interface\DebugMenuOptionOrder[20]=3
			Game\Interface\DebugMenuOptionOrder[30]=4
			Game\Interface\DebugMenuOptionOrder[40]=4
			i=-1
			
			;rings
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: ring$="Skydive"
				Case 2: ring$="Skydive Cancel"
				Case 3:	ring$="Mach"
				Case 4: ring$="Mach Cancel"
				Case 5: ring$="Vehicle Cancel"
			End Select
			
			DrawRealText(ring$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			DrawRealText("Music "+Game\Interface\DebugMenuOptionOrder[2], GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; mission objects
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: mission$="Water"
				Case 2: mission$="Lap Start"
				Case 3: mission$="Lap Checkpoint"
				Case 4: mission$="Destination Changer"
			End Select
			
			DrawRealText(mission$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; island objects
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: island$="Shop"
				Case 2: island$="Sound Test"
				Case 3: island$="Gallery"
				Case 4: island$="ATM"
			End Select
			
			DrawRealText(island$, GAME_WINDOW_W-(225)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
			If Input\Pressed\Down Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption+1
				If Game\Interface\DebugMenuOption>4 Then Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\Up Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenuOption-1
				If Game\Interface\DebugMenuOption<1 Then Game\Interface\DebugMenuOption=4
			EndIf
			
			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Game\Interface\DebugMenu=0
				
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_SKYDIVE
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_SKYDIVECANCEL
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_MACH
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_MACHCANCEL
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_VEHICLECANCEL
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_MUSIC
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_MUSIC+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_MUSIC+2000
								
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_WATER
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_LAPSTART
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_LAPCHECK
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_DEST
						End Select
					Case 4
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_SHOP
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_SOUNDTEST
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_SOUNDTEST
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_TRIGGER_ATM
						End Select	
						
						
				End Select
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
			DrawRealText("Mission card", GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*9)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
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
					Case 1: Game\Interface\DebugNewObj = OBJTYPE_TROPICAL
					Case 2: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER3
					Case 3: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER4
					Case 4: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER5
					Case 5: Game\Interface\DebugNewObj = OBJTYPE_TELEPORTER6
					Case 6: Game\Interface\DebugNewObj = OBJTYPE_TRASHCAN
					Case 7: Game\Interface\DebugNewObj = OBJTYPE_SACK
					Case 8: Game\Interface\DebugNewObj = OBJTYPE_GARDENPOINT
					Case 9: Game\Interface\DebugNewObj = OBJTYPE_MISSIONCARD
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
			DrawArrow(GAME_WINDOW_W-(255+85)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			SetColor(25,255,25)
			DrawRealText("("+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+"/"+Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]+")", GAME_WINDOW_W-(255+70)*GAME_WINDOW_SCALE#, (40-20*2+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			SetColor(255,255,255)
			Game\Interface\DebugMenuOptionOrder[10]=4
			Game\Interface\DebugMenuOptionOrder[20]=5
			Game\Interface\DebugMenuOptionOrder[30]=9
			Game\Interface\DebugMenuOptionOrder[40]=5
			Game\Interface\DebugMenuOptionOrder[50]=VISUAL_AMOUNT
			
			
			
			
			i=-1
			
			
			; checkpoints
			Select Game\Interface\DebugMenuOptionOrder[1]
				Case 1: spr$="Fire Sprinkler"
				Case 2: spr$="Fire Sprinkler Blue"
				Case 3: spr$="Water Sprinkler"
				Case 4: spr$="Confetti Sprinkler"
			End Select
			
			DrawRealText(spr$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; boxes
			Select Game\Interface\DebugMenuOptionOrder[2]
				Case 1: sea$="Butterfly"
				Case 2: sea$="Seagull"
				Case 3: sea$="Orca"
				Case 4: sea$="Dolphin"
				Case 5: sea$="Assorted fish"
			End Select
			
			DrawRealText(sea$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			; breakables
			Select Game\Interface\DebugMenuOptionOrder[3]
				Case 1: break$="Brown Rock"
				Case 2: break$="Grey Rock"
				Case 3: break$="Icicle 1"
				Case 4: break$="Icicle 2"
				Case 5: break$="Icicle 1 Big"
				Case 6: break$="Icicle 2 Big"
				Case 7: break$="Ice Decor 1"
				Case 8: break$="Ice Decor 2"
				Case 9: break$="Car"	
			End Select
			
			DrawRealText(break$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			; signs
			Select Game\Interface\DebugMenuOptionOrder[4]
				Case 1: unb$="Chair"
				Case 2: unb$="Parasol"
				Case 3: unb$="Air Balloon"
				Case 4: unb$="Helicopter"
				Case 5: unb$="Rainbow"
			End Select
			
			DrawRealText(unb$, GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			
			DrawRealText("Stage Visual" + Game\Interface\DebugMenuOptionOrder[5], GAME_WINDOW_W-(255)*GAME_WINDOW_SCALE#, (40+20*i)*GAME_WINDOW_SCALE#, (Interface_TextControls_1)) : i=i+1
			
			
			
			If Input\Pressed\Right And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]+1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]>Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10] Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=1
			EndIf
			
			If Input\Pressed\Left And Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]>1 Then
				PlaySmartSound(Sound_MenuMove)
				Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]=Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]-1
				If Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]<1 Then Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]= Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption*10]
			EndIf
			
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
				Game\Interface\DebugMenu=0
				
				Select Game\Interface\DebugMenuOption
					Case 1: 
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER
							Case 2: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+4000
							Case 3: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+1000
							Case 4: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+2000
							Case 5: Game\Interface\DebugNewObj = OBJTYPE_SPRINKLER+3000
						End Select
					Case 2
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_BUTTERFLY
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_SEAGULL
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_ORCA
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_ORCA+1000
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_SEAC
								
						End Select
					Case 3
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_ROCK
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_ROCK+1000
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_ICICLE
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_ICICLE+1000
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_ICICLEBIG
							Case 6: Game\Interface\DebugNewObj=OBJTYPE_ICICLEBIG+1000
							Case 7: Game\Interface\DebugNewObj=OBJTYPE_ICEDECOR
							Case 8: Game\Interface\DebugNewObj=OBJTYPE_ICEDECOR+1000
							Case 9: Game\Interface\DebugNewObj=OBJTYPE_CAR
						End Select
					Case 4
						Select Game\Interface\DebugMenuOptionOrder[Game\Interface\DebugMenuOption]
							Case 1: Game\Interface\DebugNewObj=OBJTYPE_CHAIR
							Case 2: Game\Interface\DebugNewObj=OBJTYPE_PARASOL
							Case 3: Game\Interface\DebugNewObj=OBJTYPE_AIRBALLOON
							Case 4: Game\Interface\DebugNewObj=OBJTYPE_HELICOPTER
							Case 5: Game\Interface\DebugNewObj=OBJTYPE_RAINBOW
						End Select
					Case 5
						Game\Interface\DebugNewObj=OBJTYPE_VISUAL[Game\Interface\DebugMenuOptionOrder[5]]
						
				End Select
				Game\Interface\DebugMenuOption=1
			EndIf
			If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
				PlaySmartSound(Sound_MenuBack)
				Game\Interface\DebugMenuOption=Game\Interface\DebugMenu-10
				Game\Interface\DebugMenu=DEBUGMENU_CHOOSE#
				Game\Interface\DebugNewObj=0
			EndIf
		Case DEBUGMENU_ATTRIBUTES#:
			DrawArrow(GAME_WINDOW_W-(240+15)*GAME_WINDOW_SCALE#, (40-20*1+20*Game\Interface\DebugMenuOption)*GAME_WINDOW_SCALE#)
			DrawRealText("Position", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			Select p\ObjType
				Case OBJTYPE_RING,OBJTYPE_TIMER,OBJTYPE_REDRING,OBJTYPE_WARPRING,OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS,OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000,OBJTYPE_BALLOON,OBJTYPE_GOAL,OBJTYPE_GOAL2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_BUBBLES,OBJTYPE_SHARD,OBJTYPE_HINT,OBJTYPE_COUNTER,OBJTYPE_BELL,OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+3000,OBJTYPE_SPRINKLER+4000,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_CLOUD,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_BOMBER2:
					SetColor(0,0,0)
				Default:
					SetColor(255,255,255)
			End Select
			DrawRealText("Rotation", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_PANEL1,OBJTYPE_PANEL2,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAILPAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_CANNON,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
					SetColor(255,255,255)
				Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
					SetColor(255,255,255)
				Case OBJTYPE_AIRBALLOON,OBJTYPE_RING,OBJTYPE_TIMER,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_CLOUD,OBJTYPE_POLE,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
					For vs = 1 To VISUAL_AMOUNT
						Select p\ObjType
							Case OBJTYPE_VISUAL[vs]
								SetColor(255,255,255)
						End Select
					Next
					
			End Select
			Select p\ObjType
				Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
					DrawRealText("Size", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000:
					DrawRealText("Size", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
					DrawRealText("Requirement", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
					DrawRealText("Water Level", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case OBJTYPE_RING,OBJTYPE_REDRING,OBJTYPE_TIMER
					DrawRealText("Value", GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Default:
					For vs = 1 To VISUAL_AMOUNT
						Select p\ObjType
							Case OBJTYPE_VISUAL[vs]
								name$="Size"
							Default
								name$="Power"
						End Select
					Next
					DrawRealText(name$, GAME_WINDOW_W-(240)*GAME_WINDOW_SCALE#, (40+20*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
			SetColor(255,255,255)
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAILPAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
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
				Case OBJTYPE_EGUNNER,OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2:
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
				Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_RAILPAD,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR,OBJTYPE_PANEL1,OBJTYPE_PANEL2,OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE:
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
							DrawRealText("Change to counter5 and so on in xml.", GAME_WINDOW_W-(560)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Case OBJTYPE_NODE:
							SetColor(255,255,0)
							DrawRealText("Change to node2 in xml for speed affecting.", GAME_WINDOW_W-(660)*GAME_WINDOW_SCALE#, (40+20*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
						Case OBJTYPE_TELEPORTER2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_WARPRING:
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
			Select Game\Interface\DebugMoveType
				Case 0
					DrawRealText("Movement type: Directional", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 1
					DrawRealText("Movement type: Directional Incremental", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 2
					DrawRealText("Movement type: Axis", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 3
					DrawRealText("Movement type: Axis Incremental", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
			
			DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
			
			Select Game\Interface\DebugCollision
				Case 0
					DrawRealText("Collision: Off", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Case 1
					DrawRealText("Collision: On", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
			
			
			DrawSmartKey(INPUT_BUTTON_BACK, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Stamp Object", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			
			
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, (30)*GAME_WINDOW_SCALE#, (30+30*6)*GAME_WINDOW_SCALE#)
			DrawRealText("Cycle Object", (30+15)*GAME_WINDOW_SCALE#, (30+30*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*7)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
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
			
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, (30)*GAME_WINDOW_SCALE#, (30+30*6)*GAME_WINDOW_SCALE#)
			DrawRealText("Done", (30+15)*GAME_WINDOW_SCALE#, (30+30*6)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			DrawSmartKey(INPUT_BUTTON_BACK, (30)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#)
			DrawRealText("Stamp Object", (30+15)*GAME_WINDOW_SCALE#, (30+30*5)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, (30)*GAME_WINDOW_SCALE#, (30+30*7)*GAME_WINDOW_SCALE#)
			DrawRealText("Cycle Object", (30+15)*GAME_WINDOW_SCALE#, (30+30*7)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
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
		Case OBJTYPE_RING,OBJTYPE_TIMER,OBJTYPE_REDRING,OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS,OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000,OBJTYPE_BALLOON,OBJTYPE_GOAL,OBJTYPE_GOAL2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_BUBBLES,OBJTYPE_SHARD,OBJTYPE_HINT,OBJTYPE_COUNTER,OBJTYPE_BELL,OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+3000,OBJTYPE_SPRINKLER+4000,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_CLOUD,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_BOMBER2:
			SetColor(0,0,0)
		Default:
			SetColor(255,255,255)
	End Select
	DrawRealText("pitch: "+TempAttribute\pitch#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText("yaw: "+TempAttribute\yaw#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	DrawRealText("roll: "+TempAttribute\roll#, 32.5*GAME_WINDOW_SCALE#+(space#*5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	
	Select p\ObjType
		Case OBJTYPE_SPRING,OBJTYPE_PANEL1,OBJTYPE_PANEL2,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_CANNON,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
			SetColor(255,255,255)
		Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
			SetColor(255,255,255)
		Case OBJTYPE_AIRBALLOON,OBJTYPE_RING,OBJTYPE_TIMER,OBJTYPE_REDRING,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_CLOUD,OBJTYPE_POLE,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select

	DrawRealText("power: "+TempAttribute\power#, 32.5*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-120*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
	
	SetColor(255,255,255)
	
	Select p\ObjType
		Case OBJTYPE_SPRING,OBJTYPE_PANEL1,OBJTYPE_PANEL2,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
			SetColor(255,255,255)
		Default:
			SetColor(0,0,0)
	End Select
	Select TempAttribute\lockcontrol#
		Case 0:
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
					SetColor(255,0,0)
				Default:
					SetColor(0,0,0)
			End Select
			DrawRealText("Cntrl lock: None", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
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
		Case 7: DrawRealText("ALL LOCKS IGNORED", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		Case 8: DrawRealText("Lock player dir to obj yaw", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
		Case 9: DrawRealText("Quickstep", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			
			
	End Select
	Select TempAttribute\lockcam#
		Case 0:
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
					SetColor(255,0,0)
				Default:
					SetColor(0,0,0)
			End Select
			DrawRealText("Cam lock: None", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*1)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
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
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
					SetColor(255,0,0)
				Default:
					SetColor(0,0,0)
			End Select
			DrawRealText("Run lock: None", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-43.75*GAME_WINDOW_SCALE#+(space#*2)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
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
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
					SetColor(255,0,0)
				Default:
					SetColor(0,0,0)
			End Select
			DrawRealText("cpos: no", 32.5*GAME_WINDOW_SCALE#+(space#*11)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-88.75*GAME_WINDOW_SCALE#+(space#*0)*GAME_WINDOW_SCALE#, (Interface_Text_1))
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
					SetColor(255,255,255)
				Default:
					SetColor(0,0,0)
			End Select
		Default:
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
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
		Case OBJTYPE_EGUNNER,OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2:
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
		CameraProject cam\Entity, o\Position\x#, o\Position\y#+height#, o\Position\z#
		x = ProjectedX () - 1
		y = ProjectedY () - 1
		DrawRealText(Text$, x, y, Interface_Text_1)
	EndIf
End Function





Function Interface_Render_Stage_Debug_DrawSquare(Color, x#, y#, w, h, space#,mode=0)
	
	If mode=0 Then im=Interface_Debug Else im=Interface_Shop
	
	For i=1 To w
	For j=1 To h
		Select j
			Case 1:
				Select i
					Case 1: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 0+(Color-1)*3)
					Case w: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 2+(Color-1)*3)
					Default: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 1+(Color-1)*3)
				End Select
			Case h:
				Select i
					Case 1: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 0+24+(Color-1)*3)
					Case w: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 2+24+(Color-1)*3)
					Default: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 1+24+(Color-1)*3)
				End Select
			Default:
				Select i
					Case 1: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 0+12+(Color-1)*3)
					Case w: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 2+12+(Color-1)*3)
					Default: DrawImageEx(INTERFACE(SmartImage(im)), x#+(i-1)*space#*GAME_WINDOW_SCALE#, y#+(j-1)*space#*GAME_WINDOW_SCALE#, 1+12+(Color-1)*3)
				End Select
		End Select
	Next
	Next

End Function
;~IDEal Editor Parameters:
;~C#Blitz3D