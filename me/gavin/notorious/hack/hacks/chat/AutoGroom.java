// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import java.util.ArrayList;
import me.gavin.notorious.util.TickTimer;
import java.util.List;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "AutoGroom", description = "SyndicateNA simulator", category = Category.Chat)
public class AutoGroom extends Hack
{
    @RegisterSetting
    public final NumSetting delay;
    private List<String> peopleInArea;
    private List<String> peopleNearbyNew;
    private List<String> peopleToRemove;
    private TickTimer timer;
    
    public AutoGroom() {
        this.delay = new NumSetting("Delay", 10.0f, 1.0f, 15.0f, 1.0f);
        this.timer = new TickTimer();
    }
    
    public void onEnable() {
        this.peopleInArea = new ArrayList<String>();
        this.peopleToRemove = new ArrayList<String>();
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        this.peopleNearbyNew = new ArrayList<String>();
        final List<EntityPlayer> playerEntities = (List<EntityPlayer>)AutoGroom.mc.field_71441_e.field_73010_i;
        for (final Entity e : playerEntities) {
            if (e.func_70005_c_().equals(AutoGroom.mc.field_71439_g.func_70005_c_())) {
                continue;
            }
            this.peopleNearbyNew.add(e.func_70005_c_());
        }
        if (this.peopleNearbyNew.size() > 0) {
            for (final String name : this.peopleNearbyNew) {
                if (!this.peopleInArea.contains(name)) {
                    if (this.timer.hasTicksPassed((long)(this.delay.getValue() * 20.0f))) {
                        AutoGroom.mc.field_71439_g.func_71165_d("/msg " + name + " hewwo wittle kitten, come be my wittle discord girl? | discord.gg/BU9g9HgGBa");
                        this.timer.reset();
                    }
                    this.peopleInArea.add(name);
                }
            }
        }
        if (this.peopleInArea.size() > 0) {
            for (final String name : this.peopleInArea) {
                if (!this.peopleNearbyNew.contains(name)) {
                    this.peopleToRemove.add(name);
                    if (!this.timer.hasTicksPassed((long)(this.delay.getValue() * 20.0f))) {
                        continue;
                    }
                    AutoGroom.mc.field_71439_g.func_71165_d("/msg " + name + " ow no pwease dont weave me my wittle kitten UWU");
                    this.timer.reset();
                }
            }
            if (this.peopleToRemove.size() > 0) {
                for (final String name : this.peopleToRemove) {
                    this.peopleInArea.remove(name);
                }
                this.peopleToRemove.clear();
            }
        }
    }
}
