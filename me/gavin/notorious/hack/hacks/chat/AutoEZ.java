// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.TotemPopEvent;
import me.gavin.notorious.util.TickTimer;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoEZ", description = "ez", category = Category.Chat)
public class AutoEZ extends Hack
{
    @RegisterSetting
    public final NumSetting delay;
    public TickTimer timer;
    
    public AutoEZ() {
        this.delay = new NumSetting("Delay", 5.0f, 1.0f, 15.0f, 1.0f);
        this.timer = new TickTimer();
    }
    
    public void onDeath(final String name) {
        if (this.timer.hasTicksPassed((long)this.delay.getValue() * 20L)) {
            AutoEZ.mc.field_71439_g.func_71165_d("LOL EZ " + name + " stay dead nn!");
        }
    }
    
    @SubscribeEvent
    public void onPop(final TotemPopEvent event) {
        if (event.getPopCount() == 1) {
            if (this.timer.hasTicksPassed((long)this.delay.getValue() * 20L)) {
                AutoEZ.mc.field_71439_g.func_71165_d("OMG " + event.getName() + " your so bad stop popping!");
                this.timer.reset();
            }
        }
        else if (this.timer.hasTicksPassed((long)this.delay.getValue() * 20L)) {
            AutoEZ.mc.field_71439_g.func_71165_d("OMG " + event.getName() + " your so bad stop popping!");
            this.timer.reset();
        }
    }
}
