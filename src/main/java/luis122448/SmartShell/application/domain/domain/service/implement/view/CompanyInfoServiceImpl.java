package luis122448.SmartShell.application.domain.domain.service.implement.view;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.view.CompanyInfoService;
import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.application.domain.persistence.repository.constants.CompanyInfoRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.constant.MESSAGEConstants;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;
    public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
    }

    @Override
    public ApiResponseList<CompanyInfoEntity> findAll(CompanyInfoEntity companyInfoEntity) throws GenericListServiceException {
        return new ApiResponseList<CompanyInfoEntity>(1, "Ok", Optional.of(this.companyInfoRepository.findAll()));
    }

    @Override
    public ApiResponseList<CompanyInfoEntity> findByLike(CompanyInfoEntity companyInfoEntity) throws GenericListServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> findById(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        return new ApiResponseObject<CompanyInfoEntity>(1,"Ok", this.companyInfoRepository.findById(companyInfoEntity.getNumint()));
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> save(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> update(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        if (!this.companyInfoRepository.existsById(companyInfoEntity.getNumint())){
             throw new GenericObjectServiceException(MESSAGEConstants.ID_NOT_EXISTS(companyInfoEntity.getNumint().toString()));
        }
        companyInfoEntity.setStatus("S");
        return new ApiResponseObject<CompanyInfoEntity>(1,"Ok",Optional.of(this.companyInfoRepository.save(companyInfoEntity)));
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> delete(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<CompanyInfoEntity> undelete(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException {
        return null;
    }
}
