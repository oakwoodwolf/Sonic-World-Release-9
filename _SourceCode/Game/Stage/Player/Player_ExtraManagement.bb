Function Player_ExtraHandle(p.tPlayer,d.tDeltaTime)

	;dummy fix for missing jump
	If p\ForceJumpTimer>0 Then
		If Game\ControlLock>0 Or (p\No#>1 and (p\Action=ACTION_HOLD Or p\Action=ACTION_HOLD2)) Then
			p\ForceJumpTimer=0
		Else
			If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_CARFALL)) Then
				Player_ActuallyJump(p,true,true,true)
			Else
				p\ForceJumpTimer=0
			EndIf
		EndIf
	EndIf

	;fix after homing
	If p\ForceAfterHomDirectionApplicable Then p\Animation\Direction#=p\ForceAfterHomDirection# : p\ForceAfterHomDirectionApplicable=False

	;fall down if slow on slope
	If abs(p\Rotation#)>80 and p\SpeedLength#<0.5 and (p\Flags\CanClimb=False) and (Not(p\Action=ACTION_GRIND Or p\Action=ACTION_CHARGE)) Then Player_ConvertGroundToAir(p) : p\Motion\Ground=False

	;deal translators touched
	If Not(p\TranslatorsTouchedTimer>0) Then p\TranslatorsTouched=0

	;ground tension
	If (p\Motion\Ground And (Not(p\Action=ACTION_GRIND Or p\Action=ACTION_DEBUG))) and (abs(p\Rotation#)<15 Or p\Flags\CanClimb) Then
		MoveEntity(p\Objects\Entity, 0, p\Physics\COMMON_GROUNDTENSION#, 0)
	EndIf

	;underwater spinning
	If p\UnderwaterFeet=1 Then
		If p\Action=ACTION_CHARGE or p\Action=Action_ROLL Then
			If p\No#=1 and (Not(ChannelPlaying(p\Channel_WaterRunning))) Then p\Channel_WaterRunning=EmitSmartSound(Sound_WaterBoosting,p\Objects\Entity)
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		ElseIf p\Action=ACTION_DRIFT
			If p\No#=1 and (Not(ChannelPlaying(p\Channel_WaterRunning))) Then p\Channel_WaterRunning=EmitSmartSound(Sound_WaterDrifting,p\Objects\Entity)
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		Else
			StopChannel(p\Channel_WaterRunning)
		EndIf
	Else
		StopChannel(p\Channel_WaterRunning)
	EndIf

	;leaning when up going
	Select p\Action
		Case ACTION_UP:
			If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+1*d\Delta
			If p\Physics\UP_ANGLE#<p\Physics\UP_ANGLE_TARGET# Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+3*d\Delta
			If p\Physics\UP_ANGLE#>p\Physics\UP_ANGLE_TARGET# Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-3*d\Delta
		Case ACTION_SKYDIVE:
			If p\Physics\UP_ANGLE#<90 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+3*d\Delta
		Case ACTION_FLOAT:
			p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-11*d\Delta
			If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=360
		Case ACTION_GLIDER:
			If Input\Hold\ActionJump Then
				If p\Physics\UP_ANGLE#>60-30 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-3*d\Delta
			ElseIf Input\Hold\ActionRoll Then
				If p\Physics\UP_ANGLE#<60+30 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+3*d\Delta
			Else
				If p\Physics\UP_ANGLE#<60 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+3*d\Delta
				If p\Physics\UP_ANGLE#>60 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-3*d\Delta
			EndIf
		Case ACTION_HOOKSHOT:
			If p\Motion\Ground=False Then
				If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+1*d\Delta
				If p\Physics\UP_ANGLE#<p\Physics\UP_ANGLE_TARGET# Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+3*d\Delta
				If p\Physics\UP_ANGLE#>p\Physics\UP_ANGLE_TARGET# Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-3*d\Delta
			EndIf
		Case ACTION_CARFALL:
			If Game\Vehicle=4 Or Game\Vehicle=9 Then
				If p\Physics\UP_ANGLE#>-20 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-4*d\Delta
			Else
				If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+6*d\Delta
				If p\Physics\UP_ANGLE#>0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-6*d\Delta
			EndIf
		Case ACTION_CAR:
			If Game\Vehicle=4 Or Game\Vehicle=9 Then
				If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+6*d\Delta
				If p\Physics\UP_ANGLE#>0 Then p\Physics\UP_ANGLE#=0
			Else
				If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+6*d\Delta
				If p\Physics\UP_ANGLE#>0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-6*d\Delta
			EndIf
		Case ACTION_TORNADO:
			If (Input\Hold\ActionJump Or p\SpecialSpinTimer>0) and Game\Victory=0 Then
				If p\Physics\UP_ANGLE#>-40 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-2.5*d\Delta
			ElseIf (Input\Hold\ActionRoll) and Game\Victory=0 Then
				If p\Physics\UP_ANGLE#<40 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+2.5*d\Delta
			Else
				If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+4*d\Delta
				If p\Physics\UP_ANGLE#>0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-4*d\Delta
			EndIf
		Default:
			If p\Action=ACTION_BOARDJUMP Or p\Physics\UP_ANGLE#<>0 Then
				If p\Physics\UP_ANGLE#>80 Then p\Physics\UP_ANGLE#=80
				If p\Physics\UP_ANGLE#<-80 Then p\Physics\UP_ANGLE#=-80
				If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+1*d\Delta
				If p\Physics\UP_ANGLE#>0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-2*d\Delta
			EndIf
	End Select

	;leaning when drifting
	If (p\Action=ACTION_DRIFT Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_CARDRIFT) Then
		If p\Physics\DRIFT_ANGLE#<p\Physics\DRIFT_ANGLE_TARGET# Then p\Physics\DRIFT_ANGLE#=p\Physics\DRIFT_ANGLE#+7*d\Delta
		If p\Physics\DRIFT_ANGLE#>p\Physics\DRIFT_ANGLE_TARGET# Then p\Physics\DRIFT_ANGLE#=p\Physics\DRIFT_ANGLE#-7*d\Delta
	Else
		If p\Physics\DRIFT_ANGLE#<0 Then p\Physics\DRIFT_ANGLE#=p\Physics\DRIFT_ANGLE#+7*d\Delta
		If p\Physics\DRIFT_ANGLE#>0 Then p\Physics\DRIFT_ANGLE#=p\Physics\DRIFT_ANGLE#-7*d\Delta
	EndIf

	;deal drift sound
	If p\No#=1 and (p\Action=ACTION_DRIFT Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_CARDRIFT) Then
		If Not(ChannelPlaying(p\Channel_Drift)) Then p\Channel_Drift=EmitSmartSound(Sound_Drift,p\Objects\Entity)
	Else
		StopChannel(p\Channel_Drift)
		p\DriftDirection=0
	EndIf

	;if run lock is active
	If Game\RunLock>0 Then Player_SetSpeed(p,p\RunLockSpeed#,true)

	;after homing, direction too much change fix
	If p\Flags\HomingWasLockedTimer>0 and ((Not(Game\CamLock>0)) Or Game\MachLock>0) Then
		If Player_IsPlayable(p) and (Input\Hold\Up Or Game\MachLock>0) Then p\Animation\Direction#=EntityYaw(cam\Entity)-180
	EndIf

	;let shine
	If p\Action=ACTION_FREEZE Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowIce,0,7
	ElseIf (p\Character=CHAR_SIL) and (p\Action=ACTION_LEVITATE Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST Or p\Action=ACTION_THROW Or p\Psychokinesis=1) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlow,0,7
	ElseIf (p\Character=CHAR_MET Or p\Character=CHAR_TDL Or p\Character=CHAR_MT3) And (p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST Or p\Action=ACTION_THROW Or p\Action=ACTION_LEVITATE) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowMetal,0,7
	ElseIf (p\Character=CHAR_MPH) and (p\Action=ACTION_LEVITATE Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowDark,0,7
	ElseIf (p\Character=CHAR_INF) and (p\Action=ACTION_LEVITATE Or p\Action=ACTION_THROW Or p\Action=ACTION_JUMPDASH Or p\Action=ACTION_HOMING Or p\Action=ACTION_PSYCHO) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowRuby,0,7
	Else
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowEmpty,0,7
	EndIf

	;ink
	If p\Inked>0 Then
		Select p\Inked
			Case 1: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_INK, p\Objects\Entity)
			Case 2: ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_POISONFOG, p\Objects\Entity)
					Object_Enemy_SpecialBehaviour_RingDrain(p)
		End Select
		If (Not(p\InkFloorTimer>0)) Then
			EntityColor(p\Objects\Mesh,255,255,255)
			p\Inked=0
		EndIf
	EndIf

	;deal trick voice
	If p\TrickTimer>0 and p\TrickTimer<0.5*secs# Then Player_PlayGoodVoice(p) : p\TrickTimer=0

	;lean while tricking
	If p\Physics\TRICK_ANGLE#>0 Then p\Physics\TRICK_ANGLE#=p\Physics\TRICK_ANGLE#-15*d\Delta

	;deal stomp sound
	If (Not(p\Action=ACTION_STOMP)) Then StopChannel(p\Channel_Stomp)

	;shield management
	If p\No#=1 Then Player_ManageShields()

	;shut up shoes and invinc if not shoes or invinc
	If p\No#=1 And Game\SuperForm=0 Then
		If Game\SpeedShoes=0 and ChannelPlaying(Game\Channel_SpeedShoes) Then StopChannel(Game\Channel_SpeedShoes)
		If Game\Invinc=0 and ChannelPlaying(Game\Channel_Invincible) Then StopChannel(Game\Channel_Invincible)
	EndIf

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
			If p\Motion\Ground and ChannelPlaying(p\Channel_Grind)=False Then p\Channel_Grind=EmitSmartSound(Sound_Pinball,p\Objects\Entity)
		Else
			StopChannel(p\Channel_Grind)
		EndIf
	EndIf
	If Game\Vehicle=8 Then
		If p\BoardWaterTimer>0 Then
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		EndIf
	EndIf

	;deal spin sound
	If Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP) Then StopChannel(p\Channel_Spin)

	;be hurt!
	If (Not(p\Action=ACTION_DIE)) and p\HurtTimer>0 Then
		If (Not(p\HurtDisappearTimer>0)) Then
			p\HurtDisappearTimer=0.4*secs#
		ElseIf p\HurtDisappearTimer>0.2*secs# Then
			HideEntity(p\Objects\Mesh)
		ElseIf p\HurtDisappearTimer>0*secs# Then
			ShowEntity(p\Objects\Mesh)
		EndIf
	Else
		ShowEntity(p\Objects\Mesh)
	EndIf

	;leaning
	If Player_IsPlayable(p) and (Input\Hold\Up Or Input\Hold\Down) and (Input\Hold\Left Or Input\Hold\Right) and (Not(Game\ControlLock>0)) Then
		If Input\Hold\Left Then
			If p\Physics\LEAN_ANGLE#<p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#+p\Physics\LEAN_ANGLE_SPEED#*d\Delta
			If p\Physics\LEAN_ANGLE#>p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#-p\Physics\LEAN_ANGLE_SPEED#*d\Delta
		EndIf
		If Input\Hold\Right Then
			If p\Physics\LEAN_ANGLE#<-p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#+p\Physics\LEAN_ANGLE_SPEED#*d\Delta
			If p\Physics\LEAN_ANGLE#>-p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#-p\Physics\LEAN_ANGLE_SPEED#*d\Delta
		EndIf
	Else
		If p\Physics\LEAN_ANGLE#<0 Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#+0.7*d\Delta
		If p\Physics\LEAN_ANGLE#>0 Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#-0.7*d\Delta
		If p\Physics\LEAN_ANGLE#<0.5 and p\Physics\LEAN_ANGLE#>-0.5 Then p\Physics\LEAN_ANGLE#=0
	EndIf

	;be invincible!
	If Game\Invinc=1 And Game\SuperForm=0 and Player_IsPlayable(p) Then ParticleTemplate_Call(p\InvisiParticle, PARTICLE_PLAYER_INVINCIBILITY, p\Objects\Entity, p\ScaleFactor#)

	;be invisible!
	If p\Invisibility=1 Then
		ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_INVISIBILITY, p\Objects\Entity, p\ScaleFactor#)

		;deal invisibility
		If (Not(p\InvisibilityTimer>0)) Or (p\Character=CHAR_CHO And (Not(p\Action=ACTION_PUDDLE))) Then p\Invisibility=0 : p\InvisibilityRestrictTimer=4*secs#
	EndIf
	
	; deal light meshes
	Player_DealLightMeshes(p, d)

	;deal fireparticle when used
	If ChannelPlaying(p\Channel_Fire) Then ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_FIRE, p\Objects\Entity, 1)

	;deal waterparticle when used
	If ChannelPlaying(p\Channel_Water) Then ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_WATER, p\Objects\Entity)
	
	;deal instashield mesh
	If p\Action=ACTION_INSTASHIELD Then
		ShowEntity(p\Objects\Instashield)
		PositionEntity p\Objects\Instashield, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
        	RotateEntity (p\Objects\Instashield, 0, p\Animation\Direction#-180, 0)
	Else
		HideEntity(p\Objects\Instashield)
	EndIf
	;psychokinesis sound
	Select p\Psychokinesis
		Case 1:
			If Not(ChannelPlaying(p\Channel_Psychokinesis)) Then p\Channel_Psychokinesis=EmitSmartSound(Sound_Psychokinesis,p\Objects\Entity)
		Case 0:
			If ChannelPlaying(p\Channel_Psychokinesis) Then StopChannel(p\Channel_Psychokinesis)
	End Select

	;drowning
	If p\Underwater=1 and Game\Interface\DebugPlacerOn=0 and (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
		If (Not Player_IsARobot(p)) Then
			Select p\DrownState
				Case 3: ParticleTemplate_Call(p\BubbleBreatheParticle, PARTICLE_PLAYER_BUBBLEBREATHE, p\Objects\Head, 0, 0, 0, 0, 0, Rand(1,5)/8.0)
				Default: ParticleTemplate_Call(p\BubbleBreatheParticle, PARTICLE_PLAYER_BUBBLEBREATHE, p\Objects\Head, 0, 0, 0, 0, 0, Rand(1,5)/4.0)
			End Select
		EndIf
	EndIf
	If p\No#=1 Then
		If Game\Victory=0 Then
		Select p\DrownState
			Case 0:
				If p\Underwater=1 and (Not(Game\Shield=OBJTYPE_BSHIELD)) and Menu\ChaoGarden=0 and (Not(Game\Stage\Properties\WaterType=6 Or Game\Stage\Properties\WaterType=7)) Then
					Select InterfaceChar(p\RealCharacter)
						Case CHAR_SON: p\DrownValue=30
						Case CHAR_TAI: p\DrownValue=45
						Case CHAR_KNU: p\DrownValue=60
						Case CHAR_AMY: p\DrownValue=30
						Case CHAR_SHA: p\DrownValue=30
						Case CHAR_ROU: p\DrownValue=60
						Case CHAR_CRE: p\DrownValue=25
						Case CHAR_BLA: p\DrownValue=40
						Case CHAR_SIL: p\DrownValue=35
						Case CHAR_OME: p\DrownValue=-1
						Case CHAR_ESP: p\DrownValue=60
						Case CHAR_CHA: p\DrownValue=20
						Case CHAR_VEC: p\DrownValue=-1
						Case CHAR_BIG: p\DrownValue=65
						Case CHAR_MAR: p\DrownValue=70
						Case CHAR_MIG: p\DrownValue=35
						Case CHAR_RAY: p\DrownValue=15
						Case CHAR_CHO: p\DrownValue=-1
						Case CHAR_TIK: p\DrownValue=65
						Case CHAR_NAC: p\DrownValue=25
						Case CHAR_BEA: p\DrownValue=-1
						Case CHAR_BAR: p\DrownValue=70
						Case CHAR_JET: p\DrownValue=25
						Case CHAR_WAV: p\DrownValue=25
						Case CHAR_STO: p\DrownValue=20
						Case CHAR_TIA: p\DrownValue=30
						Case CHAR_HON: p\DrownValue=30
						Case CHAR_SHD: p\DrownValue=40
						Case CHAR_MPH: p\DrownValue=-1
						Case CHAR_HBO: p\DrownValue=-1
						Case CHAR_GAM: p\DrownValue=-1
						Case CHAR_EME: p\DrownValue=-1
						Case CHAR_MET: p\DrownValue=-1
						Case CHAR_TDL: p\DrownValue=-1
						Case CHAR_MKN: p\DrownValue=-1
						Case CHAR_EGG: p\DrownValue=20
						Case CHAR_BET: p\DrownValue=-1
						Case CHAR_MT3: p\DrownValue=-1
						Case CHAR_GME: p\DrownValue=-1
						Case CHAR_PRS: p\DrownValue=25
						Case CHAR_COM: p\DrownValue=45
						Case CHAR_CHW: p\DrownValue=25
						Case CHAR_EGR: p\DrownValue=-1
						Case CHAR_INF: p\DrownValue=40
						Case CHAR_TRI: p\DrownValue=40
						Default:
							If IsCharMod(InterfaceChar(p\RealCharacter)) Then
								p\DrownValue=(MODCHARS_DROWN(p\RealCharacter-CHAR_MOD1+1))
							Else
								p\DrownValue=30
							EndIf
					End Select
					p\DrownState=1
				EndIf
			Case 1:
				If (Not(p\DrownValue=-1)) Then
					If (Not(p\DrownTimer>0)) Then
						p\DrownValue=p\DrownValue-1
						p\DrownTimer=1*secs#
						p\BreathCountTimer=0.01*secs#
					EndIf
					If p\DrownValue<1 Then
						Game\Channel_Drown=PlaySmartSound(Sound_Drown)
						p\DrownState=3
					ElseIf p\BreathCountTimer>0 Then
						Select p\DrownValue
							Case 4,8,12,16,20,24,28,32,36,40,44,48,52,58,62,66: PlaySmartSound(Sound_BreathCount)
						End Select
					EndIf
				EndIf
			Case 3:
				If (ChannelPlaying(Game\Channel_Drown)=False) Then
					Player_Die(p)
					If Not Player_IsARobot(p) Then
						EmitSmartSound(Sound_Drowned,p\Objects\Entity)
						For i=3 to Rand(3,5)
							p\BubbleBreatheParticle\ParticleTimer=0
							ParticleTemplate_Call(p\BubbleBreatheParticle, PARTICLE_OBJECT_BUBBLES, p\Objects\Head)
						Next
					EndIf
					p\DrownState=5
				EndIf
		End Select
		EndIf
		If Game\Victory<>0 Or p\Underwater=0 Or (Game\Shield=OBJTYPE_BSHIELD) Or Game\Interface\DebugPlacerOn=1 Or p\DrownState=0 Then
			p\DrownState=0
			p\DrownTimer=0
			StopChannel(Game\Channel_Drown)
		EndIf
	EndIf

	;fix die
	If (Not(p\Action=ACTION_DIE)) And p\DieTimer>0 Then p\Action=ACTION_DIE

	;force victory
	If Player_IsPlayable(p) and Menu\ChaoGarden=0 Then
		If Game\Victory<>0 Then
			If (Not(p\Action=ACTION_VICTORY Or p\Action=ACTION_VICTORYHOLD Or (pp(1)\Action=ACTION_VICTORYHOLD and p\Action=ACTION_HOLD2) Or p\Action=ACTION_DIE)) and Game\Vehicle=0 Then
				p\Action=ACTION_VICTORY
			EndIf
			Player_SetSpeed(p,0)
			If p\No#=1 Then Player_VictoryCam()
		EndIf
	EndIf
	;mission management
	If Game\Victory=0 and p\No#=1 Then
		Select Menu\Mission
			Case MISSION_ENEMY#,MISSION_CARNIVAL#:
				If Game\Gameplay\Enemies>=Game\Gameplay\TotalEnemies Then Player_Goal(p)
			Case MISSION_RING#:
				If Game\Gameplay\Rings>=200 Then Player_Goal(p)
			Case MISSION_HUNT#:
				If Game\Gameplay\RedRings>=3 Then Player_Goal(p)
			Case MISSION_GOLD#:
				If Game\Gameplay\TotalGoldEnemies>0 and Game\Gameplay\GoldEnemies>=Game\Gameplay\TotalGoldEnemies Then Player_Goal(p)
			Case MISSION_BALLOONS#:
				If Game\Gameplay\Balloons>=Game\Gameplay\TotalBalloons Then Player_Goal(p)
			Case MISSION_RIVAL#:
				Select Game\RivalAmount
					Case 1: If ppe(1)\Action=ACTION_RIVALDIE Then Player_Goal(p)
					Case 2: If ppe(1)\Action=ACTION_RIVALDIE and ppe(2)\Action=ACTION_RIVALDIE Then Player_Goal(p)
					Case 3: If ppe(1)\Action=ACTION_RIVALDIE and ppe(2)\Action=ACTION_RIVALDIE and ppe(3)\Action=ACTION_RIVALDIE Then Player_Goal(p)
				End Select
			Case MISSION_BOSS#:
				If Game\BossNotDefeated=0 Then Player_Goal(p)
			Case MISSION_DECLINE#:
				If Game\DeclineTime>0 Then
					Game\DeclineTime=Game\DeclineTime-timervalue#
				Else
					If Menu\ExitedAStage=0 Then Game_Stage_Quit(5)
				EndIf
		End Select
		If Menu\MissionTime=1 Then
			If (Game\LimitTime-Game\Gameplay\Time)<0 and Menu\ExitedAStage=0 Then Game_Stage_Quit(5)
		EndIf
		If Menu\MissionMach=1 Or Game\MachLockTriggered=1 Then
			Game\MachLock=1.5*secs#
		EndIf
		If Menu\MissionPerfect=1 Or Menu\Stage<0 Then
			If p\Action=ACTION_DIE and Menu\ExitedAStage=0 Then Game_Stage_Quit(1)
		EndIf
	EndIf

	;water splash
	If (Not(p\Watersplash=p\Underwater)) and (p\No#=1 Or p\Underwater=pp(1)\Underwater) and (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
		If Not(Game\Stage\Properties\WaterType=6) Then
			Select p\Underwater
				Case 1: EmitSmartSound(Sound_WaterIn, p\Objects\Entity)
				Case 0: EmitSmartSound(Sound_WaterOut, p\Objects\Entity)
			End Select
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		EndIf
		p\WaterSplash=p\Underwater
	EndIf

	;aiming and shooting
	Select p\Aiming
		Case 1:
			ShowEntity(p\Objects\Scanner)
			RotateEntity(p\Objects\Scanner,0,p\Animation\Direction#-180,0,1)
			If p\Action=ACTION_TORNADO Then
				PositionEntity(p\Objects\Scanner,p\Objects\Position\x#,p\Objects\Position\y#+3.5,p\Objects\Position\z#,1)
				If Menu\Members=1 Then
					MoveEntity(p\Objects\Scanner,0,2*p\ScaleFactor#,3.7+2*p\ScaleFactor#)
				Else
					MoveEntity(p\Objects\Scanner,0,2*pp(2)\ScaleFactor#,3.7+2*pp(2)\ScaleFactor#)
				EndIf
			Else
				PositionEntity(p\Objects\Scanner,EntityX(p\Objects\Extra,1),EntityY(p\Objects\Extra,1),EntityZ(p\Objects\Extra,1),1)
			EndIf
			If Not(ChannelPlaying(p\Channel_Aim)) Then p\Channel_Aim=EmitSmartSound(Sound_Aim,p\Objects\Entity)
			If Not(Input\Hold\ActionSkill1) Then
				If p\AimedTargets>0 Then
					p\Aiming=2
				Else
					p\AimedTargets=1 : p\Aiming=3
				EndIf
			EndIf
		Case 2,3:
			HideEntity(p\Objects\Scanner)
			If (Not(p\Action=ACTION_SHOOT Or p\Action=ACTION_SHOOTHOVER Or p\TornadoShoot=1)) and (Not(p\ShootCooldownTimer>0)) and (p\Action=ACTION_COMMON Or p\Action=ACTION_FULLFALL Or p\Action=ACTION_JUMPFALL Or p\Action=ACTION_FALL Or p\Action=ACTION_HOP Or p\Action=ACTION_JUMP Or p\Action=ACTION_HOVER Or p\Action=ACTION_TORNADO) Then
				If p\AimedTargets>0 Then
					Player_Action_Shoot_Initiate(p,2)
					p\AimedTargets=p\AimedTargets-1
				Else
					p\Aiming=0
				EndIf
			EndIf
		Case 0:
			HideEntity(p\Objects\Scanner)
			If ChannelPlaying(p\Channel_Aim) Then StopChannel(p\Channel_Aim)
			p\AimedTargets=0
	End Select

	;deal enemy combo
	If (Not(p\EnemyComboTimer>0)) Then p\EnemyComboCounter=0

	;deal sonic boom
	If p\Flags\SonicBoom=True and ( (Not(Input\Hold\ActionSkill3)) Or (Not(p\SonicBoomTimer>0)) ) Then p\Flags\SonicBoom=False : p\SonicBoomOverheatTimer=10*secs#

	;deal obj carry carrying nothing
	If (Not(p\ObjPickUpTimer>0)) and p\ObjPickUp=1 Then p\ObjPickUp=0

	;deal how many pet attack
	If Not(p\CheeseRestrictTimer>0) Then p\CheeseAttackedCount=0

	;deal grind
	If Not(p\Action=ACTION_GRIND Or p\HasVehicle>0) Then p\GrindTurn=0

	;deal vehicle
	If p\No#=1 Then
		If Game\WholeVehicle>0 Then
			If Game\Victory=0 and (Not(p\Action=ACTION_DIE Or p\Action=ACTION_DEBUG Or p\Action=ACTION_HOLD Or p\Action=ACTION_GRIND Or p\IsHoldingTimer>0 Or p\JustDeformedCharacterTimer>0)) Then
				If (Not(Game\Vehicle=Game\WholeVehicle Or (Game\WholeVehicle=6 and Game\Vehicle=7))) Then Game\Vehicle=Game\WholeVehicle
			EndIf
		EndIf
		For ppp.tPlayer = Each tPlayer
			If (Not(ppp\HasVehicle=Game\Vehicle)) and ppp\No#>0 and (ppp\No#=1 Or (Not(Game\Vehicle=6 Or Game\Vehicle=7))) Then
				If ppp\HasVehicle>0 Then
					FreeEntity ppp\Objects\Vehicle : ppp\Action=ACTION_FALL
				EndIf
				Select Game\Vehicle
					Case 1: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Board)), Game\Stage\Root)
					Case 2: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Glider)), Game\Stage\Root)
					Case 3: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_CopRacer1Car+p\VehicleColor-1)), Game\Stage\Root)
							ppp\Objects\VehicleJet1 = FindChild(ppp\Objects\Vehicle, "jet")
					Case 4: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Bike)), Game\Stage\Root)
							ppp\Objects\VehicleJet1 = FindChild(ppp\Objects\Vehicle, "jetR")
							ppp\Objects\VehicleJet2 = FindChild(ppp\Objects\Vehicle, "jetL")
							Animate(ppp\Objects\Vehicle,1,0.2,1,10)
					Case 5: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Bobsleigh)), Game\Stage\Root)
					Case 6: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Tornado1)), Game\Stage\Root)
					Case 7: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Tornado2)), Game\Stage\Root)
							ppp\Objects\VehicleJet2 = FindChild(ppp\Objects\Vehicle, "jetU")
							ppp\Objects\VehicleJet1 = FindChild(ppp\Objects\Vehicle, "jetD")
					Case 8: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Cyclone)), Game\Stage\Root)
							Animate(ppp\Objects\Vehicle,1,0.3,1,10)
					Case 9: ppp\Objects\Vehicle = CopyEntity(MESHES(SmartEntity(Mesh_Kart)), Game\Stage\Root)
							ppp\Objects\VehicleJet1 = FindChild(ppp\Objects\Vehicle, "jetR")
							ppp\Objects\VehicleJet2 = FindChild(ppp\Objects\Vehicle, "jetL")
							Animate(ppp\Objects\Vehicle,1,0.2,1,10)
				End Select
				Select Game\Vehicle
					Case 3,5,8:
						ScaleEntity ppp\Objects\Vehicle, 1.1+ppp\ScaleFactor#*0.65, 1.1+ppp\ScaleFactor#*0.65, 1.1+ppp\ScaleFactor#*0.65, 1
					Case 4,9:
						ScaleEntity ppp\Objects\Vehicle, 0.95+ppp\ScaleFactor#*0.65, 0.95+ppp\ScaleFactor#*0.65, 0.95+ppp\ScaleFactor#*0.65, 1
					Case 6,7:
						ppp\Objects\VehicleShoot = FindChild(ppp\Objects\Vehicle, "propeller")
						ppp\Objects\VehicleShootController = FindChild(ppp\Objects\Vehicle, "top")
						If Menu\Members=1 Then
							ScaleEntity ppp\Objects\Vehicle, 1.08+ppp\ScaleFactor#*0.65, 1.08+ppp\ScaleFactor#*0.65, 1.08+ppp\ScaleFactor#*0.65, 1
						Else
							ScaleEntity ppp\Objects\Vehicle, 1.08+pp(2)\ScaleFactor#*0.65, 1.08+pp(2)\ScaleFactor#*0.65, 1.08+pp(2)\ScaleFactor#*0.65, 1
						EndIf
						If Menu\Members=1 Then
							Animate(ppp\Objects\Vehicle,1,0.55,2,10)
						Else
							Animate(ppp\Objects\Vehicle,1,0.55,1,10)
						EndIf
				End Select
				ppp\HasVehicle=Game\Vehicle
				Select Game\Vehicle
					Case 1,5,8: ppp\Action=ACTION_BOARDJUMP
					Case 2: ppp\Action=ACTION_GLIDER
					Case 3,4,9: ppp\Action=ACTION_CARFALL
					Case 6,7: ppp\Action=ACTION_TORNADO
				End Select
			EndIf
		Next

		If p\HasVehicle>0 Then
			p\Invisibility=0
			If Game\WholeVehicle>0 Then
				Select Game\WholeVehicle
					Case 1,5,8:
						If Not(p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_BOARDFALL Or p\Action=ACTION_BOARDTRICK) Then
							p\Action=ACTION_BOARDJUMP
						EndIf
					Case 2:
						If Not(p\Action=ACTION_GLIDER) Then
							p\Action=ACTION_GLIDER
						EndIf
					Case 3,4,9:
						If Not(p\Action=ACTION_CAR Or p\Action=ACTION_CARDRIFT Or p\Action=ACTION_CARFALL) Then
							p\Action=ACTION_CARFALL
						EndIf
					Case 6,7:
						If Not(p\Action=ACTION_TORNADO) Then
							p\Action=ACTION_TORNADO
						EndIf
				End Select
			Else
				If Input\Pressed\ActionAct Then
					If Game\Vehicle>0 Then Game\Vehicle=0 : Input_ResetActionInput()
					Player_JumpSound(p)
					p\ForceJumpTimer=0.05*secs#
				EndIf
			EndIf
		EndIf
	EndIf

	;die on death level
	If Game\Victory=0 And p\Objects\Position\y#<Game\Stage\Properties\DeathLevel And Game\Interface\DebugPlacerOn=0 And (Not(p\Action=ACTION_DIE)) Then Player_Die(p)

	;destination targeting
	If p\GoDestination Then
		Game\ControlLock=0.1*secs#
		Game\RunLock=0
		Game\MachLock=0
		PositionEntity p\Objects\DestinationTarget, p\DestinationX#, p\DestinationY#, p\DestinationZ#, 1
			ex# = p\DestinationX# - p\Objects\Position\x#
			ey# = (p\DestinationY#+3) - p\Objects\Position\y#
			ez# = p\DestinationZ# - p\Objects\Position\z#
			AlignToVector(p\Objects\Entity, ex#, ey#, ez#, 2, .925)
			AlignToVector(p\Objects\Mesh, ex#, ey#, ez#, 2, .925)
			If p\HasVehicle=0 Then
				Select p\Animation\Animation
					Case ANIMATION_FORWARD,ANIMATION_FALL,ANIMATION_FALLFAST:
						TurnEntity p\Objects\Mesh, -90, 0, 0
				End Select
			Else
				TurnEntity p\Objects\Mesh, -90, 0, 0
			EndIf
			If EntityDistance(p\Objects\Entity,p\Objects\DestinationTarget)>p\DestinationSpeed# Then
				MoveEntity(p\Objects\Entity, 0, p\DestinationSpeed#*d\Delta, 0)
			Else
				MoveEntity(p\Objects\Entity, 0, EntityDistance(p\Objects\Entity,p\Objects\DestinationTarget)*d\Delta, 0)
			EndIf
			p\Motion\Speed\x# = 0 : p\Motion\Speed\y# = 0 : p\Motion\Speed\z# = 0
		If EntityDistance(p\Objects\Entity,p\Objects\DestinationTarget)<5 Then p\GoDestination=False

		If abs(p\DestinationSaverPreviousDistance#-EntityDistance(p\Objects\Entity,p\Objects\DestinationTarget)) < 1 Then
			p\DestinationSaverTimer=p\DestinationSaverTimer+timervalue#
			If p\DestinationSaverTimer>2*secs# Then
				p\GoDestination=False
				Game\ControlLock=0 : Game\CamLock=0 : Game\CamLock2=0
			EndIf
		Else
			p\DestinationSaverTimer=0
		EndIf
		p\DestinationSaverPreviousDistance#=EntityDistance(p\Objects\Entity,p\Objects\DestinationTarget)
	Else
		p\DestinationSaverTimer=0
	EndIf

	;spiritual disappear
	If p\SpiritualChange>0 Then
		If p\Action=ACTION_SPIRIT Then
			EntityAlpha(p\Objects\Mesh,0)
		Else
			EntityAlpha(p\Objects\Mesh,1)
		EndIf
		p\SpiritualChange=0
	EndIf

	;bomb monitor
	If p\BombMonitorTimer>0 Then
		For o.tObject = Each tObject
			If o\ThisIsAnEnemy Then
				If EntityDistance(o\Entity,p\Objects\Entity)<250 and o\Enemy\EnemyShallAppear and Object_IsActualEnemy(o\ObjType) and (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) and (Not(o\Enemy\WasJustAttacked>0)) Then
					o\Enemy\WasKilledByBombMonitor=True
					o\AlwaysPresent=True
				EndIf
			EndIf
		Next
		p\BombMonitorTimer=0
	EndIf

	;chao garden stuff
	If Menu\ChaoGarden=1 and Player_IsPlayable(p) Then
		;explode inventory
		If Game\Interface\ShallExplodeInventory and Menu\Stage=999 Then
			Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_RELEASEINVENTORY))
			If Input\Pressed\ActionSkill2 Then
				Game\Interface\ShallExplodeInventory=False
				EmitSmartSound(Sound_Sack, p\Objects\Entity)
				Player_ExplodeInventory(p)
			EndIf
			p\MayNotWhistleTimer=0.5*secs#
			p\MayNotPetTimer=0.5*secs#
		EndIf

		;whistle, pet, cheer
		If Menu\Stage=999 Then
			If (Not(p\MayNotWhistleTimer>0)) and p\MayWhistleTimer>0 Then
				Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_WHISTLE))
				If Input\Pressed\ActionSkill2 Then
					Object_Whistle_Create.tObject(p)
					EmitSmartSound(Sound_Whistle,p\Objects\Entity)
				EndIf
			ElseIf (Not(p\MayNotPetTimer>0)) and p\MayPetTimer>0 Then
				Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_PET))
				If Input\Pressed\ActionSkill2 Then
					Object_Petter_Create.tObject(p)
				EndIf
			EndIf
		Else
			If (Not(p\MayNotCheerTimer>0)) and p\MayCheerTimer>0 Then
				Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_CHEER))
				If Input\Pressed\ActionSkill2 Then
					p\MayNotCheerTimer=(2+Rand(0,2))*secs#
					For cc.tChaoManager=Each tChaoManager
						If cc\Number=1 Then cc\CheeredTimer=2*secs#
					Next
					PlaySmartSound(Sound_Whistle)
				EndIf
			EndIf
		EndIf

		; auto saving
		If (Not(Game\Interface\AutoSaveTimer>0)) and Menu\Stage=999 Then
			SaveGame_AllChaoStuff()
			Game\Interface\AutoSaveTimer=180*secs#
			Game\Interface\AutoSaveShowTimer=0.5*secs#
		Else
			Game\Interface\AutoSaveTimer=Game\Interface\AutoSaveTimer-timervalue#
		EndIf
	EndIf

	;be super!
	If (Game\SuperForm>0 and Player_IsPlayable(p)) Then
		ParticleTemplate_Call(p\InvisiParticle, PARTICLE_PLAYER_SUPER, p\Objects\Entity, p\ScaleFactor#)
		If Game\SuperForm=1 Then
			If Player_SuperAuraShouldFire(p\Character) Then
				If p\No#=1 Then ParticleTemplate_Call(p\SuperAuraParticle, PARTICLE_PLAYER_SUPERAURA, p\Objects\Entity, 1+p\ScaleFactor#, 0, 0, 0, 2, 0.2)
			Else
				If p\No#=1 Then ParticleTemplate_Call(p\SuperAuraParticle, PARTICLE_PLAYER_SUPERAURA, p\Objects\Entity, 1+p\ScaleFactor#, 0, 0, 0, 1, 0.2)
			EndIf
		ElseIf Game\SuperForm=2 Then
			If p\No#=1 Then ParticleTemplate_Call(p\SuperAuraParticle, PARTICLE_PLAYER_HYPERAURA, p\Objects\Entity, 1+p\ScaleFactor#, 0, 0, 0, 1, 0.2)
		EndIf

		Player_CreateRazer.tRazer(p,p\Objects\Mesh,Rand(5,10)/10.0,8)

		If p\No#=1 and Game\Interface\DebugPlacerOn=0 Then
			If (Not(Game\RingDropTimer>0)) Then
				Game\RingDropTimer=1*secs#
				Gameplay_SubstractRings(1)
			EndIf
		EndIf

		If Not(Game\Gameplay\Rings>0) Then
			Game\SuperForm=0
			For ppp.tPlayer = Each tPlayer : DeformCharacter(ppp,True) : Next
		EndIf
	EndIf

	;handle radius
	Select p\RadiusChange
		Case 0:
			If (p\No#>1 and (p\Action=ACTION_HOLD2)) Then
				EntityRadius(p\Objects\Entity, 12)
				p\RadiusChange=1
			ElseIf p\Action=ACTION_TORNADO Then
				If Menu\Members=1 Then
					EntityRadius(p\Objects\Entity, 10+0.8*p\ScaleFactor#)
				Else
					EntityRadius(p\Objects\Entity, 10+0.8*pp(2)\ScaleFactor#)
				EndIf
				p\RadiusChange=2
			EndIf
		Case 1:
			If (Not(p\Action=ACTION_HOLD2)) Then
				Player_SetRadius#(p)
				p\RadiusChange=3
			EndIf
		Case 2:
			If (Not(p\Action=ACTION_TORNADO)) Then
				Player_SetRadius#(p)
				p\RadiusChange=0
			EndIf
		Case 3:
			If p\Motion\Ground Then p\RadiusChange=0
	End Select

	;fix grabbed collision
	If p\WasGrabbed=1 and (Not(p\Action=ACTION_GRABBED)) Then
		p\WasGrabbed=0
		EntityType(cam\Entity, COLLISION_CAMERA)
		EntityType(p\Objects\Entity, COLLISION_PLAYER)
	EndIf

	;special stage stuff
	If Menu\Stage<0 Then
		If Game\Victory=0 and (Not(p\Action=ACTION_DIE Or p\Action=ACTION_DEBUG)) Then
			Game\MachLock=1.5*secs#
			Game\CamLock=1800*secs#
			Game\CamLock2=0
			cam\Lock\Rotation\x#=10 : cam\Lock\Rotation\y#=0 : cam\Lock\Rotation\z#=0
			cam\Lock\Zoom#=31
			cam\Lock\Speed#=10/10.0
			cam\Lock\Immediate=0
			cam\Lock\Pos=0
			cam\Lock\PreviousPos=0
		EndIf
	EndIf

End Function

Function GetClosestGardenPoint()
	gardenpointcloseness#=99999
	For o.tObject=Each tObject
		Select o\ObjType
		Case OBJTYPE_GARDENPOINT:
			gardenpointdistance#=EntityDistance(o\Entity,pp(1)\Objects\Entity)
			If gardenpointdistance#<gardenpointcloseness# Then
				gardenpointcloseness#=gardenpointdistance#
				Game\Stage\Properties\StartX#=o\InitialPosition\x#
				Game\Stage\Properties\StartY#=o\InitialPosition\y#+5
				Game\Stage\Properties\StartZ#=o\InitialPosition\z#
				Game\Stage\Properties\StartDirection#=o\InitialRotation\y#
			EndIf
		End Select
	Next
End Function

Function Player_IsPowerChar(p.tPlayer)
	Select p\Character
		Case CHAR_KNU,CHAR_OME,CHAR_BIG,CHAR_VEC,CHAR_MAR,CHAR_MIG,CHAR_MKN,CHAR_CHO,CHAR_BAR,CHAR_STO,CHAR_TIA,CHAR_MPH,CHAR_HBO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_COM:
			Return True
		Default:
			Return False
	End Select
End Function