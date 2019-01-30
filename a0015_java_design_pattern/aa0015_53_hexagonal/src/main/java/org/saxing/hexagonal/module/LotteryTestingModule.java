package org.saxing.hexagonal.module;

import com.google.inject.AbstractModule;
import org.saxing.hexagonal.banking.InMemoryBank;
import org.saxing.hexagonal.banking.WireTransfers;
import org.saxing.hexagonal.database.InMemoryTicketRepository;
import org.saxing.hexagonal.database.LotteryTicketRepository;
import org.saxing.hexagonal.eventlog.LotteryEventLog;
import org.saxing.hexagonal.eventlog.StdOutEventLog;

/**
 * Guice module for testing dependencies
 *
 * @author saxing 2019/1/30 14:02
 */
public class LotteryTestingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LotteryTicketRepository.class).to(InMemoryTicketRepository.class);
        bind(LotteryEventLog.class).to(StdOutEventLog.class);
        bind(WireTransfers.class).to(InMemoryBank.class);
    }
}
