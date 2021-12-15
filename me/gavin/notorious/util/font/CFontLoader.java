// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util.font;

import java.awt.Font;

public class CFontLoader
{
    public static final Font MULI_SEMIBOLD;
    public static final Font COMFORTAA;
    public static final Font HELVETICA_BOLD;
    public static final Font HELVETICA;
    
    public static Font getFontByName(final String name) {
        if (name.equalsIgnoreCase("muli-semibold")) {
            return getFontFromInput("/assets/notorious/Muli-SemiBold.ttf");
        }
        if (name.equalsIgnoreCase("Comfortaa")) {
            return getFontFromInput("/assets/notorious/Comfortaa-Regular.ttf");
        }
        if (name.equalsIgnoreCase("helvetica-bold")) {
            return getFontFromInput("/assets/notorious/Helvetica-Bold-Font.ttf");
        }
        if (name.equalsIgnoreCase("helvetica")) {
            return getFontFromInput("/assets/notorious/Helvetica.ttf");
        }
        return null;
    }
    
    public static Font getFontFromInput(final String path) {
        try {
            final Font newFont = Font.createFont(0, CFontLoader.class.getResourceAsStream(path));
            return newFont;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    static {
        MULI_SEMIBOLD = getFontByName("muli-semibold").deriveFont(18.0f);
        COMFORTAA = getFontByName("comfortaa").deriveFont(18.0f);
        HELVETICA_BOLD = getFontByName("helvetica-bold").deriveFont(18.0f);
        HELVETICA = getFontByName("helvetica").deriveFont(18.0f);
    }
}
