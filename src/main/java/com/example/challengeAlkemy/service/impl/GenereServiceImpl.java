
package com.example.challengeAlkemy.service.impl;

import com.example.challengeAlkemy.dto.GenereGetDto;
import com.example.challengeAlkemy.dto.MovieGetDto;
import com.example.challengeAlkemy.dto.service.GenereDtoService;
import com.example.challengeAlkemy.dto.service.MovieDtoService;
import com.example.challengeAlkemy.entity.Genere;
import com.example.challengeAlkemy.entity.Movie;
import com.example.challengeAlkemy.repository.GenereRepository;
import com.example.challengeAlkemy.service.GenereService;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class GenereServiceImpl implements GenereService {

    @Autowired
    GenereRepository genereRepository;
    @Autowired
    GenereDtoService genereDtoService;

    @Override
    public void create(Genere genere) {
        Genere genereEx = genereRepository.findByname(genere.getName());

        if (genereEx != null) {
            throw new DuplicateKeyException("Genero with name already exists: " + genere.getName());
        }
        genereRepository.save(genere);
    }

    public void update(Genere genere, Long id) {

        Genere genereChange = findById(id);
        if (genere.getName() != null) {
            Genere genereEx = genereRepository.findByname(genere.getName());

            if (genereEx != null && genereChange.getId()!=genereEx.getId()) {
                throw new DuplicateKeyException("Ya existe Genero con nombre: " + genere.getName());
            }
            genereChange.setName(genere.getName());
        }
        if (genere.getImage() != null) {
            genereChange.setImage(genere.getImage());
        }
        if (!genere.getMovies().isEmpty()) {
            genereChange.getMovies().addAll(genere.getMovies());
            
        }
        genereRepository.save(genereChange);
    }

    @Override
    public void delete(Long id) {
        genereRepository.deleteById(id);
    }

    
    public List<GenereGetDto> findAll() {
        List<Genere> generes = genereRepository.findAll();
        return genereDtoService.createListDto(generes);
    }
    
    @Override
    public Genere findById(Long id) {
        if (!existGenere(id)) {
            throw new NoSuchElementException("No existe genero con id: " + id);
        }
        return genereRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existGenere(Long id) {
        return genereRepository.existsById(id);

    }

}
