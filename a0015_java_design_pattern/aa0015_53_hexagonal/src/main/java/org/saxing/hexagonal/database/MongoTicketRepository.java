package org.saxing.hexagonal.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.saxing.hexagonal.domain.LotteryNumbers;
import org.saxing.hexagonal.domain.LotteryTicket;
import org.saxing.hexagonal.domain.LotteryTicketId;
import org.saxing.hexagonal.domain.PlayerDetails;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mongo lottery ticket database
 *
 * @author saxing 2019/1/29 23:06
 */
public class MongoTicketRepository implements LotteryTicketRepository {

    private static final String DEFAULT_DB = "lotteryDB";
    private static final String DEFAULT_TICKETS_COLLECTION = "lotteryTickets";
    private static final String DEFAULT_COUNTERS_COLLECTION = "counters";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> ticketsCollection;
    private MongoCollection<Document> countersCollection;

    public MongoTicketRepository() {
        connect();
    }

    public MongoTicketRepository(String dbName, String ticketsCollectionName,
                                 String countersCollectionName){
        connect(dbName, ticketsCollectionName, countersCollectionName);
    }

    public void connect(){
        connect(DEFAULT_DB, DEFAULT_TICKETS_COLLECTION, DEFAULT_COUNTERS_COLLECTION);
    }

    public void connect(String dbName, String ticketsCollectionName,
                        String countersCollectionName){
        if (mongoClient != null) {
            mongoClient.close();
        }
        mongoClient = new MongoClient(System.getProperty("mongo-host"),
                Integer.parseInt(System.getProperty("mongo-port")));
        database = mongoClient.getDatabase(dbName);
        ticketsCollection = database.getCollection(ticketsCollectionName);
        countersCollection = database.getCollection(countersCollectionName);
        if (countersCollection.count() <= 0) {
            initCounters();
        }
    }

    private void initCounters() {
        Document doc = new Document("_id", "ticketId").append("seq", 1);
        countersCollection.insertOne(doc);
    }

    /**
     * @return next ticket id
     */
    public int getNextId() {
        Document find = new Document("_id", "ticketId");
        Document increase = new Document("seq", 1);
        Document update = new Document("$inc", increase);
        Document result = countersCollection.findOneAndUpdate(find, update);
        return result.getInteger("seq");
    }

    /**
     *
     * @return tickets collection
     */
    public MongoCollection<Document> getTicketsCollection() {
        return ticketsCollection;
    }

    /**
     *
     * @return counters collection
     */
    public MongoCollection<Document> getCountersCollection() {
        return countersCollection;
    }

    @Override
    public Optional<LotteryTicket> findById(LotteryTicketId id) {
        return Optional.empty();
    }

    @Override
    public Optional<LotteryTicketId> save(LotteryTicket ticket) {
        return Optional.empty();
    }

    @Override
    public Map<LotteryTicketId, LotteryTicket> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    private LotteryTicket docToTicket(Document doc){
        PlayerDetails playerDetails = new PlayerDetails(doc.getString("email"), doc.getString("bank"),
                doc.getString("phone"));
        Set<Integer> numbers = Arrays.stream(doc.getString("numbers").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        LotteryNumbers lotteryNumbers = LotteryNumbers.create(numbers);
        return new LotteryTicket(new LotteryTicketId(doc.getInteger("ticketId")), playerDetails, lotteryNumbers);
    }
}
