package com.syspro.booksns.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(authz -> authz
				//セキュリティ設定を無視するパスを指定する
				//WebSecurityConfigurerAdapterは現在非推奨
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //cssなどはログインなしでもアクセス可
				.mvcMatchers("/signup", "/login").permitAll()
				.anyRequest().authenticated()
		).formLogin(login -> login
				.loginProcessingUrl("/login")
				//ログイン時のURLを指定
				.loginPage("/login")
				//認証後にリダイレクトする場所を指定
				.defaultSuccessUrl("/")
				//ログイン失敗時のリダイレクト先
				.failureUrl("/login?error")
				.permitAll())
		.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
		//ブラウザを閉じて再度開いた場合でも「ログインしたままに」
		.rememberMe();
		
		return http.build();	
	}
	
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
}
