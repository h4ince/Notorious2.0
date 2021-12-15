// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import java.math.RoundingMode;
import java.math.BigDecimal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import me.gavin.notorious.util.ColorUtil;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.Font;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.gui.api.SettingComponent;

public class SliderComponent extends SettingComponent
{
    private final NumSetting setting;
    public float sliderWidth;
    private boolean draggingSlider;
    
    public SliderComponent(final NumSetting setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.setting = setting;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.updateSliderLogic(mouseX, mouseY);
        final Font font = Notorious.INSTANCE.hackManager.getHack(Font.class);
        final float time = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).length.getValue();
        final float saturation = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).saturation.getValue();
        int color;
        if (Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).colorMode.getMode().equals("Rainbow")) {
            color = ColorUtil.getRainbow(time, saturation);
        }
        else {
            color = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
        }
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0, 0, 0, (int)Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).backgroundAlpha.getValue()).getRGB());
        Gui.func_73734_a(this.x, this.y, this.x + (int)this.sliderWidth, this.y + this.height, color);
        if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.setting.getName() + " <" + this.setting.getValue() + ">", this.x + 9.0f, this.y + 3.0f, Color.WHITE);
        }
        else {
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.setting.getName() + " <" + this.setting.getValue() + ">", this.x + 9.0f, this.y + 1.0f, new Color(255, 255, 255).getRGB());
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY) && mouseButton == 0) {
            this.draggingSlider = true;
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0 && this.draggingSlider) {
            this.draggingSlider = false;
        }
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keyCode) {
    }
    
    protected void updateSliderLogic(final int mouseX, final int mouseY) {
        final float diff = (float)Math.min(this.width, Math.max(0, mouseX - this.x));
        final float min = this.setting.getMin();
        final float max = this.setting.getMax();
        this.sliderWidth = this.width * (this.setting.getValue() - min) / (max - min);
        if (this.draggingSlider) {
            if (diff == 0.0f) {
                this.setting.setValue(this.setting.getMin());
            }
            else {
                final float value = roundToPlace(diff / this.width * (max - min) + min, 1);
                this.setting.setValue(value);
            }
        }
    }
    
    public static float roundToPlace(final float value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
    
    @Override
    public int getTotalHeight() {
        return this.height;
    }
}
