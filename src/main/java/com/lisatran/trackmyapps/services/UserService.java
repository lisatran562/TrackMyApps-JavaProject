package com.lisatran.trackmyapps.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.lisatran.trackmyapps.models.LoginUser;
import com.lisatran.trackmyapps.models.User;
import com.lisatran.trackmyapps.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());

		if (optionalUser.isPresent()) {
			result.rejectValue("email", "unique", "This email is already taken");

		}

		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "matches", "The passwords do not match");
		}

		if (result.hasErrors()) {
			return null;
		}

		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepository.save(newUser);
	}

	public User login(LoginUser newLoginObject, BindingResult result) {

	Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());

	if (!potentialUser.isPresent()) {
		result.rejectValue("email", "unique", "This email is not registered");
		return null;
	}

	User user = potentialUser.get();

	if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
		result.rejectValue("password", "matches", "Invalid password");
	}

	if (result.hasErrors()) {
		return null;
	}

	return user;
	}

	public User oneUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	
	public List<User> allUsers() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> potentialUser = userRepository.findById(id);
		if(potentialUser.isPresent()) {
			return potentialUser.get();
		}
		return null;
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	
	}
}
