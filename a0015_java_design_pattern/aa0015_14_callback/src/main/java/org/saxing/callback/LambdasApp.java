package org.saxing.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example generates the exact same output as {@link Aa001514CallbackApplication} however the callback has been
 *  * defined as a Lambdas expression.
 *
 *  @author saxing  2018/11/17 11:29
 */
public class LambdasApp {

    private static final Logger LOGOGER = LoggerFactory.getLogger(LambdasApp.class);

    public static void main(String[] args) {
        Task task = new SimpleTask();
        Callback c = () -> LOGOGER.info("I'm done now.");
        task.executeWith(c);
    }

}
