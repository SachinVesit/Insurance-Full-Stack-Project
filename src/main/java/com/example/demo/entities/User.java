package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.JsonViews.UserView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "users_new")
public class User extends AbstractEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@JsonIgnore
	@JsonView(UserView.class)
	private String password;
	
	@JsonView(UserView.class)
	private String username;
	
	@JsonView(UserView.class)
	private String emailId;
	
	@JsonView(UserView.class)
	private String employeeCode;
	
	@JsonView(UserView.class)
	private String department;
	
	@JsonView(UserView.class)
	private String fullName;
	
	@JsonView(UserView.class)
	@Transient
	private String userRoles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true ,fetch = FetchType.EAGER)
	@JsonView(UserView.class)
	private List<UserRole> authorities;
	
	

	public User(String password, String username, String emailId, String employeeCode, String department,
			String fullName, List<UserRole> authorities) {
		super();
		this.password = password;
		this.username = username;
		this.emailId = emailId;
		this.employeeCode = employeeCode;
		this.department = department;
		this.fullName = fullName;
		this.authorities = authorities;
	}
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public User(String password, String username, List<UserRole> authorities) {
		super();
		this.password = password;
		this.username = username;
		this.authorities = authorities;
	}

	public User() {
		super();
	}

	@Override
	public List<UserRole> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<UserRole> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
