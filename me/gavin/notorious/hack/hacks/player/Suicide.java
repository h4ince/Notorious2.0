// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.player;

import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Suicide", description = "Automatically kills you.", category = Category.Player)
public class Suicide extends Hack
{
    public void onEnable() {
        if (Suicide.mc.field_71439_g != null) {
            Suicide.mc.field_71439_g.func_71165_d("/kill");
        }
        this.toggle();
    }
}
