import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by arpitjain
 * Date  01/12/19
 * Time  3:09 PM
 */
public class MongoUtils {


    public static MongoCollection<Document> getCollection(String collectionName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        final MongoDatabase database = mongoClient.getDatabase("entry_management");
        return database.getCollection(collectionName);
    }
}
