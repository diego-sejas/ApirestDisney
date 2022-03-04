package com.example.challengeAlkemy.service;

import com.example.challengeAlkemy.entity.Genere;

public interface GenereService {
    
    void create (Genere genere);
    void delete (Long id);
    Genere findById(Long id);
    boolean existGenere (Long id);
}
