
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
		ElseIf Menu\Menu=MENU_BIOS# Then
			Menu\MeshMayChangeTimer=0
		Else
			Menu\MeshMayChangeTimer=0.3*secs#
		EndIf
		Menu\MeshChange = 2
		Menu\HasMeshBone = 0
	ElseIf Menu\MeshChange = 2 And (Not(Menu\MeshMayChangeTimer>0)) And Menu\Transition=0 Then
		Select Menu\Menu
		Case MENU_BIOS#:
			StopChannel(Menu\Channel_MenuCharacter)
			If IsCharMod(Menu\Option) Then
				LoadGoodSound(Sound_MenuCharacter,1,"Mods/Characters/"+MODCHARS_PATH$(Menu\Option-CHAR_MOD1+1)+"/sounds/Theme.ogg",3)
			Else
				LoadGoodSound(Sound_MenuCharacter,1,"Sounds/Menu/CharacterThemes/"+ShortCharNames$(InterfaceChar(Menu\Option),1)+".ogg",3)
			EndIf
			If Menu\DontReplayMusic>0 Then Menu\DontReplayMusic=3
			Menu\Channel_MenuCharacter=PlaySmartSound(Sound_MenuCharacter)
			Menu\Bio$ = ""
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
		If Menu\Menu=MENU_CHARACTERS# Or Menu\Menu=MENU_CHARACTERS2# Or Menu\Menu=MENU_BIOS# Or Menu\Menu=MENU_TEAMS# Or Menu\Menu=MENU_EMBLEM# Then
			If Menu\Mesh[1]<>0 Then FreeEntity(Menu\Mesh[1])
			If Menu\Mesh[2]<>0 Then FreeEntity(Menu\Mesh[2])
			If Menu\Mesh[3]<>0 Then FreeEntity(Menu\Mesh[3])
			Select Menu\Menu
				Case MENU_CHARACTERS#,MENU_CHARACTERS2#:
					char = Menu_Character(Menu\Option,Menu\Option2)
					If char=CHAR_TAI And Menu\CharacterMode[Menu\MemberToSelect]=1 Then charalt=1 Else charalt=0
					If Menu\Menu=MENU_CHARACTERS2# Then
						If (Not(Menu\Option>=6*6)) Then
							If UNLOCKEDCHAR[char]=0 Then found=False Else found=True
						Else
							found=False
						EndIf
					Else
						If UNLOCKEDCHAR[char]=0 Then found=False Else found=True
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
						If Menu\Menu=MENU_CHARACTERS2# And (Menu\Option>=6*6) Then
							LoadCharacterMesh(-1)
						Else
							Select char
								Case CHAR_TAI:
									If charalt=1 Then
										LoadCharacterMesh(char,1,0,2)
									Else
										LoadCharacterMesh(char,1)
									EndIf
								Case CHAR_EGG:
									LoadCharacterMesh(char,1,0,2)
								Default:
									LoadCharacterMesh(char,1)
							End Select
						EndIf
						Menu\MeshCharacter[1]=char
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						If (Not(Menu\Menu=MENU_CHARACTERS2#)) Or (Not(Menu\Option>=6*6)) Then
							RotateEntity(Menu\Mesh[1],0,180,0)
							Select char
								Case CHAR_TAI: actualchar=900*charalt+char
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
				Case MENU_BIOS#:
					char = Menu\Option
					If char<=CHAR_PLAYABLECOUNT Then
						If UNLOCKEDCHAR[char]=0 Then found=False Else found=True
					Else
						found=True
					EndIf
					If found=False Then
						LoadCharacterMesh(-1)
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						EntityColor(Menu\Mesh[1],0,0,0)
						scale# = 0.11*GetMenuCharacterScale#()
						ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
						Menu\CharacterMeshAnimation=0
						Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
					Else
						LoadCharacterMesh(char,2,CharHasSuperModel(Menu\MeshCharacterSuper,char))
						Menu\MeshCharacter[1]=char
						Menu\Mesh[1]=CopyEntity(CharacterMesh, Game\Stage\Root)
						DeleteCharacterMesh()
						Select char
							Case CHAR_VEC,CHAR_OME,CHAR_EGG,CHAR_EGN,CHAR_PRS,CHAR_COM,CHAR_EGR:
								scale# = 0.0425*GetMenuCharacterScale#()
								ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
							Case CHAR_BIG,CHAR_GAM,CHAR_BET,CHAR_CHW:
								scale# = 0.035*GetMenuCharacterScale#()
								ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
							Case CHAR_CHO,CHAR_BAR,CHAR_STO,CHAR_HBO,CHAR_MIA:
								scale# = 0.05*GetMenuCharacterScale#()
								ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
							Default:
								If IsCharMod(char) Then
									If GetCharScaleFactor#(char)>=GetCharScaleFactor#(CHAR_BIG) Then
										scale# = 0.035*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									ElseIf GetCharScaleFactor#(char)>=GetCharScaleFactor#(CHAR_OME) Then
										scale# = 0.0425*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									ElseIf GetCharScaleFactor#(char)>=GetCharScaleFactor#(CHAR_STO) Then
										scale# = 0.05*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									Else
										scale# = 0.06*GetMenuCharacterScale#()
										ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
									EndIf
								Else
									scale# = 0.06*GetMenuCharacterScale#()
									ScaleEntity(Menu\Mesh[1],scale#,scale#,scale#)
								EndIf
						End Select
						RotateEntity(Menu\Mesh[1],0,180,0)
						Menu\CharacterMeshAnimation=1
						If char=CHAR_SHD Then
							Menu\MeshBone=FindChild(Menu\Mesh[1], "head2")
							Menu\HasMeshBone=1
						Else
							Menu\MeshBone=0
							Menu\HasMeshBone=0
						EndIf
						Select char
							Case CHAR_CRE:
								LoadCharacterMesh(CHAR_CHE,2)
								Menu\Mesh[2]=CopyEntity(CharacterMesh, Game\Stage\Root)
								DeleteCharacterMesh()
								ScaleEntity(Menu\Mesh[2],0.06*GetMenuCharacterScale#(),0.06*GetMenuCharacterScale#(),0.06*GetMenuCharacterScale#())
								RotateEntity(Menu\Mesh[2],0,180,0)
								Menu\MeshChaoEmo = Object_ChaoEmo_Create.tChaoEmo(Menu\Mesh[2],CHAOSIDE_NEUTRAL,True)
								Menu\MeshChaoEmoActivated=1
							Case CHAR_BIG:
								LoadCharacterMesh(CHAR_FRO,2)
								Menu\Mesh[2]=CopyEntity(CharacterMesh, Game\Stage\Root)
								DeleteCharacterMesh()
								ScaleEntity(Menu\Mesh[2],0.035*GetMenuCharacterScale#(),0.035*GetMenuCharacterScale#(),0.035*GetMenuCharacterScale#())
								RotateEntity(Menu\Mesh[2],0,-7.5+180,0)
							Case CHAR_VAN:
								LoadCharacterMesh(CHAR_CHC,2)
								Menu\Mesh[2]=CopyEntity(CharacterMesh, Game\Stage\Root)
								DeleteCharacterMesh()
								ScaleEntity(Menu\Mesh[2],0.06*GetMenuCharacterScale#(),0.06*GetMenuCharacterScale#(),0.06*GetMenuCharacterScale#())
								RotateEntity(Menu\Mesh[2],0,180,0)
								Menu\MeshChaoEmo = Object_ChaoEmo_Create.tChaoEmo(Menu\Mesh[2],CHAOSIDE_NEUTRAL,True)
								Menu\MeshChaoEmoActivated=1
							Default:
								Menu\Mesh[2] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
						End Select
						Menu\CharacterMesh2MovedOnce=0
					EndIf
					Menu\Mesh[3] = CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
				Case MENU_TEAMS#:
					m=Menu\TeamOrder+Menu\Option
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
									Case TEAM_ROSE:
										Select i
											Case 1: k=CHAR_AMY
											Case 2: k=CHAR_CRE
											Case 3: k=CHAR_BIG
										End Select
									Case TEAM_CHAOTIX:
										Select i
											Case 1: k=CHAR_ESP
											Case 2: k=CHAR_CHA
											Case 3: k=CHAR_VEC
										End Select
									Case TEAM_SOL:
										Select i
											Case 1: k=CHAR_BLA
											Case 2: k=CHAR_SIL
											Case 3: k=CHAR_MAR
										End Select
									Case TEAM_OLDIES:
										Select i
											Case 1: k=CHAR_MIG
											Case 2: k=CHAR_RAY
											Case 3: k=CHAR_HBO
										End Select
									Case TEAM_HOOLIGAN:
										Select i
											Case 1: k=CHAR_NAC
											Case 2: k=CHAR_BEA
											Case 3: k=CHAR_BAR
										End Select
									Case TEAM_BABYLON:
										Select i
											Case 1: k=CHAR_JET
											Case 2: k=CHAR_WAV
											Case 3: k=CHAR_STO
										End Select
									Case TEAM_RELIC:
										Select i
											Case 1: k=CHAR_SHD
											Case 2: k=CHAR_TIK
											Case 3: k=CHAR_CHO
										End Select
									Case TEAM_ROBOTNIK:
										Select i
											Case 1: k=CHAR_MET
											Case 2: k=CHAR_TDL
											Case 3: k=CHAR_MKN
										End Select
								End Select
								LoadCharacterMesh(k,3)
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
			Default:
				Select Menu\Menu
					Case MENU_TEAMS#: k=3
					Default: k=1
				End Select
				For i=1 To k
					Select Menu\CharacterMeshAnimation
						Case 0,1;Spin
							Animate(Menu\Mesh[i],1, ((0.3))/2,ANIMATION_BIO,0)
					End Select

				Next
				If Menu\Menu=MENU_BIOS# Then
					Select Menu\CharacterMeshAnimation
						Case 0;Spin
							Animate(Menu\Mesh[i],1, ((0.6))/2,ANIMATION_BIO,0)
						Case 1,2;Idle
							Select Menu\Option
								Case CHAR_CRE: Animate(Menu\Mesh[2], 1, 0.255, 1, 10) : If Menu\MeshChaoEmoActivated=1 Then Menu\MeshChaoEmo\Emotion=CHAOEMO_default
								Case CHAR_BIG: Animate(Menu\Mesh[2], 1, 0.255, 1, 10)
								Case CHAR_VAN: Animate(Menu\Mesh[2], 1, 0.255, 1, 10) : If Menu\MeshChaoEmoActivated=1 Then Menu\MeshChaoEmo\Emotion=CHAOEMO_default
							End Select
						Case 3;Walk
							Select Menu\Option
								Case CHAR_CRE: Animate(Menu\Mesh[2], 1, 0.1275, 6, 10) : If Menu\MeshChaoEmoActivated=1 Then Menu\MeshChaoEmo\Emotion=CHAOEMO_cheerful
								Case CHAR_BIG: Animate(Menu\Mesh[2], 1, 0.544, 2, 10)
								Case CHAR_VAN: Animate(Menu\Mesh[2], 1, 0.1275, 6, 10) : If Menu\MeshChaoEmoActivated=1 Then Menu\MeshChaoEmo\Emotion=CHAOEMO_cheerful
							End Select
						Case 4;Jog
							Select Menu\Option
								Case CHAR_CRE: Animate(Menu\Mesh[2], 1, 0.255, 2, 10) : If Menu\MeshChaoEmoActivated=1 Then Menu\MeshChaoEmo\Emotion=CHAOEMO_default
								Case CHAR_BIG: Animate(Menu\Mesh[2], 1, 0.816, 2, 10)
							End Select
						Case 5;Run
							Select Menu\Option
								Case CHAR_CRE: Animate(Menu\Mesh[2], 1, 0.3188, 3, 10) : If Menu\MeshChaoEmoActivated=1 Then Menu\MeshChaoEmo\Emotion=CHAOEMO_default
								Case CHAR_BIG: Animate(Menu\Mesh[2], 1, 1.088, 2, 10)
							End Select
					End Select
				EndIf
		End Select
		Menu\MeshChange = 0
	EndIf

	;Position mesh and do other stuff to it
	Select Menu\Menu
		Case MENU_CHARACTERS#,MENU_CHARACTERS2#:
			If Menu\Menu=MENU_CHARACTERS# Then
				PositionEntity(Menu\Mesh[1],0,0.025+CARD_PLACE#/100,0)
			Else
				PositionEntity(Menu\Mesh[1],-0.275,0.045+CARD_PLACE#/100,0)
			EndIf 
				
			PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#()-GetMenuCharacterExtraY#(),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#())
		Case MENU_BIOS#:
			If Menu\Option<=CHAR_PLAYABLECOUNT Then
				If UNLOCKEDCHAR[Menu\Option]=1 Then found=True Else found=False
			Else
				found=True
			EndIf
			If found Then
				Select Menu\Option
					Case CHAR_VEC,CHAR_OME,CHAR_EGR:
						PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.04,0)
					Case CHAR_BIG,CHAR_GAM,CHAR_BET,CHAR_CHW:
						PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.055,0)
					Case CHAR_EGG,CHAR_EGN,CHAR_PRS,CHAR_COM:
						PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.04-0.01,0)
					Case CHAR_VAN:
						PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.01,0)
					Case CHAR_CHO,CHAR_BAR,CHAR_STO,CHAR_HBO,CHAR_MIA:
						PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.025,0)
					Default:
						If IsCharMod(Menu\Option) Then
							If GetCharScaleFactor#(Menu\Option)>=GetCharScaleFactor#(CHAR_BIG) Then
								PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.055,0)
							ElseIf GetCharScaleFactor#(Menu\Option)>=GetCharScaleFactor#(CHAR_OME) Then
								PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.04,0)
							ElseIf GetCharScaleFactor#(Menu\Option)>=GetCharScaleFactor#(CHAR_STO) Then
								PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100-0.025,0)
							Else
								PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100,0)
							EndIf
						Else
							PositionEntity(Menu\Mesh[1],+0.275,-0.06-CARD_PLACE#/100,0)
						EndIf
				End Select
				Select Menu\Option
					Case CHAR_CRE:
						If Menu\CharacterMesh2MovedOnce=0 Then
							PositionEntity(Menu\Mesh[2],+0.385,-0.06-CARD_PLACE#/100+0.1,0)
						Else
							PositionEntity(Menu\Mesh[2],+0.385,-0.06-CARD_PLACE#/100+0.2,0)
						EndIf
						If (Not(EntityYaw(Menu\Mesh[1])<(-180+10) Or EntityYaw(Menu\Mesh[1])>(180-5))) Or Menu\CharacterMeshAnimation>2 Then Menu\CharacterMesh2MovedOnce=1
						If Menu\Transition>0 Then Menu\CharacterMesh2MovedOnce=0
					Case CHAR_BIG:
						PositionEntity(Menu\Mesh[2],+0.135,-0.06-CARD_PLACE#/100-0.132,-1)
					Case CHAR_VAN:
						PositionEntity(Menu\Mesh[2],+0.065,-0.06-CARD_PLACE#/100+0.142,0)
				End Select
				PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#()+GetMenuCharacterExtraY#(),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#())
				PositionEntity(Menu\Mesh[2],EntityX(Menu\Mesh[2])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[2])*GetMenuCharacterScale#()+GetMenuCharacterExtraY#(),EntityZ(Menu\Mesh[2])*GetMenuCharacterScale#())
			Else
				PositionEntity(Menu\Mesh[1],+0.275,0.165+CARD_PLACE#/100-0.175,0)
				PositionEntity(Menu\Mesh[1],EntityX(Menu\Mesh[1])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[1])*GetMenuCharacterScale#()+GetMenuCharacterExtraY#(2),EntityZ(Menu\Mesh[1])*GetMenuCharacterScale#())
				PositionEntity(Menu\Mesh[2],EntityX(Menu\Mesh[2])*GetMenuCharacterScale#(),EntityY(Menu\Mesh[2])*GetMenuCharacterScale#()+GetMenuCharacterExtraY#(2),EntityZ(Menu\Mesh[2])*GetMenuCharacterScale#())
			EndIf
		Case MENU_TEAMS#:
			If UNLOCKEDTEAM[Menu\OptionOrder2]=1 Then
				If Menu\OptionOrder2>=TEAM_TEAMCOUNT Then
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
		Case MENU_EMBLEM#:
			If Menu\Transition=0 Then
				PositionEntity(Menu\Mesh[1],0,0.165-CARD_PLACE#/100-0.4+0.325+GetMenuCharacterExtraY#(4),25)
				TurnEntity Menu\Mesh[1], 0, 0.2*20*d\Delta, 0
				PositionEntity(Menu\Mesh[2],0+Cos(EntityYaw(Menu\Mesh[1]))*0.25,0.165-CARD_PLACE#/100-0.4+0.575+GetMenuCharacterExtraY#(4),25+Sin(EntityYaw(Menu\Mesh[1]))*0.25)
				PositionEntity(Menu\Mesh[3],0-Cos(EntityYaw(Menu\Mesh[1]))*0.25,0.165-CARD_PLACE#/100-0.4+0.575+GetMenuCharacterExtraY#(4),25-Sin(EntityYaw(Menu\Mesh[1]))*0.25)
				If Menu\LoadedEmblemYet=1 Then
					;Menu_Particle_Emblem(Menu\Mesh[3],0.35)
					;Menu_Particle_Emblem(Menu\Mesh[2],0.35)
				EndIf
			Else
				PositionEntity(Menu\Mesh[1],0,-10+GetMenuCharacterExtraY#(4),25)
			EndIf
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
		Case MENU_CHARACTERS2#:
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

Function Menu_ReturnCardColor(rgb, value, lives=False)
	If Menu\Team=0 Then
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

Function Menu_Play_Update(mode=0)

	Menu\Music=1
	Menu\Background=1
	Menu\ShowCards=True
	Menu\ControlsToShow=Menu\Menu

	If Not mode=2 Then
		If Menu\NewMenu=Menu\Menu Then
			DrawImageEx(INTERFACE(Interface_Icons), (25+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 4)
			DrawBetterNumber(EMBLEMS, (53+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#)
			;---
			DrawImageEx(INTERFACE(Interface_Icons), (25+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 91*GAME_WINDOW_SCALE#, 2)
			DrawBetterNumber(Menu\Wallet, (53+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 91*GAME_WINDOW_SCALE#)
			;---
			SetColor(Interface_Lives_R[InterfaceChar(Menu\Character[1])],Interface_Lives_G[InterfaceChar(Menu\Character[1])],Interface_Lives_B[InterfaceChar(Menu\Character[1])])
			Interface_DrawHead((25+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 127*GAME_WINDOW_SCALE#, Menu\Character[1]-1)
			SetColor(255,255,255)
			DrawBetterNumber(Game\Gameplay\Lives, (53+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 127*GAME_WINDOW_SCALE#)
			
		Else
			DrawImageEx(INTERFACE(Interface_Icons), (25-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#, 4)
			DrawBetterNumber(EMBLEMS, (53-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 55*GAME_WINDOW_SCALE#)
			;---
			DrawImageEx(INTERFACE(Interface_Icons), (25-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 91*GAME_WINDOW_SCALE#, 2)
			DrawBetterNumber(Menu\Wallet, (53-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 91*GAME_WINDOW_SCALE#)
			;---
			SetColor(Interface_Lives_R[InterfaceChar(Menu\Character[1])],Interface_Lives_G[InterfaceChar(Menu\Character[1])],Interface_Lives_B[InterfaceChar(Menu\Character[1])])
			Interface_DrawHead((25-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 127*GAME_WINDOW_SCALE#, Menu\Character[1]-1)
			SetColor(255,255,255)
			DrawBetterNumber(Game\Gameplay\Lives, (53-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, 127*GAME_WINDOW_SCALE#)
			
			
		EndIf
	EndIf

	Select mode
		Case 0:
			DrawSmartButton(1, "Single", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Pear", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
			DrawSmartButton(3, "Teem", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#)
			
			;changemee
;			If Menu\NewMenu=Menu\Menu Then
;				DrawSmartButton(4, "Chao World", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, (58+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, False, True)
;				DrawSmartButton(5, "Special Stage", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, (58+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#+32.5*GAME_WINDOW_SCALE#, False, True, Abs(UNLOCKEDSPECIALSTAGES[0]-1))
;				DrawSmartButton(6, "Marathon Mode", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, (58+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#+2*32.5*GAME_WINDOW_SCALE#, False, True)
;				DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#, (58+BUTTON_PLACE1#)*GAME_WINDOW_SCALE#)
;			Else
;				DrawSmartButton(4, "Chao World", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, (58-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#, False, True)
;				DrawSmartButton(5, "Special Stage", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, (58-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#+32.5*GAME_WINDOW_SCALE#, False, True, Abs(UNLOCKEDSPECIALSTAGES[0]-1))
;				DrawSmartButton(6, "Marathon Mode", GAME_WINDOW_W-80*GAME_WINDOW_SCALE#, (58-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#+2*32.5*GAME_WINDOW_SCALE#, False, True)
;				DrawSmartKey(INPUT_BUTTON_ACTIONDRIFT, GAME_WINDOW_W-160*GAME_WINDOW_SCALE#, (58-BUTTON_PLACE1#)*GAME_WINDOW_SCALE#)
;			EndIf

			If Input\Pressed\Down Then
				If (Not(Menu\Option=5)) Or UNLOCKEDSPECIALSTAGES[0] Then PlaySmartSound(Sound_MenuMove)
				Select Menu\Option
					Case 1: Menu\Option=2
					Case 2: Menu\Option=3
					Case 3: Menu\Option=1
					Case 4: If UNLOCKEDSPECIALSTAGES[0] Then Menu\Option=5 Else Menu\Option=6
					Case 5: Menu\Option=6
					Case 6: Menu\Option=4
				End Select
			EndIf

			If Input\Pressed\Up Then
				If (Not(Menu\Option=5)) Or UNLOCKEDSPECIALSTAGES[0] Then PlaySmartSound(Sound_MenuMove)
				Select Menu\Option
					Case 1: Menu\Option=3
					Case 2: Menu\Option=1
					Case 3: Menu\Option=2
					Case 4: Menu\Option=6
					Case 5: Menu\Option=4
					Case 6: If UNLOCKEDSPECIALSTAGES[0] Then Menu\Option=5 Else Menu\Option=4
				End Select
			EndIf
			
			;changemeee
;			If Input\Pressed\ActionDrift Then
;				PlaySmartSound(Sound_MenuMove)
;				Select Menu\Option
;					Case 4,5,6: Menu\Option=1
;					Default: Menu\Option=4
;				End Select
;			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\Transition=1
				For i = 1 To 3 : Menu\Character[i]=InterfaceChar(Menu\Character[i]) : Next
				Select Menu\Option
					Case 4:
						Menu\Members=1
						Menu\ChaoGarden=1
						Menu\MarathonMode=0
						Menu\MemberToSelect=1
						Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
						Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
						Menu\NewMenu=MENU_CHARACTERS2#
					Case 5:
						Menu\Members=1
						Menu\ChaoGarden=2
						Menu\MarathonMode=0
						Menu\MemberToSelect=1
						Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
						Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
						Menu\NewMenu=MENU_CHARACTERS2#
						Menu\MissionNo=1
					Case 6:
						LoadMarathonList()
						If Menu\MarathonExists Then Menu\NewOption=2 Else Menu\NewOption=1
						Menu\NewMenu=MENU_MARATHON#
						Menu\MarathonMode=1
					Default:
						Menu\Members=Menu\Option
						Menu\ChaoGarden=0
						Menu\MarathonMode=0
						Select Menu\Option
							Case 3:
								;Menu\NewOption=1
								;If Menu\Team>0 Then Menu\TeamOrder=Menu\Team-1 Else Menu\TeamOrder=1-1
								;Menu\NewMenu=MENU_TEAMS#
								Menu\Members=3
								Menu\MemberToSelect=1
								Menu\NewOption2=1
								Menu\NewOption=1
								Menu\NewMenu=MENU_CHARACTERS#
							Default:
								Menu\MemberToSelect=1
								Menu\NewOption2=1
								Menu\NewOption=1
								Menu\NewMenu=MENU_CHARACTERS#
								;changemeee
								;Menu\MemberToSelect=1
								;Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
								;Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
								;Menu\NewMenu=MENU_CHARACTERS2#
						End Select
				End Select
			EndIf

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewOption=1
				Menu\NewMenu=MENU_MAIN#
			EndIf
		Case 1:
			DrawSmartButton(1, "Single", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-75*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Pair", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-25*GAME_WINDOW_SCALE#)
			DrawSmartButton(3, "Team", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+25*GAME_WINDOW_SCALE#)
			If Menu\MarathonRandom Then
				DrawSmartButton(4, "Randomized: On", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#)
			Else
				DrawSmartButton(4, "Randomized: Off", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+75*GAME_WINDOW_SCALE#)
			EndIf

			If Input\Pressed\Down Then
			 	PlaySmartSound(Sound_MenuMove)
			 	Menu\Option=Menu\Option+1
				If Menu\Option>4 Then Menu\Option=1
			EndIf

			If Input\Pressed\Up Then
			 	PlaySmartSound(Sound_MenuMove)
			 	Menu\Option=Menu\Option-1
				If Menu\Option<1 Then Menu\Option=4
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Select Menu\Option
					Case 4:
						Menu\MarathonRandom=Abs(Menu\MarathonRandom-1)
					Default:
						Menu\Transition=1
						Menu\ChaoGarden=0
						If Menu\MarathonRandom Then
							Menu\Members=Menu\Option : Menu\MembersMarathon=Menu\Members
							Menu_GoToStage()
						Else
							For i = 1 To 3 : Menu\Character[i]=InterfaceChar(Menu\Character[i]) : Next
							Menu\Members=Menu\Option : Menu\MembersMarathon=Menu\Members
							Select Menu\Option
								Case 3:
									Menu\NewOption=1
									If Menu\Team>0 Then Menu\TeamOrder=Menu\Team-1 Else Menu\TeamOrder=1-1
									Menu\NewMenu=MENU_TEAMS#
								Default:
									Menu\MemberToSelect=1
									Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
									Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
									Menu\NewMenu=MENU_CHARACTERS2#
							End Select
						EndIf
				End Select
			EndIf

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewOption=6
				Menu\NewMenu=MENU_PLAY#
			EndIf
		Case 2:
			DrawRealText("Play continuously through all stages in a randomized order.", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#+0*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("If you previously quit during a marathon,", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#+1*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)
			DrawRealText("you can continue from where you left off.", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-85*GAME_WINDOW_SCALE#+2*20*GAME_WINDOW_SCALE#, (Interface_Text_3), 1)

			DrawSmartButton(1, "New Marathon", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+10*GAME_WINDOW_SCALE#)
			DrawSmartButton(2, "Continue", GAME_WINDOW_W/2+BUTTON_PLACE1#*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+60*GAME_WINDOW_SCALE#, False, False, Abs(Menu\MarathonExists-1))

			If Input\Pressed\Down Or Input\Pressed\Up Then
			 	Select Menu\Option
			 		Case 1: If Menu\MarathonExists Then Menu\Option=2 : PlaySmartSound(Sound_MenuMove)
			 		Case 2: Menu\Option=1 : PlaySmartSound(Sound_MenuMove)
			 	End Select
			EndIf

			If Input\Pressed\ActionJump Or Input\Pressed\Start Then
				PlaySmartSound(Sound_MenuAccept)
				Menu\Transition=1
				Menu\NewOption=Menu\Members
				Menu\NewMenu=MENU_PLAYMARATHON#
				If Menu\Option=1 Then
					Menu\MarathonStage=1
					For i=2 To StageAmount
						MarathonStage(i-1)=i
					Next
					For i=1 To StageAmount-1
						j=Rand(1,StageAmount-1)
						temp=MarathonStage(i)
						MarathonStage(i)=MarathonStage(j)
						MarathonStage(j)=temp
					Next
					SaveMarathonList()
				EndIf
				If Menu\MarathonStage=0 Then Menu\MarathonStage=1
			EndIf

			If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
				PlaySmartSound(Sound_MenuBack)
				Menu\Transition=1
				Menu\NewOption=6
				Menu\NewMenu=MENU_PLAY#
			EndIf
	End Select

End Function

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

	Menu\OptionOrder2=Menu\TeamOrder+Menu\Option
	If Menu\OptionOrder2>TEAM_TEAMCOUNT Then Menu\OptionOrder2=Menu\OptionOrder2-TEAM_TEAMCOUNT
	If UNLOCKEDTEAM[Menu\OptionOrder2]=1 Then;!!!!!
		If Menu\OptionOrder2>=TEAM_TEAMCOUNT Then
			SetColor(255,255,255)
		Else
			SetColor(Interface_Team_R[Menu\OptionOrder2],Interface_Team_G[Menu\OptionOrder2],Interface_Team_B[Menu\OptionOrder2])
		EndIf
	Else;!!!!!
		SetColor(0,0,0)
	EndIf;!!!!!
	DrawImageEx(INTERFACE(Interface_Circle), GAME_WINDOW_W/2-140*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-4.5)*GAME_WINDOW_SCALE#)
	SetColor(255,255,255)

	If UNLOCKEDTEAM[Menu\OptionOrder2]=0 Then;!!!!!
		Menu_PrintLocked(3,Menu\OptionOrder2,GAME_WINDOW_W/2-228.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-(CARD_PLACE#*3-4.5)*GAME_WINDOW_SCALE#+22.5*GAME_WINDOW_SCALE#)
	EndIf;!!!!!

	Menu_UpdateTeamButtons(1+Menu\TeamOrder) : DrawSmartButton(1, Menu\TeamButton$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-100*GAME_WINDOW_SCALE#)
	Menu_UpdateTeamButtons(2+Menu\TeamOrder) : DrawSmartButton(2, Menu\TeamButton$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-50*GAME_WINDOW_SCALE#)
	Menu_UpdateTeamButtons(3+Menu\TeamOrder) : DrawSmartButton(3, Menu\TeamButton$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-0*GAME_WINDOW_SCALE#)
	Menu_UpdateTeamButtons(4+Menu\TeamOrder) : DrawSmartButton(4, Menu\TeamButton$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+50*GAME_WINDOW_SCALE#)
	Menu_UpdateTeamButtons(5+Menu\TeamOrder) : DrawSmartButton(5, Menu\TeamButton$, GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+100*GAME_WINDOW_SCALE#)

	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2-130*GAME_WINDOW_SCALE#,20)
	DrawImageEx(INTERFACE(Interface_Icons), GAME_WINDOW_W/2+(BUTTON_PLACE1#-10)*GAME_WINDOW_SCALE#, GAME_WINDOW_H/2+130*GAME_WINDOW_SCALE#,21)

	If Input\Pressed\Down Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option+1
		If Menu\Option>5 Then Menu\Option=5 : Menu\TeamOrder=Menu\TeamOrder+1
		If Menu\TeamOrder>TEAM_TEAMCOUNT Then Menu\TeamOrder=0
		Menu\MeshChange=1
	EndIf

	If Input\Pressed\Up Then
		PlaySmartSound(Sound_MenuMove)
		Menu\Option=Menu\Option-1
		If Menu\Option<1 Then Menu\Option=1 : Menu\TeamOrder=Menu\TeamOrder-1
		If Menu\TeamOrder<0 Then Menu\TeamOrder=TEAM_TEAMCOUNT-1
		Menu\MeshChange=1
	EndIf

	If Input\Pressed\ActionJump Or Input\Pressed\Start Then
		Menu\OptionOrder2=Menu\TeamOrder+Menu\Option
		If Menu\OptionOrder2>TEAM_TEAMCOUNT Then Menu\OptionOrder2=Menu\OptionOrder2-TEAM_TEAMCOUNT
		If UNLOCKEDTEAM[Menu\OptionOrder2]=1 Then;!!!!!
			PlaySmartSound(Sound_MenuAccept)
			Menu\Transition=1
			Select Menu\OptionOrder2
				Case TEAM_TEAMCOUNT:
					Menu\Members=3
					Menu\MemberToSelect=1
					Menu\NewOption2=Ceil#(Menu\Character[1]/35.0)
					Menu\NewOption=Menu\Character[1]-35*(Menu\NewOption2-1)
					Menu\NewMenu=MENU_CHARACTERS2#
				Default:
					Menu\NewOption=Menu\SelectedStage : Menu\NewMenu=MENU_STAGE2#
					Select Menu\OptionOrder2
						Case TEAM_SONIC:	Menu\Character[1]=CHAR_SON : Menu\Character[2]=CHAR_TAI : Menu\Character[3]=CHAR_KNU
						Case TEAM_DARK:		Menu\Character[1]=CHAR_SHA : Menu\Character[2]=CHAR_ROU : Menu\Character[3]=CHAR_OME
						Case TEAM_ROSE:		Menu\Character[1]=CHAR_AMY : Menu\Character[2]=CHAR_CRE : Menu\Character[3]=CHAR_BIG
						Case TEAM_CHAOTIX:	Menu\Character[1]=CHAR_ESP : Menu\Character[2]=CHAR_CHA : Menu\Character[3]=CHAR_VEC
						Case TEAM_SOL:		Menu\Character[1]=CHAR_BLA : Menu\Character[2]=CHAR_SIL : Menu\Character[3]=CHAR_MAR
						Case TEAM_OLDIES:	Menu\Character[1]=CHAR_MIG : Menu\Character[2]=CHAR_RAY : Menu\Character[3]=CHAR_HBO
						Case TEAM_HOOLIGAN:	Menu\Character[1]=CHAR_NAC : Menu\Character[2]=CHAR_BEA : Menu\Character[3]=CHAR_BAR
						Case TEAM_BABYLON:	Menu\Character[1]=CHAR_JET : Menu\Character[2]=CHAR_WAV : Menu\Character[3]=CHAR_STO
						Case TEAM_RELIC:	Menu\Character[1]=CHAR_SHD : Menu\Character[2]=CHAR_TIK : Menu\Character[3]=CHAR_CHO
						Case TEAM_ROBOTNIK:	Menu\Character[1]=CHAR_MET : Menu\Character[2]=CHAR_TDL : Menu\Character[3]=CHAR_MKN
					End Select
					Menu\Team=Menu\OptionOrder2
					If Menu\MarathonMode Then Menu_GoToStage()
			End Select
		Else;!!!!!
			PlaySmartSound(Sound_MenuRefuse)
		EndIf;!!!!!
	EndIf

	If Input\Pressed\ActionRoll Or Input\Pressed\Back Or Input\Pressed\ActionSkill1 Then
		PlaySmartSound(Sound_MenuBack)
		Menu\Transition=1
		Menu\NewOption=Menu\Members : Menu\NewMenu=MENU_PLAY#
	EndIf

End Function
;~IDEal Editor Parameters:
;~C#Blitz3D