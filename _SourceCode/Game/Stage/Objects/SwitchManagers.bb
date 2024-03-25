
	Function Object_SwitchManager_FindSwitchAndAssign(o.tObject)
		If Object_WhetherHasSwitches(o) Then
			o\Switch\SwitchFound=True
			For i=0 To 2
				If o\Switch\SwitchNo[i]>0 Then
					switchfound=False
					For s.tSwitchManager=Each tSwitchManager
						If switchfound=False And s\No=o\Switch\SwitchNo[i] Then
							Select i
							Case 0: o\Switch\s1=s
							Case 1: o\Switch\s2=s
							Case 2: o\Switch\s3=s
							End Select
							switchfound=True
						EndIf
					Next
					If switchfound=False Then o\Switch\SwitchFound=False
				EndIf
			Next
		EndIf
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tSwitchManager
		Field No
		Field Active
		Field InitialStatus
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Object_SwitchManager_Create.tSwitchManager(no, status)
		alreadyexists=0

		For s2.tSwitchManager = Each tSwitchManager
			If s2\No=no Then alreadyexists=1
		Next

		Select alreadyexists
			Case 0:
				s.tSwitchManager = New tSwitchManager
				s\No=no
				s\InitialStatus=status : s\Active=status
				Return s
			Default:
				For s2.tSwitchManager = Each tSwitchManager
					If s2\No=no Then Return s2
				Next
		End Select		
	End Function
	
	; =========================================================================================================

	Function Object_SwitchManager_PerObjectUpdate(o.tObject)
		If o\Switch\SwitchFound Then
			o\Switch\SwitchOn=0
			If o\Switch\SwitchNo[0]>0 Then
				If o\Switch\s1\Active=1 Then o\Switch\SwitchOn=1
			EndIf
			If o\Switch\SwitchNo[1]>0 Then
				If o\Switch\s2\Active=1 Then o\Switch\SwitchOn=1
			EndIf
			If o\Switch\SwitchNo[2]>0 Then
				If o\Switch\s3\Active=1 Then o\Switch\SwitchOn=1
			EndIf
		EndIf
	End Function

	Function Object_WhetherHasSwitches(o.tObject)
		For i=0 To 2
			If o\Switch\SwitchNo[i]>0 Then Return True
		Next
		Return False
	End Function