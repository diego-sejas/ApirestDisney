/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Java.Apirest.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {
    
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
