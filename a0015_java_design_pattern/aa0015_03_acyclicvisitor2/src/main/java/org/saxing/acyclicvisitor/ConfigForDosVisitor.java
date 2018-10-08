package org.saxing.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * config for dos visitor 
 * 
 * @author saxing  2018/10/8 23:31 
 */
public class ConfigForDosVisitor implements AllModemVisitor {

    private Logger LOGGER = LoggerFactory.getLogger(ConfigForDosVisitor.class);

    @Override
    public void visit(Hayes hayes) {
        LOGGER.info(hayes + " used with Dos configurator.");
    }

    @Override
    public void visit(Zoom zoom) {
        LOGGER.info(zoom + " used with dos configurator.");
    }
}
