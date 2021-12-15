// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.manager;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import com.google.gson.GsonBuilder;
import java.io.File;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.util.Iterator;
import me.gavin.notorious.NotoriousMod;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.setting.StringSetting;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.Setting;
import java.io.Reader;
import java.io.InputStreamReader;
import com.google.gson.JsonParser;
import java.nio.file.OpenOption;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.Hack;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.io.IOException;

public class ConfigManager
{
    public void load() {
        try {
            this.loadHacks();
            this.loadToggles();
            this.loadBinds();
            this.loadDrawn();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public void save() {
        try {
            this.registerFolders();
            this.saveHacks();
            this.saveToggles();
            this.saveBinds();
            this.saveDrawn();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public void attach() {
        Runtime.getRuntime().addShutdownHook(new SaveThread());
    }
    
    public void registerFolders() throws IOException {
        if (!Files.exists(Paths.get("Notorious/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("Notorious/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
        if (!Files.exists(Paths.get("Notorious/Hacks/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("Notorious/Hacks/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
        if (!Files.exists(Paths.get("Notorious/Client/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("Notorious/Client/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
        for (final Hack.Category category : Hack.Category.values()) {
            if (!Files.exists(Paths.get("Notorious/Hacks/" + category.toString() + "/", new String[0]), new LinkOption[0])) {
                Files.createDirectories(Paths.get("Notorious/Hacks/" + category + "/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
    }
    
    public void loadHacks() throws IOException {
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            if (!Files.exists(Paths.get("Notorious/Hacks/" + hack.getCategory().toString() + "/" + hack.getName() + ".json", new String[0]), new LinkOption[0])) {
                return;
            }
            final InputStream stream = Files.newInputStream(Paths.get("Notorious/Hacks/" + hack.getCategory().toString() + "/" + hack.getName() + ".json", new String[0]), new OpenOption[0]);
            final JsonObject hackObject = new JsonParser().parse((Reader)new InputStreamReader(stream)).getAsJsonObject();
            if (hackObject.get("Hack") == null) {
                return;
            }
            final JsonObject settingObject = hackObject.get("Settings").getAsJsonObject();
            for (final Setting setting : hack.getSettings()) {
                final JsonElement dataObject = settingObject.get(setting.getName());
                try {
                    if (dataObject == null || !dataObject.isJsonPrimitive()) {
                        continue;
                    }
                    if (setting instanceof BooleanSetting) {
                        ((BooleanSetting)setting).setValue(dataObject.getAsBoolean());
                    }
                    else if (setting instanceof NumSetting) {
                        ((NumSetting)setting).setValue(dataObject.getAsFloat());
                    }
                    else if (setting instanceof ModeSetting) {
                        ((ModeSetting)setting).setMode(dataObject.getAsString());
                    }
                    else if (setting instanceof StringSetting) {
                        ((StringSetting)setting).setString(dataObject.getAsString());
                    }
                    else if (setting instanceof ColorSetting) {}
                }
                catch (NumberFormatException exception) {
                    NotoriousMod.LOGGER.error("Faulty setting found. Stacktrace below. (" + setting.getName() + ")");
                    exception.printStackTrace();
                }
            }
            stream.close();
        }
    }
    
    public void saveHacks() throws IOException {
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            if (Files.exists(Paths.get("Notorious/Hacks/" + hack.getCategory().toString() + "/" + hack.getName() + ".json", new String[0]), new LinkOption[0])) {
                new File("Notorious/Hacks/" + hack.getCategory().toString() + "/" + hack.getName() + ".json").delete();
            }
            Files.createFile(Paths.get("Notorious/Hacks/" + hack.getCategory().toString() + "/" + hack.getName() + ".json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream("Notorious/Hacks/" + hack.getCategory().toString() + "/" + hack.getName() + ".json"), StandardCharsets.UTF_8);
            final JsonObject hackObject = new JsonObject();
            final JsonObject settingObject = new JsonObject();
            hackObject.add("Hack", (JsonElement)new JsonPrimitive(hack.getName()));
            for (final Setting setting : hack.getSettings()) {
                if (setting instanceof BooleanSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(((BooleanSetting)setting).getValue())));
                }
                else if (setting instanceof NumSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)((NumSetting)setting).getValue()));
                }
                else if (setting instanceof ModeSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(((ModeSetting)setting).getMode()));
                }
                else if (setting instanceof StringSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(((StringSetting)setting).getString()));
                }
                else {
                    if (!(setting instanceof ColorSetting)) {
                        continue;
                    }
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getAsColor().getRGB()));
                }
            }
            hackObject.add("Settings", (JsonElement)settingObject);
            stream.write(gson.toJson(new JsonParser().parse(hackObject.toString())));
            stream.close();
        }
    }
    
    public void loadToggles() throws IOException {
        if (!Files.exists(Paths.get("Notorious/Client/Toggles.json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream stream = Files.newInputStream(Paths.get("Notorious/Client/Toggles.json", new String[0]), new OpenOption[0]);
        final JsonObject hackObject = new JsonParser().parse((Reader)new InputStreamReader(stream)).getAsJsonObject();
        if (hackObject.get("Hacks") == null) {
            return;
        }
        final JsonObject toggleObject = hackObject.get("Hacks").getAsJsonObject();
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            final JsonElement dataObject = toggleObject.get(hack.getName());
            if (dataObject != null && dataObject.isJsonPrimitive() && dataObject.getAsBoolean()) {
                hack.enable();
            }
        }
        stream.close();
    }
    
    public void saveToggles() throws IOException {
        if (Files.exists(Paths.get("Notorious/Client/Toggles.json", new String[0]), new LinkOption[0])) {
            new File("Notorious/Client/Toggles.json").delete();
        }
        Files.createFile(Paths.get("Notorious/Client/Toggles.json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream("Notorious/Client/Toggles.json"), StandardCharsets.UTF_8);
        final JsonObject hackObject = new JsonObject();
        final JsonObject toggleObject = new JsonObject();
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            toggleObject.add(hack.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(hack.isEnabled())));
        }
        hackObject.add("Hacks", (JsonElement)toggleObject);
        stream.write(gson.toJson(new JsonParser().parse(hackObject.toString())));
        stream.close();
    }
    
    public void loadDrawn() throws IOException {
        if (!Files.exists(Paths.get("Notorious/Client/Drawn.json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream stream = Files.newInputStream(Paths.get("Notorious/Client/Drawn.json", new String[0]), new OpenOption[0]);
        final JsonObject hackObject = new JsonParser().parse((Reader)new InputStreamReader(stream)).getAsJsonObject();
        if (hackObject.get("Hacks") == null) {
            return;
        }
        final JsonObject toggleObject = hackObject.get("Hacks").getAsJsonObject();
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            final JsonElement dataObject = toggleObject.get(hack.getName());
            if (dataObject != null && dataObject.isJsonPrimitive() && dataObject.getAsBoolean()) {
                hack.enable();
            }
        }
        stream.close();
    }
    
    public void saveDrawn() throws IOException {
        if (Files.exists(Paths.get("Notorious/Client/Drawn.json", new String[0]), new LinkOption[0])) {
            new File("Notorious/Client/Drawn.json").delete();
        }
        Files.createFile(Paths.get("Notorious/Client/Drawn.json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream("Notorious/Client/Drawn.json"), StandardCharsets.UTF_8);
        final JsonObject hackObject = new JsonObject();
        final JsonObject drawnObject = new JsonObject();
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            drawnObject.add(hack.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(hack.isDrawn())));
        }
        hackObject.add("Hacks", (JsonElement)drawnObject);
        stream.write(gson.toJson(new JsonParser().parse(hackObject.toString())));
        stream.close();
    }
    
    public void loadBinds() throws IOException {
        if (!Files.exists(Paths.get("Notorious/Client/Binds.json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream stream = Files.newInputStream(Paths.get("Notorious/Client/Binds.json", new String[0]), new OpenOption[0]);
        final JsonObject hackObject = new JsonParser().parse((Reader)new InputStreamReader(stream)).getAsJsonObject();
        if (hackObject.get("Hacks") == null) {
            return;
        }
        final JsonObject bindObject = hackObject.get("Hacks").getAsJsonObject();
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            final JsonElement dataObject = bindObject.get(hack.getName());
            if (dataObject != null && dataObject.isJsonPrimitive()) {
                hack.setBind(dataObject.getAsInt());
            }
        }
        stream.close();
    }
    
    public void saveBinds() throws IOException {
        if (Files.exists(Paths.get("Notorious/Client/Binds.json", new String[0]), new LinkOption[0])) {
            new File("Notorious/Client/Binds.json").delete();
        }
        Files.createFile(Paths.get("Notorious/Client/Binds.json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream("Notorious/Client/Binds.json"), StandardCharsets.UTF_8);
        final JsonObject hackObject = new JsonObject();
        final JsonObject bindObject = new JsonObject();
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacks()) {
            bindObject.add(hack.getName(), (JsonElement)new JsonPrimitive((Number)hack.getBind()));
        }
        hackObject.add("Hacks", (JsonElement)bindObject);
        stream.write(gson.toJson(new JsonParser().parse(hackObject.toString())));
        stream.close();
    }
    
    public static class SaveThread extends Thread
    {
        @Override
        public void run() {
            Notorious.INSTANCE.configManager.save();
        }
    }
}
