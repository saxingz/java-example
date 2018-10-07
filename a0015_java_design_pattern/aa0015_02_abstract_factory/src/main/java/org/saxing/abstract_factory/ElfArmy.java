package org.saxing.abstract_factory;


/**
 * ElfArmy
 *
 * @author saxing  2018/10/7 15:03
 */
public class ElfArmy implements Army {

    static final String DESCRIPTION = "This is the Elven army";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
