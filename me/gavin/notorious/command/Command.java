// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.command;

import me.gavin.notorious.Notorious;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;

public abstract class Command
{
    protected static final Minecraft mc;
    private final String name;
    private final String description;
    private final String syntax;
    private final List<String> aliases;
    
    public Command() {
        final RegisterCommand annotation = this.getClass().getAnnotation(RegisterCommand.class);
        this.name = annotation.name();
        this.description = annotation.description();
        this.syntax = annotation.syntax();
        this.aliases = Arrays.asList(annotation.aliases());
    }
    
    public abstract void onCommand(final String[] p0, final String p1);
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getSyntax() {
        return this.syntax;
    }
    
    public void sendSyntax() {
        Notorious.INSTANCE.messageManager.sendMessage(this.getSyntax());
    }
    
    public List<String> getAliases() {
        return this.aliases;
    }
    
    static {
        mc = Minecraft.func_71410_x();
    }
}
