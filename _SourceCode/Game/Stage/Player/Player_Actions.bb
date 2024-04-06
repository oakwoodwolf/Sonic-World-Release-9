
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

		Player_ActuallyFall(p)

		Player_ActuallyJump(p)

		Player_SkillActions(p)

		Player_ActuallyCharge(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Hop(p.tPlayer)
	
		Player_Action_Jump(p)

		If p\JumpHopTimer>0.1*secs# Then p\Action=ACTION_JUMP

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Jump(p.tPlayer)

		Player_JumpActions(p)

		If p\Bouncing=0 Then
			If ((Player_IsPlayable(p) and Input\Hold\ActionJump=False) Or (p\No#<0)) And p\Motion\Speed\y# > p\Physics\JUMP_STRENGTH_VARIABLE# and (Not(p\JumpMayRiseTimer>0)) Then
				p\Motion\Speed\y# = p\Physics\JUMP_STRENGTH_VARIABLE#
			End If

			If ((Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) Or p\No#<0) and p\JumpTimer>0.93*secs# Then
				p\Action=ACTION_JUMPFALL
			EndIf
		EndIf

		Player_ActuallyLand(p)

		If Player_IsSoundable(p) and p\JumpHopTimer>0.4*secs# and p\JumpHopTimer<0.5*secs# and ChannelPlaying(p\Channel_Spin)=False Then
			p\Channel_Spin=EmitSmartSound(Sound_Spin,p\Objects\Entity)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for the Landing.
	Function Player_Action_Land(p.tPlayer)
		
		Player_Action_Common(p)

		If p\LandTimer>0.1*secs# Then p\Action=ACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for falling.
	Function Player_Action_Fall(p.tPlayer)

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for falling, with jump actions available.
	Function Player_Action_JumpFall(p.tPlayer)

		Player_JumpActions(p)
		
		Player_Action_Fall(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for the Charging a Spindash.
	Function Player_Action_Charge(p.tPlayer)

		ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_SMOKE, p\Objects\Mesh, 1, 0.075, p\SpeedLength#+1.25, 0, 1, 0.0375)

		Player_ActuallyFall(p)

		If p\JustChargedTimer>13*secs# Then
			If p\SpeedLength#>0.71 Then
				Player_SetSpeed(p,0.71)
			EndIf
		EndIf

		Player_ActuallyJump(p)

		If (Not(Player_IsPlayable(p) and Input\Hold\ActionRoll)) and p\ChargeTimer>0.2*secs# Then
			If p\ChargeTimer>chargedelay#*secs# Then
				Player_Action_Roll_Initiate(p,0.125)
			Else
				p\Action = ACTION_COMMON
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Start function for the Roll/Released Spindash.
	Function Player_Action_Roll_Initiate(p.tPlayer, chargedelay#)
		p\Action = ACTION_ROLL
		If Player_IsSoundable(p) Then EmitSmartSound(Sound_SpinDashRelease,p\Objects\Entity)
		Player_PlayAttackVoice(p)
		If p\ChargeTimer<=5.0*secs# Then
			Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#+1.0+(p\ChargeTimer/secs#-chargedelay#),true)
		Else
			Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#+1.0+(5.0-chargedelay#),true)
		EndIf
	End Function
	;; Start function for the Roll/Released Spindash for Rival players.
	Function Player_Action_Roll_Initiate_Rival(p.tPlayer)
		p\Action = ACTION_ROLL
		p\Rival\Speed#=p\Physics\SPINDASH_SPEED#+Rand(0,4)/2.0
		EmitSmartSound(Sound_SpinDashCharge,p\Objects\Entity)
		EmitSmartSound(Sound_SpinDashRelease,p\Objects\Entity)
		Player_PlayAttackVoice(p)
	End Function
	;; Update function for the Roll/Released Spindash.
	Function Player_Action_Roll(p.tPlayer)

		If p\No#>1 and (Not(pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_ROLL)) Then p\Action=ACTION_COMMON

		Player_ActuallyFall(p)

		Player_ActuallyJump(p)

		If p\SpeedLength# < 0.05 Then p\Action=ACTION_COMMON

		If Input\Pressed\ActionRoll Then
			If Player_IsPlayable(p) and p\Motion\Ground Then p\Action=ACTION_COMMON
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Start function for the Drift.
	Function Player_Action_Drift_Initiate(p.tPlayer)
		If p\No#=1 Then
			If (Not(p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL)) Then EmitSmartSound(Sound_SpinDashCharge,p\Objects\Entity)
			If (Not(p\Action=ACTION_ROLL)) Then EmitSmartSound(Sound_SpinDashRelease,p\Objects\Entity)
		EndIf
		p\Action=ACTION_DRIFT
		Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#-0.4,true)
		Select(Rand(1,2))
			Case 1: p\DriftDirection=1
			Case 2: p\DriftDirection=-1
		End Select
	End Function
	;; Update function for the Drift.
	Function Player_Action_Drift(p.tPlayer)
        
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)

		Player_ActuallyFall(p)

		Player_ActuallyJump(p)

		If (Not(Player_IsPlayable(p) and Input\Hold\ActionDrift)) Then p\Action=ACTION_COMMON

		If (Player_IsPlayable(p) and Input\Hold\Left) Then p\DriftDirection=-1
		If (Player_IsPlayable(p) and Input\Hold\Right) Then p\DriftDirection=1

		If p\SpeedLength# < 0.05 Then p\Action=ACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for player up state. Often used on ramps.
	Function Player_Action_Fwd(p.tPlayer)

		Player_ResetAirRestrictionStuff(p)

		If p\SpeedLength# < 0.5 and p\GoDestination=False Then p\Action=ACTION_JUMPFALL

		Player_JumpActions(p)

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for player up state. Often used on springs.
	Function Player_Action_Up(p.tPlayer)

		Player_ResetAirRestrictionStuff(p)

		If p\SpeedLength# < 0.5 and p\Motion\Speed\y# < -0.5 and p\GoDestination=False Then p\Action=ACTION_JUMPFALL

		Player_JumpActions(p)

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Start function for the Air-Dash for the player
	Function Player_Action_JumpDash_Initiate(p.tPlayer)
		If p\JumpDashedOnce=0 Then
			p\JumpDashedOnce=1
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_JUMPDASH
			If p\No#=1 Then EmitSmartSound(Sound_HomingAttack,p\Objects\Entity)
			p\JumpDashTimer=0.35*secs#
			Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#+1.3),true)
			Player_FollowerHolding_EveryoneJumpDashes(p)
		EndIf
	End Function
	;; Start function for the Air-Dash for followers
	Function Player_Action_JumpDash_Initiate_Generic(p.tPlayer)
		p\Action=ACTION_JUMPDASH
		p\JumpDashTimer=0.35*secs#
		Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#+1.3),true)
	End Function
	;; Update function for the Air-Dash
	Function Player_Action_JumpDash(p.tPlayer)

		Player_SetSpeedYUpDown(p,0.5,p\JumpActionRestrictTimer)
	
		If (Not(p\JumpDashTimer>0)) Then p\Action=ACTION_JUMPFALL

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Update function for the Homing Attack
	Function Player_Action_Homing(p.tPlayer)

		If (Not(p\HomingTimer>0)) and p\Flags\Targeter=0 Then p\Action=ACTION_FALL

		Select p\Character
			Case CHAR_AMY: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FLOWER, p\Objects\Entity)
			Case CHAR_BLA: If p\Underwater=0 Then ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Fly_Initiate(p.tPlayer)
		Player_PlayJumpActionVoice(p)
		p\Action=ACTION_FLY
		If p\No#=1 Then EmitSmartSound(Sound_FlyStart,p\Objects\Entity)
		If Not(p\FlyTimer>0) Then p\FlyTimer=7*secs#
		If p\LevitatedOnce=0 Then p\FlyDistanceLimit=p\Objects\Position\y# : p\LevitatedOnce=1
		If p\Character=CHAR_CHA Then Player_SetSpeedY(p,abs(p\Physics\BUZZFLYFALL_SPEED#*2))
	End Function
	;; Update function for the Fly
	Function Player_Action_Fly(p.tPlayer)

		Player_ActuallyLand(p)

		If Not(p\JumpActionRestrictTimer>0) Then
			If Player_IsPlayable(p) and Input\Pressed\ActionJump Then
				If p\Objects\Position\y#>p\FlyDistanceLimit+130 Then
					Player_SetSpeedY(p,0)
				Else
					Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.3)
				EndIf
			Else
				Select p\Character
					Case CHAR_CHA: Player_SetSpeedY(p,p\Physics\BUZZFLYFALL_SPEED#,true)
					Default: Player_SetSpeedY(p,-p\Physics\FLYDOWN_SPEED#,true)
				End Select
			EndIf
		EndIf

		If (Not(p\FlyTimer>0)) Then p\Action=ACTION_FALL

		If p\No#=1 and Menu\ChaoGarden=0 and Player_IsPlayable(p) Then
			If Input\Pressed\ActionSkill1 Then
				Select p\Character
					Case CHAR_TAI:
						Player_Action_Drop_Initiate(p)
					Case CHAR_CRE:
						If (Not(Game\CheeseTimer>0)) and (Not(p\CheeseRestrictTimer>0)) Then Player_Action_Drop_Initiate(p)
					Case CHAR_CHA:
						Player_Action_Sprint_Initiate(p)
					Case CHAR_EGR:
						Player_Action_Shoot_Initiate(p)
				End Select
			EndIf

			If Input\Pressed\ActionSkill2 Then
				Select p\Character
					Case CHAR_CHA:
						Player_Action_Drop_Initiate(p)
				End Select
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Start function for the glide
	Function Player_Action_Glide_Initiate(p.tPlayer)
		If (Not(p\GlideRestartTimer>0)) Then
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_GLIDE
			If p\No#=1 Then EmitSmartSound(Sound_GlideStart,p\Objects\Entity)
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#,true)
			Player_SetSpeedY(p,0,true)
		EndIf
	End Function
	;; Update function for the Glide
	Function Player_Action_Glide(p.tPlayer)

		Player_ActuallyLand(p)

		If p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) Then
			Player_SetSpeed(p,1.25,true)
			p\GlideRestartTimer=0.12*secs#/2.0
			p\Action=ACTION_JUMPFALL
		EndIf

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\GLIDEFALL_SPEED#)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Starts a Double Jump for the player
	Function Player_Action_DoubleJump_Initiate(p.tPlayer,wallkick=false)
		If p\JumpDashedOnce=0 Then
			p\JumpDashedOnce=1
			Player_PlayJumpActionVoice(p)
			p\DoubleJump=0
			p\DoubleJumpTimer=0.105*secs#
			p\Action = ACTION_DOUBLEJUMP
			If wallkick=False Then
				p\Motion\Speed\x# = p\Motion\Speed\x#*1.12 : p\Motion\Speed\z# = p\Motion\Speed\z#*1.12
			Else
				Player_SetSpeed(p,0)
			EndIf
			Select p\Character
				Case CHAR_NAC:
					p\Motion\Speed\x# = p\Motion\Speed\x#*1.22 : p\Motion\Speed\z# = p\Motion\Speed\z#*1.22
					Player_SetSpeedY(p,p\Physics\JUMP_STRENGTH#+0.4)
				Case CHAR_BEA:
					p\Motion\Speed\x# = p\Motion\Speed\x#*1.12 : p\Motion\Speed\z# = p\Motion\Speed\z#*1.12
					Player_SetSpeedY(p,p\Physics\JUMP_STRENGTH#+0.6)
				Default:
					Player_SetSpeedY(p,p\Physics\JUMP_STRENGTH#+0.3)
			End Select
			p\Motion\Ground = False
			p\Motion\Align\x# = 0.0 : p\Motion\Align\y# = 1.0 : p\Motion\Align\z# = 0.0
			If p\No#=1 Then
				EmitSmartSound(Sound_DoubleJump,p\Objects\Entity)
				Select p\Character
					Case CHAR_BLA: If p\Underwater=0 Then EmitSmartSound(Sound_FireDash,p\Objects\Entity)
					Case CHAR_MIG: p\AirKickOnce=0
				End Select
			EndIf
			Player_FollowerHolding_EveryoneDoubleJumps(p)
		EndIf
	End Function
	;; Starts a Double Jump for followers.
	Function Player_Action_DoubleJump_Initiate_Generic(p.tPlayer,wallkick=false)
		p\DoubleJump=0
		p\DoubleJumpTimer=0.105*secs#
		p\Action = ACTION_DOUBLEJUMP
		p\Motion\Speed\x# = p\Motion\Speed\x#*1.12 : p\Motion\Speed\z# = p\Motion\Speed\z#*1.12
		Player_SetSpeedY(p,p\Physics\JUMP_STRENGTH#+0.3)
		p\Motion\Ground = False
		p\Motion\Align\x# = 0.0 : p\Motion\Align\y# = 1.0 : p\Motion\Align\z# = 0.0
	End Function
	;; Update function for the Double Jump
	Function Player_Action_DoubleJump(p.tPlayer)

		Select p\Character
			Case CHAR_AMY: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FLOWER, p\Objects\Entity)
			Case CHAR_BLA: If p\Underwater=0 Then ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)
			Case CHAR_NAC,CHAR_BEA:
				If p\DoubleJump=0 Then
					If (Not(p\DoubleJumpTimer>0)) Then
						Select p\Character
							Case CHAR_NAC:
								If p\No#=1 Then EmitSmartSound(Sound_Sting,p\Objects\Entity)
							Case CHAR_BEA:
								If p\No#=1 Then EmitSmartSound(Sound_BatBomb,p\Objects\Entity)
								Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#-3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BOMB, -1)
						End Select
						p\DoubleJumpTimer=2*secs#
					EndIf
					If (Not(Animating(p\Objects\Mesh))) Then p\DoubleJump=1
				EndIf
		End Select

		Player_ActuallyLand(p)

		If p\Motion\Speed\y# < -0.5 Then p\Action=ACTION_JUMPFALL

	End Function

	; =========================================================================================================
	; =========================================================================================================

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
		
		Player_SetSpeed(p,p\Physics\LEVITATION_SPEED#,true)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,0)

		If (Not(p\LevitationTimer>0)) Then p\Action=ACTION_JUMPFALL

		If p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) Then p\Action=ACTION_JUMPFALL : p\LevitationTimer=p\LevitationTimer-0.12*secs#

		Player_SkillActions2(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Stomp_Initiate(p.tPlayer, bounce=false)
		p\Action = ACTION_STOMP
		If bounce Then
			If p\Bouncing<3 Then p\Bouncing=p\Bouncing+1
		Else
			p\Bouncing=0
		EndIf
		p\StompSaverTimer=0 : p\StompSaver#=p\Objects\Position\y#
		If p\No#=1 Then p\Channel_Stomp = EmitSmartSound(Sound_Stomp,p\Objects\Entity)
		p\Motion\Speed\y# = p\Physics\STOMPFALL_SPEED#
	End Function

	Function Player_Action_Stomp_Initiate_Rival(p.tPlayer, bounce=false)
		If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP)) and p\Motion\Ground Then Player_ActuallyJump(p,true)
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
		If abs(p\StompSaver#-p\Objects\Position\y#) < 1 Then
			p\StompSaverTimer=p\StompSaverTimer+timervalue#
			If p\StompSaverTimer>2*secs# Then
				p\Flags\Targeter=0
				Player_ActuallyJump(p,true)
			EndIf
		Else
			p\StompSaverTimer=0
		EndIf
		p\StompSaver#=p\Objects\Position\y#
	End Function

	Function Player_Action_Stomp(p.tPlayer)

		Player_Action_StompSaver(p)

		If p\Motion\Ground Then
			p\StompBounceTimer=0.55*secs#
			If p\Bouncing=0 Then
				If p\No#=1 Then p\Channel_GroundLand=EmitSmartSound(Sound_StompGround,p\Objects\Entity)
				Player_Land(p)
			Else
				Player_Action_Bounce_Initiate(p)
			EndIf
			ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, 1+p\ScaleFactor#+0.25, 0, 0, p\RealCharacter, 2)
			p\Flags\Targeter=0
		EndIf

		If Not(p\Motion\Speed\y#<0) Then
			p\Action=ACTION_FALL
			p\Flags\Targeter=0
		EndIf

		If p\Motion\Ground=False Then p\Motion\Speed\y# = p\Physics\STOMPFALL_SPEED#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Hurt(p.tPlayer)

		Player_SetSpeed(p,-0.311)

		Player_ActuallyLand(p)

		Player_ActuallyJump(p)

		If Game\MachLock>0 Then p\Flags\HomingWasLockedTimer=0.25*secs#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Die(p.tPlayer)

		Player_SetSpeed(p,0)

		If p\Motion\Ground=False Then p\Motion\Speed\y# = p\Physics\DIEFALL_SPEED#

		If (Not(p\DieTimer>0.1*secs#)) and Menu\ExitedAStage=0 Then Player_DieSpawn(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Float(p.tPlayer)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\FLOATFALL_SPEED#,true)

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
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#*0.66,true)
			If p\Character=CHAR_VEC Then p\CreateGumBallTimer=0.11*secs# : p\ThrowABomb=0
		EndIf
	End Function

	Function Player_Action_SlowGlide(p.tPlayer)

		Player_SetSpeedY(p,-0.3,true)

		Player_ActuallyLand(p)

		If p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) Then
			Player_SetSpeed(p,1.25,true)
			p\GlideRestartTimer=0.12*secs#/2.0
			p\Action=ACTION_JUMPFALL
		EndIf

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\GLIDEFALL_SPEED#)

		If p\Character=CHAR_VEC and (Not(p\CreateGumBallTimer>0)) and p\ThrowABomb=0 Then
			Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Gum,1), EntityY(p\Objects\Gum,1), EntityZ(p\Objects\Gum,1), 0, p\Animation\Direction#-180, 0, BOMB_GUM, 1)
			p\ThrowABomb=1
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Sprint_Initiate(p.tPlayer, limit=1)
		If p\Motion\Ground Or p\AirKickOnce<limit Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_SPRINT
			EmitSmartSound(Sound_Dash,p\Objects\Entity)
			Select p\Character
				Case CHAR_SON,CHAR_SHA,CHAR_MIG,CHAR_ESP,CHAR_MET,CHAR_MT3,CHAR_EME,CHAR_PRS,CHAR_GME:
					p\JumpDashTimer=0.567*secs#
					Player_SetSpeedY(p,-0.75)
				Default:
					p\JumpDashTimer=0.4834*secs#
			End Select
			If p\Motion\Ground=False Then p\AirKickOnce=p\AirKickOnce+1
			p\ThrowABomb=0
		EndIf
	End Function

	Function Player_Action_Sprint(p.tPlayer)

		Select p\Character
			Case CHAR_SON,CHAR_SHA,CHAR_MIG,CHAR_EME,CHAR_PRS:
				If p\Motion\Ground=False Then Player_SetSpeedYUpDownDiff(p,0.75,-1.75)
				Player_SetSpeed(p,p\Physics\SPRINT_SPEED#,true)
			Case CHAR_BEA,CHAR_HON:
				If p\Motion\Ground=False Then Player_SetSpeedYUpDownDiff(p,1.125,0.5)
				Player_SetSpeed(p,p\Physics\SPRINT_SPEED#*0.5,true)
			Default:
				If p\Motion\Ground=False Then Player_SetSpeedYUpDown(p,0.05)
				Player_SetSpeed(p,p\Physics\SPRINT_SPEED#,true)
		End Select

		Select p\Character
			Case CHAR_MET,CHAR_MT3:
				If Player_FrameCheck(p,4,true) and p\ThrowABomb<1 Then p\ThrowABomb=1
				If p\ThrowABomb=1 Then
					p\ThrowABomb=2
					EmitSmartSound(Sound_SpearShoot,p\Objects\Entity)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+2.3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_SHOCK)
				EndIf
		End Select

		If (Not(p\JumpDashTimer>0)) and p\Flags\Targeter=0 Then
			Player_SetSpeed(p,p\SpeedLength#*0.55)
			If p\Motion\Ground=False Then p\Action=ACTION_FULLFALL Else p\Action=ACTION_COMMON
		EndIf

		Player_ActuallyJump(p)

		If abs(p\Rotation#)>30 and Player_CanWallKick(p) Then Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Grind(p.tPlayer)
     
		p\Motion\Speed\y# = -0.5

		Player_ActuallyJump(p)

		Player_ActuallyFall(p)

		If p\GrindTurn=0 Then p\GrindTurn=1
		If ( (p\GrindTurn=1 and Player_IsPlayable(p) and Input\Hold\ActionRoll) Or (p\GrindTurn=2 and (Not(Player_IsPlayable(p) and Input\Hold\ActionRoll))) ) and (Not(p\GrindTurnRestrictTimer>0)) Then
			Select p\GrindTurn
				Case 1: p\GrindTurn=2
				Case 2: p\GrindTurn=1
			End Select
			p\GrindTurnTimer=0.15*secs#
			p\GrindTurnRestrictTimer=0.35*secs#
			EmitSmartSound(Sound_GrindStart,p\Objects\Entity)
		EndIf			

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Climb_Initiate(p.tPlayer)
		Player_SetSpeed(p,0.15)
		Player_SetSpeedY(p,0)
		p\Action=ACTION_CLIMB
	End Function

	Function Player_Action_Climb(p.tPlayer)

		Player_ActuallyFall(p)

		If (Not(p\CanClimbTimer>0)) Then
			Player_ConvertGroundToAir(p)
			p\Motion\Ground = False
			p\Action=ACTION_JUMPFALL
		EndIf

		Player_ActuallyJump(p)

		If abs(p\Rotation#)<17.5 Then p\Action=ACTION_COMMON

		If p\AirBegTooFar Then Player_ActuallyJump(p,true,true) : p\AirBegGround=False

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Bumped_Initiate(p.tPlayer)
		If p\HasVehicle=0 and (Not(p\Action=ACTION_SKYDIVE)) Then
			p\Action = ACTION_BUMPED : p\BumpedTimer=0.5*secs#
		EndIf
	End Function

	Function Player_Action_BumpedJump_Initiate(p.tPlayer)
		If p\HasVehicle=0 and (Not(p\Action=ACTION_SKYDIVE)) Then
			p\JumpTimer=0
			p\Action=ACTION_JUMP : p\JumpMayRiseTimer=1.5*secs#
		EndIf
	End Function

	Function Player_Action_BumpedBounce_Initiate(p.tPlayer, speed#=2, cloud=0)
		Player_ConvertGroundToAir(p) : p\Motion\Ground = False
		Player_SetSpeedY(p,speed#)
		If Not cloud Then
			If p\No#=1 Then EmitSmartSound(Sound_Ninja,p\Objects\Entity)
		ElseIf p\HasVehicle=0 and (Not(p\Action=ACTION_SKYDIVE)) Then
			p\Action=ACTION_BUMPED
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
		
		Player_SetSpeed(p,p\Physics\HOVER_SPEED#,true)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\HOVERFALL_SPEED#*0.3455,true)

		If p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) Then p\Action=ACTION_JUMPFALL

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

	Function Player_Action_Throw_Initiate(p.tPlayer,throwtype=1,limit=2)
		Select p\Character
			Case CHAR_CRE: If throwtype=1 and (Not( (Not(Game\CheeseTimer>0)) and (Not(p\CheeseRestrictTimer>0)) )) Then Return
			Case CHAR_BIG: If throwtype=1 and (Not( (Not(Game\FroggyTimer>0)) and (Not(p\CheeseRestrictTimer>0)) )) Then Return
			Case CHAR_MAR,CHAR_WAV: If (Not(p\BoomerangAway=0)) and throwtype=2 Then Return
			Case CHAR_BLA: If p\Underwater Then Return
			Case CHAR_KNU: If throwtype=1 and p\Underwater Then Return
		End Select
		If (Not(p\ThrowTimer>0)) and p\BombThrown<limit Then
			p\Action=ACTION_THROW
			Select p\Character
				Case CHAR_HBO,CHAR_MPH,CHAR_INF: p\ThrowTimer=0.577*secs#
				Case CHAR_KNU: If throwtype=1 Then p\ThrowTimer=0.577*secs# Else p\ThrowTimer=0.37*secs#
				Case CHAR_CHO,CHAR_BLA,CHAR_STO,CHAR_SON: If throwtype=2 Then p\ThrowTimer=0.577*secs# Else p\ThrowTimer=0.37*secs#
				Default: p\ThrowTimer=0.37*secs#
			End Select
			p\ThrowABomb=0
			Player_PlayAttackVoice(p)
			p\ThrowType=throwtype
			If p\Motion\Ground=False Then p\BombThrown=p\BombThrown+1
			Select p\Character
				Case CHAR_NAC,CHAR_COM: EmitSmartSound(Sound_Shotgun1,p\Objects\Entity)
			End Select
		EndIf
	End Function

	Function Player_Action_Drop_Initiate(p.tPlayer,throwtype=1,limit=4)
		If (Not(p\ThrowTimer>0)) and p\BombThrown<limit Then
			p\ThrowTimer=0.37*secs#
			Player_PlayAttackVoice(p)
			p\ThrowType=throwtype
			Select p\Character
				Case CHAR_TAI: EmitSmartSound(Sound_Throw,p\Objects\Entity)
				Case CHAR_CHA: EmitSmartSound(Sound_Flower,p\Objects\Entity)
			End Select
			Select p\Character
				Case CHAR_CRE:
					Select p\ThrowType
						Case 1: Game\CheeseTimer=0.5*secs# : p\CheeseAttackedCount=p\CheeseAttackedCount+1
						Case 3: Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_CHEESE, 0)
					End Select
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

		If p\Motion\Ground Then
			Player_ActuallyJump(p)
		Else
			Player_SetSpeedYUpDown(p,0.05)
		EndIf

		If Player_FrameCheck(p,4,true) and p\ThrowABomb<1 Then p\ThrowABomb=1
		If p\ThrowABomb=1 Then
			p\ThrowABomb=2
			Select p\Character
				Case CHAR_TAI,CHAR_BAR,CHAR_MPH: EmitSmartSound(Sound_Throw,p\Objects\Entity)
				Case CHAR_ROU,CHAR_BEA: EmitSmartSound(Sound_BatBomb,p\Objects\Entity)
				Case CHAR_CHA: EmitSmartSound(Sound_Flower,p\Objects\Entity)
				Case CHAR_MKN,CHAR_GME: EmitSmartSound(Sound_EnemyCannon,p\Objects\Entity)
				Case CHAR_SIL: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1) : EmitSmartSound(Sound_PsychoThrow,p\Objects\Entity)
				Case CHAR_CHO:
					Select p\ThrowType
					Case 1: EmitSmartSound(Sound_SpearShoot,p\Objects\Entity)
					Case 2: EmitSmartSound(Sound_Bounce,p\Objects\Entity)
					End Select
				Case CHAR_SON: EmitSmartSound(Sound_Wind,p\Objects\Entity)
				Case CHAR_SHA: 
					Select p\ThrowType
					Case 1: EmitSmartSound(Sound_SpearShoot,p\Objects\Entity)
					Case 2: EmitSmartSound(Sound_ChaosControl,p\Objects\Entity)
					End Select
				Case CHAR_MAR:
					Select p\ThrowType
					Case 1: EmitSmartSound(Sound_WaterGun,p\Objects\Entity)
					Case 2: EmitSmartSound(Sound_Ninja,p\Objects\Entity)
					End Select
				Case CHAR_AMY: EmitSmartSound(Sound_Flower1,p\Objects\Entity)
				Case CHAR_BLA: EmitSmartSound(Sound_FireDash,p\Objects\Entity)
				Case CHAR_RAY: EmitSmartSound(Sound_Dart,p\Objects\Entity)
				Case CHAR_ESP,CHAR_JET: EmitSmartSound(Sound_Ninja,p\Objects\Entity)
				Case CHAR_KNU:
					Select p\ThrowType
					Case 1: EmitSmartSound(Sound_KnuxStomp,p\Objects\Entity) : EmitSmartSound(Sound_FireDash,p\Objects\Entity) : ParticleTemplate_Call(p\Particle2, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)
					Case 2: EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
					End Select
				Case CHAR_HBO: EmitSmartSound(Sound_Punch,p\Objects\Entity)
				Case CHAR_NAC,CHAR_COM: EmitSmartSound(Sound_Shotgun2,p\Objects\Entity)
				Case CHAR_STO:
					Select p\ThrowType
					Case 1: EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
					Case 2: EmitSmartSound(Sound_Slap,p\Objects\Entity)
					End Select
				Case CHAR_WAV:
					Select p\ThrowType
					Case 1: EmitSmartSound(Sound_BatBomb,p\Objects\Entity)
					Case 2: EmitSmartSound(Sound_Ninja,p\Objects\Entity)
					End Select
				Case CHAR_MIG: EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
				Case CHAR_TIK: EmitSmartSound(Sound_Spirit,p\Objects\Entity)
				Case CHAR_MET,CHAR_MT3: EmitSmartSound(Sound_SpearShoot,p\Objects\Entity)
				Case CHAR_INF: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1, 0, 1) : EmitSmartSound(Sound_Ruby1+Rand(1,6)-1,p\Objects\Entity)
			End Select
			Select p\Character
				Case CHAR_MKN,CHAR_GME:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_ROCKET)
				Case CHAR_MPH:
					Object_Bomb_Create5(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Animation\Direction#-180, 0, BOMB_MINION)
				Case CHAR_BAR:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+6, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_ICE, 0)
				Case CHAR_WAV:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_EXPLOSIVE, -0.125)
								Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_EXPLOSIVE, +0.125)
						Case 2: p\BoomerangAway=1 : Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.6, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_GEAR, 1)
					End Select
				Case CHAR_BEA:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BOMB, +0.125)
				Case CHAR_SIL:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BOX)
						Case 2: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_KNIFE)
					End Select
				Case CHAR_SHA:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SPEAR)
						Case 2: p\ChaosControlActiveTimer=3*secs# : p\PsychoChargeTimer=5*secs#
								ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1, 0, 2)
					End Select
				Case CHAR_CHO:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SPEARWATER)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BLOB)
					End Select
				Case CHAR_SON:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_WIND)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180+360, 0, BOMB_HURRICANE)
					End Select
				Case CHAR_CHA:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_FLOWER, 0)
				Case CHAR_CRE:
					Select p\ThrowType
						Case 1: Game\CheeseTimer=0.5*secs# : p\CheeseAttackedCount=p\CheeseAttackedCount+1
						Case 2: Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.53, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_TYPHOON)
						Case 3: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_CHEESE)
					End Select
				Case CHAR_TAI:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_RING, 0)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_RING, -0.25)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_RING, +0.25)
				Case CHAR_ROU:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_HEART, 0)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_HEART, -0.25)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+5, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_HEART, +0.25)
				Case CHAR_AMY:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.53, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_TYPHOON)
				Case CHAR_BIG:
					Select p\ThrowType
						Case 1: Game\FroggyTimer=0.5*secs# : p\CheeseAttackedCount=p\CheeseAttackedCount+1
						Case 3: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_FROGGY)
					End Select
				Case CHAR_ESP:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+2.3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BLADE)
				Case CHAR_MAR:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_BUBBLES)
						Case 2: p\BoomerangAway=1 : Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.6, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BOOMERANG, 1)
					End Select
				Case CHAR_RAY:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+1.2, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_DART)
				Case CHAR_BLA:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_FLAME)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180+360, 0, BOMB_FIREBALL)
					End Select
				Case CHAR_KNU:
					Select p\ThrowType
						Case 1: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180+360, 0, BOMB_FIREBALL)
						Case 2: Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+6, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_ROCK, 0)
					End Select
				Case CHAR_HBO:
					Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180+360, 0, BOMB_BIGBOMB)
				Case CHAR_NAC:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BULLET, 1)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BULLET, 2)
				Case CHAR_STO:
					Select p\ThrowType
						Case 1: Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+10, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_TIRE)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180+360, 0, BOMB_HURRICANE)
					End Select
				Case CHAR_JET:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.6, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_LEAF)
				Case CHAR_TDL:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_CURSE)
				Case CHAR_COM:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_BULLET, 3)
				Case CHAR_MIG:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+6, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_ROCK, 0)
				Case CHAR_TIK:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.53, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_JUSTICE)
				Case CHAR_MET,CHAR_MT3:
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+2.3, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_SHOCK)
				Case CHAR_INF
					Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180+360, 0, BOMB_CUBETRAIL)
			End Select
		EndIf

		If (Not(Animating(p\Objects\Mesh))) Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
		EndIf

		Select p\Character
			Case CHAR_HBO:
				If Player_FrameCheck(p,3) Or Player_FrameCheck(p,5) Or Player_FrameCheck(p,7) Then
					ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_BOMB, p\Objects\Extra) : EmitSmartSound(Sound_Explode,p\Objects\Entity)
				EndIf
			Case CHAR_MET,CHAR_MT3:
				If p\ThrowTimer>0.3*secs# Then p\ThrowTimer=0.3*secs#
				If Not(p\ThrowTimer>0) Then
					If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
				EndIf
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_LightDash(p.tPlayer)

		If (Not(p\LightDashTimer>0)) Or (Not(p\RingDashStopTimer>0)) Then
			p\Action=ACTION_JUMPFALL
			Player_SetSpeed(p,2.3)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Shoot_Initiate(p.tPlayer,mode=1)
		If (Not(p\ShootCooldownTimer>0)) and p\BombThrown<8 Then
			Select mode
				Case 1: Player_SetSpeed(p,0.5,true)
				Case 2: Player_SetSpeed(p,0.25,true)
			End Select
			If (Not(p\Action=ACTION_TORNADO)) Then
				Player_PlayAttackVoice(p)
				If p\Action=ACTION_HOVER Or p\Action=ACTION_FLY Then p\Action=ACTION_SHOOTHOVER Else p\Action=ACTION_SHOOT
				If p\Motion\Ground=False and (p\Character=CHAR_EGR) Then p\BombThrown=p\BombThrown+1
			Else
				p\TornadoShoot=1
			EndIf
		EndIf
	End Function

	Function Player_Action_Shoot(p.tPlayer)

		If p\Motion\Ground Then
			Player_ActuallyJump(p)
		Else
			Player_JumpActions(p)
			Player_ActuallyLand(p)
		EndIf

		If (Not(Animating(p\Objects\Mesh))) Then
			If p\Motion\Ground Then
				p\Action=ACTION_COMMON
			Else
				p\Action=ACTION_JUMPFALL
			EndIf
			Player_Action_Shoot_Shot(p)
		EndIf

	End Function

	Function Player_Action_Shoot_AimBegin(p.tPlayer)
		If p\Aiming=0 Then p\Aiming=1 : p\JustStartedAimingTimer=0.25*secs#
	End Function

	Function Player_Action_Shoot_Shot(p.tPlayer)
		Select p\Character
			Case CHAR_GAM,CHAR_EGG,CHAR_CHW,CHAR_TMH: Player_Action_Shoot_AimShot(p)
			Case CHAR_BET: Player_Action_Shoot_AimlessAimShot(p)
			Default: Player_Action_Shoot_NormalShot(p)
		End Select
	End Function

	Function Player_Action_Shoot_NormalShot(p.tPlayer)
		Select p\Character
			Case CHAR_EGR:
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_ORB, -1)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_ORB)
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_ORB, 1)
			Default:
				Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_SHOT)
		End Select
		p\ShootCooldownTimer=0.05*secs#
		EmitSmartSound(Sound_EnemyShot,p\Objects\Entity)
		p\ForceShotWalkTimer=0.24*secs#
	End Function

	Function Player_Action_Shoot_AimShot(p.tPlayer)
		If Player_IsPlayable(p) and p\Aiming=2 and (Not(p\JustStartedAimingTimer>0)) Then
			foundtargetonce=false
			For o.tObject = Each tObject
				If o\AimedAt=1 Then
					If foundtargetonce Then
						If EntityDistance(o\Entity,p\Objects\Entity)<EntityDistance(p\Objects\ScannerTarget\Entity,p\Objects\Entity) Then p\Objects\ScannerTarget=o
					Else
						p\Objects\ScannerTarget=o
						foundtargetonce=true
					EndIf
				EndIf
			Next
			If foundtargetonce Then
				Select p\Character
				Case CHAR_CHW:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra2,1), EntityY(p\Objects\Extra2,1), EntityZ(p\Objects\Extra2,1), 0, p\Animation\Direction#-180, 0, BOMB_BULLET3, 0, True, p\Objects\ScannerTarget\Position\x#, p\Objects\ScannerTarget\Position\y#, p\Objects\ScannerTarget\Position\z#)
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

	Function Player_Action_Punch_Initiate(p.tPlayer,limit=1)
		If p\AirKickOnce<limit Then
			Player_PlayAttackVoice(p)
			If p\Motion\Ground Then
				p\Action=ACTION_PUNCH
				p\PunchTimer=1.28*secs#
				p\PunchRestrictTimer=0.4*secs#
				Select p\Character
					Case CHAR_SON,CHAR_SHA,CHAR_NAC,CHAR_JET,CHAR_PRS,CHAR_INF: EmitSmartSound(Sound_SpinKick,p\Objects\Entity)
					Case CHAR_BIG: EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
					Case CHAR_VEC: p\ThrowABomb=0 : EmitSmartSound(Sound_Punch,p\Objects\Entity)
					Case CHAR_SIL,CHAR_MPH: EmitSmartSound(Sound_PsychoDash,p\Objects\Entity)
					Case CHAR_MET,CHAR_MT3: EmitSmartSound(Sound_DashElectro,p\Objects\Entity)
					Case CHAR_EME,CHAR_GME:
						Select p\CharacterMode
						Case CHAR_SON,CHAR_ESP: EmitSmartSound(Sound_SpinKick,p\Objects\Entity)
						Default: EmitSmartSound(Sound_Punch,p\Objects\Entity)
						End Select
					Case CHAR_EGG: EmitSmartSound(Sound_Sting,p\Objects\Entity) : EmitSmartSound(Sound_Punch,p\Objects\Entity)
					Case CHAR_TMH: EmitSmartSound(Sound_EnemyCannon,p\Objects\Entity) : EmitSmartSound(Sound_Punch,p\Objects\Entity)
					Case CHAR_AMY: EmitSmartSound(Sound_Hammer,p\Objects\Entity)
					Default: EmitSmartSound(Sound_Punch,p\Objects\Entity)
				End Select
				Select p\Character
					Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3: p\JumpDashTimer=0.25*secs# : p\PunchNumber=4
					Case CHAR_SON,CHAR_SHA,CHAR_NAC,CHAR_JET,CHAR_BIG,CHAR_VEC,CHAR_EGG,CHAR_PRS,CHAR_TMH,CHAR_AMY,CHAR_INF: p\PunchNumber=1
					Default:
						If Not(p\PunchTimer>0) Then
							p\PunchNumber=1
						Else
							p\PunchNumber=p\PunchNumber+1
							Select p\Character
								Case CHAR_ROU,CHAR_OME,CHAR_MIG,CHAR_HON,CHAR_SHD,CHAR_TIA,CHAR_ESP:
									If p\PunchNumber>2 Then p\PunchNumber=1
								Case CHAR_EME,CHAR_GME:
									Select p\CharacterMode
									Case CHAR_SON,CHAR_ESP: p\PunchNumber=1
									Default: If p\PunchNumber>2 Then p\PunchNumber=1
									End Select
								Default:
									If p\PunchNumber>3 Then p\PunchNumber=1
							End Select
							If p\PunchNumber=3 and (p\Character=CHAR_STO Or p\Character=CHAR_TIK) Then EmitSmartSound(Sound_Slap,p\Objects\Entity)
						EndIf
				End Select
			Else
				p\Action=ACTION_THRUST
				Select p\Character
					Case CHAR_BIG: EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
					Case CHAR_VEC: EmitSmartSound(Sound_Punch,p\Objects\Entity) : EmitSmartSound(Sound_Dash,p\Objects\Entity)
					Case CHAR_SIL,CHAR_MPH: EmitSmartSound(Sound_PsychoDash,p\Objects\Entity)
					Case CHAR_MET,CHAR_MT3: EmitSmartSound(Sound_DashElectro,p\Objects\Entity)
					Default:
						EmitSmartSound(Sound_Punch,p\Objects\Entity)
						Select p\Character
							Case CHAR_STO,CHAR_TIK: EmitSmartSound(Sound_Slap,p\Objects\Entity)
						End Select
				End Select
				Select p\Character
					Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3: p\JumpDashTimer=0.25*secs# : p\PunchNumber=4
					Case CHAR_CHO: p\PunchNumber=3
					Default: p\PunchNumber=1
				End Select
			EndIf
			If p\Motion\Ground=False Then p\AirKickOnce=p\AirKickOnce+1
		EndIf
	End Function

	Function Player_Action_Punch(p.tPlayer)

		Select p\Action
			Case ACTION_PUNCH:
				Player_ActuallyFall(p)
				Player_ActuallyJump(p)
				If (Not(p\PunchRestrictTimer>0)) Then Player_SkillActions(p)
			Case ACTION_THRUST:
				Player_ActuallyLand(p)
		End Select

		Select p\Character
			Case CHAR_SON,CHAR_SHA,CHAR_NAC,CHAR_PRS,CHAR_INF:
				Player_SetSpeed(p,0.357,true)
			Case CHAR_VEC:
				If p\Motion\Ground Then
					Player_SetSpeed(p,0.8*p\Physics\UNDERWATERTRIGGER#,true)
				Else
					Player_SetSpeed(p,1.6*p\Physics\UNDERWATERTRIGGER#,true)
				EndIf
			Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3:
				Player_SetSpeed(p,p\Physics\JUMPDASH_SPEED#*1.5,true)
			Case CHAR_JET:
				Player_SetSpeed(p,0.444,true)
			Case CHAR_EME,CHAR_GME:
				Select p\CharacterMode
				Case CHAR_SON,CHAR_ESP:
					Player_SetSpeed(p,0.357,true)
				Default:
					If p\Motion\Ground and p\PunchNumber=3 Then
						Player_SetSpeed(p,1.3*p\Physics\UNDERWATERTRIGGER#,true)
					Else
						Player_SetSpeed(p,0.8*p\Physics\UNDERWATERTRIGGER#,true)
					EndIf
				End Select
			Default:
				If p\Motion\Ground and p\PunchNumber=3 Then
					Player_SetSpeed(p,1.3*p\Physics\UNDERWATERTRIGGER#,true)
				Else
					Player_SetSpeed(p,0.8*p\Physics\UNDERWATERTRIGGER#,true)
				EndIf
		End Select

		If ((Not(Animating(p\Objects\Mesh))) Or (p\PunchNumber=4 and (Not(p\JumpDashTimer>0)))) and p\Flags\Targeter=0 Then
			If p\Motion\Ground Then
				p\Action=ACTION_COMMON
			Else
				Select p\Character
					Case CHAR_VEC,CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_JET,CHAR_MT3: p\Action=ACTION_JUMPFALL
					Default: p\Action=ACTION_FALL
			End Select
			EndIf

			Select p\Character
				Case CHAR_KNU,CHAR_BAR,CHAR_MKN,CHAR_COM:
					If p\PunchNumber=3 Then EmitSmartSound(Sound_KnuxStomp,p\Objects\Entity)
				Case CHAR_SHD:
					Select p\PunchNumber
					Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_HANDBLADE)
					Case 2: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Animation\Direction#-180, 0, BOMB_HANDBLADE)
					End Select
				Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3:
					Player_SetSpeed(p,p\SpeedLength#*0.75)
				Case CHAR_JET:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_HURRICANE)
				Case CHAR_AMY:
					EmitSmartSound(Sound_Grab,p\Objects\Entity)
			End Select
		EndIf

		Select p\Character
			Case CHAR_CHO:
				If (Not(p\ChaosStretchSpawnerTimer>0)) Then
					p\ChaosStretchSpawnerTimer=0.02*secs#
					Select p\PunchNumber
						Case 1:
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
						Case 2:
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
						Case 3:
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
						Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
					End Select
				EndIf
			Case CHAR_AMY:
				If (Not(p\ChaosStretchSpawnerTimer>0)) Then
					p\ChaosStretchSpawnerTimer=0.02*secs#
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra,1), EntityY(p\Objects\Extra,1), EntityZ(p\Objects\Extra,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
				EndIf
				ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_HEARTS, p\Objects\Extra)
			Case CHAR_HBO:
				Select p\PunchNumber
					Case 1,2:
						If Player_FrameCheck(p,5) Then
							ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_BOMB, p\Objects\Extra) : EmitSmartSound(Sound_Explode,p\Objects\Entity)
						EndIf
					Case 3:
						If Player_FrameCheck(p,3) Or Player_FrameCheck(p,5) Or Player_FrameCheck(p,7) Then
							ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_BOMB, p\Objects\Extra) : EmitSmartSound(Sound_Explode,p\Objects\Entity)
						EndIf
				End Select
			Case CHAR_VEC:
				If Player_FrameCheck(p,4,true) and p\ThrowABomb<1 Then p\ThrowABomb=1
				If p\ThrowABomb=1 and p\Flags\Targeter=0 Then
					p\ThrowABomb=2
					EmitSmartSound(Sound_GlideStart2,p\Objects\Entity)
					EmitSmartSound(Sound_Gum,p\Objects\Entity)
					Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+4, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_GUM)
				EndIf
			Case CHAR_SIL,CHAR_MPH:
				Player_SetSpeedY(p,0)
			Case CHAR_EGG,CHAR_TMH:
				If (Not(p\ChaosStretchSpawnerTimer>0)) Then
					p\ChaosStretchSpawnerTimer=0.02*secs#
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\Extra2,1), EntityY(p\Objects\Extra2,1), EntityZ(p\Objects\Extra2,1), 0, p\Animation\Direction#-180, 0, BOMB_PUNCH)
				EndIf
			Case CHAR_MET,CHAR_MT3:
				Player_SetSpeedY(p,0)
				Player_ActuallyJump(p)
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Swipe_Initiate(p.tPlayer, limit=1)
		If p\Motion\Ground Or p\AirKickOnce<limit Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_SWIPE
			p\SpecialSpinTimer=1*secs#
			Select p\Character
				Case CHAR_AMY: EmitSmartSound(Sound_PikoHammer,p\Objects\Entity)
				Case CHAR_WAV: EmitSmartSound(Sound_Wrench,p\Objects\Entity)
				Case CHAR_MET: Player_CreateRazer.tRazer(p,p\Objects\Mesh,0.8875,2)
				Case CHAR_MT3: Player_CreateRazer.tRazer(p,p\Objects\Mesh,0.8875,3)
			End Select
			If p\Motion\Ground=False Then p\AirKickOnce=p\AirKickOnce+1
		EndIf
	End Function

	Function Player_Action_Swipe(p.tPlayer)

		If p\Motion\Ground Then Player_ActuallyJump(p)

		If Player_FrameCheck(p,2) Or Player_FrameCheck(p,10) Then
			Select p\Character
				Case CHAR_MET,CHAR_MT3: EmitSmartSound(Sound_Psychokinesis,p\Objects\Entity)
				Default: EmitSmartSound(Sound_PunchSmall,p\Objects\Entity)
			End Select
			Select p\Character
				Case CHAR_TAI,CHAR_RAY,CHAR_TDL: EmitSmartSound(Sound_Swipe,p\Objects\Entity)
				Case CHAR_EME,CHAR_GME: If p\CharacterMode=CHAR_TAI Or p\CharacterMode=CHAR_RAY Then EmitSmartSound(Sound_Swipe,p\Objects\Entity)
			End Select
		EndIf

		Select p\Character
			Case CHAR_TAI,CHAR_RAY,CHAR_TDL: Player_CreateRazer.tRazer(p,p\Objects\Mesh,0.45,1)
			Case CHAR_AMY: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_HEARTS, p\Objects\Entity)
			Case CHAR_EME,CHAR_GME: If p\CharacterMode=CHAR_TAI Or p\CharacterMode=CHAR_RAY Then Player_CreateRazer.tRazer(p,p\Objects\Mesh,0.45,1)
		End Select

		i = false
		If Player_IsPlayable(p) Then
			Select p\Character
				Case CHAR_TAI,CHAR_TDL: If Not(Input\Hold\ActionSkill2) Then i = true
				Case CHAR_MET,CHAR_MT3: If Not(Input\Hold\ActionSkill3) Then i = true
				Default: If Not(Input\Hold\ActionSkill1) Then i = true
			End Select
		EndIf
		If i and (Not(p\SpecialSpinTimer>0)) and p\Flags\Targeter=0 Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Uppercut_Initiate(p.tPlayer)
		If p\Motion\Ground Then
			Player_PlayAttackVoice(p)
			p\Motion\Ground=False
			p\Action=ACTION_UPPERCUT
			EmitSmartSound(Sound_Punch,p\Objects\Entity)
			Player_SetSpeed(p,0.5*p\Physics\UNDERWATERTRIGGER#,true)
			Player_SetSpeedY(p,1.222*p\Physics\UNDERWATERTRIGGER#)
		EndIf
	End Function

	Function Player_Action_Uppercut(p.tPlayer)

		If Animating(p\Objects\Mesh)=False and p\Flags\Targeter=0 Then p\Action=ACTION_FALL

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

		If (Not(Player_IsPlayable(p) and Input\Hold\ActionSkill1)) Then
			If (Not(p\SpecialSpinTimer>0)) and p\Flags\Targeter=0 Then
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
			If p\No#=1 and p\Character=CHAR_HON Then EmitSmartSound(Sound_GlideStart,p\Objects\Entity)
			p\Action=ACTION_FLUTTER
			If p\LevitatedOnce=0 Then p\GlideTimer=2.24*secs# : p\LevitatedOnce=1 : Player_SetSpeedY(p,1.05632)
			p\GlideStartTimer=2.24*secs#
			Select p\Character
				Case CHAR_MAR: Player_SetSpeed(p,p\SpeedLength#-0.2,true)
				Case CHAR_HON: Player_SetSpeed(p,2.25,true)
			End Select
			Player_SetSpeedY(p,0.32,true)
			Player_FollowerHolding_EveryoneJumpDashes(p)
		EndIf
		EndIf
	End Function

	Function Player_Action_Flutter(p.tPlayer)

		Player_ActuallyLand(p)

		If p\GlideStartTimer<1.868*secs# and ((p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump))) Or (Not(p\GlideTimer>0))) Then
			Select p\Character
				Case CHAR_HON: Player_SetSpeed(p,1.85,true)
				Default: Player_SetSpeed(p,1.25,true)
			End Select
			p\GlideRestartTimer=0.08*secs#/2.0
			p\Action=ACTION_JUMPFALL
		EndIf

		If (Not(p\JumpActionRestrictTimer>0)) Then
			If p\GlideStartTimer>1.68*secs# Then
				Player_SetSpeedY(p,p\Physics\FLUTTERFALL_SPEED#,true)
			ElseIf p\GlideStartTimer>1.28*secs# Then
				Player_SetSpeedY(p,p\Physics\FLUTTERFALL_SPEED#*1.5,true)
			Else
				Player_SetSpeedY(p,p\Physics\FLUTTERFALL_SPEED#*2,true)
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Buoy_Initiate(p.tPlayer)
		If (Not(p\Action=ACTION_BUOY)) and (Not(p\WasInBuoyTimer>0)) and p\AirBegTooFar=False Then
			Player_PlayAttackVoice(p)
			p\Action=ACTION_BUOY
			Player_ConvertGroundToAir(p)
			p\Motion\Ground = False
			p\Motion\Align\x# = 0.0 : p\Motion\Align\y# = 1.0 : p\Motion\Align\z# = 0.0
			If p\WasInBuoyOnce=0 Or p\Objects\Position\y#<p\FlyDistanceLimit Then p\FlyDistanceLimit=p\Objects\Position\y#
		EndIf
	End Function

	Function Player_Action_Buoy(p.tPlayer)

		Player_ActuallyJump(p)

		p\WasInBuoyTimer=0.8*secs#
		p\WasInBuoyOnce=1
   
		If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP)) Then
			If p\Underwater=1 Or EntityY(p\Objects\Spine,1)<Game\Stage\Properties\WaterLevel Then
				If p\Objects\Position\y#>p\FlyDistanceLimit+130 Then
					Player_SetSpeedY(p,0)
				Else
					Player_SetSpeedY(p,1*p\Physics\UNDERWATERTRIGGER#)
				EndIf
			Else
				Player_SetSpeedY(p,0)
			EndIf
			If p\UnderwaterFeet=0 Then p\Action=ACTION_JUMPFALL
		EndIf

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
						p\PsychokinesisTimer=0.5*secs#
						EmitSmartSound(Sound_PsychoHold,p\Objects\Entity)
						If Not(p\Action=ACTION_LEVITATE) Then p\Action=ACTION_PSYCHO : p\ThrowType=1
					EndIf
				Case 1:
					If p\SpeedLength#>0 and Game\Gameplay\PsychoBombCount>0 Then
						If (Not(p\PsychoChargeTimer>0)) Then
							Player_PlayAttackVoice(p)
							Game\Gameplay\PsychoBombCount=0
							p\PsychokinesisThrowTimer=0.1*secs#
							p\Psychokinesis=0
							p\PsychokinesisTimer=0
							p\PsychoChargeTimer=3*secs#
							ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1)
							EmitSmartSound(Sound_PsychoThrow,p\Objects\Entity)
							If Not(p\Action=ACTION_LEVITATE) Then p\Action=ACTION_PSYCHO : p\ThrowType=2
						EndIf
					Else
						Game\Gameplay\PsychoBombCount=0
						p\Psychokinesis=0
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
		If p\Motion\Ground=False and (Not(p\InvisibilityRestrictTimer>0)) and p\BombThrown<1 Then
			p\Action=ACTION_TURN
			If p\Motion\Ground=False Then p\BombThrown=p\BombThrown+1
		EndIf
	End Function

	Function Player_Action_Turn(p.tPlayer)

		Player_Action_Fall(p)

		Player_SetSpeedY(p,-0.1,true)

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
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#*0.875,true)
			Player_SetSpeedY(p,0,true)
			If Not(p\SoarTimer>0) Then p\SoarTimer=6*secs#
		EndIf
	End Function

	Function Player_Action_Soar(p.tPlayer)

		Player_ActuallyLand(p)

		If p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) Then
			Player_SetSpeed(p,1.25,true)
			p\GlideRestartTimer=0.14*secs#/2.0
			If p\Motion\Ground Then Player_ConvertGroundToAir(p) : p\Motion\Ground=False
			p\Action=ACTION_JUMPFALL
		EndIf

		If p\No#=1 And (Not(p\SoarTimer>0)) Then
			Player_SetSpeed(p,1.25,true)
			p\Action=ACTION_FALL
		EndIf

		If (Not(p\JumpActionRestrictTimer>0)) and (Not(p\JustSoaredTimer>0)) Then Player_SetSpeedY(p,p\Physics\GLIDEFALL_SPEED#)

		If p\No#=1 and Player_IsPlayable(p) and Input\Pressed\ActionSkill2 and (Not(p\Action=ACTION_SOARFLAP)) and (Not(p\JustSoaredTimer>0)) and p\Motion\Ground=False Then
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
			Player_SetSpeed(p,(p\Physics\JUMPDASH_SPEED#-0.235),true)
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
	
		If (Not(p\DoubleJumpTimer>0)) and p\DoubleJump=1 Then p\Action=ACTION_JUMPFALL

		Player_ActuallyLand(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Sleet_Initiate(p.tPlayer)
		If p\JumpDashedOnce=0 and (Not(p\GlideRestartTimer>0)) Then
			p\JumpDashedOnce=1
			Player_PlayJumpActionVoice(p)
			p\Action=ACTION_SLEET
			p\GlideTimer=0.625*secs#
			If p\No#=1 Then EmitSmartSound(Sound_GlideStart3,p\Objects\Entity)
			Player_SetSpeed(p,p\Physics\GLIDE_SPEED#*1.5,true)
			Player_SetSpeedY(p,0,true)
		EndIf
	End Function

	Function Player_Action_Sleet(p.tPlayer)

		Player_ActuallyLand(p)

		If (p\No#=1 and (Not(Player_IsPlayable(p) and Input\Hold\ActionJump))) Or (Not(p\GlideTimer>0)) Then
			Player_SetSpeed(p,1.25,true)
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

		If (Not(p\GlideTimer>0)) Or (Not(Player_IsPlayable(p) and Input\Hold\ActionSkill2)) Then
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

		If (Player_IsPlayable(p) and Input\Hold\ActionJump=False And p\Motion\Speed\y#>p\Physics\JUMP_STRENGTH_VARIABLE#) Then
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
		If ( (p\GrindTurn=1 and Player_IsPlayable(p) and Input\Hold\ActionRoll) Or (p\GrindTurn=2 and (Not(Player_IsPlayable(p) and Input\Hold\ActionRoll))) ) and (Not(p\GrindTurnRestrictTimer>0)) Then
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

		If Player_IsPlayable(p) and Input\Pressed\ActionDrift Then
			p\Action=ACTION_BOARDDRIFT
			Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#-0.4,true)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardJump(p.tPlayer)

		Player_Action_BoardTrick_Initiate(p)

		If (Player_IsPlayable(p) and Input\Hold\ActionJump=False And p\Motion\Speed\y#>p\Physics\JUMP_STRENGTH_VARIABLE#) Then
			p\Motion\Speed\y# = p\Physics\JUMP_STRENGTH_VARIABLE#
		End If

		Player_ActuallyLand(p)

		If (Not(Player_IsPlayable(p) and Input\Hold\ActionJump)) and p\JumpTimer>0.93*secs# Then
			p\Action=ACTION_BOARDFALL
		EndIf

		Player_Action_Board_GrindTurn(p)

		If Game\Vehicle=8 Then
			If (Not(p\ForceJumpTimer>0)) and p\Objects\Position\y#<Game\Stage\Properties\WaterLevel+1 Then p\Action=ACTION_BOARD
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

		If (Not(Player_IsPlayable(p) and Input\Hold\ActionDrift)) Then p\Action=ACTION_BOARD

		If p\DriftDirection=0 Then
			Select(Rand(1,2))
				Case 1: p\DriftDirection=1
				Case 2: p\DriftDirection=-1
			End Select
		EndIf

		If (Player_IsPlayable(p) and Input\Hold\Left) Then p\DriftDirection=-1
		If (Player_IsPlayable(p) and Input\Hold\Right) Then p\DriftDirection=1

		Player_Action_Board_GrindTurn(p)

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardFall(p.tPlayer)

		Player_Action_BoardTrick_Initiate(p)

		Player_ActuallyLand(p)

		Player_Action_Board_GrindTurn(p)

		If Game\Vehicle=8 Then
			If (Not(p\ForceJumpTimer>0)) and p\Objects\Position\y#<Game\Stage\Properties\WaterLevel+1 Then p\Action=ACTION_BOARD
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_BoardTrick_Initiate(p.tPlayer)
		If p\No#=1 Then
		If Input\Pressed\ActionSkill2 and ((Not(p\Action=ACTION_BOARDTRICK)) Or p\Physics\TRICK_ANGLE#<180) and p\TrickCounter<5 Then
			Gameplay_AddScore(50)
			p\TrickTimer=0.625*secs#
			For ppp.tPlayer = Each tPlayer
				If ppp\No#>0 and (ppp\Action=ACTION_BOARDTRICK Or ppp\Action=ACTION_BOARDFALL Or ppp\Action=ACTION_BOARDJUMP) Then
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

		If (Not(p\JumpMayRiseTimer>0)) and p\No#=1 Then
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
		If (p\Motion\Ground Or p\Character=CHAR_TIA) and (Not(p\ShootCooldownTimer>0)) Then
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

		If (((Not(Input\Hold\ActionSkill3)) and (Not(p\Character=CHAR_TIA))) Or ((Not(Input\Hold\ActionSkill1)) and (p\Character=CHAR_TIA))) Or (Not(p\PunchNumber<10)) Then
			p\PunchTimer=0
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_JUMPFALL
			Select p\Character
				Case CHAR_TIA: p\ShootCooldownTimer=1.82*secs#
			End Select
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_ShootHover(p.tPlayer)

		Player_ActuallyLand(p)
		
		Player_SetSpeed(p,p\Physics\HOVER_SPEED#,true)

		If Not(p\JumpActionRestrictTimer>0) Then Player_SetSpeedY(p,p\Physics\HOVERFALL_SPEED#*0.3455,true)

		If (Not(Animating(p\Objects\Mesh))) Then
			Select p\Character
				Case CHAR_EGR: p\Action=ACTION_FLY
				Default: p\Action=ACTION_HOVER
			End Select
			Player_Action_Shoot_Shot(p)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Skydive(p.tPlayer)

		Player_ActuallyLand(p)

		If p\Motion\Ground=False Then
			If Input\Hold\ActionRoll Then
				Player_SetSpeedYAlways(p,p\Physics\SKYDIVE_SPEED#*2,true)
			Else
				Player_SetSpeedYAlways(p,p\Physics\SKYDIVE_SPEED#,true)
			EndIf
		EndIf

		If (Not(Game\CamLock>0)) and p\No#=1 Then
			Game\CamLock2=0.1*secs#
			cam\Lock\PreviousPos=cam\Lock\Pos
			cam\Lock\Pos=0
			cam\Lock\Immediate=0
			cam\Lock\Rotation\x#=85 : cam\Lock\Rotation\y#=p\Animation\Direction#+180 : cam\Lock\Rotation\z#=0
			If cam\Lock\Rotation\y#>=360 Then cam\Lock\Rotation\y#=cam\Lock\Rotation\y#-360
			If cam\Lock\Rotation\y#<0 Then cam\Lock\Rotation\y#=cam\Lock\Rotation\y#+360
			cam\Lock\Zoom#=30+CAMERA_CONTROL_SIZEFACTOR#*p\ScaleFactor#*0.5
			cam\Lock\Speed#=5
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Glider(p.tPlayer)

		If p\No#=1 and (Not(Game\ControlLock>0)) Then Player_ActuallyLand(p)

		If p\Motion\Ground=False Then
			If Input\Hold\ActionJump Then
				Player_SetSpeedYAlways(p,-12.5*0.020322*p\Physics\SKYDIVE_SPEED#*2,true)
			ElseIf Input\Hold\ActionRoll Then
				Player_SetSpeedYAlways(p,14*0.020322*p\Physics\SKYDIVE_SPEED#*2,true)
			Else
				Player_SetSpeedYAlways(p,0.020322*p\Physics\SKYDIVE_SPEED#,true)
			EndIf
		Else
			If Input\Hold\ActionJump Then
				Player_ConvertGroundToAir(p)
				p\Motion\Ground = False
				Player_SetSpeedYAlways(p,-10*0.020322*p\Physics\SKYDIVE_SPEED#*2,true)
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Freeze_Initiate(p.tPlayer)
		If Game\Vehicle=0 and Game\Invinc=0 Then
			EmitSmartSound(Sound_Bounce,p\Objects\Entity)
			p\Action=ACTION_FREEZE
			Player_CreateRazer.tRazer(p,p\Objects\Mesh,1,4,1+0.5*p\ScaleFactor#,1+0.5*p\ScaleFactor#,1+0.5*p\ScaleFactor#,p\WasGrabbedTimer)
		EndIf
	End Function

	Function Player_Action_Freeze_Initiate2(p.tPlayer)
		If (Not(Game\Shield=OBJTYPE_BSHIELD Or p\Character=CHAR_MAR Or p\Character=CHAR_BAR)) Then
			If (Not(p\WasGrabbedTimer>0)) and Game\Invinc=0 Then
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

		If Game\MachLock>0 and Menu\Stage<0 Then p\Animation\Direction#=180

		If Not(p\WasGrabbedTimer>0) Then
			If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Hookshot_Initiate(p.tPlayer, limit=2)
		If (Not(p\ThrowTimer>0)) and p\BombThrown<limit Then
			p\Action=ACTION_HOOKSHOT
			p\ThrowTimer=0.37*secs# : p\ThrowABomb=0
			Player_PlayAttackVoice(p)
			If p\Motion\Ground=False Then p\BombThrown=p\BombThrown+1
			Player_SetSpeedYUpDown(p,0.05)
		EndIf
	End Function

	Function Player_Action_Hookshot(p.tPlayer)

		If Player_FrameCheck(p,4,true) and p\ThrowABomb<1 Then p\ThrowABomb=1
		If p\ThrowABomb=1 Then
			p\ThrowABomb=2
			EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
			Object_Bomb_Create.tBomb(p, p\Objects\Position\x#, p\Objects\Position\y#+0.53, p\Objects\Position\z#, 0, p\Animation\Direction#-180, 0, BOMB_HOOKSHOT)
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

			If Player_IsPlayable(p) and p\Motion\Ground and Input\Pressed\ActionDrift Then
				p\Action=ACTION_CARDRIFT
				Player_SetSpeed(p,p\Physics\SPINDASH_SPEED#-0.4,true)
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_CarDrift(p.tPlayer)
        
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)
		Create_Spark.tSpark(p,p\Objects\Entity,MESHES(Mesh_Spark),cam\Entity, p\Animation\Direction#, 0, p\No#)

		If p\Motion\Ground=False and (Not(p\ForceJumpTimer>0)) Then p\Action=ACTION_CARFALL

		Player_ActuallyJump(p)

		If (Not(Player_IsPlayable(p) and Input\Hold\ActionDrift)) Then p\Action=ACTION_CAR

		If p\DriftDirection=0 Then
			Select(Rand(1,2))
				Case 1: p\DriftDirection=1
				Case 2: p\DriftDirection=-1
			End Select
		EndIf

		If (Player_IsPlayable(p) and Input\Hold\Left) Then p\DriftDirection=-1
		If (Player_IsPlayable(p) and Input\Hold\Right) Then p\DriftDirection=1

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
			For i=1 to 4
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
		If (Not(p\InvisibilityRestrictTimer>0)) and p\BombThrown<1 Then
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

			If Input\Pressed\ActionSkill2 and (Not(p\TornadoChangeTimer>0)) Then
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

			If Game\Vehicle=7 and p\TornadoShoot=1 Then
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