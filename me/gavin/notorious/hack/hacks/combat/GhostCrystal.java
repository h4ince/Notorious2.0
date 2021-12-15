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

@RegisterHack(name = "GhostCrystal", description = "or how to get kicked for packets part 2", category = Category.Combat)
public class GhostCrystal extends Hack
{
    int startingHand;
    
    @Override
    public void onUpdate() {
        final int crystalHand = InventoryUtil.findItemInHotbar(Items.field_185158_cP);
        if (InventoryUtil.findItemInHotbar(Items.field_151153_ao) != -1) {
            GhostCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(crystalHand));
            GhostCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
    }
    
    public void onEnable() {
        this.startingHand = GhostCrystal.mc.field_71439_g.field_71071_by.field_70461_c;
    }
}
