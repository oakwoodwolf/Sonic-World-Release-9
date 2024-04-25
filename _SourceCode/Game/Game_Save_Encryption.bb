
Global SaveDataPath$ = GetEnv$("AppData")+"\Sonic World\SaveData\"
Global SaveDataFormat$ = ".dat"
Global SaveDataFormatUnencrypted$ = ".xml"
Global SaveDataTmp$ = SaveDataPath$+"tmp"+SaveDataFormat$
Global CurrentOpenFile
Global CurrentOpenFileName$

;------------------------------------------------------------------------------------------------------------------------------
;; Loads an encrypted file by name and decrypts it.
;;param name the Filename
Function LoadFileWithEncryption(name$)
	CurrentOpenFileName$=name$
	DecryptFile(name$)
End Function

;; Saves an encrypted file by name and encrypts it.
;;param name the Filename
;;param mainroot the body of the file to be written.
Function WriteFileWithEncryption(name$,mainroot$="savedata")
	CurrentOpenFileName$=name$
	CurrentOpenFile=0
	CurrentOpenFile=WriteFile(SaveDataTmp$)

	WriteLine(CurrentOpenFile,"<?xml version="+Chr$(34)+"1.0"+Chr$(34)+"?>")
	WriteLine(CurrentOpenFile,"<"+mainroot$+">")
End Function
;; Saves an encrypted file by name and encrypts it.
;;param name the Filename
;;param mainroot the body of the file to be written.
Function WriteFileWithoutEncryption(name$,mainroot$="savedata")
	CurrentOpenFileName$=name$
	CurrentOpenFile=0
	CurrentOpenFile=WriteFile(SaveDataPath$+name$+SaveDataFormatUnencrypted$)
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
Function CloseWrittenFileWithoutEncryption(mainroot$="savedata")
	WriteLine(CurrentOpenFile,"</"+mainroot$+">")
	CloseFile(CurrentOpenFile)
	DeleteFile(SaveDataTmp$)
End Function

;------------------------------------------------------------------------------------------------------------------------------

Function EncryptFile(name$)
	filein = OpenFile(SaveDataTmp$)
	fileout = WriteFile(SaveDataPath$+name$+SaveDataFormat$)
	WriteInt(fileout, EncryptThereto(111)) : WriteInt(fileout, EncryptThereto(122))
	while not eof(filein)
		a = ReadInt(filein)
		WriteInt(fileout, EncryptThereto(a))
		for i=1 to 3 : WriteInt(fileout, EncryptThereto(Rand(0,130))) : next
	wend
	CloseFile(filein)
	CloseFile(fileout)
End Function

Function DecryptFile(name$)
	filein = OpenFile(SaveDataPath$+name$+SaveDataFormat$)
	fileout = WriteFile(SaveDataTmp$)
	oz1 = DecryptTherefrom(ReadInt(filein)) : oz2 = DecryptTherefrom(ReadInt(filein))
	If Not(oz1=111 and oz2=122) Then CloseFile(filein) : CloseFile(fileout) : ErrorDecryption()
	while not eof(filein)
		a = ReadInt(filein)
		WriteInt(fileout, DecryptTherefrom(a))
		for i=1 to 3 : ReadInt(filein) : next
	wend
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