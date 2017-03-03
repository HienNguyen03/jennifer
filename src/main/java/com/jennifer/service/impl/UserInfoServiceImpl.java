package com.jennifer.service.impl;

import com.jennifer.dao.UserInfoDao;
import com.jennifer.dto.CustomerDetails;
import com.jennifer.dto.SignupForm;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserInfo service implementation
 */

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    private UserInfoDao userInfoDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao, PasswordEncoder passwordEncoder) {
        this.userInfoDao = userInfoDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByEmail(email);

        if (userInfo == null)
            throw new UsernameNotFoundException(email);

        return new CustomerDetails(userInfo);
    }

    @Override
    @Transactional
    public boolean signup(SignupForm signupForm) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFullname(signupForm.getFullname());
        userInfo.setEmail(signupForm.getEmail());
        userInfo.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        userInfo.setRole(UserInfo.Role.CUSTOMER);

        userInfoDao.save(userInfo);
        return true;
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }
}
