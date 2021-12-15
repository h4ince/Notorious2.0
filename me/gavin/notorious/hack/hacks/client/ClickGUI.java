// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.client;

import net.minecraft.client.gui.GuiScreen;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ClickGUI", description = "Opens the click gui", category = Category.Client)
public class ClickGUI extends Hack
{
    @RegisterSetting
    public final ModeSetting colorMode;
    @RegisterSetting
    public final ColorSetting guiColor;
    @RegisterSetting
    public final NumSetting backgroundAlpha;
    @RegisterSetting
    public final NumSetting length;
    @RegisterSetting
    public final NumSetting saturation;
    @RegisterSetting
    public final NumSetting scrollSpeed;
    
    public ClickGUI() {
        this.colorMode = new ModeSetting("ColorMode", "Rainbow", new String[] { "Rainbow", "RGB" });
        this.guiColor = new ColorSetting("RGBColor", 255, 0, 0, 255);
        this.backgroundAlpha = new NumSetting("BackgroundAlpha", 150.0f, 1.0f, 255.0f, 1.0f);
        this.length = new NumSetting("Length", 8.0f, 1.0f, 15.0f, 1.0f);
        this.saturation = new NumSetting("Saturation", 0.6f, 0.1f, 1.0f, 0.1f);
        this.scrollSpeed = new NumSetting("ScrollSpeed", 1.0f, 0.1f, 5.0f, 0.1f);
    }
    
    @Override
    protected void onEnable() {
        ClickGUI.mc.func_147108_a((GuiScreen)this.notorious.clickGuiScreen);
        this.disable();
    }
}
