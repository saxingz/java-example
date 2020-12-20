package org.saxing.a0046_quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.saxing.a0046_quartz.job.MyJob;
import org.saxing.a0046_quartz.listener.MyTriggerListener;

import java.util.Random;

/**
 * quartz demo
 *
 * @author saxing 2020/12/19 17:58
 */
@DisallowConcurrentExecution
public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        int count = new Random().nextInt(10);

        JobDetail jobDetail1 = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("jobDetail1", "group1")
                .usingJobData("name", "saxing")
                .usingJobData("address", "my-address")
                .usingJobData("count", count)
                .build();
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        JobDetail jobDetail2 = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("jobDetail2", "group1")
                .usingJobData("name", "saxing")
                .usingJobData("address", "my-address")
                .usingJobData("count", count)
                .build();
        Trigger trigger2 = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                        .withMisfireHandlingInstructionFireAndProceed())
                .build();
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);
        Thread.sleep(60000);
        scheduler.shutdown();
    }

}
