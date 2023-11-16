package yougboyclub.honbabstop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String email;
    private String name;
    private String address;

}
