package id.or.greenlabs.vertx.starter.module.product.usecase;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Product;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 1/31/22 12:57 PM
 */
public interface Save {

    Mono<ProductDto> execute(ProductDto dto);
}
