package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.domain.UserInfo;
import yougboyclub.honbabstop.dto.*;
import yougboyclub.honbabstop.service.UserService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    //private final PasswordEncoder passwordEncoder;


    //이메일 인증코드 발송
    //BindingResult: 스프링 프레임워크에서 사용되는 유효성 검사(validation) 결과를 수신하고 오류 메시지를 처리하는 인터페이스
    @PostMapping("/emails/authenticationRequest")
    public ResponseEntity<String> sendMessage(@RequestBody @Validated RequestUserEmailDto toEmailDto, BindingResult bindingResult) {
        //입력받은 이메일이 빈칸이 있거나 이메일형식이 아닐 시, 에러 반환.
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 형식이 맞지 않습니다.");
        }

        String toEmail = toEmailDto.getEmail();
        ResponseEntity<String> result = userService.sendCodeToEmail(toEmail);

        return result;
    }

    //인증코드 인증 확인
    @PostMapping("/emails/codeChecked")
    public ResponseEntity<String> verificationEmailCode(@RequestBody RequestEmailCodeVerificationDto verificationDto) {
        ResponseEntity<String> result = userService.verifiedCode(verificationDto.getEmail(), verificationDto.getAuthCode());

        return result;
    }

    //회원가입
    @PostMapping("/new")
    public ResponseEntity<String> join(@RequestBody  RequestUserDto dto, BindingResult bindingResult) {
        System.out.println("dto = " + dto);
        //입력받은 정보 중 필수정보나 형식에 맞지 않을 시, 에러 반환.
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입에 필요한 정보가 부족하거나 형식이 틀렸습니다. 다시 확인해주세요.");
        }
        userService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 성공하였습니다.");
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLoginDto dto) {
        System.out.println("컨트롤러 이메일 = " + dto.getEmail());
        ResponseLoginDto responseLoginDto = userService.login(dto.getEmail(), dto.getPassword());

        return ResponseEntity.ok().body(responseLoginDto); // 'Authorization' 헤더가 포함된 HTTP 응답을 반환.

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

    //email로 회원정보 가져오기
    @GetMapping
    public ResponseEntity<User> findUser(@RequestParam String email) {
        User findUser = userService.findByEmail(email);
        return ResponseEntity.ok(findUser);
    }

    // 회원정보 수정하기
    @PostMapping("/edit")
    public ResponseEntity<User> updateUser (@RequestBody UpdateUserRequest request) {
        System.out.println("찍혀?");
        String name = request.getEmail();
        System.out.println("findUserName = " + name);
        User findUser = userService.findByEmail(name); // findByName으로 수정 필요
        System.out.println("findUser = " + findUser);
        Long id = findUser.getId();
        User update = userService.updateById(id, request);
        System.out.println("update = " + update);
        return ResponseEntity.ok(update);
    }
}