package com.mcgath.indexconv;

/* This is used for unit testing. Rather than actually outputting
 * a Songbook, it provides a stub. */
public class TestOutputter implements SongbookOutputter {

    private Songbook songbook;
    
    /* The only "output" this does is to make the Songbook object available */
    public void writeSongbook (Songbook s) {
        songbook = s;
    }
    
    public Songbook getSongbook() {
        return songbook;
    }
    
}
