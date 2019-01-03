package org.saxing.event_sourcing.domain;

import org.saxing.event_sourcing.event.AccountCreateEvent;
import org.saxing.event_sourcing.event.MoneyDepositEvent;
import org.saxing.event_sourcing.event.MoneyTransferEvent;
import org.saxing.state.AccountAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * account
 *
 * @author saxing 2019/1/3 23:12
 */
public class Account {

    private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

    private final int accountNo;
    private final String owner;
    private BigDecimal money;

    /**
     * constructor
     *
     * @param accountNo
     * @param owner
     */
    public Account(int accountNo, String owner) {
        this.accountNo = accountNo;
        this.owner = owner;
        money = BigDecimal.ZERO;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * copy
     *
     * @return
     */
    public Account copy(){
        Account account = new Account(accountNo, owner);
        account.setMoney(money);
        return account;
    }


    @Override
    public String toString() {
        return "Account{"
                + "accountNo=" + accountNo
                + ", owner='" + owner + '\''
                + ", money=" + money
                + '}';
    }

    private void depositMoney(BigDecimal money){
        this.money =  this.money.add(money);
    }

    private void withdrawMoney(BigDecimal money) {
        this.money = this.money.subtract(money);
    }

    private void handleDeposit(BigDecimal money, boolean realTime){
        depositMoney(money);
        AccountAggregate.putAccount(this);
        if (realTime){
            LOGGER.info("Some external api for only realtime execution could be called here.");
        }
    }

    private void handleWithdrawal(BigDecimal money, boolean realTime){
        if (this.money.compareTo(money) == -1){
            throw new RuntimeException("Insufficient Account Balance");
        }
        withdrawMoney(money);
        AccountAggregate.putAccount(this);
        if (realTime){
            LOGGER.info("Some external api for only realtime execution could be called here.");
        }
    }

    public void handleEvent(AccountCreateEvent accountCreateEvent){
        AccountAggregate.putAccount(this);
        if (accountCreateEvent.isRealTime()){
            LOGGER.info("Some external api for only realtime execution could be called here.");
        }
    }

    public void handleEvent(MoneyDepositEvent moneyDepositEvent){
        handleDeposit(moneyDepositEvent.getMoney(), moneyDepositEvent.isRealTime());
    }

    public void handleTransferFromEvent(MoneyTransferEvent moneyTransferEvent){
        handleWithdrawal(moneyTransferEvent.getMoney(), moneyTransferEvent.isRealTime());
    }

    public void handleTransferToEvent(MoneyTransferEvent moneyTransferEvent) {
        handleDeposit(moneyTransferEvent.getMoney(), moneyTransferEvent.isRealTime());
    }

}
























