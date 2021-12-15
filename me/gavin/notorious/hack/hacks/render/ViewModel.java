// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ViewModel", description = "ez", category = Category.Render)
public class ViewModel extends Hack
{
    @RegisterSetting
    public final NumSetting translateX;
    @RegisterSetting
    public final NumSetting translateY;
    @RegisterSetting
    public final NumSetting translateZ;
    @RegisterSetting
    public final NumSetting rotateX;
    @RegisterSetting
    public final NumSetting rotateY;
    @RegisterSetting
    public final NumSetting rotateZ;
    @RegisterSetting
    public final NumSetting scaleX;
    @RegisterSetting
    public final NumSetting scaleY;
    @RegisterSetting
    public final NumSetting scaleZ;
    public static ViewModel INSTANCE;
    
    public ViewModel() {
        this.translateX = new NumSetting("TranslateX", 0.0f, -200.0f, 200.0f, 1.0f);
        this.translateY = new NumSetting("TranslateY", 0.0f, -200.0f, 200.0f, 1.0f);
        this.translateZ = new NumSetting("TranslateZ", 0.0f, -200.0f, 200.0f, 1.0f);
        this.rotateX = new NumSetting("RotateX", 0.0f, -200.0f, 200.0f, 1.0f);
        this.rotateY = new NumSetting("RotateY", 0.0f, -200.0f, 200.0f, 1.0f);
        this.rotateZ = new NumSetting("RotateZ", 0.0f, -200.0f, 200.0f, 1.0f);
        this.scaleX = new NumSetting("ScaleX", 100.0f, 0.0f, 200.0f, 1.0f);
        this.scaleY = new NumSetting("ScaleY", 100.0f, 0.0f, 200.0f, 1.0f);
        this.scaleZ = new NumSetting("ScaleZ", 100.0f, 0.0f, 200.0f, 1.0f);
        ViewModel.INSTANCE = this;
    }
}
