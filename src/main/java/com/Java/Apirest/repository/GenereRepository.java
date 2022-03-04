package com.Java.Apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Java.Apirest.entity.Genere;

@Repository
public interface GenereRepository extends JpaRepository<Genere , Long> {

	Genere findByname(String name);
	
	@Query("SELECT g FROM Genere g LEFT JOIN g.movies m WHERE m.id_movie = :id") Genere
	 findgenereByMovie(@Param("id") Long id_movie);
	 
}
