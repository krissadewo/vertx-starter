package id.or.greenlabs.vertx.starter.common.evb;

import id.or.greenlabs.vertx.starter.module.kafka.service.KafkaService;
import id.or.greenlabs.vertx.starter.module.kafka.service.StockService;

/**
 * @author krissadewo
 * @date 2/4/22 10:24 AM
 */
public interface EvbAddress {

    String KAFKA_SERVICE = KafkaService.class.getSimpleName();
    String STOCK_SERVICE = StockService.class.getSimpleName();
}
