package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.service.service.UserService;
import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING +"/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> findByProfile() throws GenericObjectServiceException {
        return ResponseEntity.ok(this.userService.findByProfile());
    }
}
