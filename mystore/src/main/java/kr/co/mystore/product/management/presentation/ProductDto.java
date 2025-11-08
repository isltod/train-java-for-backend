package kr.co.mystore.product.management.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.mystore.product.management.domain.Product;

public class ProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public ProductDto(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDto(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Integer getPrice() { return price; }

    public Integer getAmount() { return amount; }

    public void setId(Long id) { this.id = id; }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getAmount()
        );
        return product;
    }

    // 둘 다 인스턴스 메소드로 만들 수 있지만, 코드가 복잡해진다...
    public static ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto(
                product.getId(), product.getName(), product.getPrice(), product.getAmount()
        );
        return productDto;
    }

}
