/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.service;

import com.example.challengeAlkemy.dto.MovieDetailsDto;
import com.example.challengeAlkemy.dto.MovieGetDto;
import com.example.challengeAlkemy.entity.Movie;
import java.util.List;

/**
 *
 * @author Mariela
 */
public interface MovieService {
    
    void create (Movie movie);
    void update (MovieDetailsDto dto, Movie movie);
    void delete (Long id);
    List<MovieGetDto> findAll();
    Movie findById(Long id);
    List<MovieGetDto> findByName(String name);
    List<MovieGetDto> findByGenere(Long idGenere);
    List<MovieGetDto> orderByCreateDate(String dato);
    boolean existMovie(Long id);
}
