package yougboyclub.honbabstop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //회원가입 (save)
    @Override
    public User save(RequestUserDto userDto) {
        User savedUser = userDto.toEntity(); //입력받은 정보로 User 객체 생성 후 DB에 저장.
        System.out.println("savedUser = " + savedUser);
        return userRepository.save(savedUser);
    }

    // 유저 1명 찾기
    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) { //optional 내부의 값이 유효하면
            return optionalUser.get(); //optional 내부에서 해당 객체를 꺼냄
        } else {
            // 예외 던지기
            return null;
        }
    }

}
