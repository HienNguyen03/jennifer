package com.jennifer.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jennifer.entity.UserInfo;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);

		//UserInfo userInfo = ((CustomerDetails) authentication.getPrincipal()).getUser();
		//mergeFavoriteBag(userInfo, request);
		//mergeShoppingBag(userInfo, request);
	}

//	private void mergeFavoriteBag(UserInfo userInfo, HttpServletRequest request){
//		List<ProductInfo> favoriteBag = (List<ProductInfo>) request.getSession().getAttribute("favoriteBag");
//		FavoriteProductService favoriteProductService = (FavoriteProductService) request.getSession().getAttribute("favoriteBagAnchor");
//
//		if(favoriteBag == null)
//			favoriteBag = new ArrayList<>();
//
//		List<ProductInfo> aCopyOfFavoriteBag = new ArrayList<>(favoriteBag);
//		List<ProductInfo> userFavoriteProducts = new ArrayList<>();
//
//		log.info("userId: "+userInfo.getId());
//		log.info("favoriteBag.size: "+favoriteBag.size());
//		List<FavoriteProduct> favoriteProductList = favoriteProductDao.findAllByUserId(userInfo.getId());
//
//		if(favoriteProductList != null && !favoriteProductList.isEmpty()){
//			for (FavoriteProduct favoriteProduct : userInfo.getFavoriteProducts()){
//				userFavoriteProducts.add(favoriteProduct.getProductInfo());
//			}
//			for (ProductInfo product : userFavoriteProducts){
//				if(!favoriteBag.contains(product))
//					favoriteBag.add(product);
//			}
//
//			if(!favoriteBag.isEmpty()) {
//				for (ProductInfo product : aCopyOfFavoriteBag) {
//					if (!userFavoriteProducts.contains(product)) {
//						FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, product);
//						favoriteProductService.update(favoriteProduct);
//					}
//				}
//			}
//
//			request.getSession().setAttribute("favoriteBag", favoriteBag);
//		} else {
//			if(!favoriteBag.isEmpty()) {
//				for (ProductInfo product : favoriteBag){
//					FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, product);
//					favoriteProductService.update(favoriteProduct);
//				}
//			}
//		}
//	}

//	private void mergeShoppingBag(UserInfo userInfo, HttpServletRequest request){
//		Map<ProductInfo, Integer> shoppingBag = (Map<ProductInfo, Integer>) request.getSession().getAttribute("shoppingBag");
//		ShoppingProductService shoppingProductService = (ShoppingProductService) request.getSession().getAttribute("shoppingBagAnchor");
//
//		if(shoppingBag == null)
//			shoppingBag = new HashMap<>();
//
//		if(!shoppingProductService.findAllByUserId(userInfo.getId()).isEmpty()) {
//			if(!shoppingBag.isEmpty()) {
//				Map<ProductInfo, Integer> shoppingProductList = new HashMap<>();
//				for (ShoppingProduct shoppingProduct : shoppingProductService.findAllByUserId(userInfo.getId())) {
//					shoppingProductList.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
//				}
//
//				for (Map.Entry<ProductInfo, Integer> entry : shoppingProductList.entrySet()) {
//					ProductInfo productInfo = entry.getKey();
//					if(shoppingBag.containsKey(productInfo)) {
//						shoppingBag.put(productInfo, shoppingBag.get(productInfo) + entry.getValue());
//					} else {
//						shoppingBag.put(productInfo, entry.getValue());
//					}
//				}
//
//				for (Map.Entry<ProductInfo, Integer> entry : shoppingBag.entrySet()) {
//					if(shoppingProductList.containsKey(entry.getKey())){
//						ShoppingProduct shoppingProduct1 = new ShoppingProduct(userInfo, entry.getKey(), entry.getValue());
//						shoppingProductService.update(shoppingProduct1);
//					} else {
//						ShoppingProduct shoppingProduct2 = new ShoppingProduct(userInfo, entry.getKey(), entry.getValue());
//						shoppingProductService.update(shoppingProduct2);
//					}
//				}
//
//			} else {
//				for (ShoppingProduct shoppingProduct : shoppingProductService.findAllByUserId(userInfo.getId())){
//					shoppingBag.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
//				}
//			}
//		} else {
//			if(!shoppingBag.isEmpty()) {
//				for (Map.Entry<ProductInfo, Integer> entry : shoppingBag.entrySet()) {
//					ShoppingProduct shoppingProduct = new ShoppingProduct(userInfo, entry.getKey(), entry.getValue());
//					shoppingProductService.update(shoppingProduct);
//				}
//			}
//		}
//	}

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl();
		if (response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Builds the target URL according to the logic defined in the main class
	 * Javadoc.
	 */
	private String determineTargetUrl() {
		UserInfo userInfo = AppUtil.getCustomerFromSession();

		if (userInfo != null && userInfo.isCustomer())
			return "/";
		else if (userInfo != null && userInfo.isManager())
			return "/manager";
		else
			throw new IllegalStateException();

	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
