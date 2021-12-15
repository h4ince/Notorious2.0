// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import net.minecraft.item.ItemStack;
import java.util.Iterator;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantment;
import java.util.Map;
import java.util.ArrayList;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "EGapFinder", description = "ez", category = Category.Misc)
public class EGapFinder extends Hack
{
    @RegisterSetting
    public final BooleanSetting egaps;
    @RegisterSetting
    public final BooleanSetting crapples;
    @RegisterSetting
    public final BooleanSetting mending;
    @RegisterSetting
    public final BooleanSetting prot;
    @RegisterSetting
    public final BooleanSetting bprot;
    @RegisterSetting
    public final BooleanSetting feather;
    @RegisterSetting
    public final BooleanSetting unb;
    @RegisterSetting
    public final BooleanSetting effi;
    @RegisterSetting
    public final BooleanSetting sharp;
    @RegisterSetting
    public final BooleanSetting books;
    
    public EGapFinder() {
        this.egaps = new BooleanSetting("EGaps", true);
        this.crapples = new BooleanSetting("Crapples", true);
        this.mending = new BooleanSetting("Mending", true);
        this.prot = new BooleanSetting("Protection", true);
        this.bprot = new BooleanSetting("BProtection", true);
        this.feather = new BooleanSetting("Feather", true);
        this.unb = new BooleanSetting("Unbreaking", true);
        this.effi = new BooleanSetting("Efficiency", true);
        this.sharp = new BooleanSetting("Sharp", true);
        this.books = new BooleanSetting("AllBooks", true);
    }
    
    @Override
    public void onTick() {
        EntityPlayer entityPlayer = null;
        if (!EGapFinder.mc.field_71441_e.field_147482_g.isEmpty()) {
            entityPlayer = EGapFinder.mc.field_71441_e.field_73010_i.get(0);
            for (final TileEntity tileEntity : EGapFinder.mc.field_71441_e.field_147482_g) {
                if (tileEntity instanceof TileEntityChest) {
                    final TileEntityChest chest = (TileEntityChest)tileEntity;
                    chest.func_184281_d(entityPlayer);
                    for (byte b = 0; b < chest.func_70302_i_(); ++b) {
                        final ItemStack itemStack = chest.func_70301_a((int)b);
                        if (itemStack.func_77973_b() == Items.field_151153_ao) {
                            if (itemStack.func_77952_i() == 1) {
                                if (this.egaps.isEnabled()) {
                                    this.notorious.messageManager.sendMessage("Dungeon Chest with god gapple at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                                }
                            }
                            else if (itemStack.func_77952_i() != 1 && this.crapples.isEnabled()) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with crapple at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                        }
                        if (itemStack.func_77973_b() == Items.field_151134_bR && EnchantmentHelper.func_82781_a(itemStack) != null) {
                            final ArrayList<String> arrayList = new ArrayList<String>();
                            for (final Map.Entry entry : EnchantmentHelper.func_82781_a(itemStack).entrySet()) {
                                final Enchantment enchantment = entry.getKey();
                                final Integer integer = entry.getValue();
                                arrayList.add(enchantment.func_77316_c((int)integer));
                            }
                            String str = "";
                            for (final String str2 : arrayList) {
                                if (arrayList.indexOf(str2) != arrayList.size() - 1) {
                                    str = String.valueOf(new StringBuilder().append(str).append(str2).append(", "));
                                }
                                else {
                                    str = String.valueOf(new StringBuilder().append(str).append(str2));
                                }
                            }
                            if (this.mending.isEnabled() && str.contains("Mending")) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Mending at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.prot.isEnabled() && str.contains("Protection IV")) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Prot 4 at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.bprot.isEnabled() && str.contains("Blast Protection IV")) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Blast Prot 4 at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.unb.isEnabled() && str.contains("Unbreaking III")) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Unbreaking 3 at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.feather.isEnabled() && str.contains("Feather Falling IV")) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Feather Falling 4 at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.effi.isEnabled() && (str.contains("Efficiency IV") || str.contains("Efficiency V"))) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Efficiency 4/5 at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.sharp.isEnabled() && (str.contains("Sharpness IV") || str.contains("Sharpness V"))) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Sharpness 4/5 at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                            if (this.books.isEnabled()) {
                                this.notorious.messageManager.sendMessage("Dungeon Chest with Enchanted Books at: " + chest.func_174877_v().func_177958_n() + " " + chest.func_174877_v().func_177956_o() + " " + chest.func_174877_v().func_177952_p());
                            }
                        }
                    }
                }
            }
            for (final Entity entity : EGapFinder.mc.field_71441_e.field_72996_f) {
                if (entity instanceof EntityMinecartContainer) {
                    final EntityMinecartContainer minecart = (EntityMinecartContainer)entity;
                    if (minecart.func_184276_b() == null) {
                        continue;
                    }
                    minecart.func_184288_f(entityPlayer);
                    for (byte b = 0; b < minecart.itemHandler.getSlots(); ++b) {
                        final ItemStack itemStack = minecart.itemHandler.getStackInSlot((int)b);
                        if (itemStack.func_77973_b() == Items.field_151153_ao) {
                            if (itemStack.func_77952_i() == 1) {
                                if (this.egaps.isEnabled()) {
                                    this.notorious.messageManager.sendMessage("Minecart with God Gapple at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                                }
                            }
                            else if (itemStack.func_77952_i() != 1 && this.crapples.isEnabled()) {
                                this.notorious.messageManager.sendMessage("Minecart with Crapple at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                        }
                        if (itemStack.func_77973_b() == Items.field_151134_bR && this.books.isEnabled() && EnchantmentHelper.func_82781_a(itemStack) != null) {
                            final ArrayList<String> arrayList = new ArrayList<String>();
                            for (final Map.Entry entry : EnchantmentHelper.func_82781_a(itemStack).entrySet()) {
                                final Enchantment enchantment = entry.getKey();
                                final Integer integer = entry.getValue();
                                arrayList.add(enchantment.func_77316_c((int)integer));
                            }
                            String str = "";
                            for (final String str2 : arrayList) {
                                if (arrayList.indexOf(str2) != arrayList.size() - 1) {
                                    str = String.valueOf(new StringBuilder().append(str).append(str2).append(", "));
                                }
                                else {
                                    str = String.valueOf(new StringBuilder().append(str).append(str2));
                                }
                            }
                            if (this.mending.isEnabled() && str.contains("Mending")) {
                                this.notorious.messageManager.sendMessage("Minecart with Mending at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.prot.isEnabled() && str.contains("Protection IV")) {
                                this.notorious.messageManager.sendMessage("Minecart with Protection 4 at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.bprot.isEnabled() && str.contains("Blast Protection IV")) {
                                this.notorious.messageManager.sendMessage("Minecart with Blast Protection 4 at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.unb.isEnabled() && str.contains("Unbreaking III")) {
                                this.notorious.messageManager.sendMessage("Minecart with Unbreaking 3 at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.feather.isEnabled() && str.contains("Feather Falling IV")) {
                                this.notorious.messageManager.sendMessage("Minecart with Feather Falling 4 at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.effi.isEnabled() && (str.contains("Efficiency IV") || str.contains("Efficiency V"))) {
                                this.notorious.messageManager.sendMessage("Minecart with Efficiency 4/5 at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.sharp.isEnabled() && (str.contains("Sharpness IV") || str.contains("Sharpness V"))) {
                                this.notorious.messageManager.sendMessage("Minecart with Sharpness 4/5 at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                            if (this.books.isEnabled()) {
                                this.notorious.messageManager.sendMessage("Minecart with Enchanted Book at: " + minecart.field_70165_t + " " + minecart.field_70163_u + " " + minecart.field_70161_v);
                            }
                        }
                    }
                }
            }
        }
    }
}
