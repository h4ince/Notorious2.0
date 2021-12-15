// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import me.gavin.notorious.util.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
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

@RegisterHack(name = "BurrowESP", description = "ez", category = Category.Render)
public class BurrowESP extends Hack
{
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final BooleanSetting self;
    @RegisterSetting
    public final BooleanSetting obsidian;
    @RegisterSetting
    public final BooleanSetting echest;
    @RegisterSetting
    public final BooleanSetting skull;
    @RegisterSetting
    public final BooleanSetting anvil;
    @RegisterSetting
    public final BooleanSetting sand;
    public BlockPos pos;
    public boolean fill;
    public boolean outline;
    public List<BlockPos> burrowedEntities;
    
    public BurrowESP() {
        this.mode = new ModeSetting("Mode", "Both", new String[] { "Both", "Outline", "Box" });
        this.outlineColor = new ColorSetting("Outline", new Color(117, 0, 255, 255));
        this.boxColor = new ColorSetting("Box", new Color(117, 0, 255, 65));
        this.self = new BooleanSetting("Self", true);
        this.obsidian = new BooleanSetting("Obsidian", true);
        this.echest = new BooleanSetting("EChest", true);
        this.skull = new BooleanSetting("Skull", true);
        this.anvil = new BooleanSetting("Anvil", true);
        this.sand = new BooleanSetting("Sand", false);
        this.burrowedEntities = new ArrayList<BlockPos>();
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.mode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        this.burrowedEntities.clear();
        for (final Entity e : BurrowESP.mc.field_71441_e.field_72996_f) {
            if (e.equals((Object)BurrowESP.mc.field_71439_g) && !this.self.isEnabled()) {
                return;
            }
            this.pos = new BlockPos(e.field_70165_t, e.field_70163_u + 0.2, e.field_70161_v);
            if ((BurrowESP.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() != Blocks.field_150343_Z || !this.obsidian.isEnabled()) && (BurrowESP.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() != Blocks.field_150477_bB || !this.echest.isEnabled()) && (BurrowESP.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() != Blocks.field_150465_bP || !this.skull.isEnabled()) && (BurrowESP.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() != Blocks.field_150467_bQ || !this.anvil.isEnabled()) && (BurrowESP.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() != Blocks.field_150354_m || !this.sand.isEnabled())) {
                continue;
            }
            this.burrowedEntities.add(this.pos);
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
        for (final BlockPos blockPos : this.burrowedEntities) {
            if (this.outline) {
                RenderUtil.renderOutlineBB(new AxisAlignedBB(blockPos), this.outlineColor.getAsColor());
            }
            if (this.fill) {
                RenderUtil.renderFilledBB(new AxisAlignedBB(blockPos), this.boxColor.getAsColor());
            }
        }
    }
}
