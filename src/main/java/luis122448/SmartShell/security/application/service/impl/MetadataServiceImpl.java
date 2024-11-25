package luis122448.SmartShell.security.application.service.impl;

import luis122448.SmartShell.application.domain.domain.service.generic.WarehouseService;
import luis122448.SmartShell.application.domain.domain.service.service.*;
import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.MetadataModel;
import luis122448.SmartShell.security.application.service.service.MetadataService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetadataServiceImpl implements MetadataService {

    private final TypeCommercialDocumentService typeCommercialDocumentService;
    private final BranchService branchService;
    private final SellerService sellerService;
    private final SerieCommercialDocumentService serieCommercialDocumentService;
    private final ReasonCommercialDocumentService reasonCommercialDocumentService;
    private final SituationCommercialDocumentService situationCommercialDocumentService;
    private final TypeInventoryService typeInventoryService;
    private final WarehouseService warehouseService;
    private final ListPriceService listPriceService;
    private final TypeBusinessPartnerService typeBusinessPartnerService;
    private final UserService userService;
    private final CompanyInfoService companyInfoService;
    private final CurrencyService currencyService;

    public MetadataServiceImpl(TypeCommercialDocumentService typeCommercialDocumentService, BranchService branchService, SellerService sellerService, SerieCommercialDocumentService serieCommercialDocumentService, ReasonCommercialDocumentService reasonCommercialDocumentService, SituationCommercialDocumentService situationCommercialDocumentService, TypeInventoryService typeInventoryService, WarehouseService warehouseService, ListPriceService listPriceService, TypeBusinessPartnerService typeBusinessPartnerService, UserService userService, CompanyInfoService companyInfoService, CurrencyService currencyService) {
        this.typeCommercialDocumentService = typeCommercialDocumentService;
        this.branchService = branchService;
        this.sellerService = sellerService;
        this.serieCommercialDocumentService = serieCommercialDocumentService;
        this.reasonCommercialDocumentService = reasonCommercialDocumentService;
        this.situationCommercialDocumentService = situationCommercialDocumentService;
        this.typeInventoryService = typeInventoryService;
        this.warehouseService = warehouseService;
        this.listPriceService = listPriceService;
        this.typeBusinessPartnerService = typeBusinessPartnerService;
        this.userService = userService;
        this.companyInfoService = companyInfoService;
        this.currencyService = currencyService;
    }

    @Override
    public Optional<MetadataModel> initMetadata() throws GenericAuthServiceException {
       try {
          MetadataModel metadataModel = new MetadataModel();
          metadataModel.setTypeCommercialDocument(this.typeCommercialDocumentService.findAll().getList().orElseThrow());
          metadataModel.setBranch(this.branchService.findAll("Y").getList().orElseThrow());
          metadataModel.setSeller(this.sellerService.findAll("Y").getList().orElseThrow());
          metadataModel.setSerieCommercialDocument(this.serieCommercialDocumentService.findAll("Y").getList().orElseThrow());
          metadataModel.setReasonCommercialDocument(this.reasonCommercialDocumentService.findAll("Y").getList().orElseThrow());
          metadataModel.setSituationCommercialDocument(this.situationCommercialDocumentService.findAll("Y").getList().orElseThrow());
          metadataModel.setTypeInventory(this.typeInventoryService.findAll("Y").getList().orElseThrow());
          metadataModel.setWarehouse(warehouseService.findAll("Y").getList().orElseThrow());
          metadataModel.setListPrice(listPriceService.findAll("Y").getList().orElseThrow());
          metadataModel.setTypeBusinessPartner(this.typeBusinessPartnerService.findAll().getList().orElseThrow());
          metadataModel.setUser(this.userService.findByProfile().getObject());
          metadataModel.setCompany(this.companyInfoService.findByIdcompany().getObject());
          metadataModel.setCurrency(this.currencyService.findAll("Y").getList().orElseThrow());
          return Optional.of(metadataModel);
       } catch (Exception e) {
           throw new GenericAuthServiceException("ERROR INIT METADATA", e);
       }
    }

}
