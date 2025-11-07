package kr.co.mystore.product.management.application;

import kr.co.mystore.product.management.domain.Product;
import kr.co.mystore.product.management.infrastructure.ListProductRepository;
import kr.co.mystore.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {

    // ListProductRepository가 제너릭이 아니라, 그 안에 products 필드가 제너릭이다..
    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }

    // 이렇게 중간에 전달만 하는 메서드를 가진 클래스들이 다 필요 없어 보이는데...
    public ProductDto add(ProductDto productDto) {
        /*
         이걸 왜 궂이 이렇게 DTO라는 걸 만들어서 이리 바꿨다 저리 바꿨다 하는지 이해가 잘 안되는데...
         내부 제품 객체 필드가 외부에 어차피 나가는 거 아닌가?
         */
        // 1. 받은 DTO를 실제 내부 제품 객체로 바꾸고
        Product product = modelMapper.map(productDto, Product.class);
        // 2. 그걸 저장하고
        Product savedProduct = listProductRepository.add(product);
        // 3. 돌려줄 객체는 다시 DTO로 바꿔서
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
        // 4. 바꾼 DTO 객체를 돌려준다...
        return savedProductDto;
    }
}
