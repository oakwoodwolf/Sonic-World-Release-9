
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tFroggy
		Field Position.tVector
		Field Entity
		Field Mesh
		Field Animation
		Field PreviousAnimation
		Field FroggyFallTimer
		Field FroggyStandStillTimer
		Field FrogSoundPlayed
		Field targetp.tPlayer
		Field ShadowCircle
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Object_Froggy_Create.tFroggy()
	
		f.tFroggy = New tFroggy

		f\Position = New tVector

		f\Entity = CreatePivot()
		LoadCharacterMesh(CHAR_FRO)
		f\Mesh=CopyEntity(CharacterMesh, Game\Stage\Root)
		DeleteCharacterMesh()
		EntityType(f\Entity,COLLISION_OBJECT2_GOTHRU)

		TranslateEntity f\Entity, Game\Gameplay\CheckX#,Game\Gameplay\CheckY#+1,Game\Gameplay\CheckZ#
		MoveEntity f\Entity, 10, -5, -10 : MoveEntity f\Mesh, 10, -5, -10

		If Menu\Settings\Shadows#>0 Then f\ShadowCircle = Init_CircleShadow(f\Entity, f\Mesh, 1.5)
		
		Return f
		
	End Function
	
	; =========================================================================================================

	Function Object_Froggy_Update(f.tFroggy, d.tDeltaTime)

		Select Menu\Members
			Case 1:
				If pp(1)\Character=CHAR_BIG Then
					f\targetp=pp(1)
				Else
					f\targetp=pp(1)
				EndIf
			Case 2:
				If pp(1)\Character=CHAR_BIG Then
					f\targetp=pp(1)
				ElseIf pp(2)\Character=CHAR_BIG Then
					f\targetp=pp(2)
				Else
					f\targetp=pp(1)
				EndIf
			Case 3:
				If pp(1)\Character=CHAR_BIG Then
					f\targetp=pp(1)
				ElseIf pp(2)\Character=CHAR_BIG Then
					f\targetp=pp(2)
				ElseIf pp(3)\Character=CHAR_BIG Then
					f\targetp=pp(3)
				Else
					f\targetp=pp(1)
				EndIf
		End Select

		If f\targetp\Character=CHAR_BIG and (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
			ShowEntity(f\Mesh)
			Object_Froggy_Update_Real(f, f\targetp, d)
			If Menu\Settings\Shadows#>0 Then Update_CircleShadow(f\ShadowCircle, f\Mesh, cam\Entity, 0)
		Else
			HideEntity(f\Mesh)
			If Menu\Settings\Shadows#>0 Then Update_CircleShadow(f\ShadowCircle, f\Mesh, cam\Entity, 1)
		EndIf
		
	End Function

	Function Object_Froggy_Update_Real(f.tFroggy, p.tPlayer, d.tDeltaTime)

		; Update position
		f\Position\x# = EntityX(f\Entity)
		f\Position\y# = EntityY(f\Entity)
		f\Position\z# = EntityZ(f\Entity)

		; Place mesh
		PositionEntity f\Mesh, (f\Position\x#), (f\Position\y#), (f\Position\z#)
		If Game\Victory=0 Then RotateEntity f\Mesh,0,(DeltaYaw#(p\Objects\Entity,f\Mesh) - 180),0 Else RotateEntity f\Mesh,0,p\Animation\Direction#-180,0

		; Animate
		If f\PreviousAnimation<>f\Animation Then
			Select f\Animation
				Case 1: Animate (f\Mesh,1,0.255,f\Animation,10)
				Case 2: Animate (f\Mesh,1,0.544,f\Animation,10)
				Case 3: Animate (f\Mesh,1,1.088,f\Animation,10)
			End Select
			f\PreviousAnimation=f\Animation
		EndIf

		; Decide animation
		If Game\FroggyTimer>0 and (Not(p\Flags\HomingTarget\x#=99999 and p\Flags\HomingTarget\y#=99999 and p\Flags\HomingTarget\z#=99999)) Then
			f\Animation=3
		ElseIf EntityDistance(p\Objects\Froggy, f\Entity)>10 Then
			f\Animation=3
			f\FroggyStandStillTimer=0
		ElseIf EntityDistance(p\Objects\Froggy, f\Entity)>1 Or p\SpeedLength#>0.05 Then
			If f\FroggyStandStillTimer<1.5*secs# Then f\FroggyStandStillTimer=f\FroggyStandStillTimer+timervalue#
			If p\Motion\Ground=False Then
				f\Animation=2
			Else
				If p\SpeedLength#<0.01 and f\FroggyStandStillTimer>1.25*secs# Then f\Animation=1 Else f\Animation=2
			EndIf
		EndIf

		; Deal fall timer
		If p\Motion\Ground=False Or abs(p\Rotation#)>20 Then
			If f\FroggyFallTimer<1*secs# Then f\FroggyFallTimer=f\FroggyFallTimer+timervalue#
		Else
			f\FroggyFallTimer=0
		EndIf

		; Movement
		If Game\FroggyTimer>0 and (Not(p\Flags\HomingTarget\x#=99999 and p\Flags\HomingTarget\y#=99999 and p\Flags\HomingTarget\z#=99999)) Then
			If f\FrogSoundPlayed=0 Then EmitSmartSound(Sound_Frog,f\Entity) : f\FrogSoundPlayed=1
			PositionEntity(p\Flags\HomingMesh, p\Flags\HomingTarget\x#, p\Flags\HomingTarget\y#+0.3, p\Flags\HomingTarget\z#)
			ex# = p\Flags\HomingTarget\x# - f\Position\x#
			ey# = (p\Flags\HomingTarget\y#+3) - f\Position\y#
			ez# = p\Flags\HomingTarget\z# - f\Position\z#
			AlignToVector(f\Entity, ex#, ey#, ez#, 2, .925)
			AlignToVector(f\Mesh, ex#, ey#, ez#, 2, .925)
			TurnEntity f\Mesh, -90, 0, 0
			If EntityDistance(f\Entity,p\Flags\HomingMesh)>10 Then
				MoveEntity(f\Entity, 0, 3.2*d\Delta, 0)
			Else
				MoveEntity(f\Entity, 0, (EntityDistance(f\Entity,p\Flags\HomingMesh)/10)*3.2*d\Delta, 0)
			EndIf
			p\Flags\HomingTarget\x#=99999
			p\Flags\HomingTarget\y#=99999
			p\Flags\HomingTarget\z#=99999
		Else
			f\FrogSoundPlayed=0
			PointEntity f\Entity, p\Objects\Froggy
			If EntityDistance(p\Objects\Froggy, f\Entity)>=150 and f\FroggyFallTimer<0.9*secs# Then
				EntityType(f\Entity,COLLISION_NONE) : PositionEntity f\Entity, p\Objects\Position\x#, p\Objects\Position\y#+1, p\Objects\Position\z#
				PositionEntity f\Mesh, p\Objects\Position\x#, p\Objects\Position\y#+1, p\Objects\Position\z# : EntityType(f\Entity,COLLISION_OBJECT2_GOTHRU)
				f\FroggyFallTimer=0
				MoveEntity f\Entity, 10, 0, -10 : MoveEntity f\Mesh, 10, 0, -10
			Else
				If f\FroggyFallTimer>0.9*secs# and p\Underwater=0 Then
					MoveEntity f\Entity,0,(-1)*d\Delta,0
				Else
					MoveEntity f\Entity,0,0,0.078*p\Physics\UNDERWATERTRIGGER#*EntityDistance(p\Objects\Froggy, f\Entity)*d\Delta
				EndIf
			EndIf
		EndIf

	End Function