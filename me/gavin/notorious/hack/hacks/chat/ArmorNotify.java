// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraft.item.ItemStack;
import me.gavin.notorious.util.ColorUtil;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ArmorNotify", description = "ez", category = Category.Chat)
public class ArmorNotify extends Hack
{
    @RegisterSetting
    public final NumSetting x;
    @RegisterSetting
    public final NumSetting y;
    @RegisterSetting
    public final BooleanSetting rainbow;
    boolean hasAnnounced;
    
    public ArmorNotify() {
        this.x = new NumSetting("X", 2.0f, 0.1f, 1000.0f, 0.1f);
        this.y = new NumSetting("Y", 2.0f, 0.1f, 600.0f, 0.1f);
        this.rainbow = new BooleanSetting("Rainbow", true);
        this.hasAnnounced = false;
    }
    
    @SubscribeEvent
    public void onChat(final PlayerLivingUpdateEvent event) {
        for (final Entity e : ArmorNotify.mc.field_71441_e.field_72996_f) {
            final boolean armorDurability = this.getArmorDurability();
            if (e.equals((Object)ArmorNotify.mc.field_71439_g)) {
                return;
            }
            if (!(e instanceof EntityPlayer) || !this.notorious.friend.isFriend(e.func_70005_c_())) {
                continue;
            }
            if (armorDurability && !this.hasAnnounced) {
                ArmorNotify.mc.field_71439_g.func_71165_d("/msg " + ((EntityPlayer)e).getDisplayNameString() + " Hey bro you need to mend your armor :o");
                this.hasAnnounced = true;
            }
            if (!this.armorAboveSeventyFive()) {
                continue;
            }
            this.hasAnnounced = false;
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final RenderGameOverlayEvent.Text event) {
        final boolean armorDurability = this.getArmorDurability();
        if (armorDurability) {
            ArmorNotify.mc.field_71466_p.func_175063_a("Armor is below 50%", this.x.getValue(), this.y.getValue(), this.rainbow.getValue() ? ColorUtil.getRainbow(6.0f, 1.0f) : -1);
        }
    }
    
    private boolean getArmorDurability() {
        for (final ItemStack itemStack : ArmorNotify.mc.field_71439_g.field_71071_by.field_70460_b) {
            if (itemStack.func_77958_k() / 2 < itemStack.func_77952_i()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean armorAboveSeventyFive() {
        for (final ItemStack itemStack : ArmorNotify.mc.field_71439_g.field_71071_by.field_70460_b) {
            if (itemStack.func_77958_k() / 3 < itemStack.func_77958_k()) {
                return true;
            }
        }
        return false;
    }
}
