// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.util.TickTimer;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoDox", description = "ez", category = Category.Chat)
public class AutoDox extends Hack
{
    @RegisterSetting
    public final ModeSetting player;
    @RegisterSetting
    public final NumSetting delay;
    public TickTimer timer;
    
    public AutoDox() {
        this.player = new ModeSetting("Player", "Sjnez", new String[] { "Sjnez", "5nt", "waspgod", "FitMC", "SalC1", "ThousandStar" });
        this.delay = new NumSetting("Delay", 10.0f, 1.0f, 15.0f, 1.0f);
        this.timer = new TickTimer();
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.player.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event) {
        if (AutoDox.mc.field_71439_g == null || AutoDox.mc.field_71441_e == null) {
            return;
        }
        if (this.timer.hasTicksPassed((long)(this.delay.getValue() * 20.0f))) {
            String dox = "Name: Address: Email: Phone: ";
            final String mode = this.player.getMode();
            switch (mode) {
                case "Sjnez": {
                    dox = "Name: Samuel J Nezzbit Address: 1820 261 St, Los Angelos, California Email: samuelnez@gmail.com Phone: (310)-538-8734 (310)-371-4746";
                    break;
                }
                case "5nt": {
                    dox = "Name: Jake Findlay Piggot Address: 179 Ikwikws RdWest Vancouver Email: jakeyisad@gmail.com";
                    break;
                }
                case "waspgod": {
                    dox = "Name: Wallace Arney Address: 152 Hazel Ln Piedmont, CA 94611 MomsEmail: lucy@lucyridgway.com Phone: (510) 735-9913 (510) 482-1580 (601) 923-1474";
                    break;
                }
                case "FitMC": {
                    dox = "Name: Peter Michael Larsen Address: 190 N Collier Blvd Apt M7 Marco Island FL 34145-3213 Email: SonOfShoop@gmail.com";
                    break;
                }
                case "SalC1": {
                    dox = "Name: Salvatore V Cracchiolo Address: 42 Waverly Ln Grosse Pt Frm Michigan 48236 Phone: 313-881-2977 313-881-2132";
                    break;
                }
                case "ThousandStar": {
                    dox = "Name: Samuel Breedlove Address: 4204 50th Ave NE Seattle, WA 98105E mail: samgamingmmoments@gmail.com";
                    break;
                }
            }
            AutoDox.mc.field_71439_g.func_71165_d(dox);
            this.timer.reset();
        }
    }
}
