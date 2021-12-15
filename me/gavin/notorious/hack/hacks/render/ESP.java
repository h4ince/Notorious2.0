// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import me.gavin.notorious.util.RenderUtil;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.util.NColor;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ESP", description = "Draws a box around entities.", category = Category.Render)
public class ESP extends Hack
{
    @RegisterSetting
    public final ModeSetting espMode;
    @RegisterSetting
    public final ModeSetting colorMode;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final NumSetting lineWidth;
    @RegisterSetting
    public final BooleanSetting players;
    @RegisterSetting
    public final BooleanSetting animals;
    @RegisterSetting
    public final BooleanSetting mobs;
    @RegisterSetting
    public final BooleanSetting items;
    
    public ESP() {
        this.espMode = new ModeSetting("ESPMode", "RotateBox", new String[] { "RotateBox", "Glow" });
        this.colorMode = new ModeSetting("ColorMode", "ClientSync", new String[] { "ClientSync", "RGB" });
        this.outlineColor = new ColorSetting("Outline", new NColor(255, 255, 255, 255));
        this.boxColor = new ColorSetting("Box", new NColor(255, 255, 255, 125));
        this.lineWidth = new NumSetting("LineWidth", 2.0f, 0.1f, 4.0f, 0.1f);
        this.players = new BooleanSetting("Players", true);
        this.animals = new BooleanSetting("Animals", true);
        this.mobs = new BooleanSetting("Mobs", true);
        this.items = new BooleanSetting("Items", true);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.lineWidth.getValue() + ChatFormatting.RESET + "]";
    }
    
    public void onDisable() {
        for (final Entity e : ESP.mc.field_71441_e.field_72996_f) {
            e.func_184195_f(false);
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        for (Entity entity : ESP.mc.field_71441_e.field_72996_f) {}
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        for (final Entity e : ESP.mc.field_71441_e.field_72996_f) {
            final AxisAlignedBB box = e.func_174813_aQ();
            final double x = (e.field_70165_t - e.field_70142_S) * event.getPartialTicks();
            final double y = (e.field_70163_u - e.field_70137_T) * event.getPartialTicks();
            final double z = (e.field_70161_v - e.field_70136_U) * event.getPartialTicks();
            final AxisAlignedBB bb = new AxisAlignedBB(box.field_72340_a + x, box.field_72338_b + y, box.field_72339_c + z, box.field_72336_d + x, box.field_72337_e + y, box.field_72334_f + z);
            if (e == ESP.mc.field_71439_g && ESP.mc.field_71474_y.field_74320_O == 0) {
                continue;
            }
            if (e instanceof EntityPlayer && this.players.isEnabled()) {
                if (this.espMode.getMode().equals("RotateBox")) {
                    this.render(e);
                }
                else {
                    e.func_184195_f(true);
                }
            }
            else if (e instanceof EntityAnimal && this.animals.isEnabled()) {
                if (this.espMode.getMode().equals("RotateBox")) {
                    this.render(e);
                }
                else {
                    e.func_184195_f(true);
                }
            }
            else if ((e instanceof EntityMob || e instanceof EntitySlime) && this.mobs.isEnabled()) {
                if (this.espMode.getMode().equals("RotateBox")) {
                    this.render(e);
                }
                else {
                    e.func_184195_f(true);
                }
            }
            else {
                if (!(e instanceof EntityItem) || !this.items.isEnabled()) {
                    continue;
                }
                if (this.espMode.getMode().equals("RotateBox")) {
                    this.render(e);
                }
                else {
                    e.func_184195_f(true);
                }
            }
        }
    }
    
    private void render(final Entity entity) {
        GlStateManager.func_179094_E();
        final AxisAlignedBB b = entity.func_174813_aQ().func_72317_d(-ESP.mc.func_175598_ae().field_78730_l, -ESP.mc.func_175598_ae().field_78731_m, -ESP.mc.func_175598_ae().field_78728_n);
        final double x = (b.field_72336_d - b.field_72340_a) / 2.0 + b.field_72340_a;
        final double y = (b.field_72337_e - b.field_72338_b) / 2.0 + b.field_72338_b;
        final double z = (b.field_72334_f - b.field_72339_c) / 2.0 + b.field_72339_c;
        GL11.glTranslated(x, y, z);
        GL11.glRotated(-MathHelper.func_151238_b((double)entity.field_70126_B, (double)entity.field_70177_z, (double)ESP.mc.func_184121_ak()), 0.0, 1.0, 0.0);
        GL11.glTranslated(-x, -y, -z);
        RenderUtil.entityESPBox(entity, this.colorMode.getMode().equals("RGB") ? this.boxColor.getAsColor() : Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor(), this.colorMode.getMode().equals("RGB") ? this.outlineColor.getAsColor() : Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor(), (int)this.lineWidth.getValue());
        GlStateManager.func_179121_F();
    }
}
