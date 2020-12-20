package org.saxing.a0046_quartz.listener;

import org.quartz.listeners.SchedulerListenerSupport;

import java.time.LocalTime;

/**
 * MySchedulerListener
 *
 * @author saxing 2020/12/20 13:55
 */
public class MySchedulerListener extends SchedulerListenerSupport {

    @Override
    public void schedulerStarting() {
        super.schedulerStarting();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "  schedulerStarting ~!!!");
    }

    @Override
    public void schedulerShutdown() {
        super.schedulerShutdown();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "  schedulerShutdown ~!!!");
    }
}
