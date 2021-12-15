// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.manager;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import java.util.ArrayDeque;
import java.util.Queue;
import me.gavin.notorious.notifications.Notification;

public class NotificationManager
{
    public Notification currentNotification;
    public Queue<Notification> notificationQueue;
    
    public NotificationManager() {
        this.notificationQueue = new ArrayDeque<Notification>();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void show(final Notification notification) {
        this.notificationQueue.add(notification);
    }
    
    public void render() {
        this.update();
        if (this.currentNotification != null) {
            this.currentNotification.draw();
        }
    }
    
    private void update() {
        if (this.currentNotification != null && this.currentNotification.expired()) {
            this.currentNotification = null;
        }
        if (this.currentNotification == null && !this.notificationQueue.isEmpty()) {
            (this.currentNotification = this.notificationQueue.poll()).show();
        }
        if (this.currentNotification != null && this.notificationQueue.size() > 1L) {
            this.notificationQueue.remove();
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(final TickEvent.RenderTickEvent event) {
        this.render();
    }
}
