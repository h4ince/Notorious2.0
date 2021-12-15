// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import me.gavin.notorious.event.events.RenderEntityEvent;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Alpha", description = "ez", category = Category.Render)
public class Alpha extends Hack
{
    @RegisterSetting
    private final NumSetting range;
    @RegisterSetting
    private final NumSetting a;
    
    public Alpha() {
        this.range = new NumSetting("Range", 4.0f, 1.0f, 8.0f, 1.0f);
        this.a = new NumSetting("Alpha", 125.0f, 1.0f, 255.0f, 0.1f);
    }
    
    @SubscribeEvent
    public void onRenderPre(final RenderEntityEvent.Pre event) {
        if (this.shouldRender(event.getEntity())) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, this.a.getValue() / 255.0f);
        }
    }
    
    @SubscribeEvent
    public void onRenderPost(final RenderEntityEvent.Post event) {
        if (this.shouldRender(event.getEntity())) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public boolean shouldRender(final Entity entity) {
        return entity != Alpha.mc.field_71439_g && entity instanceof EntityPlayer && entity.func_70032_d((Entity)Alpha.mc.field_71439_g) < this.range.getValue();
    }
}
