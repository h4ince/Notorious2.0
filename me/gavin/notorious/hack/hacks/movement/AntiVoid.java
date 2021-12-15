// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AntiVoid", description = "ez", category = Category.Movement)
public class AntiVoid extends Hack
{
    @RegisterSetting
    public final ModeSetting mode;
    @RegisterSetting
    public final NumSetting yOffset;
    
    public AntiVoid() {
        this.mode = new ModeSetting("Mode", "TP", new String[] { "TP", "Jump", "Freeze" });
        this.yOffset = new NumSetting("YOffset", 0.5f, 0.1f, 1.0f, 0.1f);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.mode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        final double yLevel = AntiVoid.mc.field_71439_g.field_70163_u;
        if (this.mode.getMode().equals("TP")) {
            if (yLevel <= (int)this.yOffset.getValue()) {
                AntiVoid.mc.field_71439_g.func_70107_b(AntiVoid.mc.field_71439_g.field_70165_t, AntiVoid.mc.field_71439_g.field_70163_u + 2.0, AntiVoid.mc.field_71439_g.field_70161_v);
                this.notorious.messageManager.sendMessage("Attempting to TP out of void hole.");
            }
        }
        else if (this.mode.getMode().equals("Jump")) {
            if (yLevel <= (int)this.yOffset.getValue()) {
                AntiVoid.mc.field_71439_g.func_70664_aZ();
                this.notorious.messageManager.sendMessage("Attempting to jump out of void hole.");
            }
        }
        else if (!AntiVoid.mc.field_71439_g.field_70145_X && yLevel <= this.yOffset.getValue()) {
            final RayTraceResult trace = AntiVoid.mc.field_71441_e.func_147447_a(AntiVoid.mc.field_71439_g.func_174791_d(), new Vec3d(AntiVoid.mc.field_71439_g.field_70165_t, 0.0, AntiVoid.mc.field_71439_g.field_70161_v), false, false, false);
            if (trace != null && trace.field_72313_a == RayTraceResult.Type.BLOCK) {
                return;
            }
            AntiVoid.mc.field_71439_g.func_70016_h(0.0, 0.0, 0.0);
            if (AntiVoid.mc.field_71439_g.func_184187_bx() != null) {
                AntiVoid.mc.field_71439_g.func_184187_bx().func_70016_h(0.0, 0.0, 0.0);
            }
        }
    }
}
