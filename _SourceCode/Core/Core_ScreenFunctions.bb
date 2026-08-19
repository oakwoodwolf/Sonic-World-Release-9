; -- Declare Windows API constants.
Const C_GWL_STYLE 			= -16
Const C_WS_POPUP 			= $80000000
Const C_HWND_TOP 			= 0
Const C_SWP_SHOWWINDOW 		= $0040

Const SW_HIDE				= 0
Const SW_SHOWNORMAL			= 1
Const SW_SHOWMINIMIZED		= 2
Const SW_SHOWMAXIMIZED		= 3
Const SW_SHOWNOACTIVATE		= 4
Const SW_SHOW				= 5
Const SW_MINIMIZE			= 6
Const SW_SHOWMINNOACTIVE	= 7
Const SW_SHOWNA				= 8
Const SW_RESTORE			= 9

; main window globals
Global GAME_WINDOW
Global GAME_APP_HANDLE
Global GAME_APP_ACTIVE

Global GAME_DESKTOP_WIDTH	= 0
Global GAME_DESKTOP_HEIGHT 	= 0
Global GAME_VIEWPORT_X# 	= 0
Global GAME_VIEWPORT_Y# 	= 0

; check game is focused
Function Game_HasFocus()
If GetActiveWindow() <> GAME_APP_HANDLE Then
Return False
Else
Return True
EndIf
End Function

; check if game is minimized
Function Game_IsMinimized()
	If GetWindowPlacement(GAME_WINDOW,GAME_APP_HANDLE) = SW_SHOWMINIMIZED Then
		Return True
	ElseIf GetWindowPlacement(GAME_WINDOW,GAME_APP_HANDLE) = SW_SHOWNORMAL Then
		Return False
	EndIf		
End Function

; hide/show functions
Function HideAppWindow(hWnd) : ShowWindow(hWnd,0) : End Function
Function ShowAppWindow(hWnd) : ShowWindow(hWnd,5) : End Function

; get desktop resolution
Function GetDesktopSize()
	Local rectangle = CreateBank( 16 )
	GetClientRect( GetDesktopWindow(), rectangle )
	G_desktop_screen_width = PeekInt( rectangle, 8 ) - PeekInt( rectangle, 0 )
	G_desktop_screen_height = PeekInt( rectangle, 12 ) - PeekInt( rectangle, 4 )
	FreeBank rectangle
End Function

; apply window graphics
Function InitializeGraphicsWindow(width%=1336, height%=768, depth%=32, mode%=2)

	; apply application name
	AppTitle(GAME_TITLE$)

	; init app handle
	GAME_APP_HANDLE = SystemProperty( "AppHWND" )

	; now find game window
	GAME_WINDOW = FindWindow("Blitz Runtime Class",GAME_TITLE$) 

	; get desktop res, and set active window
	GAME_APP_ACTIVE = GetActiveWindow()
	GetDesktopSize()

	; get to work
	Select mode
		Case 0,1,2:
			Graphics3D(width, height, depth, mode)
		Case 3:
			GAME_DESKTOP_WIDTH = GetSystemMetrics(0)
			GAME_DESKTOP_HEIGHT = GetSystemMetrics(1)
			; directx7 res limit
			If GAME_DESKTOP_WIDTH >  2048 Then GAME_DESKTOP_WIDTH =  2048
			If GAME_DESKTOP_HEIGHT > 1536 Then GAME_DESKTOP_HEIGHT = 1536
			Graphics3D(GAME_DESKTOP_WIDTH, GAME_DESKTOP_HEIGHT, depth, 2)
			; -- Change the window style to 'WS_POPUP' and then set the window position to force the style to update.
			SetWindowLong(GAME_APP_HANDLE, C_GWL_STYLE, C_WS_POPUP)
			SetWindowPos(GAME_APP_HANDLE, C_HWND_TOP, G_viewport_x, G_viewport_y, GAME_DESKTOP_WIDTH, GAME_DESKTOP_HEIGHT, C_SWP_SHOWWINDOW )
			ShowWindow(hwnd,3)
			; fix res sizes	
			GAME_WINDOW_W=GraphicsWidth()
			GAME_WINDOW_H=GraphicsHeight()		
			GAME_WINDOW_SCALE# = Float(GAME_WINDOW_W)/768.0
			GAME_WINDOW_SCALE2#=GAME_WINDOW_SCALE#*0.70
			graphicsscale# = (Float(GAME_WINDOW_W)/Float(GAME_WINDOW_H))
			If abs(graphicsscale#-1366.0/768.0)<1 Then
				If abs(GAME_WINDOW_W-1366.0)<1 Then
					Menu\Settings\Resolution#=6
				ElseIf abs(GAME_WINDOW_W-1600.0)<1 Then
					Menu\Settings\Resolution#=9
				ElseIf abs(GAME_WINDOW_W-1920.0)<1 Then
					If abs(GAME_WINDOW_H-1080.0)<1 Then
						Menu\Settings\Resolution#=11
					ElseIf abs(GAME_WINDOW_H-1200.0)<1 Then
						Menu\Settings\Resolution#=12
					EndIf
				ElseIf abs(GAME_WINDOW_W-1536.0)<1 Then
					Menu\Settings\Resolution#=6
				Else
					Menu\Settings\Resolution#=2
				EndIf
			EndIf
			HidePointer()
	End Select 

	; error upon old drivers
	If Not Windowed3D() Then RuntimeError "Graphics card uncapable of running this 3D program."

End Function