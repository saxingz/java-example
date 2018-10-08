package org.saxing.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zoom 
 * 
 * @author saxing  2018/10/8 23:27
 */
public class Zoom extends Modem {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigForDosVisitor.class);

    @Override
    public void accept(ModemVisitor modemVisitor) {
        if (modemVisitor instanceof ZoomVisitor){
            ((ZoomVisitor) modemVisitor).visit(this);
        }else {
            LOGGER.info("Only ZoomVisitor is allowed to visit Zoom modem");
        }
    }

    @Override
    public String toString() {
        return "Zoom modem";
    }
}
