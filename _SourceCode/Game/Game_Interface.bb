
Type tGame_Interface
	Field	ShowFPS
	Field	NearCautionTimer
	Field	ShowChaoTimer
	Field	ShowChaoNumber
	Field	AutoSaveTimer
	Field	AutoSaveShowTimer
	Field	ShowCaution1Timer
	Field	ShowCaution2Timer
	Field	ShowCaution3Timer
	Field	HideInterface
	Field	FlashCheckTimerTimer
	Field	RingStolenTimer
	Field	CinemaAllowUpdate

	; point values
	Field	Points
	Field	PointsTimer
	Field	PointsCommentGiven
	Field	PointsComment$
	Field	PointsChain
	Field	point_fade#, t_x#, t_y#, point_r, point_g, point_b, thisheight#

	; Result stuff
	Field 	ResultTimer
	Field 	ResultOrder
	Field 	ResultOrder2
	Field	ResultTitlePosition#
	Field 	ResultBoxAlpha#[4]

	; chao inventory
	Field	ShowChaoItems
	Field	ChaoIconAlpha#
	Field	ChaoIconScale#
	Field	ChaoIconTimer
	Field	ChaoIconSpeed#
	Field	ChaoIconSpread#
	Field	ChaoItems[10]
	Field	ChaoItemCount
	Field	GardenActionTimer[3]
	Field	GardenActionMsg1$
	Field	GardenActionMsg2$
	Field	GardenActionMsg3$
	Field	GardenActionOverlapping[3]
	Field	ShallExplodeInventory
	Field	WinningChao[8]
	Field	YourWinningChao
	Field	RaceChaoOrder[8]
	Field	RaceChaoOrderChecker[8]
	Field	RaceChaoOrderCheckTimer
	Field	RaceEnded
	Field	RaceBegan
	Field	RaceCam
	Field	RaceEndPoint#
	Field	KarateTurn
	Field	KarateHealth#[2]
	Field	KarateZeal#[2]
	Field	KarateEndTimer
	Field	RaceTime

	; Debug Mode
	Field DebugPlacerOn
	Field DebugMenu
	Field DebugMenuOption
	Field DebugNewObj
	Field DebugMoveType
	Field DebugSpeed#
	Field DebugSpeed2#
	Field DebugCollision
	Field DebugAmountAxis
	Field DebugHadD
	Field DebugSpawnedObj
	Field DebugFileTime
	Field DebugSavedTimer
	Field DebugEnteredAttributeTimer
	Field DebugAxesMesh
	Field DebugWhichSwitch

	; Hint stuff
	Field ShowHintTimer
	Field HintLine1$
	Field HintLine2$

	; Control tips
	Field ControlTipType
	Field ControlTip$
	Field ControlTipPickUpTimer
	Field ControlTipTypePickUp
End Type


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Interface_TrickPointsCounter(p.tPlayer, d.tDeltaTime, movedown#=0)
	
			If Game\Interface\PointsTimer>5.0*secs# And Game\Interface\PointsTimer<6.0*secs# Then
				If Game\Interface\t_x#<0 Then Game\Interface\t_x#=Game\Interface\t_x#+5*d\Delta
				If Game\Interface\t_x#>0 Then Game\Interface\t_x#=0
			ElseIf Game\Interface\PointsTimer>1.0*secs# And Game\Interface\PointsTimer<2.0*secs# Then
				Game\Interface\t_x#=Game\Interface\t_x#+5*d\Delta
				Game\Interface\point_fade#=Game\Interface\point_fade#-0.075*d\Delta
			ElseIf Game\Interface\PointsTimer>6.0*secs# Then
				Game\Interface\t_x#=-88
				Game\Interface\point_fade#=1.0
			ElseIf Game\Interface\PointsTimer<1.0*secs# Then
				Game\Interface\point_fade#=0.0
			EndIf	
			
			If (Game\Interface\PointsTimer>1.0*secs# And Game\Interface\PointsTimer<=6.0*secs#) Then
				If Game\Interface\PointsTimer>4.0*secs# And Game\Interface\PointsTimer<6.0*secs# Then
					SetColor(220,220,220)
					DrawBetterNumber(Game\Interface\Points, (38+Game\Interface\t_x#)*GAME_WINDOW_SCALE#, (96+Game\Interface\thisheight#+Game\Interface\t_y#+movedown#)*GAME_WINDOW_SCALE#)
				Else
					If Game\Interface\PointsCommentGiven=0 Then
						Select Rand(1,7)
							Case 1:Game\Interface\PointsComment$="Cool!"	: Game\Interface\point_r=35	: Game\Interface\point_g=35	: Game\Interface\point_b=255
							Case 2:Game\Interface\PointsComment$="Groovy"	: Game\Interface\point_r=255	: Game\Interface\point_g=0	: Game\Interface\point_b=160
							Case 3:Game\Interface\PointsComment$="Radical!"	: Game\Interface\point_r=255	: Game\Interface\point_g=10	: Game\Interface\point_b=10
							Case 4:Game\Interface\PointsComment$="Amazing"	: Game\Interface\point_r=255	: Game\Interface\point_g=255	: Game\Interface\point_b=12
							Case 5:Game\Interface\PointsComment$="Tight!"	: Game\Interface\point_r=44	: Game\Interface\point_g=255	: Game\Interface\point_b=0
							Case 6:Game\Interface\PointsComment$="Sweet"	: Game\Interface\point_r=44	: Game\Interface\point_g=255	: Game\Interface\point_b=255
							Case 7:Game\Interface\PointsComment$="Nice"	: Game\Interface\point_r=255	: Game\Interface\point_g=255	: Game\Interface\point_b=255
						End Select
						Game\Interface\PointsCommentGiven=1
					EndIf
					If Game\Interface\PointsChain<=15 Then SetColor(Game\Interface\point_r,Game\Interface\point_g,Game\Interface\point_b)
					If Game\Interface\PointsChain>15 Then SetColor(Rand(155,255),Rand(155,255),Rand(155,255))
					SetAlpha(Game\Interface\point_fade#)
					If Game\Interface\PointsChain>=5 Then DrawRealText(Game\Interface\PointsComment$, (67.5+Game\Interface\t_x#)*GAME_WINDOW_SCALE#, (96+Game\Interface\thisheight#+Game\Interface\t_y#+movedown#)*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1)
					SetColor(255,255,255) : SetAlpha(1.0)
				EndIf
			Else
				If Game\Interface\PointsTimer<1.0*secs# Then Game\Interface\PointsChain=0
			EndIf	

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Interface_Render(p.tPlayer,d.tDeltatime)
		StartDraw()
		SetBlend(FI_ALPHABLEND)
		SetAlpha(1.0)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(255, 255, 255)

		If Game\Victory>0 and Menu\ChaoGarden=0 Then
			Interface_Render_Result(p,d)
		Else
			Interface_Render_Stage(p,d)
		EndIf

		EndDraw()
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Interface_Render_Stage(p.tPlayer,d.tDeltatime)

		If Menu\Settings\Debug#=1 and KeyDown(KEY_F4) Then
			Interface_Render_Cheats()
		Else
			If Game\CinemaMode=0 Then
				If Game\Interface\DebugPlacerOn=0 Then
					If Game\Interface\HideInterface=0 Then Interface_Render_Stage_Stage(p,d)
				Else
					If Input\Pressed\Change Then Game\Interface\HideInterface=abs(Game\Interface\HideInterface-1)
					If Game\Interface\HideInterface=0 Then Interface_Render_Stage_Debug(p)
				EndIf
			Else
				If Input\Pressed\Change Then Game\Interface\HideInterface=abs(Game\Interface\HideInterface-1)
				If Game\Interface\HideInterface=0 Then Interface_Render_Stage_Cinema(p)
			EndIf
		EndIf

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Interface_Render_Stage_Cinema(p.tPlayer)

		DrawRealText("CINEMA MODE", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)

		DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+60)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawRealText("Move around", (30+60+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, true)
		DrawRealText("Moving speed ("+cam\CinemaSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, 60)
		DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, 56)
		DrawRealText("Look around", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, 61)
		DrawImageEx(INTERFACE(Interface_Keys_small), (30)*GAME_WINDOW_SCALE#-6*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, 5)
		DrawImageEx(INTERFACE(Interface_Keys_small), (30)*GAME_WINDOW_SCALE#+3*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, 31)
		DrawRealText("Free", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawSmartKey(INPUT_BUTTON_CHANGE, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25)*GAME_WINDOW_SCALE#)
		DrawRealText("Hide interface", 30*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#)
		DrawRealText("Toggle update", 30*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Interface_Render_Exit(p.tPlayer,d.tDeltatime)
		StartDraw()
		SetBlend(FI_ALPHABLEND)
		SetAlpha(1.0)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(255, 255, 255)

		;titlecard
		Game\ControlLock=0.2*secs#
		If CARD_PLACE#>-150 Then CARD_PLACE#=CARD_PLACE#-5*d\Delta
		If CARD_PLACE#<-150 Then CARD_PLACE#=CARD_PLACE#+5*d\Delta
		SetColor(0,0,0)
			For x=-30 to 30
			DrawImageEx(INTERFACE(Interface_Card1), GAME_WINDOW_W/2+x*24*GAME_WINDOW_SCALE#, 0-(CARD_PLACE#+90)*GAME_WINDOW_SCALE#+30*(GAME_WINDOW_SCALE#-Float(1066)/640.0)*GAME_WINDOW_SCALE2#, 1)
			DrawImageEx(INTERFACE(Interface_Card1), GAME_WINDOW_W/2+x*24*GAME_WINDOW_SCALE#, GAME_WINDOW_H+(CARD_PLACE#+90)*GAME_WINDOW_SCALE#-30*(GAME_WINDOW_SCALE#-Float(1066)/640.0)*GAME_WINDOW_SCALE2#, 0)
			Next
		SetColor(255,255,255)
		If Abs(CARD_PLACE#-(-150))<ReturnFPSDifferenceFactor() Then
			CARD_PLACE#=-150 : DrawImageEx(INTERFACE(Interface_Black), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
			DrawImageEx(INTERFACE(Interface_Saving), 18*GAME_WINDOW_SCALE#, 18*GAME_WINDOW_SCALE#)
			DrawRealText("Loading...", 32*GAME_WINDOW_SCALE#, 22*GAME_WINDOW_SCALE#, (Interface_Text_2))
		EndIf

		EndDraw()

	End Function

;===================================================================================================================================================================================

	Function Interface_RingCounter(d.tDeltaTime)
		DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, 66*GAME_WINDOW_SCALE#, 16)
		If (Game\Gameplay\Rings=0 Or Game\Interface\RingStolenTimer>0) And Menu\Pause=0 Then
			If Game\Interface\RingStolenTimer>0 Then Game\Interface\RingStolenTimer=Game\Interface\RingStolenTimer-timervalue#
			flash=Sin#(MilliSecs() Mod 255) : SetColor(255,(255*flash)*d\Delta,(255*flash)*d\Delta)
		EndIf
		DrawBetterNumber(Game\Gameplay\Rings, 58*GAME_WINDOW_SCALE#, 66*GAME_WINDOW_SCALE#)
		SetColor(255,255,255)
	End Function

;===================================================================================================================================================================================

Function Interface_DrawHead(x#, y#, charno)
	If IsCharMod(charno+1) Then
		If Menu\Settings\Mods#=0 Then
			DrawImageEx(INTERFACE(Interface_Heads), x#, y#, InterfaceChar(CHAR_NONMODPLAYABLECOUNT+1))
		Else
			DrawImageEx(INTERFACE(Interface_HeadsMod[InterfaceChar(charno-CHAR_MOD1+2)]), x#, y#)
		EndIf
	Else
		DrawImageEx(INTERFACE(Interface_Heads), x#, y#, InterfaceChar(charno))
	EndIf
End Function

Function Interface_MemberHeads(x#=0, y#=0)
	If (x#=0 and y#=0) Then
		Select Menu\Members
			Case 3:
				SetColor(Interface_Lives_R[InterfaceChar(pp(3)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(3)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(3)\RealCharacter)])
				Interface_DrawHead((30-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(30)*GAME_WINDOW_SCALE#, pp(3)\RealCharacter-1)
				SetColor(Interface_Lives_R[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(2)\RealCharacter)])
				Interface_DrawHead((30+15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(30)*GAME_WINDOW_SCALE#, pp(2)\RealCharacter-1)
				Select pp(1)\RealCharacter
					Case CHAR_EME,CHAR_GME: SetColor(Interface_Lives_R[InterfaceChar(pp(1)\CharacterMode)],Interface_Lives_G[InterfaceChar(pp(1)\CharacterMode)],Interface_Lives_B[InterfaceChar(pp(1)\CharacterMode)])
					Default: SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
				End Select
				Interface_DrawHead((30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(30+7.5/2.0)*GAME_WINDOW_SCALE#, pp(1)\RealCharacter-1)
				SetColor(255,255,255)
			Case 2:
				SetColor(Interface_Lives_R[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(2)\RealCharacter)])
				Interface_DrawHead((30+7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(30)*GAME_WINDOW_SCALE#, pp(2)\RealCharacter-1)
				Select pp(1)\RealCharacter
					Case CHAR_EME,CHAR_GME: SetColor(Interface_Lives_R[InterfaceChar(pp(1)\CharacterMode)],Interface_Lives_G[InterfaceChar(pp(1)\CharacterMode)],Interface_Lives_B[InterfaceChar(pp(1)\CharacterMode)])
					Default: SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
				End Select
				Interface_DrawHead((30-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(30+7.5/2.0)*GAME_WINDOW_SCALE#, pp(1)\RealCharacter-1)
				SetColor(255,255,255)
			Case 1:
				Select pp(1)\RealCharacter
					Case CHAR_EME,CHAR_GME: SetColor(Interface_Lives_R[InterfaceChar(pp(1)\CharacterMode)],Interface_Lives_G[InterfaceChar(pp(1)\CharacterMode)],Interface_Lives_B[InterfaceChar(pp(1)\CharacterMode)])
					Default: SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
				End Select
				Interface_DrawHead((30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(30)*GAME_WINDOW_SCALE#, pp(1)\RealCharacter-1)
				SetColor(255,255,255)
		End Select
	Else
		Select pp(1)\RealCharacter
			Case CHAR_EME,CHAR_GME: SetColor(Interface_Lives_R[pp(1)\CharacterMode],Interface_Lives_G[pp(1)\CharacterMode],Interface_Lives_B[pp(1)\CharacterMode])
			Default: SetColor(Interface_Lives_R[pp(1)\RealCharacter],Interface_Lives_G[pp(1)\RealCharacter],Interface_Lives_B[pp(1)\RealCharacter])
		End Select
		Interface_DrawHead(x#, y#, pp(1)\RealCharacter-1)
		SetColor(255,255,255)
	EndIf
End Function

Function Interface_ProgressBar(inputx#, inputy#)
	DrawImageEx(INTERFACE(Interface_ProgressBar), inputx#, inputy#)

	Local distancepercent# = Game\Gameplay\Progress#/Game\Gameplay\InitialProgress#
	Local gap# = ImageWidthEx#(INTERFACE(Interface_Progress))

	SetCustomColor ARGB(1, 255, 255, 255), ARGB(1, 255, 255, 255), ARGB(1, 5, 5, 255), ARGB(1, 5, 5, 255)
	For i = 0 to 80
		If (i/80.0)<=(1.0-distancepercent#) Then DrawImageRectEx%(INTERFACE(Interface_Progress), inputx#-(gap#*40)*GAME_WINDOW_SCALE#+(gap#*0.925*i)*GAME_WINDOW_SCALE#, inputy#, gap#, ImageHeightEx#(INTERFACE(Interface_Progress)), i)
	Next
	SetColor(255,255,255)

	SetColor(Interface_Emerald_R[abs(Menu\Stage)],Interface_Emerald_G[abs(Menu\Stage)],Interface_Emerald_B[abs(Menu\Stage)])
	DrawImageEx(INTERFACE(Interface_Icons), inputx#+(gap#*40)*GAME_WINDOW_SCALE#, inputy#, 32)
	SetColor(255,255,255)
	Interface_MemberHeads(inputx#-(gap#*40)*GAME_WINDOW_SCALE#+(gap#*0.925*80*(1.0-distancepercent#))*GAME_WINDOW_SCALE#, inputy#)
End Function

;===================================================================================================================================================================================

	Function Interface_Render_Stage_Stage(p.tPlayer,d.tDeltatime)

		If Menu\ChaoGarden=0 Then;!!

		DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 15)
		DrawImageEx(INTERFACE(Interface_Numbers), 94*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 10)
		DrawImageEx(INTERFACE(Interface_Numbers), 144*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 10)
		DrawNumber((Game\Gameplay\Time/60000), 58*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 2)
		DrawNumber((Game\Gameplay\Time/1000) Mod 60, 108*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 2)
		DrawNumber((Game\Gameplay\Time/10) Mod 60, 158*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 2)

		Interface_RingCounter(d)

		Update_Monitor_Icons(d)

		If Game\Interface\FlashCheckTimerTimer>0 Then
			Game\Interface\FlashCheckTimerTimer=Game\Interface\FlashCheckTimerTimer-timervalue#
			If Game\Interface\FlashCheckTimerTimer<0.8*secs# Or (Game\Interface\FlashCheckTimerTimer>1*secs# and Game\Interface\FlashCheckTimerTimer<1.2*secs#) Or (Game\Interface\FlashCheckTimerTimer>1.4*secs# and Game\Interface\FlashCheckTimerTimer<1.6*secs#) Or (Game\Interface\FlashCheckTimerTimer>1.8*secs# and Game\Interface\FlashCheckTimerTimer<2*secs#) Then
				SetColor(220,220,220)
				DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2-14*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 10)
				DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+36*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 10)
				DrawNumber((Game\Gameplay\CheckTime/60000), GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 2)
				DrawNumber((Game\Gameplay\CheckTime/1000) Mod 60, GAME_WINDOW_W/2, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 2)
				DrawNumber((Game\Gameplay\CheckTime/10) Mod 60, GAME_WINDOW_W/2+50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 2)
				SetColor(255,255,255)
			EndIf
		EndIf

		Update_ChaoItem_Icons(d)

		DrawBetterNumber(Game\Gameplay\Score, GAME_WINDOW_W-35*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 0, 1)

		If p\Underwater=1 and (Not(Game\Shield=OBJTYPE_BSHIELD)) Then
			DrawImageEx(INTERFACE(Interface_Icons), 32.5*GAME_WINDOW_SCALE#, 102*GAME_WINDOW_SCALE#, 14)
			If p\DrownValue>=0 Then DrawBetterNumber(p\DrownValue, 58*GAME_WINDOW_SCALE#, 102*GAME_WINDOW_SCALE#)
			Interface_TrickPointsCounter(p, d, 36)
		Else
			Interface_TrickPointsCounter(p, d)
		EndIf

		Interface_MemberHeads()
		SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
		If Menu\Members>1 Then
			DrawBetterNumber(Game\Gameplay\Lives, 67.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-30*GAME_WINDOW_SCALE#)
		Else
			DrawBetterNumber(Game\Gameplay\Lives, 58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-30*GAME_WINDOW_SCALE#)
		EndIf
		SetColor(255,255,255)

		If Game\Interface\ChaoItemCount>0 Then movemissioncounterup#=25.0*GAME_WINDOW_SCALE#*Game\Interface\ChaoIconSpread# Else movemissioncounterup#=0
		If Menu\MissionTime=1 Then
			movemissiontimecounterup#=movemissioncounterup#
			Select Menu\Mission
				Case MISSION_ENEMY#,MISSION_RING#,MISSION_HUNT#,MISSION_GOLD#,MISSION_BALLOONS#,MISSION_BOSS#,MISSION_RIVAL#,MISSION_FLICKY#,MISSION_DECLINE#:
					movemissioncounterup#=movemissioncounterup#+32.5*GAME_WINDOW_SCALE#
			End Select
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 15)
			If (Game\LimitTime-Game\Gameplay\Time)<5*secs# Then flash=Sin#(MilliSecs() Mod 255) : SetColor(255,(255*flash)*d\Delta,(255*flash)*d\Delta)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-94*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 10)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-144*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 10)
			If Game\Gameplay\Time<Game\LimitTime Then
				DrawNumber(((Game\LimitTime-Game\Gameplay\Time)/60000), GAME_WINDOW_W-180*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 2)
				DrawNumber(((Game\LimitTime-Game\Gameplay\Time)/1000) Mod 60, GAME_WINDOW_W-130*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 2)
				DrawNumber(((Game\LimitTime-Game\Gameplay\Time)/10) Mod 60, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 2)
			Else
				DrawNumber(0, GAME_WINDOW_W-180*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 2)
				DrawNumber(0, GAME_WINDOW_W-130*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 2)
				DrawNumber(0, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissiontimecounterup#-30*GAME_WINDOW_SCALE#, 2)
			EndIf
			SetColor(255,255,255)
		EndIf
		Select Menu\Mission
			Case MISSION_ENEMY#:
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 17)
				DrawBetterNumber(Game\Gameplay\TotalEnemies-Game\Gameplay\Enemies, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			Case MISSION_RING#:
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 16)
				If Game\Gameplay\Rings<200 Then
					DrawBetterNumber(200-Game\Gameplay\Rings, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
				Else
					DrawBetterNumber(0, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
				EndIf
			Case MISSION_HUNT#:
				For i=1 to 3
					If Game\Gameplay\RedRingDistance[i]<9 Then
						If Game\Gameplay\RedRingTimer[i]>0 Then
							If Game\Gameplay\RedRingBeepTimer[i]>0 Then
								DrawImageEx(INTERFACE(Interface_Treasure_Big), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(i-1)*37.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, Game\Gameplay\RedRingDistance[i])
							Else
								DrawImageEx(INTERFACE(Interface_Treasure), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(i-1)*37.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, Game\Gameplay\RedRingDistance[i])
							EndIf
						Else
							DrawImageEx(INTERFACE(Interface_Treasure), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(i-1)*37.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0)
						EndIf
					EndIf
					If Game\Gameplay\RedRingTimer[i]>0 Then Game\Gameplay\RedRingTimer[i]=Game\Gameplay\RedRingTimer[i]-timervalue#
					If Game\Gameplay\RedRingBeepTimer[i]>0 Then Game\Gameplay\RedRingBeepTimer[i]=Game\Gameplay\RedRingBeepTimer[i]-timervalue#
				Next
			Case MISSION_GOLD#:
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 9)
				DrawBetterNumber(Game\Gameplay\TotalGoldEnemies-Game\Gameplay\GoldEnemies, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			Case MISSION_BALLOONS#:
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 7)
				DrawBetterNumber(Game\Gameplay\TotalBalloons-Game\Gameplay\Balloons, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			Case MISSION_BOSS#:
				For o.tObject = Each tObject
				If o\ThisIsAnEnemy Then
				If o\Enemy\IsBoss=1 Then
					If o\Enemy\Health<2 Then 
						SetCustomColor ARGB(1, 255, 0, 0), ARGB(1, 255, 0, 0), ARGB(1, 255, 78, 0), ARGB(1, 255, 78, 0)
					ElseIf o\Enemy\Health<3 Then
						SetCustomColor ARGB(1, 255, 255, 0), ARGB(1, 255, 255, 0), ARGB(1, 155, 178, 0), ARGB(1, 155, 178, 0)
					Else
						SetCustomColor ARGB(1, 0, 236, 233), ARGB(1, 0, 236, 233), ARGB(1, 0, 78, 77), ARGB(1, 0, 78, 77)
					EndIf
					DrawRect(GAME_WINDOW_W-(90*1.8)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*1.25)*GAME_WINDOW_SCALE#, (o\Enemy\Health*10)*2.13/1.5, 43/3.0, 1)
					SetColor(255,255,255)
					DrawRealText("Enemy:", GAME_WINDOW_W-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
					DrawImageEx(INTERFACE(Interface_Boss), GAME_WINDOW_W-90*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#)
				EndIf
				EndIf
				Next
			Case MISSION_RIVAL#:
				For rvl=1 To Game\RivalAmount
					SetColor(Interface_Lives_R[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_G[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_B[InterfaceChar(ppe(rvl)\RealCharacter)])
					Interface_DrawHead(GAME_WINDOW_W-(30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*rvl)*GAME_WINDOW_SCALE#, ppe(rvl)\RealCharacter-1)
					For i=1 to 5
						SetColor(15,15,15) : SetAlpha(0.375)
						SetScale(GAME_WINDOW_SCALE#/1.25,GAME_WINDOW_SCALE#/1.25)
						DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-(45+i*11.25)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*rvl)*GAME_WINDOW_SCALE#, 28)
						SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
					Next
					For i=1 to ppe(rvl)\Rival\Health
						SetColor(Interface_Lives_R[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_G[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_B[InterfaceChar(ppe(rvl)\RealCharacter)]) : SetAlpha(1.0)
						SetScale(GAME_WINDOW_SCALE#/1.5,GAME_WINDOW_SCALE#/1.5)
						DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-(45+i*11.25)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*rvl)*GAME_WINDOW_SCALE#, 28)
						SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
					Next
					SetAlpha(1.0)
					SetColor(255,255,255)
				Next
			Case MISSION_FLICKY#:
				For i=1 to 5
					j=5-i+1
					If i<=Game\Gameplay\Flickies Then
						DrawImageEx(INTERFACE(Interface_Flickies), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(j-1)*35*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-35*GAME_WINDOW_SCALE#, 1)
					Else
						DrawImageEx(INTERFACE(Interface_Flickies), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(j-1)*35*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-35*GAME_WINDOW_SCALE#, 0)
					EndIf
				Next
			Case MISSION_DECLINE#:
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 33)
				If Game\DeclineTime<5*secs# Then flash=Sin#(MilliSecs() Mod 255) : SetColor(255,(255*flash)*d\Delta,(255*flash)*d\Delta)
				DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-94*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 10)
				DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-144*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 10)
				If Game\DeclineTime>0 Then
					DrawNumber((Game\DeclineTime/60000), GAME_WINDOW_W-180*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 2)
					DrawNumber((Game\DeclineTime/1000) Mod 60, GAME_WINDOW_W-130*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 2)
					DrawNumber((Game\DeclineTime/10) Mod 60, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 2)
				Else
					DrawNumber(0, GAME_WINDOW_W-180*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 2)
					DrawNumber(0, GAME_WINDOW_W-130*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 2)
					DrawNumber(0, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 2)
				EndIf
				SetColor(255,255,255)
		End Select

		If (Not(Game\StartoutLock>0 Or Game\ControlLock>0)) Then
			If Menu\Settings\ControlTips#=1 Then
				Interface_ControlTipDraw(p, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, 150*GAME_WINDOW_SCALE#)
			Else
				If p\AirBegTooFar Then DrawImageEx(INTERFACE(Interface_Icons), 20*GAME_WINDOW_SCALE#, GAME_WINDOW_H-61*GAME_WINDOW_SCALE#, 1)
			EndIf
		EndIf

		Else;!!

		Interface_Render_Stage_Chao(p,d)

		EndIf;!!

		If Menu\Settings\Debug#=1 And Game\Interface\ShowFPS=1 Then
			DrawNumber(Game\Others\FPS, GAME_WINDOW_W/2+100*GAME_WINDOW_SCALE#, 15*GAME_WINDOW_SCALE#, 0, 1)
			DrawNumber(Game\Others\CurrentCameraRange, GAME_WINDOW_W/2+200*GAME_WINDOW_SCALE#, 15*GAME_WINDOW_SCALE#, 0, 1)
			DrawNumber(OBJECT_VIEWDISTANCE#, GAME_WINDOW_W/2+290*GAME_WINDOW_SCALE#, 15*GAME_WINDOW_SCALE#, 0, 1)
			DrawNumber(Game\IdealScore, GAME_WINDOW_W/2+100*GAME_WINDOW_SCALE#, 35*GAME_WINDOW_SCALE#, 0, 1)

			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+94*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 10)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+144*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 10)
			DrawNumber((Game\IdealTime/60000), GAME_WINDOW_W/2+58*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 2)
			DrawNumber((Game\IdealTime/1000) Mod 60, GAME_WINDOW_W/2+108*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 2)
			DrawNumber((Game\IdealTime/10) Mod 60, GAME_WINDOW_W/2+158*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 2)

			DrawBetterNumber(p\Physics\COMMON_XZACCELERATION#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#, 11)
			DrawBetterNumber(p\SpeedLength#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-110*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-110*GAME_WINDOW_SCALE#, 11)

			DrawBetterNumber(p\Physics\COMMON_XZTOPSPEED#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, 11)
			DrawBetterNumber(p\Physics\JUMP_STRENGTH#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, 11)

			If Game\Cheater=1 Then
				DrawRealText("CHEATED", GAME_WINDOW_W/2, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1)
			EndIf
		EndIf

		If Menu\ChaoGarden=0 Then
		If (Not(p\Action=ACTION_TORNADO)) Then
			i = 0
			Select p\Character
				Case CHAR_CRE:
					If p\CheeseRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\CheeseRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_TAI,CHAR_CHA,CHAR_TDL,CHAR_ROU,CHAR_EGR,CHAR_BEA, CHAR_TRI:
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_WAV:
					If p\BoomerangAway=1 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(1/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_ESP,CHAR_SHD,CHAR_AMY,CHAR_CHO:
					If p\Invisibility=1 Then
						Game\Interface\ShowCaution1Timer=0.01*secs#
						DrawBetterNumber(p\InvisibilityTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\InvisibilityRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\InvisibilityRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_SIL,CHAR_INF,CHAR_SHA:
					If p\PsychoChargeTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\PsychoChargeTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_LEVITATE Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\LevitationTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_MAR:
					If p\BoomerangAway=1 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(1/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLUTTER Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_BIG:
					If p\CheeseRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\CheeseRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_RAY:
					If p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\SoarTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_NAC:
					If p\ThrowTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\ThrowTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_OME,CHAR_VEC,CHAR_TIA, CHAR_TRI:
					If p\ShootCooldownTimer>0 Then
						Game\Interface\ShowCaution1Timer=0.01*secs#
						DrawBetterNumber(p\ShootCooldownTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_BAR:
					If p\Action=ACTION_SLEET Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_MPH:
					If p\Action=ACTION_LEVITATE Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\LevitationTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_TIK:
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_SPIRIT Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					Else
						If p\ShootCooldownTimer>0 Then
							Game\Interface\ShowCaution1Timer=0.01*secs#
							DrawBetterNumber(p\ShootCooldownTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						EndIf
					EndIf
				Case CHAR_HON:
					If p\Action=ACTION_FLUTTER Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_EME:
					If p\InvisibilityRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\InvisibilityRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_GME:
					If p\InvisibilityRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\InvisibilityRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\SoarTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_MT3:
					If p\Action=ACTION_LEVITATE Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\LevitationTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
			End Select

			i = 0
			If Game\Interface\ShowCaution1Timer>0 Then
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-35*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 11)
				i=i+1
			EndIf
			If Game\Interface\ShowCaution3Timer>0 Then
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-35*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 13)
				i=i+1
			EndIf
			If Game\Interface\ShowCaution2Timer>0 Then ;has to be below
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, (66+36*i)*GAME_WINDOW_SCALE#, 12)
				i=i+1
			EndIf
		EndIf
		EndIf

		; Hint stuff
		If Game\Interface\ShowHintTimer>0 Then
			Game\Interface\ShowHintTimer=Game\Interface\ShowHintTimer-timervalue#
			SetColor(235,235,235)
			DrawRealText(Game\Interface\HintLine1$, GAME_WINDOW_W/2.0, GAME_WINDOW_H-40*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText(Game\Interface\HintLine2$, GAME_WINDOW_W/2.0, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			SetColor(255,255,255)
		EndIf

		; Draw title card
		If Menu\TitleCardTimer<1*secs# Then
			Menu\TitleCardTimer=Menu\TitleCardTimer+timervalue#
			DrawTitleCardStuff()
		EndIf

		; Special stage stuff
		If Menu\Stage<0 Then
			; Display message
			If pp(1)\Objects\Position\z#<-10*40 Then
				If pp(1)\Objects\Position\z#<-60*40 Then
					SetAlpha(0.0)
				ElseIf pp(1)\Objects\Position\z#<-40*40 Then
					SetAlpha((p\Objects\Position\z#+60*40)/(20.0*40))
				ElseIf pp(1)\Objects\Position\z#<-20*40 Then
					SetAlpha(1.0)
				ElseIf pp(1)\Objects\Position\z#>=-20*40 Then
					SetAlpha((pp(1)\Objects\Position\z#+10*40)/(-10.0*40))
				Else
					SetAlpha(0.0)
				EndIf
				DrawRealText("GET "+Int(Game\Stage\Properties\SpecialStageRingGateRequirement)+" RINGS!", GAME_WINDOW_W/2.0, GAME_WINDOW_H/2.0-80*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, Game\Stage\Properties\SpecialStageSkydomeR,Game\Stage\Properties\SpecialStageSkydomeG,Game\Stage\Properties\SpecialStageSkydomeB)
				SetAlpha(1.0)
			EndIf

			; Show special stage progress
			For o.tObject=Each tObject
				If o\ObjType=OBJTYPE_GOAL Then
					If Game\Gameplay\InitialProgress=0 Then Game\Gameplay\InitialProgress=EntityDistance(p\Objects\Entity, o\Entity)
					Game\Gameplay\Progress=EntityDistance(p\Objects\Entity, o\Entity)
				EndIf
			Next
			Interface_ProgressBar(GAME_WINDOW_W/2.0, GAME_WINDOW_H-40*GAME_WINDOW_SCALE)

			; Rings needed
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 16)
			SetColor(255,255,0)
			If Game\Gameplay\Rings<Game\Stage\Properties\SpecialStageRingGateRequirement Then
				DrawBetterNumber(Game\Stage\Properties\SpecialStageRingGateRequirement-Game\Gameplay\Rings, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			Else
				DrawBetterNumber(0, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			EndIf
			SetColor(255,255,255)
		EndIf

	End Function

;===================================================================================================================================================================================

	Function Interface_Render_Cheats(ismenu=0)

		If ismenu>0 Then
			DrawImageEx(INTERFACE(Interface_Black), GAME_WINDOW_W/2, GAME_WINDOW_H/2)

			DrawRealText("Debug nodes are", GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+3.5*30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			;-----
			Select Menu\Settings\DebugNodes#
			Case 1: DrawRealText("currently: ON", GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+4.5*30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			Case 0: DrawRealText("currently: OFF", GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+4.5*30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
		EndIf

		For i=1 to 18
			Select i
				Case 11,12,13,14,15,16,17,18:
					DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, 61)
				Default:
					DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, 61)
			End Select

			Select i
				Case 1: button=47
				Case 2: button=31
				Case 3: button=32
				Case 4: button=33
				Case 5: button=34
				Case 6: button=35
				Case 7: button=36
				Case 8: button=37
				Case 9: button=38
				Case 10: button=39
				Case 11: button=31 : button2=30
				Case 12: button=31 : button2=31
				Case 13: button=31 : button2=32
				Case 14: button=48
				Case 15: button=58
				Case 16: button=59
				Case 17: button=29
				Case 18: button=26
			End Select
			Select i
				Case 1:
					DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, button)
				Case 11,12,13:
					DrawImageEx(INTERFACE(Interface_Keys_small), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#-6*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, 5)
					DrawImageEx(INTERFACE(Interface_Keys_small), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#+3*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, button)
					DrawImageEx(INTERFACE(Interface_Keys_small), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#+9*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, button2)
				Case 14,15,16,17,18:
					DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, button)
				Default:
					DrawImageEx(INTERFACE(Interface_Keys_small), GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#-6*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, 5)
					DrawImageEx(INTERFACE(Interface_Keys_small), GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#+3*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, button)
			End Select

			Select ismenu
				Case 0:
					Select i
						Case 1: cheat$="Quit program"
						Case 2: cheat$="Spawn at origin"
						Case 3: cheat$="Die"
						Case 4: cheat$="Object placer"
						Case 5: cheat$="Show cheats"
						Case 6: cheat$="Show FPS & info"
						Case 7: cheat$="Become invincible"
						Case 8: cheat$="Get speed shoes"
						Case 9: If Menu\ChaoGarden=0 Then cheat$="Gain 50 rings" Else cheat$="Next day time (dev)"
						Case 10: If Menu\ChaoGarden=0 Then cheat$="Finish stage" Else cheat$="Random chao (dev)"
						Case 11: cheat$="Reset all objects"
						Case 12: cheat$="Toggle cinema mode"
						Case 13: cheat$="Change char row"
						Case 14: cheat$="Moon jump"
						Case 15: cheat$="Object placer"
						Case 16: cheat$="Change char"
						Case 17: cheat$="Next char"
						Case 18: If Menu\ChaoGarden=0 Then cheat$="Hurt" Else cheat$="Spawn random seed"
					End Select
				Case 1:
					Select i
						Case 1: cheat$="Quit program"
						Case 2: cheat$="Unlock all"
						Case 3: cheat$="Next chao emo"
						Case 4: cheat$="Toggle debug nodes"
						Case 5: cheat$="Show cheats"
						Case 6: cheat$="Go to welcome"
						Case 7: cheat$="Go to start screen"
						Case 8: cheat$="Go to game over"
						Case 9: cheat$="Go to time over"
						Case 10: cheat$="Change mission"
						Case 11: cheat$="Toggle time control"
						Case 12: cheat$="Random team"
						Case 13: cheat$="Change char row"
						Case 14: cheat$="..."
						Case 15: cheat$="& Anyone"
						Case 16: cheat$="Change char or team"
						Case 17: cheat$="Skip to stage select (3)"
						Case 18: cheat$="Skip to stage select (1)"
					End Select
			End Select
			Select i
				Case 11,12,13,14,15,16,17,18:
					DrawRealText(cheat$, GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
				Default:
					DrawRealText(cheat$, GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
			End Select
		Next

	End Function

;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================
;===================================================================================================================================================================================

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function DrawNumber(Number%, x, y, ZeroPadding=0, Alignment=0)
		; Convert number to string
		Num$ = ZeroPadding$(Str$(Number%), ZeroPadding)

		If (Alignment=1) Then x = x-Len(Num$)*18*GAME_WINDOW_SCALE#
		
		; Go on and render text
		For i = 1 To Len(Num$)
			DrawImageEx INTERFACE(Interface_Numbers), x, y, Asc(Mid$(Num$, i, 1))-48
			x = x+23*GAME_WINDOW_SCALE#
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function DrawBetterNumber(Number%, x, y, ZeroPadding=0, Alignment=0)
		If Number%>=100000000 Then
			DrawNumber(Number%, x+2.00*GAME_WINDOW_SCALE#-22.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=10000000 Then
			DrawNumber(Number%, x+1.75*GAME_WINDOW_SCALE#-17.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=1000000 Then
			DrawNumber(Number%, x+1.50*GAME_WINDOW_SCALE#-12.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=100000 Then
			DrawNumber(Number%, x+1.25*GAME_WINDOW_SCALE#-07.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=10000 Then
			DrawNumber(Number%, x+1.00*GAME_WINDOW_SCALE#-02.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=1000 Then
			DrawNumber(Number%, x+0.75*GAME_WINDOW_SCALE#+02.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=100 Then
			DrawNumber(Number%, x+0.50*GAME_WINDOW_SCALE#+07.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=10 Then
			DrawNumber(Number%, x+0.25*GAME_WINDOW_SCALE#+12.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		ElseIf Number%>=0 Then
			DrawNumber(Number%, x+0.00*GAME_WINDOW_SCALE#+17.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
		EndIf
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function DrawRealText(text$,inputx#,inputy#,font,alignment=0,end#=0,r=255,g=255,b=255,smaller#=0)
		textLength = Len(text$)

		letterSpacing#=1

		Select font
			Case (Interface_Text_1),(Interface_Text2_1):						sizeMultiplier#=3.0/(14/2)
			Case (Interface_Text_2),(Interface_Text2_2):						sizeMultiplier#=3.4/(14/2)
			Case (Interface_Text_3),(Interface_Text2_3):						sizeMultiplier#=4.0/(14/2)
			Case (Interface_TextButtons_1),(Interface_TextButtons2_1):				sizeMultiplier#=6.0/(14/2)
			Case (Interface_TextButtons_2),(Interface_TextButtons2_2):				sizeMultiplier#=4.0/(14/2)
			Case (Interface_TextTitle_1),(Interface_TextTitle2_1),(Interface_TextTitleChao_1):	sizeMultiplier#=9.8/(14/2)
			Case (Interface_TextControls_1):							sizeMultiplier#=7.4/(14/2)
			Case (Interface_TextControls_2):							sizeMultiplier#=8.1/(14/2)
			Case (Interface_TextRecords_1),(Interface_TextRecords_2):				sizeMultiplier#=8.1/(14/2)
			Case (Interface_TextNames_1),(Interface_TextNames2_1):					sizeMultiplier#=9.4/(14/2)
			Case (Interface_TextOvers_1),(Interface_TextOvers_2):					sizeMultiplier#=10.6/(14/2)
			Case (Interface_TextCredits_1):								sizeMultiplier#=3.2/(14/2)
		End Select

		If smaller#<0 Then smaller#=0
		If smaller#>0 Then sizeMultiplier#=(sizeMultiplier#/smaller#)

		SetScale(GAME_WINDOW_SCALE#-(smaller#/2.65),GAME_WINDOW_SCALE#-(smaller#/2.65))

		Select font
			Case (Interface_Text_1),(Interface_Text2_1):						draworderlimit=2
			Case (Interface_Text_2),(Interface_Text2_2):						draworderlimit=2
			Case (Interface_Text_3),(Interface_Text2_3):						draworderlimit=2
			Case (Interface_TextTitle_1),(Interface_TextTitle2_1),(Interface_TextTitleChao_1):	draworderlimit=2
			Case (Interface_TextNames_1),(Interface_TextNames2_1):					draworderlimit=2
			Default:										draworderlimit=1
		End Select

		TotalWidth#=0

		For draworder=0 to draworderlimit
			x#=inputx# : y#=inputy#
			Select alignment
			Case 1: x#=x#-(TotalWidth#/2)*GAME_WINDOW_SCALE#
			Case 2: x#=x#-(TotalWidth#)*GAME_WINDOW_SCALE#
			End Select

			WidthSoFar#=0
			HeightSoFar#=0
			currentPosition#=-DrawRealText_ReturnLetterWidth(Asc(Mid$(text$,1,1)))/2

			For letterNumber = 1 To textLength
				letter$ = Mid$(text$,letterNumber,1)

				character = Asc(letter$)
				letterWidth#=DrawRealText_ReturnLetterWidth(character)
				character = character - 32
				If character=3 Then character=2

				Select draworder
					Case 0:
						If alignment>0 Then
							currentPosition#=TotalWidth#+sizeMultiplier#*letterSpacing#+sizeMultiplier#*letterWidth#/2
							TotalWidth#=currentPosition#+sizeMultiplier#*letterWidth#/2
						EndIf
					Default:
						currentPosition#=widthSoFar#+sizeMultiplier#*letterSpacing#+sizeMultiplier#*letterWidth#/2
						DrawRealText_DrawText draworder, font, x#+currentPosition#*GAME_WINDOW_SCALE#, y#+HeightSoFar#*GAME_WINDOW_SCALE#, character, r, g, b
						WidthSoFar#=currentPosition#+sizeMultiplier#*letterWidth#/2
						If center=false and end#<>0 and letter$=" " and (x#+WidthSoFar#*GAME_WINDOW_SCALE#-0>end-0 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-1>end-1 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-2>end-2 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-3>end-3 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-4>end-4 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-5>end-5) Then
							HeightSoFar#=HeightSoFar#+sizeMultiplier#*(14/2)*5
							WidthSoFar=0
						EndIf
				End Select
			Next
		Next

		SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)

	End Function

	;____________________________________________________________________________________________________________________________________________

	Function DrawRealText_DrawText(draworder, font, x#, y#, character, r, g, b)
		Select font
			Case (Interface_Text_1):
				Select draworder
					Case 1: DrawImageEx INTERFACE(Interface_Text2_1), x#, y#, character
					Case 2: DrawImageEx INTERFACE(Interface_Text_1), x#, y#, character
				End Select
			Case (Interface_Text_2):
				Select draworder
					Case 1: DrawImageEx INTERFACE(Interface_Text2_2), x#, y#, character
					Case 2: DrawImageEx INTERFACE(Interface_Text_2), x#, y#, character
				End Select
			Case (Interface_Text_3):
				Select draworder
					Case 1: DrawImageEx INTERFACE(Interface_Text2_3), x#, y#, character
					Case 2: DrawImageEx INTERFACE(Interface_Text_3), x#, y#, character
				End Select
			Case (Interface_TextTitle_1):
				Select draworder
					Case 1: SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextTitle2_1), x#, y#, character : SetColor(255,255,255)
					Case 2: DrawImageEx INTERFACE(Interface_TextTitle_1), x#, y#, character
				End Select
			Case (Interface_TextTitleChao_1):
				Select draworder
					Case 1: SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextTitle2_1), x#, y#, character : SetColor(255,255,255)
					Case 2: DrawImageEx INTERFACE(Interface_TextTitleChao_1), x#, y#, character
				End Select
			Case (Interface_TextNames_1):
				Select draworder
					Case 1:
						DrawImageEx INTERFACE(Interface_TextNames2_1), x#, y#, character
					Case 2:
						If character=6 Then
							SetColor(63,63,63) : DrawImageEx INTERFACE(Interface_TextNames_1), x#, y#, character : SetColor(255,255,255)
						Else
							SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextNames_1), x#, y#, character : SetColor(255,255,255)
						EndIf
				End Select
			Case (Interface_TextCredits_1):
				Select draworder
					Case 1: SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextCredits_1), x#, y#, character : SetColor(255,255,255)
				End Select
			Default:
				DrawImageEx INTERFACE(font), x#, y#, character
		End Select
	End Function

	;____________________________________________________________________________________________________________________________________________

	Function DrawRealText_ReturnLetterWidth(character)
		Select character
			Case Asc("!"),Asc("I"),Asc("i"),Asc("l"):				letterWidth#=6
			Case Asc("'"),Asc(","),Asc("1"):					letterWidth#=7
			Case Asc("-"),Asc("."),Asc(":"),Asc(";"):				letterWidth#=8
			Case Asc("("),Asc(")"),Asc("f"),Asc("j"),Asc("t"):			letterWidth#=9
			Case Asc(" "), 3+32:							letterWidth#=11
			Case Asc("/"),Asc("L"),Asc("r"):					letterWidth#=12
			Case Asc("Z"),Asc("c"),Asc("z"):					letterWidth#=13
			Case Asc("&"),Asc("C"),Asc("K"),Asc("O"),Asc("U"),Asc("V"),Asc("x"):	letterWidth#=15
			Case Asc("M"):								letterWidth#=16
			Case Asc("Q"):								letterWidth#=17
			Case Asc("m"),Asc("%"):							letterWidth#=20
			Case Asc("w"):								letterWidth#=22
			Case Asc("W"):								letterWidth#=24
			Default:								letterWidth#=14
		End Select
		Return letterWidth#
	End Function


;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function DrawTitleCardStuff(inmenu=false)

	SetColor(Menu_ReturnCardColor(1,Menu\Character[1]),Menu_ReturnCardColor(2,Menu\Character[1]),Menu_ReturnCardColor(3,Menu\Character[1]))

	SetAlpha(abs(1-Menu\TitleCardTimer/secs#)*0.5)
	Select inmenu
		Case false: SetScale(GAME_WINDOW_SCALE#*Min#(1.85,abs((Menu\TitleCardTimer/secs#))*2+0.4), GAME_WINDOW_SCALE#*Min#(1.85,abs((Menu\TitleCardTimer/secs#))*2+0.4))
		Case true: SetScale(GAME_WINDOW_SCALE#*Min#(2.0,abs((Menu\TitleCardTimer/secs#))*2+0.4), GAME_WINDOW_SCALE#*Min#(2.0,abs((Menu\TitleCardTimer/secs#))*2+0.4))
	End Select
	DrawImageEx(INTERFACE(Interface_Spinner), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetAlpha(1.0)

	SetAlpha(abs(1-Menu\TitleCardTimer/secs#))
	SetScale(GAME_WINDOW_SCALE#*Min#(1.0,abs(1-(Menu\TitleCardTimer/secs#))+0.4), GAME_WINDOW_SCALE#*Min#(1.0,abs(1-(Menu\TitleCardTimer/secs#))+0.4))
	DrawImageEx(INTERFACE(Interface_Spinner), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetAlpha(1.0)

	Select Menu\ChaoGarden
		Case 0:
			SetColor(Menu_ReturnCardColor(1,Menu\Character[1]),Menu_ReturnCardColor(2,Menu\Character[1]),Menu_ReturnCardColor(3,Menu\Character[1]))
			For x=-15 to 15 : DrawImageEx(INTERFACE(Interface_Card2), (80-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+x*24*GAME_WINDOW_SCALE#) : Next
		Case 1:
			SetColor(35,241,253)
			For x=-15 to 15 : DrawImageEx(INTERFACE(Interface_Card3), (80-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+x*24*GAME_WINDOW_SCALE#) : Next
	End Select
	SetColor(255,255,255)

	Select inmenu
		Case false: Menu_UpdateStageNames(Menu\Stage)
		Case true: Menu_UpdateStageNames(Menu\SelectedStage)
	End Select

	Select Menu\ChaoGarden
		Case 0:
			If Menu\MarathonMode=1 and Menu\MarathonGotSpecial=0 Then
				DrawRealText("Stage "+Menu\MarathonStage, (80-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#-63*GAME_WINDOW_SCALE#, 20*GAME_WINDOW_SCALE#, (Interface_Text_3))
			EndIf
			DrawRealText(Menu\StageName$, GAME_WINDOW_W/2, GAME_WINDOW_H/2-(50+200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
		Case 1:
			DrawRealText(Menu\StageName$, GAME_WINDOW_W/2, GAME_WINDOW_H/2-(50+200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_TextTitleChao_1), 1, 0, 63, 63, 63)
	End Select

	Menu_UpdateMissionInfo()

	DrawRealText(Menu\MissionName$, GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(200*Menu\TitleCardTimer/secs#-5)*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawRealText(Menu\MissionInfo$, GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(200*Menu\TitleCardTimer/secs#+15)*GAME_WINDOW_SCALE#, (Interface_Text_2))
	DrawRealText(Menu\MissionInfo2$, GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(200*Menu\TitleCardTimer/secs#+35)*GAME_WINDOW_SCALE#, (Interface_Text_2))

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function ARGB(Alpha#, Red, Green, Blue)
	Return (Int(Alpha*255) Shl 24) Or (Red Shl 16)  Or (Green Shl 8)  Or Blue	
End Function