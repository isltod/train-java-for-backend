package kr.co.mystore.product.management.domain;

public class EntityNotFountException extends RuntimeException {
    public EntityNotFountException(String message) {
        super(message);
    }
}
