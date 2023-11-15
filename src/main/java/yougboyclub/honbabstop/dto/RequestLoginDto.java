package yougboyclub.honbabstop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class RequestLoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
