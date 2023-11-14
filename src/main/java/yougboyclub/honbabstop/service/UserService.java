package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;

import java.util.Optional;

public interface UserService {

    //회원가입 (save)
    User save(RequestUserDto userDto);

    //이메일 인증코드 발송
    void sendCodeToEmail(String toEmail);

    //이메일 인증코드 확인
    void verifiedCode(String email, String code);

    User findById(Long id);

    User findByEmail(String email);




}
