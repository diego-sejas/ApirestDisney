package com.example.challengeAlkemy.dto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.challengeAlkemy.dto.GenereGetDto;
import com.example.challengeAlkemy.dto.MovieGetDto;
import com.example.challengeAlkemy.entity.Genere;
import com.example.challengeAlkemy.entity.Movie;
import com.example.challengeAlkemy.repository.GenereRepository;

@Service
public class GenereDtoService {
	
	@Autowired
    GenereRepository genereRepository;
	
	
	public GenereGetDto createDto(Genere genere){
		GenereGetDto genereDto = new GenereGetDto();
		genereDto.setId(genere.getId());
		genereDto.setName(genere.getName());
		genereDto.setImage(genere.getImage());
        
        return genereDto;
        
    }

	public List<GenereGetDto> createListDto(List<Genere> generes){
	       List<GenereGetDto> generesDto = new ArrayList();
	       
	        for (Genere genere : generes) {
	        	generesDto.add(createDto(genere));
	        }
	        
	        return generesDto;
	    }
}
