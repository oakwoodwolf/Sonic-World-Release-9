
;; The short names, usually used for filename detection
Dim ShortCharNames$(CHAR_FULLCOUNT,2)
ShortCharNames$(CHAR_SON,1) = "Son"
ShortCharNames$(CHAR_TAI,1) = "Tai" : ShortCharNames$(CHAR_TAI,2) = "Tmh"
ShortCharNames$(CHAR_KNU,1) = "Knu"
ShortCharNames$(CHAR_AMY,1) = "Amy"
ShortCharNames$(CHAR_SHA,1) = "Sha"
ShortCharNames$(CHAR_ROU,1) = "Rou"
ShortCharNames$(CHAR_CRE,1) = "Cre"
ShortCharNames$(CHAR_BLA,1) = "Bla"
ShortCharNames$(CHAR_SIL,1) = "Sil"
ShortCharNames$(CHAR_OME,1) = "Ome"
ShortCharNames$(CHAR_ESP,1) = "Esp"
ShortCharNames$(CHAR_CHA,1) = "Cha"
ShortCharNames$(CHAR_VEC,1) = "Vec"
ShortCharNames$(CHAR_BIG,1) = "Big"
ShortCharNames$(CHAR_MAR,1) = "Mar"
ShortCharNames$(CHAR_MIG,1) = "Mig"
ShortCharNames$(CHAR_RAY,1) = "Ray"
ShortCharNames$(CHAR_CHO,1) = "Cho"
ShortCharNames$(CHAR_TIK,1) = "Tik"
ShortCharNames$(CHAR_NAC,1) = "Nac"
ShortCharNames$(CHAR_BEA,1) = "Bea"
ShortCharNames$(CHAR_BAR,1) = "Bar"
ShortCharNames$(CHAR_JET,1) = "Jet"
ShortCharNames$(CHAR_WAV,1) = "Wav"
ShortCharNames$(CHAR_STO,1) = "Sto"
ShortCharNames$(CHAR_TIA,1) = "Tia"
ShortCharNames$(CHAR_HON,1) = "Hon"
ShortCharNames$(CHAR_SHD,1) = "Shd"
ShortCharNames$(CHAR_MPH,1) = "Mph"
ShortCharNames$(CHAR_HBO,1) = "Hbo"
ShortCharNames$(CHAR_GAM,1) = "Gam"
ShortCharNames$(CHAR_EME,1) = "Eme"
ShortCharNames$(CHAR_MET,1) = "Met"
ShortCharNames$(CHAR_TDL,1) = "Tdl"
ShortCharNames$(CHAR_MKN,1) = "Mkn"
ShortCharNames$(CHAR_EGG,1) = "Egg" : ShortCharNames$(CHAR_EGG,2) = "Egw"
ShortCharNames$(CHAR_BET,1) = "Bet"
ShortCharNames$(CHAR_MT3,1) = "Mt3"
ShortCharNames$(CHAR_GME,1) = "Gme"
ShortCharNames$(CHAR_PRS,1) = "Prs"
ShortCharNames$(CHAR_COM,1) = "Com"
ShortCharNames$(CHAR_CHW,1) = "Chw"
ShortCharNames$(CHAR_EGR,1) = "Egr"
ShortCharNames$(CHAR_INF,1) = "Inf"
;---
ShortCharNames$(CHAR_OBT,1) = "Obt"
ShortCharNames$(CHAR_CBT,1) = "Cbt"
ShortCharNames$(CHAR_EGN,1) = "Egn"
ShortCharNames$(CHAR_MIA,1) = "Mia"
ShortCharNames$(CHAR_VAN,1) = "Van"
;---
ShortCharNames$(CHAR_CHE,1) = "Che"
ShortCharNames$(CHAR_CHC,1) = "Chc"
ShortCharNames$(CHAR_FRO,1) = "Fro"

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; Character values
	Const TEAM_SONIC		= 1
	Const TEAM_DARK			= 2
	Const TEAM_ROSE			= 3
	Const TEAM_CHAOTIX		= 4
	Const TEAM_SOL			= 5
	Const TEAM_OLDIES		= 6
	Const TEAM_HOOLIGAN		= 7
	Const TEAM_BABYLON		= 8
	Const TEAM_RELIC		= 9
	Const TEAM_ROBOTNIK		= 10
	Const TEAM_TEAMCOUNT	= TEAM_ROBOTNIK+1

	Const CHAR_SON			= 1
	Const CHAR_TAI			= 2
	Const CHAR_KNU			= 3
	Const CHAR_AMY			= 4
	Const CHAR_SHA			= 5
	Const CHAR_ROU			= 6
	Const CHAR_CRE			= 7
	Const CHAR_BLA			= 8
	Const CHAR_SIL			= 9
	Const CHAR_OME			= 10
	Const CHAR_ESP			= 11
	Const CHAR_CHA			= 12
	Const CHAR_VEC			= 13
	Const CHAR_BIG			= 14
	Const CHAR_MAR			= 15
	Const CHAR_MIG			= 16
	Const CHAR_RAY			= 17
	Const CHAR_CHO			= 18
	Const CHAR_TIK			= 19
	Const CHAR_NAC			= 20
	Const CHAR_BEA			= 21
	Const CHAR_BAR			= 22
	Const CHAR_JET			= 23
	Const CHAR_WAV			= 24
	Const CHAR_STO			= 25
	Const CHAR_TIA			= 26
	Const CHAR_HON			= 27
	Const CHAR_SHD			= 28
	Const CHAR_MPH			= 29
	Const CHAR_HBO			= 30
	Const CHAR_GAM			= 31
	Const CHAR_EME			= 32
	Const CHAR_MET			= 33
	Const CHAR_TDL			= 34
	Const CHAR_MKN			= 35
	;End of row 1 in CSS. Each row houses 35 + random
	Const CHAR_EGG			= 36
	Const CHAR_BET			= 37
	Const CHAR_MT3			= 38
	Const CHAR_GME			= 39
	Const CHAR_PRS			= 40
	Const CHAR_COM			= 41
	Const CHAR_CHW			= 42
	Const CHAR_EGR			= 43
	Const CHAR_INF			= 44
	Const CHAR_NONMODPLAYABLECOUNT= 44
	;Mod slots, duplicate and increment to your liking.
	Const CHAR_MOD1			= CHAR_NONMODPLAYABLECOUNT+1
	Const CHAR_MOD2			= CHAR_NONMODPLAYABLECOUNT+2
	Const CHAR_MOD3			= CHAR_NONMODPLAYABLECOUNT+3
	Const CHAR_MOD4			= CHAR_NONMODPLAYABLECOUNT+4
	Const CHAR_MOD5			= CHAR_NONMODPLAYABLECOUNT+5
	Const CHAR_MOD6			= CHAR_NONMODPLAYABLECOUNT+6
	Const CHAR_MOD7			= CHAR_NONMODPLAYABLECOUNT+7
	Const CHAR_MOD8			= CHAR_NONMODPLAYABLECOUNT+8
	Const CHAR_MOD9			= CHAR_NONMODPLAYABLECOUNT+9
	Const CHAR_MOD10		= CHAR_NONMODPLAYABLECOUNT+10
	Const CHAR_MOD11		= CHAR_NONMODPLAYABLECOUNT+11
	Const CHAR_MOD12		= CHAR_NONMODPLAYABLECOUNT+12
	Const CHAR_MOD13		= CHAR_NONMODPLAYABLECOUNT+13
	Const CHAR_MOD14		= CHAR_NONMODPLAYABLECOUNT+14
	Const CHAR_MOD15		= CHAR_NONMODPLAYABLECOUNT+15
	Const CHAR_MOD16		= CHAR_NONMODPLAYABLECOUNT+16
	Const CHAR_MOD17		= CHAR_NONMODPLAYABLECOUNT+17
	Const CHAR_MOD18		= CHAR_NONMODPLAYABLECOUNT+18
	Const CHAR_MOD19		= CHAR_NONMODPLAYABLECOUNT+19
	Const CHAR_MOD20		= CHAR_NONMODPLAYABLECOUNT+20
	Const CHAR_MOD21		= CHAR_NONMODPLAYABLECOUNT+21
	Const CHAR_MOD22		= CHAR_NONMODPLAYABLECOUNT+22
	Const CHAR_MOD23		= CHAR_NONMODPLAYABLECOUNT+23
	Const CHAR_MOD24		= CHAR_NONMODPLAYABLECOUNT+24
	Const CHAR_MOD25		= CHAR_NONMODPLAYABLECOUNT+25
	Const CHAR_MOD26		= CHAR_NONMODPLAYABLECOUNT+26
	Const CHAR_MOD27 		= CHAR_NONMODPLAYABLECOUNT+27
	Const CHAR_MOD28 		= CHAR_NONMODPLAYABLECOUNT+28
	Const CHAR_MOD29 		= CHAR_NONMODPLAYABLECOUNT+29
	Const CHAR_MOD30 		= CHAR_NONMODPLAYABLECOUNT+30
	Const CHAR_MOD31 		= CHAR_NONMODPLAYABLECOUNT+31
	Const CHAR_MOD32 		= CHAR_NONMODPLAYABLECOUNT+32
	Const CHAR_MOD33 		= CHAR_NONMODPLAYABLECOUNT+33
	Const CHAR_MOD34 		= CHAR_NONMODPLAYABLECOUNT+34
	Const CHAR_MOD35 		= CHAR_NONMODPLAYABLECOUNT+35
	Const CHAR_MOD36 		= CHAR_NONMODPLAYABLECOUNT+36
	Const CHAR_MOD37 		= CHAR_NONMODPLAYABLECOUNT+37
	Const CHAR_MOD38 		= CHAR_NONMODPLAYABLECOUNT+38
	Const CHAR_MOD39 		= CHAR_NONMODPLAYABLECOUNT+39
	Const CHAR_MOD40 		= CHAR_NONMODPLAYABLECOUNT+40
	Const CHAR_MOD41 		= CHAR_NONMODPLAYABLECOUNT+41
	Const CHAR_MOD42 		= CHAR_NONMODPLAYABLECOUNT+42
	Const CHAR_MOD43 		= CHAR_NONMODPLAYABLECOUNT+43
	Const CHAR_MOD44 		= CHAR_NONMODPLAYABLECOUNT+44
	Const CHAR_MOD45 		= CHAR_NONMODPLAYABLECOUNT+45
	Const CHAR_MOD46 		= CHAR_NONMODPLAYABLECOUNT+46
	Const CHAR_MOD47 		= CHAR_NONMODPLAYABLECOUNT+47
	Const CHAR_MOD48 		= CHAR_NONMODPLAYABLECOUNT+48
	Const CHAR_MOD49 		= CHAR_NONMODPLAYABLECOUNT+49
	Const CHAR_MOD50 		= CHAR_NONMODPLAYABLECOUNT+50
	Const CHAR_MOD51 		= CHAR_NONMODPLAYABLECOUNT+51
	Const CHAR_MOD52 		= CHAR_NONMODPLAYABLECOUNT+52
	Const CHAR_MOD53 		= CHAR_NONMODPLAYABLECOUNT+53
	Const CHAR_MOD54 		= CHAR_NONMODPLAYABLECOUNT+54
	Const CHAR_MOD55 		= CHAR_NONMODPLAYABLECOUNT+55
	Const CHAR_MOD56 		= CHAR_NONMODPLAYABLECOUNT+56
	Const CHAR_MOD57 		= CHAR_NONMODPLAYABLECOUNT+57
	Const CHAR_MOD58 		= CHAR_NONMODPLAYABLECOUNT+58
	Const CHAR_MOD59 		= CHAR_NONMODPLAYABLECOUNT+59
	Const CHAR_MOD60 		= CHAR_NONMODPLAYABLECOUNT+60
	Const CHAR_MOD61 		= CHAR_NONMODPLAYABLECOUNT+61
	Const CHAR_MOD62 		= CHAR_NONMODPLAYABLECOUNT+62
	Const CHAR_MOD63 		= CHAR_NONMODPLAYABLECOUNT+63
	Const CHAR_MOD64 		= CHAR_NONMODPLAYABLECOUNT+64
	Const CHAR_MOD65 		= CHAR_NONMODPLAYABLECOUNT+65
	Const CHAR_MOD66 		= CHAR_NONMODPLAYABLECOUNT+66
	Const CHAR_MOD67 		= CHAR_NONMODPLAYABLECOUNT+67
	Const CHAR_MOD68 		= CHAR_NONMODPLAYABLECOUNT+68
	Const CHAR_MOD69 		= CHAR_NONMODPLAYABLECOUNT+69
	Const CHAR_MOD70 		= CHAR_NONMODPLAYABLECOUNT+70
	Const CHAR_MOD71 		= CHAR_NONMODPLAYABLECOUNT+71
	Const CHAR_MOD72 		= CHAR_NONMODPLAYABLECOUNT+72
	Const CHAR_MOD73 		= CHAR_NONMODPLAYABLECOUNT+73
	Const CHAR_MOD74 		= CHAR_NONMODPLAYABLECOUNT+74
	Const CHAR_MOD75 		= CHAR_NONMODPLAYABLECOUNT+75
	Const CHAR_MOD76 		= CHAR_NONMODPLAYABLECOUNT+76
	Const CHAR_MOD77 		= CHAR_NONMODPLAYABLECOUNT+77
	Const CHAR_MOD78 		= CHAR_NONMODPLAYABLECOUNT+78
	Const CHAR_MOD79 		= CHAR_NONMODPLAYABLECOUNT+79
	Const CHAR_MOD80 		= CHAR_NONMODPLAYABLECOUNT+80
	Const CHAR_MOD81 		= CHAR_NONMODPLAYABLECOUNT+81
	Const CHAR_MOD82 		= CHAR_NONMODPLAYABLECOUNT+82
	Const CHAR_MOD83 		= CHAR_NONMODPLAYABLECOUNT+83
	Const CHAR_MOD84 		= CHAR_NONMODPLAYABLECOUNT+84
	Const CHAR_MOD85 		= CHAR_NONMODPLAYABLECOUNT+85
	Const CHAR_MOD86 		= CHAR_NONMODPLAYABLECOUNT+86
	Const CHAR_MOD87 		= CHAR_NONMODPLAYABLECOUNT+87
	Const CHAR_MOD88 		= CHAR_NONMODPLAYABLECOUNT+88
	Const CHAR_MOD89 		= CHAR_NONMODPLAYABLECOUNT+89
	Const CHAR_MOD90 		= CHAR_NONMODPLAYABLECOUNT+90
	Const CHAR_MOD91 		= CHAR_NONMODPLAYABLECOUNT+91
	Const CHAR_MOD92 		= CHAR_NONMODPLAYABLECOUNT+92
	Const CHAR_MOD93 		= CHAR_NONMODPLAYABLECOUNT+93
	Const CHAR_MOD94 		= CHAR_NONMODPLAYABLECOUNT+94
	Const CHAR_MOD95 		= CHAR_NONMODPLAYABLECOUNT+95
	Const CHAR_MOD96 		= CHAR_NONMODPLAYABLECOUNT+96
	Const CHAR_MOD97 		= CHAR_NONMODPLAYABLECOUNT+97
	Const CHAR_MOD98 		= CHAR_NONMODPLAYABLECOUNT+98
	Const CHAR_MOD99 		= CHAR_NONMODPLAYABLECOUNT+99
	Const CHAR_MOD100		= CHAR_NONMODPLAYABLECOUNT+100
	Const CHAR_MOD101		= CHAR_NONMODPLAYABLECOUNT+101
	Const CHAR_MOD102		= CHAR_NONMODPLAYABLECOUNT+102
	Const CHAR_MOD103		= CHAR_NONMODPLAYABLECOUNT+103
	Const CHAR_MOD104		= CHAR_NONMODPLAYABLECOUNT+104
	Const CHAR_MOD105		= CHAR_NONMODPLAYABLECOUNT+105
	Const CHAR_MOD106		= CHAR_NONMODPLAYABLECOUNT+106
	Const CHAR_MOD107		= CHAR_NONMODPLAYABLECOUNT+107
	Const CHAR_MOD108		= CHAR_NONMODPLAYABLECOUNT+108
	Const CHAR_MOD109		= CHAR_NONMODPLAYABLECOUNT+109
	Const CHAR_MOD110		= CHAR_NONMODPLAYABLECOUNT+110
	Const CHAR_MOD111		= CHAR_NONMODPLAYABLECOUNT+111
	Const CHAR_MOD112		= CHAR_NONMODPLAYABLECOUNT+112
	Const CHAR_MOD113		= CHAR_NONMODPLAYABLECOUNT+113
	Const CHAR_MOD114		= CHAR_NONMODPLAYABLECOUNT+114
	Const CHAR_MOD115		= CHAR_NONMODPLAYABLECOUNT+115
	Const CHAR_MOD116		= CHAR_NONMODPLAYABLECOUNT+116
	Const CHAR_MOD117		= CHAR_NONMODPLAYABLECOUNT+117
	Const CHAR_MOD118		= CHAR_NONMODPLAYABLECOUNT+118
	Const CHAR_MOD119		= CHAR_NONMODPLAYABLECOUNT+119
	Const CHAR_MOD120		= CHAR_NONMODPLAYABLECOUNT+120
	Const CHAR_MOD121		= CHAR_NONMODPLAYABLECOUNT+121
	Const CHAR_MOD122		= CHAR_NONMODPLAYABLECOUNT+122
	Const CHAR_MOD123		= CHAR_NONMODPLAYABLECOUNT+123
	Const CHAR_MOD124		= CHAR_NONMODPLAYABLECOUNT+124
	Const CHAR_MOD125		= CHAR_NONMODPLAYABLECOUNT+125
	Const CHAR_MOD126		= CHAR_NONMODPLAYABLECOUNT+126
	Const CHAR_MOD127		= CHAR_NONMODPLAYABLECOUNT+127
	Const CHAR_MOD128		= CHAR_NONMODPLAYABLECOUNT+128
	Const CHAR_MOD129		= CHAR_NONMODPLAYABLECOUNT+129
	Const CHAR_MOD130		= CHAR_NONMODPLAYABLECOUNT+130
	Const CHAR_MOD131		= CHAR_NONMODPLAYABLECOUNT+131
	Const CHAR_MOD132		= CHAR_NONMODPLAYABLECOUNT+132
	Const CHAR_MOD133		= CHAR_NONMODPLAYABLECOUNT+133
	Const CHAR_MOD134		= CHAR_NONMODPLAYABLECOUNT+134
	Const CHAR_MOD135		= CHAR_NONMODPLAYABLECOUNT+135
	Const CHAR_MOD136		= CHAR_NONMODPLAYABLECOUNT+136
	Const CHAR_MOD137		= CHAR_NONMODPLAYABLECOUNT+137
	Const CHAR_MOD138		= CHAR_NONMODPLAYABLECOUNT+138
	Const CHAR_MOD139		= CHAR_NONMODPLAYABLECOUNT+139
	Const CHAR_MOD140		= CHAR_NONMODPLAYABLECOUNT+140
	Const CHAR_MOD141		= CHAR_NONMODPLAYABLECOUNT+141
	Const CHAR_MOD142		= CHAR_NONMODPLAYABLECOUNT+142
	Const CHAR_MOD143		= CHAR_NONMODPLAYABLECOUNT+143
	Const CHAR_MOD144		= CHAR_NONMODPLAYABLECOUNT+144
	Const CHAR_MOD145		= CHAR_NONMODPLAYABLECOUNT+145
	Const CHAR_MOD146		= CHAR_NONMODPLAYABLECOUNT+146
	Const CHAR_MOD147		= CHAR_NONMODPLAYABLECOUNT+147
	Const CHAR_MOD148		= CHAR_NONMODPLAYABLECOUNT+148
	Const CHAR_MOD149		= CHAR_NONMODPLAYABLECOUNT+149
	Const CHAR_MOD150		= CHAR_NONMODPLAYABLECOUNT+150
	Const CHAR_MOD151		= CHAR_NONMODPLAYABLECOUNT+151
	Const CHAR_MOD152		= CHAR_NONMODPLAYABLECOUNT+152
	Const CHAR_MOD153		= CHAR_NONMODPLAYABLECOUNT+153
	Const CHAR_MOD154		= CHAR_NONMODPLAYABLECOUNT+154
	Const CHAR_MOD155		= CHAR_NONMODPLAYABLECOUNT+155
	Const CHAR_MOD156		= CHAR_NONMODPLAYABLECOUNT+156
	Const CHAR_MOD157		= CHAR_NONMODPLAYABLECOUNT+157
	Const CHAR_MOD158		= CHAR_NONMODPLAYABLECOUNT+158
	Const CHAR_MOD159		= CHAR_NONMODPLAYABLECOUNT+159
	Const CHAR_MOD160		= CHAR_NONMODPLAYABLECOUNT+160
	Const CHAR_MOD161		= CHAR_NONMODPLAYABLECOUNT+161
	Const CHAR_MOD162		= CHAR_NONMODPLAYABLECOUNT+162
	Const CHAR_MOD163		= CHAR_NONMODPLAYABLECOUNT+163
	Const CHAR_MOD164		= CHAR_NONMODPLAYABLECOUNT+164
	Const CHAR_MOD165		= CHAR_NONMODPLAYABLECOUNT+165
	Const CHAR_MOD166		= CHAR_NONMODPLAYABLECOUNT+166
	Const CHAR_PLAYABLECOUNT= CHAR_NONMODPLAYABLECOUNT+166

	Const CHAR_OBT			= CHAR_PLAYABLECOUNT+1
	Const CHAR_CBT			= CHAR_PLAYABLECOUNT+2
	Const CHAR_EGN			= CHAR_PLAYABLECOUNT+3
	Const CHAR_MIA			= CHAR_PLAYABLECOUNT+4
	Const CHAR_VAN			= CHAR_PLAYABLECOUNT+5
	Const CHAR_NORMALCOUNT	= CHAR_PLAYABLECOUNT+5
	Const CHAR_CHE			= CHAR_NORMALCOUNT+1
	Const CHAR_CHC			= CHAR_NORMALCOUNT+2
	Const CHAR_FRO			= CHAR_NORMALCOUNT+3
	Const CHAR_FULLCOUNT	= CHAR_NORMALCOUNT+3
	Const CHAR_TMH			= 900+CHAR_TAI

	; ---- Character Meshes ----
	Global CharacterMesh
	Function LoadCharacterMesh(char,mode=0,super=0,costume=0)
		If costume=0 Then
			costume=1
			If Not(Menu\Stage=0) Then
				Select char
					Case CHAR_EGG: costume=2
				End Select
			EndIf
		EndIf

		Select char
			Case -2: CharacterMesh = LoadAnimMesh("Objects/Rings/Emblem.b3d") : ExtractAllCharacterAnimations_Character(char,-2)
			Case -1: CharacterMesh = LoadAnimMesh("Objects/Unknown.b3d") : ExtractAllCharacterAnimations_Character(char,-1)
			Case CHAR_CHE,CHAR_CHC: CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Chao(char,mode)
			Case CHAR_FRO: CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Froggy(char,mode)
			Default:
				If IsCharMod(char) Then
					If super=0 Then
					CharacterMesh = LoadAnimMesh("Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/"+MODCHARS_MODEL$(char-CHAR_MOD1+1))
					ElseIf super=1 Then
					CharacterMesh = LoadAnimMesh("Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/"+MODCHARS_SMODEL$(char-CHAR_MOD1+1))
					ElseIf super=2 Then
					CharacterMesh = LoadAnimMesh("Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/"+MODCHARS_HMODEL$(char-CHAR_MOD1+1))
					EndIf
				Else
					If char>900 Then
						If super=0 Then
						CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(InterfaceChar(char),costume+1)+".b3d")
						ElseIf super=1 Then
						CharacterMesh = LoadAnimMesh("Characters/s"+ShortCharNames$(InterfaceChar(char),costume+1)+".b3d")
						ElseIf super=2 Then
						CharacterMesh = LoadAnimMesh("Characters/h"+ShortCharNames$(InterfaceChar(char),costume+1)+".b3d")
						EndIf
					Else
						If super=0 Then
						CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d")
						ElseIf super=1 Then
						CharacterMesh = LoadAnimMesh("Characters/s"+ShortCharNames$(char,costume)+".b3d")
						ElseIf super=2 Then
						CharacterMesh = LoadAnimMesh("Characters/h"+ShortCharNames$(char,costume)+".b3d")
						EndIf
					EndIf
				EndIf
				ExtractAllCharacterAnimations_Character(char,mode)
		End Select
		MoveEntity(CharacterMesh),0,-99999,0 : HideEntity(CharacterMesh)
	End Function
	Function DeleteCharacterMesh()
		FreeEntity CharacterMesh
		CharacterMesh = CreatePivot()
	End Function

	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------
	;; extracts all the animations of a character's b3d and splits it appropriately
	;;param char the character the animation is being extracted for.
	;;param mode the context of extraction. Default is 0 for gameplay. 1 - character menu, 2 - bio, 3 - team select, 4 - eggman
	Function ExtractAllCharacterAnimations_Character(char,mode=0)

		a=1

		Select mode
		Case -1: ;unknown
			ExtractAnimSeq(CharacterMesh,		0,	0)					;Victory
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Idle
			ExtractAnimSeq(CharacterMesh,		0,	0)					;Walk
			ExtractAnimSeq(CharacterMesh,		0,	0)					;Jog
			ExtractAnimSeq(CharacterMesh,		0,	0)					;Run
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17)			;Spin
		Case 1: ;character menu
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Victory
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Idle
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Walk
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Jog
			Select char
			Case CHAR_SHA:
				ExtractAnimSeq(CharacterMesh,	0,	0) : a=a+33			;Run
			Default:
				If IsCharMod(char) Then
					If MODCHARS_SKATES(char-CHAR_MOD1+1)>0 Then
					ExtractAnimSeq(CharacterMesh,	0,	0) : a=a+33		;Run
					Else
					ExtractAnimSeq(CharacterMesh,	0,	0) : a=a+17		;Run
					EndIf
				Else
					ExtractAnimSeq(CharacterMesh,	0,	0) : a=a+17			;Run
				EndIf
			End Select
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9)				;Spin
		Case 2: ;bio menu
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Victory
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Idle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Walk
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Jog
			Select char
			Case CHAR_SHA:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;Run
			Default:
				If IsCharMod(char) Then
					If MODCHARS_SKATES(char-CHAR_MOD1+1)>0 Then
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;Run
					Else
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Run
					EndIf
				Else
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Run
				EndIf
			End Select
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Spin
			a=a+18														;Fall,FallFast
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9)				;Forward
		Case 3: ;team menu
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17)			;Victory
		Case 4: ;eggman
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Victory
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Idle
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Walk
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Jog
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17)			;Sit
		Default:
			actualchar=char
			If IsCharMod(char) Then char=MODCHARS_TYPE(char-CHAR_MOD1+1)
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Victory
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Idle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Walk
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Jog
			Select actualchar
			Case CHAR_SHA:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;Run
			Default:
				If IsCharMod(actualchar) Then
					If MODCHARS_SKATES(actualchar-CHAR_MOD1+1)>0 Then
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;Run
					Else
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Run
					EndIf
				Else
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Run
				EndIf
			End Select
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Spin
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Fall
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;FallFast
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Forward
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Up
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Float
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Brake
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Hurt
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+1) : a=a+1		;Dead
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+1) : a=a+1		;DeadFall

			Select char
			Case CHAR_TAI,CHAR_ROU,CHAR_CRE,CHAR_SIL,CHAR_CHA,CHAR_TDL,CHAR_TIK,CHAR_RAY,CHAR_JET,CHAR_WAV,CHAR_TIA,CHAR_MPH,CHAR_OME,CHAR_EME,CHAR_MT3,CHAR_GME,CHAR_EGR,CHAR_BEA,CHAR_INF:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Fly
			Case CHAR_BIG,CHAR_GAM,CHAR_BET:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Fly
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Fly
			End Select

			Select char
			Case CHAR_KNU,CHAR_ROU,CHAR_OME,CHAR_MKN,CHAR_RAY,CHAR_CHO,CHAR_JET,CHAR_STO,CHAR_TIA,CHAR_BAR,CHAR_TIK,CHAR_HBO,CHAR_HON,CHAR_SHD,CHAR_GAM,CHAR_EME,CHAR_EGG,CHAR_BET,CHAR_GME,CHAR_COM,CHAR_CHW,CHAR_TMH:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Glide
			Case CHAR_VEC,CHAR_BIG,CHAR_MAR:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Glide
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Glide
			End Select

			Select char
			Case CHAR_KNU,CHAR_ROU,CHAR_ESP,CHAR_MKN,CHAR_CHO,CHAR_TIK,CHAR_SHD,CHAR_EME,CHAR_GME,CHAR_COM:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;ClimbIdle
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Climb
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ClimbIdle
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
			End Select

			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Grind
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;GrindFast
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;CarryIdle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;CarryWalk
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;CarryJump
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Hold1
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Hold2

			Select char
			Case CHAR_SON,CHAR_TAI,CHAR_KNU,CHAR_SHA,CHAR_CHA,CHAR_ROU,CHAR_BLA,CHAR_BIG,CHAR_RAY,CHAR_CHO,CHAR_BEA,CHAR_BAR,CHAR_JET,CHAR_WAV,CHAR_STO,CHAR_TIA,CHAR_MPH,CHAR_TDL,CHAR_MKN,CHAR_MIG,CHAR_TIK,CHAR_INF:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir
			Case CHAR_SIL,CHAR_MAR:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir
			Case CHAR_CRE:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
			Case CHAR_AMY:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
			Case CHAR_OME,CHAR_ESP,CHAR_NAC,CHAR_GME,CHAR_COM,CHAR_EGR,CHAR_SHD:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
			Case CHAR_GAM,CHAR_EME,CHAR_MET,CHAR_EGG,CHAR_BET,CHAR_MT3,CHAR_CHW,CHAR_TMH:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir
			End Select

			Select char
			Case CHAR_SON,CHAR_AMY,CHAR_SHA,CHAR_NAC,CHAR_BEA,CHAR_WAV,CHAR_EME,CHAR_MKN,CHAR_GME,CHAR_PRS,CHAR_INF,CHAR_CHO:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Kick
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir
			Case CHAR_KNU,CHAR_ROU,CHAR_BLA,CHAR_MAR,CHAR_RAY,CHAR_COM:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Kick
				ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir
			Case CHAR_TAI,CHAR_TDL:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Kick
				ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir
			Case CHAR_ESP,CHAR_CHA,CHAR_MIG,CHAR_JET,CHAR_HON,CHAR_MET,CHAR_MT3:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick
				ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir
			End Select

			Select char
			Case CHAR_KNU,CHAR_CHO,CHAR_TIK,CHAR_BAR,CHAR_STO,CHAR_HBO,CHAR_MKN,CHAR_COM:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
			Case CHAR_ROU,CHAR_TIA,CHAR_HON,CHAR_SHD,CHAR_GAM,CHAR_EME,CHAR_BET,CHAR_GME:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
			Case CHAR_OME:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch3
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir
			Case CHAR_VEC,CHAR_EGG,CHAR_TMH:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
			Case CHAR_BIG,CHAR_JET,CHAR_CHW,CHAR_AMY:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
			Case CHAR_MIG,CHAR_ESP:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir
			Case CHAR_WAV:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch1
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch1
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir
			End Select

			Select char
			Case CHAR_OME:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Glide2
			Case CHAR_VEC,CHAR_BIG:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir2
			Case CHAR_EME:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Kick2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Kick3
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir2
			Case CHAR_GME:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Kick3
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Glide2
			End Select

		End Select

	End Function

	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------

	Function ExtractAllCharacterAnimations_Chao(char,mode=0)

		b=1

		Select mode
		Case 2: ;bio menu
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Idle
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Walk
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Run
			ExtractAnimSeq(CharacterMesh,		0,	0) : b=b+9		;Exclamation
			ExtractAnimSeq(CharacterMesh,		0,	0) : b=b+17		;Questioning
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Dance
		Default:
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Idle
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Walk
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Run
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+9) : b=b+9		;Exclamation
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Questioning
			ExtractAnimSeq(CharacterMesh,		b,	-1+b+17) : b=b+17	;Dance
		End Select

	End Function

	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------

	Function ExtractAllCharacterAnimations_Froggy(char,mode=0)

		c=1

		Select mode
		Default:
			ExtractAnimSeq(CharacterMesh,		c,	-1+c+17) : c=c+17	;Idle
			ExtractAnimSeq(CharacterMesh,		c,	-1+c+17) : c=c+17	;Walk
		End Select

	End Function

	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------

	Function ExtractAllCharacterAnimations_PetChao(mesh)

		d=1

		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Idle
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Sit
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Walk
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Run
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;IdleAir
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;WalkAir
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;RunAir
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Crawl
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Trip
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Swim
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Drown
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Climb
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;Exclamation
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Questioning
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;ExclamationAir
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;QuestioningAir
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Dance
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;Eat
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Wait
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Lay
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Think
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Laugh
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Intimidate
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Hurt
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;Thrown
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Hug
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Embrace
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Sorry
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Shy
		If Menu\Stage=998 Or Menu\Stage=997 Then
		ExtractAnimSeq(mesh,				d,	-1+d+17) : d=d+17	;Win
		EndIf
		If Menu\Stage=997 Then
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;KickR
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;KickL
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;PunchR
		ExtractAnimSeq(mesh,				d,	-1+d+9) : d=d+9		;PunchL
		EndIf

	End Function

	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------

	Function ExtractAllCharacterAnimations_DealerChao(mesh)

		e=1

		ExtractAnimSeq(mesh,				e,	-1+e+17) : e=e+17	;Idle

	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D