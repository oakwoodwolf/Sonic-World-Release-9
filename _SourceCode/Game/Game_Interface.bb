Global ITEMS_LOADED
Function Menu_GoToMissionCard()
	
	PlaySmartSound(Sound_ATM)
	Menu\MissionNo=0
	Menu\MissionCard=1
	Menu\StartedStageWarp=1
	Menu_Stage_LoadMissions(0,True,1)
	
	Game_Stage_Quit(2)
	
End Function
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
	Field	ShowRedRingTimer
	Field FavouriteCommandTimer
	Field DebugInfo
	Field DebugInfoText$[64]
	Field AbilityIconOrder
	Field AbilityIcon[5]
	Field HandicapIconOrder
	Field HandicapIcon[5]
	Field DebugNumber
	Field DebugText$
	
	Field LifeTimer
	Field LifeAlpha#
	Field LifeScale#
	
	Field PromptSound
	Field PromptType
	Field PromptTimer
	
	
	Field AchievementTimer
	Field Achievement$
	Field AchievementAlpha#
	Field AchievementAlpha2#
	Field AchievementAlpha3#
	Field ConsoleTimer
	Field AchievementState
	Field AchievementIcon
	Field AchievementAmount
	Field AchievementSounded
	Field AchievementSound
	
	Field Shop
	Field SoundTest
	Field SoundTestOption
	
	Field ShopMenu
	Field ShopMenuOption
	Field ShopMenuOption2
	Field ShopMenuToBuy
	
	
	
	Field DebugConsole
	Field DebugConsoleString$
	Field PreviousDebugConsoleString$[64]
	Field PreviousCommandOrder
	Field PreviousCommandOrder2
	Field FavouriteCommand$[9]
	Field EnteredFavCommand
	; point values
	Field	Points
	Field	PointsTimer
	Field	PointsCommentGiven
	Field	PointsComment$
	Field	PointsChain
	Field	point_fade#, t_x#, t_y#, point_r, point_g, point_b, thisheight#
	
	Field WorldToken
	Field WorldTokenTimer
	Field	world_fade#, worldt_x#, worldt_y#, world_r, world_g, world_b, worldthisheight#

	; Result stuff
	Field 	ResultTimer
	Field 	ResultOrder
	Field 	ResultOrder2
	Field	ResultTitlePosition#
	Field 	ResultBoxAlpha#[4]
	Field	ResultExitType
	Field 	ResultRankScale
	Field	ResultRankAlpha
	
	Field RingScaleTimer

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
	Field DebugMenuOptionOrder[128]
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
	
	Field ShowVoiceTimer
	Field VoiceLine$
	

	; Control tips
	Field ControlTipType
	Field ControlTip$
	Field ControlTipPickUpTimer
	Field ControlTipTypePickUp
End Type


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
Function Menu_SetLoadingTip()
	If Menu\LoadingTipTimer>0 Then Menu\LoadingTipTimer=Menu\LoadingTipTimer-timervalue#
	
	;Case : Menu\LoadingTip$= ""	
	
	If (Not(Menu\LoadingTipTimer>0)) Then 
		Menu\LoadingTipTimer=5*secs#
		Select Rand(1,25)
			Case 1: Menu\LoadingTip$= "Jump just after landing from a stomp to get extra height."
			Case 2: Menu\LoadingTip$= "Characters who spin can bounce off enemies to gain height depending on your falling speed."
			Case 3: Menu\LoadingTip$= "You are ranked from S to F based on how well you complete a stage."
			Case 4: Menu\LoadingTip$= "Type signs show the recommended character type for a specific path."
			Case 5: Menu\LoadingTip$= "Sonic's Lightspeed Attack instantly kills any regular enemy!"
			Case 6: Menu\LoadingTip$= "Join our Discord, browse for mods and more at our website: sonicworldfangame.com"
			Case 7: Menu\LoadingTip$= "The Power Stomp ability damages enemies in a wider radius than the regular Stomp."
			Case 8: Menu\LoadingTip$= "Collect all five Red Star Rings in a stage to unlock the encore act!"	
			Case 9: Menu\LoadingTip$= "Collect 100 rings in a stage to gain an extra life."	
			Case 10: Menu\LoadingTip$= "You can control the direction you exit out of a light dash by holding that direction."	
			Case 11: Menu\LoadingTip$= "The longer you charge your dash, the faster you will go."	
			Case 12: Menu\LoadingTip$= "If your speed is high enough, you can run on water for a certain distance."	
			Case 13: Menu\LoadingTip$= "Many moves can be cancelled out of by jumping, such as a Spindash Charge or Boom Kick"	
			Case 14: Menu\LoadingTip$= "Stomping on a spring will send you slightly higher."	
			Case 15: Menu\LoadingTip$= "Homing Attacks are very leniant if you are facing the target."	
			Case 16: Menu\LoadingTip$= "The Flame Shield makes you immune to fire hazards and allows you to run on lava."	
			Case 17: Menu\LoadingTip$= "The Earth Shield allows you to take two extra hits before taking damage."	
			Case 18: Menu\LoadingTip$= "The Bubble Shield allows you to breathe underwater and evade freezing attacks."	
			Case 19: Menu\LoadingTip$= "The Electric Shield makes you immune to electrical hazards and attracts rings."	
			Case 20: Menu\LoadingTip$= "Shadow's air dash has more speed than a regular air dash."	
			Case 21: Menu\LoadingTip$= "You can cancel your stomp by using your character's jump action."
			Case 22: Menu\LoadingTip$= "World tokens are found in secondary acts and unlock more content in the island."
			Case 23: Menu\LoadingTip$= "Try different character combinations for a different experience."	
			Case 24: Menu\LoadingTip$= "Visit the shop on the island for a wide range of unique items"	
			Case 25: Menu\LoadingTip$= "Destroy several enemies in a row to build up a higher score bonus."		
			Case 26: Menu\LoadingTip$= "Not every skill is for attacking, some are defensive or for maneuvering."
			Case 27: Menu\LoadingTip$= "Certain items from the shop can change how much score you recieve in a stage."	
		End Select
	EndIf 
	
	
End Function
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Interface_WorldTokenCounter(p.tPlayer, d.tDeltaTime, movedown#=0)
	
	If p\Underwater=1 And (Not(Game\Shield=OBJTYPE_BSHIELD)) Then
		If Menu\ThemeScoreStyle>0 Then
			movedown#=108
		Else
			movedown#=72
		EndIf
	EndIf
	
	
	If Game\Interface\WorldTokenTimer>1*secs# And Game\Interface\WorldTokenTimer<2*secs# Then
		If Game\Interface\worldt_x#<0 Then Game\Interface\worldt_x#=Game\Interface\worldt_x#+5*d\Delta
		If Game\Interface\worldt_x#>0 Then Game\Interface\worldt_x#=0

	ElseIf Game\Interface\WorldTokenTimer>2*secs# Then
		Game\Interface\worldt_x#=-88
		Game\Interface\world_fade#=1.0
	EndIf	
	
	If Game\Interface\WorldTokenTimer>0 Then
		DrawImageEx(INTERFACE(Interface_Icons2), 94+Game\Interface\worldt_x#*GAME_WINDOW_SCALE#,  (132+movedown#)*GAME_WINDOW_SCALE#, 3)
		DrawBetterNumber(TOKENS+Game\TokensToAdd, (64+Game\Interface\worldt_x#)*GAME_WINDOW_SCALE#, (132+movedown#)*GAME_WINDOW_SCALE#)
	EndIf	
	
End Function

	Function Interface_TrickPointsCounter(p.tPlayer, d.tDeltaTime, movedown#=0)
		
		If Menu\MissionTime=1 Then Return
		
		If p\Underwater=1 And (Not(Game\Shield=OBJTYPE_BSHIELD)) Then
			If Menu\ThemeScoreStyle>0 Then
				movedown#=72
			Else
				movedown#=36
			EndIf
		ElseIf Menu\ThemeScoreStyle>0 Then
			movedown#=36
		EndIf
			
		
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

Function Achievement_Grant(ach$,icon,amount,sound)
	
	
	Game\Interface\Achievement$=ach$
	Game\Interface\AchievementIcon=icon
	Game\Interface\AchievementAmount=amount
	Game\Interface\AchievementSound=sound
	
End Function


	
Function Achievements_Render(d.tDeltaTime)
	StartDraw()
	SetBlend(FI_ALPHABLEND)
	SetAlpha(1.0)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetColor(255, 255, 255)
	
	If Game\Interface\AchievementState>0 Then Game\Interface\AchievementTimer=Game\Interface\AchievementTimer+timervalue#
	Select Game\Interface\AchievementState
		Case 2,3
			
			
			
			SetAlpha(Game\Interface\AchievementAlpha#)
			SetColor(255,130,0)
			DrawRealText("Challenge Complete!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-117.5*GAME_WINDOW_SCALE#, (Interface_TextControls_1), 1, 0)
			SetColor(255,255,255)
			SetAlpha(1.0)
			
			
		If Game\Interface\AchievementTimer>1.45*secs# Then
				Game\Interface\AchievementAlpha2# = Game\Interface\AchievementAlpha2# + 0.05*d\Delta
				SetAlpha(Game\Interface\AchievementAlpha2#)
				DrawRealText(Game\Interface\Achievement$, GAME_WINDOW_W/2, GAME_WINDOW_H/2-97.5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1,0,255,255,255,1.1)
				SetAlpha(1.0)
			EndIf
			
			If Game\Interface\AchievementTimer>2.15*secs# Then
				Game\Interface\AchievementAlpha3# = Game\Interface\AchievementAlpha3# + 0.05*d\Delta
				SetAlpha(Game\Interface\AchievementAlpha3#)
				DrawImageEx(INTERFACE(Interface_Icons),GAME_WINDOW_W/2-40, GAME_WINDOW_H/2-77.5*GAME_WINDOW_SCALE#, Game\Interface\AchievementIcon)
				DrawRealText(" + "+Game\Interface\AchievementAmount, GAME_WINDOW_W/2+40, GAME_WINDOW_H/2-77.5*GAME_WINDOW_SCALE#, (Interface_Text_3), 1,0,255,255,255,1.1)
				SetAlpha(1.0)
			EndIf
	End Select
	
	Select Game\Interface\AchievementState
		Case 0
			If (Not(Game\Interface\Achievement$="")) Then 
				Game\Interface\AchievementAlpha#=0 
				Game\Interface\AchievementAlpha2#=0 
				Game\Interface\AchievementAlpha3#=0 
				Game\Interface\AchievementTimer=0
				Game\Interface\AchievementState=1  
			EndIf
		Case 1
			If Game\Interface\AchievementTimer>0.75*secs# Then
				PlaySmartSound(Sound_Achievement)
				Game\Interface\AchievementState=2
			EndIf
		Case 2
			If Game\Interface\AchievementTimer>4.75*secs# Then
				Game\Interface\AchievementState=3
				Game\Interface\AchievementAlpha#=1 
			Else
				If Game\Interface\AchievementTimer>0 Then Game\Interface\AchievementAlpha# = Game\Interface\AchievementAlpha# + 0.05*d\Delta
			EndIf
			
			
		Case 3
			Game\Interface\AchievementAlpha# = Game\Interface\AchievementAlpha# - 0.05*d\Delta
			Game\Interface\AchievementAlpha2# = Game\Interface\AchievementAlpha#
			Game\Interface\AchievementAlpha3# = Game\Interface\AchievementAlpha#
			
			If Game\Interface\AchievementAlpha#<0 Then Game\Interface\Achievement$="" : Game\Interface\AchievementSounded=0 : Game\Interface\AchievementTimer= 0 : Game\Interface\AchievementState=0
	End Select
	
	If Game\Interface\AchievementAlpha#>1 Then Game\Interface\AchievementAlpha#=1
	If Game\Interface\AchievementAlpha2#>1 Then Game\Interface\AchievementAlpha2#=1
	If Game\Interface\AchievementAlpha3#>1 And Game\Interface\AchievementSounded=0 Then PlaySmartSound(Game\Interface\AchievementSound) :Menu\Wallet=Menu\Wallet+Game\Interface\AchievementAmount :  : Game\Interface\AchievementAlpha3#=1 : Game\Interface\AchievementSounded=1
	
	EndDraw()
	
	
	
End Function
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Interface_Render(p.tPlayer,d.tDeltaTime)
		StartDraw()
		SetBlend(FI_ALPHABLEND)
		SetAlpha(1.0)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(255, 255, 255)

		If Game\Victory>0 And Menu\ChaoGarden=0 Then
			Interface_Render_Result(p,d)
		Else
			If Menu\Pause=0 Then Interface_Render_Stage(p,d)
		EndIf

		EndDraw()
	End Function
Function Interface_Render_Stage_DebugInfo()
	For i = 1 To 32
		DrawRealText(Game\Interface\DebugInfoText$[i], 12*GAME_WINDOW_SCALE#, (10*i)*GAME_WINDOW_SCALE#, (Interface_Text_1), 0, 0, 36, 81, 143)
	Next
End Function
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Interface_Render_Stage(p.tPlayer,d.tDeltaTime)
	
	If Game\Interface\DebugConsole=1 Then
		Interface_Render_DebugConsole(p)
	Else
		If Game\CinemaMode=0 Then
			If Game\Interface\DebugPlacerOn=0 Then
				If Game\Interface\HideInterface=0 Then 
					If Game\Interface\ShowFPS=1 Then
						Interface_Render_Stage_DebugInfo()
					ElseIf Game\Interface\Shop=1 Then
						Interface_Render_Stage_Shop()
					ElseIf Game\Interface\SoundTest=1 Then
						Interface_Render_Stage_SoundTest(p,d)
					Else
						If Menu\TutorialMode=0 And Menu\CollectionRoom=0 Then
							Interface_Render_Stage_Stage(p,d)
						Else
							Interface_Render_Stage_Tutorial(p,d)
						EndIf
					EndIf
				EndIf
			Else
				If Input\Pressed\Change Then Game\Interface\HideInterface=Abs(Game\Interface\HideInterface-1)
				If Game\Interface\HideInterface=0 Then Interface_Render_Stage_Debug(p)
			EndIf
		Else
			If Input\Pressed\Change Then Game\Interface\HideInterface=Abs(Game\Interface\HideInterface-1)
			If Game\Interface\HideInterface=0 Then Interface_Render_Stage_Cinema(p)
		EndIf
	EndIf
	
	
End Function
Function Interface_RedRingCounter(inpause=False)
	
	If (Not(Menu\ChaoGarden=0)) Or Menu\MissionNo<>1 Then Return
	
	If (Game\Interface\ShowRedRingTimer>0 Or inpause) Then
		
		Game\Interface\ShowRedRingTimer=Game\Interface\ShowRedRingTimer-timervalue#
		
		DrawImageEx(INTERFACE(Interface_RedRingBox), GAME_WINDOW_W-125*GAME_WINDOW_SCALE#, GAME_WINDOW_H-125*GAME_WINDOW_SCALE#, 15)
		
		For i =1 To 5
			If Game\Gameplay\GotRedRing[i]=0 Then
				
				SetAlpha(0.75)
				SetColor(7.5,7.5,7.5)
			Else
				SetAlpha(1)
				SetColor(255,255,255)
			EndIf
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-(150-(i*25))*GAME_WINDOW_SCALE#, GAME_WINDOW_H-35*GAME_WINDOW_SCALE#, 32)
		Next
		
		
		
		SetAlpha(1)
		SetColor(255,255,255)
		
	EndIf 
End Function 
Function Interface_Gauge()
	
	If Game\Gameplay\GaugeEnergy<100 Then
		SetColor(Menu\ThemeGaugeR,Menu\ThemeGaugeG,Menu\ThemeGaugeB)
	Else
		SetColor(Menu\ThemeGaugeFR,Menu\ThemeGaugeFG,Menu\ThemeGaugeFB)
	EndIf
	DrawRect(50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-35*GAME_WINDOW_SCALE#, (Game\Gameplay\GaugeEnergy)*2.13/1.5, 10, 1)
	SetColor(255,255,255)
	DrawImageEx(INTERFACE(Interface_Gauge), 125*GAME_WINDOW_SCALE#, GAME_WINDOW_H-125*GAME_WINDOW_SCALE#, 15)
	
End Function
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Interface_Render_DebugConsole(p.tPlayer)
	
	
	
	
	DrawRealText(Game\Interface\DebugConsoleString$, 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)
	
	If Game\Interface\EnteredFavCommand=0 Then
		If KeyHit(KEY_A) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"a"
		If KeyHit(KEY_B) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"b"
		If KeyHit(KEY_C) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"c"
		If KeyHit(KEY_D) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"d"
		If KeyHit(KEY_E) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"e"
		If KeyHit(KEY_F) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"f"
		If KeyHit(KEY_G) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"g"
		If KeyHit(KEY_H) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"h"
		If KeyHit(KEY_I) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"i"
		If KeyHit(KEY_J) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"j"
		If KeyHit(KEY_K) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"k"
		If KeyHit(KEY_L) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"l"
		If KeyHit(KEY_M) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"m"
		If KeyHit(KEY_N) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"n"
		If KeyHit(KEY_O) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"o"
		If KeyHit(KEY_P) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"p"
		If KeyHit(KEY_Q) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"q"
		If KeyHit(KEY_R) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"r"
		If KeyHit(KEY_S) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"s"
		If KeyHit(KEY_T) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"t"
		If KeyHit(KEY_U) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"u"
		If KeyHit(KEY_V) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"v"
		If KeyHit(KEY_W) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"w"
		If KeyHit(KEY_X) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"x"
		If KeyHit(KEY_Y) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"y"
		If KeyHit(KEY_Z) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"z"
		
		If KeyHit(KEY_1) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"1"
		If KeyHit(KEY_2) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"2"
		If KeyHit(KEY_3) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"3"
		If KeyHit(KEY_4) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"4"
		If KeyHit(KEY_5) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"5"
		If KeyHit(KEY_6) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"6"
		If KeyHit(KEY_7) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"7"
		If KeyHit(KEY_8) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"8"
		If KeyHit(KEY_9) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"9"
		If KeyHit(KEY_0) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"0"
		
		If KeyHit(KEY_HYPHEN) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"-"
		
		If KeyHit(52) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+"."
		
		If KeyHit(KEY_ARROW_DOWN) Then
			If Game\Interface\PreviousCommandOrder2<Game\Interface\PreviousCommandOrder Then Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder2+1
			Game\Interface\DebugConsoleString$=Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder2]
			
		EndIf	
		
		If KeyHit(KEY_ARROW_UP) Then
			If Game\Interface\PreviousCommandOrder2>0 Then Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder2-1
			Game\Interface\DebugConsoleString$=Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder2]
			
		EndIf	
		
		
		
		If KeyHit(KEY_SPACE) Then Game\Interface\DebugConsoleString$=Game\Interface\DebugConsoleString$+" "
		
		If KeyHit(KEY_BACKSPACE) Then 
			If Len(Game\Interface\DebugConsoleString$)>0 Then Game\Interface\DebugConsoleString$=Left(Game\Interface\DebugConsoleString$,Len(Game\Interface\DebugConsoleString$)-1)
		EndIf
		
	EndIf
	
	If KeyHit(KEY_ENTER) Or  Game\Interface\EnteredFavCommand=1 Then
		If Game\Interface\PreviousCommandOrder<16 Then Game\Interface\PreviousCommandOrder=Game\Interface\PreviousCommandOrder+1
		Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder]=Game\Interface\DebugConsoleString$
		Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder
		
		If Left(Game\Interface\DebugConsoleString$,7) = "camdest" Then
			
			
			Game\CamDestSpeed#=(Float(Mid$ (Game\Interface\DebugConsoleString$, 9, 3)))
			PositionEntity cam\Lock\PosMesh, cam\CinemaX#, cam\CinemaY#, cam\CinemaZ#, 1
			PositionEntity cam\Lock\PosMeshTarget, Game\CamDestX#, Game\CamDestY#, Game\CamDestZ#, 1
			
			Game\CamDest=1	
				
		EndIf
		
		If Menu\Developer=1 Then
			Select Game\Interface\DebugConsoleString$
				Case "card"
					If Menu\StartedStageWarp=0 Then Menu_GoToMissionCard()
				Case "endmeshes"
					Game_Stage_End_Mesh()
				Case "charvalues"
					LoadCharacterStuff()
					DeformCharacter_FixUps(p)
				Case "shop"
					Game\Interface\Shop = 1 : Game\Interface\ShopMenuOption=1
				Case "sound"
					Game\Interface\SoundTest = 1 : Game\Interface\ShopMenuOption=1	
				Case "save"
					SaveGame(False)
			End Select
			
			If Left(Game\Interface\DebugConsoleString$,12) = "set redrings" Then
				REDRINGS=(Int(Mid$ (Game\Interface\DebugConsoleString$, 14, 5)))
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,10) = "set tokens" Then
				TOKENS=(Int(Mid$ (Game\Interface\DebugConsoleString$, 12, 5)))
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,11) = "set emblems" Then
				EMBLEMS=(Int(Mid$ (Game\Interface\DebugConsoleString$, 13, 5)))
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			
			
			If Left(Game\Interface\DebugConsoleString$,6) = "warpto" Then
				Menu\StartedStageWarp=1
				Menu\HubStage=Menu\Stage
				Menu\HubMission=Menu\Mission
				Menu\HubMissionNo=Menu\MissionNo
				Menu\HubMissionMach=Menu\MissionMach
				Menu\HubMissionPerfect=Menu\MissionPerfect
				Menu\HubMissionTime=Menu\MissionTime
				Menu\MissionNo=1
				
				Menu\WarpRingPath$=(Mid$ (Game\Interface\DebugConsoleString$, 8, 25))
				Menu_Stage_LoadMissions(0,True,1)
				Menu_GoToStage_SetMission(Menu\MissionNo)
				
				Game_Stage_Quit(2)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
		EndIf
		
		
		Select Game\Interface\DebugConsoleString$
			Case "frag is ruining my mesh uhul"
				Menu\Developer=Abs(Menu\Developer-1)
			Case "goal"
				If Menu\Stage>0 And Game\Victory=0 Then Player_Goal(p)
			Case "savecam"
				Game\CamDestX#=cam\CinemaX#
				Game\CamDestY#=cam\CinemaY#
				Game\CamDestZ#=cam\CinemaZ#
				
				
				
				
				
				
			Case "theme"
				Menu_LoadThemeData()
				
			Case "modchar"
				LoadMods_Character(p\Character)
				
			Case "kill"
				If Game\Interface\DebugConsoleString$="kill" Then Player_Die(p)
			Case "hurt"
				If Game\Interface\DebugConsoleString$="hurt" Then Player_Hit(p)
			Case "give speedshoe"
				MonitorIcon_Draw(OBJTYPE_SHOES-(OBJTYPE_RINGS-1))
				PlaySmartSound(Sound_Monitor)
				Game\SpeedShoes=1 : Game\SpeedShoeTimer=15.177130*secs#
				StopChannel(Game\Channel_Invincible) : StopChannel(Game\Channel_SpeedShoes)
				Game\Channel_SpeedShoes=PlaySmartSound(Sound_SpeedShoes)
			Case "give invinc"
				MonitorIcon_Draw(OBJTYPE_INVINC-(OBJTYPE_RINGS-1))
				Game\InvincTimer=15.177130*secs#
				PlaySmartSound(Sound_Monitor)
				StopChannel(Game\Channel_Invincible) : StopChannel(Game\Channel_SpeedShoes)
				Game\Channel_Invincible=PlaySmartSound(Sound_Invincible)
			Case "give shield"
				PlaySmartSound(Sound_MonitorShield)
				Game\Shield=OBJTYPE_NSHIELD
				MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1))
			Case "give eshield"
				PlaySmartSound(Sound_MonitorShield)
				Game\Shield=OBJTYPE_ESHIELD
				MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1))
			Case "give bshield"
				PlaySmartSound(Sound_Monitor)
				PlaySmartSound(Sound_ShieldBubble)
				Game\Shield=OBJTYPE_BSHIELD
				MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1))
			Case "give fshield"
				PlaySmartSound(Sound_Monitor)
				PlaySmartSound(Sound_ShieldFire)
				Game\Shield=OBJTYPE_FSHIELD
				MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1))
			Case "give tshield"
				PlaySmartSound(Sound_Monitor)
				PlaySmartSound(Sound_ShieldThunder)
				Game\Shield=OBJTYPE_TSHIELD
				MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1))
			Case "spawn"
				Player_SetPosition(p, Game\Stage\Properties\StartX#,Game\Stage\Properties\StartY#+10,Game\Stage\Properties\StartZ#,Game\Stage\Properties\StartDirection#)
			Case "reset objects"
				If Game\Victory=0 Then Game\ResetObjects=1
			Case "csupport"
				Menu\Settings\ControllerSupport#=Abs(Menu\Settings\ControllerSupport-1) : PlaySmartSound(Sound_Ring)
			Case "info"
				Game\Interface\ShowFPS=Abs(Game\Interface\ShowFPS-1)
			Case "reload"
				Game_Stage_Quit(2)
			Case "mav"
				RuntimeError("Memory access violation")
				
			Case "savepos"
				Game\SpawnX#=p\Objects\Position\x#
				Game\SpawnY#=p\Objects\Position\y#
				Game\SpawnZ#=p\Objects\Position\z#
				Game\SpawnDir#=p\Animation\Direction#+180
			Case "loadpos"
				PositionEntity(p\Objects\Entity,Game\SpawnX#,Game\SpawnY#+10,Game\SpawnZ#)
			Case "pause gauge"
				Game\DontLoseGauge=Abs(Game\DontLoseGauge-1)
			Case "mute"
				MuteEverything()
			Case "unmute"
				UnmuteEverything()
			Case "dirlock"
				Game\DirLock=Abs(Game\DirLock-1)
			Case "obj all"
				For i =1 To OBJTYPECOUNT
					TempAttribute\ObjectNo=i
					CreateObject_Create(p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#+50*i,0,0,0)
				Next
			Case "saveobj"
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then 
						xmlout = WriteFile("_DEBUGXML\quicksave.xml")
						WriteLine(xmlout, Lower$("	<Object Type="+Chr$(34)+objname$+Chr$(34)+">"))
							
							WriteLine(xmlout, "		<position x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")
							
							WriteLine(xmlout, "		<rotation pitch="+Chr$(34)+o\Rotation\x#+Chr$(34)+" yaw="+Chr$(34)+o\Rotation\y#+Chr$(34)+" roll="+Chr$(34)+o\Rotation\z#+Chr$(34)+"/>")
							WriteLine(xmlout, "		<power is="+Chr$(34)+o\power#+Chr$(34)+"/>")
							WriteLine(xmlout, Lower$("	</Object>"))	
							CloseFile xmlout
						EndIf
						
					Next
					
					
					
			End Select
			
			If Left(Game\Interface\DebugConsoleString$,4) = "cam " Then
				Game\CamLock=1800*secs#
				For c.tCamera = Each tCamera
					c\TargetRotation\x#=(Float(Mid$ (Game\Interface\DebugConsoleString$, 5, 3)))
					c\TargetRotation\y#=(Float(Mid$ (Game\Interface\DebugConsoleString$, 8, 3)))
					CAMERA_DISTANCE_NEAR#=(Float(Mid$ (Game\Interface\DebugConsoleString$, 11, 3)))
				Next
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,4) = "fov " Then
				
				
				cam\FieldOfView#=(Float(Mid$ (Game\Interface\DebugConsoleString$, 5, 3)))
					
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,7) = "wallet " Then
				
				
				Menu\Wallet=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 9)))
				
			EndIf
			
			
			;=========================================
			If Left(Game\Interface\DebugConsoleString$,8) = "fognear " Then
				
				
				Game\Stage\Properties\FogNearDist=(Int(Mid$ (Game\Interface\DebugConsoleString$, 9, 9)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,7) = "fogfar " Then
				
				
				Game\Stage\Properties\FogFarDist=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 9)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,5) = "fogr " Then
				
				
				Game\Stage\Properties\FogR=(Int(Mid$ (Game\Interface\DebugConsoleString$, 6, 9)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,5) = "fogg " Then
				
				
				Game\Stage\Properties\FogG=(Int(Mid$ (Game\Interface\DebugConsoleString$, 6, 9)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,5) = "fogb " Then
				
				
				Game\Stage\Properties\FogB=(Int(Mid$ (Game\Interface\DebugConsoleString$, 6, 9)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,5) = "speed" Then
				
				
				Player_SetSpeed(p,(Int(Mid$ (Game\Interface\DebugConsoleString$, 7, 16))))
				
			EndIf
			
			
			
			
			
			
			;=========================================
			
			
			
			If Left(Game\Interface\DebugConsoleString$,5) = "char " Then
				For i = 1 To CHAR_NONMODPLAYABLECOUNT
					If (Mid$ (Game\Interface\DebugConsoleString$, 6, 3)) = Lower$(ShortCharNames$(i,1)) Then p\NewCharacter=i
						
					Game\CheaterChangedCharacter=1
				Next
			EndIf
			If Left(Game\Interface\DebugConsoleString$,6) = "motion" Then p\Physics\REAL_MOTION_GROUND#=Float(Mid$ (Game\Interface\DebugConsoleString$, 8, 5))
			
			If Left(Game\Interface\DebugConsoleString$,12) = "set dirlock " Then
				Game\DirLockDir=(Int(Mid$ (Game\Interface\DebugConsoleString$, 13, 5)))
			EndIf	
			
			If Left(Game\Interface\DebugConsoleString$,10) = "obj create" Then
				TempAttribute\ObjectNo=Mid$ (Game\Interface\DebugConsoleString$, 11, 8)
				CreateObject_Create(p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#+50,0,0,0)
			EndIf	
			
			
			
			
			If Left(Game\Interface\DebugConsoleString$,9) = "quickstep" Then
				
				p\QuickstepSpeed#=Float(Mid$ (Game\Interface\DebugConsoleString$, 11, 5))
				
				
			EndIf
			
			
			
			
			If Left(Game\Interface\DebugConsoleString$,11) = "obj modify " Then
				Game\ObjToModify=Int(Mid$(Game\Interface\DebugConsoleString$, 12, 5))
			EndIf	
			
			
			If Left(Game\Interface\DebugConsoleString$,9) = "set power" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Power#=Float(Mid$ (Game\Interface\DebugConsoleString$, 11, 5))
					
				Next
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "add power" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Power#=o\Power#+Float(Mid$ (Game\Interface\DebugConsoleString$, 11, 5))
					PlaySmartSound(Sound_Ring)
				Next
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "sub power" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Power#=o\Power#-Float(Mid$ (Game\Interface\DebugConsoleString$, 11, 5))
					PlaySmartSound(Sound_Ring)
				Next
			EndIf
			
			
			If Left(Game\Interface\DebugConsoleString$,13) = "set rotationx" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Rotation\x#=Float(Mid$ (Game\Interface\DebugConsoleString$, 15, 5))
					
				Next
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,13) = "add rotationx" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Rotation\x#=o\Rotation\x#+Float(Mid$ (Game\Interface\DebugConsoleString$, 15, 5)) : RotateEntity(o\Entity,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#)
					
				Next
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,13) = "sub rotationx" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Rotation\x#=o\Rotation\x#-Float(Mid$ (Game\Interface\DebugConsoleString$, 15, 5)) : RotateEntity(o\Entity,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#)
					
				Next
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,13) = "set rotationy" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\InitialRotation\y#=Float(Mid$ (Game\Interface\DebugConsoleString$, 15, 5)) : RotateEntity(o\Entity,o\InitialRotation\x#,o\InitialRotation\y#,o\InitialRotation\z#)
					
				Next
				PlaySmartSound(Sound_Ring)
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,13) = "add rotationy" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Rotation\y#=o\Rotation\y#+Float(Mid$ (Game\Interface\DebugConsoleString$, 15, 5)) : RotateEntity(o\Entity,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#)
					
				Next
				PlaySmartSound(Sound_Ring)
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,13) = "sub rotationy" Then
				For o.tObject = Each tObject
					If o\ID=Game\ObjToModify Then o\Rotation\y#=o\Rotation\y#-Float(Mid$ (Game\Interface\DebugConsoleString$, 15, 5)) : RotateEntity(o\Entity,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#)
					
				Next
				PlaySmartSound(Sound_Ring)
			EndIf
			
			
			
			If Left(Game\Interface\DebugConsoleString$,9) = "animtest " Then
				p\Action=ACTION_ANIMTEST
				p\Animation\AnimTestAnim=((Mid$ (Game\Interface\DebugConsoleString$, 10, 2)))
				
				
				
				If p\Animation\AnimTestAnim=99 Then p\Action=ACTION_COMMON
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,10) = "give rings" Then
				Gameplay_AddRings(Int(Mid$ (Game\Interface\DebugConsoleString$, 12, 5)))
				PlaySmartSound(Sound_Ring)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "set rings" Then
				Gameplay_SetRings(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				PlaySmartSound(Sound_Ring)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "sub rings" Then
				Gameplay_SubstractRings(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				PlaySmartSound(Sound_Ring)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,10) = "give gauge" Then
				Gameplay_AddGaugeEnergy(Int(Mid$ (Game\Interface\DebugConsoleString$, 12, 5)))
				PlaySmartSound(Sound_Ring)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "set gauge" Then
				Gameplay_SetGaugeEnergy(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				PlaySmartSound(Sound_Ring)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "sub gauge" Then
				Gameplay_SubstractGaugeEnergy(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				PlaySmartSound(Sound_Ring)
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "add score" Then
				Gameplay_AddScore(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "set score" Then
				Gameplay_SetScore(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,9) = "sub score" Then
				Gameplay_SubstractScore(Int(Mid$ (Game\Interface\DebugConsoleString$, 11, 5)))
				Game\Interface\DebugConsoleString$=""
				
			EndIf
			
			
			
			
			
			
			
			If Left(Game\Interface\DebugConsoleString$,6) = "minval" Then
				Menu\ThemeMinuteVal=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 5)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,6) = "secval" Then
				Menu\ThemeSecondVal=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 5)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,6) = "milval" Then
				Menu\ThemeMilliVal=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 5)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,6) = "colval" Then
				Menu\ThemeColonVal=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 5)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,6) = "dotval" Then
				Menu\ThemeDotVal=(Int(Mid$ (Game\Interface\DebugConsoleString$, 8, 5)))
				
			EndIf
			
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite1" Then Menu\Settings\FavouriteCommand$[1]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite2" Then Menu\Settings\FavouriteCommand$[2]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite3" Then Menu\Settings\FavouriteCommand$[3]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite4" Then Menu\Settings\FavouriteCommand$[4]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite5" Then Menu\Settings\FavouriteCommand$[5]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite6" Then Menu\Settings\FavouriteCommand$[6]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite7" Then Menu\Settings\FavouriteCommand$[7]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite8" Then Menu\Settings\FavouriteCommand$[8]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			If Left(Game\Interface\DebugConsoleString$,10) = "favourite9" Then Menu\Settings\FavouriteCommand$[9]=Mid$ (Game\Interface\DebugConsoleString$, 12, 25)
			
			
			
			Game\Interface\DebugConsoleString$=""
			Game\Interface\EnteredFavCommand=0
			Menu\InputRestrictTimer=0.05*secs
			
			Game\Interface\DebugConsole=0
		EndIf
		
End Function
Function SaveScreenshot()
; Author: Leigh Bowers 
	Local sFileName$
    Local iFileNumber% = 0
    Repeat
        iFileNumber = iFileNumber + 1
        sFileName$ = "Screenshot" + String$("0", 3 - Len(Str(iFileNumber))) + iFileNumber + ".bmp"
    Until Not(FileType(sFileName))
    SaveBuffer FrontBuffer(), sFileName
End Function

	Function Interface_Render_Stage_Cinema(p.tPlayer)

		DrawRealText("Capture Mode", 0+12*GAME_WINDOW_SCALE#, 27.5*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 0, 0, 36, 81, 143)

		DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30+30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30+60)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
		DrawRealText("Move around", (30+60+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawSmartKey(INPUT_BUTTON_ACTIONACT, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, True)
		DrawRealText("Moving speed ("+cam\CinemaSpeed#+")", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, 60)
		DrawImageEx(INTERFACE(Interface_Keys), (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, 56)
		DrawRealText("Look around", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		
		DrawSmartKey(INPUT_BUTTON_BACK,(30)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#)
		DrawRealText("Free", (30+15)*GAME_WINDOW_SCALE#, (30+30*4)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawSmartKey(INPUT_BUTTON_CHANGE, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25)*GAME_WINDOW_SCALE#)
		DrawRealText("Hide interface", 30*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

		DrawSmartKey(INPUT_BUTTON_ACTIONSKILL2, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#)
		DrawRealText("Toggle update", 30*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
		
		DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, 30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*2)*GAME_WINDOW_SCALE#)
		DrawRealText("Cycle Filter", 30*GAME_WINDOW_SCALE#+15*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(25+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Interface_Render_Exit(p.tPlayer,d.tDeltaTime)
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
			For x=-30 To 30
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

	
Function Interface_RingCounter()
	
	If Game\Interface\RingScaleTimer>1*secs# Then Game\Interface\RingScaleTimer=Game\Interface\RingScaleTimer-timervalue#*2
	
	If Menu\TutorialMode=1 Or Menu\CollectionRoom=1 Then 
		i=30 
		If Menu\CollectionRoom=1 Then
			DrawImageEx(INTERFACE(Interface_Icons2), 30*GAME_WINDOW_SCALE#, 66*GAME_WINDOW_SCALE#, 3)
			DrawBetterNumber(TOKENS, 58*GAME_WINDOW_SCALE#, 66*GAME_WINDOW_SCALE#)
		EndIf
	ElseIf Menu\ThemeScoreStyle>0 Then
		i=102
	Else
		i=66
	EndIf 
	
	If Menu\ThemeRingStyle=1 Then
		SetColor(255,255,0) : DrawRealText("RINGS", 53*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1) : SetColor(255,255,255)
	Else
		
		If Menu\CollectionRoom=0 Then 
			SetScale(GAME_WINDOW_SCALE#*Game\Interface\RingScaleTimer/secs#, GAME_WINDOW_SCALE#*Game\Interface\RingScaleTimer/secs#)
			DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 16)
		Else
			DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		EndIf
		SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
	EndIf
	
	If (Game\Gameplay\Rings=0 Or Game\Interface\RingStolenTimer>0) And Menu\Pause=0 And Menu\CollectionRoom=0 Then
		If Game\Interface\RingStolenTimer>0 Then Game\Interface\RingStolenTimer=Game\Interface\RingStolenTimer-timervalue#
		For d.tDeltaTime = Each tDeltaTime
			flash=Sin#(MilliSecs() Mod 255) : SetColor(255,(255*flash)*d\Delta,(255*flash)*d\Delta)
		Next
	EndIf
	
	If Menu\CollectionRoom=0 Then j = Game\Gameplay\Rings Else j = Menu\Wallet
	
	If Menu\ThemeRingStyle=1 Then
		DrawBetterNumber(j, 248*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#,0,1)
	Else
		DrawBetterNumber(j, 58*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#)
	EndIf
	
	SetColor(255,255,255)
	
	DrawRealText(Game\Interface\DebugText$, 53*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1)
	
End Function

;===================================================================================================================================================================================

Function Interface_DrawHead(x#, y#, charno)
	If IsCharMod(charno+1) Then
		If Menu\Settings\Mods#=0 Then
			DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 1)
			
		Else
			
			DrawImageEx(INTERFACE(Interface_HeadsMod[charno-CHAR_MOD1+1]), x#, y#)
		EndIf
	Else
		DrawImageEx(INTERFACE(Interface_CharacterHeads[charno]), x#, y#)
	EndIf
End Function

Function Interface_MemberHeads(x#=0, y#=0)
	If (x#=0 And y#=0) Then
		
		Select Menu\Members
			Case 3:
				SetColor(Interface_Lives_R[InterfaceChar(pp(3)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(3)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(3)\RealCharacter)])
				Interface_DrawHead((30-15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(65+Menu\ThemeLifeOffset)*GAME_WINDOW_SCALE#, pp(3)\RealCharacter)
				SetColor(Interface_Lives_R[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(2)\RealCharacter)])
				Interface_DrawHead((30+15)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(65+Menu\ThemeLifeOffset)*GAME_WINDOW_SCALE#, pp(2)\RealCharacter)
				SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
				Interface_DrawHead((30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-((35+Menu\ThemeLifeOffset)+7.5/2.0)*GAME_WINDOW_SCALE#, pp(1)\RealCharacter)
				SetColor(255,255,255)
			Case 2:
				SetColor(Interface_Lives_R[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(2)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(2)\RealCharacter)])
				Interface_DrawHead((30+7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(65+Menu\ThemeLifeOffset)*GAME_WINDOW_SCALE#, pp(2)\RealCharacter)
				SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
				Interface_DrawHead((30-7.5)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-((65+Menu\ThemeLifeOffset)+7.5/2.0)*GAME_WINDOW_SCALE#, pp(1)\RealCharacter)
				SetColor(255,255,255)
			Case 1:
				SetColor(Interface_Lives_R[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_G[InterfaceChar(pp(1)\RealCharacter)],Interface_Lives_B[InterfaceChar(pp(1)\RealCharacter)])
				Interface_DrawHead((30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(65+Menu\ThemeLifeOffset)*GAME_WINDOW_SCALE#, pp(1)\RealCharacter)
				SetColor(255,255,255)
		End Select
		
		;lives
		If Menu\Members>1 Then
			DrawBetterNumber(Game\Gameplay\Lives, 67.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(65+Menu\ThemeLifeOffset)*GAME_WINDOW_SCALE#)
		Else
			DrawBetterNumber(Game\Gameplay\Lives, 58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(65+Menu\ThemeLifeOffset)*GAME_WINDOW_SCALE#)
		EndIf
		
		
	Else
		SetColor(Interface_Lives_R[pp(1)\RealCharacter],Interface_Lives_G[pp(1)\RealCharacter],Interface_Lives_B[pp(1)\RealCharacter])
		Interface_DrawHead(x#, y#, pp(1)\RealCharacter)
		SetColor(255,255,255)
	EndIf
End Function

Function Interface_ProgressBar(inputx#, inputy#)
	DrawImageEx(INTERFACE(Interface_ProgressBar), inputx#, inputy#)

	Local distancepercent# = Game\Gameplay\Progress#/Game\Gameplay\InitialProgress#
	Local gap# = ImageWidthEx#(INTERFACE(Interface_Progress))

	SetCustomColor ARGB(1, 255, 255, 255), ARGB(1, 255, 255, 255), ARGB(1, 5, 5, 255), ARGB(1, 5, 5, 255)
	For i = 0 To 80
		If (i/80.0)<=(1.0-distancepercent#) Then DrawImageRectEx%(INTERFACE(Interface_Progress), inputx#-(gap#*40)*GAME_WINDOW_SCALE#+(gap#*0.925*i)*GAME_WINDOW_SCALE#, inputy#, gap#, ImageHeightEx#(INTERFACE(Interface_Progress)), i)
	Next
	SetColor(255,255,255)

	SetColor(Interface_Emerald_R[Abs(Menu\Stage)],Interface_Emerald_G[Abs(Menu\Stage)],Interface_Emerald_B[Abs(Menu\Stage)])
	DrawImageEx(INTERFACE(Interface_Icons), inputx#+(gap#*40)*GAME_WINDOW_SCALE#, inputy#, 32)
	SetColor(255,255,255)
	Interface_MemberHeads(inputx#-(gap#*40)*GAME_WINDOW_SCALE#+(gap#*0.925*80*(1.0-distancepercent#))*GAME_WINDOW_SCALE#, inputy#)
End Function



Function Interface_TimeCounter(d.tDeltaTime)
	
	
	
	
	
	
	
	If Menu\RankMissionOnTime=1 Then
		
		If Game\Gameplay\Time<=Game\IdealTime Then
			rn=1
			tm=Game\IdealTime
		ElseIf Game\Gameplay\Time>Game\IdealTime+75*secs# Then
			rn=7
			tm=Game\LimitTime
		ElseIf Game\Gameplay\Time>Game\IdealTime+60*secs# Then
			rn=6	
			tm=Game\IdealTime+75*secs#
		ElseIf Game\Gameplay\Time>Game\IdealTime+45*secs# Then
			rn=5
			tm=Game\IdealTime+60*secs#
		ElseIf Game\Gameplay\Time>Game\IdealTime+30*secs# Then
			rn=4	
			tm=Game\IdealTime+45*secs#
		ElseIf Game\Gameplay\Time>Game\IdealTime+15*secs# Then
			rn=3
			tm=Game\IdealTime+30*secs#
		ElseIf Game\Gameplay\Time>Game\IdealTime Then
			rn=2	
			tm=Game\IdealTime+15*secs#
		ElseIf Game\Gameplay\Time<Game\LimitTime And Menu\MissionTime=1 Then
			rn=8
			
		EndIf
		
		SetScale(GAME_WINDOW_SCALE#*0.45, GAME_WINDOW_SCALE#*0.45)
		If rn<8 Then
			Rank_Draw(rn, GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#)
		Else
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 13)
		EndIf
		
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		DrawNumber(((tm)/1000) Mod 60, GAME_WINDOW_W-75*GAME_WINDOW_SCALE#,30*GAME_WINDOW_SCALE#, 2)
		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-87*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 10)
		DrawNumber(((tm)/60000), GAME_WINDOW_W-125*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 2)
	EndIf
	
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
	
	If Menu\ThemeScoreStyle>0 Then i = 66 Else i = 30
	
	j=Game\Gameplay\Time
	
	
	
	
	If Menu\ThemeTimeStyle=1 Then	
		DrawImageEx(INTERFACE(Interface_Numbers), 66*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 10)
		DrawImageEx(INTERFACE(Interface_Numbers), 116*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 11)
		DrawNumber((j/60000), 30*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		DrawNumber((j/1000) Mod 60, 80*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		DrawNumber((j/10) Mod 60, 130*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
	ElseIf Menu\ThemeTimeStyle=2 Then
		SetColor(255,255,0) : DrawRealText("TIME", 44*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1) : SetColor(255,255,255)
		DrawNumber((j/60000), 130*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		DrawImageEx(INTERFACE(Interface_Numbers), 166*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 10)
		DrawNumber((j/1000) Mod 60, 180*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		DrawImageEx(INTERFACE(Interface_Numbers), 216*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 11)
		DrawNumber((j/10) Mod 60, 230*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
	Else
		DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 15);icon
		DrawImageEx(INTERFACE(Interface_Numbers), 94*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 10)
		DrawImageEx(INTERFACE(Interface_Numbers), 144*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 11)
		DrawNumber((j/60000), 58*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		DrawNumber((j/1000) Mod 60, 108*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
		DrawNumber((j/10) Mod 60, 158*GAME_WINDOW_SCALE#, i*GAME_WINDOW_SCALE#, 2)
	EndIf
	
	
End Function
Function Interface_ScoreCounter()
	If Menu\RankMissionOnTime=1 Then 
		
	Else
		
		If Menu\ThemeScoreStyle=1 Then
			DrawImageEx(INTERFACE(Interface_Icons), 30*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 23);icon
			DrawBetterNumber(Game\Gameplay\Score,58*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#)
		ElseIf Menu\ThemeScoreStyle=2 Then
			SetColor(255,255,0) : DrawRealText("SCORE", 58*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1) : SetColor(255,255,255)
			DrawBetterNumber(Game\Gameplay\Score,130*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#,6)
		Else
			DrawBetterNumber(Game\Gameplay\Score, GAME_WINDOW_W-35*GAME_WINDOW_SCALE#, 30*GAME_WINDOW_SCALE#, 0, 1)
		EndIf
		
	EndIf
	
End Function

Function Interface_MissionStuff(p.tPlayer,d.tDeltaTime)
	If Game\Interface\ChaoItemCount>0 Or Menu\MissionTime=1 Then movemissioncounterup#=25.0*GAME_WINDOW_SCALE#*Game\Interface\ChaoIconSpread# Else movemissioncounterup#=0
	Select Menu\Mission
		Case MISSION_COLLECT#
			DrawImageEx(INTERFACE(Interface_Icons2), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, ICON_COLLECT)
			DrawBetterNumber(Game\MissionValue-Game\Gameplay\Collectibles, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
		Case MISSION_ENEMY#:
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 17)
			DrawBetterNumber(Game\MissionValue-Game\Gameplay\Enemies, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
		Case MISSION_RING#:
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 16)
			If Game\Gameplay\Rings<Game\MissionValue Then
				DrawBetterNumber(Game\MissionValue-Game\Gameplay\Rings, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			Else
				DrawBetterNumber(0, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			EndIf
		Case MISSION_HUNT#:
			For i=1 To 3
				If Game\Gameplay\ShardDistance[i]<9 Then
					If Game\Gameplay\ShardTimer[i]>0 Then
						If Game\Gameplay\ShardBeepTimer[i]>0 Then
							DrawImageEx(INTERFACE(Interface_Treasure_Big), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(i-1)*37.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, Game\Gameplay\ShardDistance[i])
						Else
							DrawImageEx(INTERFACE(Interface_Treasure), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(i-1)*37.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, Game\Gameplay\ShardDistance[i])
						EndIf
					Else
						DrawImageEx(INTERFACE(Interface_Treasure), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(i-1)*37.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0)
					EndIf
				EndIf
				If Game\Gameplay\ShardTimer[i]>0 Then Game\Gameplay\ShardTimer[i]=Game\Gameplay\ShardTimer[i]-timervalue#
				If Game\Gameplay\ShardBeepTimer[i]>0 Then Game\Gameplay\ShardBeepTimer[i]=Game\Gameplay\ShardBeepTimer[i]-timervalue#
			Next
		Case MISSION_LAP#
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 23)
			
			DrawBetterNumber(Game\Gameplay\CurrentLap, GAME_WINDOW_W-114*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-86*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 33)
			DrawBetterNumber(Game\Gameplay\TotalLapCount, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
			
		Case MISSION_GOLD#:
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 9)
			DrawBetterNumber(Game\MissionValue-Game\Gameplay\GoldEnemies, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
		Case MISSION_BALLOONS#:
			DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 7)
			DrawBetterNumber(Game\MissionValue-Game\Gameplay\Balloons, GAME_WINDOW_W-58*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#, 0, 1)
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
						DrawRealText("Boss:", GAME_WINDOW_W-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-50*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
						DrawImageEx(INTERFACE(Interface_Boss), GAME_WINDOW_W-90*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-30*GAME_WINDOW_SCALE#)
					EndIf
				EndIf
			Next
		Case MISSION_RIVAL#:
			For rvl=1 To Game\RivalAmount
				SetColor(Interface_Lives_R[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_G[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_B[InterfaceChar(ppe(rvl)\RealCharacter)])
				Interface_DrawHead(GAME_WINDOW_W-(30)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*rvl)*GAME_WINDOW_SCALE#, ppe(rvl)\RealCharacter)
				For i=1 To 5
					SetColor(15,15,15) : SetAlpha(0.375)
					SetScale(GAME_WINDOW_SCALE#/1.25,GAME_WINDOW_SCALE#/1.25)
					DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-(45+i*11.25)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*rvl)*GAME_WINDOW_SCALE#, 28)
					SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
				Next
				For i=1 To ppe(rvl)\Rival\Health
					SetColor(Interface_Lives_R[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_G[InterfaceChar(ppe(rvl)\RealCharacter)],Interface_Lives_B[InterfaceChar(ppe(rvl)\RealCharacter)]) : SetAlpha(1.0)
					SetScale(GAME_WINDOW_SCALE#/1.5,GAME_WINDOW_SCALE#/1.5)
					DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-(45+i*11.25)*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-(30*rvl)*GAME_WINDOW_SCALE#, 28)
					SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
				Next
				SetAlpha(1.0)
				SetColor(255,255,255)
			Next
		Case MISSION_FLICKY#:
			For i=1 To 5
				j=5-i+1
				If i<=Game\Gameplay\Flickies Then
					DrawImageEx(INTERFACE(Interface_Flickies), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(j-1)*35*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-35*GAME_WINDOW_SCALE#, 1)
				Else
					DrawImageEx(INTERFACE(Interface_Flickies), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#-(j-1)*35*GAME_WINDOW_SCALE#, GAME_WINDOW_H-movemissioncounterup#-35*GAME_WINDOW_SCALE#, 0)
				EndIf
			Next
	End Select
End Function
;===================================================================================================================================================================================
Function Interface_Render_Stage_Tutorial(p.tPlayer, d.tDeltaTime)
	
	
	
	Interface_Gauge()
	Interface_RingCounter()
	Update_Monitor_Icons(d)
	Update_ChaoItem_Icons(d)
	
	
	If Menu\Settings\CONTROLTIPS#=1 Then
		Interface_ControlTipDraw(p, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, 150*GAME_WINDOW_SCALE#)
	EndIf
	
	Interface_MiscStuff(p)
	
End Function
Global PROMPT_LEFT=1
Global PROMPT_RIGHT=2
;===================================================================================================================================================================================
Function Interface_ManagePrompts(d.tDeltaTime)
	
	
	
	If Game\Interface\PromptTimer>0 Then Game\Interface\PromptTimer=Game\Interface\PromptTimer-timervalue#
	
	If Game\Interface\PromptTimer>0 Then
		Select Game\Interface\PromptType
			Case PROMPT_LEFT
				drawprompt=1
				promptno=2
			Case PROMPT_RIGHT
				drawprompt=1
				promptno=3
		End Select
	ElseIf Game\QuickStepLock=1 Then 
		drawprompt=1
		promptno=0
	ElseIf  Game\MachLock>0 And Menu\MissionMach=0
		drawprompt=1
		promptno=1
	Else
		drawprompt=0
		Game\Interface\PromptSound=0
	EndIf
	
	If drawprompt = 1 Then 
		SetScale(GAME_WINDOW_SCALE#*1.75, GAME_WINDOW_SCALE#*1.75)
		If Game\Interface\PromptSound=0 Then PlaySmartSound(Sound_Prompt) : Game\Interface\PromptSound=1
		DrawImageEx(INTERFACE(Interface_Prompts), GAME_WINDOW_W/2+0*35*GAME_WINDOW_SCALE#, 50*GAME_WINDOW_SCALE#, promptno)
	Else
		Game\Interface\PromptSound=0
	EndIf
	
	
	
End Function

Function Interface_Render_Stage_Stage(p.tPlayer,d.tDeltaTime)
	
	If Menu\ChaoGarden=0 Then;!!
		
		
		DrawImageEx(INTERFACE(Interface_HudLeft), 125*GAME_WINDOW_SCALE#, 125*GAME_WINDOW_SCALE#)
		DrawImageEx(INTERFACE(Interface_HudRight), GAME_WINDOW_W-125*GAME_WINDOW_SCALE#, 125*GAME_WINDOW_SCALE#)
		
		Interface_TimeCounter(d)
		Interface_RingCounter()
		Interface_ScoreCounter()
		Interface_RedRingCounter()
		Interface_Gauge()
		Interface_TrickPointsCounter(p, d)
		Interface_WorldTokenCounter(p, d)
		Interface_MemberHeads()
		Interface_MissionStuff(p,d)
		Interface_MiscStuff(p)
		Interface_ManagePrompts(d)
		
		Update_Monitor_Icons(d)
		
		Update_ChaoItem_Icons(d)
		
		;checkpoint
		If Game\Interface\FlashCheckTimerTimer>0 Then
			Game\Interface\FlashCheckTimerTimer=Game\Interface\FlashCheckTimerTimer-timervalue#
			If Game\Interface\FlashCheckTimerTimer<0.8*secs# Or (Game\Interface\FlashCheckTimerTimer>1*secs# And Game\Interface\FlashCheckTimerTimer<1.2*secs#) Or (Game\Interface\FlashCheckTimerTimer>1.4*secs# And Game\Interface\FlashCheckTimerTimer<1.6*secs#) Or (Game\Interface\FlashCheckTimerTimer>1.8*secs# And Game\Interface\FlashCheckTimerTimer<2*secs#) Then
				SetColor(220,220,220)
				If Menu\Mission=MISSION_LAP# Then
					DrawRealText("Lap "+Game\Gameplay\CurrentLap+" of "+Game\Gameplay\TotalLapCount,GAME_WINDOW_W/2-14*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#,(Interface_TextControls_2), 1)
				Else
					DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2-14*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 10)
					DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+36*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 11)
					DrawNumber((Game\Gameplay\CheckTime/60000), GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 2)
					DrawNumber((Game\Gameplay\CheckTime/1000) Mod 60, GAME_WINDOW_W/2, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 2)
					DrawNumber((Game\Gameplay\CheckTime/10) Mod 60, GAME_WINDOW_W/2+50*GAME_WINDOW_SCALE#, GAME_WINDOW_H-70*GAME_WINDOW_SCALE#, 2)
				EndIf
				SetColor(255,255,255)
			EndIf
		EndIf
		
		;breath counter
		If p\Underwater=1 And (Not(Game\Shield=OBJTYPE_BSHIELD)) Then
			DrawImageEx(INTERFACE(Interface_Icons), 32.5*GAME_WINDOW_SCALE#, 102*GAME_WINDOW_SCALE#, 14)
			If p\DrownValue>=0 Then DrawBetterNumber(p\DrownValue, 58*GAME_WINDOW_SCALE#, 102*GAME_WINDOW_SCALE#)
		EndIf
		
		
		
		;control tips
		If (Not(Game\StartoutLock>0 Or Game\ControlLock>0)) Then
			If Menu\Settings\CONTROLTIPS#=1 Then Interface_ControlTipDraw(p, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, 150*GAME_WINDOW_SCALE#)
		EndIf
		
	Else;!!
		
		Interface_Render_Stage_Chao(p,d)
		
	EndIf;!!
	
		; Draw title card
	If Menu\TitleCardTimer<1*secs# Then
		Menu\TitleCardTimer=Menu\TitleCardTimer+timervalue#
		DrawTitleCardStuff()
	EndIf
	
	
End Function
Function Interface_MiscStuff(p.tPlayer)
	If Menu\ChaoGarden=0 Then
		If (Not(p\Action=ACTION_TORNADO)) Then
			i = 0
			j=0
			If Menu\CollectionRoom=1 Or Menu\TutorialMode=1 Then j=30 Else j=66
			Select p\Character
				Case CHAR_CRE:
					If p\CheeseRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\CheeseRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_TAI,CHAR_CHA,CHAR_TDL,CHAR_ROU,CHAR_EGR,CHAR_BEA:
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_WAV:
					If p\BoomerangAway=1 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(1/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_SIL,CHAR_INF,CHAR_SHA:
					If p\PsychoChargeTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\PsychoChargeTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_LEVITATE Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\LevitationTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_MAR:
					If p\BoomerangAway=1 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(1/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLUTTER Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_BIG:
					If p\CheeseRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\CheeseRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_RAY:
					If p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\SoarTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_NAC:
					If p\ThrowTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\ThrowTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_OME,CHAR_VEC,CHAR_TIA:
					If p\ShootCooldownTimer>0 Then
						Game\Interface\ShowCaution1Timer=0.01*secs#
						DrawBetterNumber(p\ShootCooldownTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_BAR:
					If p\Action=ACTION_SLEET Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_MPH:
					If p\Action=ACTION_LEVITATE Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\LevitationTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_TIK:
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_SPIRIT Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					Else
						If p\ShootCooldownTimer>0 Then
							Game\Interface\ShowCaution1Timer=0.01*secs#
							DrawBetterNumber(p\ShootCooldownTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						EndIf
					EndIf
				Case CHAR_HON:
					If p\Action=ACTION_FLUTTER Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\GlideTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_EME:
					If p\InvisibilityRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\InvisibilityRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_FLY Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\FlyTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_GME:
					If p\InvisibilityRestrictTimer>0 Then
						Game\Interface\ShowCaution3Timer=0.01*secs#
						DrawBetterNumber(p\InvisibilityRestrictTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
						i=i+1
					EndIf
					If p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\SoarTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
				Case CHAR_MT3:
					If p\Action=ACTION_LEVITATE Then
						Game\Interface\ShowCaution2Timer=0.01*secs#
						DrawBetterNumber(p\LevitationTimer/secs#, GAME_WINDOW_W-73*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 0, 1)
					EndIf
			End Select
			
			i = 0
			If Game\Interface\ShowCaution1Timer>0 Then
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-35*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 11)
				i=i+1
			EndIf
			If Game\Interface\ShowCaution3Timer>0 Then
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-35*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 13)
				i=i+1
			EndIf
			If Game\Interface\ShowCaution2Timer>0 Then ;has to be below
				DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W-30*GAME_WINDOW_SCALE#, (j+36*i)*GAME_WINDOW_SCALE#, 12)
				i=i+1
			EndIf
		EndIf
	EndIf
	
	If Menu\Settings\Debug#=1 And Game\Interface\ShowFPS=1 Then
		
		
;		DrawNumber(Game\Others\Fps, GAME_WINDOW_W/2+100*GAME_WINDOW_SCALE#, 15*GAME_WINDOW_SCALE#, 0, 1)
;		DrawNumber(Game\Others\CurrentCameraRange, GAME_WINDOW_W/2+200*GAME_WINDOW_SCALE#, 15*GAME_WINDOW_SCALE#, 0, 1)
;		DrawNumber(OBJECT_VIEWDISTANCE#, GAME_WINDOW_W/2+290*GAME_WINDOW_SCALE#, 15*GAME_WINDOW_SCALE#, 0, 1)
;		DrawNumber(Game\IdealScore, GAME_WINDOW_W/2+100*GAME_WINDOW_SCALE#, 35*GAME_WINDOW_SCALE#, 0, 1)
;		
;		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+94*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 10)
;		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+144*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 10)
;		DrawNumber((Game\IdealTime/60000), GAME_WINDOW_W/2+58*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 2)
;		DrawNumber((Game\IdealTime/1000) Mod 60, GAME_WINDOW_W/2+108*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 2)
;		DrawNumber((Game\IdealTime/10) Mod 60, GAME_WINDOW_W/2+158*GAME_WINDOW_SCALE#-60*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 2)
;		
;		DrawBetterNumber(p\Physics\COMMON_XZACCELERATION#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#)
;		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#, 11)
;		DrawBetterNumber(p\SpeedLength#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-110*GAME_WINDOW_SCALE#)
;		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-110*GAME_WINDOW_SCALE#, 11)
;		
;		DrawBetterNumber(p\Physics\COMMON_XZTOPSPEED#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#)
;		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, 11)
;		DrawBetterNumber(p\Physics\JUMP_STRENGTH#*10000, GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#)
;		DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W-70*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+80*GAME_WINDOW_SCALE#, 11)
		
;		If Game\Cheater=1 Then
;			DrawRealText("CHEATED", GAME_WINDOW_W/2, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1)
;		EndIf
	EndIf
	
	If ChannelPlaying(p\Channel_Voice) Then
		SetColor(235,235,235)
		DrawRealText(Game\Interface\VoiceLine$, GAME_WINDOW_W/2.0, GAME_WINDOW_H-40*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
		SetColor(255,255,255)
	Else
		Game\Interface\VoiceLine$=""
	EndIf
		; Hint stuff
	If Game\Interface\ShowHintTimer>0 Then
		Game\Interface\ShowHintTimer=Game\Interface\ShowHintTimer-timervalue#
		SetColor(235,235,235)
		DrawRealText(Game\Interface\HintLine1$, GAME_WINDOW_W/2.0, GAME_WINDOW_H-40*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
		DrawRealText(Game\Interface\HintLine2$, GAME_WINDOW_W/2.0, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
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

		For i=1 To 18
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
					DrawImageEx(INTERFACE(Interface_Keys_Small), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#-6*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, 5)
					DrawImageEx(INTERFACE(Interface_Keys_Small), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#+3*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, button)
					DrawImageEx(INTERFACE(Interface_Keys_Small), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#+9*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, button2)
				Case 14,15,16,17,18:
					DrawImageEx(INTERFACE(Interface_Keys), GAME_WINDOW_W/2-000*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-11)*30*GAME_WINDOW_SCALE#, button)
				Default:
					DrawImageEx(INTERFACE(Interface_Keys_Small), GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#-6*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, 5)
					DrawImageEx(INTERFACE(Interface_Keys_Small), GAME_WINDOW_W/2-300*GAME_WINDOW_SCALE#+3*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-4.5+i-1)*30*GAME_WINDOW_SCALE#, button)
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
Function DrawSpinner()
	
	SetColor(Menu_ReturnCardColor(1,Menu\Character[1]),Menu_ReturnCardColor(2,Menu\Character[1]),Menu_ReturnCardColor(3,Menu\Character[1]))
	
	SetAlpha(Abs(1-Menu\TitleCardTimer/secs#)*0.5)
	Select inmenu
		Case False: SetScale(GAME_WINDOW_SCALE#*Min#(1.85,Abs((Menu\TitleCardTimer/secs#))*2+0.4), GAME_WINDOW_SCALE#*Min#(1.85,Abs((Menu\TitleCardTimer/secs#))*2+0.4))
		Case True: SetScale(GAME_WINDOW_SCALE#*Min#(2.0,Abs((Menu\TitleCardTimer/secs#))*2+0.4), GAME_WINDOW_SCALE#*Min#(2.0,Abs((Menu\TitleCardTimer/secs#))*2+0.4))
	End Select
	DrawImageEx(INTERFACE(Interface_Spinner), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetAlpha(1.0)
	
	SetAlpha(Abs(1-Menu\TitleCardTimer/secs#))
	SetScale(GAME_WINDOW_SCALE#*Min#(1.0,Abs(1-(Menu\TitleCardTimer/secs#))+0.4), GAME_WINDOW_SCALE#*Min#(1.0,Abs(1-(Menu\TitleCardTimer/secs#))+0.4))
	DrawImageEx(INTERFACE(Interface_Spinner), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetAlpha(1.0)
	
	
End Function 
Function DrawHeroesSpinner()
	
	
	SetAlpha(Abs(1-Menu\TitleCardTimer/secs#)*0.5)
	Select inmenu
		Case False: SetScale(GAME_WINDOW_SCALE#*Min#(1.85,Abs((Menu\TitleCardTimer/secs#))*2+0.4), GAME_WINDOW_SCALE#*Min#(1.85,Abs((Menu\TitleCardTimer/secs#))*2+0.4))
		Case True: SetScale(GAME_WINDOW_SCALE#*Min#(2.0,Abs((Menu\TitleCardTimer/secs#))*2+0.4), GAME_WINDOW_SCALE#*Min#(2.0,Abs((Menu\TitleCardTimer/secs#))*2+0.4))
	End Select
	DrawImageEx(INTERFACE(Interface_Spinner1), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetAlpha(1.0)
	
	SetAlpha(Abs(1-Menu\TitleCardTimer/secs#))
	SetScale(GAME_WINDOW_SCALE#*Min#(1.0,Abs(1-(Menu\TitleCardTimer/secs#))+0.4), GAME_WINDOW_SCALE#*Min#(1.0,Abs(1-(Menu\TitleCardTimer/secs#))+0.4))
	SetColor(Menu_ReturnCardColor(1,Menu\Character[1],False,True),Menu_ReturnCardColor(2,Menu\Character[1],False,True),Menu_ReturnCardColor(3,Menu\Character[1],False,True))
	DrawImageEx(INTERFACE(Interface_Spinner1), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetColor(Menu_ReturnCardColor(1,Menu\Character[2],False,True),Menu_ReturnCardColor(2,Menu\Character[2],False,True),Menu_ReturnCardColor(3,Menu\Character[2],False,True))
	DrawImageEx(INTERFACE(Interface_Spinner2), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	SetColor(Menu_ReturnCardColor(1,Menu\Character[3],False,True),Menu_ReturnCardColor(2,Menu\Character[3],False,True),Menu_ReturnCardColor(3,Menu\Character[3],False,True))
	DrawImageEx(INTERFACE(Interface_Spinner3), GAME_WINDOW_W/2+80*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-40*GAME_WINDOW_SCALE#)
	
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetAlpha(1.0)
	
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

Function DrawTitleCardStuff(inmenu=False)
	
	If Menu\ChaoGarden=0 Then
		If FileType(Menu\WarpRingPath$+"/Media/backgroundm"+Menu\MissionNo+".png")=1 Then
			LoadSmartFastImage(Menu\WarpRingPath$+"/Media/backgroundm"+Menu\MissionNo+".png", Interface_BackgroundLoading, 1440, 900, 0, 1, 0, 0, True, False, True)
		ElseIf FileType(StagePath$(Menu\SelectedStage)+"/Media/backgroundm"+Menu\MissionNo+".png")=1
			LoadSmartFastImage(StagePath$(Menu\SelectedStage)+"/Media/backgroundm"+Menu\MissionNo+".png", Interface_BackgroundLoading, 1440, 900, 0, 1, 0, 0, True, False, True)
		Else
			LoadSmartFastImage(StagePath$(Menu\SelectedStage)+"/Media/background.png", Interface_BackgroundLoading, 1440, 900, 0, 1, 0, 0, True, False, True)
		EndIf
	EndIf
	
	SetAlpha(Abs(1-Menu\TitleCardTimer/secs#)*0.5)
	
	If Menu\CollectionRoom=0 And Menu\TutorialMode=0 And Menu\ChaoGarden=0 And Menu\MissionNo>0 Then DrawImageEx(INTERFACE(Interface_BackgroundLoading), GAME_WINDOW_W/2, GAME_WINDOW_H/2)
	
	
	
	
	If Menu\ThemeHeroesSpinner=1 And Menu\Members=3 Then
		DrawHeroesSpinner()
	Else
		DrawSpinner()
	EndIf
	
	Select Menu\ChaoGarden
		Case 0:
			
			SetColor(Menu_ReturnCardColor(1,Menu\Character[1]),Menu_ReturnCardColor(2,Menu\Character[1]),Menu_ReturnCardColor(3,Menu\Character[1]))
			For x=-15 To 15 : DrawImageEx(INTERFACE(Interface_Card2), (80-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+x*24*GAME_WINDOW_SCALE#) : Next
			
		Case 1:
			SetColor(35,241,253)
			For x=-15 To 15 : DrawImageEx(INTERFACE(Interface_Card3), (80-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+x*24*GAME_WINDOW_SCALE#) : Next
	End Select
	SetColor(255,255,255)
	
	Select inmenu
		Case False: Menu_UpdateStageNames(Menu\Stage)
		Case True: Menu_UpdateStageNames(Menu\SelectedStage)
	End Select
	
	
	Select Menu\ChaoGarden
		Case 0:
			If Menu\MarathonMode=1 Then
				DrawRealText("Stage "+Menu\MarathonStage+" of "+StageAmount, (80-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#-63*GAME_WINDOW_SCALE#, 40*GAME_WINDOW_SCALE#, (Interface_Text_3))
				
			EndIf
			
			If Menu\CollectionRoom=1 Then
				DrawRealText("The Island", GAME_WINDOW_W/2, GAME_WINDOW_H/2-(50+200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
			Else
				DrawRealText(Menu\StageName$, GAME_WINDOW_W/2, GAME_WINDOW_H/2-(50+200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_TextTitle_1), 1, 0, 63, 63, 63)
			EndIf
		Case 1:
			DrawRealText(Menu\StageName$, GAME_WINDOW_W/2, GAME_WINDOW_H/2-(50+200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_TextTitleChao_1), 1, 0, 63, 63, 63)
	End Select
	
	If Menu\ChaoGarden=0 And Menu\CollectionRoom=0 And Menu\TutorialMode=0 Then 
		DrawRealText(Menu\LoadingTip$, 10*GAME_WINDOW_SCALE#, (25-200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_Text_2))
		
		If (Not(Menu\StageAuthor$="")) Then DrawRealText("By "+Menu\StageAuthor$, GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(200*Menu\TitleCardTimer/secs#-5)*GAME_WINDOW_SCALE#, (Interface_Text_2))
		If (Not(Menu\MissionTag$="")) Then j$=": " + Menu\MissionTag$ Else j$=""
		
		If Menu\MissionCard=1 Then c$="Challenge Act" Else c$="Act "+Str(Menu\MissionNo)
		DrawRealText(c$+j$, GAME_WINDOW_W/2, GAME_WINDOW_H/2-(25+200*Menu\TitleCardTimer/secs#)*GAME_WINDOW_SCALE#, (Interface_Text_2))
		DrawRealText(Menu\MissionInfo$, GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(37.5+200*Menu\TitleCardTimer/secs#+15)*GAME_WINDOW_SCALE#, (Interface_Text_2))
		Menu_SetLoadingTip()
	EndIf
	
	Menu_UpdateMissionInfo()
	
	
	
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Global TOTAL_FILESIZE
Global TRIANGLES
Function ARGB(Alpha#, Red, Green, Blue)
	Return (Int(Alpha*255) Shl 24) Or (Red Shl 16)  Or (Green Shl 8)  Or Blue	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D