// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.util.concurrent.ConcurrentHashMap;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.apache.commons.io.IOUtils;
import java.net.URL;
import java.util.Map;

public class UUIDResolver
{
    public static final Map<String, String> uuidNameCache;
    
    public static String resolveName(String uuid) {
        final String original = uuid;
        uuid = uuid.replace("-", "");
        if (UUIDResolver.uuidNameCache.containsKey(uuid)) {
            return UUIDResolver.uuidNameCache.get(uuid);
        }
        final String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
        try {
            final String nameJson = IOUtils.toString(new URL(url));
            if (nameJson != null && nameJson.length() > 0) {
                final JSONArray jsonArray = (JSONArray)JSONValue.parseWithException(nameJson);
                if (jsonArray != null) {
                    final JSONObject latestName = jsonArray.get(jsonArray.size() - 1);
                    if (latestName != null) {
                        UUIDResolver.uuidNameCache.put(original, latestName.get("name").toString());
                        return latestName.get("name").toString();
                    }
                }
            }
        }
        catch (IOException | ParseException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
        return null;
    }
    
    static {
        uuidNameCache = new ConcurrentHashMap<String, String>();
    }
}
