
package com.example.challengeAlkemy.dto;

import com.example.challengeAlkemy.entity.Genere;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import lombok.Data;

/**
 *
 * @author Mariela
 */
@Data
public class MovieDetailsDto {
    
    private Long id;
    
    private String title;
    @Min(1)
    @Max(5)
    private Integer score;
    
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date createDate;
    
    private List<CharacterGetDto> characters= new ArrayList();
    
    private Genere genere;
}
