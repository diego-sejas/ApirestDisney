
package com.example.challengeAlkemy.security.controller;

import com.example.challengeAlkemy.security.dto.JwtDto;
import com.example.challengeAlkemy.security.dto.LoginUser;
import com.example.challengeAlkemy.security.dto.Mensaje;
import com.example.challengeAlkemy.security.dto.NewUser;
import com.example.challengeAlkemy.security.entity.Rol;
import com.example.challengeAlkemy.security.entity.User;
import com.example.challengeAlkemy.security.enums.RolName;
import com.example.challengeAlkemy.security.jwt.JwtProvider;
import com.example.challengeAlkemy.security.service.RolService;
import com.example.challengeAlkemy.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "https://portfolio-lh.web.app")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthController {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
   @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity(new Mensaje("the user entered already exists"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Mensaje("the email entered already exists"), HttpStatus.BAD_REQUEST);
        User user =
                new User(newUser.getFirstName(), newUser.getLastName(),newUser.getUserName(), newUser.getEmail(),
                        passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Mensaje("Saved user"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("wrong fields"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}