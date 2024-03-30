package luis122448.SmartShell.security.application.service.impl;
import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.security.application.repository.CompanyInfoRepository;
import luis122448.SmartShell.security.application.repository.UserRepository;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.*;
import luis122448.SmartShell.security.application.service.service.VerifyCodeService;
import luis122448.SmartShell.security.application.utility.ApiResponseAuth;
import luis122448.SmartShell.security.auth.jwt.JWTUtils;
import luis122448.SmartShell.security.auth.user.UserDetailsCustom;
import luis122448.SmartShell.security.auth.user.UserDetailsServiceCustom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.security.application.service.service.AuthService;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static luis122448.SmartShell.security.utility.constant.SecurityConstant.CODUSER;
import static luis122448.SmartShell.security.utility.constant.SecurityConstant.COMPANY;

@Service
public class AuthServiceImpl implements AuthService{

	@Qualifier("UserSecurityRepository")
	private final UserRepository usuarioRepository;
	@Qualifier("CompanyInfoSecurityRepository")
	private final CompanyInfoRepository companyInfoRepository;
	private final UserDetailsServiceCustom userDetailsServiceCustom;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final VerifyCodeService verifyCodeService;
	private final JWTUtils jwtUtils;
	public AuthServiceImpl(UserRepository usuarioRepository, CompanyInfoRepository companyInfoRepository, UserDetailsServiceCustom userDetailsServiceCustom, BCryptPasswordEncoder bCryptPasswordEncoder, VerifyCodeService verifyCodeService, JWTUtils jwtUtils) {
		this.usuarioRepository = usuarioRepository;
		this.companyInfoRepository = companyInfoRepository;
		this.userDetailsServiceCustom = userDetailsServiceCustom;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.verifyCodeService = verifyCodeService;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public ApiResponseAuth<?> login(LoginModel loginModel) throws GenericAuthServiceException {
		UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(loginModel.getCompany(), loginModel.getCoduser());
		if(isNull(userDetailsCustom)){
			throw new GenericAuthServiceException("USER OR COMPANY NOT EXISTS!");
		}
		if(!bCryptPasswordEncoder.matches(loginModel.getPassword(), userDetailsCustom.getPassword())){
			throw new GenericAuthServiceException("PASSWORD INCORRECT!");
		}
		if(userDetailsCustom.getNivel()==0){
			return new ApiResponseAuth<>(this.createToken(userDetailsCustom));
		}
		return new ApiResponseAuth<>(0,"SUCCESS",this.verifyCodeService.createCode(userDetailsCustom.getIdcompany(),userDetailsCustom.getCompany(),userDetailsCustom.getCoduser()));
	}

	@Override
	public ApiResponseAuth<?> verifyCode(VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException {
		UserEntity userEntity = this.verifyCodeService.verifyCode(verifyCodeModel.getCompany(), verifyCodeModel.getCoduser(), verifyCodeModel.getVerifyCode()).orElseThrow(
				() -> new GenericAuthServiceException("INVALID VERIFY CODE!")
		);
		UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(verifyCodeModel.getCompany(), verifyCodeModel.getCoduser());
		return new ApiResponseAuth<>(this.createToken(userDetailsCustom));
	}

	@Override
	public ApiResponseAuth<?> refreshToken(RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException {
		if(!this.jwtUtils.validateJwtToken(refreshTokenModel.getRefreshToken())){
			throw new GenericAuthServiceException("INVALID TOKEN!");
		}
		String token = this.jwtUtils.generateJwtFromTokenRefresh(refreshTokenModel.getRefreshToken());
		Map<String, Object> tokenData = this.jwtUtils.getDataJwtToken(refreshTokenModel.getRefreshToken());
		UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(tokenData.get(COMPANY).toString(), tokenData.get(CODUSER).toString());
		return new ApiResponseAuth<>(this.createToken(userDetailsCustom));
	}

	private Optional<TokenModel> createToken(UserDetailsCustom userDetailsCustom) throws GenericAuthServiceException {
		String token = this.jwtUtils.generateJwtToken(userDetailsCustom.getCompany(), userDetailsCustom.getCoduser(), false);
		String refreshToken = this.jwtUtils.generateJwtToken(userDetailsCustom.getCompany(), userDetailsCustom.getCoduser(), true);
		return Optional.of(new TokenModel(userDetailsCustom.getCoduser(), userDetailsCustom.getRole(), token, refreshToken));
	}

}
