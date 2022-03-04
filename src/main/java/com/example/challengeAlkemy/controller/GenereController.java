
package com.example.challengeAlkemy.controller;

import com.example.challengeAlkemy.entity.Genere;
import com.example.challengeAlkemy.exception.InvalidDataException;
import com.example.challengeAlkemy.service.impl.GenereServiceImpl;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/generes")
@RestController
public class GenereController {
    
    
    @Autowired
    GenereServiceImpl genereServiceImpl;
    
    
    
    
    @GetMapping("/{id}")
    ResponseEntity <Genere> getGenere(@PathVariable Long id){
        
        Genere genere= genereServiceImpl.findById(id);
        
        return new ResponseEntity(genere,HttpStatus.OK);
    }
    
    @PostMapping
    ResponseEntity<?> create (@Valid @RequestBody Genere genere, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
          throw new InvalidDataException(bindingResult);
        }
        genereServiceImpl.create(genere);
        return new ResponseEntity("se creo genero con exito",HttpStatus.CREATED);
    }
    
    @PostMapping ("/delete/{id}")
    ResponseEntity<?> detele (@PathVariable Long id){
        
        if(!genereServiceImpl.existGenere(id)){
           throw new NoSuchElementException("No existe genero con id: " + id);
        }
       
        genereServiceImpl.delete(id);
        return new ResponseEntity("se elimino correctamente",HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    ResponseEntity<?>update(@PathVariable Long id, @RequestBody Genere genere,BindingResult bindingResult){
        if (!genereServiceImpl.existGenere(id)) {
            throw new NoSuchElementException("No existe genero con id: " + id);
        }
       
       genereServiceImpl.update(genere, id);
        return new ResponseEntity("modificacion exitosa",HttpStatus.OK);
    }
}
