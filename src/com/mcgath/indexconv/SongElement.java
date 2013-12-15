package com.mcgath.indexconv;

import org.jdom2.Element;

public class SongElement extends ElementHolder {

    private Element songElem;
    
    /** 
     * Constructur.
     * 
     * @param e JDOM element, consisting of a bare 
     */
    public SongElement (String typ) {
        songElem = new Element ("relatedItem", ModsJdom.modsNS);
        songElem.setAttribute ("type", typ);
    }

    /** Populate the element with the contents of the song */
    public void buildSongElement (Song song) {
        String title = song.getTitle();
        if (title != null) {
            // Add the title and subtitle if any
            Element titleInfoElem = new Element ("titleInfo", ModsJdom.modsNS);
            Element titleElem = new Element ("title", ModsJdom.modsNS);
            titleElem.addContent(title);
            titleInfoElem.addContent (titleElem);
            String subtitle = song.getSubtitle();
            if (subtitle != null) {
                Element subtitleElem = new Element ("subTitle", ModsJdom.modsNS);
                titleInfoElem.addContent (subtitleElem);  
            }
            songElem.addContent (titleInfoElem);
        }
        
        // Add the composer and lyricist; this app assumes no more than one,
        // and they may be the same
        String composer = song.getComposer();
        String lyricist = song.getLyricist();
        boolean composerIsLyricist = false;
        if (composer != null) {
            PersonElement compElem = new PersonElement();
            if (composer.equals(lyricist)) {
                composerIsLyricist = true;
                compElem.buildPersonElement (composer, "composer", "lyricist");
            }
            else {
                compElem.buildPersonElement(composer, "composer");
            }
            songElem.addContent (compElem.getElement());
        }
        if (lyricist != null && !composerIsLyricist) {
            PersonElement lyrElem = new PersonElement();
            lyrElem.buildPersonElement (lyricist, "lyricist");
            songElem.addContent (lyrElem.getElement());
        }
        
        // If there's an original tune, add it recursively
        Song origSong = song.getOriginalSong();
        if (origSong != null) {
            SongElement origElem = new SongElement ("preceding");
            origElem.buildSongElement(origSong);
            songElem.addContent (origElem.getElement());
        }
        
    }
    
    @Override
    public Element getElement() {
        return songElem;
    }

}
