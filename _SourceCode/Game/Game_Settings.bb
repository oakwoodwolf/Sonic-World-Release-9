

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Dim CONTROLS(2,17)
	Dim CONTROLS_GAMEPAD(17)
	Dim CONTROLS_NEWGAMEPAD(17)

	; ---- Game global settings ----
	Global 	GAME_TITLE$ = "Sonic World DX"
	Global 	GAME_WINDOW_W
	Global 	GAME_WINDOW_H
	Global	GAME_WINDOW_DEPTH
	Global 	GAME_WINDOW_MODE
	Global	GAME_WINDOW_SCALE#
	Global	GAME_WINDOW_SCALE2#
	Global	GAME_WINDOW_VSYNC

	; ---- Gameplay global settings ----
	Global	Gameplay_Camera_Smoothness#
	Global	Gameplay_Camera_MouseSmoothness#
	Global	Gameplay_Camera_RotationX#
	Global	Gameplay_Camera_RotationY#
	Global	Gameplay_Camera_RotationSpeedX#
	Global	Gameplay_Camera_RotationSpeedY#
	Global	Gameplay_Camera_MouseRotationSpeedX#
	Global	Gameplay_Camera_MouseRotationSpeedY#

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Game_LoadConfig()

		; Screen config
		Select Menu\Settings\Resolution#
			Case 1: GAME_WINDOW_W = 1066 : GAME_WINDOW_H = 568
			Case 2: GAME_WINDOW_W = 1280 : GAME_WINDOW_H = 720
			Case 3: GAME_WINDOW_W = 1280 : GAME_WINDOW_H = 768
			Case 4: GAME_WINDOW_W = 1280 : GAME_WINDOW_H = 800
			Case 5: GAME_WINDOW_W = 1360 : GAME_WINDOW_H = 768
			Case 6: GAME_WINDOW_W = 1366 : GAME_WINDOW_H = 768
			Case 7: GAME_WINDOW_W = 1440 : GAME_WINDOW_H = 900
			Case 8: GAME_WINDOW_W = 1440 : GAME_WINDOW_H = 960
			Case 9: GAME_WINDOW_W = 1600 : GAME_WINDOW_H = 900
			Case 10: GAME_WINDOW_W = 1680 : GAME_WINDOW_H = 1050
			Case 11: GAME_WINDOW_W = 1920 : GAME_WINDOW_H = 1080
			Case 12: GAME_WINDOW_W = 1920 : GAME_WINDOW_H = 1200
		End Select
		GAME_WINDOW_DEPTH = 32
		Select Menu\Settings\ScreenMode#
			Case 0: GAME_WINDOW_MODE = 2
			Case 1: GAME_WINDOW_MODE = 3
		End Select
		GAME_WINDOW_VSYNC = Menu\Settings\VSync#
		GAME_WINDOW_SCALE# = Float(GAME_WINDOW_W)/640.0
		Select Menu\Settings\Resolution#
			Case 1: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*1.00
			Case 2: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.90
			Case 3: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*1.80
			Case 4: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*2.55
			Case 5: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.60
			Case 6: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.45
			Case 7: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*1.55
			Case 8: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*2.30
			Case 9: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.25
			Case 10: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.95
			Case 11: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.15
			Case 12: GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.70
		End Select

		; Effects config
		FxManager_Activated      = 0
		FxManager_OnlyPowerOfTwo = 1
		FxManager_Debug          = 1
		FxManager_BorderFix      = 1

		; Input config
		Input_MouseSpeed       = 100
		Input_MouseSensitivy   = 1
		Input_Gamepad          = 0
		Input_GamepadThreshold = 0.2
		Game_MainControlConfig()

		For i=18 To 23
			Input\Configuration1[i]\Device = INPUT_DEVICE_MOUSE
		Next
		Input\Configuration1[INPUT_BUTTON_MOUSECAM_UP]\Button = INPUT_MOUSE_YMINUS
		Input\Configuration1[INPUT_BUTTON_MOUSECAM_DOWN]\Button = INPUT_MOUSE_YPLUS
		Input\Configuration1[INPUT_BUTTON_MOUSECAM_LEFT]\Button = INPUT_MOUSE_XMINUS
		Input\Configuration1[INPUT_BUTTON_MOUSECAM_RIGHT]\Button = INPUT_MOUSE_XPLUS
		Input\Configuration1[INPUT_BUTTON_MOUSECAM_ZOOMIN]\Button = INPUT_MOUSE_WHEELMINUS
		Input\Configuration1[INPUT_BUTTON_MOUSECAM_ZOOMOUT]\Button = INPUT_MOUSE_WHEELPLUS

		; Gameplay config
		Game_DecideCameraConfig()

	End Function

	Function Game_MainControlConfig()
		For i=0 To 17
			If CONTROLS(1,i)<0 Then
				Input\Configuration1[i]\Device = INPUT_DEVICE_MOUSE
				Select CONTROLS(1,i)
					Case KEY_MOUSE_LEFT: Input\Configuration1[i]\Button = INPUT_MOUSE_LEFT
					Case KEY_MOUSE_RIGHT: Input\Configuration1[i]\Button = INPUT_MOUSE_RIGHT
					Case KEY_MOUSE_MIDDLE: Input\Configuration1[i]\Button = INPUT_MOUSE_MIDDLE
				End Select
			ElseIf CONTROLS(1,i)=0 Then
				Input\Configuration1[i]\Device = INPUT_DEVICE_NONE
				Input\Configuration1[i]\Button = CONTROLS(1,i)
			Else
				Input\Configuration1[i]\Device = INPUT_DEVICE_KEYBOARD
				Input\Configuration1[i]\Button = CONTROLS(1,i)
			EndIf
		Next
		For i=0 To 17
			If CONTROLS(2,i)>=900 Then
				Input\Configuration2[i]\Device = INPUT_DEVICE_GAMEPAD
				Input\Configuration2[i]\Button = CONTROLS(2,i)-900
			ElseIf CONTROLS(2,i)=0 Then
				Input\Configuration2[i]\Device = INPUT_DEVICE_NONE
				Input\Configuration2[i]\Button = CONTROLS(2,i)
			EndIf
		Next
	End Function

	Function Game_DecideCameraConfig()
		Gameplay_Camera_Smoothness# = 0.33
		Gameplay_Camera_MouseSmoothness# = 0.6
		Gameplay_Camera_RotationX# = 170
		Gameplay_Camera_RotationY# = 0
		Gameplay_Camera_RotationSpeedX#	= 3
		Gameplay_Camera_RotationSpeedY#	= 15
		Gameplay_Camera_MouseRotationSpeedX# = 34
		Gameplay_Camera_MouseRotationSpeedY# = 34
	End Function

;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------------------------------------------

Function SetGameGraphics()
	If (Menu\Settings\Resolution#<=6 And Menu\Settings\ScreenMode#=0) Or (GfxMode3DExists(GAME_WINDOW_W, GAME_WINDOW_H, 16) Or GfxMode3DExists(GAME_WINDOW_W, GAME_WINDOW_H, 24) Or GfxMode3DExists(GAME_WINDOW_W, GAME_WINDOW_H, 32)) Then
		InitializeGraphicsWindow(GAME_WINDOW_W, GAME_WINDOW_H, GAME_WINDOW_DEPTH, GAME_WINDOW_MODE)
	Else 
		Menu\Settings\Resolution#=6
		Menu\Settings\ScreenMode#=0
		SaveGame()
		RuntimeError("Unable to set 3D graphics mode. Graphics settings were reset. Try again.")
	EndIf
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D