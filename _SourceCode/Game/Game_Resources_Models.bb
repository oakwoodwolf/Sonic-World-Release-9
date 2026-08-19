


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/



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
		Case -5: CharacterMesh = LoadAnimMesh("Objects/Rings/RedRingStarMenu.b3d") : ExtractAllCharacterAnimations_Character(char,-2)
		Case -4: CharacterMesh = LoadAnimMesh("Objects/Rings/RubyMenu.b3d") : ExtractAllCharacterAnimations_Character(char,-2)
		Case -3: CharacterMesh = LoadAnimMesh("Objects/Rings/RedRingMenu.b3d") : ExtractAllCharacterAnimations_Character(char,-2)
		Case -1: CharacterMesh = LoadAnimMesh("Objects/Unknown.b3d") : ExtractAllCharacterAnimations_Character(char,-1)
		Case CHAR_CHE: CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Cheese(char,mode)
		Case CHAR_FRO: CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+".b3d") : ExtractAllCharacterAnimations_Froggy(char,mode)
		Default:
			If IsCharMod(char) Then
				
				
				
				Select mode
					Case 0
						If super=1 Then file$="super" Else file$="model"
						CharacterMesh = LoadAnimMesh("_Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/"+file$+Str(costume)+".b3d")
					Case 1
						CharacterMesh = LoadAnimMesh("_Mods/Characters/"+MODCHARS_PATH$(char-CHAR_MOD1+1)+"/model/bio"+Str(costume)+".b3d")
				End Select
				
			Else
				If super=0 Then
					Select mode 
						Case 1
							If (Not(FileType("Characters/"+ShortCharNames$(char,costume)+"/model/bio.b3d")=1)) Then 
								CharacterMesh = LoadAnimMesh("Objects/Empty.b3d")
							Else
								CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+"/model/bio.b3d")
							EndIf
						Default
							CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+"/model/model.b3d")
					End Select 
				Else
					CharacterMesh = LoadAnimMesh("Characters/"+ShortCharNames$(char,costume)+"/model/super.b3d")
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
		Case 1,3
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Idle
		Case 4: ;eggman
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Victory
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Idle
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Walk
			ExtractAnimSeq(CharacterMesh,		0,	0) : a=a+17			;Jog
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17)			;Sit
		Default:
			actualchar=char
			If IsCharMod(char) Then char=MODCHARS_TYPE(char-CHAR_MOD1+1)
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Idle
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+70) : a=a+70	;Wait
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Walk
			ExtractAnimSeq(CharacterMesh,		a,	-1+a+17) : a=a+17	;Jog
			Select actualchar
				Case CHAR_SHA,CHAR_SHN:
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
			
			
			
			
			If IsCharMod(actualchar) Then
				If MODCHARS_SPINS(actualchar-CHAR_MOD1+1)=0 Then
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Jump
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Charge
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Dash
				Else
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Jump
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Charge
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Dash
				EndIf
			Else
				If DoesCharSpin(actualchar) Then
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Jump
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Charge
					ExtractAnimSeq(CharacterMesh,	0,	0)	;Dash
				Else
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Jump
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Charge
					ExtractAnimSeq(CharacterMesh,		a,	-1+a+9) : a=a+9	;Dash
				EndIf
			EndIf
			
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
			
			
			
			
			
			Select actualchar
				Case CHAR_TAI,CHAR_GAM,CHAR_AMY,CHAR_CRE,CHAR_OME,CHAR_INF,CHAR_EGR,CHAR_SIL,CHAR_MET,CHAR_BLA:
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Fly
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Glide
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Climbidle
				Case CHAR_RAY
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	;Fly
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	;Glide
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Climbidle
				Case CHAR_ROU
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9			;Fly
					ExtractAnimSeq(CharacterMesh,	0,	0)		;Fly					;Glide
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17						;Climb
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17						;Climbidle
				Case CHAR_KNU
					ExtractAnimSeq(CharacterMesh,	0,	0)		;Fly
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;Glide
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17						;Climb
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17						;Climbidle
				Default:
					If IsCharMod(actualchar) Then
						Select MODCHARS_JUMPACTION(actualchar-CHAR_MOD1+1)
							Case JUMPACTION_FLY,JUMPACTION_LEVITATE,JUMPACTION_HOVER
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Fly
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Glide
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Climbidle
							Case JUMPACTION_GLIDE
								ExtractAnimSeq(CharacterMesh,	0,	0)		;Fly
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;Glide
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17						;Climb
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17						;Climbidle
							Case JUMPACTION_SOAR
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	;Fly
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	;Glide
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Climbidle
							Default
								ExtractAnimSeq(CharacterMesh,	0,	0)		;Fly
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Glide
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Climbidle
						End Select
					Else
						
						ExtractAnimSeq(CharacterMesh,	0,	0)		;Fly
						ExtractAnimSeq(CharacterMesh,	0,	0)					;Glide
						ExtractAnimSeq(CharacterMesh,	0,	0)					;Climb
						ExtractAnimSeq(CharacterMesh,	0,	0)					;Climbidle
					EndIf
					
			End Select
			
			
			
			Select actualchar
				Case CHAR_SON,CHAR_SHA,CHAR_SHN,CHAR_ESP,CHAR_BLA,CHAR_MET,CHAR_MIG
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 3
				Default:
					If IsCharMod(actualchar) Then
						Select MODCHARS_JUMPACTIONHOME(actualchar-CHAR_MOD1+1)
							Case 0
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 1
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 2
								ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 3
							Case 1
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 1
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 2
								ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Homing 3
						End Select
					Else
						ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 1
						ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 2
						ExtractAnimSeq(CharacterMesh,	0,	0)					;Homing 3
					EndIf
			End Select
			
			Select char
				Case CHAR_TAI
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir2
				Case CHAR_ESP
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;ThrowAir
					ExtractAnimSeq(CharacterMesh,	0,	0)		;ThrowAir2
				Case CHAR_CRE,CHAR_SHN	
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	        ;Throw2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	 	;ThrowAir
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	 	;ThrowAir2
				Case CHAR_GAM
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Throw
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	        ;Throw2
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
				Case CHAR_INF
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;Throw
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Throw2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;ThrowAir
					ExtractAnimSeq(CharacterMesh,	0,	0)
				Case CHAR_OME
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;Throw
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
				Case CHAR_MIG,CHAR_SIL,CHAR_GAM
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9	;Kick
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
					ExtractAnimSeq(CharacterMesh,	0,	0)			;KickAir	
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				Case CHAR_KNU
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Kick
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir	
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				Case CHAR_BLA
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+33) : a=a+33		;Kick
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9
					
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;KickAir	
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				Case CHAR_TAI
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17	;Kick
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir	
				Case CHAR_ROU
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;KickAir
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;KickAir2
				Case CHAR_OME
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;Kick
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17					;Kick2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17					;Kick3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;KickAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
				Default
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Kick2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;KickAir2
			End Select 
			
			
			
			
			
			Select char
				Case CHAR_ROU
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;PunchAir2
				Case CHAR_SHA,CHAR_OME:
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch3
					ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
				Case CHAR_SIL
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	0,	0)		;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9						;PunchAir
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9						;PunchAir2
				Case CHAR_SHN
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9				;PunchAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
				Case CHAR_KNU
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;PunchAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
				Case CHAR_MET
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9					;PunchAir
					ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir2
				Case CHAR_RAY:
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	0,	0)						;Punch2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
				Case CHAR_AMY:
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	0,	0)						;Punch2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;PunchAir2
				Case CHAR_ESP:
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch1
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+9) : a=a+9		;Punch2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
					ExtractAnimSeq(CharacterMesh,	0,	0)					;PunchAir
				Case CHAR_MIG:
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch1
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch2
					ExtractAnimSeq(CharacterMesh,	0,	0)					;Punch3
					ExtractAnimSeq(CharacterMesh,	a,	-1+a+17) : a=a+17		;PunchAir
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
	;-----------------------------------------------------------------------------------------------------------------------
	;-----------------------------------------------------------------------------------------------------------------------

	Function ExtractAllCharacterAnimations_DealerChao(mesh)

		e=1

		ExtractAnimSeq(mesh,				e,	-1+e+17) : e=e+17	;Idle

	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D