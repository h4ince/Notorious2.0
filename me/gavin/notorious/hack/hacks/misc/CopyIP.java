// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.misc;

import net.minecraft.client.multiplayer.ServerData;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "CopyIP", description = "Copies the current server IP to clipboard", category = Category.Misc)
public class CopyIP extends Hack
{
    @Override
    protected void onEnable() {
        if (CopyIP.mc.func_147114_u() != null && CopyIP.mc.func_147104_D() != null && CopyIP.mc.func_147104_D().field_78845_b != null) {
            final ServerData data = CopyIP.mc.func_147104_D();
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(data.field_78845_b), null);
            this.notorious.messageManager.sendMessage("Copied IP " + ChatFormatting.GRAY + "[" + ChatFormatting.GREEN + data.field_78845_b + ChatFormatting.GRAY + "]" + ChatFormatting.RESET + " to clipboard");
        }
        else {
            this.notorious.messageManager.sendError("Unable to copy server IP.");
        }
        this.disable();
    }
}
