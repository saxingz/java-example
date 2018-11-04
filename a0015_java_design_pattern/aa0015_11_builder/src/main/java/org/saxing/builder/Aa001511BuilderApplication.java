package org.saxing.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * builder
 *
 * @author saxing  2018/11/4 11:09
 */
//@SpringBootApplication
public class Aa001511BuilderApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001511BuilderApplication.class, args);
//    }

    private final static Logger LOGGER = LoggerFactory.getLogger(Aa001511BuilderApplication.class);

    public static void main(String[] args) {
        Hero mage = new Hero.Builder(Profession.MAGE, "Riobard").withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER).build();;
        LOGGER.info(mage.toString());

        Hero warrior = new Hero.Builder(Profession.WARRIOR, "Amberjill").withHairColor(HairColor.BLOND)
                .withHairType(HairType.LONG_CURLY).withArmor(Armor.CHAIN_MAIL).withWeapon(Weapon.SWORD)
                .build();
        LOGGER.info(warrior.toString());

        Hero thief = new Hero.Builder(Profession.THIEF, "Desmond").withHairType(HairType.BALD)
                .withWeapon(Weapon.BOW).build();
        LOGGER.info(thief.toString());
    }

}
