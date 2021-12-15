// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.command.impl;

import me.gavin.notorious.Notorious;
import me.gavin.notorious.command.RegisterCommand;
import me.gavin.notorious.command.Command;

@RegisterCommand(name = "Test", description = "Just a test command.", syntax = "test <test>", aliases = { "t", "te", "st" })
public class TestCommand extends Command
{
    @Override
    public void onCommand(final String[] args, final String command) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("one")) {
                Notorious.INSTANCE.messageManager.sendMessage(this.getName());
            }
            else if (args[0].equalsIgnoreCase("two")) {
                Notorious.INSTANCE.messageManager.sendMessage(this.getDescription());
            }
            else if (args[0].equalsIgnoreCase("three")) {
                Notorious.INSTANCE.messageManager.sendMessage(this.getSyntax() + " This is not an actual syntax message!");
            }
            else {
                this.sendSyntax();
            }
        }
    }
}
