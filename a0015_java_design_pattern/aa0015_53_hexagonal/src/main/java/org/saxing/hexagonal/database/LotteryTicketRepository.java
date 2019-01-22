package org.saxing.hexagonal.database;

import org.saxing.hexagonal.domain.LotteryTicket;
import org.saxing.hexagonal.domain.LotteryTicketId;

import java.util.Map;
import java.util.Optional;

/**
 * Interface for accessing lottery tickets in database.
 *
 * @author saxing 2019/1/20 11:02
 */
public interface LotteryTicketRepository {


    /**
     * Find lottery ticket by id
     */
    Optional<LotteryTicket> findById(LotteryTicketId id);

    /**
     * Save lottery ticket
     */
    Optional<LotteryTicketId> save(LotteryTicket ticket);

    /**
     * Get all lottery tickets
     */
    Map<LotteryTicketId, LotteryTicket> findAll();

    /**
     * Delete all lottery tickets
     */
    void deleteAll();

}
