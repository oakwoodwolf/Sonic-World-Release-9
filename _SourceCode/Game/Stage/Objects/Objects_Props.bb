
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Plant_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, size#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\ThisIsAPlant=True

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,pitch#,yaw#,roll#)

		Select o\ObjType
			Case OBJTYPE_TREE1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_TREE2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree2Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_TREE3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_TREE4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_TREE5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree5Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_TREE6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree6)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree6Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SHRUB1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shrub1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Shrub1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SHRUB2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shrub2)), Game\Stage\Root) : EntityType(o\Entity, COLLISION_OBJECT)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_SHRUB3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shrub3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_SHRUB4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shrub4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Shrub4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SHRUB5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shrub5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Shrub5Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SHRUB6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Shrub6)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bush1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bush2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bush3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bush4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bush5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Bush6)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_BUSH7:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass6)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS7:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass7)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS8:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass8)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS9:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass9)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_GRASS10:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Grass10)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_SAKURA1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree1Sakura)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SAKURA2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree2Sakura)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree2Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SAKURA3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree3Sakura)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SAKURA4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree4Sakura)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SAKURA5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree5Sakura)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree5Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SAKURA6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree6Sakura)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree6Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_PALM1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Palm1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Palm1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_PALM2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Palm2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Palm2Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_PALM3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Palm3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Palm3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_PALM4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Palm4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Palm4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_WILDPALM1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_WILDPALM2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm2Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_WILDPALM3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_WILDPALM4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_WILDPALM5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm5Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_WILDPALM6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm6)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_WildPalm6Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_FLOWER1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Flower1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_FLOWER2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Flower2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_FLOWER3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Flower3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_FLOWER4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Flower4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
				Animate(o\Entity,1,0.25,1,10)
			Case OBJTYPE_FLOWER5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Flower5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_SNOWY1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree1Snowy)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SNOWY2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree2Snowy)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree2Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SNOWY3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree3Snowy)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SNOWY4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree4Snowy)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SNOWY5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree5Snowy)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree5Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_SNOWY6:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Tree6Snowy)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Tree6Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_VINE1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Vine1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_DRYTREE1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_DryTree1Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_DRYTREE2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_DryTree2Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_DRYTREE3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_DryTree3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_ADABAT1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Adabat1)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_ADABAT2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Adabat2)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), Game\Stage\Root)
			Case OBJTYPE_ADABAT3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Adabat3)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Adabat3Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_ADABAT4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Adabat4)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Adabat4Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
			Case OBJTYPE_ADABAT5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Adabat5)), Game\Stage\Root)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Adabat5Trunk)), Game\Stage\Root) : EntityType(o\EntityX, COLLISION_OBJECT)
		End Select

		ScaleEntity o\Entity, 12*size#, 12*size#, 12*size#
		ScaleEntity o\EntityX, 12*size#, 12*size#, 12*size#

		Return o
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Omochao_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#,omovoiceon=0,omovoicepath$)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\g = Object_Gravity_Create.tGravity() : o\HasGravity=True
		o\Omochao = New tObject_Omochao : o\HasValuesetOmochao=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,4,4,4)

		o\Omochao\Mode=0
		o\Omochao\CustomVoiceOn=omovoiceon

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Pivot=CreatePivot()
		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Omochao)), Game\Stage\Root)
		EntityType(o\Pivot,COLLISION_OBJECT2)
		
		If o\Omochao\CustomVoiceOn=1 Then
			o\Omochao\Voice=LoadSound(Game\Stage\Properties\Path$+omovoicepath$)
		Else
			If SOUNDS_EXISTS(Voice_OMO_Fact[1])=False Then
				For i=1 To 16 : LoadGoodSound(Voice_OMO_Fact[i],1,"Voices/"+"omo"+"/fact"+i+".ogg",2) : Next
				For i=1 To 5 : LoadGoodSound(Voice_OMO_Hurt[i],1,"Voices/"+"omo"+"/hurt"+i+".ogg",2) : Next
				For i=1 To 15 : LoadGoodSound(Voice_OMO_Sad[i],1,"Voices/"+"omo"+"/sad"+i+".ogg",2) : Next
			EndIf
		EndIf 

		SmartSound(Sound_OmochaoFly)

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Omochao_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Position mesh
	PositionEntity o\Entity, o\Position\x#, o\Position\y#, o\Position\z#
	RotateEntity o\Entity, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#
	
		; Animation
	If o\Psychoed>0 Or o\ObjPickedUp>0 Then
		o\Anim=5
	Else
		Select o\Omochao\Mode
			Case 0: o\Anim=2
			Case 1: o\Anim=3
		End Select
	EndIf
	If o\Anim<>o\PreviousAnim Then
		Select o\Anim
			Case 1,2,3: Animate (o\Entity,1,0.255,o\Anim,10)
			Case 4: Animate (o\Entity,1,0.3188,o\Anim,10)
			Default: Animate (o\Entity,1,0.1275,o\Anim,10)
		End Select
		o\PreviousAnim=o\Anim
	EndIf
	
		; Update collision
	If o\Omochao\Mode=1 Or p\Flags\Attacking Or o\Psychoed>0 Or o\ObjPickedUp>0 Or p\ObjPickUpTimer>0 Then
		EntityType(o\Pivot,COLLISION_OBJECT2_GOTHRU)
	Else
		EntityType(o\Pivot,COLLISION_OBJECT2)
	EndIf
	
		; Gravity
	If o\Omochao\Mode=0 Then Object_EnforceGravity(o,d)
	
		; Stun
	Object_EnforceStun(o,p,d,False)
	
		; Psychokinesis
	Object_EnforcePsychokinesis(o,p,d)
	
		; Obj pick up
	Object_EnforceObjPickUp(o,p)
	
		; Update timer
	If o\Psychoed>0 Or o\ObjPickedUp>0 Then
		If o\Omochao\FactTimer>0 Then
			o\Omochao\FactTimer=o\Omochao\FactTimer-timervalue#
		Else
			o\Omochao\FactTimer=6.35*secs#
			If Not(ChannelPlaying(o\Omochao\Channel_Omochao)) Then o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Fact[Rand(1,16)])
		EndIf
	EndIf
	If o\Omochao\AttackedTimer>0 Then o\Omochao\AttackedTimer=o\Omochao\AttackedTimer-timervalue#
	If o\Omochao\OverTimer>0 Then
		o\Omochao\OverTimer=o\Omochao\OverTimer-timervalue#
	Else
		If o\Omochao\Mode=1 Then o\Omochao\Mode=0
	EndIf
	
		; Movement
	If o\Omochao\Mode=1 And (Not(o\Psychoed>0 Or o\ObjPickedUp>0)) Then
		If Not(ChannelPlaying(o\Omochao\Channel_OmochaoFly)) Then o\Omochao\Channel_OmochaoFly=EmitSmartSound(Sound_OmochaoFly,o\Entity)
		TurnEntity o\Pivot, 0, 2*o\Omochao\Direction*d\Delta, 0
		MoveEntity o\Pivot, 0, 0, 0.1*d\Delta
		If o\Omochao\AttackedTimer>1*secs# Then MoveEntity o\Pivot, 0, 0.125*d\Delta, 0
	Else
		StopChannel(o\Omochao\Channel_OmochaoFly)
		If o\Omochao\AttackedTimer>0 Then MoveEntity o\Pivot, 0, 0, -0.075*d\Delta
	EndIf
	
		; Player collided with object
	If (Not(p\ObjPickUpTimer>0)) And o\ObjPickedUp=0 Then
		
		If o\Hit Then
			
			If o\Omochao\CustomVoiceOn=1 Then
				If o\Omochao\Mode=0 Then o\Omochao\Channel_Omochao=PlaySound(o\Omochao\Voice)
			Else
				o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Sad[Rand(1,15)])
			EndIf 
			If p\Flags\Attacking Then
				RotateEntity o\Pivot,0,(DeltaYaw#(p\Objects\Entity,o\Pivot) - 180),0
				o\Omochao\AttackedTimer=0.45*secs#
				o\Omochao\Mode=0
				If p\Flags\CantAttackChao=False Then o\ObjPickedUp=-1
			Else
				If o\Omochao\Mode=0 Then o\Omochao\AttackedTimer=2.5*secs#
				o\Omochao\OverTimer=5*secs#
				o\Omochao\Mode=1
			EndIf
			Select(Rand(1,2))
				Case 1: o\Omochao\Direction=1
				Case 2: o\Omochao\Direction=-1
			End Select
			
			
				
		EndIf
		
		
		If o\BombHit Then
			o\Omochao\Mode=0
			StopChannel(o\Omochao\Channel_Omochao)
			o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Hurt[Rand(1,5)])
			o\BombHit=False
			o\ObjPickedUp=-1
		EndIf
	EndIf
	
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Sprinkler_Create.tObject(x#, y#, z#, yaw#, mode)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Visual = New tObject_Visual : o\HasValuesetVisual=True

		Select mode
			Case 3,4: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)
			Default: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,10,10,10)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select mode
			Case 3,4: Object_Acquire_Rotation(o,0,yaw#,0)
			Default: Object_Acquire_Rotation(o,0,0,0)
		End Select

		o\Mode=mode

		If (Menu\Settings\Debug#=1 And Menu\Settings\DebugNodes#=1) Then
			o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Point)), Game\Stage\Root)
		Else
			o\Entity = CreatePivot()
		EndIf

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Sprinkler_Update(o.tObject, p.tPlayer)

		Select o\Mode
			Case 2:
				If o\InView And (Not(ChannelPlaying(o\Visual\Channel_Visual))) Then o\Visual\Channel_Visual=EmitSmartSound(Sound_Waterfall,o\Entity)
		End Select

		If o\State>0 Then
			o\State=o\State-timervalue#
		Else
			Select o\Mode
				Case 3: o\State=0.05*secs#
				Case 4: o\State=(Rand(1,10)/14.0)*secs#
				Default: o\State=0.15*secs#
			End Select
			Select o\Mode
				Case 1,5:
					If o\Hit And (Not(p\HurtTimer>0)) And (Not(Game\Shield=OBJTYPE_FSHIELD Or p\Character=CHAR_BLA)) Then Player_Hit(p)
					If o\Mode=1 Then i=0 Else i=1
					If EntityDistance(o\Entity,p\Objects\Entity)>30 Then
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FIRESPRINKLER, o\Entity, 0, 0, 0, 0, i, 0.2)
					Else
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FIRESPRINKLER, o\Entity, 0, 0, 0, 0, i, 0.8)
					EndIf
				Case 2:
					If EntityDistance(o\Entity,p\Objects\Entity)>30 Then
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_WATERSPRINKLER, o\Entity, 0, 0, 0, 0, 0, 0.2)
					Else
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_WATERSPRINKLER, o\Entity, 0, 0, 0, 0, 0, 0.8)
					EndIf
				Case 3:
					If Menu\ChaoGarden=1 And (Not(Menu\Stage=999)) And Game\Interface\RaceEnded Then
						For i=1 To 3
						Select(Rand(1,5))
						Case 1: Object_Piece_Create.tObject(Mesh_Balloonpiece1, o\Position\x#+Rand(-5,5)/50.0, o\Position\y#+Rand(0,5)/50.0, o\Position\z#+Rand(-5,5)/50.0, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#, 0.26, 0, False, 0, True)
						Case 2: Object_Piece_Create.tObject(Mesh_Balloonpiece2, o\Position\x#+Rand(-5,5)/50.0, o\Position\y#+Rand(0,5)/50.0, o\Position\z#+Rand(-5,5)/50.0, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#, 0.26, 0, False, 0, True)
						Case 3: Object_Piece_Create.tObject(Mesh_Balloonpiece3, o\Position\x#+Rand(-5,5)/50.0, o\Position\y#+Rand(0,5)/50.0, o\Position\z#+Rand(-5,5)/50.0, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#, 0.26, 0, False, 0, True)
						Case 4: Object_Piece_Create.tObject(Mesh_Balloonpiece4, o\Position\x#+Rand(-5,5)/50.0, o\Position\y#+Rand(0,5)/50.0, o\Position\z#+Rand(-5,5)/50.0, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#, 0.26, 0, False, 0, True)
						Case 5: Object_Piece_Create.tObject(Mesh_Balloonpiece5, o\Position\x#+Rand(-5,5)/50.0, o\Position\y#+Rand(0,5)/50.0, o\Position\z#+Rand(-5,5)/50.0, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#, 0.26, 0, False, 0, True)
						End Select
						Next
					EndIf
				Case 4:
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_DROP, o\Entity)
					EmitSmartSound(Sound_Drop,o\Entity)
			End Select
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Visual_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, size#=1, special#=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\Visual = New tObject_Visual : o\HasValuesetVisual=True

		Select o\ObjType
			Case OBJTYPE_HELICOPTER: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,15,15,15)
			Default: Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select o\ObjType
			Case OBJTYPE_CHAIR,OBJTYPE_PARASOL,OBJTYPE_HELICOPTER,OBJTYPE_RAINBOW: Object_Acquire_Rotation(o,pitch#,yaw#,roll#)
			Case OBJTYPE_ORCA: Object_Acquire_Rotation(o,0,yaw#,0)
			Case OBJTYPE_AIRBALLOON: Object_Acquire_Rotation(o,0,Rand(0,360),0)
			Case OBJTYPE_STAGEVISUAL1, OBJTYPE_STAGEVISUAL2, OBJTYPE_STAGEVISUAL3, OBJTYPE_STAGEVISUAL4, OBJTYPE_STAGEVISUAL5:
				Object_Acquire_Rotation(o,pitch#,yaw#,roll#)	
			Default
				Object_Acquire_Rotation(o,0,0,0)
		End Select

		Select o\ObjType
			Case OBJTYPE_STAGEVISUAL1:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_StageVisual1)), Game\Stage\Root)
				Animate(o\Entity,1,0.25,1,10)
			Case OBJTYPE_STAGEVISUAL2:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_StageVisual2)), Game\Stage\Root)
				Animate(o\Entity,1,0.25,1,10)
			Case OBJTYPE_STAGEVISUAL3:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_StageVisual3)), Game\Stage\Root)
				Animate(o\Entity,1,0.25,1,10)
			Case OBJTYPE_STAGEVISUAL4:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_StageVisual4)), Game\Stage\Root)
				Animate(o\Entity,1,0.25,1,10)
			Case OBJTYPE_STAGEVISUAL5:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_StageVisual5)), Game\Stage\Root)
				Animate(o\Entity,1,0.25,1,10)
				
			Case OBJTYPE_BUTTERFLY:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Butterfly1+Rand(1,3)-1)), Game\Stage\Root)
				Animate o\Entity,1,0.25,1,10
				o\EntityX = CreatePivot() : EntityType(o\EntityX, COLLISION_OBJECT)
				o\Entity2 = CreatePivot()
			Case OBJTYPE_SEAGULL:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Seagull)), Game\Stage\Root)
				Animate o\Entity,1,0.7,1,10
				o\EntityX = CreatePivot() : EntityType(o\EntityX, COLLISION_OBJECT)
				o\Entity2 = CreatePivot()
			Case OBJTYPE_SEAC:
				Select(Rand(1,5))
					Case 1,2,3: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Fish)), Game\Stage\Root)
					Case 4: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Manta)), Game\Stage\Root)
					Case 5: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Turtle)), Game\Stage\Root)
				End Select
				Animate o\Entity,1,0.3,1,10
				o\EntityX = CreatePivot() : EntityType(o\EntityX, COLLISION_OBJECT)
				o\Entity2 = CreatePivot()
			Case OBJTYPE_ORCA:
				If special#=1 Then
					o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Dolphin)), Game\Stage\Root)
				Else
					o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Orca)), Game\Stage\Root)
				EndIf
				Animate o\Entity,1,0.135,1,10
			Case OBJTYPE_CHAIR:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Chair)), Game\Stage\Root) : EntityType(o\Entity, COLLISION_OBJECT)
			Case OBJTYPE_PARASOL:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Parasol)), Game\Stage\Root) : EntityType(o\Entity, COLLISION_OBJECT)
			Case OBJTYPE_AIRBALLOON:
				Select(Rand(1,4))
				Case 1,2: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_AirBalloon1)), Game\Stage\Root)
				Case 3: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_AirBalloon2)), Game\Stage\Root)
				Case 4: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_AirBalloon3)), Game\Stage\Root)
				End Select
				ScaleEntity o\Entity, size#, size#, size#
				Animate o\Entity,1,0.2,1,10
				EntityType(o\Entity,COLLISION_OBJECT)
			Case OBJTYPE_HELICOPTER:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Helicopter)), Game\Stage\Root)
				Animate o\Entity,1,0.2,1,10
				EntityType(o\Entity,COLLISION_OBJECT)
			Case OBJTYPE_RAINBOW:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Rainbow)), Game\Stage\Root)
		End Select
		
		Select o\ObjType
			Case OBJTYPE_STAGEVISUAL1,OBJTYPE_STAGEVISUAL2,OBJTYPE_STAGEVISUAL3,OBJTYPE_STAGEVISUAL4,OBJTYPE_STAGEVISUAL5:
				If special#=1 Then 
					EntityType(o\Entity,COLLISION_NONE)
				Else
					EntityType(o\Entity,COLLISION_OBJECT)
				EndIf 
				ScaleEntity o\Entity, size#, size#, size#
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Visual_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		Select o\ObjType
			Case OBJTYPE_SEAGULL:
				If o\InView And (Not(ChannelPlaying(o\Visual\Channel_Visual))) Then o\Visual\Channel_Visual=EmitSmartSound(Sound_Seagull,o\Entity)
			Case OBJTYPE_CHAIR,OBJTYPE_PARASOL:
				Object_EnforceGravity(o,d)
				Object_EnforcePsychokinesis(o,p,d)
			Case OBJTYPE_HELICOPTER:
				If o\InView And (Not(ChannelPlaying(o\Visual\Channel_Visual))) Then o\Visual\Channel_Visual=EmitSmartSound(Sound_Helicopter,o\Entity)
		End Select

		Select o\ObjType
			Case OBJTYPE_BUTTERFLY,OBJTYPE_SEAGULL,OBJTYPE_SEAC:
				If o\Visual\RetreatTimer>0 Then
					o\Visual\RetreatTimer=o\Visual\RetreatTimer-timervalue#
					If o\Visual\SpeedTimer>0 Then
						o\Visual\SpeedTimer=o\Visual\SpeedTimer-timervalue#
					Else
						Select o\ObjType
							Case OBJTYPE_SEAGULL:
								o\Visual\SpeedTimer=(5+Rand(1,2))*secs#
								o\Visual\Speed#=Rand(1,3)/2.0
								o\Visual\SpeedPitch#=Rand(-30,30)/20.0 : o\Visual\SpeedYaw#=Rand(-30,30)/10.0
							Case OBJTYPE_SEAC:
								o\Visual\SpeedTimer=(1+Rand(1,2)/4.0)*secs#
								o\Visual\Speed#=Rand(1,3)/40.0
								o\Visual\SpeedPitch#=Rand(-30,30)/40.0 : o\Visual\SpeedYaw#=Rand(-30,30)/20.0
							Case OBJTYPE_BUTTERFLY:
								o\Visual\SpeedTimer=(1+Rand(1,2)/2.0)*secs#
								o\Visual\Speed#=Rand(1,3)/4.0
								o\Visual\SpeedPitch#=Rand(-30,30)/4.0 : o\Visual\SpeedYaw#=Rand(-30,30)/4.0
						End Select
					EndIf
					TurnEntity o\EntityX, o\Visual\SpeedPitch#*d\Delta, o\Visual\SpeedYaw#*d\Delta, 0
					MoveEntity o\EntityX, 0, 0, o\Visual\Speed#*d\Delta
				Else
					PositionEntity o\Entity2, o\InitialPosition\x#, o\InitialPosition\y#, o\InitialPosition\z#, 1
					If EntityDistance(o\EntityX,o\Entity2)>1 Then
						PointEntity(o\EntityX,o\Entity2)
						Select o\ObjType
							Case OBJTYPE_SEAGULL:
								MoveEntity o\EntityX, 0, 0, (1.5/2.0)*d\Delta
							Case OBJTYPE_SEAC:
								MoveEntity o\EntityX, 0, 0, (1.5/20.0)*d\Delta
							Case OBJTYPE_BUTTERFLY:
								MoveEntity o\EntityX, 0, 0, (1.5/4.0)*d\Delta
						End Select
					Else
						o\Visual\RetreatTimer=(2+Rand(1,2)/2.0)*secs#
					EndIf
				EndIf
				PositionEntity o\Entity, EntityX(o\EntityX), EntityY(o\EntityX), EntityZ(o\EntityX), 1
				RotateEntity o\Entity, 0, EntityYaw(o\EntityX), 0, 1
			Case OBJTYPE_HELICOPTER:
				If o\Hit Then Player_Hit(p)
		End Select
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Jumper_Create.tObject(x#, y#, z#, yaw#, power#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID

		Select o\ObjType
			Case OBJTYPE_CLOUD: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,12,10,12)
			Case OBJTYPE_POLE: Object_CreateHitBox(HITBOXTYPE_SPEEDY_POLE,o,7,7,7)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select o\ObjType
			Case OBJTYPE_CLOUD: Object_Acquire_Rotation(o,0,Rand(1,360),0)
			Case OBJTYPE_POLE: Object_Acquire_Rotation(o,0,yaw#,0)
		End Select
		Object_Acquire_Power(o,power#)

		Select o\ObjType
			Case OBJTYPE_CLOUD:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Cloud)), Game\Stage\Root)
				Animate o\Entity,1,0.025,1,10
			Case OBJTYPE_POLE:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Pole)), Game\Stage\Root)
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Jumper_Update(o.tObject, p.tPlayer)

		If o\State>0 Then
			o\State=o\State-timervalue#
			If p\Motion\Ground=False And Input\Pressed\ActionRoll Then o\State=0
		Else
			Select o\ObjType
				Case OBJTYPE_POLE: Animate o\Entity,1,0,1,10
			End Select
		EndIf

		; Player collided with object
		If o\Hit And (Not(o\State>0)) Then
			o\State=0.25*secs#
			Select o\ObjType
				Case OBJTYPE_CLOUD: EmitSmartSound(Sound_Cloud,o\Entity)
				Case OBJTYPE_POLE: EmitSmartSound(Sound_Pole,o\Entity)
									Animate o\Entity,3,0.7,2,10
			End Select
			If p\Action=ACTION_STOMP Then
				Player_Action_BumpedBounce_Initiate(p,o\Power#+0.5,1)
			Else
				Player_Action_BumpedBounce_Initiate(p,o\Power#,1)
			EndIf
		EndIf
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Explosion_Create.tObject(x#, y#, z#)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID

		Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,Rand(1,360),0)

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Explosion)), Game\Stage\Root)
		o\Entity2 = FindChild(o\Entity, "hurt")

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Explosion_Update(o.tObject, p.tPlayer)

		Select o\State
			Case 0:
				If EntityDistance(o\Entity,p\Objects\Entity)<160 Then
					o\State=1
					o\HitBox\x#=0 : o\HitBox\y#=0 : o\HitBox\z#=0
					If o\ObjType=OBJTYPE_EXPLOSION Then
						EmitSmartSound(Sound_Psychokinesis,o\Entity)
						Animate(o\Entity,3,0.15,1,10)
					Else
						obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\position\x#, o\position\y#+78, o\position\z#, 90, Rand(1,360), 0)
						Animate(o\Entity,3,0.15,2,10)
					EndIf
					EmitSmartSound(Sound_LaserCharging,o\Entity)
				EndIf
			Case 1:
				i#=EntityDistance(o\Entity,o\Entity2)
				o\HitBox\x#=i# : o\HitBox\y#=i# : o\HitBox\z#=i#

				If o\Hit Then
					Player_Hit(p)
				EndIf

				If o\ObjType=OBJTYPE_EXPLOSION Then
					o\PreviousAnim=Int(AnimTime(o\Entity))
					If o\PreviousAnim>=3 And o\PreviousAnim<=5 Then
						If Not(ChannelPlaying(o\ExtraTexture)) Then o\ExtraTexture=EmitSmartSound(Sound_Bombed,o\Entity)
					EndIf
				EndIf

				If Not(Animating(o\Entity)) Then
					o\State=2
					o\Mode=10*secs#
				EndIf
			Case 2:
				If o\Mode>0 Then
					o\Mode=o\Mode-timervalue#
				Else
					o\State=0
				EndIf
		End Select
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Wisp_Create.tObject(x#, y#, z#, yaw#, move#=0)
		o.tObject = New tObject : o\ObjType = OBJTYPE_WISP : o\ID=TempAttribute\ObjectID
		o\Visual = New tObject_Visual : o\HasValuesetVisual=True

		Object_CreateHitBox(HITBOXTYPE_BOX,o,0,0,0)

		pivot=CreatePivot()
		PositionEntity pivot, x#, y#, z#, 1
		RotateEntity pivot, 0, yaw#, 0, 1
		MoveEntity pivot, 0, 0, move#
		Object_Acquire_Position(o,EntityX(pivot,1),EntityY(pivot,1),EntityZ(pivot,1))
		Object_Acquire_Rotation(o,0,yaw#,0)
		FreeEntity pivot

		o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_WispBlack+Rand(1,17)-1)), Game\Stage\Root)
		Animate o\Entity,1,0.1,1,10
		o\EntityX = CreatePivot()

		Return o
	End Function
	
	; =========================================================================================================
	
Function Object_Wisp_Update(o.tObject, d.tDeltaTime)

		If Game\Victory<>0 Then
			o\AlwaysPresent=True
			Select o\State
				Case 0:
					o\Visual\RetreatTimer=0.4*secs#
					o\State=1
					EntityType(o\EntityX, COLLISION_NONE)
				Case 1:
					MoveEntity o\EntityX, 0, 0, 0.3*d\Delta
					If Not(o\Visual\RetreatTimer>0) Then
						o\State=2
						EntityType(o\EntityX, COLLISION_OBJECT)
					Else
						o\Visual\RetreatTimer=o\Visual\RetreatTimer-timervalue#
					EndIf
				Case 2:
					If o\Visual\SpeedTimer>0 Then
						o\Visual\SpeedTimer=o\Visual\SpeedTimer-timervalue#
					Else
						o\Visual\SpeedTimer=(1+Rand(1,4)/2.0)*secs#
						o\Visual\Speed#=Rand(1,2)/8.0
						o\Visual\SpeedPitch#=Rand(-10,10)/4.0 : o\Visual\SpeedYaw#=Rand(-10,10)/4.0
					EndIf
					If EntityPitch(o\EntityX)<=0 And EntityPitch(o\EntityX)>-50 Then TurnEntity o\EntityX, o\Visual\SpeedPitch#*d\Delta, 0, 0
					TurnEntity o\EntityX, 0, o\Visual\SpeedYaw#*d\Delta, 0
					MoveEntity o\EntityX, 0, 0, o\Visual\Speed#*d\Delta
			End Select
		Else
			o\AlwaysPresent=False
			o\State=0
		EndIf

		PositionEntity o\Entity, EntityX(o\EntityX), EntityY(o\EntityX), EntityZ(o\EntityX), 1
		RotateEntity o\Entity, EntityPitch(o\EntityX), EntityYaw(o\EntityX), 0, 1

	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D