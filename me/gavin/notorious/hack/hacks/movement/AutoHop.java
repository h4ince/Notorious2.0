// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoHop", description = "ching chong autohop", category = Category.Movement)
public class AutoHop extends Hack
{
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (AutoHop.mc.field_71439_g.field_70122_E && !AutoHop.mc.field_71439_g.func_70093_af() && !AutoHop.mc.field_71439_g.func_180799_ab() && !AutoHop.mc.field_71439_g.func_70090_H() && !AutoHop.mc.field_71439_g.func_70617_f_() && !AutoHop.mc.field_71439_g.field_70145_X && !AutoHop.mc.field_71474_y.field_74311_E.func_151470_d() && !AutoHop.mc.field_71474_y.field_74314_A.func_151470_d()) {
            AutoHop.mc.field_71439_g.func_70664_aZ();
        }
    }
}
