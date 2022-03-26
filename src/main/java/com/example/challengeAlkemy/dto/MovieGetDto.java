
package com.example.challengeAlkemy.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Mariela
 */
@Data
public class MovieGetDto {
    
    private Long id;
    private String title;
    private String image;
    private Date createDate;
}
