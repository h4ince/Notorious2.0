// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.notifications.NotificationType;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Notifications", description = "ez", category = Category.Client)
public class Notification extends Hack
{
    @RegisterSetting
    public final ModeSetting style;
    @RegisterSetting
    public final BooleanSetting moduleToggle;
    
    public Notification() {
        this.style = new ModeSetting("Style", "Skeet", new String[] { "Skeet", "Basic" });
        this.moduleToggle = new BooleanSetting("ModuleToggle", true);
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        for (final Hack hack : this.notorious.hackManager.getHacks()) {
            if (this.moduleToggle.isEnabled()) {
                if (!hack.isEnabled() && System.currentTimeMillis() - hack.lastDisabledTime < 250L) {
                    this.notorious.notificationManager.show(new me.gavin.notorious.notifications.Notification("ToggleMessage", ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + " has been " + ChatFormatting.RED + "DISABLED!", NotificationType.INFO));
                }
                else {
                    if (!hack.isEnabled() || System.currentTimeMillis() - hack.lastEnabledTime >= 250L) {
                        continue;
                    }
                    this.notorious.notificationManager.show(new me.gavin.notorious.notifications.Notification("ToggleMessage", ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + " has been " + ChatFormatting.GREEN + "ENABLED!", NotificationType.INFO));
                }
            }
        }
    }
}
