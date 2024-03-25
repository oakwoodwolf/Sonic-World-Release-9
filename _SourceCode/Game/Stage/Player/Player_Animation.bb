
	; Animation constants
	Const ANIMATION_BIO		= 1
	Const ANIMATION_IDLE		= 2
	Const ANIMATION_WAIT		= 3
	Const ANIMATION_WALK			= 4
	Const ANIMATION_JOG			= 5
	Const ANIMATION_RUN		= 6
	Const ANIMATION_MACHRUN		= 7
	Const ANIMATION_SPIN	= 8
	Const ANIMATION_JUMP=9
	Const ANIMATION_CHARGE=10
	Const ANIMATION_ROLL=11
	Const ANIMATION_FALL		=12
	Const ANIMATION_FALLFAST			= 13
	Const ANIMATION_FORWARD		= 14
	Const ANIMATION_UP		= 15
	Const ANIMATION_FLOAT		= 16
	Const ANIMATION_BRAKE		= 17
	Const ANIMATION_HURT	= 18
	Const ANIMATION_DEAD			= 19
	Const ANIMATION_DEADFALL		=20
	Const ANIMATION_GRIND	= 21
	Const ANIMATION_GRINDSWITCH		= 22
	Const ANIMATION_GRINDFAST		= 23
	Const ANIMATION_SKYDIVE	= 24
	Const ANIMATION_SKYDIVEFAST	= 25
	Const ANIMATION_CARRYIDLE	= 26
	Const ANIMATION_CARRYWALK	= 27
	Const ANIMATION_CARRYJUMP		= 28
	Const ANIMATION_HOLD1		= 29
	Const ANIMATION_HOLD2		= 30
	Const ANIMATION_DRIFTL		= 31
	Const ANIMATION_DRIFTR	= 32
	Const ANIMATION_STOMP		= 33
	Const ANIMATION_LAND		= 34
	Const ANIMATION_TRANSFORM		= 35
	Const ANIMATION_TRICK		= 36
	Const ANIMATION_VEHICLE		= 37
	Const ANIMATION_BOARD	= 38
	Const ANIMATION_BOARDFALL		=39
	Const ANIMATION_BOARDL	= 40
	Const ANIMATION_BOARDR	= 41
	Const ANIMATION_VICTORY	= 42
	Const ANIMATION_VICTORYLOOP	= 43
	Const ANIMATION_FLY	= 44
	Const ANIMATION_POSTHOM	= 45
	Const ANIMATION_POSTHOM2		= 46
	Const ANIMATION_POSTHOM3	= 47
	Const ANIMATION_THROW		= 48
	Const ANIMATION_THROW2		= 49
	Const ANIMATION_THROWAIR	= 50
	Const ANIMATION_THROWAIR2	= 51
	Const ANIMATION_KICK		= 52
	Const ANIMATION_KICK2		= 53
	Const ANIMATION_KICK3		= 54
	Const ANIMATION_KICKAIR		= 55
	Const ANIMATION_KICKAIR2	= 56
	Const ANIMATION_PUNCH1		= 57
	Const ANIMATION_PUNCH2		= 58
	Const ANIMATION_PUNCH3		= 59
	Const ANIMATION_PUNCHAIR	= 60
	Const ANIMATION_PUNCHAIR2	= 61



;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------

Function Player_FrameCheck(p.tPlayer,frameno,tillend=False)
		If Not(p\UsedFrameTimer>0) Then
			If p\Frame=frameno Or (tillend And p\Frame>=frameno) Then
				p\UsedFrameTimer=0.05*secs#
				Return True
			Else
				Return False
			EndIf
		Else
			Return False
		EndIf
	End Function
	
; =========================================================================================================
; =========================================================================================================

Function Player_Animate(p.tPlayer, d.tDeltaTime)

	If Game\Interface\DebugPlacerOn=0 Then p\Frame = Int(AnimTime(p\Objects\Mesh))
	If Game\Interface\DebugPlacerOn=0 Then Player_UpdateBoneEntities(p)
	
	Player_AnimationHelpers(p)

		; Change animation depending on action
		Select p\Action
			Case ACTION_DEBUG
				If p\ObjType=0 Then p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_VICTORY
				If p\Motion\Ground Then 
					Select p\Animation\VictoryStage
						Case 0 : p\Animation\Animation = ANIMATION_VICTORY 
						Case 1 : p\Animation\Animation = ANIMATION_VICTORYLOOP
					End Select 
				Else 
					p\Animation\Animation = ANIMATION_SPIN
				EndIf 
			Case ACTION_COMMON,ACTION_BOOST
				If p\ForceShotWalkTimer>0 Then
					If (p\SpeedLength# > 2.35) Then
						p\Animation\Animation = ANIMATION_RUN
					Else
						p\Animation\Animation = ANIMATION_WALK
					EndIf
				Else
					If (p\SpeedLength# > 2.6) Then
						If Game\MachLock>0 Or p\Action=ACTION_BOOST Then
							p\Animation\Animation = ANIMATION_MACHRUN
						Else
							p\Animation\Animation = ANIMATION_RUN
						EndIf 
					ElseIf (p\SpeedLength# > 0.85) Then
						p\Animation\Animation = ANIMATION_JOG
					ElseIf (p\SpeedLength# > 0.00) Then
						p\Animation\Animation = ANIMATION_WALK
					Else
						Select p\Animation\IdleType
							Case 0 : p\Animation\Animation = ANIMATION_IDLE
							Case 1 : p\Animation\Animation = ANIMATION_WAIT
						End Select 
					EndIf
				EndIf
				If p\Flags\Skidding And p\Flags\Walking=False Then p\Animation\Animation = ANIMATION_BRAKE
			Case ACTION_HOP,ACTION_SINK
				p\Animation\Animation = ANIMATION_FALL
			Case ACTION_POSTHOM
				Select Player_CanHomingAttack(p)
					Case True : p\Animation\Animation = ANIMATION_POSTHOM
					Case False : p\Animation\Animation = ANIMATION_UP
				End Select 
				
			Case ACTION_TRICK
				p\Animation\Animation = ANIMATION_TRICK
			Case ACTION_LIGHTATTACK
				p\Animation\Animation = ANIMATION_FORWARD
			Case ACTION_PANEL2
				p\Animation\Animation = ANIMATION_HOLD2
			Case ACTION_PANEL
				p\Animation\Animation = ANIMATION_UP
			Case ACTION_BOOSTFALL
				p\Animation\Animation = ANIMATION_FORWARD
			Case ACTION_LAND,ACTION_JUMPDASH
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_JUMP
				If Player_CanCharSpin(p) Then
					p\Animation\Animation = ANIMATION_SPIN
				Else
					p\Animation\Animation = ANIMATION_JUMP
				EndIf 
					
			Case ACTION_FALL,ACTION_JUMPFALL,ACTION_FULLFALL
				p\Animation\Animation = ANIMATION_FALL
				If (p\SpeedLength# > 2.9) Then p\Animation\Animation = ANIMATION_FALLFAST
			Case ACTION_CHARGE
				If Player_CanCharSpin(p) Then
					p\Animation\Animation = ANIMATION_SPIN
				Else
					p\Animation\Animation = ANIMATION_CHARGE
				EndIf
			Case ACTION_ROLL
				If Player_CanCharSpin(p) Then
					p\Animation\Animation = ANIMATION_SPIN
				Else
					p\Animation\Animation = ANIMATION_ROLL
				EndIf
			Case ACTION_DRIFT
				Select p\DriftDirection
					Case -1 : p\Animation\Animation = ANIMATION_DRIFTL
					Case 1 : p\Animation\Animation = ANIMATION_DRIFTR
				End Select 
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
			Case ACTION_FWD,ACTION_LIGHTDASH
				p\Animation\Animation = ANIMATION_FORWARD
			Case ACTION_HOMING,ACTION_SPIRIT
				p\Animation\Animation = ANIMATION_SPIN
			Case ACTION_FLY,ACTION_LEVITATE,ACTION_BUOY,ACTION_SOARFLAP
				Select p\Character
					Case CHAR_TAI
						If p\FlyingSwipe=0 Then 
							p\Animation\Animation = ANIMATION_FLY
						Else
							p\Animation\Animation = ANIMATION_KICKAIR2
						EndIf 
					Default
						p\Animation\Animation = ANIMATION_FLY
				End Select 
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
				p\Animation\Animation = ANIMATION_STOMP
			Case ACTION_HURT
				p\Animation\Animation = ANIMATION_HURT
			Case ACTION_DIE,ACTION_RIVALDIE
				If p\Motion\Ground Then p\Animation\Animation = ANIMATION_DEAD Else p\Animation\Animation = ANIMATION_DEADFALL
			Case ACTION_FLOAT,ACTION_FREEZE
				p\Animation\Animation = ANIMATION_FLOAT
			Case ACTION_BOARD,ACTION_BOARDDRIFT,ACTION_BOARDGRIND
				If Input\Hold\Left Then 
					p\Animation\Animation=ANIMATION_BOARDL
				ElseIf Input\Hold\Right Then 
					p\Animation\Animation=ANIMATION_BOARDR
				Else
					p\Animation\Animation=ANIMATION_BOARD
				EndIf 
			Case ACTION_BOARDFALL,ACTION_BOARDTRICK,ACTION_BOARDJUMP
				p\Animation\Animation=ANIMATION_BOARDFALL
			Case ACTION_GRIND
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
							Case 1 : If p\Motion\Ground Then p\Animation\Animation = ANIMATION_THROW Else p\Animation\Animation = ANIMATION_THROWAIR
							Case 2 : If p\Motion\Ground Then p\Animation\Animation = ANIMATION_THROW2 Else p\Animation\Animation = ANIMATION_THROWAIR2
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
				Case CHAR_SHA: p\Animation\Animation = ANIMATION_THROWAIR
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
					Case CHAR_AMY,CHAR_WAV,CHAR_TAI: If p\Motion\Ground Then p\Animation\Animation = ANIMATION_KICK Else p\Animation\Animation = ANIMATION_KICKAIR
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
					Case CHAR_SON,CHAR_NAC,CHAR_PRS,CHAR_INF:
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
			Case ACTION_CARRYJUMP,ACTION_CARRYTHROWN
				p\Animation\Animation = ANIMATION_CARRYJUMP
			Case ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT
				p\Animation\Animation=ANIMATION_VEHICLE
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
				If p\BumpedCloudTimer>0 Then p\Animation\Animation = ANIMATION_SPIN Else p\Animation\Animation = ANIMATION_HOLD1
			Case ACTION_SKYDIVE
				If Input\Hold\ActionRoll=True Then 
					p\Animation\Animation = ANIMATION_SKYDIVEFAST
				Else
					p\Animation\Animation = ANIMATION_SKYDIVE
				EndIf 
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
				Case ANIMATION_IDLE:
					Animate(p\Objects\Mesh, 1,0.25, p\Animation\Animation, 10)
				Case ANIMATION_WAIT:
					Animate(p\Objects\Mesh, 3, 0.35, p\Animation\Animation, 10)
				Case ANIMATION_VICTORY:
					Animate(p\Objects\Mesh, 3, 0.5, p\Animation\Animation, 10)
				Case ANIMATION_VICTORYLOOP:
					Animate(p\Objects\Mesh, 1, 0.1, p\Animation\Animation, 10)
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
				Case ANIMATION_RUN,ANIMATION_MACHRUN:
					p\Animation\Speed# = (p\SpeedLength#/5.25)*d\Delta
					Select p\RealCharacter
						Case CHAR_SHA: p\Animation\Speed# = p\Animation\Speed#*0.7189866045428072
								If p\Animation\Speed#<0.4938 Then p\Animation\Speed#=0.4938
								If p\Animation\Speed#>0.9415300705299941 Then p\Animation\Speed#=0.9415300705299941
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
							Animate(p\Objects\Mesh, 1, 2.1 , p\Animation\Animation, 1)
						Case ACTION_HOMING:
							Animate(p\Objects\Mesh, 1, 1.01, p\Animation\Animation, 10)
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
				Case ANIMATION_TRICK:
					Animate(p\Objects\Mesh, 3, 0.425, p\Animation\Animation, 10)
				Case ANIMATION_DEAD:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_DEADFALL:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_SKYDIVE,ANIMATION_SKYDIVEFAST:
					Animate(p\Objects\Mesh, 1, 0.35, p\Animation\Animation, 10)
				Case ANIMATION_DRIFTL,ANIMATION_DRIFTR:
					Animate(p\Objects\Mesh, 1, 0.85, p\Animation\Animation, 10)
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
						Default: Animate(p\Objects\Mesh, 1, 0.625, p\Animation\Animation, 10)
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
				Case ANIMATION_STOMP:
					Animate(p\Objects\Mesh, 1, 0.9, p\Animation\Animation, 10)
				Case ANIMATION_JUMP
					Animate(p\Objects\Mesh, 3, 0.36, p\Animation\Animation, 10)
					
				Case ANIMATION_CLIMB:
					p\Animation\Speed# = (p\SpeedLength#/3.0)*d\Delta
					If p\Animation\Speed#<0.45 Then p\Animation\Speed#=0.45
					If p\Animation\Speed#>0.8 Then p\Animation\Speed#=0.8
					Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				Case ANIMATION_GRIND,ANIMATION_VEHICLE,ANIMATION_BOARD,ANIMATION_BOARDR,ANIMATION_BOARDL,ANIMATION_BOARDFALL:
					Animate(p\Objects\Mesh, 1, 0.4420, p\Animation\Animation, 10)
				Case ANIMATION_CHARGE:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				Case ANIMATION_ROLL:
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
					
				Case ANIMATION_POSTHOM
					Select(Rand(1,3))
						Case 1:	Animate(p\Objects\Mesh, 3, 0.4, ANIMATION_POSTHOM, 10)
						Case 2:	Animate(p\Objects\Mesh, 3, 0.4, ANIMATION_POSTHOM2, 10)
						Case 3:	Animate(p\Objects\Mesh, 3, 0.4, ANIMATION_POSTHOM3, 10)
					End Select 
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
						Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						Case CHAR_TAI: Animate(p\Objects\Mesh, 3, 0.2, p\Animation\Animation, 10)
						Case CHAR_BLA,CHAR_MAR: Animate(p\Objects\Mesh, 3, 0.69, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_EGR: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_NAC,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_MKN,CHAR_GME: Animate(p\Objects\Mesh, 3, 1.2038, p\Animation\Animation, 10)
						Case CHAR_TIA,CHAR_SHD: Animate(p\Objects\Mesh, 1, 0.69, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1.125, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_THROW2:
					Select p\Character
						Case CHAR_AMY,CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						Case CHAR_MAR: Animate(p\Objects\Mesh, 3, 0.9675, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1.125, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_THROWAIR
					Select p\Character
						Case CHAR_AMY,CHAR_ESP,CHAR_SHD,CHAR_EME,CHAR_GME: Animate(p\Objects\Mesh, 3, 0.45, p\Animation\Animation, 10)
						Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH,CHAR_EGR: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_NAC,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_MET,CHAR_MT3: Animate(p\Objects\Mesh, 1, 0.425, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.2, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_THROWAIR2:
					Select p\Character
						Case CHAR_AMY,CHAR_ESP,CHAR_SHD,CHAR_EME,CHAR_GME: Animate(p\Objects\Mesh, 3, 0.45, p\Animation\Animation, 10)
						Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH,CHAR_EGR: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_NAC,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_MET,CHAR_MT3: Animate(p\Objects\Mesh, 1, 0.425, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.2, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICK:
					Select p\Character
						Case CHAR_RAY,CHAR_TDL,CHAR_AMY,CHAR_WAV:
							If p\Animation\PreviousAnimation=ANIMATION_KICKAIR Then
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 0)
							Else
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
							EndIf
						Case CHAR_TAI: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
							
						Case CHAR_KNU,CHAR_ROU,CHAR_MKN,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.48, p\Animation\Animation, 10)
						Case CHAR_BLA,CHAR_MAR: Animate(p\Objects\Mesh, 1, 0.6, p\Animation\Animation, 10)
						Case CHAR_BEA: Animate(p\Objects\Mesh, 1, 0.84, p\Animation\Animation, 10)
						Case CHAR_CHO: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICKAIR:
					Select p\Character
						Case CHAR_SON,CHAR_MIG,CHAR_EME,CHAR_PRS,CHAR_INF: Animate(p\Objects\Mesh, 1, 0.35, p\Animation\Animation, 10)
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
						Case CHAR_TAI: Animate(p\Objects\Mesh, 1, 0.25, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_KICKAIR2:
					Select p\Character
						Case CHAR_TAI: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCH1:
					Select p\Character
						Case CHAR_KNU,CHAR_ROU,CHAR_MIG,CHAR_TIK,CHAR_BAR,CHAR_JET,CHAR_STO,CHAR_HON,CHAR_SHD,CHAR_HBO,CHAR_EME,CHAR_MKN,CHAR_TIA,CHAR_GME,CHAR_COM,CHAR_ESP,CHAR_AMY: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_SHA: Animate(p\Objects\Mesh, 3, 0.65, p\Animation\Animation, 10)
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCH2:
					Select p\Character
						Case CHAR_KNU,CHAR_ROU,CHAR_MIG,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HON,CHAR_SHD,CHAR_HBO,CHAR_EME,CHAR_MKN,CHAR_TIA,CHAR_GME,CHAR_COM,CHAR_ESP: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_GAM,CHAR_BET: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
						Case CHAR_SHA: Animate(p\Objects\Mesh, 3, 0.65, p\Animation\Animation, 10)	
						Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
					End Select
				Case ANIMATION_PUNCH3:
					Select p\Character
						Case CHAR_KNU,CHAR_CHO,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HBO,CHAR_MKN,CHAR_EGG,CHAR_COM,CHAR_TMH: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
						Case CHAR_OME,CHAR_VEC: Animate(p\Objects\Mesh, 1, 0.72, p\Animation\Animation, 10)
						Case CHAR_SHA: Animate(p\Objects\Mesh, 3, 0.65, p\Animation\Animation, 10)	
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
		End Select
		
		; Update normals
		UpdateNormals(p\Objects\Mesh)
	End Function
; =========================================================================================================
; =========================================================================================================
Function Player_VictoryAnimationStuff(p.tPlayer)
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
End Function 
Function Player_AnimationHelpers(p.tPlayer)
If Not (p\Animation\Animation = ANIMATION_IDLE Or p\Animation\Animation = ANIMATION_WAIT) Then
p\Animation\IdleType = 0
p\Animation\IdleCount =0
EndIf 

If p\Animation\Animation=ANIMATION_ROLL Then
	Select p\Character
		Case CHAR_EGR
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
	End Select 
	
ElseIf p\Animation\Animation=ANIMATION_SKYDIVEFAST Then
	Select p\Character
		Case CHAR_EGR
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundHover,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
	End Select 
ElseIf p\Animation\Animation = ANIMATION_IDLE Then
		If Player_FrameCheck(p,16) And p\Animation\IdleType = 0 Then 
		p\Animation\IdleCount=p\Animation\IdleCount+1
	EndIf 
	
	If p\Animation\IdleCount=7 And p\Animation\IdleType = 0 Then p\Animation\IdleType=1 : p\Animation\IdleCount=0 : Player_PlayIdleVoice(p)
	
ElseIf p\Animation\Animation = ANIMATION_WAIT Then
		If Player_FrameCheck(p,65) Then 
			p\Animation\IdleType = 0
			p\Animation\IdleCount =0
		EndIf 
ElseIf p\Animation\Animation=ANIMATION_VICTORY Then
	If Player_FrameCheck(p,135) Then p\Animation\VictoryStage=1
	Player_VictoryAnimationStuff(p)

;Step sound
ElseIf p\Animation\Animation = ANIMATION_WALK Or (p\Action=ACTION_CARRY And p\Animation\Animation = ANIMATION_CARRYWALK) Then
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
ElseIf p\Animation\Animation = ANIMATION_RUN Or p\Animation\Animation=ANIMATION_DRIFTL Or p\Animation\Animation=ANIMATION_DRIFTR Or p\Animation\Animation=ANIMATION_MACHRUN  And (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
	Select p\RealCharacter
		Case CHAR_SHA:
			If (Not(p\Animation\Animation=ANIMATION_DRIFTL Or p\Animation\Animation=ANIMATION_DRIFTR)) Then
				Player_PlayRandomStep(p,2,18)
			EndIf
		Case CHAR_CHA,CHAR_MPH,CHAR_INF:
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_TAI:
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTails,p\Objects\Entity)
			Player_NonStepWaterSplash(p,3,11)
		Case CHAR_TDL:
			If Not(ChannelPlaying(p\Channel_GroundFly)) Then p\Channel_GroundFly=EmitSmartSound(Sound_GroundFlyTailsDoll,p\Objects\Entity)
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
EndIf

;Shut up ground fly
If (Not(p\Animation\Animation=ANIMATION_RUN Or p\Animation\Animation=ANIMATION_MACHRUN Or p\Animation\Animation=ANIMATION_DRIFTL Or p\Animation\Animation=ANIMATION_DRIFTR Or p\Animation\Animation=ANIMATION_ROLL Or p\Animation\Animation=ANIMATION_SKYDIVEFAST)) Then
	StopChannel(p\Channel_GroundFly)
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
			If Player_FrameCheck(p,2) And ChannelPlaying(p\Channel_Fly)=False Then p\Channel_Fly=EmitSmartSound(Sound_FlyWings,p\Objects\Entity)
	End Select
Else
	StopChannel(p\Channel_Fly)
EndIf


;Gliding sound
If p\Action=ACTION_GLIDE Or p\Action=ACTION_FLUTTER Or p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Or (p\Action=ACTION_SLOWGLIDE And p\Character=CHAR_TIA) Then
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
If (p\Action=ACTION_CLIMB And p\SpeedLength#>0) Then
	If ChannelPlaying(p\Channel_Climb)=False Then p\Channel_Climb=EmitSmartSound(Sound_Climb,p\Objects\Entity)
ElseIf (p\Action=ACTION_SKYDIVE) Then
	If ChannelPlaying(p\Channel_SkydiveFast)=False Then p\Channel_SkydiveFast=EmitSmartSound(Sound_SkydiveFast,p\Objects\Entity)
	If ChannelPlaying(p\Channel_Skydive)=False Then p\Channel_Skydive=EmitSmartSound(Sound_Skydive,p\Objects\Entity)
	If Input\Hold\ActionRoll Then 
		ChannelVolume(p\Channel_Skydive,1)
		ChannelVolume(p\Channel_SkydiveFast,1)
	Else
		ChannelVolume(p\Channel_Skydive,1)
		ChannelVolume(p\Channel_SkydiveFast,0)	
	EndIf 
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
	StopChannel(p\Channel_Skydive)
	StopChannel(p\Channel_SkydiveFast)
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