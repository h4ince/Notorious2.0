// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Offhand", description = "why i coded silent switch?", category = Category.Combat)
public class Offhand extends Hack
{
    @Override
    public void onUpdate() {
        if (Offhand.mc.field_71439_g.func_184582_a(EntityEquipmentSlot.OFFHAND).func_77973_b() == Items.field_185158_cP) {
            return;
        }
        final int slot = this.getItemSlot();
        if (slot != -1) {
            Offhand.mc.field_71442_b.func_187098_a(Offhand.mc.field_71439_g.field_71069_bz.field_75152_c, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.field_71439_g);
            Offhand.mc.field_71442_b.func_187098_a(Offhand.mc.field_71439_g.field_71069_bz.field_75152_c, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.field_71439_g);
            Offhand.mc.field_71442_b.func_187098_a(Offhand.mc.field_71439_g.field_71069_bz.field_75152_c, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.field_71439_g);
            Offhand.mc.field_71442_b.func_78765_e();
        }
    }
    
    private int getItemSlot() {
        for (int i = 0; i < 36; ++i) {
            final Item item = Offhand.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item == Items.field_190929_cY) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
}
