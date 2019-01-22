package org.saxing.hexagonal.domain;

import org.saxing.hexagonal.database.LotteryTicketRepository;

import java.util.Optional;

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
        Optional<LotteryTicket> optional = repository.findById(id);
        if (optional.isPresent()){
            if (optional.get().getNumbers().equals(winningNumbers)){
                return new LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.WIN_PRIZE, 1000);
            } else {
                return new LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.NO_PRIZE);
            }
        } else {
            return new LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.TICKET_NOT_SUBMITTED);
        }
    }

}
