Function Player_IsARobot(p.tPlayer)
	Select p\Character
		Case CHAR_OME,CHAR_HBO,CHAR_GAM,CHAR_EME,CHAR_MET,CHAR_TDL,CHAR_MKN,CHAR_BET,CHAR_MT3,CHAR_GME,CHAR_EGR:
			Return True
		Default:
			Return False
	End Select
End Function

Function Player_IsPowerChar(p.tPlayer)
	Select p\Character
		Case CHAR_KNU,CHAR_OME,CHAR_BIG,CHAR_VEC,CHAR_MAR,CHAR_MKN,CHAR_CHO,CHAR_BAR,CHAR_STO,CHAR_TIA,CHAR_MPH,CHAR_HBO,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_COM:
			Return True
		Default:
			Return False
	End Select
End Function

Function Player_HasSuperModel(char)
	Select char
		Case CHAR_SON,CHAR_TAI,CHAR_KNU,CHAR_SHA,CHAR_SIL,CHAR_BLA,CHAR_RAY,CHAR_INF,CHAR_MIG:
			Return True
		Default:
			Return False
	End Select
End Function

Function GetCharSpeed#(char)
	
				If IsCharMod(char) Then
					Return (MODCHARS_SPEED#(char-CHAR_MOD1+1))
				Else
					Return Player_Speed#(char)
				EndIf
				
End Function

Function GetCharJumpStrength#(char)
	
	If IsCharMod(char) Then
		Return (MODCHARS_JUMP#(char-CHAR_MOD1+1))
	Else
		Return Player_JumpHeight#(char)
	EndIf
	
End Function

Function GetCharScaleFactor#(char)
	
	
	If IsCharMod(char) Then
		Return (MODCHARS_SCALE#(char-CHAR_MOD1+1))
	Else
		Return Player_ScaleFactor#(char)
	EndIf
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D