package org.saxing.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for spell targets.
 *
 * @author saxing  2018/11/23 14:23
 */
public abstract class Target {

    private static final Logger LOGGER = LoggerFactory.getLogger(Target.class);

    private Size size;
    private Visibility visibility;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public abstract String toString();

    /**
     * Print status
     */
    public void printStatus() {
        LOGGER.info("{}, [size={}] [visibility={}]", this, getSize(), getVisibility());
    }

}
