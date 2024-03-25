

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	Type tCamera
		; ---- Camera entity variables ----
		Field Entity
		Field Listener
		Field Filter

		; ---- Camera attributes in world space ----
		Field Position.tVector
		Field Rotation.tVector
		Field Alignment.tVector

		; ---- Camera values -----
		Field Mode
		Field DistanceFromCamera#
		Field DistanceFromTarget#
		Field FieldOfView#
		Field Underwater
		Field DontDoUnderwaterEffectsTimer

		; ---- Target values -----
		Field Target.tPlayer
		Field TargetPosition.tVector
		Field TargetRotation.tVector

		; ---- Lock values ----
		Field Lock.tCamera_Lock

		; --- Timers ---
		Field MouseCameraTimer

		; --- Other stuff ---
		Field ShouldCenterCamera
		Field ShouldCenterCameraDirection#
		Field ShouldCenterCameraTimer
		Field CinemaX#
		Field CinemaY#
		Field CinemaZ#
		Field CinemaSpeed#
		Field ClimbTimer
	End Type

	Type tCamera_Lock
		Field Pos
		Field Immediate
		Field PreviousPos
		Field Position.tVector
		Field Rotation.tVector
		Field Zoom#
		Field Speed#
		Field RealSpeed#
		Field Timer
		Field Turn
		Field PosMesh
		Field PosMeshTarget
		Field PosTimer
		Field OutOfLockTimer
		Field ChaoRaceCamOrder
		Field ChaoRaceCamTimer
		Field OnlyYawTimer
		Field NoBossCam
		Field CamLockedRightNow
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---- Camera modes -----
	Const CAMERA_MODE_NORMAL = 0
	Const CAMERA_MODE_TARGETPOV = 1
	
	; ---- Camera distance values -----
	Global CAMERA_ZOOMVALUE# = 21
	Global CAMERA_ACTUALZOOMVALUE# = 21
	Global CAMERA_DISTANCE_NEAR# = CAMERA_ZOOMVALUE#
	Global CAMERA_DISTANCE_FAR# = CAMERA_ZOOMVALUE#

	; ---- Camera field-of-view values -----
	Const CAMERA_FOV_SPINDASH# = 70
	Const CAMERA_FOV_NORMAL# = 50

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Function Camera_Create.tCamera()
		; ----- Create camera structure -----
		c.tCamera 	= New tCamera
			; Allocate vectors
			c\Position		 = Vector(0, 0, 0)
			c\Rotation		 = Vector(Gameplay_Camera_RotationX#, Gameplay_Camera_RotationY#, 0)
			c\Alignment		 = Vector(0, 1, 0)
			c\TargetPosition = Vector(0, 0, 0)
			c\TargetRotation = Vector(0, 0, 0)
			c\Lock = New tCamera_Lock
			c\Lock\Position = Vector(0, 0, 0)
			c\Lock\Rotation = Vector(0, 0, 0)
			c\Lock\PosMesh=CreatePivot()
			c\Lock\PosMeshTarget=CreatePivot()
			CAMERA_DISTANCE_NEAR#=CAMERA_ZOOMVALUE#
			CAMERA_DISTANCE_FAR#=CAMERA_ZOOMVALUE#
			c\CinemaSpeed#=1.5
			
			; Create camera and include initial setup
			c\Entity 	= CreateCamera(Game\Stage\Root)
			c\Listener  = CreateListener(c\Entity, .009, .5)
			c\Filter	= CreateFilterQuad(c\Entity)

				; Setup camera
				CameraSprite = CreateSprite(c\Entity)
				CameraZoom(c\Entity, CAMERA_FOV_NORMAL#)
				Select Menu\Settings\ViewRange#
					Case 1: Game\Others\CurrentCameraRange=4000
					Case 2: Game\Others\CurrentCameraRange=8000
					Case 3: Game\Others\CurrentCameraRange=12000
					Case 4: Game\Others\CurrentCameraRange=24000
					Default: Game\Others\CurrentCameraRange=8000
				End Select
				CameraRange(c\Entity, 1, Game\Others\CurrentCameraRange)
				CameraFogMode(c\Entity, 1)
				CameraFogColor(c\Entity, 170, 208, 255)
				CameraFogRange(c\Entity, 940, 1600)
				InitParticles(c\Entity)

				; Setup camera values
				c\Mode = CAMERA_MODE_TARGETPOV
				c\DistanceFromCamera# = ((CAMERA_DISTANCE_NEAR#+CAMERA_CONTROL_SIZEFACTOR#)+(CAMERA_DISTANCE_FAR#+CAMERA_CONTROL_SIZEFACTOR#))*0.5
				c\DistanceFromTarget# = c\DistanceFromCamera#
				c\FieldOfView# = CAMERA_FOV_NORMAL#
				
				; Setup camera collision
				EntityType(c\Entity, COLLISION_CAMERA)
				EntityRadius(c\Entity, 1.5)

				; Initializate FX Manager
				FxManager_SetCamera(c\Entity, 0.02)
				
		; ---- Done ----
		Return c

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

	Function Camera_Bind(c.tCamera, p.tPlayer)
		; ---- Attach player to camera -----
		c\Target = p
		p\Objects\Camera = c	
		; ---- Done -----
	End Function
	
	Function Camera_Update(c.tCamera, d.tDeltaTime)

		; ---- Don't do anything to the camera if there's no target.
		If (c\Target = Null) Then Return

		; ---- Camera rotation style ----
		; When the player pressed left or right, the camera rotates with them
		If Not(Game\CamLock>0 Or Game\CamLock2>0 Or c\Lock\PosTimer>0) Then
			c\Lock\CamLockedRightNow=False
			If (Not(Input\Hold\MouseCamUp Or Input\Hold\MouseCamDown Or Input\Hold\MouseCamLeft Or Input\Hold\MouseCamRight)) And Game\Interface\DebugPlacerOn=0 And Game\CinemaMode=0 And Menu\Settings\AutoCameraDisabled#=0 Then
				If Not(c\Target\Action=ACTION_CLIMB) Then RotationX# = Cos(Input\Movement_Direction)*Input\Movement_Pressure
				RotationY# = Sin(Input\Movement_Direction)*Input\Movement_Pressure

				c\TargetRotation\y# = c\TargetRotation\y#-(RotationX#*Gameplay_Camera_RotationSpeedX#*0.45*d\Delta)

				If (RotationY#>0.0) Then c\TargetRotation\y# = c\TargetRotation\y#-(RotationY#*Gameplay_Camera_RotationSpeedX#*0.7*d\Delta)*Sgn(Input\Movement_AnalogX#)
			Else
				c\TargetRotation\y# = c\TargetRotation\y#-Input\Camera_MouseAnalogX#*Gameplay_Camera_MouseRotationSpeedX#
				c\TargetRotation\x# = Clamp#(180+c\TargetRotation\x+Input\Camera_MouseAnalogY#*Gameplay_Camera_MouseRotationSpeedY#, 100, 260)-180
				c\MouseCameraTimer=0.6*secs#
			EndIf
		Else
			If Not(Game\CamLock2>0) Then c\Lock\CamLockedRightNow=True Else c\Lock\CamLockedRightNow=False
		EndIf

		; ---- Finally, change camera position around the target -----
		; Change position
		If Not((Game\CamLock>0 Or Game\CamLock2>0) And c\Lock\PosTimer>0) Then
			Vector_Set(c\TargetPosition, EntityX#(c\Target\Objects\Entity), EntityY#(c\Target\Objects\Entity), EntityZ#(c\Target\Objects\Entity))
			Vector_LinearInterpolation(c\Position, c\TargetPosition, Gameplay_Camera_Smoothness#*d\Delta)
			If c\MouseCameraTimer>0 Then
				Vector_LinearInterpolation(c\Rotation, c\TargetRotation, Gameplay_Camera_MouseSmoothness#*d\Delta)
			Else
				Vector_LinearInterpolation(c\Rotation, c\TargetRotation, Gameplay_Camera_Smoothness#*d\Delta)
			EndIf
		EndIf
		If c\Lock\PosTimer>0 Then c\Lock\PosTimer=c\Lock\PosTimer-timervalue#
		Vector_LinearInterpolation(c\Alignment, c\Target\Animation\Align, 0.05+Vector_Length(c\Target\Motion\Speed)*0.01*d\Delta)

		; Apply changes
		CameraZoom(c\Entity, 1/Tan#(c\FieldOfView#))
		;CameraZoom(c\Entity, 1/Tan#(c\FieldOfView#+((c\Target\SpeedLength#/(3.257532-0.575250))*d\Delta#)))

		Select Game\CinemaMode
			Case 0: PositionEntity(c\Entity, c\Position\x#, c\Position\y#+c\Target\ScaleFactor#*0.8, c\Position\z#, 1)
			Default: PositionEntity(c\Entity, c\CinemaX#, c\CinemaY#, c\CinemaZ#, 1)
		End Select
		If Menu\ChaoGarden=0 Or Menu\Stage=999 Then
			If Game\CamLock>5*secs# Or Game\RunLock>0 Or c\Target\Action=ACTION_DEBUG Or c\Target\Action=ACTION_GRIND Then
				EntityType(c\Entity, COLLISION_NONE)
			Else
				EntityType(c\Entity, COLLISION_CAMERA)
			EndIf
		EndIf
		RotateEntity(c\Entity, 0, 0, 0)
		
		If (c\Mode = CAMERA_MODE_TARGETPOV) Then
			If c\Target\Action=ACTION_CLIMB Or (c\Target\Action=ACTION_SPRINT And Player_CanWallKick(c\Target)) Then
				c\ClimbTimer=0.5*secs#
			Else
				If c\ClimbTimer>0 Then
					c\ClimbTimer=c\ClimbTimer-timervalue#
					Player_Align(c\Target)
					c\Target\Animation\Align\x# = c\Target\Motion\Align\x#
					c\Target\Animation\Align\y# = c\Target\Motion\Align\y#
					c\Target\Animation\Align\z# = c\Target\Motion\Align\z#
					Vector_LinearInterpolation(c\Alignment, c\Target\Animation\Align, 0.05+Vector_Length(c\Target\Motion\Speed)*0.01*d\Delta)
				Else
					AlignToVector(c\Entity, c\Alignment\x#, c\Alignment\y#, c\Alignment\z#, 2)
				EndIf
			EndIf
		EndIf

		If c\Lock\CamLockedRightNow Or (Menu\ChaoGarden=1 And Menu\Stage<>999) Then
			TurnEntity(c\Entity, c\TargetRotation\x#, c\TargetRotation\y#, c\TargetRotation\z#)
		Else
			TurnEntity(c\Entity, c\TargetRotation\x#+pp(1)\ScaleFactor#*2.5, c\TargetRotation\y#, c\TargetRotation\z#)
		EndIf
		MoveEntity(c\Entity, 0, c\DistanceFromCamera#*0.25, -c\DistanceFromCamera#)

		;Extra control stuff
		Camera_Control(c,d)

		If Menu\ChaoGarden=1 And (Not(Menu\Stage=999)) Then CAMERA_CONTROL_SIZEFACTOR#=0
		Select c\Target\Action
			Case ACTION_CANNON,ACTION_CANNON2: c\DistanceFromCamera# = ((CAMERA_DISTANCE_NEAR#+20)+(CAMERA_DISTANCE_FAR#+20))*0.5
			Default: c\DistanceFromCamera# = ((CAMERA_DISTANCE_NEAR#+CAMERA_CONTROL_SIZEFACTOR#)+(CAMERA_DISTANCE_FAR#+CAMERA_CONTROL_SIZEFACTOR#))*0.5
		End Select
		c\DistanceFromTarget# = c\DistanceFromCamera#

		;Underwater detection
		If EntityY(c\Entity) < Game\Stage\Properties\WaterLevel-1 Or c\Target\UnderwaterTriggerTimer>0 Then
			If c\Target\Underwater=1 Or c\Target\UnderwaterFeet=1 Or EntityDistance(c\Entity,c\Target\Objects\Entity)>10 Then c\Underwater=1 Else c\Underwater=0
		Else
			c\Underwater=0
		EndIf

	End Function

	Function Camera_BossCamera(c.tCamera, p.tPlayer, entity)
		If Game\Victory=0 And (Not(c\Target\Action=ACTION_DIE)) And c\Lock\NoBossCam=0 Then
			If EntityDistance(entity,p\Objects\Entity)<300 Then
				Game\CamLock2 = 2*secs#
				c\Lock\OnlyYawTimer = 2*secs#
				c\Lock\Rotation\y#=EntityYaw(entity)+180
				c\Lock\Speed#=27.5/10.0
				c\Lock\PreviousPos=c\Lock\Pos
				c\Lock\Pos=0
				c\Lock\Immediate=0
			EndIf
		EndIf
		If Input\Pressed\CamCenter Then c\Lock\NoBossCam=Abs(c\Lock\NoBossCam-1)
	End Function