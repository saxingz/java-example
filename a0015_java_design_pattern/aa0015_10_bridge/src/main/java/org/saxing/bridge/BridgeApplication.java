package org.saxing.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main
 *
 * @author saxing  2018/11/3 17:36
 */
//@SpringBootApplication
public class BridgeApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(BridgeApplication.class, args);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BridgeApplication.class);

    public static void main(String[] args) {
        LOGGER.info("The knight receives an enchanted sword.");
        Sword sword = new Sword(new SoulEatingEnchantment());
        sword.wield();
        sword.swing();
        sword.unwield();

        LOGGER.info("The valkyrie receives an enchanted hammer.");
        Hammer hammer = new Hammer(new FlyingEnchantment());
        hammer.wield();
        hammer.swing();
        sword.unwield();
    }

}
