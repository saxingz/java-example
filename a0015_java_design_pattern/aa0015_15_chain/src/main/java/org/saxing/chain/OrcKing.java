package org.saxing.chain;

/**
 * Orc King
 *
 * @author saxing  2018/11/18 14:53
 */
public class OrcKing {

    RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    private void buildChain(){
        chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
    }

    public void makeRequest(Request req){
        chain.handleRequest(req);
    }

}
