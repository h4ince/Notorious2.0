// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "SkyColor", description = "ez", category = Category.Render)
public class SkyColor extends Hack
{
    @RegisterSetting
    public final ModeSetting colorMode;
    @RegisterSetting
    public final ColorSetting rgba;
    @RegisterSetting
    public final BooleanSetting fog;
    
    public SkyColor() {
        this.colorMode = new ModeSetting("ColorMode", "ClientSync", new String[] { "ClientSync", "RGB" });
        this.rgba = new ColorSetting("SkyColor", 125, 0, 255, 255);
        this.fog = new BooleanSetting("Fog", true);
    }
    
    @SubscribeEvent
    public void fogColors(final EntityViewRenderEvent.FogColors event) {
        if (this.colorMode.getMode().equals("RGB")) {
            event.setRed(this.rgba.getAsColor().getRed() / 255.0f);
            event.setGreen(this.rgba.getAsColor().getGreen() / 255.0f);
            event.setBlue(this.rgba.getAsColor().getBlue() / 255.0f);
        }
        else {
            event.setRed(Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRed() / 255.0f);
            event.setGreen(Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getGreen() / 255.0f);
            event.setBlue(Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getBlue() / 255.0f);
        }
    }
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity event) {
        if (this.fog.isEnabled()) {
            event.setDensity(0.0f);
            event.setCanceled(true);
        }
    }
}
