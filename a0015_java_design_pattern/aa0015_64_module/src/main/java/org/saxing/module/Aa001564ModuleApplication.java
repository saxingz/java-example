package org.saxing.module;

import java.io.FileNotFoundException;

/**
 * main
 *
 * @author saxing 2019/4/24 16:04
 */
public class Aa001564ModuleApplication {

    public static FileLoggerModule fileLoggerModule;
    public static ConsoleLoggerModule consoleLoggerModule;

    public static void prepare() throws FileNotFoundException {
        fileLoggerModule = FileLoggerModule.getSingleton().prepare();
        consoleLoggerModule = ConsoleLoggerModule.getSingleton().prepare();
    }

    /**
     * Following method performs the finalization
     */
    public static void unprepare() {

        /* Close all resources */
        fileLoggerModule.unprepare();
        consoleLoggerModule.unprepare();
    }

    /**
     * Following method is main executor
     *
     * @param args for providing default program arguments
     */
    public static void execute(final String... args) {

        /* Send logs on file system */
        fileLoggerModule.printString("Message");
        fileLoggerModule.printErrorString("Error");

        /* Send logs on console */
        consoleLoggerModule.printString("Message");
        consoleLoggerModule.printErrorString("Error");
    }

    /**
     * Program entry point.
     *
     * @param args command line args.
     * @throws FileNotFoundException if program is not able to find log files (output.txt and
     *         error.txt)
     */
    public static void main(final String... args) throws FileNotFoundException {
        prepare();
        execute(args);
        unprepare();
    }

}
