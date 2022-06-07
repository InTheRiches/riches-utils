package net.projektcontingency.titanium.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.projektcontingency.titanium.internal.Pair;
import org.bson.Document;
import org.bson.UuidRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public final MongoClient mongoClient;

    public Database() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    public Object getFirstDocumentValue(Pair<String, Object> f, String collection, String key) {
        MongoDatabase database = mongoClient.getDatabase("player-data");
        MongoCollection<Document> col = database.getCollection(collection);

        Document filter = new Document(f.getFirst(), f.getSecond());
        return col.find(filter).first().get(key);
    }
}
