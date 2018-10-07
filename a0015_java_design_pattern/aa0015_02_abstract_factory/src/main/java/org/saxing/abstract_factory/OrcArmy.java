package org.saxing.abstract_factory;

/**
 * orc army
 * 
 * @author saxing  2018/10/7 15:07 
 */
public class OrcArmy implements Army {

    static final String DESCRIPTION = "This is the Orc army";
    
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
