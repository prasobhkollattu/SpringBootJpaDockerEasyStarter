package com.opensourzesupport.bootapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensourzesupport.bootapp.dao.UserRepository;
import com.opensourzesupport.bootapp.dao.entity.UserRecord;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<UserRecord> getAllUsers() {
		List<UserRecord> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public Optional<UserRecord> getUser(Integer id) {
		return userRepository.findById(id);
	}

	public void addUser(UserRecord userRecord) {
		userRepository.save(userRecord);
	}

	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	public UserRecord getUserByName(String name) {
		return userRepository.findByName(name);
	}
}
