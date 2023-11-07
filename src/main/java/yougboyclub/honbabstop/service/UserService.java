package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;

public interface UserService {
    //회원가입 (save)
    User save(RequestUserDto userDto);
}
