
package com.example.challengeAlkemy.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


/**
 *
 * @author Mariela
 */

@Entity
@Data
@Table(name = "personaje")
public class Character implements Serializable {
  
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    
    private Integer weight;
    
    private String history;
    @NotBlank
    private String image;
    
    
    @ManyToMany
    @JoinTable(
  name = "moviesJob", 
  joinColumns = @JoinColumn(name = "character_id"), 
  inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;
    
   
    
}
