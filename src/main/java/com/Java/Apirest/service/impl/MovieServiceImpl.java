package com.Java.Apirest.service.impl;

import com.Java.Apirest.dto.MovieDetailsDto;
import com.Java.Apirest.dto.MovieGetDto;
import com.Java.Apirest.dto.service.MovieDtoService;
import com.Java.Apirest.entity.Movie;
import com.Java.Apirest.repository.MovieRepository;
import com.Java.Apirest.service.MovieService;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieDtoService movieDtoService;
    @Autowired
    GenereServiceImpl genereServiceImpl;
    
    
    @Override
    public void create(Movie movie) {
        movieRepository.save(movie);
    }
    
    
    @Override
    public void update(MovieDetailsDto dto, Movie movie) {
                
       if (dto.getTitle()!=null) {
            movie.setTitle(dto.getTitle());
        }
        if (dto.getScore()!=null) {
            movie.setScore(dto.getScore());
        }
        if (dto.getImage()!=null) {
            movie.setImage(dto.getImage());
        }
        if (dto.getCreateDate()!=null) {
            movie.setCreateDate(dto.getCreateDate());
        }
       
        movieRepository.save(movie);
    
    }

    @Override
    public void delete(Long id) {
       movieRepository.deleteById(id);
    }

    @Override
    public List<MovieGetDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movieDtoService.createListDto(movies);
    }

    @Override
    public Movie findById(Long id) {
        if (!existMovie(id)) {
           throw new NoSuchElementException("No existe la pelicula con id: " + id);
            
        }
       return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<MovieGetDto> findByName(String name) {
        List<Movie> movies = movieRepository.findByName(name);

    return movieDtoService.createListDto(movies);
    }

    @Override
    public List<MovieGetDto> findByGenere(Long idGenere) {
       
       
         return movieDtoService.createListDto(genereServiceImpl.findById(idGenere).getMovies());

        
    }

    @Override
    public List<MovieGetDto> orderByCreateDate(String dato) {
        if (dato.equalsIgnoreCase("asc")) {
            return movieDtoService.createListDto(movieRepository.orderByCreateDateAsc());
        }
        if (dato.equalsIgnoreCase("desc")) {
            return movieDtoService.createListDto(movieRepository.orderByCreateDateDesc());
        }
        
        return null;
    }

    @Override
    public boolean existMovie(Long id) {
        return movieRepository.existsById(id);
    }
    
}
