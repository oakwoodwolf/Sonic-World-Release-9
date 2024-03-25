
Function Player_PlayRandomStep(p.tPlayer,firstframe,otherframe)
		If Player_FrameCheck(p,firstframe) Or Player_FrameCheck(p,otherframe) Then
			Select p\RealCharacter
				Case CHAR_SHA:
					If p\Animation\Animation = ANIMATION_RUN Or p\Animation\Animation = ANIMATION_MACHRUN Then Player_PlayRandomSwooshStep(p) Else Player_PlayRandomGroundStep(p)
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
			If p\UnderwaterFeet=1 Or p\WaterRunning=1 Then Player_PlayRandomUnderwaterStep(p)
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
		If (Not(firstframe=0 And otherframe=0)) And (Player_FrameCheck(p,firstframe) Or Player_FrameCheck(p,otherframe)) And p\UnderwaterFeet=1 Then ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
	End Function

;~IDEal Editor Parameters:
;~C#Blitz3D