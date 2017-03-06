package com.jennifer.service;

import com.jennifer.dto.SignupForm;
import com.jennifer.entity.UserInfo;

/**
 * Provided services for Customer
 */

public interface UserInfoService {
	
	boolean signup(SignupForm signupForm);
	//public User findByUsername(String username);
	//public boolean updateProfile(EditProfileForm editProfileForm);
	//public boolean checkUserPassword(String inputPassword);
	UserInfo updateUser(UserInfo userInfo);
	boolean compareUserPassword(UserInfo userInfo,String newPassword);
	UserInfo changePassword(UserInfo userInfo, String newPassword);


	
}
