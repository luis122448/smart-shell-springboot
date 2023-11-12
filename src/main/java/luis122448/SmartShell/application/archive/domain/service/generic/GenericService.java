package luis122448.SmartShell.application.archive.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GenericService<T> {

    ApiResponseObject<T> findArchive(String id) throws GenericObjectServiceException;

    ApiResponseObject<T> addArchive(T t, MultipartFile multipartFile) throws GenericObjectServiceException;

    ApiResponseObject<T> deleteArchive(String id) throws GenericObjectServiceException;

}
