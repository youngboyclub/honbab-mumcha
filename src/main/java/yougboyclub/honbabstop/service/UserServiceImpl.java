package yougboyclub.honbabstop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.dto.UpdateUserRequest;
import yougboyclub.honbabstop.config.security.TokenProvider;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.dto.ResponseLoginDto;
import yougboyclub.honbabstop.exception.AppException;
import yougboyclub.honbabstop.exception.ErrorCode;
import yougboyclub.honbabstop.repository.UserRepository;


import java.time.Duration;
import java.util.Random;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Getter
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder; // 회원 비밀번호 암호화를 위한 객체 주입.

  private static final String AUTH_CODE_PREFIX = "AuthCode ";

  private final MailService mailService;

  private final RedisService redisService;

  @Value("180000")
  private long authCodeExpirationMillis; //인증코드 유효시간 (3분)

  private final TokenProvider tokenProvider;

//  @Value("${jwt.secret}") //application.yml에서 설정. (실제 키는 이 App의 환경 변수 설정에 넣음.)
//  private String secretKey; //깃허브에 시크릿 키가 노출되면 안되기 때문에 따로 설정.

//  private Long expireTimeMs = 1000 * 60 * 60L; //유효시간을 1시간으로 세팅.


  //인증 코드를 생성 후 수신자 이메일로 발송
  @Override
  public void sendCodeToEmail(String toEmail) {
    this.checkDuplicatedEmail(toEmail);
    String title = "[* <혼밥멈춰!!> 이메일 인증 번호 *]";
    String authCode = this.createCode(); // 인증코드 생성
    //메서드 실행 시작 시간
    long beforeTime = System.currentTimeMillis();

    mailService.sendEmail(toEmail, title, authCode); //인증코드를 요청한 이메일 주소로 전송

    //메서드 종료시간
    long afterTime = System.currentTimeMillis();
    long diffTime = afterTime - beforeTime;
    System.out.println("이메일 전송 걸린 시간 = " + TimeUnit.MILLISECONDS.toSeconds(diffTime));


    // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
    redisService.setValues(AUTH_CODE_PREFIX + toEmail,
            authCode, Duration.ofMillis(this.authCodeExpirationMillis));
  }

  //이메일 중복 체크
  //회원가입하려는 이메일로 이미 가입한 회원이 있는지 확인.
  private void checkDuplicatedEmail(String email) {
    //메서드 실행 시작 시간
    long beforeTime = System.currentTimeMillis();

    Optional<User> user = userRepository.findByEmail(email); //파라미터로 받은 이메일이 DB에 존재하는지 확인.

    //메서드 종료시간
    long afterTime = System.currentTimeMillis();
    long diffTime = afterTime - beforeTime;
    System.out.println("중복 체크 걸린 시간 = " +TimeUnit.MILLISECONDS.toSeconds(diffTime));
    if (user.isPresent()) {//회원이 존재하면 예외 발생.
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
    User savedUser = User.builder()
            .name(userDto.getUserName())
            .email(userDto.getEmail())
            .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
            .birth(userDto.getBirth())
            .phone(userDto.getPhone())
            .address(userDto.getAddress())
            .gender(userDto.getGender())
            .mbti(userDto.getMbti())
            .build(); //입력받은 정보로 User 객체 생성 후 DB에 저장.
    System.out.println("savedUser = " + savedUser);
    return userRepository.save(savedUser);
  }

  // 유저 1명 찾기
  @Override
  public User findById(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) { //optional 내부의 값이 유효하면
      return optionalUser.get(); //optional 내부에서 해당 객체를 꺼냄
    } else {
      // 예외 던지기
      return null;
    }
  }

  //로그인
  @Override
  public User findByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) { //optional 내부의 값이 유효하면
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 존재하지 않습니다.");
    }
    User loginUser = user.get();
    return loginUser;
  }

  @Override
  public User findByName(String name) {
    System.out.println("name = " + name);
    return userRepository.findByName(name)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 존재하지 않습니다."));
  }

  @Override
  @Transactional
  public User updateById(Long id, UpdateUserRequest request) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    user.update(request.getEmail(), request.getName(), request.getAddress());

    return user;

//  @Override
//  public ResponseLoginDto login(String email, String password) {
//    //1.userName 없음.
//    User selectedUser = userRepository.findByEmail(email)
//            // 예외 처리하는 기능
//            .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));
//    //2. 패스워드 틀림.
//    // DB에 암호화로 저장된 패스워드와 일치하는지 체크
//    if(!bCryptPasswordEncoder.matches( password, selectedUser.getPassword())){
//      // 예외 처리하는 기능
//      throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력했습니다.");
//    }
//
//    String token = tokenProvider.create(email); //JWT토큰 생성.
//    int expireTime = 3600000;
//    System.out.println("token = " + token);
//    // 예외처리가 끝나면 (로그인 성공 시)토큰을 반환.
//
//    ResponseLoginDto responseLoginDto = new ResponseLoginDto(token, expireTime, selectedUser);
//    return responseLoginDto;
//  }
  @Override
  public ResponseLoginDto login(String email, String password) {
    //1.userName 없음.
    User selectedUser = userRepository.findByEmail(email)
            // 예외 처리하는 기능
            .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));
    //2. 패스워드 틀림.
    // DB에 암호화로 저장된 패스워드와 일치하는지 체크
    if(!bCryptPasswordEncoder.matches( password, selectedUser.getPassword())){
      // 예외 처리하는 기능
      throw new AppException(ErrorCode.INVALID_PASSWORD, "이메일 또는 비밀번호가 틀렸습니다.");
    }

    String token = tokenProvider.create(email); //JWT토큰 생성.
    int expireTime = 3600000;
    System.out.println("token = " + token);
    // 예외처리가 끝나면 (로그인 성공 시)토큰을 반환.

    ResponseLoginDto responseLoginDto = new ResponseLoginDto(token, expireTime, selectedUser);
    return responseLoginDto;
  }
}