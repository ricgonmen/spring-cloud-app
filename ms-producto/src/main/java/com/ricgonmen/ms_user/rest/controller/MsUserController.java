package com.ricgonmen.ms_user.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ricgonmen.modelo.User;
import com.ricgonmen.modelo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MsUserController {
    @Autowired
	private final UserRepository usuarioRepositorio;

	/**
	 * Obtenemos todos los usuarios
	 * 
	 * @return
	 */
	@GetMapping("/usuario")
	public List<User> obtenerTodos() {
		log.info("*** Recuperando todos los usuarios");
		return usuarioRepositorio.findAll();
	}

	/**
	 * Obtenemos un usuario en base a su ID
	 * 
	 * @param id
	 * @return Null si no encuentra el usuario
	 */
	@GetMapping("/usuario/{id}")
	public User obtenerUno(@PathVariable Long id) {
		log.info("*** Recuperando el usuario id=" + id);
		return usuarioRepositorio.findById(id).orElse(null);
	}

	/**
	 * Insertamos un nuevo usuario
	 * 
	 * @param nuevo
	 * @return usuario insertado
	 */
	@PostMapping("/usuario")
	public User nuevousuario(@RequestBody User nuevo) {
		log.info("*** Añadiendo el usuario " + nuevo.toString());
		return usuarioRepositorio.save(nuevo);
	}

	/**
	 * 
	 * @param editar
	 * @param id
	 * @return
	 */
	@PutMapping("/usuario/{id}")
	public User editarusuario(@RequestBody User editar, @PathVariable Long id) {
		log.info("*** Editando el usuario '" +editar.toString() + "' con id " + id);
		if (usuarioRepositorio.existsById(id)) {
			editar.setId(id);
			return usuarioRepositorio.save(editar);
		} else {
			return null;
		}
	}

	/**
	 * Borra un usuario del catálogo en base a su id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/usuario/{id}")
	public User borrarusuario(@PathVariable Long id) {
		log.info("*** Recuperando el usuario id=" + id);
		if (usuarioRepositorio.existsById(id)) {
			User result = usuarioRepositorio.findById(id).get();
			usuarioRepositorio.deleteById(id);
			return result;
		} else
			return null;
	}

}