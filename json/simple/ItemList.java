// 
// Decompiled by Procyon v0.5.36
// 

package org.json.simple;

import java.util.Collection;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class ItemList
{
    private String sp;
    List items;
    
    public ItemList() {
        this.sp = ",";
        this.items = new ArrayList();
    }
    
    public ItemList(final String s) {
        this.sp = ",";
        this.items = new ArrayList();
        this.split(s, this.sp, this.items);
    }
    
    public ItemList(final String sp, final String s) {
        this.sp = ",";
        this.items = new ArrayList();
        this.split(this.sp = sp, s, this.items);
    }
    
    public ItemList(final String s, final String s2, final boolean b) {
        this.sp = ",";
        this.split(s, s2, this.items = new ArrayList(), b);
    }
    
    public List getItems() {
        return this.items;
    }
    
    public String[] getArray() {
        return (String[])this.items.toArray();
    }
    
    public void split(final String str, final String delim, final List list, final boolean b) {
        if (str == null || delim == null) {
            return;
        }
        if (b) {
            final StringTokenizer stringTokenizer = new StringTokenizer(str, delim);
            while (stringTokenizer.hasMoreTokens()) {
                list.add(stringTokenizer.nextToken().trim());
            }
        }
        else {
            this.split(str, delim, list);
        }
    }
    
    public void split(final String s, final String str, final List list) {
        if (s == null || str == null) {
            return;
        }
        int i = 0;
        int n;
        do {
            n = i;
            final int index = s.indexOf(str, i);
            if (index == -1) {
                break;
            }
            list.add(s.substring(n, index).trim());
            i = index + str.length();
        } while (i != -1);
        list.add(s.substring(n).trim());
    }
    
    public void setSP(final String sp) {
        this.sp = sp;
    }
    
    public void add(final int n, final String s) {
        if (s == null) {
            return;
        }
        this.items.add(n, s.trim());
    }
    
    public void add(final String s) {
        if (s == null) {
            return;
        }
        this.items.add(s.trim());
    }
    
    public void addAll(final ItemList list) {
        this.items.addAll(list.items);
    }
    
    public void addAll(final String s) {
        this.split(s, this.sp, this.items);
    }
    
    public void addAll(final String s, final String s2) {
        this.split(s, s2, this.items);
    }
    
    public void addAll(final String s, final String s2, final boolean b) {
        this.split(s, s2, this.items, b);
    }
    
    public String get(final int n) {
        return this.items.get(n);
    }
    
    public int size() {
        return this.items.size();
    }
    
    public String toString() {
        return this.toString(this.sp);
    }
    
    public String toString(final String str) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.items.size(); ++i) {
            if (i == 0) {
                sb.append(this.items.get(i));
            }
            else {
                sb.append(str);
                sb.append(this.items.get(i));
            }
        }
        return sb.toString();
    }
    
    public void clear() {
        this.items.clear();
    }
    
    public void reset() {
        this.sp = ",";
        this.items.clear();
    }
}
