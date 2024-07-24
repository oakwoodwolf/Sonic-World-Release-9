
Function Interface_Render_PlayerMenu(p.tPlayer)
	
	DrawSmartKey_MovementGeneral((30)*GAME_WINDOW_SCALE#, (30+30*0)*GAME_WINDOW_SCALE#)
	DrawRealText("Select", (30+15)*GAME_WINDOW_SCALE#, (30+30*0)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONJUMP, (30)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#)
	DrawRealText("Confirm", (30+15)*GAME_WINDOW_SCALE#, (30+30*1)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	DrawSmartKey(INPUT_BUTTON_ACTIONROLL, (30)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#)
	DrawRealText("Back", (30+15)*GAME_WINDOW_SCALE#, (30+30*2)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, (30)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#)
	DrawRealText("Toggle Player Tag", (30+15)*GAME_WINDOW_SCALE#, (30+30*3)*GAME_WINDOW_SCALE#, (Interface_TextControls_1))
	
	SetScale(2*GAME_WINDOW_SCALE#,2*GAME_WINDOW_SCALE#)
	SetColor(Interface_Lives_R[InterfaceChar(Menu\Option)],Interface_Lives_G[InterfaceChar(Menu\Option)],Interface_Lives_B[InterfaceChar(Menu\Option)])
	Interface_DrawHead(GAME_WINDOW_W/2,GAME_WINDOW_H/2,Menu\Option-1)
	SetColor(255, 255, 255)
	If Menu\Option>CHAR_NONMODPLAYABLECOUNT Then
		DrawRealText( MODCHARS_NAME$( InterfaceChar(Menu\Option-CHAR_MOD1+1) ), GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
	Else
		DrawRealText(CharNames$(Menu\Option)+" "+CharNames2$(Menu\Option), GAME_WINDOW_W/2.0, GAME_WINDOW_H-160*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
	EndIf
	
	SetScale(GAME_WINDOW_SCALE#,GAME_WINDOW_SCALE#)
	
	If Menu\ButtonState2=0 Then Menu\ButtonSize2#=Menu\ButtonSize2#-BUTTON_SCALESPEED#*(Game\DeltaTime\Delta#*0.6) : If Menu\ButtonSize2#<0 Then Menu\ButtonState2=1 : Menu\ButtonSize2#=0
	If Menu\ButtonState2=1 Then Menu\ButtonSize2#=Menu\ButtonSize2#+BUTTON_SCALESPEED#*(Game\DeltaTime\Delta#*0.6) : If Menu\ButtonSize2#>BUTTON_SCALELIMIT# Then Menu\ButtonState2=0 : Menu\ButtonSize2#=BUTTON_SCALELIMIT#
	Menu\ButtonSize#=Menu\ButtonSize2#
	
	Select arrowtype
		Case -1
			arrowtype=19
		Case 1
			arrowtype=18
	End Select
	
	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2-50*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2, 19)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+50*GAME_WINDOW_SCALE#,GAME_WINDOW_H/2, 18)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	
	If Input\Pressed\Left Then 
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=CHAR_PLAYABLECOUNT
		PlaySmartSound(Sound_MenuMove)

	
		If UNLOCKEDCHAR[Menu\Option]=0 Then 
			Repeat
				Menu\Option=Menu\Option-1
			Until UNLOCKEDCHAR[Menu\Option]=1
		EndIf
		
		If Menu\Option<1 Then Menu\Option=CHAR_PLAYABLECOUNT
	EndIf
	
	
	If Input\Pressed\Right Then 
		Menu\Option=Menu\Option+1
		If Menu\Option>CHAR_PLAYABLECOUNT Then Menu\Option=1
		PlaySmartSound(Sound_MenuMove)
		
		If UNLOCKEDCHAR[Menu\Option]=0 Then 
			Repeat
				Menu\Option=Menu\Option+1
			Until UNLOCKEDCHAR[Menu\Option]=1
		EndIf
		
		If Menu\Option>CHAR_PLAYABLECOUNT Then Menu\Option=1
	EndIf
	
	If Input\Pressed\ActionJump  Then
		Menu\Character[1]=Menu\Option
		PostEffect_Create_FadeIn(0.004, 10, 10, 10)
		PlaySmartSound(Sound_MenuAccept)
		pp(1)\NewCharacter=Menu\Character[1]
		Game\CheaterChangedCharacter=1
		FlushKeys()
		UnPause()
	EndIf
	If Input\Pressed\ActionSkill3  Then
		onlineplayer(1)\Online\ShowTag= Not onlineplayer(1)\Online\ShowTag
		BP_UDPMessage(0,24,onlineplayer(1)\Online\ShowTag)
	EndIf
	If Input\Pressed\ActionRoll Then
		PlaySmartSound(Sound_MenuBack)
		Menu\PauseScreen=0 : Menu\Option=1
	EndIf	
	
End Function