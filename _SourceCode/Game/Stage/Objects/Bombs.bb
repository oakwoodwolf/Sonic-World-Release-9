
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

i = 0
Global BOMB_BOX = i : i=i+1
Global BOMB_BULLET = i : i=i+1
Global BOMB_BULLET2 = i : i=i+1
Global BOMB_BULLET3 = i : i=i+1
Global BOMB_SPEAR = i : i=i+1
Global BOMB_SPEARWATER = i : i=i+1
Global BOMB_BOOMERANG = i : i=i+1
Global BOMB_NOTE = i : i=i+1
Global BOMB_BLOB = i : i=i+1
Global BOMB_FLAME = i : i=i+1
Global BOMB_PSYCHIC = i : i=i+1
Global BOMB_ROCKET = i : i=i+1
Global BOMB_TYPHOON = i : i=i+1
Global BOMB_BUBBLES = i : i=i+1
Global BOMB_PUNCH = i : i=i+1
Global BOMB_MINION = i : i=i+1
Global BOMB_GUM = i : i=i+1
Global BOMB_ICE = i : i=i+1
Global BOMB_SHOCK = i : i=i+1
Global BOMB_CHEESE = i : i=i+1
Global BOMB_FROGGY = i : i=i+1
Global BOMB_HANDBLADE = i : i=i+1
Global BOMB_RING = i : i=i+1
Global BOMB_HEART = i : i=i+1
Global BOMB_FLOWER = i : i=i+1
Global BOMB_BOMB = i : i=i+1
Global BOMB_BLADE = i : i=i+1
Global BOMB_SHOT = i : i=i+1
Global BOMB_CURSE = i : i=i+1
Global BOMB_DART = i : i=i+1
Global BOMB_EXPLOSIVE = i : i=i+1
Global BOMB_BEAM = i : i=i+1
Global BOMB_WIND = i : i=i+1
Global BOMB_FIREBALL = i : i=i+1
Global BOMB_BIGBOMB = i : i=i+1
Global BOMB_KNIFE = i : i=i+1
Global BOMB_HURRICANE = i : i=i+1
Global BOMB_TIRE = i : i=i+1
Global BOMB_LEAF = i : i=i+1
Global BOMB_ORB = i : i=i+1
Global BOMB_GEAR = i : i=i+1
Global BOMB_ROCK = i : i=i+1
Global BOMB_JUSTICE = i : i=i+1
Global BOMB_HOOKSHOT = i : i=i+1
Global BOMB_CUBETRAIL = i : i=i+1
Global BOMB_BELLYFLOP = i : i=i+1

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tBomb
		Field targetp.tPlayer
		Field Position.tVector
		Field Rotation.tVector
		Field HitBox.tVector
		Field BombType
		Field ThrownMode#
		Field Entity
		Field Pivot
		Field MoveTimer
		Field MustDestroy
		Field DisappearTimer
		Field TargetAssigned
		Field TargetAssignedObj.tObject
		Field Channel_Bomb
		Field InitialSpeed#
		Field MayDestroyTimer
		Field Particle.tParticleTemplate
		Field HurtPlayer
		Field HasTarget
		Field TargetPivot
		Field Target.tVector
		Field NotAffectable
		Field ThrownAsBomb
		Field BombRoot
		Field MayNotDestroy
		Field RubyCubesTimer
	End Type

Function Object_Bomb_CreateHitBox(b.tBomb,hitboxx#,hitboxy#,hitboxz#)
	b\HitBox = New tVector
	b\HitBox\x# = hitboxx#
	b\HitBox\y# = hitboxy#
	b\HitBox\z# = hitboxz#
End Function

Function Object_Bomb_Create_Target(b.tBomb, hastarget, targetx#, targety#, targetz#)
	b\HasTarget=hastarget
	b\Target = New tVector
	b\Target\x# = targetx#
	b\Target\y# = targety#
	b\Target\z# = targetz#
	b\TargetPivot=CreatePivot()
	PositionEntity(b\TargetPivot,targetx#,targety#,targetz#,1)
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Object_Bomb_Create5(p.tPlayer, x#, y#, z#, pitch#, yaw#, roll#, bombtype#, thrownmode#=0, hastarget=false, targetx#=0, targety#=0, targetz#=0)
		yaw#=yaw#+Rand(0,360)
		i = Rand(4,5)
		For j=1 to i
		Object_Bomb_Create.tBomb(p, x#, y#, z#, pitch#, yaw#+360*(Float#(j)/Float#(i)), roll#, bombtype#, thrownmode#, hastarget, targetx#, targety#, targetz#, true)
		Next
	End Function

	Function Object_Bomb_Create.tBomb(p.tPlayer, x#, y#, z#, pitch#, yaw#, roll#, bombtype#, thrownmode#=0, hastarget=false, targetx#=0, targety#=0, targetz#=0, isfrom5#=false)
	
		b.tBomb = New tBomb

		b\targetp=p
		p\JustThrewBombTimer=0.5*secs#
		
		b\Position = New tVector
		b\Position\x# = x#
		b\Position\y# = y#
		b\Position\z# = z#
		b\Rotation = New tVector
		b\Rotation\x# = 0
		b\Rotation\y# = yaw#
		b\Rotation\z# = 0

		b\BombType=bombtype#
		b\ThrownMode#=thrownmode#
		b\MustDestroy=0
		b\InitialSpeed#=p\SpeedLength#
		If isfrom5# Then b\InitialSpeed#=b\InitialSpeed#/8.0

		Select bombtype#
		Case BOMB_BULLET,BOMB_BULLET2,BOMB_BULLET3: Object_Bomb_CreateHitBox(b,2.5,2.5,2.5)
		Case BOMB_SPEAR,BOMB_SPEARWATER,BOMB_FLAME,BOMB_WIND,BOMB_FIREBALL,BOMB_KNIFE,BOMB_HURRICANE,BOMB_ORB,BOMB_CUBETRAIL: Object_Bomb_CreateHitBox(b,4.5,4.5,4.5)
		Case BOMB_PSYCHIC,BOMB_ROCKET,-1,-2: Object_Bomb_CreateHitBox(b,5,5,5)
		Case BOMB_BOOMERANG,BOMB_NOTE,BOMB_LEAF,BOMB_GEAR,BOMB_ROCK: Object_Bomb_CreateHitBox(b,5.5,5.5,5.5)
		Case BOMB_TYPHOON,BOMB_JUSTICE: Object_Bomb_CreateHitBox(b,6,6,6)
		Case BOMB_BUBBLES,BOMB_PUNCH,BOMB_BIGBOMB: Object_Bomb_CreateHitBox(b,7,7,7)
		Case BOMB_MINION: Object_Bomb_CreateHitBox(b,7.5,7.5,7.5)
		Case BOMB_GUM: Object_Bomb_CreateHitBox(b,8,8,8)
		Case BOMB_ICE,BOMB_TIRE: Object_Bomb_CreateHitBox(b,9.5,9.5,9.5)
		Case BOMB_SHOCK: Object_Bomb_CreateHitBox(b,10,10,10)
		Case BOMB_BOX,BOMB_HOOKSHOT: Object_Bomb_CreateHitBox(b,7,7,7)
		Case BOMB_CHEESE,BOMB_FROGGY,BOMB_HANDBLADE: Object_Bomb_CreateHitBox(b,4,4,4)
		Case -100: Object_Bomb_CreateHitBox(b,thrownmode#,thrownmode#,thrownmode#)
		Case BOMB_BELLYFLOP: Object_Bomb_CreateHitBox(b,12,12,12)
		Default: Object_Bomb_CreateHitBox(b,3.5,3.5,3.5)
		End Select

		Select bombtype#
			Case BOMB_RING,BOMB_HEART,BOMB_FLOWER:
				b\DisappearTimer=3.5*secs#
				Select thrownmode#
					Case -1: b\MoveTimer=0.2*secs#
					Default: b\MoveTimer=1*secs#
				End Select
			Case BOMB_BOMB:
				Select thrownmode#
					Case -1: b\DisappearTimer=0.05*secs# : b\MoveTimer=0.2*secs#
					Default: b\DisappearTimer=3.5*secs# : b\MoveTimer=1*secs#
				End Select
			Case BOMB_BLADE:
				b\DisappearTimer=1.45*secs#
			Case BOMB_SHOT:
				b\DisappearTimer=2.5*secs#
			Case BOMB_SPEAR,BOMB_SPEARWATER,BOMB_WIND,BOMB_KNIFE:
				b\DisappearTimer=1*secs#
			Case BOMB_FLAME:
				b\DisappearTimer=1*secs#
			Case BOMB_SHOCK:
				b\DisappearTimer=0.8*secs#
			Case BOMB_ROCKET:
				b\DisappearTimer=1.688*secs#
			Case BOMB_GUM:
				Select thrownmode#
					Case 0: b\DisappearTimer=2.9*secs#
					Case 1: b\DisappearTimer=-100
				End Select
			Case BOMB_PSYCHIC,BOMB_BOX:
				b\DisappearTimer=0.65*secs#
			Case -1,-2:
				b\DisappearTimer=1.25*secs# : b\MoveTimer=1*secs#
			Case BOMB_BUBBLES:
				b\DisappearTimer=1.417*secs#
			Case BOMB_BOOMERANG,BOMB_GEAR:
				b\DisappearTimer=4*secs# : b\MoveTimer=1.124*secs#
			Case BOMB_CURSE:
				b\DisappearTimer=1.417*secs#
			Case BOMB_TYPHOON:
				b\DisappearTimer=1.2*secs#
			Case BOMB_DART:
				b\DisappearTimer=0.9*secs#
			Case BOMB_BULLET:
				b\DisappearTimer=1.26*secs#
			Case BOMB_BULLET2:
				b\DisappearTimer=0.86*secs#
			Case BOMB_BULLET3:
				b\DisappearTimer=1.86*secs#
			Case BOMB_PUNCH:
				b\DisappearTimer=0.12*secs#
			Case BOMB_ICE:
				b\DisappearTimer=2.12*secs# : b\MoveTimer=1*secs#
			Case BOMB_EXPLOSIVE:
				b\DisappearTimer=2.25*secs# : b\MoveTimer=1*secs#
			Case BOMB_BEAM:
				b\DisappearTimer=0.82*secs#
			Case BOMB_MINION:
				b\DisappearTimer=4*secs# : b\MoveTimer=1*secs#
			Case BOMB_CHEESE,BOMB_FROGGY:
				b\DisappearTimer=0.65*secs#
			Case BOMB_HANDBLADE:
				b\DisappearTimer=0.0612*secs#
			Case BOMB_BLOB,BOMB_FIREBALL,BOMB_BIGBOMB,BOMB_HURRICANE:
				b\DisappearTimer=1.12*secs#
				b\MoveTimer=1*secs#
			Case BOMB_NOTE:
				b\DisappearTimer=1.6*secs#
				b\MoveTimer=1*secs#
			Case -100:
				b\DisappearTimer=0.25*secs#
			Case BOMB_TIRE:
				b\DisappearTimer=1.85*secs#
			Case BOMB_LEAF:
				b\DisappearTimer=0.7*secs#
			Case BOMB_ORB:
				b\DisappearTimer=2.8*secs#
				b\MoveTimer=0.52484*secs#
			Case BOMB_ROCK:
				b\DisappearTimer=0.875*secs#
				b\MoveTimer=1*secs#
			Case BOMB_JUSTICE:
				b\DisappearTimer=1.4*secs#
			Case BOMB_HOOKSHOT:
				b\DisappearTimer=0.45*secs#
			Case BOMB_CUBETRAIL:
				b\DisappearTimer=2*secs#
				b\MoveTimer=0.75*secs#
			Case BOMB_BELLYFLOP:
				b\DisappearTimer=0.25*secs#
		End Select

		b\Pivot = CreatePivot()
		PositionEntity(b\Pivot, x#, y#, z#)
		RotateEntity b\Pivot, 0, yaw#, 0

		Select bombtype#
			Case BOMB_RING: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_DummyRing)), b\Pivot)
			Case BOMB_HEART: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_HeartBomb)), b\Pivot)
			Case BOMB_EXPLOSIVE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_ExplosiveBomb)), b\Pivot)
			Case BOMB_MINION: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Minion)), b\Pivot) : Animate b\Entity,1,0.38,1,10
			Case BOMB_BLADE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_NinjaStar)), b\Pivot) : EntityRadius(b\Pivot,2)
			Case BOMB_FLOWER: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Flower)), b\Pivot)
			Case BOMB_SHOT: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_PawnMissile)), b\Pivot) : MoveEntity b\Pivot,0,0,1 : EntityRadius(b\Pivot,1.5)
			Case BOMB_SHOCK,BOMB_ICE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), b\Pivot) : MoveEntity b\Pivot,0,0,1
			Case BOMB_SPEAR,BOMB_SPEARWATER,BOMB_FLAME,BOMB_PUNCH,BOMB_FIREBALL,BOMB_CUBETRAIL,BOMB_PSYCHIC,-1,-2,-100,BOMB_BELLYFLOP: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), b\Pivot)
			Case BOMB_BUBBLES:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), b\Pivot) : MoveEntity b\Pivot,0,0,1
				b\Channel_Bomb=EmitSmartSound(Sound_BubbleBeam,b\Entity)
			Case BOMB_BOOMERANG: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Boomerang)), b\Pivot) : EntityRadius(b\Pivot,4)
			Case BOMB_GEAR: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Gear)), b\Pivot) : EntityRadius(b\Pivot,4)
			Case BOMB_GUM:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Gum)), b\Pivot) : EntityRadius(b\Pivot,8) : Animate b\Entity,3,0.825
				Select thrownmode#
					Case 0: MoveEntity b\Pivot,0,1.2,5
				End Select
			Case BOMB_CURSE:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), b\Pivot) : MoveEntity b\Pivot,0,0,1
				b\Channel_Bomb=EmitSmartSound(Sound_Curse,b\Entity)
			Case BOMB_ROCKET: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_CrabMissile)), b\Pivot) : EntityRadius(b\Pivot,1.5) : MoveEntity b\Pivot,0,0.75,3
			Case BOMB_TYPHOON:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), b\Pivot) : MoveEntity b\Pivot,0,0,1
				b\Channel_Bomb=EmitSmartSound(Sound_Flower2,b\Entity)
			Case BOMB_JUSTICE:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), b\Pivot)
				b\Channel_Bomb=EmitSmartSound(Sound_WindBlow,b\Entity)
			Case BOMB_DART:
				Select(Rand(1,3))
					Case 1: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Dart1)), b\Pivot)
					Case 2: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Dart2)), b\Pivot)
					Case 3: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Dart3)), b\Pivot)
				End Select
				EntityRadius(b\Pivot,1.5)
			Case BOMB_BOMB: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_KikiBomb)), b\Pivot)
			Case BOMB_BIGBOMB: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_KikiBomb)), b\Pivot) : ScaleEntity(b\Entity,2.25,2.25,2.25)
			Case BOMB_BULLET:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bullet)), b\Pivot)
				Select thrownmode#
					Case 1: MoveEntity b\Pivot,1.0281,2.6801,5.5446 : MoveEntity b\Pivot,+0.1,0,0
					Case 2: MoveEntity b\Pivot,1.0281,2.6801,5.5446 : MoveEntity b\Pivot,-0.1,0,0
					Case 3: MoveEntity b\Pivot,3.0091,6.9319,3.8327
				End Select
			Case BOMB_BULLET2,BOMB_BULLET3:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bullet)), b\Pivot)
			Case BOMB_BEAM:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Beam)), b\Pivot)
				MoveEntity b\Pivot,1.73,4.6283,4.7181
			Case BOMB_BOX: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxWooden)), b\Pivot) : EntityRadius(b\Pivot,7)
			Case BOMB_CHEESE:
				b\Entity = LoadAnimMesh("Characters/"+ShortCharNames$(CHAR_CHE,1)+".b3d", b\Pivot)
				i=1
				ExtractAnimSeq(b\Entity,		0,	0)		 	: i=i+17		;Idle
				ExtractAnimSeq(b\Entity,		0,	0)		 	: i=i+17		;Walk
				ExtractAnimSeq(b\Entity,		i,	-1+i+17)	: i=i+17		;Run
				Animate(b\Entity,1,0.3188,3,10)
			Case BOMB_FROGGY:
				b\Entity = LoadAnimMesh("Characters/"+ShortCharNames$(CHAR_FRO,1)+".b3d", b\Pivot)
				i=1
				ExtractAnimSeq(b\Entity,		0,	0) 			: i=i+17		;Idle
				ExtractAnimSeq(b\Entity,		i,	-1+i+17)	: i=i+17		;Walk
				Animate(b\Entity,1,1.088,2,10)
				EmitSmartSound(Sound_Frog,b\Entity)
			Case BOMB_HANDBLADE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Blade)), b\Pivot)
			Case BOMB_BLOB: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WaterBlob)), b\Pivot) : Animate b\Entity,1,0.142,1,10
			Case BOMB_NOTE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Note1+Rand(1,3)-1)), b\Pivot) : MoveEntity b\Pivot,0,1.2,5 : EntityRadius(b\Pivot,4)
			Case BOMB_WIND: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Wind)), b\Pivot) : Animate b\Entity,1,0.35,1,10
			Case BOMB_KNIFE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Knife)), b\Pivot)
			Case BOMB_HURRICANE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Hurricane)), b\Pivot) : Animate b\Entity,1,0.2,1,10
			Case BOMB_TIRE: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tire)), b\Pivot) : EntityRadius(b\Pivot,6)
				MoveEntity b\Pivot,0,0,8.5
			Case BOMB_LEAF: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Leaf)), b\Pivot) : EntityRadius(b\Pivot,3)
			Case BOMB_ORB: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_EggMissile4)), b\Pivot) : EntityRadius(b\Pivot,3.5) : MoveEntity b\Pivot,0,0,3
			Case BOMB_ROCK:
				Select(Rand(1,3))
				Case 1: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_RockChunk1_brown)), b\Pivot)
				Case 2: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_RockChunk2_brown)), b\Pivot)
				Case 3: b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_RockChunk3_brown)), b\Pivot)
				End Select
				ScaleEntity(b\Entity,2.78,2.78,2.78)
				RotateEntity(b\Entity,0,Rand(1,360),0,1)
				EntityRadius(b\Pivot,3.5)
			Case BOMB_HOOKSHOT:
				b\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Hookshot)), b\Pivot)
				b\BombRoot=FindChild(b\Entity, "rope")
		End Select

		EntityType(b\Pivot,COLLISION_OBJECT_GOTHRU)

		b\MayDestroyTimer=0.25*secs#

		b\Particle = ParticleTemplate_Create.tParticleTemplate()

		If Menu\Mission=MISSION_RIVAL# Then
			If p\No#>0 Then b\HurtPlayer=-1 ElseIf p\No#<0 Then b\HurtPlayer=1
		EndIf

		If hastarget Then Object_Bomb_Create_Target(b, hastarget, targetx#, targety#, targetz#)
		
		Return b
		
	End Function
	
	; =========================================================================================================
	
	Function Object_Bomb_Update(b.tBomb, d.tDeltaTime)

		; Fix weird collision error
		For i = 1 To CountCollisions(b\Pivot)
			Select GetEntityType(CollisionEntity(b\Pivot, i))
				Case COLLISION_WORLD_POLYGON
					Nx# = CollisionNX#(b\Pivot, i)
					Ny# = CollisionNY#(b\Pivot, i)
					Nz# = CollisionNZ#(b\Pivot, i)
			End Select
		Next

		; Update position
		b\Position\x# = EntityX(b\Pivot)
		b\Position\y# = EntityY(b\Pivot)
		b\Position\z# = EntityZ(b\Pivot)

		; Update timer
		If b\MoveTimer>0 Then b\MoveTimer=b\MoveTimer-timervalue#
		If b\DisappearTimer>0 Then b\DisappearTimer=b\DisappearTimer-timervalue#
		If b\MayDestroyTimer>0 Then b\MayDestroyTimer=b\MayDestroyTimer-timervalue#

		; Movement
		If b\TargetAssigned Then
			PointEntity(b\Pivot,b\TargetAssignedObj\Entity)
			MoveEntity b\Pivot,0,0,3.2*d\Delta
			If b\TargetAssignedObj\Done=1 Then
				b\TargetAssigned=False
				RotateEntity b\Pivot, 0, EntityYaw(b\Pivot), 0
			EndIf
		Else
			Select b\BombType
				Case BOMB_RING,BOMB_HEART,BOMB_FLOWER,BOMB_BOMB,BOMB_EXPLOSIVE,BOMB_ROCK:
					Select b\ThrownMode#
						Case -0.25,0,+0.25,-0.125,+0.125:
							If b\MoveTimer>0.9*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/3)*d\Delta,1*d\Delta,(1+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.8*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/2.5)*d\Delta,0.7*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.7*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/2)*d\Delta,0.4*d\Delta,(0.2+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.6*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/1.5)*d\Delta,0,(0.1+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.5*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/1)*d\Delta,-0.2*d\Delta,(0.2+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.4*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/0.5)*d\Delta,-0.4*d\Delta,(0.3+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.3*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/1)*d\Delta,-0.7*d\Delta,(0.5+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.2*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/1.5)*d\Delta,-0.8*d\Delta,(0.8+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.1*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/2)*d\Delta,-1*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.0*secs# Then
								MoveEntity b\Pivot,(b\ThrownMode#/2.5)*d\Delta,-2*d\Delta,(0.1+b\InitialSpeed#)*d\Delta
							Else
								MoveEntity b\Pivot,0,-3*d\Delta,0
							EndIf
						Case -1:
							If b\InitialSpeed#>0 Then b\InitialSpeed#=b\InitialSpeed#-0.2*d\Delta
							If b\MoveTimer>0.1*secs# Then
								MoveEntity b\Pivot,0,-1*d\Delta,(0+b\InitialSpeed#)*d\Delta
							ElseIf b\MoveTimer>0.0*secs# Then
								MoveEntity b\Pivot,0,-2*d\Delta,(0+b\InitialSpeed#)*d\Delta
							Else
								MoveEntity b\Pivot,0,-3*d\Delta,(0+b\InitialSpeed#)*d\Delta
							EndIf
					End Select
				Case BOMB_BLADE:
					TurnEntity b\Entity, 0, 0.55*20*d\Delta, 0
					MoveEntity b\Pivot,0,0,(2.5+b\InitialSpeed#)*d\Delta
				Case BOMB_SHOT:
					MoveEntity b\Pivot,0,0,(1.8+b\InitialSpeed#)*d\Delta
					TurnEntity b\Entity, 0, 0, 0.4*20*d\Delta
				Case BOMB_SPEAR,BOMB_SPEARWATER:
					MoveEntity b\Pivot,0,0,(3+b\InitialSpeed#)*d\Delta
					If b\DisappearTimer<0.75*secs# Then MoveEntity b\Pivot,0,-0.34*d\Delta,0
				Case BOMB_WIND,BOMB_FLAME:
					TurnEntity b\Entity, 0, 0, 0.4*20*d\Delta
					MoveEntity b\Pivot,0,0,(3.2+b\InitialSpeed#)*d\Delta
				Case BOMB_SHOCK:
					MoveEntity b\Pivot,0,-0.05*d\Delta,(2.87+b\InitialSpeed#)*d\Delta
				Case BOMB_GUM:
					Select b\ThrownMode#
						Case 0:
							EntityType(b\Pivot,COLLISION_OBJECT_GOTHRU)
							If b\InitialSpeed#>0 Then b\InitialSpeed#=b\InitialSpeed#-0.2*d\Delta
							MoveEntity b\Pivot,0,0.13*d\Delta,(1+b\InitialSpeed#)*d\Delta
							If EntityCollided(b\Pivot,COLLISION_WORLD_POLYGON) Then TurnEntity b\Pivot,0,(EntityYaw(b\Pivot)+90)+Rand(-50,50),0
						Case 1:
							EntityType(b\Pivot,COLLISION_NONE)
							PositionEntity b\Pivot,EntityX(b\targetp\Objects\Gum,1),EntityY(b\targetp\Objects\Gum,1),EntityZ(b\targetp\Objects\Gum,1)
							RotateEntity b\Pivot,0,b\targetp\Animation\Direction#-180,0
							If Not(b\targetp\Action=ACTION_SLOWGLIDE) Then b\DisappearTimer=2.9*secs# : b\ThrownMode#=0
					End Select
				Case BOMB_BUBBLES:
					MoveEntity b\Pivot,0,0,(2+b\InitialSpeed#)*d\Delta
				Case BOMB_BOOMERANG,BOMB_GEAR:
					TurnEntity b\Entity, 0, 0.46*20*d\Delta, 0
					If Not(b\MoveTimer>0) Then
						PointEntity(b\Pivot,b\targetp\Objects\Entity)
						If EntityDistance(b\Pivot,b\targetp\Objects\entity)<3 Then b\MustDestroy=1
					EndIf
					MoveEntity b\Pivot,0,0,(1.78+b\InitialSpeed#)*d\Delta
					If Not(pp(1)\Character=b\targetp\Character) Then b\MustDestroy=1
				Case BOMB_PSYCHIC,BOMB_BOX:
					MoveEntity b\Pivot,0,0.001*d\Delta,(4+b\InitialSpeed#)*d\Delta
				Case BOMB_CURSE:
					MoveEntity b\Pivot,0,0,(2+b\InitialSpeed#)*d\Delta
				Case BOMB_ROCKET:
					MoveEntity b\Pivot,0,0,(1.6+b\InitialSpeed#)*d\Delta
					TurnEntity b\Entity, 0, 0, 0.4*20*d\Delta
				Case BOMB_TYPHOON,BOMB_JUSTICE:
					MoveEntity b\Pivot,0,0,(1.5+b\InitialSpeed#)*d\Delta
				Case -1:
					If b\MoveTimer>0.9*secs# Then
						MoveEntity b\Pivot,0,1.5*1*d\Delta,(1.5*1+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.8*secs# Then
						MoveEntity b\Pivot,0,1.5*0.7*d\Delta,(1.5*0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.7*secs# Then
						MoveEntity b\Pivot,0,1.5*0.4*d\Delta,(1.5*0.2+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.6*secs# Then
						MoveEntity b\Pivot,0,1.5*0*d\Delta,(1.5*0.1+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.5*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.2*d\Delta,(1.5*0.2+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.4*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.4*d\Delta,(1.5*0.3+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.3*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.7*d\Delta,(1.5*0.5+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.2*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.8*d\Delta,(1.5*0.8+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.1*secs# Then
						MoveEntity b\Pivot,0,1.5*-1*d\Delta,(1.5*0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.0*secs# Then
						MoveEntity b\Pivot,0,1.5*-2*d\Delta,(1.5*0.1+b\InitialSpeed#)*d\Delta
					Else
						MoveEntity b\Pivot,0,1.5*-3*d\Delta,0
					EndIf
					If EntityCollided(b\Pivot,COLLISION_WORLD_POLYGON) Then b\MustDestroy=1
				Case -2:
					If b\MoveTimer>0.9*secs# Then
						MoveEntity b\Pivot,0,1.5*1*d\Delta*0.5,(1.5*1+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.8*secs# Then
						MoveEntity b\Pivot,0,1.5*0.7*d\Delta*0.5,(1.5*0.4+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.7*secs# Then
						MoveEntity b\Pivot,0,1.5*0.4*d\Delta*0.5,(1.5*0.2+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.6*secs# Then
						MoveEntity b\Pivot,0,1.5*0*d\Delta*0.5,(1.5*0.1+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.5*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.2*d\Delta*0.5,(1.5*0.2+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.4*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.4*d\Delta*0.5,(1.5*0.3+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.3*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.7*d\Delta*0.5,(1.5*0.5+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.2*secs# Then
						MoveEntity b\Pivot,0,1.5*-0.8*d\Delta*0.5,(1.5*0.8+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.1*secs# Then
						MoveEntity b\Pivot,0,1.5*-1*d\Delta*0.5,(1.5*0.4+b\InitialSpeed#)*d\Delta*0.5
					ElseIf b\MoveTimer>0.0*secs# Then
						MoveEntity b\Pivot,0,1.5*-2*d\Delta*0.5,(1.5*0.1+b\InitialSpeed#)*d\Delta*0.5
					Else
						MoveEntity b\Pivot,0,1.5*-3*d\Delta*0.5,0
					EndIf
					If EntityCollided(b\Pivot,COLLISION_WORLD_POLYGON) Then b\MustDestroy=1
				Case BOMB_DART:
					MoveEntity b\Pivot,0,0,(1.82+b\InitialSpeed#)*d\Delta
				Case BOMB_BULLET,BOMB_BULLET2:
					MoveEntity b\Pivot,0,0,(1.46+b\InitialSpeed#)*d\Delta
				Case BOMB_BULLET3:
					MoveEntity b\Pivot,0,0,(4.5185+b\InitialSpeed#)*d\Delta
				Case BOMB_ICE:
					If b\MoveTimer>0.9*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/3)*d\Delta,1*d\Delta,(0.25*1.25+1+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.8*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/2.5)*d\Delta,0.7*d\Delta,(0.25*1.25+0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.7*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/2)*d\Delta,0.4*d\Delta,(0.25*1.25+0.2+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.6*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/1.5)*d\Delta,0,(0.25*1.25+0.1+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.5*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/1)*d\Delta,-0.2*d\Delta,(0.25*1.25+0.2+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.4*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/0.5)*d\Delta,-0.4*d\Delta,(0.25*1.25+0.3+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.3*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/1)*d\Delta,-0.7*d\Delta,(0.25*1.25+0.5+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.2*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/1.5)*d\Delta,-0.8*d\Delta,(0.25*1.25+0.8+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.1*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/2)*d\Delta,-1*d\Delta,(0.25*1.25+0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.0*secs# Then
						MoveEntity b\Pivot,(b\ThrownMode#/2.5)*d\Delta,-2*d\Delta,(0.25*1.25+0.1+b\InitialSpeed#)*d\Delta
					Else
						MoveEntity b\Pivot,0,-3*d\Delta,0
					EndIf
				Case BOMB_BEAM:
					TurnEntity b\Entity, 0, 0, 0.4*20*d\Delta
					MoveEntity b\Pivot,0,0,(1.12+b\InitialSpeed#)*d\Delta
				Case BOMB_MINION:
					If Not(b\MoveTimer>0) Then
						PointEntity(b\Pivot,b\targetp\Objects\Entity)
						If EntityDistance(b\Pivot,b\targetp\Objects\entity)<3 Then b\MustDestroy=1
					EndIf
					MoveEntity b\Pivot,0,0.0133*d\Delta,(0.488+b\InitialSpeed#)*d\Delta
					If Not(pp(1)\Character=b\targetp\Character) Then b\MustDestroy=1
				Case BOMB_CHEESE:
					MoveEntity b\Pivot,0,0,(3.2+b\InitialSpeed#)*d\Delta
				Case BOMB_FROGGY:
					MoveEntity b\Pivot,0,0,(3.2+b\InitialSpeed#)*d\Delta
				Case BOMB_HANDBLADE:
					Select b\targetp\PunchNumber
						Case 1: PositionEntity b\Pivot,EntityX(b\targetp\Objects\HandR,1),EntityY(b\targetp\Objects\HandR,1),EntityZ(b\targetp\Objects\HandR,1),1
						Case 2: PositionEntity b\Pivot,EntityX(b\targetp\Objects\HandL,1),EntityY(b\targetp\Objects\HandL,1),EntityZ(b\targetp\Objects\HandL,1),1
					End Select
				Case BOMB_BLOB,BOMB_FIREBALL,BOMB_BIGBOMB,BOMB_HURRICANE:
					bounce=false
					If b\MoveTimer>=1.0*secs# Then
						MoveEntity b\Pivot,0,-0.6*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.8*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.6*secs# Then
						MoveEntity b\Pivot,0,-0.4*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.4*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.2*secs# Then
						MoveEntity b\Pivot,0,-0.4*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.0*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.4+b\InitialSpeed#)*d\Delta
					Else
						MoveEntity b\Pivot,0,-0.4*d\Delta,0
					EndIf
					If b\MoveTimer>0.8*secs# and b\MoveTimer<0.81*secs# Then
						bounce=true
					ElseIf b\MoveTimer>0.6*secs# and b\MoveTimer<0.61*secs# Then
						bounce=true
					ElseIf b\MoveTimer>0.4*secs# and b\MoveTimer<0.41*secs# Then
						bounce=true
					ElseIf b\MoveTimer>0.2*secs# and b\MoveTimer<0.21*secs# Then
						bounce=true
					ElseIf b\MoveTimer>0.0*secs# and b\MoveTimer<0.01*secs# Then
						bounce=true
					EndIf
					Select b\BombType
						Case BOMB_BLOB: If bounce and (Not(ChannelPlaying(b\Channel_Bomb))) Then b\Channel_Bomb=EmitSmartSound(Sound_Bounce,b\Entity)
						Case BOMB_BIGBOMB: If bounce and (Not(ChannelPlaying(b\Channel_Bomb))) Then b\Channel_Bomb=EmitSmartSound(Sound_Crusher,b\Entity)
					End Select
				Case BOMB_NOTE:
					changecolor=false
					If b\MoveTimer>=1.6*secs# Then
						MoveEntity b\Pivot,0,-0.6*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
						changecolor=true
					ElseIf b\MoveTimer>1.41*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>1.4*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>1.21*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>1.2*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>1.01*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>1.0*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>0.81*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.8*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>0.61*secs# Then
						MoveEntity b\Pivot,0,-0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.6*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>0.41*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.4*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>0.21*secs# Then
						MoveEntity b\Pivot,0,-0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.2*secs# Then
						changecolor=true
					ElseIf b\MoveTimer>0.01*secs# Then
						MoveEntity b\Pivot,0,0.4*d\Delta,(0.62+b\InitialSpeed#)*d\Delta
					ElseIf b\MoveTimer>0.0*secs# Then
						changecolor=true
					Else
						MoveEntity b\Pivot,0,-0.4*d\Delta,0
					EndIf
					PointEntity b\Entity, cam\Entity
					If changecolor Then EntityColor(b\Entity,Rand(180,250),Rand(180,250),Rand(180,250))
				Case -100:
					MoveEntity b\Pivot,0,0,0.5*d\Delta
				Case BOMB_KNIFE:
					MoveEntity b\Pivot,0,0,(1.45+b\InitialSpeed#)*d\Delta
				Case BOMB_TIRE:
					TurnEntity b\Entity,10*d\Delta,0,0
					MoveEntity b\Pivot,0,-0.8*d\Delta,(0.8+b\InitialSpeed#)*d\Delta
				Case BOMB_LEAF:
					TurnEntity b\Entity, 0, 0.46*20*d\Delta, 0
					MoveEntity b\Pivot,0,0,(1.78+b\InitialSpeed#)*d\Delta
				Case BOMB_ORB:
					TurnEntity b\Entity, 0, 0, 0.4*20*d\Delta
					MoveEntity b\Pivot,0,0,(1.4+b\InitialSpeed#)*d\Delta
					If b\MoveTimer>0 Then MoveEntity b\Pivot,0.82*b\ThrownMode#*d\Delta,0,0
				Case BOMB_HOOKSHOT:
					PositionEntity b\BombRoot, EntityX(b\targetp\Objects\HandR,1), EntityY(b\targetp\Objects\HandR,1), EntityZ(b\targetp\Objects\HandR,1), 1
					If b\HasTarget and b\MayNotDestroy=1 Then
						PositionEntity b\Pivot, EntityX(b\TargetPivot,1), EntityY(b\TargetPivot,1), EntityZ(b\TargetPivot,1), 1
						If b\targetp\Action=ACTION_HOOKSHOT Then
							Player_SetSpeed(b\targetp,2)
							Player_SetSpeedY(b\targetp,1.5)
							Player_ActuallyJump(b\targetp)
						EndIf
						If Not(b\MoveTimer>0) Then b\MayNotDestroy=0
					Else
						MoveEntity b\Pivot,0,0.375*d\Delta,(3.8+b\InitialSpeed#)*d\Delta
					EndIf
				Case BOMB_CUBETRAIL:
					Bomb_RubyCubes(pp(1),b)
					If Not(b\MoveTimer>0) Then
						b\MoveTimer=0.5*secs#
						TurnEntity b\Pivot, 0, 90, 0
					EndIf
					MoveEntity b\Pivot,0,0,(0.54+b\InitialSpeed#)*d\Delta
			End Select
		EndIf

		; Destroy bomb if time is over
		If (Not(b\DisappearTimer>0)) and (Not(b\DisappearTimer=-100)) Then b\MustDestroy=1

		; Hurt player
		Select b\HurtPlayer
			Case 1:
				If EntityDistance(b\Pivot, pp(1)\Objects\Entity)<b\HitBox\y#+2.5+0.5*pp(1)\ScaleFactor# Then Player_Hit(pp(1)) : b\MustDestroy=1
			Case -1:
				For i=1 to Game\RivalAmount
				If EntityDistance(b\Pivot, ppe(i)\Objects\Entity)<b\HitBox\y#+2.5+0.5*ppe(i)\ScaleFactor# and (Not(ppe(i)\Action=ACTION_RIVALDIE)) Then
					If EntityDistance(pp(1)\Objects\Entity, ppe(i)\Objects\Entity)<75 Then Player_Hit(ppe(i)) : b\MustDestroy=1
				EndIf
				Next
		End Select

		; Targeting
		If b\HasTarget Then
			If (Not b\TargetAssigned) Then PointEntity(b\Pivot,b\TargetPivot)
			If EntityDistance(b\Pivot,b\TargetPivot)>5 Then b\NotAffectable=True Else b\NotAffectable=False
		EndIf

		; Bullet particle and sound
		Select b\BombType
			Case BOMB_BLADE:
				ParticleTemplate_Call(b\Particle, PARTICLE_PLAYER_ATTACKTRAIL, b\Entity, 2, 0.075, 3, 0, 6)
			Case BOMB_SHOT:
				ParticleTemplate_Call(b\Particle, PARTICLE_PLAYER_BULLETHEAT, b\Entity, 1, 1, 1)
			Case BOMB_SPEAR:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_SHINE, b\Pivot)
			Case BOMB_SPEARWATER:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_SHINEWATER, b\Pivot)
			Case BOMB_FLAME:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_FIRE, b\Pivot, 1)
			Case BOMB_SHOCK:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_ELECTRIC, b\Pivot)
			Case BOMB_BUBBLES:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_BUBBLES, b\Pivot)
			Case BOMB_BOOMERANG,BOMB_GEAR:
				If Not(ChannelPlaying(b\Channel_Bomb)) Then b\Channel_Bomb=EmitSmartSound(Sound_Boomerang,b\Entity)
				ParticleTemplate_Call(b\Particle, PARTICLE_PLAYER_ATTACKTRAIL, b\Entity, 2.25, 0.075, 3, 0, 7)
			Case BOMB_CURSE:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_CURSE, b\Pivot)
			Case BOMB_ROCKET:
				ParticleTemplate_Call(b\Particle, PARTICLE_PLAYER_SMOKE, b\Entity, 0.6, 0.064, 1, 0, 7)
			Case BOMB_TYPHOON:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_FLOWERS, b\Pivot, 2.5)
			Case BOMB_JUSTICE:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_JUSTICE, b\Pivot, 2.5)
			Case BOMB_BULLET,BOMB_BULLET2,BOMB_BULLET3:
				ParticleTemplate_Call(b\Particle, PARTICLE_PLAYER_ATTACKTRAIL, b\Entity, 0.5, 0.075, 3, 0, 8)
			Case BOMB_ICE:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_SNOW, b\Pivot)
			Case BOMB_CHEESE:
				If Not(ChannelPlaying(b\Channel_Bomb)) Then b\Channel_Bomb=EmitSmartSound(Sound_Chao,b\Entity)
				ParticleTemplate_Call(b\Particle, PARTICLE_CHAO_CHEESE, b\Entity)
			Case BOMB_FIREBALL:
				ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_FIRE, b\Pivot, 1.65)
			Case BOMB_HURRICANE:
				If (Not(ChannelPlaying(b\Channel_Bomb))) Then b\Channel_Bomb=EmitSmartSound(Sound_Hurricane,b\Entity)
			Case BOMB_LEAF:
				ParticleTemplate_Call(b\Particle, PARTICLE_PLAYER_ATTACKTRAIL, b\Entity, 2.25, 0.075, 3, 0, 7)
				If Not(ChannelPlaying(b\Channel_Bomb)) Then b\Channel_Bomb=EmitSmartSound(Sound_Boomerang,b\Entity)
			Case BOMB_TIRE:
				If Not(ChannelPlaying(b\Channel_Bomb)) Then b\Channel_Bomb=EmitSmartSound(Sound_EnemyMotor,b\Entity)
		End Select
				
		; Destroy bomb
		If (b\MustDestroy>=1) and (Not(b\MayDestroyTimer>0)) and b\MayNotDestroy=0 Then
			Select b\BombType
				Case BOMB_FLOWER,BOMB_RING:
					If b\MustDestroy<=1 Then ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_SHINE, b\Pivot)
				Case BOMB_HEART,BOMB_BOMB,BOMB_ROCKET,BOMB_EXPLOSIVE,BOMB_BIGBOMB:
					If b\MustDestroy<=1 Then ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_BOMB, b\Pivot) : EmitSmartSound(Sound_Bombed,b\Pivot)
				Case BOMB_MINION:
					If b\MustDestroy<=1 Then ParticleTemplate_Call(b\Particle, PARTICLE_BOMB_CURSE, b\Pivot) : EmitSmartSound(Sound_Minion,b\Pivot)
				Case BOMB_SPEAR,BOMB_SPEARWATER:
					If b\MustDestroy<=1 Then EmitSmartSound(Sound_SpearImpact,b\Entity)
				Case BOMB_BOOMERANG,BOMB_GEAR:
					StopChannel(b\Channel_Bomb) : b\targetp\BoomerangAway=0
				Case BOMB_PSYCHIC:
					For o.tObject=Each tObject
					If o\ThrownAsBomb=b\ThrownMode Then
						Select o\ObjType
							Case OBJTYPE_ENEMYMISSILE: o\EnemyMissile\DisappearTimer=0
							Case OBJTYPE_SPIKEBALL,OBJTYPE_BOXIRON: o\Psychoed=0
							Case OBJTYPE_OMOCHAO: o\Psychoed=0 : o\Omochao\Mode=0
							Case OBJTYPE_CHAO: o\Psychoed=0
							Case OBJTYPE_CHAIR,OBJTYPE_PARASOL: o\Psychoed=0
							Default:
								If o\ThisIsAnEnemy Then o\Enemy\Shield=0
								o\BombHit=True
						End Select
						o\ThrownAsBomb=0
					EndIf
					Next
				Case -1,-2:
					For o.tObject=Each tObject
					If o\ThrownAsBomb=b\ThrownMode Then
						If o\ThisIsAMonitor Then
							o\ObjPickedUp=0
							o\BombHit=True
						Else
							Select o\ObjType
								Case OBJTYPE_BOXWOODEN: o\BombHit=True
								Case OBJTYPE_OMOCHAO: o\ObjPickedUp=0 : o\Omochao\Mode=0
								Case OBJTYPE_CHAO: o\ObjPickedUp=0
								Default: o\ObjPickedUp=0
							End Select
						EndIf
						o\ThrownAsBomb=0
					EndIf
					Next
				Case BOMB_CURSE,BOMB_BUBBLES,BOMB_CHEESE,BOMB_TYPHOON,BOMB_HURRICANE,BOMB_TIRE,BOMB_LEAF,BOMB_JUSTICE:
					StopChannel(b\Channel_Bomb)
				Case BOMB_BEAM,BOMB_NOTE:
					If b\MustDestroy<=1 Then EmitSmartSound(Sound_Beam,b\Pivot)
				Case BOMB_BOX:
					If b\MustDestroy<=1 Then
						EmitSmartSound(Sound_Boxdestroy,b\Entity)
						Object_Pieces_Create(false,OBJTYPE_BOXWOODEN,1,b\Position\x#,b\Position\y#,b\Position\z#,b\Rotation\x#,b\Rotation\y#,b\Rotation\z#,1.0)
					EndIf
				Case BOMB_HANDBLADE:
					EmitSmartSound(Sound_Blade,b\Pivot)
				Case BOMB_KNIFE:
					If b\MustDestroy<=1 Then EmitSmartSound(Sound_PsychoHold,b\Entity)
				Case BOMB_ROCK:
					EmitSmartSound(Sound_Break,b\Pivot)
					Object_Pieces_Create(false,OBJTYPE_ROCK,0,b\Position\x#,b\Position\y#,b\Position\z#,b\Rotation\x#,b\Rotation\y#,b\Rotation\z#,0.65,0,false,1)
				Case BOMB_HOOKSHOT:
					If b\targetp\Action=ACTION_HOOKSHOT Then
						If b\targetp\Motion\Ground Then b\targetp\Action=ACTION_COMMON Else b\targetp\Action=ACTION_JUMPFALL
					EndIf
			End Select

			; Make Sonic a move
			If b\MustDestroy<=1 and b\ThrownMode#=1 And b\BombType=BOMB_GUM And b\targetp\Action=ACTION_SLOWGLIDE Then Player_JumpActionInteract(b\targetp,1)

			; Delete the bomb
			If b\ThrownAsBomb Then Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount-1
			FreeEntity b\Entity
			FreeEntity b\Pivot
			Delete b\Position
			Delete b\Rotation
			If b\HasTarget Then Delete b\Target : FreeEntity b\TargetPivot
			Delete b
			Return
		EndIf
		
	End Function