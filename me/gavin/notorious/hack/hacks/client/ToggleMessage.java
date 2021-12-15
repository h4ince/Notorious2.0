// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ToggleMessage", description = "ez", category = Category.Client)
public class ToggleMessage extends Hack
{
    @RegisterSetting
    public final ModeSetting messageMode;
    
    public ToggleMessage() {
        this.messageMode = new ModeSetting("MessageMode", "Removable", new String[] { "Removable", "Normal" });
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        for (final Hack hack : this.notorious.hackManager.getHacks()) {
            if (!hack.isEnabled() && System.currentTimeMillis() - hack.lastDisabledTime < 50L) {
                if (this.messageMode.getMode().equals("Removable")) {
                    this.notorious.messageManager.sendRemovableMessage(ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + ChatFormatting.RED + ChatFormatting.BOLD + " DISABLED!", 1);
                }
                else {
                    this.notorious.messageManager.sendMessage(ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + ChatFormatting.RED + ChatFormatting.BOLD + " DISABLED!");
                }
            }
            else {
                if (!hack.isEnabled() || System.currentTimeMillis() - hack.lastEnabledTime >= 50L) {
                    continue;
                }
                if (this.messageMode.getMode().equals("Removable")) {
                    this.notorious.messageManager.sendRemovableMessage(ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + ChatFormatting.GREEN + ChatFormatting.BOLD + " ENABLED!", 1);
                }
                else {
                    this.notorious.messageManager.sendMessage(ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + ChatFormatting.GREEN + ChatFormatting.BOLD + " ENABLED!");
                }
            }
        }
    }
}
