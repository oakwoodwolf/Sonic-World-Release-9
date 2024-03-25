
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
Function Object_RedRing_Create.tObject(x#, y#, z#,number#)
	o.tObject = New tObject : o\ObjType = OBJTYPE_REDRING : o\ID=TempAttribute\ObjectID
	
	Object_CreateHitBox(HITBOXTYPE_RING,o,5,5,5)
	
	Object_Acquire_Position(o,x#,y#,z#)
	Object_Acquire_Power(o,number#)
	
	o\State=0
	
	o\Entity = CreatePivot()
	o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_RedRing)), Game\Stage\Root)
	
	EntityType(o\Entity, COLLISION_OBJECT_GOTHRU)
	
	Select o\Power#
		Case 1 : If REDRING1(Menu\Stage)=1 Then EntityAlpha(o\EntityX,0.25)
		Case 2 : If REDRING2(Menu\Stage)=1 Then EntityAlpha(o\EntityX,0.25)
		Case 3 : If REDRING3(Menu\Stage)=1 Then EntityAlpha(o\EntityX,0.25)
		Case 4 : If REDRING4(Menu\Stage)=1 Then EntityAlpha(o\EntityX,0.25)
		Case 5 : If REDRING5(Menu\Stage)=1 Then EntityAlpha(o\EntityX,0.25)
	End Select 
	
	Return o
End Function

; =========================================================================================================

Function Object_RedRing_Update(o.tObject, p.tPlayer, d.tDeltaTime)
	
		; Movement
	PositionEntity o\EntityX, o\Position\x#, o\Position\y#, o\Position\z#
	RotateEntity o\EntityX, 0, EntityYaw(Menu\RingRotator), 0
	
		; Player collided with object
	If o\Hit Then
		
		Select o\Power#
			Case 1
				If REDRING1(Menu\Stage)=0 Then
					REDRINGS=REDRINGS+1 
					REDRING1(Menu\Stage)=1
					Game\Interface\ShowRedRingTimer=3*secs#
				EndIf 
			Case 2
				If REDRING2(Menu\Stage)=0 Then
					REDRINGS=REDRINGS+1 
					REDRING2(Menu\Stage)=1
					Game\Interface\ShowRedRingTimer=3*secs#
				EndIf 
			Case 3
				If REDRING3(Menu\Stage)=0 Then
					REDRINGS=REDRINGS+1 
					REDRING3(Menu\Stage)=1
					Game\Interface\ShowRedRingTimer=3*secs#
				EndIf 
			Case 4
				If REDRING4(Menu\Stage)=0 Then
					REDRINGS=REDRINGS+1 
					REDRING4(Menu\Stage)=1
					Game\Interface\ShowRedRingTimer=3*secs#
				EndIf 
			Case 5
				If REDRING5(Menu\Stage)=0 Then
					REDRINGS=REDRINGS+1 
					REDRING5(Menu\Stage)=1
					Game\Interface\ShowRedRingTimer=3*secs#
				EndIf 
		End Select 
		
		
			; Bling!
		EmitSmartSound(Sound_RedRing,o\Entity)
		
			;Release effect
		ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_REDRING, o\Entity, 1)
		
		
		o\Done=1
		Return
	EndIf
	
	
	
End Function

Function Object_Ring_Create.tObject(x#, y#, z#,amount#)
		o.tObject = New tObject : o\ObjType = OBJTYPE_RING : o\ID=TempAttribute\ObjectID

		Object_CreateHitBox(HITBOXTYPE_RING,o,3.5,3.5,3.5)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Power(o,amount#)

		o\State=0

		o\Entity = CreatePivot()
		
		
		Select o\Power#
			Case 5	:  o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BRing5)) , Game\Stage\Root)
			Case 10	:  o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BRing10)), Game\Stage\Root)
			Case 20	:  o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BRing20)), Game\Stage\Root)
			Default	:  o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Ring))   , Game\Stage\Root)
		End Select 
		
		If o\Power#>1 Then ScaleEntity(o\EntityX,0.75,0.75,0.75)
		
		EntityType(o\Entity, COLLISION_OBJECT_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Ring_Update(o.tObject, p.tPlayer, d.tDeltaTime)

	; Movement
		PositionEntity o\EntityX, o\Position\x#, o\Position\y#, o\Position\z#
		Select o\State
			Case 0:
				RotateEntity o\EntityX, 0, EntityYaw(Menu\RingRotator), 0
				If Game\Shield=OBJTYPE_TSHIELD Or p\Action=ACTION_BOOST And (Not(p\Action=ACTION_LIGHTDASH)) Then
					If (EntityDistance(p\Objects\Entity,o\Entity)<30) Then
						o\RingDrawIn=1	
					EndIf
				EndIf
				
				For b.tBomb=Each tBomb
					Select b\BombType
						Case BOMB_RINGBOOMERANG
							If (EntityDistance(o\Entity,b\Entity)<15) Then
								o\RingDrawIn=1
							EndIf 
					End Select
				Next 
				
				If o\RingDrawIn=1 Then 
					PointEntity o\Entity, p\Objects\Entity
					PointEntity o\EntityX, p\Objects\Entity
					MoveEntity o\Entity, 0,0,2.5*d\Delta
					o\State=1 : o\AlwaysPresent=True
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_RING, o\Entity, 1)
				EndIf 

			Case 1:
				If (Not(Game\Shield=OBJTYPE_TSHIELD)) Or EntityDistance(p\Objects\Entity,o\Entity)>OBJECT_VIEWDISTANCE_UPDATEDISTANCE#*10 Then
					o\State=0 : o\AlwaysPresent=False
				EndIf
				PointEntity o\Entity, p\Objects\Entity
				PointEntity o\EntityX, p\Objects\Entity
				If EntityDistance(o\Entity, p\Objects\Entity)>2 Then MoveEntity o\Entity, 0,0,2*d\Delta Else MoveEntity o\Entity, 0,0,EntityDistance(o\Entity, p\Objects\Entity)*d\Delta
		End Select

		; Player collided with object
		If o\Hit Then

			; Add to counter
			Gameplay_AddRings(o\Power#)
			Gameplay_AddGaugeEnergy(o\Power#)
			Gameplay_AddScore(o\Power#*10)

			; Make Sonic a move
			If p\Action=ACTION_LIGHTDASH Then p\Action=ACTION_JUMPFALL : p\LightDashRequestTimer=0.1*secs# : Player_SetSpeed(p,2)

			; Bling!
			StopChannel(Game\Channel_Ring)
			If o\Power=1 Then 
				Game\Channel_Ring=EmitSmartSound(Sound_Ring,o\Entity)
			Else
				Game\Channel_Ring=EmitSmartSound(Sound_RingSuper,o\Entity)
			EndIf 
			
			;sparkles
			Select Rand(1,3)
				Case 1 : EmitSmartSound(Sound_RingSparkle1,o\Entity)
				Case 2 : EmitSmartSound(Sound_RingSparkle2,o\Entity)
				Case 3 : EmitSmartSound(Sound_RingSparkle3,o\Entity)
			End Select 

			;Release effect
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_RING, o\Entity, 1)
		
			; Delete the object
			o\CanRingDash=False
			o\Done=1
			Return
		EndIf

		If EntityDistance(o\Entity, p\Objects\Entity)<25 Then p\AroundLightDashTimer=0.25*secs#

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_SpewRing_Create.tObject(x#, y#, z#, xspeed#, yspeed#, zspeed#, magnetized=0)
		o.tObject = New tObject : o\ObjType = OBJTYPE_SPEWRING
		o\AlwaysPresent=True
		o\Spew = New tObject_Spew : o\HasValuesetSpew=True
		
		o\Pivot = CreatePivot()

		Object_CreateHitBox(HITBOXTYPE_RING,o,3.5,3.5,3.5)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Speed(o,xspeed#,yspeed#,zspeed#)

		o\Mode=1

		o\State=magnetized
				
		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpewRing)), o\Pivot)

		RotateEntity(o\Entity, 180, 0, 0)
		PositionEntity(o\Pivot, x#, y#, z#)
		RotateEntity(o\Pivot, 0, Rnd#(0.0, 359.9), 0)
		o\Repose=1

		Return o	
	End Function

	; =========================================================================================================
	
Function Object_SpewRing_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		If o\Spew\CollectTimer#>=10 Then
			If o\IValues[0]=0 Then o\IValues[0]=1 : EntityType(o\Pivot, COLLISION_OBJECT_GOTHRU)
		EndIf

		RotateEntity o\Entity, 0, EntityYaw(Menu\RingRotator), 0

		; Ring bouncing
		Select o\State
			Case 0,1: Object_EnforceRingBouncing(o,p,d)
				o\Spew\CollectTimer# = o\Spew\CollectTimer#+timervalue#
				If o\Spew\CollectTimer# >= 7500 Then
					If ((MilliSecs() Mod 20) < 10) Then ShowEntity o\Entity Else HideEntity o\Entity
				End If
				If o\Spew\CollectTimer# >= 500 And o\State=1 Then o\State=2
			Case 2:
				PointEntity o\Pivot, p\Objects\Entity
				PointEntity o\Entity, p\Objects\Entity
				If EntityDistance(o\Pivot, p\Objects\Entity)>2 Then MoveEntity o\Pivot, 0,0,2*d\Delta Else MoveEntity o\Pivot, 0,0,EntityDistance(o\Pivot, p\Objects\Entity)*d\Delta
		End Select
		
		If o\Spew\CollectTimer# >= 10500 Or EntityDistance(o\Entity,p\Objects\Entity)>300 Then
			; Delete the object
			FreeEntity o\Entity
			Delete o\Position
			Delete o\Speed
			Delete o
			Return		
		End If

		; Player collided with object
		If o\Hit And (o\Spew\CollectTimer# >= 1500 Or o\State=2) Then
		
			; Add to counter
			Gameplay_AddRings(1)

			; Bling!
			EmitSmartSound(Sound_Ring,o\Entity)

			;Release effect
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_RING, o\Entity, 1)
		
			; Delete the object
			FreeEntity o\Entity
			Delete o\Position
			Delete o\Speed
			Delete o
			Return
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Monitor_Create.tObject(monitortype, x#, y#, z#,pitch#=0,yaw#=0,roll#=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\ThisIsAMonitor=True : o\Monitor = New tObject_Monitor : o\HasValuesetMonitor=True

		Select monitortype
			Case 0: 
				Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,5,6,5)
			Case 1:
				Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,6.5,8.0,6.5)
				o\ThisIsAMonitorBalloon=True
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,pitch#,yaw#,roll#)

		o\State=0

		If o\ObjType=OBJTYPE_RINGS Then
			Select(Rand(1,7))
				Case 1,2: o\Monitor\RingsType=5
				Case 3,4,5,6: o\Monitor\RingsType=10
				Case 7: o\Monitor\RingsType=20
			End Select
		EndIf

		Select monitortype
			Case 0:
				o\Entity = LoadAnimMesh("Objects/Monitors/Monitor.b3d", Game\Stage\Root)
				ExtractAnimSeq(o\Entity,0,1);idle
				ExtractAnimSeq(o\Entity,1,19);boom
				ScaleEntity o\Entity,1.1,1.1,1.1
			Case 1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_MonitorBalloon)), Game\Stage\Root)
		End Select
		Animate o\Entity,1,0.5,1,10

		Select o\ObjType
			Case OBJTYPE_RINGS:
				Select o\Monitor\RingsType
					Case 5: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BRing5)), Game\Stage\Root)
					Case 10: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BRing10)), Game\Stage\Root)
					Case 20: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BRing20)), Game\Stage\Root)
				End Select
			Case OBJTYPE_LIFE:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Life)), Game\Stage\Root)
			Case OBJTYPE_TRAP:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Trap)), Game\Stage\Root)
			Case OBJTYPE_INVINC:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Invincible)), Game\Stage\Root)
			Case OBJTYPE_SHOES:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Shoe)), Game\Stage\Root)
			Case OBJTYPE_NSHIELD:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_ShieldNormalX)), Game\Stage\Root)
			Case OBJTYPE_FSHIELD:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_ShieldFlameX)), Game\Stage\Root)
			Case OBJTYPE_BSHIELD:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_ShieldBubbleX)), Game\Stage\Root)
			Case OBJTYPE_TSHIELD:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_ShieldThunderX)), Game\Stage\Root)
			Case OBJTYPE_ESHIELD:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_ShieldEarthX)), Game\Stage\Root)
			Case OBJTYPE_BOMB:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Bomb)), Game\Stage\Root)
			Case OBJTYPE_BOARD:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BoardX)), Game\Stage\Root)
			Case OBJTYPE_GLIDER:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_GliderX)), Game\Stage\Root)
			Case OBJTYPE_CAR:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_CopRacer1Car+(Rand(1,6))-1)), Game\Stage\Root)
				ScaleEntity o\EntityX, 0.35, 0.35, 0.35
			Case OBJTYPE_BIKE:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Bike)), Game\Stage\Root)
				ScaleEntity o\EntityX, 0.3, 0.3, 0.3
			Case OBJTYPE_BOBSLEIGH:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Bobsleigh)), Game\Stage\Root)
				ScaleEntity o\EntityX, 0.3875, 0.3875, 0.3875
			Case OBJTYPE_TORNADO:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tornado1)), Game\Stage\Root)
				ScaleEntity o\EntityX, 0.2, 0.2, 0.2
			Case OBJTYPE_CYCLONE:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Cyclone)), Game\Stage\Root)
				ScaleEntity o\EntityX, 0.3875, 0.3875, 0.3875
			Case OBJTYPE_KART:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Kart)), Game\Stage\Root)
				ScaleEntity o\EntityX, 0.2, 0.2, 0.2
			Case OBJTYPE_WINGS:
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Wings)), Game\Stage\Root)
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Monitor_ItemRotate(o.tObject, p.tPlayer)
		If o\ThisIsAMonitorBalloon Then PointEntity(o\Entity,cam\Entity)

		If o\ObjType=OBJTYPE_LIFE And o\Monitor\MonitorTakenOnce=1 Then HideEntity(o\EntityX)

		Select o\ObjType
			Case OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_NSHIELD,OBJTYPE_WINGS:
				PointEntity(o\EntityX, cam\Entity)
			Default:
				RotateEntity o\EntityX, 0, EntityYaw(Menu\RingRotator), 0
		End Select
	End Function

Function Object_Monitor_Update(o.tObject, p.tPlayer)

		; Movement
		Object_Monitor_ItemRotate(o,p)
		
		; Player collided with object
		If (o\Hit Or o\CheeseHit Or o\FroggyHit Or o\BombHit) And o\State=0 Then
		
			; Add to counter
			Select o\ObjType
				Case OBJTYPE_RINGS:
					Gameplay_AddRings(o\Monitor\RingsType)
					Gameplay_AddGaugeEnergy(o\Monitor\RingsType)
				Case OBJTYPE_LIFE:
					If o\Monitor\MonitorTakenOnce=0 Then Gameplay_AddLives(1)
				Case OBJTYPE_TRAP:
					Gameplay_SubstractRings(10)
				Case OBJTYPE_INVINC:
					If Game\SuperForm=0 Then
						Game\Invinc=1 : Game\InvincTimer=20.046391*secs#
						StopChannel(Game\Channel_Invincible) : StopChannel(Game\Channel_SpeedShoes)
						If (ChannelPlaying(Game\Channel_Drown)=False) Then Game\Channel_Invincible=PlaySmartSound(Sound_Invincible)
					EndIf
				Case OBJTYPE_SHOES:
					If Game\SuperForm=0 Then
						Game\SpeedShoes=1 : Game\SpeedShoeTimer=15.177130*secs#
						StopChannel(Game\Channel_Invincible) : StopChannel(Game\Channel_SpeedShoes)
						If (ChannelPlaying(Game\Channel_Drown)=False) Then Game\Channel_SpeedShoes=PlaySmartSound(Sound_SpeedShoes)
					EndIf
				Case OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD:
					If p\CheeseShieldTimer>0 Then p\CheeseShieldTimer=0
					Game\Shield=o\ObjType
				Case OBJTYPE_BOMB:
					p\BombMonitorTimer=0.1*secs#
				Case OBJTYPE_BOARD:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=1
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_BOARD
						Next
					EndIf
				Case OBJTYPE_GLIDER:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=2
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_GLIDER
							p\Motion\Ground=False
							p\Motion\Speed\y#=0.5
						Next
					EndIf
				Case OBJTYPE_CAR:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=3
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_CAR
							ppp\VehicleColor=o\Mode
						Next
					EndIf
				Case OBJTYPE_BIKE:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=4
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_CAR
						Next
					EndIf
				Case OBJTYPE_BOBSLEIGH:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=5
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_BOARD
						Next
					EndIf
				Case OBJTYPE_TORNADO:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=6
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_TORNADO
						Next
					EndIf
				Case OBJTYPE_CYCLONE:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=8
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_BOARD
						Next
					EndIf
				Case OBJTYPE_KART:
					If Game\WholeVehicle=0 Then
						Game\Vehicle=9
						For ppp.tPlayer = Each tPlayer
							ppp\Action = ACTION_CAR
						Next
					EndIf
				Case OBJTYPE_WINGS:
					If p\Motion\Ground=False Then Player_ResetJumpActionStuff(p)
			End Select
			Gameplay_AddScore(100)

			; Icon drawer
			If Not(o\ObjType=OBJTYPE_LIFE And o\Monitor\MonitorTakenOnce=1) Then MonitorIcon_Draw(o\ObjType-(OBJTYPE_RINGS-1)) Else MonitorIcon_Draw(OBJTYPE_WINGS-OBJTYPE_RINGS+2)

			; Sound effect!
			If o\ThisIsAMonitorBalloon Then
				EmitSmartSound(Sound_MonitorBalloon,o\Entity)
				If (Not(o\ObjType=OBJTYPE_TRAP)) Then p\TrickTimer=1*secs#
			EndIf
			Select o\ObjType
				Case OBJTYPE_TRAP: EmitSmartSound(Sound_MonitorTrap,o\Entity)
				Case OBJTYPE_RINGS: EmitSmartSound(Sound_MonitorRing,o\Entity)
				Case OBJTYPE_NSHIELD: EmitSmartSound(Sound_MonitorShield,o\Entity)
				Case OBJTYPE_FSHIELD: EmitSmartSound(Sound_MonitorShieldFlame,o\Entity)
				Case OBJTYPE_BSHIELD: EmitSmartSound(Sound_MonitorShieldBubble,o\Entity)
				Case OBJTYPE_TSHIELD: EmitSmartSound(Sound_MonitorShieldThunder,o\Entity)
				Case OBJTYPE_ESHIELD: EmitSmartSound(Sound_MonitorShieldEarth,o\Entity)
				Case OBJTYPE_BOMB: EmitSmartSound(Sound_Bombed,o\Entity)
				Default: EmitSmartSound(Sound_Monitor,o\Entity)
			End Select

			; Make Sonic a move
			If o\CheeseHit=False And o\FroggyHit=False And o\BombHit=False And (Not(o\ObjType=OBJTYPE_BOARD)) Then
				If p\Motion\Ground=False And (Not(p\Action=ACTION_BOARD Or p\Action=ACTION_BOARDJUMP Or p\Action=ACTION_SKYDIVE Or p\Action=ACTION_HOOKSHOT)) Then
					;If o\ThisIsAMonitorBalloon Then Player_SetSpeed(p,0.1)
					If Game\Vehicle=0 Then Player_JumpActionInteract(p,2)
				EndIf
			EndIf

			; Update monitor taken once
			o\Monitor\MonitorTakenOnce=1
		
			; Delete the object
			o\CanHoming=False
			o\CheeseCanHoming=False
			Animate o\Entity,3,2.05,2,10
			o\State=1
			Return
		EndIf

		; Delete the object
		If (Not(Animating(o\Entity))) And o\State=1 Then
			o\Done=1
			Return
		EndIf

		; Aiming and shooting
		Object_EnforceAimingShooting(o,p)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Goal_Create.tObject(mode#, x#, y#, z#, teleportername$=0, switchno1=0, switchno2=0, switchno3=0,missionno#=1)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Goal = New tObject_Goal : o\Teleporter = New tObject_Teleporter : o\HasValuesetTeleporter=True
		If (o\ObjType=OBJTYPE_GOAL2) Then
			o\Switch = New tObject_Switch : o\HasValuesetSwitch=True
			o\Switch\SwitchNo[0]=switchno1
			o\Switch\SwitchNo[1]=switchno2
			o\Switch\SwitchNo[2]=switchno3
		EndIf

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Power(o,missionno#)
		o\Mode=mode#
		If mode#=1 Or o\ObjType=OBJTYPE_WARPRING Then
			j=0
			For i=0 To StageAmount
				If StageName$(i)=teleportername$ And j=0 Then j=i
			Next
			o\Teleporter\TeleporterNo=j
			o\TagTip$=teleportername$
		EndIf

		If Menu\Stage>0 Then
			If (Not(Menu\Mission=MISSION_ESCAPE#)) Then
				Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,15,15,15)
				If o\Objtype=OBJTYPE_WARPRING Then 
					o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Ring)), Game\Stage\Root)
					ScaleEntity(o\Entity,3.5,3.5,3.5)
				Else
					If Menu\Mission=MISSION_ENCORE# Then
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_GoalRuby)), Game\Stage\Root)
					Else
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_GoalRing)), Game\Stage\Root)
					EndIf 
				EndIf 
			Else
				Object_CreateHitBox(HITBOXTYPE_SPEEDY_TRANSFERER,o,7.875,5.25,7.875)
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Escaper)), Game\Stage\Root)
				Animate(o\Entity,1,0.075,1,10)
			EndIf
		Else
			Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,50,50,50)
			o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_EmeraldGoal)), Game\Stage\Root)
			EntityColorEmerald(o\Entity,Abs(Menu\Stage))
			Animate o\Entity,1,0.025,1
		EndIf

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Goal_Switch(o.tObject)
		If Object_WhetherHasSwitches(o) Then
			Object_SwitchManager_PerObjectUpdate(o)
			Select o\Switch\SwitchOn
				Case 1: EntityAlpha(o\Entity,0.5) : If o\Hit Then o\Hit=False
				Case 0: EntityAlpha(o\Entity,1)
			End Select
		EndIf
	End Function
Function Object_Goal_Sound(o.tObject, p.tPlayer)
	If ChannelPlaying(o\Goal\Channel_GoalIdle)=False Then
		If Menu\Stage>0 Then
			If Menu\Mission=MISSION_ENCORE# Then 
				o\Goal\Channel_GoalIdle=EmitSmartSound(Sound_RubyIdle,o\Entity)
			Else
				o\Goal\Channel_GoalIdle=EmitSmartSound(Sound_GoalIdle,o\Entity)
			EndIf 
			If EntityDistance(p\Objects\Entity,o\Entity) >= 500 Then
				ChannelVolume(o\Goal\Channel_GoalIdle,0)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 475 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.05)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 450 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.1)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 425 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.15)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 400 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.2)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 375 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.25)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 350 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.3)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 325 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.35)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 300 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.4)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 275 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.45)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 250 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.5)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 225 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.55)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 200 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.6)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 175 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.65)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 150 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.7)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 125 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.75)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 100 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.8)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 75 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.85)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 50 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.9)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 25 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,0.95)
			ElseIf EntityDistance(p\Objects\Entity,o\Entity) >= 1 Then 
				ChannelVolume(o\Goal\Channel_GoalIdle,1)
			EndIf 
			
		Else
			o\Goal\Channel_GoalIdle=EmitSmartSound(Sound_Emerald,o\Entity)
		EndIf
	EndIf
End Function
Function Object_Goal_Update(o.tObject, p.tPlayer)

		If o\ObjType=OBJTYPE_GOAL2 Then Object_Goal_Switch(o)

		If (Not(Menu\Mission=MISSION_ESCAPE#)) Then
			If p\Action=ACTION_STOMP Or p\Action=ACTION_LAND Then o\HitBox\y#=10 Else o\HitBox\y#=15

			RotateEntity o\Entity, 0, EntityYaw(Menu\RingRotator), 0

			If (Not(o\ObjType=OBJTYPE_WARPRING)) Then Object_Goal_Sound(o,p)

			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_GOAL, o\Entity, 1.25, 0, 0, 0, 0, 0.2)
		Else
			If o\Hit Then
				RotateEntity o\Entity,0,p\Animation\Direction#+180,0
			Else
				RotateEntity o\Entity,0,(DeltaYaw#(p\Objects\Entity,o\Entity) - 180),0
			EndIf

			If p\Action=ACTION_VICTORYHOLD Then
				p\Motion\Speed\y#=0.5
				PositionEntity o\Entity, EntityX(p\Objects\Mesh,1), EntityY(p\Objects\Mesh,1), EntityZ(p\Objects\Mesh,1), 1
				MoveEntity o\Entity, 0, 0.75+p\ScaleFactor#, 0.2
			EndIf
		EndIf
		
		; Player collided with object
		If o\Hit Then
			If o\ObjType=OBJTYPE_WARPRING Then
				Game\Interface\HintLine1$ = o\TagTip$ + ": Mission "+Int(o\Power#)
				Game\Interface\HintLine2$ = "Press Interact to Warp"
				Game\Interface\ShowHintTimer = (1)*secs#
				If Input\Pressed\ActionAct And Game\HasStartedStageTeleport=0 Then
					Menu\SelectedStage=o\Teleporter\TeleporterNo
					Menu\HubStage=Menu\Stage
					Menu\MissionNo=Int(o\Power#)
					Game_Stage_Quit(2)
					Game\HasStartedStageTeleport=1
				EndIf 
			Else
				If Game\Victory=0 Then
				; Finish stage
					Select Menu\Mission
						Case MISSION_ENEMY#,MISSION_RING#,MISSION_HUNT#,MISSION_GOLD#,MISSION_BALLOONS#,MISSION_FREEROAM#,MISSION_RIVAL#,MISSION_CARNIVAL#,MISSION_BOSS#:
							Object_Goal_Update_Teleport(o,p)
						Case MISSION_FLICKY#:
							If Game\Gameplay\Flickies>=5 Then
								Player_Goal(p,o\Mode,o\Teleporter\TeleporterNo,True)
								For o2.tObject = Each tObject
									If o2\ObjType=OBJTYPE_FLICKY Then o2\State=-1
								Next
							Else
								Object_Goal_Update_Teleport(o,p)
							EndIf
						Default:
							Player_Goal(p,o\Mode,o\Teleporter\TeleporterNo,True)
					End Select
					
					If (Not(Menu\Mission=MISSION_ESCAPE#)) Then
					; Bling!
						StopChannel(o\Goal\Channel_GoalIdle)
						If Menu\Mission=MISSION_ENCORE Then
							EmitSmartSound(Sound_RubyGoal,o\Entity)
						Else
							EmitSmartSound(Sound_Goal,o\Entity)
						EndIf 
					;Release effect
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_RING, o\Entity, 3)
						
					; Delete the object
						o\Done=1
						Return
					Else
						EmitSmartSound(Sound_Grab,o\Entity)
					EndIf
				EndIf
			EndIf 
		EndIf
		
	End Function

Function Object_Goal_Update_Teleport(o.tObject, p.tPlayer)
		Stage_ResetStageMusic()
		Game\Gameplay\CheckX#=Game\Stage\Properties\StartX#
		Game\Gameplay\CheckY#=Game\Stage\Properties\StartY#
		Game\Gameplay\CheckZ#=Game\Stage\Properties\StartZ#
		Game\Gameplay\CheckDirection#=Game\Stage\Properties\StartDirection#
		Game\Gameplay\CheckScore=Game\Gameplay\Score
		Game\Gameplay\CheckTime=Game\Gameplay\Time
		Game\Gameplay\CheckEnemies=Game\Gameplay\Enemies
		Game\ResetCamera=1
		Game\ResetChecks=1
		Game\ResetObjects=1
		PostEffect_Create_FadeIn(0.008, 255, 255, 255)
		Player_Spawn(Game\Gameplay\CheckX#,Game\Gameplay\CheckY#+7,Game\Gameplay\CheckZ#,Game\Gameplay\CheckDirection#)
		PlaySmartSound(Sound_Warp)
		DeformCharacter(p)
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Bubbles_Create.tObject(x#, y#, z#, bubbletype)
		o.tObject = New tObject : o\ObjType = OBJTYPE_BUBBLES
		o\Bubble = New tObject_Bubble : o\HasValuesetBubble=True

		Select bubbletype
			Case 0: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,0,0,0)
			Case 1: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,5,5,5)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)

		o\Bubble\BubbleType=bubbletype

		Select bubbletype
			Case 0: o\Bubble\BubbleCreateTimer=5*secs#
			Case 1: o\Bubble\BubbleDisappearTimer=3.841*secs#
		End Select

		o\Entity = CreatePivot()

		PositionEntity o\Entity, x#, y#, z#
		o\Repose=1

		Return o
		
	End Function
	
	; =========================================================================================================
	
Function Object_Bubbles_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Update timers
		If o\Bubble\BubbleCreateTimer>0 Then o\Bubble\BubbleCreateTimer=o\Bubble\BubbleCreateTimer-timervalue#
		If o\Bubble\BubbleDisappearTimer>0 Then o\Bubble\BubbleDisappearTimer=o\Bubble\BubbleDisappearTimer-timervalue#

		If o\Position\y#<Game\Stage\Properties\WaterLevel Then
			; Particle effect
			Select o\Bubble\BubbleType
				Case 0: ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BUBBLES, o\Entity)
				Case 1: ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BUBBLE, o\Entity)
			End Select

			; Creation and movement
			Select o\Bubble\BubbleType
				Case 0:
					If (Not(o\Bubble\BubbleCreateTimer>0)) Then
						o\Bubble\BubbleCreateTimer=5*secs#
						obj.tObject = Object_Bubbles_Create(o\Position\x#, o\Position\y#, o\Position\z#, 1)
					EndIf
				Case 1:
					MoveEntity o\Entity,0,0.110713*d\Delta,0
			End Select
		EndIf

		; Player collided with object
		If o\Bubble\BubbleType=1 And ( (o\Hit And o\Bubble\BubbleDisappearTimer<3.5*secs#) Or (Not(o\Bubble\BubbleDisappearTimer>0)) Or (Not(o\Position\y#<Game\Stage\Properties\WaterLevel)) ) Then
			; Release particle effect
			If o\Position\y#<Game\Stage\Properties\WaterLevel Then ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BUBBLES, o\Entity)

			; Affect player
			If o\Hit Then
				EmitSmartSound(Sound_Breath,o\Entity)
				p\DrownState=0
				p\DrownTimer=0
				StopChannel(Game\Channel_Drown)
			EndIf
		
			; Delete the object
			FreeEntity o\Entity
			Delete o\Position
			Delete o
			Return 1
		EndIf

		Return 0
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Shard_Create.tObject(x#, y#, z#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Treasure = New tObject_Treasure : o\HasValuesetTreasure=True

		Game\Gameplay\TotalShards=Game\Gameplay\TotalShards+1
		o\Treasure\ShardNo=Game\Gameplay\TotalShards

		Object_CreateHitBox(HITBOXTYPE_RING,o,7,7,7)

		Object_Acquire_Position(o,x#,y#,z#)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shard)), Game\Stage\Root)

		EntityType(o\Entity, COLLISION_OBJECT_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================

Function Object_Shard_Update(o.tObject, p.tPlayer)

		If o\IsInBox=0 Or o\InView Then o\Treasure\BoxShouldHide=False

		If o\HasShard And o\Treasure\BoxShouldHide=False Then
			ShowEntity(o\Entity)
			Object_Shard_Update_Real(o, p)
		Else
			HideEntity(o\Entity)
		EndIf
		
	End Function

Function Object_Shard_Update_Real(o.tObject, p.tPlayer)

		RotateEntity o\Entity, 0, -EntityYaw(Menu\RingRotator), 0

		; Remove if was collected
		For i=1 To 3
			If o\Treasure\ShardNo=Game\Gameplay\Shard[i] And Game\Gameplay\ShardDistance[i]=9 Then
				o\Done=1 : Return
			EndIf
		Next
		
		; Player collided with object
		If o\Hit Then

			; Add to counter
			Gameplay_AddShards(1)

			; Bling!
			EmitSmartSound(Sound_RingBig,o\Entity)

			; Save situation
			Player_SaveSituation(o,p,1,False)

			;Release effect
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_SHARD, o\Entity, 2)

			; Cancel radar
			For i=1 To 3
				If o\Treasure\ShardNo=Game\Gameplay\Shard[i] Then
					Game\Gameplay\ShardDistance[i]=9
				EndIf
			Next
		
			; Delete the object
			o\Done=1
			Return
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_SpewShard_Create.tObject(shardno, x#, y#, z#, xspeed#, yspeed#, zspeed#, shardfly=False)
		o.tObject = New tObject : o\ObjType = OBJTYPE_SPEWSHARD
		o\AlwaysPresent=True
		o\Treasure = New tObject_Treasure : o\HasValuesetTreasure=True
		o\Spew = New tObject_Spew : o\HasValuesetSpew=True

		o\Treasure\ShardNo=shardno
		
		o\Pivot = CreatePivot()

		Object_CreateHitBox(HITBOXTYPE_RING,o,7,7,7)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Speed(o,xspeed#,yspeed#,zspeed#)

		o\Treasure\ShardFly=shardfly
		If o\Treasure\ShardFly Then o\Treasure\ShardFlyStopTimer=0.25*secs#
				
		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpewShard)), o\Pivot)

		RotateEntity(o\Entity, 180, 0, 0)
		PositionEntity(o\Pivot, x#, y#, z#)
		RotateEntity(o\Pivot, 0, Rnd#(0.0, 359.9), 0)
		o\Repose=1

		o\HasShard=True

		Return o	
	End Function

	; =========================================================================================================
	
Function Object_SpewShard_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		If o\Spew\CollectTimer#>=10 Then
			If o\IValues[0]=0 Then o\IValues[0]=1 : EntityType(o\Pivot, COLLISION_OBJECT_GOTHRU)
		EndIf
	
		RotateEntity o\Entity, 0, -EntityYaw(Menu\RingRotator), 0

		; Update timer
		If o\Treasure\ShardFlyStopTimer>0 Then o\Treasure\ShardFlyStopTimer=o\Treasure\ShardFlyStopTimer-timervalue#

		; Ring bouncing
		Object_EnforceRingBouncing(o,p,d)

		If o\Spew\CollectTimer#<1.1*secs# Then o\Spew\CollectTimer# = o\Spew\CollectTimer#+timervalue#
		
		If o\Spew\CollectTimer# >= 10500 Then
			; Delete the object
			FreeEntity o\Entity
			Delete o\Position
			Delete o\Speed
			Delete o
			Return
		End If

		; Player collided with object
		If o\Hit And o\Spew\CollectTimer#>1*secs# Then

			; Add to counter
			Gameplay_AddShards(1)

			; Bling!
			EmitSmartSound(Sound_RingBig,o\Entity)

			; Save situation
			Player_SaveSituation(o,p,1,False)

			;Release effect
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_SHARD, o\Entity, 2)

			; Cancel radar
			For i=1 To 3
				If o\Treasure\ShardNo=Game\Gameplay\Shard[i] Then
					Game\Gameplay\ShardDistance[i]=9
				EndIf
			Next
		
			; Delete the object
			FreeEntity o\Entity
			Delete o\Position
			Delete o\Speed
			Delete o
			Return
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	
Function Object_Hint_Create.tObject(x#, y#, z#, hintline1$, hintline2$,length#,soundpath$)
	o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
	o\Hint = New tObject_Hint : o\HasValuesetHint=True
	
	Object_CreateHitBox(HITBOXTYPE_NORMAL,o,5.25,5.25,5.25)
	
	Object_Acquire_Position(o,x#,y#,z#)
	Object_Acquire_Power(o,length#)
	
	o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Hint)), Game\Stage\Root)
	
	o\Hint\HintLine1$=hintline1$
	o\Hint\HintLine2$=hintline2$
	
	
	If Len(soundpath$)>0 Then o\Hint\Sound=LoadSound(Game\Stage\Properties\Path$+soundpath$)
	
	Return o
End Function

	; =========================================================================================================

Function Object_Hint_Update(o.tObject, p.tPlayer)
	
		; Timer
	If o\Hint\HintRevealTimer>0 Then o\Hint\HintRevealTimer=o\Hint\HintRevealTimer-timervalue#
	If o\Hint\HintRevealTimer>0 And o\Hint\HintRevealTimer<1*secs# Then
		Animate o\Entity,1,0.05,1,10
		o\Hint\HintRevealTimer=0
	EndIf
	
		; Movement
	PointEntity(o\Entity,cam\Entity)
	
		; Player collided with object
	If o\Hit And (Not(o\Hint\HintRevealTimer>0)) And (Not(Game\Interface\ShowHintTimer>0)) Then
		
		PlaySound(o\Hint\Sound)
			; Bling!
		EmitSmartSound(Sound_Hint,o\Entity)
		Animate o\Entity,1,0.05,2,10
		
			; Give line
		Game\Interface\HintLine1$ = o\Hint\HintLine1$
		Game\Interface\HintLine2$ = o\Hint\HintLine2$
		Game\Interface\ShowHintTimer = (o\Power#)*secs#
		o\Hint\HintRevealTimer = (o\Power#+1)*secs#
		
	EndIf
	
		; Alpha
	If Game\Interface\ShowHintTimer>0 And (Not(o\Hint\HintRevealTimer>0)) Then
		EntityAlpha(o\Entity,0.25)
	Else
		EntityAlpha(o\Entity,1)
	EndIf
	
End Function



; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Counter_Create.tObject(x#, y#, z#, counterno#, length#=5)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Hint = New tObject_Hint : o\HasValuesetHint=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,5.25*1.25,5.25*1.25,5.25*1.25)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Power(o,length#)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Counter1+counterno#-1)), Game\Stage\Root)

		o\Hint\SignType=counterno#

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Counter_Update(o.tObject, p.tPlayer)

		; Timer
		If o\Hint\HintRevealTimer>0 Then o\Hint\HintRevealTimer=o\Hint\HintRevealTimer-timervalue#
		If o\Hint\HintRevealTimer>0 And o\Hint\HintRevealTimer< 1*secs# Then o\Done=1

		; Movement
		PointEntity(o\Entity,cam\Entity)

		; Player collided with object
		If o\Hit And (Not(o\Hint\HintRevealTimer>0)) Then

			; Bling!
			EmitSmartSound(Sound_Counter,o\Entity)
			Animate o\Entity,1,0.05,2,10
		
			; Give counting
			If o\Hint\SignType=5 Or (o\Hint\SignType=5-Game\CounterChance And Game\CounterChanceTimer>0) Then
				If Game\CounterChance=5-o\Hint\SignType Then Game\CounterChance=Game\CounterChance+1
				Game\CounterChanceTimer=5*secs#
				Select o\Hint\SignType
					Case 5
						Game\Channel_Counter = EmitSmartSound(Sound_Counter1,o\Entity)
					Case 4
						Game\Channel_Counter = EmitSmartSound(Sound_Counter2,o\Entity)
					Case 3
						Game\Channel_Counter = EmitSmartSound(Sound_Counter3,o\Entity)
					Case 2
						Game\Channel_Counter = EmitSmartSound(Sound_Counter4,o\Entity)
					Case 1
						Game\Channel_Counter = EmitSmartSound(Sound_Counter5,o\Entity)
				End Select 
			Else
				Game\CounterChance=0
				Game\CounterChanceTimer=0
				Game\Channel_Counter = EmitSmartSound(Sound_CounterWrong,o\Entity)
			EndIf
			o\Hint\HintRevealTimer = (1.75+1)*secs#
			

		EndIf

		
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Bell_Create.tObject(x#, y#, z#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,5,5,5)

		Object_Acquire_Position(o,x#,y#,z#)

		o\Entity = CreatePivot()
		o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Bell)), Game\Stage\Root)

		EntityRadius(o\Entity,4)
		EntityType(o\Entity, COLLISION_OBJECT_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Bell_Update_Mesh(o.tObject, d.tDeltaTime)
		PositionEntity o\EntityX, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity), 1
		RotateEntity o\EntityX, 0, 0.15*20*d\Delta, 0
	End Function

Function Object_Bell_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Movement
		If o\Mode>0 Then
			o\Mode=o\Mode-timervalue#
			If o\Mode<0.005*secs# Then Animate o\EntityX,1,0,1,10 : o\AlwaysPresent=False
		EndIf

		Object_Bell_Update_Mesh(o,d)

		; Obj pick up
		If Not(o\ObjPickedUp=0 And p\ObjPickUpTimer>0) Then Object_EnforceObjPickUp(o,p)

		; Player collided with object
		If (o\Hit Or o\BombHit) And (Not(o\Mode>2*secs#)) Then

			o\BombHit=False

			; Bling!
			EmitSmartSound(Sound_Bell,o\Entity)
			Animate o\EntityX,1,0.26,1,10 : o\AlwaysPresent=True
			o\Mode=2.625*secs#
			RotateEntity o\Entity, 0, p\Animation\Direction#+180, 0
			o\ObjPickedUp=6

			;Release effect
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_RING, o\Entity, 2)
			spewamount=Rand(1,5)
			For i=1 To spewamount
				Object_SpewRing_Create.tObject(EntityX(o\Entity), EntityY(o\Entity)+1.5, EntityZ(o\Entity), Rnd(-0.4, 0.4), Rnd(0.6, 1.2), Rnd(-0.4, 0.4), 1)
			Next
		
			; Delete the object
			o\State=o\State-1
			If Not(o\State>0) Then
				o\Done=1
				Return
			EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Flicky_Create.tObject(x#, y#, z#)
		o.tObject = New tObject : o\ObjType = OBJTYPE_FLICKY : o\ID=TempAttribute\ObjectID
		o\AlwaysPresent=True
		o\Treasure = New tObject_Treasure : o\HasValuesetTreasure=True

		Object_CreateHitBox(HITBOXTYPE_RING,o,4.5,4.5,4.5)

		Object_Acquire_Position(o,x#,y#,z#)

		o\Mode=Rand(1,4)
		o\State=-2
		o\Treasure\ShardNo=0
		o\Treasure\ShardFlyStopTimer=0.75*secs#

		o\Entity = CreatePivot()
		o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Flicky1+o\Mode-1)), Game\Stage\Root)
		Animate(o\EntityX,1,0.5,1,10)

		PositionEntity o\Entity, x#, y#, z#, 1
		PositionEntity o\EntityX, x#, y#, z#, 1
		RotateEntity o\Entity, 0, Rand(1,360), 0
		RotateEntity o\EntityX, 0, EntityYaw(o\Entity), 0

		EntityType(o\Entity, COLLISION_OBJECT_GOTHRU)
		EntityRadius(o\Entity, 1.5)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Flicky_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		If o\Treasure\ShardFlyStopTimer>0 Then o\Treasure\ShardFlyStopTimer=o\Treasure\ShardFlyStopTimer-timervalue#

		; Movement
		Select o\State
			Case -2:
				If Not(o\Treasure\ShardFlyStopTimer>0) Then o\State=0
			Case -1:
				FreeEntity o\Entity
				FreeEntity o\EntityX
				Delete o\Position
				Delete o
				Return
			Case 1:
				PositionEntity o\Entity, p\Objects\Position\x#, p\Objects\Position\y#+0.5+p\SpeedLength#*0.375, p\Objects\Position\z#
				RotateEntity o\Entity, 0, EntityYaw(p\Objects\Mesh), 0
				MoveEntity o\Entity, 0, 0, -5-2.5*p\ScaleFactor#-5*o\Treasure\ShardNo
				If p\SpeedLength#<0.5 Then
					o\State=2
					For o2.tObject = Each tObject
						If o2\ObjType=OBJTYPE_FLICKY And o2\State=2 Then RotateEntity o2\Entity, 0, (360/5.0)*o2\Treasure\ShardNo, 0 : o2\Treasure\ShardFlyStopTimer=0
					Next
				EndIf
			Case 2:
				If Not(o\Treasure\ShardFlyStopTimer>0) Then
					o\Treasure\ShardFlyStopTimer=0.4*secs#*(1/3.0)
					PositionEntity o\Entity, p\Objects\Position\x#, p\Objects\Position\y#+0.5, p\Objects\Position\z#
					TurnEntity o\Entity, 0, (360/5.0)*(1/3.0), 0
					MoveEntity o\Entity, 0, 0, 3.5+4*p\ScaleFactor#
				EndIf
				If p\SpeedLength#>=0.5 Then
					o\State=1
				EndIf
		End Select
		Select o\State
			Case -2,0:
			Select o\Mode
				Case 1:
					If EntityDistance(o\Entity,p\Objects\Entity)>14.8 Then
						If Not(o\Treasure\ShardFlyStopTimer>0) Then
							o\Treasure\ShardFlyStopTimer=0.25*secs#
							TurnEntity o\Entity, 0, 10*d\Delta, 0
						EndIf
						MoveEntity o\Entity, 0, 0, 0.1125*d\Delta
						If o\Position\y#>=p\Objects\Position\y# Then MoveEntity o\Entity, 0, -0.025*d\Delta, 0 Else MoveEntity o\Entity, 0, 0.025*d\Delta, 0
					Else
						PointEntity o\Entity, p\Objects\Entity
						MoveEntity o\Entity, 0, 0, 0.075*d\Delta
					EndIf
				Case 2:
					If EntityDistance(o\Entity,p\Objects\Entity)>14.8 Then
						If Not(o\Treasure\ShardFlyStopTimer>0) Then
							o\Treasure\ShardFlyStopTimer=0.4*secs#
							TurnEntity o\Entity, 0, 10*d\Delta, 0
						EndIf
						MoveEntity o\Entity, 0, 0, 0.1125*d\Delta
						If o\Position\y#>=p\Objects\Position\y# Then MoveEntity o\Entity, 0, -0.025*d\Delta, 0 Else MoveEntity o\Entity, 0, 0.025*d\Delta, 0
					Else
						PointEntity o\Entity, p\Objects\Entity
						MoveEntity o\Entity, 0, 0, 0.075*d\Delta
					EndIf
				Case 3:
					If Abs(o\Position\y#-p\Objects\Position\y#)<30 Then
						If Not(o\Treasure\ShardFlyStopTimer>0) Then
							o\Treasure\ShardFlyStopTimer=0.75*secs#
							TurnEntity o\Entity, 0, Rand(1,360)*d\Delta, 0
						ElseIf o\Treasure\ShardFlyStopTimer>0.375*secs# Then
							MoveEntity o\Entity, 0, 0.75*d\Delta, 0.15*d\Delta
						ElseIf o\Treasure\ShardFlyStopTimer>0*secs# Then
							MoveEntity o\Entity, 0, -0.85*d\Delta, 0.15*d\Delta
						EndIf
					Else
						MoveEntity o\Entity, 0, 0, 0.15*d\Delta
						If o\Position\y#>=p\Objects\Position\y# Then MoveEntity o\Entity, 0, -0.025*d\Delta, 0 Else MoveEntity o\Entity, 0, 0.025*d\Delta, 0
					EndIf
				Case 4:
					If Not(o\Treasure\ShardFlyStopTimer>0) Then
						o\Treasure\ShardFlyStopTimer=0.25*secs#
						RotateEntity o\Entity, 0, DeltaYaw#(p\Objects\Entity,o\Entity)-180, 0
					EndIf
					MoveEntity o\Entity, 0, 0, -0.16125*d\Delta
					If o\Position\y#>=p\Objects\Position\y# Then MoveEntity o\Entity, 0, -0.025*d\Delta, 0 Else MoveEntity o\Entity, 0, 0.025*d\Delta, 0
			End Select
			If Not(o\TreasureTimer>0) Then
				o\TreasureTimer=(Rand(1,4)/2.0)*secs# : EmitSmartSound(Sound_Flicky,o\Entity)
			Else
				o\TreasureTimer=o\TreasureTimer-timervalue#
			EndIf
		End Select
		If EntityDistance(o\EntityX,o\Entity)>0.5 Then
			Select o\State
				Case 1,2:
					PointEntity o\EntityX, o\Entity
					If EntityDistance(o\EntityX,o\Entity)>30 Then
						MoveEntity o\EntityX, 0, 0, 0.5*EntityDistance(o\EntityX,o\Entity)*p\Physics\ICETRIGGER3#*d\Delta
					Else
						If p\SpeedLength#<0.15 Then
							MoveEntity o\EntityX, 0, 0, (p\SpeedLength#+0.15)*p\Physics\ICETRIGGER3#*d\Delta
						Else
							MoveEntity o\EntityX, 0, 0, (p\SpeedLength#)*p\Physics\ICETRIGGER3#*d\Delta
						EndIf
					EndIf
				Default:
					PositionEntity o\EntityX, o\Position\x#, o\Position\y#, o\Position\z#, 1
					If o\State=0 And o\Mode=4 Then RotateEntity o\EntityX, 0, EntityYaw(o\Entity)-180, 0 Else RotateEntity o\EntityX, 0, EntityYaw(o\Entity), 0
			End Select
		EndIf
		If EntityDistance(o\EntityX,o\Entity)>50 Then PositionEntity o\EntityX, o\Position\x#, o\Position\y#, o\Position\z#, 1

		; Player collided with object
		If o\Hit And o\State=0 And Game\Gameplay\Flickies<5 Then
			; Add to counter
			Gameplay_AddFlickies(1)
			o\Treasure\ShardNo=Game\Gameplay\Flickies
			o\State=1
			EmitSmartSound(Sound_FlickyGet,o\Entity)
		EndIf

	End Function

Function Object_Flicky_Tag(o.tObject, c.tCamera)
		If EntityInView(o\EntityX,c\Entity) Then
			height# = 4.5
			CameraProject c\Entity, EntityX (o\Entity), EntityY (o\Entity)+height#, EntityZ (o\Entity)
			x = ProjectedX () - 1
			y = ProjectedY () - 1
			StartDraw()
				SetBlend(FI_ALPHABLEND)
				SetAlpha(1.0)
				SetScale(GAME_WINDOW_SCALE#*0.45, GAME_WINDOW_SCALE#*0.45)
				SetColor(255, 255, 255)
				DrawImageEx(INTERFACE(Interface_Indicator), x, y)
				SetColor(255, 255, 255)
				SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			EndDraw()
		EndIf
	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D