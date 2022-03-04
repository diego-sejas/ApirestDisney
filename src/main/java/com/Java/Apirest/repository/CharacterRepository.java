package com.Java.Apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Java.Apirest.entity.Character;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findAllByNameContaining(String name);

    //List<Character> findAllByAge(Integer age);

    List<Character> findByAge (Integer age);
    
    List<Character> findByMoviesId(Long idMovie);
       
	@Query
	 ("SELECT c FROM Character c WHERE c.name LIKE CONCAT('%', :dato,'%') ")
	  List<Character>findByName(@Param("dato")String name );
	 

}
