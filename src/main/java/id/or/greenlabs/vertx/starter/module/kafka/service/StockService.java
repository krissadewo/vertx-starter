package id.or.greenlabs.vertx.starter.module.kafka.service;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.common.codec.RequestParamCodec;
import id.or.greenlabs.vertx.starter.common.evb.EvbAddress;
import id.or.greenlabs.vertx.starter.common.evb.EvbCommand;
import id.or.greenlabs.vertx.starter.common.evb.Operation;
import id.or.greenlabs.vertx.starter.module.kafka.port.StockAdapter;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 3:16 PM
 */
public class StockService extends ApplicationService implements StockAdapter {

    @Inject
    public StockService(@Named("vertx") Vertx vertx) {
        super(vertx);
    }

    @Override
    public Mono<Object> save(List<OrderDto> dtos) {
        DeliveryOptions deliveryOptions = new DeliveryOptions().setCodecName(RequestParamCodec.class.getSimpleName());

        return Mono.fromCompletionStage(
                vertx.eventBus()
                    .request(EvbAddress.STOCK_SERVICE, new Operation(EvbCommand.UPDATE_STOCK, dtos), deliveryOptions)
                    .toCompletionStage())
            .flatMap(result -> {
                return Mono.just(result.body());
            });
    }
}
