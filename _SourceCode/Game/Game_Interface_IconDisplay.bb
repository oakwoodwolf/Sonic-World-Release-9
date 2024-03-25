	
	Type tMonitorIcon
		Field Draw
		Field mType
		Field Timer
		Field State
		Field Alpha#
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Dim MonitorIcon.tMonitorIcon(5)
	For i=1 To 5 : MonitorIcon(i) = SetupMonitorIcon() : Next
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	; This function returns a new SetupMonitorIcon object.
Function SetupMonitorIcon.tMonitorIcon()
		rd.tMonitorIcon = New tMonitorIcon
		ResetMonitorIcon(rd)
		Return rd
	End Function

Function ReplicateMonitorIcon(rd1.tMonitorIcon,rd2.tMonitorIcon)
		rd1\Draw = rd2\Draw
		rd1\mType = rd2\mType
		rd1\Timer = rd2\Timer
		rd1\State = rd2\State
		rd1\Alpha# = rd2\Alpha#
	End Function

Function ResetMonitorIcon(rd.tMonitorIcon)
		rd\Draw = False
		rd\mType = 0
		rd\Timer = 0
		rd\State = 0
		rd\Alpha = 0
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	; Draws the icons. This is still incredibly inefficient and could use improvement!
Function Update_Monitor_Icons(d.tDeltaTime, height#=40, spacing#=32.5)

		For i=1 To 5
			If MonitorIcon(i)\Draw Then
				SetAlpha(MonitorIcon(i)\Alpha)
				Update_Monitor_Icon_Timer(MonitorIcon(i),i,d)
				Select i
					Case 1: position#=0
					Case 2: position#=1
					Case 3: position#=-1
					Case 4: position#=+2
					Case 5: position#=-2
				End Select
				If Not(Game\Interface\ShowHintTimer>0) Then DrawImageEx(INTERFACE(Interface_Monitors), GAME_WINDOW_W/2+position#*spacing#*GAME_WINDOW_SCALE#, GAME_WINDOW_H-height#*GAME_WINDOW_SCALE#, MonitorIcon(i)\mType-1)
				SetAlpha(1.0)
			End If
		Next
		
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	; Updates the timer of the specified icon which effects the alpha (fading)
Function Update_Monitor_Icon_Timer(rd.tMonitorIcon,num,d.tDeltaTime)

		If rd\Timer>0 Then rd\Timer=rd\Timer-timervalue#

		Select rd\State
			Case 0:
				rd\State=1
			Case 1:
				rd\Alpha# = rd\Alpha# + 0.05*d\Delta
				If (rd\Alpha >= 1.0) Then
					rd\Alpha# = 1.0
					rd\State=2
					rd\Timer=1.5*secs#
				EndIf
			Case 2:
				If Not(rd\Timer>0) Then rd\State=3
			Case 3:
				rd\Alpha# = rd\Alpha# - 0.1*d\Delta
				If (rd\Alpha <= 0.0) Then
					rd\Alpha# = 0.0
					rd\State=0
					rd\mType = 0
					rd\Draw=False
				EndIf
		End Select

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

Function MonitorIcon_Draw(mtype#)
		hasbeenadded=False

		For i=1 To 5
			If hasbeenadded=False Then
				If MonitorIcon(i)\Draw=False Then
					MonitorIcon(i)\Draw=True
					MonitorIcon(i)\mType=mtype#
					hasbeenadded=True
				EndIf
			EndIf
		Next

		If hasbeenadded=False Then
			For i=1 To 4
				ReplicateMonitorIcon(MonitorIcon(i),MonitorIcon(i+1))
			Next
			ResetMonitorIcon(MonitorIcon(5))
			MonitorIcon(5)\Draw=True
			MonitorIcon(5)\mType=mtype#
			hasbeenadded=True
		EndIf
	End Function

;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
;^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
Function Update_ChaoItem_Icons(d.tDeltaTime, fromright#=20, frombottom#=27.5, spacing#=20)

		Select Game\Interface\ShowChaoItems

			; Entry - sets the alpha and scale
			Case 1
				Game\Interface\ChaoIconAlpha# = 0.0
				Game\Interface\ChaoIconScale# = GAME_WINDOW_SCALE# * 2
				Game\Interface\ShowChaoItems = 2

			; Expand
			Case 2
				Game\Interface\ChaoIconSpeed# = Game\Interface\ChaoIconSpeed# + 1.5*d\Delta
				Game\Interface\ChaoIconSpread# = Game\Interface\ChaoIconSpread# + 0.01*Game\Interface\ChaoIconSpeed#*d\Delta
				If (Game\Interface\ChaoIconSpread#>=1.0) Then
					Game\Interface\ChaoIconTimer=0.8*secs#
					Game\Interface\ChaoIconSpread#=1.0
					Game\Interface\ShowChaoItems = 3
				EndIf

			; Delay
			Case 3
				If Not(Game\Interface\ChaoIconTimer>0) Then
					Game\Interface\ChaoIconSpread# = 1.0
					Game\Interface\ChaoIconSpeed# = 0.0
					Game\Interface\ShowChaoItems = 4
				Else
					Game\Interface\ChaoIconTimer=Game\Interface\ChaoIconTimer-timervalue#
				EndIf

			; Collapse
			Case 4
				Game\Interface\ChaoIconSpeed# = Game\Interface\ChaoIconSpeed# + 0.25*d\Delta
				Game\Interface\ChaoIconSpread# = Game\Interface\ChaoIconSpread# - 0.01*Game\Interface\ChaoIconSpeed#*d\Delta
				If (Game\Interface\ChaoIconSpread#<0.0) Then
					Game\Interface\ChaoIconSpread#=0.0
					Game\Interface\ShowChaoItems = 0
				EndIf

			; Pause
			Case 5
				Game\Interface\ChaoIconSpread# = 1.0
				Game\Interface\ChaoIconSpeed# = 0.0
				If Menu\Pause=0 Then
					Game\Interface\ChaoIconSpread#=0.0
					Game\Interface\ShowChaoItems = 0
				EndIf

		End Select
		
		If (Game\Interface\ChaoIconAlpha#<1.0) Then Game\Interface\ChaoIconAlpha# = Game\Interface\ChaoIconAlpha# + 0.05*d\Delta
		If (Game\Interface\ChaoIconScale#>GAME_WINDOW_SCALE#) Then Game\Interface\ChaoIconScale# = Game\Interface\ChaoIconScale# - 0.05*d\Delta
		
		; Draw the icons!
		If Game\Interface\ShowChaoItems>0 Then
			If Game\Interface\ChaoItemCount>0 Then
			For i=1 To Game\Interface\ChaoItemCount
				x# = 5*GAME_WINDOW_SCALE#+GAME_WINDOW_W-(fromright#*Game\Interface\ChaoIconSpread#)*GAME_WINDOW_SCALE#-(i*spacing#*Game\Interface\ChaoIconSpread#)*GAME_WINDOW_SCALE#
				y# = GAME_WINDOW_H-frombottom#*GAME_WINDOW_SCALE#
				DrawChaoIcon(i, x#, y#, Game\Interface\ChaoIconAlpha#, Game\Interface\ChaoIconScale#)
			Next
			EndIf
		EndIf
	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

Function DrawChaoIcon(i, x#, y#, alpha#, scale#)
		If Game\Interface\ChaoItemCount>i Then
			SetAlpha(1.0)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
			DrawImageEx(INTERFACE(Interface_Inventory), x#, y#, Game\Interface\ChaoItems[i]-1)
		Else
			SetAlpha(alpha#)
			SetScale(scale#, scale#)
			DrawImageEx(INTERFACE(Interface_Inventory), x#, y#, Game\Interface\ChaoItems[i]-1)
			SetAlpha(1.0)
			SetScale(GAME_WINDOW_SCALE#, GAME_WINDOW_SCALE#)
		EndIf
	End Function

Function ChaoIcon_Draw(num#)

		Game\Interface\ChaoItemCount=TOTALCARRIEDITEMS
		If Game\Interface\ChaoItemCount>10 Then Game\Interface\ChaoItemCount=10

		If Game\Interface\ChaoItemCount>=10 Then
			For j=1 To 9
				Game\Interface\ChaoItems[j] = Game\Interface\ChaoItems[j+1]
			Next
			Game\Interface\ChaoItems[10] = num#
		Else
			Game\Interface\ChaoItems[Game\Interface\ChaoItemCount] = num#
		EndIf
		
		Game\Interface\ShowChaoItems=1

	End Function

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------

Function Rank_Draw(rank, x#, y#)

		Select rank
		Case 1: SetRotation(0)
		Case 2: SetRotation(0)
		Case 3: SetRotation(0)
		Case 4: SetRotation(0)
		Case 5: SetRotation(10)
		Case 6: SetRotation(-20)
		Case 7: SetRotation(-35)
	End Select

		DrawImageEx(INTERFACE(Interface_Ranks), x#, y#, rank-1)

		SetRotation(0)
		SetColor(255,255,255)

	End Function


;~IDEal Editor Parameters:
;~C#Blitz3D