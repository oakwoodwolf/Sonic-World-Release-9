Function Player_UpdateBoneEntities(p.tPlayer)
	
	Select p\Character
		Case CHAR_SHA,CHAR_SHN:
			Select p\Animation\Animation
				Case ANIMATION_RUN,ANIMATION_MACHRUN,ANIMATION_DRIFTL,ANIMATION_DRIFTR:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
						ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
						ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET,p\Objects\Jet3)
						ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET,p\Objects\Jet4)
					EndIf
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET,p\Objects\Jet3)
					ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET,p\Objects\Jet4)
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.25,0.2,2,p\Character,5)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.0,0.34,2,p\Character,1)
			End Select
		Case CHAR_BLA
			If p\Animation\Animation= ANIMATION_KICK2 Or p\Animation\Animation=ANIMATION_KICK Then
				ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_FIRE,p\Objects\FootL,0.6,0.64,3,p\Character,2)
				ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_FIRE,p\Objects\FootR,0.6,0.64,3,p\Character,2)
			EndIf
		Case CHAR_OME,CHAR_EGR:
			Select p\Animation\Animation
				Case ANIMATION_RUN,ANIMATION_MACHRUN,ANIMATION_DRIFTL,ANIMATION_DRIFTR:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
						ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					EndIf
				Case ANIMATION_GLIDE,ANIMATION_FLY:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
			End Select
		Case CHAR_MET,CHAR_MT3:
			Select p\Animation\Animation
				Case ANIMATION_RUN,ANIMATION_FLY:
					If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
						ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					EndIf
			End Select
		Case CHAR_MKN:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,2,0.125,2,p\Character,1)
			End Select
	 	Case CHAR_KNU,CHAR_COM:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,2,0.125,2,p\Character,1)
			End Select
	 	Case CHAR_ROU:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.0,0.34,2,p\Character,2)
			End Select
	 	Case CHAR_MIG:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
			End Select
	 	Case CHAR_SON:
			Select p\Animation\Animation
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,3,p\Character,2)
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.25,0.2,2,p\Character,5)
			End Select
	 	Case CHAR_BAR:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,3,p\Character,2)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,3,p\Character,2)
				Case ANIMATION_GLIDE:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_SNOW,p\Objects\HandL)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_SNOW,p\Objects\HandR)
			End Select
		Case CHAR_STO:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
					ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET,p\Objects\Jet3)
					ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET,p\Objects\Jet4)
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,3,p\Character,2)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,3,p\Character,2)
				Case ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,3,p\Character,2)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,3,p\Character,2)
			End Select
	 	Case CHAR_TIK:
			Select p\Animation\Animation
				Case ANIMATION_FLY,ANIMATION_GLIDE,ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH3,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
					ParticleTemplate_Call(p\Particle2,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
			End Select
	 	Case CHAR_HON,CHAR_TIA:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.0,0.34,2,p\Character,2)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,2,p\Character,2)
			End Select
		Case CHAR_CHO:
			Select p\Animation\Animation
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.25,0.2,2,p\Character,5)
			End Select
		Case CHAR_GAM
			Select p\Animation\Animation
				Case ANIMATION_FLY,ANIMATION_THROWAIR:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet2)
					ParticleTemplate_Call(p\JetParticle3,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet3)
					ParticleTemplate_Call(p\JetParticle4,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet4)
			End Select
		Case CHAR_TMH:
			Select p\Animation\Animation
				Case ANIMATION_GLIDE,ANIMATION_THROWAIR:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET2,p\Objects\Jet1)
			End Select
		Case CHAR_EME,CHAR_GME:
			Select p\Animation\Animation
				Case ANIMATION_FLY,ANIMATION_GLIDE,ANIMATION_GLIDE2:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
				Case ANIMATION_KICK,ANIMATION_KICK2,ANIMATION_KICKAIR2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,3,p\Character,2)
				Case ANIMATION_PUNCH1,ANIMATION_PUNCHAIR:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandL,1.55,0.2,2,p\Character,1)
			End Select
		Case CHAR_SIL:
			Select p\Animation\Animation
				Case ANIMATION_THROW2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
			End Select
	 	Case CHAR_PRS:
			Select p\Animation\Animation
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.0,0.34,3,p\Character,2)
			End Select
	 	Case CHAR_ESP:
			Select p\Animation\Animation
				Case ANIMATION_PUNCH1:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_PUNCH2:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootL,1.55,0.2,2,p\Character,1)
			End Select
		Case CHAR_BEA:
			Select p\Animation\Animation
				Case ANIMATION_FLY:
					ParticleTemplate_Call(p\JetParticle1,PARTICLE_PLAYER_ROCKET,p\Objects\Jet1)
					ParticleTemplate_Call(p\JetParticle2,PARTICLE_PLAYER_ROCKET,p\Objects\Jet2)
			End Select
		Case CHAR_INF:
			Select p\Animation\Animation
				Case ANIMATION_KICK:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\FootR,1.55,0.2,2,p\Character,1)
				Case ANIMATION_THROW:
					ParticleTemplate_Call(p\Particle,PARTICLE_PLAYER_ATTACKTRAIL,p\Objects\HandR,1.55,0.2,2,p\Character,1)
			End Select
	End Select
	
End Function

Function DeformCharacter_FixUps(p.tPlayer)
	p\ScaleFactor#=0.125*GetCharScaleFactor#(p\RealCharacter)
	If p\No#=1 Then CAMERA_CONTROL_SIZEFACTOR#=5*p\ScaleFactor#
	
	p\DrownState=0
	If Menu\Members=1 Then p\Invisibility=0
End Function

Function DeformCharacter(p.tPlayer,dontstartoutlock=False)
	p\Flags\LongTrailCreated=0
	Player_FreeLongTrails(p,2)
	
	Select p\Character
		Case CHAR_EME: If p\CharacterMode=0 Then p\CharacterMode=CHAR_SON
		Case CHAR_GME: If p\CharacterMode=0 Then p\CharacterMode=CHAR_ESP
		Default: p\CharacterMode=0
	End Select
	
	
	
	If p\Objects\Mesh<>0 Then ShowEntity(p\Objects\Mesh)
	FreeEntity p\Objects\Mesh
	
	If Menu\Stage=0 Then
		p\Objects\Mesh=CopyEntity(MESHES(Mesh_Empty), Game\Stage\Root)
	Else
		If (Menu\Members>1 Or Game\CheaterChangedCharacter=0) And Player_IsPlayable(p) Then
			If (Game\SuperForm=0 Or (Not(Player_IsPlayable(p)))) Then
				Select p\RealCharacter
					Case Menu\Character[1]: p\Objects\Mesh=CopyEntity(Game\CharacterMesh[1], Game\Stage\Root)
					Case Menu\Character[2]: p\Objects\Mesh=CopyEntity(Game\CharacterMesh[2], Game\Stage\Root)
					Case Menu\Character[3]: p\Objects\Mesh=CopyEntity(Game\CharacterMesh[3], Game\Stage\Root)
				End Select
			ElseIf Game\SuperForm=1 Then
				Select p\RealCharacter
					Case Menu\Character[1]: p\Objects\Mesh=CopyEntity(Game\SuperCharacterMesh[1], Game\Stage\Root)
					Case Menu\Character[2]: p\Objects\Mesh=CopyEntity(Game\SuperCharacterMesh[2], Game\Stage\Root)
					Case Menu\Character[3]: p\Objects\Mesh=CopyEntity(Game\SuperCharacterMesh[3], Game\Stage\Root)
				End Select
			EndIf
		Else
			If Game\SuperForm=0 Or (Not(Player_IsPlayable(p))) Then
				LoadCharacterMesh(p\RealCharacter,0)
			ElseIf Game\SuperForm=1 Then
				LoadCharacterMesh(p\RealCharacter,0,CharHasSuperModel(Game\SuperForm,p\RealCharacter))
			EndIf
			p\Objects\Mesh=CopyEntity(CharacterMesh, Game\Stage\Root)
			DeleteCharacterMesh()
			For t.tTrail=Each tTrail : t\char=p\RealCharacter : Next
			For trail.tLongTrail=Each tLongTrail : trail\char=p\RealCharacter : Next
		EndIf
	EndIf
	
	
	DeformCharacter_GetTheBoneEntities(p)
	
	
	
	EntityShininess(p\Objects\Mesh, 0)
	
	
	;Set up grind affectors
    p\Objects\R_GrindAffector = CreateCylinder(8, 1, p\Objects\Mesh)
    p\Objects\L_GrindAffector = CreateCylinder(8, 1, p\Objects\Mesh)
    ScaleEntity(p\Objects\R_GrindAffector, 0.5, 0.5, 0.5)
    ScaleEntity(p\Objects\L_GrindAffector, 0.5, 0.5, 0.5)
    EntityType(p\Objects\R_GrindAffector,COLLISION_GRINDAFFECTOR)
    EntityType(p\Objects\L_GrindAffector,COLLISION_GRINDAFFECTOR)
	
	p\Action=ACTION_FALL
	If p\Motion\Ground Then p\Motion\Ground=False
	Select p\Character
		Case CHAR_CHA: p\Motion\Speed\y#=0.20*p\Physics\UNDERWATERTRIGGER#
		Case CHAR_VEC,CHAR_BIG: p\Motion\Speed\y#=0.35*p\Physics\UNDERWATERTRIGGER#
		Default: p\Motion\Speed\y#=0.30*p\Physics\UNDERWATERTRIGGER#
	End Select
	
	If dontstartoutlock=False And p\No#=1 Then
		Game\ControlLock=0.5*secs#
	EndIf
	
	
	
	If Menu\Members=1 Then
		If Player_IsPlayable(p) Then Menu\Character[1]=p\RealCharacter
		
		Player_LoadVoices(p)
	EndIf
	
	
	
	Animate(p\Objects\Mesh, 1, ((p\SpeedLength#+0.4531+(1/0.1)*0.1))/2.0, ANIMATION_SPIN, 1)
	
	DeformCharacter_FixUps(p)
	
	If Game\Shield>0 Then ScaleEntity p\Objects\Shield, 1+p\ScaleFactor#, 1+p\ScaleFactor#, 1+p\ScaleFactor#
	
	Game\SmartCameraRangeDontAffectTimer=5*secs#
End Function



Function CharHasSuperModel(superform,char)
	If superform=0 Then
		Return 0
	Else
		If IsCharMod(char) Then
			If superform=1 And CharModHasSuper(char) Then
				Return 1
			Else
				Return 0
			EndIf
		Else
			If Player_HasSuperModel(char) Then
				Return superform
			Else
				Return 0
			EndIf
		EndIf
	EndIf
End Function

Function ChangeCharacter(newcharacter)
	pp(1)\Psychokinesis=0
	If pp(1)\ObjPickUp=1 Then pp(1)\Action=ACTION_COMMON : pp(1)\ObjPickUp=0
	
	If Menu\Members>1 Then
		Player_ReassignMember(Game\NewLeader)
		Game\Leader=Game\NewLeader
		DeformCharacter_FixUps(pp(1))
	Else
		DeformCharacter_DeleteTheBoneEntities(pp(1))
		Player_DetermineChar(pp(1),newcharacter)
		DeformCharacter(pp(1),True)
		Game\Vehicle=0
		Player_SetRadius#(pp(1))
	EndIf
	
	For ppp.tPlayer = Each tPlayer
		If ppp\No#>0 Then ppp\JustDeformedCharacterTimer=0.5*secs#
	Next
	
	Player_PlayTurnVoice(pp(1))
	Delay(1)
End Function

Function DeformCharacter_GetTheBoneEntities(p.tPlayer)
	Select p\Character
		Case CHAR_SHA,CHAR_STO,CHAR_SHN::
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetBL")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetBR")
			p\Objects\Jet3=FindChild(p\Objects\Mesh, "jetFL")
			p\Objects\Jet4=FindChild(p\Objects\Mesh, "jetFR")
			If p\Character=CHAR_SHN Then
				p\Objects\Extra=FindChild(p\Objects\Mesh, "gatling1")
				p\Objects\Extra2=FindChild(p\Objects\Mesh, "muzzleR")
				p\Objects\Extra3=FindChild(p\Objects\Mesh, "muzzleE")
			EndIf
		Case CHAR_OME
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetL")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetR")
			p\Objects\Extra=FindChild(p\Objects\Mesh, "gunL")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "gunR")
		Case CHAR_EGR:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetL")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetR")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "backpack")
		Case CHAR_VEC:
			p\Objects\Gum=FindChild(p\Objects\Mesh, "gum")
			p\Objects\Extra=FindChild(p\Objects\Mesh, "tail1")
		Case CHAR_MET,CHAR_MT3:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet")
		Case CHAR_BIG,CHAR_RAY:
			p\Objects\Extra=FindChild(p\Objects\Mesh, "tail1")
		Case CHAR_TAI,CHAR_TDL:
			p\Objects\Extra=FindChild(p\Objects\Mesh, "tailroot")
		Case CHAR_HBO:
			p\Objects\Extra=FindChild(p\Objects\Mesh, "spine_b")
		Case CHAR_SHD:
			p\Objects\Extra=FindChild(p\Objects\Mesh, "head2")
		Case CHAR_GAM:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetBL")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetBR")
			p\Objects\Jet3=FindChild(p\Objects\Mesh, "jetFL")
			p\Objects\Jet4=FindChild(p\Objects\Mesh, "jetFR")
			p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "booster")
		Case CHAR_BET:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetBL")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetBR")
			p\Objects\Jet3=FindChild(p\Objects\Mesh, "jetFL")
			p\Objects\Jet4=FindChild(p\Objects\Mesh, "jetFR")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "booster")
		Case CHAR_EME:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jetL")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jetR")
			p\Objects\Extra=FindChild(p\Objects\Mesh, "lidU")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "lidD")
		Case CHAR_EGG:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet1L")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jet1R")
			p\Objects\Jet3=FindChild(p\Objects\Mesh, "jet2L")
			p\Objects\Jet4=FindChild(p\Objects\Mesh, "jet2R")
			p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "handX")
		Case CHAR_CHW:
			p\Objects\Jet1=FindChild(p\Objects\Mesh, "jet1L")
			p\Objects\Jet2=FindChild(p\Objects\Mesh, "jet1R")
			p\Objects\Jet3=FindChild(p\Objects\Mesh, "jet2L")
			p\Objects\Jet4=FindChild(p\Objects\Mesh, "jet2R")
			p\Objects\Extra=FindChild(p\Objects\Mesh, "scanner")
			p\Objects\Extra2=FindChild(p\Objects\Mesh, "office")
		Case CHAR_AMY:
			p\Objects\Extra=FindChild(p\Objects\Mesh, "hammer2")
	End Select
	p\Objects\Head=FindChild(p\Objects\Mesh, "head")
	Select p\Character
		Case CHAR_EGG:
			p\Objects\HandR=FindChild(p\Objects\Mesh, "handR_e")
			p\Objects\HandL=FindChild(p\Objects\Mesh, "handL_e")
		Default:
			p\Objects\HandR=FindChild(p\Objects\Mesh, "handR")
			p\Objects\HandL=FindChild(p\Objects\Mesh, "handL")
	End Select
	p\Objects\ArmR=FindChild(p\Objects\Mesh, "armR")
	p\Objects\ArmL=FindChild(p\Objects\Mesh, "armL")
	p\Objects\ToeR=FindChild(p\Objects\Mesh, "toeR")
	p\Objects\ToeL=FindChild(p\Objects\Mesh, "toeL")
	p\Objects\FootR=FindChild(p\Objects\Mesh, "footR")
	p\Objects\FootL=FindChild(p\Objects\Mesh, "footL")
	Select p\RealCharacter
		Case CHAR_GAM,CHAR_BET:
			p\Objects\LegR=FindChild(p\Objects\Mesh, "leg1R")
			p\Objects\LegL=FindChild(p\Objects\Mesh, "leg1L")
		Default:
			p\Objects\LegR=FindChild(p\Objects\Mesh, "legR")
			p\Objects\LegL=FindChild(p\Objects\Mesh, "legL")
	End Select
	p\Objects\HipR=FindChild(p\Objects\Mesh, "hipR")
	p\Objects\HipL=FindChild(p\Objects\Mesh, "hipL")
	p\Objects\Spine=FindChild(p\Objects\Mesh, "spine")
	p\Objects\Hips=FindChild(p\Objects\Mesh, "hips")
End Function

Function DeformCharacter_DeleteTheBoneEntities(p.tPlayer)
	Select p\Character
		
				Case CHAR_SHA,CHAR_STO,CHAR_SHN:
					FreeEntity p\Objects\Jet1
					FreeEntity p\Objects\Jet2
					FreeEntity p\Objects\Jet3
					FreeEntity p\Objects\Jet4
					If p\Character=CHAR_SHN
						FreeEntity p\Objects\Extra
						FreeEntity p\Objects\Extra2
						FreeEntity p\Objects\Extra3
					EndIf
		Case CHAR_OME,CHAR_MKN,CHAR_GME,CHAR_BEA:
			FreeEntity p\Objects\Jet1
			FreeEntity p\Objects\Jet2
		Case CHAR_EGR:
			FreeEntity p\Objects\Jet1
			FreeEntity p\Objects\Jet2
			FreeEntity p\Objects\Extra2
		Case CHAR_VEC:
			FreeEntity p\Objects\Gum
			FreeEntity p\Objects\Extra
		Case CHAR_MET,CHAR_MT3:
			FreeEntity p\Objects\Jet1
		Case CHAR_BIG,CHAR_HBO,CHAR_SHD,CHAR_AMY,CHAR_TAI,CHAR_TDL,CHAR_RAY:
			FreeEntity p\Objects\Extra
		Case CHAR_GAM,CHAR_EGG,CHAR_CHW:
			FreeEntity p\Objects\Jet1
			FreeEntity p\Objects\Jet2
			FreeEntity p\Objects\Jet3
			FreeEntity p\Objects\Jet4
			FreeEntity p\Objects\Extra
			FreeEntity p\Objects\Extra2
		Case CHAR_BET:
			FreeEntity p\Objects\Jet1
			FreeEntity p\Objects\Jet2
			FreeEntity p\Objects\Jet3
			FreeEntity p\Objects\Jet4
			FreeEntity p\Objects\Extra2
		Case CHAR_EME:
			FreeEntity p\Objects\Jet1
			FreeEntity p\Objects\Jet2
			FreeEntity p\Objects\Extra
			FreeEntity p\Objects\Extra2
	End Select
	FreeEntity p\Objects\Head
	FreeEntity p\Objects\HandR
	FreeEntity p\Objects\HandL
	FreeEntity p\Objects\ArmR
	FreeEntity p\Objects\ArmL
	FreeEntity p\Objects\ToeR
	FreeEntity p\Objects\ToeL
	FreeEntity p\Objects\FootR
	FreeEntity p\Objects\FootL
	FreeEntity p\Objects\LegR
	FreeEntity p\Objects\LegL
	FreeEntity p\Objects\HipR
	FreeEntity p\Objects\HipL
	FreeEntity p\Objects\Spine
	FreeEntity p\Objects\Hips
End Function
;============================================================================================
;============================================================================================
;============================================================================================

Type tTrail
	Field id,num,brush,surface,Mesh
	Field tdx#[maxVerts], tdy#[maxVerts], tdz#[maxVerts]
	Field update%,alpha#
	Field point1, point2
	Field char
End Type
Type tLongTrail
	Field id,num,brush,surface,Mesh
	Field tdx#[maxVerts], tdy#[maxVerts], tdz#[maxVerts]
	Field update%,alpha#
	Field point1, point2
	Field char
End Type

Const trail_update#=0.3
Const maxVerts=100
Const longtrail_update#=1.0

;============================================================================================
;============================================================================================

Function Player_UpdateTrails()

	Local x#,y#,z#

	;loop through all trails
	For t.tTrail = Each tTrail

		; Move the trail pieces along.
		t\update=t\update+1 : If t\update=trail_update# Then t\update=0

		;update				
		If t\update=0
			For i=2 To CountVertices(t\surface)-1
				t\tdx[i] = (VertexX(t\surface,i-2) - VertexX(t\surface,i))/trail_update#
				t\tdy[i] = (VertexY(t\surface,i-2) - VertexY(t\surface,i))/trail_update#
				t\tdz[i] = (VertexZ(t\surface,i-2) - VertexZ(t\surface,i))/trail_update#
				t\alpha=1.0-Float(i)/30.0
				If t\alpha<0 Then t\alpha=0
				VertexColor t\surface,i,255,255,255,t\alpha
			Next
		End If

		For i=2 To CountVertices(t\surface)-1
			VertexCoords(t\surface,i,VertexX(t\surface,i)+t\tdx[i],VertexY(t\surface,i)+t\tdy[i],VertexZ(t\surface,i)+t\tdz[i])
		Next
	
		;position the first two verts at the back of the ship
		VertexCoords(t\surface,0,EntityX(t\point1,1),EntityY(t\point1,1),EntityZ(t\point1,1))
		VertexCoords(t\surface,1,EntityX(t\point2,1),EntityY(t\point2,1),EntityZ(t\point2,1))
	
	Next
			
End Function

Function Player_CreateTrail(p.tPlayer,point1,point2,polys=20,startalpha#=1.0,num#=1)

	Local x#,y#,z#

	t.tTrail=New tTrail
	t\char = p\RealCharacter

	;create mesh and set properties
	t\Mesh = CreateMesh()
	t\id = 1
	t\brush = CreateBrush()
	BrushBlend t\brush,3
	BrushFX t\brush,2+16
	t\surface = CreateSurface(t\mesh,t\brush)
	t\alpha=1.0

	;check there are two trail strat points
	If point1=False Then RuntimeError "must specify 'point1' for one side of the trail" Else t\point1=point1
	If point2=False Then RuntimeError "must specify 'point2' for one side of the trail" Else t\point2=point2

	;mid pount between two trial objects
	x=(EntityX(t\point1,1)+EntityX(t\point2,1))/2.0
	y=(EntityY(t\point1,1)+EntityY(t\point2,1))/2.0
	z=(EntityZ(t\point1,1)+EntityZ(t\point2,1))/2.0

	;create polygons
	For i=0 To polys
		AddVertex t\surface,x,y,z,Float(i)/Float(polys),1,0
		AddVertex t\surface,x,y,z,Float(i)/Float(polys),0,0
		VertexNormal t\surface,i,0,-1,0
		VertexNormal t\surface,i+1,0,-1,0
		
		If i>0
			AddTriangle t\surface,i*2,i*2-1,i*2-2
			AddTriangle t\surface,i*2,i*2+1,i*2-1
		End If
	Next
	
End Function

Function Player_FreeTrails(p.tPlayer,id)

	For t.tTrail=Each tTrail
		If t\char=p\RealCharacter And t\id=id Then
			FreeEntity t\mesh
			Delete t
		EndIf
	Next
	
	Return True
	
End Function

;==================================================================================================================================

Function Player_UpdateLongTrail()
	
	Local x#,y#,z#

	;loop through all trails
	For trail.tLongTrail = Each tLongTrail 

		; Move the trail pieces along.
		trail\update=trail\update+1 : If trail\update=longtrail_update# Then trail\update=0

		;update				
		If trail\update=0
			For i=2 To CountVertices(trail\surface)-1
				trail\tdx[i] = (VertexX(trail\surface,i-2) - VertexX(trail\surface,i))/longtrail_update#
				trail\tdy[i] = (VertexY(trail\surface,i-2) - VertexY(trail\surface,i))/longtrail_update#
				trail\tdz[i] = (VertexZ(trail\surface,i-2) - VertexZ(trail\surface,i))/longtrail_update#
				trail\alpha=1.0-Float(i)/30.0
				If trail\alpha<0 Then trail\alpha=0
				Select Game\Shield
					Case OBJTYPE_TSHIELD
						VertexColor(trail\surface,i,255,255,216,trail\alpha)
					Case OBJTYPE_ESHIELD
						VertexColor(trail\surface,i,255, 196, 32,trail\alpha)
					Case OBJTYPE_BSHIELD
						VertexColor(trail\surface,i, 128, 216, 216,trail\alpha)
					Case OBJTYPE_FSHIELD
						VertexColor(trail\surface,i, 255, 96, 0,trail\alpha)
					Default
						If Game\SuperForm=1 
							VertexColor(trail\surface,i,Interface_SCircle_R[InterfaceChar(trail\char)], Interface_SCircle_G[InterfaceChar(trail\char)], Interface_SCircle_B[InterfaceChar(trail\char)],trail\alpha)
						Else 
							VertexColor(trail\surface,i,Interface_Circle_R[InterfaceChar(trail\char)], Interface_Circle_G[InterfaceChar(trail\char)], Interface_Circle_B[InterfaceChar(trail\char)],trail\alpha)
						EndIf 	   
				End Select
			Next
		End If

		For i=2 To CountVertices(trail\surface)-1
			VertexCoords(trail\surface,i,VertexX(trail\surface,i)+trail\tdx[i],VertexY(trail\surface,i)+trail\tdy[i],VertexZ(trail\surface,i)+trail\tdz[i])
		Next
	
		;position the first two verts at the back of the ship
		VertexCoords(trail\surface,0,EntityX(trail\point1,1),EntityY(trail\point1,1),EntityZ(trail\point1,1))
		VertexCoords(trail\surface,1,EntityX(trail\point2,1),EntityY(trail\point2,1),EntityZ(trail\point2,1))
	
	Next
			
End Function


Function Player_CreateLongTrail(p.tPlayer,point1,point2,polys=20,startalpha#=1.0,num#=1, something=0)

	Local x#,y#,z#

	trail.tLongTrail=New tLongTrail
	trail\char = p\RealCharacter

	;create mesh and set properties
	trail\Mesh = CreateMesh()
	trail\id = 2
	trail\brush = CreateBrush()
	BrushBlend trail\brush,3
	BrushFX trail\brush,2+16
	trail\surface = CreateSurface(trail\mesh,trail\brush)
	trail\alpha=1.0

	EntityTexture(trail\mesh,Texture_Trail)
	EntityFX(trail\mesh,1)
	
	;check there are two trail strat points
	If point1=False Then RuntimeError "must specify 'point1' for one side of the trail" Else trail\point1=point1
	If point2=False Then RuntimeError "must specify 'point2' for one side of the trail" Else trail\point2=point2
	
	;mid pount between two trial objects
	x=(EntityX(trail\point1,1)+EntityX(trail\point2,1))/2.0
	y=(EntityY(trail\point1,1)+EntityY(trail\point2,1))/2.0
	z=(EntityZ(trail\point1,1)+EntityZ(trail\point2,1))/2.0

	;create polygons
	For i=0 To polys
		AddVertex trail\surface,x,y,z,Float(i)/Float(polys),1,0
		AddVertex trail\surface,x,y,z,Float(i)/Float(polys),0,0
		VertexNormal trail\surface,i,0,-1,0
		VertexNormal trail\surface,i+1,0,-1,0
		
		If i>0
			AddTriangle trail\surface,i*2,i*2-1,i*2-2
			AddTriangle trail\surface,i*2,i*2+1,i*2-1
		End If
	Next
	
End Function

Function Player_FreeLongTrails(p.tPlayer,id)

	For trail.tLongTrail=Each tLongTrail
		If trail\char=p\RealCharacter And trail\id=id Then
			FreeEntity trail\mesh
			Delete trail
		EndIf
	Next
	
	Return True
	
End Function


Function Player_SetLongTrail(p.tPlayer)

	PositionEntity(p\Objects\TrailPivot, EntityX#(p\Objects\Mesh),EntityY#(p\Objects\Mesh),EntityZ#(p\Objects\Mesh),True)
	RotateEntity(p\Objects\TrailPivot,EntityPitch(p\Objects\Entity),p\Animation\Direction#,EntityRoll(p\Objects\Entity))
	PointEntity(p\Objects\TrailPivot, p\Objects\Entity)
	ScaleEntity(p\Objects\TrailPivot,1+p\ScaleFactor#,1+p\ScaleFactor#,1+p\ScaleFactor#)

End Function

;===============================================================================================================================
;===============================================================================================================================

Function Player_SpawnTrail(p.tPlayer,i)

	e1=CreatePivot(p\Objects\PPivot[i])
	e2=CreatePivot(p\Objects\PPivot[i])
	e3=CreatePivot(p\Objects\PPivot[i])
	e4=CreatePivot(p\Objects\PPivot[i])

	Player_CreateTrail(p,e1,e2,20,1.0,1)
	Player_CreateTrail(p,e2,e3,20,1.0,1)
	Player_CreateTrail(p,e3,e4,20,1.0,1)
	Player_CreateTrail(p,e4,e1,20,1.0,1)

	EntityParent(e1,p\Objects\PPivot[i],0)
	EntityParent(e2,p\Objects\PPivot[i],0)
	EntityParent(e3,p\Objects\PPivot[i],0)
	EntityParent(e4,p\Objects\PPivot[i],0)

	MoveEntity e1,+0.01,+0.01,0
	MoveEntity e2,+0.01,-0.01,0
	MoveEntity e3,-0.01,-0.01,0
	MoveEntity e4,-0.01,+0.01,0

End Function


Function Player_LongTrail(p.tPlayer, Tp=1)

	e1=CreatePivot(p\Objects\TrailPivot)
	e2=CreatePivot(p\Objects\TrailPivot)
	e3=CreatePivot(p\Objects\TrailPivot)
	e4=CreatePivot(p\Objects\TrailPivot)
	e5=CreatePivot(p\Objects\TrailPivot)
	e6=CreatePivot(p\Objects\TrailPivot)
	e7=CreatePivot(p\Objects\TrailPivot)
	e8=CreatePivot(p\Objects\TrailPivot)
	e9=CreatePivot(p\Objects\TrailPivot)
	e10=CreatePivot(p\Objects\TrailPivot)
	e11=CreatePivot(p\Objects\TrailPivot)
	e12=CreatePivot(p\Objects\TrailPivot)
	e13=CreatePivot(p\Objects\TrailPivot)
	e14=CreatePivot(p\Objects\TrailPivot)
	e15=CreatePivot(p\Objects\TrailPivot)
	e16=CreatePivot(p\Objects\TrailPivot)
	e17=CreatePivot(p\Objects\TrailPivot)
	e18=CreatePivot(p\Objects\TrailPivot)
	e19=CreatePivot(p\Objects\TrailPivot)
	e20=CreatePivot(p\Objects\TrailPivot)

	Player_CreateLongTrail(p, e1,e2,20,1.0,1)
	Player_CreateLongTrail(p, e2,e3,20,1.0,1)
	Player_CreateLongTrail(p, e3,e4,20,1.0,1)
	Player_CreateLongTrail(p, e4,e5,20,1.0,1)
	Player_CreateLongTrail(p, e5,e6,20,1.0,1)
	Player_CreateLongTrail(p, e6,e7,20,1.0,1)
	Player_CreateLongTrail(p, e7,e8,20,1.0,1)
	Player_CreateLongTrail(p, e8,e9,20,1.0,1)
	Player_CreateLongTrail(p, e9,e10,20,1.0,1)
	Player_CreateLongTrail(p, e10,e11,20,1.0,1)
	Player_CreateLongTrail(p, e11,e12,20,1.0,1)
	Player_CreateLongTrail(p, e12,e13,20,1.0,1)
	Player_CreateLongTrail(p, e13,e14,20,1.0,1)
	Player_CreateLongTrail(p, e14,e15,20,1.0,1)
	Player_CreateLongTrail(p, e15,e16,20,1.0,1)
	Player_CreateLongTrail(p, e16,e17,20,1.0,1)
	Player_CreateLongTrail(p, e17,e18,20,1.0,1)
	Player_CreateLongTrail(p, e18,e19,20,1.0,1)
	Player_CreateLongTrail(p, e19,e20,20,1.0,1)
	Player_CreateLongTrail(p, e20,e1,20,1.0,1)

	MoveEntity e1,-3,0,0
	MoveEntity e2,-2.9,0.9,0
	MoveEntity e3,-2.4,1.8,0
	MoveEntity e4,-1.8,2.4,0
	MoveEntity e5,-0.9,2.9,0
	MoveEntity e6,0,3,0
	MoveEntity e7,0.9,2.9,0
	MoveEntity e8,1.8,2.4,0
	MoveEntity e9,2.4,1.8,0
	MoveEntity e10,2.9,0.9,0
	MoveEntity e11,3,0,0
	MoveEntity e12,2.9,-0.9,0
	MoveEntity e13,2.4,-1.8,0
	MoveEntity e14,1.8,-2.4,0
	MoveEntity e15,0.9,-2.9,0
	MoveEntity e16,0,-3,0
	MoveEntity e17,-0.9,-2.9,0
	MoveEntity e18,-1.8,-2.4,0
	MoveEntity e19,-2.4,-1.8,0
	MoveEntity e20,-2.9,-0.9,0

End Function

;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================


Type tRazer
Field Alpha#
Field Timer
Field Pivot
Field Mesh
Field Threshold
Field MoveTypeX
Field MoveTypeY
Field MoveTypeZ
Field Mode
Field Owner.tPlayer
Field BelongsToPlayer
End Type

Function Player_CreateRazer.tRazer(p.tPlayer,entity,alphastart#,mode,scalex#=1,scaley#=1,scalez#=1,timer#=0,belongstoplayer=True)
	Select mode
		Case 4,5,6,7:
		Case 8:
			If p\RazerSpawningTimer2>0 Then Return Else p\RazerSpawningTimer2=0.025*secs#
		Default:
			If p\RazerSpawningTimer>0 Then Return Else p\RazerSpawningTimer=0.025*secs#
	End Select
	
	rz.tRazer = New tRazer
	rz\Threshold = entity
	x# = EntityX(rz\Threshold)
	y# = EntityY(rz\Threshold)
	z# = EntityZ(rz\Threshold)
	rz\Owner = p
	rz\BelongsToPlayer = belongstoplayer
	rz\Pivot = CreatePivot(Game\Stage\Root)
	rz\Timer=timer#
	PositionEntity(rz\Pivot, x#, y#, z#)
	RotateEntity(rz\Pivot, EntityPitch(entity), EntityYaw(entity), EntityRoll(entity))
	Select mode
		Case 1: rz\Mesh = CopyEntity(MESHES(Mesh_Razer), rz\Pivot) : EntityBlend(rz\Mesh, 3)
		Case 2:	rz\Mesh = CopyEntity(MESHES(Mesh_BlackShield1), rz\Pivot)
		Case 3:	rz\Mesh = CopyEntity(MESHES(Mesh_BlackShield2), rz\Pivot)
		Case 4: rz\Mesh = CopyEntity(MESHES(Mesh_Ice), rz\Pivot) : EntityBlend(rz\Mesh, 1)
			ScaleEntity(rz\Mesh,scalex#,scaley#,scalez#) : EntityType(rz\Mesh,COLLISION_WORLD_POLYGON_ICE)
		Case 5,6,7: rz\Mesh = CopyEntity(MESHES(Mesh_Cube), rz\Pivot) : Animate(rz\Mesh,1,0.5,1,10)
			Select mode
				Case 6: MoveEntity rz\Pivot, Rand(-2,2)/2.0, Rand(-2,2)/2.0, Rand(-2,2)/2.0
				Default: MoveEntity rz\Pivot, Rand(-5,5)/2.0, 1+Rand(-5,5)/2.0, Rand(-5,5)/2.0
			End Select
		Case 8: rz\Mesh = CopyEntity(MESHES(Mesh_SuperShield), rz\Pivot) : EntityBlend(rz\Mesh, 3)
			If Player_SuperAuraShouldFire(p\Character) Then
				EntityColor(rz\Mesh,250,110,110)
			Else
				EntityColor(rz\Mesh,249,228,51)
			EndIf
			ScaleEntity rz\Mesh, 1+p\ScaleFactor#, 1+p\ScaleFactor#, 1+p\ScaleFactor#
		Case 9: rz\Mesh = CopyEntity(MESHES(Mesh_CubePlatform), rz\Pivot) : EntityBlend(rz\Mesh, 1)
			ScaleEntity(rz\Mesh,scalex#,scaley#,scalez#) : EntityType(rz\Mesh,COLLISION_WORLD_POLYGON)
			rz\Timer=5.5*secs#
	End Select
	rz\Mode = mode
	rz\Alpha# = alphastart#
	EntityAlpha(rz\Mesh, rz\Alpha#)
	rz\MoveTypeX=Rand(-1,1)
	rz\MoveTypeY=Rand(-1,1)
	rz\MoveTypeZ=Rand(-1,1)
	
	Return rz
	
End Function

Function Player_SuperAuraShouldFire(char)
	Select char
		Case CHAR_BLA,CHAR_KNU,CHAR_OME,CHAR_GAM,CHAR_MKN,CHAR_EGG,CHAR_EGR,CHAR_INF:
			Return True
		Default:
			Return False
	End Select
End Function

Function Player_UpdateRazer(rz.tRazer, d.tDeltaTime)
	
	If rz\Timer>0 Then rz\Timer=rz\Timer-timervalue#
	
	Select rz\Mode
		Case 1,8:
			rz\Alpha# = rz\Alpha# - 0.0825*d\Delta
			EntityAlpha(rz\Mesh, rz\Alpha#)
			MoveEntity rz\Pivot, (0.15+0.1*rz\Owner\SpeedLength#)*d\Delta*rz\MoveTypeX, (0.15+0.1*rz\Owner\SpeedLength#)*d\Delta*rz\MoveTypeY, (0.15+0.1*rz\Owner\SpeedLength#)*d\Delta*rz\MoveTypeZ
		Case 2,3:
			PositionEntity(rz\Pivot,EntityX(rz\Threshold,1),EntityY(rz\Threshold,1),EntityZ(rz\Threshold,1),1)
		Case 4,9,10:
			PositionEntity(rz\Pivot,EntityX(rz\Threshold,1),EntityY(rz\Threshold,1)-2.2,EntityZ(rz\Threshold,1),1)
			RotateEntity(rz\Pivot,0,EntityYaw(rz\Threshold,1),0,1)
			If rz\BelongsToPlayer Then
				If rz\Owner\WasGrabbedTimer>0 Then rz\Owner\Action=ACTION_FREEZE
			EndIf
		Case 5,6,7:
			rz\Alpha# = rz\Alpha# - 0.022*d\Delta
			EntityAlpha(rz\Mesh, rz\Alpha#)
			Select rz\Mode
				Case 5,7:
					MoveEntity rz\Pivot, 0, rz\Owner\Motion\Speed\y#*d\Delta, rz\Owner\SpeedLength#*d\Delta
			End Select
			Select rz\Mode
				Case 7: MoveEntity rz\Pivot, 0.42*d\Delta*rz\MoveTypeX, 0.42*d\Delta*rz\MoveTypeY, 0.42*d\Delta*rz\MoveTypeZ
				Default: MoveEntity rz\Pivot, 0.02*d\Delta*rz\MoveTypeX, 0.02*d\Delta*rz\MoveTypeY, 0.02*d\Delta*rz\MoveTypeZ
			End Select
	End Select
	
	Select rz\Mode
		Case 1,8: TurnEntity(rz\Mesh, 0, 50*d\Delta, 0)
		Case 2,3: TurnEntity(rz\Mesh, 0, 2*d\Delta, 0)
	End Select
	
	i=False
	Select rz\Mode
		Case 1,5,6,7,8: If rz\Alpha# < 0 Then i=True
		Case 2,3: If rz\Mode<>1 And (Not(rz\Owner\Action=ACTION_SWIPE)) Then i=True
		Case 4: If (Not(rz\Timer>0)) Then i=True
		Case 9,10: If (Not(rz\Timer>0)) Then i=True
	End Select
	
	If i=True Then 
		Select rz\Mode
			Case 9
				
				
		End Select
		FreeEntity(rz\Mesh)
		FreeEntity(rz\Pivot)
		rz\Alpha# = 0.0
		Delete rz
	EndIf
	
End Function

Function Player_RubyCubes(p.tPlayer,alpha#=0.65,amount=5,mode=5)
	If p\RubyCubesTimer>0 Then
		p\RubyCubesTimer=p\RubyCubesTimer-timervalue#
	Else
		p\RubyCubesTimer=0.1*secs#
		For i=1 To amount
			Player_CreateRazer.tRazer(p,p\Objects\Mesh,alpha#,mode)
		Next
	EndIf
End Function

Function Bomb_RubyCubes(p.tPlayer,b.tBomb)
	If b\RubyCubesTimer>0 Then
		b\RubyCubesTimer=b\RubyCubesTimer-timervalue#
	Else
		b\RubyCubesTimer=0.025*secs#
		For i=1 To 5
			Player_CreateRazer.tRazer(p,b\Pivot,0.8,6)
		Next
	EndIf
End Function

;============================================================================================
;============================================================================================
Type tAfterImage
    Field Mesh
    Field Entity
    Field Timer,Alpha#,Scale#
    Field AlphaAllowed,ScaleAllowed
End Type

Function Create_AfterImage.tAfterImage(mesh,entity,timer#=500,r,g,b,blended=0,flipentity=0,scale#=1,animation=0,fixrotate=False, canscale=True, texture=0)

af.tAfterImage = New tAfterImage

;t\AlphaAllowed=alphaallowed
af\ScaleAllowed=canscale

af\Mesh=CopyEntity(mesh);CopyEntity(mesh)
EntityFX af\Mesh,1
Animate(af\Mesh,1,1,animation,1)
If texture > 0 Then EntityTexture(af\Mesh, texture)
If blended=1 Then EntityBlend(af\Mesh, 3)
EntityShininess(af\Mesh,0)
If flipentity=1 Then FlipMesh(af\Mesh)
af\Timer=MilliSecs()+timer#
af\Scale#=scale# : ScaleEntity af\Mesh,af\Scale,af\Scale,af\Scale
af\Alpha#=0.7 : EntityAlpha af\Mesh,af\Alpha#
PositionEntity af\Mesh,EntityX(entity),EntityY(entity),EntityZ(entity)
If fixrotate=False Then
RotateEntity af\Mesh,0,EntityYaw(entity)-180,0
Else
RotateEntity af\Mesh,0,EntityYaw(entity),0
EndIf
EntityColor(af\Mesh,r,g,b)
Return af

End Function

Function Update_AfterImage(af.tAfterImage,p.tPlayer,d.tDeltaTime)

RotateEntity af\Mesh,0,EntityYaw(af\Mesh),0
;MoveEntity t\Mesh,0,0,1

;If af\AlphaAllowed=True
af\Alpha#=af\Alpha#-0.075*d\Delta
EntityAlpha af\Mesh,af\Alpha#
;EndIf

If af\ScaleAllowed Then
af\Scale#=af\Scale#-0.075*d\Delta
ScaleEntity af\Mesh,af\Scale,af\Scale,af\Scale
EndIf

If af\Alpha#<0 Then af\Alpha#=0
If af\Scale#<0 Then af\Scale#=0

If af\Scale#=0 Or af\Alpha#=0 Then 
FreeEntity af\Mesh
Delete af
Return
EndIf

End Function
; =========================================================================================================
; =========================================================================================================

Type tSpark
    Field Entity
    Field Pivot
    Field XR#,YR#,ZR#
    Field alpha#,scale#
    Field Mode
    Field Parent 
    Field Timer
    Field No#
    Field Attached 
    Field Height#
End Type

Function Create_Spark.tSpark(p.tPlayer, entity, mesh, camera, yaw#, mode#=0, no#=0, isattached%=False,height#=-1, lock=True)

	sp.tSpark = New tSpark

	;create mesh and set properties
	sp\Pivot = CreatePivot()
	sp\Entity = CopyMesh(mesh)
	sp\alpha#=1.0 : sp\scale#=1.0
	EntityFX sp\Entity ,1+16	
	EntityBlend sp\Entity, 3
	
	sp\Attached = isattached%
	sp\Height# = height#

	PositionEntity sp\Entity,EntityX(entity, lock),EntityY(entity, lock)+height#,EntityZ(entity, lock), lock
	PositionEntity sp\Pivot,EntityX(entity),EntityY(entity)+height#,EntityZ(entity)
	
	PointEntity sp\Pivot,camera

	sp\Mode = mode#

	sp\Parent = entity

	Select sp\Mode#
		Case 0:
			RotateEntity sp\Entity, 0, yaw#+90, EntityRoll#(entity)
			sp\XR# = Rnd(0,180)
			sp\YR# = Rnd(-30,30)
			sp\ZR# = Rnd(0,30)
		Case 1:
			RotateEntity sp\Entity, 0, yaw#+90, EntityRoll#(p\Objects\Mesh)
			sp\XR# = 0
			sp\YR# = Rnd(-15,15)
			sp\ZR# = Rnd(0,15)
		Case 2:
			RotateEntity sp\Entity, 0, yaw#+90, EntityRoll#(entity)
			sp\XR# = Rnd(0,180)
			sp\YR# = Rnd(-30,30)
			sp\ZR# = Rnd(0,30)	
	End Select

	sp\timer = MilliSecs()+220

	TurnEntity sp\Entity, sp\XR#,sp\YR#,sp\ZR#

	sp\No#=no#

	Return sp

End Function

Function Update_Spark(sp.tSpark, p.tPlayer, d.tDeltaTime)
	
	If sp\Attached = False Then
		If sp\Timer > MilliSecs() Then
			PositionEntity sp\Entity, EntityX(sp\Parent,True), EntityY(sp\Parent,True)+sp\Height#, EntityZ(sp\Parent,True), 1
		EndIf
	Else
		PositionEntity sp\Entity, EntityX(sp\Parent,True), EntityY(sp\Parent,True)+sp\Height#, EntityZ(sp\Parent,True), 1
	EndIf

	EntityAlpha sp\Entity, sp\alpha#
	ScaleEntity sp\Entity, sp\scale#*0.7,1*0.7,1*0.7
	
	If sp\Mode = 0 Then
		If sp\No#=p\No# Then EntityColor(sp\Entity,Interface_Circle_R[InterfaceChar(p\RealCharacter)], Interface_Circle_G[InterfaceChar(p\RealCharacter)], Interface_Circle_B[InterfaceChar(p\RealCharacter)])
	ElseIf sp\Mode=2 Then
		AlignToVector(sp\Entity, p\Animation\Align\x#, p\Animation\Align\y#, p\Animation\Align\z#, 2)
	Else
		EntityColor(sp\Entity,255, 203, 061)
		AlignToVector(sp\Entity, p\Animation\Align\x#, p\Animation\Align\y#, p\Animation\Align\z#, 2)
	EndIf

	sp\alpha# = sp\alpha# - 0.1*d\Delta
	sp\scale# = sp\scale# + 0.8*d\Delta

	If sp\alpha# < 0 Then
	FreeEntity sp\Entity
	FreeEntity sp\Pivot
	Delete sp
	Return		
	End If
		
End Function

;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================
;============================================================================================

	Global Interface_Emerald_R[7]
	Global Interface_Emerald_G[7]
	Global Interface_Emerald_B[7]
	Interface_Emerald_R[1] = 255 : Interface_Emerald_G[1] = 000 : Interface_Emerald_B[1] = 000
	Interface_Emerald_R[2] = 000 : Interface_Emerald_G[2] = 000 : Interface_Emerald_B[2] = 255
	Interface_Emerald_R[3] = 255 : Interface_Emerald_G[3] = 249 : Interface_Emerald_B[3] = 000
	Interface_Emerald_R[4] = 000 : Interface_Emerald_G[4] = 255 : Interface_Emerald_B[4] = 000
	Interface_Emerald_R[5] = 255 : Interface_Emerald_G[5] = 255 : Interface_Emerald_B[5] = 255
	Interface_Emerald_R[6] = 000 : Interface_Emerald_G[6] = 255 : Interface_Emerald_B[6] = 226
	Interface_Emerald_R[7] = 186 : Interface_Emerald_G[7] = 000 : Interface_Emerald_B[7] = 255

Type tEmerald
	Field Mesh[7]
	Field Pivot
	Field PosMultiplier#
	Field PosMultiplier_Actual#
	Field PosMultiplier_NegativeActual#
End Type

Function EntityColorEmerald(entity,emerald,forceoffset#=0)
	If forceoffset#=0 Then offset# = 100*Game\SuperForm Else offset# = 100*forceoffset#
	EntityColor(entity,Min#(255,Interface_Emerald_R[emerald]+offset#),Min#(255,Interface_Emerald_G[emerald]+offset#),Min#(255,Interface_Emerald_B[emerald]+offset#))
End Function

Function Create_Emerald.tEmerald(p.tPlayer)

	ee.tEmerald=New tEmerald

	ee\Pivot=CreatePivot()

	PositionEntity(ee\Pivot,p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#)

	For i=1 To 7
		Select p\Character
			Case CHAR_BLA,CHAR_MAR,CHAR_TIA: ee\Mesh[i]=CopyEntity(MESHES(Mesh_Sol), ee\Pivot)
			Default: ee\Mesh[i]=CopyEntity(MESHES(Mesh_Emerald), ee\Pivot)
		End Select
		EntityColorEmerald(ee\Mesh[i],i)
	Next

	ee\PosMultiplier# = -20 ;-50
	ee\PosMultiplier_Actual# = 3
	ee\PosMultiplier_NegativeActual# = 999

End Function

Function Update_Emerald(ee.tEmerald, p.tPlayer, d.tDeltaTime)

	PositionEntity(ee\Pivot,p\Objects\Position\x#,p\Objects\Position\y#,p\Objects\Position\z#)

	ee\PosMultiplier# = ee\PosMultiplier# + 1*d\Delta
	If ee\PosMultiplier# > ee\PosMultiplier_Actual# Then
		ee\PosMultiplier# = ee\PosMultiplier_Actual#
	EndIf

	PositionEntity(ee\Mesh[1], +0.0*ee\PosMultiplier#, 0.0, +6.6*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[2], +5.0*ee\PosMultiplier#, 0.0, +4.5*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[3], -5.0*ee\PosMultiplier#, 0.0, +4.5*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[4], -6.0*ee\PosMultiplier#, 0.0, -1.0*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[5], +6.0*ee\PosMultiplier#, 0.0, -1.0*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[6], -2.5*ee\PosMultiplier#, 0.0, -5.2*ee\PosMultiplier#)
	PositionEntity(ee\Mesh[7], +2.5*ee\PosMultiplier#, 0.0, -5.2*ee\PosMultiplier#)

	For i=1 To 7 : 	TurnEntity(ee\Mesh[i], 0, -1*d\Delta, 0) : Next

	TurnEntity(ee\Pivot, 0, 5*d\Delta, 0)

	If Not(p\Action=ACTION_TRANSFORM) Then Remove_Emerald(ee)

End Function

Function Remove_Emerald(ee.tEmerald)
	For i=1 To 7 : FreeEntity(ee\Mesh[i]) : Next
	Delete ee
End Function