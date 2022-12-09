
package com.example.challengeAlkemy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Data
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "movies")
public class Movie implements Serializable {
    
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    @NotNull
    @Min(1)
    @Max(5)
    private Integer score;
    
    @NotBlank
    private String image;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private Date createDate;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private List<Character> characters = new ArrayList();
    
   
    
}
