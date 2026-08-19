Function CreateObject(special#=0)
	
	If TempAttribute\dontcreate=1 Then Return
	
	If Game\Interface\DebugPlacerOn=0 Then
		If TempAttribute\missionno<>Menu\MissionNo Then Return
	EndIf
	
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
		Case OBJTYPE_TOKEN:
			If Menu\TutorialMode=0 And Menu\ChaoGarden=0 Then obj.tObject = Object_Token_Create(x#, y#, z#)
		Case OBJTYPE_DIAMOND:
			obj.tObject = Object_Diamond_Create(x#, y#, z#, TempAttribute\power#)
		Case OBJTYPE_RING:
			obj.tObject = Object_Ring_Create(x#, y#, z#, TempAttribute\power#,TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#, TempAttribute\switchmode)
		Case OBJTYPE_COLLECTIBLE:
			obj.tObject = Object_Collectible_Create(x#, y#, z#)
		Case OBJTYPE_TIMER:
			obj.tObject = Object_Timer_Create(x#, y#, z#, TempAttribute\power#)
		Case OBJTYPE_REDRING:
			obj.tObject = Object_RedRing_Create(x#, y#, z#, TempAttribute\power#)
		Case OBJTYPE_SPRING,OBJTYPE_BSPRING,OBJTYPE_PANEL1,OBJTYPE_PANEL2,OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAP,OBJTYPE_SPRINGTRAPX,OBJTYPE_PAD,OBJTYPE_RAILPAD,OBJTYPE_RAMP,OBJTYPE_TRAMP,OBJTYPE_HOOP,OBJTYPE_THOOP,OBJTYPE_ACCEL,OBJTYPE_LOCKER,OBJTYPE_FORCER,OBJTYPE_NODE,OBJTYPE_NODE2,OBJTYPE_FAN,OBJTYPE_BFAN,OBJTYPE_BFANLOW:
			obj.tObject = Object_Translator_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#, special#)
		Case OBJTYPE_CHECK:
			If Menu\MissionPerfect=0 Then obj.tObject = Object_Check_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_RINGS,OBJTYPE_LIFE,OBJTYPE_TRAP,OBJTYPE_INVINC,OBJTYPE_SHOES,OBJTYPE_NSHIELD,OBJTYPE_FSHIELD,OBJTYPE_BSHIELD,OBJTYPE_TSHIELD,OBJTYPE_ESHIELD,OBJTYPE_BOMB,OBJTYPE_BOARD,OBJTYPE_GLIDER,OBJTYPE_CAR,OBJTYPE_BIKE,OBJTYPE_BOBSLEIGH,OBJTYPE_TORNADO,OBJTYPE_CYCLONE,OBJTYPE_KART,OBJTYPE_WINGS:
			obj.tObject = Object_Monitor_Create(special#, x#, y#, z#)
		Case OBJTYPE_BALLOON:
			obj.tObject = Object_Balloon_Create(x#, y#, z#)
		Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER,OBJTYPE_SPIKEDRILL,OBJTYPE_SPIKETIMED,OBJTYPE_SPIKETRAP,OBJTYPE_SPIKEBAR,OBJTYPE_SPIKECYLINDER,OBJTYPE_SPIKESWINGBALL:
			obj.tObject = Object_Spike_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_SPIKESWING:
			For i=1 To special#
				obj.tObject = Object_Spike_Create(x#, y#, z#, pitch#, yaw#+(i-1)*(360/special#), roll#)
			Next
		Case OBJTYPE_MOONRING:
			obj.tObject = Object_MoonRing_Create(x#, y#, z#, TempAttribute\power#, TempAttribute\teleporterno#,TempAttribute\hint1$,TempAttribute\switch1#)
		Case OBJTYPE_GOAL:
			obj.tObject = Object_Goal_Create(special#, x#, y#, z#, TempAttribute\teleportername$)
		Case OBJTYPE_WARPRING:
			obj.tObject = Object_WarpRing_Create(x#, y#, z#, TempAttribute\stagefolder$,TempAttribute\stagemissionno)
		Case OBJTYPE_MISSIONCARD
			obj.tObject = Object_MissionCard_Create(x#, y#, z#, TempAttribute\stagefolder$,TempAttribute\stagemissionno)
		Case OBJTYPE_GOAL2:
			obj.tObject = Object_Goal_Create(special#, x#, y#, z#, TempAttribute\teleportername$, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#)
		Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
			obj.tObject = Object_Spout_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_LASERV,OBJTYPE_LASERH,OBJTYPE_RINGGATEV,OBJTYPE_RINGGATEH:
			obj.tObject = Object_Laser_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#, TempAttribute\power#)
		Case OBJTYPE_BOXCAGE,OBJTYPE_BOXIRON,OBJTYPE_BOXMETAL,OBJTYPE_BOXWOODEN,OBJTYPE_BOXLIGHT,OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT:
			obj.tObject = Object_Box_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#)
		Case OBJTYPE_BALLBUMPER,OBJTYPE_GROUNDBUMPER,OBJTYPE_METROBUMPER,OBJTYPE_PLATEBUMPER,OBJTYPE_TRIANGLEBUMPER,OBJTYPE_PADDLE:
			obj.tObject = Object_Bumper_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_EGUNNER,OBJTYPE_PAWN,OBJTYPE_HUNTER,OBJTYPE_INACTIVE,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPUNA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_CRABMEAT,OBJTYPE_JAWS,OBJTYPE_SPINY,OBJTYPE_GRABBER,OBJTYPE_KIKI,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_FIGHTER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_ORBINAUT,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_STEELION,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALKIRY,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_DRAGONFLY,OBJTYPE_MADMOLE,OBJTYPE_MANTA,OBJTYPE_MUSHMEANIE,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CATAKILLER,OBJTYPE_CLUCKOID,OBJTYPE_MANTIS,OBJTYPE_NEBULA,OBJTYPE_ROLLER,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_TOXO,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
			obj.tObject = Object_Enemy_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\carnival#)
		Case OBJTYPE_BUBBLES:
			obj.tObject = Object_Bubbles_Create(x#, y#, z#, 0)
		Case OBJTYPE_SHARD:
			obj.tObject = Object_Shard_Create(x#, y#, z#)
		Case OBJTYPE_TELEPORTER,OBJTYPE_TELEPORTER3,OBJTYPE_TELEPORTER4,OBJTYPE_TELEPORTER5,OBJTYPE_TELEPORTER6,OBJTYPE_TELEPORTEREND:
			obj.tObject = Object_Teleporter_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\teleporterno#)
		Case OBJTYPE_TELEPORTER2:
			obj.tObject = Object_Teleporter_Create(x#, y#, z#, pitch#, yaw#, roll#, 0, TempAttribute\teleportername$)
		Case OBJTYPE_OMOCHAO:
			obj.tObject = Object_Omochao_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_CANNON:
			obj.tObject = Object_Cannon_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#)
		Case OBJTYPE_PROPELLER,OBJTYPE_PULLEY,OBJTYPE_ROCKET,OBJTYPE_ELEVATOR:
			obj.tObject = Object_Transferer_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#, special#)
		Case OBJTYPE_HANDLE:
			obj.tObject = Object_Handle_Create(x#, y#, z#, yaw#)
		Case OBJTYPE_FPLAT:
			obj.tObject = Object_FPlat_Create(x#, y#, z#, pitch#, yaw#, roll#)
		Case OBJTYPE_SWITCH, OBJTYPE_SWITCHINVIS,OBJTYPE_SWITCHAIR,OBJTYPE_SWITCHBASE,OBJTYPE_SWITCHWATER:
			obj.tObject = Object_Switch_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\switch1#, TempAttribute\switchstatus#, TempAttribute\power#, special#,TempAttribute\switchmode,TempAttribute\switchtimer)
		Case OBJTYPE_SWITCHTOP:
			obj.tObject = Object_SwitchTop_Create(x#, y#, z#, yaw#, TempAttribute\switch1#)
		Case OBJTYPE_ROCK,OBJTYPE_CRYSTAL,OBJTYPE_AUTO,OBJTYPE_ICICLE,OBJTYPE_ICICLEBIG,OBJTYPE_ICEDECOR:
			obj.tObject = Object_Breakable_Create(x#, y#, z#, pitch#, yaw#, roll#, special#)
		Case OBJTYPE_HINT:
			obj.tObject = Object_Hint_Create(x#, y#, z#, TempAttribute\hint1$, TempAttribute\hint2$,TempAttribute\power#,TempAttribute\path$,special,TempAttribute\emittype)
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
		Case OBJTYPE_TRIGGER_VEHICLECANCEL,OBJTYPE_TRIGGER_FOG,OBJTYPE_TRIGGER_VOICE,OBJTYPE_TRIGGER_MACH,OBJTYPE_TRIGGER_MACHCANCEL,OBJTYPE_TRIGGER_SKYDIVE,OBJTYPE_TRIGGER_SKYDIVECANCEL:
			obj.tObject = Object_Trigger_Create(x#, y#, z#, special#,TempAttribute\power#)
		Case OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC,OBJTYPE_TRIGGER_LAPSTART:
			obj.tObject = Object_Trigger_Create(x#, y#, z#, special#, TempAttribute\power#)
		Case OBJTYPE_TRIGGER_DEST:
			obj.tObject = Object_Trigger_Create(x#, y#, z#, TempAttribute\power#, 7)
		Case OBJTYPE_TRIGGER_LAPCHECK
			obj.tObject = Object_Trigger_Create(x#, y#, z#, TempAttribute\teleporterno#, TempAttribute\power#)
		Case OBJTYPE_TRIGGER_SHOP,OBJTYPE_TRIGGER_SOUNDTEST,OBJTYPE_TRIGGER_ATM
			obj.tObject = Object_Trigger_Create(x#, y#, z#, special#,TempAttribute\power#)
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
		Default
			For vs=1 To VISUAL_AMOUNT
				
				If TempAttribute\ObjectNo =OBJTYPE_VISUAL[vs] Then
					obj.tObject = Object_Visual_Create(x#, y#, z#, pitch#, yaw#, roll#, TempAttribute\power#,0, TempAttribute\switch1#, TempAttribute\switch2#, TempAttribute\switch3#, TempAttribute\switchmode)
				EndIf
			Next
	End Select
	
End Function

;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________
;___________________________________________________________________________________________________________________________________________________________________________________________________________


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