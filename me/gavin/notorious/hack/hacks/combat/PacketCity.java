// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.entity.EntityLivingBase;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumHand;
import me.gavin.notorious.util.RotationUtil;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import me.gavin.notorious.hack.hacks.player.PacketMine;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "PacketCity", description = "ez", category = Category.Combat)
public class PacketCity extends Hack
{
    @RegisterSetting
    public final NumSetting range;
    @RegisterSetting
    public final BooleanSetting rotate;
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    private boolean firstRun;
    private BlockPos mineTarget;
    private BlockPos renderBlock;
    private EntityPlayer closestTarget;
    
    public PacketCity() {
        this.range = new NumSetting("Range", 7.0f, 0.0f, 9.0f, 1.0f);
        this.rotate = new BooleanSetting("Rotate", true);
        this.mode = new ModeSetting("Mode", "Both", new String[] { "Both", "Outline", "Box" });
        this.outlineColor = new ColorSetting("Outline", new Color(117, 0, 255, 255));
        this.boxColor = new ColorSetting("Box", new Color(117, 0, 255, 65));
        this.mineTarget = null;
        this.renderBlock = null;
    }
    
    public void onEnable() {
        this.firstRun = true;
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        this.findClosestTarget();
        if (this.closestTarget == null) {
            if (this.firstRun) {
                this.firstRun = false;
                this.notorious.messageManager.sendMessage(ChatFormatting.WHITE.toString() + "Enabled" + ChatFormatting.RESET + ", no one to city!");
            }
            this.toggle();
            return;
        }
        if (this.firstRun && this.mineTarget != null) {
            this.firstRun = false;
            this.notorious.messageManager.sendMessage(TextFormatting.WHITE + " Attempting to mine: " + ChatFormatting.BLUE + this.closestTarget.func_70005_c_());
        }
        this.findCityBlock();
        if (this.mineTarget != null) {
            int newSlot = -1;
            for (int i = 0; i < 9; ++i) {
                final ItemStack stack = PacketCity.mc.field_71439_g.field_71071_by.func_70301_a(i);
                if (stack != ItemStack.field_190927_a) {
                    if (stack.func_77973_b() instanceof ItemPickaxe) {
                        newSlot = i;
                        break;
                    }
                }
            }
            if (newSlot != -1) {
                PacketCity.mc.field_71439_g.field_71071_by.field_70461_c = newSlot;
            }
            final boolean wasEnabled = this.notorious.hackManager.getHack(PacketMine.class).isEnabled();
            if (!wasEnabled) {
                this.notorious.hackManager.getHack(PacketMine.class).toggle();
            }
            final Vec3d target = new Vec3d((Vec3i)this.mineTarget);
            if (this.rotate.isEnabled()) {
                RotationUtil.faceVector(target, true);
            }
            PacketCity.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
            PacketCity.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.mineTarget, EnumFacing.DOWN));
            PacketCity.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.mineTarget, EnumFacing.DOWN));
            if (!wasEnabled) {
                this.notorious.hackManager.getHack(PacketMine.class).toggle();
            }
        }
        else {
            this.notorious.messageManager.sendMessage("No city blocks to mine!");
        }
        this.toggle();
    }
    
    public BlockPos findCityBlock() {
        final Double dist = (Double)this.range.getValue();
        final Vec3d vec = this.closestTarget.func_174791_d();
        if (PacketCity.mc.field_71439_g.func_174791_d().func_72438_d(vec) <= dist) {
            final BlockPos targetX = new BlockPos(vec.func_72441_c(1.0, 0.0, 0.0));
            final BlockPos targetXMinus = new BlockPos(vec.func_72441_c(-1.0, 0.0, 0.0));
            final BlockPos targetZ = new BlockPos(vec.func_72441_c(0.0, 0.0, 1.0));
            final BlockPos targetZMinus = new BlockPos(vec.func_72441_c(0.0, 0.0, -1.0));
            if (this.canBreak(targetX)) {
                this.mineTarget = targetX;
            }
            if (!this.canBreak(targetX) && this.canBreak(targetXMinus)) {
                this.mineTarget = targetXMinus;
            }
            if (!this.canBreak(targetX) && !this.canBreak(targetXMinus) && this.canBreak(targetZ)) {
                this.mineTarget = targetZ;
            }
            if (!this.canBreak(targetX) && !this.canBreak(targetXMinus) && !this.canBreak(targetZ) && this.canBreak(targetZMinus)) {
                this.mineTarget = targetZMinus;
            }
            if ((!this.canBreak(targetX) && !this.canBreak(targetXMinus) && !this.canBreak(targetZ) && !this.canBreak(targetZMinus)) || PacketCity.mc.field_71439_g.func_174791_d().func_72438_d(vec) > dist) {
                this.mineTarget = null;
            }
            this.renderBlock = this.mineTarget;
        }
        return this.mineTarget;
    }
    
    private boolean canBreak(final BlockPos pos) {
        final IBlockState blockState = PacketCity.mc.field_71441_e.func_180495_p(pos);
        final Block block = blockState.func_177230_c();
        return block.func_176195_g(blockState, (World)PacketCity.mc.field_71441_e, pos) != -1.0f;
    }
    
    private void findClosestTarget() {
        final List<EntityPlayer> playerList = (List<EntityPlayer>)PacketCity.mc.field_71441_e.field_73010_i;
        this.closestTarget = null;
        for (final EntityPlayer target : playerList) {
            if (target == PacketCity.mc.field_71439_g) {
                continue;
            }
            if (!isLiving((Entity)target)) {
                continue;
            }
            if (target.func_110143_aJ() <= 0.0f) {
                continue;
            }
            if (this.closestTarget == null) {
                this.closestTarget = target;
            }
            else {
                if (PacketCity.mc.field_71439_g.func_70032_d((Entity)target) >= PacketCity.mc.field_71439_g.func_70032_d((Entity)this.closestTarget)) {
                    continue;
                }
                this.closestTarget = target;
            }
        }
    }
    
    public static boolean isLiving(final Entity e) {
        return e instanceof EntityLivingBase;
    }
}
