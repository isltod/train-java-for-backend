package kr.co.mystore.product.management.presentation;

import jakarta.validation.Valid;
import kr.co.mystore.product.management.application.SimpleProductService;
import kr.co.mystore.product.management.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    // 이게 스프링의 Application Context에 들어있는 Bean을 가져오는 주석
    @Autowired
    public ProductController(SimpleProductService simpleProductService) {
        /*
        그냥 SimpleProductService 클래스를 이 클래스의 필드로 등록하는 건데..
        이걸 왜 거창하게 Bean을 등록하고 의존성을 주입한다고 표현하지?
         */
        this.simpleProductService = simpleProductService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        // 매개변수로 오니까 거기다 그냥 @Valid, Product는 매개변수로 오는게 아니니까 별도의 Validation 서비스?
        return simpleProductService.add(productDto);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDto findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findProducts(@RequestParam(required = false) String name) {
        if (name == null) return simpleProductService.findAll();
        return simpleProductService.findByNameContaining(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }
}
