.lib "DirectX7A.dll"
DX7_SetSystemProperties%(d3d,dev7,draw7,hwnd,instance):"_SetSystemProperties@20"
DX7_RemoveSystemProperties%():"_RemoveSystemProperties@0"

DX7_SetMipmapLODBias%(bias#,index):"_SetMipmapLODBias@8"
DX7_GetMipmapLODBias#(index):"_GetMipmapLODBias@4"

;Clip plane is simple Type with 4 float fields, a#,b#,c#,d# to store plane equation
DX7_GetClipPlane%(index,ClipPlane*):"_GetClipPlane@8"
DX7_SetClipPlane%(index,ClipPlane*):"_SetClipPlane@8"

DX7_DeviceClear%(flags, ColorRGBA, ZVal#, StencilVal%):"_DeviceClear@16"

DX7_GetTransform%(tfState,D3DMATRIX*):"_GetTransform@8"
DX7_SetTransform%(tfState,D3DMATRIX*):"_SetTransform@8"

; Render States
DX7_SetRenderState%(renderstate,param):"_SetRenderState@8"
DX7_SetRenderStateF%(renderstate,val#):"_SetRenderStateF@8"
DX7_GetRenderState%(renderstate):"_GetRenderState@4"
DX7_GetRenderStateF#(renderstate):"_GetRenderStateF@4"

DX7_GetTextureStageState%(texLayer,state,index):"_GetTextureStageState@8"
DX7_SetTextureStageState%(texLayer,state,value):"_SetTextureStageState@12"

DX7_SetTexStageTransFlags%(texStage%, flags%):"_SetTexStageTransFlags@8"
DX7_SetTexStageCoordIndex%(texStage%, flags%):"_SetTexStageCoordIndex@8"

; For stage states that require Floats
DX7_GetTextureStageStateF#(stage,index):"_GetTextureStageStateF@8"
DX7_SetTextureStageStateF%(stage,index,value#):"_SetTextureStageStateF@12"

DX7_SetFillMode%(mode%):"_SetFillMode@4"
DX7_SetShadeMode%(mode%):"_SetShadeMode@4"
DX7_SetCullMode%(mode%):"_SetCullMode@4"


;Stencil Buffer
DX7_GetStencilBitDepth%():"_GetStencilBitDepth@0"
DX7_CreateStencilBuffer%():"_CreateStencilBuffer@0"

DX7_EnableStencil%():"_EnableStencil@0"
DX7_DisableStencil%():"_DisableStencil@0"

DX7_SetStencilFail%(stencilOP%):"_SetStencilFail@4"
DX7_SetStencilZFail%(stencilOP%):"_SetStencilZFail@4"
DX7_SetStencilPass%(stencilOP%):"_SetStencilPass@4"
DX7_SetStencilFunc%(cmpFunc%):"_SetStencilFunc@4"
DX7_SetStencilRef%(refVal%):"_SetStencilRef@4"
DX7_SetStencilMask%(mask%):"_SetStencilMask@4"
DX7_SetStencilWriteMask%(mask%):"_SetStencilWriteMask@4"
DX7_SetZFunc%(cmpFunc%):"_SetZFunc@4"


;possibly overidden by renderworld()
DX7_EnableAlphaBlending%():"_EnableAlphaBlending@0"
DX7_DisableAlphaBlending%():"_DisableAlphaBlending@0"

DX7_SetSrcBlendMode%(blendMode%):"_SetSrcBlendMode@4"
DX7_SetDestBlendMode%(blendMode%):"_SetDestBlendMode@4"
DX7_GetSrcBlendMode%():"_GetSrcBlendMode@0"
DX7_GetDestBlendMode%():"_GetDestBlendMode@0"

DX7_GetAlphaBlendingState%():"_GetAlphaBlendingState@0"

DX7_EnableAlphaTesting%():"_EnableAlphaTesting@0"
DX7_DisableAlphaTesting%():"_DisableAlphaTesting@0"
DX7_SetAlphaTestRef%(refVal#):"_SetAlphaTestRef@4"
DX7_SetAlphaTestFunc%(function%):"_SetAlphaTestFunc@4"

DX7_EnableFog():"_EnableFog@0"
DX7_EnableRangeFog%():"_EnableRangeFog@0"
DX7_DisableRangeFog%():"_DisableRangeFog@0"
DX7_DisableFog():"_DisableFog@0"
DX7_SetFogDensity%(density#):"_SetFogDensity@4"
DX7_SetFogMode%(mode%):"_SetFogMode@4"
DX7_SetFogVertexMode%(mode%):"_SetFogVertexMode@4"
DX7_SetFogStart%(start#):"_SetFogStart@4"
DX7_SetFogEnd%(start#):"_SetFogEnd@4"


; Device Capabilities
;----------------------------------------
DX7_GetDeviceGUID$():"_GetDeviceGUID@0"
DX7_SupportsAllStencilOPs%():"_SupportsAllStencilOPs@0"
DX7_SupportsRangeFog%():"_SupportsRangeFog@0"
DX7_SupportsMipMapLODBias%():"_SupportsMipMapLODBias@0"
DX7_SupportsAllZCmpCaps%():"_SupportsAllZCmpCaps@0"
DX7_SupportsCullCCW%():"_SupportsCullCCW@0"
DX7_SupportsAntiAliasing%():"_SupportsAntiAliasing@0"
DX7_SupportsDXT1%():"_SupportsDXT1@0"
DX7_SupportsDXT3%():"_SupportsDXT3@0"
DX7_SupportsDXT5%():"_SupportsDXT5@0"
DX7_SupportsRenderTexture%():"_SupportsRenderTexture@0"
DX7_SupportsBumpMapping%():"_SupportsBumpMapping@0"
DX7_SupportsLumiBumpMapping%():"_SupportsLumiBumpMapping@0"


;GetRenderTarget%():"_GetRenderTarget@0"
DX7_SetRenderTarget%(bbRenderTexture):"_SetRenderTarget@4"
DX7_RestoreRenderTarget%():"_RestoreRenderTarget@0"
DX7_GetRenderTarget%():"_GetRenderTarget@0"

;Render Texture
DX7_UserRenderTexture():"_UserRenderTexture@0"
DX7_AddZBuffer%(RTex):"_AddZBuffer@4"
DX7_AddCubemapZBuffers%(cubemap):"_AddCubemapZBuffers@4"

DX7_UserBumpTexture():"_UserBumpTexture@0"
DX7_EnableRenderBumpTexture():"_EnableRenderBumpTexture@0"
DX7_DisableRenderBumpTexture():"_DisableRenderBumpTexture@0"
DX7_SetBumpInfo(m00#,m10#,m01#,m11#,lumScale#,lumOffset#,bumpMode%):"_SetBumpInfo@28"

DX7_CopySurface%(srcX,srcY,w,h,destX,destY,srcTex,destTex):"_CopySurface@32"
DX7_CopySurfaceFX%(srcX,srcY,w,h,destX,destY,srcTex,destTex,effects,rasterOPs):"_CopySurfaceFX@40"
DX7_CopySurfaceDXtoBB%(srcX,srcY,w,h,destX,destY,srcTex,destTex):"_CopySurfaceDXtoBB@32"
DX7_CopySurfaceBBtoDX%(srcX,srcY,w,h,destX,destY,srcTex,destTex):"_CopySurfaceBBtoDX@32"

DX7_LockTransform():"_LockTransform@0"
DX7_UnLockTransform():"_UnLockTransform@0"

DX7_LockAlphaBlending():"_LockAlphaBlending@0"
DX7_UnlockAlphaBlending():"_UnlockAlphaBlending@0"

DX7_LockZBuffer():"_LockZBuffer@0"
DX7_UnlockZBuffer():"_UnlockZBuffer@0"

DX7_LockZWrites():"_LockZWrites@0"
DX7_LockBlend():"_LockBlend@0"
DX7_UnlockZWrites():"_UnlockZWrites@0"
DX7_UnlockBlend():"_UnlockBlend@0"

DX7_EnableZBuffer%():"_EnableZBuffer@0"
DX7_DisableZBuffer%():"_DisableZBuffer@0"
DX7_GetZBuffer%():"_GetZBuffer@0"

DX7_EnableZWrites%():"_EnableZWrites@0"
DX7_DisableZWrites%():"_DisableZWrites@0"
DX7_GetZWrites%():"_GetZWrites@0"



DX7_SetCubemapRenderTarget%(tex,face):"_SetCubemapRenderTarget@8"

DX7_GetIDirectDrawSurface%(tex,frame):"_GetIDirectDrawSurface@8"

DX7_ClearTexture%(tex,color):"_ClearTexture@8"