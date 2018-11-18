package org.saxing.chain;

import java.util.Objects;

/**
 * orc officer
 *
 * @author saxing  2018/11/18 14:48
 */
public class OrcOfficer extends RequestHandler {

    public OrcOfficer(RequestHandler handler) {
        super(handler);
    }

    @Override
    public void handleRequest(Request req) {
        if (Objects.equals(req.getRequestType(), RequestType.TORTURE_PRISONER)){
            printHanding(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }
    }

    @Override
    public String toString() {
        return "Orc officer";
    }
}
