package com.mcgath.indexconv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.mcgath.indexconv.SpreadsheetLine.LineKind;

/**
 *  The main class for the filk index converter.
 *  This operates on a tab separated file based on
 *  a spreadsheet according to these model documents:
 *  
 *  http://www.massfilc.org/filkindex/Filkindex%20Blank%20Spreadsheet.ods
 *  http://www.massfilc.org/filkindex/Filkindex%20Blank%20Spreadsheet.xls
 */
public class IndexConv {

    
    private SongbookOutputter outputter; // Use only to override normal output
    private boolean inPeople;
    private boolean inSongs;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println ("Usage: java IndexConv infile outfile");
            return;
        }
        String inpath = args[0];
        String outpath = args[1];
        IndexConv iconv = new IndexConv();
        iconv.run (inpath, outpath);
    }
    
    /** Call this in before run to use test "output" and ignore the
     *  output parameter on the command line.
     */
    public void setOutputter(SongbookOutputter t) {
        outputter = t;
    }

    /** Can be called from main or directly. outpath may be null for
     *  testing purposes, provided a TestOutputter is set. */
    public void run(String inpath, String outpath) {
        inPeople = false;
        inSongs = false;
        
        Songbook songbook = new Songbook ();
        File infile = new File (inpath);
        FileReader rdr;
        BufferedReader brdr;
        try {
            rdr = new FileReader (infile);
            brdr = new BufferedReader (rdr);
        }
        catch (Exception e) {
            System.out.println ("Cannot open file " + inpath);
            System.out.println (e.getClass().getName());
            return;
        }
        try {
            for (;;) {
                String line = brdr.readLine ();
                if (line == null) {
                    break;
                }
                SpreadsheetLine slin = new SpreadsheetLine(line);
                processLine (slin, songbook);
            }
            brdr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            try { brdr.close(); } catch (Exception ee) {}
            return;
        }
        
        if (!songbook.validate()) {
            System.out.println ("Songbook is not valid. No output file generated.");
            return;
        }
        
        // and now for output
        if (outputter != null) {
            // This overrides the command line
            try {
                outputter.writeSongbook(songbook);
            }
            catch (Exception e) {
                System.out.println ("Output failed.");
                System.out.println (e.getClass().getName());
                String msg = e.getMessage();
                if (msg != null) 
                    System.out.println (msg);
                Throwable cause = e.getCause();
                if (cause != null) {
                    System.out.println ("Cause: " + cause.getClass().getName());
                }
            }
        }
        else {
            // Output based on the command line
            try {
                FileWriter fwrtr = new FileWriter(outpath);
                ModsOutputter outputter = new ModsOutputter(fwrtr);
                outputter.writeSongbook(songbook);
                System.out.println ("Completed successfully.");
            }
            catch (IOException e) {
                System.out.println ("Could not write to output file " + outpath);
                System.out.println (e.getClass().getName());
                String msg = e.getMessage();
                if (msg != null) 
                    System.out.println (msg);
            }
        }
    }
    
    /* Process one line from the spreadsheet */
    private void processLine (SpreadsheetLine lin, Songbook songbook) {
        String[] tokens = lin.getTokens ();
        SpreadsheetLine.LineKind kind = lin.getKind();
        String val;
        if (tokens.length < 2) {
            val = "";
        }
        else {
            val = tokens[1];
        }
        if (inPeople) {
            if (kind == LineKind.CONTRIB_END) {
                inPeople = false;
            }
            else {
                lin.contributorToSongbook (songbook);
            }
        }
        else if (inSongs) {
            if (kind == LineKind.SONG_END) {
                inSongs = false;
            }
            else {
                lin.songToSongbook (songbook);
            }
        }
        else {
            switch (kind) {
            case TITLE:
            case SUBTITLE:
            case LANGUAGE:
            case PHYSDESC:
            case ABSTRACT:
            case PLACE:
            case PUBLISHER:
            case YEAR:
            case COPYRIGHT:
                lin.putIntoSongbook(songbook);
                break;
            case CONTRIB_START:
                inPeople = true;
                break;
            case SONG_START:
                inSongs = true;
                break;
            }
        }
    }
}
