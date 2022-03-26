
package com.example.challengeAlkemy.exception;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Mariela
 */
@Data
public class ErrorApp {
     private int status;
  
  private String message;
  
  private Date timestamp;
  
  List<String> errors;

    public ErrorApp() {
    }
  
  public ErrorApp(String message) {
    this.message = message;
  }

}


