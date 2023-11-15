package yougboyclub.honbabstop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    //App 예외(커스텀)가 발생하면 처리
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appExceptionHandler(AppException e){ // <?> =>  응답 바디에 어떠한 값이든 다 받음.
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()) //409 에러
                .body(e.getErrorCode().name()+ " " +e.getMessage());
    }

    //런타임 예외가 발생하면 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){ // <?> =>  응답 바디에 어떠한 값이든 다 받음.
        return ResponseEntity.status(HttpStatus.CONFLICT) //409 에러
                .body(e.getMessage());
    }

}
