// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.event.events.PlayerModelRotationEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelPlayer;
import org.spongepowered.asm.mixin.Mixin;
import me.gavin.notorious.stuff.IMinecraft;

@Mixin({ ModelPlayer.class })
public class ModelPlayerMixin implements IMinecraft
{
    @Inject(method = { "setRotationAngles" }, at = { @At("INVOKE") })
    public void setRotationAnglesInject(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn, final CallbackInfo ci) {
        if (entityIn == ModelPlayerMixin.mc.field_71439_g) {
            final PlayerModelRotationEvent event = new PlayerModelRotationEvent(netHeadYaw, headPitch);
            MinecraftForge.EVENT_BUS.post((Event)event);
            ((ModelPlayer)this).field_78116_c.field_78795_f = event.getPitch() * 0.017453292f;
            ((ModelPlayer)this).field_78116_c.field_78796_g = event.getYaw() * 0.017453292f;
            ((ModelPlayer)this).field_178720_f.field_78795_f = event.getYaw() * 0.017453292f;
            ((ModelPlayer)this).field_178720_f.field_78796_g = event.getYaw() * 0.017453292f;
        }
    }
}
