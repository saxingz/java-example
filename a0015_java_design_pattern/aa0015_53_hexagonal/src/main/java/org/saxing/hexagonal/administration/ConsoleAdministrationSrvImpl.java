package org.saxing.hexagonal.administration;

import org.saxing.hexagonal.domain.LotteryAdministration;
import org.slf4j.Logger;

/**
 * Console implementation for lottery administration
 *
 * @author saxing 2019/1/20 11:00
 */
public class ConsoleAdministrationSrvImpl implements ConsoleAdministrationSrv {

    private final LotteryAdministration administration;
    private final Logger logger;

    public ConsoleAdministrationSrvImpl(LotteryAdministration administration, Logger logger) {
        this.administration = administration;
        this.logger = logger;
    }

    @Override
    public void getAllSubmittedTickets() {

    }

    @Override
    public void performLottery() {

    }

    @Override
    public void resetLottery() {

    }
}
