package org.saxing.event_sourcing;

import org.saxing.event_sourcing.event.AccountCreateEvent;
import org.saxing.event_sourcing.event.MoneyDepositEvent;
import org.saxing.event_sourcing.event.MoneyTransferEvent;
import org.saxing.event_sourcing.processor.DomainEventProcessor;
import org.saxing.state.AccountAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

/**
 * main
 *
 * @author saxing 2019/1/3 23:43
 */
public class Aa001540EventSourcingApplication {


    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001540EventSourcingApplication.class);
    /**
     * The constant ACCOUNT OF DAENERYS.
     */
    public static final int ACCOUNT_OF_DAENERYS = 1;
    /**
     * The constant ACCOUNT OF JON.
     */
    public static final int ACCOUNT_OF_JON = 2;

    public static void main(String[] args) {
        DomainEventProcessor eventProcessor = new DomainEventProcessor();

        LOGGER.info("Running the system first time............");
        eventProcessor.reset();

        LOGGER.info("Creating the accounts............");

        eventProcessor.process(new AccountCreateEvent(
                0, new Date().getTime(), ACCOUNT_OF_DAENERYS, "Daenerys Targaryen"));


        eventProcessor.process(new AccountCreateEvent(
                1, new Date().getTime(), ACCOUNT_OF_JON, "Jon Snow"));


        LOGGER.info("Do some money operations............");

        eventProcessor.process(new MoneyDepositEvent(
                2, new Date().getTime(),  new BigDecimal("100000"), ACCOUNT_OF_DAENERYS));

        eventProcessor.process(new MoneyDepositEvent(
                3, new Date().getTime(), new BigDecimal("100"), ACCOUNT_OF_JON));

        eventProcessor.process(new MoneyTransferEvent(
                4, new Date().getTime(), new BigDecimal("10000"), ACCOUNT_OF_DAENERYS,
                ACCOUNT_OF_JON));

        LOGGER.info("...............State:............");
        LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString());
        LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString());

        LOGGER.info("At that point system had a shut down, state in memory is cleared............");
        AccountAggregate.resetState();

        LOGGER.info("Recover the system by the events in journal file............");

        eventProcessor = new DomainEventProcessor();
        eventProcessor.recover();

        LOGGER.info("...............Recovered State:............");
        LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString());
        LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString());

    }

}

