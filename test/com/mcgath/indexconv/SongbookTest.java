package com.mcgath.indexconv;

import static org.junit.Assert.*;

import org.junit.Test;

public class SongbookTest {

    @Test
    public void testSimpleSongbookCase () {
        Songbook sb = new Songbook ();
        SpreadsheetLine ssl = new SpreadsheetLine("Title\tThus Filked Zarathustra");
        ssl.putIntoSongbook(sb);
        ssl = new SpreadsheetLine("Subtitle\t2001 filk book");
        ssl.putIntoSongbook(sb);
        ssl = new SpreadsheetLine("Abstract\tPicasso");
        ssl.putIntoSongbook(sb);
        ssl = new SpreadsheetLine("Copyright\tCopyright 2001");
        ssl.putIntoSongbook(sb);
        ssl = new SpreadsheetLine("Year\t2001");
        ssl.putIntoSongbook(sb);
        ssl = new SpreadsheetLine("Physical description\tGBC bound");
        ssl.putIntoSongbook(sb);
        ssl = new SpreadsheetLine("Place\tPhiladelphia");
        ssl.putIntoSongbook(sb);
        assertEquals ("Thus Filked Zarathustra", sb.getTitle());
        assertEquals ("2001 filk book", sb.getSubtitle());
        assertEquals ("Picasso", sb.getAbstract());
        assertEquals ("Copyright 2001", sb.getCopyright());
        assertEquals ("2001", sb.getYear());
        assertEquals ("GBC bound", sb.getPhysicalDesc());
        assertEquals ("Philadelphia", sb.getPlace());
    }

}
