
Function SaveGame_Chao(cc.tChaoManager)
	
	CurrentOpenFile=WriteFile(SaveDataPath$+"CHAO"+cc\Number+".xml")
	
	
	
	WriteLine(CurrentOpenFile,"<name is="+Chr$(34)+cc\Name$+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<age is="+Chr$(34)+cc\Stats\Age+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<persona is="+Chr$(34)+cc\Stats\Persona+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<color is="+Chr$(34)+cc\Stats\Color+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<shape is="+Chr$(34)+cc\Stats\Shape+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<side is="+Chr$(34)+cc\Stats\Side+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<hatch timer="+Chr$(34)+cc\HatchTimer+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<shell grit="+Chr$(34)+cc\Stats\ShellGrit#+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<pos x="+Chr$(34)+cc\Position\x#+Chr$(34)+" y="+Chr$(34)+cc\Position\y#+Chr$(34)+" z="+Chr$(34)+cc\Position\z#+Chr$(34)+" dir="+Chr$(34)+cc\g\Motion\Direction#+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<run level="+Chr$(34)+cc\Stats\Run#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentRun#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[1]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<swim level="+Chr$(34)+cc\Stats\Swim#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentSwim#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[2]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<fly level="+Chr$(34)+cc\Stats\Fly#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentFly#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[3]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<strength level="+Chr$(34)+cc\Stats\Strength#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentStrength#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[4]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<stamina level="+Chr$(34)+cc\Stats\Stamina#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentStamina#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[5]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<intelligence level="+Chr$(34)+cc\Stats\Intelligence#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentIntelligence#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[6]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<luck level="+Chr$(34)+cc\Stats\Luck#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentLuck#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[7]+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<hunger need="+Chr$(34)+cc\Stats\Hunger#+Chr$(34)+" antineed="+Chr$(34)+cc\Stats\TooFull#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<sleep need="+Chr$(34)+cc\Stats\Sleep#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<hungry timer="+Chr$(34)+cc\GetHungryTimer+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<sleepy timer="+Chr$(34)+cc\GetSleepyTimer+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<happiness has="+Chr$(34)+cc\Stats\Happiness#+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<revive able="+Chr$(34)+cc\Stats\ReviveAble+Chr$(34)+" eternal="+Chr$(34)+cc\Stats\ReviveEternal+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<love hero="+Chr$(34)+cc\Stats\HeroLove#+Chr$(34)+" dark="+Chr$(34)+cc\Stats\DarkLove#+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<mate season="+Chr$(34)+cc\Stats\MateSeason+Chr$(34)+" timer="+Chr$(34)+cc\MateTimer+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<hat is="+Chr$(34)+cc\Stats\Hat+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<competitions won="+Chr$(34)+cc\Stats\CompetitionsWon+Chr$(34)+" lost="+Chr$(34)+cc\Stats\CompetitionsLost+Chr$(34)+"/>")
	
	CloseFile(CurrentOpenFile)
	
End Function

Function LoadGame_Chao(cc.tChaoManager)
	xmlin = xmlLoad(SaveDataPath$+"CHAO"+cc\Number+".xml")
	
	
	For cchild = 1 To xmlNodeChildCount(xmlin)
		
		child = xmlNodeChild(xmlin, cchild)
		
		Select xmlNodeNameGet$(child)
			Case "name": cc\Name$ = xmlNodeAttributeValueGet(child, "is")
			Case "age": cc\Stats\Age = xmlNodeAttributeValueGet(child, "is")
			Case "persona": cc\Stats\Persona = xmlNodeAttributeValueGet(child, "is")
			Case "color": cc\Stats\Color = xmlNodeAttributeValueGet(child, "is")
			Case "shape": cc\Stats\Shape = xmlNodeAttributeValueGet(child, "is")
			Case "side": cc\Stats\Side = xmlNodeAttributeValueGet(child, "is")
				
			Case "hatch": cc\HatchTimer = xmlNodeAttributeValueGet(child, "timer")
			Case "shell": cc\Stats\ShellGrit# = xmlNodeAttributeValueGet(child, "grit")
				
			Case "pos": cc\InitialPosition\x# = xmlNodeAttributeValueGet(child, "x") : cc\InitialPosition\y# = xmlNodeAttributeValueGet(child, "y") : cc\InitialPosition\z# = xmlNodeAttributeValueGet(child, "z") : cc\InitialDirection# = xmlNodeAttributeValueGet(child, "dir")
				
			Case "run": cc\Stats\Run# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentRun# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[1] = xmlNodeAttributeValueGet(child, "boost")
			Case "swim": cc\Stats\Swim# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentSwim# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[2] = xmlNodeAttributeValueGet(child, "boost")
			Case "fly": cc\Stats\Fly# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentFly# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[3] = xmlNodeAttributeValueGet(child, "boost")
			Case "strength": cc\Stats\Strength# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentStrength# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[4] = xmlNodeAttributeValueGet(child, "boost")
			Case "stamina": cc\Stats\Stamina# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentStamina# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[5] = xmlNodeAttributeValueGet(child, "boost")
			Case "intelligence": cc\Stats\Intelligence# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentIntelligence# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[6] = xmlNodeAttributeValueGet(child, "boost")
			Case "luck": cc\Stats\Luck# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentLuck# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[7] = xmlNodeAttributeValueGet(child, "boost")
				
			Case "hunger": cc\Stats\Hunger# = xmlNodeAttributeValueGet(child, "need") : cc\Stats\TooFull# = xmlNodeAttributeValueGet(child, "antineed")
			Case "sleep": cc\Stats\Sleep# = xmlNodeAttributeValueGet(child, "need")
			Case "hungry": cc\GetHungryTimer = xmlNodeAttributeValueGet(child, "timer")
			Case "sleepy": cc\GetSleepyTimer = xmlNodeAttributeValueGet(child, "timer")
				
			Case "happiness": cc\Stats\Happiness# = xmlNodeAttributeValueGet(child, "has")
				
			Case "revive": cc\Stats\ReviveAble = xmlNodeAttributeValueGet(child, "able") : cc\Stats\ReviveEternal = xmlNodeAttributeValueGet(child, "eternal")
				
			Case "love": cc\Stats\HeroLove# = xmlNodeAttributeValueGet(child, "hero") : cc\Stats\DarkLove# = xmlNodeAttributeValueGet(child, "dark")
				
			Case "mate": cc\Stats\MateSeason = xmlNodeAttributeValueGet(child, "season") : cc\MateTimer = xmlNodeAttributeValueGet(child, "timer")
				
			Case "hat": cc\Stats\Hat = xmlNodeAttributeValueGet(child, "is")
				
			Case "competitions": cc\Stats\CompetitionsWon = xmlNodeAttributeValueGet(child, "won") : cc\Stats\CompetitionsLost = xmlNodeAttributeValueGet(child, "lost")
		End Select
		
	Next
	
	xmlNodeDelete(xmlin)
	
End Function

Function LoadGame_ResetMenuChao()
	
	Menu\HeldChaoNumber=0
	
	Menu\HeldChaoName$ = ""
	Menu\HeldChaoAge = 0
	Menu\HeldChaoPersona = 0
	Menu\HeldChaoColor = 0
	Menu\HeldChaoShape = 0
	Menu\HeldChaoSide = 0
	For i=1 To 7
		Menu\HeldChaoSkills[i] = 0 : Menu\HeldChaoCurrentSkills[i] = 0
	Next
	Menu\HeldChaoEternal = 0
	Menu\HeldChaoHat = 0
	Menu\HeldChaoCompetitionsWon = 0
	Menu\HeldChaoCompetitionsLost = 0
	
End Function

Function LoadGame_MenuChao(number)
	
	LoadFileWithEncryption("CHAO"+number) : xmlin = xmlLoad(SaveDataTmp$)
	
	For cchild = 1 To xmlNodeChildCount(xmlin)
		
		child = xmlNodeChild(xmlin, cchild)
		
		Select xmlNodeNameGet$(child)
			Case "name": Menu\HeldChaoName$ = xmlNodeAttributeValueGet(child, "is")
			Case "age": Menu\HeldChaoAge = xmlNodeAttributeValueGet(child, "is")
			Case "persona": Menu\HeldChaoPersona = xmlNodeAttributeValueGet(child, "is")
			Case "color": Menu\HeldChaoColor = xmlNodeAttributeValueGet(child, "is")
			Case "shape": Menu\HeldChaoShape = xmlNodeAttributeValueGet(child, "is")
			Case "side": Menu\HeldChaoSide = xmlNodeAttributeValueGet(child, "is")
				
			Case "run": Menu\HeldChaoSkills[1] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[1] = xmlNodeAttributeValueGet(child, "current")
			Case "swim": Menu\HeldChaoSkills[2] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[2] = xmlNodeAttributeValueGet(child, "current")
			Case "fly": Menu\HeldChaoSkills[3] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[3] = xmlNodeAttributeValueGet(child, "current")
			Case "strength": Menu\HeldChaoSkills[4] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[4] = xmlNodeAttributeValueGet(child, "current")
			Case "stamina": Menu\HeldChaoSkills[5] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[5] = xmlNodeAttributeValueGet(child, "current")
			Case "intelligence": Menu\HeldChaoSkills[6] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[6] = xmlNodeAttributeValueGet(child, "current")
			Case "luck": Menu\HeldChaoSkills[7] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[7] = xmlNodeAttributeValueGet(child, "current")
				
			Case "revive": Menu\HeldChaoEternal = xmlNodeAttributeValueGet(child, "eternal")
				
			Case "hat": Menu\HeldChaoHat = xmlNodeAttributeValueGet(child, "is")
				
			Case "competitions": Menu\HeldChaoCompetitionsWon = xmlNodeAttributeValueGet(child, "won") : Menu\HeldChaoCompetitionsLost = xmlNodeAttributeValueGet(child, "lost")
		End Select
		
	Next
	
	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()
	
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_ChaoSlots()
	CurrentOpenFile=WriteFile(SaveDataPath$+"CHAOSLOTS.xml")
	
	WriteLine(CurrentOpenFile,"<first timer="+Chr$(34)+CHAOFIRSTTIMER(1)+Chr$(34)+"/>")
	
	For i=1 To CHAOCOUNT
		WriteLine(CurrentOpenFile,"<slot chao="+Chr$(34)+i+Chr$(34)+" is="+Chr$(34)+CHAOSLOTS(1,i)+Chr$(34)+"/>")
	Next
	
	CloseFile(CurrentOpenFile)
	
End Function

Function LoadGame_ChaoSlots()
	
	xmlin = xmlLoad(SaveDataPath$+"CHAOSLOTS.xml")
	
	CHAOSUM(1)=0
	
	For cchild = 1 To xmlNodeChildCount(xmlin)
		
		child = xmlNodeChild(xmlin, cchild)
		
		Select xmlNodeNameGet$(child)
				
			Case "first":
				CHAOFIRSTTIMER(1)=xmlNodeAttributeValueGet(child, "timer")
				
			Case "slot":
				chaono=xmlNodeAttributeValueGet(child, "chao")
				CHAOSLOTS(1,chaono)=xmlNodeAttributeValueGet(child, "is")
				If CHAOSLOTS(1,chaono)=1 Then CHAOSUM(1)=CHAOSUM(1)+1
				
		End Select
		
	Next
	
	xmlNodeDelete(xmlin)
	
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_ChaoGarden()
	
	SaveGame_ChaoSlots()
	CurrentOpenFile=WriteFile(SaveDataPath$+"CHAOGARDEN.xml")
	
	
	WriteLine(CurrentOpenFile,"<chaonametag setting="+Chr$(34)+Menu\Settings\ChaoNameTag#+Chr$(34)+"/>")
	
	GetClosestGardenPoint()
	WriteLine(CurrentOpenFile,"<start x="+Chr$(34)+Game\Stage\Properties\StartX#+Chr$(34)+" y="+Chr$(34)+Game\Stage\Properties\StartY#+Chr$(34)+" z="+Chr$(34)+Game\Stage\Properties\StartZ#+Chr$(34)+" dir="+Chr$(34)+Game\Stage\Properties\StartDirection#+Chr$(34)+"/>")
	
	WriteLine(CurrentOpenFile,"<daytime cycle="+Chr$(34)+Game\Stage\Properties\SkyCycle+Chr$(34)+" timer="+Chr$(34)+Game\Stage\Properties\SkyCycleTimer+Chr$(34)+"/>")
	
	For o.tObject=Each tObject
		Select o\ObjType
				
			Case OBJTYPE_FRUIT:
				If o\ChaoObj\EatCycle>0 Then
					WriteLine(CurrentOpenFile,"<fruit type="+Chr$(34)+o\ChaoObj\FruitType+Chr$(34)+" growth="+Chr$(34)+o\ChaoObj\EatCycle+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")
				EndIf
				
			Case OBJTYPE_SHELL:
				WriteLine(CurrentOpenFile,"<shell type="+Chr$(34)+o\ChaoObj\ShellType+Chr$(34)+" type2="+Chr$(34)+o\ChaoObj\ShellType2+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+" dir="+Chr$(34)+o\Rotation\y#+Chr$(34)+"/>")
				
			Case OBJTYPE_HAT:
				WriteLine(CurrentOpenFile,"<hat type="+Chr$(34)+o\ChaoObj\HatType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")
				
			Case OBJTYPE_TOY:
				WriteLine(CurrentOpenFile,"<toy type="+Chr$(34)+o\ChaoObj\ToyType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")
				
			Case OBJTYPE_TROPICAL:
				If o\ChaoObj\IsFromSeed=False Then WriteLine(CurrentOpenFile,"<tree id="+Chr$(34)+o\ID+Chr$(34)+" growth1="+Chr$(34)+o\ChaoObj\FruitGrowth[1]+Chr$(34)+" growth2="+Chr$(34)+o\ChaoObj\FruitGrowth[2]+Chr$(34)+" growth3="+Chr$(34)+o\ChaoObj\FruitGrowth[3]+Chr$(34)+" growth4="+Chr$(34)+o\ChaoObj\FruitGrowth[4]+Chr$(34)+"/>")
				
			Case OBJTYPE_DRIVE:
				WriteLine(CurrentOpenFile,"<drive type="+Chr$(34)+o\ChaoObj\DriveType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")
				
			Case OBJTYPE_SEED:
				If o\ChaoObj\SeedMode>0 Then
					WriteLine(CurrentOpenFile,"<seed type="+Chr$(34)+o\ChaoObj\FruitType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+" mode="+Chr$(34)+1+Chr$(34)+" growth1="+Chr$(34)+o\ChaoObj\FruitGrowth[1]+Chr$(34)+" growth2="+Chr$(34)+o\ChaoObj\FruitGrowth[2]+Chr$(34)+" growth3="+Chr$(34)+o\ChaoObj\FruitGrowth[3]+Chr$(34)+" growth4="+Chr$(34)+o\ChaoObj\FruitGrowth[4]+Chr$(34)+" treegrowth="+Chr$(34)+o\ChaoObj\TreeGrowth+Chr$(34)+"/>")
				Else
					WriteLine(CurrentOpenFile,"<seed type="+Chr$(34)+o\ChaoObj\FruitType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+" mode="+Chr$(34)+0+Chr$(34)+" growth1="+Chr$(34)+o\ChaoObj\FruitGrowth[1]+Chr$(34)+" growth2="+Chr$(34)+o\ChaoObj\FruitGrowth[2]+Chr$(34)+" growth3="+Chr$(34)+o\ChaoObj\FruitGrowth[3]+Chr$(34)+" growth4="+Chr$(34)+o\ChaoObj\FruitGrowth[4]+Chr$(34)+" treegrowth="+Chr$(34)+o\ChaoObj\TreeGrowth+Chr$(34)+"/>")
				EndIf
				
		End Select
	Next
	
	CloseFile(CurrentOpenFile)
	
	SaveGame_BlackMarket()
	
End Function

Function LoadGame_ChaoGarden()
	
	LoadGame_ChaoSlots()
	
	FRUITSUM(1)=0
	HATSUM(1)=0
	TOYSUM(1)=0
	DRIVESUM(1)=0
	SHELLSUM(1)=0
	SEEDSUM(1)=0
	
	xmlin = xmlLoad(SaveDataPath$+"CHAOGARDEN.xml")
	
	For cchild = 1 To xmlNodeChildCount(xmlin)
		
		child = xmlNodeChild(xmlin, cchild)
		
		Select xmlNodeNameGet$(child)
				
			Case "chaonametag": Menu\Settings\ChaoNameTag# = xmlNodeAttributeValueGet(child, "setting")
				
			Case "start":
				Game\Stage\Properties\StartX#=xmlNodeAttributeValueGet(child, "x")
				Game\Stage\Properties\StartY#=xmlNodeAttributeValueGet(child, "y")
				Game\Stage\Properties\StartZ#=xmlNodeAttributeValueGet(child, "z")
				Game\Stage\Properties\StartDirection#=xmlNodeAttributeValueGet(child, "dir")
				If Game\Stage\Properties\StartDirection#<0 Then Game\Stage\Properties\StartDirection#=Game\Stage\Properties\StartDirection#+360
				Game\Gameplay\CheckX#=Game\Stage\Properties\StartX#
				Game\Gameplay\CheckY#=Game\Stage\Properties\StartY#
				Game\Gameplay\CheckZ#=Game\Stage\Properties\StartZ#
				Game\Gameplay\CheckDirection#=Game\Stage\Properties\StartDirection#
				
			Case "daytime":
				Game\Stage\Properties\SkyCycle=xmlNodeAttributeValueGet(child, "cycle")
				Game\Stage\Properties\SkyCycleTimer=xmlNodeAttributeValueGet(child, "timer")
				Stage_ForceUpdateCyclingSkyBox()
				
			Case "fruit":
				obj.tObject = Object_Fruit_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y")+25, xmlNodeAttributeValueGet(child, "z"), xmlNodeAttributeValueGet(child, "growth"))
				
			Case "shell":
				obj.tObject = Object_Shell_Create(xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y")+25, xmlNodeAttributeValueGet(child, "z"), xmlNodeAttributeValueGet(child, "dir"), xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "type2"))
				
			Case "hat":
				obj.tObject = Object_Hat_Create(xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y")+25, xmlNodeAttributeValueGet(child, "z"), xmlNodeAttributeValueGet(child, "type"))
				
			Case "toy":
				obj.tObject = Object_Toy_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y")+25, xmlNodeAttributeValueGet(child, "z"))
				
			Case "tree":
				For o.tObject = Each tObject
					treeid=xmlNodeAttributeValueGet(child, "id")
					If o\ObjType=OBJTYPE_TROPICAL And o\ID=treeid Then
						o\ChaoObj\FruitGrowth[1]=xmlNodeAttributeValueGet(child, "growth1")
						o\ChaoObj\FruitGrowth[2]=xmlNodeAttributeValueGet(child, "growth2")
						o\ChaoObj\FruitGrowth[3]=xmlNodeAttributeValueGet(child, "growth3")
						o\ChaoObj\FruitGrowth[4]=xmlNodeAttributeValueGet(child, "growth4")
					EndIf
				Next
				
			Case "drive":
				obj.tObject = Object_Drive_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y")+25, xmlNodeAttributeValueGet(child, "z"))
				
			Case "seed":
				obj.tObject = Object_Seed_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y")+25, xmlNodeAttributeValueGet(child, "z"), False, xmlNodeAttributeValueGet(child, "mode"), xmlNodeAttributeValueGet(child, "growth1"), xmlNodeAttributeValueGet(child, "growth2"), xmlNodeAttributeValueGet(child, "growth3"), xmlNodeAttributeValueGet(child, "growth4"), xmlNodeAttributeValueGet(child, "treegrowth"))
				
		End Select
		
	Next
	
	xmlNodeDelete(xmlin)
	
	If FileType(SaveDataPath$+"BLACKMARKET"+SaveDataFormat$)=1 Then LoadGame_BlackMarket()
	
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_AllChaoStuff()
	For cc.tChaoManager=Each tChaoManager
		If FileType(SaveDataPath$+"CHAO"+cc\Number+SaveDataFormat$)=1 Then SaveGame_Chao(cc)
	Next
	SaveGame_ChaoGarden()
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D