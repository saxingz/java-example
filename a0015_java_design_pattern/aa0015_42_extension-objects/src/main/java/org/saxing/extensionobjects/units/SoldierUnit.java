package org.saxing.extensionobjects.units;

import org.saxing.extensionobjects.abstractextensions.UnitExtension;
import org.saxing.extensionobjects.concreteextensions.Soldier;

/**
 * Class defining SoldierUnit
 *
 * @author saxing 2019/1/6 11:34
 */
public class SoldierUnit extends Unit {

    public SoldierUnit(String name) {
        super(name);
    }

    @Override
    public UnitExtension getUnitExtension(String extensionName) {
        if (extensionName.equals("SoldierExtension")){
            if (unitExtension == null){
                unitExtension = new Soldier(this);
            }
            return unitExtension;
        }
        return super.getUnitExtension(extensionName);
    }
}
