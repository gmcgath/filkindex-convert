package com.mcgath.indexconv;

import static org.junit.Assert.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.junit.Test;

public class ModsJdomTest {



    @Test
    public void testModsJdom () {
        ModsJdom mjdom = new ModsJdom();
        mjdom.setID("abcde");
        mjdom.addTitleInfo("Title", "Subtitle");
        
        Document doc = mjdom.getDocument ();
        assertNotNull(doc);
        Element root = doc.getRootElement();
        assertEquals (root.getName(), "mods");
        Element titleInfo = root.getChild("titleInfo", ModsJdom.modsNS);
        assertNotNull (titleInfo);
    }
}
