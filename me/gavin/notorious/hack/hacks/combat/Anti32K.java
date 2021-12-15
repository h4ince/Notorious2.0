// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.combat;

import me.gavin.notorious.util.RenderUtil;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import me.gavin.notorious.util.BlockUtil;
import net.minecraft.util.math.MathHelper;
import me.gavin.notorious.event.events.PlayerWalkingUpdateEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.util.NColor;
import net.minecraft.util.math.BlockPos;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Anti32K", description = "nukes hoppers", category = Category.Combat)
public class Anti32K extends Hack
{
    @RegisterSetting
    public final NumSetting range;
    @RegisterSetting
    public final ColorSetting boxColor;
    @RegisterSetting
    public final ColorSetting outlineColor;
    private BlockPos targetedBlock;
    
    public Anti32K() {
        this.range = new NumSetting("Range", 5.0f, 0.0f, 6.0f, 0.5f);
        this.boxColor = new ColorSetting("Box", new NColor(255, 255, 255, 125));
        this.outlineColor = new ColorSetting("Outline", new NColor(255, 255, 255, 255));
        this.targetedBlock = null;
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.range.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final PlayerWalkingUpdateEvent.Pre event) {
        if (this.targetedBlock == null) {
            for (final BlockPos pos : BlockUtil.getSurroundingBlocks(MathHelper.func_76123_f(this.range.getValue()), true)) {
                final Block block = Anti32K.mc.field_71441_e.func_180495_p(pos).func_177230_c();
                if (block == Blocks.field_150438_bZ) {
                    this.targetedBlock = pos;
                    break;
                }
            }
        }
        else {
            if (Anti32K.mc.field_71441_e.func_180495_p(this.targetedBlock).func_177230_c() == Blocks.field_150350_a) {
                this.targetedBlock = null;
                return;
            }
            if (this.targetedBlock.func_185332_f(Anti32K.mc.field_71439_g.func_180425_c().func_177958_n(), Anti32K.mc.field_71439_g.func_180425_c().func_177956_o(), Anti32K.mc.field_71439_g.func_180425_c().func_177952_p()) > this.range.getValue()) {
                this.targetedBlock = null;
                return;
            }
            BlockUtil.damageBlock(this.targetedBlock, false, true);
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.targetedBlock != null) {
            final AxisAlignedBB bb = new AxisAlignedBB(this.targetedBlock);
            RenderUtil.renderFilledBB(bb, this.boxColor.getAsColor());
            RenderUtil.renderOutlineBB(bb, this.outlineColor.getAsColor());
        }
    }
}
