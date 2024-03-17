package luis122448.SmartShell.application.domain.domain.service.implement.view;

import luis122448.SmartShell.security.auth.user.UserDetailsCustom;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.view.CompanyInfoService;
import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.application.domain.persistence.repository.constants.CompanyInfoRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.constant.MESSAGEConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    private final Integer idcompany;
    private final String coduser;
    private final CompanyInfoRepository companyInfoRepository;
    public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            this.idcompany = userDetailsCustom.getIdcompany();
            this.coduser = userDetailsCustom.getCoduser();
        } else {
            this.idcompany = 0;
            this.coduser = "UNKNOWN";
        }
    }

    @Override
    public ApiResponseList<CompanyInfoEntity> findAll() throws GenericListServiceException {
        return new ApiResponseList<CompanyInfoEntity>(Optional.of(this.companyInfoRepository.findByIdcompany(idcompany)));
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> update(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        if (!this.companyInfoRepository.existsById(companyInfoEntity.getIdcompany())){
             throw new GenericObjectServiceException(MESSAGEConstants.ID_NOT_EXISTS(companyInfoEntity.getIdcompany().toString()));
        }
        companyInfoEntity.setStatus("Y");
        return new ApiResponseObject<CompanyInfoEntity>(Optional.of(this.companyInfoRepository.save(companyInfoEntity)));
    }

}
