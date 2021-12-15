// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.util.BlockUtil;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "BurrowBreaker", description = "ez", category = Category.Combat)
public class BurrowBreaker extends Hack
{
    @RegisterSetting
    public final BooleanSetting packet;
    @RegisterSetting
    public final BooleanSetting rotate;
    private boolean isBreaking;
    public BlockPos pos;
    public List<BlockPos> burrowedEntities;
    
    public BurrowBreaker() {
        this.packet = new BooleanSetting("Packet", false);
        this.rotate = new BooleanSetting("Rotate", true);
        this.isBreaking = false;
        this.burrowedEntities = new ArrayList<BlockPos>();
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerLivingUpdateEvent event) {
        this.burrowedEntities.clear();
        for (final Entity e : BurrowBreaker.mc.field_71441_e.field_72996_f) {
            if (e instanceof EntityPlayer) {
                if (e.equals((Object)BurrowBreaker.mc.field_71439_g)) {
                    return;
                }
                this.pos = new BlockPos(e.field_70165_t, e.field_70163_u + 0.2, e.field_70161_v);
                if (BurrowBreaker.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() == Blocks.field_150343_Z || BurrowBreaker.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() == Blocks.field_150477_bB) {
                    this.burrowedEntities.add(this.pos);
                }
                if (this.burrowedEntities.contains(this.pos) && BurrowBreaker.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemPickaxe && !this.isBreaking) {
                    BlockUtil.damageBlock(this.pos, this.packet.getValue(), this.rotate.getValue());
                    this.isBreaking = true;
                }
                if (BurrowBreaker.mc.field_71441_e.func_180495_p(this.pos).func_177230_c() != Blocks.field_150350_a) {
                    continue;
                }
                this.isBreaking = false;
            }
        }
    }
}
