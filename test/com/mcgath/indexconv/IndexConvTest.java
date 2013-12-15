package com.mcgath.indexconv;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class IndexConvTest {


    
    @Test
    public void test1() {
        String inpath = "testfiles/test1.tsv";
        TestOutputter to = new TestOutputter();
        IndexConv iconv = new IndexConv();
        iconv.setOutputter(to);
        iconv.run(inpath, null);
        Songbook sbk = to.getSongbook();
        assertNotNull (sbk);
        
        String title = sbk.getTitle();
        assertEquals(title, "Filksongs for All Eternity");
        String subtitle = sbk.getSubtitle();
        assertNull(subtitle);
        String physDesc = sbk.getPhysicalDesc();
        assertEquals(physDesc, "8 1/2 x 11, stapled");
        String abstracte = sbk.getAbstract();
        assertEquals(abstracte, "Dark Shadows filks");
        String place = sbk.getPlace();
        assertEquals(place, "Temple City, CA");
        String publisher = sbk.getPublisher();
        assertEquals(publisher, "Old Collinwood Publishing House");
        String year = sbk.getYear();
        assertEquals(year, "1979");
        String copyright = sbk.getCopyright();
        assertNull(copyright);
        
        List<Person> contribs = sbk.getContributors();
        assertNotNull (contribs);
        assertEquals (contribs.size(), 3);
        Person contrib1 = contribs.get(0);
        assertEquals (contrib1.getName(), "Jo Ann Christy");
        assertEquals (contrib1.getRole1(), "editor");
        
        List<Song> songs = sbk.getSongs();
        assertNotNull (songs);
        assertEquals (songs.size(), 42);
        Song song = songs.get(0);
        assertEquals (song.getTitle(), "Curse You, ABC");
        assertEquals (song.getLyricist(), "The Cursed Ones");
        Song origSong = song.getOriginalSong();
        assertNotNull (origSong);
        assertEquals (origSong.getTitle(), "My Bonnie");
    }

}
