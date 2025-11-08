package kr.co.mystore.product.management.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Product {

    private Long id;

    @Size(min = 1, max = 100)
    private String name;

    @Max(1_000_000)
    @Min(0)
    private Integer price;

    @Max(9_999)
    @Min(0)
    private Integer amount;

    public Product(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    // 결국 getter를 넣는데...Infrastructure에서만 사용하면 괜찮다?
    public String getName() { return name; }

    public Integer getPrice() { return price; }

    public Integer getAmount() { return amount; }

    public void setId(Long id) {
        this.id = id;
    }

    // 뿐만 아니라... setter도 몽땅 넣는데...
    public void setName(String name) { this.name = name; }

    public void setPrice(Integer price) { this.price = price; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    public Boolean containsName(String name) {
        return this.name.contains(name);
    }

}
