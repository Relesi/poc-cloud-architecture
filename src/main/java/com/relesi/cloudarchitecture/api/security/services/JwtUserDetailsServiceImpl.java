package com.relesi.cloudarchitecture.api.security.services;

import java.util.Optional;

import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.security.JwtUserFactory;
import com.relesi.cloudarchitecture.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> employee = employeeService.searchByEmail(username);

		if (employee.isPresent()) {
			return JwtUserFactory.create(employee.get());
		}

		throw new UsernameNotFoundException("Email not found.");
	}

}
