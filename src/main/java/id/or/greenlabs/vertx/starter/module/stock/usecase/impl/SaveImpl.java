package id.or.greenlabs.vertx.starter.module.stock.usecase.impl;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.assembler.dto.StockDto;
import id.or.greenlabs.vertx.starter.document.Order;
import id.or.greenlabs.vertx.starter.module.stock.port.StockAdapter;
import id.or.greenlabs.vertx.starter.module.stock.usecase.Save;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 3:33 PM
 */
public class SaveImpl implements Save {

    @Inject
    private StockAdapter adapter;

    @Override
    public Mono<List<StockDto>> execute(List<OrderDto> dtos) {
        List<StockDto> stocks = new ArrayList<>();

        dtos.forEach(dto -> {
            StockDto stock = new StockDto();
            stock.setOrder(dto);
            stock.setProduct(dto.getProduct());

            if (dto.getType() == Order.Type.SELL) {
                stock.setQty(dto.getQty() * -1);
            } else {
                stock.setQty(dto.getQty());
            }

            stocks.add(stock);
        });

        return adapter.save(stocks);
    }
}
