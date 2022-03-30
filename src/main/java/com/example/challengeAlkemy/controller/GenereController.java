
package com.example.challengeAlkemy.controller;

import com.example.challengeAlkemy.dto.CharacterGetDto;
import com.example.challengeAlkemy.dto.GenereGetDto;
import com.example.challengeAlkemy.entity.Genere;
import com.example.challengeAlkemy.exception.InvalidDataException;
import com.example.challengeAlkemy.service.impl.GenereServiceImpl;
import com.example.challengeAlkemy.util.Constantes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
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
@Tag(name =  GenereController.ENTIDADES, description = Constantes.OA_ENDPOINT + GenereController.ENTIDADES)
@RestController
public class GenereController {
    
	public static final String ENTIDAD = Constantes.GENERE;
	public static final String ENTIDADES = Constantes.GENERE_PLURAL;
    
	private static final String OA_OBTENER_TODOS = Constantes.OA_OBTENER_TODOS + ENTIDADES;
	private static final String OA_OBTENER = Constantes.OA_OBTENER + ENTIDAD;
	private static final String OA_CREAR = Constantes.OA_CREAR + ENTIDAD;
	private static final String OA_ACTUALIZAR = Constantes.OA_ACTUALIZAR + ENTIDAD;
	private static final String OA_ELIMINAR = Constantes.OA_ELIMINAR + ENTIDAD;
	
    @Autowired
    GenereServiceImpl genereServiceImpl;
    
    @Operation(summary = OA_OBTENER_TODOS)
    @GetMapping
    ResponseEntity<List<GenereGetDto>> getAll() {
        List<GenereGetDto> generers = genereServiceImpl.findAll();

        return new ResponseEntity(generers, HttpStatus.OK);
    }

    
    @Operation(summary = OA_OBTENER)
    @GetMapping("/{id}")
    ResponseEntity <Genere> getGenere(@PathVariable Long id){
        
        Genere genere= genereServiceImpl.findById(id);
        
        return new ResponseEntity(genere,HttpStatus.OK);
    }
    
    @Operation(summary = OA_CREAR)
    @PostMapping
    ResponseEntity<?> create (@Valid @RequestBody Genere genere, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
          throw new InvalidDataException(bindingResult);
        }
        genereServiceImpl.create(genere);
        return new ResponseEntity("se creo genero con exito",HttpStatus.CREATED);
    }
    
    @Operation(summary = OA_ELIMINAR)
    @PostMapping ("/delete/{id}")
    ResponseEntity<?> detele (@PathVariable Long id){
        
        if(!genereServiceImpl.existGenere(id)){
           throw new NoSuchElementException("No existe genero con id: " + id);
        }
       
        genereServiceImpl.delete(id);
        return new ResponseEntity("se elimino correctamente",HttpStatus.OK);
    }
    
    @Operation(summary = OA_ACTUALIZAR)
    @PutMapping("/{id}")
    ResponseEntity<?>update(@PathVariable Long id, @RequestBody Genere genere,BindingResult bindingResult){
        if (!genereServiceImpl.existGenere(id)) {
            throw new NoSuchElementException("No existe genero con id: " + id);
        }
       
       genereServiceImpl.update(genere, id);
        return new ResponseEntity("modificacion exitosa",HttpStatus.OK);
    }
}
