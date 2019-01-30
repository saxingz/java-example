package org.saxing.hexagonal.administration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.saxing.hexagonal.domain.LotteryAdministration;
import org.saxing.hexagonal.domain.LotteryService;
import org.saxing.hexagonal.module.LotteryModule;
import org.saxing.hexagonal.mongo.MongoConnectionPropertiesLoader;
import org.saxing.hexagonal.simpledata.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Console interface for lottery administration
 *
 * @author saxing 2019/1/30 14:10
 */
public class ConsoleAdministration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleAdministration.class);

    public static void main(String[] args) {
        MongoConnectionPropertiesLoader.load();
        Injector injector = Guice.createInjector(new LotteryModule());
        LotteryAdministration administration = injector.getInstance(LotteryAdministration.class);
        LotteryService service = injector.getInstance(LotteryService.class);
        SampleData.submitTickets(service, 20);
        ConsoleAdministrationSrv consoleAdministration = new ConsoleAdministrationSrvImpl(administration, LOGGER);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit){
                printMainMenu();
                String cmd = readString(scanner);
                if ("1".equals(cmd)) {
                    consoleAdministration.getAllSubmittedTickets();
                } else if ("2".equals(cmd)) {
                    consoleAdministration.performLottery();
                } else if ("3".equals(cmd)) {
                    consoleAdministration.resetLottery();
                } else if ("4".equals(cmd)) {
                    exit = true;
                } else {
                    LOGGER.info("Unknown command: {}", cmd);
                }
            }
        }
    }

    private static void printMainMenu(){
        LOGGER.info("");
        LOGGER.info("### Lottery Administration Console ###");
        LOGGER.info("(1) Show all submitted tickets");
        LOGGER.info("(2) Perform lottery draw");
        LOGGER.info("(3) Reset lottery ticket database");
        LOGGER.info("(4) Exit");
    }

    private static String readString(Scanner scanner) {
        System.out.print("> ");
        return scanner.next();
    }

}
