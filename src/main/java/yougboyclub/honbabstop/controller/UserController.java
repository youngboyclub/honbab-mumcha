package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.dto.RequestEmailCodeVerificationDto;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.dto.RequestUserEmailDto;
import yougboyclub.honbabstop.service.UserService;

import javax.validation.Valid;

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
    public ResponseEntity<String> join(@RequestBody RequestUserDto dto){
        System.out.println("dto = " + dto);

        userService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 성공하였습니다.");
    }
}