package com.mcgath.indexconv;

import java.io.IOException;

/* This interface is implemented by classes that "output," in some broad
 * sense, a Songbook. It can include classes for testing as well as for
 * actual output.
 */
public interface SongbookOutputter {
    public void writeSongbook(Songbook s) throws IOException;
}
