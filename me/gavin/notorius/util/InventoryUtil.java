// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorius.util;

import java.util.function.ToIntFunction;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class InventoryUtil
{
    private static final Minecraft mc;
    
    public static int findBlockInHotbar(final Block blockToFind) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack != ItemStack.field_190927_a) {
                if (stack.func_77973_b() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
                    if (block.equals(blockToFind)) {
                        slot = i;
                        break;
                    }
                }
            }
        }
        return slot;
    }
    
    public static int findBlockInHotbarObiEchestRandom() {
        final int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack != ItemStack.field_190927_a) {
                if (stack.func_77973_b() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
                    if (block.equals(Blocks.field_150343_Z)) {
                        return i;
                    }
                }
            }
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack != ItemStack.field_190927_a) {
                if (stack.func_77973_b() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
                    if (block.equals(Blocks.field_150477_bB)) {
                        return i;
                    }
                }
            }
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack != ItemStack.field_190927_a && stack.func_77973_b() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
                return i;
            }
        }
        return slot;
    }
    
    public static int findItemInHotbar(final Item itemToFind) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack != ItemStack.field_190927_a) {
                if (stack.func_77973_b() instanceof Item) {
                    final Item item = stack.func_77973_b();
                    if (item.equals(itemToFind)) {
                        slot = i;
                        break;
                    }
                }
            }
        }
        return slot;
    }
    
    public static int getItems(final Item i) {
        return InventoryUtil.mc.field_71439_g.field_71071_by.field_70462_a.stream().filter(itemStack -> itemStack.func_77973_b() == i).mapToInt(ItemStack::func_190916_E).sum() + InventoryUtil.mc.field_71439_g.field_71071_by.field_184439_c.stream().filter(itemStack -> itemStack.func_77973_b() == i).mapToInt(ItemStack::func_190916_E).sum();
    }
    
    public static int getBlocks(final Block block) {
        return getItems(Item.func_150898_a(block));
    }
    
    static {
        mc = Minecraft.func_71410_x();
    }
}
