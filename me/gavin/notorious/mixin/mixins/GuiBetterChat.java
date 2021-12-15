// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.gui.Gui;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.chat.ChatMods;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiNewChat.class })
public class GuiBetterChat
{
    @Redirect(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
    private void overrideChatBackgroundColour(final int left, final int top, final int right, final int bottom, final int color) {
        if ((!Notorious.INSTANCE.hackManager.getHack(ChatMods.class).clearChat.isEnabled() && Notorious.INSTANCE.hackManager.getHack(ChatMods.class).isEnabled()) || !Notorious.INSTANCE.hackManager.getHack(ChatMods.class).isEnabled()) {
            Gui.func_73734_a(left, top, right, bottom, color);
        }
    }
}
