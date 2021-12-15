// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import java.awt.Color;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.event.events.TotemPopEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import java.util.Map;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import java.util.HashMap;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "PopESP", description = "Renders pops", category = Category.Render)
public class PopESP extends Hack
{
    @RegisterSetting
    public final NumSetting fadeTime;
    @RegisterSetting
    public final NumSetting fadeSpeed;
    @RegisterSetting
    public final ModeSetting fadeMode;
    @RegisterSetting
    public final ModeSetting elevatorMode;
    @RegisterSetting
    public final ModeSetting renderMode;
    @RegisterSetting
    private final NumSetting lineWidth;
    @RegisterSetting
    public final NumSetting r;
    @RegisterSetting
    public final NumSetting g;
    @RegisterSetting
    public final NumSetting b;
    @RegisterSetting
    public final NumSetting a;
    private static final HashMap<EntityOtherPlayerMP, Long> popFakePlayerMap;
    float fade;
    
    public PopESP() {
        this.fadeTime = new NumSetting("FadeTime", 3000.0f, 1.0f, 5000.0f, 100.0f);
        this.fadeSpeed = new NumSetting("FadeSpeed", 0.05f, 0.01f, 1.0f, 0.01f);
        this.fadeMode = new ModeSetting("FadeMode", "Elevator", new String[] { "Elevator", "Fade", "None" });
        this.elevatorMode = new ModeSetting("ElevatorMode", "Heaven", new String[] { "Heaven", "Hell" });
        this.renderMode = new ModeSetting("RenderMode", "Both", new String[] { "Both", "Textured", "Wireframe" });
        this.lineWidth = new NumSetting("Line Width", 1.0f, 0.1f, 3.0f, 0.1f);
        this.r = new NumSetting("Red", 255.0f, 0.0f, 255.0f, 1.0f);
        this.g = new NumSetting("Green", 255.0f, 0.0f, 255.0f, 1.0f);
        this.b = new NumSetting("Blue", 255.0f, 0.0f, 255.0f, 1.0f);
        this.a = new NumSetting("Alpha", 255.0f, 0.0f, 255.0f, 1.0f);
        this.fade = 1.0f;
    }
    
    @Override
    public String getMetaData() {
        if (this.fadeMode.getMode().equals("Elevator")) {
            return " [" + ChatFormatting.GRAY + this.elevatorMode.getMode() + ChatFormatting.RESET + "]";
        }
        return " [" + ChatFormatting.GRAY + this.fadeMode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onRenderLast(final RenderWorldLastEvent event) {
        for (final Map.Entry<EntityOtherPlayerMP, Long> entry : new HashMap<EntityOtherPlayerMP, Long>(PopESP.popFakePlayerMap).entrySet()) {
            boolean wireFrame;
            boolean textured;
            if (this.renderMode.getMode().equals("Both")) {
                wireFrame = true;
                textured = true;
            }
            else if (this.renderMode.getMode().equals("Wireframe")) {
                wireFrame = true;
                textured = false;
            }
            else {
                wireFrame = false;
                textured = true;
            }
            if (System.currentTimeMillis() - entry.getValue() < (long)this.fadeTime.getValue() && this.fadeMode.getMode().equals("Elevator")) {
                if (this.elevatorMode.getMode().equals("Heaven")) {
                    final EntityOtherPlayerMP entityOtherPlayerMP = entry.getKey();
                    entityOtherPlayerMP.field_70163_u += this.fadeSpeed.getValue() * event.getPartialTicks();
                }
                else {
                    final EntityOtherPlayerMP entityOtherPlayerMP2 = entry.getKey();
                    entityOtherPlayerMP2.field_70163_u -= this.fadeSpeed.getValue() * event.getPartialTicks();
                }
            }
            else if (System.currentTimeMillis() - entry.getValue() < (long)this.fadeTime.getValue() && this.fadeMode.getMode().equals("Fade")) {
                this.fade -= this.fadeSpeed.getValue();
            }
            if (System.currentTimeMillis() - entry.getValue() > (long)this.fadeTime.getValue() || this.fade == 0.0f) {
                PopESP.popFakePlayerMap.remove(entry.getKey());
            }
            else {
                GL11.glPushMatrix();
                GL11.glDepthRange(0.01, 1.0);
                if (wireFrame) {
                    GL11.glDisable(2896);
                    GL11.glDisable(3553);
                    GL11.glPolygonMode(1032, 6913);
                    GL11.glEnable(3008);
                    GL11.glEnable(3042);
                    GL11.glLineWidth(this.lineWidth.getValue());
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glColor4f(this.r.getValue() / 255.0f, this.g.getValue() / 255.0f, this.b.getValue() / 255.0f, this.fadeMode.getMode().equals("Fade") ? this.fade : 1.0f);
                    this.renderEntityStatic((Entity)entry.getKey(), event.getPartialTicks(), true);
                    GL11.glHint(3154, 4352);
                    GL11.glPolygonMode(1032, 6914);
                    GL11.glEnable(2896);
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glEnable(3553);
                }
                if (textured) {
                    GL11.glPushAttrib(-1);
                    GL11.glEnable(3008);
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glEnable(3042);
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                    GlStateManager.func_179120_a(770, 771, 1, 0);
                    GL11.glLineWidth(1.5f);
                    GL11.glColor4f(this.r.getValue() / 255.0f, this.g.getValue() / 255.0f, this.b.getValue() / 255.0f, this.fadeMode.getMode().equals("Fade") ? this.fade : (this.a.getValue() / 255.0f));
                    this.renderEntityStatic((Entity)entry.getKey(), event.getPartialTicks(), true);
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                    GL11.glDisable(3008);
                    GL11.glEnable(3553);
                    GL11.glEnable(2896);
                    GL11.glDisable(3042);
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glPopAttrib();
                }
                GL11.glDepthRange(0.0, 1.0);
                GL11.glPopMatrix();
                this.fade = 1.0f;
            }
        }
    }
    
    @SubscribeEvent
    public void onPop(final TotemPopEvent event) {
        if (PopESP.mc.field_71441_e.func_73045_a(event.getEntityId()) != null) {
            final Entity entity = PopESP.mc.field_71441_e.func_73045_a(event.getEntityId());
            if (entity instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)entity;
                final EntityOtherPlayerMP fakeEntity = new EntityOtherPlayerMP((World)PopESP.mc.field_71441_e, player.func_146103_bH());
                fakeEntity.func_82149_j((Entity)player);
                fakeEntity.field_70759_as = player.field_70759_as;
                fakeEntity.field_70758_at = player.field_70759_as;
                fakeEntity.field_70177_z = player.field_70177_z;
                fakeEntity.field_70126_B = player.field_70177_z;
                fakeEntity.field_70125_A = player.field_70125_A;
                fakeEntity.field_70127_C = player.field_70125_A;
                fakeEntity.field_71109_bG = fakeEntity.field_70177_z;
                fakeEntity.field_70726_aT = fakeEntity.field_70125_A;
                PopESP.popFakePlayerMap.put(fakeEntity, System.currentTimeMillis());
            }
        }
    }
    
    public void renderEntityStatic(final Entity entityIn, final float partialTicks, final boolean p_188388_3_) {
        if (entityIn.field_70173_aa == 0) {
            entityIn.field_70142_S = entityIn.field_70165_t;
            entityIn.field_70137_T = entityIn.field_70163_u;
            entityIn.field_70136_U = entityIn.field_70161_v;
        }
        final double d0 = entityIn.field_70142_S + (entityIn.field_70165_t - entityIn.field_70142_S) * partialTicks;
        final double d2 = entityIn.field_70137_T + (entityIn.field_70163_u - entityIn.field_70137_T) * partialTicks;
        final double d3 = entityIn.field_70136_U + (entityIn.field_70161_v - entityIn.field_70136_U) * partialTicks;
        final float f = entityIn.field_70126_B + (entityIn.field_70177_z - entityIn.field_70126_B) * partialTicks;
        int i = entityIn.func_70070_b();
        if (entityIn.func_70027_ad()) {
            i = 15728880;
        }
        final int j = i % 65536;
        final int k = i / 65536;
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)j, (float)k);
        PopESP.mc.func_175598_ae().func_188391_a(entityIn, d0 - PopESP.mc.func_175598_ae().field_78730_l, d2 - PopESP.mc.func_175598_ae().field_78731_m, d3 - PopESP.mc.func_175598_ae().field_78728_n, f, partialTicks, p_188388_3_);
    }
    
    private void glColor(final boolean textured, final boolean wireframe) {
        final Color clr = new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), this.a.getValue());
        if (textured) {
            GL11.glColor4f(clr.getRed() / 255.0f, clr.getGreen() / 255.0f, clr.getBlue() / 255.0f, clr.getAlpha() / 255.0f);
        }
        if (wireframe) {
            GL11.glColor4f(clr.getRed() / 255.0f, clr.getGreen() / 255.0f, clr.getBlue() / 255.0f, 1.0f);
        }
    }
    
    static {
        popFakePlayerMap = new HashMap<EntityOtherPlayerMP, Long>();
    }
}
