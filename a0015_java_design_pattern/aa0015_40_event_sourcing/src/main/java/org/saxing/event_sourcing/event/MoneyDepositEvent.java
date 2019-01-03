package org.saxing.event_sourcing.event;

import org.saxing.event_sourcing.domain.Account;
import org.saxing.state.AccountAggregate;

import java.math.BigDecimal;

/**
 * money deposit event
 *
 * @author saxing 2019/1/3 23:26
 */
public class MoneyDepositEvent extends DomainEvent {

    private final BigDecimal money;
    private final int accountNo;

    public MoneyDepositEvent(long sequenceId, long createdTime, int accountNo, BigDecimal money) {
        super(sequenceId, createdTime, "MoneyDepositEvent");
        this.money = money;
        this.accountNo = accountNo;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * Gets account no.
     *
     * @return the account no
     */
    public int getAccountNo() {
        return accountNo;
    }

    @Override
    public void process() {
        Account account = AccountAggregate.getAccount(accountNo);
        if (account == null){
            throw new RuntimeException("Account not found");
        }
        account.handleEvent(this);
    }
}
