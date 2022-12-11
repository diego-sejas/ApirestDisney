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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@Tag(name =  MovieController.ENTIDADES, description = Constantes.OA_ENDPOINT + MovieController.ENTIDADES)
public class MovieController {

	public static final String ENTIDAD = Constantes.MOVIE;
	public static final String ENTIDADES = Constantes.MOVIE_PLURAL;
	
	private static final String OA_OBTENER_TODOS = Constantes.OA_OBTENER_TODOS + ENTIDADES;
	private static final String OA_OBTENER = Constantes.OA_OBTENER + ENTIDAD;
	private static final String OA_CREAR = Constantes.OA_CREAR + ENTIDAD;
	private static final String OA_ACTUALIZAR = Constantes.OA_ACTUALIZAR + ENTIDAD;
	private static final String OA_ELIMINAR = Constantes.OA_ELIMINAR + ENTIDAD;
	
    @Autowired
    MovieServiceImpl movieServiceImpl;

    @Autowired
    MovieDtoService movieDtoService;

    @Autowired
    GenereService genereService;

    @Operation(summary = OA_OBTENER_TODOS)
    @GetMapping
    ResponseEntity<List<MovieGetDto>> getAll() {
        List<MovieGetDto> movies = movieServiceImpl.findAll();

        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @Operation(summary = OA_OBTENER)
    @GetMapping("/{id}")
    ResponseEntity<MovieDetailsDto> getMovie(@PathVariable Long id) {

        Movie movie = movieServiceImpl.findById(id);
        MovieDetailsDto movieDto = movieDtoService.createDetailsDto(movie);
        return new ResponseEntity(movieDto, HttpStatus.OK);
    }

    @Operation(summary = OA_CREAR)
    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InvalidDataException(bindingResult);
        }
        movieServiceImpl.create(movie);
        return new ResponseEntity("Movie was created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = OA_ELIMINAR)
    @PostMapping("/delete/{id}")
    ResponseEntity<?> detele(@PathVariable Long id) {

        if (!movieServiceImpl.existMovie(id)) {

            throw new NoSuchElementException(ENTIDAD + Constantes.CON_ID + id + Constantes.NO_ENCONTRADA);
        }

        movieServiceImpl.delete(id);
        return new ResponseEntity("se elimino correctamente", HttpStatus.OK);
    }

    @Operation(summary = OA_ACTUALIZAR)
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
