// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.entity.Entity;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Step", description = "Automatically moves you up a block", category = Category.Movement)
public class Step extends Hack
{
    @RegisterSetting
    public final ModeSetting stepType;
    @RegisterSetting
    public final NumSetting stepHeight;
    private int ticks;
    
    public Step() {
        this.stepType = new ModeSetting("StepType", "NCP", new String[] { "NCP", "Vanilla" });
        this.stepHeight = new NumSetting("Height", 2.0f, 0.5f, 3.0f, 0.5f);
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.stepType.getMode() + ChatFormatting.RESET + " | " + ChatFormatting.GRAY + this.stepHeight.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final PlayerLivingUpdateEvent event) {
        if (this.stepType.getMode().equals("NCP")) {
            final double[] dir = forward(0.1);
            boolean twofive = false;
            boolean two = false;
            boolean onefive = false;
            boolean one = false;
            if (Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 2.6, dir[1])).isEmpty() && !Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 2.4, dir[1])).isEmpty()) {
                twofive = true;
            }
            if (Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 2.1, dir[1])).isEmpty() && !Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 1.9, dir[1])).isEmpty()) {
                two = true;
            }
            if (Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 1.6, dir[1])).isEmpty() && !Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 1.4, dir[1])).isEmpty()) {
                onefive = true;
            }
            if (Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 1.0, dir[1])).isEmpty() && !Step.mc.field_71441_e.func_184144_a((Entity)Step.mc.field_71439_g, Step.mc.field_71439_g.func_174813_aQ().func_72317_d(dir[0], 0.6, dir[1])).isEmpty()) {
                one = true;
            }
            if (Step.mc.field_71439_g.field_70123_F && (Step.mc.field_71439_g.field_191988_bg != 0.0f || Step.mc.field_71439_g.field_70702_br != 0.0f) && Step.mc.field_71439_g.field_70122_E) {
                if (one && this.stepHeight.getValue() >= 1.0) {
                    final double[] oneOffset = { 0.42, 0.753 };
                    for (int i = 0; i < oneOffset.length; ++i) {
                        Step.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + oneOffset[i], Step.mc.field_71439_g.field_70161_v, Step.mc.field_71439_g.field_70122_E));
                    }
                    Step.mc.field_71439_g.func_70107_b(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + 1.0, Step.mc.field_71439_g.field_70161_v);
                    this.ticks = 1;
                }
                if (onefive && this.stepHeight.getValue() >= 1.5) {
                    final double[] oneFiveOffset = { 0.42, 0.75, 1.0, 1.16, 1.23, 1.2 };
                    for (int i = 0; i < oneFiveOffset.length; ++i) {
                        Step.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + oneFiveOffset[i], Step.mc.field_71439_g.field_70161_v, Step.mc.field_71439_g.field_70122_E));
                    }
                    Step.mc.field_71439_g.func_70107_b(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + 1.5, Step.mc.field_71439_g.field_70161_v);
                    this.ticks = 1;
                }
                if (two && this.stepHeight.getValue() >= 2.0) {
                    final double[] twoOffset = { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
                    for (int i = 0; i < twoOffset.length; ++i) {
                        Step.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + twoOffset[i], Step.mc.field_71439_g.field_70161_v, Step.mc.field_71439_g.field_70122_E));
                    }
                    Step.mc.field_71439_g.func_70107_b(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + 2.0, Step.mc.field_71439_g.field_70161_v);
                    this.ticks = 2;
                }
                if (twofive && this.stepHeight.getValue() >= 2.5) {
                    final double[] twoFiveOffset = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
                    for (int i = 0; i < twoFiveOffset.length; ++i) {
                        Step.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + twoFiveOffset[i], Step.mc.field_71439_g.field_70161_v, Step.mc.field_71439_g.field_70122_E));
                    }
                    Step.mc.field_71439_g.func_70107_b(Step.mc.field_71439_g.field_70165_t, Step.mc.field_71439_g.field_70163_u + 2.5, Step.mc.field_71439_g.field_70161_v);
                    this.ticks = 2;
                }
            }
        }
        else {
            Step.mc.field_71439_g.field_70138_W = this.stepHeight.getValue();
        }
    }
    
    public static double[] forward(final double speed) {
        float forward = Step.mc.field_71439_g.field_71158_b.field_192832_b;
        float side = Step.mc.field_71439_g.field_71158_b.field_78902_a;
        float yaw = Step.mc.field_71439_g.field_70126_B + (Step.mc.field_71439_g.field_70177_z - Step.mc.field_71439_g.field_70126_B) * Step.mc.func_184121_ak();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
            }
            else if (side < 0.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
    
    public void onDisable() {
        Step.mc.field_71439_g.field_70138_W = 0.5f;
    }
}
