
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
		Field DebugSpam
		Field UpFromBouncing 
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

		; Other values
		Field DriftDirection
		Field HasShotOnce
		Field FlyingSwipe
		Field WaterRunning
		Field FlyDistanceLimit
		Field ScaleFactor#
		Field LevitatedOnce
		Field BouncesDone
		Field JumpActionMode
		Field PunchNumber
		Field Invisibility
		Field Psychokinesis
		Field DrownState
		Field DrownValue
		Field WaterSplash
		Field BombThrown
		Field AirKickOnce
		Field BoomerangAway
		Field EnemyComboCounter
		Field DieButDontLoseLife
		Field TranslatorsTouched
		Field CheeseAttackedCount
		Field CheeseAttackType
		Field ObjPickUp
		Field ObjPickUpTarget.tObject
		Field GrindTurn
		Field DoubleJump
		Field HoveredOnce
		Field CannonX#
		Field CannonY#
		Field CannonZ#
		Field GoDestination
		Field DestinationX#
		Field DestinationY#
		Field DestinationZ#
		Field DestinationSpeed#
		Field DestinationSaverPreviousDistance#
		Field SpiritualChange
		Field GetFruit
		Field AirBegGround
		Field AirBegTooFar
		Field HasVehicle
		Field ThrowABomb
		Field RadiusChange
		Field ForceAfterHomDirection#
		Field ForceAfterHomDirectionApplicable
		Field StompSaver#
		Field Aiming
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
		Field LightAttackHits
		Field PanelStayActivated

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
		Field Channel_Spindash
		Field Channel_BoostStart
		Field CHannel_BoostWind
		Field Channel_BoostCharge
		Field Channel_Dive
		Field Channel_Skydive
		Field Channel_SkydiveFast

		; Sounds and voices
		Field Voice[33]

		; Timers
		Field UsedFrameTimer
		Field PanelStayTimer
		Field PunchWindowTimer
		Field TranslatorsTouchedTimer
		Field JumpHopTimer
		Field JumpTimer
		Field CheeseShieldTimer
		Field ChargeTimer
		Field JustChargedTimer
		Field HomingTimer
		Field JumpDashTimer
		Field FlyTimer
		Field GlideRestartTimer
		Field JumpActionRestrictTimer
		Field LevitationTimer
		Field TrickTimer
		Field HurtTimer
		Field DieTimer
		Field ThrowTimer
		Field LightDashTimer
		Field LightDashRequestTimer
		Field SpecialSpinTimer
		Field PunchTimer
		Field InvisibilityTimer
		Field HurtDisappearTimer
		Field PsychokinesisTimer
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
		Field ShootCooldownTimer
		Field CheeseRestrictTimer
		Field GlideTimer
		Field GlideStartTimer
		Field EnemyComboTimer
		Field InvisibilityRestrictTimer
		Field PunchRestrictTimer
		Field TeleportTimer
		Field SoarTimer
		Field JustSoaredTimer
		Field ObjPickUpTimer
		Field ObjPickUpThrowTimer
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
		Field HyperBlastLimiterTimer
		Field ChaosControlActiveTimer
		Field TornadoChangeTimer
		Field BoardWaterTimer
		Field JustDeformedCharacterTimer
		Field IsHoldingTimer
		Field IsGrabbedTimer
		Field JustGrabbedPulleyTimer
		Field ForceBeingAbleToChangeLeaderTimer
		Field CantJumpTimer
		Field DebugSpamTimer
		Field BoostingTimer
		Field LightAttackTimer
		Field AroundEnemyTimer
		Field JumpPanelTimer

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
		Field AirBeg
		Field Vehicle
		Field VehicleJet1
		Field VehicleJet2
		Field VehicleShoot
		Field VehicleShootController
		Field Hommer.tObject
		Field Scanner
		Field ScannerTarget.tObject
		Field ShadowCircle

		; light meshes
       		Field JumpBall
		Field Stomp 
		Field Forth
		Field ForthRotation#
		Field ForthAlpha#
		Field ForthScale#
		
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
		Field Attacking
		Field StronglyAttacking
		Field CantAttackChao
		Field InAirAttack
		Field InJumpAttack
		Field Stomping
		Field Walking
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
		Field InALocker
		Field ReadyToLightAttack 
		Field OnJumpPanel

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
	End Type

	; ---------------------------------------------------------------------------------------------------------	
	; ---------------------------------------------------------------------------------------------------------
	Type tPlayer_Animation
		Field Animation
		Field PreviousAnimation
		Field Direction#
		Field Align.tVector
		Field Speed#
		Field SpeedChangeBlockTimer
		Field IdleCount
		Field IdleType
		Field VictoryStage
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
		Field BUZZFLYFALL_SPEED#
		Field SLOWGLIDE_SPEED#
		Field CLIMB_SPEED#
		Field HOVER_SPEED#
		Field HOVERFALL_SPEED#
		Field SPRINT_SPEED#
		Field BOUNCE_SPEED#
		Field RINGDASH_SPEED#
		Field FLUTTERFALL_SPEED#

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
	Global ACTION_BOOSTFALL				= i : i=i+1
	Global ACTION_JUMPFALL			= i : i=i+1
	Global ACTION_CHARGE			= i : i=i+1
	Global ACTION_ROLL				= i : i=i+1
	Global ACTION_DRIFT				= i : i=i+1
	Global ACTION_UP				= i : i=i+1
	Global ACTION_FWD				= i : i=i+1
	Global ACTION_JUMPDASH			= i : i=i+1
	Global ACTION_HOMING			= i : i=i+1
	Global ACTION_FLY				= i : i=i+1
	Global ACTION_GLIDE				= i : i=i+1
	Global ACTION_DOUBLEJUMP		= i : i=i+1
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
	Global ACTION_BOARDGRIND				= i : i=i+1
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
	Global ACTION_POSTHOM			= i : i=i+1
	Global ACTION_TRICK			= i : i=i+1
	Global ACTION_BOOST			= i : i=i+1
	Global ACTION_PANEL			= i : i=i+1
	Global ACTION_PANEL2			= i : i=i+1
	Global ACTION_PANEL3			= i : i=i+1
	Global ACTION_LIGHTATTACK			= i : i=i+1

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
			If (Not(p\Character=CHAR_TMH)) Then p\JumpActionMode=JUMPAMODE[p\RealCharacter]
		Else
			Game\RivalAmount=Game\RivalAmount+1 : ppe(Game\RivalAmount)=p
			p\Rival = New tPlayer_Rival : p\Rival\Health=5
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
		p\Objects\JumpBall=CopyEntity(MESHES(Mesh_JumpBall), Game\Stage\Root) : Animate p\Objects\JumpBall,1,1 : HideEntity(p\Objects\JumpBall)
		p\Objects\BoostBarrier=CopyEntity(MESHES(Mesh_BoostBarrier), Game\Stage\Root) : Animate p\Objects\BoostBarrier,1,1 : HideEntity(p\Objects\BoostBarrier)
		p\Objects\Stomp=CopyEntity(MESHES(Mesh_Stomp), Game\Stage\Root) : Animate p\Objects\Stomp,1,1 : HideEntity(p\Objects\Stomp)
		p\Objects\Forth=CopyEntity(MESHES(Mesh_Forth), Game\Stage\Root) : Animate p\Objects\Forth,1,1 : HideEntity(p\Objects\Forth)
		p\Objects\Scanner=CopyEntity(MESHES(Mesh_Scanner), Game\Stage\Root) : HideEntity(p\Objects\Scanner)
		If Menu\Settings\Shadows#>0 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then p\Objects\ShadowCircle = Init_CircleShadow(p\Objects\Entity , p\Objects\Mesh, 1.25)

		; Form places
		p\Objects\Follower=CreatePivot()
		p\Objects\Cheese=CreatePivot()
		p\Objects\Froggy=CreatePivot()
		p\Objects\FollowerPlace[1-1]=CreatePivot()
		p\Objects\FollowerPlace[2-1]=CreatePivot()
		p\Objects\FollowerPlace[3-1]=CreatePivot()
		p\Objects\AirBeg=CreatePivot()
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
		If Menu\Settings\Debug#=1 And ((Menu\ChaoGarden=0 And Menu\Stage>0 And Menu\MarathonMode=0) Or Menu\Developer=1) Then Player_HandleCheats(p)

		If (Not(Game\CinemaMode=1)) Then
			; Perform player's movement
			Player_Motion(p, d)

			; Handle actions
			Player_Handle(p, d)
			Select p\Action
				Case ACTION_DEBUG
					Player_Action_Debug(p,d)
				Case ACTION_CHAORACE
					Player_Action_ChaoRace(p)
				Case ACTION_COMMON,ACTION_BOOST
					Player_Action_Common(p)
				Case ACTION_JUMP
					Player_Action_Jump(p)
				Case ACTION_LIGHTATTACK
					Player_Action_LightAttack(p)
				Case ACTION_HOP
					Player_Action_Hop(p)
				Case ACTION_LAND
					Player_Action_Land(p)
				Case ACTION_FALL,ACTION_BOOSTFALL
					Player_Action_Fall(p)
				Case ACTION_POSTHOM
					Player_Action_PostHom(p)
					
				Case ACTION_PANEL,ACTION_PANEL2,ACTION_PANEL3
					Player_Action_Panel(p)
				Case ACTION_JUMPFALL
					Player_Action_JumpFall(p)
				Case ACTION_CHARGE
					Player_Action_Charge(p)
				Case ACTION_ROLL
					Player_Action_Roll(p)
					Case ACTION_TRICK
					Player_Action_Trick(p)
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
				Case ACTION_LEVITATE
					Player_Action_Levitate(p)
				Case ACTION_STOMP
					Player_Action_Stomp(p)
				Case ACTION_HURT
					Player_Action_Hurt(p)
				Case ACTION_DIE
					Player_Action_Die(p)
				Case ACTION_FLOAT
					Player_Action_Float(p)
				Case ACTION_SLOWGLIDE
					Player_Action_SlowGlide(p)
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
				Case ACTION_BOARD,ACTION_BOARDGRIND
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
					Player_Action_ShootHover(p)
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

			; Update All Effects
			Player_SetLongTrail(p)
			If p\No#=1 Then
				Player_UpdateTrails()
				Player_UpdateLongTrail()
				For rz.tRazer = Each tRazer : Player_UpdateRazer(rz, d) : Next
				For af.tAfterImage = Each tAfterImage : Update_AfterImage(af,p,d) : Next
				For ee.tEmerald = Each tEmerald : Update_Emerald(ee, p, d) : Next
			EndIf
			For sp.tSpark = Each tSpark : Update_Spark(sp, p, d) : Next
			If Menu\Settings\Shadows#>0 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
				If p\No#<0 Then
					Update_CircleShadow(p\Objects\ShadowCircle, p\Objects\Mesh, pp(1)\Objects\Camera\Entity)
				Else
					Update_CircleShadow(p\Objects\ShadowCircle, p\Objects\Mesh, p\Objects\Camera\Entity)
				EndIf
			EndIf

			; Animate
			Player_Animate(p, d)

			; Rival
			If p\No#<0 Then Player_Rival(p,d)
		Else
			Player_Motion_Placements(p)
		EndIf

	EndIf
	End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function DeformCharacter_FixUps(p.tPlayer)
		p\ScaleFactor#=0.125*GetCharScaleFactor#(p\RealCharacter)
		If p\No#=1 Then CAMERA_CONTROL_SIZEFACTOR#=5*p\ScaleFactor#

		p\DrownState=0
		If Menu\Members=1 Then p\Invisibility=0
	End Function

Function DeformCharacter(p.tPlayer,dontstartoutlock=False)
		p\Flags\LongTrailCreated=0
		Player_FreeLongTrails(p,2)

		Select p\Character
			Case CHAR_EME: If p\CharacterMode=0 Then p\CharacterMode=CHAR_SON
			Case CHAR_GME: If p\CharacterMode=0 Then p\CharacterMode=CHAR_ESP
			Default: p\CharacterMode=0
		End Select

		If p\Objects\Mesh<>0 Then ShowEntity(p\Objects\Mesh)
		FreeEntity p\Objects\Mesh

		If Menu\Stage=0 Then
			p\Objects\Mesh=CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		Else
			If (Menu\Members>1 Or Game\CheaterChangedCharacter=0) And Player_IsPlayable(p) Then
				If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
					Select p\RealCharacter
						Case Menu\Character[1]: p\Objects\Mesh=CopyEntity(Game\CharacterMesh[1], Game\Stage\Root)
						Case Menu\Character[2]: p\Objects\Mesh=CopyEntity(Game\CharacterMesh[2], Game\Stage\Root)
						Case Menu\Character[3]: p\Objects\Mesh=CopyEntity(Game\CharacterMesh[3], Game\Stage\Root)
					End Select
				Else
					Select p\RealCharacter
						Case Menu\Character[1]: p\Objects\Mesh=CopyEntity(Game\SuperCharacterMesh[1], Game\Stage\Root)
						Case Menu\Character[2]: p\Objects\Mesh=CopyEntity(Game\SuperCharacterMesh[2], Game\Stage\Root)
						Case Menu\Character[3]: p\Objects\Mesh=CopyEntity(Game\SuperCharacterMesh[3], Game\Stage\Root)
					End Select
				EndIf
			Else
				If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
					LoadCharacterMesh(p\RealCharacter,0)
				Else
					Select p\RealCharacter
						Case CHAR_SON,CHAR_TAI,CHAR_KNU,CHAR_SHA,CHAR_SIL,CHAR_BLA:
							LoadCharacterMesh(p\RealCharacter,0,1)
						Default:
							If IsCharMod(p\RealCharacter) Then
								If CharModHasSuper(p\RealCharacter) Then
									LoadCharacterMesh(p\RealCharacter,0,1)
								Else
									LoadCharacterMesh(p\RealCharacter,0,0)
								EndIf
							Else
								LoadCharacterMesh(p\RealCharacter,0,0)
							EndIf
					End Select
				EndIf
				p\Objects\Mesh=CopyEntity(CharacterMesh, Game\Stage\Root)
				DeleteCharacterMesh()
				For t.tTrail=Each tTrail : t\char=p\RealCharacter : Next
				For trail.tLongTrail=Each tLongTrail : trail\char=p\RealCharacter : Next
			EndIf
		EndIf

		DeformCharacter_GetTheBoneEntities(p)

		EntityShininess(p\Objects\Mesh, 0)

		p\Action=ACTION_FALL
		If p\Motion\Ground Then p\Motion\Ground=False
		Select p\Character
			Case CHAR_CHA: p\Motion\Speed\y#=0.20*p\Physics\UNDERWATERTRIGGER#
			Case CHAR_VEC,CHAR_BIG: p\Motion\Speed\y#=0.35*p\Physics\UNDERWATERTRIGGER#
			Default: p\Motion\Speed\y#=0.30*p\Physics\UNDERWATERTRIGGER#
		End Select

		If dontstartoutlock=False And p\No#=1 Then
			Game\ControlLock=0.5*secs#
		EndIf

		If Menu\Members=1 Then
			If Player_IsPlayable(p) Then Menu\Character[1]=p\RealCharacter
			Player_LoadVoices(p)
		EndIf

		Animate(p\Objects\Mesh, 1, ((p\SpeedLength#+0.4531+(1/0.1)*0.1))/2.0, ANIMATION_SPIN, 1)

		DeformCharacter_FixUps(p)

		If Game\Shield>0 Then ScaleEntity p\Objects\Shield, 1+p\ScaleFactor#, 1+p\ScaleFactor#, 1+p\ScaleFactor#

		Game\SmartCameraRangeDontAffectTimer=5*secs#
	End Function

Function Player_HasSuperModel(char)
		Select char
			Case CHAR_SON,CHAR_TAI,CHAR_KNU,CHAR_SHA,CHAR_SIL,CHAR_BLA:
				Return True
			Default:
				Return False
		End Select
	End Function

Function CharHasSuperModel(superform,char)
		If superform=0 Then
			Return 0
		Else
			If IsCharMod(char) Then
				If superform=1 And CharModHasSuper(char) Then
					Return 1
				ElseIf superform=2 And CharModHasSuper(char) Then
					Return 1
				Else
					Return 0
				EndIf
			Else
				If Player_HasSuperModel(char) Then
					Return superform
				Else
					Return 0
				EndIf
			EndIf
		EndIf
	End Function

Function ChangeCharacter(newcharacter)
		pp(1)\Psychokinesis=0
		If pp(1)\ObjPickUp=1 Then pp(1)\Action=ACTION_COMMON : pp(1)\ObjPickUp=0

		If Menu\Members>1 Then
			Player_ReassignMember(Game\NewLeader)
			Game\Leader=Game\NewLeader
			DeformCharacter_FixUps(pp(1))
		Else
			DeformCharacter_DeleteTheBoneEntities(pp(1))
			Player_DetermineChar(pp(1),newcharacter)
			DeformCharacter(pp(1),True)
			Game\Vehicle=0
			Player_SetRadius#(pp(1))
		EndIf

		For ppp.tPlayer = Each tPlayer
			If ppp\No#>0 Then ppp\JustDeformedCharacterTimer=0.5*secs#
		Next

		Player_PlayTurnVoice(pp(1))
		Delay(1)
	End Function

Function DeformCharacter_GetTheBoneEntities(p.tPlayer)
		Select p\Character
			Case CHAR_SHA,CHAR_STO:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetBL")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetBR")
				p\Objects\Jet3=FindChild(p\Objects\Mesh, "jetFL")
				p\Objects\Jet4=FindChild(p\Objects\Mesh, "jetFR")
			Case CHAR_OME,CHAR_MKN,CHAR_GME,CHAR_BEA:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetL")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetR")
			Case CHAR_EGR:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetL")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetR")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "backpack")
			Case CHAR_VEC:
				p\Objects\Gum=FindChild(p\Objects\Mesh, "gum")
				p\Objects\Extra=FindChild(p\Objects\Mesh, "tail1")
			Case CHAR_MET,CHAR_MT3:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet")
			Case CHAR_BIG,CHAR_RAY:
				p\Objects\Extra=FindChild(p\Objects\Mesh, "tail1")
			Case CHAR_TAI,CHAR_TDL:
				p\Objects\Extra=FindChild(p\Objects\Mesh, "tailroot")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "scanner")
			Case CHAR_HBO:
				p\Objects\Extra=FindChild(p\Objects\Mesh, "spine_b")
			Case CHAR_SHD:
				p\Objects\Extra=FindChild(p\Objects\Mesh, "head2")
			Case CHAR_GAM:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetBL")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetBR")
				p\Objects\Jet3=FindChild(p\Objects\Mesh, "jetFL")
				p\Objects\Jet4=FindChild(p\Objects\Mesh, "jetFR")
				p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "booster")
			Case CHAR_BET:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetBL")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetBR")
				p\Objects\Jet3=FindChild(p\Objects\Mesh, "jetFL")
				p\Objects\Jet4=FindChild(p\Objects\Mesh, "jetFR")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "booster")
			Case CHAR_EME:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetL")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetR")
				p\Objects\Extra=FindChild(p\Objects\Mesh, "lidU")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "lidD")
			Case CHAR_EGG:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet1L")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jet1R")
				p\Objects\Jet3=FindChild(p\Objects\Mesh, "jet2L")
				p\Objects\Jet4=FindChild(p\Objects\Mesh, "jet2R")
				p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "handX")
			Case CHAR_CHW:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet1L")
				p\Objects\Jet2=FindChild(p\Objects\Mesh, "jet1R")
				p\Objects\Jet3=FindChild(p\Objects\Mesh, "jet2L")
				p\Objects\Jet4=FindChild(p\Objects\Mesh, "jet2R")
				p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "office")
			Case CHAR_TMH:
				p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet")
				p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "handX")
			Case CHAR_AMY:
				p\Objects\Extra=FindChild(p\Objects\Mesh, "hammer2")
		End Select
		p\Objects\Head=FindChild(p\Objects\Mesh, "head")
		Select p\Character
			Case CHAR_TMH:
				p\Objects\HandR=FindChild(p\Objects\Mesh, "handR_t")
				p\Objects\HandL=FindChild(p\Objects\Mesh, "handL_t")
			Case CHAR_EGG:
				p\Objects\HandR=FindChild(p\Objects\Mesh, "handR_e")
				p\Objects\HandL=FindChild(p\Objects\Mesh, "handL_e")
			Default:
				p\Objects\HandR=FindChild(p\Objects\Mesh, "handR")
				p\Objects\HandL=FindChild(p\Objects\Mesh, "handL")
		End Select
		p\Objects\ArmR=FindChild(p\Objects\Mesh, "armR")
		p\Objects\ArmL=FindChild(p\Objects\Mesh, "armL")
		p\Objects\ToeR=FindChild(p\Objects\Mesh, "toeR")
		p\Objects\ToeL=FindChild(p\Objects\Mesh, "toeL")
		p\Objects\FootR=FindChild(p\Objects\Mesh, "footR")
		p\Objects\FootL=FindChild(p\Objects\Mesh, "footL")
		Select p\RealCharacter
			Case CHAR_GAM,CHAR_BET:
				p\Objects\LegR=FindChild(p\Objects\Mesh, "leg1R")
				p\Objects\LegL=FindChild(p\Objects\Mesh, "leg1L")
			Default:
				p\Objects\LegR=FindChild(p\Objects\Mesh, "legR")
				p\Objects\LegL=FindChild(p\Objects\Mesh, "legL")
		End Select
		p\Objects\HipR=FindChild(p\Objects\Mesh, "hipR")
		p\Objects\HipL=FindChild(p\Objects\Mesh, "hipL")
		p\Objects\Spine=FindChild(p\Objects\Mesh, "spine")
		p\Objects\Hips=FindChild(p\Objects\Mesh, "hips")
	End Function

Function DeformCharacter_DeleteTheBoneEntities(p.tPlayer)
		Select p\Character
			Case CHAR_SHA,CHAR_STO:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Jet2
				FreeEntity p\Objects\Jet3
				FreeEntity p\Objects\Jet4
			Case CHAR_OME,CHAR_MKN,CHAR_GME,CHAR_BEA:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Jet2
			Case CHAR_EGR:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Jet2
				FreeEntity p\Objects\Extra2
			Case CHAR_VEC:
				FreeEntity p\Objects\Gum
				FreeEntity p\Objects\Extra
			Case CHAR_MET,CHAR_MT3:
				FreeEntity p\Objects\Jet1
			Case CHAR_BIG,CHAR_HBO,CHAR_SHD,CHAR_AMY,CHAR_TDL,CHAR_RAY:
				FreeEntity p\Objects\Extra
			Case CHAR_TAI
				FreeEntity p\Objects\Extra
				FreeEntity p\Objects\Extra2
			Case CHAR_GAM,CHAR_EGG,CHAR_CHW:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Jet2
				FreeEntity p\Objects\Jet3
				FreeEntity p\Objects\Jet4
				FreeEntity p\Objects\Extra
				FreeEntity p\Objects\Extra2
			Case CHAR_BET:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Jet2
				FreeEntity p\Objects\Jet3
				FreeEntity p\Objects\Jet4
				FreeEntity p\Objects\Extra2
			Case CHAR_EME:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Jet2
				FreeEntity p\Objects\Extra
				FreeEntity p\Objects\Extra2
			Case CHAR_TMH:
				FreeEntity p\Objects\Jet1
				FreeEntity p\Objects\Extra
				FreeEntity p\Objects\Extra2
		End Select
		FreeEntity p\Objects\Head
		FreeEntity p\Objects\HandR
		FreeEntity p\Objects\HandL
		FreeEntity p\Objects\ArmR
		FreeEntity p\Objects\ArmL
		FreeEntity p\Objects\ToeR
		FreeEntity p\Objects\ToeL
		FreeEntity p\Objects\FootR
		FreeEntity p\Objects\FootL
		FreeEntity p\Objects\LegR
		FreeEntity p\Objects\LegL
		FreeEntity p\Objects\HipR
		FreeEntity p\Objects\HipL
		FreeEntity p\Objects\Spine
		FreeEntity p\Objects\Hips
	End Function

Function Player_UpdateBoneEntities(p.tPlayer)

	Select p\Character
		Case CHAR_SHA:
			Select p\Animation\Animation
				Case ANIMATION_RUN,ANIMATION_MACHRUN:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
						ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
						ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET,p\Objects\Jet3)
						ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET,p\Objects\Jet4)
					EndIf
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET,p\Objects\Jet3)
					ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET,p\Objects\Jet4)
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.25,0.2,2,p\Character,5)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.0,0.34,2,p\Character,1)
			End Select
		Case CHAR_OME:
			Select p\Animation\Animation
				Case ANIMATION_RUN:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
						ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					EndIf
				Case ANIMATION_GLIDE,ANIMATION_FLY,ANIMATION_GLIDE2:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
			End Select
		Case CHAR_EGR:
			Select p\Animation\Animation
				Case ANIMATION_RUN:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
						ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					EndIf
				Case ANIMATION_FLY,ANIMATION_SKYDIVEFAST,ANIMATION_ROLL,ANIMATION_DRIFTL,ANIMATION_DRIFTR
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
			End Select
		Case CHAR_MET,CHAR_MT3:
			Select p\Animation\Animation
				Case ANIMATION_RUN,ANIMATION_FLY:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					EndIf
			End Select
		Case CHAR_MKN:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,2,0.125,2,p\Character,1)
			End Select
	 	Case CHAR_KNU,CHAR_COM:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,2,0.125,2,p\Character,1)
			End Select
	 	Case CHAR_ROU:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.0,0.34,2,p\Character,2)
			End Select
	 	Case CHAR_MIG:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
			End Select
	 	Case CHAR_SON:
			Select p\Animation\Animation
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,3,p\Character,2)
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.25,0.2,2,p\Character,5)
			End Select
	 	Case CHAR_BAR:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,3,p\Character,2)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,3,p\Character,2)
				Case ANIMATION_GLIDE:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_SNOW,p\Objects\HandL)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_SNOW,p\Objects\HandR)
			End Select
		Case CHAR_STO:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET,p\Objects\Jet3)
					ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET,p\Objects\Jet4)
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,3,p\Character,2)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,3,p\Character,2)
				Case ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,3,p\Character,2)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,3,p\Character,2)
			End Select
	 	Case CHAR_TIK:
			Select p\Animation\Animation
				Case ANIMATION_FLY,ANIMATION_GLIDE,ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
			End Select
	 	Case CHAR_HON,CHAR_TIA:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.0,0.34,2,p\Character,2)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,2,p\Character,2)
			End Select
		Case CHAR_CHO:
			Select p\Animation\Animation
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.25,0.2,2,p\Character,5)
			End Select
		Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE,ANIMATION_THROWAIR:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet2)
					ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet3)
					ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet4)
			End Select
		Case CHAR_TMH:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE,ANIMATION_THROWAIR:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet1)
			End Select
		Case CHAR_EME,CHAR_GME:
			Select p\Animation\Animation
				Case ANIMATION_FLY,ANIMATION_GLIDE,ANIMATION_GLIDE2:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
				Case ANIMATION_KICK,ANIMATION_KICK2,ANIMATION_KICKAIR2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,3,p\Character,2)
				Case ANIMATION_PUNCH1,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
			End Select
		Case CHAR_SIL:
			Select p\Animation\Animation
				Case ANIMATION_THROW2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
			End Select
	 	Case CHAR_PRS:
			Select p\Animation\Animation
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,3,p\Character,2)
			End Select
	 	Case CHAR_ESP:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.55,0.2,2,p\Character,1)
			End Select
		Case CHAR_BEA:
			Select p\Animation\Animation
				Case ANIMATION_FLY:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
			End Select
		Case CHAR_INF:
			Select p\Animation\Animation
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
			End Select
	End Select

End Function

;______________________________________________________________________________________________________________________________________________________________________
;______________________________________________________________________________________________________________________________________________________________________

Function Player_Create_RingLoss(p.tPlayer)
	If p\CheeseShieldTimer>0 Then Return
		rings = Game\Gameplay\Rings
		If Menu\Stage>0 Then
			If rings > 30 Then rings = 30
		Else
			If rings > 10 Then rings = 10
		EndIf
			
		Repeat
			Object_SpewRing_Create.tObject(p\Objects\Position\x#, p\Objects\Position\y#+1.5, p\Objects\Position\z#, Rnd(-0.4, 0.4), Rnd(0.6, 1.2), Rnd(-0.4, 0.4))
			rings = rings - 1
		Until rings <= 0

		If Menu\Stage>0 Then
			Game\Gameplay\Rings = Game\Gameplay\Rings-30
		Else
			Game\Gameplay\Rings = Game\Gameplay\Rings-10
		EndIf
		If Game\Gameplay\Rings<0 Then Game\Gameplay\Rings=0
		Game\HurtWithoutShield = 0
		EmitSmartSound(Sound_RingLoss,p\Objects\Entity)
	End Function

Function Player_GainExtraLife(p.tPlayer)
		Game\Gameplay\Lives=Game\Gameplay\Lives+1
		Game\Gameplay\GainedLives=Game\Gameplay\GainedLives+1
		If Not(ChannelPlaying(Game\Channel_1Up)) Then Game\Channel_1Up=PlaySmartSound(Sound_1Up)
	End Function

;______________________________________________________________________________________________________________________________________________________________________
;______________________________________________________________________________________________________________________________________________________________________

Function Player_IsARobot(p.tPlayer)
	Select p\Character
		Case CHAR_OME,CHAR_HBO,CHAR_GAM,CHAR_EME,CHAR_MET,CHAR_TDL,CHAR_MKN,CHAR_BET,CHAR_MT3,CHAR_GME,CHAR_EGR:
			Return True
		Default:
			Return False
	End Select
End Function

;______________________________________________________________________________________________________________________________________________________________________
;______________________________________________________________________________________________________________________________________________________________________
;~IDEal Editor Parameters:
;~C#Blitz3D