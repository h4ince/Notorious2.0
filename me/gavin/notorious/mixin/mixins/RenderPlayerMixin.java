// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.gavin.notorious.hack.hacks.render.Nametags;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.render.PopESP;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderPlayer.class })
public class RenderPlayerMixin
{
    @Inject(method = { "renderEntityName" }, at = { @At("HEAD") }, cancellable = true)
    public void renderEntityNameHook(final AbstractClientPlayer entityIn, final double x, final double y, final double z, final String name, final double distanceSq, final CallbackInfo info) {
        if (Notorious.INSTANCE.hackManager.getHack(PopESP.class).isEnabled() || Notorious.INSTANCE.hackManager.getHack(Nametags.class).isEnabled()) {
            info.cancel();
        }
    }
}
