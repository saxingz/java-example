package org.saxing.abstract_factory;

/**
 * elf castle
 *
 * @author saxing  2018/10/7 15:04
 */
public class ElfCastle implements Castle {

    static final String DESCRIPTION = "This is the Elven castle";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
