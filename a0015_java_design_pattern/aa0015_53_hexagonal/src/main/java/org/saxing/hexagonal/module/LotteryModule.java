package org.saxing.hexagonal.module;

import com.google.inject.AbstractModule;
import org.saxing.hexagonal.banking.MongoBank;
import org.saxing.hexagonal.banking.WireTransfers;
import org.saxing.hexagonal.database.LotteryTicketRepository;
import org.saxing.hexagonal.database.MongoTicketRepository;
import org.saxing.hexagonal.eventlog.LotteryEventLog;
import org.saxing.hexagonal.eventlog.MongoEventLog;

/**
 * Guice module for binding production dependencies
 *
 * @author saxing 2019/1/29 23:05
 */
public class LotteryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LotteryTicketRepository.class).to(MongoTicketRepository.class);
        bind(LotteryEventLog.class).to(MongoEventLog.class);
        bind(WireTransfers.class).to(MongoBank.class);
    }
}
