// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util.rewrite;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import me.gavin.notorious.util.Instance;

public class InventoryUtil implements Instance
{
    public static int findItem(final Item item, final int minimum, final int maximum) {
        for (int i = minimum; i <= maximum; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack.func_77973_b() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static int findBlock(final Block block, final int minimum, final int maximum) {
        for (int i = minimum; i <= maximum; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack.func_77973_b() instanceof ItemBlock) {
                final ItemBlock item = (ItemBlock)stack.func_77973_b();
                if (item.func_179223_d() == block) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static void switchToSlot(final int slot, final boolean silent) {
        InventoryUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(slot));
        if (!silent) {
            InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c = slot;
        }
        if (InventoryUtil.mc.field_71439_g.field_71174_a.func_147298_b().func_150724_d()) {
            InventoryUtil.mc.field_71439_g.field_71174_a.func_147298_b().func_74428_b();
        }
        else {
            InventoryUtil.mc.field_71439_g.field_71174_a.func_147298_b().func_179293_l();
        }
    }
    
    public static void moveItemToSlot(final Integer startSlot, final Integer endSlot) {
        InventoryUtil.mc.field_71442_b.func_187098_a(InventoryUtil.mc.field_71439_g.field_71069_bz.field_75152_c, (int)startSlot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil.mc.field_71439_g);
        InventoryUtil.mc.field_71442_b.func_187098_a(InventoryUtil.mc.field_71439_g.field_71069_bz.field_75152_c, (int)endSlot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil.mc.field_71439_g);
        InventoryUtil.mc.field_71442_b.func_187098_a(InventoryUtil.mc.field_71439_g.field_71069_bz.field_75152_c, (int)startSlot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil.mc.field_71439_g);
    }
}
