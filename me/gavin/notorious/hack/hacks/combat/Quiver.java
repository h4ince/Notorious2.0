// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.item.ItemBow;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Quiver", description = "Automatically places a totem in your offhand.", category = Category.Combat)
public class Quiver extends Hack
{
    @RegisterSetting
    public final NumSetting tickDelay;
    
    public Quiver() {
        this.tickDelay = new NumSetting("HoldTime", 3.0f, 0.0f, 8.0f, 0.5f);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.tickDelay.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (Quiver.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemBow && Quiver.mc.field_71439_g.func_184612_cw() >= this.tickDelay.getValue()) {
            Quiver.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(Quiver.mc.field_71439_g.field_71109_bG, -90.0f, true));
            Quiver.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem());
        }
    }
}
