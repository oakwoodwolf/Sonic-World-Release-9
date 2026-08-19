Function Player_ExtraHandle(p.tPlayer,d.tDeltaTime)
	
	Player_DebugStats(p)
	
	;dummy fix for missing jump
	If p\ForceJumpTimer>0 Then
		If Game\ControlLock>0 Or (p\No#>1 And (p\Action=ACTION_HOLD Or p\Action=ACTION_HOLD2)) Then
			p\ForceJumpTimer=0
		Else
			If (Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_CARFALL)) Then
				Player_ActuallyJump(p,True,True,True)
			Else
				p\ForceJumpTimer=0
			EndIf
		EndIf
	EndIf
	
	If Not(p\Action=ACTION_GRIND) Then p\Physics\REAL_GRIND_SPEED#=p\SpeedLength#
	
	If p\Physics\REAL_GRIND_SPEED#<1.5 Then p\Physics\REAL_GRIND_SPEED#=1.5
	
	If p\ClimbJumpTimer>0 Then 
		Player_SetSpeed(p,2)
	EndIf 
	
	If (Not(p\PunchTimer>0)) Then p\PunchNumber=0
	
	If p\Character=CHAR_KNU Then
		If p\Action=ACTION_CLIMB Then
			If Input\Hold\ActionRoll Then
				p\Walldash=0
				p\WalldashTimer=p\WalldashTimer+timervalue#*3
				If p\WalldashTimer>5*secs# Then p\WalldashTimer=3*secs#
				If ChannelPlaying(p\Channel_Charge)=False Then p\Channel_Charge=EmitSmartSound(Sound_SpinDashCharge,p\Objects\Entity)
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_SMOKE, p\Objects\Mesh, 1, 0.075, p\SpeedLength#+1.25, 0, 1, 0.0375)
			Else
				
				If p\Walldash=0 Then 
					Player_SetSpeed(p,p\WalldashTimer/secs#,True) : 
					EmitSmartSound(Sound_SpinDashRelease,p\Objects\Entity)
					p\WalldashTimer=0
					p\Walldash=1
				EndIf
				StopChannel(p\Channel_Charge)
			EndIf
		EndIf
	EndIf
	
	If p\Character=CHAR_BLA Then 
		
		If p\Action=ACTION_SWIPE Then
			If ChannelPlaying(p\Channel_Fire)=False Then p\Channel_Fire=EmitSmartSound(Sound_Fire,p\Objects\Entity)
		Else
			StopChannel(p\Channel_Fire)
		EndIf
		
		If p\TrailBlazer=1 Then
			If p\BoostGaugeTimer>0 Then p\BoostGaugeTimer=p\BoostGaugeTimer-timervalue#
			If (Not(p\BoostGaugeTimer>0)) Then Gameplay_SubstractGaugeEnergy(2) : p\BoostGaugeTimer=0.15*secs#
			Player_SetSpeed(p,5.5)
			If p\Motion\Ground=False Or Game\Gameplay\GaugeEnergy<=0 Then p\TrailBlazer=0	: EmitSmartSound(Sound_BoostFinish,p\Objects\Entity)
		Else
			StopChannel(p\Channel_TrailBlazer)
		EndIf
		
	EndIf
	
	If ((Not(Game\F9Timer>0)) And Menu\StabilityTest=1 ) And Menu\Stage>0 And Game\Victory=0 Then Game\Cheater=1 : Player_Goal(p)
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_SHIELD)=1 Then
		If Game\UsedSkill=0 Then p\ShowShieldTip=1 Else p\ShowShieldTip=0
		If Input\Pressed\ActionSkillX And Game\UsedSkill=0 Then
			Game\Shield=OBJTYPE_NSHIELD
			MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1)) 
			PlaySmartSound(Sound_Shield)
			Game\UsedSkill=1
		EndIf
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_FSHIELD)=1 Then
		If Game\UsedSkill=0 Then p\ShowShieldTip=1 Else p\ShowShieldTip=0
		If Input\Pressed\ActionSkillX And Game\UsedSkill=0 Then
			Game\Shield=OBJTYPE_FSHIELD
			MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1)) 
			PlaySmartSound(Sound_ShieldFire)
			Game\UsedSkill=1
		EndIf
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_BSHIELD)=1 Then
		If Game\UsedSkill=0 Then p\ShowShieldTip=1 Else p\ShowShieldTip=0
		If Input\Pressed\ActionSkillX And Game\UsedSkill=0 Then
			Game\Shield=OBJTYPE_BSHIELD
			MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1)) 
			PlaySmartSound(Sound_ShieldBubble)
			Game\UsedSkill=1
		EndIf
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_TSHIELD)=1 Then
		If Game\UsedSkill=0 Then p\ShowShieldTip=1 Else p\ShowShieldTip=0
		If Input\Pressed\ActionSkillX And Game\UsedSkill=0 Then
			Game\Shield=OBJTYPE_TSHIELD
			MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1)) 
			PlaySmartSound(Sound_ShieldThunder)
			Game\UsedSkill=1
		EndIf
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_ESHIELD)=1 Then
		If Game\UsedSkill=0 Then p\ShowShieldTip=1 Else p\ShowShieldTip=0
		If Input\Pressed\ActionSkillX And Game\UsedSkill=0 Then
			Game\Shield=OBJTYPE_ESHIELD
			MonitorIcon_Draw(Game\Shield-(OBJTYPE_RINGS-1)) 
			PlaySmartSound(Sound_ShieldEarth)
			Game\UsedSkill=1
		EndIf
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_BOOST) Then
		Select p\Flags\Boosting
			Case False
				
				If Input\Pressed\ActionSkillX And Game\Gameplay\GaugeEnergy>0 Then
					p\BoostingTimer=0
					PostEffect_Create_MotionBlur(0.85)
					EmitSmartSound(Sound_BoostStart,p\Objects\Entity) : p\Channel_BoostCharge=EmitSmartSound(Sound_BoostCharge,p\Objects\Entity)
					Player_PlayAttackVoice(p)
					Gameplay_SubstractGaugeEnergy(5)
					p\BoostGaugeTimer=0.05*secs#
					Select p\Action
						Case ACTION_JUMP,ACTION_FALL,ACTION_FULLFALL,ACTION_JUMPFALL,ACTION_UP,ACTION_HOP,ACTION_FWD
							p\Action=ACTION_FWD
							Player_SetSpeedY(p,0.5,True)
							Player_SetSpeed(p,5,True)
						Case ACTION_COMMON
							Player_SetSpeed(p,5,True)
					End Select
					p\Flags\Boosting=True
				EndIf
				If cam\Lock\NoBossCam=1 Then
					If cam\FieldOfView#>50 Then cam\FieldOfView#=cam\FieldOfView#-0.4*d\Delta
				EndIf
			Case True
				
				p\BoostingTimer=p\BoostingTimer+timervalue*2
				If cam\Lock\NoBossCam=1 Then
					If cam\FieldOfView#<60 Then cam\FieldOfView#=cam\FieldOfView#+((0.15)*p\BoostingTimer/secs#)*d\Delta
				EndIf
				If p\BoostGaugeTimer>0 Then p\BoostGaugeTimer=p\BoostGaugeTimer-timervalue#
				If (Not(p\BoostGaugeTimer>0)) Then Gameplay_SubstractGaugeEnergy(1) : p\BoostGaugeTimer=0.15*secs#
				If p\Motion\Ground=True Then Player_SetSpeed(p,7,True)
				If ChannelPlaying(p\Channel_BoostWind)=False Then p\Channel_BoostWind=PlaySmartSound(Sound_BoostWind)
				If ((Not(Input\Hold\ActionSkillX)) And (Not(Game\ControlLock>3*secs#))) Or Game\Gameplay\GaugeEnergy=0 Then
					StopChannel(p\Channel_BoostCharge)
					StopChannel(p\Channel_BoostWind)
					p\Flags\Boosting=False
				EndIf
		End Select
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_DROPDASH) Then
		If p\Action=ACTION_JUMP Or p\Action=ACTION_JUMPFALL Then
			If Input\Pressed\ActionSkillX Then p\Flags\DropDashing=True : EmitSmartSound(Sound_DropDash,p\Objects\Entity)
			If Input\Hold\ActionSkillX=False Then p\Flags\DropDashing=False
		Else
			p\Flags\DropDashing=False
		EndIf
	EndIf
	
	If Not(p\Action=ACTION_VICTORY) Then p\Animation\VictoryStage=0
	
	
	For c.tCamera = Each tCamera
		If p\Action=ACTION_COMMON And p\SpeedLength#=0 Then c\MouseCameraTimer=0.1*secs# : 
	Next
	
	PositionEntity(CAMPOS,cam\Position\x#,cam\Position\y#,cam\Position\z#)
	
	;fix after homing
	If p\ForceAfterHomDirectionApplicable Then p\Animation\Direction#=p\ForceAfterHomDirection# : p\ForceAfterHomDirectionApplicable=False
	
	;fix 2d direction
	If Game\TwoDLock=1 Then 
		If Input\Hold\Left=True And p\TwoDDirection=1 Then
			p\Animation\Direction#=p\Animation\Direction#-180
			p\TwoDDirection=0
		EndIf 
		If Input\Hold\Right=True And p\TwoDDirection=0 Then
			p\Animation\Direction#=p\Animation\Direction#-180
			p\TwoDDirection=1
		EndIf 
	EndIf 
	
	;fall down if slow on slope
	If Abs(p\Rotation#)>80 And p\SpeedLength#<0.5 And (p\Flags\CanClimb=False) And (Not(p\Action=ACTION_GRIND Or p\Action=ACTION_CHARGE)) Then Player_ConvertGroundToAir(p) : p\Motion\Ground=False
	
	;deal translators touched
	If Not(p\TranslatorsTouchedTimer>0) Then p\TranslatorsTouched=0
	
	;ground tension
	If (p\Motion\Ground And (Not(p\Action=ACTION_GRIND Or p\Action=ACTION_DEBUG))) And (Abs(p\Rotation#)<15 Or p\Flags\CanClimb) Then
		MoveEntity(p\Objects\Entity, 0, p\Physics\COMMON_GROUNDTENSION#, 0)
	EndIf
	
	;underwater spinning
	If p\UnderwaterFeet=1 Then
		If p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Then
			If p\No#=1 And (Not(ChannelPlaying(p\Channel_WaterRunning))) Then p\Channel_WaterRunning=EmitSmartSound(Sound_WaterBoosting,p\Objects\Entity)
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		ElseIf p\Action=ACTION_DRIFT
			If p\No#=1 And (Not(ChannelPlaying(p\Channel_WaterRunning))) Then p\Channel_WaterRunning=EmitSmartSound(Sound_WaterDrifting,p\Objects\Entity)
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		Else
			StopChannel(p\Channel_WaterRunning)
		EndIf
	Else
		StopChannel(p\Channel_WaterRunning)
	EndIf
	
	;start been in the air timer
	If p\Motion\Ground=True Then p\BeenInTheAirTimer=0
	
	;metal sonic
	If p\Character=CHAR_MET And (p\Action=ACTION_COMMON Or p\Action=ACTION_JUMP Or p\Action=ACTION_FALL Or p\Action=ACTION_FULLFALL Or p\Action=ACTION_JUMPFALL) Then
		
		If Input\Hold\ActionSkill2 And Player_IsPlayable(p) Then
			If p\AttractionDash=0 Then p\AttractionDashTimer=0 : p\Channel_Attraction=EmitSmartSound(Sound_MetalCharge,p\Objects\Entity) : p\AttractionDash=1
		EndIf
		
		If p\AttractionDash=1 Then 
			p\AttractionDashTimer=p\AttractionDashTimer+timervalue#
			If p\AttractionDashParticleTimer>0 Then p\AttractionDashParticleTimer=p\AttractionDashParticleTimer-timervalue#
			If p\AttractionDashTimer>3*secs# Then p\AttractionDashTimer=3*secs#
			
			If Input\Hold\ActionSkill2=False Then 
				p\AttractionDash=0
				StopChannel(p\Channel_Attraction)
				If p\AttractionDashTimer>=3*secs# Then Player_SetSpeed(p,12) : EmitSmartSound(Sound_MetalRelease,p\Objects\Entity)
			EndIf
			
			
			If (Not(p\AttractionDashParticleTimer>0)) Then
				EmitSmartSound(Sound_SoldierCharge3+Rand(1,3),p\Objects\Entity)
				ParticleTemplate_Call(p\Particle, PARTICLE_OBJECT_SHOCK, p\Objects\Entity, 0, 0, 0, 0, 0, 0.35)
				p\AttractionDashParticleTimer=1*secs#
			EndIf
			
			
		EndIf
	Else
		StopChannel(p\Channel_Attraction)
		p\AttractionDash=0
		p\AttractionDashTimer=0
		
	EndIf
	
	
	If Game\Shield=OBJTYPE_TSHIELD Or p\Flags\Boosting Or p\AttractionDashTimer>0 Or SHOPITEM_ENABLED(SHOPMENU_ABILITY,ABILITY_RINGMAGNET) Then
		If p\Action=ACTION_LIGHTDASH Then p\Flags\CanDrawInRing=False Else p\Flags\CanDrawInRing=True
	Else
		p\Flags\CanDrawInRing=False
	EndIf
	
	;water running
	Select p\Action
		Case ACTION_STOMP,ACTION_BUOY
			p\Flags\CanWaterRun=False
			
			
		Default
			If p\Flags\InJumpAction=False And  p\SpeedLength#>p\Physics\WATERRUN_SPEED# And ((p\UnderwaterFeet=0 And p\WaterBegTooFar=0 And p\Objects\Position\y# < Game\Stage\Properties\WaterLevel+5) Or (Game\Vehicle>0))  Then
				p\Flags\CanWaterRun=True
			Else
				p\Flags\CanWaterRun=False
			EndIf
			
	End Select
	
	If Game\Stage\Properties\Water=1 Then
		Select Game\Stage\Properties\WaterType
			Case 1,3,7,9
				If p\Flags\CanWaterRun=True Then	
					EntityType(Game\Stage\Properties\WaterMesh,COLLISION_WORLD_POLYGON_WATER2)
					
					If p\Collision\GroundType=COLLISION_WORLD_POLYGON_WATER2 Then
						If p\WaterBeg=0 Then p\WaterrunTimer=5*secs# : p\WaterBeg=1
						If (Not(p\WaterrunTimer>0)) Then p\WaterBegTooFar=1
					EndIf 
				Else
					EntityType(Game\Stage\Properties\WaterMesh,COLLISION_NONE)
				EndIf
		End Select
	EndIf 
	
	If p\Animation\Animation=ANIMATION_GRINDSWITCH Then
		If p\Animation\PreviousAnimation=ANIMATION_GRINDFAST Or  p\Animation\PreviousAnimation=ANIMATION_GRIND And p\GrindSwitched = 0 Then
			EmitSmartSound(Sound_GrindStart,p\Objects\Entity)
			p\GrindSwitched=1
		EndIf 
	EndIf 
	
	;see thru shit
	Select p\Character
		Case CHAR_ROU
			If p\Action=ACTION_SPRINT Then
				EntityAlpha(p\Objects\Mesh,0.45)
				p\Invisibility=1
			Else
				EntityAlpha(p\Objects\Mesh,1)
				
			EndIf
		Case CHAR_ESP
			If p\HurtTimer>0 Then
				EntityAlpha(p\Objects\Mesh,0.45)
				p\Invisibility=1
			Else
				EntityAlpha(p\Objects\Mesh,1)
				p\Invisibility=0
			EndIf 
	End Select 
	
	;leaning when up going
	Select p\Action
		Case ACTION_UP:
			If p\Physics\UP_ANGLE#<0 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+1*d\Delta
			If p\Physics\UP_ANGLE#<p\Physics\UP_ANGLE_TARGET# Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#+3*d\Delta
			If p\Physics\UP_ANGLE#>p\Physics\UP_ANGLE_TARGET# Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-3*d\Delta
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
			If (Input\Hold\ActionJump Or p\SpecialSpinTimer>0) And Game\Victory=0 Then
				If p\Physics\UP_ANGLE#>-40 Then p\Physics\UP_ANGLE#=p\Physics\UP_ANGLE#-2.5*d\Delta
			ElseIf (Input\Hold\ActionRoll) And Game\Victory=0 Then
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
	
	
	
	;if run lock is active
	If Game\RunLock>0 Then Player_SetSpeed(p,p\RunLockSpeed#,True)
	
	;after homing, direction too much change fix
	If p\Flags\HomingWasLockedTimer>0 And ((Not(Game\CamLock>0)) Or Game\MachLock>0) Then
		If Player_IsPlayable(p) And (Input\Hold\Up Or Game\MachLock>0) Then p\Animation\Direction#=EntityYaw(cam\Entity)-180
	EndIf
	
	;let shine
	If p\Action=ACTION_FREEZE Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowIce,0,7
	ElseIf (p\Character=CHAR_SIL) And (p\Action=ACTION_LEVITATE Or p\Action=ACTION_PUNCH Or p\Animation\Animation=ANIMATION_MACHRUN Or p\Action=ACTION_THRUST Or p\Action=ACTION_SPRINT Or p\Psychokinesis=1) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlow,0,7
	ElseIf (p\Character=CHAR_MPH) And (p\Action=ACTION_LEVITATE Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowDark,0,7
	ElseIf (p\Character=CHAR_INF) And (p\Action=ACTION_LEVITATE Or p\Action=ACTION_SHOOT Or p\Action=ACTION_JUMPDASH) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlowRuby,0,7
	ElseIf (p\Character=CHAR_SON And (p\Action=ACTION_LIGHTATTACK Or p\Action=ACTION_LIGHTDASH)) Or (p\Character=CHAR_MET And ((p\Action=ACTION_PUNCH And p\PunchNumber>=3) Or p\Action=ACTION_JUMPDASH Or p\Action=ACTION_HOMING Or (p\AttractionDashTimer>=4*secs# And p\AttractionDash=1))) Then
		EntityTexture p\Objects\Mesh,p\Objects\LevitationGlow,0,7
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
	If p\TrickTimer>0 And p\TrickTimer<0.5*secs# Then Player_PlayGoodVoice(p) : p\TrickTimer=0
	
	;lean while tricking
	If p\Physics\TRICK_ANGLE#>0 Then p\Physics\TRICK_ANGLE#=p\Physics\TRICK_ANGLE#-15*d\Delta
	
	;deal stomp sound
	If (Not(p\Action=ACTION_STOMP)) Then StopChannel(p\Channel_Stomp)
	
	;shield management
	If p\No#=1 Then Player_ManageShields()
	
	;shut up shoes and invinc if not shoes or invinc
	If p\No#=1 And Game\SuperForm=0 Then
		If Game\SpeedShoes=0 And ChannelPlaying(Game\Channel_SpeedShoes) Then StopChannel(Game\Channel_SpeedShoes)
		If Game\Invinc=0 And ChannelPlaying(Game\Channel_Invincible) Then StopChannel(Game\Channel_Invincible)
	EndIf
	
	
	If Game\Vehicle=8 Then
		If p\BoardWaterTimer>0 Then
			ParticleTemplate_Call(p\WaterParticle, PARTICLE_PLAYER_WATERSPLASH, p\Objects\Mesh, (p\SpeedLength#/2.0))
		EndIf
	EndIf
	
	If Not(p\Action=ACTION_CHARGE) Then StopChannel(p\Channel_DashCharge)
	
	;deal spin sound
	If Not(p\Action=ACTION_HOP Or p\Action=ACTION_JUMP) Then StopChannel(p\Channel_Spin)
	
	;be hurt!
	If (Not(p\Action=ACTION_DIE)) And p\HurtTimer>0 Then
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
	
;	;leaning
;	If Player_IsPlayable(p) And (Input\Hold\Up Or Input\Hold\Down) And (Input\Hold\Left Or Input\Hold\Right) And ((Not(Game\ControlLock>0)) Or (Not(Game\TwoDLock=1))) Then
;		If Input\Hold\Left Then
;			If p\Physics\LEAN_ANGLE#<p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#+p\Physics\LEAN_ANGLE_SPEED#*d\Delta
;			If p\Physics\LEAN_ANGLE#>p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#-p\Physics\LEAN_ANGLE_SPEED#*d\Delta
;		EndIf
;		If Input\Hold\Right Then
;			If p\Physics\LEAN_ANGLE#<-p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#+p\Physics\LEAN_ANGLE_SPEED#*d\Delta
;			If p\Physics\LEAN_ANGLE#>-p\Physics\LEAN_ANGLE_TARGET# Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#-p\Physics\LEAN_ANGLE_SPEED#*d\Delta
;		EndIf
;	Else
;		If p\Physics\LEAN_ANGLE#<0 Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#+0.7*d\Delta
;		If p\Physics\LEAN_ANGLE#>0 Then p\Physics\LEAN_ANGLE#=p\Physics\LEAN_ANGLE#-0.7*d\Delta
;		If p\Physics\LEAN_ANGLE#<0.5 And p\Physics\LEAN_ANGLE#>-0.5 Then p\Physics\LEAN_ANGLE#=0
;	EndIf
	
	;be invincible!
	If p\Action=ACTION_BLAZETRICK Or (Game\Invinc=1 And Game\SuperForm=0 And Player_IsPlayable(p)) Then ParticleTemplate_Call(p\InvisiParticle, PARTICLE_PLAYER_INVINCIBILITY, p\Objects\Entity, p\ScaleFactor#)
	
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
	
	
	
	;drowning
	If p\Underwater=1 And Game\Interface\DebugPlacerOn=0 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
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
					If p\Underwater=1 And (Not(Game\Shield=OBJTYPE_BSHIELD)) And Menu\ChaoGarden=0 And (Not(Game\Stage\Properties\WaterType=6 Or Game\Stage\Properties\WaterType=7)) Then
						Select InterfaceChar(p\RealCharacter)
							Default:
								If IsCharMod(p\RealCharacter) Then
									p\DrownValue=MODCHARS_DROWN(p\RealCharacter-CHAR_MOD1+1)
								Else
									p\DrownValue=25
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
							For i=3 To Rand(3,5)
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
	If Player_IsPlayable(p) And Menu\ChaoGarden=0 Then
		If Game\Victory<>0 Then
			If (Not(p\Action=ACTION_VICTORY Or p\Action=ACTION_VICTORYHOLD Or (pp(1)\Action=ACTION_VICTORYHOLD And p\Action=ACTION_HOLD2) Or p\Action=ACTION_DIE)) And Game\Vehicle=0 Then
				p\Action=ACTION_VICTORY
			EndIf
			Player_SetSpeed(p,0)
			If p\No#=1 Then Player_VictoryCam()
		EndIf
	EndIf
	
	;mission management
	If Game\Victory=0 And p\No#=1 Then
		Select Menu\Mission
			Case MISSION_ENEMY#,MISSION_CARNIVAL#:
				If Game\Gameplay\Enemies>=Game\MissionValue Then Player_Goal(p)
			Case MISSION_RING#:
				If Game\Gameplay\Rings>=Game\MissionValue Then Player_Goal(p)
			Case MISSION_HUNT#:
				If Game\Gameplay\Shards>=3 Then Player_Goal(p)
			Case MISSION_GOLD#:
				If Game\Gameplay\TotalGoldEnemies>0 And Game\Gameplay\GoldEnemies>=Game\MissionValue Then Player_Goal(p)
			Case MISSION_BALLOONS#:
				If Game\Gameplay\Balloons>=Game\MissionValue Then Player_Goal(p)
			Case MISSION_COLLECT#
				If Game\Gameplay\Collectibles>= Game\MissionValue Then Player_Goal(p)
			Case MISSION_RIVAL#:
				Select Game\RivalAmount
					Case 1: If ppe(1)\Action=ACTION_RIVALDIE Then Player_Goal(p)
					Case 2: If ppe(1)\Action=ACTION_RIVALDIE And ppe(2)\Action=ACTION_RIVALDIE Then Player_Goal(p)
					Case 3: If ppe(1)\Action=ACTION_RIVALDIE And ppe(2)\Action=ACTION_RIVALDIE And ppe(3)\Action=ACTION_RIVALDIE Then Player_Goal(p)
				End Select
			Case MISSION_BOSS#:
				If Game\BossNotDefeated=0 Then Player_Goal(p)
		End Select
		If Menu\MissionTime=1 Then
			If (Game\LimitTime-Game\Gameplay\Time)<0 And Menu\ExitedAStage=0 Then Game_Stage_Quit(5)
		EndIf
		If Menu\MissionMach=1 Or Game\MachLockTriggered=1 Then
			Game\MachLock=1.5*secs#
		EndIf
		
	EndIf
	
	;water splash
	If (Not(p\WaterSplash=p\Underwater)) And (p\No#=1 Or p\Underwater=pp(1)\Underwater) And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
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
			If (Not(p\Action=ACTION_SHOOT Or p\Action=ACTION_SHOOTHOVER Or p\TornadoShoot=1)) And (Not(p\ShootCooldownTimer>0)) And (p\Action=ACTION_COMMON Or p\Action=ACTION_FULLFALL Or p\Action=ACTION_JUMPFALL Or p\Action=ACTION_FALL Or p\Action=ACTION_HOP Or p\Action=ACTION_JUMP Or p\Action=ACTION_HOVER Or p\Action=ACTION_TORNADO) Then
				If p\AimedTargets>0 Then
					;Player_Action_Shoot_Initiate(p,2)
					Player_Action_Shoot_AimShot(p) : 
					If p\Character=CHAR_GAM And p\Action=ACTION_HOVER Then
						p\Action=ACTION_HOVER
						p\ForceShotWalkTimer=0.5*secs#
					Else
						p\Action=ACTION_SHOOT
					EndIf
					
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
	
	;deal obj carry carrying nothing
	If (Not(p\ObjPickUpTimer>0)) And p\ObjPickUp=1 Then p\ObjPickUp=0
	
	;deal how many pet attack
	If Not(p\CheeseRestrictTimer>0) Then p\CheeseAttackedCount=0
	
	;deal grind
	If Not(p\Action=ACTION_GRIND Or p\HasVehicle>0) Then p\GrindTurn=0
	
	;deal vehicle
	If p\No#=1 Then
		If Game\WholeVehicle>0 Then
			If Game\Victory=0 And (Not(p\Action=ACTION_DIE Or p\Action=ACTION_DEBUG Or p\Action=ACTION_HOLD Or p\Action=ACTION_GRIND Or p\IsHoldingTimer>0 Or p\JustDeformedCharacterTimer>0)) Then
				If (Not(Game\Vehicle=Game\WholeVehicle Or (Game\WholeVehicle=6 And Game\Vehicle=7))) Then Game\Vehicle=Game\WholeVehicle
			EndIf
		EndIf
		For ppp.tPlayer = Each tPlayer
			If (Not(ppp\HasVehicle=Game\Vehicle)) And ppp\No#>0 And (ppp\No#=1 Or (Not(Game\Vehicle=6 Or Game\Vehicle=7))) Then
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
	
	If Not(p\Action=ACTION_DRIFT) Then p\PreviousSpeedLength#= p\SpeedLength#
	
	;die on death level
	If Game\Victory=0 And p\Objects\Position\y#<Game\Stage\Properties\DeathLevel And Game\Interface\DebugPlacerOn=0 And (Not(p\Action=ACTION_DIE)) Then Player_Die(p)
	
	
	If p\Action=ACTION_DRIFT Then p\Animation\Direction#=cam\Rotation\y#+180
	
;	If p\Animation\GoToTargetDirection=1 Then
;		
;		If Abs(p\Animation\Direction#-p\Animation\TargetDirection#)<180 Then p\Animation\DirectionTurn=-1 Else p\Animation\DirectionTurn=+1
;		
;		
;		If p\Animation\Direction#>p\Animation\TargetDirection# Then  
;			p\Animation\TargetDirectionType=0
;			p\Animation\Direction#= p\Animation\Direction#-p\Animation\DirectionTurn*p\Animation\TargetDirectionSpeed#*d\Delta
;			If p\Animation\Direction#=90 Then Dummy()
;			
;		EndIf
;		
;		
;		If p\Animation\Direction#<p\Animation\TargetDirection# Then  
;			p\Animation\TargetDirectionType=1
;			p\Animation\Direction#= p\Animation\Direction#+p\Animation\DirectionTurn*p\Animation\TargetDirectionSpeed#*d\Delta
;			If p\Animation\Direction#=90 Then Dummy()
;		EndIf
;		
;;		Select p\Animation\TargetDirectionType
;;			Case 0
;;				If p\Animation\Direction#<p\Animation\TargetDirection# Then Dummy
;;			Case 1
;;				If p\Animation\Direction#>p\Animation\TargetDirection# Then Dummy
;;		End Select
;		
;		
;		
;	EndIf
	
	
	
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
		
		If Abs(p\DestinationSaverPreviousDistance#-EntityDistance(p\Objects\Entity,p\Objects\DestinationTarget)) < 1 Then
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
		For o.tObject = Each tObject
			If o\ThisIsATranslator Then o\Translator\PanelDisabler=0
		Next
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
				If EntityDistance(o\Entity,p\Objects\Entity)<250 And o\Enemy\EnemyShallAppear And Object_IsActualEnemy(o\ObjType) And (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) And (Not(o\Enemy\WasJustAttacked>0)) Then
					o\Enemy\WasKilledByBombMonitor=True
					o\AlwaysPresent=True
				EndIf
			EndIf
		Next
		p\BombMonitorTimer=0
	EndIf
	
	;bomb monitor
	If p\RocketBombTimer>0 Then
		For o.tObject = Each tObject
			If o\ThisIsAnEnemy Then
				If EntityDistance(o\Entity,p\RocketBombPivot)<250 And o\Enemy\EnemyShallAppear And Object_IsActualEnemy(o\ObjType) And (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) And (Not(o\Enemy\WasJustAttacked>0)) Then
					o\Enemy\WasKilledByBombMonitor=True
					o\AlwaysPresent=True
					FreeEntity(p\RocketBombPivot)
				EndIf
			EndIf
		Next
		p\BombMonitorTimer=0
	EndIf
	
	If p\EnemyLeaderTimer>0 Then
		For o.tObject = Each tObject
			If o\ThisIsAnEnemy Then
				If o\Enemy\FollowerNo=p\LeaderToKill And o\Enemy\EnemyShallAppear And Object_IsActualEnemy(o\ObjType) And (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) And (Not(o\Enemy\WasJustAttacked>0)) Then
					o\Enemy\KilledFromLeader=True	
					o\AlwaysPresent=True
				EndIf
			EndIf
		Next
		p\EnemyLeaderTimer=0
	EndIf
	
	;chao garden stuff
	If Menu\ChaoGarden=1 And Player_IsPlayable(p) Then
		;explode inventory
		If Game\Interface\ShallExplodeInventory And Menu\Stage=999 Then
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
			If (Not(p\MayNotWhistleTimer>0)) And p\MayWhistleTimer>0 Then
				Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_WHISTLE))
				If Input\Pressed\ActionSkill2 Then
					Object_Whistle_Create.tObject(p)
					EmitSmartSound(Sound_Whistle,p\Objects\Entity)
				EndIf
			ElseIf (Not(p\MayNotPetTimer>0)) And p\MayPetTimer>0 Then
				Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_PET))
				If Input\Pressed\ActionSkill2 Then
					Object_Petter_Create.tObject(p)
				EndIf
			EndIf
		Else
			If (Not(p\MayNotCheerTimer>0)) And p\MayCheerTimer>0 Then
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
		If (Not(Game\Interface\AutoSaveTimer>0)) And Menu\Stage=999 Then
			SaveGame_AllChaoStuff()
			Game\Interface\AutoSaveTimer=180*secs#
			Game\Interface\AutoSaveShowTimer=0.5*secs#
		Else
			Game\Interface\AutoSaveTimer=Game\Interface\AutoSaveTimer-timervalue#
		EndIf
	EndIf
	
	;be super!
	If (Game\SuperForm>0 And Player_IsPlayable(p)) Then
		ParticleTemplate_Call(p\InvisiParticle, PARTICLE_PLAYER_SUPER, p\Objects\Entity, p\ScaleFactor#)
		If Game\SuperForm=1 Then
			If Player_SuperAuraShouldFire(p\Character) Then
				If p\No#=1 Then ParticleTemplate_Call(p\SuperAuraParticle, PARTICLE_PLAYER_SUPERAURA, p\Objects\Entity, 1+p\ScaleFactor#, 0, 0, 0, 2, 0.2)
			Else
				If p\No#=1 Then ParticleTemplate_Call(p\SuperAuraParticle, PARTICLE_PLAYER_SUPERAURA, p\Objects\Entity, 1+p\ScaleFactor#, 0, 0, 0, 1, 0.2)
			EndIf
		EndIf
		
		Player_CreateRazer.tRazer(p,p\Objects\Mesh,Rand(5,10)/10.0,8)
		
		If p\No#=1 And Game\Interface\DebugPlacerOn=0 Then
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
			If (p\No#>1 And (p\Action=ACTION_HOLD2)) Then
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
	If p\WasGrabbed=1 And (Not(p\Action=ACTION_GRABBED)) Then
		p\WasGrabbed=0
		EntityType(cam\Entity, COLLISION_CAMERA)
		EntityType(p\Objects\Entity, COLLISION_PLAYER)
	EndIf
	
	;special stage stuff
	If Menu\Stage<0 Then
		If Game\Victory=0 And (Not(p\Action=ACTION_DIE Or p\Action=ACTION_DEBUG)) Then
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


;~IDEal Editor Parameters:
;~C#Blitz3D