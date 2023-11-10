package yougboyclub.honbabstop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.repository.UserRepository;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final String AUTH_CODE_PREFIX = "AuthCode ";

    private final MailService mailService;

    private final RedisService redisService;

    @Value("180000")
    private long authCodeExpirationMillis; //인증코드 유효시간 (3분)


    //인증 코드를 생성 후 수신자 이메일로 발송
    @Override
    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail);
        String title = "[* <혼밥멈춰!!> 이메일 인증 번호 *]";
        String authCode = this.createCode(); // 인증코드 생성

        mailService.sendEmail(toEmail, title, authCode); //인증코드를 요청한 이메일 주소로 전송

        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    //이메일 중복 체크
    //회원가입하려는 이메일로 이미 가입한 회원이 있는지 확인.
    private void checkDuplicatedEmail(String email) {
        User user = userRepository.findByEmail(email); //파라미터로 받은 이메일이 DB에 존재하는지 확인.
        if (user!=null) {//회원이 존재하면 예외 발생.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }
    }

    //인증코드 생성
    private String createCode() {
        String authKey; //인증코드 길이: 6
        try {
            // 임의의 authKey 생성
            Random random = new Random();
            authKey = String.valueOf(random.nextInt(888888) + 111111); // 범위: 111111 ~ 999999
            return authKey;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "알고리즘 생성 에러", e);
        }
    }

    //인증 코드를 검증
    @Override
    public void verifiedCode(String email, String code) {
        this.checkDuplicatedEmail(email); //이메일 중복체크 진행.

        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email); //redis에 저장된 인증코드를 가져옴.
        System.out.println("redisAuthCode = " + redisAuthCode);

        // Redis에서 Code가 없거나 일치하지 않는다면 예외를 반환.
        if (redisAuthCode == null || !redisAuthCode.equals(code)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "인증 코드가 틀렸습니다. 다시 입력해주세요.");
        }

    }

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

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
