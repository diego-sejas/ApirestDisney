package com.Java.Apirest.service.impl;

import com.Java.Apirest.entity.Genere;
import com.Java.Apirest.repository.GenereRepository;
import com.Java.Apirest.service.GenereService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class GenereServiceImpl implements GenereService {

    @Autowired
    GenereRepository genereRepository;

    @Override
    public void create(Genere genere) {
        Genere genereEx = genereRepository.findByname(genere.getName());

        if (genereEx != null) {
            throw new DuplicateKeyException("Ya existe Genero con nombre: " + genere.getName());
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
