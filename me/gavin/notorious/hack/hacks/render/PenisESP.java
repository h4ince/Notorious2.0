// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.util.RenderUtil;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "PenisESP", description = "ESP for your penis.", category = Category.Render)
public class PenisESP extends Hack
{
    private float pspin;
    private float pcumsize;
    private float pamount;
    
    public void onEnable() {
        this.pspin = 0.0f;
        this.pcumsize = 0.0f;
        this.pamount = 0.0f;
    }
    
    @SubscribeEvent
    public void render(final RenderWorldLastEvent event) {
        for (final Object o : PenisESP.mc.field_71441_e.field_72996_f) {
            if (o instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)o;
                final double x2 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * PenisESP.mc.func_184121_ak();
                final double x3 = x2 - PenisESP.mc.func_175598_ae().field_78730_l;
                final double y2 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * PenisESP.mc.func_184121_ak();
                final double y3 = y2 - PenisESP.mc.func_175598_ae().field_78731_m;
                final double z2 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * PenisESP.mc.func_184121_ak();
                final double z3 = z2 - PenisESP.mc.func_175598_ae().field_78728_n;
                GL11.glPushMatrix();
                RenderHelper.func_74518_a();
                RenderUtil.drawPenis(player, x3, y3, z3, this.pspin, this.pcumsize, this.pamount);
                RenderHelper.func_74519_b();
                GL11.glPopMatrix();
            }
        }
    }
}
