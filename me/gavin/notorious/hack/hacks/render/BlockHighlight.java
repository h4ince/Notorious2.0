// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.math.AxisAlignedBB;
import java.awt.Color;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import me.gavin.notorious.util.RenderUtil;
import org.lwjgl.opengl.GL11;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.util.math.RayTraceResult;
import me.gavin.notorious.util.ColorUtil;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.util.NColor;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "BlockHighlight", description = "Draws a bounding box around boxes you are looking at.", category = Category.Render)
public class BlockHighlight extends Hack
{
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final ModeSetting colorMode;
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
    @RegisterSetting
    public final NumSetting lineWidth;
    boolean outline;
    boolean fill;
    
    public BlockHighlight() {
        this.mode = new ModeSetting("Mode", "Outline", new String[] { "Both", "Outline", "Box" });
        this.colorMode = new ModeSetting("ColorMode", "ClientSync", new String[] { "ClientSync", "RGB" });
        this.outlineColor = new ColorSetting("OutlineColor", new NColor(255, 255, 255));
        this.boxColor = new ColorSetting("BoxColor", 255, 255, 255, 125);
        this.rainbow = new BooleanSetting("Rainbow", true);
        this.saturation = new NumSetting("Saturation", 0.6f, 0.1f, 1.0f, 0.1f);
        this.time = new NumSetting("RainbowLength", 8.0f, 1.0f, 15.0f, 1.0f);
        this.lineWidth = new NumSetting("Line Width", 2.0f, 0.1f, 10.0f, 0.1f);
        this.outline = false;
        this.fill = false;
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.lineWidth.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        final RayTraceResult result = BlockHighlight.mc.field_71476_x;
        final Color rainbowColor = ColorUtil.colorRainbow((int)this.time.getValue(), this.saturation.getValue(), 1.0f);
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
        if (result != null && result.field_72313_a == RayTraceResult.Type.BLOCK) {
            final AxisAlignedBB box = BlockHighlight.mc.field_71441_e.func_180495_p(result.func_178782_a()).func_185918_c((World)BlockHighlight.mc.field_71441_e, result.func_178782_a());
            if (result.field_72313_a == RayTraceResult.Type.BLOCK && BlockHighlight.mc.field_71441_e.func_180495_p(result.func_178782_a()).func_177230_c() != Blocks.field_150350_a) {
                GL11.glLineWidth(this.lineWidth.getValue());
                if (this.rainbow.isEnabled()) {
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(box, rainbowColor);
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(box, rainbowColor);
                    }
                }
                else {
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(box, this.colorMode.getMode().equals("RGB") ? this.outlineColor.getAsColor() : Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor());
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(box, this.colorMode.getMode().equals("RGB") ? this.boxColor.getAsColor() : Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor());
                    }
                }
            }
        }
    }
}
