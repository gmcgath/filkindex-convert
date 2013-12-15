package com.mcgath.indexconv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/** This class takes a Songbook and writes it out as a MODS file. */
public class ModsOutputter implements SongbookOutputter {
    
    Writer writer;
    
    public ModsOutputter (Writer w) {
        writer = w;
    }
    
    /** Write out the songbook as a MODS XML file. */
    public void writeSongbook (Songbook s) throws IOException {
        Document doc = createJdomDoc (s);
        XMLOutputter xout = new XMLOutputter (Format.getPrettyFormat());
        xout.output(doc, writer);
    }
    
    /* Turn the songbook into a JDOM object */
    private Document createJdomDoc (Songbook s) {
        ModsJdom jdom = new ModsJdom();
        jdom.setID(titleToId(s.getTitle()));
        jdom.addRecordInfo();
        jdom.addTitleInfo (s.getTitle(), s.getSubtitle());
        String lang = s.getLanguage();
        if (lang != null) {
            jdom.addLanguage(lang);
        }
        String desc = s.getPhysicalDesc();
        if (desc != null) {
            jdom.addPhysicalDescription (desc);
        }
        String abstracte = s.getAbstract();
        if (abstracte != null) {
            jdom.addAbstract(abstracte);
        }
        
        // TODO other headers
        
        // And now the songs
        List<Song> songs = s.getSongs();
        for (Song song : songs) {
            jdom.addSong (song);
        }
        return jdom.getDocument();
    }

    
    /* Create an ID from the title by using only the letters and
     * digits and making everything lower case */
    private String titleToId (String title) {
        char[] titleChars = title.toCharArray();
        StringBuffer id = new StringBuffer();
        for (char ch : titleChars) {
            if (Character.isLetterOrDigit(ch)) {
                id.append(Character.toLowerCase(ch));
            }
        }
        return id.toString();
    }
}
