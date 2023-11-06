package yougboyclub.honbabstop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yougboyclub.honbabstop.dto.RequestUserDto;

@RestController
@RequestMapping("/api/users")
public class UserController {

    //회원가입
    @PostMapping("/new")
    public ResponseEntity<String> join(@RequestBody RequestUserDto dto){
        System.out.println("dto = " + dto);
        return ResponseEntity.ok().body("회원가입이 성공하였습니다.");
    }
}
