
Function Player_HandleCheats_DebugPlacer(p.tPlayer, mode=1)
		If Not Game\Victory=0 Then Return
		PlaySmartSound(Sound_DebugOnOff)
		If Game\StartoutLock>0 Then Game\StartoutLock=0
		Select Game\Interface\DebugPlacerOn
			Case 0:
				If Game\Interface\DebugSpeed#=0 Then Game\Interface\DebugSpeed#=1
				If Game\Interface\DebugSpeed2#=0 Then Game\Interface\DebugSpeed2#=1
				p\Action=ACTION_DEBUG
				p\Objects\Mesh2=CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				p\Objects\Mesh3=CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				p\Objects\Mesh4=CopyEntity(MESHES(Mesh_Locker), Game\Stage\Root)
				p\Objects\Mesh5=CopyEntity(MESHES(Mesh_Point), Game\Stage\Root)
				Game\Interface\DebugAxesMesh=CopyEntity(MESHES(SmartEntity(Mesh_Axes)), Game\Stage\Root)
				EntityType(p\Objects\Entity,COLLISION_NONE)
				EntityRadius(p\Objects\Entity, 0)
				Game\Interface\DebugCollision=0
				Game\Interface\DebugWhichSwitch=1
				p\Motion\Ground=False
				Game\Interface\DebugSpawnedObj=0
				ResetTempAttribute()
			Case 1:
				p\Action=ACTION_FALL
				FreeEntity p\Objects\Mesh2
				FreeEntity p\Objects\Mesh3
				FreeEntity p\Objects\Mesh4
				FreeEntity p\Objects\Mesh5
				FreeEntity Game\Interface\DebugAxesMesh
				DeformCharacter(p,true)
				EntityType(p\Objects\Entity,COLLISION_PLAYER)
				Player_SetRadius#(p)
				p\Motion\Ground=False
				If mode=1 and Game\Interface\DebugSpawnedObj=1 Then Player_Action_Debug_QuitAndSave(p)
		End Select
		Game\Interface\DebugPlacerOn=abs(Game\Interface\DebugPlacerOn-1)
		p\ObjType=0
		Player_SetSpeed(p,0)
		p\Motion\Speed\y#=0
		p\GoDestination=False
		Game\CamLock=0
		Game\ControlLock=0
		Game\RunLock=0
		Game\MachLock=0
		Game\MachLockTriggered=0
		Game\Interface\DebugMenu=0
		Game\Interface\DebugMenuOption=1
		Game\Vehicle=0
		If p\HasVehicle>0 Then
			For ppp.tPlayer=Each tPlayer
				ppp\HasVehicle=0
				FreeEntity ppp\Objects\Vehicle
				If Not(ppp\No#=1) Then ppp\Action=ACTION_FALL
			Next
		EndIf
		FlushAll()
		For rz.tRazer = Each tRazer
			If rz\Mode=4 and (rz\Threshold=p\Objects\Entity Or rz\Threshold=p\Objects\Mesh) Then
				FreeEntity(rz\Mesh)
				FreeEntity(rz\Pivot)
				Delete rz
			EndIf
		Next
		Game\SmartCameraRangeDontAffectTimer=5*secs#
		Game\Cheater=1
End Function

Function Player_Action_Debug(p.tPlayer, d.tDeltaTime)

	Player_SetSpeed(p,0)
	p\Motion\Speed\y#=0

	If Game\Interface\DebugNewObj<>0 and Game\Interface\DebugMenu=0 Then
		Player_Action_Debug_ChangeObj(p)
		p\ObjType=Game\Interface\DebugNewObj
		Game\Interface\DebugNewObj=0
	EndIf

	Select Game\Interface\DebugMenu
		Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#,DEBUGMENU_ATTRIBUTES_CAMROTATION#,DEBUGMENU_ATTRIBUTES_CAMZOOM#,DEBUGMENU_ATTRIBUTES_CAMSPEED#,DEBUGMENU_ATTRIBUTES_DESTINATION#:
		Default:
			TempAttribute\x#=p\Objects\Position\x#
			TempAttribute\y#=p\Objects\Position\y#
			TempAttribute\z#=p\Objects\Position\z#
	End Select
	If TempAttribute\pitch#<0 Then TempAttribute\pitch#=0
	If TempAttribute\pitch#>180 Then TempAttribute\pitch#=180
	If TempAttribute\yaw#<-180 Then TempAttribute\yaw#=180
	If TempAttribute\yaw#>180 Then TempAttribute\yaw#=-180
	If TempAttribute\campitch#<-90 Then TempAttribute\campitch#=-90
	If TempAttribute\campitch#>90 Then TempAttribute\campitch#=90
	If TempAttribute\camyaw#<0 Then TempAttribute\camyaw#=360
	If TempAttribute\camyaw#>360 Then TempAttribute\camyaw#=0
	If TempAttribute\amountpitch#<-90 Then TempAttribute\amountpitch#=-90
	If TempAttribute\amountpitch#>90 Then TempAttribute\amountpitch#=90
	If TempAttribute\amountyaw#<-180 Then TempAttribute\amountyaw#=180
	If TempAttribute\amountyaw#>180 Then TempAttribute\amountyaw#=-180
	If Game\Interface\DebugHadD Then ShowEntity(p\Objects\Mesh5) Else HideEntity(p\Objects\Mesh5)

	Select Game\Interface\DebugMenu
		Case DEBUGMENU_ATTRIBUTES_AMOUNT#,DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#,DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#:
			PositionEntity p\Objects\Mesh6, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#
			RotateEntity p\Objects\Mesh6, TempAttribute\amountpitch#, TempAttribute\amountyaw#, 0
			MoveEntity p\Objects\Mesh6, 0, 0, TempAttribute\amountspace1#
			RotateEntity p\Objects\Mesh6, TempAttribute\pitch#, TempAttribute\yaw#, TempAttribute\roll#
	End Select

	If Not(Game\Interface\DebugEnteredAttributeTimer>0) Then
		Select Game\Interface\DebugMenu
			Case DEBUGMENU_ATTRIBUTES_POSITION#: Player_Action_Debug_Position(p,d)
			Case DEBUGMENU_ATTRIBUTES_ROTATION#: Player_Action_Debug_Rotation(p,d)
			Case DEBUGMENU_ATTRIBUTES_POWER#: Player_Action_Debug_Power(p)
			Case DEBUGMENU_ATTRIBUTES_LOCKS#: Player_Action_Debug_Locks(p)
			Case DEBUGMENU_ATTRIBUTES_CAMPOSITION#: Player_Action_Debug_CamPosition(p,d)
			Case DEBUGMENU_ATTRIBUTES_CAMROTATION#: Player_Action_Debug_CamRotation(p,d)
			Case DEBUGMENU_ATTRIBUTES_CAMZOOM#: Player_Action_Debug_CamZoom(p)
			Case DEBUGMENU_ATTRIBUTES_CAMSPEED#: Player_Action_Debug_CamSpeed(p)
			Case DEBUGMENU_ATTRIBUTES_AMOUNT#: Player_Action_Debug_Amount(p)
			Case DEBUGMENU_ATTRIBUTES_AMOUNTROTATION#: Player_Action_Debug_AmountRotation(p,d)
			Case DEBUGMENU_ATTRIBUTES_AMOUNTSPACE#: Player_Action_Debug_AmountSpace(p)
			Case DEBUGMENU_ATTRIBUTES_SWITCH#: Player_Action_Debug_Switch(p)
			Case DEBUGMENU_ATTRIBUTES_SWITCHSTATUS#: Player_Action_Debug_SwitchStatus(p)
			Case DEBUGMENU_ATTRIBUTES_TELEPORTER#: Player_Action_Debug_Teleporter(p)
			Case DEBUGMENU_ATTRIBUTES_DESTINATION#: Player_Action_Debug_Destination(p,d)
		End Select
	Else
		Game\Interface\DebugEnteredAttributeTimer=Game\Interface\DebugEnteredAttributeTimer-timervalue#
	EndIf

End Function

;---------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------

	Function Player_Action_Debug_ChangeObj(p.tPlayer)
		FreeEntity p\Objects\Mesh
		FreeEntity p\Objects\Mesh2
		mesh1=Mesh_Empty : mesh2=Mesh_Empty
		Select Game\Interface\DebugNewObj
			Case OBJTYPE_RING:		mesh1=Mesh_Ring
			Case OBJTYPE_SPRING:		mesh1=Mesh_Spring
			Case OBJTYPE_BSPRING:		mesh1=Mesh_BSpring
			Case OBJTYPE_SPRINGX:		mesh1=Mesh_SpringX
			Case OBJTYPE_SPRINGTRAP:	mesh1=Mesh_SpringTrap
			Case OBJTYPE_SPRINGTRAPX:	mesh1=Mesh_SpringTrapX
			Case OBJTYPE_PAD:		mesh1=Mesh_DashPanel : mesh2=Mesh_DashPanelPads
			Case OBJTYPE_RAMP:		mesh1=Mesh_DashRamp : mesh2=Mesh_DashRampPads
			Case OBJTYPE_HOOP:		mesh1=Mesh_DashHoop : mesh2=Mesh_Locker
			Case OBJTYPE_THOOP:		mesh1=Mesh_RainbowHoop : mesh2=Mesh_Locker
			Case OBJTYPE_ACCEL:		mesh1=Mesh_Accelerator : mesh2=Mesh_AcceleratorLight
			Case OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000:
							mesh1=Mesh_Locker
			Case OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE:
							mesh1=Mesh_Locker2
			Case OBJTYPE_FAN,OBJTYPE_FAN+1000,OBJTYPE_BFAN,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW,OBJTYPE_BFANLOW+1000:
							mesh1=Mesh_Fan
			Case OBJTYPE_CHECK:		mesh1=Mesh_Checkpoint : mesh2=Mesh_Locker
			Case OBJTYPE_CHECK+1000:	mesh1=Mesh_Checkpoint2 : mesh2=Mesh_Locker
			Case OBJTYPE_CHECK+2000:	mesh1=Mesh_Checkpoint3 : mesh2=Mesh_Locker
			Case OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS,OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000:
							If Game\Interface\DebugNewObj<1000 Then mesh1=Mesh_Monitor Else mesh1=Mesh_MonitorBalloon
							Select Game\Interface\DebugNewObj
								Case OBJTYPE_RINGS:		mesh2=Mesh_BRing10
								Case OBJTYPE_LIFE:		mesh2=Mesh_Life
								Case OBJTYPE_TRAP:		mesh2=Mesh_Trap
								Case OBJTYPE_INVINC:		mesh2=Mesh_Invincible
								Case OBJTYPE_SHOES:		mesh2=Mesh_Shoe
								Case OBJTYPE_NSHIELD:		mesh2=Mesh_ShieldNormalX
								Case OBJTYPE_FSHIELD:		mesh2=Mesh_ShieldFlameX
								Case OBJTYPE_BSHIELD:		mesh2=Mesh_ShieldBubbleX
								Case OBJTYPE_TSHIELD:		mesh2=Mesh_ShieldThunderX
								Case OBJTYPE_ESHIELD:		mesh2=Mesh_ShieldEarthX
								Case OBJTYPE_BOMB:		mesh2=Mesh_Bomb
								Case OBJTYPE_BOARD:		mesh2=Mesh_BoardX
								Case OBJTYPE_GLIDER:		mesh2=Mesh_GliderX
								Case OBJTYPE_CAR:		mesh2=Mesh_Enemy_CopRacer1Car
								Case OBJTYPE_BIKE:		mesh2=Mesh_Bike
								Case OBJTYPE_BOBSLEIGH:		mesh2=Mesh_Bobsleigh
								Case OBJTYPE_TORNADO:		mesh2=Mesh_Tornado1
								Case OBJTYPE_CYCLONE:		mesh2=Mesh_Cyclone
								Case OBJTYPE_KART:			mesh2=Mesh_Kart
								Case OBJTYPE_WINGS:		mesh2=Mesh_Wings
							End Select
			Case OBJTYPE_BALLOON:		mesh1=Mesh_Balloon1
			Case OBJTYPE_SPIKEBALL:		mesh1=Mesh_SpikeBall
			Case OBJTYPE_SPIKEBOMB:		mesh1=Mesh_SpikeBomb
			Case OBJTYPE_SPIKECRUSHER:	mesh1=Mesh_SpikeCrusher
			Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP:
							mesh1=Mesh_SpikeDrill
			Case OBJTYPE_SPIKEBAR:		mesh1=Mesh_SpikeBar1 : mesh2=Mesh_SpikeBarPoles1
			Case OBJTYPE_SPIKEBAR+1000:	mesh1=Mesh_SpikeBar2 : mesh2=Mesh_SpikeBarPoles2
			Case OBJTYPE_SPIKEBAR+2000:	mesh1=Mesh_SpikeBar3 : mesh2=Mesh_SpikeBarPoles3
			Case OBJTYPE_SPIKESWING,OBJTYPE_SPIKESWING+1000,OBJTYPE_SPIKESWING+2000:
							mesh1=Mesh_SpikeSwing : mesh2=Mesh_SpikeSwingChain
			Case OBJTYPE_SPIKESWINGBALL: mesh1=Mesh_SpikeSwingBall
			Case OBJTYPE_SPIKECYLINDER:	mesh1=Mesh_SpikeCylinder
			Case OBJTYPE_GOAL,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
							mesh1=Mesh_GoalRing
			Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT:
							mesh1=Mesh_Spout
			Case OBJTYPE_SHOCKSPOUT:	mesh1=Mesh_ShockBar
			Case OBJTYPE_LASERV:		mesh1=Mesh_LaserV : mesh2=Mesh_LaserVX
			Case OBJTYPE_LASERH:		mesh1=Mesh_LaserH : mesh2=Mesh_LaserHX
			Case OBJTYPE_RINGGATEV:		mesh1=Mesh_Laser2V : mesh2=Mesh_Laser2VX
			Case OBJTYPE_RINGGATEH:		mesh1=Mesh_Laser2H : mesh2=Mesh_Laser2HX
			Case OBJTYPE_BOXWOODEN,OBJTYPE_BOXWOODEN+1000,OBJTYPE_BOXWOODEN+2000:
							mesh1=Mesh_BoxWooden
			Case OBJTYPE_BOXMETAL,OBJTYPE_BOXMETAL+1000,OBJTYPE_BOXMETAL+2000:
							mesh1=Mesh_BoxMetal
			Case OBJTYPE_BOXIRON, OBJTYPE_BOXIRON+1000,OBJTYPE_BOXIRON+2000:
							mesh1=Mesh_BoxIron
			Case OBJTYPE_BOXCAGE,OBJTYPE_BOXCAGE+1000,OBJTYPE_BOXCAGE+2000:
							mesh1=Mesh_BoxCage
			Case OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000:
							mesh1=Mesh_BoxLightOn
			Case OBJTYPE_BOXTNT,OBJTYPE_BOXTNT+1000,OBJTYPE_BOXTNT+2000:
							mesh1=Mesh_BoxTnt
			Case OBJTYPE_BOXNITRO,OBJTYPE_BOXNITRO+1000,OBJTYPE_BOXNITRO+2000:
							mesh1=Mesh_BoxNitro
			Case OBJTYPE_BOXFLOAT,OBJTYPE_BOXFLOAT+1000,OBJTYPE_BOXFLOAT+2000:
							mesh1=Mesh_BoxFloat
			Case OBJTYPE_BALLBUMPER:	mesh1=Mesh_BallBumperOn
			Case OBJTYPE_GROUNDBUMPER:	mesh1=Mesh_GroundBumperOn
			Case OBJTYPE_METROBUMPER:	mesh1=Mesh_MetroBumper1
			Case OBJTYPE_PLATEBUMPER:	mesh1=Mesh_PlateBumper1
			Case OBJTYPE_TRIANGLEBUMPER:	mesh1=Mesh_TriangleBumper
			Case OBJTYPE_PADDLE:		mesh1=Mesh_Paddle
			Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD:
							mesh1=Mesh_Enemy_Pawn
			Case OBJTYPE_FLAPPER:		mesh1=Mesh_Enemy_Flapper
			Case OBJTYPE_FLAPPERGUN:	mesh1=Mesh_Enemy_FlapperGun
			Case OBJTYPE_FLAPPERBOMB:	mesh1=Mesh_Enemy_FlapperBomb
			Case OBJTYPE_FLAPPERNEEDLE:	mesh1=Mesh_Enemy_FlapperNeedle
			Case OBJTYPE_SPINA:		mesh1=Mesh_Enemy_Spina
			Case OBJTYPE_SPANA:		mesh1=Mesh_Enemy_Spana
			Case OBJTYPE_SPONA:		mesh1=Mesh_Enemy_Spona
			Case OBJTYPE_MOTOBUG:		mesh1=Mesh_Enemy_Motobug
			Case OBJTYPE_CATERKILLER:	mesh1=Mesh_Enemy_Caterkiller
			Case OBJTYPE_BUZZBOMBER:	mesh1=Mesh_Enemy_BuzzBomber
			Case OBJTYPE_BUZZER:		mesh1=Mesh_Enemy_Buzzer
			Case OBJTYPE_CHOPPER:		mesh1=Mesh_Enemy_Chopper
			Case OBJTYPE_CRABMEAT:		mesh1=Mesh_Enemy_Crabmeat
			Case OBJTYPE_JAWS:		mesh1=Mesh_Enemy_Jaws
			Case OBJTYPE_SPINY:		mesh1=Mesh_Enemy_Spiny
			Case OBJTYPE_GRABBER:		mesh1=Mesh_Enemy_Grabber
			Case OBJTYPE_KIKI:		mesh1=Mesh_Enemy_Kiki
			Case OBJTYPE_COP:		mesh1=Mesh_Enemy_CopSpeeder
			Case OBJTYPE_COPRACER:		mesh1=Mesh_Enemy_CopRacer1
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD:
							mesh1=Mesh_Enemy_Hunter
			Case OBJTYPE_BEETLE:		mesh1=Mesh_Enemy_Beetle
			Case OBJTYPE_BEETLEMONO:	mesh1=Mesh_Enemy_BeetleMono
			Case OBJTYPE_BEETLESPARK:	mesh1=Mesh_Enemy_BeetleSpark
			Case OBJTYPE_BEETLESPRING:	mesh1=Mesh_Enemy_BeetleSpring
			Case OBJTYPE_ACHAOS:		mesh1=Mesh_Enemy_ArtificialChaos
			Case OBJTYPE_ACHAOSBLOB:	mesh1=Mesh_Enemy_ArtificialChaos2
			Case OBJTYPE_RHINO:		mesh1=Mesh_Enemy_Rhino
			Case OBJTYPE_RHINOSPIKES:	mesh1=Mesh_Enemy_RhinoSpikes
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
							mesh1=Mesh_Enemy_Hornet
			Case OBJTYPE_AEROC:		mesh1=Mesh_Enemy_AeroCannon
			Case OBJTYPE_CHASER:		mesh1=Mesh_Enemy_Chaser
			Case OBJTYPE_FIGHTER:		mesh1=Mesh_Enemy_Fighter
			Case OBJTYPE_EGGROBO:		mesh1=Mesh_Enemy_EggRobo
			Case OBJTYPE_CAMERON:		mesh1=Mesh_Enemy_Cameron
			Case OBJTYPE_KLAGEN:		mesh1=Mesh_Enemy_Klagen
			Case OBJTYPE_ORBINAUT:		mesh1=Mesh_Enemy_Orbinaut1
			Case OBJTYPE_TYPHOON:		mesh1=Mesh_Enemy_Typhoon1
			Case OBJTYPE_TYPHOONF:		mesh1=Mesh_Enemy_Typhoon2
			Case OBJTYPE_ANTON:		mesh1=Mesh_Enemy_Anton
			Case OBJTYPE_AQUIS:		mesh1=Mesh_Enemy_Aquis
			Case OBJTYPE_BOMBIE:		mesh1=Mesh_Enemy_Bombie
			Case OBJTYPE_NEWTRON:		mesh1=Mesh_Enemy_Newtron
			Case OBJTYPE_PENGUINATOR:	mesh1=Mesh_Enemy_Penguinator
			Case OBJTYPE_SLICER:		mesh1=Mesh_Enemy_Slicer
			Case OBJTYPE_SNAILB:		mesh1=Mesh_Enemy_SnailBlaster
			Case OBJTYPE_SPIKES:		mesh1=Mesh_Enemy_Spikes
			Case OBJTYPE_ASTERON:		mesh1=Mesh_Enemy_Asteron
			Case OBJTYPE_BATBOT:		mesh1=Mesh_Enemy_Batbot
			Case OBJTYPE_BUBBLS:		mesh1=Mesh_Enemy_Bubbles
			Case OBJTYPE_BUBBLSSPIKES:	mesh1=Mesh_Enemy_Bubbles2
			Case OBJTYPE_STEELION:		mesh1=Mesh_Enemy_Steelion
			Case OBJTYPE_BALKIRY:		mesh1=Mesh_Enemy_Balkiry
			Case OBJTYPE_BURROBOT:		mesh1=Mesh_Enemy_Burrobot
			Case OBJTYPE_CRAWL:		mesh1=Mesh_Enemy_Crawl
			Case OBJTYPE_DRAGONFLY:		mesh1=Mesh_Enemy_Dragonfly
			Case OBJTYPE_MADMOLE:		mesh1=Mesh_Enemy_Madmole
			Case OBJTYPE_MANTA:		mesh1=Mesh_Enemy_Manta
			Case OBJTYPE_MUSHMEANIE:	mesh1=Mesh_Enemy_Mushmeanie
			Case OBJTYPE_OCTUS:		mesh1=Mesh_Enemy_Octus
			Case OBJTYPE_PATABATA:		mesh1=Mesh_Enemy_Patabata
			Case OBJTYPE_ZOOMER:		mesh1=Mesh_Enemy_Zoomer
			Case OBJTYPE_BOO,OBJTYPE_BOOSCARE:
							mesh1=Mesh_Enemy_Boo
			Case OBJTYPE_GHOST:		mesh1=Mesh_Enemy_Ghost
			Case OBJTYPE_BITER:		mesh1=Mesh_Enemy_Biter
			Case OBJTYPE_CRAWLER:		mesh1=Mesh_Enemy_Crawler
			Case OBJTYPE_TAKER:		mesh1=Mesh_Enemy_Taker
			Case OBJTYPE_E1000:		mesh1=Mesh_Enemy_E1000
			Case OBJTYPE_BALLHOG:		mesh1=Mesh_Enemy_BallHog
			Case OBJTYPE_RHINOTANK:		mesh1=Mesh_Enemy_Rhinotank
			Case OBJTYPE_TECHNOSQU:		mesh1=Mesh_Enemy_TechnoSqueek
			Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2:
							mesh1=Mesh_Enemy_BlackWarrior
			Case OBJTYPE_OAKSWORD:		mesh1=Mesh_Enemy_BlackOak
			Case OBJTYPE_LEECH:		mesh1=Mesh_Enemy_BlackLeech
			Case OBJTYPE_WING:		mesh1=Mesh_Enemy_BlackWing
			Case OBJTYPE_SOLDIER:		mesh1=Mesh_Enemy_Soldier
			Case OBJTYPE_SOLDIERCAMO:	mesh1=Mesh_Enemy_Soldier2
			Case OBJTYPE_CATAKILLER:	mesh1=Mesh_Enemy_CatakillerJr
			Case OBJTYPE_CLUCKOID:		mesh1=Mesh_Enemy_Cluckoid
			Case OBJTYPE_MANTIS:		mesh1=Mesh_Enemy_Mantis
			Case OBJTYPE_NEBULA:		mesh1=Mesh_Enemy_Nebula
			Case OBJTYPE_ROLLER:		mesh1=Mesh_Enemy_Roller
			Case OBJTYPE_SHEEP:		mesh1=Mesh_Enemy_Sheep
			Case OBJTYPE_SNOWY:		mesh1=Mesh_Enemy_Snowy
			Case OBJTYPE_SPLATS:		mesh1=Mesh_Enemy_Splats
			Case OBJTYPE_TOXO:		mesh1=Mesh_Enemy_Toxomister
			Case OBJTYPE_HAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_HAMMERHAMMER:
							mesh1=Mesh_Enemy_Hammer
			Case OBJTYPE_WITCH1:		mesh1=Mesh_Enemy_Witch1
			Case OBJTYPE_WITCH2:		mesh1=Mesh_Enemy_Witch2
			Case OBJTYPE_FCANNON1:		mesh1=Mesh_Enemy_FCannon1
			Case OBJTYPE_FCANNON2:		mesh1=Mesh_Enemy_FCannon2
			Case OBJTYPE_FCANNON3:		mesh1=Mesh_Enemy_FCannon3
			Case OBJTYPE_BOMBER1:		mesh1=Mesh_Enemy_Bomber1
			Case OBJTYPE_BOMBER2:		mesh1=Mesh_Enemy_Bomber2
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN:
							mesh1=Mesh_Boss_EggMobile
			Case OBJTYPE_BOSSBETA:		mesh1=Mesh_Boss_Betamk2
			Case OBJTYPE_BOSSMECHA:		mesh1=Mesh_Boss_MechaSonic
			Case OBJTYPE_BOSSROBO:		mesh1=Mesh_Boss_RoboSonic
			Case OBJTYPE_BUBBLES:		mesh1=Mesh_Bubble
			Case OBJTYPE_REDRING:		mesh1=Mesh_RedRing
			Case OBJTYPE_TELEPORTER:	mesh1=Mesh_Teleporter : mesh2=Mesh_Locker
			Case OBJTYPE_TELEPORTER2:	mesh1=Mesh_Teleporter4 : mesh2=Mesh_Locker
			Case OBJTYPE_TELEPORTEREND:	mesh1=Mesh_TeleporterEnd : mesh2=Mesh_Locker
			Case OBJTYPE_OMOCHAO:		mesh1=Mesh_Omochao
			Case OBJTYPE_CANNON:		mesh1=Mesh_Cannon
			Case OBJTYPE_PROPELLER:		mesh1=Mesh_Propeller
			Case OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000:
							mesh1=Mesh_PulleyBase : mesh2=Mesh_Pulley
			Case OBJTYPE_ROCKET:		mesh1=Mesh_RocketBase : mesh2=Mesh_Rocket
			Case OBJTYPE_ELEVATOR:		mesh1=Mesh_Elevator
			Case OBJTYPE_HANDLE:		mesh1=Mesh_Handle
			Case OBJTYPE_FPLAT:		mesh1=Mesh_Platform
			Case OBJTYPE_SWITCH,OBJTYPE_SWITCHWATER:
										mesh1=Mesh_SwitchBase : mesh2=Mesh_SwitchOn
			Case OBJTYPE_SWITCHBASE:	mesh1=Mesh_SwitchClosedBase
			Case OBJTYPE_SWITCHTOP:		mesh1=Mesh_SwitchClosedTop
			Case OBJTYPE_SWITCHAIR:		mesh1=Mesh_SwitchAir
			Case OBJTYPE_ROCK:		mesh1=Mesh_Rock_brown
			Case OBJTYPE_ROCK+1000:		mesh1=Mesh_Rock_grey
			Case OBJTYPE_CRYSTAL:		mesh1=Mesh_Crystal_red
			Case OBJTYPE_ICICLE:		mesh1=Mesh_Icicle1
			Case OBJTYPE_ICICLE+1000:	mesh1=Mesh_Icicle2
			Case OBJTYPE_ICICLEBIG:		mesh1=Mesh_IcicleBig1
			Case OBJTYPE_ICICLEBIG+1000:mesh1=Mesh_IcicleBig2
			Case OBJTYPE_ICEDECOR:		mesh1=Mesh_IceDecor1
			Case OBJTYPE_ICEDECOR+1000:	mesh1=Mesh_IceDecor2
			Case OBJTYPE_AUTO:			mesh1=Mesh_Car_Sedan1
			Case OBJTYPE_HINT:		mesh1=Mesh_Hint
			Case OBJTYPE_COUNTER:		mesh1=Mesh_Counter5
			Case OBJTYPE_SIGN:		mesh1=Mesh_Sign_fall
			Case OBJTYPE_SIGN+1000:		mesh1=Mesh_Sign_up
			Case OBJTYPE_SIGN+2000:		mesh1=Mesh_Sign_down
			Case OBJTYPE_SIGN+3000:		mesh1=Mesh_Sign_left
			Case OBJTYPE_SIGN+4000:		mesh1=Mesh_Sign_right
			Case OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000:
							mesh1=Mesh_Point
			Case OBJTYPE_BELL:		mesh1=Mesh_Bell
			Case OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+3000,OBJTYPE_SPRINKLER+4000,OBJTYPE_SWITCHWATER+1000:
							mesh1=Mesh_Point
			Case OBJTYPE_SPRINKLER+2000:
							mesh1=Mesh_Point : mesh2=Mesh_Locker
			Case OBJTYPE_BUTTERFLY:		mesh1=Mesh_Butterfly1
			Case OBJTYPE_SEAGULL:		mesh1=Mesh_Seagull
			Case OBJTYPE_SEAC:		mesh1=Mesh_Turtle
			Case OBJTYPE_ORCA:		mesh1=Mesh_Orca
			Case OBJTYPE_ORCA+1000:		mesh1=Mesh_Dolphin
			Case OBJTYPE_CHAIR:		mesh1=Mesh_Chair
			Case OBJTYPE_PARASOL:		mesh1=Mesh_Parasol
			Case OBJTYPE_AIRBALLOON:	mesh1=Mesh_AirBalloon1
			Case OBJTYPE_HELICOPTER:	mesh1=Mesh_Helicopter
			Case OBJTYPE_RAINBOW:		mesh1=Mesh_Rainbow
			Case OBJTYPE_CLOUD:		mesh1=Mesh_Cloud
			Case OBJTYPE_POLE:		mesh1=Mesh_Pole
			Case OBJTYPE_EXPLOSION,OBJTYPE_EXPLOSION2:
							mesh1=Mesh_Explosion
			Case OBJTYPE_CAPSULE:	mesh1=Mesh_Capsule
			;-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			Case OBJTYPE_TROPICAL:		mesh1=Mesh_TropicalTrunk : mesh2=Mesh_Tropical
			Case OBJTYPE_TELEPORTER3:	mesh1=Mesh_Teleporter2 : mesh2=Mesh_Locker
			Case OBJTYPE_TELEPORTER4:	mesh1=Mesh_Transporter : mesh2=Mesh_Locker
			Case OBJTYPE_TELEPORTER5:	mesh1=Mesh_Teleporter3 : mesh2=Mesh_Locker
			Case OBJTYPE_TELEPORTER6:	mesh1=Mesh_Teleporter4 : mesh2=Mesh_Locker
			Case OBJTYPE_TRASHCAN:		mesh1=Mesh_TrashCan
			Case OBJTYPE_SACK:		mesh1=Mesh_Sack
			Case OBJTYPE_GARDENPOINT:	mesh1=Mesh_Point : mesh2=Mesh_Locker
			;-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			Case OBJTYPE_TREE1:		mesh1=Mesh_Tree1Trunk : mesh2=Mesh_Tree1
			Case OBJTYPE_TREE2:		mesh1=Mesh_Tree2Trunk : mesh2=Mesh_Tree2
			Case OBJTYPE_TREE3:		mesh1=Mesh_Tree3Trunk : mesh2=Mesh_Tree3
			Case OBJTYPE_TREE4:		mesh1=Mesh_Tree4Trunk : mesh2=Mesh_Tree4
			Case OBJTYPE_TREE5:		mesh1=Mesh_Tree5Trunk : mesh2=Mesh_Tree5
			Case OBJTYPE_TREE6:		mesh1=Mesh_Tree6Trunk : mesh2=Mesh_Tree6
			Case OBJTYPE_SHRUB1:		mesh1=Mesh_Shrub1Trunk : mesh2=Mesh_Shrub1
			Case OBJTYPE_SHRUB2:		mesh1=Mesh_Shrub2
			Case OBJTYPE_SHRUB3:		mesh1=Mesh_Shrub3
			Case OBJTYPE_SHRUB4:		mesh1=Mesh_Shrub4Trunk : mesh2=Mesh_Shrub4
			Case OBJTYPE_SHRUB5:		mesh1=Mesh_Shrub5Trunk : mesh2=Mesh_Shrub5
			Case OBJTYPE_SHRUB6:		mesh1=Mesh_Shrub6
			Case OBJTYPE_BUSH1:		mesh1=Mesh_Bush1
			Case OBJTYPE_BUSH2:		mesh1=Mesh_Bush2
			Case OBJTYPE_BUSH3:		mesh1=Mesh_Bush3
			Case OBJTYPE_BUSH4:		mesh1=Mesh_Bush4
			Case OBJTYPE_BUSH5:		mesh1=Mesh_Bush5
			Case OBJTYPE_BUSH6:		mesh1=Mesh_Bush6
			Case OBJTYPE_BUSH7:		mesh1=Mesh_Tree1
			Case OBJTYPE_GRASS1:		mesh1=Mesh_Grass1
			Case OBJTYPE_GRASS2:		mesh1=Mesh_Grass2
			Case OBJTYPE_GRASS3:		mesh1=Mesh_Grass3
			Case OBJTYPE_GRASS4:		mesh1=Mesh_Grass4
			Case OBJTYPE_GRASS5:		mesh1=Mesh_Grass5
			Case OBJTYPE_GRASS6:		mesh1=Mesh_Grass6
			Case OBJTYPE_GRASS7:		mesh1=Mesh_Grass7
			Case OBJTYPE_GRASS8:		mesh1=Mesh_Grass8
			Case OBJTYPE_GRASS9:		mesh1=Mesh_Grass9
			Case OBJTYPE_GRASS10:		mesh1=Mesh_Grass10
			Case OBJTYPE_SAKURA1:		mesh1=Mesh_Tree1Trunk : mesh2=Mesh_Tree1Sakura
			Case OBJTYPE_SAKURA2:		mesh1=Mesh_Tree2Trunk : mesh2=Mesh_Tree2Sakura
			Case OBJTYPE_SAKURA3:		mesh1=Mesh_Tree3Trunk : mesh2=Mesh_Tree3Sakura
			Case OBJTYPE_SAKURA4:		mesh1=Mesh_Tree4Trunk : mesh2=Mesh_Tree4Sakura
			Case OBJTYPE_SAKURA5:		mesh1=Mesh_Tree5Trunk : mesh2=Mesh_Tree5Sakura
			Case OBJTYPE_SAKURA6:		mesh1=Mesh_Tree6Trunk : mesh2=Mesh_Tree6Sakura
			Case OBJTYPE_PALM1:		mesh1=Mesh_Palm1Trunk : mesh2=Mesh_Palm1
			Case OBJTYPE_PALM2:		mesh1=Mesh_Palm2Trunk : mesh2=Mesh_Palm2
			Case OBJTYPE_PALM3:		mesh1=Mesh_Palm3Trunk : mesh2=Mesh_Palm3
			Case OBJTYPE_PALM4:		mesh1=Mesh_Palm4Trunk : mesh2=Mesh_Palm4
			Case OBJTYPE_WILDPALM1:		mesh1=Mesh_WildPalm1Trunk : mesh2=Mesh_WildPalm1
			Case OBJTYPE_WILDPALM2:		mesh1=Mesh_WildPalm2Trunk : mesh2=Mesh_WildPalm2
			Case OBJTYPE_WILDPALM3:		mesh1=Mesh_WildPalm3Trunk : mesh2=Mesh_WildPalm3
			Case OBJTYPE_WILDPALM4:		mesh1=Mesh_WildPalm4Trunk : mesh2=Mesh_WildPalm4
			Case OBJTYPE_WILDPALM5:		mesh1=Mesh_WildPalm5Trunk : mesh2=Mesh_WildPalm5
			Case OBJTYPE_WILDPALM6:		mesh1=Mesh_WildPalm6Trunk : mesh2=Mesh_WildPalm6
			Case OBJTYPE_FLOWER1:		mesh1=Mesh_Flower1
			Case OBJTYPE_FLOWER2:		mesh1=Mesh_Flower2
			Case OBJTYPE_FLOWER3:		mesh1=Mesh_Flower3
			Case OBJTYPE_FLOWER4:		mesh1=Mesh_Flower4
			Case OBJTYPE_FLOWER5:		mesh1=Mesh_Flower5
			Case OBJTYPE_SNOWY1:		mesh1=Mesh_Tree1Trunk : mesh2=Mesh_Tree1Snowy
			Case OBJTYPE_SNOWY2:		mesh1=Mesh_Tree2Trunk : mesh2=Mesh_Tree2Snowy
			Case OBJTYPE_SNOWY3:		mesh1=Mesh_Tree3Trunk : mesh2=Mesh_Tree3Snowy
			Case OBJTYPE_SNOWY4:		mesh1=Mesh_Tree4Trunk : mesh2=Mesh_Tree4Snowy
			Case OBJTYPE_SNOWY5:		mesh1=Mesh_Tree5Trunk : mesh2=Mesh_Tree5Snowy
			Case OBJTYPE_SNOWY6:		mesh1=Mesh_Tree6Trunk : mesh2=Mesh_Tree6Snowy
			Case OBJTYPE_VINE1:		mesh1=Mesh_Vine1
			Case OBJTYPE_DRYTREE1:		mesh1=Mesh_DryTree1Trunk
			Case OBJTYPE_DRYTREE2:		mesh1=Mesh_DryTree2Trunk
			Case OBJTYPE_DRYTREE3:		mesh1=Mesh_DryTree3Trunk
			Case OBJTYPE_ADABAT1:		mesh1=Mesh_Adabat1
			Case OBJTYPE_ADABAT2:		mesh1=Mesh_Adabat2
			Case OBJTYPE_ADABAT3:		mesh1=Mesh_Adabat3Trunk : mesh2=Mesh_Adabat3
			Case OBJTYPE_ADABAT4:		mesh1=Mesh_Adabat4Trunk : mesh2=Mesh_Adabat4
			Case OBJTYPE_ADABAT5:		mesh1=Mesh_Adabat5Trunk : mesh2=Mesh_Adabat5
		End Select
		Select Game\Interface\DebugNewObj
			Case 0,-1,-2:
				LoadCharacterMesh(p\RealCharacter,1)
				p\Objects\Mesh=CopyEntity(CharacterMesh, Game\Stage\Root)
				DeleteCharacterMesh()
			Default:
				p\Objects\Mesh=CopyEntity(MESHES(SmartEntity(mesh1)), Game\Stage\Root)
		End Select
		p\Objects\Mesh2=CopyEntity(MESHES(SmartEntity(mesh2)), Game\Stage\Root)
		Select Game\Interface\DebugNewObj
			Case OBJTYPE_LOCKER+1000,OBJTYPE_FORCER+1000:
				ScaleEntity p\Objects\Mesh, 2, 2, 2
			Case OBJTYPE_LOCKER+2000:
				ScaleEntity p\Objects\Mesh, 3.5, 3.5, 3.5
			Case OBJTYPE_FAN+1000:
				EntityAlpha p\Objects\Mesh, 0.5
			Case OBJTYPE_BFAN,OBJTYPE_BFANLOW:
				ScaleEntity p\Objects\Mesh, 5.5, 5.5, 5.5
			Case OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
				ScaleEntity p\Objects\Mesh, 5.5, 5.5, 5.5 : EntityAlpha p\Objects\Mesh, 0.5
			Case OBJTYPE_CAR,OBJTYPE_CAR+1000:
				ScaleEntity p\Objects\Mesh2, 0.35, 0.35, 0.35
			Case OBJTYPE_BIKE,OBJTYPE_BIKE+1000:
				ScaleEntity p\Objects\Mesh2, 0.3, 0.3, 0.3
			Case OBJTYPE_BOBSLEIGH,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_CYCLONE,OBJTYPE_CYCLONE+1000:
				ScaleEntity p\Objects\Mesh2, 0.3875, 0.3875, 0.3875
			Case OBJTYPE_TORNADO,OBJTYPE_TORNADO+1000,OBJTYPE_KART,OBJTYPE_KART+1000:
				ScaleEntity p\Objects\Mesh2, 0.2, 0.2, 0.2
			Case OBJTYPE_BOXWOODEN+1000,OBJTYPE_BOXMETAL+1000,OBJTYPE_BOXIRON+1000,OBJTYPE_BOXCAGE+1000,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXTNT+1000,OBJTYPE_BOXNITRO+1000,OBJTYPE_BOXFLOAT+1000:
				ScaleEntity p\Objects\Mesh, 1.5, 1.5, 1.5
			Case OBJTYPE_BOXWOODEN+2000,OBJTYPE_BOXMETAL+2000,OBJTYPE_BOXIRON+2000,OBJTYPE_BOXCAGE+2000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_BOXTNT+2000,OBJTYPE_BOXNITRO+2000,OBJTYPE_BOXFLOAT+2000:
				ScaleEntity p\Objects\Mesh, 1.25, 1.25, 1.25
			Case OBJTYPE_SPRINKLER+2000,OBJTYPE_GARDENPOINT:
				EntityAlpha(p\Objects\Mesh2,0.5)
			Case OBJTYPE_EXPLOSION:
				Animate p\Objects\Mesh, 1, 0.5, 1, 10
			Case OBJTYPE_EXPLOSION2:
				Animate p\Objects\Mesh, 1, 0.5, 2, 10
		End Select
		FreeEntity p\Objects\Mesh3
		Select Game\Interface\DebugNewObj
			Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE:
				p\Objects\Mesh3=CopyEntity(MESHES(Mesh_Line), Game\Stage\Root)
			Default: p\Objects\Mesh3=CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		End Select

		;radius
		Select Game\Interface\DebugNewObj
			Case OBJTYPE_RING,OBJTYPE_GARDENPOINT,OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+2000,OBJTYPE_SPRINKLER+4000:
				EntityRadius(p\Objects\Entity, 2)
			Case OBJTYPE_CHECK,OBJTYPE_CHECK+1000,OBJTYPE_CHECK+2000:
				EntityRadius(p\Objects\Entity, 2)
			Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PROPELLER,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_POLE,OBJTYPE_HANDLE:
				EntityRadius(p\Objects\Entity, 1.75)
			Case OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_FAN+1000,OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000:
				EntityRadius(p\Objects\Entity, 1.85)
			Case OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
				EntityRadius(p\Objects\Entity, 10.175)
			Case OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS
				EntityRadius(p\Objects\Entity, 5)
			Case OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000:
				EntityRadius(p\Objects\Entity, 7)
			Case OBJTYPE_BALLOON,OBJTYPE_HINT,OBJTYPE_BELL:
				EntityRadius(p\Objects\Entity, 4)
			Case OBJTYPE_COUNTER:
				EntityRadius(p\Objects\Entity, 6)
			Case OBJTYPE_SPIKEBALL:
				EntityRadius(p\Objects\Entity, 1.5)
			Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER:
				EntityRadius(p\Objects\Entity, 4.5)
			Case OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP,OBJTYPE_SPIKECYLINDER:
				EntityRadius(p\Objects\Entity, 2.25)
			Case OBJTYPE_GOAL,OBJTYPE_GOAL2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000:
				EntityRadius(p\Objects\Entity, 12.5)
			Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR,OBJTYPE_FPLAT:
				EntityRadius(p\Objects\Entity, 1)
			Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
				EntityRadius(p\Objects\Entity, 6.75)
			Case -1,-2,OBJTYPE_BOXWOODEN,OBJTYPE_BOXMETAL,OBJTYPE_BOXIRON,OBJTYPE_BOXCAGE,OBJTYPE_BOXLIGHT,OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT,OBJTYPE_BOXWOODEN+1000,OBJTYPE_BOXMETAL+1000,OBJTYPE_BOXIRON+1000,OBJTYPE_BOXCAGE+1000,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXTNT+1000,OBJTYPE_BOXNITRO+1000,OBJTYPE_BOXFLOAT+1000,OBJTYPE_BOXWOODEN+2000,OBJTYPE_BOXMETAL+2000,OBJTYPE_BOXIRON+2000,OBJTYPE_BOXCAGE+2000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_BOXTNT+2000,OBJTYPE_BOXNITRO+2000,OBJTYPE_BOXFLOAT+2000,OBJTYPE_BOMBER1,OBJTYPE_BOMBER2:
				EntityRadius(p\Objects\Entity, 2.2)
			Case OBJTYPE_PLATEBUMPER:
				EntityRadius(p\Objects\Entity, 1.5)
			Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				EntityRadius(p\Objects\Entity, 2.5)
			Case OBJTYPE_CATERKILLER,OBJTYPE_MADMOLE:
				EntityRadius(p\Objects\Entity, 1.32)
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
				EntityRadius(p\Objects\Entity, 12.5)
			Case OBJTYPE_SPIKESWING,OBJTYPE_SPIKESWING+1000,OBJTYPE_SPIKESWING+2000,OBJTYPE_SPIKESWINGBALL:
				EntityRadius(p\Objects\Entity, 6.5)
			Case OBJTYPE_REDRING:
				EntityRadius(p\Objects\Entity, 5.5)
			Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTER2,OBJTYPE_TELEPORTER3,OBJTYPE_TELEPORTER4,OBJTYPE_TELEPORTER5,OBJTYPE_TELEPORTER6,OBJTYPE_TELEPORTEREND:
				EntityRadius(p\Objects\Entity, 4.25)
			Case OBJTYPE_OMOCHAO,OBJTYPE_SWITCHTOP:
				EntityRadius(p\Objects\Entity, 1.5)
			Case OBJTYPE_SPIKEBAR,OBJTYPE_SPIKEBAR+1000,OBJTYPE_SPIKEBAR+2000:
				EntityRadius(p\Objects\Entity, 20.5)
			Case OBJTYPE_CANNON:
				EntityRadius(p\Objects\Entity, 9.15)
			Case OBJTYPE_TRASHCAN,OBJTYPE_SACK:
				EntityRadius(p\Objects\Entity, 0.75)
			Case OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000:
				EntityRadius(p\Objects\Entity, 1.25)
			Case OBJTYPE_ROCK:
				EntityRadius(p\Objects\Entity, 5)
			Case OBJTYPE_CRYSTAL:
				EntityRadius(p\Objects\Entity, 3.75)
			Case OBJTYPE_SIGN,OBJTYPE_SIGN+1000,OBJTYPE_SIGN+2000,OBJTYPE_SIGN+3000,OBJTYPE_SIGN+4000:
				EntityRadius(p\Objects\Entity, 8.25)
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA, OBJTYPE_BOSSROBO:
				EntityRadius(p\Objects\Entity, 14)
			Case OBJTYPE_CHAIR,OBJTYPE_PARASOL:
				EntityRadius(p\Objects\Entity, 1.5)
			Case OBJTYPE_ORCA,OBJTYPE_ORCA+1000:
				EntityRadius(p\Objects\Entity, 160)
			Case OBJTYPE_AIRBALLOON,OBJTYPE_HELICOPTER:
				EntityRadius(p\Objects\Entity, 20)
			Case OBJTYPE_RAINBOW:
				EntityRadius(p\Objects\Entity, 30)
			Case OBJTYPE_ACCEL,OBJTYPE_CLOUD:
				EntityRadius(p\Objects\Entity, 9)
			Case OBJTYPE_CAPSULE:
				EntityRadius(p\Objects\Entity, 16.4)
			Default:
				EntityRadius(p\Objects\Entity, 0)
		End Select

		Player_Action_Debug_Power_Limits(p,Game\Interface\DebugNewObj)
	End Function

;---------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------

	Type tTempObject
		Field ObjectNo
		Field ObjectID

		Field x#
		Field y#
		Field z#

		Field pitch#
		Field yaw#
		Field roll#

		Field power#

		Field lock#
		Field lockcontrol#
		Field lockcam#
		Field lockrun#

		Field campos#
		Field camx#
		Field camy#
		Field camz#
		Field campitch#
		Field camyaw#
		Field camroll#
		Field camzoom#
		Field camspeed#

		Field amountcircle#
		Field amount1#
		Field amount2#
		Field amount3#
		Field amountpitch#
		Field amountyaw#
		Field amountspace1#
		Field amountspace2#
		Field amountspace3#

		Field switch1#
		Field switch2#
		Field switch3#
		Field switchstatus#

		Field teleporterno#

		Field hasd#
		Field dx#
		Field dy#
		Field dz#

		Field carnival#
	End Type

	Function ResetTempAttribute()
		TempAttribute\ObjectNo=0
		TempAttribute\ObjectID=0
		TempAttribute\ID#=0

		TempAttribute\x#=0
		TempAttribute\y#=0
		TempAttribute\z#=0

		TempAttribute\pitch#=0
		TempAttribute\yaw#=0
		TempAttribute\roll#=0

		TempAttribute\power#=1

		TempAttribute\lockcontrol#=0
		TempAttribute\lockcam#=0
		TempAttribute\lockrun#=0

		TempAttribute\campos#=0
		TempAttribute\camx#=0
		TempAttribute\camy#=0
		TempAttribute\camz#=0
		TempAttribute\campitch#=0
		TempAttribute\camyaw#=0
		TempAttribute\camroll#=0
		TempAttribute\camzoom#=21
		TempAttribute\camspeed#=30

		TempAttribute\amountcircle#=0
		TempAttribute\amount1#=1
		TempAttribute\amount2#=1
		TempAttribute\amount3#=1
		TempAttribute\amountpitch#=0
		TempAttribute\amountyaw#=0
		TempAttribute\amountspace1#=10
		TempAttribute\amountspace2#=10
		TempAttribute\amountspace3#=10

		TempAttribute\switch1#=0
		TempAttribute\switch2#=0
		TempAttribute\switch3#=0
		TempAttribute\switchstatus#=1

		TempAttribute\teleporterno#=0

		TempAttribute\hasd#=0
		TempAttribute\dx#=0
		TempAttribute\dy#=0
		TempAttribute\dz#=0

		TempAttribute\hint1$=""
		TempAttribute\hint2$=""

		TempAttribute\carnival#=0
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Save(p.tPlayer)

		; Create obj ingame
		TempAttribute\ObjectNo=p\ObjType
		Select p\ObjType
			Case OBJTYPE_CHECK,OBJTYPE_SPIKEBAR,OBJTYPE_ROCK,OBJTYPE_SIGN,OBJTYPE_SPRINKLER,OBJTYPE_SPIKESWING,OBJTYPE_ICICLE,OBJTYPE_ICICLEBIG,OBJTYPE_ICEDECOR:
				CreateObject(1)
			Case OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000,OBJTYPE_BOXCAGE+1000,OBJTYPE_BOXIRON+1000,OBJTYPE_BOXMETAL+1000,OBJTYPE_BOXWOODEN+1000,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXTNT+1000,OBJTYPE_BOXNITRO+1000,OBJTYPE_BOXFLOAT+1000,OBJTYPE_ORCA+1000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-1000
				CreateObject(1)
			Case OBJTYPE_CHECK+1000,OBJTYPE_SPIKEBAR+1000,OBJTYPE_ROCK+1000,OBJTYPE_SIGN+1000,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPIKESWING+1000,OBJTYPE_ICICLE+1000,OBJTYPE_ICICLEBIG+1000,OBJTYPE_ICEDECOR+1000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-1000
				CreateObject(2)
			Case OBJTYPE_CHECK+2000,OBJTYPE_SPIKEBAR+2000,OBJTYPE_SIGN+2000,OBJTYPE_SPRINKLER+2000,OBJTYPE_SPIKESWING+2000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-2000
				CreateObject(3)
			Case OBJTYPE_SIGN+3000,OBJTYPE_SPRINKLER+3000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-3000
				CreateObject(4)
			Case OBJTYPE_SIGN+4000,OBJTYPE_SPRINKLER+4000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-4000
				CreateObject(5)
			Case OBJTYPE_BOXCAGE+2000,OBJTYPE_BOXIRON+2000,OBJTYPE_BOXMETAL+2000,OBJTYPE_BOXWOODEN+2000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_BOXTNT+2000,OBJTYPE_BOXNITRO+2000,OBJTYPE_BOXFLOAT+2000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-2000
				CreateObject(2)
			Case OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000,OBJTYPE_LOCKER+1000,OBJTYPE_FORCER+1000,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_PULLEY+1000,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_SWITCHWATER+1000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-1000
				CreateObject(1)
			Case OBJTYPE_LOCKER+2000,OBJTYPE_TRIGGER_MUSIC+2000:
				TempAttribute\ObjectNo=TempAttribute\ObjectNo-2000
				CreateObject(2)
			Default:
				CreateObject()
		End Select
		For o.tObject=Each tObject
			Objects_Reset_HasMesh(o)
			Objects_Reset_Repose(o)
		Next

		; Gather values
		TempObject.tTempObject=New tTempObject
		TempObject\ObjectNo=p\ObjType
		TempObject\ObjectID=TempAttribute\ObjectID

		TempObject\x#=TempAttribute\x#
		TempObject\y#=TempAttribute\y#
		TempObject\z#=TempAttribute\z#

		TempObject\pitch#=TempAttribute\pitch#
		TempObject\yaw#=TempAttribute\yaw#
		TempObject\roll#=TempAttribute\roll#

		TempObject\power#=TempAttribute\power#

		TempObject\lockcontrol#=TempAttribute\lockcontrol#
		TempObject\lockcam#=TempAttribute\lockcam#
		TempObject\lockrun#=TempAttribute\lockrun#

		TempObject\campos#=TempAttribute\campos#
		TempObject\camx#=TempAttribute\camx#
		TempObject\camy#=TempAttribute\camy#
		TempObject\camz#=TempAttribute\camz#
		TempObject\campitch#=TempAttribute\campitch#
		TempObject\camyaw#=TempAttribute\camyaw#
		TempObject\camroll#=TempAttribute\camroll#
		TempObject\camzoom#=TempAttribute\camzoom#
		TempObject\camspeed#=TempAttribute\camspeed#

		TempObject\amountcircle#=TempAttribute\amountcircle#
		TempObject\amount1#=TempAttribute\amount1#
		TempObject\amount2#=TempAttribute\amount2#
		TempObject\amount3#=TempAttribute\amount3#
		TempObject\amountpitch#=TempAttribute\amountpitch#
		TempObject\amountyaw#=TempAttribute\amountyaw#
		TempObject\amountspace1#=TempAttribute\amountspace1#
		TempObject\amountspace2#=TempAttribute\amountspace2#
		TempObject\amountspace3#=TempAttribute\amountspace3#

		TempObject\switch1#=TempAttribute\switch1#
		TempObject\switch2#=TempAttribute\switch2#
		TempObject\switch3#=TempAttribute\switch3#
		TempObject\switchstatus#=TempAttribute\switchstatus#

		TempObject\teleporterno#=TempAttribute\teleporterno#

		TempObject\hasd#=TempAttribute\hasd#
		TempObject\dx#=TempAttribute\dx#
		TempObject\dy#=TempAttribute\dy#
		TempObject\dz#=TempAttribute\dz#

		TempObject\carnival#=TempAttribute\carnival#

		; Spawned obj
		FlushAll()
		Game\Interface\DebugSpawnedObj=1

	End Function

	Function Player_Action_Debug_SavePlayer(p.tPlayer)
		CreateDir("DEBUGXML")
		xmlout = WriteFile("DEBUGXML\DEBUGXML_PLAYER.xml")
		WriteLine(xmlout, "	<object type="+Chr$(34)+"player"+Chr$(34)+">")
		WriteLine(xmlout, "		<position x="+Chr$(34)+TempAttribute\x#+Chr$(34)+" y="+Chr$(34)+TempAttribute\y#+Chr$(34)+" z="+Chr$(34)+TempAttribute\z#+Chr$(34)+" dir="+Chr$(34)+TempAttribute\yaw#+Chr$(34)+"/>")
		WriteLine(xmlout, "	</object>")
		CloseFile xmlout		
	End Function

	Function Player_Action_Debug_SaveRival(p.tPlayer)
		CreateDir("DEBUGXML")
		xmlout = WriteFile("DEBUGXML\DEBUGXML_RIVAL.xml")
		Select p\ObjType
		Case -1: WriteLine(xmlout, "	<object type="+Chr$(34)+"rival"+Chr$(34)+">")
		Case -2: WriteLine(xmlout, "	<object type="+Chr$(34)+"rivalrun"+Chr$(34)+">")
		End Select
		WriteLine(xmlout, "		<position x="+Chr$(34)+TempAttribute\x#+Chr$(34)+" y="+Chr$(34)+TempAttribute\y#+Chr$(34)+" z="+Chr$(34)+TempAttribute\z#+Chr$(34)+"/>")
		WriteLine(xmlout, "		<rotation pitch="+Chr$(34)+0+Chr$(34)+" yaw="+Chr$(34)+TempAttribute\yaw#+Chr$(34)+" roll="+Chr$(34)+0+Chr$(34)+"/>")
		WriteLine(xmlout, "	</object>")
		CloseFile xmlout		
	End Function

	Function Player_Action_Debug_QuitAndSave(p.tPlayer)
		CreateDir("DEBUGXML")
		If Game\Interface\DebugFileTime=0 Then
		xmlout = WriteFile("DEBUGXML\DEBUGXML.xml")
		Else
		xmlout = WriteFile("DEBUGXML\DEBUGXML"+Game\Interface\DebugFileTime+".xml")
		EndIf
		Game\Interface\DebugFileTime=Game\Interface\DebugFileTime+1

		For TempObject.tTempObject=Each tTempObject
			Select TempObject\ObjectNo
				Case OBJTYPE_RING:		objname$ = "ring"
				Case OBJTYPE_SPRING:		objname$ = "spring"
				Case OBJTYPE_BSPRING:		objname$ = "bspring"
				Case OBJTYPE_SPRINGX:		objname$ = "springx"
				Case OBJTYPE_SPRINGTRAP:	objname$ = "springt"
				Case OBJTYPE_SPRINGTRAPX:	objname$ = "springtx"
				Case OBJTYPE_PAD:		objname$ = "pad"
				Case OBJTYPE_RAMP:		objname$ = "ramp"
				Case OBJTYPE_HOOP:		objname$ = "hoop"
				Case OBJTYPE_THOOP:		objname$ = "thoop"
				Case OBJTYPE_ACCEL:		objname$ = "accel"
				Case OBJTYPE_LOCKER:		objname$ = "lock"
				Case OBJTYPE_LOCKER+1000:	objname$ = "lockb"
				Case OBJTYPE_LOCKER+2000:	objname$ = "lockbb"
				Case OBJTYPE_FORCER:		objname$ = "force"
				Case OBJTYPE_FORCER+1000:	objname$ = "forceb"
				Case OBJTYPE_NODE:		objname$ = "node"
				Case OBJTYPE_FAN:		objname$ = "fan"
				Case OBJTYPE_FAN+1000:		objname$ = "fani"
				Case OBJTYPE_BFAN:		objname$ = "bfan"
				Case OBJTYPE_BFAN+1000:		objname$ = "bfani"
				Case OBJTYPE_BFANLOW:		objname$ = "bfan2"
				Case OBJTYPE_BFANLOW+1000:	objname$ = "bfan2i"
				Case OBJTYPE_CHECK:		objname$ = "check"
				Case OBJTYPE_CHECK+1000:	objname$ = "check2"
				Case OBJTYPE_CHECK+2000:	objname$ = "check3"
				Case OBJTYPE_RINGS:		objname$ = "monitor"
				Case OBJTYPE_LIFE:		objname$ = "life"
				Case OBJTYPE_TRAP:		objname$ = "trap"
				Case OBJTYPE_INVINC:		objname$ = "invinc"
				Case OBJTYPE_SHOES:		objname$ = "shoes"
				Case OBJTYPE_NSHIELD:		objname$ = "nshield"
				Case OBJTYPE_FSHIELD:		objname$ = "fshield"
				Case OBJTYPE_BSHIELD:		objname$ = "bshield"
				Case OBJTYPE_TSHIELD:		objname$ = "tshield"
				Case OBJTYPE_ESHIELD:		objname$ = "eshield"
				Case OBJTYPE_BOMB:		objname$ = "bomb"
				Case OBJTYPE_BOARD:		objname$ = "board"
				Case OBJTYPE_GLIDER:		objname$ = "glider"
				Case OBJTYPE_CAR:		objname$ = "car"
				Case OBJTYPE_BIKE:		objname$ = "bike"
				Case OBJTYPE_BOBSLEIGH:		objname$ = "bobsleigh"
				Case OBJTYPE_TORNADO:		objname$ = "tornado"
				Case OBJTYPE_CYCLONE:		objname$ = "cyclone"
				Case OBJTYPE_KART:			objname$ = "kart"
				Case OBJTYPE_WINGS:		objname$ = "wings"
				Case OBJTYPE_RINGS+1000:	objname$ = "bmonitor"
				Case OBJTYPE_LIFE+1000:		objname$ = "blife"
				Case OBJTYPE_TRAP+1000:		objname$ = "btrap"
				Case OBJTYPE_INVINC+1000:	objname$ = "binvinc"
				Case OBJTYPE_SHOES+1000:	objname$ = "bshoes"
				Case OBJTYPE_NSHIELD+1000:	objname$ = "bnshield"
				Case OBJTYPE_FSHIELD+1000:	objname$ = "bfshield"
				Case OBJTYPE_BSHIELD+1000:	objname$ = "bbshield"
				Case OBJTYPE_TSHIELD+1000:	objname$ = "btshield"
				Case OBJTYPE_ESHIELD+1000:	objname$ = "beshield"
				Case OBJTYPE_BOMB+1000:		objname$ = "bbomb"
				Case OBJTYPE_BOARD+1000:	objname$ = "bboard"
				Case OBJTYPE_GLIDER+1000:	objname$ = "bglider"
				Case OBJTYPE_CAR+1000:		objname$ = "bcar"
				Case OBJTYPE_BIKE+1000:	objname$ = "bbike"
				Case OBJTYPE_BOBSLEIGH+1000:	objname$ = "bbobsleigh"
				Case OBJTYPE_TORNADO+1000:	objname$ = "btornado"
				Case OBJTYPE_CYCLONE+1000:	objname$ = "bcyclone"
				Case OBJTYPE_KART+1000:		objname$ = "bkart"
				Case OBJTYPE_WINGS+1000:	objname$ = "bwings"
				Case OBJTYPE_BALLOON:		objname$ = "balloon"
				Case OBJTYPE_SPIKEBALL:		objname$ = "spikeb"
				Case OBJTYPE_SPIKEBOMB:		objname$ = "spikebo"
				Case OBJTYPE_SPIKECRUSHER:	objname$ = "spikecr"
				Case OBJTYPE_SPIKEDRILL:	objname$ = "spiked"
				Case OBJTYPE_SPIKETIMED:	objname$ = "spiketi"
				Case OBJTYPE_SPIKETRAP:		objname$ = "spiketr"
				Case OBJTYPE_SPIKEBAR:		objname$ = "spikebar1"
				Case OBJTYPE_SPIKEBAR+1000:	objname$ = "spikebar2"
				Case OBJTYPE_SPIKEBAR+2000:	objname$ = "spikebar3"
				Case OBJTYPE_SPIKESWING:	objname$ = "spikesw1"
				Case OBJTYPE_SPIKESWING+1000:	objname$ = "spikesw2"
				Case OBJTYPE_SPIKESWING+2000:	objname$ = "spikesw3"
				Case OBJTYPE_SPIKESWINGBALL: objname$="spikeswx"
				Case OBJTYPE_SPIKECYLINDER:	objname$ = "spikecyl"
				Case OBJTYPE_GOAL:		objname$ = "goal"
				Case OBJTYPE_GOAL2:		objname$ = "goal2"
				Case OBJTYPE_GOAL+1000:	objname$ = "hubgoal"
				Case OBJTYPE_GOAL2+1000:objname$ = "hubgoal2"
				Case OBJTYPE_FLAMESPOUT:	objname$ = "fspout"
				Case OBJTYPE_ICESPOUT:		objname$ = "ispout"
				Case OBJTYPE_SHOCKSPOUT:	objname$ = "sspout"
				Case OBJTYPE_LASERV:		objname$ = "laserv"
				Case OBJTYPE_LASERH:		objname$ = "laserh"
				Case OBJTYPE_RINGGATEV:		objname$ = "laser2v"
				Case OBJTYPE_RINGGATEH:		objname$ = "laser2h"
				Case OBJTYPE_BOXWOODEN:		objname$ = "wbox"
				Case OBJTYPE_BOXMETAL:		objname$ = "mbox"
				Case OBJTYPE_BOXIRON:		objname$ = "ibox"
				Case OBJTYPE_BOXCAGE:		objname$ = "cbox"
				Case OBJTYPE_BOXLIGHT:		objname$ = "lbox"
				Case OBJTYPE_BOXTNT:		objname$ = "tntbox"
				Case OBJTYPE_BOXNITRO:		objname$ = "nbox"
				Case OBJTYPE_BOXFLOAT:		objname$ = "fbox"
				Case OBJTYPE_BOXWOODEN+1000:	objname$ = "bwbox"
				Case OBJTYPE_BOXMETAL+1000:	objname$ = "bmbox"
				Case OBJTYPE_BOXIRON+1000:	objname$ = "bibox"
				Case OBJTYPE_BOXCAGE+1000:	objname$ = "bcbox"
				Case OBJTYPE_BOXLIGHT+1000:	objname$ = "blbox"
				Case OBJTYPE_BOXTNT+1000:	objname$ = "btntbox"
				Case OBJTYPE_BOXNITRO+1000:	objname$ = "bnbox"
				Case OBJTYPE_BOXFLOAT+1000:	objname$ = "bfbox"
				Case OBJTYPE_BOXWOODEN+2000:	objname$ = "mwbox"
				Case OBJTYPE_BOXMETAL+2000:	objname$ = "mmbox"
				Case OBJTYPE_BOXIRON+2000:	objname$ = "mibox"
				Case OBJTYPE_BOXCAGE+2000:	objname$ = "mcbox"
				Case OBJTYPE_BOXLIGHT+2000:	objname$ = "mlbox"
				Case OBJTYPE_BOXTNT+2000:	objname$ = "mtntbox"
				Case OBJTYPE_BOXNITRO+2000:	objname$ = "mnbox"
				Case OBJTYPE_BOXFLOAT+2000:	objname$ = "mfbox"
				Case OBJTYPE_BALLBUMPER:	objname$ = "bbump"
				Case OBJTYPE_GROUNDBUMPER:	objname$ = "gbump"
				Case OBJTYPE_METROBUMPER:	objname$ = "mbump"
				Case OBJTYPE_PLATEBUMPER:	objname$ = "pbump"
				Case OBJTYPE_TRIANGLEBUMPER:	objname$ = "tbump"
				Case OBJTYPE_PADDLE:			objname$ = "paddle"
				Case OBJTYPE_PAWN:			objname$ = "pawn1"
				Case OBJTYPE_PAWNSHIELD:	objname$ = "pawn2"
				Case OBJTYPE_PAWNGUN:		objname$ = "pawn3"
				Case OBJTYPE_PAWNSWORD:		objname$ = "pawn4"
				Case OBJTYPE_FLAPPER:		objname$ = "flapr1"
				Case OBJTYPE_FLAPPERGUN:	objname$ = "flapr2"
				Case OBJTYPE_FLAPPERBOMB:	objname$ = "flapr3"
				Case OBJTYPE_FLAPPERNEEDLE:	objname$ = "flapr4"
				Case OBJTYPE_SPINA:			objname$ = "spina"
				Case OBJTYPE_SPANA:			objname$ = "spana"
				Case OBJTYPE_SPONA:			objname$ = "spona"
				Case OBJTYPE_MOTOBUG:		objname$ = "motobug"
				Case OBJTYPE_CATERKILLER:	objname$ = "caterkiller"
				Case OBJTYPE_BUZZBOMBER:	objname$ = "buzz1"
				Case OBJTYPE_BUZZER:		objname$ = "buzz2"
				Case OBJTYPE_CHOPPER:		objname$ = "chopr"
				Case OBJTYPE_CRABMEAT:		objname$ = "crab"
				Case OBJTYPE_JAWS:			objname$ = "jaws"
				Case OBJTYPE_SPINY:			objname$ = "spiny"
				Case OBJTYPE_GRABBER:		objname$ = "grabr"
				Case OBJTYPE_KIKI:			objname$ = "kiki"
				Case OBJTYPE_COP:			objname$ = "cop"
				Case OBJTYPE_COPRACER:		objname$ = "cop2"
				Case OBJTYPE_HUNTER:		objname$ = "huntr1"
				Case OBJTYPE_HUNTERSHIELD:	objname$ = "huntr2"
				Case OBJTYPE_BEETLE:		objname$ = "beetle1"
				Case OBJTYPE_BEETLEMONO:	objname$ = "beetle2"
				Case OBJTYPE_BEETLESPARK:	objname$ = "beetle3"
				Case OBJTYPE_BEETLESPRING:	objname$ = "beetle4"
				Case OBJTYPE_ACHAOS:		objname$ = "achaos1"
				Case OBJTYPE_ACHAOSBLOB:	objname$ = "achaos2"
				Case OBJTYPE_RHINO:			objname$ = "rhino1"
				Case OBJTYPE_RHINOSPIKES:	objname$ = "rhino2"
				Case OBJTYPE_HORNET3:		objname$ = "hornet3"
				Case OBJTYPE_HORNET6:		objname$ = "hornet6"
				Case OBJTYPE_AEROC:			objname$ = "aeroc"
				Case OBJTYPE_CHASER:		objname$ = "chasr"
				Case OBJTYPE_FIGHTER:		objname$ = "figtr"
				Case OBJTYPE_EGGROBO:		objname$ = "eggrobo"
				Case OBJTYPE_CAMERON:		objname$ = "cameron"
				Case OBJTYPE_KLAGEN:		objname$ = "klagen"
				Case OBJTYPE_ORBINAUT:		objname$ = "orbi"
				Case OBJTYPE_TYPHOON:		objname$ = "typh1"
				Case OBJTYPE_TYPHOONF:		objname$ = "typh2"
				Case OBJTYPE_ANTON:			objname$ = "anton"
				Case OBJTYPE_AQUIS:			objname$ = "aquis"
				Case OBJTYPE_BOMBIE:		objname$ = "bombie"
				Case OBJTYPE_NEWTRON:		objname$ = "newtron"
				Case OBJTYPE_PENGUINATOR:	objname$ = "penguin"
				Case OBJTYPE_SLICER:		objname$ = "slicer"
				Case OBJTYPE_SNAILB:		objname$ = "snailb"
				Case OBJTYPE_SPIKES:		objname$ = "spikess"
				Case OBJTYPE_ASTERON:		objname$ = "asteron"
				Case OBJTYPE_BATBOT:		objname$ = "bat"
				Case OBJTYPE_BUBBLS:		objname$ = "bubbls1"
				Case OBJTYPE_BUBBLSSPIKES:	objname$ = "bubbls2"
				Case OBJTYPE_STEELION:		objname$ = "steelion"
				Case OBJTYPE_BALKIRY:		objname$ = "balki"
				Case OBJTYPE_BURROBOT:		objname$ = "burro"
				Case OBJTYPE_CRAWL:			objname$ = "crawl"
				Case OBJTYPE_DRAGONFLY:		objname$ = "dragonfly"
				Case OBJTYPE_MADMOLE:		objname$ = "madmole"
				Case OBJTYPE_MANTA:			objname$ = "manta"
				Case OBJTYPE_MUSHMEANIE:	objname$ = "mush"
				Case OBJTYPE_OCTUS:			objname$ = "octus"
				Case OBJTYPE_PATABATA:		objname$ = "pata"
				Case OBJTYPE_ZOOMER:		objname$ = "zoomr"
				Case OBJTYPE_BOO:			objname$ = "boo1"
				Case OBJTYPE_BOOSCARE:		objname$ = "boo2"
				Case OBJTYPE_GHOST:			objname$ = "ghost"
				Case OBJTYPE_BITER:			objname$ = "biter"
				Case OBJTYPE_CRAWLER:		objname$ = "crawlr"
				Case OBJTYPE_TAKER:			objname$ = "taker"
				Case OBJTYPE_E1000:			objname$ = "e1000"
				Case OBJTYPE_BALLHOG:		objname$ = "ballh"
				Case OBJTYPE_RHINOTANK:		objname$ = "rhinot"
				Case OBJTYPE_TECHNOSQU:		objname$ = "technosqu"
				Case OBJTYPE_WARRIOR:		objname$ = "warr"
				Case OBJTYPE_WARRIORGUN1:	objname$ = "warr1"
				Case OBJTYPE_WARRIORGUN2:	objname$ = "warr2"
				Case OBJTYPE_OAKSWORD:		objname$ = "oak"
				Case OBJTYPE_LEECH:			objname$ = "leech"
				Case OBJTYPE_WING:			objname$ = "wing"
				Case OBJTYPE_SOLDIER:		objname$ = "soldier1"
				Case OBJTYPE_SOLDIERCAMO:	objname$ = "soldier2"
				Case OBJTYPE_CATAKILLER:	objname$ = "catakiller"
				Case OBJTYPE_CLUCKOID:		objname$ = "cluck"
				Case OBJTYPE_MANTIS:		objname$ = "mantis"
				Case OBJTYPE_NEBULA:		objname$ = "nebula"
				Case OBJTYPE_ROLLER:		objname$ = "roller"
				Case OBJTYPE_SHEEP:			objname$ = "sheep"
				Case OBJTYPE_SNOWY:			objname$ = "snowy"
				Case OBJTYPE_SPLATS:		objname$ = "splats"
				Case OBJTYPE_TOXO:			objname$ = "toxo"
				Case OBJTYPE_HAMMER:		objname$ = "hammr1"
				Case OBJTYPE_HAMMERSHIELD:	objname$ = "hammr2"
				Case OBJTYPE_HAMMERHAMMER:	objname$ = "hammr3"
				Case OBJTYPE_WITCH1:		objname$ = "witch1"
				Case OBJTYPE_WITCH2:		objname$ = "witch2"
				Case OBJTYPE_FCANNON1:		objname$ = "fcannon1"
				Case OBJTYPE_FCANNON2:		objname$ = "fcannon2"
				Case OBJTYPE_FCANNON3:		objname$ = "fcannon3"
				Case OBJTYPE_BOMBER1:		objname$ = "bombr1"
				Case OBJTYPE_BOMBER2:		objname$ = "bombr2"
				Case OBJTYPE_BOSS:			objname$ = "boss"
				Case OBJTYPE_BOSS2:			objname$ = "boss2"
				Case OBJTYPE_BOSSRUN:		objname$ = "bossrun"
				Case OBJTYPE_BOSSBETA:		objname$ = "bossbeta"
				Case OBJTYPE_BOSSMECHA:		objname$ = "bossmecha"
				Case OBJTYPE_BOSSROBO:		objname$ = "bossrobo"
				Case OBJTYPE_BUBBLES:		objname$ = "bubbles"
				Case OBJTYPE_REDRING:		objname$ = "redring"
				Case OBJTYPE_TELEPORTER:	objname$ = "teleporter"
				Case OBJTYPE_TELEPORTER2:	objname$ = "hubteleporter"
				Case OBJTYPE_TELEPORTEREND:	objname$ = "teleporterend"
				Case OBJTYPE_OMOCHAO:		objname$ = "omochao"
				Case OBJTYPE_CANNON:		objname$ = "cannon"
				Case OBJTYPE_PROPELLER:		objname$ = "propeller"
				Case OBJTYPE_PULLEY:		objname$ = "pulley"
				Case OBJTYPE_PULLEY+1000:	objname$ = "pulley2"
				Case OBJTYPE_ROCKET:		objname$ = "rocket"
				Case OBJTYPE_ELEVATOR:		objname$ = "elev"
				Case OBJTYPE_HANDLE:		objname$ = "handle"
				Case OBJTYPE_FPLAT:			objname$ = "fplat"
				Case OBJTYPE_SWITCH:		objname$ = "switch"
				Case OBJTYPE_SWITCHBASE:	objname$ = "switchbase"
				Case OBJTYPE_SWITCHTOP:		objname$ = "switchtop"
				Case OBJTYPE_SWITCHWATER:	objname$ = "switchwa"
				Case OBJTYPE_SWITCHWATER+1000:objname$ = "switchwai"
				Case OBJTYPE_SWITCHAIR:		objname$ = "switch2"
				Case OBJTYPE_ROCK:		objname$ = "rock1"
				Case OBJTYPE_ROCK+1000:		objname$ = "rock2"
				Case OBJTYPE_CRYSTAL:		objname$ = "crystal"
				Case OBJTYPE_ICICLE:		objname$ = "icicle1"
				Case OBJTYPE_ICICLE+1000:	objname$ = "icicle2"
				Case OBJTYPE_ICICLEBIG:		objname$ = "iciclebig1"
				Case OBJTYPE_ICICLEBIG+1000:objname$ = "iciclebig2"
				Case OBJTYPE_ICEDECOR:		objname$ = "icedecor1"
				Case OBJTYPE_ICEDECOR+1000:	objname$ = "icedecor2"
				Case OBJTYPE_AUTO:		objname$ = "auto"
				Case OBJTYPE_HINT:		objname$ = "hint"
				Case OBJTYPE_COUNTER:		objname$ = "counter"
				Case OBJTYPE_SIGN:		objname$ = "signfall"
				Case OBJTYPE_SIGN+1000:		objname$ = "signup"
				Case OBJTYPE_SIGN+2000:		objname$ = "signdown"
				Case OBJTYPE_SIGN+3000:		objname$ = "signleft"
				Case OBJTYPE_SIGN+4000:		objname$ = "signright"
				Case OBJTYPE_TRIGGER_VEHICLECANCEL:	objname$ = "vehiclecancel"
				Case OBJTYPE_TRIGGER_MACH:	objname$ = "mach"
				Case OBJTYPE_TRIGGER_MACHCANCEL:	objname$ = "machcancel"
				Case OBJTYPE_TRIGGER_SKYDIVE:	objname$ = "skydive"
				Case OBJTYPE_TRIGGER_SKYDIVECANCEL:	objname$ = "skydivecancel"
				Case OBJTYPE_TRIGGER_WATER:	objname$ = "water"
				Case OBJTYPE_TRIGGER_MUSIC:	objname$ = "music1"
				Case OBJTYPE_TRIGGER_MUSIC+1000: objname$ = "music2"
				Case OBJTYPE_TRIGGER_MUSIC+2000: objname$ = "music3"
				Case OBJTYPE_BELL:		objname$ = "bell"
				Case OBJTYPE_SPRINKLER:		objname$ = "sprinkler1"
				Case OBJTYPE_SPRINKLER+1000:	objname$ = "sprinkler2"
				Case OBJTYPE_SPRINKLER+2000:	objname$ = "sprinkler3"
				Case OBJTYPE_SPRINKLER+3000:	objname$ = "drops"
				Case OBJTYPE_SPRINKLER+4000:	objname$ = "sprinkler1x"
				Case OBJTYPE_BUTTERFLY:		objname$ = "butterfly"
				Case OBJTYPE_SEAGULL:		objname$ = "seagull"
				Case OBJTYPE_SEAC:		objname$ = "seac"
				Case OBJTYPE_ORCA:		objname$ = "orca"
				Case OBJTYPE_ORCA+1000:		objname$ = "dolphin"
				Case OBJTYPE_CHAIR:		objname$ = "chair"
				Case OBJTYPE_PARASOL:		objname$ = "parasol"
				Case OBJTYPE_AIRBALLOON:		objname$ = "airballoon"
				Case OBJTYPE_HELICOPTER:		objname$ = "helicopter"
				Case OBJTYPE_RAINBOW:		objname$ = "rainbow"
				Case OBJTYPE_CLOUD:		objname$ = "cloud"
				Case OBJTYPE_POLE:		objname$ = "pole"
				Case OBJTYPE_EXPLOSION:		objname$ = "explosion"
				Case OBJTYPE_EXPLOSION2:	objname$ = "explosion2"
				Case OBJTYPE_CAPSULE:	objname$ = "capsule"
				;-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				Case OBJTYPE_TROPICAL:		objname$ = "tropic"
				Case OBJTYPE_TELEPORTER3:	objname$ = "bmteleporter"
				Case OBJTYPE_TELEPORTER4:	objname$ = "tteleporter"
				Case OBJTYPE_TELEPORTER5:	objname$ = "steleporter"
				Case OBJTYPE_TELEPORTER6:	objname$ = "pteleporter"
				Case OBJTYPE_TRASHCAN:		objname$ = "trashcan"
				Case OBJTYPE_SACK:		objname$ = "sack"
				Case OBJTYPE_GARDENPOINT:	objname$ = "gardenpoint"
				;-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				Case OBJTYPE_TREE1:		objname$ = "tree1"
				Case OBJTYPE_TREE2:		objname$ = "tree2"
				Case OBJTYPE_TREE3:		objname$ = "tree3"
				Case OBJTYPE_TREE4:		objname$ = "tree4"
				Case OBJTYPE_TREE5:		objname$ = "tree5"
				Case OBJTYPE_TREE6:		objname$ = "tree6"
				Case OBJTYPE_SHRUB1:		objname$ = "shrub1"
				Case OBJTYPE_SHRUB2:		objname$ = "shrub2"
				Case OBJTYPE_SHRUB3:		objname$ = "shrub3"
				Case OBJTYPE_SHRUB4:		objname$ = "shrub4"
				Case OBJTYPE_SHRUB5:		objname$ = "shrub5"
				Case OBJTYPE_SHRUB6:		objname$ = "shrub6"
				Case OBJTYPE_BUSH1:		objname$ = "bush1"
				Case OBJTYPE_BUSH2:		objname$ = "bush2"
				Case OBJTYPE_BUSH3:		objname$ = "bush3"
				Case OBJTYPE_BUSH4:		objname$ = "bush4"
				Case OBJTYPE_BUSH5:		objname$ = "bush5"
				Case OBJTYPE_BUSH6:		objname$ = "bush6"
				Case OBJTYPE_BUSH7:		objname$ = "bush7"
				Case OBJTYPE_GRASS1:		objname$ = "grass1"
				Case OBJTYPE_GRASS2:		objname$ = "grass2"
				Case OBJTYPE_GRASS3:		objname$ = "grass3"
				Case OBJTYPE_GRASS4:		objname$ = "grass4"
				Case OBJTYPE_GRASS5:		objname$ = "grass5"
				Case OBJTYPE_GRASS6:		objname$ = "grass6"
				Case OBJTYPE_GRASS7:		objname$ = "grass7"
				Case OBJTYPE_GRASS8:		objname$ = "grass8"
				Case OBJTYPE_GRASS9:		objname$ = "grass9"
				Case OBJTYPE_GRASS10:		objname$ = "grass10"
				Case OBJTYPE_SAKURA1:		objname$ = "sakura1"
				Case OBJTYPE_SAKURA2:		objname$ = "sakura2"
				Case OBJTYPE_SAKURA3:		objname$ = "sakura3"
				Case OBJTYPE_SAKURA4:		objname$ = "sakura4"
				Case OBJTYPE_SAKURA5:		objname$ = "sakura5"
				Case OBJTYPE_SAKURA6:		objname$ = "sakura6"
				Case OBJTYPE_PALM1:		objname$ = "palm1"
				Case OBJTYPE_PALM2:		objname$ = "palm2"
				Case OBJTYPE_PALM3:		objname$ = "palm3"
				Case OBJTYPE_PALM4:		objname$ = "palm4"
				Case OBJTYPE_WILDPALM1:		objname$ = "wildpalm1"
				Case OBJTYPE_WILDPALM2:		objname$ = "wildpalm2"
				Case OBJTYPE_WILDPALM3:		objname$ = "wildpalm3"
				Case OBJTYPE_WILDPALM4:		objname$ = "wildpalm4"
				Case OBJTYPE_WILDPALM5:		objname$ = "wildpalm5"
				Case OBJTYPE_WILDPALM6:		objname$ = "wildpalm6"
				Case OBJTYPE_FLOWER1:		objname$ = "flower1"
				Case OBJTYPE_FLOWER2:		objname$ = "flower2"
				Case OBJTYPE_FLOWER3:		objname$ = "flower3"
				Case OBJTYPE_FLOWER4:		objname$ = "flower4"
				Case OBJTYPE_FLOWER5:		objname$ = "flower5"
				Case OBJTYPE_SNOWY1:		objname$ = "snowy1"
				Case OBJTYPE_SNOWY2:		objname$ = "snowy2"
				Case OBJTYPE_SNOWY3:		objname$ = "snowy3"
				Case OBJTYPE_SNOWY4:		objname$ = "snowy4"
				Case OBJTYPE_SNOWY5:		objname$ = "snowy5"
				Case OBJTYPE_SNOWY6:		objname$ = "snowy6"
				Case OBJTYPE_VINE1:		objname$ = "vine1"
				Case OBJTYPE_DRYTREE1:		objname$ = "drytree1"
				Case OBJTYPE_DRYTREE2:		objname$ = "drytree2"
				Case OBJTYPE_DRYTREE3:		objname$ = "drytree3"
				Case OBJTYPE_ADABAT1:		objname$ = "adabat1"
				Case OBJTYPE_ADABAT2:		objname$ = "adabat2"
				Case OBJTYPE_ADABAT3:		objname$ = "adabat3"
				Case OBJTYPE_ADABAT4:		objname$ = "adabat4"
				Case OBJTYPE_ADABAT5:		objname$ = "adabat5"
			End Select
			WriteLine(xmlout, "	<object type="+Chr$(34)+objname$+Chr$(34)+">")

			WriteLine(xmlout, "		<position x="+Chr$(34)+TempObject\x#+Chr$(34)+" y="+Chr$(34)+TempObject\y#+Chr$(34)+" z="+Chr$(34)+TempObject\z#+Chr$(34)+"/>")

			Select TempObject\ObjectNo
				Case OBJTYPE_RING,OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS,OBJTYPE_RINGS+1000,OBJTYPE_LIFE+1000,OBJTYPE_TRAP+1000,OBJTYPE_INVINC+1000,OBJTYPE_SHOES+1000,OBJTYPE_NSHIELD+1000,OBJTYPE_FSHIELD+1000,OBJTYPE_BSHIELD+1000,OBJTYPE_TSHIELD+1000,OBJTYPE_ESHIELD+1000,OBJTYPE_BOMB+1000,OBJTYPE_BOARD+1000,OBJTYPE_GLIDER+1000,OBJTYPE_CAR+1000,OBJTYPE_BIKE+1000,OBJTYPE_BOBSLEIGH+1000,OBJTYPE_TORNADO+1000,OBJTYPE_CYCLONE+1000,OBJTYPE_KART+1000,OBJTYPE_WINGS+1000,OBJTYPE_BALLOON,OBJTYPE_GOAL,OBJTYPE_GOAL2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000,OBJTYPE_BUBBLES,OBJTYPE_REDRING,OBJTYPE_HINT,OBJTYPE_COUNTER,OBJTYPE_BELL,OBJTYPE_SPRINKLER,OBJTYPE_SPRINKLER+1000,OBJTYPE_SPRINKLER+3000,OBJTYPE_SPRINKLER+4000,OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_AIRBALLOON,OBJTYPE_CLOUD,OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_BOMBER2:
				Default:
					WriteLine(xmlout, "		<rotation pitch="+Chr$(34)+TempObject\pitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\yaw#+Chr$(34)+" roll="+Chr$(34)+TempObject\roll#+Chr$(34)+"/>")
			End Select

			If TempObject\amountcircle#=0 Then
				If TempObject\amount1#>1 Or TempObject\amount2#>1 Or TempObject\amount3#>1 Then
					If TempObject\amount2#>1 Or TempObject\amount3#>1 Then
						If TempObject\amountspace2#=TempObject\amountspace1# and TempObject\amountspace3#=TempObject\amountspace1# Then
							WriteLine(xmlout, "		<obj amount1="+Chr$(34)+TempObject\amount1#+Chr$(34)+" amount2="+Chr$(34)+TempObject\amount2#+Chr$(34)+" amount3="+Chr$(34)+TempObject\amount3#+Chr$(34)+" pitch="+Chr$(34)+TempObject\amountpitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\amountyaw#+Chr$(34)+" space="+Chr$(34)+TempObject\amountspace1#+Chr$(34)+"/>")
						Else
							WriteLine(xmlout, "		<obj amount1="+Chr$(34)+TempObject\amount1#+Chr$(34)+" amount2="+Chr$(34)+TempObject\amount2#+Chr$(34)+" amount3="+Chr$(34)+TempObject\amount3#+Chr$(34)+" pitch="+Chr$(34)+TempObject\amountpitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\amountyaw#+Chr$(34)+" space1="+Chr$(34)+TempObject\amountspace1#+Chr$(34)+" space2="+Chr$(34)+TempObject\amountspace2#+Chr$(34)+" space3="+Chr$(34)+TempObject\amountspace3#+Chr$(34)+"/>")
						EndIf
					Else
						WriteLine(xmlout, "		<obj amount="+Chr$(34)+TempObject\amount1#+Chr$(34)+" pitch="+Chr$(34)+TempObject\amountpitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\amountyaw#+Chr$(34)+" space="+Chr$(34)+TempObject\amountspace1#+Chr$(34)+"/>")
					EndIf
				EndIf
			Else
				If TempObject\amount1#>1 Then
					If TempObject\amount3#>1 Then
						If TempObject\amountspace3#=TempObject\amountspace1# Then
							WriteLine(xmlout, "		<obj circle="+Chr$(34)+TempObject\amountcircle#+Chr$(34)+" amount1="+Chr$(34)+TempObject\amount1#+Chr$(34)+" amount3="+Chr$(34)+TempObject\amount3#+Chr$(34)+" pitch="+Chr$(34)+TempObject\amountpitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\amountyaw#+Chr$(34)+" space="+Chr$(34)+TempObject\amountspace1#+Chr$(34)+"/>")
						Else
							WriteLine(xmlout, "		<obj circle="+Chr$(34)+TempObject\amountcircle#+Chr$(34)+" amount1="+Chr$(34)+TempObject\amount1#+Chr$(34)+" amount3="+Chr$(34)+TempObject\amount3#+Chr$(34)+" pitch="+Chr$(34)+TempObject\amountpitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\amountyaw#+Chr$(34)+" space1="+Chr$(34)+TempObject\amountspace1#+Chr$(34)+" space3="+Chr$(34)+TempObject\amountspace3#+Chr$(34)+"/>")
						EndIf
					Else
						WriteLine(xmlout, "		<obj circle="+Chr$(34)+TempObject\amountcircle#+Chr$(34)+" amount="+Chr$(34)+TempObject\amount1#+Chr$(34)+" pitch="+Chr$(34)+TempObject\amountpitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\amountyaw#+Chr$(34)+" space="+Chr$(34)+TempObject\amountspace1#+Chr$(34)+"/>")
					EndIf
				EndIf
			EndIf

			Select TempObject\ObjectNo
				Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_LOCKER+1000,OBJTYPE_LOCKER+2000,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000,OBJTYPE_CANNON:
					Select TempObject\ObjectNo
						Case OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
						Default:
							WriteLine(xmlout, "		<power is="+Chr$(34)+TempObject\power#+Chr$(34)+"/>")
					End Select
					If Not(TempObject\ObjectNo=OBJTYPE_CANNON) Then
						WriteLine(xmlout, "		<lock control="+Chr$(34)+TempObject\lockcontrol+Chr$(34)+" cam="+Chr$(34)+TempObject\lockcam+Chr$(34)+" run="+Chr$(34)+TempObject\lockrun+Chr$(34)+"/>")
						If TempObject\campos#=0 Then
							Select TempObject\ObjectNo
								Case OBJTYPE_NODE:
									WriteLine(xmlout, "		<cam pitch="+Chr$(34)+TempObject\campitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\yaw#+Chr$(34)+" roll="+Chr$(34)+TempObject\camroll#+Chr$(34)+" zoom="+Chr$(34)+TempObject\camzoom#+Chr$(34)+" speed="+Chr$(34)+TempObject\camspeed#+Chr$(34)+"/>")
								Default:
									WriteLine(xmlout, "		<cam pitch="+Chr$(34)+TempObject\campitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\camyaw#+Chr$(34)+" roll="+Chr$(34)+TempObject\camroll#+Chr$(34)+" zoom="+Chr$(34)+TempObject\camzoom#+Chr$(34)+" speed="+Chr$(34)+TempObject\camspeed#+Chr$(34)+"/>")
							End Select
						Else
							WriteLine(xmlout, "		<cam pos="+Chr$(34)+TempObject\campos+Chr$(34)+" x="+Chr$(34)+TempObject\camx#+Chr$(34)+" y="+Chr$(34)+TempObject\camy#+Chr$(34)+" z="+Chr$(34)+TempObject\camz#+Chr$(34)+" pitch="+Chr$(34)+TempObject\campitch#+Chr$(34)+" yaw="+Chr$(34)+TempObject\camyaw#+Chr$(34)+" roll="+Chr$(34)+TempObject\camroll#+Chr$(34)+" zoom="+Chr$(34)+TempObject\camzoom#+Chr$(34)+" speed="+Chr$(34)+TempObject\camspeed#+Chr$(34)+"/>")
						EndIf
						Select TempObject\ObjectNo
							Case OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_FAN+1000,OBJTYPE_BFAN+1000,OBJTYPE_BFANLOW+1000:
							Default:
								If TempObject\hasd=1 Then
									WriteLine(xmlout, "		<d has="+Chr$(34)+TempObject\hasd+Chr$(34)+" x="+Chr$(34)+TempObject\dx#+Chr$(34)+" y="+Chr$(34)+TempObject\dy#+Chr$(34)+" z="+Chr$(34)+TempObject\dz#+Chr$(34)+"/>")
								Else
									WriteLine(xmlout, "		<d has="+Chr$(34)+TempObject\hasd+Chr$(34)+" x="+Chr$(34)+0+Chr$(34)+" y="+Chr$(34)+0+Chr$(34)+" z="+Chr$(34)+0+Chr$(34)+"/>")
								EndIf
						End Select
					EndIf
				Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
					WriteLine(xmlout, "		<power is="+Chr$(34)+TempObject\power#+Chr$(34)+"/>")
				Case OBJTYPE_AIRBALLOON,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH,OBJTYPE_CLOUD,OBJTYPE_POLE,OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
					WriteLine(xmlout, "		<power is="+Chr$(34)+TempObject\power#+Chr$(34)+"/>")
				Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
					WriteLine(xmlout, "		<power is="+Chr$(34)+TempObject\power#+Chr$(34)+"/>")
					WriteLine(xmlout, "		<d has="+Chr$(34)+1+Chr$(34)+" x="+Chr$(34)+TempObject\dx#+Chr$(34)+" y="+Chr$(34)+TempObject\dy#+Chr$(34)+" z="+Chr$(34)+TempObject\dz#+Chr$(34)+"/>")
			End Select

			Select TempObject\ObjectNo
				Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE:
					WriteLine(xmlout, "		<switch no="+Chr$(34)+TempObject\switch1#+Chr$(34)+" status="+Chr$(34)+TempObject\switchstatus+Chr$(34)+"/>")
				Case OBJTYPE_SWITCHTOP:
					WriteLine(xmlout, "		<switch no="+Chr$(34)+TempObject\switch1#+Chr$(34)+"/>")
				Case OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
					If TempObject\switch2#>0 Or TempObject\switch3#>0 Then
						WriteLine(xmlout, "		<switch no="+Chr$(34)+TempObject\switch1#+Chr$(34)+" no2="+Chr$(34)+TempObject\switch2#+Chr$(34)+" no3="+Chr$(34)+TempObject\switch3#+Chr$(34)+"/>")
					Else
						WriteLine(xmlout, "		<switch no="+Chr$(34)+TempObject\switch1#+Chr$(34)+"/>")
					EndIf
				Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2:
					If TempObject\switch1#>0 Then WriteLine(xmlout, "		<switch no="+Chr$(34)+TempObject\switch1#+Chr$(34)+"/>")
			End Select

			Select TempObject\ObjectNo
				Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTEREND:
					WriteLine(xmlout, "		<teleporter no="+Chr$(34)+TempObject\teleporterno#+Chr$(34)+"/>")
				Case OBJTYPE_TELEPORTER2,OBJTYPE_GOAL+1000,OBJTYPE_GOAL2+1000:
					WriteLine(xmlout, "		<teleporter name="+Chr$(34)+Chr$(34)+"/>")
				Case OBJTYPE_HINT:
					WriteLine(xmlout, "		<hint line1="+Chr$(34)+Chr$(34)+" line2="+Chr$(34)+Chr$(34)+"/>")
			End Select

			WriteLine(xmlout, "		<ID is="+Chr$(34)+TempObject\ObjectID+Chr$(34)+"/>")

			WriteLine(xmlout, "	</object>")

			Delete TempObject
		Next

		CloseFile xmlout

	End Function

;---------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------

	Function Player_Action_Debug_DebugSpeedChanging()
		If Input\Pressed\ActionAct Then
			Select Game\Interface\DebugSpeed#
				Case 0.1: Game\Interface\DebugSpeed#=1
				Case 1: Game\Interface\DebugSpeed#=5
				Case 5: Game\Interface\DebugSpeed#=10
				Case 10: Game\Interface\DebugSpeed#=0.1
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf
	End Function

	Function Player_Action_Debug_DebugSpeed2Changing()
		If Input\Pressed\ActionAct Then
			Select Game\Interface\DebugSpeed2#
				Case 0.1: Game\Interface\DebugSpeed2#=1
				Case 1: Game\Interface\DebugSpeed2#=5
				Case 5: Game\Interface\DebugSpeed2#=0.1
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Position(p.tPlayer, d.tDeltaTime)

		If Input\Pressed\ActionSkill2 Then Game\Interface\DebugMoveType=abs(Game\Interface\DebugMoveType-1) : PlaySmartSound(Sound_MenuMove)

		Player_Action_Debug_DebugSpeedChanging()

		If Input\Pressed\ActionDrift Then
			Game\Interface\DebugCollision=abs(Game\Interface\DebugCollision-1)
			Select Game\Interface\DebugCollision
				Case 1: EntityType(p\Objects\Entity,COLLISION_PLAYER)
				Case 0: EntityType(p\Objects\Entity,COLLISION_NONE)
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		Select Game\Interface\DebugMoveType
			Case 0:
				If Input\Hold\Up Then Player_SetSpeed(p,Game\Interface\DebugSpeed#*d\Delta)
				If Input\Hold\Down Then Player_SetSpeed(p,Game\Interface\DebugSpeed#*d\Delta)
				If Input\Hold\ActionJump Then p\Motion\Speed\y#=Game\Interface\DebugSpeed#*d\Delta
				If Input\Hold\ActionRoll Then p\Motion\Speed\y#=-Game\Interface\DebugSpeed#*d\Delta
			Case 1:
				If Input\Hold\Up Then MoveEntity p\Objects\Entity,0,0,Game\Interface\DebugSpeed#*d\Delta
				If Input\Hold\Down Then MoveEntity p\Objects\Entity,0,0,-Game\Interface\DebugSpeed#*d\Delta
				If Input\Hold\Left Then MoveEntity p\Objects\Entity,-Game\Interface\DebugSpeed#*d\Delta,0,0
				If Input\Hold\Right Then MoveEntity p\Objects\Entity,Game\Interface\DebugSpeed#*d\Delta,0,0
				If Input\Hold\ActionJump Then MoveEntity p\Objects\Entity,0,Game\Interface\DebugSpeed#*d\Delta,0
				If Input\Hold\ActionRoll Then MoveEntity p\Objects\Entity,0,-Game\Interface\DebugSpeed#*d\Delta,0
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Rotation(p.tPlayer, d.tDeltaTime)

		Player_Action_Debug_DebugSpeedChanging()

		If Input\Pressed\ActionDrift Then
			TempAttribute\pitch#=0
			TempAttribute\yaw#=0
			TempAttribute\roll#=0
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Hold\Up Then TempAttribute\pitch#=TempAttribute\pitch#+Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Down Then TempAttribute\pitch#=TempAttribute\pitch#-Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Left Then TempAttribute\yaw#=TempAttribute\yaw#+Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Right Then TempAttribute\yaw#=TempAttribute\yaw#-Game\Interface\DebugSpeed#*d\Delta

		If Input\Pressed\ActionJump Then
			Select TempAttribute\pitch#
				Case 0: TempAttribute\pitch#=45
				Case 45: TempAttribute\pitch#=90
				Case 90: TempAttribute\pitch#=135
				Case 135: TempAttribute\pitch#=180
				Case 180: TempAttribute\pitch#=0
				Default: TempAttribute\pitch#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf
		If Input\Pressed\ActionRoll Then
			Select TempAttribute\yaw#
				Case 0: TempAttribute\yaw#=45
				Case 45: TempAttribute\yaw#=90
				Case 90: TempAttribute\yaw#=135
				Case 135: TempAttribute\yaw#=180
				Case -180,180: TempAttribute\yaw#=-135
				Case -135: TempAttribute\yaw#=-90
				Case -90: TempAttribute\yaw#=-45
				Case -45: TempAttribute\yaw#=0
				Default: TempAttribute\yaw#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Power_Limits(p.tPlayer, objtype)
		Select objtype
			Case OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
				If TempAttribute\power#<0.0 Then TempAttribute\power#=0.0
			Case OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000:
			Default:
				If TempAttribute\power#<1.0 Then TempAttribute\power#=1.0
				If TempAttribute\power#>20.0 Then TempAttribute\power#=20.0
		End Select
	End Function

	Function Player_Action_Debug_Power(p.tPlayer)

		Player_Action_Debug_Power_Limits(p,p\ObjType)

		Select p\ObjType
			Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
				ScaleEntity p\Objects\Mesh, 12*TempAttribute\power#, 12*TempAttribute\power#, 12*TempAttribute\power#
				ScaleEntity p\Objects\Mesh2, 12*TempAttribute\power#, 12*TempAttribute\power#, 12*TempAttribute\power#
		End Select

		Player_Action_Debug_DebugSpeed2Changing()

		If Input\Pressed\ActionDrift Then
			Select p\ObjType
				Case OBJTYPE_SPRING,OBJTYPE_SPRINGTRAP,OBJTYPE_BSPRING,OBJTYPE_ACCEL: TempAttribute\power# = 2
				Case OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAPX: TempAttribute\power# = 1.2
				Case OBJTYPE_PAD,OBJTYPE_FORCER,OBJTYPE_FORCER+1000,OBJTYPE_NODE,OBJTYPE_NODE2: TempAttribute\power# = 4.8
				Case OBJTYPE_RAMP: TempAttribute\power# = 2.5
				Case OBJTYPE_HOOP,OBJTYPE_THOOP: TempAttribute\power# = 1.8
				Case OBJTYPE_CANNON: TempAttribute\power# = 1.5
				Case OBJTYPE_PROPELLER: TempAttribute\power# = 1.325
				Case OBJTYPE_PULLEY,OBJTYPE_PULLEY+1000: TempAttribute\power# = 0.75
				Case OBJTYPE_ROCKET: TempAttribute\power# = 2.5
				Case OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_MUSIC+1000,OBJTYPE_TRIGGER_MUSIC+2000: TempAttribute\power# = 20
				Case OBJTYPE_CLOUD,OBJTYPE_POLE: TempAttribute\power# = 1.5
				Case OBJTYPE_SWITCHWATER,OBJTYPE_SWITCHWATER+1000: TempAttribute\power# = 0
				Default: TempAttribute\power# = 1
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Pressed\ActionJump Then TempAttribute\power#=TempAttribute\power#+Game\Interface\DebugSpeed2#
		If Input\Pressed\ActionRoll Then TempAttribute\power#=TempAttribute\power#-Game\Interface\DebugSpeed2#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Locks(p.tPlayer)

		If Input\Pressed\ActionJump Then PlaySmartSound(Sound_MenuMove) : TempAttribute\lockcontrol#=TempAttribute\lockcontrol#+1
		If Input\Pressed\ActionRoll Then PlaySmartSound(Sound_MenuMove) : TempAttribute\lockcam#=TempAttribute\lockcam#+1
		If Input\Pressed\ActionSkill2 Then PlaySmartSound(Sound_MenuMove) : TempAttribute\lockrun#=TempAttribute\lockrun#+1

		If TempAttribute\lockcontrol#>5 Then TempAttribute\lockcontrol#=0
		If TempAttribute\lockcam#>5 Then TempAttribute\lockcam#=0
		If TempAttribute\lockrun#>3 Then TempAttribute\lockrun#=0

		If Input\Pressed\ActionDrift Then
			TempAttribute\lockcontrol#=0
			TempAttribute\lockcam#=0
			TempAttribute\lockrun#=0
			PlaySmartSound(Sound_MenuMove)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_CamPosition(p.tPlayer, d.tDeltaTime)

		PositionEntity p\Objects\Mesh, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh2, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh3, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1

		TempAttribute\camx#=p\Objects\Position\x#
		TempAttribute\camy#=p\Objects\Position\y#
		TempAttribute\camz#=p\Objects\Position\z#

		If Input\Pressed\ActionSkill2 Then Game\Interface\DebugMoveType=abs(Game\Interface\DebugMoveType-1) : PlaySmartSound(Sound_MenuMove)

		Player_Action_Debug_DebugSpeedChanging()

		If Game\Interface\DebugCollision=1 Then
			Game\Interface\DebugCollision=abs(Game\Interface\DebugCollision-1)
			Select Game\Interface\DebugCollision
				Case 1: EntityType(p\Objects\Entity,COLLISION_PLAYER)
				Case 0: EntityType(p\Objects\Entity,COLLISION_NONE)
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		Select Game\Interface\DebugMoveType
			Case 0:
				If Input\Hold\Up Then Player_SetSpeed(p,Game\Interface\DebugSpeed#*d\Delta) : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\Down Then Player_SetSpeed(p,Game\Interface\DebugSpeed#*d\Delta) : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\ActionJump Then p\Motion\Speed\y#=Game\Interface\DebugSpeed#*d\Delta : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\ActionRoll Then p\Motion\Speed\y#=-Game\Interface\DebugSpeed#*d\Delta : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
			Case 1:
				If Input\Hold\Up Then MoveEntity p\Objects\Entity,0,0,Game\Interface\DebugSpeed#*d\Delta : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\Down Then MoveEntity p\Objects\Entity,0,0,-Game\Interface\DebugSpeed#*d\Delta : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\Left Then MoveEntity p\Objects\Entity,-Game\Interface\DebugSpeed#*d\Delta,0,0 : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\Right Then MoveEntity p\Objects\Entity,Game\Interface\DebugSpeed#*d\Delta,0,0 : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\ActionJump Then MoveEntity p\Objects\Entity,0,Game\Interface\DebugSpeed#*d\Delta,0 : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
				If Input\Hold\ActionRoll Then MoveEntity p\Objects\Entity,0,-Game\Interface\DebugSpeed#*d\Delta,0 : If TempAttribute\campos#=0 Then TempAttribute\campos#=1
		End Select

		If Input\Pressed\CamCenter Then
			Select TempAttribute\campos#
				Case 0: TempAttribute\campos#=1
				Case 1: TempAttribute\campos#=2
				Case 2: TempAttribute\campos#=10
				Case 10: TempAttribute\campos#=11
				Case 11: TempAttribute\campos#=12
				Case 12: TempAttribute\campos#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Pressed\ActionDrift Then
			TempAttribute\campos#=0
			PlaySmartSound(Sound_MenuMove)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_CamRotation(p.tPlayer, d.tDeltaTime)

		PositionEntity p\Objects\Mesh, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh2, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh3, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1

		Player_Action_Debug_DebugSpeedChanging()

		If Input\Pressed\ActionDrift Then
			TempAttribute\campitch#=0
			TempAttribute\camyaw#=0
			TempAttribute\camroll#=0
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Hold\Up Then TempAttribute\campitch#=TempAttribute\campitch#+Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Down Then TempAttribute\campitch#=TempAttribute\campitch#-Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Left Then TempAttribute\camyaw#=TempAttribute\camyaw#+Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Right Then TempAttribute\camyaw#=TempAttribute\camyaw#-Game\Interface\DebugSpeed#*d\Delta

		If Input\Pressed\ActionJump Then
			Select TempAttribute\campitch#
				Case -90: TempAttribute\campitch#=-45
				Case -45: TempAttribute\campitch#=0
				Case 0: TempAttribute\campitch#=45
				Case 45: TempAttribute\campitch#=90
				Case 90: TempAttribute\campitch#=-90
				Default: TempAttribute\campitch#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf
		If Input\Pressed\ActionRoll Then
			Select TempAttribute\camyaw#
				Case 0: TempAttribute\camyaw#=45
				Case 45: TempAttribute\camyaw#=90
				Case 90: TempAttribute\camyaw#=135
				Case 135: TempAttribute\camyaw#=180
				Case 180: TempAttribute\camyaw#=225
				Case 225: TempAttribute\camyaw#=270
				Case 270: TempAttribute\camyaw#=315
				Case 315: TempAttribute\camyaw#=0
				Default: TempAttribute\camyaw#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_CamZoom(p.tPlayer)

		PositionEntity p\Objects\Mesh, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh2, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh3, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1

		If TempAttribute\camzoom#<0 Then TempAttribute\camzoom#=0
		If TempAttribute\camzoom#>50 Then TempAttribute\camzoom#=50

		Player_Action_Debug_DebugSpeed2Changing()

		If Input\Pressed\ActionDrift Then
			TempAttribute\camzoom#=21
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Pressed\ActionJump Then TempAttribute\camzoom#=TempAttribute\camzoom#+Game\Interface\DebugSpeed2#
		If Input\Pressed\ActionRoll Then TempAttribute\camzoom#=TempAttribute\camzoom#-Game\Interface\DebugSpeed2#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_CamSpeed(p.tPlayer)

		PositionEntity p\Objects\Mesh, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh2, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh3, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1

		If TempAttribute\camspeed#<0.1 Then TempAttribute\camspeed#=0.1
		If TempAttribute\camspeed#>100.0 Then TempAttribute\camspeed#=100.0

		Player_Action_Debug_DebugSpeed2Changing()

		If Input\Pressed\ActionDrift Then
			TempAttribute\camspeed#=30
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Pressed\ActionJump Then TempAttribute\camspeed#=TempAttribute\camspeed#+Game\Interface\DebugSpeed2#
		If Input\Pressed\ActionRoll Then TempAttribute\camspeed#=TempAttribute\camspeed#-Game\Interface\DebugSpeed2#

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Amount(p.tPlayer)

		If Game\Interface\DebugAmountAxis<1 Then Game\Interface\DebugAmountAxis=1

		If Input\Pressed\ActionSkillX Then TempAttribute\amountcircle#=abs(TempAttribute\amountcircle#-1)

		If TempAttribute\amountcircle#=1 and Game\Interface\DebugAmountAxis=2 Then Game\Interface\DebugAmountAxis=1

		If Input\Pressed\ActionSkill2 Then
			Select TempAttribute\amountcircle#
				Case 0:
					Game\Interface\DebugAmountAxis=Game\Interface\DebugAmountAxis+1
					If Game\Interface\DebugAmountAxis>3 Then Game\Interface\DebugAmountAxis=1
				Case 1:
					Select Game\Interface\DebugAmountAxis
						Case 1: Game\Interface\DebugAmountAxis=3
						Case 3: Game\Interface\DebugAmountAxis=1
					End Select
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		Select Game\Interface\DebugAmountAxis
			Case 1:
				If TempAttribute\amount1#<1 Then TempAttribute\amount1#=1

				If Input\Pressed\ActionDrift Then
					TempAttribute\amount1#=1
					PlaySmartSound(Sound_MenuMove)
				EndIf

				If Input\Pressed\ActionJump Then TempAttribute\amount1#=TempAttribute\amount1#+1
				If Input\Pressed\ActionRoll Then TempAttribute\amount1#=TempAttribute\amount1#-1
			Case 2:
				If TempAttribute\amount2#<1 Then TempAttribute\amount2#=1

				If Input\Pressed\ActionDrift Then
					TempAttribute\amount2#=1
					PlaySmartSound(Sound_MenuMove)
				EndIf

				If Input\Pressed\ActionJump Then TempAttribute\amount2#=TempAttribute\amount2#+1
				If Input\Pressed\ActionRoll Then TempAttribute\amount2#=TempAttribute\amount2#-1
			Case 3:
				If TempAttribute\amount3#<1 Then TempAttribute\amount3#=1

				If Input\Pressed\ActionDrift Then
					TempAttribute\amount3#=1
					PlaySmartSound(Sound_MenuMove)
				EndIf

				If Input\Pressed\ActionJump Then TempAttribute\amount3#=TempAttribute\amount3#+1
				If Input\Pressed\ActionRoll Then TempAttribute\amount3#=TempAttribute\amount3#-1
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_AmountRotation(p.tPlayer, d.tDeltaTime)

		If Input\Pressed\ActionSkillX Then TempAttribute\amountcircle#=abs(TempAttribute\amountcircle#-1)

		If TempAttribute\amountcircle#=1 and Game\Interface\DebugAmountAxis=2 Then Game\Interface\DebugAmountAxis=1

		Player_Action_Debug_DebugSpeedChanging()

		If Input\Pressed\ActionDrift Then
			TempAttribute\amountpitch#=0
			TempAttribute\amountyaw#=0
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Hold\Up Then TempAttribute\amountpitch#=TempAttribute\amountpitch#+Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Down Then TempAttribute\amountpitch#=TempAttribute\amountpitch#-Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Left Then TempAttribute\amountyaw#=TempAttribute\amountyaw#+Game\Interface\DebugSpeed#*d\Delta
		If Input\Hold\Right Then TempAttribute\amountyaw#=TempAttribute\amountyaw#-Game\Interface\DebugSpeed#*d\Delta

		If Input\Pressed\ActionJump Then
			Select TempAttribute\amountpitch#
				Case -90: TempAttribute\amountpitch#=-45
				Case -45: TempAttribute\amountpitch#=0
				Case 0: TempAttribute\amountpitch#=45
				Case 45: TempAttribute\amountpitch#=90
				Case 90: TempAttribute\amountpitch#=-90
				Default: TempAttribute\amountpitch#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf
		If Input\Pressed\ActionRoll Then
			Select TempAttribute\amountyaw#
				Case 0: TempAttribute\amountyaw#=45
				Case 45: TempAttribute\amountyaw#=90
				Case 90: TempAttribute\amountyaw#=135
				Case 135: TempAttribute\amountyaw#=180
				Case -180,180: TempAttribute\amountyaw#=-135
				Case -135: TempAttribute\amountyaw#=-90
				Case -90: TempAttribute\amountyaw#=-45
				Case -45: TempAttribute\amountyaw#=0
				Default: TempAttribute\amountyaw#=0
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_AmountSpace(p.tPlayer)

		If Game\Interface\DebugAmountAxis<1 Then Game\Interface\DebugAmountAxis=1

		If Input\Pressed\ActionSkillX Then TempAttribute\amountcircle#=abs(TempAttribute\amountcircle#-1)

		If TempAttribute\amountcircle#=1 and Game\Interface\DebugAmountAxis=2 Then Game\Interface\DebugAmountAxis=1

		If Input\Pressed\ActionSkill2 Then
			Select TempAttribute\amountcircle#
				Case 0:
					Game\Interface\DebugAmountAxis=Game\Interface\DebugAmountAxis+1
					If Game\Interface\DebugAmountAxis>3 Then Game\Interface\DebugAmountAxis=1
				Case 1:
					Select Game\Interface\DebugAmountAxis
						Case 1: Game\Interface\DebugAmountAxis=3
						Case 3: Game\Interface\DebugAmountAxis=1
					End Select
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		Player_Action_Debug_DebugSpeed2Changing()

		Select Game\Interface\DebugAmountAxis
			Case 1:
				If TempAttribute\amountspace1#<4 Then TempAttribute\amountspace1#=4

				If Input\Pressed\ActionDrift Then
					TempAttribute\amountspace1#=10
					PlaySmartSound(Sound_MenuMove)
				EndIf

				If Input\Pressed\ActionJump Or Input\Pressed\Up Then TempAttribute\amountspace1#=TempAttribute\amountspace1#+Game\Interface\DebugSpeed2#
				If Input\Pressed\ActionRoll Or Input\Pressed\Down Then TempAttribute\amountspace1#=TempAttribute\amountspace1#-Game\Interface\DebugSpeed2#
			Case 2:
				If TempAttribute\amountspace2#<4 Then TempAttribute\amountspace2#=4

				If Input\Pressed\ActionDrift Then
					TempAttribute\amountspace2#=10
					PlaySmartSound(Sound_MenuMove)
				EndIf

				If Input\Pressed\ActionJump Or Input\Pressed\Up Then TempAttribute\amountspace2#=TempAttribute\amountspace2#+Game\Interface\DebugSpeed2#
				If Input\Pressed\ActionRoll Or Input\Pressed\Down Then TempAttribute\amountspace2#=TempAttribute\amountspace2#-Game\Interface\DebugSpeed2#
			Case 3:
				If TempAttribute\amountspace3#<4 Then TempAttribute\amountspace3#=4

				If Input\Pressed\ActionDrift Then
					TempAttribute\amountspace3#=10
					PlaySmartSound(Sound_MenuMove)
				EndIf

				If Input\Pressed\ActionJump Or Input\Pressed\Up Then TempAttribute\amountspace3#=TempAttribute\amountspace3#+Game\Interface\DebugSpeed2#
				If Input\Pressed\ActionRoll Or Input\Pressed\Down Then TempAttribute\amountspace3#=TempAttribute\amountspace3#-Game\Interface\DebugSpeed2#
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Switch(p.tPlayer)

		If TempAttribute\switch1#<0 Then TempAttribute\switch1#=0
		If TempAttribute\switch2#<0 Then TempAttribute\switch2#=0
		If TempAttribute\switch3#<0 Then TempAttribute\switch3#=0

		If Input\Pressed\ActionSkill2 Then
			Select p\ObjType
				Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
					PlaySmartSound(Sound_MenuMove)
					Game\Interface\DebugWhichSwitch=Game\Interface\DebugWhichSwitch+1
					If Game\Interface\DebugWhichSwitch>3 Then Game\Interface\DebugWhichSwitch=1
			End Select
		EndIf

		Select p\ObjType
			Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_BOXLIGHT,OBJTYPE_BOXLIGHT+1000,OBJTYPE_BOXLIGHT+2000,OBJTYPE_GOAL2,OBJTYPE_GOAL2+1000:
				If Input\Pressed\ActionJump Then
					Select Game\Interface\DebugWhichSwitch
					Case 1: TempAttribute\switch1#=TempAttribute\switch1#+1
					Case 2: TempAttribute\switch2#=TempAttribute\switch2#+1
					Case 3: TempAttribute\switch3#=TempAttribute\switch3#+1
					End Select
				EndIf
				If Input\Pressed\ActionRoll Then
					Select Game\Interface\DebugWhichSwitch
					Case 1: TempAttribute\switch1#=TempAttribute\switch1#-1
					Case 2: TempAttribute\switch2#=TempAttribute\switch2#-1
					Case 3: TempAttribute\switch3#=TempAttribute\switch3#-1
					End Select
				EndIf
			Default:
				If Input\Pressed\ActionJump Then TempAttribute\switch1#=TempAttribute\switch1#+1
				If Input\Pressed\ActionRoll Then TempAttribute\switch1#=TempAttribute\switch1#-1
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_SwitchStatus(p.tPlayer)

		If Input\Pressed\ActionJump Then PlaySmartSound(Sound_MenuMove) : TempAttribute\switchstatus#=1
		If Input\Pressed\ActionRoll Then PlaySmartSound(Sound_MenuMove) : TempAttribute\switchstatus#=0

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Teleporter(p.tPlayer)

		If TempAttribute\teleporterno#<0 and Menu\ChaoGarden=0 Then TempAttribute\teleporterno#=0

		If p\ObjType=OBJTYPE_TELEPORTER2 and TempAttribute\teleporterno#>StageAmount Then TempAttribute\teleporterno#=StageAmount

		If Input\Pressed\ActionJump Then TempAttribute\teleporterno#=TempAttribute\teleporterno#+1
		If Input\Pressed\ActionRoll Then TempAttribute\teleporterno#=TempAttribute\teleporterno#-1

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function Player_Action_Debug_Destination(p.tPlayer, d.tDeltaTime)

		PositionEntity p\Objects\Mesh, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh2, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
		PositionEntity p\Objects\Mesh3, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1

		TempAttribute\dx#=p\Objects\Position\x#
		TempAttribute\dy#=p\Objects\Position\y#
		TempAttribute\dz#=p\Objects\Position\z#
		PositionEntity p\Objects\Mesh5, TempAttribute\dx#, TempAttribute\dy#, TempAttribute\dz#, 1

		Game\Interface\DebugHadD=True

		If Input\Pressed\ActionSkill2 Then Game\Interface\DebugMoveType=abs(Game\Interface\DebugMoveType-1) : PlaySmartSound(Sound_MenuMove)

		Player_Action_Debug_DebugSpeedChanging()

		If Game\Interface\DebugCollision=1 Then
			Game\Interface\DebugCollision=abs(Game\Interface\DebugCollision-1)
			Select Game\Interface\DebugCollision
				Case 1: EntityType(p\Objects\Entity,COLLISION_PLAYER)
				Case 0: EntityType(p\Objects\Entity,COLLISION_NONE)
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		Select Game\Interface\DebugMoveType
			Case 0:
				If Input\Hold\Up Then Player_SetSpeed(p,Game\Interface\DebugSpeed#*d\Delta) : TempAttribute\hasd#=1
				If Input\Hold\Down Then Player_SetSpeed(p,Game\Interface\DebugSpeed#*d\Delta) : TempAttribute\hasd#=1
				If Input\Hold\ActionJump Then p\Motion\Speed\y#=Game\Interface\DebugSpeed#*d\Delta : TempAttribute\hasd#=1
				If Input\Hold\ActionRoll Then p\Motion\Speed\y#=-Game\Interface\DebugSpeed#*d\Delta : TempAttribute\hasd#=1
			Case 1:
				If Input\Hold\Up Then MoveEntity p\Objects\Entity,0,0,Game\Interface\DebugSpeed#*d\Delta : TempAttribute\hasd#=1
				If Input\Hold\Down Then MoveEntity p\Objects\Entity,0,0,-Game\Interface\DebugSpeed#*d\Delta : TempAttribute\hasd#=1
				If Input\Hold\Left Then MoveEntity p\Objects\Entity,-Game\Interface\DebugSpeed#*d\Delta,0,0 : TempAttribute\hasd#=1
				If Input\Hold\Right Then MoveEntity p\Objects\Entity,Game\Interface\DebugSpeed#*d\Delta,0,0 : TempAttribute\hasd#=1
				If Input\Hold\ActionJump Then MoveEntity p\Objects\Entity,0,Game\Interface\DebugSpeed#*d\Delta,0 : TempAttribute\hasd#=1
				If Input\Hold\ActionRoll Then MoveEntity p\Objects\Entity,0,-Game\Interface\DebugSpeed#*d\Delta,0 : TempAttribute\hasd#=1
		End Select

		If Input\Pressed\CamCenter Then TempAttribute\hasd#=abs(TempAttribute\hasd#-1) : PlaySmartSound(Sound_MenuMove)

	End Function