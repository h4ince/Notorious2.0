// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack;

import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.setting.Setting;
import java.util.ArrayList;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.stuff.IMinecraft;
import me.gavin.notorious.gui.api.Bindable;
import me.gavin.notorious.gui.api.Toggleable;

public abstract class Hack implements Toggleable, Bindable, IMinecraft
{
    protected final Notorious notorious;
    private String name;
    private String description;
    private Category category;
    private boolean drawn;
    public long lastEnabledTime;
    public long lastDisabledTime;
    private int keybind;
    private boolean enabled;
    private final ArrayList<Setting> settings;
    
    public Hack() {
        this.notorious = Notorious.INSTANCE;
        this.drawn = false;
        this.lastEnabledTime = -1L;
        this.lastDisabledTime = -1L;
        this.settings = new ArrayList<Setting>();
    }
    
    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
    
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void toggle() {
        if (this.enabled) {
            this.disable();
        }
        else {
            this.enable();
        }
    }
    
    public void enable() {
        this.enabled = true;
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.onEnable();
        this.lastEnabledTime = System.currentTimeMillis();
    }
    
    public void disable() {
        this.enabled = false;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        this.onDisable();
        this.lastDisabledTime = System.currentTimeMillis();
    }
    
    public void onUpdate() {
    }
    
    public void onTick() {
    }
    
    protected void onEnable() {
    }
    
    protected void onDisable() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    @Override
    public int getBind() {
        return this.keybind;
    }
    
    @Override
    public void setBind(final int keybind) {
        this.keybind = keybind;
    }
    
    public String getMetaData() {
        return "";
    }
    
    public void setCategory(final Category category) {
        this.category = category;
    }
    
    public void setDrawn() {
        this.drawn = true;
    }
    
    public void setUndrawn() {
        this.drawn = false;
    }
    
    public boolean isDrawn() {
        return this.drawn;
    }
    
    public enum Category
    {
        Combat, 
        Player, 
        Movement, 
        Render, 
        Misc, 
        World, 
        Chat, 
        Client;
    }
}
