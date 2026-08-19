
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
	Player_ChangeLeader(p)
	
	; Run timers
	Player_HandleTimers(p)
	
	; Run extra management
	Player_ExtraHandle(p,d)
	
	; Run sounds
	Player_DealSounds(p)
	
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
	Player_ActuallyStomp(p)
	
	; Can drift any time on ground
	Player_ActuallyDrift(p)
	
	; Some skills performable anytime
	If p\No#=1 And Player_IsPlayable(p) And Menu\ChaoGarden=0 Then
		If Menu\Stage>0 And (Not(p\Action=ACTION_DEBUG Or Game\Victory<>0 Or p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_GRABBED Or p\Action=ACTION_HOLD Or p\Action=ACTION_TRANSFORM Or p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_BOARDFALL Or p\Action=ACTION_BOARDTRICK Or p\Action=ACTION_SKYDIVE Or p\Action=ACTION_GLIDER Or p\Action=ACTION_FREEZE Or p\Action=ACTION_SINK Or p\Action=ACTION_CAR Or p\Action=ACTION_CARFALL Or p\Action=ACTION_CARDRIFT Or p\Action=ACTION_TORNADO)) And p\ObjPickUp=0 Then
			If Input\Pressed\ActionSkill1 And (Not(p\Action=ACTION_CHARGE Or p\Action=ACTION_DRIFT)) Then
				Select p\Character
					Case CHAR_SIL
						Player_Action_Psycho_Initiate(p)
					Case CHAR_GAM
						Player_Action_Shoot_AimBegin(p)
				End Select
			EndIf
			If Input\Pressed\ActionSkill2 Then
				Select p\Character
					Default
						If Player_CanLightDash(p\Character) And (Not(Game\Interface\ControlTipPickUpTimer>0)) Then p\LightDashRequestTimer=0.1*secs#
				End Select
			EndIf
			
			If Input\Hold\ActionSkill3 Then
				If p\Character=CHAR_SHN And p\Motion\Ground=False And (Not(p\Action=ACTION_SHOOT Or p\GoDestination)) Then Player_Action_Shoot_Initiate(p,3)
			EndIf	
			
		EndIf
	EndIf
	
	; Flags
	Player_DetermineFlags(p)
	
	; Go super
	If p\No#=1 Then
		If Input\Pressed\ActionAct And p\Flags\CanSuperTransform  Then
			For ppp.tPlayer = Each tPlayer
				If Player_IsPlayable(ppp) Then
					ppp\Action=ACTION_TRANSFORM
					ppp\JumpMayRiseTimer=2*secs#
				EndIf
			Next
			Create_Emerald.tEmerald(p)
		EndIf
	EndIf
	
	; Be super
	If (Game\SuperForm>0 And Player_IsPlayable(p)) Then Game\Invinc=1 : Game\SpeedShoes=1
	
	; Control tips
	If p\No#=1 Then Interface_ControlTipUpdate(p\Action)
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
		; Movement
	If Player_IsPlayable(p) Then 
		If (Game\QuickStepLock=1 And (Input\Hold\Left Or Input\Hold\Right)) Or p\Action=ACTION_DIEHURT Or p\Action=ACTION_DIE Or Game\Interface\Shop=1 Or Game\Interface\SoundTest=1 Then
			
		ElseIf Game\MachLock>0 Then
			If Input\Hold\Down Then Else Player_Movement(p,d)
		ElseIf p\Action=ACTION_CLIMB And p\Character=CHAR_ESP Then
			
		Else
			Player_Movement(p,d)
		EndIf
		
	EndIf
	
		; If the character's on the ground, apply deceleration based on the current slope, and
		; check if he has not enough speed to go further.
	If p\CanClimbTimer>0 And (p\Action=ACTION_CLIMB Or (p\Action=ACTION_THRUST And p\Character=CHAR_MIG) Or (p\Action=ACTION_GLIDE And (Not(p\Character=CHAR_OME))) )Then
		p\Flags\CanClimb=True
		p\Physics\MOTION_GROUND# = -1
	Else
		p\Flags\CanClimb=False
		If p\Motion\Ground Then Player_HandleAngleAcceleration(p, d)
		p\Physics\MOTION_GROUND# = p\Physics\REAL_MOTION_GROUND# ; 0.65
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
	ElseIf Game\DirLock=1 Then
		p\Motion\Direction# = Game\DirLockDir-Input\Movement_Direction#
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
		If p\Action=ACTION_GLIDE And Game\MachLock=0 Then
			p\Animation\Direction# = ATan2(((p\Motion\Acceleration\x#+p\Motion\DeltaCos#*(p\Physics\TURNING_SHARPNESS#+0))/(p\Physics\TURNING_SHARPNESS#+1))*1.0001,-(p\Motion\Acceleration\z#+p\Motion\DeltaSin#*(p\Physics\TURNING_SHARPNESS#+0))/(p\Physics\TURNING_SHARPNESS#+1))				
		ElseIf p\Flags\Skidding=False Then
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
		If p\Physics\Rolling And p\SpeedLength#<p\Physics\COMMON_XZTOPSPEED# Then
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

	
;~IDEal Editor Parameters:
;~C#Blitz3D