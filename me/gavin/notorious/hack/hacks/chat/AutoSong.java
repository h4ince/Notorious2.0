// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoSong", description = "spams chat with lyrics", category = Category.Chat)
public class AutoSong extends Hack
{
    public void onEnable() {
        if (AutoSong.mc.field_71439_g != null) {
            AutoSong.mc.field_71439_g.func_71165_d("Never gona give you up!");
        }
        AutoSong.mc.field_71439_g.func_71165_d("Never gona let you down!");
        AutoSong.mc.field_71439_g.func_71165_d("Never gona run around!");
        AutoSong.mc.field_71439_g.func_71165_d("And Desert You!");
        AutoSong.mc.field_71439_g.func_71165_d("And stay Mad!");
        this.toggle();
    }
}
