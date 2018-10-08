package org.saxing.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigForUnixVisitor implements ZoomVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigForUnixVisitor.class);

    @Override
    public void visit(Zoom zoom) {

        LOGGER.info(zoom + " used with unix configurator.");

    }
}
