// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.Font;
import me.gavin.notorious.util.RenderUtil;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import me.gavin.notorious.setting.NumSetting;
import net.minecraft.client.Minecraft;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.gui.api.SettingComponent;

public class SexyColorComponent extends SettingComponent
{
    private boolean open;
    private final ColorSetting colorSetting;
    protected final Minecraft mc;
    private final CustomSliderComponent hueSlider;
    private final CustomSliderComponent alphaSlider;
    private final QuadSliderComponent pickerSliders;
    
    public SexyColorComponent(final ColorSetting colorSetting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.mc = Minecraft.func_71410_x();
        this.colorSetting = colorSetting;
        this.hueSlider = new CustomSliderComponent(colorSetting.getHue(), x, y, width, 12) {
            @Override
            public void drawCustomSlider(final int mouseX, final int mouseY, final float partialTicks) {
                SexyColorComponent.this.drawHueSlider(this.x, this.y, this.width, this.height);
                Gui.func_73734_a(this.x + (int)this.sliderWidth - 1, this.y, this.x + (int)this.sliderWidth + 1, this.y + this.height, -1);
            }
        };
        this.alphaSlider = new CustomSliderComponent(colorSetting.getAlpha(), x, y, width, 12) {
            @Override
            public void drawCustomSlider(final int mouseX, final int mouseY, final float partialTicks) {
                RenderUtil.drawSideGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, Color.BLACK.getRed(), colorSetting.getAsColor().getRGB());
                Gui.func_73734_a(this.x + (int)this.sliderWidth - 1, this.y, this.x + (int)this.sliderWidth + 1, this.y + this.height, -1);
            }
        };
        this.pickerSliders = new QuadSliderComponent(colorSetting.getSaturation(), colorSetting.getBrightness(), x, y, width - 2, width - 2) {
            @Override
            public int getTotalHeight() {
                return this.height;
            }
            
            @Override
            public void keyTyped(final char keyChar, final int keyCode) {
            }
            
            @Override
            public void drawPicker(final int mouseX, final int mouseY, final float partialTicks) {
                RenderUtil.drawSideGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, -1, Color.getHSBColor(colorSetting.getHue().getValue(), 1.0f, 1.0f).getRGB());
                RenderUtil.drawGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, -16777216, 0);
                Gui.func_73734_a(this.x + (int)this.sliderWidth - 1, this.y + (int)this.sliderHeight - 1, this.x + (int)this.sliderWidth + 1, this.y + (int)this.sliderHeight + 1, -7303024);
            }
        };
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        final Font font = Notorious.INSTANCE.hackManager.getHack(Font.class);
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height, this.colorSetting.getAsColor().getRGB());
        if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.colorSetting.getName(), this.x + 9.0f, this.y + 3.0f, new Color(255, 255, 255, 255));
        }
        else {
            this.mc.field_71466_p.func_175063_a(this.colorSetting.getName(), this.x + 9.0f, this.y + 1.0f, -1);
        }
        if (this.open) {
            this.pickerSliders.x = this.x + 1;
            this.pickerSliders.y = this.y + this.height;
            this.hueSlider.x = this.x + 1;
            this.hueSlider.y = this.y + this.height + this.pickerSliders.getTotalHeight();
            this.alphaSlider.x = this.x;
            this.alphaSlider.y = this.y + this.height + this.pickerSliders.height + this.hueSlider.height;
            this.hueSlider.render(mouseX, mouseY, partialTicks);
            this.alphaSlider.render(mouseX, mouseY, partialTicks);
            this.pickerSliders.render(mouseX, mouseY, partialTicks);
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY)) {
            this.open = !this.open;
        }
        if (this.open) {
            this.hueSlider.mouseClicked(mouseX, mouseY, mouseButton);
            this.alphaSlider.mouseClicked(mouseX, mouseY, mouseButton);
            this.pickerSliders.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.open) {
            this.hueSlider.mouseReleased(mouseX, mouseY, mouseButton);
            this.alphaSlider.mouseReleased(mouseX, mouseY, mouseButton);
            this.pickerSliders.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    public int getTotalHeight() {
        if (this.open) {
            return this.height + this.pickerSliders.height + this.alphaSlider.height + this.hueSlider.height;
        }
        return this.height;
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keycode) {
    }
    
    public void drawHueSlider(final int x, int y, final int width, final int height) {
        int step = 0;
        if (height > width) {
            Gui.func_73734_a(x, y, x + width, y + 4, -65536);
            y += 4;
            for (int colorIndex = 0; colorIndex < 6; ++colorIndex) {
                final int previousStep = Color.HSBtoRGB(step / 6.0f, 1.0f, 1.0f);
                final int nextStep = Color.HSBtoRGB((step + 1) / 6.0f, 1.0f, 1.0f);
                RenderUtil.drawSideGradientRect(x, y + step * (height / 6), x + width, y + (step + 1) * (height / 6), previousStep, nextStep);
                ++step;
            }
        }
        else {
            for (int colorIndex = 0; colorIndex < 6; ++colorIndex) {
                final int previousStep = Color.HSBtoRGB(step / 6.0f, 1.0f, 1.0f);
                final int nextStep = Color.HSBtoRGB((step + 1) / 6.0f, 1.0f, 1.0f);
                RenderUtil.drawSideGradientRect(x + step * (width / 6), y, x + (step + 1) * (width / 6), y + height, previousStep, nextStep);
                ++step;
            }
        }
    }
}
