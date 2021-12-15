// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.item.ItemBow;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "BowSpam", description = "bow go brrrr", category = Category.Combat)
public class BowSpam extends Hack
{
    @Override
    public void onUpdate() {
        if (BowSpam.mc.field_71439_g != null && BowSpam.mc.field_71441_e != null && BowSpam.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemBow && BowSpam.mc.field_71439_g.func_184587_cr() && BowSpam.mc.field_71439_g.func_184612_cw() >= 3) {
            BowSpam.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, BowSpam.mc.field_71439_g.func_174811_aO()));
            BowSpam.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(BowSpam.mc.field_71439_g.func_184600_cs()));
            BowSpam.mc.field_71439_g.func_184597_cx();
        }
    }
}
