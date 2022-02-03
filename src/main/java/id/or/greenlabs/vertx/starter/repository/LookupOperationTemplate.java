package id.or.greenlabs.vertx.starter.repository;

import com.mongodb.client.model.Aggregates;
import id.or.greenlabs.vertx.starter.common.CollectionName;
import org.bson.conversions.Bson;

/**
 * @author krissadewo
 * @date 1/21/22 2:21 PM
 */
public class LookupOperationTemplate {

    public static Bson lookupCategoryFromProduct() {
        return Aggregates.lookup(CollectionName.CATEGORY, "categoryId", "_id", CollectionName.CATEGORY);
    }

    public static Bson lookupProductFromOrder() {
        return Aggregates.lookup(CollectionName.PRODUCT, "productId", "_id", CollectionName.PRODUCT);
    }
}
