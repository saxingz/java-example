package org.saxing.hexagonal;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.saxing.hexagonal.domain.LotteryAdministration;
import org.saxing.hexagonal.domain.LotteryService;
import org.saxing.hexagonal.module.LotteryTestingModule;
import org.saxing.hexagonal.sampledata.SampleData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/1/30 17:03
 */
public class Aa001553HexagonalApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new LotteryTestingModule());

        // start new lottery round
        LotteryAdministration administration = injector.getInstance(LotteryAdministration.class);
        administration.resetLottery();

        // submit some lottery tickets
        LotteryService service = injector.getInstance(LotteryService.class);
        SampleData.submitTickets(service, 20);

        // perform lottery
        administration.performLottery();
    }

}

