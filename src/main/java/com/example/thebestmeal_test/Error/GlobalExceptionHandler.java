package com.example.thebestmeal_test.Error;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.thebestmeal_test.Error.ErrorCode.ALREADY_EXIST;
import static com.example.thebestmeal_test.Error.ErrorCode.DUPLICATE_RESOURCE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /* View 를 사용하지 않고 Rest API 로만 사용할 때 쓸 수 있는 @RestControllerAdvice 를 사용합니다.
    handleDataException 메소드에서는 hibernate 관련 에러를 처리합니다.
    handleCustomException 메소드는 직접 정의한 CustomException 을 사용합니다.
    Exception 발생 시 넘겨받은 ErrorCode 를 사용해서 사용자에게 보여주는 에러 메세지를 정의합니다. */

    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {
        log.error("handleDataException throw Exception : {}", ALREADY_EXIST);
        return ErrorResponse.toResponseEntity(ALREADY_EXIST);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}