package org.saxing.abstract_factory;

/**
 * orc castle
 *
 * @author saxing  2018/10/7 15:08
 */
public class OrcCastle implements Castle {

    static final String DESCRIPTION = "This is the Orc castle";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
