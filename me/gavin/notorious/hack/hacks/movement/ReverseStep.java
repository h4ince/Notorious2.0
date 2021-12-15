// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ReverseStep", description = "ez", category = Category.Movement)
public class ReverseStep extends Hack
{
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (ReverseStep.mc.field_71439_g != null && ReverseStep.mc.field_71441_e != null && ReverseStep.mc.field_71439_g.field_70122_E && !ReverseStep.mc.field_71439_g.func_70093_af() && !ReverseStep.mc.field_71439_g.func_70090_H() && !ReverseStep.mc.field_71439_g.field_70128_L && !ReverseStep.mc.field_71439_g.func_180799_ab() && !ReverseStep.mc.field_71439_g.func_70617_f_() && !ReverseStep.mc.field_71439_g.field_70145_X && !ReverseStep.mc.field_71474_y.field_74311_E.func_151470_d() && !ReverseStep.mc.field_71474_y.field_74314_A.func_151470_d() && ReverseStep.mc.field_71439_g.field_70122_E) {
            final EntityPlayerSP field_71439_g = ReverseStep.mc.field_71439_g;
            --field_71439_g.field_70181_x;
        }
    }
}
