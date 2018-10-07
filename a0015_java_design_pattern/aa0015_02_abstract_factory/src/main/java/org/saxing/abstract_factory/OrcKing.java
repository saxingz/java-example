package org.saxing.abstract_factory;

/**
 * orc king
 *
 * @author saxing  2018/10/7 15:09
 */
public class OrcKing implements King {

    static final String DESCRIPTION = "This is the Orc king";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
