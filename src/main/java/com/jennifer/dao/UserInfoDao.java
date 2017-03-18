package com.jennifer.dao;

import com.jennifer.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserInfo data access object interface
 */

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

    UserInfo findById(int id);

    @Query("from UserInfo u left join fetch u.favoriteProducts join fetch u.shoppingProducts where u.email like ?1")
    UserInfo findByEmail1(String email);

    @Query("from UserInfo u left join fetch u.favoriteProducts where u.email like ?1")
    UserInfo findByEmail2(String email);

    @Query("from UserInfo u left join fetch u.shoppingProducts where u.email like ?1")
    UserInfo findByEmail3(String email);

    @Query("from UserInfo u where u.email like ?1")
    UserInfo findByEmail(String email);

    List<UserInfo> findAll();

}
