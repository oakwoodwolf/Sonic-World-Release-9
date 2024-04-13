Function Objects_Reset()
		;reset camera if told to
		If Game\ResetCamera=1 Then
			cam\TargetRotation\x#=15 : cam\TargetRotation\z#=0
			If Game\Gameplay\CheckDirection#<0 Then cam\TargetRotation\y#=Game\Gameplay\CheckDirection#+360 Else cam\TargetRotation\y#=Game\Gameplay\CheckDirection#
			CAMERA_DISTANCE_NEAR# = CAMERA_ZOOMVALUE# : CAMERA_DISTANCE_FAR# = CAMERA_ZOOMVALUE#
			Game\ControlLock = 0
			Game\CamLock = 0
			Game\RunLock = 0
			Game\MachLock = 0
		Game\ResetCamera=0
		EndIf

		;reset checks if told to
		If Game\ResetChecks=1 Then
			For o.tObject=Each tObject
				If o\ObjType=OBJTYPE_CHECK Then	o\State=0
			Next
		Game\ResetChecks=0
		EndIf

		;reset objects if told to
		If Game\ResetObjects=1 Then
			Objects_Reset_All()
		EndIf

		;repose all obj
		For o.tobject=Each tObject
			If o\Repose=0 Then Objects_Reset_Repose(o)
		Next
End Function

;--------------------------------------------------------------------------

Function Objects_Reset_All()
		For p.tPlayer=Each tPlayer
			If p\ObjPickUp>0 Then p\Action=ACTION_COMMON : p\ObjPickUp=0

			p\DontGetHurtTimer=0.5*secs#

			If p\No#<0 Then
				Player_SetPosition(p, p\Rival\InitialPositionX#, p\Rival\InitialPositionY#, p\Rival\InitialPositionZ#, p\Rival\InitialRotationY#)
				Player_ResetAllTimers(p)
				p\Rival\Health=5
				p\Action=ACTION_COMMON
			EndIf
		Next

		For k.tBoxBlocker=Each tBoxBlocker
			k\Done=0
			k\HasObj=False
			PositionEntity k\Entity,k\InitialPosition\x#,k\InitialPosition\y#,k\InitialPosition\z#,1
		Next

		For b.tBomb=Each tBomb
			b\MustDestroy=2
		Next

		For s.tSwitchManager=Each tSwitchManager
			s\Active=s\InitialStatus
		Next

		For o.tObject=Each tObject
			Objects_Reset_Object(o)
		Next

		For rz.tRazer=Each tRazer
			rz\Timer=0
		Next

		If Menu\Mission=MISSION_HUNT# Then Objects_Reset_RedRings()

		;repose all obj
		For o.tobject=Each tObject
			If o\Repose=0 Then Objects_Reset_Repose(o)
			Objects_Hide(o)
		Next

		;assign boxes
		For o.tobject=Each tObject
			Object_BoxBlocker_FindBox(o)
		Next

		;restore water level
		If Game\Stage\Properties\Water=1 Then
			Game\Stage\Properties\WaterLevelChanged=0
			Game\Stage\Properties\WaterLevel=Game\Stage\Properties\WaterLevelInitial
			Game\Stage\Properties\WaterLevelTarget=Game\Stage\Properties\WaterLevel
			PositionEntity(Game\Stage\Properties\WaterMesh, 0, Game\Stage\Properties\WaterLevel, 0)
		EndIf

	Game\ResetObjects=0
End Function

Function Objects_Reset_Object(o.tObject, dontdelete=0)
	o\Hit=False
	o\CheeseHit=False
	o\BombHit=False
	o\GotAssignedBomb=False
	o\Done=0
	o\IsInBox=0
	o\BubbleStunTimer=0
	o\CurseStunTimer=0
	o\FroggyStunTimer=0
	o\FlowerStunTimer=0
	o\FrozenStunTimer=0
	o\WhirlwindStunTimer=0
	o\ObjPickedUp=0
	o\Psychoed=0
	o\PsychoedThrown=False
	o\Rubied=0
	o\Repose=0
	o\ThrownAsBomb=0
	o\AimedAt=0
	o\SavedFromInsideBoxOnce=0
	If o\ThisIsATranslator Then
		o\Translator\WasJustUsedTimer=0
	ElseIf o\ThisIsASpike Then
		Object_Spike_CollisionReset(o)
	EndIf
	If o\ThisIsAMonitor Then
		o\State=0
		If o\ThisIsAMonitorBalloon=False Then
			o\CanHoming = True
			o\CheeseCanHoming = True
		EndIf
		Animate o\Entity,1,0.2,1,10
	ElseIf o\ThisIsAnEnemy Then
		o\Enemy\InRange=False
		o\Enemy\InRangeForced=False
		If o\Enemy\IsBoss=1 Then
			If o\Enemy\HasBossObj1 Then o\Enemy\BossObj1\ExplodeHit=True : o\Enemy\HasBossObj1=False
			If o\Enemy\HasBossObj2 Then o\Enemy\BossObj2\ExplodeHit=True : o\Enemy\HasBossObj2=False
			If o\Enemy\HasBossObj3 Then o\Enemy\BossObj3\ExplodeHit=True : o\Enemy\HasBossObj3=False
			o\Enemy\BossMode=0
			o\Mode=0
			o\AlwaysPresent=True
		ElseIf (o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE) Then
			o\AlwaysPresent=True
			o\Enemy\InRangeForced=True
			o\Enemy\Alpha#=1
		Else
			o\AlwaysPresent=False
		EndIf
		o\CanHoming = True
		o\CheeseCanHoming = True
		o\Enemy\WasJustAttacked=0
		o\Enemy\HelloSonic=0
		o\Enemy\SeenSonic=0
		o\Enemy\SearchSonic=0
		o\Enemy\WaitTimer=0
		o\Enemy\WaitTimer2=0
		o\Enemy\WaitTimer3=0
		o\Enemy\AttackTimer=0
		o\Enemy\AttackTimer2=0
		o\Enemy\WasKilledByBombMonitor=False
		o\Anim=1
		o\PreviousAnim=0
		o\Enemy\AttackMode=0
		o\Enemy\AttackMode2=0
		o\Enemy\AttackMode3=0
		o\Enemy\WillBeHomedTimer=0
		o\Enemy\MayNotHurtTimer=0
		o\Enemy\HiddenSeenTimer=0
		o\Enemy\Shield=o\Enemy\InitialShield
		o\HasRedRing=False
		o\Enemy\Health=o\Enemy\InitialHealth
		Select o\ObjType
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA: o\Mode=5 : o\Enemy\ShouldSpawnMissile=True
			Default: o\Enemy\ShouldSpawnMissile=False
		End Select
		If o\Switch\SwitchNo[0]>0 Then o\Switch\s1\Active=1
		Object_Enemy_NoBehaviour_Anims(o)
		Object_AnimateEnemy(o)
	ElseIf o\ThisIsABox Then
		EntityRadius(o\Entity,1)
		o\Box\DestroyBox=False
		If o\ObjType=OBJTYPE_BOXTNT Then o\Box\TntBoxCount=0
	ElseIf o\ThisIsABumper Then
		o\AlwaysPresent=False
		Select o\ObjType
			Case OBJTYPE_PLATEBUMPER: o\Translator\PlateBumper=0
			Case OBJTYPE_PADDLE: o\Translator\CannonWasUsedTimer=0
		End Select
	Else
		Select o\ObjType
			Case OBJTYPE_BALLOON:
				o\CanHoming = True
				o\State=0
				o\Mode=0
			Case OBJTYPE_SPEWRING:
				o\Spew\CollectTimer#=10501
			Case OBJTYPE_SPEWREDRING:
				o\Spew\CollectTimer#=10501
				o\HasRedRing=False
			Case OBJTYPE_RING:
				o\State=0
				o\CanRingDash=True
				o\AlwaysPresent=False
			Case OBJTYPE_REDRING:
				o\HasRedRing=False
				o\AlwaysPresent=False
				o\Treasure\BoxShouldHide=False
			Case OBJTYPE_THOOP:
				o\Translator\THoopPointDisabler = 0
			Case OBJTYPE_PIECE:
				o\Piece\PieceTimer=0
				o\Piece\ShallNotFade=False
			Case OBJTYPE_ENEMYMISSILE:
				Select o\EnemyMissile\MissileType
					Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA: If o\Mode=0 Then o\EnemyMissile\DeleteDestroy=True
					Default: o\EnemyMissile\DeleteDestroy=True
				End Select
			Case OBJTYPE_OMOCHAO:
				StopChannel(o\Omochao\Channel_Omochao)
				StopChannel(o\Omochao\Channel_OmochaoFly)
				o\Omochao\Mode=0
				o\Omochao\AttackedTimer=0
				o\Omochao\FactTimer=0
			Case OBJTYPE_SPIKEBAR:
				o\Spike\BarMover=0
			Case OBJTYPE_PROPELLER,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
				o\Translator\TransfererPlace=0
				o\Translator\TransfererForcePlace=0
				o\AlwaysPresent=False
				o\Translator\TransfererDone=0
				o\Translator\TransfererIsBeingUsed=False
			Case OBJTYPE_PULLEY:
				o\Translator\TransfererPlace=1
				o\Translator\TransfererForcePlace=1
				o\Translator\TransfererDone=-1
				o\Translator\PulleyReturnTimer=0
				o\AlwaysPresent=True
				o\Translator\TransfererIsBeingUsed=False
			Case OBJTYPE_HINT,OBJTYPE_COUNTER:
				o\Hint\HintRevealTimer=0
				Animate o\Entity,1,0.05,1,10
			Case OBJTYPE_SIGN:
				o\Hint\SignAlpha#=1.0
			Case OBJTYPE_BELL:
				o\Mode=0
				Animate o\EntityX,1,0,1,10 : o\AlwaysPresent=False
				o\State=5
			Case OBJTYPE_SPEWSPIKEBOMB:
				FreeEntity o\Entity
				Delete o\Position
				Delete o\Rotation
				Delete o
				Return
			Case OBJTYPE_FLICKY:
				If o\Treasure\RedRingNo=0 Then o\State=-1
			Case OBJTYPE_REPEATER:
				o\Translator\HasDestination=1
			Case OBJTYPE_FPLAT:
				o\State=0
			Case OBJTYPE_DRIVE:
				o\ChaoObj\ForceDelete=True
			Case OBJTYPE_EXPLOSION,OBJTYPE_EXPLOSION2:
				o\State=0
			Case OBJTYPE_SWITCHBASE:
				o\Switch\SwitchTopBrought=False
				If o\Switch\SwitchTopFound=False Then
					For o2.tObject = Each tObject
						If Not o\Switch\SwitchTopFound Then
							If o2\ObjType=OBJTYPE_SWITCHTOP Then
								If o\Switch\SwitchNo[0]=o2\Switch\SwitchNo[0] Then
									o\Switch\SwitchTop=o2
									o\Switch\SwitchTopFound=True
								EndIf
							EndIf
						EndIf
					Next
				EndIf
			Case OBJTYPE_SPIKESWINGBALL:
				o\State=0
			Case OBJTYPE_CAPSULE:
				Animate o\Entity,1,0,1
			Case OBJTYPE_HOMMER:
				o\CanHoming = True
			Case OBJTYPE_BOMBER1:
				o\State=0
				o\Mode=0
		End Select
	EndIf
	Select o\ObjType
		Case OBJTYPE_BOXLIGHT,OBJTYPE_LASERH,OBJTYPE_LASERV,OBJTYPE_GOAL2:
			If o\Switch\SwitchFound=False Then Object_SwitchManager_FindSwitchAndAssign(o)
	End Select

	If Not dontdelete Then
		If o\Repeated Then Game_Stage_End_Objects_ObjectIndividual(o)
	EndIf
End Function

;--------------------------------------------------------------------------

Function Objects_Reset_Rotate(entity, pitch#, yaw#, roll#, globality=0)
	RotateEntity entity, pitch#, yaw#, 0, globality
	TurnEntity entity, 0, 0, roll#, globality
End Function

Function Objects_Reset_Repose(o.tObject)

	;reposition
	If o\HasGravity Then
		o\CollisionStore=GetEntityType(o\Pivot) : EntityType(o\Pivot,0)
		PositionEntity o\Pivot,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\Pivot, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\Pivot,o\CollisionStore)
	EndIf

	If o\Entity<>0 Then
		o\CollisionStore=GetEntityType(o\Entity) : EntityType(o\Entity,0)
		PositionEntity o\Entity,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\Entity, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\Entity,o\CollisionStore)
	EndIf

	If o\HasEntity2 Then
		o\CollisionStore=GetEntityType(o\Entity2) : EntityType(o\Entity2,0)
		PositionEntity o\Entity2,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\Entity2, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\Entity2,o\CollisionStore)
	EndIf

	If o\HasEntity3 Then
		o\CollisionStore=GetEntityType(o\Entity3) : EntityType(o\Entity3,0)
		PositionEntity o\Entity3,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\Entity3, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\Entity3,o\CollisionStore)
	EndIf

	If o\HasEntity4 Then
		o\CollisionStore=GetEntityType(o\Entity4) : EntityType(o\Entity4,0)
		PositionEntity o\Entity4,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\Entity4, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\Entity4,o\CollisionStore)
	EndIf

	If o\HasEntityX Then
		o\CollisionStore=GetEntityType(o\EntityX) : EntityType(o\EntityX,0)
		PositionEntity o\EntityX,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\EntityX, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\EntityX,o\CollisionStore)
	EndIf

	If o\HasEntityCube Then
		o\CollisionStore=GetEntityType(o\EntityCube) : EntityType(o\EntityCube,0)
		PositionEntity o\EntityCube,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\EntityCube, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\EntityCube,o\CollisionStore)
	EndIf

	If o\HasEntityCube2 Then
		o\CollisionStore=GetEntityType(o\EntityCube2) : EntityType(o\EntityCube2,0)
		PositionEntity o\EntityCube2,o\InitialPosition\x#,o\InitialPosition\y#,o\InitialPosition\z#,1
		If o\HasRotation Then Objects_Reset_Rotate o\EntityCube2, o\InitialRotation\x#, o\InitialRotation\y#, o\InitialRotation\z#,1
		EntityType(o\EntityCube2,o\CollisionStore)
	EndIf

	o\Repose=1
End Function

;-----------------------------------------------------------

Function Objects_Reset_HasMesh(o.tObject)
	o\HasEntity=True

	Select o\ObjType
		Case OBJTYPE_CHECK,OBJTYPE_BALLBUMPER,OBJTYPE_GROUNDBUMPER,OBJTYPE_METROBUMPER,OBJTYPE_PLATEBUMPER,OBJTYPE_SWITCH,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHBASE,OBJTYPE_SPIKEBAR,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR,OBJTYPE_TROPICAL,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_SPIKESWING,OBJTYPE_BOSS,OBJYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA,OBJTYPE_CAPSULE:
			o\HasEntity2=True
	End Select

	Select o\ObjType
		Case OBJTYPE_PLATEBUMPER,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA,OBJTYPE_SWITCHBASE:
			o\HasEntity3=True
	End Select

	Select o\ObjType
		Case OBJTYPE_PLATEBUMPER,OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
			o\HasEntity4=True
	End Select

	Select o\ObjType
		Case OBJTYPE_RING,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_ACCEL,OBJTYPE_BOXLIGHT,OBJTYPE_SWITCH,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHBASE,OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_PULLEY,OBJTYPE_BELL,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_FLICKY,OBJTYPE_FPLAT,OBJTYPE_WISP:
			o\HasEntityX=True
		Default:
			If o\ThisIsAMonitor Or o\ThisIsAPlant Then
	 			o\HasEntityX=True
			EndIf
	End Select

	Select o\ObjType
		Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKECYLINDER:
			o\HasEntityCube=True
		Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP:
			o\HasEntityCube=True
			o\HasEntityCube2=True
	End Select

	If o\ThisIsATranslator Then
		o\HasIValues=True
	EndIf

	Select o\ObjType
		Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_MOTOBUG,OBJTYPE_EGGROBO,OBJTYPE_ORBINAUT,OBJTYPE_ANTON,OBJTYPE_E1000,OBJTYPE_RHINOTANK,OBJTYPE_BOSSBETA,OBJTYPE_BOSSROBO:
			o\Enemy\HasJets=2
		Case OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_BOMBIE,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BALKIRY,OBJTYPE_ZOOMER,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSMECHA:
			o\Enemy\HasJets=1
	End Select

	;_____________________________________________________________________________

		Select o\ObjType
			Case OBJTYPE_RING,OBJTYPE_REDRING,OBJTYPE_SWITCH,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHTOP,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHAIR,OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_OMOCHAO,OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTER2,OBJTYPE_TELEPORTER3,OBJTYPE_TELEPORTER5,OBJTYPE_TELEPORTER6,OBJTYPE_ELEVATOR,OBJTYPE_BOMBER2,OBJTYPE_BELL:
				o\CanBeInsideBox=True
			Default:
				If o\ThisIsABox Or o\ThisIsATranslator Or o\ThisIsAMonitor Or o\ThisIsAnEnemy Then
					o\CanBeInsideBox=True
				EndIf
		End Select

End Function

;-----------------------------------------------------------

Function Objects_Reset_RedRings()
	For i=1 to 3
		If Game\Gameplay\RedRingDistance[i]<9 Then
			Select i
				Case 1:
					Game\Gameplay\RedRing[i]=Objects_Reset_DecideRedRings()
				Case 2:
					Repeat : Game\Gameplay\RedRing[i]=Objects_Reset_DecideRedRings() : Until (Not(Game\Gameplay\RedRing[i]=Game\Gameplay\RedRing[i-1]))
				Case 3:
					Repeat : Game\Gameplay\RedRing[i]=Objects_Reset_DecideRedRings() : Until (Not(Game\Gameplay\RedRing[i]=Game\Gameplay\RedRing[i-1])) and (Not(Game\Gameplay\RedRing[i]=Game\Gameplay\RedRing[i-2]))
			End Select
			Game\Gameplay\RedRingDistance[i]=0
		EndIf
	Next

	For o.tObject = Each tObject
		Select o\ObjType
			Case OBJTYPE_REDRING,OBJTYPE_SPEWREDRING:
				For i=1 to 3
					If o\Treasure\RedRingNo=Game\Gameplay\RedRing[i] Then o\HasRedRing=True : o\AlwaysPresent=True
				Next
			Default:
				If o\ThisIsAnEnemy Then
					For i=1 to 3
						If o\Enemy\EnemyNo=-Game\Gameplay\RedRing[i] And Game\Gameplay\RedRingDistance[i]<9 Then o\HasRedRing=True
					Next
				EndIf
		End Select
	Next
End Function

Function Objects_Reset_DecideRedRings()
	redringdecision#=Rand(1,Game\Gameplay\TotalEnemies/2.5+Game\Gameplay\TotalRedRings)

	If redringdecision#<=Game\Gameplay\TotalEnemies/2.5 Then
		redringdecision=-Rand(1,Game\Gameplay\TotalEnemies)
	Else
		redringdecision=Rand(1,Game\Gameplay\TotalRedRings)
	EndIf

	Return redringdecision
End Function