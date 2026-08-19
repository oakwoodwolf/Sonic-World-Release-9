
	Dim MESHES(MESHES_TOTAL)
	Dim MESHES_EXISTS(MESHES_TOTAL)
	Dim MESHES_MODDED(MESHES_TOTAL)
	Dim MESHES_PATH$(MESHES_TOTAL)
	
	
	
	
Function CopySmartEntity(x)
	Return CopyEntity(MESHES(SmartEntity(x))   , Game\Stage\Root)
End Function
	
Function SmartEntity(x)
	
	If FileType(Game\Stage\Properties\Path$+MESHES_PATH$(x))=1 And MESHES_MODDED(x)=False Then 
		LoadSmartEntity(x) 
		MESHES_MODDED(x)=True
	ElseIf FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+MESHES_PATH$(x))=1 And MESHES_MODDED(x)=False Then
		LoadSmartEntity(x) 
		MESHES_MODDED(x)=True
	ElseIf FileType("_Mods/"+Menu\ObjectsFolder$+"/"+MESHES_PATH$(x))=1 And MESHES_MODDED(x)=False Then
		LoadSmartEntity(x) 
		MESHES_MODDED(x)=True
	EndIf
	
	If MESHES_EXISTS(x)=False Then 
		LoadSmartEntity(x) 
		
	EndIf
	Return x
	
End Function

	Function FreeSmartEntity(x)
		If MESHES_EXISTS(x) Then
			FreeEntity MESHES(x) : MESHES(x)=0
			MESHES_EXISTS(x)=False
			
		EndIf
	End Function

Function CheckLoadSmartEntity(x)
	
	
	If FileType(Game\Stage\Properties\Path$+MESHES_PATH$(x))=1 And MESHES_MODDED(x)=False Then 
		LoadSmartEntity(x) 
		MESHES_MODDED(x)=True
	ElseIf FileType("_Mods/"+Menu\ObjectsFolder$+"/"+MESHES_PATH$(x))=1 And MESHES_MODDED(x)=False Then
		LoadSmartEntity(x) 
		MESHES_MODDED(x)=True
	EndIf
	
	If MESHES_EXISTS(x)=False Then 
		
		
		LoadSmartEntity(x) 
		
		
	EndIf 
	
End Function

Function LoadGoodEntity(x,directory$)
	
	MESHES_PATH$(x)=directory$
	
	If FileType(Game\Stage\Properties\Path$+directory$)=1 Then
		
		pathtomesh$=Game\Stage\Properties\Path$+directory$
		If x=Mesh_DashPanelPads Then 
			FreeTexture Object_Texture_Pads
			Object_Texture_Pads=LoadTexture(Game\Stage\Properties\Path$+"Objects/Fasteners/Pad.png", 1+256)
		EndIf
		If x=Mesh_DashRampPads Then 
			FreeTexture Object_Texture_Panel
			Object_Texture_Panel=LoadTexture(Game\Stage\Properties\Path$+"Objects/Fasteners/Panel.png", 1+256)
		EndIf
		
	ElseIf (FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+directory$)) Then
		
		pathtomesh$=(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+directory$)
		If x=Mesh_DashPanelPads Then 
			FreeTexture Object_Texture_Pads
			Object_Texture_Pads=LoadTexture(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/Objects/Fasteners/Pad.png", 1+256)
		EndIf
		If x=Mesh_DashRampPads Then 
			FreeTexture Object_Texture_Panel
			Object_Texture_Panel=LoadTexture(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/Objects/Fasteners/Panel.png", 1+256)
		EndIf
		
	ElseIf FileType("_Mods/"+Menu\ObjectsFolder$+"/"+directory$)=1 Then
		
		pathtomesh$="_Mods/"+Menu\ObjectsFolder$+"/"+directory$
		If x=Mesh_DashPanelPads Then 
			FreeTexture Object_Texture_Pads
			Object_Texture_Pads=LoadTexture("_Mods/"+Menu\ObjectsFolder$+"/"+"Objects/Fasteners/Pad.png", 1+256)
		EndIf
		If x=Mesh_DashRampPads Then 
			FreeTexture Object_Texture_Panel
			Object_Texture_Panel=LoadTexture("_Mods/"+Menu\ObjectsFolder$+"/"+"Objects/Fasteners/Panel.png", 1+256)
		EndIf
		
	Else
		
		pathtomesh$=directory$
		
	EndIf
	
	
	If Not(FileType(pathtomesh$)) Then RuntimeError("Missing "+pathtomesh$)
	MESHES(x) = LoadAnimMesh(pathtomesh$)	
	
	
End Function


Function LoadSmartEntity(x)
;
	Select x
		Case Mesh_Ring:				LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6 
		Case Mesh_GoalRuby:			LoadGoodEntity(x,"Objects/Rings/GoalRuby.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Death:				LoadGoodEntity(x,"Objects/Loading.b3d")
		Case Mesh_KunaiCoil:				LoadGoodEntity(x,"Objects/Bombs/KunaiCoil.b3d")
		Case Mesh_Kunai:			LoadGoodEntity(x,"Objects/Bombs/Kunai.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_CubePlatform:				LoadGoodEntity(x,"Objects/Shields/CubePlatform.b3d")
		Case Mesh_Nullify:		LoadGoodEntity(x,"Objects/Bombs/nullify.b3d")
			ExtractAnimSeq(MESHES(x),1,13)
		Case Mesh_JumpPanel:		LoadGoodEntity(x,"Objects/JumpPanel/JumpPanel.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_JumpPanel2:		LoadGoodEntity(x,"Objects/JumpPanel/JumpPanel2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Empty:			LoadGoodEntity(x,"Objects/Empty.b3d")
		Case Mesh_Quad:				MESHES(x) = LoadMesh("Objects\Quad.b3d")
		Case Mesh_BoostBarrier:		LoadGoodEntity(x,"Objects/Shields/BoostBarrier.b3d")
			ExtractAnimSeq(MESHES(x),1,5)
			RotateEntity MESHES(x),90,0,0
		Case Mesh_Spark:			LoadGoodEntity(x,"Objects/Shields/SlideSpark.b3d")
		Case Mesh_CannonShot:		LoadGoodEntity(x,"Objects/Bombs/cannonshot.b3d")
			ExtractAnimSeq(MESHES(x),1,13)
		Case Mesh_JumpBall:			LoadGoodEntity(x,"Objects/Shields/JumpBall.b3d")
		Case Mesh_Stomp:			LoadGoodEntity(x,"Objects/Shields/Stomp.b3d")
		Case Mesh_StompTrail:		LoadGoodEntity(x,"Objects/Shields/Stomp2.b3d")
		Case Mesh_Forth:			LoadGoodEntity(x,"Objects/Shields/Forth.b3d")
		Case Mesh_Razer:			LoadGoodEntity(x,"Objects/Shields/Razer.b3d")
		Case Mesh_Flamethrow:				
			LoadGoodEntity(x,"Objects/Bombs/FireBall.b3d") 
			ExtractAnimSeq(MESHES(x),1,9)
			ScaleEntity MESHES(x),2.5,2.5,3.25
		Case Mesh_RealJumpball:	LoadGoodEntity(x,"Objects/Shields/RealjumpBall.b3d")			
			
			ScaleEntity MESHES(x),0.775,0.85,0.85
		Case Mesh_OmoLap:				LoadGoodEntity(x,"Objects/Wisps/OmoLap.b3d") : ExtractAnimSeq(MESHES(x),1,54)
		Case Mesh_Cube:				LoadGoodEntity(x,"Objects/Shields/Cube.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Ice:				LoadGoodEntity(x,"Objects/Shields/Ice.b3d")
			
		Case Mesh_ShieldNormal:		LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldNormal.b3d")
		Case Mesh_ShieldFlame:		LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldFlame.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_ShieldBubble:		LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldBubble.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_ShieldThunder:	LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldThunder.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_ShieldEarth:		LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldEarth.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Token:	LoadGoodEntity(x,"Objects/Rings/Token.b3d") : ScaleEntity MESHES(x),0.9,0.9,0.9
		Case Mesh_ShieldNormalX:	LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldNormalX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_LockOn:			LoadGoodEntity(x,"Objects/Bombs/Lock-on.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldFlameX:		LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldFlameX.b3d")
		Case Mesh_ShieldBubbleX:	LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldBubbleX.b3d")
		Case Mesh_ShieldThunderX:	LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldThunderX.b3d")
		Case Mesh_ShieldEarthX:		LoadGoodEntity(x,"Objects/Shields/Monitor/ShieldEarthX.b3d")
		Case Mesh_PhantomBarrier:		LoadGoodEntity(x,"Objects/Shields/PhantomBarrier.b3d")
			ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_DemoBarrier:		LoadGoodEntity(x,"Objects/Shields/KnucklesDemo.b3d")
			ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SuperShield:		LoadGoodEntity(x,"Objects/Shields/SuperShield.b3d")
		Case Mesh_BoardX:			LoadGoodEntity(x,"Objects/Monitors/BoardX.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Board:			LoadGoodEntity(x,"Objects/Monitors/Board.b3d") : ScaleEntity MESHES(x),0.45,0.45,0.45	
			
		Case Mesh_Glider:			LoadGoodEntity(x,"Objects/Monitors/Glider.b3d") : ScaleEntity MESHES(x),0.45,0.45,0.45
		Case Mesh_Bike:				LoadGoodEntity(x,"Objects/Monitors/Bike.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Kart:				LoadGoodEntity(x,"Objects/Monitors/Kart.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Bobsleigh:		LoadGoodEntity(x,"Objects/Monitors/Bobsleigh.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Tornado1:			LoadGoodEntity(x,"Objects/Monitors/Tornado.b3d")
			ExtractAnimSeq(MESHES(x),1,9) : ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Tornado2:			LoadGoodEntity(x,"Objects/Monitors/Tornado2.b3d")
			ExtractAnimSeq(MESHES(x),1,9) : ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Cyclone:			LoadGoodEntity(x,"Objects/Monitors/Cyclone.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Timer5:				;LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			LoadGoodEntity(x,"Objects/Rings/Timer5.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
			ScaleEntity(MESHES(x),4,4,4)
		Case Mesh_Timer10:				;LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			LoadGoodEntity(x,"Objects/Rings/Timer10.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
			ScaleEntity(MESHES(x),4,4,4)
		Case Mesh_Timer20:				;LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			LoadGoodEntity(x,"Objects/Rings/Timer20.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
			ScaleEntity(MESHES(x),4,4,4)
		Case Mesh_MoonRing:				;LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			
			LoadGoodEntity(x,"Objects/Rings/MoonRing.b3d")
			ScaleEntity MESHES(x),0.75,0.75,0.75
			
		Case Mesh_Collectible;:			LoadGoodEntity(x,"Objects/Rings/Shard.b3d") : ScaleEntity MESHES(x),1.3,1.3,1.3
			
			LoadGoodEntity(x,"Objects/Rings/Collectible.b3d")
			
		Case Mesh_Scanner:			LoadGoodEntity(x,"Objects/Shields/Scanner.b3d") : ScaleEntity MESHES(x),0.3,0.3,1.75
		Case Mesh_BlackShield1:		LoadGoodEntity(x,"Objects/Shields/BlackShield1.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_BlackShield2:		LoadGoodEntity(x,"Objects/Shields/BlackShield2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Flower:			LoadGoodEntity(x,"Objects/Bombs/Flower.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_HeartBomb:		LoadGoodEntity(x,"Objects/Bombs/HeartBomb.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_NinjaStar:		LoadGoodEntity(x,"Objects/Bombs/NinjaStar.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Gum:				LoadGoodEntity(x,"Objects/Bombs/Gum.b3d") : ScaleEntity MESHES(x),1,1,1
		Case Mesh_Boomerang:		LoadGoodEntity(x,"Objects/Bombs/Boomerang.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Dart1:			LoadGoodEntity(x,"Objects/Bombs/Dart1.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Dart2:			LoadGoodEntity(x,"Objects/Bombs/Dart2.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Dart3:			LoadGoodEntity(x,"Objects/Bombs/Dart3.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Bullet:			LoadGoodEntity(x,"Objects/Bombs/Bullet.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Beam:				LoadGoodEntity(x,"Objects/Bombs/Beam.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_GrindSpark:			LoadGoodEntity(x,"Objects/GrindSpark.b3d")
		Case Mesh_Minion:			LoadGoodEntity(x,"Objects/Bombs/Minion.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_DummyRing:		LoadGoodEntity(x,"Objects/Rings/DummyRing.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6 : PositionMesh MESHES(x),0,1.557,0
		Case Mesh_ExplosiveBomb:	LoadGoodEntity(x,"Objects/Bombs/ExplosiveBomb.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Blade:			LoadGoodEntity(x,"Objects/Bombs/Blade.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_WaterBlob:		LoadGoodEntity(x,"Objects/Bombs/WaterBlob.b3d") : ScaleEntity MESHES(x),0.246,0.246,0.246
			ExtractAnimSeq(MESHES(x),1,11)
		Case Mesh_Note1:			LoadGoodEntity(x,"Objects/Bombs/Note1.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Note2:			LoadGoodEntity(x,"Objects/Bombs/Note2.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Note3:			LoadGoodEntity(x,"Objects/Bombs/Note3.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Wind:				LoadGoodEntity(x,"Objects/Bombs/Wind.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Knife:			LoadGoodEntity(x,"Objects/Bombs/Knife.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Hurricane:		LoadGoodEntity(x,"Objects/Bombs/Hurricane.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Tire:				LoadGoodEntity(x,"Objects/Bombs/Tire.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Leaf:				LoadGoodEntity(x,"Objects/Bombs/Leaf.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Gear:				LoadGoodEntity(x,"Objects/Bombs/Gear.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Hookshot:			LoadGoodEntity(x,"Objects/Bombs/Hookshot.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Emerald:			LoadGoodEntity(x,"Objects/Emeralds/Emerald.b3d")
		Case Mesh_Sol:				LoadGoodEntity(x,"Objects/Emeralds/Sol.b3d")
		Case Mesh_EnemyPiece1:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece1.b3d")
		Case Mesh_EnemyPiece2:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece2.b3d")
		Case Mesh_EnemyPiece3:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece3.b3d")
		Case Mesh_EnemyPiece4:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece4.b3d")
		Case Mesh_EnemyPiece5:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece5.b3d")
		Case Mesh_EnemyPiece6:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece6.b3d")
		Case Mesh_EnemyPiece7:		MESHES(x) = LoadMesh("Objects/Screws/EnemyPiece7.b3d")
		Case Mesh_Point:			LoadGoodEntity(x,"Objects/Point.b3d")
		Case Mesh_Line:				LoadGoodEntity(x,"Objects/Line.b3d")
		Case Mesh_Bubble:			LoadGoodEntity(x,"Objects/Bubble.b3d")
		Case Mesh_Locker:			LoadGoodEntity(x,"Objects/Locker.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Locker2:			LoadGoodEntity(x,"Objects/Locker2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			
		Case Mesh_MissionCard:				LoadGoodEntity(x,"Objects/Rings/MissionCard.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
		Case Mesh_Diamond:				LoadGoodEntity(x,"Objects/Rings/Diamond.b3d")
		Case Mesh_WarpRing:				LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),3,5,3.5,3.5
		Case Mesh_SpewRing:			LoadGoodEntity(x,"Objects/Rings/Ring.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6 : PositionMesh MESHES(x),0,1.557,0
		Case Mesh_Shard:			LoadGoodEntity(x,"Objects/Rings/Shard.b3d") : ScaleEntity MESHES(x),1.3,1.3,1.3
		Case Mesh_SpewShard:		LoadGoodEntity(x,"Objects/Rings/Shard.b3d") : ScaleEntity MESHES(x),1.3,1.3,1.3: PositionMesh MESHES(x),0,1.557*2,0
		Case Mesh_EnemyMissile_BuzzMissile1:	LoadGoodEntity(x,"Objects/Enemies/Buzz_Missile1.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_BuzzMissile2:	LoadGoodEntity(x,"Objects/Enemies/Buzz_Missile2.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_ChaserMissile:	LoadGoodEntity(x,"Objects/Enemies/EggChaser_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile1:		LoadGoodEntity(x,"Objects/Enemies/Egg_Missile1.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile2:		LoadGoodEntity(x,"Objects/Enemies/Egg_Missile2.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile3:		LoadGoodEntity(x,"Objects/Enemies/Egg_Missile3.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_EggMissile4:		LoadGoodEntity(x,"Objects/Enemies/Egg_Missile4.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_FlapperBomb:		LoadGoodEntity(x,"Objects/Enemies/EggFlapper_Bomb.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_PawnMissile:		LoadGoodEntity(x,"Objects/Enemies/EggPawn_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_HornetBomb:		LoadGoodEntity(x,"Objects/Enemies/GunHornet_Bomb.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_KikiBomb:		LoadGoodEntity(x,"Objects/Enemies/Kiki_Bomb.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_CrabMissile:		LoadGoodEntity(x,"Objects/Enemies/Crab_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_SpinyMissile:	LoadGoodEntity(x,"Objects/Enemies/Spiny_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_MissileBall:		LoadGoodEntity(x,"Objects/Enemies/Missile_Ball.b3d") : ScaleEntity MESHES(x),1.85,1.85,1.85
		Case Mesh_EnemyMissile_MissileGUN:		LoadGoodEntity(x,"Objects/Enemies/Missile_GUN.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_CameronMissile:	LoadGoodEntity(x,"Objects/Enemies/Cameron_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_LaserMissile:	LoadGoodEntity(x,"Objects/Enemies/Laser_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_OrbinautBomb:	LoadGoodEntity(x,"Objects/Enemies/Orbinaut_Bomb.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_SlicerSlicer:	LoadGoodEntity(x,"Objects/Enemies/Slicer_Slicer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_SpikesSpike:		LoadGoodEntity(x,"Objects/Enemies/Spikes_Spike.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_AsteronPoint:	LoadGoodEntity(x,"Objects/Enemies/Asteron_Point.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_MantaBomb:		LoadGoodEntity(x,"Objects/Enemies/Manta_Bomb.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_MadmoleMushroom:	LoadGoodEntity(x,"Objects/Enemies/Madmole_Mushroom.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_TakerSpikeBall:	LoadGoodEntity(x,"Objects/Enemies/Taker_SpikeBall.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_EnemyMissile_AlienMissile:	LoadGoodEntity(x,"Objects/Enemies/Alien_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_ExplosionMissile:LoadGoodEntity(x,"Objects/Enemies/Explosion_Missile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_EnemyMissile_FCannonMissile:	LoadGoodEntity(x,"Objects/Enemies/FCannonMissile.b3d")
		Case Mesh_EnemyMissile_BomberMissile:	LoadGoodEntity(x,"Objects/Enemies/BomberMissile.b3d") : ScaleEntity MESHES(x),1.4,1.4,1.4
		Case Mesh_Drive1:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive1.b3d")
		Case Mesh_Drive2:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive2.b3d")
		Case Mesh_Drive3:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive3.b3d")
		Case Mesh_Drive4:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive4.b3d")
		Case Mesh_Drive5:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive5.b3d")
		Case Mesh_Drive6:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive6.b3d")
		Case Mesh_Drive7:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive7.b3d")
		Case Mesh_Drive8:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive8.b3d")
		Case Mesh_Drive9:			LoadGoodEntity(x,"ChaoWorld/Drives/Drive9.b3d")
		Case Mesh_Omochao:			LoadGoodEntity(x,"Characters/Omo.b3d")
			i=1
			ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Idle
			ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Sit
			ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Walk
			ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Run
			ExtractAnimSeq(MESHES(x), i, -1+i+17) : i=i+17	;Dance
			
		Case Mesh_Balloon1:			LoadGoodEntity(x,"Objects/Balloons/Balloon1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Balloon2:			LoadGoodEntity(x,"Objects/Balloons/Balloon2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Balloon3:			LoadGoodEntity(x,"Objects/Balloons/Balloon3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Balloon4:			LoadGoodEntity(x,"Objects/Balloons/Balloon4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_BoxCage:			LoadGoodEntity(x,"Objects/Boxes/BoxCage.b3d")
		Case Mesh_BoxIron:			LoadGoodEntity(x,"Objects/Boxes/BoxIron.b3d")
		Case Mesh_BoxMetal:			LoadGoodEntity(x,"Objects/Boxes/BoxMetal.b3d")
		Case Mesh_BoxWooden:		LoadGoodEntity(x,"Objects/Boxes/BoxWooden.b3d")
		Case Mesh_BoxLightOn:		LoadGoodEntity(x,"Objects/Boxes/BoxLightOn.b3d")
		Case Mesh_BoxLightOff:		LoadGoodEntity(x,"Objects/Boxes/BoxLightOff.b3d")
		Case Mesh_BoxTnt:			LoadGoodEntity(x,"Objects/Boxes/BoxTnt.b3d")
		Case Mesh_BoxNitro:			LoadGoodEntity(x,"Objects/Boxes/BoxNitro.b3d")
		Case Mesh_BoxFloat:			LoadGoodEntity(x,"Objects/Boxes/BoxFloat.b3d")
			
		Case Mesh_BallBumperOn:		LoadGoodEntity(x,"Objects/Bumpers/BallOn.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_BallBumperOff:	LoadGoodEntity(x,"Objects/Bumpers/BallOff.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_GroundBumperOn:	LoadGoodEntity(x,"Objects/Bumpers/BumperOn.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_GroundBumperOff:	LoadGoodEntity(x,"Objects/Bumpers/BumperOff.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_MetroBumper1:		LoadGoodEntity(x,"Objects/Bumpers/Metro1.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_MetroBumper2:		LoadGoodEntity(x,"Objects/Bumpers/Metro2.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumper1:		LoadGoodEntity(x,"Objects/Bumpers/Plate1.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumper2:		LoadGoodEntity(x,"Objects/Bumpers/Plate2.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumper3:		LoadGoodEntity(x,"Objects/Bumpers/Plate3.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_PlateBumperX:		LoadGoodEntity(x,"Objects/Bumpers/PlateX.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_TriangleBumper:	LoadGoodEntity(x,"Objects/Bumpers/Triangle.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_Paddle:			LoadGoodEntity(x,"Objects/Bumpers/Paddle.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Paddle2:			LoadGoodEntity(x,"Objects/Bumpers/Paddle2.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Checkpoint:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,0) ; duration
			ExtractAnimSeq(MESHES(x),1,21) ; spin
		Case Mesh_CheckpointX:		LoadGoodEntity(x,"Objects/Checkpoint/CheckpointX.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,0) ; duration
		Case Mesh_Checkpoint2:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,0) ; duration
			ExtractAnimSeq(MESHES(x),1,21) ; spin
		Case Mesh_Checkpoint2X:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint2X.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,0) ; duration
		Case Mesh_Checkpoint3:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,0) ; duration
			ExtractAnimSeq(MESHES(x),1,21) ; spin
		Case Mesh_Checkpoint3X:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint3X.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,0) ; duration
		Case Mesh_CheckpointB:		LoadGoodEntity(x,"Objects/Checkpoint/CheckpointB.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Checkpoint2B:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint2B.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Checkpoint3B:		LoadGoodEntity(x,"Objects/Checkpoint/Checkpoint3B.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1							
			
		Case Mesh_DashPanel:		LoadGoodEntity(x,"Objects/Fasteners/DashPanel.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_DashPanelPads:	LoadGoodEntity(x,"Objects/Fasteners/DashPanelPads.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_Pads
		Case Mesh_DashRamp:			LoadGoodEntity(x,"Objects/Fasteners/DashRamp.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_DashRampPads:		LoadGoodEntity(x,"Objects/Fasteners/DashRampPads.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_Panel
			
		Case Mesh_GrindBooster:		LoadGoodEntity(x,"Objects/Fasteners/GrindBooster.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_GrindBoosterPads:	LoadGoodEntity(x,"Objects/Fasteners/GrindBoosterPads.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5: EntityTexture MESHES(x),Object_Texture_RailPads
			
			
			
		Case Mesh_TrickRamp:			LoadGoodEntity(x,"Objects/Fasteners/TrickRamp.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_TrickRampPads:		LoadGoodEntity(x,"Objects/Fasteners/TrickRampPads.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_TrickPanel
		Case Mesh_Fan:				LoadGoodEntity(x,"Objects/Fasteners/Fan.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,5) ; spin
		Case Mesh_DashHoop:			LoadGoodEntity(x,"Objects/Hoops/Hoop.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_DashHoop
		Case Mesh_RainbowHoop:		LoadGoodEntity(x,"Objects/Hoops/Hoop.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_RainbowHoop
		Case Mesh_Cloud:			LoadGoodEntity(x,"Objects/Hoops/Cloud.b3d")
			ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_Pole:				LoadGoodEntity(x,"Objects/Springs/Pole.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,1)
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Accelerator:		LoadGoodEntity(x,"Objects/Fasteners/Accelerator.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_AcceleratorLight:	LoadGoodEntity(x,"Objects/Fasteners/AcceleratorLight.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5 : EntityTexture MESHES(x),Object_Texture_AcceleratorLight
		Case Mesh_Invincible:		LoadGoodEntity(x,"Objects/Monitors/Invincible.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Monitor:			LoadGoodEntity(x,"Objects/Monitors/Monitor.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),0,1) ;idle
			ExtractAnimSeq(MESHES(x),1,19) ;boom
		Case Mesh_MonitorBalloon:	LoadGoodEntity(x,"Objects/Monitors/MonitorBalloon.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,29) ;idle
			ExtractAnimSeq(MESHES(x),30,48) ;boom
		Case Mesh_Bomb:				LoadGoodEntity(x,"Objects/Monitors/Bomb.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
			
		Case Mesh_GliderX:			LoadGoodEntity(x,"Objects/Monitors/GliderX.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Wings:			LoadGoodEntity(x,"Objects/Monitors/Wings.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Shoe:				LoadGoodEntity(x,"Objects/Monitors/Shoe.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
		Case Mesh_Trap:				LoadGoodEntity(x,"Objects/Monitors/Trap.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			
		Case Mesh_BRing5:			LoadGoodEntity(x,"Objects/Rings/BRing5.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_BRing10:			LoadGoodEntity(x,"Objects/Rings/BRing10.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
		Case Mesh_BRing20:			LoadGoodEntity(x,"Objects/Rings/BRing20.b3d") : ScaleEntity MESHES(x),0.5,0.5,0.5
			
		Case Mesh_BRing5S:			LoadGoodEntity(x,"Objects/Rings/BRing5S.b3d") : ScaleEntity MESHES(x),0.65,0.65,0.65
		Case Mesh_BRing10S:			LoadGoodEntity(x,"Objects/Rings/BRing10S.b3d") : ScaleEntity MESHES(x),0.65,0.65,0.65
		Case Mesh_BRing20S:			LoadGoodEntity(x,"Objects/Rings/BRing20S.b3d") : ScaleEntity MESHES(x),0.65,0.65,0.65
			
		Case Mesh_RedRing:			LoadGoodEntity(x,"Objects/Rings/RedRing.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_RedRingStar:			LoadGoodEntity(x,"Objects/Rings/RedRingStar.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			
		Case Mesh_Life:				LoadGoodEntity(x,"Objects/Rings/Life.b3d") : ScaleEntity MESHES(x),0.9,0.9,0.9
		Case Mesh_GoalRing:			LoadGoodEntity(x,"Objects/Rings/GoalRing.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_EmeraldGoal:		LoadGoodEntity(x,"Objects/Emeralds/EmeraldGoal.b3d") : ScaleEntity MESHES(x),4,4,4
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_SpikeBall:		LoadGoodEntity(x,"Objects/Spikes/SpikeBall.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBallCube:	LoadGoodEntity(x,"Objects/Spikes/SpikeBallCube.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBomb:		LoadGoodEntity(x,"Objects/Spikes/SpikeBomb.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBar1:		LoadGoodEntity(x,"Objects/Spikes/SpikeBar1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
			ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SpikeBar2:		LoadGoodEntity(x,"Objects/Spikes/SpikeBar2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
			ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SpikeBar3:		LoadGoodEntity(x,"Objects/Spikes/SpikeBar3.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
			ExtractAnimSeq(MESHES(x),1,5)
		Case Mesh_SpikeBarPoles1:	LoadGoodEntity(x,"Objects/Spikes/SpikeBarPoles1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBarPoles2:	LoadGoodEntity(x,"Objects/Spikes/SpikeBarPoles2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBarPoles3:	LoadGoodEntity(x,"Objects/Spikes/SpikeBarPoles3.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCrusher:		LoadGoodEntity(x,"Objects/Spikes/SpikeCrusher.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeDrill:		LoadGoodEntity(x,"Objects/Spikes/SpikeDrill.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
			ExtractAnimSeq(MESHES(x),1,1) ;close
			ExtractAnimSeq(MESHES(x),2,2) ;open
		Case Mesh_SpikeDrillCube:	LoadGoodEntity(x,"Objects/Spikes/SpikeDrillCube.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_SpikeDrillCube2:	LoadGoodEntity(x,"Objects/Spikes/SpikeDrillCube2.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_SpikeSwing:		LoadGoodEntity(x,"Objects/Spikes/SpikeSwing.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeSwingChain:	LoadGoodEntity(x,"Objects/Spikes/SpikeSwingChain.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeSwingBall:	LoadGoodEntity(x,"Objects/Spikes/SpikeSwingBall.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCylinder:	LoadGoodEntity(x,"Objects/Spikes/SpikeCylinder.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_SpikeCylinderCube:LoadGoodEntity(x,"Objects/Spikes/SpikeCylinderCube.b3d") : ScaleEntity MESHES(x),0.725,0.725,0.725
		Case Mesh_Spring:			LoadGoodEntity(x,"Objects/Springs/Spring.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_BSpring:			LoadGoodEntity(x,"Objects/Springs/BSpring.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SpringX:			LoadGoodEntity(x,"Objects/Springs/SpringX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,10);spin
		Case Mesh_SpringTrap:		LoadGoodEntity(x,"Objects/Springs/SpringTrap.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SpringTrapX:		LoadGoodEntity(x,"Objects/Springs/SpringTrapX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,10);spin
		Case Mesh_SwitchBase:		LoadGoodEntity(x,"Objects/Springs/SwitchBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SwitchOn:			LoadGoodEntity(x,"Objects/Springs/SwitchOn.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,5)
			ExtractAnimSeq(MESHES(x),6,19)
		Case Mesh_SwitchOff:		LoadGoodEntity(x,"Objects/Springs/SwitchOff.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,5)
			ExtractAnimSeq(MESHES(x),6,19)
		Case Mesh_SwitchClosedBase:	LoadGoodEntity(x,"Objects/Springs/SwitchClosedBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SwitchClosedTop:	LoadGoodEntity(x,"Objects/Springs/SwitchClosedTop.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_SwitchAir:		LoadGoodEntity(x,"Objects/Springs/SwitchAir.b3d") : ScaleEntity MESHES(x),1.85,1.85,1.85
			ExtractAnimSeq(MESHES(x),1,1)
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Teleporter:		LoadGoodEntity(x,"Objects/Springs/Teleporter.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Teleporter2:		LoadGoodEntity(x,"Objects/Springs/Teleporter2.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Teleporter3:		LoadGoodEntity(x,"Objects/Springs/Teleporter3.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Teleporter4:		LoadGoodEntity(x,"Objects/Springs/Teleporter4.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_TeleporterEnd:	LoadGoodEntity(x,"Objects/Springs/TeleporterEnd.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Transporter:		LoadGoodEntity(x,"ChaoWorld/BlackMarket/Transporter.b3d") : ScaleEntity MESHES(x),3,3,3
		Case Mesh_Platform:			LoadGoodEntity(x,"Objects/Springs/Platform.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Spout:			LoadGoodEntity(x,"Objects/Traps/Spout.b3d") : ScaleEntity MESHES(x),0.75,0.75,0.75
		Case Mesh_ShockBar:			LoadGoodEntity(x,"Objects/Traps/ShockBar.b3d") : ScaleEntity MESHES(x),0.75,0.75,0.75
		Case Mesh_LaserV:			LoadGoodEntity(x,"Objects/Traps/LaserV.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_LaserVX:			LoadGoodEntity(x,"Objects/Traps/LaserVX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_LaserH:			LoadGoodEntity(x,"Objects/Traps/LaserH.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_LaserHX:			LoadGoodEntity(x,"Objects/Traps/LaserHX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2V:			LoadGoodEntity(x,"Objects/Traps/Laser2V.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2VX:			LoadGoodEntity(x,"Objects/Traps/Laser2VX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2H:			LoadGoodEntity(x,"Objects/Traps/Laser2H.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Laser2HX:			LoadGoodEntity(x,"Objects/Traps/Laser2HX.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Cannon:			LoadGoodEntity(x,"Objects/Springs/Cannon.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_ShieldNormalX:	LoadGoodEntity(x,"Objects/Shields/ShieldNormalX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldFlameX:		LoadGoodEntity(x,"Objects/Shields/ShieldFlameX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldBubbleX:	LoadGoodEntity(x,"Objects/Shields/ShieldBubbleX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldThunderX:	LoadGoodEntity(x,"Objects/Shields/ShieldThunderX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_ShieldEarthX:		LoadGoodEntity(x,"Objects/Shields/ShieldEarthX.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Tropical:			LoadGoodEntity(x,"ChaoWorld\Trees\Tropical.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_TropicalTrunk:	LoadGoodEntity(x,"ChaoWorld\Trees\TropicalTrunk.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Tropical2:		LoadGoodEntity(x,"ChaoWorld\Trees\Tropical2.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Tropical2Trunk:	LoadGoodEntity(x,"ChaoWorld\Trees\Tropical2Trunk.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Tropical3:		LoadGoodEntity(x,"ChaoWorld\Trees\Tropical3.b3d")
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_TrashCan:			LoadGoodEntity(x,"ChaoWorld/BlackMarket/TrashCan.b3d")
		Case Mesh_Sack:				LoadGoodEntity(x,"ChaoWorld/BlackMarket/Sack.b3d")
		Case Mesh_Propeller:		LoadGoodEntity(x,"Objects/Fasteners/Propeller.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,20)
		Case Mesh_Handle:			LoadGoodEntity(x,"Objects/Fasteners/Handle.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Escaper:			LoadGoodEntity(x,"Objects/Springs/Escaper.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Pulley:			LoadGoodEntity(x,"Objects/Springs/Pulley.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_PulleyBase:		LoadGoodEntity(x,"Objects/Springs/PulleyBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Rocket:			LoadGoodEntity(x,"Objects/Springs/Rocket.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_RocketBase:		LoadGoodEntity(x,"Objects/Springs/RocketBase.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Elevator:			LoadGoodEntity(x,"Objects/Springs/Elevator.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Explosion:		LoadGoodEntity(x,"Objects/Traps/Explosion.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,19)
			ExtractAnimSeq(MESHES(x),20,38)
		Case Mesh_Rock_brown:		LoadGoodEntity(x,"Objects/Breakables/Rock_brown.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Rock_grey:		LoadGoodEntity(x,"Objects/Breakables/Rock_grey.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Crystal_red:		LoadGoodEntity(x,"Objects/Breakables/Crystal_red.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Crystal_green:	LoadGoodEntity(x,"Objects/Breakables/Crystal_green.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Crystal_blue:		LoadGoodEntity(x,"Objects/Breakables/Crystal_blue.b3d") : ScaleEntity MESHES(x),2.5,2.5,2.5
		Case Mesh_Icicle1:			LoadGoodEntity(x,"Objects/Shields/Icicle1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_Icicle2:			LoadGoodEntity(x,"Objects/Shields/Icicle2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleBig1:		LoadGoodEntity(x,"Objects/Shields/IcicleBig1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleBig2:		LoadGoodEntity(x,"Objects/Shields/IcicleBig2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleShard1:		LoadGoodEntity(x,"Objects/Shields/IcicleShard1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleShard2:		LoadGoodEntity(x,"Objects/Shields/IcicleShard2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IcicleShard3:		LoadGoodEntity(x,"Objects/Shields/IcicleShard3.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecor1:		LoadGoodEntity(x,"Objects/Shields/IceDecor1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecor2:		LoadGoodEntity(x,"Objects/Shields/IceDecor2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard1:	LoadGoodEntity(x,"Objects/Shields/IceDecorShard1.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard2:	LoadGoodEntity(x,"Objects/Shields/IceDecorShard2.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard3:	LoadGoodEntity(x,"Objects/Shields/IceDecorShard3.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard4:	LoadGoodEntity(x,"Objects/Shields/IceDecorShard4.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_IceDecorShard5:	LoadGoodEntity(x,"Objects/Shields/IceDecorShard5.b3d") : ScaleEntity MESHES(x),4,4,4
		Case Mesh_Car_Sedan1:		LoadGoodEntity(x,"Objects/Monitors/CarSedan1.b3d")
		Case Mesh_Car_Sedan2:		LoadGoodEntity(x,"Objects/Monitors/CarSedan2.b3d")
		Case Mesh_Car_Sedan3:		LoadGoodEntity(x,"Objects/Monitors/CarSedan3.b3d")
		Case Mesh_Car_Compact1:		LoadGoodEntity(x,"Objects/Monitors/CarCompact1.b3d")
		Case Mesh_Car_Compact2:		LoadGoodEntity(x,"Objects/Monitors/CarCompact2.b3d")
		Case Mesh_Car_Compact3:		LoadGoodEntity(x,"Objects/Monitors/CarCompact3.b3d")
		Case Mesh_Car_Wagon1:		LoadGoodEntity(x,"Objects/Monitors/CarWagon1.b3d")
		Case Mesh_Car_Wagon2:		LoadGoodEntity(x,"Objects/Monitors/CarWagon2.b3d")
		Case Mesh_Car_Wagon3:		LoadGoodEntity(x,"Objects/Monitors/CarWagon3.b3d")
		Case Mesh_Car_Taxi:			LoadGoodEntity(x,"Objects/Monitors/CarTaxi.b3d")
		Case Mesh_Hint:				LoadGoodEntity(x,"Objects/Rings/Hint.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter1:			LoadGoodEntity(x,"Objects/Rings/Counter1.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter2:			LoadGoodEntity(x,"Objects/Rings/Counter2.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter3:			LoadGoodEntity(x,"Objects/Rings/Counter3.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter4:			LoadGoodEntity(x,"Objects/Rings/Counter4.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Counter5:			LoadGoodEntity(x,"Objects/Rings/Counter5.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),10,18)
		Case Mesh_Sign_fall:		LoadGoodEntity(x,"Objects/Rings/Sign_fall.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_up:			LoadGoodEntity(x,"Objects/Rings/Sign_up.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_down:		LoadGoodEntity(x,"Objects/Rings/Sign_down.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_left:		LoadGoodEntity(x,"Objects/Rings/Sign_left.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_right:		LoadGoodEntity(x,"Objects/Rings/Sign_right.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_speed:		LoadGoodEntity(x,"Objects/Rings/Sign_speed.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_power:		LoadGoodEntity(x,"Objects/Rings/Sign_power.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Sign_fly:		LoadGoodEntity(x,"Objects/Rings/Sign_fly.b3d") : ScaleEntity MESHES(x),0.3,0.3,0.3
		Case Mesh_Bell:				LoadGoodEntity(x,"Objects/Rings/Bell.b3d") : ScaleEntity MESHES(x),0.6,0.6,0.6
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Butterfly1:		LoadGoodEntity(x,"Objects/Visuals/Butterfly1.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Butterfly2:		LoadGoodEntity(x,"Objects/Visuals/Butterfly2.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Butterfly3:		LoadGoodEntity(x,"Objects/Visuals/Butterfly3.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Seagull:			LoadGoodEntity(x,"Objects/Visuals/Seagull.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Fish:				LoadGoodEntity(x,"Objects/Visuals/Fish.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Manta:			LoadGoodEntity(x,"Objects/Visuals/Manta.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Turtle:			LoadGoodEntity(x,"Objects/Visuals/Turtle.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,17)
		Case Mesh_Orca:				LoadGoodEntity(x,"Objects/Visuals/Orca.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,61)
		Case Mesh_Dolphin:			LoadGoodEntity(x,"Objects/Visuals/Dolphin.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,61)
		Case Mesh_Chair:			LoadGoodEntity(x,"Objects/Visuals/Chair.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_Parasol:			LoadGoodEntity(x,"Objects/Visuals/Parasol.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
		Case Mesh_AirBalloon1:		LoadGoodEntity(x,"Objects/Visuals/AirBalloon1.b3d")
			ExtractAnimSeq(MESHES(x),1,21)
		Case Mesh_AirBalloon2:		LoadGoodEntity(x,"Objects/Visuals/AirBalloon2.b3d")
			ExtractAnimSeq(MESHES(x),1,21)
		Case Mesh_AirBalloon3:		LoadGoodEntity(x,"Objects/Visuals/AirBalloon3.b3d")
			ExtractAnimSeq(MESHES(x),1,21)
		Case Mesh_Helicopter:		LoadGoodEntity(x,"Objects/Visuals/Helicopter.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Rainbow:			LoadGoodEntity(x,"Objects/Visuals/Rainbow.b3d")
		Case Mesh_Flicky1:			LoadGoodEntity(x,"Objects/Flickies/Flicky1.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Flicky2:			LoadGoodEntity(x,"Objects/Flickies/Flicky2.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Flicky3:			LoadGoodEntity(x,"Objects/Flickies/Flicky3.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Flicky4:			LoadGoodEntity(x,"Objects/Flickies/Flicky4.b3d") : ScaleEntity MESHES(x),0.8,0.8,0.8 : ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Capsule:			LoadGoodEntity(x,"Objects/Springs/Capsule.b3d") : ScaleEntity MESHES(x),1.5,1.5,1.5
			ExtractAnimSeq(MESHES(x),1,1) ;close
			ExtractAnimSeq(MESHES(x),2,2) ;open
		Case Mesh_WispBlack:		LoadGoodEntity(x,"Objects/Wisps/WispBlack.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispBlue:			LoadGoodEntity(x,"Objects/Wisps/WispBlue.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispCrimson:		LoadGoodEntity(x,"Objects/Wisps/WispCrimson.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispCyan:			LoadGoodEntity(x,"Objects/Wisps/WispCyan.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispForest:		LoadGoodEntity(x,"Objects/Wisps/WispForest.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispGray:			LoadGoodEntity(x,"Objects/Wisps/WispGray.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispGreen:		LoadGoodEntity(x,"Objects/Wisps/WispGreen.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispIndigo:		LoadGoodEntity(x,"Objects/Wisps/WispIndigo.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispIvory:		LoadGoodEntity(x,"Objects/Wisps/WispIvory.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispMagenta:		LoadGoodEntity(x,"Objects/Wisps/WispMagenta.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispOrange:		LoadGoodEntity(x,"Objects/Wisps/WispOrange.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispPink:			LoadGoodEntity(x,"Objects/Wisps/WispPink.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispPurple:		LoadGoodEntity(x,"Objects/Wisps/WispPurple.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispRed:			LoadGoodEntity(x,"Objects/Wisps/WispRed.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispViolet:		LoadGoodEntity(x,"Objects/Wisps/WispViolet.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispWhite:		LoadGoodEntity(x,"Objects/Wisps/WispWhite.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_WispYellow:		LoadGoodEntity(x,"Objects/Wisps/WispYellow.b3d") : ScaleEntity MESHES(x),3.5,3.5,3.5
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Balloonpiece1:	LoadGoodEntity(x,"Objects/Balloons/Balloonpiece1.b3d")
		Case Mesh_Balloonpiece2:	LoadGoodEntity(x,"Objects/Balloons/Balloonpiece2.b3d")
		Case Mesh_Balloonpiece3:	LoadGoodEntity(x,"Objects/Balloons/Balloonpiece3.b3d")
		Case Mesh_Balloonpiece4:	LoadGoodEntity(x,"Objects/Balloons/Balloonpiece4.b3d")
		Case Mesh_Balloonpiece5:	LoadGoodEntity(x,"Objects/Balloons/Balloonpiece5.b3d")
		Case Mesh_BoxPiece1:		LoadGoodEntity(x,"Objects/Boxes/BoxPiece1.b3d")
		Case Mesh_BoxPiece2:		LoadGoodEntity(x,"Objects/Boxes/BoxPiece2.b3d")
		Case Mesh_BoxPiece3:		LoadGoodEntity(x,"Objects/Boxes/BoxPiece3.b3d")
		Case Mesh_BoxPiece4:		LoadGoodEntity(x,"Objects/Boxes/BoxPiece4.b3d")
		Case Mesh_SpikeBombPiece1:	LoadGoodEntity(x,"Objects/Spikes/SpikeBombPiece1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeBombPiece2:	LoadGoodEntity(x,"Objects/Spikes/SpikeBombPiece2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCrusherPiece1:	LoadGoodEntity(x,"Objects/Spikes/SpikeCrusherPiece1.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_SpikeCrusherPiece2:	LoadGoodEntity(x,"Objects/Spikes/SpikeCrusherPiece2.b3d") : ScaleEntity MESHES(x),0.925,0.925,0.925
		Case Mesh_RockChunk1_brown:		LoadGoodEntity(x,"Objects/Breakables/RockChunk1_brown.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk2_brown:		LoadGoodEntity(x,"Objects/Breakables/RockChunk2_brown.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk3_brown:		LoadGoodEntity(x,"Objects/Breakables/RockChunk3_brown.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk1_grey:		LoadGoodEntity(x,"Objects/Breakables/RockChunk1_grey.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk2_grey:		LoadGoodEntity(x,"Objects/Breakables/RockChunk2_grey.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_RockChunk3_grey:		LoadGoodEntity(x,"Objects/Breakables/RockChunk3_grey.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk1_red:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk1_red.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk2_red:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk2_red.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk3_red:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk3_red.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk1_green:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk1_green.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk2_green:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk2_green.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk3_green:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk3_green.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk1_blue:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk1_blue.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk2_blue:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk2_blue.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_CrystalChunk3_blue:	LoadGoodEntity(x,"Objects/Breakables/CrystalChunk3_blue.b3d") : ScaleEntity MESHES(x),7.5,7.5,7.5
		Case Mesh_Tree1:			LoadGoodEntity(x,"Objects/Plants/Tree1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2:			LoadGoodEntity(x,"Objects/Plants/Tree2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3:			LoadGoodEntity(x,"Objects/Plants/Tree3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4:			LoadGoodEntity(x,"Objects/Plants/Tree4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5:			LoadGoodEntity(x,"Objects/Plants/Tree5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6:			LoadGoodEntity(x,"Objects/Plants/Tree6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree1Trunk:		LoadGoodEntity(x,"Objects/Plants/Tree1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2Trunk:		LoadGoodEntity(x,"Objects/Plants/Tree2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3Trunk:		LoadGoodEntity(x,"Objects/Plants/Tree3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4Trunk:		LoadGoodEntity(x,"Objects/Plants/Tree4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5Trunk:		LoadGoodEntity(x,"Objects/Plants/Tree5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6Trunk:		LoadGoodEntity(x,"Objects/Plants/Tree6Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub1:			LoadGoodEntity(x,"Objects/Plants/Shrub1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub1Trunk:		LoadGoodEntity(x,"Objects/Plants/Shrub1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub2:			LoadGoodEntity(x,"Objects/Plants/Shrub2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub3:			LoadGoodEntity(x,"Objects/Plants/Shrub3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub4:			LoadGoodEntity(x,"Objects/Plants/Shrub4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub4Trunk:		LoadGoodEntity(x,"Objects/Plants/Shrub4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub5:			LoadGoodEntity(x,"Objects/Plants/Shrub5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub5Trunk:		LoadGoodEntity(x,"Objects/Plants/Shrub5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Shrub6:			LoadGoodEntity(x,"Objects/Plants/Shrub6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush1:			LoadGoodEntity(x,"Objects/Plants/Bush1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush2:			LoadGoodEntity(x,"Objects/Plants/Bush2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush3:			LoadGoodEntity(x,"Objects/Plants/Bush3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush4:			LoadGoodEntity(x,"Objects/Plants/Bush4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush5:			LoadGoodEntity(x,"Objects/Plants/Bush5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Bush6:			LoadGoodEntity(x,"Objects/Plants/Bush6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass1:			LoadGoodEntity(x,"Objects/Plants/Grass1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass2:			LoadGoodEntity(x,"Objects/Plants/Grass2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass3:			LoadGoodEntity(x,"Objects/Plants/Grass3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass4:			LoadGoodEntity(x,"Objects/Plants/Grass4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass5:			LoadGoodEntity(x,"Objects/Plants/Grass5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass6:			LoadGoodEntity(x,"Objects/Plants/Grass6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass7:			LoadGoodEntity(x,"Objects/Plants/Grass7.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass8:			LoadGoodEntity(x,"Objects/Plants/Grass8.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass9:			LoadGoodEntity(x,"Objects/Plants/Grass9.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Grass10:			LoadGoodEntity(x,"Objects/Plants/Grass10.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree1Sakura:		LoadGoodEntity(x,"Objects/Plants/Tree1Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2Sakura:		LoadGoodEntity(x,"Objects/Plants/Tree2Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3Sakura:		LoadGoodEntity(x,"Objects/Plants/Tree3Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4Sakura:		LoadGoodEntity(x,"Objects/Plants/Tree4Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5Sakura:		LoadGoodEntity(x,"Objects/Plants/Tree5Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6Sakura:		LoadGoodEntity(x,"Objects/Plants/Tree6Sakura.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm1:			LoadGoodEntity(x,"Objects/Plants/Palm1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm2:			LoadGoodEntity(x,"Objects/Plants/Palm2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm3:			LoadGoodEntity(x,"Objects/Plants/Palm3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm4:			LoadGoodEntity(x,"Objects/Plants/Palm4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm1Trunk:		LoadGoodEntity(x,"Objects/Plants/Palm1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm2Trunk:		LoadGoodEntity(x,"Objects/Plants/Palm2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm3Trunk:		LoadGoodEntity(x,"Objects/Plants/Palm3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Palm4Trunk:		LoadGoodEntity(x,"Objects/Plants/Palm4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm1:		LoadGoodEntity(x,"Objects/Plants/WildPalm1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm2:		LoadGoodEntity(x,"Objects/Plants/WildPalm2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm3:		LoadGoodEntity(x,"Objects/Plants/WildPalm3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm4:		LoadGoodEntity(x,"Objects/Plants/WildPalm4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm5:		LoadGoodEntity(x,"Objects/Plants/WildPalm5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm6:		LoadGoodEntity(x,"Objects/Plants/WildPalm6.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm1Trunk:	LoadGoodEntity(x,"Objects/Plants/WildPalm1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm2Trunk:	LoadGoodEntity(x,"Objects/Plants/WildPalm2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm3Trunk:	LoadGoodEntity(x,"Objects/Plants/WildPalm3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm4Trunk:	LoadGoodEntity(x,"Objects/Plants/WildPalm4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm5Trunk:	LoadGoodEntity(x,"Objects/Plants/WildPalm5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_WildPalm6Trunk:	LoadGoodEntity(x,"Objects/Plants/WildPalm6Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower1:			LoadGoodEntity(x,"Objects/Plants/Flower1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower2:			LoadGoodEntity(x,"Objects/Plants/Flower2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower3:			LoadGoodEntity(x,"Objects/Plants/Flower3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Flower4:			LoadGoodEntity(x,"Objects/Plants/Flower4.b3d") : ScaleEntity MESHES(x),12,12,12
			ExtractAnimSeq(MESHES(x),1,65)
		Case Mesh_Flower5:			LoadGoodEntity(x,"Objects/Plants/Flower5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree1Snowy:		LoadGoodEntity(x,"Objects/Plants/Tree1Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree2Snowy:		LoadGoodEntity(x,"Objects/Plants/Tree2Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree3Snowy:		LoadGoodEntity(x,"Objects/Plants/Tree3Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree4Snowy:		LoadGoodEntity(x,"Objects/Plants/Tree4Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree5Snowy:		LoadGoodEntity(x,"Objects/Plants/Tree5Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Tree6Snowy:		LoadGoodEntity(x,"Objects/Plants/Tree6Snowy.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Vine1:				LoadGoodEntity(x,"Objects/Plants/Vine1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_DryTree1Trunk:		LoadGoodEntity(x,"Objects/Plants/DryTree1Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_DryTree2Trunk:		LoadGoodEntity(x,"Objects/Plants/DryTree2Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_DryTree3Trunk:		LoadGoodEntity(x,"Objects/Plants/DryTree3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat1:			LoadGoodEntity(x,"Objects/Plants/Adabat1.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat2:			LoadGoodEntity(x,"Objects/Plants/Adabat2.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat3:			LoadGoodEntity(x,"Objects/Plants/Adabat3.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat4:			LoadGoodEntity(x,"Objects/Plants/Adabat4.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat5:			LoadGoodEntity(x,"Objects/Plants/Adabat5.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat3Trunk:		LoadGoodEntity(x,"Objects/Plants/Adabat3Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat4Trunk:		LoadGoodEntity(x,"Objects/Plants/Adabat4Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Adabat5Trunk:		LoadGoodEntity(x,"Objects/Plants/Adabat5Trunk.b3d") : ScaleEntity MESHES(x),12,12,12
		Case Mesh_Enemy_AeroCannon:			LoadGoodEntity(x,"Objects/Enemies/AeroCannon.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_BuzzBomber:			LoadGoodEntity(x,"Objects/Enemies/BuzzBomber.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_Buzzer:				LoadGoodEntity(x,"Objects/Enemies/Buzzer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_CaterkillerBody:	LoadGoodEntity(x,"Objects/Enemies/CaterkillerBody.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CaterkillerBase:	LoadGoodEntity(x,"Objects/Enemies/CaterkillerBase.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Caterkiller:		LoadGoodEntity(x,"Objects/Enemies/Caterkiller.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,1);closed
			ExtractAnimSeq(MESHES(x),2,10);idle
			ExtractAnimSeq(MESHES(x),11,15);attack
		Case Mesh_Enemy_Chopper:			LoadGoodEntity(x,"Objects/Enemies/Chopper.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,5);move
			ExtractAnimSeq(MESHES(x),6,6);dead
		Case Mesh_Enemy_Crabmeat:			LoadGoodEntity(x,"Objects/Enemies/Crabmeat.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Chaser:				LoadGoodEntity(x,"Objects/Enemies/EggChaser.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_Fighter:			LoadGoodEntity(x,"Objects/Enemies/EggFighter.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,14);attack
		Case Mesh_Enemy_Flapper:			LoadGoodEntity(x,"Objects/Enemies/EggFlapper.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_FlapperGun:			LoadGoodEntity(x,"Objects/Enemies/EggFlapperGun.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_FlapperBomb:		LoadGoodEntity(x,"Objects/Enemies/EggFlapperBomb.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_FlapperNeedle:		LoadGoodEntity(x,"Objects/Enemies/EggFlapperNeedle.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_PawnGun:			LoadGoodEntity(x,"Objects/Enemies/EggPawnGun.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_PawnSword:			LoadGoodEntity(x,"Objects/Enemies/EggPawnSword.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_PawnShield:			LoadGoodEntity(x,"Objects/Enemies/EggPawnShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Pawn:				LoadGoodEntity(x,"Objects/Enemies/EggPawn.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
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
		Case Mesh_Enemy_Grabber:			LoadGoodEntity(x,"Objects/Enemies/Grabber.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Beetle:				LoadGoodEntity(x,"Objects/Enemies/GunBeetle.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_BeetleMono:			LoadGoodEntity(x,"Objects/Enemies/GunBeetleMono.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_BeetleSpark:		LoadGoodEntity(x,"Objects/Enemies/GunBeetleSpark.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
			ExtractAnimSeq(MESHES(x),14,26);shock
		Case Mesh_Enemy_BeetleSpring:		LoadGoodEntity(x,"Objects/Enemies/GunBeetleSpring.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_ArtificialChaos:	LoadGoodEntity(x,"Objects/Enemies/ArtificialChaos.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_ArtificialChaos2:	LoadGoodEntity(x,"Objects/Enemies/ArtificialChaos2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Hornet:				LoadGoodEntity(x,"Objects/Enemies/GunHornet.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle0
			ExtractAnimSeq(MESHES(x),10,18);idle3
			ExtractAnimSeq(MESHES(x),19,27);idle6
		Case Mesh_Enemy_HunterShield:		LoadGoodEntity(x,"Objects/Enemies/GunHunterShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Inactive:				LoadGoodEntity(x,"Objects/Enemies/Inactive.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			
		Case Mesh_Enemy_Hunter:				LoadGoodEntity(x,"Objects/Enemies/GunHunter.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
			ExtractAnimSeq(MESHES(x),19,27);fly
			ExtractAnimSeq(MESHES(x),28,36);idle2
			ExtractAnimSeq(MESHES(x),37,45);idle3
			ExtractAnimSeq(MESHES(x),46,54);fly1
		Case Mesh_Enemy_EggGunner:				LoadGoodEntity(x,"Objects/Enemies/EggGunner.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			a=1
			ExtractAnimSeq(MESHES(x),		a,	-1+a+9) : a=a+9	;Idle
			ExtractAnimSeq(MESHES(x),		a,	-1+a+17) : a=a+17	;Wait
			ExtractAnimSeq(MESHES(x),		a,	-1+a+9) : a=a+9	;Walk
		Case Mesh_Enemy_Rhino:				LoadGoodEntity(x,"Objects/Enemies/GunRhino.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);move
		Case Mesh_Enemy_RhinoSpikes:		LoadGoodEntity(x,"Objects/Enemies/GunRhinoSpikes.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);move
		Case Mesh_Enemy_Jaws:				LoadGoodEntity(x,"Objects/Enemies/Jaws.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,5);move
			ExtractAnimSeq(MESHES(x),6,6);dead
		Case Mesh_Enemy_Kiki:				LoadGoodEntity(x,"Objects/Enemies/Kiki.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);hold
			ExtractAnimSeq(MESHES(x),19,27);throw
		Case Mesh_Enemy_CopSpeeder:			LoadGoodEntity(x,"Objects/Enemies/CopSpeeder.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer1:			LoadGoodEntity(x,"Objects/Enemies/CopRacer1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer2:			LoadGoodEntity(x,"Objects/Enemies/CopRacer2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer3:			LoadGoodEntity(x,"Objects/Enemies/CopRacer3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer4:			LoadGoodEntity(x,"Objects/Enemies/CopRacer4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer5:			LoadGoodEntity(x,"Objects/Enemies/CopRacer5.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer6:			LoadGoodEntity(x,"Objects/Enemies/CopRacer6.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_CopRacer1Car:		LoadGoodEntity(x,"Objects/Enemies/CopRacerCar1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer2Car:		LoadGoodEntity(x,"Objects/Enemies/CopRacerCar2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer3Car:		LoadGoodEntity(x,"Objects/Enemies/CopRacerCar3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer4Car:		LoadGoodEntity(x,"Objects/Enemies/CopRacerCar4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer5Car:		LoadGoodEntity(x,"Objects/Enemies/CopRacerCar5.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_CopRacer6Car:		LoadGoodEntity(x,"Objects/Enemies/CopRacerCar6.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Motobug:			LoadGoodEntity(x,"Objects/Enemies/Motobug.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);move
			ExtractAnimSeq(MESHES(x),19,27);charge
		Case Mesh_Enemy_Spana:				LoadGoodEntity(x,"Objects/Enemies/Spana.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_Spina:				LoadGoodEntity(x,"Objects/Enemies/Spina.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle#
		Case Mesh_Enemy_Spuna:				LoadGoodEntity(x,"Objects/Enemies/Spuna.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_Spona:				LoadGoodEntity(x,"Objects/Enemies/Spona.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_Spiny:				LoadGoodEntity(x,"Objects/Enemies/Spiny.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle1
			ExtractAnimSeq(MESHES(x),18,34);idle
		Case Mesh_Enemy_EggRobo:			LoadGoodEntity(x,"Objects/Enemies/EggRobo.b3d")
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);attack
		Case Mesh_Enemy_Cameron:			LoadGoodEntity(x,"Objects/Enemies/Cameron.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Klagen:				LoadGoodEntity(x,"Objects/Enemies/Klagen.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Orbinaut1:			LoadGoodEntity(x,"Objects/Enemies/Orbinaut1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut2:			LoadGoodEntity(x,"Objects/Enemies/Orbinaut2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut3:			LoadGoodEntity(x,"Objects/Enemies/Orbinaut3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut4:			LoadGoodEntity(x,"Objects/Enemies/Orbinaut4.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut5:			LoadGoodEntity(x,"Objects/Enemies/Orbinaut5.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Orbinaut6:			LoadGoodEntity(x,"Objects/Enemies/Orbinaut6.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,14);idle
		Case Mesh_Enemy_Typhoon1:			LoadGoodEntity(x,"Objects/Enemies/Typhoon1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,10);idle
			ExtractAnimSeq(MESHES(x),11,25);blow
		Case Mesh_Enemy_Typhoon2:			LoadGoodEntity(x,"Objects/Enemies/Typhoon2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,10);idle
			ExtractAnimSeq(MESHES(x),11,25);blow
		Case Mesh_Enemy_Anton:			LoadGoodEntity(x,"Objects/Enemies/Anton.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,10);idle
			ExtractAnimSeq(MESHES(x),11,20);move
		Case Mesh_Enemy_Aquis:			LoadGoodEntity(x,"Objects/Enemies/Aquis.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,10);dead
			ExtractAnimSeq(MESHES(x),11,19);shoot
		Case Mesh_Enemy_Bombie:			LoadGoodEntity(x,"Objects/Enemies/Bombie.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);move
		Case Mesh_Enemy_Newtron:		LoadGoodEntity(x,"Objects/Enemies/Newtron.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,10);idle
			ExtractAnimSeq(MESHES(x),11,19);shoot
		Case Mesh_Enemy_Penguinator:	LoadGoodEntity(x,"Objects/Enemies/Penguinator.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);move
			ExtractAnimSeq(MESHES(x),27,35);move2
		Case Mesh_Enemy_Slicer:			LoadGoodEntity(x,"Objects/Enemies/Slicer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,19);attack
			ExtractAnimSeq(MESHES(x),20,28);idle2
		Case Mesh_Enemy_SnailBlaster:	LoadGoodEntity(x,"Objects/Enemies/SnailBlaster.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Spikes:			LoadGoodEntity(x,"Objects/Enemies/Spikes.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);move
			ExtractAnimSeq(MESHES(x),19,27);idle1
			ExtractAnimSeq(MESHES(x),28,36);move1
		Case Mesh_Enemy_Asteron:		LoadGoodEntity(x,"Objects/Enemies/Asteron.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Batbot:			LoadGoodEntity(x,"Objects/Enemies/Batbot.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);fly
			ExtractAnimSeq(MESHES(x),18,34);idle
		Case Mesh_Enemy_Bubbles:		LoadGoodEntity(x,"Objects/Enemies/Bubbles.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Bubbles2:		LoadGoodEntity(x,"Objects/Enemies/Bubbles2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Steelion:		LoadGoodEntity(x,"Objects/Enemies/Steelion.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);move
			ExtractAnimSeq(MESHES(x),27,43);attack
			ExtractAnimSeq(MESHES(x),44,52);idle1
			ExtractAnimSeq(MESHES(x),53,69);move1
			ExtractAnimSeq(MESHES(x),70,86);attack1
		Case Mesh_Enemy_Boo:			LoadGoodEntity(x,"Objects/Enemies/Boo.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);attack
			ExtractAnimSeq(MESHES(x),19,27);attack1
		Case Mesh_Enemy_Ghost:			LoadGoodEntity(x,"Objects/Enemies/Ghost.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Balkiry:		LoadGoodEntity(x,"Objects/Enemies/Balkiry.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,10);idle
			ExtractAnimSeq(MESHES(x),11,28);fly
			ExtractAnimSeq(MESHES(x),29,38);swirl
		Case Mesh_Enemy_Burrobot:		LoadGoodEntity(x,"Objects/Enemies/Burrobot.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);move
			ExtractAnimSeq(MESHES(x),35,51);idle1
			ExtractAnimSeq(MESHES(x),52,52);idle2
		Case Mesh_Enemy_Crawl:			LoadGoodEntity(x,"Objects/Enemies/Crawl.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);move
			ExtractAnimSeq(MESHES(x),27,35);block
			ExtractAnimSeq(MESHES(x),36,44);blockduck
		Case Mesh_Enemy_Dragonfly:		LoadGoodEntity(x,"Objects/Enemies/Dragonfly.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);idle1
			ExtractAnimSeq(MESHES(x),27,43);idle2
		Case Mesh_Enemy_MadmoleBody:	LoadGoodEntity(x,"Objects/Enemies/MadmoleBody.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_MadmoleBase:	LoadGoodEntity(x,"Objects/Enemies/MadmoleBase.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Madmole:		LoadGoodEntity(x,"Objects/Enemies/Madmole.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
			ExtractAnimSeq(MESHES(x),19,35);throw
			ExtractAnimSeq(MESHES(x),36,36);closed
		Case Mesh_Enemy_Manta:			LoadGoodEntity(x,"Objects/Enemies/Manta.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_MushmeanieBody:	LoadGoodEntity(x,"Objects/Enemies/MushmeanieBody.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_MushmeanieHat:	LoadGoodEntity(x,"Objects/Enemies/MushmeanieHat.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Mushmeanie:		LoadGoodEntity(x,"Objects/Enemies/Mushmeanie.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);move
			ExtractAnimSeq(MESHES(x),18,34);move1
		Case Mesh_Enemy_Octus:			LoadGoodEntity(x,"Objects/Enemies/Octus.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle1
			ExtractAnimSeq(MESHES(x),18,34);move1
			ExtractAnimSeq(MESHES(x),35,43);shoot1
			ExtractAnimSeq(MESHES(x),44,60);idle
			ExtractAnimSeq(MESHES(x),61,77);move
			ExtractAnimSeq(MESHES(x),78,86);shoot
		Case Mesh_Enemy_Patabata:		LoadGoodEntity(x,"Objects/Enemies/Patabata.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Zoomer:			LoadGoodEntity(x,"Objects/Enemies/Zoomer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);attack
			ExtractAnimSeq(MESHES(x),35,51);dive
		Case Mesh_Enemy_Biter:			LoadGoodEntity(x,"Objects/Enemies/Biter.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);move
			ExtractAnimSeq(MESHES(x),27,35);attack
			ExtractAnimSeq(MESHES(x),36,44);attack1
		Case Mesh_Enemy_Crawler:		LoadGoodEntity(x,"Objects/Enemies/Crawler.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);rise
			ExtractAnimSeq(MESHES(x),27,35);attack
			ExtractAnimSeq(MESHES(x),36,36);idle1
		Case Mesh_Enemy_Taker:			LoadGoodEntity(x,"Objects/Enemies/Taker.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);fly
			ExtractAnimSeq(MESHES(x),19,27);attack
		Case Mesh_Enemy_E1000:			LoadGoodEntity(x,"Objects/Enemies/E1000.b3d")
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,26);move
			ExtractAnimSeq(MESHES(x),27,35);fly
		Case Mesh_Enemy_BallHog:		LoadGoodEntity(x,"Objects/Enemies/BallHog.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);idle1
		Case Mesh_Enemy_Rhinotank:		LoadGoodEntity(x,"Objects/Enemies/Rhinotank.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);move
			ExtractAnimSeq(MESHES(x),19,27);charge
		Case Mesh_Enemy_TechnoSqueek:	LoadGoodEntity(x,"Objects/Enemies/TechnoSqueek.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);move
		Case Mesh_Enemy_BlackLeech:		LoadGoodEntity(x,"Objects/Enemies/BlackLeech.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,18);idle
		Case Mesh_Enemy_BlackOakSword:	LoadGoodEntity(x,"Objects/Enemies/BlackOakSword.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_BlackOak:		LoadGoodEntity(x,"Objects/Enemies/BlackOak.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle1
			ExtractAnimSeq(MESHES(x),18,34);idle
			ExtractAnimSeq(MESHES(x),35,51);move
			ExtractAnimSeq(MESHES(x),52,60);attack
		Case Mesh_Enemy_BlackWarriorGun1:LoadGoodEntity(x,"Objects/Enemies/BlackWarriorGun1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_BlackWarriorGun2:LoadGoodEntity(x,"Objects/Enemies/BlackWarriorGun2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_BlackWarrior:	LoadGoodEntity(x,"Objects/Enemies/BlackWarrior.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);move
			ExtractAnimSeq(MESHES(x),35,43);attack
			ExtractAnimSeq(MESHES(x),44,60);idle1
			ExtractAnimSeq(MESHES(x),61,77);move1
			ExtractAnimSeq(MESHES(x),78,87);attack1
			ExtractAnimSeq(MESHES(x),88,104);idle2
			ExtractAnimSeq(MESHES(x),105,121);move2
			ExtractAnimSeq(MESHES(x),122,131);attack2
		Case Mesh_Enemy_BlackWing:		LoadGoodEntity(x,"Objects/Enemies/BlackWing.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);fly
		Case Mesh_Enemy_Soldier:		LoadGoodEntity(x,"Objects/Enemies/Soldier.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idlelook
			ExtractAnimSeq(MESHES(x),18,26);idle
			ExtractAnimSeq(MESHES(x),27,43);move
			ExtractAnimSeq(MESHES(x),44,52);shoot
			ExtractAnimSeq(MESHES(x),53,61);shoot2
		Case Mesh_Enemy_Soldier2:		LoadGoodEntity(x,"Objects/Enemies/Soldier2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idlelook
			ExtractAnimSeq(MESHES(x),18,26);idle
			ExtractAnimSeq(MESHES(x),27,43);move
			ExtractAnimSeq(MESHES(x),44,52);shoot
			ExtractAnimSeq(MESHES(x),53,61);shoot2
		Case Mesh_Enemy_CatakillerJr:	LoadGoodEntity(x,"Objects/Enemies/CatakillerJr.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle1
		Case Mesh_Enemy_Cluckoid:		LoadGoodEntity(x,"Objects/Enemies/Cluckoid.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);blow
		Case Mesh_Enemy_Mantis:			LoadGoodEntity(x,"Objects/Enemies/Mantis.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,22);jump
			ExtractAnimSeq(MESHES(x),23,31);idle1
		Case Mesh_Enemy_Nebula:			LoadGoodEntity(x,"Objects/Enemies/Nebula.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
		Case Mesh_Enemy_Roller:			LoadGoodEntity(x,"Objects/Enemies/Roller.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,22);movestart
			ExtractAnimSeq(MESHES(x),23,31);move
		Case Mesh_Enemy_SheepFluff:		LoadGoodEntity(x,"Objects/Enemies/SheepFluff.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Sheep:			LoadGoodEntity(x,"Objects/Enemies/Sheep.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);move
			ExtractAnimSeq(MESHES(x),35,51);idle1
			ExtractAnimSeq(MESHES(x),52,68);move1
		Case Mesh_Enemy_Snowy:			LoadGoodEntity(x,"Objects/Enemies/Snowy.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);move
			ExtractAnimSeq(MESHES(x),35,59);attack1
			ExtractAnimSeq(MESHES(x),60,84);attack2
		Case Mesh_Enemy_Splats:			LoadGoodEntity(x,"Objects/Enemies/Splats.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);move
		Case Mesh_Enemy_Toxomister:		LoadGoodEntity(x,"Objects/Enemies/Toxomister.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
		Case Mesh_Enemy_Sprinkler:		LoadGoodEntity(x,"Objects/Enemies/Sprinkler.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,13);idle
		Case Mesh_Enemy_DoomsEye:		LoadGoodEntity(x,"Objects/Enemies/DoomsEye.b3d")
			ExtractAnimSeq(MESHES(x),1,9);move
		Case Mesh_Enemy_HammerHammer:	LoadGoodEntity(x,"Objects/Enemies/EggHammerHammer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_HammerShield:	LoadGoodEntity(x,"Objects/Enemies/EggHammerShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Enemy_Hammer:			LoadGoodEntity(x,"Objects/Enemies/EggHammer.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);run
			ExtractAnimSeq(MESHES(x),19,27);idle1
			ExtractAnimSeq(MESHES(x),28,36);run1
			ExtractAnimSeq(MESHES(x),37,45);idle2
			ExtractAnimSeq(MESHES(x),46,54);attack2
			ExtractAnimSeq(MESHES(x),55,63);run2
		Case Mesh_Enemy_Witch1:			LoadGoodEntity(x,"Objects/Enemies/EggWitch1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_Witch2:			LoadGoodEntity(x,"Objects/Enemies/EggWitch2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idle
			ExtractAnimSeq(MESHES(x),10,18);idle1
		Case Mesh_Enemy_FCannon1:		LoadGoodEntity(x,"Objects/Enemies/FCannon1.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,1);idle
			ExtractAnimSeq(MESHES(x),2,2);idle1
		Case Mesh_Enemy_FCannon2:		LoadGoodEntity(x,"Objects/Enemies/FCannon2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,1);idle
			ExtractAnimSeq(MESHES(x),2,2);idle1
		Case Mesh_Enemy_FCannon3:		LoadGoodEntity(x,"Objects/Enemies/FCannon3.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,1);idle
			ExtractAnimSeq(MESHES(x),2,2);idle1
		Case Mesh_Enemy_Bomber1:		LoadGoodEntity(x,"Objects/Enemies/Bomber1.b3d")
		Case Mesh_Enemy_Bomber2:		LoadGoodEntity(x,"Objects/Enemies/Bomber2.b3d")
		Case Mesh_Boss_EggMobile:		LoadGoodEntity(x,"Objects/Enemies/EggMobile.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),1,1)
		Case Mesh_Boss_EggMobileNega:	LoadGoodEntity(x,"Objects/Enemies/EggMobileNega.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9)
			ExtractAnimSeq(MESHES(x),1,1)
		Case Mesh_Boss_Betamk2:			LoadGoodEntity(x,"Objects/Enemies/Betamk2.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,9);idlefly
			ExtractAnimSeq(MESHES(x),10,18);block
			ExtractAnimSeq(MESHES(x),19,27);chargestart
			ExtractAnimSeq(MESHES(x),28,36);chargemove
			ExtractAnimSeq(MESHES(x),37,45);shootidle
			ExtractAnimSeq(MESHES(x),46,54);shoot
			ExtractAnimSeq(MESHES(x),55,63);energycharge
			ExtractAnimSeq(MESHES(x),64,76);energyshoot
			ExtractAnimSeq(MESHES(x),77,85);stun
		Case Mesh_Boss_MechaSonic:		LoadGoodEntity(x,"Objects/Enemies/MechaSonic.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);fly
			ExtractAnimSeq(MESHES(x),35,43);spin
			ExtractAnimSeq(MESHES(x),44,52);attack
			ExtractAnimSeq(MESHES(x),53,57);super
			ExtractAnimSeq(MESHES(x),58,62);flysuper
		Case Mesh_Boss_MechaSonicS:		LoadGoodEntity(x,"Objects/Enemies/MechaSonicS.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
			ExtractAnimSeq(MESHES(x),1,17);idle
			ExtractAnimSeq(MESHES(x),18,34);fly
			ExtractAnimSeq(MESHES(x),35,43);spin
			ExtractAnimSeq(MESHES(x),44,52);attack
			ExtractAnimSeq(MESHES(x),53,57);super
			ExtractAnimSeq(MESHES(x),58,62);flysuper
		Case Mesh_Boss_EggMobile_Shield:		LoadGoodEntity(x,"Objects/Shields/EggMobileShield.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Boss_BetaRainbow:				LoadGoodEntity(x,"Objects/Enemies/BetaRainbow.b3d") : ScaleEntity MESHES(x),1.1,1.1,1.1
		Case Mesh_Axes:				MESHES(x) = LoadMesh("Objects/Axes.b3d")
		Case Mesh_Breeder:			LoadGoodEntity(x,"ChaoWorld\Cocoons\Breeder.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Whistle:			LoadGoodEntity(x,"ChaoWorld/BlackMarket/Whistle.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
		Case Mesh_Petter:			LoadGoodEntity(x,"ChaoWorld/BlackMarket/Petter.b3d")
			ExtractAnimSeq(MESHES(x),1,9)
		Default
			For vs = 1 To VISUAL_AMOUNT
				Select x
					Case Mesh_BRing20S+vs:			MESHES(x) = LoadAnimMesh(Game\Stage\Properties\Path$+"Objects/Visuals/Custom"+Str(vs)+".b3d"); : ExtractAnimSeq(MESHES(x),1,17)
				End Select
			Next
			
	End Select
	
	HideEntity(MESHES(x))
	MESHES_EXISTS(x)=True
	
End Function

;~IDEal Editor Parameters:
;~C#Blitz3D