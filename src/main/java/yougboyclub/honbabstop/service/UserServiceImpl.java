package yougboyclub.honbabstop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Getter
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    //회원가입 (save)
    @Override
    public User save(RequestUserDto userDto) {
        System.out.println("userDto = " + userDto);
        User savedUser = userDto.toEntity(); //입력받은 정보로 User 객체 생성 후 DB에 저장.
        System.out.println("savedUser = " + savedUser);
        return userRepository.save(savedUser);
    }
}
