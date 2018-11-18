package org.saxing.chain;

import java.util.Objects;

/**
 * orc soldier
 *
 * @author saxing  2018/11/18 14:51
 */
public class OrcSoldier extends RequestHandler{

    public OrcSoldier(RequestHandler handler) {
        super(handler);
    }

    @Override
    public void handleRequest(Request req) {
        if (Objects.equals(req.getRequestType(), RequestType.COLLECT_TAX)){
            printHanding(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }
    }

    @Override
    public String toString() {
        return "Orc Soldier";
    }
}
