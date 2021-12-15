// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Sprint", description = "Holds sprint.", category = Category.Movement)
public class Sprint extends Hack
{
    @RegisterSetting
    public final ModeSetting sprintMode;
    
    public Sprint() {
        this.sprintMode = new ModeSetting("SprintMode", "Legit", new String[] { "Legit", "Rage" });
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.sprintMode.getMode() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final PlayerLivingUpdateEvent event) {
        if (this.sprintMode.getMode().equals("Legit")) {
            if (!Sprint.mc.field_71439_g.func_70093_af() && !Sprint.mc.field_71439_g.func_184587_cr() && !Sprint.mc.field_71439_g.field_70123_F && !Sprint.mc.field_71439_g.func_70051_ag()) {
                Sprint.mc.field_71439_g.func_70031_b(true);
            }
        }
        else if ((Sprint.mc.field_71439_g.field_191988_bg != 0.0f || Sprint.mc.field_71439_g.field_70702_br != 0.0f) && !Sprint.mc.field_71439_g.func_70051_ag()) {
            Sprint.mc.field_71439_g.func_70031_b(true);
        }
    }
}
