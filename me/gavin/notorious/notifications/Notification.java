// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.notifications;

import net.minecraft.client.gui.Gui;
import java.awt.Color;
import me.gavin.notorious.Notorious;
import net.minecraft.client.gui.ScaledResolution;
import me.gavin.notorious.stuff.IMinecraft;

public class Notification implements IMinecraft
{
    public String title;
    public String message;
    public NotificationType type;
    public long start;
    
    public Notification(final String title, final String message, final NotificationType type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }
    
    public void show() {
        this.start = System.currentTimeMillis();
    }
    
    public void draw() {
        final int width = Notification.mc.field_71466_p.func_78256_a(this.message) + 2;
        final ScaledResolution sr = new ScaledResolution(Notification.mc);
        if (Notorious.INSTANCE.hackManager.getHack(me.gavin.notorious.hack.hacks.client.Notification.class).style.getMode().equals("Skeet")) {
            Gui.func_73734_a(sr.func_78326_a() - width, sr.func_78328_b() - 40, sr.func_78326_a(), sr.func_78328_b() - 5, new Color(0, 0, 0, 255).getRGB());
            Gui.func_73734_a(sr.func_78326_a() - width, sr.func_78328_b() - 40, sr.func_78326_a() - width + 5, sr.func_78328_b() - 5, this.type.color.getRGB());
        }
        else {
            Gui.func_73734_a(sr.func_78326_a() - width, sr.func_78328_b() - 20, sr.func_78326_a(), sr.func_78328_b() - 5, 2130706432);
        }
        if (Notorious.INSTANCE.hackManager.getHack(me.gavin.notorious.hack.hacks.client.Notification.class).style.getMode().equals("Skeet")) {
            Notification.mc.field_71466_p.func_78276_b(this.title, sr.func_78326_a() - width + 8, sr.func_78328_b() - 2 - 35, -1);
            Notification.mc.field_71466_p.func_78276_b(this.message, sr.func_78326_a() - width + 8, sr.func_78328_b() - 15, -1);
        }
        else {
            Notification.mc.field_71466_p.func_78276_b(this.message, sr.func_78326_a() - width + 4, sr.func_78328_b() - 15, -1);
        }
    }
    
    public long timeLeft() {
        return System.currentTimeMillis() - this.start;
    }
    
    public boolean expired() {
        return this.timeLeft() > 2000L;
    }
}
