
Dim Player_Speed#(CHAR_NONMODPLAYABLECOUNT)
Dim Player_JumpHeight#(CHAR_NONMODPLAYABLECOUNT)
Dim Player_ScaleFactor#(CHAR_NONMODPLAYABLECOUNT)

Dim MODCHARS_FOUND(MODCHAR_AMOUNT)
Dim MODCHARS_PATH$(MODCHAR_AMOUNT)
Dim MODCHARS_TYPE(MODCHAR_AMOUNT)
Dim MODCHARS_BIGHOLD(MODCHAR_AMOUNT)
Dim MODCHARS_SKATES(MODCHAR_AMOUNT)
Dim MODCHARS_NAME$(MODCHAR_AMOUNT)
Dim MODCHARS_NAME2$(MODCHAR_AMOUNT)
Dim MODCHARS_SPEED#(MODCHAR_AMOUNT)
Dim MODCHARS_JUMP#(MODCHAR_AMOUNT)
Dim MODCHARS_DROWN(MODCHAR_AMOUNT)
Dim MODCHARS_SCALE#(MODCHAR_AMOUNT)
Dim MODCHARS_JUMPSOUND(MODCHAR_AMOUNT)
Dim MODCHARS_DJUMPSOUND(MODCHAR_AMOUNT)
Dim MODCHARS_FOOTSTEP(MODCHAR_AMOUNT,6)
Dim MODCHARS_JUMPACTION(MODCHAR_AMOUNT)
Dim MODCHARS_JUMPACTIONTIP$(MODCHAR_AMOUNT)
Dim MODCHARS_JUMPACTIONHOLD(MODCHAR_AMOUNT)
Dim MODCHARS_JUMPACTIONHOME(MODCHAR_AMOUNT)
Dim MODCHARS_SPINS(MODCHAR_AMOUNT)
Dim MODCHARS_COSTUMES(MODCHAR_AMOUNT)


Const MOD_WALK=1
Const MOD_JOG=2
Const MOD_RUN=3
Const MOD_MACHRUN=4
Const MOD_DRIFT=5
Const MOD_LAND=6





	
	



Const JUMPACTION_DASH = 1
Const JUMPACTION_DOUBLE=2
Const JUMPACTION_FLY=3
Const JUMPACTION_GLIDE=4
Const JUMPACTION_THWOK=5
Const JUMPACTION_LEVITATE=6
Const JUMPACTION_HOVER=7
Const JUMPACTION_SOAR=8
	

Function IsCharMod(charno)
	If charno>=CHAR_MOD1 And charno<=CHAR_PLAYABLECOUNT Then Return True Else Return False
End Function

Function CharModHasSuper(charno)
	If FileType("_Mods/Characters/"+MODCHARS_PATH$(charno-CHAR_MOD1+1)+"/model/super1.b3d")=1 Then Return True Else Return False
End Function

Global MODCHARNO
Function LoadMods_Characters()
	For i=1 To MODCHAR_AMOUNT
		MODCHARS_FOUND(i)=0
	Next
	
	If FileType("_Mods/Characters/Characters.xml") Then mode=1 Else mode=0
	
	Select mode
		Case 0
			CharDir=ReadDir("_Mods/Characters/")
			file$=""
			Repeat 
				file$=NextFile$(CharDir)
				If (Not(file$="." Or file$=".." Or file$="" Or Right$(file,4)=".zip"  Or Right$(file,4)=".rar")) Then
					If FileType("_Mods/Characters/"+file$) = 2 Then
						
						MODCHARNO=MODCHARNO+1
								
								If MODCHARNO <= MODCHAR_AMOUNT And MODCHARNO > 0 Then
									MODCHARS_PATH$(MODCHARNO) = file$
									If Not(FileType("_Mods/Characters/"+MODCHARS_PATH$(MODCHARNO)+"/Character.xml")=1) Then
										MODCHARS_FOUND(MODCHARNO)=0
										LoadMods_DisableCharacter(MODCHARNO)
									Else
										MODCHARS_FOUND(MODCHARNO)=1
										LoadMods_Character(MODCHARNO)
									EndIf
								EndIf
								
					EndIf
				EndIf
			Until file$=""
			
			CloseDir CharDir
		Case 1
			ListRoot = xmlLoad("_Mods/Characters/Characters.xml")
			If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
			For i=1 To xmlNodeChildCount(ListRoot)
				Child = xmlNodeChild(ListRoot, i)
				Select xmlNodeNameGet$(Child)
					Case "char":
						CHARNO = xmlNodeAttributeValueGet(Child, "slot")
						If CHARNO <= MODCHAR_AMOUNT And CHARNO > 0 Then
							MODCHARS_PATH$(CHARNO) = xmlNodeAttributeValueGet(Child, "folder")
							If Not(FileType("_Mods/Characters/"+MODCHARS_PATH$(CHARNO)+"/Character.xml")=1) Then
								MODCHARS_FOUND(CHARNO)=0
								LoadMods_DisableCharacter(CHARNO)
							Else
								MODCHARS_FOUND(CHARNO)=1
								LoadMods_Character(CHARNO)
							EndIf
						EndIf
				End Select
			Next
			xmlNodeDelete(ListRoot)
	End Select

	For i=1 To MODCHAR_AMOUNT
		UNLOCKEDCHAR[i+CHAR_MOD1-1]=1
		If MODCHARS_FOUND(i)=0 Then LoadMods_DisableCharacter(i)
	Next
End Function

Function LoadMods_DisableCharacter(charno)
	UNLOCKEDCHAR[charno+CHAR_MOD1-1]=0
	TOUNLOCKCHAR[charno+CHAR_MOD1-1]=-1
End Function
Function LoadCharacterStuff()
	
	For i = 1 To CHAR_NONMODPLAYABLECOUNT
		
		If (Not(FileType("Characters/"+ShortCharNames$(i,1)+"/character.xml"))) Then 
			pathtoxml$="Characters/son/character.xml"
		Else
			pathtoxml$="Characters/"+ShortCharNames$(i,1)+"/character.xml"
		EndIf
		
		charfile = xmlLoad(pathtoxml$)
		
		For x=1 To xmlNodeChildCount(charfile)
			Child = xmlNodeChild(charfile, x)
			Select xmlNodeNameGet$(Child)
				Case "cardcolor":
					Interface_Card2_R[i]=xmlNodeAttributeValueGet(Child, "r")
					Interface_Card2_G[i]=xmlNodeAttributeValueGet(Child, "g")
					Interface_Card2_B[i]=xmlNodeAttributeValueGet(Child, "b")
				Case "namecolor":
					Interface_TextNames_R[i]=xmlNodeAttributeValueGet(Child, "r")
					Interface_TextNames_G[i]=xmlNodeAttributeValueGet(Child, "g")
					Interface_TextNames_B[i]=xmlNodeAttributeValueGet(Child, "b")
				Case "effectcolor","trailcolor"::
					Interface_Circle_R[i]=xmlNodeAttributeValueGet(Child, "r") 
					Interface_Circle_G[i]=xmlNodeAttributeValueGet(Child, "g")
					Interface_Circle_B[i]=xmlNodeAttributeValueGet(Child, "b")
				Case "supereffectcolor","supertrailcolor":
					Interface_SCircle_R[i]=xmlNodeAttributeValueGet(Child, "r") 
					Interface_SCircle_G[i]=xmlNodeAttributeValueGet(Child, "g")
					Interface_SCircle_B[i]=xmlNodeAttributeValueGet(Child, "b")
					If Interface_SCircle_R[i]=0 Then 
						Interface_SCircle_R[i]=255
						Interface_SCircle_G[i]=255
						Interface_SCircle_B[i]=0
					EndIf
					
				Case "max":
					Player_Speed#(i) = Float(xmlNodeAttributeValueGet(Child, "speed"))
				Case "jump":
					Player_JumpHeight#(i) = Float(xmlNodeAttributeValueGet(Child, "strength"))
					
				Case "scale":
					Player_ScaleFactor#(i) = Float(xmlNodeAttributeValueGet(Child, "factor"))
			End Select
		Next
		xmlNodeDelete(charfile)
	Next
	
	
End Function
Function LoadMods_Character(charno)
	actualcharno = CHAR_MOD1+charno-1
	ListRoot = xmlLoad("_Mods/Characters/"+MODCHARS_PATH$(charno)+"/Character.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
	For i=1 To xmlNodeChildCount(ListRoot)
		Child = xmlNodeChild(ListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "skill":
				MODCHARS_TYPE(charno) = xmlNodeAttributeValueGet(Child, "type")
				If MODCHARS_TYPE(charno)<0 Or MODCHARS_TYPE(charno)>CHAR_NONMODPLAYABLECOUNT Then MODCHARS_TYPE(charno)=1
			Case "char":
				MODCHARS_COSTUMES(charno) = xmlNodeAttributeValueGet(Child, "costumes")
				If MODCHARS_COSTUMES(charno) = 0 Then MODCHARS_COSTUMES(charno) = 1
				MODCHARS_BIGHOLD(charno) = xmlNodeAttributeValueGet(Child, "bighold")
				MODCHARS_SPINS(charno) = xmlNodeAttributeValueGet(Child, "spins")
				
				MODCHARS_SKATES(charno) = xmlNodeAttributeValueGet(Child, "skates")
			Case "sounds"
				MODCHARS_FOOTSTEP(charno,MOD_WALK) = xmlNodeAttributeValueGet(Child, "walk") 
				MODCHARS_FOOTSTEP(charno,MOD_JOG) = xmlNodeAttributeValueGet(Child, "jog") 
				MODCHARS_FOOTSTEP(charno,MOD_RUN) = xmlNodeAttributeValueGet(Child, "run") 
				MODCHARS_FOOTSTEP(charno,MOD_MACHRUN) = xmlNodeAttributeValueGet(Child, "machrun") 
				MODCHARS_FOOTSTEP(charno,MOD_DRIFT) = xmlNodeAttributeValueGet(Child, "drift") 
				MODCHARS_FOOTSTEP(charno,MOD_LAND) = xmlNodeAttributeValueGet(Child, "land") 
				
			Case "alignment":
				CHARSIDES(InterfaceChar(actualcharno)) = xmlNodeAttributeValueGet(Child, "is")
				If Not(CHARSIDES(InterfaceChar(actualcharno))=1 Or CHARSIDES(InterfaceChar(actualcharno))=2) Then CHARSIDES(InterfaceChar(actualcharno))=1
				
				
			Case "name":
				MODCHARS_NAME$(charno) = Upper$(xmlNodeAttributeValueGet(Child, "line1"))
				MODCHARS_NAME2$(charno) = Upper$(xmlNodeAttributeValueGet(Child, "line2"))
			Case "cardcolor":
				Interface_Card2_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_Card2_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_Card2_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "namecolor":
				Interface_TextNames_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_TextNames_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_TextNames_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "effectcolor":
				Interface_Circle_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_Circle_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_Circle_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "supereffectcolor":
				Interface_SCircle_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_SCircle_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_SCircle_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
			Case "livescolor":
				Interface_Lives_R[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "r") : Interface_Lives_G[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "g") : Interface_Lives_B[InterfaceChar(actualcharno)]=xmlNodeAttributeValueGet(Child, "b")
				
			Case "max":
				MODCHARS_SPEED#(charno) = Float(xmlNodeAttributeValueGet(Child, "speed"))
				If MODCHARS_SPEED#(charno) < 1.0 Then MODCHARS_SPEED#(charno) = 1.0
				If MODCHARS_SPEED#(charno) > 99.0 Then MODCHARS_SPEED#(charno) = 99.0
			Case "jump":
				MODCHARS_JUMP#(charno) = Float(xmlNodeAttributeValueGet(Child, "strength"))
				If MODCHARS_JUMP#(charno) < 1.0 Then MODCHARS_JUMP#(charno) = 1.0
				If MODCHARS_JUMP#(charno) > 99.0 Then MODCHARS_JUMP#(charno) = 99.0
				MODCHARS_JUMPACTION(charno) = xmlNodeAttributeValueGet(Child, "action")
				MODCHARS_JUMPACTIONTIP$(charno) = xmlNodeAttributeValueGet(Child, "actiontip")
				MODCHARS_JUMPACTIONHOLD(charno) = xmlNodeAttributeValueGet(Child, "actionhold")
				MODCHARS_JUMPACTIONHOME(charno) = xmlNodeAttributeValueGet(Child, "actionhome")
				If FileType("_Mods/Characters/"+MODCHARS_PATH$(charno)+"/Sounds/Jump.ogg")=1 Then MODCHARS_JUMPSOUND(charno)=1
				If FileType("_Mods/Characters/"+MODCHARS_PATH$(charno)+"/Sounds/DoubleJump.ogg")=1 Then MODCHARS_DJUMPSOUND(charno)=1
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
		LoadSmartFastImage("_Mods/Characters/"+MODCHARS_PATH$(charno)+"/interface/Head.png", x, 64, 70, 0, 1, 2, 2)
	EndIf
End Function
Function LoadMods_Character_InterfaceCharacter(charno, x)
	If Menu\Settings\Mods#>0 Then
		LoadSmartFastImage("_Mods/Characters/"+MODCHARS_PATH$(charno)+"/interface/Character.png", x, 3072/12.0, 840/3.0, 0, 1, 6, 6)
	EndIf
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Dim MODVOICES_FOUND(CHAR_NONMODPLAYABLECOUNT)
Dim MODVOICES_PATH$(CHAR_NONMODPLAYABLECOUNT)

Function LoadMods_Voices()
	For i=1 To CHAR_NONMODPLAYABLECOUNT
		MODVOICES_FOUND(i)=0
	Next

	ListRoot = xmlLoad("_Mods/Voices/Voices.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing xml")
	For i=1 To xmlNodeChildCount(ListRoot)
		Child = xmlNodeChild(ListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "char":
				For x = 1 To CHAR_NONMODPLAYABLECOUNT
					If xmlNodeAttributeValueGet(Child, "name") = Lower$(ShortCharNames$(x,1)) Then charno=x
				Next
				If charno <= CHAR_NONMODPLAYABLECOUNT And charno > 0 Then
					MODVOICES_PATH$(charno) = xmlNodeAttributeValueGet(Child, "folder")
					If Not(FileType("_Mods/Voices/"+MODVOICES_PATH$(charno)+"/jump1.ogg")=1) Then
						MODVOICES_FOUND(charno)=0
					Else
						MODVOICES_FOUND(charno)=1
					EndIf
				EndIf
		End Select
	Next
	xmlNodeDelete(ListRoot)
End Function