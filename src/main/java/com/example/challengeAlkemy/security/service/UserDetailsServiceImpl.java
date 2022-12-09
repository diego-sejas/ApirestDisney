
package com.example.challengeAlkemy.security.service;

import com.example.challengeAlkemy.security.entity.User;
import com.example.challengeAlkemy.security.entity.mainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//convierte la clase usuario en UsuarioPrincipal,
//tomando el usuario de la base de datos y convertirlo en UsuarioPrincipal
//clase especifica que utiliza el security para obtener los datos del usuario y los privilegios

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName).get();
        return mainUser.build(user);
    }
    
}
