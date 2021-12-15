// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.math.Vec3d;
import java.util.Iterator;
import me.gavin.notorious.util.ProjectionUtil;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "ShulkerNametag", description = "ez", category = Category.Render)
public class ShulkerRender extends Hack
{
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        for (final Entity e : ShulkerRender.mc.field_71441_e.field_72996_f) {
            if (e instanceof EntityItem) {
                final EntityItem item = (EntityItem)e;
                if (!(item.func_92059_d().func_77973_b() instanceof ItemShulkerBox)) {
                    continue;
                }
                final Vec3d projection = ProjectionUtil.toScaledScreenPos(item.func_174791_d());
                this.renderShulkerToolTip(item.func_92059_d(), (int)projection.field_72450_a - 80, (int)projection.field_72448_b - 80);
            }
        }
    }
    
    public void renderShulkerToolTip(final ItemStack stack, final int x, final int y) {
        final NBTTagCompound tagCompound = stack.func_77978_p();
        final NBTTagCompound blockEntityTag;
        if (tagCompound != null && tagCompound.func_150297_b("BlockEntityTag", 10) && (blockEntityTag = tagCompound.func_74775_l("BlockEntityTag")).func_150297_b("Items", 9)) {
            GlStateManager.func_179098_w();
            GlStateManager.func_179140_f();
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.func_179126_j();
            RenderHelper.func_74520_c();
            GlStateManager.func_179091_B();
            GlStateManager.func_179142_g();
            GlStateManager.func_179145_e();
            final NonNullList nonnulllist = NonNullList.func_191197_a(27, (Object)ItemStack.field_190927_a);
            ItemStackHelper.func_191283_b(blockEntityTag, nonnulllist);
            for (int i = 0; i < nonnulllist.size(); ++i) {
                final int iX = x + i % 9 * 18 + 8;
                final int iY = y + i / 9 * 18 + 18;
                final ItemStack itemStack = (ItemStack)nonnulllist.get(i);
                this.renderItem(itemStack, iX, iY);
            }
            GlStateManager.func_179140_f();
            GlStateManager.func_179084_k();
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    private void renderItem(final ItemStack itemStack, final int posX, final int posY) {
        GlStateManager.func_179098_w();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179086_m(256);
        GlStateManager.func_179126_j();
        GlStateManager.func_179118_c();
        ShulkerRender.mc.func_175599_af().field_77023_b = -150.0f;
        RenderHelper.func_74519_b();
        ShulkerRender.mc.func_175599_af().func_180450_b(itemStack, posX, posY);
        ShulkerRender.mc.func_175599_af().func_175030_a(ShulkerRender.mc.field_71466_p, itemStack, posX, posY);
        RenderHelper.func_74518_a();
        ShulkerRender.mc.func_175599_af().field_77023_b = 0.0f;
    }
}
