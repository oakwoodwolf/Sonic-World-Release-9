
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

i=1
Global TIP_MOVE				= i : i=i+1
Global TIP_CHANGE			= i : i=i+1
Global TIP_SUPER			= i : i=i+1
Global TIP_SUPER2			= i : i=i+1
Global TIP_HYPERBLAST		= i : i=i+1
Global TIP_JUMP				= i : i=i+1
Global TIP_JUMPHIGHER		= i : i=i+1
Global TIP_JACHANGE			= i : i=i+1
Global TIP_STOMP			= i : i=i+1
Global TIP_BOUNCE			= i : i=i+1
Global TIP_SPINDASH			= i : i=i+1
Global TIP_CHARGE			= i : i=i+1
Global TIP_ROLL				= i : i=i+1
Global TIP_BREAK			= i : i=i+1
Global TIP_DRIFT			= i : i=i+1
Global TIP_LOOK				= i : i=i+1
Global TIP_PICKINGUP		= i : i=i+1
Global TIP_PICKUP			= i : i=i+1
Global TIP_DROP				= i : i=i+1
Global TIP_THROW			= i : i=i+1
Global TIP_ASCEND			= i : i=i+1
Global TIP_DESCEND			= i : i=i+1
Global TIP_GLIDE			= i : i=i+1
Global TIP_FLAP				= i : i=i+1
Global TIP_GRINDFASTER		= i : i=i+1
Global TIP_ACCELERATE		= i : i=i+1
Global TIP_SKYDIVEFASTER	= i : i=i+1
Global TIP_SHOOT			= i : i=i+1
Global TIP_PADDLES			= i : i=i+1
Global TIP_HOPOFF			= i : i=i+1
Global TIP_RELEASEINVENTORY	= i : i=i+1
Global TIP_WHISTLE			= i : i=i+1
Global TIP_PET				= i : i=i+1
Global TIP_CHEER			= i : i=i+1
Global TIP_SHAKETREE		= i : i=i+1
Global TIP_SHAKEVINE		= i : i=i+1
Global TIP_DEMOLISH			= i : i=i+1
Global TIP_BELLYFLOP		= i : i=i+1
Global TIP_HAMMERDOWN		= i : i=i+1
Global TIP_TRICK			= i : i=i+1
Global TIP_BATTLEMODE		= i : i=i+1
Global TIP_STOMPSKILL[CHAR_NONMODPLAYABLECOUNT]			: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_STOMPSKILL[j] = i : i=i+1 : Next
Global TIP_JUMPA[CHAR_NONMODPLAYABLECOUNT]			: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_JUMPA[j] = i : i=i+1 : Next
Global TIP_ShortJUMPA[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_ShortJUMPA[j] = i : i=i+1 : Next
Global TIP_HoldJUMPA[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldJUMPA[j] = 0 : Next
Global TIP_JUMPA2[CHAR_NONMODPLAYABLECOUNT]			: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_JUMPA2[j] = i : i=i+1 : Next
Global TIP_ShortJUMPA2[CHAR_NONMODPLAYABLECOUNT]	: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_ShortJUMPA2[j] = i : i=i+1 : Next
Global TIP_HoldJUMPA2[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldJUMPA2[j] = 0 : Next
Global TIP_SKILL1[CHAR_NONMODPLAYABLECOUNT]			: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_SKILL1[j] = i : i=i+1 : Next
Global TIP_SKILL1AIR[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_SKILL1AIR[j] = i : i=i+1 : Next
Global TIP_HoldSKILL1[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldSKILL1[j] = 0 : Next
Global TIP_HoldSKILL1AIR[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldSKILL1AIR[j] = 0 : Next
Global TIP_SKILL2[CHAR_NONMODPLAYABLECOUNT]			: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_SKILL2[j] = i : i=i+1 : Next
Global TIP_SKILL2AIR[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_SKILL2AIR[j] = i : i=i+1 : Next
Global TIP_HoldSKILL2[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldSKILL2[j] = 0 : Next
Global TIP_HoldSKILL2AIR[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldSKILL2AIR[j] = 0 : Next
Global TIP_SKILL3[CHAR_NONMODPLAYABLECOUNT]			: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_SKILL3[j] = i : i=i+1 : Next
Global TIP_SKILL3AIR[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_SKILL3AIR[j] = i : i=i+1 : Next
Global TIP_HoldSKILL3[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldSKILL3[j] = 0 : Next
Global TIP_HoldSKILL3AIR[CHAR_NONMODPLAYABLECOUNT]		: For j = 1 To CHAR_NONMODPLAYABLECOUNT : TIP_HoldSKILL3AIR[j] = 0 : Next
Global TIP_SKILL2X			= i : i=i+1
;-------------------------------------------------------------------------------------------------------------------------
Dim CONTROLTIPS$(i-1)
CONTROLTIPS$(TIP_MOVE)				= "Move"
CONTROLTIPS$(TIP_CHANGE)			= "Change leader"
CONTROLTIPS$(TIP_SUPER)				= "Super transform"
CONTROLTIPS$(TIP_SUPER2)			= "Hyper transform"
CONTROLTIPS$(TIP_HYPERBLAST)		= "Hyper blast"
CONTROLTIPS$(TIP_JUMP)				= "Jump"
CONTROLTIPS$(TIP_JUMPHIGHER)		= "Jump higher"
CONTROLTIPS$(TIP_JACHANGE)			= "JA change"
CONTROLTIPS$(TIP_STOMP)				= "Stomp"
CONTROLTIPS$(TIP_BOUNCE)			= "Bounce"
CONTROLTIPS$(TIP_SPINDASH)			= "Spin dash"
CONTROLTIPS$(TIP_CHARGE)			= "Charge"
CONTROLTIPS$(TIP_ROLL)				= "Roll"
CONTROLTIPS$(TIP_BREAK)				= "Break"
CONTROLTIPS$(TIP_DRIFT)				= "Drift"
CONTROLTIPS$(TIP_LOOK)				= "Look around"
CONTROLTIPS$(TIP_PICKINGUP)			= "Picking up"
CONTROLTIPS$(TIP_PICKUP)			= "Pick up"
CONTROLTIPS$(TIP_DROP)				= "Drop"
CONTROLTIPS$(TIP_THROW)				= "Throw"
CONTROLTIPS$(TIP_ASCEND)			= "Ascend"
CONTROLTIPS$(TIP_DESCEND)			= "Descend"
CONTROLTIPS$(TIP_GLIDE)				= "Glide"
CONTROLTIPS$(TIP_FLAP)				= "Flap"
CONTROLTIPS$(TIP_GRINDFASTER)		= "Grind faster"
CONTROLTIPS$(TIP_ACCELERATE)		= "Accelerate"
CONTROLTIPS$(TIP_SKYDIVEFASTER)		= "Skydive faster"
CONTROLTIPS$(TIP_SHOOT)				= "Shoot"
CONTROLTIPS$(TIP_PADDLES)			= "Paddles"
CONTROLTIPS$(TIP_HOPOFF)			= "Hop off"
CONTROLTIPS$(TIP_RELEASEINVENTORY)	= "Release inventory"
CONTROLTIPS$(TIP_WHISTLE)			= "Whistle"
CONTROLTIPS$(TIP_PET)				= "Pet"
CONTROLTIPS$(TIP_CHEER)				= "Cheer"
CONTROLTIPS$(TIP_SHAKETREE)			= "Shake tree"
CONTROLTIPS$(TIP_SHAKEVINE)			= "Shake vine"
CONTROLTIPS$(TIP_DEMOLISH)			= "Demolish"
CONTROLTIPS$(TIP_BELLYFLOP)			= "Belly flop"
CONTROLTIPS$(TIP_HAMMERDOWN)		= "Hammer down"
CONTROLTIPS$(TIP_TRICK)				= "Trick"
CONTROLTIPS$(TIP_BATTLEMODE)		= "Battle mode"
;---------------------------------------------------------------------------
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_SON])	= "Bounce"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_TAI])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_KNU])	= "Drill Dive"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_AMY])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_SHA])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_ROU])	= "Uppercut"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_CRE])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_BLA])	= "Scorch flare"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_SIL])	= "Psychic knife"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_OME])	= "Gatling gun"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_ESP])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_CHA])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_VEC])	= "Musical blow"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_BIG])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_MAR])	= "Bubble beam"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_MIG])	= "Rock"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_RAY])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_CHO])	= "Puddle form"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_TIK])	= "Heaven's justice"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_NAC])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_BEA])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_BAR])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_JET])	= "Razor leaf"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_WAV])	= "Gear"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_STO])	= "Tire"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_TIA])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_HON])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_SHD])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_MPH])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_HBO])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_GAM])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_EME])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_MET])	= "Black shield"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_TDL])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_MKN])	= "Uppercut"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_EGG])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_BET])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_MT3])	= "Black shield"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_GME])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_PRS])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_COM])	= "Uppercut"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_CHW])	= ""
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_EGR])	= "Power Stomp"
CONTROLTIPS$(TIP_STOMPSKILL[CHAR_INF])	= "Invert gravity"

;---------------------------------------------------------------------------
CONTROLTIPS$(TIP_JUMPA[CHAR_SON])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_TAI])	= "Fly" : TIP_HoldJUMPA[CHAR_TAI]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_KNU])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_KNU]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_AMY])	= "Double jump, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_SHA])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_ROU])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_ROU]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_CRE])	= "Fly"
CONTROLTIPS$(TIP_JUMPA[CHAR_BLA])	= "Double jump, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_SIL])	= "Levitate" : TIP_HoldJUMPA[CHAR_SIL]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_OME])	= "Hover" : TIP_HoldJUMPA[CHAR_OME]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_ESP])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_CHA])	= "Fly"
CONTROLTIPS$(TIP_JUMPA[CHAR_VEC])	= "Slow glide" : TIP_HoldJUMPA[CHAR_VEC]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_BIG])	= "Slow glide" : TIP_HoldJUMPA[CHAR_BIG]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_MAR])	= "Flutter" : TIP_HoldJUMPA[CHAR_MAR]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_MIG])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_RAY])	= "Soar" : TIP_HoldJUMPA[CHAR_RAY]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_CHO])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_CHO]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_TIK])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_TIK]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_NAC])	= "Double jump"
CONTROLTIPS$(TIP_JUMPA[CHAR_BEA])	= "Double jump, bomb"
CONTROLTIPS$(TIP_JUMPA[CHAR_BAR])	= "Sleet" : TIP_HoldJUMPA[CHAR_BAR]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_JET])	= "Dive, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_WAV])	= "Fly"
CONTROLTIPS$(TIP_JUMPA[CHAR_STO])	= "Hover" : TIP_HoldJUMPA[CHAR_STO]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_TIA])	= "Parachute" : TIP_HoldJUMPA[CHAR_TIA]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_HON])	= "Flutter" : TIP_HoldJUMPA[CHAR_HON]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_SHD])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_SHD]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_MPH])	= "Levitate" : TIP_HoldJUMPA[CHAR_MPH]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_HBO])	= "Hover" : TIP_HoldJUMPA[CHAR_HBO]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_GAM])	= "Hover" : TIP_HoldJUMPA[CHAR_GAM]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_EME])	= "Jump action"
CONTROLTIPS$(TIP_JUMPA[CHAR_MET])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_TDL])	= "Fly"
CONTROLTIPS$(TIP_JUMPA[CHAR_MKN])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_MKN]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_EGG])	= "Hover" : TIP_HoldJUMPA[CHAR_EGG]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_BET])	= "Hover" : TIP_HoldJUMPA[CHAR_BET]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_MT3])	= "Levitate" : TIP_HoldJUMPA[CHAR_MT3]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_GME])	= "Jump action"
CONTROLTIPS$(TIP_JUMPA[CHAR_PRS])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA[CHAR_COM])	= "Glide, climb" : TIP_HoldJUMPA[CHAR_COM]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_CHW])	= "Hover" : TIP_HoldJUMPA[CHAR_CHW]=1
CONTROLTIPS$(TIP_JUMPA[CHAR_EGR])	= "Fly"
CONTROLTIPS$(TIP_JUMPA[CHAR_INF])	= "Levitate" : TIP_HoldJUMPA[CHAR_INF]=1
For i=1 To CHAR_NONMODPLAYABLECOUNT
	CONTROLTIPS$(TIP_ShortJUMPA[i]) = CONTROLTIPS$(TIP_JUMPA[i])
Next
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_SON])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_AMY])	= "Double jump, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_SHA])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_BLA])	= "Double jump, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_ESP])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_VEC])	= "Slow glide"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_MIG])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_BEA])	= "Double jump"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_JET])	= "Dive, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_MET])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA[CHAR_PRS])	= "Jump dash, hom"
;---------------------------------------------------------------------------
CONTROLTIPS$(TIP_JUMPA2[CHAR_SON])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_TAI])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_KNU])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_AMY])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA2[CHAR_SHA])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_ROU])	= "Fly"
CONTROLTIPS$(TIP_JUMPA2[CHAR_CRE])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_BLA])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA2[CHAR_SIL])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_OME])	= "Glide" : TIP_HoldJUMPA2[CHAR_OME]=1
CONTROLTIPS$(TIP_JUMPA2[CHAR_ESP])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_CHA])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_VEC])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_BIG])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_MAR])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_MIG])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_RAY])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_CHO])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_TIK])	= "Fly"
CONTROLTIPS$(TIP_JUMPA2[CHAR_NAC])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA2[CHAR_BEA])	= "Fly"
CONTROLTIPS$(TIP_JUMPA2[CHAR_BAR])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_JET])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA2[CHAR_WAV])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_STO])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_TIA])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_HON])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_SHD])	= "Jump dash, homing attack"
CONTROLTIPS$(TIP_JUMPA2[CHAR_MPH])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_HBO])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_GAM])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_EME])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_MET])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_TDL])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_MKN])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_EGG])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_BET])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_MT3])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_GME])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_PRS])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_COM])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_CHW])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_EGR])	= ""
CONTROLTIPS$(TIP_JUMPA2[CHAR_INF])	= "Jump dash, homing attack"
For i=1 To CHAR_NONMODPLAYABLECOUNT
	CONTROLTIPS$(TIP_ShortJUMPA2[i]) = CONTROLTIPS$(TIP_JUMPA2[i])
Next
CONTROLTIPS$(TIP_ShortJUMPA2[CHAR_AMY])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA2[CHAR_BLA])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA2[CHAR_NAC])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA2[CHAR_JET])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA2[CHAR_SHD])	= "Jump dash, hom"
CONTROLTIPS$(TIP_ShortJUMPA2[CHAR_INF])	= "Jump dash, hom"
;---------------------------------------------------------------------------
CONTROLTIPS$(TIP_SKILL1[CHAR_SON])	= "Spin kick"
CONTROLTIPS$(TIP_SKILL1[CHAR_TAI])	= "Tail swipe" : TIP_HoldSKILL1[CHAR_TAI]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_KNU])	= "Punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_AMY])	= "Hammer spin" : TIP_HoldSKILL1[CHAR_AMY]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_SHA])	= "Punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_ROU])	= "Heart bombs"
CONTROLTIPS$(TIP_SKILL1[CHAR_CRE])	= "Cheese"
CONTROLTIPS$(TIP_SKILL1[CHAR_BLA])	= "Fire claw" : TIP_HoldSKILL1[CHAR_BLA]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_SIL])	= "Psychokinesis"
CONTROLTIPS$(TIP_SKILL1[CHAR_OME])	= "Omega shots"
CONTROLTIPS$(TIP_SKILL1[CHAR_ESP])	= "Ninja star"
CONTROLTIPS$(TIP_SKILL1[CHAR_CHA])	= "Needle dash"
CONTROLTIPS$(TIP_SKILL1[CHAR_VEC])	= "Mouth punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_BIG])	= "Umbrella swing"
CONTROLTIPS$(TIP_SKILL1[CHAR_MAR])	= "Water pulse" : TIP_HoldSKILL1[CHAR_MAR]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_MIG])	= "Punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_RAY])	= "Tail swipe" : TIP_HoldSKILL1[CHAR_RAY]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_CHO])	= "Stretch punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_TIK])	= "Silent palm"
CONTROLTIPS$(TIP_SKILL1[CHAR_NAC])	= "Shotgun"
CONTROLTIPS$(TIP_SKILL1[CHAR_BEA])	= "Bomb"
CONTROLTIPS$(TIP_SKILL1[CHAR_BAR])	= "Punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_JET])	= "Palm fans"
CONTROLTIPS$(TIP_SKILL1[CHAR_WAV])	= "Giant wrench" : TIP_HoldSKILL1[CHAR_WAV]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_STO])	= "Punch, slap"
CONTROLTIPS$(TIP_SKILL1[CHAR_TIA])	= "Magical beam" : TIP_HoldSKILL1[CHAR_TIA]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_HON])	= "Kick"
CONTROLTIPS$(TIP_SKILL1[CHAR_SHD])	= "Blade punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_MPH])	= "Send minions"
CONTROLTIPS$(TIP_SKILL1[CHAR_HBO])	= "Explode punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_GAM])	= "Aim / shoot" : TIP_HoldSKILL1[CHAR_GAM]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_EME])	= "Attack action"
CONTROLTIPS$(TIP_SKILL1[CHAR_MET])	= "Energy shock"
CONTROLTIPS$(TIP_SKILL1[CHAR_TDL])	= "Curse"
CONTROLTIPS$(TIP_SKILL1[CHAR_MKN])	= "Rocket bomb"
CONTROLTIPS$(TIP_SKILL1[CHAR_EGG])	= "Aim / shoot" : TIP_HoldSKILL1[CHAR_EGG]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_BET])	= "Shoot"
CONTROLTIPS$(TIP_SKILL1[CHAR_MT3])	= "Energy shock"
CONTROLTIPS$(TIP_SKILL1[CHAR_GME])	= "Attack action"
CONTROLTIPS$(TIP_SKILL1[CHAR_PRS])	= "Spin kick"
CONTROLTIPS$(TIP_SKILL1[CHAR_COM])	= "Punch"
CONTROLTIPS$(TIP_SKILL1[CHAR_CHW])	= "Aim / shoot" : TIP_HoldSKILL1[CHAR_CHW]=1
CONTROLTIPS$(TIP_SKILL1[CHAR_EGR])	= "Triple shots"
CONTROLTIPS$(TIP_SKILL1[CHAR_INF])	= "Whip kick"
For i=1 To CHAR_NONMODPLAYABLECOUNT
	Select i
	Case CHAR_TAI
		CONTROLTIPS$(TIP_SKILL1AIR[i]) = CONTROLTIPS$(TIP_SKILL1[i])
	Default
	CONTROLTIPS$(TIP_SKILL1AIR[i]) = CONTROLTIPS$(TIP_SKILL1[i])
	TIP_HoldSKILL1AIR[i] = TIP_HoldSKILL1[i]
	End Select 
Next
CONTROLTIPS$(TIP_SKILL1AIR[CHAR_SON])	= "Boom kick"
CONTROLTIPS$(TIP_SKILL1AIR[CHAR_SHA])	= "Chaos spear"
CONTROLTIPS$(TIP_SKILL1AIR[CHAR_MIG])	= "Boom kick"
CONTROLTIPS$(TIP_SKILL1AIR[CHAR_STO])	= "Slap"
CONTROLTIPS$(TIP_SKILL1AIR[CHAR_PRS])	= "Boom kick"
CONTROLTIPS$(TIP_SKILL1AIR[CHAR_INF])	= "Boom kick"
;---------------------------------------------------------------------------
CONTROLTIPS$(TIP_SKILL2[CHAR_SON])	= "Lightdash"
CONTROLTIPS$(TIP_SKILL2[CHAR_TAI])	= "Cannon shot"
CONTROLTIPS$(TIP_SKILL2[CHAR_KNU])	= "Fireballs"
CONTROLTIPS$(TIP_SKILL2[CHAR_AMY])	= "Flower typhoon"
CONTROLTIPS$(TIP_SKILL2[CHAR_SHA])	= "Chaos control"
CONTROLTIPS$(TIP_SKILL2[CHAR_ROU])	= "Screw kick"
CONTROLTIPS$(TIP_SKILL2[CHAR_CRE])	= "Chao Shield"
CONTROLTIPS$(TIP_SKILL2[CHAR_BLA])	= "Fireballs"
CONTROLTIPS$(TIP_SKILL2[CHAR_SIL])	= "Teleport dash"
CONTROLTIPS$(TIP_SKILL2[CHAR_OME])	= "Punch"
CONTROLTIPS$(TIP_SKILL2[CHAR_ESP])	= "Screw kick"
CONTROLTIPS$(TIP_SKILL2[CHAR_CHA])	= "Flower trap"
CONTROLTIPS$(TIP_SKILL2[CHAR_VEC])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_BIG])	= "Froggy poison"
CONTROLTIPS$(TIP_SKILL2[CHAR_MAR])	= "Boomerang"
CONTROLTIPS$(TIP_SKILL2[CHAR_MIG])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_RAY])	= "Dart"
CONTROLTIPS$(TIP_SKILL2[CHAR_CHO])	= "Water blobs"
CONTROLTIPS$(TIP_SKILL2[CHAR_TIK])	= "Spiritual form" : TIP_HoldSKILL2[CHAR_TIK]=1
CONTROLTIPS$(TIP_SKILL2[CHAR_NAC])	= "Tail whip"
CONTROLTIPS$(TIP_SKILL2[CHAR_BEA])	= "Peck"
CONTROLTIPS$(TIP_SKILL2[CHAR_BAR])	= "Ice spark"
CONTROLTIPS$(TIP_SKILL2[CHAR_JET])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_WAV])	= "Explosives"
CONTROLTIPS$(TIP_SKILL2[CHAR_STO])	= "Hurricane"
CONTROLTIPS$(TIP_SKILL2[CHAR_TIA])	= "Kick"
CONTROLTIPS$(TIP_SKILL2[CHAR_HON])	= "Meow sprint"
CONTROLTIPS$(TIP_SKILL2[CHAR_SHD])	= "Hookshot"
CONTROLTIPS$(TIP_SKILL2[CHAR_MPH])	= "Teleport dash"
CONTROLTIPS$(TIP_SKILL2[CHAR_HBO])	= "Giant bombs"
CONTROLTIPS$(TIP_SKILL2[CHAR_GAM])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_EME])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_MET])	= "Electric dash"
CONTROLTIPS$(TIP_SKILL2[CHAR_TDL])	= "Tail swipe" : TIP_HoldSKILL2[CHAR_TDL]=1
CONTROLTIPS$(TIP_SKILL2[CHAR_MKN])	= "Punch"
CONTROLTIPS$(TIP_SKILL2[CHAR_EGG])	= "Stretch punch"
CONTROLTIPS$(TIP_SKILL2[CHAR_BET])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_MT3])	= "Electric dash"
CONTROLTIPS$(TIP_SKILL2[CHAR_GME])	= "Attack action"
CONTROLTIPS$(TIP_SKILL2[CHAR_PRS])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_COM])	= "Gun shot"
CONTROLTIPS$(TIP_SKILL2[CHAR_CHW])	= ""
CONTROLTIPS$(TIP_SKILL2[CHAR_EGR])	= "Rocket Bomb"
CONTROLTIPS$(TIP_SKILL2[CHAR_INF])	= "Cube trails"
For i=1 To CHAR_NONMODPLAYABLECOUNT
	CONTROLTIPS$(TIP_SKILL2AIR[i]) = CONTROLTIPS$(TIP_SKILL2[i])
	TIP_HoldSKILL2AIR[i] = TIP_HoldSKILL2[i]
Next
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_SON])	= "Lightdash"
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_CRE])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_KNU])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_OME])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_ESP])	= "Swirl, climb"
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_CHO])	= "Chaos spear"
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_NAC])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_STO])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_HBO])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_EGG])	= ""
CONTROLTIPS$(TIP_SKILL2AIR[CHAR_EGR])	= ""
CONTROLTIPS$(TIP_SKILL2X)				= "Rocket punch"
;---------------------------------------------------------------------------
CONTROLTIPS$(TIP_SKILL3[CHAR_SON])	= "Lightspeed Attack"
CONTROLTIPS$(TIP_SKILL3[CHAR_TAI])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_KNU])	= "Uppercut"
CONTROLTIPS$(TIP_SKILL3[CHAR_AMY])	= "Hammer smash"
CONTROLTIPS$(TIP_SKILL3[CHAR_SHA])	= "Chaos boost"
CONTROLTIPS$(TIP_SKILL3[CHAR_ROU])	= "Uppercut"
CONTROLTIPS$(TIP_SKILL3[CHAR_CRE])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_BLA])	= "Scorch flare"
CONTROLTIPS$(TIP_SKILL3[CHAR_SIL])	= "Psychic knife"
CONTROLTIPS$(TIP_SKILL3[CHAR_OME])	= "Gatling gun" : TIP_HoldSKILL3[CHAR_OME]=1
CONTROLTIPS$(TIP_SKILL3[CHAR_ESP])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_CHA])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_VEC])	= "Musical blow" : TIP_HoldSKILL3[CHAR_VEC]=1
CONTROLTIPS$(TIP_SKILL3[CHAR_BIG])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_MAR])	= "Bubble beam"
CONTROLTIPS$(TIP_SKILL3[CHAR_MIG])	= "Rock"
CONTROLTIPS$(TIP_SKILL3[CHAR_RAY])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_CHO])	= "Puddle form" : TIP_HoldSKILL3[CHAR_CHO]=1
CONTROLTIPS$(TIP_SKILL3[CHAR_TIK])	= "Heaven's justice"
CONTROLTIPS$(TIP_SKILL3[CHAR_NAC])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_BEA])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_BAR])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_JET])	= "Razor leaf"
CONTROLTIPS$(TIP_SKILL3[CHAR_WAV])	= "Gear"
CONTROLTIPS$(TIP_SKILL3[CHAR_STO])	= "Tire"
CONTROLTIPS$(TIP_SKILL3[CHAR_TIA])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_HON])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_SHD])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_MPH])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_HBO])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_GAM])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_EME])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_MET])	= "Black shield" : TIP_HoldSKILL3[CHAR_MET]=1
CONTROLTIPS$(TIP_SKILL3[CHAR_TDL])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_MKN])	= "Uppercut"
CONTROLTIPS$(TIP_SKILL3[CHAR_EGG])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_BET])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_MT3])	= "Black shield" : TIP_HoldSKILL3[CHAR_MT3]=1
CONTROLTIPS$(TIP_SKILL3[CHAR_GME])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_PRS])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_COM])	= "Uppercut"
CONTROLTIPS$(TIP_SKILL3[CHAR_CHW])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_EGR])	= ""
CONTROLTIPS$(TIP_SKILL3[CHAR_INF])	= "Invert gravity"
For i=1 To CHAR_NONMODPLAYABLECOUNT
	CONTROLTIPS$(TIP_SKILL3AIR[i]) = CONTROLTIPS$(TIP_SKILL3[i])
	TIP_HoldSKILL3AIR[i] = TIP_HoldSKILL3[i]
Next
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_TAI])	= ""
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_KNU])	= "Rock"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_AMY])	= "Invisibility"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_ROU])	= ""
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_OME])	= ""
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_ESP])	= "Invisibility"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_VEC])	= ""
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_BIG])	= "Swimming buoy"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_CHO])	= "Blob press" : TIP_HoldSKILL3AIR[CHAR_CHO]=0
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_SHD])	= "Helmet"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_GAM])	= "Swimming propeller"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_EME])	= "Mode change"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_MKN])	= ""
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_BET])	= "Swimming propeller"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_GME])	= "Mode change"
CONTROLTIPS$(TIP_SKILL3AIR[CHAR_COM])	= ""
;---------------------------------------------------------------------------

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Interface_ControlTipUpdate(tiptype)
	Game\Interface\ControlTipType=tiptype
End Function

Function Interface_ControlTipUpdate_PickUp(tiptype)
	Game\Interface\ControlTipTypePickUp=tiptype
	Game\Interface\ControlTipPickUpTimer=0.5*secs#
End Function

Function Interface_ControlTipDraw_Button(tip$, control, x#, y#, hold=False, nocolor=False)
	Game\Interface\ControlTip$=tip$
	DrawRealText(Game\Interface\ControlTip$, x#-12.5*GAME_WINDOW_SCALE#, y#, (Interface_Text_2), 2)
	DrawSmartKey_Small(control, x#, y#, nocolor)
	If hold Then DrawImageEx(INTERFACE(Interface_Keys_Small), x#, y#-3.5*GAME_WINDOW_SCALE#, 82)
End Function

Function Interface_ControlTipDraw_Button2(tip$, control, x#, y#, nocolor=False)
	Game\Interface\ControlTip$=tip$
	DrawRealText(Game\Interface\ControlTip$, x#+12.5*GAME_WINDOW_SCALE#, y#, (Interface_Text_2), 0)
	DrawSmartKey_Small(control, x#, y#, nocolor)
End Function

Function Interface_ControlTipDraw(p.tPlayer, x#, y#)
	Select p\Character
		Case CHAR_TMH: char=CHAR_EGG
		Default: char=p\Character
	End Select

	i=0
	If Menu\Members>1 And p\Flags\MayChangeCharacter Then
		Interface_ControlTipDraw_Button2(CONTROLTIPS$(TIP_CHANGE), INPUT_BUTTON_CHANGE, Abs(x#-GAME_WINDOW_W), GAME_WINDOW_H-(60)*GAME_WINDOW_SCALE#-i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		i=i+1
	EndIf
	If Len(CONTROLTIPS$(TIP_JUMPA2[char]))>0 Then
		Interface_ControlTipDraw_Button2(CONTROLTIPS$(TIP_JACHANGE)+" ("+Str(p\JumpActionMode+1)+")", INPUT_BUTTON_BACK, Abs(x#-GAME_WINDOW_W), GAME_WINDOW_H-(60)*GAME_WINDOW_SCALE#-i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
		i=i+1
	EndIf	
	If Menu\Stage>0 And UNLOCKEDEMERALDS[7]=1 And p\Flags\CanSuperTransform And Game\Gameplay\Rings>=50+50*Game\SuperForm Then
		If (Not(Game\Interface\ControlTipPickUpTimer>0)) Then
			If Game\SuperForm<1 Then
				Interface_ControlTipDraw_Button2(CONTROLTIPS$(TIP_SUPER), INPUT_BUTTON_ACTIONACT, Abs(x#-GAME_WINDOW_W), GAME_WINDOW_H-(60)*GAME_WINDOW_SCALE#-i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
				i=i+1
			ElseIf Game\SuperForm<2 Then
				Interface_ControlTipDraw_Button2(CONTROLTIPS$(TIP_SUPER2), INPUT_BUTTON_ACTIONACT, Abs(x#-GAME_WINDOW_W), GAME_WINDOW_H-(60)*GAME_WINDOW_SCALE#-i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
				i=i+1
			EndIf
		EndIf
	EndIf
	If p\AirBegTooFar Then
		DrawImageEx(INTERFACE(Interface_Icons), 20*GAME_WINDOW_SCALE#, GAME_WINDOW_H-(60)*GAME_WINDOW_SCALE#-1.25*i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, 1)
		i=i+1
	EndIf

	i=-1
	If Game\Interface\ControlTipPickUpTimer>0 Then
		Game\Interface\ControlTipPickUpTimer=Game\Interface\ControlTipPickUpTimer-timervalue#
		Select Game\Interface\ControlTipTypePickUp
			Case 1:
				If p\Action=ACTION_COMMON Then Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_PICKUP), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			Case 2:
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_THROW), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			Case 3:
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DROP), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
		End Select
	EndIf

	i=0
	Select Game\Interface\ControlTipType
		Case ACTION_COMMON,ACTION_JUMP,ACTION_HOP,ACTION_JUMPFALL,ACTION_FULLFALL,ACTION_UP,ACTION_FWD,ACTION_LAND,ACTION_CANNON3,ACTION_PUDDLE:
			If p\Motion\Ground Then
				If p\StompBounceTimer>0 Then
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMPHIGHER), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
				Else
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
				EndIf
				i=i+1

				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ROLL), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
				i=i+1
			Else
				If p\Action=ACTION_FULLFALL Then
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
					i=i+1
				Else
					If Menu\Stage>0 Then
						Select char
							Case CHAR_EME,CHAR_GME:
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[p\CharacterMode]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA[p\CharacterMode])
								i=i+1
							Default:
								If p\JumpActionMode And Len(CONTROLTIPS$(TIP_JUMPA2[char]))>0 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA2[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA2[char])
								Else
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA[char])
								EndIf
								i=i+1
						End Select
					Else
						Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[CHAR_SON]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA[CHAR_SON])
						i=i+1
					EndIf
				EndIf

				Interface_ControlTipDraw_Stomp(p, x#, y#, i) : i=i+1
			EndIf

			If p\Motion\Ground Then
				If Menu\Stage>0 Then
					Select char
						Case CHAR_EME,CHAR_GME:
							Select p\CharacterMode
							Case CHAR_TAI: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[p\CharacterMode]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[p\CharacterMode])
							Case CHAR_ESP: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[CHAR_SON]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[CHAR_SON])
							Case CHAR_OME: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[CHAR_MKN]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[CHAR_MKN])
							Default: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[p\CharacterMode]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[p\CharacterMode])
							End Select
							i=i+1
						Case CHAR_EGR
							If Game\Gameplay\GaugeEnergy>=5 Then
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[char]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[char])
								i=i+1
							EndIf 
						Default:
							Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[char]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[char])
							i=i+1
					End Select
					If Len(CONTROLTIPS$(TIP_SKILL2[char]))>0 Then
						Select char
							Case CHAR_GME:
								Select p\CharacterMode
									Case CHAR_OME:
										Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[CHAR_KNU]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[CHAR_KNU])
										i=i+1
								End Select
							Case CHAR_EGG:
								If p\Character=CHAR_TMH Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2X), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
								Else
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
								EndIf
								i=i+1
							Case CHAR_EGR
								If Game\Gameplay\GaugeEnergy>=15 Then
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
								i=i+1
							EndIf 
							Case CHAR_CRE
								If Game\Gameplay\GaugeEnergy>=25 And Game\Shield=0 Then 
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
								i=i+1
							EndIf 
						Case CHAR_SHA
							If Game\Gameplay\GaugeEnergy=100 Then
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
								i=i+1
							EndIf 
							Default:
								If Player_CanLightDash(p\Character) And (Not(Game\Interface\ControlTipPickUpTimer>0)) Then
									If p\AroundLightDashTimer>0 Then
										Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
										i=i+1
									EndIf
								Else
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2[char])
								i=i+1
								EndIf 
						End Select
					EndIf
					If Len(CONTROLTIPS$(TIP_SKILL3[char]))>0 Then
						Select char
							Case CHAR_BIG,CHAR_GAM,CHAR_BET:
								If p\UnderwaterFeet=1 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
									i=i+1
								EndIf
							Case CHAR_KNU:
								If p\Underwater=1 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3AIR[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3AIR[char])
								Else
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
								EndIf
								i=i+1
							Case CHAR_SON
								If p\AroundEnemyTimer>0 And Game\Gameplay\GaugeEnergy=100 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
										i=i+1
									EndIf 
								Case CHAR_SHA
									If Game\Gameplay\GaugeEnergy=100 Then
										Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
										i=i+1
									EndIf 	
								
								
							Default:
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
								i=i+1
						End Select
					EndIf
				EndIf
			Else
				If Menu\Stage>0 Then
					Select char
						Case CHAR_EME,CHAR_GME:
							Select p\CharacterMode
							Case CHAR_TAI,CHAR_ESP: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2AIR[p\CharacterMode]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[p\CharacterMode])
							Case CHAR_OME: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1AIR[CHAR_MKN]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[CHAR_MKN])
							Default: Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1AIR[p\CharacterMode]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[p\CharacterMode])
							End Select
							i=i+1
						Default:
							Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1AIR[char]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[char])
							i=i+1
					End Select
					If Len(CONTROLTIPS$(TIP_SKILL2AIR[char]))>0 Then
						Select char
							Case CHAR_GME:
								Select p\CharacterMode
									Case CHAR_OME:
										Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1AIR[CHAR_KNU]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[CHAR_KNU])
										i=i+1
								End Select
							Case CHAR_EGG:
								If p\Character=CHAR_TMH Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2X), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[char])
								Else
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2AIR[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[char])
								EndIf
								i=i+1
							Case CHAR_SHA
								If Game\Gameplay\GaugeEnergy=100 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2AIR[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
									i=i+1
								EndIf 	
							Default:
								If Player_CanLightDash(p\Character) And (Not(Game\Interface\ControlTipPickUpTimer>0)) Then
									If p\AroundLightDashTimer>0 Then
										Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2AIR[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[char])
										i=i+1
									EndIf
								Else
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2AIR[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[char])
								i=i+1
								EndIf 
						End Select
					EndIf
					If Len(CONTROLTIPS$(TIP_SKILL3AIR[char]))>0 Then
						Select char
							Case CHAR_BIG,CHAR_GAM,CHAR_BET:
								If p\UnderwaterFeet=1 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3AIR[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3AIR[char])
									i=i+1
								ElseIf char=CHAR_BIG Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_BELLYFLOP), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, 0)
									i=i+1
								EndIf
							Case CHAR_SON
								
								If p\AroundEnemyTimer>0 And Game\Gameplay\GaugeEnergy=100 Then
									Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3AIR[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3AIR[char])
										i=i+1
									EndIf 
								Case CHAR_SHA
									If Game\Gameplay\GaugeEnergy=100 Then
										Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3AIR[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3[char])
										i=i+1
									EndIf 			
							Default:
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3AIR[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3AIR[char])
								i=i+1
						End Select
					Else
						Select char
							Case CHAR_VEC:
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HAMMERDOWN), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, 0)
								i=i+1
						End Select
					EndIf
				EndIf
			EndIf

		

			If p\Motion\Ground Then
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DRIFT), INPUT_BUTTON_ACTIONDRIFT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)

				Interface_ControlTipDraw_HyperBlast(p, x#, y#, i+1)
			Else
				Interface_ControlTipDraw_Bounce(p, x#, y#, i)
			EndIf
		Case ACTION_HURT:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		Case ACTION_CHARGE:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_CHARGE), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DRIFT), INPUT_BUTTON_ACTIONDRIFT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
		Case ACTION_ROLL:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_BREAK), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DRIFT), INPUT_BUTTON_ACTIONDRIFT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
		Case ACTION_DRIFT:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DRIFT), INPUT_BUTTON_ACTIONDRIFT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
		Case ACTION_GRIND:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_GRINDFASTER), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
		Case ACTION_FLY:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ASCEND), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_Stomp(p, x#, y#, i) : i=i+1
			Select char
				Case CHAR_TAI,CHAR_CRE,CHAR_CHA,CHAR_EGR:
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[char]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[char])
					i=i+1
			End Select
			Select char
				Case CHAR_CHA:
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[char])
					i=i+1
			End Select

			Interface_ControlTipDraw_Bounce(p, x#, y#, i)
		Case ACTION_GLIDE,ACTION_SLOWGLIDE:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_GLIDE), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_LEVITATE:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Stomp(p, x#, y#, i) : i=i+1
			Select char
				Case CHAR_SIL,CHAR_MPH,CHAR_MT3,CHAR_INF:
					If char=CHAR_SIL Then
						Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[char]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[char])
						i=i+1
					EndIf
					If Len(CONTROLTIPS$(TIP_SKILL2[char]))>0 Then
						Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL2[char]), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL2AIR[char])
						i=i+1
					EndIf
					If Len(CONTROLTIPS$(TIP_SKILL3[char]))>0 Then
						Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL3[char]), INPUT_BUTTON_ACTIONSKILL3, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL3AIR[char])
						i=i+1
					EndIf
			End Select
			Interface_ControlTipDraw_Bounce(p, x#, y#, i)
		Case ACTION_HOVER:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Stomp(p, x#, y#, i) : i=i+1
			Select char
				Case CHAR_OME,CHAR_GAM,CHAR_EGG,CHAR_BET,CHAR_CHW,CHAR_TMH:
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[char]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1AIR[char])
					i=i+1
			End Select
			Interface_ControlTipDraw_Bounce(p, x#, y#, i)
		Case ACTION_FLUTTER:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_SOAR:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[CHAR_RAY]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Stomp(p, x#, y#, i) : i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ASCEND), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_Bounce(p, x#, y#, i)
		Case ACTION_SLEET:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_CLIMB:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		Case ACTION_BUOY:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_HOLD:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		Case ACTION_SPRINT:
			Select CHAR
				Case CHAR_TAI
				Default
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			End Select 
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_CARRY:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		Case ACTION_FLOAT:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		Case ACTION_CANNON:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SHOOT), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
		Case ACTION_BOARD,ACTION_BOARDDRIFT:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DRIFT), INPUT_BUTTON_ACTIONDRIFT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			If Game\WholeVehicle=0 Then
				i=i+1
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HOPOFF), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			EndIf
		Case ACTION_BOARDJUMP,ACTION_BOARDFALL,ACTION_BOARDTRICK:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_TRICK), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			i=i+1
			If Game\WholeVehicle=0 Then
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HOPOFF), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			EndIf
		Case ACTION_BUMPED:
			If p\BumpedCloudTimer>0 Then
				If p\Motion\Ground Then
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
				Else
					Select char
						Case CHAR_EME,CHAR_GME:
							Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[p\CharacterMode]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA[p\CharacterMode])
							i=i+1
						Default:
							If p\JumpActionMode And Len(CONTROLTIPS$(TIP_JUMPA2[char]))>0 Then
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA2[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA2[char])
							Else
								Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ShortJUMPA[char]), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldJUMPA[char])
							EndIf
							i=i+1
					End Select
					Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
				EndIf
			Else
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_PADDLES), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			EndIf
		Case ACTION_SKYDIVE:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKYDIVEFASTER), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
		Case ACTION_GLIDER:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ASCEND), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DESCEND), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			If Game\WholeVehicle=0 Then
				i=i+1
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HOPOFF), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			EndIf
		Case ACTION_PUNCH,ACTION_THRUST:
			Select char
				Case CHAR_MET,CHAR_MT3:
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
					i=i+1
				Default:
					If p\Motion\Ground Then Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			End Select
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_SWIPE,ACTION_CLAW,ACTION_THROW:
			If p\Motion\Ground Then Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
		Case ACTION_CAR,ACTION_CARFALL,ACTION_CARDRIFT:
			If p\Motion\Ground Then
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_JUMP), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
				i=i+1
			EndIf
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ACCELERATE), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DRIFT), INPUT_BUTTON_ACTIONDRIFT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			If Game\WholeVehicle=0 Then
				i=i+1
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HOPOFF), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			EndIf
		Case ACTION_TORNADO:
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_ASCEND), INPUT_BUTTON_ACTIONJUMP, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_DESCEND), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, True)
			i=i+1
			If p\HasVehicle=7 Then
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SKILL1[CHAR_GAM]), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, TIP_HoldSKILL1[CHAR_GAM])
			Else
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_SHOOT), INPUT_BUTTON_ACTIONSKILL1, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			EndIf
			i=i+1
			Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_BATTLEMODE), INPUT_BUTTON_ACTIONSKILL2, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
			If Game\WholeVehicle=0 Then
				i=i+1
				Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HOPOFF), INPUT_BUTTON_ACTIONACT, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
			EndIf
		Default:
			Interface_ControlTipDraw_StompAndBounce(p, x#, y#, i)
	End Select				
End Function
Function Interface_ControlTipDraw_CanStompAndBounce(p.tPlayer)
	If p\Flags\CanStomp Or (p\Action=ACTION_JUMP Or p\Action=ACTION_HOP) Then Return True Else Return False
End Function

Function Interface_ControlTipDraw_Stomp(p.tPlayer, x#, y#, i)
	If Interface_ControlTipDraw_CanStompAndBounce(p) Then
		Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_STOMP), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#)
	EndIf
End Function

Function Interface_ControlTipDraw_Bounce(p.tPlayer, x#, y#, i)
	If Interface_ControlTipDraw_CanStompAndBounce(p) Then
		Select p\Character
			Case CHAR_TAI,CHAR_CRE,CHAR_SHA
			Default
				If p\BouncesDone=0 Then
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_STOMPSKILL[p\Character]), INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#,True)
				Else
					Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_STOMPSKILL[p\Character])+" x "+p\BouncesDone, INPUT_BUTTON_ACTIONROLL, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#,True)
				EndIf
		End Select 
		Interface_ControlTipDraw_HyperBlast(p, x#, y#, i+1)
	EndIf
End Function

Function Interface_ControlTipDraw_StompAndBounce(p.tPlayer, x#, y#, i)
	Interface_ControlTipDraw_Stomp(p.tPlayer, x#, y#, i)
	Interface_ControlTipDraw_Bounce(p.tPlayer, x#, y#, i+1)
End Function
Function Interface_ControlTipDraw_HyperBlast(p.tPlayer, x#, y#, i)
	If Game\SuperForm=2 Then
 		Interface_ControlTipDraw_Button(CONTROLTIPS$(TIP_HYPERBLAST), INPUT_BUTTON_ACTIONSKILLX, x#, y#+i*CONTROLINFO_SPACE#*GAME_WINDOW_SCALE#, False, True)
 	EndIf
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D