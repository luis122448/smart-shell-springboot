package luis122448.SmartShell.security.application.service.service;

import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.MetadataModel;

import java.util.Optional;

public interface MetadataService {
    Optional<MetadataModel> initMetadata() throws GenericAuthServiceException;
}
