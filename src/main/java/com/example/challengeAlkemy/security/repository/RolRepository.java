
package com.example.challengeAlkemy.security.repository;

import com.example.challengeAlkemy.security.entity.Rol;
import com.example.challengeAlkemy.security.enums.RolName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolName (RolName rolName);
}
