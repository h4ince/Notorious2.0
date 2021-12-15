// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.stuff;

import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import java.util.Objects;
import java.util.UUID;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;

public class Capeutil
{
    static final ArrayList<String> final_uuid_list;
    static ArrayList<ResourceLocation> capeTexts;
    static Iterator<ResourceLocation> iterator;
    public static ResourceLocation capeTexture;
    static int currentCape;
    private static Thread thread;
    
    public static ArrayList<String> get_uuids() {
        try {
            final URL url = new URL("https://pastebin.com/raw/5fTM5ATQ");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final ArrayList<String> uuid_list = new ArrayList<String>();
            String s;
            while ((s = reader.readLine()) != null) {
                uuid_list.add(s);
            }
            return uuid_list;
        }
        catch (Exception ignored) {
            return null;
        }
    }
    
    public static void startAnimationLoop() {
        (Capeutil.thread = new Thread(() -> {
            while (true) {
                nextCapeTex();
                try {
                    Thread.sleep(0L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }
    
    public static void stopAnimationLoop() {
        Capeutil.thread.stop();
    }
    
    private static void nextCapeTex() {
        ++Capeutil.currentCape;
        if (Capeutil.currentCape == Capeutil.capeTexts.size()) {
            Capeutil.currentCape = 0;
        }
        Capeutil.capeTexture = Capeutil.capeTexts.get(Capeutil.currentCape);
    }
    
    public static boolean is_uuid_valid(final UUID uuid) {
        for (final String u : Objects.requireNonNull(Capeutil.final_uuid_list)) {
            if (u.equals(uuid.toString())) {
                return true;
            }
        }
        return false;
    }
    
    public static void setCape(final DynamicTexture texture) {
        Capeutil.capeTexture = Minecraft.func_71410_x().func_110434_K().func_110578_a("assets/notorious/capes", texture);
    }
    
    public static ResourceLocation getCape() {
        return Capeutil.capeTexture;
    }
    
    static {
        final_uuid_list = get_uuids();
        Capeutil.capeTexts = new ArrayList<ResourceLocation>();
        Capeutil.currentCape = 0;
        try {
            Capeutil.capeTexts.add(Minecraft.func_71410_x().func_110434_K().func_110578_a("assets/notorious/capes", new DynamicTexture(ImageIO.read(new URL("https://i.imgur.com/W1T3gFG.png")))));
            Capeutil.capeTexts.add(Minecraft.func_71410_x().func_110434_K().func_110578_a("assets/notorious/capes", new DynamicTexture(ImageIO.read(new URL("https://i.imgur.com/P0plzkt.png")))));
            Capeutil.capeTexts.add(Minecraft.func_71410_x().func_110434_K().func_110578_a("assets/notorious/capes", new DynamicTexture(ImageIO.read(new URL("https://i.imgur.com/iAEjyYs.png")))));
        }
        catch (Exception ex) {}
        Capeutil.iterator = Capeutil.capeTexts.iterator();
    }
}
