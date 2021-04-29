package taye.kiosk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import taye.kiosk.domain.Store;
import taye.kiosk.service.StoreService;

@Component
public class StoreTokenProvider implements AuthenticationProvider{
	
	@Autowired
	StoreService storeService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            System.out.println(token.getName());
            System.out.println(storeService.findStore(token.getName()).orElse(null));
            if (storeService.findStore(token.getName()).isEmpty()) {
            	return null;
            }
            return getAuthenticationToken(token.getName());
        }
        StoreAuthenticationToken token = (StoreAuthenticationToken) authentication;
        if (storeService.findStore(token.getName()).isPresent()) {
        	return getAuthenticationToken(token.getName());
        }
        return null;
    }

    private StoreAuthenticationToken getAuthenticationToken(String id) {
        Store store = storeService.findStore(id).get();
        return StoreAuthenticationToken.builder()
                .principal(store)
                .details(store.getStoreName())
                .authenticated(true)
                .build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == StoreAuthenticationToken.class ||
                authentication == UsernamePasswordAuthenticationToken.class;
    }
}
