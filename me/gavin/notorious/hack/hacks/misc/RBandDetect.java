// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.movement.Step;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import me.gavin.notorious.event.events.PacketEvent;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "RBandDetect", description = "ez", category = Category.Misc)
public class RBandDetect extends Hack
{
    @RegisterSetting
    public final BooleanSetting disableOnLag;
    @RegisterSetting
    public final BooleanSetting step;
    
    public RBandDetect() {
        this.disableOnLag = new BooleanSetting("DisableOnLag", true);
        this.step = new BooleanSetting("Step", true);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            this.notorious.messageManager.sendError("Rubberband detected!");
            if (this.disableOnLag.isEnabled() && this.step.isEnabled()) {
                this.notorious.messageManager.sendMessage("Toggling modules.");
                if (Notorious.INSTANCE.hackManager.getHack(Step.class).isEnabled()) {
                    Notorious.INSTANCE.hackManager.getHack(Step.class).toggle();
                }
            }
        }
    }
}
