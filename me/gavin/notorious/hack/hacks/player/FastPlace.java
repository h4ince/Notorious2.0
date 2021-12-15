// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.mixin.mixins.accessor.IMinecraftMixin;
import net.minecraft.init.Items;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "FastPlace", description = "Use items faster", category = Category.Player)
public class FastPlace extends Hack
{
    @RegisterSetting
    public final BooleanSetting xp;
    @RegisterSetting
    public final BooleanSetting crystals;
    @RegisterSetting
    public final BooleanSetting all;
    
    public FastPlace() {
        this.xp = new BooleanSetting("XP", true);
        this.crystals = new BooleanSetting("Crystals", true);
        this.all = new BooleanSetting("All", false);
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        if (FastPlace.mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151062_by && this.xp.isEnabled()) {
            ((IMinecraftMixin)FastPlace.mc).setRightClickDelayTimerAccessor(0);
        }
        if (FastPlace.mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP && this.crystals.isEnabled()) {
            ((IMinecraftMixin)FastPlace.mc).setRightClickDelayTimerAccessor(0);
        }
        if (this.all.isEnabled()) {
            ((IMinecraftMixin)FastPlace.mc).setRightClickDelayTimerAccessor(0);
        }
    }
}
