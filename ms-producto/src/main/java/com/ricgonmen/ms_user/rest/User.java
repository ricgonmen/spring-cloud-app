package com.ricgonmen.ms_user.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class User {

	@Id 
	@GeneratedValue 
	private Long id;
	
	private String nombre;
	
	private float precio;
	
}
