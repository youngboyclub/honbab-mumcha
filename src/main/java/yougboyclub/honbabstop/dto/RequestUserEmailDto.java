package yougboyclub.honbabstop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestUserEmailDto {

    @NotBlank //공백 없음
    @Email//email 형식 맞추기
    private String email;
}
