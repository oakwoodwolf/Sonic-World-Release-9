
	Dim MODCHARS_FOUND(MODCHAR_AMOUNT)
	Dim MODCHARS_PATH$(MODCHAR_AMOUNT)
	Dim MODCHARS_TYPE(MODCHAR_AMOUNT)
	Dim MODCHARS_MODEL$(MODCHAR_AMOUNT)
	Dim MODCHARS_SMODEL$(MODCHAR_AMOUNT)

	Dim MODCHARS_BIGHOLD(MODCHAR_AMOUNT)
	Dim MODCHARS_METALSTEPS(MODCHAR_AMOUNT)
	Dim MODCHARS_SKATES(MODCHAR_AMOUNT)
	Dim MODCHARS_HOVERRUNS(MODCHAR_AMOUNT)
	Dim MODCHARS_MOTORRUNS(MODCHAR_AMOUNT)
	Dim MODCHARS_TAILRUNS(MODCHAR_AMOUNT)
	Dim MODCHARS_NORUNSTEPS(MODCHAR_AMOUNT)
	Dim MODCHARS_NAME$(MODCHAR_AMOUNT)
	Dim MODCHARS_SPEED#(MODCHAR_AMOUNT)
	Dim MODCHARS_JUMP#(MODCHAR_AMOUNT)
	Dim MODCHARS_DROWN(MODCHAR_AMOUNT)
	Dim MODCHARS_SCALE#(MODCHAR_AMOUNT)
	Dim MODCHARS_JUMPSOUND(MODCHAR_AMOUNT)

Function IsCharMod(charno)
	If charno>=CHAR_MOD1 And charno<=CHAR_PLAYABLECOUNT Then Return True Else Return False
End Function

Function CharModHasSuper(charno)
	If Len(MODCHARS_SMODEL$(charno-CHAR_MOD1+1))>0 Then Return True Else Return False
End Function


Function LoadMods_Characters()
	For i=1 To MODCHAR_AMOUNT
		MODCHARS_FOUND(i)=0
	Next

	ListRoot = xmlLoad("Mods/Characters/Characters.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
	For i=1 To xmlNodeChildCount(ListRoot)
		Child = xmlNodeChild(ListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "char":
				charno = xmlNodeAttributeValueGet(Child, "slot")
				If charno <= MODCHAR_AMOUNT And charno > 0 Then
					MODCHARS_PATH$(charno) = xmlNodeAttributeValueGet(Child, "folder")
					If Not(FileType("Mods/Characters/"+MODCHARS_PATH$(charno)+"/Character.xml")=1) Then
						MODCHARS_FOUND(charno)=0
						LoadMods_DisableCharacter(charno)
					Else
						MODCHARS_FOUND(charno)=1
						LoadMods_Character(charno)
					EndIf
				EndIf
		End Select
	Next
	xmlNodeDelete(ListRoot)

	For i=1 To MODCHAR_AMOUNT
		UNLOCKEDCHAR[i+CHAR_MOD1-1]=1
		If MODCHARS_FOUND(i)=0 Then LoadMods_DisableCharacter(i)
	Next
End Function

Function LoadMods_DisableCharacter(charno)
	UNLOCKEDCHAR[charno+CHAR_MOD1-1]=0
	TOUNLOCKCHAR[charno+CHAR_MOD1-1]=-1
End Function

Function LoadMods_Character(charno)
	actualcharno = CHAR_MOD1+charno-1
	ListRoot = xmlLoad("Mods/Characters/"+MODCHARS_PATH$(charno)+"/Character.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
	For i=1 To xmlNodeChildCount(ListRoot)
		Child = xmlNodeChild(ListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "skill":
				MODCHARS_TYPE(charno) = xmlNodeAttributeValueGet(Child, "type")
				If MODCHARS_TYPE(charno)<0 Or MODCHARS_TYPE(charno)>CHAR_NONMODPLAYABLECOUNT Then MODCHARS_TYPE(charno)=1
			Case "char":
				MODCHARS_MODEL$(charno) = xmlNodeAttributeValueGet(Child, "model")
				MODCHARS_SMODEL$(charno) = xmlNodeAttributeValueGet(Child, "smodel")
				MODCHARS_BIGHOLD(charno) = xmlNodeAttributeValueGet(Child, "bighold")
				MODCHARS_METALSTEPS(charno) = xmlNodeAttributeValueGet(Child, "metalsteps")
				MODCHARS_SKATES(charno) = xmlNodeAttributeValueGet(Child, "skates")
				MODCHARS_HOVERRUNS(charno) = xmlNodeAttributeValueGet(Child, "hoverruns")
				MODCHARS_MOTORRUNS(charno) = xmlNodeAttributeValueGet(Child, "motorruns")
				MODCHARS_TAILRUNS(charno) = xmlNodeAttributeValueGet(Child, "tailruns")
				MODCHARS_NORUNSTEPS(charno) = xmlNodeAttributeValueGet(Child, "norunsteps")
			Case "name":
				MODCHARS_NAME$(charno) = Upper$(xmlNodeAttributeValueGet(Child, "is"))
			Case "alignment":
				CHARSIDES(InterfaceChar(actualcharno)) = xmlNodeAttributeValueGet(Child, "is")
				If Not(CHARSIDES(InterfaceChar(actualcharno))=1 Or CHARSIDES(InterfaceChar(actualcharno))=2) Then CHARSIDES(InterfaceChar(actualcharno))=1
			Case "cardcolor":
				Interface_Card2_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_Card2_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_Card2_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "namecolor":
				Interface_TextNames_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_TextNames_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_TextNames_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "circlecolor":
				Interface_Circle_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_Circle_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_Circle_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "livescolor":
				Interface_Lives_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_Lives_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_Lives_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "max":
				MODCHARS_SPEED#(charno) = Float(xmlNodeAttributeValueGet(Child, "speed"))
				If MODCHARS_SPEED#(charno) < 3.0 Then MODCHARS_SPEED#(charno) = 3.0
				If MODCHARS_SPEED#(charno) > 6.0 Then MODCHARS_SPEED#(charno) = 6.0
			Case "jump":
				MODCHARS_JUMP#(charno) = Float(xmlNodeAttributeValueGet(Child, "strength"))
				If MODCHARS_JUMP#(charno) < 5.0 Then MODCHARS_JUMP#(charno) = 5.0
				If MODCHARS_JUMP#(charno) > 8.0 Then MODCHARS_JUMP#(charno) = 8.0

				If FileType("Mods/Characters/"+MODCHARS_PATH$(charno)+"/Sounds/Jump.ogg")=1 Then MODCHARS_JUMPSOUND(charno)=1
			Case "drown":
				MODCHARS_DROWN(charno) = xmlNodeAttributeValueGet(Child, "secs")
				If Not(MODCHARS_DROWN(charno)=-1) Then
					If MODCHARS_DROWN(charno) < 15 Then MODCHARS_DROWN(charno) = 15
					If MODCHARS_DROWN(charno) > 70 Then MODCHARS_DROWN(charno) = 70
				EndIf
			Case "scale":
				MODCHARS_SCALE#(charno)=xmlNodeAttributeValueGet(Child, "factor")
				If MODCHARS_SCALE#(charno) < -5 Then MODCHARS_SCALE#(charno) = -5
				If MODCHARS_SCALE#(charno) > 20 Then MODCHARS_SCALE#(charno) = 20
		End Select
	Next
	xmlNodeDelete(ListRoot)
End Function

Function LoadMods_Character_InterfaceHead(charno, x)
	If Menu\Settings\Mods#>0 Then
		LoadSmartFastImage("Mods/Characters/"+MODCHARS_PATH$(charno)+"/interface/Head.png", x, 64, 70, 0, 1, 2, 2)
	EndIf
End Function
Function LoadMods_Character_InterfaceCharacter(charno, x)
	If Menu\Settings\Mods#>0 Then
		LoadSmartFastImage("Mods/Characters/"+MODCHARS_PATH$(charno)+"/interface/Character.png", x, 3072/12.0, 840/3.0, 0, 1, 6, 6)
	EndIf
End Function

Function LoadMods_Bio$(charno)
	ListRoot = xmlLoad("Mods/Characters/"+MODCHARS_PATH$(charno)+"/Character.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
	For i=1 To xmlNodeChildCount(ListRoot)
		Child = xmlNodeChild(ListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "bio":
				Return xmlNodeAttributeValueGet(Child, "is")
		End Select
	Next
	xmlNodeDelete(ListRoot)
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Dim MODVOICES_FOUND(CHAR_PLAYABLECOUNT)
Dim MODVOICES_PATH$(CHAR_PLAYABLECOUNT)

Function LoadMods_Voices()
	For i=1 To CHAR_PLAYABLECOUNT
		MODVOICES_FOUND(i)=0
	Next

	ListRoot = xmlLoad("Mods/Voices/Voices.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
	For i=1 To xmlNodeChildCount(ListRoot)
		Child = xmlNodeChild(ListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "char":
				charno = xmlNodeAttributeValueGet(Child, "no")
				If charno <= CHAR_PLAYABLECOUNT And charno > 0 Then
					MODVOICES_PATH$(charno) = xmlNodeAttributeValueGet(Child, "folder")
					If Not(FileType("Mods/Voices/"+MODVOICES_PATH$(charno)+"/jump1.ogg")=1) Then
						MODVOICES_FOUND(charno)=0
					Else
						MODVOICES_FOUND(charno)=1
					EndIf
				EndIf
		End Select
	Next
	xmlNodeDelete(ListRoot)
End Function