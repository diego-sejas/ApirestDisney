package com.Java.Apirest.service;


import java.util.List;

import com.Java.Apirest.dto.CharacterDetailsDto;
import com.Java.Apirest.dto.CharacterGetDto;
import com.Java.Apirest.entity.Character;

public interface CharacterService {
    void create (Character character);
    void update (CharacterDetailsDto characterDto,Character character);
    void delete (Long id);
    List<CharacterGetDto> findAll();
    Character findById(Long id);
    List<CharacterGetDto> findByName(String name);
    List<CharacterGetDto> findByAge(Integer age);
    List<CharacterGetDto> findByMovie(Long idMovie);
    boolean existCharacter(Long id);
    
}
