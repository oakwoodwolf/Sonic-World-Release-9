

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Function Player_Physics(p.tPlayer, d.tDeltaTime)
	
	p\Physics\MOTION_CEILING# = -0.65
	p\Physics\MOTION_CEILING_STOP# = -0.79
	p\Physics\MOTION_WALL_UP# = -0.7
	p\Physics\MOTION_WALL_DOWN# = 0.2
	p\Physics\MOTION_WALL_DIRECTION# = 0.3
	
	p\Physics\MOTION_DEVIATION_FACTOR[0] =-0.5
	p\Physics\MOTION_DEVIATION_FACTOR[1] = 0.4
	p\Physics\MOTION_DEVIATION_FACTOR[2] = 0.95
	p\Physics\MOTION_DEVIATION_FACTOR[3] = 1.01
	p\Physics\MOTION_ANTISLIDING_FACTOR# = 2.2
	
	p\Physics\RINGDASH_SPEED# = 6.0
	p\Physics\COMMON_ROLLWEIGHT# = 0.10
	p\Physics\COMMON_ROLLWEIGHT_UP# = 0.1
	p\Physics\COMMON_ROLLWEIGHT_DOWN# = 0.15
	p\Physics\ROLL_WEIGHT_MULTIPLIER# = 1.0
	
	; Speed compensation. Lower = better turning power
	Select p\Action
		Case ACTION_DRIFT,ACTION_BOARDDRIFT,ACTION_CARDRIFT:
			p\Physics\MOVEMENT_SPEEDCOMP_HIGH#		= (5.92/(3.75))
			p\Physics\MOVEMENT_SPEEDCOMP_MID#		= (3.8/(3.75))
			p\Physics\MOVEMENT_SPEEDCOMP_LOW#		= (1.75/(3.75))
		Default:
			p\Physics\MOVEMENT_SPEEDCOMP_HIGH#		= (1.92+2.25) ;7.92
			p\Physics\MOVEMENT_SPEEDCOMP_MID#		= (1.8+1.125) ;5.8
			p\Physics\MOVEMENT_SPEEDCOMP_LOW#		= (1.75) ;3.75
	End Select
	
	; Leaning
	p\Physics\UP_ANGLE_TARGET# = 20*p\SpeedLength#
	If p\Physics\UP_ANGLE_TARGET#>80 Then p\Physics\UP_ANGLE_TARGET#=80
	If p\Physics\UP_ANGLE_TARGET#<-80 Then p\Physics\UP_ANGLE_TARGET#=-80
	Select p\Action
		Case ACTION_GRIND:
			p\Physics\LEAN_ANGLE_TARGET# = (p\SpeedLength#/7.55)*20*1.75
			p\Physics\LEAN_ANGLE_SPEED# = 0.7*6
		Default:
			If p\Flags\InJumpAction Then
				Select p\Action
					Case ACTION_JUMPDASH,ACTION_DOUBLEJUMP,ACTION_HOVER,ACTION_FLUTTER,ACTION_DIVE,ACTION_BUOY:
						p\Physics\LEAN_ANGLE_TARGET# = (p\SpeedLength#/7.55)*20*1.25
					Case ACTION_GLIDE
						p\Physics\LEAN_ANGLE_TARGET# = 1*20
						p\Physics\LEAN_ANGLE_SPEED# = 1.4
					Default:
						p\Physics\LEAN_ANGLE_TARGET# = (p\SpeedLength#/7.55)*20*2.25
				End Select
				p\Physics\LEAN_ANGLE_SPEED# = 0.7*2
			Else
				p\Physics\LEAN_ANGLE_TARGET# = (p\SpeedLength#/7.55)*20
				p\Physics\LEAN_ANGLE_SPEED# = 0.7
			EndIf
	End Select
	p\Physics\DRIFT_ANGLE_TARGET# = -30*p\DriftDirection
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	If p\Motion\Ground And p\SpeedLength#>0 Then
		p\Physics\COMMON_GROUNDTENSION# = -0.705252525
	Else
		p\Physics\COMMON_GROUNDTENSION# = 0.0
	EndIf
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	Select p\Action
		Case ACTION_BOARD,ACTION_BOARDJUMP,ACTION_BOARDFALL,ACTION_BOARDTRICK
			If Game\Vehicle=8 Then
				p\Physics\TURNING_SHARPNESS# = 18.05+1.025*(p\SpeedLength#/6.0)
			Else
				p\Physics\TURNING_SHARPNESS# = 28.05+1.025*(p\SpeedLength#/6.0)
			EndIf
		Case ACTION_CAR,ACTION_CARFALL:
			If Input\Hold\ActionRoll Then
				p\Physics\TURNING_SHARPNESS# = 28.05+1.025*(p\SpeedLength#/6.0)
			Else
				p\Physics\TURNING_SHARPNESS# = 18.05+1.025*(p\SpeedLength#/6.0)
			EndIf
		Case ACTION_DRIFT,ACTION_GLIDE
			p\Physics\TURNING_SHARPNESS#=1.2
		Default:
			If p\SpeedLength#>2.675 Then
				p\Physics\TURNING_SHARPNESS# = 0.025+0.575*(p\SpeedLength#/6.0)
			ElseIf p\SpeedLength#>1 Then
				p\Physics\TURNING_SHARPNESS# = 6
			Else
				p\Physics\TURNING_SHARPNESS# = 1
			EndIf
			If p\IceFloorTimer>0 Or p\InkFloorTimer>0 Then p\Physics\TURNING_SHARPNESS#=p\Physics\TURNING_SHARPNESS#+11.3
	End Select
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	If p\Action=ACTION_ROLL Or p\Action=ACTION_BUMPED Or ((p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDDRIFT) And (Not(Game\Vehicle=8))) Then p\Physics\Rolling=True Else p\Physics\Rolling=False
	
	If Menu\Stage<0 Or Game\ControlLock>0 Or p\Action=ACTION_UP Or p\Action=ACTION_FWD Or p\Action=ACTION_HOMING Or p\Action=ACTION_STOMP Or p\Action=ACTION_FLOAT Or p\Action=ACTION_GRIND Or p\HasVehicle>0 Then
		p\Flags\DisallowCustomPhysics=True
	Else
		p\Flags\DisallowCustomPhysics=False
	EndIf
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	Select p\Action
		Case ACTION_UPPERCUT,ACTION_DEMODASH
			If p\Motion\Ground=True Then 
				p\Physics\COMMON_XZACCELERATION# = 0.001
			Else
				p\Physics\COMMON_XZACCELERATION# = 0.04475+0.015*(p\SpeedLength#/6.0)
			EndIf
			
		Case ACTION_ROLL,ACTION_CHARGE,ACTION_BUMPED
			
			p\Physics\COMMON_XZACCELERATION# = 0.001
			
			If p\Action=ACTION_ROLL And p\Motion\Ground=False Then p\Physics\COMMON_XZACCELERATION# = 0.04475+0.015*(p\SpeedLength#/6.0)
			
		Case ACTION_DRIFT,ACTION_BOARDDRIFT,ACTION_CARDRIFT:
			p\Physics\COMMON_XZACCELERATION# = 0.0257
		Case ACTION_SHOOT
			If p\Character=CHAR_OME And p\ShootType=3 Then
				p\Physics\COMMON_XZACCELERATION# = 0.001
			Else
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					If p\Motion\Ground Then
						p\Physics\COMMON_XZACCELERATION# = 0.05375+0.03*(p\SpeedLength#/6.0)
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.05875+0.025*(p\SpeedLength#/6.0)
					EndIf
				Else
					If p\Motion\Ground Then
						p\Physics\COMMON_XZACCELERATION# = 0.032875+0.03*(p\SpeedLength#/6.0)
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.04475+0.015*(p\SpeedLength#/6.0)
					EndIf
				EndIf
			EndIf 
		Case ACTION_CLIMB
			If p\WalldashTimer>0 Then
				p\Physics\COMMON_XZACCELERATION# = 0.001
			Else
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					If p\Motion\Ground Then
						p\Physics\COMMON_XZACCELERATION# = 0.05375+0.03*(p\SpeedLength#/6.0)
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.05875+0.025*(p\SpeedLength#/6.0)
					EndIf
				Else
					If p\Motion\Ground Then
						p\Physics\COMMON_XZACCELERATION# = 0.032875+0.03*(p\SpeedLength#/6.0)
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.04475+0.015*(p\SpeedLength#/6.0)
					EndIf
				EndIf
			EndIf
		Default:
			Select p\Action
				Case ACTION_SOAR,ACTION_SOARFLAP:
					If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
						p\Physics\COMMON_XZACCELERATION# = 0.081
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.081
					EndIf
				Case ACTION_FLUTTER:
					If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
						p\Physics\COMMON_XZACCELERATION# = 0.645*0.091
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.645*0.061
					EndIf
				Case ACTION_FLY:
					If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
						p\Physics\COMMON_XZACCELERATION# = 0.05875+0.015*(p\SpeedLength#/6.0)
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.031875+0.02*(p\SpeedLength#/6.0)
					EndIf
					If p\Character=CHAR_RAY And p\Motion\Speed\y#>1 Then p\Physics\COMMON_XZACCELERATION# = 0.001
				Case ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT:
					If Game\Vehicle=4 Then
						p\Physics\COMMON_XZACCELERATION# = 0.05375+0.05*(p\SpeedLength#/6.0)
						If Input\Hold\ActionRoll Then p\Physics\COMMON_XZACCELERATION#=1.325*p\Physics\COMMON_XZACCELERATION#
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.05375+0.03*(p\SpeedLength#/6.0)
						If Input\Hold\ActionRoll Then p\Physics\COMMON_XZACCELERATION#=1.125*p\Physics\COMMON_XZACCELERATION#
					EndIf
				Case ACTION_TORNADO:
					If p\HasVehicle=7 Then
						p\Physics\COMMON_XZACCELERATION# = 0.04875+0.03*(p\SpeedLength#/6.0)
					Else
						p\Physics\COMMON_XZACCELERATION# = 0.03875+0.03*(p\SpeedLength#/6.0)
					EndIf
				Default:
					If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
						If p\Motion\Ground Then
							p\Physics\COMMON_XZACCELERATION# = 0.05375+0.03*(p\SpeedLength#/6.0)
						Else
							p\Physics\COMMON_XZACCELERATION# = 0.05875+0.025*(p\SpeedLength#/6.0)
						EndIf
					Else
						If p\Motion\Ground Then
							p\Physics\COMMON_XZACCELERATION# = 0.032875+0.03*(p\SpeedLength#/6.0)
						Else
							p\Physics\COMMON_XZACCELERATION# = 0.04475+0.015*(p\SpeedLength#/6.0)
						EndIf
					EndIf			
			End Select
			If p\SpeedLength#<1 Then p\Physics\COMMON_XZACCELERATION#=p\Physics\COMMON_XZACCELERATION#+0.005*(1-p\SpeedLength#)/1.0
			p\Physics\COMMON_XZACCELERATION# = (p\Physics\COMMON_XZACCELERATION# + 0.005 - 0.0025*p\ScaleFactor# + 0.01*p\Rotation#/90.0) * (p\Physics\UNDERWATERTRIGGER#) * (p\Physics\ICETRIGGER#)
	End Select
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	Select p\Action
		Case ACTION_ROLL,ACTION_DRIFT,ACTION_BOARDDRIFT,ACTION_CARDRIFT,ACTION_BUMPED:
			p\Physics\COMMON_XZDECELERATION# = 0.0095*(p\Physics\UNDERWATERTRIGGER#+1.5)/2.0
		Case ACTION_PUNCH,ACTION_UPPERCUT,ACTION_DEMODASH,ACTION_THROW,ACTION_PSYCHO,ACTION_GATLING,ACTION_HOOKSHOT:
			p\Physics\COMMON_XZDECELERATION# = 0.055*(p\Physics\UNDERWATERTRIGGER#)
		Default:
			If p\Motion\Ground=True Then
				p\Physics\COMMON_XZDECELERATION# = 0.021875+0.03*(p\SpeedLength#/4.0)
			Else
				If Input\Hold\Up = True Or Input\Hold\Down=True Or Input\Hold\Left=True Or Input\Hold\Right=True Then
					p\Physics\COMMON_XZDECELERATION# = 0.021875+0.03*(p\SpeedLength#/6.0)
				Else
					p\Physics\COMMON_XZDECELERATION# = (0.021875+0.03*(p\SpeedLength#/6.0)*0.6)
					If p\Character=CHAR_RAY And p\Action=ACTION_FLY And p\Motion\Speed\y#>1 Then p\Physics\COMMON_XZDECELERATION#=p\Physics\COMMON_XZDECELERATION#*0.3
				EndIf
			EndIf 
			p\Physics\COMMON_XZDECELERATION# = (p\Physics\COMMON_XZDECELERATION#) * (p\Physics\UNDERWATERTRIGGER#) * (p\Physics\ICETRIGGER2#)
			;If p\Action=ACTION_SKYDIVE Then p\Physics\COMMON_XZDECELERATION# = p\Physics\COMMON_XZDECELERATION#*1.6
	End Select
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	If (Game\SpeedShoes=0 Or (Not(Player_IsPlayable(p)))) Then
		p\Physics\SPINDASH_SPEED# = 3.0
	Else
		p\Physics\SPINDASH_SPEED# = 5.0
	EndIf
	
	p\Physics\SPINDASH_SPEED# = p\Physics\SPINDASH_SPEED#*(p\Physics\UNDERWATERTRIGGER#)*(p\Physics\ICETRIGGER#)
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	If p\Motion\Ground Then
		Select p\Action
			Case ACTION_SLOWGLIDE:
				p\Physics\COMMON_XZTOPSPEED# = p\Physics\GLIDE_SPEED#*0.4
			Case ACTION_CLIMB:
				p\Physics\COMMON_XZTOPSPEED# = p\Physics\CLIMB_SPEED#
			Case ACTION_THROW,ACTION_PSYCHO,ACTION_HOOKSHOT:
				p\Physics\COMMON_XZTOPSPEED# = 3.02
			Case ACTION_PUNCH,ACTION_THRUST:
				Select p\Character
					Case CHAR_BIG,CHAR_VEC,CHAR_OME: p\Physics\COMMON_XZTOPSPEED# = 3.98
					Default: p\Physics\COMMON_XZTOPSPEED# = 4.24
				End Select
			Case ACTION_GATLING:
				p\Physics\COMMON_XZTOPSPEED# = 0.24
			Case ACTION_SHOOT:
				p\Physics\COMMON_XZTOPSPEED# = 3.5
			Case ACTION_SWIPE:
				p\Physics\COMMON_XZTOPSPEED# = 3
			Case ACTION_CLAW:
				p\Physics\COMMON_XZTOPSPEED# = 1.1
			Case ACTION_SWIPE:
				Select p\Character
					Case CHAR_TAI
						If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
							p\Physics\COMMON_XZTOPSPEED# = 6.94825
						Else
							p\Physics\COMMON_XZTOPSPEED# = GetCharSpeed#(p\RealCharacter)
						EndIf
					Default
						p\Physics\COMMON_XZTOPSPEED# = 1.52
				End Select 
			Case ACTION_SHAKETREE:
				p\Physics\COMMON_XZTOPSPEED# = 0
			Case ACTION_CARRY:
				p\Physics\COMMON_XZTOPSPEED# = GetCharSpeed#(p\RealCharacter)*0.5
			Case ACTION_SOAR:
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					p\Physics\COMMON_XZTOPSPEED# = 3.142
				Else
					p\Physics\COMMON_XZTOPSPEED# = 1.642
				EndIf
				
			Case ACTION_BOARD,ACTION_BOARDDRIFT,ACTION_GLIDER:
				If Game\Vehicle=8 Then
					p\Physics\COMMON_XZTOPSPEED# = 1.25
				ElseIf Game\Vehicle=5 Then
					p\Physics\COMMON_XZTOPSPEED# = 3.75
				Else
					p\Physics\COMMON_XZTOPSPEED# = 4.25
				EndIf
				If Input\Hold\ActionRoll Then p\Physics\COMMON_XZTOPSPEED#=p\Physics\COMMON_XZTOPSPEED#+0.5
			Case ACTION_CAR,ACTION_CARDRIFT:
				If Game\Vehicle=4 Then
					If Input\Hold\ActionRoll Then p\Physics\COMMON_XZTOPSPEED# = 6.375 Else p\Physics\COMMON_XZTOPSPEED# = 3.625
				Else
					If Input\Hold\ActionRoll Then p\Physics\COMMON_XZTOPSPEED# = 6.25 Else p\Physics\COMMON_XZTOPSPEED# = 3.125
				EndIf
			Case ACTION_PUDDLE:
				p\Physics\COMMON_XZTOPSPEED#=0.8115
			Case ACTION_TORNADO:
				If p\HasVehicle=7 Then p\Physics\COMMON_XZTOPSPEED#=6.5 Else p\Physics\COMMON_XZTOPSPEED#=4.5
			Default:
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					p\Physics\COMMON_XZTOPSPEED# = 6.94825
				Else
					p\Physics\COMMON_XZTOPSPEED# = GetCharSpeed#(p\RealCharacter)
				EndIf
				If p\Action=ACTION_ROLL Then p\Physics\COMMON_XZTOPSPEED#=p\Physics\COMMON_XZTOPSPEED#+0.25
		End Select
	Else
		Select p\Action
			Case ACTION_ROLL,ACTION_BUMPED:
				p\Physics\COMMON_XZTOPSPEED# = 6.5
			Case ACTION_FLY,ACTION_SOARFLAP:
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					p\Physics\COMMON_XZTOPSPEED# = 5.04
				Else
					p\Physics\COMMON_XZTOPSPEED# = 3.04
				EndIf
			Case ACTION_GLIDE,ACTION_FLUTTER,ACTION_SOAR,ACTION_SLEET:
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					p\Physics\COMMON_XZTOPSPEED# = 5.5
				Else
					p\Physics\COMMON_XZTOPSPEED# = 3.5
				EndIf
			Case ACTION_THROW,ACTION_PSYCHO,ACTION_HOOKSHOT:
				p\Physics\COMMON_XZTOPSPEED# = 1.02
			Case ACTION_LEVITATE:
				p\Physics\COMMON_XZTOPSPEED# = 4
			Case ACTION_HOVER,ACTION_SHOOTHOVER:
				p\Physics\COMMON_XZTOPSPEED# = 4
			Case ACTION_THRUST:
				Select p\Character
					Case CHAR_BIG,CHAR_VEC,CHAR_OME: p\Physics\COMMON_XZTOPSPEED# = 3.98
					Default: p\Physics\COMMON_XZTOPSPEED# = 4.24
				End Select
			Case ACTION_CLAW:
				p\Physics\COMMON_XZTOPSPEED# = 1.1
			Case ACTION_SWIPE:
				Select p\Character
					Case CHAR_TAI
						If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
							p\Physics\COMMON_XZTOPSPEED# = 6.46575
						Else
							p\Physics\COMMON_XZTOPSPEED# = 5.756876
						EndIf
					Default
						p\Physics\COMMON_XZTOPSPEED# = 1.24
				End Select
			Case ACTION_BUOY:
				p\Physics\COMMON_XZTOPSPEED# = 1.44
			Case ACTION_CARRYJUMP:
				p\Physics\COMMON_XZTOPSPEED# = 1.00
				
			Case ACTION_BOARDJUMP,ACTION_BOARDFALL,ACTION_BOARDTRICK,ACTION_SKYDIVE,ACTION_GLIDER:
				If Game\Vehicle=8 Then
					p\Physics\COMMON_XZTOPSPEED# = 5.5
				ElseIf Game\Vehicle=5 Then
					p\Physics\COMMON_XZTOPSPEED# = 3.75
				Else
					p\Physics\COMMON_XZTOPSPEED# = 4.25
				EndIf
				If Input\Hold\ActionRoll Then p\Physics\COMMON_XZTOPSPEED#=p\Physics\COMMON_XZTOPSPEED#+0.5
			Case ACTION_CARFALL:
				If Game\Vehicle=4 Then
					If Input\Hold\ActionRoll Then p\Physics\COMMON_XZTOPSPEED# = 6.375 Else p\Physics\COMMON_XZTOPSPEED# = 3.625
				Else
					If Input\Hold\ActionRoll Then p\Physics\COMMON_XZTOPSPEED# = 6.25 Else p\Physics\COMMON_XZTOPSPEED# = 3.125
				EndIf
			Case ACTION_TORNADO:
				If p\HasVehicle=7 Then p\Physics\COMMON_XZTOPSPEED#=6.5 Else p\Physics\COMMON_XZTOPSPEED#=4.5
			Default:
				If (Game\SpeedShoes=1 And Player_IsPlayable(p)) Then
					p\Physics\COMMON_XZTOPSPEED# = 6.46575
				Else
					p\Physics\COMMON_XZTOPSPEED# = 5.756876
				EndIf
		End Select
	EndIf
	
	p\Physics\COMMON_XZTOPSPEED# = p\Physics\COMMON_XZTOPSPEED# * (p\Physics\UNDERWATERTRIGGER#) * (p\Physics\SLOWTRIGGER#)
	
	If Menu\ChaoGarden=1 Or Game\Stage\Properties\Hub=1 Then
		If p\Physics\COMMON_XZTOPSPEED#>1.509458 Then p\Physics\COMMON_XZTOPSPEED#=1.509458
	EndIf
	
	p\Physics\COMMON_XZMAXSPEED# = p\Physics\COMMON_XZTOPSPEED#
	
	If Input\Hold\Down And Game\MachLock>0 Then 
		p\Physics\COMMON_XZMAXSPEED#=p\Physics\COMMON_XZMAXSPEED#*0.5
		p\Physics\COMMON_XZTOPSPEED#=p\Physics\COMMON_XZTOPSPEED#*0.5
	EndIf
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	If (Game\MachLock>0 And (Not(Game\MachLockDisabler>0)) And Game\CinemaMode=0 And (Not(p\Action=ACTION_DEBUG Or p\Action=ACTION_FREEZE))) Then
		If Input\Hold\Down Then p\Physics\COMMON_XZMINSPEED#=2 Else p\Physics\COMMON_XZMINSPEED#=4.5
	ElseIf p\BumpedTimer>0 Then
		If EntityPitch(p\Objects\Mesh)>-5 And (Not(p\BumpedCloudTimer>0)) Then p\Physics\COMMON_XZMINSPEED#=1.12 Else p\Physics\COMMON_XZMINSPEED# = 0
	Else
		Select p\Action
			Case ACTION_DRIFT:
				p\Physics\COMMON_XZMINSPEED# = 0.2*p\Physics\ICETRIGGER#
			Case ACTION_SLOWGLIDE:
				p\Physics\COMMON_XZMINSPEED# = 0.119
			Case ACTION_GLIDE:
				p\Physics\COMMON_XZMINSPEED# = 1.2
			Case ACTION_SOAR,ACTION_SOARFLAP:
				p\Physics\COMMON_XZMINSPEED# = 0.22
			Case ACTION_FLUTTER:
				p\Physics\COMMON_XZMINSPEED# = 0.468
			Case ACTION_SLEET:
				p\Physics\COMMON_XZMINSPEED# = 1.8
			Case ACTION_BOARD,ACTION_BOARDJUMP,ACTION_BOARDDRIFT,ACTION_BOARDFALL,ACTION_BOARDTRICK:
				If Game\Victory=0 Then
					If Game\Vehicle=8 Then
						If Input\Hold\ActionRoll Then p\Physics\COMMON_XZMINSPEED# = 0.75 Else p\Physics\COMMON_XZMINSPEED# = 0.15
					Else
						If EntityPitch(p\Objects\Mesh)>-5 Then p\Physics\COMMON_XZMINSPEED# = p\Physics\GRIND_SPEED#*0.9 Else p\Physics\COMMON_XZMINSPEED# = 0
					EndIf
				Else
					p\Physics\COMMON_XZMINSPEED# = 0
				EndIf
			Case ACTION_GLIDER:
				If Game\Victory=0 Then
					p\Physics\COMMON_XZMINSPEED# = 1.38
				Else
					p\Physics\COMMON_XZMINSPEED# = 0
				EndIf
			Case ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT:
				If Game\Victory=0 Then
					If Input\Hold\ActionRoll Then
						If Game\Vehicle=4 Then p\Physics\COMMON_XZMINSPEED# = 1.875 Else p\Physics\COMMON_XZMINSPEED# = 1.25
					Else
						p\Physics\COMMON_XZMINSPEED# = 0
					EndIf
				Else
					p\Physics\COMMON_XZMINSPEED# = 0
				EndIf
			Case ACTION_TORNADO:
				If Game\Victory=0 Then
					p\Physics\COMMON_XZMINSPEED# = 0.1
				Else
					p\Physics\COMMON_XZMINSPEED# = 0
				EndIf
;			Case ACTION_UP
;				p\Physics\COMMON_XZMINSPEED# = p\Physics\UpSpeed
			Default:
				p\Physics\COMMON_XZMINSPEED# = 0
		End Select
	EndIf
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	p\Physics\JUMPDASH_SPEED# = (0.95+1.4*(p\SpeedLength#/6.0))*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERZ#)
	
	p\Physics\FLYDOWN_SPEED# = 0.353101*(p\Physics\UNDERWATERTRIGGER#)
	
	p\Physics\SKYDIVE_SPEED# = -1.5*0.9*(p\Physics\UNDERWATERTRIGGER#)
	
	p\Physics\LEVITATION_SPEED# = (0.2)*(p\Physics\UNDERWATERTRIGGER#)
	
	p\Physics\FLY_SPEED# = (0.54335+0.015*(p\SpeedLength#/6.0))*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERX#)
	
	
	p\Physics\COMMON_YTOPSPEED# = -25
	
	
	p\Physics\COMMON_YTOPSPEED#=p\Physics\COMMON_YTOPSPEED#*(p\Physics\UNDERWATERTRIGGERW#)
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	If p\Action=ACTION_HURT Or p\Action=ACTION_DIE Then
		p\Physics\COMMON_YACCELERATION# = 0.05375
	Else
		If p\Flags\DisallowCustomPhysics=False Then
			
			p\Physics\COMMON_YACCELERATION# = 0.08575
			
		Else
			p\Physics\COMMON_YACCELERATION# = 0.06125
		EndIf
		If p\Motion\Speed\y#>0 Then
			p\Physics\COMMON_YACCELERATION#=(p\Physics\COMMON_YACCELERATION#)*(p\Physics\UNDERWATERTRIGGERW#)
		Else
			p\Physics\COMMON_YACCELERATION#=(p\Physics\COMMON_YACCELERATION#+0.02*-(p\Motion\Speed\y#/9.5))*(p\Physics\UNDERWATERTRIGGERW#)
		EndIf
	EndIf
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	p\Physics\COMMON_SKIDDINGFACTOR# = 0.05*p\Physics\ICETRIGGER2#
	
	p\Physics\GLIDEFALL_SPEED# = -0.33*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERX#)
	
	p\Physics\FLUTTERFALL_SPEED# = -0.2*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERX#)
	
	If (Game\SpeedShoes=0 Or (Not(Player_IsPlayable(p)))) Then
		p\Physics\GLIDE_SPEED# = 2
	Else
		p\Physics\GLIDE_SPEED# = 4
	EndIf
	
	p\Physics\GLIDE_SPEED#=p\Physics\GLIDE_SPEED#*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERX#)
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	p\Physics\JUMP_STRENGTH_SPECIFIC# = (1.55+GetCharJumpStrength#(p\RealCharacter)*0.1)
	
	If (Game\SuperForm>0 And Player_IsPlayable(p)) Then p\Physics\JUMP_STRENGTH_SPECIFIC#=p\Physics\JUMP_STRENGTH_SPECIFIC#*1.15
	
	If p\StompBounceTimer>0 Then
		p\Physics\JUMP_STRENGTH#=p\Physics\JUMP_STRENGTH_SPECIFIC#*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERT#)+0.75
	Else
		p\Physics\JUMP_STRENGTH#=p\Physics\JUMP_STRENGTH_SPECIFIC#*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERT#)
	EndIf
	
	p\Physics\JUMP_STRENGTH_VARIABLE# = 1*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERT#)
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	p\Physics\STOMPFALL_SPEED#=-6
	
	p\Physics\FLOATFALL_SPEED#=-0.9113*(p\Physics\UNDERWATERTRIGGER#)
	
	p\Physics\DIEFALL_SPEED# = -1*p\Physics\UNDERWATERTRIGGER#
	
	p\Physics\BUZZFLYFALL_SPEED# = -0.15*p\Physics\UNDERWATERTRIGGER#
	
	p\Physics\SLOWGLIDE_SPEED# = 1.74*p\Physics\UNDERWATERTRIGGER#
	
	p\Physics\CLIMB_SPEED# = 1.1012
	
	p\Physics\HOVER_SPEED# = 1.1*p\Physics\UNDERWATERTRIGGER#
	
	p\Physics\HOVERFALL_SPEED# = -0.46*p\Physics\UNDERWATERTRIGGER#
	
	p\Physics\SPRINT_SPEED# = 3.08*p\Physics\UNDERWATERTRIGGER#
	
	p\Physics\BOUNCE_SPEED# = 2*(p\Physics\UNDERWATERTRIGGER#+p\Physics\UNDERWATERTRIGGERY#)
	
	p\Physics\WATERRUN_SPEED#=4.25
	If p\Motion\Ground=False Then p\Physics\WATERRUN_SPEED#=p\Physics\WATERRUN_SPEED#*0.8
	
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	If p\UnderwaterFeet=0 Then
		p\Physics\UNDERWATERTRIGGER# = 1
		p\Physics\UNDERWATERTRIGGERX# = 0
		p\Physics\UNDERWATERTRIGGERY# = 0
		p\Physics\UNDERWATERTRIGGERZ# = 0
		p\Physics\UNDERWATERTRIGGERT# = 0
		p\Physics\UNDERWATERTRIGGERW# = 1
	ElseIf p\UnderwaterFeet=1
		p\Physics\UNDERWATERTRIGGER# = 0.8
		p\Physics\UNDERWATERTRIGGERX# = 0.1
		p\Physics\UNDERWATERTRIGGERY# = 0.175
		p\Physics\UNDERWATERTRIGGERZ# = 0.3
		p\Physics\UNDERWATERTRIGGERT# = 0
		p\Physics\UNDERWATERTRIGGERW# = 0.4575
	EndIf
	
	If (p\IceFloorTimer>0 Or p\InkFloorTimer>0) Then
		If p\SpeedLength#>1.25 Then p\Physics\ICETRIGGER#=1.15 Else p\Physics\ICETRIGGER#=1.25
		
		p\Physics\ICETRIGGER2#=0.00135
		
		If (Not(Game\MachLock>0 Or Game\RunLock>0)) Then
			If p\SpeedLength#>1.25 Then p\Physics\ICETRIGGER3#=0.5 Else p\Physics\ICETRIGGER3#=0.7
		Else
			If p\Physics\ICETRIGGER3#<1 Then p\Physics\ICETRIGGER3#=p\Physics\ICETRIGGER3#+0.25*d\Delta Else p\Physics\ICETRIGGER3#=1
		EndIf
	Else
		If p\Physics\ICETRIGGER#>1 Then p\Physics\ICETRIGGER#=p\Physics\ICETRIGGER#-0.25*d\Delta Else p\Physics\ICETRIGGER#=1
		If p\Physics\ICETRIGGER2#<1 Then p\Physics\ICETRIGGER2#=p\Physics\ICETRIGGER2#+0.25*d\Delta Else p\Physics\ICETRIGGER2#=1
		If p\Physics\ICETRIGGER3#<1 Then p\Physics\ICETRIGGER3#=p\Physics\ICETRIGGER3#+0.25*d\Delta Else p\Physics\ICETRIGGER3#=1
	EndIf
	
	If p\SlowFloorTimer>0 Or p\InkFloorTimer>0 Then
		p\Physics\SLOWTRIGGER#=0.15
	Else
		If p\Physics\SLOWTRIGGER#<1 Then p\Physics\SLOWTRIGGER#=p\Physics\SLOWTRIGGER#+0.25*d\Delta Else p\Physics\SLOWTRIGGER#=1
	EndIf
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D