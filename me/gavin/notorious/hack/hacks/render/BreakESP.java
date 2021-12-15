// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.math.AxisAlignedBB;
import java.awt.Color;
import me.gavin.notorious.util.RenderUtil;
import net.minecraft.world.World;
import me.gavin.notorious.util.ColorUtil;
import me.gavin.notorious.mixin.mixins.accessor.IRenderGlobal;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "BreakESP", description = "shows break progress", category = Category.Render)
public class BreakESP extends Hack
{
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
    @RegisterSetting
    public final NumSetting range;
    @RegisterSetting
    public final BooleanSetting fade;
    boolean outline;
    boolean fill;
    
    public BreakESP() {
        this.mode = new ModeSetting("Mode", "Outline", new String[] { "Both", "Outline", "Box" });
        this.outlineColor = new ColorSetting("OutlineColor", 255, 255, 255, 125);
        this.boxColor = new ColorSetting("BoxColor", 255, 255, 255, 125);
        this.rainbow = new BooleanSetting("Rainbow", true);
        this.saturation = new NumSetting("Saturation", 0.6f, 0.1f, 1.0f, 0.1f);
        this.time = new NumSetting("RainbowLength", 8.0f, 1.0f, 15.0f, 1.0f);
        this.range = new NumSetting("Range", 15.0f, 1.0f, 20.0f, 1.0f);
        this.fade = new BooleanSetting("Fade", true);
        this.outline = false;
        this.fill = false;
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.mode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        final Color rainbowColor;
        AxisAlignedBB pos;
        ((IRenderGlobal)BreakESP.mc.field_71438_f).getDamagedBlocks().forEach((integer, destroyBlockProgress) -> {
            rainbowColor = ColorUtil.colorRainbow((int)this.time.getValue(), this.saturation.getValue(), 1.0f);
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
            if (destroyBlockProgress.func_180246_b().func_185332_f((int)BreakESP.mc.field_71439_g.field_70165_t, (int)BreakESP.mc.field_71439_g.field_70163_u, (int)BreakESP.mc.field_71439_g.field_70161_v) <= this.range.getValue()) {
                pos = BreakESP.mc.field_71441_e.func_180495_p(destroyBlockProgress.func_180246_b()).func_185918_c((World)BreakESP.mc.field_71441_e, destroyBlockProgress.func_180246_b());
                if (this.fade.isEnabled()) {
                    pos = pos.func_186664_h((3.0 - destroyBlockProgress.func_73106_e() / 2.6666666666666665) / 9.0);
                }
                if (this.outline) {
                    if (this.rainbow.isEnabled()) {
                        RenderUtil.renderOutlineBB(pos, rainbowColor);
                    }
                    else {
                        RenderUtil.renderOutlineBB(pos, this.outlineColor.getAsColor());
                    }
                }
                if (this.fill) {
                    if (this.rainbow.isEnabled()) {
                        RenderUtil.renderFilledBB(pos, rainbowColor);
                    }
                    else {
                        RenderUtil.renderFilledBB(pos, this.boxColor.getAsColor());
                    }
                }
            }
        });
    }
}
