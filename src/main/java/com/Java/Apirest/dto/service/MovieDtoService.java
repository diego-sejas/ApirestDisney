
package com.Java.Apirest.dto.service;

import com.Java.Apirest.dto.MovieDetailsDto;
import com.Java.Apirest.dto.MovieGetDto;
import com.Java.Apirest.entity.Genere;
import com.Java.Apirest.entity.Movie;
import com.Java.Apirest.repository.GenereRepository;
import com.Java.Apirest.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieDtoService {
    
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CharacterDtoService characterDtoService;
    @Autowired
    GenereRepository genereRepository;
    
    
    public MovieGetDto createDto(Movie movie){
        MovieGetDto movieDto = new MovieGetDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setImage(movie.getImage());
        movieDto.setCreateDate(movie.getCreateDate());
        
        return movieDto;
        
    }
    
    public List<MovieGetDto> createListDto(List<Movie> movies){
       List<MovieGetDto> moviesDto = new ArrayList();
       
        for (Movie movie : movies) {
            moviesDto.add(createDto(movie));
        }
        
        return moviesDto;
    }
    
  
    
    public MovieDetailsDto createDetailsDto(Movie movie){
        MovieDetailsDto movieDto = new MovieDetailsDto();
        Genere genere = genereRepository.findgenereByMovie(movie.getId());
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setImage(movie.getImage());
        movieDto.setScore(movie.getScore());
        movieDto.setCreateDate(movie.getCreateDate());
        movieDto.setGenere(genere);// agregar genero
        movieDto.setCharacters(characterDtoService.createListDto(movie.getCharacters()));
        
        return movieDto;
    }
    
}
