
package com.example.challengeAlkemy.dto.service;

import com.example.challengeAlkemy.dto.CharacterDetailsDto;
import com.example.challengeAlkemy.dto.CharacterGetDto;
import com.example.challengeAlkemy.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.challengeAlkemy.entity.Character;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author Mariela
 */
@Service
public class CharacterDtoService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    MovieDtoService movieDtoService;
    
    
    public CharacterGetDto createDto (Character character){
        CharacterGetDto cDto = new CharacterGetDto();
            cDto.setId(character.getId());
            cDto.setImage(character.getImage());
            cDto.setName(character.getName());
        
        return cDto;
        
        
    }
    
    public List<CharacterGetDto> createListDto (List<Character> characters){
        
    List<CharacterGetDto> charactersDto = new ArrayList();
        for (Character ch : characters) {
            CharacterGetDto cDto = createDto(ch);
            charactersDto.add(cDto);
        }
        
        return charactersDto;
        
    }
    
    
    
    public CharacterDetailsDto createDetailsDto (Character character){
        CharacterDetailsDto cDto =new CharacterDetailsDto();
        cDto.setId(character.getId());
        cDto.setName(character.getName());
        cDto.setAge(character.getAge());
        cDto.setImage(character.getImage());
        cDto.setMovies(movieDtoService.createListDto(character.getMovies()));//agregar metodo listmovie
        cDto.setWeight(character.getWeight());
        cDto.setHistory(character.getHistory());
        
        return cDto;
    }
}
