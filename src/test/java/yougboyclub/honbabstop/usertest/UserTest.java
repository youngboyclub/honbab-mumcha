package yougboyclub.honbabstop.usertest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yougboyclub.honbabstop.service.UserService;

@SpringBootTest
public class UserTest {
    @Autowired
    private final UserService userService;


}
