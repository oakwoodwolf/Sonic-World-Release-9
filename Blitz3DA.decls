.lib "Blitz3DA.dll"

PeekMemInt%(address%):"_PeekMemInt@4"
PeekMemShort%(address%):"_PeekMemShort@4"
PeekMemByte%(address%):"_PeekMemByte@4"
PeekMemFloat#(address%):"_PeekMemFloat@4"

PokeMemInt(address%,val%):"_PokeMemInt@8"
PokeMemShort(address%,val%):"_PokeMemShort@8"
PokeMemByte(address%,val%):"_PokeMemByte@8"
PokeMemFloat(address%,val#):"_PokeMemFloat@8"

GetCameraZoom#(camera%):"_GetCamZoom@4"
GetCamViewportX%(camera%):"_GetCamViewportX@4"
GetCameraViewportY%(camera%):"_GetCamViewportY@4"
GetCameraViewportWidth%(camera%):"_GetCamViewportW@4"
GetCameraViewportHeight%(camera%):"_GetCamViewportH@4"
GetCamClsRed%(camera%):"_GetCamClsRed@4"
GetCamClsGreen%(camera%):"_GetCamClsGreen@4"
GetCamClsBlue%(camera%):"_GetCamClsBlue@4"
GetCamClsBuffer%(camera%):"_GetCamClsBuffer@4"
GetCamProjMode%(camera%):"_GetCamProjMode@4"
GetCamFogRed%(camera%):"_GetFogClsRed@4"
GetCamFogGreen%(camera%):"_GetFogClsGreen@4"
GetCamFogBlue%(camera%):"_GetFogClsBlue@4"
GetFogRangeStart#(camera%):"_GetFogRangeStart@4"
GetFogRangeEnd#(camera%):"_GetFogRangeEnd@4"
GetCamRangeStart#(camera%):"_GetCamRangeStart@4"
GetCamRangeEnd#(camera%):"_GetCamRangeEnd@4"
GetCamFogMode%(camera%):"_GetCamFogMode@4"
CameraRangeFar#(camera):"_CameraRangeFar@4"

SetCamFOV(camera%,FOV#):"_SetCamFOV@8"
GetCamFOV#(camera%):"_GetCamFOV@4"
GetCamFOVH#(camera%):"_GetCamFOVH@4"
GetCamFOVV#(camera%):"_GetCamFOVV@4"

; Buffer Access
GetTextureFlags%(texture%):"_GetTextureFlags@4"
GetTextureBlend%(texture%):"_GetTextureBlend@4"
GetTextureRotation#(texture%):"_GetTextureRotation@4"
GetTexturePositionX#(texture%):"_GetTexturePositionX@4"
GetTexturePositionY#(texture%):"_GetTexturePositionY@4"
GetTextureCoords%(texture):"_GetTextureCoords@4"
GetTextureScaleU#(texture):"_GetTextureScaleU@4"
GetTextureScaleV#(texture):"_GetTextureScaleV@4"
;GetTexturePositionU#(texture)
;GetTexturePositionV#(texture)
GetTextureAngle#(texture):"_GetTextureAngle@4"
SetTextureFlags%(textureBuffer%,flags%):"_SetTextureFlags@8"

LockedPitch%(buffer%):"_LockedPitch@4"
LockedWidth%(buffer%):"_LockedWidth@4"
LockedHeight%(buffer%):"_LockedHeight@4"
LockedDepth%(buffer%):"_LockedDepth@4"
LockedFormat%(buffer%):"_LockedFormat@4"
GetBufferRed%(buffer%,x%,y%):"_GetBufferRed@12"

; Entity
EntityOriginX#(entity):"_EntityOriginX@4"
EntityOriginY#(entity):"_EntityOriginY@4"
EntityOriginZ#(entity):"_EntityOriginZ@4"
AlignEntity(entity1,entity2):"_AlignEntity@4"
EntityExists%(entity):"_EntityExists@4"
NextEntity%(entity%):"_NextEntity@4"
LastEntity%(entity%):"_LastEntity@4"
EntityHidden%(entity%):"_EntityHidden@4"
EntityScaleX#(entity%):"_EntityScaleX@4"
EntityScaleY#(entity%):"_EntityScaleY@4"
EntityScaleZ#(entity%):"_EntityScaleZ@4"
GetEntityFX%(entity%):"_GetEntityFX@4"
GetEntityOrder%(entity%):"_GetEntityOrder@4"
GetEntityRadiusX#(entity%):"_GetEntityRadiusX@4"
GetEntityRadiusY#(entity%):"_GetEntityRadiusY@4"
GetEntityBoxX#(entity%):"_GetEntityBoxX@4"
GetEntityBoxY#(entity%):"_GetEntityBoxY@4"
GetEntityBoxZ#(entity%):"_GetEntityBoxZ@4"
GetEntityBoxWidth#(entity%):"_GetEntityBoxWidth@4"
GetEntityBoxHeight#(entity%):"_GetEntityBoxHeight@4"
GetEntityBoxDepth#(entity%):"_GetEntityBoxDepth@4"
EntityMoved%(entity):"_EntityMoved@4"

; Brush
GetBrushBlend%(brush%):"_GetBrushBlend@4"
GetBrushHighTexLayer%(brush%):"_GetBrushHighTexLayer@4"
GetBrushRed%(brush%):"_GetBrushRed@4"
GetBrushGreen%(brush%):"_GetBrushGreen@4"
GetBrushBlue%(brush%):"_GetBrushBlue@4"
GetBrushShininess#(brush%):"_GetBrushShininess@4"
GetBrushAlpha#(brush%):"_GetBrushAlpha@4"
GetBrushFX%(brush%):"_GetBrushFX@4"

MemCopy%(src,dest,size):"_MemCopy@12"

;Graphics
BufferWidth%(buffer%):"_BufferWidth@4"
BufferHeight%(buffer%):"_BufferHeight@4"
BufferDepth%(buffer%):"_BufferDepth@4" 


;www.bettiesart.com/tc/blitz/lib.html