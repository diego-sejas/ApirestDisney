package com.Java.Apirest.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Java.Apirest.entitys.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre , Integer> {

	Genre findByname(String name);
	@Query("SELECT g FROM Genere g LEFT JOIN g.movies m WHERE m.id = :id")
    Genre findgenereByMovie(@Param("id") Long id);
    
}
