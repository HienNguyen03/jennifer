package com.jennifer.config.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jennifer.dto.CustomerDetails;
import com.jennifer.entity.FavoriteProduct;
import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.FavoriteProductService;
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

		log.info("onAuthenticationSuccess");

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);

		/* *
		 * START : This part is for Favorite Products
		 * */
		List<ProductInfo> favoriteBag = (List<ProductInfo>) request.getSession().getAttribute("favoriteBag");
		FavoriteProductService favoriteProductService = (FavoriteProductService) request.getSession().getAttribute("favoriteBagAnchor");
		UserInfo userInfo = ((CustomerDetails) authentication.getPrincipal()).getUser();

		if(favoriteBag == null)
			favoriteBag = new ArrayList<>();

		List<ProductInfo> aCopyOfFavoriteBag = new ArrayList<>(favoriteBag);
		List<ProductInfo> userFavoriteProducts = new ArrayList<>();

		if(!userInfo.getFavoriteProducts().isEmpty()){
			for (FavoriteProduct favoriteProduct : userInfo.getFavoriteProducts()){
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

		/* *
		 * END : This part is for Favorite Products
		 * */


	}

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
