// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util.rewrite;

import java.util.Iterator;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.util.Objects;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityEnderCrystal;
import me.gavin.notorious.stuff.IMinecraft;

public class DamageUtil implements IMinecraft
{
    public static float calculateDamage(final EntityEnderCrystal crystal, final EntityPlayer player) {
        return calculateDamage(crystal.field_70165_t, crystal.field_70163_u, crystal.field_70161_v, (Entity)player);
    }
    
    public static float calculateDamage(final BlockPos position, final EntityPlayer player) {
        return calculateDamage(position.func_177958_n() + 0.5, position.func_177956_o() + 1.0, position.func_177952_p() + 0.5, (Entity)player);
    }
    
    public static float calculateDamage(final double posX, final double posY, final double posZ, final Entity entity) {
        final float doubleSize = 12.0f;
        final double size = entity.func_70011_f(posX, posY, posZ) / doubleSize;
        final Vec3d vec3d = new Vec3d(posX, posY, posZ);
        final double blockDensity = entity.field_70170_p.func_72842_a(vec3d, entity.func_174813_aQ());
        final double value = (1.0 - size) * blockDensity;
        final float damage = (float)(int)((value * value + value) / 2.0 * 7.0 * doubleSize + 1.0);
        double finalDamage = 1.0;
        if (entity instanceof EntityLivingBase) {
            finalDamage = getBlastReduction((EntityLivingBase)entity, getMultipliedDamage(damage), new Explosion((World)DamageUtil.mc.field_71441_e, (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finalDamage;
    }
    
    public static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)entity;
            final DamageSource source = DamageSource.func_94539_a(explosion);
            damage = CombatRules.func_189427_a(damage, (float)player.func_70658_aO(), (float)player.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
            damage *= 1.0f - MathHelper.func_76131_a((float)EnchantmentHelper.func_77508_a(player.func_184193_aE(), source), 0.0f, 20.0f) / 25.0f;
            if (entity.func_70644_a((Potion)Objects.requireNonNull(Potion.func_188412_a(11)))) {
                damage -= damage / 4.0f;
            }
            return damage;
        }
        damage = CombatRules.func_189427_a(damage, (float)entity.func_70658_aO(), (float)entity.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
        return damage;
    }
    
    private static float getMultipliedDamage(final float damage) {
        return damage * ((DamageUtil.mc.field_71441_e.func_175659_aa().func_151525_a() == 0) ? 0.0f : ((DamageUtil.mc.field_71441_e.func_175659_aa().func_151525_a() == 2) ? 1.0f : ((DamageUtil.mc.field_71441_e.func_175659_aa().func_151525_a() == 1) ? 0.5f : 1.5f)));
    }
    
    public static boolean shouldBreakArmor(final EntityPlayer player, final float targetPercent) {
        for (final ItemStack stack : player.func_184193_aE()) {
            if (stack == null || stack.func_77973_b() == Items.field_190931_a) {
                return true;
            }
            final float armorPercent = (stack.func_77958_k() - stack.func_77952_i()) / (float)stack.func_77958_k() * 100.0f;
            if (targetPercent >= armorPercent && stack.func_190916_E() < 2) {
                return true;
            }
        }
        return false;
    }
    
    public static float getDamageInPercent(final ItemStack stack) {
        final float green = (stack.func_77958_k() - (float)stack.func_77952_i()) / stack.func_77958_k();
        final float red = 1.0f - green;
        return (float)(100 - (int)(red * 100.0f));
    }
    
    public static int getRoundedDamage(final ItemStack stack) {
        return (int)getDamageInPercent(stack);
    }
}
