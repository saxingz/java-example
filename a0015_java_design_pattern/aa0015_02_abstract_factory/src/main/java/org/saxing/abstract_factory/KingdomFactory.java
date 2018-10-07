package org.saxing.abstract_factory;

/**
 * kingdom factory interface
 *
 * @author saxing  2018/10/7 15:00
 */
public interface KingdomFactory {

    Army createArmy();

    Castle createCastle();

    King createKing();

}
