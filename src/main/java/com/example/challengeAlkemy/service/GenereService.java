/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.challengeAlkemy.service;

import com.example.challengeAlkemy.entity.Genere;

/**
 *
 * @author Mariela
 */
public interface GenereService {
    
    void create (Genere genere);
    void delete (Long id);
    Genere findById(Long id);
    boolean existGenere (Long id);
}
