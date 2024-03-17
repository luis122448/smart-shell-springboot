package luis122448.SmartShell.security.auth.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsCustom implements UserDetails{

    @Getter
    private Integer idcompany;
    @Getter
    private String company;
    @Getter
    private String appellation;
    @Getter
    private String coduser;
    @Getter
    private String username;
    @Getter
    private String encode;
    @Getter
    private Integer nivel;
    @Getter
    private String code;
    @Getter
    private String role;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDate registdate;
    private LocalDate expiredate;
    private String status;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.encode;
    }

    @Override
    public String getUsername() {
        return this.coduser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return status.equals("Y");
    }
}
