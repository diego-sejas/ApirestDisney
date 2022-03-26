/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.security.service;


import com.example.challengeAlkemy.security.repository.UserAppRepository;
import com.example.challengeAlkemy.security.entity.UserApp;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariela
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
    
@Autowired
UserAppRepository userAppRepository;
@Autowired
 private BCryptPasswordEncoder encoder;


 
    public void createUser (UserApp userApp){
       
        userApp.setPassword(encoder.encode(userApp.getPassword()));
        
        userAppRepository.save(userApp);
        
    }
    
    public boolean existsUsername(String username ){
        return userAppRepository.existsByUsername(username);
        
    }
    
    public boolean existsMail(String mail){
        return userAppRepository.existsByMail(mail);
    }
    
    public UserApp findByUsermame(String usermane){
        return userAppRepository.findByUsername(usermane);
    }
    
    
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user= userAppRepository.findByUsername(username);
        
        if (user==null) {
            throw new UsernameNotFoundException("No hay usuario con ese nombre");
            
        }
               
       return user;
    }
    
    
}
