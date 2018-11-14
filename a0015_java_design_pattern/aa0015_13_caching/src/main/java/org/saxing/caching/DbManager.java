package org.saxing.caching;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

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
    private static MongoDatabase mongoDatabase;
    private static boolean userMongoDB;



}
