; =========================================================================================================
; Create Player's Online Data
; =========================================================================================================
Function Player_CreateOnlineData(p.tPlayer, pname$, pid%, no%, localplayer%=True)
	; build players online type.
	p\Online = New tPlayer_Online
	; the position/rotation vector.
	p\Online\PrevPos 	= Vector(0, -999999, 0) : p\Online\PrevRot 	= Vector(0, -999999, 0)
	p\Online\Pos 		= Vector(0, -999999, 0) : p\Online\Rot 		= Vector(0, -999999, 0)
	p\Online\CurrentPos = Vector(0, 1, 0) 		: p\Online\CurrentRot = Vector(0, 90, 0)
	; online player values
	p\Online\Name$ = PName$ 
	p\Online\NetID = PID% 			
	p\Online\Connected = True	
	p\Online\No=no	
	p\Online\IsLocal=localplayer
	p\Online\ShowTag=True
	p\Online\Joined=False
	p\Online\PrevColorR=255:p\Online\PrevColorG=255:p\Online\PrevColorB=255
	p\Online\PrevColorR= rnd (75,255):p\Online\PrevColorG=rnd(75,255):p\Online\PrevColorB=rnd(75,255)
	; non-local players will get things local won't.
	If p\Online\IsLocal=False Then		
		EntityType(p\Objects\Entity, 0)
		p\Online\CamPivot=CreatePivot()
		p\Online\Camera = CreateCamera(p\Online\campivot):HideEntity(p\Online\Camera)
		p\Online\Collision = CreateCylinder():EntityAlpha(p\Online\Collision,0)	
	EndIf		
	; name the player by their ID.
	NameEntity(p\Objects\Entity, p\Online\NetID)
	DebugLog("ID No:"+EntityName(p\Objects\Entity) + " " + p\Online\Name) ; debugging
	; tag bubble for Tag Game Mode. (will change to a sprite circle.)
	;p\Online\TagBubble = CreateSphere(12, p\Objects\Mesh)
	;Textures_Shield					= LoadTexture("Textures/shield.png", 1+2)
	;EntityTexture(p\Online\TagBubble, Textures_Shield)
	;ScaleEntity(p\Online\TagBubble, 7.5, 7.5, 7.5)
	;EntityAlpha(p\Online\TagBubble, 0.45)
	;EntityBlend(p\Online\TagBubble, 3)
	;EntityFx(p\Online\TagBubble, 1)
End Function

; =========================================================================================================
; Find Player's Data
; =========================================================================================================
Function FindPlayerData.tPlayer(ID)
	For p.tPlayer = Each tPlayer
		If p\Online\NetID = ID Then Return p
	Next
End Function

; same as above but returns as object.
Function GetPlayerID(id)
	; return the handle of the player stored in the field item\id
	For p.tPlayer = Each tPlayer
		If p\online\netid = id
			Return Handle(p)					
		EndIf
	Next	
End Function

function HasEveryoneFinishedTheRace()
	
	t = 0
	If Menu\Mission=MISSION_RIVAL#
		For ppp.tPlayer = Each tPlayer
			Select ppp\Action:
				Case ACTION_DIE: t=t+1
			End Select
		Next
		If t = BP_GetNumberOfPlayers%()-1 Return True Else Return False
	EndIf
	For p.tPlayer = Each tPlayer
		t = t + p\Online\FinishedRace
	Next
	If Game\Online\RaceLimit And t>0 And (Not Game\Online\Countdown>0) And Game\Online\Hosting Then Return True
	If t = BP_GetNumberOfPlayers%() Return True Else Return False

end function 
function HasEveryoneJoined()
	t = 0
	For p.tPlayer = Each tPlayer
		t = t + p\Online\Joined
	Next
	DrawRealText("Player count: " + BP_NumPlayers + " readycount:" + t, GAME_WINDOW_W-17.5*GAME_WINDOW_SCALE#, GAME_WINDOW_H-+64+(15*GAME_WINDOW_SCALE#+(1*20*GAME_WINDOW_SCALE#)), (Interface_Text_2), 2,0,255,255,255,1)
	If t = BP_NumPlayers Return True Else Return False

end function 
; =========================================================================================================
; Kick or Ban a Player
; =========================================================================================================
Function KickPlayer(pname$, Ban%=False, kickall%=False)
	; will kick everyone
	If kickall%=true then
		Local kickcount%=1
		Repeat
			kickcount=kickcount+1 : nInfo.NetInfo = BP_FindID(kickcount) 
			If nInfo<>Null Then BP_KickID(kickcount, False) : Exit
		Until BP_GetMaxPlayers()=1
		kickcount%=0:kickall%=false : Return ;reset and be done
	EndIf 
	 ;will kick a single person
	If pname$=Menu\PlayerName$ then Info("You can't kick yourself.", 255, 0, 255) : Return
	For p.tPlayer = Each tPlayer
		;if p\Online\NetID<>BP_My_ID then ; it's not for you
		If p\Online\Name=pname$ Then
			ID=p\Online\NetID
			nInfo.NetInfo = BP_FindID(ID)

			If nInfo<>Null Then
				If Ban%=True Then Info("**You banned: " + p\Online\Name, 255, 0, 0)
				If Ban%=False Then Info("**You kicked: " + p\Online\Name, 255, 153, 0)
				BP_KickID(ID, Ban%)
			Else
				Info("Player non existent, or was kicked/banned.", 255, 0, 255)
				Return
			EndIf

		End If
	Next		


End Function


; =========================================================================================================
; Change Player Name
; =========================================================================================================
Function Player_ChangeName(NewName$)
	if NewName$="/changename" or NewName$="/nickname" then NewName$=""
	p.tPlayer = First tPlayer
	;nInfo.NetInfo = BP_FindID(p\Online\NetID)
	OldName$=Menu\PlayerName$ ; keep in case
	Menu\PlayerName$=Left(NewName$,15) : PlayerName$=Left(NewName$,15) : p\Online\Name$=Left(NewName$,15) ; old to now
	;nInfo\Name=NewName$+"/"+chars$
	Info("Your Name is "+Left(NewName$,15),255, 0, 255)
	BP_UDPMessage(0,3, Left(NewName$,15)) ; send new name
	BP_UDPMessage(0, UDPMSG_MESSAGE, OldName$+" Is Now, "+Left(NewName$,15)) ; tell everyone
end function

	Function Player_SaveCheckpoint(p.tPlayer, x#=0, y#=0, z#=0, yaw#=0)
		Game\Gameplay\CheckX#=x#
		Game\Gameplay\CheckY#=y#
		Game\Gameplay\CheckZ#=z#
		Game\Gameplay\CheckDirection#=yaw#
		Game\Gameplay\CheckScore=Game\Gameplay\Score
		Game\Gameplay\CheckTime=Game\Gameplay\Time
		Game\Gameplay\CheckEnemies=Game\Gameplay\Enemies
		Game\Gameplay\CheckGoldEnemies=Game\Gameplay\GoldEnemies
		Game\Gameplay\CheckBalloons=Game\Gameplay\Balloons
		Game\Gameplay\CheckMusicMode=Game\Stage\Properties\MusicMode
	End Function
; =========================================================================================================
; Warp To Other Player
; =========================================================================================================
Function TeleportToPlayer(name$)
	if name$=Menu\PlayerName$ then Return
	for p.tPlayer = Each tPlayer
		if p\Online\Name$=name$ then
			if p=Null then Return
			BP_UDPMessage(0,3,"teleport")	
			EntityType(onlineplayer(1)\Objects\Entity, 0)			
			PositionEntity(onlineplayer(1)\Objects\Entity, EntityX(p\Objects\Entity), EntityY(p\Objects\Entity), EntityZ(p\Objects\Entity))
			EntityType(onlineplayer(1)\Objects\Entity, COLLISION_PLAYER)
			PlaySmartSound(Sound_Teleport)
		else
			Info("Player doesn't exist", 255, 0, 255)
			Return	
		endif
	next
end function
; view other players
Function ViewOtherPlayer(p.tPlayer, c.tCamera)
	if p<>null And p\Online\Name$=Game\Online\ViewName$ then
		if Game\Online\ViewPlayer=True And onlineplayer(1)\Online\Name$=Game\Online\ViewName$ then Game\Online\ViewPlayer=False : Game\Online\ViewName$="" : Info("You can't view yourself.", 255,11,255)
		if Game\Online\ViewPlayer=True then		
			ShowEntity(p\Online\Camera)
			Rect(GAME_WINDOW_W-300, GAME_WINDOW_H-300, 256*GAME_WINDOW_SCALE#, 256*GAME_WINDOW_SCALE#,0)
			CameraViewport(p\Online\Camera, GAME_WINDOW_W-300, GAME_WINDOW_H-300, 256*GAME_WINDOW_SCALE#, 256*GAME_WINDOW_SCALE#)
			CameraViewPort(c\Entity, 0, 0,GAME_WINDOW_W, GAME_WINDOW_H)
		Endif
	EndIf
	If Game\Online\ViewPlayer=False
		For p.tPlayer = Each tPlayer
		if p\Online\Camera>0 then HideEntity(p\Online\Camera)
		if p\Online\Camera>0 then CameraViewPort(p\Online\Camera, 0, 0, 0, 0)
		CameraViewPort(c\Entity, 0, 0, GAME_WINDOW_W, GAME_WINDOW_H)
		Next
	endif 	
End Function
; bring all players to host
Function Player_BringAllToHost()
	BP_UDPMessage(0,3,"teleport all")
	for p.tPlayer = Each tPlayer	
		if p<>null and p\Online\NetID<>BP_My_ID then
			EntityType(p\Objects\Entity, 0)			
			PositionEntity(p\Objects\Entity, EntityX(onlineplayer(1)\Objects\Entity)+Rand(-10, 10), EntityY(onlineplayer(1)\Objects\Entity), EntityZ(onlineplayer(1)\Objects\Entity)+Rand(-5, 5))
			EntityType(p\Objects\Entity, COLLISION_PLAYER)
		endif
	next
	PlaySmartSound(Sound_Teleport)
end function



; ============================================
; Set Tag Settings
; ============================================
Function Player_SetTagMode(p.tPlayer)
	p\Online\TagMode = TAG_IS_IT 
	p\Online\TagTimer=TAG_TIMER
	p\Online\TagCoolDown=3.5*secs#
	Player_PlayDieVoice(p)
	BP_UDPMessage(0, UDPMSG_TAGVALUES, p\Online\TagMode+"/"+p\Online\TagTimer+"/"+p\Online\TagCoolDown)							
	BP_UDPMessage(p\Online\NetID, 3, "tagged")	: BP_UDPMessage(0, UDPMSG_MESSAGE, p\Online\Name$+" is it!")	
End Function

Function GetClosestPlayer.tPlayer(distance# = 20) 
	glb = false;
	entity = pp(1)\Objects\Entity
	For p.tPlayer = Each tPlayer
		if p <> pp(1) Then
			target = p\Objects\Entity
			If (Abs(EntityX(entity,glb) - EntityX(target,glb)) < distance#) And (Abs(EntityY(entity,glb) - EntityY(target,glb)) < distance#) And (Abs(EntityZ(entity,glb) - EntityZ(target,glb)) < distance#) Then
				return p
			EndIf
		EndIf
	Next
End Function
Function GetStageNo%(stagenamed$)
	j=1
	For i=0 to StageAmount
		If StageName$(i)=stagenamed$ and j=1 Then j=i
	Next
	Return j
End Function

; draw players name tag and number.
Function DrawPlayerTag(Cam%, p.tPlayer, label$, no=1, height#=3, r=255, g=255, b=255)
	If EntityInView(p\Objects\Mesh, cam%) Then
  		CameraProject(Cam%, EntityX (p\Objects\Mesh), EntityY (p\Objects\Mesh)+height#, EntityZ (p\Objects\Mesh))
  		x = ProjectedX () - 1
  		y = ProjectedY () - 64-(GetCharScaleFactor(p\RealCharacter))
 		StartDraw()	
		; Setup rendering methods
		SetBlend(FI_ALPHABLEND)
		SetAlpha(1.0)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(r, g, b)
		DrawImageEx(INTERFACE(Interface_Indicator), x, y)
		SetColor(255, 255, 255)
		DrawRealText(label$, x, y, Interface_TextTitle_1, 1, 0, 63, 63, 63, 1.65)
		Select Game\Online\GameType
			Case GAME_TYPE_TAG:
				Select p\Online\TagMode:
					Case TAG_IS_IT: DrawRealText("IT", x, y-64, Interface_TextControls_1, 1, 0, 255, 128, 64, 0)
				End Select
			Case GAME_TYPE_RACE:
				Select p\Online\RacePosition:
					Case 1:
						suffix$="st" : rp=255 : gp=255 : bp=64
					Case 2:
						suffix$="nd" : rp=164 : gp=164 : bp=172
					Case 3:
						suffix$="rd" : rp=203 : gp=152 : bp=64
					Default:
						suffix$="th" : rp=255 : gp=128 : bp=64
				End Select
				If p\Online\FinishedRace=1 Then
					SetColor(rp, gp, bp)
					DrawRealText(p\Online\RacePosition+suffix$, x, y-64, Interface_TextControls_1, 1, 0, rp, gp, bp)
					SetColor(255, 255, 255)
					DrawRealText(p\Online\RaceTimer/60000+":"+(p\Online\RaceTimer/1000 Mod 60)+"."+(p\Online\RaceTimer/10 Mod 100), x, y-32, Interface_TextControls_1, 1, 0, rp, gp, bp)
				EndIf
		End Select
		EndDraw()
	EndIf
End Function

Function Game_OnlineMsgOfTheDay()
		If Game\Online\ShowMsg=False Then
				If Game\Online\Hosting=True Then
					SeedRnd(MilliSecs())
					Select Rand(1, 14)
						Case 1: Game\Online\MsgOfTheDay$="Rev up those fryers!"
						Case 2: Game\Online\MsgOfTheDay$="FIND THE COMPUTER ROOM!"
						Case 3: Game\Online\MsgOfTheDay$="Always eat your vegetables."
						Case 4: Game\Online\MsgOfTheDay$="Stay in drugs, Don't do School."
						Case 5: Game\Online\MsgOfTheDay$="Chaos Control!!"
						Case 6: Game\Online\MsgOfTheDay$="Is the Ocean salty because we don't Wave back?"
						Case 7: Game\Online\MsgOfTheDay$="Run Barry, Run!!"
						Case 8: Game\Online\MsgOfTheDay$="Where's that damn FOURTH Chaos Emerald!"
						Case 9: Game\Online\MsgOfTheDay$="Welcome to Sonic World Online! Have fun!"
						Case 10: Game\Online\MsgOfTheDay$="You are now entering the Twighlight Zone..."
						Case 11: Game\Online\MsgOfTheDay$="We Don't Like You Here."
						Case 12: Game\Online\MsgOfTheDay$="Piccolo: The Balls Are Inert!"
						Case 13: Game\Online\MsgOfTheDay$="Reach for the sky!!"
						Case 14: Game\Online\MsgOfTheDay$="When I play, I must say UHUL!"
					End Select
					Info(Game\Online\MsgOfTheDay$, 0,255,255)
					BP_UDPMessage(0,25, Game\Online\MsgOfTheDay$)
				EndIf
				Game\Online\ShowMsg=True
			EndIf
	End Function

	Function Player_ResetGamemodeValues(p.tPlayer)
		Game\Victory=0
		Game\Online\GTState=0
		Gameplay_SetRings(0)
		If Game\Gameplay\Flickies>0 Then
			Gameplay_SetFlickies(0)
			For o.tObject = Each tObject
				If o\ObjType=OBJTYPE_FLICKY Then o\State=-1
			Next
		EndIf
		Game\Gameplay\Time=0
		Game\Shield = 0
		Game\HurtWithoutShield = 0
		Stage_ResetStageMusic()
		Game\Gameplay\CheckX#=Game\Stage\Properties\StartX#
		Game\Gameplay\CheckY#=Game\Stage\Properties\StartY#
		Game\Gameplay\CheckZ#=Game\Stage\Properties\StartZ#
		Game\Gameplay\CheckDirection#=Game\Stage\Properties\StartDirection#
		Game\Gameplay\CheckScore=Game\Gameplay\Score
		Game\Gameplay\CheckTime=Game\Gameplay\Time
		Game\Gameplay\CheckEnemies=Game\Gameplay\Enemies
		Game\ResetCamera=1
		Game\ResetChecks=1
		Game\ResetObjects=1
		Objects_Reset_All()
		PostEffect_Create_FadeIn(0.008, 255, 255, 255)
		Player_SetPosition(p,Game\Stage\Properties\StartX#,Game\Stage\Properties\StartY#+7,Game\Stage\Properties\StartZ#,Game\Stage\Properties\StartDirection#)
		PlaySmartSound(Sound_Warp)
		Player_ResetDuringGameValues()
		Select Game\Online\GameType
			Case GAME_TYPE_RACE
			
				DebugLog("Resetting race")
				Menu_GoToStage_SetMission(1)
				If Menu\Mission=MISSION_HUNT# Or Menu\Mission=MISSION_BOSS# Then Menu\Mission=MISSION_FREEROAM#
				p\Online\FinishedRace=0
				p\Online\RacePosition=0
				Game\Online\RaceFinished=False
				DebugLog(Menu\Mission)
		End Select
	End Function

	Function Player_PVP(p.tPlayer, d.tDeltaTime)
		ppp.tPlayer=GetClosestPlayer(7)
		If ppp<>Null Then
			
			If ppp\Flags\Attacking And p\Flags\Attacking Then
				If ppp\Action=ACTION_STOMP Then rockP=true else rockP=false
				If p\Action=ACTION_STOMP Then rockE=true else rockE=false
				If ppp\Action=ACTION_CHARGE Or ppp\Action=ACTION_ROLL Or ppp\Action=ACTION_DRIFT Then paperP=true else paperP=false
				If p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or p\Action=ACTION_DRIFT Then paperE=true else paperE=false
				If rockP=false and paperP=false and ppp\Flags\Attacking Then scissorsP=true Else scissorsP=False
				If rockE=false and paperE=false and p\Flags\Attacking Then scissorsE=true Else scissorsE=False
				
				DebugLog("You" + p\Online\Name + " r:"+rockE+ " p:"+paperE+ " s:"+scissorsE)
				DebugLog("He" + ppp\Online\Name + " r:"+rockP+ " p:"+paperP+ " s:"+scissorsP)
				If (rockP and scissorsE) or (paperP and rockE) or (scissorsP and paperE) Then
					BP_UDPMessage(p\Online\NetID, 3, "hurt")
				ElseIf (rockP and paperE) or (paperP and scissorsE) or (scissorsP and rockE) Then
					BP_UDPMessage(ppp\Online\NetID, 3, "hurt")
				Else
					p\Motion\Ground=False : p\Action=ACTION_FALL : Player_SetSpeed(p,-1.75) : p\Motion\Speed\y#=0.4
					ppp\Motion\Ground=False : ppp\Action=ACTION_FALL : Player_SetSpeed(ppp,-1.75) : ppp\Motion\Speed\y#=0.4
				EndIf
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
				ParticleTemplate_Call(ppp\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
			ElseIf p\Flags\Attacking=False and (ppp\Flags\Attacking and (Not(ppp\Action=ACTION_JUMP))) Then
				BP_UDPMessage(p\Online\NetID, 3, "hurt")
				ParticleTemplate_Call(p\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
			ElseIf (p\Flags\Attacking and (Not(p\Action=ACTION_JUMP))) and ppp\Flags\Attacking=False Then
				BP_UDPMessage(ppp\Online\NetID, 3, "hurt")
				ParticleTemplate_Call(ppp\SmokeParticle, PARTICLE_PLAYER_CONTACTSPARK, p\Objects\Mesh, 1+p\ScaleFactor#*0.1)
			EndIf
		EndIf
	End Function
	Function PlayerForceBomb(p.tPlayer,throwtype)
		Select p\Character
				Case CHAR_MKN,CHAR_GME:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_ROCKET)
				Case CHAR_MPH:
					Object_Bomb_Create5(p, EntityX(p\Objects\HandL,1), EntityY(p\Objects\HandL,1), EntityZ(p\Objects\HandL,1), 0, p\Online\Rot\y#, 0, BOMB_MINION)
				Case CHAR_BAR:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+6, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_ICE, 0)
				Case CHAR_WAV:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_EXPLOSIVE, -0.125)
								Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_EXPLOSIVE, +0.125)
						Case 2: p\BoomerangAway=1 : Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+0.6, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_GEAR, 1)
					End Select
				Case CHAR_BEA:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_BOMB, +0.125)
				Case CHAR_SIL:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_BOX)
						Case 2: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_KNIFE)
					End Select
				Case CHAR_SHA:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_SPEAR)
						Case 2: p\ChaosControlActiveTimer=3*secs# : p\PsychoChargeTimer=5*secs#
								ParticleTemplate_Call(p\Particle, PARTICLE_PLAYER_PSYCHOGLOW, p\Objects\Mesh, 0, 0, 1, 0, 2)
					End Select
				Case CHAR_CHO:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_SPEARWATER)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_BLOB)
					End Select
				Case CHAR_SON:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_WIND)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#+360, 0, BOMB_HURRICANE)
					End Select
				Case CHAR_CHA:
					Select throwtype
						Case -1: 
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#-3,  p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_FLOWER, -1)
						Default: 
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_FLOWER, 0)
					End Select
				Case CHAR_CRE:
					Select throwtype
						Case -1: Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5,  p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_CHEESE, 0)
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_CHEESE)
						Case 2: Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+0.53, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_TYPHOON)
						Case 3: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_CHEESE)
					End Select
				Case CHAR_TAI:
				Select throwtype
						Case -1: 
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#-3,  p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_RING, -1)
						Default: 
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_RING, 0)
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_RING, -0.25)
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_RING, +0.25)
				End Select
				Case CHAR_ROU:
				Select throwtype
						Case -1: 
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#-3,  p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_HEART, -1)
						Default: 
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_HEART, 0)
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_HEART, -0.25)
							Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+5, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_HEART, +0.25)
				End Select
				Case CHAR_AMY:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+0.53, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_TYPHOON)
				Case CHAR_BIG:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_FROGGY)
				Case CHAR_ESP:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+2.3, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_BLADE)
				Case CHAR_MAR:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_BUBBLES)
						Case 2: p\BoomerangAway=1 : Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+0.6, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_BOOMERANG, 1)
					End Select
				Case CHAR_RAY:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+1.2, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_DART)
				Case CHAR_BLA:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_FLAME)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#+360, 0, BOMB_FIREBALL)
					End Select
				Case CHAR_KNU:
					Select throwtype
						Case 1: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#+360, 0, BOMB_FIREBALL)
						Case 2: Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+6, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_ROCK, 0)
					End Select
				Case CHAR_HBO:
					Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#+360, 0, BOMB_BIGBOMB)
				Case CHAR_NAC:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_BULLET, 1)
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_BULLET, 2)
				Case CHAR_STO:
					Select throwtype
						Case 1: Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+10, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_TIRE)
						Case 2: Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#+360, 0, BOMB_HURRICANE)
					End Select
				Case CHAR_JET:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+0.6, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_LEAF)
				Case CHAR_TDL:
					Object_Bomb_Create.tBomb(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#, 0, BOMB_CURSE)
				Case CHAR_COM:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_BULLET, 3)
				Case CHAR_MIG:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+6, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_ROCK, 0)
				Case CHAR_TIK:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+0.53, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_JUSTICE)
				Case CHAR_MET,CHAR_MT3:
					Object_Bomb_Create.tBomb(p, p\Online\Pos\x#, p\Online\Pos\y#+2.3, p\Online\Pos\z#, 0, p\Online\Rot\y#, 0, BOMB_SHOCK)
				Case CHAR_INF
					Object_Bomb_Create5(p, EntityX(p\Objects\HandR,1), EntityY(p\Objects\HandR,1), EntityZ(p\Objects\HandR,1), 0, p\Online\Rot\y#+360, 0, BOMB_CUBETRAIL)
			End Select
	End Function