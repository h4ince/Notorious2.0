// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.util.Iterator;
import me.gavin.notorious.hack.hacks.chat.PopCounter;
import me.gavin.notorious.hack.hacks.misc.FakePlayer;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import me.gavin.notorious.event.events.TotemPopEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketEntityStatus;
import me.gavin.notorious.event.events.PacketEvent;
import net.minecraftforge.common.MinecraftForge;
import java.util.HashMap;
import me.gavin.notorious.Notorious;
import java.util.Map;
import me.gavin.notorious.stuff.IMinecraft;

public class TotemPopListener implements IMinecraft
{
    public final Map<String, Integer> popMap;
    private final Notorious notorious;
    
    public TotemPopListener() {
        this.popMap = new HashMap<String, Integer>();
        this.notorious = Notorious.INSTANCE;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.func_149160_c() == 35) {
                final Entity entity = packet.func_149161_a((World)TotemPopListener.mc.field_71441_e);
                if (entity instanceof EntityPlayer) {
                    if (entity.equals((Object)TotemPopListener.mc.field_71439_g)) {
                        return;
                    }
                    final EntityPlayer player = (EntityPlayer)entity;
                    this.handlePop(player);
                }
            }
        }
    }
    
    public void handlePop(final EntityPlayer player) {
        if (!this.popMap.containsKey(player.func_70005_c_())) {
            MinecraftForge.EVENT_BUS.post((Event)new TotemPopEvent(player.func_70005_c_(), 1, player.func_145782_y()));
            this.popMap.put(player.func_70005_c_(), 1);
        }
        else {
            this.popMap.put(player.func_70005_c_(), this.popMap.get(player.func_70005_c_()) + 1);
            MinecraftForge.EVENT_BUS.post((Event)new TotemPopEvent(player.func_70005_c_(), this.popMap.get(player.func_70005_c_()), player.func_145782_y()));
        }
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        for (final EntityPlayer player : TotemPopListener.mc.field_71441_e.field_73010_i) {
            if (player == this.notorious.hackManager.getHack(FakePlayer.class).fakePlayer) {
                continue;
            }
            if (player == TotemPopListener.mc.field_71439_g || !this.popMap.containsKey(player.func_70005_c_()) || (!player.field_70128_L && player.func_70089_S() && player.func_110143_aJ() > 0.0f)) {
                continue;
            }
            this.notorious.hackManager.getHack(PopCounter.class).onDeath(player.func_70005_c_(), this.popMap.get(player.func_70005_c_()), player.func_145782_y());
            this.popMap.remove(player.func_70005_c_());
        }
    }
}
