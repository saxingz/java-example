package org.saxing.hexagonal.eventlog;

import org.saxing.hexagonal.domain.PlayerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Standard output event log
 *
 * @author saxing 2019/1/27 22:51
 */
public class StdOutEventLog implements LotteryEventLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(StdOutEventLog.class);

    @Override
    public void ticketSubmitted(PlayerDetails details) {
        LOGGER.info("Lottery ticket for {} was submitted. Bank account {} was charged for 3 credits.",
                details.getEmail(), details.getBankAccount());
    }

    @Override
    public void ticketDidNotWin(PlayerDetails details) {
        LOGGER.info("Lottery ticket for {} was checked and unfortunately did not win this time.", details.getEmail());
    }

    @Override
    public void ticketWon(PlayerDetails details, int prizeAmount) {
        LOGGER.info("Lottery ticket for {} has won! The bank account {} was deposited with {} credits.",
                details.getEmail(), details.getBankAccount(), prizeAmount);
    }

    @Override
    public void prizeError(PlayerDetails details, int prizeAmount) {
        LOGGER.error("Lottery ticket for {} has won! Unfortunately the bank credit transfer of {} failed.",
                details.getEmail(), prizeAmount);
    }

    @Override
    public void ticketSubmitError(PlayerDetails details) {
        LOGGER.error("Lottery ticket for {} could not be submitted because the credit transfer of 3 credits failed.",
                details.getEmail());
    }
}
