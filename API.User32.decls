.lib "user32.dll"
GetDC%(hWnd%):"GetDC"
ReleaseDC%( hWnd% , hDC% ) : "ReleaseDC"
MonitorFromRect%( pRect* , dwFlags% ) : "MonitorFromRect"
GetWindowLong%(hWnd%,index%):"GetWindowLongA"
SetWindowLong%( hwnd% , index% , info% ) : "SetWindowLongA"
SetWindowPos%( hWnd% , Depth% , x1%,y1%,x2%,y2% , Actions% ) : "SetWindowPos"
GetSystemMetrics%( nIndext% ) : "GetSystemMetrics"
FindWindow%( class$ , text$ ) : "FindWindowA"
ShowWindow%( hwnd% , nCmdShow% ) : "ShowWindow"
MoveWindow%( hwnd% , x%,y%,w%,h% , bRepaint% ) : "MoveWindow"
;BringWindowToTop%(hWnd%)
;SetWindowPos%(hWnd%,hWndInsertAfter,x%,y,cx%,cy%,flags%)
SetForegroundWindow%(hWnd%) : "SetForegroundWindow"
GetForegroundWindow%() : "GetForegroundWindow"
GetClassName%(hwnd%,classname*,max%):"GetClassNameA"
FindWindowText%(hWnd%,lpString$,maxCount%):"GetWindowTextA"
;GetWindowText% ( hwnd% , lpString* , cch% ) : "GetWindowTextA"
GetWindowTextLength% ( hwnd% ) : "GetWindowTextLengthA" 
SetWindowText ( hwnd% , lptext$ ) : "SetWindowTextA"
LoadCursor%(hInstance%,lpCursorName$):"LoadCursorA"
SetCursorPos( x%,y% ) : "SetCursorPos"
GetCursorPos( pt* ) : "GetCursorPos"
ShowCursor( bShow ) : "ShowCursor"
;SwapMouseButton%(bSwap)
SetClassLong%(hWnd%,nIndex%,Value%):"SetClassLongA"
RedrawWindow%( hWnd% , pRect% , hRegion% , rdwFlags% ) : "RedrawWindow"
SetFocus%(hWnd%) : "SetFocus"
GetDesktopWindow%() : "GetDesktopWindow"
GetWindow%(hWnd%,Cmd%):"GetWindow"
CloseWindow%(hWnd%):"CloseWindow"
;IsWindowVisible%(hWnd%) "
;GetTopWindow%(hWnd%)
;CascadeWindows%(HwndParent%,How%,Rect,Kids%,hKids)
;DrawIcon%(hDc%,x%,y%,hIcon%)
ScreenToClient% (hwnd%, lpPoint*) : "ScreenToClient"
ChildWindowFromPoint%(hWndParent%,x%,y%):"ChildWindowFromPoint"
GetActiveWindow%() : "GetActiveWindow"
InvalidateRect% (hwnd%, lpRect%, bErase%) : "InvalidateRect"
GetWindowThreadProcessId% ( hWnd%, ptrProcessId* ) : "GetWindowThreadProcessId"
SetLayeredWindowAttributes%( hwnd,crKey,bAlpha,dwFlags ) : "SetLayeredWindowAttributes"
GetClientRect( hwnd% , lprect* ) : "GetClientRect"
ClientToScreen% ( hwnd%, lpwndpl* ) : "ClientToScreen"
GetWindowRect%( hwnd% , lpRect* ) : "GetWindowRect"
;LockWindowUpdate( hWnd% ) : "LockWindowUpdate"
GetWindowPlacement%(hwnd%, pwpl*):"GetWindowPlacement"

;EnumDisplayDevices%( hDC% , num% , pEnum* , dwData* ) : EnumDisplayDevices

GetSystemMenu%( hWnd% , bRevert% ) : "GetSystemMenu"
DeleteMenu%( hMenuHandle% , uPos% , uFlags% ) : "DeleteMenu"

;GetMonitorInfo%( hMon% , pMonInfo* ) : "GetMonitorInfoA"
;GetAncestor%( hWnd% , gaFlags% ) : "GetAncestor"

;message handling
PeekMessage%( MSG* , hWnd% , MsgFilterMin%,MsgFilterMax% , RemoveMsg% ) : "PeekMessageA"
GetMessage%( MSG* , hWnd% , MsgFilterMin%,MsgFilterMax% ) : "GetMessageA"
SendMessage%( hwnd% , wMsg% , wParam%,lParam% ) : "SendMessageA"

;keyboard stuff
GetKeyboardState%( state* ) : "GetKeyboardState"
GetAsyncKeyState%( vKey% ) : "GetAsyncKeyState"
KeyboardEvent%( bVk%, bScan%, dwFlags%, dwExtraInfo% ) : "keybd_event" 

;clipboard
OpenClipboard%( hwnd% ) : "OpenClipboard"
CloseClipboard%() : "CloseClipboard"
ExamineClipboard%( format% ) : "IsClipboardFormatAvailable"
EmptyClipboard%() : "EmptyClipboard"
GetClipboardData%( format% ) : "GetClipboardData"
SetClipboardData%( format% , hMem% ) : "SetClipboardData"
SetClipboardDataDirectly%( format% , handle* ) : "SetClipboardData"
IsClipboardFormatAvailable%(wFormat%):"IsClipboardFormatAvailable"
GetClipboardText$ (wFormat%) : "GetClipboardData"
SetClipboardText%( wformat% , txt$ ) : "SetClipboardData"