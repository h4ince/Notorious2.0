// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import me.gavin.notorius.util.InventoryUtil;
import net.minecraft.init.Items;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "GhostGap", description = "or how to get kicked for packets", category = Category.Combat)
public class GhostGap extends Hack
{
    int startingHand;
    
    @Override
    public void onUpdate() {
        final int gapHand = InventoryUtil.findItemInHotbar(Items.field_151153_ao);
        if (InventoryUtil.findItemInHotbar(Items.field_151153_ao) != -1) {
            GhostGap.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(gapHand));
            GhostGap.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
    }
    
    public void onEnable() {
        this.startingHand = GhostGap.mc.field_71439_g.field_71071_by.field_70461_c;
    }
}
