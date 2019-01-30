package org.saxing.hexagonal.domain;

import org.saxing.hexagonal.banking.WireTransfers;
import org.saxing.hexagonal.database.LotteryTicketRepository;
import org.saxing.hexagonal.eventlog.LotteryEventLog;

import java.util.Map;

/**
 * Lottery administration implementation
 *
 * @author saxing 2019/1/30 13:37
 */
public class LotteryAdministration {

    private final LotteryTicketRepository repository;
    private final LotteryEventLog notifications;
    private final WireTransfers wireTransfers;

    public LotteryAdministration(LotteryTicketRepository repository, LotteryEventLog notifications,
                                 WireTransfers wireTransfers) {
        this.repository = repository;
        this.notifications = notifications;
        this.wireTransfers = wireTransfers;
    }

    public Map<LotteryTicketId, LotteryTicket> getAllSubmittedTickets(){
        return repository.findAll();
    }

    /**
     * Draw lottery numbers
     *
     * @return
     */
    public LotteryNumbers performLottery(){
        LotteryNumbers numbers = LotteryNumbers.createRandom();
        Map<LotteryTicketId, LotteryTicket> tickets = getAllSubmittedTickets();
        for (LotteryTicketId id : tickets.keySet()){
            LotteryTicketCheckResult result = LotteryUtils.checkTicketForPrize(repository, id, numbers);
            if (result.getResult().equals(LotteryTicketCheckResult.CheckResult.WIN_PRIZE)) {
                boolean transferred = wireTransfers.transferFunds(LotteryConstants.PRIZE_AMOUNT,
                        LotteryConstants.SERVICE_BANK_ACCOUNT, tickets.get(id).getPlayerDetails().getBankAccount());
                if (transferred){
                    notifications.ticketWon(tickets.get(id).getPlayerDetails(), LotteryConstants.PRIZE_AMOUNT);
                } else {
                    notifications.prizeError(tickets.get(id).getPlayerDetails(), LotteryConstants.PRIZE_AMOUNT);
                }
            }else if (result.getResult().equals(LotteryTicketCheckResult.CheckResult.NO_PRIZE)) {
                notifications.ticketDidNotWin(tickets.get(id).getPlayerDetails());
            }
        }
        return numbers;
    }
}
