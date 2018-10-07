package org.saxing.abstract_factory;

/**
 * orc kingdom factory 
 * 
 * @author saxing  2018/10/7 15:10 
 */
public class OrcKingdomFactory implements KingdomFactory {
    @Override
    public Army createArmy() {
        return new OrcArmy();
    }

    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public King createKing() {
        return new OrcKing();
    }
}
