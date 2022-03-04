package com.example.challengeAlkemy.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

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
