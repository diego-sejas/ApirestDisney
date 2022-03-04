package com.Java.Apirest.service;

import com.Java.Apirest.dto.MovieDetailsDto;
import com.Java.Apirest.dto.MovieGetDto;
import com.Java.Apirest.entity.Movie;
import java.util.List;

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
