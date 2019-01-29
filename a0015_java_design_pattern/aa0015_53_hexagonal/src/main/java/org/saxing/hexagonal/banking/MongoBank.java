package org.saxing.hexagonal.banking;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

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
    public MongoCollection<Document> getAccountsCollection() {
        return accountsCollection;
    }

    @Override
    public void setFunds(String bankAccount, int amount) {
        Document search = new Document("_id", bankAccount);
        Document update = new Document("_id", bankAccount).append("funds", amount);
        accountsCollection.updateOne(search, new Document("$set", update), new UpdateOptions().upsert(true));
    }

    @Override
    public int getFunds(String bankAccount) {
        Document search = new Document("_id", bankAccount);
        List<Document> results = accountsCollection.find(search).limit(1).into(new ArrayList<>());
        if (results.size() > 0) {
            return results.get(0).getInteger("funds");
        } else {
            return 0;
        }
    }

    @Override
    public boolean transferFunds(int amount, String sourceBackAccount, String destinationBankAccount) {
        int sourceFunds = getFunds(sourceBackAccount);
        if (sourceFunds < amount) {
            return false;
        } else {
            int destFunds = getFunds(destinationBankAccount);
            setFunds(sourceBackAccount, sourceFunds - amount);
            setFunds(destinationBankAccount, destFunds + amount);
            return true;
        }
    }
}
