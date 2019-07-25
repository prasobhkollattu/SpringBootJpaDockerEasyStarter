package com.opensourzesupport.bootapp.contollers;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opensourzesupport.bootapp.dao.entity.UserRecord;
import com.opensourzesupport.bootapp.exceptions.DataNotFoundException;
import com.opensourzesupport.bootapp.exceptions.ForbiddenException;
import com.opensourzesupport.bootapp.services.UserService;

/**
 * controller to handle User create/select/update and delete
 * 
 * @author pkollattu
 *
 */
@RestController
public class UsersController {
	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public List<UserRecord> getAllUser(HttpServletResponse res) {
		List<UserRecord> users = userService.getAllUsers();
		if (users == null || users.isEmpty()) {
			throw new DataNotFoundException("Users not found");
		}
		return users;
	}

	@PostMapping(value = "/user")
	public void createUser(@RequestBody UserRecord userRecord, HttpServletResponse res) throws IOException {
		userService.addUser(userRecord);
		// custom errorcode and message
		res.sendError(201, "User created successfully");
	}

	@PutMapping(value = "/user")
	public void updateUser(@RequestBody UserRecord userRecord, HttpServletResponse res) throws IOException {

		Optional.ofNullable(userRecord).ifPresent(ur -> {

			try {
				UserRecord userRecordFromDB = userService.getUser(ur.getId()).get();
				userRecordFromDB.setEmail(userRecord.getEmail());
				userService.addUser(userRecordFromDB);
				res.sendError(200, "User updated successfully");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EmptyResultDataAccessException | NoSuchElementException e) {
				throw new DataNotFoundException("User not found");
			}
			return;
		});
	}

	@GetMapping(value = "/user/{id}")
	public Optional<UserRecord> getUser(@PathVariable Integer id) {

		Optional<UserRecord> user = userService.getUser(id);
		if (!user.isPresent()) {
			throw new DataNotFoundException("User not found");
		}
		return user;
	}

	@GetMapping("/auth")
	public void unauthorized() {
		// exception scenario test
		throw new ForbiddenException("No rights buddy");
	}

	@DeleteMapping(value = "/user/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletResponse res) throws IOException {
		try {
			userService.delete(id);
			res.sendError(200, "User deleted successfully");
		} catch (EmptyResultDataAccessException exp) {
			throw new DataNotFoundException("User not found");
		}

	}
}
