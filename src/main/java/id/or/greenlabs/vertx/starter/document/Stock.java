package id.or.greenlabs.vertx.starter.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * @author krissadewo
 * @date 2/3/22 8:55 AM
 */
@Data
@NoArgsConstructor
public class Stock implements Serializable {

    @BsonId
    private ObjectId id;

    private ObjectId productId;

    private Product product;

    private ObjectId orderId;

    private Order order;

    private int qty;

    private long createdTime = System.currentTimeMillis();
}
