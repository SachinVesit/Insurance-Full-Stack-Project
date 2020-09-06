package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JsonViews.UserView;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @return all the user details present in database
	 */
	@JsonView(UserView.class)
	@GetMapping("/get_all_users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * 
	 * @param takes user id
	 * @return user details based on given user id
	 */
	@GetMapping("/user_id")
	public User getUserById(@RequestParam("id") int id) {
		return userService.getUserById(id);
	}

	/**
	 * 
	 * @param username
	 * @return user details based on given username
	 */
	@GetMapping("/username")
	public ApiResponse<User> getUserByUsername(@RequestParam("username") String username) {
		return new ApiResponse<>(HttpStatus.OK.value(), " ", userService.getUserByUsername(username));
	}

	/**
	 * 
	 * @param email
	 * @return user details based on given email id
	 */
	@GetMapping("/email")
	public ApiResponse<User> getUserByEmail(@RequestParam("email") String email) {
		return new ApiResponse<>(HttpStatus.OK.value(), " ", userService.getUserByEmail(email));
	}

	/**
	 * 
	 * @param employeeCode
	 * @return user details based on given employee code
	 */
	@GetMapping("/emp_code")
	public ApiResponse<User> getUserByEmpCode(@RequestParam("employeeCode") String employeeCode) {
		return new ApiResponse<>(HttpStatus.OK.value(), " ", userService.getUserByEmpCode(employeeCode));
	}

	/**
	 * 
	 * @param a new user details
	 * @return user id after adding this new user details in database
	 */
	@RequestMapping(value = "/add_new_user", method = { RequestMethod.POST, RequestMethod.PUT })
	public ApiResponse<User> insertUser(@RequestBody User user) {
		return new ApiResponse<>(HttpStatus.OK.value(), "New user added successfully!!!", userService.insertUser(user));
	}

	/**
	 * 
	 * @param user details that needs to be update
	 * @return returns true if new user details updates successfully
	 */
	@RequestMapping(value = "/update_user_details", method = { RequestMethod.POST })
	public ApiResponse<User> updateUser(@RequestBody User user) {
		return new ApiResponse<>(HttpStatus.OK.value(), "", userService.updateUser(user));
	}

	/**
	 * 
	 * @param it takes user id and deletes user details from database for given user
	 *           id
	 */
	@DeleteMapping("/delete_user")
	public ApiResponse<User> deleteUser(@RequestParam("id") int id) {
		return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully", userService.deleteUser(id));
	}
	
	/**
	 * returns current logged in username
	 */
	@GetMapping("/get_userrole")
	public ApiResponse<String> getUsername(){
		String userRole = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities().get(0).getAuthority();
		return new ApiResponse<>(HttpStatus.OK.value(), "logged in username", userRole);
	}
}
