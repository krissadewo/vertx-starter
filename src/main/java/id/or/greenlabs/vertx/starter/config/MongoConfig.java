package id.or.greenlabs.vertx.starter.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Indexes;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import id.or.greenlabs.vertx.starter.common.CollectionName;
import id.or.greenlabs.vertx.starter.common.Environment;
import id.or.greenlabs.vertx.starter.context.ShareableContext;
import id.or.greenlabs.vertx.starter.document.Category;
import id.or.greenlabs.vertx.starter.document.Order;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.document.Stock;
import io.vertx.core.Vertx;
import lombok.Builder;
import lombok.Getter;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Arrays;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author krissadewo
 * @date 1/21/22 12:35 PM
 */
@Getter
public class MongoConfig extends ShareableContext<MongoConfig> {

    private MongoDatabase database;

    private final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    /**
     * @param vertx
     */
    @Builder
    public MongoConfig(Vertx vertx, Environment env) {
        super(vertx);

        buildClient(env);
    }

    private void buildClient(Environment env) {
        PojoCodecProvider codecProvider = PojoCodecProvider.builder()
            .conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION, Conventions.OBJECT_ID_GENERATORS))
            .automatic(true)
            .build();

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(codecProvider));

        MongoClient mongoClient = MongoClients.create(
            MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(env.getMongodb().get("uris")))
                .codecRegistry(codecRegistry)
                .build()
        );

        database = mongoClient.getDatabase("vertx-starter");
        database.withCodecRegistry(codecRegistry);

        registerProductIndex();
    }

    public MongoCollection<Category> getCategoryCollection() {
        return database.getCollection(CollectionName.CATEGORY, Category.class);
    }

    public MongoCollection<Product> getProductCollection() {
        return database.getCollection(CollectionName.PRODUCT, Product.class);
    }

    public MongoCollection<Order> getOrderCollection() {
        return database.getCollection(CollectionName.ORDER, Order.class);
    }

    public MongoCollection<Stock> getStockCollection() {
        return database.getCollection(CollectionName.STOCK, Stock.class);
    }

    private void registerProductIndex() {
        MongoCollection<Product> collection = getProductCollection();

        Flux.concat(
                collection.createIndex(Indexes.ascending("name"))
            )
            .subscribe();
    }

    @Override
    protected MongoConfig registerConfig() {
        return this;
    }
}

