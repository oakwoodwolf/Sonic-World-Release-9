Function Menu_Character(option,option2)
	
	
	Return option+(option2-1)*(6*6-1)
End Function

Function Menu_Characters_Change(choose,nosound=False,chosen=False)
	
	Menu\ModCharCostumes[Menu\MemberToSelect]=1
	If (Not(nosound)) Then PlaySmartSound(Sound_MenuMove)
	
	Menu\MeshChange=1
	
	
	i = Ceil#(Menu\Option/6.0)
	Select choose
		Case -1,1: Menu\Option=Menu\Option+choose
		Case -2,2: Menu\Option=Menu\Option+6*(choose/2)
	End Select
	Select choose
		Case 1: If Menu\Option>i*6 Then
				Menu\Option=(i-1)*6+1
				If chosen Then Menu\Option=Menu\Option+6
			EndIf
		Case -1: If Menu\Option<(i-1)*6+1 Then Menu\Option=i*6
		Case 2: If Menu\Option>6*6 Then Menu\Option=Menu\Option-6*6
		Case -2: If Menu\Option<1 Then Menu\Option=Menu\Option+6*6
	End Select
	
End Function
Function Menu_Characters_Confirm()
	found=True
	
	If (Menu\Option>=6*6) Then
		found=True
	Else
		char = Menu_Character(Menu\Option,Menu\Option2)
		If char<=CHAR_NONMODPLAYABLECOUNT Or Menu\Settings\Mods#=1 Then
			If UNLOCKEDCHAR[char]=1 Then found=True Else found=False
		Else
			found=False
		EndIf
	EndIf
	
	
	If Menu_WasMemberChosen() And Menu\AndAnyone=0 Then found=False
	
	If found Then
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		If (Menu\Option>=6*6) Then
			Select Menu\MemberToSelect
				Case 1:
					Repeat : Menu\Character[1]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt1(Menu\Character[1])
				Case 2:
					Repeat : Menu\Character[2]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt2(Menu\Character[1],Menu\Character[2])
				Case 3:
					Repeat : Menu\Character[3]=Menu_RandomNonmodChar() : Until Menu_RandomNonmodChar_AcceptableAt3(Menu\Character[1],Menu\Character[2],Menu\Character[3])
			End Select
		Else
			Menu\Character[Menu\MemberToSelect]=Menu_Character(Menu\Option,Menu\Option2)
			If Menu\Character[Menu\MemberToSelect]=CHAR_SHA And Menu\CharacterMode[Menu\MemberToSelect]=1 Then Menu\Character[Menu\MemberToSelect]=40
		EndIf
		
		
		
		Select Menu\MemberToSelect
			Case Menu\Members:
				Menu\Team=0
				Select Menu\ChaoGarden
					Case 0:
						If Menu\MarathonMode=0 And Menu\TutorialMode=0 And Menu\CollectionRoom=0 Then
							Menu\NewOption=1 : Menu\NewMenu=MENU_STAGE2# 
						Else
							Menu_GoToStage()
						EndIf
					Case 1:
						Menu_GoToStage()
				End Select
			Default:
				Repeat : Menu_Characters_Change(1,True,True) : Until (Not(Menu_WasMemberChosen(1)))
				Menu\NewOption=Menu\Option
				Menu\NewOption2=Menu\Option2
				Menu\MemberToSelect=Menu\MemberToSelect+1
				Menu\ModCharCostumes[Menu\MemberToSelect]=1
				Menu\NewMenu=Menu\Menu
		End Select
	Else
		PlaySmartSound(Sound_MenuRefuse)
	EndIf
	
End Function
Function Menu_CharacterInRelease(char)
	
	If Menu\Developer=1 Then Return True
	
	Select char
		Case CHAR_SON,CHAR_TAI,CHAR_KNU,CHAR_SHA,CHAR_ROU,CHAR_OME,CHAR_AMY,CHAR_MIG,CHAR_CRE,CHAR_ESP,CHAR_RAY,CHAR_BLA,CHAR_GAM,CHAR_MET,CHAR_INF,CHAR_SIL
			Return True
		Default
			If UNLOCKEDCHAR[CHAR_EGR] And char=CHAR_EGR Then Return True
			Return False
	End Select
End Function
Function Menu_Characters_Update()
	
	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu
	
	
	If (Not(Menu\Option>=6*6)) Then
		If Menu_Character(Menu\Option,Menu\Option2)>CHAR_NONMODPLAYABLECOUNT Then
			
			If Menu\Settings\Mods#=0 Then
				Menu_PrintLocked(5,Menu_Character(Menu\Option,Menu\Option2),GAME_WINDOW_W/2-220*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-180)*GAME_WINDOW_SCALE#, 1)
			Else
				If UNLOCKEDCHAR[Menu_Character(Menu\Option,Menu\Option2)]=1 Then
					Menu_DrawCharacterNames(Menu_Character(Menu\Option,Menu\Option2), GAME_WINDOW_W/2-(140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+95.5)*GAME_WINDOW_SCALE#, True)
				Else
					Menu_PrintLocked(4,Menu_Character(Menu\Option,Menu\Option2),GAME_WINDOW_W/2-220*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-180)*GAME_WINDOW_SCALE#, 1)
					
				EndIf
			EndIf
		Else
			If UNLOCKEDCHAR[Menu_Character(Menu\Option,Menu\Option2)]=1 Then
				Menu_DrawCharacterNames(Menu_Character(Menu\Option,Menu\Option2), GAME_WINDOW_W/2-(140)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(CARD_PLACE#*2+95.5)*GAME_WINDOW_SCALE#, True)
			ElseIf (Not(Menu_CharacterInRelease(Menu_Character(Menu\Option,Menu\Option2)))) Then
				Menu_PrintLocked(6,Menu_Character(Menu\Option,Menu\Option2),GAME_WINDOW_W/2-220*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-180)*GAME_WINDOW_SCALE#, 1)
				
			Else
				Menu_PrintLocked(1,Menu_Character(Menu\Option,Menu\Option2),GAME_WINDOW_W/2-220*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-180)*GAME_WINDOW_SCALE#, 1)
			EndIf
		EndIf
	EndIf
	
	
	Menu_Characters_Roster(GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#)
	
	If Input\Pressed\Down Then Menu_Characters_Change(2)
	If Input\Pressed\Up Then Menu_Characters_Change(-2)
	If Input\Pressed\Right Then Menu_Characters_Change(1)
	If Input\Pressed\Left Then Menu_Characters_Change(-1)
	
	If Input\Pressed\ActionSkill2 And Menu\Members=1 And Menu\ChaoGarden=0 And Menu\CollectionRoom=0 And Menu\MarathonMode=0 Then
		If Menu_Character(Menu\Option,Menu\Option2)<CHAR_NONMODPLAYABLECOUNT Then
			Menu\TutorialMode=1
			Menu_Characters_Confirm()
		Else
			PlaySmartSound(Sound_MenuRefuse)
		EndIf
	EndIf
	
	If Input\Pressed\ActionDrift Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Option2=Menu\Option2+1
		If Menu\Option2>3 Then Menu\Option2=1
		Menu\MeshChange=1
	EndIf
	
	If Input\Pressed\ActionJump Then Menu_Characters_Confirm()
	
	
	
	If Input\Pressed\ActionRoll Or Input\Pressed\Back Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=1
		Select 1
			Case Menu\MarathonMode
				Menu\NewOption=3
			Case Menu\ChaoGarden
				Menu\NewOption=2
			Case Menu\CollectionRoom
				Menu\NewOption=4
				
				
		End Select
		Menu\NewMenu=MENU_PLAY#
		Menu\ChaoGarden=0
		Menu\CollectionRoom=0
		Menu\TutorialMode=0
	EndIf
	
	If Menu_Character(Menu\Option,Menu\Option2)>CHAR_NONMODPLAYABLECOUNT Then
		If MODCHARS_COSTUMES(Menu_Character(Menu\Option,Menu\Option2)-CHAR_MOD1+1)>1 Then
			
			DrawSmartKey(INPUT_BUTTON_ACTIONSKILL3, GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#+(7-1)*40*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#+(6-2)*40*GAME_WINDOW_SCALE#)
			DrawRealText(Menu\ModCharCostumes[Menu\MemberToSelect]+"/"+MODCHARS_COSTUMES(Menu_Character(Menu\Option,Menu\Option2)-CHAR_MOD1+1), GAME_WINDOW_W/2+(BUTTON_PLACE1#-150)*GAME_WINDOW_SCALE#+(7-1)*40*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+(-100)*GAME_WINDOW_SCALE#+(6-2)*40*GAME_WINDOW_SCALE#-20*GAME_WINDOW_SCALE#, (Interface_Text_2), 1)
			
			If Input\Pressed\ActionSkill3 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\ModCharCostumes[Menu\MemberToSelect]=Menu\ModCharCostumes[Menu\MemberToSelect]+1
				If Menu\ModCharCostumes[Menu\MemberToSelect]>MODCHARS_COSTUMES(Menu_Character(Menu\Option,Menu\Option2)-CHAR_MOD1+1) Then Menu\ModCharCostumes[Menu\MemberToSelect]=1
				Menu\MeshChange=1
			EndIf
		EndIf
	EndIf
	
	
	
	
	If Input\Pressed\ActionSkill3 Then
		If Menu_Character(Menu\Option,Menu\Option2)=CHAR_SHA Then
			PlaySmartSound(Sound_MenuBack)
			Menu\CharacterMode[Menu\MemberToSelect]=Abs(Menu\CharacterMode[Menu\MemberToSelect]-1)
			Menu\MeshChange=1
		EndIf
	EndIf
	
	
End Function



Function Menu_Characters_Roster_Chosen(x#, y#, char, i, j, h)
	If Menu\ButtonState1=0 Then Menu\ButtonSize1#=Menu\ButtonSize1#-BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#<0 Then Menu\ButtonState1=1 : Menu\ButtonSize1#=0
	If Menu\ButtonState1=1 Then Menu\ButtonSize1#=Menu\ButtonSize1#+BUTTON_SCALESPEED#*Game\DeltaTime\Delta# : If Menu\ButtonSize1#>BUTTON_SCALELIMIT# Then Menu\ButtonState1=0 : Menu\ButtonSize1#=BUTTON_SCALELIMIT#
	Menu\ButtonSize#=Menu\ButtonSize1#
	SetScale(GAME_WINDOW_SCALE#+Menu\ButtonSize#, GAME_WINDOW_SCALE#+Menu\ButtonSize#)
	;If (Not(Menu\Option>=6*6)) Then SetColor(Interface_Circle_R[InterfaceChar(char)],Interface_Circle_G[InterfaceChar(char)],Interface_Circle_B[InterfaceChar(char)])
	DrawImageEx(INTERFACE(Interface_CharacterSelector), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, 0)
	SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
	SetColor(255,255,255)
End Function

Function Menu_Characters_Roster(x#, y#)
	
	char=Menu_Character(Menu\Option,Menu\Option2)
	
	h=0
	For j=1 To 6
		For i=1 To 6
			h=h+1
			If Menu\Option=h Then Menu_Characters_Roster_Chosen(x#,y#,char,i,j,h)
			thischar=Menu_Character(h,Menu\Option2)
			
			If h<6*6 And thischar<=CHAR_PLAYABLECOUNT Then
				If Menu\AndAnyone=0 Or IsCharMod(thischar) Then
					If Menu\Character[1]=thischar And (Menu\MemberToSelect>1 Or Menu\NewMenu=MENU_STAGE# Or Menu\NewMenu=MENU_STAGE2#) Or (Menu\Character[2])=thischar And (Menu\MemberToSelect>2 Or Menu\NewMenu=MENU_STAGE# Or Menu\NewMenu=MENU_STAGE2#) Or (Menu\Character[3])=thischar And (Menu\NewMenu=MENU_STAGE# Or Menu\NewMenu=MENU_STAGE2#) Or UNLOCKEDCHAR[thischar]=0 Or (IsCharMod(thischar) And Menu\Settings\Mods#=0) Then SetColor(0,0,0)
				EndIf
				If thischar<=CHAR_NONMODPLAYABLECOUNT Then
					If Menu_CharacterInRelease(thischar) Then
						DrawImageEx(INTERFACE(Interface_Characters[thischar]), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#)
					Else
						SetColor(255,255,255)	
						DrawImageEx(INTERFACE(Interface_Icons), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, 1)
					EndIf
					
					
				Else
					
					If Menu\Settings\Mods#=1 Then
						
						If MODCHARS_FOUND(thischar-CHAR_NONMODPLAYABLECOUNT) Then found=True Else found=False
					Else
						found=False
					EndIf
					If found
						DrawImageEx(INTERFACE(Interface_CharactersMod[thischar-CHAR_NONMODPLAYABLECOUNT]), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#)
					Else
						DrawImageEx(INTERFACE(Interface_Icons), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, 27)
					EndIf
				EndIf
				SetColor(255,255,255)			
			Else
				DrawImageEx(INTERFACE(Interface_Icons), x#+(i-1)*40*GAME_WINDOW_SCALE#, y#+(j-1)*40*GAME_WINDOW_SCALE#, 27)
			EndIf
		Next
	Next
End Function
Function Menu_RandomNonmodChar()
	i = Rand(0,CHAR_INF)
	Select i
		Case 0: Return CHAR_SHN
		Default: Return i
	End Select
End Function

Function Menu_RandomNonmodChar_NotAcceptableTails(charA,charB)
	If (charA=CHAR_SHN And charB=CHAR_SHA) Or (charB=CHAR_SHN And charA=CHAR_SHA) Then Return True
	Return False
End Function

Function Menu_RandomNonmodChar_AcceptableAt1(char1,isplayer=True)
	If isplayer And (Not UNLOCKEDCHAR[char1]=1) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_AcceptableAt2(char1,char2,isplayer=True)
	If isplayer And (Not UNLOCKEDCHAR[char2]=1) Then Return False
	If char2=char1 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char1,char2) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_AcceptableAt3(char1,char2,char3,isplayer=True)
	If isplayer And (Not UNLOCKEDCHAR[char3]=1) Then Return False
	If char3=char1 Or char3=char2 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char1,char3) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char2,char3) Then Return False
	Return True
End Function

Function Menu_RandomNonmodChar_RivalAcceptableAt1(char,char1)
	If char=char1 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char1) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_RivalAcceptableAt2(char,char1,char2)
	If char=char1 Or char=char2 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char1) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char2) Then Return False
	Return True
End Function
Function Menu_RandomNonmodChar_RivalAcceptableAt3(char,char1,char2,char3)
	If char=char1 Or char=char2 Or char=char3 Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char1) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char2) Then Return False
	If Menu_RandomNonmodChar_NotAcceptableTails(char,char3) Then Return False
	Return True
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D