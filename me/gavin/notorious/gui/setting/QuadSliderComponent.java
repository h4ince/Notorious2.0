// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.gui.api.SettingComponent;

public abstract class QuadSliderComponent extends SettingComponent
{
    private final NumSetting xSetting;
    private final NumSetting ySetting;
    private boolean dragging;
    protected float sliderWidth;
    protected float sliderHeight;
    
    public QuadSliderComponent(final NumSetting xSetting, final NumSetting ySetting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.xSetting = xSetting;
        this.ySetting = ySetting;
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY) && mouseButton == 0) {
            this.dragging = true;
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0) {
            this.dragging = false;
        }
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.updateLogicX(mouseX);
        this.updateLogicY(mouseY);
        this.drawPicker(mouseX, mouseY, partialTicks);
    }
    
    public abstract void drawPicker(final int p0, final int p1, final float p2);
    
    private void updateLogicX(final int mouseX) {
        final float difference = (float)Math.min(this.width, Math.max(0, mouseX - this.x));
        final float min = this.xSetting.getMin();
        final float max = this.xSetting.getMax();
        final float value = this.xSetting.getValue();
        this.sliderWidth = this.width * (value - min) / (max - min);
        if (this.dragging) {
            if (difference == 0.0f) {
                this.xSetting.setValue(min);
            }
            else {
                final float val = SliderComponent.roundToPlace(difference / this.width * (max - min) + min, 3);
                this.xSetting.setValue(val);
            }
        }
    }
    
    private void updateLogicY(final int mouseY) {
        final float difference = (float)Math.min(this.height, Math.max(0, mouseY - this.y));
        final float min = this.ySetting.getMin();
        final float max = this.ySetting.getMax();
        final float value = this.ySetting.getValue();
        this.sliderHeight = this.height * (value - min) / (max - min);
        if (this.dragging) {
            if (difference == 0.0f) {
                this.ySetting.setValue(min);
            }
            else {
                final float val = SliderComponent.roundToPlace(difference / this.height * (max - min) + min, 3);
                this.ySetting.setValue(val);
            }
        }
    }
}
