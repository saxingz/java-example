package org.saxing.abstract_factory;

/**
 * elf king
 *
 * @author saxing  2018/10/7 15:05
 */
public class ElfKing implements King {

    static final String DESCRIPTION = "This is the Elven king";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
