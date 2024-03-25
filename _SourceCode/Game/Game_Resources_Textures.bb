
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------

	Dim INTERFACE_TEXTURE(INTERFACE_TOTAL)
	Dim INTERFACE(INTERFACE_TOTAL)
	Dim INTERFACE_EXISTS(INTERFACE_TOTAL)

Function SmartImage(x)
		If INTERFACE_EXISTS(x)=False Then LoadSmartImage(x)
		Return x
	End Function

Function FreeSmartImage(x)
		If INTERFACE_EXISTS(x) Then
			FreeImage INTERFACE(x) : INTERFACE(x)=0
			FreeTexture INTERFACE_TEXTURE(x) : INTERFACE_TEXTURE(x)=0
			INTERFACE_EXISTS(x)=False
		EndIf
	End Function

Function LoadSmartFastImage(file$, x, w#, h#, f1=0, fa=1, sx#=1, sy#=1, background=False, semibackground=False, noalpha=False)
		Select noalpha
			Case False: INTERFACE_TEXTURE(x) = LoadAnimTexture(file$, 1+2+256, w#, h#, f1, fa)
			Case True: INTERFACE_TEXTURE(x) = LoadAnimTexture(file$, 1+256, w#, h#, f1, fa)
		End Select
		Select background
			Case False:
				Select semibackground
					Case False:
						INTERFACE(x) = CreateImageEx(INTERFACE_TEXTURE(x), w#/sx#, h#/sy#, FI_FILTERED)
					Case True:
						INTERFACE(x)= CreateImageEx(INTERFACE_TEXTURE(x), (GAME_WINDOW_W/GetBackgroundGraphicsScale#()), (h#/(GetBackgroundGraphicsScale#()*0.65)), FI_FILTERED)
				End Select
			Case True:
				Select semibackground
					Case False:
						INTERFACE(x)= CreateImageEx(INTERFACE_TEXTURE(x), (GAME_WINDOW_W/GetBackgroundGraphicsScale#()), (GAME_WINDOW_H/GetBackgroundGraphicsScale#()), FI_FILTERED)
					Case True:
						INTERFACE(x)= CreateImageEx(INTERFACE_TEXTURE(x), (GAME_WINDOW_W/GetBackgroundGraphicsScale#())*2, (GAME_WINDOW_H/GetBackgroundGraphicsScale#())*2, FI_FILTERED)
				End Select
		End Select
		MidHandleImage(INTERFACE(x))
		INTERFACE_EXISTS(x)=True
	End Function

Function GetBackgroundGraphicsScale#()
		graphicsscale# = ((Float(GraphicsWidth())/Float(GraphicsHeight()))/(1366.0/768.0))
		If Menu\Settings\ScreenMode#=1 Then
			If Float(GraphicsWidth())/1366.0 <= 1 Or Abs(graphicsscale#-1)<0.25 Then
				graphicsscale# = graphicsscale#*0.775
			Else
				graphicsscale# = graphicsscale#*0.9
			EndIf
		EndIf
			Select Menu\Settings\Resolution#
				Case 1: graphicsscale#=graphicsscale#*1.55
				Case 2: graphicsscale#=graphicsscale#*1.95
				Case 3: graphicsscale#=graphicsscale#*2.10
				Case 4: graphicsscale#=graphicsscale#*2.20
				Case 5,6: graphicsscale#=graphicsscale#*2.05
				Case 7,8,9: graphicsscale#=graphicsscale#*2.40
				Case 10,11,12: graphicsscale#=graphicsscale#*2.90
			End Select
		Return graphicsscale#
	End Function

;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------------------------------------------------------------------------

Function LoadSmartImage(x)

	Select x
		;Case Interface_Numbers:		LoadSmartFastImage("Interface/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
;==================================================
;==================================================
		Case Interface_Numbers:		
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Numbers.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Numbers.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
				EndIf
			EndIf
;========================================================================
;========================================================================
		;Hud left
		Case Interface_HudLeft
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/HudLeft.png")) Then 
					LoadSmartFastImage("Interface/Theme1/HudLeft.png", x,  500, 500, 0, 1, 2, 2)
					
					
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/HudLeft.png", x,  500, 500, 0, 1, 2, 2)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/HudLeft.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/HudLeft.png", x,  500, 500, 0, 1, 2, 2)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/HudLeft.png",x,  500, 500, 0, 1, 2, 2)
				EndIf
			EndIf
			
			
		Case Interface_HudRight
		;Hud right
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/HudRight.png")) Then 
					LoadSmartFastImage("Interface/Theme1/HudRight.png",x,  500, 500, 0, 1, 2, 2)
					
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/HudRight.png", x,  500, 500, 0, 1, 2, 2)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/HudRight.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/HudRight.png", x,  500, 500, 0, 1, 2, 2)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/HudRight.png", x,  500, 500, 0, 1, 2, 2)
				EndIf
			EndIf
			
;==================================================
;==================================================
	Case Interface_Text_1:		
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				EndIf
			EndIf
		Case Interface_Text_2:		;LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				EndIf
			EndIf
		Case Interface_Text_3:		;LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				EndIf
			EndIf			
		Case Interface_Text2_1:		
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
				EndIf
			EndIf
		Case Interface_Text2_2:		;LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
				EndIf
			EndIf
		Case Interface_Text2_3:		;LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
				EndIf
			EndIf
;==================================================
;==================================================
		Case Interface_Card1:		
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Card1.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Card1.png", x, 50, 417, 0, 2, 2, 1.55)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Card1.png", x, 50, 417, 0, 2, 2, 1.55)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Card1.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Card1.png", x, 50, 417, 0, 2, 2, 1.55)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Card1.png", x, 50, 417, 0, 2, 2, 1.55)
				EndIf
			EndIf
		Case Interface_Card2:		
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Card2.png")) Then 
					LoadSmartFastImage("Interface/Card2.png", x, 417, 50, 0, 1, 1.55, 2)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Card2.png", x, 417, 50, 0, 1, 1.55, 2)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Card2.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Card2.png", x, 417, 50, 0, 1, 1.55, 2)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Card2.png",x, 417, 50, 0, 1, 1.55, 2)
				EndIf
			EndIf
		Case Interface_Card3:		LoadSmartFastImage("Interface/Card3.png", x, 417, 50, 0, 1, 1.55, 2)
;========================================================================
;========================================================================
		Case Interface_Spinner
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Spinner.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Spinner.png", x, 400, 386, 0, 1, 3.785, 3.785)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Spinner.png", x, 400, 386, 0, 1, 3.785, 3.785)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Spinner.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Spinner.png", x, 400, 386, 0, 1, 3.785, 3.785)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Spinner.png", x, 400, 386, 0, 1, 3.785, 3.785)
				EndIf
			EndIf
		Case Interface_Spinner1
			LoadSmartFastImage("Interface/Theme5/Spinner1.png", x, 400, 386, 0, 1, 3.785, 3.785)
		Case Interface_Spinner2
			LoadSmartFastImage("Interface/Theme5/Spinner2.png", x, 400, 386, 0, 1, 3.785, 3.785)
		Case Interface_Spinner3
			LoadSmartFastImage("Interface/Theme5/Spinner3.png", x, 400, 386, 0, 1, 3.785, 3.785)
			
		Case Interface_Icons
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Icons.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Icons.png", x, 64, 70, 0, 36, 2, 2)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Icons.png",x, 64, 70, 0, 36, 2, 2)

				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Icons.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Icons.png", x, 64, 70, 0, 36, 2, 2)

				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Icons.png", x, 64, 70, 0, 36, 2, 2)
				EndIf
			EndIf
		Case Interface_Heads:		LoadSmartFastImage("Interface/Heads.png", x, 64, 70, 0, CHAR_NONMODPLAYABLECOUNT+1, 2, 2)
		Case Interface_Black:		LoadSmartFastImage("Interface/Black.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_BlackBig:	LoadSmartFastImage("Interface/Black.png", x, 1440, 900, 0, 1, 0, 0, True, True, True)
		Case Interface_Results:		;LoadSmartFastImage("Interface/Results.png", x, (770), (600/6.0), 0, 7, 2.2, 2.2)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Results.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Results.png", x, (770), (600/6.0), 0, 7, 2.2, 2.2)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Results.png", x, (770), (600/6.0), 0, 7, 2.2, 2.2)
					
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Results.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Results.png",  x, (770), (600/6.0), 0, 7, 2.2, 2.2)
					
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Results.png", x, (770), (600/6.0), 0, 7, 2.2, 2.2)
				EndIf
			EndIf
;========================================================================
;========================================================================
		Case Interface_Buttons_1:
				If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Buttons.png")) Then 
						LoadSmartFastImage("Interface/Theme1/Buttons.png", x, 400, 100, 0, 3, 2, 2)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Buttons.png", x, 400, 100, 0, 3, 2, 2)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Buttons.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/Buttons.png", x, 400, 100, 0, 3, 2, 2)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Buttons.png", x, 400, 100, 0, 3, 2, 2)
					EndIf
				EndIf
		Case Interface_Buttons_2:
				If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Buttons.png")) Then 
						LoadSmartFastImage("Interface/Theme1/Buttons.png", x, 400, 100, 0, 3, 3, 3)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Buttons.png", x, 400, 100, 0, 3, 3, 3)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Buttons.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/Buttons.png", x, 400, 100, 0, 3, 3, 3)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Buttons.png", x, 400, 100, 0, 3, 3, 3)
					EndIf
				EndIf
;========================================================================
;========================================================================
		Case Interface_TextButtons_1:	;LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					EndIf
				EndIf
;========================================================================
;========================================================================
Case Interface_TextButtons_2:	;LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					EndIf
				EndIf
;========================================================================
;========================================================================
			Case Interface_TextButtons2_1:	;LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons2.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons2.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
					EndIf
				EndIf
;========================================================================
;========================================================================
Case Interface_TextButtons2_2
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons2.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons2.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
					EndIf
				EndIf
;========================================================================
;========================================================================
			Case Interface_TextTitle_1:;	LoadSmartFastImage("Interface/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextTitle.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextTitle.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextTitle.png",  x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					EndIf
				EndIf
;========================================================================
;========================================================================
		Case Interface_TextTitle2_1:;	LoadSmartFastImage("Interface/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextTitle2.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextTitle2.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextTitle2.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextTitle2.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextTitle2.png",  x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextTitle2.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
					EndIf
				EndIf
;========================================================================
;========================================================================

			Case Interface_TextTitleChao_1:;	LoadSmartFastImage("Interface/TextTitleChao.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextTitleChao.png")) Then 
					LoadSmartFastImage("Interface/Theme1/TextTitleChao.png",  x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextTitleChao.png",   x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextTitleChao.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/TextTitle2.png",   x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextTitleChao.png",  x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
				EndIf
			EndIf	
			
		Case Interface_Keys:		LoadSmartFastImage("Interface/Keys.png", x, 80, 80, 0, 169, 3, 3)
		Case Interface_Keys_Small:	LoadSmartFastImage("Interface/Keys.png", x, 80, 80, 0, 169, 4.5, 4.5)
		Case Interface_Ranks:		;LoadSmartFastImage("Interface/Ranks.png", x, 256, 256, 0, 7, 3.82, 3.82)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Ranks.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Ranks.png", x, 256, 256, 0, 7, 3.82, 3.82)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Ranks.png", x, 256, 256, 0, 7, 3.82, 3.82)
					
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Ranks.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Ranks.png",  x, 256, 256, 0, 7, 3.82, 3.82)
					
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Ranks.png", x, 256, 256, 0, 7, 3.82, 3.82)
				EndIf
			EndIf
		Case Interface_TextControls_1:	;LoadSmartFastImage("Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextControls.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextControls.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextControls.png",  x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
					EndIf
				EndIf
		Case Interface_TextControls_2:	;LoadSmartFastImage("Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextControls.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextControls.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextControls.png",  x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					EndIf
				EndIf

		Case Interface_Saving:		LoadSmartFastImage("Interface/Saving.png", x, 55, 70, 0, 1, 2.5, 2.5)
		Case Interface_Indicator:	LoadSmartFastImage("Interface/Indicator.png", x, 256, 256, 0, 1, 4.95, 4.95)
		Case Interface_Sky::
				If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Sky.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Sky.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/Sky.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Sky.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
					EndIf
				EndIf

		Case Interface_Sky2:
						Select Menu\Settings\Theme#
						Case 14,26: LoadSmartFastImage("Interface/Sky"+Int(Menu\Settings\Theme#)+"b.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
						End Select
		Case Interface_Background1:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Background.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Background.png")=1) Then
									LoadSmartFastImage("Interface/Theme1/Background.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Background.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
								EndIf
							EndIf


		Case Interface_Background2:
						Select Menu\Settings\Theme#
						Case 4: LoadSmartFastImage("Interface/Theme4/Background2.png", x, 586, 1750, 0, 1, 0, 0, False, True)
						Case 5: LoadSmartFastImage("Interface/Theme5/Background2.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
						End Select
		Case Interface_Background3:
						Select Menu\Settings\Theme#
							Case 5: LoadSmartFastImage("Interface/Theme5/Background3.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
						End Select
		Case Interface_Background4:
						Select Menu\Settings\Theme#
							Case 5: LoadSmartFastImage("Interface/Theme5/Background4.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
						End Select
;========================================================================
;========================================================================
		Case Interface_TextRecords_1;:	LoadSmartFastImage("Interface/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextRecords1.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextRecords1.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextRecords1.png",  x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					EndIf
				EndIf
		Case Interface_TextRecords_2:	;LoadSmartFastImage("Interface/TextRecords2.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
				If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextRecords2.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextRecords2.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextRecords2.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextRecords2.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextRecords2.png",  x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextRecords2.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
					EndIf
				EndIf
;========================================================================
;========================================================================
		Case Interface_TextNames_1:	;LoadSmartFastImage("Interface/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextNames.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextNames.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextNames.png",  x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					EndIf
				EndIf

		Case Interface_TextNames2_1:	;LoadSmartFastImage("Interface/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextNames2.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextNames2.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextNames2.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextNames2.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextNames2.png",  x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextNames2.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
					EndIf
				EndIf
;========================================================================
;========================================================================
		Case Interface_TextOvers_1:	;LoadSmartFastImage("Interface/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextOvers1.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextOvers1.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextOvers1.png",  x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					EndIf
				EndIf

		Case Interface_TextOvers_2:	;LoadSmartFastImage("Interface/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextOvers2.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextOvers2.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextOvers2.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextOvers2.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextOvers2.png",  x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextOvers2.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
					EndIf
				EndIf

;========================================================================
;========================================================================
		Case Interface_TextCredits_1:;	LoadSmartFastImage("Interface/TextCredits.png", x, (640/16.0), (270/6.0), 0, 96, 3, 3)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
					If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextCredits.png")) Then 
						LoadSmartFastImage("Interface/Theme1/TextCredits.png", x, (640/16.0), (270/6.0), 0, 96, 3, 3)
					Else
						LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/TextCredits.png", x, (640/16.0), (270/6.0), 0, 96, 3, 3)
					EndIf 
				Else
					If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextCredits.png")=1) Then
						LoadSmartFastImage("Interface/Theme1/TextCredits.png",  x, (640/16.0), (270/6.0), 0, 96, 3, 3)
					Else
						LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/TextCredits.png", x, (640/16.0), (270/6.0), 0, 96, 3, 3)
					EndIf
				EndIf

				Case Interface_Circle:
						Select Menu\Settings\Theme#
						Case 15,30,32: LoadSmartFastImage("Interface/Circlew.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 16: LoadSmartFastImage("Interface/Circler.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 18: LoadSmartFastImage("Interface/Circlek.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 22: LoadSmartFastImage("Interface/Circles.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 23: LoadSmartFastImage("Interface/Circlebu.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 24: LoadSmartFastImage("Interface/Circlec.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 28: LoadSmartFastImage("Interface/Circlere.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 29: LoadSmartFastImage("Interface/Circled.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 31: LoadSmartFastImage("Interface/Circlelw.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Circle.png", x, 496, 500, 0, 1, 1.6, 1.6)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Circle.png")=1) Then
									LoadSmartFastImage("Interface/Circle.png", x, 496, 500, 0, 1, 1.6, 1.6)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Circle.png", x, 496, 500, 0, 1, 1.6, 1.6)
								EndIf
							EndIf
						End Select
		Case Interface_Circle2:
						Select Menu\Settings\Theme#
						Case 15,30,32: LoadSmartFastImage("Interface/Circlew.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 16: LoadSmartFastImage("Interface/Circler.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 18: LoadSmartFastImage("Interface/Circlek.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 22: LoadSmartFastImage("Interface/Circle2s.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 23: LoadSmartFastImage("Interface/Circle2bu.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 24: LoadSmartFastImage("Interface/Circlec.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 28: LoadSmartFastImage("Interface/Circle2re.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 29: LoadSmartFastImage("Interface/Circle2d.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Case 31: LoadSmartFastImage("Interface/Circlelw.png", x, 496, 500, 0, 1, 1.6, 1.6)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Circle2.png", x, 496, 500, 0, 1, 1.6, 1.6)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Circle2.png")=1) Then
									LoadSmartFastImage("Interface/Circle2.png", x, 496, 500, 0, 1, 1.6, 1.6)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Circle2.png", x, 496, 500, 0, 1, 1.6, 1.6)
								EndIf
							EndIf
					End Select
				Case Interface_RedRingBox
					If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
						If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/RedRingBox.png")) Then 
							LoadSmartFastImage("Interface/Theme1/RedRingBox.png",x,  500, 500, 0, 1, 2, 2)
							
						Else
							LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/RedRingBox.png", x,  500, 500, 0, 1, 2, 2)
						EndIf 
					Else
						If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/RedRingBox.png")=1) Then
							LoadSmartFastImage("Interface/Theme1/RedRingBox.png", x,  500, 500, 0, 1, 2, 2)
						Else
							LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/RedRingBox.png", x,  500, 500, 0, 1, 2, 2)
						EndIf
					EndIf
					
		Case Interface_Logo:
						If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
							LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Else
							If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo.png")=1) Then
								LoadSmartFastImage("Interface/Theme1/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
							EndIf
						EndIf
		Case Interface_Logo_ring:
						If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
							LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Logo_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Else
							If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo_ring.png")=1) Then
								LoadSmartFastImage("Interface/Theme1/Logo_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
							EndIf
						EndIf
		Case Interface_Logo_flash:
						If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
							LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Logo_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Else
							If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo_flash.png")=1) Then
								LoadSmartFastImage("Interface/Theme1/Logo_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
							EndIf
						EndIf
		Case Interface_LogoRipple:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
							LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Logo_Ripple.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Else
							If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo_Ripple.png")=1) Then
								LoadSmartFastImage("Interface/Theme1/Logo_Ripple.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Logo_Ripple.png", x, 800, 500, 0, 1, 2.5, 2.5)
							EndIf
						EndIf

		Case Interface_Logoxmas:
						Select Menu\Settings\Theme#
						Case 7: LoadSmartFastImage("Interface/Logoxmas.png", x, 800, 500, 0, 1, 2.5, 2.5)
						End Select
		Case Interface_Square:		;LoadSmartFastImage("Interface/Square.png", x, 496, 400, 0, 1, 2, 2)
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Square.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Square.png",x, 496, 400, 0, 1, 2, 2)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Square.png",x, 496, 400, 0, 1, 2, 2)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Square.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Square.png", x, 496, 400, 0, 1, 2, 2)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Square.png",x, 496, 400, 0, 1, 2, 2)
				EndIf
			EndIf
		Case Interface_Round:
						Select Menu\Settings\Theme#
						Case 5: LoadSmartFastImage("Interface/Theme5/Round.png", x, 700, 700, 0, 1, 2.05, 2.05) : Menu\RoundSize#=1
						Case 16: LoadSmartFastImage("Interface/Roundr.png", x, 700, 700, 0, 1, 2.65, 2.65)
						Case 30: LoadSmartFastImage("Interface/Roundu.png", x, 700, 700, 0, 1, 2.65, 2.65)
						End Select

					Case Interface_Bubble_1:
						Select Menu\Settings\Theme#
							Case 4: LoadSmartFastImage("Interface/Theme4/Bubble1.png", x, 200, 200, 0, 1, 3.5, 3.5)
							Case 27: LoadSmartFastImage("Interface/Bubble1sc.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
							Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						End Select
					Case Interface_Bubble_2:
						Select Menu\Settings\Theme#
							Case 4: LoadSmartFastImage("Interface/Theme4/Bubble2.png", x, 200, 200, 0, 1, 3.5, 3.5)
							Case 27: LoadSmartFastImage("Interface/Bubble2sc.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
							Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						End Select
					Case Interface_Bubble_3:
						Select Menu\Settings\Theme#
							Case 4: LoadSmartFastImage("Interface/Theme4/Bubble3.png", x, 200, 200, 0, 1, 3.5, 3.5)
							Case 27: LoadSmartFastImage("Interface/Bubble3sc.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
							Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						End Select
					Case Interface_Bubble_1_2:
						Select Menu\Settings\Theme#
							Case 4: LoadSmartFastImage("Interface/Theme4/Bubble1.png", x, 200, 200, 0, 1, 5.5, 5.5)
							Case 27: LoadSmartFastImage("Interface/Bubble1sc.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
							Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						End Select
					Case Interface_Bubble_2_2:
						Select Menu\Settings\Theme#
							Case 4: LoadSmartFastImage("Interface/Theme4/Bubble2.png", x, 200, 200, 0, 1, 5.5, 5.5)
							Case 27: LoadSmartFastImage("Interface/Bubble2sc.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
							Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						End Select
					Case Interface_Bubble_3_2:
						Select Menu\Settings\Theme#
							Case 4: LoadSmartFastImage("Interface/Theme4/Bubble3.png", x, 200, 200, 0, 1, 5.5, 5.5)
							Case 27: LoadSmartFastImage("Interface/Bubble3sc.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
							Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						End Select
					Case Interface_Characters:	
						If Menu\Settings\Theme#=2 Then 
							LoadSmartFastImage("Interface/Theme2/Characters.png", x, 2560/10.0, 1400/5.0, 0, CHAR_NONMODPLAYABLECOUNT+5, 6, 6)
						Else
							LoadSmartFastImage("Interface/Characters.png", x, 2560/10.0, 1400/5.0, 0, CHAR_NONMODPLAYABLECOUNT+5, 6, 6)
						EndIf 
						
		Case Interface_TextRecords_1:	LoadSmartFastImage("Interface/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
		Case Interface_Treasure:	LoadSmartFastImage("Interface/Treasure.png", x, 51, 51, 0, 4, 2, 2)
		Case Interface_Treasure_Big:	LoadSmartFastImage("Interface/Treasure.png", x, 51, 51, 0, 4, 1.7, 1.7)
		Case Interface_Flickies:	LoadSmartFastImage("Interface/Flickies.png", x, 157/2.0, 79, 0, 2, 2.64, 2.64)
		Case Interface_Caution:		LoadSmartFastImage("Interface/Caution.png", x, 126, 126, 0, 1, 3, 3)
		Case Interface_Inventory:	LoadSmartFastImage("Interface/Inventory.png", x, 48, 48, 0, 7, 2.75, 2.75)
		Case Interface_Monitors:	LoadSmartFastImage("Interface/Monitors.png", x, 128, 128, 0, 21, 4.25, 4.25)
		Case Interface_Boss:		LoadSmartFastImage("Interface/Boss.png", x, 488, 85, 0, 1, 3.2, 3.2)
		Case Interface_ProgressBar:	LoadSmartFastImage("Interface/ProgressBar.png", x, 400, 50, 0, 1, 1.5, 1.5)
		Case Interface_Progress: LoadSmartFastImage("Interface/Progress.png", x, 5, 50, 0, 80, 1.5, 1.5)
		Case Interface_BlackMarket:	LoadSmartFastImage("Interface/BlackMarket.png", x, (600), (134), 0, 10, 2.2, 2.2)
		Case Interface_Principal:	LoadSmartFastImage("Interface/Principal.png", x, 70, 70, 0, 9, 2.2, 2.2)
		Case Interface_Stats:		LoadSmartFastImage("Interface/Stats.png", x, (44), (28), 0, 9, 3.55, 3.55)
		Case Interface_Boxes:		LoadSmartFastImage("Interface/Boxes.png", x, 230, 60, 0, 4, 1.75, 1.75)
		Case Interface_ButtonsT:	LoadSmartFastImage("Interface/ButtonsT.png", x, (450), (764/7.0), 0, 7, 2.5, 2.5)
		Case Interface_ButtonsT_2:	LoadSmartFastImage("Interface/ButtonsT.png", x, (450), (764/7.0), 0, 7, 3.75, 3.75)
		Case Interface_Transporter1:	LoadSmartFastImage("Interface/Transporter1.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_Transporter2:	LoadSmartFastImage("Interface/Transporter2.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_Transporter3:	LoadSmartFastImage("Interface/Transporter3.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_Transporter4:	LoadSmartFastImage("Interface/Transporter4.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_Transporter5:	LoadSmartFastImage("Interface/Transporter5.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_Goodbye:		LoadSmartFastImage("Interface/Goodbye.png", x, 625, 425, 0, 1, 2.85, 2.85)
		Case Interface_Naming:		LoadSmartFastImage("Interface/Naming.png", x, 70, 70, 0, 81, 2.2, 2.2)
		Case Interface_Debug:		LoadSmartFastImage("Interface/Debug.png", x, 50, 50, 0, 36, 2.75, 2.75)
		Case Interface_Race:		LoadSmartFastImage("Interface/Race.png", x, 98, 100, 0, 9, 3.2, 3.2)
		Case Interface_Karate:		LoadSmartFastImage("Interface/Karate.png", x, (1320), (280)/2.0, 0, 2, 2.2, 2.2)
		Case Interface_KarateBars:	LoadSmartFastImage("Interface/KarateBars.png", x, (172)/(172/2.0), (42), 0, (172/2.0), 2.2, 2.2)
		Case Interface_Gauge
			
			If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
				If Not(FileType("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Gauge.png")) Then 
					LoadSmartFastImage("Interface/Theme1/Gauge.png"    , x,  500, 500, 0, 1, 2, 2)
				Else
					LoadSmartFastImage("Interface/Theme"+Int(Menu\Settings\Theme#)+"/Gauge.png", x,  500, 500, 0, 1, 2, 2)
				EndIf 
			Else
				If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Gauge.png")=1) Then
					LoadSmartFastImage("Interface/Theme1/Gauge.png", x,  500, 500, 0, 1, 2, 2)
				Else
					LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Interface/Gauge.png", x,  500, 500, 0, 1, 2, 2)
				EndIf
			EndIf
		Default:
			For i = Interface_HeadsMod[1] To Interface_HeadsMod[MODCHAR_AMOUNT]
				If x=i Then LoadMods_Character_InterfaceHead(i-Interface_HeadsMod[1]+1,x) : Return
			Next
			For i = Interface_CharactersMod[1] To Interface_CharactersMod[MODCHAR_AMOUNT]
				If x=i Then LoadMods_Character_InterfaceCharacter(i-Interface_CharactersMod[1]+1,x) : Return
			Next
	End Select

End Function
;~IDEal Editor Parameters:
;~C#Blitz3D