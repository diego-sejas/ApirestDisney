package com.Java.Apirest.controllers;

import com.Java.Apirest.dto.CharacterDetailsDto;
import com.Java.Apirest.dto.CharacterGetDto;
import com.Java.Apirest.service.impl.CharacterServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Java.Apirest.entity.Character;
import com.Java.Apirest.exception.InvalidDataException;
import com.Java.Apirest.service.impl.MovieServiceImpl;
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
public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterServiceImpl;
    @Autowired
    private MovieServiceImpl movieServiceImple;

    @GetMapping
    ResponseEntity<List<CharacterGetDto>> getAll() {
        List<CharacterGetDto> characters = characterServiceImpl.findAll();

        return new ResponseEntity<List<CharacterGetDto>>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Character> getCharacter(@PathVariable Long id) {

        Character character = characterServiceImpl.findById(id);
        return new ResponseEntity<Character>(character, HttpStatus.resolve(200));
    }

    @SuppressWarnings("unchecked")
	@PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Character character, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InvalidDataException(bindingResult);
        }
        characterServiceImpl.create(character);
        return new ResponseEntity("se creo personaje con exito", HttpStatus.CREATED);
    }

    @SuppressWarnings("unchecked")
	@PostMapping("/delete/{idCharacter}")
    ResponseEntity<?> detele(@PathVariable Long idCharacter) {

        Character character = characterServiceImpl.findById(idCharacter);

        characterServiceImpl.delete(idCharacter);
        return new ResponseEntity("se elimino correctamente", HttpStatus.ACCEPTED);
    }

    @SuppressWarnings("unchecked")
	@PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody CharacterDetailsDto characterDto) {

        Character character = characterServiceImpl.findById(id);
        characterServiceImpl.update(characterDto, character);
        return new ResponseEntity("modificacion exitosa", HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
	@GetMapping(params = "name")
    ResponseEntity<?> searchName(@RequestParam("name") String name) {
        List<CharacterGetDto> characters = characterServiceImpl.findByName(name);
        if (characters.isEmpty()) {

            throw new NoSuchElementException("No existe personaje con el nombre: " + name);
        }

        return new ResponseEntity(characters, HttpStatus.OK);

    }

    @SuppressWarnings("unchecked")
	@GetMapping(params = "age")
    ResponseEntity<?> searchAge(@RequestParam("age") Integer age) {
        List<CharacterGetDto> characters = characterServiceImpl.findByAge(age);
        if (characters.isEmpty()) {

            throw new NoSuchElementException("No existe personaje con la edad: " + age);
        }

        return new ResponseEntity(characters, HttpStatus.OK);

    }

    @SuppressWarnings("unchecked")
	@GetMapping(params = "movie")
    ResponseEntity<?> searchMovie(@RequestParam("movie") Long idMovie) {
        if (!movieServiceImple.existMovie(idMovie)) {
            throw new NoSuchElementException("No existe  la pelicula con id: " + idMovie);

        }
        List<CharacterGetDto> characters = characterServiceImpl.findByMovie(idMovie);
        if (characters.isEmpty()) {

            throw new NoSuchElementException("No existe personajes en la pelicula: " + idMovie);
        }

        return new ResponseEntity(characters, HttpStatus.OK);

    }

}
