package com.Java.Apirest.service;

import com.Java.Apirest.entity.Genere;

public interface GenereService {
    
    void create (Genere genere);
    void delete (Long id);
    Genere findById(Long id);
    boolean existGenere (Long id);
}
