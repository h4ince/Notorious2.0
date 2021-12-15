// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class BlockEvent extends Event
{
    private final BlockPos pos;
    
    public BlockEvent(final BlockPos pos) {
        this.pos = pos;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public static class Click extends BlockEvent
    {
        private final EnumFacing facing;
        
        public Click(final BlockPos pos, final EnumFacing facing) {
            super(pos);
            this.facing = facing;
        }
        
        public EnumFacing getFacing() {
            return this.facing;
        }
    }
    
    public static class Damage extends BlockEvent
    {
        private final EnumFacing facing;
        
        public Damage(final BlockPos pos, final EnumFacing facing) {
            super(pos);
            this.facing = facing;
        }
        
        public EnumFacing getFacing() {
            return this.facing;
        }
    }
    
    public static class Destroy extends BlockEvent
    {
        public Destroy(final BlockPos pos) {
            super(pos);
        }
    }
}
