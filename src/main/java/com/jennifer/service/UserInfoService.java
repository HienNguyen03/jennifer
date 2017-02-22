package com.jennifer.service;

import com.jennifer.dto.SignupForm;

/**
 * Provided services for Customer
 */

public interface UserInfoService {
	
	boolean signup(SignupForm signupForm);
	//public User findByUsername(String username);
	//public boolean updateProfile(EditProfileForm editProfileForm);
	//public boolean checkUserPassword(String inputPassword);
	
}
