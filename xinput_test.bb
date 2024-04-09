Include "_SourceCode\Libraries\Library_Xinput.bb"

Graphics 800, 600, 0, 2
SetBuffer BackBuffer()

If InitX360Joy()=False Then RuntimeError "Couldnt find DX9.0c installed!"

While Not KeyHit(1)

	Cls

		j = 150
		
        XCountJoys()
		For i = 0 To 3
		    If xReadJoy(i)=0 Then
 		       Text 0,0+(i*j), "Joystick " + i + " connected:" + xJoyCount
			   Text 24,14+(i*j), "Packet Number: "+xJoyPacket(i)	
			   button=Int(xReadButtons(i))

			   If ((button And 4096)) Then
			   	   xJoyRumble(i, 65535, 65535)			
			   Else
				   xJoyRumble(i, 0, 0)	
			   End If	

			   Text 24,28+(i*j), "Buttons: "+ button
			   Text 24,42+(i*j), "Left Trigger: "+xJoyZL(i)
			   Text 24,56+(i*j), "Right Trigger: "+xJoyZR(i)
			   Text 24,70+(i*j), "Left Thumbstick X: "+ xJoyX(i)
			   Text 24,84+(i*j), "Left Thumbstick Y: "+ xJoyY(i)
			   Text 24,98+(i*j), "Right Thumbstick X: "+xJoyPitch(i)
			   Text 24,112+(i*j),"Right Thumbstick Y: "+xJoyYaw(i)
			   Text 24,126+(i*j),"Direct-X Style Z Axis: " +xJoyZ(i)
			
			   Text 275,42+(i*j),"Controller Type: "+xJoyType(i,0)
			   Text 275,56+(i*j),"Controller SubType: "+xJoyType(i,1)
			   Text 275,70+(i*j),"Controller Flags:"+xJoyType(i,2)

			   Text 550,14+(i*j), "Press 'A' to test rumble"
			Else
			   ; this joy is disconnected!
			   Text 0, 0+(i*j), "Controller " + i + " is disconnected!"
		    End If
		Next
	Flip

Wend
FreeX360Joy()

End
;~IDEal Editor Parameters:
;~C#Blitz3D