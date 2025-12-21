package br.com.techchallenge.rating.repository.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientProvider {

    private static MongoClient client;

    private MongoClientProvider() {}

    public static MongoClient getClient() {
        if (client == null) {
            String uri = System.getenv("MONGODB_URI");
            if (uri == null || uri.isBlank()) {
                throw new IllegalStateException("Missing env var: MONGODB_URI");
            }
            client = MongoClients.create(uri);
        }
        return client;
    }
}
