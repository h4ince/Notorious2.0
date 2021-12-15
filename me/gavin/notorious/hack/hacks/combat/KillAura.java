// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.util.EnumHand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.SettingGroup;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "KillAura", description = "Attacks entities for you", category = Category.Combat)
public class KillAura extends Hack
{
    public final SettingGroup targets;
    @RegisterSetting
    public final BooleanSetting attackDelay;
    @RegisterSetting
    public final BooleanSetting players;
    @RegisterSetting
    public final BooleanSetting animals;
    @RegisterSetting
    public final BooleanSetting mobs;
    @RegisterSetting
    public final NumSetting attackSpeed;
    @RegisterSetting
    public final NumSetting range;
    
    public KillAura() {
        this.targets = new SettingGroup("Targets");
        this.attackDelay = new BooleanSetting("1.9 Delay", true);
        this.players = new BooleanSetting("Players", true);
        this.animals = new BooleanSetting("Animals", false);
        this.mobs = new BooleanSetting("Mobs", false);
        this.attackSpeed = new NumSetting("Attack Speed", 10.0f, 2.0f, 18.0f, 1.0f);
        this.range = new NumSetting("Range", 4.0f, 1.0f, 6.0f, 0.25f);
        this.players.setGroup(this.targets);
        this.animals.setGroup(this.targets);
        this.mobs.setGroup(this.targets);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.range.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final PlayerLivingUpdateEvent event) {
        for (final Entity entity : KillAura.mc.field_71441_e.field_72996_f) {
            if (entity.equals((Object)KillAura.mc.field_71439_g)) {
                continue;
            }
            if (entity instanceof EntityPlayer && this.players.isEnabled()) {
                this.attack(entity);
            }
            if (entity instanceof EntityAnimal && this.animals.isEnabled()) {
                this.attack(entity);
            }
            if (!(entity instanceof EntityMob) || !this.mobs.isEnabled()) {
                continue;
            }
            this.attack(entity);
        }
    }
    
    private void attack(final Entity entity) {
        if (this.shouldAttack((EntityLivingBase)entity)) {
            if (this.attackDelay.isEnabled()) {
                if (KillAura.mc.field_71439_g.func_184825_o(0.0f) >= 1.0f) {
                    KillAura.mc.field_71442_b.func_78764_a((EntityPlayer)KillAura.mc.field_71439_g, entity);
                    KillAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                }
            }
            else if (KillAura.mc.field_71439_g.field_70173_aa % this.attackSpeed.getValue() == 0.0) {
                KillAura.mc.field_71442_b.func_78764_a((EntityPlayer)KillAura.mc.field_71439_g, entity);
                KillAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
            }
        }
    }
    
    private boolean shouldAttack(final EntityLivingBase entity) {
        return entity.func_70032_d((Entity)KillAura.mc.field_71439_g) <= this.range.getValue() && entity.func_110143_aJ() > 0.0f;
    }
}
