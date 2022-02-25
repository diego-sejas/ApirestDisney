package com.Java.Apirest.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity 
@Table(name = "movies")
public class Movie {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movie;
    
    @Column(nullable = false)
    private String image;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date crreationDate = new Date();
    
    @NotNull
    @Min(1)
    @Max(5)
    private Integer qualification;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private List<Character> characters = new ArrayList<Character>();
    
	/*
	 * @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	 * 
	 * @Column(nullable = false)
	 * 
	 * @JsonIgnoreProperties(value="movies") private List<Character> characters =
	 * new ArrayList<>();
	 * 
	 * @ManyToMany(cascade = CascadeType.PERSIST,fetch =
	 * FetchType.LAZY) @JoinTable(name ="movie_genre", joinColumns
	 * = @JoinColumn(name = "id_movie"), inverseJoinColumns = @JoinColumn(name =
	 * "id_genre"))
	 * 
	 * @Column(nullable = false)
	 * 
	 * @JsonIgnoreProperties(value="movies") private List<Genre> genres = new
	 * ArrayList<>();
	 */

	/*
	 * public List<Character> getCharacters() { return characters; }
	 * 
	 * public void setCharacters(List<Character> characters) { this.characters =
	 * characters; }
	 * 
	 * public List<Genre> getGenres() { return genres; }
	 * 
	 * public void setGenres(List<Genre> genres) { this.genres = genres; }
	 */
    
    
    
    
}




















