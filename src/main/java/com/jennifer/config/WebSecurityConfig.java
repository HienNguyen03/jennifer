package com.jennifer.config;

import javax.annotation.Resource;

import com.jennifer.config.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Manages all the security configuration of the application
 */

@Configuration
@EnableWebSecurity
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Resource
	private UserDetailsService customerDetailsService;
	
	@Resource
    private SessionRegistry sessionRegistry;
//	
//	@Bean
//	public LogoutSuccessHandler customLogoutSuccessHandler() {
//		return new LogoutSuccessHandler() {
//
//			@Override
//			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//
//				if (authentication != null) {
//					//System.out.println(authentication.getName());
//				}
//				
//				// perform other required operation
//				String URL = request.getContextPath() + "/logout";
//				response.setStatus(HttpStatus.OK.value());
//				response.sendRedirect(URL);
//
//			}
//		};
//	}
	
	@Value("${rememberMe.privateKey}")
	private String myPrivateKey;

	/** 
	 * The persistent-login authentication able to remember the identity of a principal between sessions
	 */
	@Bean
	public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(myPrivateKey,
				customerDetailsService);
		rememberMeServices.setAlwaysRemember(false);
		rememberMeServices.setTokenValiditySeconds(86400);
		return rememberMeServices;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				//.antMatchers("/", "/favicon.ico", "/public/**", "/resources/**", "/custom/**", "/lib/**").permitAll()
				//.antMatchers("/signup", "/session-expired", "/access-denied", "/404", "/r/**").anonymous()
				.antMatchers("/manager/**").hasRole("MANAGER")

				//.anyRequest().authenticated();
				.anyRequest().permitAll();

		http
				.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(new CustomAuthenticationSuccessHandler())
				.failureUrl("/login?error=true")
				.permitAll()
			.and()
				.logout().logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout=true")
				//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				//.addLogoutHandler(new ProperCookieClearingLogoutHandler("JSESSIONID"))
				.permitAll()
			.and()
				.rememberMe()
				.key(myPrivateKey)
				.rememberMeServices(rememberMeServices())
			.and()
				.csrf()
			.and()
				.exceptionHandling().accessDeniedPage("/access-denied")
			.and()
				.sessionManagement()
				.sessionFixation()
				.changeSessionId()
		        .maximumSessions(1)
		        .maxSessionsPreventsLogin(true)
		        .expiredUrl("/session-expired")
		        .sessionRegistry(sessionRegistry);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerDetailsService).passwordEncoder(passwordEncoder());
	}

}
