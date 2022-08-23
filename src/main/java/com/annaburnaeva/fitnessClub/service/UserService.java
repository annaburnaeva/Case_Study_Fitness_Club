package com.annaburnaeva.fitnessClub.service;

import com.annaburnaeva.fitnessClub.entity.User;
import com.annaburnaeva.fitnessClub.security.UserRegistrationDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	User findByEmail(String email);

	User save(UserRegistrationDto registration);

	User getCurrentlyLoggedInUser(Authentication authentication);
}