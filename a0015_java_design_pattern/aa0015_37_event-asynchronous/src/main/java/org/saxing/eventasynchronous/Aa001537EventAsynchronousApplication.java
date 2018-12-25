package org.saxing.eventasynchronous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * main
 *
 * @author saxing 2018/12/25 22:23
 */
public class Aa001537EventAsynchronousApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001537EventAsynchronousApplication.class);

    public static final String PROP_FILE_NAME = "application.properties";

    boolean interactiveMode = false;

    public static void main(String[] args) {
        Aa001537EventAsynchronousApplication app = new Aa001537EventAsynchronousApplication();

        app.setUp();
        app.run();
    }

    /**
     * setup
     */
    public void setUp(){
        Properties prop = new Properties();
        InputStream inputStream = Aa001537EventAsynchronousApplication.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);

        if (inputStream != null){
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                LOGGER.error("{} was not found. Defaulting to non-interactive mode.", PROP_FILE_NAME, e);
            }
            String property = prop.getProperty("INTERACTIVE_MODE");
            if (property.equalsIgnoreCase("YES")){
                interactiveMode = true;
            }
        }
    }

    public void run(){
        if (interactiveMode){
            runInteractiveMode();
        }else{
            quickRun();
        }
    }

    /**
     * Run program in non-interactive mode.
     */
    public void quickRun() {
        EventManager eventManager = new EventManager();

        try {
            // Create an Asynchronous event.
            int aEventId = eventManager.createAsync(60);
            LOGGER.info("Async Event [{}] has been created.", aEventId);
            eventManager.start(aEventId);
            LOGGER.info("Async Event [{}] has been started.", aEventId);

            // Create a Synchronous event.
            int sEventId = eventManager.create(60);
            LOGGER.info("Sync Event [{}] has been created.", sEventId);
            eventManager.start(sEventId);
            LOGGER.info("Sync Event [{}] has been started.", sEventId);

            eventManager.status(aEventId);
            eventManager.status(sEventId);

            eventManager.cancel(aEventId);
            LOGGER.info("Async Event [{}] has been stopped.", aEventId);
            eventManager.cancel(sEventId);
            LOGGER.info("Sync Event [{}] has been stopped.", sEventId);


        }catch (MaxNumOfEventsAllowedException | LongRunningEventException | EventDoesNotExistException
                | InvalidOperationException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Run program in interactive mode.
     */
    public void runInteractiveMode() {
        EventManager eventManager = new EventManager();

        Scanner s = new Scanner(System.in);
        int option = -1;
        while (option != 4) {
            LOGGER.info("Hello. Would you like to boil some eggs?");
            LOGGER.info("(1) BOIL AN EGG \n(2) STOP BOILING THIS EGG \n(3) HOW ARE MY EGGS? \n(4) EXIT");
            LOGGER.info("Choose [1,2,3,4]: ");
            option = s.nextInt();

            if (option == 1){
                s.nextLine();
                LOGGER.info("Boil multiple eggs at once (A) or boil them one-by-one (S)?: ");
                String eventType = s.nextLine();
                LOGGER.info("How long should this egg be boiled for (in seconds)?: ");
                int eventTime = s.nextInt();
                if (eventType.equalsIgnoreCase("A")) {
                    try {
                        int eventId = eventManager.createAsync(eventTime);
                        eventManager.start(eventId);
                        LOGGER.info("Egg [{}] is being boiled.", eventId);
                    } catch (MaxNumOfEventsAllowedException | LongRunningEventException | EventDoesNotExistException e) {
                        LOGGER.error(e.getMessage());
                    }
                } else if (eventType.equalsIgnoreCase("S")) {
                    try {
                        int eventId = eventManager.create(eventTime);
                        eventManager.start(eventId);
                        LOGGER.info("Egg [{}] is being boiled.", eventId);
                    } catch (MaxNumOfEventsAllowedException | InvalidOperationException | LongRunningEventException
                            | EventDoesNotExistException e) {
                        LOGGER.error(e.getMessage());
                    }
                } else {
                    LOGGER.info("Unknown event type.");
                }
            } else if (option == 2) {
                LOGGER.info("Which egg?: ");
                int eventId = s.nextInt();
                try {
                    eventManager.cancel(eventId);
                    LOGGER.info("Egg [{}] is removed from boiler.", eventId);
                } catch (EventDoesNotExistException e) {
                    LOGGER.error(e.getMessage());
                }
            } else if (option == 3) {
                s.nextLine();
                LOGGER.info("Just one egg (O) OR all of them (A) ?: ");
                String eggChoice = s.nextLine();

                if (eggChoice.equalsIgnoreCase("O")) {
                    LOGGER.info("Which egg?: ");
                    int eventId = s.nextInt();
                    try {
                        eventManager.status(eventId);
                    } catch (EventDoesNotExistException e) {
                        LOGGER.error(e.getMessage());
                    }
                } else if (eggChoice.equalsIgnoreCase("A")) {
                    eventManager.statusOfAllEvents();
                }
            } else if (option == 4) {
                eventManager.shutdown();
            }
        }

        s.close();

    }


}

