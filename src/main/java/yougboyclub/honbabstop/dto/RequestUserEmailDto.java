package yougboyclub.honbabstop.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 회원가입 폼에서 이메일 입력하면 requestBody의 데이터가 이 dto와 매핑된다.
 */
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestUserEmailDto {

    @NotBlank //공백 없음
    @Email//email 형식 맞추기
    private String email;
}
