Function Player_DealSounds(p.tPlayer)
	
	If p\No#=1 And Game\SuperForm>0 Then
		If ChannelPlaying(p\Channel_Super)=False Then p\Channel_Super=EmitSmartSound(Sound_Super,p\Objects\Entity)
	Else
		StopChannel(p\Channel_Super)
	EndIf
	
	;deal drift sound
	If p\No#=1 And (p\Action=ACTION_DRIFT Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_CARDRIFT) Then
		Select p\Collision\GroundType
			Case COLLISION_WORLD_POLYGON_WATER,COLLISION_WORLD_POLYGON_WATER2
				If ChannelPlaying(p\Channel_Drift) Then StopChannel(p\Channel_Drift)
				If Not(ChannelPlaying(p\Channel_DriftWater)) Then p\Channel_DriftWater=EmitSmartSound(Sound_WaterDrifting,p\Objects\Entity)
			Default
				If ChannelPlaying(p\Channel_DriftWater) Then StopChannel(p\Channel_DriftWater)
				If Not(ChannelPlaying(p\Channel_Drift)) Then p\Channel_Drift=EmitSmartSound(Sound_Drift,p\Objects\Entity)
		End Select
				
	Else
		StopChannel(p\Channel_Drift)
		p\DriftDirection=0
	EndIf
	
	;deal stomp sound
	If (Not(p\Action=ACTION_STOMP)) Then StopChannel(p\Channel_Stomp)
	
	;deal grind sound
	If p\No#=1 Then
		If p\Action=ACTION_GRIND Or p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDDRIFT Then
			If ChannelPlaying(p\Channel_Grind)=False Then
				If Game\Vehicle=8 Then
					If p\BoardWaterTimer>0 Then
						If p\Action=ACTION_BOARDDRIFT Then
							p\Channel_Grind=EmitSmartSound(Sound_WaterDrifting,p\Objects\Entity)
						Else
							p\Channel_Grind=EmitSmartSound(Sound_WaterBoosting,p\Objects\Entity)
						EndIf
					Else
						If p\Motion\Ground Then p\Channel_Grind=EmitSmartSound(Sound_Grind,p\Objects\Entity)
					EndIf
				Else
					If p\Motion\Ground Then p\Channel_Grind=EmitSmartSound(Sound_Grind,p\Objects\Entity)
				EndIf
			EndIf
		ElseIf p\Action=ACTION_BUMPED Then
			If p\Motion\Ground And ChannelPlaying(p\Channel_Grind)=False Then p\Channel_Grind=EmitSmartSound(Sound_Pinball,p\Objects\Entity)
		Else
			StopChannel(p\Channel_Grind)
		EndIf
	EndIf
	
	;deal psychokinesis sound
	Select p\Psychokinesis
		Case 1:
			If Not(ChannelPlaying(p\Channel_Psychokinesis)) Then p\Channel_Psychokinesis=EmitSmartSound(Sound_Psychokinesis,p\Objects\Entity)
		Case 0:
			If ChannelPlaying(p\Channel_Psychokinesis) Then StopChannel(p\Channel_Psychokinesis)
	End Select
	
	If Not(p\Action=ACTION_LIGHTATTACK) Then StopChannel(p\Channel_LightAttack)
	
	
	
	
	
End Function


Function Player_PlayRandomStep(p.tPlayer,firstframe,otherframe)
	
	If Player_FrameCheck(p,firstframe) Or Player_FrameCheck(p,otherframe) Then
		Select p\RealCharacter
			Case CHAR_SHA,CHAR_SHN:
				If p\Animation\Animation = ANIMATION_RUN Then Player_PlayRandomSwooshStep(p) Else Player_PlayRandomActualStep(p)
			Case CHAR_OME,CHAR_MET,CHAR_MKN,CHAR_HBO,CHAR_SHD,CHAR_GAM,CHAR_EME,CHAR_EGG,CHAR_BET,CHAR_MT3,CHAR_GME,CHAR_CHW,CHAR_EGR:
				Player_PlayRandomMetalStep(p)
			Case CHAR_TDL:
				Player_PlayRandomDollStep(p)
			Case CHAR_CHO:
				Player_PlayRandomWaterStep(p)
			Default:
				If IsCharMod(p\RealCharacter) Then
					If MODCHARS_SKATES(p\RealCharacter-CHAR_MOD1+1)>0 Then
						If p\Animation\Animation = ANIMATION_RUN Then
							Player_PlayRandomSwooshStep(p)
						Else
							Player_PlayRandomActualStep(p)
						EndIf
					Else
						Player_PlayRandomActualStep(p)
					EndIf
				Else
					Player_PlayRandomActualStep(p)
				EndIf
		End Select
		
		If p\UnderwaterFeet=1 Or p\Waterrunning=1 Then Player_PlayRandomUnderwaterStep(p)
	EndIf
End Function
Function Player_PlayRandomActualStep(p.tPlayer)
	
	Select p\Collision\GroundType
		Case COLLISION_WORLD_POLYGON_METAL: Player_PlayRandomMetalStep(p)
		Case COLLISION_WORLD_POLYGON_WOOD: Player_PlayRandomWoodStep(p)
		Case COLLISION_WORLD_POLYGON_GRASS: Player_PlayRandomGrassStep(p)
		Case COLLISION_WORLD_POLYGON_DIRT: Player_PlayRandomDirtStep(p)
		Case COLLISION_WORLD_POLYGON_WATER,COLLISION_WORLD_POLYGON_WATER2: Player_PlayRandomUnderwaterStep(p)
		Default: Player_PlayRandomGroundStep(p)
	End Select
	
	
	
End Function
Function Player_PlayRandomGroundStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5,p\Objects\Entity)
	End Select
End Function
Function Player_PlayRandomDirtStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1Dirt,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2Dirt,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3Dirt,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4Dirt,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5Dirt,p\Objects\Entity)
	End Select
End Function
Function Player_PlayRandomWoodStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1Wood,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2Wood,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3Wood,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4Wood,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5Wood,p\Objects\Entity)
	End Select
End Function
Function Player_PlayRandomGrassStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1Grass,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2Grass,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3Grass,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4Grass,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5Grass,p\Objects\Entity)
	End Select
End Function
Function Player_PlayRandomMetalStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1Metal,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2Metal,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3Metal,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4Metal,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5Metal,p\Objects\Entity)
	End Select
End Function

Function Player_PlayRandomDollStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1Doll,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2Doll,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3Doll,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4Doll,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5Doll,p\Objects\Entity)
	End Select
End Function

Function Player_PlayRandomSwooshStep(p.tPlayer)
	Select(Rand(1,2))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundShadowStep1,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundShadowStep2,p\Objects\Entity)
	End Select
End Function

Function Player_PlayRandomWaterStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep1Water,p\Objects\Entity)
		Case 2: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep2Water,p\Objects\Entity)
		Case 3: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep3Water,p\Objects\Entity)
		Case 4: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep4Water,p\Objects\Entity)
		Case 5: p\Channel_GroundStep=EmitSmartSound(Sound_GroundStep5Water,p\Objects\Entity)
	End Select
End Function

Function Player_PlayRandomUnderwaterStep(p.tPlayer)
	Select(Rand(1,5))
		Case 1: p\Channel_GroundStep2=EmitSmartSound(Sound_GroundStep1Water,p\Objects\Entity)
		Case 2: p\Channel_GroundStep2=EmitSmartSound(Sound_GroundStep2Water,p\Objects\Entity)
		Case 3: p\Channel_GroundStep2=EmitSmartSound(Sound_GroundStep3Water,p\Objects\Entity)
		Case 4: p\Channel_GroundStep2=EmitSmartSound(Sound_GroundStep4Water,p\Objects\Entity)
		Case 5: p\Channel_GroundStep2=EmitSmartSound(Sound_GroundStep5Water,p\Objects\Entity)
	End Select
	ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
End Function

Function Player_NonStepWaterSplash(p.tPlayer,firstframe,otherframe)
	If (Not(firstframe=0 And otherframe=0)) And (Player_FrameCheck(p,firstframe) Or Player_FrameCheck(p,otherframe)) And p\UnderwaterFeet=1 Then ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
End Function
Function Player_AnimationSounds(p.tPlayer)
	
	If Not (p\Animation\Animation = ANIMATION_IDLE Or p\Animation\Animation = ANIMATION_WAIT) Then
		p\Animation\IdleType = 0
		p\Animation\IdleCount =0
	EndIf 
	
	Select p\Animation\Animation
		Case ANIMATION_IDLE
			If Player_FrameCheck(p,16) And p\Animation\IdleType = 0 Then 
				p\Animation\IdleCount=p\Animation\IdleCount+1
			EndIf 
			
			If p\Animation\IdleCount=7 And p\Animation\IdleType = 0 Then p\Animation\IdleType=1 : p\Animation\IdleCount=0 : Player_PlayIdleVoice(p)
		Case ANIMATION_WAIT
			If Player_FrameCheck(p,65) Then 
				p\Animation\IdleType = 0
				p\Animation\IdleCount =0
			EndIf 
		Case ANIMATION_VICTORY
			Select p\Character
				Case CHAR_EGR
					If Player_FrameCheck(p,33) Then
						EmitSmartSound(Sound_EnemyShot,p\Objects\Entity)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#+25, 0, BOMB_ORB)
					EndIf 
					If Player_FrameCheck(p,46) Then
						EmitSmartSound(Sound_EnemyShot,p\Objects\Entity)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-90, 0, BOMB_ORB)
					EndIf 
					If Player_FrameCheck(p,69) Then
						EmitSmartSound(Sound_EnemyShot,p\Objects\Entity)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#+170, 0, BOMB_ORB)
					EndIf 
			End Select
			If Player_FrameCheck(p,135) Then p\Animation\VictoryStage=1
				
				
			
		;walking animation
		Case ANIMATION_WALK,ANIMATION_CARRYWALK
			Select p\RealCharacter
				Case CHAR_CHA,CHAR_MPH,CHAR_INF:
					Player_NonStepWaterSplash(p,3,11)
				Default:
					If IsCharMod(p\RealCharacter) Then
						Select MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_WALK)
							Case 0
								StopChannel(p\Channel_GroundFly)
							Case 1
								StopChannel(p\Channel_GroundFly)
								Player_PlayRandomStep(p,3,11)
							Case 2
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 3
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 4
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
						End Select
					Else
						Player_PlayRandomStep(p,3,11)
					EndIf
			End Select
		;jogging animation
		Case ANIMATION_JOG
			Select p\RealCharacter
				Case CHAR_CHA,CHAR_MPH,CHAR_INF:
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_EGR
					
				Default:
					If IsCharMod(p\RealCharacter) Then
						Select MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_JOG)
							Case 0
								StopChannel(p\Channel_GroundFly)
							Case 1
								StopChannel(p\Channel_GroundFly)
								Player_PlayRandomStep(p,3,11)
							Case 2
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 3
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 4
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
						End Select
					Else
						Player_PlayRandomStep(p,3,11)
					EndIf
			End Select
			;running animation
		Case ANIMATION_RUN,ANIMATION_MACHRUN
			Select p\RealCharacter
				Case CHAR_SHA,CHAR_SHN:
					Player_PlayRandomStep(p,2,18)
				Case CHAR_INF:
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_TAI:
					If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_OME,CHAR_MET,CHAR_EGR:
					If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_GAM
					If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_SIL
					If p\Animation\Animation=ANIMATION_RUN Then Player_PlayRandomStep(p,3,11)
				Default:
					If IsCharMod(p\RealCharacter) Then
						If p\Animation\Animation=ANIMATION_RUN Then i = MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_RUN) Else i = MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_MACHRUN)
						Select i
							Case 0
								StopChannel(p\Channel_GroundFly)
							Case 1
								StopChannel(p\Channel_GroundFly)
								Player_PlayRandomStep(p,3,11)
							Case 2
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 3
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 4
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
						End Select
					Else
						Player_PlayRandomStep(p,3,11)
					EndIf
			End Select
		Case ANIMATION_DRIFTL,ANIMATION_DRIFTR
			Select p\RealCharacter
				Case CHAR_SHA,CHAR_SHN,CHAR_SIL:
					
				Case CHAR_CHA,CHAR_MPH,CHAR_INF:
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_TAI:
					If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_CRE:
					If Player_FrameCheck(p,13) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyEars,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_OME,CHAR_MET,CHAR_EGR:
					If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Case CHAR_GAM
					If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
					Player_NonStepWaterSplash(p,3,11)
				Default:
					If IsCharMod(p\RealCharacter) Then
						Select MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_DRIFT)
							Case 0
								StopChannel(p\Channel_GroundFly)
							Case 1
								StopChannel(p\Channel_GroundFly)
								Player_PlayRandomStep(p,3,11)
							Case 2
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 3
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
							Case 4
								If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
								Player_NonStepWaterSplash(p,3,11)
						End Select
					Else
						Player_PlayRandomStep(p,3,11)
					EndIf
			End Select
	End Select
	
	
	
	
	If p\UnderwaterFeet=1 And (Not(p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT)) And Game\Interface\DebugPlacerOn=0 Then
		If p\SpeedLength#>6 Or p\Motion\Speed\y#>6 Or p\Motion\Speed\y#<-6 Then
			Player_NonStepWaterSplash(p.tPlayer,1,3)
			Player_NonStepWaterSplash(p.tPlayer,5,7)
			Player_NonStepWaterSplash(p.tPlayer,9,11)
			Player_NonStepWaterSplash(p.tPlayer,13,15)
		ElseIf p\SpeedLength#>5 Or p\Motion\Speed\y#>5 Or p\Motion\Speed\y#<-5 Then
			Player_NonStepWaterSplash(p.tPlayer,1,3)
			Player_NonStepWaterSplash(p.tPlayer,5,7)
			Player_NonStepWaterSplash(p.tPlayer,9,11)
			Player_NonStepWaterSplash(p.tPlayer,13,15)
		ElseIf p\SpeedLength#>4 Or p\Motion\Speed\y#>4 Or p\Motion\Speed\y#<-4 Then
			Player_NonStepWaterSplash(p.tPlayer,1,3)
			Player_NonStepWaterSplash(p.tPlayer,9,11)
		ElseIf p\SpeedLength#>3 Or p\Motion\Speed\y#>3 Or p\Motion\Speed\y#<-3 Then
			Player_NonStepWaterSplash(p.tPlayer,1,3)
			Player_NonStepWaterSplash(p.tPlayer,9,11)
		ElseIf p\SpeedLength#>2 Or p\Motion\Speed\y#>2 Or p\Motion\Speed\y#<-2 Then
			Player_NonStepWaterSplash(p.tPlayer,1,3)
			Player_NonStepWaterSplash(p.tPlayer,9,11)
		ElseIf p\SpeedLength#>1 Or p\Motion\Speed\y#>1 Or p\Motion\Speed\y#<-1 Then
			Player_NonStepWaterSplash(p.tPlayer,1,9)
		ElseIf p\SpeedLength#>0.5 Or p\Motion\Speed\y#>0.5 Or p\Motion\Speed\y#<-0.5 Then
			Player_NonStepWaterSplash(p.tPlayer,1,9)
		EndIf
	EndIf
	
	
;Shut up ground fly
	If IsCharMod(p\RealCharacter) Then
		Select p\Animation\Animation
			Case ANIMATION_WALK
				If MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_WALK)<2 Then StopChannel(p\Channel_GroundFly)
			Case ANIMATION_JOG
				If MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_JOG)<2 Then StopChannel(p\Channel_GroundFly)
			Case ANIMATION_RUN,ANIMATION_MACHRUN
				If MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_RUN)<2 Then StopChannel(p\Channel_GroundFly)
			Case ANIMATION_DRIFTL,ANIMATION_DRIFTR
				If MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_DRIFT)<2 Then StopChannel(p\Channel_GroundFly)
		End Select
		
	Else
		If (Not(p\Animation\Animation=ANIMATION_RUN Or p\Animation\Animation=ANIMATION_MACHRUN)) Then
			StopChannel(p\Channel_GroundFly)
		EndIf
	EndIf
	
	
;Walking flag
	If p\SpeedLength<1.75 Then p\Flags\Walking=True Else p\Flags\Walking=False
	
;Skidding sound
	If p\No#=1 And p\Flags\Skidding And p\Flags\Walking=False And (Not(p\Action=ACTION_DRIFT Or p\Action=ACTION_BUMPED Or p\Action=ACTION_GRIND Or p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_CAR Or p\Action=ACTION_CARDRIFT Or p\Action=ACTION_DEBUG)) Then
		If Not(ChannelPlaying(p\Channel_GroundSkid)) Then
			Select p\Character
				Case CHAR_CHO: p\Channel_GroundSkid=EmitSmartSound(Sound_GroundSkidWater,p\Objects\Entity)
				Default: p\Channel_GroundSkid=EmitSmartSound(Sound_GroundSkid,p\Objects\Entity)
			End Select
		EndIf
	Else
		StopChannel(p\Channel_GroundSkid)
	EndIf
	
;Flying sound
	If p\Action=ACTION_FLY Then
		If IsCharMod(p\RealCharacter) Then
			Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
				Case JUMPACTION_SOAR
					Select p\Animation\Animation
						Case ANIMATION_FLY
							StopChannel(p\Channel_SoarDown)
							If Not(ChannelPlaying(p\Channel_SoarUp)) Then p\Channel_SoarUp=EmitSmartSound(Sound_SoarUp,p\Objects\Entity)
						Case ANIMATION_GLIDE
							StopChannel(p\Channel_SoarUp)
							If Not(ChannelPlaying(p\Channel_SoarDown)) Then p\Channel_SoarDown=EmitSmartSound(Sound_SoarDown,p\Objects\Entity)
					End Select	
				Default
					If Not(ChannelPlaying(p\Channel_Fly)) Then p\Channel_Fly=EmitSmartSound(Sound_FlyTails,p\Objects\Entity)
			End Select
		Else
			Select p\Character
				Case CHAR_TAI:
					If Not(ChannelPlaying(p\Channel_Fly)) Then p\Channel_Fly=EmitSmartSound(Sound_FlyTails,p\Objects\Entity)
				Case CHAR_TDL:
					If Not(ChannelPlaying(p\Channel_Fly)) Then p\Channel_Fly=EmitSmartSound(Sound_FlyTailsDoll,p\Objects\Entity)
				Case CHAR_CRE:
					If Player_FrameCheck(p,7) Then p\Channel_Fly=EmitSmartSound(Sound_FlyEars,p\Objects\Entity)
				Case CHAR_CHA:
					If Player_FrameCheck(p,5) Then p\Channel_Fly=EmitSmartSound(Sound_FlyBuzz,p\Objects\Entity)
				Case CHAR_WAV:
					If Not(ChannelPlaying(p\Channel_Fly)) Then p\Channel_Fly=EmitSmartSound(Sound_Propeller,p\Objects\Entity)
				Case CHAR_EME,CHAR_GME,CHAR_EGR,CHAR_BEA:
					If Not(ChannelPlaying(p\Channel_Fly)) Then p\Channel_Fly=EmitSmartSound(Sound_Hover,p\Objects\Entity)
				Case CHAR_ROU:
					If Player_FrameCheck(p,6) And ChannelPlaying(p\Channel_Fly)=False Then p\Channel_Fly=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
				Case CHAR_RAY
					Select p\Animation\Animation
						Case ANIMATION_FLY
							StopChannel(p\Channel_SoarDown)
							If Not(ChannelPlaying(p\Channel_SoarUp)) Then p\Channel_SoarUp=EmitSmartSound(Sound_SoarUp,p\Objects\Entity)
						Case ANIMATION_GLIDE
							StopChannel(p\Channel_SoarUp)
							If Not(ChannelPlaying(p\Channel_SoarDown)) Then p\Channel_SoarDown=EmitSmartSound(Sound_SoarDown,p\Objects\Entity)
					End Select	
					
					
			End Select
		EndIf
	Else
		StopChannel(p\Channel_SoarUp)
		StopChannel(p\Channel_SoarDown)
		StopChannel(p\Channel_Fly)
	EndIf
	
;Gliding sound
	If p\Action=ACTION_GLIDE Or p\Action=ACTION_FLUTTER Or p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Or (p\Action=ACTION_SLOWGLIDE And p\Character=CHAR_TIA) Then
		Select p\Character
			Case CHAR_ESP
			Case CHAR_MAR
				If Player_FrameCheck(p,1) Or Player_FrameCheck(p,9) Then p\Channel_Glide=EmitSmartSound(Sound_Flutter,p\Objects\Entity)
			Case CHAR_HON:
				If Player_FrameCheck(p,1) Then p\Channel_Glide=EmitSmartSound(Sound_Flutter,p\Objects\Entity)
			Case CHAR_BAR:
				If Player_FrameCheck(p,1) Or Player_FrameCheck(p,5) Then p\Channel_Glide=EmitSmartSound(Sound_PunchSmall,p\Objects\Entity)
			Default:
				If ChannelPlaying(p\Channel_Glide)=False Then p\Channel_Glide=EmitSmartSound(Sound_Glide,p\Objects\Entity)
				Select p\Character
					Case CHAR_ROU:
						If Player_FrameCheck(p,2) And ChannelPlaying(p\Channel_GlideX)=False Then p\Channel_GlideX=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
					Case CHAR_RAY:
						If p\Action=ACTION_SOARFLAP Then
							If Player_FrameCheck(p,5) And ChannelPlaying(p\Channel_GlideX)=False Then p\Channel_GlideX=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
						EndIf
					Case CHAR_MKN,CHAR_EME,CHAR_GME,CHAR_OME:
						If Not(ChannelPlaying(p\Channel_GlideX)) Then p\Channel_GlideX=EmitSmartSound(Sound_Hover,p\Objects\Entity)
				End Select
		End Select
	Else
		StopChannel(p\Channel_Glide)
		StopChannel(p\Channel_GlideX)
	EndIf
	
;Levitating/hovering sound
	If p\Action=ACTION_LEVITATE Or p\Action=ACTION_HOVER Or p\Action=ACTION_SHOOTHOVER Or p\Action=ACTION_PUDDLE Or p\Action=ACTION_BELLYFLOP Then
		Select p\Character
			Case CHAR_SIL,CHAR_MPH,CHAR_INF:
				If Not(ChannelPlaying(p\Channel_Levitate)) Then p\Channel_Levitate=EmitSmartSound(Sound_Levitate,p\Objects\Entity)
			Case CHAR_OME,CHAR_STO,CHAR_GAM,CHAR_EGG,CHAR_GME,CHAR_EGR:
				If Not(ChannelPlaying(p\Channel_Levitate)) Then p\Channel_Levitate=EmitSmartSound(Sound_Hover,p\Objects\Entity)
			Case CHAR_HBO:
				If Not(ChannelPlaying(p\Channel_Levitate)) Then p\Channel_Levitate=EmitSmartSound(Sound_Propeller,p\Objects\Entity)
			Case CHAR_CHO:
				If Not(ChannelPlaying(p\Channel_Levitate)) Then p\Channel_Levitate=EmitSmartSound(Sound_WaterBoosting,p\Objects\Entity)
		End Select
	Else
		StopChannel(p\Channel_Levitate)
	EndIf
	
;Climbing sound
	If (p\Action=ACTION_CLIMB And p\SpeedLength#>0 And (Not(p\WalldashTimer>0))) Then
		If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_Climb,p\Objects\Entity)
	ElseIf (p\Action=ACTION_SKYDIVE Or p\Action=ACTION_GLIDER) Then
		If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_Skydive,p\Objects\Entity)
	ElseIf (p\Action=ACTION_CAR Or p\Action=ACTION_CARFALL Or p\Action=ACTION_CARDRIFT) Then
		If (p\SpeedLength#>0.05 Or p\Motion\Speed\y#>0.05) And p\HasVehicle>0 Then
			If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
			If p\HasVehicle=3 Then
				ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_ROCKET2, p\Objects\VehicleJet1)
			ElseIf p\HasVehicle=4 Or p\HasVehicle=9 Then
				ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_ROCKET, p\Objects\VehicleJet1)
				ParticleTemplate_Call(p\Particle2, PARTICLE_PLAYER_ROCKET, p\Objects\VehicleJet2)
			EndIf
		Else
			If p\HasVehicle=4 Or p\HasVehicle=9 Then Animate(p\Objects\Vehicle,1,0.2,1,10)
			StopChannel(p\Channel_Climb)
		EndIf
	ElseIf (p\Action=ACTION_TORNADO) Then
		If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_PlaneFlight,p\Objects\Entity)
		If p\HasVehicle=7 Then
			ParticleTemplate_Call(p\Particle, PARTICLE_OBJECT_ROCKETFUMES, p\Objects\VehicleJet2)
			ParticleTemplate_Call(p\Particle2, PARTICLE_PLAYER_ROCKET2, p\Objects\VehicleJet1)
		EndIf
	Else
		StopChannel(p\Channel_Climb)
	EndIf
	
;Buoy splash
	If p\Action=ACTION_BUOY Then Player_NonStepWaterSplash(p,1,9)
	
;Tinkle sound
	If (Not(p\Action=ACTION_GATLING)) Then StopChannel(p\Channel_Tinkle)
	
;ruby cubes effect
	If p\Character=CHAR_INF Then
		If p\RubyGravityTimer>0 Then
			Player_RubyCubes(p,0.75,20,7)
		ElseIf Game\Victory=0 And Game\Vehicle=0 Then
			If p\SpeedLength#>0.5 Or p\Motion\Speed\y#>0.5 Then Player_RubyCubes(p)
		EndIf
	EndIf
End Function 
;~IDEal Editor Parameters:
;~C#Blitz3D