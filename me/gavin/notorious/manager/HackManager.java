// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.manager;

import java.lang.reflect.Field;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.Setting;
import java.lang.annotation.Annotation;
import me.gavin.notorious.hack.RegisterHack;
import java.util.Iterator;
import java.util.Collection;
import java.util.Comparator;
import me.gavin.notorious.hack.hacks.world.MobOwner;
import me.gavin.notorious.hack.hacks.world.ShulkerJew;
import me.gavin.notorious.hack.hacks.world.Lawnmower;
import me.gavin.notorious.hack.hacks.world.BedFucker;
import me.gavin.notorious.hack.hacks.render.SelfParticle;
import me.gavin.notorious.hack.hacks.render.Chams;
import me.gavin.notorious.hack.hacks.render.VoidESP;
import me.gavin.notorious.hack.hacks.render.ViewModel;
import me.gavin.notorious.hack.hacks.render.TargetHUD;
import me.gavin.notorious.hack.hacks.render.StorageESP;
import me.gavin.notorious.hack.hacks.render.Alpha;
import me.gavin.notorious.hack.hacks.render.SkyColor;
import me.gavin.notorious.hack.hacks.render.ShulkerRender;
import me.gavin.notorious.hack.hacks.render.RBandESP;
import me.gavin.notorious.hack.hacks.render.PopESP;
import me.gavin.notorious.hack.hacks.render.PenisESP;
import me.gavin.notorious.hack.hacks.render.NoRender;
import me.gavin.notorious.hack.hacks.render.Nametags;
import me.gavin.notorious.hack.hacks.render.HoleESP;
import me.gavin.notorious.hack.hacks.render.HellenKeller;
import me.gavin.notorious.hack.hacks.render.Glint;
import me.gavin.notorious.hack.hacks.render.Fullbright;
import me.gavin.notorious.hack.hacks.render.FuckedDetector;
import me.gavin.notorious.hack.hacks.render.ESP;
import me.gavin.notorious.hack.hacks.render.BurrowESP;
import me.gavin.notorious.hack.hacks.render.BreakESP;
import me.gavin.notorious.hack.hacks.render.BlockHighlight;
import me.gavin.notorious.hack.hacks.render.AntiFog;
import me.gavin.notorious.hack.hacks.player.PacketUse;
import me.gavin.notorious.hack.hacks.player.AntiHunger;
import me.gavin.notorious.hack.hacks.player.SkinBlinker;
import me.gavin.notorious.hack.hacks.player.ToggleXP;
import me.gavin.notorious.hack.hacks.player.Suicide;
import me.gavin.notorious.hack.hacks.player.PacketMine;
import me.gavin.notorious.hack.hacks.player.MiddleClick;
import me.gavin.notorious.hack.hacks.player.FastPlace;
import me.gavin.notorious.hack.hacks.movement.FloorGang;
import me.gavin.notorious.hack.hacks.movement.TimerSpeed;
import me.gavin.notorious.hack.hacks.movement.NoSlow;
import me.gavin.notorious.hack.hacks.movement.Velocity;
import me.gavin.notorious.hack.hacks.movement.Step;
import me.gavin.notorious.hack.hacks.movement.Sprint;
import me.gavin.notorious.hack.hacks.movement.ReverseStep;
import me.gavin.notorious.hack.hacks.movement.AutoHop;
import me.gavin.notorious.hack.hacks.movement.AntiVoid;
import me.gavin.notorious.hack.hacks.misc.EGapFinder;
import me.gavin.notorious.hack.hacks.misc.WeaknessLog;
import me.gavin.notorious.hack.hacks.misc.RBandDetect;
import me.gavin.notorious.hack.hacks.misc.GhastNotifier;
import me.gavin.notorious.hack.hacks.misc.FakePlayer;
import me.gavin.notorious.hack.hacks.misc.CopyIP;
import me.gavin.notorious.hack.hacks.misc.CopyCoords;
import me.gavin.notorious.hack.hacks.misc.Respawn;
import me.gavin.notorious.hack.hacks.misc.AutoLog;
import me.gavin.notorious.hack.hacks.combat.BowSpam;
import me.gavin.notorious.hack.hacks.combat.GhostGap;
import me.gavin.notorious.hack.hacks.combat.Offhand;
import me.gavin.notorious.hack.hacks.combat.Anti32K;
import me.gavin.notorious.hack.hacks.combat.Quiver;
import me.gavin.notorious.hack.hacks.combat.GhostCrystal;
import me.gavin.notorious.hack.hacks.combat.BowBomb;
import me.gavin.notorious.hack.hacks.combat.PacketCity;
import me.gavin.notorious.hack.hacks.combat.KillAura;
import me.gavin.notorious.hack.hacks.combat.CrystalAura;
import me.gavin.notorious.hack.hacks.combat.Criticals;
import me.gavin.notorious.hack.hacks.combat.BurrowBreaker;
import me.gavin.notorious.hack.hacks.combat.Burrow;
import me.gavin.notorious.hack.hacks.combat.AutoTotem;
import me.gavin.notorious.hack.hacks.combat.AutoCrystal;
import me.gavin.notorious.hack.hacks.combat.AnvilBurrow;
import me.gavin.notorious.hack.hacks.client.AutoBitcoin;
import me.gavin.notorious.hack.hacks.client.Save;
import me.gavin.notorious.hack.hacks.client.Notification;
import me.gavin.notorious.hack.hacks.client.HUD;
import me.gavin.notorious.hack.hacks.client.Font;
import me.gavin.notorious.hack.hacks.client.DiscordRPC;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import me.gavin.notorious.hack.hacks.chat.AutoSong;
import me.gavin.notorious.hack.hacks.chat.VisualRange;
import me.gavin.notorious.hack.hacks.chat.PopCounter;
import me.gavin.notorious.hack.hacks.client.ToggleMessage;
import me.gavin.notorious.hack.hacks.chat.PotionAlert;
import me.gavin.notorious.hack.hacks.chat.ChorusPredict;
import me.gavin.notorious.hack.hacks.chat.ChatMods;
import me.gavin.notorious.hack.hacks.chat.AutoGroom;
import me.gavin.notorious.hack.hacks.chat.AutoEZ;
import me.gavin.notorious.hack.hacks.chat.AutoDox;
import me.gavin.notorious.hack.hacks.chat.AutoChad;
import me.gavin.notorious.hack.hacks.chat.ArmorNotify;
import me.gavin.notorious.hack.Hack;
import java.util.ArrayList;

public class HackManager
{
    private final ArrayList<Hack> hacks;
    private ArrayList<Hack> sortedHacks;
    
    public HackManager() {
        this.hacks = new ArrayList<Hack>();
        this.sortedHacks = new ArrayList<Hack>();
        this.addHack(new ArmorNotify());
        this.addHack(new AutoChad());
        this.addHack(new AutoDox());
        this.addHack(new AutoEZ());
        this.addHack(new AutoGroom());
        this.addHack(new ChatMods());
        this.addHack(new ChorusPredict());
        this.addHack(new PotionAlert());
        this.addHack(new ToggleMessage());
        this.addHack(new PopCounter());
        this.addHack(new VisualRange());
        this.addHack(new AutoSong());
        this.addHack(new ClickGUI());
        this.addHack(new DiscordRPC());
        this.addHack(new Font());
        this.addHack(new HUD());
        this.addHack(new Notification());
        this.addHack(new Save());
        this.addHack(new AutoBitcoin());
        this.addHack(new AnvilBurrow());
        this.addHack(new AutoCrystal());
        this.addHack(new AutoTotem());
        this.addHack(new Burrow());
        this.addHack(new BurrowBreaker());
        this.addHack(new Criticals());
        this.addHack(new CrystalAura());
        this.addHack(new KillAura());
        this.addHack(new PacketCity());
        this.addHack(new BowBomb());
        this.addHack(new GhostCrystal());
        this.addHack(new Quiver());
        this.addHack(new Anti32K());
        this.addHack(new Offhand());
        this.addHack(new GhostGap());
        this.addHack(new BowSpam());
        this.addHack(new AutoLog());
        this.addHack(new Respawn());
        this.addHack(new CopyCoords());
        this.addHack(new CopyIP());
        this.addHack(new FakePlayer());
        this.addHack(new GhastNotifier());
        this.addHack(new RBandDetect());
        this.addHack(new WeaknessLog());
        this.addHack(new EGapFinder());
        this.addHack(new AntiVoid());
        this.addHack(new AutoHop());
        this.addHack(new ReverseStep());
        this.addHack(new Sprint());
        this.addHack(new Step());
        this.addHack(new Velocity());
        this.addHack(new NoSlow());
        this.addHack(new TimerSpeed());
        this.addHack(new FloorGang());
        this.addHack(new FastPlace());
        this.addHack(new MiddleClick());
        this.addHack(new PacketMine());
        this.addHack(new Suicide());
        this.addHack(new ToggleXP());
        this.addHack(new SkinBlinker());
        this.addHack(new AntiHunger());
        this.addHack(new PacketUse());
        this.addHack(new AntiFog());
        this.addHack(new BlockHighlight());
        this.addHack(new BreakESP());
        this.addHack(new BurrowESP());
        this.addHack(new ESP());
        this.addHack(new FuckedDetector());
        this.addHack(new Fullbright());
        this.addHack(new Glint());
        this.addHack(new HellenKeller());
        this.addHack(new HoleESP());
        this.addHack(new Nametags());
        this.addHack(new NoRender());
        this.addHack(new PenisESP());
        this.addHack(new PopESP());
        this.addHack(new RBandESP());
        this.addHack(new ShulkerRender());
        this.addHack(new SkyColor());
        this.addHack(new Alpha());
        this.addHack(new StorageESP());
        this.addHack(new TargetHUD());
        this.addHack(new ViewModel());
        this.addHack(new VoidESP());
        this.addHack(new Chams());
        this.addHack(new SelfParticle());
        this.addHack(new BedFucker());
        this.addHack(new Lawnmower());
        this.addHack(new ShulkerJew());
        this.addHack(new MobOwner());
        this.hacks.sort(this::sortABC);
        this.sortedHacks.addAll(this.hacks);
    }
    
    public ArrayList<Hack> getHacks() {
        return this.hacks;
    }
    
    public ArrayList<Hack> getSortedHacks() {
        return this.sortedHacks;
    }
    
    public <T extends Hack> T getHack(final Class<T> clazz) {
        for (final Hack hack : this.hacks) {
            if (hack.getClass() == clazz) {
                return (T)hack;
            }
        }
        return null;
    }
    
    public Hack getHackString(final String name) {
        for (final Hack h : this.getHacks()) {
            if (h.getName().equalsIgnoreCase(name)) {
                return h;
            }
        }
        return null;
    }
    
    public ArrayList<Hack> getEnabledHacksWithDrawnHacksForArraylist() {
        final ArrayList<Hack> enabledHacks = new ArrayList<Hack>();
        for (final Hack hack : this.hacks) {
            if (!hack.isEnabled()) {
                continue;
            }
            if (!hack.isDrawn()) {
                continue;
            }
            enabledHacks.add(hack);
        }
        return enabledHacks;
    }
    
    public ArrayList<Hack> getEnabledHacks() {
        final ArrayList<Hack> enabledHacks = new ArrayList<Hack>();
        for (final Hack module : this.hacks) {
            if (!module.isEnabled()) {
                continue;
            }
            enabledHacks.add(module);
        }
        return enabledHacks;
    }
    
    public ArrayList<Hack> getHacksFromCategory(final Hack.Category category) {
        final ArrayList<Hack> tempList = new ArrayList<Hack>();
        for (final Hack hack : this.hacks) {
            if (hack.getCategory() == category) {
                tempList.add(hack);
            }
        }
        return tempList;
    }
    
    private void addHack(final Hack hack) {
        if (!hack.getClass().isAnnotationPresent(RegisterHack.class)) {
            return;
        }
        final RegisterHack annotation = hack.getClass().getAnnotation(RegisterHack.class);
        hack.setName(annotation.name());
        hack.setDescription(annotation.description());
        hack.setCategory(annotation.category());
        hack.setBind((hack.getClass() == ClickGUI.class) ? 22 : 0);
        for (final Field field : hack.getClass().getDeclaredFields()) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            if (Setting.class.isAssignableFrom(field.getType()) && field.isAnnotationPresent(RegisterSetting.class)) {
                try {
                    hack.getSettings().add((Setting)field.get(hack));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.hacks.add(hack);
    }
    
    private int sortABC(final Hack hack1, final Hack hack2) {
        return hack1.getName().compareTo(hack2.getName());
    }
}
