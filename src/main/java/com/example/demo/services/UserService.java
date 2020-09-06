package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

//	@Autowired
//	PasswordEncoder passwordEncoder;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	/**
	 * 
	 * @return a list of users present in database using User repository
	 */
	
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();

		List<String> userRolesList = new ArrayList<String>();

		java.util.ListIterator<User> it = users.listIterator();

		while (it.hasNext()) {
			User user = it.next();
			user.getAuthorities().forEach(a -> userRolesList.add(a.getAuthority()));
			user.setUserRoles(String.join(",", userRolesList));
			userRolesList.clear();
			users.set(it.previousIndex(), user);
		}
		
		return users;
	}

	
	/**
	 * 
	 * @param user id
	 * @return user details for given id
	 */
	public User getUserById(int id) {
		try {
			User user = userRepository.findById(id).get();
			return user;
		}catch(NoSuchElementException e) {
			return null;
		}catch(NullPointerException e) {
			return null;
		}catch(NumberFormatException e) {
			return null;
		}
	}

	
	
	/**
	 * 
	 * @param username
	 * @return user details based on given username
	 */
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	
	
	/**
	 * 
	 * @param email
	 * @return user details based on given email id
	 */
	public User getUserByEmail(String email) {
		return userRepository.findByEmailId(email);
	}

	
	
	/**
	 * 
	 * @param employeeCode
	 * @return user details based on given employee code
	 */
	public User getUserByEmpCode(String employeeCode) {
		return userRepository.findByEmployeeCode(employeeCode);
	}

	
	
	/**
	 * 
	 * @param a new user details
	 * @return user id after adding this new user details in database
	 */
	public int insertUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.getAuthorities().forEach(u -> u.setUser(user));
		userRepository.save(user);
		return user.getId();
	}

	
	
	/**
	 * 
	 * @param user details that needs to be update
	 * @return returns true if new user details updates successfully
	 */
	public boolean updateUser(User user) {
		User checkUser = getUserById(user.getId());
		if (passwordEncoder.matches(user.getPassword(), checkUser.getPassword())) {
			user.setPassword(checkUser.getPassword());
			user.getAuthorities().forEach(u -> u.setUser(user));
			userRepository.save(user);
		} else
			return false;
		return true;
	}

	
	
	/**
	 * 
	 * @param it takes user id and deletes user details from database for given user id
	 */
	public boolean deleteUser(int id) {
		userRepository.deleteById(id);
		return true;
	}

}
