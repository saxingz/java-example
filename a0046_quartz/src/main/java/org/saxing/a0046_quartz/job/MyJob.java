package org.saxing.a0046_quartz.job;

import lombok.Data;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * my test job
 *
 * @author saxing 2020/12/19 17:59
 */
@Data
public class MyJob implements Job {

    private String address;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");
        System.out.println("is doing... " + localTime.toString()
                + " name: " + name
                + " address: " + address
        );
    }
}
