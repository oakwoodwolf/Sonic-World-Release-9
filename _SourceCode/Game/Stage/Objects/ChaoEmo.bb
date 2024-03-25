
	Dim CHAOEMO_EYETEXTURENAMES$(3,1)

	CHAOEMO_EYETEXTURENAMES$(1,0)	= "hero"
	CHAOEMO_EYETEXTURENAMES$(2,0)	= "dark"
	CHAOEMO_EYETEXTURENAMES$(3,0)	= "neutral"
	CHAOEMO_EYETEXTURENAMES$(1,1)	= "angel"
	CHAOEMO_EYETEXTURENAMES$(2,1)	= "devil"
	CHAOEMO_EYETEXTURENAMES$(3,1)	= "light"

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tChaoEmo
		Field Emotion
		Field PreviousEmotion
		Field Objects.tChaoEmo_Objects
		Field FaceEmo
		Field MouthEmo
		Field OfficeEmo
		Field Side
		Field Eternal
		Field CurrentTexture$
		Field CenterOffice
		Field JiggleState[3]
		Field JiggleSize#[3]
		Field JiggleSizeTarget#[3]
	End Type

	Type tChaoEmo_Objects
		Field emotionsroot
		Field office
		Field heart
		Field question
		Field exclamation
		Field spiral

		Field happy
		Field sad
		Field surprised
		Field worried
		Field shocked
		Field thrilled
		Field joyful
		Field wicked
	End Type

		Global CHAOEMO_default		= 1
		Global CHAOEMO_happy		= 2
		Global CHAOEMO_sad		= 3
		Global CHAOEMO_confused		= 4
		Global CHAOEMO_scared		= 5
		Global CHAOEMO_surprised	= 6
		Global CHAOEMO_angry		= 7
		Global CHAOEMO_hurt		= 8
		Global CHAOEMO_worried		= 9
		Global CHAOEMO_shocked		= 10
		Global CHAOEMO_thrilled		= 11
		Global CHAOEMO_joyful		= 12
		Global CHAOEMO_wicked		= 13
		Global CHAOEMO_drowsy		= 14
		Global CHAOEMO_raged		= 15
		Global CHAOEMO_flirty		= 16
		Global CHAOEMO_curious		= 17
		Global CHAOEMO_disgusted	= 18
		Global CHAOEMO_disappointed	= 19
		Global CHAOEMO_bored		= 20
		Global CHAOEMO_cheerful		= 21
		Global CHAOEMO_lovely		= 22
		Global CHAOEMO_TOTAL		= 22

		Global CHAOEMO_face_eye1	= 1
		Global CHAOEMO_face_eye2	= 2
		Global CHAOEMO_face_eye3	= 3
		Global CHAOEMO_face_eye4	= 4
		Global CHAOEMO_face_eye5	= 5
		Global CHAOEMO_face_eye6	= 6
		Global CHAOEMO_face_eye7	= 7
		Global CHAOEMO_face_eye8	= 8
		Global CHAOEMO_face_eye9	= 9

		Global CHAOEMO_mouth_happy	= 1
		Global CHAOEMO_mouth_sad	= 2
		Global CHAOEMO_mouth_surprised	= 3
		Global CHAOEMO_mouth_worried	= 4
		Global CHAOEMO_mouth_shocked	= 5
		Global CHAOEMO_mouth_thrilled	= 6
		Global CHAOEMO_mouth_joyful	= 7
		Global CHAOEMO_mouth_wicked	= 8

		Global CHAOEMO_office_default	= 1
		Global CHAOEMO_office_heart	= 2
		Global CHAOEMO_office_exclamation=3
		Global CHAOEMO_office_question	= 4
		Global CHAOEMO_office_spiral	= 5

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Object_ChaoEmo_Create.tChaoEmo(mesh, side=1, charchao=False, eternal=0)
	
		ce.tChaoEmo = New tChaoEmo
		ce\Objects = New tChaoEmo_Objects

		ce\Objects\emotionsroot=FindChild(mesh, "emotionsroot")
		ce\Objects\office=FindChild(mesh, "office")
		ce\Objects\heart=FindChild(mesh, "heart")
		ce\Objects\question=FindChild(mesh, "question")
		ce\Objects\exclamation=FindChild(mesh, "exclamation")
		ce\Objects\spiral=FindChild(mesh, "spiral")

		ce\Objects\happy=FindChild(mesh, "happy")
		ce\Objects\sad=FindChild(mesh, "sad")
		ce\Objects\surprised=FindChild(mesh, "surprised")
		ce\Objects\worried=FindChild(mesh, "worried")
		ce\Objects\shocked=FindChild(mesh, "shocked")
		ce\Objects\thrilled=FindChild(mesh, "thrilled")
		ce\Objects\joyful=FindChild(mesh, "joyful")
		ce\Objects\wicked=FindChild(mesh, "wicked")

		ce\Emotion=CHAOEMO_default

		ce\Side=side
		ce\Eternal=eternal

		If charchao Then
			ce\CurrentTexture$="chao.eye1.png"
		Else
			ce\CurrentTexture$=CHAOEMO_EYETEXTURENAMES$(side,0)+".eye1.png"
		EndIf

		For i=1 To 3 : ce\JiggleSize[i]=1 : Next

		Return ce
		
	End Function
	
	; =========================================================================================================

	Function Object_ChaoEmo_Update(mesh, ce.tChaoEmo, d.tDeltaTime)

		;center office
		If ce\CenterOffice Then
			PositionEntity ce\Objects\emotionsroot, 0, 0.2643, 0.2442
		EndIf

		;emotion control
		Select ce\Emotion
			Case CHAOEMO_default:
				ce\FaceEmo=CHAOEMO_face_eye1
				Select ce\Side
				Case CHAOSIDE_DARK: ce\MouthEmo=CHAOEMO_mouth_wicked
				Default: ce\MouthEmo=CHAOEMO_mouth_happy
				End Select
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_happy:
				ce\FaceEmo=CHAOEMO_face_eye3
				ce\MouthEmo=CHAOEMO_mouth_happy
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_sad:
				ce\FaceEmo=CHAOEMO_face_eye7
				ce\MouthEmo=CHAOEMO_mouth_sad
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_confused:
				ce\FaceEmo=CHAOEMO_face_eye8
				ce\MouthEmo=CHAOEMO_mouth_shocked
				ce\OfficeEmo=CHAOEMO_office_question
			Case CHAOEMO_scared:
				ce\FaceEmo=CHAOEMO_face_eye5
				ce\MouthEmo=CHAOEMO_mouth_worried
				ce\OfficeEmo=CHAOEMO_office_exclamation
			Case CHAOEMO_surprised:
				ce\FaceEmo=CHAOEMO_face_eye8
				ce\MouthEmo=CHAOEMO_mouth_surprised
				ce\OfficeEmo=CHAOEMO_office_exclamation
			Case CHAOEMO_angry:
				ce\FaceEmo=CHAOEMO_face_eye6
				ce\MouthEmo=CHAOEMO_mouth_sad
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_hurt:
				ce\FaceEmo=CHAOEMO_face_eye2
				ce\MouthEmo=CHAOEMO_mouth_shocked
				ce\OfficeEmo=CHAOEMO_office_spiral
			Case CHAOEMO_worried:
				ce\FaceEmo=CHAOEMO_face_eye1
				ce\MouthEmo=CHAOEMO_mouth_worried
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_shocked:
				ce\FaceEmo=CHAOEMO_face_eye1
				ce\MouthEmo=CHAOEMO_mouth_shocked
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_thrilled:
				ce\FaceEmo=CHAOEMO_face_eye1
				ce\MouthEmo=CHAOEMO_mouth_thrilled
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_joyful:
				ce\FaceEmo=CHAOEMO_face_eye3
				ce\MouthEmo=CHAOEMO_mouth_joyful
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_wicked:
				ce\FaceEmo=CHAOEMO_face_eye1
				ce\MouthEmo=CHAOEMO_mouth_wicked
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_drowsy:
				ce\FaceEmo=CHAOEMO_face_eye4
				ce\MouthEmo=CHAOEMO_mouth_sad
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_raged:
				ce\FaceEmo=CHAOEMO_face_eye2
				ce\MouthEmo=CHAOEMO_mouth_wicked
				ce\OfficeEmo=CHAOEMO_office_exclamation
			Case CHAOEMO_flirty:
				ce\FaceEmo=CHAOEMO_face_eye3
				ce\MouthEmo=CHAOEMO_mouth_surprised
				ce\OfficeEmo=CHAOEMO_office_heart
			Case CHAOEMO_curious:
				ce\FaceEmo=CHAOEMO_face_eye1
				ce\MouthEmo=CHAOEMO_mouth_happy
				ce\OfficeEmo=CHAOEMO_office_question
			Case CHAOEMO_disgusted:
				ce\FaceEmo=CHAOEMO_face_eye8
				ce\MouthEmo=CHAOEMO_mouth_worried
				ce\OfficeEmo=CHAOEMO_office_spiral
			Case CHAOEMO_disappointed:
				ce\FaceEmo=CHAOEMO_face_eye4
				ce\MouthEmo=CHAOEMO_mouth_worried
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_bored:
				ce\FaceEmo=CHAOEMO_face_eye5
				ce\MouthEmo=CHAOEMO_mouth_sad
				ce\OfficeEmo=CHAOEMO_office_default
			Case CHAOEMO_cheerful:
				ce\FaceEmo=CHAOEMO_face_eye3
				ce\MouthEmo=CHAOEMO_mouth_thrilled
				ce\OfficeEmo=CHAOEMO_office_heart
			Case CHAOEMO_lovely:
				ce\FaceEmo=CHAOEMO_face_eye3
				ce\MouthEmo=CHAOEMO_mouth_happy
				ce\OfficeEmo=CHAOEMO_office_heart
		End Select

		;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		If ce\PreviousEmotion<>ce\Emotion Then
			neweyetexture$ = CHAOEMO_EYETEXTURENAMES$(ce\Side,ce\Eternal)+".eye"
			Select ce\Eternal
				Case 0: neweyetexture$ = neweyetexture$+ce\FaceEmo+".png"
				Case 1: neweyetexture$ = neweyetexture$+".png"
			End Select
			eyetexture = LoadTexture("ChaoWorld\Chao\"+neweyetexture$,256)
			glaretexture = LoadTexture("ChaoWorld\Chao\0.brightshoeref.png", 1+64) : TextureBlend(glaretexture,3)

			ApplyMeshTextureLayer(mesh, ce\CurrentTexture$, eyetexture)
			ApplyMeshTextureLayer(mesh, ce\CurrentTexture$, glaretexture, True) 

			FreeTexture eyetexture
			FreeTexture glaretexture

			ce\CurrentTexture$=neweyetexture$
			ce\PreviousEmotion=ce\Emotion
		EndIf

		Select ce\MouthEmo
			Case CHAOEMO_mouth_happy:
				ScaleEntity ce\Objects\happy,		1,1,1
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_sad:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		1,1,1
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_surprised:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	1,1,1
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_worried:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		1,1,1
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_shocked:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		1,1,1
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_thrilled:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	1,1,1
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_joyful:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		1,1,1
				ScaleEntity ce\Objects\wicked,		0,0,0
			Case CHAOEMO_mouth_wicked:
				ScaleEntity ce\Objects\happy,		0,0,0
				ScaleEntity ce\Objects\sad,		0,0,0
				ScaleEntity ce\Objects\surprised,	0,0,0
				ScaleEntity ce\Objects\worried,		0,0,0
				ScaleEntity ce\Objects\shocked,		0,0,0
				ScaleEntity ce\Objects\thrilled,	0,0,0
				ScaleEntity ce\Objects\joyful,		0,0,0
				ScaleEntity ce\Objects\wicked,		1,1,1
		End Select

		For i=1 To 3
			If ce\JiggleState[i]>0 Then
				If ce\JiggleSize#[i]<ce\JiggleSizeTarget#[i] Then ce\JiggleSize#[i]=ce\JiggleSize#[i]+0.01*d\Delta
				If ce\JiggleSize#[i]>ce\JiggleSizeTarget#[i] Then ce\JiggleSize#[i]=ce\JiggleSize#[i]-0.01*d\Delta
				If Abs(ce\JiggleSize#[i]-ce\JiggleSizeTarget#[i])<0.025 Then ce\JiggleState[i]=0
			Else
				ce\JiggleSizeTarget#[i]=1.0+Rand(0,5)/20.0
				ce\JiggleState[i]=1
			EndIf
		Next

		Select ce\OfficeEmo
			Case CHAOEMO_office_default:
				ScaleEntity ce\Objects\office,		ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
				ScaleEntity ce\Objects\heart,		0,0,0
				ScaleEntity ce\Objects\question,	0,0,0
				ScaleEntity ce\Objects\exclamation,	0,0,0
				ScaleEntity ce\Objects\spiral,		0,0,0
			Case CHAOEMO_office_heart:
				ScaleEntity ce\Objects\office,		0,0,0
				ScaleEntity ce\Objects\heart,		ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
				ScaleEntity ce\Objects\question,	0,0,0
				ScaleEntity ce\Objects\exclamation,	0,0,0
				ScaleEntity ce\Objects\spiral,		0,0,0
			Case CHAOEMO_office_exclamation:
				ScaleEntity ce\Objects\office,		ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
				ScaleEntity ce\Objects\heart,		0,0,0
				ScaleEntity ce\Objects\question,	0,0,0
				ScaleEntity ce\Objects\exclamation,	ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
				ScaleEntity ce\Objects\spiral,		0,0,0
			Case CHAOEMO_office_question:
				ScaleEntity ce\Objects\office,		ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
				ScaleEntity ce\Objects\heart,		0,0,0
				ScaleEntity ce\Objects\question,	ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
				ScaleEntity ce\Objects\exclamation,	0,0,0
				ScaleEntity ce\Objects\spiral,		0,0,0
			Case CHAOEMO_office_spiral:
				ScaleEntity ce\Objects\office,		0,0,0
				ScaleEntity ce\Objects\heart,		0,0,0
				ScaleEntity ce\Objects\question,	0,0,0
				ScaleEntity ce\Objects\exclamation,	0,0,0
				ScaleEntity ce\Objects\spiral,		ce\JiggleSize#[1],ce\JiggleSize#[2],ce\JiggleSize#[3]
		End Select

	End Function