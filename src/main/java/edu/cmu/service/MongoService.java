/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu.service;

import edu.cmu.model.MongoDocument;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoService {
    private static final String URI = "mongodb://&authMechanism=SCRAM-SHA-1";

    /**
     * Inserts input document to the Mongo DB collection.
     *
     * @param document input document
     */
    public static void insertDocument(MongoDocument document) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase("dashboard");
            MongoCollection<Document> collection = database.getCollection("metric");
            Document doc = new Document("inputText", document.getInputText())
                    .append("source", document.getSource())
                    .append("target", document.getTarget())
                    .append("translatedText", document.getTranslatedText())
                    .append("translationSpeed", document.getTranslationSpeed())
                    .append("timeOfTranslation", document.getTimeOfTranslation());

            collection.insertOne(doc);
        }
    }

    /**
     * Retrieves all the documents in the MongoDB Collection.
     *
     * @return list of documents in the collection
     */
    public static List<Document> getDocuments() {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase("dashboard");
            MongoCollection<Document> collection = database.getCollection("metric");
            return collection.find().into(new ArrayList<>());
        }
    }

}
