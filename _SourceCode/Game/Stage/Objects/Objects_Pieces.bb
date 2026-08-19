
	Function Object_Pieces_Create_RobotPieces(x#, y#, z#, pitch#, yaw#, roll#, enemygold#=0)
		For i=1 To 12
			Object_Piece_Create.tObject(Mesh_EnemyPiece1+Rand(1,7)-1, x#, y#, z#, pitch#, yaw#, roll#, 0.25, enemygold#)
		Next
	End Function

	Function Object_Pieces_Create(thisisanenemy, objectno#, psychoed#, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#=1, enemygold#=0, dontspawnmesh=False, special#=0)

		If thisisanenemy Then
			If Object_IsEnemyRobot(objectno#) Then
				Object_Pieces_Create_RobotPieces(x#, y#, z#, pitch#, yaw#, roll#, enemygold#)
			EndIf
		EndIf

		If dontspawnmesh=False Then
		Select objectno#
			Case OBJTYPE_PAWN,OBJTYPE_PAWNSHIELD,OBJTYPE_PAWNGUN,OBJTYPE_PAWNSWORD
				Select objectno#
					Case OBJTYPE_PAWNSHIELD
						If special#<>0 Then Object_Piece_Create.tObject(Mesh_Enemy_PawnShield, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
					Case OBJTYPE_PAWNGUN
						Object_Piece_Create.tObject(Mesh_Enemy_PawnGun, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
					Case OBJTYPE_PAWNSWORD
						Object_Piece_Create.tObject(Mesh_Enemy_PawnSword, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
				End Select
			Case -OBJTYPE_PAWNSHIELD:
				Object_Piece_Create.tObject(Mesh_Enemy_PawnShield, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
			Case OBJTYPE_HUNTER,OBJTYPE_HUNTERSHIELD
				Select objectno#
					Case OBJTYPE_HUNTERSHIELD
						If special#<>0 Then Object_Piece_Create.tObject(Mesh_Enemy_HunterShield, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
				End Select
			Case -OBJTYPE_HUNTERSHIELD:
				Object_Piece_Create.tObject(Mesh_Enemy_HunterShield, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
			Case OBJTYPE_WARRIOR,OBJTYPE_WARRIORGUN1,OBJTYPE_WARRIORGUN2
				Select objectno#
					Case OBJTYPE_WARRIORGUN1
						Object_Piece_Create.tObject(Mesh_Enemy_BlackWarriorGun1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
					Case OBJTYPE_WARRIORGUN2
						Object_Piece_Create.tObject(Mesh_Enemy_BlackWarriorGun2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
				End Select
			Case OBJTYPE_OAKSWORD
				Select objectno#
					Case OBJTYPE_OAKSWORD
						Object_Piece_Create.tObject(Mesh_Enemy_BlackOakSword, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
				End Select
			Case OBJTYPE_SOLDIER
				Object_Piece_Create.tObject(Mesh_Enemy_Soldier, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
			Case OBJTYPE_SOLDIERCAMO
				Object_Piece_Create.tObject(Mesh_Enemy_Soldier2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)	
				
			Case OBJTYPE_HAMMER,OBJTYPE_HAMMERSHIELD,OBJTYPE_HAMMERHAMMER
				Select objectno#
					Case OBJTYPE_HAMMERSHIELD
						If special#<>0 Then Object_Piece_Create.tObject(Mesh_Enemy_HammerShield, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
					Case OBJTYPE_HAMMERHAMMER
						Object_Piece_Create.tObject(Mesh_Enemy_HammerHammer, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
				End Select
			Case -OBJTYPE_HAMMERSHIELD:
				Object_Piece_Create.tObject(Mesh_Enemy_HammerShield, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#)
			Case OBJTYPE_BOXWOODEN
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Wooden)
			Case OBJTYPE_BOXMETAL
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Metal)
			Case OBJTYPE_BOXIRON
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Iron)
			Case OBJTYPE_BOXCAGE
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Cage)
			Case OBJTYPE_BOXTNT
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Tnt)
			Case OBJTYPE_BOXNITRO
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#+180, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#, yaw#-90, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#-90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece1, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece2, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece3, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
				Object_Piece_Create.tObject(Mesh_BoxPiece4, x#, y#, z#, pitch#+90, yaw#, roll#, scalefactor#, 0, False, Object_Texture_Box_Nitro)
			Case OBJTYPE_SPIKEBOMB,OBJTYPE_SPEWSPIKEBOMB
				Object_Piece_Create.tObject(Mesh_SpikeBombPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#)
				Object_Piece_Create.tObject(Mesh_SpikeBombPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#)
			Case OBJTYPE_SPIKECRUSHER
				Object_Piece_Create.tObject(Mesh_SpikeCrusherPiece1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#)
				Object_Piece_Create.tObject(Mesh_SpikeCrusherPiece2, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#)
			Case OBJTYPE_BALLOON
				For i=1 To 12
				Select(Rand(1,5))
				Case 1: Object_Piece_Create.tObject(Mesh_Balloonpiece1, x#+Rand(-0.05,0.05), y#+Rand(-0.05,0.05), z#+Rand(-0.05,0.05), pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
				Case 2: Object_Piece_Create.tObject(Mesh_Balloonpiece2, x#+Rand(-0.05,0.05), y#+Rand(-0.05,0.05), z#+Rand(-0.05,0.05), pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
				Case 3: Object_Piece_Create.tObject(Mesh_Balloonpiece3, x#+Rand(-0.05,0.05), y#+Rand(-0.05,0.05), z#+Rand(-0.05,0.05), pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
				Case 4: Object_Piece_Create.tObject(Mesh_Balloonpiece4, x#+Rand(-0.05,0.05), y#+Rand(-0.05,0.05), z#+Rand(-0.05,0.05), pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
				Case 5: Object_Piece_Create.tObject(Mesh_Balloonpiece5, x#+Rand(-0.05,0.05), y#+Rand(-0.05,0.05), z#+Rand(-0.05,0.05), pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
				End Select
				Next
			Case OBJTYPE_ROCK
				For i=1 To 40
				Select special#
					Case 1:
						Select(Rand(1,3))
						Case 1: Object_Piece_Create.tObject(Mesh_RockChunk1_brown, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 2: Object_Piece_Create.tObject(Mesh_RockChunk2_brown, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 3: Object_Piece_Create.tObject(Mesh_RockChunk3_brown, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						End Select
					Case 2:
						Select(Rand(1,3))
						Case 1: Object_Piece_Create.tObject(Mesh_RockChunk1_grey, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 2: Object_Piece_Create.tObject(Mesh_RockChunk2_grey, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 3: Object_Piece_Create.tObject(Mesh_RockChunk3_grey, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						End Select
				End Select
				Next
			Case OBJTYPE_CRYSTAL
				For i=1 To 25
				Select special#
					Case 1:
						Select(Rand(1,3))
						Case 1: Object_Piece_Create.tObject(Mesh_CrystalChunk1_red, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 2: Object_Piece_Create.tObject(Mesh_CrystalChunk2_red, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 3: Object_Piece_Create.tObject(Mesh_CrystalChunk3_red, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						End Select
					Case 2:
						Select(Rand(1,3))
						Case 1: Object_Piece_Create.tObject(Mesh_CrystalChunk1_green, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 2: Object_Piece_Create.tObject(Mesh_CrystalChunk2_green, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 3: Object_Piece_Create.tObject(Mesh_CrystalChunk3_green, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						End Select
					Case 3:
						Select(Rand(1,3))
						Case 1: Object_Piece_Create.tObject(Mesh_CrystalChunk1_blue, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 2: Object_Piece_Create.tObject(Mesh_CrystalChunk2_blue, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						Case 3: Object_Piece_Create.tObject(Mesh_CrystalChunk3_blue, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
						End Select
				End Select
				Next
			Case OBJTYPE_ICICLE,OBJTYPE_ICICLEBIG:
				For i=1 To 25
					Select(Rand(1,3))
					Case 1: Object_Piece_Create.tObject(Mesh_IcicleShard1, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
					Case 2: Object_Piece_Create.tObject(Mesh_IcicleShard2, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
					Case 3: Object_Piece_Create.tObject(Mesh_IcicleShard3, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
					End Select
				Next
			Case OBJTYPE_ICEDECOR:
				Select special#
					Case 1:
						For i=1 To 25
							Select(Rand(1,2))
							Case 1: Object_Piece_Create.tObject(Mesh_IceDecorShard1, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
							Case 2: Object_Piece_Create.tObject(Mesh_IceDecorShard2, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
							End Select
						Next
					Case 2:
						For i=1 To 20
							Select(Rand(1,3))
							Case 1: Object_Piece_Create.tObject(Mesh_IceDecorShard3, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
							Case 2: Object_Piece_Create.tObject(Mesh_IceDecorShard4, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
							Case 3: Object_Piece_Create.tObject(Mesh_IceDecorShard5, x#, y#, z#, pitch#+Rand(0,360), yaw#+Rand(0,360), roll#+Rand(0,360), scalefactor#)
							End Select
						Next
				End Select
			Case OBJTYPE_AUTO
				Object_Piece_Create.tObject(Mesh_Car_Sedan1+special#-1, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#)
		End Select
		EndIf

	End Function
	
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
; /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/

Function Object_Piece_Create.tObject(mesh, x#, y#, z#, pitch#, yaw#, roll#, scalefactor#, enemygold#=0, shallnotfade=False, texture=0, confetti=False, alphafade=False)
	o.tObject = New tObject : o\ObjType = OBJTYPE_PIECE :  : o\ID=0
	o\Piece = New tObject_Piece : o\HasValuesetPiece=True
	o\AlwaysPresent=True
	
	Object_CreateHitBox(HITBOXTYPE_NORMAL,o,0,0,0)
	
	Object_Acquire_Position(o,x#,y#,z#)
	Object_Acquire_Rotation(o,pitch#,yaw#,roll#)
	
	o\Piece\Confetti=confetti
	
	If confetti=False Then
		Select(Rand(1,2))
			Case 1: o\Piece\PositionXp=True
			Case 2: o\Piece\PositionXp=False
		End Select
		Select(Rand(1,2))
			Case 1: o\Piece\PositionYp=True
			Case 2: o\Piece\PositionYp=False
		End Select
		Select(Rand(1,2))
			Case 1: o\Piece\PositionZp=True
			Case 2: o\Piece\PositionZp=False
		End Select
		Select(Rand(1,2))
			Case 1: o\Piece\PositionXn=True
			Case 2: o\Piece\PositionXn=False
		End Select
		Select(Rand(1,2))
			Case 1: o\Piece\PositionYn=True
			Case 2: o\Piece\PositionYn=False
		End Select
		Select(Rand(1,2))
			Case 1: o\Piece\PositionZn=True
			Case 2: o\Piece\PositionZn=False
		End Select
	Else
		o\Piece\SpeedX#=Rand(1,5)/25.0
		o\Piece\SpeedY#=Rand(1,5)/50.0
		o\Piece\SpeedZ#=Rand(1,5)/50.0
	EndIf
	Select(Rand(1,2))
		Case 1: o\Piece\RotationXp=True
		Case 2: o\Piece\RotationXp=False
	End Select
	Select(Rand(1,2))
		Case 1: o\Piece\RotationYp=True
		Case 2: o\Piece\RotationYp=False
	End Select
	Select(Rand(1,2))
		Case 1: o\Piece\RotationZp=True
		Case 2: o\Piece\RotationZp=False
	End Select
	Select(Rand(1,2))
		Case 1: o\Piece\RotationXn=True
		Case 2: o\Piece\RotationXn=False
	End Select
	Select(Rand(1,2))
		Case 1: o\Piece\RotationYn=True
		Case 2: o\Piece\RotationYn=False
	End Select
	Select(Rand(1,2))
		Case 1: o\Piece\RotationZn=True
		Case 2: o\Piece\RotationZn=False
	End Select
	
	Select confetti
		Case False: o\Piece\PieceTimer=0.53*secs#: o\Piece\PieceAlphaTimer=1*secs#
		Case True: o\Piece\PieceTimer=(Rand(1,5))*secs#
	End Select
	
	o\Piece\ShallNotFade=shallnotfade
	
	o\Entity = CreatePivot()
	o\EntityX = CopyEntity(MESHES(SmartEntity(mesh)), Game\Stage\Root)
	
	Select mesh
		Case Mesh_Enemy_Soldier,Mesh_Enemy_Soldier2
			Animate(o\EntityX,1,0.2,5)
			o\Mode=1
		
	End Select
		
	If texture<>0 Then EntityTexture o\EntityX,texture
	If enemygold#=1 Then EntityTexture o\EntityX,Object_Texture_Gold
	If alphafade Then EntityAlpha(o\EntityX,0.45)
	
	RotateEntity o\Entity, pitch#, yaw#, roll#
	PositionEntity o\Entity, x#, y#, z#
	RotateEntity o\EntityX, pitch#, yaw#, roll#
	PositionEntity o\EntityX, x#, y#, z#
	o\Repose=1
	ScaleEntity o\EntityX, scalefactor#, scalefactor#, scalefactor#
	
	Return o
End Function
	
	; =========================================================================================================
	
	Function Object_Piece_Update(o.tObject, p.tPlayer, d.tDeltaTime)

		; Movement
		If o\Piece\ShallNotFade=False And o\Mode=0 Then
			If o\Piece\Confetti=False Then
				If o\Piece\PositionXp Then MoveEntity o\Entity,0.5*p\Physics\UNDERWATERTRIGGER#*d\Delta,0,0 Else MoveEntity o\Entity,0.2*p\Physics\UNDERWATERTRIGGER#*d\Delta,0,0
				If o\Piece\PositionYp Then MoveEntity o\Entity,0,0.5*p\Physics\UNDERWATERTRIGGER#*d\Delta,0 Else MoveEntity o\Entity,0,0.2*p\Physics\UNDERWATERTRIGGER#*d\Delta,0
				If o\Piece\PositionZp Then MoveEntity o\Entity,0,0,0.5*p\Physics\UNDERWATERTRIGGER#*d\Delta Else MoveEntity o\Entity,0,0,0.2*p\Physics\UNDERWATERTRIGGER#*d\Delta
				If o\Piece\PositionXn Then MoveEntity o\Entity,-0.5*p\Physics\UNDERWATERTRIGGER#*d\Delta,0,0 Else MoveEntity o\Entity,-0.2*p\Physics\UNDERWATERTRIGGER#*d\Delta,0,0
				If o\Piece\PositionYn Then MoveEntity o\Entity,0,-0.5*p\Physics\UNDERWATERTRIGGER#*d\Delta,0 Else MoveEntity o\Entity,0,-0.2*p\Physics\UNDERWATERTRIGGER#*d\Delta,0
				If o\Piece\PositionZn Then MoveEntity o\Entity,0,0,-0.5*p\Physics\UNDERWATERTRIGGER#*d\Delta Else MoveEntity o\Entity,0,0,-0.2*p\Physics\UNDERWATERTRIGGER#*d\Delta
				If o\Piece\RotationXp Then TurnEntity o\Entity,10*d\Delta,0,0 Else TurnEntity o\Entity,5*d\Delta,0,0
				If o\Piece\RotationYp Then TurnEntity o\Entity,0,10*d\Delta,0 Else TurnEntity o\Entity,0,5*d\Delta,0
				If o\Piece\RotationZp Then TurnEntity o\Entity,0,0,10*d\Delta Else TurnEntity o\Entity,0,0,5*d\Delta
				If o\Piece\RotationXn Then TurnEntity o\Entity,-10*d\Delta,0,0 Else TurnEntity o\Entity,-5*d\Delta,0,0
				If o\Piece\RotationYn Then TurnEntity o\Entity,0,-10*d\Delta,0 Else TurnEntity o\Entity,0,-5*d\Delta,0
				If o\Piece\RotationZn Then TurnEntity o\Entity,0,0,-10*d\Delta Else TurnEntity o\Entity,0,0,-5*d\Delta
				RotateEntity o\EntityX, o\Rotation\x#, o\Rotation\y#, o\Rotation\z#
			Else
				MoveEntity o\Entity,o\Piece\SpeedX#*d\Delta,o\Piece\SpeedY#*d\Delta,o\Piece\SpeedZ#*d\Delta
				If o\Piece\RotationXp Then TurnEntity o\EntityX,10*d\Delta,0,0 Else TurnEntity o\EntityX,5*d\Delta,0,0
				If o\Piece\RotationYp Then TurnEntity o\EntityX,0,10*d\Delta,0 Else TurnEntity o\EntityX,0,5*d\Delta,0
				If o\Piece\RotationZp Then TurnEntity o\EntityX,0,0,10*d\Delta Else TurnEntity o\EntityX,0,0,5*d\Delta
				If o\Piece\RotationXn Then TurnEntity o\EntityX,-10*d\Delta,0,0 Else TurnEntity o\EntityX,-5*d\Delta,0,0
				If o\Piece\RotationYn Then TurnEntity o\EntityX,0,-10*d\Delta,0 Else TurnEntity o\EntityX,0,-5*d\Delta,0
				If o\Piece\RotationZn Then TurnEntity o\EntityX,0,0,-10*d\Delta Else TurnEntity o\EntityX,0,0,-5*d\Delta
			EndIf
		EndIf
		PositionEntity o\EntityX, o\Position\x#, o\Position\y#+o\HitBox\y#, o\Position\z#

		; Update enemy timer
		If o\Piece\PieceTimer>0 Then o\Piece\PieceTimer=o\Piece\PieceTimer-timervalue#
		If o\Piece\PieceAlphaTimer>0 Then 
			o\Piece\PieceAlphaTimer=o\Piece\PieceAlphaTimer-timervalue*1.5
			If o\Mode=0 Then EntityAlpha(o\EntityX,o\Piece\PieceAlphaTimer/secs#)
		EndIf

		; Delete the object
		If (Not(o\Piece\PieceTimer>0)) And o\Piece\ShallNotFade=False Then
			; Delete the object
			ParticleTemplate_Delete(o\Particle)
			FreeEntity o\Entity
			FreeEntity o\EntityX
			Delete o\Position
			Delete o\Rotation
			Delete o
			Return
		EndIf
		
	End Function
;~IDEal Editor Parameters:
;~C#Blitz3D