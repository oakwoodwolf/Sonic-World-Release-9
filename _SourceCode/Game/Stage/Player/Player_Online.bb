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
	p\Online\PrevColorR=255:p\Online\PrevColorG=255:p\Online\PrevColorB=255
	p\Online\PrevColorR= rnd (75,255):p\Online\PrevColorG=rnd(75,255):p\Online\PrevColorB=rnd(75,255)
	; non-local players will get things local won't.
	If p\Online\IsLocal=False Then		
		EntityType(p\Objects\Entity, 0)
		p\Online\CamPivot=CreatePivot()
		p\Online\Camera = CreateCamera(p\Online\campivot):HideEntity(p\Online\Camera)
		p\Online\Collision = CreateCylinder():EntityAlpha(p\Online\Collision,0)
		EntityRadius(p\Online\Collision, 8.5, 8.5)		
		EntityType(p\Online\Collision, COLLISION_PLAYER)	
		EntityType(p\Objects\Mesh, COLLISION_OBJECT_GOTHRU)
	EndIf		
	; name the player by their ID.
	NameEntity(p\Objects\Entity, p\Online\NetID)
	DebugLog("ID No:"+EntityName(p\Objects\Entity) + " " + p\Online\Name) ; debugging
	; tag bubble for Tag Game Mode. (will change to a sprite circle.)
	p\Online\TagBubble = CreateSphere(16, p\Objects\Mesh)
	Textures_Shield					= LoadTexture("Textures/shield.png", 1+2)
	EntityTexture(p\Online\TagBubble, Textures_Shield)
	ScaleEntity(p\Online\TagBubble, 7.5, 7.5, 7.5)
	EntityAlpha(p\Online\TagBubble, 0.45)
	EntityBlend(p\Online\TagBubble, 3)
	EntityFx(p\Online\TagBubble, 1)
End Function

; =========================================================================================================
; Find Player's Data
; =========================================================================================================
Function FindPlayerData.tPlayer(ID)
	For p.tPlayer = Each tPlayer
		If p\Online\NetID = ID Then Return p
	Next
End Function

Function AreYouTheLocalPlayer(p.tPlayer)
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
	For p.tPlayer = Each tPlayer
		t = t * p\Online\FinishedRace
	Next
	If t = 0 Return False Else Return True

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
	If pname$=PlayerName$ then Info("You can't kick yourself.", 255, 0, 255) : Return
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
	OldName$=PlayerName$ ; keep in case
	PlayerName$=NewName$ : p\Online\Name$=NewName$ ; old to now
	;nInfo\Name=NewName$+"/"+chars$
	Info("Your Name is "+NewName$,255, 0, 255)
	BP_UDPMessage(0,3, NewName$) ; send new name
	BP_UDPMessage(0, UDPMSG_MESSAGE, OldName$+" Is Now, "+NewName$) ; tell everyone
end function


; =========================================================================================================
; Warp To Other Player
; =========================================================================================================
Function TeleportToPlayer(name$)
	if name$=PlayerName$ then Return
	for p.tPlayer = Each tPlayer
		if p\Online\Name$=name$ then
			if p=Null then Return
			BP_UDPMessage(0,3,"teleport")	
			EntityType(onlineplayer(1)\Objects\Entity, 0)			
			PositionEntity(onlineplayer(1)\Objects\Entity, entityx(p\Objects\Entity), entityy(p\Objects\Entity), entityz(p\Objects\Entity))
			EntityType(onlineplayer(1)\Objects\Entity, COLLISION_PLAYER)
			Channel_Teleport=PlaySound(Sound_Teleport)
		else
			Info("Player doesn't exist", 255, 0, 255)
			Return	
		endif
	next
end function

; bring all players to host
Function Player_BringAllToHost()
	BP_UDPMessage(0,3,"teleport all")
	for p.tPlayer = Each tPlayer	
		if p<>null and p\Online\NetID<>BP_My_ID then
			EntityType(p\Objects\Entity, 0)			
			PositionEntity(p\Objects\Entity, EntityX(onlineplayer(1)\Objects\Entity), EntityY(onlineplayer(1)\Objects\Entity), EntityZ(onlineplayer(1)\Objects\Entity))
			;EntityType(p\Objects\Entity, COLLISION_PLAYER)
		endif
	next
	Channel_Teleport=PlaySound(Sound_Teleport)
end function



; ============================================
; Set Tag Settings
; ============================================
Function Player_SetTagMode(p.tPlayer)
	p\Online\TagMode = TAG_IS_IT 
	p\Online\TagTimer=TAG_TIMER
	p\Online\TagCoolDown=MilliSecs()+3500
	BP_UDPMessage(0, UDPMSG_TAGVALUES, p\Online\TagMode+"/"+p\Online\TagTimer+"/"+p\Online\TagCoolDown)							
	BP_UDPMessage(p\Online\NetID, 3, "tagged")	: BP_UDPMessage(0, UDPMSG_MESSAGE, p\Online\Name$+" is it!")	
End Function

Function GetClosestPlayer.tPlayer(distance# = 20) 
	glb = false;
	entity = onlineplayer(1)\Objects\Entity
	For p.tPlayer = Each tPlayer
		if p <> onlineplayer(1) Then
			target = p\Objects\Entity
			If (Abs(EntityX(entity,glb) - EntityX(target,glb)) < distance#) And (Abs(EntityY(entity,glb) - EntityY(target,glb)) < distance#) And (Abs(EntityZ(entity,glb) - EntityZ(target,glb)) < distance#) Then
				return p
			EndIf
		EndIf
	Next
End Function


; draw players name tag and number.
Function DrawPlayerTag(Cam%, entity, label$, no=1, height#=3, r=255, g=255, b=255)
	If EntityInView(entity, cam%) Then
  		CameraProject(Cam%, EntityX (entity), EntityY (entity)+height#, EntityZ (entity))
  		x = ProjectedX () - 1
  		y = ProjectedY () - 64
 		StartDraw()	
		; Setup rendering methods
		SetBlend(FI_ALPHABLEND)
		SetAlpha(1.0)
		SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		SetColor(r, g, b)
		DrawImageEx(INTERFACE(Interface_Indicator), x, y)
		SetColor(255, 255, 255)
		DrawRealText(label$, x, y, Interface_TextTitle_1, 1, 0, 63, 63, 63, 1.65)
		EndDraw()
	EndIf
End Function

Function Game_OnlineMsgOfTheDay()
		If Game\Online\ShowMsg=False Then
				If Game\Online\Hosting=True Then
					SeedRnd(MilliSecs())
					Select Rand(1, 13)
						Case 1: Game\Online\MsgOfTheDay$="Rev up those fryers!"
						Case 2: Game\Online\MsgOfTheDay$="FIND THE COMPUTER ROOM!"
						Case 3: Game\Online\MsgOfTheDay$="Always eat your vegetables."
						Case 4: Game\Online\MsgOfTheDay$="Stay in drugs, Don't do School."
						Case 5: Game\Online\MsgOfTheDay$="Chaos Control!!"
						Case 6: Game\Online\MsgOfTheDay$="Is the Ocean salty because we don't Wave back?"
						Case 7: Game\Online\MsgOfTheDay$="Run Barry, Run!!"
						Case 8: Game\Online\MsgOfTheDay$="Where's that damn FOURTH Chaos Emerald!"
						Case 9: Game\Online\MsgOfTheDay$="Welcome to BlitzSonic Online! Have fun!"
						Case 10: Game\Online\MsgOfTheDay$="You are now entering the Twighlight Zone..."
						Case 11: Game\Online\MsgOfTheDay$="We Don't Like You Here."
						Case 12: Game\Online\MsgOfTheDay$="Piccolo: The Balls Are Inert!"
						Case 13: Game\Online\MsgOfTheDay$="Reach for the sky!!"
					End Select
					Info(Game\Online\MsgOfTheDay$, 0,255,255)
					BP_UDPMessage(0,25, Game\Online\MsgOfTheDay$)
				EndIf
				Game\Online\ShowMsg=True
			EndIf
	End Function