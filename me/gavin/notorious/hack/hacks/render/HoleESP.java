// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3i;
import net.minecraft.init.Blocks;
import java.util.Iterator;
import me.gavin.notorious.util.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;
import java.awt.Color;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.util.BlockUtil;
import java.util.ArrayList;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "HoleESP", description = "ez", category = Category.Render)
public class HoleESP extends Hack
{
    @RegisterSetting
    public final NumSetting range;
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ColorSetting bedrockOutlineColor;
    @RegisterSetting
    public final ColorSetting bedrockFillColor;
    @RegisterSetting
    public final ColorSetting obsidianOutlineColor;
    @RegisterSetting
    public final ColorSetting obsidianFillColor;
    private List<BlockPos> holes;
    public boolean fill;
    public boolean outline;
    private final BlockPos[] surroundOffset;
    
    public HoleESP() {
        this.range = new NumSetting("Range", 5.0f, 1.0f, 16.0f, 1.0f);
        this.mode = new ModeSetting("Mode", "Both", new String[] { "Both", "Outline", "Box" });
        this.bedrockOutlineColor = new ColorSetting("SafeOutlineColor", 255, 0, 0, 255);
        this.bedrockFillColor = new ColorSetting("SafeFillColor", 255, 0, 0, 125);
        this.obsidianOutlineColor = new ColorSetting("UnSafeOutlineColor", 0, 0, 255, 255);
        this.obsidianFillColor = new ColorSetting("UnSafeFillColor", 0, 0, 255, 125);
        this.holes = new ArrayList<BlockPos>();
        this.surroundOffset = BlockUtil.toBlockPos(BlockUtil.holeOffsets);
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (HoleESP.mc.field_71439_g.field_70173_aa % 2 == 0) {
            return;
        }
        this.holes = this.calcHoles();
    }
    
    @SubscribeEvent
    public void onRender3D(final RenderWorldLastEvent event) {
        final int size = this.holes.size();
        for (final BlockPos pos : this.holes) {
            final Color fillColor = this.isSafe(pos) ? new Color(this.bedrockFillColor.getAsColor().getRed(), this.bedrockFillColor.getAsColor().getGreen(), this.bedrockFillColor.getAsColor().getBlue(), 125) : new Color(this.obsidianFillColor.getAsColor().getRed(), this.obsidianFillColor.getAsColor().getGreen(), this.obsidianFillColor.getAsColor().getBlue(), 125);
            final Color outlineColor = this.isSafe(pos) ? new Color(this.bedrockOutlineColor.getAsColor().getRed(), this.bedrockOutlineColor.getAsColor().getGreen(), this.bedrockOutlineColor.getAsColor().getBlue()) : new Color(this.obsidianOutlineColor.getAsColor().getRed(), this.obsidianOutlineColor.getAsColor().getGreen(), this.obsidianOutlineColor.getAsColor().getBlue());
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
            if (this.fill) {
                RenderUtil.renderFilledBB(new AxisAlignedBB(pos), fillColor);
            }
            if (this.outline) {
                RenderUtil.renderOutlineBB(new AxisAlignedBB(pos), outlineColor);
            }
        }
    }
    
    public List<BlockPos> calcHoles() {
        final ArrayList<BlockPos> safeSpots = new ArrayList<BlockPos>();
        final List<BlockPos> positions = BlockUtil.getSphere(this.range.getValue(), false);
        final int size = positions.size();
        for (final BlockPos pos : positions) {
            if (HoleESP.mc.field_71441_e.func_180495_p(pos).func_177230_c().equals(Blocks.field_150350_a) && HoleESP.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 0)).func_177230_c().equals(Blocks.field_150350_a)) {
                if (!HoleESP.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150350_a)) {
                    continue;
                }
                boolean isSafe = true;
                for (final BlockPos offset : this.surroundOffset) {
                    final Block block = HoleESP.mc.field_71441_e.func_180495_p(pos.func_177971_a((Vec3i)offset)).func_177230_c();
                    if (block != Blocks.field_150357_h) {
                        if (block != Blocks.field_150343_Z) {
                            isSafe = false;
                        }
                    }
                }
                if (!isSafe) {
                    continue;
                }
                safeSpots.add(pos);
            }
        }
        return safeSpots;
    }
    
    private boolean isSafe(final BlockPos pos) {
        boolean isSafe = true;
        for (final BlockPos offset : this.surroundOffset) {
            if (HoleESP.mc.field_71441_e.func_180495_p(pos.func_177971_a((Vec3i)offset)).func_177230_c() != Blocks.field_150357_h) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }
}
