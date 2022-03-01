package id.or.greenlabs.vertx.starter.module.category.repository;

import id.or.greenlabs.vertx.starter.document.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/21/22 12:53 PM
 */
public interface CategoryRepository {

    Mono<Category> save(Category document);

    Flux<Category> findBy(Category param, int limit, int offset);

    Mono<Category> findBy(String id);
}
