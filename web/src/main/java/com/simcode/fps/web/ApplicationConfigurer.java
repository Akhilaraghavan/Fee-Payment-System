package com.simcode.fps.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simcode.fps.web.service.LoginDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ApplicationConfigurer extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO: authenticate
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and().exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
			.and()
			.csrf().disable()
			.formLogin().loginPage("/login")
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
			.successForwardUrl("/home")
			.defaultSuccessUrl("/home").permitAll()
			.and().logout()
			.logoutSuccessUrl("/login")
			.logoutUrl("/logout")
			.addLogoutHandler(logoutHandler)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.clearAuthentication(true)
			.permitAll().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login").maximumSessions(2)
			.expiredUrl("/login");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/icons/**", "/signup");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(passwordEncoder).and()
				.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return new UserAuthenticationFailureHandler();
	}

	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new UserAccessDeniedHandler();
	}

	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new UserAuthenticationSuccessHandler();
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new LoginDetailsService();
	}

	@Bean
	public LogoutHandler getLogoutHandler() {
		return new UserLogoutHandler();
	}
	/**
	 * Why do the voters return ACCESS_DENIED??
	 * 
	 * @return
	 */
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(new LoginAccessDecisionVoter());
		return new AffirmativeBased(decisionVoters);
	}

	@Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error").setViewName("error");
	}
}
