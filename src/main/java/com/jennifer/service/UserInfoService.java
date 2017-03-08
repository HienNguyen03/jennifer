package com.jennifer.service;

import com.jennifer.dto.SignupForm;
import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.UserInfo;

import java.util.List;

/**
 * Provided services for Customer
 */

public interface UserInfoService {
	
	boolean signup(SignupForm signupForm);
	UserInfo findById(int id);
	//public boolean updateProfile(EditProfileForm editProfileForm);
	//public boolean checkUserPassword(String inputPassword);
	UserInfo updateUser(UserInfo userInfo);
	boolean compareUserPassword(UserInfo userInfo,String newPassword);
	UserInfo changePassword(UserInfo userInfo, String newPassword);

	//List<ProductInfo> addProductToFavorite(ProductInfo productInfo);
	
}
