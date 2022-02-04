package id.or.greenlabs.vertx.starter.module.kafka.listener;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.common.evb.EvbAddress;
import id.or.greenlabs.vertx.starter.common.evb.EvbCommand;
import id.or.greenlabs.vertx.starter.common.evb.Operation;
import id.or.greenlabs.vertx.starter.listener.ApplicationListener;
import id.or.greenlabs.vertx.starter.module.kafka.usecase.SendOrder;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 10:18 AM
 */
public class KafkaEvbListener extends ApplicationListener {

    @Inject
    private SendOrder sendOrder;

    @Inject
    public KafkaEvbListener(@Named("vertx") Vertx vertx) {
        super(vertx);
    }

    @Override
    protected void eventBusListener() {
        vertx.eventBus()
            .consumer(EvbAddress.KAFKA_SERVICE, event -> {
                Operation command = (Operation) event.body();

                if (command.getCommand().equals(EvbCommand.SEND_ORDER)) {
                    List<OrderDto> dtos = (List<OrderDto>) command.getBody();

                    sendOrder.execute(dtos)
                        .doOnSuccess(s -> {
                            event.reply(StatusCode.OPERATION_SUCCESS);
                        })
                        .subscribe();
                }
            });
    }
}
