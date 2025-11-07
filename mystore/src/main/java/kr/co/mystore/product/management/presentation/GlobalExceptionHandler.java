package kr.co.mystore.product.management.presentation;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import kr.co.mystore.product.management.domain.EntityNotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Throwable cause;

    /*
    위에 @RestControllerAdvice 만으로도 예외가 발생하면 일로 오고,
    오면 아래 @ExceptionHandler 달린거에서 예외를 처리해서 대답 객체를 만들어 보낸다..인데...
    뭔가 눈에 보이게 주고 받고가 아니라 그냥 어노테이션 달면 그걸로 뒤에서 알아서 한다니까 답답하고 헛갈리고...
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        // 같은 예외가 여러개 들어오면 중복 없이 받아서 처리한다? 종류는 같아도 대상이나 필드가 틀리면 따로 알려야 하지 않나?
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> errors = constraintViolations.stream()
                .map(cV -> extractField(cV.getPropertyPath()) + ", " + cV.getMessage())
                .toList();
        // 이건 그냥 Json 문자열에 errors 제목으로 묶기 위한 트릭
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 예외 종류마다 표준적으로 처리를 못하고, 각자 그에 맞는 코드를 구글링해서 찾아 써야 한다...정말 지저분하네...
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fE -> fE.getField() + ", " + fE.getDefaultMessage())
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFountException.class)
    public ResponseEntity<ErrorMessage> hadleEntityNotFoundException(EntityNotFountException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    private String extractField(Path path) {
        String[] splittedArray = path.toString().split("[.]");
        int lastIndex = splittedArray.length - 1;
        return splittedArray[lastIndex];
    }
}
