package com.cognizant.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.userservice.UserserviceApplication;
import com.cognizant.userservice.model.User;
import com.cognizant.userservice.model.User.Role;
import com.cognizant.userservice.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public User update(Long id, User user) {
		return userRepository.save(user);
	}

	public User save(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password -> B Encrypt
		if (user.getRole() == null) {
			user.setRole(Role.USER); // Default role if not provided
		}

		return userRepository.save(user);
	}

}
