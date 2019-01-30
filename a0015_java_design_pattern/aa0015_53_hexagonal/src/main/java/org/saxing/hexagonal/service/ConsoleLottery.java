package org.saxing.hexagonal.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.saxing.hexagonal.banking.WireTransfers;
import org.saxing.hexagonal.domain.LotteryService;
import org.saxing.hexagonal.module.LotteryModule;
import org.saxing.hexagonal.mongo.MongoConnectionPropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Console interface for lottery players
 *
 * @author saxing 2019/1/30 15:05
 */
public class ConsoleLottery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleLottery.class);

    public static void main(String[] args) {
        MongoConnectionPropertiesLoader.load();
        Injector injector = Guice.createInjector(new LotteryModule());
        LotteryService service = injector.getInstance( LotteryService.class);
        WireTransfers bank = injector.getInstance(WireTransfers.class);
    }

}
