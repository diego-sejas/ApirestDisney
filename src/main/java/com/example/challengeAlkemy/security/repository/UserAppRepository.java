/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.security.repository;


import com.example.challengeAlkemy.security.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mariela
 */
@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String Username);
    
    boolean existsByUsername(String username);
    boolean existsByMail(String mail);
    
}
