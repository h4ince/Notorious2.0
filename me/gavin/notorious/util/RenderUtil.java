// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import me.gavin.notorious.stuff.IMinecraft;

public class RenderUtil implements IMinecraft
{
    private static final Frustum frustrum;
    public static final Tessellator tessellator;
    
    public static void prepare() {
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179129_p();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
    }
    
    public static void prepare2D() {
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    }
    
    public static void release() {
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179089_o();
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
    }
    
    public static void release2D() {
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static AxisAlignedBB generateBB(final long x, final long y, final long z) {
        final BlockPos blockPos = new BlockPos((double)x, (double)y, (double)z);
        final AxisAlignedBB bb = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, blockPos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        return bb;
    }
    
    public static void renderFilledBB(final AxisAlignedBB box, final Color color) {
        renderBB(box, color, RenderMode.FILLED);
    }
    
    public static void renderOutlineBB(final AxisAlignedBB box, final Color color) {
        renderBB(box, color, RenderMode.OUTLINE);
    }
    
    public static void drawBorderedRect(final int left, final int top, final int right, final int bottom, final int lineWidth, final Color borderColor, final Color insideColor) {
        Gui.func_73734_a(left, top, left + right, top + bottom, insideColor.getRGB());
        Gui.func_73734_a(left, top, left + lineWidth, top + bottom, borderColor.getRGB());
        Gui.func_73734_a(left, top, left + lineWidth, top + bottom, borderColor.getRGB());
    }
    
    public static void renderBB(AxisAlignedBB box, final Color color, final RenderMode mode) {
        prepare();
        final float r = color.getRed() / 255.0f;
        final float g = color.getGreen() / 255.0f;
        final float b = color.getBlue() / 255.0f;
        final float a = color.getAlpha() / 255.0f;
        box = box.func_72317_d(-RenderUtil.mc.func_175598_ae().field_78730_l, -RenderUtil.mc.func_175598_ae().field_78731_m, -RenderUtil.mc.func_175598_ae().field_78728_n);
        if (mode == RenderMode.FILLED) {
            RenderGlobal.func_189695_b(box.field_72340_a, box.field_72338_b, box.field_72339_c, box.field_72336_d, box.field_72337_e, box.field_72334_f, r, g, b, a);
        }
        else {
            RenderGlobal.func_189694_a(box.field_72340_a, box.field_72338_b, box.field_72339_c, box.field_72336_d, box.field_72337_e, box.field_72334_f, r, g, b, a);
        }
        release();
    }
    
    public static void entityESPBox(final Entity entity, final Color boxC, final Color outlineC, final int lineWidth) {
        final AxisAlignedBB ebox = entity.func_174813_aQ();
        final double lerpX = MathUtil.lerp(RenderUtil.mc.func_184121_ak(), entity.field_70142_S, entity.field_70165_t);
        final double lerpY = MathUtil.lerp(RenderUtil.mc.func_184121_ak(), entity.field_70137_T, entity.field_70163_u);
        final double lerpZ = MathUtil.lerp(RenderUtil.mc.func_184121_ak(), entity.field_70136_U, entity.field_70161_v);
        final AxisAlignedBB lerpBox = new AxisAlignedBB(ebox.field_72340_a - 0.05 - lerpX + (lerpX - RenderUtil.mc.func_175598_ae().field_78730_l), ebox.field_72338_b - lerpY + (lerpY - RenderUtil.mc.func_175598_ae().field_78731_m), ebox.field_72339_c - 0.05 - lerpZ + (lerpZ - RenderUtil.mc.func_175598_ae().field_78728_n), ebox.field_72336_d + 0.05 - lerpX + (lerpX - RenderUtil.mc.func_175598_ae().field_78730_l), ebox.field_72337_e + 0.1 - lerpY + (lerpY - RenderUtil.mc.func_175598_ae().field_78731_m), ebox.field_72334_f + 0.05 - lerpZ + (lerpZ - RenderUtil.mc.func_175598_ae().field_78728_n));
        prepare();
        GL11.glLineWidth((float)lineWidth);
        RenderGlobal.func_189696_b(lerpBox, boxC.getRed() / 255.0f, boxC.getGreen() / 255.0f, boxC.getBlue() / 255.0f, boxC.getAlpha() / 255.0f);
        RenderGlobal.func_189697_a(lerpBox, outlineC.getRed() / 255.0f, outlineC.getGreen() / 255.0f, outlineC.getBlue() / 255.0f, outlineC.getAlpha() / 255.0f);
        release();
    }
    
    public static void drawMultiColoredRect(final float left, final float top, final float right, final float bottom, final Color topleftcolor, final Color toprightcolor, final Color bottomleftcolor, final Color bottomrightcolor) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j(7425);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)right, (double)top, 0.0).func_181669_b(toprightcolor.getRed(), toprightcolor.getGreen(), toprightcolor.getBlue(), toprightcolor.getAlpha()).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)top, 0.0).func_181669_b(topleftcolor.getRed(), topleftcolor.getGreen(), topleftcolor.getBlue(), topleftcolor.getAlpha()).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)bottom, 0.0).func_181669_b(bottomleftcolor.getRed(), bottomleftcolor.getGreen(), bottomleftcolor.getBlue(), bottomleftcolor.getAlpha()).func_181675_d();
        bufferbuilder.func_181662_b((double)right, (double)bottom, 0.0).func_181669_b(bottomrightcolor.getRed(), bottomrightcolor.getGreen(), bottomrightcolor.getBlue(), bottomrightcolor.getAlpha()).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
    
    public static void drawPenis(final EntityPlayer player, final double x, final double y, final double z, final float pspin, final float pcumsize, final float pamount) {
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GL11.glLineWidth(1.0f);
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(-player.field_70177_z, 0.0f, player.field_70131_O, 0.0f);
        GL11.glTranslated(-x, -y, -z);
        GL11.glTranslated(x, y + player.field_70131_O / 2.0f - 0.22499999403953552, z);
        GL11.glColor4f(1.38f, 0.55f, 2.38f, 1.0f);
        GL11.glRotated((double)((player.func_70093_af() ? 35 : 0) + pspin), (double)(1.0f + pspin), 0.0, (double)pcumsize);
        GL11.glTranslated(0.0, 0.0, 0.07500000298023224);
        final Cylinder shaft = new Cylinder();
        shaft.setDrawStyle(100013);
        shaft.draw(0.1f, 0.11f, 0.4f, 25, 20);
        GL11.glTranslated(0.0, 0.0, -0.12500000298023223);
        GL11.glTranslated(-0.09000000074505805, 0.0, 0.0);
        final Sphere right = new Sphere();
        right.setDrawStyle(100013);
        right.draw(0.14f, 10, 20);
        GL11.glTranslated(0.16000000149011612, 0.0, 0.0);
        final Sphere left = new Sphere();
        left.setDrawStyle(100013);
        left.draw(0.14f, 10, 20);
        GL11.glColor4f(1.35f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslated(-0.07000000074505806, 0.0, 0.589999952316284);
        final Sphere tip = new Sphere();
        tip.setDrawStyle(100013);
        tip.draw(0.13f, 15, 20);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
    
    public static void drawSideGradientRect(final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j(7425);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)right, (double)top, 0.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)top, 0.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)bottom, 0.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        bufferbuilder.func_181662_b((double)right, (double)bottom, 0.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
    
    public static void drawGradientRect(final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        final float s = (startColor >> 24 & 0xFF) / 255.0f;
        final float s2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float s3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float s4 = (startColor & 0xFF) / 255.0f;
        final float f4 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f5 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f7 = (endColor & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j(7425);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)right, (double)top, 0.0).func_181666_a(s2, s3, s4, s).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)top, 0.0).func_181666_a(s2, s3, s4, s).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)bottom, 0.0).func_181666_a(f5, f6, f7, f4).func_181675_d();
        bufferbuilder.func_181662_b((double)right, (double)bottom, 0.0).func_181666_a(f5, f6, f7, f4).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
    
    public static void drawBorderedRect(float x, float y, float x1, float y1, final int insideC, final int borderC) {
        prepare2D();
        x *= 2.0f;
        x1 *= 2.0f;
        y *= 2.0f;
        y1 *= 2.0f;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(x, y, y1 - 1.0f, borderC);
        drawVLine(x1 - 1.0f, y, y1, borderC);
        drawHLine(x, x1 - 1.0f, y, borderC);
        drawHLine(x, x1 - 2.0f, y1 - 1.0f, borderC);
        drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        release2D();
    }
    
    public static void drawVLine(final float x, float y, float x1, final int y1) {
        if (x1 < y) {
            final float var5 = y;
            y = x1;
            x1 = var5;
        }
        drawRect(x, y + 1.0f, x + 1.0f, x1, y1);
    }
    
    public static void drawHLine(float x, float y, final float x1, final int y1) {
        if (y < x) {
            final float var5 = x;
            x = y;
            y = var5;
        }
        drawRect(x, x1, y + 1.0f, x1 + 1.0f, y1);
    }
    
    public static void drawRect(final float x, final float y, final float x1, final float y1, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        final BufferBuilder builder = RenderUtil.tessellator.func_178180_c();
        builder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        builder.func_181662_b((double)x, (double)y1, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b((double)x1, (double)y1, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b((double)x1, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        RenderUtil.tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static boolean isInViewFrustrum(final AxisAlignedBB bb) {
        final Entity current = Minecraft.func_71410_x().func_175606_aa();
        RenderUtil.frustrum.func_78547_a(Objects.requireNonNull(current).field_70165_t, current.field_70163_u, current.field_70161_v);
        return RenderUtil.frustrum.func_78546_a(bb);
    }
    
    static {
        frustrum = new Frustum();
        tessellator = Tessellator.func_178181_a();
    }
    
    private enum RenderMode
    {
        FILLED, 
        OUTLINE;
    }
}
