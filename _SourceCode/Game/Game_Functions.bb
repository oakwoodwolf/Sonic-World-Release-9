
Function ReturnFPSDifferenceFactor()
	If Game\Others\Fps>=50 Then
		Return 15
	ElseIf Game\Others\Fps>40 Then
		Return 20
	ElseIf Game\Others\Fps>30 Then
		Return 25
	Else
		Return 30
	EndIf
End Function

;-----------------------------------------
Function Gameplay_AddGaugeEnergy(value#)
	If Game\Gameplay\GaugeEnergy+value#<100 Then
		Game\Gameplay\GaugeEnergy=Game\Gameplay\GaugeEnergy+(value#)
	Else
		If Game\Gameplay\GaugeEnergy<100 Then PlaySmartSound(Sound_MenuAccept)
		Game\Gameplay\GaugeEnergy=100
		
	EndIf
End Function
Function Gameplay_AddRings(value#)
	If Game\Gameplay\Rings+value#<999 Then
		Game\Gameplay\Rings=Game\Gameplay\Rings+value#
	Else
		Game\Gameplay\Rings=999
	EndIf
End Function

Function Gameplay_AddLives(value#)
	PlaySmartSound(Sound_1Up)
	If Game\Gameplay\Lives+value#<99 Then
		Game\Gameplay\Lives=Game\Gameplay\Lives+value#
	Else
		Game\Gameplay\Lives=99
	EndIf
End Function

Function Gameplay_AddScore(value#,pointstimer#=6.2)
	If Game\Gameplay\Score+value#<999999 Then
		Game\Gameplay\Score=Game\Gameplay\Score+value#
	Else
		Game\Gameplay\Score=999999
	EndIf

	If value#>10 Then
		Game\Interface\Points=value# : Game\Interface\PointsCommentGiven=0
		Game\Interface\PointsChain=Game\Interface\PointsChain+1
		Game\Interface\PointsTimer=pointstimer#*secs#
	EndIf
End Function

Function Gameplay_AddIdealScore(o.tObject,value#)
	o\AddedToIdealScore=1
	Game\IdealScore=Game\IdealScore+value#
End Function

Function Gameplay_AddEnemies(value#)
	Game\Gameplay\Enemies=Game\Gameplay\Enemies+value#
End Function

Function Gameplay_AddGoldEnemies(value#)
	Game\Gameplay\GoldEnemies=Game\Gameplay\GoldEnemies+value#
End Function

Function Gameplay_AddTotalEnemies(value#)
	Game\Gameplay\TotalEnemies=Game\Gameplay\TotalEnemies+value#
End Function

Function Gameplay_AddTotalGoldEnemies(value#)
	Game\Gameplay\TotalGoldEnemies=Game\Gameplay\TotalGoldEnemies+value#
End Function

Function Gameplay_AddPerfectBonus(value#)
	Game\Gameplay\PerfectBonus=Game\Gameplay\PerfectBonus+value#
End Function

Function Gameplay_AddShards(value#)
	Game\Gameplay\Shards=Game\Gameplay\Shards+value#
End Function

Function Gameplay_AddBalloons(value#)
	Game\Gameplay\Balloons=Game\Gameplay\Balloons+value#
End Function

Function Gameplay_AddTotalBalloons(value#)
	Game\Gameplay\TotalBalloons=Game\Gameplay\TotalBalloons+value#
End Function

Function Gameplay_AddFlickies(value#)
	Game\Gameplay\Flickies=Game\Gameplay\Flickies+value#
End Function

;---------------------------------------------------------------------------------

Function Gameplay_SubstractRings(value#)
	If Game\Gameplay\Rings-value#>0 Then
		Game\Gameplay\Rings=Game\Gameplay\Rings-value#
	Else
		Game\Gameplay\Rings=0
	EndIf
End Function
Function Gameplay_SubstractGaugeEnergy(value#)
	If Game\Gameplay\GaugeEnergy-value#>0 Then
		Game\Gameplay\GaugeEnergy=Game\Gameplay\GaugeEnergy-value#
	Else
		Game\Gameplay\GaugeEnergy=0
	EndIf
End Function
Function Gameplay_SubstractLives(value#)
	If Game\Gameplay\Lives-value#>0 Then
		Game\Gameplay\Lives=Game\Gameplay\Lives-value#
	Else
		Game\Gameplay\Lives=0
	EndIf
End Function

Function Gameplay_SubstractScore(value#)
	If Game\Gameplay\Score-value#>0 Then
		Game\Gameplay\Score=Game\Gameplay\Score-value#
	Else
		Game\Gameplay\Score=0
	EndIf
End Function

;---------------------------------------------------------------------------------
Function Gameplay_SetGaugeEnergy(value#)
	Game\Gameplay\GaugeEnergy=value#
End Function
Function Gameplay_SetRings(value#)
	Game\Gameplay\Rings=value#
End Function

Function Gameplay_SetLives(value#)
	Game\Gameplay\Lives=value#
End Function

Function Gameplay_SetScore(value#)
	Game\Gameplay\Score=value#
End Function

Function Gameplay_SetEnemies(value#)
	Game\Gameplay\Enemies=value#
End Function

Function Gameplay_SetGoldEnemies(value#)
	Game\Gameplay\GoldEnemies=value#
End Function

Function Gameplay_SetBalloons(value#)
	Game\Gameplay\Balloons=value#
End Function

Function Gameplay_SetFlickies(value#)
	Game\Gameplay\Flickies=value#
End Function

;~IDEal Editor Parameters:
;~C#Blitz3D