// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.InputUpdateEvent;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Speed", description = "floor gang speed (beta) ", category = Category.Movement)
public class FloorGang extends Hack
{
    @RegisterSetting
    public final ModeSetting speedMode;
    
    public FloorGang() {
        this.speedMode = new ModeSetting("SpeedMode", "Bhop", new String[] { "Bhop", "Ground", "TP" });
    }
    
    @SubscribeEvent
    public void onUpdate(final InputUpdateEvent event) {
        if (this.speedMode.getMode().equals("Bhop")) {
            if ((FloorGang.mc.field_71439_g.field_191988_bg != 0.0f || FloorGang.mc.field_71439_g.field_70702_br != 0.0f) && !FloorGang.mc.field_71439_g.func_70093_af() && FloorGang.mc.field_71439_g.field_70122_E) {
                FloorGang.mc.field_71439_g.func_70664_aZ();
                final EntityPlayerSP field_71439_g = FloorGang.mc.field_71439_g;
                field_71439_g.field_70159_w *= 1.4;
                final EntityPlayerSP field_71439_g2 = FloorGang.mc.field_71439_g;
                field_71439_g2.field_70181_x *= 0.99;
                final EntityPlayerSP field_71439_g3 = FloorGang.mc.field_71439_g;
                field_71439_g3.field_70179_y *= 1.4;
            }
            if (this.speedMode.getMode().equals("Ground") && FloorGang.mc.field_71439_g.field_70122_E && FloorGang.mc.field_71474_y.field_74351_w.func_151470_d()) {
                final double speedValue = 0.21;
                final double yaw = Math.toRadians(FloorGang.mc.field_71439_g.field_70177_z);
                final double x = -Math.sin(yaw) * speedValue;
                final double z = Math.cos(yaw) * speedValue;
                FloorGang.mc.field_71439_g.field_70159_w = x;
                FloorGang.mc.field_71439_g.field_70179_y = z;
                FloorGang.mc.field_71439_g.func_70664_aZ();
            }
        }
    }
}
