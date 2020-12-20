package org.saxing.a0046_quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.time.LocalTime;

/**
 * trigger listener
 *
 * @author saxing 2020/12/20 12:48
 */
public class MyTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "MyTriggerListener";
    }


    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        super.triggerFired(trigger, context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + " Trigger is fired!");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        super.triggerComplete(trigger, context, triggerInstructionCode);
        String instructionCode = triggerInstructionCode.toString();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + " triggerComplete !" + instructionCode);
    }
}
