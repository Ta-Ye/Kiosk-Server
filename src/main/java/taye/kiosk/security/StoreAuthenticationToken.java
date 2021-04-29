package taye.kiosk.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import taye.kiosk.domain.Store;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreAuthenticationToken implements Authentication {

    private Store principal;
    private String credentials;
    private String details;
    private boolean authenticated;
    private Set<GrantedAuthority> authorities;


    @Override
    public String getName() {
        return principal == null ? "" : principal.getStoreName();
    }

}
