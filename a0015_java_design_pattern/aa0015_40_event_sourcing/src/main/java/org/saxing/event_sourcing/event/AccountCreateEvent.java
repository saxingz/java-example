package org.saxing.event_sourcing.event;

import org.saxing.domain.Account;
import org.saxing.state.AccountAggregate;

/**
 * account create event
 *
 * @author saxing 2019/1/3 23:09
 */
public class AccountCreateEvent extends DomainEvent {

    private final int accountNo;
    private final String owner;

    public AccountCreateEvent(long sequenceId, long createdTime, int accountNo, String owner) {
        super(sequenceId, createdTime, "AccountCreateEvent");
        this.accountNo = accountNo;
        this.owner = owner;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public void process() {
        Account account = AccountAggregate.getAccount(accountNo);
        if (account != null){
            throw new RuntimeException("Account already exists");
        }
        account = new Account(accountNo, owner);
        account.handleEvent(this);
    }
}
