// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import me.gavin.notorious.util.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "FuckedDetector", description = "ez", category = Category.Render)
public class FuckedDetector extends Hack
{
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final BooleanSetting self;
    public BlockPos pos;
    public boolean fill;
    public boolean outline;
    public List<BlockPos> fuckedEntities;
    
    public FuckedDetector() {
        this.mode = new ModeSetting("Mode", "Both", new String[] { "Both", "Outline", "Box" });
        this.outlineColor = new ColorSetting("Outline", new Color(255, 255, 255, 255));
        this.boxColor = new ColorSetting("Box", new Color(255, 255, 255, 125));
        this.self = new BooleanSetting("Self", true);
        this.fuckedEntities = new ArrayList<BlockPos>();
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.mode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        this.fuckedEntities.clear();
        for (final EntityPlayer e : FuckedDetector.mc.field_71441_e.field_73010_i) {
            if (e.equals((Object)FuckedDetector.mc.field_71439_g) && !this.self.isEnabled()) {
                return;
            }
            this.pos = new BlockPos(e.field_70165_t, e.field_70163_u, e.field_70161_v);
            if (!this.isFucked(e)) {
                continue;
            }
            this.fuckedEntities.add(this.pos);
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
        for (final BlockPos blockPos : this.fuckedEntities) {
            if (this.outline) {
                RenderUtil.renderOutlineBB(new AxisAlignedBB(blockPos), this.outlineColor.getAsColor());
            }
            if (this.fill) {
                RenderUtil.renderFilledBB(new AxisAlignedBB(blockPos), this.boxColor.getAsColor());
            }
        }
    }
    
    public boolean canPlaceCrystal(final BlockPos pos) {
        final Block block = FuckedDetector.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        if (block == Blocks.field_150343_Z || block == Blocks.field_150357_h) {
            final Block floor = FuckedDetector.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 0)).func_177230_c();
            final Block ceil = FuckedDetector.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 2, 0)).func_177230_c();
            if (floor == Blocks.field_150350_a && ceil == Blocks.field_150350_a && FuckedDetector.mc.field_71441_e.func_72839_b((Entity)null, new AxisAlignedBB(pos.func_177982_a(0, 1, 0))).isEmpty() && FuckedDetector.mc.field_71441_e.func_72839_b((Entity)null, new AxisAlignedBB(pos.func_177982_a(0, 2, 0))).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFucked(final EntityPlayer player) {
        final BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u - 1.0, player.field_70161_v);
        return this.canPlaceCrystal(pos.func_177968_d()) || (this.canPlaceCrystal(pos.func_177968_d().func_177968_d()) && FuckedDetector.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 1)).func_177230_c() == Blocks.field_150350_a) || (this.canPlaceCrystal(pos.func_177974_f()) || (this.canPlaceCrystal(pos.func_177974_f().func_177974_f()) && FuckedDetector.mc.field_71441_e.func_180495_p(pos.func_177982_a(1, 1, 0)).func_177230_c() == Blocks.field_150350_a)) || (this.canPlaceCrystal(pos.func_177976_e()) || (this.canPlaceCrystal(pos.func_177976_e().func_177976_e()) && FuckedDetector.mc.field_71441_e.func_180495_p(pos.func_177982_a(-1, 1, 0)).func_177230_c() == Blocks.field_150350_a)) || (this.canPlaceCrystal(pos.func_177978_c()) || (this.canPlaceCrystal(pos.func_177978_c().func_177978_c()) && FuckedDetector.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, -1)).func_177230_c() == Blocks.field_150350_a));
    }
}
