package kr.co.ordermanagement.domain.exception;

public class NotCancelableStateException extends RuntimeException {
    public NotCancelableStateException(String message) {
        super(message);
    }
}
