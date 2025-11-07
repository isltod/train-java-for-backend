package kr.co.mystore.product.management.presentation;

import java.util.List;

public class ErrorMessage {

    /*
     예외 정보를 그냥 List<String>으로 다루면 클라이언트로 가는 정보에서 그냥 문자열 배열로 가고,
     이렇게 필드를 선언하면 필드 제목으로 감싼 문자열 배열로 간다...
     */
    private List<String> errors;

    public ErrorMessage(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
