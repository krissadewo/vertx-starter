package id.or.greenlabs.vertx.starter.module.product.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Product;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 1/31/22 3:03 PM
 */
public interface FindAll {

    Flux<Product> execute(ProductDto param, int limit, int offset);
}
