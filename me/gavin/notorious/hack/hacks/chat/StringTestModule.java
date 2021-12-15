// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.StringSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "StringTestModule", description = "ez", category = Category.Chat)
public class StringTestModule extends Hack
{
    @RegisterSetting
    public final StringSetting string;
    
    public StringTestModule() {
        this.string = new StringSetting("String", "uwu");
    }
    
    public void onEnable() {
        StringTestModule.mc.field_71439_g.func_71165_d(this.string.getString());
        this.toggle();
    }
}
