// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.client;

import me.gavin.notorious.util.MathUtil;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import java.util.Calendar;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.client.multiplayer.ServerData;
import me.gavin.notorious.friend.Friend;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.math.MathHelper;
import me.gavin.notorious.util.AnimationUtil;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import me.gavin.notorious.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import me.gavin.notorious.Notorious;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "HUD", description = "ez", category = Category.Client)
public class HUD extends Hack
{
    @RegisterSetting
    public final ModeSetting renderMode;
    @RegisterSetting
    public final BooleanSetting waterMark;
    @RegisterSetting
    public final BooleanSetting MoreInfo;
    @RegisterSetting
    public final BooleanSetting Momentum;
    @RegisterSetting
    public final BooleanSetting arrayList;
    @RegisterSetting
    public final BooleanSetting friendList;
    @RegisterSetting
    public final BooleanSetting welcomer;
    @RegisterSetting
    public final ModeSetting watermarkMode;
    @RegisterSetting
    public final ModeSetting MoreInfoMode;
    @RegisterSetting
    public final ModeSetting arrayListMode;
    @RegisterSetting
    public final ModeSetting friendListMode;
    @RegisterSetting
    public final ModeSetting welcomerMode;
    @RegisterSetting
    public final ModeSetting welcomerColor;
    @RegisterSetting
    public final ColorSetting rgbWatermark;
    @RegisterSetting
    public final ColorSetting rgbArrayList;
    @RegisterSetting
    public final ColorSetting rgbArrayList2;
    @RegisterSetting
    public final ColorSetting rgbFriendList;
    @RegisterSetting
    public final ColorSetting rgbWelcomer;
    @RegisterSetting
    public final NumSetting length;
    @RegisterSetting
    public final NumSetting saturation;
    @RegisterSetting
    public final NumSetting sexySpeed;
    @RegisterSetting
    public final NumSetting wordSpacing;
    @RegisterSetting
    public final BooleanSetting version;
    @RegisterSetting
    public final BooleanSetting name;
    @RegisterSetting
    public final BooleanSetting ping;
    @RegisterSetting
    public final BooleanSetting ip;
    @RegisterSetting
    public final BooleanSetting fps;
    @RegisterSetting
    public final BooleanSetting info;
    @RegisterSetting
    public final NumSetting xWatermark;
    @RegisterSetting
    public final NumSetting yWatermark;
    @RegisterSetting
    public final NumSetting xMoreInfo;
    @RegisterSetting
    public final NumSetting yMoreInfo;
    @RegisterSetting
    public final NumSetting xFriendList;
    @RegisterSetting
    public final NumSetting yFriendList;
    @RegisterSetting
    public final NumSetting xWelcomer;
    @RegisterSetting
    public final NumSetting yWelcomer;
    
    public HUD() {
        this.renderMode = new ModeSetting("Mode", "Skeet", new String[] { "Skeet", "Basic" });
        this.waterMark = new BooleanSetting("Watermark", true);
        this.MoreInfo = new BooleanSetting("More Info", true);
        this.Momentum = new BooleanSetting("Momentum", true);
        this.arrayList = new BooleanSetting("HackList", true);
        this.friendList = new BooleanSetting("FriendList", true);
        this.welcomer = new BooleanSetting("Welcomer", true);
        this.watermarkMode = new ModeSetting("WaterMarkColor", "Rainbow", new String[] { "Rainbow", "RGB", "ClientSync" });
        this.MoreInfoMode = new ModeSetting("MoreInfo Color", "Rainbow", new String[] { "Rainbow", "RGB", "ClientSync" });
        this.arrayListMode = new ModeSetting("HackListColor", "Flow", new String[] { "Flow", "RGB", "Sexy", "ClientSync" });
        this.friendListMode = new ModeSetting("FriendListColor", "Rainbow", new String[] { "Rainbow", "RGB", "ClientSync" });
        this.welcomerMode = new ModeSetting("WelcomerType", "Normal", new String[] { "Normal", "Subscribe" });
        this.welcomerColor = new ModeSetting("FriendListColor", "Rainbow", new String[] { "Rainbow", "RGB", "ClientSync" });
        this.rgbWatermark = new ColorSetting("RGBWatermark", 255, 255, 255, 255);
        this.rgbArrayList = new ColorSetting("RGBHackList", 255, 0, 255, 255);
        this.rgbArrayList2 = new ColorSetting("RGBHackList2", 0, 0, 255, 255);
        this.rgbFriendList = new ColorSetting("RGBFriendList", 255, 255, 255, 255);
        this.rgbWelcomer = new ColorSetting("RGBWelcomer", 255, 255, 255, 255);
        this.length = new NumSetting("Length", 7.0f, 1.0f, 15.0f, 1.0f);
        this.saturation = new NumSetting("Saturation", 0.5f, 0.1f, 1.0f, 0.1f);
        this.sexySpeed = new NumSetting("SexyColorSpeed", 350.0f, 1.0f, 750.0f, 1.0f);
        this.wordSpacing = new NumSetting("WordSpacing", 0.1f, 0.1f, 30.0f, 0.1f);
        this.version = new BooleanSetting("Version", false);
        this.name = new BooleanSetting("Name", true);
        this.ping = new BooleanSetting("Ping", true);
        this.ip = new BooleanSetting("IP", true);
        this.fps = new BooleanSetting("FPS", true);
        this.info = new BooleanSetting("Info", true);
        this.xWatermark = new NumSetting("XWatermark", 2.0f, 0.1f, 1000.0f, 0.1f);
        this.yWatermark = new NumSetting("YWatermark", 2.0f, 0.1f, 600.0f, 0.1f);
        this.xMoreInfo = new NumSetting("XInfo", 2.0f, 0.1f, 1000.0f, 0.1f);
        this.yMoreInfo = new NumSetting("YInfo", 2.0f, 0.1f, 600.0f, 0.1f);
        this.xFriendList = new NumSetting("XFriendList", 2.0f, 0.1f, 1000.0f, 0.1f);
        this.yFriendList = new NumSetting("YFriendList", 2.0f, 0.1f, 600.0f, 0.1f);
        this.xWelcomer = new NumSetting("XWelcomer", 2.0f, 0.1f, 1000.0f, 0.1f);
        this.yWelcomer = new NumSetting("YWelcomer", 2.0f, 0.1f, 600.0f, 0.1f);
    }
    
    @SubscribeEvent
    public void onRenderWatermark(final RenderGameOverlayEvent.Text event) {
        if (this.waterMark.isEnabled()) {
            final Font font = Notorious.INSTANCE.hackManager.getHack(Font.class);
            final ServerData data = HUD.mc.func_147104_D();
            String string = "";
            if (this.version.isEnabled()) {
                string = "Notorious 2.0.0";
            }
            else {
                string = "Notorious";
            }
            if (this.name.isEnabled()) {
                string = string + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? " | " : " \u23d0 ") + HUD.mc.field_71439_g.getDisplayNameString();
            }
            if (this.ping.isEnabled()) {
                string = string + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? " | " : " \u23d0 ") + "Ping:" + this.getPing((EntityPlayer)HUD.mc.field_71439_g);
            }
            if (this.ip.isEnabled()) {
                if (HUD.mc.func_147114_u() != null && HUD.mc.func_147104_D() != null && HUD.mc.func_147104_D().field_78845_b != null) {
                    string = string + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? " | " : " \u23d0 ") + data.field_78845_b;
                }
                else {
                    string = string + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? " | " : " \u23d0 ") + "Singeplayer";
                }
            }
            if (this.fps.isEnabled()) {
                string = string + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? " | " : " \u23d0 ") + "FPS:" + Minecraft.func_175610_ah();
            }
            Color colorColor;
            if (this.watermarkMode.getMode().equals("Rainbow")) {
                colorColor = ColorUtil.colorRainbow((int)this.length.getValue(), this.saturation.getValue(), 1.0f);
            }
            else if (this.watermarkMode.getMode().equals("ClientSync")) {
                colorColor = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor();
            }
            else {
                colorColor = this.rgbWatermark.getAsColor();
            }
            int intColor;
            if (this.watermarkMode.getMode().equals("Rainbow")) {
                intColor = ColorUtil.getRainbow(this.length.getValue(), this.saturation.getValue());
            }
            else if (this.watermarkMode.getMode().equals("ClientSync")) {
                intColor = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
            }
            else {
                intColor = this.rgbWatermark.getAsColor().getRGB();
            }
            if (this.renderMode.getMode().equals("Basic")) {
                if (font.isEnabled()) {
                    this.notorious.fontRenderer.drawStringWithShadow(string, this.xWatermark.getValue() + 2.0f, this.yWatermark.getValue(), colorColor);
                }
                else {
                    HUD.mc.field_71466_p.func_175063_a(string, (float)((int)this.xWatermark.getValue() + 2), (float)(int)this.yWatermark.getValue(), intColor);
                }
            }
            if (this.renderMode.getMode().equals("Skeet")) {
                Gui.func_73734_a((int)this.xWatermark.getValue() - 1, (int)this.yWatermark.getValue() - 1, (int)this.xWatermark.getValue() + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? this.notorious.fontRenderer.getStringWidth(string) : HUD.mc.field_71466_p.func_78256_a(string)) + 5, (int)this.yWatermark.getValue() + HUD.mc.field_71466_p.field_78288_b + 4, new Color(80, 80, 80, 255).getRGB());
                Gui.func_73734_a((int)this.xWatermark.getValue(), (int)this.yWatermark.getValue(), (int)this.xWatermark.getValue() + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? this.notorious.fontRenderer.getStringWidth(string) : HUD.mc.field_71466_p.func_78256_a(string)) + 4, (int)this.yWatermark.getValue() + HUD.mc.field_71466_p.field_78288_b + 3, new Color(0, 0, 0, 255).getRGB());
                Gui.func_73734_a((int)this.xWatermark.getValue(), (int)this.yWatermark.getValue(), (int)this.xWatermark.getValue() + (Notorious.INSTANCE.hackManager.getHack(Font.class).isEnabled() ? this.notorious.fontRenderer.getStringWidth(string) : HUD.mc.field_71466_p.func_78256_a(string)) + 4, (int)this.yWatermark.getValue() + 1, ColorUtil.getRainbow(this.length.getValue(), this.saturation.getValue()));
                if (font.isEnabled()) {
                    this.notorious.fontRenderer.drawStringWithShadow(string, this.xWatermark.getValue() + 2.0f, this.yWatermark.getValue() + 4.0f, colorColor);
                }
                else {
                    HUD.mc.field_71466_p.func_175063_a(string, (float)((int)this.xWatermark.getValue() + 2), (float)((int)this.yWatermark.getValue() + 3), intColor);
                }
            }
        }
        if (this.arrayList.isEnabled()) {
            int yOffset = 0;
            for (final Hack hack : this.notorious.hackManager.getSortedHacks()) {
                if (hack.isEnabled() || (!hack.isEnabled() && System.currentTimeMillis() - hack.lastDisabledTime < 50L)) {
                    final String n = hack.getName();
                    final String md = hack.getMetaData();
                    final String name = n + md;
                    final Font font2 = Notorious.INSTANCE.hackManager.getHack(Font.class);
                    double startPos;
                    if (font2.isEnabled()) {
                        startPos = this.notorious.fontRenderer.getStringWidth(name) + 2;
                    }
                    else {
                        startPos = HUD.mc.field_71466_p.func_78256_a(name) + 2;
                    }
                    int color;
                    if (this.arrayListMode.getMode().equals("Flow")) {
                        color = ColorUtil.getRGBWave(this.length.getValue(), this.saturation.getValue(), yOffset * 20L);
                    }
                    else if (this.arrayListMode.getMode().equals("RGB")) {
                        color = this.rgbArrayList.getAsColor().getRGB();
                    }
                    else if (this.arrayListMode.getMode().equals("Sexy")) {
                        color = ColorUtil.getColorFlow(yOffset / 60.0, (int)this.sexySpeed.getValue(), this.rgbArrayList.getAsColor(), this.rgbArrayList2.getAsColor()).getRGB();
                    }
                    else {
                        color = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
                    }
                    double x;
                    if (hack.isEnabled()) {
                        x = -startPos + (startPos + 1.0) * MathHelper.func_151237_a((double)AnimationUtil.getSmooth2Animation(250.0f, (float)(System.currentTimeMillis() - hack.lastEnabledTime)), 0.0, 1.0);
                    }
                    else {
                        x = startPos * -MathHelper.func_151237_a((double)AnimationUtil.getSmooth2Animation(250.0f, (float)(System.currentTimeMillis() - hack.lastDisabledTime)), 0.0, 1.0);
                    }
                    double y = yOffset;
                    if (this.waterMark.isEnabled() && this.renderMode.getMode().equals("Basic")) {
                        y = yOffset + 9;
                    }
                    else if (this.waterMark.isEnabled() && this.renderMode.getMode().equals("Skeet")) {
                        y = yOffset + 12;
                    }
                    else {
                        y = yOffset;
                    }
                    if (font2.isEnabled()) {
                        if (this.renderMode.getMode().equals("Skeet")) {
                            Gui.func_73734_a((int)x - 3, (int)y - 2, (int)(x + startPos + 1.0), (int)(y + this.notorious.fontRenderer.getHeight() + 2.0), -1879048192);
                            Gui.func_73734_a((int)x - 4, (int)y - 2, (int)x + 1, (int)(y + this.notorious.fontRenderer.getHeight() + 2.0), color);
                        }
                    }
                    else if (this.renderMode.getMode().equals("Skeet")) {
                        Gui.func_73734_a((int)x - 3, (int)y - 2, (int)(x + startPos + 1.0), (int)(y + HUD.mc.field_71466_p.field_78288_b + 2.0), -1879048192);
                        Gui.func_73734_a((int)x - 4, (int)y - 2, (int)x + 1, (int)(y + HUD.mc.field_71466_p.field_78288_b + 2.0), color);
                    }
                    if (font2.isEnabled()) {
                        this.notorious.fontRenderer.drawStringWithShadow(name, x + 2.0, y, new Color(color));
                    }
                    else {
                        HUD.mc.field_71466_p.func_175063_a(name, (float)((int)x + 2), (float)(int)y, color);
                    }
                    if (font2.isEnabled()) {
                        yOffset += this.notorious.fontRenderer.getHeight() + (int)this.wordSpacing.getValue();
                    }
                    else {
                        yOffset += HUD.mc.field_71466_p.field_78288_b + (int)this.wordSpacing.getValue();
                    }
                }
            }
        }
        if (this.friendList.isEnabled()) {
            int yOffset = 2;
            final String homies = ChatFormatting.BOLD + "Homies:";
            int color2;
            if (this.friendListMode.getMode().equals("ClientSync")) {
                color2 = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
            }
            else if (this.friendListMode.getMode().equals("RGB")) {
                color2 = this.rgbFriendList.getAsColor().getRGB();
            }
            else {
                color2 = ColorUtil.getRainbow(6.0f, 1.0f);
            }
            HUD.mc.field_71466_p.func_175063_a(homies, this.xFriendList.getValue(), this.yFriendList.getValue(), color2);
            if (!this.notorious.friend.getFriends().isEmpty()) {
                for (final Friend f : this.notorious.friend.getFriends()) {
                    HUD.mc.field_71466_p.func_175063_a(f.getName(), this.xFriendList.getValue(), this.yFriendList.getValue() + yOffset + 6.0f, -1);
                    yOffset += (int)(HUD.mc.field_71466_p.field_78288_b + 0.5);
                }
            }
        }
        if (this.welcomer.isEnabled()) {
            final Font font3 = Notorious.INSTANCE.hackManager.getHack(Font.class);
            int color3;
            if (this.welcomerColor.getMode().equals("ClientSync")) {
                color3 = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
            }
            else if (this.welcomerColor.getMode().equals("RGB")) {
                color3 = this.rgbWelcomer.getAsColor().getRGB();
            }
            else {
                color3 = ColorUtil.getRainbow(6.0f, 1.0f);
            }
            if (font3.isEnabled()) {
                this.notorious.fontRenderer.drawStringWithShadow(this.welcomerMode.getMode().equals("Normal") ? (this.WelcomeMessages() + HUD.mc.field_71439_g.getDisplayNameString()) : ("Sub to " + HUD.mc.field_71439_g.getDisplayNameString() + " on YouTube"), this.xWelcomer.getValue(), this.yWelcomer.getValue(), new Color(color3));
            }
            else {
                HUD.mc.field_71466_p.func_175063_a(this.welcomerMode.getMode().equals("Normal") ? (this.WelcomeMessages() + HUD.mc.field_71439_g.getDisplayNameString()) : ("Sub to " + HUD.mc.field_71439_g.getDisplayNameString() + " on YouTube"), this.xWelcomer.getValue(), this.yWelcomer.getValue(), color3);
            }
        }
    }
    
    private String WelcomeMessages() {
        final int timeOfDay = Calendar.getInstance().get(11);
        if (timeOfDay < 12) {
            return "Good Morning, ";
        }
        if (timeOfDay < 16) {
            return "Good Afternoon, ";
        }
        if (timeOfDay < 21) {
            return "Good Evening, ";
        }
        return "Good Night, ";
    }
    
    public int getPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = MathUtil.clamp(Objects.requireNonNull(HUD.mc.func_147114_u()).func_175102_a(player.func_110124_au()).func_178853_c(), 1, 99999);
        }
        catch (NullPointerException ex) {}
        return ping;
    }
}
