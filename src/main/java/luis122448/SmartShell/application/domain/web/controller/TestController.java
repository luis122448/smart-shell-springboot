package luis122448.SmartShell.application.domain.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.*;

@RestController
@RequestMapping(PATH_TEST)
public class TestController {

    @Operation(tags = {TAG_TEST})
    @GetMapping("/hello-world")
    private ResponseEntity<?> helloWorld(){
        return ResponseEntity.ok("Hello World");
    }

}
