package org.saxing.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RequestHandler
 * 
 * @author saxing  2018/11/18 14:38
 */
public abstract class RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandler.class);

    private RequestHandler next;

    public RequestHandler(RequestHandler next) {
        this.next = next;
    }

    public void handleRequest(Request req){
        if (next != null){
            next.handleRequest(req);
        }
    }

    public void printHanding(Request req){
        LOGGER.info("{} handling request \"{}\"", this, req);
    }

    @Override
    public abstract String toString();
}
