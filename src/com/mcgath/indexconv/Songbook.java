package com.mcgath.indexconv;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/** The structure of a songbook as held in memory */
public class Songbook {

    private String title;
    private String subtitle;
    private String language;
    private String physicalDesc;
    private String abstracte;
    private String place;
    private String publisher;
    private String year;
    private String copyright;
    private List<Person> contributors;
    private List<Song> songs;
    
    public Songbook () {
        contributors = new ArrayList<Person>();
        songs = new ArrayList<Song>();
    }
    
    /** See if the completed Songbook meets the minimum requirements.
     *   @return  true iff songbook is valid */
    public boolean validate () {
        return (title != null);
    }
    

    
    
    /** Set title. Empty string is converted to null. */
    public void setTitle (String t) {
        if (!t.isEmpty())
            title = t;
        else
            title = null;
    }
    
    public String getTitle () {
        return title;
    }

    /* Set subtitle. Empty string is converted to null. */
    public void setSubtitle (String t) {
        if (t != null && !t.isEmpty())
            subtitle = t;
        else
            subtitle = null;
    }
    
    public String getSubtitle () {
        return subtitle;
    }

    /* Set language. Empty string is converted to null. */
    public void setLanguage (String t) {
        if (t != null && !t.isEmpty())
            language = t;
        else
            language= null;
    }
    
    public String getLanguage () {
        return language;
    }

    /* Set physical description. Empty string is converted to null. */
    public void setPhysicalDesc (String t) {
        if (t != null && !t.isEmpty())
            physicalDesc = t;
        else
            physicalDesc = null;
    }
    
    public String getPhysicalDesc () {
        return physicalDesc;
    }

    /* Set abstract. Empty string is converted to null. */
    public void setAbstract (String t) {
        if (t != null && !t.isEmpty())
            abstracte = t;
        else
            abstracte = null;
    }
    
    public String getAbstract () {
        return abstracte;
    }

    /* Set place. Empty string is converted to null. */
    public void setPlace (String t) {
        if (t != null && !t.isEmpty())
            place = t;
        else
            place = null;
    }
    
    public String getPlace () {
        return place;
    }

    /* Set publisher. Empty string is converted to null. */
    public void setPublisher (String t) {
        if (t != null && !t.isEmpty())
            publisher = t;
        else
            publisher = null;
    }
    
    public String getPublisher () {
        return publisher;
    }

    /* Set year. Empty string is converted to null. */
    public void setYear (String t) {
        if (t != null && !t.isEmpty())
            year = t;
        else   
            year = null;
    }
    
    public String getYear () {
        return year;
    }
    
    /* Set copyright. Empty string is converted to null. */
    public void setCopyright (String t) {
        if (t != null && !t.isEmpty())
            copyright = t;
        else
            copyright = null;
    }
    
    public String getCopyright () {
        return copyright;
    }
    
    /* Add contributor. */
    public void addContributor (Person contributor) {        
        contributors.add(contributor);
    }
    
    public List<Person> getContributors () {
        return contributors;
    }
    
    /* Add song. */
    public void addSong (Song s) {
        songs.add(s);
    }
    
    public List<Song> getSongs () {
        return songs;
    }
}
