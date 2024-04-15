
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Const PLAYER_MODE_MOUSELOOK		= 0
	Const PLAYER_MODE_ANALOG		= 1
	Const PLAYER_MODE_SRB			= 2
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---- Player management ----
	; =========================================================================================================
	; =========================================================================================================
	Function Player_Handle(p.tPlayer, d.tDeltaTime)

	; Run physics
	Player_Physics(p,d)

	; Run follow code
	If p\No#>1 Then
		Player_Follow(p)
		If EntityDistance(p\Objects\Entity,cam\Entity)<10 Then
			EntityAlpha(p\Objects\Mesh,0)
		Else
			EntityAlpha(p\Objects\Mesh,1)
		EndIf
	Else
		EntityAlpha(p\Objects\Mesh,1)
	EndIf

	; Change leader
	If p\No#=1 and Game\Interface\DebugPlacerOn=0 Then
		If p\MateChangeTimer>0 Then
			If EntityDistance(p\Objects\Entity,pp(1)\Objects\FollowerPlace[p\No#-1])>2.5+p\SpeedLength# Then
				If p\Motion\Ground and Menu\MissionMach=0 and Game\SpeedShoes=0 and abs(p\Rotation#)<30 Then
					If Sqr#( abs(p\Objects\Position\x#-EntityX(pp(1)\Objects\FollowerPlace[p\No#-1]))^2+abs(p\Objects\Position\z#-EntityZ(pp(1)\Objects\FollowerPlace[p\No#-1]))^2 )>5 Then
						p\Animation\Direction#=DeltaYaw#(p\Objects\Entity,pp(1)\Objects\FollowerPlace[p\No#-1])-180
						If pp(1)\SpeedLength#<0.5 Then
							Player_SetSpeed(p,0.5)
						Else
							Player_SetSpeed(p,pp(1)\SpeedLength#)
						EndIf
					Else
						p\Animation\Direction#=EntityYaw(pp(1)\Objects\FollowerPlace[p\No#-1])-180
					EndIf
				Else
					EntityType(p\Objects\Entity, COLLISION_NONE)
					PositionEntity p\Objects\Entity, EntityX(pp(1)\Objects\FollowerPlace[p\No#-1]), EntityY(pp(1)\Objects\FollowerPlace[p\No#-1])+1.5, EntityZ(pp(1)\Objects\FollowerPlace[p\No#-1]), 1
					PositionEntity p\Objects\Mesh, EntityX(pp(1)\Objects\FollowerPlace[p\No#-1]), EntityY(pp(1)\Objects\FollowerPlace[p\No#-1])+1.5, EntityZ(pp(1)\Objects\FollowerPlace[p\No#-1]), 1
					EntityType(p\Objects\Entity, COLLISION_PLAYER)
					p\Animation\Direction#=EntityYaw(pp(1)\Objects\FollowerPlace[p\No#-1])-180
					p\MateChangeTimer=0 : p\JustChangedMateTimer=0.25*secs#
				EndIf
			Else
				p\Animation\Direction#=EntityYaw(pp(1)\Objects\FollowerPlace[p\No#-1])-180
				p\MateChangeTimer=0 : p\JustChangedMateTimer=0.25*secs#
			EndIf
		EndIf

		If p\Underwater=0 and (Not(p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_BUMPED Or p\Action=ACTION_HOLD Or p\Action=ACTION_CARRY Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_CARRYTHROWN Or p\Action=ACTION_CLIMB Or p\HasVehicle=2)) Then p\Flags\MayChangeCharacter=True Else p\Flags\MayChangeCharacter=False

		If Input\Pressed\Change and Menu\Members>1 and (Not(p\JustChangedMateTimer>0)) Then
			If p\Flags\MayChangeCharacter Then
				Select Menu\Members
					Case 2:
						Select Game\Leader
							Case 1: Game\NewLeader=2
							Case 2: Game\NewLeader=1
						End Select
					Case 3:
						Select Game\Leader
							Case 1: Game\NewLeader=2
							Case 2: Game\NewLeader=3
							Case 3: Game\NewLeader=1
						End Select
				End Select
			Else
				If p\Motion\Ground Then PlaySmartSound(Sound_MenuRefuse)
			EndIf
			Input\Pressed\Change=False
		EndIf
		If Game\Leader<>Game\NewLeader Or (p\NewCharacter>0 And p\NewCharacter<>p\RealCharacter) Then
			If p\ForceBeingAbleToChangeLeaderTimer>0 Or (p\Motion\Ground and (Not(p\JustLandedTimer>0)) and (Not(p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_STOMP Or p\Action=ACTION_TORNADO))) Then ChangeCharacter(p\NewCharacter)
		EndIf
	EndIf

	; Run timers
	Player_HandleTimers(p)

	; Run extra management
	Player_ExtraHandle(p,d)

	; Run homing code
	Player_HomingAttack(p,d)

	; Run ring dash code
	Player_RingDash(p,d)

	; Run pick up code
	If Menu\ChaoGarden=0 Or Menu\Stage=999 Then Player_PickUp(p)

	; Calculate speed length and rotation
	p\SpeedLength#=Sqr#(p\Motion\Speed\x#^2+p\Motion\Speed\z#^2)
	p\Rotation# = Sqr#(EntityPitch(p\Objects\Mesh)^2+EntityRoll(p\Objects\Mesh)^2)

	; Check if player is underwater
	If p\Objects\Position\y# < Game\Stage\Properties\WaterLevel+5 Or p\UnderwaterTriggerTimer>0 Then
		If p\UnderwaterTriggerTimer>0 Or Game\Interface\DebugPlacerOn=1 Then
			p\Underwater=1
			p\UnderwaterFeet=1
		Else
			If EntityY(p\Objects\Head,1) < Game\Stage\Properties\WaterLevel Then p\Underwater=1 Else p\Underwater=0
			If EntityY(p\Objects\FootR,1) < Game\Stage\Properties\WaterLevel Or EntityY(p\Objects\FootL,1) < Game\Stage\Properties\WaterLevel Then p\UnderwaterFeet=1 Else p\UnderwaterFeet=0
		EndIf
		If Game\Interface\DebugPlacerOn=0 And (p\Objects\Position\y# < Game\Stage\Properties\WaterLevel) Then
			Select Game\Stage\Properties\WaterType
				Case 2,4: Player_TouchDie(p)
				Case 6,7:
					If p\No#=1 Then
						If (Not(p\Action=ACTION_SINK Or p\Action=ACTION_DIE)) Then
							p\Action=ACTION_SINK
							EmitSmartSound(Sound_Paddle2,p\Objects\Entity)
							Player_DieCamera(p,1)
						EndIf
						If p\Objects\Position\y# < Game\Stage\Properties\WaterLevel-10 Then Player_TouchDie(p)
					EndIf
			End Select
		EndIf
	Else
		p\Underwater=0
		p\UnderwaterFeet=0
	EndIf

	; Can stomp any time in air
	If p\Motion\Ground=False Then
		If p\No#=1 Or pp(1)\Flags\CanStomp Then
			Select p\Action
				Case ACTION_DEBUG,ACTION_CHAORACE,ACTION_VICTORY,ACTION_VICTORYHOLD,ACTION_STOMP,ACTION_HURT,ACTION_DIE,ACTION_GRABBED,ACTION_CANNON,ACTION_CANNON2,ACTION_FLOAT,ACTION_CARRYJUMP,ACTION_CARRYTHROWN,ACTION_SPIRIT,ACTION_HOLD,ACTION_BOARD,ACTION_BOARDJUMP,ACTION_BOARDFALL,ACTION_BOARDTRICK,ACTION_TRANSFORM,ACTION_SKYDIVE,ACTION_GLIDER,ACTION_FREEZE,ACTION_SINK,ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT,ACTION_BELLYFLOP,ACTION_TORNADO:
					p\Flags\CanStomp=False
				Case ACTION_BUMPED:
					If p\BumpedCloudTimer>0 Then p\Flags\CanStomp=True Else p\Flags\CanStomp=False
				Default:
					p\Flags\CanStomp=True
			End Select
		Else
			p\Flags\CanStomp=False
		EndIf
	Else
		p\Flags\CanStomp=False
	EndIf
	If p\Flags\CanStomp and Player_IsPlayable(p) Then
		If Input\Pressed\ActionRoll Then Player_Action_Stomp_Initiate(p)
		If Input\Pressed\ActionDrift Then Player_Action_Stomp_Initiate(p,true)
	EndIf

	; Can hyper blast
	If p\Action=ACTION_COMMON Or p\Flags\CanStomp Then
		Player_HyperBlast(p)
	EndIf

	; Can drift any time on ground
	If (Not(p\Action=ACTION_DEBUG Or p\Action=ACTION_DRIFT Or p\Action=ACTION_GRIND Or p\Action=ACTION_DIE Or p\Action=ACTION_HURT Or p\Action=ACTION_BUMPED Or p\HasVehicle>0 Or p\Action=ACTION_FREEZE Or p\Action=ACTION_SINK)) and p\Motion\Ground and (p\Flags\CanClimb=False) Then
		If Player_IsPlayable(p) and Input\Pressed\ActionDrift Then Player_Action_Drift_Initiate(p)
	EndIf

	; Some skills performable anytime
	If p\No#=1 and Player_IsPlayable(p) and Menu\ChaoGarden=0 Then
	If Menu\Stage>0 and (Not(p\Action=ACTION_DEBUG Or Game\Victory<>0 Or p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_GRABBED Or p\Action=ACTION_HOLD Or p\Action=ACTION_TRANSFORM Or p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_BOARDFALL Or p\Action=ACTION_BOARDTRICK Or p\Action=ACTION_SKYDIVE Or p\Action=ACTION_GLIDER Or p\Action=ACTION_FREEZE Or p\Action=ACTION_SINK Or p\Action=ACTION_CAR Or p\Action=ACTION_CARFALL Or p\Action=ACTION_CARDRIFT Or p\Action=ACTION_TORNADO)) and p\ObjPickUp=0 Then
		If Input\Pressed\ActionSkill1 and (Not(p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT)) Then
			Select p\Character
				Case CHAR_SIL:
					Player_Action_Psycho_Initiate(p)
				Case CHAR_GAM,CHAR_EGG,CHAR_CHW,CHAR_TMH:
					Player_Action_Shoot_AimBegin(p)
			End Select
		EndIf
		If Input\Pressed\ActionSkill2 Then
			Select p\Character
				Case CHAR_TIK:
					Player_Action_Spirit_Initiate(p)
			End Select
		EndIf
		If Input\Pressed\ActionSkill3 Then
			Select p\Character
				Case CHAR_ESP,CHAR_SHD,CHAR_EME,CHAR_AMY,CHAR_GME:
					Player_Action_Turn_Initiate(p)
				Case CHAR_INF:
					Player_Action_Psycho_Initiate(p)
				Case CHAR_SON:
					If p\Motion\Ground=True Then
					Player_Action_SonicBoom_Initiate(p)
					End If
			End Select
		EndIf
		If Input\Pressed\ActionAct Then
			If Player_CanLightDash(p\Character) and (Not(Game\Interface\ControlTipPickUpTimer>0)) Then p\LightDashRequestTimer=0.1*secs#
		EndIf
	EndIf
	EndIf

	; Determine if in jump action
	Select p\Action
		Case ACTION_JUMPDASH,ACTION_FLY,ACTION_GLIDE,ACTION_DOUBLEJUMP,ACTION_LEVITATE,ACTION_HOVER,ACTION_SLOWGLIDE,ACTION_FLUTTER,ACTION_SOAR,ACTION_SOARFLAP,ACTION_DIVE,ACTION_SLEET,ACTION_BUOY:
			p\Flags\InJumpAction=True
		Default:
			p\Flags\InJumpAction=False
			If p\No#=1 Then
				If Menu\Members>1 Then
					If pp(2)\Flags\InJumpAction Then pp(2)\Action=ACTION_FALL
				EndIf
				If Menu\Members>2
					If pp(3)\Flags\InJumpAction Then pp(3)\Action=ACTION_FALL
				EndIf
			EndIf
	End Select

	; Determine if cant attack chao
	If (p\Action=ACTION_JUMP Or p\Action=ACTION_HOP) Then
		p\Flags\CantAttackChao=True
	Else
		p\Flags\CantAttackChao=False
	EndIf

	; Determine if attacking
	If p\Flags\StronglyAttacking Or p\Action=ACTION_JUMP Or p\Action=ACTION_HOP Or p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT Or p\Action=ACTION_HOMING Or p\Action=ACTION_BUMPED Or (p\Character=CHAR_TRI And p\Action=ACTION_DOUBLEJUMP) Then
		p\Flags\Attacking=True
		If Not(p\Flags\TargeterTimer>0) Then p\Flags\Targeter=0
	Else
		p\Flags\Attacking=False
		p\Flags\Targeter=0
	EndIf

	; Determine if attacking strongly
	If p\Action=ACTION_STOMP Or (p\Action=ACTION_GLIDE and Menu\ChaoGarden=0) Or p\Action=ACTION_INSTASHIELD Or p\Action=ACTION_STARDASH Or p\Action=ACTION_FIREDASH Or p\Action=ACTION_SPRINT Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST Or p\Action=ACTION_SWIPE Or p\Action=ACTION_UPPERCUT Or p\Action=ACTION_CLAW Or (p\Action=ACTION_THROW and (p\Character=CHAR_KNU Or p\Character=CHAR_HBO Or p\Character=CHAR_STO Or p\Character=CHAR_MET Or p\Character=CHAR_MT3)) Or (p\Action=ACTION_BOARD and (p\GrindTurn=2 Or p\SpeedLength#>1)) Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_BOARDFALL Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_BOARDTRICK Or p\Action=ACTION_GLIDER Or ((p\Action=ACTION_CAR Or p\Action=ACTION_CARFALL Or p\Action=ACTION_CARDRIFT) and (p\SpeedLength#>1 Or p\Motion\Speed\y#>1)) Or p\Action=ACTION_BELLYFLOP Or p\Action=ACTION_TORNADO Then
		p\Flags\StronglyAttacking=True
	Else
		p\Flags\StronglyAttacking=False
	EndIf

	; Determine if doing air attack
	If (p\Action=ACTION_GLIDE and Menu\ChaoGarden=0) Or p\Action=ACTION_INSTASHIELD Or p\Action=ACTION_STARDASH Or p\Action=ACTION_FIREDASH Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST Or p\Action=ACTION_SWIPE Or p\Action=ACTION_SPRINT Or p\Action=ACTION_CLAW Or p\Action=ACTION_UPPERCUT Then
		p\Flags\InAirAttack=True
	Else
		p\Flags\InAirAttack=False
	EndIf

	; Determine if doing targeter attack
	Select p\Action
		Case ACTION_PUNCH,ACTION_THRUST,ACTION_SPRINT,ACTION_UPPERCUT,ACTION_SWIPE,ACTION_CLAW:
			p\Flags\InTargeterAttack=True
		Default:
			p\Flags\InTargeterAttack=False
	End Select

	; Determine if doing targeter air attack
	Select p\Action
		Case ACTION_THRUST,ACTION_STARDASH,ACTION_SPRINT,ACTION_UPPERCUT,ACTION_SWIPE,ACTION_CLAW:
			If p\Motion\Ground=False Then p\Flags\InTargeterAirAttack=True Else p\Flags\InTargeterAirAttack=False
		Default:
			p\Flags\InTargeterAirAttack=False
	End Select

	; Determine if doing spin jump attack
	If p\Action=ACTION_JUMP Or p\Action=ACTION_HOP Or (p\Character=CHAR_TRI And p\Action=ACTION_DOUBLEJUMP) Then
		p\Flags\InJumpAttack=True
	Else
		p\Flags\InJumpAttack=False
	EndIf

	; Determine if stomping
	If p\Action=ACTION_STOMP Then
		p\Flags\Stomping=1
	Else
		p\Flags\Stomping=0
	EndIf

	; Go super
	If p\No#=1 Then
		If p\Action=ACTION_JUMPFALL Or p\Action=ACTION_JUMP Or p\Action=ACTION_HOP Then p\Flags\CanSuperTransform=True Else p\Flags\CanSuperTransform=False
		If Input\Pressed\ActionAct Then
			If Menu\Stage>0 and UNLOCKEDEMERALDS[7]=1 And (Game\SuperForm<2 and Player_IsPlayable(p)) And Game\Gameplay\Rings>=50+50*Game\SuperForm And p\Flags\CanSuperTransform And (Not(Game\Interface\ControlTipPickUpTimer>0)) Then
				For ppp.tPlayer = Each tPlayer
					If Player_IsPlayable(ppp) Then
						ppp\Action=ACTION_TRANSFORM
						ppp\JumpMayRiseTimer=2*secs#
					EndIf
				Next
				Create_Emerald.tEmerald(p)
			EndIf
		EndIf
	EndIf

	; Be super
	If (Game\SuperForm>0 and Player_IsPlayable(p)) Then Game\Invinc=1 : Game\SpeedShoes=1

	;Change jump action mode
	If p\No#=1 and Input\Pressed\Back Then
		If Len(CONTROLTIPS$(TIP_JUMPA2[p\Character]))>0 Then PlaySmartSound(Sound_MenuMove)
		p\JumpActionMode=abs(p\JumpActionMode-1)
		JUMPAMODE[p\RealCharacter]=p\JumpActionMode
	EndIf

	; Control tips
	If p\No#=1 Then Interface_ControlTipUpdate(p\Action)

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		; Movement
		If Player_IsPlayable(p) Then Player_Movement(p,d)

		; If the character's on the ground, apply deceleration based on the current slope, and
		; check if he has not enough speed to go further.
		If p\CanClimbTimer>0 and p\AirBegTooFar=False and (p\Action=ACTION_CLIMB Or (p\Action=ACTION_GLIDE and (Not(p\Character=CHAR_OME))) Or (p\Action=ACTION_SPRINT and Player_CanWallKick(p)) Or (p\Action=ACTION_DOUBLEJUMP and p\Character=CHAR_TRI)) Then
			p\Flags\CanClimb=True
			p\Physics\MOTION_GROUND# = -1
		Else
			p\Flags\CanClimb=False
			If p\Motion\Ground Then Player_HandleAngleAcceleration(p, d)
			p\Physics\MOTION_GROUND# = 0.8 ; 0.65
		EndIf
		
		; However, decelerate if no acceleration exists.
		If (p\Motion\SpeedLength#>0.0) Then
			If (p\Motion\Speed\x#>0.0) Then
				p\Motion\Speed\x# = Max#(p\Motion\Speed\x#-(p\Motion\Speed\x#/p\Motion\SpeedLength#)*p\Physics\COMMON_XZDECELERATION#*d\Delta#, 0.0)
			Else
				p\Motion\Speed\x# = Min#(p\Motion\Speed\x#-(p\Motion\Speed\x#/p\Motion\SpeedLength#)*p\Physics\COMMON_XZDECELERATION#*d\Delta#, 0.0)
			End If
			If (p\Motion\Speed\z#>0.0) Then
				p\Motion\Speed\z# = Max#(p\Motion\Speed\z#-(p\Motion\Speed\z#/p\Motion\SpeedLength#)*p\Physics\COMMON_XZDECELERATION#*d\Delta#, 0.0)
			Else
				p\Motion\Speed\z# = Min#(p\Motion\Speed\z#-(p\Motion\Speed\z#/p\Motion\SpeedLength#)*p\Physics\COMMON_XZDECELERATION#*d\Delta#, 0.0)
			End If 
		End If		

		; Manage Y speeds
		If p\Motion\Ground=False Then
			p\Motion\Speed\y# = Max(p\Motion\Speed\y#-(p\Physics\COMMON_YACCELERATION#*d\Delta), p\Physics\COMMON_YTOPSPEED#)
		Else
			p\Motion\Speed\y# = 0
		End If

	End Function

Function Player_Movement(p.tPlayer, d.tDeltaTime)
		; Depending on current mode, the pressed direction uses a different method. Mouselook and analog are
		; quite similar in this aspect.
		If p\Objects\Camera\Lock\PosTimer>0 Then
			p\Motion\Direction# = EntityYaw#(p\Objects\Camera\Entity)-Input\Movement_Direction#
		Else
			p\Motion\Direction# = p\Objects\Camera\Rotation\y#-Input\Movement_Direction#
		EndIf
		p\Motion\Pressure#  = Input\Movement_Pressure#
		
		; Declarate acceleration and speed vectors and setup.
		p\Motion\Acceleration		= Vector(Cos#(p\Motion\Direction#)*p\Motion\Pressure#, 0, Sin#(p\Motion\Direction#)*p\Motion\Pressure#)
		p\Motion\PlayerSpeed 		= Vector(p\Motion\Speed\x#, 0, p\Motion\Speed\z#)
		p\Motion\SpeedNormalX# 		= ((p\Motion\Acceleration\x#+p\Motion\PlayerSpeed\x#)/2.0)*d\Delta
		p\Motion\SpeedNormalZ# 		= ((p\Motion\Acceleration\z#+p\Motion\PlayerSpeed\z#)/2.0)*d\Delta
		p\Motion\SpeedNormal		= Vector(p\Motion\SpeedNormalX#, 0, p\Motion\SpeedNormalZ#)
		p\Motion\SpeedCompensation	= Vector(0, 0, 0)
		p\Motion\SpeedLength#		= Vector_Length#(p\Motion\PlayerSpeed)
		
		; Disable skidding flag
		p\Flags\Skidding = False

		; If there exists acceleration, handle the acceleration and change to new
		; direction, preserving the momentum in the needed cases.
		If (Vector_Length#(p\Motion\Acceleration)) Then

			; Calculate delta cos and sin
			p\Motion\DeltaCos# = Cos#(p\Animation\Direction#-90)
			p\Motion\DeltaSin# = Sin#(p\Animation\Direction#-90)
			
			; Change Player's direction. Depending on current motion orientation and speed, this
			; direction change would be done instantly or smoothly. This rotation isn't done entirely
			; based on delta, because it would appear as if the character automatically rotates when at low FPS
			If p\Flags\Skidding=False Then
				If (p\SpeedLength#>2.675) Then
					p\Animation\Direction# = ATan2(((p\Motion\Acceleration\x#+p\Motion\DeltaCos#*(p\Physics\TURNING_SHARPNESS#+10*(p\SpeedLength#-0.5)+0))/(p\Physics\TURNING_SHARPNESS#+10*(p\SpeedLength#-0.5)+1))*1.0001,-(p\Motion\Acceleration\z#+p\Motion\DeltaSin#*(p\Physics\TURNING_SHARPNESS#+10*(p\SpeedLength#-0.5)+0))/(p\Physics\TURNING_SHARPNESS#+10*(p\SpeedLength#-0.5)+1))
				ElseIf (p\SpeedLength#>0.5) Then
					p\Animation\Direction# = ATan2(((p\Motion\Acceleration\x#+p\Motion\DeltaCos#*(p\Physics\TURNING_SHARPNESS#+2))/(p\Physics\TURNING_SHARPNESS#+3))*1.0001,-(p\Motion\Acceleration\z#+p\Motion\DeltaSin#*(p\Physics\TURNING_SHARPNESS#+2))/(p\Physics\TURNING_SHARPNESS#+3))
				Else
					p\Animation\Direction# = ATan2(((p\Motion\Acceleration\x#+p\Motion\DeltaCos#*(p\Physics\TURNING_SHARPNESS#+0))/(p\Physics\TURNING_SHARPNESS#+1))*1.0001,-(p\Motion\Acceleration\z#+p\Motion\DeltaSin#*(p\Physics\TURNING_SHARPNESS#+0))/(p\Physics\TURNING_SHARPNESS#+1))
				EndIf
			EndIf
			
			; Depending on the dot product between current direction and new motion direction
			p\Motion\DotProduct# = Vector_DotProductNormalized#(p\Motion\Acceleration, p\Motion\PlayerSpeed)

			If (p\Motion\DotProduct# < p\Physics\MOTION_DEVIATION_FACTOR[0]) Then
				; If there's an opposite change of motion direction, completely, albeit skid.
				If p\Motion\Ground Then
					Vector_MultiplyByScalar(p\Motion\Acceleration, (p\Physics\MOTION_ANTISLIDING_FACTOR#-1.0))
					If (p\Motion\SpeedLength#>0.4) Then p\Flags\Skidding = True : ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_SMOKE, p\Objects\Mesh, 1, 0.05, p\SpeedLength#+1.25, 0, 2)
				End If

				Player_SubstractTowardsZero(p\Motion\PlayerSpeed, 0.011*d\Delta#)

			Else If (p\Motion\DotProduct# < (p\Physics\MOTION_DEVIATION_FACTOR[1] + 0.1)) Then
				; If there's a harsh change in motion direction, decrease
				; greatly the motion in current direction and increase acceleration
				; on the new.
				If p\Motion\Ground Then
					p\Motion\SpeedCompensation\x# = (p\Motion\Speed\x#*p\Physics\MOVEMENT_SPEEDCOMP_HIGH#+p\Motion\DeltaCos#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_HIGH#+1)*0.96
					p\Motion\SpeedCompensation\z# = (p\Motion\Speed\z#*p\Physics\MOVEMENT_SPEEDCOMP_HIGH#+p\Motion\DeltaSin#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_HIGH#+1)*0.96				
					Vector_LinearInterpolation(p\Motion\PlayerSpeed, p\Motion\SpeedCompensation, d\Delta#)
				Else
					Player_SubstractTowardsZero(p\Motion\PlayerSpeed, 0.02*d\Delta#)
				EndIf

				Vector_MultiplyByScalar(p\Motion\Acceleration, p\Physics\MOTION_ANTISLIDING_FACTOR#)

			Else If (p\Motion\DotProduct# < p\Physics\MOTION_DEVIATION_FACTOR[2]) Then
				; If there's a mild change in direction, slighty decresae
				; the motion in current direction.
				If p\Motion\Ground Then
					p\Motion\SpeedCompensation\x# = (p\Motion\Speed\x#*p\Physics\MOVEMENT_SPEEDCOMP_MID#+p\Motion\DeltaCos#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_MID#+1);*0.98
					p\Motion\SpeedCompensation\z# = (p\Motion\Speed\z#*p\Physics\MOVEMENT_SPEEDCOMP_MID#+p\Motion\DeltaSin#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_MID#+1);*0.98
				Else
					p\Motion\SpeedCompensation\x# = (p\Motion\Speed\x#*(p\Physics\MOVEMENT_SPEEDCOMP_MID#+2.2)+p\Motion\DeltaCos#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_MID#+3.1)*0.98
					p\Motion\SpeedCompensation\z# = (p\Motion\Speed\z#*(p\Physics\MOVEMENT_SPEEDCOMP_MID#+2.2)+p\Motion\DeltaSin#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_MID#+3.1)*0.98
				EndIf

				Vector_MultiplyByScalar(p\Motion\Acceleration, (p\Physics\MOTION_ANTISLIDING_FACTOR#*0.5))
				Vector_LinearInterpolation(p\Motion\PlayerSpeed, p\Motion\SpeedCompensation, d\Delta#)									

			Else If (p\Motion\DotProduct# < p\Physics\MOTION_DEVIATION_FACTOR[3]) Then
				; If there's a low change in direction, slighty decresae
				; the motion in current direction.
				If p\Motion\Ground Then
					p\Motion\SpeedCompensation\x# = (p\Motion\Speed\x#*p\Physics\MOVEMENT_SPEEDCOMP_LOW#+p\Motion\DeltaCos#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_LOW#+1);*0.98 ; 6
					p\Motion\SpeedCompensation\z# = (p\Motion\Speed\z#*p\Physics\MOVEMENT_SPEEDCOMP_LOW#+p\Motion\DeltaSin#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_LOW#+1);*0.98 ; 6
				Else
					p\Motion\SpeedCompensation\x# = (p\Motion\Speed\x#*(p\Physics\MOVEMENT_SPEEDCOMP_LOW#+3.3)+p\Motion\DeltaCos#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_LOW#+4.2)*0.98 ; 8 ; 0.98
					p\Motion\SpeedCompensation\z# = (p\Motion\Speed\z#*(p\Physics\MOVEMENT_SPEEDCOMP_LOW#+3.3)+p\Motion\DeltaSin#*p\Motion\SpeedLength#)/(p\Physics\MOVEMENT_SPEEDCOMP_LOW#+4.2)*0.98 ; 8 ; 0.98
				EndIf

				Vector_MultiplyByScalar(p\Motion\Acceleration, (p\Physics\MOTION_ANTISLIDING_FACTOR#*0.5))
				Vector_LinearInterpolation(p\Motion\PlayerSpeed, p\Motion\SpeedCompensation, d\Delta#)		
			End If

			If (p\Motion\SpeedLength# <= p\Physics\COMMON_XZTOPSPEED#) Then
				Vector_MultiplyByScalar(p\Motion\Acceleration, p\Physics\COMMON_XZACCELERATION#*d\Delta#)
				Vector_Add(p\Motion\PlayerSpeed, p\Motion\Acceleration)
			End If
		End If

		; Set back the ground speed
		p\Motion\Speed\x# = p\Motion\PlayerSpeed\x# : p\Motion\Speed\z# = p\Motion\PlayerSpeed\z#
		Delete p\Motion\Acceleration : Delete p\Motion\PlayerSpeed : Delete p\Motion\SpeedCompensation
End Function


	; =========================================================================================================
	; =========================================================================================================
	Function Player_HandleAngleAcceleration(p.tPlayer, d.tDeltaTime)
		; Decelerate and check for falling
		If (Abs(p\Motion\Align\y#) <= 0.7) Then 
			p\Motion\Speed\x# = p\Motion\Speed\x#+p\Motion\Align\x#^2*0.04*Sgn(p\Motion\Align\x#)*d\Delta
			p\Motion\Speed\z# = p\Motion\Speed\z#+p\Motion\Align\z#^2*0.04*Sgn(p\Motion\Align\z#)*d\Delta
		End If

		; Check if player falls
		If (p\Motion\Align\y# <= 0.1 And Vector_Length#(p\Motion\Speed)*10<(2.0))
			Player_ConvertGroundToAir(p)
			p\Motion\Align\x# 	= Game\Stage\GravityAlignment\x#
			p\Motion\Align\y# 	= Game\Stage\GravityAlignment\y#
			p\Motion\Align\z# 	= Game\Stage\GravityAlignment\z#
			p\Motion\Ground 	= False 
		End If

		; Some kind of rolling physics
		If p\Physics\Rolling and p\SpeedLength#<p\Physics\COMMON_XZTOPSPEED# Then
			p\Motion\Speed\x# = p\Motion\Speed\x#+p\Motion\Align\x#^2*(0.0579*Abs(p\Motion\Align\y#)+0.0902)*Sgn(p\Motion\Align\x#)*d\Delta
			p\Motion\Speed\z# = p\Motion\Speed\z#+p\Motion\Align\z#^2*(0.0579*Abs(p\Motion\Align\y#)+0.0902)*Sgn(p\Motion\Align\z#)*d\Delta
		;Else
		;	p\Motion\Speed\x# = p\Motion\Speed\x#+p\Motion\Align\x#^2*(0.054*Abs(p\Motion\Align\y#)+0.0402)*Sgn(p\Motion\Align\x#)*d\Delta
		;	p\Motion\Speed\z# = p\Motion\Speed\z#+p\Motion\Align\z#^2*(0.054*Abs(p\Motion\Align\y#)+0.0402)*Sgn(p\Motion\Align\z#)*d\Delta
		End If

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function Player_SubstractTowardsZero(v.tVector, Delta#)
		; Clamp delta
		Delta# = Min#(Max#(Delta#, 0.0), 1.0)

		; Calculate substract value
		SubX# = v\x#*Delta#
		SubY# = v\y#*Delta#
		SubZ# = v\z#*Delta#

		; Substract to each axys
		If (v\x# > 0) Then : v\x# = Max#(v\x#-SubX#, 0) : Else  : v\x# = Min#(v\x#-SubX#, 0) : End If
		If (v\y# > 0) Then : v\y# = Max#(v\y#-SubY#, 0) : Else  : v\y# = Min#(v\y#-SubY#, 0) : End If
		If (v\z# > 0) Then : v\z# = Max#(v\z#-SubZ#, 0) : Else  : v\z# = Min#(v\z#-SubZ#, 0) : End If
	End Function



;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	Function Player_Rival(p.tPlayer, d.tDeltaTime)

		If (Not(p\Action=ACTION_RIVALDIE)) Then
			doattack=false
			doattack2=false
			dojumps=false
			If (Not(p\Rival\Running)) Then
				If EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)<200 Then
					doattack=true
					doattack2=true
					dojumps=true
					If Not(p\Rival\MoveTimer>0) Then
						If p\Rival\DontMoveTimer>0 Then
							Player_SetSpeed(p,0)
						Else
							p\Rival\MoveTimer=(1.5+Rand(0,5)/2.0)*secs#
							p\Rival\DontMoveTimer=(Rand(0,2)/2.0)*secs#
							p\Rival\Speed#=Rand(1,4)/2.0
						EndIf
					ElseIf p\Rival\MoveTimer>0 and (Not(p\Action=ACTION_HURT)) Then
						If (Not(p\Action=ACTION_CHARGE)) Then
							If (EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)>15 and p\Flags\Attacking=False) Or p\Action=ACTION_ROLL Then
								If p\Action=ACTION_ROLL Then
									Player_SetSpeed(p,p\Rival\Speed#)
								Else
									p\Animation\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,p\Objects\Entity))
									If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<50 Then
										Player_SetSpeed(p,p\Rival\Speed#*(EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)/50.0))
									Else
										Player_SetSpeed(p,p\Rival\Speed#)
									EndIf
								EndIf
							Else
								p\Animation\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,p\Objects\Entity))
								If Not(p\Flags\Attacking) Then Player_SetSpeed(p,0)
							EndIf
						ElseIf p\Action=ACTION_CHARGE Then
							p\Animation\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,p\Objects\Entity))
							Player_SetSpeed(p,0)
						EndIf
					EndIf
				EndIf
			Else
				p\Animation\Direction#=180
				If p\Objects\Position\z#<pp(1)\Objects\Position\z#-300 Or p\Objects\Position\z#>pp(1)\Objects\Position\z#+700 Or abs(p\Objects\Position\y#-pp(1)\Objects\Position\y#)>700 Then
					PositionEntity p\Objects\Entity, 0, pp(1)\Objects\Position\y#+20, pp(1)\Objects\Position\z#+250, 1
					EmitSmartSound(Sound_Teleport,p\Objects\Entity)
				Else
					If p\Objects\Position\z#<pp(1)\Objects\Position\z#+300 Then
						If p\HurtTimer>0 Then
							Player_SetSpeed(p,6.5)
						Else
							If p\Objects\Position\z#>pp(1)\Objects\Position\z#-50 Then
								If pp(1)\SpeedLength#<2.5 Then
									Player_SetSpeed(p,2.5)
								Else
									If pp(1)\Flags\Attacking=False and pp(1)\Flags\InJumpAction=False and Game\SpeedShoes=0 Then
										Player_SetSpeed(p,pp(1)\SpeedLength#-0.25)
									Else
										Player_SetSpeed(p,pp(1)\SpeedLength#-0.5)
									EndIf
								EndIf
							Else
								If pp(1)\SpeedLength#<2.5 Then
									Player_SetSpeed(p,2.5/2.0)
								Else
									Player_SetSpeed(p,pp(1)\SpeedLength#/2.0)
								EndIf
							EndIf
						EndIf
					Else
						Player_SetSpeed(p,pp(1)\SpeedLength#/2.0)
					EndIf
					If pp(1)\SpeedLength#>0 Then
						If Not(p\Rival\MoveTimer)>0 Then
							p\Rival\MoveTimer=2*secs#
							Select(Rand(1,3))
							Case 1: p\Rival\MoveSide=-1
							Case 2: p\Rival\MoveSide=0
							Case 3: p\Rival\MoveSide=1
							End Select
						EndIf
						If p\SpeedLength#>0 Then
							If p\Objects\Position\x#<18*p\Rival\MoveSide Then MoveEntity p\Objects\Entity, 0.25*d\Delta, 0, 0
							If p\Objects\Position\x#>18*p\Rival\MoveSide Then MoveEntity p\Objects\Entity, -0.25*d\Delta, 0, 0
						EndIf
					EndIf
					If pp(1)\Flags\Attacking Then doattack=true
					If p\Objects\Position\z#<pp(1)\Objects\Position\z#+50 and p\Objects\Position\z#>pp(1)\Objects\Position\z#-200 Then doattack=true : doattack2=true
					If p\Objects\Position\z#<pp(1)\Objects\Position\z#+200 Then dojumps=true
				EndIf
			EndIf

			If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<5 Then
				If p\Flags\Attacking and (pp(1)\Flags\Attacking Or Game\Invinc=1) Then
					If pp(1)\Action=ACTION_STOMP Then rockP=true else rockP=false
					If p\Action=ACTION_STOMP Then rockE=true else rockE=false
					If pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_ROLL Or pp(1)\Action=ACTION_DRIFT Then paperP=true else paperP=false
					If p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT Then paperE=true else paperE=false
					If rockP=false and paperP=false and pp(1)\Flags\Attacking Then scissorsP=true Else scissorsP=False
					If rockE=false and paperE=false and p\Flags\Attacking Then scissorsE=true Else scissorsE=False
					If Game\Invinc=1 Then rockP=true : paperP=true : scissorsP=true

					If (rockP and scissorsE) or (paperP and rockE) or (scissorsP and paperE) Then
						Player_Hit(p)
					ElseIf (rockP and paperE) or (paperP and scissorsE) or (scissorsP and rockE) Then
						Player_Hit(pp(1))
					Else
						p\Motion\Ground=False : p\Action=ACTION_FALL : Player_SetSpeed(p,-1.75) : p\Motion\Speed\y#=0.4
						pp(1)\Motion\Ground=False : pp(1)\Action=ACTION_FALL : Player_SetSpeed(pp(1),-1.75) : pp(1)\Motion\Speed\y#=0.4
					EndIf
					ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
					ParticleTemplate_Call(pp(1)\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
				ElseIf p\Flags\Attacking=False and (pp(1)\Flags\Attacking and (Not(pp(1)\Action=ACTION_JUMP))) Then
					Player_Hit(p)
					ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
				ElseIf (p\Flags\Attacking and (Not(p\Action=ACTION_JUMP))) and pp(1)\Flags\Attacking=False Then
					Player_Hit(pp(1))
					ParticleTemplate_Call(pp(1)\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
				EndIf
			EndIf

			If doattack or dojumps Then
				If Not(p\Rival\JustHadActionTimer>0) Then
					If dojumps Then
						If (Not(p\Action=ACTION_HOP OR p\Action=ACTION_JUMP)) and p\Motion\Ground and ( (Not(p\Rival\MakeJumpTimer>0)) Or (pp(1)\Action=ACTION_JUMP and Rand(1,2)=1) ) Then
							Player_ActuallyJump(p,true)
						EndIf
						If p\Flags\InJumpAction=False and p\Motion\Ground=False and ( (Not(p\Rival\MakeJumpActionTimer>0)) Or (pp(1)\Flags\InJumpAction and Rand(1,3)=1) ) Then
							If (Not(p\Action=ACTION_HOP OR p\Action=ACTION_JUMP)) and p\Motion\Ground Then Player_ActuallyJump(p,true)
							Select(Rand(1,2))
							Case 1: p\JumpActionMode=abs(p\JumpActionMode-1)
							End Select
							Player_JumpActions(p,true)
							p\Rival\JustHadActionTimer=0.8*secs#
						EndIf
					EndIf

					If doattack Then
						If EntityDistance(pp(1)\Objects\Entity,p\Objects\Entity)<50 Then
							If p\Flags\Attacking=False and ( (Not(p\Rival\MakeChargeTimer>0)) Or ((pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_ROLL Or pp(1)\Action=ACTION_DRIFT) and Rand(1,3)=1) ) Then
								Player_Action_Roll_Initiate_Rival(p)
								p\Rival\JustHadActionTimer=0.8*secs#
							EndIf
							If p\Flags\Attacking=False and p\Motion\Ground=False and ( (Not(p\Rival\MakeStompTimer>0)) Or (pp(1)\Action=ACTION_STOMP and Rand(1,2)=1) ) Then
								Select(Rand(0,1))
								Case 0: Player_Action_Stomp_Initiate_Rival(p)
								Case 1: Player_Action_Stomp_Initiate_Rival(p,true)
								End Select
								p\Rival\JustHadActionTimer=0.8*secs#
							EndIf
							If doattack2 and p\Flags\Attacking=False and ( (Not(p\Rival\MakeAttackTimer>0)) Or ((pp(1)\Flags\Attacking Or pp(1)\Action=ACTION_THROW Or pp(1)\Action=ACTION_SHOOT Or pp(1)\Action=ACTION_SHOOTHOVER Or pp(1)\Action=ACTION_PSYCHO) and Rand(1,4)=1) ) Then
								Select(Rand(1,3))
									Case 1: Player_SkillActions(p,true)
									Case 2: Player_SkillActions2(p,true)
									Case 3: Player_SkillActions3(p,true)
								End Select
								p\Rival\JustHadActionTimer=0.8*secs#
							EndIf
						EndIf
					EndIf
				EndIf

				If Not(p\Rival\MakeJumpTimer>0) Then p\Rival\MakeJumpTimer=(Rand(1,20)/4.0)*secs#
				If Not(p\Rival\MakeJumpActionTimer>0) Then p\Rival\MakeJumpActionTimer=(Rand(1,16)/4.0)*secs#
				If Not(p\Rival\MakeAttackTimer>0) Then p\Rival\MakeAttackTimer=(Rand(1,8)/4.0)*secs#
				If Not(p\Rival\MakeChargeTimer>0) Then p\Rival\MakeChargeTimer=(Rand(1,16)/4.0)*secs#
				If Not(p\Rival\MakeStompTimer>0) Then p\Rival\MakeStompTimer=(Rand(1,32)/4.0)*secs#

				If p\Action = ACTION_JUMPDASH Or (p\Action = ACTION_DOUBLEJUMP and (p\Character=CHAR_AMY Or p\Character=CHAR_BLA Or p\Character=CHAR_EME)) Or p\Action = ACTION_DIVE Then
					Player_GetClosestObject(p,1)
				ElseIf p\Flags\Attacking Then
					Player_GetClosestObject(p,3)
				EndIf
			EndIf

			If p\Objects\Position\y#<Game\Stage\Properties\DeathLevel Then
				Player_SetPosition(p, p\Rival\InitialPositionX#, p\Rival\InitialPositionY#, p\Rival\InitialPositionZ#, p\Rival\InitialRotationY#)
				EmitSmartSound(Sound_Teleport,p\Objects\Entity)
				Player_ResetAllTimers(p)
				Player_Hit(p)
			EndIf
		Else
			Player_SetSpeed(p,0)
		EndIf

	End Function

	Function Player_ReturnChosenRival(char,try)
		Select char
			Case CHAR_SON:
				Select(try)
				Case 1: Return CHAR_SHA
				Case 2: Return CHAR_MET
				Case 3: Return CHAR_SIL
				Case 4: Return CHAR_INF
				End Select
			Case CHAR_TAI:
				Select(try)
				Case 1: Return CHAR_WAV
				Case 2: Return CHAR_ROU
				Case 3: Return CHAR_TDL
				Case 4: Return CHAR_BIG
				End Select
			Case CHAR_KNU:
				Select(try)
				Case 1: Return CHAR_ROU
				Case 2: Return CHAR_MKN
				Case 3: Return CHAR_SHA
				Case 4: Return CHAR_OME
				End Select
			Case CHAR_AMY:
				Select(try)
				Case 1: Return CHAR_TIA
				Case 2: Return CHAR_BLA
				Case 3: Return CHAR_ESP
				Case 4: Return CHAR_HON
				End Select
			Case CHAR_SHA:
				Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_SIL
				Case 3: Return CHAR_KNU
				Case 4: Return CHAR_INF
				End Select
			Case CHAR_ROU:
				Select(try)
				Case 1: Return CHAR_KNU
				Case 2: Return CHAR_TAI
				Case 3: Return CHAR_CRE
				Case 4: Return CHAR_ESP
				End Select
			Case CHAR_CRE:
				Select(try)
				Case 1: Return CHAR_CHA
				Case 2: Return CHAR_MAR
				Case 3: Return CHAR_ROU
				Case 4: Return CHAR_TIK
				End Select
			Case CHAR_BLA:
				Select(try)
				Case 1: Return CHAR_TIK
				Case 2: Return CHAR_AMY
				Case 3: Return CHAR_MIG
				Case 4: Return CHAR_MPH
				End Select
			Case CHAR_SIL:
				Select(try)
				Case 1: Return CHAR_MPH
				Case 2: Return CHAR_SHA
				Case 3: Return CHAR_NAC
				Case 4: Return CHAR_SON
				End Select
			Case CHAR_OME:
				Select(try)
				Case 1: Return CHAR_EGG
				Case 2: Return CHAR_BET
				Case 3: Return CHAR_VEC
				Case 4: Return CHAR_KNU
				End Select
			Case CHAR_ESP:
				Select(try)
				Case 1: Return CHAR_SHD
				Case 2: Return CHAR_MIG
				Case 3: Return CHAR_AMY
				Case 4: Return CHAR_ROU
				End Select
			Case CHAR_CHA:
				Select(try)
				Case 1: Return CHAR_CRE
				Case 2: Return CHAR_STO
				Case 3: Return CHAR_MAR
				Case 4: Return CHAR_SHD
				End Select
			Case CHAR_VEC:
				Select(try)
				Case 1: Return CHAR_BIG
				Case 2: Return CHAR_BAR
				Case 3: Return CHAR_OME
				Case 4: Return CHAR_HBO
				End Select
			Case CHAR_BIG:
				Select(try)
				Case 1: Return CHAR_VEC
				Case 2: Return CHAR_HBO
				Case 3: Return CHAR_BAR
				Case 4: Return CHAR_TAI
				End Select
			Case CHAR_MAR:
				Select(try)
				Case 1: Return CHAR_HON
				Case 2: Return CHAR_CRE
				Case 3: Return CHAR_CHA
				Case 4: Return CHAR_GME
				End Select
			Case CHAR_MIG:
				Select(try)
				Case 1: Return CHAR_JET
				Case 2: Return CHAR_ESP
				Case 3: Return CHAR_BLA
				Case 4: Return CHAR_EME
				End Select
			Case CHAR_RAY:
				Select(try)
				Case 1: Return CHAR_BEA
				Case 2: Return CHAR_TDL
				Case 3: Return CHAR_TAI
				Case 4: Return CHAR_GAM
				End Select
			Case CHAR_CHO:
				Select(try)
				Case 1: Return CHAR_BLA
				Case 2: Return CHAR_MPH
				Case 3: Return CHAR_STO
				Case 4: Return CHAR_EGR
				End Select
			Case CHAR_TIK:
				Select(try)
				Case 1: Return CHAR_EME
				Case 2: Return CHAR_NAC
				Case 3: Return CHAR_WAV
				Case 4: Return CHAR_CRE
				End Select
			Case CHAR_NAC:
				Select(try)
				Case 1: Return CHAR_HBO
				Case 2: Return CHAR_TIK
				Case 3: Return CHAR_SIL
				Case 4: Return CHAR_JET
				End Select
			Case CHAR_BEA:
				Select(try)
				Case 1: Return CHAR_RAY
				Case 2: Return CHAR_WAV
				Case 3: Return CHAR_TAI
				Case 4: Return CHAR_BET
				End Select
			Case CHAR_BAR:
				Select(try)
				Case 1: Return CHAR_STO
				Case 2: Return CHAR_VEC
				Case 3: Return CHAR_BIG
				Case 4: Return CHAR_MKN
				End Select
			Case CHAR_JET:
				Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_MET
				Case 3: Return CHAR_MT3
				Case 4: Return CHAR_NAC
				End Select
			Case CHAR_WAV:
				Select(try)
				Case 1: Return CHAR_TAI
				Case 2: Return CHAR_BEA
				Case 3: Return CHAR_TIK
				Case 4: Return CHAR_TIA
				End Select
			Case CHAR_STO:
				Select(try)
				Case 1: Return CHAR_BAR
				Case 2: Return CHAR_CHA
				Case 3: Return CHAR_CHO
				Case 4: Return CHAR_RAY
				End Select
			Case CHAR_TIA:
				Select(try)
				Case 1: Return CHAR_AMY
				Case 2: Return CHAR_HON
				Case 3: Return CHAR_MPH
				Case 4: Return CHAR_WAV
				End Select
			Case CHAR_HON:
				Select(try)
				Case 1: Return CHAR_MAR
				Case 2: Return CHAR_SHD
				Case 3: Return CHAR_TIA
				Case 4: Return CHAR_AMY
				End Select
			Case CHAR_SHD:
				Select(try)
				Case 1: Return CHAR_ESP
				Case 2: Return CHAR_TIA
				Case 3: Return CHAR_HON
				Case 4: Return CHAR_CHA
				End Select
			Case CHAR_MPH:
				Select(try)
				Case 1: Return CHAR_SIL
				Case 2: Return CHAR_CHO
				Case 3: Return CHAR_TIA
				Case 4: Return CHAR_BLA
				End Select
			Case CHAR_HBO:
				Select(try)
				Case 1: Return CHAR_NAC
				Case 2: Return CHAR_BIG
				Case 3: Return CHAR_CHW
				Case 4: Return CHAR_VEC
				End Select
			Case CHAR_GAM:
				Select(try)
				Case 1: Return CHAR_BET
				Case 2: Return CHAR_EGG
				Case 3: Return CHAR_EME
				Case 4: Return CHAR_STO
				End Select
			Case CHAR_EME:
				Select(try)
				Case 1: Return CHAR_CHO
				Case 2: Return CHAR_GME
				Case 3: Return CHAR_EGR
				Case 4: Return CHAR_MIG
				End Select
			Case CHAR_MET:
				Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_MIG
				Case 3: Return CHAR_COM
				Case 4: Return CHAR_ESP
				End Select
			Case CHAR_TDL:
				Select(try)
				Case 1: Return CHAR_CHW
				Case 2: Return CHAR_RAY
				Case 3: Return CHAR_TAI
				Case 4: Return CHAR_CHW
				End Select
			Case CHAR_MKN:
				Select(try)
				Case 1: Return CHAR_EGR
				Case 2: Return CHAR_KNU
				Case 3: Return CHAR_GME
				Case 4: Return CHAR_BAR
				End Select
			Case CHAR_EGG:
				Select(try)
				Case 1: Return CHAR_OME
				Case 2: Return CHAR_GAM
				Case 3: Return CHAR_BET
				Case 4: Return CHAR_MT3
				End Select
			Case CHAR_BET:
				Select(try)
				Case 1: Return CHAR_GAM
				Case 2: Return CHAR_OME
				Case 3: Return CHAR_EGG
				Case 4: Return CHAR_BEA
				End Select
			Case CHAR_MT3:
				Select(try)
				Case 1: Return CHAR_COM
				Case 2: Return CHAR_JET
				Case 3: Return CHAR_PRS
				Case 4: Return CHAR_EGG
				End Select
			Case CHAR_GME:
				Select(try)
				Case 1: Return CHAR_PRS
				Case 2: Return CHAR_EME
				Case 3: Return CHAR_MKN
				Case 4: Return CHAR_MAR
				End Select
			Case CHAR_PRS:
				Select(try)
				Case 1: Return CHAR_GME
				Case 2: Return CHAR_PRS
				Case 3: Return CHAR_MT3
				Case 4: Return CHAR_COM
				End Select
			Case CHAR_COM:
				Select(try)
				Case 1: Return CHAR_MT3
				Case 2: Return CHAR_COM
				Case 3: Return CHAR_MET
				Case 4: Return CHAR_PRS
				End Select
			Case CHAR_CHW:
				Select(try)
				Case 1: Return CHAR_TDL
				Case 2: Return CHAR_EGR
				Case 3: Return CHAR_HBO
				Case 4: Return CHAR_TDL
				End Select
			Case CHAR_EGR:
				Select(try)
				Case 1: Return CHAR_MKN
				Case 2: Return CHAR_CHW
				Case 3: Return CHAR_GAM
				Case 4: Return CHAR_CHO
				End Select
			Case CHAR_INF:
				Select(try)
				Case 1: Return CHAR_SON
				Case 2: Return CHAR_SHA
				Case 3: Return CHAR_SIL
				Case 4: Return CHAR_KNU
				End Select
			Case CHAR_TRI:
				Select(try)
				Case 1: Return CHAR_NAC
				Case 2: Return CHAR_AMY
				Case 3: Return CHAR_EGR
				Case 4: Return CHAR_EGW
				End Select
			Case CHAR_TMH:
				Select(try)
				Case 1: Return CHAR_EGG
				Case 2: Return CHAR_CHW
				Case 3: Return CHAR_GAM
				Case 4: Return CHAR_BET
				End Select
			Default:
				Select(try)
				Case 1: Return CHAR_SHA
				Case 2: Return CHAR_MET
				Case 3: Return CHAR_SIL
				Case 4: Return CHAR_INF
				End Select
		End Select
		Return CHAR_SON
	End Function