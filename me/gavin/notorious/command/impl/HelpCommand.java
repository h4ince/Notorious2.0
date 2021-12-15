// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.command.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.command.RegisterCommand;
import me.gavin.notorious.command.Command;

@RegisterCommand(name = "Help", description = "Tells you stuff about the client and client stuff.", syntax = "help", aliases = { "h", "he", "lp" })
public class HelpCommand extends Command
{
    @Override
    public void onCommand(final String[] args, final String command) {
        Notorious.INSTANCE.messageManager.sendMessage("Notorious Help:");
        Notorious.INSTANCE.messageManager.sendMessage("TestCommand - " + ChatFormatting.GRAY + "Just a test command.");
    }
}
