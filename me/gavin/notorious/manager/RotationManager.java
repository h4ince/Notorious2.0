// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.manager;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.mixin.mixins.accessor.ICPacketPlayerMixin;
import net.minecraft.network.play.client.CPacketPlayer;
import me.gavin.notorious.event.events.PacketEvent;
import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.stuff.IMinecraft;

public class RotationManager implements IMinecraft
{
    public boolean shouldRotate;
    public float desiredPitch;
    public float desiredYaw;
    
    public RotationManager() {
        this.shouldRotate = false;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Send event) {
        if (!this.shouldRotate) {
            return;
        }
        if (event.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer packet = (CPacketPlayer)event.getPacket();
            final ICPacketPlayerMixin accessor = (ICPacketPlayerMixin)packet;
            accessor.setYawAccessor(this.desiredYaw);
            accessor.setPitchAccessor(this.desiredPitch);
        }
    }
    
    public void rotateToEntity(final Entity entity) {
        final float[] angle = MathUtil.calcAngle(RotationManager.mc.field_71439_g.func_174824_e(RotationManager.mc.func_184121_ak()), entity.func_174791_d());
        this.shouldRotate = true;
        this.desiredYaw = angle[0];
        this.desiredPitch = angle[1];
    }
    
    public void rotateToPosition(final BlockPos position) {
        final float[] angle = MathUtil.calcAngle(RotationManager.mc.field_71439_g.func_174824_e(RotationManager.mc.func_184121_ak()), new Vec3d((double)(position.func_177958_n() + 0.5f), (double)(position.func_177956_o() - 0.5f), (double)position.func_177952_p()));
        this.shouldRotate = true;
        this.desiredYaw = angle[0];
        this.desiredPitch = angle[1];
    }
}
