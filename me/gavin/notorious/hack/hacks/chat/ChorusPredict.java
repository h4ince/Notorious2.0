// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import me.gavin.notorious.util.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import me.gavin.notorious.event.events.PacketEvent;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.util.TimerUtils;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ChorusPredict", description = "ez", category = Category.Chat)
public class ChorusPredict extends Hack
{
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final BooleanSetting render;
    private final TimerUtils timer;
    private BlockPos chorusPos;
    
    public ChorusPredict() {
        this.outlineColor = new ColorSetting("Outline", new Color(255, 255, 255, 255));
        this.boxColor = new ColorSetting("Box", new Color(255, 255, 255, 125));
        this.render = new BooleanSetting("Render", true);
        this.timer = new TimerUtils();
    }
    
    @SubscribeEvent
    public void onUpdate(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect)event.getPacket();
            if (packet.func_186978_a() == SoundEvents.field_187544_ad || packet.func_186978_a() == SoundEvents.field_187534_aX) {
                this.chorusPos = new BlockPos(packet.func_149207_d(), packet.func_149211_e(), packet.func_149210_f());
                this.notorious.messageManager.sendMessage("Player has used a chorus and is now at X:" + ChatFormatting.RED + ChatFormatting.BOLD + packet.func_149207_d() + ChatFormatting.RESET + " Y:" + ChatFormatting.RED + ChatFormatting.BOLD + packet.func_149211_e() + ChatFormatting.RESET + " Z:" + ChatFormatting.RED + ChatFormatting.BOLD + packet.func_149210_f() + ChatFormatting.RESET + "!");
                this.timer.reset();
            }
        }
    }
    
    @Override
    public void onTick() {
        if (this.timer.hasTimeElapsed(2000L)) {
            this.chorusPos = null;
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.chorusPos != null) {
            RenderUtil.renderFilledBB(new AxisAlignedBB(this.chorusPos), this.boxColor.getAsColor());
            RenderUtil.renderOutlineBB(new AxisAlignedBB(this.chorusPos), this.outlineColor.getAsColor());
        }
    }
}
