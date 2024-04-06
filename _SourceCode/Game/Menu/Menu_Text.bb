
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

Function Menu_UpdateBios(found=true)

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

;; Draws the text for the option buttons based on its id.
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
	Case 8: Menu\OptionButton$="Bloom"
	Case 9: Menu\OptionButton$="Shadows"
	Case 10: Menu\OptionButton$="Motion Blur"
	Case 11: Menu\OptionButton$="Sun Rays"
	Case 12: Menu\OptionButton$="Bump Maps"
	Case 13: Menu\OptionButton$="3D Sounds"
	Case 14: Menu\OptionButton$="Plants"
	Case 15: Menu\OptionButton$="View Range"
	Case 16: Menu\OptionButton$="Auto Camera"
	Case 17: Menu\OptionButton$="V-Sync"
	Case 18: Menu\OptionButton$="Mods"
	Case 19: Menu\OptionButton$="Control Tips"
	Case 20: Menu\OptionButton$="Menu Theme"
	Case 21: Menu\OptionButton$="Reset SaveData"
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
		Case 11: Menu\TeamButton$="Custom"
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
			If option<=StageAmount and option>0 Then
				Menu\StageName$=StageName$(option)
			Else
				If option<0 Then
					Menu\StageName$="Special Stage "+Int(abs(Menu\SelectedStage))
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
		Menu\MissionInfo$ = "Destroy as many as all enemies"
	Case MISSION_RING#:
		Menu\MissionName$ = "Ring Collector"
		Menu\MissionInfo$ = "Collect 200 rings"
	Case MISSION_HUNT#:
		Menu\MissionName$ = "Treasure Hunter"
		Menu\MissionInfo$ = "Collect 3 red star rings"
	Case MISSION_GOLD#:
		Menu\MissionName$ = "Gold Pursue"
		Menu\MissionInfo$ = "Destroy as many as all golden enemies"
	Case MISSION_STEALTH#:
		Menu\MissionName$ = "Stealth"
		Menu\MissionInfo$ = "Reach the goal without being detected by enemies"
	Case MISSION_BALLOONS#:
		Menu\MissionName$ = "Confetti Parade"
		Menu\MissionInfo$ = "Pop as many as all balloons"
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
						Menu\MissionInfo$ = "Good luck"
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
		Menu\MissionName$ = "Time Decline"
		Menu\MissionInfo$ = "Reach the goal before time runs out"
	Case MISSION_ESCAPE#:
		Menu\MissionName$ = "Escape"
		Menu\MissionInfo$ = "Escape from the chaser"
	Case MISSION_CAPSULE#:
		Menu\MissionName$ = "Wisp Rescue"
		Menu\MissionInfo$ = "Find the capsule and rescue the wisps"
End Select

If Menu\MissionTime=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Time Attack"
If Menu\MissionMach=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Mach Speed"
If Menu\MissionPerfect=1 Then Menu\MissionName$ = Menu\MissionName$ + " & Perfect Dash"
Menu\MissionName$ = Menu\MissionName$ + ":"

Menu\MissionInfo2$ = ""

If len(Menu\MissionInfo$) < 30 Then
	If Menu\MissionTime=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ", under a certain time"
Else
	If len(Menu\MissionInfo2$) <= 0 Then
		If Menu\MissionTime=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ","
		If Menu\MissionTime=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + "under a certain time"
	Else
		If Menu\MissionTime=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + ", under a certain time"
	EndIf
EndIf
If len(Menu\MissionInfo$) < 30 Then
	If Menu\MissionMach=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ", in mach speed"
Else
	If len(Menu\MissionInfo2$) <= 0 Then
		If Menu\MissionMach=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ","
		If Menu\MissionMach=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + "in mach speed"
	Else
		If Menu\MissionMach=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + ", in mach speed"
	EndIf
EndIf
If len(Menu\MissionInfo$) < 30 Then
	If Menu\MissionPerfect=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ", without dying"
Else
	If len(Menu\MissionInfo2$) <= 0 Then
		If Menu\MissionPerfect=1 Then Menu\MissionInfo$ = Menu\MissionInfo$ + ","
		If Menu\MissionPerfect=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + "without dying"
	Else
		If Menu\MissionPerfect=1 Then Menu\MissionInfo2$ = Menu\MissionInfo2$ + ", without dying"
	EndIf
EndIf
If len(Menu\MissionInfo2$) <= 0 Then
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

	If IsCharMod(value) and mode<=2 Then mode=4

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

	If mode<>4 and mode<>5 Then
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

Function Menu_DrawCharacterNames(option, x#, y#, showstats=false, shownonplayable=false, showarrows=false, found=true)

	If found Then
		If showstats Then
			If option=CHAR_TAI and Menu\CharacterMode[Menu\MemberToSelect]=1 Then
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
	Menu_DrawCreditsText("Ozcrash (Ozan)", x#,		y#, i, 049, 175, 255) : i=i+1
 	Menu_DrawCreditsText("Main Programming", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Character Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Character Animation", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Character Rigging", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Animation", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Plant Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Rigging", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Menu and Interface Art", x#,	y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("WizGenesis (Rodrick)", x#,	y#, i, 255, 242, 028) : i=i+1
 	Menu_DrawCreditsText("Programming", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Character Rigging", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Rigging", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Website Design", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Nibroc.Rock", x#,			y#, i, 132, 255, 000) : i=i+1
	Menu_DrawCreditsText("Character Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Object Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Plant Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Textures", x#,			y#, i) : i=i+1
 	Menu_DrawCreditsText("Menu and Interface Art", x#,	y#, i) : i=i+1
	Menu_DrawCreditsText("Logo Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
 	Menu_DrawCreditsText("Syphyous (Johann)", x#,		y#, i, 173, 058, 216) : i=i+1
	Menu_DrawCreditsText("Website Design", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("SonicFanNr1 (Nico)", x#,		y#, i, 000, 251, 047) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Rigging", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Plant Modeling", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Shahars71 (Shahar)", x#,		y#, i, 009, 247, 149) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("AquaStarMarine", x#,		y#, i, 255, 173, 016) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Chao Lessons", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Glitch Kitten (Lexie)", x#,	y#, i, 255, 000, 240) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("EmuEmi (Emily)", x#,		y#, i, 246, 119, 200) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Jalex777 (Jovin)", x#,		y#, i, 043, 224, 120) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1
 	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1
 	Menu_DrawCreditsText("Website Design", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Gerbil (Isaac)", x#,		y#, i, 180, 058, 058) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Chaos", x#,		y#, i, 030, 238, 206) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Zonex", x#,		y#, i, 044, 215, 080) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Waisechef", x#,			y#, i, 177, 245, 030) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Music", x#,					y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("CII", x#,				y#, i, 250, 237, 023) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Drflash55", x#,			y#, i, 074, 074, 172) : i=i+1
 	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("TheJojoNetwork", x#,		y#, i, 145, 225, 042) : i=i+1
 	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Wishdream (Lucyn)", x#,		y#, i, 213, 143, 217) : i=i+1
 	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Mykle Hunter", x#,		y#, i, 000, 120, 255) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Demon Alchemist", x#,		y#, i, 133, 213, 038) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("UltaGuide", x#,			y#, i, 245, 161, 240) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("ShadowOne333", x#,		y#, i, 154, 084, 199) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Nekkosu", x#,			y#, i, 028, 234, 168) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Siyren", x#,			y#, i, 117, 049, 170) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Natalie", x#,			y#, i, 209, 121, 208) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Logo Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Game84cube", x#,			y#, i, 101, 206, 007) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("SuperChaosControl", x#,		y#, i, 017, 141, 228) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Aloix12", x#,			y#, i, 057, 174, 185) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Amphobius", x#,			y#, i, 092, 137, 172) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Tudd", x#,			y#, i, 076, 116, 169) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("General Offensive", x#,		y#, i, 033, 226, 187) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("crisdebo0723", x#,		y#, i, 234, 044, 232) : i=i+1
	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("OriginalityAce", x#,		y#, i, 032, 218, 150) : i=i+1
	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Zol", x#,				y#, i, 108, 140, 129) : i=i+1
	Menu_DrawCreditsText("Menu Art", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Logo Art", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Character Rigging", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Chishado", x#,			y#, i, 037, 160, 031) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Plant Modeling", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("NicoCW", x#,			y#, i, 115, 022, 150) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Deefor", x#,			y#, i, 255, 190, 013) : i=i+1
	Menu_DrawCreditsText("Stage Banners Art", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Website Design", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Marvin Valentin", x#,		y#, i, 110, 110, 109) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Landy", x#,			y#, i, 075, 181, 225) : i=i+1
	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Voice Acting", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("DJ EAR", x#,			y#, i, 039, 084, 130) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Sean Evans", x#,			y#, i, 044, 196, 090) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Faseeh & Freen in Green", x#,	y#, i, 109, 244, 011) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("dante", x#,			y#, i, 235, 036, 228) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("LoGi", x#,			y#, i, 036, 235, 073) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Box Robot Studios", x#, y#, i, 247, 188, 073) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("SonicGenJSR", x#,			y#, i, 036, 219, 235) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("TheWhiteSnowOne", x#,		y#, i, 229, 229, 229) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Yarcaz", x#,			y#, i, 251, 243, 013) : i=i+1
	Menu_DrawCreditsText("Enemy Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Enemy Rigging", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Firelis", x#,			y#, i, 055, 093, 157) : i=i+1
	Menu_DrawCreditsText("Enemy Rigging", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Redlerred7", x#,			y#, i, 208, 025, 025) : i=i+1
	Menu_DrawCreditsText("Enemy Animation", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Falk", x#,			y#, i, 033, 224, 073) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Bouncy Glow", x#,			y#, i, 034, 136, 231) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Anti-Miles Prower", x#,		y#, i, 249, 203, 020) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("XTheMasterX", x#,			y#, i, 165, 024, 231) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("sui tune", x#,			y#, i, 054, 233, 231) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Dr. Mack Foxx & EspioKaos (John Weeks)", x#, y#, i, 248, 227, 089) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("jparecki95", x#,			y#, i, 226, 165, 033) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Hapi-San", x#,			y#, i, 163, 052, 233) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("darksupersonic741", x#,		y#, i, 057, 108, 137) : i=i+1
	Menu_DrawCreditsText("Stage Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("DemondraBG", x#,		y#, i, 058, 208, 042) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Turret 3471", x#,			y#, i, 016, 157, 129) : i=i+1
	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Kamau2335", x#,			y#, i, 018, 054, 144) : i=i+1
	Menu_DrawCreditsText("Character Rigging", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Razor the Shark", x#,		y#, i, 113, 143, 169) : i=i+1
	Menu_DrawCreditsText("Object Modeling", x#,		y#, i) : i=i+1
	Menu_DrawCreditsText("Object Placement", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("IPPY", x#,			y#, i, 036, 205, 116) : i=i+1
	Menu_DrawCreditsText("Logo Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("DaKoopa", x#,			y#, i, 130, 098, 069) : i=i+1
	Menu_DrawCreditsText("Logo Art", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("JaysonJean", x#,			y#, i, 041, 201, 225) : i=i+1
	Menu_DrawCreditsText("Logo Art", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Object Animation", x#,	y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText(". .", x#,				y#, (i-0.7), 255, 137, 254)
	Menu_DrawCreditsText(".  .", x#,			y#, (i-0.7), 255, 137, 254)
	Menu_DrawCreditsText("Kopuk", x#,			y#, i, 255, 137, 254) : i=i+1
	Menu_DrawCreditsText("Love", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
 	Menu_DrawCreditsText("also thanks to...", x#,		y#, i, 150, 150, 150) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Damizean", x#,			y#, i, 172, 118, 065) : i=i+1
 	Menu_DrawCreditsText("Base Engine", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("SEGA", x#,			y#, i, 000, 012, 254) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Sounds", x#,			y#, i) : i=i+1
	Menu_DrawCreditsText("Voices", x#,			y#, i) : i=i+1
 	Menu_DrawCreditsText("Textures", x#,			y#, i) : i=i+1
 	Menu_DrawCreditsText("Character Models", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Enemy Models", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Object Models", x#,		y#, i) : i=i+1
 	Menu_DrawCreditsText("Stage Models", x#,		y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Dev Large & Zeebra Twigy", x#,	y#, i, 039, 124, 145) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Tee Lopes", x#,	y#, i, 045, 191, 172) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Krome Studios", x#,		y#, i, 227, 177, 024) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Capcom", x#,			y#, i, 030, 140, 118) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("Spiralmouth", x#,			y#, i, 218, 218, 025) : i=i+1
 	Menu_DrawCreditsText("Music", x#,			y#, i) : i=i+1

	Menu_DrawCreditsText("", x#,				y#, i) : i=i+1
	Menu_DrawCreditsText("@ 2013-2018", x#,			y#, i, 150, 150, 150) : i=i+1

End Function

Function Menu_DrawCreditsText(text$, x#, y#, i#, r=255, g=255, b=255)
	y#=y#+(4.2*5)*i#
	If y#>0 and y#<GAME_WINDOW_H Then DrawRealText(text$, x#, y#, (Interface_TextCredits_1), 1, 0, r, g, b)
End Function