package luis122448.SmartShell.security.application.service.service;

import luis122448.SmartShell.security.application.service.model.UserModel;

public interface LoginService {
    UserModel login(String company, String coduser) throws Exception;
}
