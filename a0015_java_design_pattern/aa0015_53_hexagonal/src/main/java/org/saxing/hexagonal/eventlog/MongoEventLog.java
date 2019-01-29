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

    public MongoEventLog() {
        connect();
    }

    /**
     * Constructor accepting parameters
     */
    public MongoEventLog(String dbName, String eventsCollectionName) {
        connect(dbName, eventsCollectionName);
    }

    @Override
    public void ticketSubmitted(PlayerDetails details) {
        Document document = new Document("email", details.getEmail());
        document.put("phone", details.getPhoneNumber());
        document.put("bank", details.getBankAccount());
        document.put("message", "Lottery ticket was submitted and bank account was charged for 3 credits.");
        eventsCollection.insertOne(document);
        stdOutEventLog.ticketSubmitted(details);
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

    private void connect() {
        connect(DEFAULT_DB, DEFAULT_EVENTS_COLLECTION);
    }

    private void connect(String dbName, String eventsCollectionName) {
        if (mongoClient != null){
            mongoClient.close();
        }
        mongoClient = new MongoClient(System.getProperty("mongo-host"),
                Integer.parseInt(System.getProperty("mongo-port")));
        database = mongoClient.getDatabase(dbName);
        eventsCollection = database.getCollection(eventsCollectionName);
    }


    /**
     * @return mongo client
     */
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    /**
     *
     * @return mongo database
     */
    public MongoDatabase getMongoDatabase() {
        return database;
    }

    /**
     *
     * @return accounts collection
     */
    public MongoCollection<Document> getEventsCollection() {
        return eventsCollection;
    }

}
