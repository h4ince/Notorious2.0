// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.world;

import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.util.BlockUtil;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Lawnmower", description = "Mines tall grass and stuff around you", category = Category.World)
public class Lawnmower extends Hack
{
    @RegisterSetting
    public final NumSetting delay;
    public ArrayList<BlockPos> posList;
    private BlockPos targetBlock;
    
    public Lawnmower() {
        this.delay = new NumSetting("Delay", 5.0f, 1.0f, 10.0f, 1.0f);
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final PlayerLivingUpdateEvent event) {
        this.posList = BlockUtil.getSurroundingBlocks(4, true);
        for (final BlockPos pos : this.posList) {
            if (this.isMineable(Lawnmower.mc.field_71441_e.func_180495_p(pos).func_177230_c()) && Lawnmower.mc.field_71439_g.field_70173_aa % this.delay.getValue() == 0.0) {
                BlockUtil.damageBlock(pos, false, true);
            }
        }
    }
    
    private boolean isMineable(final Block block) {
        return block == Blocks.field_150329_H || block == Blocks.field_150398_cm || block == Blocks.field_150328_O || block == Blocks.field_150327_N;
    }
}
