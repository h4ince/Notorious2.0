// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.TotemPopEvent;
import me.gavin.notorious.notifications.NotificationType;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.Notification;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "PopCounter", description = "Counts totem pops", category = Category.Chat)
public class PopCounter extends Hack
{
    public void onDeath(final String name, final int pops, final int entId) {
        final String s = (pops == 1) ? "" : "s";
        this.notorious.messageManager.sendRemovableMessage(ChatFormatting.RED + name + ChatFormatting.RESET + " has died after popping " + ChatFormatting.GREEN + pops + ChatFormatting.RESET + " totem" + s, entId);
        if (Notorious.INSTANCE.hackManager.getHack(Notification.class).isEnabled()) {
            this.notorious.notificationManager.show(new me.gavin.notorious.notifications.Notification("Totem Pop", String.format("%s has died after popping %s...", name, pops), NotificationType.INFO));
        }
    }
    
    @SubscribeEvent
    public void onPop(final TotemPopEvent event) {
        if (event.getPopCount() == 1) {
            this.notorious.messageManager.sendRemovableMessage(ChatFormatting.RED + event.getName() + ChatFormatting.RESET + " has popped a totem", event.getEntityId());
            if (Notorious.INSTANCE.hackManager.getHack(Notification.class).isEnabled()) {
                this.notorious.notificationManager.show(new me.gavin.notorious.notifications.Notification("Totem Pop", String.format("%s has popped a totem...", event.getName(), event.getPopCount()), NotificationType.INFO));
            }
        }
        else {
            this.notorious.messageManager.sendRemovableMessage(ChatFormatting.RED + event.getName() + ChatFormatting.RESET + " has popped " + ChatFormatting.GREEN + event.getPopCount() + ChatFormatting.RESET + " totems", event.getEntityId());
            if (Notorious.INSTANCE.hackManager.getHack(Notification.class).isEnabled()) {
                this.notorious.notificationManager.show(new me.gavin.notorious.notifications.Notification("Totem Pop", String.format("%s has popped %s totems...", event.getName(), event.getPopCount()), NotificationType.INFO));
            }
        }
    }
}
