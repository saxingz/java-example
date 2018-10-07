package org.saxing.abstract_factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private King king;
    private Castle castle;
    private Army army;

    public void createKingdom(final KingdomFactory factory){
        setKing(factory.createKing());
        setCastle(factory.createCastle());
        setArmy(factory.createArmy());
    }

    King getKing(final KingdomFactory factory){
        return factory.createKing();
    }

    King getKing(){
        return king;
    }

    private void setKing(final King king){
        this.king = king;
    }

    Castle getCastle(final KingdomFactory factory){
        return factory.createCastle();
    }

    Castle getCastle(){
        return castle;
    }

    private void setCastle(final Castle castle){
        this.castle = castle;
    }

    Army getArmy(final KingdomFactory factory){
        return factory.createArmy();
    }

    Army getArmy(){
        return army;
    }

    private void setArmy(final Army army){
        this.army = army;
    }


    /**
     * factory of kingdom factories
     */
    public static class FactoryMaker{

        /**
         * Enumeration for the different types of Kingdoms
         */
        public enum KingdomType {
            ELF, ORC
        }

        public static KingdomFactory makeFactory(KingdomType type){
            switch (type){
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("KingdomType is not supported");

            }
        }


    }

    public static void main(String[] args) {
        App app = new App();

        LOGGER.info("Elf kingdom");
        app.createKingdom(FactoryMaker.makeFactory(FactoryMaker.KingdomType.ELF));
        LOGGER.info(app.getArmy().getDescription());
        LOGGER.info(app.getCastle().getDescription());
        LOGGER.info(app.getKing().getDescription());

        LOGGER.info("Orc kingdom");
        app.createKingdom(FactoryMaker.makeFactory(FactoryMaker.KingdomType.ORC));
        LOGGER.info(app.getArmy().getDescription());
        LOGGER.info(app.getCastle().getDescription());
        LOGGER.info(app.getKing().getDescription());

    }
}
