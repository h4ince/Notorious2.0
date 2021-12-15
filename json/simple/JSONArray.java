// 
// Decompiled by Procyon v0.5.36
// 

package org.json.simple;

import java.io.IOException;
import java.util.Iterator;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

public class JSONArray extends ArrayList implements List, JSONAware, JSONStreamAware
{
    private static final long serialVersionUID = 3957988303675231981L;
    
    public static void writeJSONString(final List list, final Writer writer) throws IOException {
        if (list == null) {
            writer.write("null");
            return;
        }
        int n = 1;
        final Iterator<Object> iterator = list.iterator();
        writer.write(91);
        while (iterator.hasNext()) {
            if (n != 0) {
                n = 0;
            }
            else {
                writer.write(44);
            }
            final Object next = iterator.next();
            if (next == null) {
                writer.write("null");
            }
            else {
                JSONValue.writeJSONString(next, writer);
            }
        }
        writer.write(93);
    }
    
    public void writeJSONString(final Writer writer) throws IOException {
        writeJSONString(this, writer);
    }
    
    public static String toJSONString(final List list) {
        if (list == null) {
            return "null";
        }
        int n = 1;
        final StringBuffer sb = new StringBuffer();
        final Iterator<Object> iterator = list.iterator();
        sb.append('[');
        while (iterator.hasNext()) {
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append(',');
            }
            final Object next = iterator.next();
            if (next == null) {
                sb.append("null");
            }
            else {
                sb.append(JSONValue.toJSONString(next));
            }
        }
        sb.append(']');
        return sb.toString();
    }
    
    public String toJSONString() {
        return toJSONString(this);
    }
    
    public String toString() {
        return this.toJSONString();
    }
}
