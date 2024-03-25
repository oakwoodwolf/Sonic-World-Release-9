
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
	Const CHAR_KNU			= 4
	Const CHAR_AMY			= 5
	Const CHAR_SHA			= 3
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
	Const CHAR_PLAYABLECOUNT= CHAR_NONMODPLAYABLECOUNT+26

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
			Case CHAR_CHC: CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Chao(char,mode)
			Case CHAR_FRO: CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Froggy(char,mode)
			Case CHAR_CHE : CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Cheese(char,mode)
			Default:
				If IsCharMod(char) Then
					If super=0 Then
					CharacterMesh = LoadAnimMesh("Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/"+MODCHARS_MODEL$(char-CHAR_MOD1+1)) : ExtractAllCharacterAnimations_Character(char,mode)
					Else
					CharacterMesh = LoadAnimMesh("Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/"+MODCHARS_SMODEL$(char-CHAR_MOD1+1)) : ExtractAllCharacterAnimations_Character(char,mode)
					EndIf
				Else
					If char>900 Then
						If super=0 Then
						CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(InterfaceChar(char),costume+1)+"/model.b3d") : ExtractAllCharacterAnimations_Character(char,mode)
						Else
						CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(InterfaceChar(char),costume+1)+"/super.b3d") : ExtractAllCharacterAnimations_Character(char,mode)
						EndIf
					Else
						If super=0 Then
							Select mode 
								Case 1
									CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+"/bio.b3d") : ExtractAllCharacterAnimations_Character(char,mode)
								Default
									CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+"/model.b3d") : ExtractAllCharacterAnimations_Character(char,mode)
							End Select 
						Else
							CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+"/super.b3d") : ExtractAllCharacterAnimations_Character(char,mode)
						EndIf
					EndIf
				EndIf
		End Select
		MoveEntity(CharacterMesh),0,-99999,0 : HideEntity(CharacterMesh)
	End Function
Function DeleteCharacterMesh()
		FreeEntity CharacterMesh
		CharacterMesh = CreatePivot()
	End Function

	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------

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
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;bio
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
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Bio
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Idle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+70) : a=a+70	;Wait
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Walk
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Jog
			Select actualchar
			Case CHAR_SHA:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;Run
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;MachRun
			Default:
				If IsCharMod(actualchar) Then
					If MODCHARS_SKATES(actualchar-CHAR_MOD1+1)>0 Then
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;Run
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33	;MachRun
					Else
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Run
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;MachRun
					EndIf
				Else
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Run
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;MachRun
				EndIf
			End Select
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Spin
			Select char
				Case CHAR_EGR,CHAR_GAM,CHAR_AMY,CHAR_BET,CHAR_ROU,CHAR_ESP:
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Jump
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Charge
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Dash
				Default:
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Jump
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Charge
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Dash
			End Select 
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Fall
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;FallFast
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Forward
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Up
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Float
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Brake
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Hurt
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+1) : a=a+1		;Dead
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+1) : a=a+1		;DeadFall
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Grind
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;GrindSwitch
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;GrindFast
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;SkyDive
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;SkyDiveFast
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;CarryIdle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;CarryWalk
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;CarryJump
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Hold1
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Hold2
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;DriftL
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;DriftR
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Stomp
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Land
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Transform
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+33) : a=a+33	;Trick
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Vehicle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;Board
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;BoardFall
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;BoardL
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;BoardR
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+140) : a=a+140	;Victory
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9		;VictoryLoop

			
			
			Select char
			Case CHAR_TAI,CHAR_ROU,CHAR_CRE,CHAR_SIL,CHAR_CHA,CHAR_TDL,CHAR_TIK,CHAR_RAY,CHAR_JET,CHAR_WAV,CHAR_TIA,CHAR_MPH,CHAR_OME,CHAR_EME,CHAR_MT3,CHAR_GME,CHAR_EGR,CHAR_BEA,CHAR_INF:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Fly
			Case CHAR_BIG,CHAR_GAM,CHAR_BET:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Fly
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Fly
			End Select
			
			Select char
				Case CHAR_SON,CHAR_SHA,CHAR_ESP
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 3
				Default:
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 1
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 3
			End Select
			
			Select char
			Case CHAR_TAI
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir2
			Case CHAR_CRE
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	        ;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	 	;ThrowAir
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	 	;ThrowAir2
			Case CHAR_EGR
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir2
			Case CHAR_SHA
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;ThrowAir
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir2
			Default:
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw
				ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir
				ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir2
			End Select
			
			Select char
				Case CHAR_SON
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Kick
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir	
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				Case CHAR_TAI
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Kick
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir	
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;KickAir2
				Default
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir2
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
			Case CHAR_OME,CHAR_SHA:
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
				ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch3
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir
				ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
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

Function ExtractAllCharacterAnimations_Cheese(char,mode=0)

		f=1

		Select mode
		Default:
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+17) : f=f+17	;Idle
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+17) : f=f+17	;Move
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+70) : f=f+70	;Wait
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+9) : f=f+9	;Attack
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+9) : f=f+9	;Spin
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+140) : f=f+140	;Victory
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+9) : f=f+9	;VictoryLoop
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+9) : f=f+9	;Shield
			ExtractAnimSeq(CharacterMesh,		f,	-1+f+9) : f=f+9	;Hurt
		End Select

	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D