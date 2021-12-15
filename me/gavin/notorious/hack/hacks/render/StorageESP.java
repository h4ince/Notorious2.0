// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityEnderChest;
import me.gavin.notorious.util.RenderUtil;
import org.lwjgl.opengl.GL11;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "StorageESP", description = "Draws a box around storage stuff.", category = Category.Render)
public class StorageESP extends Hack
{
    @RegisterSetting
    public final ModeSetting renderMode;
    @RegisterSetting
    public final ModeSetting colorMode;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final NumSetting lineWidth;
    @RegisterSetting
    public final BooleanSetting chest;
    @RegisterSetting
    public final BooleanSetting enderChest;
    @RegisterSetting
    public final BooleanSetting hopper;
    @RegisterSetting
    public final BooleanSetting shulkerBox;
    private boolean outline;
    private boolean fill;
    public final Color chestOutlineStatic;
    public final Color chestBoxStatic;
    public final Color enderChestOutlineStatic;
    public final Color enderChestBoxStatic;
    public final Color hopperOutlineStatic;
    public final Color hopperBoxStatic;
    public final Color shulkerOutlineStatic;
    public final Color shulkerBoxStatic;
    
    public StorageESP() {
        this.renderMode = new ModeSetting("RenderMode", "Both", new String[] { "Both", "Outline", "Box" });
        this.colorMode = new ModeSetting("ColorMode", "Custom", new String[] { "Static", "Custom" });
        this.outlineColor = new ColorSetting("Outline", new Color(255, 255, 255, 255));
        this.boxColor = new ColorSetting("Box", new Color(255, 255, 255, 125));
        this.lineWidth = new NumSetting("LineWidth", 2.0f, 0.1f, 4.0f, 0.1f);
        this.chest = new BooleanSetting("Chest", true);
        this.enderChest = new BooleanSetting("EnderChest", true);
        this.hopper = new BooleanSetting("Hopper", true);
        this.shulkerBox = new BooleanSetting("ShulkerBox", true);
        this.outline = false;
        this.fill = false;
        this.chestOutlineStatic = new Color(139, 69, 19, 255);
        this.chestBoxStatic = new Color(205, 133, 63, 125);
        this.enderChestOutlineStatic = new Color(75, 0, 130, 255);
        this.enderChestBoxStatic = new Color(138, 43, 226, 125);
        this.hopperOutlineStatic = new Color(105, 105, 105, 255);
        this.hopperBoxStatic = new Color(169, 169, 169, 125);
        this.shulkerOutlineStatic = new Color(199, 21, 133, 255);
        this.shulkerBoxStatic = new Color(234, 16, 130, 125);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.renderMode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        for (final TileEntity e : StorageESP.mc.field_71441_e.field_147482_g) {
            final AxisAlignedBB bb = new AxisAlignedBB(e.func_174877_v());
            if (this.renderMode.getMode().equals("Both")) {
                this.outline = true;
                this.fill = true;
            }
            else if (this.renderMode.getMode().equals("Outline")) {
                this.outline = true;
                this.fill = false;
            }
            else {
                this.fill = true;
                this.outline = false;
            }
            if (e instanceof TileEntityChest && this.chest.isEnabled()) {
                if (this.colorMode.getMode().equals("Custom")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, new Color(this.outlineColor.getAsColor().getRed(), this.outlineColor.getAsColor().getGreen(), this.outlineColor.getAsColor().getBlue(), this.outlineColor.getAsColor().getAlpha()));
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.boxColor.getAsColor());
                    }
                }
                if (this.colorMode.getMode().equals("Static")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, this.chestOutlineStatic);
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.chestBoxStatic);
                    }
                }
            }
            if (e instanceof TileEntityEnderChest && this.enderChest.isEnabled()) {
                if (this.colorMode.getMode().equals("Custom")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, this.outlineColor.getAsColor());
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.boxColor.getAsColor());
                    }
                }
                if (this.colorMode.getMode().equals("Static")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, this.enderChestOutlineStatic);
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.enderChestBoxStatic);
                    }
                }
            }
            if (e instanceof TileEntityHopper && this.hopper.isEnabled()) {
                if (this.colorMode.getMode().equals("Custom")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, this.outlineColor.getAsColor());
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.boxColor.getAsColor());
                    }
                }
                if (this.colorMode.getMode().equals("Static")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, this.hopperOutlineStatic);
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.hopperBoxStatic);
                    }
                }
            }
            if (e instanceof TileEntityShulkerBox && this.shulkerBox.isEnabled()) {
                if (this.colorMode.getMode().equals("Custom")) {
                    GL11.glLineWidth(this.lineWidth.getValue());
                    if (this.outline) {
                        RenderUtil.renderOutlineBB(bb, this.outlineColor.getAsColor());
                    }
                    if (this.fill) {
                        RenderUtil.renderFilledBB(bb, this.boxColor.getAsColor());
                    }
                }
                if (!this.colorMode.getMode().equals("Static")) {
                    continue;
                }
                GL11.glLineWidth(this.lineWidth.getValue());
                if (this.outline) {
                    RenderUtil.renderOutlineBB(bb, this.shulkerOutlineStatic);
                }
                if (!this.fill) {
                    continue;
                }
                RenderUtil.renderFilledBB(bb, this.shulkerBoxStatic);
            }
        }
    }
}
