package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
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
    public ResponseEntity<?> findAll() throws GenericListServiceException {
        ApiResponseList<ListPriceEntity> obj = this.listPriceService.findAll();
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "codlistprice", defaultValue = "0") Integer codlistprice) throws GenericObjectServiceException{
        ListPriceEntity tmp = new ListPriceEntity();
        tmp.setCodlistprice(codlistprice);
        ApiResponseObject<ListPriceEntity> obj = this.listPriceService.findById(tmp);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        ApiResponseObject<ListPriceEntity> obj = this.listPriceService.save(listPriceEntity);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestParam(name = "codlistprice", defaultValue = "0") Integer codlistprice,
                                    @RequestBody ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        listPriceEntity.setCodlistprice(codlistprice);
        ApiResponseObject<ListPriceEntity> obj = this.listPriceService.update(listPriceEntity);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "codlistprice", defaultValue = "0") Integer codlistprice) throws GenericObjectServiceException {
        ListPriceEntity tmp = new ListPriceEntity();
        tmp.setCodlistprice(codlistprice);
        ApiResponseObject<ListPriceEntity> obj = this.listPriceService.delete(tmp);
        return ResponseEntity.ok(obj);
    }
}
