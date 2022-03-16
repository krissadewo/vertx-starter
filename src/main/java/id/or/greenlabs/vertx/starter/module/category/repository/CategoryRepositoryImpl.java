package id.or.greenlabs.vertx.starter.module.category.repository;

import com.mongodb.client.model.Filters;
import id.or.greenlabs.vertx.starter.common.DefaultException;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.config.MongoConfig;
import id.or.greenlabs.vertx.starter.document.Category;
import id.or.greenlabs.vertx.starter.repository.GenericRepository;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author krissadewo
 * @date 1/21/22 12:54 PM
 */
public class CategoryRepositoryImpl extends GenericRepository implements CategoryRepository {

    @Inject
    public CategoryRepositoryImpl(@Named("mongoConfig") MongoConfig mongoConfig) {
        super(mongoConfig);
    }

    @Override
    public Mono<Category> save(Category document) {
        return Mono.from(mongoConfig.getCategoryCollection().insertOne(document))
            .flatMap(result -> {
                if (result.wasAcknowledged() && result.getInsertedId() != null) {
                    document.setId(result.getInsertedId().asObjectId().getValue());

                    return Mono.just(document);
                }

                return Mono.error(new DefaultException(StatusCode.OPERATION_FAILED));
            });
    }

    @Override
    public Flux<Category> findBy(Category param, int limit, int offset) {
        return Flux.from(
            mongoConfig.getCategoryCollection()
                .find()
                .filter(
                    Filters.and(
                        Filters.ne("name", param.getName()),
                        Filters.eq("delete", false))
                )
                .skip(offset)
                .limit(limit)
        );
    }

    @Override
    public Mono<Category> findBy(String id) {
        return Mono.from(mongoConfig.getCategoryCollection()
            .find(Filters.eq("_id", new ObjectId(id)))
        );
    }
}
