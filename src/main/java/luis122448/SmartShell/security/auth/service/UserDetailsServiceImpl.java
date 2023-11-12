package luis122448.SmartShell.security.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import luis122448.SmartShell.security.api.dto.Authority;
import luis122448.SmartShell.application.domain.persistence.entity.UsuarioEntity;
import luis122448.SmartShell.application.domain.persistence.repository.UsuarioRepository;

import static java.util.Objects.isNull;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	public final UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UsuarioEntity obj = this.usuarioRepository.findById(username).orElseThrow(
					() -> new UsernameNotFoundException("USER NOT EXISTS!")
			);
			if (obj.getEstado().equals("N")) {
				throw new UsernameNotFoundException("USER INACTIVE FOR SYSTEM!");
			}
			UserDetails userDetails = User.builder()
					.username(obj.getCoduser())
					.password(obj.getEncode())
					.authorities(getAuthorities(obj.getCodrol()))
					.build();
			return userDetails;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UsernameNotFoundException("USUARIO, ERROR DESCONOCIDO");
		}
	}
	
	private List<GrantedAuthority> getAuthorities(String codrol){
		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_" + codrol));
		authorityList.add(new SimpleGrantedAuthority("ROLE_" + "TEST"));
		return authorityList;
	}	
	
	// No Implementado ( Rol, como arreglo )
//	private List<GrantedAuthority> getAuthorities(Set<AuthorityEntity> authorities){
//		List<GrantedAuthority> authorityList = new ArrayList<>();
//		for (AuthorityEntity authorityEntity : authorities) {
//			authorityList.add(new SimpleGrantedAuthority(authorityEntity.getNombre()));
//		}
//		//log.info("authorityList {}",authorityList);
//		return authorityList;
//	}	

}
