package id.or.greenlabs.vertx.starter.module.product.repository;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import id.or.greenlabs.vertx.starter.common.CollectionName;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.repository.GenericRepository;
import id.or.greenlabs.vertx.starter.repository.LookupOperationTemplate;
import org.bson.Document;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;

/**
 * @author krissadewo
 * @date 1/24/22 9:52 AM
 */
public class ProductRepositoryImpl extends GenericRepository implements ProductRepository {

    @Inject
    public ProductRepositoryImpl(@Named("mongoConfig") MongoConfig mongoConfig) {
        super(mongoConfig);
    }

    @Override
    public Mono<Product> save(Product document) {
        return Mono.from(mongoConfig.getProductCollection().insertOne(document))
            .flatMap(insertOneResult -> {
                if (insertOneResult.wasAcknowledged() && insertOneResult.getInsertedId() != null) {
                    document.setId(insertOneResult.getInsertedId().asObjectId().getValue());

                    return Mono.just(document);
                }

                return Mono.error(new DefaultException(StatusCode.OPERATION_FAILED));
            });
    }

    @Override
    public Mono<UpdateResult> delete(String id) {
        return Mono.from(
            mongoConfig.getProductCollection()
                .updateOne(Filters.eq("_id", new ObjectId(id)), Updates.combine(Updates.set("delete", true)))
        );
    }

    @Override
    public Flux<Product> findBy(Product param, int limit, int offset) {
        return Flux.from(
            mongoConfig.getProductCollection()
                .aggregate(
                    Arrays.asList(
                        LookupOperationTemplate.lookupCategoryFromProduct(),
                        Aggregates.addFields(new Field<Object>(CollectionName.CATEGORY, new Document("$arrayElemAt", Arrays.asList("$" + CollectionName.CATEGORY, 0)))),
                        Aggregates.skip(offset),
                        Aggregates.limit(limit))
                )
        );
    }

    @Override
    public Mono<Product> findBy(String id) {
        return Mono.from(mongoConfig.getProductCollection()
            .find(Filters.eq("_id", new ObjectId(id)))
        );
    }
}
