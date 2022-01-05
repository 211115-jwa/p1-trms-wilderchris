package com.revature.services;

import com.revature.beans.Employee;

public interface UserService {

	
		public Employee register(Employee newUser);// throws UsernameAlreadyExistsException;
		public Employee logIn(String username, String password);// throws IncorrectCredentialsException;
		public Employee getUserById(int id);
		public Employee updateUser(Employee empToUpdate);
		
	
}
