// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import me.gavin.notorious.mixin.mixins.accessor.IMinecraftMixin;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import java.util.Iterator;
import me.gavin.notorious.util.MathUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import me.gavin.notorious.util.rewrite.InventoryUtil;
import me.gavin.notorious.util.BlockUtil;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAnvilBlock;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AnvilBurrow", description = "Drops a anvil inside of you to act as a burrow.", category = Category.Combat)
public class AnvilBurrow extends Hack
{
    @RegisterSetting
    public final BooleanSetting rotate;
    @RegisterSetting
    public final BooleanSetting packet;
    @RegisterSetting
    public final BooleanSetting switchToAnvil;
    int slot;
    public float yaw;
    public float pitch;
    public boolean rotating;
    private BlockPos placeTarget;
    public ItemAnvilBlock anvil;
    private EntityPlayer finalTarget;
    
    public AnvilBurrow() {
        this.rotate = new BooleanSetting("Rotate", true);
        this.packet = new BooleanSetting("Packet", true);
        this.switchToAnvil = new BooleanSetting("SwitchToAnvil", true);
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.rotating = false;
    }
    
    public void onEnable() {
        this.doAnvilBurrow();
        this.toggle();
    }
    
    private void doAnvilBurrow() {
        this.finalTarget = (EntityPlayer)AnvilBurrow.mc.field_71439_g;
        if (this.finalTarget != null) {
            this.placeTarget = this.getTargetPos((Entity)this.finalTarget);
        }
        if (this.placeTarget != null && this.finalTarget != null) {
            this.placeAnvil(this.placeTarget);
        }
    }
    
    public void placeAnvil(final BlockPos pos) {
        this.rotateToPos(pos);
        if (this.switchToAnvil.isEnabled() && !this.isHoldingAnvil()) {
            this.switchToAnvil();
        }
        BlockUtil.placeBlock(pos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.isEnabled(), AnvilBurrow.mc.field_71439_g.func_70093_af());
    }
    
    private boolean isHoldingAnvil() {
        return AnvilBurrow.mc.field_71439_g.func_184614_ca().func_77973_b() == this.anvil;
    }
    
    private void switchToAnvil() {
        this.slot = InventoryUtil.findItem((Item)this.anvil, 0, 9);
        if (AnvilBurrow.mc.field_71439_g.func_184614_ca().func_77973_b() != this.anvil && this.slot != -1) {
            InventoryUtil.switchToSlot(this.slot, false);
        }
    }
    
    public List<BlockPos> getPlaceableBlocksAboveEntity(final Entity target) {
        final BlockPos playerPos = new BlockPos(Math.floor(AnvilBurrow.mc.field_71439_g.field_70165_t), Math.floor(AnvilBurrow.mc.field_71439_g.field_70163_u), Math.floor(AnvilBurrow.mc.field_71439_g.field_70161_v));
        final ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
        BlockPos pos;
        for (int i = (int)Math.floor(AnvilBurrow.mc.field_71439_g.field_70163_u + 2.0); i <= 256 && BlockUtil.isPositionPlaceable(pos = new BlockPos(Math.floor(AnvilBurrow.mc.field_71439_g.field_70165_t), (double)i, Math.floor(AnvilBurrow.mc.field_71439_g.field_70161_v)), false) != 0 && BlockUtil.isPositionPlaceable(pos, false) != -1 && BlockUtil.isPositionPlaceable(pos, false) != 2; ++i) {
            positions.add(pos);
        }
        return positions;
    }
    
    public BlockPos getTargetPos(final Entity target) {
        double distance = -1.0;
        BlockPos finalPos = null;
        for (final BlockPos pos : this.getPlaceableBlocksAboveEntity(target)) {
            if (distance != -1.0 && AnvilBurrow.mc.field_71439_g.func_174818_b(pos) >= MathUtil.square(distance)) {
                continue;
            }
            finalPos = pos;
            distance = AnvilBurrow.mc.field_71439_g.func_70011_f((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
        }
        return finalPos;
    }
    
    public void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction, final boolean packet) {
        if (packet) {
            final float f = (float)(vec.field_72450_a - pos.func_177958_n());
            final float f2 = (float)(vec.field_72448_b - pos.func_177956_o());
            final float f3 = (float)(vec.field_72449_c - pos.func_177952_p());
            AnvilBurrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
        }
        else {
            AnvilBurrow.mc.field_71442_b.func_187099_a(AnvilBurrow.mc.field_71439_g, AnvilBurrow.mc.field_71441_e, pos, direction, vec, hand);
        }
        AnvilBurrow.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        ((IMinecraftMixin)AnvilBurrow.mc).setRightClickDelayTimerAccessor(4);
    }
    
    private void rotateToPos(final BlockPos pos) {
        if (this.rotate.isEnabled()) {
            final float[] angle = MathUtil.calcAngle(AnvilBurrow.mc.field_71439_g.func_174824_e(AnvilBurrow.mc.func_184121_ak()), new Vec3d((double)(pos.func_177958_n() + 0.5f), (double)(pos.func_177956_o() - 0.5f), (double)(pos.func_177952_p() + 0.5f)));
            this.yaw = angle[0];
            this.pitch = angle[1];
            this.rotating = true;
        }
    }
}
