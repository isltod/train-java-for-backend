package kr.co.mystore.product.management.presentation;

import kr.co.mystore.product.management.application.SimpleProductService;
import kr.co.mystore.product.management.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        // TODO: Product 만들고 리스트에 넣기
        return simpleProductService.add(productDto);
    }

}
