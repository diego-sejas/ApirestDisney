
package com.example.challengeAlkemy.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "genres ")
public class Genere implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    
    private String image;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;
    
    @OneToMany 
    private List<Movie> movies = new ArrayList();
}
