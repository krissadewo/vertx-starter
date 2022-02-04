package id.or.greenlabs.vertx.starter.common.evb;

import id.or.greenlabs.vertx.starter.module.kafka.service.KafkaService;

/**
 * @author krissadewo
 * @date 2/4/22 10:24 AM
 */
public interface EvbAddress {

    String KAFKA_SERVICE = KafkaService.class.getSimpleName();
}
