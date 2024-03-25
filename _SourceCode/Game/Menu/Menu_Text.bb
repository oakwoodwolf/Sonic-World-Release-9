
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

; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Dim CharNames$(CHAR_NORMALCOUNT)
Dim CharNames2$(CHAR_NORMALCOUNT)
CharNames$(CHAR_SON) = "SONIC"				: CharNames2$(CHAR_SON) = "THE HEDGEHOG"
CharNames$(CHAR_TAI) = "MILES #TAILS#"		: CharNames2$(CHAR_TAI) = "PROWER"
CharNames$(CHAR_KNU) = "KNUCKLES"			: CharNames2$(CHAR_KNU) = "THE ECHIDNA"
CharNames$(CHAR_AMY) = "AMY ROSE"			: CharNames2$(CHAR_AMY) = ""
CharNames$(CHAR_SHA) = "SHADOW"				: CharNames2$(CHAR_SHA) = "THE HEDGEHOG"
CharNames$(CHAR_ROU) = "ROUGE THE BAT"		: CharNames2$(CHAR_ROU) = ""
CharNames$(CHAR_CRE) = "CREAM THE RABBIT"	: CharNames2$(CHAR_CRE) = "& CHEESE"
CharNames$(CHAR_BLA) = "BLAZE THE CAT"		: CharNames2$(CHAR_BLA) = ""
CharNames$(CHAR_SIL) = "SILVER"				: CharNames2$(CHAR_SIL) = "THE HEDGEHOG"
CharNames$(CHAR_OME) = "E-123 OMEGA"		: CharNames2$(CHAR_OME) = ""
CharNames$(CHAR_ESP) = "ESPIO"				: CharNames2$(CHAR_ESP) = "THE CHAMELEON"
CharNames$(CHAR_CHA) = "CHARMY BEE"			: CharNames2$(CHAR_CHA) = ""
CharNames$(CHAR_VEC) = "VECTOR"				: CharNames2$(CHAR_VEC) = "THE CROCODILE"
CharNames$(CHAR_BIG) = "BIG THE CAT"		: CharNames2$(CHAR_BIG) = "& FROGGY"
CharNames$(CHAR_MAR) = "MARINE"				: CharNames2$(CHAR_MAR) = "THE RACCOON"
CharNames$(CHAR_MIG) = "MIGHTY"				: CharNames2$(CHAR_MIG) = "THE ARMADILLO"
CharNames$(CHAR_RAY) = "RAY THE"			: CharNames2$(CHAR_RAY) = "FLYING SQUIRREL"
CharNames$(CHAR_CHO) = "CHAOS"				: CharNames2$(CHAR_CHO) = ""
CharNames$(CHAR_TIK) = "TIKAL"				: CharNames2$(CHAR_TIK) = "THE ECHIDNA"
CharNames$(CHAR_NAC) = "FANG THE SNIPER"	: CharNames2$(CHAR_NAC) = "(NACK THE WEASEL)"
CharNames$(CHAR_BEA) = "BEAN"				: CharNames2$(CHAR_BEA) = "THE DYNAMITE"
CharNames$(CHAR_BAR) = "BARK THE"			: CharNames2$(CHAR_BAR) = "POLAR BEAR"
CharNames$(CHAR_JET) = "JET THE HAWK"		: CharNames2$(CHAR_JET) = ""
CharNames$(CHAR_WAV) = "WAVE"				: CharNames2$(CHAR_WAV) = "THE SWALLOW"
CharNames$(CHAR_STO) = "STORM"				: CharNames2$(CHAR_STO) = "THE ALBATROSS"
CharNames$(CHAR_TIA) = "TIARA THE MANX"		: CharNames2$(CHAR_TIA) = ""
CharNames$(CHAR_HON) = "HONEY THE CAT"		: CharNames2$(CHAR_HON) = ""
CharNames$(CHAR_SHD) = "SHADE"				: CharNames2$(CHAR_SHD) = "THE ECHIDNA"
CharNames$(CHAR_MPH) = "MEPHILES THE DARK"	: CharNames2$(CHAR_MPH) = ""
CharNames$(CHAR_HBO) = "HEAVY & BOMB"		: CharNames2$(CHAR_HBO) = ""
CharNames$(CHAR_GAM) = "E-102 GAMMA"		: CharNames2$(CHAR_GAM) = ""
CharNames$(CHAR_EME) = "EMERL"				: CharNames2$(CHAR_EME) = ""
CharNames$(CHAR_MET) = "METAL SONIC"		: CharNames2$(CHAR_MET) = ""
CharNames$(CHAR_TDL) = "TAILS DOLL"			: CharNames2$(CHAR_TDL) = ""
CharNames$(CHAR_MKN) = "METAL KNUCKLES"		: CharNames2$(CHAR_MKN) = ""
CharNames$(CHAR_EGG) = "DR. IVO #EGGMAN#"	: CharNames2$(CHAR_EGG) = "ROBOTNIK"
CharNames$(CHAR_BET) = "E-101 BETA"			: CharNames2$(CHAR_BET) = ""
CharNames$(CHAR_MT3) = "METAL SONIC 3.0"	: CharNames2$(CHAR_MT3) = ""
CharNames$(CHAR_GME) = "GEMERL"				: CharNames2$(CHAR_GME) = ""
CharNames$(CHAR_PRS) = "THE PRESIDENT"		: CharNames2$(CHAR_PRS) = ""
CharNames$(CHAR_COM) = "THE COMMANDER"		: CharNames2$(CHAR_COM) = ""
CharNames$(CHAR_CHW) = "MILK"				: CharNames2$(CHAR_CHW) = ""
CharNames$(CHAR_EGR) = "EGGROBO"			: CharNames2$(CHAR_EGR) = ""
CharNames$(CHAR_INF) = "INFINITE"			: CharNames2$(CHAR_INF) = ""
;---
CharNames$(CHAR_OBT) = "ORBOT"				: CharNames2$(CHAR_OBT) = ""
CharNames$(CHAR_CBT) = "CUBOT"				: CharNames2$(CHAR_CBT) = ""
CharNames$(CHAR_EGN) = "EGGMAN NEGA"		: CharNames2$(CHAR_EGN) = ""
CharNames$(CHAR_MIA) = "MARIA"				: CharNames2$(CHAR_MIA) = "ROBOTNIK"
CharNames$(CHAR_VAN) = "VANILLA THE RABBIT"	: CharNames2$(CHAR_VAN) = "& CHOCOLA"

; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Dim SingleCharNames$(CHAR_NORMALCOUNT)
SingleCharNames$(CHAR_SON) = "Sonic"
SingleCharNames$(CHAR_TAI) = "Tails"
SingleCharNames$(CHAR_KNU) = "Knuckles"
SingleCharNames$(CHAR_AMY) = "Amy"
SingleCharNames$(CHAR_SHA) = "Shadow"
SingleCharNames$(CHAR_ROU) = "Rouge"
SingleCharNames$(CHAR_CRE) = "Cream"
SingleCharNames$(CHAR_BLA) = "Blaze"
SingleCharNames$(CHAR_SIL) = "Silver"
SingleCharNames$(CHAR_OME) = "Omega"
SingleCharNames$(CHAR_ESP) = "Espio"
SingleCharNames$(CHAR_CHA) = "Charmy"
SingleCharNames$(CHAR_VEC) = "Vector"
SingleCharNames$(CHAR_BIG) = "Big"
SingleCharNames$(CHAR_MAR) = "Marine"
SingleCharNames$(CHAR_MIG) = "Mighty"
SingleCharNames$(CHAR_RAY) = "Ray"
SingleCharNames$(CHAR_CHO) = "Chaos"
SingleCharNames$(CHAR_TIK) = "Tikal"
SingleCharNames$(CHAR_NAC) = "Fang"
SingleCharNames$(CHAR_BEA) = "Bean"
SingleCharNames$(CHAR_BAR) = "Bark"
SingleCharNames$(CHAR_JET) = "Jet"
SingleCharNames$(CHAR_WAV) = "Wave"
SingleCharNames$(CHAR_STO) = "Storm"
SingleCharNames$(CHAR_TIA) = "Tiara"
SingleCharNames$(CHAR_HON) = "Honey"
SingleCharNames$(CHAR_SHD) = "Shade"
SingleCharNames$(CHAR_MPH) = "Mephiles"
SingleCharNames$(CHAR_HBO) = "Heavy & Bomb"
SingleCharNames$(CHAR_GAM) = "Gamma"
SingleCharNames$(CHAR_EME) = "Emerl"
SingleCharNames$(CHAR_MET) = "Metal Sonic"
SingleCharNames$(CHAR_TDL) = "Tails Doll"
SingleCharNames$(CHAR_MKN) = "Metal Knuckles"
SingleCharNames$(CHAR_EGG) = "Eggman"
SingleCharNames$(CHAR_BET) = "Beta"
SingleCharNames$(CHAR_MT3) = "Metal Sonic 3.0"
SingleCharNames$(CHAR_GME) = "Gemerl"
SingleCharNames$(CHAR_PRS) = "the President"
SingleCharNames$(CHAR_COM) = "the Commander"
SingleCharNames$(CHAR_CHW) = "Milk"
SingleCharNames$(CHAR_EGR) = "EggRobo"
SingleCharNames$(CHAR_INF) = "Infinite"
;---
SingleCharNames$(CHAR_OBT) = "Orbot"
SingleCharNames$(CHAR_CBT) = "Cubot"
SingleCharNames$(CHAR_EGN) = "Eggman Nega"
SingleCharNames$(CHAR_MIA) = "Maria"
SingleCharNames$(CHAR_VAN) = "Vanilla"


Dim SingleTeamNames$(TEAM_TEAMCOUNT)
SingleTeamNames$(TEAM_SONIC)	= "Team Sonic"
SingleTeamNames$(TEAM_DARK)		= "Team Dark"
SingleTeamNames$(TEAM_ROSE)		= "Team Rose"
SingleTeamNames$(TEAM_CHAOTIX)	= "Team Chaotix"
SingleTeamNames$(TEAM_SOL)		= "Team Sol"
SingleTeamNames$(TEAM_OLDIES)	= "Team Oldies"
SingleTeamNames$(TEAM_HOOLIGAN)	= "Team Hooligan"
SingleTeamNames$(TEAM_BABYLON)	= "Team Babylon"
SingleTeamNames$(TEAM_RELIC)	= "Team Relic"
SingleTeamNames$(TEAM_ROBOTNIK)	= "Team Robotnik"
SingleTeamNames$(TEAM_TEAMCOUNT)= "custom teams"

; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function Menu_UpdateBios(found=True)

If Len(Menu\Bio$)=0 Then
	If found Then
		If IsCharMod(Menu\Option) Then
			Menu\Bio$ = LoadMods_Bio$(Menu\Option-CHAR_MOD1+1)
		Else
			Menu\Bio$ = ReadAndFindTextExplanation$("Text_Bios",InterfaceChar(Menu\Option))
		EndIf
	Else
		Menu\Bio$ = ""
	EndIf
EndIf

End Function

;________________________________________________________________


Function Menu_UpdateOptionButtons(optionorder)

If optionorder>MENU_RESET# Then optionorder=optionorder-MENU_RESET#

Select optionorder
	Case 1: Menu\OptionButton$="Resolution"
	Case 2: Menu\OptionButton$="Screen Mode"
	Case 3: Menu\OptionButton$="Debug Mode"
	Case 4: Menu\OptionButton$="Sound Volume"
	Case 5: Menu\OptionButton$="      Controls"
	Case 6: Menu\OptionButton$="   Controls"
	Case 7: Menu\OptionButton$="Depth of Field"
	Case 8: Menu\OptionButton$="Shadows"
	Case 9: Menu\OptionButton$="Motion Blur"
	Case 10: Menu\OptionButton$="Sun Rays"
	Case 11: Menu\OptionButton$="Bump Maps"
	Case 12: Menu\OptionButton$="3D Sounds"
	Case 13: Menu\OptionButton$="Plants"
	Case 14: Menu\OptionButton$="Render Distance"
	Case 15: Menu\OptionButton$="Auto Camera"
	Case 16: Menu\OptionButton$="V-Sync"
	Case 17: Menu\OptionButton$="Mods"
	Case 18: Menu\OptionButton$="Control Tips"
	Case 19: Menu\OptionButton$="Game Theme"
	Case 20: Menu\OptionButton$="Reset SaveData"
End Select

End Function

;________________________________________________________________


Function Menu_UpdateTeamButtons(teamorder)

If teamorder>TEAM_TEAMCOUNT Then teamorder=teamorder-TEAM_TEAMCOUNT

If UNLOCKEDTEAM[teamorder]=1 Then
	Select teamorder
		Case 1: Menu\TeamButton$="Team Sonic"
		Case 2: Menu\TeamButton$="Team Dark"
		Case 3: Menu\TeamButton$="Team Rose"
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
		Case 11: Menu\TeamButton$="???"
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
				Menu\StageName$=StageName$(option)
			Else
				If option<0 Then
					Menu\StageName$="Special Stage "+Int(Abs(Menu\SelectedStage))
				Else
					Menu\StageName$="???"
				EndIf
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
	Case MISSION_NORMAL#:
		If Menu\SelectedStage>=0 Then
			Menu\MissionName$ = "Normal Run"
			Menu\MissionInfo$ = "Reach the goal"
		Else
			Menu\MissionName$ = "Emerald Chase"
			Menu\MissionInfo$ = "Get the emeralds"
		EndIf
	Case MISSION_ENEMY#:
		Menu\MissionName$ = "Destructor"
		Menu\MissionInfo$ = "Destroy the required amount of enemies"
	Case MISSION_RING#:
		Menu\MissionName$ = "Ring Collector"
		Menu\MissionInfo$ = "Collect the required amount of rings"
	Case MISSION_HUNT#:
		Menu\MissionName$ = "Treasure Hunter"
		Menu\MissionInfo$ = "Collect 3 emerald shards"
	Case MISSION_GOLD#:
		Menu\MissionName$ = "Gold Rush"
		Menu\MissionInfo$ = "Destroy all golden enemies"
	Case MISSION_STEALTH#:
		Menu\MissionName$ = "Stealth"
		Menu\MissionInfo$ = "Reach the goal without being detected by enemies"
	Case MISSION_BALLOONS#:
		Menu\MissionName$ = "Confetti Parade"
		Menu\MissionInfo$ = "Pop all balloons"
	Case MISSION_FREEROAM#:
		Select Menu\ChaoGarden
			Case 0:
				Menu\MissionName$ = "Free Roam"
				Menu\MissionInfo$ = "Run around and explore"
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
		Menu\MissionInfo$ = "Defeat the opponent(s)"
	Case MISSION_CARNIVAL#:
		Menu\MissionName$ = "Robot Carnival"
		Menu\MissionInfo$ = "Destroy all enemies"
	Case MISSION_BOSS#:
		Menu\MissionName$ = "Boss Battle"
		Menu\MissionInfo$ = "Defeat the boss"
	Case MISSION_FLICKY#:
		Menu\MissionName$ = "Flicky Rescue"
		Menu\MissionInfo$ = "Rescue 5 flickies and bring them to the goal"
	Case MISSION_DECLINE#:
		Menu\MissionName$ = "Countdown"
		Menu\MissionInfo$ = "Reach the goal before time runs out"
	Case MISSION_ESCAPE#:
		Menu\MissionName$ = "Escape"
		Menu\MissionInfo$ = "Escape from the chaser"
	Case MISSION_ENCORE#:
		Menu\MissionName$ = "Encore Mode"
		Menu\MissionInfo$ = "A tougher challenge awaits"
End Select

If Menu\MissionTime=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Time Attack"
If Menu\MissionMach=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Mach Speed"
If Menu\MissionPerfect=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Perfect Dash"
Menu\MissionName$ = Menu\MissionName$ + ":"

Menu\MissionInfo2$ = ""

If Len(Menu\MissionInfo$) < 30 Then
	If Menu\MissionTime=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ", under a certain time"
Else
	If Len(Menu\MissionInfo2$) <= 0 Then
		If Menu\MissionTime=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ","
		If Menu\MissionTime=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + "under a certain time"
	Else
		If Menu\MissionTime=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + ", under a certain time"
	EndIf
EndIf
If Len(Menu\MissionInfo$) < 30 Then
	If Menu\MissionMach=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ", in mach speed"
Else
	If Len(Menu\MissionInfo2$) <= 0 Then
		If Menu\MissionMach=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ","
		If Menu\MissionMach=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + "in mach speed"
	Else
		If Menu\MissionMach=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + ", in mach speed"
	EndIf
EndIf
If Len(Menu\MissionInfo$) < 30 Then
	If Menu\MissionPerfect=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ", without dying"
Else
	If Len(Menu\MissionInfo2$) <= 0 Then
		If Menu\MissionPerfect=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ","
		If Menu\MissionPerfect=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + "without dying"
	Else
		If Menu\MissionPerfect=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + ", without dying"
	EndIf
EndIf
If Len(Menu\MissionInfo2$) <= 0 Then
	Menu\MissionInfo$ = Menu\MissionInfo$ + "!"
Else
	Menu\MissionInfo2$ = Menu\MissionInfo2$ + "!"
EndIf

End Function

;________________________________________________________________

Function Menu_PrintLocked(mode,value,x,y,showiconmode=0)

	CountEmblems()

	amount=0
	Select mode
		Case 1,2: amount=TOUNLOCKCHAR[value]
		Case 3: amount=TOUNLOCKTEAM[value]
	End Select

	If IsCharMod(value) And mode<=2 Then mode=4

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
		End Select
	SetColor 255,255,255

	If mode<>4 And mode<>5 Then
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
			If option=CHAR_TAI And Menu\CharacterMode[Menu\MemberToSelect]=1 Then
				speed$ = Str(GetCharSpeed#(CHAR_TMH)) : jump$ = Str(GetCharJumpStrength#(CHAR_TMH))
			Else
				speed$ = Str(GetCharSpeed#(option)) : jump$ = Str(GetCharJumpStrength#(option))
			EndIf
			DrawRealText("Max speed: "+speed$,	x#-(125)*GAME_WINDOW_SCALE#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+43*GAME_WINDOW_SCALE#, (Interface_Text_2))
			DrawRealText("Jump strength: "+jump$,	x#-(18)*GAME_WINDOW_SCALE#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+43*GAME_WINDOW_SCALE#, (Interface_Text_2))
			If Menu\ChaoGarden=1 Then DrawImageEx(INTERFACE(Interface_Characters), x#-(140)*GAME_WINDOW_SCALE#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+45*GAME_WINDOW_SCALE#, CHAR_NONMODPLAYABLECOUNT+1+CHARSIDES(InterfaceChar(option)))
		EndIf
	EndIf

	If shownonplayable Then
	SetColor(255,10,10)
	Select option
		Case CHAR_VAN,CHAR_MIA:
			DrawRealText("(non-playable)",	x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+43*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
		Case CHAR_OBT,CHAR_CBT,CHAR_EGN:
			DrawRealText("(non-playable)",	x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25+33*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
	End Select
	SetColor(255,255,255)
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
			Case CHAR_VAN:
				DrawRealText(CharNames$(InterfaceChar(option)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
				DrawRealText(CharNames2$(InterfaceChar(option)), x#, y#+9.4*5*GAME_WINDOW_SCALE#*0.25, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(CHAR_CHC)], Interface_TextNames_G[InterfaceChar(CHAR_CHC)], Interface_TextNames_B[InterfaceChar(CHAR_CHC)])
			Default:
				If IsCharMod(InterfaceChar(option)) Then
					DrawRealText(MODCHARS_NAME$(InterfaceChar(option-CHAR_MOD1+1)), x#, y#-9.4*5*GAME_WINDOW_SCALE#*0.00, (Interface_TextNames_1), 1, 0, Interface_TextNames_R[InterfaceChar(option)], Interface_TextNames_G[InterfaceChar(option)], Interface_TextNames_B[InterfaceChar(option)])
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

	If showarrows Then
		If found Then
			Select Menu\Option
				Case CHAR_CRE,CHAR_EGG,CHAR_MKN:
					DrawImageEx(INTERFACE(Interface_Icons), x#-165*GAME_WINDOW_SCALE#, y#,19)
					DrawImageEx(INTERFACE(Interface_Icons), x#+165*GAME_WINDOW_SCALE#, y#,18)
				Case CHAR_AMY,CHAR_CHO,CHAR_OBT,CHAR_CBT:
					DrawImageEx(INTERFACE(Interface_Icons), x#-120*GAME_WINDOW_SCALE#, y#,19)
					DrawImageEx(INTERFACE(Interface_Icons), x#+120*GAME_WINDOW_SCALE#, y#,18)
				Case CHAR_OME,CHAR_CHA,CHAR_MET,CHAR_TDL,CHAR_BEA,CHAR_BAR,CHAR_GAM,CHAR_EME,CHAR_BET,CHAR_GME,CHAR_CHW,CHAR_EGR,CHAR_INF:
					DrawImageEx(INTERFACE(Interface_Icons), x#-135*GAME_WINDOW_SCALE#, y#,19)
					DrawImageEx(INTERFACE(Interface_Icons), x#+135*GAME_WINDOW_SCALE#, y#,18)
				Case CHAR_VAN,CHAR_NAC,CHAR_MPH:
					DrawImageEx(INTERFACE(Interface_Icons), x#-175*GAME_WINDOW_SCALE#, y#,19)
					DrawImageEx(INTERFACE(Interface_Icons), x#+175*GAME_WINDOW_SCALE#, y#,18)
				Default:
					DrawImageEx(INTERFACE(Interface_Icons), x#-150*GAME_WINDOW_SCALE#, y#,19)
					DrawImageEx(INTERFACE(Interface_Icons), x#+150*GAME_WINDOW_SCALE#, y#,18)
			End Select
		Else
			DrawImageEx(INTERFACE(Interface_Icons), x#-120*GAME_WINDOW_SCALE#, y#,19)
			DrawImageEx(INTERFACE(Interface_Icons), x#+120*GAME_WINDOW_SCALE#, y#,18)
		EndIf
	EndIf

End Function

;________________________________________________________________


Function Menu_DrawCredits(x#, y#)

	i=0

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Sonic World DX SAGE 2020 Demo", x#,		y#, i, 049, 175, 255) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("Programming", x#,		y#,  i, 049, 175, 255) : i=i+1
	Menu_DrawCreditsText("Mark", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Samy/SonicDBZFan07", x#,		y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Character Animation", x#,		y#, i, 049, 175, 255) : i=i+1
	Menu_DrawCreditsText("Deefor", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Strike", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Kamau", x#,	y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
	Menu_DrawCreditsText("Stage Design", x#,		y#, i, 049, 175, 255) : i=i+1
	Menu_DrawCreditsText("Sergeant Gerbil", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Mark", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("MirrorOfDespair", x#,		y#, i) : i=i+1
	
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	
 	Menu_DrawCreditsText("Interface Design", x#,		y#, i, 049, 175, 255) : i=i+1
 	Menu_DrawCreditsText("Deefor", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Brandon", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("MirrorOfDespair", x#,	y#, i) : i=i+1
	
	
	Menu_DrawCreditsText("2018-2020", x#,			y#, i, 150, 150, 150) : i=i+1

End Function

Function Menu_DrawCreditsText(text$, x#, y#, i#, r=255, g=255, b=255)
	y#=y#+(4.2*5)*i#
	If y#>0 And y#<GAME_WINDOW_H Then DrawRealText(Text$, x#, y#, (Interface_TextCredits_1), 1, 0, r, g, b)
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D