package id.or.greenlabs.vertx.starter.module.order.service;

import com.google.inject.Inject;
import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.common.codec.GenericCodec;
import id.or.greenlabs.vertx.starter.common.evb.EvbAddress;
import id.or.greenlabs.vertx.starter.common.evb.EvbCommand;
import id.or.greenlabs.vertx.starter.common.evb.Operation;
import id.or.greenlabs.vertx.starter.module.order.port.KafkaAdapter;
import id.or.greenlabs.vertx.starter.service.ApplicationService;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import reactor.core.publisher.Mono;

import javax.inject.Named;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:33 AM
 */
public class KafkaService extends ApplicationService implements KafkaAdapter {

    @Inject
    public KafkaService(@Named("vertx") Vertx vertx) {
        super(vertx);
    }

    @Override
    public Mono<Object> send(List<OrderDto> dto) {
        DeliveryOptions deliveryOptions = new DeliveryOptions().setCodecName(GenericCodec.class.getSimpleName());

        return Mono.fromCompletionStage(
                vertx.eventBus()
                    .request(EvbAddress.KAFKA_SERVICE, new Operation(EvbCommand.SEND_ORDER, dto), deliveryOptions)
                    .toCompletionStage())
            .map(result -> {
                return result.body();
            });
    }
}
