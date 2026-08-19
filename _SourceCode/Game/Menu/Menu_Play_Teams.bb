
	Const CONTROLINFO_SPACE# = 18.55
	Global CONTROLINFO_START# = -3.5

Function GetMenuCharacterScale#(mode#=0)
	If Menu\Settings\ScreenMode#=0 Then
		Return 1
	Else
		Select mode#
			Case 1: Return 0.69375
			Default: Return 0.83125
		End Select
	EndIf
End Function

Function GetMenuCharacterExtraY#(mode#=0)
	Select mode#
		Case 1:
			Select Menu\Settings\Resolution#
				Case 1: Return -0.0625
				Case 2,11,12: Return 0.0625
				Case 3,4: Return 0.1875
				Case 7,8: Return 0.25
				Default: Return 0
			End Select
		Case 2:
			Select Menu\Settings\Resolution#
				Case 1: Return 0.0625
				Case 2: Return -0.025
				Case 3,4: Return -0.125
				Case 7,8: Return -0.1625
				Default: Return 0
			End Select
		Case 3:
			Select Menu\Settings\Resolution#
				Case 1: Return -0.0875
				Case 2: Return 0.05
				Case 3,4: Return 0.15
				Case 7,8: Return 0.1875
				Default: Return 0
			End Select
		Case 4:
			Select Menu\Settings\Resolution#
				Case 2: Return 0.025
				Case 3,4: Return 0.1125
				Case 7,8: Return 0.1375
				Default: Return 0
			End Select
		Default:
			Select Menu\Settings\Resolution#
				Case 1: Return -0.03125
				Case 3,4: Return 0.03125
				Case 7,8: Return 0.0375
				Default: Return 0
			End Select
	End Select
End Function


Function Menu_CharacterMeshOnScreen(d.tDeltaTime)
	If Menu\MeshMayChangeTimer>0 Then Menu\MeshMayChangeTimer=Menu\MeshMayChangeTimer-timervalue#
	
	;Establish mesh
	If Menu\MeshChange = -1 Then
		If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
		If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
		If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
		Menu\Mesh[1] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		Menu\MeshChange = 0
		Menu\HasMeshBone = 0
	EndIf
	If Menu\MeshChange = 1 Then
		If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
		If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
		If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
		Menu\Mesh[1] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		If Menu\Menu=MENU_TEAMS# Then
			Menu\MeshMayChangeTimer=0.5*secs#
		Else
			Menu\MeshMayChangeTimer=0.3*secs#
		EndIf
		Menu\MeshChange = 2
		Menu\HasMeshBone = 0
	ElseIf Menu\MeshChange = 2 And (Not(Menu\MeshMayChangeTimer>0)) And Menu\Transition=0 Then
		Select Menu\Menu
			Case MENU_BLACKMARKET#:
				LoadGoodSound(Sound_MenuChao,1,"Sounds/MenuBlackMarket.ogg",3)
			Case MENU_TRANSPORTER#:
				LoadGoodSound(Sound_MenuChao,1,"Sounds/MenuChaoMachine.ogg",3)
				If Menu\HeldChaoNumber>0 Then LoadGoodSound(Sound_MenuChao2,1,"Sounds/MenuChaoGoodbye.ogg",3)
			Case MENU_PRINCIPAL#:
				LoadGoodSound(Sound_MenuChao,1,"Sounds/MenuChaoMachine.ogg",3)
		End Select
		Menu\MeshChange = 3
	ElseIf Menu\MeshChange = 3 Then
		If Menu\Menu=MENU_CHARACTERS# Or Menu\Menu=MENU_TEAMS# Or Menu\Menu=MENU_EMBLEM# Or Menu\Menu=MENU_REDRING# Then
			If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
			If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
			If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
			Select Menu\Menu
				Case MENU_CHARACTERS#
					char = Menu_Character(Menu\Option,Menu\Option2)
					If char=CHAR_SHA And Menu\CharacterMode[Menu\MemberToSelect]=1 Then charalt=1 Else charalt=0
					
					If (Not(Menu\Option>=6*6)) Then
						If UNLOCKEDCHAR[char]=0 Then found=False Else found=True
					Else
						found=False
					EndIf
					
					If char>CHAR_NONMODPLAYABLECOUNT And Menu\Settings\Mods#=0 Then found=False
					If found=False Then
						LoadCharacterMesh(-1)
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						EntityColor(Menu\Mesh[1],0,0,0)
						scale# = 0.07*GetMenuCharacterScale#()
						ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
						Menu\CharacterMeshAnimation=0
					Else
						If Menu\Menu=MENU_CHARACTERS# And (Menu\Option>=6*6) Then
							LoadCharacterMesh(-1)
						Else
							Select char
								Case CHAR_SHA:
									If charalt=1 Then
										LoadCharacterMesh(char,1,0,2)
									Else
										LoadCharacterMesh(char,1)
									EndIf
								Case CHAR_EGG:
									LoadCharacterMesh(char,1,0,2)
								Default:
									If IsCharMod(char) Then
										LoadCharacterMesh(char,1,0,Menu\ModCharCostumes[Menu\MemberToSelect])
									Else
										LoadCharacterMesh(char,1)
									EndIf		
							End Select
						EndIf
						Menu\MeshCharacter[1]=char
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						If (Not(Menu\Option>=6*6)) Then
							RotateEntity(Menu\Mesh[1],0,180,0)
							Select char
								Case CHAR_SHA: actualchar=900*charalt+char
								Default: actualchar=char
							End Select
							Select actualchar
								Case CHAR_HBO:
									scale# = (0.07/(1.325+0.15*(0.125*GetCharScaleFactor#(actualchar))))*GetMenuCharacterScale#()
									ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
								Default:
									If GetCharScaleFactor#(actualchar)<0 Then
										scale# = (0.07/(0.90+0.15*(0.125*GetCharScaleFactor#(actualchar))))*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									ElseIf GetCharScaleFactor#(actualchar)>5 Then
										scale# = (0.07/(1.75+0.15*(0.125*GetCharScaleFactor#(actualchar))))*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									Else
										scale# = (0.07/(1.00+0.15*(0.125*GetCharScaleFactor#(actualchar))))*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									EndIf
							End Select
						Else
							scale# = (0.07/(1.00+0.15*(0.125*GetCharScaleFactor#(char))))*GetMenuCharacterScale#()
							ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
							EntityColor(Menu\Mesh[1],210,210,0)
						EndIf
						Menu\CharacterMeshAnimation=0
						If char=CHAR_SHD Then
							Menu\MeshBone=FindChild(Menu\Mesh[1], "head2")
							Menu\HasMeshBone=1
						Else
							Menu\MeshBone=0
							Menu\HasMeshBone=0
						EndIf
					EndIf
					Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					
				Case MENU_TEAMS#:
					m=Menu\Option
					If m>TEAM_TEAMCOUNT Then m=m-TEAM_TEAMCOUNT
					If UNLOCKEDTEAM[m]=0 Then
						LoadCharacterMesh(-1)
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						EntityColor(Menu\Mesh[1],0,0,0)
						scale# = 0.11*GetMenuCharacterScale#(1)
						ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
						Menu\CharacterMeshAnimation=0
						Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
						Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					ElseIf m>=TEAM_TEAMCOUNT Then
						LoadCharacterMesh(-1)
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						EntityColor(Menu\Mesh[1],210,210,0)
						scale# = 0.11*GetMenuCharacterScale#()
						ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
						Menu\CharacterMeshAnimation=0
						Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
						Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					Else
						If m>=TEAM_TEAMCOUNT Then
							Menu\Mesh[1] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
							Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
							Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
						Else
							Menu\MeshBone=0
							Menu\HasMeshBone=0
							For i=1 To 3
								Select m
									Case TEAM_SONIC:
										Select i
											Case 1: k=CHAR_SON
											Case 2: k=CHAR_TAI
											Case 3: k=CHAR_KNU
										End Select
									Case TEAM_DARK:
										Select i
											Case 1: k=CHAR_SHA
											Case 2: k=CHAR_ROU
											Case 3: k=CHAR_OME
										End Select
										
								End Select
								LoadCharacterMesh(k,1)
								Menu\MeshCharacter[i]=k
								Menu\Mesh[i]=CopyEntity(CharacterMesh, Game\Stage\Root)
								DeleteCharacterMesh()
								scale# = 0.11*GetMenuCharacterScale#(1)
								ScaleEntity(Menu\Mesh[i],scale#,scale#,scale#)
								RotateEntity(Menu\Mesh[i],0,180,0)
								Menu\CharacterMeshAnimation=1
								If k=CHAR_SHD Then
									Menu\MeshBone=FindChild(Menu\Mesh[i], "head")
									Menu\HasMeshBone=1
								EndIf
							Next
						EndIf
					EndIf
					
				Case MENU_EMBLEM#:
					If Menu\EmblemsGot>0 Then
						LoadCharacterMesh(-2)
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						ScaleEntity(Menu\Mesh[1],0.11,0.11,0.11)
					Else
						Menu\Mesh[1]=CopyEntity(MESHES(SmartEntity(Mesh_EmeraldGoal)), Game\Stage\Root)
						EntityColorEmerald(Menu\Mesh[1],Abs(Menu\EmblemsGot))
						ScaleEntity(Menu\Mesh[1],0.09,0.09,0.09)
					EndIf
					Menu\CharacterMeshAnimation=0
					Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				Case MENU_REDRING#:
					LoadCharacterMesh(-3)
					Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
					DeleteCharacterMesh()
					ScaleEntity(Menu\Mesh[1],0.045,0.045,0.045)
					Menu\CharacterMeshAnimation=0
					
					LoadCharacterMesh(-4)
					Menu\Mesh[2]=CopyEntity(CharacterMesh, Game\Stage\Root)
					DeleteCharacterMesh()
					ScaleEntity(Menu\Mesh[2],0.045,0.045,0.045)
					Menu\CharacterMeshAnimation=0
					
					LoadCharacterMesh(-5)
					Menu\Mesh[3]=CopyEntity(CharacterMesh, Game\Stage\Root)
					DeleteCharacterMesh()
					ScaleEntity(Menu\Mesh[3],0.04,0.04,0.04)
					Menu\CharacterMeshAnimation=0
					
					
			End Select
		ElseIf Menu\Menu=MENU_BLACKMARKET# Then
			If Menu\WentToChaoMenu=0 Then
				If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
				Menu\Mesh[1]=LoadMesh("ChaoWorld\BlackMarket\BlackMarket.b3d", Game\Stage\Root)
				ScaleEntity(Menu\Mesh[1],0.25,0.25,0.25)
				RotateEntity(Menu\Mesh[1],0,180,0)
				PositionEntity(Menu\Mesh[1],0,-5+0.28,30)
				
				If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
				Menu\Mesh[2]=LoadAnimMesh("ChaoWorld\Chao\Dealer.b3d", Game\Stage\Root)
				ExtractAllCharacterAnimations_DealerChao(Menu\Mesh[2])
				ScaleEntity(Menu\Mesh[2],0.35,0.35,0.35)
				RotateEntity(Menu\Mesh[2],0,180,0)
				PositionEntity(Menu\Mesh[2],0,-3.4275+0.28,31.55)
				Animate(Menu\Mesh[2], 1, 0.255, 1, 10)
				
				For x=INTERFACE_STAGETOTAL+1 To INTERFACE_BLACKMARKETTOTAL : LoadSmartImage(x) : Next
				
				Menu\EggsBought=0
				
				Menu\WentToChaoMenu=1
			EndIf
			
			If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
			Select Menu\NewMenu2
				Case Menu_BlackMarket_BuyList#,Menu_BlackMarket_BuyConfirm#:
					Select Menu\BlackMarketBuyCategory
						Case 1: Menu\Mesh[3]=LoadMesh("ChaoWorld\Fruits\"+FRUITS$(Menu\CurrentItem)+".b3d", Game\Stage\Root)
						Case 2: Menu\Mesh[3]=LoadMesh("ChaoWorld\Hats\"+HATS_FILE$(Menu\CurrentItem)+".b3d", Game\Stage\Root)
						Case 3: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\egg.b3d", Game\Stage\Root)
							eggtexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(Menu\CurrentItem)+".png",256)
							EntityTexture Menu\Mesh[3], eggtexture
							FreeTexture eggtexture
						Case 5: Menu\Mesh[3]=LoadMesh("ChaoWorld\Toys\"+TOYS_FILE$(Menu\CurrentItem)+".b3d", Game\Stage\Root)
					End Select
					ScaleEntity(Menu\Mesh[3],0.2,0.2,0.2)
					Select Menu\BlackMarketBuyCategory
						Case 2:
							Select Menu\CurrentItem
								Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3,HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3:
									PositionEntity(Menu\Mesh[3],-0.575,-3.2325+0.35,30.995)
								Default:
									PositionEntity(Menu\Mesh[3],-0.575,-3.2325+0.28,30.995)
							End Select
						Default:
							PositionEntity(Menu\Mesh[3],-0.575,-3.2325+0.28,30.995)
					End Select
				Case Menu_BlackMarket_SellList#,Menu_BlackMarket_SellConfirm#:
					Select Menu\BlackMarketBuyCategory
						Case 1: Menu\Mesh[3]=LoadMesh("ChaoWorld\Fruits\"+FRUITS$(Menu\BlackMarketSellCategory)+".b3d", Game\Stage\Root)
						Case 2: Menu\Mesh[3]=LoadMesh("ChaoWorld\Hats\"+HATS_FILE$(Menu\BlackMarketSellCategory)+".b3d", Game\Stage\Root)
						Case 3: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\egg.b3d", Game\Stage\Root)
							eggtexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(Menu\BlackMarketSellCategory)+".png",256)
							EntityTexture Menu\Mesh[3], eggtexture
							FreeTexture eggtexture
						Case 4:
							Select Menu\BlackMarketSellCategory
								Case SHELL_BOTTOM: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\eggB.b3d", Game\Stage\Root)
								Case SHELL_TOP: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\eggT.b3d", Game\Stage\Root)
							End Select
							eggtexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(Menu\BlackMarketSellCategory2)+".png",256)
							EntityTexture Menu\Mesh[3], eggtexture
							FreeTexture eggtexture
						Case 5: Menu\Mesh[3]=LoadMesh("ChaoWorld\Toys\"+TOYS_FILE$(Menu\BlackMarketSellCategory)+".b3d", Game\Stage\Root)
						Case 6: Menu\Mesh[3]=LoadMesh("ChaoWorld\Trees\Seed.b3d", Game\Stage\Root)
					End Select
					ScaleEntity(Menu\Mesh[3],0.2,0.2,0.2)
					Select Menu\BlackMarketBuyCategory
						Case 2:
							Select Menu\BlackMarketSellCategory
								Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3,HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3:
									PositionEntity(Menu\Mesh[3],-0.575,-3.2325+0.35,30.995)
								Default:
									PositionEntity(Menu\Mesh[3],-0.575,-3.2325+0.28,30.995)
							End Select
						Default:
							PositionEntity(Menu\Mesh[3],-0.575,-3.2325+0.28,30.995)
					End Select
				Default:
					Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
			End Select
		ElseIf Menu\Menu=MENU_TRANSPORTER# Then
			If Menu\WentToChaoMenu=0 Then
				If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
				Menu\Mesh[1] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				
				If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
				If Not(Menu\HeldChaoNumber>0) Then
					Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				Else
					Menu\Mesh[2] = LoadAnimMesh("ChaoWorld\Chao\"+CHAOSIDES$(Menu\HeldChaoSide)+"."+CHAOSHAPES$(Menu\HeldChaoShape)+".b3d", Game\Stage\Root)
					bodytexture=LoadTexture("ChaoWorld\Chao\"+CHAOCOLORS$(Menu\HeldChaoColor)+"\"+CHAOSIDES$(Menu\HeldChaoSide)+".body."+CHAOCOLORS$(Menu\HeldChaoColor)+"."+CHAOSHAPES$(Menu\HeldChaoShape)+".png",256)
					officetexture=LoadTexture("ChaoWorld\Chao\Office\"+CHAOSIDES$(Menu\HeldChaoSide)+".office."+CHAOSHAPES$(Menu\HeldChaoShape)+".png",256)
					bodyglaretexture=LoadTexture("ChaoWorld\Chao\0.blackglare2.png",1+64) : TextureBlend(bodyglaretexture,3)
					officeglaretexture=LoadTexture("ChaoWorld\Chao\0.chaoref.png",1+64) : TextureBlend(officeglaretexture,3)
					
					ApplyMeshTextureLayer(Menu\Mesh[2], CHAOSIDES$(Menu\HeldChaoSide)+".body.celeste.png", bodytexture)
					ApplyMeshTextureLayer(Menu\Mesh[2], CHAOSIDES$(Menu\HeldChaoSide)+".office.normal.png", officetexture)
					ApplyMeshTextureLayer(Menu\Mesh[2], CHAOSIDES$(Menu\HeldChaoSide)+".body."+CHAOCOLORS$(Menu\HeldChaoColor)+"."+CHAOSHAPES$(Menu\HeldChaoShape)+".png", bodyglaretexture, True)
					ApplyMeshTextureLayer(Menu\Mesh[2], CHAOSIDES$(Menu\HeldChaoSide)+".office."+CHAOSHAPES$(Menu\HeldChaoShape)+".png", officeglaretexture, True)
					
					FreeTexture bodytexture
					FreeTexture officetexture
					FreeTexture bodyglaretexture
					FreeTexture officeglaretexture
					
					Menu\MeshChaoEmo = Object_ChaoEmo_Create.tChaoEmo(Menu\Mesh[2],Menu\HeldChaoSide,False,Menu\HeldChaoEternal)
					Menu\MeshChaoEmoActivated=1
					Menu\MeshChaoEmo\Emotion=Menu\HeldChaoPersona
					ExtractAllCharacterAnimations_DealerChao(Menu\Mesh[2])
					Animate(Menu\Mesh[2], 1, 0.255, 1, 10)
					ScaleEntity(Menu\Mesh[2],0.11*GetMenuCharacterScale#(),0.11*GetMenuCharacterScale#(),0.11*GetMenuCharacterScale#())
					RotateEntity(Menu\Mesh[2],0,180,0)
				EndIf
				
				For x=INTERFACE_CHAOGARDENTOTAL+1 To INTERFACE_TRANSPORTERTOTAL : LoadSmartImage(x) : Next
				If Menu\HeldChaoNumber>0 Then LoadSmartImage(Interface_Boxes) : LoadSmartImage(Interface_Stats)
				
				Menu\WentToChaoMenu=1
			EndIf
			
			If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
			Select Menu\NewMenu2
				Case Menu_Transporter_Inventory#:
					If Not(TOTALITEMS>0) Then
						Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					Else
						Select Menu\BlackMarketBuyCategory
							Case 1: Menu\Mesh[3]=LoadMesh("ChaoWorld\Fruits\"+FRUITS$(Menu\BlackMarketSellCategory)+".b3d", Game\Stage\Root)
							Case 2: Menu\Mesh[3]=LoadMesh("ChaoWorld\Hats\"+HATS_FILE$(Menu\BlackMarketSellCategory)+".b3d", Game\Stage\Root)
							Case 3: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\egg.b3d", Game\Stage\Root)
								eggtexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(Menu\BlackMarketSellCategory)+".png",256)
								EntityTexture Menu\Mesh[3], eggtexture
								FreeTexture eggtexture
							Case 4:
								Select Menu\BlackMarketSellCategory
									Case SHELL_BOTTOM: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\eggB.b3d", Game\Stage\Root)
									Case SHELL_TOP: Menu\Mesh[3]=LoadMesh("ChaoWorld\Eggs\eggT.b3d", Game\Stage\Root)
								End Select
								eggtexture=LoadTexture("ChaoWorld\Eggs\"+CHAOCOLORS$(Menu\BlackMarketSellCategory2)+".png",256)
								EntityTexture Menu\Mesh[3], eggtexture
								FreeTexture eggtexture
							Case 5: Menu\Mesh[3]=LoadMesh("ChaoWorld\Toys\"+TOYS_FILE$(Menu\BlackMarketSellCategory)+".b3d", Game\Stage\Root)
							Case 6: Menu\Mesh[3]=LoadMesh("ChaoWorld\Trees\Seed.b3d", Game\Stage\Root)
						End Select
						ScaleEntity(Menu\Mesh[3],0.1*GetMenuCharacterScale#(),0.1*GetMenuCharacterScale#(),0.1*GetMenuCharacterScale#())
						Select Menu\BlackMarketBuyCategory
							Case 1,5:
								PositionEntity(Menu\Mesh[3],-0.275,-0.15+0.05,0)
							Case 2:
								Select Menu\BlackMarketSellCategory
									Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3,HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3:
										PositionEntity(Menu\Mesh[3],-0.275,+0.05-0.05+0.05,0)
									Default:
										PositionEntity(Menu\Mesh[3],-0.275,-0.05-0.05+0.05,0)
								End Select
							Default:
								PositionEntity(Menu\Mesh[3],-0.275,-0.1875+0.05,0)
						End Select
					EndIf
				Default:
					Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
			End Select
		ElseIf Menu\Menu=MENU_PRINCIPAL# Then
			If Menu\WentToChaoMenu=0 Then
				If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
				Menu\Mesh[1]=LoadMesh("ChaoWorld\BlackMarket\PrincipalRoom.b3d", Game\Stage\Root)
				ScaleEntity(Menu\Mesh[1],0.25,0.25,0.25)
				RotateEntity(Menu\Mesh[1],0,180,0)
				PositionEntity(Menu\Mesh[1],-2.275,-5+0.28,30)
				
				If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
				Menu\Mesh[2]=LoadAnimMesh("ChaoWorld\Chao\Principal.b3d", Game\Stage\Root)
				ExtractAllCharacterAnimations_DealerChao(Menu\Mesh[2])
				ScaleEntity(Menu\Mesh[2],0.35,0.35,0.35)
				RotateEntity(Menu\Mesh[2],0,180,0)
				PositionEntity(Menu\Mesh[2],-2.275,-3.4275+0.28,31.55)
				Animate(Menu\Mesh[2], 1, 0.255, 1, 10)
				
				For x=INTERFACE_CHAOGARDENTOTAL+1 To INTERFACE_TRANSPORTERTOTAL : LoadSmartImage(x) : Next
				
				Menu\WentToChaoMenu=1
			EndIf
			
			If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
			Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
		EndIf
		Delay(75)
		Menu\MeshChange = 4
	ElseIf Menu\MeshChange = 4 Then
		Select Menu\Menu
			Case MENU_BLACKMARKET#,MENU_PRINCIPAL#:
				;do nothing
			Case MENU_EMBLEM#:
				Menu\LoadedEmblemYet=1
			Case MENU_REDRING#:
				Menu\LoadedRedRingYet=1
			Default:
				Select Menu\Menu
					Case MENU_TEAMS#: k=3
					Default: k=1
				End Select
				For i=1 To k
					Animate(Menu\Mesh[i],1, 0.2,1,0)
					
					
				Next
				
		End Select
		Menu\MeshChange = 0
	EndIf
	
	;Position mesh and do other stuff to it
	Select Menu\Menu
		Case MENU_CHARACTERS#
			PositionEntity(Menu\Mesh[1],-0.275,0.025+CARD_PLACE#/100,0)
			PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#()-GetMenuCharacterExtraY#(),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#())
		Case MENU_TEAMS#:
			If UNLOCKEDTEAM[Menu\Option]=1 Then
				If Menu\Option>=TEAM_TEAMCOUNT Then
					PositionEntity(Menu\Mesh[1],-0.275,-0.04-CARD_PLACE#/100+0.025,0)
					PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#()+GetMenuCharacterExtraY#(3),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#())
				Else
					PositionEntity(Menu\Mesh[1],-0.575+0*0.4,0.165-CARD_PLACE#/100-0.4-0*0.005,35+0*1)
					PositionEntity(Menu\Mesh[2],-0.575-1*0.4,0.165-CARD_PLACE#/100-0.4-1*0.005,35+1*1)
					PositionEntity(Menu\Mesh[3],-0.575+1*0.4,0.165-CARD_PLACE#/100-0.4-2*0.005,35+2*1)
					PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(1),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#(1)+GetMenuCharacterExtraY#(1),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#(1))
					PositionEntity(Menu\Mesh[2],EntityX(Menu\Mesh[2])*GetMenuCharacterScale#(1),EntityY(Menu\Mesh[2])*GetMenuCharacterScale#(1)+GetMenuCharacterExtraY#(1),EntityZ(Menu\Mesh[2])*GetMenuCharacterScale#(1))
					PositionEntity(Menu\Mesh[3],EntityX(Menu\Mesh[3])*GetMenuCharacterScale#(1),EntityY(Menu\Mesh[3])*GetMenuCharacterScale#(1)+GetMenuCharacterExtraY#(1),EntityZ(Menu\Mesh[3])*GetMenuCharacterScale#(1))
				EndIf
			Else
				PositionEntity(Menu\Mesh[1],-0.575+0*0.4,0.165-CARD_PLACE#/100-0.4-0.0275,35+0*1)
				PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(1),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#(1)+GetMenuCharacterExtraY#(3),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#(1))
			EndIf
		Case MENU_EMBLEM#
			
			If Menu\MeshFValues[1] < 360 Then Menu\MeshFValues#[1]=Menu\MeshFValues#[1] + 3.0*Game\DeltaTime\Delta# Else Menu\MeshFValues#[1]=0.0
			If Menu\MeshFValues[2] < 360 Then Menu\MeshFValues#[2]=Menu\MeshFValues#[2] + 2.0*Game\DeltaTime\Delta# Else Menu\MeshFValues#[2]=0.0
			
			If Menu\Transition=0 Then
				PositionEntity(Menu\Mesh[1],0,(0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4))+Sin(Menu\MeshFValues#[1])*0.04,25)
				PositionEntity(Menu\Mesh[1],0,(0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4))+Sin(Menu\MeshFValues#[1])*0.04,25)
				PositionEntity(Menu\Mesh[1],0,(0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4))+Sin(Menu\MeshFValues#[1])*0.04,25)
;				TurnEntity Menu\Mesh[1], 0, 0.2*15*d\Delta, 0
;				TurnEntity Menu\Mesh[2], 0, 0.2*15*d\Delta, 0
;				TurnEntity Menu\Mesh[3], 0, -0.2*15*d\Delta, 0
				RotateEntity(Menu\Mesh[1], 0, 180, Sin#(Menu\MeshFValues#[2])*10.0)
				
			Else
				PositionEntity(Menu\Mesh[1],0,-10+GetMenuCharacterExtraY#(4),25 + Sin(Menu\MeshFValues#[1])*0.5)
				PositionEntity(Menu\Mesh[2],0,-10+GetMenuCharacterExtraY#(4),25 + Sin(Menu\MeshFValues#[1])*0.5)
				PositionEntity(Menu\Mesh[3],0,-10+GetMenuCharacterExtraY#(4),25 + Sin(Menu\MeshFValues#[1])*0.5)
				RotateEntity(Menu\Mesh[1], 0, 0, Sin#(Menu\MeshFValues#[2])*10.0)
			EndIf
			
		Case MENU_REDRING#	
			If Menu\Transition=0 Then
				PositionEntity(Menu\Mesh[1],0,0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4),25)
				PositionEntity(Menu\Mesh[2],0,0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4),25)
				PositionEntity(Menu\Mesh[3],0,0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4),25)
				TurnEntity Menu\Mesh[1], 0, 0.2*15*d\Delta, 0
				TurnEntity Menu\Mesh[2], 0, 0.2*15*d\Delta, 0
				TurnEntity Menu\Mesh[3], 0, -0.2*15*d\Delta, 0
			Else
				PositionEntity(Menu\Mesh[1],0,-10+GetMenuCharacterExtraY#(4),25)
				PositionEntity(Menu\Mesh[2],0,-10+GetMenuCharacterExtraY#(4),25)
				PositionEntity(Menu\Mesh[3],0,-10+GetMenuCharacterExtraY#(4),25)
			EndIf
			
			
			EntityAlpha(Menu\Mesh[1],Menu\RedRingFade/secs#)
			
			
			EntityAlpha(Menu\Mesh[2],Menu\RubyFade/secs#)
			
			
			
		Case MENU_BLACKMARKET#:
			Select Menu\Menu2
				Case Menu_BlackMarket_BuyList#,Menu_BlackMarket_BuyConfirm#,Menu_BlackMarket_SellList#,Menu_BlackMarket_SellConfirm#:
					TurnEntity Menu\Mesh[3], 0, 2.5*d\Delta, 0
			End Select
		Case MENU_TRANSPORTER#:
			Select Menu\Menu2
				Case Menu_Transporter_Inventory#:
					TurnEntity Menu\Mesh[3], 0, 2.5*d\Delta, 0
			End Select
			Select Menu\Menu2
				Case Menu_Transporter_Goodbye#:
					If Menu\OptionOrder2<3 Then
						PositionEntity(Menu\Mesh[2],+0.4,CARD_PLACE#/100,0)
						Menu_CharacterMeshOnScreen_RotateControl_Chao()
					Else
						PositionEntity(Menu\Mesh[2],0,5,0)
					EndIf
					PositionEntity(Menu\Mesh[2],EntityX(Menu\Mesh[2])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[2])*GetMenuCharacterScale#()-GetMenuCharacterExtraY#(4),EntityZ(Menu\Mesh[2])*GetMenuCharacterScale#())
				Case Menu_Transporter_Stadium#:
					PositionEntity(Menu\Mesh[2],-0.025,CARD_PLACE#/100-0.0125,0)
					Menu_CharacterMeshOnScreen_RotateControl_Chao()
					PositionEntity(Menu\Mesh[2],EntityX(Menu\Mesh[2])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[2])*GetMenuCharacterScale#()-GetMenuCharacterExtraY#(4),EntityZ(Menu\Mesh[2])*GetMenuCharacterScale#())
				Default:
					PositionEntity(Menu\Mesh[2],0,5,0)
					PositionEntity(Menu\Mesh[2],EntityX(Menu\Mesh[2])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[2])*GetMenuCharacterScale#()-GetMenuCharacterExtraY#(),EntityZ(Menu\Mesh[2])*GetMenuCharacterScale#())
			End Select
	End Select
End Function

Function Menu_CharacterMeshOnScreen_RotateControl(char,found=True)
	If found Then
		If Input\Hold\MouseCamRight Or Input\Hold\CamRight Then TurnEntity Menu\Mesh[1], 0, 5*Game\DeltaTime\Delta, 0 : TurnEntity Menu\Mesh[2], 0, 5*Game\DeltaTime\Delta, 0 : TurnEntity Menu\Mesh[3], 0, 5*Game\DeltaTime\Delta, 0
		If Input\Hold\MouseCamLeft Or Input\Hold\CamLeft Then TurnEntity Menu\Mesh[1], 0, -5*Game\DeltaTime\Delta, 0 : TurnEntity Menu\Mesh[2], 0, -5*Game\DeltaTime\Delta, 0 : TurnEntity Menu\Mesh[3], 0, -5*Game\DeltaTime\Delta, 0
	EndIf
End Function

Function Menu_CharacterMeshOnScreen_RotateControl_Chao()
	If Input\Hold\MouseCamRight Or Input\Hold\CamRight Then TurnEntity Menu\Mesh[2], 0, 5*Game\DeltaTime\Delta, 0
	If Input\Hold\MouseCamLeft Or Input\Hold\CamLeft Then TurnEntity Menu\Mesh[2], 0, -5*Game\DeltaTime\Delta, 0
End Function

Function Menu_Particle_Emblem(entity, size#=1)
	ParticleTemplate_Call(Game\Stage\Properties\AmbientParticle, PARTICLE_MENU_EMBLEM, entity, size#)
End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_WasMemberChosen(j=0)
	Select Menu\Menu
		Case MENU_CHARACTERS#:
			char = Menu_Character(Menu\Option,Menu\Option2)
			If Menu\Option>=6*6 Then Return False
		Default: char = Menu\Option
	End Select
	char=InterfaceChar(char)
	For i=1 To Menu\MemberToSelect-1+j
		If InterfaceChar(Menu\Character[i])=char Then Return True
	Next
	Return False
End Function

Function Menu_ReturnCardColor(rgb, value, lives=False,heroes=False,stage=False)
	
	If stage Then
		Select rgb
			Case 1: Return Interface_Circle_R[InterfaceChar(value)]
			Case 2: Return Interface_Circle_G[InterfaceChar(value)]
			Case 3: Return Interface_Circle_B[InterfaceChar(value)]
		End Select
	EndIf
	
	If Menu\Team=0 Or heroes Then
		If lives=False Then
			Select rgb
				Case 1: Return Interface_Card2_R[InterfaceChar(value)]
				Case 2: Return Interface_Card2_G[InterfaceChar(value)]
				Case 3: Return Interface_Card2_B[InterfaceChar(value)]
			End Select
		Else
			Select rgb
				Case 1: Return Interface_Lives_R[InterfaceChar(value)]
				Case 2: Return Interface_Lives_G[InterfaceChar(value)]
				Case 3: Return Interface_Lives_B[InterfaceChar(value)]
			End Select
		EndIf
	Else
		Select rgb
			Case 1: Return Interface_Team_R[Menu\Team]
			Case 2: Return Interface_Team_G[Menu\Team]
			Case 3: Return Interface_Team_B[Menu\Team]
		End Select
	EndIf
End Function

;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================



;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================
;===============================================================================================================================================================

Function Menu_Teams_Update()
	
	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu
	
	
	
	If Menu\Option=3 Then
		SetColor(255,255,255)
	Else
		SetColor(Interface_Team_R[Menu\Option],Interface_Team_G[Menu\Option],Interface_Team_B[Menu\Option])
	EndIf
	
	DrawImageEx(INTERFACE(Interface_Circle), GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-4.5)*GAME_WINDOW_SCALE#)
	SetColor(255,255,255)
	
	
	
	DrawSmartButton(1, "Team Sonic", GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-60*GAME_WINDOW_SCALE#)
	DrawSmartButton(2, "Team Dark", GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-10*GAME_WINDOW_SCALE#)
	DrawSmartButton(3, "Custom Teams", GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+40*GAME_WINDOW_SCALE#)
	
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#,20)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+130*GAME_WINDOW_SCALE#,21)
	
	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>3 Then Menu\Option=1 
		Menu\MeshChange=1
	EndIf
	
	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=3 
		Menu\MeshChange=1
	EndIf
	
	If Input\Pressed\ActionJump  Then
		
		PlaySmartSound(Sound_MenuAccept)
		Menu\Transition=1
		Select Menu\Option
			Case 3:
				Menu\Members=3
				Menu\MemberToSelect=1
				Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
				Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
				Menu\NewMenu=MENU_CHARACTERS#
			Default:
				Menu\NewOption=Menu\SelectedStage : Menu\NewMenu=MENU_STAGE2#
				Select Menu\Option
					Case TEAM_SONIC:	Menu\Character[1]=CHAR_SON : Menu\Character[2]=CHAR_TAI : Menu\Character[3]=CHAR_KNU
					Case TEAM_DARK:		Menu\Character[1]=CHAR_SHA : Menu\Character[2]=CHAR_ROU : Menu\Character[3]=CHAR_OME
				End Select
				Menu\Team=Menu\Option
				If Menu\MarathonMode Then Menu_GoToStage()
		End Select
		
		
	EndIf
	
	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=1 : Menu\NewMenu=MENU_PLAY#
		Menu\MemberSelect=Menu\Members
	EndIf
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D