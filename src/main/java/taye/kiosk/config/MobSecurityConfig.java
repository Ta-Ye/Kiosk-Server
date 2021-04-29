package taye.kiosk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import taye.kiosk.security.StoreTokenProvider;

@Order(1)
@Configuration
public class MobSecurityConfig extends WebSecurityConfigurerAdapter {

    private final StoreTokenProvider storeProvider;

    public MobSecurityConfig(StoreTokenProvider storeProvider) {
        this.storeProvider = storeProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(storeProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .csrf().disable()
                .authorizeRequests(request->request.anyRequest().authenticated())
                .httpBasic()
                ;
    }

}

