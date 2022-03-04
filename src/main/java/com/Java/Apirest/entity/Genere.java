
package com.Java.Apirest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Mariela
 */
@Entity
@Table(name = "geners")
@Data
public class Genere implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    
    private String image;
    @OneToMany 
    private List<Movie> movies = new ArrayList<Movie>();
}
