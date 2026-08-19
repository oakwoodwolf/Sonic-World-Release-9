
Function ReadAndFindTextExplanation$(textfilename$,itemno)

	xmlin = xmlLoad("Interface/Text/"+textfilename$+".xml")

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "item":
			If xmlNodeAttributeValueGet(child, "no")=itemno Then Return xmlNodeAttributeValueGet(child, "text")
	End Select

	Next

	xmlNodeDelete(xmlin)

End Function


	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function DrawNumber(Number%, x, y, ZeroPadding=0, Alignment=0)
		; Convert number to string
	Num$ = ZeroPadding$(Str$(Number%), ZeroPadding)
	
	If (Alignment=1) Then x = x-Len(Num$)*18*GAME_WINDOW_SCALE#
	
		; Go on and render text
	For i = 1 To Len(Num$)
		DrawImageEx INTERFACE(Interface_Numbers), x, y, Asc(Mid$(Num$, i, 1))-48
		x = x+23*GAME_WINDOW_SCALE#
	Next
End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function DrawBetterNumber(Number%, x, y, ZeroPadding=0, Alignment=0)
	If Number%>=100000000 Then
		DrawNumber(Number%, x+2.00*GAME_WINDOW_SCALE#-22.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=10000000 Then
		DrawNumber(Number%, x+1.75*GAME_WINDOW_SCALE#-17.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=1000000 Then
		DrawNumber(Number%, x+1.50*GAME_WINDOW_SCALE#-12.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=100000 Then
		DrawNumber(Number%, x+1.25*GAME_WINDOW_SCALE#-07.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=10000 Then
		DrawNumber(Number%, x+1.00*GAME_WINDOW_SCALE#-02.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=1000 Then
		DrawNumber(Number%, x+0.75*GAME_WINDOW_SCALE#+02.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=100 Then
		DrawNumber(Number%, x+0.50*GAME_WINDOW_SCALE#+07.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=10 Then
		DrawNumber(Number%, x+0.25*GAME_WINDOW_SCALE#+12.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	ElseIf Number%>=0 Then
		DrawNumber(Number%, x+0.00*GAME_WINDOW_SCALE#+17.5*Alignment*GAME_WINDOW_SCALE#, y, ZeroPadding, Alignment)
	EndIf
End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function DrawRealText(text$,inputx#,inputy#,font,alignment=0,end#=0,r=255,g=255,b=255,smaller#=0)
	textLength = Len(Text$)
	
	letterSpacing#=1
	
	Select font
		Case (Interface_Text_1),(Interface_Text2_1):						sizeMultiplier#=3.0/(14/2)
		Case (Interface_Text_2),(Interface_Text2_2):						sizeMultiplier#=3.4/(14/2)
		Case (Interface_Text_3),(Interface_Text2_3):						sizeMultiplier#=4.0/(14/2)
		Case (Interface_TextButtons_1),(Interface_TextButtons2_1):				sizeMultiplier#=6.0/(14/2)
		Case (Interface_TextButtons_2),(Interface_TextButtons2_2):				sizeMultiplier#=4.0/(14/2)
		Case (Interface_TextTitle_1),(Interface_TextTitle2_1),(Interface_TextTitleChao_1):	sizeMultiplier#=9.8/(14/2)
		Case (Interface_TextControls_1):							sizeMultiplier#=7.4/(14/2)
		Case (Interface_TextControls_2):							sizeMultiplier#=8.1/(14/2)
		Case (Interface_TextRecords_1),(Interface_TextRecords_2):				sizeMultiplier#=8.1/(14/2)
		Case (Interface_TextNames_1),(Interface_TextNames2_1):					sizeMultiplier#=9.4/(14/2)
		Case (Interface_TextOvers_1),(Interface_TextOvers_2):					sizeMultiplier#=10.6/(14/2)
		Case (Interface_TextCredits_1):								sizeMultiplier#=3.2/(14/2)
	End Select
	
	If smaller#<0 Then smaller#=0
	If smaller#>0 Then sizeMultiplier#=(sizeMultiplier#/smaller#)
	
	SetScale(GAME_WINDOW_SCALE#-(smaller#/2.65),GAME_WINDOW_SCALE#-(smaller#/2.65))
	
	Select font
		Case (Interface_Text_1),(Interface_Text2_1):						draworderlimit=2
		Case (Interface_Text_2),(Interface_Text2_2):						draworderlimit=2
		Case (Interface_Text_3),(Interface_Text2_3):						draworderlimit=2
		Case (Interface_TextTitle_1),(Interface_TextTitle2_1),(Interface_TextTitleChao_1):	draworderlimit=2
		Case (Interface_TextNames_1),(Interface_TextNames2_1):					draworderlimit=2
		Default:										draworderlimit=1
	End Select
	
	TotalWidth#=0
	
	For draworder=0 To draworderlimit
		x#=inputx# : y#=inputy#
		Select alignment
			Case 1: x#=x#-(TotalWidth#/2)*GAME_WINDOW_SCALE#
			Case 2: x#=x#-(TotalWidth#)*GAME_WINDOW_SCALE#
		End Select
		
		WidthSoFar#=0
		HeightSoFar#=0
		currentPosition#=-DrawRealText_ReturnLetterWidth(Asc(Mid$(Text$,1,1)))/2
		
		For letterNumber = 1 To textLength
			letter$ = Mid$(Text$,letterNumber,1)
			
			character = Asc(letter$)
			letterWidth#=DrawRealText_ReturnLetterWidth(character)
			character = character - 32
			If character=3 Then character=2
			
			Select draworder
				Case 0:
					If alignment>0 Then
						currentPosition#=TotalWidth#+sizeMultiplier#*letterSpacing#+sizeMultiplier#*letterWidth#/2
						TotalWidth#=currentPosition#+sizeMultiplier#*letterWidth#/2
					EndIf
				Default:
					currentPosition#=widthSoFar#+sizeMultiplier#*letterSpacing#+sizeMultiplier#*letterWidth#/2
					DrawRealText_DrawText draworder, font, x#+currentPosition#*GAME_WINDOW_SCALE#, y#+HeightSoFar#*GAME_WINDOW_SCALE#, character, r, g, b
					WidthSoFar#=currentPosition#+sizeMultiplier#*letterWidth#/2
					If center=False And End#<>0 And letter$=" " And (x#+WidthSoFar#*GAME_WINDOW_SCALE#-0>End-0 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-1>End-1 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-2>End-2 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-3>End-3 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-4>End-4 Or x#+WidthSoFar#*GAME_WINDOW_SCALE#-5>End-5) Then
						HeightSoFar#=HeightSoFar#+sizeMultiplier#*(14/2)*5
						WidthSoFar=0
					EndIf
			End Select
		Next
	Next
	
	SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
	
End Function

	;____________________________________________________________________________________________________________________________________________

Function DrawRealText_DrawText(draworder, font, x#, y#, character, r, g, b)
	Select font
		Case (Interface_Text_1):
			Select draworder
				Case 1: DrawImageEx INTERFACE(Interface_Text2_1), x#, y#, character
				Case 2: DrawImageEx INTERFACE(Interface_Text_1), x#, y#, character
			End Select
		Case (Interface_Text_2):
			Select draworder
				Case 1: DrawImageEx INTERFACE(Interface_Text2_2), x#, y#, character
				Case 2: DrawImageEx INTERFACE(Interface_Text_2), x#, y#, character
			End Select
		Case (Interface_Text_3):
			Select draworder
				Case 1: DrawImageEx INTERFACE(Interface_Text2_3), x#, y#, character
				Case 2: DrawImageEx INTERFACE(Interface_Text_3), x#, y#, character
			End Select
		Case (Interface_TextTitle_1):
			Select draworder
				Case 1: SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextTitle2_1), x#, y#, character : SetColor(255,255,255)
				Case 2: DrawImageEx INTERFACE(Interface_TextTitle_1), x#, y#, character
			End Select
		Case (Interface_TextTitleChao_1):
			Select draworder
				Case 1: SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextTitle2_1), x#, y#, character : SetColor(255,255,255)
				Case 2: DrawImageEx INTERFACE(Interface_TextTitleChao_1), x#, y#, character
			End Select
		Case (Interface_TextNames_1):
			Select draworder
				Case 1:
					DrawImageEx INTERFACE(Interface_TextNames2_1), x#, y#, character
				Case 2:
					If character=6 Then
						SetColor(63,63,63) : DrawImageEx INTERFACE(Interface_TextNames_1), x#, y#, character : SetColor(255,255,255)
					Else
						SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextNames_1), x#, y#, character : SetColor(255,255,255)
					EndIf
			End Select
		Case (Interface_TextCredits_1):
			Select draworder
				Case 1: SetColor(r,g,b) : DrawImageEx INTERFACE(Interface_TextCredits_1), x#, y#, character : SetColor(255,255,255)
			End Select
		Default:
			DrawImageEx INTERFACE(font), x#, y#, character
	End Select
End Function

	;____________________________________________________________________________________________________________________________________________

Function DrawRealText_ReturnLetterWidth(character)
	Select character
		Case Asc("A") letterWidth#=Menu\Settings\ThemeKerningCapA
		Case Asc("B") letterWidth#=Menu\Settings\ThemeKerningCapB
		Case Asc("C") letterWidth#=Menu\Settings\ThemeKerningCapC
		Case Asc("D") letterWidth#=Menu\Settings\ThemeKerningCapD
		Case Asc("E") letterWidth#=Menu\Settings\ThemeKerningCapE
		Case Asc("F") letterWidth#=Menu\Settings\ThemeKerningCapF
		Case Asc("G") letterWidth#=Menu\Settings\ThemeKerningCapG
		Case Asc("H") letterWidth#=Menu\Settings\ThemeKerningCapH
		Case Asc("I") letterWidth#=Menu\Settings\ThemeKerningCapI
		Case Asc("J") letterWidth#=Menu\Settings\ThemeKerningCapJ
		Case Asc("K") letterWidth#=Menu\Settings\ThemeKerningCapK
		Case Asc("L") letterWidth#=Menu\Settings\ThemeKerningCapL
		Case Asc("M") letterWidth#=Menu\Settings\ThemeKerningCapM
		Case Asc("N") letterWidth#=Menu\Settings\ThemeKerningCapN
		Case Asc("O") letterWidth#=Menu\Settings\ThemeKerningCapO
		Case Asc("P") letterWidth#=Menu\Settings\ThemeKerningCapP
		Case Asc("Q") letterWidth#=Menu\Settings\ThemeKerningCapQ
		Case Asc("R") letterWidth#=Menu\Settings\ThemeKerningCapR
		Case Asc("S") letterWidth#=Menu\Settings\ThemeKerningCapS
		Case Asc("T") letterWidth#=Menu\Settings\ThemeKerningCapT
		Case Asc("U") letterWidth#=Menu\Settings\ThemeKerningCapU
		Case Asc("V") letterWidth#=Menu\Settings\ThemeKerningCapV
		Case Asc("W") letterWidth#=Menu\Settings\ThemeKerningCapW
		Case Asc("X") letterWidth#=Menu\Settings\ThemeKerningCapX
		Case Asc("Y") letterWidth#=Menu\Settings\ThemeKerningCapY
		Case Asc("Z") letterWidth#=Menu\Settings\ThemeKerningCapZ
			
		Case Asc("a") letterWidth#=Menu\Settings\ThemeKerningA
		Case Asc("b") letterWidth#=Menu\Settings\ThemeKerningB
		Case Asc("c") letterWidth#=Menu\Settings\ThemeKerningC
		Case Asc("d") letterWidth#=Menu\Settings\ThemeKerningD
		Case Asc("e") letterWidth#=Menu\Settings\ThemeKerningE
		Case Asc("f") letterWidth#=Menu\Settings\ThemeKerningF
		Case Asc("g") letterWidth#=Menu\Settings\ThemeKerningG
		Case Asc("h") letterWidth#=Menu\Settings\ThemeKerningH
		Case Asc("i") letterWidth#=Menu\Settings\ThemeKerningI
		Case Asc("j") letterWidth#=Menu\Settings\ThemeKerningJ
		Case Asc("k") letterWidth#=Menu\Settings\ThemeKerningK
		Case Asc("l") letterWidth#=Menu\Settings\ThemeKerningL
		Case Asc("m") letterWidth#=Menu\Settings\ThemeKerningM
		Case Asc("n") letterWidth#=Menu\Settings\ThemeKerningN
		Case Asc("o") letterWidth#=Menu\Settings\ThemeKerningO
		Case Asc("p") letterWidth#=Menu\Settings\ThemeKerningP
		Case Asc("q") letterWidth#=Menu\Settings\ThemeKerningQ
		Case Asc("r") letterWidth#=Menu\Settings\ThemeKerningR
		Case Asc("s") letterWidth#=Menu\Settings\ThemeKerningS
		Case Asc("t") letterWidth#=Menu\Settings\ThemeKerningT
		Case Asc("u") letterWidth#=Menu\Settings\ThemeKerningU
		Case Asc("v") letterWidth#=Menu\Settings\ThemeKerningV
		Case Asc("w") letterWidth#=Menu\Settings\ThemeKerningW
		Case Asc("x") letterWidth#=Menu\Settings\ThemeKerningX
		Case Asc("y") letterWidth#=Menu\Settings\ThemeKerningY
		Case Asc("z") letterWidth#=Menu\Settings\ThemeKerningZ
			
		Case Asc("1") letterWidth#=Menu\Settings\ThemeKerning1
		Case Asc("2") letterWidth#=Menu\Settings\ThemeKerning2
		Case Asc("3") letterWidth#=Menu\Settings\ThemeKerning3
		Case Asc("4") letterWidth#=Menu\Settings\ThemeKerning4
		Case Asc("5") letterWidth#=Menu\Settings\ThemeKerning5
		Case Asc("6") letterWidth#=Menu\Settings\ThemeKerning6
		Case Asc("7") letterWidth#=Menu\Settings\ThemeKerning7
		Case Asc("8") letterWidth#=Menu\Settings\ThemeKerning8
		Case Asc("9") letterWidth#=Menu\Settings\ThemeKerning9
		Case Asc("0") letterWidth#=Menu\Settings\ThemeKerning0
			
			
		Case Asc("!")			letterWidth#=6
		Case Asc("'"),Asc(",")					letterWidth#=7
		Case Asc("-"),Asc("."),Asc(":"),Asc(";"):				letterWidth#=8
		Case Asc("("),Asc(")"):			letterWidth#=9
		Case Asc(" "), 3+32:							letterWidth#=11
		Case Asc("/")				letterWidth#=12
			
		Case Asc("%"):							letterWidth#=20
		Default:								letterWidth#=14
	End Select
	Return letterWidth#
End Function

;________________________________________________________________


Function Menu_UpdateOptionButtons(optionorder)
	
	If optionorder>MENU_RESET# Then optionorder=optionorder-MENU_RESET#
	
	Select optionorder
		Case 1: Menu\OptionButton$="Resolution"
		Case 2: Menu\OptionButton$="Screen Mode"
		Case 3: Menu\OptionButton$="Video"
		Case 4: Menu\OptionButton$="Volume"
		Case 5: Menu\OptionButton$="Keyboard"
		Case 6: Menu\OptionButton$="Gamepad"
		Case 7: Menu\OptionButton$="Gameplay"
		Case 8: Menu\OptionButton$="Game Theme"
		Case 9: Menu\OptionButton$="Reset"
	End Select
	
End Function

;________________________________________________________________


Function Menu_UpdateTeamButtons(teamorder)

If teamorder>TEAM_TEAMCOUNT Then teamorder=teamorder-TEAM_TEAMCOUNT

If UNLOCKEDTEAM[teamorder]=1 Then
	Select teamorder
		Case 1: Menu\TeamButton$="Team Sonic"
		Case 2: Menu\TeamButton$="Team Dark"
		Case 3: Menu\TeamButton$="Custom"
		Case 4: Menu\TeamButton$="Team Chaotix"
		Case 5: Menu\TeamButton$="Team Sol"
		Case 6: Menu\TeamButton$="Team Oldies"
		Case 7: Menu\TeamButton$="Team Hooligan"
		Case 8: Menu\TeamButton$="Team Babylon"
		Case 9: Menu\TeamButton$="Team Relic"
		Case 10: Menu\TeamButton$="Team Robotnik"
		Case 11: Menu\TeamButton$="Custom Team"
	End Select
Else
	Select teamorder
		Case 3: Menu\TeamButton$="???"
		Default: Menu\TeamButton$="Team ???"
	End Select
EndIf

End Function

;________________________________________________________________


Function Menu_UpdateWarnings()

Select Menu\Menu2
	Case MENU_SCREEN#:
		Menu\Warning$ = "If you change this choice, the game will close down when you exit options, and you will have to reboot the game."
	Case MENU_VOLUME#:
		Menu\Warning$ = "Set the volume of sounds."
	Case MENU_THEME#:
		Menu\Warning$ = "Choose a nice menu theme."
	Case MENU_RESOLUTION#:
		Menu\Warning$ = "If you change this choice, the game will close down when you exit options, and you will have to reboot the game. Be sure to choose an appropriate resolution that will work for your display, or you will get a crash."
	Case MENU_DEBUG#:
		Menu\Warning$ = "This enables useful features such as moon jump and the object placer."
	Case MENU_CONTROLS#,MENU_CONTROLS2#:
		Select Menu\ControlAssignmentSource
			Case 1: Menu\Warning$ = "You can assign buttons for your keyboard and mouse here."
			Case 2: Menu\Warning$ = "You can assign buttons for your gamepad here."
		End Select
	Case MENU_PLANTS#:
		Menu\Warning$ = "This option determines whether plants will be spawned in stages and the Chao Garden."
	Case MENU_VIEW#:
		Menu\Warning$ = "Allow the engine to automatically determine the camera and object view range according to performance, or choose a fixed view range."
	Case MENU_AUTOCAM#:
		Menu\Warning$ = "If you prefer to control the camera with your mouse, turn this off."
	Case MENU_RESET#:
		Menu\Warning$ = "You must reset your saved game data here. Do not reset the save data files on your own. If you make a choice, the game will close down when you exit options, and you will have to reboot the game."
	Case MENU_SOUNDS#:
		Menu\Warning$ = "3D sounds are highly recommended."
	Case MENU_MODS#:
		Menu\Warning$ = "This option toggles mods. If you change this choice, the game will close down when you exit options, and you will have to reboot the game."
	Case MENU_TIPS#:
		Menu\Warning$ = "This option toggles whether control tips show up during gameplay."
	Case MENU_VSYNC#:
		Menu\Warning$ = "Turn this off if the game lags on your computer or this feature causes corruption in the game. If you change this choice, the game will close down when you exit options, and you will have to reboot the game."
	Default:
		Menu\Warning$ = "Turn this off if the game lags on your computer or this feature causes corruption in the game."
End Select

End Function

;________________________________________________________________


Function Menu_UpdateStageNames(option)

	Select Menu\ChaoGarden
		Case 0:
			If option<=StageAmount And option>0 Then
				If Menu\TutorialMode=1 Then
					Menu\StageName$ = SingleCharNames(Menu\Character[1]) + " Tutorial"
				ElseIf Menu\CollectionRoom=1 Then
					Menu\StageName$="The Island"
				ElseIf Menu\StartedStageWarp=1 Or Menu\MissionCard=1 Then
					Menu\StageName$=Menu\WarpRingName$
				Else
					Menu\StageName$=StageName$(option)
				EndIf
			Else
				
					Menu\StageName$="???"
					
			EndIf
		Case 1:
			Select Menu\SelectedStage
				Case 999: Menu\StageName$="Chao Garden"
				Case 998: Menu\StageName$="Chao Race"
				Case 997: Menu\StageName$="Chao Karate"
			End Select
	End Select

End Function

;________________________________________________________________


Function Menu_UpdateMissionInfo()
	
	Select Menu\Mission
		Case MISSION_NORMAL#
			Menu\MissionName$ = "Normal Run"
		Case MISSION_ENEMY#:
			Menu\MissionName$ = "Destructor"
		Case MISSION_RING#:
			Menu\MissionName$ = "Ring Collector"
		Case MISSION_HUNT#:
			Menu\MissionName$ = "Treasure Hunt"
		Case MISSION_GOLD#:
			Menu\MissionName$ = "Gold Rush"
		Case MISSION_STEALTH#:
			Menu\MissionName$ = "Stealth"
		Case MISSION_BALLOONS#:
			Menu\MissionName$ = "Confetti Parade"
		Case MISSION_FREEROAM#:
			Select Menu\ChaoGarden
				Case 0:
					Menu\MissionName$ = "Free Roam"
				Case 1:
					Select Menu\SelectedStage
						Case 999:
							Menu\MissionName$ = "Chao World"
							Menu\MissionInfo$ = "Take care of your chao"
						Case 998,997:
							Menu\MissionName$ = "Chao Stadium"
							Menu\MissionInfo$ = "May the odds be in your favor"
					End Select
			End Select
		Case MISSION_RIVAL#:
			Menu\MissionName$ = "Rival Battle"
		Case MISSION_CARNIVAL#:
			Menu\MissionName$ = "Robot Carnival"
		Case MISSION_BOSS#:
			Menu\MissionName$ = "Boss Battle"
		Case MISSION_FLICKY#:
			Menu\MissionName$ = "Flicky Rescue"
		Case MISSION_ESCAPE#:
			Menu\MissionName$ = "Escape"
		Case MISSION_ENCORE#:
			Menu\MissionName$ = "Encore"
		Case MISSION_COLLECT#:
			Menu\MissionName$ = "Collectathon"
		Case MISSION_LAP#:
			Menu\MissionName$ = "Race"
	End Select
	
	If Menu\MissionTime=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Time Attack"
	If Menu\MissionMach=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Mach Speed"
	If Menu\MissionPerfect=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Perfect Run"
	
End Function

;________________________________________________________________

Function Menu_PrintLocked(mode,value,x,y,showiconmode=0)
	

	amount=0
	Select mode
		Case 1,2: amount=TOUNLOCKCHAR[value]
		Case 3: amount=TOUNLOCKTEAM[value]
	End Select

	If IsCharMod(value) And mode<=2 Then mode=4
	
	If (Not(Menu_CharacterInRelease(value))) And (Not(IsCharMod(value))) Or value=CHAR_SHN Then mode=6

	SetColor 100, 100, 100
		Select mode
			Case 1:
				DrawRealText("This character is locked.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				DrawRealText("Return with "+amount+" emblems", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-4)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				DrawRealText("to continue.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-3)*GAME_WINDOW_SCALE#, (Interface_Text_3))
			Case 2:
				DrawRealText("This character is locked.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				DrawRealText("Return with "+amount+" emblems", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-4)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				DrawRealText("to view biography.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-3)*GAME_WINDOW_SCALE#, (Interface_Text_3))
			Case 3:
				If value>=TEAM_TEAMCOUNT Then
					DrawRealText("This feature is locked.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				Else
					DrawRealText("This team is locked.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				EndIf
				DrawRealText("Return with "+amount+" emblems", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-4)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				DrawRealText("to continue.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-3)*GAME_WINDOW_SCALE#, (Interface_Text_3))
			Case 4:
				DrawRealText("This mod character", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
				DrawRealText("was not found.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-4)*GAME_WINDOW_SCALE#, (Interface_Text_3))
			Case 5:
				DrawRealText("Mods are disabled.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
			Case 6
				DrawRealText("This character is unfinished.", x, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
		End Select
	SetColor 255,255,255

	If mode<>4 And mode<>5 And mode<>6 Then
	Select showiconmode
		Case 0:
			DrawImageEx(INTERFACE(Interface_Icons), x+65*GAME_WINDOW_SCALE#, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-1)*GAME_WINDOW_SCALE#, 4)
			DrawRealText(EMBLEMS+"/"+amount, x+95*GAME_WINDOW_SCALE#, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-1)*GAME_WINDOW_SCALE#, (Interface_Text_3))
		Case 1:
			DrawImageEx(INTERFACE(Interface_Icons), x-70*GAME_WINDOW_SCALE#, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-3.5)*GAME_WINDOW_SCALE#, 4)
			DrawRealText(EMBLEMS+"/"+amount, x-45*GAME_WINDOW_SCALE#, y+(CONTROLINFO_START#+CONTROLINFO_SPACE#*-3.5)*GAME_WINDOW_SCALE#, (Interface_Text_3))
	End Select
	EndIf

End Function

;________________________________________________________________

Function Menu_DrawCharacterNames(option, x#, y#, showstats=False, shownonplayable=False, showarrows=False, found=True)

	If found Then
		If showstats Then
			If option=CHAR_SHN And Menu\CharacterMode[Menu\MemberToSelect]=1 Then
				speed$ = Str(GetCharSpeed#(CHAR_SHN)) : jump$ = Str(GetCharJumpStrength#(CHAR_SHN))
			Else
				speed$ = Str(GetCharSpeed#(option)) : jump$ = Str(GetCharJumpStrength#(option))
			EndIf
			DrawRealText("Max speed: "+speed$,	x#-(125)*GAME_WINDOW_SCALE#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+43*GAME_WINDOW_SCALE#, (Interface_Text_2))
			DrawRealText("Jump strength: "+jump$,	x#-(18)*GAME_WINDOW_SCALE#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+43*GAME_WINDOW_SCALE#, (Interface_Text_2))
		EndIf
	EndIf

	;-------------------------

	If found Then
		Select InterfaceChar(option)
			Case CHAR_CRE:
				DrawRealText(CharNames$(InterfaceChar(option)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
				DrawRealText(CharNames2$(InterfaceChar(option)), x#, y#+9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(CHAR_CHE)], Interface_TextNames_G[InterfaceChar(CHAR_CHE)], Interface_TextNames_B[InterfaceChar(CHAR_CHE)])
			Case CHAR_BIG:
				DrawRealText(CharNames$(InterfaceChar(option)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
				DrawRealText(CharNames2$(InterfaceChar(option)), x#, y#+9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(CHAR_FRO)], Interface_TextNames_G[InterfaceChar(CHAR_FRO)], Interface_TextNames_B[InterfaceChar(CHAR_FRO)])
			Default:
				If IsCharMod(InterfaceChar(option)) Then
					If Len(MODCHARS_NAME$(InterfaceChar(option-CHAR_MOD1+1)))>0 Then
						DrawRealText(MODCHARS_NAME$(InterfaceChar(option-CHAR_MOD1+1)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
						DrawRealText(MODCHARS_NAME2$(InterfaceChar(option-CHAR_MOD1+1)), x#, y#+9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
					Else
						DrawRealText(MODCHARS_NAME$(InterfaceChar(option-CHAR_MOD1+1)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.00, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
					EndIf
				Else
					If Len(CharNames2$(option))>0 Then
						DrawRealText(CharNames$(InterfaceChar(option)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
						DrawRealText(CharNames2$(InterfaceChar(option)), x#, y#+9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
					Else
						DrawRealText(CharNames$(InterfaceChar(option)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.00, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
					EndIf
				EndIf
		End Select
	Else
		DrawRealText("???",	x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.00, (Interface_TextNames_1), 1, 0, 0, 0, 0)
	EndIf

End Function

;________________________________________________________________

Function Menu_DrawCredits(x#, y#)
	
	i=0
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Sonic World DX Version "+GAME_VERSION_TAG$, x#,		y#, i, 049, 175, 255) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("DX Developers", x#,		y#,  i, 049, 175, 255) : i=i+1
	Menu_DrawCreditsText("Deefor", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Sergeant Gerbil", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Brandon506042", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Jalex777", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Samy Crossette", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Redler Red7", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("UltimateDarkman", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("LandyRS", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Syphyous", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Mark", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Strike", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("MirrorOfDespair", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Giygas", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Orbyy", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Rummy", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("SonicpoX", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Shahars71", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Nemopolymer", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Astralix", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("xJOTA", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Marble", x#,		y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Programmers", x#,				y#, i, 255, 049, 049) : i=i+1
	Menu_DrawCreditsText("Mark - Lead programmer", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Samy - Assistant programmer", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Marble - Assistant programmer", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Character Animation", x#,		y#, i, 049, 175, 255) : i=i+1
	Menu_DrawCreditsText("Deefor", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Strike", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Redler Red7", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Kamau", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("UltimateDarkman", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Giygas", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Darkman", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Marble", x#,		y#, i) : i=i+1
	
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Stage Design", x#,		y#, i, 049, 175, 255) : i=i+1
	Menu_DrawCreditsText("Sergeant Gerbil", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Deefor", x#,	y#, i) : i=i+1
 	Menu_DrawCreditsText("Mark", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Redler Red7", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Brandon506042", x#,	y#, i) : i=i+1
 	Menu_DrawCreditsText("MirrorOfDespair", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Shahars71", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Astralix", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Nemopolymer", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("xJOTA", x#,		y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("UI Design", x#,		y#, i, 049, 255, 128) : i=i+1
 	Menu_DrawCreditsText("Deefor", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Brandon506042", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("MirrorOfDespair", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("B0M", x#,	y#, i) : i=i+1
	
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("Object Design", x#,		y#, i, 255, 049, 255) : i=i+1
 	Menu_DrawCreditsText("Deefor", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Redler Red7", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("TriNic", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Nibroc Rock", x#,	y#, i) : i=i+1
	
 	Menu_DrawCreditsText("Island and Garden Models", x#,		y#, i, 255, 255, 128) : i=i+1
 	Menu_DrawCreditsText("Nibroc Rock", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Deefor", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("Voice Actors", x#,		y#, i, 255, 255, 049) : i=i+1
 	Menu_DrawCreditsText("Sonic and GUN Soldiers - LandyRS", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Tails - RioDies", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Knuckles - Tanooki", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Shadow and Espio - PaxtonLee", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Rouge - Kat Alyst", x#,	y#, i) : i=i+1
 	Menu_DrawCreditsText("Omega - Artsy Noely", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Amy - SarahTheCatlove", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Cream - Cherri", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Silver and Fanclub Leader - CaptainComedy", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Blaze and Gamma - Scrillow", x#,	y#, i) : i=i+1
 	Menu_DrawCreditsText("Mighty - Bluespeedmouse", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Ray = SketchVA", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Infinite - TrevZed", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("GUN Commander - HereComesMongo", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Dr. Eggman - JoshG", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Dr. Eggman Nega - D the Hedgehog", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("Custom Music", x#,		y#, i, 255, 255, 049) : i=i+1
	Menu_DrawCreditsText("OverClocked Remix - Hang Castle (Section 1)", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("SHiRUBA - Hang Castle (Section 2)", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Siyren - Chao Garden Remix", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("UrBoiRAD - Misty Gorge", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Tudd and General Offensive - Sky Canyon", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("RedlerRed7 - Sonic Omniverse Theme Tracks", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("LandyRS - Sonic Earth Theme Tracks", x#,	y#, i) : i=i+1
	
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Additional Sounds", x#,		y#, i, 255, 049, 255) : i=i+1
 	Menu_DrawCreditsText("D the Hedgehog", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Bluespeedmouse", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("SWDX Launcher", x#,		y#, i, 255, 128, 049) : i=i+1
	Menu_DrawCreditsText("Brandon506042", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Chao Lessons", x#,		y#, i, 255, 128, 049) : i=i+1
	Menu_DrawCreditsText("Aquastarmarine", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Beta Testers", x#,		y#, i, 049, 255, 049) : i=i+1
 	Menu_DrawCreditsText("Sonic Overtime", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Naxshe", x#,	y#, i) : i=i+1
 	Menu_DrawCreditsText("Astreachan", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Dylovski", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("crisdebo0723", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Bluespeedmouse", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("SirMaxim", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Mr. Zong", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("SonicWorld24", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("KodyCrimson", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Special Thanks", x#,		y#, i, 049, 255, 049) : i=i+1
 	Menu_DrawCreditsText("WizGenesis", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Sonic Fangames HQ for helping to promote the game and moderation assistance.", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("The Modding Scene for keeping the game alive", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Original BlitzSonic Developers", x#,		y#, i, 049, 255, 049) : i=i+1
 	Menu_DrawCreditsText("Damizean", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Mark (Coré)", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Streak Thunderstorm", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Mista ED", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Original Music, Sounds, Models and Animations by Sonic Team", x#,			y#, i, 150, 150, 150) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("2018-2022", x#,			y#, i, 150, 150, 150) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
End Function

Function Menu_DrawCreditsText(text$, x#, y#, i#, r=255, g=255, b=255)
	y#=y#+(4.2*5)*i#
	If y#>0 And y#<GAME_WINDOW_H Then DrawRealText(Text$, x#, y#, (Interface_TextCredits_1), 1, 0, r, g, b)
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D