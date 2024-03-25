
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

	Type tBoxBlocker
		Field Position.tVector
		Field InitialPosition.tVector
		Field Entity
		Field Done
		Field BoxSize#
		Field ObjType
		Field HasObj
	End Type

; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	
	Function Object_BoxBlocker_Create.tBoxBlocker(hitbox#, x#, y#, z#, objtype)
	
		k.tBoxBlocker = New tBoxBlocker

		k\Position = New tVector
		k\Position\x# = x#
		k\Position\y# = y#
		k\Position\z# = z#
		k\InitialPosition = New tVector
		k\InitialPosition\x# = x#
		k\InitialPosition\y# = y#
		k\InitialPosition\z# = z#

		k\BoxSize#=hitbox#

		k\Entity = CreatePivot()

		TranslateEntity k\Entity, x#, y#, z#

		k\ObjType=objtype
		
		Return k
		
	End Function
	
	; =========================================================================================================

	Function Object_BoxBlocker_Update(k.tBoxBlocker, o.tObject, p.tPlayer)

		; Update position
		PositionEntity k\Entity, o\Position\x#, o\Position\y#, o\Position\z#
		k\Position\x# = EntityX(k\Entity)
		k\Position\y# = EntityY(k\Entity)
		k\Position\z# = EntityZ(k\Entity)

		; Get reality from box
		If (Not(o\ObjType=OBJTYPE_BOXLIGHT)) Then
			k\Done=o\Done
		Else
			If o\Switch\SwitchOn=1 Then k\Done=o\Done Else k\Done=1
		EndIf

	End Function

	Function Object_BoxBlocker_FindBox(o.tObject)
		For k.tBoxBlocker=Each tBoxBlocker
			If k\HasObj=False Then
				j=False
				If o\CanBeInsideBox Then j=True
				If o\ThisIsABox Then
					If o\Box\k=k Then j=False
				EndIf
				If o\ThisIsABox Or o\ThisIsAnEnemy Then
					If o\InView Then o\SavedFromInsideBoxOnce=0
					If o\SavedFromInsideBoxOnce Then j=False
				EndIf
				If j Then
					If EntityDistance(o\Entity,k\Entity)<(k\BoxSize#-1) Then
						o\IsInBox=1 : o\k=k
						k\HasObj=True
					EndIf
				EndIf
			EndIf
		Next
	End Function

	Function Object_BoxBlocker_ObjectInBox(o.tObject)

		; Give reality to objects inside
		If o\IsInBox Then
			If o\k\Done=0 And o\Done=0 Then
				If o\ThisIsABox Or o\ThisIsAnEnemy Then
					PositionEntity o\Entity, o\Position\x#,o\Position\y#+o\k\BoxSize#*1.5,o\Position\z#,1
					If o\ThisIsABox Then
						If o\Box\hask Then Object_BoxBlocker_Update(o\Box\k,o,pp(1))
					EndIf
					o\IsInBox=0
					o\SavedFromInsideBoxOnce=1
				Else
					If o\ThisIsATranslator Then
						PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-(o\k\InitialPosition\y#-o\InitialPosition\y#), o\k\Position\z#
						If o\HasEntityX Then PositionEntity o\EntityX, o\k\Position\x#, o\k\Position\y#-(o\k\InitialPosition\y#-o\InitialPosition\y#), o\k\Position\z#
					ElseIf o\ThisIsAMonitor Then
						If o\k\BoxSize#>7 Then i#=1.75 Else i#=1.5
						PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-2.2/2+o\k\BoxSize#/i#, o\k\Position\z#
						If o\HasEntityX Then PositionEntity o\EntityX, o\k\Position\x#, o\k\Position\y#-2.2/2+o\k\BoxSize#/i#, o\k\Position\z#
					Else
						Select o\ObjType
							Case OBJTYPE_SHARD:
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-2.2+o\k\BoxSize#/2, o\k\Position\z#
								If o\InView=False And (Not(o\k\ObjType=OBJTYPE_BOXCAGE Or o\k\ObjType=OBJTYPE_BOXLIGHT)) Then o\Treasure\BoxShouldHide=True
							Case OBJTYPE_SWITCH,OBJTYPE_SWITCHBASE:
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-0.5, o\k\Position\z#
								PositionEntity o\Entity2, o\k\Position\x#, o\k\Position\y#-0.5, o\k\Position\z#
								PositionEntity o\EntityX, o\k\Position\x#, o\k\Position\y#-0.5, o\k\Position\z#
								If o\ObjType=OBJTYPE_SWITCHBASE Then PositionEntity o\Entity3, o\k\Position\x#, o\k\Position\y#-0.5, o\k\Position\z#
							Case OBJTYPE_SPIKEBALL,OBJTYPE_SPIKEBOMB,OBJTYPE_SPIKECRUSHER:
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-(o\k\InitialPosition\y#-o\InitialPosition\y#), o\k\Position\z#
								If o\HasEntityX Then PositionEntity o\EntityX, o\k\Position\x#, o\k\Position\y#-(o\k\InitialPosition\y#-o\InitialPosition\y#), o\k\Position\z#
							Case OBJTYPE_OMOCHAO:
								PositionEntity o\Pivot, o\k\Position\x#, o\k\Position\y#-2.2/2+o\k\BoxSize#/2, o\k\Position\z#
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-2.2/2+o\k\BoxSize#/2, o\k\Position\z#
							Case OBJTYPE_SWITCHTOP:
								PositionEntity o\Pivot, o\k\Position\x#, o\k\Position\y#-2.2+o\k\BoxSize#/2, o\k\Position\z#
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-2.2+o\k\BoxSize#/2, o\k\Position\z#
							Case OBJTYPE_BELL:
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-0.2+o\k\BoxSize#/2, o\k\Position\z#
							Default:
								PositionEntity o\Entity, o\k\Position\x#, o\k\Position\y#-2.2+o\k\BoxSize#/2, o\k\Position\z#
						End Select
					EndIf
				EndIf
			Else
				o\IsInBox=0
				o\k\HasObj=False
			EndIf
		EndIf

	End Function