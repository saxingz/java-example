package org.saxing.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main
 *
 * @author saxing 2019/1/9 23:47
 */
public class Aa001545FactoryMethodApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001545FactoryMethodApplication.class);

    private final Blacksmith blacksmith;

    public Aa001545FactoryMethodApplication(Blacksmith blacksmith) {
        this.blacksmith = blacksmith;
    }

    public static void main(String[] args) {
        Aa001545FactoryMethodApplication app = new Aa001545FactoryMethodApplication(new OrcBlacksmith());
        app.manufactureWeapons();

        app = new Aa001545FactoryMethodApplication(new ElfBlacksmith());
        app.manufactureWeapons();
    }

    private void manufactureWeapons(){
        Weapon weapon;
        weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
        LOGGER.info(weapon.toString());
        weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
        LOGGER.info(weapon.toString());
    }

}

