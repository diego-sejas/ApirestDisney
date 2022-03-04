/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Java.Apirest.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Java.Apirest.security.entity.UserApp;

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
