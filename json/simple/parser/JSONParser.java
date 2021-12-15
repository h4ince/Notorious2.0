// 
// Decompiled by Procyon v0.5.36
// 

package org.json.simple.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;
import java.util.LinkedList;

public class JSONParser
{
    public static final int S_INIT = 0;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_ARRAY = 3;
    public static final int S_PASSED_PAIR_KEY = 4;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_END = 6;
    public static final int S_IN_ERROR = -1;
    private LinkedList handlerStatusStack;
    private Yylex lexer;
    private Yytoken token;
    private int status;
    
    public JSONParser() {
        this.lexer = new Yylex((Reader)null);
        this.token = null;
        this.status = 0;
    }
    
    private int peekStatus(final LinkedList list) {
        if (list.size() == 0) {
            return -1;
        }
        return list.getFirst();
    }
    
    public void reset() {
        this.token = null;
        this.status = 0;
        this.handlerStatusStack = null;
    }
    
    public void reset(final Reader reader) {
        this.lexer.yyreset(reader);
        this.reset();
    }
    
    public int getPosition() {
        return this.lexer.getPosition();
    }
    
    public Object parse(final String s) throws ParseException {
        return this.parse(s, (ContainerFactory)null);
    }
    
    public Object parse(final String s, final ContainerFactory containerFactory) throws ParseException {
        final StringReader stringReader = new StringReader(s);
        try {
            return this.parse(stringReader, containerFactory);
        }
        catch (IOException ex) {
            throw new ParseException(-1, 2, ex);
        }
    }
    
    public Object parse(final Reader reader) throws IOException, ParseException {
        return this.parse(reader, (ContainerFactory)null);
    }
    
    public Object parse(final Reader reader, final ContainerFactory containerFactory) throws IOException, ParseException {
        this.reset(reader);
        final LinkedList<Integer> list = new LinkedList<Integer>();
        final LinkedList list2 = new LinkedList<String>();
        try {
            do {
                this.nextToken();
                Label_0922: {
                    switch (this.status) {
                        case 0: {
                            switch (this.token.type) {
                                case 0: {
                                    this.status = 1;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)this.token.value);
                                    break Label_0922;
                                }
                                case 1: {
                                    this.status = 2;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)this.createObjectContainer(containerFactory));
                                    break Label_0922;
                                }
                                case 3: {
                                    this.status = 3;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)this.createArrayContainer(containerFactory));
                                    break Label_0922;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0922;
                                }
                            }
                            break;
                        }
                        case 1: {
                            if (this.token.type == -1) {
                                return list2.removeFirst();
                            }
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                        case 2: {
                            switch (this.token.type) {
                                case 5: {
                                    break Label_0922;
                                }
                                case 0: {
                                    if (this.token.value instanceof String) {
                                        list2.addFirst((String)this.token.value);
                                        this.status = 4;
                                        list.addFirst(new Integer(this.status));
                                        break Label_0922;
                                    }
                                    this.status = -1;
                                    break Label_0922;
                                }
                                case 2: {
                                    if (list2.size() > 1) {
                                        list.removeFirst();
                                        list2.removeFirst();
                                        this.status = this.peekStatus(list);
                                        break Label_0922;
                                    }
                                    this.status = 1;
                                    break Label_0922;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0922;
                                }
                            }
                            break;
                        }
                        case 4: {
                            switch (this.token.type) {
                                case 6: {
                                    break;
                                }
                                case 0: {
                                    list.removeFirst();
                                    ((Map<String, Map>)list2.getFirst()).put(list2.removeFirst(), (Map)this.token.value);
                                    this.status = this.peekStatus(list);
                                    break;
                                }
                                case 3: {
                                    list.removeFirst();
                                    final String s = list2.removeFirst();
                                    final Map<String, Map> map = (Map<String, Map>)list2.getFirst();
                                    final List arrayContainer = this.createArrayContainer(containerFactory);
                                    map.put(s, (Map)arrayContainer);
                                    this.status = 3;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)arrayContainer);
                                    break;
                                }
                                case 1: {
                                    list.removeFirst();
                                    final String s2 = list2.removeFirst();
                                    final Map<String, Map> map2 = (Map<String, Map>)list2.getFirst();
                                    final Map objectContainer = this.createObjectContainer(containerFactory);
                                    map2.put(s2, objectContainer);
                                    this.status = 2;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)objectContainer);
                                    break;
                                }
                                default: {
                                    this.status = -1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            switch (this.token.type) {
                                case 5: {
                                    break;
                                }
                                case 0: {
                                    ((List<Map<String, Map>>)list2.getFirst()).add((Map<String, Map>)this.token.value);
                                    break;
                                }
                                case 4: {
                                    if (list2.size() > 1) {
                                        list.removeFirst();
                                        list2.removeFirst();
                                        this.status = this.peekStatus(list);
                                        break;
                                    }
                                    this.status = 1;
                                    break;
                                }
                                case 1: {
                                    final List<Map<String, Map>> list3 = (List<Map<String, Map>>)list2.getFirst();
                                    final Map objectContainer2 = this.createObjectContainer(containerFactory);
                                    list3.add(objectContainer2);
                                    this.status = 2;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)objectContainer2);
                                    break;
                                }
                                case 3: {
                                    final List<Map<String, Map>> list4 = (List<Map<String, Map>>)list2.getFirst();
                                    final List arrayContainer2 = this.createArrayContainer(containerFactory);
                                    list4.add((Map<String, Map>)arrayContainer2);
                                    this.status = 3;
                                    list.addFirst(new Integer(this.status));
                                    list2.addFirst((String)arrayContainer2);
                                    break;
                                }
                                default: {
                                    this.status = -1;
                                    break;
                                }
                            }
                            break;
                        }
                        case -1: {
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                    }
                }
                if (this.status == -1) {
                    throw new ParseException(this.getPosition(), 1, this.token);
                }
            } while (this.token.type != -1);
        }
        catch (IOException ex) {
            throw ex;
        }
        throw new ParseException(this.getPosition(), 1, this.token);
    }
    
    private void nextToken() throws ParseException, IOException {
        this.token = this.lexer.yylex();
        if (this.token == null) {
            this.token = new Yytoken(-1, null);
        }
    }
    
    private Map createObjectContainer(final ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONObject();
        }
        final Map objectContainer = containerFactory.createObjectContainer();
        if (objectContainer == null) {
            return new JSONObject();
        }
        return objectContainer;
    }
    
    private List createArrayContainer(final ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONArray();
        }
        final List creatArrayContainer = containerFactory.creatArrayContainer();
        if (creatArrayContainer == null) {
            return new JSONArray();
        }
        return creatArrayContainer;
    }
    
    public void parse(final String s, final ContentHandler contentHandler) throws ParseException {
        this.parse(s, contentHandler, false);
    }
    
    public void parse(final String s, final ContentHandler contentHandler, final boolean b) throws ParseException {
        final StringReader stringReader = new StringReader(s);
        try {
            this.parse(stringReader, contentHandler, b);
        }
        catch (IOException ex) {
            throw new ParseException(-1, 2, ex);
        }
    }
    
    public void parse(final Reader reader, final ContentHandler contentHandler) throws IOException, ParseException {
        this.parse(reader, contentHandler, false);
    }
    
    public void parse(final Reader reader, final ContentHandler contentHandler, final boolean b) throws IOException, ParseException {
        if (!b) {
            this.reset(reader);
            this.handlerStatusStack = new LinkedList();
        }
        else if (this.handlerStatusStack == null) {
            this.reset(reader);
            this.handlerStatusStack = new LinkedList();
        }
        final LinkedList handlerStatusStack = this.handlerStatusStack;
        try {
            do {
                Label_0911: {
                    switch (this.status) {
                        case 0: {
                            contentHandler.startJSON();
                            this.nextToken();
                            switch (this.token.type) {
                                case 0: {
                                    this.status = 1;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.primitive(this.token.value)) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 1: {
                                    this.status = 2;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObject()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 3: {
                                    this.status = 3;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startArray()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0911;
                                }
                            }
                            break;
                        }
                        case 1: {
                            this.nextToken();
                            if (this.token.type == -1) {
                                contentHandler.endJSON();
                                this.status = 6;
                                return;
                            }
                            this.status = -1;
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                        case 2: {
                            this.nextToken();
                            switch (this.token.type) {
                                case 5: {
                                    break Label_0911;
                                }
                                case 0: {
                                    if (!(this.token.value instanceof String)) {
                                        this.status = -1;
                                        break Label_0911;
                                    }
                                    final String s = (String)this.token.value;
                                    this.status = 4;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObjectEntry(s)) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 2: {
                                    if (handlerStatusStack.size() > 1) {
                                        handlerStatusStack.removeFirst();
                                        this.status = this.peekStatus(handlerStatusStack);
                                    }
                                    else {
                                        this.status = 1;
                                    }
                                    if (!contentHandler.endObject()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0911;
                                }
                            }
                            break;
                        }
                        case 4: {
                            this.nextToken();
                            switch (this.token.type) {
                                case 6: {
                                    break Label_0911;
                                }
                                case 0: {
                                    handlerStatusStack.removeFirst();
                                    this.status = this.peekStatus(handlerStatusStack);
                                    if (!contentHandler.primitive(this.token.value)) {
                                        return;
                                    }
                                    if (!contentHandler.endObjectEntry()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 3: {
                                    handlerStatusStack.removeFirst();
                                    handlerStatusStack.addFirst(new Integer(5));
                                    this.status = 3;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startArray()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 1: {
                                    handlerStatusStack.removeFirst();
                                    handlerStatusStack.addFirst(new Integer(5));
                                    this.status = 2;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObject()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0911;
                                }
                            }
                            break;
                        }
                        case 5: {
                            handlerStatusStack.removeFirst();
                            this.status = this.peekStatus(handlerStatusStack);
                            if (!contentHandler.endObjectEntry()) {
                                return;
                            }
                            break;
                        }
                        case 3: {
                            this.nextToken();
                            switch (this.token.type) {
                                case 5: {
                                    break Label_0911;
                                }
                                case 0: {
                                    if (!contentHandler.primitive(this.token.value)) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 4: {
                                    if (handlerStatusStack.size() > 1) {
                                        handlerStatusStack.removeFirst();
                                        this.status = this.peekStatus(handlerStatusStack);
                                    }
                                    else {
                                        this.status = 1;
                                    }
                                    if (!contentHandler.endArray()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 1: {
                                    this.status = 2;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startObject()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                case 3: {
                                    this.status = 3;
                                    handlerStatusStack.addFirst(new Integer(this.status));
                                    if (!contentHandler.startArray()) {
                                        return;
                                    }
                                    break Label_0911;
                                }
                                default: {
                                    this.status = -1;
                                    break Label_0911;
                                }
                            }
                            break;
                        }
                        case 6: {
                            return;
                        }
                        case -1: {
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }
                    }
                }
                if (this.status == -1) {
                    throw new ParseException(this.getPosition(), 1, this.token);
                }
            } while (this.token.type != -1);
        }
        catch (IOException ex) {
            this.status = -1;
            throw ex;
        }
        catch (ParseException ex2) {
            this.status = -1;
            throw ex2;
        }
        catch (RuntimeException ex3) {
            this.status = -1;
            throw ex3;
        }
        catch (Error error) {
            this.status = -1;
            throw error;
        }
        this.status = -1;
        throw new ParseException(this.getPosition(), 1, this.token);
    }
}
