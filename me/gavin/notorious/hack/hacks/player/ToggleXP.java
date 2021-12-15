// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.player;

import net.minecraft.item.Item;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import me.gavin.notorious.mixin.mixins.accessor.IMinecraftMixin;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.init.Items;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ToggleXP", description = "Ez", category = Category.Player)
public class ToggleXP extends Hack
{
    @RegisterSetting
    public final BooleanSetting footXP;
    private int serverSlot;
    
    public ToggleXP() {
        this.footXP = new BooleanSetting("FootXP", true);
        this.serverSlot = -1;
    }
    
    public void onDisable() {
        ToggleXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(ToggleXP.mc.field_71439_g.field_71071_by.field_70461_c));
        this.serverSlot = -1;
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        final int getSlot = this.getXPSlot();
        if (getSlot != -1) {
            if (this.serverSlot == -1) {
                ToggleXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(getSlot));
                this.serverSlot = getSlot;
            }
            else if (ToggleXP.mc.field_71439_g.field_71071_by.func_70301_a(this.serverSlot).func_77973_b() != Items.field_151062_by) {
                this.serverSlot = -1;
            }
            else {
                if (this.footXP.isEnabled()) {
                    ToggleXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(ToggleXP.mc.field_71439_g.field_70177_z, 90.0f, ToggleXP.mc.field_71439_g.field_70122_E));
                }
                ((IMinecraftMixin)ToggleXP.mc).setRightClickDelayTimerAccessor(0);
                ToggleXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            }
        }
    }
    
    private int getXPSlot() {
        final Item item = Items.field_151062_by;
        final Minecraft mc = Minecraft.func_71410_x();
        int itemSlot = (mc.field_71439_g.func_184614_ca().func_77973_b() == item) ? mc.field_71439_g.field_71071_by.field_70461_c : -1;
        if (itemSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (mc.field_71439_g.field_71071_by.func_70301_a(l).func_77973_b() == item) {
                    itemSlot = l;
                }
            }
        }
        return itemSlot;
    }
}
