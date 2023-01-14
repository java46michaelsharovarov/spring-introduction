package telran.spring.calculator.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import telran.spring.calculator.controller.CalculatorController;

@Configuration
@EnableWebSecurity
public class CalculatorSecurityConfiguration {

	Logger LOG = LoggerFactory.getLogger(CalculatorController.class);
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
			.httpBasic();
		return http.build();
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
			
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		String userName = "user";
		manager.createUser(User.withUsername(userName)
				.password(bCryptPasswordEncoder.encode("userPass"))
				.roles("USER")
				.build());		
		LOG.debug("Created user: {}", manager.loadUserByUsername(userName));
		String adminName = "admin";
		manager.createUser(User.withUsername(adminName)
				.password(bCryptPasswordEncoder.encode("adminPass"))
				.roles("ADMIN", "USER")
				.build());
		LOG.debug("Created user: {}", manager.loadUserByUsername(adminName));
		return manager;
	}
	
}