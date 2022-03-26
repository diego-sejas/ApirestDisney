
package com.example.challengeAlkemy.security.controller;

import com.example.challengeAlkemy.exception.InvalidDataException;
import com.example.challengeAlkemy.security.dto.UserLogin;
import com.example.challengeAlkemy.security.entity.UserApp;
import com.example.challengeAlkemy.security.jwt.JwtDto;
import com.example.challengeAlkemy.security.jwt.JwtProvider;
import com.example.challengeAlkemy.security.service.UserServiceImpl;
import com.example.challengeAlkemy.service.impl.EmailService;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mariela
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserAppController {
    @Autowired
    private UserServiceImpl userService;
     @Autowired
    private AuthenticationManager authenticationManager;
     @Autowired
     private JwtProvider jwtProvider;
     @Autowired 
     private EmailService emailService;
    
    @PostMapping("/register")
    public ResponseEntity<?> nuevo(@Valid @RequestBody UserApp userApp, BindingResult bindingResult) throws MessagingException{
        if(bindingResult.hasErrors()){
             throw new InvalidDataException(bindingResult);
         }
        if(userService.existsUsername(userApp.getUsername())){
             throw new DuplicateKeyException("Ya existe usuario con nombre: " + userApp.getUsername());
        }
        if(userService.existsMail(userApp.getMail())) {
             throw new DuplicateKeyException("Ya existe usuario con email: " + userApp.getMail());
            
        }
         userService.createUser(userApp);
         String text= "Te damos la BIENVENIDA " + userApp.getFistName() + " " + userApp.getLastName()+ "!!!";
         emailService.sendEmail(userApp.getMail(), "Bienvenido a Disney!",text);
        return new ResponseEntity("usuario guardado con éxito", HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            
             throw new InvalidDataException(bindingResult);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
