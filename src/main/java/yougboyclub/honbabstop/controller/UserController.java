package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yougboyclub.honbabstop.dto.RequestUserDto;
import yougboyclub.honbabstop.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //회원가입
    @PostMapping("/new")
    public ResponseEntity<String> join(@RequestBody RequestUserDto dto){
        System.out.println("dto = " + dto);
        userService.save(dto);

        return ResponseEntity.ok().body("회원가입이 성공하였습니다.");
    }
}
