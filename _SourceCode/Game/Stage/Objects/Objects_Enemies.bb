Function Object_CheckEnemyHitBox(o.tObject,p.tPlayer)
	If o\Enemy\WasJustAttacked>0 And o\Enemy\IsBoss=0 And (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) Then
		If Input\Pressed\ActionJump Or Input\Pressed\ActionRoll Or Input\Pressed\ActionSkill1 Or Input\Pressed\ActionSkill2 Or Input\Pressed\ActionSkill3 Or Input\Pressed\ActionDrift Then o\Enemy\WasJustAttacked=0
	EndIf
	
	If o\Enemy\WasJustAttacked>0 Or (o\Enemy\MayGetAttacked=False And o\Enemy\IsBoss=1) Then o\AttackDetectRestrict=True Else o\AttackDetectRestrict=False

	If ((Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*(1+p\ScaleFactor#/4)+2*p\Flags\Stomping+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#*(1+p\ScaleFactor#/4)+1*p\Flags\Stomping+(p\SpeedLength#/3.0)) And (p\Objects\Position\y#>(o\Position\y#-1.2-(2+(p\SpeedLength#/3.0))) Or o\Enemy\FlyEnemyType) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*(1+p\ScaleFactor#/4)+2*p\Flags\Stomping+(p\SpeedLength#/3.0))) And (Not(o\FrozenStunTimer>0)) Then
		i=False
		Select o\ObjType
			Case OBJTYPE_BEETLESPRING: If (p\Objects\Position\y#-EntityY(o\Entity))>2 Or p\Action=ACTION_STOMP Then i=True
			Default:
				If Game\Invinc=0 And p\HasVehicle=0 Then
					Select o\ObjType
						Case OBJTYPE_CRAWL: If o\Anim=3 Or o\Anim=4 Then i=True
						Case OBJTYPE_MUSHMEANIE,OBJTYPE_SHEEP: If o\Enemy\AttackMode=0 Or o\Enemy\WaitTimer2>0 Then i=True
						Case  OBJTYPE_ROLLER: If o\Anim=3 Then i=True
					End Select
				EndIf
		End Select
		If i Then
			Object_Enemy_SpecialBehaviour(o,p)
		Else
			enemyhit=False
			If (p\Flags\Attacking And (o\Enemy\MayGetAttacked Or p\HurtTimer>0)) Or Game\Invinc=1 Then enemyhit=True
			If (o\ObjType=OBJTYPE_GRABBER Or o\ObjType=OBJTYPE_KLAGEN) And (p\Action=ACTION_JUMP Or p\Action=ACTION_HOP) And p\Objects\Position\y#<(o\Position\y#-0.5) Then enemyhit=False

			If enemyhit Then
				If o\Enemy\IsBoss=0 Or o\Enemy\BossMode<5 Then
					o\Hit=True
					ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
					If (Not(o\ObjType=OBJTYPE_BOMBIE)) Then p\DontGetHurtTimer=0.25*secs#
				EndIf
			ElseIf p\Motion\Ground Or (p\Objects\Position\y#>o\Position\y#) Then
				If (o\ObjType=OBJTYPE_GRABBER Or o\ObjType=OBJTYPE_KLAGEN Or o\ObjType=OBJTYPE_ACHAOSBLOB Or o\ObjType=OBJTYPE_BOO) And (Not(p\WasGrabbedTimer>0 Or p\DontGetHurtTimer>0)) And o\Psychoed=0 And o\Rubied=0 And Game\Invinc=0 And p\Invisibility=0 And (Not(p\Action=ACTION_GRABBED Or p\Action=ACTION_HURT Or p\Action=ACTION_DIE Or p\Action=ACTION_SKYDIVE)) And (Not(Game\ControlLock>0)) And (Not(Game\RunLock>0)) Then
					EntityType(cam\Entity, COLLISION_NONE)
					EntityType(p\Objects\Entity, COLLISION_NONE)
					o\Enemy\AttackTimer=3*secs#
					Select o\ObjType
						Case OBJTYPE_ACHAOSBLOB: EmitSmartSound(Sound_Bounce,p\Objects\Entity)
						Case OBJTYPE_BOO: EmitSmartSound(Sound_GhostGrab,p\Objects\Entity)
					End Select
					p\Action=ACTION_GRABBED
					Player_SetSpeed(p,0)
					Player_SetSpeedY(p,0)
				Else
					If (Not(p\Action=ACTION_GRABBED Or p\WasGrabbedTimer>0)) And o\Psychoed=0 And o\Rubied=0 Then
						If (Not(p\HurtTimer>0)) Then
							Select o\ObjType
								Case OBJTYPE_RHINOSPIKES:
									If p\Objects\Position\y#>o\Position\y#+3 Then EmitSmartSound(Sound_Spikes,o\Entity)
								Case OBJTYPE_SPIKES:
									If p\Objects\Position\y#>o\Position\y#+3 And o\Enemy\AttackMode=0 Then EmitSmartSound(Sound_Spikes,o\Entity)
								Case OBJTYPE_ASTERON,OBJTYPE_BUBBLSSPIKES:
									If o\Enemy\AttackMode=0 Then EmitSmartSound(Sound_Spikes,o\Entity)
								Case OBJTYPE_FLAPPERNEEDLE:
									If o\Anim=2 Then EmitSmartSound(Sound_Spikes,o\Entity)
							End Select
						EndIf
						If o\PsychoedThrown=False And (Not(o\Enemy\MayNotHurtTimer>0)) Then
							Player_Hit(p)
							If o\Enemy\IsBoss=1 Then
								If (Not(Rand(1,1+2)=1)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Win[Rand(1,2)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
							EndIf
						EndIf
					EndIf
					o\Hit=False
				EndIf
			Else
				o\Hit=False
			EndIf
		EndIf
	Else
		o\Hit=False
	EndIf
End Function

Function BossType(char)
	Select(Menu\Character[1])
		Case CHAR_BLA,CHAR_SIL,CHAR_MAR:
			Return 1
	End Select
	For i=1 To 3
		If Menu\Members>=i Then
		Select Menu\Character[i]
			Case CHAR_EGG,CHAR_MET,CHAR_TDL,CHAR_MKN,CHAR_BET,CHAR_MT3,CHAR_EGR,CHAR_INF:
				Return 1
		End Select
		EndIf
	Next
	Return 0
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_PlayRobotDestroySound(o.tObject,defeated=True)
	If Object_IsEnemyRobot(o\ObjType) Then
		If defeated Then
			Select(Rand(1,3))
				Case 1: EmitSmartSound(Sound_Robot1,o\Entity)
				Case 2: EmitSmartSound(Sound_Robot2,o\Entity)
				Case 3: EmitSmartSound(Sound_Robot3,o\Entity)
			End Select
			Select o\ObjType
				Case OBJTYPE_E1000: EmitSmartSound(Sound_RobotPoof,o\Entity)
			End Select
		Else
			EmitSmartSound(Sound_EggmanHurt,o\Entity)
		EndIf
	Else
		Select o\ObjType
			Case OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST: EmitSmartSound(Sound_GhostVanish,o\Entity)
			Default: EmitSmartSound(Sound_MonsterDamage,o\Entity)
		End Select
		If defeated Then
			Select o\ObjType
				Case OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER: EmitSmartSound(Sound_MonsterDead,o\Entity)
				Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING: EmitSmartSound(Sound_MonsterDeadAlien,o\Entity)
				Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
					Select(Rand(1,4))
						Case 1: EmitSmartSound(Sound_SoldierHurt1,o\Entity)
						Case 2: EmitSmartSound(Sound_SoldierHurt2,o\Entity)
						Case 3: EmitSmartSound(Sound_SoldierHurt3,o\Entity)
					End Select
				Case OBJTYPE_SHEEP:
					Select(Rand(1,3))
						Case 1: EmitSmartSound(Sound_Sheep1,o\Entity)
						Case 2: EmitSmartSound(Sound_Sheep2,o\Entity)
					End Select
			End Select
		EndIf
	EndIf
End Function

Function Object_PlayRobotSteps(o.tObject,firstframe,otherframe)
	If (Not(firstframe=0 And otherframe=0)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyStep))) Then
		If o\Enemy\Frame=firstframe Or o\Enemy\Frame=otherframe Then
			Select o\ObjType
				Case OBJTYPE_BITER,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_SHEEP:
					Select(Rand(1,5))
						Case 1: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep1,o\Entity)
						Case 2: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep2,o\Entity)
						Case 3: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep3,o\Entity)
						Case 4: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep4,o\Entity)
						Case 5: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep5,o\Entity)
					End Select
					Select o\ObjType
						Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
							Select(Rand(1,6))
								Case 1: o\Enemy\Channel_EnemyStep2=EmitSmartSound(Sound_GroundStep1Soldier,o\Entity)
								Case 2: o\Enemy\Channel_EnemyStep2=EmitSmartSound(Sound_GroundStep2Soldier,o\Entity)
								Case 3: o\Enemy\Channel_EnemyStep2=EmitSmartSound(Sound_GroundStep3Soldier,o\Entity)
								Case 4: o\Enemy\Channel_EnemyStep2=EmitSmartSound(Sound_GroundStep4Soldier,o\Entity)
								Case 5: o\Enemy\Channel_EnemyStep2=EmitSmartSound(Sound_GroundStep5Soldier,o\Entity)
								Case 6: o\Enemy\Channel_EnemyStep2=EmitSmartSound(Sound_GroundStep6Soldier,o\Entity)
							End Select
					End Select
				Default:
					Select(Rand(1,5))
						Case 1: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep1Metal,o\Entity)
						Case 2: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep2Metal,o\Entity)
						Case 3: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep3Metal,o\Entity)
						Case 4: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep4Metal,o\Entity)
						Case 5: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep5Metal,o\Entity)
					End Select
			End Select
			If o\Enemy\Underwater=1 Then
				Select(Rand(1,5))
					Case 1: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep1Water,o\Entity)
					Case 2: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep2Water,o\Entity)
					Case 3: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep3Water,o\Entity)
					Case 4: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep4Water,o\Entity)
					Case 5: o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_GroundStep5Water,o\Entity)
				End Select
				Object_NonStepWaterSplash(o,firstframe,otherframe)
			EndIf
		EndIf
	Else
		If o\Enemy\Underwater=1 Then
			ParticleTemplate_Call(o\Enemy\WaterParticle, PARTICLE_OBJECT_WATERSPLASH, o\Entity)
			If (Not(ChannelPlaying(o\Enemy\Channel_EnemySwim))) And o\InView Then o\Enemy\Channel_EnemySwim=EmitSmartSound(Sound_EnemySwim,o\Entity)
		EndIf
	EndIf
End Function

Function Object_NonStepWaterSplash(o.tObject,firstframe,otherframe)
	If (o\Enemy\Frame=firstframe Or o\Enemy\Frame=otherframe) Then ParticleTemplate_Call(o\Enemy\WaterParticle, PARTICLE_OBJECT_WATERSPLASH, o\Entity)
End Function

Function Object_IsEnemyRobot(objtype)
	Select objtype
		Case OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_SHEEP,OBJTYPE_DOOMSEYE:
			Return False
		Default:
			Return True
	End Select
End Function

Function Object_IsActualEnemy(objtype)
	Select objtype
		Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
			Return False
		Default:
			Return True
	End Select
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_AnimateEnemy(o.tObject)
	If Not(o\PreviousAnim=o\Anim) Then
		Select o\ObjType
			Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_SPRINKLR:
				Animate o\Entity,1,0.6,o\Anim,10
			Case OBJTYPE_SPINY,OBJTYPE_LEECH,OBJTYPE_SHEEP,OBJTYPE_SPLATS:
				Animate o\Entity,1,0.3,o\Anim,10
			Case OBJTYPE_CATERKILLER,OBJTYPE_BALKIRY,OBJTYPE_MADMOLE:
				If o\Anim=3 Then Animate o\Entity,1,0.3,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_FIGHTER,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR:
				If o\Anim=2 Then Animate o\Entity,1,0.3,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_BOOSCARE:
				If o\Anim=2 Then Animate o\Entity,1,0.25,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_BOMBIE:
				If o\Anim=2 Then Animate o\Entity,1,0.55,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_STEELION,OBJTYPE_CRAWL,OBJTYPE_E1000:
				If o\Anim=2 Then Animate o\Entity,1,0.35,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_DRAGONFLY:
				Animate o\Entity,1,0.25,o\Anim,10
			Case OBJTYPE_OCTUS:
				If o\Anim=3 Or o\Anim=6 Then Animate o\Entity,1,0.1,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_ZOOMER:
				If o\Anim=2 Then Animate o\Entity,1,0.275,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_BITER,OBJTYPE_CRAWLER:
				If o\Anim=2 Or o\Anim=3 Then Animate o\Entity,1,0.35,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_TAKER:
				If o\Anim=1 Or o\Anim=3 Then Animate o\Entity,1,0.35,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2:
				If o\Anim=3 Then
					Animate o\Entity,1,0.225,o\Anim,10
				Else
					If Not(o\Anim=1 Or o\Anim=4 Or o\Anim=7) Then Animate o\Entity,1,0.3,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
				EndIf
			Case OBJTYPE_OAKSWORD:
				If o\Anim=4 Then
					Animate o\Entity,1,0.225,o\Anim,10
				Else
					If Not(o\Anim=1 Or o\Anim=2) Then Animate o\Entity,1,0.3,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
				EndIf
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
				If o\Anim=1 Then
					Animate o\Entity,1,0.075,o\Anim,10
				Else
					If o\Anim=3 Then Animate o\Entity,1,0.45,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
				EndIf
			Case OBJTYPE_WING:
				Animate o\Entity,1,0.2,o\Anim,10
			Case OBJTYPE_ROLLER:
				If o\Anim=3 Then
					Animate o\Entity,1,0.8,o\Anim,10
				ElseIf o\Anim=2 Then
					Animate o\Entity,1,0.45,o\Anim,10
				Else
					Animate o\Entity,1,0.15,o\Anim,10
				EndIf
			Case OBJTYPE_MANTIS:
				If o\Anim=2 Then Animate o\Entity,1,0.85,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_SNOWY:
				If o\Anim=3 Or o\Anim=4 Then Animate o\Entity,1,0.625,o\Anim,10 Else Animate o\Entity,1,0.3,o\Anim,10
			Case OBJTYPE_BOSSBETA:
				Select o\Anim
					Case 3,6,8:
						Animate o\Entity,3,0.3,o\Anim,10
					Case 2:
						Animate o\Entity,3,0.15,o\Anim,10
					Default:
						Animate o\Entity,1,0.15,o\Anim,10
				End Select
			Case OBJTYPE_BOSSMECHA:
				If o\Anim=3 Then
					Animate o\Entity,1,0.45,o\Anim,10 : Animate o\Entity2,1,0.45,o\Anim,10
				Else
					Animate o\Entity,1,0.15,o\Anim,10 : Animate o\Entity2,1,0.15,o\Anim,10
				EndIf
			Case OBJTYPE_DOOMSEYE:
				Animate o\Entity,1,0.075,o\Anim,10
			Case OBJTYPE_HAMMERHAMMER:
				If o\Anim=6 Then Animate o\Entity,1,0.275,o\Anim,10 Else Animate o\Entity,1,0.15,o\Anim,10
			Case OBJTYPE_PAWNGUN
				If o\EggpawnShootTimer>0 Then 
					Animate o\Entity,1,0.15,6,10
				Else
					Animate o\Entity,1,0.15,o\Anim,10
				EndIf 
			Case OBJTYPE_GUNNER
				Select o\Anim
					Case 1 : Animate o\Entity,1,0.15,o\Anim,10
					Case 2 : Animate o\Entity,1,0.25,o\Anim,10
					Case 3 : Animate o\Entity,1,0.05,o\Anim,10
				End Select 
			Default:
				Animate o\Entity,1,0.15,o\Anim,10
				
		End Select
		o\PreviousAnim=o\Anim
	EndIf
End Function

Function Object_Enemy_GravityStuff(o.tObject,p.tPlayer,d.tDeltaTime)
	; Check if enemy is underwater
	If o\Position\y# < Game\Stage\Properties\WaterLevel Then o\Enemy\Underwater=1 Else o\Enemy\Underwater=0
	Select o\ObjType
		Case OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_STEELION:
			If o\Enemy\Underwater=1 Then o\Enemy\FlyEnemyType=True Else o\Enemy\FlyEnemyType=False
	End Select

	; Gravity stuff
	Select o\ObjType
		Case OBJTYPE_GUNNER,OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_MOTOBUG,OBJTYPE_CRABMEAT,OBJTYPE_KIKI,OBJTYPE_SPINY,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_ACHAOS,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_FIGHTER,OBJTYPE_CAMERON,OBJTYPE_KLAGEN,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_CRAWL,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_COPRACER,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_CLUCKOID,OBJTYPE_SHEEP,OBJTYPE_SNOWY,OBJTYPE_TOXO,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
			Object_EnforceGravity(o,d)
		Case OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_STEELION,OBJTYPE_OCTUS,OBJTYPE_MUSHMEANIE,OBJTYPE_BURROBOT,OBJTYPE_E1000,OBJTYPE_MANTIS,OBJTYPE_ROLLER,OBJTYPE_SPLATS:
			If Not o\Enemy\FlyEnemyType Then Object_EnforceGravity(o,d)
	End Select
	If o\Enemy\IsBoss=0 Then
		If (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) Then
			Object_EnforcePsychokinesis(o,p,d)
			Object_EnforceRubyGravity(o,p,d)
		EndIf
		Object_EnforceStun(o,p,d)
	EndIf
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_IsStun(o.tObject)
	If (Not(o\BubbleStunTimer>0)) And (Not(o\CurseStunTimer>0)) And (Not(o\FroggyStunTimer>0)) And (Not(o\FlowerStunTimer>0)) And (Not(o\FrozenStunTimer>0)) And (Not(o\WhirlwindStunTimer>0)) Then
		Return False
	Else
		Return True
	EndIf
End Function

Function Object_EnemyIsStun(o.tObject)
	If (pp(1)\Invisibility=0 Or o\ThisIsAnEnemyMissile) And (Not(Object_IsStun(o) Or Game\ChaosControlTimer>0)) And o\Psychoed=0 And o\Rubied=0 Then
		Return False
	Else
		Return True
	EndIf
End Function

Function Object_ReturnCanAttackShield(p.tPlayer,o.tObject)
	;If o\Enemy\Shield=0 Or p\Flags\StronglyAttacking Or o\BombHit Or o\CheeseHit Or Game\Invinc=1 Or o\Enemy\WasKilledByBombMonitor Then
		Return True
	;Else
	;	Return False
	;EndIf
End Function

Function Object_CreateEnemyPieces(o.tObject,dontspawnmesh=False)
	situation = o\Psychoed Or o\Rubied
	Select o\ObjType
		Case OBJTYPE_PAWNSHIELD,OBJTYPE_HUNTERSHIELD,OBJTYPE_HAMMERSHIELD:
			Object_Pieces_Create(True,o\ObjType,situation,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold,dontspawnmesh, o\Enemy\Shield)
		Case OBJTYPE_ORBINAUT,OBJTYPE_COPRACER:
			Object_Pieces_Create(True,o\ObjType,situation,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold,dontspawnmesh, o\Entity2)
		Case OBJTYPE_MUSHMEANIE,OBJTYPE_SHEEP:
			If o\Enemy\AttackMode=0 Then Object_Pieces_Create(False,-o\ObjType,0,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold)
			Object_Pieces_Create(True,o\ObjType,situation,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold,dontspawnmesh)
		Default:
			Object_Pieces_Create(True,o\ObjType,situation,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold,dontspawnmesh)
	End Select
	Select o\ObjType
		Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_GUNNER:
			Object_Pieces_Create(True,o\ObjType,situation,o\Position\x#,o\Position\y#+12,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold,True)
		Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA:
			Object_Pieces_Create(True,o\ObjType,situation,o\Position\x#,o\Position\y#+5,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold,True)
	End Select
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_Enemy_Create.tObject(x#, y#, z#, pitch#, yaw#, roll#, switchno, carnivalno)
		o.tObject = New tObject : o\ObjType = TempAttribute\ObjectNo : o\ID=TempAttribute\ObjectID
		o\ThisIsAnEnemy=True : o\Enemy = New tObject_Enemy : o\HasValuesetEnemy=True
		o\Switch = New tObject_Switch : o\HasValuesetSwitch=True
		If Object_IsActualEnemy(o\ObjType) Then
			Gameplay_AddTotalEnemies(1)
			o\Enemy\EnemyNo=Game\Gameplay\TotalEnemies
		Else
			switchno=0
			carnivalno=0
		EndIf

		o\Switch\s1 = Object_SwitchManager_Create.tSwitchManager(switchno, switchstatus)
		o\Switch\SwitchNo[0]=switchno
		o\Enemy\CarnivalNo=carnivalno

		Select o\ObjType
			Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_KLAGEN,OBJTYPE_EGGROBO,OBJTYPE_MOTOBUG,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_ANTON,OBJTYPE_STEELION,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE,OBJTYPE_WITCH1,OBJTYPE_WITCH2: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,13,6.5)
			Case OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,7.5,7,7.5)
			Case OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,9.5,11.5,9.5)
			Case OBJTYPE_CATERKILLER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,7.5,13,7.5)
			Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_AQUIS,OBJTYPE_BOMBIE,OBJTYPE_NEWTRON,OBJTYPE_PENGUINATOR,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_SPIKES,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BURROBOT,OBJTYPE_CRAWL,OBJTYPE_MADMOLE,OBJTYPE_OCTUS,OBJTYPE_TAKER,OBJTYPE_DRAGONFLY,OBJTYPE_SPLATS: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,8,6.5)
			Case OBJTYPE_CRABMEAT: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8.5,13,8.5)
			Case OBJTYPE_SPINY: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8,11,8)
			Case OBJTYPE_GRABBER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,11.5,12,11.5)
			Case OBJTYPE_KIKI,OBJTYPE_ASTERON,OBJTYPE_PATABATA,OBJTYPE_TECHNOSQU,OBJTYPE_LEECH: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,7,6.5)
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_GUNNER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,26,6.5)
			Case OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8,8,8)
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_BITER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8.5,16,8.5)
			Case OBJTYPE_AEROC: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8.5,9,8.5)
			Case OBJTYPE_CHASER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8.5,10,8.5)
			Case OBJTYPE_FIGHTER,OBJTYPE_E1000: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,7.5,16,7.5)
			Case OBJTYPE_CAMERON: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,10,15,10)
			Case OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_BALLHOG,OBJTYPE_RHINOTANK,OBJTYPE_MANTIS,OBJTYPE_NEBULA: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,10,6.5)
			Case OBJTYPE_ACHAOS,OBJTYPE_ACHAOSBLOB: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,7.5,14.5,7.5)
			Case OBJTYPE_ORBINAUT: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,3.5,4,3.5)
			Case OBJTYPE_BALKIRY,OBJTYPE_MANTA: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,8,5,8)
			Case OBJTYPE_MUSHMEANIE: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,5.5,4,5.5)
			Case OBJTYPE_ZOOMER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,5,6.5,5)
			Case OBJTYPE_CRAWLER,OBJTYPE_OAKSWORD: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,18,6.5)
			Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,15,6.5)
			Case OBJTYPE_WING: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,7,6.5)
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,5.5,15,5.5)
			Case OBJTYPE_CATAKILLER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,4.5,4.5,4.5)
			Case OBJTYPE_CLUCKOID: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,4.5,15.25,4.5)
			Case OBJTYPE_SHEEP,OBJTYPE_GHOST: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,6.5,11,6.5)
			Case OBJTYPE_SNOWY: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,10.5,20,10.5)
			Case OBJTYPE_TOXO: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,3.5,10.5,3.5)
			Case OBJTYPE_ROLLER: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,5,10,5)
			Case OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,14.25,21,14.25)
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,10,8,10) : o\Enemy\IsBoss=1
			Case OBJTYPE_BOSSMECHA: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,5,5.5,5) : o\Enemy\IsBoss=1
			Case OBJTYPE_FCANNON1: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,16.5,25,16.5)
			Case OBJTYPE_FCANNON2: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,15,39,15)
			Case OBJTYPE_FCANNON3: Object_CreateHitBox(HITBOXTYPE_ENEMY,o,21.5,17.5,21.5)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)
		Object_Acquire_Speed(o,0,-1,0)

		Select o\ObjType
			Case OBJTYPE_PAWNSHIELD,OBJTYPE_HUNTERSHIELD,OBJTYPE_HAMMERSHIELD: o\Enemy\InitialShield=1
		End Select

		Select o\ObjType
			Case OBJTYPE_PAWN: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Pawn)), Game\Stage\Root)
			Case OBJTYPE_PAWNSHIELD: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Pawn)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_PawnShield)
			Case OBJTYPE_PAWNGUN: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Pawn)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_PawnGun)
			Case OBJTYPE_PAWNSWORD: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Pawn)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_PawnSword)
			Case OBJTYPE_FLAPPER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Flapper)), Game\Stage\Root)
			Case OBJTYPE_FLAPPERGUN: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_FlapperGun)), Game\Stage\Root)
			Case OBJTYPE_FLAPPERBOMB: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_FlapperBomb)), Game\Stage\Root)
			Case OBJTYPE_FLAPPERNEEDLE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_FlapperNeedle)), Game\Stage\Root)
			Case OBJTYPE_SPINA: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Spina)), Game\Stage\Root)
			Case OBJTYPE_SPANA: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Spana)), Game\Stage\Root)
			Case OBJTYPE_SPONA: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Spona)), Game\Stage\Root)
			Case OBJTYPE_MOTOBUG: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Motobug)), Game\Stage\Root)
			Case OBJTYPE_CATERKILLER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Caterkiller)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_CaterkillerBody) : CheckLoadSmartEntity(Mesh_Enemy_CaterkillerBase)
			Case OBJTYPE_BUZZBOMBER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BuzzBomber)), Game\Stage\Root) : EntityBlend o\Entity, 1
			Case OBJTYPE_BUZZER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Buzzer)), Game\Stage\Root) : EntityBlend o\Entity, 1
			Case OBJTYPE_CHOPPER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Chopper)), Game\Stage\Root)
			Case OBJTYPE_CRABMEAT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Crabmeat)), Game\Stage\Root)
			Case OBJTYPE_JAWS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Jaws)), Game\Stage\Root)
			Case OBJTYPE_SPINY: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Spiny)), Game\Stage\Root)
			Case OBJTYPE_GRABBER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Grabber)), Game\Stage\Root)
			Case OBJTYPE_KIKI: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Kiki)), Game\Stage\Root)
			Case OBJTYPE_COP: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_CopSpeeder)), Game\Stage\Root)
			Case OBJTYPE_COPRACER: o\Entity2=Rand(1,6) : o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_CopRacer1+o\Entity2-1)), Game\Stage\Root)
			Case OBJTYPE_HUNTER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Hunter)), Game\Stage\Root)
			Case OBJTYPE_GUNNER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Gunner)), Game\Stage\Root)	
			Case OBJTYPE_HUNTERSHIELD: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Hunter)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_HunterShield)
			Case OBJTYPE_BEETLE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Beetle)), Game\Stage\Root)
			Case OBJTYPE_BEETLEMONO: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BeetleMono)), Game\Stage\Root)
			Case OBJTYPE_BEETLESPARK: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BeetleSpark)), Game\Stage\Root)
			Case OBJTYPE_BEETLESPRING: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BeetleSpring)), Game\Stage\Root)
			Case OBJTYPE_ACHAOS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_ArtificialChaos)), Game\Stage\Root)
			Case OBJTYPE_ACHAOSBLOB: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_ArtificialChaos2)), Game\Stage\Root)
			Case OBJTYPE_RHINO: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Rhino)), Game\Stage\Root)
			Case OBJTYPE_RHINOSPIKES: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_RhinoSpikes)), Game\Stage\Root)
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Hornet)), Game\Stage\Root)
			Case OBJTYPE_AEROC: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_AeroCannon)), Game\Stage\Root)
			Case OBJTYPE_CHASER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Chaser)), Game\Stage\Root)
			Case OBJTYPE_FIGHTER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Fighter)), Game\Stage\Root)
			Case OBJTYPE_EGGROBO: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_EggRobo)), Game\Stage\Root)
			Case OBJTYPE_CAMERON: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Cameron)), Game\Stage\Root)
			Case OBJTYPE_KLAGEN: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Klagen)), Game\Stage\Root)
			Case OBJTYPE_ORBINAUT: o\Entity2=Rand(1,6) : o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Orbinaut1+o\Entity2-1)), Game\Stage\Root)
			Case OBJTYPE_TYPHOON: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Typhoon1)), Game\Stage\Root)
			Case OBJTYPE_TYPHOONF: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Typhoon2)), Game\Stage\Root)
			Case OBJTYPE_ANTON: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Anton)), Game\Stage\Root)
			Case OBJTYPE_AQUIS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Aquis)), Game\Stage\Root)
			Case OBJTYPE_BOMBIE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Bombie)), Game\Stage\Root)
			Case OBJTYPE_NEWTRON: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Newtron)), Game\Stage\Root)
			Case OBJTYPE_PENGUINATOR: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Penguinator)), Game\Stage\Root)
			Case OBJTYPE_SLICER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Slicer)), Game\Stage\Root)
			Case OBJTYPE_SNAILB: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_SnailBlaster)), Game\Stage\Root)
			Case OBJTYPE_SPIKES: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Spikes)), Game\Stage\Root)
			Case OBJTYPE_ASTERON: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Asteron)), Game\Stage\Root)
			Case OBJTYPE_BATBOT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Batbot)), Game\Stage\Root) : EntityBlend o\Entity, 1
			Case OBJTYPE_BUBBLS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Bubbles)), Game\Stage\Root)
			Case OBJTYPE_BUBBLSSPIKES: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Bubbles2)), Game\Stage\Root)
			Case OBJTYPE_STEELION: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Steelion)), Game\Stage\Root)
			Case OBJTYPE_BOO,OBJTYPE_BOOSCARE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Boo)), Game\Stage\Root)
			Case OBJTYPE_GHOST: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Ghost)), Game\Stage\Root)
			Case OBJTYPE_BALKIRY: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Balkiry)), Game\Stage\Root)
			Case OBJTYPE_BURROBOT: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Burrobot)), Game\Stage\Root)
			Case OBJTYPE_CRAWL: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Crawl)), Game\Stage\Root)
			Case OBJTYPE_DRAGONFLY: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Dragonfly)), Game\Stage\Root)
			Case OBJTYPE_MADMOLE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Madmole)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_MadmoleBody) : CheckLoadSmartEntity(Mesh_Enemy_MadmoleBase)
			Case OBJTYPE_MANTA: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Manta)), Game\Stage\Root)
			Case OBJTYPE_MUSHMEANIE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Mushmeanie)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_MushmeanieBody) : CheckLoadSmartEntity(Mesh_Enemy_MushmeanieHat)
			Case OBJTYPE_OCTUS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Octus)), Game\Stage\Root)
			Case OBJTYPE_PATABATA: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Patabata)), Game\Stage\Root)
			Case OBJTYPE_ZOOMER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Zoomer)), Game\Stage\Root)
			Case OBJTYPE_BITER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Biter)), Game\Stage\Root)
			Case OBJTYPE_CRAWLER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Crawler)), Game\Stage\Root)
			Case OBJTYPE_TAKER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Taker)), Game\Stage\Root)
			Case OBJTYPE_E1000: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_E1000)), Game\Stage\Root) : EntityBlend o\Entity, 1
			Case OBJTYPE_BALLHOG: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BallHog)), Game\Stage\Root)
			Case OBJTYPE_RHINOTANK: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Rhinotank)), Game\Stage\Root)
			Case OBJTYPE_TECHNOSQU: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_TechnoSqueek)), Game\Stage\Root)
			Case OBJTYPE_WARRIOR: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BlackWarrior)), Game\Stage\Root)
			Case OBJTYPE_WARRIORGUN1: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BlackWarrior)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_BlackWarriorGun1)
			Case OBJTYPE_WARRIORGUN2: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BlackWarrior)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_BlackWarriorGun2)
			Case OBJTYPE_OAKSWORD: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BlackOak)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_BlackOakSword)
			Case OBJTYPE_LEECH: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BlackLeech)), Game\Stage\Root)
			Case OBJTYPE_WING: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_BlackWing)), Game\Stage\Root)
			Case OBJTYPE_SOLDIER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Soldier)), Game\Stage\Root)
			Case OBJTYPE_SOLDIERCAMO: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Soldier2)), Game\Stage\Root)
			Case OBJTYPE_CATAKILLER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_CatakillerJr)), Game\Stage\Root)
			Case OBJTYPE_CLUCKOID: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Cluckoid)), Game\Stage\Root)
			Case OBJTYPE_MANTIS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Mantis)), Game\Stage\Root)
			Case OBJTYPE_NEBULA: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Nebula)), Game\Stage\Root)
			Case OBJTYPE_ROLLER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Roller)), Game\Stage\Root)
			Case OBJTYPE_SHEEP: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Sheep)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_SheepFluff)
			Case OBJTYPE_SNOWY: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Snowy)), Game\Stage\Root)
			Case OBJTYPE_SPLATS: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Splats)), Game\Stage\Root)
			Case OBJTYPE_TOXO: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Toxomister)), Game\Stage\Root)
			Case OBJTYPE_SPRINKLR: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Sprinkler)), Game\Stage\Root)
			Case OBJTYPE_DOOMSEYE: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_DoomsEye)), Game\Stage\Root)
			Case OBJTYPE_HAMMER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Hammer)), Game\Stage\Root)
			Case OBJTYPE_HAMMERSHIELD: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Hammer)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_HammerShield)
			Case OBJTYPE_HAMMERHAMMER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Hammer)), Game\Stage\Root) : CheckLoadSmartEntity(Mesh_Enemy_HammerHammer)
			Case OBJTYPE_WITCH1: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Witch1)), Game\Stage\Root)
			Case OBJTYPE_WITCH2: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Witch2)), Game\Stage\Root)
			Case OBJTYPE_FCANNON1: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_FCannon1)), Game\Stage\Root)
			Case OBJTYPE_FCANNON2: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_FCannon2)), Game\Stage\Root)
			Case OBJTYPE_FCANNON3: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_FCannon3)), Game\Stage\Root)
			Case OBJTYPE_SEARCHER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Searcher)), Game\Stage\Root)
			Case OBJTYPE_EGGHUNTER: o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_EggHunter)), Game\Stage\Root)	
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN:
				Select BossType(Menu\Character[1])
					Case 1:
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Boss_EggMobileNega)), Game\Stage\Root)
						o\ExtraTexture = LoadTexture("Objects/Shields/EggMobileNegaShield.png",2+256) : o\HasExtraTexture=1
						o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_Boss_EggMobile_Shield)), Game\Stage\Root) : EntityTexture o\Entity3, o\ExtraTexture
						LoadCharacterMesh(CHAR_EGN,4,0,1)
						For i=1 To 5 : LoadGoodSound(Voice_EGG_Attack[i],1,"Voices/"+"egn"+"/attack"+i+".ogg",2) : Next
						For i=1 To 3 : LoadGoodSound(Voice_EGG_Lose[i],1,"Voices/"+"egn"+"/lose"+i+".ogg",2) : Next
						For i=1 To 2 : LoadGoodSound(Voice_EGG_Win[i],1,"Voices/"+"egn"+"/win"+i+".ogg",2) : Next
					Default:
						o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Boss_EggMobile)), Game\Stage\Root)
						o\ExtraTexture = LoadTexture("Objects/Shields/EggMobileShield.png",2+256) : o\HasExtraTexture=1
						o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_Boss_EggMobile_Shield)), Game\Stage\Root) : EntityTexture o\Entity3, o\ExtraTexture
						LoadCharacterMesh(CHAR_EGG,4,0,1)
						For i=1 To 5 : LoadGoodSound(Voice_EGG_Attack[i],1,"Voices/"+"egg"+"/attack"+i+".ogg",2) : Next
						For i=1 To 3 : LoadGoodSound(Voice_EGG_Lose[i],1,"Voices/"+"egg"+"/lose"+i+".ogg",2) : Next
						For i=1 To 2 : LoadGoodSound(Voice_EGG_Win[i],1,"Voices/"+"egg"+"/win"+i+".ogg",2) : Next
				End Select
				o\Entity2=CopyEntity(CharacterMesh, Game\Stage\Root)
				DeleteCharacterMesh()
				Animate(o\Entity2, 1, 0.0515, ANIMATION_JOG+1, 10)
			Case OBJTYPE_BOSSBETA:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Boss_Betamk2)), Game\Stage\Root)
				o\ExtraTexture = LoadTexture("Objects/Shields/EggMobileBetaShield.png",2+256) : o\HasExtraTexture=1
				o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_Boss_EggMobile_Shield)), Game\Stage\Root) : EntityTexture o\Entity3, o\ExtraTexture
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_Boss_BetaRainbow)), Game\Stage\Root) : EntityBlend o\Entity2, 1
			Case OBJTYPE_BOSSMECHA:
				o\Entity = CopyEntity(MESHES(SmartEntity(Mesh_Boss_MechaSonic)), Game\Stage\Root)
				o\Entity2 = CopyEntity(MESHES(SmartEntity(Mesh_Boss_MechaSonicS)), Game\Stage\Root)
				o\ExtraTexture = LoadTexture("Objects/Shields/EggMobileNegaShield.png",2+256) : o\HasExtraTexture=1
				o\Entity3 = CopyEntity(MESHES(SmartEntity(Mesh_Boss_EggMobile_Shield)), Game\Stage\Root) : EntityTexture o\Entity3, o\ExtraTexture
				ScaleEntity o\Entity3, 0.8, 0.8, 0.8
		End Select

		Select o\ObjType
			Case OBJTYPE_BURROBOT:
				o\ExtraTexture=LoadTexture("Objects\Enemies\burrobot_body2.png", 1+256) : o\HasExtraTexture=1
				ApplyMeshTextureLayer(o\Entity, "burrobot_body2.png", o\ExtraTexture)
			Case OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER:
				o\ExtraTexture=LoadTexture("Objects\Enemies\monsterfire.png", 1+256) : o\HasExtraTexture=1
				ApplyMeshTextureLayer(o\Entity, "monsterfire.png", o\ExtraTexture)
			Case OBJTYPE_RHINOTANK:
				o\ExtraTexture=LoadTexture("Objects\Enemies\rhinotank_body2.png", 1+256) : o\HasExtraTexture=1
				ApplyMeshTextureLayer(o\Entity, "rhinotank_body2.png", o\ExtraTexture)
		End Select

		If o\Enemy\IsBoss Then
			o\Entity4=CreatePivot()
			o\HasEntity4=True
		EndIf

		Select o\ObjType
			Case OBJTYPE_ACHAOSBLOB,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE: EntityRadius(o\Entity,o\HitBox\x#*0.5,6)
			Case OBJTYPE_DRAGONFLY: EntityRadius(o\Entity,o\HitBox\x#*0.5,3)
			Case OBJTYPE_ZOOMER: EntityRadius(o\Entity,o\HitBox\x#*0.5,3.5)
			Default: EntityRadius(o\Entity,o\HitBox\x#*0.5,2.20)
		End Select

		Select o\ObjType
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN:
				o\Enemy\Center=FindChild(o\Entity, "mobile")
			Case OBJTYPE_BEETLE,OBJTYPE_BEETLESPRING,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLEMONO:
				o\Enemy\Center=FindChild(o\Entity, "spine")
			Case OBJTYPE_JAWS,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				o\Enemy\Center=FindChild(o\Entity, "root")
			Default:
				o\Enemy\Center=FindChild(o\Entity, "hips")
		End Select

		o\Enemy\WaterParticle = ParticleTemplate_Create.tParticleTemplate()

		Select o\ObjType
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_MOTOBUG,OBJTYPE_EGGROBO,OBJTYPE_ORBINAUT,OBJTYPE_ANTON,OBJTYPE_E1000,OBJTYPE_RHINOTANK,OBJTYPE_BOSSBETA:
				o\Enemy\Jet1=FindChild(o\Entity, "jetL")
				o\Enemy\Jet2=FindChild(o\Entity, "jetR")
				o\Enemy\JetParticle1 = ParticleTemplate_Create.tParticleTemplate()
				o\Enemy\JetParticle2 = ParticleTemplate_Create.tParticleTemplate()
			Case OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_BOMBIE,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BALKIRY,OBJTYPE_ZOOMER,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSMECHA:
				o\Enemy\Jet1=FindChild(o\Entity, "jet")
				o\Enemy\JetParticle1 = ParticleTemplate_Create.tParticleTemplate()
			Case OBJTYPE_STEELION:
				o\Enemy\Jet1=FindChild(o\Entity, "mouth")
			Case OBJTYPE_DRAGONFLY:
				o\Enemy\Jet1=FindChild(o\Entity, "tail4")
				o\Enemy\Jet2=FindChild(o\Entity, "tail8")
			Case OBJTYPE_OAKSWORD:
				o\Enemy\Jet1=FindChild(o\Entity, "sword1")
				o\Enemy\Jet2=FindChild(o\Entity, "sword2")
			Case OBJTYPE_GUNNER
				o\Enemy\Gun=FindChild(o\Entity, "forearmL")
			Case OBJTYPE_CATAKILLER:
				o\Enemy\Jet1=FindChild(o\Entity, "hips")
				o\Enemy\Jet2=FindChild(o\Entity, "tail3")
			Case OBJTYPE_SNOWY,OBJTYPE_SLICER:
				o\Enemy\Jet1=FindChild(o\Entity, "handR")
				o\Enemy\Jet2=FindChild(o\Entity, "handL")
			Case OBJTYPE_HAMMERHAMMER:
				o\Enemy\Jet1=FindChild(o\Entity, "hammer1")
			Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2:
				o\Enemy\Jet1=FindChild(o\Entity, "missile")
			Case OBJTYPE_FCANNON3:
				o\Enemy\Jet1=FindChild(o\Entity, "missileR")
				o\Enemy\Jet2=FindChild(o\Entity, "missileL")
		End Select

		If Menu\Settings\Shadows#>0 Then o\Enemy\ShadowCircle = Init_CircleShadow(o\Entity, o\Entity, 1.5)

		EntityType(o\Entity,COLLISION_OBJECT_GOTHRU)

		Select o\ObjType
			Case OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_GRABBER,OBJTYPE_COP,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_ACHAOSBLOB,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_EGGROBO,OBJTYPE_ORBINAUT,OBJTYPE_ASTERON,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_AQUIS,OBJTYPE_BALKIRY,OBJTYPE_DRAGONFLY,OBJTYPE_MANTA,OBJTYPE_PATABATA,OBJTYPE_ZOOMER,OBJTYPE_TAKER,OBJTYPE_WING,OBJTYPE_NEBULA,OBJTYPE_CATAKILLER,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE:
				o\Enemy\FlyEnemyType=True
			Default:
				o\Enemy\FlyEnemyType=False
		End Select

		Select o\ObjType
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA: o\Enemy\InitialHealth=10
			Case OBJTYPE_SNOWY,OBJTYPE_CAMERON,OBJTYPE_OAKSWORD,OBJTYPE_WITCH1,OBJTYPE_WITCH2: o\Enemy\InitialHealth=3
			Case OBJTYPE_PAWNSHIELD,OBJTYPE_HUNTERSHIELD,OBJTYPE_STEELION,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE: o\Enemy\InitialHealth=2
			Case OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER: o\Enemy\InitialHealth=5
			Case OBJTYPE_HAMMERSHIELD: o\Enemy\InitialHealth=7
			Default: o\Enemy\InitialHealth=1
		End Select

		Return o
	End Function

	; =========================================================================================================

	Function Object_Enemy_DecideAppear(o.tObject, p.tPlayer)
		If Menu\Mission=MISSION_CARNIVAL# Then
			If o\Enemy\CarnivalNo=Game\CarnivalLevel Then
				If Game\CarnivalAppearTimer>0 Then
					ParticleTemplate_Call(o\Enemy\WaterParticle, PARTICLE_OBJECT_SPAWNSPARKS, o\Entity)
					o\Enemy\EnemyShallAppear=False
				Else
					Game\CurrentCarnivalTimer=2*secs#
					o\Enemy\EnemyShallAppear=True
				EndIf
			Else
				o\Enemy\EnemyShallAppear=False
			EndIf
		ElseIf o\Enemy\CarnivalNo=0 Then
			o\Enemy\EnemyShallAppear=True
		Else
			o\Enemy\EnemyShallAppear=False
		EndIf
	End Function

	Function Object_Enemy_AppearStuff(o.tObject)
		If o\Enemy\IsBoss=0 Then
			ShowEntity(o\Entity)
		Else
			Select o\ObjType
				Case OBJTYPE_BOSSMECHA:
					If o\Enemy\Shield Then
						ShowEntity(o\Entity2)
						HideEntity(o\Entity)
					Else
						ShowEntity(o\Entity)
						HideEntity(o\Entity2)
					EndIf
				Default:
					ShowEntity(o\Entity)
					Select o\ObjType
						Case OBJTYPE_BOSSBETA:
						Default: ShowEntity(o\Entity2)
					End Select
			End Select
		EndIf
	End Function

	Function Object_Enemy_Update(o.tObject, p.tPlayer, d.tDeltaTime)
		Object_Enemy_DecideAppear(o,p)

		If o\Enemy\EnemyShallAppear Then
			Object_Enemy_AppearStuff(o)
			Object_Enemy_Update_Real(o,p,d)
			If Menu\Settings\Shadows#>0 Then Update_CircleShadow(o\Enemy\ShadowCircle, o\Entity, cam\Entity, 0)
		Else
			HideEntity(o\Entity)
			If o\Enemy\IsBoss=1 Then
				Select o\ObjType
					Case OBJTYPE_BOSSBETA:
					Default: HideEntity(o\Entity2)
				End Select
			EndIf
			If Menu\Settings\Shadows#>0 Then Update_CircleShadow(o\Enemy\ShadowCircle, o\Entity, cam\Entity, 1)
		EndIf
	End Function

	Function Object_Enemy_Update_Real(o.tObject, p.tPlayer, d.tDeltaTime)

		; Manage switch
		If o\Switch\SwitchNo[0]>0 Then o\Switch\s1\Active=1

		; Fix homing position if enemy is to be hommed
		If o\Enemy\WillBeHomedTimer>0 Then
			p\Flags\HomingTarget\x# = o\Position\x#
			p\Flags\HomingTarget\y# = o\Position\y#
			p\Flags\HomingTarget\z# = o\Position\z#
		EndIf

		; Animate and update enemy frame
		If o\Enemy\IsBoss=0 Or (o\ObjType=OBJTYPE_BOSSBETA Or o\ObjType=OBJTYPE_BOSSMECHA) Then Object_AnimateEnemy(o) : o\Enemy\Frame = Int(AnimTime(o\Entity))

		; Update collision aligning
		Object_UpdateCollisions(o,o\Entity)

		; Update timer
		If o\Enemy\WasJustAttacked>0 Then o\Enemy\WasJustAttacked=o\Enemy\WasJustAttacked-timervalue#
		If o\Enemy\WillBeHomedTimer>0 Then o\Enemy\WillBeHomedTimer=o\Enemy\WillBeHomedTimer-timervalue#
		If o\EggpawnShootTimer>0 Then o\EggpawnShootTimer=o\EggpawnShootTimer-timervalue#

		; Move
		If (o\Psychoed=0 And o\Rubied=0 And (Not(p\LightDashRequestTimer>0))) Then Object_EnemyMovements(o,p,d)

		; Detect the player
		Select o\ObjType
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA:
				If EntityDistance(o\Entity,p\Objects\Entity)<300 Or o\Enemy\BossMode>=5 Then
					o\Enemy\InRange=True
				Else
					o\Enemy\InRange=False
				EndIf
			Default:
				Select o\ObjType
					Case OBJTYPE_HAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_HAMMERHAMMER:
						i=1
					Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
						i=2
					Default:
						i=0
				End Select
				If (Not(Object_EnemyIsStun(o))) And EntityDistance(o\Entity,p\Objects\Entity)<(110+15*i) And (Abs(p\Objects\Position\y# - o\Position\y#) < (40+5*i)) Then
					o\Enemy\InRange=True
				Else
					o\Enemy\InRange=False
				EndIf
		End Select
		If o\Enemy\MayNotHurtTimer>0 Then o\Enemy\MayNotHurtTimer=o\Enemy\MayNotHurtTimer-timervalue#

		; React to player
		If o\Enemy\InRange Then
			If o\Enemy\SearchSonic>0 Then o\Enemy\SearchSonic=o\Enemy\SearchSonic-timervalue#
			If o\Enemy\HelloSonic=0 Then
				Select o\ObjType
					Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD: EmitSmartSound(Sound_EnemyHello,o\Entity)
					Case OBJTYPE_BITER: EmitSmartSound(Sound_MonsterRoar1,o\Entity)
					Case OBJTYPE_CRAWLER: EmitSmartSound(Sound_MonsterRoar2,o\Entity)
					Case OBJTYPE_TAKER: EmitSmartSound(Sound_MonsterFind,o\Entity)
				End Select
				o\Enemy\HelloSonic=1
				o\Enemy\SeenSonic=2 : If Menu\Mission=MISSION_STEALTH# Then Player_Die(p,1)
				o\Enemy\SearchSonic=(4+Rand(0,6)/1.5)*secs#
			ElseIf o\Enemy\HelloSonic=1 Then
				If o\Enemy\SeenSonic=0 Then
					Select o\ObjType
						Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD: EmitSmartSound(Sound_EnemySeen,o\Entity)
						Case OBJTYPE_BITER: EmitSmartSound(Sound_MonsterRoar1,o\Entity)
						Case OBJTYPE_CRAWLER: EmitSmartSound(Sound_MonsterRoar2,o\Entity)
						Case OBJTYPE_TAKER: EmitSmartSound(Sound_MonsterFind,o\Entity)
					End Select
					o\Enemy\SeenSonic=1 : If Menu\Mission=MISSION_STEALTH# Then Player_Die(p,1)
				EndIf
				If Not(o\Enemy\SearchSonic>0) Then
					Select o\ObjType
						Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_HAMMER,OBJTYPE_HAMMERHAMMER,OBJTYPE_HAMMERSHIELD: EmitSmartSound(Sound_EnemySearch,o\Entity)
						Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
							Select(Rand(1,4))
								Case 1: EmitSmartSound(Sound_SoldierCharge1,o\Entity)
								Case 2: EmitSmartSound(Sound_SoldierCharge2,o\Entity)
								Case 3: EmitSmartSound(Sound_SoldierCharge3,o\Entity)
							End Select
						Case OBJTYPE_SHEEP:
							Select(Rand(1,3))
								Case 1: EmitSmartSound(Sound_Sheep1,o\Entity)
								Case 2: EmitSmartSound(Sound_Sheep2,o\Entity)
							End Select
					End Select
					o\Enemy\SearchSonic=(4.5+Rand(0,6)/1.5)*secs#
				EndIf
			EndIf
		Else
			o\Enemy\SeenSonic=0
		EndIf

		; Gravity, stun, psychokinesis
		Object_Enemy_GravityStuff(o,p,d)

		; Player collided with enemy
		If Game\Victory>0 And o\Enemy\InRange And o\Enemy\IsBoss=0 Then o\Enemy\SelfDestruct=True
		If (o\AttackDetectRestrict=False And (o\Hit Or o\CheeseHit Or o\BombHit)) Or o\Enemy\SelfDestruct Or o\Enemy\WasKilledByBombMonitor Then
			If Object_ReturnCanAttackShield(p,o) Then
			If o\Enemy\IsBoss=0 And o\Enemy\Health>1 And (Game\Victory<>0 Or (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE))) Then
				If o\Psychoed>0 Or o\Rubied>0 Or Game\Invinc Or Game\MachLock>0 Or o\Enemy\WasKilledByBombMonitor Or (o\BombHit And (o\BombHitType=BOMB_HOOKSHOT Or o\BombHitType=BOMB_BULLET3 Or (o\BombHitType=BOMB_ROCKET And o\Enemy\Health<=5))) Or p\HasVehicle>0 Or Game\Victory<>0 Then
					o\Enemy\Health=1
				EndIf
			EndIf
			If Game\BishopMagicTimer>0 Then
				Select o\ObjType
					Case OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
					Default:
						If Game\Invinc=0 Then o\Enemy\Health=o\Enemy\Health+1
				End Select
			EndIf
			
			;not one hit
			If o\Enemy\Health>1 Then
				
				If o\Enemy\IsBoss=1 Then
					If o\Hit And o\Enemy\WasKilledByBombMonitor=False Then Game\ControlLock=0.4*secs#
					o\Enemy\WasJustAttacked=5.31*secs#
					Select o\ObjType
						Case OBJTYPE_BOSSBETA:
							If o\Anim=9 Then o\Anim=1
						Case OBJTYPE_BOSSMECHA:
						Default:
							Animate(o\Entity, 3, 0.25, 1, 10)
					End Select
					o\Enemy\AttackMode2=0
				Else
					If (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) Then
						o\Enemy\WasJustAttacked=0.31*secs#
					Else
						o\Enemy\WasJustAttacked=2*secs#
					EndIf
					If o\Hit Then
						Select o\ObjType
							Case OBJTYPE_CAMERON: If p\Motion\Ground Then Player_SetSpeed(p,-1.575)
							Default: Player_SetSpeed(p,-1.575)
						End Select
						Game\ControlLock=0.2*secs#
					EndIf
					If Not(o\Enemy\Shield=0) Then
						Select o\ObjType
							Case OBJTYPE_PAWNSHIELD,OBJTYPE_HUNTERSHIELD: i=True
							Case OBJTYPE_HAMMERSHIELD: If o\Enemy\Health<=6 Then i=True Else i=False
						End Select
						If i Then
							Object_Pieces_Create(False,-o\ObjType,o\Psychoed,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold)
							o\Enemy\Shield=0
						EndIf
					EndIf
				EndIf
				o\GotAssignedBomb=False
				If (Not(o\ObjType=OBJTYPE_SPRINKLR Or o\ObjType=OBJTYPE_DOOMSEYE)) Then 
					If Game\ChaosSnapTimer>0 Then
						o\Enemy\Health=o\Enemy\Health-2
					Else
						o\Enemy\Health=o\Enemy\Health-1
					EndIf 
				EndIf 
				
				If o\Hit Then p\Flags\HomingLocked = False : p\Flags\Targeter=0
				If o\Enemy\Health=0 Then 
					Object_PlayRobotDestroySound(o)
					If Object_IsEnemyRobot(o\ObjType) Then Object_CreateEnemyPieces(o)
				Else
					Object_PlayRobotDestroySound(o,False)
					If Object_IsEnemyRobot(o\ObjType) Then Object_CreateEnemyPieces(o,True)
				EndIf 
			Else
				; Add to counter
				If o\Enemy\SelfDestruct=False Then
					Gameplay_AddScore(100+150*o\Enemy\Gold+40*p\EnemyComboCounter) : p\EnemyComboCounter=p\EnemyComboCounter+1 : p\EnemyComboTimer=1.6*secs#
					If Menu\Mission=MISSION_DECLINE# Then Game\DeclineTime=Game\DeclineTime+(2*p\EnemyComboCounter)*secs#
					Gameplay_AddGaugeEnergy(o\Enemy\InitialHealth*5)
				EndIf
				If Object_IsActualEnemy(o\ObjType) Then
					Gameplay_AddEnemies(1)
					If o\Enemy\Gold=1 Then Gameplay_AddGoldEnemies(1)
				EndIf
				
				
				
				; Boom!
				If Game\Victory=0 Then Object_PlayRobotDestroySound(o)

				;Release effect
				If Object_IsEnemyRobot(o\ObjType) Then
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BOMB, o\Entity)
				Else
					Select o\ObjType
						Case OBJTYPE_BITER,OBJTYPE_TAKER,OBJTYPE_CRAWLER: ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FLAMYBLOOD, o\Entity)
						Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_LEECH,OBJTYPE_WING: ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ALIENBLOOD, o\Entity)
					End Select
				EndIf
				Object_CreateEnemyPieces(o)

				;Deal bombs
				Select o\ObjType
					Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA:
						If o\Enemy\HasBossObj1 Then o\Enemy\BossObj1\Mode=0 : o\Enemy\HasBossObj1=False
						If o\Enemy\HasBossObj2 Then o\Enemy\BossObj2\Mode=0 : o\Enemy\HasBossObj2=False
						If o\Enemy\HasBossObj3 Then o\Enemy\BossObj3\Mode=0 : o\Enemy\HasBossObj3=False
						If o\Enemy\HasBossObj4 Then o\Enemy\BossObj4\Mode=0 : o\Enemy\HasBossObj4=False
					Case OBJTYPE_BOMBIE:
						o\Enemy\ShouldSpawnMissile=True
						Object_EnemyMovements_SpawnMissile(o,p)
				End Select

				;Enemy hitting consequences
				o\Enemy\Health=o\Enemy\Health-1
				If Game\Invinc=1 Then
					o\Enemy\WasJustAttacked=0
				Else
					If o\Enemy\IsBoss=0 Then o\Enemy\WasJustAttacked=0.31*secs# Else o\Enemy\WasJustAttacked=4.5*secs#
				EndIf

				If o\Enemy\IsBoss=0 Then
					;Spawn chaos drive or flicky
					If Menu\Mission=MISSION_FLICKY# Then
						If Rand(1,5)=1 And Game\Gameplay\Flickies<5 Then
							Object_Flicky_Create(o\Position\x#, o\Position\y#+4, o\Position\z#)
						Else
							Object_CreateDriveFromEnemy(o\ObjType, o\Position\x#, o\Position\y#+4, o\Position\z#)
						EndIf
					Else
						Object_CreateDriveFromEnemy(o\ObjType, o\Position\x#, o\Position\y#+4, o\Position\z#)
					EndIf

					; Spawn red ring
					If Object_ReturnCanAttackShield(p,o) And o\HasShard Then
						If o\Enemy\FlyEnemyType Then
							Object_SpewShard_Create.tObject(-o\Enemy\EnemyNo, o\Position\x#, o\Position\y#, o\Position\z#, Rnd(-0.4*1.1, 0.4*1.1), Rnd(0.6*1.1, 1.2*1.1), Rnd(-0.4*1.1, 0.4*1.1), 1)
						Else
							Object_SpewShard_Create.tObject(-o\Enemy\EnemyNo, o\Position\x#, o\Position\y#, o\Position\z#, Rnd(-0.4*1.1, 0.4*1.1), Rnd(0.6*1.1, 1.2*1.1), Rnd(-0.4*1.1, 0.4*1.1))
						EndIf
					EndIf
				Else
					Select o\ObjType
						Case OBJTYPE_BOSSBETA:
							If o\Anim=9 Then o\Anim=1
						Case OBJTYPE_BOSSMECHA:
						Default:
							Animate(o\Entity, 3, 0.5, 1, 10)
					End Select
					If o\Hit Then Game\ControlLock=0.8*secs#
				EndIf

				; Toggle switch
				If o\Switch\SwitchNo[0]>0 Then o\Switch\s1\Active=0
			EndIf
			EndIf

			If o\Hit Then
				; Make Sonic a move
				If p\HasVehicle=0 And o\Enemy\WasKilledByBombMonitor=False Then
					If o\Enemy\IsBoss=1 Then
						p\JumpTimer=0
						p\Action=ACTION_JUMP : p\JumpMayRiseTimer=1.5*secs#
						Player_SetSpeed(p,-3.575)
						p\Motion\Speed\y#=0.5
						Select o\ObjType
							Case OBJTYPE_BOSSRUN,OBJTYPE_BOSSMECHA:
								p\Animation\Direction#=180
						End Select
					ElseIf p\Motion\Ground=False And o\Enemy\SelfDestruct=False And o\CheeseHit=False And o\BombHit=False And o\Psychoed=0 And o\Rubied=0 Then
						If (p\Flags\Stomping=1 And p\Bouncing=0) Or p\Action=ACTION_BOOSTFALL Or p\Action=ACTION_LIGHTATTACK Then 
							
						Else 
							If (Not(Object_ReturnCanAttackShield(p,o))) Or (o\Enemy\Health+1)>1 Then
								Player_JumpActionInteract(p,3)
							Else
								Player_JumpActionInteract(p)
							EndIf 
						EndIf 
						If p\Action=ACTION_LIGHTATTACK Then p\LightAttackHits=p\LightAttackHits+1 : p\LightAttackTimer=0.75*secs#
					EndIf
				EndIf
				p\Flags\HomingLocked = False
				p\Flags\Targeter=0 : p\Flags\InTargeterAttack=False : p\Flags\InTargeterAirAttack=False

				; Fix grabber hurt
				If (o\ObjType=OBJTYPE_GRABBER Or o\ObjType=OBJTYPE_KLAGEN Or o\ObjType=OBJTYPE_ACHAOSBLOB Or o\ObjType=OBJTYPE_BOO) And p\Action=ACTION_GRABBED Then
					EntityType(cam\Entity, COLLISION_CAMERA)
					EntityType(p\Objects\Entity, COLLISION_PLAYER)
					Player_Hit(p)
				EndIf
			EndIf

			; Fix hurt
			o\Enemy\MayNotHurtTimer=0.55*secs#
		
			; Delete the object
			If (Not(Object_ReturnCanAttackShield(p,o))) Or (o\Enemy\Health+1)>1 Then
				o\Hit=False
				o\CheeseHit=False
				o\BombHit=False
				o\Enemy\WillBeHomedTimer=0
			Else
				If o\Enemy\IsBoss=0 Then
					o\Enemy\SelfDestruct=False
					StopChannel(o\Enemy\Channel_EnemyStep)
					StopChannel(o\Enemy\Channel_EnemyState)
					o\CanHoming = False
					o\CheeseCanHoming=False
					o\Done=1
					HideEntity(o\Entity)
					Return
				Else
					o\Hit=False
					o\CheeseHit=False
					o\BombHit=False
					o\Enemy\WillBeHomedTimer=0
				EndIf
			EndIf
		EndIf

		; Jets
		Select o\ObjType
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD:
				Select o\Anim
					Case 3,6:
						ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet1)
						ParticleTemplate_Call(o\Enemy\JetParticle2, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet2)
				End Select
			Case OBJTYPE_MOTOBUG,OBJTYPE_ANTON:
				Select o\Anim
					Case 2,3:
						ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet1)
						ParticleTemplate_Call(o\Enemy\JetParticle2, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet2)
				End Select
			Case OBJTYPE_RHINOTANK:
				Select o\Anim
					Case 2,3:
						ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET2, o\Enemy\Jet1)
						ParticleTemplate_Call(o\Enemy\JetParticle2, PARTICLE_PLAYER_ROCKET2, o\Enemy\Jet2)
				End Select
			Case OBJTYPE_EGGROBO,OBJTYPE_ORBINAUT:
				Select o\Anim
					Case 1:
						ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet1)
						ParticleTemplate_Call(o\Enemy\JetParticle2, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet2)
				End Select
			Case OBJTYPE_E1000:
				Select o\Anim
					Case 3:
						ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET2, o\Enemy\Jet1)
						ParticleTemplate_Call(o\Enemy\JetParticle2, PARTICLE_PLAYER_ROCKET2, o\Enemy\Jet2)
				End Select
			Case OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BALKIRY,OBJTYPE_ZOOMER:
				ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET, o\Enemy\Jet1)
			Case OBJTYPE_COP,OBJTYPE_COPRACER:
				ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_ROCKET2, o\Enemy\Jet1)
			Case OBJTYPE_BOMBIE:
				If EntityDistance(p\Objects\Entity,o\Entity)<60 Then
					ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_PLAYER_FIRE, o\Enemy\Jet1)
				EndIf
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN:
				ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_OBJECT_ROCKETFUMES, o\Enemy\Jet1)
				PositionEntity o\Entity2, EntityX(o\Enemy\Center, 1), EntityY(o\Enemy\Center, 1), EntityZ(o\Enemy\Center, 1), 1
				RotateEntity o\Entity2, EntityPitch(o\Enemy\Center, 1), EntityYaw(o\Enemy\Center, 1), EntityRoll(o\Enemy\Center, 1), 1
				PositionEntity o\Entity3, EntityX(o\Enemy\Center, 1), EntityY(o\Enemy\Center, 1), EntityZ(o\Enemy\Center, 1), 1
				PointEntity o\Entity3, cam\Entity
			Case OBJTYPE_BOSSBETA:
				ParticleTemplate_Call(o\Enemy\JetParticle1, PARTICLE_OBJECT_ROCKETFUMES, o\Enemy\Jet1)
				ParticleTemplate_Call(o\Enemy\JetParticle2, PARTICLE_OBJECT_ROCKETFUMES, o\Enemy\Jet2)
				PositionEntity o\Entity3, EntityX(o\Enemy\Center, 1), EntityY(o\Enemy\Center, 1), EntityZ(o\Enemy\Center, 1), 1
				PointEntity o\Entity3, cam\Entity
				PositionEntity o\Entity2, EntityX(o\Enemy\Center, 1), EntityY(o\Enemy\Center, 1), EntityZ(o\Enemy\Center, 1), 1
			Case OBJTYPE_BOSSMECHA:
				PositionEntity o\Entity2, EntityX(o\Entity, 1), EntityY(o\Entity, 1), EntityZ(o\Entity, 1), 1
				RotateEntity o\Entity2, EntityPitch(o\Entity, 1), EntityYaw(o\Entity, 1), EntityRoll(o\Entity, 1), 1
				PositionEntity o\Entity3, EntityX(o\Enemy\Center, 1), EntityY(o\Enemy\Center, 1), EntityZ(o\Enemy\Center, 1), 1
				PointEntity o\Entity3, cam\Entity
		End Select

		; Fallen too low
		If o\Psychoed=0 And o\Rubied=0 And (o\InitialPosition\y#-o\Position\y#)>50 And o\Enemy\FlyEnemyType=False Then o\Enemy\SelfDestruct=True

		; Aiming and shooting
		Object_EnforceAimingShooting(o,p)
		
		If EntityDistance(o\Entity, p\Objects\Entity)<100 Then p\AroundEnemyTimer=0.1*secs#
		
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function Object_EnemyMissile_Create.tObject(o2.tObject, enemyno#, x#, y#, z#, pitch#=0, yaw#=0, roll#=0, mode=0)
		o.tObject = New tObject : o\ObjType = OBJTYPE_ENEMYMISSILE : o\ID=0
		o\ThisIsAnEnemyMissile=True : o\EnemyMissile = New tObject_EnemyMissile : o\HasValuesetEnemyMissile=True
		o\AlwaysPresent=True

		o\EnemyMissile\Sender=o2
		o\EnemyMissile\MissileType=enemyno#

		Select enemyno#
			Case OBJTYPE_KIKI,OBJTYPE_ASTERON: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,4,4,4)
			Case OBJTYPE_CHASER,OBJTYPE_BOSSRUN,-OBJTYPE_BOSSRUN: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,8.5,8.5,8.5)
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_MADMOLE: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,6,6,6)
			Case OBJTYPE_EGGROBO,OBJTYPE_STEELION,OBJTYPE_DRAGONFLY,OBJTYPE_BITER,OBJTYPE_OAKSWORD,OBJTYPE_CATAKILLER,OBJTYPE_SNOWY: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,6.5,6.5,6.5)
			Case OBJTYPE_CAMERON,-OBJTYPE_BOSSMECHA: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,9,9,9)
			Case OBJTYPE_BOSS2,OBJTYPE_AQUIS,OBJTYPE_SNAILB,OBJTYPE_BOSSMECHA: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,6.25,6.25,6.25)
			Case -OBJTYPE_BOSS2: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,8.25,6,8.25)
			Case -OBJTYPE_BOSS2*10,-OBJTYPE_CATAKILLER,-OBJTYPE_BOSSBETA*10: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,10.5,10.5,10.5)
			Case OBJTYPE_BOMBIE: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,15,15,15)
			Case OBJTYPE_SPIKES: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,3,3,3)
			Case OBJTYPE_TAKER,OBJTYPE_CRAWLER: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,7,7,7)
			Case OBJTYPE_E1000,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,2.5,2.5,2.5)
			Case OBJTYPE_TOXO: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,7.5,5,7.5)
			Case OBJTYPE_BOSSBETA: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,17.5,17.5,17.5)
			Case OBJTYPE_SPRINKLR: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,5.8,5.8,5.8)
			Case OBJTYPE_HAMMERHAMMER: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,11,7,11)
			Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,7.5,7.5,7.5)
			Default: Object_CreateHitBox(HITBOXTYPE_SPEEDY,o,5,5,5)
		End Select

		Object_Acquire_Position(o,x#,y#,z#)
		Object_Acquire_Rotation(o,0,yaw#,0)

		o\Entity = CreatePivot()
		Select enemyno#
			Case OBJTYPE_PAWNGUN,OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_PawnMissile)), o\Entity)
			Case OBJTYPE_GUNNER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Bullet)), o\Entity)	
			Case OBJTYPE_BUZZBOMBER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_BuzzMissile2)), o\Entity)
			Case OBJTYPE_BUZZER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_BuzzMissile1)), o\Entity)
			Case OBJTYPE_CRABMEAT,OBJTYPE_BOSS: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_CrabMissile)), o\Entity) : o\EnemyMissile\RealMissile=1
			Case OBJTYPE_SPINY,OBJTYPE_NEWTRON: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_SpinyMissile)), o\Entity)
			Case OBJTYPE_KIKI: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_KikiBomb)), o\Entity)
			Case OBJTYPE_CHASER,OBJTYPE_BOSSRUN,-OBJTYPE_BOSSRUN: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_ChaserMissile)), o\Entity)
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,-OBJTYPE_BOSSBETA: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_MissileGUN)), o\Entity) : o\EnemyMissile\RealMissile=1
			Case OBJTYPE_FLAPPERBOMB: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_FlapperBomb)), o\Entity)
			Case OBJTYPE_AEROC,OBJTYPE_BALLHOG: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_MissileBall)), o\Entity)
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_HornetBomb)), o\Entity)
			Case OBJTYPE_EGGROBO: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_EggMissile4)), o\Entity)
			Case OBJTYPE_CAMERON,-OBJTYPE_BOSSMECHA: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_CameronMissile)), o\Entity)
			Case OBJTYPE_ACHAOS: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_LaserMissile)), o\Entity)
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA,OBJTYPE_NEBULA: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_OrbinautBomb)), o\Entity)
			Case OBJTYPE_BOSS2,OBJTYPE_AQUIS,OBJTYPE_SNAILB,OBJTYPE_BOSSMECHA: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_EggMissile1)), o\Entity)
			Case -OBJTYPE_BOSS2: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_EggMissile2)), o\Entity)
			Case -OBJTYPE_BOSS2*10,-OBJTYPE_BOSSBETA*10: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_EggMissile3)), o\Entity)
			Case OBJTYPE_BOMBIE,OBJTYPE_STEELION,OBJTYPE_OCTUS,OBJTYPE_DRAGONFLY,OBJTYPE_BITER,OBJTYPE_OAKSWORD,OBJTYPE_CATAKILLER,-OBJTYPE_CATAKILLER,OBJTYPE_TOXO,OBJTYPE_SNOWY,OBJTYPE_BOSSBETA,OBJTYPE_HAMMERHAMMER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Empty)), o\Entity)
			Case OBJTYPE_SLICER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_SlicerSlicer)), o\Entity)
			Case OBJTYPE_SPIKES: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_SpikesSpike)), o\Entity)
			Case OBJTYPE_ASTERON: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_AsteronPoint)), o\Entity)
			Case OBJTYPE_MADMOLE: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_MadmoleMushroom)), o\Entity)
			Case OBJTYPE_MANTA: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_MantaBomb)), o\Entity)
			Case OBJTYPE_TAKER,OBJTYPE_CRAWLER: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_TakerSpikeBall)), o\Entity)
			Case OBJTYPE_E1000,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Bullet)), o\Entity)
			Case OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_DOOMSEYE: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_AlienMissile)), o\Entity)
			Case OBJTYPE_EXPLOSION2: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_ExplosionMissile)), o\Entity) : o\EnemyMissile\RealMissile=1
			Case OBJTYPE_SPRINKLR: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_SpikeBall)), o\Entity)
			Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_FCannonMissile)), o\Entity)
			Case OBJTYPE_BOMBER1: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_EnemyMissile_BomberMissile)), o\Entity)
			Case OBJTYPE_BOMBER2: o\EntityX = CopyEntity(MESHES(SmartEntity(Mesh_Enemy_Bomber2)), o\Entity) : ScaleEntity o\EntityX, 0.5, 0.5, 0.5
		End Select
		EntityBlend o\EntityX, 1

		RotateEntity o\Entity, 0, yaw#, 0
		PositionEntity o\Entity, x#, y#, z#
		o\Repose=1

		EntityType(o\Entity,COLLISION_OBJECT_GOTHRU)

		Select enemyno#
			Case OBJTYPE_PAWNGUN:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.1*secs#
				
				MoveEntity o\Entity,0,3.5,6.5
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_FLAPPERGUN:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.1*secs#
				MoveEntity o\Entity,0,-4.5,3.5
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.1*secs#
				MoveEntity o\Entity,0,-2.5,3.5
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER:
				o\EnemyMissile\FollowTimer=0.57*secs#
				o\EnemyMissile\DisappearTimer=2.8*secs#
				MoveEntity o\Entity,0,-5.62,2
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_CRABMEAT:
				o\EnemyMissile\FollowTimer=0.79*secs#
				o\EnemyMissile\DisappearTimer=8.1*secs#
				Select mode
					Case 1: MoveEntity o\Entity,5.1,5,2
					Case 2: MoveEntity o\Entity,-5.1,5,2
				End Select
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_SPINY,OBJTYPE_NEWTRON:
				o\EnemyMissile\FollowTimer=0.15*secs#
				o\EnemyMissile\DisappearTimer=2.8*secs#
				Select o\EnemyMissile\MissileType
					Case OBJTYPE_SPINY: MoveEntity o\Entity,0,3,0
					Case OBJTYPE_NEWTRON: MoveEntity o\Entity,0,-0.46,5.96
				End Select
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_KIKI,OBJTYPE_SPRINKLR,OBJTYPE_BOMBER2:
				o\EnemyMissile\FollowTimer=1.25*secs#
				Select enemyno#
					Case OBJTYPE_BOMBER2:
						o\EnemyMissile\DisappearTimer=1.25*secs#
						MoveEntity o\Entity,0,-1.3119,0.8999
						o\EnemyMissile\KikiBombSpeed#=1.75
					Default:
						o\EnemyMissile\DisappearTimer=6.1*secs#
						MoveEntity o\Entity,2,2,-3
						Select(Rand(1,9))
							Case 1,6: o\EnemyMissile\KikiBombSpeed#=3
							Case 2: o\EnemyMissile\KikiBombSpeed#=4
							Case 3,7: o\EnemyMissile\KikiBombSpeed#=2
							Case 4,8: o\EnemyMissile\KikiBombSpeed#=2.5
							Case 5,9: o\EnemyMissile\KikiBombSpeed#=3.5
						End Select
				End Select
				Select enemyno#
					Case OBJTYPE_SPRINKLR,OBJTYPE_BOMBER2: EntityRadius o\Entity, 1.5
					Default: EntityRadius o\Entity, 1.078
				End Select
			Case OBJTYPE_CHASER,OBJTYPE_BOSSRUN,-OBJTYPE_BOSSRUN:
				o\EnemyMissile\FollowTimer=0
				Select enemyno#
					Case OBJTYPE_BOSSRUN: o\EnemyMissile\DisappearTimer=0.65*secs#
					Case -OBJTYPE_BOSSRUN: o\EnemyMissile\DisappearTimer=0.8*secs#
					Default: o\EnemyMissile\DisappearTimer=1.8*secs#
				End Select
				Select enemyno#
					Case OBJTYPE_BOSSRUN,-OBJTYPE_BOSSRUN: MoveEntity o\Entity,0,-0.5,18
															RotateEntity o\Entity, pitch#, yaw#, 0
					Default:
						Select mode
							Case 1: MoveEntity o\Entity,-3,10,2.5
							Case 2: MoveEntity o\Entity,3,10,2.5
						End Select
				End Select
				EntityRadius o\Entity, 3
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,-OBJTYPE_BOSSBETA:
				o\EnemyMissile\FollowTimer=0.99*secs#
				o\EnemyMissile\DisappearTimer=3.1*secs#
				Select mode
					Case 1: MoveEntity o\Entity,0,-4.6,10.5
					Case 2: MoveEntity o\Entity,4.85,10,12.5
					Case 3,4: MoveEntity o\Entity,-1.78,0.44,13.4
				End Select
				EntityRadius o\Entity, 1.25
			Case OBJTYPE_GUNNER
				o\EnemyMissile\FollowTimer=0.5*secs#
				o\EnemyMissile\DisappearTimer=1.35*secs#
				;PositionEntity o\Entity
				; MoveEntity o\Entity,8,5,-3
				EntityRadius o\Entity, 1.25
			Case OBJTYPE_FLAPPERBOMB:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=3.5*secs#
				MoveEntity o\Entity,0,-5,0
				EntityRadius o\Entity, 1.4
			Case OBJTYPE_AEROC:
				o\EnemyMissile\FollowTimer=0.345*secs#
				o\EnemyMissile\DisappearTimer=3.4*secs#
				MoveEntity o\Entity,0,0,12
				EntityRadius o\Entity, 0.33
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
				o\EnemyMissile\FollowTimer=0.97*secs#
				o\EnemyMissile\DisappearTimer=3.0*secs#
				Select mode
					Case 1: MoveEntity o\Entity,0,12,-1.2
					Case 2: MoveEntity o\Entity,0,-12,-1.2
					Case 3: MoveEntity o\Entity,-10,6,-1.2
					Case 4: MoveEntity o\Entity,-10,-6,-1.2
					Case 5: MoveEntity o\Entity,10,6,-1.2
					Case 6: MoveEntity o\Entity,10,-6,-1.2
				End Select
				EntityRadius o\Entity, 2
			Case OBJTYPE_EGGROBO:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.8*secs#
				MoveEntity o\Entity,0,3.5457,3
				EntityRadius o\Entity, 3.5
			Case OBJTYPE_CAMERON,-OBJTYPE_BOSSMECHA:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=5.4*secs#
				Select enemyno#
					Case -OBJTYPE_BOSSMECHA: MoveEntity o\Entity,0,2.52,1.02
					Case OBJTYPE_CAMERON: MoveEntity o\Entity,0,6.5,8
				End Select
				EntityRadius o\Entity, 5
			Case OBJTYPE_ACHAOS:
				o\EnemyMissile\FollowTimer=0.5*secs#
				o\EnemyMissile\DisappearTimer=3*secs#
				MoveEntity o\Entity,mode*1.01,8.34,2.02
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA,OBJTYPE_NEBULA:
				o\EnemyMissile\FollowTimer=0
				Select enemyno#
					Case OBJTYPE_NEBULA: o\EnemyMissile\DisappearTimer=2*secs#
					Default: o\EnemyMissile\DisappearTimer=3*secs#
				End Select
				Select enemyno#
					Case OBJTYPE_ORBINAUT: MoveEntity o\Entity,0,0,5
					Case OBJTYPE_SPONA: MoveEntity o\Entity,0,0,20
					Case OBJTYPE_NEBULA: MoveEntity o\Entity,0,-4.08,0
				End Select
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_BOSS:
				o\EnemyMissile\FollowTimer=0.79*secs#
				o\EnemyMissile\DisappearTimer=8.1*secs#
				MoveEntity o\Entity,0,-0.5,8
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_BOSS2,OBJTYPE_AQUIS,OBJTYPE_SNAILB,OBJTYPE_BOSSMECHA:
				o\EnemyMissile\FollowTimer=0.5*secs#
				o\EnemyMissile\DisappearTimer=4*secs#
				Select enemyno#
					Case OBJTYPE_BOSS2: MoveEntity o\Entity,0,-0.5,18
					Case OBJTYPE_AQUIS: MoveEntity o\Entity,0,2.19,4.48
					Case OBJTYPE_SNAILB:
						Select mode
							Case 1: MoveEntity o\Entity,0,4.96,1.61
							Case 2: MoveEntity o\Entity,0,3.33,4
						End Select
					Case OBJTYPE_BOSSMECHA: MoveEntity o\Entity,0,2.52,1.02
				End Select
				EntityRadius o\Entity, 3
			Case -OBJTYPE_BOSS2:
				o\EnemyMissile\FollowTimer=1.5*secs#
				o\EnemyMissile\DisappearTimer=5*secs#
				EntityRadius o\Entity, 0.75
			Case -OBJTYPE_BOSS2*10,-OBJTYPE_BOSSBETA*10:
				o\EnemyMissile\FollowTimer=1*secs#
				o\EnemyMissile\DisappearTimer=8*secs#
				Select enemyno#
					Case -OBJTYPE_BOSS2*10: MoveEntity o\Entity,0,-0.5,22
					Case -OBJTYPE_BOSSBETA*10: MoveEntity o\Entity,0,-4.33,10.24
				End Select
				EntityRadius o\Entity, 5
			Case OBJTYPE_BOMBIE:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=0.25*secs#
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_SLICER:
				o\EnemyMissile\FollowTimer=0.5*secs#
				o\EnemyMissile\DisappearTimer=5.1*secs#
				Select mode
					Case 1: MoveEntity o\Entity,2.18,0.63,4.65
					Case 2: MoveEntity o\Entity,-2.18,0.63,4.65
				End Select
				EntityRadius o\Entity, 1.5
				o\EnemyMissile\KikiBombSpeed#=3.25
			Case OBJTYPE_SPIKES:
				o\EnemyMissile\FollowTimer=0.69*secs#
				o\EnemyMissile\DisappearTimer=7.1*secs#
				Select mode
					Case 1: MoveEntity o\Entity,0,2.52,-0.21
					Case 2: MoveEntity o\Entity,-1.03,1.35,-1.13
					Case 3: MoveEntity o\Entity,1.03,1.35,-1.13
					Case 4: MoveEntity o\Entity,-0.99,1.58,1.01
					Case 5: MoveEntity o\Entity,0.99,1.58,1.01
				End Select
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_ASTERON:
				o\EnemyMissile\FollowTimer=0.59*secs#
				o\EnemyMissile\DisappearTimer=2.31*secs#
				Select mode
					Case 1: MoveEntity o\Entity,0,1.884,0
					Case 2: MoveEntity o\Entity,-1.824,0.564,0
					Case 3: MoveEntity o\Entity,-1.332,-1.572,0
					Case 4: MoveEntity o\Entity,1.332,-1.572,0
					Case 5: MoveEntity o\Entity,1.824,0.564,0
				End Select
				EntityRadius o\Entity, 1.25
				RotateEntity o\Entity, 0, yaw#, roll#
			Case OBJTYPE_STEELION,OBJTYPE_BITER,OBJTYPE_TOXO:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=0.5*secs#
				Select enemyno#
				Case OBJTYPE_STEELION: MoveEntity o\Entity,0,0.42,1.14
				Case OBJTYPE_BITER: MoveEntity o\Entity,0,3.7,10.0
				Case OBJTYPE_TOXO: MoveEntity o\Entity,0,3.23,0
				End Select
				EntityRadius o\Entity, 3
			Case OBJTYPE_MADMOLE:
				o\EnemyMissile\FollowTimer=0.2*secs#
				o\EnemyMissile\DisappearTimer=1.25*secs#
				MoveEntity o\Entity,0,2.28,5.39
				EntityRadius o\Entity, 2
			Case OBJTYPE_MANTA:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.5*secs#
				MoveEntity o\Entity,0,-2.6,0.75
				EntityRadius o\Entity, 1.188
			Case OBJTYPE_OCTUS:
				o\EnemyMissile\FollowTimer=0.1*secs#
				o\EnemyMissile\DisappearTimer=2*secs#
				MoveEntity o\Entity,0,0.901,5.65
				EntityRadius o\Entity, 1.5
			Case OBJTYPE_DRAGONFLY,OBJTYPE_OAKSWORD,OBJTYPE_CATAKILLER,OBJTYPE_SNOWY,OBJTYPE_HAMMERHAMMER:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=0.1*secs#
				EntityRadius o\Entity, 0
			Case OBJTYPE_TAKER,OBJTYPE_CRAWLER:
				o\EnemyMissile\FollowTimer=1*secs#
				o\EnemyMissile\DisappearTimer=4*secs#
				Select enemyno#
				Case OBJTYPE_TAKER: MoveEntity o\Entity,0,-4.4,0.67
				Case OBJTYPE_CRAWLER: MoveEntity o\Entity,0,13.44,13.49
				End Select
				EntityRadius o\Entity, 3
				RotateEntity o\Entity, 0, Rand(1,360), 0
				o\ExtraTexture=LoadTexture("Objects\Enemies\monsterfire.png", 1+256) : o\HasExtraTexture=1
				ApplyMeshTextureLayer(o\EntityX, "monsterfire.png", o\ExtraTexture)
			Case OBJTYPE_E1000:
				o\EnemyMissile\FollowTimer=0.75*secs#
				o\EnemyMissile\DisappearTimer=1.26*secs#
				Select mode
					Case 1: MoveEntity o\Entity,-3.49,3.89,5.44
					Case 2: MoveEntity o\Entity,3.49,3.89,5.44
					Case 3: MoveEntity o\Entity,-3.05,0.74,9.65
					Case 4: MoveEntity o\Entity,3.05,0.74,9.65
				End Select
				EntityRadius o\Entity, 1
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
				o\EnemyMissile\FollowTimer=0.45*secs#
				o\EnemyMissile\DisappearTimer=1.343*secs#
				Select mode
					Case 1: MoveEntity o\Entity,1.2,5.55,4.88
					Case 2: MoveEntity o\Entity,1.27,5.27,6.61
				End Select
				EntityRadius o\Entity, 1
			Case OBJTYPE_BALLHOG:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2*secs#
				MoveEntity o\Entity,0,0.43,2.37
				ScaleEntity o\EntityX,5,5,5
				EntityRadius o\Entity, 2.5
			Case OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_DOOMSEYE:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.1*secs#
				Select enemyno#
					Case OBJTYPE_WARRIORGUN1:
						MoveEntity o\Entity,4.05,3.25,8
					Case OBJTYPE_WARRIORGUN2:
						MoveEntity o\Entity,3.4,3.25,10.77
						Select mode
							Case 1: MoveEntity o\Entity,-0.97,0,0
							Case 2: MoveEntity o\Entity,0.97,0,0
						End Select
					Case OBJTYPE_DOOMSEYE:
						MoveEntity o\Entity,0,0,2
				End Select
				EntityRadius o\Entity, 1.5
			Case -OBJTYPE_CATAKILLER:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=0.4*secs#
				MoveEntity o\Entity,0,0,-6
				EntityRadius o\Entity, 0
			Case OBJTYPE_BOSSBETA:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=0.75*secs#
				EntityRadius o\Entity, 0
			Case OBJTYPE_EXPLOSION2:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=0.2*secs#
				EntityRadius o\Entity, 1.5
				RotateEntity o\Entity, pitch#, yaw#, 0
			Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				o\EnemyMissile\FollowTimer=1*secs#
				o\EnemyMissile\DisappearTimer=5*secs#
				EntityRadius o\Entity, 3
			Case OBJTYPE_BOMBER1:
				o\EnemyMissile\FollowTimer=0
				o\EnemyMissile\DisappearTimer=2.5*secs#
				MoveEntity o\Entity,0,0.1043,1.8317
				EntityRadius o\Entity, 1.5
		End Select

		o\Mode=mode

		Return o
	End Function
	
	; =========================================================================================================
	
	Function Object_EnemyMissile_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Update missile timer
		If o\EnemyMissile\FollowTimer>0 Then o\EnemyMissile\FollowTimer=o\EnemyMissile\FollowTimer-timervalue#
		If o\EnemyMissile\DisappearTimer>0 Then o\EnemyMissile\DisappearTimer=o\EnemyMissile\DisappearTimer-timervalue#

		; Movement
		If (Not(Object_EnemyIsStun(o))) Then
		Select o\EnemyMissile\MissileType
			Case OBJTYPE_PAWNGUN,OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_DOOMSEYE:
				MoveEntity(o\Entity,0,0,0.85*d\Delta*0.75)
			Case OBJTYPE_EGGHUNTER
				MoveEntity(o\Entity,0,0,0.85*d\Delta*1.1)
			Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER:
				MoveEntity(o\Entity,0,0,0.85*d\Delta*0.75)
				If o\EnemyMissile\FollowTimer>0 And o\EnemyMissile\FollowTimer<0.42*secs#*0.5 Then PointEntity(o\Entity,p\Objects\Entity)
			Case OBJTYPE_CRABMEAT:
				MoveEntity(o\Entity,0,0,0.45*d\Delta*0.75)
				If o\EnemyMissile\FollowTimer>0 And o\EnemyMissile\FollowTimer<0.44*secs#*0.5 Then PointEntity(o\Entity,p\Objects\Entity)
			Case OBJTYPE_BOSS:
				MoveEntity(o\Entity,0,0,(0.6+0.3*o\Mode)*d\Delta*0.75)
				If o\EnemyMissile\FollowTimer>0 And o\EnemyMissile\FollowTimer<0.44*secs#*0.5 Then PointEntity(o\Entity,p\Objects\Entity)
			Case OBJTYPE_SPINY: MoveEntity(o\Entity,0,0.8*d\Delta*0.75,0)
			Case OBJTYPE_KIKI,OBJTYPE_SPRINKLR,OBJTYPE_BOMBER2:
				If o\EnemyMissile\FollowTimer>1.250*secs# Then
					MoveEntity(o\Entity,0,3*0.150*d\Delta*0.85,o\EnemyMissile\KikiBombSpeed#*0.15*d\Delta*0.85)
				ElseIf o\EnemyMissile\FollowTimer>0.90*secs# Then
					MoveEntity(o\Entity,0,3*0.3*d\Delta*0.85,o\EnemyMissile\KikiBombSpeed#*0.3*d\Delta*0.85)
				ElseIf o\EnemyMissile\FollowTimer>0.625*secs# Then
					MoveEntity(o\Entity,0,0*d\Delta*0.85,o\EnemyMissile\KikiBombSpeed#*0.21*d\Delta*0.85)
				ElseIf o\EnemyMissile\FollowTimer>0.310*secs# Then
					MoveEntity(o\Entity,0,-3*0.15*d\Delta*0.85,o\EnemyMissile\KikiBombSpeed#*0.15*d\Delta*0.85)
				ElseIf o\EnemyMissile\FollowTimer>0*secs# Then
					MoveEntity(o\Entity,0,-3*0.35*d\Delta*0.85,o\EnemyMissile\KikiBombSpeed#*0.25*d\Delta*0.85)
				Else
					MoveEntity(o\Entity,0,-1*d\Delta*0.85,0.2*d\Delta*0.85)
				EndIf
			Case OBJTYPE_CHASER,OBJTYPE_BOSSRUN:
				MoveEntity(o\Entity,0,0,1.85*d\Delta*0.75)
			Case -OBJTYPE_BOSSRUN:
				MoveEntity(o\Entity,0,-1.85*d\Delta*0.75,1.85*d\Delta*0.75)
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE:
				If o\EnemyMissile\FollowTimer>0 And o\EnemyMissile\FollowTimer<0.64*secs#*0.5 Then
					PointEntity(o\Entity,p\Objects\Entity)
					MoveEntity(o\Entity,0,0,0.85*d\Delta*0.75)
				Else
					MoveEntity(o\Entity,0,0,0.348*d\Delta*0.75)
				EndIf
			Case OBJTYPE_GUNNER
				PointEntity(o\Entity,p\Objects\Entity)
				MoveEntity(o\Entity,0,0,0.85*d\Delta*2)
				EntityColor(o\EntityX,50,0,0)
			Case OBJTYPE_FLAPPERBOMB:
				MoveEntity(o\Entity,0,-2*d\Delta*0.75,0)
			Case OBJTYPE_AEROC:
				MoveEntity(o\Entity,0,0,0.74*d\Delta*0.75)
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
				MoveEntity(o\Entity,0,0,0.535*d\Delta*0.75)
				If o\EnemyMissile\FollowTimer>0 And o\EnemyMissile\FollowTimer<0.44*secs#*0.5 Then PointEntity(o\Entity,p\Objects\Entity)
			Case OBJTYPE_EGGROBO:
				MoveEntity o\Entity,0,0,1.4*d\Delta
				If o\EnemyMissile\DisappearTimer>(2.8-0.52484)*secs# Then MoveEntity o\Entity,0.82*o\Mode*d\Delta,0,0
			Case OBJTYPE_CAMERON: MoveEntity(o\Entity,0,0,0.65*d\Delta*0.75)
			Case OBJTYPE_ACHAOS:
				MoveEntity(o\Entity,0,0,1.45*d\Delta*0.75)
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA:
				If o\Mode>0 Then
					o\EnemyMissile\DisappearTimer=0.1*secs#
				Else
					MoveEntity(o\Entity,0,0,0.755*d\Delta*0.75)
				EndIf
			Case OBJTYPE_BOSS2:
				If o\EnemyMissile\FollowTimer>0 Then
					MoveEntity(o\Entity,0,-0.25*d\Delta,0)
				Else
					RotateEntity o\Entity,EntityPitch(o\Entity),(DeltaYaw#(p\Objects\Entity,o\Entity) - 180),EntityRoll(o\Entity)
					MoveEntity(o\Entity,0,-0.35*d\Delta,0.95*d\Delta)
				EndIf
			Case OBJTYPE_AQUIS,OBJTYPE_SNAILB:
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
				MoveEntity(o\Entity,0,0,0.75*d\Delta)
			Case -OBJTYPE_BOSS2:
				If o\EnemyMissile\FollowTimer>0 Then 
					MoveEntity(o\Entity,0,-0.75*d\Delta,0)
				Else
					EntityType(o\Entity,0)
					MoveEntity(o\Entity,0,0,1.65*d\Delta)
				EndIf
			Case -OBJTYPE_BOSS2*10,-OBJTYPE_BOSSBETA*10:
				If o\EnemyMissile\MissileType=-OBJTYPE_BOSSBETA*10 And o\EnemyMissile\FollowTimer>0 Then
					ScaleEntity o\EntityX,1.4*(1-o\EnemyMissile\FollowTimer/secs#),1.4*(1-o\EnemyMissile\FollowTimer/secs#),1.4*(1-o\EnemyMissile\FollowTimer/secs#)
				EndIf
				If o\EnemyMissile\FollowTimer>0 Then
					MoveEntity(o\Entity,0,-0.15*d\Delta,0)
				Else
					PointEntity(o\Entity,p\Objects\Entity)
					Select o\EnemyMissile\MissileType
						Case -OBJTYPE_BOSSBETA*10: MoveEntity(o\Entity,0,0,1.05*d\Delta)
						Default: MoveEntity(o\Entity,0,0,0.75*d\Delta)
					End Select
				EndIf
			Case OBJTYPE_NEWTRON:
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
				MoveEntity(o\Entity,0,0,0.8*d\Delta*0.75)
			Case OBJTYPE_SPIKES,OBJTYPE_ASTERON: MoveEntity(o\Entity,0,0.45*d\Delta*0.75,0)
			Case OBJTYPE_STEELION,OBJTYPE_BITER: MoveEntity(o\Entity,0,0,1.2*d\Delta*0.75)
			Case OBJTYPE_MADMOLE:
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
				MoveEntity(o\Entity,0,0,0.8*d\Delta*0.75)
			Case OBJTYPE_MANTA: MoveEntity(o\Entity,0,-1.8*d\Delta*0.75,0)
			Case OBJTYPE_OCTUS:
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
				MoveEntity(o\Entity,0,0,0.85*d\Delta*0.75)
			Case OBJTYPE_DRAGONFLY,OBJTYPE_OAKSWORD,OBJTYPE_CATAKILLER,OBJTYPE_SNOWY:
				Select o\Mode
				Case 1: PositionEntity o\Entity, EntityX(o\EnemyMissile\Sender\Enemy\Jet1,1), EntityY(o\EnemyMissile\Sender\Enemy\Jet1,1), EntityZ(o\EnemyMissile\Sender\Enemy\Jet1,1), 1
				Case 2: PositionEntity o\Entity, EntityX(o\EnemyMissile\Sender\Enemy\Jet2,1), EntityY(o\EnemyMissile\Sender\Enemy\Jet2,1), EntityZ(o\EnemyMissile\Sender\Enemy\Jet2,1), 1
				End Select
				Select o\EnemyMissile\MissileType
					Case OBJTYPE_CATAKILLER:
						If p\Flags\Attacking Then o\EnemyMissile\MayNotHurt=1 Else o\EnemyMissile\MayNotHurt=0
				End Select
			Case OBJTYPE_SLICER:
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
				If o\EnemyMissile\DisappearTimer>3.6*secs# Then
					MoveEntity(o\Entity,0,0,o\EnemyMissile\KikiBombSpeed#*0.21*d\Delta*0.85)
				Else
					Select o\Mode
					Case 1: PointEntity(o\Entity,o\EnemyMissile\Sender\Enemy\Jet1)
					Case 2: PointEntity(o\Entity,o\EnemyMissile\Sender\Enemy\Jet2)
					End Select
					If o\EnemyMissile\DisappearTimer>2.1*secs# Then
						MoveEntity(o\Entity,0,o\EnemyMissile\KikiBombSpeed#*0.21*d\Delta*0.85,o\EnemyMissile\KikiBombSpeed#*0.21*d\Delta*0.85)
					Else
						MoveEntity(o\Entity,0,0,o\EnemyMissile\KikiBombSpeed#*0.21*d\Delta*0.85)
					EndIf
					If EntityDistance(o\Entity,o\EnemyMissile\Sender\Entity)<o\EnemyMissile\Sender\HitBox\y# Then
						o\EnemyMissile\DisappearTimer=0
						o\EnemyMissile\Sender\Enemy\WaitTimer2=0
						o\EnemyMissile\Sender\Enemy\AttackMode=0
					EndIf
				EndIf
			Case OBJTYPE_TAKER,OBJTYPE_CRAWLER:
				PositionTexture(o\ExtraTexture, 0, 0.0004*Game\Gameplay\Time)
				If o\EnemyMissile\FollowTimer>0 Then
					PointEntity(o\Entity,p\Objects\Entity)
					MoveEntity(o\Entity,0,0,0.65*d\Delta*0.75)
				Else
					RotateEntity(o\Entity,0,EntityYaw(o\Entity),0)
					MoveEntity(o\Entity,0,-0.85*d\Delta*0.75,0)
				EndIf
			Case OBJTYPE_E1000,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
				If o\EnemyMissile\FollowTimer>0 Then PointEntity(o\Entity,p\Objects\Entity)
				MoveEntity o\Entity,0,0,0.425*1.46*d\Delta
			Case OBJTYPE_BALLHOG:
				bounce=False
				If o\EnemyMissile\DisappearTimer>1.5*secs# Then
					MoveEntity o\Entity,0,-0.4*d\Delta,0.35*d\Delta
				ElseIf o\EnemyMissile\DisappearTimer>1.0*secs# Then
					MoveEntity o\Entity,0,0.4*d\Delta,0.35*d\Delta
				ElseIf o\EnemyMissile\DisappearTimer>0.5*secs# Then
					MoveEntity o\Entity,0,-0.4*d\Delta,0.35*d\Delta
				ElseIf o\EnemyMissile\DisappearTimer>0.0*secs# Then
					MoveEntity o\Entity,0,0.4*d\Delta,0.35*d\Delta
				Else
					MoveEntity o\Entity,0,-0.4*d\Delta,0
				EndIf
				If o\EnemyMissile\DisappearTimer>1.5*secs# And o\EnemyMissile\DisappearTimer<1.51*secs# Then
					bounce=True
				ElseIf o\EnemyMissile\DisappearTimer>1.0*secs# And o\EnemyMissile\DisappearTimer<1.01*secs# Then
					bounce=True
				ElseIf o\EnemyMissile\DisappearTimer>0.5*secs# And o\EnemyMissile\DisappearTimer<0.51*secs# Then
					bounce=True
				ElseIf o\EnemyMissile\DisappearTimer>0.0*secs# And o\EnemyMissile\DisappearTimer<0.01*secs# Then
					bounce=True
				EndIf
				If bounce And (Not(ChannelPlaying(o\EnemyMissile\Channel_State))) Then o\EnemyMissile\Channel_State=EmitSmartSound(Sound_Crusher,o\Entity)
			Case -OBJTYPE_CATAKILLER,OBJTYPE_BOSSBETA:
				PositionEntity o\Entity, EntityX(o\EnemyMissile\Sender\Enemy\Center,1), EntityY(o\EnemyMissile\Sender\Enemy\Center,1), EntityZ(o\EnemyMissile\Sender\Enemy\Center,1), 1
				If Not(ChannelPlaying(o\EnemyMissile\Channel_State)) Then o\EnemyMissile\Channel_State=EmitSmartSound(Sound_EnemyElectric,o\Entity)
			Case OBJTYPE_NEBULA: MoveEntity(o\Entity,0,-0.755*d\Delta*0.75,0)
			Case -OBJTYPE_BOSSBETA:
				If Not(o\Mode=4) Then
					PointEntity(o\Entity,p\Objects\Entity)
					MoveEntity(o\Entity,0,0,1.85*d\Delta*0.75)
				Else
					If o\Position\y#>p\Objects\Position\y#+1 Then MoveEntity(o\Entity,0,-0.55*d\Delta*0.75,0)
					If o\Position\y#<p\Objects\Position\y#-1 Then MoveEntity(o\Entity,0,0.55*d\Delta*0.75,0)
					MoveEntity(o\Entity,0,0,1.95*d\Delta*0.75)
				EndIf
			Case OBJTYPE_BOSSMECHA:
				RotateEntity o\Entity,EntityPitch(o\Entity),(DeltaYaw#(p\Objects\Entity,o\Entity) - 180),EntityRoll(o\Entity)
				MoveEntity(o\Entity,0,-1.05*d\Delta,0.95*d\Delta)
			Case -OBJTYPE_BOSSMECHA:
				MoveEntity(o\Entity,0,-0.85*d\Delta,0.65*d\Delta*0.75)
			Case OBJTYPE_EXPLOSION2:
				MoveEntity(o\Entity,0,0,9.5*d\Delta*0.75)
			Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				If o\EnemyMissile\FollowTimer>0 Then
					If Not(o\EnemyMissile\MissileType=OBJTYPE_FCANNON3) Then PointEntity(o\Entity,p\Objects\Entity)
				EndIf
				MoveEntity(o\Entity,0,0,0.785*d\Delta*0.75)
			Case OBJTYPE_BOMBER1:
				MoveEntity(o\Entity,0,0,0.9*d\Delta*0.75)
		End Select
		EndIf

		; Turn mesh around
		Select o\EnemyMissile\MissileType
			Case OBJTYPE_KIKI,OBJTYPE_FLAPPERBOMB,-OBJTYPE_BOSS2,OBJTYPE_BOMBIE,OBJTYPE_STEELION,OBJTYPE_OCTUS,OBJTYPE_DRAGONFLY,OBJTYPE_BITER,OBJTYPE_TAKER,OBJTYPE_CRAWLER,OBJTYPE_E1000,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_OAKSWORD,OBJTYPE_CATAKILLER,-OBJTYPE_CATAKILLER,OBJTYPE_TOXO,OBJTYPE_SNOWY,OBJTYPE_HAMMERHAMMER,OBJTYPE_BOMBER2:
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_MANTA,OBJTYPE_NEBULA,OBJTYPE_SPRINKLR: TurnEntity o\EntityX, 0, 0.3*20*d\Delta, 0
			Case OBJTYPE_SLICER,OBJTYPE_MADMOLE: TurnEntity o\EntityX, 0.7*20*d\Delta, 0, 0
			Default: TurnEntity o\EntityX, 0, 0, 0.4*20*d\Delta
		End Select

		; Psychokinesis and stun
		Select o\EnemyMissile\MissileType
			Case OBJTYPE_BOMBIE:
			Default:
				Object_EnforcePsychokinesis(o,p,d)
				Object_EnforceRubyGravity(o,p,d)
				Object_EnforceStun(o,p,d)
		End Select

		; Bullet particle
		Select o\EnemyMissile\MissileType
			Case OBJTYPE_PAWNGUN,OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_ACHAOS:
				ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_BULLETHEAT, o\Entity, 1, 1, 1, 0, 0, 0.025)
			Case OBJTYPE_CRABMEAT,OBJTYPE_HUNTER,OBJTYPE_GUNNER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BOSS,-OBJTYPE_BOSSBETA,OBJTYPE_EXPLOSION2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_SMOKE, o\Entity, 0.2, 0.099, 1, 0, 3, 0.1)
			Case OBJTYPE_STEELION:
				ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_ICE, o\Entity)
			Case OBJTYPE_BITER,OBJTYPE_TAKER,OBJTYPE_CRAWLER:
				ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_FIRE, o\Entity)
			Case OBJTYPE_OCTUS:
				ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_INK, o\Entity)
			Case OBJTYPE_E1000,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
				ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_ATTACKTRAIL, o\Entity, 0.5, 0.075, 3, 0, 8)
			Case OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_DOOMSEYE:
				ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_BULLETHEATALIEN, o\Entity, 1, 1, 1, 0, 0, 0.025)
			Case -OBJTYPE_CATAKILLER,OBJTYPE_BOSSBETA:
				ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ELECTRIC, o\Entity, 0, 0, 0, 0, 0, 0.05)
			Case OBJTYPE_TOXO:
				ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_POISONFOG, o\Entity)
		End Select

		; Delete the object
	 	If o\Hit Or (Not(o\EnemyMissile\DisappearTimer>0)) Or o\EnemyMissile\DeleteDestroy Then
	 		If (Not(o\EnemyMissile\DeleteDestroy)) Then
	 			; If hurt player
				If o\Hit And o\PsychoedThrown=False And o\EnemyMissile\MayNotHurt=0 Then
					If Game\Invinc=0 And (Not(p\DontGetHurtTimer>0)) Then
						Select o\EnemyMissile\MissileType
							Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA,OBJTYPE_SPIKES,OBJTYPE_ASTERON,OBJTYPE_MANTA,OBJTYPE_NEBULA,OBJTYPE_SPRINKLR: EmitSmartSound(Sound_Spikes,o\Entity)
							Case OBJTYPE_MADMOLE: EmitSmartSound(Sound_Ninja,o\Entity)
							Case OBJTYPE_OCTUS,OBJTYPE_TOXO: EmitSmartSound(Sound_Throw,o\Entity)
							Case OBJTYPE_DRAGONFLY: If Not(p\HurtTimer>0) Then EmitSmartSound(Sound_Spikes,o\Entity)
							Case OBJTYPE_OAKSWORD: If Not(p\HurtTimer>0) Then EmitSmartSound(Sound_Blade,o\Entity)
						End Select
					EndIf
					Select o\EnemyMissile\MissileType
						Case OBJTYPE_STEELION: Player_Action_Freeze_Initiate2(p)
						Case OBJTYPE_OCTUS,OBJTYPE_TOXO:
							p\InkFloorTimer=3*secs#
							Select o\EnemyMissile\MissileType
							Case OBJTYPE_TOXO: p\Inked=2 : EntityColor(p\Objects\Mesh,248,16,234)
							Default: p\Inked=1 : EntityColor(p\Objects\Mesh,20,20,20)
							End Select
						Case OBJTYPE_MADMOLE:
							If Not(p\BumpedTimer>0) Then
								p\Animation\Direction#=EntityYaw(o\Entity)
								If p\HasVehicle=0 Then
									Player_Action_Bumped_Initiate(p)
								Else
									p\BumpedTimer=0.5*secs#
								EndIf
								If Game\ControlLock<0.5*secs# Then Game\ControlLock=0.5*secs#
								Player_SetSpeed(p,-1.5)
								Player_SetSpeedY(p,0.75)
							EndIf
						Case OBJTYPE_BITER:
							If (Not(Game\Shield=OBJTYPE_FSHIELD Or p\Character=CHAR_BLA)) Then Player_Hit(p)
						Case OBJTYPE_TAKER,OBJTYPE_CRAWLER:
							If (Not(Game\Shield=OBJTYPE_FSHIELD)) Then Player_Hit(p)
						Case OBJTYPE_GUNNER
							Object_Enemy_SpecialBehaviour_GunnerRingDrain(p)
							ParticleTemplate_Call(o\Particle, PARTICLE_PLAYER_SMOKE, p\Objects\Entity, 0.2, 0.099, 1, 0, 3, 0.1)
						Default: Player_Hit(p)
					End Select
				EndIf

				; Upon always
				Select o\EnemyMissile\MissileType
					Case OBJTYPE_CRABMEAT,OBJTYPE_KIKI,OBJTYPE_FLAPPERBOMB,OBJTYPE_BOSS,OBJTYPE_MANTA,OBJTYPE_EXPLOSION2,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
						EmitSmartSound(Sound_Bombed,o\Entity)
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BOMB, o\Entity)
					Case OBJTYPE_BOMBIE:
						EmitSmartSound(Sound_Bombed,o\Entity)
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BOMBBIG, o\Entity)
					Case -OBJTYPE_BOSS2*10,-OBJTYPE_BOSSBETA*10:
						EmitSmartSound(Sound_EnemyShotBlow,o\Entity)
					Case OBJTYPE_TAKER,OBJTYPE_CRAWLER:
						EmitSmartSound(Sound_SpearImpact,o\Entity)
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FLAMYBLOOD, o\Entity)
					Case OBJTYPE_BOMBER2:
						EmitSmartSound(Sound_Paddle2,o\Entity)
				End Select
			EndIf

			; Deletion
			Select o\EnemyMissile\MissileType
				Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA:
					If o\Mode>0 Then
						If o\Mode=1 Then o\EnemyMissile\Sender\Enemy\HasBossObj1=False
						If o\Mode=2 Then o\EnemyMissile\Sender\Enemy\HasBossObj2=False
						If o\Mode=3 Then o\EnemyMissile\Sender\Enemy\HasBossObj3=False
						If o\Mode=4 Then o\EnemyMissile\Sender\Enemy\HasBossObj4=False
					EndIf
			End Select
			StopChannel(o\EnemyMissile\Channel_State)
			FreeEntity o\Entity
			Delete o\Position
			Delete o\Rotation
			Delete o
			Return
		EndIf

	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_EnemyLookAtPlayer(o.tObject, p.tPlayer)
	If (Not(p\Action=ACTION_HOMING)) And (Abs(p\Objects\Position\x# - o\Position\x#) > 2.5) And (Abs(p\Objects\Position\z# - o\Position\z#) > 2.5) Then
		RotateEntity o\Entity,EntityPitch(o\Entity),(DeltaYaw#(p\Objects\Entity,o\Entity) - 180),EntityRoll(o\Entity)
	EndIf
End Function

Function Object_EnemyPointAtPlayer(o.tObject, p.tPlayer)
	If (Not(p\Action=ACTION_HOMING)) And (Abs(p\Objects\Position\x# - o\Position\x#) > 2.5) And (Abs(p\Objects\Position\z# - o\Position\z#) > 2.5) Then
		PointEntity(o\Entity,p\Objects\Entity)
	EndIf
End Function

Function Object_EnemyMoveToPlayer(o.tObject, p.tPlayer, d.tDeltaTime, speed#)
	If (Not(p\Action=ACTION_GRABBED Or p\Action=ACTION_FREEZE)) And (((Abs(p\Objects\Position\x# - o\Position\x#) > 1.3) And (Abs(p\Objects\Position\z# - o\Position\z#) > 1.3)) Or (Abs(p\Objects\Position\y# - o\Position\y#) > 1.3)) Then
		MoveEntity(o\Entity,0,0,speed#*d\Delta)
	EndIf
End Function

Function Object_EnemyMoveUp(o.tObject, p.tPlayer, d.tDeltaTime, speed#)
	If (Not(p\Action=ACTION_GRABBED Or p\Action=ACTION_FREEZE)) Then
		MoveEntity(o\Entity,0,speed#*d\Delta,0)
	EndIf
End Function

Function Object_EnemyMoveUpAndDown(o.tObject, p.tPlayer, d.tDeltaTime, limitup#, limitdown#, speed#)
	If o\Position\y#>p\Objects\Position\y#+limitup# Then Object_EnemyMoveUp(o,p,d,-speed#)
	If o\Position\y#<p\Objects\Position\y#-limitdown# Then Object_EnemyMoveUp(o,p,d,+speed#)
End Function

Function Object_EnemyMovements_SpawnMissile(o.tObject, p.tPlayer)
	If o\Enemy\ShouldSpawnMissile Then
		Select o\ObjType
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
			Case OBJTYPE_GUNNER
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Gun,1), EntityY(o\Enemy\Gun,1), EntityZ(o\Enemy\Gun,1), 0, EntityYaw(o\Entity), 0, 2)
				
			Case OBJTYPE_BEETLE:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
			Case OBJTYPE_CRABMEAT,OBJTYPE_CHASER,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_WARRIORGUN2:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
			Case OBJTYPE_HORNET3:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 4)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 6)
			Case OBJTYPE_HORNET6:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 4)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 6)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 3)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 5)
			Case OBJTYPE_EGGROBO:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, -1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
			Case OBJTYPE_ACHAOS:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, -1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA:
				i=False
				Select o\Mode
					Case 4: If o\Enemy\HasBossObj4 Then i=True
					Case 3: If o\Enemy\HasBossObj3 Then i=True
					Case 2: If o\Enemy\HasBossObj2 Then i=True
					Case 1: If o\Enemy\HasBossObj1 Then i=True
				End Select
				If i Then obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, EntityPitch(o\Entity), EntityYaw(o\Entity), 0)
				Select o\Mode
					Case 5:
						If o\Enemy\HasBossObj1=False Then
							o\Enemy\BossObj1.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
							Objects_Reset_HasMesh(o\Enemy\BossObj1) : Objects_Reset_Repose(o\Enemy\BossObj1) : Objects_Reset_Object(o\Enemy\BossObj1)
							o\Enemy\HasBossObj1=True
						EndIf
						If o\Enemy\HasBossObj2=False Then
							o\Enemy\BossObj2.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
							Objects_Reset_HasMesh(o\Enemy\BossObj2) : Objects_Reset_Repose(o\Enemy\BossObj2) : Objects_Reset_Object(o\Enemy\BossObj2)
							o\Enemy\HasBossObj2=True
						EndIf
						If o\Enemy\HasBossObj3=False Then
							o\Enemy\BossObj3.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 3)
							Objects_Reset_HasMesh(o\Enemy\BossObj3) : Objects_Reset_Repose(o\Enemy\BossObj3) : Objects_Reset_Object(o\Enemy\BossObj3)
							o\Enemy\HasBossObj3=True
						EndIf
						If o\Enemy\HasBossObj4=False Then
							o\Enemy\BossObj4.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 4)
							Objects_Reset_HasMesh(o\Enemy\BossObj4) : Objects_Reset_Repose(o\Enemy\BossObj4) : Objects_Reset_Object(o\Enemy\BossObj4)
							o\Enemy\HasBossObj4=True
						EndIf
						o\Mode=4
					Case 4:
						If o\Enemy\HasBossObj4 Then o\Enemy\BossObj4\Mode=0 : o\Enemy\HasBossObj4=False
						o\Mode=3
					Case 3:
						If o\Enemy\HasBossObj3 Then o\Enemy\BossObj3\Mode=0 : o\Enemy\HasBossObj3=False
						o\Mode=2
					Case 2:
						If o\Enemy\HasBossObj2 Then o\Enemy\BossObj2\Mode=0 : o\Enemy\HasBossObj2=False
						o\Mode=1
					Case 1:
						If o\Enemy\HasBossObj1 Then o\Enemy\BossObj1\Mode=0 : o\Enemy\HasBossObj1=False
						o\Mode=0
				End Select
			Case OBJTYPE_BOSS:
				Select o\Enemy\BossMode
					Case 1:
						obj.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8.46, o\Position\z#, 0, EntityYaw(o\Entity), 0, 0, OBJTYPE_SPEWSPIKEBOMB)
					Case 2,4:
						obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
				End Select
			Case OBJTYPE_BOSS2:
				Select o\Enemy\BossMode
					Case 1:
						obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
					Case 2,4:
						For i=0 To 40
						obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#-9.6, o\Position\z#, 0, EntityYaw(o\Entity)+(360/40)*i, 0, 1)
						Next
					Case 3:
						obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType*10, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
				End Select
			Case OBJTYPE_BOSSRUN:
				Select o\Enemy\BossMode
					Case 1:
						obj.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8.46, o\Position\z#, 0, EntityYaw(o\Entity), 0, 0, OBJTYPE_SPEWSPIKEBOMB)
					Case 2,3:
						If Not(o\Enemy\AttackTimer2>0) Then o\Enemy\AttackTimer2=2*secs#
						If o\Enemy\AttackTimer2>1*secs# Then
							obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, -90+180*(o\Enemy\AttackTimer2/secs#-1)/1.0, EntityYaw(o\Entity), 0, 1)
						Else
							obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 90-180*(o\Enemy\AttackTimer2/secs#)/1.0, EntityYaw(o\Entity), 0, 1)
						EndIf
					Case 4:
						If Not(o\Enemy\AttackTimer2>0) Then o\Enemy\AttackTimer2=4*secs#
						If o\Enemy\AttackTimer2>1*secs# Then
							obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity)-90+180*(o\Enemy\AttackTimer2/secs#-2)/2.0, 0, 1)
						Else
							obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity)+90-180*(o\Enemy\AttackTimer2/secs#)/2.0, 0, 1)
						EndIf
				End Select
			Case OBJTYPE_SPIKES:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 3)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 4)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 5)
			Case OBJTYPE_ASTERON:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0*72, 1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 1*72, 2)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 2*72, 3)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 3*72, 4)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 4*72, 5)
			Case OBJTYPE_STEELION,OBJTYPE_HAMMERHAMMER:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet1,1), EntityY(o\Enemy\Jet1,1), EntityZ(o\Enemy\Jet1,1), 0, EntityYaw(o\Entity), 0)
			Case OBJTYPE_DRAGONFLY,OBJTYPE_OAKSWORD,OBJTYPE_CATAKILLER:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet1,1), EntityY(o\Enemy\Jet1,1), EntityZ(o\Enemy\Jet1,1), 0, EntityYaw(o\Entity), 0, 1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet2,1), EntityY(o\Enemy\Jet2,1), EntityZ(o\Enemy\Jet2,1), 0, EntityYaw(o\Entity), 0, 2)
			Case OBJTYPE_E1000:
				If o\Anim=3 Then
					obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 3)
					obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 4)
				Else
					obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
					obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
				EndIf
			Case OBJTYPE_SOLDIER:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 1)
			Case OBJTYPE_SOLDIERCAMO:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 2)
			Case OBJTYPE_SNOWY:
				If o\Anim=3 Then obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet1,1), EntityY(o\Enemy\Jet1,1), EntityZ(o\Enemy\Jet1,1), 0, EntityYaw(o\Entity), 0, 1)
				If o\Anim=4 Then obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet2,1), EntityY(o\Enemy\Jet2,1), EntityZ(o\Enemy\Jet2,1), 0, EntityYaw(o\Entity), 0, 2)
			Case OBJTYPE_BOSSBETA:
				If o\Enemy\AttackMode<10 Then
					obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
				Else
					Select o\Enemy\BossMode
						Case 2:
							obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 3)
						Case 3:
							obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType*10, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
						Case 4:
							If o\Enemy\AttackMode<100 Then
								obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0, 4)
								obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity)+35, 0, 4)
								obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity)-35, 0, 4)
							Else
								obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType*10, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
							EndIf
					End Select
				EndIf
			Case OBJTYPE_BOSSMECHA:
				Select o\Enemy\BossMode
					Case 3:
						obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
					Case 4:
						obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
				End Select
			Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				o\Enemy\AttackMode=Rand(0,1)
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet1,1), EntityY(o\Enemy\Jet1,1), EntityZ(o\Enemy\Jet1,1), EntityPitch(o\Enemy\Jet1,1), EntityYaw(o\Entity), 0)
				If o\ObjType=OBJTYPE_FCANNON3 Then
					obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, EntityX(o\Enemy\Jet2,1), EntityY(o\Enemy\Jet2,1), EntityZ(o\Enemy\Jet2,1), EntityPitch(o\Enemy\Jet2,1), EntityYaw(o\Entity), 0)
				EndIf
			Default:
				obj.tObject = Object_EnemyMissile_Create(o, o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
				If o\ObjType=OBJTYPE_PAWNGUN Then o\EggpawnShootTimer=0.25*secs#
		End Select
		o\Enemy\ShouldSpawnMissile=False
	EndIf
End Function

Function Object_EnemyMovements(o.tObject, p.tPlayer, d.tDeltaTime)

	Object_EnemyMovements_SpawnMissile(o,p)

	If o\Enemy\WaitTimer>0 Then o\Enemy\WaitTimer=o\Enemy\WaitTimer-timervalue#
	
	If o\Enemy\WaitTimer2>0 Then o\Enemy\WaitTimer2=o\Enemy\WaitTimer2-timervalue#
	If o\Enemy\AttackTimer>0 Then o\Enemy\AttackTimer=o\Enemy\AttackTimer-timervalue#

	o\Enemy\MayGetAttacked=True

	Select o\ObjType
		Case OBJTYPE_PAWN,OBJTYPE_CHOPPER,OBJTYPE_FLAPPER,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPRING,OBJTYPE_HAMMER:
			Object_Enemy_Behaviour(o,p,d,8,1)
		Case OBJTYPE_PAWNSHIELD,OBJTYPE_HAMMERSHIELD:
			Object_Enemy_Behaviour(o,p,d,5,1)
		Case OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER:
			Object_Enemy_Behaviour(o,p,d,5.25,3.5,0.5)
		Case OBJTYPE_EGGHUNTER
			Object_Enemy_Behaviour(o,p,d,5.25,3.5,0.3)
		Case OBJTYPE_HAMMERHAMMER:
			Object_Enemy_Behaviour(o,p,d,7.0,5.5,0.1)
		Case OBJTYPE_PAWNSWORD:
			Object_Enemy_Behaviour(o,p,d,5.25,3.5,4,1)
		Case OBJTYPE_FLAPPERBOMB,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE:
			Object_Enemy_Behaviour(o,p,d,5.5,5,5.65)
		Case OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES:
			Object_Enemy_Behaviour(o,p,d,9)
			Select o\ObjType
				Case OBJTYPE_BUBBLSSPIKES: Object_Enemy_SpecialBehaviour(o,p)
			End Select
		Case OBJTYPE_MOTOBUG,OBJTYPE_SPIKES,OBJTYPE_RHINOTANK:
			Object_Enemy_Behaviour(o,p,d,8,4)
			Select o\ObjType
				Case OBJTYPE_SPIKES: Object_Enemy_SpecialBehaviour(o,p)
			End Select
		Case OBJTYPE_CATERKILLER,OBJTYPE_CAMERON:
			Object_Enemy_Behaviour(o,p,d,5.5,3.5,8,4)
		Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER:
			Object_Enemy_Behaviour(o,p,d,5.5,1,0.7)
		Case OBJTYPE_CRABMEAT:
			Object_Enemy_Behaviour(o,p,d,5,3.75,1.25)
		Case OBJTYPE_JAWS:
			Object_Enemy_Behaviour(o,p,d,7,1)
		Case OBJTYPE_SPINY:
			Object_Enemy_Behaviour(o,p,d,5.75,3.5,0.7)
		Case OBJTYPE_GRABBER,OBJTYPE_KLAGEN,OBJTYPE_ACHAOSBLOB,OBJTYPE_BOO:
			If p\Action=ACTION_GRABBED Then
				If o\Enemy\InRange And EntityDistance(o\Entity,p\Objects\Entity)<20 Then
					Object_Enemy_SpecialBehaviour(o,p)
				Else
					Select o\ObjType
						Case OBJTYPE_KLAGEN: Object_Enemy_Behaviour(o,p,d,5.5,2.5)
						Case OBJTYPE_BOO: Object_Enemy_Behaviour(o,p,d,6,5.5,5.5)
						Default: Object_Enemy_Behaviour(o,p,d,5.5,3)
					End Select
				EndIf
			Else
				Select o\ObjType
					Case OBJTYPE_KLAGEN: Object_Enemy_Behaviour(o,p,d,5.5,2.5)
					Case OBJTYPE_BOO: Object_Enemy_Behaviour(o,p,d,6,5.5,5.5)
					Default: Object_Enemy_Behaviour(o,p,d,5.5,3)
				End Select
			EndIf
		Case OBJTYPE_KIKI:
			Object_Enemy_Behaviour(o,p,d,4.5,3.5,2.3)
		Case OBJTYPE_COP,OBJTYPE_COPRACER:
			Object_Enemy_Behaviour(o,p,d,6.5,1)
		Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD:
			Object_Enemy_Behaviour(o,p,d,7.5,5.5,2.65)
		Case OBJTYPE_GUNNER
			Object_Enemy_Behaviour(o,p,d,7.5,5.5,0.25)
		Case OBJTYPE_BEETLE:
			Object_Enemy_Behaviour(o,p,d,5,3.5,1.5)
		Case OBJTYPE_BEETLESPARK,OBJTYPE_FLAPPERNEEDLE:
			Object_Enemy_Behaviour(o,p,d,10)
		Case OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES:
			Object_Enemy_Behaviour(o,p,d,14,5)
		Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
			Object_Enemy_Behaviour(o,p,d,4.5,2.5)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_AEROC:
			Object_Enemy_Behaviour(o,p,d,6.5,4,1.2)
		Case OBJTYPE_CHASER:
			Object_Enemy_Behaviour(o,p,d,3.375,2,0.1)
		Case OBJTYPE_FIGHTER:
			Object_Enemy_Behaviour(o,p,d,6.75,5,4,1)
		Case OBJTYPE_EGGROBO:
			Object_Enemy_Behaviour(o,p,d,3,1,0.5)
		Case OBJTYPE_ACHAOS:
			Object_Enemy_Behaviour(o,p,d,3.75,2,0.5)
		Case OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_CLUCKOID:
			Object_Enemy_Behaviour(o,p,d,6.125,3.75,4.25)
		Case OBJTYPE_ORBINAUT:
			Object_Enemy_Behaviour(o,p,d,2.5,1,1.25)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_SPONA:
			Object_Enemy_Behaviour(o,p,d,5,3,3.5)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_BOOSCARE,OBJTYPE_GHOST:
			Object_Enemy_Behaviour(o,p,d,5,3,3.5)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_ANTON,OBJTYPE_PENGUINATOR:
			Object_Enemy_Behaviour(o,p,d,9,3)
		Case OBJTYPE_AQUIS:
			Object_Enemy_Behaviour(o,p,d,3.75,3,0.75)
		Case OBJTYPE_BOMBIE:
			Object_Enemy_Behaviour(o,p,d,5,2,1)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_NEWTRON,OBJTYPE_SLICER,OBJTYPE_ASTERON:
			Object_Enemy_Behaviour(o,p,d,4,2.75,0.85)
			Select o\ObjType
				Case OBJTYPE_SLICER,OBJTYPE_ASTERON: Object_Enemy_SpecialBehaviour(o,p)
			End Select
		Case OBJTYPE_SNAILB:
			Object_Enemy_Behaviour(o,p,d,4,2.8,1.25)
		Case OBJTYPE_STEELION:
			Object_Enemy_Behaviour(o,p,d,6,3.75,0.75)
		Case OBJTYPE_BALKIRY:
			Object_Enemy_Behaviour(o,p,d,5,1,1)
		Case OBJTYPE_BURROBOT:
			EntityAlpha(o\Entity,1)
			Object_Enemy_Behaviour(o,p,d,6,2)
		Case OBJTYPE_CRAWL:
			Object_Enemy_Behaviour(o,p,d,4.48,3,2.5)
		Case OBJTYPE_DRAGONFLY,OBJTYPE_CATAKILLER:
			Object_Enemy_Behaviour(o,p,d,6)
		Case OBJTYPE_MADMOLE:
			Object_Enemy_Behaviour(o,p,d,4.75,4,7,3.25)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_MANTA:
			Object_Enemy_Behaviour(o,p,d,5,4,1.155)
		Case OBJTYPE_MUSHMEANIE,OBJTYPE_PATABATA:
			Object_Enemy_Behaviour(o,p,d,1)
		Case OBJTYPE_OCTUS:
			Object_Enemy_Behaviour(o,p,d,6.25,4.25,1.15,0.5)
		Case OBJTYPE_ZOOMER:
			Object_Enemy_Behaviour(o,p,d,5,4,1)
		Case OBJTYPE_BITER,OBJTYPE_TAKER:
			Object_Enemy_Behaviour(o,p,d,4.875,3.5,0.5)
		Case OBJTYPE_CRAWLER:
			EntityAlpha(o\Entity,1)
			Object_Enemy_Behaviour(o,p,d,5.75,2,0.5)
		Case OBJTYPE_BALLHOG:
			Object_Enemy_Behaviour(o,p,d,5,3,1.19)
		Case OBJTYPE_TECHNOSQU:
			Object_Enemy_Behaviour(o,p,d,5,0.5)
		Case OBJTYPE_E1000:
			Object_Enemy_Behaviour(o,p,d,5.125,3.25,0.65)
			Object_Enemy_SpecialBehaviour(o,p)
		Case OBJTYPE_WARRIOR,OBJTYPE_OAKSWORD,OBJTYPE_SNOWY:
			Object_Enemy_Behaviour(o,p,d,3.5,2,0.7)
		Case OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2:
			Object_Enemy_Behaviour(o,p,d,4,3,0.5)
		Case OBJTYPE_LEECH:
			Object_Enemy_Behaviour(o,p,d,4.5,0.125)
		Case OBJTYPE_WING:
			Object_Enemy_Behaviour(o,p,d,4,0.525)
		Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
			Object_Enemy_Behaviour(o,p,d,5,3.45,0.8)
		Case OBJTYPE_MANTIS:
			Object_Enemy_Behaviour(o,p,d,2)
		Case OBJTYPE_NEBULA:
			Object_Enemy_Behaviour(o,p,d,3,2,2.25)
		Case OBJTYPE_ROLLER:
			Object_Enemy_Behaviour(o,p,d,4,3,3)
		Case OBJTYPE_SHEEP:
			Object_Enemy_Behaviour(o,p,d,3,1)
		Case OBJTYPE_SPLATS:
			Object_Enemy_Behaviour(o,p,d,4,0.5)
		Case OBJTYPE_TOXO:
			Object_Enemy_Behaviour(o,p,d,5,3.5,0.75)
		Case OBJTYPE_WITCH1,OBJTYPE_WITCH2:
			Object_Enemy_Behaviour(o,p,d,6.5,4.5,0.5)
		Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA:
			Object_Enemy_BossBehaviour(o,p,d)
		Case OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
			Object_Enemy_Behaviour(o,p,d,2.5,2,1)
	End Select

End Function

	Function Object_Enemy_Behaviour(o.tObject, p.tPlayer, d.tDeltaTime, waitload#, wait#=0, attackwaitload#=0, attackwait#=0)
		If (o\Enemy\InRange Or o\Enemy\InRangeForced) And (Not(p\Action=ACTION_FREEZE)) Then
			Object_Enemy_DefaultBehaviour(o,p,d)
			If Not(o\Enemy\WaitTimer)>0 Then
				Select o\ObjType
					Case OBJTYPE_CRAWL,OBJTYPE_MADMOLE,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_BOOSCARE,OBJTYPE_GHOST,OBJTYPE_BALLHOG,OBJTYPE_ROLLER,OBJTYPE_TOXO,OBJTYPE_SLICER:
						If o\Enemy\AttackMode3=0 Then
							o\Enemy\AttackMode3=1
							o\Enemy\WaitTimer=wait#*secs#
							Return
						EndIf
				End Select
				o\Enemy\WaitTimer=waitload#*secs#
				Object_Enemy_MoveBehaviour_Initiate(o,p)
			ElseIf o\Enemy\WaitTimer>wait#*secs# Then
				Object_Enemy_MoveBehaviour(o,p,d)
			Else
				If attackwaitload#>0 Then
					Object_Enemy_AttackBehaviour_General(o,p,d)
					If Not(o\Enemy\AttackTimer)>0 Then
						o\Enemy\AttackTimer=attackwaitload#*secs#
						Object_Enemy_AttackBehaviour_Initiate(o,p)
					ElseIf o\Enemy\AttackTimer>attackwait#*secs# Then
						Object_Enemy_AttackBehaviour(o,p,d)
					Else
						Object_Enemy_NoMoveBehaviour(o,p,d)
					EndIf
				Else
					Object_Enemy_NoMoveBehaviour(o,p,d)
				EndIf
			EndIf
		Else
			Object_Enemy_NoBehaviour(o,p)
		EndIf
	End Function

	Function Object_Enemy_MoveBehaviour_Initiate(o.tObject, p.tPlayer)
		Select o\ObjType
			Case OBJTYPE_CHOPPER:
				If o\Enemy\FlyEnemyType Then Object_EnemyPointAtPlayer(o,p)
		End Select
	End Function

	Function Object_Enemy_MoveBehaviour(o.tObject, p.tPlayer, d.tDeltaTime)
		Select o\ObjType
			Case OBJTYPE_PAWN,OBJTYPE_GUNNER,OBJTYPE_MOTOBUG,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_CAMERON,OBJTYPE_ANTON,OBJTYPE_BOMBIE,OBJTYPE_BALKIRY,OBJTYPE_CRAWL,OBJTYPE_BITER,OBJTYPE_TAKER,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_WARRIOR,OBJTYPE_WING,OBJTYPE_SNOWY,OBJTYPE_SPLATS,OBJTYPE_HAMMER: o\Anim=2
			Case OBJTYPE_PAWNSHIELD,OBJTYPE_HAMMERSHIELD: If o\Enemy\Shield Then o\Anim=4 Else o\Anim=2
			Case OBJTYPE_PAWNGUN,OBJTYPE_HAMMERHAMMER: o\Anim=7
			Case OBJTYPE_PAWNSWORD: o\Anim=10
			Case OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_AQUIS: If o\Enemy\FlyEnemyType Then o\Anim=1 Else o\Anim=2
			Case OBJTYPE_HUNTER,OBJTYPE_OAKSWORD: o\Anim=3
			Case OBJTYPE_HUNTERSHIELD: If o\Enemy\Shield Then o\Anim=6 Else o\Anim=3
			Case OBJTYPE_PENGUINATOR: If o\Enemy\WaitTimer>6*secs# Then o\Anim=2 Else o\Anim=3
			Case OBJTYPE_SLICER: If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=3
			Case OBJTYPE_SPIKES: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=4
			Case OBJTYPE_ASTERON,OBJTYPE_MADMOLE,OBJTYPE_MUSHMEANIE: If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=2
			Case OBJTYPE_BUBBLSSPIKES: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_STEELION,OBJTYPE_OCTUS: If o\Enemy\Underwater=1 Then o\Anim=5 Else o\Anim=2
			Case OBJTYPE_BURROBOT:
				Select o\Enemy\AttackMode
					Case 0: o\Anim=4
					Case 1: o\Anim=3
					Default: o\Anim=2
				End Select
			Case OBJTYPE_DRAGONFLY: If o\Enemy\WaitTimer>3*secs# Then o\Anim=2 Else o\Anim=3
			Case OBJTYPE_CRAWLER:
				Select o\Enemy\AttackMode
					Case 0: o\Anim=4
					Case 1: o\Anim=2
					Default: o\Anim=1
				End Select
			Case OBJTYPE_E1000: If o\Enemy\FlyEnemyType Then o\Anim=3 Else o\Anim=2
			Case OBJTYPE_WARRIORGUN1: o\Anim=5
			Case OBJTYPE_WARRIORGUN2: o\Anim=8
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: If o\Enemy\SeenSonic Then o\Anim=3 Else o\Anim=1
			Case OBJTYPE_MANTIS:
				Select o\Enemy\AttackMode
					Case 1: o\Anim=2
					Case 2: o\Anim=3
					Default: o\Anim=1
				End Select
			Case OBJTYPE_SHEEP: If o\Enemy\AttackMode=0 Then o\Anim=4 Else o\Anim=2
			Default: o\Anim=1
		End Select
		Select o\ObjType
			Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD,OBJTYPE_HAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_HAMMERHAMMER:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.35)
				Object_PlayRobotSteps(o,3,7)
			Case OBJTYPE_GUNNER
				Object_PlayRobotSteps(o,6,13)
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.35)
			Case OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_WITCH1,OBJTYPE_WITCH2:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.025)
				Select o\ObjType
					Case OBJTYPE_FLAPPERNEEDLE:
						Object_Enemy_BlockBehaviour(o,p,3.5,7.5)
				End Select
			Case OBJTYPE_SPINA,OBJTYPE_SPANA:
				Object_EnemyPointAtPlayer(o,p)
				Select o\ObjType
					Case OBJTYPE_SPANA:
						Object_Enemy_BlockBehaviour(o,p,3.5,7.5)
				End Select
			Case OBJTYPE_MOTOBUG,OBJTYPE_ANTON,OBJTYPE_BOMBIE,OBJTYPE_PENGUINATOR,OBJTYPE_SPIKES,OBJTYPE_BURROBOT,OBJTYPE_CRAWLER,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_LEECH:
				Select o\ObjType
					Case OBJTYPE_BOMBIE:
						If EntityDistance(p\Objects\Entity,o\Entity)>40 Then
							TurnEntity o\Entity, 0, 1*d\Delta, 0
						Else
							Object_EnemyLookAtPlayer(o,p)
						EndIf
					Case OBJTYPE_PENGUINATOR:
						Object_EnemyLookAtPlayer(o,p)
						If o\Enemy\WaitTimer>6*secs# Then
							Object_PlayRobotSteps(o,3,11)
						Else
							If (Not(ChannelPlaying(o\Enemy\Channel_EnemyStep))) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_Grind,o\Entity)
						EndIf
					Case OBJTYPE_BURROBOT:
						If o\Enemy\AttackMode=2 Then
							Object_EnemyLookAtPlayer(o,p)
							If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor,o\Entity)
							Object_PlayRobotSteps(o,0,0)
						EndIf
					Case OBJTYPE_LEECH:
						Object_EnemyLookAtPlayer(o,p)
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_Curse,o\Entity)
					Case OBJTYPE_CRAWLER: Object_EnemyLookAtPlayer(o,p)
					Default:
						Object_EnemyLookAtPlayer(o,p)
						Select o\ObjType
							Case OBJTYPE_TECHNOSQU:
								If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor2,o\Entity)
							Default:
								If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor,o\Entity)
						End Select
						Object_PlayRobotSteps(o,0,0)
				End Select
				Select o\ObjType
					Case OBJTYPE_ANTON: Object_EnemyMoveToPlayer(o,p,d,0.58)
					Case OBJTYPE_BOMBIE: Object_EnemyMoveToPlayer(o,p,d,0.38) : Object_PlayRobotSteps(o,3,11)
					Case OBJTYPE_PENGUINATOR:
						If (Not(Game\PenguinatorMovingTimer>0)) Then
							Game\PenguinatorMovingTimer=1*secs#
							Game\PenguinatorToMove=o
						EndIf
						If Game\PenguinatorToMove=o Then
							Game\PenguinatorMovingTimer=1*secs#
							If o\Enemy\WaitTimer>6*secs# Then
								Object_EnemyMoveToPlayer(o,p,d,0.28)
							Else
								Object_EnemyMoveToPlayer(o,p,d,0.68)
							EndIf
						EndIf
					Case OBJTYPE_BURROBOT: If o\Enemy\AttackMode=2 Then Object_EnemyMoveToPlayer(o,p,d,0.58)
					Case OBJTYPE_RHINOTANK: Object_EnemyMoveToPlayer(o,p,d,0.38)
					Case OBJTYPE_TECHNOSQU: Object_EnemyMoveToPlayer(o,p,d,0.71)
					Case OBJTYPE_LEECH: Object_EnemyMoveToPlayer(o,p,d,0.15)
					Case OBJTYPE_CRAWLER:
					Default: Object_EnemyMoveToPlayer(o,p,d,0.48)
				End Select
			Case OBJTYPE_CATERKILLER,OBJTYPE_CAMERON,OBJTYPE_MADMOLE:
				Object_EnemyLookAtPlayer(o,p)
				Select o\ObjType
					Case OBJTYPE_CAMERON: o\Enemy\Shield=1
					Case OBJTYPE_CATERKILLER:
						o\Enemy\MayNotBeTargeted=True
						If ChannelPlaying(o\Enemy\Channel_EnemyState) Then StopChannel(o\Enemy\Channel_EnemyState)
				End Select
			Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_COP,OBJTYPE_COPRACER:
				Select o\ObjType
					Case OBJTYPE_COPRACER: Object_EnemyLookAtPlayer(o,p)
					Default: Object_EnemyPointAtPlayer(o,p)
				End Select
				Select o\ObjType
					Case OBJTYPE_BUZZBOMBER: Object_EnemyMoveToPlayer(o,p,d,0.46)
					Case OBJTYPE_COP,OBJTYPE_COPRACER: Object_EnemyMoveToPlayer(o,p,d,1.28)
					Default: Object_EnemyMoveToPlayer(o,p,d,0.76)
				End Select
			Case OBJTYPE_CHOPPER:
				If o\Enemy\FlyEnemyType Then
					Object_EnemyMoveToPlayer(o,p,d,0.4)
					Object_PlayRobotSteps(o,0,0)
				EndIf
			Case OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING:
				Object_EnemyLookAtPlayer(o,p)
				Select o\ObjType
					Case OBJTYPE_BEETLESPARK:
						Object_Enemy_BlockBehaviour(o,p,4.5,8.5)
				End Select
			Case OBJTYPE_JAWS:
				If o\Enemy\FlyEnemyType Then
					Object_EnemyPointAtPlayer(o,p)
					Object_EnemyMoveToPlayer(o,p,d,0.81)
					Object_PlayRobotSteps(o,0,0)
				EndIf
			Case OBJTYPE_GRABBER,OBJTYPE_KLAGEN,OBJTYPE_ACHAOSBLOB,OBJTYPE_BOO,OBJTYPE_GHOST:
				Select o\ObjType
					Case OBJTYPE_GRABBER:
						Object_EnemyLookAtPlayer(o,p)
						Object_EnemyMoveUp(o,p,d,0.11)
					Case OBJTYPE_KLAGEN:
						Object_EnemyLookAtPlayer(o,p)
						Object_EnemyMoveToPlayer(o,p,d,0.31)
					Case OBJTYPE_ACHAOSBLOB:
						Object_EnemyPointAtPlayer(o,p)
						Object_EnemyMoveToPlayer(o,p,d,0.43)
					Case OBJTYPE_BOO:
						Object_EnemyLookAtPlayer(o,p)
						Object_EnemyMoveUpAndDown(o,p,d,2,2,0.35)
						Object_EnemyMoveToPlayer(o,p,d,0.55)
					Case OBJTYPE_GHOST:
						Object_EnemyLookAtPlayer(o,p)
						Object_EnemyMoveUpAndDown(o,p,d,1,1,0.15)
						Object_EnemyMoveToPlayer(o,p,d,0.3)
				End Select
				o\Enemy\MayNotBeTargeted=False
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.23)
			Case OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.48)
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor2,o\Entity)
				Object_PlayRobotSteps(o,0,0)
			Case OBJTYPE_CHASER:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveUp(o,p,d,-1)
				o\Enemy\FlyEnemyType=False
				If o\Enemy\InRange Then o\Enemy\InRangeForced=False
			Case OBJTYPE_FIGHTER:
				Object_EnemyLookAtPlayer(o,p)
				If ChannelPlaying(o\Enemy\Channel_EnemyState) Then StopChannel(o\Enemy\Channel_EnemyState)
			Case OBJTYPE_SPONA:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.12)
			Case OBJTYPE_BATBOT:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.32)
				Object_EnemyMoveUpAndDown(o,p,d,2,2,0.42)
			Case OBJTYPE_STEELION:
				If o\Enemy\Underwater=1 Then
					Object_EnemyPointAtPlayer(o,p)
					Object_EnemyMoveToPlayer(o,p,d,1.02)
				Else
					Object_EnemyLookAtPlayer(o,p)
					Object_EnemyMoveToPlayer(o,p,d,0.52)
					Object_PlayRobotSteps(o,3,11)
				EndIf
			Case OBJTYPE_BALKIRY:
				Object_EnemyLookAtPlayer(o,p)
				If o\Position\y#>p\Objects\Position\y#+4 Then
					Object_EnemyMoveUp(o,p,d,-0.45)
				ElseIf o\Position\y#<p\Objects\Position\y# Then
					Object_EnemyMoveUp(o,p,d,0.25)
				EndIf
				Object_EnemyMoveToPlayer(o,p,d,0.72)
			Case OBJTYPE_CRAWL:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.48)
				Object_PlayRobotSteps(o,5,13)
			Case OBJTYPE_DRAGONFLY:
				o\Enemy\InRangeForced=True
				Object_EnemyLookAtPlayer(o,p)
				If o\Enemy\WaitTimer>3*secs# Then
					Object_EnemyMoveUp(o,p,d,0.25)
				Else
					Object_EnemyMoveUp(o,p,d,-0.25)
				EndIf
				If o\InView Then o\Enemy\ShouldSpawnMissile=True
			Case OBJTYPE_MANTA:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.58)
			Case OBJTYPE_MUSHMEANIE:
				If o\Position\y#<o\InitialPosition\y#+30 Then
					If (Not(o\Enemy\AttackTimer>0)) Then
						o\Enemy\FlyEnemyType=True
						If o\Enemy\WaitTimer>0.5*secs# Then
							If o\Enemy\WaitTimer>0.9*secs# Then
								If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_Ninja,o\Entity)
							EndIf
							Object_EnemyMoveUp(o,p,d,0.68)
						Else
							Object_EnemyMoveUp(o,p,d,-0.68)
						EndIf
					EndIf
				Else
					o\Enemy\FlyEnemyType=False
					o\Enemy\AttackTimer=1*secs#
				EndIf
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.48)
			Case OBJTYPE_OCTUS:
				Object_EnemyLookAtPlayer(o,p)
				o\Enemy\FlyEnemyType=True
				If o\Position\y#<o\InitialPosition\y#+20 Then Object_EnemyMoveUp(o,p,d,0.37)
			Case OBJTYPE_PATABATA:
				TurnEntity o\Entity, 0, 0.75*d\Delta, 0
				Object_EnemyMoveToPlayer(o,p,d,0.32)
			Case OBJTYPE_ZOOMER:
				Object_EnemyLookAtPlayer(o,p)
				If o\Enemy\AttackMode>0 Then
					If o\Position\y#<o\InitialPosition\y# Then
						Object_EnemyMoveUp(o,p,d,1)
					Else
						o\Enemy\AttackMode=0
					EndIf
				EndIf
				Object_EnemyMoveToPlayer(o,p,d,0.42)
			Case OBJTYPE_BITER:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.4)
				Object_PlayRobotSteps(o,3,11)
			Case OBJTYPE_TAKER,OBJTYPE_WING:
				Object_EnemyLookAtPlayer(o,p)
				Select o\ObjType
					Case OBJTYPE_WING:
						Object_EnemyMoveToPlayer(o,p,d,0.1)
						Object_EnemyMoveUpAndDown(o,p,d,18,-16,0.15)
					Default:
						Object_EnemyMoveToPlayer(o,p,d,0.3)
						Object_EnemyMoveUpAndDown(o,p,d,18,-16,0.3)
				End Select
			Case OBJTYPE_E1000:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.68)
				Object_PlayRobotSteps(o,3,11)
				If o\Enemy\FlyEnemyType Then Object_EnemyMoveUpAndDown(o,p,d,10,-8,0.7)
			Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_SNOWY:
				Object_EnemyLookAtPlayer(o,p)
				Select o\ObjType
					Case OBJTYPE_OAKSWORD: Object_EnemyMoveToPlayer(o,p,d,0.25)
					Case OBJTYPE_SNOWY: Object_EnemyMoveToPlayer(o,p,d,0.15)
					Default: Object_EnemyMoveToPlayer(o,p,d,0.45)
				End Select
				Object_PlayRobotSteps(o,3,11)
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
				If o\Enemy\SeenSonic Then
					Object_EnemyLookAtPlayer(o,p)
					Object_EnemyMoveToPlayer(o,p,d,0.79)
					Object_PlayRobotSteps(o,3,11)
				EndIf
			Case OBJTYPE_CATAKILLER:
				o\Enemy\InRangeForced=True
				TurnEntity o\Entity, 0, 1.2*d\Delta, 0				
				Object_EnemyMoveToPlayer(o,p,d,0.3)
				If o\InView Then
					o\Enemy\ShouldSpawnMissile=True
					If Not(o\Enemy\AttackTimer>0) Then
						o\Enemy\AttackTimer=1*secs#
						obj.tObject = Object_EnemyMissile_Create(o, -o\ObjType, o\Position\x#, o\Position\y#, o\Position\z#, 0, EntityYaw(o\Entity), 0)
					EndIf
				EndIf
			Case OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_CLUCKOID,OBJTYPE_ROLLER:
				Object_EnemyLookAtPlayer(o,p)
				StopChannel(o\Enemy\Channel_EnemyState)
				If o\ObjType=OBJTYPE_ROLLER Then o\Enemy\FlyEnemyType=False
			Case OBJTYPE_MANTIS:
				o\Enemy\InRangeForced=True
				Object_EnemyLookAtPlayer(o,p)
				Select o\Enemy\AttackMode
					Case 0:
						o\Enemy\FlyEnemyType=False
						If (Not(o\Enemy\WaitTimer2>0)) And Abs(o\Position\x#-p\Objects\Position\x#)<40 And Abs(o\Position\z#-p\Objects\Position\z#)<40 Then
							o\Enemy\AttackMode=1
							EmitSmartSound(Sound_Ninja,o\Entity)
							o\Enemy\WaitTimer2=0.2375*secs#
						EndIf
					Case 1:
						o\Enemy\FlyEnemyType=True
						If o\Position\y#<o\InitialPosition\y#+20 Then Object_EnemyMoveUp(o,p,d,1.7)
						If Not(o\Enemy\WaitTimer2>0) Then
							o\Enemy\AttackMode=2
							o\Enemy\WaitTimer2=0.7*secs#
						EndIf
					Case 2:
						o\Enemy\FlyEnemyType=True
						If o\Position\y#<o\InitialPosition\y#+20 Then
							Object_EnemyMoveUp(o,p,d,1.7)
						ElseIf (Not(o\Enemy\WaitTimer2>0)) Then
							o\Enemy\AttackMode=0
							o\Enemy\WaitTimer2=1.25*secs#
						EndIf
				End Select
			Case OBJTYPE_SHEEP:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.32)
				Object_PlayRobotSteps(o,3,11)
			Case OBJTYPE_SPLATS:
				Object_EnemyLookAtPlayer(o,p)
				If o\Enemy\Frame>5 Then Object_EnemyMoveToPlayer(o,p,d,0.38)
			Case OBJTYPE_CRABMEAT,OBJTYPE_SPINY,OBJTYPE_KIKI,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_EGGROBO,OBJTYPE_ACHAOS,OBJTYPE_BOOSCARE,OBJTYPE_NEWTRON,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_ASTERON,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BALLHOG,OBJTYPE_NEBULA,OBJTYPE_TOXO,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2:
				Object_EnemyLookAtPlayer(o,p)
			Case OBJTYPE_ORBINAUT:
				Object_EnemyPointAtPlayer(o,p)
		End Select
	End Function

	Function Object_Enemy_NoMoveBehaviour(o.tObject, p.tPlayer, d.tDeltaTime)
		Select o\ObjType
			Case OBJTYPE_MOTOBUG,OBJTYPE_ANTON,OBJTYPE_BOMBIE,OBJTYPE_PENGUINATOR,OBJTYPE_SPIKES,OBJTYPE_BURROBOT,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU:
				If o\Enemy\WaitTimer>2*secs# Then
					Object_Enemy_NoBehaviour(o,p)
				Else
					Select o\ObjType
						Case OBJTYPE_MOTOBUG,OBJTYPE_RHINOTANK: o\Anim=3
						Case OBJTYPE_SPIKES: If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=3
						Default: o\Anim=1
					End Select
					Object_EnemyLookAtPlayer(o,p)
					Select o\ObjType
						Case OBJTYPE_BOMBIE,OBJTYPE_PENGUINATOR,OBJTYPE_TECHNOSQU:
						Default:
							If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor,o\Entity)
							Object_PlayRobotSteps(o,0,0)
					End Select
				EndIf
			Case OBJTYPE_CATERKILLER,OBJTYPE_CAMERON,OBJTYPE_MADMOLE:
				Select o\ObjType
					Case OBJTYPE_CAMERON: o\Enemy\Shield=0
					Case OBJTYPE_CATERKILLER: o\Enemy\MayNotBeTargeted=False
				End Select
				Object_Enemy_NoBehaviour(o,p)
			Case OBJTYPE_GRABBER,OBJTYPE_KLAGEN,OBJTYPE_ACHAOSBLOB,OBJTYPE_BOO:
				Object_EnemyLookAtPlayer(o,p)
				If o\ObjType=OBJTYPE_GRABBER Then Object_EnemyMoveUp(o,p,d,-0.11)
				o\Enemy\MayNotBeTargeted=False
			Case OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES:
				If o\Enemy\WaitTimer>3*secs# Then
					Object_Enemy_NoBehaviour(o,p)
				Else
					o\Anim=1
					Object_EnemyLookAtPlayer(o,p)
					If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor2,o\Entity)
					Object_PlayRobotSteps(o,0,0)
				EndIf
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
				If o\Enemy\AttackMode=0 And (Not(o\Enemy\AttackTimer)>0) Then
					o\Enemy\AttackTimer=0.2*secs#
					Object_Enemy_AttackBehaviour_Initiate(o,p)
					o\Enemy\AttackMode=1
				EndIf
			Case OBJTYPE_MUSHMEANIE:
				o\Enemy\FlyEnemyType=False
			Case OBJTYPE_OCTUS:
				o\Enemy\FlyEnemyType=True
			Case OBJTYPE_BOOSCARE,OBJTYPE_STEELION,OBJTYPE_ZOOMER,OBJTYPE_GHOST,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2:
				Object_EnemyLookAtPlayer(o,p)
			Case OBJTYPE_BITER,OBJTYPE_TAKER,OBJTYPE_CRAWLER,OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_OAKSWORD,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_SNOWY,OBJTYPE_FCANNON3:
			Default:
				Object_Enemy_NoBehaviour(o,p)
		End Select
	End Function

	Function Object_Enemy_AttackBehaviour_Initiate(o.tObject, p.tPlayer)
		Select o\ObjType
			Case OBJTYPE_WARRIORGUN1,OBJTYPE_HAMMERHAMMER: o\Anim=6
			Case OBJTYPE_PAWNSWORD,OBJTYPE_WARRIORGUN2: o\Anim=9
			Case  OBJTYPE_GUNNER: o\Anim=3
			Case OBJTYPE_CRABMEAT,OBJTYPE_SPINY,OBJTYPE_KIKI,OBJTYPE_HUNTER,OBJTYPE_CHASER,OBJTYPE_EGGROBO,OBJTYPE_NEWTRON,OBJTYPE_BALLHOG,OBJTYPE_WITCH1,OBJTYPE_WITCH2: o\Anim=2
			Case OBJTYPE_HUNTERSHIELD: If o\Enemy\Shield Then o\Anim=5 Else o\Anim=2
			Case OBJTYPE_AQUIS: If o\Enemy\FlyEnemyType Then o\Anim=3 Else o\Anim=2
			Case OBJTYPE_SLICER,OBJTYPE_ROLLER: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=3
			Case OBJTYPE_ASTERON,OBJTYPE_MADMOLE,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3: If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=2
			Case OBJTYPE_BUBBLSSPIKES: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_STEELION,OBJTYPE_OCTUS: If o\Enemy\Underwater=1 Then o\Anim=6 Else o\Anim=3
			Case OBJTYPE_BALKIRY,OBJTYPE_TAKER,OBJTYPE_CRAWLER: o\Anim=3
			Case OBJTYPE_ZOOMER:
				Select o\Enemy\AttackMode
					Case 0: o\Anim=1
					Case 1: o\Anim=2
					Default: o\Anim=3
				End Select
			Case OBJTYPE_BITER: If o\Enemy\AttackMode=0 Then o\Anim=3 Else o\Anim=4
			Case OBJTYPE_E1000: If o\Enemy\FlyEnemyType Then o\Anim=3 Else o\Anim=1
			Case OBJTYPE_WARRIOR: o\Anim=3
			Case OBJTYPE_OAKSWORD,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: o\Anim=4
			Case OBJTYPE_SNOWY: If o\Enemy\AttackMode=0 Then o\Anim=3 Else o\Anim=4
			Default: o\Anim=1
		End Select
		Select o\ObjType
			Case OBJTYPE_PAWNGUN,OBJTYPE_GUNNER,OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER,OBJTYPE_FLAPPERBOMB,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_CRABMEAT,OBJTYPE_SPINY,OBJTYPE_KIKI,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_EGGROBO,OBJTYPE_CAMERON,OBJTYPE_ACHAOS,OBJTYPE_ORBINAUT,OBJTYPE_SPONA,OBJTYPE_NEWTRON,OBJTYPE_SNAILB,OBJTYPE_STEELION,OBJTYPE_MANTA,OBJTYPE_OCTUS,OBJTYPE_E1000,OBJTYPE_BALLHOG,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_NEBULA,OBJTYPE_TOXO,OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE,OBJTYPE_HAMMERHAMMER,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3:
				o\Enemy\ShouldSpawnMissile=True
				Select o\ObjType
					Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA,OBJTYPE_STEELION,OBJTYPE_TOXO,OBJTYPE_HAMMERHAMMER:
					Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_SPINY,OBJTYPE_CAMERON: EmitSmartSound(Sound_EnemyShot2,o\Entity)
					Case OBJTYPE_FLAPPERBOMB,OBJTYPE_CRABMEAT,OBJTYPE_MANTA,OBJTYPE_BALLHOG,OBJTYPE_NEBULA,OBJTYPE_SPRINKLR,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3: EmitSmartSound(Sound_EnemyCannon,o\Entity)
					Case OBJTYPE_KIKI: EmitSmartSound(Sound_EnemyThrow,o\Entity)
					Case OBJTYPE_HUNTER,OBJTYPE_GUNNER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_HORNET3,OBJTYPE_HORNET6: EmitSmartSound(Sound_EnemyShot3,o\Entity)
					Case OBJTYPE_AEROC: EmitSmartSound(Sound_EnemyShot4,o\Entity)
					Case OBJTYPE_NEWTRON,OBJTYPE_SNAILB: EmitSmartSound(Sound_EnemyShotPoof,o\Entity)
					Case OBJTYPE_OCTUS: EmitSmartSound(Sound_Gum,o\Entity)
					Case OBJTYPE_E1000,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: EmitSmartSound(Sound_Shotgun2,o\Entity)
					Default: EmitSmartSound(Sound_EnemyShot,o\Entity)
				End Select
			Case OBJTYPE_PAWNSWORD:
				Object_EnemyLookAtPlayer(o,p)
				EmitSmartSound(Sound_EnemyLaugh,o\Entity)
			Case OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST:
				EmitSmartSound(Sound_Ghost,o\Entity)
			Case OBJTYPE_AQUIS:
				If o\Enemy\FlyEnemyType Then
					o\Enemy\ShouldSpawnMissile=True
					EmitSmartSound(Sound_EnemyShot2,o\Entity)
				EndIf
			Case OBJTYPE_SLICER,OBJTYPE_ASTERON:
				If o\Enemy\AttackMode=0 Then
					o\Enemy\AttackMode=1
					o\Enemy\ShouldSpawnMissile=True
					EmitSmartSound(Sound_Grab,o\Entity)
					Select o\ObjType
						Case OBJTYPE_SLICER: o\Enemy\WaitTimer2=6.25*secs#
						Case OBJTYPE_ASTERON: o\Enemy\WaitTimer2=4.25*secs#
					End Select
				EndIf
			Case OBJTYPE_BALKIRY:
				EmitSmartSound(Sound_GlideStart3,o\Entity)
			Case OBJTYPE_BITER:
				o\Enemy\AttackMode=Rand(0,3)
				If o\Enemy\AttackMode=0 Then
					Select(Rand(1,2))
					Case 1: If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterBite,o\Entity)
					Case 2: If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterRoar1,o\Entity)
					End Select
				Else
					o\Enemy\ShouldSpawnMissile=True
					If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterRoar1,o\Entity)
					EmitSmartSound(Sound_FireDash,p\Objects\Entity)
				EndIf
			Case OBJTYPE_TAKER:
				If o\Enemy\AttackMode=0 Then
					o\Enemy\ShouldSpawnMissile=True
					If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterFind,o\Entity)
					EmitSmartSound(Sound_FireDash,p\Objects\Entity)
					o\Enemy\AttackMode=1
				Else
					If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterFind,o\Entity)
				EndIf
			Case OBJTYPE_CRAWLER:
				If o\Enemy\AttackMode>1 Then
					If (Not(o\Enemy\WaitTimer2>0)) And o\Enemy\Frame>=5 Then
						o\Enemy\WaitTimer2=2.5*secs#
						o\Enemy\ShouldSpawnMissile=True
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterRoar2,o\Entity)
						EmitSmartSound(Sound_FireDash,p\Objects\Entity)
					Else
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_MonsterRoar2,o\Entity)
					EndIf
				Else
					o\Enemy\WaitTimer=0
				EndIf
			Case OBJTYPE_WARRIOR:
				EmitSmartSound(Sound_Punch,p\Objects\Entity)
			Case OBJTYPE_OAKSWORD:
				EmitSmartSound(Sound_EnemyHit,p\Objects\Entity)
				EmitSmartSound(Sound_Blade,p\Objects\Entity)
			Case OBJTYPE_ROLLER:
				o\Enemy\AttackMode=0
				o\Enemy\WaitTimer2=0.5*secs#
			Case OBJTYPE_SNOWY:
				o\Enemy\AttackMode=Rand(0,1)
				EmitSmartSound(Sound_PunchBig,p\Objects\Entity)
		End Select
	End Function

	Function Object_Enemy_AttackBehaviour_General(o.tObject, p.tPlayer, d.tDeltaTime)
		Select o\ObjType
			Case OBJTYPE_CRABMEAT,OBJTYPE_SPINY,OBJTYPE_KIKI,OBJTYPE_NEWTRON,OBJTYPE_SLICER,OBJTYPE_SNAILB,OBJTYPE_ASTERON:
				Object_EnemyLookAtPlayer(o,p)
				
			Case OBJTYPE_CHASER:
				Object_EnemyMoveUp(o,p,d,0.54)
				o\Enemy\FlyEnemyType=True
				o\Enemy\InRangeForced=True
			Case OBJTYPE_STEELION:
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_WindBlow,o\Entity)
			Case OBJTYPE_CRAWL:
				If p\Objects\Position\y#>o\Position\y#+1 Then o\Enemy\AttackMode=1 Else o\Enemy\AttackMode=0
			Case OBJTYPE_OAKSWORD:
				o\Enemy\ShouldSpawnMissile=True
			Case OBJTYPE_WITCH1:
				Game\BishopMagicTimer=0.1*secs#
			Case OBJTYPE_WITCH2:
				Object_Enemy_SpecialBehaviour_RingDrain(p)
		End Select
	End Function

	Function Object_Enemy_AttackBehaviour(o.tObject, p.tPlayer, d.tDeltaTime)
		Select o\ObjType
			Case OBJTYPE_PAWNSWORD,OBJTYPE_WARRIORGUN2: o\Anim=9
			Case OBJTYPE_CATERKILLER,OBJTYPE_BALKIRY,OBJTYPE_TAKER,OBJTYPE_CRAWLER,OBJTYPE_WARRIOR: o\Anim=3
			Case OBJTYPE_KIKI: If o\Enemy\AttackTimer>1.875*secs# Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_HUNTERSHIELD: If o\Enemy\Shield Then o\Anim=4 Else o\Anim=1
			Case OBJTYPE_OAKSWORD,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: o\Anim=4
			Case OBJTYPE_FIGHTER,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_BOOSCARE,OBJTYPE_NEWTRON,OBJTYPE_BALLHOG,OBJTYPE_CLUCKOID,OBJTYPE_WITCH1,OBJTYPE_WITCH2,OBJTYPE_GHOST: o\Anim=2
			Case OBJTYPE_AQUIS: If o\Enemy\FlyEnemyType Then o\Anim=3 Else o\Anim=2
			Case OBJTYPE_SLICER,OBJTYPE_ROLLER: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=3
			Case OBJTYPE_ASTERON,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2,OBJTYPE_FCANNON3: If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=2
			Case OBJTYPE_BUBBLSSPIKES: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_STEELION,OBJTYPE_OCTUS: If o\Enemy\Underwater=1 Then o\Anim=6 Else o\Anim=3
			Case OBJTYPE_CRAWL: If o\Enemy\AttackMode=0 Then o\Anim=3 Else o\Anim=4
			Case OBJTYPE_MADMOLE: If o\Enemy\AttackMode=2 Then o\Anim=2 Else o\Anim=3
				
			Case OBJTYPE_ZOOMER:
				Select o\Enemy\AttackMode
					Case 0: o\Anim=1
					Case 1: o\Anim=2
					Default: o\Anim=3
				End Select
			Case OBJTYPE_BITER: If o\Enemy\AttackMode=0 Then o\Anim=3 Else o\Anim=4
			Case OBJTYPE_E1000: If o\Enemy\FlyEnemyType Then o\Anim=3 Else o\Anim=1
			Case OBJTYPE_WARRIORGUN1,OBJTYPE_HAMMERHAMMER: o\Anim=6
			Case OBJTYPE_SNOWY: If o\Enemy\AttackMode=0 Then o\Anim=3 Else o\Anim=4
			Default: o\Anim=1
		End Select
		Select o\ObjType
			Case OBJTYPE_PAWNSWORD:
				Object_EnemyMoveToPlayer(o,p,d,1.05)
				Object_PlayRobotSteps(o,3,7)
			Case OBJTYPE_CATERKILLER:
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_EnemyBite,o\Entity)
				o\Enemy\MayNotBeTargeted=False
			Case OBJTYPE_CAMERON:
				o\Enemy\Shield=0
			Case OBJTYPE_FIGHTER:
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_EnemyBite,o\Entity)
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.88)
			Case OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_CLUCKOID:
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_WindBlow,o\Entity)
				Object_EnemyLookAtPlayer(o,p)
				If EntityDistance(o\Entity,p\Objects\Entity)<20 And (Not(p\WasGrabbedTimer>0)) And Game\Invinc=0 Then
					Player_ConvertGroundToAir(p) : p\Motion\Ground = False
					p\Flags\HomingLocked = False : p\Flags\Targeter=0
					p\Animation\Direction#=o\Rotation\y#
					Player_SetSpeed(p,-2)
					Player_SetSpeedY(p,1)
					If o\ObjType=OBJTYPE_TYPHOONF And (Not(p\Character=CHAR_BAR)) Then
						p\WasGrabbedTimer=3*secs#
						Player_Action_Freeze_Initiate(p)
					Else
						p\WasGrabbedTimer=1.5*secs#
						EmitSmartSound(Sound_Ninja,p\Objects\Entity)
					EndIf
					ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
				EndIf
			Case OBJTYPE_ORBINAUT:
				Object_EnemyPointAtPlayer(o,p)
			Case OBJTYPE_SPONA:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.12)
			Case OBJTYPE_COP,OBJTYPE_COPRACER:
				Object_EnemyPointAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,1.28)
			Case OBJTYPE_BOO:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveUpAndDown(o,p,d,2,2,0.35)
				Object_EnemyMoveToPlayer(o,p,d,0.55)
			Case OBJTYPE_BOMBIE:
				If EntityDistance(p\Objects\Entity,o\Entity)<40 Then o\Enemy\SelfDestruct=True
			Case OBJTYPE_STEELION:
				If o\Enemy\Underwater=1 Then
					Object_EnemyPointAtPlayer(o,p)
				Else
					Object_EnemyLookAtPlayer(o,p)
				EndIf
			Case OBJTYPE_BALKIRY:
				Object_EnemyLookAtPlayer(o,p)
				If o\Position\y#>p\Objects\Position\y#+4 Then
					Object_EnemyMoveUp(o,p,d,-0.75)
				ElseIf o\Position\y#<p\Objects\Position\y# Then
					Object_EnemyMoveUp(o,p,d,0.45)
				EndIf
				Object_EnemyMoveToPlayer(o,p,d,1.32)
			Case OBJTYPE_MADMOLE:
				Object_EnemyLookAtPlayer(o,p)
				If o\Enemy\AttackMode=0 Then
					If o\Enemy\Frame>=9 Then
						EmitSmartSound(Sound_EnemyThrow,o\Entity)
						o\Enemy\AttackMode=1
						o\Enemy\ShouldSpawnMissile=True
						o\Enemy\WaitTimer2=2*secs#
					EndIf
				ElseIf o\Enemy\AttackMode=1 Then
					If o\Enemy\Frame>=15 Then o\Enemy\AttackMode=2
				EndIf
			Case OBJTYPE_MANTA:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.58)
			Case OBJTYPE_OCTUS:
				Object_EnemyLookAtPlayer(o,p)
				o\Enemy\FlyEnemyType=True
				If o\Position\y#<o\InitialPosition\y#+20 Then Object_EnemyMoveUp(o,p,d,0.37)
			Case OBJTYPE_ZOOMER:
				Object_EnemyLookAtPlayer(o,p)
				If o\Enemy\AttackMode=0 Then
					If ((Abs(p\Objects\Position\x# - o\Position\x#) < 10) And (Abs(p\Objects\Position\z# - o\Position\z#) < 10)) Then
						o\Enemy\AttackMode=1
					EndIf
				ElseIf o\Enemy\AttackMode=1 Then
					If o\Enemy\Frame>=15 Then
						o\Enemy\AttackMode=2
						EmitSmartSound(Sound_GlideStart3,o\Entity)
					EndIf
				Else
					Object_EnemyMoveUp(o,p,d,-2)
				EndIf
			Case OBJTYPE_TAKER:
				Object_EnemyLookAtPlayer(o,p)
				Object_EnemyMoveToPlayer(o,p,d,0.3)
				Object_EnemyMoveUpAndDown(o,p,d,18,-16,0.3)
			Case OBJTYPE_E1000:
				Object_EnemyLookAtPlayer(o,p)
				If o\Enemy\FlyEnemyType Then
					Object_EnemyMoveToPlayer(o,p,d,0.7)
					Object_EnemyMoveUpAndDown(o,p,d,10,-8,0.7)
				EndIf
			Case OBJTYPE_ROLLER:
				Object_EnemyLookAtPlayer(o,p)
				Select o\Enemy\AttackMode
					Case 0:
						o\Enemy\FlyEnemyType=False
						If Not(o\Enemy\WaitTimer2>0) Then o\Enemy\AttackMode=Rand(1,2)
					Case 1:
						o\Enemy\FlyEnemyType=False
						Object_EnemyMoveToPlayer(o,p,d,0.8)
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_Pinball,o\Entity)
					Case 2:
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_Pinball,o\Entity)
						Object_EnemyMoveToPlayer(o,p,d,0.65)
						If o\Enemy\WaitTimer>2*secs# Then
							o\Enemy\FlyEnemyType=True
							Object_EnemyMoveUp(o,p,d,0.2)
						ElseIf o\Enemy\WaitTimer>1*secs# Then
							o\Enemy\FlyEnemyType=True
							Object_EnemyMoveUp(o,p,d,-0.2)
						Else
							o\Enemy\FlyEnemyType=False
						EndIf
				End Select
			Case OBJTYPE_SNOWY:
				o\Enemy\ShouldSpawnMissile=True
			Case OBJTYPE_HAMMERHAMMER:
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\AttackMode=0
				If o\Enemy\AttackMode>0 Then
					o\Enemy\AttackMode=o\Enemy\AttackMode-timervalue#
				Else
					o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_EnemyHammer,o\Entity)
					o\Enemy\AttackMode=0.575*secs#
				EndIf
			Case OBJTYPE_WITCH1,OBJTYPE_WITCH2:
				If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_EnemyMagic,o\Entity)
			Case OBJTYPE_BOOSCARE,OBJTYPE_CRAWL,OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_BALLHOG,OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO,OBJTYPE_NEBULA,OBJTYPE_TOXO,OBJTYPE_GHOST,OBJTYPE_FCANNON1,OBJTYPE_FCANNON2:
				Object_EnemyLookAtPlayer(o,p)
			Default:
				Object_Enemy_NoMoveBehaviour(o,p,d)
		End Select
	End Function

	Function Object_Enemy_DefaultBehaviour(o.tObject, p.tPlayer, d.tDeltaTime)
		Select o\ObjType
			Case OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_SEARCHER,OBJTYPE_EGGHUNTER,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_GRABBER,OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_BEETLE,OBJTYPE_BEETLEMONO,OBJTYPE_BEETLESPARK,OBJTYPE_BEETLESPRING,OBJTYPE_HORNET3,OBJTYPE_HORNET6,OBJTYPE_AEROC,OBJTYPE_CHASER,OBJTYPE_KLAGEN,OBJTYPE_EGGROBO,OBJTYPE_COP,OBJTYPE_COPRACER,OBJTYPE_SPONA,OBJTYPE_ORBINAUT,OBJTYPE_BATBOT,OBJTYPE_BUBBLS,OBJTYPE_BUBBLSSPIKES,OBJTYPE_BALKIRY,OBJTYPE_DRAGONFLY,OBJTYPE_MANTA,OBJTYPE_OCTUS,OBJTYPE_PATABATA,OBJTYPE_E1000,OBJTYPE_NEBULA:
				If (Not(ChannelPlaying(o\Enemy\Channel_EnemyStep))) And o\InView Then
					Select o\ObjType
						Case OBJTYPE_SPINA,OBJTYPE_SPANA,OBJTYPE_SPONA,OBJTYPE_GRABBER,OBJTYPE_MANTA,OBJTYPE_NEBULA:
							o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyFly,o\Entity)
						Case OBJTYPE_BUZZBOMBER,OBJTYPE_BUZZER,OBJTYPE_DRAGONFLY,OBJTYPE_PATABATA:
							o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyBuzz,o\Entity)
						Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD,OBJTYPE_E1000:
							If o\Anim=3 Or o\Anim=6 Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyHover,o\Entity)
						Case OBJTYPE_COP:
							o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyTraffic,o\Entity)
						Case OBJTYPE_COPRACER:
							o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyMotor2,o\Entity)
						Case OBJTYPE_BATBOT:
							If o\Anim=1 And (o\Enemy\Frame=1 Or o\Enemy\Frame=11) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_FlyWings,o\Entity)
						Case OBJTYPE_OCTUS:
							If Not(o\Anim=1 Or o\Anim=4) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyFly,o\Entity)
						Default:
							o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyHover,o\Entity)
					End Select
				EndIf
			Case OBJTYPE_JAWS:
				If o\Enemy\FlyEnemyType Then ParticleTemplate_Call(o\Enemy\WaterParticle, PARTICLE_OBJECT_WATERSPLASH, o\Entity)
			Case OBJTYPE_BOO,OBJTYPE_BOOSCARE,OBJTYPE_GHOST:
				If EntityDistance(o\Entity,p\Objects\Entity)>50 Then
					EntityAlpha(o\Entity,0.125)
				Else
					Select o\ObjType
						Case OBJTYPE_BOOSCARE,OBJTYPE_GHOST:
							If o\Anim=2 Then
								EntityAlpha(o\Entity,Max#(0.25,Min#(1.0,(50.0-EntityDistance(o\Entity,p\Objects\Entity))/30.0)))
							Else
								EntityAlpha(o\Entity,0.25)
							EndIf
						Default:
							EntityAlpha(o\Entity,Max#(0.25,Min#(1.0,(50.0-EntityDistance(o\Entity,p\Objects\Entity))/30.0)))
					End Select
				EndIf
			Case OBJTYPE_BURROBOT:
				Select o\Enemy\AttackMode
					Case 0:
						o\Enemy\MayNotBeTargeted=True
						o\Enemy\FlyEnemyType=False
						If EntityDistance(o\Entity,p\Objects\Entity)<55 Then
							o\Enemy\AttackMode=1
							o\Enemy\WaitTimer2=0.54*secs#
							EmitSmartSound(Sound_KnuxStomp,o\Entity)
							Object_Pieces_Create(False,OBJTYPE_ROCK,0,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,0.65,0,False,1)
						EndIf
					Case 1:
						o\Enemy\MayNotBeTargeted=False
						o\Enemy\FlyEnemyType=True
						Object_EnemyLookAtPlayer(o,p)
						Object_EnemyMoveUp(o,p,d,0.458)
						Object_EnemyMoveToPlayer(o,p,d,0.2)
						If (Not(o\Enemy\WaitTimer2>0)) Or o\InView=False Then
							o\Enemy\AttackMode=2
						EndIf
					Case 2:
						o\Enemy\MayNotBeTargeted=False
						o\Enemy\FlyEnemyType=False
				End Select
				If o\Anim=2 Then PositionTexture(o\ExtraTexture, 0, 0.005*Game\Gameplay\Time)
			Case OBJTYPE_BITER,OBJTYPE_CRAWLER,OBJTYPE_TAKER:
				PositionTexture(o\ExtraTexture, 0, 0.0004*Game\Gameplay\Time)
				Select o\ObjType
					Case OBJTYPE_TAKER:
						If o\InView Then
							If ((o\Anim=1 Or o\Anim=3) And (o\Enemy\Frame=5)) Or (o\Anim=2 And (o\Enemy\Frame=3 Or o\Enemy\Frame=7)) Then
								If (Not(ChannelPlaying(o\Enemy\Channel_EnemyStep))) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_MonsterFlap,o\Entity)
							EndIf
						EndIf
					Case OBJTYPE_CRAWLER:
						Select o\Enemy\AttackMode
							Case 0:
								o\Enemy\MayNotBeTargeted=True
								If EntityDistance(o\Entity,p\Objects\Entity)<55 Then
									o\Enemy\AttackMode=1
									o\Enemy\WaitTimer2=0.54*secs#
									EmitSmartSound(Sound_KnuxStomp,o\Entity)
									Object_Pieces_Create(False,OBJTYPE_ROCK,0,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,0.65,0,False,1)
								EndIf
							Case 1:
								o\Enemy\MayNotBeTargeted=False
								Object_EnemyLookAtPlayer(o,p)
								If (Not(o\Enemy\WaitTimer2>0)) Or o\InView=False Then
									o\Enemy\AttackMode=2
								EndIf
							Case 2:
								o\Enemy\MayNotBeTargeted=False
						End Select
				End Select
			Case OBJTYPE_AQUIS:
				If o\Enemy\FlyEnemyType Then
					Object_EnemyLookAtPlayer(o,p)
					If Not(o\Enemy\WaitTimer2>0) Then
						o\Enemy\WaitTimer2=5*secs#
					ElseIf o\Enemy\WaitTimer2>2.5*secs# Then
						Object_EnemyMoveUp(o,p,d,0.1975)
					Else
						Object_EnemyMoveUp(o,p,d,-0.1975)
					EndIf
				EndIf
			Case OBJTYPE_RHINOTANK:
				If o\Anim=2 Then PositionTexture(o\ExtraTexture, 0, 0.005*Game\Gameplay\Time)
			Case OBJTYPE_WING:			
				If o\InView Then
					If (o\Anim=1 And o\Enemy\Frame=1) Or (o\Anim=2 And o\Enemy\Frame=5) Then
						If (Not(ChannelPlaying(o\Enemy\Channel_EnemyStep))) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_MonsterFlap,o\Entity)
					EndIf
				EndIf
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO:
				If Not o\Enemy\SeenSonic Then
					If Not(o\Enemy\SearchSonic>0) Then
						Select(Rand(1,5))
							Case 1: EmitSmartSound(Sound_SoldierLook1,o\Entity)
							Case 2: EmitSmartSound(Sound_SoldierLook2,o\Entity)
							Case 3: EmitSmartSound(Sound_SoldierLook3,o\Entity)
						End Select
						o\Enemy\SearchSonic=(4.5+Rand(0,6)/1.5)*secs#
					Else
						If o\Enemy\SearchSonic>0 Then o\Enemy\SearchSonic=o\Enemy\SearchSonic-timervalue#
					EndIf
				EndIf
			Case OBJTYPE_SPLATS:
				If o\InView Then
					If o\Enemy\Frame=1 Then
						If (Not(ChannelPlaying(o\Enemy\Channel_EnemyStep))) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_Sting,o\Entity)
					EndIf
				EndIf
			Case OBJTYPE_SPRINKLR,OBJTYPE_DOOMSEYE:
				Object_HandleGlow(o,p)
				dist#=EntityDistance(o\Entity,p\Objects\Entity)
				If o\Enemy\WasJustAttacked>0 Then
					o\Enemy\MayNotBeTargeted=True
					If dist#<50 Then Object_EnemyMoveToPlayer(o,p,d,-0.74)
				Else
					o\Enemy\MayNotBeTargeted=False
					Object_EnemyLookAtPlayer(o,p)
				EndIf
				If (Not(Object_EnemyIsStun(o))) And (Not(Game\ControlLock>0)) And (Not(p\HurtTimer>0)) And (Not(o\Enemy\WasJustAttacked>0)) And (Not(p\TeleportTimer>0)) And Game\Victory=0 And (Not(p\Action=ACTION_TRANSFORM)) Then
					If dist#<50 Then
						Object_EnemyMoveToPlayer(o,p,d,0.5+p\SpeedLength#*0.25)
						Object_EnemyMoveUpAndDown(o,p,d,5,-3,0.5+p\SpeedLength#*0.25)
					Else
						Object_EnemyMoveToPlayer(o,p,d,0.5+p\SpeedLength#*0.75)
						Object_EnemyMoveUpAndDown(o,p,d,5,-3,0.5+p\SpeedLength#*0.75)
					EndIf
				EndIf
				If Not dist#<100 Then
					If Not o\Enemy\Alpha#>0 Then
						EntityType(o\Entity,0)
						PositionEntity o\Entity, p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, 1
						RotateEntity o\Entity, 0, p\Animation\Direction#+180, 0, 1
						EntityType(o\Entity,COLLISION_OBJECT_GOTHRU)
						MoveEntity o\Entity, 0, 30, -50
					EndIf
				EndIf
				If EntityDistance(o\Entity,cam\Entity)<5 Then
					EntityAlpha(o\Entity,0)
				Else
					If dist#<100 Then
						If o\Enemy\Alpha#<1 Then o\Enemy\Alpha#=o\Enemy\Alpha#+0.05*d\Delta Else o\Enemy\Alpha#=1
					Else
						If o\Enemy\Alpha#>0 Then o\Enemy\Alpha#=o\Enemy\Alpha#-0.05*d\Delta Else o\Enemy\Alpha#=0
					EndIf
					EntityAlpha(o\Entity, o\Enemy\Alpha#)
				EndIf
		End Select
		Select o\ObjType
			Case OBJTYPE_RHINOSPIKES:
				If (Not(p\Objects\Position\y#<o\Position\y#+3)) Then o\Enemy\MayGetAttacked=False
			Case OBJTYPE_SPIKES:
				If (Not(p\Objects\Position\y#<o\Position\y#+3)) And o\Enemy\AttackMode=0 Then o\Enemy\MayGetAttacked=False
			Case OBJTYPE_BUBBLSSPIKES:
				If Not o\InView Then o\Enemy\AttackMode=1
				If o\Enemy\AttackMode=0 Then o\Enemy\MayGetAttacked=False
			Case OBJTYPE_MADMOLE:
				If o\Anim=4 Then o\Enemy\MayNotBeTargeted=True Else o\Enemy\MayNotBeTargeted=False
		End Select
		Object_PlayRobotSteps(o,0,0)
	End Function

	Function Object_Enemy_BlockBehaviour(o.tObject, p.tPlayer, firsttime#, secondtime#)
			If o\Enemy\WaitTimer>firsttime#*secs# And o\Enemy\WaitTimer<secondtime#*secs# Then
				Select o\ObjType
					Case OBJTYPE_BEETLESPARK,OBJTYPE_FLAPPERNEEDLE: o\Anim=2
				End Select
				Select o\ObjType
					Case OBJTYPE_SPANA,OBJTYPE_BEETLESPARK:
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_EnemyElectric,o\Entity)
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ELECTRIC, o\Entity, 0, 0, 0, 0, 0, 0.05)
						If (Not(Game\Shield=OBJTYPE_TSHIELD)) Then o\Enemy\MayGetAttacked=False
					Default:
						o\Enemy\MayGetAttacked=False
				End Select
			Else
				Object_Enemy_NoBehaviour(o,p)
			EndIf
	End Function

	Function Object_Enemy_NoBehaviour_Anims(o.tObject)
		Select o\ObjType
			Case OBJTYPE_PAWNSHIELD,OBJTYPE_HAMMERSHIELD: If o\Enemy\Shield Then o\Anim=3 Else o\Anim=1
			Case OBJTYPE_PAWNGUN,OBJTYPE_HAMMERHAMMER: o\Anim=5
			Case OBJTYPE_PAWNSWORD: o\Anim=8
			Case OBJTYPE_CATERKILLER,OBJTYPE_CRABMEAT,OBJTYPE_SPINY,OBJTYPE_KIKI,OBJTYPE_HUNTER,OBJTYPE_CHASER,OBJTYPE_NEWTRON: If o\Enemy\InRange Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_AQUIS: If o\Enemy\FlyEnemyType Then o\Anim=1 Else o\Anim=2
			Case OBJTYPE_HUNTERSHIELD:
				If o\Enemy\Shield Then
					If o\Enemy\InRange Then o\Anim=5 Else o\Anim=4
				Else
					If o\Enemy\InRange Then o\Anim=2 Else o\Anim=1
				EndIf
			Case OBJTYPE_SLICER:
				If o\Enemy\InRange Then
					If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=3
				Else
					If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=3
				EndIf
			Case OBJTYPE_ASTERON,OBJTYPE_MUSHMEANIE: If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=2
			Case OBJTYPE_BUBBLSSPIKES: If o\Enemy\AttackMode=0 Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_STEELION,OBJTYPE_OCTUS: If o\Enemy\Underwater=1 Then o\Anim=4 Else o\Anim=1
			Case OBJTYPE_BURROBOT:
				Select o\Enemy\AttackMode
					Case 0: o\Anim=4
					Case 1: o\Anim=3
					Default: o\Anim=1
				End Select
			Case OBJTYPE_MADMOLE:
				If (Not(o\Enemy\InRange)) Then
					o\Anim=4
				Else
					If o\Enemy\AttackMode=0 Then o\Anim=1 Else o\Anim=2
				EndIf
			Case OBJTYPE_CRAWLER:
				Select o\Enemy\AttackMode
					Case 0: o\Anim=4
					Case 1: o\Anim=2
					Default: o\Anim=1
				End Select
			Case OBJTYPE_WARRIORGUN1: o\Anim=4
			Case OBJTYPE_WARRIORGUN2: o\Anim=7
			Case OBJTYPE_OAKSWORD: o\Anim=2
			Case OBJTYPE_SOLDIER,OBJTYPE_SOLDIERCAMO: If o\Enemy\SeenSonic Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_SHEEP: If o\Enemy\AttackMode=0 Then o\Anim=3 Else o\Anim=1
			Case OBJTYPE_SPLATS: If o\InView Then o\Anim=2 Else o\Anim=1
			Case OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSMECHA: o\Anim=2
			Default: o\Anim=1
		End Select
	End Function

	Function Object_Enemy_NoBehaviour(o.tObject, p.tPlayer)
		Object_Enemy_NoBehaviour_Anims(o)
		Select o\ObjType
			Case OBJTYPE_MOTOBUG,OBJTYPE_CATERKILLER,OBJTYPE_FIGHTER,OBJTYPE_CAMERON,OBJTYPE_ANTON,OBJTYPE_SPIKES,OBJTYPE_PENGUINATOR,OBJTYPE_STEELION,OBJTYPE_BURROBOT,OBJTYPE_RHINOTANK,OBJTYPE_TECHNOSQU,OBJTYPE_LEECH:
				If ChannelPlaying(o\Enemy\Channel_EnemyState) Then StopChannel(o\Enemy\Channel_EnemyState)
			Case OBJTYPE_SPANA,OBJTYPE_BEETLESPARK,OBJTYPE_FLAPPERNEEDLE:
				If ChannelPlaying(o\Enemy\Channel_EnemyState) Then StopChannel(o\Enemy\Channel_EnemyState)
				Select o\ObjType
					Case OBJTYPE_SPANA: Object_EnemyPointAtPlayer(o,p)
					Default: Object_EnemyLookAtPlayer(o,p)
				End Select
			Case OBJTYPE_CHOPPER,OBJTYPE_JAWS,OBJTYPE_AQUIS:
				If ChannelPlaying(o\Enemy\Channel_EnemyStep) Then StopChannel(o\Enemy\Channel_EnemyStep)
				RotateEntity o\Entity,0,EntityYaw(o\Entity),0
			Case OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES:
				If ChannelPlaying(o\Enemy\Channel_EnemyStep) Then StopChannel(o\Enemy\Channel_EnemyStep)
			Case OBJTYPE_BUBBLSSPIKES:
				o\Enemy\AttackTimer=1.75*secs#
				o\Enemy\AttackMode=1
			Case OBJTYPE_OCTUS:
				If o\Enemy\Underwater=1 Then o\Enemy\FlyEnemyType=True Else o\Enemy\FlyEnemyType=False
		End Select
	End Function

	Function Object_Enemy_SpecialBehaviour_RingDrain(p.tPlayer)
		If (Not(Game\Interface\RingStolenTimer>0)) And Game\Gameplay\Rings>0 Then
			Game\Interface\RingStolenTimer=0.25*secs#
			EmitSmartSound(Sound_Ring,p\Objects\Entity)
			Gameplay_SubstractRings(1)
		EndIf
	End Function
Function Object_Enemy_SpecialBehaviour_GunnerRingDrain(p.tPlayer)
	If (Not(Game\Interface\RingStolenTimer>0)) And Game\Gameplay\Rings>0 Then
		Game\Interface\RingStolenTimer=0.25*secs#
		EmitSmartSound(Sound_Ring,p\Objects\Entity)
		Gameplay_SubstractRings(1)
	EndIf
	If Game\Gameplay\Rings=0 Then Player_Hit(p)
End Function
	Function Object_Enemy_SpecialBehaviour(o.tObject, p.tPlayer)
		Select o\ObjType
			Case OBJTYPE_GRABBER,OBJTYPE_KLAGEN,OBJTYPE_ACHAOSBLOB,OBJTYPE_BOO:
				Select o\ObjType
					Case OBJTYPE_GRABBER: o\Anim=2
					Case OBJTYPE_BOO: o\Anim=3
					Default: o\Anim=1
				End Select
				If Not(o\Enemy\AttackTimer>0) Then
					EntityType(cam\Entity, COLLISION_CAMERA)
					EntityType(p\Objects\Entity, COLLISION_PLAYER)
					Player_Hit(p)
					o\Enemy\SelfDestruct=True
				EndIf
				Select o\ObjType
					Case OBJTYPE_KLAGEN:
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ELECTRIC, o\Entity, 0, 0, 0, 0, 0, 0.05)
						PositionEntity p\Objects\Entity,o\Position\x#,o\Position\y#+0.75,o\Position\z#
						Object_Enemy_SpecialBehaviour_RingDrain(p)
					Case OBJTYPE_GRABBER:
						PositionEntity p\Objects\Entity,o\Position\x#,o\Position\y#-5,o\Position\z#
					Case OBJTYPE_ACHAOSBLOB:
						PositionEntity p\Objects\Entity,o\Position\x#,o\Position\y#-0.25,o\Position\z#
					Default:
						PositionEntity p\Objects\Entity,o\Position\x#,o\Position\y#,o\Position\z#
				End Select
				p\IsGrabbedTimer=0.1*secs#
				o\Enemy\MayNotBeTargeted=True
			Case OBJTYPE_HORNET3,OBJTYPE_HORNET6:
				Select o\ObjType
					Case OBJTYPE_HORNET3:
						Select o\Enemy\AttackMode
							Case 0: o\Anim=2
							Case 1: o\Anim=1
						End Select
					Case OBJTYPE_HORNET6:
						Select o\Enemy\AttackMode
							Case 0: o\Anim=3
							Case 1: o\Anim=1
						End Select
				End Select
			Case OBJTYPE_BEETLESPRING:
				If Not(p\Action=ACTION_UP) Then
					Player_SetSpeed(p,0)
					If p\Action=ACTION_STOMP Then
						Player_SetSpeedY(p,3.0)
					Else
						Player_SetSpeedY(p,2.5)
					EndIf
					If p\HasVehicle=0 Then p\Action=ACTION_UP
					EmitSmartSound(Sound_Spring,o\Entity)
				EndIf
			Case OBJTYPE_ORBINAUT,OBJTYPE_SPONA:
				If Not o\Enemy\InRange Then
					If o\Enemy\AttackMode=0 Then
						o\Enemy\ShouldSpawnMissile=True
						Object_EnemyMovements_SpawnMissile(o,p)
						o\Enemy\AttackMode=1
					EndIf
				EndIf
				If o\ObjType=OBJTYPE_SPONA Then i#=15 Else i#=0
				If o\Enemy\HasBossObj1 Then
					PositionEntity o\Enemy\BossObj1\Entity, o\Position\x#, o\Position\y#, o\Position\z#, 1
					RotateEntity o\Enemy\BossObj1\Entity, 0, EntityYaw(Menu\RingRotator)+90*(1-1), 0, 1
					MoveEntity o\Enemy\BossObj1\Entity, 0, 0, 10+i#
				EndIf
				If o\Enemy\HasBossObj2 Then
					PositionEntity o\Enemy\BossObj2\Entity, o\Position\x#, o\Position\y#, o\Position\z#, 1
					RotateEntity o\Enemy\BossObj2\Entity, 0, EntityYaw(Menu\RingRotator)+90*(2-1), 0, 1
					MoveEntity o\Enemy\BossObj2\Entity, 0, 0, 10+i#
				EndIf
				If o\Enemy\HasBossObj3 Then
					PositionEntity o\Enemy\BossObj3\Entity, o\Position\x#, o\Position\y#, o\Position\z#, 1
					RotateEntity o\Enemy\BossObj3\Entity, 0, EntityYaw(Menu\RingRotator)+90*(3-1), 0, 1
					MoveEntity o\Enemy\BossObj3\Entity, 0, 0, 10+i#
				EndIf
				If o\Enemy\HasBossObj4 Then
					PositionEntity o\Enemy\BossObj4\Entity, o\Position\x#, o\Position\y#, o\Position\z#, 1
					RotateEntity o\Enemy\BossObj4\Entity, 0, EntityYaw(Menu\RingRotator)+90*(4-1), 0, 1
					MoveEntity o\Enemy\BossObj4\Entity, 0, 0, 10+i#
				EndIf
			Case OBJTYPE_BOOSCARE:
				If o\Anim=2 Then
					o\HitBox\x#=10 : o\HitBox\y#=12.35 : o\HitBox\z#=10
				Else
					o\HitBox\x#=6.5 : o\HitBox\y#=10 : o\HitBox\z#=6.5
				EndIf
			Case OBJTYPE_SPIKES:
				If Not(o\Enemy\AttackTimer>0) Then
					o\Enemy\AttackTimer=(2+Rand(2,4))*secs#
					EmitSmartSound(Sound_SpikeDrill2,o\Entity)
					o\Enemy\AttackMode=0
				Else
					If o\Enemy\AttackTimer<2*secs# And o\Enemy\AttackMode=0 Then
						o\Enemy\AttackMode=1
						EmitSmartSound(Sound_SpikeDrill1,o\Entity)
						EmitSmartSound(Sound_EnemyShotPoof,o\Entity)
						o\Enemy\ShouldSpawnMissile=True
					EndIf
				EndIf
			Case OBJTYPE_SLICER,OBJTYPE_ASTERON,OBJTYPE_MADMOLE:
				If (Not(o\Enemy\WaitTimer2>0)) And o\Enemy\AttackMode>0 Then o\Enemy\AttackMode=0
			Case OBJTYPE_BOMBIE:
				If EntityDistance(p\Objects\Entity,o\Entity)<60 Then
					If (Not(o\Enemy\WaitTimer2>0)) Then o\Enemy\WaitTimer2=1*secs# : EmitSmartSound(Sound_Tnt,o\Entity)
				EndIf
			Case OBJTYPE_BUBBLSSPIKES:
				If Not(o\Enemy\AttackTimer>0) Then
					o\Enemy\AttackTimer=(2+Rand(3,5))*secs#
					EmitSmartSound(Sound_SpikeDrill2,o\Entity)
					o\Enemy\AttackMode=0
				Else
					If o\Enemy\AttackTimer<2*secs# And o\Enemy\AttackMode=0 Then
						o\Enemy\AttackMode=1
						EmitSmartSound(Sound_SpikeDrill1,o\Entity)
					EndIf
				EndIf
			Case OBJTYPE_CRAWL,OBJTYPE_MUSHMEANIE,OBJTYPE_ROLLER,OBJTYPE_SHEEP:
				If (Not(p\BumpedTimer>0)) Then
					If p\HasVehicle=0 Then
						Player_ConvertGroundToAir(p) : p\Motion\Ground = False
						p\Flags\HomingLocked = False : p\Flags\Targeter=0
						Player_Action_Bumped_Initiate(p)
						p\BumpedCloudTimer=p\BumpedTimer
					Else
						If Abs(o\Position\y#-p\Objects\Position\y#)<2 Then p\Animation\Direction#=p\Animation\Direction#+180
						p\BumpedTimer=0.5*secs#
					EndIf
					If Game\ControlLock<0.125*secs# Then Game\ControlLock=0.125*secs#
					Select o\ObjType
						Case OBJTYPE_SHEEP:
							Player_SetSpeedY(p,2.5)
						Default:
							Player_SetSpeed(p,-1.5)
							Player_SetSpeedY(p,0.75)
					End Select
					Select o\ObjType
						Case OBJTYPE_MUSHMEANIE,OBJTYPE_SHEEP:
							Select o\ObjType
								Case OBJTYPE_SHEEP: EmitSmartSound(Sound_Cloud,o\Entity)
								Default: EmitSmartSound(Sound_Ninja,o\Entity)
							End Select
							o\Enemy\AttackMode=1
							Object_Pieces_Create(False,-o\ObjType,0,o\Position\x#,o\Position\y#,o\Position\z#,o\Rotation\x#,o\Rotation\y#,o\Rotation\z#,1.1,o\Enemy\Gold)
							o\Enemy\WaitTimer2=0.5*secs#
						Default:
							EmitSmartSound(Sound_Bumper1,o\Entity)
					End Select
				EndIf
			Case OBJTYPE_E1000:
				If Not(o\Enemy\WaitTimer2>0) Then
					o\Enemy\WaitTimer2=(2+(Rand(0,1)))*secs#
					If Rand(0,1)=0 Then o\Enemy\FlyEnemyType=True Else o\Enemy\FlyEnemyType=False
				EndIf
		End Select
	End Function

;-------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------

Function Object_Enemy_BossBehaviour(o.tObject,p.tPlayer,d.tDeltaTime)

	Select o\ObjType
	Case OBJTYPE_BOSSRUN,OBJTYPE_BOSSMECHA:
	Default:
		PositionEntity o\Entity4, o\Position\x#, o\Position\y#, o\Position\z#, 1
		PointEntity o\Entity4, p\Objects\Entity
		Camera_BossCamera(cam, p, o\Entity4)
	End Select

	If o\Enemy\VoiceTimer>0 Then o\Enemy\VoiceTimer=o\Enemy\VoiceTimer-timervalue#
	If o\Enemy\AttackTimer2>0 Then o\Enemy\AttackTimer2=o\Enemy\AttackTimer2-timervalue#
	If o\Enemy\WaitTimer3>0 Then o\Enemy\WaitTimer3=o\Enemy\WaitTimer3-timervalue#

	If o\Enemy\InRange Then
		Object_Enemy_BossGeneral(o,p,d)
		Select o\Enemy\BossMode
			Case 0:
				o\Enemy\BossMode=1 : o\Enemy\AttackTimer=2*secs#
				If (o\ObjType=OBJTYPE_BOSSBETA Or o\ObjType=OBJTYPE_BOSSMECHA) Then o\Enemy\AttackMode=0 : o\Enemy\AttackMode2=0
			Case 1:
				Select o\ObjType
					Case OBJTYPE_BOSSBETA:
						Object_Enemy_BossBehaviour_BetaShock(o,p)
				End Select
				If Not(o\Enemy\AttackTimer)>0 Then
					Select o\ObjType
						Case OBJTYPE_BOSS,OBJTYPE_BOSSRUN:
							o\Enemy\AttackTimer=(1+Rand(0,2)/2.0)*secs#
							EmitSmartSound(Sound_EnemyCannon,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSS2:
							o\Enemy\AttackTimer=(0.5+Rand(0,1)/2.0)*secs#
							EmitSmartSound(Sound_EnemyShot2,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSSBETA:
							Object_Enemy_BossBehaviour_Beta(o,p)
						Case OBJTYPE_BOSSMECHA:
							Object_Enemy_BossBehaviour_MechaSonic(o,p)
					End Select
					If (Not(Rand(3,3+5)>3)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Attack[Rand(1,5)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
				EndIf
				If o\Enemy\Health<=7 Then
					o\Enemy\BossMode=2 : o\Enemy\AttackTimer=2*secs#
					If (o\ObjType=OBJTYPE_BOSSBETA Or o\ObjType=OBJTYPE_BOSSMECHA) Then o\Enemy\AttackMode=0 : o\Enemy\AttackMode2=0
				EndIf
			Case 2:
				Select o\ObjType
					Case OBJTYPE_BOSSBETA:
						Object_Enemy_BossBehaviour_BetaShock(o,p)
				End Select
				If Not(o\Enemy\AttackTimer)>0 Then
					Select o\ObjType
						Case OBJTYPE_BOSS:
							o\Enemy\AttackTimer=(0.5+Rand(0,2)/4.0)*secs#
							EmitSmartSound(Sound_EnemyCannon,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSS2:
							o\Enemy\AttackTimer=(2+Rand(0,2)/2.0)*secs#
							EmitSmartSound(Sound_EnemyShot2,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSSRUN:
							o\Enemy\AttackTimer=0.0875*secs#
							EmitSmartSound(Sound_EnemyShot,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSSBETA:
							Object_Enemy_BossBehaviour_Beta(o,p,1)
						Case OBJTYPE_BOSSMECHA:
							Object_Enemy_BossBehaviour_MechaSonic(o,p)
					End Select
					If (Not(Rand(3,3+5)>3)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Attack[Rand(1,5)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
				EndIf
				Select o\ObjType
					Case OBJTYPE_BOSS,OBJTYPE_BOSSRUN:
						If o\Enemy\Health<=4 Then
							o\Enemy\BossMode=3 : o\Enemy\AttackTimer=0
							If o\Enemy\HasBossObj1=False Then
								o\Enemy\BossObj1.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8, o\Position\z#, 0, (1-1)*(360/3), 0, 0, OBJTYPE_SPIKESWING)
								Objects_Reset_HasMesh(o\Enemy\BossObj1) : Objects_Reset_Repose(o\Enemy\BossObj1) : Objects_Reset_Object(o\Enemy\BossObj1)
								o\Enemy\HasBossObj1=True
							EndIf
							If o\Enemy\HasBossObj2=False Then
								o\Enemy\BossObj2.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8, o\Position\z#, 0, (2-1)*(360/3), 0, 0, OBJTYPE_SPIKESWING)
								Objects_Reset_HasMesh(o\Enemy\BossObj2) : Objects_Reset_Repose(o\Enemy\BossObj2) : Objects_Reset_Object(o\Enemy\BossObj2)
								o\Enemy\HasBossObj2=True
							EndIf
							If o\Enemy\HasBossObj3=False Then
								o\Enemy\BossObj3.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8, o\Position\z#, 0, (3-1)*(360/3), 0, 0, OBJTYPE_SPIKESWING)
								Objects_Reset_HasMesh(o\Enemy\BossObj3) : Objects_Reset_Repose(o\Enemy\BossObj3) : Objects_Reset_Object(o\Enemy\BossObj3)
								o\Enemy\HasBossObj3=True
							EndIf
						EndIf
					Default:
						If o\Enemy\Health<=4 Then
							o\Enemy\BossMode=3 : o\Enemy\AttackTimer=2*secs#
							If (o\ObjType=OBJTYPE_BOSSBETA Or o\ObjType=OBJTYPE_BOSSMECHA) Then o\Enemy\AttackMode=0 : o\Enemy\AttackMode2=0
						EndIf
				End Select				
			Case 3:
				Select o\ObjType
					Case OBJTYPE_BOSS,OBJTYPE_BOSSRUN:
						PositionEntity o\Enemy\BossObj1\Entity, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj1\Entity2, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj2\Entity, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj2\Entity2, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj3\Entity, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj3\Entity2, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
					Case OBJTYPE_BOSSBETA:
						Object_Enemy_BossBehaviour_BetaShock(o,p)
				End Select
				Select o\ObjType
					Case OBJTYPE_BOSS:
					Default:
						If Not(o\Enemy\AttackTimer)>0 Then
							Select o\ObjType
								Case OBJTYPE_BOSSRUN:
									o\Enemy\AttackTimer=0.0875*secs#
									EmitSmartSound(Sound_EnemyShot,o\Entity)
									o\Enemy\ShouldSpawnMissile=True
								Case OBJTYPE_BOSS2:
									o\Enemy\AttackTimer=(6+Rand(0,2)/2.0)*secs#
									EmitSmartSound(Sound_EnemyShot2,o\Entity)
									o\Enemy\ShouldSpawnMissile=True
								Case OBJTYPE_BOSSBETA:
									Object_Enemy_BossBehaviour_Beta(o,p,2)
								Case OBJTYPE_BOSSMECHA:
									Object_Enemy_BossBehaviour_MechaSonic(o,p)
							End Select
							If (Not(Rand(3,3+5)>3)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Attack[Rand(1,5)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
						EndIf
				End Select
				Select o\ObjType
					Case OBJTYPE_BOSS2:
						If o\Enemy\Health<=2 Then
							o\Enemy\BossMode=4 : o\Enemy\AttackTimer=2*secs#
							If (Not(Rand(1,1+3)=1)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Lose[Rand(1,3)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
							If o\Enemy\HasBossObj1=False Then
								o\Enemy\BossObj1.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8, o\Position\z#, 0, (1-1)*(360/3), 0, 0, OBJTYPE_SPIKESWING)
								Objects_Reset_HasMesh(o\Enemy\BossObj1) : Objects_Reset_Repose(o\Enemy\BossObj1) : Objects_Reset_Object(o\Enemy\BossObj1)
								o\Enemy\HasBossObj1=True
							EndIf
							If o\Enemy\HasBossObj2=False Then
								o\Enemy\BossObj2.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8, o\Position\z#, 0, (2-1)*(360/3), 0, 0, OBJTYPE_SPIKESWING)
								Objects_Reset_HasMesh(o\Enemy\BossObj2) : Objects_Reset_Repose(o\Enemy\BossObj2) : Objects_Reset_Object(o\Enemy\BossObj2)
								o\Enemy\HasBossObj2=True
							EndIf
							If o\Enemy\HasBossObj3=False Then
								o\Enemy\BossObj3.tObject = Object_Spike_Create(o\Position\x#, o\Position\y#-8, o\Position\z#, 0, (3-1)*(360/3), 0, 0, OBJTYPE_SPIKESWING)
								Objects_Reset_HasMesh(o\Enemy\BossObj3) : Objects_Reset_Repose(o\Enemy\BossObj3) : Objects_Reset_Object(o\Enemy\BossObj3)
								o\Enemy\HasBossObj3=True
							EndIf
						EndIf
					Default:
						If o\Enemy\Health<=2 Then
							o\Enemy\BossMode=4 : o\Enemy\AttackTimer=0
							If (o\ObjType=OBJTYPE_BOSSBETA Or o\ObjType=OBJTYPE_BOSSMECHA) Then o\Enemy\AttackMode=0 : o\Enemy\AttackMode2=0
							If (Not(Rand(1,1+3)=1)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Lose[Rand(1,3)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
							Select o\ObjType
								Case OBJTYPE_BOSSRUN:
									If o\Enemy\HasBossObj1 Then o\Enemy\BossObj1\ExplodeHit=True : o\Enemy\HasBossObj1=False
									If o\Enemy\HasBossObj2 Then o\Enemy\BossObj2\ExplodeHit=True : o\Enemy\HasBossObj2=False
									If o\Enemy\HasBossObj3 Then o\Enemy\BossObj3\ExplodeHit=True : o\Enemy\HasBossObj3=False
							End Select
						EndIf
				End Select
			Case 4:
				Select o\ObjType
					Case OBJTYPE_BOSS,OBJTYPE_BOSS2:
						PositionEntity o\Enemy\BossObj1\Entity, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj1\Entity2, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj2\Entity, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj2\Entity2, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj3\Entity, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
						PositionEntity o\Enemy\BossObj3\Entity2, o\Position\x#, o\Position\y#-8, o\Position\z#, 1
					Case OBJTYPE_BOSSBETA:
						Object_Enemy_BossBehaviour_BetaShock(o,p)
				End Select
				If Not(o\Enemy\AttackTimer)>0 Then
					Select o\ObjType
						Case OBJTYPE_BOSS:
							o\Enemy\AttackTimer=(0.5+Rand(0,2)/4.0)*secs#
							EmitSmartSound(Sound_EnemyCannon,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSS2:
							o\Enemy\AttackTimer=(2+Rand(0,2)/2.0)*secs#
							EmitSmartSound(Sound_EnemyShot2,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSSRUN:
							o\Enemy\AttackTimer=0.0875*secs#
							EmitSmartSound(Sound_EnemyShot,o\Entity)
							o\Enemy\ShouldSpawnMissile=True
						Case OBJTYPE_BOSSBETA:
							Object_Enemy_BossBehaviour_Beta(o,p,3)
						Case OBJTYPE_BOSSMECHA:
							Object_Enemy_BossBehaviour_MechaSonic(o,p)
					End Select
					If (Not(Rand(3,3+5)>3)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Attack[Rand(1,5)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
				EndIf
				If o\Enemy\Health<=0 Then
					o\Enemy\BossMode=5 : o\Enemy\AttackTimer=5*secs# : o\Enemy\WaitTimer=0
					If (o\ObjType=OBJTYPE_BOSSBETA Or o\ObjType=OBJTYPE_BOSSMECHA) Then o\Enemy\AttackMode=0 : o\Enemy\AttackMode2=0
					If (Not(Rand(1,1+3)=1)) And (Not(ChannelPlaying(o\Enemy\Channel_EnemyState))) And (Not(o\Enemy\VoiceTimer>0)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Voice_EGG_Lose[Rand(1,3)],o\Entity2) : o\Enemy\VoiceTimer=3*secs#
					Select o\ObjType
						Case OBJTYPE_BOSS,OBJTYPE_BOSS2:
							If o\Enemy\HasBossObj1 Then o\Enemy\BossObj1\ExplodeHit=True : o\Enemy\HasBossObj1=False
							If o\Enemy\HasBossObj2 Then o\Enemy\BossObj2\ExplodeHit=True : o\Enemy\HasBossObj2=False
							If o\Enemy\HasBossObj3 Then o\Enemy\BossObj3\ExplodeHit=True : o\Enemy\HasBossObj3=False
					End Select
				EndIf
		End Select
	EndIf

End Function

Function Object_Enemy_BossBehaviour_Beta(o.tObject, p.tPlayer, second=0)
	If o\Enemy\AttackMode>=0 Then
	Select o\Enemy\AttackMode
		Case 0:
			If second=0 Then
				o\Anim=1
				o\Enemy\AttackMode=1
			ElseIf second=1 Or second=2 Then
				If Rand(1,3)=1 Then
					o\Anim=1
					o\Enemy\AttackMode=1
				Else
					If second=1 Then
						o\Anim=5
						o\Enemy\AttackMode=11
					Else
						o\Anim=7
						o\Enemy\AttackMode=101
					EndIf
				EndIf
			ElseIf second=3 Then
				If Rand(1,3)=1 Then
					o\Anim=1
					o\Enemy\AttackMode=1
				Else
					If Rand(1,2)=1 Then
						o\Anim=5
						o\Enemy\AttackMode=11
					Else
						o\Anim=7
						o\Enemy\AttackMode=101
					EndIf
				EndIf
			EndIf
			o\Enemy\AttackMode2=o\Enemy\AttackMode2+1
			o\Enemy\AttackTimer=(0.75+Rand(0,2)/2.0)*secs#
		Case 1:
			o\Anim=3
			o\Enemy\AttackMode=2
			EmitSmartSound(Sound_PsychoThrow,o\Entity)
		Case 2:
			If Not(Animating(o\Entity)) Then
				o\Anim=4
				o\Enemy\AttackMode=3
				o\Enemy\AttackTimer=1.05*secs#
			EndIf
		Case 3:
			If (Rand(1,4)=1 And o\Enemy\AttackMode2>=4) Or (o\Enemy\AttackMode2>=7) Then
				o\Anim=9
				o\Enemy\AttackMode=4
				o\Enemy\AttackTimer=2.5*secs#
			Else
				o\Enemy\AttackMode=0
			EndIf
		Case 4:
			o\Enemy\AttackMode=0
		Case 11,12,13,14,15:
			o\Enemy\ShouldSpawnMissile=True
			EmitSmartSound(Sound_EnemyShot3,o\Entity)
			o\Anim=6
			o\Enemy\AttackMode=o\Enemy\AttackMode+5
		Case 16,17,18,19,20:
			If Not(Animating(o\Entity)) Then
				o\Anim=5
				If o\Enemy\AttackMode=20 Then
					If (Rand(1,4)=1 And o\Enemy\AttackMode2>=4) Or (o\Enemy\AttackMode2>=7) Then
						o\Enemy\AttackMode=21
						o\Enemy\AttackTimer=0.5*secs#
					Else
						o\Enemy\AttackMode=0
					EndIf
				Else
					o\Enemy\AttackMode=o\Enemy\AttackMode-5+1
					o\Enemy\AttackTimer=0.5*secs#
				EndIf
			EndIf
		Case 21:
			o\Anim=9
			o\Enemy\AttackMode=4
			o\Enemy\AttackTimer=2.5*secs#
		Case 101:
			o\Anim=8
			o\Enemy\AttackMode=102
			o\Enemy\ShouldSpawnMissile=True
			EmitSmartSound(Sound_EnemyShot2,o\Entity)
		Case 102:
			If Not(Animating(o\Entity)) Then
				o\Anim=7
				o\Enemy\AttackMode=103
				o\Enemy\AttackTimer=0.5*secs#
			EndIf
		Case 103:
			If (Rand(1,4)=1 And o\Enemy\AttackMode2>=4) Or (o\Enemy\AttackMode2>=7) Then
				o\Anim=9
				o\Enemy\AttackMode=4
				o\Enemy\AttackTimer=2.5*secs#
			Else
				o\Enemy\AttackMode=0
			EndIf
	End Select
	EndIf
End Function

Function Object_Enemy_BossBehaviour_BetaShock(o.tObject, p.tPlayer)
	Select o\Enemy\AttackMode
		Case 2,3:
			If (Not(o\Enemy\AttackTimer2>0)) Then
				o\Enemy\ShouldSpawnMissile=True
				o\Enemy\AttackTimer2=0.8*secs#
			EndIf
	End Select
End Function

Function Object_Enemy_BossBehaviour_MechaSonic(o.tObject, p.tPlayer)
	Select o\Enemy\BossMode
		Case 1,2:
			Select o\Enemy\AttackMode2
				Case 0:
					o\Anim=2
					o\Enemy\AttackMode2=1
					o\Enemy\AttackTimer=(0.75+Rand(0,2)/2.0)*secs#
				Case 1:
					o\Anim=3
					o\Enemy\AttackMode2=2
					EmitSmartSound(Sound_PsychoThrow,o\Entity)
				Case 4:
					o\Anim=2
					o\Enemy\AttackMode2=5
					o\Enemy\AttackTimer=8*secs#
				Case 5:
					o\Enemy\AttackMode2=0
			End Select
		Case 3,4:
			Select o\Enemy\AttackMode2
				Case 0:
					o\Anim=6
					o\Enemy\AttackMode2=1
					o\Enemy\AttackTimer=2*secs#
				Case 1:
					EmitSmartSound(Sound_GoSuper,p\Objects\Entity)
					o\Enemy\Shield=1
					o\Anim=2
					o\Enemy\AttackMode2=2
					o\Enemy\AttackTimer=(0.25+Rand(0,2)/2.0)*secs#
				Case 2:
					o\Anim=4
					o\Enemy\AttackMode2=3
					o\Enemy\AttackMode3=0
				Case 3:
					If (o\Enemy\AttackMode3>=5 And Rand(1,2)=1) Or (o\Enemy\AttackMode3>=10) Then
						If Rand(1,3)=1 Then
							o\Anim=2
							o\Enemy\AttackMode2=4
							o\Enemy\AttackTimer=0.875*secs#
						Else
							o\Anim=2
							o\Enemy\AttackMode2=2
							o\Enemy\AttackTimer=(0.75+Rand(0,2)/2.0)*secs#
						EndIf
					Else
						EmitSmartSound(Sound_EnemyShot2,o\Entity)
						o\Enemy\ShouldSpawnMissile=True
						o\Enemy\AttackTimer=0.5*secs#
						o\Enemy\AttackMode3=o\Enemy\AttackMode3+1
					EndIf
				Case 4:
					o\Enemy\Shield=0
					o\Enemy\AttackMode2=5
					o\Enemy\AttackTimer=8*secs#
				Case 5:
					o\Enemy\AttackMode2=0
			End Select
	End Select
End Function

Function Object_Enemy_BossGeneral(o.tObject, p.tPlayer, d.tDeltaTime)
	If Not(ChannelPlaying(o\Enemy\Channel_EnemyStep)) Then o\Enemy\Channel_EnemyStep=EmitSmartSound(Sound_EnemyHover,o\Entity)

	If o\Enemy\WaitTimer2>0 Then
		ShowEntity(o\Entity3)
		PositionTexture(o\ExtraTexture, 0, 0.002*Game\Gameplay\Time)
		o\Enemy\MayGetAttacked=False
		Select o\ObjType
			Case OBJTYPE_BOSSBETA:
				HideEntity(o\Entity2)
		End Select
	Else
		If o\Enemy\BossMode<5 And o\Enemy\WasJustAttacked<3.8*secs# And o\Enemy\WasJustAttacked>1*secs# Then
			o\Enemy\WaitTimer2=(5+Rand(1,4)/4.0)*secs# : EmitSmartSound(Sound_EggmanShield,o\Entity)
			o\Enemy\WasJustAttacked=0
		EndIf
		HideEntity(o\Entity3)
		Select o\ObjType
			Case OBJTYPE_BOSSBETA:
				If o\Anim=9 Then o\Enemy\MayGetAttacked=True Else o\Enemy\MayGetAttacked=False
				If o\Anim=3 Or o\Anim=4 Then
					ShowEntity(o\Entity2)
					TurnEntity o\Entity2,5*d\Delta,5*d\Delta,5*d\Delta
				Else
					HideEntity(o\Entity2)
				EndIf
			Case OBJTYPE_BOSSMECHA:
				If (Not(o\Anim=3)) And o\Enemy\Shield=0 Then o\Enemy\MayGetAttacked=True Else o\Enemy\MayGetAttacked=False
			Default: o\Enemy\MayGetAttacked=True
		End Select
	EndIf

	Select o\Enemy\BossMode
		Case 5:
			Object_EnemyLookAtPlayer(o,p)
			Object_EnemyMoveToPlayer(o,p,d,-0.6)
			Object_EnemyMoveUp(o,p,d,0.4)
			If (Not(o\Enemy\AttackTimer>0)) Then Game\BossNotDefeated=0
			If Not(EntityDistance(o\Entity,p\Objects\Entity)>450) Then
				If Not(o\Enemy\WaitTimer)>0 Then
					o\Enemy\WaitTimer=(Rand(2,5)/5.0)*secs#
					Object_PlayRobotDestroySound(o)
					ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BOMBBIG, o\Entity)
				EndIf
			EndIf
		Default:
			Select o\ObjType
				Case OBJTYPE_BOSSRUN:
					Object_Enemy_BossGeneral_BossRun(o,p,d)
				Case OBJTYPE_BOSSBETA:
					If o\Enemy\AttackMode>=-1 Then
						If Not(o\Anim=9) Then
							If o\Anim=4 Then
								If o\Enemy\AttackTimer>0.875*secs# And EntityDistance(o\Entity,p\Objects\Entity)>10 Then Object_EnemyLookAtPlayer(o,p)
								Object_EnemyMoveToPlayer(o,p,d,2.38)
							Else
								Object_EnemyLookAtPlayer(o,p)
								If o\Enemy\BossMode<4 Then
									Object_EnemyMoveToPlayer(o,p,d,0.4)
								Else
									Object_EnemyMoveToPlayer(o,p,d,0.8)
								EndIf
							EndIf
						EndIf
					EndIf
				Case OBJTYPE_BOSSMECHA:
					If o\Position\z#<p\Objects\Position\z#-100 Or o\Position\z#>p\Objects\Position\z#+700 Or Abs(o\Position\y#-p\Objects\Position\y#)>700 Then
						Object_Enemy_BossGeneral_BossRun(o,p,d)
						o\Enemy\AttackMode2=0
						o\Enemy\AttackTimer=0
					Else
						Select o\Enemy\BossMode
							Case 1,2:
								Select o\Enemy\AttackMode2
									Case 2,3:
										If o\Position\z#>p\Objects\Position\z#-10 Then
											Select o\Enemy\BossMode
												Case 1:
													Object_EnemyMoveToPlayer(o,p,d,0.8-p\SpeedLength#*0.5)
													If o\Enemy\AttackMode2=2 Then
														Object_EnemyMoveUp(o,p,d,-1.8)
														If Abs(o\Position\y#-p\Objects\Position\y#)<5 Then o\Enemy\AttackMode2=3 : EmitSmartSound(Sound_Crusher,o\Entity)
													Else
														Object_EnemyMoveUp(o,p,d,1.8)
														If Abs(o\Position\y#-p\Objects\Position\y#)>20 Then o\Enemy\AttackMode2=2 : EmitSmartSound(Sound_Crusher,o\Entity)
													EndIf
												Case 2:
													Object_EnemyMoveToPlayer(o,p,d,0.95-p\SpeedLength#*0.5)
													If o\Position\y#>p\Objects\Position\y#+1.5 Then MoveEntity o\Entity,0,-1.8*d\Delta,0
											End Select
											If o\Position\z#>p\Objects\Position\z#+30 Then
												If o\Position\x#>p\Objects\Position\x#+2.5 Then MoveEntity o\Entity,1.8*d\Delta,0,0
												If o\Position\x#<p\Objects\Position\x#-2.5 Then MoveEntity o\Entity,-1.8*d\Delta,0,0
											EndIf
										Else
											EmitSmartSound(Sound_PsychoThrow,o\Entity)
											o\Enemy\AttackMode2=4
											o\Enemy\AttackTimer=1*secs#
										EndIf
									Case 4:
										Object_EnemyMoveToPlayer(o,p,d,-3.8-p\SpeedLength#*0.5)
									Default: Object_Enemy_BossGeneral_BossRun(o,p,d)
								End Select
							Default: Object_Enemy_BossGeneral_BossRun(o,p,d)
						End Select
					EndIf
				Default:
					Object_EnemyLookAtPlayer(o,p)
					If o\Enemy\BossMode<4 Then
						Object_EnemyMoveToPlayer(o,p,d,0.4)
					Else
						Object_EnemyMoveToPlayer(o,p,d,0.8)
					EndIf
			End Select
			Select o\ObjType
				Case OBJTYPE_BOSSBETA:
					If (Not(o\Anim=9)) Then
						If Not(o\Enemy\WaitTimer)>0 Then
							o\Enemy\WaitTimer=2*secs#
							If (Not(o\Enemy\WaitTimer2>0)) And Rand(1,20)=1 Then o\Enemy\WaitTimer2=(5+Rand(1,4)/4.0)*secs# : EmitSmartSound(Sound_DashElectro,o\Entity)
						EndIf
						If o\Enemy\BossMode=3 And o\Enemy\AttackMode>10 Then
							Object_EnemyMoveUpAndDown(o,p,d,30,-28,0.3)
						Else
							Object_EnemyMoveUpAndDown(o,p,d,15,-13,0.3)
						EndIf

						If (Not(o\Enemy\WasJustAttacked>0)) And (p\JustThrewBombTimer>0 Or (EntityDistance(o\Entity,p\Objects\Entity)<20 And p\Flags\Attacking)) And (Not(o\Enemy\AttackMode<0)) And (Not(o\Enemy\WaitTimer2>0)) Then
							If p\Flags\Attacking Then o\Enemy\AttackMode=-Rand(2,3) Else o\Enemy\AttackMode=-1
							o\Enemy\WaitTimer2=2*secs# : EmitSmartSound(Sound_DashElectro,o\Entity)
							o\Anim=2
						EndIf
						If o\Enemy\AttackMode<0 Then
							If o\Hit Then p\Flags\HomingLocked = False : p\Flags\Targeter=0
							If (Not(Animating(o\Entity))) Then o\Enemy\AttackMode=0
							Select o\Enemy\AttackMode
								Case -2: MoveEntity o\Entity,-1.3*d\Delta,0,0
								Case -3: MoveEntity o\Entity,1.3*d\Delta,0,0
							End Select
						ElseIf o\Enemy\AttackMode=0 Then
							o\Anim=1
						EndIf
					Else
						o\Enemy\WaitTimer2=0
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_ELECTROSTUN, o\Entity, o\HitBox\y#/4.0)
						If Not(ChannelPlaying(o\Enemy\Channel_EnemyState)) Then o\Enemy\Channel_EnemyState=EmitSmartSound(Sound_EnemyStun,o\Entity)
					EndIf
				Case OBJTYPE_BOSSMECHA:
					If o\Anim=2 Or o\Anim=4 Then Object_Enemy_BossGeneral_UpDownAround(o,p,d,25)
				Default:
					Object_Enemy_BossGeneral_UpDownAround(o,p,d)
			End Select
	End Select
End Function

Function Object_Enemy_BossGeneral_UpDownAround(o.tObject, p.tPlayer, d.tDeltaTime, top#=40)
	If Not(o\Enemy\WaitTimer)>0 Then
		o\Enemy\WaitTimer=2*secs#
		Repeat : o\Enemy\AttackMode=Rand(-2,2) : Until o\Enemy\AttackMode<>0
		If (Not(o\Enemy\WaitTimer2>0)) And Rand(1,20)=1 Then o\Enemy\WaitTimer2=(5+Rand(1,4)/4.0)*secs# : EmitSmartSound(Sound_DashElectro,o\Entity)
	Else
		If o\Position\y#>p\Objects\Position\y#+20 Then
			If o\Position\y#<p\Objects\Position\y#+top# Or o\Enemy\AttackMode<0 Then
				Object_EnemyMoveUp(o,p,d,0.2*o\Enemy\AttackMode)
			ElseIf o\Position\y#>p\Objects\Position\y#+top#+5
				Object_EnemyMoveUp(o,p,d,-0.4)
			EndIf
		ElseIf o\Position\y#<p\Objects\Position\y#+15 Or o\Enemy\AttackMode>0 Then
			Object_EnemyMoveUp(o,p,d,0.2)
		EndIf
	EndIf
End Function

Function Object_Enemy_BossGeneral_BossRun(o.tObject, p.tPlayer, d.tDeltaTime)
	If o\Position\z#<p\Objects\Position\z#-100 Or o\Position\z#>p\Objects\Position\z#+700 Or Abs(o\Position\y#-p\Objects\Position\y#)>700 Then
		PositionEntity o\Entity, 0, o\InitialPosition\y#, pp(1)\Objects\Position\z#+250, 1
		EmitSmartSound(Sound_Teleport,o\Entity)
	Else
		If p\SpeedLength#<2.5 Then
			Object_EnemyMoveToPlayer(o,p,d,-2.5)
		Else
			If o\Position\z#<p\Objects\Position\z# Then
				Object_EnemyMoveToPlayer(o,p,d,-(p\SpeedLength#))
			Else
				Object_EnemyMoveToPlayer(o,p,d,-(p\SpeedLength#-0.35))
			EndIf
		EndIf
	EndIf
	If o\Enemy\BossMode>=4 Then
		o\Mode=0
	Else
		If Not(o\Enemy\WaitTimer3)>0 Then
			o\Enemy\WaitTimer3=2*secs#
			Select(Rand(1,3))
			Case 1: o\Mode=-1
			Case 2: o\Mode=0
			Case 3: o\Mode=1
			End Select
		EndIf
	EndIf
	If p\SpeedLength#>0 Then
		If o\Position\x#<9*o\Mode Then MoveEntity o\Entity, -0.2*d\Delta, 0, 0
		If o\Position\x#>9*o\Mode Then MoveEntity o\Entity, 0.2*d\Delta, 0, 0
	EndIf
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D