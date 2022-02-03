package id.or.greenlabs.vertx.starter.assembler.dto;

import id.or.greenlabs.vertx.starter.document.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author krissadewo
 * @date 2/3/22 5:33 PM
 */
@Data
@NoArgsConstructor
public class OrderDto {

    private String id;

    private ProductDto product;

    private String code;

    private int qty;

    private Order.Type type;

    private long createdTime;

    public enum Type {
        SELL, BUY
    }
}
