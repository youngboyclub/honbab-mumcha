package yougboyclub.honbabstop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //회원이름이 중복이었을 때 : 409에러
    USERNAME_DUPLICATE(HttpStatus.CONFLICT, ""),
    //회원 이름이 없을 때 : 404에러
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    //패스워드가 틀릴때 : 409 에러
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");

    private HttpStatus httpStatus;
    private String message;
}
