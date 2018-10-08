package org.saxing.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hayes
 * 
 * @author saxing  2018/10/8 23:29 
 */
public class Hayes extends Modem {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigForDosVisitor.class);

    @Override
    public void accept(ModemVisitor modemVisitor) {
        if (modemVisitor instanceof HayesVisitor){
            ((HayesVisitor) modemVisitor).visit(this);
        }else {
            LOGGER.info("Only HayesVisitor is allowed to visit Hayes modem");
        }
    }

    @Override
    public String toString() {
        return "Hayes modem";
    }
}
