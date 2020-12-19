package org.saxing.a0046_quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.saxing.a0046_quartz.job.MyJob;

/**
 * quartz demo
 *
 * @author saxing 2020/12/19 17:58
 */
public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("jobDetail1", "group1")
                .usingJobData("name", "saxing")
                .usingJobData("address", "my-address")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(60000);
        scheduler.shutdown();
    }

}
