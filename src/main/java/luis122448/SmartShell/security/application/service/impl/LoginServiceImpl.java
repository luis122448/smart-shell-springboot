package luis122448.SmartShell.security.application.service.impl;

import luis122448.SmartShell.security.application.entity.CompanyInfoEntity;
import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.security.application.entity.key.UserKey;
import luis122448.SmartShell.security.application.repository.CompanyInfoRepository;
import luis122448.SmartShell.security.application.repository.UserRepository;
import luis122448.SmartShell.security.application.service.model.UserModel;
import luis122448.SmartShell.security.application.service.service.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Qualifier("UserSecurityRepository")
    private final UserRepository usuarioRepository;
    @Qualifier("CompanyInfoSecurityRepository")
    private final CompanyInfoRepository companyInfoRepository;
    public LoginServiceImpl(UserRepository usuarioRepository, CompanyInfoRepository companyInfoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.companyInfoRepository = companyInfoRepository;
    }

    @Override
    public UserModel login(String company, String coduser) throws Exception {
        CompanyInfoEntity companyEntity = this.companyInfoRepository.findByCompany(company).orElseThrow(() -> new Exception("COMPANY SSN INVALID!"));
        UserEntity userEntity = this.usuarioRepository.findById(new UserKey(companyEntity.getIdcompany(), coduser)).orElseThrow(() -> new Exception("USERNAME NOT EXISTS!"));
        return UserModel.builder()
                .idcompany(companyEntity.getIdcompany())
                .company(companyEntity.getCompany())
                .appellation(companyEntity.getAppellation())
                .coduser(userEntity.getCoduser())
                .username(userEntity.getApepat() + " " + userEntity.getApemat() + ", " + userEntity.getNombre())
                .encode(userEntity.getEncode())
                .nivel(userEntity.getNivel())
                .code(userEntity.getCode())
                .role(userEntity.getRole())
                .registdate(userEntity.getRegistdate())
                .expiredate(userEntity.getExpiredate())
                .status("Y")
                .build();
    }
}
