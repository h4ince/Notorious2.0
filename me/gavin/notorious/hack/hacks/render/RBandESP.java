// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.client.renderer.OpenGlHelper;
import java.awt.Color;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import java.util.Map;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import java.util.HashMap;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "RBandESP", description = "ez", category = Category.Render)
public class RBandESP extends Hack
{
    @RegisterSetting
    public final NumSetting fadeTime;
    @RegisterSetting
    private final NumSetting lineWidth;
    @RegisterSetting
    public final ColorSetting color;
    private final HashMap<EntityOtherPlayerMP, Long> popFakePlayerMap;
    
    public RBandESP() {
        this.fadeTime = new NumSetting("FadeTime", 3000.0f, 1.0f, 5000.0f, 100.0f);
        this.lineWidth = new NumSetting("Line Width", 1.0f, 0.1f, 3.0f, 0.1f);
        this.color = new ColorSetting("Color", 0, 255, 255, 255);
        this.popFakePlayerMap = new HashMap<EntityOtherPlayerMP, Long>();
    }
    
    @SubscribeEvent
    public void onRenderLast(final RenderWorldLastEvent event) {
        for (final Map.Entry<EntityOtherPlayerMP, Long> entry : new HashMap<EntityOtherPlayerMP, Long>(this.popFakePlayerMap).entrySet()) {
            if (System.currentTimeMillis() - entry.getValue() > (long)this.fadeTime.getValue()) {
                this.popFakePlayerMap.remove(entry.getKey());
            }
            else {
                GL11.glPushMatrix();
                GL11.glDepthRange(0.0, 0.01);
                GL11.glDisable(2896);
                GL11.glDisable(3553);
                GL11.glPolygonMode(1032, 6913);
                GL11.glEnable(3008);
                GL11.glEnable(3042);
                GL11.glLineWidth(this.lineWidth.getValue());
                GL11.glEnable(2848);
                GL11.glHint(3154, 4354);
                this.glColor();
                this.renderEntity((Entity)entry.getKey(), event.getPartialTicks(), false);
                GL11.glHint(3154, 4352);
                GL11.glPolygonMode(1032, 6914);
                GL11.glEnable(2896);
                GL11.glDepthRange(0.0, 1.0);
                GL11.glEnable(3553);
                GL11.glPopMatrix();
            }
        }
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive event) {
        for (final EntityPlayer e : RBandESP.mc.field_71441_e.field_73010_i) {
            if (event.getPacket() instanceof SPacketPlayerPosLook && RBandESP.mc.field_71441_e.func_73045_a(e.func_145782_y()) != null) {
                final Entity entity = RBandESP.mc.field_71441_e.func_73045_a(e.func_145782_y());
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                final EntityPlayer player = (EntityPlayer)entity;
                final EntityOtherPlayerMP fakeEntity = new EntityOtherPlayerMP((World)RBandESP.mc.field_71441_e, player.func_146103_bH());
                fakeEntity.func_82149_j((Entity)player);
                fakeEntity.field_70759_as = player.field_70759_as;
                fakeEntity.field_70758_at = player.field_70759_as;
                fakeEntity.field_70177_z = player.field_70177_z;
                fakeEntity.field_70126_B = player.field_70177_z;
                fakeEntity.field_70125_A = player.field_70125_A;
                fakeEntity.field_70127_C = player.field_70125_A;
                fakeEntity.field_71109_bG = fakeEntity.field_70177_z;
                fakeEntity.field_70726_aT = fakeEntity.field_70125_A;
                this.popFakePlayerMap.put(fakeEntity, System.currentTimeMillis());
            }
        }
    }
    
    private void glColor() {
        final Color clr = this.color.getAsColor();
        GL11.glColor4f(clr.getRed() / 255.0f, clr.getGreen() / 255.0f, clr.getBlue() / 255.0f, this.color.getAlpha().getValue());
    }
    
    public void renderEntity(final Entity entityIn, final float partialTicks, final boolean p_188388_3_) {
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
        RBandESP.mc.func_175598_ae().func_188391_a(entityIn, d0 - RBandESP.mc.func_175598_ae().field_78730_l, d2 - RBandESP.mc.func_175598_ae().field_78731_m, d3 - RBandESP.mc.func_175598_ae().field_78728_n, f, partialTicks, p_188388_3_);
    }
}
