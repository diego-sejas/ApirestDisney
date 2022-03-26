/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.controller;

import com.example.challengeAlkemy.dto.MovieDetailsDto;
import com.example.challengeAlkemy.dto.MovieGetDto;
import com.example.challengeAlkemy.dto.service.MovieDtoService;
import com.example.challengeAlkemy.entity.Movie;
import com.example.challengeAlkemy.exception.InvalidDataException;
import com.example.challengeAlkemy.service.GenereService;
import com.example.challengeAlkemy.service.impl.MovieServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mariela
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @Autowired
    MovieDtoService movieDtoService;

    @Autowired
    GenereService genereService;

    @GetMapping
    ResponseEntity<List<MovieGetDto>> getAll() {
        List<MovieGetDto> movies = movieServiceImpl.findAll();

        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<MovieDetailsDto> getMovie(@PathVariable Long id) {

        Movie movie = movieServiceImpl.findById(id);
        MovieDetailsDto movieDto = movieDtoService.createDetailsDto(movie);
        return new ResponseEntity(movieDto, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InvalidDataException(bindingResult);
        }
        movieServiceImpl.create(movie);
        return new ResponseEntity("se creo personaje con exito", HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    ResponseEntity<?> detele(@PathVariable Long id) {

        if (!movieServiceImpl.existMovie(id)) {

            throw new NoSuchElementException("No existe la pelicula con id: " + id);
        }

        movieServiceImpl.delete(id);
        return new ResponseEntity("se elimino correctamente", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody MovieDetailsDto movieDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult);
        }

        Movie movie = movieServiceImpl.findById(id);
        movieServiceImpl.update(movieDto, movie);
        return new ResponseEntity("modificacion exitosa", HttpStatus.OK);
    }

    @GetMapping(params = "name")
    ResponseEntity<?> searchName(@RequestParam("name") String title) {
        List<MovieGetDto> movieDto = movieServiceImpl.findByName(title);
        if (movieDto.isEmpty()) {
            throw new NoSuchElementException("No existe peliculas con ese nombre: " + title);
        }

        return new ResponseEntity(movieDto, HttpStatus.OK);

    }

    @GetMapping(params = "genre")
    ResponseEntity<?> searchGenre(@RequestParam("genre") Long idGenere) {
        if (!genereService.existGenere(idGenere)) {
            throw new NoSuchElementException("No existe el genero: " + idGenere);

        }

        List<MovieGetDto> moviesDto = movieServiceImpl.findByGenere(idGenere);
        if (moviesDto.isEmpty()) {
            throw new NoSuchElementException("No existe peliculas con el genero: " + idGenere);

        }

        return new ResponseEntity(moviesDto, HttpStatus.OK);

    }

    @GetMapping(params = "order")
    ResponseEntity<?> searchMovie(@RequestParam("order") String dato) {

        List<MovieGetDto> moviesDto = movieServiceImpl.orderByCreateDate(dato);

        return new ResponseEntity(moviesDto, HttpStatus.OK);

    }

}
