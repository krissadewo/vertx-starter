package id.or.greenlabs.vertx.starter.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * @author krissadewo
 * @date 2/3/22 9:13 AM
 */
@Data
@NoArgsConstructor
public class Order implements Serializable {

    @BsonId
    private ObjectId id;

    private ObjectId productId;

    private Product product;

    private String code;

    private int qty;

    private Type type;

    private long createdTime = System.currentTimeMillis();

    public enum Type {
        SELL, BUY
    }

}
