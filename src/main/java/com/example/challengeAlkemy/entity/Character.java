
package com.example.challengeAlkemy.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;


@Entity
@Data
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "characters")
public class Character implements Serializable {
  
    private static final long serialVersionUID = 1L;
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

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;
    
    
    @ManyToMany
    @JoinTable(
  name = "moviesJob", 
  joinColumns = @JoinColumn(name = "character_id"), 
  inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;
    
   
    
}
