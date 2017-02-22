package com.jennifer.dao;

import com.jennifer.entity.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * UserInfo data access object interface
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

    UserInfo findById(int id);
    UserInfo findByEmail(String email);
    List<UserInfo> findAll();

}
