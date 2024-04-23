	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Game_Stage_End_Camera()
		FreeEntity CameraSprite
		For c.tCamera = Each tCamera
			If c\Filter<>0 Then FreeEntity(c\Filter)
			FreeEntity c\Entity
			Delete c\Position
			Delete c\Rotation
			Delete c\Alignment
			Delete c\TargetPosition
			Delete c\TargetRotation
			Delete c\Lock\Position
			Delete c\Lock\Rotation
			FreeEntity c\Lock\PosMesh
			FreeEntity c\Lock\PosMeshTarget
			Delete c\Lock
			Delete c
		Next
	End Function

	Function Game_Stage_End_Objects_ObjectIndividual(o.tObject)
		If o\ThisIsAnEnemy Then
			If o\Enemy\HasJets>=1 Then FreeEntity o\Enemy\Jet1 : ParticleTemplate_Delete(o\Enemy\JetParticle1)
			If o\Enemy\HasJets>=2 Then FreeEntity o\Enemy\Jet2 : ParticleTemplate_Delete(o\Enemy\JetParticle2)
			FreeEntity o\Enemy\Center
			ParticleTemplate_Delete(o\Enemy\WaterParticle)
			If Menu\Settings\Shadows#>0 Then FreeEntity(o\Enemy\ShadowCircle)
		EndIf
		If o\HasIValues Then FreeEntity o\IValues[0]
		If o\HasEntity2 Then FreeEntity o\Entity2
		If o\HasEntity3 Then FreeEntity o\Entity3
		If o\HasEntity4 Then FreeEntity o\Entity4
		If o\HasEntityCube Then FreeEntity o\EntityCube
		If o\HasEntityCube2 Then FreeEntity o\EntityCube2
		If o\HasEntityX Then FreeEntity o\EntityX
		If o\ObjType=OBJTYPE_TROPICAL Then
			For i=1 To 4 : FreeEntity o\ChaoObj\FruitMesh[i] : Next
		EndIf
		FreeEntity o\Entity
		If o\HasGravity Then FreeEntity o\Pivot
		If o\HasExtraTexture Then FreeTexture o\ExtraTexture
		ParticleTemplate_Delete(o\Particle)
		Delete o\Position
		Delete o\Rotation
		Delete o\InitialPosition
		Delete o\InitialRotation
		If o\ThisIsATranslator Then
			Delete o\Translator\CamPosition
			Delete o\Translator\CamRotation
			Delete o\Translator\Destination
		EndIf
		Delete o\Speed
		Delete o\HitBox
		If o\HasValuesetEnemy Then Delete o\Enemy
		If o\HasValuesetEnemyMissile Then Delete o\EnemyMissile
		If o\HasValuesetPiece Then Delete o\Piece
		If o\HasValuesetChaoObj Then Delete o\ChaoObj
		If o\HasValuesetSpew Then Delete o\Spew
		If o\HasValuesetTranslator Then Delete o\Translator
		If o\HasValuesetOmochao Then Delete o\Omochao
		If o\HasValuesetSpike Then Delete o\Spike
		If o\HasValuesetTrap Then Delete o\Trap
		If o\HasValuesetBox Then Delete o\Box
		If o\HasValuesetBubble Then Delete o\Bubble
		If o\HasValuesetGoal Then Delete o\Goal
		If o\HasValuesetMonitor Then Delete o\Monitor
		If o\HasValuesetTreasure Then Delete o\Treasure
		If o\HasValuesetSwitch Then Delete o\Switch
		If o\HasValuesetTeleporter Then Delete o\Teleporter
		If o\HasValuesetHint Then Delete o\Hint
		If o\HasValuesetVisual Then Delete o\Visual
		Delete o
	End Function

	Function Game_Stage_End_Objects()
		For o.tObject = Each tObject
			Game_Stage_End_Objects_ObjectIndividual(o)
		Next

		For b.tBomb = Each tBomb
			If b\Entity<>0 Then FreeEntity b\Entity
			If b\Pivot<>0 Then FreeEntity b\Pivot
			ParticleTemplate_Delete(b\Particle)
			Delete b\Position
			Delete b\Rotation
			If b\HasTarget Then Delete b\Target : FreeEntity b\TargetPivot
			Delete b
		Next

		For ch.tCheese = Each tCheese
			If Menu\Settings\Shadows#>0 Then FreeEntity(ch\ShadowCircle)
			If ch\Entity<>0 Then FreeEntity ch\Entity
			If ch\Mesh<>0 Then FreeEntity ch\Mesh
			ParticleTemplate_Delete(ch\Particle)
			Delete ch\Position
			Delete ch\Emo
			Delete ch
		Next

		For f.tFroggy = Each tFroggy
			If Menu\Settings\Shadows#>0 Then FreeEntity(f\ShadowCircle)
			If f\Entity<>0 Then FreeEntity f\Entity
			If f\Mesh<>0 Then FreeEntity f\Mesh
			Delete f\Position
			Delete f
		Next

		For k.tBoxBlocker = Each tBoxBlocker
			FreeEntity k\Entity
			Delete k\Position
			Delete k\InitialPosition
			Delete k
		Next

		For s.tSwitchManager = Each tSwitchManager
			Delete s
		Next
	End Function

	Function Game_Stage_End_Player()
		For p.tPlayer = Each tPlayer
			DeformCharacter_DeleteTheBoneEntities(p)
			If Menu\Settings\Shadows#>0 and (Menu\ChaoGarden=0 Or Menu\Stage=999) Then FreeEntity(p\Objects\ShadowCircle)
			FreeEntity p\Objects\Staring
			If p\Objects\Entity<>0 Then FreeEntity p\Objects\Entity
			If p\Objects\Mesh<>0 Then FreeEntity p\Objects\Mesh
			If Game\Shield>0 Then FreeEntity p\Objects\Shield
			FreeEntity p\Objects\Follower
			FreeEntity p\Objects\Cheese
			FreeEntity p\Objects\Buddy
			FreeEntity p\Objects\Froggy
                        FreeEntity p\Objects\JumpBall
                        FreeEntity p\Objects\Stomp
						FreeEntity p\Objects\Instashield
			FreeEntity p\Objects\Scanner
			FreeEntity p\Objects\FollowerPlace[1-1]
			FreeEntity p\Objects\FollowerPlace[2-1]
			FreeEntity p\Objects\FollowerPlace[3-1]
			FreeTexture p\Objects\LevitationGlowEmpty
			FreeTexture p\Objects\LevitationGlow
			FreeTexture p\Objects\LevitationGlowMetal
			FreeTexture p\Objects\LevitationGlowDark
			FreeTexture p\Objects\LevitationGlowIce
			FreeTexture p\Objects\LevitationGlowRuby
                        FreeEntity p\Flags\HomingMesh
                        FreeEntity p\Flags\HomingMesh2
                        FreeEntity p\Objects\AirBeg
			If p\HasVehicle>0 Then FreeEntity p\Objects\Vehicle
			ParticleTemplate_Delete(p\Particle)
			ParticleTemplate_Delete(p\Particle2)
			ParticleTemplate_Delete(p\WaterParticle)
			ParticleTemplate_Delete(p\SmokeParticle)
			ParticleTemplate_Delete(p\JetParticle1)
			ParticleTemplate_Delete(p\JetParticle2)
			ParticleTemplate_Delete(p\JetParticle3)
			ParticleTemplate_Delete(p\JetParticle4)
			ParticleTemplate_Delete(p\InvisiParticle)
			ParticleTemplate_Delete(p\SuperAuraParticle)
			ParticleTemplate_Delete(p\BubbleBreatheParticle)
			Player_FreeTrails(p,1)
			Player_FreeLongTrails(p,2)
			For i=0 to PLAYER_VOICES : FreeSound p\Voice[i] : p\Voice[i]=0 : Next
			Delete p\Motion\Speed
			Delete p\Motion\Align
			Delete p\Animation\Align
			;TODO delete follower
			
			Delete p\Objects\Position
			Delete p\Objects
			Delete p\Motion
			Delete p\Animation
			Delete p\Flags
			Delete p\Physics
			
			Delete p
		Next

		For af.tAfterImage = Each tAfterImage 
			FreeEntity(af\Mesh)
			Delete af
		Next

		For rz.tRazer = Each tRazer
			FreeEntity(rz\Mesh)
			FreeEntity(rz\Pivot)
			rz\Alpha# = 0.0
			Delete rz
		Next

		For sp.tSpark = Each tSpark 
			FreeEntity(sp\Entity)
			FreeEntity(sp\Pivot)
			Delete sp
		Next
		
		For ee.tEmerald = Each tEmerald
			For i=1 to 7 : FreeEntity(ee\Mesh[i]) : Next
			Delete ee
		Next
	End Function

	Function Game_Stage_End_Chao()
		For cc.tChaoManager = Each tChaoManager
			If Menu\Settings\Shadows#>0 Then FreeEntity(cc\ShadowCircle)
			If cc\Stats\Hat>0 Then FreeEntity cc\HatMesh
			FreeEntity cc\Mesh
			FreeEntity cc\Pivot
			ParticleTemplate_Delete(cc\Particle)
			Delete cc\Emo
			Delete cc
		Next
	End Function

	Function Game_Stage_End_Stage()
		For m.MeshStructure = Each MeshStructure
			FreeEntity m\Entity
			Select m\AnimTexType
				Case 1: FreeTexture m\DiffuseTexture[1]
				Case 2: For i=1 to 4 : FreeTexture m\DiffuseTexture[i] : Next
			End Select
			Delete m
		Next

		If Menu\Stage<0 Then
			FreeTexture Game\Stage\Properties\SpecialStageTexture
			FreeTexture Game\Stage\Properties\SpecialStageSkydomeTexture
		EndIf

		FreeEntity Game\Stage\Properties\GeneralLight
		FreeEntity Game\Stage\Properties\GeneralLightPivot
		FreeEntity Game\Stage\Root
		FreeEntity Game\Stage\Gravity
		
		For v.tVector = Each tVector
			Delete v
		Next

		ParticleTemplate_Delete(Game\Stage\Properties\AmbientParticle)

		For pt.tParticleTemplate = Each tParticleTemplate
			ParticleTemplate_Delete(pt)
		Next
		FreeParticles()

		FreeEntity Game\Stage\Properties\Skydome
		If Menu\Stage<>0 and Menu\ChaoGarden=1 and Menu\Stage=999 Then
			For i=1 to 3 : FreeEntity Game\Stage\Properties\SkyMesh[i] : Next
			FreeEntity Game\Stage\Properties\SunMoon
		EndIf
		FreeEntity Game\Stage\Properties\Earth
		FreeEntity Game\Stage\Properties\Moon
		FreeEntity Game\Stage\Properties\Sun
		For ls.LightSource = Each LightSource
			FreeEntity ls\Source
			Delete ls
		Next

		If Game\Stage\Properties\Water=1 Then
			If Menu\Settings\WaterQuality#>0 Then FreeWater()
			FreeEntity Game\Stage\Properties\WaterMesh
		EndIf
		If Menu\Stage<>0 Then
			For i=0 to 2
				If Game\Stage\Properties\Music[i]<>0 Then FreeSound Game\Stage\Properties\Music[i]
			Next
		EndIf

		For i =1 to 3
		If Game\CharacterMesh[i]<>0 Then FreeEntity Game\CharacterMesh[i]
		If Game\SuperCharacterMesh[i]<>0 Then FreeEntity Game\SuperCharacterMesh[i]
		;In R9 hypers werent freed, causing a memory leak.
		If Game\HyperCharacterMesh[i]<>0 Then FreeEntity Game\HyperCharacterMesh[i]
		Next
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Game_Stage_End()

		For i=0 to 2 : StopChannel(Game\Stage\Properties\MusicChn[i]) : Next
		StopChannel(Game\Channel_Invincible)
		StopChannel(Game\Channel_SpeedShoes)
		StopChannel(Game\Channel_Drown)
		StopChannel(Game\Channel_MissionCompleted)
		StopChannel(Game\Channel_Result)
		StopChannel(Game\Stage\Properties\Channel_AmbientAlarm)
		StopChannel(Game\Stage\Properties\Channel_AmbientBeach)
		StopChannel(Game\Stage\Properties\Channel_AmbientForest)
		StopChannel(Game\Stage\Properties\Channel_AmbientRain)
		StopChannel(Game\Stage\Properties\Channel_AmbientSnow)
		StopChannel(Game\Stage\Properties\Channel_AmbientVoid)
		StopChannel(Game\Stage\Properties\Channel_AmbientWind)

		Game_Stage_End_Camera()

		Game_Stage_End_Objects()

		Game_Stage_End_Player()

		Game_Stage_End_Chao()

		Game_Stage_End_Stage()

		For x = MESHES_ALWAYSTOTAL+1 to MESHES_TOTAL : FreeSmartEntity(x) : Next

		For x = INTERFACE_ALWAYSTOTAL+1 to INTERFACE_TOTAL : FreeSmartImage(x) : Next

		For x = SOUNDS_ALWAYSTOTAL+1 to SOUNDS_TOTAL : FreeSmartSound(x) : Next
				
		ClearCollisions()

		If Menu\PreviousStage<>0 Then
			For f0.tPostEffect_Fade=Each tPostEffect_Fade
				Select f0\Kind
					Case 0: f0\Alpha#=0.0
					Case 1: f0\Alpha#=1.0
				End Select
			Next
		EndIf
		
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_Restart()

		Game\State=GAME_STATE_END
		Game_Stage_End()
		Input_Lock = True
		ClsColor(0,0,0)
		Cls

		Menu\Mesh[1] = CopyEntity(MESHES(Mesh_Empty))
		Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty))
		Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty))

		Game\State=GAME_STATE_START

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_Quit(mode)
		Game\MustQuitStage=mode
		If Menu\Stage<>0 Then CARD_PLACE#=80 : Menu\ExitedAStage=1
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_Stage_ReallyQuit(mode)

		PostEffect_Create_FadeOut(0.5, 10, 10, 10)
		Menu\Transition=1
		Menu\PreviousStage=Menu\Stage

		If Menu\Stage<>0 Then CARD_PLACE#=80

		Select mode
			Case 1: ;gameover
				If Menu\MarathonMode Then Menu\MarathonGotSpecial=0
				Menu\GameOverType=0
				Menu\Stage=0
				Menu\NewMenu=MENU_GAMEOVER#
				Menu\NewOption=0
			Case 2: ;restart
				If Menu\MarathonMode and Menu\MarathonRandom Then Menu_Stage_RandomizeTeam(true)
				Menu\Stage=Menu\SelectedStage
				Menu\NewMenu=MENU_LOADING#
				Menu\NewOption=0
				Menu\ExitedAStage=0
			Case 3: ;exit
				Menu\Stage=0
				Select Menu\ChaoGarden
				Case 0:
					If Menu\MarathonMode=0 Then
						If Menu\SelectedStage>0 Then Menu\NewMenu=MENU_STAGE2# Else Menu\NewMenu=MENU_STAGESPECIAL#
						Menu\NewOption=Menu\SelectedStage
					Else
						Menu\NewMenu=MENU_PLAY#
						Menu\NewOption=6
					EndIf
				Case 1:
					Menu\NewMenu=MENU_PLAY#
					Menu\NewOption=1
				End Select
				If Menu\ChaoGarden=1 Then
					If Menu\Stage=999 Then SaveGame_AllChaoStuff()
					Menu\ChaoGarden=0
					Menu\SelectedStage=1
				EndIf
			Case 4: ;result quit
				If Menu\MarathonMode Then
					If Menu\SelectedStage>0 Then
						Menu\MarathonStage=Menu\MarathonStage+1
						If Game\ResultRings>=100 and Game\Cheater=0 Then Menu\MarathonGotSpecial=1 Else Menu\MarathonGotSpecial=0
					Else
						Menu\MarathonGotSpecial=0
					EndIf
				EndIf
				If Menu\EmblemsGot=0 Then
					If Menu\MarathonMode=0 Then
						Menu\Stage=0
						If Menu\SelectedStage>0 Then Menu\NewMenu=MENU_STAGE2# Else Menu\NewMenu=MENU_STAGESPECIAL#
						Menu\NewOption=Menu\SelectedStage
					Else
						Menu_GoToMarathonStage()
						If Menu\Stage<>0 Then
							Menu\Stage=Menu\SelectedStage
							Menu\NewMenu=MENU_LOADING#
							Menu\NewOption=0
							Menu\ExitedAStage=0
						EndIf
					EndIf
				Else
					Menu\Stage=0
					Menu\NewMenu=MENU_EMBLEM#
					Menu\NewOption=Menu\SelectedStage
					Menu\OptionOrder=0
				EndIf
			Case 5: ;timeover
				If Menu\MarathonMode Then Menu\MarathonGotSpecial=0
				Menu\GameOverType=1
				Menu\Stage=0
				Menu\NewMenu=MENU_GAMEOVER#
				Menu\NewOption=0
			Case 6: ;blackmarket
				Menu\WentToChaoMenu=0
				Menu\Background=3
				Menu\Stage=0
				Menu\NewMenu=MENU_BLACKMARKET#
				Menu\NewOption=1
				Menu\MeshChange = 1
				SaveGame_AllChaoStuff()
			Case 7: ;chaotransporter
				Menu\WentToChaoMenu=0
				Menu\Background=3
				Menu\Stage=0
				Menu\NewMenu=MENU_TRANSPORTER#
				Menu\NewMenu2=MENU_TRANSPORTER_MAIN#
				If Menu\HeldChaoNumber>0 Then Menu\NewOption=1 Else Menu\NewOption=3
				Menu\MeshChange = 1
				Menu\PreviousBackground2=1
				Menu\Background2=1
				SaveGame_AllChaoStuff()
				Menu\SaveChaoName=0
			Case 8,9: ;chaostadium
				Menu\WentToChaoMenu=0
				Menu\Background=5
				Menu\Stage=0
				Menu\NewMenu=MENU_TRANSPORTER#
				Menu\NewMenu2=MENU_TRANSPORTER_STADIUM#
				If Menu\HeldChaoNumber>0 Then Menu\NewOption=1 Else Menu\NewOption=3
				Menu\MeshChange = 1
				Menu\PreviousBackground2=1
				Menu\Background2=1
				If mode=8 Then SaveGame_AllChaoStuff()
			Case 10: ;principal
				Menu\WentToChaoMenu=0
				Menu\Background=3
				Menu\Stage=0
				Menu\NewMenu=MENU_PRINCIPAL#
				Menu\Convo$=ReadAndFindTextExplanation$("Text_ChaoLessons",0)
				Menu\NewOption=1
				Menu\OptionOrder=0
				Menu\MeshChange = 1
				SaveGame_AllChaoStuff()
		End Select
		Select Menu\NewMenu
			Case MENU_EMBLEM#,MENU_BLACKMARKET#,MENU_TRANSPORTER#,MENU_PRINCIPAL#,MENU_GAMEOVER#: Menu\Menu=Menu\NewMenu
			Default: Menu\Menu=0
		End Select
		Menu\Menu2=0
		Select mode
			Case 7,8,9:
			Default: Menu\NewMenu2=0
		End Select

		Game\Victory=0

		Game_Stage_Restart()
		Menu\SavedLives=Game\Gameplay\Lives
		SaveGame()

	End Function


	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function PauseAllChannels()
			For i=0 to 2 : PauseChannel(Game\Stage\Properties\MusicChn[i]) : Next
			PauseChannel(Game\Channel_Invincible)
			PauseChannel(Game\Channel_SpeedShoes)
			PauseChannel(Game\Channel_Drown)
			PauseChannel(Game\Channel_MissionCompleted)
			PauseChannel(Game\Channel_Result)
			PauseChannel(Game\Channel_1Up)
			PauseChannel(Game\Channel_ChaoEffect)
			PauseChannel(Game\Stage\Properties\Channel_AmbientAlarm)
			PauseChannel(Game\Stage\Properties\Channel_AmbientBeach)
			PauseChannel(Game\Stage\Properties\Channel_AmbientForest)
			PauseChannel(Game\Stage\Properties\Channel_AmbientRain)
			PauseChannel(Game\Stage\Properties\Channel_AmbientSnow)
			PauseChannel(Game\Stage\Properties\Channel_AmbientVoid)
			PauseChannel(Game\Stage\Properties\Channel_AmbientWind)
			PauseChannel(Game\Stage\Properties\WaterLevelChangeChannel)

			For b.tBomb = Each tBomb
				PauseChannel(b\Channel_Bomb)
			Next

			For cc.tChaoManager = Each tChaoManager
				PauseChannel(cc\Channel_Voice)
			Next

			For ch.tCheese = Each tCheese
				PauseChannel(ch\Channel_Chao)
			Next

			For o.tObject = Each tObject
				If o\ThisIsAnEnemy Then
					PauseChannel(o\Enemy\Channel_EnemyStep)
					PauseChannel(o\Enemy\Channel_EnemyStep2)
					PauseChannel(o\Enemy\Channel_EnemyState)
					PauseChannel(o\Enemy\Channel_EnemySwim)
				ElseIf o\ThisIsATranslator Then
					PauseChannel(o\Translator\Channel_Fan)
				ElseIf o\ThisIsAnEnemyMissile Then
					PauseChannel(o\EnemyMissile\Channel_State)
				Else
					Select o\ObjType
						Case OBJTYPE_OMOCHAO:
							PauseChannel(o\Omochao\Channel_Omochao)
							PauseChannel(o\Omochao\Channel_OmochaoFly)
						Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
							PauseChannel(o\Trap\Channel_Spout)
						Case OBJTYPE_GOAL,OBJTYPE_GOAL2:
							PauseChannel(o\Goal\Channel_GoalIdle)
						Case OBJTYPE_SPRINKLER,OBJTYPE_SEAGULL,OBJTYPE_HELICOPTER:
							PauseChannel(o\Visual\Channel_Visual)
					End Select
				EndIf
			Next

			For p.tPlayer = Each tPlayer
				PauseChannel(p\Channel_Voice)
				PauseChannel(p\Channel_GroundSkid)
				PauseChannel(p\Channel_GroundStep)
				PauseChannel(p\Channel_GroundStep2)
				PauseChannel(p\Channel_GroundLand)
				PauseChannel(p\Channel_GroundLand2)
				PauseChannel(p\Channel_GroundFly)
				PauseChannel(p\Channel_Fly)
				PauseChannel(p\Channel_Glide)
				PauseChannel(p\Channel_GlideX)
				PauseChannel(p\Channel_Levitate)
				PauseChannel(p\Channel_WaterRunning)
				PauseChannel(p\Channel_Drift)
				PauseChannel(p\Channel_Stomp)
				PauseChannel(p\Channel_Climb)
				PauseChannel(p\Channel_Grind)
				PauseChannel(p\Channel_Spin)
				PauseChannel(p\Channel_Fire)
				PauseChannel(p\Channel_Water)
				PauseChannel(p\Channel_Psychokinesis)
				PauseChannel(p\Channel_Tinkle)
				PauseChannel(p\Channel_ChaosDrive)
			Next
	End Function

	Function ResumeAllChannels()
			For i=0 to 2 : ResumeChannel(Game\Stage\Properties\MusicChn[i]) : Next
			ResumeChannel(Game\Channel_Invincible)
			ResumeChannel(Game\Channel_SpeedShoes)
			ResumeChannel(Game\Channel_Drown)
			ResumeChannel(Game\Channel_MissionCompleted)
			ResumeChannel(Game\Channel_Result)
			ResumeChannel(Game\Channel_1Up)
			ResumeChannel(Game\Channel_ChaoEffect)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientAlarm)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientBeach)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientForest)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientRain)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientSnow)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientVoid)
			ResumeChannel(Game\Stage\Properties\Channel_AmbientWind)
			ResumeChannel(Game\Stage\Properties\WaterLevelChangeChannel)

			For b.tBomb = Each tBomb
				ResumeChannel(b\Channel_Bomb)
			Next

			For cc.tChaoManager = Each tChaoManager
				ResumeChannel(cc\Channel_Voice)
			Next

			For ch.tCheese = Each tCheese
				ResumeChannel(ch\Channel_Chao)
			Next

			For o.tObject = Each tObject
				If o\ThisIsAnEnemy Then
					ResumeChannel(o\Enemy\Channel_EnemyStep)
					ResumeChannel(o\Enemy\Channel_EnemyStep2)
					ResumeChannel(o\Enemy\Channel_EnemyState)
					ResumeChannel(o\Enemy\Channel_EnemySwim)
				ElseIf o\ThisIsATranslator Then
					ResumeChannel(o\Translator\Channel_Fan)
				ElseIf o\ThisIsAnEnemyMissile Then
					ResumeChannel(o\EnemyMissile\Channel_State)
				Else
					Select o\ObjType
						Case OBJTYPE_OMOCHAO:
							ResumeChannel(o\Omochao\Channel_Omochao)
							ResumeChannel(o\Omochao\Channel_OmochaoFly)
						Case OBJTYPE_FLAMESPOUT,OBJTYPE_ICESPOUT,OBJTYPE_SHOCKSPOUT:
							ResumeChannel(o\Trap\Channel_Spout)
						Case OBJTYPE_GOAL,OBJTYPE_GOAL2:
							ResumeChannel(o\Goal\Channel_GoalIdle)
						Case OBJTYPE_SPRINKLER,OBJTYPE_SEAGULL,OBJTYPE_HELICOPTER:
							ResumeChannel(o\Visual\Channel_Visual)
					End Select
				EndIf
			Next

			For p.tPlayer = Each tPlayer
				ResumeChannel(p\Channel_Voice)
				ResumeChannel(p\Channel_GroundSkid)
				ResumeChannel(p\Channel_GroundStep)
				ResumeChannel(p\Channel_GroundStep2)
				ResumeChannel(p\Channel_GroundLand)
				ResumeChannel(p\Channel_GroundLand2)
				ResumeChannel(p\Channel_GroundFly)
				ResumeChannel(p\Channel_Fly)
				ResumeChannel(p\Channel_Glide)
				ResumeChannel(p\Channel_GlideX)
				ResumeChannel(p\Channel_Levitate)
				ResumeChannel(p\Channel_WaterRunning)
				ResumeChannel(p\Channel_Drift)
				ResumeChannel(p\Channel_Stomp)
				ResumeChannel(p\Channel_Climb)
				ResumeChannel(p\Channel_Grind)
				ResumeChannel(p\Channel_Spin)
				ResumeChannel(p\Channel_Fire)
				ResumeChannel(p\Channel_Water)
				ResumeChannel(p\Channel_Psychokinesis)
				ResumeChannel(p\Channel_Tinkle)
				ResumeChannel(p\Channel_ChaosDrive)
			Next
	End Function