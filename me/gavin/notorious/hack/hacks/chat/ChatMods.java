// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Date;
import java.text.SimpleDateFormat;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ChatMods", description = "Modifies chat.", category = Category.Chat)
public class ChatMods extends Hack
{
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ModeSetting chatColor;
    @RegisterSetting
    public final BooleanSetting chatSuffix;
    @RegisterSetting
    public final BooleanSetting chatTimestamps;
    @RegisterSetting
    public final BooleanSetting clearChat;
    @RegisterSetting
    public final BooleanSetting colorChat;
    public String suffix;
    public String color;
    
    public ChatMods() {
        this.mode = new ModeSetting("SuffixMode", "Unicode", new String[] { "Unicode", "Vanilla", "UnicodeVer", "Custom" });
        this.chatColor = new ModeSetting("ChatColor", "Green", new String[] { "Green", "Red", "Cyan" });
        this.chatSuffix = new BooleanSetting("ChatSuffix", true);
        this.chatTimestamps = new BooleanSetting("ChatTimestamps", true);
        this.clearChat = new BooleanSetting("ClearChat", true);
        this.colorChat = new BooleanSetting("ColorChat", false);
        this.suffix = "";
        this.color = "";
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        final String originalMessage = event.getOriginalMessage();
        if (this.chatSuffix.isEnabled()) {
            if (this.mode.getMode().equals("Vanilla")) {
                this.suffix = " | Notorious";
            }
            else if (this.mode.getMode().equals("Unicode")) {
                this.suffix = " \u23d0 \u0274\u1d0f\u1d1b\u1d0f\u0280\u026a\u1d0f\u1d1c\ua731";
            }
            else if (this.mode.getMode().equals("UnicodeVersion")) {
                this.suffix = " \u23d0 \u0274\u1d0f\u1d1b\u1d0f\u0280\u026a\u1d0f\u1d1c\ua731\u1d0f 2.0.0";
            }
            if (event.getMessage().startsWith("!")) {
                return;
            }
            if (event.getMessage().startsWith(".")) {
                return;
            }
            if (event.getMessage().startsWith("/")) {
                return;
            }
            event.setMessage(originalMessage + this.suffix);
        }
        if (this.colorChat.isEnabled()) {
            if (this.chatColor.getMode().equals("Green")) {
                this.color = ">";
            }
            else if (this.chatColor.getMode().equals("Red")) {
                this.color = "@";
            }
            else if (this.chatColor.getMode().equals("Cyan")) {
                this.color = "^";
            }
            if (event.getMessage().startsWith("!")) {
                return;
            }
            if (event.getMessage().startsWith(".")) {
                return;
            }
            if (event.getMessage().startsWith("/")) {
                return;
            }
            event.setMessage(this.color + originalMessage);
        }
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (this.chatTimestamps.isEnabled()) {
            final String time = new SimpleDateFormat("k:mm").format(new Date());
            final TextComponentString text = new TextComponentString(ChatFormatting.GRAY + "<" + time + "> " + ChatFormatting.RESET);
            event.setMessage(text.func_150257_a(event.getMessage()));
        }
    }
}
