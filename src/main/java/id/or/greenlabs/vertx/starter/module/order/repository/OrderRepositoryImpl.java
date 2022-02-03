package id.or.greenlabs.vertx.starter.module.order.repository;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import id.or.greenlabs.vertx.starter.common.CollectionName;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.document.Order;
import id.or.greenlabs.vertx.starter.repository.GenericRepository;
import id.or.greenlabs.vertx.starter.repository.LookupOperationTemplate;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;

/**
 * @author krissadewo
 * @date 2/3/22 10:47 AM
 */
public class OrderRepositoryImpl extends GenericRepository implements OrderRepository {

    @Inject
    public OrderRepositoryImpl(@Named("mongoConfig") MongoConfig mongoConfig) {
        super(mongoConfig);
    }

    @Override
    public Mono<Order> save(Order document) {
        return Mono.from(mongoConfig.getOrderCollection().insertOne(document))
            .flatMap(insertOneResult -> {
                if (insertOneResult.wasAcknowledged() && insertOneResult.getInsertedId() != null) {
                    document.setId(insertOneResult.getInsertedId().asObjectId().getValue());

                    return Mono.just(document);
                }

                return Mono.error(new DefaultException(StatusCode.OPERATION_FAILED));
            });
    }

    @Override
    public Flux<Order> find(Order param, int limit, int offset) {
        return Flux.from(
            mongoConfig.getOrderCollection()
                .aggregate(
                    Arrays.asList(
                        LookupOperationTemplate.lookupProductFromOrder(),
                        Aggregates.addFields(new Field<Object>(CollectionName.PRODUCT, new Document("$arrayElemAt", Arrays.asList("$" + CollectionName.PRODUCT, 0)))),
                        Aggregates.skip(offset),
                        Aggregates.limit(limit))
                )
        );
    }
}