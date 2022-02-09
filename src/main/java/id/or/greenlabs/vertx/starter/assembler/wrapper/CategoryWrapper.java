package id.or.greenlabs.vertx.starter.assembler.wrapper;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.assembler.generic.Assembler;
import id.or.greenlabs.vertx.starter.document.Category;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author krissadewo
 * @date 1/21/22 1:06 PM
 */
public class CategoryWrapper implements Assembler<CategoryDto, Category> {

    @Override
    public CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId().toHexString());
        dto.setName(entity.getName());

        return dto;
    }

    @Override
    public Collection<CategoryDto> toDto(Collection<Category> documents) {
        Collection<CategoryDto> dtos = new ArrayList<>();

        documents.forEach(object -> {
            dtos.add(toDto(object));
        });

        return dtos;
    }

    @Override
    public Category toParam(CategoryDto dto) {
        Category param = new Category();
        param.setName(dto.getName());

        return param;
    }

    @Override
    public Category toDocument(CategoryDto dto) {
        Category document = new Category();
        document.setName(dto.getName());

        return document;
    }

    @Override
    public Collection<Category> toDocument(Collection<CategoryDto> dto) {
        return null;
    }
}
