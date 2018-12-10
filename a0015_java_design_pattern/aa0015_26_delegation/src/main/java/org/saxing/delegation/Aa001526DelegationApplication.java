package org.saxing.delegation;

import org.saxing.delegation.printers.CanonPrinter;
import org.saxing.delegation.printers.EpsonPrinter;
import org.saxing.delegation.printers.HpPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication

/**
 * main
 *
 * @author saxing 2018/12/10 22:25
 */
public class Aa001526DelegationApplication {

    private static final String MESSAGE_TO_PRINT = "hello world";

    public static void main(String[] args) {
//        SpringApplication.run(Aa001526DelegationApplication.class, args);

        PrinterController hpPrinterController = new PrinterController(new HpPrinter());
        PrinterController canonPrinterController = new PrinterController(new CanonPrinter());
        PrinterController epsonPrinterController = new PrinterController(new EpsonPrinter());

        hpPrinterController.print(MESSAGE_TO_PRINT);
        canonPrinterController.print(MESSAGE_TO_PRINT);
        epsonPrinterController.print(MESSAGE_TO_PRINT);

    }
}
