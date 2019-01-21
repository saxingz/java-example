package org.saxing.hexagonal.domain;

import org.saxing.hexagonal.database.LotteryTicketRepository;

/**
 * Lottery utilities
 *
 * @author saxing 2019/1/21 23:48
 */
public class LotteryUtils {

    private LotteryUtils() {
    }

    public static LotteryTicketCheckResult checkTicketForPrize(LotteryTicketRepository repository, LotteryTicketId id,
                                                               LotteryNumbers winningNumbers){

    }

}
