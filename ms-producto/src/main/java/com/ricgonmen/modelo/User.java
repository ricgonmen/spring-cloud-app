package com.ricgonmen.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Entity
public class User {

	@Id 
	@GeneratedValue 
	private Long id;
	
	private String nombre;
	
	private float precio;
	
}
