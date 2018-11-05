package org.saxing.businessdelegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JmsService
 *
 * @author saxing  2018/11/5 21:37
 */
public class JmsService implements BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsService.class);

    @Override
    public void doProcessing() {
        LOGGER.info("JmsService is now processing");
    }
}
