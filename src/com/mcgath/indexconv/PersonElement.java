package com.mcgath.indexconv;

import org.jdom2.Element;

public class PersonElement extends ElementHolder {

    private Element personElem;
    
    public PersonElement () {
        personElem = new Element ("name", ModsJdom.modsNS);
        personElem.setAttribute ("type", "personal");

    }
    
    public void buildPersonElement (String name, String ... roles) {
        Element namePartElem = new Element ("namePart", ModsJdom.modsNS);
        namePartElem.addContent (name);
        personElem.addContent (namePartElem);
        for (String role : roles) {
            Element roleElem = new Element ("role", ModsJdom.modsNS);
            Element roleTermElem = new Element ("roleTerm", ModsJdom.modsNS);
            roleElem.addContent (roleTermElem);
            roleTermElem.addContent (role);
            personElem.addContent (roleElem);
        }
    }
    
    @Override
    public Element getElement() {
        return personElem;
    }

}
