package yougboyclub.honbabstop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{

    private ErrorCode errorCode; // 어떤 예외가 발생했는지 에러코드.
    private String message; // 어떤 예외가 발생했는지 메시지 출력
}
