package com.jennifer.controller.rest;

import com.jennifer.entity.FavoriteProduct;
import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.FavoriteProductService;
import com.jennifer.service.ProductInfoService;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Rest API Controller for ProductInfo
 */

@RestController
@RequestMapping("/api/favorite-product")
@SessionAttributes({"favoriteBag", "favoriteBagAnchor"})
public class RestFavoriteProductController {

    private static final Logger log = LoggerFactory.getLogger(RestFavoriteProductController.class);
    private ProductInfoService productInfoService;
    private FavoriteProductService favoriteProductService;
    private UserInfoService userInfoService;

    @Autowired
    public RestFavoriteProductController(ProductInfoService productInfoService, FavoriteProductService favoriteProductService, UserInfoService userInfoService){
        this.productInfoService = productInfoService;
        this.favoriteProductService = favoriteProductService;
        this.userInfoService = userInfoService;
    }

    @ModelAttribute("favoriteBagAnchor")
    public FavoriteProductService createFavoriteBagAnchor(){
        return favoriteProductService;
    }

    @ModelAttribute("favoriteBag")
    public static List<ProductInfo> createFavoriteBag(){
        log.info("create favorite bag !");
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        if(userInfo != null) {
            List<ProductInfo> productInfoList = new ArrayList<>();
            List<FavoriteProduct> favoriteProductList = userInfo.getFavoriteProducts();
            for(FavoriteProduct favoriteProduct : favoriteProductList){
                productInfoList.add(favoriteProduct.getProductInfo());
            }
            return productInfoList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * In below method, 'favoriteBag' is the reference-variable of [userInfo returned by AppUtil.getCustomerFromSession()]
     * so favoriteBag.add() has the same result as userInfo.getProductInfos.add()
     * */
    @PutMapping("/{productId}")
    public Object addProductToFavorite(@ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag, @PathVariable int productId) {
        log.info(" > [rest] Favorite Product - addProductToFavorite");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ProductInfo productInfo = productInfoService.findProduct(productId);

        if(favoriteBag.contains(productInfo)){
            return new ResponseEntity<>("The product was added to your favorite!", HttpStatus.OK);
        } else {
            if(userInfo != null) {
                // add to favorite bag - database
                FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, productInfo);
                favoriteProductService.update(favoriteProduct);

                // add to favorite bag - session
                favoriteBag.add(productInfo);
                return favoriteBag.size();
            } else {
                favoriteBag.add(productInfo);
                return favoriteBag.size();
            }
        }
    }

    @DeleteMapping("/{productId}")
    public Object deleteProductToFavorite(@ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag, @PathVariable int productId) {
        log.info(" > [rest] Favorite Product - deleteProductToFavorite");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        log.info("."+productId);

        if(userInfo != null){
//            FavoriteProduct delFavoriteProduct = favoriteProductService.findByUserIdAndProductId(userInfo.getId(), productId);
//            favoriteProductService.delete(delFavoriteProduct);
//
//            for (Iterator<ProductInfo> iter = favoriteBag.listIterator(); iter.hasNext(); ) {
//                ProductInfo productInfo = iter.next();
//                if (productInfo.getId() == productId) {
//                    iter.remove();
//                }
//            }
//
//            log.info("user "+ favoriteBag.size());


            ProductInfo productInfo = productInfoService.findProduct(productId);
            favoriteBag.remove(productInfo);
            FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, productInfo);
            favoriteProductService.delete(favoriteProduct);
            userInfo.getFavoriteProducts().remove(favoriteProduct);
            //productInfo.getFavoriteProducts().remove(userInfo);
            userInfoService.updateUser(userInfo);

            return favoriteBag.size();

        }else{
            for (Iterator<ProductInfo> iter = favoriteBag.listIterator(); iter.hasNext(); ) {
                ProductInfo productInfo = iter.next();
                if (productInfo.getId() == productId) {
                    iter.remove();
                }
            }

            log.info("bag size "+ favoriteBag.size());
            return favoriteBag.size();
        }
    }

//    @GetMapping
//    public Object findAll() throws JsonProcessingException {
//        log.info(" > [rest] Product - findAll");
//        List<ProductInfo> productInfoList = productInfoService.findAllProducts();
//        if (productInfoList.isEmpty()) {
//            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(List.class, new ProductInfoSerializer());
//        mapper.registerModule(module);
//
//        return mapper.writeValueAsString(productInfoList);
//    }
//
//    @PutMapping
//    public Object update(@RequestBody ProductInfo productInfo){
//        log.info(" > [rest] Product - update");
//        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());
//
//        if(productInfoData != null)
//            return productInfoService.updateProduct(productInfo);
//
//        return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @DeleteMapping
//    public Object delete(@RequestBody ProductInfo productInfo){
//        log.info(" > [rest] Product - delete");
//        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());
//
//        try {
//            if(productInfoData != null)
//                productInfoService.deleteProduct(productInfo);
//            return productInfoData;
//        } catch (DataIntegrityViolationException e){
//            return new ResponseEntity<>("Product '" + productInfoData.getName() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
//        } catch (Exception e){
//            return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping
//    public ProductInfo insert(@RequestBody ProductInfo productInfo){
//        log.info(" > [rest] Product - insert");
//        return productInfoService.addProduct(productInfo);
//    }

}
