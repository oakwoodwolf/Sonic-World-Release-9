
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function FlushAll()
	FlushKeys() : FlushJoy() : FlushMouse()
End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tInput_Configuration
		Field Device
		Field Button
	End Type
	
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tInput_Buttons
		Field Up
		Field Down
		Field Left
		Field Right
		Field Change
		Field ActionJump
		Field ActionRoll
		Field ActionDrift
		Field ActionSkill1
		Field ActionSkill2
		Field ActionSkill3
		Field ActionSkillX
		Field ActionAct
		Field Start
		Field Back
		Field CamLeft
		Field CamRight
		Field CamCenter
		Field MouseCamUp
		Field MouseCamDown
		Field MouseCamLeft
		Field MouseCamRight
		Field MouseCamZoomIn
		Field MouseCamZoomOut
	End Type
	
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tInput
		; ---- Input mode and configuration ----
		Field Mode
		Field Configuration1.tInput_Configuration[23]
		Field Configuration2.tInput_Configuration[23]
		Field Minimized[23]

		; ---- Input entries -----
		Field Hold.tInput_Buttons
		Field Pressed.tInput_Buttons
		Field AllowMouse

		; ---- Analogic Input ----
		Field Movement_AnalogX#
		Field Movement_AnalogY#
		Field Movement_Direction#
		Field Movement_Pressure#

		; ---- Analogic camera Input ----
		Field Camera_AnalogX#
		Field Camera_AnalogY#
		Field Camera_MouseAnalogX#
		Field Camera_MouseAnalogY#
		Field Camera_Direction#
		Field Camera_Pressure#
	End Type


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Global ThisWindowNumber = SystemProperty$("AppHWND")

	; ---- Input object -----
	Global Input.tInput = New tInput
	Input\Hold		= New tInput_Buttons
	Input\Pressed	= New tInput_Buttons
	For i=0 To 23 : Input\Configuration1[i] = New tInput_Configuration : Next
	For i=0 To 23 : Input\Configuration2[i] = New tInput_Configuration : Next

	; ---- Other values ----
	Global Input_Gamepad		= 0
	Global Input_GamepadThreshold#	= 0.6
	Global Input_GamepadThreshold2#	= 0.8
	Global Input_MouseSpeed#	= 100
	Global Input_MouseSensitivy#	= 1.4
	Global Input_MouseX#		= 0
	Global Input_MouseY#		= 0
	Global Input_MouseWheel#	= 0
	Global Input_Lock		= True


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---- Configuration constants ----
	Const INPUT_BUTTON_UP				= 0
	Const INPUT_BUTTON_DOWN				= 1
	Const INPUT_BUTTON_LEFT				= 2
	Const INPUT_BUTTON_RIGHT			= 3
	Const INPUT_BUTTON_CHANGE			= 4
	Const INPUT_BUTTON_ACTIONJUMP		= 5
	Const INPUT_BUTTON_ACTIONROLL		= 6
	Const INPUT_BUTTON_ACTIONDRIFT		= 7
	Const INPUT_BUTTON_ACTIONSKILL1		= 8
	Const INPUT_BUTTON_ACTIONSKILL2		= 9
	Const INPUT_BUTTON_ACTIONSKILL3		= 10
	Const INPUT_BUTTON_ACTIONACT		= 11
	Const INPUT_BUTTON_START			= 12
	Const INPUT_BUTTON_BACK				= 13
	Const INPUT_BUTTON_CAM_LEFT			= 14
	Const INPUT_BUTTON_CAM_RIGHT		= 15
	Const INPUT_BUTTON_CAM_CENTER		= 16
	Const INPUT_BUTTON_ACTIONSKILLX		= 17
	Const INPUT_BUTTON_MOUSECAM_UP		= 18
	Const INPUT_BUTTON_MOUSECAM_DOWN	= 19
	Const INPUT_BUTTON_MOUSECAM_LEFT	= 20
	Const INPUT_BUTTON_MOUSECAM_RIGHT	= 21
	Const INPUT_BUTTON_MOUSECAM_ZOOMIN	= 22
	Const INPUT_BUTTON_MOUSECAM_ZOOMOUT	= 23

	Const INPUT_DEVICE_NONE			= 0
	Const INPUT_DEVICE_KEYBOARD		= 1
	Const INPUT_DEVICE_GAMEPAD		= 2
	Const INPUT_DEVICE_MOUSE		= 3

	Const INPUT_MOUSE_XMINUS		= 0
	Const INPUT_MOUSE_XPLUS			= 1
	Const INPUT_MOUSE_YMINUS		= 2
	Const INPUT_MOUSE_YPLUS			= 3
	Const INPUT_MOUSE_WHEELMINUS		= 4
	Const INPUT_MOUSE_WHEELPLUS		= 5
	Const INPUT_MOUSE_LEFT			= 6
	Const INPUT_MOUSE_RIGHT			= 7
	Const INPUT_MOUSE_MIDDLE		= 8

	Const INPUT_GAMEPAD_XMINUS	= 1
	Const INPUT_GAMEPAD_XPLUS	= 2
	Const INPUT_GAMEPAD_YMINUS	= 3
	Const INPUT_GAMEPAD_YPLUS	= 4
	Const INPUT_GAMEPAD_ZMINUS	= 5
	Const INPUT_GAMEPAD_ZPLUS	= 6
	Const INPUT_GAMEPAD_DPAD_UP	= 7
	Const INPUT_GAMEPAD_DPAD_LEFT	= 8
	Const INPUT_GAMEPAD_DPAD_RIGHT	= 9
	Const INPUT_GAMEPAD_DPAD_DOWN	= 10
	Const INPUT_GAMEPAD_PMINUS	= 11
	Const INPUT_GAMEPAD_PPLUS	= 12
	Const INPUT_GAMEPAD_WMINUS	= 13
	Const INPUT_GAMEPAD_WPLUS	= 14
	Const INPUT_GAMEPAD_RMINUS	= 15
	Const INPUT_GAMEPAD_RPLUS	= 16


; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Input_Update()

	; If game window is minimized, have to reset controls
       If  GetActiveWindow() <> ThisWindowNumber Then
		FlushAll() : Input_ResetAllInput()
	EndIf

	If (Not(Menu\Menu=MENU_OPTIONS# And Menu\Menu2=MENU_CONTROLS#)) Or Menu\ButtonToChange=-1 Then

		maymove = ( (Not(Game\ControlLock>0)) And (Not(Game\StartoutLock>0)) And Game\Victory=0 )

		; ---- Check for input lock -----
		If Menu\Pause=0 And (Input\Pressed\Start) And Menu\Stage<>0 And Game\Victory=0 And Menu\ExitedAStage=0 And Game\Interface\DebugPlacerOn=0 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then
			Menu\Pause=1 : Input_ResetAllInput() : Game\SmartCameraRangeDontAffectTimer=3*secs#
			Input_Lock = False
			Menu\Option=1
			PlaySmartSound(Sound_MenuPause)
			Menu\Transition=0
			If Menu\ChaoGarden=1 Then
				SaveGame_AllChaoStuff()
				Game\Interface\AutoSaveShowTimer=0.5*secs#
				For i=1 to 3 : Game\Interface\GardenActionTimer[i]=0 : Next
			Else
				Game\Interface\ShowChaoItems=5
			EndIf
			PauseAllChannels()
		ElseIf Menu\Pause=1 And (((Input\Pressed\Start Or Input\Pressed\ActionJump) And Menu\Option=1) Or (Input\Pressed\ActionRoll Or Input\Pressed\ActionSkill1 Or Input\Pressed\Back)) Then
			Input_Lock = True
			PlaySmartSound(Sound_MenuBack)
			Menu\Pause=0 : Input_ResetAllInput() : Game\SmartCameraRangeDontAffectTimer=3*secs#
			FlushMouse()
			If Menu\ChaoGarden=1 Then
				For i=1 to 3 : Game\Interface\GardenActionTimer[i]=0 : Next
			EndIf
			ResumeAllChannels()
		EndIf
		
		; ---- Update mouse wheel, as it can only be checked once -----
		Input_MouseX# = MouseXSpeed()
		Input_MouseY# = MouseYSpeed()
		Input_MouseWheel# = MouseZSpeed()

		; ---- Update digital input -----
		If (Menu\Stage<>0) Or (Menu\Stage=0 and (Not(Game\ControlLock>0))) Then
			Input\Pressed\Start	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_START)) And (Input\Hold\Start = 0)
			Input\Pressed\Back	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_BACK)) And (Input\Hold\Back = 0)
			Input\Hold\Start	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_START))
			Input\Hold\Back		 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_BACK))
		EndIf

		; ---- Update digital input -----
		If Menu\Pause=1 Or (  (Not(Game\ControlLock>0)) and (Not(Game\StartoutLock>0)) and Game\Victory=0  ) Then
			Input\Pressed\Up 		 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_UP)) And (Input\Hold\Up = 0)
			Input\Pressed\Down		 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_DOWN)) And (Input\Hold\Down = 0)
			Input\Pressed\Left 			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_LEFT)) And (Input\Hold\Left = 0)
			Input\Pressed\Right 			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_RIGHT)) And (Input\Hold\Right = 0)
			Input\Pressed\Change	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CHANGE)) And (Input\Hold\Change = 0)
			Input\Pressed\ActionJump	 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONJUMP)) And (Input\Hold\ActionJump = 0)
			Input\Pressed\ActionRoll	 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONROLL)) And (Input\Hold\ActionRoll = 0)
			Input\Pressed\ActionDrift 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONDRIFT)) And (Input\Hold\ActionDrift = 0)
			Input\Pressed\ActionSkill1	 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILL1)) And (Input\Hold\ActionSkill1 = 0)
			Input\Pressed\ActionSkill2	 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILL2)) And (Input\Hold\ActionSkill2 = 0)
			Input\Pressed\ActionSkill3	 	= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILL3)) And (Input\Hold\ActionSkill3 = 0)
			Input\Pressed\ActionAct	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONACT)) And (Input\Hold\ActionAct = 0)
			Input\Pressed\CamLeft			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CAM_LEFT)) And (Input\Hold\CamLeft = 0)
			Input\Pressed\CamRight			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CAM_RIGHT)) And (Input\Hold\CamRight = 0)
			Input\Pressed\CamCenter			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CAM_CENTER)) And (Input\Hold\CamCenter = 0)
			Input\Pressed\ActionSkillX		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILLX)) And (Input\Hold\ActionSkillX = 0)
			Input\Pressed\MouseCamUp		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_UP,true)) And (Input\Hold\MouseCamUp = 0)
			Input\Pressed\MouseCamDown		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_DOWN,true)) And (Input\Hold\MouseCamDown = 0)
			Input\Pressed\MouseCamLeft		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_LEFT,true)) And (Input\Hold\MouseCamLeft = 0)
			Input\Pressed\MouseCamRight		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_RIGHT,true)) And (Input\Hold\MouseCamRight = 0)
			Input\Pressed\MouseCamZoomIn		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_ZOOMIN,true)) And (Input\Hold\MouseCamZoomIn = 0)
			Input\Pressed\MouseCamZoomOut		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_ZOOMOUT,true)) And (Input\Hold\MouseCamZoomOut = 0)

			Input\Hold\Up 		 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_UP))
			Input\Hold\Down		 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_DOWN))
			Input\Hold\Left 			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_LEFT))
			Input\Hold\Right 			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_RIGHT))
			Input\Hold\Change 			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CHANGE))
			Input\Hold\ActionJump	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONJUMP))
			Input\Hold\ActionRoll	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONROLL))
			Input\Hold\ActionDrift	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONDRIFT))
			Input\Hold\ActionSkill1	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILL1))
			Input\Hold\ActionSkill2	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILL2))
			Input\Hold\ActionSkill3	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILL3))
			Input\Hold\ActionAct	 		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONACT))
			Input\Hold\CamLeft			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CAM_LEFT))
			Input\Hold\CamRight			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CAM_RIGHT))
			Input\Hold\CamCenter			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_CAM_CENTER))
			Input\Hold\ActionSkillX			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_ACTIONSKILLX))
			Input\Hold\MouseCamUp			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_UP,true))
			Input\Hold\MouseCamDown			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_DOWN,true))
			Input\Hold\MouseCamLeft			= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_LEFT,true))
			Input\Hold\MouseCamRight		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_RIGHT,true))
			Input\Hold\MouseCamZoomIn		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_ZOOMIN,true))
			Input\Hold\MouseCamZoomOut		= Ceil#(Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_ZOOMOUT,true))
		EndIf

		; ---- Update analog movement ----
		If (Menu\Pause=1 Or maymove) Then
			Input\Movement_AnalogX# = Input_RetrieveStatus(INPUT_BUTTON_RIGHT)-Input_RetrieveStatus(INPUT_BUTTON_LEFT)
			Input\Movement_AnalogY# = Input_RetrieveStatus(INPUT_BUTTON_DOWN)-Input_RetrieveStatus(INPUT_BUTTON_UP)
		Else
			Input\Movement_AnalogX# = 0
			Input\Movement_AnalogY# = 0
		EndIf
		Input\Movement_Pressure# = Sqr#(Input\Movement_AnalogX#*Input\Movement_AnalogX#+Input\Movement_AnalogY#*Input\Movement_AnalogY#)
			; Square movement fix ------------
			If (Input\Movement_Pressure# > 1.0) Then Input\Movement_Pressure# = 1.0
			If (Input\Movement_Pressure# < -1.0) Then Input\Movement_Pressure# = -1.0
		If (Input\Movement_Pressure# <> 0.0) Then Input\Movement_Direction# = WrapAngle#(ATan2#(Input\Movement_AnalogY#, Input\Movement_AnalogX#))
		
		; ---- Update camera movement ----
		If (Menu\Pause=1 Or maymove) Then
			Input\Camera_AnalogX# = Input_RetrieveStatus(INPUT_BUTTON_RIGHT)-Input_RetrieveStatus(INPUT_BUTTON_LEFT)
			Input\Camera_AnalogY# = Input_RetrieveStatus(INPUT_BUTTON_DOWN)-Input_RetrieveStatus(INPUT_BUTTON_UP)
		Else
			Input\Camera_AnalogX# = 0
			Input\Camera_AnalogY# = 0
		EndIf
		If (Menu\Pause=1 Or maymove) and Input\AllowMouse Then
			Input\Camera_MouseAnalogX# = Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_RIGHT)-Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_LEFT)
			Input\Camera_MouseAnalogY# = Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_DOWN)-Input_RetrieveStatus(INPUT_BUTTON_MOUSECAM_UP)
		Else
			Input\Camera_MouseAnalogX# = 0
			Input\Camera_MouseAnalogY# = 0
		EndIf
		Input\Camera_Pressure# = Sqr#(Input\Camera_AnalogX#*Input\Camera_AnalogX#+Input\Camera_AnalogY#*Input\Camera_AnalogY#)
		If (Input\Camera_Pressure# <> 0.0) Then Input\Camera_Direction = WrapAngle#(ATan2#(Input\Camera_AnalogY#, Input\Camera_AnalogX#))
EndIf
	End Function 

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Input_IndividualMinimizedReset(inputno)
		If Ceil#(Input_RetrieveStatus(inputno)) Then Input\Minimized[inputno]=true
	End Function

	Function Input_ResetAllInput()
			Input\Pressed\Up 		 	= False
			Input\Pressed\Down		 	= False
			Input\Pressed\Left 			= False
			Input\Pressed\Right 		= False
			Input\Pressed\Change	 	= False
			Input\Pressed\ActionJump	 	= False
			Input\Pressed\ActionRoll	 	= False
			Input\Pressed\ActionDrift 		= False
			Input\Pressed\ActionSkill1	 	= False
			Input\Pressed\ActionSkill2	 	= False
			Input\Pressed\ActionSkill3	 	= False
			Input\Pressed\ActionSkillX	 	= False
			Input\Pressed\ActionAct	 		= False

			Input\Hold\Up 		 		= False
			Input\Hold\Down		 		= False
			Input\Hold\Left 			= False
			Input\Hold\Right 			= False
			Input\Hold\Change 			= False
			Input\Hold\ActionJump	 		= False
			Input\Hold\ActionRoll	 		= False
			Input\Hold\ActionDrift	 		= False
			Input\Hold\ActionSkill1	 		= False
			Input\Hold\ActionSkill2	 		= False
			Input\Hold\ActionSkill3	 		= False
			Input\Hold\ActionSkillX	 		= False
			Input\Hold\ActionAct	 		= False

			For i=0 to INPUT_BUTTON_BACK
				Input_IndividualMinimizedReset(i)
			Next
			Input_IndividualMinimizedReset(INPUT_BUTTON_ACTIONSKILLX)

			Input_ResetCameraInput()
	End Function

	Function Input_ResetCameraInput()
			Input\Pressed\CamLeft			= False
			Input\Pressed\CamRight			= False
			Input\Pressed\CamCenter			= False
			Input\Pressed\MouseCamUp		= False
			Input\Pressed\MouseCamDown		= False
			Input\Pressed\MouseCamLeft		= False
			Input\Pressed\MouseCamRight		= False
			Input\Pressed\MouseCamZoomIn		= False
			Input\Pressed\MouseCamZoomOut		= False

			Input\Hold\CamLeft			= False
			Input\Hold\CamRight			= False
			Input\Hold\CamCenter			= False
			Input\Hold\MouseCamUp			= False
			Input\Hold\MouseCamDown			= False
			Input\Hold\MouseCamLeft			= False
			Input\Hold\MouseCamRight		= False
			Input\Hold\MouseCamZoomIn		= False
			Input\Hold\MouseCamZoomOut		= False

			For i=INPUT_BUTTON_CAM_LEFT to INPUT_BUTTON_CAM_CENTER
				Input_IndividualMinimizedReset(i)
			Next
			For i=INPUT_BUTTON_MOUSECAM_UP to INPUT_BUTTON_MOUSECAM_ZOOMOUT
				Input_IndividualMinimizedReset(i)
			Next
	End Function

	Function Input_ResetActionInput()
			For i=INPUT_BUTTON_ACTIONJUMP to INPUT_BUTTON_ACTIONACT
				Input_IndividualMinimizedReset(i)
			Next
			Input_IndividualMinimizedReset(INPUT_BUTTON_ACTIONSKILLX)
	End Function

	Function Input_ResetActionInput2()
			Input\Pressed\ActionJump	 	= False
			Input\Pressed\ActionRoll 		= False
			Input\Pressed\ActionDrift 		= False
			Input\Pressed\ActionSkill1	 	= False
			Input\Pressed\ActionSkill2	 	= False
			Input\Pressed\ActionSkill3	 	= False
			Input\Pressed\ActionSkillX	 	= False
			Input\Pressed\ActionAct	 		= False

			Input\Hold\ActionJump	 		= False
			Input\Hold\ActionRoll 			= False
			Input\Hold\ActionDrift	 		= False
			Input\Hold\ActionSkill1	 		= False
			Input\Hold\ActionSkill2	 		= False
			Input\Hold\ActionSkill3	 		= False
			Input\Hold\ActionSkillX	 		= False
			Input\Hold\ActionAct	 		= False

			Input_ResetActionInput()
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Input_RetrieveStatus#(Button, nogamepad=false)
		g = Input_Gamepad
		Result1# = 0.0
		Result2# = 0.0

		; Get device status for keyboard and mouse
		Select Input\Configuration1[Button]\Device
			Case INPUT_DEVICE_NONE
				Result1# = 0.0
			Case INPUT_DEVICE_KEYBOARD
				If Input\Minimized[Button] Then
					If KeyHit(Input\Configuration1[Button]\Button) Then Input\Minimized[Button]=false
				EndIf
				If Input\Minimized[Button]=false Then Result1# = KeyDown(Input\Configuration1[Button]\Button)
			Case INPUT_DEVICE_MOUSE
				Select Input\Configuration1[Button]\Button
	 				Case INPUT_MOUSE_XMINUS		: If (Input_MouseX#<-1.0) Then Result1# = Abs(Input_MouseX#)*Input_MouseSensitivy#/Input_MouseSpeed#
	 				Case INPUT_MOUSE_XPLUS		: If (Input_MouseX#>1.0) Then Result1# = Abs(Input_MouseX#)*Input_MouseSensitivy#/Input_MouseSpeed#
	 				Case INPUT_MOUSE_YMINUS		: If (Input_MouseY#<-1.0) Then Result1# = Abs(Input_MouseY#)*Input_MouseSensitivy#/Input_MouseSpeed#
	 				Case INPUT_MOUSE_YPLUS		: If (Input_MouseY#>1.0) Then Result1# = Abs(Input_MouseY#)*Input_MouseSensitivy#/Input_MouseSpeed#
	 				Case INPUT_MOUSE_WHEELMINUS	: If (Input_MouseWheel#<0.0) Then Result1# = Abs(Input_MouseWheel#)
	 				Case INPUT_MOUSE_WHEELPLUS	: If (Input_MouseWheel#>0.0) Then Result1# = Abs(Input_MouseWheel#)
	 				Default:
						If Input\Minimized[Button] Then
							If MouseHit(Input\Configuration1[Button]\Button-INPUT_MOUSE_LEFT+1) Then Input\Minimized[Button]=false
						EndIf
						If Input\Minimized[Button]=false Then Result1# = MouseDown(Input\Configuration1[Button]\Button-INPUT_MOUSE_LEFT+1)
				End Select
		End Select

		; Get device status for gamepad
		If not nogamepad Then
		Select Input\Configuration2[Button]\Device
			Case INPUT_DEVICE_NONE
				Result2# = 0.0
			Case INPUT_DEVICE_GAMEPAD
				Select Input\Configuration2[Button]\Button
	 				Case INPUT_GAMEPAD_XMINUS	: If (JoyX#(g)<-Input_GamepadThreshold#) Then Result2# = (Abs(JoyX#(g))-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_XPLUS	: If (JoyX#(g)>Input_GamepadThreshold#)  Then Result2# = (Abs(JoyX#(g))-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_YMINUS	: If (JoyY#(g)<-Input_GamepadThreshold#) Then Result2# = (Abs(JoyY#(g))-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_YPLUS	: If (JoyY#(g)>Input_GamepadThreshold#)  Then Result2# = (Abs(JoyY#(g))-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_ZMINUS	: If (JoyZ#(g)<-Input_GamepadThreshold#) Then Result2# = (Abs(JoyZ#(g))-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_ZPLUS	: If (JoyZ#(g)>Input_GamepadThreshold#)  Then Result2# = (Abs(JoyZ#(g))-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
					Case INPUT_GAMEPAD_DPAD_UP	: If (Not(JoyHat(g)=-1)) and (JoyHat(g)=0) Then Result2# = 1.0
					Case INPUT_GAMEPAD_DPAD_LEFT	: If (Not(JoyHat(g)=-1)) and (JoyHat(g)=270) Then Result2# = 1.0
					Case INPUT_GAMEPAD_DPAD_RIGHT	: If (Not(JoyHat(g)=-1)) and (JoyHat(g)=90) Then Result2# = 1.0
					Case INPUT_GAMEPAD_DPAD_DOWN	: If (Not(JoyHat(g)=-1)) and (JoyHat(g)=180) Then Result2# = 1.0
	 				Case INPUT_GAMEPAD_PMINUS	: If ((JoyPitch#(g)/180.0)<-Input_GamepadThreshold#) Then Result2# = (Abs(JoyPitch#(g)/180.0)-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_PPLUS	: If ((JoyPitch#(g)/180.0)>Input_GamepadThreshold#)  Then Result2# = (Abs(JoyPitch#(g)/180.0)-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_WMINUS	: If ((JoyYaw#(g)/180.0)<-Input_GamepadThreshold#)   Then Result2# = (Abs(JoyYaw#(g)/180.0)-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_WPLUS	: If ((JoyYaw#(g)/180.0)>Input_GamepadThreshold#)    Then Result2# = (Abs(JoyYaw#(g)/180.0)-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Case INPUT_GAMEPAD_RMINUS	: If ((JoyRoll#(g)/180.0)<-Input_GamepadThreshold#)  Then Result2# = (Abs(JoyRoll#(g)/180.0)-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
					Case INPUT_GAMEPAD_RPLUS	: If ((JoyRoll#(g)/180.0)>Input_GamepadThreshold#)   Then Result2# = (Abs(JoyRoll#(g)/180.0)-Input_GamepadThreshold#)/(1-Input_GamepadThreshold#)
	 				Default	:
						If Input\Minimized[Button] Then
							If JoyHit(Input\Configuration2[Button]\Button-50, g) Then Input\Minimized[Button]=false
						EndIf
						If Input\Minimized[Button]=false Then Result2# = JoyDown(Input\Configuration2[Button]\Button-50, g)
				End Select
		End Select
		EndIf

		If (Result1# > 1.0) Then Result1# = 1.0
		If (Result2# > 1.0) Then Result2# = 1.0
		If Result1#>0 Then
			Menu\Settings\PrimaryController#=1
			Return Result1#
		ElseIf Result2#>0 Then
			Menu\Settings\PrimaryController#=2
			Return Result2#
		Else
			Return 0.0
		EndIf
		
	End Function