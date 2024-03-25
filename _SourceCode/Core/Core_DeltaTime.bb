
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	; ---------------------------------------------------------------------------------------------------------
	; ---------------------------------------------------------------------------------------------------------
	Type tDeltaTime
		; Ideal values
		Field IdealFPS
		Field IdealInterval#

		; Intervals
		Field TimePreviousFrame
		Field TimeCurrentFrame

		; Delta values
		Field DeltaList#[20]
		Field DeltaNode
		Field Delta#
		Field InvDelta#
	End Type

Global TimeControl# = 1

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function DeltaTime_Create.tDeltaTime(FPS=60)
		d.tDeltaTime = New tDeltaTime
		d\IdealFPS 			= FPS
		d\IdealInterval		= 1/(1000/Float(FPS))
		d\TimePreviousFrame = MilliSecs()
		d\TimeCurrentFrame	= MilliSecs()
		Return d
	End Function

	Function DeltaTime_Reset(d.tDeltaTime)
		d\TimePreviousFrame = MilliSecs()
		d\TimeCurrentFrame = MilliSecs()
		d\Delta# = 0.0
		d\DeltaNode = 0
		For i=0 To 19
			d\DeltaList#[i] = 0.0
		Next
	End Function
	
	Function DeltaTime_Update(d.tDeltaTime)		
		; Capture current time
		d\TimePreviousFrame = d\TimeCurrentFrame
		d\TimeCurrentFrame	= MilliSecs()
	
		; Update intervals
		d\DeltaList#[d\DeltaNode] = Float(d\TimeCurrentFrame-d\TimePreviousFrame)*d\IdealInterval*TimeControl#
		d\DeltaNode = (d\DeltaNode+1) Mod 20
		
		; Calculate delta
		d\Delta# = 0.0
		For i=0 To 19
			d\Delta# = d\Delta# + d\DeltaList#[i]
		Next
		d\Delta# 	= d\Delta#*0.05				; d\Delta/20
		d\InvDelta#	= 1.0/d\Delta#

		;If Game\TimeControl=1 Then
		;	If KeyHit(KEY_Y) Then TimeControl# = 1 : Game\Cheater=1
		;	If KeyHit(KEY_U) Then TimeControl# = TimeControl# + 0.2 : Game\Cheater=1
		;	If KeyHit(KEY_I) Then TimeControl# = TimeControl# - 0.2 : Game\Cheater=1
		;EndIf
	End Function 
;~IDEal Editor Parameters:
;~C#Blitz3D