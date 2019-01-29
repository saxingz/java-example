package org.saxing.hexagonal.banking;

import java.util.HashMap;
import java.util.Map;

/**
 * Banking implementation
 *
 * @author saxing 2019/1/29 23:00
 */
public class InMemoryBank implements WireTransfers  {

    private static Map<String, Integer> accounts = new HashMap<>();

    static {
        accounts.put(LotteryConstants.SERVICE_BANK_ACCOUNT, LotteryConstants.SERVICE_BANK_ACCOUNT_BALANCE);
    }

    @Override
    public void setFunds(String bankAccount, int amount) {

    }

    @Override
    public int getFunds(String bankAccount) {
        return 0;
    }

    @Override
    public boolean transferFunds(int amount, String sourceBackAccount, String destinationBankAccount) {
        return false;
    }
}
