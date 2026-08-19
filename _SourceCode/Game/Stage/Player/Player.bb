
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer
		; Player objects and entities
		Field Objects.tPlayer_Objects

		; Player core values
		Field No#
		Field Motion.tPlayer_Motion
		Field Flags.tPlayer_Flags
		Field Animation.tPlayer_Animation
		Field Physics.tPlayer_Physics
		Field Collision.tPlayer_Collision
		Field Rival.tPlayer_Rival

		; Debug object placer values
		Field ObjType

		; Main values
		Field Character
		Field RealCharacter
		Field NewCharacter
		Field CharacterMode
		Field Action
		Field SpeedLength#
		Field Rotation#
		Field Underwater
		Field UnderwaterFeet
		Field Frame
		Field TwoDDirection
		
		Field PanelX#
		Field PanelY#
		Field PanelZ#
		Field PanelRotation#
		Field PanelRotationX#
		Field PanelRotationY#
		
		; Other values
		Field DriftDirection
		Field BallLight#[3]
		Field HoldingDrift#
		Field GiveShield
		Field ShowShieldTip
		Field SoarState
		Field SoarStore#
		Field EnemiesBounced#
		Field FlyDistanceLimit
		Field OnJumpPanel
		Field ObjPickUpType
		Field ScaleFactor#
		Field LevitatedOnce
		Field LeaderToKill
		Field BouncesDone
		Field ShootType
		Field JumpActionMode
		Field PunchNumber
		Field BoostingTimer
		Field Invisibility
		Field Psychokinesis
		Field DrownState
		Field DrownValue
		Field WaterSplash
		Field BombThrown
		Field AirKickOnce
		Field CurledUp
		Field BoomerangAway
		Field EnemyComboCounter
		Field DieButDontLoseLife
		Field TranslatorsTouched
		Field CheeseAttackedCount
		Field CanceledFlight
		Field DoubleJumped
		Field ObjPickUp
		Field ObjPickUpTarget.tObject
		Field GrindTurn
		Field TrailBlazer
		Field DoubleJump
		Field HoveredOnce
		Field CannonX#
		Field CannonY#
		Field CannonZ#
		Field GoDestination
		Field PsychoType
		Field DestinationX#
		Field DestinationY#
		Field DestinationZ#
		Field DestinationSpeed#
		Field DestinationSaverPreviousDistance#
		Field SpiritualChange
		Field GetFruit
		Field HasVehicle
		Field ThrowABomb
		Field RadiusChange
		Field ForceAfterHomDirection#
		Field ForceAfterHomDirectionApplicable
		Field StompSaver#
		Field Aiming
		Field GrindSwitched
		Field AimedTargets
		Field WasInBuoyOnce
		Field ThrowType
		Field RunLockSpeed#
		Field WasGrabbed
		Field Inked
		Field VehicleColor
		Field Bouncing
		Field TrickCounter
		Field TornadoShoot
		Field TornadoStance
		Field JumpDashedOnce
		Field Waterrunning
		Field WaterBegTooFar
		Field WaterBeg
		Field UppercutSound
		Field UppercutOnce
		Field DemoDash
		Field HasShotOnce
		Field PreviousSpeedLength#
		Field QuickstepDir
		Field QuickstepSpeed#
		Field RayAnimTimer
		Field Walldash
		Field Shots
		Field TricksDone
		Field RocketBombPivot
		Field MightySpikeHurt
		Field CheeseMode
		; Channels
		Field Channel_Voice
		Field Channel_GroundSkid
		Field Channel_GroundStep
		Field Channel_GroundStep2
		Field Channel_GroundLand
		Field Channel_GroundLand2
		Field Channel_GroundFly
		Field Channel_Fly
		Field Channel_Glide
		Field Channel_GlideX
		Field Channel_Levitate
		Field Channel_WaterRunning
		Field Channel_Drift
		Field Channel_DriftWater
		Field Channel_Stomp
		Field Channel_Climb
		Field Channel_Grind
		Field Channel_Spin
		Field Channel_Fire
		Field Channel_Water
		Field Channel_Psychokinesis
		Field Channel_Tinkle
		Field Channel_ChaosDrive
		Field Channel_Aim
		Field Channel_Charge
		Field Channel_DashCharge
		Field Channel_DashRelease
		Field Channel_BoostCharge
		Field Channel_BoostWind
		Field Channel_BoostStart
		Field Channel_LightAttack
		Field Channel_Attraction
		Field Channel_SoarUp
		Field Channel_SoarDown
		Field Channel_VoiceClip
		Field Channel_TrailBlazer
		Field Channel_Super
		

		; Sounds and voices
		Field Voice[36]

		; Timers
		Field UsedFrameTimer
		Field TranslatorsTouchedTimer
		Field JumpHopTimer
		Field JumpTimer
		Field ShootShotTimer
		Field ChargeTimer
		Field JustChargedTimer
		Field HomingTimer
		Field JumpDashTimer
		Field JumpPanelTimer
		Field TrailBlazerTimer
		Field WaterrunTimer
		Field UpperCutTimer
		Field FlyTimer
		Field GlideRestartTimer
		Field AmySpinTimer
		Field JumpActionRestrictTimer
		Field AttractionDashParticleTimer
		Field LevitationTimer
		Field TrickTimer
		Field HurtTimer
		Field DieTimer
		Field ClimbJumpTimer
		Field ThrowTimer
		Field LightDashTimer
		Field DemoDashTimer
		Field LightDashRequestTimer
		Field SpecialSpinTimer
		Field PunchTimer
		Field DashpadSpinTimer
		Field BoostGaugeTimer
		Field InvisibilityTimer
		Field Hurt2Timer
		Field HurtDisappearTimer
		Field PsychokinesisTimer
		Field RocketBombTimer
		Field PsychokinesisThrowTimer
		Field DrownTimer
		Field BreathCountTimer
		Field StompBounceTimer
		Field BombMonitorTimer
		Field ForceShotWalkTimer
		Field CreateGumBallTimer
		Field LandTimer
		Field FloatTimer
		Field SonicBoomTrailTimer
		Field PsychoChargeTimer
		Field BlazeHoverTimer
		Field ShootCooldownTimer
		Field QuickstepTimer
		Field CheeseRestrictTimer
		Field GlideTimer
		Field GlideStartTimer
		Field EnemyComboTimer
		Field PanelStayTimer
		Field EnemyLeaderTimer
		Field InvisibilityRestrictTimer
		Field PunchRestrictTimer
		Field TeleportTimer
		Field SoarTimer
		Field JustSoaredTimer
		Field ObjPickUpTimer
		Field ObjPickUpThrowTimer
		Field BeenInTheAirTimer
		Field GrindTurnTimer
		Field GrindTurnRestrictTimer
		Field OnDeathMeshTimer
		Field TrailTimer
		Field RingDashStopTimer
		Field DoubleJumpTimer
		Field ChaosStretchSpawnerTimer
		Field ShakeTreeTimer
		Field CanClimbTimer
		Field ShouldBeHoldingTimer
		Field MayWhistleTimer
		Field MayNotWhistleTimer
		Field MayPetTimer
		Field MayNotPetTimer
		Field MayCheerTimer
		Field MayNotCheerTimer
		Field MateChangeTimer
		Field JustChangedMateTimer
		Field ForceJumpTimer
		Field AroundLightDashTimer
		Field BumpedTimer
		Field JumpMayRiseTimer
		Field FollowerIsHoldingLeaderTimer
		Field StompSaverTimer
		Field DestinationSaverTimer
		Field JustStartedAimingTimer
		Field WasInBuoyTimer
		Field UnderwaterTriggerTimer
		Field WasGrabbedTimer
		Field JustLandedTimer
		Field IceFloorTimer
		Field InkFloorTimer
		Field SlowFloorTimer
		Field BumpedCloudTimer
		Field DontGetHurtTimer
		Field JustThrewBombTimer
		Field RubyCubesTimer
		Field RubyGravityTimer
		Field RazerSpawningTimer
		Field RazerSpawningTimer2
		Field DriftTimer
		Field ChaosControlActiveTimer
		Field TornadoChangeTimer
		Field BoardWaterTimer
		Field JustDeformedCharacterTimer
		Field IsHoldingTimer
		Field IsGrabbedTimer
		Field JustGrabbedPulleyTimer
		Field ForceBeingAbleToChangeLeaderTimer
		Field JumpballTimer
		Field CantJumpTimer
		Field AroundEnemyTimer
		Field LightAttackTimer
		Field CheeseShieldTimer
		Field AttractionDashTimer
		Field AttractionDashSpeedTimer
		Field AttractionDashReleaseTimer
		Field AttractionDash
		Field AttractionDashSound
		Field GrindAffectorTimer
		Field WalldashTimer
		

		; Particle templates
		Field Particle.tParticleTemplate
		Field Particle2.tParticleTemplate
		Field WaterParticle.tParticleTemplate
		Field SmokeParticle.tParticleTemplate
		Field JetParticle1.tParticleTemplate
		Field JetParticle2.tParticleTemplate
		Field JetParticle3.tParticleTemplate
		Field JetParticle4.tParticleTemplate
		Field InvisiParticle.tParticleTemplate
		Field SuperAuraParticle.tParticleTemplate
		Field BubbleBreatheParticle.tParticleTemplate
	End Type

	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Objects
		Field Entity
		Field Position.tVector
		Field PPivot[5]
        Field TrailPivot 
		Field Camera.tCamera
		Field Mesh
		Field Mesh2
		Field Mesh3
		Field Mesh4
		Field Mesh5
		Field Mesh6
		Field Shield
		Field Staring
		Field DestinationTarget
		Field Vehicle
		Field VehicleJet1
		Field VehicleJet2
		Field VehicleShoot
		Field VehicleShootController
		Field Hommer.tObject
		Field Scanner
		Field ScannerTarget.tObject
		Field ShadowCircle
		Field WaterBegg
		
		Field R_GrindAffector
		Field L_GrindAffector
		
		; light meshes
		Field RealJumpball
		Field PhantomBarrier
		Field DemoBarrier
        Field JumpBall
		Field Stomp 
		Field Forth
		Field ForthRotation#
		Field ForthAlpha#
		Field ForthScale#
		Field OmoLap
		Field BoostBarrier
		
		Field Follower
		Field Cheese
		Field Froggy

		Field Gum
		Field Jet1
		Field Jet2
		Field Jet3
		Field Jet4
		Field Head
		Field HandR
		Field HandL
		Field ArmR
		Field ArmL
		Field HipR
		Field HipL
		Field LegR
		Field LegL
		Field FootR
		Field FootL
		Field ToeR
		Field ToeL
		Field Spine
		Field Hips
		Field Extra
		Field Extra2
		Field Extra3
		
		Field DebugCube

		Field FollowerPlace[2]

		Field LevitationGlowEmpty
		Field LevitationGlow
		Field LevitationGlowMetal
		Field LevitationGlowDark
		Field LevitationGlowIce
		Field LevitationGlowRuby
	End Type 
	
	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	; Contains information of player object's motion, such as speed and
	; other values.
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Motion
		Field Speed.tVector
		Field Align.tVector
		Field Ground
		
		; player handling fields
		Field Direction#
		Field Pressure#	
		Field Acceleration.tVector
		Field PlayerSpeed.tVector
		Field SpeedNormalX# 
		Field SpeedNormalZ#
		Field SpeedNormal.tVector
		Field SpeedCompensation.tVector	
		Field SpeedLength#		
		Field DeltaCos#
		Field DeltaSin#		
		Field DotProduct#
		
		; player motion fields
		Field GroundTest
		
		; player test collision fields
		Field Collision.tPlayer_Collision		
		
	End Type
	
	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Collision
	
		; player test collision fields
		Field CeilingTest#
		Field GroundTest#
		Field FrontTest#
		Field FrontFactor#
		Field Align, ShouldAlign, Result
		Field Normal.tVector
		Field GroundNormal.tVector
		Field CeilingNormal.tVector
		Field SpeedNormal.tVector
		
		; mesh collisions
		Field Surface 
		Field Triangle
		Field Vertex
		Field DotProduct#
		Field Cross.tVector
		Field GroundType
		
	End Type

	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Flags
		Field AllowCommonInput
		Field AllowXZMovement
		Field AllowYMovement
		Field AllowSkidding
		Field Skidding
		Field InJumpAction
		Field DropDashing
		Field Attacking
		Field InStompAction
		Field StronglyAttacking
		Field CanWaterRun
		Field CantAttackChao
		Field InAirAttack
		Field InJumpAttack
		Field Stomping
		Field Walking
		Field ShouldBounce
		Field DisallowCustomPhysics
        Field LongTrailCreated
		Field InTargeterAttack
		Field InTargeterAirAttack
		Field Targeter
		Field TargeterTimer
		Field MayChangeCharacter
		Field CanStomp
		Field CanSuperTransform
		Field CanClimb
		Field OnJumpPanel
		Field CanDrawInRing
		Field CanBlazeTrick
		;homing flags
		Field HomingTarget.tVector
		Field HomingLocked
		Field HomingNearTarget
		Field HomingLimitTimer
		Field HomingTimer
		Field HomingMesh
		Field HomingMesh2
		Field RingDashTarget.tVector
		Field RingDashLocked
		Field HomingWasLockedTimer
		Field Boosting
	End Type

	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Animation
		Field Animation
		Field PreviousAnimation
		Field Direction#
		Field PreviousDirection#
		Field Align.tVector
		Field Speed#
		Field SpeedChangeBlockTimer
		Field IdleCount
		Field IdleType
		Field AnimTestAnim
		Field AnimTestSpeed#
		Field VictoryStage
		Field Mode
	End Type

	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Physics
		; Common values
		Field COMMON_XZACCELERATION#
		Field COMMON_XZDECELERATION#
		Field COMMON_SKIDDINGFACTOR#
		Field COMMON_XZTOPSPEED#
		Field COMMON_XZMAXSPEED#
		Field COMMON_XZMINSPEED#
		Field COMMON_YACCELERATION#
		Field COMMON_YTOPSPEED#
		Field JUMPDASH_SPEED#
		Field FLY_SPEED#
		Field FLYDOWN_SPEED#
		Field GLIDE_SPEED#
		Field GLIDEFALL_SPEED#
		Field SPINDASH_SPEED#
		Field SKYDIVE_SPEED#
		Field LEVITATION_SPEED#
		Field STOMPFALL_SPEED#
		Field FLOATFALL_SPEED#
		Field DIEFALL_SPEED#
		Field GRIND_SPEED#
		Field REAL_GRIND_SPEED#
		Field BUZZFLYFALL_SPEED#
		Field SLOWGLIDE_SPEED#
		Field CLIMB_SPEED#
		Field HOVER_SPEED#
		Field HOVERFALL_SPEED#
		Field SPRINT_SPEED#
		Field BOUNCE_SPEED#
		Field RINGDASH_SPEED#
		Field FLUTTERFALL_SPEED#
		Field WATERRUN_SPEED#

		; Rotation correction values
		Field UP_ANGLE_ACTUAL#
		Field UP_ANGLE#
		Field UP_ANGLE_TARGET#
		Field LEAN_ANGLE_ACTUAL#
		Field LEAN_ANGLE#
		Field LEAN_ANGLE_TARGET#
		Field LEAN_ANGLE_SPEED#
		Field TRICK_ANGLE_ACTUAL#
		Field TRICK_ANGLE#
		Field DRIFT_ANGLE_ACTUAL#
		Field DRIFT_ANGLE#
		Field DRIFT_ANGLE_TARGET#

		; Motion values
		Field REAL_MOTION_GROUND#
		Field MOTION_GROUND#
		Field MOTION_CEILING#
		Field MOTION_CEILING_STOP#
		Field MOTION_WALL_UP#
		Field MOTION_WALL_DOWN#
		Field MOTION_WALL_DIRECTION#
		Field MOTION_DEVIATION_FACTOR#[3]
		Field MOTION_ANTISLIDING_FACTOR#
		Field UNDERWATERTRIGGER#
		Field UNDERWATERTRIGGERX#
		Field UNDERWATERTRIGGERY#
		Field UNDERWATERTRIGGERZ#
		Field UNDERWATERTRIGGERT#
		Field UNDERWATERTRIGGERW#
		Field ICETRIGGER#
		Field ICETRIGGER2#
		Field ICETRIGGER3#
		Field SLOWTRIGGER#
		Field TURNING_SHARPNESS#
		Field COMMON_GROUNDTENSION#

		; movement values
		Field MOVEMENT_SPEEDCOMP_HIGH#
		Field MOVEMENT_SPEEDCOMP_MID#
		Field MOVEMENT_SPEEDCOMP_LOW#

		; Rolling Values
		Field COMMON_ROLLWEIGHT#
		Field COMMON_ROLLWEIGHT_UP#
		Field COMMON_ROLLWEIGHT_DOWN#
		Field ROLL_WEIGHT_MULTIPLIER#

		; Jump values
		Field JUMP_STRENGTH#
		Field JUMP_STRENGTH_SPECIFIC#
		Field JUMP_STRENGTH_VARIABLE#

		; Flags
		Field Rolling

	End Type
	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Rival
		Field InitialPositionX#
		Field InitialPositionY#
		Field InitialPositionZ#
		Field InitialRotationY#

		Field Health
		Field Speed#
		Field Running
		Field MoveSide

		Field MoveTimer
		Field DontMoveTimer
		Field MakeJumpTimer
		Field MakeJumpActionTimer
		Field MakeAttackTimer
		Field MakeChargeTimer
		Field MakeStompTimer
		Field JustHadActionTimer
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; Action constants
	i = -3
	Global ACTION_DEBUG				= i : i=i+1
	Global ACTION_CHAORACE			= i : i=i+1
	Global ACTION_VICTORY			= i : i=i+1
	Global ACTION_COMMON			= i : i=i+1
	Global ACTION_JUMP				= i : i=i+1
	Global ACTION_HOP				= i : i=i+1
	Global ACTION_LAND				= i : i=i+1
	Global ACTION_FALL				= i : i=i+1
	Global ACTION_JUMPFALL			= i : i=i+1
	Global ACTION_CHARGE			= i : i=i+1
	Global ACTION_ROLL				= i : i=i+1
	Global ACTION_DRIFT				= i : i=i+1
	Global ACTION_UP				= i : i=i+1
	Global ACTION_FWD				= i : i=i+1
	Global ACTION_PANEL			= i : i=i+1
	Global ACTION_PANEL2			= i : i=i+1
	Global ACTION_PANEL3			= i : i=i+1
	Global ACTION_JUMPDASH			= i : i=i+1
	Global ACTION_HOMING			= i : i=i+1
	Global ACTION_FLY				= i : i=i+1
	Global ACTION_GLIDE				= i : i=i+1
	Global ACTION_DOUBLEJUMP		= i : i=i+1
	Global ACTION_DOUBLEJUMPS		= i : i=i+1
	Global ACTION_LEVITATE			= i : i=i+1
	Global ACTION_STOMP				= i : i=i+1
	Global ACTION_HURT				= i : i=i+1
	Global ACTION_DIE				= i : i=i+1
	Global ACTION_FLOAT				= i : i=i+1
	Global ACTION_SLOWGLIDE			= i : i=i+1
	Global ACTION_SPRINT			= i : i=i+1
	Global ACTION_GRIND				= i : i=i+1
	Global ACTION_CLIMB				= i : i=i+1
	Global ACTION_BUMPED			= i : i=i+1
	Global ACTION_HOVER				= i : i=i+1
	Global ACTION_GRABBED			= i : i=i+1
	Global ACTION_THROW				= i : i=i+1
	Global ACTION_LIGHTDASH			= i : i=i+1
	Global ACTION_SHOOT				= i : i=i+1
	Global ACTION_PUNCH				= i : i=i+1
	Global ACTION_THRUST			= i : i=i+1
	Global ACTION_SWIPE				= i : i=i+1
	Global ACTION_UPPERCUT			= i : i=i+1
	Global ACTION_CLAW				= i : i=i+1
	Global ACTION_FULLFALL			= i : i=i+1
	Global ACTION_FLUTTER			= i : i=i+1
	Global ACTION_BUOY				= i : i=i+1
	Global ACTION_PSYCHO			= i : i=i+1
	Global ACTION_TURN				= i : i=i+1
	Global ACTION_SOAR				= i : i=i+1
	Global ACTION_SOARFLAP			= i : i=i+1
	Global ACTION_CARRY				= i : i=i+1
	Global ACTION_DIVE				= i : i=i+1
	Global ACTION_SLEET				= i : i=i+1
	Global ACTION_CANNON			= i : i=i+1
	Global ACTION_CANNON2			= i : i=i+1
	Global ACTION_CANNON3			= i : i=i+1
	Global ACTION_SPIRIT			= i : i=i+1
	Global ACTION_CARRYJUMP			= i : i=i+1
	Global ACTION_CARRYTHROWN		= i : i=i+1
	Global ACTION_SHAKETREE			= i : i=i+1
	Global ACTION_HOLD				= i : i=i+1
	Global ACTION_HOLD2				= i : i=i+1
	Global ACTION_BOARD				= i : i=i+1
	Global ACTION_BOARDJUMP			= i : i=i+1
	Global ACTION_BOARDDRIFT		= i : i=i+1
	Global ACTION_BOARDFALL			= i : i=i+1
	Global ACTION_BOARDTRICK		= i : i=i+1
	Global ACTION_TRANSFORM			= i : i=i+1
	Global ACTION_RIVALDIE			= i : i=i+1
	Global ACTION_GATLING			= i : i=i+1
	Global ACTION_SHOOTHOVER		= i : i=i+1
	Global ACTION_SKYDIVE			= i : i=i+1
	Global ACTION_GLIDER			= i : i=i+1
	Global ACTION_CAR				= i : i=i+1
	Global ACTION_CARFALL			= i : i=i+1
	Global ACTION_CARDRIFT			= i : i=i+1
	Global ACTION_FREEZE			= i : i=i+1
	Global ACTION_HOOKSHOT			= i : i=i+1
	Global ACTION_SINK				= i : i=i+1
	Global ACTION_BELLYFLOP			= i : i=i+1
	Global ACTION_PUDDLE			= i : i=i+1
	Global ACTION_VICTORYHOLD		= i : i=i+1
	Global ACTION_TORNADO			= i : i=i+1
	Global ACTION_TRICK			= i : i=i+1
	Global ACTION_POSTHOM		= i : i=i+1
	Global ACTION_LIGHTATTACK		= i : i=i+1
	Global ACTION_ANIMTEST		= i : i=i+1
	Global ACTION_DEMODASH		= i : i=i+1
	Global ACTION_SCREWKICK		= i : i=i+1
	Global ACTION_SPREAD		= i : i=i+1
	Global ACTION_DIEHURT		= i : i=i+1
	Global ACTION_BLAZETRICK		= i : i=i+1

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Player_DetermineChar(p.tPlayer, realchar)
		p\RealCharacter = realchar
		If IsCharMod(p\RealCharacter) Then
			p\Character=MODCHARS_TYPE(p\RealCharacter-CHAR_MOD1+1)
		Else
			p\Character=p\RealCharacter
		EndIf
		p\NewCharacter=p\RealCharacter
	End Function
	
	; =========================================================================================================
	; =========================================================================================================
	Function Player_Create.tPlayer(no#,rivalrun#=0,rivalfixed#=0)
		; Create new player object
		p.tPlayer 	= New tPlayer
		p\No#=no#
		If no#>0 Then
			pp(no#)=p
			Player_DetermineChar(p,Menu\Character[p\No#])
		Else
			Game\RivalAmount=Game\RivalAmount+1 : ppe(Game\RivalAmount)=p
			p\Rival = New tPlayer_Rival : p\Rival\Health=Game\MissionValue
			p\Rival\Running=rivalrun#
			randomrival#=0
			If rivalfixed#>0 Then
				i = 0
				j = False
				
				If (Not(IsCharMod(Menu\Character[1]))) Then char=Menu\Character[1] Else char=MODCHARS_TYPE(Menu\Character[1]-CHAR_MOD1+1)
				Repeat
					Select rivalfixed#
						Case 1:
							Select i
							Case 0: p\Character = Player_ReturnChosenRival(char,1) : i=i+1
							Case 1: p\Character = Player_ReturnChosenRival(char,2) : i=i+1
							Case 2: p\Character = Player_ReturnChosenRival(char,3) : i=i+1
							Case 3: p\Character = Player_ReturnChosenRival(char,4) : i=i+1
							Case 4: i=i+1
							End Select
						Case 2:
							Select i
							Case 0: p\Character = Player_ReturnChosenRival(char,2) : i=i+1
							Case 1: p\Character = Player_ReturnChosenRival(char,3) : i=i+1
							Case 2: p\Character = Player_ReturnChosenRival(char,4) : i=i+1
							Case 3: p\Character = Player_ReturnChosenRival(char,1) : i=i+1
							Case 4: i=i+1
							End Select
						Case 3:
							Select i
							Case 0: p\Character = Player_ReturnChosenRival(char,3) : i=i+1
							Case 1: p\Character = Player_ReturnChosenRival(char,4) : i=i+1
							Case 2: p\Character = Player_ReturnChosenRival(char,1) : i=i+1
							Case 3: p\Character = Player_ReturnChosenRival(char,2) : i=i+1
							Case 4: i=i+1
							End Select
						Case 4:
							Select i
							Case 0: p\Character = Player_ReturnChosenRival(char,4) : i=i+1
							Case 1: p\Character = Player_ReturnChosenRival(char,1) : i=i+1
							Case 2: p\Character = Player_ReturnChosenRival(char,2) : i=i+1
							Case 3: p\Character = Player_ReturnChosenRival(char,3) : i=i+1
							Case 4: i=i+1
							End Select
					End Select
					j=True
					If (p\Character=Menu\Character[1]) Or (Menu\Members>=2 And p\Character=Menu\Character[2]) Or (Menu\Members>=3 And p\Character=Menu\Character[3]) Then j=False
					If Game\RivalAmount>=2 Then
						If (p\Character=ppe(1)\Character) Then j=False
					EndIf
					If Game\RivalAmount>=3 Then
						If (p\Character=ppe(2)\Character) Then j=False
					EndIf
					If i=4 Then j=True
				Until j
			Else
				randomrival#=1
			EndIf
			If randomrival# Then
				Select Game\RivalAmount
					Case 1:
						Select Menu\Members
							Case 1:
							Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt1(p\Character,Menu\Character[1])
							Case 2:
							Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt2(p\Character,Menu\Character[1],Menu\Character[2])
							Case 3:
							Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt3(p\Character,Menu\Character[1],Menu\Character[2],Menu\Character[3])
						End Select
					Case 2:
						Repeat
							Select Menu\Members
								Case 1:
								Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt1(p\Character,Menu\Character[1])
								Case 2:
								Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt2(p\Character,Menu\Character[1],Menu\Character[2])
								Case 3:
								Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt3(p\Character,Menu\Character[1],Menu\Character[2],Menu\Character[3])
							End Select
						Until Menu_RandomNonmodChar_AcceptableAt2(ppe(1)\Character,p\Character,False)
					Case 3:
						Repeat
							Select Menu\Members
								Case 1:
								Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt1(p\Character,Menu\Character[1])
								Case 2:
								Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt2(p\Character,Menu\Character[1],Menu\Character[2])
								Case 3:
								Repeat : p\Character = Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_RivalAcceptableAt3(p\Character,Menu\Character[1],Menu\Character[2],Menu\Character[3])
							End Select
						Until Menu_RandomNonmodChar_AcceptableAt3(ppe(1)\Character,ppe(2)\Character,p\Character,False)
				End Select
			EndIf
			Player_DetermineChar(p,p\Character)
		EndIf

		; Create objects
		p\Objects 	= New tPlayer_Objects
		p\Motion  	= New tPlayer_Motion
		p\Flags		= New tPlayer_Flags
		p\Animation	= New tPlayer_Animation
		p\Physics	= New tPlayer_Physics
		p\Collision	= New tPlayer_Collision  
		p\Motion\Speed    = Vector(0, 0, 0)
		p\Motion\Align 	  = Vector(0, 1, 0)
		p\Animation\Align = Vector(0, 1, 0)
		p\Objects\Entity = CreatePivot(Game\Stage\Root)
		p\Objects\Position = New tVector
		For i=0 To 4 : p\Objects\PPivot[i] = CreatePivot(p\Objects\Entity) : ScaleEntity(p\Objects\PPivot[i],1.075,1.075,1.075) : Next
		p\Objects\TrailPivot = CreatePivot(p\Objects\Entity)

		; Homing Attack flags
		p\Flags\HomingTarget = Vector(0, 0, 0)
		p\Flags\HomingLimitTimer = 0
		p\Flags\HomingTimer		 = 0
		p\Flags\HomingLocked	 = False
		p\Flags\HomingMesh = CreateCube()
		p\Flags\HomingMesh2 = CreateCube()
		HideEntity(p\Flags\HomingMesh)
		HideEntity(p\Flags\HomingMesh2)
		p\Flags\RingDashTarget = Vector(0, 0, 0)

		; Some unnecessary input stuff
		p\Flags\AllowCommonInput = True
		p\Flags\AllowXZMovement  = True
		p\Flags\AllowYMovement   = True
		
		

		; Form character
		p\Objects\Staring=CreatePivot()
		p\Objects\DestinationTarget=CreatePivot()
		
		DeformCharacter(p)
	
		p\Objects\WaterBegg=CreatePivot
		p\Objects\OmoLap=CopyEntity(MESHES(Mesh_OmoLap), Game\Stage\Root) : Animate(p\Objects\OmoLap,1,0.4) : HideEntity(p\Objects\OmoLap)
		p\Objects\DemoBarrier=CopyEntity(MESHES(Mesh_DemoBarrier), Game\Stage\Root) : Animate p\Objects\DemoBarrier,1,1 : HideEntity(p\Objects\DemoBarrier)
		p\Objects\PhantomBarrier=CopyEntity(MESHES(Mesh_PhantomBarrier), Game\Stage\Root) : Animate p\Objects\PhantomBarrier,1,1 : HideEntity(p\Objects\PhantomBarrier)
		p\Objects\JumpBall=CopyEntity(MESHES(Mesh_JumpBall), Game\Stage\Root) : Animate p\Objects\JumpBall,1,1 : HideEntity(p\Objects\JumpBall)
		p\Objects\Stomp=CopyEntity(MESHES(Mesh_Stomp), Game\Stage\Root) : Animate p\Objects\Stomp,1,1 : HideEntity(p\Objects\Stomp)
		p\Objects\Forth=CopyEntity(MESHES(Mesh_Forth), Game\Stage\Root) : Animate p\Objects\Forth,1,1 : HideEntity(p\Objects\Forth)
		p\Objects\Scanner=CopyEntity(MESHES(Mesh_Scanner), Game\Stage\Root) : HideEntity(p\Objects\Scanner)
		p\Objects\BoostBarrier=CopyEntity(MESHES(Mesh_BoostBarrier), Game\Stage\Root) : Animate p\Objects\BoostBarrier,1,1 : HideEntity(p\Objects\BoostBarrier)
		If Menu\Settings\Shadows#=2 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then p\Objects\ShadowCircle = Init_CircleShadow(p\Objects\Entity , p\Objects\Mesh, 1.25)
		p\Objects\RealJumpball=CopyEntity(MESHES(Mesh_RealJumpball), Game\Stage\Root) :  HideEntity(p\Objects\RealJumpball)
		
		
		
		
		
		
		
		
		
		; Form places
		p\Objects\Follower=CreatePivot()
		p\Objects\Cheese=CreatePivot()
		p\Objects\Froggy=CreatePivot()
		p\Objects\FollowerPlace[1-1]=CreatePivot()
		p\Objects\FollowerPlace[2-1]=CreatePivot()
		p\Objects\FollowerPlace[3-1]=CreatePivot()
		If p\No#>0 Then
			p\Objects\Hommer.tObject = Object_Hommer_Create.tObject(p,1)
		Else
			p\Objects\Hommer.tObject = Object_Hommer_Create.tObject(p,-1)
		EndIf
		
		

		; Blending textures
		p\Objects\LevitationGlowEmpty = CreateTexture(0,0)
		p\Objects\LevitationGlow = LoadTexture("Textures/levitation.png",64)
		TextureBlend p\Objects\LevitationGlow,3
		p\Objects\LevitationGlowMetal = LoadTexture("Textures/levitationm.png",64)
		TextureBlend p\Objects\LevitationGlowMetal,3
		p\Objects\LevitationGlowDark = LoadTexture("Textures/levitationd.png",64)
		TextureBlend p\Objects\LevitationGlowDark,3
		p\Objects\LevitationGlowIce = LoadTexture("Textures/levitationb.png",64)
		TextureBlend p\Objects\LevitationGlowIce,3
		p\Objects\LevitationGlowRuby = LoadTexture("Textures/levitationr.png",64)
		TextureBlend p\Objects\LevitationGlowRuby,3

		; Initiate camera target
		If Menu\Stage<>0 And Player_IsPlayable(p) Then
			For c.tCamera=Each tCamera : Camera_Bind(c,p) : Next
		EndIf
		
		; Setup pivot collision
		EntityType(p\Objects\Entity, COLLISION_PLAYER)
		Player_SetRadius#(p)
		Player_Spawn(Game\Gameplay\CheckX#,Game\Gameplay\CheckY#,Game\Gameplay\CheckZ#,Game\Gameplay\CheckDirection#)

		; Particle templates
		p\Particle = ParticleTemplate_Create.tParticleTemplate()
		p\Particle2 = ParticleTemplate_Create.tParticleTemplate()
		p\WaterParticle = ParticleTemplate_Create.tParticleTemplate()
		p\SmokeParticle = ParticleTemplate_Create.tParticleTemplate()
		p\JetParticle1 = ParticleTemplate_Create.tParticleTemplate()
		p\JetParticle2 = ParticleTemplate_Create.tParticleTemplate()
		p\JetParticle3 = ParticleTemplate_Create.tParticleTemplate()
		p\JetParticle4 = ParticleTemplate_Create.tParticleTemplate()
		p\InvisiParticle = ParticleTemplate_Create.tParticleTemplate()
		p\SuperAuraParticle = ParticleTemplate_Create.tParticleTemplate()
		p\BubbleBreatheParticle = ParticleTemplate_Create.tParticleTemplate()
		
		p\Physics\REAL_MOTION_GROUND#=0.7
		p\AttractionDashParticleTimer=0.1*secs#

		; Load sounds and voices
		If Menu\Stage<>0 Then Player_LoadVoices(p)

		; Done
		p\Action = ACTION_FALL
		Return p
	End Function


	; =========================================================================================================
	; =========================================================================================================
	Function Player_Destroy(p.tPlayer)
		FreeEntity(p\Objects\Entity)
		FreeEntity(p\Objects\Mesh)
		If p\Objects\Shield<>0 Then FreeEntity(p\Objects\Shield)
		
		Delete p\Motion\Speed
		Delete p\Motion\Align
		Delete p\Animation\Align
		Delete p\Objects
		Delete p\Motion
		Delete p\Animation
		Delete p\Flags
	End Function


	; =========================================================================================================
	; =========================================================================================================
	Function Player_Update(p.tPlayer, d.tDeltaTime)
	If p\No#=1 Or Game\Interface\DebugPlacerOn=0 Then

		; Run cheats
		
			If Menu\Settings\Debug#=1 And ((Menu\ChaoGarden=0 And Menu\CollectionRoom=0 And Menu\TutorialMode=0 And Menu\MarathonMode=0) Or Menu\Developer=1) Then 
				Player_HandleCheats(p)
			EndIf

		If (Not(Game\CinemaMode=1)) Then
			
			
			
			; Perform player's movement
			Player_Motion(p, d)
			
			
			Player_Animate(p, d)
				
			; Physics
			Player_Physics(p,d)
			
			; Handle 
			Player_Handle(p, d)
			
			; Effects
			Player_UpdateEffects(p,d)
			
			; Actions
			Select p\Action
				Case ACTION_DEBUG
					Player_Action_Debug(p,d)
				Case ACTION_DEMODASH
					Player_Action_DemoDash(p)
				Case ACTION_ANIMTEST
					Player_Action_Animtest(p)
				Case ACTION_CHAORACE
					Player_Action_ChaoRace(p)
				Case ACTION_COMMON
					Player_Action_Common(p)
				Case ACTION_JUMP
					Player_Action_Jump(p)
				Case ACTION_LIGHTATTACK
					Player_Action_LightAttack(p)
				Case ACTION_HOP
					Player_Action_Hop(p)
				Case ACTION_LAND
					Player_Action_Land(p)
				Case ACTION_FALL
					Player_Action_Fall(p)
				Case ACTION_POSTHOM
					Player_Action_PostHom(p)	
				Case ACTION_PANEL,ACTION_PANEL2,ACTION_PANEL3
					Player_Action_Panel(p)
				Case ACTION_JUMPFALL,ACTION_TRICK,ACTION_BLAZETRICK
					Player_Action_JumpFall(p)
				Case ACTION_CHARGE
					Player_Action_Charge(p)
				Case ACTION_ROLL
					Player_Action_Roll(p)
				Case ACTION_DRIFT
					Player_Action_Drift(p)
				Case ACTION_FWD
					Player_Action_Fwd(p)
				Case ACTION_UP
					Player_Action_Up(p)
				Case ACTION_JUMPDASH
					Player_Action_JumpDash(p)
				Case ACTION_HOMING
					Player_Action_Homing(p)
				Case ACTION_FLY
					Player_Action_Fly(p)
				Case ACTION_GLIDE
					Player_Action_Glide(p)
				Case ACTION_DOUBLEJUMP
					Player_Action_DoubleJump(p)
				Case ACTION_DOUBLEJUMPS
					Player_Action_DoubleJumpSkill(p)
				Case ACTION_LEVITATE
					Player_Action_Levitate(p)
				Case ACTION_STOMP
					Player_Action_Stomp(p)
				Case ACTION_HURT,ACTION_DIEHURT
					Player_Action_Hurt(p)
				Case ACTION_DIE
					Player_Action_Die(p)
				Case ACTION_FLOAT
					Player_Action_Float(p)
				Case ACTION_SLOWGLIDE
					Player_Action_SlowGlide(p)
				Case ACTION_SCREWKICK,ACTION_SPREAD
					Player_Action_ScrewKick(p)
				Case ACTION_SPRINT
					Player_Action_Sprint(p)
				Case ACTION_GRIND
					Player_Action_Grind(p)
				Case ACTION_CLIMB
					Player_Action_Climb(p)
				Case ACTION_BUMPED
					Player_Action_Bumped(p)
				Case ACTION_HOVER
					Player_Action_Hover(p)
				Case ACTION_GRABBED
					Player_Action_Grabbed(p)
				Case ACTION_THROW
					Player_Action_Throw(p)
				Case ACTION_LIGHTDASH
					Player_Action_LightDash(p)
				Case ACTION_SHOOT
					Player_Action_Shoot(p)
				Case ACTION_PUNCH,ACTION_THRUST
					Player_Action_Punch(p)
				Case ACTION_SWIPE
					Player_Action_Swipe(p)
				Case ACTION_UPPERCUT
					Player_Action_Uppercut(p)
				Case ACTION_CLAW
					Player_Action_Claw(p)
				Case ACTION_FULLFALL
					Player_Action_FullFall(p)
				Case ACTION_FLUTTER
					Player_Action_Flutter(p)
				Case ACTION_BUOY
					Player_Action_Buoy(p)
				Case ACTION_PSYCHO
					Player_Action_Psycho(p)
				Case ACTION_TURN
					Player_Action_Turn(p)
				Case ACTION_SOAR
					Player_Action_Soar(p)
				Case ACTION_SOARFLAP
					Player_Action_SoarFlap(p)
				Case ACTION_CARRY
					Player_Action_Carry(p)
				Case ACTION_DIVE
					Player_Action_Dive(p)
				Case ACTION_SLEET
					Player_Action_Sleet(p)
				Case ACTION_CANNON,ACTION_CANNON2
					Player_Action_Cannon(p)
				Case ACTION_CANNON3
					Player_Action_Up(p)
				Case ACTION_SPIRIT
					Player_Action_Spirit(p)
				Case ACTION_CARRYJUMP
					Player_Action_CarryJump(p)
				Case ACTION_CARRYTHROWN
					Player_Action_CarryThrown(p)
				Case ACTION_SHAKETREE
					Player_Action_ShakeTree(p)
				Case ACTION_HOLD,ACTION_HOLD2
					Player_Action_Hold(p)
				Case ACTION_BOARD
					Player_Action_Board(p)
				Case ACTION_BOARDJUMP
					Player_Action_BoardJump(p)
				Case ACTION_BOARDDRIFT
					Player_Action_BoardDrift(p)
				Case ACTION_BOARDFALL
					Player_Action_BoardFall(p)
				Case ACTION_BOARDTRICK
					Player_Action_BoardTrick(p)
				Case ACTION_TRANSFORM
					Player_Action_Transform(p)
				Case ACTION_RIVALDIE
					Player_Action_RivalDie(p)
				Case ACTION_GATLING
					Player_Action_Gatling(p)
				Case ACTION_SHOOTHOVER
					Player_Action_Shoot_Hover(p)
				Case ACTION_SKYDIVE
					Player_Action_Skydive(p)
				Case ACTION_GLIDER
					Player_Action_Glider(p)
				Case ACTION_CAR,ACTION_CARFALL
					Player_Action_Car(p)
				Case ACTION_CARDRIFT
					Player_Action_CarDrift(p)
				Case ACTION_FREEZE
					Player_Action_Freeze(p)
				Case ACTION_HOOKSHOT
					Player_Action_Hookshot(p)
				Case ACTION_SINK
					Player_Action_Sink(p)
				Case ACTION_BELLYFLOP
					Player_Action_BellyFlop(p)
				Case ACTION_PUDDLE
					Player_Action_Puddle(p)
				Case ACTION_TORNADO
					Player_Action_Tornado(p)
			End Select

			

			

			; Rival
			If p\No#<0 Then Player_Rival(p,d)
		Else
			Player_Motion_Placements(p)
		EndIf

	EndIf
	End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	

	

;______________________________________________________________________________________________________________________________________________________________________
;______________________________________________________________________________________________________________________________________________________________________

	
;______________________________________________________________________________________________________________________________________________________________________
;______________________________________________________________________________________________________________________________________________________________________

	
;______________________________________________________________________________________________________________________________________________________________________
;______________________________________________________________________________________________________________________________________________________________________
;~IDEal Editor Parameters:
;~C#Blitz3D