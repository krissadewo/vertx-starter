package id.or.greenlabs.vertx.starter.module.product.usecase;

import id.or.greenlabs.vertx.starter.document.Product;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/31/22 2:48 PM
 */
public interface FindById {

    Mono<Product> execute(String id);
}
