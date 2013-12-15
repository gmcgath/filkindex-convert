package com.mcgath.indexconv;

import org.jdom2.Element;

/* This is a wrapper for some of the more complicated JDOM elements */
public abstract class ElementHolder {

    /** Return the JDOM element */
    public abstract Element getElement ();
}
