// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemEgg;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import me.gavin.notorious.event.events.PacketEvent;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "BowBomb", description = "Uno hitter w bows", category = Category.Combat)
public class BowBomb extends Hack
{
    private boolean shooting;
    private long lastShootTime;
    @RegisterSetting
    public final BooleanSetting Bows;
    @RegisterSetting
    public final BooleanSetting pearls;
    @RegisterSetting
    public final BooleanSetting eggs;
    @RegisterSetting
    public final BooleanSetting snowballs;
    @RegisterSetting
    public final NumSetting Timeout;
    @RegisterSetting
    public final NumSetting spoofs;
    @RegisterSetting
    public final BooleanSetting bypass;
    @RegisterSetting
    public final BooleanSetting debug;
    
    public BowBomb() {
        this.Bows = new BooleanSetting("Bows", true);
        this.pearls = new BooleanSetting("Pearls", true);
        this.eggs = new BooleanSetting("Eggs", true);
        this.snowballs = new BooleanSetting("SnowBallz", true);
        this.Timeout = new NumSetting("Timeout", 5000.0f, 10.0f, 20000.0f, 1.0f);
        this.spoofs = new NumSetting("Spoofs", 10.0f, 1.0f, 400.0f, 1.0f);
        this.bypass = new BooleanSetting("Bypass", false);
        this.debug = new BooleanSetting("Debug", false);
    }
    
    public void onEnable() {
        if (this.isEnabled()) {
            this.shooting = false;
            this.lastShootTime = System.currentTimeMillis();
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging packet = (CPacketPlayerDigging)event.getPacket();
            if (packet.func_180762_c() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                final ItemStack handStack = BowBomb.mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND);
                if (!handStack.func_190926_b() && handStack.func_77973_b() != null && handStack.func_77973_b() instanceof ItemBow && this.Bows.getValue()) {
                    this.doSpoofs();
                    if (this.debug.getValue()) {
                        this.notorious.messageManager.sendMessage("trying to spoof");
                    }
                }
            }
        }
        else if (event.getPacket() instanceof CPacketPlayerTryUseItem) {
            final CPacketPlayerTryUseItem packet2 = (CPacketPlayerTryUseItem)event.getPacket();
            if (packet2.func_187028_a() == EnumHand.MAIN_HAND) {
                final ItemStack handStack = BowBomb.mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND);
                if (!handStack.func_190926_b() && handStack.func_77973_b() != null) {
                    if (handStack.func_77973_b() instanceof ItemEgg && this.eggs.getValue()) {
                        this.doSpoofs();
                    }
                    else if (handStack.func_77973_b() instanceof ItemEnderPearl && this.pearls.getValue()) {
                        this.doSpoofs();
                    }
                    else if (handStack.func_77973_b() instanceof ItemSnowball && this.snowballs.getValue()) {
                        this.doSpoofs();
                    }
                }
            }
        }
    }
    
    private void doSpoofs() {
        if (System.currentTimeMillis() - this.lastShootTime >= this.Timeout.getValue()) {
            this.shooting = true;
            this.lastShootTime = System.currentTimeMillis();
            BowBomb.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)BowBomb.mc.field_71439_g, CPacketEntityAction.Action.START_SPRINTING));
            for (int index = 0; index < this.spoofs.getValue(); ++index) {
                if (this.bypass.getValue()) {
                    BowBomb.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(BowBomb.mc.field_71439_g.field_70165_t, BowBomb.mc.field_71439_g.field_70163_u + 1.0E-10, BowBomb.mc.field_71439_g.field_70161_v, false));
                    BowBomb.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(BowBomb.mc.field_71439_g.field_70165_t, BowBomb.mc.field_71439_g.field_70163_u - 1.0E-10, BowBomb.mc.field_71439_g.field_70161_v, true));
                }
                else {
                    BowBomb.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(BowBomb.mc.field_71439_g.field_70165_t, BowBomb.mc.field_71439_g.field_70163_u - 1.0E-10, BowBomb.mc.field_71439_g.field_70161_v, true));
                    BowBomb.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(BowBomb.mc.field_71439_g.field_70165_t, BowBomb.mc.field_71439_g.field_70163_u + 1.0E-10, BowBomb.mc.field_71439_g.field_70161_v, false));
                }
            }
            if (this.debug.getValue()) {
                this.notorious.messageManager.sendMessage("Spoofed");
            }
            this.shooting = false;
        }
    }
}
