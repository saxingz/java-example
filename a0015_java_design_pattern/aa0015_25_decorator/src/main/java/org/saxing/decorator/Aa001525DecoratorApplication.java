package org.saxing.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SpringBootApplication

/**
 * main
 *
 * @author saxing 2018/12/8 11:38
 *
 */
public class Aa001525DecoratorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001525DecoratorApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(Aa001525DecoratorApplication.class, args);

        LOGGER.info("A simple looking troll approaches.");
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();
        LOGGER.info("Simple troll power {}.\n", troll.getAttackPower());

        // change the behavior of the simple troll by adding a decorator
        LOGGER.info("A troll with huge club surprises you.");
        Troll clubbedTroll = new ClubbedTroll(troll);
        clubbedTroll.attack();
        clubbedTroll.fleeBattle();
        LOGGER.info("Clubbed troll power {}.\n", clubbedTroll.getAttackPower());
    }
}
