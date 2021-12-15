// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoLog", description = "ez", category = Category.Misc)
public class AutoLog extends Hack
{
    @RegisterSetting
    public final NumSetting health;
    
    public AutoLog() {
        this.health = new NumSetting("HealthToLog", 16.0f, 1.0f, 36.0f, 1.0f);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.health.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (AutoLog.mc.field_71439_g.func_110143_aJ() <= this.health.getValue()) {
            Objects.requireNonNull(AutoLog.mc.func_147114_u()).func_147253_a(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged at [" + AutoLog.mc.field_71439_g.func_110143_aJ() + "]")));
            this.toggle();
        }
    }
}
