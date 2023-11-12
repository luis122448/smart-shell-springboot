package luis122448.SmartShell.security.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppEncoder {
	
	public static void main(String[] arg) {
		// Longitud Predetermianda 10*
		System.out.println(new BCryptPasswordEncoder().encode("1073741824"));
		// Mayor Nivel de Encriptacion = Menor Optimización
//		System.out.println(new BCryptPasswordEncoder(16).encode("123"));
	}
	//	Alt + Shift + X, J
	// $2a$10$fZT4cdDwfKy6aJXHWVmdz.n8iYU029HYWsxYrcQ8CxA4/s.NlK6hK
	// $2a$10$kW7wYBadjAL3vBdpMH7hd..TYJbb.66y17I588HlxYPUNVi/.9lf.
	// $2a$10$6J3Kw8HLcwlVFWkeAV1N6.Ebbg414BNdc6UvcCabnDWJK.cC5ehie
}
