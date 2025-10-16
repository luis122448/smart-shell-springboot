package luis122448.SmartShell.security.application.service.impl;

import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.security.application.repository.UserRepository;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.VerifyCodeModel;
import luis122448.SmartShell.security.application.service.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.security.auth.utility.TOTPUtil;

import java.util.Optional;

@Slf4j
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService{
	@Qualifier("UserSecurityRepository")
	private final UserRepository userRepository;
	private final TOTPUtil tOTPUtil;
	public VerifyCodeServiceImpl(UserRepository userRepository, TOTPUtil tOTPUtil) {
		this.userRepository = userRepository;
		this.tOTPUtil = tOTPUtil;
	}

	@Transactional
	public Optional<VerifyCodeModel> createCode(Integer idcompany,String company, String coduser) throws GenericAuthServiceException {
		String code = this.tOTPUtil.generateCode();
		this.userRepository.updateVerifyCode(idcompany, coduser, code);
		return Optional.of(new VerifyCodeModel(company, coduser, code));
	}
	
	public Optional<UserEntity> verifyCode(String company, String coduser, String code) throws GenericAuthServiceException {
		UserEntity userEntity = this.userRepository.findByCompanyAndCoduser(company, coduser).orElseThrow(() -> new GenericAuthServiceException("USER OR COMPANY NOT EXISTS!"));
		if (userEntity.getCode().equals(code) && this.tOTPUtil.verifyCode(code)) {
			return Optional.of(userEntity);
		} else {
			throw new GenericAuthServiceException("INVALID VERIFY CODE!");
		}
	}
}

