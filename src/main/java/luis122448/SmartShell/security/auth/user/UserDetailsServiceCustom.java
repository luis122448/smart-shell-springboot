package luis122448.SmartShell.security.auth.user;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.UserModel;
import luis122448.SmartShell.security.application.service.service.LoginService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private final LoginService loginService;
    public UserDetailsServiceCustom(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserModel userModel = this.loginService.login("0000000001",username);
            return UserDetailsCustom.builder()
                    .idcompany(userModel.getIdcompany())
                    .company(userModel.getCompany())
                    .appellation(userModel.getAppellation())
                    .coduser(userModel.getCoduser())
                    .username(userModel.getUsername())
                    .encode(userModel.getEncode())
                    .nivel(userModel.getNivel())
                    .code(userModel.getCode())
                    .role(userModel.getRole())
                    .authorities(getAuthorities(userModel.getRole()))
                    .registdate(userModel.getRegistdate())
                    .expiredate(userModel.getExpiredate())
                    .status("Y")
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException("ERROR UNKNOWN", e);
        }
    }

    public UserDetailsCustom loadUserByUsernameAndCompany(String company, String username) throws GenericAuthServiceException {
        UserModel userModel = this.loginService.login(company,username);
        return UserDetailsCustom.builder()
                .idcompany(userModel.getIdcompany())
                .company(userModel.getCompany())
                .appellation(userModel.getAppellation())
                .coduser(userModel.getCoduser())
                .username(userModel.getUsername())
                .encode(userModel.getEncode())
                .nivel(userModel.getNivel())
                .code(userModel.getCode())
                .role(userModel.getRole())
                .authorities(getAuthorities(userModel.getRole()))
                .registdate(userModel.getRegistdate())
                .expiredate(userModel.getExpiredate())
                .status("Y")
                .build();
    }

    private List<GrantedAuthority> getAuthorities(String role){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + "TEST"));
        return authorityList;
    }

}
