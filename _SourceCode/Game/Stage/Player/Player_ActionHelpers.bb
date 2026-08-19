Function Player_DebugStats(p.tPlayer)
	;debug info
	i=0
	i=i+1 : Game\Interface\DebugInfoText$[i]="Cheated: "+Game\Cheater
	i=i+1 : Game\Interface\DebugInfoText$[i]="FPS: "+Game\Others\Fps
	
	If p\Motion\Ground=True Then
		i=i+1 : Game\Interface\DebugInfoText$[i]="On ground"
	Else
		i=i+1 : Game\Interface\DebugInfoText$[i]="Off ground"
	EndIf
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Turning sharpness: "+p\Physics\TURNING_SHARPNESS#
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Action: "+p\Action
	i=i+1 : Game\Interface\DebugInfoText$[i]="Animation: "+p\Animation\Animation
	i=i+1 : Game\Interface\DebugInfoText$[i]="Speedlength: "+p\SpeedLength#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Direction: "+p\Animation\Direction#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Rotation: "+p\Rotation#
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Ideal Score: "+Game\IdealScore
	i=i+1 : Game\Interface\DebugInfoText$[i]="Ideal Time: "+Game\IdealTime/secs#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Max Time: "+Game\LimitTime/secs#
	
	i=i+1 : Game\Interface\DebugInfoText$[i]=""
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Position X: "+EntityX#(p\Objects\Entity)
	i=i+1 : Game\Interface\DebugInfoText$[i]="Position Y: "+EntityY#(p\Objects\Entity)
	i=i+1 : Game\Interface\DebugInfoText$[i]="Position Z: "+EntityZ#(p\Objects\Entity)
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Rotation X: "+EntityPitch#(p\Objects\Entity)
	i=i+1 : Game\Interface\DebugInfoText$[i]="Rotation Y: "+EntityYaw#(p\Objects\Entity)
	i=i+1 : Game\Interface\DebugInfoText$[i]="Rotation Z: "+EntityRoll#(p\Objects\Entity)
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Speed X: "+p\Motion\Speed\x#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Speed Y: "+p\Motion\Speed\y#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Speed Z: "+p\Motion\Speed\z#
	
	
	
	i=i+1 : Game\Interface\DebugInfoText$[i]=""
	
	
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="XZ Acceleration: "+p\Physics\COMMON_XZACCELERATION#
	i=i+1 : Game\Interface\DebugInfoText$[i]="XZ Deceleration: "+p\Physics\COMMON_XZDECELERATION#
	i=i+1 : Game\Interface\DebugInfoText$[i]="XZ Top Speed: "+p\Physics\COMMON_XZTOPSPEED# 
	i=i+1 : Game\Interface\DebugInfoText$[i]="XZ Max Speed: "+p\Physics\COMMON_XZMAXSPEED#
	i=i+1 : Game\Interface\DebugInfoText$[i]="XZ MinSpeed: "+p\Physics\COMMON_XZMINSPEED#
	
	i=i+1 : Game\Interface\DebugInfoText$[i]=""
	
	i=i+1 : Game\Interface\DebugInfoText$[i]="Y Acceleration: "+p\Physics\COMMON_YACCELERATION#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Y Top Speed: "+p\Physics\COMMON_YTOPSPEED#
	i=i+1 : Game\Interface\DebugInfoText$[i]="Jump Strength: "+p\Physics\JUMP_STRENGTH_SPECIFIC#
End Function 



; =========================================================================================================
	; =========================================================================================================
Function Player_ActuallyDrift(p.tPlayer)
	
	
	If Game\TwoDLock=0 And (Not(p\Action=ACTION_DEBUG Or p\Action=ACTION_DRIFT Or p\Action=ACTION_GRIND Or p\Action=ACTION_DIE Or p\Action=ACTION_HURT Or p\Action=ACTION_BUMPED Or p\HasVehicle>0 Or p\Action=ACTION_FREEZE Or p\Action=ACTION_SINK)) And p\Motion\Ground And (p\Flags\CanClimb=False) Then
		If Player_IsPlayable(p) And Input\Pressed\ActionDrift Then Player_Action_Drift_Initiate(p)
	EndIf
End Function
Function Player_FlyHeight(p.tPlayer) 
	
	If EntityY(p\Objects\Entity)>p\FlyDistanceLimit+200 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+198 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.1)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+196 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.2)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+194 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.3)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+192 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.4)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+190  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.5)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+188 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.6)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+186 Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.7)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+184  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.8)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+182  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*0.9)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+180  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+178  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.1)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+176  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.2)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+174  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.3)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+172  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.4)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+170  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.5)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+168  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.6)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+166  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.7)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+164  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.8)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+162  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*1.9)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+160  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+158  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.1)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+156  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.2)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+154  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.3)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+152  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.4)
	ElseIf EntityY(p\Objects\Entity)>p\FlyDistanceLimit+150  Then
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.5)
	Else
		Player_SetSpeedY(p,p\Physics\FLY_SPEED#*2.5)
	EndIf
End Function
Function Player_UpdateEffects(p.tPlayer,d.tDeltaTime)
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
	If Menu\Settings\Shadows#=2 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
		If p\No#<0 Then
			Update_CircleShadow(p\Objects\ShadowCircle, p\Objects\Mesh, pp(1)\Objects\Camera\Entity)
		Else
			Update_CircleShadow(p\Objects\ShadowCircle, p\Objects\Mesh, p\Objects\Camera\Entity)
		EndIf
	EndIf
	
	If SHOPITEM_ENABLED(SHOPMENU_COSMETIC,COSMETIC_INVIS)=1 Then EntityAlpha(p\Objects\Mesh,0)
	
	
End Function

Function Player_Create_RingLoss(p.tPlayer)
	rings = Game\Gameplay\Rings
	
	If SHOPITEM_ENABLED(SHOPMENU_ABILITY,ABILITY_SPARECHANGE)=1 Then
		If rings>75 Then rings=75
	Else
		If rings>50 Then rings=50
	EndIf
	
	
	
	Repeat
		Object_SpewRing_Create.tObject(p\Objects\Position\x#, p\Objects\Position\y#+1.5, p\Objects\Position\z#, Rnd(-0.4, 0.4), Rnd(0.6, 1.2), Rnd(-0.4, 0.4))
		rings = rings - 1
	Until rings <= 0
	
	
	Game\Gameplay\Rings = Game\Gameplay\Rings-50
				
				
	If Game\Gameplay\Rings<0 Then Game\Gameplay\Rings=0
	Game\HurtWithoutShield = 0
	EmitSmartSound(Sound_RingLoss,p\Objects\Entity)
End Function

Function Player_GainExtraLife(p.tPlayer)
	
;	Game\Interface\LifeTimer=4*secs#
;	Game\Interface\LifeScale#=1
;	Game\Interface\LifeAlpha#=0
	MonitorIcon_Draw(2) 
	Game\Gameplay\Lives=Game\Gameplay\Lives+1
	Game\Gameplay\GainedLives=Game\Gameplay\GainedLives+1
	If Not(ChannelPlaying(Game\Channel_1Up)) Then Game\Channel_1Up=PlaySmartSound(Sound_1Up)
End Function




Function Player_DetermineFlags(p.tPlayer)
	
	; Determine if superable
	Select p\Action
		Case ACTION_JUMP,ACTION_HOP,ACTION_JUMPFALL
			If Menu\Stage>0 And Game\SuperForm=0 And Player_IsPlayable(p) And Game\Gameplay\Rings>=50 And (Not(Game\Interface\ControlTipPickUpTimer>0)) Then 
				p\Flags\CanSuperTransform=True
			Else
				p\Flags\CanSuperTransform=False
			EndIf
		Default
			p\Flags\CanSuperTransform=False
	End Select
	
	If UNLOCKEDSUPERS=0 Then p\Flags\CanSuperTransform=False
	
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
	If p\Flags\StronglyAttacking Or p\Action=ACTION_JUMP Or p\Action=ACTION_JUMPDASH Or p\Action=ACTION_HOP Or p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT Or p\Action=ACTION_HOMING Or p\Action=ACTION_BUMPED Then
		p\Flags\Attacking=True
		If Not(p\Flags\TargeterTimer>0) Then p\Flags\Targeter=0
	Else
		p\Flags\Attacking=False
		p\Flags\Targeter=0
	EndIf
	
	; Determine if attacking strongly
	If p\Action=ACTION_STOMP Or p\TrailBlazer=1 Or p\Action=ACTION_DEMODASH Or p\Flags\Boosting Or p\Action=ACTION_LIGHTATTACK Or (p\Action=ACTION_GLIDE And Menu\ChaoGarden=0) Or (p\Action=ACTION_SPRINT And (Not(p\Character=CHAR_ROU))) Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST Or p\Action=ACTION_SWIPE Or p\Action=ACTION_UPPERCUT Or p\Action=ACTION_CLAW Or (p\Action=ACTION_THROW And (p\Character=CHAR_KNU Or p\Character=CHAR_HBO Or p\Character=CHAR_STO Or p\Character=CHAR_MET Or p\Character=CHAR_MT3)) Or (p\Action=ACTION_BOARD And (p\GrindTurn=2 Or p\SpeedLength#>1)) Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_BOARDFALL Or p\Action=ACTION_BOARDDRIFT Or p\Action=ACTION_BOARDTRICK Or p\Action=ACTION_GLIDER Or ((p\Action=ACTION_CAR Or p\Action=ACTION_CARFALL Or p\Action=ACTION_CARDRIFT) And (p\SpeedLength#>1 Or p\Motion\Speed\y#>1)) Or p\Action=ACTION_BELLYFLOP Or p\Action=ACTION_TORNADO Then
		p\Flags\StronglyAttacking=True
	Else
		p\Flags\StronglyAttacking=False
	EndIf
	
	; Determine if doing air attack
	If (p\Action=ACTION_GLIDE And Menu\ChaoGarden=0) Or p\Action=ACTION_PUNCH Or p\Action=ACTION_THRUST Or p\Action=ACTION_SWIPE Or (p\Action=ACTION_SPRINT And (Not(p\Character=CHAR_ROU))) Or p\Action=ACTION_CLAW Or p\Action=ACTION_UPPERCUT Then
		p\Flags\InAirAttack=True
	Else
		p\Flags\InAirAttack=False
	EndIf
	
	; Determine if doing targeter attack
	Select p\Action
		Case ACTION_PUNCH,ACTION_THRUST,ACTION_SPRINT,ACTION_UPPERCUT,ACTION_SWIPE,ACTION_CLAW:
			Select p\Character
				Case CHAR_ROU
					If p\Action=ACTION_SPRINT Then p\Flags\InTargeterAttack=False
				Case CHAR_RAY,CHAR_AMY
					p\Flags\InTargeterAttack=False
				Default
					p\Flags\InTargeterAttack=True
			End Select
					
		Default:
			p\Flags\InTargeterAttack=False
	End Select
	
	; Determine if doing targeter air attack
	Select p\Action
		Case ACTION_THRUST,ACTION_SPRINT,ACTION_UPPERCUT,ACTION_SWIPE,ACTION_CLAW:
			Select p\Character
				Case CHAR_ROU,CHAR_SON
					If p\Action=ACTION_SPRINT Then p\Flags\InTargeterAirAttack=False
				Default
					If p\Motion\Ground=False Then p\Flags\InTargeterAirAttack=True Else p\Flags\InTargeterAirAttack=False
			End Select
		Default:
			p\Flags\InTargeterAirAttack=False
	End Select
	
	; Determine if doing spin jump attack
	If p\Action=ACTION_JUMP Or p\Action=ACTION_HOP Then
		p\Flags\InJumpAttack=True
	Else
		p\Flags\InJumpAttack=False
	EndIf
	
	; Determine if can stomp
	If p\Motion\Ground=False Then
		If p\No#=1 Or pp(1)\Flags\CanStomp Then
			Select p\Action
				Case ACTION_DEBUG,ACTION_DIEHURT,ACTION_PANEL2,ACTION_CHAORACE,ACTION_VICTORY,ACTION_VICTORYHOLD,ACTION_STOMP,ACTION_HURT,ACTION_DIE,ACTION_GRABBED,ACTION_CANNON,ACTION_CANNON2,ACTION_CARRYJUMP,ACTION_CARRYTHROWN,ACTION_SPIRIT,ACTION_HOLD,ACTION_BOARD,ACTION_BOARDJUMP,ACTION_BOARDFALL,ACTION_BOARDTRICK,ACTION_TRANSFORM,ACTION_SKYDIVE,ACTION_GLIDER,ACTION_FREEZE,ACTION_SINK,ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT,ACTION_BELLYFLOP,ACTION_TORNADO:
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
	
	; Determine if stomping
	If p\Action=ACTION_STOMP Then
		p\Flags\Stomping=1
	Else
		p\Flags\Stomping=0
	EndIf
End Function

Function Player_ActuallyStomp(p.tPlayer)
	
	If p\Flags\CanStomp And Player_IsPlayable(p) Then
		If Input\Pressed\ActionRoll Then Player_Action_Stomp_Initiate(p)
	EndIf
End Function

Function DoesCharSpin(char)
	
	
	If IsCharMod(char) Then
		If MODCHARS_SPINS(char-CHAR_MOD1+1)=1 Then 
			Return True
		Else
			Return False
		EndIf
	Else
		Select char
			Case CHAR_EGR,CHAR_GAM,CHAR_AMY,CHAR_BET,CHAR_ROU,CHAR_ESP,CHAR_INF,CHAR_AMY,CHAR_OME,CHAR_BLA,CHAR_MET,CHAR_BEA,CHAR_SIL:
				Return False
			Default
				Return True
		End Select
	EndIf 
	
	
End Function 

Function Player_CanCharSpin(p.tPlayer)
	
	If SHOPITEM_ENABLED(SHOPMENU_ABILITY,ABILITY_SPIN) Then 
		Return True
	Else
		If IsCharMod(p\RealCharacter) Then
			If MODCHARS_SPINS(p\RealCharacter-CHAR_MOD1+1)=1 Then 
				Return True
			Else
				Return False
			EndIf
		Else
			Select p\Character
				Case CHAR_EGR,CHAR_GAM,CHAR_AMY,CHAR_BET,CHAR_ROU,CHAR_ESP,CHAR_INF,CHAR_AMY,CHAR_OME,CHAR_BLA,CHAR_MET,CHAR_BEA,CHAR_SIL:
					Return False
				Default
					Return True
			End Select
		EndIf 
	EndIf
End Function 
Function Player_ChangeLeader(p.tPlayer)
	If p\No#=1 And Game\Interface\DebugPlacerOn=0  Then
		If p\MateChangeTimer>0 Then
			If EntityDistance(p\Objects\Entity,pp(1)\Objects\FollowerPlace[p\No#-1])>2.5+p\SpeedLength# Then
				If p\Motion\Ground And Menu\MissionMach=0 And Game\SpeedShoes=0 And Abs(p\Rotation#)<30 Then
					If Sqr#( Abs(p\Objects\Position\x#-EntityX(pp(1)\Objects\FollowerPlace[p\No#-1]))^2+Abs(p\Objects\Position\z#-EntityZ(pp(1)\Objects\FollowerPlace[p\No#-1]))^2 )>5 Then
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
		
		If p\Underwater=0 And (Not(p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_BUMPED Or p\Action=ACTION_HOLD Or p\Action=ACTION_CARRY Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_CARRYTHROWN Or p\Action=ACTION_CLIMB Or p\HasVehicle=2)) Then p\Flags\MayChangeCharacter=True Else p\Flags\MayChangeCharacter=False
		
		If Input\Pressed\Change And Menu\Members>1 And (Not(p\JustChangedMateTimer>0)) Then
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
			If p\ForceBeingAbleToChangeLeaderTimer>0 Or (p\Motion\Ground And (Not(p\JustLandedTimer>0)) And (Not(p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_STOMP Or p\Action=ACTION_TORNADO))) Then ChangeCharacter(p\NewCharacter)
		EndIf
	EndIf
End Function
Function Player_SetPosition(p.tPlayer,x#,y#,z#,direction#)
	
	EntityType(p\Objects\Entity, COLLISION_NONE)
	RotateEntity p\Objects\Entity,0,0,0,1
	Player_Align(p)
	PositionEntity p\Objects\Entity,x#,y#,z#,1
	PositionEntity p\Objects\Mesh,x#,y#,z#,1
	
	If Menu\Stage<>0 And p\No#=1 Then
		PositionEntity cam\Entity, x#, y#, z#, 1
		PositionEntity cam\Lock\PosMesh, x#, y#, z#, 1
		cam\Position\x#=x# : cam\Position\y#=y# : cam\Position\z#=z#
	EndIf
	
	p\Motion\Speed\x = 0
	p\Motion\Speed\y = 0
	p\Motion\Speed\z = 0
	p\Animation\Direction#=direction#+180

	If Menu\Stage<>0 And p\No#=1 Then
		If direction#<0 Then direction#=direction#+360
		If direction#>360 Then direction#=direction#-360
		cam\Rotation\y#=direction#
		cam\TargetRotation\y#=direction#
	EndIf
	If (Not(p\Action=ACTION_DEBUG)) Then
		EntityType(p\Objects\Entity, COLLISION_PLAYER)
	Else
		If Game\Interface\DebugCollision=1 Then
			EntityType(p\Objects\Entity,COLLISION_PLAYER)
		EndIf
	EndIf
	
	Select p\No#
		Case 1:
			For ch.tCheese=Each tCheese
				EntityType(ch\Entity,COLLISION_NONE) : PositionEntity ch\Entity, p\Objects\Position\x#, p\Objects\Position\y#+7, p\Objects\Position\z#, 1
				PositionEntity ch\Mesh, p\Objects\Position\x#, p\Objects\Position\y#+7, p\Objects\Position\z#, 1 : EntityType(ch\Entity,COLLISION_OBJECT2_GOTHRU)
			Next
			
			For f.tFroggy=Each tFroggy
				EntityType(f\Entity,COLLISION_NONE) : PositionEntity f\Entity, p\Objects\Position\x#, p\Objects\Position\y#+1, p\Objects\Position\z#, 1
				PositionEntity f\Mesh, p\Objects\Position\x#, p\Objects\Position\y#+1, p\Objects\Position\z#, 1 : EntityType(f\Entity,COLLISION_OBJECT2_GOTHRU)
				f\FroggyFallTimer=0
				MoveEntity f\Entity, 10, 0, -10 : MoveEntity f\Mesh, 10, 0, -10
			Next
		Case 2,3:
			MoveEntity pp(p\No#)\Objects\Entity, Player_ReturnFollowerPosition(p,1,p\No#), 0, 0;Player_ReturnFollowerPosition(p,2,p\No#)
	End Select
	
	If p\Objects\Position\y# < Game\Stage\Properties\WaterLevel Then p\Underwater=1 Else p\Underwater=0
	p\WaterSplash=p\Underwater
	
	
	
	Player_SetPositionOfPickedUpObj(p)
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_UpdatePosition(p.tPlayer)
		p\Objects\Position\x#=EntityX(p\Objects\Entity,1)
		p\Objects\Position\y#=EntityY(p\Objects\Entity,1)
		p\Objects\Position\z#=EntityZ(p\Objects\Entity,1)
	End Function

	; =========================================================================================================
	; =========================================================================================================
	
	Function Player_SetPositionOfPickedUpObj(p.tPlayer)
		Player_UpdatePosition(p)
		If p\ObjPickUp>0 Then
			If p\ObjPickUpTarget\HasGravity=False Then
				EntityType(p\ObjPickUpTarget\Entity,0)
				PositionEntity p\ObjPickUpTarget\Entity, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1
			Else
				EntityType(p\ObjPickUpTarget\Pivot,0)
				PositionEntity p\ObjPickUpTarget\Pivot, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1
			EndIf
			Select p\ObjPickUpTarget\ObjType
				Case OBJTYPE_OMOCHAO:
					EntityType(p\ObjPickUpTarget\Pivot,COLLISION_OBJECT2)
				Case OBJTYPE_CHAO:
					EntityType(p\ObjPickUpTarget\Entity,COLLISION_OBJECT2_GOTHRU)
					EntityType(p\ObjPickUpTarget\ChaoObj\targetcc\Pivot,0)
					PositionEntity p\ObjPickUpTarget\ChaoObj\targetcc\Pivot, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1
					EntityType(p\ObjPickUpTarget\ChaoObj\targetcc\Pivot,COLLISION_OBJECT2)
				Default:
					If p\ObjPickUpTarget\HasGravity=False Then
						EntityType(p\ObjPickUpTarget\Entity,COLLISION_OBJECT2_GOTHRU)
					Else
						EntityType(p\ObjPickUpTarget\Pivot,COLLISION_OBJECT2_GOTHRU)
					EndIf
			End Select
		EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Spawn(x#,y#,z#,direction#)

		For p.tPlayer=Each tPlayer
		If Player_IsPlayable(p) Then
			Player_SetPosition(p,x#,y#,z#,direction#)

			If p\Objects\Position\y# < Game\Stage\Properties\WaterLevel Then p\Underwater=1 Else p\Underwater=0
			p\WaterSplash=p\Underwater
		EndIf
		Next

	End Function
Function Player_Stop(p.tPlayer)
	Player_SetSpeed(p,0)
	Player_SetSpeedY(p,0)
End Function
	; =========================================================================================================
	; =========================================================================================================

	Function Player_SetSpeed(p.tPlayer,speed#,onlyifsmaller=False)

		If onlyifsmaller=False Then
			p\Motion\Speed\x# = Sin(p\Animation\Direction#)*(speed#)
			p\Motion\Speed\z# = -Cos(p\Animation\Direction#)*(speed#)
		Else
			If p\SpeedLength#<speed# Then Player_SetSpeed(p,speed#,False)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_SetSpeedY(p.tPlayer,speed#,onlyifsmaller=False)

		If onlyifsmaller=False Then
			p\Motion\Speed\y# = speed#
		Else
			If p\Motion\Speed\y#<speed# Then Player_SetSpeedY(p,speed#,False)
		EndIf

	End Function

	Function Player_SetSpeedYAlways(p.tPlayer,speed#,onlyifsmaller=False)

		If onlyifsmaller=False Then
			p\Motion\Speed\y# = speed#
		Else
			If p\Motion\Speed\y#<speed# Then Player_SetSpeedYAlways(p,speed#,False)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_SetSpeedYUpDown(p.tPlayer,speed#,timer=0)

		If Not(timer>0) Then
			If p\Motion\Speed\y#>speed# Then Player_SetSpeedY(p,speed#)
			If p\Motion\Speed\y#<-speed# Then Player_SetSpeedY(p,-speed#)
		EndIf

	End Function


	Function Player_SetSpeedYUpDownDiff(p.tPlayer,speedup#,speeddown#,timer=0)

		If Not(timer>0) Then
			If p\Motion\Speed\y#>speedup# Then Player_SetSpeedY(p,speedup#)
			If p\Motion\Speed\y#<speeddown# Then Player_SetSpeedY(p,speeddown#)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_IsPlayable(p.tPlayer)
		If p\No#>0 Then Return True Else Return False
	End Function

	Function Player_IsSoundable(p.tPlayer)
		If p\No#=1 Or p\No#<0 Then Return True Else Return False
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Follow(p.tPlayer)

		If Game\Victory=0 And (Not(pp(1)\Action=ACTION_TORNADO)) Then
			p\TornadoStance=0
			If (pp(1)\Invisibility=0 Or pp(1)\MateChangeTimer>0) And (Not(pp(1)\Action=ACTION_CANNON)) Then
				If (Not(Game\RunLock>0)) And (Abs(pp(1)\Rotation#)<40 Or p\Action=ACTION_CHARGE) Then
					If (Not(pp(1)\Action=ACTION_DIE Or pp(1)\Action=ACTION_HURT)) Then
						If EntityDistance(p\Objects\Entity,pp(1)\Objects\FollowerPlace[p\No#-1])>2.5 Then
							If Sqr#( Abs(p\Objects\Position\x#-EntityX(pp(1)\Objects\FollowerPlace[p\No#-1]))^2+Abs(p\Objects\Position\z#-EntityZ(pp(1)\Objects\FollowerPlace[p\No#-1]))^2 )>5 Then
								p\Animation\Direction#=DeltaYaw#(p\Objects\Entity,pp(1)\Objects\FollowerPlace[p\No#-1])-180
								If pp(1)\SpeedLength#<0.5 Then
									Player_SetSpeed(p,0.5)
								Else
									Player_SetSpeed(p,pp(1)\SpeedLength#)
								EndIf
							Else
								p\Animation\Direction#=pp(1)\Animation\Direction#
							EndIf
						ElseIf EntityDistance(p\Objects\Entity,pp(1)\Objects\FollowerPlace[p\No#-1])<0.5 Then
							Player_SetSpeed(p,0)
						EndIf
					EndIf

					If EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)<50 Then
						If (pp(1)\Action=ACTION_UP Or pp(1)\Action=ACTION_FWD Or pp(1)\Action=ACTION_FLOAT Or pp(1)\Action=ACTION_SKYDIVE) Then
							Player_FollowerTakeAction(p)
						EndIf
					EndIf

					If pp(1)\Rotation#<5 And pp(1)\Rotation#>-5 And (Not(pp(1)\Action=ACTION_JUMP Or pp(1)\Action=ACTION_HOP Or pp(1)\Action=ACTION_CANNON Or pp(1)\Action=ACTION_CANNON2 Or pp(1)\Action=ACTION_DIE Or pp(1)\Action=ACTION_HURT)) And pp(1)\Flags\InJumpAction=False Then
						If (Not(p\Action=ACTION_JUMP Or p\Action=ACTION_HOP Or p\Action=ACTION_UP Or p\Action=ACTION_FWD Or p\Action=ACTION_FLOAT Or p\Action=ACTION_CLIMB Or (p\Action=ACTION_STOMP And EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)<40))) Then
							If ( Abs(p\Objects\Position\y#-EntityY(pp(1)\Objects\FollowerPlace[p\No#-1]))>5 And Abs(p\Objects\Position\y#-EntityY(pp(1)\Objects\FollowerPlace[p\No#-1]))<75 ) Then
								Player_ConvertGroundToAir(p)
								p\Motion\Ground = False
								If pp(1)\Motion\Ground=False And p\Objects\Position\y#<pp(1)\Objects\Position\y# And (p\Motion\Ground=False Or EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)>40) Then
									If pp(1)\Motion\Speed\y#<0.5 Then
										p\Motion\Speed\y#=0.5
									Else
										p\Motion\Speed\y#=pp(1)\Motion\Speed\y#
									EndIf
								EndIf
							EndIf
						EndIf
					EndIf

					If EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)>150 Then
						If pp(1)\Motion\Ground And (Not(pp(1)\Action=ACTION_DIE Or pp(1)\Action=ACTION_CLIMB)) And (Not(Game\ControlLock>0)) And Abs(pp(1)\Rotation#<=40) Then Player_FollowerSpawn(p)
					EndIf

				ElseIf Game\RunLock>0 And EntityDistance(p\Objects\Entity,pp(1)\Objects\Entity)<35 Then
					p\Animation\Direction#=pp(1)\Animation\Direction#
					Player_SetSpeed(p,pp(1)\SpeedLength#)
				EndIf
			Else
				Player_SetSpeed(p,0)
				If pp(1)\Invisibility=0 Then
					p\Animation\Direction#=pp(1)\Animation\Direction#
				Else
					p\Animation\Direction#=(DeltaYaw#(p\Objects\Entity,pp(1)\Objects\Entity) - 180)
				EndIf
				If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FALL
			EndIf
		ElseIf (pp(1)\Action=ACTION_TORNADO) Then
			If p\TornadoStance=0 Then
				Select(Rand(1,2))
					Case 1: p\TornadoStance=-1
					Case 2: p\TornadoStance=1
				End Select
			EndIf
			p\Action=ACTION_TORNADO
			PositionEntity p\Objects\Entity,pp(1)\Objects\Position\x#,pp(1)\Objects\Position\y#,pp(1)\Objects\Position\z#,1
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_FollowerSpawn(p.tPlayer,changespawn=False)
		If Game\Interface\DebugPlacerOn=0 Then
			EntityType(p\Objects\Entity, COLLISION_NONE)
			RotateEntity p\Objects\Entity,0,0,0,1
			Player_Align(p)
			PositionEntity p\Objects\Entity,pp(1)\Objects\Position\x#,pp(1)\Objects\Position\y#,pp(1)\Objects\Position\z#,1
			PositionEntity p\Objects\Mesh,pp(1)\Objects\Position\x#,pp(1)\Objects\Position\y#,pp(1)\Objects\Position\z#,1
			p\Animation\Direction#=pp(1)\Animation\Direction#

			p\Motion\Speed\x = 0
			p\Motion\Speed\y = 0
			p\Motion\Speed\z = 0
			EntityType(p\Objects\Entity, COLLISION_PLAYER)
			MoveEntity p\Objects\Entity, Player_ReturnFollowerPosition(p,1,p\No#), 2.5, Player_ReturnFollowerPosition(p,2,p\No#)-pp(1)\SpeedLength#
			p\Underwater=pp(1)\Underwater
			p\WaterSplash=pp(1)\Underwater

			If changespawn Then
				If p\HasVehicle=0 Then p\Action=ACTION_COMMON
				p\Motion\Ground=True
				Player_SetSpeed(p,pp(1)\SpeedLength#)
			EndIf
		EndIf
	End Function

	Function Player_FollowerGoalSpawn(p.tPlayer)
		If Game\Interface\DebugPlacerOn=0 Then
			goalpos = CreatePivot()
			PositionEntity goalpos,pp(1)\Objects\Position\x#,pp(1)\Objects\Position\y#+7,pp(1)\Objects\Position\z#,1
			RotateEntity goalpos, 0, pp(1)\Animation\Direction#-180, 0, 1
			Select p\No#
				Case 2: MoveEntity goalpos, (10+1*pp(1)\ScaleFactor#+5*p\ScaleFactor#), 0, -10
				Case 3: MoveEntity goalpos, -(10+1*pp(1)\ScaleFactor#+5*p\ScaleFactor#), 0, -10
			End Select
			EntityType(p\Objects\Entity, COLLISION_NONE)
			PositionEntity p\Objects\Entity,EntityX(goalpos),EntityY(goalpos),EntityZ(goalpos),1
			PositionEntity p\Objects\Mesh,EntityX(goalpos),EntityY(goalpos),EntityZ(goalpos),1
			p\Animation\Direction#=pp(1)\Animation\Direction#
			FreeEntity goalpos

			p\Motion\Speed\x = 0
			p\Motion\Speed\y = 0
			p\Motion\Speed\z = 0
			EntityType(p\Objects\Entity, COLLISION_PLAYER)
			p\Underwater=pp(1)\Underwater
			p\WaterSplash=pp(1)\Underwater
		EndIf
	End Function

	Function Player_ReassignMember(newleader)
		If Menu\Members>1 Then
			If EntityDistance(pp(1)\Objects\Entity,pp(2)\Objects\Entity)>40 Then Player_FollowerSpawn(pp(2),True)
			If Menu\Members>2 Then
				If EntityDistance(pp(1)\Objects\Entity,pp(3)\Objects\Entity)>40 Then Player_FollowerSpawn(pp(3),True)
			EndIf
		EndIf

		; prepare mate change for leader
		follower=CreatePivot()
		PositionEntity follower, EntityX(pp(1)\Objects\Mesh), EntityY(pp(1)\Objects\Mesh), EntityZ(pp(1)\Objects\Mesh), 1
		RotateEntity follower, EntityPitch(pp(1)\Objects\Mesh), EntityYaw(pp(1)\Objects\Mesh), EntityRoll(pp(1)\Objects\Mesh), 1

		; assign
		Select Menu\Members
			Case 3:
				Select newleader
					Case 1: pp(1)\No#=3 : pp(2)\No#=1 : pp(3)\No#=2
					Case 2: pp(1)\No#=2 : pp(2)\No#=1 : pp(3)\No#=3
					Case 3: pp(1)\No#=3 : pp(2)\No#=2 : pp(3)\No#=1
				End Select
			Case 2: pp(1)\No#=2 : pp(2)\No#=1
		End Select

		; assign type to global types
		For p.tPlayer = Each tPlayer
			If Player_IsPlayable(p) Then pp(p\No#)=p
		Next
						
		; bind the camera to member		
		Camera_Bind(cam,pp(1))

		; activate mate change for leader
		PositionEntity pp(1)\Objects\FollowerPlace[1-1], EntityX(follower), EntityY(follower), EntityZ(follower), 1
		RotateEntity pp(1)\Objects\FollowerPlace[1-1], EntityPitch(follower), EntityYaw(follower), EntityRoll(follower), 1
		FreeEntity follower
		pp(1)\MateChangeTimer=1*secs#
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_FollowerTakeAction(p.tPlayer)

		Player_ConvertGroundToAir(p)
		p\Motion\Ground = False
		p\Action=pp(1)\Action
		Player_SetSpeed(p,pp(1)\SpeedLength#)
		If p\Objects\Position\y#<pp(1)\Objects\Position\y# Then p\Motion\Speed\y#=pp(1)\Motion\Speed\y# Else p\Motion\Speed\y#=0

		If p\Action=ACTION_SKYDIVE Then p\Animation\Direction#=pp(1)\Animation\Direction#

	End Function
Function Player_CanHomingAttack(p.tPlayer)
	If IsCharMod(p\RealCharacter) Then
		Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
			Case 0
				Return False
			Case 1
				Return True
		End Select
	Else
		Select p\RealCharacter
			Case CHAR_SON,CHAR_SHA,CHAR_SHN,CHAR_ESP,CHAR_CRE,CHAR_MET,CHAR_MIG
				Return True
			Default
				Return False
		End Select
	EndIf
	
End Function 
	; =========================================================================================================
	; =========================================================================================================

Function Player_JumpActionInteract(p.tPlayer,forcetype=0)
	
	p\Flags\CanBlazeTrick=True
	
	Select forcetype
		Case 0:
			p\JumpTimer=0 : p\JumpDashedOnce=0
			If p\Flags\InAirAttack=False Or p\Flags\InJumpAction Then
				If p\Flags\InJumpAction=False And p\HasVehicle=0 Then
					Select p\Action
						Case ACTION_JUMP,ACTION_ROLL
							p\Flags\ShouldBounce=True
						Default
							p\Flags\ShouldBounce=False
					End Select
					
					Select p\Action
						Case ACTION_HOMING
							p\Action=ACTION_POSTHOM
						Default
							p\Action=ACTION_JUMP : p\JumpMayRiseTimer=1.5*secs#
					End Select
					
				Else
					If Not(p\Action=ACTION_LEVITATE Or p\Action=ACTION_HOVER) Then p\JumpActionRestrictTimer=0.5*secs#
				EndIf
			EndIf
			If Not(p\Action=ACTION_CARRY) Then 
				
				If p\Motion\Speed\y#<-1.35 And p\Flags\ShouldBounce Then
					p\Motion\Speed\y#=((Abs(p\Motion\Speed\y#))*p\Physics\UNDERWATERTRIGGER#)
				Else
					p\Motion\Speed\y#=2
				EndIf
				
			EndIf 
		Case 1:
			p\JumpTimer=0 : p\JumpDashedOnce=0
			p\Action=ACTION_JUMP : p\JumpMayRiseTimer=1.5*secs#
			p\Motion\Speed\y#=2*p\Physics\UNDERWATERTRIGGER#
		Case 2:
			If Not(p\Action=ACTION_STOMP) Then Player_JumpActionInteract(p,0)
	End Select
	Player_ResetAirRestrictionStuff(p)
	p\BeenInTheAirTimer=0
End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_JumpActions(p.tPlayer,force=False)
	If Not(p\No#=1 Or p\No#<0) Then Return
	
	If SHOPITEM_ENABLED(SHOPMENU_SKILL,SKILL_DOUBLEJUMP) Then
		If (Player_IsPlayable(p) And Input\Pressed\ActionSkillX) Then
			Player_Action_DoubleJumpSkill_Initiate(p)
		EndIf
	EndIf
	
	
	If ((Player_IsPlayable(p) And Input\Pressed\ActionJump) Or force) Then
		If Menu\Stage>0 Then
			If IsCharMod(p\RealCharacter) Then
				Select MODCHARS_JUMPACTION(p\RealCharacter-CHAR_MOD1+1)
					Case JUMPACTION_DASH
						Player_Action_JumpDash_Initiate(p)
					Case JUMPACTION_DOUBLE
						Player_Action_DoubleJump_Initiate(p)
					Case JUMPACTION_FLY,JUMPACTION_SOAR
						Player_Action_Fly_Initiate(p)
					Case JUMPACTION_GLIDE
						Player_Action_Glide_Initiate(p)
					Case JUMPACTION_THWOK
						Player_Action_JumpDash_Initiate(p)
					Case JUMPACTION_LEVITATE
						Player_Action_Levitate_Initiate(p)
					Case JUMPACTION_HOVER
						Player_Action_Hover_Initiate(p)
						
					Case 0:
						
					Default
						Player_Action_JumpDash_Initiate(p)
				End Select
			Else
				Select p\Character
					Case CHAR_SON,CHAR_SHA,CHAR_MET,CHAR_SHN,CHAR_ESP
						Player_Action_JumpDash_Initiate(p)
					Case CHAR_TAI,CHAR_CRE,CHAR_CHA,CHAR_TDL,CHAR_WAV,CHAR_EGR,CHAR_RAY,CHAR_ROU:
						Player_Action_Fly_Initiate(p)
					Case CHAR_KNU
						Player_Action_Glide_Initiate(p)
					Case CHAR_AMY,CHAR_BLA,CHAR_MIG:
						Player_Action_DoubleJump_Initiate(p)
					Case CHAR_INF,CHAR_SIL:
						Player_Action_Levitate_Initiate(p)
					Case CHAR_GAM,CHAR_OME
						Player_Action_Hover_Initiate(p)
				End Select
			EndIf
		EndIf
	EndIf
	Player_SkillActions(p)
End Function
Function Player_DealTrails(p.tPlayer)
		; Deal trails
	If p\No#=1 And (p\Action=ACTION_FLY And p\JumpDashTimer>0) Or p\QuickstepTimer>0 Or p\Action=ACTION_HOMING Or (Game\MachLock>0 Or Game\SpeedShoes=1 Or (p\Action=ACTION_SKYDIVE And Input\Hold\ActionRoll)) And (Not(p\Action=ACTION_HOMING Or p\Action=ACTION_DEBUG)) And (p\SpeedLength#>0 Or Abs(p\Motion\Speed\y#)>1) And (Game\SuperForm=0 Or Game\MachLock>0) And Game\Victory=0 Then
		If (Not(p\SonicBoomTrailTimer>0)) Then
			Player_FreeTrails(p,1)
			For i=0 To 4
				PositionEntity(p\Objects\PPivot[i], EntityX#(p\Objects\Mesh),EntityY#(p\Objects\Mesh),EntityZ#(p\Objects\Mesh), True)
				MoveEntity(p\Objects\PPivot[i], 0.5*Rand(-3,3)+Rand(-1,1)*p\ScaleFactor#,0.5*Rand(-3,3)+Rand(-1,1)*p\ScaleFactor#,0.15*Rand(-2,2)+Rand(-1,1)*p\ScaleFactor#)
				Player_SpawnTrail(p,i)
			Next
			p\SonicBoomTrailTimer=0.15*secs#
		EndIf
		For i=0 To 4
			RotateEntity(p\Objects\PPivot[i],EntityPitch(p\Objects\Entity),p\Animation\Direction#,EntityRoll(p\Objects\Entity))
		Next
	Else
		Player_FreeTrails(p,1)
	EndIf
	
		; Deal longtrail
	Select p\Action
		Case ACTION_JUMPDASH,ACTION_LIGHTATTACK,ACTION_SPRINT,ACTION_HOMING,ACTION_STOMP,ACTION_BUMPED,ACTION_ROLL,ACTION_DRIFT,ACTION_BELLYFLOP,ACTION_POSTHOM: 
			If p\Flags\LongTrailCreated=0 Then 
				Player_LongTrail(p, 1)
				p\Flags\LongTrailCreated=1
			EndIf
		Case ACTION_DEMODASH
			If p\Motion\Ground=False Then
				If p\Flags\LongTrailCreated=0 Then 
					Player_LongTrail(p, 1)
					p\Flags\LongTrailCreated=1
				EndIf
			EndIf
		Case ACTION_FLY
			If p\JumpDashTimer>0 Then
				If p\Flags\LongTrailCreated=0 Then 
					Player_LongTrail(p, 1)
					p\Flags\LongTrailCreated=1
				EndIf
			Else
				p\Flags\LongTrailCreated=0
				Player_FreeLongTrails(p,2)
			EndIf
		Case ACTION_SOAR,ACTION_GLIDE,ACTION_DOUBLEJUMP,ACTION_DOUBLEJUMPS:
			Select p\Character
				Case CHAR_ESP
					If p\Flags\LongTrailCreated=0 Then 
						Player_LongTrail(p, 1)
						p\Flags\LongTrailCreated=1
					EndIf
				Default
					If p\Motion\Ground=False Then
						If p\Flags\LongTrailCreated=0 Then 
							Player_LongTrail(p, 1)
							p\Flags\LongTrailCreated=1
							p\TrailTimer = 0.175*secs#
						EndIf
					End If
					If (Not p\TrailTimer>0) Then Player_FreeLongTrails(p,2)
			End Select
		Case ACTION_SKYDIVE
			If Input\Hold\ActionRoll Then
				If p\Flags\LongTrailCreated=0 Then 
					Player_LongTrail(p, 1)
					p\Flags\LongTrailCreated=1
				EndIf
			Else
				p\Flags\LongTrailCreated=0
				Player_FreeLongTrails(p,2)
			EndIf
		Default:
			p\Flags\LongTrailCreated=0
			Player_FreeLongTrails(p,2)
	End Select
End Function
	Function Player_ResetJumpActionStuff(p.tPlayer)
		Player_ResetAirRestrictionStuff(p)
		Select p\Action
			Case ACTION_JUMPDASH:
				p\JumpDashTimer=0.35*secs#
			Case ACTION_FLY:
				p\FlyTimer=7*secs#
				p\FlyDistanceLimit=p\Objects\Position\y#
			Case ACTION_LEVITATE:
				p\LevitationTimer=3*secs#
			Case ACTION_FLUTTER:
				p\GlideTimer=2.24*secs#
			Case ACTION_SOAR:
				p\SoarTimer=6*secs#
			Case ACTION_DIVE:
				p\JumpDashTimer=0.775*secs#
			Case ACTION_SLEET:
				p\GlideTimer=0.625*secs#
		End Select
		
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_SkillActions(p.tPlayer,force=False)
	If Not((p\No#=1 Or p\No#<0) And Menu\ChaoGarden=0) Then Return
	If Menu\Stage>0 And ((Player_IsPlayable(p) And Input\Pressed\ActionSkill1) Or force) Then
		Select p\Character
			Case CHAR_SON
				If p\Motion\Ground Then
					Player_Action_Punch_Initiate(p)
				Else
					Player_Action_Sprint_Initiate(p)
				EndIf
			Case CHAR_MIG
				If p\Motion\Ground Then
					Player_Action_Sprint_Initiate(p)
					
				Else
					Player_Action_Punch_Initiate(p)
				EndIf
			Case CHAR_MET
				If p\Motion\Ground Then
					Player_Action_Punch_Initiate(p)
				Else
					Player_Action_Sprint_Initiate(p)
				EndIf
			Case CHAR_EGR
				Player_Action_Shoot_Initiate(p,1)
			Case CHAR_ESP
				If Game\Gameplay\GaugeEnergy>=5 Then Player_Action_Shoot_Initiate(p,1)
			Case CHAR_TAI
				If p\Motion\Ground=True Then
					Player_Action_Swipe_Initiate(p)
				Else
					Player_Action_Punch_Initiate(p)
				EndIf
			Case CHAR_BLA
				If p\Motion\Ground=True Then 
					Player_Action_Swipe_Initiate(p)
				Else
					Player_Action_Sprint_Initiate(p)
				EndIf
					
					
			Case CHAR_KNU,CHAR_ROU,CHAR_AMY,CHAR_SHN,CHAR_RAY
				Player_Action_Punch_Initiate(p)
			Case CHAR_INF
				Player_Action_Sprint_Initiate(p)
			Case CHAR_SHA,CHAR_OME
				If p\Motion\Ground=True Then
					Player_Action_Punch_Initiate(p)
				Else
					Player_Action_Shoot_Initiate(p)
				EndIf
			Case CHAR_CRE:
				Player_Action_Cheese_Initiate(p)
				
			Case CHAR_GAM,CHAR_EGG,CHAR_CHW,CHAR_TMH:
				If (Not(Player_IsPlayable(p))) Then Player_Action_Shoot_Initiate(p)
				
			Case CHAR_TIA:
				Player_Action_Gatling_Initiate(p)
		End Select
	EndIf
	Player_SkillActions2(p)
	End Function

	; =========================================================================================================
	; =========================================================================================================
	
	Function Player_SkillActions2(p.tPlayer,force=False)
	If Not((p\No#=1 Or p\No#<0) And Menu\ChaoGarden=0) Then Return
	If Menu\Stage>0 And ((Player_IsPlayable(p) And Input\Pressed\ActionSkill2) Or force) Then
		Select p\Character
			Case CHAR_RAY
				If p\CurledUp=0 And p\Motion\Ground=False Then p\Action=ACTION_ROLL : p\CurledUp=1
			Case CHAR_EGR
				If Game\Gameplay\GaugeEnergy=100 Then Player_Action_Shoot_Initiate(p,2)
			Case CHAR_KNU
				Player_Action_Uppercut_Initiate(p)
			Case CHAR_GAM
				If Game\Gameplay\GaugeEnergy>=15 And p\Motion\Ground=False Then
					Player_Action_DoubleJump_Initiate(p)
					Gameplay_SubstractGaugeEnergy(15)
				EndIf
			Case CHAR_SHN
				If Game\Gameplay\GaugeEnergy>=50 Then Player_Action_Shoot_Initiate(p)
			Case CHAR_CRE
				Player_Action_Cheese_Initiate(p,1)
			Case CHAR_INF:
				Player_Action_Shoot_Initiate(p,2)
			Case CHAR_ROU,CHAR_MKN,CHAR_TIA:
				Player_Action_Sprint_Initiate(p)
			Case CHAR_SIL
				If Game\Gameplay\GaugeEnergy>=10 Then Player_Action_Shoot_Initiate(p)
			Case CHAR_TAI
				Player_Action_Shoot_Initiate(p)
			Case CHAR_OME
				If Game\Gameplay\GaugeEnergy>=20 Then Player_Action_Shoot_Initiate(p,2)
		End Select
	EndIf
	Player_SkillActions3(p)
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_SkillActions3(p.tPlayer,force=False)
	If Not((p\No#=1 Or p\No#<0) And Menu\ChaoGarden=0) Then Return
	If Menu\Stage>0 And ((Player_IsPlayable(p) And Input\Pressed\ActionSkill3) Or force) Then
		Select p\Character
			Case CHAR_CRE
				If Game\Gameplay\GaugeEnergy>=50 Then Player_Action_Cheese_Initiate(p,2)	
			Case CHAR_SHN
				If p\Motion\Ground=True Then
					If Game\Gameplay\GaugeEnergy>= 5 Then Player_Action_Shoot_Initiate(p,2)
				Else
					Player_Action_Shoot_Initiate(p,3)
				EndIf
			Case CHAR_SHA
				If Game\Gameplay\GaugeEnergy=100 Then p\ChaosControlActiveTimer=10*secs# : Game\Gameplay\GaugeEnergy=0 : EmitSmartSound(Sound_ChaosControl, p\Objects\Entity) : Player_PlaySpecialAttackVoice(p)
			Case CHAR_ROU
				Player_Action_ScrewKick_Initiate(p)
			Case CHAR_INF
				Player_Action_Shoot_Initiate(p)
			Case CHAR_KNU
				If p\Motion\Ground Then 
					Player_Action_DemoDash_Initiate(p)
				EndIf
			Case CHAR_OME:
				If Game\Gameplay\GaugeEnergy>=5 Then Player_Action_Shoot_Initiate(p,3)
			Case CHAR_BIG,CHAR_GAM,CHAR_BET:
				If p\Motion\Ground=False Then
					If p\UnderwaterFeet=1 Then
						Player_Action_Buoy_Initiate(p)
					ElseIf p\Character=CHAR_BIG Then
						Player_Action_BellyFlop_Initiate(p)
					EndIf
				EndIf
			Case CHAR_SON
				Player_Action_LightAttack_Initiate(p)
			Case CHAR_ESP
				Player_Action_Hookshot_Initiate(p)
			Case CHAR_SIL
				Player_Action_Sprint_Initiate(p)
			Case CHAR_MIG
				If p\CurledUp=0 And p\Motion\Ground=False Then p\Action=ACTION_ROLL : p\CurledUp=1
				
			Case CHAR_BLA:
				If p\Motion\Ground=True Then
					If Game\Gameplay\GaugeEnergy=100 Then Player_TrailBlazer_Initiate(p)
				Else
					Player_Action_BlazeTrick_Initiate(p)
				EndIf
				
				
		End Select
	EndIf
End Function
Function Player_TrailBlazer_Initiate(p.tPlayer)
	p\BoostGaugeTimer=0.05*secs#
	p\TrailBlazer=1
	p\Channel_TrailBlazer=PlaySmartSound(Sound_BoostCharge)
	EmitSmartSound(Sound_FireDash,p\Objects\Entity)
End Function
	; =========================================================================================================
	; =========================================================================================================

	Function Player_ActuallyFall(p.tPlayer)
		If p\Motion\Ground=False Then
			Select p\Action
				Case ACTION_CLIMB: p\Action=ACTION_JUMPFALL : p\ForceJumpTimer=0.05*secs#
				Default: p\Action=ACTION_FULLFALL
			End Select
		EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_ActuallyCharge(p.tPlayer,force=False)
	
	If SHOPITEM_ENABLED(SHOPMENU_HANDICAP,HANDICAP_NOROLL)=1 Then Return
	
	If p\No#=1 Or force Or (pp(1)\Invisibility=0 And (pp(1)\Action=ACTION_COMMON Or pp(1)\Action=ACTION_ROLL Or pp(1)\Action=ACTION_CHARGE Or pp(1)\Action=ACTION_DRIFT)) Then

		If (Player_IsPlayable(p) And Input\Pressed\ActionRoll) Or force Then
			p\ChargeTimer=0
			p\JustChargedTimer=0
			p\Action=ACTION_CHARGE
			If p\No=1 Then p\Channel_DashCharge= EmitSmartSound(Sound_SpinDashCharge,p\Objects\Entity)
		EndIf

	EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_ActuallyJump(p.tPlayer,force=False,noeffects=False,mightbebounce=False)
	If p\No#=1 Or force Or (pp(1)\Flags\InJumpAction=False And pp(1)\Invisibility=0) Then
		
		; Jump when pressed and if on ground
		If (Player_IsPlayable(p) And Input\Pressed\ActionJump And SHOPITEM_ENABLED(SHOPMENU_HANDICAP,HANDICAP_NOJUMP)=0) Or force Then
			
			If p\Action=ACTION_ROLL Or p\Animation\Animation=ANIMATION_RUN Or p\Animation\Animation=ANIMATION_MACHRUN Or p\Motion\Ground=False Then
				
			ElseIf p\Action=ACTION_SPRINT
				p\Motion\Speed\x# = p\Motion\Speed\x#*0.5
				p\Motion\Speed\z# = p\Motion\Speed\z#*0.5
			Else
				p\Motion\Speed\x# = p\Motion\Speed\x#*0.775
				p\Motion\Speed\z# = p\Motion\Speed\z#*0.775
			EndIf
			
			
			Player_ConvertGroundToAir(p)
			p\Motion\Ground = False
			
			If (Not mightbebounce) Then p\Bouncing=0
			
			If p\Action=ACTION_HOLD Then
				Player_SetSpeedY(p,2)
			Else
				p\Motion\Speed\y# = p\Physics\JUMP_STRENGTH#*p\Physics\UNDERWATERTRIGGER#
			EndIf
				
			If Player_IsPlayable(p) Then p\JumpMayRiseTimer=0 Else p\JumpMayRiseTimer=(Rand(0,4)/5.0)*secs#
			
			If noeffects=False Then
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_AFTERJUMP, p\Objects\Mesh, 1+p\ScaleFactor#+0.25, 0, 0, p\RealCharacter, 1)
				If Player_IsSoundable(p) Then
					Select p\Action
						Case ACTION_BOARD,ACTION_BOARDDRIFT:	
						Default: Player_JumpSound(p)
					End Select
				EndIf
				Player_PlayJumpVoice(p)
			EndIf
			
			Select p\Action
				Case ACTION_CARRY:
					p\Action = ACTION_CARRYJUMP
				Case ACTION_BOARD,ACTION_BOARDDRIFT:
					p\Action = ACTION_BOARDJUMP
					EmitSmartSound(Sound_BoardJump,p\Objects\Entity)
				Case ACTION_CAR,ACTION_CARDRIFT:
					p\Action = ACTION_CARFALL
				Case ACTION_CLIMB
					p\CanceledFlight=0
					p\Action = ACTION_HOP
					p\JumpHopTimer=0
					p\JumpTimer=0
					p\Animation\Direction=p\Animation\Direction+180
					If p\Character=CHAR_ESP Then p\ClimbJumpTimer=0.05*secs#
				Default:
					p\Action = ACTION_HOP
					p\JumpHopTimer=0
					p\JumpTimer=0
			End Select
			p\ForceJumpTimer=0.05*secs#
			p\JumpballTimer=0.5*secs
			
			
			
			; When jumping, we need to set the align vector instantly, or else the player may reattach to the wall
			p\Motion\Align\x# = 0.0
			p\Motion\Align\y# = 1.0
			p\Motion\Align\z# = 0.0
			
		End If
		
	EndIf
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_CanWallKick(p.tPlayer)
		Select p\Character
			Case CHAR_ESP,CHAR_GME:
				Return True
			Default:
				Return False
		End Select
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_ActuallyLand(p.tPlayer)
		If p\Motion\Ground Then Player_Land(p) : ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_SMOKE, p\Objects\Mesh, 1+p\ScaleFactor#+0.025, 0.2, 3, 0, 5)
	End Function
Function Player_NormalLandSound(p.tPlayer)
	Select p\Collision\GroundType
		Case COLLISION_WORLD_POLYGON_WATER,COLLISION_WORLD_POLYGON_WATER2
			p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandWater,p\Objects\Entity)
		Case COLLISION_WORLD_POLYGON_WOOD: p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandWood,p\Objects\Entity)
		Case COLLISION_WORLD_POLYGON_DIRT: p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandDirt,p\Objects\Entity)
		Case COLLISION_WORLD_POLYGON_GRASS: p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandGrass,p\Objects\Entity)
		Case COLLISION_WORLD_POLYGON_METAL: p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandMetal,p\Objects\Entity)
		Default: p\Channel_GroundLand=EmitSmartSound(Sound_GroundLand,p\Objects\Entity)
			
	End Select
	
End Function	
Function Player_Land(p.tPlayer,waterland=False)
	Player_SetSpeedY(p,0)
	p\JustLandedTimer=0.125*secs#
	p\Bouncing=0
	p\UppercutOnce=0
	p\UppercutSound=0
	p\EnemiesBounced=0
	p\DemoDash=0
	p\AirKickOnce=0
	p\DoubleJumped=0
	p\HasShotOnce=0
	p\CanceledFlight=0
	p\Shots=0
	p\MightySpikeHurt=0
	p\TricksDone=0
	p\CurledUp=0
	If Player_IsSoundable(p) And ChannelPlaying(p\Channel_GroundLand)=False And (Not(p\JustChangedMateTimer>0)) Then
		If p\HasVehicle>0 Then
			p\Channel_GroundLand=EmitSmartSound(Sound_BoardLand,p\Objects\Entity)
		ElseIf Not waterland Then
			Select p\RealCharacter
				Case CHAR_OME,CHAR_MET,CHAR_MKN,CHAR_HBO,CHAR_SHD,CHAR_GAM,CHAR_EME,CHAR_EGG,CHAR_BET,CHAR_MT3,CHAR_GME,CHAR_CHW,CHAR_TMH,CHAR_EGR:
					p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandMetal,p\Objects\Entity)
				Case CHAR_TDL:
					p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandDoll,p\Objects\Entity)
				Case CHAR_CHO:
					p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandWater,p\Objects\Entity)
				Default:
					If IsCharMod(p\RealCharacter) Then 
						Select MODCHARS_FOOTSTEP(p\RealCharacter-CHAR_MOD1+1,MOD_LAND)
							Case 0
								
							Case 1
								Player_NormalLandSound(p)
							Case 2
								p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandMetal,p\Objects\Entity)
							Case 3
								p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandWater,p\Objects\Entity)
							Case 4
								p\Channel_GroundLand=EmitSmartSound(Sound_GroundLandDoll,p\Objects\Entity)
						End Select
					Else
						Player_NormalLandSound(p)
					EndIf
			End Select	
			
		EndIf
		If p\UnderwaterFeet=1 Then p\Channel_GroundLand2=EmitSmartSound(Sound_GroundLandWater,p\Objects\Entity)
	EndIf
	
	If Game\StartoutLock>0 Then Game\StartoutLock=0
	
	
	
	Select p\Action
		Case ACTION_CARRYJUMP,ACTION_CARRYTHROWN:
			p\Action = ACTION_CARRY : p\LandTimer=0
		Case ACTION_BOARDJUMP,ACTION_BOARDFALL,ACTION_BOARDTRICK:
			p\Action = ACTION_BOARD : p\LandTimer=0
			p\TrickCounter=0
		Case ACTION_CARFALL:
			p\Action = ACTION_CAR : p\LandTimer=0
		Default:
			If p\CanClimbTimer>0 And Abs(p\Rotation#)>30 And (p\Action=ACTION_GLIDE Or p\Action=ACTION_SPRINT Or (p\Action=ACTION_THRUST And p\Character=CHAR_MIG)) Then
				If p\Action=ACTION_GLIDE Or p\Action=ACTION_THRUST Then
					Player_Action_Climb_Initiate(p)
				ElseIf p\Action=ACTION_SPRINT Then
					Select p\Character
						Case CHAR_ESP,CHAR_GME: Player_Action_Climb_Initiate(p)
						Default: Player_Action_DoubleJump_Initiate(p,True)
					End Select
				EndIf
			Else
				If p\Physics\UP_ANGLE#>20 Then p\Physics\UP_ANGLE#=20
				If p\Physics\UP_ANGLE#<-5 Then p\Physics\UP_ANGLE#=-5
				If Game\Vehicle>0 Then Game\Vehicle=0
				p\Action = ACTION_LAND : p\LandTimer=0
			EndIf
	End Select
	
	If p\Flags\DropDashing=True Then Player_SetSpeed(p,5,True) : p\Action=ACTION_ROLL
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_ResetAirRestrictionStuff(p.tPlayer)
		p\JumpDashedOnce=0
		p\LevitatedOnce=0
		p\LevitationTimer=0
		p\CanceledFlight=0
		p\DoubleJumped=0
		p\SoarState=0
		p\SoarStore#=0
		If Not(p\Action=ACTION_BELLYFLOP) Then p\BombThrown=0
		p\FlyTimer=0
		p\AirKickOnce=0
		p\SoarTimer=0
		p\HoveredOnce=0
		p\WasInBuoyOnce=0
		p\CurledUp=0
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_HomingAttack(p.tPlayer, d.tDeltaTime)
	If p\Flags\HomingLocked Then
		If p\Action=ACTION_HOMING Or p\Action=ACTION_LIGHTATTACK Or p\Flags\InTargeterAirAttack Then
			PositionEntity(p\Flags\HomingMesh, p\Flags\HomingTarget\x#, p\Flags\HomingTarget\y#+0.3, p\Flags\HomingTarget\z#)
			ex# = p\Flags\HomingTarget\x# - p\Objects\Position\x#
			ey# = (p\Flags\HomingTarget\y#+3) - p\Objects\Position\y#
			ez# = p\Flags\HomingTarget\z# - p\Objects\Position\z#
			AlignToVector(p\Objects\Entity, ex#, ey#, ez#, 2, .925)
			AlignToVector(p\Objects\Mesh, ex#, ey#, ez#, 2, .925)
			If p\Flags\InTargeterAirAttack Then TurnEntity p\Objects\Mesh, -90, 0, 0
			If EntityDistance(p\Objects\Entity,p\Flags\HomingMesh)>3.2 Then
				If p\Action=ACTION_LIGHTATTACK Or p\Character=CHAR_SHA Or p\Character=CHAR_SHN Then 
					MoveEntity(p\Objects\Entity, 0, 7*d\Delta, 0)
				Else
					MoveEntity(p\Objects\Entity, 0, 5.5*d\Delta, 0)
				EndIf 
			Else
				p\Flags\HomingLocked=False : p\Flags\Targeter=0
				If (Not(p\Action=ACTION_LIGHTATTACK)) Then Player_JumpActionInteract(p)
			EndIf
			p\Motion\Speed\x# = 0 : p\Motion\Speed\y# = 0 : p\Motion\Speed\z# = 0
			If Not(Game\MachLock>0) Then p\Flags\HomingWasLockedTimer=0.05*secs# Else p\Flags\HomingWasLockedTimer=0.25*secs#
			Game\MachLockDisabler=0.125*secs#
		ElseIf p\Flags\InTargeterAttack Then
			PositionEntity(p\Flags\HomingMesh, p\Flags\HomingTarget\x#, p\Flags\HomingTarget\y#+0.3, p\Flags\HomingTarget\z#)
			PositionEntity(p\Flags\HomingMesh2, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#)
			RotateEntity p\Flags\HomingMesh2,0,DeltaYaw#(p\Flags\HomingMesh,p\Flags\HomingMesh2),0
			p\Animation\Direction#=EntityYaw(p\Flags\HomingMesh2)
			If Not EntityDistance(p\Objects\Entity,p\Flags\HomingMesh)>3.2 Then
				p\Flags\HomingLocked=False : p\Flags\Targeter=0
			EndIf
			Player_SetSpeed(p,2.25,True)
			If Not(Game\MachLock>0) Then p\Flags\HomingWasLockedTimer=0.05*secs# Else p\Flags\HomingWasLockedTimer=0.25*secs#
			Game\MachLockDisabler=0.125*secs#
		Else
			p\Flags\HomingLocked=False
		End If
		
		If p\Flags\InTargeterAirAttack Or p\Flags\InTargeterAttack Then
			If EntityDistance(p\Flags\HomingMesh,p\Objects\Entity)>10 Then
				p\Flags\HomingLocked=False : p\Flags\Targeter=0
			EndIf
		EndIf
	EndIf
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_RingDash(p.tPlayer, d.tDeltaTime)	
		If p\Action = ACTION_LIGHTDASH Then
			PositionEntity(p\Flags\HomingMesh, p\Flags\RingDashTarget\x#, p\Flags\RingDashTarget\y#+0.3, p\Flags\RingDashTarget\z#)				
			ex# = p\Flags\RingDashTarget\x# - p\Objects\Position\x# 
			ey# = (p\Flags\RingDashTarget\y#+3) - p\Objects\Position\y#
			ez# = p\Flags\RingDashTarget\z# - p\Objects\Position\z#					
			AlignToVector(p\Objects\Entity, ex#, ey#, ez#, 2, .925)
			AlignToVector(p\Objects\Mesh, ex#, ey#, ez#, 2, .925)
			TurnEntity(p\Objects\Mesh,-90,0,0)
			If EntityDistance(p\Objects\Entity,p\Flags\HomingMesh)>p\Physics\RINGDASH_SPEED# Then	
				MoveEntity(p\Objects\Entity, 0, p\Physics\RINGDASH_SPEED#*d\Delta, 0)
			Else
				MoveEntity(p\Objects\Entity, 0, EntityDistance(p\Objects\Entity,p\Flags\HomingMesh)*d\Delta, 0)
			EndIf					
			p\Motion\Speed\x# = 0 : p\Motion\Speed\y# = 0 : p\Motion\Speed\z# = 0
			p\LightDashTimer=0.15*secs#
		Else
			p\Flags\RingDashLocked = False
		End If
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_PickUp(p.tPlayer)	
	If Player_IsPlayable(p) And Game\MissionCardable=0 And (Input\Pressed\ActionAct Or (Menu\ChaoGarden=1 And Input\Pressed\ActionSkill1)) Then
		If p\HasVehicle=0 Then
			Select p\ObjPickUp
				Case 0:
					p\ObjPickUpTimer=0.5*secs#
				Default:
					p\ObjPickUpThrowTimer=0.1*secs#
					p\ObjPickUpTimer=0
					If p\Motion\Ground Then p\Action=ACTION_COMMON Else p\Action=ACTION_FULLFALL
			End Select
		EndIf
	EndIf
End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_ResetCheckValues()
		Game\Gameplay\CheckX#=Game\Stage\Properties\StartX#
		Game\Gameplay\CheckY#=Game\Stage\Properties\StartY#
		Game\Gameplay\CheckZ#=Game\Stage\Properties\StartZ#
		Game\Gameplay\CheckDirection#=Game\Stage\Properties\StartDirection#
		Game\Gameplay\CheckScore=0
		Game\Gameplay\CheckTime=0
		Game\Gameplay\CheckEnemies=0
		Game\Gameplay\CheckGoldEnemies=0
		Game\Gameplay\CheckBalloons=0
		Game\Gameplay\CheckMusicMode=0
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_ResetOutGameValues()
		Player_ResetCheckValues()
		
		Game\Gameplay\CurrentLap=1
		Game\Gameplay\CurrentLapCheck=1
		Game\Gameplay\Collectibles=0

		Gameplay_SetLives(Menu\SavedLives)

		Player_ResetInGameValues()

		If Menu\MarathonResetValues=0 Then Gameplay_SetScore(0)
		Game\Gameplay\Time=0
		Gameplay_SetEnemies(0)
		Gameplay_SetGoldEnemies(0)
		Gameplay_SetBalloons(0)

		Game\Gameplay\PerfectBonus=500
		Game\TokensToAdd=0
		Game\Gameplay\DiedOnce=0
		Game\Gameplay\GainedLives=0

		Game\ResetCamera=0
		Game\ResetChecks=0
		Game\ResetObjects=0

		Game\Gameplay\TotalEnemies=0
		Game\Gameplay\TotalGoldEnemies=0
		Game\Gameplay\TotalBalloons=0

		Game\Gameplay\Shards=0
		Game\Gameplay\TotalShards=0
		For i=1 To 3 : Game\Gameplay\ShardDistance[i]=0 : Next

		Game\Gameplay\PsychoBombCount=0

		Game\Gameplay\TotalBoxes=0

		OBJECT_VIEWDISTANCE_COUNT# = 0
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_ResetInGameValues()
		Game\Victory=0

		If Menu\MarathonResetValues=0 Then Gameplay_SetRings(0)
		If Menu\MarathonResetValues=0 Then Gameplay_SetGaugeEnergy(0)
		If Game\Gameplay\Flickies>0 Then
			Gameplay_SetFlickies(0)
			For o.tObject = Each tObject
				If o\ObjType=OBJTYPE_FLICKY Then o\State=-1
			Next
		EndIf

		Game\Shield = 0
		Game\HurtWithoutShield = 0

		Player_ResetDuringGameValues()
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_ResetDuringGameValues(goal=False)
		Game\SpeedShoes = 0
		Game\Invinc = 0
		If Not goal Then
			Game\SuperForm = 0
			Game\Vehicle=0
		EndIf

		Game\StartoutLock=1*secs#
		Game\ControlLock=0
		Game\CamLock=0
		Game\CamLock2=0
		Game\TwoDLock=0
		Game\QuickStepLock=0
		Game\RunLock=0
		Game\DirLock=0
		Game\MachLock=0
		Game\MachLockTriggered=0
		Game\BishopMagicTimer=0
		
		Game\AddGauge=0
		Game\GaugeToAdd=0
		Game\AddGaugeTimer=0
		Game\AddSuperRing=0
		Game\SuperRingsToAdd=0
		Game\SuperRingTimer=0
		
		

		Game\Interface\ShowHintTimer=0
		Game\CounterChance=0
		Game\CounterChanceTimer=0
		Game\CarnivalLevel=0
		Game\CurrentCarnivalTimer=0
		Select Menu\Mission
			Case MISSION_BOSS#: Game\BossNotDefeated=1
		End Select

		For p.tPlayer=Each tPlayer
			Player_ResetAllTimers(p)

			p\Invisibility=0
			p\Psychokinesis=0
			p\ObjPickUp=0
			p\DriftDirection=0

			p\BoomerangAway=0
			p\Aiming=0
			p\AimedTargets=0
			p\WasInBuoyOnce=0
			p\CheeseAttackedCount=0
			p\TornadoShoot=0
			p\TornadoStance=0

			p\DrownState=0
			p\DrownValue=0
			p\DieButDontLoseLife=0
			p\GoDestination=False
			p\ForceAfterHomDirectionApplicable=False

			p\Physics\UP_ANGLE_ACTUAL#=0
			p\Physics\UP_ANGLE#=0
			p\Physics\LEAN_ANGLE_ACTUAL#=0
			p\Physics\LEAN_ANGLE#=0
			p\Physics\TRICK_ANGLE_ACTUAL#=0
			p\Physics\TRICK_ANGLE#=0
			p\Physics\DRIFT_ANGLE_ACTUAL#=0
			p\Physics\DRIFT_ANGLE#=0
		Next
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_IsStaring(p.tPlayer, entity, angle1#=90, angle2#=270)
		PositionEntity p\Objects\Staring, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
		TurnEntity p\Objects\Staring, 0, DeltaYaw#(p\Objects\Staring,entity), 0
		Return ( (Abs(EntityYaw(p\Objects\Staring)-p\Animation\Direction#))>=angle1# And (Abs(EntityYaw(p\Objects\Staring)-p\Animation\Direction#))<=angle2# )
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Hit(p.tPlayer)
	
	If p\TrailBlazerTimer>0 Or p\Action=ACTION_DIEHURT Then Return
	
	If p\No#=1 Then
		If Game\Victory=0 And (Not(Game\StartoutLock>0)) Then
			If (Not(p\HurtTimer>0)) And (Not(p\Action=ACTION_TRANSFORM)) And Game\SuperForm=0 Then
				If SHOPITEM_ENABLED(SHOPMENU_HANDICAP,HANDICAP_NOJUMP)=1 Then
					Player_DieHurt(p)
				Else
				If Game\Gameplay\Rings=0 And (Not(p\CheeseShieldTimer>0)) Then
					If Game\Invinc=0 And Game\Shield=0 And p\MightySpikeHurt=0 Then
						
						Player_DieHurt(p)
					Else
						Player_Hurt(p)
					EndIf
				Else
					Player_Hurt(p)
				EndIf
			EndIf
			EndIf
		EndIf
	ElseIf p\No#<0 Then
		If (Not(p\HurtTimer>0)) Then
			If p\Rival\Health>1 Then
				p\Rival\Health=p\Rival\Health-1
				p\Motion\Ground=False
				p\Motion\Speed\y#=1.03
				Player_SetSpeed(p,-1.55)
				EmitSmartSound(Sound_Die,p\Objects\Entity)
				p\HurtTimer=4*secs#
				Player_PlayHurtVoice(p)
				p\Action=ACTION_HURT
			Else
				p\Rival\Health=p\Rival\Health-1
				p\Motion\Ground=False
				p\Motion\Speed\y#=1.3
				EmitSmartSound(Sound_Die,p\Objects\Entity)
				p\HurtTimer=4*secs#
				Player_PlayDieVoice(p)
				p\Action=ACTION_RIVALDIE
			EndIf
		EndIf
	EndIf
End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_Hurt(p.tPlayer,mode=0)
		If p\No#=1 And (Not(p\HurtTimer>0)) Then
			If Game\Invinc=0 And (Not(p\DontGetHurtTimer>0)) Then
				If Game\Shield=0 And (Not(p\CheeseShieldTimer>0)) And p\MightySpikeHurt=0 Then Game\HurtWithoutShield = 1 : 
				If Game\Shield=OBJTYPE_ESHIELD Then
					Game\Shield=OBJTYPE_NSHIELD
				Else
					Game\Shield=0
				EndIf
				If p\CheeseShieldTimer>0 Then p\CheeseShieldTimer=0
				If Abs(p\Rotation#)<20 Then
					p\Motion\Ground=False
					p\Motion\Speed\y#=1.03
					Player_SetSpeed(p,-1.55)
				EndIf
				If p\MightySpikeHurt=1 Then
					EmitSmartSound(Sound_BreakSpin,p\Objects\Entity)
				Else
					EmitSmartSound(Sound_Die,p\Objects\Entity)
				EndIf
				If p\Character=CHAR_ESP Then EmitSmartSound(Sound_Invisible,p\Objects\Entity)
				p\HurtTimer=4*secs#
				p\Hurt2Timer=1*secs#
				Player_PlayHurtVoice(p)
				Player_ResetAirRestrictionStuff(p)
				If (Not(p\Action=ACTION_BUMPED)) Then
					If p\HasVehicle>0 Then
						Select Game\Vehicle
						Case 1,5: p\Action=ACTION_BOARDFALL
						Case 2: p\Action=ACTION_GLIDER
						Case 3,4,9: p\Action=ACTION_CARFALL
						Case 6,7: p\Action=ACTION_TORNADO
						End Select
					ElseIf p\Action=ACTION_SKYDIVE Then
						p\Action=ACTION_SKYDIVE
					ElseIf p\Action=ACTION_FREEZE Then
						p\Action=ACTION_FREEZE
					Else
						If Abs(p\Rotation#)<20 Then p\Action=ACTION_HURT
					EndIf
				EndIf
				If Game\HurtWithoutShield = 1 Then
					Player_Create_RingLoss(p)
					If Game\Gameplay\Flickies>0 Then
						Gameplay_SetFlickies(0)
						For o.tObject = Each tObject
							If o\ObjType=OBJTYPE_FLICKY Then o\State=-2 : o\Treasure\ShardNo=0 : o\Treasure\ShardFlyStopTimer=0.75*secs#
						Next
					EndIf
				EndIf
				Game\ControlLock=0
				Game\RunLock=0
				p\MightySpikeHurt=0
			EndIf
		EndIf
		
		
		
	End Function
Function Player_DieHurt(p.tPlayer,mode=0)
	If p\No#=1 Then
			If Abs(p\Rotation#)<20 Then
				p\Motion\Ground=False
				p\Motion\Speed\y#=1.03
				Player_SetSpeed(p,-1.55)
			EndIf
			Player_PlayHurtVoice(p)
			Player_ResetAirRestrictionStuff(p)
			
			If Abs(p\Rotation#)<20 Then p\Action=ACTION_DIEHURT
			Game\ControlLock=0
			Game\RunLock=0
			
	EndIf
End Function
	; =========================================================================================================
	; =========================================================================================================

	Function Player_TouchDie(p.tPlayer)
		If Game\Victory=0 And (Not(p\Action=ACTION_DIE)) And (Not(p\DieTimer>0)) Then Player_Die(p)
	End Function

	Function Player_DieCamera(p.tPlayer, zoom#=0)
		Game\ControlLock=1800*secs#
		Game\CamLock=1800*secs#
		Game\CamLock2=0
		cam\Lock\Rotation\x#=50 : cam\Lock\Rotation\y#=cam\TargetRotation\y# : cam\Lock\Rotation\z#=cam\TargetRotation\z#
		If zoom#=0 Then cam\Lock\Zoom#=161 Else cam\Lock\Zoom#=31
		cam\Lock\Speed#=10/10.0
		cam\Lock\Immediate=0
		cam\Lock\Pos=0
		cam\Lock\PreviousPos=0
	End Function

	Function Player_Die(p.tPlayer, diebutdontloselife=0)
		If p\No#=1 And (Not(p\Action=ACTION_DIE)) Then
			Game\Vehicle=0
			p\Motion\Ground=False
			p\Motion\Speed\y#=1.3
			EmitSmartSound(Sound_Die,p\Objects\Entity)
			p\HurtTimer=4*secs#
			p\DieTimer=2.1*secs#
			;Player_DieCamera(p)
			Player_PlayDieVoice(p)
			p\Action=ACTION_DIE
			p\DieButDontLoseLife=diebutdontloselife
			PostEffect_Create_FadeOut(0.007, 10, 10, 10)
		EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================

Function Player_DieSpawn(p.tPlayer,perfect=False)
	
	If perfect=True Then QuickRestartStage(p) : Return
	
		If Game\Gameplay\Lives=0 Then
			Game\Gameplay\Lives=5
			Game_Stage_Quit(1)
		Else
			Game\ResetObjects=1
			Game\ResetCollectibles=0
			Objects_Reset_All()
			Game\ResetCollectibles=1
			
			For i = 1 To 5
				Game\Gameplay\GotRedRing[i]=Game\Gameplay\CheckRedRing[i]
			Next
			
			
			
			Player_Spawn(Game\Gameplay\CheckX#,Game\Gameplay\CheckY#+7,Game\Gameplay\CheckZ#,Game\Gameplay\CheckDirection#)
			Player_PlayTurnVoice(p)
			
			Game\Stage\Properties\MusicStartChannel=PlaySound(Game\Stage\Properties\MusicStart)
			
			If Menu\TutorialMode=0 Then Gameplay_SubstractLives(Abs(p\DieButDontLoseLife-1))

			Game\Stage\Properties\MusicMode=Game\Gameplay\CheckMusicMode
			For i=0 To 2
				StopChannel(Game\Stage\Properties\MusicChn[i])
				Game\Stage\Properties\MusicFade#[i]=1.0
			Next

			Player_ResetInGameValues()
			DeformCharacter(pp(1))
			If Menu\Members>=2 Then DeformCharacter(pp(2))
			If Menu\Members>=3 Then DeformCharacter(pp(3))

			Game\ResetCamera=1

			Game\Gameplay\PerfectBonus=0
			Game\Gameplay\DiedOnce=1

			Gameplay_SetScore(Game\Gameplay\CheckScore)
			Game\Gameplay\Time=Game\Gameplay\CheckTime
			Gameplay_SetEnemies(Game\Gameplay\CheckEnemies)
			Gameplay_SetGoldEnemies(Game\Gameplay\CheckGoldEnemies)
			Gameplay_SetBalloons(Game\Gameplay\CheckBalloons)

			Game\SmartCameraRangeDontAffectTimer=5*secs#
			PostEffect_Create_FadeIn(0.008, 10, 10, 10)

			StopChannel(Game\Channel_Invincible)
			StopChannel(Game\Channel_SpeedShoes)
			StopChannel(Game\Channel_Drown)
			StopChannel(Game\Channel_MissionCompleted)
			StopChannel(Game\Channel_Result)
			
			If SHOPITEM_ENABLED(SHOPMENU_ABILITY,ABILITY_SAFETYNET)=1 Then
				Gameplay_AddRings(10)
				EmitSmartSound(Sound_RingSuper,p\Objects\Entity)
				MonitorIcon_Draw(22) 
			EndIf
		EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_SaveSituation(o.tObject, p.tPlayer, mode=0, position=True)
		If position Then
			Game\Gameplay\CheckX#=o\Position\x#
			Game\Gameplay\CheckY#=o\Position\y#
			Game\Gameplay\CheckZ#=o\Position\z#
			Select mode
			Case 0: Game\Gameplay\CheckDirection#=o\Rotation\y#
			Case 1: Game\Gameplay\CheckDirection#=p\Animation\Direction#-180
			End Select
		EndIf
		Game\Gameplay\CheckScore=Game\Gameplay\Score
		Game\Gameplay\CheckTime=Game\Gameplay\Time
		Game\Gameplay\CheckEnemies=Game\Gameplay\Enemies
		Game\Gameplay\CheckGoldEnemies=Game\Gameplay\GoldEnemies
		Game\Gameplay\CheckBalloons=Game\Gameplay\Balloons
		Game\Gameplay\CheckMusicMode=Game\Stage\Properties\MusicMode
		Game\Gameplay\CheckTokens=TOKENS
		For i = 1 To 5
			Game\Gameplay\CheckRedRing[i]=Game\Gameplay\GotRedRing[i]
		Next
	End Function

	; =========================================================================================================
	; =========================================================================================================
	Global RINGMULT#
Function Player_Goal(p.tPlayer, victoryendtype=0, victoryendstage=0, touchedgoal=False)
	
	
	If Menu\ThemeVictoryTimer>0 Then Game\ReadyToSeeVictoryTimer=Menu\ThemeVictoryTimer*secs#
	
		Game\VictoryEndType=victoryendtype
		Game\VictoryEndStage=victoryendstage

		Player_ResetDuringGameValues(True)
		
		Game\Interface\AbilityIconOrder=1
		
		For i = 1 To SHOPITEM_TOTAL(SHOPMENU_ABILITY)
			ABILITY_ADDEDTORESULT(i)=0
		Next
		
		RINGMULT#=1
		
		For i = 1 To SHOPITEM_TOTAL(SHOPMENU_ABILITY)
			If SHOPITEM_ENABLED(SHOPMENU_ABILITY,i) = 1 And ABILITY_ADDEDTORESULT(i)=0 Then 
				Game\Interface\AbilityIcon[Game\Interface\AbilityIconOrder]=i-1
				Game\Interface\AbilityIconOrder=Game\Interface\AbilityIconOrder+1
				RINGMULT#=RINGMULT#*SHOPITEM_MULT#(SHOPMENU_ABILITY,i)
				ABILITY_ADDEDTORESULT(i)=1
			EndIf
		Next
		
		For i = 1 To SHOPITEM_TOTAL(SHOPMENU_HANDICAP)
			HANDICAP_ADDEDTORESULT(i)=0
		Next
		
		For i = 1 To SHOPITEM_TOTAL(SHOPMENU_HANDICAP)
			If SHOPITEM_ENABLED(SHOPMENU_HANDICAP,i) = 1 And HANDICAP_ADDEDTORESULT(i)=0 Then 
				Game\Interface\HandicapIcon[Game\Interface\HandicapIconOrder]=i-1
				Game\Interface\HandicapIconOrder=Game\Interface\HandicapIconOrder+1
				RINGMULT#=RINGMULT#*SHOPITEM_MULT#(SHOPMENU_HANDICAP,i)
				HANDICAP_ADDEDTORESULT(i)=1
			EndIf
		Next
		
		
		

		Game\Interface\ResultOrder=-1
		
		If Menu\StabilityTest=1 Then Game\Cheater=1 : Interface_Render_Result_Exit(p)
		Game\Shield=0
		Game\ResultRings=Int(Game\Gameplay\Rings*RINGMULT#)
		Game\ResultRingsForBank=Game\ResultRings
		Game\ResultEnemies=Game\Gameplay\Enemies
		Game\ResultTime=Game\Gameplay\Time
		Game\ResultPerfectBonus=Game\Gameplay\PerfectBonus
		
		Menu\Option=1

		Game\ScoreBonus=0
		If Game\ResultTime<Game\IdealTime Then
			Game\ScoreBonus=((((Game\IdealTime-Game\ResultTime)/secs#)/((Game\IdealTime)/secs#))*1000)*0.5
		EndIf
		Game\ScoreBonus=Game\ScoreBonus+Game\ResultRings*10

		Game\ScoreTotal=Game\Gameplay\Score

		Game\Rank=0

		PostEffect_Create_FadeIn(0.006, 255, 255, 255)
		For i=0 To 2 : StopChannel(Game\Stage\Properties\MusicChn[i]) : Next
		Game\Victory=1

		Game\Channel_MissionCompleted=PlaySmartSound(Sound_MissionCompleted)
		ChannelVolume(Game\Channel_MissionCompleted,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
		
		Game\Vehicle=0
		
		If (Not(Menu\Mission=MISSION_ESCAPE#)) Or (Not touchedgoal) Then
			If Game\Vehicle=0 Then pp(1)\Action=ACTION_VICTORY
		Else
			pp(1)\Action=ACTION_VICTORYHOLD
		EndIf
		If Menu\Members>=2 Then
			Player_FollowerGoalSpawn(pp(2))
			If Game\Vehicle=0 Then pp(2)\Action=ACTION_VICTORY
		EndIf
		If Menu\Members>=3 Then
			Player_FollowerGoalSpawn(pp(3))
			If Game\Vehicle=0 Then pp(3)\Action=ACTION_VICTORY
		EndIf
	End Function
	
	

	


	; ==========================================================================================================
	; ==========================================================================================================
	
Function Player_DealLightMeshes(p.tPlayer, d.tDeltaTime)
	
	If p\Action=ACTION_DEBUG Then Return
	
	;phantom barrier
	If (p\Action=ACTION_SPRINT Or (p\Action=ACTION_STOMP And Input\Hold\ActionRoll)) And p\Character=CHAR_INF Then
		If p\Action=ACTION_SPRINT Then
			ShowEntity(p\Objects\PhantomBarrier)
			PositionEntity p\Objects\PhantomBarrier, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
			RotateEntity (p\Objects\PhantomBarrier, 0, p\Animation\Direction#, 0)
			ScaleEntity(p\Objects\PhantomBarrier,1.2+p\ScaleFactor#,1.2+p\ScaleFactor#,1.2+p\ScaleFactor#)
			AlignToVector(p\Objects\PhantomBarrier, p\Motion\Align\x#, p\Motion\Align\y#, p\Motion\Align\z#, 2)
		EndIf
	Else
		HideEntity(p\Objects\PhantomBarrier)
	EndIf
	
		;phantom barrier
	If (p\Action=ACTION_DEMODASH) And p\Motion\Ground=False Then
		
		ShowEntity(p\Objects\DemoBarrier)
		PositionEntity p\Objects\DemoBarrier, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
		RotateEntity (p\Objects\DemoBarrier, 0, p\Animation\Direction#, 0)
		ScaleEntity(p\Objects\DemoBarrier,1.2+p\ScaleFactor#,1.2+p\ScaleFactor#,1.2+p\ScaleFactor#)
		AlignToVector(p\Objects\DemoBarrier, p\Motion\Align\x#, p\Motion\Align\y#, p\Motion\Align\z#, 2)
		
	Else
		HideEntity(p\Objects\DemoBarrier)
	EndIf
	
	If (p\Objects\R_GrindAffector <> 0 And p\Objects\L_GrindAffector <> 0) Then
		PositionEntity p\Objects\R_GrindAffector, -1, 0, 1.7
		PositionEntity p\Objects\L_GrindAffector, 1, 0, 1.7
		EntityAlpha p\Objects\L_GrindAffector,0
		EntityAlpha p\Objects\R_GrindAffector,0
	EndIf
	
	;jump ball
	If (p\Action=ACTION_JUMP Or p\Action=ACTION_CHARGE Or p\Action=ACTION_BUMPED Or p\Action=ACTION_ROLL) And Player_CanCharSpin(p) Then
		
		
		
		
		If Player_HasJumpball[p\RealCharacter]=1 Then
		;real jump ball
			
			
			
			Select Rand(1,3)
				Case 1
					ShowEntity(p\Objects\RealJumpball)
				Case 2,3
					If p\Flags\DropDashing=1 Then 
						ShowEntity(p\Objects\RealJumpball)
					Else
						HideEntity(p\Objects\RealJumpball)
					EndIf
			End Select
			
			If Game\SuperForm=0 Then
				EntityTexture p\Objects\RealJumpball,Player_Texture_Jumpball[p\Character]
			Else
				EntityTexture p\Objects\RealJumpball,Player_Texture_SuperJumpball[p\Character]
			EndIf
			
			
			RotateEntity (p\Objects\RealJumpball, 1*Game\Gameplay\Time, p\Animation\Direction#-180, 0)
			PositionEntity p\Objects\RealJumpball, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
			Select p\Character
				Case CHAR_SON
					ScaleEntity(p\Objects\RealJumpball,(1+p\ScaleFactor#*1.165)*1.1,(1+p\ScaleFactor#-0.10)*1.1,(1+p\ScaleFactor#-0.10)*1.1)
				Default
					ScaleEntity(p\Objects\RealJumpball,(1+p\ScaleFactor#*1.15),(1+p\ScaleFactor#-0.10),(1+p\ScaleFactor#-0.10))
			End Select
			
		EndIf
		
		;fake jumpball
		If Not(p\Action=ACTION_ROLL) Then
			ShowEntity(p\Objects\JumpBall)
			PositionEntity p\Objects\JumpBall, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
			RotateEntity (p\Objects\JumpBall, 0, p\Animation\Direction#-180, 0)
			EntityBlend(p\Objects\JumpBall, 3)
			EntityTexture(p\Objects\JumpBall, Texture_Trail)
			EntityFX(p\Objects\JumpBall,1)
			Select(p\RealCharacter)
				Case CHAR_HBO:
					ScaleEntity(p\Objects\JumpBall,1+p\ScaleFactor#*1.55,1+p\ScaleFactor#,1+p\ScaleFactor#)
				Case CHAR_CHO:
					ScaleEntity(p\Objects\JumpBall,(1+p\ScaleFactor#*1.15)*0.75,(1+p\ScaleFactor#-0.10)*0.75,(1+p\ScaleFactor#-0.10)*0.75)
				Case CHAR_BAR:
					ScaleEntity(p\Objects\JumpBall,(1+p\ScaleFactor#*1.15)*0.85,(1+p\ScaleFactor#-0.10)*0.85,(1+p\ScaleFactor#-0.10)*0.85)
				Case CHAR_GAM,CHAR_BET:
					ScaleEntity(p\Objects\JumpBall,(1+p\ScaleFactor#*1.15)*0.7,(1+p\ScaleFactor#-0.10)*0.65,(1+p\ScaleFactor#-0.10)*0.7)
				Case CHAR_PRS,CHAR_COM:
					ScaleEntity(p\Objects\JumpBall,(1+p\ScaleFactor#*1.15)*0.9,(1+p\ScaleFactor#-0.10)*0.9,(1+p\ScaleFactor#-0.10)*0.9)
				Default
					ScaleEntity(p\Objects\JumpBall,1+p\ScaleFactor#*1.25,1+p\ScaleFactor#-0.10,1+p\ScaleFactor#-0.10)
			End Select
			If Game\SuperForm=1 Then
				EntityColor(p\Objects\JumpBall,Interface_SCircle_R[InterfaceChar(p\RealCharacter)], Interface_SCircle_G[InterfaceChar(p\RealCharacter)], Interface_SCircle_B[InterfaceChar(p\RealCharacter)])
				Create_AfterImage.tAfterImage(p\Objects\JumpBall,p\Objects\Mesh,500, Interface_SCircle_R[InterfaceChar(p\RealCharacter)], Interface_SCircle_G[InterfaceChar(p\RealCharacter)], Interface_SCircle_B[InterfaceChar(p\RealCharacter)],1,1,1+p\ScaleFactor#,True,True,1)
			Else
				EntityColor(p\Objects\JumpBall,Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)])
				Create_AfterImage.tAfterImage(p\Objects\JumpBall,p\Objects\Mesh,500, Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)],1,1,1+p\ScaleFactor#,True,True,1)
			EndIf
		EndIf
	Else
		
		HideEntity(p\Objects\JumpBall)
		HideEntity(p\Objects\RealJumpball)
		
	EndIf
	
	If p\Flags\Boosting  Then
		ShowEntity(p\Objects\BoostBarrier)
		Select p\Character
			Case CHAR_SON
				EntityColor(p\Objects\BoostBarrier,0,225,255)
			Default
				EntityColor(p\Objects\BoostBarrier,Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)])
		End Select
		
		
		PositionEntity p\Objects\BoostBarrier, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh)+1, EntityZ(p\Objects\Mesh)
		RotateEntity (p\Objects\BoostBarrier, 0, p\Animation\Direction#, 0)
		AlignToVector(p\Objects\BoostBarrier, p\Motion\Align\x#, p\Motion\Align\y#, p\Motion\Align\z#, 2)
		ScaleEntity(p\Objects\BoostBarrier,1.25+p\ScaleFactor#,1.2+p\ScaleFactor#,1.2+p\ScaleFactor#)
	Else
		HideEntity(p\Objects\BoostBarrier)
	EndIf
	
	If Game\PassedLapTimer>0 Then
		ShowEntity(p\Objects\OmoLap)
		PositionEntity p\Objects\OmoLap, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh)+5, EntityZ(p\Objects\Mesh)
		PointEntity(p\Objects\OmoLap,cam\Entity)
		;Animate(p\Objects\OmoLap,1,0.4)
	Else
		HideEntity(p\Objects\OmoLap)
	EndIf
	
	
	If p\Action=ACTION_STOMP Then
		ShowEntity(p\Objects\Stomp)
		PositionEntity p\Objects\Stomp, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh)
		RotateEntity (p\Objects\Stomp, 0, p\Animation\Direction#-180, 0)
		EntityBlend(p\Objects\Stomp, 3)
		EntityTexture(p\Objects\Stomp, Texture_Trail)
		EntityShininess(p\Objects\Stomp,0)
		EntityFX(p\Objects\Stomp,1)
		ScaleEntity(p\Objects\Stomp,1.75+p\ScaleFactor#,1.75+p\ScaleFactor#,1.75+p\ScaleFactor#)
		If Game\SuperForm=1 Then
			EntityColor(p\Objects\Stomp,Interface_SCircle_R[InterfaceChar(p\RealCharacter)], Interface_SCircle_G[InterfaceChar(p\RealCharacter)], Interface_SCircle_B[InterfaceChar(p\RealCharacter)])
			Create_AfterImage.tAfterImage(MESHES(Mesh_StompTrail),p\Objects\Mesh,500, Interface_SCircle_R[InterfaceChar(p\RealCharacter)], Interface_SCircle_G[InterfaceChar(p\RealCharacter)], Interface_SCircle_B[InterfaceChar(p\RealCharacter)],1,1,1+p\ScaleFactor#,True,True,1,Texture_Trail)
		Else
			EntityColor(p\Objects\Stomp,Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)])
			Create_AfterImage.tAfterImage(MESHES(Mesh_StompTrail),p\Objects\Mesh,500, Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)],1,1,1+p\ScaleFactor#,True,True,1,Texture_Trail)
			
		EndIf
			
	Else
		HideEntity(p\Objects\Stomp)
	EndIf
	
	Select p\Action
		Case ACTION_FLY,ACTION_DEMODASH,ACTION_JUMPDASH,ACTION_GLIDE,ACTION_DOUBLEJUMP,ACTION_LEVITATE,ACTION_SLOWGLIDE,ACTION_FLUTTER,ACTION_SOAR,ACTION_SOARFLAP,ACTION_HOVER,ACTION_SPRINT,ACTION_PUNCH,ACTION_THRUST,ACTION_ROLL,ACTION_DRIFT,ACTION_DIVE,ACTION_SLEET,ACTION_SHOOTHOVER:
			Select p\Action
				Case ACTION_PUNCH:
					Select p\Character
						Case CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3:
						Default: Return
					End Select
				Case ACTION_THRUST:
					Select p\Character
						Case CHAR_VEC,CHAR_SIL,CHAR_MET,CHAR_MPH,CHAR_MT3:
						Default: Return
					End Select
			End Select
			ShowEntity(p\Objects\Forth)
			PositionEntity(p\Objects\Forth, EntityX(p\Objects\Mesh), EntityY(p\Objects\Mesh), EntityZ(p\Objects\Mesh))
			EntityBlend(p\Objects\Forth, 3)
			EntityTexture(p\Objects\Forth, Texture_Trail)
			p\Objects\ForthAlpha#=p\Objects\ForthAlpha#-0.075*d\Delta
			p\Objects\ForthScale#=p\Objects\ForthScale#+0.15*d\Delta
			EntityAlpha(p\Objects\Forth, p\Objects\ForthAlpha#)
			EntityShininess(p\Objects\Forth, 0)
			ScaleEntity(p\Objects\Forth,p\Objects\ForthScale#-0.50+p\ScaleFactor#,p\Objects\ForthScale#-0.50+p\ScaleFactor#,p\Objects\ForthScale#-0.50+p\ScaleFactor#)
			If p\Action=ACTION_FLY Or p\Action=ACTION_DOUBLEJUMP Or (p\Action=ACTION_SOAR And p\JustSoaredTimer>0) Or p\Action=ACTION_DIVE Then
				p\Objects\ForthRotation# = 0
			ElseIf p\Action=ACTION_ROLL Then
				p\Objects\ForthRotation# = (EntityPitch(p\Objects\Mesh)+90) 
			Else
				p\Objects\ForthRotation# = 90
			EndIf
			RotateEntity (p\Objects\Forth, p\Objects\ForthRotation#, p\Animation\Direction#-180, 0)
			If Game\SuperForm=1 Then 
				EntityColor(p\Objects\Forth,Interface_SCircle_R[InterfaceChar(p\RealCharacter)], Interface_SCircle_G[InterfaceChar(p\RealCharacter)], Interface_SCircle_B[InterfaceChar(p\RealCharacter)])
			Else
				EntityColor(p\Objects\Forth,Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)])
			EndIf
		Default:
			HideEntity(p\Objects\Forth)
			p\Objects\ForthAlpha# = 1
			p\Objects\ForthScale# = 1
			p\Objects\ForthRotation# = 0
	End Select
	
End Function


	; ==========================================================================================================
	; ==========================================================================================================




	; ==========================================================================================================
	; ==========================================================================================================
Function QuickRestartStage(p.tPlayer)
	Player_ResetOutGameValues()
	DeformCharacter(pp(1))
	If Menu\Members>=2 Then DeformCharacter(pp(2))
	If Menu\Members>=3 Then DeformCharacter(pp(3))
	
	Game\ResultRings=0
	Game\ResultRingsForBank=0
	Game\ResultEnemies=0
	Game\ResultTime=5999999
	Game\ResultPerfectBonus=0
	Game\ScoreBonus=0
	Game\ScoreTotal=0
	Game\Rank=7
	Game\RivalAmount=0
	Game\InsideBoxCheckerTimer=0
	
	Menu\Pause=0 : Input_ResetAllInput() : Game\SmartCameraRangeDontAffectTimer=3*secs#
	FlushMouse()
	
	
	
	Game\CamLock=0
	Game\ControlLock=0
	Game\RunLock=0
	
	
	Game\ResetChecks=1
	Game\ResetObjects=1
	Game\ResetCollectibles=1
	Objects_Reset_All()
	
	Game\UsedSkill=0
	
	Player_Spawn(Game\Stage\Properties\StartX#,Game\Stage\Properties\StartY#+7,Game\Stage\Properties\StartZ#,Game\Stage\Properties\StartDirection#) : Player_PlayTurnVoice(p)
	
	
	
	Game\Stage\Properties\MusicMode=Game\Gameplay\CheckMusicMode
	For i=0 To 2
		StopChannel(Game\Stage\Properties\MusicChn[i])
		Game\Stage\Properties\MusicFade#[i]=1.0
	Next
	
	Game\Stage\Properties\MusicStartChannel=PlaySound(Game\Stage\Properties\MusicStart)
	
;	DeformCharacter(pp(1))
;	If Menu\Members>=2 Then DeformCharacter(pp(2))
;	If Menu\Members>=3 Then DeformCharacter(pp(3))
	
	Game\SmartCameraRangeDontAffectTimer=5*secs#
	PostEffect_Create_FadeIn(0.008, 10, 10, 10)
	
	
	
	StopChannel(Game\Channel_Invincible)
	StopChannel(Game\Channel_SpeedShoes)
	StopChannel(Game\Channel_Drown)
	StopChannel(Game\Channel_MissionCompleted)
	StopChannel(Game\Channel_Result)
	
	
End Function
Function Player_ManageShields()
	
	If Game\Shield<>Game\PreviousShield Then
		For ppp.tPlayer = Each tPlayer
			If Player_IsPlayable(ppp) Then
				If Game\PreviousShield>0 Then FreeEntity ppp\Objects\Shield
				Select Game\Shield
					Case OBJTYPE_NSHIELD: ppp\Objects\Shield = CopyEntity(MESHES(SmartEntity(Mesh_ShieldNormal)), Game\Stage\Root)
					Case OBJTYPE_FSHIELD: ppp\Objects\Shield = CopyEntity(MESHES(SmartEntity(Mesh_ShieldFlame)), Game\Stage\Root)
					Case OBJTYPE_BSHIELD: ppp\Objects\Shield = CopyEntity(MESHES(SmartEntity(Mesh_ShieldBubble)), Game\Stage\Root)
					Case OBJTYPE_TSHIELD: ppp\Objects\Shield = CopyEntity(MESHES(SmartEntity(Mesh_ShieldThunder)), Game\Stage\Root)
					Case OBJTYPE_ESHIELD: ppp\Objects\Shield = CopyEntity(MESHES(SmartEntity(Mesh_ShieldEarth)), Game\Stage\Root)
				End Select
				Select Game\Shield
					Case OBJTYPE_BSHIELD,OBJTYPE_TSHIELD
						Animate ppp\Objects\Shield,1,0.4,1;,10
					Case OBJTYPE_ESHIELD,OBJTYPE_FSHIELD
						Animate ppp\Objects\Shield,1,0.1,1;,10
				End Select
				If Game\Shield>0 Then ScaleEntity ppp\Objects\Shield, 1+ppp\ScaleFactor#, 1+ppp\ScaleFactor#, 1+ppp\ScaleFactor#
			EndIf
		Next
		Game\PreviousShield=Game\Shield
	End If
	
	If Game\Shield>0 Then
		For ppp.tPlayer = Each tPlayer
			If Player_IsPlayable(ppp) And (Not(ppp\Action=ACTION_TORNADO)) Then
				Player_ShieldPlacement(ppp)
			EndIf
		Next
	EndIf
End Function

	Function Player_ShieldPlacement(p.tPlayer)
		RotateEntity p\Objects\Shield, EntityPitch(p\Objects\Mesh,1), EntityYaw(p\Objects\Mesh,1), EntityRoll(p\Objects\Mesh,1), 1
		PositionEntity p\Objects\Shield, EntityX(p\Objects\Mesh,1), EntityY(p\Objects\Mesh,1)+MeshHeight#(p\Objects\Mesh)/4.0, EntityZ(p\Objects\Mesh,1), 1
		If p\Animation\Animation=ANIMATION_HOLD1 Or p\Animation\Animation=ANIMATION_HOLD2 Then MoveEntity(p\Objects\Shield), 0, -MeshHeight#(p\Objects\Mesh)/1.5, 0
		If (Not(p\Action=ACTION_HOLD Or p\Action=ACTION_HOLD2)) Or Abs(cam\TargetRotation\x#)<50 Then PointEntity(p\Objects\Shield,cam\Entity)
	End Function

	Function Player_PlacementsOfVehicle(p.tPlayer)
		Player_Motion_PetPlacements(p)
		If Game\Shield>0 And Game\PreviousShield=Game\Shield Then
			Player_ShieldPlacement(p)
		EndIf
	End Function

	; ==========================================================================================================
	; ==========================================================================================================

	Function Player_Interface_TreasureCaution(p.tPlayer, c.tCamera)			
		height# = MeshHeight#(p\Objects\Mesh)
		If EntityInView (p\Objects\Mesh, c\Entity) Then
			CameraProject c\Entity, EntityX (p\Objects\Mesh), EntityY (p\Objects\Mesh)+height#, EntityZ (p\Objects\Mesh)
			w = StringWidth (label$)
			h = StringHeight (label$)
			x = ProjectedX () - (w / 2.0) - 1
			y = ProjectedY () - (h / 2.0) - 1
			Color 0, 0, 0
			Rect x, y, w + 2, h + 2, 1
			Color 255, 255, 255
			Text x, y, label$
			StartDraw()
				SetBlend(FI_ALPHABLEND)
				SetAlpha(1.0)
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
				SetColor(255, 255, 255)
				DrawImageEx(INTERFACE(Interface_Caution), x, y)
			EndDraw()
		EndIf	
	End Function


	; ==========================================================================================================
	; ==========================================================================================================

	Function Player_ExplodeInventory(p.tPlayer)
		For cii.tCarriedItem=Each tCarriedItem
			doneit=False
			Select cii\Type1
				Case 1: If Not(DRIVESUM(1)>=30) Then
						doneit=True : obj.tObject = Object_Drive_Create(cii\Type2, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, True)
					Else
						Interface_CreateOverlappingChaoMsg(3,"Garden too full to spawn more drives.",191.25,0,255)
					EndIf
			End Select
			If doneit Then
				TOTALCARRIEDITEMS=TOTALCARRIEDITEMS-1
				Delete cii
			EndIf
		Next
		TOTALCARRIEDITEMS=0
		Game\Interface\ChaoItemCount=0

		For ii.tItem=Each tItem
			doneit=False
			If ii\IsHeld Then
			Select ii\Type1
				Case 1: If Not(FRUITSUM(1)>=30) Then
						doneit=True : obj.tObject = Object_Fruit_Create(ii\Type2, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, ii\Type3, True)
					Else
						ii\IsHeld=False
						Interface_CreateOverlapping2ChaoMsg(1,"Garden too full to spawn more food.","The rest has been sent to your inventory.",0,255,0)
					EndIf
				Case 2: If Not(HATSUM(1)>=15) Then
						doneit=True : obj.tObject = Object_Hat_Create(p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, ii\Type2, True)
					Else
						ii\IsHeld=False
						Interface_CreateOverlapping2ChaoMsg(2,"Garden too full to spawn more hats.","The rest has been sent to your inventory.",255,127.5,0)
					EndIf
				Case 3: If (Not(CHAOSUM(1)>=CHAOCOUNT)) Then
						doneit=True : Object_ChaoManager_SpawnNewChao(ii\Type2)
					Else
						ii\IsHeld=False
						Interface_CreateOverlapping2ChaoMsg(6,"There are already too many chao in the garden.","The rest of the eggs has been sent to your inventory.",116,139,255)
					EndIf
				Case 4: If Not(SHELLSUM(1)>=10) Then
						doneit=True : obj.tObject = Object_Shell_Create(p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, Rand(1,360), ii\Type2, ii\Type3, True)
					Else
						ii\IsHeld=False
						Interface_CreateOverlapping2ChaoMsg(4,"Garden too full to spawn more shells.","The rest has been sent to your inventory.",0,255,255)
					EndIf
				Case 5: If Not(TOYSUM(1)>=10) Then
						doneit=True : obj.tObject = Object_Toy_Create(ii\Type2, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, True)
					Else
						ii\IsHeld=False
						Interface_CreateOverlapping2ChaoMsg(5,"Garden too full to spawn more toys.","The rest has been sent to your inventory.",255,255,0)
					EndIf
				Case 6: If (Not(SEEDSUM(1)>=20)) Then
						doneit=True : obj.tObject = Object_Seed_Create(ii\Type2, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, True)
					Else
						ii\IsHeld=False
						Interface_CreateOverlapping2ChaoMsg(7,"Garden too full to spawn more seeds.","The rest has been sent to your inventory.",248,241,135)
					EndIf
			End Select
			EndIf
			If doneit Then
				For aii.tItem=Each tItem
					If aii\ID>ii\ID Then aii\ID=aii\ID-1
				Next
				TOTALITEMS=TOTALITEMS-1
				Delete ii
			EndIf
		Next

		SaveGame_Inventory()
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_GetCharacterRadius#(p.tPlayer)
		Return 2.20
	End Function

	Function Player_GetCharacterRadiusHorizontal#(p.tPlayer)
		Return 2.20+0.3*p\ScaleFactor#
	End Function

	Function Player_SetRadius#(p.tPlayer)
		EntityRadius(p\Objects\Entity, Player_GetCharacterRadius#(p))
	End Function

	
	; =========================================================================================================
	; =========================================================================================================

	Function Player_CanLightDash(char)
		Select char
			Case CHAR_SON,CHAR_SHA,CHAR_BLA,CHAR_MIG,CHAR_AMY,CHAR_ESP,CHAR_NAC,CHAR_SHD,CHAR_PRS:
				Return True
			Default:
				Return False
		End Select
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_PlaceOnTornado(p.tPlayer,no)
		RotateEntity p\Objects\Mesh, EntityPitch(pp(1)\Objects\Mesh,1), EntityYaw(pp(1)\Objects\Mesh,1), EntityRoll(pp(1)\Objects\Mesh,1), 1
		Select no
			Case 2:
				ScaleEntity p\Objects\HipR, 0, 0, 0
				ScaleEntity p\Objects\HipL, 0, 0, 0
				PositionEntity p\Objects\Hips, EntityX(pp(1)\Objects\Mesh,1), EntityY(pp(1)\Objects\Mesh,1), EntityZ(pp(1)\Objects\Mesh,1), 1
				MoveEntity p\Objects\Hips, 0, 2.1, -1.05-0.08*p\ScaleFactor#
				If GetCharScaleFactor#(p\RealCharacter)>5 Then MoveEntity p\Objects\Hips, 0, 0, -2
				Select p\Character
					Case CHAR_BIG,CHAR_TAI,CHAR_TDL,CHAR_VEC,CHAR_RAY:
						ScaleEntity p\Objects\Extra, 0, 0, 0
					Case CHAR_TMH:
						MoveEntity p\Objects\Hips, 0, 3.2, 0
					Case CHAR_GAM,CHAR_BET,CHAR_EGR:
						MoveEntity p\Objects\Hips, 0, 3.2, 0
						If Menu\Members>1 Then ScaleEntity p\Objects\Extra2, 0, 0, 0
					Case CHAR_CHW:
						MoveEntity p\Objects\Hips, 0, 3.2, 0
						TurnEntity p\Objects\ArmL, -80, 0, 0
					Case CHAR_EGG:
						MoveEntity p\Objects\Hips, 0, 3.2, 0
						ScaleEntity p\Objects\Extra2, 0, 0, 0
				End Select
			Default:
				PositionEntity p\Objects\Mesh, EntityX(pp(1)\Objects\Mesh,1), EntityY(pp(1)\Objects\Mesh,1), EntityZ(pp(1)\Objects\Mesh,1), 1
				Select no
					Case 1:
						MoveEntity p\Objects\Mesh, 0, 5.375+2*pp(2)\ScaleFactor#, -5.25-2*pp(2)\ScaleFactor#
						If pp(1)\HasVehicle=7 Then MoveEntity p\Objects\Mesh, 0, 1.5+0.75*pp(2)\ScaleFactor#, 0
						Select p\Character
							Case CHAR_PRS,CHAR_COM:
								TurnEntity p\Objects\LegR, 120, 0, 0
								TurnEntity p\Objects\LegL, 120, 0, 0
								TurnEntity p\Objects\FootR, 10, 0, 0
								TurnEntity p\Objects\FootL, 10, 0, 0
								MoveEntity p\Objects\Hips, 0, -3.25, -1
							Case CHAR_INF:
								TurnEntity p\Objects\FootR, -27.5, 0, 0
								MoveEntity p\Objects\Hips, 0, -1, 0
							Case CHAR_CHW:
								TurnEntity p\Objects\ArmL, -80, 0, 0
						End Select
						TurnEntity p\Objects\ArmR, -25, 10, 20
						TurnEntity p\Objects\ArmL, -25, -10, -20
						If pp(1)\HasVehicle=6 Or pp(1)\HasVehicle=7 Then
							ScaleEntity p\Objects\VehicleShootController, 1+p\ScaleFactor#*1.2, 1+p\ScaleFactor#*1.2, 1+p\ScaleFactor#*1.2, 1
							MoveEntity p\Objects\VehicleShootController, 0, 0, p\ScaleFactor#*2.45
						EndIf
					Case 3:
						MoveEntity p\Objects\Mesh, p\TornadoStance*(6.5+2.5*pp(2)\ScaleFactor#), 4.75+1.65*pp(2)\ScaleFactor#, 1.05*pp(2)\ScaleFactor#
						If pp(1)\HasVehicle=7 Then
							MoveEntity p\Objects\Mesh, p\TornadoStance*(2+3*pp(2)\ScaleFactor#), -14-7*pp(2)\ScaleFactor#, -4-2.5*pp(2)\ScaleFactor#
							TurnEntity p\Objects\Mesh, 0, 0, -p\TornadoStance*40
						EndIf
				End Select
		End Select
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_VictoryCam()
		If Game\CinemaMode=0 Then
			Game\CamLock=0
			cam\Rotation\y#=pp(1)\Animation\Direction#
			cam\TargetRotation\y#=pp(1)\Animation\Direction#
			cam\Rotation\x#=10
			cam\TargetRotation\x#=10
			;If Not(Menu\Mission=MISSION_CAPSULE#) Then i#=13.5 Else i#=30.375
			i#=13.5
			CAMERA_DISTANCE_NEAR# = i#+CAMERA_CONTROL_SIZEFACTOR#*pp(1)\ScaleFactor#
			CAMERA_DISTANCE_FAR# = i#+CAMERA_CONTROL_SIZEFACTOR#*pp(1)\ScaleFactor#
			If pp(1)\Action=ACTION_TORNADO Then
				If Menu\Members=1 Then
					CAMERA_DISTANCE_NEAR# = CAMERA_DISTANCE_NEAR#+10+4*pp(1)\ScaleFactor#
					CAMERA_DISTANCE_FAR# = CAMERA_DISTANCE_FAR#+10+4*pp(1)\ScaleFactor#
				Else
					CAMERA_DISTANCE_NEAR# = CAMERA_DISTANCE_NEAR#+10+4*pp(2)\ScaleFactor#
					CAMERA_DISTANCE_FAR# = CAMERA_DISTANCE_FAR#+10+4*pp(2)\ScaleFactor#
				EndIf
			EndIf
		EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================
;~IDEal Editor Parameters:
;~C#Blitz3D