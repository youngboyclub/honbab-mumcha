package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;

public interface UserService {

    //회원가입 (save)
    User save(RequestUserDto userDto);

    //이메일 인증코드 발송
    void sendCodeToEmail(String toEmail);

    //이메일 인증코드 확인
    Boolean verifiedCode(String email, String code);


}
