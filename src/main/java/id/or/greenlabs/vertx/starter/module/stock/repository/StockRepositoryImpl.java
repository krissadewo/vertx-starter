package id.or.greenlabs.vertx.starter.module.stock.repository;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import id.or.greenlabs.vertx.starter.common.CollectionName;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.document.Stock;
import id.or.greenlabs.vertx.starter.repository.GenericRepository;
import id.or.greenlabs.vertx.starter.repository.LookupOperationTemplate;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 1:33 PM
 */
public class StockRepositoryImpl extends GenericRepository implements StockRepository {

    @Inject
    public StockRepositoryImpl(@Named("mongoConfig") MongoConfig mongoConfig) {
        super(mongoConfig);
    }

    @Override
    public Mono<List<Stock>> save(List<Stock> documents) {
        return Mono.from(mongoConfig.getStockCollection().insertMany(documents))
            .flatMap(insertManyResult -> {
                if (insertManyResult.wasAcknowledged() && insertManyResult.getInsertedIds().size() == documents.size()) {
                    return Mono.just(documents);
                }

                return Mono.error(new DefaultException(StatusCode.OPERATION_FAILED));
            });
    }

    @Override
    public Flux<Stock> findBy(Stock stock, int limit, int offset) {
        return Flux.from(
            mongoConfig.getStockCollection()
                .aggregate(
                    Arrays.asList(
                        LookupOperationTemplate.lookupProductFromStock(),
                        LookupOperationTemplate.lookupOrderFromStock(),
                        Aggregates.addFields(new Field<Object>(CollectionName.PRODUCT, new Document("$arrayElemAt", Arrays.asList("$" + CollectionName.PRODUCT, 0)))),
                        Aggregates.addFields(new Field<Object>(CollectionName.ORDER, new Document("$arrayElemAt", Arrays.asList("$" + CollectionName.ORDER, 0)))),
                        Aggregates.skip(offset),
                        Aggregates.limit(limit))
                )
        );
    }
}
