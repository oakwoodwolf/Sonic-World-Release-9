	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Interface_Render_Result_GetRank_Score(p.tPlayer)
		If Game\ScoreTotal>=Game\IdealScore*0.950 Then
			Return 1
		ElseIf Game\ScoreTotal>=Game\IdealScore*0.783 Then
			Return 2
		ElseIf Game\ScoreTotal>=Game\IdealScore*0.617 Then
			Return 3
		ElseIf Game\ScoreTotal>=Game\IdealScore*0.450 Then
			Return 4
		ElseIf Game\ScoreTotal>=Game\IdealScore*0.283 Then
			Return 5
		ElseIf Game\ScoreTotal>=Game\IdealScore*0.117 Then
			Return 6
		ElseIf Game\ScoreTotal>=Game\IdealScore*0.000 Then
			Return 7
		Else
			Return 7
		EndIf
	End Function

	Function Interface_Render_Result_GetRank_Time(p.tPlayer)
		If Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.000 Then
			Return 1
		ElseIf Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.117 Then
			Return 2
		ElseIf Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.283 Then
			Return 3
		ElseIf Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.450 Then
			Return 4
		ElseIf Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.617 Then
			Return 5
		ElseIf Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.783 Then
			Return 6
		ElseIf Game\ResultTime<=Game\IdealTime+Game\IdealTime*0.950 Then
			Return 7
		Else
			Return 7
		EndIf
	End Function

	Function Interface_Render_Result_GetRank(p.tPlayer)
		Game\Rank = Floor(0.3*Interface_Render_Result_GetRank_Score(p)+0.7*Interface_Render_Result_GetRank_Time(p))
	End Function

	Function Interface_Render_Result_Exit(p.tPlayer)
		Interface_Render_Result_Save(p)
		Game\Victory=3
		If Game\VictoryEndType=0 Then
			If Menu\HubStage>0 Then
				Menu\SelectedStage=Menu\HubStage
				Menu\HubStage=0
				Game_Stage_Quit(2)
			Else
				Game_Stage_Quit(4)
			EndIf
		Else
			Menu\SelectedStage=Game\VictoryEndStage
			If Menu\HubStage>0 Then Menu\HubStage=0
			Game_Stage_Quit(2)
		EndIf
	End Function

	Function Interface_Render_Result_Save(p.tPlayer)
		If Menu\ChaoGarden=0 Then
			LoadGame_Records(Menu\Stage)

			If Game\Cheater=0 Then;!!!
				If Game\ResultRings>RECORDS_RINGS(Menu\MissionNo,0) Then
					RECORDS_RINGS(Menu\MissionNo,0)=Game\ResultRings
					RECORDS_RINGS(Menu\MissionNo,1)=Menu\Character[1]
					RECORDS_RINGS(Menu\MissionNo,2)=Menu\Character[2]
					RECORDS_RINGS(Menu\MissionNo,3)=Menu\Character[3]
					RECORDS_RINGS(Menu\MissionNo,4)=Menu\Members
				EndIf
				If Game\ResultEnemies>RECORDS_ENEMIES(Menu\MissionNo,0) Then
					RECORDS_ENEMIES(Menu\MissionNo,0)=Game\ResultEnemies
					RECORDS_ENEMIES(Menu\MissionNo,1)=Menu\Character[1]
					RECORDS_ENEMIES(Menu\MissionNo,2)=Menu\Character[2]
					RECORDS_ENEMIES(Menu\MissionNo,3)=Menu\Character[3]
					RECORDS_ENEMIES(Menu\MissionNo,4)=Menu\Members
				EndIf
				If Game\ResultTime<RECORDS_TIME(Menu\MissionNo,0) Then
					RECORDS_TIME(Menu\MissionNo,0)=Game\ResultTime
					RECORDS_TIME(Menu\MissionNo,1)=Menu\Character[1]
					RECORDS_TIME(Menu\MissionNo,2)=Menu\Character[2]
					RECORDS_TIME(Menu\MissionNo,3)=Menu\Character[3]
					RECORDS_TIME(Menu\MissionNo,4)=Menu\Members
				EndIf
				If Game\ScoreTotal>RECORDS_SCORE(Menu\MissionNo,0) Then
					RECORDS_SCORE(Menu\MissionNo,0)=Game\ScoreTotal
					RECORDS_SCORE(Menu\MissionNo,1)=Menu\Character[1]
					RECORDS_SCORE(Menu\MissionNo,2)=Menu\Character[2]
					RECORDS_SCORE(Menu\MissionNo,3)=Menu\Character[3]
					RECORDS_SCORE(Menu\MissionNo,4)=Menu\Members
				EndIf
				If Game\Rank<RECORDS_RANK(Menu\MissionNo,0) Then
					RECORDS_RANK(Menu\MissionNo,0)=Game\Rank
					RECORDS_RANK(Menu\MissionNo,1)=Menu\Character[1]
					RECORDS_RANK(Menu\MissionNo,2)=Menu\Character[2]
					RECORDS_RANK(Menu\MissionNo,3)=Menu\Character[3]
					RECORDS_RANK(Menu\MissionNo,4)=Menu\Members
				EndIf
				If Menu\BuddyChaoNumber>0 Then SaveGame_Chao(p\Objects\FollowerChao) : 	Game\Interface\AutoSaveShowTimer=0.5*secs#
				SaveGame_Records(Menu\Stage)
				
				Menu\EmblemsGot=0
				If Menu\Stage>0 Then
					If EMBLEMS1(Menu\MissionNo,Menu\Stage)=0 Then EMBLEMS1(Menu\MissionNo,Menu\Stage)=1 : Menu\EmblemsGot=Menu\EmblemsGot+1
					If Game\Rank=1 And EMBLEMS2(Menu\MissionNo,Menu\Stage)=0 Then EMBLEMS2(Menu\MissionNo,Menu\Stage)=1 : Menu\EmblemsGot=Menu\EmblemsGot+1
				ElseIf Menu\Stage<0 Then
					If UNLOCKEDEMERALDS[abs(Menu\Stage)]=0 Then Menu\EmblemsGot=Menu\Stage
				EndIf

				SaveGame_Emblems(Menu\Stage)
			Else;!!!
				Menu\EmblemsGot=0
			EndIf;!!!
		EndIf

		SaveGame(False)
	End Function

	Function Interface_Render_Result_ExitChaoRace(p.tPlayer)
		SaveGame(False)
		Game\Victory=3
		Game_Stage_Quit(9)
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Interface_Render_Result(p.tPlayer,d.tDeltatime,chaorace=false)

		If Game\Interface\ResultOrder=-1 Then
			Game\Interface\ResultTimer=0
			Game\Interface\ResultOrder=0
			Game\Interface\ResultOrder2=0
			For i=0 to 4 : Game\Interface\ResultBoxAlpha#[i]=0 : Next
			Game\Interface\ResultTitlePosition#=0
		EndIf
		If Game\Interface\AutoSaveShowTimer>0 Then
					DrawImageEx(INTERFACE(Interface_Saving), 18*GAME_WINDOW_SCALE#, 18*GAME_WINDOW_SCALE#)
					DrawRealText("Auto-saving...", 32*GAME_WINDOW_SCALE#, 22*GAME_WINDOW_SCALE#, (Interface_Text_2))
					Game\Interface\AutoSaveShowTimer=Game\Interface\AutoSaveShowTimer-timervalue#
		EndIf
		If Game\Interface\ResultTimer>0 Then Game\Interface\ResultTimer=Game\Interface\ResultTimer-timervalue#

		If Input\Pressed\Start Or Input\Pressed\ActionJump Then Game\Interface\ResultOrder2=-1

		If chaorace=false Then
			Interface_Render_Result_Stage(p,d)
		Else
			Interface_Render_Result_ChaoRace(p,d)
		EndIf

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Interface_Render_Result_Stage(p.tPlayer,d.tDeltatime)

		If Game\Interface\ResultOrder<7 Then ;!

		; MISSION CLEAR
		If Game\Interface\ResultOrder>=0 Then
			If Game\Interface\ResultOrder=0 Then
				If (Not(ChannelPlaying(Game\Channel_MissionCompleted))) Then
					Select Game\Interface\ResultOrder2
						Case 0,1,2:
							SetAlpha(Game\Interface\ResultBoxAlpha#[0])
							DrawRealText("MISSION CLEAR!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, (Interface_TextRecords_1), 1)
							SetAlpha(1)
						Default:
							SetAlpha(Game\Interface\ResultBoxAlpha#[0])
							DrawRealText("MISSION CLEAR!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, (Interface_TextRecords_1), 1)
							SetAlpha(1-Game\Interface\ResultBoxAlpha#[0])
							DrawRealText("RESULT", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, (Interface_TextRecords_1), 1)
							SetAlpha(1)
					End Select
				EndIf
			Else
				DrawRealText("RESULT", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, (Interface_TextRecords_1), 1)
			EndIf
		EndIf

		; BOX1
		SetAlpha(Game\Interface\ResultBoxAlpha#[1])
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2-85.0*GAME_WINDOW_SCALE#, 0)
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2-40.5*GAME_WINDOW_SCALE#, 1)
		SetAlpha(1)
			If Game\Interface\ResultOrder>=1 Then
				If Game\Interface\ResultOrder>1 Or Game\Interface\ResultOrder2>=3 Then
					DrawRealText("RINGS", GAME_WINDOW_W/2-161*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-86.5*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
					DrawBetterNumber(Game\ResultRings, GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-86.5*GAME_WINDOW_SCALE#, 0, 1)
				EndIf
				If Game\Interface\ResultOrder>1 Or Game\Interface\ResultOrder2>=4 Then
					DrawRealText("ENEMIES", GAME_WINDOW_W/2-161*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-63*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
					DrawBetterNumber(Game\ResultEnemies, GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-63*GAME_WINDOW_SCALE#, 0, 1)
				EndIf
				If Game\Interface\ResultOrder>1 Or Game\Interface\ResultOrder2>=5 Then
					DrawRealText("TIME", GAME_WINDOW_W/2-161*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
					DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+64*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#, 10)
					DrawImageEx(INTERFACE(Interface_Numbers), GAME_WINDOW_W/2+114*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#, 10)
					DrawNumber((Game\ResultTime/60000), GAME_WINDOW_W/2+28*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#, 2)
					DrawNumber((Game\ResultTime/1000) Mod 60, GAME_WINDOW_W/2+78*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#, 2)
					DrawNumber((Game\ResultTime/10) Mod 60, GAME_WINDOW_W/2+128*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-39.5*GAME_WINDOW_SCALE#, 2)
				EndIf
			EndIf

		; BOX2
		SetAlpha(Game\Interface\ResultBoxAlpha#[2])
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2+6.5*GAME_WINDOW_SCALE#, 2)
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2+51.0*GAME_WINDOW_SCALE#, 3)
		SetAlpha(1)
			If Game\Interface\ResultOrder>=2 Then
				If Game\Interface\ResultOrder>2 Or Game\Interface\ResultOrder2>=3 Then
					DrawRealText("PERFECT", GAME_WINDOW_W/2-161*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
					DrawBetterNumber(Game\ResultPerfectBonus, GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+5*GAME_WINDOW_SCALE#, 0, 1)
				EndIf
				If Game\Interface\ResultOrder>2 Or Game\Interface\ResultOrder2>=4 Then
					DrawRealText("BONUS SCORE", GAME_WINDOW_W/2-161*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+29.5*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
					DrawBetterNumber(Game\ScoreBonus, GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+29.5*GAME_WINDOW_SCALE#, 0, 1)
				EndIf
				If Game\Interface\ResultOrder>2 Or Game\Interface\ResultOrder2>=5 Then
					DrawRealText("TOTAL SCORE", GAME_WINDOW_W/2-161*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+54*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
					DrawBetterNumber(Game\ScoreTotal, GAME_WINDOW_W/2+151*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+54*GAME_WINDOW_SCALE#, 0, 1)
				EndIf
			EndIf

		; RANKTAG
		SetAlpha(Game\Interface\ResultBoxAlpha#[3])
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2+103.0*GAME_WINDOW_SCALE#, 5)
		SetAlpha(1)
			If Game\Interface\ResultOrder>=4 Then
				If Game\Interface\ResultOrder>4 Or Game\Interface\ResultOrder2>=2 Then
					DrawRealText("RANK", GAME_WINDOW_W/2-82*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+103.5*GAME_WINDOW_SCALE#, (Interface_TextControls_2))
				EndIf
				If Game\Interface\ResultOrder>4 Or Game\Interface\ResultOrder2>1 Then
					If Game\Rank>0 Then Rank_Draw(Game\Rank, GAME_WINDOW_W/2+36.295*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+103.0*GAME_WINDOW_SCALE#)
				EndIf
			EndIf

		ElseIf Game\Interface\ResultOrder=7 Then ;!

		; WALLET
		SetAlpha(Game\Interface\ResultBoxAlpha#[4])
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2, 6)
		SetAlpha(1)
			If Game\Interface\ResultOrder>=7 Then
				If Game\Interface\ResultOrder>7 Or Game\Interface\ResultOrder2>=2 Then
					DrawBetterNumber(Menu\Wallet, GAME_WINDOW_W/2+60*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-2.5*GAME_WINDOW_SCALE#, 0, 1)
					DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-60*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-2.5*GAME_WINDOW_SCALE#, 2)
				EndIf
			EndIf

		EndIf ;!


		; calculations
		Select Game\Interface\ResultOrder
			Case 0: ; MISSION CLEAR
				If (Not(ChannelPlaying(Game\Channel_MissionCompleted))) Or Game\Interface\ResultOrder2=-1 Then
					Select Game\Interface\ResultOrder2
						Case 0:
							Game\Interface\ResultTitlePosition#=0
							Game\Interface\ResultBoxAlpha#[0]=0
							Game\Interface\ResultOrder2=1
						Case 1:
							If Game\Interface\ResultBoxAlpha#[0]<1 Then
								Game\Interface\ResultBoxAlpha#[0]=Game\Interface\ResultBoxAlpha#[0]+0.1*d\Delta
							Else
								Game\Interface\ResultBoxAlpha#[0]=1
								Game\Interface\ResultOrder2=2
								Game\Interface\ResultTimer=0.345*secs#
							EndIf
						Case 2:
							If Not(Game\Interface\ResultTimer>0) Then
								If Game\Interface\ResultTitlePosition#<116.5 Then
									Game\Interface\ResultTitlePosition#=Game\Interface\ResultTitlePosition#+2.5*d\Delta
								Else
									Game\Interface\ResultTitlePosition#=116.5
									Game\Interface\ResultOrder2=3
								EndIf
							EndIf
						Case 3:
							If Game\Interface\ResultBoxAlpha#[0]>0 Then
								Game\Interface\ResultBoxAlpha#[0]=Game\Interface\ResultBoxAlpha#[0]-0.1*d\Delta
							Else
								Game\Interface\ResultBoxAlpha#[0]=0
								Game\Interface\ResultOrder2=4
							EndIf
						Case 4:
							Game\Interface\ResultOrder=1
							Game\Interface\ResultOrder2=0
						Case -1:
							Game\Interface\ResultBoxAlpha#[0]=0
							Game\Interface\ResultTitlePosition#=116.5
							Game\Interface\ResultOrder=1
							Game\Interface\ResultOrder2=0
					End Select
				EndIf
			Case 1,2: ; BOX1,BOX2
				Select Game\Interface\ResultOrder2
					Case 0:
						Game\Interface\ResultBoxAlpha#[Game\Interface\ResultOrder]=0
						Game\Interface\ResultOrder2=1
					Case 1:
						If Game\Interface\ResultBoxAlpha#[Game\Interface\ResultOrder]<1 Then
							Game\Interface\ResultBoxAlpha#[Game\Interface\ResultOrder]=Game\Interface\ResultBoxAlpha#[Game\Interface\ResultOrder]+0.05*d\Delta
						Else
							Game\Interface\ResultBoxAlpha#[Game\Interface\ResultOrder]=1
							Game\Interface\ResultOrder2=2
							If Game\Interface\ResultOrder2=1 Then Game\Interface\ResultTimer=0.325*secs# Else Game\Interface\ResultTimer=0.375*secs#
						EndIf
					Case 2,3,4:
						If Not(Game\Interface\ResultTimer>0) Then
							Game\Interface\ResultOrder2=Game\Interface\ResultOrder2+1
							Game\Interface\ResultTimer=0.65*secs#
							PlaySmartSound(Sound_RankScore)
						EndIf
					Case 5:
						Game\Interface\ResultOrder=Game\Interface\ResultOrder+1
						Game\Interface\ResultOrder2=0
					Case -1:
						Game\Interface\ResultBoxAlpha#[Game\Interface\ResultOrder]=1
						Game\Interface\ResultTimer=0.85*secs#
						PlaySmartSound(Sound_RankScore)
						Game\Interface\ResultOrder=Game\Interface\ResultOrder+1
						Game\Interface\ResultOrder2=0
				End Select
			Case 3: ; TOTAL SCORE CALCULATION
				Select Game\Interface\ResultOrder2
					Case 0:
						Game\Interface\ResultOrder2=1
						Game\Interface\ResultTimer=1*secs#
					Case 1:
						If Not(Game\Interface\ResultTimer>0) Then Game\Interface\ResultOrder2=2
					Case 2:
						If Game\ScoreBonus>0 Or Game\ResultPerfectBonus>0 Then
							Game\Interface\ResultTimer=0.01*secs#
							If Not(ChannelPlaying(Game\Channel_ResultCount)) Then Game\Channel_ResultCount=PlaySmartSound(Sound_ResultCounting)
							If Game\ResultPerfectBonus>10 Then
								Game\ResultPerfectBonus=Game\ResultPerfectBonus-10
								Game\ScoreTotal=Game\ScoreTotal+10
							ElseIf Game\ResultPerfectBonus>0 Then
								Game\ResultPerfectBonus=Game\ResultPerfectBonus-1
								Game\ScoreTotal=Game\ScoreTotal+1
							EndIf
							If Game\ScoreBonus>10 Then
								Game\ScoreBonus=Game\ScoreBonus-10
								Game\ScoreTotal=Game\ScoreTotal+10
							ElseIf Game\ScoreBonus>0 Then
								Game\ScoreBonus=Game\ScoreBonus-1
								Game\ScoreTotal=Game\ScoreTotal+1
							EndIf
						Else
							Game\ResultPerfectBonus=0
							Game\ScoreBonus=0
							StopChannel(Game\Channel_ResultCount)
							Game\Channel_ResultCount=PlaySmartSound(Sound_ResultCount)
							Game\Interface\ResultTimer=2*secs#
							Game\Interface\ResultOrder=4
							Game\Interface\ResultOrder2=0
						EndIf
					Case -1:
						Game\ScoreTotal=Game\ScoreTotal+Game\ScoreBonus+Game\ResultPerfectBonus
						Game\ScoreBonus=0
						Game\ResultPerfectBonus=0
						StopChannel(Game\Channel_ResultCount)
						Game\Channel_ResultCount=PlaySmartSound(Sound_ResultCount)
						Game\Interface\ResultTimer=2*secs#
						Game\Interface\ResultOrder=4
						Game\Interface\ResultOrder2=0
				End Select
			Case 4: ; RANKTAG
				Select Game\Interface\ResultOrder2
					Case 0:
						Game\Interface\ResultBoxAlpha#[3]=0
						Game\Interface\ResultOrder2=1
					Case 1:
						If Not(Game\Interface\ResultTimer>0) Then
							If Game\Interface\ResultBoxAlpha#[3]<1 Then
								Game\Interface\ResultBoxAlpha#[3]=Game\Interface\ResultBoxAlpha#[3]+0.05*d\Delta
							Else
								Game\Interface\ResultBoxAlpha#[3]=1
								Game\Interface\ResultOrder2=2
								Game\Interface\ResultTimer=0.5*secs#
							EndIf
						EndIf
					Case 2:
						If Not(Game\Interface\ResultTimer>0) Then
							Game\Interface\ResultOrder2=3
							Game\Interface\ResultTimer=1.75*secs#
							Interface_Render_Result_GetRank(p)
							If Game\Rank=1 Then PlaySmartSound(Sound_RankS) Else PlaySmartSound(Sound_Rank)
						EndIf
					Case 3:
						If Not(Game\Interface\ResultTimer>0) Then
							Player_PlayRankVoice(p,Game\Rank)
							Game\Interface\ResultTimer=2.25*secs#
							Game\Interface\ResultOrder=5
							Game\Interface\ResultOrder2=0
						EndIf
					Case -1:
						Game\Interface\ResultBoxAlpha#[3]=1
						If Game\Rank=0 Then
							Interface_Render_Result_GetRank(p)
							If Game\Rank=1 Then PlaySmartSound(Sound_RankS) Else PlaySmartSound(Sound_Rank)
						EndIf
						Game\Interface\ResultTimer=2.25*secs#
						Game\Interface\ResultOrder=5
						Game\Interface\ResultOrder2=0
				End Select
			Case 5: ; RESULT MUSIC
				Select Game\Interface\ResultOrder2
					Case 0,-1:
						If (Not(Game\Interface\ResultTimer>0)) Or Game\Interface\ResultOrder2=-1 Then
							If Game\Rank=1 Then
								Game\Channel_Result=PlaySmartSound(Sound_ResultS)
							Else
								Game\Channel_Result=PlaySmartSound(Sound_Result)
							EndIf
							ChannelVolume(Game\Channel_Result,Menu\Settings\VolumeM#*Menu\Settings\Volume#)
							Game\Interface\ResultOrder=6
							Game\Interface\ResultOrder2=0
						EndIf
				End Select
			Case 6: ; WAIT UP
				If Game\Interface\ResultOrder2=-1 Or (Not(ChannelPlaying(Game\Channel_Result))) Then
					If Game\ResultRingsForBank>0 and Game\Cheater=0 Then
						Game\Interface\ResultOrder=7
					Else
						Game\Interface\ResultTimer=1.5*secs#
						Game\Interface\ResultOrder=8
					EndIf
					Game\Interface\ResultOrder2=0
				EndIf
			Case 7: ; WALLET
				Select Game\Interface\ResultOrder2
					Case 0:
						Game\Interface\ResultBoxAlpha#[4]=0
						Game\Interface\ResultOrder2=1
					Case 1:
						If Game\Interface\ResultBoxAlpha#[4]<1 Then
							Game\Interface\ResultBoxAlpha#[4]=Game\Interface\ResultBoxAlpha#[4]+0.1*d\Delta
						Else
							Game\Interface\ResultBoxAlpha#[4]=1
							Game\Interface\ResultOrder2=2
							Game\Interface\ResultTimer=0.325*secs#
						EndIf
					Case 2:
						If Not(Game\Interface\ResultTimer>0) Then
							Game\Interface\ResultOrder2=3
						EndIf
					Case 3:
						If Game\ResultRingsForBank>0 and Menu\Wallet<99999 Then
							Game\ResultRingsForBank=Game\ResultRingsForBank-1
							Menu\Wallet=Menu\Wallet+1
						Else
							Game\ResultRingsForBank=0
							Game\Interface\ResultTimer=1.25*secs#
							Game\Interface\ResultOrder2=4
						EndIf
					Case 4:
						If Not(Game\Interface\ResultTimer>0) Then
							Game\Interface\ResultTimer=1.5*secs#
							Game\Interface\ResultOrder=8
							Game\Interface\ResultOrder2=0
						EndIf
					Case -1:
						Game\Interface\ResultBoxAlpha#[4]=1
						Menu\Wallet=Menu\Wallet+Game\ResultRingsForBank
						Game\ResultRingsForBank=0
						Game\Interface\ResultTimer=1.5*secs#
						Game\Interface\ResultOrder=8
						Game\Interface\ResultOrder2=0
				End Select
			Case 8: ; EXIT
				If Not(Game\Interface\ResultTimer>0) Then
					Game\Interface\ResultOrder=9
					Interface_Render_Result_Exit(p)
				EndIf
		End Select

		If Game\Cheater=1 and Menu\ChaoGarden=0 Then
			DrawRealText("YOU CHEATED", GAME_WINDOW_W/2, GAME_WINDOW_H/2-150*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1)
			DrawRealText("YOUR RECORDS WILL NOT BE SAVED", GAME_WINDOW_W/2, GAME_WINDOW_H/2+150*GAME_WINDOW_SCALE#, (Interface_TextControls_2), 1)
		EndIf

		If Menu\Settings\ControlTips#=1 Then
			Select Game\Interface\ResultOrder
				Case 0,1,2,3,4,5,7: Interface_ControlTipDraw_Button("Skip", INPUT_BUTTON_START, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#)
				Case 6: Interface_ControlTipDraw_Button("Continue", INPUT_BUTTON_START, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#)
			End Select
		EndIf

	End Function


	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Interface_Render_Result_ChaoRace(p.tPlayer,d.tDeltatime)

		If Game\Interface\ResultOrder<7 Then ;!

		; MISSION CLEAR
		If Game\Interface\ResultOrder>=0 Then
			If Game\Interface\ResultOrder=0 Then
				If (Not(ChannelPlaying(Game\Channel_MissionCompleted))) Then
					Select Game\Interface\ResultOrder2
						Case 0,1,2:
							SetAlpha(Game\Interface\ResultBoxAlpha#[0])
							Select Menu\Stage
							Case 998:
								If Game\Interface\YourWinningChao>3 Then
									DrawRealText("BETTER LUCK NEXT TIME!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								Else
									DrawRealText("CONGRATULATIONS!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								EndIf
							Case 997:
								If Game\Interface\YourWinningChao=0 Then
									DrawRealText("BETTER LUCK NEXT TIME!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								Else
									DrawRealText("CONGRATULATIONS!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								EndIf
							End Select
							SetAlpha(1)
						Default:
							SetAlpha(Game\Interface\ResultBoxAlpha#[0])
							Select Menu\Stage
							Case 998:
								If Game\Interface\YourWinningChao>3 Then
									DrawRealText("BETTER LUCK NEXT TIME!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								Else
									DrawRealText("CONGRATULATIONS!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								EndIf
								SetAlpha(1-Game\Interface\ResultBoxAlpha#[0])
								Select Game\Interface\YourWinningChao
								Case 1: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"ST PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								Case 2: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"ND PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								Case 3: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"RD PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								Case -1: DrawRealText("YOU COULD NOT FINISH!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								Default: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"TH PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								End Select
							Case 997:
								If Game\Interface\YourWinningChao=0 Then
									DrawRealText("BETTER LUCK NEXT TIME!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								Else
									DrawRealText("CONGRATULATIONS!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								EndIf
								SetAlpha(1-Game\Interface\ResultBoxAlpha#[0])
								Select Game\Interface\YourWinningChao
								Case 1: DrawRealText("YOU WIN!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
								Case 0: DrawRealText("YOU LOSE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
								End Select
							End Select
							SetAlpha(1)
					End Select
				EndIf
			Else
				Select Menu\Stage
				Case 998:
					Select Game\Interface\YourWinningChao
					Case 1: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"ST PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
					Case 2: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"ND PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
					Case 3: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"RD PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
					Case -1: DrawRealText("YOU COULD NOT FINISH!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
					Default: DrawRealText("YOU GOT "+Game\Interface\YourWinningChao+"TH PLACE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
					End Select
				Case 997:
					Select Game\Interface\YourWinningChao
					Case 1: DrawRealText("YOU WIN!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_1), 1)
					Case 0: DrawRealText("YOU LOSE!", GAME_WINDOW_W/2, GAME_WINDOW_H/2-Game\Interface\ResultTitlePosition#*GAME_WINDOW_SCALE#, SmartImage(Interface_TextRecords_2), 1)
					End Select
				End Select
			EndIf
		EndIf

		ElseIf Game\Interface\ResultOrder=7 Then ;!

		; WALLET
		SetAlpha(Game\Interface\ResultBoxAlpha#[4])
		DrawImageEx(INTERFACE(Interface_Results), GAME_WINDOW_W/2, GAME_WINDOW_H/2, 6)
		SetAlpha(1)
			If Game\Interface\ResultOrder>=7 Then
				If Game\Interface\ResultOrder>7 Or Game\Interface\ResultOrder2>=2 Then
					DrawBetterNumber(Menu\Wallet, GAME_WINDOW_W/2+60*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-2.5*GAME_WINDOW_SCALE#, 0, 1)
					DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-60*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-2.5*GAME_WINDOW_SCALE#, 2)
				EndIf
			EndIf

		EndIf ;!


		; calculations
		Select Game\Interface\ResultOrder
			Case 0: ; MISSION CLEAR
				If (Not(ChannelPlaying(Game\Channel_MissionCompleted))) Or Game\Interface\ResultOrder2=-1 Then
					Select Game\Interface\ResultOrder2
						Case 0:
							Game\Interface\ResultTitlePosition#=0
							Game\Interface\ResultBoxAlpha#[0]=0
							Game\Interface\ResultOrder2=1
						Case 1:
							If Game\Interface\ResultBoxAlpha#[0]<1 Then
								Game\Interface\ResultBoxAlpha#[0]=Game\Interface\ResultBoxAlpha#[0]+0.1*d\Delta
							Else
								Game\Interface\ResultBoxAlpha#[0]=1
								Game\Interface\ResultOrder2=2
								Game\Interface\ResultTimer=1.125*secs#
							EndIf
						Case 2:
							If Not(Game\Interface\ResultTimer>0) Then
								If Game\Interface\ResultTitlePosition#<116.5 Then
									Game\Interface\ResultTitlePosition#=Game\Interface\ResultTitlePosition#+1.25*d\Delta
								Else
									Game\Interface\ResultTitlePosition#=116.5
									Game\Interface\ResultOrder2=3
								EndIf
							EndIf
						Case 3:
							If Game\Interface\ResultBoxAlpha#[0]>0 Then
								Game\Interface\ResultBoxAlpha#[0]=Game\Interface\ResultBoxAlpha#[0]-0.1*d\Delta
							Else
								Game\Interface\ResultBoxAlpha#[0]=0
								Game\Interface\ResultOrder2=4
							EndIf
						Case 4:
							Game\Interface\ResultTimer=2.25*secs#
							Game\Interface\ResultOrder=5
							Game\Interface\ResultOrder2=0
						Case -1:
							Game\Interface\ResultBoxAlpha#[0]=0
							Game\Interface\ResultTitlePosition#=116.5
							Game\Interface\ResultTimer=2.25*secs#
							Game\Interface\ResultOrder=5
							Game\Interface\ResultOrder2=0
					End Select
				EndIf
			Case 5: ; RESULT MUSIC
				Select Game\Interface\ResultOrder2
					Case 0,-1:
						If (Not(Game\Interface\ResultTimer>0)) Or Game\Interface\ResultOrder2=-1 Then
							Game\Channel_Result=PlaySmartSound(Sound_ResultChao)
							ChannelVolume(Game\Channel_Result,Menu\Settings\VolumeM#*Menu\Settings\Volume#)
							Game\Interface\ResultOrder=6
							Game\Interface\ResultOrder2=0
						EndIf
				End Select
			Case 6: ; WAIT UP
				If Game\Interface\ResultOrder2=-1 Or (Not(ChannelPlaying(Game\Channel_Result))) Then
					If Game\ResultRingsForBank>0 Then
						Game\Interface\ResultOrder=7
					Else
						Game\Interface\ResultTimer=1.5*secs#
						Game\Interface\ResultOrder=8
					EndIf
					Game\Interface\ResultOrder2=0
				EndIf
			Case 7: ; WALLET
				Select Game\Interface\ResultOrder2
					Case 0:
						Game\Interface\ResultBoxAlpha#[4]=0
						Game\Interface\ResultOrder2=1
					Case 1:
						If Game\Interface\ResultBoxAlpha#[4]<1 Then
							Game\Interface\ResultBoxAlpha#[4]=Game\Interface\ResultBoxAlpha#[4]+0.1*d\Delta
						Else
							Game\Interface\ResultBoxAlpha#[4]=1
							Game\Interface\ResultOrder2=2
							Game\Interface\ResultTimer=0.325*secs#
						EndIf
					Case 2:
						If Not(Game\Interface\ResultTimer>0) Then
							Game\Interface\ResultOrder2=3
						EndIf
					Case 3:
						If Game\ResultRingsForBank>0 and Menu\Wallet<99999 Then
							Game\ResultRingsForBank=Game\ResultRingsForBank-1
							Menu\Wallet=Menu\Wallet+1
						Else
							Game\ResultRingsForBank=0
							Game\Interface\ResultTimer=1.25*secs#
							Game\Interface\ResultOrder2=4
						EndIf
					Case 4:
						If Not(Game\Interface\ResultTimer>0) Then
							Game\Interface\ResultTimer=1.5*secs#
							Game\Interface\ResultOrder=8
							Game\Interface\ResultOrder2=0
						EndIf
					Case -1:
						Game\Interface\ResultBoxAlpha#[4]=1
						Menu\Wallet=Menu\Wallet+Game\ResultRingsForBank
						Game\ResultRingsForBank=0
						Game\Interface\ResultTimer=1.5*secs#
						Game\Interface\ResultOrder=8
						Game\Interface\ResultOrder2=0
				End Select
			Case 8: ; EXIT
				If Not(Game\Interface\ResultTimer>0) Then
					Game\Interface\ResultOrder=9
					Interface_Render_Result_ExitChaoRace(p)
				EndIf
		End Select

		If Menu\Settings\ControlTips#=1 Then
			Select Game\Interface\ResultOrder
				Case 0,5,7: Interface_ControlTipDraw_Button("Skip", INPUT_BUTTON_START, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#)
				Case 6: Interface_ControlTipDraw_Button("Continue", INPUT_BUTTON_START, GAME_WINDOW_W-20*GAME_WINDOW_SCALE#, GAME_WINDOW_H-20*GAME_WINDOW_SCALE#)
			End Select
		EndIf

	End Function