package org.saxing.domain;

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
        // todo
    }

}
