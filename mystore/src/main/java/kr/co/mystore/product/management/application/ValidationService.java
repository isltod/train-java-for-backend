package kr.co.mystore.product.management.application;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/*
 이렇게 조각조각 나누는게 난잡해 보이지만,
 유효성 검서 서비스를 이용하려면 클래스를 만들어야 하고,
 자바는 1 클래스는 1 파일 원칙이니까..
*/
@Service
@Validated
public class ValidationService {
    // 근데 앞에 제너릭은 뭐지? return은 void인데...그냥 여기서 제너릭을 사용하려면 여기 써야 하나?
    public <T> void checkValid(@Valid T validationTarget) {
        // do nothing
    }
}
