// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.init.MobEffects;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "PotionAlert", description = "Tells you in chat when you are hit by a arrow.", category = Category.Chat)
public class PotionAlert extends Hack
{
    @RegisterSetting
    public final BooleanSetting weakness;
    @RegisterSetting
    public final BooleanSetting slowness;
    private boolean hasAnnouncedWeakness;
    private boolean hasAnnouncedSlowness;
    
    public PotionAlert() {
        this.weakness = new BooleanSetting("Weakness", true);
        this.slowness = new BooleanSetting("Slowness", true);
        this.hasAnnouncedWeakness = false;
        this.hasAnnouncedSlowness = false;
    }
    
    @Override
    public String getMetaData() {
        String weakness;
        if (PotionAlert.mc.field_71439_g.func_70644_a(MobEffects.field_76437_t)) {
            weakness = ChatFormatting.GREEN + "Weakness";
        }
        else {
            weakness = ChatFormatting.GRAY + "Weakness";
        }
        String slowness;
        if (PotionAlert.mc.field_71439_g.func_70644_a(MobEffects.field_76421_d)) {
            slowness = ChatFormatting.GREEN + "Slowness";
        }
        else {
            slowness = ChatFormatting.GRAY + "Slowness";
        }
        return " [" + weakness + ChatFormatting.GRAY + ChatFormatting.RESET + " | " + ChatFormatting.GRAY + slowness + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (this.weakness.isEnabled()) {
            if (PotionAlert.mc.field_71439_g.func_70644_a(MobEffects.field_76437_t) && !this.hasAnnouncedWeakness) {
                this.notorious.messageManager.sendMessage("RIP bro you now have " + ChatFormatting.GRAY + ChatFormatting.BOLD + "WEAKNESS" + ChatFormatting.RESET + "!");
                this.hasAnnouncedWeakness = true;
            }
            if (!PotionAlert.mc.field_71439_g.func_70644_a(MobEffects.field_76437_t) && this.hasAnnouncedWeakness) {
                this.notorious.messageManager.sendMessage("Ey bro good job you don't have " + ChatFormatting.GRAY + ChatFormatting.BOLD + "WEAKNESS" + ChatFormatting.RESET + " anymore!");
                this.hasAnnouncedWeakness = false;
            }
        }
        if (this.slowness.isEnabled()) {
            if (PotionAlert.mc.field_71439_g.func_70644_a(MobEffects.field_76421_d) && !this.hasAnnouncedSlowness) {
                this.notorious.messageManager.sendMessage("RIP bro you now have " + ChatFormatting.AQUA + ChatFormatting.BOLD + "SLOWNESS" + ChatFormatting.RESET + "!");
                this.hasAnnouncedSlowness = true;
            }
            if (!PotionAlert.mc.field_71439_g.func_70644_a(MobEffects.field_76421_d) && this.hasAnnouncedSlowness) {
                this.notorious.messageManager.sendMessage("Ey bro good job you don't have " + ChatFormatting.AQUA + ChatFormatting.BOLD + "SLOWNESS" + ChatFormatting.RESET + " anymore!");
                this.hasAnnouncedSlowness = false;
            }
        }
    }
}
