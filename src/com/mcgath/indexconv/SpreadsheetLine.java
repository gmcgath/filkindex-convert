package com.mcgath.indexconv;

/** This class deals with the parsing of one line from the spreadsheet.
 *  We assume tab separation. */
public class SpreadsheetLine {

    public enum LineKind {
        TITLE,
        SUBTITLE,
        LANGUAGE,
        PHYSDESC,
        ABSTRACT,
        PLACE,
        PUBLISHER,
        YEAR,
        COPYRIGHT,
        CONTRIB_START,
        CONTRIB_END,
        SONG_START,
        SONG_END,
        OTHER
    }
    
    
    private String rawLine;
    private String[] tokens;
    
    
    LineKind kind;
    
    public SpreadsheetLine(String text) {
        rawLine = text;
        tokens = parse ();
    }
    
    public String[] getTokens () {
        return tokens;
    }
    
    private String[] parse () {
        String[] tokens = rawLine.split ("\\t");
        kind = LineKind.OTHER;
        if (tokens.length >= 1) {
            String first = tokens[0];
            if (first.startsWith("Title")) 
                kind = LineKind.TITLE;
            else if (first.startsWith ("Subtitle"))
                kind = LineKind.SUBTITLE;
            else if (first.startsWith("Language"))
                kind = LineKind.LANGUAGE;
            else if (first.startsWith("Physical"))
                kind = LineKind.PHYSDESC;
            else if (first.startsWith("Abstract"))
                kind = LineKind.ABSTRACT;
            else if (first.startsWith ("Place"))
                kind = LineKind.PLACE;
            else if (first.startsWith ("Publisher"))
                kind = LineKind.PUBLISHER;
            else if (first.startsWith ("Year"))
                kind = LineKind.YEAR;
            else if (first.startsWith ("Copyright"))
                kind = LineKind.COPYRIGHT;
            else if (first.startsWith ("_CONTRIB_START"))
                kind = LineKind.CONTRIB_START;
            else if (first.startsWith ("_CONTRIB_END"))
                kind = LineKind.CONTRIB_END;
            else if (first.startsWith ("_SONG_START"))
                kind = LineKind.SONG_START;
            else if (first.startsWith ("_SONG_END"))
                kind = LineKind.SONG_END;
        }
        return tokens;
    }
    
    /** Returns the token representing the first column of the line.
     *  Returns OTHER if it wasn't a recognized token. */
    public LineKind getKind () {
        return kind;
    }
    
    /** Put the line into the songbook. Only label/value lines
     *  are handled here. */
    public void putIntoSongbook (Songbook songbook) {
        String val;
        if (tokens.length < 2) {
            val = "";
        }
        else {
            val = tokens[1];
        }
        switch (kind) {
        case TITLE:
            songbook.setTitle (val);
            break;
        case SUBTITLE:
            songbook.setSubtitle (val);
            break;
        case LANGUAGE:
            songbook.setLanguage (val);
            break;
        case PHYSDESC:
            songbook.setPhysicalDesc (val);
            break;
        case ABSTRACT:
            songbook.setAbstract (val);
            break;
        case PLACE:
            songbook.setPlace (val);
            break;
        case PUBLISHER:
            songbook.setPublisher (val);
            break;
        case YEAR:
            songbook.setYear (val);
            break;
        case COPYRIGHT:
            songbook.setCopyright (val);
            break;
        }
    }
    
    /* Put the line into the songbook as a contributor. */
    public void contributorToSongbook (Songbook songbook) {
        String name = null;
        String role1 = null;
        String role2 = null;
        String role3 = null;
        if (tokens.length > 1) 
            name = tokens[1];
        if (tokens.length > 2)
            role1 = tokens[2];
        if (tokens.length > 3)
            role1 = tokens[3];
        if (tokens.length > 4)
            role1 = tokens[4];
        Person p = new Person();
        p.setAndFlipName(name);
        p.setRole1(role1);
        p.setRole2(role2);
        p.setRole3(role3);
        songbook.addContributor(p);
    }
    
    /* Put the line into the songbook as a song. */
    public void songToSongbook (Songbook songbook) {
        String title = null;
        String composer = null;
        String lyricist = null;
        String origTitle = null;
        String origComposer = null;
        String note = null;
        Song origTune = null;
        if (tokens.length > 1)
            title = tokens[1];
        if (tokens.length > 2)
            composer = tokens[2];
        if (tokens.length > 3)
            lyricist = tokens[3];
        if (tokens.length > 4)
            origTitle = tokens[4];
        if (tokens.length > 5)
            origComposer = tokens[5];
        if (tokens.length > 6) 
            note = tokens[6];
        if (origTitle != null && origTitle.length() > 0) {
            origTune = new Song();
            origTune.setTitle (origTitle);
            origTune.setComposer (origComposer);
            
        }
        Song song = new Song ();
        song.setTitle(title);
        song.setComposer(composer);
        song.setLyricist(lyricist);
        song.setNote(note);
        song.setOriginalSong(origTune);
        songbook.addSong(song);
    }
}
