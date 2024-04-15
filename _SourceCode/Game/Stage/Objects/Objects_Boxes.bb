
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Box_Create.tObject(boxtype, x#, y#, z#, pitch#, yaw#, roll#, switchno1=0, switchno2=0, switchno3=0)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\ThisIsABox=True : o\Box = New tObject_Box : o\HasValuesetBox=True
		If (o\ObjType=OBJTYPE_BOXLIGHT) Then o\Switch = New tObject_Switch :  : o\HasValuesetSwitch=True

		Game\Gameplay\TotalBoxes=Game\Gameplay\TotalBoxes+1

		Select boxtype
			Case 0:	Object_CreateHitBox(HITBOXTYPE_BOX,o,7,7,7)
			Case 1:	Object_CreateHitBox(HITBOXTYPE_BOX,o,10.5,10.5,10.5)
			Case 2:	Object_CreateHitBox(HITBOXTYPE_BOX,o,8.75,8.75,8.75)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Select(Rand(1,4))
		Case 1: Object_Acquire_Rotation(o,0,yaw#+0*90,0)
		Case 2: Object_Acquire_Rotation(o,0,yaw#+1*90,0)
		Case 3: Object_Acquire_Rotation(o,0,yaw#+2*90,0)
		Case 4: Object_Acquire_Rotation(o,0,yaw#+3*90,0)
		End Select
		Object_Acquire_Speed(o,0,-2,0)

		o\Box\BoxType=boxtype

		Select o\ObjType
			Case OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO,OBJTYPE_BOXFLOAT: o\Box\hask=false
			Default: o\Box\hask=true
		End Select
		If o\Box\hask Then o\Box\k = Object_BoxBlocker_Create.tBoxBlocker(o\HitBox\y#, x#, y#, z#, o\ObjType)

		If (o\ObjType=OBJTYPE_BOXLIGHT) Then
			o\Switch\SwitchNo[0]=switchno1
			o\Switch\SwitchNo[1]=switchno2
			o\Switch\SwitchNo[2]=switchno3
		EndIf

		Select o\ObjType
			Case OBJTYPE_BOXCAGE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxCage)), Game\Stage\Root)
			Case OBJTYPE_BOXIRON: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxIron)), Game\Stage\Root)
			Case OBJTYPE_BOXMETAL: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxMetal)), Game\Stage\Root)
			Case OBJTYPE_BOXWOODEN: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxWooden)), Game\Stage\Root)
			Case OBJTYPE_BOXLIGHT:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxLightOn)), Game\Stage\Root) : EntityType(o\Entity,COLLISION_OBJECT)
				o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_BoxLightOff)), Game\Stage\Root) : EntityType(o\EntityX,COLLISION_NONE)
			Case OBJTYPE_BOXTNT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxTnt)), Game\Stage\Root)
			Case OBJTYPE_BOXNITRO: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxNitro)), Game\Stage\Root)
			Case OBJTYPE_BOXFLOAT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_BoxFloat)), Game\Stage\Root)
		End Select
		SmartEntity(Mesh_BoxPiece1)
		SmartEntity(Mesh_BoxPiece2)
		SmartEntity(Mesh_BoxPiece3)
		SmartEntity(Mesh_BoxPiece4)

		Select boxtype
			Case 1:
				ScaleEntity o\Entity, 1.5, 1.5, 1.5
				If (o\ObjType=OBJTYPE_BOXLIGHT) Then ScaleEntity o\EntityX, 1.5, 1.5, 1.5
			Case 2:
				ScaleEntity o\Entity, 1.25, 1.25, 1.25
				If (o\ObjType=OBJTYPE_BOXLIGHT) Then ScaleEntity o\EntityX, 1.25, 1.25, 1.25
		End Select

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_Box_GravityStuff(o.tObject, p.tPlayer, d.tDeltaTime)
		; Update collision
		If (Not(o\ObjType=OBJTYPE_BOXLIGHT)) Then
			If o\Psychoed>0 Or o\ObjPickedUp>0  Then
				EntityType(o\Entity,COLLISION_OBJECT_GOTHRU)
			Else
				EntityType(o\Entity,COLLISION_OBJECT)
			EndIf
		Else
			EntityType(o\Entity,COLLISION_OBJECT)
			EntityType(o\EntityX,COLLISION_NONE)
		EndIf

		If (Not(o\ObjType=OBJTYPE_BOXLIGHT Or o\ObjType=OBJTYPE_BOXFLOAT)) Then
			Object_EnforceGravity(o,d)
			Object_EnforceStun(o,p,d,false)
			Object_EnforcePsychokinesis(o,p,d)
			If Not o\Psychoed=0 Or (Not o\ObjPickedUp=0) Then EntityRadius(o\Entity,o\HitBox\y#-3,1)
		EndIf
		; Obj pick up
		If ((Not(o\ObjType=OBJTYPE_BOXLIGHT Or o\ObjType=OBJTYPE_BOXFLOAT)) And Player_IsPowerChar(p)) Then Object_EnforceObjPickUp(o,p)
		
	End Function

	Function Object_Box_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Gravity, stun, psychokinesis
		Object_Box_GravityStuff(o,p,d)

		; Hide/show according to active
		If (o\ObjType=OBJTYPE_BOXLIGHT) Then
			If Object_WhetherHasSwitches(o) Then
				Object_SwitchManager_PerObjectUpdate(o)
				Select o\Switch\SwitchOn
					Case 0: HideEntity(o\Entity) : ShowEntity(o\EntityX)
					Case 1: ShowEntity(o\Entity) : HideEntity(o\EntityX)
				End Select
			EndIf
		EndIf

		; Tnt count down
		If o\Box\TntBoxCount>0 Then
			If o\Box\TntBoxTimer>0 Then o\Box\TntBoxTimer=o\Box\TntBoxTimer-timervalue#
			If (Not(o\Box\TntBoxTimer>0)) Then
				o\Box\TntBoxTimer=2*secs#
			ElseIf o\Box\TntBoxTimer<1*secs# Then
				o\Box\TntBoxCount=o\Box\TntBoxCount+1
				o\Box\TntBoxTimer=0
				EmitSmartSound(Sound_Tnt,o\Entity)
			EndIf
			If o\Box\TntBoxCount>=4 Then o\Box\DestroyBox=True
		EndIf
		
		; Player collided with object
		If (Not(o\ObjType=OBJTYPE_BOXLIGHT Or o\ObjType=OBJTYPE_BOXFLOAT)) and (o\Hit Or o\BombHit) Then
			If o\BombHit Then
				o\Box\DestroyBox=True
			Else
				Select o\ObjType
					Case OBJTYPE_BOXWOODEN:
						If p\Flags\Attacking and p\Flags\InJumpAttack=False Then o\Box\DestroyBox=True
					Case OBJTYPE_BOXMETAL:
						If p\Flags\StronglyAttacking and p\Flags\InJumpAttack=False Then o\Box\DestroyBox=True
					Case OBJTYPE_BOXIRON:
						If p\Flags\StronglyAttacking and p\Flags\InJumpAttack=False Then
							If o\ObjType=OBJTYPE_BOXIRON and Game\SuperForm>0 and Player_IsPowerChar(p) Then o\Box\DestroyBox=True
						EndIf
					Case OBJTYPE_BOXCAGE:
						If p\Flags\StronglyAttacking and p\Flags\InJumpAttack=False Then o\Box\DestroyBox=True
					Case OBJTYPE_BOXTNT:
						If p\Flags\Attacking and p\Flags\InJumpAttack=False Then
							o\Box\DestroyBox=True
						ElseIf o\Box\TntBoxCount=0 Then
							o\Box\TntBoxCount=1 : EmitSmartSound(Sound_Tnt,o\Entity)
						EndIf
					Case OBJTYPE_BOXNITRO:
						o\Box\DestroyBox=True
				End Select
			EndIf
		EndIf
		If o\Box\DestroyBox Then

			; Add to counter
			Gameplay_AddScore(50)

			; Bling!
			Select o\ObjType
				Case OBJTYPE_BOXWOODEN,OBJTYPE_BOXMETAL: EmitSmartSound(Sound_Boxdestroy,o\Entity)
				Case OBJTYPE_BOXIRON,OBJTYPE_BOXCAGE: EmitSmartSound(Sound_Boxirondestroy,o\Entity)
				Case OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO: EmitSmartSound(Sound_Boxirondestroy,o\Entity) : EmitSmartSound(Sound_Explode,o\Entity)
					For i=1 to 12 : Object_Bomb_Create.tBomb(p, o\Position\x#, o\Position\y#, o\Position\z#, 0, 360*(i/12.0), 0, -100, o\HitBox\y#) : Next
			End Select

			; Hurt sonic
			Select o\ObjType
				Case OBJTYPE_BOXTNT,OBJTYPE_BOXNITRO:
					If EntityDistance(o\Entity,p\Objects\entity)<20 Then Player_Hit(p)
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_EXPLODE, o\Entity)
			End Select

			;Release effect
			Select o\Box\BoxType
				Case 0: Object_Pieces_Create(false,o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.0)
				Case 1: Object_Pieces_Create(false,o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.5)
				Case 2: Object_Pieces_Create(false,o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.25)
			End Select
		
			; Delete the object
			o\Done=1
			Return
		EndIf

		; If player got inside box
		If o\Psychoed=0 Then
			i=false
			If o\ObjType=OBJTYPE_BOXLIGHT Then
				If o\Switch\SwitchOn=0 Then i=true
			EndIf
			If o\ObjType=OBJTYPE_BOXFLOAT Then i=true
			If (Not(i)) and p\SpeedLength#<0.5 and EntityDistance(p\Objects\Entity,o\Entity)<o\HitBox\y#/1.5 and p\Objects\Position\y#>o\Position\y# Then
				EntityType(p\Objects\Entity,0)
				PositionEntity p\Objects\Entity, o\Position\x#,o\Position\y#+o\HitBox\y#*2,o\Position\z#
				EntityType(p\Objects\Entity, COLLISION_PLAYER)
			EndIf
		EndIf
		
	End Function