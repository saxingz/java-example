package org.saxing.hexagonal.administration;


/**
 * Console interface for lottery administration
 *
 * @author saxing 2019/1/20 10:59
 */
public interface ConsoleAdministrationSrv {

    /**
     * Get all submitted tickets
     */
    void getAllSubmittedTickets();

    /**
     * Draw lottery numbers
     */
    void performLottery();

    /**
     * Begin new lottery round
     */
    void resetLottery();

}
