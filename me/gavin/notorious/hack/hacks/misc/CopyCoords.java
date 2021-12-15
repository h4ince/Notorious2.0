// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "CopyCoords", description = "ez", category = Category.Misc)
public class CopyCoords extends Hack
{
    @Override
    protected void onEnable() {
        if (CopyCoords.mc.field_71439_g != null && CopyCoords.mc.field_71441_e != null) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(CopyCoords.mc.field_71439_g.func_180425_c().toString()), null);
            this.notorious.messageManager.sendMessage("Copied Coords " + ChatFormatting.GRAY + "[" + ChatFormatting.GREEN + "X:" + CopyCoords.mc.field_71439_g.func_180425_c().func_177958_n() + " Y:" + CopyCoords.mc.field_71439_g.func_180425_c().func_177956_o() + " Z:" + CopyCoords.mc.field_71439_g.func_180425_c().func_177952_p() + ChatFormatting.GRAY + "]" + ChatFormatting.RESET + " to clipboard");
        }
        else {
            this.notorious.messageManager.sendError("Unable to copy coords?");
        }
        this.disable();
    }
}
