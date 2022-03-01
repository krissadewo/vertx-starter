package id.or.greenlabs.vertx.starter.module.product.repository;

import com.mongodb.client.result.UpdateResult;
import id.or.greenlabs.vertx.starter.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/24/22 9:51 AM
 */
public interface ProductRepository {

    Mono<Product> save(Product document);

    Mono<UpdateResult> delete(String id);

    Flux<Product> findBy(Product param, int limit, int offset);

    Mono<Product> findBy(String id);
}
