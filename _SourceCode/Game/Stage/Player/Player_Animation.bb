i=1
Global ANIMATION_IDLE		= i : i=i+1
Global ANIMATION_WAIT		= i : i=i+1
Global ANIMATION_WALK		= i : i=i+1
Global ANIMATION_JOG			= i : i=i+1
Global ANIMATION_RUN			= i : i=i+1
Global ANIMATION_MACHRUN			= i : i=i+1
Global ANIMATION_SPIN		= i : i=i+1
Global ANIMATION_JUMP		= i : i=i+1
Global ANIMATION_CHARGE		= i : i=i+1
Global ANIMATION_ROLL		= i : i=i+1
Global ANIMATION_FALL		= i : i=i+1
Global ANIMATION_FALLFAST		= i : i=i+1
Global ANIMATION_FORWARD			= i : i=i+1
Global ANIMATION_UP			= i : i=i+1
Global ANIMATION_FLOAT			= i : i=i+1
Global ANIMATION_BRAKE			= i : i=i+1
Global ANIMATION_HURT			= i : i=i+1
Global ANIMATION_DEAD				= i : i=i+1
Global ANIMATION_DEADFALL			= i : i=i+1
Global ANIMATION_GRIND			= i : i=i+1
Global ANIMATION_GRINDSWITCH				= i : i=i+1
Global ANIMATION_GRINDFAST			= i : i=i+1
Global ANIMATION_SKYDIVE		= i : i=i+1
Global ANIMATION_SKYDIVEFAST		= i : i=i+1
Global ANIMATION_CARRYIDLE		= i : i=i+1
Global ANIMATION_CARRYWALK		= i : i=i+1
Global ANIMATION_CARRYJUMP			= i : i=i+1
Global ANIMATION_HOLD1		= i : i=i+1
Global ANIMATION_HOLD2			= i : i=i+1
Global ANIMATION_DRIFTL		= i : i=i+1
Global ANIMATION_DRIFTR		= i : i=i+1
Global ANIMATION_STOMP			= i : i=i+1
Global ANIMATION_LAND			= i : i=i+1
Global ANIMATION_TRANSFORM				= i : i=i+1
Global ANIMATION_TRICK			= i : i=i+1
Global ANIMATION_VEHICLE				= i : i=i+1
Global ANIMATION_BOARD			= i : i=i+1
Global ANIMATION_BOARDFALL			= i : i=i+1
Global ANIMATION_BOARDL		= i : i=i+1
Global ANIMATION_BOARDR		= i : i=i+1
Global ANIMATION_VICTORY			= i : i=i+1
Global ANIMATION_VICTORYLOOP			= i : i=i+1
Global ANIMATION_FLY			= i : i=i+1
Global ANIMATION_GLIDE		= i : i=i+1
Global ANIMATION_CLIMB		= i : i=i+1
Global ANIMATION_CLIMBIDLE		= i : i=i+1
Global ANIMATION_POSTHOM			= i : i=i+1
Global ANIMATION_POSTHOM2			= i : i=i+1
Global ANIMATION_POSTHOM3			= i : i=i+1
Global ANIMATION_THROW				= i : i=i+1
Global ANIMATION_THROW2			= i : i=i+1
Global ANIMATION_THROWAIR			= i : i=i+1
Global ANIMATION_THROWAIR2			= i : i=i+1
Global ANIMATION_KICK				= i : i=i+1
Global ANIMATION_KICK2				= i : i=i+1
Global ANIMATION_KICK3			= i : i=i+1
Global ANIMATION_KICKAIR				= i : i=i+1
Global ANIMATION_KICKAIR2			= i : i=i+1
Global ANIMATION_PUNCH1			= i : i=i+1
Global ANIMATION_PUNCH2				= i : i=i+1
Global ANIMATION_PUNCH3				= i : i=i+1
Global ANIMATION_PUNCHAIR			= i : i=i+1
Global ANIMATION_PUNCHAIR2			= i : i=i+1

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

;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------------------------------
Function Player_Animate(p.tPlayer, d.tDeltaTime)
	
	If Game\Interface\DebugPlacerOn=0 Then p\Frame = Int(AnimTime(p\Objects\Mesh))
	
	Player_AnimationSounds(p)
	
	If Game\Interface\DebugPlacerOn=0 Then Player_UpdateBoneEntities(p)
	
	Select p\Action
		Case ACTION_DEBUG
			If p\ObjType=0 Then 
				p\Animation\Animation = ANIMATION_FALL
				If (p\SpeedLength# > 0) Then p\Animation\Animation = ANIMATION_FALLFAST
			EndIf 
		Case ACTION_VICTORY
			If p\Motion\Ground Then 
				Select p\Animation\VictoryStage
					Case 0 : p\Animation\Animation = ANIMATION_VICTORY 
					Case 1 : p\Animation\Animation = ANIMATION_VICTORYLOOP
				End Select 
			Else 
				p\Animation\Animation = ANIMATION_FALL
			EndIf 
			
		Case ACTION_COMMON
			If p\PsychokinesisTimer>0 Then 
				If p\Motion\Ground=True Then p\Animation\Animation=ANIMATION_PUNCH2 Else p\Animation\Animation=ANIMATION_PUNCHAIR2
				
			ElseIf p\QuickstepTimer>0 Then
				p\Animation\Animation=ANIMATION_DRIFTL+p\QuickstepDir
			ElseIf p\DashpadSpinTimer>0 Then
				p\Animation\Animation=ANIMATION_SPIN
			ElseIf p\TrailBlazer=1 Then
				p\Animation\Animation=ANIMATION_KICK
			ElseIf Game\CheeseTimer>0 Then
				
				p\Animation\Animation=ANIMATION_THROW+p\CheeseMode
			ElseIf (p\SpeedLength# > 2.85) Then
				If Game\MachLock>0 Or p\Flags\Boosting Or (Game\SpeedShoeTimer>0 And p\SpeedLength#>5.5) Then
					p\Animation\Animation = ANIMATION_MACHRUN
				Else
					p\Animation\Animation = ANIMATION_RUN
				EndIf 
			ElseIf (p\SpeedLength# > 1.4) Then
				p\Animation\Animation = ANIMATION_JOG
			ElseIf (p\SpeedLength# > 0.00) Then
				p\Animation\Animation = ANIMATION_WALK
			Else
				Select p\Animation\IdleType
					Case 0 : p\Animation\Animation = ANIMATION_IDLE
					Case 1 : p\Animation\Animation = ANIMATION_WAIT
				End Select 
			EndIf
			
			If p\Flags\Skidding And p\Flags\Walking=False Then p\Animation\Animation = ANIMATION_BRAKE
		Case ACTION_HOP,ACTION_SINK
			p\Animation\Animation = ANIMATION_FALL
		Case ACTION_TRICK
			p\Animation\Animation=ANIMATION_TRICK
			
		Case ACTION_PANEL2
			p\Animation\Animation = ANIMATION_HOLD2
		Case ACTION_PANEL
			p\Animation\Animation = ANIMATION_FORWARD
			
		Case ACTION_JUMPDASH
			Select p\Character
				Case CHAR_MET
					p\Animation\Animation=ANIMATION_FLY
				Default
					p\Animation\Animation = ANIMATION_SPIN
			End Select
		Case ACTION_LAND
			p\Animation\Animation = ANIMATION_LAND
			
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
		Case ACTION_SCREWKICK
			p\Animation\Animation = ANIMATION_KICKAIR2
		Case ACTION_SPREAD
			p\Animation\Animation = ANIMATION_PUNCHAIR2
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
		Case ACTION_DEMODASH
			If p\Motion\Ground=True Then 
				p\Animation\Animation = ANIMATION_KICK2
			Else
				p\Animation\Animation = ANIMATION_STOMP
			EndIf
		Case ACTION_UP,ACTION_DOUBLEJUMPS
			p\Animation\Animation = ANIMATION_UP
		Case ACTION_TRANSFORM
			p\Animation\Animation = ANIMATION_TRANSFORM
		Case ACTION_DOUBLEJUMP
			
			If IsCharMod(p\RealCharacter) Then
				p\Animation\Animation = ANIMATION_UP
			Else
				Select p\Character
					Case CHAR_AMY
						If p\DoubleJump=1 Then
							p\Animation\Animation = ANIMATION_PUNCHAIR2
						Else
							p\Animation\Animation = ANIMATION_FLY
						EndIf
					Case CHAR_BLA
						p\Animation\Animation = ANIMATION_FLY
					Case CHAR_MIG
						p\Animation\Animation=ANIMATION_SPIN
						
					Default: p\Animation\Animation = ANIMATION_UP
				End Select
			EndIf
			
			
		Case ACTION_FWD,ACTION_LIGHTDASH,ACTION_LIGHTATTACK
			p\Animation\Animation = ANIMATION_FORWARD
		Case ACTION_HOMING
			p\Animation\Animation = ANIMATION_SPIN
		Case ACTION_LEVITATE
			p\Animation\Animation = ANIMATION_FLY
		Case ACTION_BUOY
			p\Animation\Animation = ANIMATION_KICK
		Case ACTION_FLY,ACTION_SOARFLAP
			Select p\Character
				Case CHAR_RAY
					
					If p\Motion\Speed\y#>1 Or p\SoarState=1 Then p\Animation\Animation=ANIMATION_FLY Else p\Animation\Animation=ANIMATION_GLIDE
					
					
				Default
					If p\JumpDashTimer>0 Then
						p\Animation\Animation = ANIMATION_MACHRUN
					Else
						p\Animation\Animation = ANIMATION_FLY
					EndIf
			End Select
		Case ACTION_SHOOTHOVER
			Select p\Character
				Case CHAR_EGR: p\Animation\Animation = ANIMATION_THROWAIR
				Case CHAR_GAM,CHAR_TAI: p\Animation\Animation = ANIMATION_THROWAIR2
				Default: p\Animation\Animation = ANIMATION_FLY
			End Select
		Case ACTION_HOVER
			
			If p\ForceShotWalkTimer>0 Then 
				p\Animation\Animation=ANIMATION_THROWAIR
			Else
				p\Animation\Animation = ANIMATION_FLY
			EndIf
		Case ACTION_GLIDE
			Select p\Character
					
				Case CHAR_OME,CHAR_GAM:
					p\Animation\Animation = ANIMATION_FLY
				Case CHAR_ESP
					p\Animation\Animation=ANIMATION_FORWARD
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
			Select p\Character
				Case CHAR_SON
					If Input\Hold\ActionRoll Then
						p\Animation\Animation = ANIMATION_SPIN
					Else	
						p\Animation\Animation = ANIMATION_STOMP
					EndIf 	
				Default
					p\Animation\Animation = ANIMATION_STOMP
			End Select 
		Case ACTION_HURT,ACTION_DIEHURT
			p\Animation\Animation = ANIMATION_HURT
		Case ACTION_DIE,ACTION_RIVALDIE
			If p\Motion\Ground Then p\Animation\Animation = ANIMATION_DEAD Else p\Animation\Animation = ANIMATION_DEADFALL
		Case ACTION_FLOAT
			p\Animation\Animation = ANIMATION_FLOAT
		Case ACTION_FREEZE
			Animate(p\Objects\Mesh, 1, 0, p\Animation\PreviousAnimation, 10)
		Case ACTION_BOARD,ACTION_BOARDDRIFT
			If Game\Vehicle=5 Or Game\Vehicle=8 Then
				p\Animation\Animation=ANIMATION_CARRYIDLE
			Else
				If Input\Hold\Left Then 
					p\Animation\Animation=ANIMATION_BOARDL
				ElseIf Input\Hold\Right Then 
					p\Animation\Animation=ANIMATION_BOARDR
				Else
					p\Animation\Animation=ANIMATION_BOARD
				EndIf 
			EndIf 
		Case ACTION_BOARDFALL,ACTION_BOARDTRICK,ACTION_BOARDJUMP
			If Game\Vehicle=5 Or Game\Vehicle=8 Then
				p\Animation\Animation=ANIMATION_CARRYIDLE
			Else
				p\Animation\Animation=ANIMATION_BOARDFALL
			EndIf
		Case ACTION_GRIND
			If Game\Vehicle=5 Or Game\Vehicle=8 Then
				p\Animation\Animation = ANIMATION_CARRYIDLE
			Else
				If p\GrindTurnTimer>0 Then
					p\Animation\Animation = ANIMATION_GRINDSWITCH
				Else
					Select p\GrindTurn
						Case 1: p\Animation\Animation = ANIMATION_GRIND
						Case 2: p\Animation\Animation = ANIMATION_GRINDFAST
					End Select
				EndIf
			EndIf
		Case ACTION_POSTHOM
			Select Player_CanHomingAttack(p)
				Case True : p\Animation\Animation = ANIMATION_POSTHOM
				Case False : p\Animation\Animation = ANIMATION_UP
			End Select 
		Case ACTION_BLAZETRICK
			p\Animation\Animation = ANIMATION_POSTHOM
		Case ACTION_CLIMB
			If (p\SpeedLength# > 0.1) Then p\Animation\Animation = ANIMATION_CLIMB Else p\Animation\Animation = ANIMATION_CLIMBIDLE
			If p\Character=CHAR_ESP Or p\Character=CHAR_MIG Then p\Animation\Animation=ANIMATION_HOLD2
			If p\WalldashTimer>0 Then p\Animation\Animation=ANIMATION_SPIN
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
				Case CHAR_NAC,CHAR_COM,CHAR_ESP: If p\Motion\Ground Then p\Animation\Animation = ANIMATION_THROW Else p\Animation\Animation = ANIMATION_THROWAIR
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
				Case CHAR_SHA: p\Animation\Animation = ANIMATION_THROWAIR
				Case CHAR_EGR
					Select p\ShootType
						Case 1
							If p\Motion\Ground Then
								p\Animation\Animation = ANIMATION_THROW
							Else
								p\Animation\Animation = ANIMATION_THROWAIR
							EndIf
						Case 2
							p\Animation\Animation = ANIMATION_THROW2
					End Select
				Case CHAR_GAM,CHAR_BET:
					If p\Motion\Ground Then
						If p\SpeedLength#>2.35 Then p\Animation\Animation = ANIMATION_THROW2 Else p\Animation\Animation = ANIMATION_THROW
					Else
						p\Animation\Animation = ANIMATION_THROWAIR
					EndIf
				Case CHAR_OME
					Select p\ShootType
						Case 1
							p\Animation\Animation=ANIMATION_THROWAIR
						Case 2
							If p\Motion\Ground=True Then
								If p\SpeedLength#>1.5 Then
									p\Animation\Animation=ANIMATION_KICK3
								ElseIf p\SpeedLength#>0 Then
									p\Animation\Animation=ANIMATION_KICK2
								Else
									p\Animation\Animation=ANIMATION_KICK
								EndIf
							Else
								p\Animation\Animation=ANIMATION_KICKAIR
							EndIf
						Case 3
							p\Animation\Animation=ANIMATION_THROW
					End Select
				Case CHAR_INF
					p\Animation\Animation = ANIMATION_THROW
				Case CHAR_SHN
					If p\Motion\Ground Then
						p\Animation\Animation = ANIMATION_THROW+p\ShootType-1
					Else
						Select p\ShootType
							Case 1
								p\Animation\Animation=ANIMATION_THROWAIR
							Case 3
								p\Animation\Animation=ANIMATION_THROWAIR2
						End Select
					EndIf
				Case CHAR_SIL
					If p\Motion\Ground=True Then p\Animation\Animation=ANIMATION_PUNCH1 Else p\Animation\Animation=ANIMATION_PUNCHAIR
				Default:
					If p\Motion\Ground Then
						p\Animation\Animation = ANIMATION_THROW
					Else
						p\Animation\Animation = ANIMATION_THROWAIR
					EndIf
			End Select
		Case ACTION_UPPERCUT
			If p\Motion\Ground=True Then 
				p\Animation\Animation = ANIMATION_KICK
			Else
				p\Animation\Animation = ANIMATION_KICKAIR
			EndIf 
		Case ACTION_SWIPE
			
			If p\Character=CHAR_BLA Then p\Animation\Animation=ANIMATION_KICK2 Else p\Animation\Animation = ANIMATION_KICK
		Case ACTION_SPRINT
			Select p\Character
				Case CHAR_MIG,CHAR_SIL: p\Animation\Animation = ANIMATION_KICK
				Case CHAR_INF: p\Animation\Animation = ANIMATION_FORWARD
				Case CHAR_MET: p\Animation\Animation = ANIMATION_PUNCHAIR
				Case CHAR_BLA: p\Animation\Animation = ANIMATION_KICKAIR
				Default: p\Animation\Animation = ANIMATION_KICKAIR
			End Select
		Case ACTION_PUNCH
			Select p\Character
				Case CHAR_SON,CHAR_NAC,CHAR_PRS,CHAR_INF:
					p\Animation\Animation = ANIMATION_KICK
				Case CHAR_SIL,CHAR_MPH,CHAR_MT3:
					p\Animation\Animation = ANIMATION_FORWARD
				Case CHAR_EME,CHAR_GME:
					Select p\CharacterMode
						Case CHAR_SON,CHAR_ESP: p\Animation\Animation = ANIMATION_KICK
						Default: p\Animation\Animation = ANIMATION_PUNCH1+p\PunchNumber-1
					End Select
				Case CHAR_EGG
					p\Animation\Animation = ANIMATION_PUNCH3
				Case CHAR_AMY
					If p\Motion\Ground=True Then
						p\Animation\Animation = ANIMATION_PUNCH1
					Else
						p\Animation\Animation = ANIMATION_PUNCHAIR
					EndIf
				Case CHAR_MET
					If p\PunchNumber=3 Or p\PunchNumber=4 Then
						p\Animation\Animation = ANIMATION_PUNCH3
					Else
						p\Animation\Animation = ANIMATION_PUNCH1+p\PunchNumber-1
					EndIf
					
				Default:
					p\Animation\Animation = ANIMATION_PUNCH1+p\PunchNumber-1
			End Select
		Case ACTION_THRUST
			Select p\Character
				Case CHAR_TAI
					p\Animation\Animation = ANIMATION_KICKAIR
				Default
					p\Animation\Animation = ANIMATION_PUNCHAIR
			End Select
			
			
		Case ACTION_PSYCHO
			Select p\Character
				Case CHAR_SIL:
					Select p\PsychoType
						Case 1
							If p\Motion\Ground=True Then p\Animation\Animation = ANIMATION_PUNCH1 Else p\Animation\Animation = ANIMATION_PUNCHAIR
						Case 2
							If p\Motion\Ground=True Then p\Animation\Animation = ANIMATION_PUNCH2 Else p\Animation\Animation = ANIMATION_PUNCHAIR2
					End Select
					
				Default:
					p\Animation\Animation = ANIMATION_THROW
			End Select
		Case ACTION_GATLING:
			
			p\Animation\Animation = ANIMATION_THROW
			
			
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
			p\Animation\Animation = ANIMATION_VEHICLE
			
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
			If p\Hurt2Timer>0 Then
				p\Animation\Animation = ANIMATION_FLOAT
			Else
				If Input\Hold\ActionRoll=True Then 
					p\Animation\Animation = ANIMATION_SKYDIVEFAST
				Else
					p\Animation\Animation = ANIMATION_SKYDIVE
				EndIf 
			EndIf
		Case ACTION_BELLYFLOP
			Select p\Character
				Case CHAR_CHO: p\Animation\Animation = ANIMATION_KICKAIR
				Default: p\Animation\Animation = ANIMATION_PUNCHAIR2
			End Select
		Case ACTION_ANIMTEST
			p\Animation\Animation = p\Animation\AnimTestAnim
			
	End Select
	
	If p\Flags\DropDashing=True Then p\Animation\Animation=ANIMATION_SPIN
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
		; If the animation changed, animate new
	If (p\Animation\Animation<>p\Animation\PreviousAnimation) Then
		Select p\Animation\Animation
			Case ANIMATION_IDLE:
				Animate(p\Objects\Mesh, 1,0.25, p\Animation\Animation, 10)
			Case ANIMATION_LAND:
				Animate(p\Objects\Mesh, 3,0.675, p\Animation\Animation, 10)
			Case ANIMATION_POSTHOM
				Animate(p\Objects\Mesh, 3, 0.5, ANIMATION_CLIMBIDLE+Rand(1,3), 10)
			Case ANIMATION_WAIT:
				Animate(p\Objects\Mesh, 3, 0.35, p\Animation\Animation, 10)
			Case ANIMATION_VICTORY:
				
				Animate(p\Objects\Mesh, 3, 0.45,p\Animation\Animation)
			Case ANIMATION_VICTORYLOOP:
				Animate(p\Objects\Mesh, 1, 0.16, p\Animation\Animation, 10)
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
					Case CHAR_SHA,CHAR_SHN: p\Animation\Speed# = p\Animation\Speed#*0.7189866045428072
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
							If p\Animation\Speed#<0.8 Then p\Animation\Speed#=0.8
							If p\Animation\Speed#>1.3095238 Then p\Animation\Speed#=1.3095238
						EndIf
				End Select
				Select p\Animation\Animation
					Case ANIMATION_RUN: Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
					Case ANIMATION_MACHRUN: Animate(p\Objects\Mesh, 1, p\Animation\Speed#*1.4, p\Animation\Animation, 10)
				End Select
				
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
				If p\GoDestination=True Then
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation)
				Else
					Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				EndIf 
			Case ANIMATION_UP,ANIMATION_TRANSFORM:
				Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
			Case ANIMATION_FLOAT:
				Animate(p\Objects\Mesh, 1, 0.4, p\Animation\Animation, 10)
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
				Select p\Character
					Case CHAR_MET
						Animate(p\Objects\Mesh, 1, 0.6, p\Animation\Animation, 10)
					Default
						Animate(p\Objects\Mesh, 1, 0.85, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_FLY:
				Select p\Character
					Case CHAR_CRE: Animate(p\Objects\Mesh, 1, 0.7/2.3, p\Animation\Animation, 10)
					Case CHAR_SIL,CHAR_INF: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_AMY: Animate(p\Objects\Mesh, 1, 0.45, p\Animation\Animation, 10)
					Case CHAR_RAY,CHAR_GME: Animate(p\Objects\Mesh, 3, 0.7, p\Animation\Animation, 10)
					Case CHAR_ROU,CHAR_EGR: Animate(p\Objects\Mesh, 1, 0.3/1.3, p\Animation\Animation, 10)
					Case CHAR_BLA:	Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_RAY
						If p\RayAnimTimer>0 Then
							Animate(p\Objects\Mesh, 1, 0.1, p\Animation\Animation, 10)
						Else
							Animate(p\Objects\Mesh, 1, 0.625, p\Animation\Animation, 10)
						EndIf
					Default: Animate(p\Objects\Mesh, 1, 0.625, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_GLIDE:
				Select p\Character
					Case CHAR_KNU,CHAR_TIK,CHAR_SHD,CHAR_EME,CHAR_COM: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
						
					Case CHAR_OME,CHAR_HBO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_MAR: Animate(p\Objects\Mesh, 1, 1.4, p\Animation\Animation, 10)
					Case CHAR_HON: Animate(p\Objects\Mesh, 1, 0.84, p\Animation\Animation, 10)
					Case CHAR_JET: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
					Case CHAR_RAY
						If p\RayAnimTimer>0 Then
							Animate(p\Objects\Mesh, 1, 0.05, p\Animation\Animation, 10)
						Else
							Animate(p\Objects\Mesh, 1, 0.3/1.15, p\Animation\Animation, 10)
						EndIf
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
				If p\Animation\Speed#>1.3 Then p\Animation\Speed#=1.3
				
				Animate(p\Objects\Mesh, 1, p\Animation\Speed#, p\Animation\Animation, 10)
				
			Case ANIMATION_GRIND,ANIMATION_VEHICLE,ANIMATION_BOARD,ANIMATION_BOARDR,ANIMATION_BOARDL,ANIMATION_BOARDFALL:
				Animate(p\Objects\Mesh, 1, 0.4420, p\Animation\Animation, 10)
			Case ANIMATION_GRINDSWITCH
				Select p\GrindTurn
					Case 1 : Animate(p\Objects\Mesh, 1, -0.3, p\Animation\Animation, 10)
					Case 2 : Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				End Select 
			Case ANIMATION_CHARGE:
				Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
			Case ANIMATION_ROLL:
				Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
				
			Case ANIMATION_POSTHOM
				Select p\Character
					Case CHAR_BLA: Animate(p\Objects\Mesh, 3, 0.6, p\Animation\Animation, 10)
					Default: 
						Select Rand(1,3)
							Case 1: Animate(p\Objects\Mesh, 3, 0.4, ANIMATION_POSTHOM, 10)
							Case 2: Animate(p\Objects\Mesh, 3, 0.4, ANIMATION_POSTHOM2, 10)
							Case 3: Animate(p\Objects\Mesh, 3, 0.4, ANIMATION_POSTHOM3, 10)
						End Select
				End Select
				
			Case ANIMATION_POSTHOM2
				Select p\Character
					Case CHAR_BLA: Animate(p\Objects\Mesh, 3, 0.6, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.4, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_POSTHOM3
				Select p\Character
					Case CHAR_BLA: Animate(p\Objects\Mesh, 3, 0.6, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.4, p\Animation\Animation, 10)
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
					Case CHAR_GAM: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					Case CHAR_OME: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.5, p\Animation\Animation, 10)
				End Select
				
				
			Case ANIMATION_THROW2:
				Select p\Character
					Case CHAR_SHN: Animate(p\Objects\Mesh, 1, 0.45, p\Animation\Animation, 10)
					Case CHAR_MAR: Animate(p\Objects\Mesh, 3, 0.9675, p\Animation\Animation, 10)
						
					Default: Animate(p\Objects\Mesh, 3, 0.5, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_THROWAIR
				Select p\Character
					Case CHAR_AMY,CHAR_ESP,CHAR_EGR,CHAR_SHN: Animate(p\Objects\Mesh, 3, 0.45, p\Animation\Animation, 10)
					Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
					Case CHAR_GAM: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_SHA: Animate(p\Objects\Mesh, 3, 0.6, p\Animation\Animation, 10)
					Case CHAR_MET,CHAR_MT3: Animate(p\Objects\Mesh, 1, 0.425, p\Animation\Animation, 10)
					Case CHAR_TAI: Animate(p\Objects\Mesh, 3, 0.4, p\Animation\Animation, 10)
					Case CHAR_ESP: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
					Case CHAR_OME:Animate(p\Objects\Mesh, 3, 0.55, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.2, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_THROWAIR2:
				Select p\Character
					Case CHAR_SHN
						
						Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
						
					Case CHAR_AMY,CHAR_ESP,CHAR_EME,CHAR_GME,CHAR_TAI: Animate(p\Objects\Mesh, 3, 0.45, p\Animation\Animation, 10)
					Case CHAR_CRE: Animate(p\Objects\Mesh, 3, 0.3, p\Animation\Animation, 10)
					Case CHAR_OME,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_EGR: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
					Case CHAR_NAC,CHAR_COM: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
					Case CHAR_MET,CHAR_MT3: Animate(p\Objects\Mesh, 1, 0.425, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.2, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_KICK:
				Select p\Character
					Case CHAR_TAI,CHAR_MIG,CHAR_GAM: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_KNU: Animate(p\Objects\Mesh, 1, 0.48, p\Animation\Animation, 10)
					Case CHAR_BLA,CHAR_MAR: Animate(p\Objects\Mesh, 1, 0.6, p\Animation\Animation, 10)
					Case CHAR_BEA: Animate(p\Objects\Mesh, 1, 0.84, p\Animation\Animation, 10)
					Case CHAR_CHO: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
					Case CHAR_OME
						Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_KICKAIR:
				Select p\Character
					Case CHAR_SON,CHAR_MIG,CHAR_EME,CHAR_PRS,CHAR_INF,CHAR_ROU: Animate(p\Objects\Mesh, 1, 0.35, p\Animation\Animation, 10)
					Case CHAR_OME: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_AMY,CHAR_WAV:
						If p\Animation\PreviousAnimation=ANIMATION_KICK Then
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 0)
						Else
							Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
						EndIf
					Case CHAR_ESP,CHAR_CHA,CHAR_MET,CHAR_MKN,CHAR_MT3,CHAR_GME: Animate(p\Objects\Mesh, 1, 1.5, p\Animation\Animation, 10)
					Case CHAR_NAC,CHAR_BEA: Animate(p\Objects\Mesh, 3, 0.98, p\Animation\Animation, 10)
					Case CHAR_JET: Animate(p\Objects\Mesh, 1, 0.3, p\Animation\Animation, 10)
					Case CHAR_KNU: Animate(p\Objects\Mesh, 3, 0.5, p\Animation\Animation, 10)
					Case CHAR_CHO: Animate(p\Objects\Mesh, 1, 0.15, p\Animation\Animation, 10)
					Case CHAR_TAI: Animate(p\Objects\Mesh, 3, 0.4, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_KICKAIR2:
				Select p\Character
					Case CHAR_TAI: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_ROU: Animate(p\Objects\Mesh, 3, 0.7, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_PUNCH1:
				Select p\Character
					Case CHAR_KNU,CHAR_ROU: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
					Case CHAR_AMY: Animate(p\Objects\Mesh, 3, 0.7, p\Animation\Animation, 10)
					Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
					Case CHAR_SHA,CHAR_SHN: Animate(p\Objects\Mesh, 3, 0.65, p\Animation\Animation, 10)
					Case CHAR_BLA: Animate(p\Objects\Mesh, 1, 0.51, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_PUNCH2:
				Select p\Character
					Case CHAR_KNU,CHAR_ROU,CHAR_MIG,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HON,CHAR_SHD,CHAR_HBO,CHAR_EME,CHAR_MKN,CHAR_TIA,CHAR_GME,CHAR_COM,CHAR_ESP: Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 0)
					Case CHAR_GAM,CHAR_BET: Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
					Case CHAR_SHA,CHAR_SHN: Animate(p\Objects\Mesh, 3, 0.65, p\Animation\Animation, 10)	
					Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_PUNCH3:
				Select p\Character
					Case CHAR_KNU,CHAR_CHO,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HBO,CHAR_MKN,CHAR_EGG,CHAR_COM Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
					Case CHAR_SHA,CHAR_SHN: Animate(p\Objects\Mesh, 3, 0.65, p\Animation\Animation, 10)	
					Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_PUNCHAIR:
				Select p\Character
					Case CHAR_KNU,CHAR_ROU,CHAR_SHN Animate(p\Objects\Mesh, 3, 0.72, p\Animation\Animation, 10)
					Case CHAR_AMY: Animate(p\Objects\Mesh, 3, 0.5, p\Animation\Animation, 10)
					Case CHAR_VEC: Animate(p\Objects\Mesh, 3, 0.35, p\Animation\Animation, 10)
					Case CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW Animate(p\Objects\Mesh, 3, 3.4, p\Animation\Animation, 10)
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
					Case CHAR_OME: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Case CHAR_KNU: Animate(p\Objects\Mesh, 1, 0.48, p\Animation\Animation, 10)
					Case CHAR_BLA
						p\Animation\Speed# = (p\SpeedLength#/5.25)*d\Delta
						If p\Animation\Speed#<0.8 Then p\Animation\Speed#=0.8
						If p\Animation\Speed#>1.3095238 Then p\Animation\Speed#=1.3095238
						Animate(p\Objects\Mesh, 1, p\Animation\Speed#*0.8, p\Animation\Animation, 10)
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
					Case CHAR_OME: Animate(p\Objects\Mesh, 1, 0.5, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 1, p\Animation\Animation, 10)
				End Select
			Case ANIMATION_PUNCHAIR2:
				Select p\Character
					Case CHAR_AMY: Animate(p\Objects\Mesh, 1, 0.3/1.15, p\Animation\Animation, 10)
					Case CHAR_ROU: Animate(p\Objects\Mesh, 3, 0.4, p\Animation\Animation, 10)
					Case CHAR_MET: Animate(p\Objects\Mesh, 1, 0.3/1.15, p\Animation\Animation, 10)
					Default: Animate(p\Objects\Mesh, 3, 0.51, p\Animation\Animation, 10)
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
		Case ANIMATION_RUN,ANIMATION_MACHRUN:
			p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/5.25)*d\Delta
			If Not(p\Animation\SpeedChangeBlockTimer>0) Then
				Select p\RealCharacter
					Case CHAR_SHA: SetAnimTime(p\Objects\Mesh, p\Animation\Speed#*0.7189866045428072, p\Animation\Animation)
					Case CHAR_CRE: SetAnimTime(p\Objects\Mesh, p\Animation\Speed#*0.8594933022714036, p\Animation\Animation)
					Default: SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, p\Animation\Animation)
				End Select
			EndIf
		Case ANIMATION_CLIMB:
			If p\UnderwaterFeet=0 Then
				p\Animation\Speed# = p\Animation\Speed# + (p\SpeedLength#/3.0)*d\Delta
				If Not(p\Animation\SpeedChangeBlockTimer>0) Then SetAnimTime(p\Objects\Mesh, p\Animation\Speed#, p\Animation\Animation)
			EndIf
	End Select
	
		; Update normals
	UpdateNormals(p\Objects\Mesh)
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D