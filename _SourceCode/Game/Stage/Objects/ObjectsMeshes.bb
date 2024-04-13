
	Dim MESHES(MESHES_TOTAL)
	Dim MESHES_EXISTS(MESHES_TOTAL)

	Function SmartEntity(x)
		If MESHES_EXISTS(x)=false Then LoadSmartEntity(x)
		Return x
	End Function

	Function FreeSmartEntity(x)
		If MESHES_EXISTS(x) Then
			FreeEntity MESHES(x) : MESHES(x)=0
			MESHES_EXISTS(x)=false
		EndIf
	End Function

	Function CheckLoadSmartEntity(x)
		If MESHES_EXISTS(x)=false Then LoadSmartEntity(x)
	End Function

;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------

Function LoadSmartEntity(x)

	Select x
		Case Mesh_Empty:			MESHES(x) = LoadAnimMesh("Objects/Empty.b3d")
		Case Mesh_Quad:				MESHES(x) = LoadMesh("Objects\Quad.b3d")
		Case Mesh_Spark:			MESHES(x) = LoadAnimMesh("Objects/Shields/SlideSpark.b3d")
		Case Mesh_JumpBall:			MESHES(x) = LoadAnimMesh("Objects/Shields/JumpBall.b3d")
		Case Mesh_Stomp:			MESHES(x) = LoadAnimMesh("Objects/Shields/Stomp.b3d")
		Case Mesh_StompTrail:		MESHES(x) = LoadAnimMesh("Objects/Shields/Stomp2.b3d")
		Case Mesh_Forth:			MESHES(x) = LoadAnimMesh("Objects/Shields/Forth.b3d")
		Case Mesh_Razer:			MESHES(x) = LoadAnimMesh("Objects/Shields/Razer.b3d")
		Case Mesh_Cube:				MESHES(x) = LoadAnimMesh("Objects/Shields/Cube.b3d")
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Ice:				MESHES(x) = LoadAnimMesh("Objects/Shields/Ice.b3d")
		Case Mesh_InstaShield: 		MESHES(x) = LoadAnimMesh("Objects/Shields/Instashield.b3d")
		Case Mesh_ShieldNormal:		MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldNormal.b3d")
		Case Mesh_ShieldFlame:		MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldFlame.b3d")
		Case Mesh_ShieldBubble:		MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldBubble.b3d")
		Case Mesh_ShieldThunder:	MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldThunder.b3d")
		Case Mesh_ShieldEarth:		MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldEarth.b3d")
		Case Mesh_SuperShield:		MESHES(x) = LoadAnimMesh("Objects/Shields/SuperShield.b3d")
		Case Mesh_Board:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Board.b3d") : ScaleEntity MESHES(x),0.45,0.45,0.45
		Case Mesh_Glider:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Glider.b3d") : ScaleEntity MESHES(x),0.45,0.45,0.45
		Case Mesh_Bike:				MESHES(x) = LoadAnimMesh("Objects/Monitors/Bike.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Kart:				MESHES(x) = LoadAnimMesh("Objects/Monitors/Kart.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Bobsleigh:		MESHES(x) = LoadAnimMesh("Objects/Monitors/Bobsleigh.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Tornado1:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Tornado.b3d")
									ExtractAnimSeq(MESHES(x),1,9) : ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Tornado2:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Tornado2.b3d")
									ExtractAnimSeq(MESHES(x),1,9) : ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Cyclone:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Cyclone.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Scanner:			MESHES(x) = LoadAnimMesh("Objects/Shields/Scanner.b3d") : ScaleEntity MESHES(x),0.3,0.3,1.75
		Case Mesh_BlackShield1:		MESHES(x) = LoadAnimMesh("Objects/Shields/BlackShield1.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_BlackShield2:		MESHES(x) = LoadAnimMesh("Objects/Shields/BlackShield2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Flower:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Flower.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_HeartBomb:		MESHES(x) = LoadAnimMesh("Objects/Bombs/HeartBomb.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_NinjaStar:		MESHES(x) = LoadAnimMesh("Objects/Bombs/NinjaStar.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Gum:				MESHES(x) = LoadAnimMesh("Objects/Bombs/Gum.b3d") : ScaleEntity MESHES(x),1,1,1
		Case Mesh_Boomerang:		MESHES(x) = LoadAnimMesh("Objects/Bombs/Boomerang.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Dart1:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Dart1.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Dart2:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Dart2.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Dart3:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Dart3.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Bullet:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Bullet.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Beam:				MESHES(x) = LoadAnimMesh("Objects/Bombs/Beam.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_Minion:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Minion.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_DummyRing:		MESHES(x) = LoadAnimMesh("Objects/Rings/DummyRing.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6 : PositionMesh MESHES(x),0,1.557,0
		Case Mesh_ExplosiveBomb:	MESHES(x) = LoadAnimMesh("Objects/Bombs/ExplosiveBomb.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Blade:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Blade.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_WaterBlob:		MESHES(x) = LoadAnimMesh("Objects/Bombs/WaterBlob.b3d") : ScaleEntity MESHES(x),0.246,0.246,0.246
									ExtractAnimSeq(MESHES(x),1,11)
		Case Mesh_Note1:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Note1.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Note2:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Note2.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Note3:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Note3.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Wind:				MESHES(x) = LoadAnimMesh("Objects/Bombs/Wind.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Knife:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Knife.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Hurricane:		MESHES(x) = LoadAnimMesh("Objects/Bombs/Hurricane.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Tire:				MESHES(x) = LoadAnimMesh("Objects/Bombs/Tire.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Leaf:				MESHES(x) = LoadAnimMesh("Objects/Bombs/Leaf.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Gear:				MESHES(x) = LoadAnimMesh("Objects/Bombs/Gear.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Hookshot:			MESHES(x) = LoadAnimMesh("Objects/Bombs/Hookshot.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Emerald:			MESHES(x) = LoadAnimMesh("Objects/Emeralds/Emerald.b3d")
		Case Mesh_Sol:				MESHES(x) = LoadAnimMesh("Objects/Emeralds/Sol.b3d")
		Case Mesh_EnemyPiece1:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece1.b3d")
		Case Mesh_EnemyPiece2:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece2.b3d")
		Case Mesh_EnemyPiece3:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece3.b3d")
		Case Mesh_EnemyPiece4:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece4.b3d")
		Case Mesh_EnemyPiece5:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece5.b3d")
		Case Mesh_EnemyPiece6:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece6.b3d")
		Case Mesh_EnemyPiece7:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece7.b3d")
		Case Mesh_Point:			MESHES(x) = LoadAnimMesh("Objects/Point.b3d")
		Case Mesh_Line:				MESHES(x) = LoadAnimMesh("Objects/Line.b3d")
		Case Mesh_Bubble:			MESHES(x) = LoadAnimMesh("Objects/Bubble.b3d")
		Case Mesh_Locker:			MESHES(x) = LoadAnimMesh("Objects/Locker.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Locker2:			MESHES(x) = LoadAnimMesh("Objects/Locker2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Ring:				MESHES(x) = LoadAnimMesh("Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_SpewRing:			MESHES(x) = LoadAnimMesh("Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6 : PositionMesh MESHES(x),0,1.557,0
		Case Mesh_RedRing:			MESHES(x) = LoadAnimMesh("Objects/Rings/RedRing.b3d") : ScaleEntity MESHES(x),1.3,1.3,1.3
		Case Mesh_SpewRedRing:		MESHES(x) = LoadAnimMesh("Objects/Rings/RedRing.b3d") : ScaleEntity MESHES(x),1.3,1.3,1.3: PositionMesh MESHES(x),0,1.557*2,0
		Case Mesh_EnemyMissile_BuzzMissile1:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Buzz_Missile1.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_BuzzMissile2:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Buzz_Missile2.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_ChaserMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/EggChaser_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile1:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Egg_Missile1.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile2:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Egg_Missile2.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile3:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Egg_Missile3.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile4:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Egg_Missile4.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_FlapperBomb:		MESHES(x) = LoadAnimMesh("Objects/Enemies/EggFlapper_Bomb.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_PawnMissile:		MESHES(x) = LoadAnimMesh("Objects/Enemies/EggPawn_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_HornetBomb:		MESHES(x) = LoadAnimMesh("Objects/Enemies/GunHornet_Bomb.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_KikiBomb:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Kiki_Bomb.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_CrabMissile:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Crab_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_SpinyMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Spiny_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_MissileBall:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Missile_Ball.b3d") : ScaleEntity MESHES(x),1.85,1.85,1.85
		Case Mesh_EnemyMissile_MissileGUN:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Missile_GUN.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_CameronMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Cameron_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_LaserMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Laser_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_OrbinautBomb:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut_Bomb.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_SlicerSlicer:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Slicer_Slicer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_SpikesSpike:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Spikes_Spike.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_AsteronPoint:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Asteron_Point.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_MantaBomb:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Manta_Bomb.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_MadmoleMushroom:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Madmole_Mushroom.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_TakerSpikeBall:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Taker_SpikeBall.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_AlienMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Alien_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_ExplosionMissile:MESHES(x) = LoadAnimMesh("Objects/Enemies/Explosion_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_FCannonMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/FCannonMissile.b3d")
		Case Mesh_EnemyMissile_BomberMissile:	MESHES(x) = LoadAnimMesh("Objects/Enemies/BomberMissile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_Drive1:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive1.b3d")
		Case Mesh_Drive2:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive2.b3d")
		Case Mesh_Drive3:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive3.b3d")
		Case Mesh_Drive4:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive4.b3d")
		Case Mesh_Drive5:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive5.b3d")
		Case Mesh_Drive6:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive6.b3d")
		Case Mesh_Drive7:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive7.b3d")
		Case Mesh_Drive8:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive8.b3d")
		Case Mesh_Drive9:			MESHES(x) = LoadAnimMesh("ChaoWorld/Drives/Drive9.b3d")
		Case Mesh_Omochao:			MESHES(x) = LoadAnimMesh("Characters/Omo.b3d")
									i=1
									ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Idle
									ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Sit
									ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Walk
									ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Run
									ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Dance
		Case Mesh_Balloon1:			MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloon1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Balloon2:			MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloon2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Balloon3:			MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloon3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Balloon4:			MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloon4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_BoxCage:			MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxCage.b3d")
		Case Mesh_BoxIron:			MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxIron.b3d")
		Case Mesh_BoxMetal:			MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxMetal.b3d")
		Case Mesh_BoxWooden:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxWooden.b3d")
		Case Mesh_BoxLightOn:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxLightOn.b3d")
		Case Mesh_BoxLightOff:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxLightOff.b3d")
		Case Mesh_BoxTnt:			MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxTnt.b3d")
		Case Mesh_BoxNitro:			MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxNitro.b3d")
		Case Mesh_BoxFloat:			MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxFloat.b3d")
		Case Mesh_BallBumperOn:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/BallOn.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_BallBumperOff:	MESHES(x) = LoadAnimMesh("Objects/Bumpers/BallOff.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_GroundBumperOn:	MESHES(x) = LoadAnimMesh("Objects/Bumpers/BumperOn.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_GroundBumperOff:	MESHES(x) = LoadAnimMesh("Objects/Bumpers/BumperOff.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_MetroBumper1:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/Metro1.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_MetroBumper2:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/Metro2.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumper1:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/Plate1.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumper2:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/Plate2.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumper3:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/Plate3.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumperX:		MESHES(x) = LoadAnimMesh("Objects/Bumpers/PlateX.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_TriangleBumper:	MESHES(x) = LoadAnimMesh("Objects/Bumpers/Triangle.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_Paddle:			MESHES(x) = LoadAnimMesh("Objects/Bumpers/Paddle.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Paddle2:			MESHES(x) = LoadAnimMesh("Objects/Bumpers/Paddle2.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Checkpoint:		MESHES(x) = LoadAnimMesh("Objects/Checkpoint/Checkpoint.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,0) ; duration
									ExtractAnimSeq(MESHES(x),1,21) ; spin
		Case Mesh_CheckpointX:		MESHES(x) = LoadAnimMesh("Objects/Checkpoint/CheckpointX.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,0) ; duration
		Case Mesh_Checkpoint2:		MESHES(x) = LoadAnimMesh("Objects/Checkpoint/Checkpoint2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,0) ; duration
									ExtractAnimSeq(MESHES(x),1,21) ; spin
		Case Mesh_Checkpoint2X:		MESHES(x) = LoadAnimMesh("Objects/Checkpoint/Checkpoint2X.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,0) ; duration
		Case Mesh_Checkpoint3:		MESHES(x) = LoadAnimMesh("Objects/Checkpoint/Checkpoint3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,0) ; duration
									ExtractAnimSeq(MESHES(x),1,21) ; spin
		Case Mesh_Checkpoint3X:		MESHES(x) = LoadAnimMesh("Objects/Checkpoint/Checkpoint3X.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,0) ; duration
		Case Mesh_DashPanel:		MESHES(x) = LoadAnimMesh("Objects/Fasteners/DashPanel.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_DashPanelPads:	MESHES(x) = LoadAnimMesh("Objects/Fasteners/DashPanelPads.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_Pads
		Case Mesh_DashRamp:			MESHES(x) = LoadAnimMesh("Objects/Fasteners/DashRamp.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_DashRampPads:		MESHES(x) = LoadAnimMesh("Objects/Fasteners/DashRampPads.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_Pads
		Case Mesh_Fan:				MESHES(x) = LoadAnimMesh("Objects/Fasteners/Fan.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,5) ; spin
		Case Mesh_DashHoop:			MESHES(x) = LoadAnimMesh("Objects/Hoops/Hoop.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_DashHoop
		Case Mesh_RainbowHoop:		MESHES(x) = LoadAnimMesh("Objects/Hoops/Hoop.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_RainbowHoop
		Case Mesh_Cloud:			MESHES(x) = LoadAnimMesh("Objects/Hoops/Cloud.b3d")
									ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_Pole:				MESHES(x) = LoadAnimMesh("Objects/Springs/Pole.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,1)
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Accelerator:		MESHES(x) = LoadAnimMesh("Objects/Fasteners/Accelerator.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_AcceleratorLight:	MESHES(x) = LoadAnimMesh("Objects/Fasteners/AcceleratorLight.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_AcceleratorLight
		Case Mesh_Invincible:		MESHES(x) = LoadAnimMesh("Objects/Monitors/Invincible.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Monitor:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Monitor.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),0,1) ;idle
									ExtractAnimSeq(MESHES(x),1,19) ;boom
		Case Mesh_MonitorBalloon:	MESHES(x) = LoadAnimMesh("Objects/Monitors/MonitorBalloon.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
									ExtractAnimSeq(MESHES(x),1,29) ;idle
									ExtractAnimSeq(MESHES(x),30,48) ;boom
		Case Mesh_Bomb:				MESHES(x) = LoadAnimMesh("Objects/Monitors/Bomb.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_BoardX:			MESHES(x) = LoadAnimMesh("Objects/Monitors/BoardX.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_GliderX:			MESHES(x) = LoadAnimMesh("Objects/Monitors/GliderX.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Wings:			MESHES(x) = LoadAnimMesh("Objects/Monitors/Wings.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Shoe:				MESHES(x) = LoadAnimMesh("Objects/Monitors/Shoe.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Trap:				MESHES(x) = LoadAnimMesh("Objects/Monitors/Trap.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_BRing5:			MESHES(x) = LoadAnimMesh("Objects/Rings/BRing5.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_BRing10:			MESHES(x) = LoadAnimMesh("Objects/Rings/BRing10.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_BRing20:			MESHES(x) = LoadAnimMesh("Objects/Rings/BRing20.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_Life:				MESHES(x) = LoadAnimMesh("Objects/Rings/Life.b3d") : ScaleEntity MESHES(x),0.9,0.9,0.9
		Case Mesh_GoalRing:			MESHES(x) = LoadAnimMesh("Objects/Rings/GoalRing.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_EmeraldGoal:		MESHES(x) = LoadAnimMesh("Objects/Emeralds/EmeraldGoal.b3d") : ScaleEntity MESHES(x),4,4,4
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_SpikeBall:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBall.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBallCube:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBallCube.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBomb:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBomb.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBar1:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBar1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
									ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SpikeBar2:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBar2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
									ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SpikeBar3:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBar3.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
									ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SpikeBarPoles1:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBarPoles1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBarPoles2:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBarPoles2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBarPoles3:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBarPoles3.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCrusher:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeCrusher.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeDrill:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeDrill.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
									ExtractAnimSeq(MESHES(x),1,1) ;close
									ExtractAnimSeq(MESHES(x),2,2) ;open
		Case Mesh_SpikeDrillCube:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeDrillCube.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_SpikeDrillCube2:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeDrillCube2.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_SpikeSwing:		MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeSwing.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeSwingChain:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeSwingChain.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeSwingBall:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeSwingBall.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCylinder:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeCylinder.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_SpikeCylinderCube:MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeCylinderCube.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_Spring:			MESHES(x) = LoadAnimMesh("Objects/Springs/Spring.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_BSpring:			MESHES(x) = LoadAnimMesh("Objects/Springs/BSpring.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SpringX:			MESHES(x) = LoadAnimMesh("Objects/Springs/SpringX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,10);spin
		Case Mesh_SpringTrap:		MESHES(x) = LoadAnimMesh("Objects/Springs/SpringTrap.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SpringTrapX:		MESHES(x) = LoadAnimMesh("Objects/Springs/SpringTrapX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,10);spin
		Case Mesh_SwitchBase:		MESHES(x) = LoadAnimMesh("Objects/Springs/SwitchBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SwitchOn:			MESHES(x) = LoadAnimMesh("Objects/Springs/SwitchOn.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,5)
									ExtractAnimSeq(MESHES(x),6,19)
		Case Mesh_SwitchOff:		MESHES(x) = LoadAnimMesh("Objects/Springs/SwitchOff.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,5)
									ExtractAnimSeq(MESHES(x),6,19)
		Case Mesh_SwitchClosedBase:	MESHES(x) = LoadAnimMesh("Objects/Springs/SwitchClosedBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SwitchClosedTop:	MESHES(x) = LoadAnimMesh("Objects/Springs/SwitchClosedTop.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SwitchAir:		MESHES(x) = LoadAnimMesh("Objects/Springs/SwitchAir.b3d") : ScaleEntity MESHES(x),1.85,1.85,1.85
									ExtractAnimSeq(MESHES(x),1,1)
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Teleporter:		MESHES(x) = LoadAnimMesh("Objects/Springs/Teleporter.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Teleporter2:		MESHES(x) = LoadAnimMesh("Objects/Springs/Teleporter2.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Teleporter3:		MESHES(x) = LoadAnimMesh("Objects/Springs/Teleporter3.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Teleporter4:		MESHES(x) = LoadAnimMesh("Objects/Springs/Teleporter4.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_TeleporterEnd:	MESHES(x) = LoadAnimMesh("Objects/Springs/TeleporterEnd.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Transporter:		MESHES(x) = LoadAnimMesh("ChaoWorld/BlackMarket/Transporter.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Platform:			MESHES(x) = LoadAnimMesh("Objects/Springs/Platform.b3d") : ScaleEntity MESHES(x),2,2,2
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Spout:			MESHES(x) = LoadAnimMesh("Objects/Traps/Spout.b3d") : ScaleEntity MESHES(x),0.75,0.75,0.75
		Case Mesh_ShockBar:			MESHES(x) = LoadAnimMesh("Objects/Traps/ShockBar.b3d") : ScaleEntity MESHES(x),0.75,0.75,0.75
		Case Mesh_LaserV:			MESHES(x) = LoadAnimMesh("Objects/Traps/LaserV.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_LaserVX:			MESHES(x) = LoadAnimMesh("Objects/Traps/LaserVX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_LaserH:			MESHES(x) = LoadAnimMesh("Objects/Traps/LaserH.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_LaserHX:			MESHES(x) = LoadAnimMesh("Objects/Traps/LaserHX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2V:			MESHES(x) = LoadAnimMesh("Objects/Traps/Laser2V.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2VX:			MESHES(x) = LoadAnimMesh("Objects/Traps/Laser2VX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2H:			MESHES(x) = LoadAnimMesh("Objects/Traps/Laser2H.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2HX:			MESHES(x) = LoadAnimMesh("Objects/Traps/Laser2HX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Cannon:			MESHES(x) = LoadAnimMesh("Objects/Springs/Cannon.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_ShieldNormalX:	MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldNormalX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldFlameX:		MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldFlameX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldBubbleX:	MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldBubbleX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldThunderX:	MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldThunderX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldEarthX:		MESHES(x) = LoadAnimMesh("Objects/Shields/ShieldEarthX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Tropical:			MESHES(x) = LoadAnimMesh("ChaoWorld\Trees\Tropical.b3d")
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_TropicalTrunk:	MESHES(x) = LoadAnimMesh("ChaoWorld\Trees\TropicalTrunk.b3d")
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Tropical2:		MESHES(x) = LoadAnimMesh("ChaoWorld\Trees\Tropical2.b3d")
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Tropical2Trunk:	MESHES(x) = LoadAnimMesh("ChaoWorld\Trees\Tropical2Trunk.b3d")
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Tropical3:		MESHES(x) = LoadAnimMesh("ChaoWorld\Trees\Tropical3.b3d")
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_TrashCan:			MESHES(x) = LoadAnimMesh("ChaoWorld/BlackMarket/TrashCan.b3d")
		Case Mesh_Sack:				MESHES(x) = LoadAnimMesh("ChaoWorld/BlackMarket/Sack.b3d")
		Case Mesh_Propeller:		MESHES(x) = LoadAnimMesh("Objects/Fasteners/Propeller.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,20)
		Case Mesh_Handle:			MESHES(x) = LoadAnimMesh("Objects/Fasteners/Handle.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Escaper:			MESHES(x) = LoadAnimMesh("Objects/Springs/Escaper.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Pulley:			MESHES(x) = LoadAnimMesh("Objects/Springs/Pulley.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_PulleyBase:		MESHES(x) = LoadAnimMesh("Objects/Springs/PulleyBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Rocket:			MESHES(x) = LoadAnimMesh("Objects/Springs/Rocket.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_RocketBase:		MESHES(x) = LoadAnimMesh("Objects/Springs/RocketBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Elevator:			MESHES(x) = LoadAnimMesh("Objects/Springs/Elevator.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Explosion:		MESHES(x) = LoadAnimMesh("Objects/Traps/Explosion.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,19)
									ExtractAnimSeq(MESHES(x),20,38)
		Case Mesh_Rock_brown:		MESHES(x) = LoadAnimMesh("Objects/Breakables/Rock_brown.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Rock_grey:		MESHES(x) = LoadAnimMesh("Objects/Breakables/Rock_grey.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Crystal_red:		MESHES(x) = LoadAnimMesh("Objects/Breakables/Crystal_red.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Crystal_green:	MESHES(x) = LoadAnimMesh("Objects/Breakables/Crystal_green.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Crystal_blue:		MESHES(x) = LoadAnimMesh("Objects/Breakables/Crystal_blue.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Icicle1:			MESHES(x) = LoadAnimMesh("Objects/Shields/Icicle1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_Icicle2:			MESHES(x) = LoadAnimMesh("Objects/Shields/Icicle2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleBig1:		MESHES(x) = LoadAnimMesh("Objects/Shields/IcicleBig1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleBig2:		MESHES(x) = LoadAnimMesh("Objects/Shields/IcicleBig2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleShard1:		MESHES(x) = LoadAnimMesh("Objects/Shields/IcicleShard1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleShard2:		MESHES(x) = LoadAnimMesh("Objects/Shields/IcicleShard2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleShard3:		MESHES(x) = LoadAnimMesh("Objects/Shields/IcicleShard3.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecor1:		MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecor1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecor2:		MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecor2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard1:	MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecorShard1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard2:	MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecorShard2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard3:	MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecorShard3.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard4:	MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecorShard4.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard5:	MESHES(x) = LoadAnimMesh("Objects/Shields/IceDecorShard5.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_Car_Sedan1:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarSedan1.b3d")
		Case Mesh_Car_Sedan2:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarSedan2.b3d")
		Case Mesh_Car_Sedan3:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarSedan3.b3d")
		Case Mesh_Car_Compact1:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarCompact1.b3d")
		Case Mesh_Car_Compact2:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarCompact2.b3d")
		Case Mesh_Car_Compact3:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarCompact3.b3d")
		Case Mesh_Car_Wagon1:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarWagon1.b3d")
		Case Mesh_Car_Wagon2:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarWagon2.b3d")
		Case Mesh_Car_Wagon3:		MESHES(x) = LoadAnimMesh("Objects/Monitors/CarWagon3.b3d")
		Case Mesh_Car_Taxi:			MESHES(x) = LoadAnimMesh("Objects/Monitors/CarTaxi.b3d")
		Case Mesh_Hint:				MESHES(x) = LoadAnimMesh("Objects/Rings/Hint.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
									ExtractAnimSeq(MESHES(x),1,9)
									ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter1:			MESHES(x) = LoadAnimMesh("Objects/Rings/Counter1.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
									ExtractAnimSeq(MESHES(x),1,9)
									ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter2:			MESHES(x) = LoadAnimMesh("Objects/Rings/Counter2.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
									ExtractAnimSeq(MESHES(x),1,9)
									ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter3:			MESHES(x) = LoadAnimMesh("Objects/Rings/Counter3.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
									ExtractAnimSeq(MESHES(x),1,9)
									ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter4:			MESHES(x) = LoadAnimMesh("Objects/Rings/Counter4.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
									ExtractAnimSeq(MESHES(x),1,9)
									ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter5:			MESHES(x) = LoadAnimMesh("Objects/Rings/Counter5.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
									ExtractAnimSeq(MESHES(x),1,9)
									ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Sign_fall:		MESHES(x) = LoadAnimMesh("Objects/Rings/Sign_fall.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_up:			MESHES(x) = LoadAnimMesh("Objects/Rings/Sign_up.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_down:		MESHES(x) = LoadAnimMesh("Objects/Rings/Sign_down.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_left:		MESHES(x) = LoadAnimMesh("Objects/Rings/Sign_left.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_right:		MESHES(x) = LoadAnimMesh("Objects/Rings/Sign_right.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Bell:				MESHES(x) = LoadAnimMesh("Objects/Rings/Bell.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Butterfly1:		MESHES(x) = LoadAnimMesh("Objects/Visuals/Butterfly1.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Butterfly2:		MESHES(x) = LoadAnimMesh("Objects/Visuals/Butterfly2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Butterfly3:		MESHES(x) = LoadAnimMesh("Objects/Visuals/Butterfly3.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Seagull:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Seagull.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Fish:				MESHES(x) = LoadAnimMesh("Objects/Visuals/Fish.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Manta:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Manta.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Turtle:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Turtle.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Orca:				MESHES(x) = LoadAnimMesh("Objects/Visuals/Orca.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,61)
		Case Mesh_Dolphin:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Dolphin.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,61)
		Case Mesh_Chair:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Chair.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Parasol:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Parasol.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_AirBalloon1:		MESHES(x) = LoadAnimMesh("Objects/Visuals/AirBalloon1.b3d")
									ExtractAnimSeq(MESHES(x),1,21)
		Case Mesh_AirBalloon2:		MESHES(x) = LoadAnimMesh("Objects/Visuals/AirBalloon2.b3d")
									ExtractAnimSeq(MESHES(x),1,21)
		Case Mesh_AirBalloon3:		MESHES(x) = LoadAnimMesh("Objects/Visuals/AirBalloon3.b3d")
									ExtractAnimSeq(MESHES(x),1,21)
		Case Mesh_Helicopter:		MESHES(x) = LoadAnimMesh("Objects/Visuals/Helicopter.b3d")
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Rainbow:			MESHES(x) = LoadAnimMesh("Objects/Visuals/Rainbow.b3d")
		Case Mesh_Flicky1:			MESHES(x) = LoadAnimMesh("Objects/Flickies/Flicky1.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Flicky2:			MESHES(x) = LoadAnimMesh("Objects/Flickies/Flicky2.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Flicky3:			MESHES(x) = LoadAnimMesh("Objects/Flickies/Flicky3.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Flicky4:			MESHES(x) = LoadAnimMesh("Objects/Flickies/Flicky4.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Capsule:			MESHES(x) = LoadAnimMesh("Objects/Springs/Capsule.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
									ExtractAnimSeq(MESHES(x),1,1) ;close
									ExtractAnimSeq(MESHES(x),2,2) ;open
		Case Mesh_WispBlack:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispBlack.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispBlue:			MESHES(x) = LoadAnimMesh("Objects/Wisps/WispBlue.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispCrimson:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispCrimson.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispCyan:			MESHES(x) = LoadAnimMesh("Objects/Wisps/WispCyan.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispForest:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispForest.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispGray:			MESHES(x) = LoadAnimMesh("Objects/Wisps/WispGray.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispGreen:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispGreen.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispIndigo:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispIndigo.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispIvory:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispIvory.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispMagenta:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispMagenta.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispOrange:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispOrange.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispPink:			MESHES(x) = LoadAnimMesh("Objects/Wisps/WispPink.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispPurple:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispPurple.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispRed:			MESHES(x) = LoadAnimMesh("Objects/Wisps/WispRed.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispViolet:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispViolet.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispWhite:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispWhite.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispYellow:		MESHES(x) = LoadAnimMesh("Objects/Wisps/WispYellow.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Balloonpiece1:	MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloonpiece1.b3d")
		Case Mesh_Balloonpiece2:	MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloonpiece2.b3d")
		Case Mesh_Balloonpiece3:	MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloonpiece3.b3d")
		Case Mesh_Balloonpiece4:	MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloonpiece4.b3d")
		Case Mesh_Balloonpiece5:	MESHES(x) = LoadAnimMesh("Objects/Balloons/Balloonpiece5.b3d")
		Case Mesh_BoxPiece1:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxPiece1.b3d")
		Case Mesh_BoxPiece2:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxPiece2.b3d")
		Case Mesh_BoxPiece3:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxPiece3.b3d")
		Case Mesh_BoxPiece4:		MESHES(x) = LoadAnimMesh("Objects/Boxes/BoxPiece4.b3d")
		Case Mesh_SpikeBombPiece1:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBombPiece1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBombPiece2:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeBombPiece2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCrusherPiece1:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeCrusherPiece1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCrusherPiece2:	MESHES(x) = LoadAnimMesh("Objects/Spikes/SpikeCrusherPiece2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_RockChunk1_brown:		MESHES(x) = LoadAnimMesh("Objects/Breakables/RockChunk1_brown.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk2_brown:		MESHES(x) = LoadAnimMesh("Objects/Breakables/RockChunk2_brown.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk3_brown:		MESHES(x) = LoadAnimMesh("Objects/Breakables/RockChunk3_brown.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk1_grey:		MESHES(x) = LoadAnimMesh("Objects/Breakables/RockChunk1_grey.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk2_grey:		MESHES(x) = LoadAnimMesh("Objects/Breakables/RockChunk2_grey.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk3_grey:		MESHES(x) = LoadAnimMesh("Objects/Breakables/RockChunk3_grey.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk1_red:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk1_red.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk2_red:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk2_red.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk3_red:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk3_red.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk1_green:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk1_green.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk2_green:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk2_green.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk3_green:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk3_green.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk1_blue:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk1_blue.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk2_blue:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk2_blue.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk3_blue:	MESHES(x) = LoadAnimMesh("Objects/Breakables/CrystalChunk3_blue.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_Tree1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Tree1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Tree2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Tree3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Tree4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5:			MESHES(x) = LoadAnimMesh("Objects/Plants/Tree5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6:			MESHES(x) = LoadAnimMesh("Objects/Plants/Tree6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree1Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree6Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub1Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub4Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub5:			MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub5Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub6:			MESHES(x) = LoadAnimMesh("Objects/Plants/Shrub6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Bush1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Bush2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Bush3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Bush4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush5:			MESHES(x) = LoadAnimMesh("Objects/Plants/Bush5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush6:			MESHES(x) = LoadAnimMesh("Objects/Plants/Bush6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass5:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass6:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass7:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass7.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass8:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass8.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass9:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass9.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass10:			MESHES(x) = LoadAnimMesh("Objects/Plants/Grass10.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree1Sakura:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree1Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2Sakura:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree2Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3Sakura:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree3Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4Sakura:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree4Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5Sakura:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree5Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6Sakura:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree6Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Palm1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Palm2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Palm3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Palm4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm1Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Palm1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm2Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Palm2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm3Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Palm3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm4Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Palm4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm1:		MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm2:		MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm3:		MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm4:		MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm5:		MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm6:		MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm1Trunk:	MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm2Trunk:	MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm3Trunk:	MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm4Trunk:	MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm5Trunk:	MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm6Trunk:	MESHES(x) = LoadAnimMesh("Objects/Plants/WildPalm6Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Flower1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Flower2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Flower3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Flower4.b3d") : ScaleEntity MESHES(x),12,12,12
									ExtractAnimSeq(MESHES(x),1,65)
		Case Mesh_Flower5:			MESHES(x) = LoadAnimMesh("Objects/Plants/Flower5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree1Snowy:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree1Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2Snowy:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree2Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3Snowy:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree3Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4Snowy:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree4Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5Snowy:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree5Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6Snowy:		MESHES(x) = LoadAnimMesh("Objects/Plants/Tree6Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Vine1:				MESHES(x) = LoadAnimMesh("Objects/Plants/Vine1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_DryTree1Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/DryTree1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_DryTree2Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/DryTree2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_DryTree3Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/DryTree3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat1:			MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat2:			MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat3:			MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat4:			MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat5:			MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat3Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat4Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat5Trunk:		MESHES(x) = LoadAnimMesh("Objects/Plants/Adabat5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Enemy_AeroCannon:			MESHES(x) = LoadAnimMesh("Objects/Enemies/AeroCannon.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_BuzzBomber:			MESHES(x) = LoadAnimMesh("Objects/Enemies/BuzzBomber.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_Buzzer:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Buzzer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_CaterkillerBody:	MESHES(x) = LoadAnimMesh("Objects/Enemies/CaterkillerBody.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CaterkillerBase:	MESHES(x) = LoadAnimMesh("Objects/Enemies/CaterkillerBase.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Caterkiller:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Caterkiller.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,1);closed
											ExtractAnimSeq(MESHES(x),2,10);idle
											ExtractAnimSeq(MESHES(x),11,15);attack
		Case Mesh_Enemy_Chopper:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Chopper.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,5);move
											ExtractAnimSeq(MESHES(x),6,6);dead
		Case Mesh_Enemy_Crabmeat:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Crabmeat.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Chaser:				MESHES(x) = LoadAnimMesh("Objects/Enemies/EggChaser.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_Fighter:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggFighter.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,14);attack
		Case Mesh_Enemy_Flapper:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggFlapper.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_FlapperGun:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggFlapperGun.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_FlapperBomb:		MESHES(x) = LoadAnimMesh("Objects/Enemies/EggFlapperBomb.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_FlapperNeedle:		MESHES(x) = LoadAnimMesh("Objects/Enemies/EggFlapperNeedle.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_PawnGun:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggPawnGun.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_PawnSword:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggPawnSword.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_PawnShield:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggPawnShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Pawn:				MESHES(x) = LoadAnimMesh("Objects/Enemies/EggPawn.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);run
											ExtractAnimSeq(MESHES(x),19,27);idle1
											ExtractAnimSeq(MESHES(x),28,36);run1
											ExtractAnimSeq(MESHES(x),37,45);idle2
											ExtractAnimSeq(MESHES(x),46,54);attack2
											ExtractAnimSeq(MESHES(x),55,63);run2
											ExtractAnimSeq(MESHES(x),64,72);idle3
											ExtractAnimSeq(MESHES(x),73,81);attack3
											ExtractAnimSeq(MESHES(x),82,90);run3
		Case Mesh_Enemy_Grabber:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Grabber.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Beetle:				MESHES(x) = LoadAnimMesh("Objects/Enemies/GunBeetle.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_BeetleMono:			MESHES(x) = LoadAnimMesh("Objects/Enemies/GunBeetleMono.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_BeetleSpark:		MESHES(x) = LoadAnimMesh("Objects/Enemies/GunBeetleSpark.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
											ExtractAnimSeq(MESHES(x),14,26);shock
		Case Mesh_Enemy_BeetleSpring:		MESHES(x) = LoadAnimMesh("Objects/Enemies/GunBeetleSpring.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_ArtificialChaos:	MESHES(x) = LoadAnimMesh("Objects/Enemies/ArtificialChaos.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_ArtificialChaos2:	MESHES(x) = LoadAnimMesh("Objects/Enemies/ArtificialChaos2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Hornet:				MESHES(x) = LoadAnimMesh("Objects/Enemies/GunHornet.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle0
											ExtractAnimSeq(MESHES(x),10,18);idle3
											ExtractAnimSeq(MESHES(x),19,27);idle6
		Case Mesh_Enemy_HunterShield:		MESHES(x) = LoadAnimMesh("Objects/Enemies/GunHunterShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Hunter:				MESHES(x) = LoadAnimMesh("Objects/Enemies/GunHunter.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
											ExtractAnimSeq(MESHES(x),19,27);fly
											ExtractAnimSeq(MESHES(x),28,36);idle2
											ExtractAnimSeq(MESHES(x),37,45);idle3
											ExtractAnimSeq(MESHES(x),46,54);fly1
		Case Mesh_Enemy_Rhino:				MESHES(x) = LoadAnimMesh("Objects/Enemies/GunRhino.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);move
		Case Mesh_Enemy_RhinoSpikes:		MESHES(x) = LoadAnimMesh("Objects/Enemies/GunRhinoSpikes.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);move
		Case Mesh_Enemy_Jaws:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Jaws.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,5);move
											ExtractAnimSeq(MESHES(x),6,6);dead
		Case Mesh_Enemy_Kiki:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Kiki.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);hold
											ExtractAnimSeq(MESHES(x),19,27);throw
		Case Mesh_Enemy_CopSpeeder:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopSpeeder.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer1:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacer1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer2:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacer2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer3:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacer3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer4:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacer4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer5:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacer5.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer6:			MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacer6.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer1Car:		MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacerCar1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer2Car:		MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacerCar2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer3Car:		MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacerCar3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer4Car:		MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacerCar4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer5Car:		MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacerCar5.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer6Car:		MESHES(x) = LoadAnimMesh("Objects/Enemies/CopRacerCar6.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Motobug:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Motobug.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);move
											ExtractAnimSeq(MESHES(x),19,27);charge
		Case Mesh_Enemy_Spana:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Spana.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_Spina:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Spina.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_Spona:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Spona.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_Spiny:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Spiny.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle1
											ExtractAnimSeq(MESHES(x),18,34);idle
		Case Mesh_Enemy_EggRobo:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggRobo.b3d")
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);attack
		Case Mesh_Enemy_Cameron:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Cameron.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Klagen:				MESHES(x) = LoadAnimMesh("Objects/Enemies/Klagen.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Orbinaut1:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut2:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut3:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut4:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut5:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut5.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut6:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Orbinaut6.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Typhoon1:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Typhoon1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,10);idle
											ExtractAnimSeq(MESHES(x),11,25);blow
		Case Mesh_Enemy_Typhoon2:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Typhoon2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,10);idle
											ExtractAnimSeq(MESHES(x),11,25);blow
		Case Mesh_Enemy_Anton:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Anton.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,10);idle
											ExtractAnimSeq(MESHES(x),11,20);move
		Case Mesh_Enemy_Aquis:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Aquis.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,10);dead
											ExtractAnimSeq(MESHES(x),11,19);shoot
		Case Mesh_Enemy_Bombie:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Bombie.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);move
		Case Mesh_Enemy_Newtron:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Newtron.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,10);idle
											ExtractAnimSeq(MESHES(x),11,19);shoot
		Case Mesh_Enemy_Penguinator:	MESHES(x) = LoadAnimMesh("Objects/Enemies/Penguinator.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);move
											ExtractAnimSeq(MESHES(x),27,35);move2
		Case Mesh_Enemy_Slicer:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Slicer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,19);attack
											ExtractAnimSeq(MESHES(x),20,28);idle2
		Case Mesh_Enemy_SnailBlaster:	MESHES(x) = LoadAnimMesh("Objects/Enemies/SnailBlaster.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Spikes:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Spikes.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);move
											ExtractAnimSeq(MESHES(x),19,27);idle1
											ExtractAnimSeq(MESHES(x),28,36);move1
		Case Mesh_Enemy_Asteron:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Asteron.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Batbot:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Batbot.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);fly
											ExtractAnimSeq(MESHES(x),18,34);idle
		Case Mesh_Enemy_Bubbles:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Bubbles.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Bubbles2:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Bubbles2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Steelion:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Steelion.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);move
											ExtractAnimSeq(MESHES(x),27,43);attack
											ExtractAnimSeq(MESHES(x),44,52);idle1
											ExtractAnimSeq(MESHES(x),53,69);move1
											ExtractAnimSeq(MESHES(x),70,86);attack1
		Case Mesh_Enemy_Boo:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Boo.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);attack
											ExtractAnimSeq(MESHES(x),19,27);attack1
		Case Mesh_Enemy_Ghost:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Ghost.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Balkiry:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Balkiry.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,10);idle
											ExtractAnimSeq(MESHES(x),11,28);fly
											ExtractAnimSeq(MESHES(x),29,38);swirl
		Case Mesh_Enemy_Burrobot:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Burrobot.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);move
											ExtractAnimSeq(MESHES(x),35,51);idle1
											ExtractAnimSeq(MESHES(x),52,52);idle2
		Case Mesh_Enemy_Crawl:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Crawl.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);move
											ExtractAnimSeq(MESHES(x),27,35);block
											ExtractAnimSeq(MESHES(x),36,44);blockduck
		Case Mesh_Enemy_Dragonfly:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Dragonfly.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);idle1
											ExtractAnimSeq(MESHES(x),27,43);idle2
		Case Mesh_Enemy_MadmoleBody:	MESHES(x) = LoadAnimMesh("Objects/Enemies/MadmoleBody.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_MadmoleBase:	MESHES(x) = LoadAnimMesh("Objects/Enemies/MadmoleBase.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Madmole:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Madmole.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
											ExtractAnimSeq(MESHES(x),19,35);throw
											ExtractAnimSeq(MESHES(x),36,36);closed
		Case Mesh_Enemy_Manta:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Manta.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_MushmeanieBody:	MESHES(x) = LoadAnimMesh("Objects/Enemies/MushmeanieBody.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_MushmeanieHat:	MESHES(x) = LoadAnimMesh("Objects/Enemies/MushmeanieHat.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Mushmeanie:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Mushmeanie.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);move
											ExtractAnimSeq(MESHES(x),18,34);move1
		Case Mesh_Enemy_Octus:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Octus.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle1
											ExtractAnimSeq(MESHES(x),18,34);move1
											ExtractAnimSeq(MESHES(x),35,43);shoot1
											ExtractAnimSeq(MESHES(x),44,60);idle
											ExtractAnimSeq(MESHES(x),61,77);move
											ExtractAnimSeq(MESHES(x),78,86);shoot
		Case Mesh_Enemy_Patabata:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Patabata.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Zoomer:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Zoomer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);attack
											ExtractAnimSeq(MESHES(x),35,51);dive
		Case Mesh_Enemy_Biter:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Biter.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);move
											ExtractAnimSeq(MESHES(x),27,35);attack
											ExtractAnimSeq(MESHES(x),36,44);attack1
		Case Mesh_Enemy_Crawler:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Crawler.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);rise
											ExtractAnimSeq(MESHES(x),27,35);attack
											ExtractAnimSeq(MESHES(x),36,36);idle1
		Case Mesh_Enemy_Taker:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Taker.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);fly
											ExtractAnimSeq(MESHES(x),19,27);attack
		Case Mesh_Enemy_E1000:			MESHES(x) = LoadAnimMesh("Objects/Enemies/E1000.b3d")
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,26);move
											ExtractAnimSeq(MESHES(x),27,35);fly
		Case Mesh_Enemy_BallHog:		MESHES(x) = LoadAnimMesh("Objects/Enemies/BallHog.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Rhinotank:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Rhinotank.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);move
											ExtractAnimSeq(MESHES(x),19,27);charge
		Case Mesh_Enemy_TechnoSqueek:	MESHES(x) = LoadAnimMesh("Objects/Enemies/TechnoSqueek.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);move
		Case Mesh_Enemy_BlackLeech:		MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackLeech.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,18);idle
		Case Mesh_Enemy_BlackOakSword:	MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackOakSword.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_BlackOak:		MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackOak.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle1
											ExtractAnimSeq(MESHES(x),18,34);idle
											ExtractAnimSeq(MESHES(x),35,51);move
											ExtractAnimSeq(MESHES(x),52,60);attack
		Case Mesh_Enemy_BlackWarriorGun1:MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackWarriorGun1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_BlackWarriorGun2:MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackWarriorGun2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_BlackWarrior:	MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackWarrior.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);move
											ExtractAnimSeq(MESHES(x),35,43);attack
											ExtractAnimSeq(MESHES(x),44,60);idle1
											ExtractAnimSeq(MESHES(x),61,77);move1
											ExtractAnimSeq(MESHES(x),78,87);attack1
											ExtractAnimSeq(MESHES(x),88,104);idle2
											ExtractAnimSeq(MESHES(x),105,121);move2
											ExtractAnimSeq(MESHES(x),122,131);attack2
		Case Mesh_Enemy_BlackWing:		MESHES(x) = LoadAnimMesh("Objects/Enemies/BlackWing.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_Soldier:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Soldier.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idlelook
											ExtractAnimSeq(MESHES(x),18,26);idle
											ExtractAnimSeq(MESHES(x),27,43);move
											ExtractAnimSeq(MESHES(x),44,52);shoot
		Case Mesh_Enemy_Soldier2:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Soldier2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idlelook
											ExtractAnimSeq(MESHES(x),18,26);idle
											ExtractAnimSeq(MESHES(x),27,43);move
											ExtractAnimSeq(MESHES(x),44,52);shoot
		Case Mesh_Enemy_CatakillerJr:	MESHES(x) = LoadAnimMesh("Objects/Enemies/CatakillerJr.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle1
		Case Mesh_Enemy_Cluckoid:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Cluckoid.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);blow
		Case Mesh_Enemy_Mantis:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Mantis.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,22);jump
											ExtractAnimSeq(MESHES(x),23,31);idle1
		Case Mesh_Enemy_Nebula:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Nebula.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
		Case Mesh_Enemy_Roller:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Roller.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,22);movestart
											ExtractAnimSeq(MESHES(x),23,31);move
		Case Mesh_Enemy_SheepFluff:		MESHES(x) = LoadAnimMesh("Objects/Enemies/SheepFluff.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Sheep:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Sheep.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);move
											ExtractAnimSeq(MESHES(x),35,51);idle1
											ExtractAnimSeq(MESHES(x),52,68);move1
		Case Mesh_Enemy_Snowy:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Snowy.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);move
											ExtractAnimSeq(MESHES(x),35,59);attack1
											ExtractAnimSeq(MESHES(x),60,84);attack2
		Case Mesh_Enemy_Splats:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Splats.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);move
		Case Mesh_Enemy_Toxomister:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Toxomister.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Sprinkler:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Sprinkler.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_DoomsEye:		MESHES(x) = LoadAnimMesh("Objects/Enemies/DoomsEye.b3d")
											ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_HammerHammer:	MESHES(x) = LoadAnimMesh("Objects/Enemies/EggHammerHammer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_HammerShield:	MESHES(x) = LoadAnimMesh("Objects/Enemies/EggHammerShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Hammer:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggHammer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);run
											ExtractAnimSeq(MESHES(x),19,27);idle1
											ExtractAnimSeq(MESHES(x),28,36);run1
											ExtractAnimSeq(MESHES(x),37,45);idle2
											ExtractAnimSeq(MESHES(x),46,54);attack2
											ExtractAnimSeq(MESHES(x),55,63);run2
		Case Mesh_Enemy_Witch1:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggWitch1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Witch2:			MESHES(x) = LoadAnimMesh("Objects/Enemies/EggWitch2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_FCannon1:		MESHES(x) = LoadAnimMesh("Objects/Enemies/FCannon1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,1);idle
											ExtractAnimSeq(MESHES(x),2,2);idle1
		Case Mesh_Enemy_FCannon2:		MESHES(x) = LoadAnimMesh("Objects/Enemies/FCannon2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,1);idle
											ExtractAnimSeq(MESHES(x),2,2);idle1
		Case Mesh_Enemy_FCannon3:		MESHES(x) = LoadAnimMesh("Objects/Enemies/FCannon3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,1);idle
											ExtractAnimSeq(MESHES(x),2,2);idle1
		Case Mesh_Enemy_Bomber1:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Bomber1.b3d")
		Case Mesh_Enemy_Bomber2:		MESHES(x) = LoadAnimMesh("Objects/Enemies/Bomber2.b3d")
		Case Mesh_Boss_EggMobile:		MESHES(x) = LoadAnimMesh("Objects/Enemies/EggMobile.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9)
											ExtractAnimSeq(MESHES(x),1,1)
		Case Mesh_Boss_EggMobileNega:	MESHES(x) = LoadAnimMesh("Objects/Enemies/EggMobileNega.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9)
											ExtractAnimSeq(MESHES(x),1,1)
		Case Mesh_Boss_Betamk2:			MESHES(x) = LoadAnimMesh("Objects/Enemies/Betamk2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idlefly
											ExtractAnimSeq(MESHES(x),10,18);block
											ExtractAnimSeq(MESHES(x),19,27);chargestart
											ExtractAnimSeq(MESHES(x),28,36);chargemove
											ExtractAnimSeq(MESHES(x),37,45);shootidle
											ExtractAnimSeq(MESHES(x),46,54);shoot
											ExtractAnimSeq(MESHES(x),55,63);energycharge
											ExtractAnimSeq(MESHES(x),64,76);energyshoot
											ExtractAnimSeq(MESHES(x),77,85);stun
		Case Mesh_Boss_MechaSonic:		MESHES(x) = LoadAnimMesh("Objects/Enemies/MechaSonic.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);fly
											ExtractAnimSeq(MESHES(x),35,43);spin
											ExtractAnimSeq(MESHES(x),44,52);attack
											ExtractAnimSeq(MESHES(x),53,57);super
											ExtractAnimSeq(MESHES(x),58,62);flysuper
		Case Mesh_Boss_MechaSonicS:		MESHES(x) = LoadAnimMesh("Objects/Enemies/MechaSonicS.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,17);idle
											ExtractAnimSeq(MESHES(x),18,34);fly
											ExtractAnimSeq(MESHES(x),35,43);spin
											ExtractAnimSeq(MESHES(x),44,52);attack
											ExtractAnimSeq(MESHES(x),53,57);super
											ExtractAnimSeq(MESHES(x),58,62);flysuper
		Case Mesh_Boss_RoboSonic:		MESHES(x) = LoadAnimMesh("Objects/Enemies/RoboSonic.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
											ExtractAnimSeq(MESHES(x),1,9);idle
											ExtractAnimSeq(MESHES(x),10,18);charge
											ExtractAnimSeq(MESHES(x),19,28);spin
											ExtractAnimSeq(MESHES(x),29,29);die
		Case Mesh_Boss_EggMobile_Shield:		MESHES(x) = LoadAnimMesh("Objects/Shields/EggMobileShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Boss_BetaRainbow:				MESHES(x) = LoadAnimMesh("Objects/Enemies/BetaRainbow.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Axes:				MESHES(x) = LoadMesh("Objects/Axes.b3d")
		Case Mesh_Breeder:			MESHES(x) = LoadAnimMesh("ChaoWorld\Cocoons\Breeder.b3d")
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Whistle:			MESHES(x) = LoadAnimMesh("ChaoWorld/BlackMarket/Whistle.b3d")
									ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Petter:			MESHES(x) = LoadAnimMesh("ChaoWorld/BlackMarket/Petter.b3d")
									ExtractAnimSeq(MESHES(x),1,9)
	End Select

	HideEntity(MESHES(x))
	MESHES_EXISTS(x)=true

End Function
