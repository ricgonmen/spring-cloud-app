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

import com.ricgonmen.ms_user.rest.dto.CreateUserDTO;
import com.ricgonmen.ms_user.rest.dto.UserDTO;
import com.ricgonmen.ms_user.rest.dto.converter.UserDTOConverter;
import com.ricgonmen.ms_user.rest.excepcion.UserNotFoundException;
import com.ricgonmen.ms_user.rest.model.User;
import com.ricgonmen.ms_user.rest.model.UserRepository;

import io.swagger.annotations.ApiOperation;
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
	@ApiOperation(value="Return the list of all users", notes="")
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
	@ApiOperation(value="Return a single user", notes="")
	@GetMapping("/user/{username}")
	public UserDTO obtenerUnoPorUsername(@PathVariable String username) {
		log.info("*** Recuperando el usuario username=" + username);
		return usuarioDTOConverter.convertToDto(usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
	}

	/**
	 * Insertamos un nuevo usuario
	 * /api/user/ (POST): create a user.
	 * @param nuevo
	 * @return 201 y el producto insertado
	 * TODO: ¿Excepción en saved?
	 */
	@ApiOperation(value="Create a user", notes="")
	@PostMapping("/user")
	public ResponseEntity<?> nuevousuario(@RequestBody CreateUserDTO newUser) {
		log.info("*** Añadiendo el usuario " + newUser.toString());
				
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepositorio.save(new User(newUser)));
	}
	
	/**
	 * /api/user/{username}/ (PUT): update the information of a single user.
	 * @param editar
	 * @param username
	 * @return
	 */
	@ApiOperation(value="Update the information of a single user", notes="")
	@PutMapping("/user/{username}")
	public User editarusuarioPorUsername(@RequestBody User currentUser, @PathVariable String username) {
		log.info("*** Editando el usuario '" +currentUser.toString() + "' con username " + username);
		
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

		if (user != null) {
			currentUser.setId(user.getId());
			return usuarioRepositorio.save(currentUser);
		} else {
			return null;
		}
		
	}

	
	/**
	 * Borra un usuario del catálogo en base a su username
	 * /api/user/{username}/ (DELETE): delete a single user.
	 * @param id
	 * @return
	 */
	@ApiOperation(value="Delete a single user", notes="")
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
	@ApiOperation(value="Generate a number, provided as a parameter, of random users", notes="To create the users you have to use the Random User Generator service. Users\r\n"
			+ "	will be added to the collection of existing users.")
	@GetMapping("/user/generate/{number}")
	public ResponseEntity<?> generarUsuariosRandom(@PathVariable Long numberUsersToCreate) {
		log.info("*** Creando " + numberUsersToCreate + " usuarios aleatorios.");
	
		List<User> result = new ArrayList<User>();
		
		for (int i=0;i<numberUsersToCreate;i++) {
			User randomUser = new User(new CreateUserDTO(true));
			result.add(randomUser);
			usuarioRepositorio.save(randomUser);
		}
		
		return ResponseEntity.ok(result);
	}
}
