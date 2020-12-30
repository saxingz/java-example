package org.saxing.a0046_quartz.config;


import org.quartz.*;
import org.saxing.a0046_quartz.job.MyBootJob;
import org.saxing.a0046_quartz.listener.MyTriggerListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quartz config
 *
 * @author saxing 2020/12/20 12:06
 */
@Configuration
public class QuartzConfig {


    @Bean
    public JobDetail myBootJobDetail() {
        return JobBuilder.newJob(MyBootJob.class)
                .withIdentity("mybootjob1", "group1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myBootTrigger() {
        return TriggerBuilder.newTrigger()
                .startNow().withIdentity("trigger1", "group1")
                .forJob(myBootJobDetail())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5).repeatForever())
                .build();
    }

    @Bean
    public TriggerListener myTriggerListener() {
        return new MyTriggerListener();
    }

}
