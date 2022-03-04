package com.Java.Apirest.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Java.Apirest.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

	 @Query ("SELECT m FROM Movie m WHERE m.title LIKE CONCAT('%', :dato,'%') ")
	 List<Movie>findByName(@Param("dato")String title );
	    
	 @Query ("SELECT m FROM Movie m ORDER BY createDate DESC ")
	 List<Movie> orderByCreateDateDesc();
	    
	 @Query ("SELECT m FROM Movie m ORDER BY createDate ASC ")
	 List<Movie> orderByCreateDateAsc();
}