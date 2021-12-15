// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BlockRenderEvent extends Event
{
    private final BlockPos blockPos;
    
    public BlockRenderEvent(final BlockPos blockPos) {
        this.blockPos = blockPos;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
}
