// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.util.TickTimer;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoChad", description = "ez", category = Category.Chat)
public class AutoChad extends Hack
{
    @RegisterSetting
    public final NumSetting delay;
    public TickTimer timer;
    
    public AutoChad() {
        this.delay = new NumSetting("Delay", 10.0f, 1.0f, 15.0f, 1.0f);
        this.timer = new TickTimer();
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.delay.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent event) {
        if (AutoChad.mc.field_71439_g == null || AutoChad.mc.field_71441_e == null) {
            return;
        }
        if (this.timer.hasTicksPassed((long)(this.delay.getValue() * 20.0f))) {
            AutoChad.mc.field_71439_g.func_71165_d("youtube.com/channel/UCRpxbpHD4IMS9fZmAirFaDg");
            this.timer.reset();
        }
    }
}
