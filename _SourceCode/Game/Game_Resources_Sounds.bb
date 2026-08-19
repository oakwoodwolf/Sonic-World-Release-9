
; initiate the basica array to store every sound
Dim SOUNDS(SOUNDS_TOTAL)

; initiate the array to store the type of volume that affects the sound
Dim SOUNDS_V(SOUNDS_TOTAL)

; initiate the array that checks to see if the sound is currently loaded into memory
Dim SOUNDS_EXISTS(SOUNDS_TOTAL)

Dim SOUNDS_NAME$(SOUNDS_TOTAL)

; this function checks to see if a specific sound currently exists, and if not, loads the sound into memory
Function SmartSound(x)
	If SOUNDS_EXISTS(x)=False Then LoadSmartSound(x)
	Return x
End Function

; this function unloads the specific sound from memory
Function FreeSmartSound(x)
	If SOUNDS_EXISTS(x) Then
		FreeSound SOUNDS(x) : SOUNDS(x)=0 : SOUNDS_V(x)=0
		SOUNDS_EXISTS(x)=False
	EndIf
End Function

; this function loads a specific sound into the sounds array
Function LoadGoodSound(x,mode,directory$,volume=1)
	
	; clear the sound from current memory
	FreeSmartSound(x)
	
	
	
	; check to see if there is a stage specific sound, and load it instead of the default or theme specific sound
	; if no specific sound is found in the stage folder then load the sound from either the theme specific folder if it exists or the "Sounds" folder if not.
	If FileType(Game\Stage\Properties\Path$+directory$)=1 And Menu\Stage<>0 Then
		pathtosound$=Game\Stage\Properties\Path$+directory$
	Else
		If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+directory$)) Then
			pathtosound$=directory$
		Else	
			pathtosound$=(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+directory$)
		EndIf
	EndIf
	
	
	SOUNDS_NAME$(x)=Replace$(Mid$(directory$,8,25),".ogg","")
	
	If mode=1 Or mode=4 Then
		SOUNDS(x)=LoadSound(pathtosound$)
	ElseIf mode=3 Then
		If Menu\Settings\ThreeDSounds#=1 Then
			SOUNDS(x)=Load3DSound(pathtosound$)
		Else
			SOUNDS(x)=LoadSound(pathtosound$)
		EndIf
	EndIf
	
	Select volume
		Case 1: SoundVolume(SOUNDS(x),Menu\Settings\VolumeSFX#*(Menu\Settings\Volume#*0.175))
		Case 2: SoundVolume(SOUNDS(x),Menu\Settings\VolumeVA#*(Menu\Settings\Volume#*0.175))
		Case 3: SoundVolume(SOUNDS(x),Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
		Case 4: SoundVolume(SOUNDS(x),Menu\Settings\VolumeSFX#*Menu\Settings\VolumeAmb#*(Menu\Settings\Volume#*0.175))
	End Select
	SOUNDS_V(x)=volume
	
	SOUNDS_EXISTS(x)=True
End Function

Function PlaySmartSound(x)
	Return PlaySound(SOUNDS(SmartSound(x)))	
End Function

Function EmitSmartSound(x, pivot)
	Return EmitSound(SOUNDS(SmartSound(x)), pivot)
End Function

Function LoadMenuMusic()
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuMain.ogg")) Then
		MenuMusic_Main=LoadSound("Sounds/MenuMusic/MenuMain.ogg")
	Else	
		MenuMusic_Main=LoadSound(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuMain.ogg")
	EndIf
	SoundVolume(MenuMusic_Main,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	
	
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuIntro.ogg")) Then
		MenuMusic_Intro=LoadSound("Sounds/MenuMusic/MenuIntro.ogg")
	Else	
		MenuMusic_Intro=LoadSound(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuIntro.ogg")
	EndIf
	SoundVolume(MenuMusic_Intro,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuProgress.ogg")) Then
		MenuMusic_Stats=LoadSound("Sounds/MenuMusic/MenuProgress.ogg")
	Else	
		MenuMusic_Stats=LoadSound(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuProgress.ogg")
	EndIf
	SoundVolume(MenuMusic_Stats,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuCredits.ogg")) Then
		MenuMusic_Credits=LoadSound("Sounds/MenuMusic/MenuCredits.ogg")
	Else	
		MenuMusic_Credits=LoadSound(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuCredits.ogg")
	EndIf
	SoundVolume(MenuMusic_Credits,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	
	
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuOptions.ogg")) Then
		MenuMusic_Options=LoadSound("Sounds/MenuMusic/MenuOptions.ogg")
	Else	
		MenuMusic_Options=LoadSound(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuOptions.ogg")
	EndIf
	SoundVolume(MenuMusic_Options,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuShop.ogg")) Then
		MenuMusic_Shop=LoadSound("Sounds/MenuMusic/MenuShop.ogg")
	Else	
		MenuMusic_Shop=LoadSound(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/MenuMusic/MenuShop.ogg")
	EndIf
	SoundVolume(MenuMusic_Shop,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
End Function

Function UpdateAllSoundVolumes()
	For x=1 To SOUNDS_TOTAL
		Select SOUNDS_V(x)
			Case 1: SoundVolume(SOUNDS(x),Menu\Settings\VolumeSFX#*(Menu\Settings\Volume#*0.175))
			Case 2: SoundVolume(SOUNDS(x),Menu\Settings\VolumeVA#*(Menu\Settings\Volume#*0.175))
			Case 3: SoundVolume(SOUNDS(x),Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
			Case 4: SoundVolume(SOUNDS(x),Menu\Settings\VolumeSFX#*Menu\Settings\VolumeAmb#*(Menu\Settings\Volume#*0.175))
		End Select
	Next
End Function



Function UpdateAllPlayerVolumes()
	For s = 0 To Sound_JumpX
		For v=Voice_Attack1 To PLAYER_VOICES
			For p.tPlayer = Each tPlayer
				SoundVolume(p\Voice[s],Menu\Settings\VolumeSFX#*(Menu\Settings\Volume#*0.175))
				SoundVolume(p\Voice[v],Menu\Settings\VolumeVA#*(Menu\Settings\Volume#*0.175))
			Next
		Next
	Next
End Function

Function MuteEverything()
	
	Menu\Settings\PreviousVolume#=Menu\Settings\Volume#
	Menu\Settings\Volume#=0
	
	UpdateAllSoundVolumes()
	UpdateAllPlayerVolumes()
	
End Function

Function UnmuteEverything()
	
	Menu\Settings\Volume#=Menu\Settings\PreviousVolume#
	
	UpdateAllSoundVolumes()
	UpdateAllPlayerVolumes()
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function LoadStageMusic(Path$)
	
	If Menu\TutorialMode=1 Then  
		FreeSound(StageMusic[0])
		StageMusic[0] = LoadSound(Path$+"Media/"+(Str(ShortCharNames(Menu\Character[1],1)))+".ogg")
	Else
		
		For i=0 To 2
			FreeSound(StageMusic[i])
			
			If Menu\ChaoGarden=0 Then
				
				Select i
					Case 0: no$=""
					Case 1: no$="2"
					Case 2: no$="3"
				End Select
				
				If FileType(Path$+"/Media/hero"+no$+"m"+Menu\MissionNo+".ogg")=1 Then
					pathtomusic$=Path$+"/Media/hero"+no$+"m"+Menu\MissionNo+".ogg"
				Else
					pathtomusic$=Path$+"/Media/hero"+no$+".ogg"
				EndIf
				
				StageMusic[i] = LoadSound(pathtomusic$)
				
			ElseIf i=0 Then
				Select Menu\Stage
					Case 999:
						StageMusic[i] = LoadSound(Path$+"/Media/chao"+Rand(1,6)+".ogg")
					Case 998:
						StageMusic[i] = LoadSound("Sounds/ChaoRace.ogg")
					Case 997:
						StageMusic[i] = LoadSound("Sounds/ChaoKarate.ogg")
				End Select
			EndIf
		Next
	EndIf
End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function LoadSmartSound(x)
	
	Select x
		Case Sound_ATM:		LoadGoodSound(x,3,"Sounds/ATM.ogg")
		Case Sound_TitleCardRuby:			LoadGoodSound(x,1,"Sounds/RubySwirl.ogg")
		Case Sound_Prompt:		LoadGoodSound(x,3,"Sounds/Prompt.ogg")
		Case Sound_Super:		LoadGoodSound(x,3,"Sounds/Super.ogg")
		Case Sound_BoostFinish:		LoadGoodSound(x,3,"Sounds/BoostFinish.ogg")
		Case Sound_SoarUp:		LoadGoodSound(x,3,"Sounds/SoarUp.ogg")
		Case Sound_SoarDown:		LoadGoodSound(x,3,"Sounds/SoarDown.ogg")
		Case Sound_DiamondBig:		LoadGoodSound(x,3,"Sounds/DiamondBig.ogg")
		Case Sound_DiamondBlue1:		LoadGoodSound(x,3,"Sounds/DiamondBlue1.ogg")
		Case Sound_DiamondBlue2:		LoadGoodSound(x,3,"Sounds/DiamondBlue2.ogg")
		Case Sound_DiamondRed1:		LoadGoodSound(x,3,"Sounds/DiamondRed1.ogg")
		Case Sound_DiamondRed2:		LoadGoodSound(x,3,"Sounds/DiamondRed2.ogg")
		Case Sound_DiamondGreen1:		LoadGoodSound(x,3,"Sounds/DiamondGreen1.ogg")
		Case Sound_DiamondGreen2:		LoadGoodSound(x,3,"Sounds/DiamondGreen2.ogg")
		Case Sound_Kunai:		LoadGoodSound(x,3,"Sounds/Kunai.ogg")
		Case Sound_Quickstep:		LoadGoodSound(x,3,"Sounds/Quickstep.ogg")
		Case Sound_AmbientAlarm:		LoadGoodSound(x,1,"Sounds/AmbientAlarm.ogg",4)
		Case Sound_MenuAccept:			LoadGoodSound(x,1,"Sounds/MenuAccept.ogg")
		Case Sound_MenuBegin:			LoadGoodSound(x,1,"Sounds/MenuBegin.ogg")
		Case Sound_Achievement:			LoadGoodSound(x,1,"Sounds/Achievement.ogg")
		Case Sound_MenuBack:			LoadGoodSound(x,1,"Sounds/MenuBack.ogg")
		Case Sound_MenuMove:			LoadGoodSound(x,1,"Sounds/MenuMove.ogg")
		Case Sound_MenuPause:			LoadGoodSound(x,1,"Sounds/MenuPause.ogg")
		Case Sound_MenuRefuse:			LoadGoodSound(x,1,"Sounds/MenuRefuse.ogg")
		Case Sound_1Up:				LoadGoodSound(x,1,"Sounds/1Up.ogg")
		Case Sound_CharacterChange:		LoadGoodSound(x,3,"Sounds/CharacterChange.ogg")
		Case Sound_DebugOnOff:			LoadGoodSound(x,1,"Sounds/DebugOnOff.ogg")
		Case Sound_TitleCard:			LoadGoodSound(x,1,"Sounds/TitleCard.ogg")
		Case Sound_Emblem:			LoadGoodSound(x,1,"Sounds/Emblem.ogg")
		Case Sound_GameOver:			LoadGoodSound(x,1,"Sounds/GameOver.ogg",3)
		Case Sound_Unlock:			LoadGoodSound(x,1,"Sounds/Unlock.ogg")
		Case Sound_AmbientBeach:		LoadGoodSound(x,1,"Sounds/AmbientBeach.ogg",4)
		Case Sound_AmbientForest:		LoadGoodSound(x,1,"Sounds/AmbientForest.ogg",4)
		Case Sound_AmbientRain:			LoadGoodSound(x,1,"Sounds/AmbientRain.ogg",4)
		Case Sound_AmbientSnow:			LoadGoodSound(x,1,"Sounds/AmbientSnow.ogg",4)
		Case Sound_AmbientVoid:			LoadGoodSound(x,1,"Sounds/AmbientVoid.ogg",4)
		Case Sound_AmbientWind:			LoadGoodSound(x,1,"Sounds/AmbientWind.ogg",4)
		Case Sound_Rank:			LoadGoodSound(x,1,"Sounds/Rank.ogg")
		Case Sound_RankS:			LoadGoodSound(x,1,"Sounds/RankS.ogg")
		Case Sound_PunchShadow1:			LoadGoodSound(x,1,"Sounds/PunchShadow1.ogg")
		Case Sound_PunchShadow2:			LoadGoodSound(x,1,"Sounds/PunchShadow2.ogg")
		Case Sound_Dodge:			LoadGoodSound(x,1,"Sounds/Dodge.ogg")	
		Case Sound_RankScore:			LoadGoodSound(x,1,"Sounds/RankScore.ogg")
		Case Sound_ResultCount:			LoadGoodSound(x,1,"Sounds/ResultCount.ogg")
		Case Sound_ResultCounting:		LoadGoodSound(x,1,"Sounds/ResultCounting.ogg")
		Case Sound_Result:			LoadGoodSound(x,1,"Sounds/Result.ogg")
		Case Sound_ResultS:			LoadGoodSound(x,1,"Sounds/ResultS.ogg")
		Case Sound_ResultChao:			LoadGoodSound(x,1,"Sounds/ResultChao.ogg")
		Case Sound_RankScore:			LoadGoodSound(x,1,"Sounds/RankScore.ogg")
		Case Sound_Aim:				LoadGoodSound(x,3,"Sounds/Aim.ogg")
		Case Sound_Balloon:			LoadGoodSound(x,3,"Sounds/Balloon.ogg")
		Case Sound_BoostStart:		LoadGoodSound(x,1,"Sounds/BoostStart.ogg")
		Case Sound_BoostWind:		LoadGoodSound(x,1,"Sounds/BoostWind.ogg")
		Case Sound_BoostCharge:		LoadGoodSound(x,1,"Sounds/BoostCharge.ogg")
		Case Sound_MetalCharge:		LoadGoodSound(x,1,"Sounds/MetalCharge.ogg")
		Case Sound_MetalRelease:		LoadGoodSound(x,1,"Sounds/MetalRelease.ogg")
		Case Sound_BatBomb:			LoadGoodSound(x,3,"Sounds/BatBomb.ogg")
		Case Sound_Beam:			LoadGoodSound(x,3,"Sounds/Beam.ogg")
		Case Sound_Spark1:			LoadGoodSound(x,3,"Sounds/Spark1.ogg")
		Case Sound_Spark2:			LoadGoodSound(x,3,"Sounds/Spark2.ogg")
		Case Sound_Spark3:			LoadGoodSound(x,3,"Sounds/Spark3.ogg")
		Case Sound_BoardGrind:			LoadGoodSound(x,3,"Sounds/BoardGrind.ogg")
		Case Sound_BoardJump:			LoadGoodSound(x,3,"Sounds/BoardJump.ogg")
		Case Sound_BoardLand:			LoadGoodSound(x,3,"Sounds/BoardLand.ogg")
		Case Sound_Bell:			LoadGoodSound(x,3,"Sounds/Bell.ogg")
		Case Sound_Blade:			LoadGoodSound(x,3,"Sounds/Blade.ogg")
		Case Sound_Board:			LoadGoodSound(x,3,"Sounds/Board.ogg")
		Case Sound_Bombed:			LoadGoodSound(x,3,"Sounds/Bombed.ogg")
		Case Sound_Boomerang:			LoadGoodSound(x,3,"Sounds/Boomerang.ogg")
		Case Sound_Gatling:			LoadGoodSound(x,3,"Sounds/Gatling.ogg")
		Case Sound_Charged:			LoadGoodSound(x,3,"Sounds/Charged.ogg")
		Case Sound_Uppercut:			LoadGoodSound(x,3,"Sounds/Uppercut.ogg")
		Case Sound_Lightattack:			LoadGoodSound(x,3,"Sounds/Lightattack.ogg")
		Case Sound_Bounce:			LoadGoodSound(x,3,"Sounds/Bounce.ogg")
		Case Sound_Bounce2:			LoadGoodSound(x,3,"Sounds/Bounce2.ogg")
		Case Sound_Boxdestroy:			LoadGoodSound(x,3,"Sounds/Boxdestroy.ogg")
		Case Sound_BoxdestroyWood1:			LoadGoodSound(x,3,"Sounds/BoxDestroyWood1.ogg")
		Case Sound_BoxdestroyWood2:			LoadGoodSound(x,3,"Sounds/BoxDestroyWood2.ogg")
		Case Sound_BoxdestroyWood3:			LoadGoodSound(x,3,"Sounds/BoxDestroyWood3.ogg")
		Case Sound_BoxdestroyMetal:			LoadGoodSound(x,3,"Sounds/BoxDestroyMetal.ogg")
		Case Sound_Boxirondestroy:		LoadGoodSound(x,3,"Sounds/Boxirondestroy.ogg")
		Case Sound_Break:			LoadGoodSound(x,3,"Sounds/Break.ogg")
		Case Sound_BreakSpin:			LoadGoodSound(x,3,"Sounds/BreakSpin.ogg")
		Case Sound_Breath:			LoadGoodSound(x,3,"Sounds/Breath.ogg")
		Case Sound_BreathCount:			LoadGoodSound(x,1,"Sounds/BreathCount.ogg")
		Case Sound_BubbleBeam:			LoadGoodSound(x,3,"Sounds/BubbleBeam.ogg")
		Case Sound_Bumper1:			LoadGoodSound(x,3,"Sounds/Bumper1.ogg")
		Case Sound_Bumper2:			LoadGoodSound(x,3,"Sounds/Bumper2.ogg")
		Case Sound_Bumper3:			LoadGoodSound(x,3,"Sounds/Bumper3.ogg")
		Case Sound_Bumper4:			LoadGoodSound(x,3,"Sounds/Bumper4.ogg")
		Case Sound_Cannon:			LoadGoodSound(x,3,"Sounds/Cannon.ogg")
		Case Sound_Car1:			LoadGoodSound(x,3,"Sounds/Car1.ogg")
		Case Sound_Car2:			LoadGoodSound(x,3,"Sounds/Car2.ogg")
		Case Sound_Car3:			LoadGoodSound(x,3,"Sounds/Car3.ogg")
		Case Sound_Car4:			LoadGoodSound(x,3,"Sounds/Car4.ogg")
		Case Sound_Chao:			LoadGoodSound(x,3,"Sounds/Chao.ogg")
		Case Sound_ChaosControl:		LoadGoodSound(x,3,"Sounds/ChaosControl.ogg")
		Case Sound_ChaosDrive:			LoadGoodSound(x,3,"Sounds/ChaosDrive.ogg")
		Case Sound_Check:			LoadGoodSound(x,3,"Sounds/Check.ogg")
		Case Sound_Climb:			LoadGoodSound(x,3,"Sounds/Climb.ogg")
		Case Sound_Cloud:			LoadGoodSound(x,3,"Sounds/Cloud.ogg")
		Case Sound_Collectible:			LoadGoodSound(x,1,"Sounds/Collectible.ogg")
		Case Sound_Counter:				LoadGoodSound(x,3,"Sounds/Counter.ogg")
		Case Sound_MoonCollect:			LoadGoodSound(x,3,"Sounds/MoonCollect.ogg")
		Case Sound_MoonAll:			LoadGoodSound(x,3,"Sounds/MoonAll.ogg")	
		Case Sound_MoonFail:			LoadGoodSound(x,3,"Sounds/MoonFail.ogg")	
		Case Sound_MoonTimer:			LoadGoodSound(x,3,"Sounds/MoonTimer.ogg")		
		Case Sound_Counter1:			LoadGoodSound(x,3,"Sounds/Counter1.ogg")
		Case Sound_Counter2:			LoadGoodSound(x,3,"Sounds/Counter2.ogg")
		Case Sound_Counter3:			LoadGoodSound(x,3,"Sounds/Counter3.ogg")
		Case Sound_Counter4:			LoadGoodSound(x,3,"Sounds/Counter4.ogg")
		Case Sound_Counter5:			LoadGoodSound(x,3,"Sounds/Counter5.ogg")
		Case Sound_CounterWrong:		LoadGoodSound(x,3,"Sounds/CounterWrong.ogg")
		Case Sound_Crumble:			LoadGoodSound(x,3,"Sounds/Crumble.ogg")
		Case Sound_Crusher:			LoadGoodSound(x,3,"Sounds/Crusher.ogg")
		Case Sound_Curse:			LoadGoodSound(x,3,"Sounds/Curse.ogg")
		Case Sound_Dart:			LoadGoodSound(x,3,"Sounds/Dart.ogg")
		Case Sound_Dash:			LoadGoodSound(x,3,"Sounds/Dash.ogg")
		Case Sound_DashElectro:			LoadGoodSound(x,3,"Sounds/DashElectro.ogg")
		Case Sound_DashPad:			LoadGoodSound(x,3,"Sounds/DashPad.ogg")
		Case Sound_DashRamp:			LoadGoodSound(x,3,"Sounds/DashRamp.ogg")
		Case Sound_Deflect:			LoadGoodSound(x,3,"Sounds/Deflect.ogg")
		Case Sound_Die:				LoadGoodSound(x,3,"Sounds/Die.ogg")
		Case Sound_Dive:			LoadGoodSound(x,3,"Sounds/Dive.ogg")
		Case Sound_Drift:			LoadGoodSound(x,3,"Sounds/Drift.ogg")
		Case Sound_Drop:			LoadGoodSound(x,3,"Sounds/Drop.ogg")
		Case Sound_DropDash:			LoadGoodSound(x,3,"Sounds/DropDash.ogg")
		Case Sound_Flamethrower:			LoadGoodSound(x,3,"Sounds/Flamethrower.ogg")
		Case Sound_Drown:			LoadGoodSound(x,1,"Sounds/Drown.ogg")
		Case Sound_Drowned:			LoadGoodSound(x,3,"Sounds/Drowned.ogg")
		Case Sound_DoubleJump:			LoadGoodSound(x,3,"Sounds/DoubleJump.ogg")
		Case Sound_EggmanHurt:			LoadGoodSound(x,3,"Sounds/EggmanHurt.ogg")
		Case Sound_EggmanShield:		LoadGoodSound(x,3,"Sounds/EggmanShield.ogg")
		Case Sound_EggmanShot:			LoadGoodSound(x,3,"Sounds/EggmanShot.ogg")
		Case Sound_Elevator:			LoadGoodSound(x,3,"Sounds/Elevator.ogg")
		Case Sound_ElevatorCharge:		LoadGoodSound(x,3,"Sounds/ElevatorCharge.ogg")
		Case Sound_Emerald:			LoadGoodSound(x,3,"Sounds/Emerald.ogg")
		Case Sound_EnemyBite:			LoadGoodSound(x,3,"Sounds/EnemyBite.ogg")
		Case Sound_EnemyBuzz:			LoadGoodSound(x,3,"Sounds/EnemyBuzz.ogg")
		Case Sound_EnemyCannon:			LoadGoodSound(x,3,"Sounds/EnemyCannon.ogg")
		Case Sound_EnemyElectric:		LoadGoodSound(x,3,"Sounds/EnemyElectric.ogg")
		Case Sound_EnemyFly:			LoadGoodSound(x,3,"Sounds/EnemyFly.ogg")
		Case Sound_EnemyHammer:			LoadGoodSound(x,3,"Sounds/EnemyHammer.ogg")
		Case Sound_EnemyHello:			LoadGoodSound(x,3,"Sounds/EnemyHello.ogg")
		Case Sound_EnemyHit:			LoadGoodSound(x,3,"Sounds/EnemyHit.ogg")
		Case Sound_EnemyHover:			LoadGoodSound(x,3,"Sounds/EnemyHover.ogg")
		Case Sound_EnemyJump:			LoadGoodSound(x,3,"Sounds/EnemyJump.ogg")
		Case Sound_EnemyLaugh:			LoadGoodSound(x,3,"Sounds/EnemyLaugh.ogg")
		Case Sound_EnemyMagic:			LoadGoodSound(x,3,"Sounds/EnemyMagic.ogg")
		Case Sound_EnemyMotor:			LoadGoodSound(x,3,"Sounds/EnemyMotor.ogg")
		Case Sound_EnemyMotor2:			LoadGoodSound(x,3,"Sounds/EnemyMotor2.ogg")
		Case Sound_EnemyPing:			LoadGoodSound(x,3,"Sounds/EnemyPing.ogg")
		Case Sound_EnemySearch:			LoadGoodSound(x,3,"Sounds/EnemySearch.ogg")
		Case Sound_EnemySeen:			LoadGoodSound(x,3,"Sounds/EnemySeen.ogg")
		Case Sound_EnemyShot:			LoadGoodSound(x,3,"Sounds/EnemyShot.ogg")
		Case Sound_EnemyShot2:			LoadGoodSound(x,3,"Sounds/EnemyShot2.ogg")
		Case Sound_EnemyShot3:			LoadGoodSound(x,3,"Sounds/EnemyShot3.ogg")
		Case Sound_EnemyShot4:			LoadGoodSound(x,3,"Sounds/EnemyShot4.ogg")
		Case Sound_EnemyShotBlow:		LoadGoodSound(x,3,"Sounds/EnemyShotBlow.ogg")
		Case Sound_EnemyShotPoof:		LoadGoodSound(x,3,"Sounds/EnemyShotPoof.ogg")
		Case Sound_EnemyStun:			LoadGoodSound(x,3,"Sounds/EnemyStun.ogg")
		Case Sound_EnemySwim:			LoadGoodSound(x,3,"Sounds/EnemySwim.ogg")
		Case Sound_EnemyThrow:			LoadGoodSound(x,3,"Sounds/EnemyThrow.ogg")
		Case Sound_EnemyTraffic:		LoadGoodSound(x,3,"Sounds/EnemyTraffic.ogg")
		Case Sound_Explode:			LoadGoodSound(x,3,"Sounds/Explode.ogg")
		Case Sound_Fan:				LoadGoodSound(x,3,"Sounds/Fan.ogg")
		Case Sound_Fire:			LoadGoodSound(x,3,"Sounds/Fire.ogg")
		Case Sound_FireDash:			LoadGoodSound(x,3,"Sounds/FireDash.ogg")
		Case Sound_Flicky:			LoadGoodSound(x,3,"Sounds/Flicky.ogg")
		Case Sound_FlickyGet:			LoadGoodSound(x,3,"Sounds/FlickyGet.ogg")
		Case Sound_Flower:			LoadGoodSound(x,3,"Sounds/Flower.ogg")
		Case Sound_Flower1:			LoadGoodSound(x,3,"Sounds/Flower1.ogg")
		Case Sound_Flower2:			LoadGoodSound(x,3,"Sounds/Flower2.ogg")
		Case Sound_Flutter:			LoadGoodSound(x,3,"Sounds/Flutter.ogg")
		Case Sound_FlyBuzz:			LoadGoodSound(x,3,"Sounds/FlyBuzz.ogg")
		Case Sound_FlyEars:			LoadGoodSound(x,3,"Sounds/FlyEars.ogg")
		Case Sound_FlyStart:			LoadGoodSound(x,3,"Sounds/FlyStart.ogg")
		Case Sound_FlyTails:			LoadGoodSound(x,3,"Sounds/FlyTails.ogg")
		Case Sound_FlyTailsDoll:		LoadGoodSound(x,3,"Sounds/FlyTailsDoll.ogg")
		Case Sound_FlyWings:			LoadGoodSound(x,3,"Sounds/FlyWings.ogg")
		Case Sound_Frog:			LoadGoodSound(x,3,"Sounds/Frog.ogg")
		Case Sound_Ghost:			LoadGoodSound(x,3,"Sounds/Ghost.ogg")
		Case Sound_GhostGrab:		LoadGoodSound(x,3,"Sounds/GhostGrab.ogg")
		Case Sound_GhostVanish:		LoadGoodSound(x,3,"Sounds/GhostVanish.ogg")
		Case Sound_Glide:			LoadGoodSound(x,3,"Sounds/Glide.ogg")
		Case Sound_GlideStart:			LoadGoodSound(x,3,"Sounds/GlideStart.ogg")
		Case Sound_GlideStart2:			LoadGoodSound(x,3,"Sounds/GlideStart2.ogg")
		Case Sound_GlideStart3:			LoadGoodSound(x,3,"Sounds/GlideStart3.ogg")
		Case Sound_Goal:			LoadGoodSound(x,3,"Sounds/Goal.ogg")
		Case Sound_GoalIdle:			LoadGoodSound(x,3,"Sounds/GoalIdle.ogg")
		Case Sound_Grab:			LoadGoodSound(x,3,"Sounds/Grab.ogg")
		Case Sound_Grabber:			LoadGoodSound(x,3,"Sounds/Grabber.ogg")
		Case Sound_Grind:			LoadGoodSound(x,3,"Sounds/Grind.ogg")
		Case Sound_GrindStart:			LoadGoodSound(x,3,"Sounds/GrindStart.ogg")
		Case Sound_GroundFlyEars:		LoadGoodSound(x,3,"Sounds/GroundFlyEars.ogg")
		Case Sound_GroundFlyTails:		LoadGoodSound(x,3,"Sounds/GroundFlyTails.ogg")
		Case Sound_GroundFlyTailsDoll:		LoadGoodSound(x,3,"Sounds/GroundFlyTailsDoll.ogg")
		Case Sound_GroundHover:			LoadGoodSound(x,3,"Sounds/GroundHover.ogg")
		Case Sound_GroundLand:			LoadGoodSound(x,3,"Sounds/Footstep/GroundLandStone.ogg")
		Case Sound_GroundLandWood:			LoadGoodSound(x,3,"Sounds/Footstep/GroundLandWood.ogg")
		Case Sound_GroundLandGrass:			LoadGoodSound(x,3,"Sounds/Footstep/GroundLandGrass.ogg")
		Case Sound_GroundLandDirt:			LoadGoodSound(x,3,"Sounds/Footstep/GroundLandDirt.ogg")
		Case Sound_GroundLandMetal:		LoadGoodSound(x,3,"Sounds/Footstep/GroundLandMetal.ogg")
		Case Sound_GroundLandBoard:		LoadGoodSound(x,3,"Sounds/GroundLandBoard.ogg")
		Case Sound_GroundLandDoll:		LoadGoodSound(x,3,"Sounds/GroundLandDoll.ogg")
		Case Sound_GroundLandWater:		LoadGoodSound(x,3,"Sounds/GroundLandWater.ogg")
		Case Sound_GroundShadowStep1:		LoadGoodSound(x,3,"Sounds/GroundShadowStep1.ogg")
		Case Sound_GroundShadowStep2:		LoadGoodSound(x,3,"Sounds/GroundShadowStep2.ogg")
		Case Sound_GroundSkid:			LoadGoodSound(x,3,"Sounds/GroundSkid.ogg")
		Case Sound_GroundSkidWater:		LoadGoodSound(x,3,"Sounds/GroundSkidWater.ogg")
		Case Sound_GroundStepPawn:			LoadGoodSound(x,3,"Sounds/GroundStepPawn.ogg")
		Case Sound_GroundStep1:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep1Stone.ogg")
		Case Sound_GroundStep2:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep2Stone.ogg")
		Case Sound_GroundStep3:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep3Stone.ogg")
		Case Sound_GroundStep4:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep4Stone.ogg")
		Case Sound_GroundStep5:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep5Stone.ogg")
		Case Sound_GroundStep1Dirt:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep1Dirt.ogg")
		Case Sound_GroundStep2Dirt:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep2Dirt.ogg")
		Case Sound_GroundStep3Dirt:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep3Dirt.ogg")
		Case Sound_GroundStep4Dirt:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep4Dirt.ogg")
		Case Sound_GroundStep5Dirt:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep5Dirt.ogg")
		Case Sound_GroundStep1Grass:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep1Grass.ogg")
		Case Sound_GroundStep2Grass:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep2Grass.ogg")
		Case Sound_GroundStep3Grass:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep3Grass.ogg")
		Case Sound_GroundStep4Grass:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep4Grass.ogg")
		Case Sound_GroundStep5Grass:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep5Grass.ogg")
		Case Sound_GroundStep1Wood:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep1Wood.ogg")
		Case Sound_GroundStep2Wood:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep2Wood.ogg")
		Case Sound_GroundStep3Wood:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep3Wood.ogg")
		Case Sound_GroundStep4Wood:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep4Wood.ogg")
		Case Sound_GroundStep5Wood:			LoadGoodSound(x,3,"Sounds/Footstep/GroundStep5Wood.ogg")
		Case Sound_GroundStep1Metal:		LoadGoodSound(x,3,"Sounds/Footstep/GroundStep1Metal.ogg")
		Case Sound_GroundStep2Metal:		LoadGoodSound(x,3,"Sounds/Footstep/GroundStep2Metal.ogg")
		Case Sound_GroundStep3Metal:		LoadGoodSound(x,3,"Sounds/Footstep/GroundStep3Metal.ogg")
		Case Sound_GroundStep4Metal:		LoadGoodSound(x,3,"Sounds/Footstep/GroundStep4Metal.ogg")
		Case Sound_GroundStep5Metal:		LoadGoodSound(x,3,"Sounds/Footstep/GroundStep5Metal.ogg")
		Case Sound_GroundStep1Doll:		LoadGoodSound(x,3,"Sounds/GroundStep1Doll.ogg")
		Case Sound_GroundStep2Doll:		LoadGoodSound(x,3,"Sounds/GroundStep2Doll.ogg")
		Case Sound_GroundStep3Doll:		LoadGoodSound(x,3,"Sounds/GroundStep3Doll.ogg")
		Case Sound_GroundStep4Doll:		LoadGoodSound(x,3,"Sounds/GroundStep4Doll.ogg")
		Case Sound_GroundStep5Doll:		LoadGoodSound(x,3,"Sounds/GroundStep5Doll.ogg")
		Case Sound_GroundStep1Water:		LoadGoodSound(x,3,"Sounds/GroundStep1Water.ogg")
		Case Sound_GroundStep2Water:		LoadGoodSound(x,3,"Sounds/GroundStep2Water.ogg")
		Case Sound_GroundStep3Water:		LoadGoodSound(x,3,"Sounds/GroundStep3Water.ogg")
		Case Sound_GroundStep4Water:		LoadGoodSound(x,3,"Sounds/GroundStep4Water.ogg")
		Case Sound_GroundStep5Water:		LoadGoodSound(x,3,"Sounds/GroundStep5Water.ogg")
		Case Sound_GroundStep1Soldier:			LoadGoodSound(x,3,"Sounds/GroundStep1Soldier.ogg")
		Case Sound_GroundStep2Soldier:			LoadGoodSound(x,3,"Sounds/GroundStep2Soldier.ogg")
		Case Sound_GroundStep3Soldier:			LoadGoodSound(x,3,"Sounds/GroundStep3Soldier.ogg")
		Case Sound_GroundStep4Soldier:			LoadGoodSound(x,3,"Sounds/GroundStep4Soldier.ogg")
		Case Sound_GroundStep5Soldier:			LoadGoodSound(x,3,"Sounds/GroundStep5Soldier.ogg")
		Case Sound_GroundStep6Soldier:			LoadGoodSound(x,3,"Sounds/GroundStep6Soldier.ogg")
		Case Sound_GoSuper:			LoadGoodSound(x,3,"Sounds/GoSuper.ogg")
		Case Sound_Gum:				LoadGoodSound(x,3,"Sounds/Gum.ogg")
		Case Sound_Hammer:			LoadGoodSound(x,3,"Sounds/Hammer.ogg")
		Case Sound_Helicopter:			LoadGoodSound(x,3,"Sounds/Helicopter.ogg")
		Case Sound_Hint:			LoadGoodSound(x,3,"Sounds/Hint.ogg")
		Case Sound_HomingAttack:		LoadGoodSound(x,3,"Sounds/HomingAttack.ogg")
		Case Sound_HomingAttackDeep:		LoadGoodSound(x,3,"Sounds/HomingAttackDeep.ogg")
		Case Sound_HoopDash:			LoadGoodSound(x,3,"Sounds/HoopDash.ogg")
		Case Sound_HoopRainbow:			LoadGoodSound(x,3,"Sounds/HoopRainbow.ogg")
		Case Sound_Hover:			LoadGoodSound(x,3,"Sounds/Hover.ogg")
		Case Sound_Hurricane:			LoadGoodSound(x,3,"Sounds/Hurricane.ogg")
		Case Sound_Invincible:			LoadGoodSound(x,1,"Sounds/Invincible.ogg")
		Case Sound_Invisible:			LoadGoodSound(x,3,"Sounds/Invisible.ogg")
		Case Sound_JumpPanelExit:		LoadGoodSound(x,3,"Sounds/JumpPanelExit.ogg")
		Case Sound_JumpPanelLand:		LoadGoodSound(x,3,"Sounds/JumpPanelLand.ogg")
		Case Sound_KnuxStomp:			LoadGoodSound(x,3,"Sounds/KnuxStomp.ogg")
		Case Sound_LaserCharging:		LoadGoodSound(x,3,"Sounds/LaserCharging.ogg")
		Case Sound_Lap:				LoadGoodSound(x,1,"Sounds/Lap.ogg")
		Case Sound_LapLast:				LoadGoodSound(x,1,"Sounds/LapLast.ogg")
		Case Sound_Levitate:			LoadGoodSound(x,3,"Sounds/Levitate.ogg")
		Case Sound_Levitate2:			LoadGoodSound(x,3,"Sounds/Levitate2.ogg")
		Case Sound_LevitateStart:		LoadGoodSound(x,3,"Sounds/LevitateStart.ogg")
		Case Sound_Minion:			LoadGoodSound(x,3,"Sounds/Minion.ogg")
		Case Sound_MissionCompleted:		LoadGoodSound(x,1,"Sounds/MissionCompleted.ogg")
		Case Sound_Monitor:			LoadGoodSound(x,3,"Sounds/Monitor.ogg")
		Case Sound_MonitorBalloon:		LoadGoodSound(x,3,"Sounds/MonitorBalloon.ogg")
		Case Sound_MonitorShield:		LoadGoodSound(x,3,"Sounds/MonitorShield.ogg")
		Case Sound_MonitorTrap:			LoadGoodSound(x,3,"Sounds/MonitorTrap.ogg")
		Case Sound_MonsterBite:				LoadGoodSound(x,3,"Sounds/MonsterBite.ogg")
		Case Sound_MonsterDamage:			LoadGoodSound(x,3,"Sounds/MonsterDamage.ogg")
		Case Sound_MonsterDead:			LoadGoodSound(x,3,"Sounds/MonsterDead.ogg")
		Case Sound_MonsterDeadAlien:	LoadGoodSound(x,3,"Sounds/MonsterDeadAlien.ogg")
		Case Sound_MonsterFind:			LoadGoodSound(x,3,"Sounds/MonsterFind.ogg")
		Case Sound_MonsterFlap:			LoadGoodSound(x,3,"Sounds/MonsterFlap.ogg")
		Case Sound_MonsterRoar1:			LoadGoodSound(x,3,"Sounds/MonsterRoar1.ogg")
		Case Sound_MonsterRoar2:			LoadGoodSound(x,3,"Sounds/MonsterRoar2.ogg")
		Case Sound_Ninja:			LoadGoodSound(x,3,"Sounds/Ninja.ogg")
		Case Sound_OmochaoFly:			LoadGoodSound(x,3,"Sounds/OmochaoFly.ogg")
		Case Sound_Paddle:			LoadGoodSound(x,3,"Sounds/Paddle.ogg")
		Case Sound_Paddle2:			LoadGoodSound(x,3,"Sounds/Paddle2.ogg")
		Case Sound_Parachute:			LoadGoodSound(x,3,"Sounds/Parachute.ogg")
		Case Sound_PikoHammer:			LoadGoodSound(x,3,"Sounds/PikoHammer.ogg")
		Case Sound_Pinball:			LoadGoodSound(x,3,"Sounds/Pinball.ogg")
		Case Sound_PlaneChange:			LoadGoodSound(x,3,"Sounds/PlaneChange.ogg")
		Case Sound_PlaneFlight:			LoadGoodSound(x,3,"Sounds/PlaneFlight.ogg")
		Case Sound_PlaneShoot:			LoadGoodSound(x,3,"Sounds/PlaneShoot.ogg")
		Case Sound_Pole:			LoadGoodSound(x,3,"Sounds/Pole.ogg")
		Case Sound_Propel:			LoadGoodSound(x,3,"Sounds/Propel.ogg")
		Case Sound_Propeller:			LoadGoodSound(x,3,"Sounds/Propeller.ogg")
		Case Sound_PsychoDash:			LoadGoodSound(x,3,"Sounds/PsychoDash.ogg")
		Case Sound_PsychoHold:			LoadGoodSound(x,3,"Sounds/PsychoHold.ogg")
		Case Sound_Psychokinesis:		LoadGoodSound(x,3,"Sounds/Psychokinesis.ogg")
		Case Sound_PsychoRelease:		LoadGoodSound(x,3,"Sounds/PsychoRelease.ogg")
		Case Sound_PsychoThrow:			LoadGoodSound(x,3,"Sounds/PsychoThrow.ogg")
		Case Sound_Punch:			LoadGoodSound(x,3,"Sounds/Punch.ogg")
		Case Sound_PunchBig:			LoadGoodSound(x,3,"Sounds/PunchBig.ogg")
		Case Sound_PunchSmall:			LoadGoodSound(x,3,"Sounds/PunchSmall.ogg")
		Case Sound_RedRings:			LoadGoodSound(x,3,"Sounds/RedRings.ogg")
		Case Sound_Ring:			LoadGoodSound(x,3,"Sounds/Ring.ogg")
		Case Sound_RingRed:			LoadGoodSound(x,3,"Sounds/RingRed.ogg")
		Case Sound_RingBig:			LoadGoodSound(x,3,"Sounds/RingBig.ogg")
		Case Sound_RingSuper:			LoadGoodSound(x,3,"Sounds/RingSuper.ogg")
		Case Sound_RingSparkle1:			LoadGoodSound(x,3,"Sounds/RingSparkle1.ogg")
		Case Sound_RingSparkle2:			LoadGoodSound(x,3,"Sounds/RingSparkle2.ogg")
		Case Sound_RingSparkle3:			LoadGoodSound(x,3,"Sounds/RingSparkle3.ogg")
		Case Sound_RingSparkle4:			LoadGoodSound(x,3,"Sounds/RingSparkle3.ogg")
		Case Sound_RingLoss:			LoadGoodSound(x,3,"Sounds/RingLoss.ogg")
		Case Sound_Robot1:			LoadGoodSound(x,3,"Sounds/Robot1.ogg")
		Case Sound_Robot2:			LoadGoodSound(x,3,"Sounds/Robot2.ogg")
		Case Sound_Robot3:			LoadGoodSound(x,3,"Sounds/Robot3.ogg")
		Case Sound_RobotPoof:		LoadGoodSound(x,3,"Sounds/RobotPoof.ogg")
		Case Sound_Rocket:			LoadGoodSound(x,3,"Sounds/Rocket.ogg")
		Case Sound_RocketGo:			LoadGoodSound(x,3,"Sounds/RocketGo.ogg")
		Case Sound_Ruby1:			LoadGoodSound(x,3,"Sounds/Ruby1.ogg")
		Case Sound_Ruby2:			LoadGoodSound(x,3,"Sounds/Ruby2.ogg")
		Case Sound_Ruby3:			LoadGoodSound(x,3,"Sounds/Ruby3.ogg")
		Case Sound_Ruby4:			LoadGoodSound(x,3,"Sounds/Ruby4.ogg")
		Case Sound_Ruby5:			LoadGoodSound(x,3,"Sounds/Ruby5.ogg")
		Case Sound_Ruby6:			LoadGoodSound(x,3,"Sounds/Ruby6.ogg")
		Case Sound_RubyIdle:			LoadGoodSound(x,3,"Sounds/RubyIdle.ogg")
		Case Sound_RubySwirl:		LoadGoodSound(x,3,"Sounds/RubySwirl.ogg")
		Case Sound_Sack:			LoadGoodSound(x,3,"Sounds/Sack.ogg")
		Case Sound_Seagull:			LoadGoodSound(x,3,"Sounds/Seagull.ogg")
		Case Sound_Sheep1:			LoadGoodSound(x,3,"Sounds/Sheep1.ogg")
		Case Sound_Sheep2:			LoadGoodSound(x,3,"Sounds/Sheep2.ogg")
		Case Sound_Shield:			LoadGoodSound(x,3,"Sounds/Shield.ogg")
		Case Sound_ShieldEarth:			LoadGoodSound(x,3,"Sounds/ShieldEarth.ogg")
		Case Sound_ShieldBubble:			LoadGoodSound(x,3,"Sounds/ShieldBubble.ogg")
		Case Sound_ShieldFire:			LoadGoodSound(x,3,"Sounds/ShieldFire.ogg")
		Case Sound_ShieldThunder:			LoadGoodSound(x,3,"Sounds/ShieldThunder.ogg")
		Case Sound_Shotgun1:			LoadGoodSound(x,3,"Sounds/Shotgun1.ogg")
		Case Sound_Shotgun2:			LoadGoodSound(x,3,"Sounds/Shotgun2.ogg")
		Case Sound_Skydive:			LoadGoodSound(x,3,"Sounds/Skydive.ogg")
		Case Sound_Slap:			LoadGoodSound(x,3,"Sounds/Slap.ogg")
		Case Sound_SoldierHurt1:		LoadGoodSound(x,3,"Sounds/SoldierHurt1.ogg")
		Case Sound_SoldierHurt2:		LoadGoodSound(x,3,"Sounds/SoldierHurt2.ogg")
		Case Sound_SoldierHurt3:		LoadGoodSound(x,3,"Sounds/SoldierHurt3.ogg")
		Case Sound_SoldierLook1:		LoadGoodSound(x,3,"Sounds/SoldierLook1.ogg")
		Case Sound_SoldierLook2:		LoadGoodSound(x,3,"Sounds/SoldierLook2.ogg")
		Case Sound_SoldierLook3:		LoadGoodSound(x,3,"Sounds/SoldierLook3.ogg")
		Case Sound_SoldierCharge1:		LoadGoodSound(x,3,"Sounds/SoldierCharge1.ogg")
		Case Sound_SoldierCharge2:		LoadGoodSound(x,3,"Sounds/SoldierCharge2.ogg")
		Case Sound_SoldierCharge3:		LoadGoodSound(x,3,"Sounds/SoldierCharge3.ogg")
		Case Sound_SpearImpact:			LoadGoodSound(x,3,"Sounds/SpearImpact.ogg")
		Case Sound_SpearShoot:			LoadGoodSound(x,3,"Sounds/SpearShoot.ogg")
		Case Sound_SpeedShoes:			LoadGoodSound(x,1,"Sounds/SpeedShoes.ogg")
		Case Sound_SpikeDrill1:			LoadGoodSound(x,3,"Sounds/SpikeDrill1.ogg")
		Case Sound_SpikeDrill2:			LoadGoodSound(x,3,"Sounds/SpikeDrill2.ogg")
		Case Sound_Spikes:			LoadGoodSound(x,3,"Sounds/Spikes.ogg")
		Case Sound_Spin:			LoadGoodSound(x,3,"Sounds/Spin.ogg")
		Case Sound_SpinDashCharge:		LoadGoodSound(x,3,"Sounds/SpinDashCharge.ogg")
		Case Sound_SpinDashRelease:		LoadGoodSound(x,3,"Sounds/SpinDashRelease.ogg")
		Case Sound_SpinKick:			LoadGoodSound(x,3,"Sounds/SpinKick.ogg")
		Case Sound_Spirit:			LoadGoodSound(x,3,"Sounds/Spirit.ogg")
		Case Sound_Spring:			LoadGoodSound(x,3,"Sounds/Spring.ogg")
		Case Sound_SpringBounce:			LoadGoodSound(x,3,"Sounds/SpringBounce.ogg")
		Case Sound_SpringTrap:		LoadGoodSound(x,3,"Sounds/SpringTrap.ogg")
		Case Sound_Sting:			LoadGoodSound(x,3,"Sounds/Sting.ogg")
		Case Sound_Stomp:			LoadGoodSound(x,3,"Sounds/Stomp.ogg")
		Case Sound_StompGround:			LoadGoodSound(x,3,"Sounds/StompGround.ogg")
		Case Sound_Swinger:			LoadGoodSound(x,3,"Sounds/Swinger.ogg")
		Case Sound_Swipe:			LoadGoodSound(x,3,"Sounds/Swipe.ogg")
		Case Sound_Switch:			LoadGoodSound(x,3,"Sounds/Switch.ogg")
		Case Sound_SwitchOff:			LoadGoodSound(x,3,"Sounds/SwitchOff.ogg")
		Case Sound_SwitchAir:			LoadGoodSound(x,3,"Sounds/SwitchAir.ogg")
		Case Sound_Teleport:			LoadGoodSound(x,1,"Sounds/Teleport.ogg")
		Case Sound_Throw:			LoadGoodSound(x,3,"Sounds/Throw.ogg")
		Case Sound_Tinkle:			LoadGoodSound(x,3,"Sounds/Tinkle.ogg")
		Case Sound_Tnt:				LoadGoodSound(x,3,"Sounds/Tnt.ogg")
		Case Sound_Token:			LoadGoodSound(x,1,"Sounds/Token.ogg")
		Case Sound_Trash:			LoadGoodSound(x,3,"Sounds/Trash.ogg")
		Case Sound_Treasure:			LoadGoodSound(x,1,"Sounds/Treasure.ogg")
		Case Sound_Trick:			LoadGoodSound(x,3,"Sounds/Trick.ogg")
			
		Case Sound_Umbrella:			LoadGoodSound(x,3,"Sounds/Umbrella.ogg")
		Case Sound_Warp:			LoadGoodSound(x,1,"Sounds/Warp.ogg")
		Case Sound_WaterBoosting:		LoadGoodSound(x,3,"Sounds/WaterBoosting.ogg")
		Case Sound_WaterDash:			LoadGoodSound(x,3,"Sounds/WaterDash.ogg")
		Case Sound_WaterDrifting:		LoadGoodSound(x,3,"Sounds/WaterDrifting.ogg")
		Case Sound_Waterfall:			LoadGoodSound(x,3,"Sounds/Waterfall.ogg")
		Case Sound_WaterGun:			LoadGoodSound(x,3,"Sounds/WaterGun.ogg")
		Case Sound_WaterIn:			LoadGoodSound(x,3,"Sounds/WaterIn.ogg")
		Case Sound_WaterOut:			LoadGoodSound(x,3,"Sounds/WaterOut.ogg")
		Case Sound_WaterRise:			LoadGoodSound(x,1,"Sounds/WaterRise.ogg")
		Case Sound_Wind:			LoadGoodSound(x,3,"Sounds/Wind.ogg")
		Case Sound_WindBlow:		LoadGoodSound(x,3,"Sounds/WindBlow.ogg")
		Case Sound_Wrench:			LoadGoodSound(x,3,"Sounds/Wrench.ogg")
		Case Sound_LogoHum1:			LoadGoodSound(x,1,"Sounds/LogoHum1.ogg")
		Case Sound_LogoHum2:			LoadGoodSound(x,1,"Sounds/LogoHum2.ogg")
		Case Sound_LogoConnect:			LoadGoodSound(x,1,"Sounds/LogoConnect.ogg")
	End Select
	
End Function
Function Dummy(mode=1)
	Select mode
		Case 1
			PlaySmartSound(Sound_1Up)
		Case 2
			PlaySmartSound(Sound_DebugOnOff)
	End Select
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D