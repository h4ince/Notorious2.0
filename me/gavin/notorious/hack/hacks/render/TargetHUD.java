// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import net.minecraft.client.renderer.RenderHelper;
import me.gavin.notorious.util.MathUtil;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import me.gavin.notorious.util.ColorUtil;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import net.minecraft.entity.Entity;
import java.util.Comparator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.hack.hacks.combat.AutoCrystal;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "TargetHUD", description = "ez", category = Category.Render)
public class TargetHUD extends Hack
{
    @RegisterSetting
    public final NumSetting x;
    @RegisterSetting
    public final NumSetting y;
    @RegisterSetting
    public final NumSetting range;
    @RegisterSetting
    public final BooleanSetting autoCrystalTarget;
    @RegisterSetting
    public final BooleanSetting friendSkip;
    @RegisterSetting
    public final BooleanSetting rainbowLine;
    public String nameString;
    
    public TargetHUD() {
        this.x = new NumSetting("X", 2.0f, 0.1f, 1000.0f, 0.1f);
        this.y = new NumSetting("Y", 2.0f, 0.1f, 600.0f, 0.1f);
        this.range = new NumSetting("Range", 4.0f, 1.0f, 6.0f, 1.0f);
        this.autoCrystalTarget = new BooleanSetting("AutoCrystalTarget", true);
        this.friendSkip = new BooleanSetting("FriendSkip", false);
        this.rainbowLine = new BooleanSetting("RainbowLine", true);
    }
    
    @Override
    public String getMetaData() {
        if (this.nameString != null) {
            return " [" + ChatFormatting.GRAY + this.nameString + ChatFormatting.RESET + "]";
        }
        return "";
    }
    
    @SubscribeEvent
    public void onUpdate(final RenderGameOverlayEvent.Text event) {
        EntityPlayer entityPlayer;
        if (this.autoCrystalTarget.isEnabled()) {
            entityPlayer = AutoCrystal.target;
        }
        else {
            entityPlayer = (EntityPlayer)TargetHUD.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityPlayer).filter(entity -> entity != TargetHUD.mc.field_71439_g).map(entity -> entity).min(Comparator.comparing(c -> TargetHUD.mc.field_71439_g.func_70032_d(c))).orElse(null);
        }
        if (entityPlayer != null) {
            if (this.friendSkip.isEnabled() && this.notorious.friend.isFriend(entityPlayer.func_70005_c_())) {
                return;
            }
            if (entityPlayer.func_70032_d((Entity)TargetHUD.mc.field_71439_g) <= this.range.getValue()) {
                this.nameString = entityPlayer.getDisplayNameString();
                Gui.func_73734_a((int)this.x.getValue(), (int)this.y.getValue(), (int)this.x.getValue() + 190, (int)this.y.getValue() + 50, new Color(0, 0, 0, 255).getRGB());
                if (this.rainbowLine.isEnabled()) {
                    Gui.func_73734_a((int)this.x.getValue(), (int)this.y.getValue(), (int)this.x.getValue() + 190, (int)this.y.getValue() + 1, ColorUtil.getRainbow(6.0f, 1.0f));
                }
                TargetHUD.mc.field_71466_p.func_175063_a(entityPlayer.func_70005_c_(), this.x.getValue() + 5.0f, this.y.getValue() + 5.0f, this.notorious.friend.isFriend(entityPlayer.func_70005_c_()) ? new Color(0, 255, 234).getRGB() : -1);
                final int healthInt = (int)(entityPlayer.func_110143_aJ() + entityPlayer.func_110139_bj());
                Color healthColor = null;
                if (healthInt > 19) {
                    healthColor = new Color(0, 255, 0, 255);
                }
                else if (healthInt > 10) {
                    healthColor = new Color(255, 255, 0, 255);
                }
                else if (healthInt > 0) {
                    healthColor = new Color(255, 0, 0, 255);
                }
                else {
                    healthColor = new Color(0, 0, 0, 255);
                }
                TargetHUD.mc.field_71466_p.func_175063_a("HP:" + healthInt, this.x.getValue() + 5.0f, this.y.getValue() + 15.0f, healthColor.getRGB());
                GlStateManager.func_179124_c(1.0f, 1.0f, 1.0f);
                GuiInventory.func_147046_a((int)this.x.getValue() + 115, (int)this.y.getValue() + 48, 20, 0.0f, 0.0f, (EntityLivingBase)entityPlayer);
                Color pingColor = null;
                if (this.getPing(entityPlayer) < 49) {
                    pingColor = new Color(0, 255, 0, 255);
                }
                else if (this.getPing(entityPlayer) < 99) {
                    pingColor = new Color(255, 255, 0, 255);
                }
                else if (this.getPing(entityPlayer) >= 100) {
                    pingColor = new Color(255, 0, 0, 255);
                }
                else {
                    pingColor = new Color(0, 0, 0, 255);
                }
                TargetHUD.mc.field_71466_p.func_175063_a("Ping:" + this.getPing(entityPlayer), this.x.getValue() + 5.0f, this.y.getValue() + 25.0f, pingColor.getRGB());
                String fuckedDetector = "";
                Color fuckedColor;
                if (this.isFucked(entityPlayer)) {
                    fuckedDetector = "FUCKED";
                    fuckedColor = new Color(0, 255, 0, 255);
                }
                else if (!this.isFucked(entityPlayer)) {
                    fuckedDetector = "SAFE";
                    fuckedColor = new Color(255, 0, 0, 255);
                }
                else {
                    fuckedColor = new Color(0, 0, 0, 255);
                }
                TargetHUD.mc.field_71466_p.func_175063_a(fuckedDetector, this.x.getValue() + 5.0f, this.y.getValue() + 35.0f, fuckedColor.getRGB());
                final ItemStack itemStack = new ItemStack(Items.field_190929_cY);
                this.renderItem(itemStack, (int)this.x.getValue() + 142, (int)this.y.getValue() + 26);
                String pops = "0";
                if (this.notorious.popListener.popMap.get(entityPlayer.func_70005_c_()) != null) {
                    pops = this.notorious.popListener.popMap.get(entityPlayer.func_70005_c_()).toString();
                }
                TargetHUD.mc.field_71466_p.func_175063_a(pops, this.x.getValue() + 158.0f, this.y.getValue() + 32.0f, -1);
                int yOffset = 10;
                for (final ItemStack stack : entityPlayer.func_184193_aE()) {
                    if (stack == null) {
                        continue;
                    }
                    final ItemStack armourStack = stack.func_77946_l();
                    this.renderItem(armourStack, (int)this.x.getValue() + 125, yOffset + (int)this.y.getValue() + 26);
                    yOffset -= 13;
                }
                final ArrayList<Block> surroundblocks = this.getSurroundBlocks((EntityLivingBase)entityPlayer);
                this.renderItem(new ItemStack((Block)surroundblocks.get(0)), (int)this.x.getValue() + 73, (int)this.y.getValue() + 10);
                this.renderItem(new ItemStack((Block)surroundblocks.get(1)), (int)this.x.getValue() + 61, (int)this.y.getValue() + 21);
                this.renderItem(new ItemStack((Block)surroundblocks.get(2)), (int)this.x.getValue() + 73, (int)this.y.getValue() + 32);
                this.renderItem(new ItemStack((Block)surroundblocks.get(3)), (int)this.x.getValue() + 85, (int)this.y.getValue() + 21);
                final ItemStack mainHand = new ItemStack(entityPlayer.func_184614_ca().func_77973_b());
                final ItemStack offHand = new ItemStack(entityPlayer.func_184592_cb().func_77973_b());
                this.renderItem(mainHand, (int)this.x.getValue() + 160, (int)this.y.getValue() + 5);
                this.renderItem(offHand, (int)this.x.getValue() + 142, (int)this.y.getValue() + 5);
            }
        }
    }
    
    public boolean isFucked(final EntityPlayer player) {
        final BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u - 1.0, player.field_70161_v);
        return this.canPlaceCrystal(pos.func_177968_d()) || (this.canPlaceCrystal(pos.func_177968_d().func_177968_d()) && TargetHUD.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 1)).func_177230_c() == Blocks.field_150350_a) || (this.canPlaceCrystal(pos.func_177974_f()) || (this.canPlaceCrystal(pos.func_177974_f().func_177974_f()) && TargetHUD.mc.field_71441_e.func_180495_p(pos.func_177982_a(1, 1, 0)).func_177230_c() == Blocks.field_150350_a)) || (this.canPlaceCrystal(pos.func_177976_e()) || (this.canPlaceCrystal(pos.func_177976_e().func_177976_e()) && TargetHUD.mc.field_71441_e.func_180495_p(pos.func_177982_a(-1, 1, 0)).func_177230_c() == Blocks.field_150350_a)) || (this.canPlaceCrystal(pos.func_177978_c()) || (this.canPlaceCrystal(pos.func_177978_c().func_177978_c()) && TargetHUD.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, -1)).func_177230_c() == Blocks.field_150350_a));
    }
    
    public boolean canPlaceCrystal(final BlockPos pos) {
        final Block block = TargetHUD.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        if (block == Blocks.field_150343_Z || block == Blocks.field_150357_h) {
            final Block floor = TargetHUD.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 0)).func_177230_c();
            final Block ceil = TargetHUD.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 2, 0)).func_177230_c();
            if (floor == Blocks.field_150350_a && ceil == Blocks.field_150350_a && TargetHUD.mc.field_71441_e.func_72839_b((Entity)null, new AxisAlignedBB(pos.func_177982_a(0, 1, 0))).isEmpty() && TargetHUD.mc.field_71441_e.func_72839_b((Entity)null, new AxisAlignedBB(pos.func_177982_a(0, 2, 0))).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public int getPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = MathUtil.clamp(Objects.requireNonNull(TargetHUD.mc.func_147114_u()).func_175102_a(player.func_110124_au()).func_178853_c(), 1, 99999);
        }
        catch (NullPointerException ex) {}
        return ping;
    }
    
    private void renderItem(final ItemStack itemStack, final int posX, final int posY) {
        GlStateManager.func_179098_w();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179086_m(256);
        GlStateManager.func_179126_j();
        GlStateManager.func_179118_c();
        TargetHUD.mc.func_175599_af().field_77023_b = -150.0f;
        RenderHelper.func_74519_b();
        TargetHUD.mc.func_175599_af().func_180450_b(itemStack, posX, posY);
        TargetHUD.mc.func_175599_af().func_175030_a(TargetHUD.mc.field_71466_p, itemStack, posX, posY);
        RenderHelper.func_74518_a();
        TargetHUD.mc.func_175599_af().field_77023_b = 0.0f;
    }
    
    private ArrayList<Block> getSurroundBlocks(final EntityLivingBase e) {
        final ArrayList<Block> surroundblocks = new ArrayList<Block>();
        final BlockPos entityblock = new BlockPos(Math.floor(e.field_70165_t), Math.floor(e.field_70163_u), Math.floor(e.field_70161_v));
        surroundblocks.add(TargetHUD.mc.field_71441_e.func_180495_p(entityblock.func_177978_c()).func_177230_c());
        surroundblocks.add(TargetHUD.mc.field_71441_e.func_180495_p(entityblock.func_177974_f()).func_177230_c());
        surroundblocks.add(TargetHUD.mc.field_71441_e.func_180495_p(entityblock.func_177968_d()).func_177230_c());
        surroundblocks.add(TargetHUD.mc.field_71441_e.func_180495_p(entityblock.func_177976_e()).func_177230_c());
        return surroundblocks;
    }
}
