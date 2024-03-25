
	i = 0
	Global Sound_Jump = i : i=i+1
	Global Sound_JumpX = i : i=i+1
	Global Voice_Attack1 = i : i=i+1
	Global Voice_Attack2 = i : i=i+1
	Global Voice_Attack3 = i : i=i+1
	Global Voice_Attack4 = i : i=i+1
	Global Voice_Die = i : i=i+1
	Global Voice_Go1 = i : i=i+1
	Global Voice_Go2 = i : i=i+1
	Global Voice_Go3 = i : i=i+1
	Global Voice_Go4 = i : i=i+1
	Global Voice_Go5 = i : i=i+1
	Global Voice_Good1 = i : i=i+1
	Global Voice_Good2 = i : i=i+1
	Global Voice_Good3 = i : i=i+1
	Global Voice_Good4 = i : i=i+1
	Global Voice_Hurt = i : i=i+1
	Global Voice_Jump1 = i : i=i+1
	Global Voice_Jump2 = i : i=i+1
	Global Voice_Jump3 = i : i=i+1
	Global Voice_Jump4 = i : i=i+1
	Global Voice_Jumpa1 = i : i=i+1
	Global Voice_Jumpa2 = i : i=i+1
	Global Voice_Jumpa3 = i : i=i+1
	Global Voice_Jumpa4 = i : i=i+1
	Global Voice_RankS = i : i=i+1
	Global Voice_RankA = i : i=i+1
	Global Voice_RankB = i : i=i+1
	Global Voice_RankC = i : i=i+1
	Global Voice_RankD = i : i=i+1
	Global Voice_RankEF = i : i=i+1
	Global Voice_Idle = i : i=i+1
	Global Voice_Transform = i : i=i+1

	Global PLAYER_VOICES = i-1

;-----------------------------------------------------------------------------------------------------------------------------------

Function LoadGoodPlayerVoice(p.tPlayer,sound,directory$,volume=1)
		Select sound
			Case 0: mode=3
			Default: If Player_IsPlayable(p) Then mode=1 Else mode=3
		End Select

		If mode=1 Then
			p\Voice[sound]=LoadSound(directory$)
		ElseIf mode=3 Then
			If Menu\Settings\ThreeDSounds#=1 Then
				p\Voice[sound]=Load3DSound(directory$)
			Else
				p\Voice[sound]=LoadSound(directory$)
			EndIf
		EndIf

		Select volume
			Case 1: SoundVolume(p\Voice[sound],Menu\Settings\VolumeSFX#*Menu\Settings\Volume#)
			Case 2: SoundVolume(p\Voice[sound],Menu\Settings\VolumeVA#*Menu\Settings\Volume#)
			Case 3: SoundVolume(p\Voice[sound],Menu\Settings\VolumeM#*Menu\Settings\Volume#)
		End Select
	End Function

;-----------------------------------------------------------------------------------------------------------------------------------

Function Player_JumpSoundLoad(p.tPlayer,jumptype,jumppitch)
	Select jumptype
		Case 0:
			Select jumppitch
				Case -2: 
				;LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/JumpLoLo.ogg")
					If Not(FileType("Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpLoLo.ogg")) Then 
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme1/JumpLoLo.ogg")
					Else
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpLoLo.ogg")
					EndIf 
				Case -1:
					If Not(FileType("Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpLo.ogg")) Then 
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme1/JumpLo.ogg")
					Else
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpLo.ogg")
					EndIf 
				Case 00:
					If Not(FileType("Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpMed.ogg")) Then 
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme1/JumpMed.ogg")
					Else
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpMed.ogg")
					EndIf 
				Case +1:
					If Not(FileType("Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpHi.ogg")) Then 
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme1/JumpHi.ogg")
					Else
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpHi.ogg")
					EndIf 
				Case +2: 
					If Not(FileType("Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpHiHi.ogg")) Then 
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme1/JumpHiHi.ogg")
					Else
						LoadGoodPlayerVoice(p,Sound_Jump,"Sounds/Menu/Theme"+Int(Menu\Settings\Theme#)+"/JumpHiHi.ogg")
					EndIf 
			End Select
			

	Case 1:
		Select jumppitch
		Case 0: LoadGoodPlayerVoice(p,Sound_JumpX,"Sounds/0.ogg")
		Case 1: LoadGoodPlayerVoice(p,Sound_JumpX,"Sounds/JumpRobot.ogg")
		Case 2: LoadGoodPlayerVoice(p,Sound_JumpX,"Sounds/JumpRobot2.ogg")
		Case 3: LoadGoodPlayerVoice(p,Sound_JumpX,"Sounds/JumpNinja.ogg")
		Case 4: LoadGoodPlayerVoice(p,Sound_JumpX,"Sounds/JumpBubble.ogg")
		End Select
	End Select
End Function

Function Player_LoadJumpSounds(p.tPlayer)
 	h=00
 	If IsCharMod(InterfaceChar(p\RealCharacter)) Then
 		If MODCHARS_JUMPSOUND(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))=1 Then h=-1
 	EndIf
 	If h>=0 Then
		Select p\Character
		Case CHAR_SON: i=00 : j=00
		Case CHAR_TAI: i=+1 : j=00
		Case CHAR_KNU: i=-1 : j=00
		Case CHAR_AMY: i=+1 : j=+1
		Case CHAR_SHA: i=-1 : j=00
		Case CHAR_ROU: i=+1 : j=00
		Case CHAR_CRE: i=+1 : j=+2
		Case CHAR_BLA: i=00 : j=+1
		Case CHAR_SIL: i=-1 : j=-1
		Case CHAR_OME: i=-2 : j=-1 : h=2
		Case CHAR_ESP: i=00 : j=+1
		Case CHAR_CHA: i=+2 : j=+2
		Case CHAR_VEC: i=-2 : j=-1
		Case CHAR_BIG: i=-2 : j=-2
		Case CHAR_MAR: i=+2 : j=+1
		Case CHAR_MIG: i=00 : j=00
		Case CHAR_RAY: i=+1 : j=+1
		Case CHAR_CHO: i=-1 : j=-2 : h=4
		Case CHAR_TIK: i=+1 : j=+2
		Case CHAR_NAC: i=-1 : j=-1
		Case CHAR_BEA: i=00 : j=+1
		Case CHAR_BAR: i=-2 : j=-1
		Case CHAR_JET: i=00 : j=+1
		Case CHAR_WAV: i=+1 : j=+1
		Case CHAR_STO: i=-2 : j=-1
		Case CHAR_TIA: i=+1 : j=+1
		Case CHAR_HON: i=+1 : j=+1
		Case CHAR_SHD: i=-1 : j=-1
		Case CHAR_MPH: i=-2 : j=-2
		Case CHAR_HBO: i=-2 : j=-2 : h=3
		Case CHAR_GAM: i=-1 : j=-1 : h=2
		Case CHAR_EME: i=-1 : j=-1 : h=1
		Case CHAR_MET: i=00 : j=00 : h=1
		Case CHAR_TDL: i=+1 : j=+1 : h=1
		Case CHAR_MKN: i=-1 : j=-1 : h=1
		Case CHAR_EGG: i=-2 : j=-2 : h=2
		Case CHAR_BET: i=-1 : j=-1 : h=2
		Case CHAR_MT3: i=00 : j=00 : h=1
		Case CHAR_GME: i=-1 : j=-1 : h=1
		Case CHAR_PRS: i=00 : j=-1
		Case CHAR_COM: i=-1 : j=-1
		Case CHAR_CHW: i=-1 : j=-2 : h=2
		Case CHAR_TMH: i=+1 : j=-2 : h=2
		Case CHAR_EGR: i=-1 : j=+1 : h=1
		Case CHAR_INF: i=-2 : j=-1
		Default: i=00 : j=00
		End Select
		Player_JumpSoundLoad(p,0,i)
		Player_JumpSoundLoad(p,1,j)
		Player_JumpSoundLoad(p,2,h)
	Else
		LoadGoodPlayerVoice(p,Sound_Jump,"Mods/Characters/"+MODCHARS_PATH$(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))+"/Sounds/Jump.ogg")
	EndIf
End Function

;-----------------------------------------------------------------------------------------------------------------------------------

Function Player_LoadVoices(p.tPlayer)
	hasvoicemod=False
	If Menu\Settings\Mods#>0 Then
		If MODVOICES_FOUND(InterfaceChar(p\RealCharacter))>0 And (Not(IsCharMod(InterfaceChar(p\RealCharacter)))) Then hasvoicemod=True
	EndIf
	Select hasvoicemod
		Case False:
			If IsCharMod(InterfaceChar(p\RealCharacter)) Then
				voicedir$="Mods/Characters/"+MODCHARS_PATH$(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))+"/voice"
				dir$=""
			Else
				voicedir$="Voices/"
				Select InterfaceChar(p\RealCharacter)
					Case CHAR_EGG:
						dir$=ShortCharNames$(InterfaceChar(p\RealCharacter),2)
					Default:
						dir$=ShortCharNames$(InterfaceChar(p\RealCharacter),1)
				End Select
			EndIf
		Case True:
			voicedir$="Mods/Voices/"+MODVOICES_PATH$(InterfaceChar(p\RealCharacter))
			dir$=ShortCharNames$(InterfaceChar(p\RealCharacter),1)
			dir$=""
	End Select
	Player_LoadJumpSounds(p)
	LoadGoodPlayerVoice(p,Voice_Attack1,voicedir$+dir$+"/attack1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Attack2,voicedir$+dir$+"/attack2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Attack3,voicedir$+dir$+"/attack3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Attack4,voicedir$+dir$+"/attack4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Die,voicedir$+dir$+"/die.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go1,voicedir$+dir$+"/go1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go2,voicedir$+dir$+"/go2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go3,voicedir$+dir$+"/go3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go4,voicedir$+dir$+"/go4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go5,voicedir$+dir$+"/go5.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good1,voicedir$+dir$+"/good1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good2,voicedir$+dir$+"/good2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good3,voicedir$+dir$+"/good3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good4,voicedir$+dir$+"/good4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Hurt,voicedir$+dir$+"/hurt.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump1,voicedir$+dir$+"/jump1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump2,voicedir$+dir$+"/jump2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump3,voicedir$+dir$+"/jump3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump4,voicedir$+dir$+"/jump4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa1,voicedir$+dir$+"/jumpa1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa2,voicedir$+dir$+"/jumpa2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa3,voicedir$+dir$+"/jumpa3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa4,voicedir$+dir$+"/jumpa4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankS,voicedir$+dir$+"/ranks.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankA,voicedir$+dir$+"/ranka.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankB,voicedir$+dir$+"/rankb.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankC,voicedir$+dir$+"/rankc.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankD,voicedir$+dir$+"/rankd.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankEF,voicedir$+dir$+"/rankef.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Idle,voicedir$+dir$+"/idle.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Transform,voicedir$+dir$+"/transform.ogg",2)
End Function

;-----------------------------------------------------------------------------------------------------------------------------------

Function Player_PlayAttackVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,5))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Attack1],p\Objects\Entity)
		Case 2: p\Channel_Voice=EmitSound(p\Voice[Voice_Attack2],p\Objects\Entity)
		Case 3: p\Channel_Voice=EmitSound(p\Voice[Voice_Attack3],p\Objects\Entity)
		Case 4: p\Channel_Voice=EmitSound(p\Voice[Voice_Attack4],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_PlayDieVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,2))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Die],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_PlayTurnVoice(p.tPlayer)
If Player_IsSoundable(p) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,5))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Go1],p\Objects\Entity)
		Case 2: p\Channel_Voice=EmitSound(p\Voice[Voice_Go2],p\Objects\Entity)
		Case 3: p\Channel_Voice=EmitSound(p\Voice[Voice_Go3],p\Objects\Entity)
		Case 4: p\Channel_Voice=EmitSound(p\Voice[Voice_Go4],p\Objects\Entity)
		Case 5: p\Channel_Voice=EmitSound(p\Voice[Voice_Go5],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_PlayGoodVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,5))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Good1],p\Objects\Entity)
		Case 2: p\Channel_Voice=EmitSound(p\Voice[Voice_Good2],p\Objects\Entity)
		Case 3: p\Channel_Voice=EmitSound(p\Voice[Voice_Good3],p\Objects\Entity)
		Case 4: p\Channel_Voice=EmitSound(p\Voice[Voice_Good4],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_PlayHurtVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,2))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Hurt],p\Objects\Entity)
	End Select
EndIf
End Function
Function Player_PlayIdleVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,2))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Idle],p\Objects\Entity)
	End Select
EndIf
End Function
Function Player_PlayTransformVoice(p.tPlayer)
	If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
		StopChannel p\Channel_Voice
		p\Channel_Voice=EmitSound(p\Voice[Voice_Idle],p\Objects\Entity)
	EndIf
End Function
Function Player_PlayJumpVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,5))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Jump1],p\Objects\Entity)
		Case 2: p\Channel_Voice=EmitSound(p\Voice[Voice_Jump2],p\Objects\Entity)
		Case 3: p\Channel_Voice=EmitSound(p\Voice[Voice_Jump3],p\Objects\Entity)
		Case 4: p\Channel_Voice=EmitSound(p\Voice[Voice_Jump4],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_PlayJumpActionVoice(p.tPlayer)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,5))
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Jumpa1],p\Objects\Entity)
		Case 2: p\Channel_Voice=EmitSound(p\Voice[Voice_Jumpa2],p\Objects\Entity)
		Case 3: p\Channel_Voice=EmitSound(p\Voice[Voice_Jumpa3],p\Objects\Entity)
		Case 4: p\Channel_Voice=EmitSound(p\Voice[Voice_Jumpa4],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_PlayRankVoice(p.tPlayer,rank#)
If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
	StopChannel p\Channel_Voice
	Select rank#
		Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_RankS],p\Objects\Entity)
		Case 2: p\Channel_Voice=EmitSound(p\Voice[Voice_RankA],p\Objects\Entity)
		Case 3: p\Channel_Voice=EmitSound(p\Voice[Voice_RankB],p\Objects\Entity)
		Case 4: p\Channel_Voice=EmitSound(p\Voice[Voice_RankC],p\Objects\Entity)
		Case 5: p\Channel_Voice=EmitSound(p\Voice[Voice_RankD],p\Objects\Entity)
		Case 6,7: p\Channel_Voice=EmitSound(p\Voice[Voice_RankEF],p\Objects\Entity)
	End Select
EndIf
End Function

Function Player_JumpSound(p.tPlayer)
	EmitSound(p\Voice[Sound_Jump],p\Objects\Entity)
	EmitSound(p\Voice[Sound_JumpX],p\Objects\Entity)
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D