package com.example.challengeAlkemy.security.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.challengeAlkemy.enums.RolNombre;
import com.sun.istack.NotNull;

public class Rol {

	   @Id
	    @GeneratedValue(strategy =  GenerationType.IDENTITY)
	    private int id;

	    @NotNull
	    //Se indica que va a ser un Enum de tipo String
	    @Enumerated(EnumType.STRING)
	    private RolNombre rolNombre;
}
