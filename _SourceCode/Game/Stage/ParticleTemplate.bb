Dim PARTICLETEXTURES(106)

Function LoadParticleTexture(particletextureno, path$, mode = 0, blend = 1)
	PARTICLETEXTURES(particletextureno) = LoadTexture(path$, mode)
	TextureBlend PARTICLETEXTURES(particletextureno), blend
End Function

Function LoadParticleAnimTexture(particletextureno, path$, mode, blend, w, h, maxframes)
	PARTICLETEXTURES(particletextureno) = LoadAnimTexture(path$, mode, w, h, 0, maxframes)
	TextureBlend PARTICLETEXTURES(particletextureno), blend
End Function

;---------------------------------------------------------------------------------

i = 1
Global ParticleTexture_Emerald1 = i		: LoadParticleTexture(i, "Textures\Emerald1.png", 2, 1) : i=i+1
Global ParticleTexture_Emerald2 = i		: LoadParticleTexture(i, "Textures\Emerald2.png", 2, 1) : i=i+1
Global ParticleTexture_Emerald3 = i		: LoadParticleTexture(i, "Textures\Emerald3.png", 2, 1) : i=i+1
Global ParticleTexture_Emerald4 = i		: LoadParticleTexture(i, "Textures\Emerald4.png", 2, 1) : i=i+1
Global ParticleTexture_Emerald5 = i		: LoadParticleTexture(i, "Textures\Emerald5.png", 2, 1) : i=i+1
Global ParticleTexture_Flower = i		: LoadParticleTexture(i, "Textures\Flower.png", 4) : i=i+1
Global ParticleTexture_Petal = i		: LoadParticleTexture(i, "Textures\Petal.png", 4) : i=i+1
Global ParticleTexture_Fire = i			: LoadParticleTexture(i, "Textures\Fire.png", 2, 1) : i=i+1
Global ParticleTexture_FireBlue = i		: LoadParticleTexture(i, "Textures\FireBlue.png", 2, 1) : i=i+1
Global ParticleTexture_FireEx = i		: LoadParticleTexture(i, "Textures\FireEx.png", 2, 1) : i=i+1
Global ParticleTexture_Water = i		: LoadParticleTexture(i, "Textures\Water.png", 3) : i=i+1
Global ParticleTexture_WaterEx = i		: LoadParticleTexture(i, "Textures\WaterEx.png", 3) : i=i+1
Global ParticleTexture_Electric1 = i		: LoadParticleTexture(i, "Textures\Electric1.png", 2, 1) : i=i+1
Global ParticleTexture_Electric2 = i		: LoadParticleTexture(i, "Textures\Electric2.png", 2, 1) : i=i+1
Global ParticleTexture_Electric3 = i		: LoadParticleTexture(i, "Textures\Electric3.png", 2, 1) : i=i+1
Global ParticleTexture_Electric4 = i		: LoadParticleTexture(i, "Textures\Electric4.png", 2, 1) : i=i+1
Global ParticleTexture_Electric5 = i		: LoadParticleTexture(i, "Textures\Electric5.png", 2, 1) : i=i+1
Global ParticleTexture_Electric6 = i		: LoadParticleTexture(i, "Textures\Electric6.png", 2, 1) : i=i+1
Global ParticleTexture_Heart = i		: LoadParticleTexture(i, "Textures\Heart.png", 4) : i=i+1
Global ParticleTexture_Spirit1 = i		: LoadParticleTexture(i, "Textures\Spirit1.png", 2) : i=i+1
Global ParticleTexture_Spirit2 = i		: LoadParticleTexture(i, "Textures\Spirit2.png", 2) : i=i+1
Global ParticleTexture_Spirit3 = i		: LoadParticleTexture(i, "Textures\Spirit3.png", 2) : i=i+1
Global ParticleTexture_Spirit4 = i		: LoadParticleTexture(i, "Textures\Spirit4.png", 2) : i=i+1
Global ParticleTexture_Spirit5 = i		: LoadParticleTexture(i, "Textures\Spirit5.png", 2) : i=i+1
Global ParticleTexture_Spirit6 = i		: LoadParticleTexture(i, "Textures\Spirit6.png", 2) : i=i+1
Global ParticleTexture_Invisibility1 = i	: LoadParticleTexture(i, "Textures\Invisibility1.png", 4) : i=i+1
Global ParticleTexture_Invisibility2 = i	: LoadParticleTexture(i, "Textures\Invisibility2.png", 4) : i=i+1
Global ParticleTexture_Invisibility3 = i	: LoadParticleTexture(i, "Textures\Invisibility3.png", 4) : i=i+1
Global ParticleTexture_Invisibility4 = i	: LoadParticleTexture(i, "Textures\Invisibility4.png", 4) : i=i+1
Global ParticleTexture_Invisibility5 = i	: LoadParticleTexture(i, "Textures\Invisibility5.png", 4) : i=i+1
Global ParticleTexture_Invincibility = i	: LoadParticleTexture(i, "Textures\Invincibility.png", 4) : i=i+1
Global ParticleTexture_Invincibility2 = i	: LoadParticleTexture(i, "Textures\Invincibility2.png", 4) : i=i+1
Global ParticleTexture_Super1 = i		: LoadParticleAnimTexture(i, "Textures\super1.png", 2, 1, 128, 128, 4) : i=i+1
Global ParticleTexture_Super2 = i		: LoadParticleAnimTexture(i, "Textures\super2.png", 2, 1, 128, 128, 4) : i=i+1
Global ParticleTexture_Hyper1 = i		: LoadParticleAnimTexture(i, "Textures\hyper1.png", 2, 1, 128, 128, 4) : i=i+1
Global ParticleTexture_Hyper2 = i		: LoadParticleAnimTexture(i, "Textures\hyper2.png", 2, 1, 128, 128, 4) : i=i+1
Global ParticleTexture_ShotDespawn = i		: LoadParticleAnimTexture(i, "Textures\ShotDespawn.png", 2, 1, 128, 128, 4) : i=i+1
Global ParticleTexture_WaterSplash1 = i	: LoadParticleTexture(i, "Textures\WaterSplash1.png", 2, 1) : i=i+1
Global ParticleTexture_WaterSplash2 = i	: LoadParticleTexture(i, "Textures\WaterSplash2.png", 2, 1) : i=i+1
Global ParticleTexture_WaterSplash3 = i	: LoadParticleTexture(i, "Textures\WaterSplash3.png", 2, 1) : i=i+1
Global ParticleTexture_WaterSplash4 = i	: LoadParticleTexture(i, "Textures\WaterSplash4.png", 2, 1) : i=i+1
Global ParticleTexture_Smoke = i		: LoadParticleTexture(i, "Textures\Smoke.png", 2, 1) : i=i+1
Global ParticleTexture_Smoke_x = i		: LoadParticleTexture(i, "Textures\Smoke.png", 3, 3) : i=i+1
Global ParticleTexture_Smoke1 = i		: LoadParticleTexture(i, "Textures\Smoke1.png", 2, 1) : i=i+1
Global ParticleTexture_Shock = i		: LoadParticleTexture(i, "Textures\shock.png", 2, 1) : i=i+1
Global ParticleTexture_Levitation = i		: LoadParticleTexture(i, "Textures\levitation.png", 2, 1) : i=i+1
Global ParticleTexture_LevitationRuby = i	: LoadParticleTexture(i, "Textures\levitationr.png", 2, 1) : i=i+1
Global ParticleTexture_LevitationChaos = i	: LoadParticleTexture(i, "Textures\levitationd.png", 2, 1) : i=i+1
Global ParticleTexture_TNT = i			: LoadParticleTexture(i, "Textures\TNT.png", 3, 3) : i=i+1
Global ParticleTexture_Spark = i		: LoadParticleTexture(i, "Textures\Spark.jpg", 3, 3) : i=i+1
Global ParticleTexture_SparkWater = i		: LoadParticleTexture(i, "Textures\SparkWater.jpg", 3, 3) : i=i+1
Global ParticleTexture_PunchTrail = i		: LoadParticleTexture(i, "Textures\punchtrail.png", 2, 1) : i=i+1
Global ParticleTexture_KickTrail = i		: LoadParticleTexture(i, "Textures\kicktrail.png", 2, 1) : i=i+1
Global ParticleTexture_WindTrail = i		: LoadParticleTexture(i, "Textures\windtrail.png", 2, 1) : i=i+1
Global ParticleTexture_BoomerangSpin = i	: LoadParticleTexture(i, "Textures\boomerangspin.png", 2, 1) : i=i+1
Global ParticleTexture_AfterJump = i		: LoadParticleTexture(i, "Textures\afterjump.png", 2, 1) : i=i+1
Global ParticleTexture_ShockWave = i		: LoadParticleTexture(i, "Textures\shockwave.png", 2, 1) : i=i+1
Global ParticleTexture_BulletImage = i		: LoadParticleTexture(i, "Textures\bulletimage.png", 2, 1) : i=i+1
Global ParticleTexture_BulletImageAlien = i	: LoadParticleTexture(i, "Textures\bulletimage_alien.png", 2, 1) : i=i+1
Global ParticleTexture_Snow = i		: LoadParticleTexture(i, "Textures\Snow.png", 3) : i=i+1
Global ParticleTexture_Snoww = i		: LoadParticleTexture(i, "Textures\Snoww.png", 3) : i=i+1
Global ParticleTexture_Ring1 = i		: LoadParticleTexture(i, "Textures\Ring1.png", 4) : i=i+1
Global ParticleTexture_Ring2 = i		: LoadParticleTexture(i, "Textures\Ring2.png", 4) : i=i+1
Global ParticleTexture_Ring3 = i		: LoadParticleTexture(i, "Textures\Ring3.png", 4) : i=i+1
Global ParticleTexture_Ring4 = i		: LoadParticleTexture(i, "Textures\Ring4.png", 4) : i=i+1
Global ParticleTexture_RRing1 = i		: LoadParticleTexture(i, "Textures\RRing1.png", 4) : i=i+1
Global ParticleTexture_RRing2 = i		: LoadParticleTexture(i, "Textures\RRing2.png", 4) : i=i+1
Global ParticleTexture_RRing3 = i		: LoadParticleTexture(i, "Textures\RRing3.png", 4) : i=i+1
Global ParticleTexture_RRing4 = i		: LoadParticleTexture(i, "Textures\RRing4.png", 4) : i=i+1
Global ParticleTexture_Shard1 = i		: LoadParticleTexture(i, "Textures\Shard1.png", 4) : i=i+1
Global ParticleTexture_Shard2 = i		: LoadParticleTexture(i, "Textures\Shard2.png", 4) : i=i+1
Global ParticleTexture_Shard3 = i		: LoadParticleTexture(i, "Textures\Shard3.png", 4) : i=i+1
Global ParticleTexture_Shard4 = i		: LoadParticleTexture(i, "Textures\Shard4.png", 4) : i=i+1
Global ParticleTexture_Bubble = i		: LoadParticleTexture(i, "Textures\Bubble.png", 2, 1) : i=i+1
Global ParticleTexture_Drop = i			: LoadParticleTexture(i, "Textures\Drop.png", 2, 1) : i=i+1
Global ParticleTexture_Curse1 = i		: LoadParticleTexture(i, "Textures\Curse1.png", 2, 1) : i=i+1
Global ParticleTexture_Curse2 = i		: LoadParticleTexture(i, "Textures\Curse2.png", 2, 1) : i=i+1
Global ParticleTexture_Goal1 = i		: LoadParticleAnimTexture(i, "Textures\Goal1.png", 2, 1, 64, 64, 4) : i=i+1
Global ParticleTexture_Goal2 = i		: LoadParticleAnimTexture(i, "Textures\Goal2.png", 2, 1, 64, 64, 4) : i=i+1
Global ParticleTexture_Fire2 = i		: LoadParticleTexture(i, "Textures\Fire2.png", 2, 1) : i=i+1
Global ParticleTexture_Fire2Ex = i		: LoadParticleTexture(i, "Textures\Fire2Ex.png", 2, 1) : i=i+1
Global ParticleTexture_Fire2ExBlue = i		: LoadParticleTexture(i, "Textures\Fire2ExBlue.png", 2, 1) : i=i+1
Global ParticleTexture_Ice = i			: LoadParticleTexture(i, "Textures\Ice.png", 2, 1) : i=i+1
Global ParticleTexture_IceEx = i		: LoadParticleTexture(i, "Textures\IceEx.png", 2, 1) : i=i+1
Global ParticleTexture_Poison1 = i		: LoadParticleTexture(i, "Textures\Poison1.png", 2, 1) : i=i+1
Global ParticleTexture_Poison2 = i		: LoadParticleTexture(i, "Textures\Poison2.png", 2, 1) : i=i+1
Global ParticleTexture_PoisonFog = i		: LoadParticleTexture(i, "Textures\PoisonFog.png", 2, 1) : i=i+1
Global ParticleTexture_RocketFume = i		: LoadParticleTexture(i, "Textures\RocketFume.png", 3, 3) : i=i+1
Global ParticleTexture_RocketFume2 = i		: LoadParticleTexture(i, "Textures\RocketFume2.png", 3, 3) : i=i+1
Global ParticleTexture_WindTunnel = i		: LoadParticleTexture(i, "Textures\windtunnel.png", 2, 1) : i=i+1
Global ParticleTexture_SpawnSpark = i		: LoadParticleTexture(i, "Textures\spawnspark.jpg", 2, 1) : i=i+1
Global ParticleTexture_WaterPuddle1 = i	: LoadParticleTexture(i, "Textures\waterpuddle1.png", 3, 3) : i=i+1
Global ParticleTexture_WaterPuddle2 = i	: LoadParticleTexture(i, "Textures\waterpuddle2.png", 3, 3) : i=i+1
Global ParticleTexture_Chao1 = i		: LoadParticleTexture(i, "Textures\Chao1.png", 2, 1) : i=i+1
Global ParticleTexture_Chao2 = i		: LoadParticleTexture(i, "Textures\Chao2.png", 2, 1) : i=i+1
Global ParticleTexture_Chao3 = i		: LoadParticleTexture(i, "Textures\Chao3.png", 2, 1) : i=i+1
Global ParticleTexture_Justice = i		: LoadParticleTexture(i, "Textures\Justice.png", 3) : i=i+1
Global ParticleTexture_Leaf1 = i		: LoadParticleTexture(i, "Textures\Leaf1.png", 3) : i=i+1
Global ParticleTexture_Leaf2 = i		: LoadParticleTexture(i, "Textures\Leaf2.png", 3) : i=i+1
Global ParticleTexture_Rain = i		: LoadParticleTexture(i, "Textures\Rain.png", 3) : i=i+1
Global ParticleTexture_Aimed = i		: LoadParticleTexture(i, "Textures\Aimed.png", 3) : i=i+1
Global ParticleTexture_Twilight1 = i		: LoadParticleTexture(i, "Textures\twilight1.png", 3) : i=i+1
Global ParticleTexture_Twilight2 = i		: LoadParticleTexture(i, "Textures\twilight2.png", 3) : i=i+1
Global ParticleTexture_Ink = i		: LoadParticleTexture(i, "Textures\Ink.png", 3) : i=i+1
Global ParticleTexture_AlienBlood = i		: LoadParticleTexture(i, "Textures\AlienBlood.png", 2, 1) : i=i+1
Global ParticleTexture_AlienBloodEx = i		: LoadParticleTexture(i, "Textures\AlienBloodEx.png", 2, 1) : i=i+1

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tParticleTemplate
		Field ParticleType
		Field ParticleTimer
		Field Template[5]
		Field Pos.tVector
		Field Dir#
		Field Pivot

		Field Size#
		Field Alpha#
		Field Interval#
		Field Character
		Field InsideType
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Function ParticleTemplate_Create.tParticleTemplate()
		pt.tParticleTemplate = New tParticleTemplate
		pt\Pivot=CreatePivot()
		pt\Pos = New tVector
		Return pt
	End Function

	Function ParticleTemplate_Delete(pt.tParticleTemplate)
		For i=0 To 5 : FreeEmitter pt\Template[i] : Next
		FreeEmitter pt\Pivot
		FreeEntity pt\Pivot
		Delete pt\Pos
		Delete pt
		Return
	End Function

	Function ParticleTemplate_Update(pt.tParticleTemplate)
		If pt\ParticleTimer>0 Then pt\ParticleTimer=pt\ParticleTimer-timervalue#
	End Function

	Function ParticleTemplate_Call(pt.tParticleTemplate, particletype, pivot, size#=0, alpha#=0, interval#=0, character=0, insidetype=0, particletimer#=0)
		If (Not(pt\ParticleTimer>0)) Or particletype<>pt\ParticleType Then
			Select particletype
				Case PARTICLE_OBJECT_AIMED,PARTICLE_OBJECT_BUBBLE,PARTICLE_OBJECT_BUBBLESTUN: If particletimer#<0.0125 Then particletimer#=0.0125
				Default: If particletimer#<0.05 Then particletimer#=0.05
			End Select

			pt\ParticleType=particletype
			pt\Pos\x#=EntityX(pivot,1)
			pt\Pos\y#=EntityY(pivot,1)
			pt\Pos\z#=EntityZ(pivot,1)
			pt\Dir#=EntityYaw(pivot,1)
			pt\ParticleTimer=particletimer#*secs#

			pt\Size#=size#
			pt\Alpha#=alpha#
			pt\Interval#=interval#
			pt\Character=character
			pt\InsideType=insidetype

			ParticleTemplate_CreateParticle(pt)
		EndIf
	End Function

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	pm=1
	Global PARTICLE_PLAYER_CHARACTERDUST	= pm : pm=pm+1
	Global PARTICLE_PLAYER_FLOWER		= pm : pm=pm+1
	Global PARTICLE_PLAYER_FIRE		= pm : pm=pm+1
	Global PARTICLE_PLAYER_ICE		= pm : pm=pm+1
	Global PARTICLE_PLAYER_INK		= pm : pm=pm+1
	Global PARTICLE_PLAYER_POISONFOG	= pm : pm=pm+1
	Global PARTICLE_PLAYER_WATER		= pm : pm=pm+1
	Global PARTICLE_PLAYER_ELECTRIC		= pm : pm=pm+1
	Global PARTICLE_PLAYER_HEARTS		= pm : pm=pm+1
	Global PARTICLE_PLAYER_SPIRIT		= pm : pm=pm+1
	Global PARTICLE_PLAYER_INVISIBILITY	= pm : pm=pm+1
	Global PARTICLE_PLAYER_INVINCIBILITY	= pm : pm=pm+1
	Global PARTICLE_PLAYER_SUPER		= pm : pm=pm+1
	Global PARTICLE_PLAYER_SHOTDESPAWN		= pm : pm=pm+1
	Global PARTICLE_PLAYER_SUPERAURA	= pm : pm=pm+1
	Global PARTICLE_PLAYER_HYPERAURA	= pm : pm=pm+1
	Global PARTICLE_PLAYER_WATERSPLASH	= pm : pm=pm+1
	Global PARTICLE_PLAYER_ROCKET		= pm : pm=pm+1
	Global PARTICLE_PLAYER_ROCKET2		= pm : pm=pm+1
	Global PARTICLE_PLAYER_SMOKE		= pm : pm=pm+1
	Global PARTICLE_PLAYER_CONTACTSPARK	= pm : pm=pm+1
	Global PARTICLE_PLAYER_PSYCHOGLOW	= pm : pm=pm+1
	Global PARTICLE_PLAYER_EXPLODE		= pm : pm=pm+1
	Global PARTICLE_PLAYER_ATTACKTRAIL	= pm : pm=pm+1
	Global PARTICLE_PLAYER_AFTERJUMP	= pm : pm=pm+1
	Global PARTICLE_PLAYER_BULLETHEAT	= pm : pm=pm+1
	Global PARTICLE_PLAYER_BULLETHEATALIEN= pm : pm=pm+1
	Global PARTICLE_PLAYER_SNOW		= pm : pm=pm+1
	Global PARTICLE_PLAYER_BOMB		= pm : pm=pm+1
	Global PARTICLE_PLAYER_BUBBLEBREATHE= pm : pm=pm+1
	Global PARTICLE_OBJECT_RING		= pm : pm=pm+1
	Global PARTICLE_OBJECT_REDRING		= pm : pm=pm+1
	Global PARTICLE_OBJECT_SHARD		= pm : pm=pm+1
	Global PARTICLE_OBJECT_BUBBLES		= pm : pm=pm+1
	Global PARTICLE_OBJECT_BUBBLE		= pm : pm=pm+1
	Global PARTICLE_OBJECT_DROP			= pm : pm=pm+1
	Global PARTICLE_OBJECT_BUBBLESTUN	= pm : pm=pm+1
	Global PARTICLE_OBJECT_CURSESTUN	= pm : pm=pm+1
	Global PARTICLE_OBJECT_GOAL		= pm : pm=pm+1
	Global PARTICLE_OBJECT_BOMB		= pm : pm=pm+1
	Global PARTICLE_OBJECT_BOMBBIG		= pm : pm=pm+1
	Global PARTICLE_OBJECT_EXPLODE		= pm : pm=pm+1
	Global PARTICLE_OBJECT_FIRE		= pm : pm=pm+1
	Global PARTICLE_OBJECT_ICE		= pm : pm=pm+1
	Global PARTICLE_OBJECT_INK		= pm : pm=pm+1
	Global PARTICLE_OBJECT_SHOCK		= pm : pm=pm+1
	Global PARTICLE_OBJECT_ELECTRIC		= pm : pm=pm+1
	Global PARTICLE_OBJECT_POISON		= pm : pm=pm+1
	Global PARTICLE_OBJECT_POISONFOG	= pm : pm=pm+1
	Global PARTICLE_OBJECT_FLAMYBLOOD	= pm : pm=pm+1
	Global PARTICLE_OBJECT_ALIENBLOOD	= pm : pm=pm+1
	Global PARTICLE_OBJECT_WATERSPLASH	= pm : pm=pm+1
	Global PARTICLE_OBJECT_FLOWERS		= pm : pm=pm+1
	Global PARTICLE_OBJECT_ELECTROSTUN	= pm : pm=pm+1
	Global PARTICLE_OBJECT_ROCKETFUMES	= pm : pm=pm+1
	Global PARTICLE_OBJECT_ROCKETEXPLODE	= pm : pm=pm+1
	Global PARTICLE_OBJECT_WINDTUNNEL	= pm : pm=pm+1
	Global PARTICLE_OBJECT_FIRESPRINKLER	= pm : pm=pm+1
	Global PARTICLE_OBJECT_WATERSPRINKLER	= pm : pm=pm+1
	Global PARTICLE_OBJECT_SPAWNSPARKS	= pm : pm=pm+1
	Global PARTICLE_OBJECT_AIMED		= pm : pm=pm+1
	Global PARTICLE_CHAO_SWIM		= pm : pm=pm+1
	Global PARTICLE_CHAO_CHEESE		= pm : pm=pm+1
	Global PARTICLE_BOMB_SHINE		= pm : pm=pm+1
	Global PARTICLE_BOMB_SHINEWATER		= pm : pm=pm+1
	Global PARTICLE_BOMB_SNOW		= pm : pm=pm+1
	Global PARTICLE_BOMB_BOMB		= pm : pm=pm+1
	Global PARTICLE_BOMB_ELECTRIC		= pm : pm=pm+1
	Global PARTICLE_BOMB_BUBBLES		= pm : pm=pm+1
	Global PARTICLE_BOMB_CURSE		= pm : pm=pm+1
	Global PARTICLE_BOMB_FLOWERS		= pm : pm=pm+1
	Global PARTICLE_BOMB_FIRE		= pm : pm=pm+1
	Global PARTICLE_BOMB_JUSTICE		= pm : pm=pm+1
	Global PARTICLE_AMBIENT_LEAF		= pm : pm=pm+1
	Global PARTICLE_AMBIENT_RAIN		= pm : pm=pm+1
	Global PARTICLE_AMBIENT_SNOW		= pm : pm=pm+1
	Global PARTICLE_AMBIENT_TWILIGHT	= pm : pm=pm+1
	Global PARTICLE_MENU_EMBLEM		= pm : pm=pm+1

	Global PARTICLE_PARTICLESCOUNT		= pm-1

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function ParticleTemplate_CreateParticle(pt.tParticleTemplate)

	PositionEntity pt\Pivot, pt\Pos\x#, pt\Pos\y#, pt\Pos\z#, 1
	RotateEntity pt\Pivot, 0, pt\Dir#, 0, 1

	Select pt\ParticleType
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_CHARACTERDUST:
		For i=0 To 2
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 1)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateEmitterLifeTime(pt\Template[i], 6)
		SetTemplateParticleLifeTime(pt\Template[i], 18, 25)
		Select(Rand(1,5))
		Case 1: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Emerald1))
		Case 2: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Emerald2))
		Case 3: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Emerald3))
		Case 4: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Emerald4))
		Case 5: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Emerald5))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[i], -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5), -2, (2.00+GetCharScaleFactor#(pt\Character)*0.5), -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5))
		Case 2: SetTemplateOffset(pt\Template[i], -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5), -2, (2.25+GetCharScaleFactor#(pt\Character)*0.5), -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5))
		Case 3: SetTemplateOffset(pt\Template[i], -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5), -2, (2.50+GetCharScaleFactor#(pt\Character)*0.5), -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5))
		Case 4: SetTemplateOffset(pt\Template[i], -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5), -2, (2.50+GetCharScaleFactor#(pt\Character)*0.5), -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5))
		Case 5: SetTemplateOffset(pt\Template[i], -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5), -2, (2.75+GetCharScaleFactor#(pt\Character)*0.5), -(1+GetCharScaleFactor#(pt\Character)*0.5), (1+GetCharScaleFactor#(pt\Character)*0.5))
		End Select
		SetTemplateVelocity(pt\Template[i], -0.15, 0.15, .0075, .015, -0.15, 0.15)
		SetTemplateSize(pt\Template[i], 5, 5, .9, .9)
		SetTemplateMaxParticles(pt\Template[i],5)
		SetTemplateGravity(pt\Template[i], -.005)
		SetEmitter(pt\Pivot,pt\Template[i])
		Next
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_FLOWER:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 4.5)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,3))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Flower))
		Case 2,3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Petal))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.00, -1, 1)
		Case 2: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.25, -1, 1)
		Case 3: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.50, -1, 1)
		Case 4: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.50, -1, 1)
		Case 5: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.75, -1, 1)
		End Select
		Select(Rand(1,3))
		Case 1: SetTemplateVelocity(pt\Template[0], -.5, .5, 0.1, 0.1, -.5, .5)
		Case 2: SetTemplateVelocity(pt\Template[0], -1, 1, 0.2, 0.5, -1, 1)
		Case 3: SetTemplateVelocity(pt\Template[0], -.75, .75, 0.5, 0.3, -.75, .75)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateRotation(pt\Template[0], -6.1, 6.1)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.7, 0.7, .5, 2)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_FIRE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select Rand(1, 2)
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_FireEx))
		End Select
		SetTemplateOffset(pt\Template[0], -1.52, 1.52, -1.525, 1.525, -1.52, 1.52)
		SetTemplateAlpha(pt\Template[0], 0.25)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.3, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_ICE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select Rand(1, 2)
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ice))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_IceEx))
		End Select
		SetTemplateOffset(pt\Template[0], -1.52, 1.52, -1.525, 1.525, -1.52, 1.52)
		SetTemplateAlpha(pt\Template[0], 0.25)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.3, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_INK:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ink))
		SetTemplateOffset(pt\Template[0], -3, 3, -3, 3, -3, 3)
		SetTemplateAlpha(pt\Template[0], 0.5)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 8, 8, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.3, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_POISONFOG:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 75, 85)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_PoisonFog))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], -0.05, 0.05, -0.075, 0.075, -0.05, 0.05)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 3, 3, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_WATER:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select Rand(1, 2)
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Water))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterEx))
		End Select
		SetTemplateOffset(pt\Template[0], -2.525, 2.525, -2.525, 2.525, -2.525, 2.525)
		SetTemplateAlpha(pt\Template[0], 1.0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9*1, .9*1)
		SetTemplateSizeVel(pt\Template[0], -.4, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_ELECTRIC:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,6))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric5))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric6))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], -1, 1, 0.3, 0.3, -1, 1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 15, 15, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_HEARTS:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Heart))
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -3, 3, -2, 2.00, -3, 3)
		Case 2: SetTemplateOffset(pt\Template[0], -3, 3, -2, 2.25, -3, 3)
		Case 3: SetTemplateOffset(pt\Template[0], -3, 3, -2, 2.50, -3, 3)
		Case 4: SetTemplateOffset(pt\Template[0], -3, 3, -2, 2.50, -3, 3)
		Case 5: SetTemplateOffset(pt\Template[0], -3, 3, -2, 2.75, -3, 3)
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.34, 0.34, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_SPIRIT:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 2.5, 7.5)
		Select(Rand(1,6))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spirit1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spirit2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spirit3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spirit4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spirit5))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spirit6))
		End Select
		SetTemplateOffset(pt\Template[0], -0.25, 0.25, -0.25, -0.25, -0.25, 0.25)
		SetTemplateVelocity(pt\Template[0], -.05, .05, -.05, .05, -.05, .05)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 2, 2, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_INVISIBILITY:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		If Game\Others\Fps>=30 Then
			SetTemplateParticlesPerInterval(pt\Template[0], 8)
		Else
			SetTemplateParticlesPerInterval(pt\Template[0], 4)
		EndIf
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		Select(Rand(1,5))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invisibility1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invisibility2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invisibility3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invisibility4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invisibility5))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.00+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 2: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.25+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 3: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.50+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 4: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.50+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 5: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.75+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.7, 0.7, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_INVINCIBILITY:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		If Game\Others\Fps>=30 Then
			SetTemplateParticlesPerInterval(pt\Template[0], 8)
		Else
			SetTemplateParticlesPerInterval(pt\Template[0], 4)
		EndIf
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invincibility))
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.00+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 2: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.25+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 3: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.50+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 4: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.50+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 5: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.75+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.6, 0.6, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_SUPER:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		If Game\Others\Fps>=30 Then
			SetTemplateParticlesPerInterval(pt\Template[0], 8)
		Else
			SetTemplateParticlesPerInterval(pt\Template[0], 4)
		EndIf
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Invincibility2))
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.00+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 2: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.25+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 3: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.50+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 4: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.50+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		Case 5: SetTemplateOffset(pt\Template[0], -(3+2+1*pt\Size#), (3+2+1*pt\Size#), -(2), (2.75+2+1.5*pt\Size#), -(3+2+1*pt\Size#), (3+2+1*pt\Size#))
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.6, 0.6, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
		
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_SUPERAURA:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select pt\InsideType
		Case 1:
			Select Rand(1, 2)
			Case 1: SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_Super1), 4, 0.00075)
			Case 2: SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_Super2), 4, 0.00075)
			End Select
		Case 2:
			Select Rand(1, 2)
			Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire))
			Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_FireEx))
			End Select
		End Select
		SetTemplateOffset(pt\Template[0], -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#), -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#), -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#))
		SetTemplateAlpha(pt\Template[0], 0.04)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 5, 5, 1.9, 1.9)
		SetTemplateSizeVel(pt\Template[0], -.2, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
		
	Case PARTICLE_PLAYER_SHOTDESPAWN:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_ShotDespawn), 4, 0.00075)
		SetTemplateOffset(pt\Template[0], -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#), -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#), -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#))
		SetTemplateAlpha(pt\Template[0], 0.04)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 5, 5, 1.9, 1.9)
		SetTemplateSizeVel(pt\Template[0], -.2, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_HYPERAURA:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select Rand(1, 2)
		Case 1: SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_Hyper1), 4, 0.00075)
		Case 2: SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_Hyper2), 4, 0.00075)
		End Select
		SetTemplateOffset(pt\Template[0], -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#), -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#), -(1.52+1.25*pt\Size#), (1.52+1.25*pt\Size#))
		SetTemplateAlpha(pt\Template[0], 0.04)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 5, 5, 1.9, 1.9)
		SetTemplateSizeVel(pt\Template[0], -.2, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_WATERSPLASH:
		pt\Template[0] = CreateTemplate()
		SetTemplateParticlesPerInterval(pt\Template[0],1)	
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 2.7)
		Select(Rand(1,3))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0],20,30)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0],30,40)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0],15,25)
		End Select
		Select(Rand(1,4))
		Case 1:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash1))
		Case 2:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash2))
		Case 3:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash3))
		Case 4:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash4))
		End Select
		Select Game\Stage\Properties\WaterType
			Case 2: SetTemplateRGB(pt\Template[0], 200, 57, 57)
			Case 4: SetTemplateRGB(pt\Template[0], 108, 198, 68)
		End Select
		SetTemplateOffset(pt\Template[0], 0+1*pt\Size#, -1,-2.5, -3, 0+1*pt\Size#, -1)
		Select(Rand(1,3))
		Case 1: SetTemplateVelocity(pt\Template[0], -(1/5), (1/5), (1/2), (2/2), -(1/5), (1/5))
		Case 2: SetTemplateVelocity(pt\Template[0], -(3/5), (3/5), (1/2), (2/2), -(3/5), (3/5))
		Case 3: SetTemplateVelocity(pt\Template[0], -(5/5), (5/5), (1/2), (2/2), -(5/5), (5/5))
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], (8+1*pt\Size#)*0.64, (8+1*pt\Size#)*0.64, (.9)*0.64, (.9)*0.64)
		Case 2: SetTemplateSize(pt\Template[0], (8.5+1*pt\Size#)*0.64, (8.5+1*pt\Size#)*0.64, (.9)*0.64, (.9)*0.64)
		Case 3: SetTemplateSize(pt\Template[0], (9+1*pt\Size#)*0.64, (9+1*pt\Size#)*0.64, (.9)*0.64, (.9)*0.64)
		Case 4: SetTemplateSize(pt\Template[0], (9.5+1*pt\Size#)*0.64, (9.5+1*pt\Size#)*0.64, (.9)*0.64, (.9)*0.64)
		Case 5: SetTemplateSize(pt\Template[0], (10+1*pt\Size#)*0.64, (10+1*pt\Size#)*0.64, (.9)*0.64, (.9)*0.64)
		End Select
		SetTemplateSizeVel(pt\Template[0], 0.6, .6, 1)
		Select(Rand(1,3))
		Case 1: SetTemplateMaxParticles(pt\Template[0],5)
		Case 2: SetTemplateMaxParticles(pt\Template[0],5)
		Case 3: SetTemplateMaxParticles(pt\Template[0],6)
		End Select
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_ROCKET:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 4, 6)
		SetTemplateParticlesPerInterval(pt\Template[0], 3)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_RocketFume))
		SetTemplateOffset(pt\Template[0], 0.1, -0.1, 0.1, -0.1, 0.1, -0.1)
		SetTemplateVelocity(pt\Template[0], -0.075, 0.075, 0.075, -0.075, -0.075, 0.075)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateRotation(pt\Template[0], -3, 3)
		SetTemplateSize(pt\Template[0], 1.5, 1.5, 0.5, 0.5)
		SetTemplateMaxParticles(pt\Template[0], 2)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_ROCKET2:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 4, 6)
		SetTemplateParticlesPerInterval(pt\Template[0], 3)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_RocketFume2))
		SetTemplateOffset(pt\Template[0], 0.1, -0.1, 0.1, -0.1, 0.1, -0.1)
		SetTemplateVelocity(pt\Template[0], -0.075, 0.075, 0.075, -0.075, -0.075, 0.075)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateRotation(pt\Template[0], -3, 3)
		SetTemplateSize(pt\Template[0], 1.5, 1.5, 0.5, 0.5)
		SetTemplateMaxParticles(pt\Template[0], 2)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_SMOKE:
		pt\Template[0] = CreateTemplate()
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 10)
		If pt\Interval# > 0 Then SetTemplateParticlesPerInterval(pt\Template[0], pt\Interval#)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Smoke1))
		If pt\InsideType<6 Then SetTemplateOffset(pt\Template[0], -2, 2, -3.05, -3.05, -2, 2)
		If pt\InsideType=5 Then SetTemplateVelocity(pt\Template[0], 0.6, -0.6, .25, .10, 0.6, -0.6, False)
		If pt\InsideType=0 Then SetTemplateVelocity(pt\Template[0], -2, -2, .5, 1, -2, -2, True)
		If pt\InsideType=1 Then SetTemplateVelocity(pt\Template[0], 2, 2, .5, 1, 2, 2, True)
		If pt\InsideType=3 Then SetTemplateVelocity(pt\Template[0], 0, 0, .5, 1, 0, 0, True)
		If pt\InsideType=7 Then SetTemplateVelocity(pt\Template[0], 0.1, -0.1, -0.1, 0.1, 0.1, -0.1, False)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateRGB(pt\Template[0], Interface_Circle_R[InterfaceChar(pt\Character)], Interface_Circle_G[InterfaceChar(pt\Character)], Interface_Circle_B[InterfaceChar(pt\Character)])
		SetTemplateAlpha(pt\Template[0], pt\Alpha#)
		SetTemplateSize(pt\Template[0], 1, 1, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], .5, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_CONTACTSPARK:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 6, 7)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Shock))
		SetTemplateOffset(pt\Template[0], 0, 0, -2.25, -2.25, 0, 0)
		SetTemplateAlpha(pt\Template[0], 1.0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 2.5+pt\Size#, 2.5+pt\Size#, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], .5, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 1)
		SetTemplateEmitterLifeTime(pt\Template[1], 3)
		SetTemplateParticleLifeTime(pt\Template[1], 8, 9)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Shock))
		SetTemplateOffset(pt\Template[1], 0, 0, -2.25, -2.25, 0, 0)
		SetTemplateAlpha(pt\Template[1], 1.0)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateSize(pt\Template[1], 2.5, 2.5, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[1], .5, 0, 1)
		SetTemplateFixAngles(pt\Template[1], 90, -1)
		SetEmitter(pt\Pivot, pt\Template[1])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_PSYCHOGLOW:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], pt\Interval#)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 20, 30)
		Select pt\InsideType
		Case 0: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Levitation))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_LevitationRuby))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_LevitationChaos))
		End Select
		SetTemplateOffset(pt\Template[0], -1.52, 1.52, -1.525, 1.525, -1.52, 1.52)
		SetTemplateAlpha(pt\Template[0], 1.0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		SetTemplateSizeVel(pt\Template[0], -.5, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_EXPLODE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 60)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_TNT))
		SetTemplateOffset(pt\Template[0], -.7, .7, -.7, .7, .3, 1.7)
		SetTemplateVelocity(pt\Template[0], -.03, .03, -.03, .03, -.03, .03)
		SetTemplateSize(pt\Template[0], 10, 8)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF00, $FFAA44)
		SetEmitter(pt\Pivot,pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8)
		SetTemplateParticleLifeTime(pt\Template[1], 50, 65)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4, .4, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2, 3)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 4)
		SetTemplateParticleLifeTime(pt\Template[2], 80, 110)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2, 1.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4, 4, 3, 5)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[2], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_ATTACKTRAIL:
		pt\Template[0] = CreateTemplate()
		Select(pt\InsideType)
		Case 1:SetTemplateEmitterBlend(pt\Template[0], 3)
		Case 4:SetTemplateEmitterBlend(pt\Template[0], 1)
		End Select
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], pt\Interval#)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 20, 30)
		Select(pt\InsideType)
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_PunchTrail))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_KickTrail))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_PunchTrail))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spark))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WindTrail))
		Case 7: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_BoomerangSpin))
		Case 8: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WindTrail))
		End Select
		If pt\InsideType=2 Then SetTemplateRGB(pt\Template[0], Interface_Circle_R[InterfaceChar(pt\Character)], Interface_Circle_G[InterfaceChar(pt\Character)], Interface_Circle_B[InterfaceChar(pt\Character)])
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlpha(pt\Template[0], pt\Alpha#)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1.5, 1.5, .9*pt\Size#, .9*pt\Size#)
		If pt\InsideType=6 Or pt\InsideType=7 Then SetTemplateFixAngles(pt\Template[0], 90, -1)
		SetTemplateSizeVel(pt\Template[0], -.1, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_AFTERJUMP:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 2)
		Select pt\InsideType
		Case 1: SetTemplateParticleLifeTime(pt\Template[0], 20, 25)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0], 12.5, 17.5)
		End Select
		Select pt\InsideType
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_AfterJump))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_ShockWave))
		End Select
		SetTemplateRGB(pt\Template[0], Interface_Circle_R[InterfaceChar(pt\Character)], Interface_Circle_G[InterfaceChar(pt\Character)], Interface_Circle_B[InterfaceChar(pt\Character)])
		SetTemplateOffset(pt\Template[0], 0, 0, -2, -2, 0, 0)
		Select pt\InsideType
		Case 1: SetTemplateAlpha(pt\Template[0], 1.0)
		Case 2: SetTemplateAlpha(pt\Template[0], 0.2556)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1+pt\Size#, 1+pt\Size#, .9*1, .9*1)
		SetTemplateSizeVel(pt\Template[0], .5, 0, 1)
		SetTemplateFixAngles(pt\Template[0], 90, -1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_BULLETHEAT:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], pt\Interval#)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_BulletImage))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlpha(pt\Template[0], pt\Alpha#)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1.5, 1.5, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.06, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_BULLETHEATALIEN:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], pt\Interval#)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_BulletImageAlien))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlpha(pt\Template[0], pt\Alpha#)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1.5, 1.5, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.06, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_SNOW:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 4.5)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,3))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Snow))
		Case 2,3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Snoww))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.00, -1, 1)
		Case 2: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.25, -1, 1)
		Case 3: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.50, -1, 1)
		Case 4: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.50, -1, 1)
		Case 5: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.75, -1, 1)
		End Select
		Select(Rand(1,3))
		Case 1: SetTemplateVelocity(pt\Template[0], -.5, .5, 0.1, 0.1, -.5, .5)
		Case 2: SetTemplateVelocity(pt\Template[0], -1, 1, 0.2, 0.5, -1, 1)
		Case 3: SetTemplateVelocity(pt\Template[0], -.75, .75, 0.5, 0.3, -.75, .75)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateRotation(pt\Template[0], -6.1, 6.1)
		SetTemplateGravity(pt\Template[0], .02)
		Select(Rand(1,6))
		Case 1: SetTemplateSize(pt\Template[0], 0.2, 0.2, .5, 2)
		Case 2,3: SetTemplateSize(pt\Template[0], 0.7, 0.7, .5, 2)
		Case 4,5: SetTemplateSize(pt\Template[0], 1.2, 1.2, .5, 2)
		Case 6: SetTemplateSize(pt\Template[0], 1.7, 1.7, .5, 2)
		End Select
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_BOMB:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 8*0.5)
		SetTemplateParticleLifeTime(pt\Template[0], 50*0.5, 60*0.5)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Smoke_x))
		SetTemplateOffset(pt\Template[0], -.7, .7, -.7, .7, .3, 1.7)
		SetTemplateVelocity(pt\Template[0], -.03, .03, -.03, .03, -.03, .03)
		SetTemplateSize(pt\Template[0], 6*0.5, 4*0.5)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF00, $FFAA44)
		SetEmitter(pt\Pivot,pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8*0.5)
		SetTemplateParticleLifeTime(pt\Template[1], 50*0.5, 65*0.5)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4, .4, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2*0.5, 3*0.5)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 4)
		SetTemplateParticleLifeTime(pt\Template[2], 40, 55)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2, 1.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4*0.5, 4*0.5, 3*0.5, 5*0.5)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_PLAYER_BUBBLEBREATHE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		Select(Rand(1,3))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0], 25, 35)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0], 30, 40)
		End Select
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Bubble))
		Select(Rand(1,2))
		Case 1: SetTemplateOffset(pt\Template[0], 0, 0, 0.5, 1.0, 0, 0)
		Case 2: SetTemplateOffset(pt\Template[0], -0.5, 0.5, 0.5, 1.0, -0.5, 0.5)
		End Select
		SetTemplateVelocity(pt\Template[0], -.05, .05, 0.4, 0.35, -.05, .05)
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,2))
		Case 1: SetTemplateSize(pt\Template[0], 1.0, 1.0, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 1.4, 1.4, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_RING:
		For i=0 To 2
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 1)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateParticlesPerInterval(pt\Template[i], 8)
		SetTemplateEmitterLifeTime(pt\Template[i], 1)
		SetTemplateParticleLifeTime(pt\Template[i], 5, 15)
		Select(Rand(1,4))
		Case 1: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Ring1))
		Case 2: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Ring2))
		Case 3: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Ring3))
		Case 4: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Ring4))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.00-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 2: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.25-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 3: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.50-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 4: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.50-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 5: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.75-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[i], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[i], -1.5, 1.5)
		SetTemplateGravity(pt\Template[i], .02)
		SetTemplateSize(pt\Template[i], 0.58*pt\Size#, 0.58*pt\Size#, .5*pt\Size#, 2*pt\Size#)
		SetTemplateFloor(pt\Template[i], -5, .45)
		SetTemplateAlphaVel(pt\Template[i], True)
		SetTemplateMaxParticles(pt\Template[i],1)
		SetEmitter(pt\Pivot,pt\Template[i])
	Next
Case PARTICLE_OBJECT_REDRING:
	For i=0 To 2
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 1)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateParticlesPerInterval(pt\Template[i], 8)
		SetTemplateEmitterLifeTime(pt\Template[i], 1)
		SetTemplateParticleLifeTime(pt\Template[i], 5, 15)
		Select(Rand(1,4))
			Case 1: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_RRing1))
			Case 2: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_RRing2))
			Case 3: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_RRing3))
			Case 4: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_RRing4))
		End Select
		Select(Rand(1,5))
			Case 1: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.00-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
			Case 2: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.25-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
			Case 3: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.50-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
			Case 4: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.50-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
			Case 5: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.75-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[i], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[i], -1.5, 1.5)
		SetTemplateGravity(pt\Template[i], .02)
		SetTemplateSize(pt\Template[i], 0.58*pt\Size#, 0.58*pt\Size#, .5*pt\Size#, 2*pt\Size#)
		SetTemplateFloor(pt\Template[i], -5, .45)
		SetTemplateAlphaVel(pt\Template[i], True)
		SetTemplateMaxParticles(pt\Template[i],1)
		SetEmitter(pt\Pivot,pt\Template[i])
	Next
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_SHARD:
		For i=0 To 2
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 1)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateParticlesPerInterval(pt\Template[i], 8)
		SetTemplateEmitterLifeTime(pt\Template[i], 1)
		SetTemplateParticleLifeTime(pt\Template[i], 5, 15)
		Select(Rand(1,4))
		Case 1: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Shard1))
		Case 2: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Shard2))
		Case 3: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Shard3))
		Case 4: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Shard4))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.00-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 2: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.25-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 3: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.50-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 4: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.50-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		Case 5: SetTemplateOffset(pt\Template[i], -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#, -(2-0.6)*pt\Size#, (2.75-0.6)*pt\Size#, -(3-0.6)*pt\Size#, (3-0.6)*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[i], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[i], -1.5, 1.5)
		SetTemplateGravity(pt\Template[i], .02)
		SetTemplateSize(pt\Template[i], 0.58*pt\Size#, 0.58*pt\Size#, .5*pt\Size#, 2*pt\Size#)
		SetTemplateFloor(pt\Template[i], -5, .45)
		SetTemplateAlphaVel(pt\Template[i], True)
		SetTemplateMaxParticles(pt\Template[i],1)
		SetEmitter(pt\Pivot,pt\Template[i])
		Next
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_BUBBLES:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		Select(Rand(1,5))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0], 25, 35)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0], 30, 40)
		Case 4: SetTemplateParticleLifeTime(pt\Template[0], 35, 45)
		Case 5: SetTemplateParticleLifeTime(pt\Template[0], 20, 30)
		End Select
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Bubble))
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], -.15, .15, 0.05, 0.05, -.15, .15)
		Case 2: SetTemplateVelocity(pt\Template[0], -.17, .17, 0.07, 0.07, -.17, .17)
		Case 3: SetTemplateVelocity(pt\Template[0], -.18, .18, 0.08, 0.08, -.18, .18)
		Case 4: SetTemplateVelocity(pt\Template[0], -.13, .13, 0.03, 0.03, -.13, .13)
		Case 5: SetTemplateVelocity(pt\Template[0], -.12, .12, 0.02, 0.02, -.12, .12)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 1.0*2.3, 1.0*2.3, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 1.4*2.3, 1.4*2.3, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 0.6*2.3, 0.6*2.3, .9, .9)
		Case 4: SetTemplateSize(pt\Template[0], 0.4*2.3, 0.4*2.3, .9, .9)
		Case 5: SetTemplateSize(pt\Template[0], 1.0*2.3, 1.0*2.3, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_BUBBLE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 10)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Bubble))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 6, 6, .9, .9)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_DROP:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		Select(Rand(1,3))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0], 25, 35)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0], 30, 40)
		End Select
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Drop))
		Select(Rand(1,2))
		Case 1: SetTemplateOffset(pt\Template[0], 0, 0, 0.5, 1.0, 0, 0)
		Case 2: SetTemplateOffset(pt\Template[0], -0.5, 0.5, 0.5, 1.0, -0.5, 0.5)
		End Select
		SetTemplateVelocity(pt\Template[0], -.05, .05, -0.5, -0.4, -.05, .05)
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,2))
		Case 1: SetTemplateSize(pt\Template[0], 1.2, 1.2, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 1.6, 1.6, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_BUBBLESTUN:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 10)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Bubble))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 12*pt\Size#, 12*pt\Size#, .9, .9)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_CURSESTUN:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 10)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Curse1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Curse2))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 12*pt\Size#, 12*pt\Size#, .9, .9)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFFFFF)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_GOAL:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 25, 35)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_Goal1), Rand(1,4), 0.3)
		Case 2: SetTemplateLoadedAnimTexture(pt\Template[0], (ParticleTexture_Goal2), Rand(1,4), 0.3)
		End Select
		If pt\InsideType = 0 Then
		SetTemplateOffset(pt\Template[0], Rand(10.7, 10.90), -Rand(10.7, 10.90), Rand(10.7, 10.90), -Rand(10.7, 10.90), Rand(10.7, 10.90), -Rand(10.7, 10.90))
		Else
		SetTemplateOffset(pt\Template[0], Rand(10.7, 10.90)*pt\Size#, -Rand(10.7, 10.90)*pt\Size#, Rand(10.7, 10.90)*pt\Size#, -Rand(10.7, 10.90)*pt\Size#, Rand(10.7, 10.90)*pt\Size#, -Rand(10.7, 10.90)*pt\Size#)
		EndIf
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 3, 3, .9*pt\Size#, .9*pt\Size#)
		SetTemplateMaxParticles(pt\Template[0], 3)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_BOMB:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 60)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Smoke_x))
		SetTemplateOffset(pt\Template[0], -.7, .7, -.7, .7, .3, 1.7)
		SetTemplateVelocity(pt\Template[0], -.03, .03, -.03, .03, -.03, .03)
		SetTemplateSize(pt\Template[0], 6, 4)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF00, $FFAA44)
		SetEmitter(pt\Pivot,pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8)
		SetTemplateParticleLifeTime(pt\Template[1], 50, 65)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4, .4, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2, 3)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 4)
		SetTemplateParticleLifeTime(pt\Template[2], 80, 110)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2, 1.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4, 4, 3, 5)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_BOMBBIG:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 60)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Smoke_x))
		SetTemplateOffset(pt\Template[0], -.7, .7, -.7, .7, .3, 1.7)
		SetTemplateVelocity(pt\Template[0], -.03, .03, -.03, .03, -.03, .03)
		SetTemplateSize(pt\Template[0], 6*1.35, 4*1.35)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF00, $FFAA44)
		SetEmitter(pt\Pivot,pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8)
		SetTemplateParticleLifeTime(pt\Template[1], 50, 65)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4, .4, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2*1.35, 3*1.35)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 4)
		SetTemplateParticleLifeTime(pt\Template[2], 80, 110)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2, 1.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4*1.35, 4*1.35, 3*1.35, 5*1.35)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_EXPLODE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 60)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_TNT))
		SetTemplateOffset(pt\Template[0], -.7, .7, -.7, .7, .3, 1.7)
		SetTemplateVelocity(pt\Template[0], -.03, .03, -.03, .03, -.03, .03)
		SetTemplateSize(pt\Template[0], 10, 8)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF00, $FFAA44)
		SetEmitter(pt\Pivot,pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8)
		SetTemplateParticleLifeTime(pt\Template[1], 50, 65)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4, .4, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2, 3)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 4)
		SetTemplateParticleLifeTime(pt\Template[2], 80, 110)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2, 1.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4, 4, 3, 5)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_FIRE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire2))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire2Ex))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		SetTemplateVelocity(pt\Template[0], -.05, .05, 1.75, 1.75, -.05, .05)
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 5, 5, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 5, 5, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		Case 4: SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		Case 5: SetTemplateSize(pt\Template[0], 3, 3, .9, .9)
		End Select
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_ICE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ice))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_IceEx))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		SetTemplateVelocity(pt\Template[0], -.05, .05, 1.75, 1.75, -.05, .05)
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 5, 5, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 5, 5, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		Case 4: SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		Case 5: SetTemplateSize(pt\Template[0], 3, 3, .9, .9)
		End Select
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_INK:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ink))
		SetTemplateOffset(pt\Template[0], -1.52, 1.52, -1.525, 1.525, -1.52, 1.52)
		SetTemplateAlpha(pt\Template[0], 0.5)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.3, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_SHOCK:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,6))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric5))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric6))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		SetTemplateVelocity(pt\Template[0], -.05, .05, 1.75, 1.75, -.05, .05)
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 5, 5, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 5, 5, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		Case 4: SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		Case 5: SetTemplateSize(pt\Template[0], 3, 3, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_ELECTRIC:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,6))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric5))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric6))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], -1, 1, 0.3, 0.3, -1, 1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 15, 15, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_POISON:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 75, 85)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Poison1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Poison2))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], -0.4, 0.4, 0.6, 0.6, -0.4, 0.4)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_POISONFOG:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 75, 85)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_PoisonFog))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], -0.05, 0.05, -0.0125, 0.0125, -0.05, 0.05)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1.25, 1.25, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_FLAMYBLOOD:
		For i=0 To 5
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 1)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateEmitterLifeTime(pt\Template[i], 3)
		SetTemplateParticleLifeTime(pt\Template[i], 75, 85)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_Fire))
		Case 2: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_FireEx))
		End Select
		SetTemplateOffset(pt\Template[i], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[i], -0.1, 0.1, 0.15, 0.15, -0.1, 0.1)
		SetTemplateAlpha(pt\Template[i], 0.75)
		SetTemplateAlphaVel(pt\Template[i], True)
		SetTemplateSize(pt\Template[i], 7, 7, .9, .9)
		SetTemplateMaxParticles(pt\Template[i],1)
		SetEmitter(pt\Pivot,pt\Template[i])
		Next
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_ALIENBLOOD:
		For i=0 To 5
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 1)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateEmitterLifeTime(pt\Template[i], 3)
		SetTemplateParticleLifeTime(pt\Template[i], 75, 85)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_AlienBlood))
		Case 2: SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_AlienBloodEx))
		End Select
		SetTemplateOffset(pt\Template[i], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[i], -0.1, 0.1, 0.15, 0.15, -0.1, 0.1)
		SetTemplateAlpha(pt\Template[i], 0.75)
		SetTemplateAlphaVel(pt\Template[i], True)
		SetTemplateSize(pt\Template[i], 7, 7, .9, .9)
		SetTemplateMaxParticles(pt\Template[i],1)
		SetEmitter(pt\Pivot,pt\Template[i])
		Next
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_WATERSPLASH:
		pt\Template[0] = CreateTemplate()
		SetTemplateParticlesPerInterval(pt\Template[0],1)	
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 2.7)
		Select(Rand(1,3))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0],20,30)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0],30,40)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0],15,25)
		End Select
		Select(Rand(1,4))
		Case 1:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash1))
		Case 2:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash2))
		Case 3:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash3))
		Case 4:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash4))
		End Select
		Select Game\Stage\Properties\WaterType
			Case 2: SetTemplateRGB(pt\Template[0], 200, 57, 57)
			Case 4: SetTemplateRGB(pt\Template[0], 108, 198, 68)
		End Select
		SetTemplateOffset(pt\Template[0], 1, -1, 0, -0.5, 1, -1)
		Select(Rand(1,3))
		Case 1: SetTemplateVelocity(pt\Template[0], -(1/5), (1/5), (1/2), (2/2), -(1/5), (1/5))
		Case 2: SetTemplateVelocity(pt\Template[0], -(3/5), (3/5), (1/2), (2/2), -(3/5), (3/5))
		Case 3: SetTemplateVelocity(pt\Template[0], -(5/5), (5/5), (1/2), (2/2), -(5/5), (5/5))
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], (8)*0.64, (8)*0.64, (.9)*0.64, (.9)*0.64)
		Case 2: SetTemplateSize(pt\Template[0], (8.5)*0.64, (8.5)*0.64, (.9)*0.64, (.9)*0.64)
		Case 3: SetTemplateSize(pt\Template[0], (9)*0.64, (9)*0.64, (.9)*0.64, (.9)*0.64)
		Case 4: SetTemplateSize(pt\Template[0], (9.5)*0.64, (9.5)*0.64, (.9)*0.64, (.9)*0.64)
		Case 5: SetTemplateSize(pt\Template[0], (10)*0.64, (10)*0.64, (.9)*0.64, (.9)*0.64)
		End Select
		SetTemplateSizeVel(pt\Template[0], 0.6, .6, 1)
		Select(Rand(1,3))
		Case 1: SetTemplateMaxParticles(pt\Template[0],5)
		Case 2: SetTemplateMaxParticles(pt\Template[0],5)
		Case 3: SetTemplateMaxParticles(pt\Template[0],6)
		End Select
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_FLOWERS:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Flower))
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.00*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 2: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.25*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 3: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.50*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 4: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.50*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 5: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.75*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.34*pt\Size#, 0.34*pt\Size#, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_ELECTROSTUN:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		Select(Rand(1,6))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric5))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric6))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.00*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 2: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.25*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 3: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.50*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 4: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.50*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 5: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.75*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.54*pt\Size#, 0.54*pt\Size#, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_ROCKETFUMES:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 4, 6)
		SetTemplateParticlesPerInterval(pt\Template[0], 3)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_RocketFume2))
		SetTemplateOffset(pt\Template[0], 0.1, -0.1, 0.1, -0.1, 0.1, -0.1)
		SetTemplateVelocity(pt\Template[0], -0.075, 0.075, 0.075, -0.075, -0.075, 0.075)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateRotation(pt\Template[0], -3, 3)
		SetTemplateSize(pt\Template[0], 3.75, 3.75, 0.75*1, 0.75*1)
		SetTemplateMaxParticles(pt\Template[0], 2)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_ROCKETEXPLODE:
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8)
		SetTemplateParticleLifeTime(pt\Template[1], 50, 65)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4, .4, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2, 3)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 2)
		SetTemplateParticleLifeTime(pt\Template[2], 80, 110)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2, 1.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4, 4, 3, 5)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_WINDTUNNEL:
		For i=0 To 5
		pt\Template[i] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[i], 3)
		SetTemplateInterval(pt\Template[i], 1)
		SetTemplateEmitterLifeTime(pt\Template[i], 6)
		SetTemplateParticleLifeTime(pt\Template[i], 40,40)
		SetTemplateLoadedTexture(pt\Template[i], (ParticleTexture_WindTunnel))
		SetTemplateOffset(pt\Template[i], -6.45*pt\Size#, 6.45*pt\Size#, 1, 1.00, -6.45*pt\Size#, 6.45*pt\Size#)
		SetTemplateVelocity(pt\Template[i], 0, 0, Rnd(0.5, 0.2)*pt\Interval#, Rnd(0.1, 0.75)*pt\Interval#, 0, 0, False)
		SetTemplateAlpha(pt\Template[i], 0.575)
		SetTemplateAlphaVel(pt\Template[i], True)
		SetTemplateSize(pt\Template[i], 0.0215*pt\Size#, 1.7*pt\Size#, .5*pt\Size#, 2*pt\Size#)
		SetTemplateFixAngles(pt\Template[i], 0, -1)
		SetTemplateMaxParticles(pt\Template[i],1)
		SetEmitter(pt\Pivot,pt\Template[i])
		Next
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_FIRESPRINKLER:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 6)
		SetTemplateEmitterLifeTime(pt\Template[0], 10)
		SetTemplateParticleLifeTime(pt\Template[0], 23, 25)
		Select Rand(1,5)
		Case 1,2:
			If pt\InsideType=0 Then
				SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire))
			Else
				SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_FireBlue))
			EndIf
		Case 3,4:
			If pt\InsideType=0 Then
				SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire2Ex))
			Else
				SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire2ExBlue))
			EndIf
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Smoke)) : SetTemplateParticleLifeTime(pt\Template[0], 33, 35)
		End Select
		SetTemplateOffset(pt\Template[0], -5, 5, -5, 5, -5, 5)
		SetTemplateAlpha(pt\Template[0], 0.65)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateGravity(pt\Template[0], -0.0625)
		SetTemplateRotation(pt\Template[0], 5, -5)
		SetTemplateSize(pt\Template[0], 4.525, 4, 4.5, 4)
		SetTemplateSizeVel(pt\Template[0], -.25, 0, 1)
		SetTemplateMaxParticles(pt\Template[0], 12)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_WATERSPRINKLER:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select(Rand(1,4))
		Case 1:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash1))
		Case 2:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash2))
		Case 3:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash3))
		Case 4:	SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterSplash4))
		End Select
		SetTemplateOffset(pt\Template[0], -5, 5, -5, 5, -5, 5)
		SetTemplateVelocity(pt\Template[0], -0.05, 0.05, -0.05, 0.05, -0.05, 0.05)
		SetTemplateAlpha(pt\Template[0], 0.5)
		SetTemplateRotation(pt\Template[0], 8.5, -8.5)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4.75, 4, 2.5, 2)
		SetTemplateSizeVel(pt\Template[0], -.4, 0, 1)
		SetTemplateMaxParticles(pt\Template[0], 20)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_SPAWNSPARKS:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 22, 32)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_SpawnSpark))
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		SetTemplateVelocity(pt\Template[0], -.3, .3, -.3, .3, -.3, .3)
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,3))
		Case 1: SetTemplateSize(pt\Template[0], 1.5*1.25, 1.5*1.25, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 1.0*1.25, 1.0*1.25, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 0.5*1.25, 0.5*1.25, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_OBJECT_AIMED:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 10)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Aimed))
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4*pt\Size#, 4*pt\Size#, .9, .9)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFFFFF)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_CHAO_SWIM:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 22)
		SetTemplateParticleLifeTime(pt\Template[0], 42, 44)
		Select Rand(1,2)
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterPuddle1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_WaterPuddle2))
		End Select
		SetTemplateOffset(pt\Template[0], -1.5, 1.5, 0.25-pt\Interval#, 0.25-pt\Interval#, -1.5, 1.5)
		SetTemplateAlpha(pt\Template[0], 0.5)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1+size#, 1+size#, .9*1, .9*1)
		SetTemplateSizeVel(pt\Template[0], .085, 0, 1)
		SetTemplateFixAngles(pt\Template[0], 90, -1)
		deelo=SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_CHAO_CHEESE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 6)
		SetTemplateParticleLifeTime(pt\Template[0], 18, 25)
		Select(Rand(1,3))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Chao1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Chao2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Chao3))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.00, -1, 1)
		Case 2: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.25, -1, 1)
		Case 3: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.50, -1, 1)
		Case 4: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.50, -1, 1)
		Case 5: SetTemplateOffset(pt\Template[0], -1, 1, -2, 2.75, -1, 1)
		End Select
		SetTemplateVelocity(pt\Template[0], -0.15, 0.15, .0075, .015, -0.15, 0.15)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 1.2, 1.2, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],5)
		SetTemplateGravity(pt\Template[0], -.005)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_SHINE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 3)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 65)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[0], -.4, .4, -.4+2.6, .4+2.6, -.4, .4)
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[0], .002)
		SetTemplateAlignToFall(pt\Template[0], True, 45)
		SetTemplateSize(pt\Template[0], 1, 2)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF33, $FFFF33)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_SHINEWATER:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 3)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 65)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_SparkWater))
		SetTemplateOffset(pt\Template[0], -.4, .4, -.4+2.6, .4+2.6, -.4, .4)
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[0], .002)
		SetTemplateAlignToFall(pt\Template[0], True, 45)
		SetTemplateSize(pt\Template[0], 1, 2)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF33, $FFFF33)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_SNOW:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 2)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 10)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Snow))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Snoww))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		SetTemplateVelocity(pt\Template[0], -.3, .3, .3, -.3, -.3, .3)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 3, 4, .9, .9)
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_BOMB:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 2)
		SetTemplateEmitterLifeTime(pt\Template[0], 8)
		SetTemplateParticleLifeTime(pt\Template[0], 50, 60)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Smoke_x))
		SetTemplateOffset(pt\Template[0], -.7, .7, -.7+2.2, .7+2.2, .3, 1.7)
		SetTemplateVelocity(pt\Template[0], -.03, .03, -.03, .03, -.03, .03)
		SetTemplateSize(pt\Template[0], 6, 4)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateColors(pt\Template[0], $FFFF00, $FFAA44)
		SetEmitter(pt\Pivot,pt\Template[0])
		;-------------------------
		pt\Template[1] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[1], 3)
		SetTemplateInterval(pt\Template[1], 1)
		SetTemplateParticlesPerInterval(pt\Template[1], 2)
		SetTemplateEmitterLifeTime(pt\Template[1], 8)
		SetTemplateParticleLifeTime(pt\Template[1], 50, 65)
		SetTemplateLoadedTexture(pt\Template[1], (ParticleTexture_Spark))
		SetTemplateOffset(pt\Template[1], -.4, .4, -.4+2.2, .4+2.2, -.4, .4)
		SetTemplateVelocity(pt\Template[1], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateGravity(pt\Template[1], .002)
		SetTemplateAlignToFall(pt\Template[1], True, 45)
		SetTemplateSize(pt\Template[1], 2, 3)
		SetTemplateAlphaVel(pt\Template[1], True)
		SetTemplateColors(pt\Template[1], $FFFF33, $FFFF33)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[1])
		SetEmitter(pt\Pivot,pt\Template[1])
		;-------------------------
		pt\Template[2] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[2], 1)
		SetTemplateInterval(pt\Template[2], 1)
		SetTemplateEmitterLifeTime(pt\Template[2], 4)
		SetTemplateParticleLifeTime(pt\Template[2], 80, 110)
		SetTemplateLoadedTexture(pt\Template[2], (ParticleTexture_Smoke))
		SetTemplateOffset(pt\Template[2], -1.2, 1.2, -1.2+2.2, 1.2+2.2, -1.2, 1.2)
		SetTemplateVelocity(pt\Template[2], -.01, .01, .01, .02, -.01, .01)
		SetTemplateSize(pt\Template[2], 4, 4, 3, 5)
		SetTemplateAlpha(pt\Template[2], .5)
		SetTemplateAlphaVel(pt\Template[2], True)
		SetTemplateSubTemplate(pt\Template[0], pt\Template[2])
		SetEmitter(pt\Pivot,pt\Template[2])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_ELECTRIC:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Select(Rand(1,6))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric4))
		Case 5: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric5))
		Case 6: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Electric6))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 0, 0, 0, 0)
		SetTemplateVelocity(pt\Template[0], -1, 1, 0.3, 0.3, -1, 1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 15, 15, .9, .9)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_BUBBLES:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		Select(Rand(1,5))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0], 25, 35)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0], 30, 40)
		Case 4: SetTemplateParticleLifeTime(pt\Template[0], 35, 45)
		Case 5: SetTemplateParticleLifeTime(pt\Template[0], 20, 30)
		End Select
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Bubble))
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], -.15*4, .15*4, 0.05*4, -0.05*4, -.15*4, .15*4)
		Case 2: SetTemplateVelocity(pt\Template[0], -.17*4, .17*4, 0.07*4, -0.07*4, -.17*4, .17*4)
		Case 3: SetTemplateVelocity(pt\Template[0], -.18*4, .18*4, 0.08*4, -0.08*4, -.18*4, .18*4)
		Case 4: SetTemplateVelocity(pt\Template[0], -.13*4, .13*4, 0.03*4, -0.03*4, -.13*4, .13*4)
		Case 5: SetTemplateVelocity(pt\Template[0], -.12*4, .12*4, 0.02*4, -0.02*4, -.12*4, .12*4)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 1.0*4.3, 1.0*4.3, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 1.4*4.3, 1.4*4.3, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 0.6*4.3, 0.6*4.3, .9, .9)
		Case 4: SetTemplateSize(pt\Template[0], 0.4*4.3, 0.4*4.3, .9, .9)
		Case 5: SetTemplateSize(pt\Template[0], 1.0*4.3, 1.0*4.3, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFBB00)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_CURSE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		Select(Rand(1,5))
		Case 1: SetTemplateParticleLifeTime(pt\Template[0], 15, 25)
		Case 2: SetTemplateParticleLifeTime(pt\Template[0], 25, 35)
		Case 3: SetTemplateParticleLifeTime(pt\Template[0], 30, 40)
		Case 4: SetTemplateParticleLifeTime(pt\Template[0], 35, 45)
		Case 5: SetTemplateParticleLifeTime(pt\Template[0], 20, 30)
		End Select
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Curse1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Curse2))
		End Select
		SetTemplateOffset(pt\Template[0], 0, 0, 1, 1.5, 0, 0)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], -.15*4, .15*4, 0.05*4, -0.05*4, -.15*4, .15*4)
		Case 2: SetTemplateVelocity(pt\Template[0], -.17*4, .17*4, 0.07*4, -0.07*4, -.17*4, .17*4)
		Case 3: SetTemplateVelocity(pt\Template[0], -.18*4, .18*4, 0.08*4, -0.08*4, -.18*4, .18*4)
		Case 4: SetTemplateVelocity(pt\Template[0], -.13*4, .13*4, 0.03*4, -0.03*4, -.13*4, .13*4)
		Case 5: SetTemplateVelocity(pt\Template[0], -.12*4, .12*4, 0.02*4, -0.02*4, -.12*4, .12*4)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 1.0*4.3, 1.0*4.3, .9, .9)
		Case 2: SetTemplateSize(pt\Template[0], 1.4*4.3, 1.4*4.3, .9, .9)
		Case 3: SetTemplateSize(pt\Template[0], 0.6*4.3, 0.6*4.3, .9, .9)
		Case 4: SetTemplateSize(pt\Template[0], 0.4*4.3, 0.4*4.3, .9, .9)
		Case 5: SetTemplateSize(pt\Template[0], 1.0*4.3, 1.0*4.3, .9, .9)
		End Select
		SetTemplateColors(pt\Template[0], $FFFFFF, $FFFFFF)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_FLOWERS:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Flower))
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.00*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 2: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.25*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 3: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.50*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 4: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.50*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		Case 5: SetTemplateOffset(pt\Template[0], -3*pt\Size#, 3*pt\Size#, -2*pt\Size#, 2.75*pt\Size#, -3*pt\Size#, 3*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.34*pt\Size#, 0.34*pt\Size#, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_FIRE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 3)
		SetTemplateParticleLifeTime(pt\Template[0], 10, 20)
		Select Rand(1,2)
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Fire))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_FireEx))
		End Select
		SetTemplateOffset(pt\Template[0], -1.52, 1.52, -1.525, 1.525, -1.52, 1.52)
		SetTemplateAlpha(pt\Template[0], 0.25)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateSize(pt\Template[0], 4, 4, .9*pt\Size#, .9*pt\Size#)
		SetTemplateSizeVel(pt\Template[0], -.3, 0, 1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_BOMB_JUSTICE:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 5, 15)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Justice))
		Select(Rand(1,5))
		Case 1: SetTemplateOffset(pt\Template[0], -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#)
		Case 2: SetTemplateOffset(pt\Template[0], -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#)
		Case 3: SetTemplateOffset(pt\Template[0], -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#)
		Case 4: SetTemplateOffset(pt\Template[0], -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#)
		Case 5: SetTemplateOffset(pt\Template[0], -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#, -1*pt\Size#, 1*pt\Size#)
		End Select
		SetTemplateVelocity(pt\Template[0], -.1, .1, -.1, .1, -.1, .1)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateSize(pt\Template[0], 0.54*pt\Size#, 0.54*pt\Size#, .5, 2)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_AMBIENT_LEAF:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 75)
		SetTemplateParticleLifeTime(pt\Template[0], 45, 45)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Leaf1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Leaf2))
		End Select
		SetTemplateOffset(pt\Template[0], -120, 120, 40, 40, -120, 120)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], .1, .1, -0.01, -0.01, 0, 0)
		Case 2: SetTemplateVelocity(pt\Template[0], .1, .1, -0.03, -0.03, 0, 0)
		Case 3: SetTemplateVelocity(pt\Template[0], .1, .1, -0.05, -0.05, 0, 0)
		Case 4: SetTemplateVelocity(pt\Template[0], .1, .1, -0.07, -0.07, 0, 0)
		Case 5: SetTemplateVelocity(pt\Template[0], .1, .1, -0.09, -0.09, 0, 0)
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 1.2, 1.2)
		Case 2: SetTemplateSize(pt\Template[0], 1.3, 1.3)
		Case 3: SetTemplateSize(pt\Template[0], 1.5, 1.5)
		Case 4: SetTemplateSize(pt\Template[0], 1.6, 1.6)
		Case 5: SetTemplateSize(pt\Template[0], 1.7, 1.7)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateRotation(pt\Template[0], -5, 5)
		SetTemplateGravity(pt\Template[0], .02)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_AMBIENT_RAIN:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 11)
		SetTemplateEmitterLifeTime(pt\Template[0], 25)
		SetTemplateParticleLifeTime(pt\Template[0], 25, 25)
		SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Rain))
		SetTemplateOffset(pt\Template[0], -120, 120, 40, 40, -120, 120)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], .1, .1, -3.83, -3.93, 0, 0)
		Case 2: SetTemplateVelocity(pt\Template[0], .1, .1, -4, -4, 0, 0)
		Case 3: SetTemplateVelocity(pt\Template[0], .1, .1, -5, -5, 0, 0)
		Case 4: SetTemplateVelocity(pt\Template[0], .1, .1, -4.5, -4.5, 0, 0)
		Case 5: SetTemplateVelocity(pt\Template[0], .1, .1, -3.53, -3.53, 0, 0)
		End Select
		Select(Rand(1,7))
		Case 1: SetTemplateSize(pt\Template[0], .4, .4)
		Case 2: SetTemplateSize(pt\Template[0], .5, .5)
		Case 3: SetTemplateSize(pt\Template[0], .6, .6)
		Case 4: SetTemplateSize(pt\Template[0], .7, .7)
		Case 5: SetTemplateSize(pt\Template[0], .9, .9)
		Case 6: SetTemplateSize(pt\Template[0], .9, .9)
		Case 7: SetTemplateSize(pt\Template[0], .9, .9)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateMaxParticles(pt\Template[0],17)
		SetTemplateAlpha(pt\Template[0], 1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_AMBIENT_SNOW:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 1)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 1)
		SetTemplateEmitterLifeTime(pt\Template[0], 45)
		SetTemplateParticleLifeTime(pt\Template[0], 45, 45)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Snow))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Snoww))
		End Select
		SetTemplateOffset(pt\Template[0], -120, 120, 40, 40, -120, 120)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], .1, .1, -0.83, -0.83, 0, 0)
		Case 2: SetTemplateVelocity(pt\Template[0], .1, .1, -1, -1, 0, 0)
		Case 3: SetTemplateVelocity(pt\Template[0], .1, .1, -0.33, -0.33, 0, 0)
		Case 4: SetTemplateVelocity(pt\Template[0], .1, .1, -1.3, -1.3, 0, 0)
		Case 5: SetTemplateVelocity(pt\Template[0], .1, .1, -0.63, -0.63, 0, 0)
		End Select
		Select(Rand(1,10))
		Case 1: SetTemplateSize(pt\Template[0], .1, .1)
		Case 2: SetTemplateSize(pt\Template[0], .15, .15)
		Case 3: SetTemplateSize(pt\Template[0], .2, .2)
		Case 4: SetTemplateSize(pt\Template[0], .25, .25)
		Case 5: SetTemplateSize(pt\Template[0], .3, .3)
		Case 6: SetTemplateSize(pt\Template[0], .35, .35)
		Case 7: SetTemplateSize(pt\Template[0], .4, .4)
		Case 8: SetTemplateSize(pt\Template[0], .45, .45)
		Case 9: SetTemplateSize(pt\Template[0], .5, .5)
		Case 10: SetTemplateSize(pt\Template[0], .55, .55)
		End Select
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateMaxParticles(pt\Template[0],10)
		SetTemplateAlpha(pt\Template[0], 1)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_AMBIENT_TWILIGHT:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 6)
		SetTemplateEmitterLifeTime(pt\Template[0], 75)
		SetTemplateParticleLifeTime(pt\Template[0], 45, 45)
		Select(Rand(1,2))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Twilight1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Twilight2))
		End Select
		SetTemplateOffset(pt\Template[0], -130, 130, Rand(30,90), Rand(-30,-90), -130, 130)
		Select(Rand(1,5))
		Case 1: SetTemplateVelocity(pt\Template[0], Rand(.075, -.075), Rand(.1, -.1), 0.01, 0.01, Rand(.075, -.075), Rand(.1, -.1))
		Case 2: SetTemplateVelocity(pt\Template[0], Rand(.075, -.075), Rand(.1, -.1), 0.03, 0.03, Rand(.075, -.075), Rand(.1, -.1))
		Case 3: SetTemplateVelocity(pt\Template[0], Rand(.075, -.075), Rand(.1, -.1), 0.05, 0.05, Rand(.075, -.075), Rand(.1, -.1))
		Case 4: SetTemplateVelocity(pt\Template[0], Rand(.075, -.075), Rand(.1, -.1), 0.07, 0.07, Rand(.075, -.075), Rand(.1, -.1))
		Case 5: SetTemplateVelocity(pt\Template[0], Rand(.075, -.075), Rand(.1, -.1), 0.09, 0.09, Rand(.075, -.075), Rand(.1, -.1))
		End Select
		Select(Rand(1,5))
		Case 1: SetTemplateSize(pt\Template[0], 0.9, 0.9)
		Case 2: SetTemplateSize(pt\Template[0], 0.1, 0.1)
		Case 3: SetTemplateSize(pt\Template[0], 0.5, 0.5)
		Case 4: SetTemplateSize(pt\Template[0], 0.3, 0.3)
		Case 5: SetTemplateSize(pt\Template[0], 0.7, 0.7)
		End Select
		SetTemplateAlpha(pt\Template[0], Rnd(0.5, 1))
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateRotation(pt\Template[0], -5, 5)
		SetTemplateGravity(pt\Template[0], -.002)
		SetTemplateMaxParticles(pt\Template[0],4)
		SetEmitter(pt\Pivot,pt\Template[0])
	;============================================================================================
	;============================================================================================
	Case PARTICLE_MENU_EMBLEM:
		pt\Template[0] = CreateTemplate()
		SetTemplateEmitterBlend(pt\Template[0], 3)
		SetTemplateInterval(pt\Template[0], 1)
		SetTemplateParticlesPerInterval(pt\Template[0], 8)
		SetTemplateEmitterLifeTime(pt\Template[0], 1)
		SetTemplateParticleLifeTime(pt\Template[0], 7, 17)
		Select(Rand(1,4))
		Case 1: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ring1))
		Case 2: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ring2))
		Case 3: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ring3))
		Case 4: SetTemplateLoadedTexture(pt\Template[0], (ParticleTexture_Ring4))
		End Select
		i=Rand(1,5) : SetTemplateOffset(pt\Template[0], -0.05*pt\Size#*i, 0.05*pt\Size#*i, -0.05*pt\Size#*i, 0.015*pt\Size#*i, -0.05*pt\Size#*i, 0.05*pt\Size#*i)
		SetTemplateVelocity(pt\Template[0], -.00015, .00015, -.02, -.02, -.00015, .00015)
		SetTemplateRotation(pt\Template[0], -1.5, 1.5)
		SetTemplateGravity(pt\Template[0], .0075)
		SetTemplateSize(pt\Template[0], 0.58*pt\Size#, 0.58*pt\Size#, .5*pt\Size#, .5*pt\Size#)
		SetTemplateFloor(pt\Template[0], -5, .45)
		SetTemplateAlphaVel(pt\Template[0], True)
		SetTemplateMaxParticles(pt\Template[0],1)
		SetEmitter(pt\Pivot, pt\Template[0])
	;============================================================================================
	;============================================================================================
	End Select

End Function
;~IDEal Editor Parameters:
;~C#Blitz3D