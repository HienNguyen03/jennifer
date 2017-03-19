package com.jennifer.service.impl;

import com.jennifer.dao.UserInfoDao;
import com.jennifer.dto.CustomerDetails;
import com.jennifer.dto.SignupForm;
import com.jennifer.entity.*;
import com.jennifer.service.FavoriteProductService;
import com.jennifer.service.ShoppingProductService;
import com.jennifer.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserInfo service implementation
 */

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    private UserInfoDao userInfoDao;
    private PasswordEncoder passwordEncoder;
    private FavoriteProductService favoriteProductService;
    private ShoppingProductService shoppingProductService;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao, PasswordEncoder passwordEncoder, FavoriteProductService favoriteProductService,
                               ShoppingProductService shoppingProductService) {
        this.userInfoDao = userInfoDao;
        this.passwordEncoder = passwordEncoder;
        this.favoriteProductService = favoriteProductService;
        this.shoppingProductService = shoppingProductService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByEmail(email);
        if (userInfo == null)
            throw new UsernameNotFoundException(email);

        mergeFavoriteBag(userInfo);
        mergeShoppingBag(userInfo);

        List<FavoriteProduct> favoriteProductList = favoriteProductService.findAllByUserId(userInfo.getId());
        List<ShoppingProduct> shoppingProductList = shoppingProductService.findAllByUserId(userInfo.getId());
        if(favoriteProductList.size() > 0 && shoppingProductList.size() > 0) {
            userInfo = userInfoDao.findByEmail1(email);
        } else if(favoriteProductList.size() > 0 && shoppingProductList.size() == 0) {
            userInfo = userInfoDao.findByEmail2(email);
        } else if(shoppingProductList.size() > 0 && favoriteProductList.size() == 0) {
            userInfo = userInfoDao.findByEmail3(email);
        }

        return new CustomerDetails(userInfo);
    }

    private void mergeFavoriteBag(UserInfo userInfo){
        List<ProductInfo> favoriteBag = (List<ProductInfo>) request.getSession().getAttribute("favoriteBag");

        if(favoriteBag == null)
            favoriteBag = new ArrayList<>();

        List<ProductInfo> aCopyOfFavoriteBag = new ArrayList<>(favoriteBag);
        List<ProductInfo> userFavoriteProducts = new ArrayList<>();

        List<FavoriteProduct> favoriteProductList = favoriteProductService.findAllByUserId(userInfo.getId());

        if(favoriteProductList != null && !favoriteProductList.isEmpty()){
            for (FavoriteProduct favoriteProduct : favoriteProductList){
                userFavoriteProducts.add(favoriteProduct.getProductInfo());
            }
            for (ProductInfo product : userFavoriteProducts){
                if(!favoriteBag.contains(product))
                    favoriteBag.add(product);
            }

            if(!favoriteBag.isEmpty()) {
                for (ProductInfo product : aCopyOfFavoriteBag) {
                    if (!userFavoriteProducts.contains(product)) {
                        FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, product);
                        favoriteProductService.update(favoriteProduct);
                    }
                }
            }
            request.getSession().setAttribute("favoriteBag", favoriteBag);
        } else {
            if(!favoriteBag.isEmpty()) {
                for (ProductInfo product : favoriteBag){
                    FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, product);
                    favoriteProductService.update(favoriteProduct);
                }
            }
        }
    }

    private void mergeShoppingBag(UserInfo userInfo){
        Map<ProductInfo, Integer> shoppingBag = (Map<ProductInfo, Integer>) request.getSession().getAttribute("shoppingBag");

        if(shoppingBag == null)
            shoppingBag = new HashMap<>();

        if(!shoppingProductService.findAllByUserId(userInfo.getId()).isEmpty()) {
            if(!shoppingBag.isEmpty()) {
                Map<ProductInfo, Integer> shoppingProductList = new HashMap<>();
                for (ShoppingProduct shoppingProduct : shoppingProductService.findAllByUserId(userInfo.getId())) {
                    shoppingProductList.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
                }

                for (Map.Entry<ProductInfo, Integer> entry : shoppingProductList.entrySet()) {
                    ProductInfo productInfo = entry.getKey();
                    if(shoppingBag.containsKey(productInfo)) {
                        shoppingBag.put(productInfo, shoppingBag.get(productInfo) + entry.getValue());
                    } else {
                        shoppingBag.put(productInfo, entry.getValue());
                    }
                }

                for (Map.Entry<ProductInfo, Integer> entry : shoppingBag.entrySet()) {
                    if(shoppingProductList.containsKey(entry.getKey())){
                        ShoppingProduct shoppingProduct1 = new ShoppingProduct(userInfo, entry.getKey(), entry.getValue());
                        shoppingProductService.update(shoppingProduct1);
                    } else {
                        ShoppingProduct shoppingProduct2 = new ShoppingProduct(userInfo, entry.getKey(), entry.getValue());
                        shoppingProductService.update(shoppingProduct2);
                    }
                }

            } else {
                for (ShoppingProduct shoppingProduct : shoppingProductService.findAllByUserId(userInfo.getId())){
                    shoppingBag.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
                }
            }
        } else {
            if(!shoppingBag.isEmpty()) {
                for (Map.Entry<ProductInfo, Integer> entry : shoppingBag.entrySet()) {
                    ShoppingProduct shoppingProduct = new ShoppingProduct(userInfo, entry.getKey(), entry.getValue());
                    shoppingProductService.update(shoppingProduct);
                }
            }
        }
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
    public UserInfo findById(int id) {
        return userInfoDao.findById(id);
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    @Override
    public boolean compareUserPassword(UserInfo userInfo, String newPassword) {
        UserInfo userInfoFound = userInfoDao.findById(userInfo.getId());
        return passwordEncoder.matches(newPassword,userInfoFound.getPassword());
    }

    @Override
    public UserInfo changePassword(UserInfo userInfo, String newPassword) {
        UserInfo userInfoFound = userInfoDao.findById(userInfo.getId());
        userInfoFound.setPassword(passwordEncoder.encode(newPassword));
        return userInfoDao.save(userInfoFound);
    }

}
