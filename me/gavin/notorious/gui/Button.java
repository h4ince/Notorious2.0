// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui;

import me.gavin.notorious.gui.api.SettingComponent;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import me.gavin.notorious.util.ColorUtil;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.Font;
import java.util.Iterator;
import me.gavin.notorious.gui.api.Bindable;
import me.gavin.notorious.gui.setting.KeybindComponent;
import me.gavin.notorious.gui.setting.StringComponent;
import me.gavin.notorious.setting.StringSetting;
import me.gavin.notorious.gui.setting.SexyColorComponent;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.gui.setting.SliderComponent;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.gui.setting.ModeComponent;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.gui.setting.BooleanComponent;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.Setting;
import me.gavin.notorious.gui.api.Toggleable;
import me.gavin.notorious.hack.Hack;
import me.gavin.notorious.stuff.IMinecraft;
import me.gavin.notorious.gui.api.AbstractToggleContainer;

public class Button extends AbstractToggleContainer implements IMinecraft
{
    private final Hack hack;
    
    public Button(final Hack hack, final int x, final int y, final int width, final int height) {
        super(hack, x, y, width, height);
        this.hack = hack;
        for (final Setting setting : hack.getSettings()) {
            if (setting instanceof BooleanSetting) {
                this.components.add(new BooleanComponent((BooleanSetting)setting, x, y, width, height));
            }
            else if (setting instanceof ModeSetting) {
                this.components.add(new ModeComponent((ModeSetting)setting, x, y, width, height));
            }
            else if (setting instanceof NumSetting) {
                this.components.add(new SliderComponent((NumSetting)setting, x, y, width, height));
            }
            else if (setting instanceof ColorSetting) {
                this.components.add(new SexyColorComponent((ColorSetting)setting, x, y, width, height));
            }
            else {
                if (!(setting instanceof StringSetting)) {
                    continue;
                }
                this.components.add(new StringComponent((StringSetting)setting, x, y, width, height));
            }
        }
        this.components.add(new KeybindComponent(hack, x + 5, y, width, height));
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        final Font font = Notorious.INSTANCE.hackManager.getHack(Font.class);
        int renderYOffset = this.height;
        final float time = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).length.getValue();
        final float saturation = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).saturation.getValue();
        int intRainbow;
        if (Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).colorMode.getMode().equals("Rainbow")) {
            intRainbow = ColorUtil.getRainbow(time, saturation);
        }
        else {
            intRainbow = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
        }
        Color colorRainbow;
        if (Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).colorMode.getMode().equals("Rainbow")) {
            colorRainbow = ColorUtil.colorRainbow((int)time, saturation, 1.0f);
        }
        else {
            colorRainbow = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor();
        }
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0, 0, 0, (int)Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).backgroundAlpha.getValue()).getRGB());
        if (this.open) {
            if (font.isEnabled()) {
                Notorious.INSTANCE.fontRenderer.drawStringWithShadow("-", this.x + this.width - 8.0f, this.y + 2.0f, Color.WHITE);
            }
            else {
                Button.mc.field_71466_p.func_175063_a("-", this.x + this.width - 8.0f, this.y + 2.0f, new Color(255, 255, 255).getRGB());
            }
        }
        else if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow("+", this.x + this.width - 8.0f, this.y + 4.0f, Color.WHITE);
        }
        else {
            Button.mc.field_71466_p.func_175063_a("+", this.x + this.width - 8.0f, this.y + 2.0f, new Color(255, 255, 255).getRGB());
        }
        if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.hack.getName(), this.x + 2.0f, this.y + 4.0f, this.hack.isEnabled() ? colorRainbow : new Color(255, 255, 255, 255));
        }
        else {
            Button.mc.field_71466_p.func_175063_a(this.hack.getName(), this.x + 2.0f, this.y + 2.0f, this.hack.isEnabled() ? intRainbow : -1);
        }
        if (this.open) {
            for (final SettingComponent component : this.components) {
                component.x = this.x;
                component.y = this.y + renderYOffset;
                renderYOffset += component.getTotalHeight();
                component.render(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.hack.toggle();
            }
            else if (mouseButton == 1) {
                this.open = !this.open;
            }
            else if (mouseButton == 2) {
                if (this.hack.isDrawn()) {
                    this.hack.setUndrawn();
                    Notorious.INSTANCE.messageManager.sendMessage(this.hack.getName() + " is no longer Drawn.");
                }
                else {
                    this.hack.setDrawn();
                    Notorious.INSTANCE.messageManager.sendMessage(this.hack.getName() + " is now Drawn.");
                }
            }
        }
        if (this.open) {
            for (final SettingComponent component : this.components) {
                component.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.open) {
            for (final SettingComponent component : this.components) {
                component.mouseReleased(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keyCode) {
        if (this.open) {
            for (final SettingComponent component : this.components) {
                component.keyTyped(keyChar, keyCode);
            }
        }
    }
    
    @Override
    public int getTotalHeight() {
        if (this.open) {
            int h = 0;
            for (final SettingComponent component : this.components) {
                h += component.getTotalHeight();
            }
            return this.height + h;
        }
        return this.height;
    }
}
