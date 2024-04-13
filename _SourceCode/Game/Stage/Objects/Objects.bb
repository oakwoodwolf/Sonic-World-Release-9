Global Object_Texture_Quad = LoadTexture("Textures\Woosh.png", 1+256)

Global Object_Texture_Pads=LoadTexture("Objects\Fasteners\Pad.png", 1+256)
Global Object_Texture_DashHoop=LoadTexture("Objects\Hoops\DashHoop.png", 1+256)
Global Object_Texture_RainbowHoop=LoadTexture("Objects\Hoops\RainbowHoop.png", 1+256)
Global Object_Texture_AcceleratorLight=LoadTexture("Objects\Fasteners\accellight.png", 1+256)

Global Object_Texture_Gold=LoadTexture("Textures\Gold.png", 64+256) : TextureBlend Object_Texture_Gold,2

Global Object_Texture_AntiGlow = CreateTexture(0,0)
Global Object_Texture_PsychoGlow = LoadTexture("Textures/levitation.png",64) : TextureBlend Object_Texture_PsychoGlow,3
Global Object_Texture_CurseGlow = LoadTexture("Textures/levitationm.png",64) : TextureBlend Object_Texture_CurseGlow,3
Global Object_Texture_IceGlow = LoadTexture("Textures/levitationb.png",64) : TextureBlend Object_Texture_IceGlow,3
Global Object_Texture_RubyGlow = LoadTexture("Textures/levitationr.png",64) : TextureBlend Object_Texture_RubyGlow,3
Global Object_Texture_ChaosGlow = LoadTexture("Textures/levitationd.png",64) : TextureBlend Object_Texture_ChaosGlow,3

Global Object_Texture_Box_Wooden=LoadTexture("Objects\Boxes\box_wooden.png")
Global Object_Texture_Box_Metal=LoadTexture("Objects\Boxes\box_metal.png")
Global Object_Texture_Box_Iron=LoadTexture("Objects\Boxes\box_iron.png")
Global Object_Texture_Box_Cage=LoadTexture("Objects\Boxes\box_cage.png", 4)
Global Object_Texture_Box_Tnt=LoadTexture("Objects\Boxes\box_tnt.png")
Global Object_Texture_Box_Nitro=LoadTexture("Objects\Boxes\box_nitro.png")
Global Object_Texture_Box_Float=LoadTexture("Objects\Boxes\box_float.png")

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	i=0

	Global OBJTYPE_NULL			= i : i=i+1

	Global OBJTYPE_RING			= i : i=i+1
	Global OBJTYPE_REDRING		= i : i=i+1

	Global OBJTYPE_SPRING		= i : i=i+1
	Global OBJTYPE_BSPRING		= i : i=i+1
	Global OBJTYPE_SPRINGX		= i : i=i+1
	Global OBJTYPE_SPRINGTRAP	= i : i=i+1
	Global OBJTYPE_SPRINGTRAPX	= i : i=i+1
	Global OBJTYPE_PAD			= i : i=i+1
	Global OBJTYPE_RAMP			= i : i=i+1
	Global OBJTYPE_HOOP			= i : i=i+1
	Global OBJTYPE_THOOP		= i : i=i+1
	Global OBJTYPE_ACCEL		= i : i=i+1
	Global OBJTYPE_LOCKER		= i : i=i+1
	Global OBJTYPE_FORCER		= i : i=i+1
	Global OBJTYPE_NODE			= i : i=i+1
	Global OBJTYPE_NODE2		= i : i=i+1
	Global OBJTYPE_FAN			= i : i=i+1
	Global OBJTYPE_BFAN			= i : i=i+1
	Global OBJTYPE_BFANLOW		= i : i=i+1

	Global OBJTYPE_CHECK		= i : i=i+1

	Global OBJTYPE_RINGS		= i : i=i+1
	Global OBJTYPE_LIFE			= i : i=i+1
	Global OBJTYPE_TRAP			= i : i=i+1
	Global OBJTYPE_INVINC		= i : i=i+1
	Global OBJTYPE_SHOES		= i : i=i+1
	Global OBJTYPE_NSHIELD		= i : i=i+1
	Global OBJTYPE_FSHIELD		= i : i=i+1
	Global OBJTYPE_BSHIELD		= i : i=i+1
	Global OBJTYPE_TSHIELD		= i : i=i+1
	Global OBJTYPE_ESHIELD		= i : i=i+1
	Global OBJTYPE_BOMB			= i : i=i+1
	Global OBJTYPE_BOARD		= i : i=i+1
	Global OBJTYPE_GLIDER		= i : i=i+1
	Global OBJTYPE_CAR			= i : i=i+1
	Global OBJTYPE_BIKE			= i : i=i+1
	Global OBJTYPE_BOBSLEIGH	= i : i=i+1
	Global OBJTYPE_TORNADO		= i : i=i+1
	Global OBJTYPE_CYCLONE		= i : i=i+1
	Global OBJTYPE_KART			= i : i=i+1
	Global OBJTYPE_WINGS		= i : i=i+1

	Global OBJTYPE_BALLOON		= i : i=i+1

	Global OBJTYPE_SPIKEBALL	= i : i=i+1
	Global OBJTYPE_SPIKEBOMB	= i : i=i+1
	Global OBJTYPE_SPIKECRUSHER	= i : i=i+1
	Global OBJTYPE_SPIKEDRILL	= i : i=i+1
	Global OBJTYPE_SPIKETIMED	= i : i=i+1
	Global OBJTYPE_SPIKETRAP	= i : i=i+1
	Global OBJTYPE_SPIKEBAR		= i : i=i+1
	Global OBJTYPE_SPIKESWING	= i : i=i+1
	Global OBJTYPE_SPIKESWINGBALL= i : i=i+1
	Global OBJTYPE_SPIKECYLINDER= i : i=i+1

	Global OBJTYPE_GOAL			= i : i=i+1
	Global OBJTYPE_GOAL2		= i : i=i+1

	Global OBJTYPE_FLAMESPOUT	= i : i=i+1
	Global OBJTYPE_ICESPOUT		= i : i=i+1
	Global OBJTYPE_SHOCKSPOUT	= i : i=i+1
	Global OBJTYPE_LASERV		= i : i=i+1
	Global OBJTYPE_LASERH		= i : i=i+1
	Global OBJTYPE_RINGGATEV	= i : i=i+1
	Global OBJTYPE_RINGGATEH	= i : i=i+1

	Global OBJTYPE_BOXWOODEN	= i : i=i+1
	Global OBJTYPE_BOXMETAL		= i : i=i+1
	Global OBJTYPE_BOXIRON		= i : i=i+1
	Global OBJTYPE_BOXCAGE		= i : i=i+1
	Global OBJTYPE_BOXLIGHT		= i : i=i+1
	Global OBJTYPE_BOXTNT		= i : i=i+1
	Global OBJTYPE_BOXNITRO		= i : i=i+1
	Global OBJTYPE_BOXFLOAT		= i : i=i+1

	Global OBJTYPE_BALLBUMPER	= i : i=i+1
	Global OBJTYPE_GROUNDBUMPER	= i : i=i+1
	Global OBJTYPE_METROBUMPER	= i : i=i+1
	Global OBJTYPE_PLATEBUMPER	= i : i=i+1
	Global OBJTYPE_TRIANGLEBUMPER= i : i=i+1
	Global OBJTYPE_PADDLE		= i : i=i+1
	Global OBJTYPE_CLOUD		= i : i=i+1
	Global OBJTYPE_POLE			= i : i=i+1

	Global OBJTYPE_PAWN			= i : i=i+1
	Global OBJTYPE_PAWNSHIELD	= i : i=i+1
	Global OBJTYPE_PAWNGUN		= i : i=i+1
	Global OBJTYPE_PAWNSWORD	= i : i=i+1
	Global OBJTYPE_FLAPPER		= i : i=i+1
	Global OBJTYPE_FLAPPERGUN	= i : i=i+1
	Global OBJTYPE_FLAPPERBOMB	= i : i=i+1
	Global OBJTYPE_FLAPPERNEEDLE= i : i=i+1
	Global OBJTYPE_SPINA		= i : i=i+1
	Global OBJTYPE_SPANA		= i : i=i+1
	Global OBJTYPE_SPONA		= i : i=i+1
	Global OBJTYPE_MOTOBUG		= i : i=i+1
	Global OBJTYPE_CATERKILLER	= i : i=i+1
	Global OBJTYPE_BUZZBOMBER	= i : i=i+1
	Global OBJTYPE_BUZZER		= i : i=i+1
	Global OBJTYPE_CHOPPER		= i : i=i+1
	Global OBJTYPE_CRABMEAT		= i : i=i+1
	Global OBJTYPE_JAWS			= i : i=i+1
	Global OBJTYPE_SPINY		= i : i=i+1
	Global OBJTYPE_GRABBER		= i : i=i+1
	Global OBJTYPE_KIKI			= i : i=i+1
	Global OBJTYPE_COP			= i : i=i+1
	Global OBJTYPE_COPRACER		= i : i=i+1
	Global OBJTYPE_HUNTER		= i : i=i+1
	Global OBJTYPE_HUNTERSHIELD	= i : i=i+1
	Global OBJTYPE_BEETLE		= i : i=i+1
	Global OBJTYPE_BEETLEMONO	= i : i=i+1
	Global OBJTYPE_BEETLESPARK	= i : i=i+1
	Global OBJTYPE_BEETLESPRING	= i : i=i+1
	Global OBJTYPE_ACHAOS		= i : i=i+1
	Global OBJTYPE_ACHAOSBLOB	= i : i=i+1
	Global OBJTYPE_RHINO		= i : i=i+1
	Global OBJTYPE_RHINOSPIKES	= i : i=i+1
	Global OBJTYPE_HORNET3		= i : i=i+1
	Global OBJTYPE_HORNET6		= i : i=i+1
	Global OBJTYPE_AEROC		= i : i=i+1
	Global OBJTYPE_CHASER		= i : i=i+1
	Global OBJTYPE_FIGHTER		= i : i=i+1
	Global OBJTYPE_EGGROBO		= i : i=i+1
	Global OBJTYPE_CAMERON		= i : i=i+1
	Global OBJTYPE_KLAGEN		= i : i=i+1
	Global OBJTYPE_ORBINAUT		= i : i=i+1
	Global OBJTYPE_TYPHOON		= i : i=i+1
	Global OBJTYPE_TYPHOONF		= i : i=i+1
	Global OBJTYPE_ANTON		= i : i=i+1
	Global OBJTYPE_AQUIS		= i : i=i+1
	Global OBJTYPE_BOMBIE		= i : i=i+1
	Global OBJTYPE_NEWTRON		= i : i=i+1
	Global OBJTYPE_PENGUINATOR	= i : i=i+1
	Global OBJTYPE_SLICER		= i : i=i+1
	Global OBJTYPE_SNAILB		= i : i=i+1
	Global OBJTYPE_SPIKES		= i : i=i+1
	Global OBJTYPE_ASTERON		= i : i=i+1
	Global OBJTYPE_BATBOT		= i : i=i+1
	Global OBJTYPE_BUBBLS		= i : i=i+1
	Global OBJTYPE_BUBBLSSPIKES	= i : i=i+1
	Global OBJTYPE_STEELION		= i : i=i+1
	Global OBJTYPE_BALKIRY		= i : i=i+1
	Global OBJTYPE_BURROBOT		= i : i=i+1
	Global OBJTYPE_CRAWL		= i : i=i+1
	Global OBJTYPE_DRAGONFLY	= i : i=i+1
	Global OBJTYPE_MADMOLE		= i : i=i+1
	Global OBJTYPE_MANTA		= i : i=i+1
	Global OBJTYPE_MUSHMEANIE	= i : i=i+1
	Global OBJTYPE_OCTUS		= i : i=i+1
	Global OBJTYPE_PATABATA		= i : i=i+1
	Global OBJTYPE_ZOOMER		= i : i=i+1
	Global OBJTYPE_BITER		= i : i=i+1
	Global OBJTYPE_CRAWLER		= i : i=i+1
	Global OBJTYPE_TAKER		= i : i=i+1
	Global OBJTYPE_E1000		= i : i=i+1
	Global OBJTYPE_BALLHOG		= i : i=i+1
	Global OBJTYPE_RHINOTANK	= i : i=i+1
	Global OBJTYPE_TECHNOSQU	= i : i=i+1
	Global OBJTYPE_WARRIOR		= i : i=i+1
	Global OBJTYPE_WARRIORGUN1	= i : i=i+1
	Global OBJTYPE_WARRIORGUN2	= i : i=i+1
	Global OBJTYPE_OAKSWORD		= i : i=i+1
	Global OBJTYPE_LEECH		= i : i=i+1
	Global OBJTYPE_WING			= i : i=i+1
	Global OBJTYPE_SOLDIER		= i : i=i+1
	Global OBJTYPE_SOLDIERCAMO	= i : i=i+1
	Global OBJTYPE_CATAKILLER	= i : i=i+1
	Global OBJTYPE_CLUCKOID		= i : i=i+1
	Global OBJTYPE_MANTIS		= i : i=i+1
	Global OBJTYPE_NEBULA		= i : i=i+1
	Global OBJTYPE_ROLLER		= i : i=i+1
	Global OBJTYPE_SHEEP		= i : i=i+1
	Global OBJTYPE_SNOWY		= i : i=i+1
	Global OBJTYPE_SPLATS		= i : i=i+1
	Global OBJTYPE_TOXO			= i : i=i+1
	Global OBJTYPE_SPRINKLR		= i : i=i+1
	Global OBJTYPE_DOOMSEYE		= i : i=i+1
	Global OBJTYPE_BOO			= i : i=i+1
	Global OBJTYPE_BOOSCARE		= i : i=i+1
	Global OBJTYPE_GHOST		= i : i=i+1
	Global OBJTYPE_HAMMER		= i : i=i+1
	Global OBJTYPE_HAMMERHAMMER	= i : i=i+1
	Global OBJTYPE_HAMMERSHIELD	= i : i=i+1
	Global OBJTYPE_WITCH1		= i : i=i+1
	Global OBJTYPE_WITCH2		= i : i=i+1
	Global OBJTYPE_FCANNON1		= i : i=i+1
	Global OBJTYPE_FCANNON2		= i : i=i+1
	Global OBJTYPE_FCANNON3		= i : i=i+1
	Global OBJTYPE_ENEMYCOUNT	= i-1
	Global OBJTYPE_BOSS			= i : i=i+1
	Global OBJTYPE_BOSS2		= i : i=i+1
	Global OBJTYPE_BOSSRUN		= i : i=i+1
	Global OBJTYPE_BOSSBETA		= i : i=i+1
	Global OBJTYPE_BOSSMECHA	= i : i=i+1
	Global OBJTYPE_BOSSROBO	= i : i=i+1
	Global OBJTYPE_BUBBLES		= i : i=i+1

	Global OBJTYPE_TELEPORTER	= i : i=i+1
	Global OBJTYPE_TELEPORTER2	= i : i=i+1
	Global OBJTYPE_TELEPORTEREND= i : i=i+1

	Global OBJTYPE_OMOCHAO		= i : i=i+1

	Global OBJTYPE_CANNON		= i : i=i+1

	Global OBJTYPE_PROPELLER	= i : i=i+1
	Global OBJTYPE_PULLEY		= i : i=i+1
	Global OBJTYPE_ROCKET		= i : i=i+1
	Global OBJTYPE_ELEVATOR		= i : i=i+1

	Global OBJTYPE_HANDLE		= i : i=i+1

	Global OBJTYPE_FPLAT		= i : i=i+1

	Global OBJTYPE_SWITCH		= i : i=i+1
	Global OBJTYPE_SWITCHBASE	= i : i=i+1
	Global OBJTYPE_SWITCHTOP	= i : i=i+1
	Global OBJTYPE_SWITCHWATER	= i : i=i+1
	Global OBJTYPE_SWITCHAIR	= i : i=i+1

	Global OBJTYPE_BELL			= i : i=i+1

	Global OBJTYPE_ROCK			= i : i=i+1
	Global OBJTYPE_CRYSTAL		= i : i=i+1
	Global OBJTYPE_ICICLE		= i : i=i+1
	Global OBJTYPE_ICICLEBIG	= i : i=i+1
	Global OBJTYPE_ICEDECOR		= i : i=i+1
	Global OBJTYPE_AUTO			= i : i=i+1

	Global OBJTYPE_HINT			= i : i=i+1
	Global OBJTYPE_COUNTER		= i : i=i+1
	Global OBJTYPE_SIGN			= i : i=i+1

	Global OBJTYPE_TRIGGER_VEHICLECANCEL= i : i=i+1
	Global OBJTYPE_TRIGGER_MACH			= i : i=i+1
	Global OBJTYPE_TRIGGER_MACHCANCEL	= i : i=i+1
	Global OBJTYPE_TRIGGER_SKYDIVE		= i : i=i+1
	Global OBJTYPE_TRIGGER_SKYDIVECANCEL= i : i=i+1
	Global OBJTYPE_TRIGGER_WATER		= i : i=i+1
	Global OBJTYPE_TRIGGER_MUSIC		= i : i=i+1

	Global OBJTYPE_EXPLOSION	= i : i=i+1
	Global OBJTYPE_EXPLOSION2	= i : i=i+1
	Global OBJTYPE_BOMBER1		= i : i=i+1
	Global OBJTYPE_BOMBER2		= i : i=i+1

	Global OBJTYPE_CAPSULE		= i : i=i+1
	Global OBJTYPE_WISP			= i : i=i+1

	Global OBJTYPECOUNT=i-1

	Global OBJTYPE_TROPICAL		= i : i=i+1

	Global OBJTYPE_TELEPORTER3	= i : i=i+1
	Global OBJTYPE_TELEPORTER4	= i : i=i+1
	Global OBJTYPE_TELEPORTER5	= i : i=i+1
	Global OBJTYPE_TELEPORTER6	= i : i=i+1

	Global OBJTYPE_TRASHCAN		= i : i=i+1
	Global OBJTYPE_SACK			= i : i=i+1

	Global OBJTYPE_GARDENPOINT	= i : i=i+1

	Global OBJTYPE_SPRINKLER	= i : i=i+1
	Global OBJTYPE_BUTTERFLY	= i : i=i+1
	Global OBJTYPE_SEAGULL		= i : i=i+1
	Global OBJTYPE_SEAC			= i : i=i+1
	Global OBJTYPE_ORCA			= i : i=i+1
	Global OBJTYPE_CHAIR		= i : i=i+1
	Global OBJTYPE_PARASOL		= i : i=i+1
	Global OBJTYPE_AIRBALLOON	= i : i=i+1
	Global OBJTYPE_HELICOPTER	= i : i=i+1
	Global OBJTYPE_RAINBOW		= i : i=i+1

	Global OBJTYPECOUNT_CHAO=i-1

	;objects below dont show up in obj placer--
	Global OBJTYPE_SPEWRING		= i : i=i+1
	Global OBJTYPE_SPEWREDRING	= i : i=i+1
	Global OBJTYPE_FLICKY		= i : i=i+1

	Global OBJTYPE_PIECE		= i : i=i+1

	Global OBJTYPE_ENEMYMISSILE	= i : i=i+1

	Global OBJTYPE_SPEWSPIKEBOMB= i : i=i+1

	Global OBJTYPE_CHAO			= i : i=i+1
	Global OBJTYPE_FRUIT		= i : i=i+1
	Global OBJTYPE_SHELL		= i : i=i+1
	Global OBJTYPE_HAT			= i : i=i+1
	Global OBJTYPE_TOY			= i : i=i+1
	Global OBJTYPE_DRIVE		= i : i=i+1
	Global OBJTYPE_SEED			= i : i=i+1

	Global OBJTYPE_BREEDER		= i : i=i+1
	Global OBJTYPE_WHISTLE		= i : i=i+1
	Global OBJTYPE_PETTER		= i : i=i+1

	Global OBJTYPE_HOMMER		= i : i=i+1
	Global OBJTYPE_REPEATER		= i : i=i+1
	;---

	i=901

	Global OBJTYPE_TREE1	= i : i=i+1
	Global OBJTYPE_TREE2	= i : i=i+1
	Global OBJTYPE_TREE3	= i : i=i+1
	Global OBJTYPE_TREE4	= i : i=i+1
	Global OBJTYPE_TREE5	= i : i=i+1
	Global OBJTYPE_TREE6	= i : i=i+1
	Global OBJTYPE_SHRUB1	= i : i=i+1
	Global OBJTYPE_SHRUB2	= i : i=i+1
	Global OBJTYPE_SHRUB3	= i : i=i+1
	Global OBJTYPE_SHRUB4	= i : i=i+1
	Global OBJTYPE_SHRUB5	= i : i=i+1
	Global OBJTYPE_SHRUB6	= i : i=i+1
	Global OBJTYPE_BUSH1	= i : i=i+1
	Global OBJTYPE_BUSH2	= i : i=i+1
	Global OBJTYPE_BUSH3	= i : i=i+1
	Global OBJTYPE_BUSH4	= i : i=i+1
	Global OBJTYPE_BUSH5	= i : i=i+1
	Global OBJTYPE_BUSH6	= i : i=i+1
	Global OBJTYPE_BUSH7	= i : i=i+1
	Global OBJTYPE_GRASS1	= i : i=i+1
	Global OBJTYPE_GRASS2	= i : i=i+1
	Global OBJTYPE_GRASS3	= i : i=i+1
	Global OBJTYPE_GRASS4	= i : i=i+1
	Global OBJTYPE_GRASS5	= i : i=i+1
	Global OBJTYPE_GRASS6	= i : i=i+1
	Global OBJTYPE_GRASS7	= i : i=i+1
	Global OBJTYPE_GRASS8	= i : i=i+1
	Global OBJTYPE_GRASS9	= i : i=i+1
	Global OBJTYPE_GRASS10	= i : i=i+1
	Global OBJTYPE_SAKURA1	= i : i=i+1
	Global OBJTYPE_SAKURA2	= i : i=i+1
	Global OBJTYPE_SAKURA3	= i : i=i+1
	Global OBJTYPE_SAKURA4	= i : i=i+1
	Global OBJTYPE_SAKURA5	= i : i=i+1
	Global OBJTYPE_SAKURA6	= i : i=i+1
	Global OBJTYPE_PALM1	= i : i=i+1
	Global OBJTYPE_PALM2	= i : i=i+1
	Global OBJTYPE_PALM3	= i : i=i+1
	Global OBJTYPE_PALM4	= i : i=i+1
	Global OBJTYPE_WILDPALM1= i : i=i+1
	Global OBJTYPE_WILDPALM2= i : i=i+1
	Global OBJTYPE_WILDPALM3= i : i=i+1
	Global OBJTYPE_WILDPALM4= i : i=i+1
	Global OBJTYPE_WILDPALM5= i : i=i+1
	Global OBJTYPE_WILDPALM6= i : i=i+1
	Global OBJTYPE_FLOWER1	= i : i=i+1
	Global OBJTYPE_FLOWER2	= i : i=i+1
	Global OBJTYPE_FLOWER3	= i : i=i+1
	Global OBJTYPE_FLOWER4	= i : i=i+1
	Global OBJTYPE_FLOWER5	= i : i=i+1
	Global OBJTYPE_SNOWY1	= i : i=i+1
	Global OBJTYPE_SNOWY2	= i : i=i+1
	Global OBJTYPE_SNOWY3	= i : i=i+1
	Global OBJTYPE_SNOWY4	= i : i=i+1
	Global OBJTYPE_SNOWY5	= i : i=i+1
	Global OBJTYPE_SNOWY6	= i : i=i+1
	Global OBJTYPE_VINE1	= i : i=i+1
	Global OBJTYPE_DRYTREE1	= i : i=i+1
	Global OBJTYPE_DRYTREE2	= i : i=i+1
	Global OBJTYPE_DRYTREE3	= i : i=i+1
	Global OBJTYPE_ADABAT1	= i : i=i+1
	Global OBJTYPE_ADABAT2	= i : i=i+1
	Global OBJTYPE_ADABAT3	= i : i=i+1
	Global OBJTYPE_ADABAT4	= i : i=i+1
	Global OBJTYPE_ADABAT5	= i : i=i+1

	Global OBJTYPECOUNT_PLANTS=i-1


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Global OBJECT_VIEWDISTANCE# = 0
	Global OBJECT_VIEWDISTANCE_COUNT# = 0
	Global OBJECT_VIEWDISTANCE_IDCOUNT# = 0
	Global OBJECT_VIEWDISTANCE_LARGESTIDCOUNT# = 0
	Global OBJECT_VIEWDISTANCE_UPDATEDISTANCE# = 0
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tObject
		;object values
		Field ObjType
		Field ID
		Field AlwaysPresent
		Field HasRedRing
		Field InView
		Field InView2
		Field CanBeInsideBox
		Field IsInBox
		Field SavedFromInsideBoxOnce
		Field k.tBoxBlocker
		Field Repeated
		Field AddedToIdealScore

		;extensive types
		Field Enemy.tObject_Enemy
		Field EnemyMissile.tObject_EnemyMissile
		Field Piece.tObject_Piece
		Field ChaoObj.tObject_ChaoObj
		Field Spew.tObject_Spew
		Field Translator.tObject_Translator
		Field Omochao.tObject_Omochao
		Field Spike.tObject_Spike
		Field Trap.tObject_Trap
		Field Box.tObject_Box
		Field Bubble.tObject_Bubble
		Field Goal.tObject_Goal
		Field Monitor.tObject_Monitor
		Field Treasure.tObject_Treasure
		Field Switch.tObject_Switch
		Field Teleporter.tObject_Teleporter
		Field Hint.tObject_Hint
		Field Visual.tObject_Visual
		Field HasValuesetEnemy
		Field HasValuesetEnemyMissile
		Field HasValuesetPiece
		Field HasValuesetChaoObj
		Field HasValuesetSpew
		Field HasValuesetTranslator
		Field HasValuesetOmochao
		Field HasValuesetSpike
		Field HasValuesetTrap
		Field HasValuesetBox
		Field HasValuesetBubble
		Field HasValuesetGoal
		Field HasValuesetMonitor
		Field HasValuesetTreasure
		Field HasValuesetSwitch
		Field HasValuesetTeleporter
		Field HasValuesetHint
		Field HasValuesetVisual

		;object mesh values
		Field Pivot
		Field Entity
		Field Entity2
		Field Entity3
		Field Entity4
		Field EntityX
		Field EntityCube
		Field EntityCube2
		Field HasEntity
		Field HasEntity2
		Field HasEntity3
		Field HasEntity4
		Field HasEntityX
		Field HasEntityCube
		Field HasEntityCube2
		Field CollisionStore
		Field ExtraTexture
		Field HasExtraTexture

		;object general values
		Field Particle.tParticleTemplate
		Field Position.tVector
		Field Rotation.tVector
		Field InitialPosition.tVector
		Field InitialRotation.tVector
		Field HasRotation
		Field Power#
		Field HitType
		Field HitBox.tVector
		Field Speed.tVector
		Field SlopeRotation#
		Field g.tGravity
		Field HasGravity
		Field GotAssignedBomb

		;this-is-a flags
		Field BombHittable
		Field BombHitDirection#
		Field ThisIsATranslator
		Field ThisIsAMonitor
		Field ThisIsAMonitorBalloon
		Field ThisIsABumper
		Field ThisIsAnEnemy
		Field ThisIsAnEnemyMissile
		Field ThisIsABox
		Field ThisIsASpike
		Field ThisIsAPlant
		Field ThisIsAChaoTargetableObj

		;usage values
		Field HasIValues
		Field IValues[1]
		Field FValues#[1]
		Field Mode
		Field State
		Field Done
		Field Hit
		Field CheeseHit
		Field FroggyHit
		Field BombHit
		Field BombHitType
		Field ExplodeHit
		Field Repose
		Field AttackDetectRestrict
		Field AimedAt
		Field ForceCollisionReset

		; Homing flags
		Field CanRingDash
		Field CanHoming
		Field CheeseCanHoming

		; Animation values
		Field Anim
		Field PreviousAnim

		; Infected values
		Field Psychoed
		Field PsychoedThrown
		Field Rubied
		Field RubiedTimer
		Field PsychokineticX#
		Field PsychokineticY#
		Field PsychokineticZ#
		Field ObjPickedUp
		Field ThrownAsBomb
		Field FrozenStunTimer
		Field WhirlwindStunTimer
		Field FlowerTyphoonDirection
		Field WhirlwindDirection
		Field CurseStunTimer
		Field FroggyStunTimer
		Field FlowerStunTimer
		Field BubbleStunTimer

		; Treasure values
		Field TreasureTimer
		Field TreasurePreviousDistance#

	End Type

	;enemy values
	Type tObject_Enemy
		Field Center
		Field EnemyNo
		Field CarnivalNo
		Field EnemyShallAppear
		Field InRange
		Field InRangeForced
		Field Frame
		Field Shield
		Field InitialShield
		Field ShouldSpawnMissile
		Field WasJustAttacked
		Field HelloSonic
		Field SeenSonic
		Field SearchSonic
		Field WaitTimer
		Field WaitTimer2
		Field WaitTimer3
		Field AttackTimer
		Field AttackTimer2
		Field MayGetAttacked
		Field MayNotBeTargeted
		Field Underwater
		Field SelfDestruct
		Field WasKilledByBombMonitor
		Field Gold
		Field AttackMode
		Field AttackMode2
		Field AttackMode3
		Field WillBeHomedTimer
		Field Channel_EnemyStep
		Field Channel_EnemyStep2
		Field Channel_EnemyState
		Field Channel_EnemySwim
		Field HasJets
		Field Jet1
		Field Jet2
		Field JetParticle1.tParticleTemplate
		Field JetParticle2.tParticleTemplate
		Field WaterParticle.tParticleTemplate
		Field FlyEnemyType
		Field MayNotHurtTimer
		Field InitialHealth
		Field Health
		Field IsBoss
		Field BossObj1.tObject
		Field BossObj2.tObject
		Field BossObj3.tObject
		Field BossObj4.tObject
		Field HasBossObj1
		Field HasBossObj2
		Field HasBossObj3
		Field HasBossObj4
		Field BossMode
		Field ShadowCircle
		Field VoiceTimer
		Field HiddenSeenTimer
		Field Alpha#
	End Type

	;enemy missile values
	Type tObject_EnemyMissile
		Field Sender.tObject
		Field MissileType
		Field FollowTimer
		Field DisappearTimer
		Field KikiBombSpeed#
		Field RealMissile
		Field DeleteDestroy
		Field MayNotHurt
		Field Channel_State
	End Type

	;piece values
	Type tObject_Piece
		Field PieceTimer
		Field PositionXp
		Field PositionYp
		Field PositionZp
		Field PositionXn
		Field PositionYn
		Field PositionZn
		Field RotationXp
		Field RotationYp
		Field RotationZp
		Field RotationXn
		Field RotationYn
		Field RotationZn
		Field ShallNotFade
		Field Confetti
		Field SpeedX#
		Field SpeedY#
		Field SpeedZ#
	End Type

	;chao obj values
	Type tObject_ChaoObj
		Field ForceDelete
		Field targetcc.tChaoManager
		Field targetcc2.tChaoManager
		Field FruitType
		Field EatCycle
		Field ChaoTargetedThis
		Field FruitMesh[4]
		Field FruitCreated[4]
		Field FruitGrowth[4]
		Field FruitGrowthTimer[4]
		Field ShellType
		Field ShellType2
		Field DriveType
		Field DrivePitch
		Field HatType
		Field ToyType
		Field CollectTimer
		Field IsFromSeed
		Field SeedMode
		Field SeedsTree.tObject
		Field TreeType
		Field TreeGrowth
		Field TreeGrowthTimer
	End Type

	;spew ring values
	Type tObject_Spew
		Field Gravity#
		Field CollectTimer#
	End Type

	;translator values
	Type tObject_Translator
		Field LockControl#
		Field LockCam#
		Field LockRun#
		Field CamPos
		Field CamPosition.tVector
		Field CamRotation.tVector
		Field CamZoom#
		Field CamSpeed#
		Field HasDestination
		Field Destination.tVector

		Field THoopPointDisabler
		Field CannonWasUsedTimer
		Field BigSpringPoint
		Field LockerBType
		Field FanInvisible
		Field BumperTimer
		Field BumperCoolDownTimer
		Field WasJustUsedTimer
		Field Channel_Fan
		Field PlateBumper
		Field TransfererPlace
		Field TransfererForcePlace
		Field TransfererDone
		Field PulleyReturnTimer
		Field TransfererInverted
		Field TransfererIsBeingUsed
	End Type

	;omochao values
	Type tObject_Omochao
		Field FactTimer
		Field AttackedTimer
		Field OverTimer
		Field Channel_Omochao
		Field Channel_OmochaoFly
		Field Mode
		Field Direction
	End Type

	;spike values
	Type tObject_Spike
		Field SpikeTimer
		Field BarSize
		Field BarMover
		Field Channel_Spike
	End Type

	;trap values
	Type tObject_Trap
		Field SpoutTimer
		Field Channel_Spout
		Field CrystalColor
	End Type

	;box values
	Type tObject_Box
		Field BoxType
		Field DestroyBox
		Field TntBoxTimer
		Field TntBoxCount
		Field hask
		Field k.tBoxBlocker
	End Type

	;bubble values
	Type tObject_Bubble
		Field BubbleCreateTimer
		Field BubbleDisappearTimer
		Field BubbleType
	End Type

	;goal values
	Type tObject_Goal
		Field Channel_GoalIdle
	End Type

	;monitor values
	Type tObject_Monitor
		Field RingsType
		Field MonitorTakenOnce
	End Type

	;treasure values
	Type tObject_Treasure
		Field RedRingFlyStopTimer
		Field RedRingNo
		Field RedRingFly
		Field BoxShouldHide
	End Type

	;switch values
	Type tObject_Switch
		Field SwitchOn
		Field SwitchTimer
		Field SwitchNo[2]
		Field s1.tSwitchManager
		Field s2.tSwitchManager
		Field s3.tSwitchManager
		Field SwitchFound
		Field SwitchTop.tObject
		Field SwitchTopFound
		Field SwitchTopBrought
	End Type

	;teleporter values
	Type tObject_Teleporter
		Field RestrictTeleportTimer
		Field TeleporterNo
		Field OtherTeleporter.tObject
		Field TeleporterFound
	End Type

	;hint values
	Type tObject_Hint
		Field HintLine1$
		Field HintLine2$
		Field HintRevealTimer
		Field SignType
		Field SignAlpha#
	End Type

	;visuals values
	Type tObject_Visual
		Field Speed#
		Field SpeedPitch#
		Field SpeedYaw#
		Field SpeedTimer
		Field RetreatTimer
		Field Channel_Visual
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Type tObjectList
	Field ObjectDistance#
	Field ObjectName
	Field ObjectType
	Field ObjectItself.tObject
End Type

Type tObjectList2
	Field ClosestObjectName
	Field ClosestObjectType
	Field ClosestObject.tObject
	Field ShortestDistance#
End Type	
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Objects_Update(d.tDeltaTime)

		; Update objects

		For b.tBomb=Each tBomb
			Object_Bomb_Update(b,d)
		Next

		For ch.tCheese=Each tCheese
			Object_Cheese_Update(ch,d)
		Next

		For f.tFroggy=Each tFroggy
			Object_Froggy_Update(f,d)
		Next

		For cc.tChaoManager=Each tChaoManager
			Object_ChaoManager_Update(cc,d)
		Next

		For p.tPlayer = Each tPlayer
		If p\No#=1 and Game\Gameplay\Time>10 Then

			For o.tObject = Each tObject
				distance# = EntityDistance(p\Objects\Entity, o\Entity)
				Select o\ObjType
					Case OBJTYPE_BFAN,OBJTYPE_BFANLOW:
						i#=2
					Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
						i#=1.125
					Default:
						i#=0
				End Select
				o\InView = distance# < (OBJECT_VIEWDISTANCE_UPDATEDISTANCE#+300*i#)
				If Not o\InView Then
					If distance# < OBJECT_VIEWDISTANCE_UPDATEDISTANCE#+80 Then
						If o\AddedToIdealScore=0 Then
							Select o\ObjType
								Case OBJTYPE_RING:
									Gameplay_AddIdealScore(o,10)
								Case OBJTYPE_BALLOON:
									Gameplay_AddIdealScore(o,50)
								Case OBJTYPE_THOOP,OBJTYPE_AUTO:
									Gameplay_AddIdealScore(o,100)
								Default:
									If o\ThisIsAMonitor Then
										Gameplay_AddIdealScore(o,100)
									ElseIf o\ThisIsAnEnemy Then
										Object_Enemy_DecideAppear(o,p)
										If o\Enemy\EnemyShallAppear Then Gameplay_AddIdealScore(o,100+150*o\Enemy\Gold)
									ElseIf o\ThisIsABox Then
										Gameplay_AddIdealScore(o,50)
									ElseIf o\ThisIsABumper Then
										Gameplay_AddIdealScore(o,10)
									EndIf
							End Select
						EndIf

						Object_CheckHitBox_Bomb(o,p)
						If o\BombHit Then o\Inview=True
					EndIf
				EndIf
				Select o\ObjType
					Case OBJTYPE_AIRBALLOON,OBJTYPE_HELICOPTER,OBJTYPE_RAINBOW:
						i#=7
					Case OBJTYPE_BFAN,OBJTYPE_BFANLOW,OBJTYPE_ORCA,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_BUTTERFLY:
						i#=2
					Case OBJTYPE_SPRINKLER:
						i#=0.25
					Case OBJTYPE_SIGN:
						i#=1
					Case OBJTYPE_EXPLOSION,OBJTYPE_EXPLOSION2:
						o\InView = distance# < OBJECT_VIEWDISTANCE_UPDATEDISTANCE#+200
					Default:
						If o\ThisIsAPlant Then
							i#=3
						Else
							i#=0
						EndIf
				End Select
				o\InView2 = distance# < (OBJECT_VIEWDISTANCE#+300*i#)
				If Not o\InView2 Then o\SavedFromInsideBoxOnce=0

				If o\HasRedRing Then
					If o\Done=0 Then
					Select o\ObjType
						Case OBJTYPE_REDRING,OBJTYPE_SPEWREDRING:
							Object_EnforceTreasureRadar(o,p,o\Treasure\RedRingNo)
						Default:
							If o\ThisIsAnEnemy Then Object_EnforceTreasureRadar(o,p,-o\Enemy\EnemyNo)
					End Select
					EndIf
				EndIf

				If (o\InView2 Or o\AlwaysPresent) and o\Done=0 Then
					If Objects_GeneralViewable(o) and o\InView2 Then
							ShowEntity(o\Entity)
							If o\HasEntity2 Then ShowEntity(o\Entity2)
							If o\HasEntity3 Then ShowEntity(o\Entity3)
							If o\HasEntity4 Then ShowEntity(o\Entity4)
							If o\HasEntityX Then
								If o\ThisIsAMonitor and o\State=1 Then HideEntity(o\EntityX) Else ShowEntity(o\EntityX)
							EndIf
							If o\HasEntityCube Then ShowEntity(o\EntityCube)
							If o\HasEntityCube2 Then ShowEntity(o\EntityCube2)
					Else
						If o\InView=False Then
							HideEntity(o\Entity)
							If o\HasEntityX Then HideEntity(o\EntityX)
						EndIf
					EndIf

					If Not(Game\InsideBoxCheckerTimer>0) Then
						Object_BoxBlocker_FindBox(o)
					EndIf

					Object_BoxBlocker_ObjectInBox(o)

					If o\InView=False and o\AlwaysPresent=False and o\Psychoed=0 and o\Rubied=0 and Game\Interface\DebugPlacerOn=0 Then
						Select o\ObjType
							Case OBJTYPE_RING:
								RotateEntity o\EntityX, 0, EntityYaw(Menu\RingRotator), 0
							Case OBJTYPE_SPEWRING:
								RotateEntity o\Entity, 0, EntityYaw(Menu\RingRotator), 0
							Case OBJTYPE_GOAL,OBJTYPE_GOAL2:
								If (Not(Menu\Mission=MISSION_ESCAPE#)) Then
									RotateEntity o\Entity, 0, EntityYaw(Menu\RingRotator), 0
								Else
									RotateEntity o\Entity,0,(DeltaYaw#(p\Objects\Entity,o\Entity) - 180),0
								EndIf
								If o\ObjType=OBJTYPE_GOAL2 Then Object_Goal_Switch(o)
							Case OBJTYPE_CHECK:
								Select o\State
									Case 0:
										ShowEntity(o\Entity)
										HideEntity(o\Entity2)
										Animate o\Entity,1,0.5,1,0
									Case 1,2:
										ShowEntity(o\Entity)
										HideEntity(o\Entity2)
										If o\State=1 Then
											o\State=2
										ElseIf o\State=2 Then
											If Not(Animating(o\Entity)) Then o\State=3
										EndIf
									Case 3:
										ShowEntity(o\Entity2)
										HideEntity(o\Entity)
										Animate o\Entity2,1,0.5,1,0
								End Select
							Case OBJTYPE_BOXLIGHT:
								If Object_WhetherHasSwitches(o) Then
									Object_SwitchManager_PerObjectUpdate(o)
									Select o\Switch\SwitchOn
										Case 0: HideEntity(o\Entity) : ShowEntity(o\EntityX)
										Case 1: ShowEntity(o\Entity) : HideEntity(o\EntityX)
									End Select
								EndIf
							Case OBJTYPE_LASERH,OBJTYPE_LASERV,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
								Object_Laser_Update_SwitchControl(o,p)
							Case OBJTYPE_HINT,OBJTYPE_COUNTER:
								PointEntity(o\Entity,cam\Entity)
							Case OBJTYPE_REDRING,OBJTYPE_SPEWREDRING:
								RotateEntity o\Entity, 0, -EntityYaw(Menu\RingRotator), 0
							Case OBJTYPE_SPRINKLER:
								Select o\Mode
									Case 1,5:
										If o\Mode=1 Then i=0 Else i=1
										ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FIRESPRINKLER, o\Entity, 0, 0, 0, 0, i, 0.2)
									Case 2:
										ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_WATERSPRINKLER, o\Entity, 0, 0, 0, 0, 0, 0.2)
								End Select
							Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHWATER:
								Object_Switch_Appear(o)
							Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
								Object_Spout_Particles(o,p,true)
							Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
								Object_Transferer_UpdateAlways(o,p,d)
							Case OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_BUTTERFLY:
								Object_Visual_Update(o,p,d)
							Case OBJTYPE_BUBBLES:
								If distance#<300 Then
									If Object_Bubbles_Update(o, p, d) Then Return
								EndIf
							Case OBJTYPE_BALLOON:
								Object_Balloon_Update_Timer(o)
							Case OBJTYPE_BELL:
								Object_Bell_Update_Mesh(o,d)
							Default:
								If o\ThisIsAnEnemy Then
									Object_Enemy_DecideAppear(o,p)
									If o\Enemy\EnemyShallAppear Then
										If o\Switch\SwitchNo[0]>0 Then o\Switch\s1\Active=1
										ShowEntity(o\Entity)
										Select o\ObjType
											Case OBJTYPE_CRAWLER,OBJTYPE_BURROBOT:
												If o\Anim=4 Then
													EntityAlpha(o\Entity,0)
												Else
													EntityAlpha(o\Entity,1)
												EndIf
										End Select
										If Menu\Settings\Shadows#>0 Then Update_CircleShadow(o\Enemy\ShadowCircle, o\Entity, cam\Entity, 0)
										Object_Enemy_NoBehaviour_Anims(o)
										o\Enemy\WaitTimer3=0
										Object_Enemy_GravityStuff(o,p,d)										
										Select o\ObjType
											Case OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_BURROBOT,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_SPLATS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_GHOST: Object_Enemy_DefaultBehaviour(o,p,d)
											Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA: Object_UpdatePosition(o) : Object_Enemy_SpecialBehaviour(o,p)
											Case OBJTYPE_DRAGONFLY,OBJTYPE_CATAKILLER,OBJTYPE_MANTIS,OBJTYPE_PATABATA: o\Enemy\InRangeForced=True : Object_EnemyMovements(o,p,d)
											Case OBJTYPE_MUSHMEANIE: Object_Enemy_NoMoveBehaviour(o,p,d)
											Case OBJTYPE_OCTUS: Object_Enemy_NoBehaviour(o,p)
											Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: If distance# < OBJECT_VIEWDISTANCE_UPDATEDISTANCE#+80 Then Object_Enemy_DefaultBehaviour(o,p,d)
										End Select
										If (Not o\Enemy\InRangeForced) Then
											Select o\ObjType
												Case OBJTYPE_CHOPPER,OBJTYPE_JAWS: If o\Enemy\Underwater Then Object_EnemyLookAtPlayer(o,p)
												Case OBJTYPE_FCANNON3:
												Default: Object_EnemyLookAtPlayer(o,p)
											End Select
										EndIf
										Object_AnimateEnemy(o)
										Object_EnforceAimingShooting(o,p)
									EndIf
								ElseIf o\ThisIsABox Then
									If Menu\Stage>0 Then Object_Box_GravityStuff(o,p,d)
									If o\Box\hask Then Object_BoxBlocker_Update(o\Box\k,o,p)
								ElseIf o\ThisIsATranslator Or o\ObjType=OBJTYPE_CANNON Then
									Object_QuadEffect(o,p,d)
								ElseIf o\ThisIsAMonitor Then
									Object_Monitor_ItemRotate(o,p)
									If (Not(Animating(o\Entity))) and o\State=1 Then
										o\Done=1
										Return
									EndIf
									Object_EnforceAimingShooting(o,p)
								ElseIf o\ThisIsABumper Then
									Object_Bumper_Appearance(o,d)
								ElseIf o\ThisIsASpike Then
									Object_UpdatePosition(o)
									Object_Spike_Movement(o,p,d)
								EndIf
						End Select
						If o\HasGravity Then
							Object_EnforceGravity(o,d)
							Object_UpdatePosition(o)
							PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
							Object_UpdateRotation(o)
							RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#
						EndIf
					ElseIf Game\Interface\DebugPlacerOn=1 Then
						ShowEntity(o\Entity)
					EndIf
				Else
					Objects_Hide(o)
				EndIf
				If (o\InView Or o\AlwaysPresent Or o\Psychoed>0 Or o\Rubied>0 Or o\ObjPickedUp<>0) and o\Done=0 Then

					If Game\Interface\DebugPlacerOn=0 Then

						Object_CheckHitBox(o,p)
						Object_UpdatePosition(o)
						Object_UpdateRotation(o)

						Select o\ObjType

							Case OBJTYPE_NULL

							Case OBJTYPE_RING
								Object_Ring_Update(o, p, d)

							Case OBJTYPE_SPEWRING
								Object_SpewRing_Update(o, p, d)

							Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_FORCER,OBJTYPE_NODE,OBJTYPE_NODE2,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW
								Object_Translator_Update(o, p, d)

							Case OBJTYPE_CHECK
								Object_Check_Update(o, p)

							Case OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS
								Object_Monitor_Update(o, p)

							Case OBJTYPE_BALLOON
								Object_Balloon_Update(o, p, d)

							Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP,OBJTYPE_SPIKEBAR,OBJTYPE_SPIKESWING,OBJTYPE_SPIKESWINGBALL,OBJTYPE_SPEWSPIKEBOMB,OBJTYPE_SPIKECYLINDER
								Object_Spike_Update(o, p, d)

							Case OBJTYPE_GOAL,OBJTYPE_GOAL2
								Object_Goal_Update(o, p)

							Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT
								Object_Spout_Update(o, p)

							Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH
								Object_Laser_Update(o, p)

							Case OBJTYPE_BOXCAGE,OBJTYPE_BOXIRON,OBJTYPE_BOXMETAL,OBJTYPE_BOXWOODEN,OBJTYPE_BOXLIGHT,OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT
								Object_Box_Update(o, p, d)
								If o\Box\hask Then Object_BoxBlocker_Update(o\Box\k,o,p)

							Case OBJTYPE_BALLBUMPER,OBJTYPE_GROUNDBUMPER,OBJTYPE_METROBUMPER,OBJTYPE_PLATEBUMPER,OBJTYPE_TRIANGLEBUMPER,OBJTYPE_PADDLE
								Object_Bumper_Update(o, p, d)

							Case OBJTYPE_PIECE
								Object_Piece_Update(o, p, d)

							Case OBJTYPE_ENEMYMISSILE
								Object_EnemyMissile_Update(o, p, d)

							Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA,OBJTYPE_BOSSROBO,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3
								Object_Enemy_Update(o, p, d)

							Case OBJTYPE_BUBBLES
								Object_Bubbles_Update(o, p, d)

							Case OBJTYPE_REDRING
								Object_RedRing_Update(o, p)

							Case OBJTYPE_SPEWREDRING
								Object_SpewRedRing_Update(o, p, d)

							Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTER2,OBJTYPE_TELEPORTER3,OBJTYPE_TELEPORTER4,OBJTYPE_TELEPORTER5,OBJTYPE_TELEPORTER6,OBJTYPE_TELEPORTEREND
								Object_Teleporter_Update(o, p)

							Case OBJTYPE_OMOCHAO
								Object_Omochao_Update(o, p, d)

							Case OBJTYPE_CANNON
								Object_Cannon_Update(o, p, d)

							Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR
								Object_Transferer_Update(o, p, d)

							Case OBJTYPE_HANDLE:
								Object_Handle_Update(o, p)

							Case OBJTYPE_FPLAT
								Object_FPlat_Update(o, p, d)

							Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHWATER
								Object_Switch_Update(o, p)

							Case OBJTYPE_SWITCHTOP
								Object_SwitchTop_Update(o, p, d)

							Case OBJTYPE_ROCK,OBJTYPE_CRYSTAL,OBJTYPE_AUTO,OBJTYPE_ICICLE,OBJTYPE_ICICLEBIG,OBJTYPE_ICEDECOR
								Object_Breakable_Update(o, p, d)

							Case OBJTYPE_HINT
								Object_Hint_Update(o, p)

							Case OBJTYPE_COUNTER
								Object_Counter_Update(o, p)

							Case OBJTYPE_SIGN
								Object_Sign_Update(o, p, d)

							Case OBJTYPE_TROPICAL
								Object_Tropical_Update(o, p)

							Case OBJTYPE_CHAO
								Object_Chao_Update(o, p)

							Case OBJTYPE_FRUIT
								Object_Fruit_Update(o, p, d)

							Case OBJTYPE_SHELL
								Object_Shell_Update(o, p, d)

							Case OBJTYPE_HAT
								Object_Hat_Update(o, p, d)

							Case OBJTYPE_TOY
								Object_Toy_Update(o, p, d)

							Case OBJTYPE_DRIVE
								Object_Drive_Update(o, p, d)

							Case OBJTYPE_SEED
								Object_Seed_Update(o, p, d)

							Case OBJTYPE_TRASHCAN
								Object_TrashCan_Update(o, p, d)

							Case OBJTYPE_SACK
								Object_Sack_Update(o, p, d)

							Case OBJTYPE_BREEDER
								Object_Breeder_Update(o, p)

							Case OBJTYPE_WHISTLE
								Object_Whistle_Update(o, p)

							Case OBJTYPE_PETTER
								Object_Petter_Update(o, p)

							Case OBJTYPE_BELL
								Object_Bell_Update(o, p, d)

							Case OBJTYPE_SPRINKLER
								Object_Sprinkler_Update(o, p)

							Case OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_ORCA,OBJTYPE_CHAIR,OBJTYPE_PARASOL,OBJTYPE_AIRBALLOON,OBJTYPE_HELICOPTER,OBJTYPE_RAINBOW
								Object_Visual_Update(o, p, d)

							Case OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL,OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC
								Object_Trigger_Update(o, p)

							Case OBJTYPE_FLICKY
								Object_Flicky_Update(o, p, d)

							Case OBJTYPE_CLOUD,OBJTYPE_POLE
								Object_Jumper_Update(o, p)

							Case OBJTYPE_REPEATER
								Object_Repeater_Update(o, p)

							Case OBJTYPE_HOMMER
								Object_Hommer_Update(o, p)

							Case OBJTYPE_EXPLOSION,OBJTYPE_EXPLOSION2
								Object_Explosion_Update(o, p)

							Case OBJTYPE_CAPSULE
								Object_Capsule_Update(o, p)

							Case OBJTYPE_WISP
								Object_Wisp_Update(o, d)

							Case OBJTYPE_BOMBER1,OBJTYPE_BOMBER2:
								Object_Bomber_Update(o, p, d)

						End Select
					EndIf
				Else
					If o\ThisIsAnEnemy Then
						If Menu\Settings\Shadows#>0 Then Update_CircleShadow(o\Enemy\ShadowCircle, o\Entity, cam\Entity, 1)
					EndIf
				End If
			Next

			If Game\CheeseTimer>0 Or Game\FroggyTimer>0 Or p\Action = ACTION_JUMPDASH Or (p\Action = ACTION_DOUBLEJUMP and (p\Character=CHAR_AMY Or p\Character=CHAR_BLA Or p\Character=CHAR_EME)) Or p\Action = ACTION_DIVE Then
				Player_GetClosestObject(p,1)
			ElseIf p\LightDashRequestTimer>0 Then
				Player_GetClosestObject(p,2)
			ElseIf p\Flags\Attacking Then
				Player_GetClosestObject(p,3)
			EndIf
			If Menu\ChaoGarden=1 Then Player_GetClosestObject(p,4)

		EndIf
		Next

		If Game\InsideBoxCheckerTimer>0 Then
			Game\InsideBoxCheckerTimer=Game\InsideBoxCheckerTimer-timervalue#
		Else
			Game\InsideBoxCheckerTimer=0.5*secs#
		EndIf

		; Reset all objects if told to do so
		Objects_Reset()

		; If not enough golden enemies
		If Menu\Mission=MISSION_GOLD# and Game\Gameplay\TotalGoldEnemies=0 and Game\Victory=0 Then
			For o.tObject=Each tObject
				If o\ThisIsAnEnemy Then
					If Object_IsEnemyRobot(o\ObjType) and Object_IsActualEnemy(o\ObjType) Then
						If Rand(1,6)=1 Then
							o\Enemy\Gold=1 : EntityTexture o\Entity, Object_Texture_Gold : Gameplay_AddTotalGoldEnemies(1)
						EndIf
					EndIf
				EndIf
			Next
		EndIf
	
	End Function

	Function Objects_Hide(o.tObject)
		HideEntity(o\Entity)
		If o\HasEntity2 Then HideEntity(o\Entity2)
		If o\HasEntity3 Then HideEntity(o\Entity3)
		If o\HasEntity4 Then HideEntity(o\Entity4)
		If o\HasEntityX Then HideEntity(o\EntityX)
		If o\HasEntityCube Then HideEntity(o\EntityCube)
		If o\HasEntityCube2 Then HideEntity(o\EntityCube2)
	End Function

	Function Objects_GeneralViewable(o.tObject)
		If (o\ThisIsABumper=False and o\ThisIsAnEnemy=False) and (Not(o\ObjType=OBJTYPE_REDRING Or o\ObjType=OBJTYPE_BALLOON Or o\ObjType=OBJTYPE_BOXLIGHT Or o\ObjType=OBJTYPE_SWITCH Or o\ObjType=OBJTYPE_SWITCHBASE Or o\ObjType=OBJTYPE_SWITCHWATER Or o\ObjType=OBJTYPE_SWITCHAIR Or o\ObjType=OBJTYPE_LASERV Or o\ObjType=OBJTYPE_LASERH Or o\ObjType=OBJTYPE_RINGGATEV Or o\ObjType=OBJTYPE_RINGGATEH Or o\ObjType=OBJTYPE_CHECK)) Or (o\ObjType=OBJTYPE_REDRING and Game\Interface\DebugPlacerOn) Then Return True Else Return False
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Player_GetClosestObject_Get.tObjectList(o.tObject, p.tPlayer)
	;Create new entry
	obj.tObjectList = New tObjectList
	;Fill new entry
	obj\ObjectName = o\Entity
	obj\ObjectDistance# = EntityDistance(p\Objects\Entity, o\Entity)
	obj\ObjectType = o\ObjType
	obj\ObjectItself = o
	Return obj
End Function

Function Player_GetClosestObject(p.tPlayer, mode=1)
	; Make new variables
	obj2.tObjectList2 = New tObjectList2
	
	; Set distance up
	obj2\ShortestDistance# = 10000
	
	; Get each object
	For o.tObject = Each tObject
	If o\Done=0 Then
		
		;Check if homing able
		Select mode
		Case 1,3:
			mayhom=true
			If o\IsInBox=0 and ( ((Not(Game\CheeseTimer>0 Or Game\FroggyTimer>0)) and o\CanHoming) Or ((Game\CheeseTimer>0 Or Game\FroggyTimer>0) and o\CheeseCanHoming) ) Then mayhom=true Else mayhom=false
			If o\ObjType=OBJTYPE_HOMMER Then
				If (p\No#>0 and o\State=-1) Or (p\No#<0 and o\State=1) Then mayhom=true Else mayhom=false
			EndIf
			If p\No#<0 and (Not(o\ObjType=OBJTYPE_HOMMER)) Then mayhom=false
			If o\ThisIsAnEnemy Then
				If o\Enemy\EnemyShallAppear=False Then mayhom=False
			EndIf

			If mayhom Then
				If o\ThisIsAnEnemy=False Then
					obj.tObjectList=Player_GetClosestObject_Get.tObjectList(o,p)
				Else
					If o\Enemy\MayNotBeTargeted=False and (Not(o\Enemy\WasJustAttacked>0)) Then obj.tObjectList=Player_GetClosestObject_Get.tObjectList(o,p)
				EndIf
			End If
		Case 2:
			If o\IsInBox=0 and o\CanRingDash Then
				obj.tObjectList=Player_GetClosestObject_Get.tObjectList(o,p)
			End If
		Case 4:
			If o\ObjType=OBJTYPE_CHAO Then
				obj.tObjectList=Player_GetClosestObject_Get.tObjectList(o,p)
			End If
		End Select

	EndIf
	Next
	
	;When completed filling entries, check for the closest
	For ob2.tObjectList = Each tObjectList
		If (ob2\ObjectDistance# < obj2\ShortestDistance#) Then
			
			; Set closest entity
			obj2\ClosestObjectName = ob2\ObjectName
			obj2\ClosestObjectType = ob2\ObjectType
			obj2\ClosestObject = ob2\ObjectItself
			
			; Set entity's distance
			obj2\ShortestDistance# = ob2\ObjectDistance#

			ClosestObjectX# = EntityX(obj2\ClosestObjectName)
			If (obj2\ClosestObjectType = OBJTYPE_SPRING Or obj2\ClosestObjectType = OBJTYPE_SPRINGTRAP) Then
				ClosestObjectY# = EntityY(obj2\ClosestObjectName)+1
			Else
				ClosestObjectY# = EntityY(obj2\ClosestObjectName)
			End If			
			ClosestObjectZ# = EntityZ(obj2\ClosestObjectName)
		End If	
	Next
	
	; Once that is done, check if the distance is short enough and if character is facing the object
	If obj2\ClosestObjectName<>0 Then
	If (Player_IsStaring(p,obj2\ClosestObjectName) Or (Game\CheeseTimer>0 Or Game\FroggyTimer>0)) Or Menu\ChaoGarden=1 Then
		Select mode
		Case 1:
			If (obj2\ShortestDistance# <= 55) Then
				If obj2\ClosestObject\ThisIsAnEnemy Then obj2\ClosestObject\Enemy\WillBeHomedTimer=0.25*secs#
				p\Flags\HomingTarget\x# = ClosestObjectX#
				p\Flags\HomingTarget\y# = ClosestObjectY#
				p\Flags\HomingTarget\z# = ClosestObjectZ#
				p\Flags\HomingLocked = True
				If Not(Game\CheeseTimer>0 Or Game\FroggyTimer>0) Then
					If obj2\ClosestObject\ThisIsAnEnemy Or obj2\ClosestObject\ThisIsAMonitor Or obj2\ClosestObject\ObjType=OBJTYPE_BALLOON Then
						p\ForceAfterHomDirectionApplicable=True
						If (Not(Game\MachLock>0)) Then p\ForceAfterHomDirection#=(DeltaYaw#(p\Objects\Entity,obj2\ClosestObject\Entity) - 180) Else p\ForceAfterHomDirection#=p\Objects\Camera\TargetRotation\y#-180
					Else
						p\ForceAfterHomDirectionApplicable=False
					EndIf
					p\Action = ACTION_HOMING : EmitSmartSound(Sound_HomingAttackDeep,p\Objects\Entity) : p\HomingTimer=1.1*secs#
				Else
					If Game\CheeseTimer>0 Or Game\FroggyTimer>0 Then
						Select p\Character
							Case CHAR_CRE: If p\CheeseAttackedCount>2 Then p\CheeseRestrictTimer=3*secs#
							Case CHAR_BIG: If p\CheeseAttackedCount>3 Then p\CheeseRestrictTimer=2*secs#
						End Select
					EndIf
				EndIf
			EndIf
		Case 2:
			If (obj2\ShortestDistance# <= 50) Then
				p\Flags\RingDashTarget\x# = ClosestObjectX#
				p\Flags\RingDashTarget\y# = ClosestObjectY#
				p\Flags\RingDashTarget\z# = ClosestObjectZ#
				p\Flags\RingDashLocked = True
				p\Action = ACTION_LIGHTDASH
				p\RingDashStopTimer=1*secs#
			EndIf
		Case 3:
			If (Not(Game\MachLock>0)) and (obj2\ShortestDistance# <= 30) and obj2\ClosestObject\ThisIsAnEnemy and obj2\ClosestObject\Psychoed=0 and obj2\ClosestObject\Rubied=0 Then
			If (p\Motion\Ground and abs(obj2\ClosestObject\Position\y#-p\Objects\Position\y#)<2.5) Or p\Motion\Ground=False Then
				If p\Flags\InTargeterAttack Or p\Flags\InTargeterAirAttack Then
					obj2\ClosestObject\Enemy\WillBeHomedTimer=0.25*secs#
					p\Flags\HomingTarget\x# = ClosestObjectX#
					p\Flags\HomingTarget\y# = ClosestObjectY#
					p\Flags\HomingTarget\z# = ClosestObjectZ#
					p\Flags\HomingLocked = True
				EndIf
				p\Flags\TargeterTimer=0.5*secs#
				p\Flags\Targeter=1
			EndIf
			EndIf
		Case 4:
			If (obj2\ShortestDistance# <= 15) And (obj2\ClosestObject\ObjPickedUp=1 Or obj2\ClosestObject\ChaoObj\targetcc\ForceInterface=1) Then
				Game\Interface\ShowChaoTimer=0.1*secs#
				Game\Interface\ShowChaoNumber=obj2\ClosestObject\ChaoObj\targetcc\Number
			EndIf
		End Select
	End If
	EndIf
	
	For instance.tObjectList2 = Each tObjectList2
		Delete instance
	Next
	
	For instance2.tObjectList = Each tObjectList
		Delete instance2
	Next
End Function