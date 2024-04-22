
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
		If INTERFACE_EXISTS(x)=false Then LoadSmartImage(x)
		Return x
	End Function

	Function FreeSmartImage(x)
		If INTERFACE_EXISTS(x) Then
			FreeImage INTERFACE(x) : INTERFACE(x)=0
			FreeTexture INTERFACE_TEXTURE(x) : INTERFACE_TEXTURE(x)=0
			INTERFACE_EXISTS(x)=false
		EndIf
	End Function

	Function LoadSmartFastImage(file$, x, w#, h#, f1=0, fa=1, sx#=1, sy#=1, background=false, semibackground=false, noalpha=false)
		Select noalpha
			Case false: INTERFACE_TEXTURE(x) = LoadAnimTexture(file$, 1+2+256, w#, h#, f1, fa)
			Case true: INTERFACE_TEXTURE(x) = LoadAnimTexture(file$, 1+256, w#, h#, f1, fa)
		End Select
		Select background
			Case false:
				Select semibackground
					Case false:
						INTERFACE(x) = CreateImageEx(INTERFACE_TEXTURE(x), w#/sx#, h#/sy#, FI_FILTERED)
					Case true:
						INTERFACE(x)= CreateImageEx(INTERFACE_TEXTURE(x), (GAME_WINDOW_W/GetBackgroundGraphicsScale#()), (h#/(GetBackgroundGraphicsScale#()*0.65)), FI_FILTERED)
				End Select
			Case true:
				Select semibackground
					Case false:
						INTERFACE(x)= CreateImageEx(INTERFACE_TEXTURE(x), (GAME_WINDOW_W/GetBackgroundGraphicsScale#()), (GAME_WINDOW_H/GetBackgroundGraphicsScale#()), FI_FILTERED)
					Case true:
						INTERFACE(x)= CreateImageEx(INTERFACE_TEXTURE(x), (GAME_WINDOW_W/GetBackgroundGraphicsScale#())*2, (GAME_WINDOW_H/GetBackgroundGraphicsScale#())*2, FI_FILTERED)
				End Select
		End Select
		MidHandleImage(INTERFACE(x))
		INTERFACE_EXISTS(x)=true
	End Function

	Function GetBackgroundGraphicsScale#()
		graphicsscale# = ((Float(GraphicsWidth())/Float(GraphicsHeight()))/(1366.0/768.0))
		If Menu\Settings\ScreenMode#=1 Then
			If Float(GraphicsWidth())/1366.0 <= 1 Or abs(graphicsscale#-1)<0.25 Then
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
		Case Interface_Numbers:		LoadSmartFastImage("Interface/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
		Case Interface_Text_1:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
		Case Interface_Text_2:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
		Case Interface_Text_3:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
		Case Interface_Text2_1:		LoadSmartFastImage("Interface/Text2.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
		Case Interface_Text2_2:		LoadSmartFastImage("Interface/Text2.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
		Case Interface_Text2_3:		LoadSmartFastImage("Interface/Text2.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
		Case Interface_Card1:
						Select Menu\Settings\Theme#
						Case 6,15,17: LoadSmartFastImage("Interface/Card1e.png", x, 50, 417, 0, 2, 2, 1.55)
						Case 8,12: LoadSmartFastImage("Interface/Card1h.png", x, 50, 417, 0, 2, 2, 1.55)
						Case 11,13,14,18,21,28,29,30: LoadSmartFastImage("Interface/Card1b.png", x, 50, 417, 0, 2, 2, 1.55)
						Case 16,20,31: LoadSmartFastImage("Interface/Card1r.png", x, 50, 417, 0, 2, 2, 1.55)
						Case 19: LoadSmartFastImage("Interface/Card1ru.png", x, 50, 417, 0, 2, 2, 1.55)
						Case 32: LoadSmartFastImage("Interface/Card1ri.png", x, 50, 417, 0, 2, 2, 1.55)
						Default: LoadSmartFastImage("Interface/Card1.png", x, 50, 417, 0, 2, 2, 1.55)
						End Select
		Case Interface_Card2:		LoadSmartFastImage("Interface/Card2.png", x, 417, 50, 0, 1, 1.55, 2)
		Case Interface_Card3:		LoadSmartFastImage("Interface/Card3.png", x, 417, 50, 0, 1, 1.55, 2)
		Case Interface_Spinner:		LoadSmartFastImage("Interface/Spinner.png", x, 400, 386, 0, 1, 3.785, 3.785)
		Case Interface_Icons:		LoadSmartFastImage("Interface/Icons.png", x, 64, 70, 0, 36, 2, 2)
		Case Interface_Heads:		LoadSmartFastImage("Interface/Heads.png", x, 64, 70, 0, CHAR_NONMODPLAYABLECOUNT+1, 2, 2)
		Case Interface_Black:		LoadSmartFastImage("Interface/Black.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
		Case Interface_BlackBig:	LoadSmartFastImage("Interface/Black.png", x, 1440, 900, 0, 1, 0, 0, true, true, true)
		Case Interface_Results:		LoadSmartFastImage("Interface/Results.png", x, (770), (600/6.0), 0, 7, 2.2, 2.2)
		Case Interface_Buttons_1:
						Select Menu\Settings\Theme#
						Case 8: LoadSmartFastImage("Interface/Buttonsh.png", x, 400, 100, 0, 3, 2, 2)
						Case 9,10: LoadSmartFastImage("Interface/Buttonsf.png", x, 400, 100, 0, 3, 2, 2)
						Case 11: LoadSmartFastImage("Interface/Buttonsb.png", x, 400, 100, 0, 3, 2, 2)
						Case 13,30: LoadSmartFastImage("Interface/Buttonsv.png", x, 400, 100, 0, 3, 2, 2)
						Case 14: LoadSmartFastImage("Interface/Buttonsa.png", x, 400, 100, 0, 3, 2, 2)
						Case 15: LoadSmartFastImage("Interface/Buttonsw.png", x, 400, 100, 0, 3, 2, 2)
						Case 16: LoadSmartFastImage("Interface/Buttonsr.png", x, 400, 100, 0, 3, 2, 2)
						Case 18: LoadSmartFastImage("Interface/Buttonsk.png", x, 400, 100, 0, 3, 2, 2)
						Case 19: LoadSmartFastImage("Interface/Buttonsru.png", x, 400, 100, 0, 3, 2, 2)
						Case 21: LoadSmartFastImage("Interface/Buttonsn.png", x, 400, 100, 0, 3, 2, 2)
						Case 22: LoadSmartFastImage("Interface/Buttonss.png", x, 400, 100, 0, 3, 2, 2)
						Case 23: LoadSmartFastImage("Interface/Buttonsbu.png", x, 400, 100, 0, 3, 2, 2)
						Case 24: LoadSmartFastImage("Interface/Buttonsc.png", x, 400, 100, 0, 3, 2, 2)
						Case 28: LoadSmartFastImage("Interface/Buttonsre.png", x, 400, 100, 0, 3, 2, 2)
						Case 29: LoadSmartFastImage("Interface/Buttonsd.png", x, 400, 100, 0, 3, 2, 2)
						Case 31: LoadSmartFastImage("Interface/Buttonslw.png", x, 400, 100, 0, 3, 2, 2)
						Case 32: LoadSmartFastImage("Interface/Buttonsri.png", x, 400, 100, 0, 3, 2, 2)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Buttons.png", x, 400, 100, 0, 3, 2, 2)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Buttons.png")=1) Then
									LoadSmartFastImage("Interface/Buttons.png", x, 400, 100, 0, 3, 2, 2)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Buttons.png", x, 400, 100, 0, 3, 2, 2)
								EndIf
							EndIf
						End Select
		Case Interface_Buttons_2:
						Select Menu\Settings\Theme#
						Case 8: LoadSmartFastImage("Interface/Buttonsh.png", x, 400, 100, 0, 3, 3, 3)
						Case 9,10: LoadSmartFastImage("Interface/Buttonsf.png", x, 400, 100, 0, 3, 3, 3)
						Case 11: LoadSmartFastImage("Interface/Buttonsb.png", x, 400, 100, 0, 3, 3, 3)
						Case 13,30: LoadSmartFastImage("Interface/Buttonsv.png", x, 400, 100, 0, 3, 3, 3)
						Case 14: LoadSmartFastImage("Interface/Buttonsa.png", x, 400, 100, 0, 3, 3, 3)
						Case 15: LoadSmartFastImage("Interface/Buttonsw.png", x, 400, 100, 0, 3, 3, 3)
						Case 16: LoadSmartFastImage("Interface/Buttonsr.png", x, 400, 100, 0, 3, 3, 3)
						Case 18: LoadSmartFastImage("Interface/Buttonsk.png", x, 400, 100, 0, 3, 3, 3)
						Case 19: LoadSmartFastImage("Interface/Buttonsru.png", x, 400, 100, 0, 3, 3, 3)
						Case 21: LoadSmartFastImage("Interface/Buttonsn.png", x, 400, 100, 0, 3, 3, 3)
						Case 22: LoadSmartFastImage("Interface/Buttonss.png", x, 400, 100, 0, 3, 3, 3)
						Case 23: LoadSmartFastImage("Interface/Buttonsbu.png", x, 400, 100, 0, 3, 3, 3)
						Case 24: LoadSmartFastImage("Interface/Buttonsc.png", x, 400, 100, 0, 3, 3, 3)
						Case 28: LoadSmartFastImage("Interface/Buttonsre.png", x, 400, 100, 0, 3, 3, 3)
						Case 29: LoadSmartFastImage("Interface/Buttonsd.png", x, 400, 100, 0, 3, 3, 3)
						Case 31: LoadSmartFastImage("Interface/Buttonslw.png", x, 400, 100, 0, 3, 3, 3)
						Case 32: LoadSmartFastImage("Interface/Buttonsri.png", x, 400, 100, 0, 3, 3, 3)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Buttons.png", x, 400, 100, 0, 3, 3, 3)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Buttons.png")=1) Then
									LoadSmartFastImage("Interface/Buttons.png", x, 400, 100, 0, 3, 3, 3)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Buttons.png", x, 400, 100, 0, 3, 3, 3)
								EndIf
							EndIf
						End Select
		Case Interface_TextButtons_1:	LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
		Case Interface_TextButtons_2:	LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
		Case Interface_TextButtons2_1:	LoadSmartFastImage("Interface/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
		Case Interface_TextButtons2_2:	LoadSmartFastImage("Interface/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
		Case Interface_TextTitle_1:	LoadSmartFastImage("Interface/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
		Case Interface_TextTitle2_1:	LoadSmartFastImage("Interface/TextTitle2.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
		Case Interface_TextTitleChao_1:	LoadSmartFastImage("Interface/TextTitleChao.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
		Case Interface_Keys:		LoadSmartFastImage("Interface/Keys.png", x, 80, 80, 0, 169, 3, 3)
		Case Interface_Keys_Small:	LoadSmartFastImage("Interface/Keys.png", x, 80, 80, 0, 169, 4.5, 4.5)
		Case Interface_Ranks:		LoadSmartFastImage("Interface/Ranks.png", x, 256, 256, 0, 7, 3.82, 3.82)
		Case Interface_TextControls_1:	LoadSmartFastImage("Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
		Case Interface_TextControls_2:	LoadSmartFastImage("Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
		Case Interface_Saving:		LoadSmartFastImage("Interface/Saving.png", x, 55, 70, 0, 1, 2.5, 2.5)
		Case Interface_Indicator:	LoadSmartFastImage("Interface/Indicator.png", x, 256, 256, 0, 1, 4.95, 4.95)
		Case Interface_Sky:
						Select Menu\Settings\Theme#
						Case 14,26: LoadSmartFastImage("Interface/Sky"+Int(Menu\Settings\Theme#)+"a.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Sky"+Int(Menu\Settings\Theme#)+".png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Sky.png")=1) Then
									LoadSmartFastImage("Interface/Sky"+Int(1)+".png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Sky.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
								EndIf
							EndIf
						End Select
		Case Interface_Sky2:
						Select Menu\Settings\Theme#
						Case 14,26: LoadSmartFastImage("Interface/Sky"+Int(Menu\Settings\Theme#)+"b.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						End Select
		Case Interface_Background1:
						Select Menu\Settings\Theme#
						Case 8,19,20,22,23,30: LoadSmartFastImage("Interface/Background"+Int(Menu\Settings\Theme#)+"a.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						Case 26: LoadSmartFastImage("Interface/Background"+Int(Menu\Settings\Theme#)+"1.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Background"+Int(Menu\Settings\Theme#)+".png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Background.png")=1) Then
									LoadSmartFastImage("Interface/Background"+Int(1)+".png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Background.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
								EndIf
							EndIf
						End Select
		Case Interface_Background2:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Background11b.png", x, 586, 1750, 0, 1, 0, 0, false, true)
						Case 8,19,20,22,23,30: LoadSmartFastImage("Interface/Background"+Int(Menu\Settings\Theme#)+"b.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						End Select
		Case Interface_Background3:
						Select Menu\Settings\Theme#
						Case 8,30: LoadSmartFastImage("Interface/Background"+Int(Menu\Settings\Theme#)+"c.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						End Select
		Case Interface_Background4:
						Select Menu\Settings\Theme#
						Case 8,30: LoadSmartFastImage("Interface/Background"+Int(Menu\Settings\Theme#)+"d.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
						End Select
		Case Interface_TextRecords_2:	LoadSmartFastImage("Interface/TextRecords2.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
		Case Interface_TextNames_1:	LoadSmartFastImage("Interface/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
		Case Interface_TextNames2_1:	LoadSmartFastImage("Interface/TextNames2.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
		Case Interface_TextOvers_1:	LoadSmartFastImage("Interface/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
		Case Interface_TextOvers_2:	LoadSmartFastImage("Interface/TextOvers2.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
		Case Interface_TextCredits_1:	LoadSmartFastImage("Interface/TextCredits.png", x, (640/16.0), (270/6.0), 0, 96, 3, 3)
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
		Case Interface_Logo:
						Select Menu\Settings\Theme#
						Case 8: LoadSmartFastImage("Interface/Logohh.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 20: LoadSmartFastImage("Interface/Logoh.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 11: LoadSmartFastImage("Interface/Logob.png", x, 950, 540, 0, 1, 2.5, 2.5)
						Case 14: LoadSmartFastImage("Interface/Logoc.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 15: LoadSmartFastImage("Interface/Logow.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 16: LoadSmartFastImage("Interface/Logor.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 18: LoadSmartFastImage("Interface/Logok.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 19: LoadSmartFastImage("Interface/Logoru.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 21: LoadSmartFastImage("Interface/Logon.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 26: LoadSmartFastImage("Interface/Logog.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 28: LoadSmartFastImage("Interface/Logore.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 29: LoadSmartFastImage("Interface/Logod.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 30: LoadSmartFastImage("Interface/Logou.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 31: LoadSmartFastImage("Interface/Logolw.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 32: LoadSmartFastImage("Interface/Logori.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Logo.png")=1) Then
									LoadSmartFastImage("Interface/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
								EndIf
							EndIf
						End Select
		Case Interface_Logo_ring:
						Select Menu\Settings\Theme#
						Case 8: LoadSmartFastImage("Interface/Logohh_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 20: LoadSmartFastImage("Interface/Logoh_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 11: LoadSmartFastImage("Interface/Logob_ring.png", x, 950, 540, 0, 1, 2.5, 2.5)
						Case 14: LoadSmartFastImage("Interface/Logoc_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 15: LoadSmartFastImage("Interface/Logow_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 16: LoadSmartFastImage("Interface/Logor_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 18: LoadSmartFastImage("Interface/Logok_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 19: LoadSmartFastImage("Interface/Logoru_ring.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 21: LoadSmartFastImage("Interface/Logon_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 26: LoadSmartFastImage("Interface/Logog_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 28: LoadSmartFastImage("Interface/Logore_ring.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 29: LoadSmartFastImage("Interface/Logod_ring.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 30: LoadSmartFastImage("Interface/Logou_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 31: LoadSmartFastImage("Interface/Logolw_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 32: LoadSmartFastImage("Interface/Logori_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Logo_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Logo_ring.png")=1) Then
									LoadSmartFastImage("Interface/Logo_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Logo_ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
								EndIf
							EndIf
						End Select
		Case Interface_Logo_flash:
						Select Menu\Settings\Theme#
						Case 8: LoadSmartFastImage("Interface/Logohh_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 20: LoadSmartFastImage("Interface/Logoh_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 11: LoadSmartFastImage("Interface/Logob_flash.png", x, 950, 540, 0, 1, 2.5, 2.5)
						Case 14: LoadSmartFastImage("Interface/Logoc_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 15: LoadSmartFastImage("Interface/Logow_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 16: LoadSmartFastImage("Interface/Logor_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 18: LoadSmartFastImage("Interface/Logok_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 19: LoadSmartFastImage("Interface/Logoru_flash.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 21: LoadSmartFastImage("Interface/Logon_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 26: LoadSmartFastImage("Interface/Logog_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 28: LoadSmartFastImage("Interface/Logore_flash.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 29: LoadSmartFastImage("Interface/Logod_flash.png", x, 1200, 680, 0, 1, 2.5, 2.5)
						Case 30: LoadSmartFastImage("Interface/Logou_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 31: LoadSmartFastImage("Interface/Logolw_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Case 32: LoadSmartFastImage("Interface/Logori_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
						Default:
							If Menu\Settings\Theme#<=MENU_THEME_NONMODAMOUNT# Then
								LoadSmartFastImage("Interface/Logo_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
							Else
								If Not(FileType("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Logo_flash.png")=1) Then
									LoadSmartFastImage("Interface/Logo_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
								Else
									LoadSmartFastImage("Mods/MenuThemes/CustomTheme"+Int(Menu\Settings\Theme#-MENU_THEME_NONMODAMOUNT#)+"/Logo_flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
								EndIf
							EndIf
						End Select
		Case Interface_LogoRipple:
						LoadSmartFastImage("Interface/LogoRipple.png", x, 800, 500, 0, 1, 2.5, 2.5)
		Case Interface_Logoxmas:
						Select Menu\Settings\Theme#
						Case 7: LoadSmartFastImage("Interface/Logoxmas.png", x, 800, 500, 0, 1, 2.5, 2.5)
						End Select
		Case Interface_Square:		LoadSmartFastImage("Interface/Square.png", x, 496, 400, 0, 1, 2, 2)
		Case Interface_Round:
						Select Menu\Settings\Theme#
						Case 8: LoadSmartFastImage("Interface/Round.png", x, 700, 700, 0, 1, 2.05, 2.05) : Menu\RoundSize#=1
						Case 16: LoadSmartFastImage("Interface/Roundr.png", x, 700, 700, 0, 1, 2.65, 2.65)
						Case 30: LoadSmartFastImage("Interface/Roundu.png", x, 700, 700, 0, 1, 2.65, 2.65)
						End Select
		Case Interface_Bubble_1:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Bubble1.png", x, 200, 200, 0, 1, 3.5, 3.5)
						Case 27: LoadSmartFastImage("Interface/Bubble1sc.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						End Select
		Case Interface_Bubble_2:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Bubble2.png", x, 200, 200, 0, 1, 3.5, 3.5)
						Case 27: LoadSmartFastImage("Interface/Bubble2sc.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						End Select
		Case Interface_Bubble_3:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Bubble3.png", x, 200, 200, 0, 1, 3.5, 3.5)
						Case 27: LoadSmartFastImage("Interface/Bubble3sc.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 3.5*2, 3.5*2)
						End Select
		Case Interface_Bubble_1_2:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Bubble1.png", x, 200, 200, 0, 1, 5.5, 5.5)
						Case 27: LoadSmartFastImage("Interface/Bubble1sc.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						End Select
		Case Interface_Bubble_2_2:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Bubble2.png", x, 200, 200, 0, 1, 5.5, 5.5)
						Case 27: LoadSmartFastImage("Interface/Bubble2sc.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						End Select
		Case Interface_Bubble_3_2:
						Select Menu\Settings\Theme#
						Case 11: LoadSmartFastImage("Interface/Bubble3.png", x, 200, 200, 0, 1, 5.5, 5.5)
						Case 27: LoadSmartFastImage("Interface/Bubble3sc.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						Case 30: LoadSmartFastImage("Interface/Bubble1u.png", x, 200, 200, 0, 1, 5.5*2, 5.5*2)
						End Select
		Case Interface_Characters:	LoadSmartFastImage("Interface/Characters.png", x, 2560/10.0, 2500/9.0, 0, CHAR_NONMODPLAYABLECOUNT+5, 6, 6)
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
		Case Interface_Transporter1:	LoadSmartFastImage("Interface/Transporter1.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
		Case Interface_Transporter2:	LoadSmartFastImage("Interface/Transporter2.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
		Case Interface_Transporter3:	LoadSmartFastImage("Interface/Transporter3.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
		Case Interface_Transporter4:	LoadSmartFastImage("Interface/Transporter4.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
		Case Interface_Transporter5:	LoadSmartFastImage("Interface/Transporter5.png", x, 1440, 900, 0, 1, 0, 0, true, false, true)
		Case Interface_Goodbye:		LoadSmartFastImage("Interface/Goodbye.png", x, 625, 425, 0, 1, 2.85, 2.85)
		Case Interface_Buddy:		LoadSmartFastImage("Interface/Buddy.png", x, 625, 425, 0, 1, 2.85, 2.85)
		Case Interface_Naming:		LoadSmartFastImage("Interface/Naming.png", x, 70, 70, 0, 81, 2.2, 2.2)
		Case Interface_Debug:		LoadSmartFastImage("Interface/Debug.png", x, 50, 50, 0, 36, 2.75, 2.75)
		Case Interface_Race:		LoadSmartFastImage("Interface/Race.png", x, 98, 100, 0, 9, 3.2, 3.2)
		Case Interface_Karate:		LoadSmartFastImage("Interface/Karate.png", x, (1320), (280)/2.0, 0, 2, 2.2, 2.2)
		Case Interface_KarateBars:	LoadSmartFastImage("Interface/KarateBars.png", x, (172)/(172/2.0), (42), 0, (172/2.0), 2.2, 2.2)
		Default:
			For i = Interface_HeadsMod[1] to Interface_HeadsMod[MODCHAR_AMOUNT]
				If x=i Then LoadMods_Character_InterfaceHead(i-Interface_HeadsMod[1]+1,x) : Return
			Next
			For i = Interface_CharactersMod[1] to Interface_CharactersMod[MODCHAR_AMOUNT]
				If x=i Then LoadMods_Character_InterfaceCharacter(i-Interface_CharactersMod[1]+1,x) : Return
			Next
	End Select

End Function