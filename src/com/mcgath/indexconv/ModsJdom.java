package com.mcgath.indexconv;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.ProcessingInstruction;

/** The JDOM model of a MODS output file.
 *  It's assumed that each function to add content will be called just once,
 *  unless multiple values can be accumulated.
 *  The effect of these functions is cumulative. */
public class ModsJdom {

    private Document modsDoc;
    private Element modsRoot;
    public final static Namespace modsNS = 
            Namespace.getNamespace("http://www.loc.gov/mods/v3");
    
    public ModsJdom () {
        modsDoc = new Document();
        addStylesheet ("stylesheets/modshtml.xsl");
        modsRoot = new Element ("mods", modsNS);
        modsDoc.setRootElement (modsRoot);
    }
    
    /** Return the MODS JDOM Document */
    public Document getDocument () {
        return modsDoc;
    }
    
    /* Set the MODS document ID */
    public void setID(String id) {
        modsRoot.setAttribute("ID", id);
    }
    
    /** Add the recordInfo element. Assumes both creation and change date are today. */
    public void addRecordInfo () {
    	Element recordInfo = new Element ("recordInfo", modsNS);
    	GregorianCalendar cal = new GregorianCalendar ();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH) + 1;
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	String dateStr = Integer.toString(year) + "-" + 
    			Integer.toString (month) + "-" +
    			Integer.toString (day);
    	Element creationElem = new Element ("recordCreationDate", modsNS);
    	creationElem.addContent (dateStr);
    	recordInfo.addContent (creationElem);
    	
    	Element changeElem = new Element ("recordChangeDate", modsNS);
    	changeElem.addContent (dateStr);
    	recordInfo.addContent (changeElem);
    	
    	modsRoot.addContent (recordInfo);
    }
    
    /** Add the title and subtitle information. title is assumed non-null. */
    public void addTitleInfo (String title, String subtitle) {
        Element titleInfo = new Element ("titleInfo", modsNS);
        Element titleElem = new Element ("title", modsNS);
        titleElem.addContent(title);
        titleInfo.addContent(titleElem);
        if (subtitle != null) {
            Element subtitleElem = new Element ("subTitle", modsNS);
            subtitleElem.addContent (subtitle);
            titleInfo.addContent (subtitleElem);
        }
        modsRoot.addContent (titleInfo);
    }
    
    /** Add the physical description. The text goes into the note 
     *  subelement. */
    public void addPhysicalDescription (String note) {
        Element pdElem = new Element ("physicalDescription", modsNS);
        Element noteElem = new Element ("note", modsNS);
        pdElem.addContent(noteElem);
        noteElem.addContent(note);
        modsRoot.addContent(pdElem);
    }
    
    
    /** Add the language as an iso639-2b string. There can be more than one. */
    public void addLanguage (String lang) {
        Element langElem = new Element ("language", modsNS);
        Element langTerm = new Element ("languageTerm", modsNS);
        langTerm.setAttribute("type", "code");
        langTerm.setAttribute("authority", "iso639-2b");
        langTerm.addContent(lang);
        langElem.addContent(langTerm);
        modsRoot.addContent(langElem);
    }
    
    /** Add the abstract. */
    public void addAbstract (String a) {
        Element abElem = new Element ("abstract", modsNS);
        abElem.addContent(a);
        modsRoot.addContent(abElem);
    }
    
    /** Add a top-level song. */
    public void addSong (Song s) {
        SongElement seHolder = new SongElement ("constituent");
        seHolder.buildSongElement (s);
        modsRoot.addContent (seHolder.getElement());
    }
    
    /** Build a song element for which we've created the base element.
     *  This can call itself recursively for "preceding" items 
     *  (i.e., tunes filked) */
//    private void buildSongElement (Song s, Element songElem) {
//    }

    private void addStylesheet (String href) {
    	ProcessingInstruction pi = new ProcessingInstruction ("xml-stylesheet");
    	pi.setPseudoAttribute("href", href);
    	pi.setPseudoAttribute("type", "text/xsl");
    	modsDoc.addContent (pi);
    }
    

}
