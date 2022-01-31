package id.or.greenlabs.vertx.starter.assembler.dto;

import id.or.greenlabs.vertx.starter.document.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author krissadewo
 * @date 1/31/22 1:01 PM
 */
@Data
@NoArgsConstructor
public class ProductDto {

    private String id;

    private String code;

    private String name;

    private CategoryDto category;

    private boolean delete;

    public ProductDto(CategoryDto category) {
        this.category = category;
    }
}
