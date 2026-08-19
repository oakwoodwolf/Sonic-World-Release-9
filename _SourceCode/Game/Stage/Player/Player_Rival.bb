Function Player_Rival(p.tPlayer, d.tDeltaTime)
	
	If (Not(p\Action=ACTION_RIVALDIE)) Then
		doattack=False
		doattack2=False
		dojumps=False
		If (Not(p\Rival\Running)) Then
			If EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)<200 Then
				doattack=True
				doattack2=True
				dojumps=True
				If Not(p\Rival\MoveTimer>0) Then
					If p\Rival\DontMoveTimer>0 Then
						Player_SetSpeed(p,0)
					Else
						p\Rival\MoveTimer=(1.5+Rand(0,5)/2.0)*secs#
						p\Rival\DontMoveTimer=(Rand(0,2)/2.0)*secs#
						p\Rival\Speed#=Rand(1,4)/2.0
					EndIf
				ElseIf p\Rival\MoveTimer>0 And (Not(p\Action=ACTION_HURT)) Then
					If (Not(p\Action=ACTION_CHARGE)) Then
						If (EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)>15 And p\Flags\Attacking=False) Or p\Action=ACTION_ROLL Then
							If p\Action=ACTION_ROLL Then
								Player_SetSpeed(p,p\Rival\Speed#)
							Else
								p\Animation\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,p\Objects\Entity))
								If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<50 Then
									Player_SetSpeed(p,p\Rival\Speed#*(EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)/50.0))
								Else
									Player_SetSpeed(p,p\Rival\Speed#)
								EndIf
							EndIf
						Else
							p\Animation\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,p\Objects\Entity))
							If Not(p\Flags\Attacking) Then Player_SetSpeed(p,0)
						EndIf
					ElseIf p\Action=ACTION_CHARGE Then
						p\Animation\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,p\Objects\Entity))
						Player_SetSpeed(p,0)
					EndIf
				EndIf
			EndIf
		Else
			p\Animation\Direction#=180
			If p\Objects\Position\z#<pp(1)\Objects\Position\z#-300 Or p\Objects\Position\z#>pp(1)\Objects\Position\z#+700 Or Abs(p\Objects\Position\y#-pp(1)\Objects\Position\y#)>700 Then
				PositionEntity p\Objects\Entity, 0, pp(1)\Objects\Position\y#+20, pp(1)\Objects\Position\z#+250, 1
				EmitSmartSound(Sound_Teleport,p\Objects\Entity)
			Else
				If p\Objects\Position\z#<pp(1)\Objects\Position\z#+300 Then
					If p\HurtTimer>0 Then
						Player_SetSpeed(p,6.5)
					Else
						If p\Objects\Position\z#>pp(1)\Objects\Position\z#-50 Then
							If pp(1)\SpeedLength#<2.5 Then
								Player_SetSpeed(p,2.5)
							Else
								If pp(1)\Flags\Attacking=False And pp(1)\Flags\InJumpAction=False And Game\SpeedShoes=0 Then
									Player_SetSpeed(p,pp(1)\SpeedLength#-0.25)
								Else
									Player_SetSpeed(p,pp(1)\SpeedLength#-0.5)
								EndIf
							EndIf
						Else
							If pp(1)\SpeedLength#<2.5 Then
								Player_SetSpeed(p,2.5/2.0)
							Else
								Player_SetSpeed(p,pp(1)\SpeedLength#/2.0)
							EndIf
						EndIf
					EndIf
				Else
					Player_SetSpeed(p,pp(1)\SpeedLength#/2.0)
				EndIf
				If pp(1)\SpeedLength#>0 Then
					If Not(p\Rival\MoveTimer)>0 Then
						p\Rival\MoveTimer=2*secs#
						Select(Rand(1,3))
							Case 1: p\Rival\MoveSide=-1
							Case 2: p\Rival\MoveSide=0
							Case 3: p\Rival\MoveSide=1
						End Select
					EndIf
					If p\SpeedLength#>0 Then
						If p\Objects\Position\x#<18*p\Rival\MoveSide Then MoveEntity p\Objects\Entity, 0.25*d\Delta, 0, 0
						If p\Objects\Position\x#>18*p\Rival\MoveSide Then MoveEntity p\Objects\Entity, -0.25*d\Delta, 0, 0
					EndIf
				EndIf
				If pp(1)\Flags\Attacking Then doattack=True
				If p\Objects\Position\z#<pp(1)\Objects\Position\z#+50 And p\Objects\Position\z#>pp(1)\Objects\Position\z#-200 Then doattack=True : doattack2=True
				If p\Objects\Position\z#<pp(1)\Objects\Position\z#+200 Then dojumps=True
			EndIf
		EndIf
		
		If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<5 Then
			If p\Flags\Attacking And (pp(1)\Flags\Attacking Or Game\Invinc=1) Then
				If pp(1)\Action=ACTION_STOMP Then rockP=True Else rockP=False
				If p\Action=ACTION_STOMP Then rockE=True Else rockE=False
				If pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_ROLL Or pp(1)\Action=ACTION_DRIFT Then paperP=True Else paperP=False
				If p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT Then paperE=True Else paperE=False
				If rockP=False And paperP=False And pp(1)\Flags\Attacking Then scissorsP=True Else scissorsP=False
				If rockE=False And paperE=False And p\Flags\Attacking Then scissorsE=True Else scissorsE=False
				If Game\Invinc=1 Then rockP=True : paperP=True : scissorsP=True
				
				If (rockP And scissorsE) Or (paperP And rockE) Or (scissorsP And paperE) Then
					Player_Hit(p)
				ElseIf (rockP And paperE) Or (paperP And scissorsE) Or (scissorsP And rockE) Then
					Player_Hit(pp(1))
				Else
					p\Motion\Ground=False : p\Action=ACTION_FALL : Player_SetSpeed(p,-1.75) : p\Motion\Speed\y#=0.4
					pp(1)\Motion\Ground=False : pp(1)\Action=ACTION_FALL : Player_SetSpeed(pp(1),-1.75) : pp(1)\Motion\Speed\y#=0.4
				EndIf
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
				ParticleTemplate_Call(pp(1)\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
			ElseIf p\Flags\Attacking=False And (pp(1)\Flags\Attacking And (Not(pp(1)\Action=ACTION_JUMP))) Then
				Player_Hit(p)
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
			ElseIf (p\Flags\Attacking And (Not(p\Action=ACTION_JUMP))) And pp(1)\Flags\Attacking=False Then
				Player_Hit(pp(1))
				ParticleTemplate_Call(pp(1)\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
			EndIf
		EndIf
		
		If doattack Or dojumps Then
			If Not(p\Rival\JustHadActionTimer>0) Then
				If dojumps Then
					If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP)) And p\Motion\Ground And ( (Not(p\Rival\MakeJumpTimer>0)) Or (pp(1)\Action=ACTION_JUMP And Rand(1,2)=1) ) Then
						Player_ActuallyJump(p,True)
					EndIf
					If p\Flags\InJumpAction=False And p\Motion\Ground=False And ( (Not(p\Rival\MakeJumpActionTimer>0)) Or (pp(1)\Flags\InJumpAction And Rand(1,3)=1) ) Then
						If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP)) And p\Motion\Ground Then Player_ActuallyJump(p,True)
						Select(Rand(1,2))
							Case 1: p\JumpActionMode=Abs(p\JumpActionMode-1)
						End Select
						Player_JumpActions(p,True)
						p\Rival\JustHadActionTimer=0.8*secs#
					EndIf
				EndIf
				
				If doattack Then
					If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<50 Then
						If p\Flags\Attacking=False And ( (Not(p\Rival\MakeChargeTimer>0)) Or ((pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_ROLL Or pp(1)\Action=ACTION_DRIFT) And Rand(1,3)=1) ) Then
							Player_Action_Roll_Initiate_Rival(p)
							p\Rival\JustHadActionTimer=0.8*secs#
						EndIf
						If p\Flags\Attacking=False And p\Motion\Ground=False And ( (Not(p\Rival\MakeStompTimer>0)) Or (pp(1)\Action=ACTION_STOMP And Rand(1,2)=1) ) Then
							Select(Rand(0,1))
								Case 0: Player_Action_Stomp_Initiate_Rival(p)
								Case 1: Player_Action_Stomp_Initiate_Rival(p,True)
							End Select
							p\Rival\JustHadActionTimer=0.8*secs#
						EndIf
						If doattack2 And p\Flags\Attacking=False And ( (Not(p\Rival\MakeAttackTimer>0)) Or ((pp(1)\Flags\Attacking Or pp(1)\Action=ACTION_THROW Or pp(1)\Action=ACTION_SHOOT Or pp(1)\Action=ACTION_SHOOTHOVER Or pp(1)\Action=ACTION_PSYCHO) And Rand(1,4)=1) ) Then
							Select(Rand(1,3))
								Case 1: Player_SkillActions(p,True)
								Case 2: Player_SkillActions2(p,True)
								Case 3: Player_SkillActions3(p,True)
							End Select
							p\Rival\JustHadActionTimer=0.8*secs#
						EndIf
					EndIf
				EndIf
			EndIf
			
			If Not(p\Rival\MakeJumpTimer>0) Then p\Rival\MakeJumpTimer=(Rand(1,20)/4.0)*secs#
			If Not(p\Rival\MakeJumpActionTimer>0) Then p\Rival\MakeJumpActionTimer=(Rand(1,16)/4.0)*secs#
			If Not(p\Rival\MakeAttackTimer>0) Then p\Rival\MakeAttackTimer=(Rand(1,8)/4.0)*secs#
			If Not(p\Rival\MakeChargeTimer>0) Then p\Rival\MakeChargeTimer=(Rand(1,16)/4.0)*secs#
			If Not(p\Rival\MakeStompTimer>0) Then p\Rival\MakeStompTimer=(Rand(1,32)/4.0)*secs#
			
			If p\Action = ACTION_JUMPDASH Or (p\Action = ACTION_DOUBLEJUMP And (p\Character=CHAR_AMY Or p\Character=CHAR_BLA Or p\Character=CHAR_EME)) Or p\Action = ACTION_DIVE Then
				Player_GetClosestObject(p,1)
			ElseIf p\Flags\Attacking Then
				Player_GetClosestObject(p,3)
			EndIf
		EndIf
		
		If p\Objects\Position\y#<Game\Stage\Properties\DeathLevel Then
			Player_SetPosition(p, p\Rival\InitialPositionX#, p\Rival\InitialPositionY#, p\Rival\InitialPositionZ#, p\Rival\InitialRotationY#)
			EmitSmartSound(Sound_Teleport,p\Objects\Entity)
			Player_ResetAllTimers(p)
			Player_Hit(p)
		EndIf
	Else
		Player_SetSpeed(p,0)
	EndIf
	
End Function

Function Player_ReturnChosenRival(char,try)
	Select char
		Case CHAR_SON:
			Select(try)
				Case 1: Return CHAR_SHA
				Case 2: Return CHAR_MET
				Case 3: Return CHAR_KNU
				Case 4: Return CHAR_BLA
			End Select
		Case CHAR_TAI:
			Select(try)
				Case 1: Return CHAR_CRE
				Case 2: Return CHAR_ROU
				Case 3: Return CHAR_SON
				Case 4: Return CHAR_KNU
			End Select
		Case CHAR_KNU:
			Select(try)
				Case 1: Return CHAR_ROU
				Case 2: Return CHAR_SON
				Case 3: Return CHAR_OME
				Case 4: Return CHAR_SHA
			End Select
		Case CHAR_AMY:
			Select(try)
				Case 1: Return CHAR_CRE
				Case 2: Return CHAR_BLA
				Case 3: Return CHAR_ROU
				Case 4: Return CHAR_ESP
			End Select
		Case CHAR_SHA:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_KNU
				Case 3: Return CHAR_INF
				Case 4: Return CHAR_ESP
			End Select
		Case CHAR_ROU:
			Select(try)
				Case 1: Return CHAR_KNU
				Case 2: Return CHAR_TAI
				Case 3: Return CHAR_CRE
				Case 4: Return CHAR_ESP
			End Select
		Case CHAR_CRE:
			Select(try)
				Case 1: Return CHAR_GAM
				Case 2: Return CHAR_AMY
				Case 3: Return CHAR_ROU
				Case 4: Return CHAR_TAI
			End Select
		Case CHAR_BLA:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_AMY
				Case 3: Return CHAR_MIG
				Case 4: Return CHAR_KNU
			End Select
		Case CHAR_SIL:
			Select(try)
				Case 1: Return CHAR_ESP
				Case 2: Return CHAR_SHA
				Case 3: Return CHAR_MET
				Case 4: Return CHAR_SON
			End Select
		Case CHAR_OME:
			Select(try)
				Case 1: Return CHAR_KNU
				Case 2: Return CHAR_GAM
				Case 3: Return CHAR_INF
				Case 4: Return CHAR_MIG
			End Select
		Case CHAR_ESP:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_MIG
				Case 3: Return CHAR_AMY
				Case 4: Return CHAR_ROU
			End Select
		Case CHAR_CHA:
			Select(try)
				Case 1: Return CHAR_CRE
				Case 2: Return CHAR_STO
				Case 3: Return CHAR_MAR
				Case 4: Return CHAR_SHD
			End Select
		Case CHAR_VEC:
			Select(try)
				Case 1: Return CHAR_BIG
				Case 2: Return CHAR_BAR
				Case 3: Return CHAR_OME
				Case 4: Return CHAR_HBO
			End Select
		Case CHAR_BIG:
			Select(try)
				Case 1: Return CHAR_VEC
				Case 2: Return CHAR_HBO
				Case 3: Return CHAR_BAR
				Case 4: Return CHAR_TAI
			End Select
		Case CHAR_MAR:
			Select(try)
				Case 1: Return CHAR_HON
				Case 2: Return CHAR_CRE
				Case 3: Return CHAR_CHA
				Case 4: Return CHAR_GME
			End Select
		Case CHAR_MIG:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_KNU
				Case 3: Return CHAR_ESP
				Case 4: Return CHAR_BLA
			End Select
		Case CHAR_RAY:
			Select(try)
				Case 1: Return CHAR_MIG
				Case 2: Return CHAR_AMY
				Case 3: Return CHAR_TAI
				Case 4: Return CHAR_GAM
			End Select
		Case CHAR_CHO:
			Select(try)
				Case 1: Return CHAR_BLA
				Case 2: Return CHAR_MPH
				Case 3: Return CHAR_STO
				Case 4: Return CHAR_EGR
			End Select
		Case CHAR_TIK:
			Select(try)
				Case 1: Return CHAR_EME
				Case 2: Return CHAR_NAC
				Case 3: Return CHAR_WAV
				Case 4: Return CHAR_CRE
			End Select
		Case CHAR_NAC:
			Select(try)
				Case 1: Return CHAR_HBO
				Case 2: Return CHAR_TIK
				Case 3: Return CHAR_SIL
				Case 4: Return CHAR_JET
			End Select
		Case CHAR_BEA:
			Select(try)
				Case 1: Return CHAR_RAY
				Case 2: Return CHAR_WAV
				Case 3: Return CHAR_TAI
				Case 4: Return CHAR_BET
			End Select
		Case CHAR_BAR:
			Select(try)
				Case 1: Return CHAR_STO
				Case 2: Return CHAR_VEC
				Case 3: Return CHAR_BIG
				Case 4: Return CHAR_MKN
			End Select
		Case CHAR_JET:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_MET
				Case 3: Return CHAR_MT3
				Case 4: Return CHAR_NAC
			End Select
		Case CHAR_WAV:
			Select(try)
				Case 1: Return CHAR_TAI
				Case 2: Return CHAR_BEA
				Case 3: Return CHAR_TIK
				Case 4: Return CHAR_TIA
			End Select
		Case CHAR_STO:
			Select(try)
				Case 1: Return CHAR_BAR
				Case 2: Return CHAR_CHA
				Case 3: Return CHAR_CHO
				Case 4: Return CHAR_RAY
			End Select
		Case CHAR_TIA:
			Select(try)
				Case 1: Return CHAR_AMY
				Case 2: Return CHAR_HON
				Case 3: Return CHAR_MPH
				Case 4: Return CHAR_WAV
			End Select
		Case CHAR_HON:
			Select(try)
				Case 1: Return CHAR_MAR
				Case 2: Return CHAR_SHD
				Case 3: Return CHAR_TIA
				Case 4: Return CHAR_AMY
			End Select
		Case CHAR_SHD:
			Select(try)
				Case 1: Return CHAR_ESP
				Case 2: Return CHAR_TIA
				Case 3: Return CHAR_HON
				Case 4: Return CHAR_CHA
			End Select
		Case CHAR_MPH:
			Select(try)
				Case 1: Return CHAR_SIL
				Case 2: Return CHAR_CHO
				Case 3: Return CHAR_TIA
				Case 4: Return CHAR_BLA
			End Select
		Case CHAR_HBO:
			Select(try)
				Case 1: Return CHAR_NAC
				Case 2: Return CHAR_BIG
				Case 3: Return CHAR_CHW
				Case 4: Return CHAR_VEC
			End Select
		Case CHAR_GAM:
			Select(try)
				Case 1: Return CHAR_OME
				Case 2: Return CHAR_SON
				Case 3: Return CHAR_INF
				Case 4: Return CHAR_EGR
			End Select
		Case CHAR_EME:
			Select(try)
				Case 1: Return CHAR_CHO
				Case 2: Return CHAR_GME
				Case 3: Return CHAR_EGR
				Case 4: Return CHAR_MIG
			End Select
		Case CHAR_MET:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_MIG
				Case 3: Return CHAR_EGR
				Case 4: Return CHAR_SHA
			End Select
		Case CHAR_TDL:
			Select(try)
				Case 1: Return CHAR_CHW
				Case 2: Return CHAR_RAY
				Case 3: Return CHAR_TAI
				Case 4: Return CHAR_CHW
			End Select
		Case CHAR_MKN:
			Select(try)
				Case 1: Return CHAR_EGR
				Case 2: Return CHAR_KNU
				Case 3: Return CHAR_GME
				Case 4: Return CHAR_BAR
			End Select
		Case CHAR_EGG:
			Select(try)
				Case 1: Return CHAR_OME
				Case 2: Return CHAR_GAM
				Case 3: Return CHAR_BET
				Case 4: Return CHAR_MT3
			End Select
		Case CHAR_BET:
			Select(try)
				Case 1: Return CHAR_GAM
				Case 2: Return CHAR_OME
				Case 3: Return CHAR_EGG
				Case 4: Return CHAR_BEA
			End Select
		Case CHAR_MT3:
			Select(try)
				Case 1: Return CHAR_COM
				Case 2: Return CHAR_JET
				Case 3: Return CHAR_PRS
				Case 4: Return CHAR_EGG
			End Select
		Case CHAR_GME:
			Select(try)
				Case 1: Return CHAR_PRS
				Case 2: Return CHAR_EME
				Case 3: Return CHAR_MKN
				Case 4: Return CHAR_MAR
			End Select
		Case CHAR_PRS:
			Select(try)
				Case 1: Return CHAR_GME
				Case 2: Return CHAR_PRS
				Case 3: Return CHAR_MT3
				Case 4: Return CHAR_COM
			End Select
		Case CHAR_COM:
			Select(try)
				Case 1: Return CHAR_MT3
				Case 2: Return CHAR_COM
				Case 3: Return CHAR_MET
				Case 4: Return CHAR_PRS
			End Select
		Case CHAR_CHW:
			Select(try)
				Case 1: Return CHAR_TDL
				Case 2: Return CHAR_EGR
				Case 3: Return CHAR_HBO
				Case 4: Return CHAR_TDL
			End Select
		Case CHAR_EGR:
			Select(try)
				Case 1: Return CHAR_KNU
				Case 2: Return CHAR_SON
				Case 3: Return CHAR_MIG
				Case 4: Return CHAR_TAI
			End Select
		Case CHAR_INF:
			Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_SHA
				Case 3: Return CHAR_OME
				Case 4: Return CHAR_KNU
			End Select
		Case CHAR_TMH:
			Select(try)
				Case 1: Return CHAR_EGG
				Case 2: Return CHAR_CHW
				Case 3: Return CHAR_GAM
				Case 4: Return CHAR_BET
			End Select
	End Select
	Return CHAR_SON
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D