package org.saxing.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

/**
 * The ConsoleLoggerModule is responsible for showing logs on System Console
 *  * <p>
 *  * The below example demonstrates a Console logger module, which can print simple and error messages
 *  * in two designated formats
 *
 *  @author saxing 2019/4/24 15:56
 */
public final class ConsoleLoggerModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleLoggerModule.class);

    private static ConsoleLoggerModule singleton = null;

    public PrintStream output = null;
    public PrintStream error = null;

    private ConsoleLoggerModule() {}

    /**
     * Static method to get single instance of class
     *
     * @return singleton instance of ConsoleLoggerModule
     */
    public static ConsoleLoggerModule getSingleton() {

        if (ConsoleLoggerModule.singleton == null) {
            ConsoleLoggerModule.singleton = new ConsoleLoggerModule();
        }

        return ConsoleLoggerModule.singleton;
    }

    /**
     * Following method performs the initialization
     */
    public ConsoleLoggerModule prepare() {

        LOGGER.debug("ConsoleLoggerModule::prepare();");

        this.output = new PrintStream(System.out);
        this.error = new PrintStream(System.err);

        return this;
    }

    /**
     * Following method performs the finalization
     */
    public void unprepare() {

        if (this.output != null) {

            this.output.flush();
            this.output.close();
        }

        if (this.error != null) {

            this.error.flush();
            this.error.close();
        }

        LOGGER.debug("ConsoleLoggerModule::unprepare();");
    }

    /**
     * Used to print a message
     *
     * @param value will be printed on console
     */
    public void printString(final String value) {
        this.output.println(value);
    }

    /**
     * Used to print a error message
     *
     * @param value will be printed on error console
     */
    public void printErrorString(final String value) {
        this.error.println(value);
    }



}
