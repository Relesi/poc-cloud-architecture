package com.relesi.cloudarchitecture.api.security;

import java.util.ArrayList;
import java.util.List;

import com.relesi.cloudarchitecture.api.entities.Employee;
import com.relesi.cloudarchitecture.api.enums.ProfileEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converts and generates a JwtUser based on an employee's data.
	 * 
	 * @param employee
	 * @return JwtUser
	 */
	public static JwtUser create(Employee employee) {
		return new JwtUser(employee.getId(), employee.getEmail(), employee.getPassword(),
				mapToGrantedAuthorities(employee.getProfile()));
	}

	/**
	 * Converts the user profile to the format used by Spring Security.
	 * 
	 * @param profileEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authorities;
	}

}
