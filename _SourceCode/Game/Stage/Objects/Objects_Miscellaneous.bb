Function Object_RocketEffect(o.tObject,entity)
	Rocket = CreateCube()
	PositionEntity(Rocket, EntityX(entity,1), EntityY(entity,1), EntityZ(entity,1))
	ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ROCKETFUMES, Rocket)
	FreeEntity(Rocket)
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Check_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, mode=1)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID

		Object_CreateHitBox(HITBOXTYPE_SPEEDY_CHECKPOINT,o,8.5,34.0,8.5)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\State=0
		o\Mode=mode

		Select mode
			Case 1:
			o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint)), Game\Stage\Root)
			o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_CheckpointX)), Game\Stage\Root)
			o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_CheckpointB)), Game\Stage\Root)
			Case 2:
			o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint2)), Game\Stage\Root)
			o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint2X)), Game\Stage\Root)
			o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint2B)), Game\Stage\Root)
			Case 3:
			o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint3)), Game\Stage\Root)
			o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint3X)), Game\Stage\Root)
			o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_Checkpoint3B)), Game\Stage\Root)
		End Select
		
		EntityType(o\Entity3,COLLISION_NONE)
		
		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Check_Update(o.tObject, p.tPlayer)

		If p\SpeedLength#<=1.5 Then
			EntityType(o\Entity,COLLISION_OBJECT)
			EntityType(o\Entity2,COLLISION_OBJECT)
		Else
			EntityType(o\Entity,COLLISION_NONE)
			EntityType(o\Entity2,COLLISION_NONE)
		EndIf
		
 		If o\Hit And o\State=0 And p\Objects\Position\y#>o\Position\y#-6 Then
			; Add to counter
			If Game\Gameplay\DiedOnce=0 Then Gameplay_AddPerfectBonus(100)

			; Save situation
			Player_SaveSituation(o,p)
			Game\Interface\FlashCheckTimerTimer=2.2*secs#

			; Sound effect!
			EmitSmartSound(Sound_Check,o\Entity)
			
			Game\CheckSpeed=Int(p\SpeedLength*10)

			; Delete the object
			o\State=1
		EndIf

		Select o\State
			Case 0:
				ShowEntity(o\Entity)
				ShowEntity(o\Entity3)
				HideEntity(o\Entity2)
				Animate o\Entity,1,0.5,1,0
			Case 1:
				ShowEntity(o\Entity)
				HideEntity(o\Entity2)
				ShowEntity(o\Entity3)
				If p\SpeedLength#>= 0 Then Animate o\Entity,3,0.2,2,0
				If p\SpeedLength#>= 2 Then Animate o\Entity,3,0.4,2,0
				If p\SpeedLength#>= 4 Then Animate o\Entity,3,0.5,2,0
				If p\SpeedLength#>= 6 Then Animate o\Entity,3,0.6,2,0
				o\State=2
			Case 2:
				ShowEntity(o\Entity)
				HideEntity(o\Entity2)
				HideEntity(o\Entity3)
				If Not(Animating(o\Entity)) Then o\State=3
			Case 3:
				ShowEntity(o\Entity2)
				HideEntity(o\Entity)
				HideEntity(o\Entity3)
				Animate o\Entity2,1,0.5,1,0
		End Select
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Balloon_Create.tObject(x#, y#, z#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		Gameplay_AddTotalBalloons(1)

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,6.5,5.5,6.5)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		Select(Rand(1,4))
			Case 1: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Balloon1)), Game\Stage\Root)
			Case 2: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Balloon2)), Game\Stage\Root)
			Case 3: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Balloon3)), Game\Stage\Root)
			Case 4: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Balloon4)), Game\Stage\Root)
		End Select
		Animate o\Entity,1,0.1

		SmartEntity(Mesh_Balloonpiece1)
		SmartEntity(Mesh_Balloonpiece2)
		SmartEntity(Mesh_Balloonpiece3)
		SmartEntity(Mesh_Balloonpiece4)
		SmartEntity(Mesh_Balloonpiece5)

		EntityType(o\Entity,COLLISION_OBJECT_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Balloon_Update_Timer(o.tObject)
		If o\State>0 Then
			o\State=o\State-timervalue#
			HideEntity(o\Entity)
			o\CanHoming=False
			o\BombHit=False : o\GotAssignedBomb=False
		Else
			ShowEntity(o\Entity)
			o\CanHoming=True
		EndIf
	End Function

Function Object_Balloon_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		Object_Balloon_Update_Timer(o)

		If (Not(o\State>0)) Then
			; Psychokinesis
			Object_EnforcePsychokinesis(o,p,d)
			
			; Player collided with object
			If o\Hit Or o\BombHit Then
			
				; Add to counter
				If o\Mode=0 Then
					Gameplay_AddScore(50)
					Gameplay_AddBalloons(1)
					o\Mode=1
				EndIf
				
				
				
				; Bling!
				EmitSmartSound(Sound_Balloon,o\Entity)

				;Release effect
				Object_Pieces_Create(False,o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1)

					; Make Sonic a move
				If o\BombHit=False And o\Psychoed=0 Then
					If p\Action=ACTION_HOMING Then Player_SetSpeed(p,2.3)
					Player_JumpActionInteract(p)
				EndIf
			
				; Delete the object
				o\CanHoming=False
				o\State=5*secs#
				Return
			EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Spike_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, spikebarsize=0, forcespiketype=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		If forcespiketype>0 Then o\ObjType=forcespiketype
		o\ThisIsASpike=True : o\Spike = New tObject_Spike : o\HasValuesetSpike=True

		If o\ObjType=OBJTYPE_SPIKEBAR Then o\Spike\BarSize=spikebarsize

		Select o\ObjType
			Case OBJTYPE_SPIKEBALL: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,5.8,5.8,5.8)
			Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB,OBJTYPE_SPIKECRUSHER: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,7.5,7.5,7.5)
			Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP,OBJTYPE_SPIKECYLINDER: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,5.5,6.5,5.5)
			Case OBJTYPE_SPIKEBAR: Object_CreateHitBox(HITBOXTYPE_NORMAL_SPIKEBAR,o,7.5,7.5,7.5)
			Case OBJTYPE_SPIKESWING: Object_CreateHitBox(HITBOXTYPE_NORMAL_SPIKESWING,o,8,8,8)
			Case OBJTYPE_SPIKESWINGBALL: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,8,8,8)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select o\ObjType
			Case OBJTYPE_SPIKEBALL:
				Object_Acquire_Rotation(o,Rand(1,360),Rand(1,360),0)
			Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_SPEWSPIKEBOMB:
				Object_Acquire_Rotation(o,0,yaw#,0)
			Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP,OBJTYPE_SPIKECYLINDER:
				Object_Acquire_Rotation(o,pitch#,yaw#,roll#)
			Case OBJTYPE_SPIKEBAR,OBJTYPE_SPIKESWING,OBJTYPE_SPIKESWINGBALL:
				Object_Acquire_Rotation(o,0,yaw#,0)
		End Select

		Select o\ObjType
			Case OBJTYPE_SPIKEBALL:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBall)), Game\Stage\Root)
				o\EntityCube = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBallCube)), o\Entity)
			Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBomb)), Game\Stage\Root)
				SmartEntity(Mesh_SpikeBombPiece1) : SmartEntity(Mesh_SpikeBombPiece2)
				EntityRadius(o\Entity, 3)
				If o\ObjType=OBJTYPE_SPEWSPIKEBOMB Then o\Spike\SpikeTimer=3*secs#
			Case OBJTYPE_SPIKECRUSHER:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeCrusher)), Game\Stage\Root)
				SmartEntity(Mesh_SpikeCrusherPiece1) : SmartEntity(Mesh_SpikeCrusherPiece2)
				EntityRadius(o\Entity, 3)
			Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeDrill)), Game\Stage\Root)
				Animate(o\Entity,3,1,1)
				o\EntityCube = CopyEntity(MESHES(SmartEntity(Mesh_SpikeDrillCube)), o\Entity)
				o\EntityCube2 = CopyEntity(MESHES(SmartEntity(Mesh_SpikeDrillCube2)), o\Entity)
			Case OBJTYPE_SPIKEBAR:
				Select spikebarsize
					Case 1:
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBar1)), Game\Stage\Root)
						o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBarPoles1)), Game\Stage\Root)
					Case 2:
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBar2)), Game\Stage\Root)
						o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBarPoles2)), Game\Stage\Root)
					Case 3:
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBar3)), Game\Stage\Root)
						o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBarPoles3)), Game\Stage\Root)
				End Select
			Case OBJTYPE_SPIKESWING:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeSwingChain)), Game\Stage\Root)
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_SpikeSwing)), Game\Stage\Root)
			Case OBJTYPE_SPIKECYLINDER:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeCylinder)), Game\Stage\Root)
				o\EntityCube = CopyEntity(MESHES(SmartEntity(Mesh_SpikeCylinderCube)), o\Entity)
			Case OBJTYPE_SPIKESWINGBALL:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SpikeSwingBall)), Game\Stage\Root)
		End Select

		Object_Spike_CollisionReset(o)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Spike_CollisionReset(o.tObject)
		EntityType(o\Entity,COLLISION_OBJECT_GOTHRU)
		If o\HasEntity2 Then EntityType(o\Entity2,COLLISION_OBJECT_GOTHRU)
		If o\HasEntityCube Then EntityType(o\EntityCube,COLLISION_OBJECT_GOTHRU)
		If o\HasEntityCube2 Then EntityType(o\EntityCube2,COLLISION_OBJECT_GOTHRU)
		o\ForceCollisionReset=1
	End Function

Function Object_Spike_Collision(o.tObject)
		If pp(1)\Action=ACTION_TORNADO Then
			Object_Spike_CollisionReset(o)
		ElseIf o\ForceCollisionReset=1 Or o\Psychoed>0 Then
			o\ForceCollisionReset=0
			Select o\ObjType
				Case OBJTYPE_SPIKEBALL:
					If o\Psychoed>0 Then
						EntityType(o\EntityCube,COLLISION_OBJECT_GOTHRU)
						o\ForceCollisionReset=1
					Else
						EntityType(o\EntityCube,COLLISION_OBJECT)
					EndIf
				Case OBJTYPE_SPIKECYLINDER:
					EntityType(o\EntityCube,COLLISION_OBJECT)
				Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_SPEWSPIKEBOMB:
					EntityType(o\Entity,COLLISION_OBJECT)
				Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP:
					EntityType(o\EntityCube,COLLISION_OBJECT)
					EntityType(o\EntityCube2,COLLISION_OBJECT)
				Case OBJTYPE_SPIKEBAR:
					EntityType(o\Entity,COLLISION_OBJECT)
					EntityType(o\Entity2,COLLISION_OBJECT)
				Case OBJTYPE_SPIKESWING:
					EntityType(o\Entity2,COLLISION_OBJECT)
				Case OBJTYPE_SPIKESWINGBALL:
					EntityType(o\Entity,COLLISION_OBJECT)
			End Select
		EndIf
	End Function

Function Object_Spike_Movement(o.tObject, p.tPlayer, d.tDeltaTime)
		If o\Spike\SpikeTimer>0 Then o\Spike\SpikeTimer=o\Spike\SpikeTimer-timervalue#

		Select o\ObjType
			Case OBJTYPE_SPIKEDRILL:
				Animate(o\Entity,3,1,2,5)
				EntityType(o\EntityCube,COLLISION_OBJECT)
			Case OBJTYPE_SPIKECRUSHER:
				If EntityDistance(o\Entity,p\Objects\Entity)<86 Then
					If p\Objects\Position\y#<o\Position\y# And Abs(p\Objects\Position\x#-o\Position\x#)<10 And Abs(p\Objects\Position\z#-o\Position\z#)<10 Then MoveEntity o\Entity, 0, -2.45*d\Delta, 0
				EndIf
			Case OBJTYPE_SPIKETIMED:
				If (Not(o\Spike\SpikeTimer>0)) Then
					o\Spike\SpikeTimer=5*secs#
				ElseIf o\Spike\SpikeTimer>0*secs# And o\Spike\SpikeTimer<0.03*secs# Then
					If o\InView And (Not(ChannelPlaying(o\Spike\Channel_Spike))) Then o\Spike\Channel_Spike=EmitSmartSound(Sound_SpikeDrill2,o\Entity)
				ElseIf o\Spike\SpikeTimer>0.03*secs# And o\Spike\SpikeTimer<2.5*secs# Then
					Animate(o\Entity,3,1,1,5)
					EntityType(o\EntityCube,COLLISION_NONE)
				ElseIf o\Spike\SpikeTimer>2.5*secs# And o\Spike\SpikeTimer<2.53*secs# Then
					If o\InView And (Not(ChannelPlaying(o\Spike\Channel_Spike))) Then o\Spike\Channel_Spike=EmitSmartSound(Sound_SpikeDrill1,o\Entity)
				ElseIf o\Spike\SpikeTimer>2.53*secs# And o\Spike\SpikeTimer<5*secs# Then
					Animate(o\Entity,3,1,2,5)
					EntityType(o\EntityCube,COLLISION_OBJECT)
				EndIf
			Case OBJTYPE_SPIKETRAP:
				If o\Spike\SpikeTimer>0*secs# And o\Spike\SpikeTimer<0.03*secs# Then
					If o\InView And (Not(ChannelPlaying(o\Spike\Channel_Spike))) Then o\Spike\Channel_Spike=EmitSmartSound(Sound_SpikeDrill2,o\Entity)
				ElseIf o\Spike\SpikeTimer>0.03*secs# And o\Spike\SpikeTimer<0.5*secs# Then
					Animate(o\Entity,3,1,1,5)
					EntityType(o\EntityCube,COLLISION_NONE)
				ElseIf o\Spike\SpikeTimer>0.5*secs# And o\Spike\SpikeTimer<0.53*secs# Then
					If o\InView And (Not(ChannelPlaying(o\Spike\Channel_Spike))) Then o\Spike\Channel_Spike=EmitSmartSound(Sound_SpikeDrill1,o\Entity)
				ElseIf o\Spike\SpikeTimer>0.53*secs# And o\Spike\SpikeTimer<3*secs# Then
					Animate(o\Entity,3,1,2,5)
					EntityType(o\EntityCube,COLLISION_OBJECT)
				EndIf
			Case OBJTYPE_SPIKEBAR:
				Select o\Spike\BarMover
					Case 0:
						o\Spike\BarMover=1
						o\Spike\SpikeTimer=1.6*secs#
						Animate(o\Entity,1,0.1,1)
					Case 1:
						If (Not(Abs(o\Position\y#-(o\InitialPosition\y#-17.5))<2)) And o\Spike\SpikeTimer>0 Then
							MoveEntity o\Entity, 0, -0.35*d\Delta, 0
						Else
							o\Spike\BarMover=2
							o\Spike\SpikeTimer=3.2*secs#
							Animate(o\Entity,1,0.1,1)
						EndIf
					Case 2:
						If (Not(Abs(o\Position\y#-(o\InitialPosition\y#+17.5))<2)) And o\Spike\SpikeTimer>0 Then
							MoveEntity o\Entity, 0, 0.35*d\Delta, 0
						Else
							o\Spike\BarMover=1
							o\Spike\SpikeTimer=3.2*secs#
							Animate(o\Entity,1,-0.1,1)
						EndIf
				End Select
			Case OBJTYPE_SPIKESWING:
				TurnEntity o\Entity, 0, 2.5*d\Delta, 0
				TurnEntity o\Entity2, 0, 2.5*d\Delta, 0
				If (Not(o\Spike\SpikeTimer>0)) Then
					If o\InView And (Not(ChannelPlaying(o\Spike\Channel_Spike))) Then o\Spike\Channel_Spike=EmitSmartSound(Sound_Swinger,o\Entity)
					o\Spike\SpikeTimer=1.02*secs#
				EndIf
			Case OBJTYPE_SPEWSPIKEBOMB:
				MoveEntity(o\Entity, 0, -2*d\Delta, 0)
				If Not(o\Spike\SpikeTimer>0) Then o\ExplodeHit=True
			Case OBJTYPE_SPIKESWINGBALL:
				Select o\State
					Case 0:
						If o\Position\y#>o\InitialPosition\y#-20 And o\Spike\SpikeTimer>0 Then
							MoveEntity o\Entity, 0, -2.33*d\Delta, 0
						Else
							If o\InView Then EmitSmartSound(Sound_Swinger,o\Entity)
							o\Spike\SpikeTimer=1*secs# : o\State=2
						EndIf
					Case 1:
						If o\Position\y#<o\InitialPosition\y# And o\Spike\SpikeTimer>0 Then
							MoveEntity o\Entity, 0, 2.33*d\Delta, 0
						Else
							If o\InView Then EmitSmartSound(Sound_Swinger,o\Entity)
							o\Spike\SpikeTimer=1*secs# : o\State=3
						EndIf
					Case 2:
						If (Not(o\Spike\SpikeTimer>0)) Then o\Spike\SpikeTimer=1.5*secs# : o\State=1
					Case 3:
						If (Not(o\Spike\SpikeTimer>0)) Then o\Spike\SpikeTimer=1.5*secs# : o\State=0
				End Select
		End Select
	End Function

Function Object_Spike_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Update collision
		Object_Spike_Collision(o)

		; Psychokinesis, stun
		Select o\ObjType
			Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB,OBJTYPE_SPIKECRUSHER:
				Object_EnforcePsychokinesis(o,p,d)
				Object_EnforceStun(o,p,d)
		End Select

		; Bomb monitor blow up
		Select o\ObjType
			Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB,OBJTYPE_SPIKECRUSHER:
				If p\BombMonitorTimer>0 Then
					If EntityDistance(o\Entity,p\Objects\Entity)<150 Then o\BombHit=True
				EndIf
		End Select

		; Movement
		Object_Spike_Movement(o,p,d)
		
		; Player collided with object
		If o\ExplodeHit Or o\Hit Or (o\BombHit And (o\ObjType=OBJTYPE_SPIKEBOMB Or o\ObjType=OBJTYPE_SPEWSPIKEBOMB Or o\ObjType=OBJTYPE_SPIKECRUSHER)) Then
		If (Not(o\ObjType=OBJTYPE_SPIKETRAP Or o\ObjType=OBJTYPE_SPIKETIMED)) Or (o\ObjType=OBJTYPE_SPIKETIMED And o\Spike\SpikeTimer>2.5*secs# And o\Spike\SpikeTimer<5*secs#) Or (o\ObjType=OBJTYPE_SPIKETRAP And o\Spike\SpikeTimer>0.5*secs# And o\Spike\SpikeTimer<3*secs#) Then

			; Hurt Sonic
			If (o\ExplodeHit=False Or o\Hit) And (Not(p\HurtTimer>0)) And o\BombHit=False And o\Psychoed=0 Then
				If Game\Invinc=0 And Game\Victory=0 And (Not(p\DontGetHurtTimer>0)) Then EmitSmartSound(Sound_Spikes,o\Entity)
				Player_Hit(p)
			EndIf

			;Release effect
			Select o\ObjType
				Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB,OBJTYPE_SPIKECRUSHER:
					Object_Pieces_Create(False,o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#)
			End Select

			; Delete the object
			Select o\ObjType
				Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB,OBJTYPE_SPIKECRUSHER:
					EmitSmartSound(Sound_Bombed,o\Entity)
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BOMB, o\Entity)
					If o\ObjType=OBJTYPE_SPIKECRUSHER Then EmitSmartSound(Sound_Crusher,o\Entity)
					If Not(o\ObjType=OBJTYPE_SPEWSPIKEBOMB) Then
						o\Done=1
					Else
						FreeEntity o\Entity
						Delete o\Position
						Delete o\Rotation
						Delete o
						Return
					EndIf
				Case OBJTYPE_SPIKESWING:
					If o\ExplodeHit Then
						FreeEntity o\Entity
						FreeEntity o\Entity2
						Delete o\Position
						Delete o\Rotation
						Delete o
						Return
					EndIf
			End Select

		Else

			If (Not(o\Spike\SpikeTimer>0)) Then o\Spike\SpikeTimer=3.35*secs#

		EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Spout_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Trap = New tObject_Trap : o\HasValuesetTrap=True

		Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,3,30.5,3)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		Select o\ObjType
			Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Spout)), Game\Stage\Root)
			Case OBJTYPE_SHOCKSPOUT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_ShockBar)), Game\Stage\Root)
		End Select

		EntityType(o\Entity,COLLISION_OBJECT)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Spout_Particles(o.tObject, p.tPlayer, far=False)
		If Not(o\Trap\SpoutTimer>0) Then
			o\Trap\SpoutTimer=9*secs#
		ElseIf o\Trap\SpoutTimer>4*secs# Then
			Select o\ObjType
				Case OBJTYPE_FLAMESPOUT:
				  If (EntityY(o\Entity) > Game\Stage\Properties\WaterLevel) Then
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FIRE, o\Entity, 0, 0, 0, 0, 0, 0.05)
					If (Not far) And ChannelPlaying(o\Trap\Channel_Spout)=False Then o\Trap\Channel_Spout=EmitSmartSound(Sound_Fire,o\Entity)
				  EndIf
				Case OBJTYPE_ICESPOUT:
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ICE, o\Entity, 0, 0, 0, 0, 0, 0.05)
					If (Not far) And ChannelPlaying(o\Trap\Channel_Spout)=False Then o\Trap\Channel_Spout=EmitSmartSound(Sound_WindBlow,o\Entity)
				Case OBJTYPE_SHOCKSPOUT:
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_SHOCK, o\Entity, 0, 0, 0, 0, 0, 0.05)
					If (Not far) And ChannelPlaying(o\Trap\Channel_Spout)=False Then o\Trap\Channel_Spout=EmitSmartSound(Sound_EnemyElectric,o\Entity)
			End Select
		Else
			StopChannel(o\Trap\Channel_Spout)
		EndIf
	End Function

Function Object_Spout_Update(o.tObject, p.tPlayer)

		; Update timer
		If o\Trap\SpoutTimer>0 Then o\Trap\SpoutTimer=o\Trap\SpoutTimer-timervalue#

		; Particles and sound
		Object_Spout_Particles(o,p)
		
		; Player collided with object
		If o\Hit And (Not(p\HurtTimer>0)) And p\Objects\Position\y#>o\Position\y# Then
		If o\Trap\SpoutTimer>4*secs# Then

			; Hurt Sonic
			Select o\ObjType
				Case OBJTYPE_FLAMESPOUT:
					If p\Underwater=0 And (Not(Game\Shield=OBJTYPE_FSHIELD Or p\Character=CHAR_BLA)) Then Player_Hit(p)
				Case OBJTYPE_ICESPOUT:
					Player_Action_Freeze_Initiate2(p)
				Case OBJTYPE_SHOCKSPOUT:
					If (Not(Game\Shield=OBJTYPE_TSHIELD)) Then Player_Hit(p)
			End Select

		EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Switch_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, switchno, switchstatus, power#=0, invisi=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Switch = New tObject_Switch : o\HasValuesetSwitch=True

		Select o\ObjType
			Case OBJTYPE_SWITCHAIR: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,5.5,5.5,5.5)
			Default: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,3.5,3.5,3.5)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select o\ObjType
			Case OBJTYPE_SWITCHAIR: Object_Acquire_Rotation(o,0,yaw#,0)
			Default: Object_Acquire_Rotation(o,0,Rand(0,360),0)
		End Select

		Select o\ObjType
			Case OBJTYPE_SWITCHWATER:
				Object_Acquire_Power(o,power#)
			Default:
				o\Switch\s1 = Object_SwitchManager_Create.tSwitchManager(switchno, switchstatus)
				o\Switch\SwitchNo[0]=switchno
		End Select

		Select o\ObjType
			Case OBJTYPE_SWITCHAIR:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SwitchAir)), Game\Stage\Root)
			Case OBJTYPE_SWITCHWATER:
				If invisi=0 Then
					o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SwitchOn)), Game\Stage\Root)
					o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_SwitchOff)), Game\Stage\Root)
					o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_SwitchBase)), Game\Stage\Root)
				Else
					o\Entity = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					o\EntityX = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					o\Entity2 = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				EndIf
				o\State=invisi
			Default:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SwitchOn)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_SwitchOff)), Game\Stage\Root)
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_SwitchBase)), Game\Stage\Root)
				If o\ObjType=OBJTYPE_SWITCHBASE Then o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_SwitchClosedBase)), Game\Stage\Root)
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Switch_Appear(o.tObject)
		Select o\ObjType
			Case OBJTYPE_SWITCHWATER:
				If Abs(Game\Stage\Properties\WaterLevelTarget-o\Power#)<2 Then
					HideEntity(o\Entity) : ShowEntity(o\EntityX)
					ShowEntity(o\Entity2)
					If (Not(Animating(o\EntityX))) Then Animate(o\EntityX,1,0.02,1)
				Else
					ShowEntity(o\Entity) : HideEntity(o\EntityX)
					ShowEntity(o\Entity2)
					If (Not(Animating(o\Entity))) Then Animate(o\Entity,1,0.02,1)
				EndIf
			Default:
				If (Not(o\ObjType=OBJTYPE_SWITCHBASE)) Or o\Switch\SwitchTopBrought Then
					If o\Switch\SwitchNo[0]>0 Then
						Select o\Switch\s1\Active
						Case 0:
							Select o\ObjType
								Case OBJTYPE_SWITCHAIR:
									ShowEntity(o\Entity)
									EntityAlpha(o\Entity,0.5)
									If (Not(Animating(o\Entity))) Then Animate(o\Entity,1,0.2,1)
								Default:
									HideEntity(o\Entity) : ShowEntity(o\EntityX)
									ShowEntity(o\Entity2)
									If (Not(Animating(o\EntityX))) Then Animate(o\EntityX,1,0.02,1)
							End Select
						Case 1:
							Select o\ObjType
								Case OBJTYPE_SWITCHAIR:
									ShowEntity(o\Entity)
									EntityAlpha(o\Entity,1)
									If (Not(Animating(o\Entity))) Then Animate(o\Entity,1,0.2,1)
								Default:
									ShowEntity(o\Entity) : HideEntity(o\EntityX)
									ShowEntity(o\Entity2)
									If (Not(Animating(o\Entity))) Then Animate(o\Entity,1,0.02,1)
							End Select
						End Select
					EndIf
					If o\ObjType=OBJTYPE_SWITCHBASE Then HideEntity(o\Entity3)
				Else
					HideEntity(o\Entity) : HideEntity(o\EntityX)
					HideEntity(o\Entity2) : ShowEntity(o\Entity3)
				EndIf
		End Select
	End Function

Function Object_Switch_Update(o.tObject, p.tPlayer)

		; Hide/show according to active
		Object_Switch_Appear(o)

		If (Not(o\ObjType=OBJTYPE_SWITCHBASE)) Or o\Switch\SwitchTopBrought Then
			If o\Switch\SwitchTimer>0 Then o\Switch\SwitchTimer=o\Switch\SwitchTimer-timervalue#
			
			; Player collided
	 		If (o\Switch\SwitchNo[0]>0 Or o\ObjType=OBJTYPE_SWITCHWATER) And (o\Hit Or o\BombHit) Then
				If (Not(o\Switch\SwitchTimer>0)) Then
					Select o\ObjType
						Case OBJTYPE_SWITCHWATER:
							Game\Stage\Properties\WaterLevelChanged=1
							Game\Stage\Properties\WaterLevelTarget=o\Power#
						Default: o\Switch\s1\Active=Abs(o\Switch\s1\Active-1)
					End Select
					o\Switch\SwitchTimer=1.05*secs#
					Select o\ObjType
						Case OBJTYPE_SWITCHAIR:
							EmitSmartSound(Sound_SwitchAir,o\Entity)
							Animate(o\Entity,3,0.36,2)
						Default:
							If o\State=0 Then 
								Select o\Switch\s1\Active
									Case 0 : EmitSmartSound(Sound_SwitchOff,o\Entity)
									Case 1 : EmitSmartSound(Sound_SwitchOn,o\Entity)
								End Select
							EndIf 
										
							RotateEntity(o\Entity,0,p\Animation\Direction#,0)
							RotateEntity(o\EntityX,0,p\Animation\Direction#,0)
							Animate(o\Entity,3,0.2,2) : Animate(o\EntityX,3,0.2,2)
					End Select
					Game\InsideBoxCheckerTimer=0
				EndIf
				o\BombHit=False
			EndIf
		ElseIf o\ObjType=OBJTYPE_SWITCHBASE Then
			If o\Switch\SwitchTopFound Then
				If EntityDistance(o\Entity,o\Switch\SwitchTop\Entity)<o\HitBox\y#*2 Then
					o\Switch\SwitchTopBrought=True
					o\Switch\SwitchTop\Done=1
					If Not o\Switch\SwitchTop\ObjPickedUp=0 Then p\ObjPickUp=0
				EndIf
			EndIf
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_SwitchTop_Create.tObject(x#, y#, z#, yaw#, switchno#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\Switch = New tObject_Switch : o\HasValuesetSwitch=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,3.5,3.5,3.5)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(0,360),0)

		o\Switch\SwitchNo[0]=switchno

		o\Pivot=CreatePivot()
		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_SwitchClosedTop)), Game\Stage\Root)
		EntityType(o\Pivot,COLLISION_OBJECT2)
		EntityRadius(o\Pivot,0)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_SwitchTop_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		; Update collision
		If p\Flags\Attacking Or o\Psychoed>0 Or o\ObjPickedUp>0 Or p\ObjPickUpTimer>0 Then
			EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)
		Else
			EntityType(o\Pivot,COLLISION_OBJECT2)
		EndIf

		; Gravity
		Object_EnforceGravity(o,d)

		; Stun
		Object_EnforceStun(o,p,d,False)

		; Psychokinesis
		Object_EnforcePsychokinesis(o,p,d)

		; Obj pick up
		Object_EnforceObjPickUp(o,p)
		
		; Player collided with object
		If (Not(p\ObjPickUpTimer>0)) And o\ObjPickedUp=0 Then
			If o\BombHit Then o\BombHit=False : o\ObjPickedUp=-1
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Laser_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, switchno1=0, switchno2=0, switchno3=0, requirement#=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Switch = New tObject_Switch : o\HasValuesetSwitch=True

		Select o\ObjType
			Case OBJTYPE_LASERV,OBJTYPE_RINGGATEV: Object_CreateHitBox(HITBOXTYPE_NORMAL_LASER,o,5.5,12.375,5.5)
			Case OBJTYPE_LASERH,OBJTYPE_RINGGATEH: Object_CreateHitBox(HITBOXTYPE_NORMAL_LASER,o,5.5,5.5,12.375)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		If yaw#=180 Then yaw#=179
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Switch\SwitchNo[0]=switchno1
		o\Switch\SwitchNo[1]=switchno2
		o\Switch\SwitchNo[2]=switchno3
		o\Power#=requirement#

		Select o\ObjType
			Case OBJTYPE_LASERV:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_LaserV)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_LaserVX)), Game\Stage\Root)
			Case OBJTYPE_LASERH:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_LaserH)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_LaserHX)), Game\Stage\Root)
			Case OBJTYPE_RINGGATEV:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Laser2V)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Laser2VX)), Game\Stage\Root)
			Case OBJTYPE_RINGGATEH:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Laser2H)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Laser2HX)), Game\Stage\Root)
		End Select

		Return o
	End Function
	
	; =========================================================================================================

Function Object_Laser_Update_SwitchControl(o.tObject, p.tPlayer)
		; Hide/show according to active
		If Object_WhetherHasSwitches(o) Or (Not(o\ObjType=OBJTYPE_LASERV Or o\ObjType=OBJTYPE_LASERH)) Then
			Select o\ObjType
				Case OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
					If Game\Gameplay\Rings<o\Power# Then
						If Not(o\Switch\SwitchOn=1) Then o\Switch\SwitchOn=1
					Else
						If Not(o\Switch\SwitchOn=0) Then o\Switch\SwitchOn=0
					EndIf
				Default:
					Object_SwitchManager_PerObjectUpdate(o)
			End Select
			ShowEntity(o\Entity)
			Select o\Switch\SwitchOn
				Case 0: HideEntity(o\EntityX)
				Case 1: ShowEntity(o\EntityX)
			End Select
		EndIf
	End Function
	
Function Object_Laser_Update(o.tObject, p.tPlayer)

		Object_Laser_Update_SwitchControl(o,p)

		; Manage collision
		If p\InvisibilityTimer>0 And (o\ObjType=OBJTYPE_LASERH Or o\ObjType=OBJTYPE_LASERV) Then
			EntityType(o\EntityX,COLLISION_NONE)
		Else
			EntityType(o\EntityX,COLLISION_OBJECT)
		EndIf
		
		; Player collided with object
		If o\Switch\SwitchOn=1 And o\Hit And (Not(p\HurtTimer>0)) And p\Invisibility=0 Then
			If Menu\Stage>0 Then
				Player_Hit(p)
			Else
				Player_Die(p)
			EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Teleporter_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, teleporterno, teleportername$=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Teleporter = New tObject_Teleporter : o\HasValuesetTeleporter=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,3,9,3)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Teleporter\TeleporterNo=teleporterno

		If o\ObjType=OBJTYPE_TELEPORTER2 Then
			j=0
			For i=0 To StageAmount
				If StageName$(i)=teleportername$ And j=0 Then j=i
			Next
			o\Teleporter\TeleporterNo=j
		EndIf

		Select o\ObjType
			Case OBJTYPE_TELEPORTER2: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Teleporter4)), Game\Stage\Root)
			Case OBJTYPE_TELEPORTER3: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Teleporter2)), Game\Stage\Root)
			Case OBJTYPE_TELEPORTER4: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Transporter)), Game\Stage\Root)
			Case OBJTYPE_TELEPORTER5: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Teleporter3)), Game\Stage\Root)
			Case OBJTYPE_TELEPORTER6: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Teleporter4)), Game\Stage\Root)
			Case OBJTYPE_TELEPORTEREND: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_TeleporterEnd)), Game\Stage\Root)
			Default: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Teleporter)), Game\Stage\Root)
		End Select
		EntityType(o\Entity,COLLISION_OBJECT)

		If o\ObjType=OBJTYPE_TELEPORTER Or o\ObjType=OBJTYPE_TELEPORTEREND Then
			For o2.tObject = Each tObject
				If o2\ObjType=OBJTYPE_TELEPORTER Or o2\ObjType=OBJTYPE_TELEPORTEREND Then
					If o2\Teleporter\TeleporterNo=o\Teleporter\TeleporterNo And o2\Teleporter\TeleporterFound=False And (Not(o2=o)) Then
						o\Teleporter\OtherTeleporter=o2 : o\Teleporter\TeleporterFound=True
						o2\Teleporter\OtherTeleporter=o : o2\Teleporter\TeleporterFound=True
					EndIf
				EndIf
			Next
		EndIf

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Teleporter_Update(o.tObject, p.tPlayer)
	
	If o\Teleporter\RestrictTeleportTimer>0 Then o\Teleporter\RestrictTeleportTimer=o\Teleporter\RestrictTeleportTimer-timervalue#
	
	If Not(o\ObjType=OBJTYPE_TELEPORTEREND) Then
 		If (o\Teleporter\TeleporterNo>0 Or (Not(o\ObjType=OBJTYPE_TELEPORTER Or o\ObjType=OBJTYPE_TELEPORTER2))) And o\Hit And (Not(o\Teleporter\RestrictTeleportTimer>0)) Then
			If Not(p\TeleportTimer>0) Then
				p\TeleportTimer=2*secs#
				PostEffect_Create_FadeOut(0.010, 255, 255, 255)
			ElseIf p\TeleportTimer<0.5*secs# Then
				p\TeleportTimer=0
				Game\ControlLock=0.25*secs#
				PlaySmartSound(Sound_Teleport)
				PostEffect_Create_FadeIn(0.018, 255, 255, 255)
				o\Teleporter\RestrictTeleportTimer=2.5*secs#
				Select o\ObjType
					Case OBJTYPE_TELEPORTER:
						If o\Teleporter\TeleporterFound Then Player_Spawn(o\Teleporter\OtherTeleporter\Position\x#,o\Teleporter\OtherTeleporter\Position\y#+2.25,o\Teleporter\OtherTeleporter\Position\z#,o\Teleporter\OtherTeleporter\Rotation\y#) 
						o\Teleporter\OtherTeleporter\Teleporter\RestrictTeleportTimer=2.5*secs#
					Case OBJTYPE_TELEPORTER2:
						Menu\SelectedStage=o\Teleporter\TeleporterNo
						Menu\HubStage=Menu\Stage
						Game_Stage_Quit(2)
					Case OBJTYPE_TELEPORTER3:
						Object_Teleporter_ChaoItem(p,p\ObjPickUpTarget,True)
						SaveGame_Inventory()
						Game_Stage_Quit(6)
					Case OBJTYPE_TELEPORTER4:
						Object_Teleporter_ChaoItem(p,p\ObjPickUpTarget,False)
						SaveGame_Inventory()
						Game_Stage_Quit(7)
					Case OBJTYPE_TELEPORTER5:
						Object_Teleporter_ChaoItem(p,p\ObjPickUpTarget,False,True)
						SaveGame_Inventory()
						Game_Stage_Quit(8)
					Case OBJTYPE_TELEPORTER6:
						If p\ObjPickUp>0 Then p\ObjPickUp=0
						SaveGame_Inventory()
						Game_Stage_Quit(10)
				End Select
			EndIf
			Game\ControlLock=0.1*secs#
		EndIf
	EndIf
	

		; Particle effect
		ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_GOAL, o\Entity, 0.65, 0, 0, 0, 1, 0.15)
		
	End Function

Function Object_Teleporter_ChaoItem(p.tPlayer, o.tObject, isheld, racechao=False)
		LoadGame_ResetMenuChao()
		If p\ObjPickUp>0 Then
			If (racechao=False Or o\ObjType=OBJTYPE_CHAO) Then
				If Not(p\ObjPickUpTarget\ObjType=OBJTYPE_TRASHCAN Or p\ObjPickUpTarget\ObjType=OBJTYPE_SACK) Then stophold=True Else stophold=False
				Select o\ObjType
				Case OBJTYPE_CHAO:
					If (isheld=False Or o\ChaoObj\targetcc\Stats\Age=0) And (racechao=False Or ChaoManager_ChaoAlive(o\ChaoObj\targetcc)) Then
						o\ObjPickedUp=0
						HideEntity o\ChaoObj\targetcc\Mesh
						Object_ChaoManager_RandomBeginPos(o\ChaoObj\targetcc)
						PositionEntity o\ChaoObj\targetcc\Pivot, o\ChaoObj\targetcc\Position\x#, o\ChaoObj\targetcc\Position\y#, o\ChaoObj\targetcc\Position\z#
						RotateEntity o\ChaoObj\targetcc\Pivot, 0, o\ChaoObj\targetcc\g\Motion\Direction#, 0
						If ChaoManager_ChaoAlive(o\ChaoObj\targetcc) Then
							Menu\HeldChaoNumber=o\ChaoObj\targetcc\Number
							LoadGame_MenuChao(Menu\HeldChaoNumber)
						ElseIf o\ChaoObj\targetcc\Stats\Age=0 Then
							ii.tItem = Item_Create(TOTALITEMS+1, 3, o\ChaoObj\targetcc\Stats\Color, 0, isheld)
							Menu_Transporter_Goodbye_DeleteAChao(o\ChaoObj\targetcc\Number)
						EndIf
					EndIf
				Case OBJTYPE_FRUIT:
					ii.tItem = Item_Create(TOTALITEMS+1, 1, o\ChaoObj\FruitType, o\ChaoObj\EatCycle, isheld)
					o\ChaoObj\ForceDelete=True : Object_EnforceForceDeleteChaoObj(o)
				Case OBJTYPE_SHELL:
					ii.tItem = Item_Create(TOTALITEMS+1, 4, o\ChaoObj\ShellType, o\ChaoObj\ShellType2, isheld)
					o\ChaoObj\ForceDelete=True : Object_EnforceForceDeleteChaoObj(o)
				Case OBJTYPE_HAT:
					ii.tItem = Item_Create(TOTALITEMS+1, 2, o\ChaoObj\HatType, 0, isheld)
					o\ChaoObj\ForceDelete=True : Object_EnforceForceDeleteChaoObj(o)
				Case OBJTYPE_TOY:
					ii.tItem = Item_Create(TOTALITEMS+1, 5, o\ChaoObj\ToyType, 0, isheld)
					o\ChaoObj\ForceDelete=True : Object_EnforceForceDeleteChaoObj(o)
				Case OBJTYPE_SEED:
					ii.tItem = Item_Create(TOTALITEMS+1, 6, o\ChaoObj\FruitType, 0, isheld)
					o\ChaoObj\ForceDelete=True : Object_EnforceForceDeleteChaoObj(o)
				End Select
			EndIf
			If stophold Then p\ObjPickUp=0
		EndIf
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Breakable_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, special#=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Trap = New tObject_Trap : o\HasValuesetTrap=True

		Select o\ObjType
			Case OBJTYPE_CRYSTAL: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,10,10,10)
			Case OBJTYPE_AUTO: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,25,25,25)
			Case OBJTYPE_ICICLE: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,7,11,7)
			Case OBJTYPE_ICICLEBIG:
				If special#=1 Then
					Object_CreateHitBox(HITBOXTYPE_NORMAL,o,10.5,30,10.5)
				Else
					Object_CreateHitBox(HITBOXTYPE_NORMAL,o,15.5,15,15.5)
				EndIf
			Case OBJTYPE_ICEDECOR:
				If special#=1 Then
					Object_CreateHitBox(HITBOXTYPE_NORMAL,o,7,21.5,7)
				Else
					Object_CreateHitBox(HITBOXTYPE_NORMAL,o,7,15,7)
				EndIf
			Default: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,15,15,15)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select o\ObjType
			Case OBJTYPE_AUTO,OBJTYPE_ICICLEBIG:
				Object_Acquire_Rotation(o,pitch#,yaw#,roll#)
			Case OBJTYPE_ICEDECOR:
				Object_Acquire_Rotation(o,0,yaw#,0)
			Default:
				If (Not(pitch#>0)) And (Not(o\ObjType=OBJTYPE_ICICLE And special#=2)) Then
					Object_Acquire_Rotation(o,0,Rand(1,360),0)
				Else
					Object_Acquire_Rotation(o,pitch#,yaw#,roll#)
				EndIf
		End Select

		Select o\ObjType
			Case OBJTYPE_ROCK:
				o\Trap\CrystalColor=special#
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Rock_brown+o\Trap\CrystalColor-1)), Game\Stage\Root)
				SmartEntity(Mesh_RockChunk1_brown+(o\Trap\CrystalColor-1)*3)
				SmartEntity(Mesh_RockChunk2_brown+(o\Trap\CrystalColor-1)*3)
				SmartEntity(Mesh_RockChunk3_brown+(o\Trap\CrystalColor-1)*3)
			Case OBJTYPE_CRYSTAL:
				o\Trap\CrystalColor=Rand(1,3)
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Crystal_red+o\Trap\CrystalColor-1)), Game\Stage\Root)
				SmartEntity(Mesh_CrystalChunk1_red+(o\Trap\CrystalColor-1)*3)
				SmartEntity(Mesh_CrystalChunk2_red+(o\Trap\CrystalColor-1)*3)
				SmartEntity(Mesh_CrystalChunk3_red+(o\Trap\CrystalColor-1)*3)
			Case OBJTYPE_ICICLE:
				o\Trap\CrystalColor=special#
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Icicle1+o\Trap\CrystalColor-1)), Game\Stage\Root)
				SmartEntity(Mesh_IcicleShard1)
				SmartEntity(Mesh_IcicleShard2)
				SmartEntity(Mesh_IcicleShard3)
			Case OBJTYPE_ICICLEBIG:
				o\Trap\CrystalColor=special#
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_IcicleBig1+o\Trap\CrystalColor-1)), Game\Stage\Root)
				SmartEntity(Mesh_IcicleShard1)
				SmartEntity(Mesh_IcicleShard2)
				SmartEntity(Mesh_IcicleShard3)
			Case OBJTYPE_ICEDECOR:
				o\Trap\CrystalColor=special#
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_IceDecor1+o\Trap\CrystalColor-1)), Game\Stage\Root)
				SmartEntity(Mesh_IceDecorShard1)
				SmartEntity(Mesh_IceDecorShard2)
				SmartEntity(Mesh_IceDecorShard3)
				SmartEntity(Mesh_IceDecorShard4)
				SmartEntity(Mesh_IceDecorShard5)
			Case OBJTYPE_AUTO:
				o\Trap\CrystalColor=Rand(1,10)
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Car_Sedan1+o\Trap\CrystalColor-1)), Game\Stage\Root)
		End Select

		Select o\ObjType
			Case OBJTYPE_ICICLEBIG: EntityType(o\Entity,COLLISION_WORLD_POLYGON_ICE)
			Default: EntityType(o\Entity,COLLISION_OBJECT)
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Breakable_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		Select o\ObjType
			Case OBJTYPE_AUTO:
				Object_EnforcePsychokinesis(o,p,d)
				Object_EnforceRubyGravity(o,p,d)
		End Select
		
		; Player collided with object
		If o\Hit Or o\BombHit Then
			i=False
			If o\BombHit Or p\Flags\StronglyAttacking Or (o\Hit And o\ObjType=OBJTYPE_CRYSTAL) Then
				Select o\ObjType
					Case OBJTYPE_AUTO,OBJTYPE_ICICLEBIG:
						If o\BombHit Then
							i=True
						Else
							If o\Rotation\x#<11.25 Then
								If p\Objects\Position\y#>=o\Position\y# Then i=True
							ElseIf o\Rotation\x#>146.25 Then
								If p\Objects\Position\y#<=o\Position\y# Then i=True
							Else
								i=True
							EndIf
						EndIf
					Default:
						i=True
				End Select
			EndIf
			If i Then
				If o\ObjType=OBJTYPE_CRYSTAL And (Not(p\HurtTimer>0)) And o\Hit Then Player_Hit(p)
				Select o\ObjType
					Case OBJTYPE_AUTO:
						EmitSmartSound(Sound_Car1+Rand(1,4)-1,o\Entity)
						Gameplay_AddScore(100)
						If o\Hit Then ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
					Default: EmitSmartSound(Sound_Break,o\Entity)
				End Select
				Select o\ObjType
					Case OBJTYPE_ROCK: j#=0.5
					Case OBJTYPE_ICICLEBIG: If o\Trap\CrystalColor=1 Then j#=7 Else j#=5
					Case OBJTYPE_ICEDECOR: If o\Trap\CrystalColor=1 Then j#=6.5 Else j#=4
					Case OBJTYPE_AUTO: j#=0
					Default: j#=2
				End Select
				Select o\ObjType
					Case OBJTYPE_ICICLEBIG: h#=1.925
					Case OBJTYPE_ICEDECOR: h#=4
					Default: h#=1.1
				End Select
				Object_Pieces_Create(False,o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#+j#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,h#,0,False,o\Trap\CrystalColor)
				o\Done=1
				Return
			EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Sign_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, special#=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Hint = New tObject_Hint : o\HasValuesetHint=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,15,15,15)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,pitch#,yaw#,roll#)

		o\Hint\SignType = special#

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Sign_fall+o\Hint\SignType-1)), Game\Stage\Root)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Sign_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		If o\Hit Then
			If o\Hint\SignAlpha#>0 Then o\Hint\SignAlpha#=o\Hint\SignAlpha#-0.2*d\Delta
		Else
			If o\Hint\SignAlpha#<1 Then o\Hint\SignAlpha#=o\Hint\SignAlpha#+0.2*d\Delta
		EndIf

		EntityAlpha(o\Entity,o\Hint\SignAlpha#)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Hommer_Create.tObject(p.tPlayer, hommabletype#=-1)
		o.tObject = New tObject : o\ObjType = OBJTYPE_HOMMER : o\ID=0
		o\AlwaysPresent=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,(1.2+p\ScaleFactor#)*5,(1.2+p\ScaleFactor#)*5,(1.2+p\ScaleFactor#)*5)

		Object_Acquire_Position(o,x#,y#,z#)

		o\State=hommabletype#

		If Player_IsPlayable(p) Then o\Mode=1

		o\Entity = CreatePivot()

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Hommer_Update(o.tObject, p.tPlayer)

		o\GotAssignedBomb=False

		; Aiming and shooting
		If o\State<0 Then Object_EnforceAimingShooting(o,p)

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Repeater_Create.tObject(objtype$, x#, y#, z#, pitch#, yaw#, roll#, power#, space#, dx#, dy#, dz#)
		o.tObject = New tObject : o\ObjType = OBJTYPE_REPEATER : o\ID=TempAttribute\ObjectID
		o\Translator = New tObject_Translator : o\HasValuesetTranslator=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,pitch#,yaw#,roll#)
		Object_Acquire_Power(o,power#)
		Object_Acquire_Destination(o,0,dx#,dy#,dz#)
		o\FValues#[0]=space#

		o\Entity = CreatePivot()
		o\AlwaysPresent=True

		Select objtype$
			Case "ring":	o\Mode=OBJTYPE_RING
			Case "spring":	o\Mode=OBJTYPE_SPRING
			Case "springx":	o\Mode=OBJTYPE_SPRINGX
			Case "springt":	o\Mode=OBJTYPE_SPRINGTRAP
			Case "springtx":o\Mode=OBJTYPE_SPRINGTRAPX
			Case "pad":		o\Mode=OBJTYPE_PAD
			Case "ramp":	o\Mode=OBJTYPE_RAMP
			Case "hoop":	o\Mode=OBJTYPE_HOOP
			Case "thoop":	o\Mode=OBJTYPE_THOOP
			Case "accel":	o\Mode=OBJTYPE_ACCEL
			Case "spikeb":	o\Mode=OBJTYPE_SPIKEBALL
			Case "spikebo":	o\Mode=OBJTYPE_SPIKEBOMB
			Case "spikecr":	o\Mode=OBJTYPE_SPIKECRUSHER
			Case "spikecyl":o\Mode=OBJTYPE_SPIKECYLINDER
			Case "xspikeb":	o\Mode=OBJTYPE_SPIKEBALL*10
			Case "xspikebo":o\Mode=OBJTYPE_SPIKEBOMB*10
			Case "xspikecr":o\Mode=OBJTYPE_SPIKECRUSHER*10
			Case "xspikecyl":o\Mode=OBJTYPE_SPIKECYLINDER*10
			Case "cbox":	o\Mode=OBJTYPE_BOXCAGE
			Case "ibox":	o\Mode=OBJTYPE_BOXIRON
			Case "mbox":	o\Mode=OBJTYPE_BOXMETAL
			Case "wbox":	o\Mode=OBJTYPE_BOXWOODEN
			Case "tntbox":	o\Mode=OBJTYPE_BOXTNT
			Case "nbox":	o\Mode=OBJTYPE_BOXNITRO
			Case "fbox":	o\Mode=OBJTYPE_BOXFLOAT
			Case "monitor":	o\Mode=OBJTYPE_RINGS
			Case "fspout":	o\Mode=OBJTYPE_FLAMESPOUT*10
			Case "ispout":	o\Mode=OBJTYPE_ICESPOUT*10
			Case "sspout":	o\Mode=OBJTYPE_SHOCKSPOUT*10
			Case "enemy":	o\Mode=OBJTYPE_PAWN
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Repeater_Update(o.tObject, p.tPlayer)

		For h=1-2 To 1
		If (p\Objects\Position\z#+o\FValues#[0])>(o\Translator\HasDestination+h)*o\FValues#[0] Then
			o\Translator\HasDestination=o\Translator\HasDestination+1
			x#=Rand(o\InitialPosition\x#-o\Translator\Destination\x#,o\InitialPosition\x#+o\Translator\Destination\x#)
			Select o\Mode
				Case OBJTYPE_RING,OBJTYPE_RINGS:
					Select(Rand(1,2))
					Case 1: y#=o\InitialPosition\y#
					Case 2: y#=o\InitialPosition\y#+o\Translator\Destination\y#
					End Select
				Default:
					y#=o\InitialPosition\y#
			End Select
			z#=Rand(o\InitialPosition\z#-o\Translator\Destination\z#,o\InitialPosition\z#+o\Translator\Destination\z#)+(o\Translator\HasDestination)*o\FValues#[0]
			Select o\Mode
				Case OBJTYPE_RING:
					For i=1 To Rand(4,6)
					o2.tObject = Object_Ring_Create(x#-3, y#, z#-(i-1)*8,1) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
					o2.tObject = Object_Ring_Create(x#+3, y#, z#-+(i-1)*8,1) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
					Next
				Case OBJTYPE_SPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL:
					TempAttribute\ObjectNo=o\Mode
					o2.tObject = Object_Translator_Create(x#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#, o\Power#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
				Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_SPIKECYLINDER:
					TempAttribute\ObjectNo=o\Mode
					o2.tObject = Object_Spike_Create(x#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
				Case OBJTYPE_SPIKEBALL*10,OBJTYPE_SPIKEBOMB*10,OBJTYPE_SPIKECRUSHER*10,OBJTYPE_SPIKECYLINDER*10,OBJTYPE_FLAMESPOUT*10,OBJTYPE_ICESPOUT*10,OBJTYPE_SHOCKSPOUT*10:
					TempAttribute\ObjectNo=o\Mode/10
					x#=o\InitialPosition\x#
					q#=0
					While q#<o\InitialPosition\x#+o\Translator\Destination\x#
						If q#>0 Then
							Select TempAttribute\ObjectNo
								Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
									o2.tObject = Object_Spout_Create(x#-q#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
									o2.tObject = Object_Spout_Create(x#+q#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
								Default:
									o2.tObject = Object_Spike_Create(x#-q#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
									o2.tObject = Object_Spike_Create(x#+q#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
							End Select
						Else
							Select TempAttribute\ObjectNo
								Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
									o2.tObject = Object_Spout_Create(x#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
								Default:
									o2.tObject = Object_Spike_Create(x#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
							End Select
						EndIf
						Select TempAttribute\ObjectNo
							Case OBJTYPE_SPIKEBALL: q#=q#+5.8
							Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER: q#=q#+7.5*1.5
							Case OBJTYPE_SPIKECYLINDER: q#=q#+5.5*2
							Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT: q#=q#+3*2.75
						End Select
					Wend
				Case OBJTYPE_BOXCAGE,OBJTYPE_BOXIRON,OBJTYPE_BOXMETAL,OBJTYPE_BOXWOODEN,OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT:
					TempAttribute\ObjectNo=o\Mode
					o2.tObject = Object_Box_Create(0, x#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
				Case OBJTYPE_RINGS:
					TempAttribute\ObjectNo=o\Mode
					o2.tObject = Object_Monitor_Create(0, x#, y#, z#) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
				Case OBJTYPE_PAWN:
					TempAttribute\ObjectNo=Rand(OBJTYPE_PAWN,OBJTYPE_ENEMYCOUNT)
					o2.tObject = Object_Enemy_Create(x#, y#, z#, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#, 0, 0) : Objects_Reset_HasMesh(o2) : Objects_Reset_Object(o2, 1) : Objects_Reset_Repose(o2) : o2\Repeated=1
			End Select
		EndIf
		Next

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Capsule_Create.tObject(x#, y#, z#, yaw#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID

		Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,4,5,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Capsule)), Game\Stage\Root)
		EntityType(o\Entity,COLLISION_OBJECT)
		o\Entity2 = CreatePivot()

		For i=1 To 15
		For j=1 To 3
			Object_Wisp_Create.tObject(x#, y#-10.59+(j-1)*2.5, z#, yaw#+(360/15.0)*i, 5)
		Next
		Next

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Capsule_Update(o.tObject, p.tPlayer)

		If Game\Victory=0 Then
			Animate o\Entity,1,0,1
		Else
			Animate o\Entity,1,0,2
		EndIf

		PositionEntity o\Entity2, o\Position\x#, o\Position\y#-8.84, o\Position\z#, 1
		
 		If o\Hit Then
			If Game\Victory=0 Then
				PositionEntity p\Objects\Entity, o\Position\x#, o\Position\y#+5, o\Position\z#, 1
				Player_Goal(p,0,0,True)

				EmitSmartSound(Sound_RobotPoof,o\Entity2)
				ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BOMB, o\Entity2)
				Object_Pieces_Create_RobotPieces(EntityX(o\Entity2),EntityY(o\Entity2),EntityZ(o\Entity2),o\Rotation\x#,o\Rotation\y#,o\Rotation\z#)
			EndIf
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Bomber_Create.tObject(x#, y#, z#, yaw#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID

		Select o\ObjType
			Case OBJTYPE_BOMBER1: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,6.5,8,6.5)
			Case OBJTYPE_BOMBER2: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,5.7,5.7,5.7)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select o\ObjType
			Case OBJTYPE_BOMBER2: Object_Acquire_Rotation(o,0,Rand(1,360),0)
			Default: Object_Acquire_Rotation(o,0,yaw#,0)
		End Select

		Select o\ObjType
			Case OBJTYPE_BOMBER1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Bomber1)), Game\Stage\Root)
			Case OBJTYPE_BOMBER2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Bomber2)), Game\Stage\Root)
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Bomber_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		Select o\ObjType
			Case OBJTYPE_BOMBER1:
				If p\SpeedLength#<=1.5 Then
					EntityType(o\Entity,COLLISION_OBJECT)
				Else
					EntityType(o\Entity,COLLISION_NONE)
				EndIf

				If o\Anim>0 Then o\Anim=o\Anim-timervalue#
				Select o\State
					Case 0:
						EmitSmartSound(Sound_EnemyShotPoof,o\Entity)
						obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\position\x#, o\position\y#, o\position\z#, 0, EntityYaw(o\Entity), 0)
						o\Anim=0.5*secs#
						o\State=1
						o\Mode=o\Mode+1
						If o\Mode>=8 Then o\Mode=0
					Case 1:
						If Not(o\Anim>0) Then o\State=2
					Case 2:
						i=o\Mode*45
						If i>=180 Then i=i-360
						If o\Rotation\y#<i Then
							TurnEntity o\Entity, 0, 10*d\Delta, 0
						Else
							RotateEntity o\Entity, 0, o\Mode*45, 0, 1
							o\State=3
							o\Anim=0.5*secs#
						EndIf
					Case 3:
						If Not(o\Anim>0) Then o\State=0
				End Select
			Case OBJTYPE_BOMBER2:
				If EntityDistance(o\Entity,p\Objects\Entity)<40 And o\IsInBox=0 Then
					EmitSmartSound(Sound_RobotPoof,o\Entity)
					j=Rand(1,360)
					For i=0 To 3
						obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\position\x#, o\position\y#, o\position\z#, 0, EntityYaw(o\Entity)+j+90*i, 0)
					Next
					o\Done=1
				EndIf
		End Select

	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D