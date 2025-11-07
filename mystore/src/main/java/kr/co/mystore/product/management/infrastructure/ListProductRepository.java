package kr.co.mystore.product.management.infrastructure;

import kr.co.mystore.product.management.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

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

}
