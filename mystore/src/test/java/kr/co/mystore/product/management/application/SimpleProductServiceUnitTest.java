package kr.co.mystore.product.management.application;

import kr.co.mystore.product.management.domain.Product;
import kr.co.mystore.product.management.domain.ProductRepository;
import kr.co.mystore.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimpleProductServiceUnitTest {

    // 근데 이게 구현이 안된 상황에서 하는 모킹 테스트 아닌가? 이걸 이렇게 버젓이 사용해도 되나?
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야 한다.")
    void productAddTest() {
        ProductDto productDto = new ProductDto("필통", 1300, 120);
        long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);

        ProductDto savedProductDto = simpleProductService.add(productDto);

        assertEquals(savedProductDto.getId(), PRODUCT_ID);
    }

}
