package com.Java.Apirest.dto;

import java.util.Date;
import lombok.Data;

@Data
public class MovieGetDto {
    
    private Long id;
    private String title;
    private String image;
    private Date createDate;
}
