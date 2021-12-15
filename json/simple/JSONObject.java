// 
// Decompiled by Procyon v0.5.36
// 

package org.json.simple;

import java.io.IOException;
import java.util.Iterator;
import java.io.Writer;
import java.util.Map;
import java.util.HashMap;

public class JSONObject extends HashMap implements Map, JSONAware, JSONStreamAware
{
    private static final long serialVersionUID = -503443796854799292L;
    
    public static void writeJSONString(final Map map, final Writer writer) throws IOException {
        if (map == null) {
            writer.write("null");
            return;
        }
        int n = 1;
        final Iterator<Entry<Object, V>> iterator = map.entrySet().iterator();
        writer.write(123);
        while (iterator.hasNext()) {
            if (n != 0) {
                n = 0;
            }
            else {
                writer.write(44);
            }
            final Entry<Object, V> entry = iterator.next();
            writer.write(34);
            writer.write(escape(String.valueOf(entry.getKey())));
            writer.write(34);
            writer.write(58);
            JSONValue.writeJSONString(entry.getValue(), writer);
        }
        writer.write(125);
    }
    
    public void writeJSONString(final Writer writer) throws IOException {
        writeJSONString(this, writer);
    }
    
    public static String toJSONString(final Map map) {
        if (map == null) {
            return "null";
        }
        final StringBuffer sb = new StringBuffer();
        int n = 1;
        final Iterator<Entry<Object, V>> iterator = map.entrySet().iterator();
        sb.append('{');
        while (iterator.hasNext()) {
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append(',');
            }
            final Entry<Object, V> entry = iterator.next();
            toJSONString(String.valueOf(entry.getKey()), entry.getValue(), sb);
        }
        sb.append('}');
        return sb.toString();
    }
    
    public String toJSONString() {
        return toJSONString(this);
    }
    
    private static String toJSONString(final String s, final Object o, final StringBuffer sb) {
        sb.append('\"');
        if (s == null) {
            sb.append("null");
        }
        else {
            JSONValue.escape(s, sb);
        }
        sb.append('\"').append(':');
        sb.append(JSONValue.toJSONString(o));
        return sb.toString();
    }
    
    public String toString() {
        return this.toJSONString();
    }
    
    public static String toString(final String s, final Object o) {
        final StringBuffer sb = new StringBuffer();
        toJSONString(s, o, sb);
        return sb.toString();
    }
    
    public static String escape(final String s) {
        return JSONValue.escape(s);
    }
}
