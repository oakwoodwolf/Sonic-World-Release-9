
Global SaveDataPath$ = GetEnv$("AppData")+"\Sonic World DX\SaveData\"
Global SaveDataFormat$ = ".dat"
Global SaveDataTmp$ = SaveDataPath$+"tmp"+SaveDataFormat$
Global CurrentOpenFile
Global CurrentOpenFileName$

;------------------------------------------------------------------------------------------------------------------------------

Function LoadFileWithEncryption(name$)
	CurrentOpenFileName$=name$
	DecryptFile(name$)
End Function

Function WriteFileWithEncryption(name$,mainroot$="savedata")
	CurrentOpenFileName$=name$
	CurrentOpenFile=0
	CurrentOpenFile=WriteFile(SaveDataTmp$)

	WriteLine(CurrentOpenFile,"<?xml version="+Chr$(34)+"1.0"+Chr$(34)+"?>")
	WriteLine(CurrentOpenFile,"<"+mainroot$+">")
End Function

Function CloseLoadedFileWithEncryption()
	DeleteFile(SaveDataTmp$)
End Function

Function CloseWrittenFileWithEncryption(mainroot$="savedata")
	WriteLine(CurrentOpenFile,"</"+mainroot$+">")

	CloseFile(CurrentOpenFile)
	EncryptFile(CurrentOpenFileName$)
	DeleteFile(SaveDataTmp$)
End Function

;------------------------------------------------------------------------------------------------------------------------------

Function EncryptFile(name$)
	filein = OpenFile(SaveDataTmp$)
	fileout = WriteFile(SaveDataPath$+name$+SaveDataFormat$)
	WriteInt(fileout, EncryptThereto(111)) : WriteInt(fileout, EncryptThereto(122))
	While Not Eof(filein)
		a = ReadInt(filein)
		WriteInt(fileout, EncryptThereto(a))
		For i=1 To 3 : WriteInt(fileout, EncryptThereto(Rand(0,130))) : Next
	Wend
	CloseFile(filein)
	CloseFile(fileout)
End Function

Function DecryptFile(name$)
	filein = OpenFile(SaveDataPath$+name$+SaveDataFormat$)
	fileout = WriteFile(SaveDataTmp$)
	oz1 = DecryptTherefrom(ReadInt(filein)) : oz2 = DecryptTherefrom(ReadInt(filein))
	If Not(oz1=111 And oz2=122) Then CloseFile(filein) : CloseFile(fileout) : ErrorDecryption()
	While Not Eof(filein)
		a = ReadInt(filein)
		WriteInt(fileout, DecryptTherefrom(a))
		For i=1 To 3 : ReadInt(filein) : Next
	Wend
	CloseFile(filein)
	CloseFile(fileout)
End Function

Function ErrorDecryption()
	DeleteFile(SaveDataTmp$)
	ResetAll()
	RuntimeError("Corrupted save data was reset.")
End Function

Function EncryptThereto(a)
	Return a+1200
End Function

Function DecryptTherefrom(a)
	Return a-1200
End Function

;------------------------------------------------------------------------------------------------------------------------------