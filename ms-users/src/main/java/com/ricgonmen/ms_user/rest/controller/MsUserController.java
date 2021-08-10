/*
 * 
 	The application will provide the following JSON web services:
	/api/user/ (GET): return the list of all users.
	/api/user/{username}/ (GET): return a single user.
	/api/user/ (POST): create a user.
	/api/user/{username}/ (PUT): update the information of a single user.
	/api/user/{username}/ (DELETE): delete a single user.
	/api/user/generate/{number}/ (GET): generate a number, provided as a parameter, of random users.
		To create the users you have to use the Random User Generator service. Users
		will be added to the collection of existing users.
	
 */

// Mirar en https://github.com/javaquery/spring-boot-examples
// https://github.com/OpenWebinarsNet/spring-rest-apis


package com.ricgonmen.ms_user.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.dto.converter.UserDTOConverter;
import com.ricgonmen.ms_user.rest.excepcion.UserNotFoundException;
import com.ricgonmen.ms_user.rest.model.User;
import com.ricgonmen.ms_user.rest.model.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MsUserController {
	private final UserRepository usuarioRepositorio;
	private final UserDTOConverter usuarioDTOConverter;
	
	/**
	 * Obtenemos todos los usuarios
	 * /api/user/ (GET): return the list of all users.
	 * @return 404 si no hay usuarios, 200 y lista de usuarios si hay uno o máss
	 */
	@GetMapping("/user")
	public ResponseEntity<?> obtenerTodos() {
		log.info("*** Recuperando todos los usuarios");
		
		List<User> result = usuarioRepositorio.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			List<UserDTO> dtoResult = result.stream().map(usuarioDTOConverter::convertToDto)
					.collect(Collectors.toList());

			return ResponseEntity.ok(dtoResult);
		}
		
	}

	/**
	 * Obtenemos un usuario en base a su username
	 * /api/user/{username}/ (GET): return a single user.
	 * @param username
	 * @return 404 si no encuentra el usuario, 200 y el usuario si lo encuentra
	 */
	@GetMapping("/user/{username}")
	public ResponseEntity<?> obtenerUnoPorUsername(@PathVariable String username) {
		log.info("*** Recuperando el usuario username=" + username);
		User result = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		if (result == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(result);
	}

	/**
	 * Insertamos un nuevo usuario
	 * /api/user/ (POST): create a user.
	 * @param nuevo
	 * @return 201 y el producto insertado
	 * TODO: ¿Excepción en saved?
	 */
	@PostMapping("/user")
	public ResponseEntity<?> nuevousuario(@RequestBody User nuevo) {
		log.info("*** Añadiendo el usuario " + nuevo.toString());
		
		User usuario = usuarioRepositorio.save(nuevo);
		
		if (usuario!=null)
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		else
			return ResponseEntity.badRequest().body(null);
	}
	
	/**
	 * /api/user/{username}/ (PUT): update the information of a single user.
	 * @param editar
	 * @param username
	 * @return
	 */
	@PutMapping("/user/{username}")
	public User editarusuarioPorUsername(@RequestBody User editar, @PathVariable String username) {
		log.info("*** Editando el usuario '" +editar.toString() + "' con username " + username);
		
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

		if (user != null) {
			editar.setId(user.getId());
			return usuarioRepositorio.save(editar);
		} else {
			return null;
		}
		
		/*
		 * Alternativa
		 * 
		 * 
		 * return productoRepositorio.findById(id).map(p -> {
			p.setNombre(editar.getNombre());
			p.setPrecio(editar.getPrecio());
			return ResponseEntity.ok(productoRepositorio.save(p));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
		 */
	}

	
	/**
	 * Borra un usuario del catálogo en base a su username
	 * /api/user/{username}/ (DELETE): delete a single user.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/user/{username}")
	public ResponseEntity<?> borrarusuarioPorUsername(@PathVariable String username) {
		log.info("*** Borrando el usuario username=" + username);
		
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

		if (user != null) {
			usuarioRepositorio.deleteById(user.getId());
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.badRequest().body(null);
	}

	/**
	 * /api/user/generate/{number}/ (GET): generate a number, provided as a parameter, of random users.
	To create the users you have to use the Random User Generator service. Users
	will be added to the collection of existing users.
	 * @param username
	 * @return 200 y lista de n usuarios generados
	 */
	@GetMapping("/user/generate/{number}")
	public ResponseEntity<?> generarUsuariosRandom(@PathVariable Long number) {
		log.info("*** Creando " + number + " usuarios aleatorios.");
		// return usuarioRepositorio.findById(username).orElse(null);
		
		List<User> result = new ArrayList<User>();
		
		for (int i=0;i<number;i++) {
			User randomUser = new User(true);
			result.add(randomUser);
			usuarioRepositorio.save(randomUser);
		}
		
		return ResponseEntity.ok(result);
	}
}
