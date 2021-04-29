package taye.kiosk.dto;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import taye.kiosk.domain.Store;

public class StoreDTO {
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class StoreAuthority implements GrantedAuthority {
		private String authority;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class StoreDetail implements UserDetails{
		
		private String storeName;
		@JsonIgnore
		private String storeKey;
		private Set<StoreAuthority> authorities; 
		private boolean enabled;
		
		@Override
		public String getPassword() {
			return storeKey;
		}
		@Override
		public String getUsername() {
			return storeName;
		}
		@Override
		public boolean isAccountNonExpired() {
			return enabled;
		}
		@Override
		public boolean isAccountNonLocked() {
			return enabled;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			return enabled;
		}
		
		public static StoreDetail of(Store store) {
			return StoreDetail.builder().storeName(store.getStoreName()).storeKey(store.getStoreKey())
					.authorities(Set.of(new StoreAuthority("ROLE_USER"))).enabled(true).build();
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class StoreName {
		private String storeName;
		
		public static StoreName of(Store store) {
			return new StoreName(store.getStoreName());
		}
	}
		
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class StoreRegi {		
		private String id;
		private String password;
		
		public static Store toEntity(StoreRegi store) {
			return Store.builder().storeName(store.getId()).storeKey(store.getPassword()).build();
		}
	}
}
