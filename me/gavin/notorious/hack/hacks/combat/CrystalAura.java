// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.RayTraceResult;
import java.util.Iterator;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import me.gavin.notorious.util.rewrite.InventoryUtil;
import net.minecraft.init.Items;
import me.gavin.notorious.util.rewrite.DamageUtil;
import me.gavin.notorious.util.zihasz.WorldUtil;
import net.minecraft.network.Packet;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import me.gavin.notorious.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.util.RenderUtil;
import java.awt.Color;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.util.TimerUtils;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoCrystalRewrite", description = "nigus", category = Category.Combat)
public class CrystalAura extends Hack
{
    @RegisterSetting
    private final NumSetting targetRange;
    @RegisterSetting
    private final NumSetting placeRange;
    @RegisterSetting
    private final NumSetting breakRange;
    @RegisterSetting
    private final NumSetting placeDelay;
    @RegisterSetting
    private final NumSetting breakDelay;
    @RegisterSetting
    private final NumSetting minTDamage;
    @RegisterSetting
    private final NumSetting maxSDamage;
    @RegisterSetting
    private final BooleanSetting ignoreSelfDamage;
    @RegisterSetting
    private final BooleanSetting oneThirteen;
    @RegisterSetting
    private final BooleanSetting entityCheck;
    @RegisterSetting
    private final BooleanSetting silentSwitch;
    @RegisterSetting
    private final BooleanSetting cancelSwing;
    @RegisterSetting
    private final BooleanSetting soundSync;
    @RegisterSetting
    private final ModeSetting renderMode;
    @RegisterSetting
    private final ColorSetting outlineColor;
    @RegisterSetting
    private final ColorSetting fillColor;
    private final TimerUtils rTimer;
    private final TimerUtils pTimer;
    private final TimerUtils bTimer;
    private EntityPlayer target;
    private BlockPos renderPos;
    
    public CrystalAura() {
        this.targetRange = new NumSetting("TargetRange", 7.0f, 0.0f, 10.0f, 1.0f);
        this.placeRange = new NumSetting("PlaceRange", 5.0f, 0.0f, 7.0f, 1.0f);
        this.breakRange = new NumSetting("BreakRange", 5.0f, 0.0f, 7.0f, 1.0f);
        this.placeDelay = new NumSetting("PlaceDelay", 0.0f, 0.0f, 1000.0f, 1.0f);
        this.breakDelay = new NumSetting("BreakDelay", 50.0f, 0.0f, 1000.0f, 1.0f);
        this.minTDamage = new NumSetting("MinTargetDamage", 4.0f, 0.0f, 36.0f, 1.0f);
        this.maxSDamage = new NumSetting("MaxSelfDamage", 10.0f, 0.0f, 36.0f, 1.0f);
        this.ignoreSelfDamage = new BooleanSetting("IgnoreSelfDamage", false);
        this.oneThirteen = new BooleanSetting("1.13+", false);
        this.entityCheck = new BooleanSetting("EntityCheck", false);
        this.silentSwitch = new BooleanSetting("SilentSwitch", false);
        this.cancelSwing = new BooleanSetting("CancelSwing", false);
        this.soundSync = new BooleanSetting("SoundSync", false);
        this.renderMode = new ModeSetting("RenderMode", "Both", new String[] { "Both", "Outline", "Fill" });
        this.outlineColor = new ColorSetting("OutlineColor", 255, 255, 255, 255);
        this.fillColor = new ColorSetting("OutlineColor", 255, 255, 255, 255);
        this.rTimer = new TimerUtils();
        this.pTimer = new TimerUtils();
        this.bTimer = new TimerUtils();
    }
    
    @Override
    protected void onEnable() {
    }
    
    @Override
    public void onTick() {
        if (CrystalAura.mc.field_71439_g == null || CrystalAura.mc.field_71441_e == null) {
            return;
        }
        this.doAutoCrystal();
    }
    
    @SubscribeEvent
    public void onRender3D(final RenderWorldLastEvent event) {
        boolean outline;
        boolean fill;
        if (this.renderMode.getMode().equals("Both")) {
            outline = true;
            fill = true;
        }
        else if (this.renderMode.getMode().equals("Outline")) {
            outline = true;
            fill = false;
        }
        else {
            fill = true;
            outline = false;
        }
        if (this.renderPos != null) {
            final AxisAlignedBB bb = new AxisAlignedBB(this.renderPos);
            if (fill) {
                RenderUtil.renderFilledBB(bb, new Color(this.fillColor.getAsColor().getRed(), this.fillColor.getAsColor().getGreen(), this.fillColor.getAsColor().getBlue(), 125));
            }
            if (outline) {
                RenderUtil.renderOutlineBB(bb, new Color(this.outlineColor.getAsColor().getRed(), this.outlineColor.getAsColor().getGreen(), this.outlineColor.getAsColor().getBlue(), 255));
            }
            if (this.rTimer.hasTimeElapsed(500L)) {
                this.renderPos = null;
                this.rTimer.reset();
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketRead(final PacketEvent.Receive event) {
        final Packet<?> raw = event.getPacket();
        if (raw instanceof SPacketSoundEffect) {
            if (!this.soundSync.getValue()) {
                return;
            }
            final SPacketSoundEffect packet = (SPacketSoundEffect)raw;
            if (packet.func_186978_a() == SoundEvents.field_187539_bB) {
                final BlockPos pos = new BlockPos(packet.func_149207_d(), packet.func_149211_e(), packet.func_149210_f());
                CrystalAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> entity.func_174818_b(pos) <= 36.0).forEach(Entity::func_70106_y);
            }
        }
    }
    
    @Override
    protected void onDisable() {
        this.target = null;
        this.renderPos = null;
        this.rTimer.reset();
        this.pTimer.reset();
        this.bTimer.reset();
    }
    
    private void doAutoCrystal() {
        this.target = this.getTarget();
        this.doPlace();
        this.doBreak();
    }
    
    private void doPlace() {
        if (this.target == null) {
            return;
        }
        if (!this.pTimer.hasTimeElapsed((long)this.placeDelay.getValue())) {
            return;
        }
        BlockPos optimal = null;
        float optimalDmg = 0.0f;
        for (final BlockPos block : WorldUtil.getSphere(CrystalAura.mc.field_71439_g.func_180425_c(), this.placeRange.getValue(), false)) {
            if (block != null && this.target != null) {
                if (CrystalAura.mc.field_71439_g == null) {
                    continue;
                }
                if (CrystalAura.mc.field_71441_e.func_175623_d(block)) {
                    continue;
                }
                if (!this.isPlaceable(block)) {
                    continue;
                }
                if (!this.canPlaceCry(block, this.oneThirteen.getValue(), this.entityCheck.getValue())) {
                    continue;
                }
                if (CrystalAura.mc.field_71439_g.func_70011_f((double)block.func_177958_n(), (double)block.func_177956_o(), (double)block.func_177952_p()) > this.placeRange.getValue()) {
                    continue;
                }
                if (optimal == null) {
                    optimal = block;
                }
                final float targetDamage = DamageUtil.calculateDamage(block, this.target);
                final float selfDamage = this.ignoreSelfDamage.getValue() ? 0.0f : DamageUtil.calculateDamage(block, (EntityPlayer)CrystalAura.mc.field_71439_g);
                if (targetDamage < this.minTDamage.getValue()) {
                    continue;
                }
                if (selfDamage > this.maxSDamage.getValue()) {
                    continue;
                }
                if (optimalDmg >= targetDamage) {
                    continue;
                }
                optimal = block;
                optimalDmg = targetDamage;
            }
        }
        if (optimal == null) {
            return;
        }
        boolean switched = false;
        final int crySlot = InventoryUtil.findItem(Items.field_185158_cP, 0, 9);
        final int oldSlot = CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c;
        if (!this.isHoldingCrystal() && this.silentSwitch.getValue() && crySlot != -1) {
            InventoryUtil.switchToSlot(crySlot, this.silentSwitch.getValue());
            switched = true;
        }
        final RayTraceResult result = CrystalAura.mc.field_71441_e.func_72933_a(new Vec3d((double)optimal.func_177958_n(), (double)optimal.func_177956_o(), (double)optimal.func_177952_p()), CrystalAura.mc.field_71439_g.func_174791_d());
        final EnumFacing facing = (result == null || result.field_178784_b == null) ? EnumFacing.UP : result.field_178784_b;
        final Vec3d hitVec = (result == null || result.field_72307_f == null) ? new Vec3d(0.0, 0.0, 0.0) : result.field_72307_f;
        this.renderPos = optimal;
        CrystalAura.mc.field_71442_b.func_187099_a(CrystalAura.mc.field_71439_g, CrystalAura.mc.field_71441_e, optimal, facing, hitVec, switched ? EnumHand.MAIN_HAND : this.getHand());
        if (switched && this.silentSwitch.getValue()) {
            CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(oldSlot));
        }
        this.pTimer.reset();
    }
    
    private void doBreak() {
        if (!this.bTimer.hasTimeElapsed((long)this.breakDelay.getValue())) {
            return;
        }
        for (final Entity entity : CrystalAura.mc.field_71441_e.field_72996_f) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            if (entity.field_70128_L) {
                continue;
            }
            if (CrystalAura.mc.field_71439_g.func_70032_d(entity) > this.breakRange.getValue()) {
                continue;
            }
            CrystalAura.mc.field_71442_b.func_78764_a((EntityPlayer)CrystalAura.mc.field_71439_g, entity);
        }
        this.bTimer.reset();
    }
    
    private EntityPlayer getTarget() {
        EntityPlayer optimal = null;
        for (final EntityPlayer player : CrystalAura.mc.field_71441_e.field_73010_i) {
            if (player == null) {
                continue;
            }
            if (player == CrystalAura.mc.field_71439_g) {
                continue;
            }
            if (player.field_70128_L || player.func_110143_aJ() <= 0.0f) {
                continue;
            }
            if (!player.func_70089_S()) {
                continue;
            }
            if (this.notorious.friend.isFriend(player.func_70005_c_())) {
                continue;
            }
            if (CrystalAura.mc.field_71439_g.func_70032_d((Entity)player) > this.targetRange.getValue()) {
                continue;
            }
            if (optimal == null) {
                optimal = player;
            }
            else if (player.func_110143_aJ() > optimal.func_110143_aJ()) {
                optimal = player;
            }
            else {
                if (CrystalAura.mc.field_71439_g.func_70032_d((Entity)player) >= CrystalAura.mc.field_71439_g.func_70032_d((Entity)optimal)) {
                    continue;
                }
                optimal = player;
            }
        }
        return optimal;
    }
    
    private EnumHand getHand() {
        return CrystalAura.mc.field_71439_g.func_184614_ca().func_77973_b().equals(Items.field_185158_cP) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
    }
    
    private boolean isPlaceable(final BlockPos pos) {
        final Block block = CrystalAura.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        return block.equals(Blocks.field_150343_Z) || block.equals(Blocks.field_150357_h);
    }
    
    private boolean canPlaceCry(final BlockPos pos, final boolean oneThirteen, final boolean entityCheck) {
        final BlockPos up1 = pos.func_177982_a(0, 1, 0);
        final BlockPos up2 = pos.func_177982_a(0, 2, 0);
        if (!CrystalAura.mc.field_71441_e.func_175623_d(up1)) {
            return false;
        }
        if (entityCheck && !CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(up2)).isEmpty()) {
            return false;
        }
        for (final Entity entity : CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(up1))) {
            if (!(entity instanceof EntityEnderCrystal)) {
                return false;
            }
        }
        if (!oneThirteen) {
            if (!CrystalAura.mc.field_71441_e.func_175623_d(up2)) {
                return false;
            }
            if (entityCheck && !CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(up2)).isEmpty()) {
                return false;
            }
            for (final Entity entity : CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(up2))) {
                if (!(entity instanceof EntityEnderCrystal)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isHoldingCrystal() {
        return CrystalAura.mc.field_71439_g.func_184614_ca().func_77973_b().equals(Items.field_185158_cP) || CrystalAura.mc.field_71439_g.func_184592_cb().func_77973_b().equals(Items.field_185158_cP);
    }
}
