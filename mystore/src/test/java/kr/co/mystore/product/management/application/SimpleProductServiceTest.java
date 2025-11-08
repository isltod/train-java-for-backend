package kr.co.mystore.product.management.application;

import kr.co.mystore.product.management.domain.EntityNotFountException;
import kr.co.mystore.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

    @Transactional
    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    void productAddAndFindByIdTest() {
        ProductDto productDto = new ProductDto("크래용", 468, 864);
        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId);
        assertEquals(savedProductDto.getId(), foundProductDto.getId());
        assertEquals(savedProductDto.getName(), foundProductDto.getName());
        assertEquals(savedProductDto.getPrice(), foundProductDto.getPrice());
        assertEquals(savedProductDto.getAmount(), foundProductDto.getAmount());
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야 한다.")
    void findProductNotExistIdTest() {
        Long notExistId = -1L;
        assertThrows(EntityNotFountException.class, () -> {
            simpleProductService.findById(notExistId);
        });
    }
}