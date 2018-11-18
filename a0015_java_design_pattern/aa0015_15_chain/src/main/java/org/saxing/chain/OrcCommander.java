package org.saxing.chain;

import java.util.Objects;

/**
 * orc commander
 *
 * @author saxing  2018/11/18 14:54
 */
public class OrcCommander extends RequestHandler {

    public OrcCommander(RequestHandler handler) {
        super(handler);
    }

    @Override
    public void handleRequest(Request req) {
        if (Objects.equals(req.getRequestType(), RequestType.DEFEND_CASTLE)){
            printHanding(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }
    }

    @Override
    public String toString() {
        return "Orc Commander";
    }
}
