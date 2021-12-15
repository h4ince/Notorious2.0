// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.friend;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Friends
{
    public ArrayList<Friend> friends;
    
    public Friends() {
        this.friends = new ArrayList<Friend>();
    }
    
    public List<Friend> getFriends() {
        return this.friends;
    }
    
    public boolean isFriend(final String name) {
        for (final Friend f : this.getFriends()) {
            if (f.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<String> getFriendByName() {
        final ArrayList<String> friendsName = new ArrayList<String>();
        this.friends.forEach(friend -> friendsName.add(friend.getName()));
        return friendsName;
    }
    
    public Friend getFriendByName(final String name) {
        Friend fr = null;
        for (final Friend f : this.getFriends()) {
            if (f.getName().equalsIgnoreCase(name)) {
                fr = f;
            }
        }
        return fr;
    }
    
    public void addFriend(final String name) {
        this.friends.add(new Friend(name));
    }
    
    public void delFriend(final String name) {
        this.friends.remove(this.getFriendByName(name));
    }
}
