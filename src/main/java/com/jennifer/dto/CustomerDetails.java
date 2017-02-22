package com.jennifer.dto;

import java.util.Collection;
import java.util.HashSet;

import com.jennifer.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Handles retrieval of user information when logging in as part of Spring Security
 */

public class CustomerDetails implements UserDetails {
	
	private UserInfo user;
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public CustomerDetails(UserInfo user){
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<>();

		if(user.getRole() == UserInfo.Role.MANAGER){
			authorities.add(new SimpleGrantedAuthority("ROLE_" + UserInfo.Role.MANAGER.toString()));
		}

		authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
