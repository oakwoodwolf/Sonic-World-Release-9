
	; Animation constants
	Const ANIMATION_VICTORY		= 1
	Const ANIMATION_IDLE		= 2
	Const ANIMATION_WALK		= 3
	Const ANIMATION_JOG			= 4
	Const ANIMATION_RUN			= 5
	Const ANIMATION_SPIN		= 6
	Const ANIMATION_FALL		= 7
	Const ANIMATION_FALLFAST	= 8
	Const ANIMATION_FORWARD		= 9
	Const ANIMATION_FORWARDRUN	= 90
	Const ANIMATION_UP			= 10
	Const ANIMATION_FLOAT		= 11
	Const ANIMATION_BRAKE		= 12
	Const ANIMATION_HURT		= 13
	Const ANIMATION_DEAD		= 14
	Const ANIMATION_DEADFALL	= 15
	Const ANIMATION_FLY			= 16
	Const ANIMATION_GLIDE		= 17
	Const ANIMATION_CLIMBIDLE	= 18
	Const ANIMATION_CLIMB		= 19
	Const ANIMATION_GRIND		= 20
	Const ANIMATION_GRINDFAST	= 21
	Const ANIMATION_CARRYIDLE	= 22
	Const ANIMATION_CARRYWALK	= 23
	Const ANIMATION_CARRYJUMP	= 24
	Const ANIMATION_HOLD1		= 25
	Const ANIMATION_HOLD2		= 26
	Const ANIMATION_THROW		= 27
	Const ANIMATION_THROW2		= 28
	Const ANIMATION_THROWAIR	= 29
	Const ANIMATION_KICK		= 30
	Const ANIMATION_KICKAIR		= 31
	Const ANIMATION_PUNCH1		= 32
	Const ANIMATION_PUNCH2		= 33
	Const ANIMATION_PUNCH3		= 34
	Const ANIMATION_PUNCHAIR	= 35
	Const ANIMATION_KICK2		= 36
	Const ANIMATION_KICKAIR2	= 37
	Const ANIMATION_KICK3		= 38
	Const ANIMATION_PUNCHAIR2	= 39
	Const ANIMATION_GLIDE2		= 40

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

	Function Player_FrameCheck(p.tPlayer,frameno,tillend=false)
		If Not(p\UsedFrameTimer>0) Then
			If p\Frame=frameno Or (tillend and p\Frame>=frameno) Then
				p\UsedFrameTimer=0.05*secs#
				Return True
			Else
				Return False
			EndIf
		Else
			Return False
		EndIf
	End Function

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

	Function Player_PlayRandomStep(p.tPlayer,firstframe,otherframe)
		If Player_FrameCheck(p,firstframe) or Player_FrameCheck(p,otherframe) Then
			Select p\RealCharacter
				Case CHAR_SHA:
					If p\Animation\Animation = ANIMATION_RUN Then Player_PlayRandomSwooshStep(p) Else Player_PlayRandomGroundStep(p)
				Case CHAR_OME,CHAR_MET,CHAR_MKN,CHAR_HBO,CHAR_SHD,CHAR_GAM,CHAR_EME,CHAR_EGG,CHAR_BET,CHAR_MT3,CHAR_GME,CHAR_CHW,CHAR_TMH,CHAR_EGR:
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
								If MODCHARS_METALSTEPS(p\RealCharacter-CHAR_MOD1+1)>0 Then Player_PlayRandomMetalStep(p) Else Player_PlayRandomGroundStep(p)
							EndIf
						Else
							If MODCHARS_METALSTEPS(p\RealCharacter-CHAR_MOD1+1)>0 Then Player_PlayRandomMetalStep(p) Else Player_PlayRandomGroundStep(p)
						EndIf
					Else
						Player_PlayRandomGroundStep(p)
					EndIf
			End Select
			If p\UnderwaterFeet=1 Then Player_PlayRandomUnderwaterStep(p)
		EndIf
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
		If (Not(firstframe=0 and otherframe=0)) and (Player_FrameCheck(p,firstframe) Or Player_FrameCheck(p,otherframe)) And p\UnderwaterFeet=1 Then ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; =========================================================================================================
	; =========================================================================================================
	Function Player_Animate(p.tPlayer, d.tDeltaTime)

	If Game\Interface\DebugPlacerOn=0 Then p\Frame = Int(AnimTime(p\Objects\Mesh))

;Step sound
If p\Animation\Animation = ANIMATION_WALK Or (p\Action=ACTION_CARRY and p\Animation\Animation = ANIMATION_CARRYWALK) Then
	Select p\RealCharacter
		Case CHAR_CHA,CHAR_MPH,CHAR_INF:
			Player_NonStepWaterSplash(p,3,11)
		Default:
			Player_PlayRandomStep(p,3,11)
	End Select
ElseIf p\Animation\Animation = ANIMATION_JOG Then
	Select p\RealCharacter
		Case CHAR_CHA,CHAR_MPH,CHAR_INF:
			Player_NonStepWaterSplash(p,3,11)
		Default:
			Player_PlayRandomStep(p,3,11)
	End Select
ElseIf p\Animation\Animation = ANIMATION_RUN And (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
	Select p\RealCharacter
		Case CHAR_SHA:
			Player_PlayRandomStep(p,2,18)
		Case CHAR_CHA,CHAR_MPH,CHAR_INF:
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_TAI:
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_TDL:
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTailsDoll,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_CRE:
			If Player_FrameCheck(p,13) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyEars,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_OME,CHAR_MET,CHAR_MT3,CHAR_EGR:
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_GAM,CHAR_BET:
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
		Default:
			If IsCharMod(p\RealCharacter) Then
				If MODCHARS_NORUNSTEPS(p\RealCharacter-CHAR_MOD1+1)=0 Then
					If MODCHARS_MOTORRUNS(p\RealCharacter-CHAR_MOD1+1)>0 Then
						If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_EnemyMotor2,p\Objects\Entity)
						Player_NonStepWaterSplash(p,3,11)
					ElseIf MODCHARS_HOVERRUNS(p\RealCharacter-CHAR_MOD1+1)>0 Then
						If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
						Player_NonStepWaterSplash(p,3,11)
					ElseIf MODCHARS_TAILRUNS(p\RealCharacter-CHAR_MOD1+1)>0 Then
						If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
						Player_NonStepWaterSplash(p,3,11)
					Else
						Player_PlayRandomStep(p,3,11)
					EndIf
				EndIf
			Else
				Player_PlayRandomStep(p,3,11)
			EndIf
	End Select
Else
	If p\UnderwaterFeet=1 And (Not(p\Action=ACTION_CHARGE Or p\Action=Action_ROLL Or p\Action=ACTION_DRIFT)) And Game\Interface\DebugPlacerOn=0 Then
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
EndIf

;Shut up ground fly
If (Not(p\Animation\Animation=ANIMATION_RUN)) Then
	StopChannel(p\Channel_GroundFly)
EndIf

;Walking flag
If p\SpeedLength<1.75 Then p\Flags\Walking=True Else p\Flags\Walking=False

;Skidding sound
If p\No#=1 and p\Flags\Skidding and p\Flags\Walking=False and (Not(p\Action=ACTION_DRIFT Or p\Action=ACTION_BUMPED Or p\Action=ACTION_GRIND Or p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_CAR Or p\Action=ACTION_CARDRIFT Or p\Action=ACTION_DEBUG)) Then
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
			If Player_FrameCheck(p,2) and ChannelPlaying(p\Channel_Fly)=False Then p\Channel_Fly=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
	End Select
Else
	StopChannel(p\Channel_Fly)
EndIf

;Gliding sound
If p\Action=ACTION_GLIDE Or p\Action=ACTION_FLUTTER Or p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Or (p\Action=ACTION_SLOWGLIDE and p\Character=CHAR_TIA) Then
	Select p\Character
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
					If Player_FrameCheck(p,2) and ChannelPlaying(p\Channel_GlideX)=False Then p\Channel_GlideX=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
				Case CHAR_RAY:
					If p\Action=ACTION_SOARFLAP Then
						If Player_FrameCheck(p,5) and ChannelPlaying(p\Channel_GlideX)=False Then p\Channel_GlideX=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
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
		Case CHAR_SIL,CHAR_MPH,CHAR_MT3,CHAR_INF:
			If Not(ChannelPlaying(p\Channel_Levitate)) Then p\Channel_Levitate=EmitSmartSound(Sound_Levitate,p\Objects\Entity)
		Case CHAR_OME,CHAR_STO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_GME,CHAR_CHW,CHAR_TMH,CHAR_EGR:
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
If (p\Action=ACTION_CLIMB and p\SpeedLength#>0) Then
	If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_Climb,p\Objects\Entity)
ElseIf (p\Action=ACTION_SKYDIVE Or p\Action=ACTION_GLIDER) Then
	If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_Skydive,p\Objects\Entity)
ElseIf (p\Action=ACTION_CAR Or p\Action=ACTION_CARFALL Or p\Action=ACTION_CARDRIFT) Then
	If (p\SpeedLength#>0.05 Or p\Motion\Speed\y#>0.05) and p\HasVehicle>0 Then
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
	ElseIf Game\Victory=0 and Game\Vehicle=0 Then
		If p\SpeedLength#>0.5 Or p\Motion\Speed\y#>0.5 Then Player_RubyCubes(p)
	EndIf
EndIf

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If Game\Interface\DebugPlacerOn=0 Then Player_UpdateBoneEntities(p)

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		; Change animation depending on action
		Select p\Action
			Case ACTION_DEBUG
				If p\ObjType=0 Then p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_VICTORY
				If p\Motion\Ground Then p\Animation\Animation = ANIMATION_VICTORY Else p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_COMMON
				If p\ForceShotWalkTimer>0 Then
					If (p\SpeedLength# > 2.35) Then
						p\Animation\Animation = ANIMATION_RUN
					Else
						p\Animation\Animation = ANIMATION_WALK
					EndIf
				Else
					If (p\SpeedLength# > 2.35) Then
						If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then p\Animation\Animation = ANIMATION_RUN Else p\Animation\Animation = ANIMATION_FORWARDRUN
					ElseIf (p\SpeedLength# > 0.85) Then
						p\Animation\Animation = ANIMATION_JOG
					ElseIf (p\SpeedLength# > 0.00) Then
						p\Animation\Animation = ANIMATION_WALK
					Else
						p\Animation\Animation = ANIMATION_IDLE
					EndIf
				EndIf
				If p\Flags\Skidding and p\Flags\Walking=False Then p\Animation\Animation = ANIMATION_BRAKE
			Case ACTION_HOP,ACTION_SINK
				p\Animation\Animation = ANIMATION_FALL
			Case ACTION_JUMP,ACTION_LAND
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_FALL,ACTION_JUMPFALL,ACTION_FULLFALL
				p\Animation\Animation = ANIMATION_FALL
				If (p\SpeedLength# > 2.9) Then p\Animation\Animation = ANIMATION_FALLFAST
			Case ACTION_CHARGE
				If p\ChargeTimer<0.1*secs# Then
					p\Animation\Animation = ANIMATION_FALL
				Else
					p\Animation\Animation = ANIMATION_SPIN
				EndIf
			Case ACTION_ROLL,ACTION_DRIFT
				If p\ChargeTimer<0.1*secs# Then
					p\Animation\Animation = ANIMATION_FALL
				Else
					p\Animation\Animation = ANIMATION_SPIN
				EndIf
			Case ACTION_UP,ACTION_TRANSFORM
				p\Animation\Animation = ANIMATION_UP
			Case ACTION_DOUBLEJUMP
				Select p\Character
					Case CHAR_NAC,CHAR_BEA:
						Select p\DoubleJump
							Case 0: p\Animation\Animation = ANIMATION_KICKAIR
							Case 1: p\Animation\Animation = ANIMATION_UP
						End Select
					Default: p\Animation\Animation = ANIMATION_UP
				End Select
			Case ACTION_FWD,ACTION_JUMPDASH,ACTION_LIGHTDASH
				p\Animation\Animation = ANIMATION_FORWARD
			Case ACTION_HOMING,ACTION_SPIRIT
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_INSTASHIELD, ACTION_STARDASH, ACTION_FIREDASH
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_FLY,ACTION_LEVITATE,ACTION_BUOY,ACTION_SOARFLAP
				p\Animation\Animation = ANIMATION_FLY
			Case ACTION_SHOOTHOVER
				Select p\Character
					Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH,CHAR_EGR: p\Animation\Animation = ANIMATION_THROWAIR
					Default: p\Animation\Animation = ANIMATION_FLY
				End Select
			Case ACTION_GLIDE,ACTION_HOVER,ACTION_SOAR,ACTION_FLUTTER
				Select p\Character
					Case CHAR_GME:
						If p\Action=ACTION_HOVER Then p\Animation\Animation = ANIMATION_GLIDE2 Else p\Animation\Animation = ANIMATION_GLIDE
					Case CHAR_OME:
						If p\Action=ACTION_HOVER Then p\Animation\Animation = ANIMATION_GLIDE Else p\Animation\Animation = ANIMATION_GLIDE2
					Default:
						p\Animation\Animation = ANIMATION_GLIDE
				End Select
			Case ACTION_SLOWGLIDE
				Select p\Character
					Case CHAR_TIA:
						If p\SpeedLength#>2 Then p\Animation\Animation = ANIMATION_FLY Else p\Animation\Animation = ANIMATION_GLIDE
					Default:
						p\Animation\Animation = ANIMATION_GLIDE
				End Select
			Case ACTION_STOMP
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_HURT
				p\Animation\Animation = ANIMATION_HURT
			Case ACTION_DIE,ACTION_RIVALDIE
				If p\Motion\Ground Then p\Animation\Animation = ANIMATION_DEAD Else p\Animation\Animation = ANIMATION_DEADFALL
			Case ACTION_FLOAT,ACTION_FREEZE
				p\Animation\Animation = ANIMATION_FLOAT
			Case ACTION_GRIND,ACTION_BOARD,ACTION_BOARDJUMP,ACTION_BOARDDRIFT,ACTION_BOARDFALL,ACTION_BOARDTRICK
				If Game\Vehicle=5 Or Game\Vehicle=8 Then
					p\Animation\Animation = ANIMATION_CARRYIDLE
				Else
					If p\GrindTurnTimer>0 Then
						p\Animation\Animation = ANIMATION_FALL
					Else
						Select p\GrindTurn
							Case 1: p\Animation\Animation = ANIMATION_GRIND
							Case 2: p\Animation\Animation = ANIMATION_GRINDFAST
						End Select
					EndIf
				EndIf
			Case ACTION_CLIMB
				If (p\SpeedLength# > 0.1) Then p\Animation\Animation = ANIMATION_CLIMB Else p\Animation\Animation = ANIMATION_CLIMBIDLE
			Case ACTION_BUMPED,ACTION_CANNON,ACTION_CANNON2,ACTION_CANNON3
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_GRABBED
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_THROW,ACTION_HOOKSHOT
				Select p\Character
					Case CHAR_AMY: p\Animation\Animation = ANIMATION_THROW2
					Case CHAR_CRE:
						Select p\ThrowType
						Case 1,3: If p\Motion\Ground Then p\Animation\Animation = ANIMATION_THROW Else p\Animation\Animation = ANIMATION_THROWAIR
						Case 2: p\Animation\Animation = ANIMATION_THROW2
						End Select
					Case CHAR_MET,CHAR_MT3: p\Animation\Animation = ANIMATION_KICKAIR
					Case CHAR_HBO: p\Animation\Animation = ANIMATION_PUNCH3
					Case CHAR_KNU:
						Select p\ThrowType
						Case 1: p\Animation\Animation = ANIMATION_PUNCH3
						Case 2: p\Animation\Animation = ANIMATION_THROW
						End Select
					Case CHAR_NAC,CHAR_COM: If p\Motion\Ground Then p\Animation\Animation = ANIMATION_THROW Else p\Animation\Animation = ANIMATION_THROWAIR
					Case CHAR_STO:
						Select p\ThrowType
						Case 1: p\Animation\Animation = ANIMATION_THROW
						Case 2: p\Animation\Animation = ANIMATION_PUNCH3
						End Select
					Case CHAR_MAR:
						Select p\ThrowType
						Case 1: p\Animation\Animation = ANIMATION_THROW
						Case 2: p\Animation\Animation = ANIMATION_THROW2
						End Select
					Default: p\Animation\Animation = ANIMATION_THROW
				End Select
			Case ACTION_SHOOT
				Select p\Character
					Case CHAR_GAM,CHAR_BET:
						If p\Motion\Ground Then
							If p\SpeedLength#>2.35 Then p\Animation\Animation = ANIMATION_PUNCH2 Else p\Animation\Animation = ANIMATION_PUNCH1
						Else
							p\Animation\Animation = ANIMATION_PUNCHAIR
						EndIf
					Case CHAR_EGG,CHAR_CHW,CHAR_TMH:
						If p\Motion\Ground Then
							p\Animation\Animation = ANIMATION_PUNCH1
						Else
							p\Animation\Animation = ANIMATION_PUNCHAIR
						EndIf
					Default:
						If p\Motion\Ground Then
							p\Animation\Animation = ANIMATION_THROW
						Else
							p\Animation\Animation = ANIMATION_THROWAIR
						EndIf
				End Select
			Case ACTION_UPPERCUT,ACTION_CLAW,ACTION_PUDDLE
				p\Animation\Animation = ANIMATION_KICK
			Case ACTION_SWIPE
				Select p\Character
					Case CHAR_EME,CHAR_GME:
						Select p\CharacterMode
						Case CHAR_AMY: If p\Motion\Ground Then p\Animation\Animation = ANIMATION_KICK2 Else p\Animation\Animation = ANIMATION_KICKAIR2
						Default: p\Animation\Animation = ANIMATION_KICK3
						End Select
					Case CHAR_AMY,CHAR_WAV: If p\Motion\Ground Then p\Animation\Animation = ANIMATION_KICK Else p\Animation\Animation = ANIMATION_KICKAIR
					Case CHAR_MET,CHAR_MT3: p\Animation\Animation = ANIMATION_THROWAIR
					Default: p\Animation\Animation = ANIMATION_KICK
				End Select
			Case ACTION_SPRINT
				Select p\Character
					Case CHAR_BEA: p\Animation\Animation = ANIMATION_KICK
					Default: p\Animation\Animation = ANIMATION_KICKAIR
				End Select
			Case ACTION_PUNCH
				Select p\Character
					Case CHAR_SON,CHAR_SHA,CHAR_NAC,CHAR_PRS,CHAR_INF:
						p\Animation\Animation = ANIMATION_KICK
					Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3:
						p\Animation\Animation = ANIMATION_FORWARD
					Case CHAR_EME,CHAR_GME:
						Select p\CharacterMode
							Case CHAR_SON,CHAR_ESP: p\Animation\Animation = ANIMATION_KICK
							Default: p\Animation\Animation = ANIMATION_PUNCH1+p\PunchNumber-1
						End Select
					Case CHAR_EGG,CHAR_TMH:
						p\Animation\Animation = ANIMATION_PUNCH3
					Default:
						p\Animation\Animation = ANIMATION_PUNCH1+p\PunchNumber-1
				End Select
			Case ACTION_THRUST
				Select p\Character
					Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3:
						p\Animation\Animation = ANIMATION_FORWARD
					Default:
						p\Animation\Animation = ANIMATION_PUNCHAIR
				End Select
			Case ACTION_PSYCHO
				Select p\Character
					Case CHAR_SIL:
						If p\ThrowType=1 Then p\Animation\Animation = ANIMATION_THROW2 Else p\Animation\Animation = ANIMATION_THROW
					Default:
						p\Animation\Animation = ANIMATION_THROW
				End Select
			Case ACTION_GATLING:
				Select p\Character
					Case CHAR_TIA: p\Animation\Animation = ANIMATION_THROW
					Default: p\Animation\Animation = ANIMATION_PUNCH3
				End Select
			Case ACTION_TURN
				p\Animation\Animation = ANIMATION_THROWAIR
			Case ACTION_CARRY,ACTION_SHAKETREE
				p\Animation\Animation = ANIMATION_CARRYIDLE
				If (p\SpeedLength# > 0.00) Then p\Animation\Animation = ANIMATION_CARRYWALK
			Case ACTION_CARRYJUMP,ACTION_CARRYTHROWN,ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT
				p\Animation\Animation = ANIMATION_CARRYJUMP
			Case ACTION_TORNADO
				If Menu\Members=1 Then
					p\Animation\Animation = ANIMATION_CARRYJUMP
				Else
					Select p\No#
						Case 1: p\Animation\Animation = ANIMATION_CARRYIDLE
						Case 3: If pp(1)\HasVehicle=7 Then p\Animation\Animation = ANIMATION_HOLD1 Else p\Animation\Animation = ANIMATION_GRIND
						Default: p\Animation\Animation = ANIMATION_CARRYJUMP
					End Select
				EndIf
			Case ACTION_DIVE
				Select p\DoubleJump
					Case 0: p\Animation\Animation = ANIMATION_FLY
					Case 1: p\Animation\Animation = ANIMATION_GLIDE
				End Select
			Case ACTION_SLEET
				p\Animation\Animation = ANIMATION_GLIDE
			Case ACTION_HOLD,ACTION_GLIDER,ACTION_VICTORYHOLD
				If p\BumpedCloudTimer>0 Then p\Animation\Animation = ANIMATION_SPIN Else p\Animation\Animation = ANIMATION_HOLD1
			Case ACTION_HOLD2
				If p\BumpedCloudTimer>0 Then p\Animation\Animation = ANIMATION_SPIN Else p\Animation\Animation = ANIMATION_HOLD2
			Case ACTION_SKYDIVE
				p\Animation\Animation = ANIMATION_FORWARD
			Case ACTION_BELLYFLOP
				Select p\Character
					Case CHAR_CHO: p\Animation\Animation = ANIMATION_KICKAIR
					Default: p\Animation\Animation = ANIMATION_PUNCHAIR2
				End Select
		End Select

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		; If the animation changed, animate new
		If (p\Animation\Animation<>p\Animation\PreviousAnimation) Then
			Select p\Animation\Animation
				Case ANIMATION_IDLE,ANIMATION_VICTORY:
					Select p\RealCharacter
						Case CHAR_CHA: Animate(p\Objects\Mesh, 1, 0.1915, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 1, 0.0415, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_WALK:
					p\Animation\Speed# = (p\SpeedLength#/2.0)*d\Delta
					If p\Animation\Speed#<0.3405 Then p\Animation\Speed#=0.3405
					If p\Animation\Speed#>0.425 Then p\Animation\Speed#=0.425
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				Case ANIMATION_JOG:
					p\Animation\Speed# = (p\SpeedLength#/3.0)*d\Delta
					If p\Animation\Speed#<0.4420 Then p\Animation\Speed#=0.4420
					If p\Animation\Speed#>0.7833 Then p\Animation\Speed#=0.7833
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				Case ANIMATION_RUN:
					p\Animation\Speed# = (p\SpeedLength#/5.25)*d\Delta
					Select p\RealCharacter
						Case CHAR_SHA: p\Animation\Speed# = p\Animation\Speed#*0.7189866045428072
								If p\Animation\Speed#<0.4938 Then p\Animation\Speed#=0.4938
								If p\Animation\Speed#>0.9415300705299941 Then p\Animation\Speed#=0.9415300705299941
						Case CHAR_CRE: p\Animation\Speed# = p\Animation\Speed#*0.8594933022714036
								If p\Animation\Speed#<0.5903 Then p\Animation\Speed#=0.5903
								If p\Animation\Speed#>1.125526935264997 Then p\Animation\Speed#=1.125526935264997
						Default:
							If IsCharMod(p\RealCharacter) Then
								If MODCHARS_SKATES(p\RealCharacter-CHAR_MOD1+1)>0 Then
									p\Animation\Speed# = p\Animation\Speed#*0.7189866045428072
									If p\Animation\Speed#<0.4938 Then p\Animation\Speed#=0.4938
									If p\Animation\Speed#>0.9415300705299941 Then p\Animation\Speed#=0.9415300705299941
								Else
									If p\Animation\Speed#<0.6868 Then p\Animation\Speed#=0.6868
									If p\Animation\Speed#>1.3095238 Then p\Animation\Speed#=1.3095238
								EndIf
							Else
								If p\Animation\Speed#<0.6868 Then p\Animation\Speed#=0.6868
								If p\Animation\Speed#>1.3095238 Then p\Animation\Speed#=1.3095238
							EndIf
					End Select
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				Case ANIMATION_SPIN:
					Select p\Action
						Case ACTION_VICTORY,ACTION_JUMP,ACTION_LAND,ACTION_GRIND,ACTION_BUMPED,ACTION_GRABBED,ACTION_CANNON,ACTION_CANNON2,ACTION_CANNON3:
							If p\SpeedLength#>0.1 Then
							Animate(p\Objects\Mesh, 1, ((p\SpeedLength#+0.4531+(1/p\SpeedLength#)*0.1))/2.0, p\Animation\Animation, 10)
							Else
							Animate(p\Objects\Mesh, 1, ((p\SpeedLength#+0.4531+(1/0.1)*0.1))/2.0, p\Animation\Animation, 10)
							EndIf
						Case ACTION_CHARGE:
							Animate(p\Objects\Mesh, 1, ((3.7+0.4531+(1/3.7)*0.1))/2.0, p\Animation\Animation, 1)
						Case ACTION_STOMP:
							Animate(p\Objects\Mesh, 1, ((2.6+0.4531+(1/2.6)*0.1))/2.0, p\Animation\Animation, 10)
						Case ACTION_HOMING:
							Animate(p\Objects\Mesh, 1, ((1.5+0.4531+(1/1.5)*0.1))/2.0, p\Animation\Animation, 10)
						Case ACTION_DEBUG:
							Animate(p\Objects\Mesh, 1, ((0.4+0.4531+(1/0.4)*0.1))/2.0, p\Animation\Animation, 1)
						Default:
							If p\SpeedLength#>0.1 Then
							Animate(p\Objects\Mesh, 1, ((p\SpeedLength#+0.4531+(1/p\SpeedLength#)*0.1))/2.0, p\Animation\Animation, 1)
							Else
							Animate(p\Objects\Mesh, 1, ((p\SpeedLength#+0.4531+(1/0.1)*0.1))/2.0, p\Animation\Animation, 1)
							EndIf
					End Select
				Case ANIMATION_FALL:
					Select p\Action
						Case ACTION_HOP:
							Animate(p\Objects\Mesh, 1, p\SpeedLength#+0.4531, p\Animation\Animation, 10)
						Default:
							Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_FALLFAST:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_FORWARD:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_FORWARDRUN:
					p\Animation\Speed# = (p\SpeedLength#/5.25)*d\Delta
					If p\Animation\Speed#<0.6868 Then p\Animation\Speed#=0.6868
					If p\Animation\Speed#>1.3095238 Then p\Animation\Speed#=1.3095238
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, ANIMATION_FORWARD, 10)
				Case ANIMATION_UP:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_FLOAT:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_BRAKE:
					Animate(p\Objects\Mesh, 1, 1.0, p\Animation\Animation, 10)
				Case ANIMATION_HURT:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_DEAD:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_DEADFALL:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_FLY:
					Select p\Character
						Case CHAR_CRE,CHAR_TIK: Animate(p\Objects\Mesh, 1, 0.7/2.3, p\Animation\Animation, 10)
						Case CHAR_SIL,CHAR_MT3,CHAR_INF: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
						Case CHAR_BIG,CHAR_GAM,CHAR_BET: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Case CHAR_RAY,CHAR_GME: Animate(p\Objects\Mesh, 3, 0.7, p\Animation\Animation, 10)
						Case CHAR_JET: Animate(p\Objects\Mesh, 3, 0.54, p\Animation\Animation, 10)
						Case CHAR_WAV: Animate(p\Objects\Mesh, 1, 0.7/1.5, p\Animation\Animation, 10)
						Case CHAR_OME: Animate(p\Objects\Mesh, 3, 2.4, p\Animation\Animation, 10)
						Case CHAR_ROU,CHAR_EGR: Animate(p\Objects\Mesh, 1, 0.3/1.3, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 1, 0.7, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_GLIDE:
					Select p\Character
						Case CHAR_KNU,CHAR_TIK,CHAR_SHD,CHAR_EME,CHAR_COM: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Case CHAR_ROU: Animate(p\Objects\Mesh, 1, 0.3/1.3, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_HBO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
						Case CHAR_MAR: Animate(p\Objects\Mesh, 1, 1.4, p\Animation\Animation, 10)
						Case CHAR_HON: Animate(p\Objects\Mesh, 1, 0.84, p\Animation\Animation, 10)
						Case CHAR_JET: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 1, 0.3/1.15, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_CLIMBIDLE:
					Animate(p\Objects\Mesh, 1, 0.15, p\Animation\Animation, 10)
				Case ANIMATION_CLIMB:
					p\Animation\Speed# = (p\SpeedLength#/3.0)*d\Delta
					If p\Animation\Speed#<0.45 Then p\Animation\Speed#=0.45
					If p\Animation\Speed#>0.8 Then p\Animation\Speed#=0.8
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				Case ANIMATION_GRIND:
					Animate(p\Objects\Mesh, 1, 0.4420, p\Animation\Animation, 10)
				Case ANIMATION_GRINDFAST:
					Animate(p\Objects\Mesh, 1, 0.6868, p\Animation\Animation, 10)
				Case ANIMATION_CARRYIDLE:
					Select p\Action
						Case ACTION_SHAKETREE:
							Animate(p\Objects\Mesh, 1, 0.3405*1.75, p\Animation\Animation, 10)
						Case ACTION_BOARD,ACTION_BOARDJUMP,ACTION_BOARDDRIFT,ACTION_BOARDFALL,ACTION_BOARDTRICK,ACTION_TORNADO:
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
						Default:
							Select p\RealCharacter
								Case CHAR_CHA: Animate(p\Objects\Mesh, 1, 0.1915, p\Animation\Animation, 10)
								Default: Animate(p\Objects\Mesh, 1, 0.0415, p\Animation\Animation, 10)
							End Select
					End Select
				Case ANIMATION_CARRYWALK:
					p\Animation\Speed# = (p\SpeedLength#/2.0)*d\Delta
					If p\Animation\Speed#<0.3405 Then p\Animation\Speed#=0.3405
					If p\Animation\Speed#>0.425 Then p\Animation\Speed#=0.425
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				Case ANIMATION_CARRYJUMP:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_HOLD1,ANIMATION_HOLD2:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_THROW:
					Select p\Character
						Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 1.0125, p\Animation\Animation, 10)
						Case CHAR_BLA,CHAR_MAR: Animate(p\Objects\Mesh, 3, 0.69, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_EGR: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_NAC,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_MKN,CHAR_GME: Animate(p\Objects\Mesh, 3, 1.2038, p\Animation\Animation, 10)
						Case CHAR_TIA,CHAR_SHD: Animate(p\Objects\Mesh, 1, 0.69, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1.125, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_THROW2:
					Select p\Character
						Case CHAR_AMY,CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.69, p\Animation\Animation, 10)
						Case CHAR_MAR: Animate(p\Objects\Mesh, 3, 0.9675, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1.125, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_THROWAIR:
					Select p\Character
						Case CHAR_AMY,CHAR_ESP,CHAR_SHD,CHAR_EME,CHAR_GME: Animate(p\Objects\Mesh, 3, 0.45, p\Animation\Animation, 10)
						Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 1.0125, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH,CHAR_EGR: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_NAC,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_MET,CHAR_MT3: Animate(p\Objects\Mesh, 1, 0.425, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1.125, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICK:
					Select p\Character
						Case CHAR_TAI,CHAR_RAY,CHAR_TDL,CHAR_AMY,CHAR_WAV:
							If p\Animation\PreviousAnimation=ANIMATION_KICKAIR Then
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 0)
							Else
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
							EndIf
						Case CHAR_KNU,CHAR_ROU,CHAR_MKN,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.48, p\Animation\Animation, 10)
						Case CHAR_BLA,CHAR_MAR: Animate(p\Objects\Mesh, 1, 0.6, p\Animation\Animation, 10)
						Case CHAR_BEA: Animate(p\Objects\Mesh, 1, 0.84, p\Animation\Animation, 10)
						Case CHAR_CHO: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICKAIR:
					Select p\Character
						Case CHAR_SON,CHAR_SHA,CHAR_MIG,CHAR_EME,CHAR_PRS,CHAR_INF: Animate(p\Objects\Mesh, 1, 0.8, p\Animation\Animation, 10)
						Case CHAR_AMY,CHAR_WAV:
							If p\Animation\PreviousAnimation=ANIMATION_KICK Then
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 0)
							Else
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
							EndIf
						Case CHAR_ESP,CHAR_CHA,CHAR_MET,CHAR_MKN,CHAR_MT3,CHAR_GME: Animate(p\Objects\Mesh, 1, 1.5, p\Animation\Animation, 10)
						Case CHAR_NAC,CHAR_BEA: Animate(p\Objects\Mesh, 3, 0.98, p\Animation\Animation, 10)
						Case CHAR_JET: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Case CHAR_HON: Animate(p\Objects\Mesh, 1, 0.68, p\Animation\Animation, 10)
						Case CHAR_CHO: Animate(p\Objects\Mesh, 1, 0.15, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCH1:
					Select p\Character
						Case CHAR_KNU,CHAR_ROU,CHAR_MIG,CHAR_TIK,CHAR_BAR,CHAR_JET,CHAR_STO,CHAR_HON,CHAR_SHD,CHAR_HBO,CHAR_EME,CHAR_MKN,CHAR_TIA,CHAR_GME,CHAR_COM,CHAR_ESP,CHAR_AMY: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCH2:
					Select p\Character
						Case CHAR_KNU,CHAR_ROU,CHAR_MIG,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HON,CHAR_SHD,CHAR_HBO,CHAR_EME,CHAR_MKN,CHAR_TIA,CHAR_GME,CHAR_COM,CHAR_ESP: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_GAM,CHAR_BET: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCH3:
					Select p\Character
						Case CHAR_KNU,CHAR_CHO,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HBO,CHAR_MKN,CHAR_EGG,CHAR_COM,CHAR_TMH: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_VEC: Animate(p\Objects\Mesh, 1, 0.72, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCHAIR:
					Select p\Character
						Case CHAR_KNU,CHAR_ROU,CHAR_CHO,CHAR_TIK,CHAR_BAR,CHAR_JET,CHAR_STO,CHAR_HON,CHAR_SHD,CHAR_HBO,CHAR_EME,CHAR_MKN,CHAR_TIA,CHAR_GME,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_AMY,CHAR_WAV: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						Case CHAR_VEC: Animate(p\Objects\Mesh, 3, 0.35, p\Animation\Animation, 10)
						Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICK2:
					Select p\Character
						Case CHAR_EME:
							If p\Animation\PreviousAnimation=ANIMATION_KICKAIR2 Then
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 0)
							Else
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
							EndIf
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICKAIR2:
					Select p\Character
						Case CHAR_EME:
							If p\Animation\PreviousAnimation=ANIMATION_KICK2 Then
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 0)
							Else
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
							EndIf
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICK3:
					Select p\Character
						Case CHAR_EME,CHAR_GME: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCHAIR2:
					Select p\Character
						Case CHAR_BIG,CHAR_VEC: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Case CHAR_EME: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_GLIDE2:
					Select p\Character
						Case CHAR_OME: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
						Case CHAR_GME: Animate(p\Objects\Mesh, 1, 0.7, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 1, 0.3/1.15, p\Animation\Animation, 10)
					End Select
			End Select

			p\Animation\PreviousAnimation = p\Animation\Animation
			p\Animation\Speed#=0.0
			p\Animation\SpeedChangeBlockTimer=1.42*secs#
		End If

		If p\Animation\SpeedChangeBlockTimer>0 Then p\Animation\SpeedChangeBlockTimer=p\Animation\SpeedChangeBlockTimer-timervalue#
		Select p\Animation\Animation
			Case ANIMATION_WALK,ANIMATION_CARRYWALK:
				p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/2.0)*d\Delta
				If Not(p\Animation\SpeedChangeBlockTimer>0) Then SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, p\Animation\Animation)
			Case ANIMATION_JOG:
				p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/3.0)*d\Delta
				If Not(p\Animation\SpeedChangeBlockTimer>0) Then SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, p\Animation\Animation)
			Case ANIMATION_RUN:
				p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/5.25)*d\Delta
				If Not(p\Animation\SpeedChangeBlockTimer>0) Then
				Select p\RealCharacter
					Case CHAR_SHA: SetAnimTime(p\Objects\Mesh, p\Animation\Speed#*0.7189866045428072, p\Animation\Animation)
					Case CHAR_CRE: SetAnimTime(p\Objects\Mesh, p\Animation\Speed#*0.8594933022714036, p\Animation\Animation)
					Default: SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, p\Animation\Animation)
				End Select
				EndIf
			Case ANIMATION_FORWARDRUN:
				p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/5.25)*d\Delta
				If Not(p\Animation\SpeedChangeBlockTimer>0) Then SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, ANIMATION_FORWARD)
			Case ANIMATION_CLIMB:
				If p\UnderwaterFeet=0 Then
					p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/3.0)*d\Delta
					If Not(p\Animation\SpeedChangeBlockTimer>0) Then SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, p\Animation\Animation)
				EndIf
		End select
		
		; Update normals
		UpdateNormals(p\Objects\Mesh)
	End Function