package id.or.greenlabs.vertx.starter.module.stock.listener;

import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.common.StatusCode;
import id.or.greenlabs.vertx.starter.common.evb.EvbAddress;
import id.or.greenlabs.vertx.starter.common.evb.EvbCommand;
import id.or.greenlabs.vertx.starter.common.evb.Operation;
import id.or.greenlabs.vertx.starter.listener.ApplicationListener;
import id.or.greenlabs.vertx.starter.module.stock.usecase.Save;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author krissadewo
 * @date 2/4/22 3:29 PM
 */
public class StockEvbListener extends ApplicationListener {

    @Inject
    private Save save;

    @Inject
    public StockEvbListener(@Named("vertx") Vertx vertx) {
        super(vertx);
    }

    @Override
    protected void eventBusListener() {
        vertx.eventBus()
            .consumer(EvbAddress.STOCK_SERVICE, event -> {
                Operation command = (Operation) event.body();

                if (command.getCommand().equals(EvbCommand.UPDATE_STOCK)) {
                    List<OrderDto> dtos = (List<OrderDto>) command.getBody();

                    save.execute(dtos)
                        .doOnSuccess(s -> {
                            event.reply(StatusCode.OPERATION_SUCCESS);
                        })
                        .subscribe();
                }
            });
    }
}
