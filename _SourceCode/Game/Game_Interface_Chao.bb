
Global boxspacing#=33.625

Function DrawBox(x#,y#,heightsize#=1)
	For i=1 To heightsize#
		If (i=1) Then
			DrawImageEx(INTERFACE(Interface_Boxes), x#, y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#, 1)
		ElseIf (i>1 And i<heightsize#) Then
			DrawImageEx(INTERFACE(Interface_Boxes), x#, y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#, 2)
		ElseIf (i=heightsize#) Then
			DrawImageEx(INTERFACE(Interface_Boxes), x#, y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#, 3)
		EndIf
	Next
End Function

Function DrawStatsBox(textt$,statslevel,x#,y#)
	DrawImageEx(INTERFACE(Interface_Boxes), x#, y#, 0)
	DrawRealText(textt$,x#-(56.75)*GAME_WINDOW_SCALE#, y#-(6.5)*GAME_WINDOW_SCALE#, Interface_Text_2,0,0,255,255,255,1.35)
	SetColor(255,255,0)
	DrawRealText("Lv."+statslevel, x#+(55)*GAME_WINDOW_SCALE#, y#-(4)*GAME_WINDOW_SCALE#, Interface_Text_2,2,0,255,255,255,1.35)
	SetColor(255,255,255)
End Function

Function DrawStats(statshandle, x#, y#, statno#)
	spacing#=10.5
	If statshandle<10 Then
		For n=statshandle To 10-1
			DrawImageEx(INTERFACE(Interface_Stats), x#+((spacing#)*n)*GAME_WINDOW_SCALE#, y#, 0)
		Next
	EndIf
	If statshandle>0 Then
		For n=1-1 To statshandle-1
			DrawImageEx(INTERFACE(Interface_Stats), x#+((spacing#)*n)*GAME_WINDOW_SCALE#, y#, 1+statno#)
		Next
	EndIf
	If (statshandle)<10 Then DrawImageEx(INTERFACE(Interface_Stats), x#+((spacing#)*(statshandle))*GAME_WINDOW_SCALE#, y#, 1)
End Function

Function DrawChaoStats(cc.tChaoManager, x#, y#)
	DrawBox(x#, y#+(5+boxspacing#*(-1))*GAME_WINDOW_SCALE#, 8)
	DrawRealText(cc\Name$, x#-58*GAME_WINDOW_SCALE#, y#-26*GAME_WINDOW_SCALE#, Interface_TextTitleChao_1, 0, 0, 63, 63, 63, 1.65)
	For i = 1 To 7
		Select i
		Case 1: DrawStatsBox("Run:",cc\Stats\Skills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 2: DrawStatsBox("Swim:",cc\Stats\Skills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 3: DrawStatsBox("Fly:",cc\Stats\Skills[i],x#,y#+(+boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 4: DrawStatsBox("Strength:",cc\Stats\Skills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 5: DrawStatsBox("Stamina:",cc\Stats\Skills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 6: DrawStatsBox("Intelligence:",cc\Stats\Skills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 7: DrawStatsBox("Luck:",cc\Stats\Skills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		End Select
		DrawStats(cc\Stats\CurrentSkills[i], x#-47*GAME_WINDOW_SCALE#, y#+(6.5+boxspacing#*(i-1))*GAME_WINDOW_SCALE#, i)
	Next
	j# = (cc\Stats\HeroLove#-cc\Stats\DarkLove#+100)/200.0
	SetColor(255*j#,255*j#,255*j#)
	DrawImageEx(INTERFACE(Interface_Icons), x#-45*GAME_WINDOW_SCALE#, y#+(boxspacing#*7)*GAME_WINDOW_SCALE#, 30)
	j# = (cc\Stats\Happiness#+100)/200.0
	SetColor(255*j#,255*j#,255*j#)
	DrawImageEx(INTERFACE(Interface_Icons), x#-22.5*GAME_WINDOW_SCALE#, y#+(boxspacing#*7)*GAME_WINDOW_SCALE#, 31)
	SetColor(255,255,255)
	;Hatch
	j# = (cc\HatchTimer/100/10)
	SetColor(255,255,255)
	DrawImageEx(INTERFACE(Interface_Icons), x#+38*GAME_WINDOW_SCALE#, y#+(boxspacing#*7)*GAME_WINDOW_SCALE#, 11)
	DrawRealText(j#, x#+55*GAME_WINDOW_SCALE#, y#+(boxspacing#*7)*GAME_WINDOW_SCALE#, Interface_Text_2,2,0,255,255,255,1.35)
	SetColor(255,255,255)
	;Character
	j# = (cc\Stats\CharacterLove[pp(1)\Character]+100)/200.0
	SetColor(255*j#,255*j#,255*j#)	
	Interface_DrawHead(x#-45*GAME_WINDOW_SCALE#, y#+(boxspacing#*8)*GAME_WINDOW_SCALE#, pp(1)\Character)
	SetColor(255,255,255)
End Function

Function DrawChaoStats_Menu(x#, y#)
	DrawBox(x#, y#+(5+boxspacing#*(-1))*GAME_WINDOW_SCALE#, 8)
	DrawRealText(Menu\HeldChaoName$, x#-58*GAME_WINDOW_SCALE#, y#-26*GAME_WINDOW_SCALE#, Interface_TextTitleChao_1, 0, 0, 63, 63, 63, 1.65)
	For i = 1 To 7
		Select i
		Case 1: DrawStatsBox("Run/Speed:",Menu\HeldChaoSkills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 2: DrawStatsBox("Swim/Defense:",Menu\HeldChaoSkills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 3: DrawStatsBox("Fly/Stealth:",Menu\HeldChaoSkills[i],x#,y#+(+boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 4: DrawStatsBox("Strength:",Menu\HeldChaoSkills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 5: DrawStatsBox("Stamina:",Menu\HeldChaoSkills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 6: DrawStatsBox("Intelligence:",Menu\HeldChaoSkills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		Case 7: DrawStatsBox("Luck:",Menu\HeldChaoSkills[i],x#,y#+(boxspacing#*(i-1))*GAME_WINDOW_SCALE#)
		End Select
		DrawStats(Menu\HeldChaoCurrentSkills[i], x#-47*GAME_WINDOW_SCALE#, y#+(6.5+boxspacing#*(i-1))*GAME_WINDOW_SCALE#, i)
		DrawRealText("Likes " + FRUITS(Menu\HeldChaoFavouriteFood), x#*GAME_WINDOW_SCALE#, y#+(boxspacing#*7)*GAME_WINDOW_SCALE#, Interface_Text_2,2,0,255,255,255,1.35)
	Next
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Type tGame_Interface_ChaoMsg
	Field msg$
	Field timer
	Field r,g,b
	Field no
	Field overlap
End Type

Function Interface_CreateChaoMsg(msg$, r=255, g=255, b=255, overlap=0)
	chaomsg.tGame_Interface_ChaoMsg = new tGame_Interface_ChaoMsg
	chaomsg\msg$=msg$
	chaomsg\r=r : chaomsg\g=g : chaomsg\b=b
	chaomsg\timer=4*secs#
	For otherchaomsg.tGame_Interface_ChaoMsg = Each tGame_Interface_ChaoMsg
		otherchaomsg\no=otherchaomsg\no+1
		If otherchaomsg\no>10 Then Delete otherchaomsg
	Next
	chaomsg\no=1
	chaomsg\overlap=overlap
End Function

Function Interface_Create2ChaoMsg(msg$, msg2$, r=255, g=255, b=255)
	Interface_CreateChaoMsg(msg$, r, g, b)
	Interface_CreateChaoMsg(msg2$, r, g, b)
End Function

Function Interface_CreateChaoNamedMsg(msg$, name$, r=255, g=255, b=255)
	If (name$="") Then
		msg$="A chao "+msg$
	Else
		msg$=name$+" "+msg$
	EndIf
	Interface_CreateChaoMsg(msg$, r, g, b)
End Function

Function Interface_CreateOverlappingChaoMsg(overlap, msg$, r=255, g=255, b=255)
	foundoverlap=false
	For otherchaomsg.tGame_Interface_ChaoMsg = Each tGame_Interface_ChaoMsg
		If otherchaomsg\overlap=overlap Then foundoverlap=True
	Next
	If foundoverlap=false Then
		Interface_CreateChaoMsg(msg$, r, g, b, overlap)
	EndIf
End Function

Function Interface_CreateOverlapping2ChaoMsg(overlap, msg$, msg2$, r=255, g=255, b=255)
	foundoverlap=false
	For otherchaomsg.tGame_Interface_ChaoMsg = Each tGame_Interface_ChaoMsg
		If otherchaomsg\overlap=overlap Then foundoverlap=True
	Next
	If foundoverlap=false Then
		Interface_CreateChaoMsg(msg$, r, g, b, overlap)
		Interface_CreateChaoMsg(msg2$, r, g, b, overlap)
	EndIf
End Function

Function Interface_DeleteChaoMsg(chaomsg.tGame_Interface_ChaoMsg)
	For otherchaomsg.tGame_Interface_ChaoMsg = Each tGame_Interface_ChaoMsg
		If otherchaomsg\no>chaomsg\no Then otherchaomsg\no=otherchaomsg\no-1
	Next
	Delete chaomsg
End Function

Function Interface_ActivateGardenAction(gardenactionno, gardenactiomsg$, overlapping=0)
	If Game\Interface\GardenActionOverlapping[gardenactionno]=0 Or (overlapping=1 and Game\Interface\GardenActionOverlapping[gardenactionno]=1) Then
		Select gardenactionno
			Case 3: Game\Interface\GardenActionTimer[gardenactionno]=0.05*secs#
			Default: Game\Interface\GardenActionTimer[gardenactionno]=0.5*secs#
		End Select
		Select gardenactionno
			Case 1: Game\Interface\GardenActionMsg1$=gardenactiomsg$
			Case 2: Game\Interface\GardenActionMsg2$=gardenactiomsg$
			Case 3: Game\Interface\GardenActionMsg3$=gardenactiomsg$
		End Select
		If overlapping=1 Then Game\Interface\GardenActionOverlapping[gardenactionno]=1
	EndIf
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function DrawKarateHealth(health, x#, y#, backwards=false)

	If health<0 Then health=0

	spacefactor#=0.8
	If backwards Then spacefactor#=-spacefactor#

	For i=health+1 to 250
		Select backwards
		Case false:
			Select i
			Case 1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+1-1)
			Case 2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+2-1)
			Case 3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+3-1)
			Case 4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+4-1)
			Case 5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+5-1)
			Case 6: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+6-1)
			Case 250-5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+1-1)
			Case 250-4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+2-1)
			Case 250-3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+3-1)
			Case 250-2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+4-1)
			Case 250-1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+5-1)
			Case 250-0: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+6-1)
			Default: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7-1)
			End Select
		Case true:
			Select i
			Case 250-5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+6-1)
			Case 250-4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+5-1)
			Case 250-3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+4-1)
			Case 250-2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+3-1)
			Case 250-1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+2-1)
			Case 250-0: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+1-1)
			Case 1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+6-1)
			Case 2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+5-1)
			Case 3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+4-1)
			Case 4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+3-1)
			Case 5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+2-1)
			Case 6: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7+1-1)
			Default: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 13+7-1)
			End Select
		End Select
	Next

	If health>0 Then
	For i=1 to health
		Select backwards
		Case false:
			Select i
			Case 1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 1-1)
			Case 2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 2-1)
			Case 3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 3-1)
			Case 4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 4-1)
			Case 5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 5-1)
			Case 6: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 6-1)
			Case 250-5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+1-1)
			Case 250-4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+2-1)
			Case 250-3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+3-1)
			Case 250-2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+4-1)
			Case 250-1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+5-1)
			Case 250-0: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+6-1)
			Default: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7-1)
			End Select
		Case true:
			Select i
			Case 250-5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 6-1)
			Case 250-4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 5-1)
			Case 250-3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 4-1)
			Case 250-2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 3-1)
			Case 250-1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 2-1)
			Case 250-0: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 1-1)
			Case 1: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+6-1)
			Case 2: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+5-1)
			Case 3: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+4-1)
			Case 4: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+3-1)
			Case 5: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+2-1)
			Case 6: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7+1-1)
			Default: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 7-1)
			End Select
		End Select
	Next
	EndIf

End Function

Function DrawKarateZeal(zeal, x#, y#, backwards=false)

	If zeal<0 Then zeal=0

	spacefactor#=0.8
	If backwards Then spacefactor#=-spacefactor#

	For i=zeal+1 to 285
		Select backwards
		Case false: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 41+((i-1) Mod 15))
		Case true: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 30+41+abs(15-((i) Mod 15)))
		End Select
	Next

	If zeal>0 Then
	For i=1 to zeal
		Select backwards
		Case false: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 26+((i-1) Mod 15))
		Case true: DrawImageEx(INTERFACE(Interface_KarateBars), x#+spacefactor#*i*GAME_WINDOW_SCALE#, y#, 30+26+abs(15-((i) Mod 15)))
		End Select
	Next
	EndIf

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function Interface_Render_Stage_Chao(p.tPlayer,d.tDeltatime)

	;show stats
	If Game\Interface\ShowChaoTimer>0 Then
		For cc.tChaoManager=Each tChaoManager
		If cc\Number=Game\Interface\ShowChaoNumber Then
			If ChaoManager_ChaoAlive(cc) Then DrawChaoStats(cc,130*GAME_WINDOW_SCALE#,75*GAME_WINDOW_SCALE#)
			If Game\Interface\ShowFPS=1 Then
				Select cc\Stats\Age
					Case 0: DrawRealText("Shell grit: "+cc\Stats\ShellGrit, 80*GAME_WINDOW_SCALE#,330*GAME_WINDOW_SCALE#, (Interface_Text_2))
					Default: DrawRealText("Happiness: "+cc\Stats\Happiness, 80*GAME_WINDOW_SCALE#,330*GAME_WINDOW_SCALE#, (Interface_Text_2))
				End Select
			EndIf
		EndIf
		Next
	EndIf

	; auto saving
	If Game\Interface\AutoSaveShowTimer>0 Then
		DrawImageEx(INTERFACE(Interface_Saving), 18*GAME_WINDOW_SCALE#, 18*GAME_WINDOW_SCALE#)
		DrawRealText("Auto-saving...", 32*GAME_WINDOW_SCALE#, 22*GAME_WINDOW_SCALE#, (Interface_Text_2))
		Game\Interface\AutoSaveShowTimer=Game\Interface\AutoSaveShowTimer-timervalue#
	EndIf

	;chaomsg system
	If Menu\Stage=999 Then
		For chaomsg.tGame_Interface_ChaoMsg = Each tGame_Interface_ChaoMsg
			SetColor(chaomsg\r,chaomsg\g,chaomsg\b)
			DrawRealText(chaomsg\msg$, GAME_WINDOW_W-17.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(chaomsg\no*15*GAME_WINDOW_SCALE#), (Interface_Text_2), 2)
			SetColor(255,255,255)
			If chaomsg\timer>0 Then chaomsg\timer=chaomsg\timer-timervalue# Else Interface_DeleteChaoMsg(chaomsg)
		Next
	EndIf

	;garden action system
	gardenactionposy#=0
	For i=1 to 3
		If Game\Interface\GardenActionTimer[i]>0 Then
			Game\Interface\GardenActionTimer[i]=Game\Interface\GardenActionTimer[i]-timervalue#
			Select i
				Case 1: gardenactionmsg$=Game\Interface\GardenActionMsg1$
				Case 2: gardenactionmsg$=Game\Interface\GardenActionMsg2$
				Case 3: gardenactionmsg$=Game\Interface\GardenActionMsg3$
			End Select
			If Len(gardenactionmsg$)<=3 Then
				gardenactionposx#=Len(gardenactionmsg$)*28.75*GAME_WINDOW_SCALE#
			ElseIf Len(gardenactionmsg$)<=5 Then
				gardenactionposx#=Len(gardenactionmsg$)*12.5*GAME_WINDOW_SCALE#
			ElseIf Len(gardenactionmsg$)<10 Then
				gardenactionposx#=Len(gardenactionmsg$)*6.25*GAME_WINDOW_SCALE#
			ElseIf Len(gardenactionmsg$)>15
				gardenactionposx#=-Len(gardenactionmsg$)*4.375*GAME_WINDOW_SCALE#
			Else
				gardenactionposx#=0
			EndIf
			Select i
				Case 1: DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#+gardenactionposx#, 27.5*GAME_WINDOW_SCALE#+gardenactionposy#)
				Case 2: DrawSmartKey(INPUT_BUTTON_ACTIONSKILL1, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#+gardenactionposx#, 27.5*GAME_WINDOW_SCALE#+gardenactionposy#)
				Case 3: DrawSmartKey(INPUT_BUTTON_CHANGE, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#+gardenactionposx#, 27.5*GAME_WINDOW_SCALE#+gardenactionposy#)
			End Select
			DrawRealText(gardenactionmsg$, GAME_WINDOW_W-140*GAME_WINDOW_SCALE#+gardenactionposx#, 27.5*GAME_WINDOW_SCALE#+gardenactionposy#, (Interface_TextButtons_1))
			gardenactionposy#=gardenactionposy#+30*GAME_WINDOW_SCALE#
		Else
			Game\Interface\GardenActionOverlapping[i]=0
		EndIf
	Next

	;race
	If Menu\Stage=998 and Game\Interface\RaceEnded=False Then
		ChaoManager_Race_GetChaoOrder()
		For i=1 to 8
			racechaox#=45*GAME_WINDOW_SCALE#
			If Game\Interface\RaceBegan=False Then
				racechaoy#=(40+(i-1)*35)*GAME_WINDOW_SCALE#
			Else
				racechaoy#=(285-(i-1)*35)*GAME_WINDOW_SCALE#
			EndIf

			If Game\Interface\RaceChaoOrder[i]=1 Then
				If Menu\ButtonState1=0 Then Menu\ButtonSize1#=Menu\ButtonSize1#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#<0 Then Menu\ButtonState1=1 : Menu\ButtonSize1#=0
				If Menu\ButtonState1=1 Then Menu\ButtonSize1#=Menu\ButtonSize1#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#>BUTTON_SCALELIMIT# Then Menu\ButtonState1=0 : Menu\ButtonSize1#=BUTTON_SCALELIMIT#
				Menu\ButtonSize#=Menu\ButtonSize1#
				SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
				DrawImageEx(INTERFACE(Interface_Race), racechaox#-32.5*GAME_WINDOW_SCALE#, racechaoy#, 0)
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			EndIf
			DrawImageEx(INTERFACE(Interface_Race), racechaox#, racechaoy#, Game\Interface\RaceChaoOrder[i])
		Next

		If Game\Interface\RaceTime>0 Then Game\Interface\RaceTime=Game\Interface\RaceTime-timervalue#
		SetColor(255,251,219)
		DrawRealText("TIME", GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-28*GAME_WINDOW_SCALE#, Interface_Text_3, 1)
		SetColor(255,255,255)
		DrawRealText(Int(Game\Interface\RaceTime/secs#), GAME_WINDOW_W/2, GAME_WINDOW_H-28*GAME_WINDOW_SCALE#, Interface_TextButtons_1, 1)
	EndIf

	;karate
	If Menu\Stage=997 and Game\Interface\RaceEnded=False Then
		DrawImageEx(INTERFACE(Interface_Karate), GAME_WINDOW_W/2, 40*GAME_WINDOW_SCALE#, 0)
		If Game\Interface\RaceTime>0 Then Game\Interface\RaceTime=Game\Interface\RaceTime-timervalue#
		SetColor(31,31,99)
		DrawRealText("TIME", GAME_WINDOW_W/2, 28*GAME_WINDOW_SCALE#, Interface_Text_2, 1)
		SetColor(255,255,255)
		DrawRealText(Int(Game\Interface\RaceTime/secs#), GAME_WINDOW_W/2, 47*GAME_WINDOW_SCALE#, Interface_TextButtons_1, 1)
		DrawKarateHealth(Game\Interface\KarateHealth#[1]*12.5, GAME_WINDOW_W/2-36*GAME_WINDOW_SCALE#+3, 31*GAME_WINDOW_SCALE#, True)
		DrawRealText(Menu\HeldChaoName, GAME_WINDOW_W/4*GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#-60, Interface_Text_2, 0,0,0,0,0,1)
		DrawKarateHealth(Game\Interface\KarateHealth#[2]*12.5, GAME_WINDOW_W/2+36*GAME_WINDOW_SCALE#-3, 31*GAME_WINDOW_SCALE#, False)

		DrawImageEx(INTERFACE(Interface_Karate), GAME_WINDOW_W/2, GAME_WINDOW_H-26*GAME_WINDOW_SCALE#, 1)
		DrawRealText("Zeal", GAME_WINDOW_W/2, GAME_WINDOW_H-18.5*GAME_WINDOW_SCALE#, Interface_Text_2, 1)
		DrawKarateZeal(Game\Interface\KarateZeal#[1]*3.8, GAME_WINDOW_W/2-29.25*GAME_WINDOW_SCALE#, GAME_WINDOW_H-19*GAME_WINDOW_SCALE#, true)
		DrawKarateZeal(Game\Interface\KarateZeal#[2]*3.8, GAME_WINDOW_W/2+29.25*GAME_WINDOW_SCALE#, GAME_WINDOW_H-19*GAME_WINDOW_SCALE#, false)
	EndIf

	;race results
	If Game\Interface\RaceEnded Then Interface_Render_Result(p,d,true) : p\MayNotCheerTimer=0.5*secs#

End Function