package org.saxing.abstract_factory;

/**
 * elf kingdom factory
 * 
 * @author saxing  2018/10/7 15:06 
 */
public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Army createArmy() {
        return new ElfArmy();
    }

    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }
}
