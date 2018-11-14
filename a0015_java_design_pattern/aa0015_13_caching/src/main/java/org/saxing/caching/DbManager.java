package org.saxing.caching;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * DbManager
 *
 *  * <p>DBManager handles the communication with the underlying data store i.e. Database. It contains the
 *  * implemented methods for querying, inserting, and updating data. MongoDB was used as the database
 *  * for the application.</p>
 *  *
 *  * <p>Developer/Tester is able to choose whether the application should use MongoDB as its underlying
 *  * data storage (connect()) or a simple Java data structure to (temporarily) store the data/objects
 *  * during runtime (createVirtualDB()).</p>
 * 
 * @author saxing  2018/11/14 9:05
 */
public final class DbManager {

    private static MongoClient mongoClient;
    private static MongoDatabase db;
    private static boolean userMongoDB;

    private static Map<String, UserAccount> virtualDB;

    public DbManager() {
    }

    /**
     * Create DB
     */
    public static void createVirtualDb(){
        userMongoDB = false;
        virtualDB = new HashMap<>();
    }

    /**
     * Connect to DB
     *
     * @throws ParseException
     */
    public static void connect() throws ParseException{
        userMongoDB = false;
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("test");
    }

    /**
     * Read user account from DB
     *
     * @param userId
     * @return
     */
    public static UserAccount readFromDb(String userId){
        if (!userMongoDB){
            if (virtualDB.containsKey(userId)){
                return virtualDB.get(userId);
            }
            return null;
        }
        if (db == null){
            try {
                connect();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        FindIterable<Document> iterable =
                db.getCollection("user_accounts").find(new Document("userId", userId));
        if (iterable == null){
            return null;
        }
        Document doc = iterable.first();
        return new UserAccount(userId, doc.getString("userName"), doc.getString("additionalInfo"));
    }

    public static void writeToDb(UserAccount userAccount){
        if (!userMongoDB){
            virtualDB.put(userAccount.getUserId(), userAccount);
            return;
        }
        if (db == null){
            try {
                connect();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        db.getCollection("user_accounts").insertOne(
                new Document("userID", userAccount.getUserId()).append("userName", userAccount.getUserName())
                .append("additionalInfo", userAccount.getAdditionalInfo()));
    }

}
