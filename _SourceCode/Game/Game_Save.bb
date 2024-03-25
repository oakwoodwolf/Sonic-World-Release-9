
Global UNLOCKEDCHAR[CHAR_NORMALCOUNT]
Global UNLOCKEDTEAM[TEAM_TEAMCOUNT]
Global UNLOCKEDSPECIALSTAGES[0]
Global UNLOCKEDEMERALDS[7]

Global StageAmountPossible
GetStageAmount()
Global StageAmount
Dim StagePath$(StageAmountPossible)
Dim StageName$(StageAmountPossible)
Dim MarathonStage(StageAmountPossible-1)
Global StageMission[5]
Global StageMissionTime[5]
Global StageMissionMach[5]
Global StageMissionPerfect[5]

Global JUMPAMODE[CHAR_PLAYABLECOUNT]

Dim EMBLEMS1(5,StageAmountPossible)
Dim EMBLEMS2(5,StageAmountPossible)
Global EMBLEMS

Dim REDRING1(StageAmountPossible)
Dim REDRING2(StageAmountPossible)
Dim REDRING3(StageAmountPossible)
Dim REDRING4(StageAmountPossible)
Dim REDRING5(StageAmountPossible)
Dim ALLREDRING(StageAmountPossible)
Global REDRINGS

Global RECORDS_CURRENT
Dim RECORDS_NAME$(1)
Dim RECORDS_RINGS(5,4)
Dim RECORDS_ENEMIES(5,4)
Dim RECORDS_TIME(5,4)
Dim RECORDS_SCORE(5,4)
Dim RECORDS_RANK(5,4)

Global CHAOCOUNT=20
Dim CHAOSLOTS(1,CHAOCOUNT)
Dim CHAOSUM(1)
Dim CHAOFIRSTTIMER(1)

Dim FRUITSUM(1)
Dim HATSUM(1)
Dim TOYSUM(1)
Dim DRIVESUM(1)
Dim SHELLSUM(1)
Dim SEEDSUM(1)

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function LoadStageList()
	StageAmount=0
	LoadStageList_Individual("StagesOfficial")
	LoadStageList_Individual("Stages")

	For i=1 To StageAmount
	LoadGame_Emblems(i)
	Next
End Function

Function LoadStageList_Individual(stagesxml$)
	StageListRoot = xmlLoad(stagesxml$+"/Stages.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing 'Stages.xml'")
	For i=1 To xmlNodeChildCount(StageListRoot)
		Child = xmlNodeChild(StageListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "stage":
				StageAmount=StageAmount+1
				StagePath$(StageAmount) = stagesxml$+"/"+xmlNodeAttributeValueGet(Child, "name")
				StageName$(StageAmount) = xmlNodeAttributeValueGet(Child, "name")
		End Select
	Next
	xmlNodeDelete(StageListRoot)
End Function

Function GetStageAmount()
	StageAmountPossible = 0
	GetStageAmount_Individual("StagesOfficial")
	GetStageAmount_Individual("Stages")
End Function

Function GetStageAmount_Individual(stagesxml$)
	StageListRoot = xmlLoad(stagesxml$+"/Stages.xml")
	If (xmlErrorCount()>0) Then RuntimeError("Game_Startup() -> Error while parsing 'Stages.xml'")
	For i=1 To xmlNodeChildCount(StageListRoot)
		Child = xmlNodeChild(StageListRoot, i)
		Select xmlNodeNameGet$(Child)
			Case "stage":
				StageAmountPossible=StageAmountPossible+1
		End Select
	Next
	xmlNodeDelete(StageListRoot)
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame(saveemblems=True)

	WriteFileWithEncryption("SETTINGS")

	WriteLine(CurrentOpenFile,"<firsttime is="+Chr$(34)+Menu\FirstTime+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<resolution setting="+Chr$(34)+Menu\Settings\Resolution#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<screen setting="+Chr$(34)+Menu\Settings\ScreenMode#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<debug setting="+Chr$(34)+Menu\Settings\Debug#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<debugnodes setting="+Chr$(34)+Menu\Settings\DebugNodes#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<volume setting="+Chr$(34)+Menu\Settings\Volume#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<volumesfx setting="+Chr$(34)+Menu\Settings\VolumeSFX#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<volumeva setting="+Chr$(34)+Menu\Settings\VolumeVA#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<volumem setting="+Chr$(34)+Menu\Settings\VolumeM#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<volumeamb setting="+Chr$(34)+Menu\Settings\VolumeAmb#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<dof setting="+Chr$(34)+Menu\Settings\DepthOfField#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<shadows setting="+Chr$(34)+Menu\Settings\Shadows#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<blur setting="+Chr$(34)+Menu\Settings\MotionBlur#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<srays setting="+Chr$(34)+Menu\Settings\SunRays#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<sounds setting="+Chr$(34)+Menu\Settings\ThreeDSounds#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<theme setting="+Chr$(34)+Menu\Settings\Theme#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<bumpmaps setting="+Chr$(34)+Menu\Settings\BumpMaps#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<stadium setting="+Chr$(34)+Menu\Settings\StadiumDifficulty#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<plants setting="+Chr$(34)+Menu\Settings\DisablePlants#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<mods setting="+Chr$(34)+Menu\Settings\Mods#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<tips setting="+Chr$(34)+Menu\Settings\ControlTips#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<layout setting="+Chr$(34)+Menu\Settings\ControllerLayout#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<autocam setting="+Chr$(34)+Menu\Settings\AutoCameraDisabled#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<vsync setting="+Chr$(34)+Menu\Settings\VSync#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<viewrange setting="+Chr$(34)+Menu\Settings\ViewRange#+Chr$(34)+"/>")

	For i=0 To 17
	WriteLine(CurrentOpenFile,"<control no="+Chr$(34)+i+Chr$(34)+" control1="+Chr$(34)+CONTROLS(1,i)+Chr$(34)+" control2="+Chr$(34)+CONTROLS(2,i)+Chr$(34)+" gamepad="+Chr$(34)+CONTROLS_GAMEPAD(i)+Chr$(34)+"/>")
	Next

	For c=1 To CHAR_NORMALCOUNT
	WriteLine(CurrentOpenFile,"<char no="+Chr$(34)+(c)+Chr$(34)+" unlocked="+Chr$(34)+UNLOCKEDCHAR[c]+Chr$(34)+"/>")
	Next
	For c=1 To TEAM_TEAMCOUNT
	WriteLine(CurrentOpenFile,"<team no="+Chr$(34)+(c)+Chr$(34)+" unlocked="+Chr$(34)+UNLOCKEDTEAM[c]+Chr$(34)+"/>")
	Next
	WriteLine(CurrentOpenFile,"<specialstages unlocked="+Chr$(34)+UNLOCKEDSPECIALSTAGES[0]+Chr$(34)+"/>")
	For c=1 To 7
	WriteLine(CurrentOpenFile,"<emerald no="+Chr$(34)+(c)+Chr$(34)+" unlocked="+Chr$(34)+UNLOCKEDEMERALDS[c]+Chr$(34)+"/>")
	Next

	For c=1 To CHAR_PLAYABLECOUNT
	WriteLine(CurrentOpenFile,"<jumpamode no="+Chr$(34)+(c)+Chr$(34)+" is="+Chr$(34)+JUMPAMODE[c]+Chr$(34)+"/>")
	Next

	CloseWrittenFileWithEncryption()

	If saveemblems Then
		For i=1 To StageAmount
		SaveGame_Emblems(i)
		Next
	EndIf

	SaveGame_Inventory()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function LoadGame(loademblems=True)

	LoadFileWithEncryption("SETTINGS") : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "firsttime": Menu\FirstTime = xmlNodeAttributeValueGet(child, "is")

		Case "resolution": Menu\Settings\Resolution# = xmlNodeAttributeValueGet(child, "setting")
		Case "screen": Menu\Settings\ScreenMode# = xmlNodeAttributeValueGet(child, "setting")
		Case "debug": Menu\Settings\Debug# = xmlNodeAttributeValueGet(child, "setting")
		Case "debugnodes": Menu\Settings\DebugNodes# = xmlNodeAttributeValueGet(child, "setting")
		Case "volume": Menu\Settings\Volume# = xmlNodeAttributeValueGet(child, "setting")
		Case "volumesfx": Menu\Settings\VolumeSFX# = xmlNodeAttributeValueGet(child, "setting")
		Case "volumeva": Menu\Settings\VolumeVA# = xmlNodeAttributeValueGet(child, "setting")
		Case "volumem": Menu\Settings\VolumeM# = xmlNodeAttributeValueGet(child, "setting")
		Case "volumeamb": Menu\Settings\VolumeAmb# = xmlNodeAttributeValueGet(child, "setting")
		Case "dof": Menu\Settings\DepthOfField# = xmlNodeAttributeValueGet(child, "setting")
		Case "shadows": Menu\Settings\Shadows# = xmlNodeAttributeValueGet(child, "setting")
		Case "blur": Menu\Settings\MotionBlur# = xmlNodeAttributeValueGet(child, "setting")
		Case "srays": Menu\Settings\SunRays# = xmlNodeAttributeValueGet(child, "setting")
		Case "sounds": Menu\Settings\ThreeDSounds# = xmlNodeAttributeValueGet(child, "setting")
		Case "theme": Menu\Settings\Theme# = xmlNodeAttributeValueGet(child, "setting")
		Case "bumpmaps": Menu\Settings\BumpMaps# = xmlNodeAttributeValueGet(child, "setting")
		Case "stadium": Menu\Settings\StadiumDifficulty# = xmlNodeAttributeValueGet(child, "setting")
		Case "plants": Menu\Settings\DisablePlants# = xmlNodeAttributeValueGet(child, "setting")
		Case "mods": Menu\Settings\Mods# = xmlNodeAttributeValueGet(child, "setting")
		Case "tips": Menu\Settings\ControlTips# = xmlNodeAttributeValueGet(child, "setting")
		Case "layout": Menu\Settings\ControllerLayout# = xmlNodeAttributeValueGet(child, "setting")
		Case "autocam": Menu\Settings\AutoCameraDisabled# = xmlNodeAttributeValueGet(child, "setting")
		Case "vsync": Menu\Settings\VSync# = xmlNodeAttributeValueGet(child, "setting")
		Case "viewrange": Menu\Settings\ViewRange# = xmlNodeAttributeValueGet(child, "setting")

		Case "control": controlno = xmlNodeAttributeValueGet(child, "no") : CONTROLS(1,controlno) = xmlNodeAttributeValueGet(child, "control1") : CONTROLS(2,controlno) = xmlNodeAttributeValueGet(child, "control2") : CONTROLS_GAMEPAD(controlno) = xmlNodeAttributeValueGet(child, "gamepad")

		Case "char": i = xmlNodeAttributeValueGet(child, "no") : If i<=CHAR_NORMALCOUNT Then UNLOCKEDCHAR[i] = xmlNodeAttributeValueGet(child, "unlocked")
		Case "team": i = xmlNodeAttributeValueGet(child, "no") : If i<=TEAM_TEAMCOUNT Then UNLOCKEDTEAM[i] = xmlNodeAttributeValueGet(child, "unlocked")
		Case "specialstages": UNLOCKEDSPECIALSTAGES[0] = xmlNodeAttributeValueGet(child, "unlocked")
		Case "emerald": i = xmlNodeAttributeValueGet(child, "no") : If i<=7 Then UNLOCKEDEMERALDS[i] = xmlNodeAttributeValueGet(child, "unlocked")

		Case "jumpamode": i = xmlNodeAttributeValueGet(child, "no") : If i<=CHAR_PLAYABLECOUNT Then JUMPAMODE[i] = xmlNodeAttributeValueGet(child, "is")

	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

	If loademblems Then
		For i=1 To StageAmount
		LoadGame_Emblems(i)
		Next
	EndIf

	LoadGame_Inventory()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_Emblems(i,reset=False)

	If i<0 Then Return

	If reset Then SaveGame_Emblems_Reset(i)

	WriteFileWithEncryption("EMBLEMS"+i)

	For h=1 To 5
	WriteLine(CurrentOpenFile,"<emblems"+(h)+" 1="+Chr$(34)+EMBLEMS1(h,i)+Chr$(34)+" 2="+Chr$(34)+EMBLEMS2(h,i)+Chr$(34)+"/>")
Next

WriteLine(CurrentOpenFile,"<redrings 1="+Chr$(34)+REDRING1(i)+Chr$(34)+" 2="+Chr$(34)+REDRING2(i)+Chr$(34)+" 3="+Chr$(34)+REDRING3(i)+Chr$(34)+" 4="+Chr$(34)+REDRING4(i)+Chr$(34)+" 5="+Chr$(34)+REDRING5(i)+Chr$(34)+" all="+Chr$(34)+ALLREDRING(i)+Chr$(34)+"/>")


	CloseWrittenFileWithEncryption()

End Function

Function LoadGame_Emblems(i)

	If i<0 Then Return

	If Not(FileType(SaveDataPath$+"EMBLEMS"+i+SaveDataFormat$)=1) Then ;!

		SaveGame_Emblems(i,True)

	Else ;!

	LoadFileWithEncryption("EMBLEMS"+i) : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)

		;mission1-------------------------------------------------------------------------------
		Case "emblems1": LoadGame_Emblems_individual(child,1,i)
		;mission2-------------------------------------------------------------------------------
		Case "emblems2": LoadGame_Emblems_individual(child,2,i)
		;mission3-------------------------------------------------------------------------------
		Case "emblems3": LoadGame_Emblems_individual(child,3,i)
		;mission4-------------------------------------------------------------------------------
		Case "emblems4": LoadGame_Emblems_individual(child,4,i)
		;mission5-------------------------------------------------------------------------------
		Case "emblems5": LoadGame_Emblems_individual(child,5,i)
			
		;redrings------------------------------------------------------------------
		Case "redrings": LoadGame_RedRings_individual(child,i)
			
	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

	EndIf;!

End Function

Function LoadGame_Emblems_individual(child,h,i)
	EMBLEMS1(h,i)=xmlNodeAttributeValueGet(child, "1")
	EMBLEMS2(h,i)=xmlNodeAttributeValueGet(child, "2")
End Function

Function LoadGame_RedRings_individual(child,i)
	REDRING1(i)=xmlNodeAttributeValueGet(child, "1")
	REDRING2(i)=xmlNodeAttributeValueGet(child, "2")
	REDRING3(i)=xmlNodeAttributeValueGet(child, "3")
	REDRING4(i)=xmlNodeAttributeValueGet(child, "4")
	REDRING5(i)=xmlNodeAttributeValueGet(child, "5")
	ALLREDRING(i)=xmlNodeAttributeValueGet(child, "all")
	
End Function

Function SaveGame_Emblems_Reset(i)
	For h=1 To 5
	EMBLEMS1(h,i)=0
	EMBLEMS2(h,i)=0
Next

REDRING1(i)=0
REDRING2(i)=0
REDRING3(i)=0
REDRING4(i)=0
REDRING5(i)=0
ALLREDRING(i)=0
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_Records(i,reset=False)

	If reset Then SaveGame_Records_Reset(i)

	WriteFileWithEncryption("RECORDS"+i)

	RECORDS_NAME(1)=SaveGame_Records_GetStageName(i)
	WriteLine(CurrentOpenFile,"<stage name="+Chr$(34)+RECORDS_NAME(1)+Chr$(34)+"/>")

	For r=0 To 4
	For h=1 To 5
	WriteLine(CurrentOpenFile,"<records"+(h)+"-"+(r)+" rings="+Chr$(34)+RECORDS_RINGS(h,r)+Chr$(34)+" enemies="+Chr$(34)+RECORDS_ENEMIES(h,r)+Chr$(34)+" time="+Chr$(34)+RECORDS_TIME(h,r)+Chr$(34)+" score="+Chr$(34)+RECORDS_SCORE(h,r)+Chr$(34)+" rank="+Chr$(34)+RECORDS_RANK(h,r)+Chr$(34)+"/>")
	Next
	Next

	CloseWrittenFileWithEncryption()

End Function

Function LoadGame_Records(i)

	RECORDS_CURRENT=i

	If Not(FileType(SaveDataPath$+"RECORDS"+i+SaveDataFormat$)=1) Then ;!

		SaveGame_Records(i,True)

	Else ;!

	LoadFileWithEncryption("RECORDS"+i) : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "stage": RECORDS_NAME(1) = xmlNodeAttributeValueGet(child, "name")
		;mission1-------------------------------------------------------------------------------
		Case "records1-0": LoadGame_Records_individual(child,1,0)
		Case "records1-1": LoadGame_Records_individual(child,1,1)
		Case "records1-2": LoadGame_Records_individual(child,1,2)
		Case "records1-3": LoadGame_Records_individual(child,1,3)
		Case "records1-4": LoadGame_Records_individual(child,1,4)
		;mission2-------------------------------------------------------------------------------
		Case "records2-0": LoadGame_Records_individual(child,2,0)
		Case "records2-1": LoadGame_Records_individual(child,2,1)
		Case "records2-2": LoadGame_Records_individual(child,2,2)
		Case "records2-3": LoadGame_Records_individual(child,2,3)
		Case "records2-4": LoadGame_Records_individual(child,2,4)
		;mission3-------------------------------------------------------------------------------
		Case "records3-0": LoadGame_Records_individual(child,3,0)
		Case "records3-1": LoadGame_Records_individual(child,3,1)
		Case "records3-2": LoadGame_Records_individual(child,3,2)
		Case "records3-3": LoadGame_Records_individual(child,3,3)
		Case "records3-4": LoadGame_Records_individual(child,3,4)
		;mission4-------------------------------------------------------------------------------
		Case "records4-0": LoadGame_Records_individual(child,4,0)
		Case "records4-1": LoadGame_Records_individual(child,4,1)
		Case "records4-2": LoadGame_Records_individual(child,4,2)
		Case "records4-3": LoadGame_Records_individual(child,4,3)
		Case "records4-4": LoadGame_Records_individual(child,4,4)
		;mission5-------------------------------------------------------------------------------
		Case "records5-0": LoadGame_Records_individual(child,5,0)
		Case "records5-1": LoadGame_Records_individual(child,5,1)
		Case "records5-2": LoadGame_Records_individual(child,5,2)
		Case "records5-3": LoadGame_Records_individual(child,5,3)
		Case "records5-4": LoadGame_Records_individual(child,5,4)
	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

	EndIf;!

End Function

Function LoadGame_Records_individual(child,h,r)
	RECORDS_RINGS(h,r)=xmlNodeAttributeValueGet(child, "rings")
	RECORDS_ENEMIES(h,r)=xmlNodeAttributeValueGet(child, "enemies")
	RECORDS_TIME(h,r)=xmlNodeAttributeValueGet(child, "time")
	RECORDS_SCORE(h,r)=xmlNodeAttributeValueGet(child, "score")
	RECORDS_RANK(h,r)=xmlNodeAttributeValueGet(child, "rank")
End Function

Function SaveGame_Records_GetStageName$(i)
	If i<=StageAmount And i>0 Then
		Return StageName$(i)
	Else
		If i<0 Then
			Return "Special Stage "+Int(Abs(i))
		Else
			Return ""
		EndIf
	EndIf
End Function

Function SaveGame_Records_Reset(i)
	RECORDS_NAME(1)=SaveGame_Records_GetStageName$(i)
	For h=1 To 5
	RECORDS_RINGS(h,0)=0
	RECORDS_ENEMIES(h,0)=0
	RECORDS_TIME(h,0)=5999999
	RECORDS_SCORE(h,0)=0
	RECORDS_RANK(h,0)=7
	Next
	For r=1 To 4
	For h=1 To 5
	RECORDS_RINGS(h,r)=0
	RECORDS_ENEMIES(h,r)=0
	RECORDS_TIME(h,r)=0
	RECORDS_SCORE(h,r)=0
	RECORDS_RANK(h,r)=0
	Next
	Next
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_ResetAndSaveStageRecordsAndEmblems(i)
	If i<=StageAmount Then
		SaveGame_Emblems(i,True)
		SaveGame_Records(i,True)
	EndIf
End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function CleanUpSaveDirectory()
	SaveDir = ReadDir(SaveDataPath$)
	Repeat
		SaveFile$ = NextFile$(SaveDir)
		DeleteFile(SaveDataPath$+SaveFile$)
	Until (SaveFile$ = "")
	CloseDir(SaveDir)
End Function

Function ResetAll()
	CleanUpSaveDirectory()
	ResetOptions()
	ResetGame()
	ResetRecords()
	ResetGarden()
End Function

Function ResetOptions_Values()
	width#=GetSystemMetrics(0) : height#=GetSystemMetrics(1)
	If width#=1066 And height#=568 Then
		Menu\Settings\Resolution#=1
	ElseIf width#=1280 And height#=720 Then
		Menu\Settings\Resolution#=2
	ElseIf width#=1280 And height#=768 Then
		Menu\Settings\Resolution#=3
	ElseIf width#=1280 And height#=800 Then
		Menu\Settings\Resolution#=4
	ElseIf width#=1360 And height#=768 Then
		Menu\Settings\Resolution#=5
	ElseIf width#=1366 And height#=768 Then
		Menu\Settings\Resolution#=6
	ElseIf width#=1440 And height#=900 Then
		Menu\Settings\Resolution#=7
	ElseIf width#=1440 And height#=960 Then
		Menu\Settings\Resolution#=8
	ElseIf width#=1600 And height#=900 Then
		Menu\Settings\Resolution#=9
	ElseIf width#=1680 And height#=1050 Then
		Menu\Settings\Resolution#=10
	ElseIf width#=1920 And height#=1080 Then
		Menu\Settings\Resolution#=11
	ElseIf width#=1920 And height#=1200 Then
		Menu\Settings\Resolution#=12
	ElseIf width#=1536 And height#=864 Then
		Menu\Settings\Resolution#=6
	ElseIf width#=1280 And height#=1024 Then
		Menu\Settings\Resolution#=4
	ElseIf width#=1024 And height#=768 Then
		Menu\Settings\Resolution#=1
	ElseIf width#=800 And height#=600 Then
		Menu\Settings\Resolution#=1
	ElseIf width#<1366 Then
		Menu\Settings\Resolution#=1
	Else
		Menu\Settings\Resolution#=6
	EndIf
	Menu\Settings\ScreenMode#=0
	If Menu\Developer=1 Then Menu\Settings\Debug#=1 Else Menu\Settings\Debug#=0
	Menu\Settings\DebugNodes#=0
	Menu\Settings\Volume#=1.0
	Menu\Settings\VolumeSFX#=1.0
	Menu\Settings\VolumeVA#=1.0
	Menu\Settings\VolumeM#=1.0
	Menu\Settings\VolumeAmb#=1.0
	Menu\Settings\DepthOfField#=0
	Menu\Settings\Shadows#=0
	Menu\Settings\MotionBlur#=0
	Menu\Settings\SunRays#=0
	Menu\Settings\ThreeDSounds#=1
	Menu\Settings\Theme#=1
	Menu\Settings\BumpMaps#=0
	Menu\Settings\StadiumDifficulty#=1
	Menu\Settings\DisablePlants#=0
	Menu\Settings\Mods#=0
	Menu\Settings\ControlTips#=1
	Menu\Settings\ControllerLayout#=1
	Menu\Settings\AutoCameraDisabled#=0
	Menu\Settings\VSync#=0
	Menu\Settings\ViewRange#=0

	ResetOptions_ResetKeyboard()
	ResetOptions_ResetGamepad()
End Function

Function ResetOptions()

	Menu\FirstTime=0

	ResetOptions_Values()

	SaveGame(False)

End Function

Function ResetOptions_ResetKeyboard()
	CONTROLS(1,INPUT_BUTTON_UP)=KEY_ARROW_UP
	CONTROLS(1,INPUT_BUTTON_DOWN)=KEY_ARROW_DOWN
	CONTROLS(1,INPUT_BUTTON_LEFT)=KEY_ARROW_LEFT
	CONTROLS(1,INPUT_BUTTON_RIGHT)=KEY_ARROW_RIGHT
	CONTROLS(1,INPUT_BUTTON_CHANGE)=KEY_CTRL_RIGHT
	CONTROLS(1,INPUT_BUTTON_ACTIONJUMP)=KEY_A
	CONTROLS(1,INPUT_BUTTON_ACTIONROLL)=KEY_X
	CONTROLS(1,INPUT_BUTTON_ACTIONDRIFT)=KEY_TAB
	CONTROLS(1,INPUT_BUTTON_ACTIONSKILL1)=KEY_C
	CONTROLS(1,INPUT_BUTTON_ACTIONSKILL2)=KEY_S
	CONTROLS(1,INPUT_BUTTON_ACTIONSKILL3)=KEY_D
	CONTROLS(1,INPUT_BUTTON_ACTIONSKILLX)=KEY_Q
	CONTROLS(1,INPUT_BUTTON_ACTIONACT)=KEY_Z
	CONTROLS(1,INPUT_BUTTON_START)=KEY_ENTER
	CONTROLS(1,INPUT_BUTTON_BACK)=KEY_CTRL_LEFT
	CONTROLS(1,INPUT_BUTTON_CAM_LEFT)=KEY_SHIFT_LEFT
	CONTROLS(1,INPUT_BUTTON_CAM_RIGHT)=KEY_SHIFT_RIGHT
	CONTROLS(1,INPUT_BUTTON_CAM_CENTER)=KEY_ALT_LEFT
End Function

Function ResetOptions_ResetGamepadButtons()
	CONTROLS(2,INPUT_BUTTON_UP)=KEY_GAMEPAD_Y_MINUS
	CONTROLS(2,INPUT_BUTTON_DOWN)=KEY_GAMEPAD_Y_PLUS
	CONTROLS(2,INPUT_BUTTON_LEFT)=KEY_GAMEPAD_X_MINUS
	CONTROLS(2,INPUT_BUTTON_RIGHT)=KEY_GAMEPAD_X_PLUS
	CONTROLS(2,INPUT_BUTTON_CHANGE)=KEY_GAMEPAD_BUTTON7
	CONTROLS(2,INPUT_BUTTON_ACTIONJUMP)=KEY_GAMEPAD_BUTTON3
	CONTROLS(2,INPUT_BUTTON_ACTIONROLL)=KEY_GAMEPAD_BUTTON4
	CONTROLS(2,INPUT_BUTTON_ACTIONDRIFT)=KEY_GAMEPAD_BUTTON6
	CONTROLS(2,INPUT_BUTTON_ACTIONSKILL1)=KEY_GAMEPAD_BUTTON2
	CONTROLS(2,INPUT_BUTTON_ACTIONSKILL2)=KEY_GAMEPAD_BUTTON1
	CONTROLS(2,INPUT_BUTTON_ACTIONSKILL3)=KEY_GAMEPAD_BUTTON8
	CONTROLS(2,INPUT_BUTTON_ACTIONSKILLX)=KEY_GAMEPAD_BUTTON12
	CONTROLS(2,INPUT_BUTTON_ACTIONACT)=KEY_GAMEPAD_BUTTON9
	CONTROLS(2,INPUT_BUTTON_START)=KEY_GAMEPAD_BUTTON10
	CONTROLS(2,INPUT_BUTTON_BACK)=KEY_GAMEPAD_BUTTON5
	Select JoyType(g)
		Case 1:
			CONTROLS(2,INPUT_BUTTON_CAM_LEFT)=KEY_GAMEPAD_P_MINUS
			CONTROLS(2,INPUT_BUTTON_CAM_RIGHT)=KEY_GAMEPAD_P_PLUS
		Default:
			CONTROLS(2,INPUT_BUTTON_CAM_LEFT)=KEY_GAMEPAD_R_MINUS
			CONTROLS(2,INPUT_BUTTON_CAM_RIGHT)=KEY_GAMEPAD_R_PLUS
	End Select
	CONTROLS(2,INPUT_BUTTON_CAM_CENTER)=KEY_GAMEPAD_BUTTON11
End Function

Function ResetOptions_ResetGamepad()
	ResetOptions_ResetGamepadButtons()

	CONTROLS_GAMEPAD(INPUT_BUTTON_UP)=11
	CONTROLS_GAMEPAD(INPUT_BUTTON_DOWN)=12
	CONTROLS_GAMEPAD(INPUT_BUTTON_LEFT)=13
	CONTROLS_GAMEPAD(INPUT_BUTTON_RIGHT)=14
	CONTROLS_GAMEPAD(INPUT_BUTTON_CHANGE)=20
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONJUMP)=1
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONROLL)=2
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONDRIFT)=23
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONSKILL1)=3
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONSKILL2)=4
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONSKILL3)=21
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONSKILLX)=25
	CONTROLS_GAMEPAD(INPUT_BUTTON_ACTIONACT)=26
	CONTROLS_GAMEPAD(INPUT_BUTTON_START)=27
	CONTROLS_GAMEPAD(INPUT_BUTTON_BACK)=22
	CONTROLS_GAMEPAD(INPUT_BUTTON_CAM_LEFT)=18
	CONTROLS_GAMEPAD(INPUT_BUTTON_CAM_RIGHT)=19
	CONTROLS_GAMEPAD(INPUT_BUTTON_CAM_CENTER)=24
End Function

Function ResetOptions_ResetNewGamepad()
	ResetOptions_ResetGamepadButtons()

	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_UP)=11
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_DOWN)=12
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_LEFT)=13
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_RIGHT)=14
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CHANGE)=20
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONJUMP)=1
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONROLL)=2
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONDRIFT)=23
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILL1)=3
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILL2)=4
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILL3)=21
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONSKILLX)=25
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_ACTIONACT)=26
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_START)=27
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_BACK)=22
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CAM_LEFT)=18
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CAM_RIGHT)=19
	CONTROLS_NEWGAMEPAD(INPUT_BUTTON_CAM_CENTER)=24
End Function

Function ResetGame()

	Menu\SavedLives=5
	Menu\Wallet=0

	For c=1 To CHAR_NORMALCOUNT
	Select c
		Case CHAR_MIG,CHAR_RAY,CHAR_CHO,CHAR_NAC,CHAR_BEA,CHAR_BAR,CHAR_JET,CHAR_WAV,CHAR_STO,CHAR_TIA,CHAR_MPH,CHAR_MET,CHAR_TDL,CHAR_MKN,CHAR_TIK,CHAR_HBO,CHAR_HON,CHAR_SHD,CHAR_GAM,CHAR_EME,CHAR_EGG,CHAR_BET,CHAR_MT3,CHAR_GME,CHAR_PRS,CHAR_COM,CHAR_CHW,CHAR_EGR,CHAR_INF:
			UNLOCKEDCHAR[c]=0
		Default:
			UNLOCKEDCHAR[c]=1
	End Select
	Next
	For c=1 To TEAM_TEAMCOUNT
	Select c
		Case TEAM_TEAMCOUNT,TEAM_OLDIES,TEAM_HOOLIGAN,TEAM_BABYLON,TEAM_RELIC,TEAM_ROBOTNIK:
			UNLOCKEDTEAM[c]=0
		Default:
			UNLOCKEDTEAM[c]=1
	End Select
	Next
	UNLOCKEDSPECIALSTAGES[0]=0
	For c=1 To 7
		UNLOCKEDEMERALDS[c]=0
	Next

	SaveGame(False)

	DeleteMarathonList()

End Function

Function ResetRecords()

	For i=1 To StageAmount
		SaveGame_ResetAndSaveStageRecordsAndEmblems(i)
	Next

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Global TOUNLOCKCHAR[CHAR_NORMALCOUNT]
Global TOUNLOCKTEAM[TEAM_TEAMCOUNT]
Global TOUNLOCKSPECIALSTAGES[0]

	For c=1 To CHAR_NORMALCOUNT
	Select c
		Case CHAR_MET,CHAR_TDL,CHAR_MKN:			TOUNLOCKCHAR[c]=15
		Case CHAR_MIG,CHAR_RAY,CHAR_HBO:			TOUNLOCKCHAR[c]=20
		Case CHAR_NAC,CHAR_BEA,CHAR_BAR:			TOUNLOCKCHAR[c]=25
		Case CHAR_JET,CHAR_WAV,CHAR_STO:			TOUNLOCKCHAR[c]=30
		Case CHAR_SHD,CHAR_TIK,CHAR_CHO:			TOUNLOCKCHAR[c]=35
		Case CHAR_TIA,CHAR_HON:						TOUNLOCKCHAR[c]=40
		Case CHAR_GAM,CHAR_EME,CHAR_BET,CHAR_GME:	TOUNLOCKCHAR[c]=45
		Case CHAR_EGG,CHAR_MT3,CHAR_MPH:			TOUNLOCKCHAR[c]=50
		Case CHAR_PRS,CHAR_COM:						TOUNLOCKCHAR[c]=55
		Case CHAR_CHW:								TOUNLOCKCHAR[c]=60
		Case CHAR_EGR:								TOUNLOCKCHAR[c]=65
		Case CHAR_INF:								TOUNLOCKCHAR[c]=70
		Default: TOUNLOCKCHAR[c]=0 : UNLOCKEDCHAR[c]=1
	End Select
	Next

	For c=1 To TEAM_TEAMCOUNT
	Select c
		Case TEAM_TEAMCOUNT: TOUNLOCKTEAM[c]=5
		Case TEAM_ROBOTNIK: TOUNLOCKTEAM[c]=15
		Case TEAM_OLDIES: TOUNLOCKTEAM[c]=20
		Case TEAM_HOOLIGAN: TOUNLOCKTEAM[c]=25
		Case TEAM_BABYLON: TOUNLOCKTEAM[c]=30
		Case TEAM_RELIC: TOUNLOCKTEAM[c]=35
		Default: TOUNLOCKTEAM[c]=0 : UNLOCKEDTEAM[c]=1
	End Select
	Next

	TOUNLOCKSPECIALSTAGES[0]=10

Function CountEmblems()
	EMBLEMS=0
	REDRINGS=0
	For h=1 To 5
	For i=1 To StageAmount
		If EMBLEMS1(h,i)=1 Then EMBLEMS=EMBLEMS+1
		If EMBLEMS2(h,i)=1 Then EMBLEMS=EMBLEMS+1
	Next
Next

For i=1 To StageAmount
	If REDRING1(i)=1 Then REDRINGS=REDRINGS+1
	If REDRING2(i)=1 Then REDRINGS=REDRINGS+1
	If REDRING3(i)=1 Then REDRINGS=REDRINGS+1
	If REDRING4(i)=1 Then REDRINGS=REDRINGS+1
	If REDRING5(i)=1 Then REDRINGS=REDRINGS+1
Next

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_Chao(cc.tChaoManager)

	WriteFileWithEncryption("CHAO"+cc\Number)

	WriteLine(CurrentOpenFile,"<name is="+Chr$(34)+cc\Name$+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<age is="+Chr$(34)+cc\Stats\Age+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<persona is="+Chr$(34)+cc\Stats\Persona+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<color is="+Chr$(34)+cc\Stats\Color+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<shape is="+Chr$(34)+cc\Stats\Shape+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<side is="+Chr$(34)+cc\Stats\Side+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<hatch timer="+Chr$(34)+cc\HatchTimer+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<shell grit="+Chr$(34)+cc\Stats\ShellGrit#+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<pos x="+Chr$(34)+cc\Position\x#+Chr$(34)+" y="+Chr$(34)+cc\Position\y#+Chr$(34)+" z="+Chr$(34)+cc\Position\z#+Chr$(34)+" dir="+Chr$(34)+cc\g\Motion\Direction#+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<run level="+Chr$(34)+cc\Stats\Run#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentRun#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[1]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<swim level="+Chr$(34)+cc\Stats\Swim#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentSwim#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[2]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<fly level="+Chr$(34)+cc\Stats\Fly#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentFly#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[3]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<strength level="+Chr$(34)+cc\Stats\Strength#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentStrength#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[4]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<stamina level="+Chr$(34)+cc\Stats\Stamina#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentStamina#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[5]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<intelligence level="+Chr$(34)+cc\Stats\Intelligence#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentIntelligence#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[6]+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<luck level="+Chr$(34)+cc\Stats\Luck#+Chr$(34)+" current="+Chr$(34)+cc\Stats\CurrentLuck#+Chr$(34)+" boost="+Chr$(34)+cc\Stats\BoostSkills[7]+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<hunger need="+Chr$(34)+cc\Stats\Hunger#+Chr$(34)+" antineed="+Chr$(34)+cc\Stats\TooFull#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<sleep need="+Chr$(34)+cc\Stats\Sleep#+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<hungry timer="+Chr$(34)+cc\GetHungryTimer+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<sleepy timer="+Chr$(34)+cc\GetSleepyTimer+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<happiness has="+Chr$(34)+cc\Stats\Happiness#+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<revive able="+Chr$(34)+cc\Stats\ReviveAble+Chr$(34)+" eternal="+Chr$(34)+cc\Stats\ReviveEternal+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<love hero="+Chr$(34)+cc\Stats\HeroLove#+Chr$(34)+" dark="+Chr$(34)+cc\Stats\DarkLove#+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<mate season="+Chr$(34)+cc\Stats\MateSeason+Chr$(34)+" timer="+Chr$(34)+cc\MateTimer+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<hat is="+Chr$(34)+cc\Stats\Hat+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<competitions won="+Chr$(34)+cc\Stats\CompetitionsWon+Chr$(34)+" lost="+Chr$(34)+cc\Stats\CompetitionsLost+Chr$(34)+"/>")

	CloseWrittenFileWithEncryption()

End Function

Function LoadGame_Chao(cc.tChaoManager)

	LoadFileWithEncryption("CHAO"+cc\Number) : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "name": cc\Name$ = xmlNodeAttributeValueGet(child, "is")
		Case "age": cc\Stats\Age = xmlNodeAttributeValueGet(child, "is")
		Case "persona": cc\Stats\Persona = xmlNodeAttributeValueGet(child, "is")
		Case "color": cc\Stats\Color = xmlNodeAttributeValueGet(child, "is")
		Case "shape": cc\Stats\Shape = xmlNodeAttributeValueGet(child, "is")
		Case "side": cc\Stats\Side = xmlNodeAttributeValueGet(child, "is")

		Case "hatch": cc\HatchTimer = xmlNodeAttributeValueGet(child, "timer")
		Case "shell": cc\Stats\ShellGrit# = xmlNodeAttributeValueGet(child, "grit")

		Case "pos": cc\InitialPosition\x# = xmlNodeAttributeValueGet(child, "x") : cc\InitialPosition\y# = xmlNodeAttributeValueGet(child, "y") : cc\InitialPosition\z# = xmlNodeAttributeValueGet(child, "z") : cc\InitialDirection# = xmlNodeAttributeValueGet(child, "dir")

		Case "run": cc\Stats\Run# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentRun# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[1] = xmlNodeAttributeValueGet(child, "boost")
		Case "swim": cc\Stats\Swim# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentSwim# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[2] = xmlNodeAttributeValueGet(child, "boost")
		Case "fly": cc\Stats\Fly# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentFly# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[3] = xmlNodeAttributeValueGet(child, "boost")
		Case "strength": cc\Stats\Strength# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentStrength# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[4] = xmlNodeAttributeValueGet(child, "boost")
		Case "stamina": cc\Stats\Stamina# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentStamina# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[5] = xmlNodeAttributeValueGet(child, "boost")
		Case "intelligence": cc\Stats\Intelligence# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentIntelligence# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[6] = xmlNodeAttributeValueGet(child, "boost")
		Case "luck": cc\Stats\Luck# = xmlNodeAttributeValueGet(child, "level") : cc\Stats\CurrentLuck# = xmlNodeAttributeValueGet(child, "current") : cc\Stats\BoostSkills[7] = xmlNodeAttributeValueGet(child, "boost")

		Case "hunger": cc\Stats\Hunger# = xmlNodeAttributeValueGet(child, "need") : cc\Stats\TooFull# = xmlNodeAttributeValueGet(child, "antineed")
		Case "sleep": cc\Stats\Sleep# = xmlNodeAttributeValueGet(child, "need")
		Case "hungry": cc\GetHungryTimer = xmlNodeAttributeValueGet(child, "timer")
		Case "sleepy": cc\GetSleepyTimer = xmlNodeAttributeValueGet(child, "timer")

		Case "happiness": cc\Stats\Happiness# = xmlNodeAttributeValueGet(child, "has")

		Case "revive": cc\Stats\ReviveAble = xmlNodeAttributeValueGet(child, "able") : cc\Stats\ReviveEternal = xmlNodeAttributeValueGet(child, "eternal")

		Case "love": cc\Stats\HeroLove# = xmlNodeAttributeValueGet(child, "hero") : cc\Stats\DarkLove# = xmlNodeAttributeValueGet(child, "dark")

		Case "mate": cc\Stats\MateSeason = xmlNodeAttributeValueGet(child, "season") : cc\MateTimer = xmlNodeAttributeValueGet(child, "timer")

		Case "hat": cc\Stats\Hat = xmlNodeAttributeValueGet(child, "is")

		Case "competitions": cc\Stats\CompetitionsWon = xmlNodeAttributeValueGet(child, "won") : cc\Stats\CompetitionsLost = xmlNodeAttributeValueGet(child, "lost")
	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

End Function

Function LoadGame_ResetMenuChao()

	Menu\HeldChaoNumber=0

	Menu\HeldChaoName$ = ""
	Menu\HeldChaoAge = 0
	Menu\HeldChaoPersona = 0
	Menu\HeldChaoColor = 0
	Menu\HeldChaoShape = 0
	Menu\HeldChaoSide = 0
	For i=1 To 7
		Menu\HeldChaoSkills[i] = 0 : Menu\HeldChaoCurrentSkills[i] = 0
	Next
	Menu\HeldChaoEternal = 0
	Menu\HeldChaoHat = 0
	Menu\HeldChaoCompetitionsWon = 0
	Menu\HeldChaoCompetitionsLost = 0

End Function

Function LoadGame_MenuChao(number)

	LoadFileWithEncryption("CHAO"+number) : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "name": Menu\HeldChaoName$ = xmlNodeAttributeValueGet(child, "is")
		Case "age": Menu\HeldChaoAge = xmlNodeAttributeValueGet(child, "is")
		Case "persona": Menu\HeldChaoPersona = xmlNodeAttributeValueGet(child, "is")
		Case "color": Menu\HeldChaoColor = xmlNodeAttributeValueGet(child, "is")
		Case "shape": Menu\HeldChaoShape = xmlNodeAttributeValueGet(child, "is")
		Case "side": Menu\HeldChaoSide = xmlNodeAttributeValueGet(child, "is")

		Case "run": Menu\HeldChaoSkills[1] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[1] = xmlNodeAttributeValueGet(child, "current")
		Case "swim": Menu\HeldChaoSkills[2] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[2] = xmlNodeAttributeValueGet(child, "current")
		Case "fly": Menu\HeldChaoSkills[3] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[3] = xmlNodeAttributeValueGet(child, "current")
		Case "strength": Menu\HeldChaoSkills[4] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[4] = xmlNodeAttributeValueGet(child, "current")
		Case "stamina": Menu\HeldChaoSkills[5] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[5] = xmlNodeAttributeValueGet(child, "current")
		Case "intelligence": Menu\HeldChaoSkills[6] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[6] = xmlNodeAttributeValueGet(child, "current")
		Case "luck": Menu\HeldChaoSkills[7] = xmlNodeAttributeValueGet(child, "level") : Menu\HeldChaoCurrentSkills[7] = xmlNodeAttributeValueGet(child, "current")

		Case "revive": Menu\HeldChaoEternal = xmlNodeAttributeValueGet(child, "eternal")

		Case "hat": Menu\HeldChaoHat = xmlNodeAttributeValueGet(child, "is")

		Case "competitions": Menu\HeldChaoCompetitionsWon = xmlNodeAttributeValueGet(child, "won") : Menu\HeldChaoCompetitionsLost = xmlNodeAttributeValueGet(child, "lost")
	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_ChaoSlots()

	WriteFileWithEncryption("CHAOSLOTS")

	WriteLine(CurrentOpenFile,"<first timer="+Chr$(34)+CHAOFIRSTTIMER(1)+Chr$(34)+"/>")

	For i=1 To CHAOCOUNT
	WriteLine(CurrentOpenFile,"<slot chao="+Chr$(34)+i+Chr$(34)+" is="+Chr$(34)+CHAOSLOTS(1,i)+Chr$(34)+"/>")
	Next

	CloseWrittenFileWithEncryption()

End Function

Function LoadGame_ChaoSlots()

	LoadFileWithEncryption("CHAOSLOTS") : xmlin = xmlLoad(SaveDataTmp$)

	CHAOSUM(1)=0

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)

		Case "first":
		CHAOFIRSTTIMER(1)=xmlNodeAttributeValueGet(child, "timer")

		Case "slot":
		chaono=xmlNodeAttributeValueGet(child, "chao")
		CHAOSLOTS(1,chaono)=xmlNodeAttributeValueGet(child, "is")
		If CHAOSLOTS(1,chaono)=1 Then CHAOSUM(1)=CHAOSUM(1)+1

	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_ChaoGarden()

	SaveGame_ChaoSlots()

	WriteFileWithEncryption("CHAOGARDEN")

	WriteLine(CurrentOpenFile,"<chaonametag setting="+Chr$(34)+Menu\Settings\ChaoNameTag#+Chr$(34)+"/>")

	GetClosestGardenPoint()
	WriteLine(CurrentOpenFile,"<start x="+Chr$(34)+Game\Stage\Properties\StartX#+Chr$(34)+" y="+Chr$(34)+Game\Stage\Properties\StartY#+Chr$(34)+" z="+Chr$(34)+Game\Stage\Properties\StartZ#+Chr$(34)+" dir="+Chr$(34)+Game\Stage\Properties\StartDirection#+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<daytime cycle="+Chr$(34)+Game\Stage\Properties\SkyCycle+Chr$(34)+" timer="+Chr$(34)+Game\Stage\Properties\SkyCycleTimer+Chr$(34)+"/>")

	For o.tObject=Each tObject
	Select o\ObjType

		Case OBJTYPE_FRUIT:
		If o\ChaoObj\EatCycle>0 Then
		WriteLine(CurrentOpenFile,"<fruit type="+Chr$(34)+o\ChaoObj\FruitType+Chr$(34)+" growth="+Chr$(34)+o\ChaoObj\EatCycle+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")
		EndIf

		Case OBJTYPE_SHELL:
		WriteLine(CurrentOpenFile,"<shell type="+Chr$(34)+o\ChaoObj\ShellType+Chr$(34)+" type2="+Chr$(34)+o\ChaoObj\ShellType2+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+" dir="+Chr$(34)+o\Rotation\y#+Chr$(34)+"/>")

		Case OBJTYPE_HAT:
		WriteLine(CurrentOpenFile,"<hat type="+Chr$(34)+o\ChaoObj\HatType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")

		Case OBJTYPE_TOY:
		WriteLine(CurrentOpenFile,"<toy type="+Chr$(34)+o\ChaoObj\ToyType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")

		Case OBJTYPE_TROPICAL:
		If o\ChaoObj\IsFromSeed=False Then WriteLine(CurrentOpenFile,"<tree id="+Chr$(34)+o\ID+Chr$(34)+" growth1="+Chr$(34)+o\ChaoObj\FruitGrowth[1]+Chr$(34)+" growth2="+Chr$(34)+o\ChaoObj\FruitGrowth[2]+Chr$(34)+" growth3="+Chr$(34)+o\ChaoObj\FruitGrowth[3]+Chr$(34)+" growth4="+Chr$(34)+o\ChaoObj\FruitGrowth[4]+Chr$(34)+"/>")

		Case OBJTYPE_DRIVE:
		WriteLine(CurrentOpenFile,"<drive type="+Chr$(34)+o\ChaoObj\DriveType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+"/>")

		Case OBJTYPE_SEED:
		If o\ChaoObj\SeedMode>0 Then
			WriteLine(CurrentOpenFile,"<seed type="+Chr$(34)+o\ChaoObj\FruitType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+" mode="+Chr$(34)+1+Chr$(34)+" growth1="+Chr$(34)+o\ChaoObj\FruitGrowth[1]+Chr$(34)+" growth2="+Chr$(34)+o\ChaoObj\FruitGrowth[2]+Chr$(34)+" growth3="+Chr$(34)+o\ChaoObj\FruitGrowth[3]+Chr$(34)+" growth4="+Chr$(34)+o\ChaoObj\FruitGrowth[4]+Chr$(34)+" treegrowth="+Chr$(34)+o\ChaoObj\TreeGrowth+Chr$(34)+"/>")
		Else
			WriteLine(CurrentOpenFile,"<seed type="+Chr$(34)+o\ChaoObj\FruitType+Chr$(34)+" x="+Chr$(34)+o\Position\x#+Chr$(34)+" y="+Chr$(34)+o\Position\y#+Chr$(34)+" z="+Chr$(34)+o\Position\z#+Chr$(34)+" mode="+Chr$(34)+0+Chr$(34)+" growth1="+Chr$(34)+o\ChaoObj\FruitGrowth[1]+Chr$(34)+" growth2="+Chr$(34)+o\ChaoObj\FruitGrowth[2]+Chr$(34)+" growth3="+Chr$(34)+o\ChaoObj\FruitGrowth[3]+Chr$(34)+" growth4="+Chr$(34)+o\ChaoObj\FruitGrowth[4]+Chr$(34)+" treegrowth="+Chr$(34)+o\ChaoObj\TreeGrowth+Chr$(34)+"/>")
		EndIf

	End Select
	Next

	CloseWrittenFileWithEncryption()

	SaveGame_BlackMarket()

End Function

Function LoadGame_ChaoGarden()

	LoadGame_ChaoSlots()

	FRUITSUM(1)=0
	HATSUM(1)=0
	TOYSUM(1)=0
	DRIVESUM(1)=0
	SHELLSUM(1)=0
	SEEDSUM(1)=0

	LoadFileWithEncryption("CHAOGARDEN") : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)

		Case "chaonametag": Menu\Settings\ChaoNameTag# = xmlNodeAttributeValueGet(child, "setting")

		Case "start":
		Game\Stage\Properties\StartX#=xmlNodeAttributeValueGet(child, "x")
		Game\Stage\Properties\StartY#=xmlNodeAttributeValueGet(child, "y")
		Game\Stage\Properties\StartZ#=xmlNodeAttributeValueGet(child, "z")
		Game\Stage\Properties\StartDirection#=xmlNodeAttributeValueGet(child, "dir")
			If Game\Stage\Properties\StartDirection#<0 Then Game\Stage\Properties\StartDirection#=Game\Stage\Properties\StartDirection#+360
			Game\Gameplay\CheckX#=Game\Stage\Properties\StartX#
			Game\Gameplay\CheckY#=Game\Stage\Properties\StartY#
			Game\Gameplay\CheckZ#=Game\Stage\Properties\StartZ#
			Game\Gameplay\CheckDirection#=Game\Stage\Properties\StartDirection#

		Case "daytime":
		Game\Stage\Properties\SkyCycle=xmlNodeAttributeValueGet(child, "cycle")
		Game\Stage\Properties\SkyCycleTimer=xmlNodeAttributeValueGet(child, "timer")
		Stage_ForceUpdateCyclingSkyBox()

		Case "fruit":
		obj.tObject = Object_Fruit_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y"), xmlNodeAttributeValueGet(child, "z"), xmlNodeAttributeValueGet(child, "growth"))

		Case "shell":
		obj.tObject = Object_Shell_Create(xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y"), xmlNodeAttributeValueGet(child, "z"), xmlNodeAttributeValueGet(child, "dir"), xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "type2"))

		Case "hat":
		obj.tObject = Object_Hat_Create(xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y"), xmlNodeAttributeValueGet(child, "z"), xmlNodeAttributeValueGet(child, "type"))

		Case "toy":
		obj.tObject = Object_Toy_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y"), xmlNodeAttributeValueGet(child, "z"))

		Case "tree":
		For o.tObject = Each tObject
			treeid=xmlNodeAttributeValueGet(child, "id")
			If o\ObjType=OBJTYPE_TROPICAL And o\ID=treeid Then
				o\ChaoObj\FruitGrowth[1]=xmlNodeAttributeValueGet(child, "growth1")
				o\ChaoObj\FruitGrowth[2]=xmlNodeAttributeValueGet(child, "growth2")
				o\ChaoObj\FruitGrowth[3]=xmlNodeAttributeValueGet(child, "growth3")
				o\ChaoObj\FruitGrowth[4]=xmlNodeAttributeValueGet(child, "growth4")
			EndIf
		Next

		Case "drive":
		obj.tObject = Object_Drive_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y"), xmlNodeAttributeValueGet(child, "z"))

		Case "seed":
		obj.tObject = Object_Seed_Create(xmlNodeAttributeValueGet(child, "type"), xmlNodeAttributeValueGet(child, "x"), xmlNodeAttributeValueGet(child, "y"), xmlNodeAttributeValueGet(child, "z"), False, xmlNodeAttributeValueGet(child, "mode"), xmlNodeAttributeValueGet(child, "growth1"), xmlNodeAttributeValueGet(child, "growth2"), xmlNodeAttributeValueGet(child, "growth3"), xmlNodeAttributeValueGet(child, "growth4"), xmlNodeAttributeValueGet(child, "treegrowth"))

	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

	If FileType(SaveDataPath$+"BLACKMARKET"+SaveDataFormat$)=1 Then LoadGame_BlackMarket()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_AllChaoStuff()
	For cc.tChaoManager=Each tChaoManager
		If FileType(SaveDataPath$+"CHAO"+cc\Number+SaveDataFormat$)=1 Then SaveGame_Chao(cc)
	Next
	SaveGame_ChaoGarden()
End Function
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_Inventory()

	WriteFileWithEncryption("INVENTORY")

	WriteLine(CurrentOpenFile,"<lives saved="+Chr$(34)+Menu\SavedLives+Chr$(34)+"/>")
	WriteLine(CurrentOpenFile,"<wallet saved="+Chr$(34)+Menu\Wallet+Chr$(34)+"/>")

	For ii.tItem=Each tItem
		For n=1 To TOTALITEMS
		If ii\ID=n Then WriteLine(CurrentOpenFile,"<item id="+Chr$(34)+ii\ID+Chr$(34)+" type1="+Chr$(34)+ii\Type1+Chr$(34)+" type2="+Chr$(34)+ii\Type2+Chr$(34)+" type3="+Chr$(34)+ii\Type3+Chr$(34)+"/>")
		Next
	Next

	For cii.tCarriedItem=Each tCarriedItem
		For n=1 To TOTALCARRIEDITEMS
		If cii\ID=n Then WriteLine(CurrentOpenFile,"<citem id="+Chr$(34)+cii\ID+Chr$(34)+" type1="+Chr$(34)+cii\Type1+Chr$(34)+" type2="+Chr$(34)+cii\Type2+Chr$(34)+"/>")
		Next
	Next

	CloseWrittenFileWithEncryption()

End Function

Function LoadGame_Inventory()

	LoadFileWithEncryption("INVENTORY") : xmlin = xmlLoad(SaveDataTmp$)

	For ii.tItem=Each tItem
		Delete ii
	Next
	TOTALITEMS=0

	For cii.tCarriedItem=Each tCarriedItem
		Delete cii
	Next
	TOTALCARRIEDITEMS=0

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "lives":	Menu\SavedLives = xmlNodeAttributeValueGet(child, "saved") : If Menu\SavedLives>99 Then Menu\SavedLives=99
				If Menu\SavedLives<0 Then Menu\SavedLives=0
		Case "wallet":	Menu\Wallet = xmlNodeAttributeValueGet(child, "saved")
				If Menu\Wallet>99999 Then Menu\Wallet=99999
				If Menu\Wallet<0 Then Menu\Wallet=0
		Case "item":	ii.tItem = Item_Create(xmlNodeAttributeValueGet(child, "id"), xmlNodeAttributeValueGet(child, "type1"), xmlNodeAttributeValueGet(child, "type2"), xmlNodeAttributeValueGet(child, "type3"))
		Case "citem": 	If Menu\GameStarted=1 Then cii.tCarriedItem = CarriedItem_Create(xmlNodeAttributeValueGet(child, "id"), xmlNodeAttributeValueGet(child, "type1"), xmlNodeAttributeValueGet(child, "type2"))
	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

End Function
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveGame_BlackMarket()

	WriteFileWithEncryption("BLACKMARKET")

	WriteLine(CurrentOpenFile,"<market timer="+Chr$(34)+Menu\BlackMarketRandomizerTimer+Chr$(34)+"/>")

	For i=1 To ITEM_MAX(1)
		WriteLine(CurrentOpenFile,"<item type1="+Chr$(34)+1+Chr$(34)+" type2="+Chr$(34)+i+Chr$(34)+" type3="+Chr$(34)+ITEM_AVAILABLE_FRUITS(i)+Chr$(34)+"/>")
	Next

	For i=1 To ITEM_MAX(2)
		WriteLine(CurrentOpenFile,"<item type1="+Chr$(34)+2+Chr$(34)+" type2="+Chr$(34)+i+Chr$(34)+" type3="+Chr$(34)+ITEM_AVAILABLE_HATS(i)+Chr$(34)+"/>")
	Next

	For i=1 To ITEM_MAX(3)
		WriteLine(CurrentOpenFile,"<item type1="+Chr$(34)+3+Chr$(34)+" type2="+Chr$(34)+i+Chr$(34)+" type3="+Chr$(34)+ITEM_AVAILABLE_EGGS(i)+Chr$(34)+"/>")
	Next

	For i=1 To ITEM_MAX(5)
		WriteLine(CurrentOpenFile,"<item type1="+Chr$(34)+5+Chr$(34)+" type2="+Chr$(34)+i+Chr$(34)+" type3="+Chr$(34)+ITEM_AVAILABLE_TOYS(i)+Chr$(34)+"/>")
	Next

	CloseWrittenFileWithEncryption()

End Function

Function LoadGame_BlackMarket()

	LoadFileWithEncryption("BLACKMARKET") : xmlin = xmlLoad(SaveDataTmp$)

	For cchild = 1 To xmlNodeChildCount(xmlin)

	child = xmlNodeChild(xmlin, cchild)

	Select xmlNodeNameGet$(child)
		Case "market":	Menu\BlackMarketRandomizerTimer = xmlNodeAttributeValueGet(child, "timer")
		Case "item":
			Select xmlNodeAttributeValueGet(child, "type1")
				Case 1: ITEM_AVAILABLE_FRUITS(xmlNodeAttributeValueGet(child, "type2")) = xmlNodeAttributeValueGet(child, "type3")
				Case 2: ITEM_AVAILABLE_HATS(xmlNodeAttributeValueGet(child, "type2")) = xmlNodeAttributeValueGet(child, "type3")
				Case 3: ITEM_AVAILABLE_EGGS(xmlNodeAttributeValueGet(child, "type2")) = xmlNodeAttributeValueGet(child, "type3")
				Case 5: ITEM_AVAILABLE_TOYS(xmlNodeAttributeValueGet(child, "type2")) = xmlNodeAttributeValueGet(child, "type3")
			End Select
	End Select

	Next

	xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function ResetGarden()

	Menu\Settings\ChaoNameTag#=0

	DeleteFile(SaveDataPath$+"BLACKMARKET"+SaveDataFormat$)

	DeleteFile(SaveDataPath$+"CHAOGARDEN"+SaveDataFormat$)

	For i=1 To CHAOCOUNT
		If FileType(SaveDataPath$+"CHAO"+i+SaveDataFormat$)=1 Then Menu_Transporter_Goodbye_DeleteAChao(i) : i=i-1
	Next

	For ii.tItem=Each tItem
		Delete ii
	Next
	TOTALITEMS=0

	For cii.tCarriedItem=Each tCarriedItem
		Delete cii
	Next
	TOTALCARRIEDITEMS=0

	SaveGame_Inventory()

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function SaveMarathonList()

	WriteFileWithEncryption("MARATHON","marathon")

	WriteLine(CurrentOpenFile,"<random is="+Chr$(34)+Menu\MarathonRandom+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<last was="+Chr$(34)+Menu\MarathonStage+Chr$(34)+"/>")

	WriteLine(CurrentOpenFile,"<length is="+Chr$(34)+StageAmount+Chr$(34)+"/>")

	For i=1 To StageAmount-1
	WriteLine(CurrentOpenFile,"<stage no="+Chr$(34)+(i)+Chr$(34)+" stageno="+Chr$(34)+MarathonStage(i)+Chr$(34)+"/>")
	Next

	CloseWrittenFileWithEncryption("marathon")

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function LoadMarathonList()

	j=0

	If Not(FileType(SaveDataPath$+"MARATHON"+SaveDataFormat$)=1) Then
		Menu\MarathonExists=0
	Else
		LoadFileWithEncryption("MARATHON") : xmlin = xmlLoad(SaveDataTmp$)

		For cchild = 1 To xmlNodeChildCount(xmlin)

		child = xmlNodeChild(xmlin, cchild)

		Select xmlNodeNameGet$(child)
			Case "random": Menu\MarathonRandom = xmlNodeAttributeValueGet(child, "is")

			Case "last": Menu\MarathonStage = xmlNodeAttributeValueGet(child, "was")

			Case "length": j = xmlNodeAttributeValueGet(child, "is")

			Case "stage": i = xmlNodeAttributeValueGet(child, "no") : If i<=StageAmount-1 Then MarathonStage(i) = xmlNodeAttributeValueGet(child, "stageno")
		End Select

		Next

		xmlNodeDelete(xmlin) : CloseLoadedFileWithEncryption()

		If j<>StageAmount Then Menu\MarathonExists=0 Else Menu\MarathonExists=1
	EndIf

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Function DeleteMarathonList()

	If (FileType(SaveDataPath$+"MARATHON"+SaveDataFormat$)=1) Then
		Menu\MarathonExists=0
		DeleteFile(SaveDataPath$+"MARATHON"+SaveDataFormat$)
	EndIf

End Function

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~IDEal Editor Parameters:
;~C#Blitz3D