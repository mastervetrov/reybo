package reybo.shop.common.security.entities.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
public class Role {

    private Long id;

    private RoleType authority;

    private User user;

    public GrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(authority.name());
    }

    public static Role from(RoleType type, User user) {
        var role = new Role();
        role.setAuthority(type);
        role.setUser(user);
        return role;
    }
}
