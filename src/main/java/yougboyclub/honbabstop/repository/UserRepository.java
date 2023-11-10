package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yougboyclub.honbabstop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // 이메일로 회원 객체를 조회.
    
    User findByEmailAndPassword(String email,String password);//이메일과 비밀번호로 조회
}
