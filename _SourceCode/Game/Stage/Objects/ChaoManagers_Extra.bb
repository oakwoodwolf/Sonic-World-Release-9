

Function LoadChaoVoices()
	For i=1 to 2	: LoadGoodSound(ChaoVoice_Amazed[i],3,"ChaoWorld/Voices/amazed"+i+".ogg",2) : Next
	For i=1 to 7	: LoadGoodSound(ChaoVoice_Attack[i],3,"ChaoWorld/Voices/attack"+i+".ogg",2) : Next
	For i=1 to 25	: LoadGoodSound(ChaoVoice_Casual[i],3,"ChaoWorld/Voices/casual"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Crazy[i],3,"ChaoWorld/Voices/crazy"+i+".ogg",2) : Next
	For i=1 to 4	: LoadGoodSound(ChaoVoice_Crying[i],3,"ChaoWorld/Voices/crying"+i+".ogg",2) : Next
	For i=1 to 4	: LoadGoodSound(ChaoVoice_Disgusted[i],3,"ChaoWorld/Voices/disgusted"+i+".ogg",2) : Next
	For i=1 to 2	: LoadGoodSound(ChaoVoice_Drowning[i],3,"ChaoWorld/Voices/drowning"+i+".ogg",2) : Next
	For i=1 to 4	: LoadGoodSound(ChaoVoice_Eating[i],3,"ChaoWorld/Voices/eating"+i+".ogg",2) : Next
	For i=1 to 2	: LoadGoodSound(ChaoVoice_Figured[i],3,"ChaoWorld/Voices/figured"+i+".ogg",2) : Next
	For i=1 to 6	: LoadGoodSound(ChaoVoice_Frustrated[i],3,"ChaoWorld/Voices/frustrated"+i+".ogg",2) : Next
	For i=1 to 1	: LoadGoodSound(ChaoVoice_Humming[i],3,"ChaoWorld/Voices/humming"+i+".ogg",2) : Next
	For i=1 to 6	: LoadGoodSound(ChaoVoice_Hurt[i],3,"ChaoWorld/Voices/hurt"+i+".ogg",2) : Next
	For i=1 to 6	: LoadGoodSound(ChaoVoice_Laughing[i],3,"ChaoWorld/Voices/laughing"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Mumbling[i],3,"ChaoWorld/Voices/mumbling"+i+".ogg",2) : Next
	For i=1 to 6	: LoadGoodSound(ChaoVoice_Playing[i],3,"ChaoWorld/Voices/playing"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Ready[i],3,"ChaoWorld/Voices/ready"+i+".ogg",2) : Next
	For i=1 to 4	: LoadGoodSound(ChaoVoice_Refusing[i],3,"ChaoWorld/Voices/refusing"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Sad[i],3,"ChaoWorld/Voices/sad"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Satisfied[i],3,"ChaoWorld/Voices/satisfied"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Scared[i],3,"ChaoWorld/Voices/scared"+i+".ogg",2) : Next
	For i=1 to 9	: LoadGoodSound(ChaoVoice_Singing[i],3,"ChaoWorld/Voices/singing"+i+".ogg",2) : Next
	For i=1 to 6	: LoadGoodSound(ChaoVoice_Sleeping[i],3,"ChaoWorld/Voices/sleeping"+i+".ogg",2) : Next
	For i=1 to 5	: LoadGoodSound(ChaoVoice_Surprised[i],3,"ChaoWorld/Voices/surprised"+i+".ogg",2) : Next
	For i=1 to 10	: LoadGoodSound(ChaoVoice_Talking[i],3,"ChaoWorld/Voices/talking"+i+".ogg",2) : Next
	For i=1 to 7	: LoadGoodSound(ChaoVoice_Thinking[i],3,"ChaoWorld/Voices/thinking"+i+".ogg",2) : Next
	For i=1 to 2	: LoadGoodSound(ChaoVoice_Tired[i],3,"ChaoWorld/Voices/tired"+i+".ogg",2) : Next
	For i=1 to 4	: LoadGoodSound(ChaoVoice_Waking[i],3,"ChaoWorld/Voices/waking"+i+".ogg",2) : Next
	For i=1 to 3	: LoadGoodSound(ChaoVoice_Yawning[i],3,"ChaoWorld/Voices/yawning"+i+".ogg",2) : Next

	LoadGoodSound(Sound_ChaoDarkEvo,1,"Sounds/ChaoDarkEvo.ogg")
	LoadGoodSound(Sound_ChaoDeath,1,"Sounds/ChaoDeath.ogg")
	LoadGoodSound(Sound_ChaoHeroEvo,1,"Sounds/ChaoHeroEvo.ogg")
	LoadGoodSound(Sound_ChaoMating,1,"Sounds/ChaoMating.ogg")
	LoadGoodSound(Sound_ChaoNeutralEvo,1,"Sounds/ChaoNeutralEvo.ogg")
	LoadGoodSound(Sound_ChaoReincarnation,1,"Sounds/ChaoReincarnation.ogg")

	LoadGoodSound(Sound_Whistle,1,"Sounds/Whistle.ogg")

	If Menu\Stage=998 Then
		LoadGoodSound(Sound_ChaoRaceCompleted,1,"Sounds/ChaoRaceCompleted.ogg")
		SmartImage(Interface_Race)
	EndIf

	If Menu\Stage=997 Then
		LoadGoodSound(Sound_ChaoKarateWin,1,"Sounds/ChaoKarateWin.ogg") : LoadGoodSound(Sound_ChaoKarateLose,1,"Sounds/ChaoKarateLose.ogg")
		SmartImage(Interface_Karate) : SmartImage(Interface_KarateBars)
	EndIf

End Function


	Global CHAOVOICEEMO_AMAZED	= 1
	Global CHAOVOICEEMO_ATTACK	= 2
	Global CHAOVOICEEMO_CASUAL	= 3
	Global CHAOVOICEEMO_CRAZY	= 4
	Global CHAOVOICEEMO_CRYING	= 5
	Global CHAOVOICEEMO_DISGUSTED	= 6
	Global CHAOVOICEEMO_DROWNING	= 7
	Global CHAOVOICEEMO_EATING	= 8
	Global CHAOVOICEEMO_FIGURED	= 9
	Global CHAOVOICEEMO_FRUSTRATED	= 10
	Global CHAOVOICEEMO_HUMMING	= 11
	Global CHAOVOICEEMO_HURT	= 12
	Global CHAOVOICEEMO_LAUGHING	= 13
	Global CHAOVOICEEMO_MUMBLING	= 14
	Global CHAOVOICEEMO_PLAYING	= 15
	Global CHAOVOICEEMO_READY	= 16
	Global CHAOVOICEEMO_REFUSING	= 17
	Global CHAOVOICEEMO_SAD		= 18
	Global CHAOVOICEEMO_SATISFIED	= 19
	Global CHAOVOICEEMO_SCARED	= 20
	Global CHAOVOICEEMO_SINGING	= 21
	Global CHAOVOICEEMO_SLEEPING	= 22
	Global CHAOVOICEEMO_SURPRISED	= 23
	Global CHAOVOICEEMO_TALKING	= 24
	Global CHAOVOICEEMO_THINKING	= 25
	Global CHAOVOICEEMO_TIRED	= 26
	Global CHAOVOICEEMO_WAKING	= 27
	Global CHAOVOICEEMO_YAWNING	= 28

	Function ChaoManager_UpdateVoice(cc.tChaoManager)
		If cc\PreviousVoiceEmo<>cc\VoiceEmo Then
			If cc\VoiceEmo<>CHAOVOICEEMO_CASUAL Then cc\VoiceTimer=0 : StopChannel(cc\Channel_Voice)
			cc\PreviousVoiceEmo=cc\VoiceEmo
		EndIf

		If Not(cc\VoiceTimer>0) Then
			
			If (Not(ChannelPlaying(cc\Channel_Voice))) Then
				cc\VoiceTimer=(4.5575+Rand(-1.5,2.5))*secs#
				Select cc\VoiceEmo
					Case CHAOVOICEEMO_AMAZED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Amazed[Rand(1,2)]), cc\Pivot)
					Case CHAOVOICEEMO_ATTACK:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Attack[Rand(1,7)]), cc\Pivot)
					Case CHAOVOICEEMO_CASUAL:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Casual[Rand(1,25)]), cc\Pivot)
					Case CHAOVOICEEMO_CRAZY:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Crazy[Rand(1,3)]), cc\Pivot)
					Case CHAOVOICEEMO_CRYING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Crying[Rand(1,4)]), cc\Pivot)
					Case CHAOVOICEEMO_DISGUSTED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Disgusted[Rand(1,4)]), cc\Pivot)
					Case CHAOVOICEEMO_DROWNING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Drowning[Rand(1,2)]), cc\Pivot)
					Case CHAOVOICEEMO_EATING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Eating[Rand(1,4)]), cc\Pivot)
					Case CHAOVOICEEMO_FIGURED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Figured[Rand(1,2)]), cc\Pivot)
					Case CHAOVOICEEMO_FRUSTRATED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Frustrated[Rand(1,6)]), cc\Pivot)
					Case CHAOVOICEEMO_HUMMING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Humming[Rand(1,1)]), cc\Pivot)
					Case CHAOVOICEEMO_HURT:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Hurt[Rand(1,6)]), cc\Pivot)
					Case CHAOVOICEEMO_LAUGHING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Laughing[Rand(1,6)]), cc\Pivot)
					Case CHAOVOICEEMO_MUMBLING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Mumbling[Rand(1,3)]), cc\Pivot)
					Case CHAOVOICEEMO_PLAYING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Playing[Rand(1,6)]), cc\Pivot)
					Case CHAOVOICEEMO_READY:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Ready[Rand(1,3)]), cc\Pivot)
					Case CHAOVOICEEMO_REFUSING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Refusing[Rand(1,4)]), cc\Pivot)
					Case CHAOVOICEEMO_SAD:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Sad[Rand(1,3)]), cc\Pivot)
					Case CHAOVOICEEMO_SATISFIED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Satisfied[Rand(1,3)]), cc\Pivot)
					Case CHAOVOICEEMO_SCARED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Scared[Rand(1,3)]), cc\Pivot)
					Case CHAOVOICEEMO_SINGING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Singing[Rand(1,9)]), cc\Pivot)
					Case CHAOVOICEEMO_SLEEPING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Sleeping[Rand(1,6)]), cc\Pivot)
					Case CHAOVOICEEMO_SURPRISED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Surprised[Rand(1,5)]), cc\Pivot)
					Case CHAOVOICEEMO_TALKING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Talking[Rand(1,10)]), cc\Pivot)
					Case CHAOVOICEEMO_THINKING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Thinking[Rand(1,7)]), cc\Pivot)
					Case CHAOVOICEEMO_TIRED:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Tired[Rand(1,2)]), cc\Pivot)
					Case CHAOVOICEEMO_WAKING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Waking[Rand(1,4)]), cc\Pivot)
					Case CHAOVOICEEMO_YAWNING:
						cc\Channel_Voice=EmitSound(SOUNDS(ChaoVoice_Yawning[Rand(1,3)]), cc\Pivot)
				End Select
			EndIf
		Else
			cc\VoiceTimer=cc\VoiceTimer-timervalue#
		EndIf

	End Function




;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function Chao_Particle_Swim(cc.tChaoManager, size#=0.1)
	ParticleTemplate_Call(cc\Particle, PARTICLE_CHAO_SWIM, cc\Pivot, size#, 0, 0, 0, 0, 0.325)
End Function

;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________


Function Chao_Interface_NameTag(cc.tChaoManager, c.tCamera)
	If EntityInView(cc\Mesh,c\Entity) and EntityDistance(cc\Mesh,c\Entity)<750 Then
		height# = MeshHeight#(cc\Mesh)+0.5
		CameraProject c\Entity, EntityX(cc\Pivot), EntityY(cc\Pivot)+height#, EntityZ(cc\Pivot)
		x = ProjectedX () - 1
		y = ProjectedY () - 1
		StartDraw()
			SetBlend(FI_ALPHABLEND)
			SetAlpha(1.0)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			Select cc\Stats\Color
				Case CHAOCOLOR_CELESTE:	SetColor(000, 240, 255)
				Case CHAOCOLOR_WHITE:	SetColor(250, 250, 250)
				Case CHAOCOLOR_BLUE:	SetColor(015, 086, 247)
				Case CHAOCOLOR_RED:		SetColor(255, 010, 010)
				Case CHAOCOLOR_YELLOW:	SetColor(253, 239, 008)
				Case CHAOCOLOR_ORANGE:	SetColor(255, 163, 003)
				Case CHAOCOLOR_AZURE:	SetColor(005, 193, 255)
				Case CHAOCOLOR_PINK:	SetColor(255, 061, 203)
				Case CHAOCOLOR_PURPLE:	SetColor(178, 011, 248)
				Case CHAOCOLOR_GREEN:	SetColor(064, 194, 034)
				Case CHAOCOLOR_BROWN:	SetColor(115, 062, 029)
				Case CHAOCOLOR_GREY:	SetColor(134, 134, 134)
				Case CHAOCOLOR_LIME:	SetColor(158, 243, 010)
				Case CHAOCOLOR_BLACK:	SetColor(016, 016, 016)
			End Select
			DrawImageEx(INTERFACE(Interface_Indicator), x, y)
			SetColor(255, 255, 255)
			DrawRealText(cc\Name$, x, y, Interface_TextTitleChao_1, 1, 0, 63, 63, 63, 1.65)
		EndDraw()
	EndIf
End Function


;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function Object_CreateRaceChao()
	x#=-88.5
	y#=31.25
	z#=-2

	For i=1 to 8
		Select i
		Case 1: Object_CreateRaceChao_Held(i,x#,y#,z#)
		Default: Object_CreateRaceChao_Random(i,x#,y#,z#)
		End Select
		z#=z#-5

		Game\Interface\WinningChao[i]=0
	Next

	Game\Interface\RaceEnded=False
	Game\Interface\RaceBegan=False
	cam\Lock\ChaoRaceCamOrder=0
	Game\CamLock=1800*secs#

	pp(1)\Action=ACTION_CHAORACE

	Select Menu\RaceType
		Case 1: Game\Interface\RaceEndPoint#=375
		Case 2: Game\Interface\RaceEndPoint#=479.5
		Case 3: Game\Interface\RaceEndPoint#=481
		Case 4: Game\Interface\RaceEndPoint#=556
	End Select
	Game\Interface\RaceTime=180*secs#

End Function

Function Object_CreateKarateChao()
	x#=11.75
	y#=4.25
	z#=0

	For i=1 to 2
		Select i
		Case 1: Object_CreateRaceChao_Held(i,x#,y#,z#)
		Default: Object_CreateRaceChao_Random(i,-x#,y#,z#)
		End Select

		Game\Interface\WinningChao[i]=0
	Next

	Game\Interface\RaceEnded=False
	Game\Interface\RaceBegan=False
	cam\Lock\ChaoRaceCamOrder=0
	Game\CamLock=1800*secs#

	pp(1)\Action=ACTION_CHAORACE

	Game\Interface\KarateTurn=Rand(1,2)
	Game\Interface\RaceTime=70*secs#

End Function

Function Object_CreateRaceChao_Held(chaonumber,x#,y#,z#)
If (Not( Menu\Stage=0)) Then
	raceage=Menu\HeldChaoAge
	racepersona=Menu\HeldChaoPersona
	racecolor=Menu\HeldChaoColor
	raceshiny=Menu\HeldChaoShiny
	racemono=Menu\HeldChaoMonotone
	racetex=Menu\HeldChaoTextured
	raceshape=Menu\HeldChaoShape
	raceside=Menu\HeldChaoSide
	racehat=Menu\HeldChaoHat

	obj.tObject = Object_Chao_Create(0, 0, 0, 0)
	racecc.tChaoManager = Object_ChaoManager_Create(chaonumber, obj, false, 0, true, x#, y#, z#, true, raceage, racepersona, racecolor, raceshape, raceside, racehat, racemono, raceshiny, racetex)

	racecc\Stats\Run# = Menu\HeldChaoSkills[1] : racecc\Stats\CurrentRun# = Menu\HeldChaoCurrentSkills[1]
	racecc\Stats\Swim# = Menu\HeldChaoSkills[2] : racecc\Stats\CurrentSwim# = Menu\HeldChaoCurrentSkills[2]
	racecc\Stats\Fly# = Menu\HeldChaoSkills[3] : racecc\Stats\CurrentFly# = Menu\HeldChaoCurrentSkills[3]
	racecc\Stats\Strength# = Menu\HeldChaoSkills[4] : racecc\Stats\CurrentStrength# = Menu\HeldChaoCurrentSkills[4]
	racecc\Stats\Stamina# = Menu\HeldChaoSkills[5] : racecc\Stats\CurrentStamina# = Menu\HeldChaoCurrentSkills[5]
	racecc\Stats\Intelligence# = Menu\HeldChaoSkills[6] : racecc\Stats\CurrentIntelligence# = Menu\HeldChaoCurrentSkills[6]
	racecc\Stats\Luck# = Menu\HeldChaoSkills[7] : racecc\Stats\CurrentLuck# = Menu\HeldChaoCurrentSkills[7]

	racecc\HatchTimer=1800*secs#
	racecc\MateTimer=1800*secs#
	racecc\Stats\ReviveEternal=Menu\HeldChaoEternal
	racecc\Emo\Eternal=racecc\Stats\ReviveEternal

	Select Menu\Stage
	Case 998: racecc\Action=CHAOACTION_RACE_BEGIN
	Case 997: racecc\Action=CHAOACTION_KARATE_BEGIN
			Game\Interface\KarateHealth#[1]=20
			Game\Interface\KarateZeal#[1]=racecc\Stats\Stamina#+racecc\Stats\Luck#/2.0
	Case 999:
	Default: 
	If Menu\ChaoGarden=0 And Menu\Stage>0 Then 
		racecc\Action=CHAOACTION_BUDDY
	EndIf
	End Select
EndIf
End Function

Function Object_CreateRaceChao_Stage.tChaoManager(chaonumber,x#,y#,z#)
If (Not( Menu\Stage=0)) Then
	raceage=Menu\BuddyChaoAge
	racepersona=Menu\BuddyChaoPersona
	racecolor=Menu\BuddyChaoColor
	raceshiny=Menu\BuddyChaoShiny
	racemono=Menu\BuddyChaoMonotone
	racebright=Menu\BuddyChaoBright
	racetex=Menu\BuddyChaoTextured
	raceshape=Menu\BuddyChaoShape
	raceside=Menu\BuddyChaoSide
	racehat=Menu\BuddyChaoHat

	obj.tObject = Object_Chao_Create(0, 0, 0, 0)
	racecc.tChaoManager = Object_ChaoManager_Create(chaonumber, obj, false, 0, true, x#, y#, z#, true, raceage, racepersona, racecolor, raceshape, raceside, racehat, racemono, raceshiny, racetex, racebright)

	racecc\Stats\Run# = Menu\BuddyChaoSkills[1] : racecc\Stats\CurrentRun# = Menu\BuddyChaoCurrentSkills[1]
	racecc\Stats\Swim# = Menu\BuddyChaoSkills[2] : racecc\Stats\CurrentSwim# = Menu\BuddyChaoCurrentSkills[2]
	racecc\Stats\Fly# = Menu\BuddyChaoSkills[3] : racecc\Stats\CurrentFly# = Menu\BuddyChaoCurrentSkills[3]
	racecc\Stats\Strength# = Menu\BuddyChaoSkills[4] : racecc\Stats\CurrentStrength# = Menu\BuddyChaoCurrentSkills[4]
	racecc\Stats\Stamina# = Menu\BuddyChaoSkills[5] : racecc\Stats\CurrentStamina# = Menu\BuddyChaoCurrentSkills[5]
	racecc\Stats\Intelligence# = Menu\BuddyChaoSkills[6] : racecc\Stats\CurrentIntelligence# = Menu\BuddyChaoCurrentSkills[6]
	racecc\Stats\Luck# = Menu\BuddyChaoSkills[7] : racecc\Stats\CurrentLuck# = Menu\BuddyChaoCurrentSkills[7]

	racecc\HatchTimer=1800*secs#
	racecc\MateTimer=1800*secs#
	racecc\Stats\ReviveEternal=Menu\BuddyChaoEternal
	racecc\Emo\Eternal=racecc\Stats\ReviveEternal

	Select Menu\Stage
	Case 998: racecc\Action=CHAOACTION_RACE_BEGIN
	Case 997: racecc\Action=CHAOACTION_KARATE_BEGIN
			Game\Interface\KarateHealth#[1]=20
			Game\Interface\KarateZeal#[1]=racecc\Stats\Stamina#+racecc\Stats\Luck#/2.0
	Case 999:
	Default: 
	If Menu\ChaoGarden=0 And Menu\Stage>0 Then 
		racecc\Action=CHAOACTION_BUDDY
	EndIf
	End Select
EndIf
Return racecc
End Function

Function Object_CreateRaceChao_Random(chaonumber,x#,y#,z#)

	raceage=Menu\HeldChaoAge
	racepersona=ChaoManager_GiveRandomFace()
	racecolor=Rand(1,CHAOCOLORS_total)
	racemono=Rand(0,1)
	racetex=Rand(0,1)
	raceshiny=Rand(0,1)
	If raceage>=3 Then
		raceshape=Rand(1,5)
		raceside=Rand(1,3)
	Else
		raceshape=1
		raceside=3
	EndIf
	Select(Rand(1,3))
		Case 1: racehat=Rand(1,ITEM_MAX(2))
	End Select

	obj.tObject = Object_Chao_Create(0, 0, 0, 0)
	racecc.tChaoManager = Object_ChaoManager_Create(chaonumber, obj, false, 0, true, x#, y#, z#, true, raceage, racepersona, racecolor, raceshape, raceside, racehat, racemono, raceshiny, racetex)

	For h=1 to 7
		downlimit#=Menu\HeldChaoSkills[h]-5 : uplimit#=Menu\HeldChaoSkills[h]+2*Menu\Settings\StadiumDifficulty#
		If downlimit#<0 Then downlimit#=0
		If uplimit#>50 Then uplimit#=50
		downlimit2#=Menu\HeldChaoCurrentSkills[h]-5 : uplimit2#=Menu\HeldChaoCurrentSkills[h]+2*Menu\Settings\StadiumDifficulty#
		If downlimit2#<0 Then downlimit2#=0
		If uplimit2#>10 Then uplimit2#=10
		Select h
		Case 1: racecc\Stats\Run# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentRun# = Rand(downlimit2#,uplimit2#)
		Case 2: racecc\Stats\Swim# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentSwim# = Rand(downlimit2#,uplimit2#)
		Case 3: racecc\Stats\Fly# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentFly# = Rand(downlimit2#,uplimit2#)
		Case 4: racecc\Stats\Strength# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentStrength# = Rand(downlimit2#,uplimit2#)
		Case 5: racecc\Stats\Stamina# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentStamina# = Rand(downlimit2#,uplimit2#)
		Case 6: racecc\Stats\Intelligence# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentIntelligence# = Rand(downlimit2#,uplimit2#)
		Case 7: racecc\Stats\Luck# = Rand(downlimit#,uplimit#) : racecc\Stats\CurrentLuck# = Rand(downlimit2#,uplimit2#)
		End Select
	Next

	racecc\HatchTimer=1800*secs#
	racecc\MateTimer=1800*secs#

	Select Menu\Stage
	Case 998: racecc\Action=CHAOACTION_RACE_BEGIN
	Case 997: racecc\Action=CHAOACTION_KARATE_BEGIN
			Game\Interface\KarateHealth#[2]=20
			Game\Interface\KarateZeal#[2]=racecc\Stats\Stamina#+racecc\Stats\Luck#/2.0
	End Select

End Function

;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function ChaoManager_Race_Camera(cc.tChaoManager, d.tDeltaTime)

	If cam\Lock\ChaoRaceCamTimer>0 Then cam\Lock\ChaoRaceCamTimer=cam\Lock\ChaoRaceCamTimer-timervalue#

	Select cc\Action
		Case CHAOACTION_RACE_BEGIN:
			Select cam\Lock\ChaoRaceCamOrder
				Case 0:
					cam\Lock\PreviousPos=2
					cam\Lock\Pos=2
					cam\Lock\Immediate=1
					cam\Lock\Position\x#=-85 : cam\Lock\Position\y#=33 : cam\Lock\Position\z#=-2
					PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
					cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=90
					cam\Lock\Zoom#=12.5
					cam\Lock\Speed#=30/10
					cam\Lock\ChaoRaceCamTimer=2*secs#
					cam\Lock\ChaoRaceCamOrder=1
					EntityType(cam\Entity, COLLISION_NONE)
				Case 1:
					If Not(cam\Lock\ChaoRaceCamTimer>0) Then
						cam\Lock\Immediate=0
						cam\Lock\ChaoRaceCamOrder=2
					EndIf
				Case 2:
					If cam\Lock\Position\z#>-37 Then
						cam\Lock\Position\z#=cam\Lock\Position\z#-0.125*d\Delta
						PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
					Else
						cam\Lock\ChaoRaceCamOrder=3
						cam\Lock\ChaoRaceCamTimer=1*secs#
					EndIf
				Case 3:
					If Not(cam\Lock\ChaoRaceCamTimer>0) Then
						cam\Lock\Immediate=1
						cam\Lock\ChaoRaceCamOrder=0
						cam\Lock\ChaoRaceCamTimer=0
						For racecc.tChaoManager = Each tChaoManager : racecc\Action=CHAOACTION_RACE_COMMON : Next
						Game\Interface\RaceBegan=True
					EndIf
			End Select
		Default:
			If Game\Interface\RaceEnded=False Then
				dontlookchaoracefront=false : If cc\g\Motion\Align\x#<-0.25 Or cc\Action=CHAOACTION_RACE_CLIMB Or (Menu\RaceType=3 and cc\Position\x#<440) Then dontlookchaoracefront=true
				dontlookchaoraceback=false : If cc\Position\x#<13.75 Or cc\g\Motion\Align\x#>0.25 Then dontlookchaoraceback=true
				If Not(cam\Lock\ChaoRaceCamTimer>0) Then
					cam\Lock\ChaoRaceCamTimer=(10+Rand(-2,2))*secs#
					If dontlookchaoracefront and dontlookchaoraceback Then
						Game\Interface\RaceCam=1
					ElseIf dontlookchaoracefront Then
						Select(Rand(1,4))
						Case 1: Game\Interface\RaceCam=1
						Case 2: Game\Interface\RaceCam=4
						Case 3: Game\Interface\RaceCam=7
						Case 4: Game\Interface\RaceCam=8
						End Select
					ElseIf dontlookchaoraceback Then
						Select(Rand(1,5))
						Case 1: Game\Interface\RaceCam=1
						Case 2: Game\Interface\RaceCam=2
						Case 3: Game\Interface\RaceCam=3
						Case 4: Game\Interface\RaceCam=5
						Case 5: Game\Interface\RaceCam=6
						End Select
					Else
						Game\Interface\RaceCam=Rand(1,8)
					EndIf
				EndIf
				Select Game\Interface\RaceCam
					Case 1: ;top view
						cam\Lock\Position\y#=cc\Position\y#+15.75
						cam\Lock\Rotation\x#=90 : cam\Lock\Rotation\y#=-90
						cam\Lock\Zoom#=60
					Case 2: ;front side view right
						cam\Lock\Position\y#=cc\Position\y#-3.75
						cam\Lock\Rotation\x#=46 : cam\Lock\Rotation\y#=31.5
						cam\Lock\Zoom#=42
						If dontlookchaoracefront Then cam\Lock\ChaoRaceCamTimer=0
					Case 3: ;front side view left
						cam\Lock\Position\y#=cc\Position\y#-3.75
						cam\Lock\Rotation\x#=46 : cam\Lock\Rotation\y#=121.5
						cam\Lock\Zoom#=42
						If dontlookchaoracefront Then cam\Lock\ChaoRaceCamTimer=0
					Case 4: ;back view
						cam\Lock\Position\y#=cc\Position\y#+0.25
						cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=-90
						cam\Lock\Zoom#=50
						If dontlookchaoraceback Then cam\Lock\ChaoRaceCamTimer=0
					Case 5: ;front view
						cam\Lock\Position\y#=cc\Position\y#+0.25
						cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=90
						cam\Lock\Zoom#=50
						If dontlookchaoracefront Then cam\Lock\ChaoRaceCamTimer=0
					Case 6: ;general view
						cam\Lock\Position\y#=cc\Position\y#+2.75
						cam\Lock\Rotation\x#=31 : cam\Lock\Rotation\y#=75
						cam\Lock\Zoom#=30
						If dontlookchaoracefront Then cam\Lock\ChaoRaceCamTimer=0
					Case 7: ;back side view right
						cam\Lock\Position\y#=cc\Position\y#-3.75
						cam\Lock\Rotation\x#=46 : cam\Lock\Rotation\y#=301.5
						cam\Lock\Zoom#=42
						If dontlookchaoraceback Then cam\Lock\ChaoRaceCamTimer=0
					Case 8: ;back side view left
						cam\Lock\Position\y#=cc\Position\y#-3.75
						cam\Lock\Rotation\x#=46 : cam\Lock\Rotation\y#=211.5
						cam\Lock\Zoom#=42
						If dontlookchaoraceback Then cam\Lock\ChaoRaceCamTimer=0
				End Select
				cam\Lock\Position\x#=cc\Position\x# : cam\Lock\Position\z#=-19.5
				PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
			Else
				cam\Lock\Position\x#=Game\Interface\RaceEndPoint#+54 : cam\Lock\Position\y#=35.5 : cam\Lock\Position\z#=-13
				PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
				cam\Lock\Rotation\x#=9.25 : cam\Lock\Rotation\y#=-108.75
				cam\Lock\Zoom#=10
			EndIf
	End Select

	PositionEntity cam\Lock\PosMesh, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1

	EntityType(pp(1)\Objects\Entity, COLLISION_NONE)
	PositionEntity pp(1)\Objects\Entity, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1

End Function

;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function ChaoManager_Race_EndRace(cc.tChaoManager)

	endedrace=0

	For i=1 to 8
		If Game\Interface\WinningChao[i]=0 and endedrace=0 Then
			Game\Interface\WinningChao[i]=cc\Number
			cc\WinningChao=i
			If i>3 Then endedrace=2 Else endedrace=1
			If i>=8 Then ChaoManager_Race_EndRace_End()
			If cc\Number=1 Then Game\Interface\YourWinningChao=i : Game\ResultRingsForBank=2500.0*(abs(i-8)/7.0)*(Menu\RaceType/4.0)*(Menu\Settings\StadiumDifficulty#/5.0)
		EndIf
	Next

	Select endedrace
		Case 1: cc\Action=CHAOACTION_RACE_WIN
			If cc\Number=1 Then Menu\HeldChaoCompetitionsWon=Menu\HeldChaoCompetitionsWon+1 : Menu\SaveChaoCompetitions=1
		Case 2: cc\Action=CHAOACTION_RACE_LOSE
			If cc\Number=1 Then Menu\HeldChaoCompetitionsLost=Menu\HeldChaoCompetitionsLost+1 : Menu\SaveChaoCompetitions=1
	End Select

End Function

Function ChaoManager_Race_EndRace_End()

	Game\Interface\RaceEnded=True
	Game\Interface\RaceTime=0

	Game\Interface\ResultOrder=-1

	PostEffect_Create_FadeIn(0.008, 255, 255, 255)
	For i=0 to 2 : StopChannel(Game\Stage\Properties\MusicChn[i]) : Next
	Game\Victory=2

	Game\Channel_MissionCompleted=PlaySmartSound(Sound_ChaoRaceCompleted)
	ChannelVolume(Game\Channel_MissionCompleted,Menu\Settings\VolumeM#*Menu\Settings\Volume#)

End Function

Function ChaoManager_Race_Manage(cc.tChaoManager, d.tDeltaTime)

	If cc\Number=1 Then
		ChaoManager_Race_Camera(cc,d)
		If (Not(Game\Interface\RaceTime>0)) and Game\Interface\RaceEnded=False Then
			For racecc.tChaoManager = Each tChaoManager
				racecc\WinningChao=-1
				racecc\Action=CHAOACTION_RACE_LOST
				If racecc\Number=1 Then Game\Interface\YourWinningChao=-1
			Next
			ChaoManager_Race_EndRace_End()
		EndIf
	EndIf

	If Game\Interface\RaceEnded=False Then
		If cc\Position\x#>=Game\Interface\RaceEndPoint# and cc\WinningChao=0 Then ChaoManager_Race_EndRace(cc)

		Select Menu\RaceType
			Case 2:
				If ChaoManager_Race_Manage_PositionCheck(cc,70.25) and (Not(cc\Action=CHAOACTION_RACE_SWIM)) Then cc\Action=CHAOACTION_RACE_SWIM
				If ChaoManager_Race_Manage_PositionCheck(cc,407.75) and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON
			Case 3:
				If ChaoManager_Race_Manage_PositionCheck(cc,75.75) and cc\Position\y#<-5+45.5 and (Not(cc\Action=CHAOACTION_RACE_CLIMB)) Then cc\Action=CHAOACTION_RACE_CLIMB
				If ChaoManager_Race_Manage_PositionCheck(cc,75.75) and cc\Position\y#>45.5 and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON : MoveEntity cc\Pivot, 0, 0.25, 0.5
				If ChaoManager_Race_Manage_PositionCheck(cc,100.25) and cc\Position\y#<-5+60.75 and (Not(cc\Action=CHAOACTION_RACE_CLIMB)) Then cc\Action=CHAOACTION_RACE_CLIMB
				If ChaoManager_Race_Manage_PositionCheck(cc,100.25) and cc\Position\y#>60.75 and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON : MoveEntity cc\Pivot, 0, 0.25, 0.5
				If ChaoManager_Race_Manage_PositionCheck(cc,123.5) and cc\Position\y#<-5+75.5 and (Not(cc\Action=CHAOACTION_RACE_CLIMB)) Then cc\Action=CHAOACTION_RACE_CLIMB
				If ChaoManager_Race_Manage_PositionCheck(cc,123.5) and cc\Position\y#>75.5 and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON : MoveEntity cc\Pivot, 0, 0.25, 0.5
				If ChaoManager_Race_Manage_PositionCheck(cc,209.5) and cc\Position\y#<-5+74.5 and (Not(cc\Action=CHAOACTION_RACE_CLIMB)) Then cc\Action=CHAOACTION_RACE_CLIMB
				If ChaoManager_Race_Manage_PositionCheck(cc,209.5) and cc\Position\y#>74.5 and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON : MoveEntity cc\Pivot, 0, 0.25, 0.5
				If ChaoManager_Race_Manage_PositionCheck(cc,228.75) and cc\Position\y#<-5+95.25 and (Not(cc\Action=CHAOACTION_RACE_CLIMB)) Then cc\Action=CHAOACTION_RACE_CLIMB
				If ChaoManager_Race_Manage_PositionCheck(cc,228.75) and cc\Position\y#>95.25 and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON : MoveEntity cc\Pivot, 0, 0.25, 0.5
				If ChaoManager_Race_Manage_PositionCheck(cc,306.5) and cc\Position\y#<-5+95 and (Not(cc\Action=CHAOACTION_RACE_CLIMB)) Then cc\Action=CHAOACTION_RACE_CLIMB
				If ChaoManager_Race_Manage_PositionCheck(cc,306.5) and cc\Position\y#>95 and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON : MoveEntity cc\Pivot, 0, 0.25, 0.5
			Case 4:
				If ChaoManager_Race_Manage_PositionCheck(cc,75.5) and (Not(cc\Action=CHAOACTION_RACE_FLY)) Then cc\Action=CHAOACTION_RACE_FLY
				If ChaoManager_Race_Manage_PositionCheck(cc,155) and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON
				If ChaoManager_Race_Manage_PositionCheck(cc,198.5) and (Not(cc\Action=CHAOACTION_RACE_FLY)) Then cc\Action=CHAOACTION_RACE_FLY
				If ChaoManager_Race_Manage_PositionCheck(cc,267.5) and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON
				If ChaoManager_Race_Manage_PositionCheck(cc,313) and (Not(cc\Action=CHAOACTION_RACE_FLY)) Then cc\Action=CHAOACTION_RACE_FLY
				If ChaoManager_Race_Manage_PositionCheck(cc,378.25) and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON
				If ChaoManager_Race_Manage_PositionCheck(cc,423.5) and (Not(cc\Action=CHAOACTION_RACE_FLY)) Then cc\Action=CHAOACTION_RACE_FLY
				If ChaoManager_Race_Manage_PositionCheck(cc,477) and (Not(cc\Action=CHAOACTION_RACE_COMMON)) Then cc\Action=CHAOACTION_RACE_COMMON
		End Select
	EndIf

End Function

Function ChaoManager_Race_Manage_PositionCheck(cc.tChaoManager, pos#)
	If cc\Position\x#>=pos# and cc\Position\x#<pos#+5 Then Return true Else Return false
End Function

;_________________________________________________________________________________________________________

Function ChaoManager_Race_GetChaoOrder()

	If Game\Interface\RaceBegan Then
		If Game\Interface\RaceChaoOrderCheckTimer>0 Then
			Game\Interface\RaceChaoOrderCheckTimer=Game\Interface\RaceChaoOrderCheckTimer-timervalue#
		Else
			Game\Interface\RaceChaoOrderCheckTimer=0.5*secs#

			For cc.tChaoManager = Each tChaoManager
				If cc\WinningChao=0 Then
					Game\Interface\RaceChaoOrderChecker[cc\Number]=cc\Position\x#
				Else
					Game\Interface\RaceChaoOrderChecker[cc\Number]=1000+abs(cc\WinningChao-8)*10
				EndIf
				Game\Interface\RaceChaoOrder[cc\Number]=cc\Number
			Next

			ChaoManager_Race_GetChaoOrder_Sort()
		EndIf
	Else
		For i=1 to 8 : Game\Interface\RaceChaoOrder[i]=i : Next
	EndIf

End Function

Function ChaoManager_Race_GetChaoOrder_Sort_Swap(i1,i2)
	temp = Game\Interface\RaceChaoOrderChecker[i1]
	Game\Interface\RaceChaoOrderChecker[i1]=Game\Interface\RaceChaoOrderChecker[i2]
	Game\Interface\RaceChaoOrderChecker[i2]=temp

	temp = Game\Interface\RaceChaoOrder[i1]
	Game\Interface\RaceChaoOrder[i1]=Game\Interface\RaceChaoOrder[i2]
	Game\Interface\RaceChaoOrder[i2]=temp
End Function

Function ChaoManager_Race_GetChaoOrder_QuickSort_Partition(p, q)
	x = Game\Interface\RaceChaoOrderChecker[p]
	i = p
	y = q

	j = p + 1
	while(j <= q)
			if (Game\Interface\RaceChaoOrderChecker[j] < x) then
				i = i + 1
				ChaoManager_Race_GetChaoOrder_Sort_Swap(i, j)
			else if (Game\Interface\RaceChaoOrderChecker[j] = x) then
				y = y - 1
				ChaoManager_Race_GetChaoOrder_Sort_Swap(i, y)
			endif
		j = j + 1
	wend

	ChaoManager_Race_GetChaoOrder_Sort_Swap(p, i)

	return i
End Function

Function ChaoManager_Race_GetChaoOrder_QuickSort_Conquer(p, r)
	if (p < r) then
		q = ChaoManager_Race_GetChaoOrder_QuickSort_Partition(p, r)
		ChaoManager_Race_GetChaoOrder_QuickSort_Conquer(p, q - 1)
		ChaoManager_Race_GetChaoOrder_QuickSort_Conquer(q + 1, r)
	endif
End Function

Function ChaoManager_Race_GetChaoOrder_Sort()
	ChaoManager_Race_GetChaoOrder_QuickSort_Conquer(1, 8)
End Function

;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function ChaoManager_Karate_Camera(cc.tChaoManager)

	If cam\Lock\ChaoRaceCamTimer>0 Then cam\Lock\ChaoRaceCamTimer=cam\Lock\ChaoRaceCamTimer-timervalue#

	Select cc\Action
		Case CHAOACTION_KARATE_BEGIN:
			Select cam\Lock\ChaoRaceCamOrder
				Case 0:
					cam\Lock\PreviousPos=2
					cam\Lock\Pos=2
					cam\Lock\Immediate=1
					cam\Lock\Position\x#=6 : cam\Lock\Position\y#=cc\Position\y# : cam\Lock\Position\z#=0
					PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
					cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=-90
					cam\Lock\Zoom#=2.5
					cam\Lock\Speed#=30/10
					cam\Lock\ChaoRaceCamTimer=3*secs#
					cam\Lock\ChaoRaceCamOrder=1
					EntityType(cam\Entity, COLLISION_NONE)
				Case 1:
					If Not(cam\Lock\ChaoRaceCamTimer>0) Then
						cam\Lock\Position\x#=-6 : cam\Lock\Position\y#=cc\Position\y# : cam\Lock\Position\z#=0
						PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
						cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=90
						cam\Lock\ChaoRaceCamTimer=2.75*secs#
						cam\Lock\ChaoRaceCamOrder=2
					EndIf
				Case 2:
					If Not(cam\Lock\ChaoRaceCamTimer>0) Then
						cam\Lock\ChaoRaceCamOrder=3
					EndIf
				Case 3:
					cam\Lock\ChaoRaceCamOrder=0
					cam\Lock\ChaoRaceCamTimer=0
					For racecc.tChaoManager = Each tChaoManager : racecc\Action=CHAOACTION_KARATE_COMMON : Next
					Game\Interface\RaceBegan=True
			End Select
		Default:
			If Game\Interface\RaceEnded=False Then
				If cc\Position\x#>0 Then cam\Lock\Position\x#=cc\Position\x#/2.0 Else cam\Lock\Position\x#=cc\Position\x#
				cam\Lock\Position\y#=cc\Position\y# : cam\Lock\Position\z#=0
				PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
				cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=180
				cam\Lock\Zoom#=8
			Else
				cam\Lock\Position\x#=0 : cam\Lock\Position\y#=3.8 : cam\Lock\Position\z#=0
				PositionEntity cam\Lock\PosMeshTarget, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1
				cam\Lock\Rotation\x#=0 : cam\Lock\Rotation\y#=180
				cam\Lock\Zoom#=12
			EndIf
	End Select

	PositionEntity cam\Lock\PosMesh, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1

	EntityType(pp(1)\Objects\Entity, COLLISION_NONE)
	PositionEntity pp(1)\Objects\Entity, cam\Lock\Position\x#, cam\Lock\Position\y#, cam\Lock\Position\z#, 1

End Function

;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function ChaoManager_Karate_EndRace()

	If (Not(Game\Interface\RaceTime>0)) Then
		If Game\Interface\KarateHealth[1]=Game\Interface\KarateHealth[2] Then
			win=2
		Else
			If Game\Interface\KarateHealth[1]<Game\Interface\KarateHealth[2] Then win=0 Else win=1
		EndIf
	Else
		If Game\Interface\KarateHealth[1]=0 Then win=0 Else win=1
	EndIf

	If win=0 Then
		For karatecc.tChaoManager = Each tChaoManager
			Select karatecc\Number
			Case 1: karatecc\Action=CHAOACTION_KARATE_LOSE
				If karatecc\Number=1 Then Menu\HeldChaoCompetitionsLost=Menu\HeldChaoCompetitionsLost+1 : Menu\SaveChaoCompetitions=1
			Case 2: karatecc\Action=CHAOACTION_KARATE_WIN
				If karatecc\Number=1 Then Menu\HeldChaoCompetitionsWon=Menu\HeldChaoCompetitionsWon+1 : Menu\SaveChaoCompetitions=1
			End Select
		Next
		Game\Interface\YourWinningChao=0
		ChaoManager_Karate_EndRace_End(1)
	ElseIf win=1
		For karatecc.tChaoManager = Each tChaoManager
			Select karatecc\Number
			Case 1: karatecc\Action=CHAOACTION_KARATE_WIN
				If karatecc\Number=1 Then Menu\HeldChaoCompetitionsWon=Menu\HeldChaoCompetitionsWon+1 : Menu\SaveChaoCompetitions=1
			Case 2: karatecc\Action=CHAOACTION_KARATE_LOSE
				If karatecc\Number=1 Then Menu\HeldChaoCompetitionsLost=Menu\HeldChaoCompetitionsLost+1 : Menu\SaveChaoCompetitions=1
			End Select
		Next
		Game\Interface\YourWinningChao=1
		Game\ResultRingsForBank=1000.0*(Menu\Settings\StadiumDifficulty#/5.0)
		ChaoManager_Karate_EndRace_End(0)
	ElseIf win=2 Then
		For karatecc.tChaoManager = Each tChaoManager
			Select karatecc\Number
			Case 1: karatecc\Action=CHAOACTION_KARATE_LOSE
				If karatecc\Number=1 Then Menu\HeldChaoCompetitionsLost=Menu\HeldChaoCompetitionsLost+1 : Menu\SaveChaoCompetitions=1
			Case 2: karatecc\Action=CHAOACTION_KARATE_LOSE
				If karatecc\Number=1 Then Menu\HeldChaoCompetitionsLost=Menu\HeldChaoCompetitionsLost+1 : Menu\SaveChaoCompetitions=1
			End Select
		Next
		Game\Interface\YourWinningChao=0
		ChaoManager_Karate_EndRace_End(1)
	EndIf

End Function

Function ChaoManager_Karate_EndRace_End(lose)

	Game\Interface\RaceEnded=True
	Game\Interface\RaceTime=0

	Game\Interface\ResultOrder=-1

	PostEffect_Create_FadeIn(0.008, 255, 255, 255)
	For i=0 to 2 : StopChannel(Game\Stage\Properties\MusicChn[i]) : Next
	Game\Victory=2

	Select lose
	Case 0: Game\Channel_MissionCompleted=PlaySmartSound(Sound_ChaoKarateWin)
	Case 1: Game\Channel_MissionCompleted=PlaySmartSound(Sound_ChaoKarateLose)
	End Select
	ChannelVolume(Game\Channel_MissionCompleted,Menu\Settings\VolumeM#*Menu\Settings\Volume#)

End Function

Function ChaoManager_Karate_Manage(cc.tChaoManager, d.tDeltaTime)

	If cc\Number=1 Then
		ChaoManager_Karate_Camera(cc)
		If Game\Interface\RaceEnded=False Then
			If (Not(Game\Interface\KarateEndTimer>0)) Then
				If (Not(Game\Interface\RaceTime>0)) Or (Game\Interface\KarateHealth[1]<=0 Or Game\Interface\KarateHealth[2]<=0) Then
					If Game\Interface\KarateHealth[1]<=0 Then Game\Interface\KarateHealth[1]=0
					If Game\Interface\KarateHealth[2]<=0 Then Game\Interface\KarateHealth[2]=0
					Game\Interface\KarateEndTimer=(1+2)*secs#
				EndIf
			EndIf

			If Game\Interface\KarateEndTimer>0 Then
				Game\Interface\KarateEndTimer=Game\Interface\KarateEndTimer-timervalue#
				If Game\Interface\KarateEndTimer<1*secs# Then
					Game\Interface\KarateEndTimer=0
					ChaoManager_Karate_EndRace()
				EndIf
			EndIf
		EndIf
	EndIf

	If Game\Interface\RaceEnded=False Then
		Select cc\Number
			Case 1: cc\g\Motion\Direction#=90
			Case 2: cc\g\Motion\Direction#=-90
		End Select

		If Not(cc\Action=CHAOACTION_KARATE_BEGIN) Then
			If Game\Interface\KarateZeal#[cc\Number]<=-1 Then
				If Game\Interface\KarateTurn=cc\Number Then
					If Game\Interface\KarateTurn=1 Then Game\Interface\KarateTurn=2 Else Game\Interface\KarateTurn=1
				EndIf
				If Not(cc\MeditateTimer>0) Then Game\Interface\KarateZeal#[cc\Number]=10.0
			Else
				If Game\Interface\KarateZeal#[cc\Number]>0 Then
					Game\Interface\KarateZeal#[cc\Number]=Game\Interface\KarateZeal#[cc\Number]-0.01*d\Delta
				Else
					Game\Interface\KarateZeal#[cc\Number]=-1
					cc\MeditateTimer=(1+Rand(0,2))*secs#
				EndIf
			EndIf
		EndIf
	EndIf

End Function

;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________
;_________________________________________________________________________________________________________

Function Object_CreateRandomChao(chaonumber,x#,y#,z#)

	Select(Rand(1,2))
		Case 1: randomchaoage=1
		Case 2: randomchaoage=3
	End Select
	randomchaopersona=ChaoManager_GiveRandomFace()
	randomchaocolor=Rand(1,CHAOCOLORS_total)
	randomchaomono=Rand(0,1)
	randomchaoshiny=Rand(0,1)
	randomchaotex=Rand(0,1)
	If randomchaoage>=3 Then
		randomchaoshape=Rand(1,5)
		randomchaoside=Rand(1,3)
	Else
		randomchaoshape=1
		randomchaoside=3
	EndIf
	Select(Rand(1,3))
		Case 1: randomchaohat=Rand(1,ITEM_MAX(2))
	End Select

	obj.tObject = Object_Chao_Create(0, 0, 0, 0)
	cc.tChaoManager = Object_ChaoManager_Create(chaonumber, obj, false, 0, true, x#, y#, z#, true, randomchaoage, randomchaopersona, randomchaocolor, randomchaoshape, randomchaoside, randomchaohat, randomchaomono, randomchaoshiny, randomchaotex)

	For h=1 to 7
		downlimit#=0 : uplimit#=50
		downlimit2#=0 : uplimit2#=10
		Select h
		Case 1: cc\Stats\Run# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentRun# = Rand(downlimit2#,uplimit2#)
		Case 2: cc\Stats\Swim# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentSwim# = Rand(downlimit2#,uplimit2#)
		Case 3: cc\Stats\Fly# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentFly# = Rand(downlimit2#,uplimit2#)
		Case 4: cc\Stats\Strength# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentStrength# = Rand(downlimit2#,uplimit2#)
		Case 5: cc\Stats\Stamina# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentStamina# = Rand(downlimit2#,uplimit2#)
		Case 6: cc\Stats\Intelligence# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentIntelligence# = Rand(downlimit2#,uplimit2#)
		Case 7: cc\Stats\Luck# = Rand(downlimit#,uplimit#) : cc\Stats\CurrentLuck# = Rand(downlimit2#,uplimit2#)
		End Select
	Next

	Select randomchaoage
		Case 1:
			cc\HatchTimer=(3600+Rand(-600,600))*secs#
		Case 3:
			cc\HatchTimer=(10800+Rand(-1200,1200))*secs#
			cc\Stats\MateSeason=1
	End Select

End Function

Function OverwriteGardenAndCreateRandomChao()
	Game_Stage_End_Chao()
	For o.tObject = Each tObject
		If o\ObjType=OBJTYPE_CHAO Then
			FreeEntity o\Entity
			Delete o\Position
			Delete o
		EndIf
	Next

	For i=1 to CHAOCOUNT
		If FileType(SaveDataPath$+"CHAO"+i+".dat")=1 Then Menu_Transporter_Goodbye_DeleteAChao(i) : i=i-1
	Next

	CHAOSUM(1)=0
	x#=pp(1)\Objects\Position\x# : y#=pp(1)\Objects\Position\y# : z#=pp(1)\Objects\Position\z#
	gardenoverwriter=CreatePivot()
	EntityType(gardenoverwriter,COLLISION_OBJECT)
	For i=1 to CHAOCOUNT
		PositionEntity gardenoverwriter, x#, y#, z#, 1
		MoveEntity gardenoverwriter, Rand(-10,10), 0, Rand(-10,10)
		Object_CreateRandomChao(i,EntityX(gardenoverwriter),EntityY(gardenoverwriter),EntityZ(gardenoverwriter))
		CHAOSUM(1)=CHAOSUM(1)+1 : CHAOSLOTS(1,CHAOSUM(1))=1
	Next
	FreeEntity gardenoverwriter

	For cc.tChaoManager=Each tChaoManager
		SaveGame_Chao(cc)
	Next
	SaveGame_ChaoGarden()
End Function