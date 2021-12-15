// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util.rewrite;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import me.gavin.notorious.stuff.Minecraft;

public final class RenderUtil implements Minecraft
{
    public static void prepareGL() {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
    }
    
    public static void releaseGL() {
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static AxisAlignedBB generateBB(final long x, final long y, final long z) {
        final BlockPos blockPos = new BlockPos((double)x, (double)y, (double)z);
        return new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, blockPos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
    }
    
    public static void draw(final BlockPos blockPos, final boolean box, final boolean outline, final double boxHeight, final double outlineHeight, final Color colour) {
        final AxisAlignedBB axisAlignedBB = generateBB(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
        prepareGL();
        if (box) {
            drawFilledBox(axisAlignedBB, boxHeight, colour.getRed() / 255.0f, colour.getGreen() / 255.0f, colour.getBlue() / 255.0f, colour.getAlpha() / 255.0f);
        }
        if (outline) {
            drawBoundingBox(axisAlignedBB, outlineHeight, colour.getRed() / 255.0f, colour.getGreen() / 255.0f, colour.getBlue() / 255.0f, colour.getAlpha() / 255.0f);
        }
        releaseGL();
    }
    
    public static void drawBoundingBox(final BufferBuilder bufferBuilder, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final float red, final float green, final float blue, final float alpha) {
        bufferBuilder.func_181662_b(minX, minY, minZ).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferBuilder.func_181662_b(minX, minY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, minY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, minY, maxZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(minX, minY, maxZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(minX, minY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(minX, maxY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, maxY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, maxY, maxZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(minX, maxY, maxZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(minX, maxY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(minX, maxY, maxZ).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferBuilder.func_181662_b(minX, minY, maxZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, maxY, maxZ).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferBuilder.func_181662_b(maxX, minY, maxZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, maxY, minZ).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferBuilder.func_181662_b(maxX, minY, minZ).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(maxX, minY, minZ).func_181666_a(red, green, blue, 0.0f).func_181675_d();
    }
    
    public static void addChainedFilledBoxVertices(final BufferBuilder bufferBuilder, final double x1, final double y1, final double z1, final double x2, final double y2, final double z2, final float red, final float green, final float blue, final float alpha) {
        bufferBuilder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferBuilder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
    }
    
    public static void renderFilledBox(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final double height, final float red, final float green, final float blue, final float alpha) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        addChainedFilledBoxVertices(bufferbuilder, minX, minY, minZ, maxX, maxY + height, maxZ, red, green, blue, alpha);
        tessellator.func_78381_a();
    }
    
    public static void renderBoundingBox(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final double height, final float red, final float green, final float blue, final float alpha) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        drawBoundingBox(bufferbuilder, minX, minY, minZ, maxX, maxY + height, maxZ, red, green, blue, alpha);
        tessellator.func_78381_a();
    }
    
    public static void drawFilledBox(final AxisAlignedBB axisAlignedBB, final double height, final float red, final float green, final float blue, final float alpha) {
        renderFilledBox(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f, height, red, green, blue, alpha);
    }
    
    public static void drawBoundingBox(final AxisAlignedBB axisAlignedBB, final double height, final float red, final float green, final float blue, final float alpha) {
        renderBoundingBox(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f, height, red, green, blue, alpha);
    }
}
