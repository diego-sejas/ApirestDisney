
package com.example.challengeAlkemy.security.entity;

import com.example.challengeAlkemy.security.enums.RolName;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol() {
    }

    public Rol(RolName rolName) {
        this.rolName = rolName;
    }

    public int getId() {
        return id;
    }

    public RolName getRolName() {
        return rolName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }

    
}
