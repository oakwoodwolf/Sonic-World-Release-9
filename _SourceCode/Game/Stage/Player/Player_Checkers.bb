Function Player_CanHomingAttack(p.tPlayer)
	Select p\Character
		Case CHAR_SON,CHAR_SHA
			Return True
		Default
			Return False
	End Select
End Function 