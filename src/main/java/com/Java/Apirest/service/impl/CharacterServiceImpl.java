package com.Java.Apirest.service.impl;

import com.Java.Apirest.dto.CharacterDetailsDto;
import com.Java.Apirest.dto.CharacterGetDto;
import com.Java.Apirest.dto.MovieGetDto;
import com.Java.Apirest.dto.service.CharacterDtoService;
import com.Java.Apirest.entity.Character;
import com.Java.Apirest.repository.CharacterRepository;
import com.Java.Apirest.service.CharacterService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class CharacterServiceImpl implements CharacterService{
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    CharacterDtoService characterDtoService;
    @Autowired
    MovieServiceImpl movieServiceImpl;
    
    
    
    @Override
    public void create(Character character) {
     
        
        characterRepository.save(character);
    }
   

    @Override
    public void update(CharacterDetailsDto characterDto,Character character) {
        
        if (characterDto.getName()!=null) {
            character.setName(characterDto.getName());
        }
        if (characterDto.getAge()!=null) {
            character.setAge(characterDto.getAge());
        }
        if (characterDto.getHistory()!=null) {
            character.setHistory(characterDto.getHistory());
        }
        if (characterDto.getWeight()!=null) {
            character.setWeight(characterDto.getWeight());
        }
        if (characterDto.getImage()!=null) {
            character.setImage(characterDto.getImage());
        }
        
        if (!characterDto.getMovies().isEmpty()) {
            for (MovieGetDto movieDto : characterDto.getMovies()) {
                character.getMovies().add(movieServiceImpl.findById(movieDto.getId()));
            }
        }
        
        characterRepository.save(character);
    }

    @Override
    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public List<CharacterGetDto> findAll() {
    List<Character>characters= characterRepository.findAll();
 
        
        return characterDtoService.createListDto(characters);
    }

    @Override
    public Character findById(Long id) {
       Character character = characterRepository.findById(id).orElse(null);
        if (character==null) {
            throw new NoSuchElementException("No existe el personaje con el id: " + id);
        }
        
        return character;
    }

    @Override
    public List<CharacterGetDto> findByName(String name) {
        List<Character>characters = characterRepository.findByName(name);
        return characterDtoService.createListDto(characters);
    }

    @Override
    public List<CharacterGetDto> findByAge(Integer age) {
        List<Character>characters = characterRepository.findByAge(age);
        return characterDtoService.createListDto(characters);
    }

    @Override
    public List<CharacterGetDto> findByMovie(Long idMovie) {
        List<Character>characters = characterRepository.findByMoviesId(idMovie);
        return characterDtoService.createListDto(characters);
    }

    @Override
    public boolean existCharacter(Long id) {
    return characterRepository.existsById(id);
        
    }
    

}
