package com.example.challengeAlkemy.controller;


import com.example.challengeAlkemy.dto.CharacterDetailsDto;
import com.example.challengeAlkemy.dto.CharacterGetDto;
import com.example.challengeAlkemy.repository.CharacterRepository;
import com.example.challengeAlkemy.security.dto.Mensaje;
import com.example.challengeAlkemy.service.impl.CharacterServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.challengeAlkemy.entity.Character;
import com.example.challengeAlkemy.exception.InvalidDataException;
import com.example.challengeAlkemy.service.impl.MovieServiceImpl;
import com.example.challengeAlkemy.util.Constantes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/characters")
@Tag(name =  CharacterController.ENTIDADES, description = Constantes.OA_ENDPOINT + CharacterController.ENTIDADES)
public class CharacterController {

	public static final String ENTIDAD = Constantes.CHARACTER;
	public static final String ENTIDADES = Constantes.CHARACTER_PLURAL;
	private static final String OA_OBTENER_TODOS = Constantes.OA_OBTENER_TODOS + ENTIDADES;
	private static final String OA_OBTENER = Constantes.OA_OBTENER + ENTIDAD;
	private static final String OA_CREAR = Constantes.OA_CREAR + ENTIDAD;
	private static final String OA_ACTUALIZAR = Constantes.OA_ACTUALIZAR + ENTIDAD;
	private static final String OA_ELIMINAR = Constantes.OA_ELIMINAR + ENTIDAD;
	
	
    @Autowired
    private CharacterServiceImpl characterServiceImpl;
    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @Autowired
    private CharacterRepository characterRepository;

    
    @Operation(summary = OA_OBTENER_TODOS)
    @GetMapping
    ResponseEntity<List<CharacterGetDto>> getAll() {
        List<CharacterGetDto> characters = characterServiceImpl.findAll();

        return new ResponseEntity(characters, HttpStatus.OK);
    }

    @Operation(summary = OA_OBTENER)
    @GetMapping("/{id}")
    ResponseEntity<Character> getCharacter(@PathVariable Long id) {

        Character character = characterServiceImpl.findById(id);
        return new ResponseEntity(character, HttpStatus.resolve(200));
    }
    
    @Operation(summary = OA_CREAR)
    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Character character, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InvalidDataException(bindingResult);
        }
        characterServiceImpl.create(character);
        return new ResponseEntity("successfully created character", HttpStatus.CREATED);
    }

    @Operation(summary = OA_ELIMINAR)
    @PostMapping("/delete/{idCharacter}")
    ResponseEntity<?> detele(@PathVariable Long idCharacter) {

        Character character = characterServiceImpl.findById(idCharacter);

        characterServiceImpl.delete(idCharacter);
        return new ResponseEntity("se elimino correctamente", HttpStatus.ACCEPTED);
    }

    @Operation(summary = OA_ACTUALIZAR)
    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody CharacterDetailsDto characterDto) {

        Character character = characterServiceImpl.findById(id);
        characterServiceImpl.update(characterDto, character);
        return new ResponseEntity("modificacion exitosa", HttpStatus.OK);
    }

    @GetMapping(params = "name")
    ResponseEntity<?> searchName(@RequestParam("name") String name) {
        List<CharacterGetDto> characters = characterServiceImpl.findByName(name);
        if (characters.isEmpty()) {

            throw new NoSuchElementException("No existe personaje con el nombre: " + name);
        }

        return new ResponseEntity(characters, HttpStatus.OK);

    }

    @GetMapping(params = "age")
    ResponseEntity<?> searchAge(@RequestParam("age") Integer age) {
        List<CharacterGetDto> characters = characterServiceImpl.findByAge(age);
        if (characters.isEmpty()) {

            throw new NoSuchElementException("No existe personaje con la edad: " + age);
        }

        return new ResponseEntity(characters, HttpStatus.OK);

    }

    @GetMapping(params = "movie")
    ResponseEntity<?> searchMovie(@RequestParam("movie") Long idMovie) {
        if (!movieServiceImpl.existMovie(idMovie)) {
            throw new NoSuchElementException("No existe  la pelicula con id: " + idMovie);

        }
        List<CharacterGetDto> characters = characterServiceImpl.findByMovie(idMovie);
        if (characters.isEmpty()) {

            throw new NoSuchElementException("No existe personajes en la pelicula: " + idMovie);
        }

        return new ResponseEntity(characters, HttpStatus.OK);

    }

}
