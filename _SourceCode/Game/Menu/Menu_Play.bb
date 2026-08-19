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
		Case MISSION_LAP#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 33)
		Case MISSION_ESCAPE#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 34)
		Case MISSION_COLLECT#:	DrawImageEx(INTERFACE(Interface_Icons2), x#, y#, 0)
		Case MISSION_ENCORE#:	DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 35)
			
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
	
	If Menu\Menu=MENU_STAGE2# Then i=(Menu\Option+Menu\OptionOrder2*4) Else i=Menu\Option
	
	If missionno=5 And ALLREDRING(i)=0 Then DrawImageEx(INTERFACE(Interface_Icons), x#, y#, 1)
	
End Function

Function Menu_Stage_Draw_MissionSelector(x#, y#)
	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	SetColor(Menu_ReturnCardColor(1,Menu\Character[1],True,False,True),Menu_ReturnCardColor(2,Menu\Character[1],True,False,True),Menu_ReturnCardColor(3,Menu\Character[1],True,False,True))
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
	
	Select Menu\Menu
		Case MENU_STAGE2#:stageno=Menu\Option+Menu\OptionOrder2*4
		Default: stageno=Menu\Option
	End Select
	
	If ALLREDRING(stageno)=1 Or Menu\Developer=1 Then i = 5 Else i = 4
	
	If Input\Pressed\Right And (Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=1) Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MissionNo=Menu\MissionNo+1
		Menu\LoadThumbnailAndMissions=True
		
		If Menu\MissionNo>i Then Menu\MissionNo=1
	EndIf

	If Input\Pressed\Left And (Menu\Menu=MENU_STAGE# Or Menu\OptionOrder=1) Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MissionNo=Menu\MissionNo-1
		Menu\LoadThumbnailAndMissions=True
		If Menu\MissionNo<1 Then Menu\MissionNo=i
	EndIf
	
	If Menu\Menu=MENU_STAGE# Then s=Menu\Option Else s=(Menu\Option+4*Menu\OptionOrder2)
	
	If Input\Pressed\ActionJump  Then
		If (Menu\Menu=MENU_STAGE# Or ((Menu\Option+4*Menu\OptionOrder2)<=StageAmount)) And StageLocked(s)=0 Then
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
			Menu\NewOption=1 : Menu\NewMenu=MENU_PLAY#
		Else
			Menu\OptionOrder=0
		EndIf
	EndIf

	If Input\Pressed\ActionDrift Then Menu_Stage_RandomizeTeam()
End Function

Function Menu_Stage_RandomizeTeam(nosound=False)
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
		If Menu\MissionNo=5 Then
			SetColor(Rand(155,255),Rand(155,255),Rand(155,255))
		Else
			SetColor(255,115,0)
		EndIf
		
		Select StageMission[Menu\MissionNo]
			Case 0 : k$="Normal Run"
			Case 1 : k$="Destructor"
			Case 2 : k$="Ring Collector"
			Case 3 : k$="Treasure Hunt"
			Case 4 : k$="Gold Rush"
			Case 5 : k$="Stealth"
			Case 6 : k$="Confetti Parade"
			Case 7 : k$="Free Roam"
			Case 8 : k$="Rival Battle"
			Case 9 : k$="Robot Carnival"
			Case 10 : k$="Boss Battle"
			Case 11 : k$="Flicky Rescue"
			Case 12 : k$="Race"
			Case 13 : k$="Escape"
			Case 15 : k$="Collectathon"
			Case 14 : k$="Encore"
		End Select
		If StageLocked(i)=1 Then k$=UnlockReq$(i)
		DrawRealText(k$, x#, y#-compressy#, (Interface_TextControls_1), 1):
		SetColor(255,255,255)		
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
	DrawImageEx(INTERFACE(Interface_Square2), x#, y#-110*GAME_WINDOW_SCALE#)
		If StageOrigin[Menu\MissionNo]=-1 Then
			;DrawImageEx(INTERFACE(Interface_Origins), x#+70*GAME_WINDOW_SCALE#, y#-172*GAME_WINDOW_SCALE#,5)
			DrawImageEx(INTERFACE(Interface_CustomOrigin), x#+70*GAME_WINDOW_SCALE#, y#-172*GAME_WINDOW_SCALE#)
		Else
			DrawImageEx(INTERFACE(Interface_Origins), x#+70*GAME_WINDOW_SCALE#, y#-172*GAME_WINDOW_SCALE#,StageOrigin[Menu\MissionNo]-1)
		EndIf
End Function

Function Menu_Stage2_Update()

	For i=1 To 4
	For j=1 To 3
		stageno=i+(j-1)*4
		If Menu\Option=stageno And Menu\OptionOrder=0 Then
			SetScale(GAME_WINDOW_SCALE#*0.325+Menu\ButtonSize#*0.4, GAME_WINDOW_SCALE#*0.325+Menu\ButtonSize#*0.4)
			SetColor(255,255,255)
		ElseIf Menu\OptionOrder=1 Then
			SetScale(GAME_WINDOW_SCALE#*0.325, GAME_WINDOW_SCALE#*0.325)
			SetColor(25,25,25)
		Else
			SetScale(GAME_WINDOW_SCALE#*0.325, GAME_WINDOW_SCALE#*0.325)
			SetColor(100,100,100)
		EndIf
		DrawImageEx(INTERFACE(Interface_StageSelectThumbnails[stageno]), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(Interface_Square), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(255,255,255)
		If Menu\OptionOrder=0 Then
			If j=1 And (Menu\Option)=i Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#-45*GAME_WINDOW_SCALE#,20)
			If j=3 And (Menu\Option-8)=i Then DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-260*GAME_WINDOW_SCALE#+(i-1)*90*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*3-60)*GAME_WINDOW_SCALE#+(j-1)*70*GAME_WINDOW_SCALE#+45*GAME_WINDOW_SCALE#,21)
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
			Menu\MissionNo=1
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
			Menu\MissionNo=1
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
			Menu\MissionNo=1
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
			Menu\MissionNo=1
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
						If Not(Menu\Option<=4 And Menu\OptionOrder2=0) Then
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
			Menu\MissionNo=Rand(1,4)
			Menu_Stage_LoadStageSelectThumbnails()
			Menu\LoadThumbnailAndMissions=True
		EndIf
	EndIf

End Function

Function Menu_Stage_DrawRecords(specialstage#=False,x#=0,y#=0)
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

	If Not specialstage# Then Menu_Stage_DrawRecords_Emblems(x#,y#)
End Function
Function Menu_Stage_DrawRecords_Emblems(x#=0,y#=0)
	Select Menu\Menu
		Case MENU_STAGE2#: stageno=(Menu\Option+Menu\OptionOrder2*4)
		Default: stageno=(Menu\Option) 
	End Select
	
		If stageno<=StageAmount Then
			For i = 1 To 5
				If REDRING(i,stageno)=0 Then
					SetAlpha(0.75)
					SetColor(7.5,7.5,7.5)
				Else
					SetAlpha(1)
					SetColor(255,255,255)
				EndIf
				
				If Menu\Menu=MENU_STAGE2# Then 
					DrawImageEx(INTERFACE(Interface_Icons), x#+(((7*GAME_WINDOW_SCALE#)))+(GAME_WINDOW_W/2)+12*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-(315-(25*i))*GAME_WINDOW_SCALE#, y#+(GAME_WINDOW_H/2)+27*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#+35*GAME_WINDOW_SCALE#, 32)
				Else
					
					DrawImageEx(INTERFACE(Interface_Icons), x#+(((7*GAME_WINDOW_SCALE#)))+(GAME_WINDOW_W/2)+12*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-(182-(25*i))*GAME_WINDOW_SCALE#, y#+(GAME_WINDOW_H/2)+27*GAME_WINDOW_SCALE#+45*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#+35*GAME_WINDOW_SCALE#, 32)
				EndIf
				
			Next
			If EMBLEMS1(Menu\MissionNo,stageno)=0 Then
				SetAlpha(0.75)
				SetColor(7.5,7.5,7.5)
			Else
				SetAlpha(1)
				SetColor(255,255,255)
			EndIf
			DrawImageEx(INTERFACE(Interface_Icons), x#+(((7*GAME_WINDOW_SCALE#)))+(GAME_WINDOW_W/2)+12*GAME_WINDOW_SCALE#-(BUTTON_PLACE1#-56)*GAME_WINDOW_SCALE#-160*GAME_WINDOW_SCALE#, y#+(GAME_WINDOW_H/2)+27*GAME_WINDOW_SCALE#+20*GAME_WINDOW_SCALE#+30*GAME_WINDOW_SCALE#+35*GAME_WINDOW_SCALE#, 4)
			If EMBLEMS2(Menu\MissionNo,stageno)=0 Then
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
		Menu\MissionNo=1
		Menu\Option=Menu\Option+1
		If Menu\Option>StageAmount Then Menu\Option=1
		Menu\LoadThumbnailAndMissions=True
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\MissionNo=1
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=StageAmount
		Menu\LoadThumbnailAndMissions=True
	EndIf
End Function


Function Menu_Stage_RecordZeroCharacterTester(value, h, i)
	If RECORDS_CURRENT<>i Then LoadGame_Records(Menu\StageName$)
	Select value
		Case 1: Return RECORDS_RINGS(h,4)
		Case 2: Return RECORDS_ENEMIES(h,4)
		Case 3: Return RECORDS_TIME(h,4)
		Case 4: Return RECORDS_SCORE(h,4)
		Case 5: Return RECORDS_RANK(h,4)
	End Select
End Function

Function Menu_Stage_ReturnRecordValue(value, h, i, r=0)
	If RECORDS_CURRENT<>i Then LoadGame_Records(Menu\StageName$)
	Select value
		Case 1: Return RECORDS_RINGS(h,r)
		Case 2: Return RECORDS_ENEMIES(h,r)
		Case 3: Return RECORDS_TIME(h,r)
		Case 4: Return RECORDS_SCORE(h,r)
		Case 5: Return RECORDS_RANK(h,r)
	End Select
End Function
Function Menu_Stage_LoadInfo(xmlfile,ideals)
	For i = 1 To xmlNodeChildCount(xmlfile)
		
		child = xmlNodeChild(xmlfile, i)
		
		Select xmlNodeNameGet$(child)
			Case "mission1","mission2","mission3","mission4","mission5","mission0":
				j = Int(Mid$(xmlNodeNameGet$(child),8,1))
				StageMission[j] = xmlNodeAttributeValueGet(child, "is")
				StageMissionTime[j] = xmlNodeAttributeValueGet(child, "time")
				StageMissionMach[j] = xmlNodeAttributeValueGet(child, "mach")
				StageMissionPerfect[j] = xmlNodeAttributeValueGet(child, "perfect")
				StageOrigin[j] = xmlNodeAttributeValueGet(child, "origin")
				
				Select Menu\Menu
					Case MENU_STAGE2#: stageno=(Menu\Option+Menu\OptionOrder2*4)
					Default: stageno=(Menu\Option) 
				End Select
				If FileType(StagePath$(stageno)+"/Media/originm"+Str(j)+".png")=1 Then StageOrigin[j]=-1
					
					
				
				
				If ideals Then
					If j=Menu\MissionNo Then
						Menu\ForceMembers=0
						Menu\ForceMembers=xmlNodeAttributeValueGet(child, "members")
						If Menu\ForceMembers>0 Then
								For l = 1 To Menu\ForceMembers
									Menu\ForceCharacter[l]=xmlNodeAttributeValueGet(child, "char"+Str(l))
								Next
						EndIf
						Game\LimitTime = xmlNodeAttributeValueGet(child, "maxtime")
						If Game\LimitTime=0 Then Game\LimitTime=999
						Game\LimitTime=Game\LimitTime*secs#
						
						Game\IdealTime = (Int(xmlNodeAttributeValueGet(child, "idealtime")))*secs#	
						Game\IdealScore = (Int(xmlNodeAttributeValueGet(child, "idealscore")))
						Menu\MissionInfo$=xmlNodeAttributeValueGet(child, "info")
						Menu\MissionTag$=xmlNodeAttributeValueGet(child, "tag")
						Menu\StageAuthor$=xmlNodeAttributeValueGet(child, "author")
					EndIf
				EndIf
		End Select
		
	Next
	
	xmlNodeDelete(xmlfile)
End Function
Function Menu_Stage_LoadMissions(stageno, loadideals=False,mode=0)
	
	If stageno>StageAmount Then Return
	
	For i=0 To 5
		StageMission[i]=0
		StageMissionTime[i]=0
		StageMissionMach[i]=0
		StageMissionPerfect[i]=0
	Next
	
	Select mode
		Case 0
			If (Not(stageno>StageAmount Or stageno<0)) Then
				If (FileType(StagePath$(stageno)+"/Media/Missions.xml")=1) Then
					xmlin = xmlLoad(StagePath$(stageno)+"/Media/Missions.xml")
				Else
					xmlin = xmlLoad("Interface/Missions.xml")
				EndIf
			EndIf
		Case 1
			xmlin = xmlLoad(Menu\WarpRingPath$+"/Media/missions.xml")
	End Select
	
	Menu_Stage_LoadInfo(xmlin,loadideals)
	
	For i=0 To 5
		If StageMission[i]>MISSIONCOUNT# Then StageMission[i]=0
	Next
End Function

Function Menu_Stage_LoadThumbnail(stageno)
	If INTERFACE_EXISTS(Interface_Thumbnail) Then FreeSmartImage(Interface_Thumbnail)
	
	If stageno>StageAmount Then
		LoadSmartFastImage("Interface/NoThumbnail2.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
	ElseIf StageLocked(stageno)=1 And stageno<=StageAmount Then
		LoadSmartFastImage("Interface/Locked.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
	ElseIf Not(FileType(StagePath$(stageno)+"/Media/thumbnail.png")=1) Then
		LoadSmartFastImage("Interface/NoThumbnail.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
	Else
		If FileType(StagePath$(stageno)+"/Media/thumbnailm"+Menu\MissionNo+".png")=1 Then
			LoadSmartFastImage(StagePath$(stageno)+"/Media/thumbnailm"+Menu\MissionNo+".png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
		Else
			LoadSmartFastImage(StagePath$(stageno)+"/Media/thumbnail.png", Interface_Thumbnail, 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
		EndIf
		
	EndIf
	
	If StageOrigin[Menu\MissionNo] = -1 Then 
		If INTERFACE_EXISTS(Interface_CustomOrigin) Then FreeSmartImage(Interface_CustomOrigin)
		LoadSmartFastImage(StagePath$(stageno)+"/Media/originm"+Str(Menu\MissionNo)+".png", Interface_CustomOrigin, 128, 128, 0, 1, 2, 2)
	EndIf
	
End Function

Function Menu_Stage_LoadThumbnailAndMissions(stageno)
	
	Menu_Stage_LoadMissions(stageno)
	Menu_Stage_LoadThumbnail(stageno)
End Function

Function Menu_Stage_LoadStageSelectThumbnails()
For i=1 To 12
	If INTERFACE_EXISTS(Interface_StageSelectThumbnails[i]) Then FreeSmartImage(Interface_StageSelectThumbnails[i])
	stageno = i+4*Menu\OptionOrder2
	If stageno>StageAmount Then
		LoadSmartFastImage("Interface/NoThumbnail2.png", Interface_StageSelectThumbnails[i], 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
	ElseIf Not(FileType(StagePath$(stageno)+"/Media/thumbnail.png")=1) Then
		LoadSmartFastImage("Interface/NoThumbnail.png", Interface_StageSelectThumbnails[i], 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
	Else
		LoadSmartFastImage(StagePath$(stageno)+"/Media/thumbnail.png", Interface_StageSelectThumbnails[i], 400, 308, 0, 1, 2.03695, 2.03695, False, False, True)
	EndIf
Next
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------------------------
;~IDEal Editor Parameters:
;~C#Blitz3D