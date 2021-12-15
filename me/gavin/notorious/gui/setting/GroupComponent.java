// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import me.gavin.notorious.setting.SettingGroup;
import me.gavin.notorious.gui.api.SettingComponent;

public class GroupComponent extends SettingComponent
{
    private final SettingGroup setting;
    private boolean open;
    
    public GroupComponent(final SettingGroup setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.setting = setting;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keyCode) {
    }
    
    @Override
    public int getTotalHeight() {
        return 0;
    }
}
