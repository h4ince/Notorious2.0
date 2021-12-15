// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event;

import me.gavin.notorious.util.ProjectionUtil;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.hack.Hack;
import me.gavin.notorious.Notorious;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.stuff.IMinecraft;

public class EventProcessor implements IMinecraft
{
    public EventProcessor() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (EventProcessor.mc.field_71439_g != null && EventProcessor.mc.field_71441_e != null) {
            Notorious.INSTANCE.hackManager.getHacks().stream().filter(Hack::isEnabled).forEach(Hack::onTick);
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent event) {
        if (EventProcessor.mc.field_71439_g != null && EventProcessor.mc.field_71441_e != null) {
            Notorious.INSTANCE.hackManager.getHacks().stream().filter(Hack::isEnabled).forEach(Hack::onUpdate);
        }
    }
    
    @SubscribeEvent
    public void onKey(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == 0) {
                return;
            }
            for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
                if (hack.getBind() == Keyboard.getEventKey()) {
                    hack.toggle();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        ProjectionUtil.updateMatrix();
    }
}
