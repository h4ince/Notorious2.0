// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.InputUpdateEvent;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "TimerSpeed", description = "finally, its garbage as allways :/", category = Category.Movement)
public class TimerSpeed extends Hack
{
    @SubscribeEvent
    public void onUpdate(final InputUpdateEvent event) {
        if (TimerSpeed.mc.field_71439_g.field_191988_bg != 1.0f || TimerSpeed.mc.field_71439_g.field_70702_br != 1.0f) {}
        if (TimerSpeed.mc.field_71439_g.field_70122_E) {
            TimerSpeed.mc.field_71439_g.func_70664_aZ();
        }
        else {
            final EntityPlayerSP field_71439_g = TimerSpeed.mc.field_71439_g;
            field_71439_g.field_70159_w *= 1.0;
            final EntityPlayerSP field_71439_g2 = TimerSpeed.mc.field_71439_g;
            field_71439_g2.field_70179_y *= 1.0;
            if (TimerSpeed.mc.field_71439_g.field_70143_R > 0.4) {
                final EntityPlayerSP field_71439_g3 = TimerSpeed.mc.field_71439_g;
                field_71439_g3.field_70181_x -= 62.0;
            }
        }
    }
}
