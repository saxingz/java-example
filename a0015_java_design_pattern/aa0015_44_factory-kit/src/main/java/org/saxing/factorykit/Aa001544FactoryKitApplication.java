package org.saxing.factorykit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/1/8 21:23
 */
public class Aa001544FactoryKitApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001544FactoryKitApplication.class);

    public static void main(String[] args) {
        WeaponFactory weaponFactory = WeaponFactory.factory(builder -> {
            builder.add(WeaponType.SWORD, Sword::new);
            builder.add(WeaponType.AXE, Axe::new);
            builder.add(WeaponType.SPEAR, Spear::new);
            builder.add(WeaponType.BOW, Bow::new);
        });

        Weapon axe = weaponFactory.create(WeaponType.AXE);
        LOGGER.info(axe.toString());
    }

}

