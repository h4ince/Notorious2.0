// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.gavin.notorious.util.RenderUtil;
import me.gavin.notorious.util.ColorUtil;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.util.ProjectionUtil;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Nametags", description = "ez", category = Category.Render)
public class Nametags extends Hack
{
    @RegisterSetting
    public final NumSetting scale;
    @RegisterSetting
    public final ColorSetting outlineColor;
    @RegisterSetting
    public final BooleanSetting rainbow;
    @RegisterSetting
    public final BooleanSetting armor;
    @RegisterSetting
    public final BooleanSetting items;
    
    public Nametags() {
        this.scale = new NumSetting("Scale", 2.5f, 1.0f, 5.0f, 0.1f);
        this.outlineColor = new ColorSetting("OutlineColor", 120, 81, 169, 255);
        this.rainbow = new BooleanSetting("Rainbow", true);
        this.armor = new BooleanSetting("Armor", false);
        this.items = new BooleanSetting("Items", true);
    }
    
    @SubscribeEvent
    public void onRender2d(final RenderGameOverlayEvent.Text event) {
        for (final EntityPlayer player : Nametags.mc.field_71441_e.field_73010_i) {
            if (player.equals((Object)Nametags.mc.field_71439_g)) {
                continue;
            }
            GlStateManager.func_179094_E();
            final double yAdd = player.func_70093_af() ? 1.75 : 2.25;
            final double deltaX = MathHelper.func_151238_b(player.field_70142_S, player.field_70165_t, (double)event.getPartialTicks());
            final double deltaY = MathHelper.func_151238_b(player.field_70137_T, player.field_70163_u, (double)event.getPartialTicks());
            final double deltaZ = MathHelper.func_151238_b(player.field_70136_U, player.field_70161_v, (double)event.getPartialTicks());
            final Vec3d projection = ProjectionUtil.toScaledScreenPos(new Vec3d(deltaX, deltaY, deltaZ).func_72441_c(0.0, yAdd, 0.0));
            GlStateManager.func_179137_b(projection.field_72450_a, projection.field_72448_b, 0.0);
            GlStateManager.func_179152_a(this.scale.getValue(), this.scale.getValue(), 0.0f);
            int ping = -1;
            if (Nametags.mc.func_147114_u() != null) {
                ping = Nametags.mc.func_147114_u().func_175102_a(player.func_110124_au()).func_178853_c();
            }
            final double health = player.func_110143_aJ() + player.func_110139_bj();
            String str = "";
            str = str + ChatFormatting.GRAY + "" + ping + "ms " + ChatFormatting.RESET;
            str += player.func_70005_c_();
            str = str + " " + this.getHealthColor(health) + String.format("%.1f", health);
            if (this.armor.isEnabled()) {
                int xOffset = 1;
                for (final ItemStack stack : player.func_184193_aE()) {
                    if (stack == null) {
                        continue;
                    }
                    final ItemStack armourStack = stack.func_77946_l();
                    this.renderItem(armourStack, xOffset + 10, -26);
                    xOffset -= 13;
                }
            }
            Nametags.mc.field_71439_g.func_70005_c_();
            RenderUtil.drawBorderedRect(-((Nametags.mc.field_71466_p.func_78256_a(str) + 2) / 2.0f), (float)(-(Nametags.mc.field_71466_p.field_78288_b + 2)), (float)((Nametags.mc.field_71466_p.func_78256_a(str) + 2) / 2), 1.0f, new Color(0, 0, 0, 125).getRGB(), this.rainbow.getValue() ? ColorUtil.getRainbow(6.0f, 1.0f) : this.outlineColor.getAsColor().getRGB());
            Nametags.mc.field_71466_p.func_175063_a(str, -(Nametags.mc.field_71466_p.func_78256_a(str) / 2.0f), (float)(-Nametags.mc.field_71466_p.field_78288_b), -1);
            GlStateManager.func_179121_F();
        }
    }
    
    private void renderItem(final ItemStack itemStack, final int posX, final int posY) {
        GlStateManager.func_179098_w();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179086_m(256);
        GlStateManager.func_179126_j();
        GlStateManager.func_179118_c();
        Nametags.mc.func_175599_af().field_77023_b = -150.0f;
        RenderHelper.func_74519_b();
        Nametags.mc.func_175599_af().func_180450_b(itemStack, posX, posY);
        Nametags.mc.func_175599_af().func_175030_a(Nametags.mc.field_71466_p, itemStack, posX, posY);
        RenderHelper.func_74518_a();
        Nametags.mc.func_175599_af().field_77023_b = 0.0f;
    }
    
    private ChatFormatting getHealthColor(final double health) {
        if (health >= 15.0) {
            return ChatFormatting.GREEN;
        }
        if (health >= 10.0) {
            return ChatFormatting.YELLOW;
        }
        if (health >= 5.0) {
            return ChatFormatting.RED;
        }
        return ChatFormatting.DARK_RED;
    }
}
