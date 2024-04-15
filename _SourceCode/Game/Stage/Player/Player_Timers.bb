
Global timervalue#=0
Global secs#=1000

Function Player_HandleTimers(p.tPlayer)

If p\UsedFrameTimer>0 Then p\UsedFrameTimer=p\UsedFrameTimer-timervalue#
If p\TranslatorsTouchedTimer>0 Then p\TranslatorsTouchedTimer=p\TranslatorsTouchedTimer-timervalue#
If p\Action=ACTION_HOP Or p\Action=ACTION_JUMP Then p\JumpHopTimer=p\JumpHopTimer+timervalue# : p\JumpTimer=p\JumpTimer+timervalue#
If p\Action=ACTION_CHARGE Or p\Action=ACTION_ROLL Or (p\Action=ACTION_DRIFT and p\ChargeTimer<0.1*secs#) Then p\ChargeTimer=p\ChargeTimer+timervalue# : p\JustChargedTimer=p\JustChargedTimer+timervalue#
If p\Action=ACTION_HOMING Then p\HomingTimer=p\HomingTimer-timervalue#
If p\JumpDashTimer>0 Then p\JumpDashTimer=p\JumpDashTimer-timervalue#
If p\Action=ACTION_FLY and p\FlyTimer>0 Then p\FlyTimer=p\FlyTimer-timervalue#
If p\GlideRestartTimer>0 Then p\GlideRestartTimer=p\GlideRestartTimer-timervalue#
If p\JumpActionRestrictTimer>0 Then p\JumpActionRestrictTimer=p\JumpActionRestrictTimer-timervalue#
If p\Action=ACTION_LEVITATE Then p\LevitationTimer=p\LevitationTimer-timervalue#
If p\TrickTimer>0 Then p\TrickTimer=p\TrickTimer-timervalue#
If p\HurtTimer>0 Then p\HurtTimer=p\HurtTimer-timervalue#
If p\DieTimer>0 Then p\DieTimer=p\DieTimer-timervalue#
If p\ThrowTimer>0 Then p\ThrowTimer=p\ThrowTimer-timervalue#
If p\LightDashTimer>0 Then p\LightDashTimer=p\LightDashTimer-timervalue#
If p\LightDashRequestTimer>0 Then p\LightDashRequestTimer=p\LightDashRequestTimer-timervalue#
If p\SpecialSpinTimer>0 Then p\SpecialSpinTimer=p\SpecialSpinTimer-timervalue#
If p\PunchTimer>0 Then p\PunchTimer=p\PunchTimer-timervalue#
If p\InvisibilityTimer>0 Then p\InvisibilityTimer=p\InvisibilityTimer-timervalue#
If p\HurtDisappearTimer>0 Then p\HurtDisappearTimer=p\HurtDisappearTimer-timervalue#
If p\PsychokinesisTimer>0 Then p\PsychokinesisTimer=p\PsychokinesisTimer-timervalue#
If p\PsychokinesisThrowTimer>0 Then p\PsychokinesisThrowTimer=p\PsychokinesisThrowTimer-timervalue#
If p\DrownTimer>0 Then p\DrownTimer=p\DrownTimer-timervalue#
If p\BreathCountTimer>0 Then p\BreathCountTimer=p\BreathCountTimer-timervalue#
If p\StompBounceTimer>0 Then p\StompBounceTimer=p\StompBounceTimer-timervalue#
If p\BombMonitorTimer>0 Then p\BombMonitorTimer=p\BombMonitorTimer-timervalue#
If p\ForceShotWalkTimer>0 Then p\ForceShotWalkTimer=p\ForceShotWalkTimer-timervalue#
If p\CreateGumBallTimer>0 Then p\CreateGumBallTimer=p\CreateGumBallTimer-timervalue#
If p\Action=ACTION_LAND Then p\LandTimer=p\LandTimer+timervalue#
If p\FloatTimer>0 Then p\FloatTimer=p\FloatTimer-timervalue#
If p\SonicBoomTrailTimer>0 Then p\SonicBoomTrailTimer=p\SonicBoomTrailTimer-timervalue#
If p\SonicBoomTimer>0 Then p\SonicBoomTimer=p\SonicBoomTimer-timervalue#
If p\SonicBoomOverheatTimer>0 Then p\SonicBoomOverheatTimer=p\SonicBoomOverheatTimer-timervalue#
If p\PsychoChargeTimer>0 Then p\PsychoChargeTimer=p\PsychoChargeTimer-timervalue#
If p\ShootCooldownTimer>0 Then p\ShootCooldownTimer=p\ShootCooldownTimer-timervalue#
If p\CheeseRestrictTimer>0 Then p\CheeseRestrictTimer=p\CheeseRestrictTimer-timervalue#
If p\GlideTimer>0 Then p\GlideTimer=p\GlideTimer-timervalue#
If p\GlideStartTimer>0 Then p\GlideStartTimer=p\GlideStartTimer-timervalue#
If p\EnemyComboTimer>0 Then p\EnemyComboTimer=p\EnemyComboTimer-timervalue#
If p\InvisibilityRestrictTimer>0 Then p\InvisibilityRestrictTimer=p\InvisibilityRestrictTimer-timervalue#
If p\PunchRestrictTimer>0 Then p\PunchRestrictTimer=p\PunchRestrictTimer-timervalue#
If p\TeleportTimer>0 Then p\TeleportTimer=p\TeleportTimer-timervalue#
If p\Action=ACTION_SOAR Or p\Action=ACTION_SOARFLAP Then p\SoarTimer=p\SoarTimer-timervalue#
If p\JustSoaredTimer>0 Then p\JustSoaredTimer=p\JustSoaredTimer-timervalue#
If p\ObjPickUpTimer>0 Then p\ObjPickUpTimer=p\ObjPickUpTimer-timervalue#
If p\ObjPickUpThrowTimer>0 Then p\ObjPickUpThrowTimer=p\ObjPickUpThrowTimer-timervalue#
If p\GrindTurnTimer>0 Then p\GrindTurnTimer=p\GrindTurnTimer-timervalue#
If p\GrindTurnRestrictTimer>0 Then p\GrindTurnRestrictTimer=p\GrindTurnRestrictTimer-timervalue#
If p\OnDeathMeshTimer>0 Then p\OnDeathMeshTimer=p\OnDeathMeshTimer-timervalue#
If p\TrailTimer>0 Then p\TrailTimer=p\TrailTimer-timervalue#
If p\RingDashStopTimer>0 Then p\RingDashStopTimer=p\RingDashStopTimer-timervalue#
If p\DoubleJumpTimer>0 Then p\DoubleJumpTimer=p\DoubleJumpTimer-timervalue#
If p\ChaosStretchSpawnerTimer>0 Then p\ChaosStretchSpawnerTimer=p\ChaosStretchSpawnerTimer-timervalue#
If p\Flags\TargeterTimer>0 Then p\Flags\TargeterTimer=p\Flags\TargeterTimer-timervalue#
If p\ShakeTreeTimer>0 Then p\ShakeTreeTimer=p\ShakeTreeTimer-timervalue#
If p\Flags\HomingWasLockedTimer>0 Then p\Flags\HomingWasLockedTimer=p\Flags\HomingWasLockedTimer-timervalue#
If p\CanClimbTimer>0 Then p\CanClimbTimer=p\CanClimbTimer-timervalue#
If p\ShouldBeHoldingTimer>0 Then p\ShouldBeHoldingTimer=p\ShouldBeHoldingTimer-timervalue#
If p\MateChangeTimer>0 Then p\MateChangeTimer=p\MateChangeTimer-timervalue#
If p\JustChangedMateTimer>0 Then p\JustChangedMateTimer=p\JustChangedMateTimer-timervalue#
If p\ForceJumpTimer>0 Then p\ForceJumpTimer=p\ForceJumpTimer-timervalue#
If p\AroundLightDashTimer>0 Then p\AroundLightDashTimer=p\AroundLightDashTimer-timervalue#
If p\BumpedTimer>0 Then p\BumpedTimer=p\BumpedTimer-timervalue#
If p\JumpMayRiseTimer>0 Then p\JumpMayRiseTimer=p\JumpMayRiseTimer-timervalue#
If p\FollowerIsHoldingLeaderTimer>0 Then p\FollowerIsHoldingLeaderTimer=p\FollowerIsHoldingLeaderTimer-timervalue#
If p\Aiming>0 Then p\JustStartedAimingTimer=p\JustStartedAimingTimer-timervalue#
If p\WasInBuoyTimer>0 Then p\WasInBuoyTimer=p\WasInBuoyTimer-timervalue#
If p\UnderwaterTriggerTimer>0 Then p\UnderwaterTriggerTimer=p\UnderwaterTriggerTimer-timervalue#
If p\WasGrabbedTimer>0 Then p\WasGrabbedTimer=p\WasGrabbedTimer-timervalue#
If p\JustLandedTimer>0 Then p\JustLandedTimer=p\JustLandedTimer-timervalue#
If p\IceFloorTimer>0 Then p\IceFloorTimer=p\IceFloorTimer-timervalue#
If p\InkFloorTimer>0 Then p\InkFloorTimer=p\InkFloorTimer-timervalue#
If p\SlowFloorTimer>0 Then p\SlowFloorTimer=p\SlowFloorTimer-timervalue#
If p\BumpedCloudTimer>0 Then p\BumpedCloudTimer=p\BumpedCloudTimer-timervalue#
If p\DontGetHurtTimer>0 Then p\DontGetHurtTimer=p\DontGetHurtTimer-timervalue#
If p\JustThrewBombTimer>0 Then p\JustThrewBombTimer=p\JustThrewBombTimer-timervalue#
If p\RubyGravityTimer>0 Then p\RubyGravityTimer=p\RubyGravityTimer-timervalue#
If p\RazerSpawningTimer>0 Then p\RazerSpawningTimer=p\RazerSpawningTimer-timervalue#
If p\RazerSpawningTimer2>0 Then p\RazerSpawningTimer2=p\RazerSpawningTimer2-timervalue#
If p\HyperBlastLimiterTimer>0 Then p\HyperBlastLimiterTimer=p\HyperBlastLimiterTimer-timervalue#
If p\ChaosControlActiveTimer>0 Then p\ChaosControlActiveTimer=p\ChaosControlActiveTimer-timervalue#
If p\TornadoChangeTimer>0 Then p\TornadoChangeTimer=p\TornadoChangeTimer-timervalue#
If p\BoardWaterTimer>0 Then p\BoardWaterTimer=p\BoardWaterTimer-timervalue#
If p\JustDeformedCharacterTimer>0 Then p\JustDeformedCharacterTimer=p\JustDeformedCharacterTimer-timervalue#
If p\IsHoldingTimer>0 Then p\IsHoldingTimer=p\IsHoldingTimer-timervalue#
If p\IsGrabbedTimer>0 Then p\IsGrabbedTimer=p\IsGrabbedTimer-timervalue#
If p\JustGrabbedPulleyTimer>0 Then p\JustGrabbedPulleyTimer=p\JustGrabbedPulleyTimer-timervalue#
If p\ForceBeingAbleToChangeLeaderTimer>0 Then p\ForceBeingAbleToChangeLeaderTimer=p\ForceBeingAbleToChangeLeaderTimer-timervalue#
If p\CantJumpTimer>0 Then p\CantJumpTimer=p\CantJumpTimer-timervalue#

If Menu\ChaoGarden=1 Then
	If p\MayWhistleTimer>0 Then p\MayWhistleTimer=p\MayWhistleTimer-timervalue#
	If p\MayNotWhistleTimer>0 Then p\MayNotWhistleTimer=p\MayNotWhistleTimer-timervalue#	
	If p\MayPetTimer>0 Then p\MayPetTimer=p\MayPetTimer-timervalue#
	If p\MayNotPetTimer>0 Then p\MayNotPetTimer=p\MayNotPetTimer-timervalue#
	If p\MayCheerTimer>0 Then p\MayCheerTimer=p\MayCheerTimer-timervalue#
	If p\MayNotCheerTimer>0 Then p\MayNotCheerTimer=p\MayNotCheerTimer-timervalue#
EndIf

If p\No#<0 Then
	If p\Rival\MoveTimer>0 Then p\Rival\MoveTimer=p\Rival\MoveTimer-timervalue#
	If (Not(p\Rival\MoveTimer>0)) and p\Rival\DontMoveTimer>0 Then p\Rival\DontMoveTimer=p\Rival\DontMoveTimer-timervalue#
	If p\Rival\MakeJumpTimer>0 Then p\Rival\MakeJumpTimer=p\Rival\MakeJumpTimer-timervalue#
	If p\Rival\MakeJumpActionTimer>0 Then p\Rival\MakeJumpActionTimer=p\Rival\MakeJumpActionTimer-timervalue#
	If p\Rival\MakeAttackTimer>0 Then p\Rival\MakeAttackTimer=p\Rival\MakeAttackTimer-timervalue#
	If p\Rival\MakeChargeTimer>0 Then p\Rival\MakeChargeTimer=p\Rival\MakeChargeTimer-timervalue#
	If p\Rival\MakeStompTimer>0 Then p\Rival\MakeStompTimer=p\Rival\MakeStompTimer-timervalue#
	If p\Rival\JustHadActionTimer>0 Then p\Rival\JustHadActionTimer=p\Rival\JustHadActionTimer-timervalue#
EndIf

End Function


;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------
;---------------------------------------------------------------------------------------------------------------

Function Player_ResetAllTimers(p.tPlayer)

;FROM PLAYER
p\UsedFrameTimer=0
p\TranslatorsTouchedTimer=0
p\JumpHopTimer=0
p\JumpTimer=0
p\ChargeTimer=0
p\JustChargedTimer=0
p\HomingTimer=0
p\JumpDashTimer=0
p\FlyTimer=0
p\GlideRestartTimer=0
p\JumpActionRestrictTimer=0
p\LevitationTimer=0
p\TrickTimer=0
p\HurtTimer=0
p\DieTimer=0
p\ThrowTimer=0
p\LightDashTimer=0
p\LightDashRequestTimer=0
p\SpecialSpinTimer=0
p\PunchTimer=0
p\InvisibilityTimer=0
p\HurtDisappearTimer=0
p\PsychokinesisTimer=0
p\PsychokinesisThrowTimer=0
p\DrownTimer=0
p\BreathCountTimer=0
p\StompBounceTimer=0
p\BombMonitorTimer=0
p\ForceShotWalkTimer=0
p\CreateGumBallTimer=0
p\LandTimer=0
p\FloatTimer=0
p\SonicBoomTrailTimer=0
p\SonicBoomTimer=0
p\SonicBoomOverheatTimer=0
p\PsychoChargeTimer=0
p\ShootCooldownTimer=0
p\CheeseRestrictTimer=0
p\GlideTimer=0
p\GlideStartTimer=0
p\EnemyComboTimer=0
p\InvisibilityRestrictTimer=0
p\PunchRestrictTimer=0
p\TeleportTimer=0
p\SoarTimer=0
p\JustSoaredTimer=0
p\ObjPickUpTimer=0
p\ObjPickUpThrowTimer=0
p\GrindTurnTimer=0
p\GrindTurnRestrictTimer=0
p\OnDeathMeshTimer=0
p\TrailTimer=0
p\RingDashStopTimer=0
p\DoubleJumpTimer=0
p\ChaosStretchSpawnerTimer=0
p\Flags\TargeterTimer=0
p\ShakeTreeTimer=0
p\Flags\HomingWasLockedTimer=0
p\CanClimbTimer=0
p\ShouldBeHoldingTimer=0
p\MateChangeTimer=0
p\JustChangedMateTimer=0
p\ForceJumpTimer=0
p\BumpedTimer=0
p\FollowerIsHoldingLeaderTimer=0
p\StompSaverTimer=0
p\DestinationSaverTimer=0
p\JustStartedAimingTimer=0
p\WasInBuoyTimer=0
p\UnderwaterTriggerTimer=0
p\WasGrabbedTimer=0
p\JustLandedTimer=0
p\IceFloorTimer=0
p\InkFloorTimer=0
p\SlowFloorTimer=0
p\BumpedCloudTimer=0
p\JustThrewBombTimer=0
p\RubyGravityTimer=0
p\RazerSpawningTimer=0
p\RazerSpawningTimer2=0
p\HyperBlastLimiterTimer=0
p\ChaosControlActiveTimer=0
p\TornadoChangeTimer=0
p\BoardWaterTimer=0
p\JustDeformedCharacterTimer=0
p\IsHoldingTimer=0
p\IsGrabbedTimer=0
p\JustGrabbedPulleyTimer=0
p\ForceBeingAbleToChangeLeaderTimer=0
p\CantJumpTimer=0

If p\No#<0 Then
	p\Rival\MoveTimer=0
	p\Rival\DontMoveTimer=1*secs#
	p\Rival\MakeJumpTimer=1*secs#
	p\Rival\MakeJumpActionTimer=1*secs#
	p\Rival\MakeAttackTimer=1*secs#
	p\Rival\MakeChargeTimer=1*secs#
	p\Rival\MakeStompTimer=1*secs#
	p\Rival\JustHadActionTimer=0
EndIf

;FROM GAME
Game\CheeseTimer=0
Game\FroggyTimer=0
Game\Interface\ShowCaution1Timer=0
Game\Interface\ShowCaution2Timer=0
Game\Interface\ShowCaution3Timer=0
For i=1 to 3
	Game\Gameplay\RedRingTimer[i]=0
	Game\Gameplay\RedRingBeepTimer[i]=0
Next
Game\ControlLock=0
Game\CamLock=0
Game\RunLock=0
Game\MachLock=0
Game\InvincTimer=0
Game\SpeedShoeTimer=0
cam\Lock\Timer=0
cam\MouseCameraTimer=0

End Function