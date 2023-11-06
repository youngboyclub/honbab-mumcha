package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yougboyclub.honbabstop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
