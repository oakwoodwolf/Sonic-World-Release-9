
	i = -1
	Global CHAOACTION_PICKEDUP	= i : i=i+1
	Global CHAOACTION_COMMON	= i : i=i+1
	Global CHAOACTION_THROWN	= i : i=i+1
	Global CHAOACTION_HURT		= i : i=i+1
	Global CHAOACTION_DANCE		= i : i=i+1
	Global CHAOACTION_SWIM		= i : i=i+1
	Global CHAOACTION_FLY		= i : i=i+1
	Global CHAOACTION_EAT		= i : i=i+1
	Global CHAOACTION_SLEEP		= i : i=i+1
	Global CHAOACTION_WAKE		= i : i=i+1
	Global CHAOACTION_BORN		= i : i=i+1
	Global CHAOACTION_SUCK		= i : i=i+1
	Global CHAOACTION_THINK		= i : i=i+1
	Global CHAOACTION_WEAR		= i : i=i+1
	Global CHAOACTION_WAITBREED	= i : i=i+1
	Global CHAOACTION_BREED		= i : i=i+1
	Global CHAOACTION_PLAY		= i : i=i+1
	Global CHAOACTION_BALL		= i : i=i+1
	Global CHAOACTION_TEDDYBEAR	= i : i=i+1
	Global CHAOACTION_SING		= i : i=i+1
	Global CHAOACTION_LIFT		= i : i=i+1
	Global CHAOACTION_RATTLE	= i : i=i+1
	Global CHAOACTION_DRIVE		= i : i=i+1
	Global CHAOACTION_DRIVEDUCK	= i : i=i+1
	Global CHAOACTION_PILLOWSLEEP	= i : i=i+1
	Global CHAOACTION_WATCH	= i : i=i+1
	Global CHAOACTION_PETTED	= i : i=i+1
	Global CHAOACTION_RACE_BEGIN	= i : i=i+1
	Global CHAOACTION_RACE_COMMON	= i : i=i+1
	Global CHAOACTION_RACE_SWIM	= i : i=i+1
	Global CHAOACTION_RACE_FLY	= i : i=i+1
	Global CHAOACTION_RACE_CLIMB	= i : i=i+1
	Global CHAOACTION_RACE_WIN	= i : i=i+1
	Global CHAOACTION_RACE_LOSE	= i : i=i+1
	Global CHAOACTION_RACE_LOST	= i : i=i+1
	Global CHAOACTION_KARATE_BEGIN	= i : i=i+1
	Global CHAOACTION_KARATE_COMMON	= i : i=i+1
	Global CHAOACTION_KARATE_KICK	= i : i=i+1
	Global CHAOACTION_KARATE_PUNCH	= i : i=i+1
	Global CHAOACTION_KARATE_THROWN	= i : i=i+1
	Global CHAOACTION_KARATE_HURT	= i : i=i+1
	Global CHAOACTION_KARATE_WIN	= i : i=i+1
	Global CHAOACTION_KARATE_LOSE	= i : i=i+1
	Global CHAOACTION_KARATE_MEDITATE=i : i=i+1
	Global CHAOACTION_KARATE_DODGE	= i : i=i+1
	Global CHAOACTION_BUDDY	= i : i=i+1
	

	Function ChaoManager_ManageActions(cc.tChaoManager, d.tDeltaTime)

		If Menu\Settings\Shadows#>0 Then Update_CircleShadow(cc\ShadowCircle, cc\Pivot, cam\Entity)

		;action determine
		If cc\obj\ObjPickedUp=1 Then cc\Action=CHAOACTION_PICKEDUP
		Select cc\Action
			Case CHAOACTION_PICKEDUP:
				ChaoManager_Action_PickedUp(cc)
			Case CHAOACTION_COMMON:
				If ChaoManager_ChaoAlive(cc) Then ChaoManager_Action_Common(cc,d)
			Case CHAOACTION_THROWN:
				ChaoManager_Action_Thrown(cc)
			Case CHAOACTION_HURT:
				ChaoManager_Action_Hurt(cc)
			Case CHAOACTION_DANCE:
				ChaoManager_Action_Dance(cc)
			Case CHAOACTION_SWIM:
				ChaoManager_Action_Swim(cc,d)
			Case CHAOACTION_FLY:
				ChaoManager_Action_Fly(cc,d)
			Case CHAOACTION_EAT:
				ChaoManager_Action_Eat(cc)
			Case CHAOACTION_SLEEP:
				ChaoManager_Action_Sleep(cc)
			Case CHAOACTION_WAKE
				ChaoManager_Action_Wake(cc)
			Case CHAOACTION_BORN:
				ChaoManager_Action_Born(cc)
			Case CHAOACTION_SUCK:
				ChaoManager_Action_Suck(cc)
			Case CHAOACTION_THINK:
				ChaoManager_Action_Think(cc)
			Case CHAOACTION_WEAR:
				ChaoManager_Action_Wear(cc)
			Case CHAOACTION_WAITBREED:
				ChaoManager_Action_WaitBreed(cc)
			Case CHAOACTION_BREED:
				ChaoManager_Action_Breed(cc)
			Case CHAOACTION_PLAY:
				ChaoManager_Action_Play(cc)
			Case CHAOACTION_WATCH:
				ChaoManager_Action_Watch(cc)
			Case CHAOACTION_BALL:
				ChaoManager_Action_Ball(cc,d)
			Case CHAOACTION_TEDDYBEAR:
				ChaoManager_Action_TeddyBear(cc)
			Case CHAOACTION_SING:
				ChaoManager_Action_Sing(cc)
			Case CHAOACTION_LIFT:
				ChaoManager_Action_Lift(cc)
			Case CHAOACTION_RATTLE:
				ChaoManager_Action_Rattle(cc)
			Case CHAOACTION_DRIVE:
				ChaoManager_Action_Drive(cc,d)
			Case CHAOACTION_DRIVEDUCK:
				ChaoManager_Action_DriveDuck(cc,d)
			Case CHAOACTION_PILLOWSLEEP:
				ChaoManager_Action_PillowSleep(cc)
			Case CHAOACTION_PETTED:
				ChaoManager_Action_Petted(cc)
			Case CHAOACTION_RACE_BEGIN:
				ChaoManager_Action_Race_Begin(cc,d)
			Case CHAOACTION_RACE_COMMON:
				ChaoManager_Action_Race_Common(cc,d)
			Case CHAOACTION_RACE_SWIM:
				ChaoManager_Action_Race_Swim(cc,d)
			Case CHAOACTION_RACE_FLY:
				ChaoManager_Action_Race_Fly(cc,d)
			Case CHAOACTION_RACE_CLIMB:
				ChaoManager_Action_Race_Climb(cc,d)
			Case CHAOACTION_RACE_WIN,CHAOACTION_RACE_LOSE:
				ChaoManager_Action_Race_WinOrLose(cc,d)
			Case CHAOACTION_RACE_LOST:
				ChaoManager_Action_Race_Lost(cc,d)
			Case CHAOACTION_KARATE_BEGIN:
				ChaoManager_Action_Karate_Begin(cc,d)
			Case CHAOACTION_KARATE_COMMON:
				ChaoManager_Action_Karate_Common(cc,d)
			Case CHAOACTION_KARATE_KICK,CHAOACTION_KARATE_PUNCH:
				ChaoManager_Action_Karate_Attack(cc,d)
			Case CHAOACTION_KARATE_THROWN:
				ChaoManager_Action_Karate_Thrown(cc,d)
			Case CHAOACTION_KARATE_HURT:
				ChaoManager_Action_Karate_Hurt(cc,d)
			Case CHAOACTION_KARATE_WIN,CHAOACTION_KARATE_LOSE:
				ChaoManager_Action_Karate_WinOrLose(cc,d)
			Case CHAOACTION_KARATE_MEDITATE:
				ChaoManager_Action_Karate_Meditate(cc,d)
			Case CHAOACTION_KARATE_DODGE:
				ChaoManager_Action_Karate_Dodge(cc,d)
			Case CHAOACTION_BUDDY:
				ChaoManager_Action_Buddy(cc,d)
		End Select

		;animation determine
		If ChaoManager_ChaoAlive(cc) Then
		Select cc\Action
			Case CHAOACTION_PICKEDUP:
				If cc\Stats\CharacterLove[pp(1)\Character] >= -50 Then cc\Animation=CHAOANIMATION_IDLEAIR Else cc\Animation=CHAOANIMATION_DROWN			
			Case CHAOACTION_COMMON,CHAOACTION_RACE_COMMON,CHAOACTION_RACE_BEGIN,CHAOACTION_KARATE_BEGIN:
				Select cc\g\Motion\Ground
				Case True:
					If cc\g\SpeedLength#>0 Then
						If cc\Stats\Age<=1 and cc\Stats\Run#<4 Then
							cc\Animation=CHAOANIMATION_CRAWL
						ElseIf cc\Stats\Run#<10 Then
							cc\Animation=CHAOANIMATION_WALK
						Else
							cc\Animation=CHAOANIMATION_RUN
						EndIf
					Else
						If cc\Stats\Age<=1 and cc\Stats\Run#<4 Then
							cc\Animation=CHAOANIMATION_SIT
						Else
							If cc\WhistleLoveTimer>0 Then
								cc\Animation=CHAOANIMATION_SHY
							ElseIf cc\WhistleThinkTimer>0 Then
								cc\Animation=CHAOANIMATION_THINK
							Else
								cc\Animation=CHAOANIMATION_IDLE
							EndIf
						EndIf
					EndIf
				Case False:
					If cc\g\SpeedLength#>0 Then
						If cc\Stats\Age<=1 and cc\Stats\Run#<4 Then
							cc\Animation=CHAOANIMATION_WALKAIR
						ElseIf cc\Stats\Run#<10 Then
							cc\Animation=CHAOANIMATION_WALKAIR
						Else
							cc\Animation=CHAOANIMATION_RUNAIR
						EndIf
					Else
						cc\Animation=CHAOANIMATION_IDLEAIR
					EndIf
				End Select
			Case CHAOACTION_THROWN,CHAOACTION_KARATE_THROWN:
				cc\Animation=CHAOANIMATION_THROWN
			Case CHAOACTION_HURT,CHAOACTION_KARATE_HURT:
				cc\Animation=CHAOANIMATION_HURT
			Case CHAOACTION_DANCE,CHAOACTION_SING:
				cc\Animation=CHAOANIMATION_DANCE
			Case CHAOACTION_SWIM,CHAOACTION_RACE_SWIM:
				If cc\Stats\Swim#<3 Then
					cc\Animation=CHAOANIMATION_DROWN
				Else
					cc\Animation=CHAOANIMATION_SWIM
				EndIf
			Case CHAOACTION_BORN,CHAOACTION_FLY,CHAOACTION_RACE_FLY,CHAOACTION_KARATE_DODGE:
				If cc\g\SpeedLength#>0 Then
					If cc\Stats\Run#<10 Then
						cc\Animation=CHAOANIMATION_WALKAIR
					Else
						cc\Animation=CHAOANIMATION_RUNAIR
					EndIf
				Else
					cc\Animation=CHAOANIMATION_IDLEAIR
				EndIf
			Case CHAOACTION_EAT:
				If cc\EatingWaiting=1 Then
					cc\Animation=CHAOANIMATION_EAT
				Else
					cc\Animation=CHAOANIMATION_WAIT
				EndIf
			Case CHAOACTION_SLEEP,CHAOACTION_WAKE,CHAOACTION_PILLOWSLEEP:
				cc\Animation=CHAOANIMATION_LAY
			Case CHAOACTION_SUCK:
				If cc\EatingWaiting=1 Then
					cc\Animation=CHAOANIMATION_EMBRACE
				Else
					cc\Animation=CHAOANIMATION_WAIT
				EndIf
			Case CHAOACTION_THINK,CHAOACTION_WEAR,CHAOACTION_PLAY:
				cc\Animation=CHAOANIMATION_THINK
			Case CHAOACTION_WAITBREED:
				cc\Animation=CHAOANIMATION_SIT
			Case CHAOACTION_BREED:
				cc\Animation=CHAOANIMATION_EMBRACE
			Case CHAOACTION_BALL:
				Select cc\PlayOrder
					Case 1: cc\Animation=CHAOANIMATION_RUN
					Case 2: cc\Animation=CHAOANIMATION_EXCLAMATION
					Case 3: cc\Animation=CHAOANIMATION_RUN
				End Select
			Case CHAOACTION_WATCH:
				Select cc\PlayOrder
					Case 1: cc\Animation=CHAOANIMATION_IDLE
					Case 2: cc\Animation=CHAOANIMATION_SIT
				End Select
			Case CHAOACTION_DRIVE:
				Select cc\PlayOrder
					Case 1: cc\Animation=CHAOANIMATION_RUN
					Case 2: cc\Animation=CHAOANIMATION_IDLE
				End Select
			Case CHAOACTION_DRIVEDUCK:
				Select cc\PlayOrder
					Case 1: cc\Animation=CHAOANIMATION_SWIM
					Case 2: cc\Animation=CHAOANIMATION_IDLEAIR
				End Select
			Case CHAOACTION_TEDDYBEAR:
				cc\Animation=CHAOANIMATION_HUG
			Case CHAOACTION_RATTLE:
				cc\Animation=CHAOANIMATION_LAUGH
			Case CHAOACTION_LIFT:
				Select cc\PlayOrder
					Case 1: cc\Animation=CHAOANIMATION_IDLE
					Case 2: cc\Animation=CHAOANIMATION_EXCLAMATION
				End Select
			Case CHAOACTION_PETTED:
				If cc\Stats\Age<=1 and cc\Stats\Run#<4 Then
					cc\Animation=CHAOANIMATION_SIT
				Else
					cc\Animation=CHAOANIMATION_IDLE
				EndIf
			Case CHAOACTION_RACE_CLIMB:
				cc\Animation=CHAOANIMATION_CLIMB
			Case CHAOACTION_RACE_WIN,CHAOACTION_KARATE_WIN:
				cc\Animation=CHAOANIMATION_WIN
			Case CHAOACTION_RACE_LOSE,CHAOACTION_RACE_LOST,CHAOACTION_KARATE_LOSE:
				cc\Animation=CHAOANIMATION_SORRY
			Case CHAOACTION_KARATE_COMMON:
				If cc\g\SpeedLength#>0 Then
					If cc\Stats\Age<=1 and cc\Stats\Run#<4 Then
						cc\Animation=CHAOANIMATION_CRAWL
					ElseIf cc\Stats\Run#<10 Then
						cc\Animation=CHAOANIMATION_WALK
					Else
						cc\Animation=CHAOANIMATION_RUN
					EndIf
				Else
					cc\Animation=CHAOANIMATION_INTIMIDATE
				EndIf
			Case CHAOACTION_KARATE_KICK:
				If cc\Number=1 Then cc\Animation=CHAOANIMATION_KICKR Else cc\Animation=CHAOANIMATION_KICKL
			Case CHAOACTION_KARATE_PUNCH:
				If cc\Number=1 Then cc\Animation=CHAOANIMATION_PUNCHR Else cc\Animation=CHAOANIMATION_PUNCHL
			Case CHAOACTION_KARATE_MEDITATE:
				cc\Animation=CHAOANIMATION_WAIT
		End Select
		Else
			If ChaoManager_ChaoCocoonAlive(cc) Then
				cc\Animation=CHAOANIMATION_SIT
				If (1/(cc\HatchTimer/secs#))<0.375 Then
					EntityAlpha cc\CocoonMesh, 0.375
				Else
					EntityAlpha cc\CocoonMesh, (1/(cc\HatchTimer/secs#))
				EndIf
			EndIf
		EndIf

		;emotion determine
		If ChaoManager_ChaoAlive(cc) And cc\Stats\Body=0 Then
			If cc\obj\ObjPickedUp=1 Then cc\Emo\CenterOffice=True Else cc\Emo\CenterOffice=False

			If cc\HurtTimer>0 Then
				cc\Emo\Emotion=CHAOEMO_hurt
			ElseIf cc\JustGotHappyTimer>0 Then
				cc\Emo\Emotion=CHAOEMO_lovely
			ElseIf cc\JustGotSadTimer>0 Then
				cc\Emo\Emotion=CHAOEMO_sad
			Else
				Select cc\Action
					Case CHAOACTION_BUDDY:
					Case CHAOACTION_DANCE:
						Select cc\Stats\Persona
							Case CHAOEMO_happy,CHAOEMO_thrilled,CHAOEMO_joyful:
								cc\Emo\Emotion=CHAOEMO_cheerful
							Default:
								cc\Emo\Emotion=ChaoManager_GetDefaultPersona(cc)
						End Select
					Case CHAOACTION_EAT,CHAOACTION_SUCK:
						Select cc\EatingWaiting
							Case 1:	If cc\Stats\FavouriteFood<>cc\Target\ChaoObj\FruitType Then cc\Emo\Emotion=CHAOEMO_happy Else cc\Emo\Emotion=CHAOEMO_thrilled
							Case 3:	If cc\Stats\FavouriteFood<>cc\Target\ChaoObj\FruitType Then cc\Emo\Emotion=CHAOEMO_disgusted Else cc\Emo\Emotion=CHAOEMO_joyful
							Default: cc\Emo\Emotion=CHAOEMO_default
						End Select
					Case CHAOACTION_SLEEP,CHAOACTION_PILLOWSLEEP:
						cc\Emo\Emotion=CHAOEMO_drowsy
					Case CHAOACTION_THINK,CHAOACTION_WEAR,CHAOACTION_PLAY:
						cc\Emo\Emotion=CHAOEMO_curious
					Case CHAOACTION_WAITBREED:
						cc\Emo\Emotion=CHAOEMO_lovely
					Case CHAOACTION_BREED:
						cc\Emo\Emotion=CHAOEMO_flirty
					Case CHAOACTION_BALL:
						Select cc\PlayOrder
							Case 1: cc\Emo\Emotion=CHAOEMO_joyful
							Case 2: cc\Emo\Emotion=CHAOEMO_surprised
							Case 3: cc\Emo\Emotion=CHAOEMO_happy
						End Select
					Case CHAOACTION_DRIVE,CHAOACTION_DRIVEDUCK:
						Select cc\PlayOrder
							Case 1: cc\Emo\Emotion=CHAOEMO_joyful
							Case 2: cc\Emo\Emotion=CHAOEMO_happy
						End Select
					Case CHAOACTION_PICKEDUP:
						If cc\Stats\CharacterLove[pp(1)\Character]> 50 Then
							cc\Emo\Emotion=CHAOEMO_cheerful
						ElseIf cc\Stats\CharacterLove[pp(1)\Character]<-50 Then
							cc\Emo\Emotion=CHAOEMO_scared
						Else
							cc\Emo\Emotion=ChaoManager_GetDefaultPersona(cc)
						EndIf
					Case CHAOACTION_TEDDYBEAR,CHAOACTION_SING:
						cc\Emo\Emotion=CHAOEMO_cheerful
					Case CHAOACTION_RATTLE:
						cc\Emo\Emotion=CHAOEMO_thrilled
					Case CHAOACTION_LIFT:
						Select cc\PlayOrder
							Case 1: cc\Emo\Emotion=CHAOEMO_default
							Case 2: cc\Emo\Emotion=CHAOEMO_angry
						End Select
					Case CHAOACTION_PETTED:
						cc\Emo\Emotion=CHAOEMO_lovely
					Case CHAOACTION_RACE_WIN,CHAOACTION_KARATE_WIN:
						cc\Emo\Emotion=CHAOEMO_happy
					Case CHAOACTION_RACE_LOSE,CHAOACTION_RACE_LOST,CHAOACTION_KARATE_LOSE:
						cc\Emo\Emotion=CHAOEMO_sad
					Case CHAOACTION_KARATE_KICK,CHAOACTION_KARATE_PUNCH:
						cc\Emo\Emotion=CHAOEMO_angry
					Case CHAOACTION_KARATE_THROWN,CHAOACTION_KARATE_HURT:
						cc\Emo\Emotion=CHAOEMO_hurt
					Case CHAOACTION_KARATE_MEDITATE:
						cc\Emo\Emotion=CHAOEMO_drowsy
					Case CHAOACTION_KARATE_DODGE:
						cc\Emo\Emotion=CHAOEMO_thrilled
					Default:
						If Not(cc\FollowWhistleTimer>0) Then
							If cc\WhistleLoveTimer>0 Then
								cc\Emo\Emotion=CHAOEMO_lovely
							ElseIf cc\WhistleThinkTimer>0 Then
								If cc\Stats\CharacterLove[pp(1)\Character] > -50 Then cc\Emo\Emotion=CHAOEMO_curious Else cc\Emo\Emotion=CHAOEMO_angry
							Else
								cc\Emo\Emotion=ChaoManager_GetDefaultPersona(cc)
							EndIf
						Else
							cc\Emo\Emotion=CHAOEMO_surprised
						EndIf
				End Select
			EndIf
		Else
			If ChaoManager_ChaoCocoonAlive(cc) And cc\Stats\Body=0 Then cc\Emo\Emotion=CHAOEMO_drowsy
		EndIf

		;voice determine
		If ChaoManager_ChaoAlive(cc) And cc\Stats\Body=0 Then
			Select cc\Action
				Case CHAOACTION_PICKEDUP:
					If cc\Stats\CharacterLove[pp(1)\Character]> 50 Then
							cc\VoiceEmo=CHAOVOICEEMO_LAUGHING
						ElseIf cc\Stats\CharacterLove[pp(1)\Character]<-50 Then
							cc\VoiceEmo=CHAOVOICEEMO_REFUSING
						Else
							cc\VoiceEmo=CHAOVOICEEMO_CASUAL
						EndIf
				Case CHAOACTION_BUDDY: 
					Select cc\targetp\Action
						Case ACTION_THROW, ACTION_GATLING, ACTION_THRUST, ACTION_SHOOT, ACTION_SHOOTHOVER, ACTION_PUNCH, ACTION_UPPERCUT, ACTION_PSYCHO, ACTION_SPRINT:
							cc\VoiceEmo=CHAOVOICEEMO_ATTACK
						Case ACTION_HURT, ACTION_DIE:
							cc\VoiceEmo=CHAOVOICEEMO_HURT
						Case ACTION_VICTORY, ACTION_VICTORYHOLD:
							cc\VoiceEmo=CHAOVOICEEMO_SINGING
						Case ACTION_UP, ACTION_FLOAT, ACTION_SLOWGLIDE, ACTION_SLEET, ACTION_GLIDE, ACTION_SKYDIVE:
							cc\VoiceEmo=CHAOVOICEEMO_CRAZY
						Default:
							If (cc\Underwater=1) Then
								If cc\Stats\Swim#<3 Then
									cc\VoiceEmo=CHAOVOICEEMO_DROWNING
								Else
									cc\VoiceEmo=CHAOVOICEEMO_HUMMING
								EndIf
							Else
								cc\VoiceEmo=CHAOVOICEEMO_CASUAL
							EndIf
					End Select
				Case CHAOACTION_THROWN,CHAOACTION_KARATE_THROWN:
					cc\VoiceEmo=CHAOVOICEEMO_FRUSTRATED
				Case CHAOACTION_HURT,CHAOACTION_KARATE_HURT:
					cc\VoiceEmo=CHAOVOICEEMO_HURT
				Case CHAOACTION_DANCE,CHAOACTION_SINGING:
					cc\VoiceEmo=CHAOVOICEEMO_SINGING
				Case CHAOACTION_SWIM,CHAOACTION_RACE_SWIM:
					If cc\Stats\Swim#<3 Then
						cc\VoiceEmo=CHAOVOICEEMO_DROWNING
					Else
						cc\VoiceEmo=CHAOVOICEEMO_CASUAL
					EndIf
				Case CHAOACTION_FLY:
					cc\VoiceEmo=CHAOVOICEEMO_CRAZY
				Case CHAOACTION_EAT:
					If cc\EatingWaiting=1 Then
						cc\VoiceEmo=CHAOVOICEEMO_EATING
					ElseIf cc\EatingWaiting=3 Then
						If cc\Stats\FavouriteFood=cc\Target\ChaoObj\FruitType Then cc\VoiceEmo=CHAOVOICEEMO_SATISFIED Else cc\VoiceEmo=CHAOVOICEEMO_DISGUSTED
					Else
						If cc\FoundTarget Then
							If cc\Stats\FavouriteFood=cc\Target\ChaoObj\FruitType Then cc\VoiceEmo=CHAOVOICEEMO_FIGURED Else cc\VoiceEmo=CHAOVOICEEMO_CASUAL
						Else
							cc\VoiceEmo=CHAOVOICEEMO_SATISFIED
						EndIf
					EndIf
				Case CHAOACTION_SLEEP,CHAOACTION_PILLOWSLEEP:
					cc\VoiceEmo=CHAOVOICEEMO_SLEEPING
				Case CHAOACTION_WAKE:
					cc\VoiceEmo=CHAOVOICEEMO_WAKING
				Case CHAOACTION_BORN:
					cc\VoiceEmo=CHAOVOICEEMO_READY
				Case CHAOACTION_SUCK:
					cc\VoiceEmo=CHAOVOICEEMO_SURPRISED
				Case CHAOACTION_THINK:
					cc\VoiceEmo=CHAOVOICEEMO_THINKING
				Case CHAOACTION_WEAR,CHAOACTION_PLAY:
					cc\VoiceEmo=CHAOVOICEEMO_FIGURE
				Case CHAOACTION_WAITBREED:
					cc\VoiceEmo=CHAOVOICEEMO_HUMMING
				Case CHAOACTION_BREED:
					cc\VoiceEmo=CHAOVOICEEMO_PLAYING
				Case CHAOACTION_BALL:
					Select cc\PlayOrder
						Case 1: cc\VoiceEmo=CHAOVOICEEMO_PLAYING
						Case 2: cc\VoiceEmo=CHAOVOICEEMO_AMAZED
						Case 3: cc\VoiceEmo=CHAOVOICEEMO_PLAYING
					End Select
				Case CHAOACTION_DRIVE,CHAOACTION_DRIVEDUCK:
					Select cc\PlayOrder
						Case 1: cc\VoiceEmo=CHAOVOICEEMO_PLAYING
						Case 2: cc\VoiceEmo=CHAOVOICEEMO_AMAZED
					End Select
				Case CHAOACTION_TEDDYBEAR,CHAOACTION_RATTLE:
					cc\VoiceEmo=CHAOVOICEEMO_AMAZED
				Case CHAOACTION_LIFT:
					cc\VoiceEmo=CHAOVOICEEMO_ATTACK
				Case CHAOACTION_PETTED:
					cc\VoiceEmo=CHAOVOICEEMO_SATISFIED
				Case CHAOACTION_RACE_LOSE,CHAOACTION_RACE_LOST,CHAOACTION_KARATE_LOSE:
					If Game\Interface\RaceEnded=False Then
						cc\VoiceEmo=CHAOVOICEEMO_SAD
					Else
						cc\VoiceEmo=CHAOVOICEEMO_CASUAL
					EndIf
				Case CHAOACTION_RACE_WIN,CHAOACTION_KARATE_WIN:
					cc\VoiceEmo=CHAOVOICEEMO_AMAZED
				Case CHAOACTION_KARATE_KICK,CHAOACTION_KARATE_PUNCH:
					cc\VoiceEmo=CHAOVOICEEMO_ATTACK
				Case CHAOACTION_KARATE_MEDITATE:
					cc\VoiceEmo=CHAOVOICEEMO_YAWNING
				Case CHAOACTION_KARATE_DODGE:
					cc\VoiceEmo=CHAOVOICEEMO_REFUSING
				Default:
					If cc\Stats\Happiness >-25 Then cc\VoiceEmo=CHAOVOICEEMO_CASUAL Else cc\VoiceEmo=CHAOVOICEEMO_SAD
			End Select
			ChaoManager_UpdateVoice(cc)
		EndIf

		; hit a wall
		If cc\g\Motion\WallWasHitTimer>0 and (Not(cc\FollowWhistleTimer>0)) Then
			cc\g\Motion\Direction#=cc\g\Motion\Direction#-180
			cc\WanderDirection=cc\g\Motion\Direction#
			cc\WanderDirectionTimer=4.25*secs#
			cc\g\Motion\WallWasHitTimer=0
			cc\g\Motion\ChangedWallTimer=1*secs#
		EndIf
		
		; Control needs
		If ChaoManager_ChaoAlive(cc) and Menu\Stage=999 Then
			If (Not(cc\GetHungryTimer>0)) and (Not(cc\Action=CHAOACTION_EAT)) Then
				cc\Stats\Hunger#=cc\Stats\Hunger#+1
				cc\GetHungryTimer=(100+cc\Stats\Stamina#)*secs#
				If cc\Stats\TooFull#>0 Then cc\Stats\TooFull#=cc\Stats\TooFull#-1
			EndIf
			If (Not(cc\GetSleepyTimer>0)) and (Not(cc\Action=CHAOACTION_SLEEP)) Then
				cc\Stats\Sleep#=cc\Stats\Sleep#+1
				cc\GetSleepyTimer=(300+cc\Stats\Stamina#*2)*secs#
			EndIf
		EndIf

		; Force interface
		If pp(1)\ObjPickUp=0 And (cc\Action=CHAOACTION_EAT Or cc\Action=CHAOACTION_SLEEP Or cc\Action=CHAOACTION_PILLOWSLEEP Or cc\Action=CHAOACTION_SUCK) Then
			cc\ForceInterface=1
		Else
			cc\ForceInterface=0
		EndIf

		; Take away happiness
		If cc\CanBeInterrupted=1 and (Not(cc\Action=CHAOACTION_EAT Or cc\Action=CHAOACTION_SUCK Or cc\Action=CHAOACTION_SLEEP Or cc\Action=CHAOACTION_PILLOWSLEEP)) Then
			ChaoManager_GetSad(cc)
			cc\CanBeInterrupted=0
		EndIf
		If cc\Stats\Hunger#>=10 and (Not(cc\HungerIgnoredTimer>0)) and (Not(cc\Action=CHAOACTION_SLEEP Or cc\Action=CHAOACTION_PILLOWSLEEP Or cc\Action=CHAOACTION_EAT Or cc\Action=CHAOACTION_SUCK)) Then
			ChaoManager_GetSad(cc)
			cc\HungerIgnoredTimer=240*secs#
			Interface_CreateChaoNamedMsg("is hungry.",cc\Name$,255,182,8)
		EndIf

		; Mate timing
		If cc\Stats\Age=3 and Menu\Stage=999 Then
			If (Not(cc\MateTimer>0)) Then
				cc\Stats\MateSeason=abs(cc\Stats\MateSeason-1)
				Select cc\Stats\MateSeason
					Case 0: cc\MateTimer=(3600+Rand(-1800,1800))*secs#
					Case 1: cc\MateTimer=(1800+Rand(-900,900))*secs# : Interface_CreateChaoNamedMsg("is in mating season.",cc\Name$,234,29,121)
				End Select
			EndIf
		Else
			cc\MateTimer=0
			cc\Stats\MateSeason=0
		EndIf

		; Egg rumble
		If cc\Stats\Age=0 Then
			Select cc\Action
				Case CHAOACTION_PICKEDUP,CHAOACTION_THROWN,CHAOACTION_HURT:
					Animate cc\Mesh,1,0,1,10
					cc\EggRumbleTimer=0
				Default:
					If cc\Stats\ShellGrit#<=3 Then
						If Not(cc\EggRumbleTimer>0) Then
							cc\EggRumbleTimer=10*secs#
							If cc\Stats\ShellGrit#>0 Then
								Animate cc\Mesh,1,0.125/cc\Stats\ShellGrit#,1,10
							Else
								Animate cc\Mesh,1,0.125,1,10
							EndIf
						EndIf
					Else
						Animate cc\Mesh,1,0,1,10
					EndIf
			End Select
		EndIf

		; Hatch (this has to be at the bottom because it deletes the current chao)
		If cc\Action=CHAOACTION_COMMON and cc\Stats\MateSeason=0 and Menu\Stage=999 Then
			Select cc\Stats\Age
				Case 0: If (Not(cc\HatchTimer>0)) Then cc\Stats\ShellGrit#=cc\Stats\ShellGrit#-1 : cc\HatchTimer=Rand(30,60)*secs#
					If (Not(cc\Stats\ShellGrit#>0)) Then ChaoManager_AgeUp(cc, True)
				Case 3: If (Not(cc\HatchTimer>0)) and cc\Stats\ReviveEternal=0 Then ChaoManager_AgeUp(cc)
				Default: If (Not(cc\HatchTimer>0)) Then ChaoManager_AgeUp(cc)
			End Select
		EndIf

	End Function

	Function ChaoManager_GetDefaultPersona(cc.tChaomanager)
		Select cc\Stats\Persona
			Case CHAOEMO_happy,CHAOEMO_sad,CHAOEMO_angry,CHAOEMO_hurt,CHAOEMO_worried,CHAOEMO_shocked,CHAOEMO_thrilled,CHAOEMO_joyful,CHAOEMO_wicked,CHAOEMO_drowsy,CHAOEMO_bored,CHAOEMO_cheerful,CHAOEMO_lovely,CHAOEMO_flirty:
				Return cc\Stats\Persona
			Default:
				Return CHAOEMO_default
		End Select
	End Function

;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	Function ChaoManager_Timers(cc.tChaoManager)
		If cc\JustGotHappyTimer>0 Then cc\JustGotHappyTimer=cc\JustGotHappyTimer-timervalue#
		If cc\JustGotSadTimer>0 Then cc\JustGotSadTimer=cc\JustGotSadTimer-timervalue#
		If cc\HurtTimer>0 Then cc\HurtTimer=cc\HurtTimer-timervalue#
		If cc\DanceTimer>0 Then cc\DanceTimer=cc\DanceTimer-timervalue#
			If Not(cc\Action=CHAOACTION_COMMON Or cc\Action=CHAOACTION_DANCE) Then cc\DanceTimer=0
		If cc\WanderTimer>0 Then cc\WanderTimer=cc\WanderTimer-timervalue#
		If cc\WillWanderTimer>0 Then cc\WillWanderTimer=cc\WillWanderTimer-timervalue#
		If cc\WanderDirectionTimer>0 Then cc\WanderDirectionTimer=cc\WanderDirectionTimer-timervalue#
		If cc\FlyTimer>0 Then cc\FlyTimer=cc\FlyTimer-timervalue#
		If cc\WillFlyTimer>0 Then cc\WillFlyTimer=cc\WillFlyTimer-timervalue#
		If cc\EatTimer>0 Then cc\EatTimer=cc\EatTimer-timervalue#
		If cc\GetHungryTimer>0 Then cc\GetHungryTimer=cc\GetHungryTimer-timervalue#
		If cc\GetSleepyTimer>0 Then cc\GetSleepyTimer=cc\GetSleepyTimer-timervalue#
		If cc\SleepTimer>0 Then cc\SleepTimer=cc\SleepTimer-timervalue#
		If cc\BornTimer>0 Then cc\BornTimer=cc\BornTimer-timervalue#
		If cc\HatchTimer>0 Then cc\HatchTimer=cc\HatchTimer-timervalue#
		If cc\HungerIgnoredTimer>0 Then cc\HungerIgnoredTimer=cc\HungerIgnoredTimer-timervalue#
		If cc\ThinkingTimer>0 Then cc\ThinkingTimer=cc\ThinkingTimer-timervalue#
		If cc\WearTimer>0 Then cc\WearTimer=cc\WearTimer-timervalue#
		If cc\MateTimer>0 Then cc\MateTimer=cc\MateTimer-timervalue#
		If cc\WillWaitBreedTimer>0 Then cc\WillWaitBreedTimer=cc\WillWaitBreedTimer-timervalue#
		If cc\WaitBreedTimer>0 Then cc\WaitBreedTimer=cc\WaitBreedTimer-timervalue#
		If cc\BreedTimer>0 Then cc\BreedTimer=cc\BreedTimer-timervalue# : ParticleTemplate_Call(cc\Particle, PARTICLE_PLAYER_HEARTS, cc\Pivot)
		If cc\PlayTimer>0 Then cc\PlayTimer=cc\PlayTimer-timervalue#
		If cc\PlayedEnoughTimer>0 Then cc\PlayedEnoughTimer=cc\PlayedEnoughTimer-timervalue#
		If cc\ShallFollowWhistleTimer>0 Then cc\ShallFollowWhistleTimer=cc\ShallFollowWhistleTimer-timervalue#
		If cc\ShallBePettedTimer>0 Then cc\ShallBePettedTimer=cc\ShallBePettedTimer-timervalue#
		If cc\EggRumbleTimer>0 Then cc\EggRumbleTimer=cc\EggRumbleTimer-timervalue#
		If cc\FollowWhistleTimer>0 Then cc\FollowWhistleTimer=cc\FollowWhistleTimer-timervalue#
		If cc\WhistleLoveTimer>0 Then cc\WhistleLoveTimer=cc\WhistleLoveTimer-timervalue#
		If cc\WhistleThinkTimer>0 Then cc\WhistleThinkTimer=cc\WhistleThinkTimer-timervalue#
		If cc\RandomWhistleTimer>0 Then cc\RandomWhistleTimer=cc\RandomWhistleTimer-timervalue#

		If Not(Menu\Stage=999) Then
			If cc\CheeredTimer>0 Then cc\CheeredTimer=cc\CheeredTimer-timervalue#
			If cc\RandomBoostTimer>0 Then cc\RandomBoostTimer=cc\RandomBoostTimer-timervalue#
			If cc\RandomBoostedTimer>0 Then cc\RandomBoostedTimer=cc\RandomBoostedTimer-timervalue#
			If cc\MeditateTimer>0 Then cc\MeditateTimer=cc\MeditateTimer-timervalue#
		EndIf
	End Function

	Function ChaoManager_ChaoAlive(cc.tChaoManager)
		Select cc\Stats\Age
			Case 1,3: Return True
			Default: Return False
		End Select
	End Function

	Function ChaoManager_ChaoCocoonAlive(cc.tChaoManager)
		Select cc\Stats\Age
			Case 2,4: Return True
			Case 1,3: Return True
			Default: Return False
		End Select
	End Function

;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Wander(cc.tChaoManager, d.tDeltaTime)

		If (Not(cc\WillWanderTimer>0)) Then
			cc\WillWanderTimer=(4+Rand(1,4))*secs#
		ElseIf cc\WillWanderTimer>0*secs# and cc\WillWanderTimer<1*secs# Then
			If (Not(cc\WanderTimer>0)) Then cc\WanderTimer=(8+Rand(0,10))*secs#
		EndIf

		If cc\WanderTimer>0 Or cc\FollowWhistleTimer>0 Then
			If Not(cc\WanderDirectionTimer>0) Then
				cc\WanderDirection=Rand(-180,180)
				cc\WanderDirectionSpeed=Rand(1,5)
				cc\WanderDirectionTimer=3.25*secs#
			EndIf
			Select cc\Action
				Case CHAOACTION_SWIM: cc\g\Motion\Speed\z#=0.05+(cc\Stats\Swim#/1000.0)+(Rand(1,4)/100.0)
				Case CHAOACTION_FLY: cc\g\Motion\Speed\z#=0.05+(cc\Stats\Fly#/1000.0)+(Rand(1,4)/100.0)
				Default: cc\g\Motion\Speed\z#=0.05+(cc\Stats\Run#/1000.0)+(Rand(1,4)/100.0)
			End Select
			If Not(cc\FollowWhistleTimer>0) Then
				If cc\g\Motion\Direction#<cc\WanderDirection Then cc\g\Motion\Direction#=cc\g\Motion\Direction#+cc\WanderDirectionSpeed*d\Delta
				If cc\g\Motion\Direction#>cc\WanderDirection Then cc\g\Motion\Direction#=cc\g\Motion\Direction#-cc\WanderDirectionSpeed*d\Delta
			Else
				ChaoManager_NoAutonomousCommonActions(cc)
				If cc\Stats\CharacterLove[pp(1)\Character] < -50 Then dir = 0 Else dir = 180
				cc\g\Motion\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,cc\Pivot) - dir)
				If EntityDistance(pp(1)\Objects\Entity,cc\Pivot)<5 Then
					cc\FollowWhistleTimer=0 : cc\WillWanderTimer=4*secs# : cc\WanderTimer=0*secs#
					If cc\Stats\CharacterLove[pp(1)\Character] > 50 Then cc\WhistleLoveTimer=0.75*secs# Else cc\WhistleThinkTimer=0.75*secs#
				EndIf
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Race_Wander(cc.tChaoManager, d.tDeltaTime)

		If cc\Number=1 Then pp(1)\MayCheerTimer=0.5*secs#

		cc\g\Motion\Direction#=-90

		cc\LuckSpeed#=Rand(0,cc\Stats\Stamina#/1000.0)+Rand(0,cc\Stats\Luck#/2000.0)+Rand(0,cc\Stats\Intelligence#/3000.0)
		If cc\CheeredTimer>0 Then cc\LuckSpeed#=cc\LuckSpeed#+0.0125+Rand(0,cc\Stats\Stamina#/4000.0)

		If (Not(cc\RandomBoostTimer>0)) and (Not(cc\RandomBoostedTimer>0)) Then cc\RandomBoostTimer=(1+4+Rand(-2,1))*secs#
		If cc\RandomBoostTimer>0 and cc\RandomBoostTimer<1*secs# Then cc\RandomBoostTimer=0 : cc\RandomBoostedTimer=(2+Rand(1,2))*secs#
		If cc\RandomBoostedTimer>0 Then cc\LuckSpeed#=cc\LuckSpeed#+0.025+Rand(0,cc\Stats\Stamina#/4000.0)

		Select cc\Action
			Case CHAOACTION_RACE_SWIM: cc\g\Motion\Speed\z#=0.05+(cc\Stats\Swim#/500.0)+(Rand(1,4)/100.0)
			Case CHAOACTION_RACE_FLY: cc\g\Motion\Speed\z#=0.05+(cc\Stats\Fly#/500.0)+(Rand(1,4)/100.0)
			Default: cc\g\Motion\Speed\z#=0.05+(cc\Stats\Run#/500.0)+(Rand(1,4)/100.0)
		End Select

		Select cc\Action
			Case CHAOACTION_RACE_CLIMB: cc\g\Motion\Speed\z#=0
			Default: cc\g\Motion\Speed\z#=cc\g\Motion\Speed\z#+cc\LuckSpeed#
		End Select

		cc\g\Motion\Speed\z#=cc\g\Motion\Speed\z#+0.1125 ;extra speed up

		ChaoManager_Race_Manage(cc,d)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_React(cc.tChaoManager)

		If cc\FoundTarget Then
			cc\Channel_Think=EmitSmartSound(Sound_ChaoStatus, cc\Pivot)
			cc\Action=CHAOACTION_COMMON
			Select cc\Target\ObjType
				Case OBJTYPE_FRUIT: ChaoManager_EatorSuck(cc, true)
				Case OBJTYPE_DRIVE: ChaoManager_EatorSuck(cc, false)
				Case OBJTYPE_HAT: ChaoManager_ShallWear(cc)
				Case OBJTYPE_TOY: ChaoManager_ShallPlay(cc)
			End Select
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_GetBorn(cc.tChaoManager)

		Select cc\Stats\Age
			Case 1:
				cc\Action=CHAOACTION_BORN
				cc\BornTimer=2*secs#
				cc\Stats\Persona = ChaoManager_GiveRandomFace()
				If cc\Stats\Happiness#<-11 Then
					Select(Rand(1,8)) : Case 1: cc\Stats\Persona#=CHAOEMO_wicked : End Select
				ElseIf cc\Stats\Happiness#<-8 Then
					Select(Rand(1,8)) : Case 1: cc\Stats\Persona#=CHAOEMO_angry : End Select
				ElseIf cc\Stats\Happiness#<-5 Then
					Select(Rand(1,8)) : Case 1: cc\Stats\Persona#=CHAOEMO_hurt : End Select
				EndIf
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_PlaceTargetWith(cc.tChaoManager, extraplace#=0, extraplaceY#=0)

		cc\Target\CollisionStore=GetEntityType(cc\Target\Pivot) : EntityType(cc\Target\Pivot,0)

		PositionEntity cc\Target\Pivot, EntityX(cc\Pivot), EntityY(cc\Pivot), EntityZ(cc\Pivot)
		RotateEntity cc\Target\Pivot, EntityPitch(cc\Pivot), EntityYaw(cc\Pivot), EntityRoll(cc\Pivot)
		MoveEntity cc\Target\Pivot, 0, 0.125+extraplaceY#, 1.25+extraplace#
		Select cc\Target\ObjType
			Case OBJTYPE_HAT:
				Select cc\Target\ChaoObj\HatType
					Case HAT_TUNIC_0,HAT_TUNIC_1,HAT_TUNIC_2,HAT_TUNIC_3: TurnEntity cc\Target\Pivot, 0, 180, 0
					Default:
				End Select
			Default:
		End Select

		PositionEntity cc\Target\Entity, EntityX(cc\Target\Pivot), EntityY(cc\Target\Pivot), EntityZ(cc\Target\Pivot)
		RotateEntity cc\Target\Entity, EntityPitch(cc\Target\Pivot), EntityYaw(cc\Target\Pivot), EntityRoll(cc\Target\Pivot)

		EntityType(cc\Target\Pivot,cc\Target\CollisionStore)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_EatorSuck(cc.tChaoManager, eat)

		cc\g\Motion\Speed\z#=0
		Select eat
			Case true: cc\EatTimer=(2+Rand(1,4))*secs# : cc\Action=CHAOACTION_EAT
			Case false: cc\EatTimer=(1+Rand(1,2)/2.0)*secs# : cc\Action=CHAOACTION_SUCK
		End select
		cc\EatingWaiting=1
		ChaoManager_GetHappy(cc)
		cc\CanBeInterrupted=1

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_ShallWear(cc.tChaoManager)

		cc\g\Motion\Speed\z#=0
		cc\WearTimer=(4+Rand(0,2))*secs# : cc\Action=CHAOACTION_WEAR

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_ShallPlay(cc.tChaoManager)

		cc\g\Motion\Speed\z#=0
		cc\WearTimer=(2+Rand(0,2))*secs# : cc\Action=CHAOACTION_PLAY

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_NoAutonomousCommonActions(cc.tChaoManager)

		cc\WillWanderTimer=0
		cc\WillFlyTimer=0
		cc\ThinkingTimer=0
		cc\WillWaitBreedTimer=0
		cc\DanceTimer=0

	End Function

;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_PickedUp(cc.tChaoManager)

		If cc\obj\ObjPickedUp=0 Then cc\Action=CHAOACTION_COMMON

		ChaoManager_NoAutonomousCommonActions(cc)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Common(cc.tChaoManager, d.tDeltaTime)

		; Wander around
		ChaoManager_Wander(cc,d)

		; React to obj around
		ChaoManager_React(cc)

		;Start dancing
		If Not(cc\DanceTimer>0) Then cc\DanceTimer=(8+Rand(1,5))*secs#
		If cc\Stats\Happiness#>20 And cc\WanderTimer<5*secs# And (cc\DanceTimer<1*secs# And cc\DanceTimer>0*secs#) Then cc\DanceTimer=(3+Rand(3,7))*secs : cc\Action=CHAOACTION_DANCE

		;Start swimming
		If cc\Underwater=1 Then
			If cc\Stats\Swim#<3 Then Interface_CreateChaoNamedMsg("is drowning.",cc\Name$,7,40,148)
			cc\Action=CHAOACTION_SWIM
		EndIf

		;Start flying
		If cc\Stats\Fly#>=6 Then
			If (Not(cc\WillFlyTimer>0)) Then cc\WillFlyTimer=(10+Rand(1,8))*secs#
			If cc\WillFlyTimer>0*secs# and cc\WillFlyTimer<1*secs# Then cc\Action=CHAOACTION_FLY : cc\FlyTimer=(Rand(1,7)/1.75)*secs#
		EndIf

		;Start sleeping
		If cc\Stats\Sleep#>=10 and cc\g\Motion\Ground Then
			cc\CanBeInterrupted=1
			cc\Action=CHAOACTION_SLEEP
			cc\SleepTimer=12*secs#
			Interface_CreateChaoNamedMsg("is asleep.",cc\Name$,153,89,179)
			If cc\FoundTarget Then cc\LetGoTarget=True
		EndIf

		;Start thinking
		If Not(cc\ThinkingTimer>0) Then cc\ThinkingTimer=(10+Rand(1,4))*secs#
		If cc\WanderTimer<5*secs# and (cc\ThinkingTimer<1*secs# and cc\ThinkingTimer>0*secs#) Then cc\ThinkingTimer=(6+Rand(0,2))*secs : cc\Action=CHAOACTION_THINK  : cc\Channel_Think=EmitSmartSound(Sound_ChaoStatus, cc\Pivot) 

		;Start waiting breed
		If cc\Stats\MateSeason=1 and cc\FoundTarget=False Then
			If Not(cc\WillWaitBreedTimer>0) Then cc\WillWaitBreedTimer=(6+Rand(-1,1))*secs#
			If cc\WillWaitBreedTimer>0*secs# and cc\WillWaitBreedTimer<1*secs# Then
				cc\Action=CHAOACTION_WAITBREED
				cc\WaitBreedTimer=(20+Rand(-5,5))*secs#
				cc\breeder = Object_Breeder_Create(cc\Position\x#, cc\Position\y#, cc\Position\z#, cc)
			EndIf
		EndIf

		;Random whistle
		If Not(cc\RandomWhistleTimer>0) Then
			cc\RandomWhistleTimer=(30+Rand(0,300))*secs#
			If CHARSIDES(InterfaceChar(pp(1)\Character))=ChaoManager_GetHighestSide(cc) and cc\ShallFollowWhistleTimer>0 Then cc\FollowWhistleTimer=5*secs# : cc\WhistleLoveTimer=0 : cc\WhistleThinkTimer=0
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	;; Initiate buddy state for player chao
	;;author Deefor
	Function ChaoManager_Action_Buddy(cc.tChaoManager, d.tDeltaTime)
		cc\targetp=pp(1)
		ShowEntity(cc\Mesh)
		ChaoManager_NoAutonomousCommonActions(cc)
		
		ChaoManager_Action_Buddy_Update_Real(cc, cc\targetp, d)
	End Function
	Function ChaoManager_Action_Buddy_Update_Real(cc.tChaoManager ,p.tPlayer, d.tDeltaTime)
		; Decide animation
		If Game\CheeseTimer>0 and (Not(p\Flags\HomingTarget\x#=99999 and p\Flags\HomingTarget\y#=99999 and p\Flags\HomingTarget\z#=99999)) Then
			cc\Animation=CHAOANIMATION_PUNCHL
			cc\VoiceEmo=CHAOVOICEEMO_ATTACK
			ParticleTemplate_Call(cc\Particle, PARTICLE_CHAO_CHEESE, cc\Pivot)
		Else
			Select p\Action
				Case ACTION_THROW, ACTION_THRUST, ACTION_SHOOT, ACTION_SHOOTHOVER:
					cc\Animation=CHAOANIMATION_EXCLAMATIONAIR
					cc\Emo\Emotion=CHAOEMO_angry
				Case ACTION_PUNCH, ACTION_UPPERCUT, ACTION_PSYCHO:
					cc\Animation=CHAOANIMATION_PUNCHL
					cc\Emo\Emotion=CHAOEMO_angry
				Case ACTION_SPRINT:
					cc\Animation=CHAOANIMATION_RUNAIR
					cc\Emo\Emotion=CHAOEMO_angry
				Case ACTION_HURT:
					cc\Emo\Emotion=CHAOEMO_hurt
					cc\Animation=CHAOANIMATION_THROWN
				Case ACTION_DIE:
					cc\Emo\Emotion=CHAOEMO_hurt
					cc\Animation=CHAOANIMATION_HURT
				Case ACTION_VICTORY, ACTION_VICTORYHOLD:
					cc\Animation=CHAOANIMATION_WIN
					cc\Emo\Emotion=CHAOEMO_cheerful
				Default:
					If (cc\Underwater=1) Then
						If cc\Stats\Swim#<3 Then
							ParticleTemplate_Call(cc\Particle, PARTICLE_PLAYER_BUBBLEBREATHE, cc\Pivot, 0.1, 0, 0, 0, 0, Rand(1,5)/2.0)
							cc\Animation=CHAOANIMATION_DROWN
							cc\Emo\Emotion=CHAOEMO_disgusted
						Else
							ParticleTemplate_Call(cc\Particle, PARTICLE_PLAYER_BUBBLEBREATHE, cc\Pivot, 0.1, 0, 0, 0, 0, Rand(1,5)/8.0)
							cc\Animation=CHAOANIMATION_SWIM
							cc\Emo\Emotion=CHAOEMO_bored
						EndIf
					Else
						cc\Emo\Emotion=ChaoManager_GetDefaultPersona(cc)
						If (Not(p\Motion\Ground)) Or cc\Stats\Fly>6 Then
							If p\SpeedLength#>2 Then
								cc\Animation=CHAOANIMATION_RUNAIR
								cc\VoiceEmo=CHAOVOICEEMO_HUMMING
								ParticleTemplate_Call(cc\Particle, PARTICLE_CHAO_CHEESE, cc\Pivot)
							Else If p\SpeedLength#>0.5
								cc\VoiceEmo=CHAOVOICEEMO_CASUAL
								cc\Animation=CHAOANIMATION_WALKAIR
							Else
								cc\VoiceEmo=CHAOVOICEEMO_CASUAL
								cc\Animation=CHAOANIMATION_IDLEAIR
							EndIf
						Else If cc\Stats\Run>5 Then 
							If p\SpeedLength#>2 Then
								cc\Animation=CHAOANIMATION_RUN
								cc\VoiceEmo=CHAOVOICEEMO_HUMMING
								ParticleTemplate_Call(cc\Particle, PARTICLE_PLAYER_ATTACKTRAIL, cc\Pivot)
							Else If p\SpeedLength#>0.1
								cc\VoiceEmo=CHAOVOICEEMO_CASUAL
								cc\Animation=CHAOANIMATION_WALK
							Else
								cc\VoiceEmo=CHAOVOICEEMO_CASUAL
								cc\Animation=CHAOANIMATION_IDLE
							EndIf
						Else
							If p\SpeedLength#>0.1 Then
								cc\Animation=CHAOANIMATION_CRAWL
							Else
								cc\VoiceEmo=CHAOVOICEEMO_CASUAL
								cc\Animation=CHAOANIMATION_SIT
							EndIf
						EndIf
					EndIf
			End Select
		EndIf
		

		; Movement
		If Game\CheeseTimer>0 and (Not(p\Flags\HomingTarget\x#=99999 and p\Flags\HomingTarget\y#=99999 and p\Flags\HomingTarget\z#=99999)) Then
			PositionEntity(p\Flags\HomingMesh, p\Flags\HomingTarget\x#, p\Flags\HomingTarget\y#+0.3, p\Flags\HomingTarget\z#)
			ParticleTemplate_Call(cc\Particle, PARTICLE_CHAO_CHEESE, cc\Pivot)

			ex# = p\Flags\HomingTarget\x# - cc\Position\x#
			ey# = (p\Flags\HomingTarget\y#+3) - cc\Position\y#
			ez# = p\Flags\HomingTarget\z# - cc\Position\z#
			AlignToVector(cc\Pivot, ex#, ey#, ez#, 2, .925)
			AlignToVector(cc\Mesh, ex#, ey#, ez#, 2, .925)
			TurnEntity cc\Mesh, -90, 0, 0
			MoveEntity cc\Pivot, 0, 3.2*d\Delta, 0
			p\Flags\HomingTarget\x#=99999
			p\Flags\HomingTarget\y#=99999
			p\Flags\HomingTarget\z#=99999
		Else

				If (Not(p\Motion\Ground)) Or cc\Stats\Fly>6  Or cc\Underwater Then
					PointEntity cc\Pivot, p\Objects\Buddy, 0
				Else
					RotateEntity cc\Pivot,0,(DeltaYaw#(cc\Pivot,p\Objects\Buddy)),0
				EndIf

				
			If EntityDistance(p\Objects\Buddy, cc\Pivot)>=100 Then
				EntityType(cc\Pivot,COLLISION_NONE) : PositionEntity cc\Pivot, p\Objects\Position\x#, p\Objects\Position\y#+12, p\Objects\Position\z#
				;EmitSmartSound(Sound_CharacterChange, cc\Pivot)
				ParticleTemplate_Call(cc\Particle, PARTICLE_PLAYER_CHARACTERDUST, cc\Pivot) :EntityType(cc\Pivot,COLLISION_OBJECT2_GOTHRU)
			Else
				If cc\Stats\Fly>6 Or cc\Underwater Then
					MoveEntity cc\Pivot,0,0,((((EntityDistance(p\Objects\Buddy, cc\Pivot)*((cc\Stats\Run + 20)/70))^2)/100)*d\Delta) 
					cc\g\Motion\Speed\y#=0.01+(cc\Stats\Fly#/100.0)
					
				Else
					If EntityDistance(cc\Pivot, p\Objects\Hips)>=7 Then MoveEntity cc\Pivot,0,0,((((EntityDistance(p\Objects\Hips, cc\Pivot)*((cc\Stats\Run + 20)/70))^2)/100)*d\Delta)
					If (Not(p\Motion\Ground)) Then cc\g\Motion\Speed\y#=0.05+(cc\Stats\Fly#/100.0)
				EndIf 
				If cc\Underwater And (Not(p\Motion\Ground)) Then 
					cc\g\Motion\Speed\z#=0.05+(cc\Stats\Swim#/100.0)+(Rand(1,4)/100.0)
				End If
			EndIf
		EndIf
			
	End Function
	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Thrown(cc.tChaoManager)

		Select cc\Stats\Age
			Case 0: cc\HurtTimer=0.125*secs#
			Default: cc\HurtTimer=4*secs#
		End Select

		If cc\g\Motion\Ground Then
			ChaoManager_GetSad(cc,5)
			If cc\Stats\Hat>0 Then Object_ChaoManager_TakeOffHat(cc)
			cc\Action=CHAOACTION_HURT
			If cc\Stats\Age=0 Then cc\Stats\ShellGrit#=cc\Stats\ShellGrit#-1
			If ChaoManager_ChaoAlive(cc) Then Interface_CreateChaoNamedMsg("was hurt.",cc\Name$,182,42,42)
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Hurt(cc.tChaoManager)

		If Not(cc\HurtTimer>2*secs#) Then cc\Action=CHAOACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Dance(cc.tChaoManager)

		ChaoManager_React(cc)

		If Not(cc\DanceTimer>0) Then cc\Action=CHAOACTION_COMMON

		If cc\FollowWhistleTimer>0 Then cc\Action=CHAOACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Swim(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Wander(cc,d)

		Chao_Particle_Swim(cc)

		If EntityY(cc\Pivot)<Game\Stage\Properties\WaterLevel-0.5 Then cc\g\Motion\Speed\y#=0.05+(cc\Stats\Swim#/1000.0)+(Rand(1,3)/100.0) Else cc\g\Motion\Speed\y#=0

		If EntityY(cc\Pivot)>Game\Stage\Properties\WaterLevel+0.25 Then cc\Action=CHAOACTION_COMMON

		If cc\Animation=CHAOANIMATION_DROWN Then cc\HurtTimer=0.1*secs#

		; React to obj around
		ChaoManager_React(cc)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Fly(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Wander(cc,d)

		cc\g\Motion\Speed\y#=0.05+(cc\Stats\Fly#/1000.0)+(Rand(1,3)/100.0)

		If Not(cc\FlyTimer>0) Then cc\WillFlyTimer=0 : cc\Action=CHAOACTION_COMMON

		If cc\FollowWhistleTimer>0 Then cc\Action=CHAOACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Eat(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\CanBeInterrupted=0

		If cc\FoundTarget Then
			ChaoManager_PlaceTargetWith(cc)
			If cc\Stats\TooFull#>=20 Or (cc\Target\ChaoObj\FruitType=FRUIT_PEPPER and cc\EatingWaiting=2) Then
				cc\Target\ObjPickedUp=6
				cc\Target\ChaoObj\ChaoTargetedThis=False
				cc\FoundTarget=False
				cc\EatTimer=(1+Rand(1,4)/2.0)*secs#
				cc\EatingWaiting=3
			EndIf
		EndIf

		If (Not(cc\EatTimer>0)) Then
			Select cc\EatingWaiting
				Case 1:
					If cc\Target\ChaoObj\FruitType<>cc\Stats\FavouriteFood Then cc\EatTimer=(1+Rand(1,4)/2.0)*secs# Else cc\EatTimer=(1+Rand(1,2)/2.0)*secs#
					cc\EatingWaiting=2
					cc\Target\ChaoObj\EatCycle=cc\Target\ChaoObj\EatCycle-1
					ChaoManager_Gain(1, cc\Target\ChaoObj\FruitType, cc, cc\Target\ChaoObj\EatCycle)
					If Not(cc\Target\ChaoObj\EatCycle>0) Then cc\FoundTarget=False
				Case 2,3:
					If cc\FoundTarget=False Then cc\Action=CHAOACTION_COMMON : cc\CanBeInterrupted=0
					cc\EatTimer=(2+Rand(1,4))*secs#
					cc\EatingWaiting=1
			End Select
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Sleep(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\CanBeInterrupted=0

		If Not(cc\Stats\Sleep#>0) Then cc\SleepTimer=1.15*secs# : cc\Action=CHAOACTION_WAKE : cc\CanBeInterrupted=0

		If Not(cc\SleepTimer>0) Then
			cc\Stats\Sleep#=cc\Stats\Sleep#-1
			cc\SleepTimer=12*secs#
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Wake(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON

		If Not(cc\SleepTimer>0) Then cc\Action=CHAOACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Born(cc.tChaoManager)

		If Not(cc\BornTimer>0) Then cc\Action=CHAOACTION_COMMON

		cc\g\Motion\Speed\y#=0.1
		cc\g\Motion\Speed\z#=0.1

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Suck(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\CanBeInterrupted=0

		If cc\FoundTarget Then
			ChaoManager_PlaceTargetWith(cc)
		EndIf

		If (Not(cc\EatTimer>0)) Then
			Select cc\EatingWaiting
				Case 1:
					cc\EatTimer=(1+Rand(1,4)/2.0)*secs#
					cc\EatingWaiting=2
					ChaoManager_Gain(2, cc\Target\ChaoObj\DriveType, cc)
					cc\Target\ChaoObj\ForceDelete=True
					cc\FoundTarget=False
				Case 2,3:
					If cc\FoundTarget=False Then cc\Action=CHAOACTION_COMMON : cc\CanBeInterrupted=0
					cc\EatTimer=(2+Rand(1,4))*secs#
					cc\EatingWaiting=1
			End Select
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Think(cc.tChaoManager)

		ChaoManager_React(cc)

		If Not(cc\ThinkingTimer>0) Then cc\Action=CHAOACTION_COMMON

		If cc\FollowWhistleTimer>0 Then cc\Action=CHAOACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Wear(cc.tChaoManager)

		If cc\FoundTarget Then
			ChaoManager_PlaceTargetWith(cc)
		Else
			cc\Action=CHAOACTION_COMMON
		EndIf

		If Not(cc\WearTimer>0) Then
			If cc\Stats\Intelligence#>=4 Then
				If cc\Stats\Hat>0 Then Object_ChaoManager_TakeOffHat(cc)
				cc\Stats\Hat=cc\Target\ChaoObj\HatType
				cc\Target\ChaoObj\ForceDelete=True
				cc\FoundTarget=False
				ChaoManager_BoostSkill_Hat(cc, cc\Stats\Hat)
				Object_ChaoManager_LoadHat(cc)
			Else
				Interface_CreateChaoNamedMsg("needs more intelligence to put on hats.",cc\Name$,105,202,33)
				cc\Target\ObjPickedUp=6
				cc\Target\ChaoObj\ChaoTargetedThis=False
				cc\FoundTarget=False
			EndIf
			cc\Action=CHAOACTION_COMMON			
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_WaitBreed(cc.tChaoManager)

		If Not(cc\WaitBreedTimer>0) Then
			cc\Action=CHAOACTION_COMMON
			FreeEntity cc\breeder\Entity
			Delete cc\breeder\Position
			Delete cc\breeder
			Return
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Breed(cc.tChaoManager)

		If Not(cc\BreedTimer>0) Then cc\Action=CHAOACTION_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Play(cc.tChaoManager)

		If cc\FoundTarget Then
			ChaoManager_PlaceTargetWith(cc)
		Else
			cc\Action=CHAOACTION_COMMON
		EndIf

		If cc\Underwater=1 Then cc\g\Motion\Speed\y#=0

		If Not(cc\WearTimer>0) Then
			gotit=true
			If cc\Stats\Intelligence#>=2 Then
				Select cc\Target\ChaoObj\ToyType
					Case TOY_BEACHBALL,TOY_SOCCERBALL: If (Not(cc\Stats\Age<=1 and cc\Stats\Run#<4)) Then cc\Action=CHAOACTION_BALL Else gotit=false
					Case TOY_TOYCAR: If (Not(cc\Stats\Age<=1 and cc\Stats\Run#<4)) Then cc\Action=CHAOACTION_DRIVE Else gotit=false
					Case TOY_RUBBERDUCK: If cc\Underwater=0 Then
									gotit=false
								Else
									If Not(cc\Stats\Swim#<3) Then cc\Action=CHAOACTION_DRIVEDUCK Else gotit=false
								EndIf
					Case TOY_TEDDYBEAR: cc\Action=CHAOACTION_TEDDYBEAR
					Case TOY_MICROPHONE: If cc\Stats\Stamina#>=2 Then cc\Action=CHAOACTION_SING Else gotit=false
					Case TOY_DUMBBELL1,TOY_DUMBBELL2,TOY_DUMBBELL3,TOY_DUMBBELL4,TOY_DUMBBELL5: If (cc\Stats\Strength#>=(cc\Target\ChaoObj\ToyType-TOY_DUMBBELL1-1)*10) Then cc\Action=CHAOACTION_LIFT Else gotit=false
					Case TOY_RATTLE: cc\Action=CHAOACTION_RATTLE : Animate cc\Target\Entity,1,0.15,1,10
					Case TOY_PILLOW: cc\Action=CHAOACTION_PILLOWSLEEP : cc\SleepTimer=12*secs# : Interface_CreateChaoNamedMsg("is asleep.",cc\Name$,105,202,33)
					Case TOY_TV: cc\Action=CHAOACTION_WATCH
				End Select
			Else
				gotit=false
			EndIf
			If gotit=false Then
				If cc\Stats\Intelligence#>=2 Then
					Select cc\Target\ChaoObj\ToyType
						Case TOY_BEACHBALL,TOY_SOCCERBALL: Interface_CreateChaoNamedMsg("cannot walk and thus cannot play with balls.",cc\Name$,105,202,33)
						Case TOY_TOYCAR: Interface_CreateChaoNamedMsg("cannot walk and thus cannot play with toy cars.",cc\Name$,105,202,33)
						Case TOY_RUBBERDUCK: If cc\Underwater=0 Then
										Interface_CreateChaoNamedMsg("needs to be in water to play with rubber ducks.",cc\Name$,105,202,33)
									Else
										Interface_CreateChaoNamedMsg("cannot swim and thus cannot play with rubber ducks.",cc\Name$,105,202,33)
									EndIf
						Case TOY_MICROPHONE: Interface_CreateChaoNamedMsg("does not have enough stamina to sing.",cc\Name$,105,202,33)
						Case TOY_DUMBBELL1,TOY_DUMBBELL2,TOY_DUMBBELL3,TOY_DUMBBELL4,TOY_DUMBBELL5: Interface_CreateChaoNamedMsg("is not strong enough to lift the dumbbell.",cc\Name$,105,202,33)
					End Select
				Else
					Interface_CreateChaoNamedMsg("needs more intelligence to play with toys.",cc\Name$,105,202,33)
				EndIf
				If Not cc\Target\ChaoObj\ToyType = TOY_TV Then cc\Target\ObjPickedUp=6
				cc\Target\ChaoObj\ChaoTargetedThis=False
				cc\FoundTarget=False
				cc\Action=CHAOACTION_COMMON
			Else
				ChaoManager_GetHappy(cc)
				cc\PlayOrder=0
				cc\PlayedEnoughTimer=(30+Rand(-2,5))*secs#
			EndIf		
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Watch(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		Select cc\PlayOrder
			Case 0:
				cc\PlayOrder=1
				cc\PlayTimer=(1+Rand(1,2))*secs#
			Case 1:
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=2
					cc\PlayTimer=(30+Rand(1,4))*secs#
				EndIf
			Case 2:
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=0
				EndIf
		End Select

	End Function
	
	Function ChaoManager_Action_Ball(cc.tChaoManager, d.tDeltaTime)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		Select cc\PlayOrder
			Case 0:
				cc\PlayOrder=1
				cc\PlayTimer=(2+Rand(1,3))*secs#
				Animate cc\Target\Entity,1,0.25,1,10
			Case 1:
				If cc\Target\ChaoObj\ToyType=TOY_SOCCERBALL Then cc\g\Motion\Direction#=(DeltaYaw#(pp(1)\Objects\Entity,cc\Pivot) - 180)
				ChaoManager_PlaceTargetWith(cc,1.25)
				ChaoManager_Wander(cc,d)
				If Not(cc\PlayTimer>0) Then
					PositionEntity cc\Target\Pivot, cc\Position\x#, cc\Position\y#+1, cc\Position\z#, 1
					cc\Target\ObjPickedUp=6
					cc\PlayOrder=2
					cc\PlayTimer=(1+Rand(1,2))*secs#
					Animate cc\Target\Entity,1,0,1,10
				EndIf
			Case 2:
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=3
				EndIf
			Case 3:
				cc\g\Motion\Direction#=cc\Target\Rotation\y#
				cc\g\Motion\Speed\z#=0.05+(cc\Stats\Run#/1000.0)+(Rand(1,4)/100.0)
				If EntityDistance(cc\Pivot,cc\Target\Entity)<2 Then
					cc\PlayOrder=1
					cc\PlayTimer=(2+Rand(1,3))*secs#
					Animate cc\Target\Entity,1,0.25,1,10
				EndIf
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_TeddyBear(cc.tChaoManager)

		ChaoManager_PlaceTargetWith(cc)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Sing(cc.tChaoManager)

		ChaoManager_PlaceTargetWith(cc)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Lift(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		Select cc\PlayOrder
			Case 0:
				cc\PlayOrder=1
				cc\PlayTimer=(1+Rand(1,2))*secs#
			Case 1:
				ChaoManager_PlaceTargetWith(cc)
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=2
					cc\PlayTimer=(3+Rand(1,4))*secs#
					ChaoManager_Gain(2,4,cc)
				EndIf
			Case 2:
				ChaoManager_PlaceTargetWith(cc, -0.6, 1)
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=0
				EndIf
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Drive(cc.tChaoManager, d.tDeltaTime)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		Select cc\PlayOrder
			Case 0:
				cc\PlayOrder=1
				cc\PlayTimer=(2+Rand(1,3))*secs#
			Case 1:
				ChaoManager_PlaceTargetWith(cc,0.85)
				ChaoManager_Wander(cc,d)
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=2
					cc\PlayTimer=(1+Rand(1,2))*secs#
				EndIf
			Case 2:
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=0
				EndIf
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_DriveDuck(cc.tChaoManager, d.tDeltaTime)

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_SWIM : cc\LetGoTarget=True

		Chao_Particle_Swim(cc)

		If EntityY(cc\Pivot)<Game\Stage\Properties\WaterLevel-0.5 Then cc\g\Motion\Speed\y#=0.05+(cc\Stats\Swim#/1000.0)+(Rand(1,3)/100.0) Else cc\g\Motion\Speed\y#=0

		If EntityY(cc\Pivot)>Game\Stage\Properties\WaterLevel+0.25 Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		Select cc\PlayOrder
			Case 0:
				cc\PlayOrder=1
				cc\PlayTimer=(2+Rand(1,3))*secs#
			Case 1:
				ChaoManager_PlaceTargetWith(cc,0.55,0.125)
				ChaoManager_Wander(cc,d)
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=2
					cc\PlayTimer=(1+Rand(1,2))*secs#
				EndIf
			Case 2:
				If Not(cc\PlayTimer>0) Then
					cc\PlayOrder=0
				EndIf
		End Select

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Rattle(cc.tChaoManager)

		ChaoManager_PlaceTargetWith(cc)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If (Not(cc\PlayedEnoughTimer>0)) Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_PillowSleep(cc.tChaoManager)

		ChaoManager_PlaceTargetWith(cc, -1.875, -0.25)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON : cc\LetGoTarget=True

		If Not(cc\Stats\Sleep#>0) Then cc\SleepTimer=1.15*secs# : cc\Action=CHAOACTION_WAKE : cc\LetGoTarget=True

		If Not(cc\SleepTimer>0) Then
			cc\Stats\Sleep#=cc\Stats\Sleep#-1
			cc\SleepTimer=12*secs#
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Petted(cc.tChaoManager)

		If cc\g\Motion\Ground=False Then cc\Action=CHAOACTION_COMMON

		ChaoManager_NoAutonomousCommonActions(cc)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_Begin(cc.tChaoManager, d.tDeltaTime)

		cc\g\Motion\Direction#=-90

		ChaoManager_Race_Manage(cc,d)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_Common(cc.tChaoManager, d.tDeltaTime)

		; Wander race
		ChaoManager_Race_Wander(cc,d)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_Swim(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Race_Wander(cc,d)

		Chao_Particle_Swim(cc)

		If cc\g\Motion\Ground Then cc\g\Motion\Ground=False

		If EntityY(cc\Pivot)<Game\Stage\Properties\WaterLevel-0.5 Then cc\g\Motion\Speed\y#=0.025+(cc\Stats\Swim#/500.0)+(Rand(1,3)/100.0) Else cc\g\Motion\Speed\y#=0

		If cc\Animation=CHAOANIMATION_DROWN Then cc\HurtTimer=0.1*secs#

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_Fly(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Race_Wander(cc,d)

		cc\g\Motion\Speed\y#=0.025+(cc\Stats\Fly#/500.0)+(Rand(1,3)/600.0)+cc\LuckSpeed#

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_Climb(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Race_Wander(cc,d)

		cc\g\Motion\Speed\y#=0.05+(cc\Stats\Strength#/500.0)+(Rand(1,3)/100.0)+cc\LuckSpeed#

		cc\g\Motion\Speed\y#=cc\g\Motion\Speed\y#+0.1125 ;extra speed up

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_WinOrLose(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Race_Manage(cc,d)

		If Game\Interface\RaceEnded Then
			If cc\WinningChao>3 Then
				Select cc\WinningChao
					Case 4: cc\g\Motion\Direction#=-125 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+56.75, 30.5, -9.25
					Case 5: cc\g\Motion\Direction#=-105 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+53, 30.5, -13.75
					Case 6: cc\g\Motion\Direction#=-75 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+53.75, 30.5, -21.75
					Case 7: cc\g\Motion\Direction#=-60 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+57, 30.5, -25.75
					Case 8: cc\g\Motion\Direction#=-55 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+57.75, 30.5, -31
				End Select
			Else
				Select cc\WinningChao
					Case 1: cc\g\Motion\Direction#=90 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+75, 36.5, -19.75
					Case 2: cc\g\Motion\Direction#=75 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+70, 34.5, -24
					Case 3: cc\g\Motion\Direction#=105 : PositionEntity cc\Pivot, Game\Interface\RaceEndPoint#+68, 32.5, -16.25
				End Select
			EndIf
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Race_Lost(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Race_Manage(cc,d)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Begin(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Common(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		If Not(Game\Interface\KarateZeal#[cc\Number]>0) Then
			cc\Action=CHAOACTION_KARATE_MEDITATE
		Else
			If cc\Number=1 Then karateopponent=2 Else karateopponent=1
			For oppcc.tChaoManager=Each tChaoManager
				If oppcc\Number=karateopponent and (Not(oppcc\HurtTimer>0)) and (Not(oppcc\FlyTimer>0)) and (Not(oppcc\Action=CHAOACTION_KARATE_THROWN Or oppcc\Action=CHAOACTION_KARATE_HURT Or oppcc\Action=CHAOACTION_KARATE_DODGE)) and (Not(Game\Interface\KarateEndTimer>0)) Then
					If abs(cc\Position\x#-oppcc\Position\x#)>3.2 Then
						cc\g\Motion\Speed\z#=0.05+(cc\Stats\Run#/500.0)+(Rand(1,4)/100.0)
					ElseIf Game\Interface\KarateTurn=cc\Number Then
						Game\Interface\KarateTurn=Game\Interface\KarateTurn+5
						Select(Rand(1,2))
							Case 1: cc\Action=CHAOACTION_KARATE_KICK
							Case 2: cc\Action=CHAOACTION_KARATE_PUNCH
						End Select
					EndIf
				EndIf
			Next
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Attack(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		cc\g\Motion\Speed\z#=0.05

		If Not(Animating(cc\Mesh)) Then
			cc\Action=CHAOACTION_KARATE_COMMON
			Game\Interface\KarateTurn=Game\Interface\KarateTurn-5
			If Game\Interface\KarateTurn=1 Then Game\Interface\KarateTurn=2 Else Game\Interface\KarateTurn=1

			If cc\Number=1 Then karateopponent=2 Else karateopponent=1
			For oppcc.tChaoManager=Each tChaoManager
				If oppcc\Number=karateopponent Then
					dodgechance=Rand(1,oppcc\Stats\Fly#)
					If dodgechance>(50-0.75*oppcc\Stats\Fly#) Then
						oppcc\Action=CHAOACTION_KARATE_DODGE
						Create_AfterImage.tAfterImage(oppcc\Mesh,oppcc\Pivot,500,96,96,96,1,0,1.5,oppcc\Animation,True,False,Texture_Trail)
						oppcc\Channel_Swim = EmitSmartSound(Sound_Dodge, oppcc\Pivot)
						oppcc\g\Motion\Ground=False : oppcc\g\Motion\Speed\y#=0.14
						oppcc\FlyTimer=0.245*secs#
					Else
						oppcc\Action=CHAOACTION_KARATE_THROWN
						oppcc\g\Motion\Ground=False : oppcc\g\Motion\Speed\y#=0.2+cc\Stats\Strength#/100.0 cc\g\Motion\Speed\z#=-0.2-cc\Stats\Strength#/50.0
						oppcc\HurtTimer=0.365*secs#
						ParticleTemplate_Call(oppcc\Particle, PARTICLE_OBJECT_FLAMYBLOOD,oppcc\Pivot)
						Game\Interface\KarateHealth#[oppcc\Number]=Game\Interface\KarateHealth#[oppcc\Number]-(Rand(0,4)/4.0+0.5+cc\Stats\Strength#/20.0-oppcc\Stats\Swim#/100.0+Game\Interface\KarateZeal#[cc\Number]/60.0)
					EndIf
				EndIf
			Next
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Thrown(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		If cc\HurtTimer>0 Then cc\g\Motion\Speed\y#=0.18
		cc\g\Motion\Speed\z#=-0.2

		If cc\g\Motion\Ground Then
			cc\Action=CHAOACTION_KARATE_HURT
			cc\HurtTimer=1.2*secs#
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Hurt(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		If Not(cc\HurtTimer>0) Then cc\Action=CHAOACTION_KARATE_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_WinOrLose(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		If Game\Interface\RaceEnded Then
			Select cc\Number
			Case 1: cc\g\Motion\Direction#=0 : PositionEntity cc\Pivot, 5.875, EntityY(cc\Pivot), 0 ;3.8, 0
			Case 2: cc\g\Motion\Direction#=0 : PositionEntity cc\Pivot, -5.875, EntityY(cc\Pivot), 0 ;3.8, 0
			End Select
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Meditate(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		If Not(cc\MeditateTimer>0) Then cc\Action=CHAOACTION_KARATE_COMMON

	End Function

	; =========================================================================================================
	; =========================================================================================================
	Function ChaoManager_Action_Karate_Dodge(cc.tChaoManager, d.tDeltaTime)

		ChaoManager_Karate_Manage(cc,d)

		If cc\FlyTimer>0 Then cc\g\Motion\Speed\y#=0.08
		cc\g\Motion\Speed\z#=-0.12

		If cc\g\Motion\Ground Then
			cc\Action=CHAOACTION_KARATE_COMMON
			cc\FlyTimer=0
		EndIf

	End Function

	; =========================================================================================================
	; =========================================================================================================