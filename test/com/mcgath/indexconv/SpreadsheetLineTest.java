package com.mcgath.indexconv;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpreadsheetLineTest {


    
    @Test
    public void testLine() {
        String line = "one, two\tthree four\tfive";
        SpreadsheetLine ssLine = new SpreadsheetLine(line);
        String[] comps = ssLine.getTokens();
        assertEquals (comps.length, 3);
        assertEquals ("one, two", comps[0]);
        assertEquals ("three four", comps[1]);
        assertEquals ("five", comps[2]);
    }


}
