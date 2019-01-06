package org.saxing.extensionobjects.units;

import org.saxing.extensionobjects.abstractextensions.UnitExtension;
import org.saxing.extensionobjects.concreteextensions.Commander;

/**
 * Class defining CommanderUnit
 *
 * @author saxing 2019/1/6 11:52
 */
public class CommanderUnit extends Unit {

    public CommanderUnit(String name) {
        super(name);
    }

    @Override
    public UnitExtension getUnitExtension(String extensionName) {
        if (extensionName.equals("CommanderExtension")){
            if (unitExtension == null){
                unitExtension = new Commander(this);
            }
            return unitExtension;
        }
        return super.getUnitExtension(extensionName);
    }
}
