package org.saxing.extensionobjects;

import org.saxing.extensionobjects.abstractextensions.CommanderExtension;
import org.saxing.extensionobjects.abstractextensions.SergeantExtension;
import org.saxing.extensionobjects.abstractextensions.SoldierExtension;
import org.saxing.extensionobjects.units.CommanderUnit;
import org.saxing.extensionobjects.units.SergeantUnit;
import org.saxing.extensionobjects.units.SoldierUnit;
import org.saxing.extensionobjects.units.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/1/6 11:56
 */
public class Aa001542ExtensionObjectsApplication {

    public static void main(String[] args) {

        //Create 3 different units
        Unit soldierUnit = new SoldierUnit("SoldierUnit1");
        Unit sergeantUnit = new SergeantUnit("SergeantUnit1");
        Unit commanderUnit = new CommanderUnit("CommanderUnit1");

        checkExtensionsForUnit(soldierUnit);
        checkExtensionsForUnit(sergeantUnit);
        checkExtensionsForUnit(commanderUnit);
    }

    /**
     * check extensions for unit
     *
     * @param unit
     */
    private static void checkExtensionsForUnit(Unit unit){
        final Logger logger = LoggerFactory.getLogger(Aa001542ExtensionObjectsApplication.class);

        SoldierExtension soldierExtension = (SoldierExtension) unit.getUnitExtension("SoldierExtension");
        SergeantExtension sergeantExtension = (SergeantExtension) unit.getUnitExtension("SergeantExtension");
        CommanderExtension commanderExtension = (CommanderExtension) unit.getUnitExtension("CommanderExtension");

        //if unit have extension call the method
        if (soldierExtension != null) {
            soldierExtension.soldierReady();
        } else {
            logger.info(unit.getName() + " without SoldierExtension");
        }

        if (sergeantExtension != null) {
            sergeantExtension.sergeantReady();
        } else {
            logger.info(unit.getName() + " without SergeantExtension");
        }

        if (commanderExtension != null) {
            commanderExtension.commanderReady();
        } else {
            logger.info(unit.getName() + " without CommanderExtension");
        }
    }

}

