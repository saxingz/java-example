package org.saxing.a0046_quartz.job;

import lombok.Data;
import org.quartz.*;

import java.time.LocalTime;

/**
 * my test job
 *
 * @author saxing 2020/12/19 17:59
 */
@Data
@PersistJobDataAfterExecution
public class MyJob implements Job {

    private String address;

    private int count;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");

        count++;
        jobExecutionContext.getJobDetail().getJobDataMap().put("count", count);
        System.out.println("is doing... " + localTime.toString()
                + " name: " + name
                + " address: " + address
                + " count: " + count
        );
    }
}
