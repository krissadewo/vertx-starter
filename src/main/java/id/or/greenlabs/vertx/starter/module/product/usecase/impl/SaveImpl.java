package id.or.greenlabs.vertx.starter.module.product.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.vertx.starter.module.product.usecase.Save;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

/**
 * @author krissadewo
 * @date 1/31/22 12:58 PM
 */
public class SaveImpl implements Save {

    @Inject
    private ProductAdapter adapter;

    @Override
    public Mono<Product> execute(ProductDto dto) {
        return adapter.save(dto);
    }
}
