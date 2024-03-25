;------------------------------------------------------------------------
;------------------------------------------------------------------------
Const ATTRIB_POSITION = 0
Const ATTRIB_ROTATION = 1
Const ATTRIB_POWER = 3
Const ATTRIB_LOCK = 4
Const ATTRIB_CAM = 5
Const ATTRIB_AMOUNT = 6
Const ATTRIB_SWITCH = 7
Const ATTRIB_SWITCHOBJ = 8
Const ATTRIB_TELEPORTER = 9
Const ATTRIB_DESTINATION = 10
Const ATTRIB_HINT = 11
Const ATTRIB_CARNIVAL = 12
Const ATTRIB_OMOVOICE=13

Type tTempAttribute
	Field ID#

	Field x#
	Field y#
	Field z#

	Field pitch#
	Field yaw#
	Field roll#

	Field power#

	Field lock#
	Field lockcontrol#
	Field lockcam#
	Field lockrun#

	Field campos#
	Field camx#
	Field camy#
	Field camz#
	Field campitch#
	Field camyaw#
	Field camroll#
	Field camzoom#
	Field camspeed#

	Field amountcircle#
	Field amount1#
	Field amount2#
	Field amount3#
	Field amountpitch#
	Field amountyaw#
	Field amountspace1#
	Field amountspace2#
	Field amountspace3#

	Field switch1#
	Field switch2#
	Field switch3#
	Field switchstatus#

	Field teleporterno#
	Field teleportername$

	Field hasd#
	Field dx#
	Field dy#
	Field dz#

	Field hint1$
	Field hint2$
	Field hintlength#

	Field carnival#

	Field ObjectNo
	Field ObjectID
	Field TempObject
	
	Field omovoiceon#
	Field omovoicepath$
	
	Field soundpath$
End Type

Global TempAttribute.tTempAttribute = New tTempAttribute

Function GetAttribute(Attribute, Node)
	; Select the attribute to get, and set the TempAttribute object
	Select Attribute
		Case ATTRIB_POSITION
			TempAttribute\x# = 0
			TempAttribute\y# = 0
			TempAttribute\z# = 0
			ScenePosition = xmlNodeFind("position", Node)
			If (ScenePosition<>0) Then
				TempAttribute\x# = Float(xmlNodeAttributeValueGet(ScenePosition, "x"))
				TempAttribute\y# = Float(xmlNodeAttributeValueGet(ScenePosition, "y"))
				TempAttribute\z# = Float(xmlNodeAttributeValueGet(ScenePosition, "z"))
			End If

		Case ATTRIB_ROTATION
			TempAttribute\pitch# = 0
			TempAttribute\yaw# = 0
			TempAttribute\roll# = 0
			SceneRotation = xmlNodeFind("rotation", Node)
			If (SceneRotation<>0) Then
				TempAttribute\pitch# = Float(xmlNodeAttributeValueGet(SceneRotation, "pitch"))
				TempAttribute\yaw# = Float(xmlNodeAttributeValueGet(SceneRotation, "yaw"))
				TempAttribute\roll# = Float(xmlNodeAttributeValueGet(SceneRotation, "roll"))
			End If
			
		Case ATTRIB_OMOVOICE
			TempAttribute\omovoiceon# = 0
			TempAttribute\omovoicepath$ = 0
			SceneOmovoice = xmlNodeFind("omovoice", Node)
			If (SceneOmovoice<>0) Then
				TempAttribute\omovoiceon# = Float(xmlNodeAttributeValueGet(SceneOmovoice, "on"))
				TempAttribute\omovoicepath$ = xmlNodeAttributeValueGet(SceneOmovoice, "path")
			End If

		Case ATTRIB_POWER
			TempAttribute\power# = 0
			ScenePower = xmlNodeFind("power", Node)
			If (ScenePower<>0) Then
				TempAttribute\power# = Float(xmlNodeAttributeValueGet(ScenePower, "is"))
			End If
			If TempAttribute\power#=0 Then
				If TempAttribute\ObjectNo<>0 Then
					Select TempAttribute\ObjectNo
						Case OBJTYPE_SPRING,OBJTYPE_SPRINGTRAP,OBJTYPE_BSPRING,OBJTYPE_ACCEL: TempAttribute\power# = 2
						Case OBJTYPE_SPRINGX,OBJTYPE_SPRINGTRAPX: TempAttribute\power# = 1.2
						Case OBJTYPE_PAD,OBJTYPE_FORCER,OBJTYPE_NODE,OBJTYPE_NODE2: TempAttribute\power# = 4.8
						Case OBJTYPE_RAMP: TempAttribute\power# = 2.5
						Case OBJTYPE_HOOP,OBJTYPE_THOOP: TempAttribute\power# = 1.8
						Case OBJTYPE_CANNON: TempAttribute\power# = 1.5
						Case OBJTYPE_PROPELLER: TempAttribute\power# = 1.325
						Case OBJTYPE_PULLEY: TempAttribute\power# = 0.75
						Case OBJTYPE_ROCKET: TempAttribute\power# = 2.5
						Case OBJTYPE_TRIGGER_WATER,OBJTYPE_TRIGGER_MUSIC: TempAttribute\power# = 20
						Case OBJTYPE_CLOUD,OBJTYPE_POLE: TempAttribute\power# = 1.5
						Case OBJTYPE_SWITCHWATER: TempAttribute\power# = 0
						Case OBJTYPE_COUNTER: TempAttribute\power# = 5
						Case OBJTYPE_HINT: TempAttribute\power# = 2
						Default: TempAttribute\power# = 1
					End Select
				Else
					TempAttribute\power# = 0
				EndIf
			EndIf

		Case ATTRIB_LOCK
			TempAttribute\lockcontrol# = 0
			TempAttribute\lockcam# = 0
			TempAttribute\lockrun# = 0
			TempAttribute\lock# = 0
			SceneLock = xmlNodeFind("lock", Node)
			If (SceneLock<>0) Then
				TempAttribute\lock# = Float(xmlNodeAttributeValueGet(SceneLock, "is"))
				TempAttribute\lockcontrol# = Float(xmlNodeAttributeValueGet(SceneLock, "control"))
				TempAttribute\lockcam# = Float(xmlNodeAttributeValueGet(SceneLock, "cam"))
				TempAttribute\lockrun# = Float(xmlNodeAttributeValueGet(SceneLock, "run"))
			End If

			If TempAttribute\lock#<>0 Then
			Select TempAttribute\lock#
				Case 000: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=0
				Case 100: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=0
				Case 200: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=0
				Case 300: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=0
				Case 010: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=0
				Case 110: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=0
				Case 210: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=0
				Case 310: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=0
				Case 020: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=0
				Case 120: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=0
				Case 220: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=0
				Case 320: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=0
				Case 030: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=0
				Case 130: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=0
				Case 230: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=0
				Case 330: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=0
				Case 001: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=1
				Case 101: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=1
				Case 201: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=1
				Case 301: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=0 : TempAttribute\lockrun#=1
				Case 011: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=1
				Case 111: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=1
				Case 211: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=1
				Case 311: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=1 : TempAttribute\lockrun#=1
				Case 021: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=1
				Case 121: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=1
				Case 221: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=1
				Case 321: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=2 : TempAttribute\lockrun#=1
				Case 031: TempAttribute\lockcontrol#=0 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=1
				Case 131: TempAttribute\lockcontrol#=1 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=1
				Case 231: TempAttribute\lockcontrol#=2 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=1
				Case 331: TempAttribute\lockcontrol#=3 : TempAttribute\lockcam#=3 : TempAttribute\lockrun#=1
			End Select
			EndIf

		Case ATTRIB_CAM
			TempAttribute\campos# = 0
			TempAttribute\camx# = 0
			TempAttribute\camy# = 0
			TempAttribute\camz# = 0
			TempAttribute\campitch# = 0
			TempAttribute\camyaw# = 0
			TempAttribute\camroll# = 0
			TempAttribute\camzoom# = 21
			TempAttribute\camspeed# = 30

			SceneCam = xmlNodeFind("cam", Node)
			If (SceneCam<>0) Then
				If TempAttribute\lock#>0 Then
					TempAttribute\campos# = 0
					TempAttribute\camx# = 0
					TempAttribute\camy# = 0
					TempAttribute\camz# = 0
					TempAttribute\campitch# = Float(xmlNodeAttributeValueGet(SceneCam, "x"))
					TempAttribute\camyaw# = Float(xmlNodeAttributeValueGet(SceneCam, "y"))
					;TempAttribute\camroll# = Float(xmlNodeAttributeValueGet(SceneCam, "z"))
					TempAttribute\camzoom# = Float(xmlNodeAttributeValueGet(SceneCam, "zoom"))
					TempAttribute\camspeed# = Float(xmlNodeAttributeValueGet(SceneCam, "speed"))
				Else
					TempAttribute\campos# = Float(xmlNodeAttributeValueGet(SceneCam, "pos"))
					TempAttribute\camx# = Float(xmlNodeAttributeValueGet(SceneCam, "x"))
					TempAttribute\camy# = Float(xmlNodeAttributeValueGet(SceneCam, "y"))
					TempAttribute\camz# = Float(xmlNodeAttributeValueGet(SceneCam, "z"))
					TempAttribute\campitch# = Float(xmlNodeAttributeValueGet(SceneCam, "pitch"))
					TempAttribute\camyaw# = Float(xmlNodeAttributeValueGet(SceneCam, "yaw"))
					;TempAttribute\camroll# = Float(xmlNodeAttributeValueGet(SceneCam, "roll"))
					TempAttribute\camzoom# = Float(xmlNodeAttributeValueGet(SceneCam, "zoom"))
					TempAttribute\camspeed# = Float(xmlNodeAttributeValueGet(SceneCam, "speed"))
				End If
			EndIf

		Case ATTRIB_AMOUNT
			TempAttribute\ID# = 0
			SceneID = xmlNodeFind("ID", Node)
			If (SceneID<>0) Then
				TempAttribute\ID# = Float(xmlNodeAttributeValueGet(SceneID, "is"))
			End If

			TempAttribute\amountcircle# = 0
			TempAttribute\amount1# = 1
			TempAttribute\amount2# = 1
			TempAttribute\amount3# = 1
			TempAttribute\amountpitch# = 0
			TempAttribute\amountyaw# = 0
			TempAttribute\amountspace1# = 20
			TempAttribute\amountspace2# = 20
			TempAttribute\amountspace3# = 20
			SceneAmount = xmlNodeFind("obj", Node)
			If (SceneAmount<>0) Then
				TempAttribute\amountcircle# = Float(xmlNodeAttributeValueGet(SceneAmount, "circle"))
				TempAttribute\amount1# = Float(xmlNodeAttributeValueGet(SceneAmount, "amount"))
				If TempAttribute\amount1#<=0 Then TempAttribute\amount1# = Float(xmlNodeAttributeValueGet(SceneAmount, "amount1"))
				TempAttribute\amount2# = Float(xmlNodeAttributeValueGet(SceneAmount, "amount2"))
				TempAttribute\amount3# = Float(xmlNodeAttributeValueGet(SceneAmount, "amount3"))
				TempAttribute\amountpitch# = Float(xmlNodeAttributeValueGet(SceneAmount, "pitch"))
				TempAttribute\amountyaw# = Float(xmlNodeAttributeValueGet(SceneAmount, "yaw"))
				TempAttribute\amountspace1# = Float(xmlNodeAttributeValueGet(SceneAmount, "space"))
				TempAttribute\amountspace2# = Float(xmlNodeAttributeValueGet(SceneAmount, "space"))
				TempAttribute\amountspace3# = Float(xmlNodeAttributeValueGet(SceneAmount, "space"))
				If TempAttribute\amountspace1#<=0 Then TempAttribute\amountspace1# = Float(xmlNodeAttributeValueGet(SceneAmount, "space1"))
				If TempAttribute\amountspace2#<=0 Then TempAttribute\amountspace2# = Float(xmlNodeAttributeValueGet(SceneAmount, "space2"))
				If TempAttribute\amountspace3#<=0 Then TempAttribute\amountspace3# = Float(xmlNodeAttributeValueGet(SceneAmount, "space3"))
			End If

		Case ATTRIB_SWITCH
			TempAttribute\switch1# = 0
			TempAttribute\switchstatus# = 1
			SceneSwitch = xmlNodeFind("switch", Node)
			If (SceneSwitch<>0) Then
				TempAttribute\switch1# = Float(xmlNodeAttributeValueGet(SceneSwitch, "no"))
				TempAttribute\switchstatus# = Float(xmlNodeAttributeValueGet(SceneSwitch, "status"))
			End If

		Case ATTRIB_SWITCHOBJ
			TempAttribute\switch1# = 0
			TempAttribute\switch2# = 0
			TempAttribute\switch3# = 0
			SceneSwitchObj = xmlNodeFind("switch", Node)
			If (SceneSwitchObj<>0) Then
				TempAttribute\switch1# = Float(xmlNodeAttributeValueGet(SceneSwitchObj, "no"))
				TempAttribute\switch2# = Float(xmlNodeAttributeValueGet(SceneSwitchObj, "no2"))
				If TempAttribute\switch2#=TempAttribute\switch1# Then TempAttribute\switch2#=0
				TempAttribute\switch3# = Float(xmlNodeAttributeValueGet(SceneSwitchObj, "no3"))
				If TempAttribute\switch3#=TempAttribute\switch1# Or TempAttribute\switch3#=TempAttribute\switch2# Then TempAttribute\switch3#=0
			End If

		Case ATTRIB_TELEPORTER
			TempAttribute\teleporterno# = 0
			TempAttribute\teleportername$ = 0
			SceneTeleporter = xmlNodeFind("teleporter", Node)
			If (SceneTeleporter<>0) Then
				TempAttribute\teleporterno# = Float(xmlNodeAttributeValueGet(SceneTeleporter, "no"))
				TempAttribute\teleportername$ = xmlNodeAttributeValueGet(SceneTeleporter, "name")
			End If

		Case ATTRIB_DESTINATION
			TempAttribute\hasd# = 0
			TempAttribute\dx# = 0
			TempAttribute\dy# = 0
			TempAttribute\dz# = 0
			SceneDestination = xmlNodeFind("d", Node)
			If (SceneDestination<>0) Then
				TempAttribute\hasd# = Float(xmlNodeAttributeValueGet(SceneDestination, "has"))
				TempAttribute\dx# = Float(xmlNodeAttributeValueGet(SceneDestination, "x"))
				TempAttribute\dy# = Float(xmlNodeAttributeValueGet(SceneDestination, "y"))
				TempAttribute\dz# = Float(xmlNodeAttributeValueGet(SceneDestination, "z"))
			End If

		Case ATTRIB_HINT
			TempAttribute\hint1$ = ""
			TempAttribute\hint2$ = ""
			TempAttribute\soundpath$ = ""
			SceneHint = xmlNodeFind("hint", Node)
			If (SceneHint<>0) Then
				TempAttribute\hint1$ = xmlNodeAttributeValueGet(SceneHint, "line1")
				TempAttribute\hint2$ = xmlNodeAttributeValueGet(SceneHint, "line2")
				TempAttribute\soundpath$ = xmlNodeAttributeValueGet(SceneHint, "soundpath")
			End If

		Case ATTRIB_CARNIVAL
			TempAttribute\carnival# = 0
			SceneCarnival = xmlNodeFind("carnival", Node)
			If (SceneCarnival<>0) Then
				TempAttribute\carnival# = Float(xmlNodeAttributeValueGet(SceneCarnival, "no"))
			End If

	End Select
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D