Function Object_Acquire_Position(o.tObject,x#,y#,z#)
	o\Position = New tVector
	o\Position\x# = x#
	o\Position\y# = y#
	o\Position\z# = z#
	o\InitialPosition = New tVector
	o\InitialPosition\x# = x#
	o\InitialPosition\y# = y#
	o\InitialPosition\z# = z#

	o\Particle = ParticleTemplate_Create.tParticleTemplate()
End Function

Function Object_Acquire_Rotation(o.tObject,pitch#,yaw#,roll#)
	o\HasRotation=True
	o\Rotation = New tVector
	o\Rotation\x# = pitch#
	o\Rotation\y# = yaw#
	o\Rotation\z# = roll#
	o\InitialRotation = New tVector
	o\InitialRotation\x# = pitch#
	o\InitialRotation\y# = yaw#
	o\InitialRotation\z# = roll#
End Function

Function Object_Acquire_Power(o.tObject,power#)
	o\Power# = power#
End Function

Function Object_Acquire_Lock(o.tObject,lockcontrol#,lockcam#,lockrun#)
	o\Translator\LockControl# = lockcontrol#
	o\Translator\LockCam# = lockcam#
	o\Translator\LockRun# = lockrun#
End Function

Function Object_Acquire_Camera(o.tObject,campos,camx#,camy#,camz#,campitch#,camyaw#,camroll#,camzoom#,camspeed#)
	o\Translator\CamPos=campos
	o\Translator\CamPosition = New tVector
	o\Translator\CamPosition\x# = camx#
	o\Translator\CamPosition\y# = camy#
	o\Translator\CamPosition\z# = camz#
	o\Translator\CamRotation = New tVector
	o\Translator\CamRotation\x# = campitch#
	o\Translator\CamRotation\y# = camyaw#
	o\Translator\CamRotation\z# = camroll#
	o\Translator\CamZoom# = camzoom#
	o\Translator\CamSpeed# = camspeed#
End Function

Function Object_Acquire_Speed(o.tObject,xspeed#,yspeed#,zspeed#)
	o\Speed = New tVector
	o\Speed\x# = xspeed#
   	o\Speed\y# = yspeed#
	o\Speed\z# = zspeed#
End Function

Function Object_Acquire_Destination(o.tObject,hasd,dx#,dy#,dz#)
	o\Translator\HasDestination=hasd
	o\Translator\Destination = New tVector
	o\Translator\Destination\x# = dx#
	o\Translator\Destination\y# = dy#
	o\Translator\Destination\z# = dz#
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Const HITBOXTYPE_NORMAL = 1
Const HITBOXTYPE_NORMAL_LASER = 11
Const HITBOXTYPE_NORMAL_SPIKEBAR = 12
Const HITBOXTYPE_NORMAL_SPIKESWING = 13
Const HITBOXTYPE_NORMAL_LOCKER = 14
Const HITBOXTYPE_SPEEDY = 2
Const HITBOXTYPE_SPEEDY_BSPRING = 20
Const HITBOXTYPE_SPEEDY_LOCKER = 21
Const HITBOXTYPE_SPEEDY_CHECKPOINT = 22
Const HITBOXTYPE_SPEEDY_TRANSFERER = 23
Const HITBOXTYPE_SPEEDY_POLE = 24
Const HITBOXTYPE_RING = 3
Const HITBOXTYPE_BOX = 4
Const HITBOXTYPE_ENEMY = 5
Const HITBOXTYPE_FAN = 6

Function Object_CreateHitBox(hittype,o.tObject,hitboxx#,hitboxy#,hitboxz#)
	o\HitType=hittype
	o\HitBox = New tVector
	o\HitBox\x# = hitboxx#
	o\HitBox\y# = hitboxy#
	o\HitBox\z# = hitboxz#
End Function

Function Object_CheckHitBox(o.tObject,p.tPlayer)
	If o\IsInBox=0 and o\Psychoed=0 and o\Rubied=0 and o\PsychoedThrown=False Then
	Select o\HitType
		Case HITBOXTYPE_NORMAL:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#) Then o\Hit=True Else o\Hit=False
		Case HITBOXTYPE_NORMAL_LASER:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf (Abs(p\Objects\Position\x# - (o\Position\x#-5*cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#-5*sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf (Abs(p\Objects\Position\x# - (o\Position\x#+5*cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#+5*sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_NORMAL_SPIKEBAR:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf o\Spike\BarSize>=1 And (Abs(p\Objects\Position\x# - (o\Position\x#-1*8*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#-1*8*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf o\Spike\BarSize>=1 And (Abs(p\Objects\Position\x# - (o\Position\x#+1*8*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#+1*8*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf o\Spike\BarSize>=2 And (Abs(p\Objects\Position\x# - (o\Position\x#-2*8*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#-2*8*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf o\Spike\BarSize>=2 And (Abs(p\Objects\Position\x# - (o\Position\x#+2*8*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#+2*8*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf o\Spike\BarSize>=3 And (Abs(p\Objects\Position\x# - (o\Position\x#-3*8*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#-3*8*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			ElseIf o\Spike\BarSize>=3 And (Abs(p\Objects\Position\x# - (o\Position\x#+3*8*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#+3*8*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_NORMAL_SPIKESWING:
			If (Abs(p\Objects\Position\x# - (o\Position\x#+23.2*Cos(o\Rotation\y#))) < o\HitBox\x#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(p\Objects\Position\z# - (o\Position\z#+23.2*Sin(o\Rotation\y#))) < o\HitBox\z#) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_NORMAL_LOCKER:
			Select o\Translator\LockerBType
				Case 0: lockerbsize# = 1
				Case 1: lockerbsize# = 2
				Case 2: lockerbsize# = 3.5
			End Select
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*lockerbsize#) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#*lockerbsize#) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*lockerbsize#) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_SPEEDY:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then o\Hit=True Else o\Hit=False
		Case HITBOXTYPE_SPEEDY_BSPRING:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True : o\Translator\BigSpringPoint=0
			ElseIf (Abs(p\Objects\Position\x# - (o\Position\x#-6.5*cos(o\Rotation\y#))) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - (o\Position\z#-6.5*sin(o\Rotation\y#))) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True : o\Translator\BigSpringPoint=-1
			ElseIf (Abs(p\Objects\Position\x# - (o\Position\x#+6.5*cos(o\Rotation\y#))) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - (o\Position\z#+6.5*sin(o\Rotation\y#))) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True : o\Translator\BigSpringPoint=1
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_SPEEDY_LOCKER:
			Select o\Translator\LockerBType
				Case 0: lockerbsize# = 1
				Case 1: lockerbsize# = 2
				Case 2: lockerbsize# = 3.5
			End Select
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*lockerbsize#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#*lockerbsize#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*lockerbsize#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_SPEEDY_CHECKPOINT:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			ElseIf o\Mode>1 And (Abs(p\Objects\Position\x# - o\Position\x#-6*Cos(o\Rotation\y#)) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#-6*Sin(o\Rotation\y#)) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			ElseIf o\Mode>1 And (Abs(p\Objects\Position\x# - o\Position\x#+6*Cos(o\Rotation\y#)) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#+6*Sin(o\Rotation\y#)) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			ElseIf o\Mode>2 And (Abs(p\Objects\Position\x# - o\Position\x#-12*Cos(o\Rotation\y#)) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#-12*Sin(o\Rotation\y#)) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			ElseIf o\Mode>2 And (Abs(p\Objects\Position\x# - o\Position\x#+12*Cos(o\Rotation\y#)) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#+12*Sin(o\Rotation\y#)) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_SPEEDY_TRANSFERER:
			If o\Hit and p\Action=ACTION_HOLD Then
				If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*4) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#*4) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*4) Then o\Hit=True Else o\Hit=False
			Else
				If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then o\Hit=True Else o\Hit=False
			EndIf
		Case HITBOXTYPE_SPEEDY_POLE:
			If (Abs(p\Objects\Position\x# - (o\Position\x#+7.5*Cos(o\Rotation\y#))) < o\HitBox\x#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#+(p\SpeedLength#/3.0)) And (Abs(p\Objects\Position\z# - (o\Position\z#+7.5*Sin(o\Rotation\y#))) < o\HitBox\z#+(p\SpeedLength#/3.0)) Then
				o\Hit=True
			Else
				o\Hit=False
			EndIf
		Case HITBOXTYPE_RING:
			If p\Action=ACTION_LIGHTDASH and (Not(o\ObjType=OBJTYPE_SPEWRING)) Then
				If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*3) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#*3) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*3) Then o\Hit=True Else o\Hit=False
			Else
				If p\SpeedLength#<5 Then
					If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*(1+p\ScaleFactor#)+(p\SpeedLength#/3.0)) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#*(1+p\ScaleFactor#)+(p\SpeedLength#/3.0)+2) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*(1+p\ScaleFactor#)+(p\SpeedLength#/3.0)) Then o\Hit=True Else o\Hit=False
				Else
					If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*(1+p\ScaleFactor#)+(p\SpeedLength#/3.0)*2) And (Abs(EntityY(p\Objects\Entity#) - o\Position\y) < o\HitBox\y#*(1+p\ScaleFactor#)+(p\SpeedLength#/3.0)*2+2) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*(1+p\ScaleFactor#)+(p\SpeedLength#/3.0)*2) Then o\Hit=True Else o\Hit=False
				EndIf
			EndIf
		Case HITBOXTYPE_BOX:
			If o\ThisIsABox Then
				If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*(1+p\ScaleFactor#/4.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#+4.5+2*o\Box\BoxType) And p\Objects\Position\y#>(o\Position\y#-2.2) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*(1+p\ScaleFactor#/4.0)) Then o\Hit=True Else o\Hit=False
			Else
				If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#*(1+p\ScaleFactor#/4.0)) And (Abs(p\Objects\Position\y# - o\Position\y#) < o\HitBox\y#+4.5) And p\Objects\Position\y#>(o\Position\y#-2.2) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#*(1+p\ScaleFactor#/4.0)) Then o\Hit=True Else o\Hit=False
			EndIf
		Case HITBOXTYPE_ENEMY:
			If o\Enemy\EnemyShallAppear and o\Enemy\WasKilledByBombMonitor=False Then Object_CheckEnemyHitBox(o,p)
		Case HITBOXTYPE_FAN:
			If (Abs(p\Objects\Position\x# - o\Position\x#) < o\HitBox\x#) And (p\Objects\Position\y# > o\Position\y#-5) And (p\Objects\Position\y# < o\Position\y#+o\HitBox\y#+20) And (Abs(p\Objects\Position\z# - o\Position\z#) < o\HitBox\z#) Then o\Hit=True Else o\Hit=False
	End Select
	Else
		o\Hit=False
	EndIf

	For ch.tCheese=Each tCheese
	If Game\CheeseTimer>0 and (o\ThisIsAMonitor Or (o\ThisIsAnEnemy and o\AttackDetectRestrict=False)) and (Abs(ch\Position\x# - o\Position\x#) < o\HitBox\x#) And (Abs(ch\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(ch\Position\z# - o\Position\z#) < o\HitBox\z#) Then o\CheeseHit=True Else o\CheeseHit=False
	Next

	For f.tFroggy=Each tFroggy
	If o\ThisIsAnEnemy Then
		If Game\FroggyTimer>0 and o\AttackDetectRestrict=False and (Abs(f\Position\x# - o\Position\x#) < o\HitBox\x#) And (Abs(f\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(f\Position\z# - o\Position\z#) < o\HitBox\z#) Then o\FroggyStunTimer=12*secs#
	Else
		If Game\FroggyTimer>0 and (o\ThisIsAMonitor Or (o\ThisIsAnEnemy and o\AttackDetectRestrict=False)) and (Abs(f\Position\x# - o\Position\x#) < o\HitBox\x#) And (Abs(f\Position\y# - o\Position\y#) < o\HitBox\y#) And (Abs(f\Position\z# - o\Position\z#) < o\HitBox\z#) Then o\FroggyHit=True Else o\FroggyHit=False
	EndIf
	Next

	Object_CheckHitBox_Bomb(o,p)
End Function

Function Object_CheckHitBox_Bomb(o.tObject,p.tPlayer)
	For b.tBomb=Each tBomb
	If b\MustDestroy=0 Then
		If o\ThisIsAMonitor Or (o\ObjType=OBJTYPE_BALLOON and (Not(o\State>0))) Or o\ObjType=OBJTYPE_SPIKEBOMB Or o\ObjType=OBJTYPE_SPIKECRUSHER Or o\ObjType=OBJTYPE_SWITCH Or (o\ObjType=OBJTYPE_SWITCHBASE and o\State=1) Or o\ObjType=OBJTYPE_SWITCHWATER Or o\ObjType=OBJTYPE_SWITCHAIR Or (o\ObjType=OBJTYPE_HOMMER and o\Mode=0) Then
			o\BombHittable=True
		ElseIf o\ThisIsAnEnemy Then
			If o\AttackDetectRestrict=False and o\Enemy\EnemyShallAppear Then o\BombHittable=True Else o\BombHittable=False
		ElseIf (o\ThisIsAnEnemyMissile Or o\ObjType=OBJTYPE_SPIKEBALL) and (b\BombType=BOMB_BUBBLES Or b\BombType=BOMB_TYPHOON) Then
			o\BombHittable=True
		ElseIf (o\ObjType=OBJTYPE_BOXIRON) and (b\BombType=BOMB_BUBBLES Or b\BombType=BOMB_TYPHOON) Then
			o\BombHittable=True
		ElseIf (o\ObjType=OBJTYPE_BOXWOODEN Or o\ObjType=OBJTYPE_BOXMETAL Or o\ObjType=OBJTYPE_BOXTNT Or o\ObjType=OBJTYPE_BOXNITRO) and (Not(b\BombType=BOMB_RING Or b\BombType=BOMB_NOTE Or b\BombType=BOMB_ICE)) Then
			o\BombHittable=True
		ElseIf (o\ObjType=OBJTYPE_BOXCAGE) and (b\BombType=BOMB_PSYCHIC Or b\BombType=BOMB_BOOMERANG Or b\BombType=BOMB_GEAR Or b\BombType=BOMB_TYPHOON Or b\BombType=BOMB_PUNCH Or b\BombType=BOMB_BULLET3 Or b\BombType=BOMB_BULLET2 Or b\BombType=BOMB_BULLET Or b\BombType=BOMB_ROCK Or b\BombType=BOMB_JUSTICE Or b\BombType=BOMB_HOOKSHOT Or b\BombType=BOMB_CUBETRAIL Or b\BombType=BOMB_BELLYFLOP) Then
			o\BombHittable=True
		ElseIf (o\ObjType=OBJTYPE_OMOCHAO Or o\ObjType=OBJTYPE_SWITCHTOP Or o\ObjType=OBJTYPE_CHAO Or o\ObjType=OBJTYPE_BELL) and (Not(b\BombType=-1 Or b\BombType=-2)) Then
			o\BombHittable=True
		ElseIf (o\ObjType=OBJTYPE_ROCK Or o\ObjType=OBJTYPE_CRYSTAL Or o\ObjType=OBJTYPE_AUTO Or o\ObjType=OBJTYPE_ICICLE Or o\ObjType=OBJTYPE_ICICLEBIG Or o\ObjType=OBJTYPE_ICEDECOR) and (Not(b\BombType=BOMB_RING Or b\BombType=BOMB_NOTE)) Then
			o\BombHittable=True
		Else
			o\BombHittable=False
		EndIf
	Else
		o\BombHittable=False
	EndIf

	If (Not(b\targetp=pp(1))) Or o\IsInBox Or p\Flags\Targeter=1 Or b\NotAffectable Then o\BombHittable=False

	If o\ThisIsABox and b\Position\y#<(o\Position\y#-2.2) Then o\BombHittable=False

	If o\ObjType=OBJTYPE_AUTO and b\Position\y#<o\Position\y# Then o\BombHittable=False

	If o\ThisIsAnEnemy Then
		Select o\ObjType
			Case OBJTYPE_FIGHTER,OBJTYPE_CAMERON,OBJTYPE_TYPHOON,OBJTYPE_TYPHOONF,OBJTYPE_CLUCKOID: If o\Anim=2 Then o\BombHittable=False
			;Case OBJTYPE_CRAWL,OBJTYPE_SHEEP: If o\Anim=3 Or o\Anim=4 Then o\BombHittable=False
			Case OBJTYPE_CATERKILLER: If o\Anim=1 Then o\BombHittable=False
			Case OBJTYPE_BOSSROBO: o\BombHittable=False
				;,OBJTYPE_MUSHMEANIE
			Case OBJTYPE_MADMOLE,OBJTYPE_BURROBOT,OBJTYPE_CRAWLER: If o\Anim=4 Then o\BombHittable=False
		End Select
	EndIf

	If o\BombHittable and o\BombHit=False and o\Psychoed=0 and o\Rubied=0 and o\ObjPickedUp=0 Then
		bombhitted=false
		Select b\BombType
			Case BOMB_BUBBLES:
				If o\ThisIsAnEnemy Or o\ThisIsAnEnemyMissile Or o\ThisIsABox Or o\ObjType=OBJTYPE_SPIKEBALL Or o\ObjType=OBJTYPE_SPIKEBOMB Or o\ObjType=OBJTYPE_SPIKECRUSHER Or o\ObjType=OBJTYPE_OMOCHAO Then
					If (Not(o\BubbleStunTimer>0)) and (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BubbleStunTimer=4.5*secs# : bombhitted=true
				Else
					If (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : bombhitted=true
				EndIf
			Case BOMB_BOOMERANG,BOMB_GEAR:
				If (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : b\TargetAssigned=False
			Case BOMB_CURSE:
				If o\ThisIsAnEnemy Or o\ThisIsAnEnemyMissile Or o\ThisIsABox Or o\ObjType=OBJTYPE_SPIKEBALL Or o\ObjType=OBJTYPE_SPIKEBOMB Or o\ObjType=OBJTYPE_SPIKECRUSHER Then
					If (Not(o\CurseStunTimer>0)) and (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\CurseStunTimer=5.5*secs# : bombhitted=true
				Else
					If (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : bombhitted=true
				EndIf
			Case BOMB_TYPHOON:
				If o\ThisIsAnEnemy Or o\ThisIsAnEnemyMissile Or o\ThisIsABox Or o\ObjType=OBJTYPE_SPIKEBALL Or o\ObjType=OBJTYPE_SPIKEBOMB Or o\ObjType=OBJTYPE_SPIKECRUSHER Or o\ObjType=OBJTYPE_OMOCHAO Then
					If (Not(o\FlowerStunTimer>0)) and (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\FlowerStunTimer=4.5*secs# : bombhitted=true
				Else
					If (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : bombhitted=true
				EndIf
			Case BOMB_ICE:
				If Not o\ThisIsAnEnemyMissile Then
				If o\ThisIsAnEnemy Then
					i=true
					If o\ThisIsAnEnemy Then
						If o\Enemy\IsBoss=1 Then i=false
					EndIf
					If i and (Not(o\FrozenStunTimer>0)) and (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then
						o\FrozenStunTimer=5.5*secs# : bombhitted=true
						EmitSmartSound(Sound_Bounce,o\Entity)
						Player_CreateRazer.tRazer(p,o\Entity,1,4,0.25*o\HitBox\x#,0.125*o\HitBox\y#,0.25*o\HitBox\z#,o\FrozenStunTimer,false)
					EndIf
				Else
					If (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : bombhitted=true
				EndIf
				EndIf
			Case BOMB_HOOKSHOT:
				i=true
				If o\ThisIsAnEnemy Then
					If o\Enemy\IsBoss=1 Then i=false
				EndIf
				If i and (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : bombhitted=true
			Default:
				If (Abs(b\Position\x# - o\Position\x#) < o\HitBox\x#+b\HitBox\x#) And (Abs(b\Position\y# - o\Position\y#) < o\HitBox\y#+b\HitBox\y#) And (Abs(b\Position\z# - o\Position\z#) < o\HitBox\z#+b\HitBox\z#) Then o\BombHit=True : bombhitted=true
		End Select
		If bombhitted Then
			If Not o\InView Then o\InView=True
			If (Not(b\BombType=BOMB_BOOMERANG Or b\BombType=BOMB_GEAR)) Then b\MustDestroy=1
			If o\ThisIsAnEnemy Then
				If o\Enemy\IsBoss=1 Then b\MustDestroy=1
			EndIf
			If b\BombType=BOMB_HOOKSHOT Then
				b\MayNotDestroy=1
				b\MoveTimer=0.5*secs#
				Object_Bomb_Create_Target(b, 1, o\Position\x#, o\Position\y#, o\Position\z#)
				Player_ConvertGroundToAir(b\targetp) : b\targetp\Motion\Ground = False
				MoveEntity b\targetp\Objects\Entity, 0, 5, 0
				b\targetp\Animation\Direction#=(DeltaYaw#(b\targetp\Objects\Entity,b\TargetPivot) - 180)
				b\targetp\BombThrown=0
				EmitSmartSound(Sound_GrindStart,b\Entity)
			EndIf
		EndIf

		If o\BombHit Then
			o\BombHitDirection#=EntityYaw(b\Pivot)
			o\BombHitType=b\BombType
		EndIf

		If b\TargetAssigned=False and o\GotAssignedBomb=False Then
			i=False
			If (Not(b\DisappearTimer=-100)) Then
				If EntityDistance(o\Entity,b\Entity)<b\HitBox\y#+20 Then i=True
			Else
				If EntityDistance(o\Entity,b\Entity)<b\HitBox\y#+5 Then i=True
			EndIf
			If i Then
				b\TargetAssignedObj=o
				b\TargetAssigned=True
				o\GotAssignedBomb=True
			EndIf
		EndIf
	EndIf
	Next
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_UpdatePosition(o.tObject)
	If o\OBJTYPE=OBJTYPE_SPEWRING Or o\OBJTYPE=OBJTYPE_SPEWREDRING Or o\HasGravity Then
		o\Position\x# = EntityX(o\Pivot,1) : o\Position\y# = EntityY(o\Pivot,1) : o\Position\z# = EntityZ(o\Pivot,1)
	Else
		o\Position\x# = EntityX(o\Entity,1) : o\Position\y# = EntityY(o\Entity,1) : o\Position\z# = EntityZ(o\Entity,1)
	EndIf
End Function

Function Object_UpdateRotation(o.tObject)
	If o\HasRotation Then
		o\Rotation\x# = EntityPitch(o\Entity,1) : o\Rotation\y# = EntityYaw(o\Entity,1) : o\Rotation\z# = EntityRoll(o\Entity,1)
	EndIf
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceGravity(o.tObject,d.tDeltaTime)

	If (Not(o\BubbleStunTimer>0)) And (Not(o\FlowerStunTimer>0)) And (Not(o\WhirlwindStunTimer>0)) And o\Psychoed=0 And o\Rubied=0 And o\ObjPickedUp=0 Then
		If o\HasGravity Then
			Gravity_Motion(o\Pivot,o\Entity,o\g,d,o\ObjPickedUp)
		Else
			MoveEntity o\Entity,0,-1.25*d\Delta,0
		EndIf
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforcePsychokinesis(o.tObject,p.tPlayer,d.tDeltaTime)
	If o\Rubied=0 Then
	Select o\Psychoed
		Case 0:
			Object_HandleGlow(o,p)
			If p\PsychokinesisTimer>0 and EntityDistance(o\Entity,p\Objects\Entity)<40 Then
				o\Psychoed=1
				o\PsychokineticX#=p\Objects\Position\x#-EntityX(o\Entity)
				o\PsychokineticY#=p\Objects\Position\y#-EntityY(o\Entity)
				o\PsychokineticZ#=p\Objects\Position\z#-EntityZ(o\Entity)
				Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			EndIf
		Case 1:
			If o\ThisIsAnEnemyMissile Then
				EntityTexture o\EntityX,Object_Texture_PsychoGlow,0,3
			Else
				EntityTexture o\Entity,Object_Texture_PsychoGlow,0,3
			EndIf
			PositionEntity o\Entity, p\Objects\Position\x#-o\PsychokineticX#, p\Objects\Position\y#-o\PsychokineticY#, p\Objects\Position\z#-o\PsychokineticZ#
			If p\PsychokinesisThrowTimer>0 Then
				Player_PlayAttackVoice(p)
				o\PsychoedThrown=True
				o\Psychoed=3
			Else
				If p\Psychokinesis=0 Then
					If o\ObjType=OBJTYPE_ENEMYMISSILE Then o\EnemyMissile\DisappearTimer=0
					o\Psychoed=0
				EndIf
			EndIf
		Case 3:
			Object_HandleGlow(o,p)
			PositionEntity o\Entity, p\Objects\Position\x#-o\PsychokineticX#, p\Objects\Position\y#-o\PsychokineticY#, p\Objects\Position\z#-o\PsychokineticZ#
			If EntityDistance(p\Objects\Entity,o\Entity)<10 Then
				If o\ObjType=OBJTYPE_OMOCHAO Then StopChannel(o\Omochao\Channel_Omochao) : o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Hurt[Rand(1,5)])
				Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
				Object_Bomb_Create.tBomb(p, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity), 0, p\Animation\Direction#-180, 0, BOMB_PSYCHIC, Game\Gameplay\PsychoBombCount)
				o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
				o\Psychoed=4
			Else
				If o\PsychokineticX>0 Then o\PsychokineticX#=o\PsychokineticX#-2*d\Delta
				If o\PsychokineticX<0 Then o\PsychokineticX#=o\PsychokineticX#+2*d\Delta
				If o\PsychokineticY>0 Then o\PsychokineticY#=o\PsychokineticY#-2*d\Delta
				If o\PsychokineticY<0 Then o\PsychokineticY#=o\PsychokineticY#+2*d\Delta
				If o\PsychokineticZ>0 Then o\PsychokineticZ#=o\PsychokineticZ#-2*d\Delta
				If o\PsychokineticZ<0 Then o\PsychokineticZ#=o\PsychokineticZ#+2*d\Delta
			EndIf
		Default:
			Object_HandleGlow(o,p)
			For b.tBomb=Each tBomb
			If o\ThrownAsBomb=b\ThrownMode Then
				b\ThrownAsBomb=True
				PositionEntity o\Entity, EntityX(b\Pivot), EntityY(b\Pivot), EntityZ(b\Pivot)
			EndIf
			Next
	End Select
	EndIf
End Function

Function Object_EnforceRubyGravity(o.tObject,p.tPlayer,d.tDeltaTime)
	If o\Psychoed=0 Then
	Select o\Rubied
		Case 0:
			Object_HandleGlow(o,p)
			If p\RubyGravityTimer>0 and EntityDistance(o\Entity,p\Objects\Entity)<40 Then
				o\Rubied=1
				o\RubiedTimer=0.5*secs#
				RotateEntity o\Entity, 0, EntityYaw(o\Entity), 180, 1
				If o\ThisIsAnEnemy Then MoveEntity o\Entity, 0, -MeshHeight#(o\Entity), 0
			EndIf
		Case 1:
			If o\ThisIsAnEnemyMissile Then
				EntityTexture o\EntityX,Object_Texture_RubyGlow,0,3
			Else
				EntityTexture o\Entity,Object_Texture_RubyGlow,0,3
			EndIf
			If o\RubiedTimer>0 Then
				o\RubiedTimer=o\RubiedTimer-timervalue#
				MoveEntity o\Entity, 0, -1.5*d\Delta, 0
			Else
				If o\ThisIsAnEnemyMissile Then
					o\EnemyMissile\DisappearTimer=0
				Else
					o\BombHit=True
				EndIf
			EndIf
	End Select
	EndIf
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_HandleGlow(o.tObject,p.tPlayer)
	If o\ThisIsAnEnemyMissile Then
		If p\ChaosControlActiveTimer>0 Then
			EntityTexture o\EntityX,Object_Texture_ChaosGlow,0,3
		ElseIf (Not(o\CurseStunTimer>0)) and (Not(o\FrozenStunTimer>0)) Then
			EntityTexture o\EntityX,Object_Texture_AntiGlow,0,3
		ElseIf (Not(o\CurseStunTimer>0)) and ((o\FrozenStunTimer>0)) Then
			EntityTexture o\EntityX,Object_Texture_IceGlow,0,3
		ElseIf ((o\CurseStunTimer>0)) and (Not(o\FrozenStunTimer>0)) Then
			EntityTexture o\EntityX,Object_Texture_CurseGlow,0,3
		EndIf
	ElseIf o\ThisIsAnEnemy Then
		If p\ChaosControlActiveTimer>0 Then
			EntityTexture o\Entity,Object_Texture_ChaosGlow,0,3
		ElseIf (Not(o\CurseStunTimer>0)) and (Not(o\FrozenStunTimer>0)) Then
			EntityTexture o\Entity,Object_Texture_AntiGlow,0,3
		ElseIf (Not(o\CurseStunTimer>0)) and ((o\FrozenStunTimer>0)) Then
			EntityTexture o\Entity,Object_Texture_IceGlow,0,3
		ElseIf ((o\CurseStunTimer>0)) and (Not(o\FrozenStunTimer>0)) Then
			EntityTexture o\Entity,Object_Texture_CurseGlow,0,3
		EndIf
	Else
		EntityTexture o\Entity,Object_Texture_AntiGlow,0,3
	EndIf
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceObjPickUp(o.tObject,p.tPlayer)
	pickable=true
	If o\ThisIsAChaoTargetableObj Then
		If o\ChaoObj\ChaoTargetedThis Then pickable=false
	EndIf

	If pickable Then
		If o\HasGravity=False Then
			Object_EnforceObjPickUp_Normal(o,p)
		Else
			Object_EnforceObjPickUp_Pivot(o,p)
		EndIf
	EndIf
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceObjPickUp_Normal(o.tObject,p.tPlayer)

	Select o\ObjPickedUp
		Case 0:
			If (o\ThisIsAMonitor=False and EntityDistance(o\Entity,p\Objects\Entity)<(o\HitBox\y#+2)) Or (o\ThisIsAMonitor=True and EntityDistance(o\Entity,p\Objects\Entity)<(o\HitBox\y#+5)) Then
				If p\ObjPickUpTimer>0 and p\ObjPickUp=0 Then
					Player_PlayJumpVoice(p)
					p\ObjPickUpTimer=0 : p\ObjPickUp=1 : p\ObjPickUpTarget=o
					p\Action=ACTION_CARRY
				EndIf
				If Menu\ChaoGarden=1 and Menu\Stage=999 and p\ObjPickUp=0 Then Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_PICKUP))
				Interface_ControlTipUpdate_PickUp(1)
			EndIf
			If p\ObjPickUp=1 and p\Action=ACTION_CARRY Then
			If (o\ThisIsAMonitor=False and EntityDistance(o\Entity,p\Objects\Entity)<(o\HitBox\y#+2)) Or (o\ThisIsAMonitor=True and EntityDistance(o\Entity,p\Objects\Entity)<(o\HitBox\y#+5)) Then
				o\ObjPickedUp=1
				p\ObjPickUp=3
			EndIf
			EndIf
		Case 1:
			If (Input\Hold\Up Or Input\Hold\Left Or Input\Hold\Right Or Input\Hold\Down) Or p\Motion\Ground=False Or Input\Hold\ActionSkill2 Then
				canthrow=true
				If Menu\ChaoGarden=1 Then Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_THROW))
				Interface_ControlTipUpdate_PickUp(2)
			Else
				canthrow=false
				If Menu\ChaoGarden=1 Then Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_DROP)+" ")
				Interface_ControlTipUpdate_PickUp(3)
			EndIf
			If (Not(p\Action=ACTION_CARRY Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_CARRYTHROWN)) Then p\ObjPickUp=0
			
			Select p\RealCharacter
				Case CHAR_EGG,CHAR_TMH:
					PositionEntity o\Entity, EntityX(p\Objects\Extra2, 1), EntityY(p\Objects\Extra2, 1), EntityZ(p\Objects\Extra2, 1)
					RotateEntity o\Entity, EntityPitch(p\Objects\Mesh), p\Animation\Direction#+180, EntityRoll(p\Objects\Mesh)
					MoveEntity o\Entity, 0, 1.7, 0.8
				Case CHAR_CHW:
					PositionEntity o\Entity, EntityX(p\Objects\HandR, 1), EntityY(p\Objects\HandR, 1), EntityZ(p\Objects\HandR, 1)
					RotateEntity o\Entity, EntityPitch(p\Objects\Mesh), p\Animation\Direction#+180, EntityRoll(p\Objects\Mesh)
					MoveEntity o\Entity, 0, 1.7, 0.8
				Default:
					PositionEntity o\Entity, EntityX(p\Objects\Spine, 1), (EntityY(p\Objects\HandR, 1)+EntityY(p\Objects\HandL, 1))/2.0, EntityZ(p\Objects\Spine, 1)
					RotateEntity o\Entity, EntityPitch(p\Objects\Mesh), p\Animation\Direction#+180, EntityRoll(p\Objects\Mesh)
					MoveEntity o\Entity, 0, 0, (2.05+p\ScaleFactor#*2.5)
			End Select
			If p\Rotation#<0 Then MoveEntity o\Entity, 0, -p\Rotation#*0.05, 0 Else MoveEntity o\Entity, 0, -p\Rotation#*0.025, 0
			If o\ObjType=OBJTYPE_CHAO Then
				PositionEntity o\ChaoObj\targetcc\Pivot, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity)
				RotateEntity o\ChaoObj\targetcc\Pivot, EntityPitch(o\Entity), EntityYaw(o\Entity), EntityRoll(o\Entity)
			EndIf
			If o\ThisIsAMonitor=True or o\ThisIsABox=True Then
				PositionEntity o\Entity, EntityX(p\Objects\Entity), EntityY(p\Objects\Entity)+8.5, EntityZ(p\Objects\Entity)
				RotateEntity o\Entity, 0, p\Animation\Direction#, 0
				PositionEntity o\EntityX, EntityX(p\Objects\Entity), EntityY(p\Objects\Entity)+8.5, EntityZ(p\Objects\Entity)
			EndIf
			If p\ObjPickUpThrowTimer>0 Then
				If canthrow Then
					Player_PlayAttackVoice(p)
					o\ObjPickedUp=3
				Else
					Player_PlayJumpVoice(p)
					o\ObjPickedUp=4 : Player_SetSpeed(p,0)
				EndIf
				p\ObjPickUp=0
			Else
				If p\ObjPickUp=0 Then o\ObjPickedUp=0
			EndIf
		Case -1:
			If o\Hit Then
				RotateEntity o\Entity, 0, p\Animation\Direction#+180, 0
				If o\ObjType=OBJTYPE_CHAO Then
					RotateEntity o\ChaoObj\targetcc\Pivot, 0, EntityYaw(o\Entity), 0
				EndIf
			Else
				RotateEntity o\Entity, 0, o\BombHitDirection#, 0
				If o\ObjType=OBJTYPE_CHAO Then
					RotateEntity o\ChaoObj\targetcc\Pivot, 0, EntityYaw(o\Entity), 0
				EndIf
			EndIf
			o\BombHitDirection#=0
			o\ObjPickedUp=3
			p\ObjPickUp=0
		Case 3:
			If (Not(p\Action=ACTION_CARRY Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_CARRYTHROWN)) Then p\ObjPickUp=0
			If o\ObjType=OBJTYPE_OMOCHAO Then StopChannel(o\Omochao\Channel_Omochao) : o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Hurt[Rand(1,5)])
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			Object_Bomb_Create.tBomb(p, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity), 0, EntityYaw(o\Entity), 0, -1, Game\Gameplay\PsychoBombCount)
			o\ObjPickedUp=9
			o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
		Case 4:
			If o\ObjType=OBJTYPE_OMOCHAO Then StopChannel(o\Omochao\Channel_Omochao) : o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Hurt[Rand(1,5)])
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			o\ObjPickedUp=0
		Case 5:
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			Object_Bomb_Create.tBomb(p, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity), 0, EntityYaw(o\Entity), 0, -1, Game\Gameplay\PsychoBombCount)
			o\ObjPickedUp=9
			o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
		Case 6:
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			Object_Bomb_Create.tBomb(p, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity), 0, EntityYaw(o\Entity), 0, -2, Game\Gameplay\PsychoBombCount)
			o\ObjPickedUp=9
			o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
		Case -2:
			If o\ChaoObj\ChaoTargetedThis=False Then o\ObjPickedUp=0
		Default:
			For b.tBomb=Each tBomb
			If o\ThrownAsBomb=b\ThrownMode Then
				b\ThrownAsBomb=True
				If o\ThisIsAMonitor=True Then
					PositionEntity o\Entity, EntityX(b\Pivot), EntityY(b\Pivot)+4.5, EntityZ(b\Pivot)
					PositionEntity o\EntityX, EntityX(b\Pivot), EntityY(b\Pivot)+4.5, EntityZ(b\Pivot)
				ElseIf o\ObjType=OBJTYPE_OMOCHAO Then
					PositionEntity o\Entity, EntityX(b\Pivot), EntityY(b\Pivot)+1.5, EntityZ(b\Pivot)
					PositionEntity o\EntityX, EntityX(b\Pivot), EntityY(b\Pivot)+1.5, EntityZ(b\Pivot)
				Else
				PositionEntity o\Entity, EntityX(b\Pivot), EntityY(b\Pivot), EntityZ(b\Pivot)
				EndIf
				If o\ObjType=OBJTYPE_CHAO Then
					PositionEntity o\ChaoObj\targetcc\Pivot, EntityX(o\Entity), EntityY(o\Entity), EntityZ(o\Entity)
					RotateEntity o\ChaoObj\targetcc\Pivot, EntityPitch(o\Entity), EntityYaw(o\Entity), EntityRoll(o\Entity)
					o\ChaoObj\targetcc\Action=CHAOACTION_THROWN
				EndIf
			EndIf
			Next			
	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceObjPickUp_Pivot(o.tObject,p.tPlayer)

	Select o\ObjPickedUp
		Case 0:
			If EntityDistance(o\Pivot,p\Objects\Entity)<(o\HitBox\y#+2) Then
				If p\ObjPickUpTimer>0 and p\ObjPickUp=0 Then
					Player_PlayJumpVoice(p)
					p\ObjPickUpTimer=0 : p\ObjPickUp=1 : p\ObjPickUpTarget=o
					p\Action=ACTION_CARRY
				EndIf
				If Menu\ChaoGarden=1 and Menu\Stage=999 and p\ObjPickUp=0 Then Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_PICKUP))
				Interface_ControlTipUpdate_PickUp(1)
			EndIf
			If p\ObjPickUp=1 and p\Action=ACTION_CARRY Then
			If EntityDistance(o\Pivot,p\Objects\Entity)<(o\HitBox\y#+2) Then
				o\ObjPickedUp=1
				p\ObjPickUp=3
			EndIf
			EndIf
		Case 1:
			If (Input\Hold\Up Or Input\Hold\Left Or Input\Hold\Right Or Input\Hold\Down) Or p\Motion\Ground=False Then
				canthrow=true
				If Menu\ChaoGarden=1 Then Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_THROW))
				Interface_ControlTipUpdate_PickUp(2)
			Else
				canthrow=false
				If Menu\ChaoGarden=1 Then Interface_ActivateGardenAction(2, CONTROLTIPS$(TIP_DROP)+" ")
				Interface_ControlTipUpdate_PickUp(3)
			EndIf
			If (Not(p\Action=ACTION_CARRY Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_CARRYTHROWN)) Then p\ObjPickUp=0
			Select p\RealCharacter
				Case CHAR_EGG,CHAR_TMH:
					PositionEntity o\Pivot, EntityX(p\Objects\Extra2, 1), EntityY(p\Objects\Extra2, 1), EntityZ(p\Objects\Extra2, 1)
					RotateEntity o\Pivot, EntityPitch(p\Objects\Mesh), p\Animation\Direction#+180, EntityRoll(p\Objects\Mesh)
					MoveEntity o\Pivot, 0, 1.7, 0.8
				Case CHAR_CHW:
					PositionEntity o\Entity, EntityX(p\Objects\HandR, 1), EntityY(p\Objects\HandR, 1), EntityZ(p\Objects\HandR, 1)
					RotateEntity o\Entity, EntityPitch(p\Objects\Mesh), p\Animation\Direction#+180, EntityRoll(p\Objects\Mesh)
					MoveEntity o\Entity, 0, 1.7, 0.8
				Default:
					PositionEntity o\Pivot, EntityX(p\Objects\Spine, 1), (EntityY(p\Objects\HandR, 1)+EntityY(p\Objects\HandL, 1))/2.0, EntityZ(p\Objects\Spine, 1)
					RotateEntity o\Pivot, EntityPitch(p\Objects\Mesh), p\Animation\Direction#+180, EntityRoll(p\Objects\Mesh)
					MoveEntity o\Pivot, 0, 0, (2.05+p\ScaleFactor#*2.5)
			End Select
			Select o\ObjType
				Case OBJTYPE_HAT:
					Select o\ChaoObj\HatType
						Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3,HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3,HAT_PACIFIER_0,HAT_PACIFIER_1,HAT_PACIFIER_2,HAT_PACIFIER_3:
							PositionEntity o\Entity, EntityX(o\Pivot), EntityY(o\Pivot)+1.5, EntityZ(o\Pivot)
						Default:
							PositionEntity o\Entity, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot)
					End Select
				Default:
					PositionEntity o\Entity, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot)
			End Select
			RotateEntity o\Entity, EntityPitch(o\Pivot), EntityYaw(o\Pivot), EntityRoll(o\Pivot)
			If o\ObjType=OBJTYPE_FRUIT Or o\ObjType=OBJTYPE_DRIVE Then MoveEntity o\Pivot, 0, 0.5, 0
			If p\Rotation#<0 Then MoveEntity o\Pivot, 0, -p\Rotation#*0.05, 0 Else MoveEntity o\Pivot, 0, -p\Rotation#*0.025, 0
			If p\ObjPickUpThrowTimer>0 Then
				If canthrow Then
					Player_PlayAttackVoice(p)
					o\ObjPickedUp=3
				Else
					Player_PlayJumpVoice(p)
					o\ObjPickedUp=4 : Player_SetSpeed(p,0)
				EndIf
				p\ObjPickUp=0
			Else
				If p\ObjPickUp=0 Then o\ObjPickedUp=0
			EndIf
		Case -1:
			If o\Hit Then
				RotateEntity o\Pivot, 0, p\Animation\Direction#+180, 0
			Else
				RotateEntity o\Pivot, 0, o\BombHitDirection#, 0
			EndIf
			o\BombHitDirection#=0
			o\ObjPickedUp=3
			p\ObjPickUp=0
		Case 3:
			If (Not(p\Action=ACTION_CARRY Or p\Action=ACTION_CARRYJUMP Or p\Action=ACTION_CARRYTHROWN)) Then p\ObjPickUp=0
			If o\ObjType=OBJTYPE_OMOCHAO Then StopChannel(o\Omochao\Channel_Omochao) : o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Hurt[Rand(1,5)])
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			Object_Bomb_Create.tBomb(p, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot), 0, EntityYaw(o\Pivot), 0, -1, Game\Gameplay\PsychoBombCount)
			o\ObjPickedUp=9
			o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
		Case 4:
			If o\ObjType=OBJTYPE_OMOCHAO Then StopChannel(o\Omochao\Channel_Omochao) : o\Omochao\Channel_Omochao=PlaySmartSound(Voice_OMO_Hurt[Rand(1,5)])
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			o\ObjPickedUp=0
		Case 5:
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			Object_Bomb_Create.tBomb(p, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot), 0, EntityYaw(o\Pivot), 0, -1, Game\Gameplay\PsychoBombCount)
			o\ObjPickedUp=9
			o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
		Case 6:
			Game\Gameplay\PsychoBombCount=Game\Gameplay\PsychoBombCount+1
			Object_Bomb_Create.tBomb(p, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot), 0, EntityYaw(o\Pivot), 0, -2, Game\Gameplay\PsychoBombCount)
			o\ObjPickedUp=9
			o\ThrownAsBomb=Game\Gameplay\PsychoBombCount
		Default:
			For b.tBomb=Each tBomb
			If o\ThrownAsBomb=b\ThrownMode Then
				b\ThrownAsBomb=True
				PositionEntity o\Pivot, EntityX(b\Pivot), EntityY(b\Pivot), EntityZ(b\Pivot)
				Select o\ObjType
					Case OBJTYPE_HAT:
						Select o\ChaoObj\HatType
							Case HAT_TIE_0,HAT_TIE_1,HAT_TIE_2,HAT_TIE_3,HAT_BOW_0,HAT_BOW_1,HAT_BOW_2,HAT_BOW_3,HAT_PACIFIER_0,HAT_PACIFIER_1,HAT_PACIFIER_2,HAT_PACIFIER_3:
								PositionEntity o\Entity, EntityX(o\Pivot), EntityY(o\Pivot)+1.5, EntityZ(o\Pivot)
							Default:
								PositionEntity o\Entity, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot)
						End Select
					Default:
						PositionEntity o\Entity, EntityX(o\Pivot), EntityY(o\Pivot), EntityZ(o\Pivot)
				End Select
				RotateEntity o\Entity, EntityPitch(o\Pivot), EntityYaw(o\Pivot), EntityRoll(o\Pivot)
			EndIf
			Next			
	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceTreasureRadar(o.tObject,p.tPlayer,redringno)
	If Not Game\Interface\DebugPlacerOn=0 Then Return

	; Radar sound
	If o\TreasureTimer>0 Then
		o\TreasureTimer=o\TreasureTimer-timervalue#
		If Abs(EntityDistance(p\Objects\Entity,o\Entity)-o\TreasurePreviousDistance#)>50 Then o\TreasureTimer=0
	Else
		If EntityDistance(p\Objects\Entity,o\Entity)<350 Then
			o\TreasurePreviousDistance#=EntityDistance(p\Objects\Entity,o\Entity)
			o\TreasureTimer=(((EntityDistance(p\Objects\Entity,o\Entity))/100)/1.5)*secs#
			PlaySmartSound(Sound_Treasure)

			; Radar interface
			For i=1 to 3
			If redringno=Game\Gameplay\RedRing[i] Then
				Game\Gameplay\RedRingTimer[i]=2*secs#
				Game\Gameplay\RedRingBeepTimer[i]=0.105*secs#
				If EntityDistance(p\Objects\Entity,o\Entity)>200 Then
					Game\Gameplay\RedRingDistance[i]=1
				ElseIf EntityDistance(p\Objects\Entity,o\Entity)>50 Then
					Game\Gameplay\RedRingDistance[i]=2
				ElseIf EntityDistance(p\Objects\Entity,o\Entity)>0 Then
					Game\Gameplay\RedRingDistance[i]=3
				EndIf
			EndIf
			Next

			If EntityDistance(p\Objects\Entity,o\Entity)<25 Then Game\Interface\NearCautionTimer=0.25*secs#
		EndIf
	EndIf

End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceRingBouncing(o.tObject,p.tPlayer,d.tDeltaTime)

	o\Spew\Gravity# = 0.04*p\Physics\UNDERWATERTRIGGER#
	For i = 1 To CountCollisions(o\Pivot)
		Select GetEntityType(CollisionEntity(o\Pivot, i))
			Case COLLISION_WORLD_POLYGON,COLLISION_WORLD_POLYGON_BLOCK,COLLISION_WORLD_POLYGON_PINBALL,COLLISION_WORLD_POLYGON_ICE,COLLISION_WORLD_POLYGON_BOUNCE,COLLISION_WORLD_POLYGON_SLOW
				; Get the normal of the surface collided with. 
				Nx# = CollisionNX#(o\Pivot, i) 
				Ny# = CollisionNY#(o\Pivot, i) 
				Nz# = CollisionNZ#(o\Pivot, i) 
				
				; Compute the dot product of the ball's motion vector and the normal of the surface collided with. 
				VdotN# = o\Speed\x#*Nx# + o\Speed\y#*Ny# + o\Speed\z#*Nz# 
				
				; Calculate the normal force. 
				NFx# = -2.0 * Nx# * VdotN# 
				NFy# = -2.0 * Ny# * VdotN# 
				NFz# = -2.0 * Nz# * VdotN# 
			
				; Add the normal force to the motion vector. 
				o\Speed\x#=(o\Speed\x# + NFx#)*0.8
				o\Speed\y#=(o\Speed\y# + NFy#)*0.8
				o\Speed\z#=(o\Speed\z# + NFz#)*0.8
		End Select
	Next
	o\Speed\y# = Max(o\Speed\y#-(o\Spew\Gravity#*d\Delta), p\Physics\COMMON_YTOPSPEED#)

	Select o\ObjType
		Case OBJTYPE_SPEWRING
			TranslateEntity(o\Pivot, o\Speed\x#*d\Delta, o\Speed\y#*d\Delta, o\Speed\z*d\Delta)
		Case OBJTYPE_SPEWREDRING
			If (o\Treasure\RedRingFly=0) Or (o\Treasure\RedRingFly=1 And o\Treasure\RedRingFlyStopTimer>0) Then TranslateEntity(o\Pivot, o\Speed\x#*d\Delta, o\Speed\y#*d\Delta, o\Speed\z*d\Delta)
	End Select

End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceForceDeleteChaoObj(o.tObject)
	If o\ChaoObj\ForceDelete Then
		Select o\ObjType
			Case OBJTYPE_FRUIT: FRUITSUM(1) = FRUITSUM(1) - 1
			Case OBJTYPE_HAT: HATSUM(1) = HATSUM(1) - 1
			Case OBJTYPE_TOY: TOYSUM(1) = TOYSUM(1) - 1
			Case OBJTYPE_DRIVE: DRIVESUM(1) = DRIVESUM(1) - 1
			Case OBJTYPE_SHELL: SHELLSUM(1) = SHELLSUM(1) - 1
			Case OBJTYPE_SEED: SEEDSUM(1) = SEEDSUM(1) - 1
		End Select
		FreeEntity o\Entity
		FreeEntity o\Pivot
		Delete o\Position
		Delete o\Rotation
		Delete o
		Return
	EndIf
End Function

;--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Function Object_EnforceStun(o.tObject,p.tPlayer,d.tDeltaTime,canbeothers=true)

	Object_EnforceBubbleStun(o,p,d)
	Object_EnforceFlowerStun(o,p,d)
	Object_EnforceWhirlwindStun(o,p,d)
	If canbeothers Then
		Object_EnforceCurseStun(o,p)
		Object_EnforcePoisonStun(o,p)
		Object_EnforceFrozenStun(o,p)
	EndIf

End Function

Function Object_EnforceBubbleStun(o.tObject,p.tPlayer,d.tDeltaTime)

	If o\BubbleStunTimer>0 Then
		o\BubbleStunTimer=o\BubbleStunTimer-timervalue#
		If Not(o\ObjType=OBJTYPE_CATERKILLER Or o\ObjType=OBJTYPE_MADMOLE Or o\ObjType=OBJTYPE_BOXIRON) Then MoveEntity o\Entity,0,(1/p\Physics\UNDERWATERTRIGGER#)*0.078*d\Delta,0
		If o\ThisIsABox Or o\ObjType=OBJTYPE_FLAPPER Or o\ObjType=OBJTYPE_FLAPPERGUN Or o\ObjType=OBJTYPE_FLAPPERBOMB Or o\ObjType=OBJTYPE_FLAPPERNEEDLE Or o\ObjType=OBJTYPE_MANTA Then
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BUBBLESTUN, o\Entity, o\HitBox\y#/5.0)
		Else
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_BUBBLESTUN, o\Entity, o\HitBox\y#/10.0)
		EndIf
	EndIf

End Function

Function Object_EnforceCurseStun(o.tObject,p.tPlayer)

	If o\CurseStunTimer>0 Then
		o\CurseStunTimer=o\CurseStunTimer-timervalue#
		If o\ThisIsABox Or o\ObjType=OBJTYPE_FLAPPER Or o\ObjType=OBJTYPE_FLAPPERGUN Or o\ObjType=OBJTYPE_FLAPPERBOMB Or o\ObjType=OBJTYPE_FLAPPERNEEDLE Or o\ObjType=OBJTYPE_MANTA Then
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_CURSESTUN, o\Entity, o\HitBox\y#/5.0)
		Else
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_CURSESTUN, o\Entity, o\HitBox\y#/10.0)
		EndIf
	EndIf

End Function

Function Object_EnforcePoisonStun(o.tObject,p.tPlayer)

	If o\FroggyStunTimer>0 Then
		o\FroggyStunTimer=o\FroggyStunTimer-timervalue#
		ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_POISON, o\Entity)
	EndIf

End Function

Function Object_EnforceFlowerStun(o.tObject,p.tPlayer,d.tDeltaTime)

	If o\FlowerStunTimer>0 Then
		If o\FlowerTyphoonDirection=0 Then
			Select(Rand(1,2))
			Case 1: o\FlowerTyphoonDirection=-1
			Case 2: o\FlowerTyphoonDirection=1
			End Select
		EndIf
		o\FlowerStunTimer=o\FlowerStunTimer-timervalue#
		If Not(o\ObjType=OBJTYPE_CATERKILLER Or o\ObjType=OBJTYPE_MADMOLE Or o\ObjType=OBJTYPE_BOXIRON) Then TurnEntity o\Entity,0,o\FlowerTyphoonDirection*5*d\Delta,0 : MoveEntity o\Entity,0,(1/p\Physics\UNDERWATERTRIGGER#)*0.278*d\Delta,(1/p\Physics\UNDERWATERTRIGGER#)*0.496*d\Delta
		ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_FLOWERS, o\Entity, o\HitBox\y#/4.0)
	Else
		If o\FlowerTyphoonDirection>0 Then o\FlowerTyphoonDirection=0
	EndIf

End Function

Function Object_EnforceFrozenStun(o.tObject,p.tPlayer)

	If o\FrozenStunTimer>0 Then
		o\FrozenStunTimer=o\FrozenStunTimer-timervalue#
	EndIf

End Function

Function Object_EnforceWhirlwindStun(o.tObject,p.tPlayer,d.tDeltaTime)

	If o\WhirlwindStunTimer>0 Then
		If o\WhirlwindDirection=0 Then
			Select(Rand(1,2))
			Case 1: o\WhirlwindDirection=-1
			Case 2: o\WhirlwindDirection=1
			End Select
		EndIf
		o\WhirlwindStunTimer=o\WhirlwindStunTimer-timervalue#
		If Not(o\ObjType=OBJTYPE_CATERKILLER Or o\ObjType=OBJTYPE_MADMOLE) Then TurnEntity o\Entity,0,o\WhirlwindDirection*6*d\Delta,0 : MoveEntity o\Entity,0,(1/p\Physics\UNDERWATERTRIGGER#)*0.478*d\Delta,(1/p\Physics\UNDERWATERTRIGGER#)*0.696*d\Delta
	Else
		If o\WhirlwindDirection>0 Then o\WhirlwindDirection=0
	EndIf

End Function

Function Object_EnforceAimingShooting(o.tObject,p.tPlayer)

	If o\AimedAt=0 Then
		If p\Aiming=1 and p\AimedTargets<10 Then
			If EntityDistance(p\Objects\Entity,o\Entity)<140 and Player_IsStaring(p,o\Entity) and o\IsInBox=0 Then o\AimedAt=1 : p\AimedTargets=p\AimedTargets+1
		EndIf
	Else
		If o\ThisIsAMonitor Then
			ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_AIMED, o\Entity, o\HitBox\y#/2.5)
		Else
			If o\ThisIsAnEnemy Then
				Select o\ObjType
					Case OBJTYPE_RHINO,OBJTYPE_RHINOSPIKES,OBJTYPE_FLAPPER,OBJTYPE_FLAPPERGUN,OBJTYPE_FLAPPERBOMB,OBJTYPE_FLAPPERNEEDLE,OBJTYPE_BALKIRY,OBJTYPE_TAKER,OBJTYPE_BOSS,OBJTYPE_BOSS2,OBJTYPE_BOSSRUN,OBJTYPE_BOSSBETA,OBJTYPE_BOSSMECHA, OBJTYPE_BOSSROBO,OBJTYPE_FCANNON1,OBJTYPE_FCANNON3:
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_AIMED, o\Enemy\Center, o\HitBox\y#/2.5)
					Default:
						ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_AIMED, o\Enemy\Center, o\HitBox\y#/5.0)
				End Select
			Else
				ParticleTemplate_Call(o\Particle, PARTICLE_OBJECT_AIMED, o\Entity, o\HitBox\y#/5.0)
			EndIf
		EndIf
		If EntityDistance(p\Objects\Entity,o\Entity)>240 Or p\Aiming=0 Then o\AimedAt=0 : p\AimedTargets=p\AimedTargets-1
	EndIf

End Function

;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

Function Object_UpdateCollisions(o.tObject, entity)

	o\SlopeRotation#=Sqr#(EntityPitch(o\Entity)^2+EntityRoll(o\Entity)^2)

	If Abs(o\SlopeRotation#)>55 Then
		; this part is the linepick. The tform part, is to make sure the linepick is done from the players
		; actual rotation, straigh down.
		TFormVector 0,-10,0,entity,0

		; the values from the tform then get used in the linepick command. if you just substitute your
		; variable for the player into here, everything will be cool!
		LinePick(EntityX(entity,True),EntityY(entity,True),EntityZ(entity,True),TFormedX(),TFormedY(),TFormedZ()); do a linepick straight down form the players location

		; aligns the player to the scenery, whatever has has a pickmode set.
		AlignToVector entity,PickedNX(),PickedNY(),PickedNZ(),2,0.1
	EndIf
	
End Function