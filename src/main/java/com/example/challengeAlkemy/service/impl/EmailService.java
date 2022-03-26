/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.service.impl;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariela
 */
@Service
public class EmailService {
    @Autowired
    JavaMailSender sender;
    
    @Value ("${spring.mail.username}")
    String from;
    
    
    public void sendEmail (String to,String subject,String text ) throws MessagingException{
       new Thread(()-> {
            
               try {
                  
                   MimeMessage message = sender.createMimeMessage();
                   
                   MimeMessageHelper helper = new MimeMessageHelper(message, true);
                   
                   helper.setFrom(from);
                   helper.setTo(to);
                   helper.setSubject(subject);
                   helper.setText(text);
                   helper.addAttachment("imagen.png", new File("src//main//resources//static//fotos//imagen.png"));
                   sender.send(message);
               } catch (MessagingException e) {
                   System.out.println("Error al enviar mensaje " + to);
                   
               }
           
       }).start();
        
 
        
        
        
    }
}
