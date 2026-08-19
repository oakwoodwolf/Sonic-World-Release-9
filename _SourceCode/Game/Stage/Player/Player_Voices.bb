
	i = 0
	Global Sound_Jump[3]
	Sound_Jump[1]= i : i=i+1
	Sound_Jump[2]= i : i=i+1
	Sound_Jump[3]= i : i=i+1
	Global Sound_ModJump= i : i=i+1
	Global Sound_JumpD = i : i=i+1
	Global Voice_Attack1 = i : i=i+1
	Global Voice_Attack2 = i : i=i+1
	Global Voice_Attack3 = i : i=i+1
	Global Voice_Attack4 = i : i=i+1
	Global Voice_AttackX = i : i=i+1
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
	
	If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+directory$)) Then
		pathtovoice$=directory$
		For i = 1 To 3
			If sound=Sound_Jump[i]  Then
				If Not(FileType("Characters/"+ShortCharNames$(Menu\Character[i],1)+"/sounds/Jump.ogg")=1) Then 
					If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/Jump.ogg")=1) Then
						pathtovoice$="Sounds/Jump.ogg"	
					Else
						pathtovoice$=THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/Jump.ogg"
					EndIf
				Else
					If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/Jump.ogg")=1) Then
						pathtovoice$="Characters/"+ShortCharNames$(Menu\Character[i],1)+"/sounds/Jump.ogg"
					Else
						pathtovoice$=THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/Jump.ogg"
					EndIf
					
				EndIf
			EndIf
		Next
	ElseIf FileType(Game\Stage\Properties\Path$+directory$)=1 And Menu\Stage<>0 Then
		pathtovoice$=Game\Stage\Properties\Path$+directory$
	Else	
		pathtovoice$=(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+directory$)
		For i = 1 To 3
			If sound=Sound_Jump[i] Then
				If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Characters/"+ShortCharNames$(Menu\Character[i],1)+"/sounds/Jump.ogg")=1) Then pathtovoice$=THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+"Sounds/Jump.ogg"
			EndIf
		Next
	EndIf
	
	If mode=1 Then
		p\Voice[sound]=LoadSound(pathtovoice$)
	ElseIf mode=3 Then
		If Menu\Settings\ThreeDSounds#=1 Then
			p\Voice[sound]=Load3DSound(pathtovoice$)
		Else
			p\Voice[sound]=LoadSound(pathtovoice$)
		EndIf
	EndIf
	
	Select volume
		Case 1: SoundVolume(p\Voice[sound],Menu\Settings\VolumeSFX#*(Menu\Settings\Volume#*0.175))
		Case 2: SoundVolume(p\Voice[sound],Menu\Settings\VolumeVA#*(Menu\Settings\Volume#*0.175))
		Case 3: SoundVolume(p\Voice[sound],Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	End Select
End Function
;-----------------------------------------------------------------------------------------------------------------------------------
Function Player_LoadJumpSounds(p.tPlayer)
	
 	If IsCharMod(InterfaceChar(p\RealCharacter)) Then
		If MODCHARS_JUMPSOUND(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))=1 Then 
			LoadGoodPlayerVoice(p,Sound_ModJump,"_Mods/Characters/"+MODCHARS_PATH$(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))+"/Sounds/Jump.ogg")
		Else
			LoadGoodPlayerVoice(p,Sound_ModJump,"Sounds/Jump.ogg")
		EndIf
		If MODCHARS_DJUMPSOUND(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))=1 Then
			LoadGoodPlayerVoice(p,Sound_JumpD,"_Mods/Characters/"+MODCHARS_PATH$(InterfaceChar(p\RealCharacter-CHAR_MOD1+1))+"/Sounds/DoubleJump.ogg")
		EndIf
	Else
		For i = 1 To Menu\Members
			
			LoadGoodPlayerVoice(p,Sound_Jump[i],"Characters/"+ShortCharNames$(Menu\Character[i],1)+"/sounds/Jump.ogg")
			
			
		Next
		
	EndIf
	
End Function
;-----------------------------------------------------------------------------------------------------------------------------------
Function Player_LoadVoices(p.tPlayer)
	hasvoicemod=False
	If IsCharMod(p\RealCharacter) Then
		
	Else
		If MODVOICES_FOUND(InterfaceChar(p\RealCharacter))>0 Then hasvoicemod=True
	EndIf
	
	Select hasvoicemod
		Case False:
			If IsCharMod(InterfaceChar(p\RealCharacter)) Then
				voicedir$="_Mods/Characters/"+MODCHARS_PATH$(p\RealCharacter-CHAR_MOD1+1)+"/voice"
			Else
				voicedir$="Characters/"+ShortCharNames$(InterfaceChar(p\RealCharacter),1)+"/voice"
				
			EndIf
		Case True:
			voicedir$="_Mods/Voices/"+MODVOICES_PATH$(InterfaceChar(p\RealCharacter))
	End Select
	Player_LoadJumpSounds(p)
	
	LoadGoodPlayerVoice(p,Voice_Attack1,voicedir$+"/attack1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Attack2,voicedir$+"/attack2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Attack3,voicedir$+"/attack3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Attack4,voicedir$+"/attack4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_AttackX,voicedir$+"/attackX.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Die,voicedir$+"/die.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go1,voicedir$+"/go1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go2,voicedir$+"/go2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go3,voicedir$+"/go3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go4,voicedir$+"/go4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Go5,voicedir$+"/go5.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good1,voicedir$+"/good1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good2,voicedir$+"/good2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good3,voicedir$+"/good3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Good4,voicedir$+"/good4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Hurt,voicedir$+"/hurt.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump1,voicedir$+"/jump1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump2,voicedir$+"/jump2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump3,voicedir$+"/jump3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jump4,voicedir$+"/jump4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa1,voicedir$+"/jumpa1.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa2,voicedir$+"/jumpa2.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa3,voicedir$+"/jumpa3.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Jumpa4,voicedir$+"/jumpa4.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankS,voicedir$+"/ranks.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankA,voicedir$+"/ranka.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankB,voicedir$+"/rankb.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankC,voicedir$+"/rankc.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankD,voicedir$+"/rankd.ogg",2)
	LoadGoodPlayerVoice(p,Voice_RankEF,voicedir$+"/rankef.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Idle,voicedir$+"/idle.ogg",2)
	LoadGoodPlayerVoice(p,Voice_Transform,voicedir$+"/transform.ogg",2)
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

Function Player_PlaySpecialAttackVoice(p.tPlayer)
	If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
		StopChannel p\Channel_Voice
		p\Channel_Voice=EmitSound(p\Voice[Voice_AttackX],p\Objects\Entity)
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

Function Player_PlayIdleVoice(p.tPlayer)
	If Player_IsSoundable(p) And (Not(ChannelPlaying(p\Channel_Voice))) Then
		StopChannel p\Channel_Voice
		Select(Rand(1,3))
			Case 1: p\Channel_Voice=EmitSound(p\Voice[Voice_Idle],p\Objects\Entity)
		End Select
	EndIf
End Function



Function Player_PlayTurnVoice(p.tPlayer)
If Player_IsSoundable(p) Then
	StopChannel p\Channel_Voice
	Select(Rand(1,6))
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
	If IsCharMod(p\RealCharacter) Then
		EmitSound(p\Voice[Sound_ModJump],p\Objects\Entity)
	Else
		EmitSound(p\Voice[Sound_Jump[Game\Leader]],p\Objects\Entity)
	EndIf
	
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D