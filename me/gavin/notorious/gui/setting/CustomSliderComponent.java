// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import me.gavin.notorious.setting.NumSetting;

public abstract class CustomSliderComponent extends SliderComponent
{
    public CustomSliderComponent(final NumSetting setting, final int x, final int y, final int width, final int height) {
        super(setting, x, y, width, height);
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        super.updateSliderLogic(mouseX, mouseY);
        this.drawCustomSlider(mouseX, mouseY, partialTicks);
    }
    
    public abstract void drawCustomSlider(final int p0, final int p1, final float p2);
}
