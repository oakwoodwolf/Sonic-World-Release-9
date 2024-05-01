
	Dim CHAOCHECKER_SKILL(7)
	Dim CHAOCHECKER_SIDE(0)

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tChaoManager
		Field obj.tObject
		Field Number
		Field Name$
		Field Pivot
		Field Mesh
		Field Mesh_horn1
		Field Mesh_horn2
		Field HandL
		Field HandR
		Field CocoonMesh
		Field HatMesh
		Field Action
		Field Animation
		Field PreviousAnimation
		Field Stats.tChaoManager_Stats
		Field Emo.tChaoEmo
		Field g.tGravity
		Field Rotation#
		Field Underwater
		Field WanderDirection
		Field WanderDirectionSpeed
		Field FoundTarget
		Field Target.tObject
		Field Targetp.tPlayer
		Field EatingWaiting
		Field ForceInterface
		Field Position.tVector
		Field InitialPosition.tVector
		Field InitialDirection#
		Field StatChange
		Field BodyTexture
		Field OfficeTexture
		Field BodyGlareTexture
		Field OfficeGlareTexture
		Field CanBeInterrupted
		Field HatCoversHorn
		Field VoiceEmo
		Field PreviousVoiceEmo
		Field Channel_Voice
		Field Channel_Swim
		Field Channel_Think
		Field VoiceTimer
		Field PlayOrder
		Field LetGoTarget
		Field LuckSpeed#
		Field WinningChao
		Field breeder.tObject
		Field Particle.tParticleTemplate
		Field ShadowCircle

		;timers
		Field JustGotHappyTimer
		Field JustGotSadTimer
		Field HurtTimer
		Field DanceTimer
		Field WanderTimer
		Field WillWanderTimer
		Field WanderDirectionTimer
		Field FlyTimer
		Field WillFlyTimer
		Field EatTimer
		Field GetHungryTimer
		Field GetSleepyTimer
		Field SleepTimer
		Field BornTimer
		Field HatchTimer
		Field HungerIgnoredTimer
		Field ThinkingTimer
		Field WearTimer
		Field MateTimer
		Field WillWaitBreedTimer
		Field WaitBreedTimer
		Field BreedTimer
		Field PlayTimer
		Field PlayedEnoughTimer
		Field ShallFollowWhistleTimer
		Field ShallBePettedTimer
		Field EggRumbleTimer
		Field CheeredTimer
		Field RandomBoostTimer
		Field RandomBoostedTimer
		Field MeditateTimer
		Field FollowWhistleTimer
		Field WhistleLoveTimer
		Field WhistleThinkTimer
		Field RandomWhistleTimer
	End Type

	Type tChaoManager_Stats
		Field Body#
		Field Age
		Field Persona
		Field Color
		Field Shape
		Field Side
		Field Run#
		Field Swim#
		Field Fly#
		Field Strength#
		Field Stamina#
		Field Intelligence#
		Field Luck#
		Field CurrentRun#
		Field CurrentSwim#
		Field CurrentFly#
		Field CurrentStrength#
		Field CurrentStamina#
		Field CurrentIntelligence#
		Field CurrentLuck#
		Field Skills[7]
		Field CurrentSkills[7]
		Field BoostSkills[7]
		Field Hunger#
		Field TooFull#
		Field Sleep#
		Field Happiness#
		Field ShellGrit#
		Field ReviveAble
		Field ReviveEternal
		Field Monotone
		Field Shiny
		Field Bright
		Field Textured
		Field HeroLove#
		Field DarkLove#
		Field Love[2]
		Field CharacterLove[CHAR_NONMODPLAYABLECOUNT]
		Field FavouriteFood
		Field MateSeason
		Field Hat
		Field CompetitionsWon
		Field CompetitionsLost
	End Type

	Const CHAOANIMATION_IDLE		= 1
	Const CHAOANIMATION_SIT			= 2
	Const CHAOANIMATION_WALK		= 3
	Const CHAOANIMATION_RUN			= 4
	Const CHAOANIMATION_IDLEAIR		= 5
	Const CHAOANIMATION_WALKAIR		= 6
	Const CHAOANIMATION_RUNAIR		= 7
	Const CHAOANIMATION_CRAWL		= 8
	Const CHAOANIMATION_TRIP		= 9
	Const CHAOANIMATION_SWIM		= 10
	Const CHAOANIMATION_DROWN		= 11
	Const CHAOANIMATION_CLIMB		= 12
	Const CHAOANIMATION_EXCLAMATION		= 13
	Const CHAOANIMATION_QUESTIONING		= 14
	Const CHAOANIMATION_EXCLAMATIONAIR	= 15
	Const CHAOANIMATION_QUESTIONINGAIR	= 16
	Const CHAOANIMATION_DANCE		= 17
	Const CHAOANIMATION_EAT			= 18
	Const CHAOANIMATION_WAIT		= 19
	Const CHAOANIMATION_LAY			= 20
	Const CHAOANIMATION_THINK		= 21
	Const CHAOANIMATION_LAUGH		= 22
	Const CHAOANIMATION_INTIMIDATE		= 23
	Const CHAOANIMATION_HURT		= 24
	Const CHAOANIMATION_THROWN		= 25
	Const CHAOANIMATION_HUG			= 26
	Const CHAOANIMATION_EMBRACE		= 27
	Const CHAOANIMATION_SORRY		= 28
	Const CHAOANIMATION_SHY			= 29
	Const CHAOANIMATION_WIN			= 30
	Const CHAOANIMATION_KICKR		= 31
	Const CHAOANIMATION_KICKL		= 32
	Const CHAOANIMATION_PUNCHR		= 33
	Const CHAOANIMATION_PUNCHL		= 34


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_ChaoManager_NewChao(cc.tChaoManager, Color=0, makepos=True,mono=0,shiny=0,tex=0)
		cc\Stats\Age=0
		cc\Stats\Persona=1
		If makepos Then Object_ChaoManager_RandomBeginPos(cc, True)
		cc\Stats\Hunger#=5 : cc\GetHungryTimer=80*secs#
		cc\HatchTimer=Rand(30,60)*secs#
		cc\Stats\ShellGrit#=4+Rand(0,10)
		cc\Stats\Side=CHAOSIDE_NEUTRAL
		cc\Stats\Shape=1
		If Color=0 Then ; undefined
			cc\Stats\Color=1
		Else ;defined
			cc\Stats\Color=Color Mod CHAOCOLORS_total
			If cc\Stats\Color=0 Then cc\Stats\Color=CHAOCOLORS_total
		EndIf
		If mono=1 Or (Color>(CHAOCOLORS_total*1) And Color<=(CHAOCOLORS_total*2)) Or (Color>(CHAOCOLORS_total*3) And Color<=(CHAOCOLORS_total*4)) Then
			cc\Stats\Monotone=1
		EndIf
		If shiny=1 Or (Color>(CHAOCOLORS_total*2) And Color<=(CHAOCOLORS_total*4)) Then
			cc\Stats\Shiny=1
		EndIf
		If tex=1 Or Color>(CHAOCOLORS_total*4) Then
			cc\Stats\Textured=1
		EndIf
		cc\Stats\FavouriteFood=Rand(FRUIT_CUBICLE,FRUIT_RADISH)
	End Function

	Function Object_ChaoManager_RandomBeginPos(cc.tChaoManager, frominventory=False)
		temporarychaolocation=CreatePivot()
		If frominventory Then
			PositionEntity temporarychaolocation,pp(1)\Objects\Position\x#,pp(1)\Objects\Position\y#,pp(1)\Objects\Position\z#,1
		Else
			GetClosestGardenPoint()
			PositionEntity temporarychaolocation,Game\Stage\Properties\StartX#,Game\Stage\Properties\StartY#,Game\Stage\Properties\StartZ#,1
		EndIf
		EntityType(temporarychaolocation,COLLISION_OBJECT_GOTHRU)
		MoveEntity temporarychaolocation, Rand(-10,10), 5, Rand(-10,10)
		cc\Position\x#=EntityX(temporarychaolocation)
		cc\Position\z#=EntityZ(temporarychaolocation)
		cc\Position\y#=EntityY(temporarychaolocation)
		FreeEntity temporarychaolocation
		cc\g\Motion\Direction#=Rand(-180,180)
	End Function

	Function Object_ChaoManager_SpawnNewChao(color=0,takepos=False,x#=0,y#=0,z#=0,mono%=0,shiny%=0,jewel%=0)
		CHAOSUM(1)=CHAOSUM(1)+1 : CHAOSLOTS(1,CHAOSUM(1))=1
		Object_CreateChao(CHAOSUM(1),Color,takepos,x#,y#,z#,mono,shiny,jewel)
		SaveGame_AllChaoStuff()
	End Function

	Function Object_ChaoManager_HornInHat(hattype)
		Select hattype
			Case HAT_KNIGHT_0,HAT_KNIGHT_1,HAT_KNIGHT_2,HAT_PUMPKIN_0,HAT_SONICMAN_0,HAT_SONICMAN_1,HAT_STRAW_0,HAT_WOOL_0,HAT_WOOL_1,HAT_WOOL_2,HAT_WOOL_3,HAT_FORMAL_0,HAT_FORMAL_1,HAT_SKULL_0,HAT_BUCKET_0,HAT_TUNIC_0,HAT_TUNIC_1,HAT_TUNIC_2,HAT_TUNIC_3,HAT_BEANIE_0,HAT_BEANIE_1,HAT_BEANIE_2,HAT_BEANIE_3,HAT_FPOT_0,HAT_CARDBOARD_0,HAT_SHELL_0,HAT_PLUMBER_0,HAT_PLUMBER_1,HAT_PLUMBER_2,HAT_PLUMBER_3,HAT_PLUMBER_4,HAT_BANDANA_0,HAT_BANDANA_1,HAT_BANDANA_2,HAT_BANDANA_3,HAT_BANDANA_4, HAT_TURBAN_0, HAT_HOOD_0, HAT_HOOD_1, HAT_GOHAN_0,HAT_GOHAN_1,HAT_GOHAN_2,HAT_GOHAN_3,HAT_GOHAN_4,HAT_GOHAN_5,HAT_GOHAN_6:
				Return True
			Default:
				Return False
		End Select
	End Function

	Function Object_ChaoManager_LoadHat(cc.tChaoManager)
		cc\HatMesh = LoadAnimMesh("ChaoWorld\Hats\"+HATS_FILE$(cc\Stats\Hat)+".b3d", FindChild(cc\Mesh, "head"))
		MoveEntity cc\HatMesh, 0, 1.4, 0
		cc\HatCoversHorn=Object_ChaoManager_HornInHat(cc\Stats\Hat)
		HATSUM(1) = HATSUM(1) + 1
	End Function

	Function Object_ChaoManager_TakeOffHat(cc.tChaoManager)
		FreeEntity cc\HatMesh
		obj.tObject = Object_Hat_Create(EntityX(cc\Pivot), EntityY(cc\Pivot), EntityZ(cc\Pivot), cc\Stats\Hat, True)
		cc\Stats\Hat=0
		cc\HatCoversHorn=Object_ChaoManager_HornInHat(cc\Stats\Hat)
		HATSUM(1) = HATSUM(1) - 1
		ChaoManager_RemoveBoostedSkills(cc)
	End Function
	
	Function Object_ChaoManager_Create.tChaoManager(number, o.tObject, born=False, color=0, takepos=False, x#=0, y#=0, z#=0, forcechao=False, forceage=0, forcepersona=0, forcecolor=0, forceshape=0, forceside=0, forcehat=0, forcemono=0, forceshiny=0, forcetex=0, forcebright=0, forcebody=0)
	
		cc.tChaoManager = New tChaoManager
		cc\Number=number
		cc\Stats = New tChaoManager_Stats
		cc\g = Object_Gravity_Create.tGravity()
		cc\Position = New tVector
		cc\InitialPosition = New tVector

		If forcechao=False Then
			If Not(FileType(SaveDataPath$+"Chao"+cc\Number+".xml")=1) Then
				Object_ChaoManager_NewChao(cc,Color,True,forcemono,forceshiny,forcetex)
				If takepos Then cc\Position\x#=x# : cc\Position\y#=y# : cc\Position\z#=z#
				SaveGame_Chao(cc)
			EndIf
			LoadGame_Chao(cc)
		Else
			cc\Stats\Age=forceage
			cc\Stats\Persona=forcepersona
			cc\Stats\Color=forcecolor
			cc\Stats\Monotone=forcemono
			cc\Stats\Shiny=forceshiny
			cc\Stats\Bright=forcebright
			cc\Stats\Textured=forcetex
			cc\Stats\Shape=forceshape
			cc\Stats\Side=forceside
			cc\Stats\Hat=forcehat
			If takepos Then cc\InitialPosition\x#=x# : cc\InitialPosition\y#=y# : cc\InitialPosition\z#=z#
		EndIf
		If cc\Stats\FavouriteFood<2 Then cc\Stats\FavouriteFood = Rand(FRUIT_CUBICLE, FRUIT_RADISH)
		cc\obj=o
		o\ChaoObj\targetcc=cc
		cc\Stats\Body = forcebody
		cc\Pivot = CreatePivot()
		PositionEntity(cc\Pivot, cc\InitialPosition\x#, cc\InitialPosition\y#, cc\InitialPosition\z#)
		RotateEntity cc\Pivot, 0, cc\InitialDirection#, 0
		If forcebody=0 Then
			Select cc\Stats\Age
				Case 0:
					cc\Mesh = LoadAnimMesh("ChaoWorld\Eggs\egg.b3d", cc\Pivot)
					ExtractAnimSeq(cc\Mesh,1,9)
					If cc\Stats\Textured>0 Then
						cc\BodyTexture=LoadTexture("ChaoWorld\Chao\Tex\tex_"+CHAOJEWEL$(cc\Stats\Color)+".png",1+64+256)
					ElseIf cc\Stats\Monotone>0 Then
						cc\BodyTexture=LoadTexture("ChaoWorld\Chao\"+CHAOCOLORS$(cc\Stats\Color)+"\"+CHAOCOLORS$(cc\Stats\Color)+".png",256)
					Else
						cc\BodyTexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(cc\Stats\Color)+".png",256)
					EndIf
					EntityTexture cc\Mesh, cc\BodyTexture
					FreeTexture cc\BodyTexture
				Default:
					cc\Mesh = LoadAnimMesh("ChaoWorld\Chao\"+CHAOSIDES$(cc\Stats\Side)+"."+CHAOSHAPES$(cc\Stats\Shape)+".b3d", cc\Pivot)
					If cc\Stats\Textured>0 Then
						cc\BodyTexture=LoadTexture("ChaoWorld\Chao\Tex\tex_"+CHAOJEWEL$(cc\Stats\Color)+".png",1+64+256)
					ElseIf cc\Stats\Monotone>0 Then
						cc\BodyTexture=LoadTexture("ChaoWorld\Chao\"+CHAOCOLORS$(cc\Stats\Color)+"\"+CHAOCOLORS$(cc\Stats\Color)+".png",256)
					Else
						cc\BodyTexture=LoadTexture("ChaoWorld\Chao\"+CHAOCOLORS$(cc\Stats\Color)+"\"+CHAOSIDES$(cc\Stats\Side)+".body."+CHAOCOLORS$(cc\Stats\Color)+"."+CHAOSHAPES$(cc\Stats\Shape)+".png",256)
					EndIf
					cc\OfficeTexture=LoadTexture("ChaoWorld\Chao\Office\"+CHAOSIDES$(cc\Stats\Side)+".office."+CHAOSHAPES$(cc\Stats\Shape)+".png",256)
					If cc\Stats\Shiny>0 Then cc\BodyGlareTexture=LoadTexture("ChaoWorld\Chao\0.blackglare2.Shiny.png",1+64) Else cc\BodyGlareTexture=LoadTexture("ChaoWorld\Chao\0.blackglare2.png",1+64)
					TextureBlend(cc\BodyGlareTexture,3)
					cc\OfficeGlareTexture=LoadTexture("ChaoWorld\Chao\0.chaoref.png",1+64) : TextureBlend(cc\OfficeGlareTexture,3)

					ApplyMeshTextureLayer(cc\Mesh, CHAOSIDES$(cc\Stats\Side)+".body.celeste.png", cc\BodyTexture)
					If cc\Stats\Bright=1 Then
						TextureBlend(cc\BodyTexture,3)
						ApplyMeshTextureLayer(cc\Mesh, CHAOSIDES$(cc\Stats\Side)+".body.celeste.png", cc\BodyTexture,true)
					EndIf
					ApplyMeshTextureLayer(cc\Mesh, CHAOSIDES$(cc\Stats\Side)+".office.normal.png", cc\OfficeTexture)
					ApplyMeshTextureLayer(cc\Mesh, CHAOSIDES$(cc\Stats\Side)+".body."+CHAOCOLORS$(cc\Stats\Color)+"."+CHAOSHAPES$(cc\Stats\Shape)+".png", cc\BodyGlareTexture, True)
					ApplyMeshTextureLayer(cc\Mesh, CHAOSIDES$(cc\Stats\Side)+".office."+CHAOSHAPES$(cc\Stats\Shape)+".png", cc\OfficeGlareTexture, True)
					FreeTexture cc\BodyTexture
					FreeTexture cc\OfficeTexture
					FreeTexture cc\BodyGlareTexture
					FreeTexture cc\OfficeGlareTexture

					cc\Mesh_horn1 = FindChild(cc\Mesh, "horn1")
					cc\Mesh_horn2 = FindChild(cc\Mesh, "horn2")
					cc\HandL = FindChild(cc\Mesh, "handL")
					cc\HandR = FindChild(cc\Mesh, "handR")
			End Select
			
			If ChaoManager_ChaoAlive(cc) Or ChaoManager_ChaoCocoonAlive(cc) Then ExtractAllCharacterAnimations_PetChao(cc\Mesh)

			Select cc\Stats\Age
				Case 2:
					cc\CocoonMesh = LoadAnimMesh("ChaoWorld\Cocoons\Cocoon.b3d", cc\Pivot)
				Case 4:
					Select cc\Stats\ReviveAble
					Case 0: cc\CocoonMesh = LoadAnimMesh("ChaoWorld\Cocoons\Cocoon3.b3d", cc\Pivot)
					Case 1: cc\CocoonMesh = LoadAnimMesh("ChaoWorld\Cocoons\Cocoon2.b3d", cc\Pivot)
					End Select
			End Select
			
			If ChaoManager_ChaoAlive(cc) Or ChaoManager_ChaoCocoonAlive(cc) Then cc\Emo = Object_ChaoEmo_Create.tChaoEmo(cc\Mesh, cc\Stats\Side, False, cc\Stats\ReviveEternal)


			If born Then ChaoManager_GetBorn(cc)
		ElseIf forcebody=2 Then
				wispname$="Purple"
				Select cc\Stats\Color
					Case CHAOCOLOR_AZURE
						wispname = "Cyan"
					Case CHAOCOLOR_WHITE
						wispname = "White"
					Case CHAOCOLOR_BLUE
						wispname = "Blue"
					Case CHAOCOLOR_BLACK
						wispname = "Black"
					Case CHAOCOLOR_GREY
						wispname = "Ivory"
					Case CHAOCOLOR_BROWN
						wispname = "Crimson"
					Case CHAOCOLOR_RED
						wispname = "Red"
					Case CHAOCOLOR_ORANGE
						wispname = "Orange"
					Case CHAOCOLOR_YELLOW
						wispname = "Yellow"
					Case CHAOCOLOR_GREEN
						wispname = "Gray"
					Case CHAOCOLOR_LIME
						wispname = "Green"
					Case CHAOCOLOR_PURPLE
						wispname = "Indigo"
					Case CHAOCOLOR_PINK
						wispname = "Pink"
					Case CHAOCOLOR_TRANS
						wispname = "Magenta"
					Case CHAOCOLOR_CELESTE
						wispname = "Violet"
				End Select
				cc\Mesh = LoadAnimMesh("Objects\Wisps\Wisp"+wispname+".b3d", cc\Pivot)
				ScaleEntity cc\Mesh,3.0,3.0,3.0
				PositionEntity cc\Mesh, 0.0,0.5,0.0
				cc\Mesh_horn2 = FindChild(cc\Mesh, "horn2")
		ElseIf forcebody=3 Then
				flickyname=0
				Select cc\Stats\Color
					Case CHAOCOLOR_PURPLE, CHAOCOLOR_PINK, CHAOCOLOR_BROWN:
						flickyname = 2
					Case CHAOCOLOR_RED, CHAOCOLOR_ORANGE, CHAOCOLOR_YELLOW:
						flickyname = 3
					Case CHAOCOLOR_GREEN, CHAOCOLOR_LIME, CHAOCOLOR_WHITE:
						flickyname = 4
					Default:
						flickyname = 1
				End Select
				cc\Mesh = LoadAnimMesh("Objects\Flickies\Flicky"+flickyname+".b3d", cc\Pivot)
				ScaleEntity cc\Mesh,0.8,0.8,0.8
				PositionEntity cc\Mesh, 0.0,0.5,0.0
				ExtractAnimSeq(cc\Mesh,1,9)
				cc\Mesh_horn1 = FindChild(cc\Mesh, "Hair01")
				cc\Mesh_horn2 = FindChild(cc\Mesh, "Hair01")
		Else
			cc\Mesh = LoadAnimMesh("ChaoWorld\Chao\Special\"+forcebody+".b3d", cc\Pivot)
			ExtractAllCharacterAnimations_PetChao(cc\Mesh)
			cc\Mesh_horn1 = FindChild(cc\Mesh, "horn1")
			cc\Mesh_horn2 = FindChild(cc\Mesh, "horn2")
		EndIf
		If cc\Stats\Hat>0 And forcebody=4 Then cc\Stats\Hat=0
		If cc\Stats\Hat>0 Then Object_ChaoManager_LoadHat(cc)
		If cc\Stats\Hat>0 And forcebody=2 Then ScaleEntity cc\HatMesh,0.33,0.33,0.33 : MoveEntity cc\HatMesh,0.0,-1.2,0.0
		EntityType(cc\Pivot,COLLISION_OBJECT2)
		If Menu\Settings\Shadows#>0 Then cc\ShadowCircle = Init_CircleShadow(cc\Pivot, cc\Mesh, 1.5)
		
	

		cc\VoiceTimer=(4.5575+Rand(-0.5,0.5))*secs#

		cc\RandomWhistleTimer=(Rand(1,2))*secs#

		cc\Particle = ParticleTemplate_Create.tParticleTemplate()

		Return cc
		
	End Function
	
	; =========================================================================================================

	Function Object_ChaoManager_Update(cc.tChaoManager, d.tDeltaTime)

		; Position chao object
		If cc\obj\ObjPickedUp=0 Then
			PositionEntity cc\obj\Entity, EntityX(cc\Pivot), EntityY(cc\Pivot), EntityZ(cc\Pivot)
		Else
			cc\g\Motion\Direction#=EntityYaw(cc\obj\Entity)
		EndIf

		; Update position
		cc\Position\x# = EntityX(cc\Pivot,1) : cc\Position\y# = EntityY(cc\Pivot,1) : cc\Position\z# = EntityZ(cc\Pivot,1)

		; Restrict stats
		If cc\Stats\Run#<0 Then cc\Stats\Run#=0
		If cc\Stats\Swim#<0 Then cc\Stats\Swim#=0
		If cc\Stats\Fly#<0 Then cc\Stats\Fly#=0
		If cc\Stats\Strength#<0 Then cc\Stats\Strength#=0
		If cc\Stats\Stamina#<0 Then cc\Stats\Stamina#=0
		If cc\Stats\Intelligence#<0 Then cc\Stats\Intelligence#=0
		If cc\Stats\Luck#<0 Then cc\Stats\Luck#=0
		If cc\Stats\Run#>50 Then cc\Stats\Run#=50
		If cc\Stats\Swim#>50 Then cc\Stats\Swim#=50
		If cc\Stats\Fly#>50 Then cc\Stats\Fly#=50
		If cc\Stats\Strength#>50 Then cc\Stats\Strength#=50
		If cc\Stats\Stamina#>50 Then cc\Stats\Stamina#=50
		If cc\Stats\Intelligence#>50 Then cc\Stats\Intelligence#=50
		If cc\Stats\Luck#>50 Then cc\Stats\Luck#=50
		If cc\Stats\CurrentRun#<0 Then cc\Stats\CurrentRun#=0
		If cc\Stats\CurrentSwim#<0 Then cc\Stats\CurrentSwim#=0
		If cc\Stats\CurrentFly#<0 Then cc\Stats\CurrentFly#=0
		If cc\Stats\CurrentStrength#<0 Then cc\Stats\CurrentStrength#=0
		If cc\Stats\CurrentStamina#<0 Then cc\Stats\CurrentStamina#=0
		If cc\Stats\CurrentIntelligence#<0 Then cc\Stats\CurrentIntelligence#=0
		If cc\Stats\CurrentLuck#<0 Then cc\Stats\CurrentLuck#=0
		If cc\Stats\CurrentRun#>10 Then cc\Stats\CurrentRun#=10
		If cc\Stats\CurrentSwim#>10 Then cc\Stats\CurrentSwim#=10
		If cc\Stats\CurrentFly#>10 Then cc\Stats\CurrentFly#=10
		If cc\Stats\CurrentStrength#>10 Then cc\Stats\CurrentStrength#=10
		If cc\Stats\CurrentStamina#>10 Then cc\Stats\CurrentStamina#=10
		If cc\Stats\CurrentIntelligence#>10 Then cc\Stats\CurrentIntelligence#=10
		If cc\Stats\CurrentLuck#>10 Then cc\Stats\CurrentLuck#=10
		If cc\Stats\Hunger#<0 Then cc\Stats\Hunger#=0
		If cc\Stats\TooFull#<0 Then cc\Stats\TooFull#=0
		If cc\Stats\Sleep#<0 Then cc\Stats\Sleep#=0
		If cc\Stats\Hunger#>10 Then cc\Stats\Hunger#=10
		If cc\Stats\TooFull#>20 Then cc\Stats\TooFull#=20
		If cc\Stats\Sleep#>10 Then cc\Stats\Sleep#=10
		If cc\Stats\Happiness#<-100 Then cc\Stats\Happiness#=-100
		If cc\Stats\Happiness#>100 Then cc\Stats\Happiness#=100
		If cc\Stats\ShellGrit#<0 Then cc\Stats\ShellGrit#=0
		If cc\Stats\HeroLove#<0 Then cc\Stats\HeroLove#=0
		If cc\Stats\DarkLove#<0 Then cc\Stats\DarkLove#=0
		If cc\Stats\HeroLove#>100 Then cc\Stats\HeroLove#=100
		If cc\Stats\DarkLove#>100 Then cc\Stats\DarkLove#=100
		For i=1 To CHAR_NONMODPLAYABLECOUNT
			If cc\Stats\CharacterLove[i]<-100 Then cc\Stats\CharacterLove[i]=-100
			If cc\Stats\CharacterLove[i]>100 Then cc\Stats\CharacterLove[i]=100
		Next
		; Reference stats
		For i=1 To 7
		Select i
			Case 1:
			cc\Stats\Skills[i]=cc\Stats\Run
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentRun
			Case 2:
			cc\Stats\Skills[i]=cc\Stats\Swim
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentSwim
			Case 3:
			cc\Stats\Skills[i]=cc\Stats\Fly
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentFly
			Case 4:
			cc\Stats\Skills[i]=cc\Stats\Strength
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentStrength
			Case 5:
			cc\Stats\Skills[i]=cc\Stats\Stamina
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentStamina
			Case 6:
			cc\Stats\Skills[i]=cc\Stats\Intelligence
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentIntelligence
			Case 7:
			cc\Stats\Skills[i]=cc\Stats\Luck
			cc\Stats\CurrentSkills[i]=cc\Stats\CurrentLuck
		End Select
		Next
		For i=1 To 2
		Select i
			Case 1:
			cc\Stats\Love[i]=cc\Stats\HeroLove
			Case 2:
			cc\Stats\Love[i]=cc\Stats\DarkLove
		End Select
		Next

		; Check if player is underwater
		If EntityY(cc\Pivot) < Game\Stage\Properties\WaterLevel Then cc\Underwater=1 Else cc\Underwater=0

		; Physics
		If cc\Underwater=0 Then
			cc\g\Physics\UNDERWATERTRIGGER# = 1
			cc\g\Physics\UNDERWATERTRIGGERW# = 1
		ElseIf cc\Underwater=1
			cc\g\Physics\UNDERWATERTRIGGER# = 0.5
			cc\g\Physics\UNDERWATERTRIGGERW# = 0.2
		EndIf
		cc\g\Physics\COMMON_YACCELERATION# = 0.06125*(cc\g\Physics\UNDERWATERTRIGGERW#)
		cc\g\Physics\COMMON_YTOPSPEED# = -1.055*(cc\g\Physics\UNDERWATERTRIGGERW#)

		; Run motion
		Gravity_Motion(cc\Pivot,cc\Mesh,cc\g,d,cc\obj\ObjPickedUp,cc\Action)

		; Run timers
		ChaoManager_Timers(cc)

		; Animate
		If ChaoManager_ChaoAlive(cc) Or ChaoManager_ChaoCocoonAlive(cc) Then
			If Not(ChaoManager_UsesUniqueAnims(cc)) Then
				If cc\PreviousAnimation<>cc\Animation Then
					Select cc\Animation
						Case CHAOANIMATION_RUN,CHAOANIMATION_RUNAIR,CHAOANIMATION_INTIMIDATE,CHAOANIMATION_THROWN:
							Animate (cc\Mesh,1,0.3188,cc\Animation,10)
						Case CHAOANIMATION_EXCLAMATION,CHAOANIMATION_QUESTIONING,CHAOANIMATION_EXCLAMATIONAIR,CHAOANIMATION_QUESTIONINGAIR,CHAOANIMATION_DANCE:
							Animate (cc\Mesh,1,0.1275,cc\Animation,10)
						Case CHAOANIMATION_KICKR,CHAOANIMATION_KICKL,CHAOANIMATION_PUNCHR,CHAOANIMATION_PUNCHL:
							Animate (cc\Mesh,3,0.455,cc\Animation,10)
						Default:
							Animate (cc\Mesh,1,0.255,cc\Animation,10)
					End Select
					cc\PreviousAnimation=cc\Animation
				EndIf
			Else
				If cc\PreviousAnimation<>cc\Animation Then
					Animate (cc\Mesh,1,0.255,0,0)
					cc\PreviousAnimation=cc\Animation
				EndIf
			EndIf
		EndIf

		; Look for target
		If ChaoManager_ChaoAlive(cc) And (Not(cc\Action=CHAOACTION_SLEEP)) Then
		Select cc\FoundTarget
			Case False:
				chaotaketarget=False
				Select cc\Action
					Case CHAOACTION_COMMON,CHAOACTION_DANCE,CHAOACTION_THINK:
						chaotaketarget=True
					Case CHAOACTION_SWIM:
						If pp(1)\ObjPickUp>0 Then
							If pp(1)\ObjPickUpTarget\ObjType=OBJTYPE_TOY Then
								If pp(1)\ObjPickUpTarget\ChaoObj\ToyType=TOY_RUBBERDUCK Then chaotaketarget=True
							EndIf
						EndIf
				End Select
				If chaotaketarget Then
					For o.tObject=Each tObject
						Select o\ObjType
						Case OBJTYPE_FRUIT,OBJTYPE_DRIVE,OBJTYPE_HAT,OBJTYPE_TOY:
							If o\ChaoObj\ChaoTargetedThis=False And (o\ObjPickedUp=-2 Or (o\ObjPickedUp=0 And (Not(o\ObjType=OBJTYPE_HAT))) Or o\ObjPickedUp=1) And (Abs(EntityX(cc\Pivot) - o\Position\x#) < 2) And (Abs(EntityY(cc\Pivot) - o\Position\y#) < 6.5) And (Abs(EntityZ(cc\Pivot) - o\Position\z#) < 2) Then
								cc\Target=o
								cc\FoundTarget=True
								If o\ObjPickedUp=1 Then o\ObjPickedUp=-2 : pp(1)\ObjPickUp=0
								o\ChaoObj\ChaoTargetedThis=True
								o\ChaoObj\targetcc=cc
							EndIf
						Case OBJTYPE_BREEDER:
							If o\ChaoObj\ChaoTargetedThis=False And cc\obj\ObjPickedUp=0 And cc\Stats\MateSeason=1 And (Not(cc\Action=CHAOACTION_WAITBREED Or cc\Action=CHAOACTION_BREED)) And (Abs(EntityX(cc\Pivot) - o\Position\x#) < o\HitBox\x#) And (Abs(EntityY(cc\Pivot) - o\Position\y#) < o\HitBox\y#) And (Abs(EntityZ(cc\Pivot) - o\Position\z#) < o\HitBox\z#) Then
								o\ChaoObj\ChaoTargetedThis=True
								o\ChaoObj\targetcc2=cc
								o\ChaoObj\targetcc\Action=CHAOACTION_BREED : o\ChaoObj\targetcc\BreedTimer=(6+Rand(-2,2))*secs#
								o\ChaoObj\targetcc2\Action=CHAOACTION_BREED : o\ChaoObj\targetcc2\BreedTimer=o\ChaoObj\targetcc\BreedTimer
								Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoMating)
							EndIf
						End Select
					Next
				EndIf
			Case True:
				maybefar=False
				If cc\Target\ObjType=OBJTYPE_TOY And cc\Action=CHAOACTION_BALL Then maybefar=True
				If cc\obj\ObjPickedUp=1 Or cc\LetGoTarget Or ( maybefar=False And (Not(Abs(EntityX(cc\Pivot) - cc\Target\Position\x#) < 4) And (Abs(EntityY(cc\Pivot) - cc\Target\Position\y#) < 4) And (Abs(EntityZ(cc\Pivot) - cc\Target\Position\z#) < 4)) ) Then
					cc\FoundTarget=False
					cc\Target\ChaoObj\ChaoTargetedThis=False
					If cc\LetGoTarget=False Then cc\Target\ObjPickedUp=0 Else cc\Target\ObjPickedUp=6 : cc\LetGoTarget=False
					If cc\Target\ObjType=OBJTYPE_TOY Then
						If cc\Target\ChaoObj\ToyType=TOY_PILLOW Then MoveEntity cc\Target\Pivot, 0, 2, 0
					EndIf
				EndIf
		End Select
		EndIf

		; Run actions
		ChaoManager_ManageActions(cc,d)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_AgeUp(cc.tChaoManager, born=False)

		die=False

		cc\Stats\Age=cc\Stats\Age+1

		Select cc\Stats\Age
			Case 1: cc\HatchTimer=(3000+Rand(-600,600))*secs#
				Interface_CreateChaoMsg("A chao was born.",51,204,255)
			Case 2: cc\HatchTimer=(120+Rand(-10,10))*secs#
				Interface_CreateChaoNamedMsg("has entered a cocoon.",cc\Name$,23,240,0)
			Case 3: cc\HatchTimer=(10800+Rand(-1200,1200))*secs#
				cc\Stats\MateSeason=1
				ChaoManager_Evolve(cc)
				Interface_CreateChaoNamedMsg("has aged up.",cc\Name$,48,145,245)
			Case 4: cc\HatchTimer=(240+Rand(-20,20))*secs#
				If cc\Stats\Happiness#>30 Then cc\Stats\ReviveAble=1
				Interface_CreateChaoNamedMsg("has entered a mature cocoon.",cc\Name$,183,32,246)
			Case 5:
				If cc\Stats\Hat>0 Then Object_ChaoManager_TakeOffHat(cc)
				Select cc\Stats\ReviveAble
				Case 0: Menu_Transporter_Goodbye_DeleteAChao(cc\Number) : die=True
					Interface_CreateChaoNamedMsg("has passed away.",cc\Name$,98,98,98)
				Case 1: ChaoManager_Revive(cc)
					Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoReincarnation)
					Interface_CreateChaoNamedMsg("has reincarnated.",cc\Name$,249,97,186)
				End Select
		End Select

		Select die
		Case True:
			Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoDeath)
		Case False:
			SaveGame_Chao(cc)

			obj.tObject = Object_Chao_Create(cc\Position\x#, cc\Position\y#, cc\Position\z#, cc\g\Motion\Direction#)
			cc2.tChaoManager = Object_ChaoManager_Create(cc\Number, obj, born)

			Delay(1)

			Select cc\Stats\Age
				Case 1: If Not(SHELLSUM(1)+1>=10) Then
						obj.tObject = Object_Shell_Create(cc\Position\x#, cc\Position\y#, cc\Position\z#, cc\g\Motion\Direction#, SHELL_BOTTOM, cc\Stats\Color, False, cc\Stats\Monotone, cc\Stats\Shiny, cc\Stats\Textured)
						obj.tObject = Object_Shell_Create(cc\Position\x#, cc\Position\y#, cc\Position\z#, cc\g\Motion\Direction#, SHELL_TOP, cc\Stats\Color, True, cc\Stats\Monotone, cc\Stats\Shiny, cc\Stats\Textured)
					Else
						ii.tItem = Item_Create(TOTALITEMS+1, 4, SHELL_BOTTOM, cc\Stats\Color)
						ii.tItem = Item_Create(TOTALITEMS+1, 4, SHELL_TOP, cc\Stats\Color)
						Interface_CreateOverlapping2ChaoMsg(4,"Garden too full to spawn more shells.","The born chao's remains has been sent to your inventory.",0,255,255)
					EndIf
			End Select
		End Select

		FreeEntity cc\obj\Entity
		Delete cc\obj\Position
		Delete cc\obj\Rotation
		Delete cc\obj

		FreeEmitter cc\Pivot
		If cc\Mesh<>0 Then FreeEntity cc\Mesh
		FreeEntity cc\Pivot
		Delete cc\Emo
		Delete cc

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Evolve(cc.tChaoManager)

		Select ChaoManager_GetHighestSkill(cc)
			Case 1: cc\Stats\Shape=CHAOSHAPE_RUN
			Case 2: cc\Stats\Shape=CHAOSHAPE_SWIM
			Case 3: cc\Stats\Shape=CHAOSHAPE_FLY
			Case 4: cc\Stats\Shape=CHAOSHAPE_STRENGTH
			Default: cc\Stats\Shape=CHAOSHAPE_NORMAL
		End Select

		cc\Stats\Side=ChaoManager_GetHighestSide(cc)

		For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],0 : Next
		Select cc\Stats\Side
			Case 1: Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoHeroEvo) : Interface_CreateChaoNamedMsg("has evolved into hero form.",cc\Name$,229,232,207)
			Case 2: Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoDarkEvo) : Interface_CreateChaoNamedMsg("has evolved into dark form.",cc\Name$,119,76,76)
			Case 3: Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoNeutralEvo) : Interface_CreateChaoNamedMsg("has evolved into neutral form.",cc\Name$,100,235,233)
		End Select
		ChannelVolume(Game\Channel_ChaoEffect,Menu\Settings\VolumeM#)

	End Function

	Function ChaoManager_GetHighestSkill(cc.tChaoManager)

		For i=1 To 7
			CHAOCHECKER_SKILL(i)=True
			For j=1 To 7
				If Not(cc\Stats\Skills[i]>=cc\Stats\Skills[j]) Then CHAOCHECKER_SKILL(i)=False
			Next
		Next

		h=0
		Repeat : h=Rand(1,7) : Until CHAOCHECKER_SKILL(h)=True
		Return h

	End Function

	Function ChaoManager_GetHighestSide(cc.tChaoManager)

		CHAOCHECKER_SIDE(0) = cc\Stats\Love[1] - cc\Stats\Love[2]

		If CHAOCHECKER_SIDE(0) > 100.0*(1.0/3.0) Then
			Return 1
		ElseIf CHAOCHECKER_SIDE(0) < -100.0*(1.0/3.0) Then
			Return 2
		Else
			Return 3
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Revive(cc.tChaoManager)

		cc\Stats\Run#=cc\Stats\Run#*0.1
		cc\Stats\Swim#=cc\Stats\Swim#*0.1
		cc\Stats\Fly#=cc\Stats\Fly#*0.1
		cc\Stats\Strength#=cc\Stats\Strength#*0.1
		cc\Stats\Stamina#=cc\Stats\Stamina#*0.1
		cc\Stats\Intelligence#=cc\Stats\Intelligence#*0.1
		cc\Stats\Luck#=cc\Stats\Luck#*0.1	

		cc\Stats\CurrentRun#=0
		cc\Stats\CurrentSwim#=0
		cc\Stats\CurrentFly#=0
		cc\Stats\CurrentStrength#=0
		cc\Stats\CurrentStamina#=0
		cc\Stats\CurrentIntelligence#=0
		cc\Stats\CurrentLuck#=0

		cc\Stats\Age=0
		cc\Stats\Shape=1
		cc\HatchTimer=Rand(60,90)*secs#
		cc\Stats\ShellGrit#=4+Rand(0,10)
		cc\Stats\ReviveAble=0
		cc\Stats\Happiness#=0

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Gain(gaintype1, gaintype2, cc.tChaoManager, eatcycle=0, instage=0)

		gainedstat=0

		; Gain stats
		Select gaintype1
			Case 1:
				Select gaintype2
					Case FRUIT_ROUND:
						gainedstat = 5
					Case FRUIT_CUBICLE:
						Select(Rand(1,2))
						Case 1: gainedstat = 6
						Case 2: gainedstat = 5
						End Select
					Case FRUIT_TRIANGULAR:
						Select(Rand(1,3))
						Case 1: gainedstat = 1
						Case 2: gainedstat = 3
						Case 3: gainedstat = 4
						End Select
					Case FRUIT_HEART:
						If eatcycle=0 And cc\Stats\Age>2 Then
							cc\MateTimer=0
						EndIf
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 4
						End Select
					Case FRUIT_APPLE:
						gainedstat = 4
					Case FRUIT_ORANGE:
						gainedstat = 5
					Case FRUIT_BANANA:
						gainedstat = 2
					Case FRUIT_PEAR:
						gainedstat = 6
					Case FRUIT_GRAPE:
						Select(Rand(1,2))
						Case 1: gainedstat = 2
						Case 2: gainedstat = 5
						End Select
					Case FRUIT_WATERMELON:
						gainedstat = 2
					Case FRUIT_MANGO:
						gainedstat = 3	
					Case FRUIT_LEMON:
						gainedstat = 7
					Case FRUIT_MANDARINE:
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 3
						End Select
					Case FRUIT_STRAWBERRY:
						Select(Rand(1,2))
						Case 1: gainedstat = 1
						Case 2: gainedstat = 4
						End Select
					Case FRUIT_COCONUT:
						Select(Rand(1,2))
						Case 1: gainedstat = 7
						Case 2: gainedstat = 1
						End Select
					Case FRUIT_PINEAPPLE:
						Select(Rand(1,2))
						Case 1: gainedstat = 4
						Case 2: gainedstat = 2
						End Select
					Case FRUIT_KIWI:
						gainedstat = 4
					Case FRUIT_APRICOT:
						Select(Rand(1,2))
						Case 1: gainedstat = 4
						Case 2: gainedstat = 1
						End Select
					Case FRUIT_PEACH:
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 6
						End Select
					Case FRUIT_PITAYA:
						Select(Rand(1,3))
						Case 1: gainedstat = 7
						Case 2: gainedstat = 6
						Case 3: gainedstat = 3
						End Select
					Case FRUIT_CHERRY:
						gainedstat = 7
					Case FRUIT_BLACKBERRY:
						Select(Rand(1,2))
						Case 1: gainedstat = 3
						Case 2: gainedstat = 4
						End Select
					Case FRUIT_CARAMBOLA:
						gainedstat = 7
					Case FRUIT_PLUM:
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 1
						End Select
					Case FRUIT_COBALT:
						gainedstat = 1
					Case FRUIT_JUICY:
						Select(Rand(1,7))
						Case 1: gainedstat = 1
						Case 2: gainedstat = 2
						Case 3: gainedstat = 3
						Case 4: gainedstat = 4
						Case 5: gainedstat = 5
						Case 6: gainedstat = 6
						Case 7: gainedstat = 7
						End Select
					Case FRUIT_MUSHROOM:
						Select(Rand(1,2))
						Case 1: gainedstat = 6
						Case 2: gainedstat = 4
						End Select
					Case FRUIT_CARROT:
						gainedstat = 6
					Case FRUIT_PARSLEY:
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 6
						End Select
					Case FRUIT_TOMATO:
						Select(Rand(1,2))
						Case 1: gainedstat = 4
						Case 2: gainedstat = 2
						End Select
					Case FRUIT_POTATO:
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 3
						End Select
					Case FRUIT_CORN:
						Select(Rand(1,2))
						Case 1: gainedstat = 6
						Case 2: gainedstat = 1
						End Select
					Case FRUIT_PEPPER:
						Select(Rand(1,2))
						Case 1: gainedstat = 4
						Case 2: gainedstat = 5
						End Select
					Case FRUIT_LETTUCE:
						Select(Rand(1,2))
						Case 1: gainedstat = 2
						Case 2: gainedstat = 6
						End Select
					Case FRUIT_CUCUMBER:
						gainedstat = 2
					Case FRUIT_ONION:
						gainedstat = 4
					Case FRUIT_RADISH:
						gainedstat = 1
					Case FRUIT_BREAD:
						Select(Rand(1,2))
						Case 1: gainedstat = 4
						Case 2: gainedstat = 5
						End Select
					Case FRUIT_CHEESE:
						Select(Rand(1,2))
						Case 1: gainedstat = 3
						Case 2: gainedstat = 6
						End Select
					Case FRUIT_PIZZA:
						gainedstat = 5
					Case FRUIT_BURGER:
						gainedstat = 5
					Case FRUIT_CHILI:
						gainedstat = 1
					Case FRUIT_RICEBALL:
						Select(Rand(1,2))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 7
						End Select
					Case FRUIT_CANDY:
						gainedstat = 7
					Case FRUIT_COOKIE:
						Select(Rand(1,3))
						Case 1: gainedstat = 7
						Case 2: gainedstat = 3
						Case 3: gainedstat = 4
						End Select
					Case FRUIT_ICECREAM:
						Select(Rand(1,2))
						Case 1: gainedstat = 2
						Case 2: gainedstat = 1
						End Select
					Case FRUIT_POPSICLE:
						gainedstat = 2
					Case FRUIT_MARSHMELLOW:
						gainedstat = 7
					Case FRUIT_CHOCOLATE:
						Select(Rand(1,3))
						Case 1: gainedstat = 5
						Case 2: gainedstat = 4
						Case 3: gainedstat = 6
						End Select
					Case FRUIT_PIE:
						gainedstat = 5
					Case FRUIT_BRIGHT:
						If eatcycle=0 Then
							If cc\Stats\Bright=0 Then
								Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoReincarnation)
								Interface_CreateChaoNamedMsg("will go bright.",cc\Name$,245,245,0)
								cc\Stats\Bright=1
							EndIf
						EndIf
						gainedstat = 5
					Case FRUIT_GOLDEN:
						If eatcycle=0 Then
							If cc\Stats\ReviveEternal=0 Then
								Game\Channel_ChaoEffect=PlaySmartSound(Sound_ChaoReincarnation)
								Interface_CreateChaoNamedMsg("was granted eternal life.",cc\Name$,245,245,0)
								cc\Stats\ReviveEternal=1
								cc\Emo\Eternal=cc\Stats\ReviveEternal
							EndIf
						EndIf
						gainedstat = 5
				End Select
			Case 2:
				Select gaintype2
					Case 8:
						gainedstat = Rand(1,7)
					Case 9:
						gainedstat = Rand(1,7)*10
					Default:
						gainedstat = gaintype2
				End Select

		End Select

		; Level up
		If instage =0 Then gainamount = Rand(1,5) Else gainamount = 1
		If (gaintype2=cc\Stats\FavouriteFood And gaintype1=1) Then gainamount = gainamount*1.5
		If gainedstat>10 Then gainedstat=gainedstat/10 : gainamount = gainamount+5

		For i=1 To gainamount
			Select gainedstat
				Case 1: cc\Stats\CurrentRun#=cc\Stats\CurrentRun#+1 : DebugLog("run")
				Case 2: cc\Stats\CurrentSwim#=cc\Stats\CurrentSwim#+1 : DebugLog("swim")
				Case 3: cc\Stats\CurrentFly#=cc\Stats\CurrentFly#+1 : DebugLog("fly")
				Case 4: cc\Stats\CurrentStrength#=cc\Stats\CurrentStrength#+1 : DebugLog("strength")
				Case 5: cc\Stats\CurrentStamina#=cc\Stats\CurrentStamina#+1 : DebugLog("stamina")
				Case 6: cc\Stats\CurrentIntelligence#=cc\Stats\CurrentIntelligence#+1 : DebugLog("intelligence")
				Case 7: cc\Stats\CurrentLuck#=cc\Stats\CurrentLuck#+1 : DebugLog("luck")
			End Select

			If cc\Stats\CurrentRun#>10 And cc\Stats\Run#<50 Then
				cc\Stats\Run#=cc\Stats\Run#+1 : cc\Stats\CurrentRun#=cc\Stats\CurrentRun#-11 : Interface_CreateChaoNamedMsg("has leveled up in run.",cc\Name$,233,233,233)
				If cc\Stats\Age<=1 And cc\Stats\Run#=4 Then Interface_CreateChaoNamedMsg("learned to walk.",cc\Name$,220,220,220)
			EndIf
			If cc\Stats\CurrentSwim#>10 And cc\Stats\Swim#<50 Then
				cc\Stats\Swim#=cc\Stats\Swim#+1 : cc\Stats\CurrentSwim#=cc\Stats\CurrentSwim#-11 : Interface_CreateChaoNamedMsg("has leveled up in swim.",cc\Name$,233,233,233)
				If cc\Stats\Swim#=3 Then Interface_CreateChaoNamedMsg("learned to swim.",cc\Name$,220,220,220)
			EndIf
			If cc\Stats\CurrentFly#>10 And cc\Stats\Fly#<50 Then
				cc\Stats\Fly#=cc\Stats\Fly#+1 : cc\Stats\CurrentFly#=cc\Stats\CurrentFly#-11 : Interface_CreateChaoNamedMsg("has leveled up in fly.",cc\Name$,233,233,233)
				If cc\Stats\Fly#=6 Then Interface_CreateChaoNamedMsg("learned to fly.",cc\Name$,220,220,220)
			EndIf
			If cc\Stats\CurrentStrength#>10 And cc\Stats\Strength#<50 Then
				cc\Stats\Strength#=cc\Stats\Strength#+1 : cc\Stats\CurrentStrength#=cc\Stats\CurrentStrength#-11 : Interface_CreateChaoNamedMsg("has leveled up in strength.",cc\Name$,233,233,233)
			EndIf
			If cc\Stats\CurrentStamina#>10 And cc\Stats\Stamina#<50 Then
				cc\Stats\Stamina#=cc\Stats\Stamina#+1 : cc\Stats\CurrentStamina#=cc\Stats\CurrentStamina#-11 : Interface_CreateChaoNamedMsg("has leveled up in stamina.",cc\Name$,233,233,233)
			EndIf
			If cc\Stats\CurrentIntelligence#>10 And cc\Stats\Intelligence#<50 Then
				cc\Stats\Intelligence#=cc\Stats\Intelligence#+1 : cc\Stats\CurrentIntelligence#=cc\Stats\CurrentIntelligence#-11 : Interface_CreateChaoNamedMsg("has leveled up in intelligence.",cc\Name$,233,233,233)
				If cc\Stats\Intelligence#=2 Then Interface_CreateChaoNamedMsg("learned to play with toys.",cc\Name$,220,220,220)
				If cc\Stats\Intelligence#=4 Then Interface_CreateChaoNamedMsg("learned to put on hats.",cc\Name$,220,220,220)
			EndIf
			If cc\Stats\CurrentLuck#>10 And cc\Stats\Luck#<50 Then
				cc\Stats\Luck#=cc\Stats\Luck#+1 : cc\Stats\CurrentLuck#=cc\Stats\CurrentLuck#-11 : Interface_CreateChaoNamedMsg("has leveled up in luck.",cc\Name$,233,233,233)
			EndIf
		Next

		; Affect hunger
		If gaintype1=1 Then
			cc\Stats\Hunger#=cc\Stats\Hunger#-1
			If gaintype2<>cc\Stats\FavouriteFood Then cc\Stats\TooFull#=cc\Stats\TooFull#+1 Else cc\Stats\TooFull#=cc\Stats\TooFull#+Rand(0,1)
		EndIf
		cc\HatchTimer = cc\HatchTimer-5*secs#

		; Auto-save
		cc\StatChange=cc\StatChange+1
		If cc\StatChange>=10 And instage=0 Then
			SaveGame_AllChaoStuff()
			Game\Interface\AutoSaveShowTimer=0.5*secs#
			cc\StatChange=0
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function ChaoManager_GiveRandomFace()
		Select(Rand(1,22))
			Case 1,2:	Return CHAOEMO_happy
			Case 3,4:	Return CHAOEMO_sad
			Case 5,6:	Return CHAOEMO_angry
			Case 7:		Return CHAOEMO_hurt
			Case 8:		Return CHAOEMO_worried
			Case 9:		Return CHAOEMO_shocked
			Case 10:	Return CHAOEMO_thrilled
			Case 11,12:	Return CHAOEMO_joyful
			Case 13:	Return CHAOEMO_wicked
			Case 14:	Return CHAOEMO_drowsy
			Case 15,16:	Return CHAOEMO_bored
			Case 17:	Return CHAOEMO_cheerful
			Case 18,19:	Return CHAOEMO_lovely
			Case 20:	Return CHAOEMO_flirty
			Case 21,22:	Return CHAOEMO_default
		End Select
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function ChaoManager_GetHappy(cc.tChaomanager,value=1)
		cc\Stats\Happiness#=cc\Stats\Happiness#+value
		If cc\Stats\Happiness#>=90 Then
			Select(Rand(1,5))
			Case 1: Interface_CreateChaoNamedMsg("is very happy.",cc\Name$,10,241,255)
			Default:
			End Select
		EndIf
		cc\Stats\CharacterLove[pp(1)\Character]=cc\Stats\CharacterLove[pp(1)\Character]+value
		If cc\Stats\CharacterLove[pp(1)\Character]>=75 Then
			Select(Rand(1,5))
			Case 1,2: Interface_CreateChaoNamedMsg("really likes " + SingleCharNames(pp(1)\Character),cc\Name$,10,241,255)
			Default:
			End Select
		EndIf
		Select CHARSIDES(InterfaceChar(pp(1)\Character))
			Case 1: cc\Stats\HeroLove#=cc\Stats\HeroLove#+value
			Case 2: cc\Stats\DarkLove#=cc\Stats\DarkLove#+value
		End Select
		cc\JustGotHappyTimer=1.25*secs#
	End Function
	Function ChaoManager_GetSad(cc.tChaomanager,value=1)
		cc\Stats\Happiness#=cc\Stats\Happiness#-value
		If cc\Stats\Happiness#<=-90 Then
			Select(Rand(1,5))
			Case 1: Interface_CreateChaoNamedMsg("is very sad.",cc\Name$,76,67,149)
			Default:
			End Select
		EndIf
		cc\Stats\CharacterLove[pp(1)\Character]=cc\Stats\CharacterLove[pp(1)\Character]-value
		If cc\Stats\CharacterLove[pp(1)\Character]<=-75 Then
			Select(Rand(1,5))
			Case 1,2: Interface_CreateChaoNamedMsg("really hates " + SingleCharNames(pp(1)\Character),cc\Name$,76,67,149)
			Default:
			End Select
		EndIf
		Select CHARSIDES(InterfaceChar(pp(1)\Character))
			Case 1: cc\Stats\HeroLove#=cc\Stats\HeroLove#-value
			Case 2: cc\Stats\DarkLove#=cc\Stats\DarkLove#-value
		End Select
		cc\JustGotSadTimer=1.25*secs#
	End Function

	; =========================================================================================================
	; =========================================================================================================

	Function ChaoManager_BoostSkill(cc.tChaoManager, skillno)

		Select skillno
			Case 1: Interface_CreateChaoNamedMsg("got boosted in run.",cc\Name$,233,233,233)
			Case 2: Interface_CreateChaoNamedMsg("got boosted in swim.",cc\Name$,233,233,233)
			Case 3: Interface_CreateChaoNamedMsg("got boosted in fly.",cc\Name$,233,233,233)
			Case 4: Interface_CreateChaoNamedMsg("got boosted in strength.",cc\Name$,233,233,233)
			Case 5: Interface_CreateChaoNamedMsg("got boosted in stamina.",cc\Name$,233,233,233)
			Case 6: Interface_CreateChaoNamedMsg("got boosted in intelligence.",cc\Name$,233,233,233)
			Case 7: Interface_CreateChaoNamedMsg("got boosted in luck.",cc\Name$,233,233,233)
		End Select

		For i=1 To 5
			If cc\Stats\Skills[skillno]<50 Then
				Select skillno
					Case 1: cc\Stats\Run#=cc\Stats\Run#+1
					Case 2: cc\Stats\Swim#=cc\Stats\Swim#+1
					Case 3: cc\Stats\Fly#=cc\Stats\Fly#+1
					Case 4: cc\Stats\Strength#=cc\Stats\Strength#+1
					Case 5: cc\Stats\Stamina#=cc\Stats\Stamina#+1
					Case 6: cc\Stats\Intelligence#=cc\Stats\Intelligence#+1
					Case 7: cc\Stats\Luck#=cc\Stats\Luck#+1
				End Select
				cc\Stats\BoostSkills[skillno]=cc\Stats\BoostSkills[skillno]+1
			EndIf
		Next

	End Function
	Function ChaoManager_UsesUniqueAnims(cc.tChaoManager)
		Select cc\Stats\Body
			Case 2,3:
				Return True
			Default:
				Return False
		End Select
	End Function
	Function ChaoManager_RemoveBoostedSkills(cc.tChaoManager)

		For skillno=1 To 7
			If cc\Stats\BoostSkills[skillno]>0 Then
				Select skillno
					Case 1: cc\Stats\Run#=cc\Stats\Run#-cc\Stats\BoostSkills[skillno]
					Case 2: cc\Stats\Swim#=cc\Stats\Swim#-cc\Stats\BoostSkills[skillno]
					Case 3: cc\Stats\Fly#=cc\Stats\Fly#-cc\Stats\BoostSkills[skillno]
					Case 4: cc\Stats\Strength#=cc\Stats\Strength#-cc\Stats\BoostSkills[skillno]
					Case 5: cc\Stats\Stamina#=cc\Stats\Stamina#-cc\Stats\BoostSkills[skillno]
					Case 6: cc\Stats\Intelligence#=cc\Stats\Intelligence#-cc\Stats\BoostSkills[skillno]
					Case 7: cc\Stats\Luck#=cc\Stats\Luck#-cc\Stats\BoostSkills[skillno]
				End Select
				cc\Stats\BoostSkills[skillno]=0
			EndIf
		Next

	End Function

	Function ChaoManager_BoostSkill_Hat(cc.tChaoManager, hattype)

		Select hattype
			Case HAT_CAT_0,HAT_CAT_1,HAT_CAT_2,HAT_CAT_3:
				ChaoManager_BoostSkill(cc, 7)
			Case HAT_STRAW_0:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_WOOL_0,HAT_WOOL_1,HAT_WOOL_2,HAT_WOOL_3, HAT_HOOD_0, HAT_HOOD_1, HAT_TURBAN_0:
				ChaoManager_BoostSkill(cc, 5)
			Case HAT_FORMAL_0,HAT_FORMAL_1:
				ChaoManager_BoostSkill(cc, 3)
			Case HAT_SKULL_0, HAT_GOHAN_0, HAT_GOHAN_1, HAT_GOHAN_2, HAT_GOHAN_3, HAT_GOHAN_4, HAT_GOHAN_5, HAT_GOHAN_6:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_BUCKET_0:
				ChaoManager_BoostSkill(cc, 2)
			Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3:
				ChaoManager_BoostSkill(cc, 1)
			Case HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3:
				ChaoManager_BoostSkill(cc, 7)
			Case HAT_TUNIC_0,HAT_TUNIC_1,HAT_TUNIC_2,HAT_TUNIC_3, HAT_PUMPKIN_0:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_BEANIE_0,HAT_BEANIE_1,HAT_BEANIE_2,HAT_BEANIE_3:
				ChaoManager_BoostSkill(cc, 2)
			Case HAT_FPOT_0:
				ChaoManager_BoostSkill(cc, 5)
			Case HAT_FCROWN_0:
				ChaoManager_BoostSkill(cc, 7)
			Case HAT_SGLASSES_0:
				ChaoManager_BoostSkill(cc, 1)
			Case HAT_PACIFIER_0,HAT_PACIFIER_1,HAT_PACIFIER_2,HAT_PACIFIER_3:
				ChaoManager_BoostSkill(cc, 7)
			Case HAT_CARDBOARD_0:
				ChaoManager_BoostSkill(cc, 5)
			Case HAT_SHELL_0, HAT_KNIGHT_1, HAT_KNIGHT_2, HAT_KNIGHT_0:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_BUNNY_0,HAT_BUNNY_1, HAT_SONICMAN_0,HAT_SONICMAN_1:
				ChaoManager_BoostSkill(cc, 1)
			Case HAT_STACHE_0:
				ChaoManager_BoostSkill(cc, 6)
			Case HAT_HOCKEY_0:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_TOPHAT_0:
				ChaoManager_BoostSkill(cc, 6)
			Case HAT_GLASSES_0,HAT_GLASSES_1,HAT_GLASSES_2,HAT_GLASSES_3, HAT_EGGMAN_0, HAT_EGGMAN_1:
				ChaoManager_BoostSkill(cc, 6)
			Case HAT_HEADPHONES_0,HAT_HEADPHONES_1:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_PLUMBER_0,HAT_PLUMBER_1,HAT_PLUMBER_2,HAT_PLUMBER_3,HAT_PLUMBER_4:
				ChaoManager_BoostSkill(cc, 5)
			Case HAT_BANDANA_0,HAT_BANDANA_1,HAT_BANDANA_2,HAT_BANDANA_3,HAT_BANDANA_4:
				ChaoManager_BoostSkill(cc, 3)
			Case HAT_HEADBAND_0,HAT_HEADBAND_1,HAT_HEADBAND_2,HAT_HEADBAND_3,HAT_HEADBAND_4:
				ChaoManager_BoostSkill(cc, 4)
			Case HAT_PRINCIPAL_0,HAT_PRINCIPAL_1,HAT_PRINCIPAL_2:
				ChaoManager_BoostSkill(cc, 6)
			Case HAT_DEALER_0:
				ChaoManager_BoostSkill(cc, 7)
			Case HAT_DEALER_1:
				ChaoManager_BoostSkill(cc, 1)
			Case HAT_SNORKEL_0:
				ChaoManager_BoostSkill(cc, 2)
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_OffspringMono%(parent1,parent2)
		If parent1 > 0 Or parent1 > 0
			Return Rand(0, 1)
		Else
			Return 1
		EndIf

	End Function
	Function ChaoManager_OffspringColor(parent1,parent2)
		Select parent1
			Case CHAOCOLOR_CELESTE:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_RED
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_AZURE:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_CELESTE
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_BLUE:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_AZURE
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_WHITE:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_WHITE
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_GREY
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PINK
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_GREY:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_GREY
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_GREY
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PURPLE
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_BLACK:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_BLACK
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_RED:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_RED
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_ORANGE:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_ORANGE
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_YELLOW:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PINK
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_BROWN:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PURPLE
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_GREEN:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_RED
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_LIME:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PINK
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_PURPLE:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_RED
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_BLUE
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_BROWN
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PURPLE
					Default:	Return parent1
				End Select
			Case CHAOCOLOR_PINK:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_GREY
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PINK
					Default:	Return parent1
				End Select
			Default:
				Select parent2
					Case CHAOCOLOR_CELESTE:	Return CHAOCOLOR_AZURE
					Case CHAOCOLOR_AZURE:	Return CHAOCOLOR_CELESTE
					Case CHAOCOLOR_BLUE:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_WHITE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_GREY:	Return CHAOCOLOR_GREY
					Case CHAOCOLOR_BLACK:	Return CHAOCOLOR_BLACK
					Case CHAOCOLOR_RED:	Return CHAOCOLOR_ORANGE
					Case CHAOCOLOR_ORANGE:	Return CHAOCOLOR_YELLOW
					Case CHAOCOLOR_YELLOW:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_BROWN:	Return CHAOCOLOR_PURPLE
					Case CHAOCOLOR_GREEN:	Return CHAOCOLOR_LIME
					Case CHAOCOLOR_LIME:	Return CHAOCOLOR_GREEN
					Case CHAOCOLOR_PURPLE:	Return CHAOCOLOR_PINK
					Case CHAOCOLOR_PINK:	Return CHAOCOLOR_PINK
					Default:	Return parent1
				End Select
		End Select
	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D