package com.Java.Apirest.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Data @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "genres")
public class Genre {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_genre;
    
    @Column(unique=true)
    private String name;
    
    private String image;
    @OneToMany 
    private List<Movie> movies = new ArrayList<Movie>();
    

	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	 * 
	 * @JsonIgnoreProperties(value="genres") private List<Movie> movies = new
	 * ArrayList<>();
	 */

    
    
}





