package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.service.generic.WarehouseService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/find-by-all")
    public ResponseEntity<?> findByAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return new ResponseEntity<>(warehouseService.findAll(status), HttpStatus.OK);
    }

    @GetMapping("/find-by-typinv")
    public ResponseEntity<?> findByTypinv(@RequestParam(name = "typinv", defaultValue = "1") Integer typinv,
                                          @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return new ResponseEntity<>(warehouseService.findByTypinv(typinv, status), HttpStatus.OK);
    }

}
