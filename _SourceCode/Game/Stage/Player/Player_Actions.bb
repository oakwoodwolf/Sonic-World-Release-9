
	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_ChaoRace(p.tPlayer)

		p\Motion\Ground=False

		Player_SetSpeed(p,0)
		Player_SetSpeedY(p,0)

		p\Motion\Ground=False

		HideEntity(p\Objects\Mesh)

		Game\StartoutLock=0

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Common(p.tPlayer)
		
		p\Flags\CanBlazeTrick=False
		
		If Game\Interface\Shop=1 Or Game\Interface\SoundTest=1 Then Return
		
		Select Menu\Settings\RollBehaviour#
			Case 0
				Player_ActuallyCharge(p)
				
			Case 1
				If Input\Hold\ActionRoll Then p\Action=ACTION_ROLL
		End Select
		
		
		
		Player_ActuallyFall(p)

		Player_ActuallyJump(p)

		Player_SkillActions(p)
		
		If Game\QuickStepLock=1 Then
			If Input\Pressed\Left And (Not(p\QuickstepTimer>0)) Then
				EmitSmartSound(Sound_Quickstep,p\Objects\Entity)
				p\QuickstepDir=0
				p\QuickstepTimer=0.175*secs#
			EndIf
			If Input\Pressed\Right And (Not(p\QuickstepTimer>0)) Then
				EmitSmartSound(Sound_Quickstep,p\Objects\Entity)
				p\QuickstepDir=1
				p\QuickstepTimer=0.175*secs#
			EndIf
		EndIf

		

End Function

Function Player_Action_Animtest(p.tPlayer)
	
	
	
	
End Function

	; =========================================================================================================
	; =========================================================================================================
Function Player_Action_LightAttack_Initiate(p.tPlayer)
	
	If p\AroundEnemyTimer>0 And Game\Gameplay\GaugeEnergy=100  Then 
		p\Action=ACTION_LIGHTATTACK : Gameplay_SetGaugeEnergy(0) : p\LightAttackTimer=1*secs#
		p\Channel_LightAttack=PlaySmartSound(Sound_Lightattack)
		Player_PlaySpecialAttackVoice(p)
	EndIf 
	
End Function
Function Player_Action_LightAttack(p.tPlayer)
	;Create_AfterImage.tAfterImage(p\Objects\Mesh,p\Objects\Mesh,500,255,255,255,3,0,1,p\Animation\Animation,True,False)
	
	Player_ActuallyJump(p)
	
	If (Not(p\LightAttackTimer>0)) Then
		p\Action=ACTION_JUMPFALL
		Player_SetSpeed(p,1)
		StopChannel(p\Channel_LightAttack)
	EndIf
	
End Function
	Function Player_Action_Hop(p.tPlayer)
	
		Player_Action_Jump(p)

		If p\JumpHopTimer>0.1*secs# Then p\Action=ACTION_JUMP

	End Function

	; =========================================================================================================
	; =========================================================================================================

	
Function Player_Action_Jump(p.tPlayer)
	
	Player_JumpActions(p)
	
	If p\Bouncing=0 And (Not(p\Action=ACTION_DOUBLEJUMP)) Then
		If ((Player_IsPlayable(p) And Input\Hold\ActionJump=False) Or (p\No#<0)) And p\Motion\Speed\y# > p\Physics\JUMP_STRENGTH_VARIABLE# And (Not(p\JumpMayRiseTimer>0)) Then
			p\Motion\Speed\y# = p\Physics\JUMP_STRENGTH_VARIABLE#
		End If
		
		If Input\Pressed\ActionDrift And Player_IsPlayable(p) And p\JumpTimer>0.2*secs# Then p\Action=ACTION_JUMPFALL
	EndIf
	
	Player_ActuallyLand(p)
	
	If (Not(Animating(p\Objects\Mesh))) Then p\Action=ACTION_JUMPFALL
	
	If Menu\ThemeLoopSpin=1 Then
		If Player_IsSoundable(p) Then
			If ChannelPlaying(p\Channel_Spin)=False And p\JumpHopTimer>0.15*secs# And Player_CanCharSpin(p) Then p\Channel_Spin=EmitSmartSound(Sound_Spin,p\Objects\Entity)
		EndIf
	Else
		If Player_IsSoundable(p) And p\JumpHopTimer>0.4*secs# And p\JumpHopTimer<0.5*secs# And ChannelPlaying(p\Channel_Spin)=False And Player_CanCharSpin(p) Then
			p\Channel_Spin=EmitSmartSound(Sound_Spin,p\Objects\Entity)
		EndIf
	EndIf
	
	
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Land(p.tPlayer)
		
		Player_Action_Common(p)

		If (Not(Animating(p\Objects\Mesh))) Or p\SpeedLength#>1.65 Then p\Action=ACTION_COMMON

	End Function
Function Player_Action_PostHom(p.tPlayer)
	
	Player_ResetAirRestrictionStuff(p)
	
	If  p\Motion\Speed\y# < -1.5 Or (Not(Animating(p\Objects\Mesh))) Then p\Action=ACTION_JUMPFALL
	
	Player_JumpActions(p)
	
	Player_ActuallyLand(p)
	
End Function
	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Fall(p.tPlayer)

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_JumpFall(p.tPlayer)
		
		If (p\Action=ACTION_TRICK Or p\Action=ACTION_BLAZETRICK) And (Not(Animating(p\Objects\Mesh))) Then p\Action=ACTION_JUMPFALL
		
		Player_JumpActions(p)
		
		Player_Action_Fall(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Charge(p.tPlayer)
	
	
	Select p\Character
		Case CHAR_ROU
		Default
			ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_SMOKE, p\Objects\Mesh, 1, 0.075, p\SpeedLength#+1.25, 0, 1, 0.0375)
	End Select
	
	Player_ActuallyFall(p)
	
	
	
	
	Player_ActuallyJump(p)
	
	If (Not(Player_IsPlayable(p) And Input\Hold\ActionRoll)) Then
		Player_Action_Roll_Initiate(p,0.125)
	EndIf
	
End Function

	; =========================================================================================================
	; =========================================================================================================


	Function Player_Action_Roll_Initiate_Rival(p.tPlayer)
		p\Action = ACTION_ROLL
		p\Rival\Speed#=p\Physics\SPINDASH_SPEED#+Rand(0,4)/2.0
		EmitSmartSound(Sound_SpinDashRelease,p\Objects\Entity)
		Player_PlayAttackVoice(p)
End Function

	
Function Player_Action_Roll_Initiate(p.tPlayer, chargedelay#)
	p\Action = ACTION_ROLL
	If Player_IsSoundable(p) Then EmitSmartSound(Sound_SpinDashRelease,p\Objects\Entity)
	StopChannel(p\Channel_DashCharge)
	Player_PlayAttackVoice(p)
	If p\ChargeTimer<=0.1*secs# Then
		Player_SetSpeed(p,p\SpeedLength#+1.8)
	ElseIf p\ChargeTimer<=3.0*secs# Then
		Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#*(1+(p\ChargeTimer/secs#*0.84)))
	Else
		Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#*(2.75))
	EndIf
End Function
	Function Player_Action_Roll(p.tPlayer)

		If p\No#>1 And (Not(pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_ROLL)) Then p\Action=ACTION_COMMON

		If p\CurledUp=0 Then Player_ActuallyJump(p)
		
		Player_SkillActions(p)
		
		If (p\SpeedLength# < 0.05 Or (Input\Hold\ActionRoll=False And Menu\Settings\RollBehaviour#=1)) And p\Motion\Ground=True Then p\Action=ACTION_COMMON
		
		If Menu\Settings\RollBehaviour#=0 Then
			
			If Input\Pressed\ActionRoll Then
				If Player_IsPlayable(p) And p\Motion\Ground Then p\Action=ACTION_COMMON
			EndIf
		EndIf
		
		Select p\Character
			Case CHAR_BLA
				ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)
		End Select
			

	End Function

	; =========================================================================================================
	; =========================================================================================================
Function Player_Action_Panel(p.tPlayer)
	
	Player_ActuallyLand(p)
	
	Player_ResetAirRestrictionStuff(p)
	
	If p\Action=ACTION_PANEL2 Then 
		If p\PanelStayTimer>0 Then
			PositionEntity p\Objects\Entity, p\PanelX#, p\PanelY#, p\PanelZ#
		Else
			Player_SetSpeedY(p,0)
			EntityType(p\Objects\Entity,COLLISION_PLAYER)
			Game\CamLock=0
			p\Action=ACTION_FALL
			
		EndIf 
		
		
	EndIf 
	
	
	
	
End Function

Function Player_Action_Drift_Initiate(p.tPlayer)
	If p\SpeedLength#>2 Then 
		p\Action=ACTION_DRIFT
		Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#-0.4,True)
		Select(Rand(1,2))
			Case 1: p\DriftDirection=1
			Case 2: p\DriftDirection=-1
		End Select
	EndIf
	End Function

	Function Player_Action_Drift(p.tPlayer)
        
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)

		Player_ActuallyFall(p)

		Player_ActuallyJump(p)

		If (Not(Player_IsPlayable(p) And Input\Hold\ActionDrift)) Then p\Animation\Direction#=cam\Rotation\y#+180 : p\Action=ACTION_COMMON

		If (Player_IsPlayable(p) And Input\Hold\Left Or Input\Hold\MouseCamLeft) Then p\DriftDirection=-1
		If (Player_IsPlayable(p) And Input\Hold\Right Or Input\Hold\MouseCamRight) Then p\DriftDirection=1
		
		If Input\Hold\Left Or Input\Hold\Right Then
			p\HoldingDrift#=1.5
		Else
			p\HoldingDrift#=0.8
		EndIf
		
		
		If Abs(p\Rotation)<60 Then  Player_SetSpeed(p,p\PreviousSpeedLength#)
		
		If p\SpeedLength# < 0.05 Then p\Action=ACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Fwd(p.tPlayer)

		Player_ResetAirRestrictionStuff(p)

		If p\SpeedLength# < 0.5 And p\GoDestination=False Then p\Action=ACTION_JUMPFALL

		Player_JumpActions(p)

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Up(p.tPlayer)

		Player_ResetAirRestrictionStuff(p)

		If p\SpeedLength# < 0.5 And p\Motion\Speed\y# < -0.5 And p\GoDestination=False Then p\Action=ACTION_JUMPFALL

		Player_JumpActions(p)

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_JumpDash_Initiate(p.tPlayer)
		If p\JumpDashedOnce=0 Then
			p\JumpDashedOnce=1
			Player_PlayJumpActionVoice(p)
			Select p\Character
				Case CHAR_ESP: p\Action=ACTION_GLIDE
				Default: p\Action=ACTION_JUMPDASH
			End Select
			
			If p\No#=1 Then EmitSmartSound(Sound_HomingAttack,p\Objects\Entity)
			p\JumpDashTimer=0.35*secs#
			If IsCharMod(p\RealCharacter) Then
				Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
					Case JUMPACTION_DASH
						Player_SetSpeedY(p,0.65)
						Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#+1.9),True)
					Case JUMPACTION_THWOK
						Player_SetSpeedY(p,0.7)
						Player_SetSpeed(p,4.5)
				End Select
			Else
				Select p\Character
					Case CHAR_SHA,CHAR_SHN
						Player_SetSpeedY(p,0.7)
						Player_SetSpeed(p,4)
					Case CHAR_MET
						Player_SetSpeed(p,4)
					Default
						Player_SetSpeedY(p,0.65)
						Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#+1.9),True)
				End Select
			EndIf
			
			
			Player_FollowerHolding_EveryoneJumpDashes(p)
		EndIf
	End Function

	Function Player_Action_JumpDash_Initiate_Generic(p.tPlayer)
		p\Action=ACTION_JUMPDASH
		p\JumpDashTimer=0.35*secs#
		Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#+1.3),True)
	End Function

	Function Player_Action_JumpDash(p.tPlayer)
		
		If IsCharMod(p\RealCharacter) Then
			Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
				Case JUMPACTION_DASH
					Player_SetSpeedY(p,0)
				Case JUMPACTION_THWOK
					
			End Select
		Else
			If p\Character=CHAR_SHA Or p\Character=CHAR_SHN Then
				
			Else
				Player_SetSpeedY(p,0)
			EndIf 
		EndIf
		
		If (Not(p\JumpDashTimer>0)) Then p\Action=ACTION_JUMPFALL

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Homing(p.tPlayer)

		If (Not(p\HomingTimer>0)) And p\Flags\Targeter=0 Then p\Action=ACTION_FALL

		Select p\Character
			Case CHAR_AMY: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FLOWER, p\Objects\Entity)
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	
Function Player_Action_Fly_Initiate(p.tPlayer)
	If Not ( p\CanceledFlight=1 And (Not(p\FlyTimer>0)) ) Then
		Player_PlayJumpActionVoice(p)
		p\Action=ACTION_FLY
		If p\No#=1 Then EmitSmartSound(Sound_FlyStart,p\Objects\Entity)
		If p\CanceledFlight=0 Then p\FlyTimer=4*secs# : p\CanceledFlight=1
		If p\LevitatedOnce=0 Then p\FlyDistanceLimit=p\Objects\Position\y# : p\LevitatedOnce=1
		If IsCharMod(p\RealCharacter) Then
			Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
				Case JUMPACTION_SOAR
					Player_SetSpeedY(p,1.5)
			End Select
		Else
			Select p\Character
				Case CHAR_RAY
					Player_SetSpeedY(p,1.5)
			End Select
		EndIf
	EndIf
End Function

Function Player_Action_Fly(p.tPlayer)
	
	For c.tCamera = Each tCamera : c\MouseCameraTimer=0.5*secs# : Next
	p\BeenInTheAirTimer=0
	
	
	
	Player_ActuallyLand(p)
	
	Select p\SoarState
		Case 0
			If Input\PRESSED\ActionJump And p\Motion\Speed\y#<-0.5 Then 
				
				p\SoarStore#=Abs(p\Motion\Speed\y#)
				If p\SoarStore#>8 Then p\SoarStore#=8
				p\SoarStore=p\SoarStore*0.9
				p\RayAnimTimer=0.75*secs# : p\SoarState=1 
			EndIf 
		Case 1
			p\Motion\Speed\y#=p\Motion\Speed\y#+0.3
			Player_SetSpeed(p,p\SpeedLength#+0.055)
			If p\Motion\Speed\y#>p\SoarStore# Then p\RayAnimTimer=0.75*secs#: p\SoarState=0
		Case 2
			
	End Select
	
	
	If IsCharMod(p\RealCharacter) Then
		Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
			Case JUMPACTION_SOAR
				
			Default
				If (Not(p\FlyTimer>0)) Then p\Action=ACTION_FALL
				If Player_IsPlayable(p) And Input\Hold\ActionJump Then
					Player_FlyHeight(p)
				Else
					Player_SetSpeedY(p,-p\Physics\FLYDOWN_SPEED#,True)
				EndIf
		End Select
	Else
		Select p\Character
			Case CHAR_RAY
				
			Default
				If (Not(p\FlyTimer>0)) Then p\Action=ACTION_FALL
				If Player_IsPlayable(p) And Input\Hold\ActionJump Then
					Player_FlyHeight(p)
				Else
					Player_SetSpeedY(p,-p\Physics\FLYDOWN_SPEED#,True)
				EndIf
		End Select
		
	EndIf
	
	
	If p\No#=1 And Menu\ChaoGarden=0 And Player_IsPlayable(p) Then
		If Input\Pressed\ActionSkill1 Then
			Select p\Character
				Case CHAR_TAI:
					If Game\Gameplay\GaugeEnergy>=25 Then 
						Gameplay_SubstractGaugeEnergy(25)
						EmitSmartSound(Sound_BoostStart,p\Objects\Entity)
						p\JumpDashTimer=0.5*secs#
						Player_PlayJumpActionVoice(p)
					EndIf
				Case CHAR_CRE:
					Player_Action_Cheese_Initiate(p)
				Case CHAR_EGR:
					Player_Action_Shoot_Initiate(p)
			End Select
		EndIf
		
		If Input\Pressed\ActionSkill2 Then
			Select p\Character
				Case CHAR_TAI:
					Player_Action_Shoot_Initiate(p)
				Case CHAR_RAY
					If Input\Pressed\ActionSkill2 And p\CurledUp=0 Then 
						p\Action=ACTION_ROLL : p\CurledUp=1
						p\SoarState=0 : p\SoarStore=0
					EndIf
			End Select
		EndIf
	EndIf
	
	Select p\Character
		Case CHAR_TAI
			If p\JumpDashTimer>0 Then Player_SetSpeed(p,5,True) : Player_SetSpeedY(p,0)
	End Select
	
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Glide_Initiate(p.tPlayer)
		If (Not(p\GlideRestartTimer>0)) Then
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_GLIDE
			If p\No#=1 Then EmitSmartSound(Sound_GlideStart,p\Objects\Entity)
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#,True)
		EndIf
	End Function

Function Player_Action_Glide(p.tPlayer)
	
	p\BeenInTheAirTimer=0
	Player_ActuallyLand(p)
	Select p\Character
		Case CHAR_ESP
			Player_SetSpeedYUpDown(p,0)
			If (Not(p\JumpDashTimer>0)) Then p\Action=ACTION_JUMPFALL
		Default
			If p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump)) Then
				Player_SetSpeed(p,1.25,True)
				p\GlideRestartTimer=0.12*secs#/2.0
				p\Action=ACTION_JUMPFALL
			EndIf
			
			If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\GLIDEFALL_SPEED#,True)
	End Select
	
End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_DoubleJump_Initiate(p.tPlayer,wallkick=False)
	If p\DoubleJumped=0 Then
		p\AmySpinTimer=-0.5*secs#
		p\DoubleJumped=1
		Player_PlayJumpActionVoice(p)
		p\DoubleJump=0
		p\DoubleJumpTimer=0.105*secs#
		;p\Action = ACTION_DOUBLEJUMP
		Player_SetSpeedY(p,3)
		If p\Character=CHAR_MIG Then p\Action=ACTION_JUMP Else p\Action=ACTION_DOUBLEJUMP
		
		
		If p\Character=CHAR_GAM Then
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_CANNONSHOT)
			EmitSmartSound(Sound_EnemyShot4,p\Objects\Entity)
		EndIf
		
		If p\No#=1 Then 
			If IsCharMod(p\RealCharacter) Then
				If MODCHARS_DJUMPSOUND(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))=1
					EmitSound(p\Voice[Sound_JumpD],p\Objects\Entity)
				Else
					EmitSmartSound(Sound_DoubleJump,p\Objects\Entity)
				EndIf
			Else
				Select p\Character
					Case CHAR_BLA: If p\Underwater=0 Then EmitSmartSound(Sound_FireDash,p\Objects\Entity)
					Case CHAR_MET:EmitSmartSound(Sound_Dodge,p\Objects\Entity) : Player_PlayJumpActionVoice(p)
					Default: EmitSmartSound(Sound_DoubleJump,p\Objects\Entity)
				End Select
			EndIf
		EndIf
		Player_FollowerHolding_EveryoneDoubleJumps(p)
	EndIf
End Function

Function Player_Action_DoubleJumpSkill_Initiate(p.tPlayer,wallkick=False)
	If p\DoubleJumped=0 Then
		p\AmySpinTimer=-0.5*secs#
		p\DoubleJumped=1
		Player_PlayJumpActionVoice(p)
		p\DoubleJump=0
		p\DoubleJumpTimer=0.105*secs#
		p\Action = ACTION_DOUBLEJUMPS
		Player_SetSpeedY(p,3)
		If p\No#=1 Then 
			If IsCharMod(p\Character) Then
				If MODCHARS_DJUMPSOUND(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))=1
					EmitSound(p\Voice[Sound_JumpD],p\Objects\Entity)
				Else
					EmitSmartSound(Sound_DoubleJump,p\Objects\Entity)
				EndIf
			Else
				EmitSmartSound(Sound_DoubleJump,p\Objects\Entity)
			EndIf
		EndIf
		Player_FollowerHolding_EveryoneDoubleJumps(p)
	EndIf
End Function

	Function Player_Action_DoubleJump_Initiate_Generic(p.tPlayer,wallkick=False)
		p\DoubleJump=0
		
		p\DoubleJumpTimer=0.105*secs#
		p\Action = ACTION_DOUBLEJUMP
		p\Motion\Speed\x# = p\Motion\Speed\x#*1.12 : p\Motion\Speed\z# = p\Motion\Speed\z#*1.12
		Player_SetSpeedY(p,p\Physics\JUMP_STRENGTH#+0.3)
		p\Motion\Ground = False
		p\Motion\Align\x# = 0.0 : p\Motion\Align\y# = 1.0 : p\Motion\Align\z# = 0.0
	End Function

Function Player_Action_DoubleJump(p.tPlayer)
	
	If IsCharMod(p\RealCharacter) Then
		If p\Motion\Speed\y# < -0.5 Then p\Action=ACTION_JUMPFALL
	Else
		
		Select p\Character
			Case CHAR_AMY: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_HEARTS, p\Objects\Extra)
			Case CHAR_BLA: If p\Underwater=0 Then ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)
			Case CHAR_GAM: Player_JumpActions(p)
		End Select
		
		Select p\Character
			Case CHAR_AMY
				If p\DoubleJump=1 Then Player_JumpActions(p)
		End Select
		
		
		
		
		
		
		
		
		If p\Motion\Speed\y# < -0.5 Then 
			Select p\Character
				Case CHAR_AMY
					If p\DoubleJump=0 Then
						If Input\Hold\ActionJump Then 
							p\Motion\Speed\y#=-0.2 
						Else 
							If (Not(p\DoubleJumpTimer>0)) Then p\Action=ACTION_JUMPFALL
						EndIf 
					EndIf	
				Default
					p\Action=ACTION_JUMPFALL
			End Select
		EndIf 
		
		Player_ActuallyLand(p)
		
	EndIf
		


End Function

Function Player_Action_DoubleJumpSkill(p.tPlayer)
	
	Player_ActuallyLand(p)
	
	If p\Motion\Speed\y# < -0.5 Then p\Action=ACTION_JUMPFALL
	
End Function
	; =========================================================================================================
	; =========================================================================================================
Function Player_Action_BlazeTrick_Initiate(p.tPlayer)
	
	
	
	If p\TricksDone>3 Or p\Flags\CanBlazeTrick=False Then Return
	p\Action=ACTION_BLAZETRICK
	Player_SetSpeedY(p,2+(0.25*p\TricksDone))
	p\TricksDone=p\TricksDone+1
	Player_PlayGoodVoice(p)
	EmitSmartSound(Sound_Trick,p\Objects\Entity)
	Gameplay_AddScore(10*p\TricksDone)
	
	
End Function



	Function Player_Action_Levitate_Initiate(p.tPlayer)
		If p\LevitatedOnce=0 Or p\LevitationTimer>0 Then
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_LEVITATE
			If p\No#=1 Then EmitSmartSound(Sound_LevitateStart,p\Objects\Entity)
			p\Channel_Levitate=EmitSmartSound(Sound_Levitate2,p\Objects\Entity)
			If p\LevitatedOnce=0 Then p\LevitationTimer=3*secs# : p\LevitatedOnce=1
		EndIf
	End Function

	Function Player_Action_Levitate(p.tPlayer)

		Player_ActuallyLand(p)
		
		Player_SetSpeed(p,p\Physics\LEVITATION_SPEED#,True)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,0)

		If (Not(p\LevitationTimer>0)) Then p\Action=ACTION_JUMPFALL

		If p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump)) Then p\Action=ACTION_JUMPFALL : p\LevitationTimer=p\LevitationTimer-0.12*secs#

		Player_SkillActions2(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Stomp_Initiate(p.tPlayer, bounce=False)
		p\Action = ACTION_STOMP
		If bounce Then p\Flags\InStompAction=True Else p\Flags\InStompAction=False
		p\StompSaverTimer=0 : p\StompSaver#=p\Objects\Position\y#
		If p\No#=1 Then p\Channel_Stomp = EmitSmartSound(Sound_Stomp,p\Objects\Entity)
		Player_PlayAttackVoice(p)
		p\Motion\Speed\y# = p\Physics\STOMPFALL_SPEED#
	End Function

	Function Player_Action_Stomp_Initiate_Rival(p.tPlayer, bounce=False)
		If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP)) And p\Motion\Ground Then Player_ActuallyJump(p,True)
		p\Action = ACTION_STOMP
		If bounce Then
			If p\Bouncing<3 Then p\Bouncing=p\Bouncing+1
		Else
			p\Bouncing=0
		EndIf
		p\Channel_Stomp = EmitSmartSound(Sound_Stomp,p\Objects\Entity)
		p\Motion\Speed\y# = p\Physics\STOMPFALL_SPEED#
	End Function

	Function Player_Action_Bounce_Initiate(p.tPlayer)
		If p\No#=1 Then p\Channel_GroundLand=EmitSmartSound(Sound_Bounce2,p\Objects\Entity)
		p\ForceJumpTimer=0.05*secs#
	End Function

	Function Player_Action_StompSaver(p.tPlayer)
		If Abs(p\StompSaver#-p\Objects\Position\y#) < 1 Then
			p\StompSaverTimer=p\StompSaverTimer+timervalue#
			If p\StompSaverTimer>2*secs# Then
				p\Flags\Targeter=0
				Player_ActuallyJump(p,True)
			EndIf
		Else
			p\StompSaverTimer=0
		EndIf
		p\StompSaver#=p\Objects\Position\y#
	End Function

Function Player_Action_Stomp(p.tPlayer)
	
	Player_Action_StompSaver(p)
	
	If p\Motion\Ground Then
		p\StompBounceTimer=0.2*secs#
		Select p\Character
			Case CHAR_SIL
			;	Player_Action_Psycho_Initiate(p)
				If p\No#=1 Then p\Channel_GroundLand=EmitSmartSound(Sound_StompGround,p\Objects\Entity)
				Player_Land(p)
			Case CHAR_SON
				If Input\Hold\ActionRoll Then
					If Not((p\Collision\GroundType=COLLISION_WORLD_POLYGON_RAIL)) Then p\Bouncing=1 : Player_Action_Bounce_Initiate(p)
				Else
					If p\No#=1 Then p\Channel_GroundLand=EmitSmartSound(Sound_StompGround,p\Objects\Entity)
					Player_Land(p)
				EndIf
			Case CHAR_KNU,CHAR_OME,CHAR_AMY,CHAR_INF,CHAR_EGR
				If p\Flags\InStompAction Then 
					If p\No#=1 Then p\Channel_GroundLand=EmitSmartSound(Sound_StompGround,p\Objects\Entity)
					If (Not(p\ChaosStretchSpawnerTimer>0)) Then
						p\ChaosStretchSpawnerTimer=0.02*secs#
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Entity,1), EntityY(p\Objects\Entity,1), EntityZ(p\Objects\Entity,1), 0, p\Animation\Direction#-180, 0, BOMB_POWER)
						
					EndIf
					ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, (1+p\ScaleFactor#+0.25)*3, 0, 0, p\RealCharacter, 2)
				EndIf
				
				If p\No#=1 Then EmitSmartSound(Sound_KnuxStomp,p\Objects\Entity)
				Player_Land(p)
			Default
				If p\No#=1 Then p\Channel_GroundLand=EmitSmartSound(Sound_StompGround,p\Objects\Entity)
				Player_Land(p)
		End Select
		
		ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, 1+p\ScaleFactor#+0.25, 0, 0, p\RealCharacter, 2)
		p\Flags\Targeter=0
	EndIf
	
	If Not(p\Motion\Speed\y#<0) Then
		p\Action=ACTION_FULLFALL
		p\Flags\Targeter=0
	EndIf
	
	If p\Motion\Ground=False Then p\Motion\Speed\y# = p\Physics\STOMPFALL_SPEED#
	
	Player_JumpActions(p)
	
End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Hurt(p.tPlayer)
	
	Player_SetSpeed(p,-0.311)
	
	If p\Action=ACTION_HURT Then
		Player_ActuallyLand(p)
		
		Player_ActuallyJump(p)
		
	Else
		If p\Motion\Ground=True Then Player_Die(p)
	EndIf
	
	If Game\MachLock>0 Then p\Flags\HomingWasLockedTimer=0.25*secs#
	
	
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Die(p.tPlayer)

		Player_SetSpeed(p,0)

		If p\Motion\Ground=False Then p\Motion\Speed\y# = p\Physics\DIEFALL_SPEED#

		If (Not(p\DieTimer>0.1*secs#)) And Menu\ExitedAStage=0 Then 
			If Menu\MissionPerfect=1 Then
				Player_DieSpawn(p,True)
			Else
				Player_DieSpawn(p)
			EndIf
			
		EndIf
		

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Float(p.tPlayer)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\FLOATFALL_SPEED#,True)

		Player_ActuallyLand(p)

		Player_ActuallyJump(p)

		If Not(p\FloatTimer>0) Then p\Action=ACTION_FULLFALL

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_SlowGlide_Initiate(p.tPlayer)
		If (Not(p\GlideRestartTimer>0)) Then
			Player_PlayJumpActionVoice(p)
			If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,0.135)
			p\JumpActionRestrictTimer=0.4*secs#
			p\Action=ACTION_SLOWGLIDE
			If p\No#=1 Then
				Select p\Character
					Case CHAR_VEC: EmitSmartSound(Sound_GlideStart3,p\Objects\Entity) : EmitSmartSound(Sound_Gum,p\Objects\Entity) : EmitSmartSound(Sound_GlideStart2,p\Objects\Entity)
					Case CHAR_BIG: EmitSmartSound(Sound_GlideStart3,p\Objects\Entity) : EmitSmartSound(Sound_Umbrella,p\Objects\Entity)
					Case CHAR_TIA: EmitSmartSound(Sound_Parachute,p\Objects\Entity)
				End Select
			EndIf
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#*0.66,True)
			If p\Character=CHAR_VEC Then p\CreateGumBallTimer=0.11*secs# : p\ThrowABomb=0
		EndIf
	End Function

	Function Player_Action_SlowGlide(p.tPlayer)

		Player_SetSpeedY(p,-0.3,True)

		Player_ActuallyLand(p)

		If p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump)) Then
			Player_SetSpeed(p,1.25,True)
			p\GlideRestartTimer=0.12*secs#/2.0
			p\Action=ACTION_JUMPFALL
		EndIf

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\GLIDEFALL_SPEED#)

		If p\Character=CHAR_VEC And (Not(p\CreateGumBallTimer>0)) And p\ThrowABomb=0 Then
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Gum,1), EntityY(p\Objects\Gum,1), EntityZ(p\Objects\Gum,1), 0, p\Animation\Direction#-180, 0, BOMB_GUM, 1)
			p\ThrowABomb=1
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Sprint_Initiate(p.tPlayer, limit=1)
		If p\Motion\Ground Or (p\Motion\Ground=False And p\AirKickOnce=0) Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_SPRINT
			
			Select p\Character
				Case CHAR_SON,CHAR_MIG
					p\JumpDashTimer=0.567*secs#
					Player_SetSpeedY(p,-0.75)
					EmitSmartSound(Sound_Dash,p\Objects\Entity)
				Case CHAR_TAI
					Player_SetSpeed(p,2.25)
					Player_SetSpeedY(p,0.675)
				Case CHAR_BLA
					Player_SetSpeed(p,4.7,True)
					Player_SetSpeedY(p,0.675)
					EmitSmartSound(Sound_Dash,p\Objects\Entity)
				Case CHAR_INF
					EmitSmartSound(Sound_PsychoDash,p\Objects\Entity)
					p\JumpDashTimer=0.4834*secs#
					If p\Motion\Ground = False Then Player_SetSpeedY(p,0.4)
				Case CHAR_ROU
					EmitSmartSound(Sound_Dodge,p\Objects\Entity)
					p\Invisibility=1
					p\JumpDashTimer=0.4834*secs#
					If p\Motion\Ground = False Then Player_SetSpeedY(p,0.6)
					Player_SetSpeed(p,3,True)
				Case CHAR_SIL
					EmitSmartSound(Sound_PsychoDash,p\Objects\Entity)
					p\JumpDashTimer=0.45*secs#
					Player_SetSpeed(p,5.5)
					If p\Motion\Ground=False Then Player_SetSpeedY(p,0.5)
				Case CHAR_MET
					p\JumpDashTimer=0.4834*secs#
					
					EmitSmartSound(Sound_PsychoDash,p\Objects\Entity)
				Default:
					p\JumpDashTimer=0.4834*secs#
			End Select
			If p\Motion\Ground=False Then p\AirKickOnce=1
		EndIf
	End Function

Function Player_Action_Sprint(p.tPlayer)
	
	Select p\Character
		Case CHAR_SON,CHAR_INF,CHAR_MIG
			If p\Motion\Ground=False Then Player_SetSpeedYUpDownDiff(p,0.75,-1.75)
			Player_SetSpeed(p,p\Physics\SPRINT_SPEED#,True)
		Case CHAR_BEA,CHAR_HON:
			If p\Motion\Ground=False Then Player_SetSpeedYUpDownDiff(p,1.125,0.5)
			Player_SetSpeed(p,p\Physics\SPRINT_SPEED#*0.5,True)
		Case CHAR_TAI,CHAR_ROU,CHAR_MET,CHAR_BLA,CHAR_SIL
		Default:
			If p\Motion\Ground=False Then Player_SetSpeedYUpDown(p,0.05)
			Player_SetSpeed(p,p\Physics\SPRINT_SPEED#,True)
	End Select
	
	Select p\Character
		Case CHAR_ROU,CHAR_INF,CHAR_SIL
			If (Not(p\JumpDashTimer>0)) Then
				If p\Motion\Ground=False Then p\Action=ACTION_JUMPFALL Else p\Action=ACTION_COMMON
			EndIf
		Case CHAR_BLA,CHAR_MET
			If (Not(Animating(p\Objects\Mesh))) Then p\Action=ACTION_JUMPFALL
		Default
			If (Not(p\JumpDashTimer>0)) And p\Flags\Targeter=0 Then
				If p\Motion\Ground=False Then p\Action=ACTION_FULLFALL Else p\Action=ACTION_COMMON
			EndIf
	End Select
	
	Select p\Character
		Case CHAR_ROU,CHAR_INF,CHAR_MET,CHAR_BLA,CHAR_SIL
			If p\Motion\Ground=True Then Player_ActuallyJump(p)
		Default:	Player_ActuallyJump(p)
	End Select
	
	If p\Character=CHAR_SIL Then Player_JumpActions(p)
	
	If p\Character=CHAR_MET Then Player_SetSpeedY(p,2.7)
	
	
	
End Function
Function Player_Action_ScrewKick_Initiate(p.tPlayer)
	If p\Motion\Ground=True Then
		p\Motion\Ground=False : p\Motion\Speed\y#=3.4
		Player_PlayAttackVoice(p)
		EmitSmartSound(Sound_Uppercut,p\Objects\Entity)
		p\Action=ACTION_SCREWKICK
	Else
		If p\JumpDashedOnce=0 Then
			p\Action=ACTION_SPREAD
			p\JumpDashedOnce=1
		EndIf
		
	EndIf
	
	
	
End Function

Function Player_Action_ScrewKick(p.tPlayer)
	
	If p\Action=ACTION_SCREWKICK Then 
		Player_JumpActions(p)
	Else
		If Player_FrameCheck(p,6) Then EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
		Player_SetSpeedY(p,0)
	EndIf
	
	If (Not(Animating(p\Objects\Mesh))) Then p\Action=ACTION_JUMPFALL
	
End Function
	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Grind(p.tPlayer)
	
	
	If EntityPitch#(p\Objects\Entity)>-10 Then
	Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_GrindSpark),cam\Entity, p\Animation\Direction#, 2, p\No#)
	Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_GrindSpark),cam\Entity, p\Animation\Direction#, 2, p\No#)
	Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_GrindSpark),cam\Entity, p\Animation\Direction#, 2, p\No#)
EndIf
	
p\Motion\Speed\y# = -0.3
	
	If p\Collision\GroundType = COLLISION_WORLD_POLYGON Then p\Action=ACTION_COMMON
	
;	If Game\QuickStepLock=1 Then
;		If Input\Hold\Left And Input\Pressed\ActionJump And (Not(p\RailSwitchTimer>0)) Then
;			p\RailSwitchDir=0
;			p\RailSwitchTimer=0.45*secs#
;			EntityType(p\Objects\Entity,COLLISION_NONE) 
;			Player_ActuallyJump(p,True)
;			
;			
;			
;		EndIf
;		If Input\Hold\Right And Input\Pressed\ActionJump And (Not(p\RailSwitchTimer>0)) Then
;			p\RailSwitchDir=1
;			p\RailSwitchTimer=0.45*secs#
;			EntityType(p\Objects\Entity,COLLISION_NONE) 
;			Player_ActuallyJump(p,True)
;		EndIf
;	Else
;		Player_ActuallyJump(p)
;	EndIf
	
	Player_ActuallyJump(p)
	
	
	
	Player_ActuallyFall(p)
	
	If Not(p\Animation\Animation=ANIMATION_GRINDSWITCH) Then p\GrindSwitched=0
	
	If p\GrindTurn=0 Then p\GrindTurn=1
	Select p\GrindTurn
		Case 1
			Player_SetSpeed(p,p\Physics\REAL_GRIND_SPEED#,True)
			If Input\Hold\ActionRoll And (Not(p\GrindTurnRestrictTimer>0)) Then 
				p\GrindTurn=2
				p\GrindTurnTimer=0.5*secs#
				p\GrindTurnRestrictTimer=1*secs#
			EndIf 
		Case 2
			Player_SetSpeed(p,p\Physics\REAL_GRIND_SPEED#+1.5,True)
			If (Not(Input\Hold\ActionRoll)) And (Not(p\GrindTurnRestrictTimer>0)) Then 
				p\GrindTurn=1
				p\GrindTurnTimer=0.5*secs#
				p\GrindTurnRestrictTimer=1*secs#
			EndIf 
	End Select 
	
;		If ( (p\GrindTurn=1 And Player_IsPlayable(p) And Input\Hold\ActionRoll) Or (p\GrindTurn=2 And (Not(Player_IsPlayable(p) And Input\Hold\ActionRoll))) ) And (Not(p\GrindTurnRestrictTimer>0)) Then
;			Select p\GrindTurn
;				Case 1: p\GrindTurn=2
;				Case 2: p\GrindTurn=1
;			End Select
;			p\GrindTurnTimer=0.5*secs#
;			p\GrindTurnRestrictTimer=0.5*secs#
;			
;		EndIf		
	
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Climb_Initiate(p.tPlayer)
		Player_SetSpeed(p,0.15)
		Player_SetSpeedY(p,0)
		p\Action=ACTION_CLIMB
	End Function

	Function Player_Action_Climb(p.tPlayer)
		
		If p\Character=CHAR_ESP Or p\Character=CHAR_MIG Then Player_Stop(p)
		
		
		Player_ActuallyFall(p)

		If (Not(p\CanClimbTimer>0)) Then
			Player_ConvertGroundToAir(p)
			p\Motion\Ground = False
			p\Action=ACTION_JUMPFALL
		EndIf
		
		

		Player_ActuallyJump(p)

		If Abs(p\Rotation#)<17.5 Then p\Action=ACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Bumped_Initiate(p.tPlayer)
		If p\HasVehicle=0 And (Not(p\Action=ACTION_SKYDIVE)) Then
			p\Action = ACTION_BUMPED : p\BumpedTimer=0.5*secs#
		EndIf
	End Function

	Function Player_Action_BumpedJump_Initiate(p.tPlayer)
		If p\HasVehicle=0 And (Not(p\Action=ACTION_SKYDIVE)) Then
			p\JumpTimer=0
			p\Action=ACTION_JUMP : p\JumpMayRiseTimer=1.5*secs#
		EndIf
	End Function

	Function Player_Action_BumpedBounce_Initiate(p.tPlayer, speed#=2, cloud=0)
		Player_ConvertGroundToAir(p) : p\Motion\Ground = False
		Player_SetSpeedY(p,speed#)
		If Not cloud Then
			If p\No#=1 Then EmitSmartSound(Sound_Ninja,p\Objects\Entity)
		ElseIf p\HasVehicle=0 And (Not(p\Action=ACTION_SKYDIVE)) Then
			p\Action=ACTION_UP
		EndIf
		p\BumpedTimer=2*secs# : p\BumpedCloudTimer=p\BumpedTimer
	End Function

	Function Player_Action_Bumped(p.tPlayer)

		If p\BumpedCloudTimer>0 Then
			If p\Motion\Ground Then
				Player_ActuallyJump(p)
			Else
				Player_JumpActions(p)
			EndIf
		EndIf

		If (Not(p\BumpedTimer>0)) Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Hover_Initiate(p.tPlayer)
		Player_PlayJumpActionVoice(p)
		If p\HoveredOnce=0 Then
			Player_SetSpeedY(p,-p\Physics\HOVERFALL_SPEED#*2.15)
			p\HoveredOnce=1
		EndIf
		p\Action=ACTION_HOVER
	End Function
				
	Function Player_Action_Hover(p.tPlayer)

		Player_ActuallyLand(p)

		Select p\Character
			Case CHAR_OME,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH:
				Player_SkillActions(p)
		End Select
		
		Player_SetSpeed(p,p\Physics\HOVER_SPEED#,True)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\HOVERFALL_SPEED#*0.3455,True)

		If p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump)) Then p\Action=ACTION_JUMPFALL

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Grabbed(p.tPlayer)

		p\WasGrabbed=1
		p\WasGrabbedTimer=1.275*secs#
		
		Player_SetSpeed(p,0)
		Player_SetSpeedY(p,0)

		If Input\Pressed\Up Or Input\Pressed\Down Or Input\Pressed\Left Or Input\Pressed\Right Or Input\Pressed\ActionJump Or Input\Pressed\ActionRoll Then
			If Rand(1,8)=1 Then p\Action=ACTION_FALL
		EndIf

		If (Not(p\IsGrabbedTimer>0)) Then p\Action=ACTION_FALL

	End Function

	; =========================================================================================================
	; =========================================================================================================
Function Player_Action_Cheese_Initiate(p.tPlayer,mode=0)
	
	If p\CheeseShieldTimer>0 Then Return
	
	Select mode
		Case 0
			
			If   (Not(Game\CheeseTimer>0)) And (Not(p\CheeseRestrictTimer>0))  Then p\CheeseMode=0 :  Game\CheeseTimer=0.5*secs# : p\CheeseAttackedCount=p\CheeseAttackedCount+1
			
		Case 1
			If Game\Gameplay\GaugeEnergy>=25 Then p\CheeseShieldTimer=7*secs# : Gameplay_SubstractGaugeEnergy(25)
		Case 2
			If   (Not(Game\CheeseTimer>0)) And (Not(p\CheeseRestrictTimer>0))  Then p\CheeseMode=1 : Game\CheeseTimer=0.5*secs# : p\CheeseAttackedCount=p\CheeseAttackedCount+1 
			Gameplay_SubstractGaugeEnergy(50)
	End Select
		
	
	
End Function
	Function Player_Action_Throw_Initiate(p.tPlayer,throwtype=1,limit=2)
		
	End Function

	Function Player_Action_Drop_Initiate(p.tPlayer,throwtype=1,limit=4)
		If (Not(p\ThrowTimer>0)) And p\BombThrown<limit Then
			p\ThrowTimer=0.37*secs#
			Player_PlayAttackVoice(p)
			p\ThrowType=throwtype
			Select p\Character
				Case CHAR_TAI: EmitSmartSound(Sound_Throw,p\Objects\Entity)
				Case CHAR_CHA: EmitSmartSound(Sound_Flower,p\Objects\Entity)
			End Select
			Select p\Character
				Case CHAR_TAI:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#-3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_RING, -1)
				Case CHAR_ROU:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#-3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_HEART, -1)
				Case CHAR_CHA:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#-3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_FLOWER, -1)
			End Select
			p\BombThrown=p\BombThrown+1
		EndIf
	End Function

	Function Player_Action_Throw(p.tPlayer)

		

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_LightDash(p.tPlayer)

		If (Not(p\LightDashTimer>0)) Or (Not(p\RingDashStopTimer>0)) Then
			p\Action=ACTION_JUMPFALL
			Player_SetSpeed(p,2.3)
		EndIf
		
		;Create_AfterImage.tAfterImage(p\Objects\Mesh,p\Objects\Mesh,500, Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)],1,1,1,True,True,1)
		
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Shoot_Initiate(p.tPlayer,mode=1)
	If p\JumpDashedOnce=1 Then Return
	Select p\Character
		Case CHAR_OME
			If p\HasShotOnce=2 Then Return
		Case CHAR_SHA
			If p\Shots=2 Then Return
	End Select
	
	If (Not(p\ShootCooldownTimer>0)) Then
		p\ShootShotTimer=0.1*secs#
		p\ShootType=mode
		If (Not(p\Action=ACTION_TORNADO)) Then
			Player_PlayAttackVoice(p)
			If p\Action=ACTION_HOVER Or p\Action=ACTION_FLY Then p\Action=ACTION_SHOOTHOVER Else p\Action=ACTION_SHOOT
		Else
			p\TornadoShoot=1
		EndIf
		
		Select p\Character
			Case CHAR_SHN
				If mode=3 Then p\HasShotOnce=0
			Case CHAR_TAI,CHAR_EGR,CHAR_SIL
				Player_SetSpeedY(p,0.5)
		End Select
		
	EndIf	
	
End Function

Function Player_Action_Shoot(p.tPlayer)
	
	If p\ShootType=2 And p\Character=CHAR_OME Then
	Else
		If p\Motion\Ground Then
			Player_ActuallyJump(p)
		Else
			Player_ActuallyLand(p)
		EndIf
	EndIf
	
	Select p\Character
		Case CHAR_TAI,CHAR_ESP,CHAR_SIL
			If Player_FrameCheck(p,4) And p\HasShotOnce=0 Then
				Player_Action_Shoot_NormalShot(p)
				If p\SpeedLength#<1 Then Player_SetSpeed(p,-0.5)
				p\HasShotOnce=1
				If p\Character=CHAR_SIL Then 
					
					ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1)
				EndIf
			EndIf
		Case CHAR_EGR
			If Player_FrameCheck(p,4) And p\HasShotOnce=0 Then
				Player_Action_Shoot_NormalShot(p)
				p\HasShotOnce=1
			EndIf
		Case CHAR_INF
			Select p\ShootType
				Case 1
					If Player_FrameCheck(p,6) And p\HasShotOnce=0 Then
						Player_Action_Shoot_NormalShot(p)
						p\HasShotOnce=1
					EndIf
				Case 2
					If Player_FrameCheck(p,6) And p\HasShotOnce=0 Then
						Player_Action_Shoot_NormalShot(p)
						p\HasShotOnce=1
					EndIf
			End Select
		Case CHAR_SHA
			Player_SetSpeedY(p,0)
			If Player_FrameCheck(p,4) And p\HasShotOnce=0 Then
				Player_Action_Shoot_NormalShot(p)
				p\Shots=p\Shots+1
				p\HasShotOnce=1
			EndIf
		Case CHAR_SHN
			Select p\ShootType
				Case 1
					If Player_FrameCheck(p,4) And p\HasShotOnce=0 Then
						Player_Action_Shoot_NormalShot(p)
						Gameplay_SubstractGaugeEnergy(50)
						If p\SpeedLength#<1 Then Player_SetSpeed(p,-1)
						p\HasShotOnce=1
					EndIf
					If p\Motion\Ground=False Then Player_SetSpeedY(p,0)
				Case 2
					If (Not(p\ShootShotTimer>0)) Then
						p\ShootShotTimer=0.15*secs#
						Player_SetSpeed(p,-0.2)
						EmitSmartSound(Sound_Gatling,p\Objects\Entity)
						Gameplay_SubstractGaugeEnergy(5)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra2,1), EntityY(p\Objects\Extra2,1), EntityZ(p\Objects\Extra2,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET2, 1)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra3,1), EntityY(p\Objects\Extra3,1), EntityZ(p\Objects\Extra3,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET2, 2)
					EndIf
					If (Not(Input\Hold\ActionSkill3)) Or Game\Gameplay\GaugeEnergy<=0 Then
						p\ShootShotTimer=0
						If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
					EndIf
				Case 3
					If Player_FrameCheck(p,4) And p\HasShotOnce= 0 Then
						Player_Action_Shoot_NormalShot(p)
						p\HasShotOnce=1
					EndIf
					Player_Stop(p)
			End Select
		Case CHAR_OME
			Select p\ShootType
				Case 1 ; omega shot
					Player_SetSpeedY(p,0)
					If Player_FrameCheck(p,2) And p\HasShotOnce<2 Then
						Player_Action_Shoot_NormalShot(p)
						p\HasShotOnce=p\HasShotOnce+1
					EndIf
				Case 2 ; flamethrower
					If Input\Pressed\ActionJump And p\Motion\Ground=True Then
						p\Motion\Ground=False : p\Motion\Speed\y#=2
						ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, 1+p\ScaleFactor#+0.25, 0, 0, p\RealCharacter, 1)
						Player_JumpSound(p)
						Player_PlayJumpVoice(p)
					EndIf
					
					If Input\Hold\ActionSkill2 Then
						
						If ChannelPlaying(p\Channel_Fire)=False Then p\Channel_Fire=EmitSmartSound(Sound_Flamethrower,p\Objects\Entity)
						
						If (Not(p\ShootShotTimer>0)) Then
							Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_FLAMETHROW)
							p\ShootShotTimer=0.1*secs#
							Gameplay_SubstractGaugeEnergy(2)
						EndIf
					Else
						StopChannel(p\Channel_Fire)
						
					EndIf
				Case 3 ; gatling
					
					If (Not(p\ShootShotTimer>0)) Then
						p\ShootShotTimer=0.15*secs#
						Player_SetSpeed(p,-0.2)
						EmitSmartSound(Sound_Gatling,p\Objects\Entity)
						Gameplay_SubstractGaugeEnergy(5)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra2,1), EntityY(p\Objects\Extra2,1), EntityZ(p\Objects\Extra2,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET2, 1)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra,1), EntityY(p\Objects\Extra,1), EntityZ(p\Objects\Extra,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET2, 2)
					EndIf
					If (Not(Input\Hold\ActionSkill3)) Or Game\Gameplay\GaugeEnergy<=0 Then
						p\ShootShotTimer=0
						If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
					EndIf
			End Select
	End Select
	
	Select p\Character
		Case CHAR_OME
			If (Not(Animating(p\Objects\Mesh))) Then
				p\Action=ACTION_JUMPFALL
			EndIf
			If p\ShootType=2 And ((Not(Input\Hold\ActionSkill2)) Or Game\Gameplay\GaugeEnergy<=0) Then 
				If p\Motion\Ground=True Then
					p\Action=ACTION_COMMON
				Else
					p\Action=ACTION_FALL
				EndIf
			EndIf 
		Default
			If (Not(Animating(p\Objects\Mesh))) Then
				p\HasShotOnce=0
				If p\Motion\Ground Then
					p\Action=ACTION_COMMON
				Else
					
					
					If p\Action=ACTION_SHOOTHOVER Then
						
						p\Action=ACTION_FLY
					Else
						
						p\Action=ACTION_JUMPFALL
					EndIf
					
				EndIf
			EndIf
	End Select
	
	
	
	
	
End Function

	Function Player_Action_Shoot_AimBegin(p.tPlayer)
		If p\Aiming=0 Then p\Aiming=1 : p\JustStartedAimingTimer=0.25*secs#
	End Function

	Function Player_Action_Shoot_Shot(p.tPlayer)
		Select p\Character
			Case CHAR_GAM,CHAR_EGG,CHAR_CHW,Player_Action_Shoot_AimShot(p)
			Case CHAR_BET: Player_Action_Shoot_AimlessAimShot(p)
			Default: Player_Action_Shoot_NormalShot(p)
		End Select
	End Function

Function Player_Action_Shoot_NormalShot(p.tPlayer)
	Select p\Character
		Case CHAR_EGR:
			Select p\ShootType
				Case 1
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_ORB, -1)
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_ORB)
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_ORB, 1)
					
				Case 2
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_ROCKET)
					Gameplay_SubstractGaugeEnergy(100)
			End Select		
		Case CHAR_ESP:
			Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+2.3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BLADE)
		Case CHAR_TAI,CHAR_OME
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_CANNONSHOT)
		Case CHAR_SHN
			If p\ShootType=1 Then
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SPEAR)
				
			Else
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SHOT)
				
			EndIf
		Case CHAR_SIL
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_KNIFE)
			
		Case CHAR_SHA
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SPEAR)
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-165, 0, BOMB_SPEAR)
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-195, 0, BOMB_SPEAR)
		Case CHAR_INF
			Select p\ShootType
				Case 1
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-210, 0, BOMB_CUBETRAIL)
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-195, 0, BOMB_CUBETRAIL)
					
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_CUBETRAIL)
					
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-165, 0, BOMB_CUBETRAIL)
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-150, 0, BOMB_CUBETRAIL)
				Case 2
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_NULLIFY, 0)
			End Select	
			
			
		Default:
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SHOT)
	End Select
	
	p\ShootCooldownTimer=0.2*secs#
	
	Select p\Character
		Case CHAR_SHA: EmitSmartSound(Sound_SpearShoot,p\Objects\Entity)
		Case CHAR_INF: EmitSmartSound(Sound_RocketGo+Rand(1,6),p\Objects\Entity)
		Case CHAR_ESP: EmitSmartSound(Sound_Kunai,p\Objects\Entity)
		Case CHAR_SIL : EmitSmartSound(Sound_PsychoThrow,p\Objects\Entity)
		Default: EmitSmartSound(Sound_EnemyShot,p\Objects\Entity)
	End Select
	
	Select p\Character
		Case CHAR_ESP
			Gameplay_SubstractGaugeEnergy(5)
		Case CHAR_SIL
			Gameplay_SubstractGaugeEnergy(10)
	End Select
	
End Function

	Function Player_Action_Shoot_AimShot(p.tPlayer)
		If Player_IsPlayable(p) And p\Aiming=2 And (Not(p\JustStartedAimingTimer>0)) Then
			foundtargetonce=False
			For o.tObject = Each tObject
				If o\AimedAt=1 Then
					If foundtargetonce Then
						If EntityDistance(o\Entity,p\Objects\Entity)<EntityDistance(p\Objects\ScannerTarget\Entity,p\Objects\Entity) Then p\Objects\ScannerTarget=o
					Else
						p\Objects\ScannerTarget=o
						foundtargetonce=True
					EndIf
				EndIf
			Next
			If foundtargetonce Then
				Select p\Character
				Case CHAR_CHW:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra2,1), EntityY(p\Objects\Extra2,1), EntityZ(p\Objects\Extra2,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3, 0, True, p\Objects\ScannerTarget\Position\x#, p\Objects\ScannerTarget\Position\y#, p\Objects\ScannerTarget\Position\z#)
				Case CHAR_GAM
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET4, 0, True, p\Objects\ScannerTarget\Position\x#, p\Objects\ScannerTarget\Position\y#, p\Objects\ScannerTarget\Position\z#)
					
				Default:
					If (Not(p\Action=ACTION_TORNADO)) Then
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3, 0, True, p\Objects\ScannerTarget\Position\x#, p\Objects\ScannerTarget\Position\y#, p\Objects\ScannerTarget\Position\z#)
					Else
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\VehicleShoot,1), EntityY(p\Objects\VehicleShoot,1), EntityZ(p\Objects\VehicleShoot,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3, 0, True, p\Objects\ScannerTarget\Position\x#, p\Objects\ScannerTarget\Position\y#, p\Objects\ScannerTarget\Position\z#)
					EndIf
				End Select
				p\Objects\ScannerTarget\AimedAt=0
			EndIf
			p\ShootCooldownTimer=0.2*secs#
		Else
			Select p\Character
			Case CHAR_CHW:
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra2,1), EntityY(p\Objects\Extra2,1), EntityZ(p\Objects\Extra2,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3)
			Case CHAR_GAM
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET4)
			Default:
				If (Not(p\Action=ACTION_TORNADO)) Then
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3)
				Else
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\VehicleShoot,1), EntityY(p\Objects\VehicleShoot,1), EntityZ(p\Objects\VehicleShoot,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3)
				EndIf
			End Select
			p\ShootCooldownTimer=0.05*secs#
		EndIf
		EmitSmartSound(Sound_Shotgun2,p\Objects\Entity)
		p\ForceShotWalkTimer=0.24*secs#
	End Function

	Function Player_Action_Shoot_AimlessAimShot(p.tPlayer)
		Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3)
		Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3)
		p\ShootCooldownTimer=0.09*secs#
		EmitSmartSound(Sound_Shotgun2,p\Objects\Entity)
		p\ForceShotWalkTimer=0.24*secs#
	End Function

	; =========================================================================================================
	; =========================================================================================================
Function Player_Action_DemoDash_Initiate(p.tPlayer)
	Player_PlayAttackVoice(p)
	
	p\DemoDashTimer=1.5*secs# : p\Action=ACTION_DEMODASH
End Function

Function Player_Action_DemoDash(p.tPlayer)
	If Input\Hold\ActionSkill3=False And p\Motion\Ground=True And p\DemoDash=0 Then
		;Player_SetSpeed(p,0.5*p\Physics\UNDERWATERTRIGGER#,True)
		EmitSmartSound(Sound_Uppercut,p\Objects\Entity)
		p\Motion\Ground=False
		
		Player_SetSpeedY(p,p\DemoDashTimer/secs#*p\Physics\UNDERWATERTRIGGER#)
		Player_SetSpeed(p,p\DemoDashTimer/secs#+1)
		p\DemoDash=1
	EndIf
	
	If p\Motion\Ground=True And p\DemoDash=1 Then Player_Land(p)
	
	Player_ActuallyJump(p)
	
	
	
End Function
Function Player_Action_Punch_Initiate(p.tPlayer,limit=1)
	Select p\Character
		Case CHAR_OME
			If p\PunchTimer>0.4*secs# Then Return
	End Select
	If p\AirKickOnce<limit Then
		Player_PlayAttackVoice(p)
		If p\Motion\Ground Then
			p\Action=ACTION_PUNCH
			Select p\Character
				Case CHAR_SON,CHAR_RAY
					p\PunchNumber=1
					EmitSmartSound(Sound_SpinKick,p\Objects\Entity)
				Case CHAR_KNU,CHAR_SHA,CHAR_OME,CHAR_MET,CHAR_SHN
					p\PunchTimer=0.75*secs#
					
					p\PunchNumber=p\PunchNumber+1
					If p\PunchNumber>3 Then p\PunchNumber=1
					If p\Character=CHAR_KNU And p\PunchNumber=3 Then Player_JumpSound(p)
					EmitSmartSound(Sound_PunchBig+Rand(1,2),p\Objects\Entity)
					
				Case CHAR_ROU
					p\PunchTimer=0.75*secs#
					p\PunchNumber=p\PunchNumber+1
					If p\PunchNumber>2 Then p\PunchNumber=1
					EmitSmartSound(Sound_PunchBig+Rand(1,2),p\Objects\Entity)
				Case CHAR_AMY: 
					p\PunchNumber=1
					EmitSmartSound(Sound_Hammer,p\Objects\Entity)
				Default
					p\PunchNumber=p\PunchNumber+1
			End Select
		Else
			p\Action=ACTION_THRUST
			p\PunchNumber=1
			Select p\Character
				Case CHAR_ROU,CHAR_KNU,CHAR_BLA,CHAR_RAY,CHAR_MIG,CHAR_TAI
					Player_SetSpeed(p,2.5,True)
					Player_SetSpeedY(p,0.75)
				Case CHAR_SHN
					Player_SetSpeed(p,2.25,True)
					Player_SetSpeedY(p,0.75)
				Case CHAR_AMY
					EmitSmartSound(Sound_Hammer,p\Objects\Entity)
					Player_SetSpeedY(p,0.75,True)
					Player_SetSpeed(p,0.5,True)
			End Select
		EndIf
		
		If p\Motion\Ground=False Then p\AirKickOnce=p\AirKickOnce+1 : 
	EndIf
End Function

Function Player_Action_Punch(p.tPlayer)
	
	Select p\Action
		Case ACTION_PUNCH:
			Player_ActuallyFall(p)
			Player_ActuallyJump(p)
			If (Not(p\PunchRestrictTimer>0)) Then
				Select p\Character
					Case CHAR_KNU,CHAR_SHA,CHAR_OME,CHAR_MET
						If p\PunchNumber<3 Then Player_SkillActions(p)
					Case CHAR_ROU
						Player_SkillActions(p)
				End Select
			EndIf
		Case ACTION_THRUST:
			Player_ActuallyLand(p)
	End Select
	
	Select p\Character
		Case CHAR_SON,CHAR_OME,CHAR_INF,CHAR_RAY
			Player_SetSpeed(p,0.357,True)
		Case CHAR_SHA,CHAR_ROU,CHAR_KNU,CHAR_SHN,CHAR_OME,CHAR_BLA,CHAR_TAI
			Player_SetSpeed(p,0.125,True)
		Case CHAR_AMY
			Player_SetSpeed(p,0.1,True)
		Case CHAR_MET
			If p\PunchNumber=3 Then
				If Player_FrameCheck(p,7) Then
					Player_PlayAttackVoice(p)
					p\PunchNumber=4
				EndIf
				Player_SetSpeed(p,0)
			ElseIf p\PunchNumber=4 Then
				Player_SetSpeed(p,3.4,True)
			Else
				
				Player_SetSpeed(p,0.1,True)
			EndIf 
		Default:
			If p\Motion\Ground And p\PunchNumber=3 Then
				Player_SetSpeed(p,1.3*p\Physics\UNDERWATERTRIGGER#,True)
			Else
				Player_SetSpeed(p,0.8*p\Physics\UNDERWATERTRIGGER#,True)
			EndIf
	End Select
	
	;when anim is done
	If (Not(Animating(p\Objects\Mesh))) And (p\Flags\Targeter=0 Or p\Character=CHAR_AMY) Then
		If p\Motion\Ground Then
			p\Action=ACTION_COMMON
			Select p\Character
				Case CHAR_KNU,CHAR_SHA,CHAR_ROU
					p\PunchTimer=0.5*secs#
				Case CHAR_AMY
					ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Extra, 6, 0, 0, p\RealCharacter, 2)
					If (Not(p\ChaosStretchSpawnerTimer>0)) Then
						p\ChaosStretchSpawnerTimer=0.02*secs#
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra,1), EntityY(p\Objects\Extra,1), EntityZ(p\Objects\Extra,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
						
					EndIf
					ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_HEARTS, p\Objects\Extra)
			End Select
		Else
			p\Action=ACTION_JUMPFALL
		EndIf
	EndIf
	
	;during anim
	Select p\Character
		Case CHAR_KNU
			If p\PunchNumber=3 And Player_FrameCheck(p,10) Then
				EmitSmartSound(Sound_StompGround,p\Objects\Entity)
				EmitSmartSound(Sound_KnuxStomp,p\Objects\Entity)
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_SMOKE, p\Objects\Mesh, 1+p\ScaleFactor#+0.025, 0.2, 3, 0, 5)
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, (1+p\ScaleFactor#+0.25), 0, 0, p\RealCharacter, 2)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Entity,1), EntityY(p\Objects\Entity,1), EntityZ(p\Objects\Entity,1), 0, p\Animation\Direction#-90, 0, BOMB_FIREBALL)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Entity,1), EntityY(p\Objects\Entity,1), EntityZ(p\Objects\Entity,1), 0, p\Animation\Direction#-180, 0, BOMB_FIREBALL)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Entity,1), EntityY(p\Objects\Entity,1), EntityZ(p\Objects\Entity,1), 0, p\Animation\Direction#-270, 0, BOMB_FIREBALL)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Entity,1), EntityY(p\Objects\Entity,1), EntityZ(p\Objects\Entity,1), 0, p\Animation\Direction#-360, 0, BOMB_FIREBALL)
			EndIf
		Case CHAR_AMY
			If Player_FrameCheck(p,5) And p\Motion\Ground=True Then 
				If (Not(Input\Hold\ActionSkill1)) Then EmitSmartSound(Sound_Grab,p\Objects\Entity)
				
				If Input\Hold\ActionSkill1 And p\SpeedLength# > 2.85 Then 
					p\DoubleJump=1 : p\Motion\Ground=False : p\Motion\Speed\y#=4 : p\Action=ACTION_DOUBLEJUMP 
				EndIf
			EndIf 
		Case CHAR_OME
			If p\PunchNumber=3 And Player_FrameCheck(p,4) Then 
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-170, 0, BOMB_FLAMETHROW)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_FLAMETHROW)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-190, 0, BOMB_FLAMETHROW)
				EmitSmartSound(Sound_FireDash,p\Objects\Entity)
			EndIf
			
			
	End Select
	
	
	
	
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Swipe_Initiate(p.tPlayer, limit=1)
		If p\Motion\Ground Or p\AirKickOnce<limit Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_SWIPE
			p\SpecialSpinTimer=1*secs#
			If p\Motion\Ground=False Then p\AirKickOnce=p\AirKickOnce+1
		EndIf
	End Function

Function Player_Action_Swipe(p.tPlayer)
	
	If p\Motion\Ground Then Player_ActuallyJump(p)
	
	Select p\Character
		Case CHAR_TAI
			Player_CreateRazer.tRazer(p,p\Objects\Mesh,0.45,1)
			If Player_FrameCheck(p,2) Or Player_FrameCheck(p,10) Then
				EmitSmartSound(Sound_PunchSmall,p\Objects\Entity)
				EmitSmartSound(Sound_Swipe,p\Objects\Entity)
			EndIf
		Case CHAR_BLA
			ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)
			
	End Select
	
	
	
	i = False
	
	If Player_IsPlayable(p) Then
		Select p\Character
			Case CHAR_TAI,CHAR_BLA If Not(Input\Hold\ActionSkill1) Then i = True
		End Select
	EndIf
	
	If i And (Not(p\SpecialSpinTimer>0)) And p\Flags\Targeter=0 Then
		If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
	EndIf
	
End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Uppercut_Initiate(p.tPlayer)
	If p\UppercutOnce=0 Then
		If p\Motion\Ground=False Then Player_SetSpeedY(p,2) : EmitSmartSound(Sound_Uppercut,p\Objects\Entity)
		Player_PlayAttackVoice(p)
		p\UpperCutTimer=1.5*secs# : p\Action=ACTION_UPPERCUT
		p\UppercutOnce=1
	EndIf
End Function

Function Player_Action_Uppercut(p.tPlayer)
	
	
	If p\Motion\Ground=True Then
		If Input\Hold\ActionSkill2=False Then
			Player_SetSpeed(p,0.5*p\Physics\UNDERWATERTRIGGER#,True)
			EmitSmartSound(Sound_Uppercut,p\Objects\Entity)
			p\Motion\Ground=False
			Player_SetSpeedY(p,p\UpperCutTimer/secs#*p\Physics\UNDERWATERTRIGGER#)
		EndIf
		If p\Motion\Ground=False And Input\Hold\ActionSkill2 Then p\Action=ACTION_FULLFALL
		Player_ActuallyJump(p)
	Else
		If Input\Pressed\ActionJump Then Player_Action_Glide_Initiate(p)
	EndIf
	
	
	If (Not(Animating(p\Objects\Mesh))) Then p\Action=ACTION_JUMPFALL
	
	
	
	
	
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Claw_Initiate(p.tPlayer)
		Player_PlayAttackVoice(p)
		p\Action=ACTION_CLAW
		p\SpecialSpinTimer=0.5*secs#
	End Function

	Function Player_Action_Claw(p.tPlayer)

		If p\Motion\Ground Then
			Player_ActuallyJump(p)
		Else
			Player_SetSpeedY(p,-0.3*p\Physics\UNDERWATERTRIGGER#)
		EndIf

		If (Not(Player_IsPlayable(p) And Input\Hold\ActionSkill1)) Then
			If (Not(p\SpecialSpinTimer>0)) And p\Flags\Targeter=0 Then
				If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
			EndIf
		EndIf

		Select p\Character
			Case CHAR_BLA:
				If p\Underwater=0 Then
					If Not(ChannelPlaying(p\Channel_Fire)) Then p\Channel_Fire=EmitSmartSound(Sound_FireDash,p\Objects\Entity)
				EndIf
			Case CHAR_MAR:
				If Not(ChannelPlaying(p\Channel_Water)) Then p\Channel_Water=EmitSmartSound(Sound_WaterDash,p\Objects\Entity)
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_FullFall(p.tPlayer)

		Player_ActuallyJump(p)

		Player_SkillActions(p)
		
		Player_Action_Fall(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Flutter_Initiate(p.tPlayer)
		If (Not(p\GlideRestartTimer>0)) Then
		If p\LevitatedOnce=0 Or p\GlideTimer>0 Then
			Player_PlayJumpActionVoice(p)
			If p\No#=1 And p\Character=CHAR_HON Then EmitSmartSound(Sound_GlideStart,p\Objects\Entity)
			p\Action=ACTION_FLUTTER
			If p\LevitatedOnce=0 Then p\GlideTimer=2.24*secs# : p\LevitatedOnce=1 : Player_SetSpeedY(p,1.05632)
			p\GlideStartTimer=2.24*secs#
			Select p\Character
				Case CHAR_MAR: Player_SetSpeed(p,p\SpeedLength#-0.2,True)
				Case CHAR_HON: Player_SetSpeed(p,2.25,True)
			End Select
			Player_SetSpeedY(p,0.32,True)
			Player_FollowerHolding_EveryoneJumpDashes(p)
		EndIf
		EndIf
	End Function

	Function Player_Action_Flutter(p.tPlayer)

		Player_ActuallyLand(p)

		If p\GlideStartTimer<1.868*secs# And ((p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump))) Or (Not(p\GlideTimer>0))) Then
			Select p\Character
				Case CHAR_HON: Player_SetSpeed(p,1.85,True)
				Default: Player_SetSpeed(p,1.25,True)
			End Select
			p\GlideRestartTimer=0.08*secs#/2.0
			p\Action=ACTION_JUMPFALL
		EndIf

		If (Not(p\JumpActionRestrictTimer>0)) Then
			If p\GlideStartTimer>1.68*secs# Then
				Player_SetSpeedY(p,p\Physics\FLUTTERFALL_SPEED#,True)
			ElseIf p\GlideStartTimer>1.28*secs# Then
				Player_SetSpeedY(p,p\Physics\FLUTTERFALL_SPEED#*1.5,True)
			Else
				Player_SetSpeedY(p,p\Physics\FLUTTERFALL_SPEED#*2,True)
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Buoy_Initiate(p.tPlayer)
		If p\Objects\Position\y#<Game\Stage\Properties\WaterLevel And (Not(p\WasInBuoyTimer>0)) Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_BUOY
			Player_ConvertGroundToAir(p)
			p\Motion\Ground = False
			p\Motion\Align\x# = 0.0 : p\Motion\Align\y# = 1.0 : p\Motion\Align\z# = 0.0
		EndIf
	End Function

	Function Player_Action_Buoy(p.tPlayer)

		If p\Objects\Position\y#<Game\Stage\Properties\WaterLevel Then
			p\Motion\Speed\y#=1
		EndIf
		
		If (Not(Input\Hold\ActionSkill3)) Then p\Action=ACTION_JUMPFALL : p\WasInBuoyTimer=0.2*secs#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Psycho_Initiate(p.tPlayer)
		Select p\Character
			Case CHAR_SIL:
				Select p\Psychokinesis
				Case 0:
					If (Not(p\PsychoChargeTimer>0)) Then
						Player_PlayAttackVoice(p)
						p\Psychokinesis=1
						p\PsychoType=2
						p\PsychokinesisTimer=0.5*secs#
						EmitSmartSound(Sound_PsychoHold,p\Objects\Entity)
						If Not(p\Action=ACTION_LEVITATE) Then p\Action=ACTION_PSYCHO : p\ThrowType=1
					EndIf
				Case 1:
					If p\SpeedLength#>0 And Game\Gameplay\PsychoBombCount>0 Then
						If (Not(p\PsychoChargeTimer>0)) Then
							Player_PlayAttackVoice(p)
							Game\Gameplay\PsychoBombCount=0
							p\PsychokinesisThrowTimer=0.1*secs#
							p\Psychokinesis=0
							p\PsychoType=1
							p\PsychokinesisTimer=0
							p\PsychoChargeTimer=1*secs#
							ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1)
							EmitSmartSound(Sound_PsychoThrow,p\Objects\Entity)
							If Not(p\Action=ACTION_LEVITATE) Then p\Action=ACTION_PSYCHO : p\ThrowType=2
						EndIf
					Else
						Game\Gameplay\PsychoBombCount=0
						p\Psychokinesis=0
						p\PsychoType=1
						p\PsychokinesisTimer=0
						EmitSmartSound(Sound_PsychoRelease,p\Objects\Entity)
						If Not(p\Action=ACTION_LEVITATE) Then p\Action=ACTION_PSYCHO : p\ThrowType=1
					EndIf
				End Select
			Case CHAR_INF:
				If (Not(p\PsychoChargeTimer>0)) Then
					Player_PlayAttackVoice(p)
					p\PsychoChargeTimer=3*secs#
					p\RubyGravityTimer=2.25*secs#
					EmitSmartSound(Sound_RubySwirl,p\Objects\Entity)
					ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1, 0, 1)
					If Not(p\Action=ACTION_LEVITATE) Then p\Action=ACTION_PSYCHO : p\ThrowType=1
				EndIf
		End Select
	End Function

	Function Player_Action_Psycho(p.tPlayer)

		If p\Motion\Ground Then
			Player_Action_Common(p)
		Else
			Player_Action_JumpFall(p)
		EndIf

		If Not(Animating(p\Objects\Mesh)) Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Turn_Initiate(p.tPlayer)
		If p\Motion\Ground=False And (Not(p\InvisibilityRestrictTimer>0)) And p\BombThrown<1 Then
			p\Action=ACTION_TURN
			If p\Motion\Ground=False Then p\BombThrown=p\BombThrown+1
		EndIf
	End Function

	Function Player_Action_Turn(p.tPlayer)

		Player_Action_Fall(p)

		Player_SetSpeedY(p,-0.1,True)

		If Not(Animating(p\Objects\Mesh)) Then
			Select p\Character
				Case CHAR_EME,CHAR_GME:
					Select p\Character
						Case CHAR_EME:
							Select p\CharacterMode
								Case CHAR_SON: p\CharacterMode=CHAR_TAI
								Case CHAR_TAI: p\CharacterMode=CHAR_KNU
								Case CHAR_KNU: p\CharacterMode=CHAR_AMY
								Default: p\CharacterMode=CHAR_SON
							End Select
						Case CHAR_GME:
							Select p\CharacterMode
								Case CHAR_ESP: p\CharacterMode=CHAR_RAY
								Case CHAR_RAY: p\CharacterMode=CHAR_OME
								Default: p\CharacterMode=CHAR_ESP
							End Select
					End Select
					EmitSmartSound(Sound_Invisible,p\Objects\Entity)
					p\InvisibilityRestrictTimer=0*secs#
				Default:
					If (Not(p\InvisibilityRestrictTimer>0)) Then
						Select p\Invisibility
							Case 0: p\Invisibility=1 : p\InvisibilityTimer=10*secs#
							Case 1: p\InvisibilityTimer=0
						End Select
						EmitSmartSound(Sound_Invisible,p\Objects\Entity)
					EndIf
			End Select
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Soar_Initiate(p.tPlayer)
		If (Not(p\GlideRestartTimer>0)) Then
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_SOAR
			If p\No#=1 Then EmitSmartSound(Sound_GlideStart,p\Objects\Entity)
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#*0.875,True)
			Player_SetSpeedY(p,0,True)
			If Not(p\SoarTimer>0) Then p\SoarTimer=6*secs#
		EndIf
	End Function

	Function Player_Action_Soar(p.tPlayer)

		Player_ActuallyLand(p)

		If p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump)) Then
			Player_SetSpeed(p,1.25,True)
			p\GlideRestartTimer=0.14*secs#/2.0
			If p\Motion\Ground Then Player_ConvertGroundToAir(p) : p\Motion\Ground=False
			p\Action=ACTION_JUMPFALL
		EndIf

		If p\No#=1 And (Not(p\SoarTimer>0)) Then
			Player_SetSpeed(p,1.25,True)
			p\Action=ACTION_FALL
		EndIf

		If (Not(p\JumpActionRestrictTimer>0)) And (Not(p\JustSoaredTimer>0)) Then Player_SetSpeedY(p,p\Physics\GLIDEFALL_SPEED#)

		If p\No#=1 And Player_IsPlayable(p) And Input\Pressed\ActionSkill2 And (Not(p\Action=ACTION_SOARFLAP)) And (Not(p\JustSoaredTimer>0)) And p\Motion\Ground=False Then
			p\Action=ACTION_SOARFLAP
			p\Objects\ForthRotation# = 0
		EndIf

	End Function

	Function Player_Action_SoarFlap(p.tPlayer)

		Player_Action_Soar(p)

		p\Objects\ForthScale# = 0.001
		p\Objects\ForthAlpha# = 0.001
		p\Objects\ForthRotation# = 0

		If Not(Animating(p\Objects\Mesh)) Then
			Player_SetSpeedY(p,1.825*p\Physics\UNDERWATERTRIGGER#)
			p\Objects\ForthScale# = 1
			p\Objects\ForthAlpha# = 1
			p\Objects\ForthRotation# = 0
			p\Action=ACTION_SOAR
			p\JustSoaredTimer=0.323*secs#
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Carry(p.tPlayer)

		Player_ActuallyJump(p)

		If p\Motion\Ground=False Then p\Action=ACTION_CARRYJUMP

		If p\Rotation#>50 Or p\ObjPickUp=0 Then
			If p\Motion\Ground=False Then p\Action=ACTION_FULLFALL Else p\Action=ACTION_COMMON
			p\ObjPickUp=0
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Dive_Initiate(p.tPlayer)
		If p\JumpDashedOnce=0 Then
			p\JumpDashedOnce=1
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_DIVE
			p\DoubleJump=0
			If p\No#=1 Then EmitSmartSound(Sound_Dive,p\Objects\Entity)
			p\DoubleJumpTimer=0.775*secs#
			Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#-0.235),True)
			Player_FollowerHolding_EveryoneDoubleJumps(p)
		EndIf
	End Function

	Function Player_Action_Dive(p.tPlayer)

		Select p\DoubleJump
			Case 0:
				If p\Frame<3 Then
					Player_SetSpeedY(p,3)
				ElseIf p\Frame<5 Then
					Player_SetSpeedY(p,1.5)
				ElseIf p\Frame<7 Then
					Player_SetSpeedY(p,0)
				ElseIf p\Frame<9 Then
					Player_SetSpeedY(p,-1)
				EndIf
				If Not(Animating(p\Objects\Mesh)) Then p\DoubleJump=1
			Case 1:
				Player_SetSpeedY(p,-1.85)
		End Select
	
		If (Not(p\DoubleJumpTimer>0)) And p\DoubleJump=1 Then p\Action=ACTION_JUMPFALL

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Sleet_Initiate(p.tPlayer)
		If p\JumpDashedOnce=0 And (Not(p\GlideRestartTimer>0)) Then
			p\JumpDashedOnce=1
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_SLEET
			p\GlideTimer=0.625*secs#
			If p\No#=1 Then EmitSmartSound(Sound_GlideStart3,p\Objects\Entity)
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#*1.5,True)
			Player_SetSpeedY(p,0,True)
		EndIf
	End Function

	Function Player_Action_Sleet(p.tPlayer)

		Player_ActuallyLand(p)

		If (p\No#=1 And (Not(Player_IsPlayable(p) And Input\Hold\ActionJump))) Or (Not(p\GlideTimer>0)) Then
			Player_SetSpeed(p,1.25,True)
			p\GlideRestartTimer=0.12*secs#/2.0
			p\Action=ACTION_JUMPFALL
		EndIf

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,-p\Physics\GLIDEFALL_SPEED#*1.315)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Cannon(p.tPlayer)

		PositionEntity p\Objects\Entity, p\CannonX#, p\CannonY#, p\CannonZ#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Spirit_Initiate(p.tPlayer)
		If Not(p\ShootCooldownTimer>0) Then
			Player_ConvertGroundToAir(p)
			p\Motion\Ground = False
			Player_PlayAttackVoice(p)
			EmitSmartSound(Sound_Spirit,p\Objects\Entity)
			p\Action=ACTION_SPIRIT
			p\GlideTimer=2.1*secs#
		EndIf
	End Function

	Function Player_Action_Spirit(p.tPlayer)

		p\SpiritualChange=1

		ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_SPIRIT, p\Objects\Entity)

		Player_SetSpeedYUpDownDiff(p,0.5,0.05)

		If (Not(p\GlideTimer>0)) Or (Not(Player_IsPlayable(p) And Input\Hold\ActionSkill2)) Then
			p\Action=ACTION_FALL
			EmitSmartSound(Sound_Spirit,p\Objects\Entity)
			p\ShootCooldownTimer=4.85*secs#
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_CarryThrown(p.tPlayer)

		If p\ObjPickUp=0 Then p\Action=ACTION_FALL

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_CarryJump(p.tPlayer)

		If (Player_IsPlayable(p) And Input\Hold\ActionJump=False And p\Motion\Speed\y#>p\Physics\JUMP_STRENGTH_VARIABLE#) Then
			p\Motion\Speed\y# = p\Physics\JUMP_STRENGTH_VARIABLE#
		End If

		Player_Action_CarryThrown(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_ShakeTree(p.tPlayer)

		If p\Motion\Ground=False Then p\Action=ACTION_FALL : p\ObjPickUp=0

		If (Not(p\ShakeTreeTimer>0)) Then p\Action=ACTION_COMMON : p\ObjPickUp=0 : p\GetFruit=1

		Game\ControlLock=0.1*secs#

		Player_SetSpeed(p,0)

		cam\DontDoUnderwaterEffectsTimer=1*secs#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Hold(p.tPlayer)

		p\IsHoldingTimer=0.5*secs#

		If Not(p\JustGrabbedPulleyTimer>0) Then Player_ActuallyJump(p)

		If Not(p\ShouldBeHoldingTimer>0) Then p\Action=ACTION_FALL

		If p\No#>1 Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Board_GrindTurn(p.tPlayer)
		If p\GrindTurn=0 Then p\GrindTurn=1
		If ( (p\GrindTurn=1 And Player_IsPlayable(p) And Input\Hold\ActionRoll) Or (p\GrindTurn=2 And (Not(Player_IsPlayable(p) And Input\Hold\ActionRoll))) ) And (Not(p\GrindTurnRestrictTimer>0)) Then
			Select p\GrindTurn
				Case 1: p\GrindTurn=2
				Case 2: p\GrindTurn=1
			End Select
			p\GrindTurnTimer=0.15*secs#
			p\GrindTurnRestrictTimer=0.35*secs#
			If Game\Vehicle=1 Or Game\Vehicle=5 Then EmitSmartSound(Sound_Board,p\Objects\Entity)
		EndIf
	End Function

	Function Player_Action_Board_GetGrounded(p.tPlayer)
		If (Not(p\ForceJumpTimer>0)) Then
			If Game\Vehicle=8 Then
				level#=p\Objects\Position\y#
				If level#<Game\Stage\Properties\WaterLevel+3 Then
					If level#<Game\Stage\Properties\WaterLevel+1 Then
						Player_SetSpeedYAlways(p,0.5)
					Else
						Player_SetSpeedYAlways(p,0)
					EndIf
				Else
					If p\Motion\Ground=False Then p\Action=ACTION_BOARDFALL
				EndIf
				If level#<Game\Stage\Properties\WaterLevel+5 Then
					p\BoardWaterTimer=0.1*secs#
					p\TrickCounter=0
					If level#>Game\Stage\Properties\WaterLevel-5 Then
						p\ForceBeingAbleToChangeLeaderTimer=0.1*secs#
					Else
						If p\Motion\Ground=False Then p\CantJumpTimer=0.1*secs#
					EndIf
				Else
					If p\Motion\Ground=False Then p\CantJumpTimer=0.1*secs#
				EndIf
			Else
				If p\Motion\Ground=False Then p\Action=ACTION_BOARDFALL
			EndIf
		EndIf
	End Function

	Function Player_Action_Board(p.tPlayer)

		If Not(p\CantJumpTimer>0) Then Player_ActuallyJump(p)

		Player_Action_Board_GetGrounded(p)

		Player_Action_Board_GrindTurn(p)

		If Player_IsPlayable(p) And Input\Pressed\ActionDrift Then
			p\Action=ACTION_BOARDDRIFT
			Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#-0.4,True)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardJump(p.tPlayer)

		Player_Action_BoardTrick_Initiate(p)

		If (Player_IsPlayable(p) And Input\Hold\ActionJump=False And p\Motion\Speed\y#>p\Physics\JUMP_STRENGTH_VARIABLE#) Then
			p\Motion\Speed\y# = p\Physics\JUMP_STRENGTH_VARIABLE#
		End If

		Player_ActuallyLand(p)

		If (Not(Player_IsPlayable(p) And Input\Hold\ActionJump)) And p\JumpTimer>0.93*secs# Then
			p\Action=ACTION_BOARDFALL
		EndIf

		Player_Action_Board_GrindTurn(p)

		If Game\Vehicle=8 Then
			If (Not(p\ForceJumpTimer>0)) And p\Objects\Position\y#<Game\Stage\Properties\WaterLevel+1 Then p\Action=ACTION_BOARD
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardDrift(p.tPlayer)
        
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)

		Player_Action_Board_GetGrounded(p)

		If Not(p\CantJumpTimer>0) Then Player_ActuallyJump(p)

		If (Not(Player_IsPlayable(p) And Input\Hold\ActionDrift)) Then p\Action=ACTION_BOARD

		If p\DriftDirection=0 Then
			Select(Rand(1,2))
				Case 1: p\DriftDirection=1
				Case 2: p\DriftDirection=-1
			End Select
		EndIf

		If (Player_IsPlayable(p) And Input\Hold\Left) Then p\DriftDirection=-1
		If (Player_IsPlayable(p) And Input\Hold\Right) Then p\DriftDirection=1

		Player_Action_Board_GrindTurn(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardFall(p.tPlayer)

		Player_Action_BoardTrick_Initiate(p)

		Player_ActuallyLand(p)

		Player_Action_Board_GrindTurn(p)

		If Game\Vehicle=8 Then
			If (Not(p\ForceJumpTimer>0)) And p\Objects\Position\y#<Game\Stage\Properties\WaterLevel+1 Then p\Action=ACTION_BOARD
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardTrick_Initiate(p.tPlayer)
		If p\No#=1 Then
			If Input\Pressed\ActionSkill2 And ((Not(p\Action=ACTION_BOARDTRICK)) Or p\Physics\TRICK_ANGLE#<180) And p\TrickCounter<5 Then
			p\TrickTimer=0.625*secs#
			For ppp.tPlayer = Each tPlayer
				If ppp\No#>0 And (ppp\Action=ACTION_BOARDTRICK Or ppp\Action=ACTION_BOARDFALL Or ppp\Action=ACTION_BOARDJUMP) Then
					ppp\Physics\TRICK_ANGLE#=360
					ppp\Action=ACTION_BOARDTRICK
				EndIf
			Next
			EmitSmartSound(Sound_Trick,p\Objects\Entity)
			p\TrickCounter=p\TrickCounter+1
		EndIf
		EndIf
	End Function

	Function Player_Action_BoardTrick(p.tPlayer)

		If p\Physics\TRICK_ANGLE#<=0 Then p\Action=ACTION_BOARDFALL

		Player_Action_BoardFall(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Transform(p.tPlayer)

		Player_SetSpeed(p,0)
		Player_SetSpeedY(p,0)

		If (Not(p\JumpMayRiseTimer>0)) And p\No#=1 Then
			EmitSmartSound(Sound_GoSuper,p\Objects\Entity)
			Game\SuperForm=Game\SuperForm+1
			For ee.tEmerald=Each tEmerald : Remove_Emerald(ee) : Next
			For ppp.tPlayer = Each tPlayer : DeformCharacter(ppp,True) : Next
			PostEffect_Create_FadeIn(0.04, 255, 255, 255)
		EndIf		

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_RivalDie(p.tPlayer)

		Player_SetSpeed(p,0)

		If p\Motion\Ground=False Then p\Motion\Speed\y# = p\Physics\DIEFALL_SPEED#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Gatling_Initiate(p.tPlayer)
		If (p\Motion\Ground Or p\Character=CHAR_TIA) And (Not(p\ShootCooldownTimer>0)) Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_GATLING
			p\PunchTimer=0.2*secs#
			EmitSmartSound(Sound_Punch,p\Objects\Entity)
			p\PunchNumber=1
		EndIf
	End Function

	Function Player_Action_Gatling(p.tPlayer)

		Select p\Character
			Case CHAR_TIA:
				Player_SetSpeedY(p,0)
				If Not(ChannelPlaying(p\Channel_Tinkle)) Then p\Channel_Tinkle=EmitSmartSound(Sound_Tinkle,p\Objects\Entity)
			Default:
				Player_ActuallyFall(p)
		End Select

		If (Not(p\PunchTimer>0)) Then
			If p\PunchNumber<10 Then
				p\PunchTimer=0.25*secs#
				Select p\Character
					Case CHAR_OME:
						EmitSmartSound(Sound_Shotgun2,p\Objects\Entity)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET2, 1)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET2, 2)
					Case CHAR_VEC:
						EmitSmartSound(Sound_GlideStart2,p\Objects\Entity)
						Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+4, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_NOTE)
					Case CHAR_TIA:
						Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BEAM)
				End Select
				p\PunchNumber=p\PunchNumber+1
			EndIf
		EndIf

		If (((Not(Input\Hold\ActionSkill3)) And (Not(p\Character=CHAR_TIA))) Or ((Not(Input\Hold\ActionSkill1)) And (p\Character=CHAR_TIA))) Or (Not(p\PunchNumber<10)) Then
			p\PunchTimer=0
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
			Select p\Character
				Case CHAR_TIA: p\ShootCooldownTimer=1.82*secs#
			End Select
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Shoot_Hover(p.tPlayer)

		Player_ActuallyLand(p)
		
		Player_SetSpeed(p,p\Physics\HOVER_SPEED#,True)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\HOVERFALL_SPEED#*0.3455,True)
		
		Select p\Character
			Case CHAR_TAI
				If Player_FrameCheck(p,6) Then
					Player_Action_Shoot_NormalShot(p)
				EndIf
			Case CHAR_EGR
				If Player_FrameCheck(p,4) And p\HasShotOnce=0 Then
					Player_Action_Shoot_NormalShot(p)
					p\HasShotOnce=1
				EndIf
		End Select
		
		If (Not(Animating(p\Objects\Mesh))) Then
			p\HasShotOnce=0
			Select p\Character
				Case CHAR_EGR,CHAR_TAI: p\Action=ACTION_FLY
				Default: p\Action=ACTION_HOVER : Player_Action_Shoot_Shot(p)
			End Select
			
		EndIf
		
		
		
		

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Skydive(p.tPlayer)

		Player_ActuallyLand(p)

		If p\Motion\Ground=False Then
			If Input\Hold\ActionRoll Then
				Player_SetSpeedYAlways(p,p\Physics\SKYDIVE_SPEED#*2,True)
			Else
				Player_SetSpeedYAlways(p,p\Physics\SKYDIVE_SPEED#,True)
			EndIf
		EndIf
		
		If p\Objects\Position\y#<Game\SkydiveCancel Then p\Action=ACTION_FALL

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Glider(p.tPlayer)

		If p\No#=1 And (Not(Game\ControlLock>0)) Then Player_ActuallyLand(p)

		If p\Motion\Ground=False Then
			If Input\Hold\ActionJump Then
				Player_SetSpeedYAlways(p,-12.5*0.020322*p\Physics\SKYDIVE_SPEED#*2,True)
			ElseIf Input\Hold\ActionRoll Then
				Player_SetSpeedYAlways(p,14*0.020322*p\Physics\SKYDIVE_SPEED#*2,True)
			Else
				Player_SetSpeedYAlways(p,0.020322*p\Physics\SKYDIVE_SPEED#,True)
			EndIf
		Else
			If Input\Hold\ActionJump Then
				Player_ConvertGroundToAir(p)
				p\Motion\Ground = False
				Player_SetSpeedYAlways(p,-10*0.020322*p\Physics\SKYDIVE_SPEED#*2,True)
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Freeze_Initiate(p.tPlayer)
		If Game\Vehicle=0 And Game\Invinc=0 Then
			EmitSmartSound(Sound_Bounce,p\Objects\Entity)
			p\Action=ACTION_FREEZE
			Player_CreateRazer.tRazer(p,p\Objects\Mesh,1,4,1+0.5*p\ScaleFactor#,1+0.5*p\ScaleFactor#,1+0.5*p\ScaleFactor#,p\WasGrabbedTimer)
		EndIf
	End Function

	Function Player_Action_Freeze_Initiate2(p.tPlayer)
		If (Not(Game\Shield=OBJTYPE_BSHIELD Or p\Character=CHAR_MAR Or p\Character=CHAR_BAR)) Then
			If (Not(p\WasGrabbedTimer>0)) And Game\Invinc=0 Then
				Player_ConvertGroundToAir(p) : p\Motion\Ground = False
				If Game\MachLock>0 Then
					Player_SetSpeed(p,0.5)
				Else
					Player_SetSpeed(p,-1)
				EndIf
				Player_SetSpeedY(p,1)
				p\WasGrabbedTimer=2.0*secs#
				Player_Action_Freeze_Initiate(p)
			EndIf
		EndIf
	End Function

	Function Player_Action_Freeze(p.tPlayer)

		Game\ControlLock=0.2*secs#

		If p\Motion\Ground Then
			Player_SetSpeed(p,0)
			Player_SetSpeedY(p,0)
		Else
			If p\WasGrabbedTimer<1.0*secs# Then Player_SetSpeed(p,0)
		EndIf

		If Game\MachLock>0 And Menu\Stage<0 Then p\Animation\Direction#=180

		If Not(p\WasGrabbedTimer>0) Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Action_Hookshot_Initiate(p.tPlayer, limit=2)
	If (Not(p\ThrowTimer>0)) Then
		p\ThrowTimer=0.55*secs#
		Player_PlayAttackVoice(p)
		If p\Motion\Ground=False And p\ThrowABomb=<2 Then Player_SetSpeedY(p,1.2) : p\ThrowABomb=p\ThrowABomb+1
		p\Action=ACTION_HOOKSHOT	
	EndIf
End Function

Function Player_Action_Hookshot(p.tPlayer)
	
	
	If (Not(Animating(p\Objects\Mesh))) Then
		EmitSmartSound(Sound_Kunai,p\Objects\Entity)
		Object_Bomb_Create.tBomb(p,  EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_HOOKSHOT)
		If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
	EndIf 
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Sink(p.tPlayer)

		Game\ControlLock=0.1*secs#

		Player_SetSpeed(p,0)
		Player_SetSpeedY(p,-0.0625)

		If p\Objects\Position\y# > Game\Stage\Properties\WaterLevel+5 Then p\Action=ACTION_FALL

	End Function
	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Car(p.tPlayer)

		If p\Action=ACTION_CARFALL Then
			Player_ActuallyLand(p)
		Else
			If p\Motion\Ground Then
				Player_ActuallyJump(p)
			Else
				If (Not(p\ForceJumpTimer>0)) Then p\Action=ACTION_CARFALL
			EndIf

			If Player_IsPlayable(p) And p\Motion\Ground And Input\Pressed\ActionDrift Then
				p\Action=ACTION_CARDRIFT
				Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#-0.4,True)
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_CarDrift(p.tPlayer)
        
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)

		If p\Motion\Ground=False And (Not(p\ForceJumpTimer>0)) Then p\Action=ACTION_CARFALL

		Player_ActuallyJump(p)

		If (Not(Player_IsPlayable(p) And Input\Hold\ActionDrift)) Then p\Action=ACTION_CAR

		If p\DriftDirection=0 Then
			Select(Rand(1,2))
				Case 1: p\DriftDirection=1
				Case 2: p\DriftDirection=-1
			End Select
		EndIf

		If (Player_IsPlayable(p) And Input\Hold\Left) Then p\DriftDirection=-1
		If (Player_IsPlayable(p) And Input\Hold\Right) Then p\DriftDirection=1

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BellyFlop_Initiate(p.tPlayer)
		If p\BombThrown<3 Then
			p\Action = ACTION_BELLYFLOP
			p\StompSaverTimer=0 : p\StompSaver#=p\Objects\Position\y#
			EmitSmartSound(Sound_GlideStart3,p\Objects\Entity)
			Select p\Character
				Case CHAR_CHO: EmitSmartSound(Sound_Bounce,p\Objects\Entity)
			End Select
			If p\SpeedLength#+0.5>3 Then
				Player_SetSpeed(p,3)
			Else
				Player_SetSpeed(p,p\SpeedLength#+0.5)
			EndIf
			p\SpecialSpinTimer=0.2*secs#
			p\BombThrown=p\BombThrown+1
		EndIf
	End Function

	Function Player_Action_BellyFlop(p.tPlayer)

		Player_Action_StompSaver(p)

		Select p\Character
			Case CHAR_CHO: ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		End Select

		If p\Motion\Ground Then
			p\Bouncing=1
			Player_Action_Bounce_Initiate(p)
			p\Channel_GroundLand=EmitSmartSound(Sound_KnuxStomp,p\Objects\Entity)
			p\Motion\Speed\y# = 2.0
			ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, 1+p\ScaleFactor#+5, 0, 0, p\RealCharacter, 2)
			p\Flags\Targeter=0
			For i=1 To 4
			Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BELLYFLOP)
			Next
		EndIf

		If p\SpecialSpinTimer>0 Then
			p\Motion\Speed\y# = -p\Physics\STOMPFALL_SPEED#*0.1
		Else
			If p\Motion\Ground=False Then p\Motion\Speed\y# = p\Physics\STOMPFALL_SPEED#-1
			If Not(p\Motion\Speed\y#<0) Then
				p\Action=ACTION_FALL
				p\Flags\Targeter=0
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Puddle_Initiate(p.tPlayer)
		If (Not(p\InvisibilityRestrictTimer>0)) And p\BombThrown<1 Then
			p\Action=ACTION_PUDDLE
			If p\Motion\Ground=False Then p\BombThrown=p\BombThrown+1
			p\Invisibility=1 : p\InvisibilityTimer=10*secs#
			EmitSmartSound(Sound_Bounce,p\Objects\Entity)
		EndIf
	End Function

	Function Player_Action_Puddle(p.tPlayer)

		ParticleTemplate_Call(p\SmokeParticle, PARTICLE_CHAO_SWIM, p\Objects\Mesh, 0.2, 0, 2.2, 0, 0, 0.125)

		Player_Action_Common(p)

		If (Not(Input\Hold\ActionSkill3)) Or p\Invisibility=0 Then p\Action=ACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Tornado(p.tPlayer)
	If p\No#=1 Then
		If Game\Victory<>0 Then
			Player_SetSpeedYAlways(p,0)
		Else
			If p\Motion\Ground=False Then
				If p\SpecialSpinTimer>0 Then
					Player_SetSpeedYAlways(p,0.5)
				Else
					If Input\Hold\ActionJump Then
						Player_SetSpeedYAlways(p,-1.5*p\Physics\SKYDIVE_SPEED#)
					ElseIf Input\Hold\ActionRoll Then
						Player_SetSpeedYAlways(p,1.5*p\Physics\SKYDIVE_SPEED#)
					Else
						Player_SetSpeedYAlways(p,0)
					EndIf
				EndIf
			Else
				Player_ConvertGroundToAir(p)
				p\Motion\Ground = False
				p\SpecialSpinTimer=0.5*secs#
				Player_SetSpeedYAlways(p,0.5)
			EndIf

			If Input\Pressed\ActionSkill2 And (Not(p\TornadoChangeTimer>0)) Then
				p\TornadoChangeTimer=0.5*secs#
				If (Not(Game\Vehicle=7)) Then Game\Vehicle=7 Else Game\Vehicle=6
				PostEffect_Create_FadeIn(0.04, 255, 255, 255)
				EmitSmartSound(Sound_PlaneChange,p\Objects\Entity)
			EndIf

			If Input\Pressed\ActionSkill1 Then
				If Game\Vehicle=7 Then
					Player_Action_Shoot_AimBegin(p)
				Else
					If (Not(p\ThrowTimer>0)) Then
						p\ThrowTimer=0.05*secs#
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\VehicleShoot,1), EntityY(p\Objects\VehicleShoot,1), EntityZ(p\Objects\VehicleShoot,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3)
						EmitSmartSound(Sound_Shotgun2,p\Objects\Entity)
						If Rand(1,8)=1 Then Player_PlayAttackVoice(p)
					EndIf
				EndIf
			EndIf

			If Game\Vehicle=7 And p\TornadoShoot=1 Then
				p\TornadoShoot=0
				Player_Action_Shoot_AimShot(p)
				If Rand(1,8)=1 Then Player_PlayAttackVoice(p)
			EndIf
		EndIf
	Else
		If (Not(pp(1)\Action=ACTION_TORNADO)) Then p\Action=ACTION_FALL
	EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================
;~IDEal Editor Parameters:
;~C#Blitz3D