package com.Java.Apirest.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;



@Data @AllArgsConstructor @NoArgsConstructor
@Getter @Setter

@Entity 
@Table(name = "characters")
public class Character{
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer age;
    
    @Column(nullable = false)
    private Integer weight;
    
    @Column(nullable = false)
    private String history;
    
    @Column(nullable = false)
    private String image;
    
    @ManyToMany
    @JoinTable(
    name = "moviesJob", 
    joinColumns = @JoinColumn(name = "character_id"), 
    inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;
    
	/*
	 * @ManyToMany(cascade= CascadeType.PERSIST, fetch =
	 * FetchType.LAZY) @JoinTable(name ="character_movie", joinColumns
	 * = @JoinColumn(name = "id_character"), inverseJoinColumns = @JoinColumn(name =
	 * "id_movie"))
	 * 
	 * @JsonIgnoreProperties(value="characters") private List<Movie> movies = new
	 * ArrayList<>();
	 */
    

    
}




















