// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import me.gavin.notorious.util.BlockUtil;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import me.gavin.notorious.util.rewrite.InventoryUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Burrow", description = "ez", category = Category.Combat)
public class Burrow extends Hack
{
    @RegisterSetting
    private final NumSetting height;
    @RegisterSetting
    private final BooleanSetting preferEChests;
    @RegisterSetting
    private final BooleanSetting lessPackets;
    private static Burrow INSTANCE;
    public BlockPos startPos;
    private int obbySlot;
    
    public Burrow() {
        this.height = new NumSetting("Height", 5.0f, -5.0f, 5.0f, 1.0f);
        this.preferEChests = new BooleanSetting("PreferEChests", true);
        this.lessPackets = new BooleanSetting("ConservePackets", false);
        this.obbySlot = -1;
        Burrow.INSTANCE = this;
    }
    
    public static Burrow getInstance() {
        return Burrow.INSTANCE;
    }
    
    public void onEnable() {
        if (Burrow.mc.field_71439_g != null && Burrow.mc.field_71441_e != null) {
            this.toggle();
            return;
        }
        this.obbySlot = InventoryUtil.findBlock(Blocks.field_150343_Z, 0, 9);
        final int eChestSlot = InventoryUtil.findBlock(Blocks.field_150477_bB, 0, 9);
        if ((this.preferEChests.getValue() || this.obbySlot == -1) && eChestSlot != -1) {
            this.obbySlot = eChestSlot;
        }
        else {
            this.obbySlot = InventoryUtil.findBlock(Blocks.field_150343_Z, 0, 9);
            if (this.obbySlot == -1) {
                this.notorious.messageManager.sendError("Toggling no blocks to place with.");
                this.disable();
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (Burrow.mc.field_71439_g != null && Burrow.mc.field_71441_e != null) {
            return;
        }
        final int startSlot = Burrow.mc.field_71439_g.field_71071_by.field_70461_c;
        Burrow.mc.func_147114_u().func_147297_a((Packet)new CPacketHeldItemChange(this.obbySlot));
        this.startPos = new BlockPos(Burrow.mc.field_71439_g.func_174791_d());
        if (this.lessPackets.getValue()) {
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 0.45, Burrow.mc.field_71439_g.field_70161_v, true));
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 0.79, Burrow.mc.field_71439_g.field_70161_v, true));
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 1.1, Burrow.mc.field_71439_g.field_70161_v, true));
        }
        else {
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 0.41, Burrow.mc.field_71439_g.field_70161_v, true));
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 0.75, Burrow.mc.field_71439_g.field_70161_v, true));
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 1.0, Burrow.mc.field_71439_g.field_70161_v, true));
            Burrow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + 1.16, Burrow.mc.field_71439_g.field_70161_v, true));
        }
        final boolean onEChest = Burrow.mc.field_71441_e.func_180495_p(new BlockPos(Burrow.mc.field_71439_g.func_174791_d())).func_177230_c() == Blocks.field_150477_bB;
        if (this.placeBlock(onEChest ? this.startPos.func_177984_a() : this.startPos) && !Burrow.mc.field_71439_g.field_71075_bZ.field_75098_d) {
            Burrow.mc.func_147114_u().func_147297_a((Packet)new CPacketPlayer.Position(Burrow.mc.field_71439_g.field_70165_t, Burrow.mc.field_71439_g.field_70163_u + this.height.getValue(), Burrow.mc.field_71439_g.field_70161_v, false));
        }
        if (startSlot != -1) {
            Burrow.mc.func_147114_u().func_147297_a((Packet)new CPacketHeldItemChange(startSlot));
        }
        this.disable();
    }
    
    public boolean placeBlock(final BlockPos pos) {
        if (!BlockUtil.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_176200_f((IBlockAccess)BlockUtil.mc.field_71441_e, pos)) {
            return false;
        }
        final boolean alreadySneaking = BlockUtil.mc.field_71439_g.func_70093_af();
        for (int i = 0; i < 6; ++i) {
            final EnumFacing side = EnumFacing.values()[i];
            final IBlockState offsetState = BlockUtil.mc.field_71441_e.func_180495_p(pos.func_177972_a(side));
            if (offsetState.func_177230_c().func_176209_a(offsetState, false)) {
                if (!offsetState.func_185904_a().func_76222_j()) {
                    final boolean activated = offsetState.func_177230_c().func_180639_a((World)BlockUtil.mc.field_71441_e, pos, BlockUtil.mc.field_71441_e.func_180495_p(pos), (EntityPlayer)BlockUtil.mc.field_71439_g, EnumHand.MAIN_HAND, side, 0.5f, 0.5f, 0.5f);
                    if (activated && !alreadySneaking) {
                        Burrow.mc.func_147114_u().func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    Burrow.mc.func_147114_u().func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos.func_177972_a(side), side.func_176734_d(), EnumHand.MAIN_HAND, 0.5f, 0.5f, 0.5f));
                    Burrow.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                    if (activated) {
                        if (!alreadySneaking) {
                            Burrow.mc.func_147114_u().func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
                        }
                    }
                }
            }
        }
        return true;
    }
}
