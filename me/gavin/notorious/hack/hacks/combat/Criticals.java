// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.client.CPacketUseEntity;
import me.gavin.notorious.event.events.PacketEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Criticals", description = "Makes every one of your hits a critical.", category = Category.Combat)
public class Criticals extends Hack
{
    @RegisterSetting
    public final ModeSetting mode;
    
    public Criticals() {
        this.mode = new ModeSetting("Mode", "Packet", new String[] { "Packet", "FakeJump", "Jump", "MiniJump" });
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.mode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
            if (packet.func_149565_c() == CPacketUseEntity.Action.ATTACK && !(packet.func_149564_a((World)Criticals.mc.field_71441_e) instanceof EntityEnderCrystal) && !AutoCrystal.isPredicting) {
                if (!Criticals.mc.field_71439_g.field_70122_E) {
                    return;
                }
                if (Criticals.mc.field_71439_g.func_70090_H() || Criticals.mc.field_71439_g.func_180799_ab()) {
                    return;
                }
                if (this.mode.getMode().equals("Packet")) {
                    Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u + 0.0625, Criticals.mc.field_71439_g.field_70161_v, true));
                    Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u, Criticals.mc.field_71439_g.field_70161_v, false));
                    Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u + 1.1E-5, Criticals.mc.field_71439_g.field_70161_v, false));
                    Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u, Criticals.mc.field_71439_g.field_70161_v, false));
                }
                else if (this.mode.getMode().equals("FakeJump")) {
                    Criticals.mc.field_71439_g.field_70181_x = 0.10000000149011612;
                    Criticals.mc.field_71439_g.field_70143_R = 0.1f;
                    Criticals.mc.field_71439_g.field_70122_E = false;
                }
                else {
                    Criticals.mc.field_71439_g.func_70664_aZ();
                    if (this.mode.getMode().equals("MiniJump")) {
                        final EntityPlayerSP field_71439_g = Criticals.mc.field_71439_g;
                        field_71439_g.field_70181_x /= 2.0;
                    }
                }
            }
        }
    }
}
