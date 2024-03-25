.lib "FastImage.dll"
DrawVersion$ () : "DrawVersion_"

InitDraw_% (Direct3DDevice7%, DirectDraw7%) : "InitDraw_"
DeinitDraw%() : "DeinitDraw_"
StartDraw% () : "StartDraw_"
EndDraw% () : "EndDraw_"
SetCustomState% (operation%, value%) : "SetCustomState_"
SetCustomTextureState% (operation%, value%) : "SetCustomTextureState_"
SetMipLevel% (level%) : "SetMipLevel_"
SetBlend% (blend%) : "SetBlend_"
SetAlpha% (alpha#) : "SetAlpha_"
SetColor% (r%, g%, b%) : "SetColor_"
SetCustomColor% (colorVertex0%, colorVertex1%, colorVertex2%, colorVertex3%) : "SetCustomColor_"
SetRotation% (rotation#) : "SetRotation_"
SetScale% (scaleX#, scaleY#) : "SetScale_"
SetTransform% (rotation#, scaleX#, scaleY#) : "SetTransform_"
SetMatrix% (xx#, xy#, yx#, yy#) : "SetMatrix_"
SetHandle% (x#, y#) : "SetHandle_"
SetOrigin% (x#, y#) : "SetOrigin_"
MidHandleImage% (image%) : "MidHandleImage_"
SetImageHandle% (image%, x#, y#) : "SetImageHandle_"
AutoMidHandleEx% (state%) : "AutoMidHandleEx_"
AutoImageFlags% (flags%) : "AutoImageFlags_"
SetLineWidth% (width#) : "SetLineWidth_"
SetViewport% (x%, y%, width%, height%) : "SetViewport_"
CreateImageEx_% (texure%, width%, height%, flags%) : "CreateImageEx_"
FreeImageEx_% (image%) : "FreeImageEx_"
DrawImageEx_% (image%, x#, y#, frame%) : "DrawImageEx_"
DrawImageRectEx_% (image%, x#, y#, width#, height#, frame%) : "DrawImageRectEx_"
DrawImagePart_% (image%, x#, y#, width#, height#, partX#, partY#, partWidth#, partHeight#, frame%, wrap%) : "DrawImagePart_"
DrawPoly_% (x#, y#, bank%, image%, frame%, color%) : "DrawPoly_"
DrawPolyEx_% (x#, y#, bank%, image%, frame%, color%) : "DrawPolyEx_"
DrawRect_% (x#, y#, width#, height#, fill%) : "DrawRect_"
DrawRectSimple_% (x#, y#, width#, height#, fill%) : "DrawRectSimple_"
DrawLine% (x#, y#, x2#, y2#) : "DrawLine_"
DrawLineSimple% (x#, y#, x2#, y2#) : "DrawLineSimple_"
DrawPlot% (x#, y#) : "DrawPlot_"
DrawOval% (x#, y#, width#, height#) : "DrawOval_"
GetProperty_%(type*) : "GetProperty_"
GetImageProperty_%(image%, type*) : "GetImageProperty_"
SetProjScale% (scaleX#, scaleY#) : "SetProjScale_"
SetProjRotation% (rotation#) : "SetProjRotation_"
SetProjTransform% (rotation#, scaleX#, scaleY#) : "SetProjTransform_"
SetProjOrigin% (x#, y#) : "SetProjOrigin_"
SetProjHandle% (x#, y#) : "SetProjHandle_"
MidHandleProj% () : "MidHandleProj_"
CreateImageFont% (type*) : "CreateImageFont_"
SetImageFont_% (font%, customKerning%) : "SetImageFont_"
FreeImageFont_% (font%) : "FreeImageFont_"
DrawText_% (text$, x#, y#, centerX%, centerY%, maxWidth%) : "DrawText_"
DrawTextRect_% (text$, x#, y#, w#, h#, centerX%, centerY%, lineSpacing%) : "DrawTextRect_"
TextRectCount%() : "TextRectCount_"
TextRectMaxWidth%() : "TextRectMaxWidth_"
TextRectWidth%(StringNumber%) : "TextRectWidth_"
StringWidthEx_%(text$, maxWidth%) : "StringWidthEx_"
StringHeightEx%(text$) : "StringHeightEx_"
GetFontProperty_%(font%, type*) : "GetFontProperty_"
TestRect_% (xPoint#, yPoint#, xRect#, yRect#, WidthRect#, HeightRect#, Local%, Result*, ResultFlag%) : "TestRect_"
TestOval_% (xPoint#, yPoint#, xOval#, yOval#, WidthOval#, HeightOval#, Local%, Result*, ResultFlag%) : "TestOval_"
TestImage_% (xPoint#, yPoint#, xImage#, yImage#, Image%, Local%, Result*, ResultFlag%) : "TestImage_"
TestRendered_% (xPoint#, yPoint#, Local%, Result*, ResultFlag%) : "TestRendered_"
GetCustomTransform_% (type%, martix*) : "GetCustomTransform_"
SetCustomTransform_% (type%, matrix*) : "SetCustomTransform_"
ImagesIsLost% () : "ImagesIsLost_"
LoadImageEx_% (fileName$, texture%, imageFlags%) : "LoadImageEx_"
LoadFontProperty_$ (type*, filename$, flags) : "LoadFontProperty_"

UpdateImage_% (image%, texture%, width%, height%) : "UpdateImage_"
UpdateImageFlag_% (image%, update%) : "UpdateImageFlag_"
SetCoordsRound% (mode%) : "SetCoordsRound_"
SetTint% (level#, red%, green%, blue%) : "SetTint_"
CountImages% () : "CountImages_"
GetImageByIndex% (index%) : "GetImageByIndex_"
SetDefaultProperty_% (all%) : "SetDefaultProperty_"



.lib " "

CreateImageEx% (texure%, width%, height%, flags%)
LoadImageEx% (fileName$, textureFlags%, imageFlags%)
LoadAnimImageEx% (fileName$, textureFlags%, frameWidth%, frameHeight%, firstFrame%, frameCount%, imageFlags%)
DrawImageEx% (image%, x#, y#, frame%)
DrawImageRectEx% (image%, x#, y#, width#, height#, frame%)
DrawImagePart% (image%, x#, y#, width#, height#, partX#, partY#, partWidth#, partHeight#, frame%)
DrawRect% (x#, y#, width#, height#, fill%)
DrawRectSimple% (x#, y#, width#, height#, fill%)
DrawPoly% (x#, y#, bank%, image%, frame%, color%)
DrawPolyEx% (x#, y#, bank%, image%, frame%, color%)
SetCustomBlend% (src%, dest%)

InitDraw% ()
GetProperty% ()
GetImageProperty% (image%)
GetFontProperty% (font%)

LoadImageFont% (filename$, flags%)
SetImageFont% (font%, customKerning%)
DrawText% (txt$, x#, y#, centerX%, centerY%)
DrawTextRect% (txt$, x#, y#, w#, h#, centerX%, centerY%, lineSpacing%)

TestRect% (xPoint#, yPoint#, xRect#, yRect#, WidthRect#, HeightRect#, Local%)
TestOval% (xPoint#, yPoint#, xOval#, yOval#, WidthOval#, HeightOval#, Local%)
TestImage% (xPoint#, yPoint#, xImage#, yImage#, Image%, alphaLevel%, Frame%, Local%)
TestRendered% (xPoint#, yPoint#, alphaLevel%, Local%)

UpdateImage% (image%, texture%, width%, height%)
UpdateImageFlag% (image%, update%)

SetDefault% (all%)