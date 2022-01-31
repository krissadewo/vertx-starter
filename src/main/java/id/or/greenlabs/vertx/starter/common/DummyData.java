package id.or.greenlabs.vertx.starter.common;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Category;
import id.or.greenlabs.vertx.starter.document.Product;
import org.bson.types.ObjectId;

/**
 * @author krissadewo
 * @date 1/21/22 1:30 PM
 */
public class DummyData {

    public Category category() {
        Category category = new Category();
        category.setName("SUV");

        return category;
    }

    public Product product(ObjectId categoryId) {
        Product product = new Product();
        product.setName("HRV");
        product.setCode("M001");
        product.setCategoryId(categoryId);

        return product;
    }

    public ProductDto productDto() {
        ProductDto product = new ProductDto();
        product.setName("HRV");
        product.setCode("M001");
        product.setCategory(new CategoryDto(new ObjectId().toHexString()));

        return product;
    }
}
