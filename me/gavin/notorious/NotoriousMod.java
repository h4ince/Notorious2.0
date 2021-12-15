// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious;

import org.apache.logging.log4j.LogManager;
import me.gavin.notorious.hack.Hack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Comparator;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import org.lwjgl.opengl.Display;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "notorious", name = "Notorious", version = "2.0.0", clientSideOnly = true)
public class NotoriousMod
{
    public static final String MOD_ID = "notorious";
    public static final String MOD_NAME = "Notorious";
    public static final String VERSION = "2.0.0";
    public static final String NAME_VERSION = "Notorious 2.0.0";
    public static final Logger LOGGER;
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        new Notorious();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        Display.setTitle("Notorious 2.0.0");
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        Notorious.INSTANCE.hackManager.getSortedHacks().sort(Comparator.comparing(hack -> -Notorious.INSTANCE.fontRenderer.getStringWidth(hack.getName() + hack.getMetaData())));
    }
    
    static {
        LOGGER = LogManager.getLogger("Notorious");
    }
}
