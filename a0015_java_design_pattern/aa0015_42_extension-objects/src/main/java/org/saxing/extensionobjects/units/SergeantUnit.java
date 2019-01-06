package org.saxing.extensionobjects.units;

import org.saxing.extensionobjects.abstractextensions.UnitExtension;
import org.saxing.extensionobjects.concreteextensions.Sergeant;

/**
 * Class defining SergeantUnit
 *
 * @author saxing 2019/1/6 11:42
 */
public class SergeantUnit extends Unit {

    public SergeantUnit(String name) {
        super(name);
    }

    @Override
    public UnitExtension getUnitExtension(String extensionName) {
        if (extensionName.equals("SergeantExtension")) {
            if (unitExtension == null) {
                unitExtension = new Sergeant(this);
            }
            return unitExtension;
        }
        return super.getUnitExtension(extensionName);
    }
}
