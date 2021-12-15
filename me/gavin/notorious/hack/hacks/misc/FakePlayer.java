// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.math.MathHelper;
import me.gavin.notorious.util.rewrite.DamageUtil;
import net.minecraft.network.play.server.SPacketExplosion;
import me.gavin.notorious.event.events.PacketEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "FakePlayer", description = "Spawns a fake player", category = Category.Misc)
public class FakePlayer extends Hack
{
    @RegisterSetting
    private final BooleanSetting pops;
    @RegisterSetting
    private final BooleanSetting totemPopSound;
    @RegisterSetting
    private final BooleanSetting totemPopParticle;
    @RegisterSetting
    private final BooleanSetting move;
    @RegisterSetting
    private final ModeSetting mode;
    @RegisterSetting
    private final NumSetting chaseX;
    @RegisterSetting
    private final NumSetting chaseY;
    @RegisterSetting
    private final NumSetting chaseZ;
    public EntityOtherPlayerMP fakePlayer;
    
    public FakePlayer() {
        this.pops = new BooleanSetting("TotemPops", true);
        this.totemPopSound = new BooleanSetting("TotemPopSound", true);
        this.totemPopParticle = new BooleanSetting("TotemPopParticle", true);
        this.move = new BooleanSetting("Move", true);
        this.mode = new ModeSetting("MovementMode", "Static", new String[] { "Static", "Chase" });
        this.chaseX = new NumSetting("ChaseX", 4.0f, 1.0f, 120.0f, 0.1f);
        this.chaseY = new NumSetting("ChaseY", 2.0f, 1.0f, 120.0f, 0.1f);
        this.chaseZ = new NumSetting("ChaseZ", 2.0f, 1.0f, 120.0f, 0.1f);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.mode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @Override
    protected void onEnable() {
        if (FakePlayer.mc.field_71441_e == null || FakePlayer.mc.field_71439_g == null) {
            this.disable();
        }
        else {
            final UUID playerUUID = FakePlayer.mc.field_71439_g.func_110124_au();
            (this.fakePlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.field_71441_e, new GameProfile(UUID.fromString(playerUUID.toString()), FakePlayer.mc.field_71439_g.getDisplayNameString()))).func_82149_j((Entity)FakePlayer.mc.field_71439_g);
            this.fakePlayer.field_71071_by.func_70455_b(FakePlayer.mc.field_71439_g.field_71071_by);
            FakePlayer.mc.field_71441_e.func_73027_a(-7777, (Entity)this.fakePlayer);
            this.notorious.messageManager.sendMessage("Added a " + ChatFormatting.GREEN + "fake player" + ChatFormatting.RESET + " to your world.");
        }
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        if (this.pops.getValue() && this.fakePlayer != null) {
            this.fakePlayer.field_71071_by.field_184439_c.set(0, (Object)new ItemStack(Items.field_190929_cY));
            if (this.fakePlayer.func_110143_aJ() <= 0.0f) {
                this.fakePop((Entity)this.fakePlayer);
                this.fakePlayer.func_70606_j(20.0f);
                this.notorious.popListener.handlePop((EntityPlayer)this.fakePlayer);
            }
        }
        if (this.move.isEnabled() && !this.mode.getMode().equals("Chase")) {
            this.travel(this.fakePlayer.field_70702_br, this.fakePlayer.field_70701_bs, this.fakePlayer.field_191988_bg);
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.mode.getMode().equals("Chase")) {
            this.fakePlayer.field_70165_t = FakePlayer.mc.field_71439_g.field_70165_t + this.chaseX.getValue();
            this.fakePlayer.field_70163_u = this.chaseY.getValue();
            this.fakePlayer.field_70161_v = FakePlayer.mc.field_71439_g.field_70161_v + this.chaseZ.getValue();
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (this.fakePlayer == null) {
            return;
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion explosion = (SPacketExplosion)event.getPacket();
            if (this.fakePlayer.func_70011_f(explosion.func_149148_f(), explosion.func_149143_g(), explosion.func_149145_h()) <= 15.0) {
                final double damage = DamageUtil.calculateDamage(explosion.func_149148_f(), explosion.func_149143_g(), explosion.func_149145_h(), (Entity)this.fakePlayer);
                if (damage > 0.0 && this.pops.isEnabled()) {
                    this.fakePlayer.func_70606_j((float)(this.fakePlayer.func_110143_aJ() - MathHelper.func_151237_a(damage, 0.0, 999.0)));
                }
            }
        }
    }
    
    public void travel(final float strafe, final float vertical, final float forward) {
        final double d0 = this.fakePlayer.field_70163_u;
        float f1 = 0.8f;
        float f2 = 0.02f;
        float f3 = (float)EnchantmentHelper.func_185294_d((EntityLivingBase)this.fakePlayer);
        if (f3 > 3.0f) {
            f3 = 3.0f;
        }
        if (!this.fakePlayer.field_70122_E) {
            f3 *= 0.5f;
        }
        if (f3 > 0.0f) {
            f1 += (0.54600006f - f1) * f3 / 3.0f;
            f2 += (this.fakePlayer.func_70689_ay() - f2) * f3 / 4.0f;
        }
        this.fakePlayer.func_191958_b(strafe, vertical, forward, f2);
        this.fakePlayer.func_70091_d(MoverType.SELF, this.fakePlayer.field_70159_w, this.fakePlayer.field_70181_x, this.fakePlayer.field_70179_y);
        final EntityOtherPlayerMP fakePlayer = this.fakePlayer;
        fakePlayer.field_70159_w *= f1;
        final EntityOtherPlayerMP fakePlayer2 = this.fakePlayer;
        fakePlayer2.field_70181_x *= 0.800000011920929;
        final EntityOtherPlayerMP fakePlayer3 = this.fakePlayer;
        fakePlayer3.field_70179_y *= f1;
        if (!this.fakePlayer.func_189652_ae()) {
            final EntityOtherPlayerMP fakePlayer4 = this.fakePlayer;
            fakePlayer4.field_70181_x -= 0.02;
        }
        if (this.fakePlayer.field_70123_F && this.fakePlayer.func_70038_c(this.fakePlayer.field_70159_w, this.fakePlayer.field_70181_x + 0.6000000238418579 - this.fakePlayer.field_70163_u + d0, this.fakePlayer.field_70179_y)) {
            this.fakePlayer.field_70181_x = 0.30000001192092896;
        }
    }
    
    @Override
    protected void onDisable() {
        if (this.fakePlayer != null && FakePlayer.mc.field_71441_e != null) {
            FakePlayer.mc.field_71441_e.func_73028_b(-7777);
            this.notorious.messageManager.sendMessage("Removed a " + ChatFormatting.RED + "fake player" + ChatFormatting.RESET + " from your world.");
            this.fakePlayer = null;
        }
    }
    
    private void fakePop(final Entity entity) {
        if (this.totemPopParticle.isEnabled()) {
            FakePlayer.mc.field_71452_i.func_191271_a(entity, EnumParticleTypes.TOTEM, 30);
        }
        if (this.totemPopSound.isEnabled()) {
            FakePlayer.mc.field_71441_e.func_184134_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, SoundEvents.field_191263_gW, entity.func_184176_by(), 1.0f, 1.0f, false);
        }
    }
}
