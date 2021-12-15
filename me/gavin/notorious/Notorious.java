// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious;

import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.event.EventProcessor;
import me.gavin.notorious.util.font.CFontLoader;
import me.gavin.notorious.manager.ConfigManager;
import me.gavin.notorious.manager.NotificationManager;
import me.gavin.notorious.friend.Friends;
import me.gavin.notorious.util.TotemPopListener;
import me.gavin.notorious.manager.RotationManager;
import me.gavin.notorious.manager.MessageManager;
import me.gavin.notorious.manager.CommandManager;
import me.gavin.notorious.util.font.CFontRenderer;
import me.gavin.notorious.gui.ClickGuiScreen;
import me.gavin.notorious.manager.HackManager;

public class Notorious
{
    public static Notorious INSTANCE;
    public final HackManager hackManager;
    public final ClickGuiScreen clickGuiScreen;
    public final CFontRenderer fontRenderer;
    public final CommandManager commandManager;
    public final MessageManager messageManager;
    public final RotationManager rotationManager;
    public final TotemPopListener popListener;
    public final Friends friend;
    public final NotificationManager notificationManager;
    public final ConfigManager configManager;
    
    public Notorious() {
        Notorious.INSTANCE = this;
        this.hackManager = new HackManager();
        this.fontRenderer = new CFontRenderer(CFontLoader.HELVETICA, true, true);
        this.clickGuiScreen = new ClickGuiScreen();
        this.commandManager = new CommandManager();
        this.messageManager = new MessageManager();
        this.rotationManager = new RotationManager();
        this.popListener = new TotemPopListener();
        this.friend = new Friends();
        this.notificationManager = new NotificationManager();
        this.configManager = new ConfigManager();
        new EventProcessor();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.configManager.load();
        this.configManager.attach();
    }
}
