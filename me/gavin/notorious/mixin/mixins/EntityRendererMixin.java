// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.renderer.GlStateManager;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.render.AntiFog;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityRenderer.class })
public class EntityRendererMixin
{
    @Inject(method = { "setupFog" }, at = { @At("RETURN") })
    public void setupFogInject(final int startCoords, final float partialTicks, final CallbackInfo ci) {
        if (Notorious.INSTANCE.hackManager.getHack(AntiFog.class).isEnabled()) {
            GlStateManager.func_179106_n();
        }
    }
}
