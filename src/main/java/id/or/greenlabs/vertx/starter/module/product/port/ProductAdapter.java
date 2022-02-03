package id.or.greenlabs.vertx.starter.module.product.port;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/31/22 12:50 PM
 */
public interface ProductAdapter {

    Mono<ProductDto> save(ProductDto dto);

    Mono<ProductDto> find(String id);

    Mono<String> delete(String id);

    Flux<ProductDto> find(ProductDto param, int limit, int offset);
}
