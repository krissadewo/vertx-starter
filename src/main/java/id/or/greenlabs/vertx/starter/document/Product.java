package id.or.greenlabs.vertx.starter.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;


/**
 * @author krissadewo
 * @date 1/21/22 12:39 PM
 */
@Data
@NoArgsConstructor
public class Product implements Serializable {

    @BsonId
    private ObjectId id;

    private String code;

    private String name;

    private ObjectId categoryId;

    private Category category;

    private boolean delete;

}
