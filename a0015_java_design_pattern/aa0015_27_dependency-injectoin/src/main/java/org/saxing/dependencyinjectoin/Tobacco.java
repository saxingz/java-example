package org.saxing.dependencyinjectoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tobacco
 * 
 * @author saxing 2018/12/11 9:44
 */
public abstract class Tobacco {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tobacco.class);
    
    public void smoke(Wizard wizard){
        LOGGER.info("{} smoking {}", wizard.getClass().getSimpleName(),
                this.getClass().getSimpleName());
    }
    
}
