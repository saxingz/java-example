package org.saxing.hexagonal.domain;

import com.google.inject.Inject;
import org.saxing.hexagonal.banking.WireTransfers;
import org.saxing.hexagonal.database.LotteryTicketRepository;
import org.saxing.hexagonal.eventlog.LotteryEventLog;

import java.util.Optional;

/**
 * Implementation for lottery service
 *
 * @author saxing 2019/1/30 13:48
 */
public class LotteryService {

    private final LotteryTicketRepository repository;
    private final LotteryEventLog notifications;
    private final WireTransfers wireTransfers;

    @Inject
    public LotteryService(LotteryTicketRepository repository, LotteryEventLog notifications, WireTransfers wireTransfers) {
        this.repository = repository;
        this.notifications = notifications;
        this.wireTransfers = wireTransfers;
    }

    /**
     * Submit lottery ticket to participate in the lottery
     *
     * @param ticket
     * @return
     */
    public Optional<LotteryTicketId> submitTicket(LotteryTicket ticket){
        boolean result = wireTransfers.transferFunds(LotteryConstants.TICKET_PRIZE,
                ticket.getPlayerDetails().getBankAccount(), LotteryConstants.SERVICE_BANK_ACCOUNT);
        if (!result){
            notifications.ticketSubmitError(ticket.getPlayerDetails());
            return Optional.empty();
        }
        Optional<LotteryTicketId> optional = repository.save(ticket);
        if (optional.isPresent()){
            notifications.ticketSubmitted(ticket.getPlayerDetails());
        }
        return optional;
    }

    /**
     * Check if lottery ticket has won
     *
     * @return
     */
    public LotteryTicketCheckResult checkTicketForPrize(LotteryTicketId id, LotteryNumbers winningNumbers){
        return LotteryUtils.checkTicketForPrize(repository, id, winningNumbers);
    }
}
