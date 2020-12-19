package org.saxing.a0046_quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * my test job
 *
 * @author saxing 2020/12/19 17:59
 */
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();

        System.out.println("is doing... " + localTime.toString());
    }
}
