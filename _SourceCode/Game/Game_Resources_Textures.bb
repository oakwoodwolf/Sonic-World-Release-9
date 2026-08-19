
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
	
	If FileType(Game\Stage\Properties\Path$+file$)=1 And Menu\Stage<>0 Then
		pathtoimage$=Game\Stage\Properties\Path$+file$
	Else
		If Not(FileType(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+file$)) Then
			pathtoimage$=file$
		Else	
			pathtoimage$=(THEMEDIR$+Menu\ThemeFolder$[Menu\Settings\Theme#]+"/"+file$)
		EndIf
	EndIf
	
	
	Select noalpha
		Case False: INTERFACE_TEXTURE(x) = LoadAnimTexture(pathtoimage$, 1+2+256, w#, h#, f1, fa)
		Case True: INTERFACE_TEXTURE(x) = LoadAnimTexture(pathtoimage$ ,1+256, w#, h#, f1, fa)
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
		Case Interface_Pause
			LoadSmartFastImage("Interface/Pause.png", x,  500, 500, 0, 1, 2, 2)
			
;==================================================
;==================================================
		Case Interface_Numbers:		
			LoadSmartFastImage("Interface/Numbers.png", x, (201/3.0), (268/4.0), 0, 12, 3, 3)
			
;========================================================================
;========================================================================
		;Hud left
		Case Interface_HudLeft : LoadSmartFastImage("Interface/HudLeft.png", x,  500, 500, 0, 1, 2, 2)
			
			
			
		Case Interface_HudRight
			
			LoadSmartFastImage("Interface/HudRight.png",x,  500, 500, 0, 1, 2, 2)
			
			
;==================================================
;==================================================
		Case Interface_Text_1:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
		Case Interface_Text_2:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
		Case Interface_Text_3:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
		Case Interface_Text2_1:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 2.35, 2.35)
		Case Interface_Text2_2:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.85, 1.85)
		Case Interface_Text2_3:		LoadSmartFastImage("Interface/Text.png", x, (480/16.0), (210/6.0), 0, 96, 1.50, 1.50)
			
;==================================================
;==================================================
		Case Interface_Card1:		
			
			LoadSmartFastImage("Interface/Card1.png", x, 50, 417, 0, 2, 2, 1.55)
			
		Case Interface_Card2:		
			
			LoadSmartFastImage("Interface/Card2.png", x, 417, 50, 0, 1, 1.55, 2)
			
		Case Interface_Card3:		LoadSmartFastImage("Interface/Card3.png", x, 417, 50, 0, 1, 1.55, 2)
;========================================================================
;========================================================================
		Case Interface_Spinner
			LoadSmartFastImage("Interface/Spinner.png", x, 400, 386, 0, 1, 3.785, 3.785)
			
		Case Interface_Spinner1
			LoadSmartFastImage("Interface/Spinner1.png", x, 400, 386, 0, 1, 3.785, 3.785)
		Case Interface_Spinner2
			LoadSmartFastImage("Interface/Spinner2.png", x, 400, 386, 0, 1, 3.785, 3.785)
		Case Interface_Spinner3
			LoadSmartFastImage("Interface/Spinner3.png", x, 400, 386, 0, 1, 3.785, 3.785)
			
		Case Interface_Icons
			LoadSmartFastImage("Interface/Icons.png", x, 64, 70, 0, 36, 2, 2)
			
		Case Interface_Origins
			LoadSmartFastImage("Interface/Origins.png", x, 128, 128, 0, 25, 2, 2)
			
		Case Interface_Icons2
			LoadSmartFastImage("Interface/Icons2.png", x, 64, 70, 0, 36, 2, 2)	
			
		Case Interface_Icons_Cosmetic
			LoadSmartFastImage("Interface/Icons_Cosmetic.png", x, 64, 70, 0, 36, 2, 2)
			
		Case Interface_Icons_Consume
			LoadSmartFastImage("Interface/Icons_Consume.png", x, 64, 70, 0, 36, 2, 2)
			
		Case Interface_Icons_Ability
			LoadSmartFastImage("Interface/Icons_Ability.png", x, 64, 70, 0, 36, 2, 2)
			
		Case Interface_Icons_Handicap
			LoadSmartFastImage("Interface/Icons_Handicap.png", x, 64, 70, 0, 36, 2, 2)
			
		Case Interface_Icons_Skill
			LoadSmartFastImage("Interface/Icons_Skill.png", x, 64, 70, 0, 36, 2, 2)
			
		Case Interface_Heads:		LoadSmartFastImage("Interface/Heads.png", x, 64, 70, 0, CHAR_NONMODPLAYABLECOUNT+1, 2, 2)
		Case Interface_Black:		LoadSmartFastImage("Interface/Black.png", x, 1440, 900, 0, 1, 0, 0, True, False, True)
		Case Interface_BlackBig:	LoadSmartFastImage("Interface/Black.png", x, 1440, 900, 0, 1, 0, 0, True, True, True)
		Case Interface_Results:		LoadSmartFastImage("Interface/Results.png", x, (770), (600/6.0), 0, 7, 2.2, 2.2)
;========================================================================
;========================================================================
		Case Interface_Buttons_1: LoadSmartFastImage("Interface/Buttons.png", x, 400, 100, 0, 3, 2, 2)
		Case Interface_Buttons_2: LoadSmartFastImage("Interface/Buttons.png", x, 400, 100, 0, 3, 3, 3)
;========================================================================
;========================================================================
		Case Interface_TextButtons_1:	LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
		Case Interface_TextButtons_2:	LoadSmartFastImage("Interface/TextButtons.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
		Case Interface_TextButtons2_1:	LoadSmartFastImage("Interface/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 2.35, 2.35)
		Case Interface_TextButtons2_2:	LoadSmartFastImage("Interface/TextButtons2.png", x, (960/16.0), (420/6.0), 0, 96, 3.35, 3.35)
			
			
;========================================================================
;========================================================================
		Case Interface_TextTitle_1:	LoadSmartFastImage("Interface/TextTitle.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
			
			
;========================================================================
;========================================================================
		Case Interface_TextTitle2_1:	LoadSmartFastImage("Interface/TextTitle2.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
			
;========================================================================
;========================================================================
			
		Case Interface_TextTitleChao_1:	LoadSmartFastImage("Interface/TextTitleChao.png", x, (1440/16.0), (630/6.0), 0, 96, 2.05, 2.05)
			
			
		Case Interface_Keys:		LoadSmartFastImage("Interface/Keys.png", x, 80, 80, 0, 169, 3, 3)
		Case Interface_Keys_Small:	LoadSmartFastImage("Interface/Keys.png", x, 80, 80, 0, 169, 4.5, 4.5)
		Case Interface_Ranks:		LoadSmartFastImage("Interface/Ranks.png", x, 256, 256, 0, 7, 3.82, 3.82)
			
		Case Interface_TextControls_1:	LoadSmartFastImage("Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 3, 3)
			
		Case Interface_TextControls_2:	LoadSmartFastImage("Interface/TextControls.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
			
			
		Case Interface_Saving:		LoadSmartFastImage("Interface/Saving.png", x, 55, 70, 0, 1, 2.5, 2.5)
		Case Interface_Indicator:	LoadSmartFastImage("Interface/Indicator.png", x, 256, 256, 0, 1, 4.95, 4.95)
		Case Interface_Sky:LoadSmartFastImage("Interface/Sky.png", x, Menu\ThemeResX, Menu\ThemeResY, 0, 1, 0, 0, True, False, True)
		Case Interface_Sky2:LoadSmartFastImage("Interface/Sky2.png", x, Menu\ThemeResX, Menu\ThemeResY, 0, 1, 0, 0, True, False, True)
		Case Interface_Background1: LoadSmartFastImage("Interface/Background.png", x, Menu\ThemeResX, Menu\ThemeResY, 0, 1, 0, 0, True, False, True)
			
			
		Case Interface_BackgroundScroll: 
			
			If Menu\ThemeScrolls=1 Then LoadSmartFastImage("Interface/Scroll.png", x, 586, 1750, 0, 1, 0, 0, False, True)
		Case Interface_Background2:
			If Menu\ThemeBackgroundPlay=1 Then LoadSmartFastImage("Interface/BackgroundPlay.png", x, Menu\ThemeResX, Menu\ThemeResY, 0, 1, 0, 0, True, False, True)
			
		Case Interface_Background3: 
			If Menu\ThemeBackgroundOptions=1 LoadSmartFastImage("Interface/BackgroundOptions.png", x, Menu\ThemeResX, Menu\ThemeResY, 0, 1, 0, 0, True, False, True)
			
			Case Interface_Background4: LoadSmartFastImage("Interface/Background4.png", x, Menu\ThemeResX, Menu\ThemeResY, 0, 1, 0, 0, True, False, True)
			
;========================================================================
;========================================================================
		Case Interface_TextRecords_1:	LoadSmartFastImage("Interface/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
			
		Case Interface_TextRecords_2:	LoadSmartFastImage("Interface/TextRecords2.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
			
;========================================================================
;========================================================================
		Case Interface_TextNames_1:	LoadSmartFastImage("Interface/TextNames.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
			
			
		Case Interface_TextNames2_1:	LoadSmartFastImage("Interface/TextNames2.png", x, (1120/16.0), (280/4.0), 0, 64, 2, 2)
			
;========================================================================
;========================================================================
		Case Interface_TextOvers_1:	LoadSmartFastImage("Interface/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
			
		Case Interface_TextOvers_2:	LoadSmartFastImage("Interface/TextOvers1.png", x, (1440/16.0), (630/6.0), 0, 96, 1.65, 1.65)
			
			
;========================================================================
;========================================================================
		Case Interface_TextCredits_1:	LoadSmartFastImage("Interface/TextCredits.png", x, (640/16.0), (270/6.0), 0, 96, 3, 3)
			
			
		Case Interface_Circle:
			LoadSmartFastImage("Interface/Circle.png", x, 496, 500, 0, 1, 1.6, 1.6)
			
		Case Interface_Circle2:
			
			LoadSmartFastImage("Interface/Circle2.png", x, 496, 500, 0, 1, 1.6, 1.6)
			
		Case Interface_RedRingBox
			
			LoadSmartFastImage("Interface/RedRingBox.png",x,  500, 500, 0, 1, 2, 2)
			
			
			
		Case Interface_Logo:
			LoadSmartFastImage("Interface/Logo.png", x, 800, 500, 0, 1, 2.5, 2.5)
		Case Interface_Logo_Ring:
			LoadSmartFastImage("Interface/Logo_Ring.png", x, 800, 500, 0, 1, 2.5, 2.5)
		Case Interface_Logo_Sonic:
			LoadSmartFastImage("Interface/Logo_Sonic.png", x, 720, 196, 0, 1, 2.5, 2.5)
		Case Interface_Logo_World:
			LoadSmartFastImage("Interface/Logo_World.png", x, 634, 135, 0, 1, 2.5, 2.5)
		Case Interface_Logo_SpeedRing:
			LoadSmartFastImage("Interface/Logo_SpeedRing.png", x, 800, 500, 0, 1, 2.5, 2.5)
		Case Interface_Logo_SpeedSonic:
			LoadSmartFastImage("Interface/Logo_SpeedSonic.png", x, 720, 196, 0, 1, 2.5, 2.5)
		Case Interface_Logo_SpeedWorld:
			LoadSmartFastImage("Interface/Logo_SpeedWorld.png", x, 634, 135, 0, 1, 2.5, 2.5)
		Case Interface_Logo_Ripple:
			LoadSmartFastImage("Interface/Logo_Ripple.png", x, 800, 500, 0, 1, 2.5, 2.5)
		Case Interface_Logo_Flash:
			LoadSmartFastImage("Interface/Logo_Flash.png", x, 800, 500, 0, 1, 2.5, 2.5)
		Case Interface_Square:		LoadSmartFastImage("Interface/Square.png", x, 496, 400, 0, 1, 2, 2)
		Case Interface_Square2:		LoadSmartFastImage("Interface/Square2.png", x, 496, 400, 0, 1, 2, 2)
			
		Case Interface_Round:
			LoadSmartFastImage("Interface/Round.png", x, 700, 700, 0, 1, 2.05, 2.05) : Menu\RoundSize#=1
			
		Case Interface_Bubble_1:
			
			If Menu\ThemeBubbles=1 Then LoadSmartFastImage("Interface/Bubble1.png", x, 200, 200, 0, 1, 3.5, 3.5)
			
			
		Case Interface_Bubble_2:
			
			If Menu\ThemeBubbles=1 Then  LoadSmartFastImage("Interface/Bubble2.png", x, 200, 200, 0, 1, 3.5, 3.5)
			
			
		Case Interface_Bubble_3:
			
			If Menu\ThemeBubbles=1 Then  LoadSmartFastImage("Interface/Bubble3.png", x, 200, 200, 0, 1, 3.5, 3.5)
			
			
		Case Interface_Bubble_1_2:
			
			If Menu\ThemeBubbles=1 Then  LoadSmartFastImage("Interface/Bubble1.png", x, 200, 200, 0, 1, 5.5, 5.5)
			
		Case Interface_Bubble_2_2:
			
			If Menu\ThemeBubbles=1 Then  LoadSmartFastImage("Interface/Bubble2.png", x, 200, 200, 0, 1, 5.5, 5.5)
			
			
		Case Interface_Bubble_3_2:
			
			If Menu\ThemeBubbles=1 Then  LoadSmartFastImage("Interface/Bubble3.png", x, 200, 200, 0, 1, 5.5, 5.5)
			
			
			
		Case Interface_CharacterSelector:	
			LoadSmartFastImage("Interface/CharacterSelector.png", x, 256, 280, 0, 1, 6, 6)
			
			
		Case Interface_TextRecords_1:	LoadSmartFastImage("Interface/TextRecords1.png", x, (1440/16.0), (540/6.0), 0, 96, 2.75, 2.75)
		Case Interface_Treasure:	LoadSmartFastImage("Interface/Treasure.png", x, 51, 51, 0, 4, 2, 2)
		Case Interface_Treasure_Big:	LoadSmartFastImage("Interface/Treasure.png", x, 51, 51, 0, 4, 1.7, 1.7)
		Case Interface_Flickies:	LoadSmartFastImage("Interface/Flickies.png", x, 157/2.0, 79, 0, 2, 2.64, 2.64)
		Case Interface_Caution:		LoadSmartFastImage("Interface/Caution.png", x, 126, 126, 0, 1, 3, 3)
		Case Interface_Inventory:	LoadSmartFastImage("Interface/Inventory.png", x, 48, 48, 0, 7, 2.75, 2.75)
		Case Interface_Monitors:	LoadSmartFastImage("Interface/Monitors.png", x, 128, 128, 0, 24, 4.25, 4.25)
		Case Interface_Prompts:	LoadSmartFastImage("Interface/Prompts.png", x, 128, 128, 0, 24, 4.25, 4.25)
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
		Case Interface_Shop:		LoadSmartFastImage("Interface/Shop.png", x, 50, 50, 0, 36, 2.75, 2.75)
		Case Interface_Race:		LoadSmartFastImage("Interface/Race.png", x, 98, 100, 0, 9, 3.2, 3.2)
		Case Interface_Karate:		LoadSmartFastImage("Interface/Karate.png", x, (1320), (280)/2.0, 0, 2, 2.2, 2.2)
		Case Interface_KarateBars:	LoadSmartFastImage("Interface/KarateBars.png", x, (172)/(172/2.0), (42), 0, (172/2.0), 2.2, 2.2)
		Case Interface_Gauge
			LoadSmartFastImage("Interface/Gauge.png"    , x,  500, 500, 0, 1, 2, 2)
			
			
			
			



		Default:
			For i = Interface_HeadsMod[1] To Interface_HeadsMod[MODCHAR_AMOUNT]
				If x=i Then LoadMods_Character_InterfaceHead(i-Interface_HeadsMod[1]+1,x) :  Return
				
			Next
			For i = Interface_CharactersMod[1] To Interface_CharactersMod[MODCHAR_AMOUNT]
				If x=i Then LoadMods_Character_InterfaceCharacter(i-Interface_CharactersMod[1]+1,x) : Return
			Next
			For i = Interface_Characters[1] To Interface_Characters[CHAR_NONMODPLAYABLECOUNT]
				If x=i Then LoadSmartFastImage("Characters/"+Lower$(ShortCharNames(i-Interface_Characters[1]+1,1))+"/interface/Character.png", x, 3072/12.0, 840/3.0, 0, 1, 6, 6): Return
			Next
			For i = Interface_CharacterHeads[1] To Interface_CharacterHeads[CHAR_NONMODPLAYABLECOUNT]
				If x=i Then LoadSmartFastImage("Characters/"+Lower$(ShortCharNames(i-Interface_CharacterHeads[1]+1,1))+"/interface/Head.png", x, 64, 70, 0, 1, 2, 2): Return
			Next
	End Select
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D