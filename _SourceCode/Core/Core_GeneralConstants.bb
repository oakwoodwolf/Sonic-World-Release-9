;; The short names, usually used for filename detection
Dim ShortCharNames$(CHAR_FULLCOUNT,2)
ShortCharNames$(CHAR_SON,1) = "Son"
ShortCharNames$(CHAR_TAI,1) = "Tai" : ShortCharNames$(CHAR_TAI,2) = "Tmh"
ShortCharNames$(CHAR_KNU,1) = "Knu"
ShortCharNames$(CHAR_AMY,1) = "Amy"
ShortCharNames$(CHAR_SHA,1) = "Sha"
ShortCharNames$(CHAR_ROU,1) = "Rou"
ShortCharNames$(CHAR_CRE,1) = "Cre"
ShortCharNames$(CHAR_BLA,1) = "Bla"
ShortCharNames$(CHAR_SIL,1) = "Sil"
ShortCharNames$(CHAR_OME,1) = "Ome"
ShortCharNames$(CHAR_ESP,1) = "Esp"
ShortCharNames$(CHAR_CHA,1) = "Cha"
ShortCharNames$(CHAR_VEC,1) = "Vec"
ShortCharNames$(CHAR_BIG,1) = "Big"
ShortCharNames$(CHAR_MAR,1) = "Mar"
ShortCharNames$(CHAR_MIG,1) = "Mig"
ShortCharNames$(CHAR_RAY,1) = "Ray"
ShortCharNames$(CHAR_CHO,1) = "Cho"
ShortCharNames$(CHAR_TIK,1) = "Tik"
ShortCharNames$(CHAR_NAC,1) = "Nac"
ShortCharNames$(CHAR_BEA,1) = "Bea"
ShortCharNames$(CHAR_BAR,1) = "Bar"
ShortCharNames$(CHAR_JET,1) = "Jet"
ShortCharNames$(CHAR_WAV,1) = "Wav"
ShortCharNames$(CHAR_STO,1) = "Sto"
ShortCharNames$(CHAR_TIA,1) = "Tia"
ShortCharNames$(CHAR_HON,1) = "Hon"
ShortCharNames$(CHAR_SHD,1) = "Shd"
ShortCharNames$(CHAR_MPH,1) = "Mph"
ShortCharNames$(CHAR_HBO,1) = "Hbo"
ShortCharNames$(CHAR_GAM,1) = "Gam"
ShortCharNames$(CHAR_EME,1) = "Eme"
ShortCharNames$(CHAR_MET,1) = "Met"
ShortCharNames$(CHAR_TDL,1) = "Tdl"
ShortCharNames$(CHAR_MKN,1) = "Mkn"
ShortCharNames$(CHAR_EGG,1) = "Egg" : ShortCharNames$(CHAR_EGG,2) = "Egw"
ShortCharNames$(CHAR_BET,1) = "Bet"
ShortCharNames$(CHAR_MT3,1) = "Mt3"
ShortCharNames$(CHAR_GME,1) = "Gme"
ShortCharNames$(CHAR_PRS,1) = "Prs"
ShortCharNames$(CHAR_COM,1) = "Com"
ShortCharNames$(CHAR_CHW,1) = "Chw"
ShortCharNames$(CHAR_EGR,1) = "Egr"
ShortCharNames$(CHAR_INF,1) = "Inf"
ShortCharNames$(CHAR_TRI,1) = "Tri"
;---
ShortCharNames$(CHAR_OBT,1) = "Obt"
ShortCharNames$(CHAR_CBT,1) = "Cbt"
ShortCharNames$(CHAR_EGN,1) = "Egn"
ShortCharNames$(CHAR_MIA,1) = "Mia"
ShortCharNames$(CHAR_VAN,1) = "Van"
;---
ShortCharNames$(CHAR_CHE,1) = "Che"
ShortCharNames$(CHAR_CHC,1) = "Chc"
ShortCharNames$(CHAR_FRO,1) = "Fro"

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Const KEY_EMPTY = 0

	Const KEY_ESCAPE = 1
	Const KEY_1 = 2
	Const KEY_2 = 3
	Const KEY_3 = 4
	Const KEY_4 = 5
	Const KEY_5 = 6
	Const KEY_6 = 7
	Const KEY_7 = 8
	Const KEY_8 = 9
	Const KEY_9 = 10
	Const KEY_0 = 11
	Const KEY_HYPHEN = 12
	Const KEY_BACKSPACE = 14
	Const KEY_TAB = 15
	Const KEY_Q = 16
	Const KEY_W = 17
	Const KEY_E = 18
	Const KEY_R = 19
	Const KEY_T = 20
	Const KEY_Y = 21
	Const KEY_U = 22
	Const KEY_I = 23
	Const KEY_O = 24
	Const KEY_P = 25
	Const KEY_ENTER = 28
	Const KEY_CTRL_LEFT = 29
	Const KEY_A = 30
	Const KEY_S = 31
	Const KEY_D = 32
	Const KEY_F = 33
	Const KEY_G = 34
	Const KEY_H = 35
	Const KEY_J = 36
	Const KEY_K = 37
	Const KEY_L = 38
	Const KEY_SHIFT_LEFT = 42
	Const KEY_Z = 44
	Const KEY_X = 45
	Const KEY_C = 46
	Const KEY_V = 47
	Const KEY_B = 48
	Const KEY_N = 49
	Const KEY_M = 50
	Const KEY_SHIFT_RIGHT = 54
	Const KEY_ALT_LEFT = 56
	Const KEY_SPACE = 57
	Const KEY_F1 = 59
	Const KEY_F2 = 60
	Const KEY_F3 = 61
	Const KEY_F4 = 62
	Const KEY_F5 = 63
	Const KEY_F6 = 64
	Const KEY_F7 = 65
	Const KEY_F8 = 66
	Const KEY_F9 = 67
	Const KEY_F10 = 68
	Const KEY_PLUS = 78
	Const KEY_F11 = 87
	Const KEY_F12 = 88
	Const KEY_F13 = 100
	Const KEY_F14 = 101
	Const KEY_F15 = 102
	Const KEY_CTRL_RIGHT = 157
	Const KEY_ALT_RIGHT = 184
	Const KEY_ARROW_UP = 200
	Const KEY_ARROW_LEFT = 203
	Const KEY_ARROW_RIGHT = 205
	Const KEY_ARROW_DOWN = 208
	Const KEY_DELETE = 211

	Const KEY_MOUSE_LEFT = -1
	Const KEY_MOUSE_RIGHT = -2
	Const KEY_MOUSE_MIDDLE = -3

	Const KEY_GAMEPAD_BUTTON1 = 901+50
	Const KEY_GAMEPAD_BUTTON2 = 902+50
	Const KEY_GAMEPAD_BUTTON3 = 903+50
	Const KEY_GAMEPAD_BUTTON4 = 904+50
	Const KEY_GAMEPAD_BUTTON5 = 905+50
	Const KEY_GAMEPAD_BUTTON6 = 906+50
	Const KEY_GAMEPAD_BUTTON7 = 907+50
	Const KEY_GAMEPAD_BUTTON8 = 908+50
	Const KEY_GAMEPAD_BUTTON9 = 909+50
	Const KEY_GAMEPAD_BUTTON10 = 910+50
	Const KEY_GAMEPAD_BUTTON11 = 911+50
	Const KEY_GAMEPAD_BUTTON12 = 912+50
	Const KEY_GAMEPAD_X_MINUS = 901
	Const KEY_GAMEPAD_X_PLUS = 902
	Const KEY_GAMEPAD_Y_MINUS = 903
	Const KEY_GAMEPAD_Y_PLUS = 904
	Const KEY_GAMEPAD_Z_MINUS = 905
	Const KEY_GAMEPAD_Z_PLUS = 906
	Const KEY_GAMEPAD_DPAD_UP = 907
	Const KEY_GAMEPAD_DPAD_LEFT = 908
	Const KEY_GAMEPAD_DPAD_RIGHT = 909
	Const KEY_GAMEPAD_DPAD_DOWN = 910
	Const KEY_GAMEPAD_P_MINUS = 911
	Const KEY_GAMEPAD_P_PLUS = 912
	Const KEY_GAMEPAD_W_MINUS = 913
	Const KEY_GAMEPAD_W_PLUS = 914
	Const KEY_GAMEPAD_R_MINUS = 915
	Const KEY_GAMEPAD_R_PLUS = 916


	Global BUTTONS[84]

	BUTTONS[1]=KEY_A
	BUTTONS[2]=KEY_B
	BUTTONS[3]=KEY_C
	BUTTONS[4]=KEY_D
	BUTTONS[5]=KEY_E
	BUTTONS[6]=KEY_F
	BUTTONS[7]=KEY_G
	BUTTONS[8]=KEY_H
	BUTTONS[9]=KEY_I
	BUTTONS[10]=KEY_J
	BUTTONS[11]=KEY_K
	BUTTONS[12]=KEY_L
	BUTTONS[13]=KEY_M
	BUTTONS[14]=KEY_N
	BUTTONS[15]=KEY_O
	BUTTONS[16]=KEY_P
	BUTTONS[17]=KEY_Q
	BUTTONS[18]=KEY_R
	BUTTONS[19]=KEY_S
	BUTTONS[20]=KEY_T
	BUTTONS[21]=KEY_U
	BUTTONS[22]=KEY_V
	BUTTONS[23]=KEY_W
	BUTTONS[24]=KEY_X
	BUTTONS[25]=KEY_Y
	BUTTONS[26]=KEY_Z
	BUTTONS[27]=KEY_HYPHEN
	BUTTONS[28]=KEY_BACKSPACE
	BUTTONS[29]=KEY_ENTER
	BUTTONS[30]=KEY_PLUS
	BUTTONS[31]=KEY_0
	BUTTONS[32]=KEY_1
	BUTTONS[33]=KEY_2
	BUTTONS[34]=KEY_3
	BUTTONS[35]=KEY_4
	BUTTONS[36]=KEY_5
	BUTTONS[37]=KEY_6
	BUTTONS[38]=KEY_7
	BUTTONS[39]=KEY_8
	BUTTONS[40]=KEY_9
	BUTTONS[41]=KEY_TAB
	BUTTONS[42]=KEY_CTRL_LEFT
	BUTTONS[43]=KEY_CTRL_RIGHT
	BUTTONS[44]=KEY_SHIFT_LEFT
	BUTTONS[45]=KEY_SHIFT_RIGHT
	BUTTONS[46]=KEY_ALT_LEFT
	BUTTONS[47]=KEY_ALT_RIGHT
	BUTTONS[48]=KEY_ESCAPE
	BUTTONS[49]=KEY_SPACE
	BUTTONS[50]=KEY_ARROW_UP
	BUTTONS[51]=KEY_ARROW_DOWN
	BUTTONS[52]=KEY_ARROW_LEFT
	BUTTONS[53]=KEY_ARROW_RIGHT
	BUTTONS[54]=KEY_MOUSE_LEFT
	BUTTONS[55]=KEY_MOUSE_RIGHT
	BUTTONS[56]=KEY_MOUSE_MIDDLE
	BUTTONS[57]=KEY_GAMEPAD_BUTTON1
	BUTTONS[58]=KEY_GAMEPAD_BUTTON2
	BUTTONS[59]=KEY_GAMEPAD_BUTTON3
	BUTTONS[60]=KEY_GAMEPAD_BUTTON4
	BUTTONS[61]=KEY_GAMEPAD_BUTTON5
	BUTTONS[62]=KEY_GAMEPAD_BUTTON6
	BUTTONS[63]=KEY_GAMEPAD_BUTTON7
	BUTTONS[64]=KEY_GAMEPAD_BUTTON8
	BUTTONS[65]=KEY_GAMEPAD_BUTTON9
	BUTTONS[66]=KEY_GAMEPAD_BUTTON10
	BUTTONS[67]=KEY_GAMEPAD_BUTTON11
	BUTTONS[68]=KEY_GAMEPAD_BUTTON12
	BUTTONS[69]=KEY_GAMEPAD_X_MINUS
	BUTTONS[70]=KEY_GAMEPAD_X_PLUS
	BUTTONS[71]=KEY_GAMEPAD_Y_MINUS
	BUTTONS[72]=KEY_GAMEPAD_Y_PLUS
	BUTTONS[73]=KEY_GAMEPAD_Z_MINUS
	BUTTONS[74]=KEY_GAMEPAD_Z_PLUS
	BUTTONS[75]=KEY_GAMEPAD_DPAD_UP
	BUTTONS[76]=KEY_GAMEPAD_DPAD_LEFT
	BUTTONS[77]=KEY_GAMEPAD_DPAD_RIGHT
	BUTTONS[78]=KEY_GAMEPAD_DPAD_DOWN
	BUTTONS[79]=KEY_GAMEPAD_P_MINUS
	BUTTONS[80]=KEY_GAMEPAD_P_PLUS
	BUTTONS[81]=KEY_GAMEPAD_W_MINUS
	BUTTONS[82]=KEY_GAMEPAD_W_PLUS
	BUTTONS[83]=KEY_GAMEPAD_R_MINUS
	BUTTONS[84]=KEY_GAMEPAD_R_PLUS

	Function RETURN_BUTTON_FROM_KEY(input)
		For i=1 to 84
			If BUTTONS[i]=input Return i
		Next
	End Function

	Function RETURN_KEY_FROM_BUTTONS(input)
		Select input
			Case 1: Return KEY_A
			Case 2: Return KEY_B
			Case 3: Return KEY_C
			Case 4: Return KEY_D
			Case 5: Return KEY_E
			Case 6: Return KEY_F
			Case 7: Return KEY_G
			Case 8: Return KEY_H
			Case 9: Return KEY_I
			Case 10: Return KEY_J
			Case 11: Return KEY_K
			Case 12: Return KEY_L
			Case 13: Return KEY_M
			Case 14: Return KEY_N
			Case 15: Return KEY_O
			Case 16: Return KEY_P
			Case 17: Return KEY_Q
			Case 18: Return KEY_R
			Case 19: Return KEY_S
			Case 20: Return KEY_T
			Case 21: Return KEY_U
			Case 22: Return KEY_V
			Case 23: Return KEY_W
			Case 24: Return KEY_X
			Case 25: Return KEY_Y
			Case 26: Return KEY_Z
			Case 27: Return KEY_HYPHEN
			Case 28: Return KEY_BACKSPACE
			Case 29: Return KEY_ENTER
			Case 30: Return KEY_PLUS
			Case 31: Return KEY_0
			Case 32: Return KEY_1
			Case 33: Return KEY_2
			Case 34: Return KEY_3
			Case 35: Return KEY_4
			Case 36: Return KEY_5
			Case 37: Return KEY_6
			Case 38: Return KEY_7
			Case 39: Return KEY_8
			Case 40: Return KEY_9
			Case 41: Return KEY_TAB
			Case 42: Return KEY_CTRL_LEFT
			Case 43: Return KEY_CTRL_RIGHT
			Case 44: Return KEY_SHIFT_LEFT
			Case 45: Return KEY_SHIFT_RIGHT
			Case 46: Return KEY_ALT_LEFT
			Case 47: Return KEY_ALT_RIGHT
			Case 48: Return KEY_ESCAPE
			Case 49: Return KEY_SPACE
			Case 50: Return KEY_ARROW_UP
			Case 51: Return KEY_ARROW_DOWN
			Case 52: Return KEY_ARROW_LEFT
			Case 53: Return KEY_ARROW_RIGHT
			Case 54: Return KEY_MOUSE_LEFT
			Case 55: Return KEY_MOUSE_RIGHT
			Case 56: Return KEY_MOUSE_MIDDLE
			Case 57: Return KEY_GAMEPAD_BUTTON1
			Case 58: Return KEY_GAMEPAD_BUTTON2
			Case 59: Return KEY_GAMEPAD_BUTTON3
			Case 60: Return KEY_GAMEPAD_BUTTON4
			Case 61: Return KEY_GAMEPAD_BUTTON5
			Case 62: Return KEY_GAMEPAD_BUTTON6
			Case 63: Return KEY_GAMEPAD_BUTTON7
			Case 64: Return KEY_GAMEPAD_BUTTON8
			Case 65: Return KEY_GAMEPAD_BUTTON9
			Case 66: Return KEY_GAMEPAD_BUTTON10
			Case 67: Return KEY_GAMEPAD_BUTTON11
			Case 68: Return KEY_GAMEPAD_BUTTON12
			Case 69: Return KEY_GAMEPAD_X_MINUS
			Case 70: Return KEY_GAMEPAD_X_PLUS
			Case 71: Return KEY_GAMEPAD_Y_MINUS
			Case 72: Return KEY_GAMEPAD_Y_PLUS
			Case 73: Return KEY_GAMEPAD_Z_MINUS
			Case 74: Return KEY_GAMEPAD_Z_PLUS
			Case 75: Return KEY_GAMEPAD_DPAD_UP
			Case 76: Return KEY_GAMEPAD_DPAD_LEFT
			Case 77: Return KEY_GAMEPAD_DPAD_RIGHT
			Case 78: Return KEY_GAMEPAD_DPAD_DOWN
			Case 79: Return KEY_GAMEPAD_P_MINUS
			Case 80: Return KEY_GAMEPAD_P_PLUS
			Case 81: Return KEY_GAMEPAD_W_MINUS
			Case 82: Return KEY_GAMEPAD_W_PLUS
			Case 83: Return KEY_GAMEPAD_R_MINUS
			Case 84: Return KEY_GAMEPAD_R_PLUS
		End Select
	End Function






















;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------
;-----------------------------------------------------------------------------------------------------

	; ---- Other constants ----
	Const GFX_DEFAULT 			= 0
	Const GFX_FULLSCREEN 		= 1
	Const GFX_WINDOWED 			= 2
	Const GFX_WINDOWEDSCALED 	= 3
	
	Const PROJ_NONE 			= 0
	Const PROJ_PERSPECTIVE 		= 1
	Const PROJ_ORTHO 			= 2
	
	Const LIGHT_DIRECTIONAL 	= 1
	Const LIGHT_POINT 			= 2
	Const LIGHT_SPOT 			= 3
	
	Const TX_COLOR 				= 1
	Const TX_ALPHA 				= 2
	Const TX_MASKED 			= 4
	Const TX_MIP 				= 8
	Const TX_CLAMPU 			= 16
	Const TX_CLAMPV 			= 32
	Const TX_SPHERE 			= 64
	Const TX_CUBIC 				= 128
	Const TX_VRAM 				= 256
	Const TX_HIGHCOLOR 			= 512
	
	Const TX_BLEND_NONE 		= 0
	Const TX_BLEND_ALPHA 		= 1
	Const TX_BLEND_MULT 		= 2
	Const TX_BLEND_ADD 			= 3	
	Const TX_BLEND_DOT3 		= 4
	Const TX_BLEND_MULT2 		= 5
	
	Const CUBEFACE_LEFT 		= 0
	Const CUBEFACE_FRONT 		= 1
	Const CUBEFACE_RIGHT 		= 2
	Const CUBEFACE_BACK 		= 3
	Const CUBEFACE_TOP 			= 4
	Const CUBEFACE_BOTTOM 		= 5
	
	Const CUBEMODE_SPECULAR 	= 1
	Const CUBEMODE_DIFFUSE 		= 2
	Const CUBEMODE_REFRACTION 	= 3
	
	Const BRUSHBLEND_NONE 		= 0
	Const BRUSHBLEND_MULTIPLY 	= 1
	Const BRUSHBLEND_ALPHA 		= 2
	Const BRUSHBLEND_ADD 		= 3
	
	Const BRUSHFX_NONE 			= 0
	Const BRUSHFX_FULLBRIGHT 	= 1
	Const BRUSHFX_VERTEXCOLOR 	= 2
	Const BRUSHFX_FLAT 			= 4
	Const BRUSHFX_NOFOG 		= 8
	Const BRUSHFX_DOUBLESIDED 	= 16
	Const BRUSHFX_VERTEXALPHA 	= 32
	
	Const COLLIDE_SPHERESPHERE 	= 1
	Const COLLIDE_SPHEREPOLY 	= 2
	Const COLLIDE_SPHEREBOX 	= 3
	
	Const COLLIDE_STOP 			= 1
	Const COLLIDE_SLIDE1 		= 2
	Const COLLIDE_SLIDE2 		= 3
	
	Const PICK_NONE 			= 0
	Const PICK_SPHERE 			= 1
	Const PICK_POLY 			= 2
	Const PICK_BOX 				= 3
	
	Const ANIM_STOP 			= 0
	Const ANIM_LOOP 			= 1
	Const ANIM_PINGPONG 		= 2
	Const ANIM_ONCE 			= 3
	
	Const SPRITE_TURNXY 		= 1
	Const SPRITE_STILL 			= 2
	Const SPRITE_ALIGNZ 		= 3
	Const SPRITE_TURNY 			= 4
	
	Const PLAYCD_SINGLE 		= 1
	Const PLAYCD_LOOP 			= 2
	Const PLAYCD_ALL 			= 3
	
	Const MOUSE_BUTTON 			= 1
	Const MOUSE_RIGHTBUTTON 	= 2
	Const MOUSE_MIDDLEBUTTON 	= 3
	
	Const JOYTYPE_NONE 			= 0
	Const JOYTYPE_DIGITAL 		= 1 
	Const JOYTYPE_ANALOG 		= 2