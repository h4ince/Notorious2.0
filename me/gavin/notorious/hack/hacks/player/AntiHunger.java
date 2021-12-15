// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.player;

import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AntiHunger", description = "Africa Simulator", category = Category.Player)
public class AntiHunger extends Hack
{
    @Override
    public void onUpdate() {
        AntiHunger.mc.field_71439_g.func_71024_bL().func_75114_a(20);
    }
}
