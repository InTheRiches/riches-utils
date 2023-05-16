package net.projektcontingency.titanium.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.projektcontingency.titanium.Titanium;
import net.projektcontingency.titanium.internal.Pair;
import org.bson.Document;
import org.bson.UuidRepresentation;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Database {
    public MongoClient mongoClient;

    public Database() {

    }

    public void connect() {
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

    public String getFirstDocument(Pair<String, Object> f, String collection) {
        MongoDatabase database = mongoClient.getDatabase("player-data");
        MongoCollection<Document> col = database.getCollection(collection);

        Document filter = new Document(f.getFirst(), f.getSecond());
        Document result = col.find(filter).first();
        if (result == null) {
            return new Document(f.getFirst(), f.getSecond()).toJson();
        }
        return result.toJson();
    }

    public List<String> getDocuments(Pair<String, Object> f, String collection) {
        MongoDatabase database = mongoClient.getDatabase("player-data");
        MongoCollection<Document> col = database.getCollection(collection);

        Document filter = new Document(f.getFirst(), f.getSecond());
        List<Document> documents = col.find(filter).into(new ArrayList<>());
        List<String> documentJson = new ArrayList<>();
        documents.forEach(document -> documentJson.add(document.toJson()));

        return documentJson;
    }

    public List<String> getDocuments(String collection) {
        MongoDatabase database = mongoClient.getDatabase("player-data");
        MongoCollection<Document> col = database.getCollection(collection);

        List<Document> documents = col.find().into(new ArrayList<>());
        List<String> documentJson = new ArrayList<>();
        documents.forEach(document -> documentJson.add(document.toJson()));

        return documentJson;
    }

    public void setFirstDocumentValue(Pair<String, Object> f, String collection, String key, Object value) {
        MongoDatabase database = mongoClient.getDatabase("player-data");
        MongoCollection<Document> col = database.getCollection(collection);

        Document filter = new Document(f.getFirst(), f.getSecond());
        Document doc = col.find(filter).first();
        doc.append(key, value);
        col.findOneAndReplace(filter, doc);
    }

    public void replaceDocument(Pair<String, Object> f, String collection, String json) {
        MongoDatabase database = mongoClient.getDatabase("player-data");
        MongoCollection<Document> col = database.getCollection(collection);

        Document filter = new Document(f.getFirst(), f.getSecond());
        if (col.find(filter).first() == null) {
            col.insertOne(Document.parse(json));
            return;
        }
        col.findOneAndReplace(filter, Document.parse(json));
    }
}
