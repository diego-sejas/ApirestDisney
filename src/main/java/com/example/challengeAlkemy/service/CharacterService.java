/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.service;


import com.example.challengeAlkemy.dto.CharacterDetailsDto;
import com.example.challengeAlkemy.dto.CharacterGetDto;
import java.util.List;
import com.example.challengeAlkemy.entity.Character;
/**
 *
 * @author Mariela
 */
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
