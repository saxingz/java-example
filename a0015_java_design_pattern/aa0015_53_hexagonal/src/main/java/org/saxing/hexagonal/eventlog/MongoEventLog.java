package org.saxing.hexagonal.eventlog;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.saxing.hexagonal.domain.PlayerDetails;

/**
 * Mongo based event log
 *
 * @author saxing 2019/1/27 22:50
 */
public class MongoEventLog implements LotteryEventLog  {

    private static final String DEFAULT_DB = "lotteryDB";
    private static final String DEFAULT_EVENTS_COLLECTION = "events";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> eventsCollection;

    private StdOutEventLog stdOutEventLog = new StdOutEventLog();

    @Override
    public void ticketSubmitted(PlayerDetails details) {

    }

    @Override
    public void ticketSubmitError(PlayerDetails details) {

    }

    @Override
    public void ticketDidNotWin(PlayerDetails details) {

    }

    @Override
    public void ticketWon(PlayerDetails details, int prizeAmount) {

    }

    @Override
    public void prizeError(PlayerDetails details, int prizeAmount) {

    }
}
