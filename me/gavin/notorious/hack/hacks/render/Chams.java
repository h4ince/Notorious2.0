// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.GlStateManager;
import me.gavin.notorious.util.ColorUtil;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.RenderEntityEvent;
import net.minecraft.entity.Entity;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Chams", description = "highlight entities", category = Category.Render)
public class Chams extends Hack
{
    @RegisterSetting
    private final BooleanSetting players;
    @RegisterSetting
    private final BooleanSetting crystal;
    @RegisterSetting
    private final BooleanSetting animals;
    @RegisterSetting
    private final BooleanSetting mobs;
    @RegisterSetting
    private final BooleanSetting walls;
    @RegisterSetting
    private final BooleanSetting texture;
    @RegisterSetting
    private final BooleanSetting glint;
    @RegisterSetting
    private final BooleanSetting rainbow;
    @RegisterSetting
    private final NumSetting lineWidth;
    @RegisterSetting
    private final NumSetting r;
    @RegisterSetting
    private final NumSetting g;
    @RegisterSetting
    private final NumSetting b;
    @RegisterSetting
    private final NumSetting a;
    @RegisterSetting
    private final ModeSetting mode;
    public Entity entityBeingRendered;
    
    public Chams() {
        this.players = new BooleanSetting("Players", true);
        this.crystal = new BooleanSetting("Crystal", true);
        this.animals = new BooleanSetting("Animals", false);
        this.mobs = new BooleanSetting("Mobs", false);
        this.walls = new BooleanSetting("Walls", true);
        this.texture = new BooleanSetting("Texture", true);
        this.glint = new BooleanSetting("Glint", true);
        this.rainbow = new BooleanSetting("Rainbow", false);
        this.lineWidth = new NumSetting("Line Width", 1.0f, 0.1f, 3.0f, 0.1f);
        this.r = new NumSetting("Red", 255.0f, 0.0f, 255.0f, 1.0f);
        this.g = new NumSetting("Green", 255.0f, 0.0f, 255.0f, 1.0f);
        this.b = new NumSetting("Blue", 255.0f, 0.0f, 255.0f, 1.0f);
        this.a = new NumSetting("Alpha", 255.0f, 0.0f, 255.0f, 1.0f);
        this.mode = new ModeSetting("RenderMode", "Color", new String[] { "Color", "Wireframe", "Skin" });
    }
    
    @SubscribeEvent
    public void onRenderEntity(final RenderEntityEvent.Pre event) {
        if (this.shouldDoChams(event.getEntity())) {
            this.setupChams();
            this.entityBeingRendered = event.getEntity();
            if (this.walls.isEnabled()) {
                this.startDepthRange();
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderEntityPost(final RenderEntityEvent.Post event) {
        if (this.shouldDoChams(event.getEntity())) {
            if (this.walls.isEnabled()) {
                this.endDepthRange();
            }
            this.entityBeingRendered = null;
            this.endChams();
        }
    }
    
    private void startDepthRange() {
        GL11.glDepthRange(0.0, 0.01);
    }
    
    private void endDepthRange() {
        GL11.glDepthRange(0.0, 1.0);
    }
    
    private void startWireframe() {
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glPolygonMode(1032, 6913);
        GL11.glEnable(3008);
        GL11.glEnable(3042);
        GL11.glLineWidth(this.lineWidth.getValue());
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(this.lineWidth.getValue());
        GL11.glColor4f(this.rainbow.getValue() ? ((float)ColorUtil.getRainbow(6.0f, 1.0f)) : (this.r.getValue() / 255.0f), this.g.getValue() / 255.0f, this.b.getValue() / 255.0f, 1.0f);
    }
    
    private void endWireframe() {
        GL11.glHint(3154, 4352);
        GL11.glPolygonMode(1032, 6914);
        GL11.glEnable(2896);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
    }
    
    private void startColor() {
        GL11.glPushAttrib(-1);
        GL11.glEnable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GL11.glColor4f(this.rainbow.getValue() ? ((float)ColorUtil.getRainbow(6.0f, 1.0f)) : (this.r.getValue() / 255.0f), this.g.getValue() / 255.0f, this.b.getValue() / 255.0f, this.a.getValue() / 255.0f);
    }
    
    private void endColor() {
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3008);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopAttrib();
    }
    
    private void setupChams() {
        if (!this.texture.getValue() && this.mode.getMode() != "Skin") {
            GL11.glDisable(3553);
        }
        Chams.mc.func_175598_ae().func_178633_a(false);
        if (this.walls.getValue()) {
            this.startDepthRange();
        }
        if (this.mode.getMode().equals("Wireframe")) {
            this.startWireframe();
        }
        else if (this.mode.getMode().equals("Color")) {
            this.startColor();
        }
        else if (this.mode.getMode().equals("Both")) {
            this.startWireframe();
            this.startColor();
        }
    }
    
    private void endChams() {
        Chams.mc.func_175598_ae().func_178633_a(Chams.mc.field_71474_y.field_181151_V);
        if (this.walls.getValue()) {
            this.endDepthRange();
        }
        if (this.mode.getMode() == "Wireframe") {
            this.endWireframe();
        }
        else if (this.mode.getMode() == "Color") {
            this.endColor();
        }
        else if (this.mode.getMode() == "Both") {
            this.endColor();
            this.endWireframe();
        }
        GL11.glEnable(3553);
    }
    
    private boolean shouldDoChams(final Entity entity) {
        return (entity instanceof EntityPlayer && this.players.getValue()) || ((entity instanceof EntityMob || entity instanceof EntitySlime) && this.mobs.getValue()) || (entity instanceof EntityEnderCrystal && this.crystal.getValue()) || (entity instanceof EntityAnimal && this.animals.getValue());
    }
}
