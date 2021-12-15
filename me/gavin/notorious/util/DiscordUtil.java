// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.util.Objects;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.Minecraft;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class DiscordUtil
{
    private static final DiscordRichPresence discordRichPresence;
    private static final DiscordRPC discordRPC;
    
    public static void startRPC() {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        String ip;
        if (Minecraft.func_71410_x().func_71387_A()) {
            ip = "Singleplayer";
        }
        else {
            ip = Objects.requireNonNull(Minecraft.func_71410_x().func_147104_D()).field_78845_b;
        }
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        final String discordID = "866094794517643265";
        DiscordUtil.discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        DiscordUtil.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordUtil.discordRichPresence.details = "Dealing drugs on " + ip;
        DiscordUtil.discordRichPresence.largeImageKey = "Notorius 2.0.0";
        DiscordUtil.discordRichPresence.largeImageText = "discord.gg/nPBPJRcuqP";
        DiscordUtil.discordRichPresence.smallImageKey = "jerking_off";
        DiscordUtil.discordRichPresence.smallImageText = "i cant hear you thru my dick being in ur mouth";
        DiscordUtil.discordRichPresence.state = null;
        DiscordUtil.discordRPC.Discord_UpdatePresence(DiscordUtil.discordRichPresence);
    }
    
    public static void stopRPC() {
        DiscordUtil.discordRPC.Discord_Shutdown();
        DiscordUtil.discordRPC.Discord_ClearPresence();
    }
    
    static {
        discordRichPresence = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
    }
}
