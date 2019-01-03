package org.saxing.state;

import org.saxing.event_sourcing.domain.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * account aggregate
 *
 * @author saxing 2019/1/3 23:16
 */
public class AccountAggregate {

    private static Map<Integer, Account> accounts = new HashMap<>();

    private AccountAggregate() {
    }

    public static void putAccount(Account account){
        accounts.put(account.getAccountNo(), account);
    }

    public static Account getAccount(int accountNo){
        Account account = accounts.get(accountNo);
        if (account == null){
            return null;
        }
        return account.copy();
    }

    public static void resetState(){
        accounts = new HashMap<>();
    }
}
