/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Mariela
 */
@Data
public class CharacterDetailsDto {
    
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private Integer weight;
    
    private String history;
    
    private String image;    
    
    private List<MovieGetDto> movies = new ArrayList ();
}
