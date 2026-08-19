Function GetMeshEntity(base,anim=False)
	For m.MeshStructure = Each MeshStructure
		If base=m\ID Then Return CopyEntity(m\Entity)
		
		
	Next
End Function

Function Game_Stage_ManageMeshes(d.tDeltaTime, m.MeshStructure)
	
	
	Select m\AnimTexType
		Case 1:
			PositionTexture(m\DiffuseTexture[1], m\ScrollSpeedX#*0.004*Game\Gameplay\Time, m\ScrollSpeedY#*0.004*Game\Gameplay\Time)
		Case 2,3:
			If m\ChangeTexTimer>0 Then
				m\ChangeTexTimer=m\ChangeTexTimer-timervalue#
			Else
				m\ChangeTexTimer=m\ChangeTexSpeed#*secs#
				m\ChangeTexOrder=m\ChangeTexOrder+1
				If m\ChangeTexOrder>4 Then m\ChangeTexOrder=1
				EntityTexture(m\Entity, m\DiffuseTexture[m\ChangeTexOrder])
			EndIf
			If m\AnimTexType=3 Then PositionTexture(m\DiffuseTexture[m\ChangeTexOrder], m\ScrollSpeedX#*0.004*Game\Gameplay\Time, m\ScrollSpeedY#*0.004*Game\Gameplay\Time)
	End Select
	
	If m\IsRollMesh=1 Then
		For p.tPlayer = Each tPlayer
			If p\Action=ACTION_ROLL Then
				If m\CollisionChanged=0 Then EntityType(m\Entity,COLLISION_NONE) : m\CollisionChanged=1
			Else
				
				If m\CollisionChanged=1 Then EntityType(m\Entity,COLLISION_WORLD_POLYGON) : m\CollisionChanged=0
			EndIf
		Next
	EndIf
	
	
	
	Select m\MoveType
		Case 1	
			MoveEntity(m\Entity,(m\MoveX#*0.1)*d\Delta#,(m\MoveY#*0.1)*d\Delta#,(m\MoveZ#*0.1)*d\Delta#)
			TurnEntity(m\Entity,(m\RotX#*0.1)*d\Delta#,(m\RotY#*0.1)*d\Delta#,(m\RotZ#*0.1)*d\Delta#)
		Case 2
			PointEntity(m\Pivot,m\DestinationTarget)
			PositionEntity(m\Entity,EntityX(m\Pivot),EntityY(m\Pivot),EntityZ(m\Pivot))
			PositionEntity m\DestinationTarget, m\MoveX#, m\MoveY#, m\MoveZ#, 1
			mx# = m\MoveX# - EntityX(m\Entity)
			my# = m\MoveY# - EntityY(m\Entity)
			mz# = m\MoveZ# - EntityZ(m\Entity)
							;AlignToVector(m\Entity, mx#, my#, mz#, 2, .925)
			MoveEntity(m\Pivot, 0, 0, m\MoveSpeed#*d\Delta)
			
	End Select
	
	Select m\LodType
		Case 0
			
		Case 1
			For p.tPlayer = Each tPlayer
				If EntityDistance(p\Objects\Entity,m\Pivot)>m\LodDistance Then
					HideEntity(m\Entity)
				Else
					ShowEntity(m\Entity)
				EndIf
			Next
	End Select
	
	If m\IsSwitchMesh=1 Then
		
		Select m\SwitchType
			Case 0
				If Game\SwitchOn[m\SwitchNo]=1 Then ShowEntity(m\Entity) Else HideEntity(m\Entity)
			Case 1
				If Game\SwitchOn[m\SwitchNo]=0 Then ShowEntity(m\Entity) Else HideEntity(m\Entity)
		End Select
		
	EndIf
	
	
	
	
	If m\ShallRepeat Then
		While pp(1)\Objects\Position\z#>EntityZ(m\Entity)+m\InitialPosZ#+m\RepeatDistance#
			PositionEntity m\Entity, EntityX(m\Entity), EntityY(m\Entity), EntityZ(m\Entity)+m\RepeatDistance#*2, 1
		Wend
		While pp(1)\Objects\Position\z#<EntityZ(m\Entity)+m\InitialPosZ#-m\RepeatDistance#
			PositionEntity m\Entity, EntityX(m\Entity), EntityY(m\Entity), EntityZ(m\Entity)-m\RepeatDistance#*2, 1
		Wend
	EndIf
	
	If m\AlphaType>0 Then
		If m\AlphaType=1 Then
			If m\ChangeAlphaTimer>0 Then 
				
				m\ChangeAlphaTimer=m\ChangeAlphaTimer-timervalue#
			Else
				EntityAlpha(m\Entity,m\Alpha#)
				
				If m\Alpha#<m\TargetAlpha#  Then 
					m\Alpha#=m\Alpha#+((m\AlphaRate*0.01)*d\Delta)
					If m\Alpha#>m\TargetAlphaUpper# Then m\TargetAlpha#=m\TargetAlphaLower# : m\ChangeAlphaTimer=m\AlphaTimer*secs#
				EndIf
				
				If m\Alpha#>m\TargetAlpha# Then 
					m\Alpha#=m\Alpha#-((m\AlphaRate*0.01)*d\Delta)
					If m\Alpha#<m\TargetAlphaLower# Then m\TargetAlpha#=m\TargetAlphaUpper# : m\ChangeAlphaTimer=m\AlphaTimer*secs#
				EndIf
			EndIf
		Else
			EntityAlpha(m\Entity,m\Alpha#)
		EndIf
		
	EndIf 
	
	
	
	
	
	
End Function
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Game_Stage_MusicOrganisation(d.tDeltaTime)
	
	If Menu\Stage<>0 And Game\Victory=0 Then
		Select Game\Stage\Properties\MusicStyle
			Case 0
				For i=0 To 2
					If i=Game\Stage\Properties\MusicMode Then
						If Game\Stage\Properties\MusicFade#[i]<1.0 Then Game\Stage\Properties\MusicFade#[i]=Game\Stage\Properties\MusicFade#[i]+0.025*d\Delta
						If Game\Stage\Properties\MusicFade#[i]>1.0 Then Game\Stage\Properties\MusicFade#[i]=1.0
					Else
						If Game\Stage\Properties\MusicFade#[i]>0.0 Then Game\Stage\Properties\MusicFade#[i]=Game\Stage\Properties\MusicFade#[i]-0.025*d\Delta
						If Game\Stage\Properties\MusicFade#[i]<0.0 Then Game\Stage\Properties\MusicFade#[i]=0.0
					EndIf
				Next
				If ChannelPlaying(Game\Stage\Properties\MusicChn[Game\Stage\Properties\MusicMode])=False Then Game\Stage\Properties\MusicChn[Game\Stage\Properties\MusicMode]=PlaySound(StageMusic[Game\Stage\Properties\MusicMode])
				
			Case 1
				For i=0 To 2
					If ChannelPlaying(Game\Stage\Properties\MusicChn[i])=False Then Game\Stage\Properties\MusicChn[i]=PlaySound(StageMusic[i])
				Next	
				Select Game\Stage\Properties\MusicMode
					Case 0
						If Game\Stage\Properties\MusicFade#[0]<1.0 Then Game\Stage\Properties\MusicFade#[0]=Game\Stage\Properties\MusicFade#[0]+0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[0]>1.0 Then Game\Stage\Properties\MusicFade#[0]=1.0
						If Game\Stage\Properties\MusicFade#[1]>0.0 Then Game\Stage\Properties\MusicFade#[1]=Game\Stage\Properties\MusicFade#[1]-0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[1]<0.0 Then Game\Stage\Properties\MusicFade#[1]=0.0
						If Game\Stage\Properties\MusicFade#[2]>0.0 Then Game\Stage\Properties\MusicFade#[2]=Game\Stage\Properties\MusicFade#[2]-0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[2]<0.0 Then Game\Stage\Properties\MusicFade#[2]=0.0
						
					Case 1
						If Game\Stage\Properties\MusicFade#[1]<1.0 Then Game\Stage\Properties\MusicFade#[1]=Game\Stage\Properties\MusicFade#[1]+0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[1]>1.0 Then Game\Stage\Properties\MusicFade#[1]=1.0
						
						If Game\Stage\Properties\MusicFade#[0]>0.0 Then Game\Stage\Properties\MusicFade#[0]=Game\Stage\Properties\MusicFade#[0]-0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[0]<0.0 Then Game\Stage\Properties\MusicFade#[0]=0.0
						
						If Game\Stage\Properties\MusicFade#[2]>0.0 Then Game\Stage\Properties\MusicFade#[2]=Game\Stage\Properties\MusicFade#[2]-0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[2]<0.0 Then Game\Stage\Properties\MusicFade#[2]=0.0
					Case 2
						If Game\Stage\Properties\MusicFade#[2]<1.0 Then Game\Stage\Properties\MusicFade#[2]=Game\Stage\Properties\MusicFade#[2]+0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[2]>1.0 Then Game\Stage\Properties\MusicFade#[2]=1.0
						
						If Game\Stage\Properties\MusicFade#[1]>0.0 Then Game\Stage\Properties\MusicFade#[1]=Game\Stage\Properties\MusicFade#[1]-0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[1]<0.0 Then Game\Stage\Properties\MusicFade#[1]=0.0
						
						If Game\Stage\Properties\MusicFade#[0]>0.0 Then Game\Stage\Properties\MusicFade#[0]=Game\Stage\Properties\MusicFade#[0]-0.035*d\Delta
						If Game\Stage\Properties\MusicFade#[0]<0.0 Then Game\Stage\Properties\MusicFade#[0]=0.0
				End Select
		End Select
		
	EndIf
	
	If ChannelPlaying(Game\Channel_Collectible) And Game\CollectiblePauseMusic=1 Then
		For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],0 : Next
		ChannelVolume(Game\Channel_Invincible,0)
		ChannelVolume(Game\Channel_SpeedShoes,0)
	ElseIf (Game\SpeedShoes=1 Or Game\Invinc=1) And Game\SuperForm=0 And (ChannelPlaying(Game\Channel_Drown)=False) Then
		For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],0 : Next
		ChannelVolume(Game\Channel_Invincible,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
		ChannelVolume(Game\Channel_SpeedShoes,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
		If Not(Game\SpeedShoeTimer>0) Then Game\SpeedShoes=0
		If Not(Game\InvincTimer>0) Then Game\Invinc=0
	ElseIf ChannelPlaying(Game\Channel_ChaoEffect) Then
		For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],0 : Next
		ChannelVolume(Game\Channel_Invincible,0)
		ChannelVolume(Game\Channel_SpeedShoes,0)
		ChannelVolume(Game\Channel_ChaoEffect,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
	ElseIf Game\Interface\Shop=1 Then
		For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],0 : Next
		If ChannelPlaying(Game\Channel_Shop)=False  Then Game\Channel_Shop=PlaySound(MenuMusic_Shop)
		
	Else
		StopChannel(Game\Channel_Shop)
		If ChannelPlaying(Game\Channel_Drown) Or pp(1)\DrownState>1 Then
			For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],0 : Next
		Else
			For i=0 To 2 : ChannelVolume Game\Stage\Properties\MusicChn[i],Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175)*Game\Stage\Properties\MusicFade#[i] : Next
			
		EndIf
		ChannelVolume(Game\Channel_Invincible,0)
		ChannelVolume(Game\Channel_SpeedShoes,0)
	EndIf
End Function

Function Game_Stage_Step(d.tDeltaTime)
	
	
	
		;deal with mouse
	HidePointer()
	Input\AllowMouse=False
	If Menu\Stage<>0 Then
		If Menu\Pause=0 And (Menu\ChaoGarden=0 Or Menu\Stage=999) Then Input\AllowMouse=True
	Else
		If (Menu\Menu=MENU_TRANSPORTER# And (Menu\Menu2=Menu_Transporter_Goodbye# Or Menu\Menu2=Menu_Transporter_Stadium#)) Then Input\AllowMouse=True
	EndIf
	If Game\Interface\DebugPlacerOn=1 And Menu\Pause=0 Then Input\AllowMouse=True
	If Input\AllowMouse Then
		If (Input\Hold\MouseCamUp Or Input\Hold\MouseCamDown Or Input\Hold\MouseCamLeft Or Input\Hold\MouseCamRight) Then MoveMouse(GAME_WINDOW_W Shr 1, GAME_WINDOW_H Shr 1)
	EndIf
	
		;music organization
	Game_Stage_MusicOrganisation(d)
	
		;save the counters
	If Game\Gameplay\Rings>99999 Then
		Game\Gameplay\Rings=99999
	ElseIf Game\Gameplay\Rings<0 Then
		Game\Gameplay\Rings=0
	EndIf
	If Game\Gameplay\Lives>9999 Then
		Game\Gameplay\Lives=9999
	ElseIf Game\Gameplay\Lives<0 Then
		Game\Gameplay\Lives=0
	EndIf
	If Game\Gameplay\Score>9999999 Then
		Game\Gameplay\Score=9999999
	ElseIf Game\Gameplay\Score<0 Then
		Game\Gameplay\Score=0
	EndIf
	If Game\Gameplay\GaugeEnergy>100 Then
		Game\Gameplay\GaugeEnergy=100
	ElseIf Game\Gameplay\GaugeEnergy<0 Then
		Game\Gameplay\GaugeEnergy=0
	EndIf 
	
	If SHOPITEM_ENABLED(SHOPMENU_ABILITY,ABILITY_ENDLESSGAUGE)=1 Then Game\Gameplay\GaugeEnergy=100
	
	;AmbientLight(Game\Stage\Properties\AmbientR, Game\Stage\Properties\AmbientG,Game\Stage\Properties\AmbientB)
	
	If Menu\Pause=0 Then
		
		If Game\AddSuperRing=1 Then
			If Game\SuperRingTimer>0 Then Game\SuperRingTimer=Game\SuperRingTimer-timervalue#
			If (Not(Game\SuperRingTimer>0)) Then 
				If Game\SuperRingsToAdd>0 Then
					Gameplay_AddRings(1)
					PlaySmartSound(Sound_Ring)
					Game\SuperRingTimer=0.1*secs#
					Game\SuperRingsToAdd=Game\SuperRingsToAdd-1
				Else
					Game\AddSuperRing=0
				EndIf
			EndIf
		EndIf
		
		If Game\AddGauge=1 Then
			If Game\Gameplay\GaugeEnergy=100 Then Game\AddGauge=0
			If Game\AddGaugeTimer>0 Then Game\AddGaugeTimer=Game\AddGaugeTimer-timervalue#
			If (Not(Game\AddGaugeTimer>0)) Then 
				If Game\GaugeToAdd>0 Then
					Game\Gameplay\GaugeEnergy=Game\Gameplay\GaugeEnergy+1
					Game\GaugeToAdd=Game\GaugeToAdd-1
					Game\AddGaugeTimer=0.02*secs#
					
				Else
					Game\AddGauge=0
				EndIf
			EndIf
		EndIf
		
		If Menu\Settings\ControllerSupport#=1 Then
			Gameplay_Camera_MouseRotationSpeedX# = 5
			Gameplay_Camera_MouseRotationSpeedY# = 5
		Else
			Gameplay_Camera_MouseRotationSpeedX# = 34
			Gameplay_Camera_MouseRotationSpeedY# = 34
		EndIf
		
		If Not(ChannelPlaying(Game\Channel_MoonTimer)) Then Game\Channel_MoonTimer=PlaySmartSound(Sound_MoonTimer)
		
		If Game\MoonRingTimer>0 Then
			Game\MoonRingTimer=Game\MoonRingTimer-timervalue#
			ChannelVolume(Game\Channel_MoonTimer,Menu\Settings\VolumeM#*(Menu\Settings\Volume#*0.175))
		Else
			If Game\MoonRingState=1 Then 
				PlaySmartSound(Sound_MoonFail)
				For o.tObject = Each tObject
					
					If o\ObjType=OBJTYPE_MOONRING Then
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_RING, o\Entity, 1)
						o\Done=1
					EndIf
					
				Next
				Game\MoonRingState=0
			EndIf
			
			ChannelVolume(Game\Channel_MoonTimer,0)
			
		EndIf
		
;ambient environment sounds
		If Game\Stage\Properties\AmbientAlarm=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientAlarm)) Then Game\Stage\Properties\Channel_AmbientAlarm=PlaySmartSound(Sound_AmbientAlarm)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientAlarm) Then StopChannel(Game\Stage\Properties\Channel_AmbientAlarm)
		EndIf
		
		If Game\Stage\Properties\AmbientBeach>=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientBeach)) Then Game\Stage\Properties\Channel_AmbientBeach=PlaySmartSound(Sound_AmbientBeach)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientBeach) Then StopChannel(Game\Stage\Properties\Channel_AmbientBeach)
		EndIf
		
		If Game\Stage\Properties\AmbientForest>=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientForest)) Then Game\Stage\Properties\Channel_AmbientForest=PlaySmartSound(Sound_AmbientForest)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientForest) Then StopChannel(Game\Stage\Properties\Channel_AmbientForest)
		EndIf
		
		If Game\Stage\Properties\AmbientRain>=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientRain)) Then Game\Stage\Properties\Channel_AmbientRain=PlaySmartSound(Sound_AmbientRain)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientRain) Then StopChannel(Game\Stage\Properties\Channel_AmbientRain)
		EndIf
		
		If Game\Stage\Properties\AmbientSnow>=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientSnow)) Then Game\Stage\Properties\Channel_AmbientSnow=PlaySmartSound(Sound_AmbientSnow)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientSnow) Then StopChannel(Game\Stage\Properties\Channel_AmbientSnow)
		EndIf
		
		If Game\Stage\Properties\AmbientVoid>=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientVoid)) Then Game\Stage\Properties\Channel_AmbientVoid=PlaySmartSound(Sound_AmbientVoid)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientVoid) Then StopChannel(Game\Stage\Properties\Channel_AmbientVoid)
		EndIf
		
		If Game\Stage\Properties\AmbientWind>=1 Then
			If Not(ChannelPlaying(Game\Stage\Properties\Channel_AmbientWind)) Then Game\Stage\Properties\Channel_AmbientWind=PlaySmartSound(Sound_AmbientWind)
		Else
			If ChannelPlaying(Game\Stage\Properties\Channel_AmbientWind) Then StopChannel(Game\Stage\Properties\Channel_AmbientWind)
		EndIf
		
		; For menu models to overcome images
		For c.tCamera = Each tCamera
			If Menu\Stage<>0 Then
				CameraClsMode c\Entity,1,1
			Else
				If Menu\Background>0 Then
					CameraClsMode c\Entity,0,1
					CameraRange(c\Entity, 1, 2000)
					PositionEntity(c\Entity,0,0.0,-31)
					RotateEntity(c\Entity,0,0,0)
					CameraZoom(c\Entity,50.0)
				Else
					CameraClsMode c\Entity,1,1
					CameraRange(c\Entity, 1, 2000)
					PositionEntity(c\Entity,0,-2.36,26.2005)
					RotateEntity(c\Entity,1.0,0,0)
					CameraZoom(c\Entity,1.0)
				EndIf
			EndIf
		Next
		
		; Update game timer and fps count
		timervalue#=(d\TimeCurrentFrame-d\TimePreviousFrame)*TimeControl#
		For p.tPlayer=Each tPlayer
			If p\No#=1 And (Not(p\ChaosControlActiveTimer>0)) Then
				If (Not(p\Action=ACTION_DEBUG)) Then Game\Gameplay\Time = Game\Gameplay\Time+(d\TimeCurrentFrame-d\TimePreviousFrame)*TimeControl#
			EndIf
		Next
		Game\Others\Frames = Game\Others\Frames + 1
		If (Game\Others\NextFrame < d\TimeCurrentFrame) Then
			Game\Others\Fps   	  = Game\Others\Frames
			Game\Others\Frames	  = 0
			Game\Others\NextFrame = d\TimeCurrentFrame+1000
		End If
		
		; Update gravity alignment
		AlignToVector(Game\Stage\Gravity, Game\Stage\GravityAlignment\x#, Game\Stage\GravityAlignment\y#, Game\Stage\GravityAlignment\z#, 2)
		
		; Update entities
		If Menu\Stage<>0 Then
			For c.tCamera = Each tCamera : Camera_Update(c,d) : Next
			For p.tPlayer = Each tPlayer : Player_Update(p,d) : Next
			If (Not(Game\CinemaMode=1)) Or Game\Interface\CinemaAllowUpdate=1 Then Objects_Update(d)
			For pt.tParticleTemplate = Each tParticleTemplate : ParticleTemplate_Update(pt) : Next
			TurnEntity Menu\RingRotator, 0, 0.15*20*d\Delta, 0
			TurnEntity Menu\RingRotator2, 0, -0.15*20*d\Delta, 0
			TurnEntity Menu\SlowRingRotator, 0, 0.15*10*d\Delta, 0
			TurnEntity Menu\SlowRingRotator2, 0,- 0.15*10*d\Delta, 0
		EndIf
		
		; Update particles
		If Menu\Settings\DisallowParticles#=0 Then UpdateParticles(d)
		
		; Update world
		UpdateWorld(d\Delta)
		
		; Head pointing & other after world update effects
		If Menu\Stage<>0 Then
			For p.tPlayer = Each tPlayer
				If p\No#>0 Then
					If Game\Vehicle>0 Then
						Select Game\Vehicle
							Case 5,8:
								Select p\Character
									Case CHAR_SON,CHAR_SHA,CHAR_TAI
										MoveEntity(p\Objects\Mesh,0,1.875,-0.9)
								End Select
							Case 4
								Select p\Character
									Case CHAR_SON,CHAR_SHA,CHAR_TAI
										MoveEntity(p\Objects\Mesh,0,1.5,-0.6)
								End Select
							Case 6
								Select p\Character
									Case CHAR_SON,CHAR_SHA,CHAR_TAI
										MoveEntity(p\Objects\Mesh,0,1.725,-0.6)
								End Select
							Case 9
								Select p\Character
									Case CHAR_SON,CHAR_SHA,CHAR_TAI
										MoveEntity(p\Objects\Mesh,0,0.15,-1)
								End Select
						End Select 
					EndIf 
					If Game\Vehicle>0 Then
						Select Game\Vehicle
							Case 6,7:
								If p\No#=1 Then
									Player_PlacementsOfVehicle(pp(1))
									If Menu\Members>1 Then Player_PlacementsOfVehicle(pp(2))
									If Menu\Members>2 Then Player_PlacementsOfVehicle(pp(3))
								EndIf
							Default:
								Player_PlacementsOfVehicle(p)
						End Select
					EndIf
				EndIf
			Next
		EndIf
		
		; Chao emotion
		If Menu\Stage<>0 Then
			For ch.tCheese=Each tCheese
				If ch\targetp\Character=CHAR_CRE Then Object_ChaoEmo_Update(ch\Mesh, ch\Emo, d)
			Next
			If Menu\ChaoGarden=1 Then
				For cc.tChaoManager=Each tChaoManager
					If ChaoManager_ChaoAlive(cc) Or ChaoManager_ChaoCocoonAlive(cc) Then
						Object_ChaoEmo_Update(cc\Mesh, cc\Emo, d)
						If cc\Stats\Hat>0 And cc\HatCoversHorn Then
							ScaleEntity cc\Mesh_horn2, 0, 0, 0
							PositionEntity cc\Mesh_horn2, EntityX(cc\Mesh_horn1,1), EntityY(cc\Mesh_horn1,1), EntityZ(cc\Mesh_horn1,1), 1
							RotateEntity cc\Mesh_horn2, EntityPitch(cc\Emo\Objects\emotionsroot), EntityYaw(cc\Emo\Objects\emotionsroot), EntityRoll(cc\Emo\Objects\emotionsroot)
							MoveEntity cc\Mesh_horn2, 0, 0, -0.25
							MoveEntity cc\Emo\Objects\emotionsroot, 0, 0.5, 0
						EndIf
					EndIf
				Next
			EndIf
		Else
			If Menu\MeshChaoEmoActivated>0 Then Object_ChaoEmo_Update(Menu\Mesh[2], Menu\MeshChaoEmo, d)
		EndIf
		
		; Update timers
		If Game\StartoutLock>0 Then Game\StartoutLock=Game\StartoutLock-timervalue#
		If Game\ControlLock>0 Then Game\ControlLock=Game\ControlLock-timervalue#
		If Game\CamLock>0 Then Game\CamLock=Game\CamLock-timervalue#
		If Game\CamLock2>0 Then Game\CamLock2=Game\CamLock2-timervalue#
		If Game\RunLock>0 Then Game\RunLock=Game\RunLock-timervalue#
		If Game\MachLock>0 Then Game\MachLock=Game\MachLock-timervalue#
		If Game\MachLockDisabler>0 Then Game\MachLockDisabler=Game\MachLockDisabler-timervalue#
		If Game\InvincTimer>0 Then Game\InvincTimer=Game\InvincTimer-timervalue#
		If Game\SpeedShoeTimer>0 Then Game\SpeedShoeTimer=Game\SpeedShoeTimer-timervalue#
		If cam\Lock\Timer>0 Then cam\Lock\Timer=cam\Lock\Timer-timervalue#
		If Game\RingDropTimer>0 Then Game\RingDropTimer=Game\RingDropTimer-timervalue#
		If Game\CounterChanceTimer>0 Then Game\CounterChanceTimer=Game\CounterChanceTimer-timervalue# Else Game\CounterChance=0
		If Game\SmartCameraRangeDontAffectTimer>0 Then Game\SmartCameraRangeDontAffectTimer=Game\SmartCameraRangeDontAffectTimer-timervalue#
		If Game\BishopMagicTimer>0 Then Game\BishopMagicTimer=Game\BishopMagicTimer-timervalue#
		If Game\F9Timer>0 Then Game\F9Timer=Game\F9Timer-timervalue#
		If Game\PassedLapTimer>0 Then Game\PassedLapTimer=Game\PassedLapTimer-timervalue#
		If Game\AmbientTimer>0 Then Game\AmbientTimer=Game\AmbientTimer-timervalue
		
;		If (Not(Game\AmbientTimer>0)) Then 
;			Game\AmbientState=Abs(Game\AmbientState-1)
;			Select Game\AmbientState
;				Case 0
;					Game\Stage\Properties\AmbientR=140
;					Game\Stage\Properties\AmbientG=140
;					Game\Stage\Properties\AmbientB=140
;				Case 1
;					Game\Stage\Properties\AmbientR=255
;					Game\Stage\Properties\AmbientG=010
;					Game\Stage\Properties\AmbientB=010
;			End Select
;			Game\AmbientTimer=1.5*secs#
;		EndIf
		
		; Carnival
		If Menu\Mission=MISSION_CARNIVAL# Then
			If Game\CarnivalAppearTimer>0 Then
				Game\CarnivalAppearTimer=Game\CarnivalAppearTimer-timervalue#
			Else
				If Game\CurrentCarnivalTimer>0 Then
					Game\CurrentCarnivalTimer=Game\CurrentCarnivalTimer-timervalue#
				Else
					foundcarnivals = False
					For o.tObject = Each tObject
						If o\ThisIsAnEnemy Then
							If o\Enemy\CarnivalNo=Game\CarnivalLevel And o\Done=0 Then foundcarnivals=True
						EndIf
					Next
					If foundcarnivals = False Then
						Game\CarnivalLevel=Game\CarnivalLevel+1
						Game\CarnivalAppearTimer=1*secs#
					EndIf
				EndIf
			EndIf
		EndIf
		
		; Counter
		If Game\CounterChance>=5 Then
			Game\CounterChance=0
			Game\CounterChanceTimer=0
			Gameplay_AddLives(1)
			MonitorIcon_Draw(2)
		EndIf
		
		; Update animated textures for objects
		PositionTexture(Object_Texture_Pads, 0, -0.0035*Game\Gameplay\Time)
		PositionTexture(Object_Texture_Panel, 0, -0.00175*Game\Gameplay\Time)
		PositionTexture(Object_Texture_RailPads, 0.0035*Game\Gameplay\Time, 0)
		PositionTexture(Object_Texture_TrickPanel, 0, -0.00175*Game\Gameplay\Time)
		PositionTexture(Object_Texture_DashHoop, 0, 0.5*0.0035*Game\Gameplay\Time)
		PositionTexture(Object_Texture_RainbowHoop, 0, 0.5*0.0035*Game\Gameplay\Time)
		PositionTexture(Object_Texture_AcceleratorLight, 0, -0.75*0.0020*Game\Gameplay\Time)
		
		; Update mesh structures
		For m.MeshStructure = Each MeshStructure
			Game_Stage_ManageMeshes(d,m)
		Next
		
		; Update extra timers
		If Game\CheeseTimer>0 Then Game\CheeseTimer=Game\CheeseTimer-timervalue#
		If Game\FroggyTimer>0 Then Game\FroggyTimer=Game\FroggyTimer-timervalue#
		If Game\TranslatorTimer>0 Then Game\TranslatorTimer=Game\TranslatorTimer-timervalue#
		If Game\Interface\NearCautionTimer>0 Then Game\Interface\NearCautionTimer=Game\Interface\NearCautionTimer-timervalue#
		If Game\Interface\PointsTimer>0 Then Game\Interface\PointsTimer=Game\Interface\PointsTimer-timervalue#
		If Game\Interface\WorldTokenTimer>0 Then Game\Interface\WorldTokenTimer=Game\Interface\WorldTokenTimer-timervalue#
		If Game\Interface\ShowChaoTimer>0 Then Game\Interface\ShowChaoTimer=Game\Interface\ShowChaoTimer-timervalue#
		If Game\Interface\ShowCaution1Timer>0 Then Game\Interface\ShowCaution1Timer=Game\Interface\ShowCaution1Timer-timervalue#
		If Game\Interface\ShowCaution2Timer>0 Then Game\Interface\ShowCaution2Timer=Game\Interface\ShowCaution2Timer-timervalue#
		If Game\Interface\ShowCaution3Timer>0 Then Game\Interface\ShowCaution3Timer=Game\Interface\ShowCaution3Timer-timervalue#
		If Game\PenguinatorMovingTimer>0 Then Game\PenguinatorMovingTimer=Game\PenguinatorMovingTimer-timervalue#
		
		; Gain extra live
		For i=1 To 9
			If Game\Gameplay\Rings>=100*i And Game\Gameplay\GainedLives < i Then Player_GainExtraLife(p)
		Next
		
		; Cancel old EMBM
		FxManager_Supported = False
		
		; Update water texture
		If Game\Stage\Properties\Water=1 Then
			If Not(Game\Stage\Properties\WaterTextureTimer>0) Then
				Game\Stage\Properties\WaterTextureTimer=0.05*secs#
				Game\Stage\Properties\WaterTexture=Game\Stage\Properties\WaterTexture+1
				If Game\Stage\Properties\WaterTexture>13 Then Game\Stage\Properties\WaterTexture=1
				
				; bump water
				If Menu\Settings\BumpMaps#>0 Then
					ScaleTexture(water_bump_texture(Game\Stage\Properties\WaterType), 15.024, 15.024)
					PositionTexture(water_bump_texture(Game\Stage\Properties\WaterType), 0, 0.0725*0.0035*Game\Gameplay\Time)
					EntityTexture Game\Stage\Properties\WaterMesh, water_bump_texture(Game\Stage\Properties\WaterType),0,0
				EndIf
				
				; water texture
				Select Game\Stage\Properties\WaterType
					Case 6:
					Default:
						EntityTexture Game\Stage\Properties\WaterMesh, water_texture(Game\Stage\Properties\WaterType,Game\Stage\Properties\WaterTexture),0,1
				End Select
			Else
				Select Game\Stage\Properties\WaterType
					Case 6: PositionTexture(water_texture(Game\Stage\Properties\WaterType,1), 0, 0.00016375*Game\Gameplay\Time)
				End Select
				Game\Stage\Properties\WaterTextureTimer=Game\Stage\Properties\WaterTextureTimer-timervalue#
			EndIf
			
			; water level changing
			If Game\Stage\Properties\WaterLevelChanged=1 Then
				If (Not(ChannelPlaying(Game\Stage\Properties\WaterLevelChangeChannel))) Then Game\Stage\Properties\WaterLevelChangeChannel=PlaySmartSound(Sound_WaterRise)
				If Abs(Game\Stage\Properties\WaterLevel-Game\Stage\Properties\WaterLevelTarget)<2 Then
					Game\Stage\Properties\WaterLevelChanged=0
					Game\Stage\Properties\WaterLevel=Game\Stage\Properties\WaterLevelTarget
				Else
					If Game\Stage\Properties\WaterLevel>Game\Stage\Properties\WaterLevelTarget Then Game\Stage\Properties\WaterLevel=Game\Stage\Properties\WaterLevel-1*d\Delta
					If Game\Stage\Properties\WaterLevel<Game\Stage\Properties\WaterLevelTarget Then Game\Stage\Properties\WaterLevel=Game\Stage\Properties\WaterLevel+1*d\Delta
				EndIf
				PositionEntity(Game\Stage\Properties\WaterMesh, 0, Game\Stage\Properties\WaterLevel, 0)
			Else
				StopChannel(Game\Stage\Properties\WaterLevelChangeChannel)
			EndIf
		EndIf
		
		; Randomize black market
		If Menu\ChaoGarden=1 Then
			If Not(Menu\BlackMarketRandomizerTimer>0) Then
				RandomizeDealersInventory()
				Interface_CreateChaoMsg("The Black Market has been restocked",51,51,255)
				Menu\BlackMarketRandomizerTimer=300*secs#
			Else
				Menu\BlackMarketRandomizerTimer=Menu\BlackMarketRandomizerTimer-timervalue#
			EndIf
		EndIf
		
	EndIf
	
	; Render menu
	If Menu\Stage = 0 Then
		If Menu\Background>0 Then Menu_Update(d)
	EndIf
	
	For c.tCamera = Each tCamera
		
		; Render world
		CameraClsColor(c\Entity,0,0,0) ; CameraClsColor(c\Entity,255,255,255)
		CameraProjMode (c\Entity, 1)
		
		;skydome stuff
		If Menu\Stage<>0 Then
			PositionEntity(Game\Stage\Properties\Skydome, EntityX(c\Entity), EntityY(c\Entity), EntityZ(c\Entity))
			PositionEntity(Game\Stage\Properties\Earth, EntityX(c\Entity), EntityY(c\Entity), EntityZ(c\Entity))
			PositionEntity(Game\Stage\Properties\Moon, EntityX(c\Entity), EntityY(c\Entity), EntityZ(c\Entity))
			PositionEntity(Game\Stage\Properties\Sun, EntityX(c\Entity)+Game\Stage\Properties\SunPos\x#, EntityY(c\Entity)+Game\Stage\Properties\SunPos\y#, EntityZ(c\Entity)+Game\Stage\Properties\SunPos\z#)
			If Menu\ChaoGarden=1 And Menu\Stage=999 Then
				PositionEntity(Game\Stage\Properties\SunMoon, EntityX(c\Entity)+Game\Stage\Properties\SunPos\x#, EntityY(c\Entity)+Game\Stage\Properties\SunPos\y#, EntityZ(c\Entity)+Game\Stage\Properties\SunPos\z#)
				Stage_UpdateCyclingSkyBox(c\Entity, d)
			EndIf
		EndIf
		
		; Sun stuff
		If Game\Stage\Properties\Sun>0 Then
			PointEntity(Game\Stage\Properties\Sun, c\Entity)
			If Menu\ChaoGarden=1 And Menu\Stage=999 Then PointEntity(Game\Stage\Properties\SunMoon, c\Entity)
		EndIf
		
		; Is camera under water?
		If c\DontDoUnderwaterEffectsTimer>0 Then c\DontDoUnderwaterEffectsTimer=c\DontDoUnderwaterEffectsTimer-timervalue#
		If c\Underwater=1 And (Not c\DontDoUnderwaterEffectsTimer>0) Then
			CameraFogMode c\Entity, 1
			Select Game\Stage\Properties\WaterType
				Case 2:
					CameraFogColor c\Entity, 120, 70, 70
				Case 4:
					CameraFogColor c\Entity, 70, 120, 70
				Case 5:
					CameraFogColor c\Entity, 70, 120, 100
				Case 6:
					CameraFogColor c\Entity, 120, 90, 70
				Case 7:
					CameraFogColor c\Entity, 110, 70, 120
				Case 8:
					CameraFogColor c\Entity, 120, 70, 110
				Case 9:
					CameraFogColor c\Entity, Game\Stage\Properties\WaterFogR, Game\Stage\Properties\WaterFogG, Game\Stage\Properties\WaterFogB
				Default:
					CameraFogColor c\Entity, 70, 80, 120
			End Select
			Select Game\Stage\Properties\WaterType
				Case 9
					CameraFogRange c\Entity, Game\Stage\Properties\WaterFogNear, Game\Stage\Properties\WaterFogFar
				Default
					CameraFogRange c\Entity, 0.1, 390
			End Select
			If Game\Stage\Properties\Water=1 And EntityY(c\Entity) < Game\Stage\Properties\WaterLevel Then RotateEntity Game\Stage\Properties\WaterMesh,180,0,0
			Select Game\Stage\Properties\WaterType
				Case 2:
					Game\FilterIntensity#=0.525 : Game\FilterColorR#=196 : Game\FilterColorG#=030 : Game\FilterColorB#=013
				Case 4:
					Game\FilterIntensity#=0.475 : Game\FilterColorR#=131 : Game\FilterColorG#=207 : Game\FilterColorB#=032
				Case 5:
					Game\FilterIntensity#=0.375 : Game\FilterColorR#=051 : Game\FilterColorG#=233 : Game\FilterColorB#=060
				Case 6:
					Game\FilterIntensity#=0.875 : Game\FilterColorR#=203 : Game\FilterColorG#=182 : Game\FilterColorB#=087
				Case 7:
					Game\FilterIntensity#=0.875 : Game\FilterColorR#=146 : Game\FilterColorG#=023 : Game\FilterColorB#=191
				Case 8:
					Game\FilterIntensity#=0.375 : Game\FilterColorR#=251 : Game\FilterColorG#=098 : Game\FilterColorB#=242
				Default:
					Game\FilterIntensity#=0.375 : Game\FilterColorR#=000 : Game\FilterColorG#=157 : Game\FilterColorB#=225
			End Select
		Else
			CameraFogMode c\Entity, Game\Stage\Properties\Fog
			
			If Game\Stage\Properties\FogR#<Game\Stage\Properties\TargetFogR# Then Game\Stage\Properties\FogR#=Game\Stage\Properties\FogR#+Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			If Game\Stage\Properties\FogR#>Game\Stage\Properties\TargetFogR# Then Game\Stage\Properties\FogR#=Game\Stage\Properties\FogR#-Game\Stage\Properties\TargetFogChangeRate#*d\Delta	
			
			If Game\Stage\Properties\FogG#<Game\Stage\Properties\TargetFogG# Then Game\Stage\Properties\FogG#=Game\Stage\Properties\FogG#+Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			If Game\Stage\Properties\FogG#>Game\Stage\Properties\TargetFogG# Then Game\Stage\Properties\FogG#=Game\Stage\Properties\FogG#-Game\Stage\Properties\TargetFogChangeRate#*d\Delta	
			
			If Game\Stage\Properties\FogB<Game\Stage\Properties\TargetFogB# Then Game\Stage\Properties\FogB#=Game\Stage\Properties\FogB#+Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			If Game\Stage\Properties\FogB>Game\Stage\Properties\TargetFogB# Then Game\Stage\Properties\FogB#=Game\Stage\Properties\FogB#-Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			
			If Game\Stage\Properties\FogNearDist#<Game\Stage\Properties\TargetFogNearDist# Then Game\Stage\Properties\FogNearDist#=Game\Stage\Properties\FogNearDist#+Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			If Game\Stage\Properties\FogNearDist#>Game\Stage\Properties\TargetFogNearDist# Then Game\Stage\Properties\FogNearDist#=Game\Stage\Properties\FogNearDist#-Game\Stage\Properties\TargetFogChangeRate#*d\Delta	
			
			If Game\Stage\Properties\FogFarDist#<Game\Stage\Properties\TargetFogFarDist# Then Game\Stage\Properties\FogFarDist#=Game\Stage\Properties\FogFarDist#+Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			If Game\Stage\Properties\FogFarDist#>Game\Stage\Properties\TargetFogFarDist# Then Game\Stage\Properties\FogFarDist#=Game\Stage\Properties\FogFarDist#-Game\Stage\Properties\TargetFogChangeRate#*d\Delta
			
			
			CameraFogColor c\Entity, Int(Game\Stage\Properties\FogR), Int(Game\Stage\Properties\FogG), Int(Game\Stage\Properties\FogB)	
			CameraFogRange c\Entity, Int(Game\Stage\Properties\FogNearDist), Int(Game\Stage\Properties\FogFarDist)
			If Game\Stage\Properties\Water=1 And EntityY(c\Entity) > Game\Stage\Properties\WaterLevel Then RotateEntity Game\Stage\Properties\WaterMesh,0,0,0
			If Menu\Pause=1 Or Game\Interface\Shop=1 Or Game\Interface\SoundTest=1 Then
				If Game\Stage\Properties\FilterOn=1 Then 
					Game\FilterIntensity#=Game\Stage\Properties\FilterIntesity*0.8 : Game\FilterColorR#=Game\Stage\Properties\FilterR*0.24 : Game\FilterColorG#=Game\Stage\Properties\FilterG*0.24 : Game\FilterColorB#=Game\Stage\Properties\FilterB*0.24
				Else
					Game\FilterIntensity#=0.7 : Game\FilterColorR#=20 : Game\FilterColorG#=20 : Game\FilterColorB#=20
				EndIf
			ElseIf Game\Interface\DebugConsole=1 Then
				
				Game\FilterIntensity#=0.7 : Game\FilterColorR#=20 : Game\FilterColorG#=20 : Game\FilterColorB#=20
			Else
				If Game\Stage\Properties\FilterOn=1 Then 
					Game\FilterIntensity#=Game\Stage\Properties\FilterIntesity : Game\FilterColorR#=Game\Stage\Properties\FilterR : Game\FilterColorG#=Game\Stage\Properties\FilterG : Game\FilterColorB#=Game\Stage\Properties\FilterB
				Else
					Game\FilterIntensity#=0.000 : Game\FilterColorR#=255 : Game\FilterColorG#=255 : Game\FilterColorB#=255
				EndIf
				
			EndIf
		EndIf
		
		
		SetFilterColor(c\Filter, Game\FilterIntensity#, Game\FilterColorR#, Game\FilterColorG#, Game\FilterColorB#)
		
		FxManager_RenderWorldFast()
		CameraProjMode (c\Entity, 0)
		
		BumpTextureFrame = BumpTextureFrame + 1
		
		If Menu\Pause=0 Then
			
		;ambient environment effects
			If Menu\Pause=0 Then
				If Game\Stage\Properties\AmbientRain=1 Then
					If c\Underwater=0 Then ParticleTemplate_Call(Game\Stage\Properties\AmbientParticle, PARTICLE_AMBIENT_RAIN, c\Entity)
				EndIf
				
				If Game\Stage\Properties\AmbientSnow=1 Then
					If c\Underwater=0 Then ParticleTemplate_Call(Game\Stage\Properties\AmbientParticle, PARTICLE_AMBIENT_SNOW, c\Entity)
				EndIf
				
				If Game\Stage\Properties\AmbientForest=1 Then
					If c\Underwater=0 Then ParticleTemplate_Call(Game\Stage\Properties\AmbientParticle, PARTICLE_AMBIENT_LEAF, c\Entity)
				ElseIf Game\Stage\Properties\AmbientForest=2 Then
					If c\Underwater=0 Then ParticleTemplate_Call(Game\Stage\Properties\AmbientParticle, PARTICLE_AMBIENT_TWILIGHT, c\Entity)
				EndIf
			EndIf
			
		EndIf
		
		; Render FastExt effects
		If Menu\Stage<>0 Then
			For p.tPlayer = Each tPlayer
				If p\ChaosControlActiveTimer>0 Then RenderPostprocess(FE_Inverse)
			Next
			
			If Game\CinemaMode>0 Then
				
				If Input\Pressed\Back Then
					Select Game\CinemaMode
						Case 1: Game\CinemaMode=3
						Case 3: Game\CinemaMode=1
					End Select
				EndIf
				
				
				For i=1 To 9
					If KeyHit(i) Then
						Game\CinemaFilterOn[i]=Abs(Game\CinemaFilterOn[i]-1)
					EndIf 
					
				Next 
				
				If Input\Pressed\ActionSkill3 Then
					For i = 0 To 9
						Game\CinemaFilterOn[i]=0
					Next
					Game\CinemaFilter=Game\CinemaFilter+1
					Game\CinemaFilterOn[Game\CinemaFilter]=1
					If Game\CinemaFilter>9 Then Game\CinemaFilter=1
				EndIf
				
				If Game\CinemaFilterOn[2]=1 Then RenderPostprocess(FE_Glow)
				If Game\CinemaFilterOn[3]=1 Then RenderPostprocess(FE_Inverse)
				If Game\CinemaFilterOn[4]=1 Then RenderPostprocess(FE_Grayscale)
				If Game\CinemaFilterOn[5]=1 Then RenderPostprocess(FE_Contrast)
				If Game\CinemaFilterOn[6]=1 Then RenderPostprocess(FE_Overlay)
				If Game\CinemaFilterOn[7]=1 Then RenderPostprocess(FE_Posterize)
				If Game\CinemaFilterOn[8]=1 Then RenderPostprocess(FE_Rays)
				If Game\CinemaFilterOn[9]=1 Then RenderPostprocess(FE_Blur)
				
				If TimeControl#<0.0 Then RenderPostprocess(FE_Inverse)
				If Menu\Settings\DepthOfField#=1 Then RenderPostprocess(FE_DOF)
				
				If Menu\Settings\MotionBlur#=1 Or Game\DoBoost=1 And Game\Interface\DebugPlacerOn=0 Then Postprocess_BlurHandle(1,1,0,0,((pp(1)\SpeedLength#-2.2)/6.5),(pp(1)\Objects\Camera\Rotation\y#-(pp(1)\Animation\Direction#-90)))
				If Game\Stage\Properties\Sun>0 And Game\Stage\Properties\SunRays=1 Then Postprocess_SunRays(Menu\Settings\sunrays#)
				
				
				If KeyHit(KEY_0) Then
					For i = 0 To 9
						Game\CinemaFilterOn[i]=0
					Next
				EndIf
			EndIf	
			
			
			
			
		EndIf
		
		PostEffect_UpdateAll(d)
		
		; Render menu
		If Menu\Stage = 0 Then
			If Menu\Background=0 Then Menu_Update(d)
		EndIf
		
		; Render interface
		If Menu\Stage<>0 Then
			If Menu\ChaoGarden=1 Then
				If Menu\Pause=1 Then
					Select Menu\Settings\ChaoNameTag#
						Case 0: Interface_ActivateGardenAction(3, "Toggle name tags on    ")
						Case 1: Interface_ActivateGardenAction(3, "Toggle name tags off   ")
					End Select
				EndIf
				If Input\Pressed\Change Then Menu\Settings\ChaoNameTag#=Abs(Menu\Settings\ChaoNameTag#-1)
				If Menu\Settings\ChaoNameTag#=1 And Menu\Stage=999 And (Not(Game\MustQuitStage>0)) Then
					For cc.tChaoManager = Each tChaoManager
						Chao_Interface_NameTag(cc, c)	
					Next
				EndIf
			EndIf
			
			For p.tPlayer=Each tPlayer
				If p\No#=1 Then
					If Menu\Mission=MISSION_HUNT# Then
						If Game\Interface\NearCautionTimer>0 Then
							Player_Interface_TreasureCaution(p,c)
						EndIf
					EndIf
					Interface_Render(p,d)
					If Menu\Pause=1 Then Menu_Pause_Update()
					If Game\MustQuitStage>0 Then Interface_Render_Exit(p,d)
				EndIf
			Next
			
			For o.tObject=Each tObject
				Select o\ObjType
					Case OBJTYPE_FLICKY:
						If o\State=0 Or o\State=-2 Object_Flicky_Tag(o, cam)
					End Select
				Next
			EndIf
			If Game\Interface\HideInterface=0 And Menu\Settings\HideAchievements#=0 Then  Achievements_Render(d)
		Next
		
	; chao garden cheats
		If Menu\Settings\Debug#=1 And Menu\Developer=1 And Menu\ChaoGarden=1 And Menu\Stage=999 Then
		;random chao overwriting
			If (KeyHit(KEY_F9)) Then OverwriteGardenAndCreateRandomChao() : Game\SmartCameraRangeDontAffectTimer=5*secs#
			
		; chao sky cheat
			If (KeyHit(KEY_F8)) Then
				Game\Stage\Properties\SkyCycle=Game\Stage\Properties\SkyCycle+1
				If Game\Stage\Properties\SkyCycle>4 Then Game\Stage\Properties\SkyCycle=1
				Select Game\Stage\Properties\SkyCycle
					Case 1,3: Game\Stage\Properties\SkyCycleTimer=600*secs#
					Case 2,4: Game\Stage\Properties\SkyCycleTimer=300*secs#
				End Select
				Stage_ForceUpdateCyclingSkyBox()
			EndIf
		EndIf
		
		Flip(GAME_WINDOW_VSYNC)
		
End Function

;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------

Function Stage_CreateCyclingSkyBox(path$)

	DawnSkyTexture = LoadTexture(path$+"Skydome/skyDawn.png")
	DaySkyTexture = LoadTexture(path$+"Skydome/skyDay.png")
	NightSkyTexture = LoadTexture(path$+"Skydome/skyNight.png")

	For i=1 To 3
		Game\Stage\Properties\SkyMesh[i] = LoadAnimMesh(path$+"Skydome/Sky.b3d")
		ScaleEntity(Game\Stage\Properties\SkyMesh[i], 0.1, 0.1, 0.1)
		Select i
			Case 1: EntityTexture(Game\Stage\Properties\SkyMesh[i], DawnSkyTexture)
			Case 2: EntityTexture(Game\Stage\Properties\SkyMesh[i], DaySkyTexture)
			Case 3: EntityTexture(Game\Stage\Properties\SkyMesh[i], NightSkyTexture)
		End Select
		EntityColor(Game\Stage\Properties\SkyMesh[i], 255, 255, 255)
		EntityFX(Game\Stage\Properties\SkyMesh[i], 9)
		EntityOrder(Game\Stage\Properties\SkyMesh[i], 1)
		Animate(Game\Stage\Properties\SkyMesh[i],1)
	Next

	Game\Stage\Properties\SkyCycle = 4

	FreeTexture(DawnSkyTexture)
	FreeTexture(DaySkyTexture)
	FreeTexture(NightSkyTexture)

End Function
	
Function Stage_UpdateCyclingSkyBox(camera, d.tDeltaTime)

	If Not(Game\Stage\Properties\SkyCycleTimer>0) Then
		Game\Stage\Properties\SkyCycle=Game\Stage\Properties\SkyCycle+1
		If Game\Stage\Properties\SkyCycle>4 Then Game\Stage\Properties\SkyCycle=1
		Select Game\Stage\Properties\SkyCycle
			Case 1,3: Game\Stage\Properties\SkyCycleTimer=600*secs#
			Case 2,4: Game\Stage\Properties\SkyCycleTimer=300*secs#
		End Select
	Else
		Game\Stage\Properties\SkyCycleTimer=Game\Stage\Properties\SkyCycleTimer-timervalue#
	EndIf

	Select Game\Stage\Properties\SkyCycle
		Case 1: ;morning
			Game\Stage\Properties\SkyMeshAlpha[1]=Game\Stage\Properties\SkyMeshAlpha[1]-(0.014/4)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[2]=Game\Stage\Properties\SkyMeshAlpha[2]+(0.024/4)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[3]=Game\Stage\Properties\SkyMeshAlpha[3]-(0.014/4)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[1]>=140) Then Game\Stage\Properties\AmbientCycle[1]=Game\Stage\Properties\AmbientCycle[1]+(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[2]>=140) Then Game\Stage\Properties\AmbientCycle[2]=Game\Stage\Properties\AmbientCycle[2]+(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[3]>=140) Then Game\Stage\Properties\AmbientCycle[3]=Game\Stage\Properties\AmbientCycle[3]+(5/2)*d\Delta
		Case 2: ;nightdawn
			Game\Stage\Properties\SkyMeshAlpha[1]=Game\Stage\Properties\SkyMeshAlpha[1]+(0.024)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[2]=Game\Stage\Properties\SkyMeshAlpha[2]-(0.014)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[3]=Game\Stage\Properties\SkyMeshAlpha[3]-(0.014)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[1]>=193) Then Game\Stage\Properties\AmbientCycle[1]=Game\Stage\Properties\AmbientCycle[1]+(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[2]<=093) Then Game\Stage\Properties\AmbientCycle[2]=Game\Stage\Properties\AmbientCycle[2]-(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[3]<=018) Then Game\Stage\Properties\AmbientCycle[3]=Game\Stage\Properties\AmbientCycle[3]-(5/2)*d\Delta
		Case 3: ;night
			Game\Stage\Properties\SkyMeshAlpha[1]=Game\Stage\Properties\SkyMeshAlpha[1]-(0.014/4)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[2]=Game\Stage\Properties\SkyMeshAlpha[2]-(0.014/4)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[3]=Game\Stage\Properties\SkyMeshAlpha[3]+(0.014/4)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[1]<=043) Then Game\Stage\Properties\AmbientCycle[1]=Game\Stage\Properties\AmbientCycle[1]-(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[2]<=021) Then Game\Stage\Properties\AmbientCycle[2]=Game\Stage\Properties\AmbientCycle[2]-(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[3]>=076) Then Game\Stage\Properties\AmbientCycle[3]=Game\Stage\Properties\AmbientCycle[3]+(5/2)*d\Delta
		Case 4: ;morningdawn
			Game\Stage\Properties\SkyMeshAlpha[1]=Game\Stage\Properties\SkyMeshAlpha[1]+(0.024/4)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[2]=Game\Stage\Properties\SkyMeshAlpha[2]-(0.014/4)*d\Delta
			Game\Stage\Properties\SkyMeshAlpha[3]=Game\Stage\Properties\SkyMeshAlpha[3]-(0.014/4)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[1]>=150) Then Game\Stage\Properties\AmbientCycle[1]=Game\Stage\Properties\AmbientCycle[1]+(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[2]>=092) Then Game\Stage\Properties\AmbientCycle[2]=Game\Stage\Properties\AmbientCycle[2]+(5/2)*d\Delta
			If Not(Game\Stage\Properties\AmbientCycle[3]<=018) Then Game\Stage\Properties\AmbientCycle[3]=Game\Stage\Properties\AmbientCycle[3]-(5/2)*d\Delta
	End Select

	EntityAlpha(Game\Stage\Properties\SkyMesh[1],Game\Stage\Properties\SkyMeshAlpha[1])
	EntityAlpha(Game\Stage\Properties\SkyMesh[2],Game\Stage\Properties\SkyMeshAlpha[2])
	EntityAlpha(Game\Stage\Properties\SkyMesh[3],Game\Stage\Properties\SkyMeshAlpha[3])
	EntityAlpha(Game\Stage\Properties\Sun,Game\Stage\Properties\SkyMeshAlpha[2])
	EntityAlpha(Game\Stage\Properties\SunMoon,Game\Stage\Properties\SkyMeshAlpha[3])

	AmbientLight(Game\Stage\Properties\AmbientCycle[1],Game\Stage\Properties\AmbientCycle[2],Game\Stage\Properties\AmbientCycle[3])
	For m.MeshStructure = Each MeshStructure : EntityColor(m\Entity,Game\Stage\Properties\AmbientCycle[1]+50,Game\Stage\Properties\AmbientCycle[2]+50,Game\Stage\Properties\AmbientCycle[3]+50) : Next

	For i=1 To 3
		If Game\Stage\Properties\SkyMeshAlpha[i]>1.0 Then Game\Stage\Properties\SkyMeshAlpha[i]=1.0
		If Game\Stage\Properties\SkyMeshAlpha[i]<0.0 Then Game\Stage\Properties\SkyMeshAlpha[i]=0.0
		PositionEntity(Game\Stage\Properties\SkyMesh[i], EntityX(camera), EntityY(camera), EntityZ(camera))
	Next

End Function

Function Stage_ForceUpdateCyclingSkyBox()

	Select Game\Stage\Properties\SkyCycle
		Case 1: ;nightdawn
			Game\Stage\Properties\SkyMeshAlpha[1]=1
			Game\Stage\Properties\SkyMeshAlpha[2]=0
			Game\Stage\Properties\SkyMeshAlpha[3]=0
			Game\Stage\Properties\AmbientCycle[1]=140
			Game\Stage\Properties\AmbientCycle[2]=140
			Game\Stage\Properties\AmbientCycle[3]=140
		Case 2: ;night
			Game\Stage\Properties\SkyMeshAlpha[1]=0
			Game\Stage\Properties\SkyMeshAlpha[2]=0
			Game\Stage\Properties\SkyMeshAlpha[3]=1
			Game\Stage\Properties\AmbientCycle[1]=193
			Game\Stage\Properties\AmbientCycle[2]=093
			Game\Stage\Properties\AmbientCycle[3]=018
		Case 3: ;morningdawn
			Game\Stage\Properties\SkyMeshAlpha[1]=1
			Game\Stage\Properties\SkyMeshAlpha[2]=0
			Game\Stage\Properties\SkyMeshAlpha[3]=0
			Game\Stage\Properties\AmbientCycle[1]=043
			Game\Stage\Properties\AmbientCycle[2]=021
			Game\Stage\Properties\AmbientCycle[3]=076
		Case 4: ;morning
			Game\Stage\Properties\SkyMeshAlpha[1]=0
			Game\Stage\Properties\SkyMeshAlpha[2]=1
			Game\Stage\Properties\SkyMeshAlpha[3]=0
			Game\Stage\Properties\AmbientCycle[1]=150
			Game\Stage\Properties\AmbientCycle[2]=092
			Game\Stage\Properties\AmbientCycle[3]=018
	End Select

End Function

;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------

Function Stage_ResetStageMusic()
	Game\Stage\Properties\MusicMode=0
	For i=0 To 2
		Game\Stage\Properties\MusicFade#[i]=1.0
		StopChannel(Game\Stage\Properties\MusicChn[i])
	Next
End Function

;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------
;~IDEal Editor Parameters:
;~C#Blitz3D