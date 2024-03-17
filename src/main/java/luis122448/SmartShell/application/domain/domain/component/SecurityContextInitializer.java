package luis122448.SmartShell.application.domain.domain.component;

import luis122448.SmartShell.security.auth.user.UserDetailsCustom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextInitializer {

    public Integer getIdCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return userDetailsCustom.getIdcompany();
        } else {
            return 0;
        }
    }

    public String getCodUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return userDetailsCustom.getCoduser();
        } else {
            return "unknown";
        }
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return userDetailsCustom.getUsername();
        } else {
            return "unknown";
        }
    }
}