package luis122448.SmartShell.security.constant;

public class SecurityConstant {

//Spring Security
 public static final String API_AUTH = "/v1/auth";
 public static final String LOGIN_URL = "/login";
 public static final String REFRESH_TOKEN_URL = "/refreshtoken";
 
 public static final String HEADER_AUTHORIZACION_KEY 	= "Authorization";
 public static final String HEADER_REFRESH_TOKEN_KEY 	= "RefreshToken";
 
 public static final String TOKEN_BEARER_PREFIX 		= "Bearer ";
 public static final String AUTHORITIES					= "authorities";
 public static final String TOTP_KEY					= "KXJT7ADTPNAMIQQCOVBMVFIQNCOE2V6X";
 
 // JWT

 public static final String ISSUER_INFO = "http://www.galaxy.edu.pe/";
 
 //https://www.allkeysgenerator.com/ Encryption key 512-bit
 
 public static final String SUPER_SECRET_KEY = "r4u7x!A%D*G-KaP"; //123
 
 
 public static final long 	TOKEN_EXPIRATION_TIME_TOKEN = 15_000_000_00; // 1 day  86_400_000

 public static final long 	TOKEN_EXPIRATION_TIME_REFRESH_TOKEN = 600_000; // 1 day  86_400_000

}
