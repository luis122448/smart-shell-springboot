package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.WarehouseDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.WarehouseService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findByAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.warehouseService.findAll(status));
    }

    @GetMapping("/by-typinv")
    public ResponseEntity<?> findByTypinv(@RequestParam(name = "typinv", defaultValue = "1") Integer typinv,
                                          @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.warehouseService.findByTypinv(typinv, status));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "typinv") Integer typinv,
                                     @RequestParam(name = "codwarehouse") Integer codwarehouse) throws GenericObjectServiceException {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setTypinv(typinv);
        dto.setCodwarehouse(codwarehouse);
        return ResponseEntity.ok(this.warehouseService.findById(dto));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody WarehouseDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.warehouseService.save(dto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody WarehouseDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.warehouseService.update(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("typinv") Integer typinv,
                                    @RequestParam("codwarehouse") Integer codwarehouse) throws GenericObjectServiceException {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setTypinv(typinv);
        dto.setCodwarehouse(codwarehouse);
        return ResponseEntity.ok(this.warehouseService.delete(dto));
    }


}
