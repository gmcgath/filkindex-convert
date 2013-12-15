package com.mcgath.indexconv;

/** The representation of a song or other item (such as a preface) 
 *  in the book, or a song which is cited as an original.
 */
public class Song {
    private String title;
    private String subtitle;
    private String composer;
    private String lyricist;
    private String note;
    private Song originalSong;
    
    public String getTitle() {
        return title;
    }
    
    public String getSubtitle() {
        return subtitle;
    }
    
    /** In general there can be more than one composer, but the
     *  spreadsheet only supports one */
    public String getComposer () {
        return composer;
    }
    
    /** In general there can be more than one lyricist, but the
     *  spreadsheet only supports one */
    public String getLyricist() {
        return lyricist;
    }
     
    public String getNote() {
        return note;
    }
    
    public Song getOriginalSong() {
        return originalSong;
    }
    
    public void setTitle (String r) {
        if (r == null || r.isEmpty()) 
            title = null;
        else
            title = r;
    }    
    
    public void setSubtitle (String r) {
        if (r == null || r.isEmpty()) 
            subtitle = null;
        else
            subtitle = r;
    }    
    
    public void setComposer (String r) {
        if (r == null || r.isEmpty()) 
            composer = null;
        else
            composer = r;
    }
    
    public void setLyricist (String r) {
        if (r == null || r.isEmpty()) 
            lyricist = null;
        else
            lyricist = r;
    }
    
    public void setNote (String r) {
        if (r == null || r.isEmpty()) 
            note = null;
        else
            note = r;
    }
    
    public void setOriginalSong (Song s) {
        originalSong = s;
    }
}
