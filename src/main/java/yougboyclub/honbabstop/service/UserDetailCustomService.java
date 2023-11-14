package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.repository.UserRepository;

/**
 * 시큐리티 설정에서 loginProcessing("/login");
 * /login요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername함수가 실행
 */
@Service
@RequiredArgsConstructor
//스프링시큐리티에서 사용자 정보를 가져오는 인터페이스
//시큐리티 Session(내부 Authentication(내부 UserDetails))
public class UserDetailCustomService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override//사용자 email로 사용자의 정보를 가져오는 메서드
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException((email)));
    }
}
