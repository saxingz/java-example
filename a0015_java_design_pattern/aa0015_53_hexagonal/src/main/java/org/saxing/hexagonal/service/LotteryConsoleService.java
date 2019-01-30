package org.saxing.hexagonal.service;

import org.saxing.hexagonal.banking.WireTransfers;
import org.saxing.hexagonal.domain.LotteryService;

import java.util.Scanner;

/**
 * Console interface for lottery service
 *
 * @author saxing 2019/1/30 14:55
 */
public interface LotteryConsoleService {

    void checkTicket(LotteryService service, Scanner scanner);

    /**
     * Submit lottery ticket to participate in the lottery
     */
    void submitTicket(LotteryService service, Scanner scanner);

    /**
     * Add funds to lottery account
     */
    void addFundsToLotteryAccount(WireTransfers bank, Scanner scanner);


    /**
     * Recovery funds from lottery account
     */
    void queryLotteryAccountFunds(WireTransfers bank, Scanner scanner);

}
