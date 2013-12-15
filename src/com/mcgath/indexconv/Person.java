package com.mcgath.indexconv;

/** The representation of a person, such as a lyricist, author, composer,
 *  editor, etc. Includes the person's role.
 */
public class Person {

    private String name;
    private String role1;
    private String role2;
    private String role3;
    
    /* There's only the default constructor. Setting a constructor
     * Person (name, role) -- or is it Person (role, name)? --
     * would only create opportunities for bugs.
     */
    public String getName() {
        return name;
    }
    
    public String getRole1() {
        return role1;
    }
    
    public String getRole2() {
        return role2;
    }
    
    public String getRole3() {
        return role3;
    }
    
    public void setName(String n) {
        if (n == null || n.isEmpty()) 
            name = null;
        else
            name = n;
    }
    
    /** This uses a heuristic to change names to the form "Last, First"
     *  if there is just one space. It won't get everything right,
     *  but it should minimize the amount of manual work needed.
     */
    public void setAndFlipName(String n) {
        String[] tokens = n.split (" ");
        if (tokens.length == 2 && ! ("The".equals (tokens[0]))) {
            n = tokens[1] + ", " + tokens[0];
        }
        name = n;
    }
    
    public void setRole1 (String r) {
        if (r == null || r.isEmpty()) 
            role1 = null;
        else
            role1 = r.toLowerCase();
    }
    
    public void setRole2 (String r) {
        if (r == null || r.isEmpty()) 
            role2 = null;
        else
            role2 = r.toLowerCase();
    }
    
    public void setRole3 (String r) {
        if (r == null || r.isEmpty()) 
            role3 = null;
        else
            role3 = r.toLowerCase();
    }
}
