// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.client;

import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Save", description = "ez", category = Category.Client)
public class Save extends Hack
{
    public void onEnable() {
        try {
            this.notorious.configManager.save();
            this.notorious.messageManager.sendMessage("Saved your config.");
        }
        catch (Exception var5) {
            this.notorious.messageManager.sendMessage("Config failed to save?");
            var5.printStackTrace();
        }
        this.toggle();
    }
}
