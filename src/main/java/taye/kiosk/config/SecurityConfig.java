package taye.kiosk.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import taye.kiosk.service.StoreService;

@Order(2)
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final StoreService storeService;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public SecurityConfig(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(storeService);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
                .authorizeRequests(request->
                        request.antMatchers("/login/**", "/signin/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        login->login.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", false)
                        .failureUrl("/login/error")
                )
                .logout(logout->logout.logoutSuccessUrl("/"))
                .exceptionHandling(e->e.accessDeniedPage("/error"))
                ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                ;
    }
}