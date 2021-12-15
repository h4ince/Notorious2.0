// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Fullbright", description = "Makes it fully bright", category = Category.Render)
public class Fullbright extends Hack
{
    private final List<Float> previousLevels;
    
    public Fullbright() {
        this.previousLevels = new ArrayList<Float>();
    }
    
    public void onEnable() {
        if (Fullbright.mc.field_71439_g != null && Fullbright.mc.field_71441_e != null) {
            final float[] table = Fullbright.mc.field_71441_e.field_73011_w.func_177497_p();
            if (Fullbright.mc.field_71441_e.field_73011_w != null) {
                for (final float f : table) {
                    this.previousLevels.add(f);
                }
                Arrays.fill(table, 1.0f);
            }
        }
        else {
            this.toggle();
        }
    }
    
    public void onDisable() {
        if (Fullbright.mc.field_71439_g != null && Fullbright.mc.field_71441_e != null) {
            final float[] table = Fullbright.mc.field_71441_e.field_73011_w.func_177497_p();
            for (int i = 0; i < table.length; ++i) {
                table[i] = this.previousLevels.get(i);
            }
            this.previousLevels.clear();
        }
    }
}
