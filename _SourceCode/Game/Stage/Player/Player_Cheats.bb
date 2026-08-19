	; =========================================================================================================
	; =========================================================================================================




Function Player_HandleCheats(p.tPlayer)
	If p\No#=1 Then
		
		;spawn at origin, or cinema mode playing
		If (KeyHit(KEY_F2)) Then
			Select Game\CinemaMode
				Case 0:
				Default:
					PlaySmartSound(Sound_DebugOnOff)
					Select Game\CinemaMode
						Case 1: Game\CinemaMode=3
						Case 3: Game\CinemaMode=1
					End Select
			End Select
			Game\Cheater=1
		EndIf
		;object reset cheat
		If (KeyHit(KEY_F10)) Then
			If Game\Victory=0 Then Game\ResetObjects=1 : Game\Cheater=1
		EndIf
		
		;moonjump
		If (KeyDown(KEY_TAB)) Then
			p\Motion\Ground=False : p\Motion\Speed\y=2
			Game\Cheater=1
		EndIf
		
		;go debug placer
		If ((Menu\ChaoGarden=0 And Menu\TutorialMode=0 And Menu\CollectionRoom=0 And Menu\MarathonMode=0) Or Menu\Developer=1) And (Not(p\Action=ACTION_DIE Or p\Action=ACTION_DIEHURT)) Then
			If (KeyHit(KEY_DELETE)) Then Player_HandleCheats_DebugPlacer(p,1)
			If (KeyHit(KEY_F3)) Then Player_HandleCheats_DebugPlacer(p,1)
		EndIf
		
		If (KeyHit(KEY_F1)) Then 
			If Menu\Developer=0 Then Game\Cheater=1
			Game\Interface\ConsoleTimer=3*secs#
			Game\Interface\DebugConsole=Abs(Game\Interface\DebugConsole-1) Menu\InputRestrictTimer=0.05*secs#
			Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder+1
		EndIf
		
		If Game\Interface\DebugConsole=0 And Game\Interface\DebugPlacerOn=0 And Game\CinemaMode=0 Then
			
			For i = 1 To 9
				If KeyHit(i) Then
					Game\Interface\EnteredFavCommand=1
					Game\Interface\DebugConsoleString$=Menu\Settings\FavouriteCommand$[i]
					Game\Interface\DebugConsole=1
				EndIf
			Next
			
			If KeyHit(KEY_0)
				Game\Interface\EnteredFavCommand=1
				If Game\Interface\PreviousCommandOrder2>0 Then Game\Interface\PreviousCommandOrder2=Game\Interface\PreviousCommandOrder2-1
				Game\Interface\DebugConsoleString$=Game\Interface\PreviousDebugConsoleString$[Game\Interface\PreviousCommandOrder2]
				Game\Interface\DebugConsole=1
			EndIf
			
		EndIf
		
		If Menu\InputRestrictTimer>0 Then  Game\Interface\DebugConsoleString$=""
		
		
		
		
		
		
		
		
		
		
	EndIf
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D