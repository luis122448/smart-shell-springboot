package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.BranchDTO;
import luis122448.SmartShell.application.domain.domain.service.service.BranchService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/branch")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.branchService.findAll(status));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "codbranch") Integer codbranch) throws GenericObjectServiceException {
        BranchDTO dto = new BranchDTO();
        dto.setCodbranch(codbranch);
        return ResponseEntity.ok(this.branchService.findById(dto));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BranchDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.branchService.save(dto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BranchDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.branchService.update(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("codbranch") Integer codbranch) throws GenericObjectServiceException {
        BranchDTO dto = new BranchDTO();
        dto.setCodbranch(codbranch);
        return ResponseEntity.ok(this.branchService.delete(dto));
    }

}
