; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Chao_Create.tObject(x#, y#, z#, yaw#)
		o.tObject = New tObject : o\ObjType = OBJTYPE_CHAO : o\ID=TempAttribute\ObjectID
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\AlwaysPresent=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Entity = CreatePivot()
		EntityType(o\Entity,COLLISION_OBJECT2_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Chao_Update(o.tObject, p.tPlayer)

		; Obj pick up
		If (ChaoManager_ChaoAlive(o\ChaoObj\targetcc) Or o\ChaoObj\targetcc\Stats\Age=0) And (Menu\Stage=999) Then Object_EnforceObjPickUp(o,p)
		If o\ObjPickedUp=1 Then
			If o\ChaoObj\targetcc\Stats\Age=0 Then Interface_CreateOverlappingChaoMsg(8,"This chao egg has "+Int(o\ChaoObj\targetcc\Stats\ShellGrit)+" cycles left to hatch.",77,155,244)
		EndIf

		; Attack
		If p\Flags\Attacking and o\Hit Then
			If p\Flags\CantAttackChao=False Then
				o\ObjPickedUp=-1
			Else If Menu\ChaoGarden=1 Then
				o\ChaoObj\targetcc\Action=CHAOACTION_COMMON
			EndIf
		EndIf
		If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False

		; Whistle and pet
		If ChaoManager_ChaoAlive(o\ChaoObj\targetcc) Then
			If EntityDistance(p\Objects\Entity,o\Entity)<5 and o\ChaoObj\targetcc\Action=CHAOACTION_COMMON Then p\MayPetTimer=0.5*secs# : o\ChaoObj\targetcc\ShallBePettedTimer=0.5*secs#
			If (Not(p\MayPetTimer>0)) and EntityDistance(p\Objects\Entity,o\Entity)<45 and EntityDistance(p\Objects\Entity,o\Entity)>5 Then p\MayWhistleTimer=0.5*secs# : o\ChaoObj\targetcc\ShallFollowWhistleTimer=0.5*secs#
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_CreateChao(i,color=0,takepos=false,x#=0,y#=0,z#=0)
		If CHAOSLOTS(1,i)=1 Or (i<=3 and CHAOFIRSTTIMER(1)=0) Then
			CHAOSLOTS(1,i)=1
			obj.tObject = Object_Chao_Create(0, 0, 0, 0)
			cc.tChaoManager = Object_ChaoManager_Create(i, obj, false, color, takepos, x#, y#, z#)
		EndIf
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Fruit_Create.tObject(fruittype, x#, y#, z#, growth=5, throw=false)
		o.tObject = New tObject : o\ObjType = OBJTYPE_FRUIT : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\ThisIsAChaoTargetableObj=True

		FRUITSUM(1) = FRUITSUM(1) + 1

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		o\ChaoObj\FruitType=fruittype

		o\Pivot=CreatePivot()
		o\Entity = LoadAnimMesh("ChaoWorld\Fruits\"+FRUITS$(fruittype)+".b3d", Game\Stage\Root)
		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		o\ChaoObj\EatCycle=growth

		If throw Then o\ObjPickedUp=6

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Fruit_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		; Resize
		ScaleEntity o\Entity, 1.0-0.2*abs(o\ChaoObj\EatCycle-5), 1.0-0.2*abs(o\ChaoObj\EatCycle-5), 1.0-0.2*abs(o\ChaoObj\EatCycle-5)

		If o\ChaoObj\ChaoTargetedThis=False Then
			; Gravity
			Object_EnforceGravity(o,d)

			; Obj pick up
			Object_EnforceObjPickUp(o,p)
		EndIf

		; Attack
		If p\Flags\Attacking and p\Flags\CantAttackChao=False and o\Hit Then o\ObjPickedUp=-1
		If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False

		; Delete the object
		If Not(o\ChaoObj\EatCycle>0) Then
			FRUITSUM(1) = FRUITSUM(1) - 1
			If o\ChaoObj\ChaoTargetedThis Then o\ChaoObj\targetcc\FoundTarget=False
			o\ChaoObj\ForceDelete=True
			Select o\ChaoObj\FruitType
				Case FRUIT_ROUND,FRUIT_CUBICLE,FRUIT_TRIANGULAR,FRUIT_HEART,FRUIT_APPLE,FRUIT_ORANGE,FRUIT_BANANA,FRUIT_PEAR,FRUIT_GRAPE,FRUIT_WATERMELON,FRUIT_MANGO,FRUIT_LEMON,FRUIT_MANDARINE,FRUIT_STRAWBERRY,FRUIT_COCONUT,FRUIT_PINEAPPLE,FRUIT_KIWI,FRUIT_APRICOT,FRUIT_PEACH,FRUIT_PITAYA,FRUIT_CHERRY,FRUIT_BLACKBERRY,FRUIT_CARAMBOLA,FRUIT_PLUM,FRUIT_COBALT,FRUIT_JUICY,FRUIT_CARROT,FRUIT_TOMATO,FRUIT_POTATO,FRUIT_CORN,FRUIT_PEPPER,FRUIT_LETTUCE,FRUIT_CUCUMBER,FRUIT_ONION,FRUIT_RADISH,FRUIT_GOLDEN:
					spawnseed#=Rand(1,10)
					If spawnseed#=1 Then
						If (Not(SEEDSUM(1)>=20)) Then
							Object_Seed_Create(o\ChaoObj\FruitType, o\Position\x#, o\Position\y#, o\Position\z#, false)
							Interface_CreateChaoMsg("A seed was dropped.",248,241,135)
						Else
							ii.tItem = Item_Create(TOTALITEMS+1, 6, o\ChaoObj\FruitType, 0, false)
							Interface_CreateOverlapping2ChaoMsg(7,"Garden too full to spawn more seeds.","The rest has been sent to your inventory.",248,241,135)
						EndIf
					EndIf
			End Select
		EndIf

		; Enforce force delete
		Object_EnforceForceDeleteChaoObj(o)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Shell_Create.tObject(x#, y#, z#, yaw#, shelltype, shelltype2, throw=false)
		o.tObject = New tObject : o\ObjType = OBJTYPE_SHELL : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\ThisIsAChaoTargetableObj=True

		SHELLSUM(1) = SHELLSUM(1) + 1

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\ChaoObj\ShellType=shelltype
		o\ChaoObj\ShellType2=shelltype2

		o\Pivot=CreatePivot()
		Select shelltype
			Case SHELL_BOTTOM: o\Entity = LoadAnimMesh("ChaoWorld\Eggs\eggB.b3d", Game\Stage\Root)
			Case SHELL_TOP:	o\Entity = LoadAnimMesh("ChaoWorld\Eggs\eggT.b3d", Game\Stage\Root)
		End Select
		eggtexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(shelltype2)+".png",256)
		EntityTexture o\Entity, eggtexture
		FreeTexture eggtexture

		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		If throw Then o\ObjPickedUp=6

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Shell_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		If o\ChaoObj\ChaoTargetedThis=False Then
			; Gravity
			Object_EnforceGravity(o,d)

			; Obj pick up
			Object_EnforceObjPickUp(o,p)
		EndIf

		; Attack
		If p\Flags\Attacking and p\Flags\CantAttackChao=False and o\Hit Then o\ObjPickedUp=-1
		If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False

		; Enforce force delete
		Object_EnforceForceDeleteChaoObj(o)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Hat_Create.tObject(x#, y#, z#, hattype, throw=false)
		o.tObject = New tObject : o\ObjType = OBJTYPE_HAT : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\ThisIsAChaoTargetableObj=True

		HATSUM(1) = HATSUM(1) + 1

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		o\ChaoObj\HatType=hattype

		o\Pivot=CreatePivot()
		o\Entity = LoadAnimMesh("ChaoWorld\Hats\"+HATS_FILE$(hattype)+".b3d", Game\Stage\Root)
		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		If throw Then o\ObjPickedUp=6

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Hat_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		Select o\ChaoObj\HatType
			Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3,HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3,HAT_PACIFIER_0,HAT_PACIFIER_1,HAT_PACIFIER_2,HAT_PACIFIER_3:
				PositionEntity o\Entity, o\Position\x#, o\Position\y#+1.5, o\Position\z#
			Default:
				PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		End Select
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		If o\ChaoObj\ChaoTargetedThis=False Then
			; Gravity
			Object_EnforceGravity(o,d)

			; Obj pick up
			Object_EnforceObjPickUp(o,p)
		EndIf

		; Attack
		If p\Flags\Attacking and p\Flags\CantAttackChao=False and o\Hit Then o\ObjPickedUp=-1
		If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False

		; Enforce force delete
		Object_EnforceForceDeleteChaoObj(o)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Toy_Create.tObject(toytype, x#, y#, z#, throw=false)
		o.tObject = New tObject : o\ObjType = OBJTYPE_TOY : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\ThisIsAChaoTargetableObj=True

		TOYSUM(1) = TOYSUM(1) + 1

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		o\ChaoObj\ToyType=toytype

		o\Pivot=CreatePivot()
		o\Entity = LoadAnimMesh("ChaoWorld\Toys\"+TOYS_FILE$(toytype)+".b3d", Game\Stage\Root)
		Select toytype
			Case TOY_BEACHBALL: ExtractAnimSeq(o\Entity,1,9)
			Case TOY_SOCCERBALL: ExtractAnimSeq(o\Entity,1,17)
			Case TOY_RATTLE: ExtractAnimSeq(o\Entity,1,9)
		End Select
		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		If throw Then o\ObjPickedUp=6

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Toy_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		If o\ChaoObj\ChaoTargetedThis=False Then
			Select o\ChaoObj\ToyType
				Case TOY_BEACHBALL,TOY_SOCCERBALL:
					Animate o\Entity,1,0,1,10
					If Not(o\Position\y#<Game\Stage\Properties\WaterLevel+0.5) Then Object_EnforceGravity(o,d)
					If o\Position\y#<Game\Stage\Properties\WaterLevel-0.5 Then MoveEntity o\Pivot, 0, 0.05*d\Delta, 0
				Case TOY_RUBBERDUCK:
					If Not(o\Position\y#<Game\Stage\Properties\WaterLevel+0.5) Then Object_EnforceGravity(o,d)
					If o\Position\y#<Game\Stage\Properties\WaterLevel+0.25 Then MoveEntity o\Pivot, 0, 0.05*d\Delta, 0
				Case TOY_RATTLE:
					Animate o\Entity,1,0,1,10
				Default:
					Object_EnforceGravity(o,d)
			End Select
		EndIf

		; Obj pick up
		Object_EnforceObjPickUp(o,p)

		; Attack
		If p\Flags\Attacking and p\Flags\CantAttackChao=False and o\Hit Then o\ObjPickedUp=-1
		If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False

		; Enforce force delete
		Object_EnforceForceDeleteChaoObj(o)
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Tropical_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, fruittype=1, isfromseed=false, growth1#=0, growth2#=0, growth3#=0, growth4#=0, treegrowth#=4)
		o.tObject = New tObject : o\ObjType = OBJTYPE_TROPICAL : o\ID=TempAttribute\ObjectID
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\AlwaysPresent=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,4,6,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		Select fruittype
			Case FRUIT_ROUND,FRUIT_CUBICLE,FRUIT_TRIANGULAR,FRUIT_HEART,FRUIT_BANANA,FRUIT_COCONUT,FRUIT_PITAYA,FRUIT_COBALT,FRUIT_JUICY,FRUIT_GOLDEN:
				o\ChaoObj\TreeType=1
			Case FRUIT_APPLE,FRUIT_ORANGE,FRUIT_PEAR,FRUIT_MANGO,FRUIT_LEMON,FRUIT_MANDARINE,FRUIT_KIWI,FRUIT_APRICOT,FRUIT_PEACH,FRUIT_CHERRY,FRUIT_PLUM:
				o\ChaoObj\TreeType=2
			Case FRUIT_GRAPE,FRUIT_WATERMELON,FRUIT_STRAWBERRY,FRUIT_PINEAPPLE,FRUIT_BLACKBERRY,FRUIT_CARAMBOLA,FRUIT_CARROT,FRUIT_TOMATO,FRUIT_POTATO,FRUIT_CORN,FRUIT_PEPPER,FRUIT_LETTUCE,FRUIT_CUCUMBER,FRUIT_ONION,FRUIT_RADISH:
				o\ChaoObj\TreeType=3
			Default:
				o\ChaoObj\TreeType=1
		End Select

		Select o\ChaoObj\TreeType
			Case 1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tropical)), Game\Stage\Root)
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_TropicalTrunk)), Game\Stage\Root) : EntityType(o\Entity2, COLLISION_OBJECT)
			Case 2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tropical2)), Game\Stage\Root)
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_Tropical2Trunk)), Game\Stage\Root) : EntityType(o\Entity2, COLLISION_OBJECT)
			Case 3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tropical3)), Game\Stage\Root)
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root) : EntityType(o\Entity2, COLLISION_OBJECT)
		End Select
		o\EntityX = FindChild(o\Entity, "items")

		For i=1 to 4
			o\ChaoObj\FruitGrowthTimer[i]=(120+Rand(-30,30))*secs#
			o\ChaoObj\FruitMesh[i]=CreatePivot()
		Next

		o\ChaoObj\FruitType=fruittype
		o\ChaoObj\IsFromSeed=isfromseed

		If isfromseed Then
			o\ChaoObj\FruitGrowth[1]=growth1#
			o\ChaoObj\FruitGrowth[2]=growth2#
			o\ChaoObj\FruitGrowth[3]=growth3#
			o\ChaoObj\FruitGrowth[4]=growth4#
			o\ChaoObj\TreeGrowth=treegrowth# : o\ChaoObj\TreeGrowthTimer=(60+Rand(-30,30))*secs#
		EndIf

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Tropical_Update(o.tObject, p.tPlayer)

		If o\ChaoObj\IsFromSeed=False Or o\ChaoObj\TreeGrowth>=4 Then;!!!

		For i=1 to 4 ;!

		; place fruits
		If o\ChaoObj\FruitCreated[i] Then
			fruititemsX#=EntityX(o\EntityX,1)
			fruititemsY#=EntityY(o\EntityX,1)
			fruititemsZ#=EntityZ(o\EntityX,1)
			Select i
			Case 1: PositionEntity o\ChaoObj\FruitMesh[i], fruititemsX#+1.25, fruititemsY#-0.5, fruititemsZ#+1.25, 1
			Case 2: PositionEntity o\ChaoObj\FruitMesh[i], fruititemsX#+1.25, fruititemsY#-0.5, fruititemsZ#-1.25, 1
			Case 3: PositionEntity o\ChaoObj\FruitMesh[i], fruititemsX#-1.25, fruititemsY#-0.5, fruititemsZ#+1.25, 1
			Case 4: PositionEntity o\ChaoObj\FruitMesh[i], fruititemsX#-1.25, fruititemsY#-0.5, fruititemsZ#-1.25, 1
			End Select
			RotateEntity o\ChaoObj\FruitMesh[i], o\Rotation\x#, o\Rotation\y#, o\Rotation\z#
			ScaleEntity o\ChaoObj\FruitMesh[i], 1.0-0.2*abs(o\ChaoObj\FruitGrowth[i]-5), 1.0-0.2*abs(o\ChaoObj\FruitGrowth[i]-5), 1.0-0.2*abs(o\ChaoObj\FruitGrowth[i]-5)
		EndIf

		; create fruit
		If o\ChaoObj\FruitGrowth[i]>0 and o\ChaoObj\FruitCreated[i]=False Then
			FreeEntity o\ChaoObj\FruitMesh[i]
			o\ChaoObj\FruitMesh[i] = LoadAnimMesh("ChaoWorld\Fruits\"+FRUITS$(o\ChaoObj\FruitType)+".b3d", Game\Stage\Root)
			o\ChaoObj\FruitCreated[i]=True
		EndIf

		; grow
		If o\ChaoObj\FruitGrowth[i]<5 Then
			If Not(o\ChaoObj\FruitGrowthTimer[i]>0) Then
				o\ChaoObj\FruitGrowthTimer[i]=(120+Rand(-30,30))*secs#
				o\ChaoObj\FruitGrowth[i]=o\ChaoObj\FruitGrowth[i]+1
			Else
				o\ChaoObj\FruitGrowthTimer[i]=o\ChaoObj\FruitGrowthTimer[i]-timervalue#
			EndIf
		EndIf

		Next ;!

		; attach
		If EntityDistance(p\Objects\Entity,o\Entity)<4.25 and p\ObjPickUp=0 Then
			Select o\ChaoObj\TreeType
				Case 3: Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_SHAKEVINE), 1)
				Default: Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_SHAKETREE), 1)
			End Select
		EndIf
		If p\ObjPickUpTimer>0 and p\ObjPickUp=0 and o\Hit Then
			o\ObjPickedUp=1
			p\ObjPickUp=1
			p\ObjPickUpTarget=o
			p\ShakeTreeTimer=(Rand(1,2))*secs#
			p\Action=ACTION_SHAKETREE
			Animate o\Entity,1,0.125/2,1,10
			Animate o\Entity2,1,0.125/2,1,10
			Game\CamLock=p\ShakeTreeTimer
			cam\Lock\PreviousPos=cam\Lock\Pos
			cam\Lock\Pos=0
			Select o\ChaoObj\TreeType
				Case 3: cam\Lock\Rotation\x#=25
				Default: cam\Lock\Rotation\x#=-35
			End Select
			cam\Lock\Rotation\y#=cam\TargetRotation\y# : cam\Lock\Rotation\z#=0
			cam\Lock\Zoom#=15
			cam\Lock\Speed#=10/10
		EndIf
		If p\Action=ACTION_SHAKETREE and o\Hit Then
			p\Animation\Direction#=(DeltaYaw#(p\Objects\Entity,o\Entity) - 180)
		EndIf

		; drop
		If o\ObjPickedUp=1 and p\ObjPickUp=0 and p\GetFruit=1 Then
			o\ObjPickedUp=0
			p\GetFruit=0
			Animate o\Entity,1,0,1,10
			Animate o\Entity2,1,0,1,10
			Select o\ChaoObj\FruitType
				Case FRUIT_GOLDEN,FRUIT_ROUND,FRUIT_CUBICLE,FRUIT_TRIANGULAR,FRUIT_HEART,FRUIT_APPLE,FRUIT_ORANGE,FRUIT_BANANA,FRUIT_PEAR,FRUIT_GRAPE,FRUIT_WATERMELON,FRUIT_MANGO,FRUIT_LEMON,FRUIT_MANDARINE,FRUIT_STRAWBERRY,FRUIT_COCONUT,FRUIT_PINEAPPLE,FRUIT_KIWI,FRUIT_APRICOT,FRUIT_PEACH,FRUIT_PITAYA,FRUIT_CHERRY,FRUIT_BLACKBERRY,FRUIT_CARAMBOLA,FRUIT_PLUM,FRUIT_COBALT,FRUIT_JUICY:
					planttype$="fruits"
				Case FRUIT_CARROT,FRUIT_TOMATO,FRUIT_POTATO,FRUIT_CORN,FRUIT_PEPPER,FRUIT_LETTUCE,FRUIT_CUCUMBER,FRUIT_ONION,FRUIT_RADISH:
					planttype$="veggies"
				Default:
					planttype$="food"
			End Select
			If Not(FRUITSUM(1)>=30) Then
				maydropfruits=false
				For f=1 to 4
					If o\ChaoObj\FruitGrowth[f]>=3 and o\ChaoObj\FruitCreated[f] Then maydropfruits=true
				Next
				If maydropfruits Then
					Repeat : f=Rand(1,4) : Until (o\ChaoObj\FruitGrowth[f]>=3 and o\ChaoObj\FruitCreated[f]=True)
					obj.tObject = Object_Fruit_Create(o\ChaoObj\FruitType, EntityX(o\ChaoObj\FruitMesh[f]), EntityY(o\ChaoObj\FruitMesh[f]), EntityZ(o\ChaoObj\FruitMesh[f]), o\ChaoObj\FruitGrowth[f])
					o\ChaoObj\FruitGrowth[f]=0
					o\ChaoObj\FruitCreated[f]=False
					FreeEntity o\ChaoObj\FruitMesh[f]
					o\ChaoObj\FruitMesh[f]=CreatePivot()
				Else
					Interface_CreateChaoMsg("No ripe "+planttype$+" to drop.",0,255,0)
				EndIf
			Else
				Interface_CreateChaoMsg("Garden too full to spawn more "+planttype$+".",0,255,0)
			EndIf
		EndIf

		Else;!!!

		If o\ChaoObj\TreeGrowth>=0 Then;!
			If Not(o\ChaoObj\TreeGrowthTimer>0) Then
				o\ChaoObj\TreeGrowth=o\ChaoObj\TreeGrowth+1
				o\ChaoObj\TreeGrowthTimer=(60+Rand(-30,30))*secs#
			Else
				o\ChaoObj\TreeGrowthTimer=o\ChaoObj\TreeGrowthTimer-timervalue#
			EndIf

			If o\ChaoObj\TreeGrowth>0 Then
				ScaleEntity o\Entity, o\ChaoObj\TreeGrowth/4.0, o\ChaoObj\TreeGrowth/4.0, o\ChaoObj\TreeGrowth/4.0
				ScaleEntity o\Entity2, o\ChaoObj\TreeGrowth/4.0, o\ChaoObj\TreeGrowth/4.0, o\ChaoObj\TreeGrowth/4.0
				EntityType(o\Entity2, COLLISION_OBJECT)
			Else
				Select o\ChaoObj\TreeType
					Case 3:
						ScaleEntity o\Entity, 0.15, 0.15, 0.15
						ScaleEntity o\Entity2, 0.15, 0.15, 0.15
					Default:
						ScaleEntity o\Entity, 0.05, 0.05, 0.05
						ScaleEntity o\Entity2, 0.05, 0.05, 0.05
				End Select
				EntityType(o\Entity2, COLLISION_NONE)
			EndIf
		Else;!
			If o\ChaoObj\ForceDelete Then
				FreeEntity o\Entity
				FreeEntity o\Entity2
				Delete o\Position
				Delete o\Rotation
				Delete o
				Return
			EndIf
		EndIf;!

		EndIf;!!!
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Drive_Create.tObject(drivetype, x#, y#, z#, throw=false)
		o.tObject = New tObject : o\ObjType = OBJTYPE_DRIVE : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\ThisIsAChaoTargetableObj=True
		If Menu\ChaoGarden=0 Then o\AlwaysPresent=True

		If Menu\ChaoGarden=1 Then DRIVESUM(1) = DRIVESUM(1) + 1

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		o\ChaoObj\DriveType=drivetype

		o\Pivot=CreatePivot()
		o\Entity = CopyEntity(MESHES(Mesh_Drive1+(drivetype-1)), Game\Stage\Root)
		o\ChaoObj\DrivePitch=Rand(1,45)

		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		If throw Then o\ObjPickedUp=6

		If Menu\ChaoGarden=0 Then o\ChaoObj\CollectTimer=0.375*secs#

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Drive_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		; Movement
		If o\ChaoObj\ChaoTargetedThis=False and o\ObjPickedUp=0 Then
			RotateEntity o\Entity, o\ChaoObj\DrivePitch, EntityYaw(o\Entity), 0
			TurnEntity o\Entity, 0, -0.125*20*d\Delta, 0
		EndIf

		Select Menu\ChaoGarden
			Case 0:
				If o\ChaoObj\CollectTimer>0 Then
					o\ChaoObj\CollectTimer=o\ChaoObj\CollectTimer-timervalue#
				Else
					If EntityDistance(o\Pivot, p\Objects\Entity)<16.25 Then
						PointEntity(o\Pivot, p\Objects\Entity)
						If EntityDistance(o\Pivot, p\Objects\Entity)>20 Then
							MoveEntity(o\Pivot, 0, 0, 3.2*d\Delta)
						ElseIf EntityDistance(o\Pivot, p\Objects\Entity)>4 Then
							MoveEntity(o\Pivot, 0, 0, 1.2*d\Delta)
						Else
							MoveEntity(o\Pivot, 0, 0, (EntityDistance(o\Pivot, p\Objects\Entity)/4)*1.2*d\Delta)
						EndIf
					Else
						MoveEntity(o\Pivot, 0, -0.0125*d\Delta, 0)
					EndIf

					; Delete
					If o\Hit Then
						If Not(Menu\BuddyChaoNumber>0) Then
							If ChannelPlaying(p\Channel_ChaosDrive)=False Then p\Channel_ChaosDrive=PlaySmartSound(Sound_ChaosDrive)
							CarriedItem_CreateFromTouch(1, o\ChaoObj\DriveType)
							o\ChaoObj\ForceDelete=True
							ChaoIcon_Draw(o\ChaoObj\DriveType)
						Else
							If ChannelPlaying(p\Channel_ChaosDrive)=False Then p\Channel_ChaosDrive=PlaySmartSound(Sound_ChaosDrive)
							ChaoManager_Gain(2, o\ChaoObj\DriveType, p\Objects\FollowerChao, 0, 1)
							o\ChaoObj\ForceDelete=True
						EndIf
					
					EndIf
					
				EndIf

				If (p\Action=ACTION_DIE Or p\Action=ACTION_VICTORY Or p\Action=ACTION_VICTORYHOLD) Then o\ChaoObj\ForceDelete=True
			Case 1:
				If o\ChaoObj\ChaoTargetedThis=False Then
					; Gravity
					Object_EnforceGravity(o,d)
		
					; Obj pick up
					Object_EnforceObjPickUp(o,p)
				EndIf

				; Attack
				If p\Flags\Attacking and p\Flags\CantAttackChao=False and o\Hit Then o\ObjPickedUp=-1
				If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False
		End Select

		; Enforce force delete
		Object_EnforceForceDeleteChaoObj(o)

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_CreateDriveFromEnemy(objtype, x#, y#, z#)
		If Not(Object_IsEnemyRobot(objtype)) Then Return
		Select(Rand(1,10))
		Case 1: drivetype=8
		Case 2: drivetype=9
		Default:
			Select objtype
				Case OBJTYPE_PAWN:			drivetype = 5
				Case OBJTYPE_PAWNSHIELD:	drivetype = 4
				Case OBJTYPE_PAWNGUN:		drivetype = 7
				Case OBJTYPE_PAWNSWORD:		drivetype = 4
				Case OBJTYPE_FLAPPER:		drivetype = 3
				Case OBJTYPE_FLAPPERGUN:	drivetype = 5
				Case OBJTYPE_FLAPPERBOMB:	drivetype = 1
				Case OBJTYPE_FLAPPERNEEDLE:	drivetype = 1
				Case OBJTYPE_SPINA:			drivetype = 3
				Case OBJTYPE_SPANA:			drivetype = 6
				Case OBJTYPE_SPONA:			drivetype = 8
				Case OBJTYPE_MOTOBUG:		drivetype = 1
				Case OBJTYPE_CATERKILLER:	drivetype = 6
				Case OBJTYPE_BUZZBOMBER:	drivetype = 5
				Case OBJTYPE_BUZZER:		drivetype = 1
				Case OBJTYPE_CHOPPER:		drivetype = 2
				Case OBJTYPE_CRABMEAT:		drivetype = 2
				Case OBJTYPE_JAWS:			drivetype = 2
				Case OBJTYPE_SPINY:			drivetype = 6
				Case OBJTYPE_GRABBER:		drivetype = 1
				Case OBJTYPE_KIKI:			drivetype = 6
				Case OBJTYPE_COP:			drivetype = 6
				Case OBJTYPE_COPRACER:		drivetype = 6
				Case OBJTYPE_HUNTER:		drivetype = 3
				Case OBJTYPE_HUNTERSHIELD:	drivetype = 4
				Case OBJTYPE_BEETLE:		drivetype = 2
				Case OBJTYPE_BEETLEMONO:	drivetype = 1
				Case OBJTYPE_BEETLESPARK:	drivetype = 7
				Case OBJTYPE_BEETLESPRING:	drivetype = 1
				Case OBJTYPE_ACHAOS:		drivetype = 2
				Case OBJTYPE_ACHAOSBLOB:	drivetype = 2
				Case OBJTYPE_RHINO:			drivetype = 1
				Case OBJTYPE_RHINOSPIKES:	drivetype = 4
				Case OBJTYPE_HORNET3:		drivetype = 5
				Case OBJTYPE_HORNET6:		drivetype = 7
				Case OBJTYPE_AEROC:			drivetype = 3
				Case OBJTYPE_CHASER:		drivetype = 5
				Case OBJTYPE_FIGHTER:		drivetype = 2
				Case OBJTYPE_EGGROBO:		drivetype = 5
				Case OBJTYPE_CAMERON:		drivetype = 2
				Case OBJTYPE_KLAGEN:		drivetype = 3
				Case OBJTYPE_ORBINAUT:		drivetype = 3
				Case OBJTYPE_TYPHOON:		drivetype = 1
				Case OBJTYPE_TYPHOONF:		drivetype = 2
				Case OBJTYPE_ANTON:			drivetype = 1
				Case OBJTYPE_AQUIS:			drivetype = 2
				Case OBJTYPE_BOMBIE:		drivetype = 6
				Case OBJTYPE_NEWTRON:		drivetype = 6
				Case OBJTYPE_PENGUINATOR:	drivetype = 2
				Case OBJTYPE_SLICER:		drivetype = 5
				Case OBJTYPE_SNAILB:		drivetype = 4
				Case OBJTYPE_SPIKES:		drivetype = 4
				Case OBJTYPE_ASTERON:		drivetype = 7
				Case OBJTYPE_BATBOT:		drivetype = 3
				Case OBJTYPE_BUBBLS:		drivetype = 2
				Case OBJTYPE_BUBBLSSPIKES:	drivetype = 4
				Case OBJTYPE_STEELION:		drivetype = 2
				Case OBJTYPE_BALKIRY:		drivetype = 3
				Case OBJTYPE_BURROBOT:		drivetype = 5
				Case OBJTYPE_CRAWL:			drivetype = 4
				Case OBJTYPE_DRAGONFLY:		drivetype = 3
				Case OBJTYPE_MADMOLE:		drivetype = 6
				Case OBJTYPE_MANTA:			drivetype = 7
				Case OBJTYPE_MUSHMEANIE:	drivetype = 6
				Case OBJTYPE_OCTUS:			drivetype = 2
				Case OBJTYPE_PATABATA:		drivetype = 3
				Case OBJTYPE_ZOOMER:		drivetype = 3
				Case OBJTYPE_E1000:			drivetype = 4
				Case OBJTYPE_BALLHOG:		drivetype = 5
				Case OBJTYPE_RHINOTANK:		drivetype = 1
				Case OBJTYPE_TECHNOSQU:		drivetype = 1
				Case OBJTYPE_CATAKILLER:	drivetype = 1
				Case OBJTYPE_CLUCKOID:		drivetype = 3
				Case OBJTYPE_MANTIS:		drivetype = 2
				Case OBJTYPE_NEBULA:		drivetype = 5
				Case OBJTYPE_ROLLER:		drivetype = 1
				Case OBJTYPE_SNOWY:			drivetype = 4
				Case OBJTYPE_SPLATS:		drivetype = 7
				Case OBJTYPE_TOXO:			drivetype = 6
				Case OBJTYPE_HAMMER:		drivetype = 5
				Case OBJTYPE_HAMMERHAMMER:	drivetype = 4
				Case OBJTYPE_HAMMERSHIELD:	drivetype = 4
				Case OBJTYPE_WITCH1:		drivetype = 3
				Case OBJTYPE_WITCH2:		drivetype = 1
				Case OBJTYPE_FCANNON1:		drivetype = 4
				Case OBJTYPE_FCANNON2:		drivetype = 3
				Case OBJTYPE_FCANNON3:		drivetype = 1
				Default:					drivetype = 5
			End Select
		End Select
		obj.tObject = Object_Drive_Create(drivetype, x#, y#, z#)
	End Function
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_TrashCan_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,6,5,6)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Pivot=CreatePivot()
		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_TrashCan)), Game\Stage\Root)

		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_TrashCan_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		; Gravity
		Object_EnforceGravity(o,d)
		
		; Obj pick up
		Object_EnforceObjPickUp(o,p)
		If o\ObjPickedUp=1 Then Interface_CreateOverlappingChaoMsg(8,"Bring items to the trash can to trash them.",108,60,149)

		; Player collided with object
		If o\Hit Then
			If p\ObjPickUp>0 Then
				Select p\ObjPickUpTarget\ObjType
					Case OBJTYPE_FRUIT,OBJTYPE_SHELL,OBJTYPE_DRIVE,OBJTYPE_SEED:
						Select p\ObjPickUpTarget\ObjType
							Case OBJTYPE_FRUIT: Interface_CreateChaoMsg("A fruit was trashed.",75,75,75)
							Case OBJTYPE_SHELL: Interface_CreateChaoMsg("A shell was trashed.",75,75,75)
							Case OBJTYPE_DRIVE: Interface_CreateChaoMsg("A drive was trashed.",75,75,75)
							Case OBJTYPE_SEED: Interface_CreateChaoMsg("A seed was trashed.",75,75,75)
						End Select
						EmitSmartSound(Sound_Trash, o\Entity)
						p\ObjPickUpTarget\ChaoObj\ForceDelete=True
						p\ObjPickUp=0
				End Select
			EndIf
		EndIf

		; Affect other obj
		If o\ObjPickedUp=1 Then
			For o2.tObject=Each tObject
				Select o2\ObjType
					Case OBJTYPE_FRUIT,OBJTYPE_SHELL,OBJTYPE_DRIVE,OBJTYPE_SEED:
						If o2\Hit and o2\ObjPickedUp=0 Then
							Select o2\ObjType
								Case OBJTYPE_FRUIT: Interface_CreateChaoMsg("A fruit was trashed.",75,75,75)
								Case OBJTYPE_SHELL: Interface_CreateChaoMsg("A shell was trashed.",75,75,75)
								Case OBJTYPE_DRIVE: Interface_CreateChaoMsg("A drive was trashed.",75,75,75)
								Case OBJTYPE_SEED: Interface_CreateChaoMsg("A seed was trashed.",75,75,75)
							End Select
							EmitSmartSound(Sound_Trash, o\Entity)
							o2\ChaoObj\ForceDelete=True
						EndIf
				End Select
			Next
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Sack_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,6,5,6)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Pivot=CreatePivot()
		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Sack)), Game\Stage\Root)

		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Sack_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
		PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

		; Gravity
		Object_EnforceGravity(o,d)
		
		; Obj pick up
		Object_EnforceObjPickUp(o,p)
		If o\ObjPickedUp=1 Then Interface_CreateOverlappingChaoMsg(8,"Bring items to the sack to send them to your inventory.",235,179,27)

		; Player collided with object
		If o\Hit Then
			If p\ObjPickUp>0 Then
				Select p\ObjPickUpTarget\ObjType
					Case OBJTYPE_FRUIT,OBJTYPE_SHELL,OBJTYPE_HAT,OBJTYPE_TOY,OBJTYPE_SEED:
						Select p\ObjPickUpTarget\ObjType
							Case OBJTYPE_FRUIT: Interface_CreateChaoMsg("A fruit was sent to your inventory.",217,217,217)
							Case OBJTYPE_SHELL: Interface_CreateChaoMsg("A shell was sent to your inventory.",217,217,217)
							Case OBJTYPE_HAT: Interface_CreateChaoMsg("A hat was sent to your inventory.",217,217,217)
							Case OBJTYPE_TOY: Interface_CreateChaoMsg("A toy was sent to your inventory.",217,217,217)
							Case OBJTYPE_SEED: Interface_CreateChaoMsg("A seed was sent to your inventory.",217,217,217)
						End Select
						EmitSmartSound(Sound_Sack, o\Entity)
						Object_Teleporter_ChaoItem(p, p\ObjPickUpTarget, False)
						p\ObjPickUp=0
				End Select
			EndIf
		EndIf

		; Affect other obj
		If o\ObjPickedUp=1 Then
			For o2.tObject=Each tObject
				Select o2\ObjType
					Case OBJTYPE_FRUIT,OBJTYPE_SHELL,OBJTYPE_HAT,OBJTYPE_TOY,OBJTYPE_SEED:
						If o2\Hit and o2\ObjPickedUp=0 Then
							Select o2\ObjType
								Case OBJTYPE_FRUIT: Interface_CreateChaoMsg("A fruit was sent to your inventory.",217,217,217)
								Case OBJTYPE_SHELL: Interface_CreateChaoMsg("A shell was sent to your inventory.",217,217,217)
								Case OBJTYPE_HAT: Interface_CreateChaoMsg("A hat was sent to your inventory.",217,217,217)
								Case OBJTYPE_TOY: Interface_CreateChaoMsg("A toy was sent to your inventory.",217,217,217)
								Case OBJTYPE_SEED: Interface_CreateChaoMsg("A seed was sent to your inventory.",217,217,217)
							End Select
							EmitSmartSound(Sound_Sack, o\Entity)
							Object_Teleporter_ChaoItem(p, o2, False)
						EndIf
				End Select
			Next
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Breeder_Create.tObject(x#, y#, z#, targetcc.tChaoManager)
		o.tObject = New tObject : o\ObjType = OBJTYPE_BREEDER
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\AlwaysPresent=True

		o\ChaoObj\targetcc = targetcc

		Object_CreateHitBox(HITBOXTYPE_BOX,o,10,6,10)

		Object_Acquire_Position(o,x#,y#,z#)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Breeder)), Game\Stage\Root)
		Animate o\Entity,1,0.0125,1,10

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Breeder_Update(o.tObject, p.tPlayer)

		; Position
		PositionEntity o\Entity, o\ChaoObj\targetcc\Position\x#, o\ChaoObj\targetcc\Position\y#-0.25, o\ChaoObj\targetcc\Position\z#, 1

		Select o\ChaoObj\targetcc\Action
			Case CHAOACTION_BREED:
				; Mate
				PositionEntity o\ChaoObj\targetcc2\Pivot, o\ChaoObj\targetcc\Position\x#, o\ChaoObj\targetcc\Position\y#, o\ChaoObj\targetcc\Position\z#, 1
				o\ChaoObj\targetcc2\g\Motion\Direction#=o\ChaoObj\targetcc\g\Motion\Direction#+180
				MoveEntity o\ChaoObj\targetcc2\Pivot, 0, 0, -1.875
				If o\ChaoObj\targetcc\BreedTimer>0*secs# and o\ChaoObj\targetcc\BreedTimer<1*secs# Then
					offspringchance1#=Rand(1,o\ChaoObj\targetcc\Stats\Luck#)
					offspringchance2#=Rand(1,o\ChaoObj\targetcc2\Stats\Luck#)
					If offspringchance1#>=5 and offspringchance2#>=5 Then
						Interface_CreateChaoNamedMsg("mated with another chao and produced offspring.",o\ChaoObj\targetcc\Name$,0,141,240)
						offspringcolor=ChaoManager_OffspringColor(o\ChaoObj\targetcc\Stats\Color,o\ChaoObj\targetcc2\Stats\Color)
						If (Not(CHAOSUM(1)>=CHAOCOUNT)) Then
							Object_ChaoManager_SpawnNewChao(offspringcolor, true, o\ChaoObj\targetcc\Position\x#, o\ChaoObj\targetcc\Position\y#, o\ChaoObj\targetcc\Position\z#)
						Else
							Interface_Create2ChaoMsg("But there are already too many chao in the garden.","The egg has been sent to your inventory.",0,141,240)
							ii.tItem = Item_Create(TOTALITEMS+1, 3, offspringcolor, 0, false)
						EndIf
					Else
						Interface_CreateChaoNamedMsg("and another chao mated, but were not lucky enough to produce offspring.",o\ChaoObj\targetcc\Name$,0,141,240)
					EndIf

					o\ChaoObj\targetcc\BreedTimer=0
					o\ChaoObj\targetcc2\BreedTimer=0
					PositionEntity o\ChaoObj\targetcc2\Pivot, o\ChaoObj\targetcc\Position\x#, o\ChaoObj\targetcc\Position\y#, o\ChaoObj\targetcc\Position\z#, 1
					o\ChaoObj\targetcc2\g\Motion\Direction#=o\ChaoObj\targetcc\g\Motion\Direction#+180
					MoveEntity o\ChaoObj\targetcc\Pivot, 0, 0, -8
					MoveEntity o\ChaoObj\targetcc2\Pivot, 0, 0, -8

					o\ChaoObj\targetcc\MateTimer=0
					o\ChaoObj\targetcc2\MateTimer=0
				EndIf
			Default:
				; Delete
				If Not(o\ChaoObj\targetcc\Action=CHAOACTION_WAITBREED) Then
					FreeEntity o\Entity
					Delete o\Position
					Delete o
					Return
				EndIf
		End Select
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_GardenPoint_Create.tObject(x#, y#, z#, yaw#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\AlwaysPresent=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,90,yaw#,0)

		If (Menu\Settings\Debug#=1 and Menu\Settings\DebugNodes#=1) and Menu\Developer=1 Then
			o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Locker)), Game\Stage\Root)
		Else
			o\Entity = CreatePivot()
		EndIf

		Return o
	End Function
	
	; =========================================================================================================

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Whistle_Create.tObject(p.tPlayer)
		o.tObject = New tObject : o\ObjType = OBJTYPE_WHISTLE
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\AlwaysPresent=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,0,0,0)

		Object_Acquire_Position(o,p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Whistle)), Game\Stage\Root)
		Animate o\Entity,1,0.5,1,10

		o\ChaoObj\CollectTimer = 0.75*secs#

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Whistle_Update(o.tObject, p.tPlayer)

		; Position
		PositionEntity o\Entity, p\Objects\Position\x#, p\Objects\Position\y#+7+5*p\ScaleFactor#, p\Objects\Position\z#, 1
		RotateEntity o\Entity, 0, p\Animation\Direction#+180, 0

		p\MayNotWhistleTimer=0.5*secs#

		; Affect chao
		For cc.tChaoManager = Each tChaoManager
			If cc\ShallFollowWhistleTimer>0 Then cc\FollowWhistleTimer=5*secs# : cc\WhistleLoveTimer=0 : cc\WhistleThinkTimer=0
		Next

		; Delete
		If o\ChaoObj\CollectTimer>0 Then
			o\ChaoObj\CollectTimer=o\ChaoObj\CollectTimer-timervalue#
		Else
			FreeEntity o\Entity
			Delete o\Position
			Delete o
			Return
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Petter_Create.tObject(p.tPlayer)
		o.tObject = New tObject : o\ObjType = OBJTYPE_PETTER
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\AlwaysPresent=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,0,0,0)

		Object_Acquire_Position(o,p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Petter)), Game\Stage\Root)
		Animate o\Entity,1,0.25,1,10

		o\ChaoObj\CollectTimer = 1.125*secs#

		For cc.tChaoManager = Each tChaoManager
			If o\ChaoObj\ChaoTargetedThis=False Then
			If cc\ShallBePettedTimer>0 and EntityDistance(p\Objects\Entity,cc\Pivot)<5 and (Not(cc\Action=CHAOACTION_PETTED)) Then
				o\ChaoObj\targetcc=cc : cc\Action=CHAOACTION_PETTED
				o\ChaoObj\ChaoTargetedThis=True
			EndIf
			EndIf
		Next

		If o\ChaoObj\ChaoTargetedThis=False Then
			FreeEntity o\Entity
			Delete o\Position
			Delete o
			Return
		EndIf

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Petter_Update(o.tObject, p.tPlayer)

		; Position
		PositionEntity o\Entity, o\ChaoObj\targetcc\Position\x#, o\ChaoObj\targetcc\Position\y#+1.75, o\ChaoObj\targetcc\Position\z#, 1
		RotateEntity o\Entity, 0, o\ChaoObj\targetcc\g\Motion\Direction#, 0

		p\MayNotPetTimer=0.5*secs#

		; Delete
		If o\ChaoObj\CollectTimer>0 Then
			o\ChaoObj\CollectTimer=o\ChaoObj\CollectTimer-timervalue#
		Else
			o\ChaoObj\targetcc\Action=CHAOACTION_COMMON
			ChaoManager_GetHappy(o\ChaoObj\targetcc)
			FreeEntity o\Entity
			Delete o\Position
			Delete o
			Return
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Seed_Create.tObject(fruittype, x#, y#, z#, throw=false, seedmode=0, growth1#=0, growth2#=0, growth3#=0, growth4#=0, treegrowth#=0)
		o.tObject = New tObject : o\ObjType = OBJTYPE_SEED : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\ChaoObj = New tObject_ChaoObj : o\HasValuesetChaoObj=True
		o\ThisIsAChaoTargetableObj=True

		SEEDSUM(1) = SEEDSUM(1) + 1

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		o\ChaoObj\FruitType=fruittype

		o\Pivot=CreatePivot()
		o\Entity = LoadAnimMesh("ChaoWorld\Trees\Seed.b3d", Game\Stage\Root)
		EntityType(o\Pivot,0)
		RotateEntity o\Pivot, 0, o\InitialRotation\y#, 0
		PositionEntity o\Pivot, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)

		If throw Then o\ObjPickedUp=6

		o\ChaoObj\SeedMode=seedmode

		If seedmode=1 Then
			o\ChaoObj\FruitGrowth[1]=growth1#
			o\ChaoObj\FruitGrowth[2]=growth2#
			o\ChaoObj\FruitGrowth[3]=growth3#
			o\ChaoObj\FruitGrowth[4]=growth4#
			o\ChaoObj\TreeGrowth=treegrowth#
		EndIf

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Seed_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		Select o\ChaoObj\SeedMode
			Case 0:
				; Position mesh
				PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
				RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#

				; Gravity
				Object_EnforceGravity(o,d)

				; Obj pick up
				Object_EnforceObjPickUp(o,p)
				If o\ObjPickedUp=1 Then Interface_CreateOverlappingChaoMsg(8,"Stomp on the seed to plant it.",248,241,135)

				; Attack
				If p\Flags\Attacking and p\Flags\CantAttackChao=False and o\Hit Then
					If p\Action=ACTION_STOMP Then
						o\ChaoObj\SeedMode=1 : o\ChaoObj\TreeGrowthTimer=0.75*secs#
					Else
						o\ObjPickedUp=-1
					EndIf
				EndIf
				If o\BombHit Then o\ObjPickedUp=-1 : o\BombHit=False

				; Enforce force delete
				Object_EnforceForceDeleteChaoObj(o)
			Case 1:
				If o\ChaoObj\TreeGrowthTimer>0 Then
					o\ChaoObj\TreeGrowthTimer=o\ChaoObj\TreeGrowthTimer-timervalue#
				Else
					o\ChaoObj\SeedsTree = Object_Tropical_Create.tObject(o\Position\x#, o\Position\y#-1.375, o\Position\z#, 0, 0, 0, o\ChaoObj\FruitType, true, o\ChaoObj\FruitGrowth[1], o\ChaoObj\FruitGrowth[2], o\ChaoObj\FruitGrowth[3], o\ChaoObj\FruitGrowth[4], o\ChaoObj\TreeGrowth)
					Objects_Reset_HasMesh(o\ChaoObj\SeedsTree)
					Objects_Reset_Repose(o\ChaoObj\SeedsTree)
					Objects_Reset_Object(o\ChaoObj\SeedsTree)
					EntityAlpha(o\Entity,0)
					o\ChaoObj\SeedMode=2
				EndIf
			Case 2:
				If Game\Interface\ShallExplodeInventory=False and EntityDistance(p\Objects\Entity,o\Entity)<4.25 and p\ObjPickUp=0 and (Not(o\ChaoObj\SeedsTree\ChaoObj\TreeGrowth=-1)) Then
					Interface_ActivateGardenAction(1, CONTROLTIPS$(TIP_DEMOLISH)+"  ")
					p\MayNotWhistleTimer=0.5*secs#
					p\MayNotPetTimer=0.5*secs#
					If o\ChaoObj\SeedsTree\Hit and Input\Pressed\ActionSkill2 Then
						o\ChaoObj\SeedsTree\ChaoObj\TreeGrowth=-1
						For i=1 to 4 : FreeEntity o\ChaoObj\SeedsTree\ChaoObj\FruitMesh[i] : Next
					EndIf
				EndIf
				If o\ChaoObj\SeedsTree\ChaoObj\TreeGrowth=-1 Then
					o\ChaoObj\SeedMode=0
					EntityAlpha(o\Entity,1)
					o\ChaoObj\SeedsTree\ChaoObj\ForceDelete=True
					o\ChaoObj\FruitGrowth[1]=0
					o\ChaoObj\FruitGrowth[2]=0
					o\ChaoObj\FruitGrowth[3]=0
					o\ChaoObj\FruitGrowth[4]=0
					o\ChaoObj\TreeGrowth=0
				Else
					o\ChaoObj\FruitGrowth[1]=o\ChaoObj\SeedsTree\ChaoObj\FruitGrowth[1]
					o\ChaoObj\FruitGrowth[2]=o\ChaoObj\SeedsTree\ChaoObj\FruitGrowth[2]
					o\ChaoObj\FruitGrowth[3]=o\ChaoObj\SeedsTree\ChaoObj\FruitGrowth[3]
					o\ChaoObj\FruitGrowth[4]=o\ChaoObj\SeedsTree\ChaoObj\FruitGrowth[4]
					o\ChaoObj\TreeGrowth=o\ChaoObj\SeedsTree\ChaoObj\TreeGrowth
				EndIf
		End Select
		
	End Function