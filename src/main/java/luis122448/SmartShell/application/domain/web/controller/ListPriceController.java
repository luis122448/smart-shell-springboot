package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.ListPriceDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/list-price")
public class ListPriceController {
    private final ListPriceService listPriceService;

    public ListPriceController(ListPriceService listPriceService) {
        this.listPriceService = listPriceService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.listPriceService.findAll(status));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "codlistprice") Integer codlistprice) throws GenericObjectServiceException{
        ListPriceDTO dto = new ListPriceDTO();
        dto.setCodlistprice(codlistprice);
        return ResponseEntity.ok(this.listPriceService.findById(dto));
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ListPriceDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.listPriceService.save(dto));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody ListPriceDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.listPriceService.update(dto));
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "codlistprice") Integer codlistprice) throws GenericObjectServiceException {
        ListPriceDTO dto = new ListPriceDTO();
        dto.setCodlistprice(codlistprice);
        return ResponseEntity.ok(this.listPriceService.delete(dto));
    }
}
