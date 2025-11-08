package kr.co.mystore.product.management.infrastructure;

import kr.co.mystore.product.management.domain.EntityNotFountException;
import kr.co.mystore.product.management.domain.Product;
import kr.co.mystore.product.management.domain.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class ListProductRepository implements ProductRepository {

    // 둘 다 스레드 세이프한 클래들이라는 구만...
    private AtomicLong sequence = new AtomicLong(1L);
    private List<Product> products = new CopyOnWriteArrayList<>();

    // 단순히 상품 하나 받아서 상품 리스트에 넣는다..근데 왜 반환값이 상품이지? success 아닌가?
    public Product add(Product product) {
        // 아이디만 설정하고 1 늘려 대비하고...
        product.setId(sequence.getAndAdd(1L));
        products.add(product);
        return product;
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFountException("Product를 찾지 못했습니다."));
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByNameContainig(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        // List의 set 메서드가 그 인덱스 위치에 객체를 바꿔주는 모양...
        products.set(indexToModify, product);
        return product;
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
