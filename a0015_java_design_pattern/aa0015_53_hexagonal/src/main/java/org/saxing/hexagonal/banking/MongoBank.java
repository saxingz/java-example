package org.saxing.hexagonal.banking;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Mongo based banking adapter
 *
 * @author saxing 2019/1/29 22:53
 */
public class MongoBank implements WireTransfers {

    private static final String DEFAULT_DB = "lotteryDB";
    private static final String DEFAULT_ACCOUNTS_COLLECTION = "accounts";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> accountsCollection;

    public MongoBank() {
        connect();
    }

    public MongoBank(String dbName, String accountsCollectionName) {
        connect(dbName, accountsCollectionName);
    }

    /**
     * Connect to database with default parameters
     */
    public void connect() {
        connect(DEFAULT_DB, DEFAULT_ACCOUNTS_COLLECTION);
    }

    /**
     * Connect to database with given parameters
     */
    public void connect(String dbName, String accountsCollectionName) {
        if (mongoClient != null) {
            mongoClient.close();
        }
        mongoClient = new MongoClient(System.getProperty("mongo-host"),
                Integer.parseInt(System.getProperty("mongo-port")));
        database = mongoClient.getDatabase(dbName);
        accountsCollection = database.getCollection(accountsCollectionName);
    }

    @Override
    public void setFunds(String bankAccount, int amount) {

    }

    @Override
    public int getFunds(String bankAccount) {
        return 0;
    }

    @Override
    public boolean transferFunds(int amount, String sourceBackAccount, String destinationBankAccount) {
        return false;
    }
}
