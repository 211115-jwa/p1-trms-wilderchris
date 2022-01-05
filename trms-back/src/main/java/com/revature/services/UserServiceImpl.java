package com.revature.services;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.utils.DAOFactory;

public class UserServiceImpl implements UserService {
	private EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
	
	
	@Override
	public Employee register(Employee newEmp) {
		int newId = empDAO.create(newEmp);
		if (newId > 0) {
			newEmp.setEmpId(newId);
			return newEmp;
		} else if (newId == -1) {
			//throw new UsernameAlreadyExistsException();
		}
		return null;

	}

	@Override
	public Employee logIn(String username, String password) {
		Employee emp = empDAO.getByUsername(username);
		if (emp != null && emp.getPassword().equals(password)) {
			return emp;
		} else {
			//throw new IncorrectCredentialsException();
		}
		return null;
	}


	@Override
	public Employee getUserById(int id) {

		return empDAO.getById(id);
	}

	@Override
	public Employee updateUser(Employee empToUpdate) {
		if (empDAO.getById(empToUpdate.getEmpId()) != null) {
			empDAO.update(empToUpdate);
			empToUpdate = empDAO.getById(empToUpdate.getEmpId());
			return empToUpdate;
		}
		return null;
	}

}
