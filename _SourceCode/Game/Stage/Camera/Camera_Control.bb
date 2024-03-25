
	Const CAMERA_CONTROL_AIRPITCH#=5
	Const CAMERA_CONTROL_AIRAIRPITCH#=33
	Global CAMERA_CONTROL_SIZEFACTOR#=0

Function Camera_Control(c.tCamera,d.tDeltaTime)

		If c\MouseCameraTimer>0 Then c\MouseCameraTimer=c\MouseCameraTimer-timervalue#
		If c\Lock\OnlyYawTimer>0 Then c\Lock\OnlyYawTimer=c\Lock\OnlyYawTimer-timervalue#

		If c\TargetRotation\x#>360 Then c\Rotation\x#=0 : c\TargetRotation\x#=0
		If c\TargetRotation\z#>360 Then c\Rotation\z#=0 : c\TargetRotation\z#=0
		If c\TargetRotation\x#<-360 Then c\Rotation\x#=0 : c\TargetRotation\x#=0
		If c\TargetRotation\z#<-360 Then c\Rotation\z#=0 : c\TargetRotation\z#=0
		If c\TargetRotation\y#<0 Then c\Rotation\y#=360 : c\TargetRotation\y#=360
		If c\TargetRotation\y#>360 Then c\Rotation\y#=0 : c\TargetRotation\y#=0

	If Game\CamLock>0 Or Game\CamLock2>0 Then

		c\ShouldCenterCamera=False

		If Not(c\Lock\Pos=1) Then
			If c\Lock\Immediate=0 Then
				If Not(c\Lock\OnlyYawTimer>0) Then
					If Abs(c\TargetRotation\x#-c\Lock\Rotation\x#) < c\Lock\Speed# Then c\Lock\RealSpeed#=Abs(c\TargetRotation\x#-c\Lock\Rotation\x#) Else c\Lock\RealSpeed#=c\Lock\Speed#
					If c\TargetRotation\x#>c\Lock\Rotation\x# Then c\TargetRotation\x#=c\TargetRotation\x#-c\Lock\RealSpeed#*d\Delta
					If c\TargetRotation\x#<c\Lock\Rotation\x# Then c\TargetRotation\x#=c\TargetRotation\x#+c\Lock\RealSpeed#*d\Delta

					If Abs(c\TargetRotation\z#-c\Lock\Rotation\z#) < c\Lock\Speed# Then c\Lock\RealSpeed#=Abs(c\TargetRotation\z#-c\Lock\Rotation\z#) Else c\Lock\RealSpeed#=c\Lock\Speed#
					If c\TargetRotation\z#>c\Lock\Rotation\z# Then c\TargetRotation\z#=c\TargetRotation\z#-c\Lock\RealSpeed#*d\Delta
					If c\TargetRotation\z#<c\Lock\Rotation\z# Then c\TargetRotation\z#=c\TargetRotation\z#+c\Lock\RealSpeed#*d\Delta
				Else
					If c\Target\Motion\Ground=False And (Not(c\Target\Action=ACTION_BUOY Or c\Target\Action=ACTION_TORNADO Or c\Target\BoardWaterTimer>0)) Then
						If Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRAIRPITCH#) < 0.301 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRAIRPITCH#) Else c\Lock\RealSpeed#=0.301
						If c\TargetRotation\x#<CAMERA_CONTROL_AIRAIRPITCH# Then c\TargetRotation\x#=c\TargetRotation\x#+c\Lock\RealSpeed#*d\Delta
					Else
						If Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRPITCH#) < 1.131 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRPITCH#) Else c\Lock\RealSpeed#=1.131
						If c\TargetRotation\x#>CAMERA_CONTROL_AIRPITCH# Then c\TargetRotation\x#=c\TargetRotation\x#-c\Lock\RealSpeed#*d\Delta
						If c\TargetRotation\x#<CAMERA_CONTROL_AIRPITCH# Then c\TargetRotation\x#=c\TargetRotation\x#+c\Lock\RealSpeed#*d\Delta
					EndIf
				EndIf

				If Abs(c\TargetRotation\y#-c\Lock\Rotation\y#)<180 Then c\Lock\Turn=+1 Else c\Lock\Turn=-1
				If Abs(c\TargetRotation\y#-c\Lock\Rotation\y#) < c\Lock\Speed# Then c\Lock\RealSpeed#=Abs(c\TargetRotation\y#-c\Lock\Rotation\y#) Else c\Lock\RealSpeed#=c\Lock\Speed#
				If c\TargetRotation\y#>c\Lock\Rotation\y# Then c\TargetRotation\y#=c\TargetRotation\y#-c\Lock\Turn*c\Lock\RealSpeed#*d\Delta
				If c\TargetRotation\y#<c\Lock\Rotation\y# Then c\TargetRotation\y#=c\TargetRotation\y#+c\Lock\Turn*c\Lock\RealSpeed#*d\Delta
			Else
				c\TargetRotation\x#=c\Lock\Rotation\x#
				c\TargetRotation\y#=c\Lock\Rotation\y#
				c\TargetRotation\z#=c\Lock\Rotation\z#
			EndIf
		EndIf

		If c\Lock\Immediate=0 Then
			If Not(c\Lock\OnlyYawTimer>0) Then
				If Abs(CAMERA_DISTANCE_NEAR#-c\Lock\Zoom#) < c\Lock\Speed# Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_NEAR#-c\Lock\Zoom#) Else c\Lock\RealSpeed#=c\Lock\Speed#
				If CAMERA_DISTANCE_NEAR#>c\Lock\Zoom# Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_NEAR#<c\Lock\Zoom# Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#+c\Lock\RealSpeed#*d\Delta
				If Abs(CAMERA_DISTANCE_FAR#-c\Lock\Zoom#) < c\Lock\Speed# Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_FAR#-c\Lock\Zoom#) Else c\Lock\RealSpeed#=c\Lock\Speed#
				If CAMERA_DISTANCE_FAR#>c\Lock\Zoom# Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_FAR#<c\Lock\Zoom# Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#+c\Lock\RealSpeed#*d\Delta
			EndIf
		Else
			CAMERA_DISTANCE_NEAR#=c\Lock\Zoom#
			CAMERA_DISTANCE_FAR#=c\Lock\Zoom#
		EndIf

		If c\Lock\Pos>0 Then
			If c\Lock\Immediate=0 Then
				c\Lock\PosTimer=0.1*secs#
				If EntityDistance(c\Lock\PosMesh,c\Lock\PosMeshTarget)<c\Lock\Speed# Then c\Lock\RealSpeed#=EntityDistance(c\Lock\PosMesh,c\Lock\PosMeshTarget) Else c\Lock\RealSpeed#=c\Lock\Speed#
				If EntityDistance(c\Lock\PosMesh,c\Lock\PosMeshTarget)>0 Then
					PointEntity(c\Lock\PosMesh,c\Lock\PosMeshTarget)
					MoveEntity c\Lock\PosMesh, 0, 0, c\Lock\RealSpeed#*d\Delta
				EndIf
				c\Position\x#=EntityX(c\Lock\PosMesh) : c\Position\y#=EntityY(c\Lock\PosMesh) : c\Position\z#=EntityZ(c\Lock\PosMesh)
				If c\Lock\Pos=1 Then PointEntity(c\Entity,c\Target\Objects\Entity)
			Else
				c\Position\x#=EntityX(c\Lock\PosMeshTarget) : c\Position\y#=EntityY(c\Lock\PosMeshTarget) : c\Position\z#=EntityZ(c\Lock\PosMeshTarget)
			EndIf
		Else
			If c\Lock\PreviousPos>0 And c\Lock\PosTimer>0 And EntityDistance(c\Lock\PosMesh,c\Target\Objects\Entity)>0 Then
				c\Lock\PosTimer=0.1*secs#
				If EntityDistance(c\Lock\PosMesh,c\Target\Objects\Entity)<c\Lock\Speed# Then c\Lock\RealSpeed#=EntityDistance(c\Lock\PosMesh,c\Target\Objects\Entity) Else c\Lock\RealSpeed#=c\Lock\Speed#
				PointEntity(c\Lock\PosMesh,c\Target\Objects\Entity)
				MoveEntity c\Lock\PosMesh, 0, 0, c\Lock\RealSpeed#*d\Delta
				c\Position\x#=EntityX(c\Lock\PosMesh) : c\Position\y#=EntityY(c\Lock\PosMesh) : c\Position\z#=EntityZ(c\Lock\PosMesh)
				If c\Lock\Immediate=1 Then
					c\Position\x#=EntityX(c\Lock\PosMeshTarget) : c\Position\y#=EntityY(c\Lock\PosMeshTarget) : c\Position\z#=EntityZ(c\Lock\PosMeshTarget)
				EndIf
				PointEntity(c\Entity,c\Target\Objects\Entity)
			EndIf
		EndIf

		c\Lock\OutOfLockTimer=2.25*secs#

	ElseIf Game\Victory=0 Then

		If c\Lock\OutOfLockTimer>0 Then
			c\Lock\OutOfLockTimer=c\Lock\OutOfLockTimer-timervalue#
			If c\Lock\PreviousPos>0 And c\Lock\PosTimer>0 Then
				If EntityDistance(c\Lock\PosMesh,c\Target\Objects\Entity)>1.5 Then
					c\Lock\PosTimer=0.1*secs#
					If EntityDistance(c\Lock\PosMesh,c\Target\Objects\Entity)<5+cam\Target\SpeedLength# Then c\Lock\RealSpeed#=EntityDistance(c\Lock\PosMesh,c\Target\Objects\Entity)+cam\Target\SpeedLength# Else c\Lock\RealSpeed#=5
					PointEntity(c\Lock\PosMesh,c\Target\Objects\Entity)
					MoveEntity c\Lock\PosMesh, 0, 0, c\Lock\RealSpeed#*d\Delta
					c\Position\x#=EntityX(c\Lock\PosMesh) : c\Position\y#=EntityY(c\Lock\PosMesh) : c\Position\z#=EntityZ(c\Lock\PosMesh)
					If c\Lock\Immediate=1 Then
						c\Position\x#=EntityX(c\Lock\PosMeshTarget) : c\Position\y#=EntityY(c\Lock\PosMeshTarget) : c\Position\z#=EntityZ(c\Lock\PosMeshTarget)
					EndIf
				Else
					c\Lock\OutOfLockTimer=0
				EndIf
			EndIf
		Else
			c\Lock\PosTimer=0
		EndIf

		If c\Lock\Immediate=1 Then
			c\TargetRotation\x#=15
			c\TargetRotation\y#=c\Target\Animation\Direction#
			c\TargetRotation\z#=0
			CAMERA_DISTANCE_NEAR#=CAMERA_ZOOMVALUE#
			CAMERA_DISTANCE_FAR#=CAMERA_ZOOMVALUE#
			c\Lock\Immediate=0
		EndIf

		If Abs(c\TargetRotation\z#-0) < 1 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\z#-0) Else c\Lock\RealSpeed#=1
		If c\TargetRotation\z#>0 Then c\TargetRotation\z#=c\TargetRotation\z#-c\Lock\RealSpeed#*d\Delta
		If c\TargetRotation\z#<0 Then c\TargetRotation\z#=c\TargetRotation\z#+c\Lock\RealSpeed#*d\Delta

		If Menu\ChaoGarden=0 Then
			If Abs(c\Target\Rotation#)<30 Then
				If Abs(CAMERA_DISTANCE_NEAR#-CAMERA_ZOOMVALUE#) < 1 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_NEAR#-CAMERA_ZOOMVALUE#) Else c\Lock\RealSpeed#=1
				If CAMERA_DISTANCE_NEAR#>CAMERA_ZOOMVALUE# Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_NEAR#<CAMERA_ZOOMVALUE# Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#+c\Lock\RealSpeed#*d\Delta
				If Abs(CAMERA_DISTANCE_FAR#-CAMERA_ZOOMVALUE#) < 1 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_FAR#-CAMERA_ZOOMVALUE#) Else c\Lock\RealSpeed#=1
				If CAMERA_DISTANCE_FAR#>CAMERA_ZOOMVALUE# Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_FAR#<CAMERA_ZOOMVALUE# Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#+c\Lock\RealSpeed#*d\Delta
			Else
				If Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#+4.75)) < 1 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#+4.75)) Else c\Lock\RealSpeed#=1
				If CAMERA_DISTANCE_NEAR#>CAMERA_ZOOMVALUE#+4.75 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_NEAR#<CAMERA_ZOOMVALUE#+4.75 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#+c\Lock\RealSpeed#*d\Delta
				If Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#+4.75)) < 1 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#+4.75)) Else c\Lock\RealSpeed#=1
				If CAMERA_DISTANCE_FAR#>CAMERA_ZOOMVALUE#+4.75 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_FAR#<CAMERA_ZOOMVALUE#+4.75 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#+c\Lock\RealSpeed#*d\Delta
			EndIf
		Else
			If c\Target\ObjPickUp=0 Then
				If Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#-3.125)) < 0.225 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#-6.25/2)) Else c\Lock\RealSpeed#=0.225
				If CAMERA_DISTANCE_NEAR#>CAMERA_ZOOMVALUE#-3.125 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_NEAR#<CAMERA_ZOOMVALUE#-3.125 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#+c\Lock\RealSpeed#*d\Delta
				If Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#-3.125)) < 0.225 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#-6.25/2)) Else c\Lock\RealSpeed#=0.225
				If CAMERA_DISTANCE_FAR#>CAMERA_ZOOMVALUE#-3.125 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#-c\Lock\RealSpeed#*d\Delta
				If CAMERA_DISTANCE_FAR#<CAMERA_ZOOMVALUE#-3.125 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#+c\Lock\RealSpeed#*d\Delta
			Else
				If c\Target\ObjPickUpTarget\ObjType=OBJTYPE_CHAO Then
					If Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#-8.25)) < 0.225 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#-6.25)) Else c\Lock\RealSpeed#=0.225
					If CAMERA_DISTANCE_NEAR#>CAMERA_ZOOMVALUE#-8.25 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#-c\Lock\RealSpeed#*d\Delta
					If CAMERA_DISTANCE_NEAR#<CAMERA_ZOOMVALUE#-8.25 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#+c\Lock\RealSpeed#*d\Delta
					If Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#-8.25)) < 0.225 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#-6.25)) Else c\Lock\RealSpeed#=0.225
					If CAMERA_DISTANCE_FAR#>CAMERA_ZOOMVALUE#-8.25 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#-c\Lock\RealSpeed#*d\Delta
					If CAMERA_DISTANCE_FAR#<CAMERA_ZOOMVALUE#-8.25 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#+c\Lock\RealSpeed#*d\Delta
				Else
					If Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#-6.25)) < 0.225 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_NEAR#-(CAMERA_ZOOMVALUE#-6.25)) Else c\Lock\RealSpeed#=0.225
					If CAMERA_DISTANCE_NEAR#>CAMERA_ZOOMVALUE#-6.25 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#-c\Lock\RealSpeed#*d\Delta
					If CAMERA_DISTANCE_NEAR#<CAMERA_ZOOMVALUE#-6.25 Then CAMERA_DISTANCE_NEAR#=CAMERA_DISTANCE_NEAR#+c\Lock\RealSpeed#*d\Delta
					If Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#-6.25)) < 0.225 Then c\Lock\RealSpeed#=Abs(CAMERA_DISTANCE_FAR#-(CAMERA_ZOOMVALUE#-6.25)) Else c\Lock\RealSpeed#=0.225
					If CAMERA_DISTANCE_FAR#>CAMERA_ZOOMVALUE#-6.25 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#-c\Lock\RealSpeed#*d\Delta
					If CAMERA_DISTANCE_FAR#<CAMERA_ZOOMVALUE#-6.25 Then CAMERA_DISTANCE_FAR#=CAMERA_DISTANCE_FAR#+c\Lock\RealSpeed#*d\Delta
				EndIf
			EndIf
		EndIf

		If Game\Interface\DebugPlacerOn=0 And Game\CinemaMode=0 Then
			If c\Target\Action=ACTION_DRIFT Or c\Target\Action=ACTION_BOARDDRIFT Or c\Target\Action=ACTION_CARDRIFT Then
				If c\Target\DriftDirection=-1 Then c\TargetRotation\y# = c\TargetRotation\y# + (0.538)*d\Delta
				If c\Target\DriftDirection=1 Then c\TargetRotation\y# = c\TargetRotation\y# - (0.538)*d\Delta
			ElseIf Input\Pressed\CamCenter Then
				c\ShouldCenterCamera=True
				c\ShouldCenterCameraDirection#=c\Target\Animation\Direction#+180
				CAMERA_ACTUALZOOMVALUE#=21
				
				Repeat
				If c\ShouldCenterCameraDirection#>360 Then c\ShouldCenterCameraDirection#=c\ShouldCenterCameraDirection#-360
				If c\ShouldCenterCameraDirection#<0 Then c\ShouldCenterCameraDirection#=c\ShouldCenterCameraDirection#+360
				Until c\ShouldCenterCameraDirection#<=360 And c\ShouldCenterCameraDirection#>=0
			Else
				If (Input\Hold\CamLeft) And (Input\Hold\CamRight) Then Input_ResetCameraInput()
				If (Input\Hold\CamLeft) Then
					If (Not(Input\Hold\CamRight)) Then c\TargetRotation\y#=c\TargetRotation\y#-3.3333*d\Delta
				EndIf
				If (Input\Hold\CamRight) Then
					If (Not(Input\Hold\CamLeft)) Then c\TargetRotation\y#=c\TargetRotation\y#+3.3333*d\Delta
				EndIf
			EndIf
		EndIf

		If Not(c\MouseCameraTimer>0) Then
			If c\Target\Motion\Ground=False And (Not(c\Target\Action=ACTION_BUOY Or c\Target\Action=ACTION_TORNADO Or c\Target\BoardWaterTimer>0)) Then
				If Not(c\Target\Action=ACTION_GLIDER) Then
					If Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRAIRPITCH#) < 0.301 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRAIRPITCH#) Else c\Lock\RealSpeed#=0.301
					If c\TargetRotation\x#<CAMERA_CONTROL_AIRAIRPITCH# Then c\TargetRotation\x#=c\TargetRotation\x#+c\Lock\RealSpeed#*d\Delta
				Else
					If Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRAIRPITCH#/6.0) < 0.301 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRAIRPITCH#/6.0) Else c\Lock\RealSpeed#=0.301
					If c\TargetRotation\x#<CAMERA_CONTROL_AIRAIRPITCH#/6.0 Then c\TargetRotation\x#=c\TargetRotation\x#+c\Lock\RealSpeed#*d\Delta
				EndIf
			Else
				If Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRPITCH#) < 1.131 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\x#-CAMERA_CONTROL_AIRPITCH#) Else c\Lock\RealSpeed#=1.131
				If c\TargetRotation\x#>CAMERA_CONTROL_AIRPITCH# Then c\TargetRotation\x#=c\TargetRotation\x#-c\Lock\RealSpeed#*d\Delta
				If c\TargetRotation\x#<CAMERA_CONTROL_AIRPITCH# Then c\TargetRotation\x#=c\TargetRotation\x#+c\Lock\RealSpeed#*d\Delta
			EndIf
		EndIf

		If c\ShouldCenterCamera Then
			If Input\Hold\CamCenter Then
				If Abs(c\TargetRotation\y#-c\ShouldCenterCameraDirection#)>5.5 Then c\ShouldCenterCameraTimer=0.2*secs#
				If c\ShouldCenterCameraTimer>0 Then c\ShouldCenterCameraTimer=c\ShouldCenterCameraTimer-timervalue#

				If Abs(c\TargetRotation\y#-c\ShouldCenterCameraDirection#)<180 Then c\Lock\Turn=+1 Else c\Lock\Turn=-1
				If Abs(c\TargetRotation\y#-c\ShouldCenterCameraDirection#) < 6.3333 Then c\Lock\RealSpeed#=Abs(c\TargetRotation\y#-c\ShouldCenterCameraDirection#) Else c\Lock\RealSpeed#=6.3333
				If c\TargetRotation\y#<c\ShouldCenterCameraDirection# Then c\TargetRotation\y#=c\TargetRotation\y#+c\Lock\Turn*c\Lock\RealSpeed#*d\Delta
				If c\TargetRotation\y#>c\ShouldCenterCameraDirection# Then c\TargetRotation\y#=c\TargetRotation\y#-c\Lock\Turn*c\Lock\RealSpeed#*d\Delta

				If Abs(c\TargetRotation\y#-c\ShouldCenterCameraDirection#)<=5 Or (Not(c\ShouldCenterCameraTimer>0)) Then c\ShouldCenterCamera=False
			Else
				c\ShouldCenterCamera=False
			EndIf
		EndIf

		If Input\Hold\MouseCamZoomIn Then
			If CAMERA_ACTUALZOOMVALUE#>5 Then CAMERA_ACTUALZOOMVALUE#=CAMERA_ACTUALZOOMVALUE#-2.5*d\Delta
		EndIf
		If Input\Hold\MouseCamZoomOut Then
			If c\Target\Action=ACTION_DEBUG Then
				If CAMERA_ACTUALZOOMVALUE#<350 Then CAMERA_ACTUALZOOMVALUE#=CAMERA_ACTUALZOOMVALUE#+2.5*d\Delta
			Else
				If CAMERA_ACTUALZOOMVALUE#<350 Then CAMERA_ACTUALZOOMVALUE#=CAMERA_ACTUALZOOMVALUE#+2.5*d\Delta
			EndIf
		EndIf
		If c\Target\Action=ACTION_TORNADO Then
			If Menu\Members=1 Then
				CAMERA_ZOOMVALUE#=CAMERA_ACTUALZOOMVALUE#+20+4*pp(1)\ScaleFactor#
			Else
				CAMERA_ZOOMVALUE#=CAMERA_ACTUALZOOMVALUE#+20+4*pp(2)\ScaleFactor#
			EndIf
		Else
			CAMERA_ZOOMVALUE#=CAMERA_ACTUALZOOMVALUE#
		EndIf

	EndIf

	If Game\CinemaMode=1 Then
		Game\StartoutLock=0
		Game\ControlLock=0
		Game\CamLock=0
		Game\RunLock=0

		If Input\Hold\Up Then c\CinemaZ#=c\CinemaZ#+Cos(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta : c\CinemaX#=c\CinemaX#-Sin(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta
		If Input\Hold\Down Then c\CinemaZ#=c\CinemaZ#-Cos(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta : c\CinemaX#=c\CinemaX#+Sin(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta

		If Input\Hold\Right Then c\CinemaZ#=c\CinemaZ#+Sin(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta : c\CinemaX#=c\CinemaX#+Cos(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta
		If Input\Hold\Left Then c\CinemaZ#=c\CinemaZ#-Sin(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta : c\CinemaX#=c\CinemaX#-Cos(EntityYaw(c\Entity))*c\CinemaSpeed#*d\Delta

		If Input\Hold\ActionJump Then c\CinemaY#=c\CinemaY#+c\CinemaSpeed#*d\Delta
		If Input\Hold\ActionRoll Then c\CinemaY#=c\CinemaY#-c\CinemaSpeed#*d\Delta

		If Input\Pressed\ActionAct Then
			Select c\CinemaSpeed#
				Case 0.5: c\CinemaSpeed#=1.5
				Case 1.5: c\CinemaSpeed#=3
				Case 3: c\CinemaSpeed#=7
				Case 7: c\CinemaSpeed#=0.5
			End Select
			PlaySmartSound(Sound_MenuMove)
		EndIf

		If Input\Pressed\ActionSkill2 Then
			Game\Interface\CinemaAllowUpdate=Abs(Game\Interface\CinemaAllowUpdate-1)
			PlaySmartSound(Sound_MenuMove)
		EndIf
	EndIf

	If Game\RunLock>0 Or Game\TwoDLock=1 Then c\Mode=CAMERA_MODE_NORMAL Else c\Mode=CAMERA_MODE_TARGETPOV

End Function