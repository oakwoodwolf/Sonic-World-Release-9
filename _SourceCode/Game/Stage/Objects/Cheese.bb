Const CHEESEANIM_IDLE=1
Const CHEESEANIM_MOVE=2
Const CHEESEANIM_WAIT=3
Const CHEESEANIM_ATTACK=4
Const CHEESEANIM_SPIN=5
Const CHEESEANIM_VICTORY=6
Const CHEESEANIM_VICTORYLOOP=7
Const CHEESEANIM_SHIELD=8
Const CHEESEANIM_HURT=9
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tCheese
		Field Position.tVector
		Field Entity
		Field Mesh
		Field Animation
		Field PreviousAnimation
		Field Channel_Chao
		Field targetp.tPlayer
		Field Emo.tChaoEmo
		Field Particle.tParticleTemplate
		Field ShadowCircle
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
Function Object_Cheese_Create.tCheese()
	
		ch.tCheese = New tCheese

		ch\Position = New tVector
		
		ch\Entity = CreatePivot()
		LoadCharacterMesh(CHAR_CHE)
		ch\Mesh=CopyEntity(CharacterMesh, Game\Stage\Root)
		DeleteCharacterMesh()
		EntityType(ch\Entity,COLLISION_OBJECT2_GOTHRU)

		ch\Emo = Object_ChaoEmo_Create.tChaoEmo(ch\Mesh,CHAOSIDE_NEUTRAL,True)

		TranslateEntity ch\Entity, Game\Gameplay\CheckX#,Game\Gameplay\CheckY#+7,Game\Gameplay\CheckZ#

		If Menu\Settings\Shadows#>0 Then ch\ShadowCircle = Init_CircleShadow(ch\Entity, ch\Mesh, 1.5)

		ch\Particle = ParticleTemplate_Create.tParticleTemplate()
		
		Return ch
		
	End Function
	
	; =========================================================================================================

Function Object_Cheese_Update(ch.tCheese, d.tDeltaTime)

		Select Menu\Members
			Case 1:
				If pp(1)\Character=CHAR_CRE Then
					ch\targetp=pp(1)
				Else
					ch\targetp=pp(1)
				EndIf
			Case 2:
				If pp(1)\Character=CHAR_CRE Then
					ch\targetp=pp(1)
				ElseIf pp(2)\Character=CHAR_CRE Then
					ch\targetp=pp(2)
				Else
					ch\targetp=pp(1)
				EndIf
			Case 3:
				If pp(1)\Character=CHAR_CRE Then
					ch\targetp=pp(1)
				ElseIf pp(2)\Character=CHAR_CRE Then
					ch\targetp=pp(2)
				ElseIf pp(3)\Character=CHAR_CRE Then
					ch\targetp=pp(3)
				Else
					ch\targetp=pp(1)
				EndIf
		End Select

		If ch\targetp\Character=CHAR_CRE And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
			ShowEntity(ch\Mesh)
			Object_Cheese_Update_Real(ch, ch\targetp, d)
			If Menu\Settings\Shadows#>0 Then Update_CircleShadow(ch\ShadowCircle, ch\Mesh, cam\Entity, 0)
		Else
			HideEntity(ch\Mesh)
			If Menu\Settings\Shadows#>0 Then Update_CircleShadow(ch\ShadowCircle, ch\Mesh, cam\Entity, 1)
		EndIf
		
	End Function

Function Object_Cheese_Update_Real(ch.tCheese, p.tPlayer, d.tDeltaTime)

		; Update position
		ch\Position\x# = EntityX(ch\Entity)
		ch\Position\y# = EntityY(ch\Entity)
		ch\Position\z# = EntityZ(ch\Entity)

		; Place mesh
		PositionEntity ch\Mesh, (ch\Position\x#), (ch\Position\y#), (ch\Position\z#)
		If Game\Victory=0 Then RotateEntity ch\Mesh,0,(DeltaYaw#(p\Objects\Entity,ch\Mesh) - 180),0 Else RotateEntity ch\Mesh,0,p\Animation\Direction#-180,0

		; Animate
		If ch\PreviousAnimation<>ch\Animation Then
			Select ch\Animation
				Case CHEESEANIM_SHIELD
					Animate (ch\Mesh,1,0.4,ch\Animation,10)
				Case CHEESEANIM_SPIN
					Animate (ch\Mesh,1,0.7,ch\Animation,10)
				Case CHEESEANIM_WAIT
					Animate (ch\Mesh,1,0.35,ch\Animation,10)
				Case CHEESEANIM_ATTACK
					Animate (ch\Mesh,1,0.5,ch\Animation,10)
				Case CHEESEANIM_VICTORY
					Animate (ch\Mesh,3,0.5,ch\Animation,10)
				Default: Animate (ch\Mesh,1,0.6,ch\Animation,10)
			End Select
			ch\PreviousAnimation=ch\Animation
		EndIf

		; Decide animation
		If Game\CheeseTimer>0 And (Not(p\Flags\HomingTarget\x#=99999 And p\Flags\HomingTarget\y#=99999 And p\Flags\HomingTarget\z#=99999)) Then
			ch\Animation=CHEESEANIM_ATTACK : ch\Emo\Emotion=CHAOEMO_default
		ElseIf p\Action=ACTION_THROW Then
			If p\ThrowType=1 Then 
				ch\Animation=CHEESEANIM_ATTACK : ch\Emo\Emotion=CHAOEMO_angry
			Else
				ch\Animation=CHEESEANIM_SPIN : ch\Emo\Emotion=CHAOEMO_angry
			EndIf 
		ElseIf p\Action=ACTION_HURT Then
			ch\Animation=CHEESEANIM_HURT : ch\Emo\Emotion=CHAOEMO_confused
		ElseIf p\Action=ACTION_DIE Then
			ch\Animation=CHEESEANIM_HURT : ch\Emo\Emotion=CHAOEMO_surprised
		ElseIf p\Action=ACTION_VICTORY Then
			If p\Motion\Ground=True Then
				If p\Animation\VictoryStage=1 Then
					ch\Animation=CHEESEANIM_VICTORYLOOP
				Else
					ch\Animation=CHEESEANIM_VICTORY
				EndIf
			Else
				ch\Animation=CHEESEANIM_SPIN
			EndIf 
		Else
			If p\CheeseShieldTimer>0 Then 
				ch\Animation=CHEESEANIM_SHIELD
			Else
				If p\SpeedLength#>0.5 Then
					ch\Animation=CHEESEANIM_MOVE : ch\Emo\Emotion=CHAOEMO_default
				Else
					If p\Animation\IdleType=1 Then
						ch\Animation=CHEESEANIM_WAIT : ch\Emo\Emotion=CHAOEMO_default
					Else
						ch\Animation=CHEESEANIM_IDLE : ch\Emo\Emotion=CHAOEMO_default
					EndIf
				EndIf
			EndIf 
		EndIf

		; Movement
		If Game\CheeseTimer>0  And (Not(p\Flags\HomingTarget\x#=99999 And p\Flags\HomingTarget\y#=99999 And p\Flags\HomingTarget\z#=99999)) Then
			ParticleTemplate_Call(ch\Particle, PARTICLE_CHAO_CHEESE, ch\Entity)
			PositionEntity(p\Flags\HomingMesh, p\Flags\HomingTarget\x#, p\Flags\HomingTarget\y#+0.3, p\Flags\HomingTarget\z#)
			ex# = p\Flags\HomingTarget\x# - ch\Position\x#
			ey# = (p\Flags\HomingTarget\y#+3) - ch\Position\y#
			ez# = p\Flags\HomingTarget\z# - ch\Position\z#
			AlignToVector(ch\Entity, ex#, ey#, ez#, 2, .925)
			AlignToVector(ch\Mesh, ex#, ey#, ez#, 2, .925)
			TurnEntity ch\Mesh, -90, 0, 0
			If EntityDistance(ch\Entity,p\Flags\HomingMesh)>10 Then
				MoveEntity(ch\Entity, 0, 3.2*d\Delta, 0)
			Else
				MoveEntity(ch\Entity, 0, (EntityDistance(ch\Entity,p\Flags\HomingMesh)/10)*3.2*d\Delta, 0)
			EndIf
			p\Flags\HomingTarget\x#=99999
			p\Flags\HomingTarget\y#=99999
			p\Flags\HomingTarget\z#=99999
			If (Not(ChannelPlaying(ch\Channel_Chao))) Then ch\Channel_Chao=EmitSmartSound(Sound_Chao,ch\Entity)
		Else
			If p\CheeseShieldTimer>0 Or p\Action=ACTION_VICTORY Then 
				PositionEntity ch\Mesh, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z# : EntityType(ch\Entity,COLLISION_OBJECT2_GOTHRU)
			Else
			PointEntity ch\Entity, p\Objects\Cheese
			If EntityDistance(p\Objects\Cheese, ch\Entity)>=150 Then
				EntityType(ch\Entity,COLLISION_NONE) : PositionEntity ch\Entity, p\Objects\Position\x#, p\Objects\Position\y#+7, p\Objects\Position\z#
				PositionEntity ch\Mesh, p\Objects\Position\x#, p\Objects\Position\y#+7, p\Objects\Position\z# : EntityType(ch\Entity,COLLISION_OBJECT2_GOTHRU)
			Else
				MoveEntity ch\Entity,0,0,0.15*p\Physics\UNDERWATERTRIGGER#*EntityDistance(p\Objects\Cheese, ch\Entity)*d\Delta
			EndIf
			If (ChannelPlaying(ch\Channel_Chao)) Then StopChannel(ch\Channel_Chao)
			EndIf 
		EndIf

	End Function