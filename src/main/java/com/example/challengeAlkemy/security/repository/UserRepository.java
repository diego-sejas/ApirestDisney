
package com.example.challengeAlkemy.security.repository;

import com.example.challengeAlkemy.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
