package org.saxing.ambassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * service ambassador
 *
 * @author saxing  2018/10/14 14:23
 */
public class ServiceAmbassador implements RemoteServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(Thread.currentThread().getClass());
    private static final int RETRIES = 3;
    private static final int DELAY_MS = 3000;

    ServiceAmbassador(){}

    @Override
    public long doRemoteFunction(int value){
        return safeCall(value);
    }

    private long checkLatency(int value){
        long startTime = System.currentTimeMillis();
        long result = RemoteService.getRemoteService().doRemoteFunction(value);
        long timeToken = System.currentTimeMillis() - startTime;

        LOGGER.info("Time taken (ms): " + timeToken);
        return result;
    }

    private long safeCall(int value){
        int retries = 0;
        long result = -1;

        for (int i = 0; i < RETRIES; i++) {
            if (retries >= RETRIES){
                return -1;
            }

            if ((result = checkLatency(value)) == -1){
                LOGGER.info("Failed to reach remote: (" + (i + 1) + ")");
                retries++;

                try {
                    Thread.sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    LOGGER.error("Thread sleep state interrupted", e);
                }
            } else {
                break;
            }
        }
        return result;
    }
}
