// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketExplosion;
import me.gavin.notorious.event.events.PacketEvent;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "NoRender", description = "ez", category = Category.Render)
public class NoRender extends Hack
{
    @RegisterSetting
    private final BooleanSetting explosions;
    
    public NoRender() {
        this.explosions = new BooleanSetting("Explosions", true);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketExplosion && this.explosions.getValue()) {
            event.setCanceled(true);
        }
    }
}
