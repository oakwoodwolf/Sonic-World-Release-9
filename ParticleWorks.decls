.lib "ParticleWorks.dll"
PW_CreateEmitter()
PW_SetEmitter(emitterHandle, Life#, ParticleLife, LifeSpread, Flow#, SpreadX#, SpreadY#)
PW_SetParticle(emitterHandle, objPart, typPart, partRad#, objToFace, TiedToPivot)
PW_SetTexture(emitterHandle, texPart, Frames, CyclesPerFrame, EntFX)
PW_SetVelocity(emitterHandle, Vel#, VelSpread#, Acc#, Grav#)
PW_SetAngle(emitterHandle, IAX#, IAY#, IAZ#, AngleSpread#)
PW_SetRotation(emitterHandle, RotX#, RotY#, RotZ#, RotSpread#)
PW_SetAlpha(emitterHandle, Alpha#, AlphaSpread#, FadeStart)
PW_SetScale(emitterHandle, ScX#, ScY#, ScZ#, ScSpread#, Growth#)
PW_CreateModifier(emitterHandle, MinRange#, MaxRange#, MinForce#, MaxForce#, AffectAngle)
PW_DuplicateModifier(modifierHandle, newEmitterHandle, ParentIt)
PW_ModifyModifier(modifierHandle, emitterHandle, MinRange#, MaxRange#, MinForce#, MaxForce#, AffectAngle)
PW_EmitterExists(emitterHandle)
PW_ModifierExists(modifierHandle)
PW_KillParticles(emitterHandle)
PW_KillEmitter(emitterHandle)
PW_KillModifier(modifierHandle)
PW_UpdateEmitters()
PW_UpdateParticles()
