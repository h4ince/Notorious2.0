// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import net.minecraft.util.ChatAllowedCharacters;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import me.gavin.notorious.util.ColorUtil;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.Font;
import me.gavin.notorious.setting.StringSetting;
import me.gavin.notorious.gui.api.SettingComponent;

public class StringComponent extends SettingComponent
{
    private final StringSetting setting;
    private boolean isTyping;
    private CurrentString currentString;
    
    public StringComponent(final StringSetting setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.isTyping = false;
        this.currentString = new CurrentString("");
        this.setting = setting;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        final Font font = Notorious.INSTANCE.hackManager.getHack(Font.class);
        final float time = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).length.getValue();
        final float saturation = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).saturation.getValue();
        int color;
        if (Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).colorMode.getMode().equals("Rainbow")) {
            color = ColorUtil.getRainbow(time, saturation);
        }
        else {
            color = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
        }
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0, 0, 0, (int)Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).backgroundAlpha.getValue()).getRGB());
        Gui.func_73734_a(this.x, this.y, this.x + 2, this.y + this.height, color);
        if (this.isTyping) {
            if (font.isEnabled()) {
                Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.setting.getName() + " <" + this.currentString.getString() + ">", this.x + 9.0f, this.y + 3.0f, Color.WHITE);
            }
            else {
                Minecraft.func_71410_x().field_71466_p.func_175063_a(this.setting.getName() + " <" + this.currentString.getString() + ">", this.x + 9.0f, this.y + 1.0f, -1);
            }
        }
        else if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.setting.getName() + " <" + this.setting.getString() + ">", this.x + 9.0f, this.y + 3.0f, Color.WHITE);
        }
        else {
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.setting.getName() + " <" + this.setting.getString() + ">", this.x + 9.0f, this.y + 1.0f, -1);
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY) && mouseButton == 0) {
            this.isTyping = !this.isTyping;
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keyCode) {
        if (this.isTyping) {
            if (keyCode == 1) {
                return;
            }
            if (keyCode == 28) {
                this.enterString();
            }
            else if (keyCode == 14) {
                this.setString(removeLastChar(this.currentString.getString()));
            }
            else {
                Label_0122: {
                    if (keyCode == 47) {
                        if (!Keyboard.isKeyDown(157)) {
                            if (!Keyboard.isKeyDown(29)) {
                                break Label_0122;
                            }
                        }
                        try {
                            this.setString(this.currentString.getString() + Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
                        }
                        catch (Exception var5) {
                            var5.printStackTrace();
                        }
                        return;
                    }
                }
                if (ChatAllowedCharacters.func_71566_a(keyChar)) {
                    this.setString(this.currentString.getString() + keyChar);
                }
            }
        }
    }
    
    public static String removeLastChar(final String str) {
        String output = "";
        if (str != null && str.length() > 0) {
            output = str.substring(0, str.length() - 1);
        }
        return output;
    }
    
    @Override
    public int getTotalHeight() {
        return this.height;
    }
    
    private void enterString() {
        if (this.currentString.getString().isEmpty()) {
            this.setting.setString(this.setting.getString());
        }
        else {
            this.setting.setString(this.currentString.getString());
        }
    }
    
    public void setString(final String newString) {
        this.currentString = new CurrentString(newString);
    }
    
    public static class CurrentString
    {
        private String string;
        
        public CurrentString(final String string) {
            this.string = string;
        }
        
        public String getString() {
            return this.string;
        }
    }
}
