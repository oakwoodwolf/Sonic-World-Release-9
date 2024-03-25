Function CreateObject(special#=0)

	Repeat
	If TempAttribute\amountpitch#<-180 Then TempAttribute\amountpitch#=TempAttribute\amountpitch#+360
	If TempAttribute\amountpitch#>180 Then TempAttribute\amountpitch#=TempAttribute\amountpitch#-360
	Until TempAttribute\amountpitch#<=180 And TempAttribute\amountpitch#>=-180
	Repeat
	If TempAttribute\amountyaw#<-180 Then TempAttribute\amountyaw#=TempAttribute\amountyaw#+360
	If TempAttribute\amountyaw#>180 Then TempAttribute\amountyaw#=TempAttribute\amountyaw#-360
	Until TempAttribute\amountyaw#<=180 And TempAttribute\amountyaw#>=-180

	;--------------------------------------------

	Repeat
	If TempAttribute\camyaw#<0 Then TempAttribute\camyaw#=TempAttribute\camyaw#+360
	If TempAttribute\camyaw#>360 Then TempAttribute\camyaw#=TempAttribute\camyaw#-360
	Until TempAttribute\camyaw#>=0 And TempAttribute\camyaw#<=360

	Repeat
	If TempAttribute\campitch#<-180 Then TempAttribute\campitch#=TempAttribute\campitch#+360
	If TempAttribute\campitch#>180 Then TempAttribute\campitch#=TempAttribute\campitch#-360
	Until TempAttribute\campitch#<=180 And TempAttribute\campitch#>=-180

	Repeat
	If TempAttribute\camroll#<-180 Then TempAttribute\camroll#=TempAttribute\camroll#+360
	If TempAttribute\camroll#>180 Then TempAttribute\camroll#=TempAttribute\camroll#-360
	Until TempAttribute\camroll#<=180 And TempAttribute\camroll#>=-180

	;--------------------------------------------

	pitch#=TempAttribute\pitch#
	yaw#=TempAttribute\yaw#
	roll#=TempAttribute\roll#

	Repeat
	If pitch#<-180 Then pitch#=pitch#+360
	If pitch#>180 Then pitch#=pitch#-360
	Until pitch#<=180 And pitch#>=-180
	Repeat
	If yaw#<-180 Then yaw#=yaw#+360
	If yaw#>180 Then yaw#=yaw#-360
	Until yaw#<=180 And yaw#>=-180
	Repeat
	If roll#<-180 Then roll#=roll#+360
	If roll#>180 Then roll#=roll#-360
	Until roll#<=180 And roll#>=-180

	;--------------------------------------------

	OBJECT_VIEWDISTANCE_COUNT# = OBJECT_VIEWDISTANCE_COUNT# + 1
	While(OBJECT_VIEWDISTANCE_IDCOUNT#=OBJECT_VIEWDISTANCE_COUNT#) : OBJECT_VIEWDISTANCE_COUNT# = OBJECT_VIEWDISTANCE_COUNT# + 1 : Wend
	If OBJECT_VIEWDISTANCE_IDCOUNT#<OBJECT_VIEWDISTANCE_COUNT# Then OBJECT_VIEWDISTANCE_IDCOUNT#=OBJECT_VIEWDISTANCE_COUNT#
	TempAttribute\ObjectID=OBJECT_VIEWDISTANCE_IDCOUNT#
	If TempAttribute\ID#>0 Then
		TempAttribute\ObjectID=TempAttribute\ID#
		If OBJECT_VIEWDISTANCE_LARGESTIDCOUNT#<OBJECT_VIEWDISTANCE_IDCOUNT# Then OBJECT_VIEWDISTANCE_LARGESTIDCOUNT#=OBJECT_VIEWDISTANCE_IDCOUNT#
	EndIf

	;--------------------------------------------

	If TempAttribute\amount1#<1 Then TempAttribute\amount1#=1
	If TempAttribute\amount2#<1 Then TempAttribute\amount2#=1
	If TempAttribute\amount3#<1 Then TempAttribute\amount3#=1

	If TempAttribute\amount1#>1 And TempAttribute\amountspace1#<4 Then TempAttribute\amountspace1#=4
	If TempAttribute\amount2#>1 And TempAttribute\amountspace2#<4 Then TempAttribute\amountspace2#=4
	If TempAttribute\amount3#>1 And TempAttribute\amountspace3#<4 Then TempAttribute\amountspace3#=4

	Select TempAttribute\amountcircle#
		Case 0:
			For i=0 To TempAttribute\amount1#-1
			For j=0 To TempAttribute\amount2#-1
			For h=0 To TempAttribute\amount3#-1

			TempAttribute\TempObject=CreatePivot()
			TranslateEntity TempAttribute\TempObject,TempAttribute\x#,TempAttribute\y#,TempAttribute\z#
			RotateEntity TempAttribute\TempObject,TempAttribute\amountpitch#,TempAttribute\amountyaw#,0
			MoveEntity TempAttribute\TempObject,-TempAttribute\amountspace2#*j,TempAttribute\amountspace3#*h,TempAttribute\amountspace1#*i

			;--------------------------------------------

			x#=EntityX(TempAttribute\TempObject)
			y#=EntityY(TempAttribute\TempObject)
			z#=EntityZ(TempAttribute\TempObject)

			;--------------------------------------------

			CreateObject_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)

			;--------------------------------------------

			FreeEntity TempAttribute\TempObject

			Next
			Next
			Next
		Case 1:
			For i=0 To TempAttribute\amount1#-1
			For h=0 To TempAttribute\amount3#-1

			TempAttribute\TempObject=CreatePivot()
			TranslateEntity TempAttribute\TempObject,TempAttribute\x#,TempAttribute\y#,TempAttribute\z#
			RotateEntity TempAttribute\TempObject,0,TempAttribute\amountyaw#+(360/TempAttribute\amount1#)*i,0
			MoveEntity TempAttribute\TempObject,0,TempAttribute\amountspace3#*h,TempAttribute\amountspace1#

			;--------------------------------------------

			x#=EntityX(TempAttribute\TempObject)
			y#=EntityY(TempAttribute\TempObject)
			z#=EntityZ(TempAttribute\TempObject)

			;--------------------------------------------

			CreateObject_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)

			;--------------------------------------------

			FreeEntity TempAttribute\TempObject

			Next
			Next
	End Select

End Function

Function CreateObject_Create(x#, y#, z#, pitch#, yaw#, roll#, special#=0)

		Select TempAttribute\ObjectNo
		Case -10,-20,-11,-21,-12,-22,-13,-23,-14,-24:
			Select TempAttribute\ObjectNo
				Case -10: pe.tPlayer = Player_Create(-1, 0, 0)
				Case -20: pe.tPlayer = Player_Create(-1, 1, 0)
				Case -11: pe.tPlayer = Player_Create(-1, 0, 1)
				Case -21: pe.tPlayer = Player_Create(-1, 1, 1)
				Case -12: pe.tPlayer = Player_Create(-1, 0, 2)
				Case -22: pe.tPlayer = Player_Create(-1, 1, 2)
				Case -13: pe.tPlayer = Player_Create(-1, 0, 3)
				Case -23: pe.tPlayer = Player_Create(-1, 1, 3)
				Case -14: pe.tPlayer = Player_Create(-1, 0, 4)
				Case -24: pe.tPlayer = Player_Create(-1, 1, 4)
			End Select
			Player_SetPosition(pe, x#, y#, z#, yaw#)
			pe\Rival\InitialPositionX#=x#
			pe\Rival\InitialPositionY#=y#
			pe\Rival\InitialPositionZ#=z#
			pe\Rival\InitialRotationY#=yaw#
		Case OBJTYPE_RING:
			obj.tObject = Object_Ring_Create(x#, y#, z#, TempAttribute\power#)
		Case OBJTYPE_REDRING:
			obj.tObject = Object_RedRing_Create(x#, y#, z#, TempAttribute\power#)
		Case OBJTYPE_SPRING,OBJTYPE_SPRINGICE,OBJTYPE_PANEL1,OBJTYPE_PANEL2,OBJTYPE_BSPRING,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTHORN,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_FORCER,OBJTYPE_NODE,OBJTYPE_NODE2,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW:
			obj.tObject = Object_Translator_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#, special#)
		Case OBJTYPE_CHECK:
			obj.tObject = Object_Check_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS:
			obj.tObject = Object_Monitor_Create(special#, x#, y#, z#,pitch#,yaw#,roll#)
		Case OBJTYPE_BALLOON:
			obj.tObject = Object_Balloon_Create(x#, y#, z#)
		Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP,OBJTYPE_SPIKEBAR,OBJTYPE_SPIKECYLINDER,OBJTYPE_SPIKESWINGBALL:
			obj.tObject = Object_Spike_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_SPIKESWING:
			For i=1 To special#
				obj.tObject = Object_Spike_Create(x#, y#, z#, pitch#, yaw#+(i-1)*(360/special#), roll#)
			Next
		Case OBJTYPE_GOAL:
			obj.tObject = Object_Goal_Create(special#, x#, y#, z#, TempAttribute\teleportername$)
		Case OBJTYPE_GOAL2:
			obj.tObject = Object_Goal_Create(special#, x#, y#, z#, TempAttribute\teleportername$, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#)
		Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
			obj.tObject = Object_Spout_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
			obj.tObject = Object_Laser_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#, TempAttribute\power#)
		Case OBJTYPE_BOXCAGE,OBJTYPE_BOXYELLOW,OBJTYPE_BOXIRON,OBJTYPE_BOXMETAL,OBJTYPE_BOXWOODEN,OBJTYPE_BOXLIGHT,OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT:
			obj.tObject = Object_Box_Create(special#, x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#)
		Case OBJTYPE_BALLBUMPER,OBJTYPE_GROUNDBUMPER,OBJTYPE_METROBUMPER,OBJTYPE_PLATEBUMPER,OBJTYPE_TRIANGLEBUMPER,OBJTYPE_PADDLE:
			obj.tObject = Object_Bumper_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_GUNNER,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
			obj.tObject = Object_Enemy_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\carnival#)
		Case OBJTYPE_WARPRING:
			obj.tObject = Object_Goal_Create(special#,x#, y#, z#, TempAttribute\teleportername$,0,0,0,TempAttribute\power#)
			
		Case OBJTYPE_BUBBLES:
			obj.tObject = Object_Bubbles_Create(x#, y#, z#, 0)
		Case OBJTYPE_SHARD:
			obj.tObject = Object_Shard_Create(x#, y#, z#)
		Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTER3,OBJTYPE_TELEPORTER4,OBJTYPE_TELEPORTER5,OBJTYPE_TELEPORTER6,OBJTYPE_TELEPORTEREND:
			obj.tObject = Object_Teleporter_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\teleporterno#)
		Case OBJTYPE_TELEPORTER2:
			obj.tObject = Object_Teleporter_Create(x#, y#, z#, pitch#, yaw#, roll#, 0, TempAttribute\teleportername$)
		Case OBJTYPE_OMOCHAO:
			obj.tObject = Object_Omochao_Create(x#, y#, z#, pitch#, yaw#, roll#,TempAttribute\omovoiceon,TempAttribute\omovoicepath$)
		Case OBJTYPE_CANNON:
			obj.tObject = Object_Cannon_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#)
		Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
			obj.tObject = Object_Transferer_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#, special#)
		Case OBJTYPE_HANDLE:
			obj.tObject = Object_Handle_Create(x#, y#, z#, yaw#)
		Case OBJTYPE_FPLAT:
			obj.tObject = Object_FPlat_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_SWITCH,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHWATER:
			obj.tObject = Object_Switch_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\switchstatus#, TempAttribute\power#, special#)
		Case OBJTYPE_SWITCHTOP:
			obj.tObject = Object_SwitchTop_Create(x#, y#, z#, yaw#, TempAttribute\switch1#)
		Case OBJTYPE_ROCK,OBJTYPE_CRYSTAL,OBJTYPE_AUTO,OBJTYPE_ICICLE,OBJTYPE_ICICLEBIG,OBJTYPE_ICEDECOR:
			obj.tObject = Object_Breakable_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_HINT:
			obj.tObject = Object_Hint_Create(x#, y#, z#, TempAttribute\hint1$, TempAttribute\hint2$,TempAttribute\power#,TempAttribute\soundpath$)
		Case OBJTYPE_COUNTER:
			obj.tObject = Object_Counter_Create(x#, y#, z#, special#, TempAttribute\power#)
		Case OBJTYPE_SIGN:
			obj.tObject = Object_Sign_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_TROPICAL
			obj.tObject = Object_Tropical_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_TREE1,OBJTYPE_TREE2,OBJTYPE_TREE3,OBJTYPE_TREE4,OBJTYPE_TREE5,OBJTYPE_TREE6,OBJTYPE_SHRUB1,OBJTYPE_SHRUB2,OBJTYPE_SHRUB3,OBJTYPE_SHRUB4,OBJTYPE_SHRUB5,OBJTYPE_SHRUB6,OBJTYPE_BUSH1,OBJTYPE_BUSH2,OBJTYPE_BUSH3,OBJTYPE_BUSH4,OBJTYPE_BUSH5,OBJTYPE_BUSH6,OBJTYPE_BUSH7,OBJTYPE_GRASS1,OBJTYPE_GRASS2,OBJTYPE_GRASS3,OBJTYPE_GRASS4,OBJTYPE_GRASS5,OBJTYPE_GRASS6,OBJTYPE_GRASS7,OBJTYPE_GRASS8,OBJTYPE_GRASS9,OBJTYPE_GRASS10,OBJTYPE_SAKURA1,OBJTYPE_SAKURA2,OBJTYPE_SAKURA3,OBJTYPE_SAKURA4,OBJTYPE_SAKURA5,OBJTYPE_SAKURA6,OBJTYPE_PALM1,OBJTYPE_PALM2,OBJTYPE_PALM3,OBJTYPE_PALM4,OBJTYPE_WILDPALM1,OBJTYPE_WILDPALM2,OBJTYPE_WILDPALM3,OBJTYPE_WILDPALM4,OBJTYPE_WILDPALM5,OBJTYPE_WILDPALM6,OBJTYPE_FLOWER1,OBJTYPE_FLOWER2,OBJTYPE_FLOWER3,OBJTYPE_FLOWER4,OBJTYPE_FLOWER5,OBJTYPE_SNOWY1,OBJTYPE_SNOWY2,OBJTYPE_SNOWY3,OBJTYPE_SNOWY4,OBJTYPE_SNOWY5,OBJTYPE_SNOWY6,OBJTYPE_VINE1,OBJTYPE_DRYTREE1,OBJTYPE_DRYTREE2,OBJTYPE_DRYTREE3,OBJTYPE_ADABAT1,OBJTYPE_ADABAT2,OBJTYPE_ADABAT3,OBJTYPE_ADABAT4,OBJTYPE_ADABAT5:
			If Menu\Settings\RealDisablePlants#=0 Then
				obj.tObject = Object_Plant_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#)
			EndIf
		Case OBJTYPE_TRASHCAN
			obj.tObject = Object_TrashCan_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_SACK
			obj.tObject = Object_Sack_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_GARDENPOINT
			obj.tObject = Object_GardenPoint_Create(x#, y#, z#, yaw#)
		Case OBJTYPE_BELL:
			obj.tObject = Object_Bell_Create(x#, y#, z#)
		Case OBJTYPE_SPRINKLER:
			obj.tObject = Object_Sprinkler_Create(x#, y#, z#, yaw#, special#)
		Case OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC,OBJTYPE_ORCA,OBJTYPE_CHAIR,OBJTYPE_PARASOL,OBJTYPE_AIRBALLOON,OBJTYPE_HELICOPTER,OBJTYPE_RAINBOW:
			Select TempAttribute\ObjectNo
				Case OBJTYPE_BUTTERFLY: j=Rand(1,2)
				Case OBJTYPE_SEAGULL: j=Rand(1,3)
				Case OBJTYPE_SEAC: j=Rand(1,5)
				Default: j=1
			End Select
			For i=1 To j
				obj.tObject = Object_Visual_Create(x#, y#+2*(i-1), z#, pitch#, yaw#, roll#, TempAttribute\power#, special#)
			Next
		Case OBJTYPE_STAGEVISUAL1,OBJTYPE_STAGEVISUAL2,OBJTYPE_STAGEVISUAL3,OBJTYPE_STAGEVISUAL4,OBJTYPE_STAGEVISUAL5
			obj.tObject = Object_Visual_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#, special#)
		Case OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL:
			obj.tObject = Object_Trigger_Create(x#, y#, z#, special#)
		Case OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC:
			obj.tObject = Object_Trigger_Create(x#, y#, z#, special#, TempAttribute\power#)
		Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA:
			obj.tObject = Object_Enemy_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, 0)
		Case OBJTYPE_REPEATER:
			obj.tObject = Object_Repeater_Create(TempAttribute\hint1$, x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#, TempAttribute\hasd#, TempAttribute\dx#, TempAttribute\dy#, TempAttribute\dz#)
		Case OBJTYPE_CLOUD,OBJTYPE_POLE:
			obj.tObject = Object_Jumper_Create(x#, y#, z#, yaw#, TempAttribute\power#)
		Case OBJTYPE_EXPLOSION,OBJTYPE_EXPLOSION2:
			obj.tObject = Object_Explosion_Create(x#, y#, z#)
		Case OBJTYPE_CAPSULE:
			obj.tObject = Object_Capsule_Create(x#, y#, z#, yaw#)
		Case OBJTYPE_BOMBER1,OBJTYPE_BOMBER2:
			obj.tObject = Object_Bomber_Create(x#, y#, z#, yaw#)
		End Select

End Function

;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________

Function CreateSpecialStageContent_Floor(Path$,length=425)

	Select(Abs(Menu\Stage))
		Case 1: Game\Stage\Properties\SpecialStageRingGateRequirement=300
		Case 2: Game\Stage\Properties\SpecialStageRingGateRequirement=300
		Case 3: Game\Stage\Properties\SpecialStageRingGateRequirement=250
		Case 4: Game\Stage\Properties\SpecialStageRingGateRequirement=250
		Case 5: Game\Stage\Properties\SpecialStageRingGateRequirement=200
		Case 6: Game\Stage\Properties\SpecialStageRingGateRequirement=200
		Case 7: Game\Stage\Properties\SpecialStageRingGateRequirement=200
	End Select

	Game\LimitTime=90*secs#
	Game\IdealTime=80*secs#
	Game\IdealScore=0

	block = LoadMesh(Path$+"Stage\block.b3d", Game\Stage\Root) : ScaleEntity(block,2,2,2)
	blockx = LoadMesh(Path$+"Stage\blockx.b3d", Game\Stage\Root) : ScaleEntity(blockx,2,2,2)
	blockL = LoadMesh(Path$+"Stage\blockL.b3d", Game\Stage\Root) : ScaleEntity(blockL,2,2,2)
	blockR = LoadMesh(Path$+"Stage\blockR.b3d", Game\Stage\Root) : ScaleEntity(blockR,2,2,2)

	Game\Stage\Properties\SpecialStageTexture=LoadTexture(Path$+"Stage\block.png", 1+2)

	hy=0
	For j=-80 To length+30
		If Abs(Menu\Stage)=3 Or Abs(Menu\Stage)=7 Then
			If Rand(1,20)=1 Then hy=Abs(hy-1)
		EndIf

		For i=1 To 7
			h=False
			h2=False
			h3=False
			If j<0 Or j>length Then h2=True
			If i=1 Or i=7 Then h3=True
			If h2 Or h3 Then
				h=True
			Else
				h=True
				If Abs(Menu\Stage)>=4 And Abs(Menu\Stage)<=5 Then
					If Rand(1,9)=1 Then h=False
				ElseIf Abs(Menu\Stage)>=6 Then
					If Rand(1,7)=1 Then h=False
				EndIf
			EndIf

			m.MeshStructure = New MeshStructure

			Select i
			Case 1: m\Entity = CopyEntity(blockR, Game\Stage\Root)
			Case 7: m\Entity = CopyEntity(blockL, Game\Stage\Root)
			Default: If h Then m\Entity = CopyEntity(block, Game\Stage\Root) Else m\Entity = CopyEntity(blockx, Game\Stage\Root)
			End Select
			m\ForSpecialStage=1

			EntityPickMode(m\Entity, 2, True)
			EntityShininess(m\Entity, 0)
			If h Then
				EntityAlpha(m\Entity, 0.75)
				EntityType(m\Entity, COLLISION_WORLD_POLYGON)
				EntityTexture(m\Entity, Game\Stage\Properties\SpecialStageTexture)
				EntityColor(m\Entity, Rand(50,200), Rand(50,200), Rand(50,200))
			EndIf
			RotateEntity(m\Entity, 0, 0, 0)

			x#=(i-4)*20 : z#=j*40
			If h2 Then hy=0
			y#=0+20*hy
			PositionEntity(m\Entity, x#, y#, z#)
			m\InitialPosX#=x# : m\InitialPosY#=y# : m\InitialPosZ#=z#

			If (Not(h2 Or h3)) And h Then
				Select(Rand(1,3))
				Case 1,2:
					Select(Rand(1,30))
						Case 1: CreateSpecialStageContent_Obj(OBJTYPE_RINGS,x#,y#,z#)
						Default: CreateSpecialStageContent_Obj(OBJTYPE_RING,x#,y#,z#-20)
					End Select
				Case 3:
					Select(Abs(Menu\Stage))
						Case 1: CreateSpecialStageContent_Obj(OBJTYPE_SPIKEBOMB,x#,y#,z#)
						Case 2:
							Select(Rand(1,3))
								Case 1: CreateSpecialStageContent_Obj(OBJTYPE_SPIKEBOMB,x#,y#,z#)
								Case 2: CreateSpecialStageContent_Obj(OBJTYPE_SPIKECRUSHER,x#,y#,z#)
								Case 3: CreateSpecialStageContent_Obj(OBJTYPE_GROUNDBUMPER,x#,y#,z#)
							End Select
						Case 3: CreateSpecialStageContent_Obj(OBJTYPE_SHOCKSPOUT,x#,y#,z#)
						Case 4:
							Select(Rand(1,2))
								Case 1: CreateSpecialStageContent_Obj(OBJTYPE_ORBINAUT,x#,y#,z#)
								Case 2: CreateSpecialStageContent_Obj(OBJTYPE_BOXNITRO,x#,y#,z#)
							End Select
						Case 5:
							Select(Rand(1,4))
								Case 1: CreateSpecialStageContent_Obj(OBJTYPE_BOXNITRO,x#,y#,z#)
								Case 2: CreateSpecialStageContent_Obj(OBJTYPE_SPINA,x#,y#,z#)
								Case 3: CreateSpecialStageContent_Obj(OBJTYPE_SPRING,x#,y#,z#)
								Case 4: CreateSpecialStageContent_Obj(OBJTYPE_ICESPOUT,x#,y#,z#)
							End Select
						Case 6:
							Select(Rand(1,4))
								Case 1: CreateSpecialStageContent_Obj(OBJTYPE_SPIKEBOMB,x#,y#,z#)
								Case 2: CreateSpecialStageContent_Obj(OBJTYPE_SPIKECRUSHER,x#,y#,z#)
								Case 3: CreateSpecialStageContent_Obj(OBJTYPE_ORBINAUT,x#,y#,z#)
								Case 4: CreateSpecialStageContent_Obj(OBJTYPE_GROUNDBUMPER,x#,y#,z#)
							End Select
						Case 7:
							Select(Rand(1,5))
								Case 1: CreateSpecialStageContent_Obj(OBJTYPE_SPIKEBOMB,x#,y#,z#)
								Case 2: CreateSpecialStageContent_Obj(OBJTYPE_SPIKECRUSHER,x#,y#,z#)
								Case 3: CreateSpecialStageContent_Obj(OBJTYPE_SHOCKSPOUT,x#,y#,z#)
								Case 4: CreateSpecialStageContent_Obj(OBJTYPE_SPRING,x#,y#,z#)
								Case 5: CreateSpecialStageContent_Obj(OBJTYPE_TRAP,x#,y#,z#)
							End Select
					End Select
				End Select
			EndIf
		Next
	Next

	CreateSpecialStageContent_Obj(OBJTYPE_RINGGATEV,120,0,(length+10)*40)
	CreateSpecialStageContent_Obj(OBJTYPE_GOAL,0,0,(length+20)*40)

	FreeEntity block
	FreeEntity blockx
	FreeEntity blockL
	FreeEntity blockR

	Game\Stage\Properties\SpecialStageSkydomeTexture=LoadTexture(Path$+"Skydome\sky.png", 1+2)
	EntityTexture(Game\Stage\Properties\Skydome,Game\Stage\Properties\SpecialStageSkydomeTexture)
	Game\Stage\Properties\SpecialStageSkydomeTargetR=Rand(50,200)
	Game\Stage\Properties\SpecialStageSkydomeTargetG=Rand(50,200)
	Game\Stage\Properties\SpecialStageSkydomeTargetB=Rand(50,200)
	Game\Stage\Properties\SpecialStageSkydomeR=Game\Stage\Properties\SpecialStageSkydomeTargetR
	Game\Stage\Properties\SpecialStageSkydomeG=Game\Stage\Properties\SpecialStageSkydomeTargetG
	Game\Stage\Properties\SpecialStageSkydomeB=Game\Stage\Properties\SpecialStageSkydomeTargetB
	EntityColor(Game\Stage\Properties\Skydome,Game\Stage\Properties\SpecialStageSkydomeR,Game\Stage\Properties\SpecialStageSkydomeG,Game\Stage\Properties\SpecialStageSkydomeB)

	For h=-1 To 1
	For j=-80 To length+30
		If Not h=0 Then
		For i=1 To Rand(1,5)
			m.MeshStructure = New MeshStructure

			m\Entity = LoadMesh(Path$+"Stage\prop"+Int(Rand(1,9))+".b3d", Game\Stage\Root) : ScaleEntity(m\Entity,17,17,17)
			m\ForSpecialStage=2
			m\ChangeTexOrder=Rand(1,3)

			EntityPickMode(m\Entity, 2, True)
			EntityShininess(m\Entity, 0)
			EntityAlpha(m\Entity, 0.75)
			EntityColor(m\Entity, Rand(50,200), Rand(50,200), Rand(50,200))
			RotateEntity(m\Entity, Rand(1,360), Rand(1,360), Rand(1,360))

			x#=h*(500+(i-1)*500)+Rand(-300,300) : z#=j*40
			j=j+Rand(0,20)
			y#=Rand(-100,300)
			PositionEntity(m\Entity, x#, y#, z#)
			m\InitialPosX#=x# : m\InitialPosY#=y# : m\InitialPosZ#=z#
		Next
		EndIf
	Next
	Next

End Function

;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________

Function CreateSpecialStageContent_Obj(objtype,x#,y#,z#)

	TempAttribute\ObjectNo=objtype
	TempAttribute\x#=x# : TempAttribute\y#=y# : TempAttribute\z#=z#
	TempAttribute\pitch#=0 : TempAttribute\yaw#=0 : TempAttribute\roll#=0
	ResetTempAmounts()
	Select objtype
		Case OBJTYPE_RING:
			If Rand(1,5)=1 Then
				TempAttribute\y#=y#+4.0
				TempAttribute\amount1# = Rand(3,4)
				TempAttribute\amountspace1# = 10
				Select Rand(1,10)
					Case 1,2,3,4,5,6,7,8,9
						TempAttribute\power#=1
					Case 10
						TempAttribute\power#=Rand(5,10)
				End Select
				CreateObject()
			EndIf
		Case OBJTYPE_RINGS:
			If Rand(1,5)=1 Then
				TempAttribute\y#=y#+6.9
				CreateObject()
			EndIf
		Case OBJTYPE_SPIKEBOMB:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+6.48
				CreateObject()
			EndIf
		Case OBJTYPE_SPIKECRUSHER:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+19
				CreateObject()
			EndIf
		Case OBJTYPE_SHOCKSPOUT:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+2.98
				CreateObject()
			EndIf
		Case OBJTYPE_BOXNITRO:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+3.1
				CreateObject()
			EndIf
		Case OBJTYPE_SPINA:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+4.1
				TempAttribute\yaw#=180
				CreateObject()
			EndIf
		Case OBJTYPE_ORBINAUT:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+4.1
				TempAttribute\yaw#=180
				CreateObject()
			EndIf
		Case OBJTYPE_GROUNDBUMPER:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+1.99
				CreateObject()
			EndIf
		Case OBJTYPE_SPRING:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+3.64
				TempAttribute\pitch#=22.5
				TempAttribute\power#=1.5
				TempAttribute\lockcontrol#=0
				TempAttribute\lockcam#=0
				TempAttribute\lockrun#=0
				TempAttribute\hasd#=0
				CreateObject()
			EndIf
		Case OBJTYPE_TRAP:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+6.9
				CreateObject()
			EndIf
		Case OBJTYPE_ICESPOUT:
			If Rand(1,10)=1 Then
				TempAttribute\y#=y#+2.98
				CreateObject()
			EndIf
		Case OBJTYPE_RINGGATEV:
			TempAttribute\y#=y#+8.73
			TempAttribute\power#=Game\Stage\Properties\SpecialStageRingGateRequirement
			TempAttribute\amount1# = 13
			TempAttribute\amount3# = 6
			TempAttribute\amountyaw# = 90
			TempAttribute\amountspace1# = 20
			TempAttribute\amountspace3# = 13.67
			CreateObject()
		Case OBJTYPE_GOAL:
			TempAttribute\y#=y#+15.0
			CreateObject()
	End Select

End Function

Function ResetTempAmounts()
	TempAttribute\amountcircle# = 0
	TempAttribute\amount1# = 1
	TempAttribute\amount2# = 1
	TempAttribute\amount3# = 1
	TempAttribute\amountpitch# = 0
	TempAttribute\amountyaw# = 0
	TempAttribute\amountspace1# = 0
	TempAttribute\amountspace2# = 0
	TempAttribute\amountspace3# = 0
	TempAttribute\switch1#=0
	TempAttribute\switch2#=0
	TempAttribute\switch3#=0
	TempAttribute\carnival#=0
End Function

;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________

Function CreateSprinkler(special=0)
	If special=1 Then
		TempAttribute\ObjectNo=OBJTYPE_DOOMSEYE
	Else
		TempAttribute\ObjectNo=OBJTYPE_SPRINKLR
	EndIf
	TempAttribute\x#=Game\Stage\Properties\StartX# : TempAttribute\y#=Game\Stage\Properties\StartY# : TempAttribute\z#=Game\Stage\Properties\StartZ#
	TempAttribute\pitch#=0 : TempAttribute\yaw#=Game\Stage\Properties\StartDirection# : TempAttribute\roll#=0
	ResetTempAmounts()

	temp=CreatePivot()
	PositionEntity temp, TempAttribute\x#, TempAttribute\y#, TempAttribute\z#, 1
	RotateEntity temp, 0, TempAttribute\yaw#, 0, 1
	MoveEntity temp, 0, 30, -50
	TempAttribute\x#=EntityX#(temp,1) : TempAttribute\y#=EntityY#(temp,1) : TempAttribute\z#=EntityZ#(temp,1)
	FreeEntity temp

	CreateObject()
End Function

;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;~IDEal Editor Parameters:
;~C#Blitz3D