// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Respawn", description = "Respawn automatically", category = Category.Misc)
public class Respawn extends Hack
{
    @RegisterSetting
    public final BooleanSetting antiDeathScreen;
    @RegisterSetting
    public final BooleanSetting deathCoord;
    String deathCoords;
    
    public Respawn() {
        this.antiDeathScreen = new BooleanSetting("Respawn", true);
        this.deathCoord = new BooleanSetting("DeathCoords", true);
        this.deathCoords = "X:0 Y:0 Z:0";
    }
    
    @Override
    public String getMetaData() {
        return " [" + ChatFormatting.GRAY + this.deathCoords + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onDeath(final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiGameOver) {
            final int x = Respawn.mc.field_71439_g.func_180425_c().func_177958_n();
            final int y = Respawn.mc.field_71439_g.func_180425_c().func_177956_o();
            final int z = Respawn.mc.field_71439_g.func_180425_c().func_177952_p();
            if (this.antiDeathScreen.isEnabled()) {
                event.setCanceled(true);
            }
            if (Respawn.mc.field_71439_g.func_110143_aJ() <= 0.0f) {
                Respawn.mc.field_71439_g.func_71004_bE();
            }
            if (this.deathCoord.isEnabled()) {
                if (Respawn.mc.field_71439_g.field_71093_bK == -1) {
                    this.notorious.messageManager.sendRemovableMessage("You died at X: " + ChatFormatting.RED + ChatFormatting.BOLD + x + ChatFormatting.RESET + " Y: " + ChatFormatting.RED + ChatFormatting.BOLD + y + ChatFormatting.RESET + " Z: " + ChatFormatting.RED + ChatFormatting.BOLD + z + ChatFormatting.RESET + " Dimension: " + ChatFormatting.RED + ChatFormatting.BOLD + "Nether", 1);
                }
                if (Respawn.mc.field_71439_g.field_71093_bK == 0) {
                    this.notorious.messageManager.sendRemovableMessage("You died at X: " + ChatFormatting.RED + ChatFormatting.BOLD + x + ChatFormatting.RESET + " Y: " + ChatFormatting.RED + ChatFormatting.BOLD + y + ChatFormatting.RESET + " Z: " + ChatFormatting.RED + ChatFormatting.BOLD + z + ChatFormatting.RESET + " Dimension: " + ChatFormatting.GREEN + ChatFormatting.BOLD + "Overworld", 2);
                }
                if (Respawn.mc.field_71439_g.field_71093_bK == 1) {
                    this.notorious.messageManager.sendRemovableMessage("You died at X: " + ChatFormatting.RED + ChatFormatting.BOLD + x + ChatFormatting.RESET + " Y: " + ChatFormatting.RED + ChatFormatting.BOLD + y + ChatFormatting.RESET + " Z: " + ChatFormatting.RED + ChatFormatting.BOLD + z + ChatFormatting.RESET + " Dimension: " + ChatFormatting.DARK_PURPLE + ChatFormatting.BOLD + "End", 3);
                }
            }
            this.deathCoords = "X:" + x + " Y:" + y + " Z:" + z;
        }
    }
}
