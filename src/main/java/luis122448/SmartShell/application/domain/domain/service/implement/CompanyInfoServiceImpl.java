package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.CompanyInfoService;
import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.application.domain.persistence.repository.CompanyInfoRepository;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.constant.MESSAGEConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Qualifier("CompanyInfoRepository")
    private final CompanyInfoRepository companyInfoRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository, SecurityContextInitializer securityContextInitializer) {
        this.companyInfoRepository = companyInfoRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> findByIdcompany() throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        CompanyInfoEntity companyInfoEntity = this.companyInfoRepository.findByIdcompany(idcompany).orElseThrow(
                () -> new GenericListServiceException(MESSAGEConstants.ID_NOT_EXISTS(idcompany.toString())));
        return new ApiResponseObject<>(Optional.of(companyInfoEntity));
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> update(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        if (!this.companyInfoRepository.existsById(idcompany)){
            throw new GenericObjectServiceException(MESSAGEConstants.ID_NOT_EXISTS(idcompany.toString()));
        }
        companyInfoEntity.setIdcompany(idcompany);
        companyInfoEntity.setStatus("Y");
        return new ApiResponseObject<>(Optional.of(this.companyInfoRepository.save(companyInfoEntity)));
    }

}
