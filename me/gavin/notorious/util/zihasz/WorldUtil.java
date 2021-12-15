// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util.zihasz;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.util.Instance;

public class WorldUtil implements Instance
{
    public static List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> blocks = new ArrayList<BlockPos>();
        final int cx = loc.func_177958_n();
        final int cy = loc.func_177956_o();
        final int cz = loc.func_177952_p();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        blocks.add(new BlockPos(x, y + plus_y, z));
                    }
                }
            }
        }
        return blocks;
    }
    
    public static List<BlockPos> getSphere(final BlockPos loc, final float r, final boolean hollow) {
        return getSphere(loc, r, (int)r, hollow, true, 0);
    }
}
