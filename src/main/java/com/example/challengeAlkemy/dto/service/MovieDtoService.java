
package com.example.challengeAlkemy.dto.service;

import com.example.challengeAlkemy.dto.MovieDetailsDto;
import com.example.challengeAlkemy.dto.MovieGetDto;
import com.example.challengeAlkemy.entity.Genere;
import com.example.challengeAlkemy.entity.Movie;
import com.example.challengeAlkemy.repository.GenereRepository;
import com.example.challengeAlkemy.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariela
 */
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
