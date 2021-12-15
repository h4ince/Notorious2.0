// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.manager;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.stuff.IMinecraft;

public class MessageManager implements IMinecraft
{
    public final String messagePrefix;
    public final String errorPrefix;
    
    public MessageManager() {
        this.messagePrefix = ChatFormatting.BLUE + "<" + "notorious" + "> " + ChatFormatting.RESET;
        this.errorPrefix = ChatFormatting.DARK_RED + "<" + "notorious" + "> " + ChatFormatting.RESET;
    }
    
    public void sendRawMessage(final String message) {
        if (MessageManager.mc.field_71439_g != null) {
            MessageManager.mc.field_71439_g.func_145747_a((ITextComponent)new TextComponentString(message));
        }
    }
    
    public void sendMessage(final String message) {
        this.sendRawMessage(this.messagePrefix + message);
    }
    
    public void sendError(final String message) {
        this.sendRawMessage(this.errorPrefix + message);
    }
    
    public void sendRemovableMessage(final String message, final int id) {
        MessageManager.mc.field_71456_v.func_146158_b().func_146234_a((ITextComponent)new TextComponentString(this.messagePrefix + message), id);
    }
}
