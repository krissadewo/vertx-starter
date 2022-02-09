package id.or.greenlabs.vertx.starter.common;

import id.or.greenlabs.vertx.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.vertx.starter.assembler.dto.OrderDto;
import id.or.greenlabs.vertx.starter.assembler.dto.ProductDto;
import id.or.greenlabs.vertx.starter.document.Category;
import id.or.greenlabs.vertx.starter.document.Order;
import id.or.greenlabs.vertx.starter.document.Product;
import id.or.greenlabs.vertx.starter.document.Stock;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    public CategoryDto categoryDto() {
        CategoryDto dto = new CategoryDto();
        dto.setName("SUV");

        return dto;
    }

    public Product product(ObjectId categoryId) {
        Product product = new Product();
        product.setName("HRV");
        product.setCode("M001");
        product.setCategoryId(categoryId);

        return product;
    }

    public ProductDto productDto() {
        ProductDto dto = new ProductDto();
        dto.setName("HRV");
        dto.setCode("M001");
        dto.setCategory(new CategoryDto(new ObjectId().toHexString()));

        return dto;
    }

    public List<Order> orders(Product product) {
        Order order = new Order();
        order.setProductId(product.getId());
        order.setQty(8);
        order.setType(Order.Type.SELL);

        return Collections.singletonList(order);
    }

    public List<OrderDto> orders() {
        List<OrderDto> orders = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            OrderDto dto = new OrderDto();
            dto.setCode(UUID.randomUUID().toString());
            dto.setProduct(new ProductDto(new ObjectId().toHexString(), new CategoryDto(new ObjectId().toHexString())));
            dto.setQty(i + 2);
            dto.setType(Order.Type.SELL);

            orders.add(dto);
        }

        return orders;
    }

    public List<Stock> stocks(List<Order> orders) {
        List<Stock> stocks = new ArrayList<>();

        orders.forEach(order -> {
            Stock stock = new Stock();
            stock.setOrderId(order.getId());
            stock.setProductId(order.getProductId());
            stock.setQty(order.getQty());

            stocks.add(stock);
        });

        return stocks;
    }

}
