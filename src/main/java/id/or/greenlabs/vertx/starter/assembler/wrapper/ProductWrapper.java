package id.or.greenlabs.vertx.starter.assembler.wrapper;

import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.assembler.generic.Assembler;
import id.or.greenlabs.vertx.starter.document.Product;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author krissadewo
 * @date 1/31/22 1:02 PM
 */
public class ProductWrapper implements Assembler<ProductDto, Product> {

    @Override
    public ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId().toHexString());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());

        if (entity.getCategory() != null) {
            dto.setCategory(new CategoryWrapper().toDto(entity.getCategory()));
        }

        return dto;
    }

    @Override
    public Collection<ProductDto> toDto(Collection<Product> documents) {
        Collection<ProductDto> dtos = new ArrayList<>();

        documents.forEach(object -> {
            dtos.add(toDto(object));
        });

        return dtos;
    }

    @Override
    public Product toParam(ProductDto dto) {
        Product param = new Product();
        param.setCode(dto.getCode());
        param.setName(dto.getName());

        return param;
    }

    @Override
    public Product toDocument(ProductDto dto) {
        Product document = new Product();
        document.setName(dto.getName());
        document.setCode(dto.getCode());
        document.setCategoryId(new ObjectId(dto.getCategory().getId()));

        return document;
    }

    @Override
    public Collection<Product> toDocument(Collection<ProductDto> dto) {
        Collection<Product> dtos = new ArrayList<>();

        dto.forEach(object -> {
            dtos.add(toDocument(object));
        });

        return dtos;
    }
}
