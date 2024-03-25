.lib "FastExt.dll"
ExtVersion$ () : "ExtVersion_"

InitExt_% (Direct3DDevice7%, BackBuf%, DriverCaps*) : "InitExt_"
DeInitExt_% () : "DeInitExt_"
SetBuffer_% (buffer%) : "SetBuffer_"
ClsColor_% (red%, green%, blue%, alpha%, ZValue#)
Cls_% (clearColor%, clearZBuffer%)
Wireframe_% (enable%) : "Wireframe_"
Bump_% (enable%) : "Bump_"
BumpPower% (power#) : "BumpPower_"
CopyRectStretch% (src_x%, src_y%, src_width%, src_height%, dest_x%, dest_y%, dest_width%, dest_height%, src_buffer%, dest_buffer% ) : "CopyRectStretch_"
FreeTexture_% (texture%, textureBuffer%) : "FreeTexture_"
CreateClipplane_% (x1#, y1#, z1#, x2#, y2#, z2#, x3#, y3#, z3#) : "CreateClipplane_"
AlignClipplane_% (clipplane%, x1#, y1#, z1#, x2#, y2#, z2#, x3#, y3#, z3#) : "AlignClipplane_"
HideClipplane% (clipplane%) : "HideClipplane_"
ShowClipplane% (clipplane%) : "ShowClipplane_"
FreeClipplane% (clipplane%) : "FreeClipplane_"
FreeClipplanes% () : "FreeClipplanes_"
ParentEntity% (entity%) : "ParentEntity_"
ColorFilter_% (red%, green%, blue%, alpha%) : "ColorFilter_"
EntityID_% (entity%, id%) : "EntityID_"
BlendTexture% (texture%) : "BlendTexture_"
TextureBlend_% (texture%, blend%) : "TextureBlend_"
SetCubeAlign% (texture%,entity_id%) : "SetCubeAlign_"
RenderEntityLight% (number%,light%) : "RenderEntityLight_"
RenderEntity_% (entity%, camera%, tween#, clearViewport%, cameraSys%) : "RenderEntity_"
FogMode% (mode%) : "FogMode_"
FogDensity% (density#) : "FogDensity_"
TextureAnisotropy_% (level%, index%) : "TextureAnisotropy_"
TextureLodBias_% (bias#, index%) : "TextureLodBias_"
ExecAndExit_% (file$, command$, workingDir$) : "ExecAndExit_"

InitPostprocess_% (buffer0%, buffer1%, buffer2%, buffer3%, buffer4%, buffer5%) : "InitPostprocess_"
RenderPostprocess_% (flags%, x%, y%, width%, height%) : "RenderPostprocess_"
CustomPostprocessDOF_% (near#, far#, direction%, level%, blur#, quality%) : "CustomPostprocessDOF_"
CustomPostprocessGlow_% (alpha#, dPasses%, bPasses%, blur#, quality%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessGlow_"
CustomPostprocessGlowEx% (lPasses%, lMethod%) : "CustomPostprocessGlowEx_"
CustomPostprocessBlur_% (alpha#, bPasses%, blur#, quality%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessBlur_"
CustomPostprocessInverse_% (alpha#, red%, green%, blue%, alphaTexture%) : "CustomPostprocessInverse_"
CustomPostprocessGrayscale_% (alpha#, brightness#, method%, alphaTexture%) : "CustomPostprocessGrayscale_"
CustomPostprocessContrast_% (alpha#, method%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessContrast_"
CustomPostprocessBlurDirectional_% (angle#, alpha#, blurPasses%, blurDistance#, quality%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessBlurDirectional_"
CustomPostprocessBlurZoom_% (centerX#, centerY#, zoomFactor#, alpha#, blurPasses%, quality%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessBlurZoom_"
CustomPostprocessBlurSpin_% (centerX#, centerY#, spinAngle#, alpha#, blurPasses%, quality%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessBlurSpin_"
CustomPostprocessBlurMotion_% (alpha#, originX#, originY#, handleX#, handleY#, scaleX#, scaleY#, angle#, blend%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessBlurMotion_"
CustomPostprocessOverlay_% (alpha#, blend%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessOverlay_"
CustomPostprocessPosterize% (level%) : "CustomPostprocessPosterize_"
CustomPostprocessRays_% (centerX#, centerY#, zoomFactor#, alpha#, dPass%, bPass%, quality%, red%, green%, blue%, alphaTexture%) : "CustomPostprocessRays_"
CustomPostprocessRaysEx% (lightPasses%, lightMethod%) : "CustomPostprocessRaysEx_"

SetProjMatrix% (index%, matrix*) : "SetProjMatrix_"
SetViewMatrix% (index%, matrix*) : "SetViewMatrix_"
SetProjMatrixCurrent% (index%) : "SetProjMatrixCurrent_"
SetViewMatrixCurrent% (index%) : "SetViewMatrixCurrent_"
CopyProjMatrix% (matrix*) : "CopyProjMatrix_"
CopyViewMatrix% (matrix*) : "CopyViewMatrix_"
IndexTexture% (texture%) : "IndexTexture_"
TextureIndex% (texture%, index%) : "TextureIndex_"
EntityAutoFadeMode% (entity%, state%) : "EntityAutoFadeMode_"
OverwriteFX% (FX_ValueOr%, FX_ValueAnd%) : "OverwriteFX_"
GetOverwriteFX% (type%) : "GetOverwriteFX_"

GroupAttach% (group%, entity%) : "GroupAttach_"
GroupDetach% (group%, entity%) : "GroupDetach_"
GroupChange% (group%, entity%) : "GroupChange_"
GroupFind% (group%, entity%) : "GroupFind_"
ClearGroup% (group%) : "ClearGroup_"
ClearGroups% () : "ClearGroups_"
RenderGroup_% (group%, camera%, tween#, clearViewport%, pivotSys%) : "RenderGroup_"

MatrixInverse% (out*, in*) : "MatrixInverse"
MatrixMultiply (out*, in1*, in2*) : "MatrixMultiply"

.lib " "
InitPostprocess% ()
MirrorCamera% (camera%, entity%)
RestoreCamera% (camera%)
TextureBlendCustom% (texture%, color_operation%, alpha_operation%, projection_flag%)
EntityBlendCustom% (entity%, source_blend%, destination_blend%, alphablending_enable%)
BrushBlendCustom% (brush%, source_blend%, destination_blend%, alphablending_enable%)
RenderPostprocess% (flags%, x%, y%, width%, height%)
CustomPostprocessDOF% (near#, far#, direction%, level%, blurRadius#, quality%)
CustomPostprocessGlow% (alpha#, darkPasses%, blurPasses%, blurRadius#, quality%, red%, green%, blue%, alphaTexture%)
CustomPostprocessBlur% (alpha#, blurPasses%, blurRadius#, quality%, red%, green%, blue%, alphaTexture%)
CustomPostprocessInverse% (alpha#, red%, green%, blue%, alphaTexture%)
CustomPostprocessGrayscale% (alpha#, brightness#, inverse%, alphaTexture%)
CustomPostprocessContrast% (alpha#, method%, red%, green%, blue%, alphaTexture%)
CustomPostprocessBlurDirectional% (angle#, alpha#, blurPasses%, blurRadius#, quality%, red%, green%, blue%, alphaTexture%)
CustomPostprocessBlurZoom% (x#, y#, zoomFactor#, alpha#, blurPasses%, quality%, red%, green%, blue%, alphaTexture%)
CustomPostprocessBlurSpin% (x#, y#, spinAngle#, alpha#, blurPasses%, quality%, red%, green%, blue%, alphaTexture%)
CustomPostprocessBlurMotion% (alpha#, originX#, originY#, handleX#, handleY#, scaleX#, scaleY#, angle#, blend%, red%, green%, blue%, alphaTexture%)
CustomPostprocessOverlay% (alpha#, blend%, red%, green%, blue%, alphaTexture%)
RenderEntity% (entity%, camera%, clearViewport%, tween#)
RenderGroup% (group%, camera%, clearViewport%, tween#)
