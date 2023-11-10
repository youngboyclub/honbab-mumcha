package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.domain.UserInfo;
import yougboyclub.honbabstop.dto.RequestEmailCodeVerificationDto;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.dto.RequestUserEmailDto;
import yougboyclub.honbabstop.service.UserService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //이메일 인증코드 발송
    @PostMapping("/emails/authenticationRequest")
    public ResponseEntity<String> sendMessage(@RequestBody RequestUserEmailDto toEmailDto) {
        String toEmail = toEmailDto.getEmail();
        userService.sendCodeToEmail(toEmail);

        return ResponseEntity.status(HttpStatus.OK).body("인증코드가 발송되었습니다.");
    }

    //인증코드 인증 확인
    @PostMapping("/emails/codeChecked")
    public ResponseEntity<String> verificationEmailCode(@RequestBody RequestEmailCodeVerificationDto verificationDto) {
        userService.verifiedCode(verificationDto.getEmail(), verificationDto.getAuthCode());

        return ResponseEntity.status(HttpStatus.OK).body("인증이 완료되었습니다."); //200
    }

    //회원가입
    @PostMapping("/new")
    public ResponseEntity<String> join(@RequestBody RequestUserDto dto) {
        System.out.println("dto = " + dto);

        userService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 성공하였습니다.");
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpSession session) {
        System.out.println("나는 실행되었도다" + user.getEmail() + user.getPassword());
        System.out.println("일단 세션 확인부터" + session);
        try {
            User loginUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            System.out.println("loginUser" + loginUser);
            if (loginUser != null) {
                // 로그인 성공
                UserInfo userInfo = new UserInfo(loginUser.getName(), loginUser.getEmail());
                return ResponseEntity.ok(userInfo);
            } else {
                // 사용자가 존재하지 않거나 비밀번호가 일치하지 않음
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            // 예외 발생 시
            return ResponseEntity.internalServerError().body("서버 오류: " + e.getMessage());
        }
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("세션에 담긴 유저" + user);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}