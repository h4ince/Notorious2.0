// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import me.gavin.notorious.mixin.mixins.accessor.IMinecraftMixin;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.GameType;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumHand;
import me.gavin.notorious.Notorious;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.Block;
import java.util.List;
import me.gavin.notorious.stuff.IMinecraft;

public class BlockUtil implements IMinecraft
{
    public static List<Block> emptyBlocks;
    public static List<Block> actualBlocks;
    public static List<Block> rightclickableBlocks;
    public static List<Block> unSafeBlocks;
    public static Vec3d[] holeOffsets;
    
    public static ArrayList<BlockPos> getSurroundingBlocks(final int radius, final boolean motion) {
        final ArrayList<BlockPos> posList = new ArrayList<BlockPos>();
        final BlockPos playerPos = BlockUtil.mc.field_71439_g.func_180425_c().func_177982_a(0, 1, 0);
        if (motion) {
            playerPos.func_177963_a(BlockUtil.mc.field_71439_g.field_70159_w, BlockUtil.mc.field_71439_g.field_70181_x, BlockUtil.mc.field_71439_g.field_70179_y);
        }
        for (int x = -radius; x < radius; ++x) {
            for (int y = -radius; y < radius; ++y) {
                for (int z = -radius; z < radius; ++z) {
                    posList.add(new BlockPos(x, y, z).func_177971_a((Vec3i)playerPos));
                }
            }
        }
        return posList;
    }
    
    public static boolean isBurrowed(final Entity entity) {
        final BlockPos entityPos = new BlockPos(MathUtil.roundValueToCenter(entity.field_70165_t), entity.field_70163_u + 0.2, MathUtil.roundValueToCenter(entity.field_70161_v));
        return BlockUtil.mc.field_71441_e.func_180495_p(entityPos).func_177230_c() == Blocks.field_150343_Z || BlockUtil.mc.field_71441_e.func_180495_p(entityPos).func_177230_c() == Blocks.field_150477_bB;
    }
    
    public static ArrayList<BlockPos> getSurroundingBlocksOtherPlayers(final int radius, final boolean motion) {
        final Iterator<Entity> iterator = BlockUtil.mc.field_71441_e.field_72996_f.iterator();
        if (iterator.hasNext()) {
            final Entity e = iterator.next();
            final ArrayList<BlockPos> posList = new ArrayList<BlockPos>();
            final BlockPos playerPos = e.func_180425_c().func_177982_a(0, 1, 0);
            if (motion) {
                playerPos.func_177963_a(e.field_70159_w, e.field_70181_x, e.field_70179_y);
            }
            for (int x = -radius; x < radius; ++x) {
                for (int y = -radius; y < radius; ++y) {
                    for (int z = -radius; z < radius; ++z) {
                        posList.add(new BlockPos(x, y, z).func_177971_a((Vec3i)playerPos));
                    }
                }
            }
            return posList;
        }
        return null;
    }
    
    public static void damageBlock(final BlockPos position, final boolean packet, final boolean rotations) {
        damageBlock(position, EnumFacing.func_190914_a(position, (EntityLivingBase)BlockUtil.mc.field_71439_g), packet, rotations);
    }
    
    public static void damageBlock(final BlockPos position, final EnumFacing facing, final boolean packet, final boolean rotations) {
        if (rotations) {
            final float[] r = MathUtil.calculateLookAt(position.func_177958_n() + 0.5, position.func_177956_o() + 0.5, position.func_177952_p() + 0.5, (EntityPlayer)BlockUtil.mc.field_71439_g);
            Notorious.INSTANCE.rotationManager.desiredYaw = r[0];
            Notorious.INSTANCE.rotationManager.desiredPitch = r[1];
        }
        BlockUtil.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        if (packet) {
            BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, position, facing));
            BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, position, facing));
        }
        else if (BlockUtil.mc.func_147114_u().func_175102_a(BlockUtil.mc.field_71439_g.func_110124_au()).func_178848_b() == GameType.CREATIVE) {
            BlockUtil.mc.field_71442_b.func_180511_b(position, facing);
        }
        else {
            BlockUtil.mc.field_71442_b.func_180512_c(position, facing);
        }
    }
    
    public static void rotatePacket(final double x, final double y, final double z) {
        final double diffX = x - BlockUtil.mc.field_71439_g.field_70165_t;
        final double diffY = y - (BlockUtil.mc.field_71439_g.field_70163_u + BlockUtil.mc.field_71439_g.func_70047_e());
        final double diffZ = z - BlockUtil.mc.field_71439_g.field_70161_v;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(yaw, pitch, BlockUtil.mc.field_71439_g.field_70122_E));
    }
    
    public static int isPositionPlaceable(final BlockPos pos, final boolean rayTrace, final boolean entityCheck) {
        final Block block = BlockUtil.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) {
            return 0;
        }
        if (!rayTracePlaceCheck(pos, rayTrace, 0.0f)) {
            return -1;
        }
        if (entityCheck) {
            for (final Entity entity : BlockUtil.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(pos))) {
                if (!(entity instanceof EntityItem)) {
                    if (entity instanceof EntityXPOrb) {
                        continue;
                    }
                    return 1;
                }
            }
        }
        for (final EnumFacing side : getPossibleSides(pos)) {
            if (!canBeClicked(pos.func_177972_a(side))) {
                continue;
            }
            return 3;
        }
        return 2;
    }
    
    public static boolean placeBlock(final BlockPos pos, final EnumHand hand, final boolean rotate, final boolean packet, final boolean isSneaking) {
        boolean sneaking = false;
        final EnumFacing side = getFirstFacing(pos);
        if (side == null) {
            return isSneaking;
        }
        final BlockPos neighbour = pos.func_177972_a(side);
        final EnumFacing opposite = side.func_176734_d();
        final Vec3d hitVec = new Vec3d((Vec3i)neighbour).func_72441_c(0.5, 0.5, 0.5).func_178787_e(new Vec3d(opposite.func_176730_m()).func_186678_a(0.5));
        final Block neighbourBlock = BlockUtil.mc.field_71441_e.func_180495_p(neighbour).func_177230_c();
        if (!BlockUtil.mc.field_71439_g.func_70093_af() && (BlockUtil.emptyBlocks.contains(neighbourBlock) || BlockUtil.rightclickableBlocks.contains(neighbourBlock))) {
            BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.field_71439_g.func_70095_a(true);
            sneaking = true;
        }
        if (rotate) {
            RotationUtil.faceVector(hitVec, true);
        }
        rightClickBlock(neighbour, hitVec, hand, opposite, packet);
        BlockUtil.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        ((IMinecraftMixin)BlockUtil.mc).setRightClickDelayTimerAccessor(4);
        return sneaking || isSneaking;
    }
    
    public static void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction, final boolean packet) {
        if (packet) {
            final float f = (float)(vec.field_72450_a - pos.func_177958_n());
            final float f2 = (float)(vec.field_72448_b - pos.func_177956_o());
            final float f3 = (float)(vec.field_72449_c - pos.func_177952_p());
            BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
        }
        else {
            BlockUtil.mc.field_71442_b.func_187099_a(BlockUtil.mc.field_71439_g, BlockUtil.mc.field_71441_e, pos, direction, vec, hand);
        }
        BlockUtil.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        ((IMinecraftMixin)BlockUtil.mc).setRightClickDelayTimerAccessor(4);
    }
    
    public static EnumFacing getFirstFacing(final BlockPos pos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(pos).iterator();
        if (iterator.hasNext()) {
            final EnumFacing facing = iterator.next();
            return facing;
        }
        return null;
    }
    
    public static int isPositionPlaceable(final BlockPos pos, final boolean rayTrace) {
        return isPositionPlaceable(pos, rayTrace, true);
    }
    
    public static List<EnumFacing> getPossibleSides(final BlockPos pos) {
        final ArrayList<EnumFacing> facings = new ArrayList<EnumFacing>();
        if (BlockUtil.mc.field_71441_e == null || pos == null) {
            return facings;
        }
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbour = pos.func_177972_a(side);
            final IBlockState blockState = BlockUtil.mc.field_71441_e.func_180495_p(neighbour);
            if (blockState != null && blockState.func_177230_c().func_176209_a(blockState, false)) {
                if (!blockState.func_185904_a().func_76222_j()) {
                    facings.add(side);
                }
            }
        }
        return facings;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos pos, final boolean shouldCheck, final float height) {
        return !shouldCheck || BlockUtil.mc.field_71441_e.func_147447_a(new Vec3d(BlockUtil.mc.field_71439_g.field_70165_t, BlockUtil.mc.field_71439_g.field_70163_u + BlockUtil.mc.field_71439_g.func_70047_e(), BlockUtil.mc.field_71439_g.field_70161_v), new Vec3d((double)pos.func_177958_n(), (double)(pos.func_177956_o() + height), (double)pos.func_177952_p()), false, true, false) == null;
    }
    
    public static boolean isRightClickableBlock(final BlockPos pos) {
        return pos.equals((Object)BlockUtil.rightclickableBlocks);
    }
    
    public static boolean canBeClicked(final BlockPos pos) {
        return getBlock(pos).func_176209_a(getState(pos), false);
    }
    
    public static BlockPos[] toBlockPos(final Vec3d[] vec3ds) {
        final BlockPos[] list = new BlockPos[vec3ds.length];
        for (int i = 0; i < vec3ds.length; ++i) {
            list[i] = new BlockPos(vec3ds[i]);
        }
        return list;
    }
    
    public static List<BlockPos> getSphere(final float radius, final boolean ignoreAir) {
        final ArrayList<BlockPos> sphere = new ArrayList<BlockPos>();
        final BlockPos pos = new BlockPos(BlockUtil.mc.field_71439_g.func_174791_d());
        final int posX = pos.func_177958_n();
        final int posY = pos.func_177956_o();
        final int posZ = pos.func_177952_p();
        final int radiuss = (int)radius;
        for (int x = posX - radiuss; x <= posX + radius; ++x) {
            for (int z = posZ - radiuss; z <= posZ + radius; ++z) {
                for (int y = posY - radiuss; y < posY + radius; ++y) {
                    if ((posX - x) * (posX - x) + (posZ - z) * (posZ - z) + (posY - y) * (posY - y) < radius * radius) {
                        final BlockPos position = new BlockPos(x, y, z);
                        if (!ignoreAir || BlockUtil.mc.field_71441_e.func_180495_p(position).func_177230_c() != Blocks.field_150350_a) {
                            sphere.add(position);
                        }
                    }
                }
            }
        }
        return sphere;
    }
    
    private static Block getBlock(final BlockPos pos) {
        return getState(pos).func_177230_c();
    }
    
    private static IBlockState getState(final BlockPos pos) {
        return BlockUtil.mc.field_71441_e.func_180495_p(pos);
    }
    
    static {
        BlockUtil.emptyBlocks = Arrays.asList(Blocks.field_150350_a, (Block)Blocks.field_150356_k, (Block)Blocks.field_150353_l, (Block)Blocks.field_150358_i, (Block)Blocks.field_150355_j, Blocks.field_150395_bd, Blocks.field_150431_aC, (Block)Blocks.field_150329_H, (Block)Blocks.field_150480_ab);
        BlockUtil.actualBlocks = Arrays.asList((Block)Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150377_bs);
        BlockUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150477_bB, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.field_150467_bQ, Blocks.field_150471_bO, Blocks.field_150430_aB, (Block)Blocks.field_150441_bU, (Block)Blocks.field_150413_aR, (Block)Blocks.field_150416_aS, (Block)Blocks.field_150455_bV, Blocks.field_180390_bo, Blocks.field_180391_bp, Blocks.field_180392_bq, Blocks.field_180386_br, Blocks.field_180385_bs, Blocks.field_180387_bt, Blocks.field_150382_bo, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150442_at, Blocks.field_150323_B, Blocks.field_150421_aI, (Block)Blocks.field_150461_bJ, Blocks.field_150324_C, Blocks.field_150460_al, (Block)Blocks.field_180413_ao, (Block)Blocks.field_180414_ap, (Block)Blocks.field_180412_aq, (Block)Blocks.field_180411_ar, (Block)Blocks.field_180410_as, (Block)Blocks.field_180409_at, Blocks.field_150414_aQ, Blocks.field_150381_bn, Blocks.field_150380_bt, (Block)Blocks.field_150438_bZ, Blocks.field_185776_dc, Blocks.field_150483_bI, Blocks.field_185777_dd, Blocks.field_150462_ai);
        BlockUtil.unSafeBlocks = Arrays.asList(Blocks.field_150343_Z, Blocks.field_150357_h, Blocks.field_150477_bB, Blocks.field_150467_bQ);
        BlockUtil.holeOffsets = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, -1.0, 0.0) };
    }
}
