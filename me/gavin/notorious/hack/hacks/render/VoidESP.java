// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.init.Blocks;
import java.awt.Color;
import net.minecraft.util.math.AxisAlignedBB;
import me.gavin.notorious.util.RenderUtil;
import me.gavin.notorious.util.ColorUtil;
import net.minecraft.world.World;
import java.util.Collection;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.math.Vec3i;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "VoidESP", description = "shows void holes", category = Category.Render)
public class VoidESP extends Hack
{
    @RegisterSetting
    public final NumSetting range;
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final BooleanSetting rainbow;
    @RegisterSetting
    public final NumSetting saturation;
    @RegisterSetting
    public final NumSetting time;
    public final ArrayList<BlockPos> voidBlocks;
    boolean outline;
    boolean fill;
    
    public VoidESP() {
        this.range = new NumSetting("Range", 15.0f, 1.0f, 20.0f, 1.0f);
        this.mode = new ModeSetting("Mode", "Outline", new String[] { "Both", "Outline", "Box" });
        this.outlineColor = new ColorSetting("OutlineColor", 255, 255, 255, 125);
        this.boxColor = new ColorSetting("BoxColor", 255, 255, 255, 125);
        this.rainbow = new BooleanSetting("Rainbow", true);
        this.saturation = new NumSetting("Saturation", 0.6f, 0.1f, 1.0f, 0.1f);
        this.time = new NumSetting("RainbowLength", 8.0f, 1.0f, 15.0f, 1.0f);
        this.voidBlocks = new ArrayList<BlockPos>();
        this.outline = false;
        this.fill = false;
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.range.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (VoidESP.mc.field_71439_g == null) {
            return;
        }
        this.voidBlocks.clear();
        final Vec3i player_pos = new Vec3i(VoidESP.mc.field_71439_g.field_70165_t, VoidESP.mc.field_71439_g.field_70163_u, VoidESP.mc.field_71439_g.field_70161_v);
        for (int x = (int)(player_pos.func_177958_n() - this.range.getValue()); x < player_pos.func_177958_n() + this.range.getValue(); ++x) {
            for (int z = (int)(player_pos.func_177952_p() - this.range.getValue()); z < player_pos.func_177952_p() + this.range.getValue(); ++z) {
                for (int y = (int)(player_pos.func_177956_o() + this.range.getValue()); y > player_pos.func_177956_o() - this.range.getValue(); --y) {
                    final BlockPos blockPos = new BlockPos(x, y, z);
                    if (this.is_void_hole(blockPos)) {
                        this.voidBlocks.add(blockPos);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.mode.getMode().equals("Both")) {
            this.outline = true;
            this.fill = true;
        }
        else if (this.mode.getMode().equals("Outline")) {
            this.outline = true;
            this.fill = false;
        }
        else {
            this.fill = true;
            this.outline = false;
        }
        final AxisAlignedBB bb;
        final Color rainbowColor;
        new ArrayList(this.voidBlocks).forEach(blockPos -> {
            bb = VoidESP.mc.field_71441_e.func_180495_p(blockPos).func_185918_c((World)VoidESP.mc.field_71441_e, blockPos);
            rainbowColor = ColorUtil.colorRainbow((int)this.time.getValue(), this.saturation.getValue(), 1.0f);
            if (this.outline) {
                if (this.rainbow.isEnabled()) {
                    RenderUtil.renderOutlineBB(bb, rainbowColor);
                }
                else {
                    RenderUtil.renderOutlineBB(bb, this.outlineColor.getAsColor());
                }
            }
            if (this.fill) {
                if (this.rainbow.isEnabled()) {
                    RenderUtil.renderFilledBB(bb, rainbowColor);
                }
                else {
                    RenderUtil.renderFilledBB(bb, this.boxColor.getAsColor());
                }
            }
        });
    }
    
    public boolean is_void_hole(final BlockPos blockPos) {
        return blockPos.func_177956_o() == 0 && VoidESP.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150350_a;
    }
}
