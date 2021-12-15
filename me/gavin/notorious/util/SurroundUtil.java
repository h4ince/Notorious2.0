// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import java.util.List;
import net.minecraft.client.Minecraft;

public class SurroundUtil
{
    private static final Minecraft mc;
    public static List<Block> emptyBlocks;
    public static List<Block> rightclickableBlocks;
    
    public static boolean canSeeBlock(final BlockPos p_Pos) {
        return SurroundUtil.mc.field_71439_g != null && SurroundUtil.mc.field_71441_e.func_147447_a(new Vec3d(SurroundUtil.mc.field_71439_g.field_70165_t, SurroundUtil.mc.field_71439_g.field_70163_u + SurroundUtil.mc.field_71439_g.func_70047_e(), SurroundUtil.mc.field_71439_g.field_70161_v), new Vec3d((double)p_Pos.func_177958_n(), (double)p_Pos.func_177956_o(), (double)p_Pos.func_177952_p()), false, true, false) == null;
    }
    
    public static void placeCrystalOnBlock(final BlockPos pos, final EnumHand hand) {
        final RayTraceResult result = SurroundUtil.mc.field_71441_e.func_72933_a(new Vec3d(SurroundUtil.mc.field_71439_g.field_70165_t, SurroundUtil.mc.field_71439_g.field_70163_u + SurroundUtil.mc.field_71439_g.func_70047_e(), SurroundUtil.mc.field_71439_g.field_70161_v), new Vec3d(pos.func_177958_n() + 0.5, pos.func_177956_o() - 0.5, pos.func_177952_p() + 0.5));
        final EnumFacing facing = (result == null || result.field_178784_b == null) ? EnumFacing.UP : result.field_178784_b;
        SurroundUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, facing, hand, 0.0f, 0.0f, 0.0f));
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos pos, final boolean shouldCheck, final float height) {
        return !shouldCheck || SurroundUtil.mc.field_71441_e.func_147447_a(new Vec3d(SurroundUtil.mc.field_71439_g.field_70165_t, SurroundUtil.mc.field_71439_g.field_70163_u + SurroundUtil.mc.field_71439_g.func_70047_e(), SurroundUtil.mc.field_71439_g.field_70161_v), new Vec3d((double)pos.func_177958_n(), (double)(pos.func_177956_o() + height), (double)pos.func_177952_p()), false, true, false) == null;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos pos, final boolean shouldCheck) {
        return rayTracePlaceCheck(pos, shouldCheck, 1.0f);
    }
    
    public static void openBlock(final BlockPos pos) {
        final EnumFacing[] values;
        final EnumFacing[] facings = values = EnumFacing.values();
        for (final EnumFacing f : values) {
            final Block neighborBlock = SurroundUtil.mc.field_71441_e.func_180495_p(pos.func_177972_a(f)).func_177230_c();
            if (SurroundUtil.emptyBlocks.contains(neighborBlock)) {
                SurroundUtil.mc.field_71442_b.func_187099_a(SurroundUtil.mc.field_71439_g, SurroundUtil.mc.field_71441_e, pos, f.func_176734_d(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    static {
        mc = Minecraft.func_71410_x();
        SurroundUtil.emptyBlocks = Arrays.asList(Blocks.field_150350_a, (Block)Blocks.field_150356_k, (Block)Blocks.field_150353_l, (Block)Blocks.field_150358_i, (Block)Blocks.field_150355_j, Blocks.field_150395_bd, Blocks.field_150431_aC, (Block)Blocks.field_150329_H, (Block)Blocks.field_150480_ab);
        SurroundUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150477_bB, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.field_150467_bQ, Blocks.field_150471_bO, Blocks.field_150430_aB, (Block)Blocks.field_150441_bU, (Block)Blocks.field_150413_aR, (Block)Blocks.field_150416_aS, (Block)Blocks.field_150455_bV, Blocks.field_180390_bo, Blocks.field_180391_bp, Blocks.field_180392_bq, Blocks.field_180386_br, Blocks.field_180385_bs, Blocks.field_180387_bt, Blocks.field_150382_bo, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150442_at, Blocks.field_150323_B, Blocks.field_150421_aI, (Block)Blocks.field_150461_bJ, Blocks.field_150324_C, Blocks.field_150460_al, (Block)Blocks.field_180413_ao, (Block)Blocks.field_180414_ap, (Block)Blocks.field_180412_aq, (Block)Blocks.field_180411_ar, (Block)Blocks.field_180410_as, (Block)Blocks.field_180409_at, Blocks.field_150414_aQ, Blocks.field_150381_bn, Blocks.field_150380_bt, (Block)Blocks.field_150438_bZ, Blocks.field_185776_dc, Blocks.field_150483_bI, Blocks.field_185777_dd, Blocks.field_150462_ai);
    }
}
