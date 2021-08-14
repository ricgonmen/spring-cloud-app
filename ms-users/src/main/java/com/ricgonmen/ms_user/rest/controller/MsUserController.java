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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ricgonmen.ms_user.dto.CreateUserDTO;
import com.ricgonmen.ms_user.dto.UserDTO;
import com.ricgonmen.ms_user.model.User;
import com.ricgonmen.ms_user.rest.excepcion.ApiError;
import com.ricgonmen.ms_user.service.MsUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MsUserController {

	@Autowired
	private MsUserService userService;

	/**
	 * /api/user/ (GET): return the list of all users.
	 * 
	 * @return 404 si no hay usuarios, 200 y lista de usuarios si hay uno o máss
	 */
	@ApiOperation(value = "Return the list of all users", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@GetMapping("/api/user")
	public ResponseEntity<?> obtenerTodos() {
		log.info("*** Recuperando todos los usuarios");

		List<UserDTO> result = userService.getUsers();

		return ResponseEntity.ok(result);

	}

	/**
	 * /api/puser/{page=?&size=?} (GET): return the list of all users (paginatged).
	 * 
	 * @return 404 si no hay usuarios, 200 y lista de usuarios si hay uno o máss
	 */
	@ApiOperation(value = "Return the list of all users (paginated)", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@GetMapping("/api/puser")
	public ResponseEntity<Map<String, Object>> obtenerTodosPaginados(
			@ApiParam(value = "Page number (default 0)", required = false, type = "int") @RequestParam(defaultValue = "0") int page,
			@ApiParam(value = "Size of page (default 3)", required = false, type = "int") @RequestParam(defaultValue = "3") int size) {
		log.info("*** Recuperando el pagina =" + page + " de tamaño " + size);

		Pageable paging = PageRequest.of(page, size);

		Page<User> pageUsers = userService.getUsers(paging);

		Map<String, Object> response = new HashMap<>();
		response.put("users", pageUsers.getContent());
		response.put("currentPage", pageUsers.getNumber());
		response.put("totalItems", pageUsers.getTotalElements());
		response.put("totalPages", pageUsers.getTotalPages());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * /api/user/{username}/ (GET): return a single user.
	 * 
	 * @param username
	 * @return 404 si no encuentra el usuario, 200 y el usuario si lo encuentra
	 */
	@ApiOperation(value = "Return a single user", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@GetMapping("/api/user/{username}")
	public UserDTO obtenerUnoPorUsername(
			@ApiParam(value = "Username key", required = true, type = "string") @PathVariable String username) {
		log.info("*** Recuperando el usuario username=" + username);
		return userService.getUser(username);
	}

	/**
	 * /api/user/ (POST): create a user.
	 * 
	 * @param nuevo
	 * @return 201 y el producto insertado 
	 */
	@ApiOperation(value = "Create a user", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 422, message = "Unprocessable Entity", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@PostMapping("/api/user")
	public ResponseEntity<?> nuevousuario(
			@ApiParam(value = "User JSON", required = true, type = "application/json") @RequestBody CreateUserDTO newUser) {
		log.info("*** Añadiendo el usuario " + newUser.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(newUser));
	}

	/**
	 * /api/user/{username}/ (PUT): update the information of a single user.
	 * 
	 * @param editar
	 * @param username
	 * @return
	 */
	@ApiOperation(value = "Update the information of a single user", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@PutMapping("/api/user/{username}")
	public UserDTO editarusuarioPorUsername(@RequestBody UserDTO newUserData,
			@ApiParam(value = "Username key", required = true, type = "string") @PathVariable String username) {
		log.info("*** Actualizando el usuario con username '" + username + "' a los datos " + newUserData.toString());

		return userService.updateUser(newUserData, username);
	}

	/**
	 * Borra un usuario del catálogo en base a su username /api/user/{username}/
	 * (DELETE): delete a single user.
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Delete a single user", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@DeleteMapping("/api/user/{username}")
	public ResponseEntity<?> borrarusuarioPorUsername(
			@ApiParam(value = "Username key", required = true, type = "string") @PathVariable String username) {
		log.info("*** Borrando el usuario username=" + username);

		userService.deleteUser(username);

		return ResponseEntity.ok().build();
	}

	/**
	 * /api/user/generate/{number}/ (GET): generate a number, provided as a
	 * parameter, of random users. To create the users you have to use the Random
	 * User Generator service. Users will be added to the collection of existing
	 * users.
	 * 
	 * @param username
	 * @return 200 y lista de n usuarios generados
	 */
	@ApiOperation(value = "Generate a number, provided as a parameter, of random users", notes = "Users\r\n"
			+ " will be added to the collection of existing users.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@GetMapping("/api/user/generate/{number}")
	public ResponseEntity<?> generarUsuariosRandom(
			@ApiParam(value = "Number of users to generate", required = true, type = "Long") @PathVariable Long number) {
		log.info("*** Creando " + number + " usuarios aleatorios.");

		return ResponseEntity.ok(userService.addRandomUsers(number));
	}
}
