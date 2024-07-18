	; =========================================================================================================
	; =========================================================================================================

Function Player_HandleCheats(p.tPlayer)
If p\No#=1 Then

	;spawn at origin, or cinema mode playing
	If (KeyHit(KEY_F1)) Then
		Select Game\CinemaMode
			Case 0:
				Player_SetPosition(p, Game\Stage\Properties\StartX#,Game\Stage\Properties\StartY#+10,Game\Stage\Properties\StartZ#,Game\Stage\Properties\StartDirection#)
			Default:
				PlaySmartSound(Sound_DebugOnOff)
				Select Game\CinemaMode
					Case 1: Game\CinemaMode=3
					Case 3: Game\CinemaMode=1
				End Select
		End Select
		Game\Cheater=1
	EndIf

	;die cheat
	If KeyHit(KEY_F2) and (Not(p\Action=ACTION_GRABBED Or p\Action=ACTION_DEBUG)) and Menu\ChaoGarden=0 Then Player_Die(p)

	;hurt cheat
	If KeyHit(KEY_HYPHEN) Then
		If Menu\ChaoGarden=0 Then
			Player_Hit(p)
		Else
			Object_Seed_Create(Rand(FRUIT_ROUND,FRUIT_RADISH), p\Objects\Position\x#, p\Objects\Position\y#, p\Objects\Position\z#, true)
		EndIf
		Game\Cheater=1
	EndIf

	;moonjump
	If (KeyDown(KEY_SPACE)) Then
		p\Motion\Ground=False : p\Motion\Speed\y=1.53
		Game\Cheater=1
	EndIf

	;object reset cheat
	If (KeyHit(KEY_F10)) Then
		If Game\Victory=0 Then Game\ResetObjects=1 : Game\Cheater=1
	EndIf

	;go debug placer
	If (Menu\ChaoGarden=0 Or Menu\Developer=1) and (Not(p\Action=ACTION_DIE)) and Chatting\Allowed=0 Then
		If (KeyHit(KEY_DELETE)) Then Player_HandleCheats_DebugPlacer(p,1)
		If (KeyHit(KEY_F3)) Then Player_HandleCheats_DebugPlacer(p,2)
	EndIf

	;character changing
	If Menu\Members=1 Then
		If KeyHit(KEY_PLUS) Then
			If p\RealCharacter>CHAR_NONMODPLAYABLECOUNT Then
				p\NewCharacter=1
			Else
				p\NewCharacter=p\RealCharacter+1
				If p\NewCharacter>CHAR_NONMODPLAYABLECOUNT Then p\NewCharacter=1
				If Not(UNLOCKEDCHAR[p\NewCharacter]=1) Then
					Repeat
						p\NewCharacter=p\NewCharacter+1
						If p\NewCharacter>CHAR_NONMODPLAYABLECOUNT Then p\NewCharacter=1
					Until UNLOCKEDCHAR[p\NewCharacter]=1
				EndIf
			EndIf
			Game\Cheater=1 : Game\CheaterChangedCharacter=1 : FlushKeys()
		EndIf
		If (Not(p\Action=ACTION_DEBUG)) Then
			If KeyHit(KEY_F12) Then
				Menu\CharacterRow=Menu\CharacterRow+1
				If Menu\CharacterRow>Ceil(CHAR_NONMODPLAYABLECOUNT/10.0) Then Menu\CharacterRow=1
				PlaySmartSound(Sound_MenuMove)
				Game\Cheater=1
			EndIf
			If Menu\CharacterRow>0 Then
				For i=KEY_1 to KEY_0
					j = (10*(Menu\CharacterRow-1))+i-1
					If j<=CHAR_NONMODPLAYABLECOUNT Then
					If KeyHit(i) and UNLOCKEDCHAR[j]=1 Then
						p\NewCharacter=j
						Game\Cheater=1 : Game\CheaterChangedCharacter=1 : FlushKeys()
						If p\NewCharacter>CHAR_NONMODPLAYABLECOUNT Then p\NewCharacter=1
					EndIf
					EndIf
				Next
			EndIf
		EndIf
	EndIf

	;go invincible
	If (KeyHit(KEY_F6)) Then
		Game\Invinc=1 : Game\InvincTimer=20.046391*secs#
		StopChannel(Game\Channel_Invincible) : StopChannel(Game\Channel_SpeedShoes)
		Game\Channel_Invincible=PlaySmartSound(Sound_Invincible)
		Game\Cheater=1
	EndIf

	;go speedshoes
	If (KeyHit(KEY_F7)) Then
		Game\SpeedShoes=1 : Game\SpeedShoeTimer=15.177130*secs#
		StopChannel(Game\Channel_Invincible) : StopChannel(Game\Channel_SpeedShoes)
		Game\Channel_SpeedShoes=PlaySmartSound(Sound_SpeedShoes)
		Game\Cheater=1
	EndIf

	If Menu\ChaoGarden=0 Then
		;ring cheat
		If (KeyHit(KEY_F8)) Then Gameplay_AddRings(50) : Game\Cheater=1

		;complete stage cheat
		If (KeyHit(KEY_F9)) and Menu\Stage>0 Then Game\Cheater=1 : Player_Goal(p)
	EndIf

	;cinema mode
	If (KeyHit(KEY_F11)) Then
		If Game\Victory=0 Then
			PlaySmartSound(Sound_DebugOnOff)
			Select Game\CinemaMode
				Case 0:
					Game\CinemaMode=1
					cam\CinemaX#=p\Objects\Position\x#
					cam\CinemaY#=p\Objects\Position\y#
					cam\CinemaZ#=p\Objects\Position\z#
				Default:
					Game\CinemaMode=0
			End Select
		EndIf
	EndIf

	;show fps
	If (KeyHit(KEY_F5)) Then Game\Interface\ShowFPS=Abs(Game\Interface\ShowFPS-1)

EndIf
End Function