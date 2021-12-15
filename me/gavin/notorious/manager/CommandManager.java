// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.manager;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.Notorious;
import java.util.Arrays;
import net.minecraftforge.client.event.ClientChatEvent;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.lang.annotation.Annotation;
import me.gavin.notorious.command.RegisterCommand;
import com.google.common.reflect.ClassPath;
import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.command.Command;
import java.util.ArrayList;
import me.gavin.notorious.stuff.IMinecraft;

public class CommandManager implements IMinecraft
{
    private String prefix;
    private final ArrayList<Command> commands;
    
    public CommandManager() {
        this.prefix = ".";
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.commands = new ArrayList<Command>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            final ClassPath path = ClassPath.from(loader);
            for (final ClassPath.ClassInfo info : path.getTopLevelClassesRecursive("dev.notorious.client.commands.impl")) {
                final Class<?> commandClass = (Class<?>)info.load();
                if (Command.class.isAssignableFrom(commandClass) && commandClass.isAnnotationPresent(RegisterCommand.class)) {
                    this.commands.add((Command)commandClass.newInstance());
                }
            }
        }
        catch (IOException | IllegalAccessException | InstantiationException ex2) {
            final Exception ex;
            final Exception exception = ex;
            exception.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onChatSent(final ClientChatEvent event) {
        String message = event.getMessage();
        if (message.startsWith(this.getPrefix())) {
            event.setCanceled(true);
            message = message.substring(this.getPrefix().length());
            if (message.split(" ").length > 0) {
                final String name = message.split(" ")[0];
                boolean found = false;
                for (final Command command : this.getCommands()) {
                    if (command.getAliases().contains(name.toLowerCase()) || command.getName().equalsIgnoreCase(name)) {
                        CommandManager.mc.field_71456_v.func_146158_b().func_146239_a(event.getMessage());
                        command.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Notorious.INSTANCE.messageManager.sendError("Command could not be found.");
                }
            }
        }
    }
    
    public ArrayList<Command> getCommands() {
        return this.commands;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}
