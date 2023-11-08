package yougboyclub.honbabstop.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class RequestEmailCodeVerificationDto {
    @Email
    private String email;
    @NotBlank
    private String authCode;
}
